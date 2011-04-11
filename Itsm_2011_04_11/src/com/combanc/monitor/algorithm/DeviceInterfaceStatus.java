package com.combanc.monitor.algorithm;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojoext.MonitorInterfaceCacheExt;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorInterfaceCacheService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.LinkPortUtilities;

/**
 * <p>
 * Title: 轮询设备接口流量数据，并存入数据库
 * </p>
 * 
 * @author li shijie
 * @version 2.0
 */

public class DeviceInterfaceStatus {
	private MonitorDeviceService monitorDeviceService;
	private MonitorInterfaceCacheService monitorInterfaceCacheService;
	private MonitorLinkportService monitorLinkportService;
	private MonitorSystemParamService monitorSystemParamService;
	private MonitorDevice monitorDevice;
	
	// 轮询结果存在扩展po的list，interfaceCacheExtList里面
	List<MonitorInterfaceCacheExt> interfaceCacheExtList = new ArrayList<MonitorInterfaceCacheExt>();
	List<MonitorInterfaceCache> interfaceCacheList = new ArrayList<MonitorInterfaceCache>();
	TextDataFile resultDataFile = new TextDataFile();

	int bandLimen = SystemParamConstants.SYSTEM_BAND_LIMEN_DEFUALT_VALUE;
	int unicastLimen = SystemParamConstants.SYSTEM_UNICAST_LIMEN_DEFUALT_VALUE;
	int broadcastLimen = SystemParamConstants.SYSTEM_BROADCAST_LIMEN_DEFUALT_VALUE;
	
	boolean bandOverLimen;
	boolean singleOverLimen;
	boolean multiOverLimen;
	boolean errorOverLimen;
	boolean discardOverLimen;

	boolean inPoll = true;
	boolean internalRefresh = false;
	boolean panelImgLoad = false;
	int lastSelectRow = -1;
	
	boolean isServer = false;
	
	String direction = "双向";
	String mode = "数据";
	String counter64 = "";
	
	int[][] pollStatus;// [0]=1得到有效数据，-1没有得到有效数据，0未轮训；[1]=1接口上线，-1接口下线，-2接口关闭

