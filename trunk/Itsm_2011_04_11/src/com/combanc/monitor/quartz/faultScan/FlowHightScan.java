package com.combanc.monitor.quartz.faultScan;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.alert.alertParser.ParserTool;
import com.combanc.monitor.alert.alertParser.abnormalCounter.FlowHight;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorRealtimePortflow;
import com.combanc.monitor.quartz.BaseScan;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorRealtimePortflowService;
import com.combanc.monitor.service.MonitorSystemParamService;

/**
 * <p>
 * Title:带宽占用超阈值扫描
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class FlowHightScan extends BaseScan{

	private static final Log log = LogFactory.getLog(FlowHightScan.class);
	
	/**设备列表**/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	/**互联端口表**/
	private List<MonitorLinkport> linkportList = null;
	/**实时端口流量表**/
	private List<MonitorRealtimePortflow> realtimePortflowList = null;
	
	private List<MonitorRealtimePortflow> addRealtimePortflowList = new ArrayList<MonitorRealtimePortflow>();
	private List<MonitorRealtimePortflow> updateRealtimePortflowList = new ArrayList<MonitorRealtimePortflow>();
	
	private MonitorDeviceService monitorDeviceService;	
	private MonitorLinkportService monitorLinkportService;	
	private MonitorRealtimePortflowService monitorRealtimePortflowService;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	private Alerter alerter;
	
	public FlowHightScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
		cronTriggerName ="cronTriggerFlowHightScanTask";
	}

	/**初始化**/
	public void init(){
		deviceList = monitorDeviceService.getAll();
		linkportList = monitorLinkportService.getAll();
		realtimePortflowList = monitorRealtimePortflowService.getAll();
	}
	public void startScan(){
		System.out.println("端口流量阈值报警扫描开始");
		init();
		List<FlowHight> list = Alerter.flowHightList;
		if ( null == list  || list.isEmpty())
			return;
		synchronized (Alerter.flowHightList) {
			for (int i = 0; i < list.size(); i++) {
				FlowHight fh = list.get(i);

				// 获取设备对象
				MonitorDevice dv = ParserTool.getDeviceFromList(fh.getDeviceId(), deviceList);
				// 如果查不到，说明设备已被删除，把异常列表中的该记录也清除
				if ( null == dv ) {
					list.remove(i);
					i--;
					continue;
				}
				
				// 获取互连接口对象
				int interface_ = fh.getInterface_();
				// 如果查不到，说明互连接口已被删除，把异常列表中的该记录也清除
				MonitorLinkport lp = ParserTool.getLinkportFromList(dv.getIp(),interface_, linkportList);
				if( null == lp ) {
					list.remove(i);
					i--;
					continue;
				}
				
//				if (!MainFrame.subnetList.contains(dv.getSubnet().getSubnetName())) {
//					continue;
//				}
//				
				//MainFrame.statusBar.setText("检查带宽占用率：" + dv.getIp() + "...");
				
				// 获取带宽占用率
				int bandRate = getBandRate(dv, lp);
				String bandLimenValue = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_BAND_LIMEN).getValue();
				int bandLimen ;
				try {
					
					bandLimen = Integer.parseInt(bandLimenValue);
					// 维护带宽占用率超阈值计数器
					if ((bandRate != 0) && bandRate > bandLimen) {
						// 如果超过，增加计数器
						fh.IncreaseFaultCount();
						fh.setCurrFlowRate(bandRate);
					} else {
						// 如果没超过，计数器置0
						fh.clearCount();
						fh.setCurrFlowRate(bandRate);
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
				}
				
			}// for
			if( null != addRealtimePortflowList && addRealtimePortflowList.size()>0){
				monitorRealtimePortflowService.batchInsert(addRealtimePortflowList);
			}
			if( null != updateRealtimePortflowList && updateRealtimePortflowList.size()>0){
				monitorRealtimePortflowService.batchUpdate(updateRealtimePortflowList);
			}
			alerter.checkFlowHight();
		}
	}
	
	private int getBandRate( MonitorDevice dv, MonitorLinkport lp ){
		String[] oids = new String[3];// 接收字节、发送字节、带宽
		String commStr = dv.getReadCommunity();
		String result[];
		String note1=dv.getNote1();
		String ifIndex = lp.getInterface_();
		String ret = Tools.support64(deviceSnmpQuery, lp.getIp(), commStr);
		if (note1 != null && note1.trim().equalsIgnoreCase("c64") && ret.equals("OK")) {
			oids[0] = ".1.3.6.1.2.1.31.1.1.1.6" + "." + ifIndex;// 接收字节，64位计数器
			oids[1] = ".1.3.6.1.2.1.31.1.1.1.10" + "." + ifIndex;// 发送字节，64位计数器
			oids[2] = ".1.3.6.1.2.1.31.1.1.1.15" + "." + ifIndex;;// 带宽，64位计数器
			result = pollData(lp, commStr, oids, SnmpTarget.VERSION2C); // 设置版本为V2C
			result[2] = String.valueOf(Long.parseLong(result[2])*1000000);
		} else { // 包括超时、表空两种情况。3COMSUPERSTACK2出现超时。
			oids[0] = ".1.3.6.1.2.1.2.2.1.10" + "." + ifIndex; // 接收字节，32位计数器
			oids[1] = ".1.3.6.1.2.1.2.2.1.16" + "." + ifIndex; // 发送字节，32位计数器
			oids[2] = ".1.3.6.1.2.1.2.2.1.5" + "." + ifIndex;  // 带宽，32位计数器
			result = pollData(lp, commStr, oids, SnmpTarget.VERSION1); // 设置版本为V1
		}
		
		if( result != null ){
			try{
				int bandRate = fluxBandRate(result, lp);
				return bandRate;
			} catch(SQLException e) {
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	// 读指定接口的流量数据：
	// 返回：接收字节、发送字节、带宽
	private String[] pollData(MonitorLinkport lp, String commStr, String oids[], int version) {
		SnmpTarget target = new SnmpTarget();
		
		String result[] = new String[3];
		String tempStr, oidStr;
		String indexOid;
		int indexOidLen;

		target.setLoadFromCompiledMibs(true);
		target.setTimeout(1);
		target.setRetries(0);
		target.setTargetHost(lp.getIp());
		target.setCommunity(commStr);
		target.setSnmpVersion(version);

		indexOid = oids[0];
		indexOidLen = indexOid.length();
		target.setObjectIDList(oids);
		result = target.snmpGetList();
		// 超时：
		if (result == null)
			return null;
		// 返回OID长度小于键长，无接口表、表空或读接口表完毕
		oidStr = target.getSnmpOID().toString();

		if (oidStr.length() < indexOidLen)
			return null;
		// 返回OID前导部分与键长不等，无接口表、表空或读接口表完毕
		tempStr = oidStr.substring(0, indexOidLen);
		if (!tempStr.equals(indexOid))
			return null;

		return result;
	}
	
	private int fluxBandRate(String[] result, MonitorLinkport lp)throws SQLException {
		int ret = 0;
		long[] flux;
		// 如果某个接口没有流量数据，则result[0],result[1],result[2]为空，长度不为3。
		if (result.length != 3)
			return 0;
		String ip = lp.getIp();
		String Interface = lp.getInterface_(); // 接口
		Integer interfaceId = lp.getId();
		
		long TxByte = -1;
		long RxByte = -1;
		long band = -1;
		try {
			TxByte = Long.parseLong(result[1]);
			RxByte = Long.parseLong(result[0]);
			band   = Long.parseLong(result[2]);
		} catch (NumberFormatException e) {// 如果格式错误则数据无效
			TxByte = -1;
			RxByte = -1;
			band   = -1;
			e.printStackTrace();
		}
		
		Timestamp datetime = new Timestamp(System.currentTimeMillis());
		
		if( null != realtimePortflowList ){
			Query q = new Query();
			String sql = "SELECT * FROM com.combanc.monitor.pojo.MonitorRealtimePortflow where deviceIp =:ip and interface_=:interface ";
			try {
				q.parse(sql);
			} catch (QueryParseException e) {
				e.printStackTrace();
			}
			q.setVariable("ip", ip);
			q.setVariable("interface", Interface);
			QueryResults qr = new QueryResults();
			try {
				qr = q.execute(realtimePortflowList);
			} catch (QueryExecutionException e) {
				e.printStackTrace();
			}
			List list = new ArrayList();
			list = qr.getResults();
			if ( list!=null && list.size()>0 ) {
				//修改原来数据
				MonitorRealtimePortflow realtimePortflow = (MonitorRealtimePortflow)list.get(0);
				flux = Tools.compuData(realtimePortflow.getRxByte(), realtimePortflow.getTxByte(), RxByte, TxByte);
				realtimePortflow.setTxByte(TxByte);
				realtimePortflow.setRxByte(RxByte);
				//得到时间差，单位是毫秒，再除以1000得到秒
				long timeCalculate = (datetime.getTime()-realtimePortflow.getGatherTime().getTime())/1000L;
				//计算流量
				if( timeCalculate != 0 ){
					realtimePortflow.setBilateralTraffic(flux[0]/timeCalculate);
					realtimePortflow.setDeliveryTraffic(flux[1]/timeCalculate);
					realtimePortflow.setReceiveTraffic(flux[2]/timeCalculate);
					realtimePortflow.setGatherTime(datetime);
					if( band != 0 && band != -1 && interfaceId != null ){
						ret = (int)(flux[0]*8*100/timeCalculate/band);
					} 
				}
				updateRealtimePortflowList.add(realtimePortflow);
			} else {
				//直接添加到临时list
				MonitorRealtimePortflow realtimePortflow = new MonitorRealtimePortflow();
				realtimePortflow.setDeviceIp(ip);
				realtimePortflow.setInterface_(Interface);
				boolean confirmLinkport= monitorLinkportService.isLinkPort(ip,Interface);
				realtimePortflow.setIsLinkport(confirmLinkport == true? 1:0);
				realtimePortflow.setTxByte(TxByte);
				realtimePortflow.setRxByte(RxByte);
				realtimePortflow.setBilateralTraffic(-1L);
				realtimePortflow.setDeliveryTraffic(-1L);
				realtimePortflow.setReceiveTraffic(-1L);
				realtimePortflow.setGatherTime(datetime);
				addRealtimePortflowList.add(realtimePortflow);
			}
		}
		return ret;
	}
	
	
	
	
	public List<MonitorDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<MonitorDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public List<MonitorLinkport> getLinkportList() {
		return linkportList;
	}

	public void setLinkportList(List<MonitorLinkport> linkportList) {
		this.linkportList = linkportList;
	}

	public List<MonitorRealtimePortflow> getRealtimePortflowList() {
		return realtimePortflowList;
	}

	public void setRealtimePortflowList(
			List<MonitorRealtimePortflow> realtimePortflowList) {
		this.realtimePortflowList = realtimePortflowList;
	}

	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}

	public MonitorRealtimePortflowService getMonitorRealtimePortflowService() {
		return monitorRealtimePortflowService;
	}

	public void setMonitorRealtimePortflowService(
			MonitorRealtimePortflowService monitorRealtimePortflowService) {
		this.monitorRealtimePortflowService = monitorRealtimePortflowService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	
	public Alerter getAlerter() {
		return alerter;
	}
	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}
	
	
	
	
}
