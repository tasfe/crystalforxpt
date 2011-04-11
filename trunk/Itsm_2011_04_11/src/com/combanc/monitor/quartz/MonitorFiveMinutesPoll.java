
package com.combanc.monitor.quartz;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.CronTriggerBean;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.algorithm.tool.CpuPollTool;
import com.combanc.monitor.dao.MonitorCpuDataDAO;
import com.combanc.monitor.dao.MonitorTopologyNodeDAO;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorRealtimePortflow;
import com.combanc.monitor.pojo.MonitorRegularData;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojo.MonitorTopologyNode;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorInterfaceCacheService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorRealtimePortflowService;
import com.combanc.monitor.service.MonitorRegularDataService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.alert.alertParser.Alerter;

/**
 * <p>
 * Title:流量及CPU采集 
 * </P>
 * <p>
 * Description: 保存cpu数据及设备接口流量数据到表中
 * </P>
 * <p>
 * Copyright: Copyright (c) 2010
 * </P>
 * <p>
 * Company: Combanc
 * </P>
 * @author 
 * @version 
 */
public class MonitorFiveMinutesPoll extends BaseScan{

	private int devCount;
	/**待插入的端口流量数据**/
	private List<MonitorRegularData> regulardataList = new ArrayList<MonitorRegularData>();
	/**最近一次的流量数据**/
	private List<MonitorRegularData> regulardataLatestList=new ArrayList<MonitorRegularData>();
	/**待插入的TOP N 端口流量数据**/
	private List<MonitorRealtimePortflow> realtimePortflowList = new ArrayList<MonitorRealtimePortflow>();
	
	private Alerter alerter;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	
	private MonitorCpuDataDAO monitorCpuDataDAO;
	
	private MonitorTopologyNodeDAO monitorTopologyNodeDAO;

	private MonitorDeviceService monitorDeviceService;
	 
	private MonitorLinkportService monitorLinkportService;
	
	private MonitorRegularDataService monitorRegularDataService;
	
	private MonitorRealtimePortflowService monitorRealtimePortflowService;
	
	private MonitorInterfaceCacheService monitorInterfaceCacheService;
	
	private boolean hasTruncateRealtimePortflowTable=false;
	