	DataPollTools dataPollTools = new DataPollTools();
	SnmpTarget target = new SnmpTarget();
	// 2010-08-20
	private DeviceSnmpQuery deviceSnmpQuery;
	
	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}

	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	
	public MonitorDevice getMonitorDevice() {
		return monitorDevice;
	}

	public void setMonitorDevice(MonitorDevice monitorDevice) {
		this.monitorDevice = monitorDevice;
	}
	
	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}
	
	public MonitorInterfaceCacheService getMonitorInterfaceCacheService() {
		return monitorInterfaceCacheService;
	}

	public void setMonitorInterfaceCacheService(
			MonitorInterfaceCacheService monitorInterfaceCacheService) {
		this.monitorInterfaceCacheService = monitorInterfaceCacheService;
	}
	
	public List<MonitorInterfaceCacheExt> getInterfaceCacheExtList() {
		return interfaceCacheExtList;
	}

	public void setInterfaceCacheExtList(
			List<MonitorInterfaceCacheExt> interfaceCacheExtList) {
		this.interfaceCacheExtList = interfaceCacheExtList;
	}

	public DeviceInterfaceStatus() {
	}

	public void init(Integer deviceId) throws Exception {
		interfaceCacheExtList.clear();
		MonitorDevice monitorDevice = monitorDeviceService.get(deviceId);
		String c64 = monitorDevice.getNote1();
		if (c64 != null && c64.length() > 0)
			counter64 = c64.toUpperCase();
		// 将interfaceCacheList里面的信息复制到扩展List里面
		if(monitorDevice != null && (interfaceCacheExtList == null || interfaceCacheExtList.isEmpty())) {
			// interfaceCacheList = monitorInterfaceCacheService.findByDeviceId(deviceId);
			// 2010-09-20  此处isSave不能为true，否则flex端报错：connection is read-only
			interfaceCacheList.clear();
			deviceSnmpQuery.readDeviceInterface(monitorDevice, interfaceCacheList, false);
			// deviceSnmpQuery.readDeviceInterface(monitorDevice, interfaceCacheList, true);
			for(MonitorInterfaceCache mic : interfaceCacheList) {
				MonitorInterfaceCacheExt mice = new MonitorInterfaceCacheExt();
				mice.setIp(mic.getMonitorDevice().getIp());
				mice.setPort(mic.getPort());
				mice.setInterface_(mic.getInterface_());
				mice.setDescription(mic.getDescription());
				interfaceCacheExtList.add(mice);
			}
		}
		target.setLoadFromCompiledMibs(true);
		target.setTimeout(MainConstants.SNMP_TIME_OUT);
		target.setRetries(MainConstants.SNMP_RETRY);
		
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BAND_LIMEN);
		if(null != param && !"".equals(param))
			bandLimen = Integer.parseInt(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_UNICAST_LIMEN);
		if(null != param && !"".equals(param))
			unicastLimen = Integer.parseInt(param.getValue());
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BROADCAST_LIMEN);
		if(null != param && !"".equals(param))
			broadcastLimen = Integer.parseInt(param.getValue());
		
	}

	// 初始化时间项为0，为POLL重新开始作准备：
	private void initPoll() {
//		dataSet.first();
//		do {
//			dataSet.setLong("上次时间", 0);
//			dataSet.setLong("当前时间", 0);
//		} while (dataSet.next());
	}

	//@ isDevice 如果是网络设备则从cache中读取，如果是服务器，总是直接读取
	public boolean ifInit(MonitorDevice md) {
		if (md == null) {
			return false;
		}
		this.monitorDevice = md;
		this.isServer = md.getMonitorDeviceType().getCode().equals(MainConstants.DEVICE_SERVER);

		String deviceIp = md.getIp();
		String c64 = md.getNote1();
		// 2010-08-20
		String counter64 = "";
		if (c64 != null && c64.length() > 0)
			counter64 = c64.toUpperCase();

		// 打开连接端口表：用来标记接口的不同类型
		TableDataSet linkPortDataSet = new TableDataSet();
		List<MonitorLinkport> list = null;
		list = monitorLinkportService.getLinkPortList();
		LinkPortUtilities.initLinkPortTable(linkPortDataSet);
		LinkPortUtilities.listToTable(list, linkPortDataSet);
		if (!linkPortDataSet.isOpen())
			linkPortDataSet.open();

		// 注意：先读取没有流量信息的设备接口表，不能接口、流量数据一起读，因为某些接口可能没有流量数据
		String ret = deviceSnmpQuery.getIFCache(monitorDevice, interfaceCacheList, true, isServer);
		if (!ret.equals("OK")) {
			JOptionPane.showMessageDialog(null, "读设备 " + deviceIp + " 接口表出错："+ ret);
			return false;
		}

		DataRow loc = new DataRow(linkPortDataSet, new String[] { "IP", "接口",
				"子网ID" });
		// 初始化表及控件：
//		for (int i = 0; i < dataSet.getRowCount(); i++) {
//			dataSet.goToRow(i);
			// 2010-08-20
			// 查找当前接口是否为互连端口
//			loc.setString("IP", deviceIp);
//			loc.setString("接口", dataSet.getString("接口"));
//		}
		
		if(isServer) {
//			jMenuPanel.setVisible(false);
//			jMenuSort.setVisible(false);
//			jMenuIntUser.setVisible(false);
//			jMenuCopy.setVisible(false);
//			jMenuAddToMonitor.setVisible(false);
//			jMenuMonitorTable.setVisible(false);
		}else {
//			jMenuSelectIf.setVisible(false);
		}
		return true;
	}

	// 取当前行可显示的文本值
	private String[] getTextResult(MonitorInterfaceCacheExt mice) {
		long[] now = new long[6];// 流量、单播、广播、错包、丢包的当前值
		long[] nowTD = new long[5];// 流量、单播、广播、错包、丢包的双向值
		String[] result = new String[6];// 流量、单播、广播、错包、丢包、包长
		bandOverLimen = false;
		singleOverLimen = false;
		multiOverLimen = false;
		errorOverLimen = false;
		discardOverLimen = false;

		long timeGap = mice.getTime();
		// 如果是第一组数据
		if (timeGap == 0) {
			result[0] = "-";
			result[1] = "-";
			result[2] = "-";
			result[3] = "-";
			result[4] = "-";
			result[5] = "-";
			return result;
		}
		// 计算峰值
		long data[] = mice.getData();
		long lastPeek[] = mice.getPeekData();
		long[] peek = new long[3];	//传给flex使用，记录峰值
		peek[0] = Tools.getLinkLoad(data[0], mice.getSpeed(), timeGap);			// 发送
		peek[1] = Tools.getLinkLoad(data[1], mice.getSpeed(), timeGap);	// 接收
		peek[2] = Tools.getLinkLoad(data[0] + data[1], mice.getSpeed(), timeGap);	// 双向
		for(int p = 0; p < 3; p++) {
			if(peek[p] > lastPeek[p])
				lastPeek[p] = peek[p];
		}
		mice.setPeekData(lastPeek);
		
		for(int k = 0;k<5;k++) {
			nowTD[k] = data[2 * k] + data[2 * k +1];
			if (direction.equals("双向")) {
				now[k] = data[2 * k] + data[2 * k +1];
			}
			if (direction.equals("发送")) {
				now[k] = data[2 * k];
			}
			if (direction.equals("接收")) {
				now[k] = data[2 * k +1];
			}
		}

		// 先计算平均包长，随后数据将折算成速率。
		if (now[1] + now[2] == 0) {
			now[5] = 0;
			result[5] = "-";
		} else {
			now[5] = now[0] / (now[1] + now[2]);
			result[5] = String.valueOf(now[0] / (now[1] + now[2]));
		}
		
		// 得到流量、单播包、广播包的秒速率值，当前时间为差值。错包和丢包为轮训期间值
		now[0] = now[0] * 1000 * 8 / timeGap;// 位速率
		nowTD[0] = nowTD[0] * 1000 * 8 / timeGap;

		now[1] = now[1] * 1000 / timeGap;// 单播包秒速率
		nowTD[1] = nowTD[1] * 1000 / timeGap;

		now[2] = now[2] * 1000 / timeGap;// 广播包秒速率
		nowTD[2] = nowTD[2] * 1000 / timeGap;

		// 得到显示的文本
		for (int i = 0; i < 5; i++) {
			if (nowTD[i] == 0)
				nowTD[i] = 1;// 如果双向值为0，则当前值必为0。为防止除0，可以不判断分母为0。
			if (mode.equals("数据"))
				result[i] = Tools.getBandText(now[i]);
			if (mode.equals("比例"))
				result[i] = (now[i] * 100 / nowTD[i]) + "%";
			if (mode.equals("数据+比例"))
				result[i] = Tools.getBandText(now[i]) + " - "
						+ (now[i] * 100 / nowTD[i]) + "%";
		}

		// 过载判断
		long ifspd = mice.getSpeed();
		// 端口使用率
		if (ifspd > 0 && now[0] >= ifspd * bandLimen / 100)
			bandOverLimen = true;
		// 单播包
		if (now[1] >= unicastLimen)
			singleOverLimen = true;
		// 广播包
		if (now[2] > broadcastLimen)
			multiOverLimen = true;
		// 错包
		if (now[3] > 0)
			errorOverLimen = true;
		// 丢包
		if (now[4] > 0)
			discardOverLimen = true;

		return result;
	}
	
	// ---------------------------扫描相关函数！
	public void pollFirst(Integer deviceId) {
		pollFirst(deviceId, this.interfaceCacheExtList);
	}
	
	public List<MonitorInterfaceCacheExt> pollFirst(Integer deviceId, List<MonitorInterfaceCacheExt> interfaceCacheExtList) {
		if(interfaceCacheExtList == null || interfaceCacheExtList.isEmpty())
			return null;
		initPoll();// 初始化流量采集数据，设置时间为0，表示重新开始
		String[] pollData = null;// 接收值、发送值、接口速率、接口状态

		// while (true) {
		inPoll = true;
		MonitorDevice monitorDevice = monitorDeviceService.get(deviceId);
		// 用循环方式，这样每次循环开始时可以强制设置行指针，如果指针被行操作移动，这里能恢复。
		for (int i = 0; i < interfaceCacheExtList.size(); i++) {
			MonitorInterfaceCacheExt mice = interfaceCacheExtList.get(i);
			// 读数据：
			pollData = dataPollTools
					.getFluxData(target, monitorDevice.getIp(), mice.getInterface_(),
							monitorDevice.getReadCommunity(), counter64);
			// 如果读到数据：
			if (pollData != null) {
				saveData(mice, pollData, counter64);
				// pollStatus[i][0] = 1;// 得到数据
				mice.setPollStatus(1);
				if (pollData[11].equals("1") || pollData[11].equals("up(1)"))
					// pollStatus[i][1] = 1;// 接口上线
					mice.setStatus(1);
				else {
					// lsj 接口关闭置-2，接口开up(1)置-1，接口testing(3)状态未实际测试，置-1
					if (pollData[12].equals("2") || pollData[12].equals("down(2)")) {
						// pollStatus[i][1] = -2;// 接口关闭
						mice.setStatus(-2);
					} else
						// pollStatus[i][1] = -1;// 接口下线
						mice.setStatus(-1);
				}
			} else {
				// pollStatus[i][0] = -1;// 没有得到数据
				mice.setPollStatus(-1);
			}
			
			// 2010-08-20
			getTextResult(mice);// 显示当前行
		}// for
		inPoll = false;
		return interfaceCacheExtList;
	}// run

	//
	// public void pollSecond(int i) {
	public List<MonitorInterfaceCacheExt> pollSecond(Integer deviceId, List<MonitorInterfaceCacheExt> interfaceCacheExtList) {
		if(interfaceCacheExtList == null || interfaceCacheExtList.isEmpty())
			return null;
		MonitorDevice monitorDevice = monitorDeviceService.get(deviceId);
		for (int i = 0; i < interfaceCacheExtList.size(); i++) {
			MonitorInterfaceCacheExt mice = interfaceCacheExtList.get(i);
			String[] pollData = null;// 接收值、发送值、接口速率、接口状态
			
			// 读数据：
			pollData = dataPollTools.getFluxData(target, monitorDevice.getIp(),
					mice.getInterface_(), monitorDevice.getReadCommunity(), counter64);
			// 如果读到数据：
			if (pollData != null) {
				saveData(mice, pollData, counter64);
				// pollStatus[i][0] = 1;// 得到数据
				mice.setPollStatus(1);
				if (pollData[11].equals("1") || pollData[11].equals("up(1)"))
					// pollStatus[i][1] = 1;// 接口上线
					mice.setStatus(1);
				else {
					// lsj 接口关闭置-2，接口开up(1)置-1，接口testing(3)状态未实际测试，置-1
					if (pollData[12].equals("2") || pollData[12].equals("down(2)")) {
						// pollStatus[i][1] = -2;// 接口关闭
						mice.setStatus(-2);
					} else
						// pollStatus[i][1] = -1;// 接口下线
						mice.setStatus(-1);
				}
			} else {
				// pollStatus[i][0] = -1;// 没有得到数据
				mice.setPollStatus(-1);
			}
			getTextResult(mice);// 显示当前行
		}
		return interfaceCacheExtList;
	}
	
	// 计算并刷新当前行的有关数据。表的数据列必须对应指定的数据顺序。
	/*
	 * 0-9列是上次值，必须为： "上次发送字节","上次接收字节","上次发送单播包","上次接收单播包",
	 * "上次发送广播包","上次接收广播包","上次发送错包","上次接收错包", "上次发送丢包","上次接收丢包", //
	 * 10-19列是当前与上次的差值，必须为： "当前发送字节","当前接收字节","当前发送单播包","当前接收单播包",
	 * "当前发送广播包","当前接收广播包","当前发送错包","当前接收错包", "当前发送丢包","当前接收丢包"
	 * 必须有：当前时间、上次时间、接口速率项
	 */
	// 将字符串数组中的数据保存到DATASET的当前行的指定位置上。
	// 第十一个数据为接口速率。第十二个数据为接口状态，用来显示，不需计算。
	public void saveData(MonitorInterfaceCacheExt mice, String[] data,
			String c64) {
		long[] now = new long[11];// 当前值，第11个值为接口速率，初值为0
		long gap, time, timeGap;
		// 如果有数据返回，则将不空并且格式正确的解析，其它使用初值0。不包括第十二个接口状态。
		// 中兴2826出现SNMP返回正确，但12个返回数中有三个为NULL的情况。
		for (int i = 0; i < 11; i++)
			if (data[i] != null)
				try {
					now[i] = Long.parseLong(data[i]);
				} catch (NumberFormatException e) {
				}
		// 取当前时间：
		time = System.currentTimeMillis();
		if (c64 == null)
			c64 = "";
		// 如果是第一组有效数据，将当前数据保存到表中后返回
		// 0-9列是上次值，必须为： "上次发送字节","上次接收字节","上次发送单播包","上次接收单播包",
		// "上次发送广播包","上次接收广播包","上次发送错包","上次接收错包", "上次发送丢包","上次接收丢包",
		if (mice.getLastTime() == 0) {
			mice.setLastData(now);
			mice.setLastTime(time);
			mice.setTime(0);
			if (c64.equals("C64"))
				mice.setSpeed(now[10] * 1000000);
			else
				mice.setSpeed(now[10]);
			mice.setStrSpeed(Tools.getBandText(mice.getSpeed()));
			return;
		}

		// 计算两次时间差
		timeGap = time - mice.getLastTime();

		// 计算两次差值、当前带宽并将数据保存到表中。共5组10个数据。
		for (int j = 0; j < 10; j++) {
			if (now[j] >= mice.getLastData()[j]) {
				gap = now[j] - mice.getLastData()[j];
				mice.getData()[j] = gap; // 刷新当前差值
			} else {
				gap = mice.getData()[j];// 32位计数器归零，使用上次差值做近似
			}
//			if(mice.getIp().equals("192.168.1.2") && mice.getInterface_().equals("5")) {
//				if(j == 0 )
//					System.out.println("上次发送字节  " + mice.getLastData()[j] + "   此次发送字节   " + now[j]);
//				if(j == 1 )
//					System.out.println("======上次接收字节  " + mice.getLastData()[j] + "   此次接收字节   " + now[j]
//					                            +   "   差值:   " + (now[j] - mice.getLastData()[j] + now[0] - mice.getLastData()[0]) 
//					                            +  "   差值:/时间   " + ((now[j] - mice.getLastData()[j] + now[0] - mice.getLastData()[0])*8/timeGap) );
//			}
			mice.getLastData()[j] = now[j];// 刷新上次值：
			mice.setLastData(mice.getLastData());
//			if(mice.getIp().equals("192.168.1.2") && mice.getInterface_().equals("5")) {
//				if(j == 0 ) {
//				System.out.println("******************刷新上次值");
//				
//					System.out.println("上次发送字节  " + mice.getLastData()[j] + "   此次发送字节   " + now[j]);
//				System.out.println("****************************");
//				}
//			}
		}

		mice.setLastTime(time);				// 刷新上次时间
		mice.setTime(timeGap);				// 刷新时间差值
		if (c64.equals("C64"))
			mice.setSpeed(now[10] * 1000000);
		else
			mice.setSpeed(now[10]);
		mice.setStrSpeed(Tools.getBandText(mice.getSpeed()));
	}
}
