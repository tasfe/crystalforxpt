package com.combanc.monitor.quartz;

import java.util.List;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.dao.MonitorDeviceDAO;
import com.combanc.monitor.dao.MonitorLinkportDAO;
import com.combanc.monitor.dao.MonitorTopologyEdgeDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorTopologyEdge;
import com.combanc.monitor.util.ApplicationContextUtil;

public class GetLinkPortInfo  extends Thread {

	private List<MonitorLinkport> linkPortList;
	private MonitorLinkportDAO monitorLinkportDAO;
	private MonitorDeviceDAO monitorDeviceDAO;
	private MonitorTopologyEdgeDAO monitorTopologyEdgeDAO;
	
	public GetLinkPortInfo (List<MonitorLinkport> linkPortList){
		this.linkPortList = linkPortList;
		monitorLinkportDAO = (MonitorLinkportDAO) ApplicationContextUtil.getContext().getBean("MonitorLinkportDAO");
		monitorDeviceDAO = (MonitorDeviceDAO) ApplicationContextUtil.getContext().getBean("MonitorDeviceDAO");
		monitorTopologyEdgeDAO = (MonitorTopologyEdgeDAO) ApplicationContextUtil.getContext().getBean("MonitorTopologyEdgeDAO");
	}
	public void run(){
		for(MonitorLinkport linkPort:linkPortList){
		 
			SnmpTarget target = new SnmpTarget();// SNMP工具
			target.setLoadFromCompiledMibs(true);
			target.setTimeout(MainConstants.SNMP_TIME_OUT);
			target.setRetries(MainConstants.SNMP_RETRY);
			
			String[] pollData;// 接收值、发送值、接口速率、接口状态。
			String strIp,strInterface;// 如果下位设备接口为空则从上位设备读取数据，否则从下位设备读取
			String dataSource;//数据源
			if(linkPort.getDownlinkInterface()==null||linkPort.getDownlinkInterface().equals("")){
				strIp=linkPort.getIp();
				strInterface=linkPort.getInterface_();
				dataSource="up";
			} else {
				strIp=linkPort.getDownlinkIp();
				strInterface=linkPort.getDownlinkInterface();
				dataSource="down";
			}
			
			//IP不合法和没有端口的跳过
			if(!Tools.filter(strIp, strInterface))	
				continue;
			
			List<MonitorDevice> deviceList = monitorDeviceDAO.findByIp(strIp);
			if(deviceList.size()>0) {
				MonitorDevice device=deviceList.get(0);
				pollData = this.getFluxData(target, device, strInterface);
				
				// 数据不空，并且接口上线则数据有效；否则无效，如接口下线则警示故障。
				// 思科1750路由器接口上下线状态读取结果为“6”，通过接口测控时状态为“打开：6”发现此问题
				if (pollData != null && (pollData[11].equals("1")||pollData[11].equals("up(1)") || pollData[11].equals("6"))) {
					 this.saveData( linkPort,pollData, device.getNote1(),dataSource);
				}else if(pollData != null
						&&(pollData[11].equals("2")||pollData[11].equals("3")||pollData[11].equals("down(2)")||pollData[11].equals("testing(3)") ||pollData[11].indexOf("test") >= 0)
						&&(!pollData[1].equals("0")||!pollData[0].equals("0"))){
					this.saveData( linkPort,pollData, device.getNote1(),dataSource);
				}
				
			}
		}
	}
	
	
	/**
	 * 读设备接口收发数据，共12项。 
	 * @param target SNMPTARGET
	 * @param strHost IP
	 * @param ifIndex
	 * @param strCommunity COMMSTR
	 * @param c64 64位标志
	 * @return
	 */
	private String[]  getFluxData(SnmpTarget target, MonitorDevice device, String strInterface){
		String strHost=device.getIp();
		String strCommunity=device.getReadCommunity();
		String c64=device.getNote1();
		
		int errorCode, errorIndex;
		String[] OID = new String[13];
		String[] Result = new String[13];
		target.setTargetHost(strHost);
		target.setCommunity(strCommunity);
		if(c64 == null)
			c64 = "";
		
		// 是否为64位计数器
		if (c64.equals("C64")) {
			OID[0] = ".1.3.6.1.2.1.31.1.1.1.10." + strInterface;// 字节发送，64位计数器
			OID[1] = ".1.3.6.1.2.1.31.1.1.1.6." + strInterface;// 字节接收
			OID[10] = ".1.3.6.1.2.1.31.1.1.1.15." + strInterface;// 接口速率
			target.setSnmpVersion(SnmpTarget.VERSION2C);
		} else {
			OID[0] = ".1.3.6.1.2.1.2.2.1.16." + strInterface;// 字节发送，32位计数器
			OID[1] = ".1.3.6.1.2.1.2.2.1.10." + strInterface;// 字节接收
			OID[10] = ".1.3.6.1.2.1.2.2.1.5." + strInterface;// 接口速率
			target.setSnmpVersion(SnmpTarget.VERSION1);
		}
		
		OID[2] = ".1.3.6.1.2.1.2.2.1.17." + strInterface;// 单播包发送
		OID[3] = ".1.3.6.1.2.1.2.2.1.11." + strInterface;// 单播包接收
		OID[4] = ".1.3.6.1.2.1.2.2.1.18." + strInterface;// 广播包发送
		OID[5] = ".1.3.6.1.2.1.2.2.1.12." + strInterface;// 广播包接收
		OID[6] = ".1.3.6.1.2.1.2.2.1.20." + strInterface;// 错包发送
		OID[7] = ".1.3.6.1.2.1.2.2.1.14." + strInterface;// 错包接收
		OID[8] = ".1.3.6.1.2.1.2.2.1.19." + strInterface;// 丢包发送
		OID[9] = ".1.3.6.1.2.1.2.2.1.13." + strInterface;// 丢包接收
		OID[11] = ".1.3.6.1.2.1.2.2.1.8." + strInterface;// 接口上线状态 up(1), down(2), testing(3)
		OID[12] = ".1.3.6.1.2.1.2.2.1.7." + strInterface;// 接口操作（开闭）状态
		
		target.setObjectIDList(OID);
		Result = target.snmpGetList();
		
		errorCode = target.getErrorCode();
		errorIndex = target.getErrorIndex();
		
		// 成功读取
		if (errorCode == 0)
			return Result;
		
		// 超时
		if (errorCode == 22 && errorIndex == 0)
			return null;
		
		// 没有取到全部数据，如LINUX无广播包、错包，重读，这时返回null，改只读字节和单播包
		if (errorCode == 2) {// noSuchName：暂不考虑64位计数器
			OID = new String[7];
			OID[0] = ".1.3.6.1.2.1.2.2.1.16." + strInterface;// 字节发送
			OID[1] = ".1.3.6.1.2.1.2.2.1.10." + strInterface;// 字节接收
			OID[2] = ".1.3.6.1.2.1.2.2.1.17." + strInterface;// 单播包发送
			OID[3] = ".1.3.6.1.2.1.2.2.1.11." + strInterface;// 单播包接收
			OID[4] = ".1.3.6.1.2.1.2.2.1.5." + strInterface;// 接口速率
			OID[5] = ".1.3.6.1.2.1.2.2.1.8." + strInterface;// 接口上线状态
			OID[6] = ".1.3.6.1.2.1.2.2.1.7." + strInterface;// 接口操作（开闭）状态

			target.setObjectIDList(OID);
			Result = target.snmpGetList();
			errorCode = target.getErrorCode();
			errorIndex = target.getErrorIndex();
			
			if (errorCode != 0) {
				return null;
			} else {
				// 按相同格式构造数据，未读数据用0填充。
				String[] tmp = new String[13];
				for (int i = 0; i <= 3; i++)
					tmp[i] = Result[i];
				for (int i = 4; i <= 9; i++)
					tmp[i] = "0";// 广播包、错包、丢包
				tmp[10] = Result[4];// 接口速率
				tmp[11] = Result[5];// 上线状态
				tmp[12] = Result[6];// 接口操作（开闭）状态
				return tmp;
			}
		}
		return null;
		
		
	}