	public MonitorFiveMinutesPoll(){
		 paramCode = SystemParamConstants.SYSTEM_FLUX_SCAN_GAP_TIME;
		 defaultCronExpression = "0 0/5 * * * ?";
		 cronTriggerName ="cronTriggerFiveMinutesDataTask";
	}
	
	
    public void scan(){
    	//是否启用
    	boolean isrun = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_FLUX_SWITCH);
    	if(isrun){
    		super.scan();
    	}
    	
    }
    
    /**初始化操作**/
	private void init(){
		hasTruncateRealtimePortflowTable=false;
		regulardataLatestList=monitorRegularDataService.findLatest();
	}
	
	
	public void startScan(){
		Timestamp datetime1 = new Timestamp(System.currentTimeMillis());
		System.out.println("数据任务开始 CpuData & RegularData————————————"+datetime1.toString());
		try {
			 //判断拓扑轮询调度的触发器状态，如果是4表示挂起（STATE_BLOCKED）就等待一会，直到状态不是4时才读取数据
			 int state=scheduler.getTriggerState("cronTriggerTopologyPollTask", Scheduler.DEFAULT_GROUP);
			 while(state==4){
				 try {
					Thread.sleep(1000);
					state=scheduler.getTriggerState("cronTriggerTopologyPollTask", Scheduler.DEFAULT_GROUP);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			this.init();
			this.saveCpuData();
			this.saveRegularData();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp datetime2 = new Timestamp(System.currentTimeMillis());
		Long l = (datetime2.getTime() - datetime1.getTime());
		long day=l/(24*60*60*1000);
	       long hour=(l/(60*60*1000)-day*24);
	       long min=((l/(60*1000))-day*24*60-hour*60);
	       long s=(l/1000-day*24*60*60-hour*60*60-min*60);
	    System.out.println("数据任务结束CpuData & RegularData————————————用时"+day+"天"+hour+"小时"+min+"分"+s+"秒");
	}
	
	/**
	 * 保存cpu数据
	 */
	private void saveCpuData() {
		
		List<MonitorDevice> deviceList = monitorDeviceService.findEffectiveList();
		CpuPollTool cpuPollTool = new CpuPollTool();
		SnmpTarget target = new SnmpTarget();
		 for(MonitorDevice device : deviceList){
			 //温湿度设备
			 
			 if(device.getMonitorDeviceType() .getCode().equals(MainConstants.DEVICE_AKCP)){
				 float result[] = cpuPollTool.getAkcpData(target, device.getIp(), device.getReadCommunity()); 
				 try {
						String ip = device.getIp();
						 
						Timestamp datetime = new Timestamp(System.currentTimeMillis());

						MonitorCpuData po1 = new MonitorCpuData();
						MonitorCpuData po2 = new MonitorCpuData();
						//温度
						po1.setIp(ip);
						po1.setCpu(result[0]);
						po1.setGatherTime(datetime);
						po1.setDataIndex(2);
						monitorCpuDataDAO.save(po1);
						//湿度
						po2.setIp(ip);
						po2.setCpu(result[1]);
						po2.setGatherTime(datetime);
						po2.setDataIndex(3);
						monitorCpuDataDAO.save(po2);
					} catch (Exception e) {
						e.printStackTrace();
					}
					continue;
			 }
			 
			String	ret = String.valueOf(cpuPollTool.getCpuLoad(device.getIp(), device.getReadCommunity(), null));
			//检查设备的CPU占用率是否超阈值
			alerter.mergeCpuHight(device.getId(), Float.parseFloat(ret));
			try {
				Timestamp datetime = new Timestamp(System.currentTimeMillis());
				MonitorCpuData po = new MonitorCpuData();
				po.setIp( device.getIp());
				po.setCpu(Float.parseFloat(ret));
				po.setGatherTime(datetime);
				po.setDataIndex(1);
				monitorCpuDataDAO.save(po);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		 }
		 System.out.println(" 轮询采样：设备CPU数据采样完毕。");

	}
	/**
	 * 采集流量数据并保存
	 */
	private void saveRegularData(){
		String type=monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PARAM_FLUX_TYPE).getValue();
		// 与流量报警计数遍历线程互斥
		synchronized (Alerter.flowHightList) {
			if("1".equals(type)) {
				List<MonitorLinkport> linkportList=monitorLinkportService.findUniqueLegitimateList();
				for(MonitorLinkport linkport:linkportList){
					String ip=linkport.getIp();
					MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
					if(null != device)
						this.getDeviceFluxData(linkport.getInterface_(), device);
					 
				}
			} else {
				devCount=0;
				List<MonitorDevice> deviceList=monitorDeviceService.findEffectiveList();
				if (deviceList == null || deviceList.isEmpty())
					return;
				for (MonitorDevice device : deviceList) {
					 if(Tools.ipFormatOK(device.getIp())){
						 devCount++;
						 this.getDeviceFluxData(device);
					 }
				}
			}
			
			if(realtimePortflowList.size()>0){
				if(!hasTruncateRealtimePortflowTable){
					monitorRealtimePortflowService.truncateTable();
					hasTruncateRealtimePortflowTable=true;
				}
				monitorRealtimePortflowService.batchInsert(realtimePortflowList);
				realtimePortflowList.clear();
			}
			
			if(regulardataList.size()>0){
				monitorRegularDataService.batchInsert(regulardataList);
				regulardataList.clear();
			}
			
		}
		
	}
	/**
	 * 读设备接口表，获得所有接口收发流量数据：
	 * @param dv
	 */
	private void getDeviceFluxData(MonitorDevice device) {
		String note1 = device.getNote1();
		String[] oids = new String[3];// 接收字节、发送字节、带宽
		if (note1 != null && note1.trim().equalsIgnoreCase("c64") 
				&& "OK".equals(Tools.support64(deviceSnmpQuery, device.getIp(), device.getReadCommunity()))) {
			oids[0] = ".1.3.6.1.2.1.31.1.1.1.6";// 接收字节，64位计数器
			oids[1] = ".1.3.6.1.2.1.31.1.1.1.10";// 发送字节，64位计数器
			oids[2] = ".1.3.6.1.2.1.31.1.1.1.15";// 带宽，64位计数器
			//oids[2] = ".1.3.6.1.2.1.2.2.1.5"; // 带宽，32位计数器
			pollDataAllInterface(device, SnmpTarget.VERSION2C, oids);// 设置版本为V2C
		} else {// 包括超时、表空两种情况。3COMSUPERSTACK2出现超时。
			oids[0] = ".1.3.6.1.2.1.2.2.1.10";// 接收字节，32位计数器
			oids[1] = ".1.3.6.1.2.1.2.2.1.16";// 发送字节，32位计数器
			oids[2] = ".1.3.6.1.2.1.2.2.1.5"; // 带宽，32位计数器
			pollDataAllInterface(device, SnmpTarget.VERSION1, oids);// 设置版本为V1
		}
	}
	/**
	 *  读当前设备所有接口流量数据：
	 * @param dv
	 * @param version
	 */
	private void pollDataAllInterface(MonitorDevice device, int version,String[] oids) {
		int readCount = 0;
		String result[] = new String[3];
		String tempStr, oidStr;
		String indexOid;
		int indexOidLen;
		SnmpTarget target = new SnmpTarget();
		target.setLoadFromCompiledMibs(true);
		target.setTimeout(1);
		target.setRetries(0);
		target.setTargetHost(device.getIp());
		target.setCommunity(device.getReadCommunity());
		target.setSnmpVersion(version);

		indexOid = oids[0];
		indexOidLen = indexOid.length();
		target.setObjectIDList(oids);

		while (true) {
			
			result = target.snmpGetNextList();
			// 超时：
			if (result == null)
				return;
			
			// 返回OID长度小于键长，无接口表、表空或读接口表完毕
			oidStr = target.getSnmpOID().toString();

			if (oidStr.length() < indexOidLen)
				return;
			
			// 返回OID前导部分与键长不等，无接口表、表空或读接口表完毕
			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid))
				return;

			readCount++;
			System.out.println(" 轮询采样：第 " + devCount + " 台设备 " + device.getIp() + " 接口表 "
					+ readCount + " 条记录。");
			 
			restoreData(result, oidStr, device,false);
			 
		}// while
	}
	/**
	 *  读设备接口表，获得指定接口收发流量数据：
	 * @param linkport
	 * @param device
	 */
	private void getDeviceFluxData(String ifIndex,MonitorDevice device){
		String ip=device.getIp();
		String note1=device.getNote1();
		 
		String[] oids = new String[3];// 接收字节、发送字节、带宽
		if (note1 != null && note1.trim().equalsIgnoreCase("c64") 
				&& "OK".equals(Tools.support64(deviceSnmpQuery, ip, device.getReadCommunity()))) {
			oids[0] = ".1.3.6.1.2.1.31.1.1.1.6" + "." + ifIndex;// 接收字节，64位计数器
			oids[1] = ".1.3.6.1.2.1.31.1.1.1.10" + "." + ifIndex;// 发送字节，64位计数器
			oids[2] = ".1.3.6.1.2.1.31.1.1.1.15" + "." + ifIndex;;// 带宽，64位计数器
			pollDataSpecifiedInterface( device, SnmpTarget.VERSION2C,oids); // 设置版本为V2C
		} else {
			oids[0] = ".1.3.6.1.2.1.2.2.1.10" + "." + ifIndex; // 接收字节，32位计数器
			oids[1] = ".1.3.6.1.2.1.2.2.1.16" + "." + ifIndex; // 发送字节，32位计数器
			oids[2] = ".1.3.6.1.2.1.2.2.1.5" + "." + ifIndex;  // 带宽，32位计数器
			pollDataSpecifiedInterface( device, SnmpTarget.VERSION1,oids); // 设置版本为V1
		}
		
	}
	
	/**
	 *  读指定接口流量数据：
	 * @param lp 
	 * @param device
	 * @param version
	 * @param oids
	 */
	private void pollDataSpecifiedInterface(MonitorDevice device, int version,String[] oids ) {
		String result[] = new String[3];
		String tempStr, oidStr;
		String indexOid;
		int indexOidLen;
		SnmpTarget target = new SnmpTarget();
		target.setLoadFromCompiledMibs(true);
		target.setTimeout(1);
		target.setRetries(0);
		target.setTargetHost(device.getIp());
		target.setCommunity(device.getReadCommunity());
		target.setSnmpVersion(version);

		indexOid = oids[0];
		indexOidLen = indexOid.length();
		target.setObjectIDList(oids);
		result = target.snmpGetList();
		// 超时：
		if (result == null)
			return;
		
		
		if(1==version)//64位得到的带宽需要乘以1000000
			result[2] = String.valueOf(Long.parseLong(result[2])*1000000);
		
		// 返回OID长度小于键长，无接口表、表空或读接口表完毕
		oidStr = target.getSnmpOID().toString();

		if (oidStr.length() < indexOidLen)
			return;
		// 返回OID前导部分与键长不等，无接口表、表空或读接口表完毕
		tempStr = oidStr.substring(0, indexOidLen);
		if (!tempStr.equals(indexOid))
			return;

		 
		 
		restoreData(result, oidStr,device,true);
		 
	}
	
	
	/**
	 *  保存到表
	 * @param result 数据结果
	 * @param oidStr OID
	 * @param device 设备对象
	 * @param isLinkPort 是否是互联端口
	 */
	private void restoreData(String[] result, String oidStr,MonitorDevice device,boolean isLinkPort) {
		long[] flux;
		// 如果某个接口没有流量数据，则result[0],result[1],result[2]为空，长度不为3。
		if (result.length != 3)
			return;
		String ip = device.getIp();
		String Interface = oidStr.substring(oidStr.lastIndexOf(".") + 1); // 接口
		 
		
		long TxByte = -1;
		long RxByte = -1;
		long band = -1;
		try {
			RxByte = Long.parseLong(result[0]);
			TxByte = Long.parseLong(result[1]);
			band   = Long.parseLong(result[2]);
		} catch (NumberFormatException e) {// 如果格式错误则数据无效
			TxByte = -1;
			RxByte = -1;
			band   = -1;
			//e.printStackTrace();
		}
		long BiTraffic = -1;
		long DeliveryTraffic = -1;
		long ReceiveTraffic = -1;
		Timestamp datetime = new Timestamp(System.currentTimeMillis());
		MonitorRegularData po = new MonitorRegularData();
		po.setIp(ip);
		po.setInterface_(Interface) ;
		po.setTxByte(TxByte);
		po.setRxByte(RxByte);
		po.setBiTraffic(BiTraffic);
		po.setDeliveryTraffic(DeliveryTraffic);
		po.setReceiveTraffic(ReceiveTraffic);
		po.setGatherTime(datetime);
		regulardataList.add(po);
		
		
		//如果isLinkPort==FALSE，说明查询的是设备所有的接口，这时需要确认接口是否是互联接口，如果isLinkPort==true，则不用进行判断，因为现在查询的是互联接口
		boolean confirmLinkport= isLinkPort == true ? true : monitorLinkportService.isLinkPort(ip,Interface);
		this.calculatedFlow(po, band, confirmLinkport);
		
		if(regulardataList.size()>99){
			monitorRegularDataService.batchInsert(regulardataList);
			regulardataList.clear();
		}
	}
	
	
	/**计算流量**/
	private void calculatedFlow(MonitorRegularData currentRegularData,long band,boolean isLinkPort){
		if(null!=this.regulardataLatestList&&this.regulardataLatestList.size()>0){
			MonitorRegularData lastRegularData=null;
			for(MonitorRegularData model:this.regulardataLatestList){
				if(model.getIp().equals(currentRegularData.getIp())
						&&model.getInterface_().equals(currentRegularData.getInterface_())){
					lastRegularData=model;
					break;
				}
			}
			if(null!=lastRegularData){
				long[] flux;
				flux = Tools.compuData(lastRegularData.getRxByte(), lastRegularData.getTxByte(),currentRegularData.getRxByte(),currentRegularData.getTxByte());
				//得到时间差，单位是毫秒，再除以1000得到秒
				long timeCalculate = (currentRegularData.getGatherTime().getTime()-lastRegularData.getGatherTime().getTime())/1000L;
				//计算流量
				if( timeCalculate != 0 ){
					MonitorRealtimePortflow realtimePortflow=new MonitorRealtimePortflow();
					realtimePortflow.setDeviceIp(currentRegularData.getIp());
					MonitorDevice device = monitorDeviceService.findDeviceByIp(currentRegularData.getIp());
					 
					if(null != device){
						 
						realtimePortflow.setDeviceName(device.getName());
						if(null!=device.getMonitorDeviceType() && !"".equals(device.getMonitorDeviceType().getName()))
							realtimePortflow.setDeviceType(device.getMonitorDeviceType().getName());
						else
							realtimePortflow.setDeviceType("");
						realtimePortflow.setInterface_(currentRegularData.getInterface_());
						MonitorInterfaceCache monitorInterfaceCache = monitorInterfaceCacheService.findByDeviceAndInterface(device.getId(), currentRegularData.getInterface_());
						if(null != monitorInterfaceCache && null != monitorInterfaceCache.getDescription())
							realtimePortflow.setInterfaceDescription(monitorInterfaceCache.getDescription());
						else
							realtimePortflow.setInterfaceDescription("");
						realtimePortflow.setTxByte(currentRegularData.getTxByte());
						realtimePortflow.setRxByte(currentRegularData.getRxByte());
						realtimePortflow.setIsLinkport(isLinkPort==true?1:0);
						realtimePortflow.setBilateralTraffic(flux[0]/timeCalculate);
						realtimePortflow.setDeliveryTraffic(flux[1]/timeCalculate);
						realtimePortflow.setReceiveTraffic(flux[2]/timeCalculate);
						realtimePortflow.setGatherTime(currentRegularData.getGatherTime());
						realtimePortflowList.add(realtimePortflow);
						
						// 若isLinkPort==TRUE，说明是互连端口
						if( band != 0 && band != -1 ){
							// 通过流量报警分析器的检查，决定是否加入计数器列表
							//int bandRate = (int)(flux[0]*8*100/timeCalculate/(band*1024*1024));
							float bandRate = (flux[0]*8*100/timeCalculate/(band));
							//System.out.println("ip:"+device.getIp()+">>>>>>interface:"+currentRegularData.getInterface_()+">>>带宽占有率>>>>>>>>>>>>>>>>>>"+flux[0]+">>"+timeCalculate+">>"+band+">>"+bandRate+"%");
							if(isLinkPort)
								alerter.mergeFlowHight(device.getId(), Integer.parseInt(currentRegularData.getInterface_()), (int)bandRate);
						}
					}
					
					
					if(realtimePortflowList.size()>99){
						if(!hasTruncateRealtimePortflowTable){
							monitorRealtimePortflowService.truncateTable();
							hasTruncateRealtimePortflowTable=true;
						}
						monitorRealtimePortflowService.batchInsert(realtimePortflowList);
						realtimePortflowList.clear();
					}
					
				}
			}
		}
		
	}
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	
	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}

	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}
	
	public MonitorCpuDataDAO getMonitorCpuDataDAO() {
		return monitorCpuDataDAO;
	}

	public void setMonitorCpuDataDAO(MonitorCpuDataDAO monitorCpuDataDAO) {
		this.monitorCpuDataDAO = monitorCpuDataDAO;
	}

	public MonitorTopologyNodeDAO getMonitorTopologyNodeDAO() {
		return monitorTopologyNodeDAO;
	}

	public void setMonitorTopologyNodeDAO(MonitorTopologyNodeDAO monitorTopologyNodeDAO) {
		this.monitorTopologyNodeDAO = monitorTopologyNodeDAO;
	}
	
	
	public List<MonitorRegularData> getRegulardataList() {
		return regulardataList;
	}

	public void setRegulardataList(List<MonitorRegularData> regulardataList) {
		this.regulardataList = regulardataList;
	}

	public MonitorRegularDataService getMonitorRegularDataService() {
		return monitorRegularDataService;
	}

	public void setMonitorRegularDataService(
			MonitorRegularDataService monitorRegularDataService) {
		this.monitorRegularDataService = monitorRegularDataService;
	}

	public MonitorRealtimePortflowService getMonitorRealtimePortflowService() {
		return monitorRealtimePortflowService;
	}

	public void setMonitorRealtimePortflowService(
			MonitorRealtimePortflowService monitorRealtimePortflowService) {
		this.monitorRealtimePortflowService = monitorRealtimePortflowService;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

	public MonitorInterfaceCacheService getMonitorInterfaceCacheService() {
		return monitorInterfaceCacheService;
	}

	public void setMonitorInterfaceCacheService(
			MonitorInterfaceCacheService monitorInterfaceCacheService) {
		this.monitorInterfaceCacheService = monitorInterfaceCacheService;
	}
	
	
	
}