	/**保存数据**/
	private void saveData(MonitorLinkport linkPort,String[] pollData,String c64,String dataSource){
		
		long[] now = new long[11];// 当前值，第11个值为接口速率，初值为0
		long gap, time, timeGap;
		for (int i = 0; i < 11; i++)
			if (pollData[i] != null){
				try {
					now[i] = Long.parseLong(pollData[i]);
				} catch (NumberFormatException e) {
				}
			}
		// 取当前时间：
		time = System.currentTimeMillis();
		if(c64 == null)
			c64 = "";
		MonitorTopologyEdge model=monitorTopologyEdgeDAO.findByCondition(
				linkPort.getIp(), 
				linkPort.getInterface_(), 
				linkPort.getDownlinkIp(), 
				linkPort.getDownlinkInterface());
		
		// 如果是第一组有效数据，差值直接取当前数据
		if(model==null){
			model=new MonitorTopologyEdge();
			model.setIp(linkPort.getIp());
			model.setInterface_(linkPort.getInterface_());
			model.setDownlinkIp(linkPort.getDownlinkIp());
			model.setDownlinkInterface(linkPort.getDownlinkInterface());
			model.setDataSource(dataSource);
			model.setSendByte(now[0]);
			model.setReceiveByte(now[1]);
			model.setSendUnicastPacket(now[2]);
			model.setReceiveUnicastPacket(now[3]);
			model.setSendBroadcastPacket(now[4]);
			model.setReceiveBroadcastPacket(now[5]);
			model.setSendWrongPacket(now[6]);
			model.setReceiveWrongPacket(now[7]);
			model.setSendLostPacket(now[8]);
			model.setReceiveLostPacket(now[9]);
			model.setDiffSendByte(now[0]);
			model.setDiffReceiveByte(now[1]);
			model.setDiffSendUnicastPacket(now[2]);
			model.setDiffReceiveUnicastPacket(now[3]);
			model.setDiffSendBroadcastPacket(now[4]);
			model.setDiffReceiveBroadcastPacket(now[5]);
			model.setDiffSendWrongPacket(now[6]);
			model.setDiffReceiveWrongPacket(now[7]);
			model.setDiffSendLostPacket(now[8]);
			model.setDiffReceiveLostPacket(now[9]);
			
			model.setTime(time);
			model.setTimeGap(0L);
			if (c64.equals("C64"))
				model.setInterfaceRate(now[10] * 1000000);
			else
				model.setInterfaceRate(now[10]);
			monitorTopologyEdgeDAO.save(model);
		}else{
			// 计算两次时间差
			timeGap = time - model.getTime();
			// 计算两次差值、当前带宽并将数据保存到表中。共5组10个数据。
			// 如果当前值大于等于前次值，则取差；如果小于则表明32位计数器归零，使用上次差值做近似。
			if(now[0]>model.getSendByte()){
				model.setDiffSendByte( now[0] - model.getSendByte());//刷新当前差值
			}
			if(now[1]>model.getReceiveByte()){
				model.setDiffReceiveByte(now[1] - model.getReceiveByte());
			}
			
			if(now[2]>model.getSendUnicastPacket()){
				model.setDiffSendUnicastPacket(now[2] - model.getSendUnicastPacket());
			}
			
			if(now[3]>model.getReceiveUnicastPacket()){
				model.setDiffReceiveUnicastPacket(now[3] - model.getReceiveUnicastPacket());
			}
			if(now[4]>model.getSendBroadcastPacket()){
				model.setDiffSendBroadcastPacket(now[4] - model.getSendBroadcastPacket());
			}
			if(now[5]>model.getReceiveBroadcastPacket()){
				model.setDiffReceiveBroadcastPacket(now[5] - model.getReceiveBroadcastPacket());
			}
			if(now[6]>model.getSendWrongPacket()){
				model.setDiffSendWrongPacket(now[6] - model.getSendWrongPacket());
			}
			
			if(now[7]>model.getReceiveWrongPacket()){
				model.setDiffReceiveWrongPacket(now[7] - model.getReceiveWrongPacket());
			}
			
			if(now[8]>model.getSendLostPacket()){
				model.setDiffSendLostPacket(now[8] - model.getSendLostPacket());
			}
			
			if(now[9]>model.getReceiveLostPacket()){
				model.setDiffReceiveLostPacket(now[9] - model.getReceiveLostPacket());
			}
				 
			//替换上次值	
			model.setSendByte(now[0]);
			model.setReceiveByte(now[1]);
			model.setSendUnicastPacket(now[2]);
			model.setReceiveUnicastPacket(now[3]);
			model.setSendBroadcastPacket(now[4]);
			model.setReceiveBroadcastPacket(now[5]);
			model.setSendWrongPacket(now[6]);
			model.setReceiveWrongPacket(now[7]);
			model.setSendLostPacket(now[8]);
			model.setReceiveLostPacket(now[9]);
			
			model.setTime(time);
			model.setTimeGap(timeGap);
			if (c64.equals("C64"))
				model.setInterfaceRate(now[10] * 1000000);
			else
				model.setInterfaceRate(now[10]);
			
		 
			// 比较并生成当前边的峰值
			//刷新双向峰值及时间
			long tmp = getLinkLoad(model.getDiffSendByte()+model.getDiffReceiveByte(),model.getInterfaceRate(),timeGap);
			if (model.getBilateralPeak()==null||tmp >= model.getBilateralPeak()) {
				model.setBilateralPeak(tmp);
				model.setBilateralPeakTime(time);
			}	
			//刷新发送峰值及时间
			tmp = getLinkLoad(model.getDiffSendByte(),model.getInterfaceRate(),timeGap);
			if (model.getSendPeak()==null||tmp >= model.getSendPeak()) {
				model.setSendPeak(tmp);
				model.setSendPeakTime(time);
			}	
			
			//刷新接收峰值及时间
			tmp = getLinkLoad(model.getDiffReceiveByte(),model.getInterfaceRate(),timeGap);
			if (model.getReceivePeak()==null||tmp >= model.getReceivePeak()) {
				model.setReceivePeak(tmp);
				model.setReceivePeakTime(time);
			}
			monitorTopologyEdgeDAO.update(model);
		}
	}
	
	/**得到链路负荷**/
	private long getLinkLoad(long bytes, long ifspd, long timeGap) {
		if (ifspd <= 0)
			return 0;
		long bw = bytes * 1000 / timeGap;
		return (bw * 8 * 100 + ifspd / 2) / ifspd;// 四舍五入
	}
}
