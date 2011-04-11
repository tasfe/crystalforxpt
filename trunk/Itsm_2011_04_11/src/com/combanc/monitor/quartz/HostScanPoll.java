
package com.combanc.monitor.quartz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.HostSnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;
import com.combanc.monitor.pojo.MonitorKeyInterface;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.pojo.MonitorPingDestType;
import com.combanc.monitor.pojo.MonitorPingRegion;
import com.combanc.monitor.pojo.MonitorPingResult;
import com.combanc.monitor.pojo.MonitorRealtimeCpu;
import com.combanc.monitor.pojo.MonitorRealtimeDelay;
import com.combanc.monitor.pojo.MonitorRealtimeFault;
import com.combanc.monitor.pojo.MonitorRealtimeUse;
import com.combanc.monitor.pojo.MonitorServerProcess;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojo.MonitorTcpPort;
import com.combanc.monitor.pojo.MonitorUrlResponse;
import com.combanc.monitor.service.MonitorCpuDataService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorKeyInterfaceService;
import com.combanc.monitor.service.MonitorPingDestTypeService;
import com.combanc.monitor.service.MonitorRealtimeUseService;
import com.combanc.monitor.service.MonitorRealtimeCpuService;
import com.combanc.monitor.service.MonitorRealtimeDelayService;
import com.combanc.monitor.service.MonitorRealtimeFaultService;
import com.combanc.monitor.service.MonitorRealtimePortflowService;
import com.combanc.monitor.service.MonitorServerProcessService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.service.MonitorTcpPortService;
import com.combanc.monitor.service.MonitorUrlResponseService;
import com.combanc.monitor.util.PingTools;
import com.combanc.monitor.vo.urlMonitor.model.MonitorResult;
import com.combanc.monitor.alert.alertParser.Alerter;

/**
 * <p>
 * Title:故障检查
 * </p>
 * <p>
 * Description:产生TOP N 数据
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

public class HostScanPoll extends BaseScan{
	
	public int pingNum = SystemParamConstants.SYSTEM_PING_NUM_DEFUALT_VALUE;					// 每次ping的次数
	public int pingSize = SystemParamConstants.SYSTEM_PING_SIZE_DEFUALT_VALUE;				// ping包长度
	public int pingTimeout = SystemParamConstants.SYSTEM_PING_TIMEOUT_DEFUALT_VALUE;
	
	private static final Log log = LogFactory.getLog(HostScanPoll.class);
	
	/**设备列表**/
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	/**故障表**/
	private List<MonitorRealtimeFault> faultList=new ArrayList<MonitorRealtimeFault>();
	/**延时表**/
	private List<MonitorRealtimeDelay> delayList=new ArrayList<MonitorRealtimeDelay>();
	/**可用表**/
	private List<MonitorRealtimeUse> useList=new ArrayList<MonitorRealtimeUse>();
	/**CPU数据**/
	private List<MonitorRealtimeCpu> cpuList = new ArrayList<MonitorRealtimeCpu>();
	/**服务器关键进程表**/
	private List<MonitorServerProcess> serverProcessList = new ArrayList<MonitorServerProcess>();
	
	/** 内存中存储的procNameList，缓存**/
	List<String> procNameList = new ArrayList<String>();
	/** 内存中存储的procNameList对应的服务器的id**/
	int currentDevice = -1;
	
	private MonitorDeviceService monitorDeviceService;
	
	private MonitorTcpPortService monitorTcpPortService;
	
	private MonitorServerProcessService monitorServerProcessService;
	
	private MonitorCpuDataService monitorCpuDataService;
	
	private MonitorRealtimeUseService monitorRealtimeUseService;
	
	private MonitorRealtimeDelayService monitorRealtimeDelayService;
	
	private MonitorRealtimeFaultService monitorRealtimeFaultService;
	
	private MonitorRealtimePortflowService monitorRealtimePortflowService;
	
	private MonitorRealtimeCpuService monitorRealtimeCpuService;
	
	private MonitorKeyInterfaceService monitorKeyInterfaceService;
	
	private MonitorUrlResponseService monitorUrlResponseService;
	
	private MonitorPingDestTypeService monitorPingDestTypeService;
	
	private Alerter alerter;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	
	private HostSnmpQuery hostSnmpQuery;
	
	public HostScanPoll(){
		 paramCode = SystemParamConstants.SYSTEM_CHECK_GAP;
		 defaultCronExpression = "0 0/5 * * * ?";
		 cronTriggerName ="cronTriggerHostScanPollTask";
	}
	
	/**初始化**/
	public void init(){
		deviceList=monitorDeviceService.findEffectiveList();
		serverProcessList = monitorServerProcessService.findByProcStart(true);
		MonitorSystemParam systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_NUM);
		if( null != systemParam && !"".equals(systemParam.getValue()))
			pingNum = Integer.parseInt(systemParam.getValue());
		systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_SIZE);
		if( null != systemParam && !"".equals(systemParam.getValue()))
			pingSize = Integer.parseInt(systemParam.getValue());
		systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_TIMEOUT);
		if( null != systemParam && !"".equals(systemParam.getValue()))
			pingTimeout = Integer.parseInt(systemParam.getValue());
	}
	
	/**需要定时执行的方法**/
	public void startScan(){
		System.out.println("主机扫描任务开始————————————");
		init();
 		scanDevice();				//扫描所有设备、主机
 		scanService();				//扫描服务器的服务
 		scanTemp();					//扫描温度传感器
 		scanCpu();					//扫描CPU占用率
 		scanKeyInterface();// 扫描关键端口
 		scanProcess();				//扫描服务器的关键进程
 		setUseRate();				//修改可用性表
		scanUrl();
 		saveData();
 		System.out.println("主机扫描任务结束————————————");
	}
	/**设备/主机SNMP无反应后，检查是否能ping通，无反应也加入报警**/
	public void pingDev(MonitorDevice d){
		// 执行ping操作，返回结果
		MonitorPingResult pr = PingTools.ping(d.getIp(), pingNum, pingSize, pingTimeout);
		if(pr.getSuccessCount() == 0) {	//ping不通，加入报警
			MonitorPingDest dest = new MonitorPingDest();
			dest.setDestIpUrl(d.getIp());
			dest.setHostName(d.getName());
			if(null != d.getMonitorDeviceType()){
				Integer typeId = MainConstants.PING_DEST_OTHERS;
				
				if(MainConstants.isServer(d.getMonitorDeviceType().getCode())){
					typeId = MainConstants.PING_DEST_SERVER;
					
				}else if(MainConstants.isDevice(d.getMonitorDeviceType().getCode())){
					typeId = MainConstants.PING_DEST_DEVICE;
				}
				MonitorPingDestType monitorPingDestType = monitorPingDestTypeService.get(typeId);
				if(null != monitorPingDestType)
					dest.setMonitorPingDestType(monitorPingDestType);
			}
			//设置ping的报警策略=设备的报警策略
			MonitorPingRegion region =  new MonitorPingRegion();
			region.setMonitorAlertPolicy(d.getMonitorAlertPolicy());
			dest.setMonitorPingRegion(region);
			synchronized (Alerter.pingDeviceFaultList) {
				alerter.mergePingDeviceFault(dest);
			}
		}
	}
	/**扫描所有设备、主机**/
	private void scanDevice() {
		if (deviceList == null || deviceList.isEmpty())
			return;
		String type = "设备";
		long timeUsed;//用时
		String ret;
		double inc = 0;// 有效增量
		synchronized (Alerter.deviceFaultList) {
		for (MonitorDevice d : deviceList) {
			if(!isToScan(d))
				continue;
			System.out.println("检查" + type + " ：" + d.getIp() + "...");
			type = d.getMonitorDeviceType() != null ? d.getMonitorDeviceType().getName() : "";
			timeUsed = System.currentTimeMillis();
			ret = deviceSnmpQuery.getVendorOID(d);
			if (ret == null || ret.equals("超时")) {
				// 无反应者加入故障表
				int alertType = 1;
				alerter.mergeDeviceFault(d.getId());
				alertType = null==d.getMonitorDeviceType()?1:d.getMonitorDeviceType().getCode();
				alert(alertType, d);
				inc = 0;
				pingDev(d);
			} else {
				// 有反应者加入延时表
				timeUsed = System.currentTimeMillis() - timeUsed;
				MonitorRealtimeDelay delay = new MonitorRealtimeDelay();
				delay.setData(timeUsed);
				delay.setIp(d.getIp());
				delay.setName(d.getName());
				delay.setType(d.getMonitorDeviceType().getName());
				delayList.add(delay);
				inc = 1;

			}
			
			// 刷新可用表
			MonitorRealtimeUse use = new MonitorRealtimeUse();
			use.setIp(d.getIp());
			use.setType(null==d.getMonitorDeviceType()?"":d.getMonitorDeviceType().getName());
			 
			if (!monitorRealtimeUseService.locate(useList, use, inc)) {
				use.setTotalNum(1f);
				use.setEffectiveNum((float) inc);
				use.setIp(d.getIp());
				use.setName(d.getName());
				use.setType(null==d.getMonitorDeviceType()?"":d.getMonitorDeviceType().getName());
				use.setExist(true);
				useList.add(use);
			}
		}
		}
		System.out.println("设备检查完毕。");
	}
	
	/**扫描服务器的服务**/
	private void scanService(){
		if (deviceList == null || deviceList.isEmpty())
			return;
		// 按IP@TCP端口号、名称:TCP端口名的格式生成被检查服务表。
		double inc = 0;// 有效增量
		long timeUsed;
		String ret;
		synchronized (Alerter.serviceFaultList) {
			for (MonitorDevice d : deviceList) {
				// 非服务器类型跳过
				if (d.getMonitorDeviceType() != null
						&& !d.getMonitorDeviceType().getCode().equals(
								MainConstants.DEVICE_SERVER))
					continue;
				if (!isToScan(d))
					continue;
			
				for (int i = 1; i <= 4; i++) {
					String temp = "";
					if (i == 1)
						temp = d.getNote1();
					if (i == 2)
						temp = d.getNote2();
					if (i == 3)
						temp = d.getNote3();
					if (i == 4)
						temp = d.getNote4();
					
					MonitorTcpPort tcpPort = monitorTcpPortService.findByPortName(temp);
					if(null!=tcpPort){
						timeUsed = System.currentTimeMillis();
						System.out.println("检查服务器："+ d.getIp() + "的服务 "+ tcpPort.getPortNumber() + "...");
						String servieIp = d.getIp() + "@"+ tcpPort.getPortNumber();
						String serviceName = d.getName() + "@"+ tcpPort.getPortName();
						ret = Tools.tcpPing(servieIp);
						if (ret == null || ret.equals("超时")) {
							// 无反应者报警
							int alertType = -2;
							int port = 80;
							try {
								port = Integer.valueOf(tcpPort.getPortNumber());
							} catch (NumberFormatException nfe) {
								nfe.printStackTrace();
								port = 80;
							}
							alerter.mergeServiceFault(d.getId(), port,tcpPort.getPortName());
							alert(servieIp, serviceName);// 报警
							inc = 0;
						} else {
							// 有反应者加入延时表
							timeUsed = System.currentTimeMillis() - timeUsed;
							MonitorRealtimeDelay delay = new MonitorRealtimeDelay();
							delay.setData(timeUsed);
							delay.setIp(servieIp);
							delay.setName(serviceName);
							delay.setType(d.getMonitorDeviceType().getName());
							delayList.add(delay);
							inc = 1;
						}
						// 刷新可用表
						// added by dongyanping
						MonitorRealtimeUse use = new MonitorRealtimeUse();
						use.setIp(servieIp);
						use.setType(null==d.getMonitorDeviceType()?"":d.getMonitorDeviceType().getName());
						if (!monitorRealtimeUseService.locate(useList, use, inc)) {
							use.setTotalNum(1f);
							use.setEffectiveNum((float) inc);
							use.setIp(servieIp);
							use.setName(serviceName);
							use.setType(null==d.getMonitorDeviceType()?"":d.getMonitorDeviceType().getName());
							use.setExist(true);
							useList.add(use);
						}
						
						
					}
		
				}
			}// end for
		}
		System.out.println("服务检查完毕。");
	}
	
	
	/**扫描温度传感器**/
	private void scanTemp() {
		
		if (deviceList == null || deviceList.isEmpty())
			return;
		String ret;
		// 温度使用变量
		long temp = 0;
		synchronized (Alerter.tempHightList) {
			for (MonitorDevice d : deviceList) {
				if (d.getMonitorDeviceType() != null
						&& !d.getMonitorDeviceType().getCode().equals(
								MainConstants.DEVICE_SERVER))
					continue;
				if (!isToScan(d))
					continue;
				System.out.println("检查温度：" + d.getIp() + "...");
				ret = deviceSnmpQuery.getVendorOID(d);
				if (ret != null && ret.equals("3854")) {
					temp = deviceSnmpQuery.getAkcpTemp(d);
					if ((temp != -1) && temp > Long.parseLong(monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_CPU_LIMEN).getValue())) {
						// 合并入报警计数器列表
						alerter.mergeTempHight(d.getId(), temp);
						// 故障一览表中报前30个，报警表中将不报。
						alert(-1,d); // 报警 -1："温度监控"
					}
				}
			}
		}
		System.out.println("温度检查完毕！");
	}
	
	/**扫描CPU占用率**/
	private void scanCpu(){
		/**
		if (deviceList == null || deviceList.isEmpty())
			return;
		for (MonitorDevice d : deviceList) {
			String cpuLoadOid, res;
			cpuLoadOid=deviceSnmpQuery.getVendorCpuOid(d);
			res=deviceSnmpQuery.getCpuRunTime(d, cpuLoadOid);
			alerter.mergeCpuHight(d.getId(), res);	// 使用Cpu报警分析器分析
			
			try {
				Timestamp datetime = new Timestamp(System.currentTimeMillis());
				MonitorRealtimeCpu po = new MonitorRealtimeCpu();
				po.setIp(d.getIp());
				po.setData(Float.parseFloat(res));
				po.setGatherTime(datetime);
				cpuList.add(po);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		**/
		//从monitor_cpu_data表中直接取最近的数据，避免重复扫描
		List<MonitorCpuData> list=monitorCpuDataService.findLatest();
		synchronized (Alerter.cpuHightList) {
			for(MonitorCpuData cpuData:list){
				MonitorRealtimeCpu po = new MonitorRealtimeCpu();
				po.setIp(cpuData.getIp());
				po.setData(cpuData.getCpu());
				po.setGatherTime(cpuData.getGatherTime());
				List<MonitorDevice> deviceList=monitorDeviceService.findByIp(po.getIp());
				if(deviceList.size()>0){
					MonitorDevice device=deviceList.get(0);
					
					po.setName(device.getName());
					po.setType(null==device.getMonitorDeviceType()?"":device.getMonitorDeviceType().getName());
					cpuList.add(po);
					alerter.mergeCpuHight(device.getId(), po.getData());	// 使用Cpu报警分析器分析
				}
					
			}
		}
		
		 
		System.out.println("CPU占用率检查完毕。");
		
		
	}
	
	/** 关键接口检查 **/
	private void scanKeyInterface() {
		List<MonitorKeyInterface> keyInterfaceList = null;
		try {
			keyInterfaceList = monitorKeyInterfaceService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ( null == keyInterfaceList || keyInterfaceList.isEmpty())
			return;
		synchronized (Alerter.keyInterfaceFaultList) {
			for (MonitorKeyInterface ki : keyInterfaceList) {
				if(null == ki.getMonitorDevice()  || null == ki.getInterfaceNum()  || "".equals(ki.getInterfaceNum()))
					continue;
				if (!ki.getIsMonitor()) // 暂停监控
					continue;
				String ret = deviceSnmpQuery.getVendorOID(ki.getMonitorDevice());
				if( null == ret || "超时".equals(ret))
					continue;
				int interfaceStatus = monitorDeviceService.getInterfaceStatus(ki.getMonitorDevice(), ki.getInterfaceNum());
				if( -1 == interfaceStatus ) {	// 获取失败
					continue;
				} else if (interfaceStatus != MainConstants.INTERFACE_STATUS_ONLINE) {
					alerter.mergeKeyInterfaceFault(ki.getMonitorDevice().getId(), 
							ki.getInterfaceNum(), ki.getInterfaceDesc(), 
							ki.getNote(), interfaceStatus);
				}
			}// for
		}// synchronized end 
		System.out.println("关键接口检查完毕。");
	}
	/**扫描服务器的关键进程t**/
	private void scanProcess(){
		if (serverProcessList == null || serverProcessList.isEmpty())
			return;
		String ret;
		synchronized (Alerter.processFaultList) {
			for (MonitorServerProcess sp : serverProcessList) {
				
				if(null==sp.getMonitorDevice() || null==sp.getName()
						|| "".equals(sp.getName()))
					continue;
				ret = deviceSnmpQuery.getVendorOID(sp.getMonitorDevice());
				if(ret == null || "超时".equals(ret))
					continue;
				if(!isProcessExist(sp.getMonitorDevice(), sp.getName())) {
					alerter.mergeProcessFault(sp.getMonitorDevice().getId(), sp.getName());
					System.out.println(sp.getMonitorDevice().getIp() + "  " + sp.getName());
				}
			}// for
		}// synchronized
		
		System.out.println("关键进程检查完毕。");
	}
	
	/**修改可用性表,计算可用比**/
	private void setUseRate(){
		if (this.useList.isEmpty())
			return;
		Iterator itr = useList.iterator();
		while (itr.hasNext()) {
			MonitorRealtimeUse newUse = (MonitorRealtimeUse) itr.next();
			if (newUse.getExist() == false)
				itr.remove();
		}// while1
		if (this.useList.isEmpty())
			return;
		double use;
		itr = useList.iterator();
		while (itr.hasNext()) {
			MonitorRealtimeUse newUse = (MonitorRealtimeUse) itr.next();
			use = newUse.getEffectiveNum() * 100 / newUse.getTotalNum();
			newUse.setRate((float) use);
		}// while1
	}
	
	/**URL监测**/
	private void scanUrl(){
		List<MonitorUrlResponse> urlList = null;
		try {
			urlList = monitorUrlResponseService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ( null == urlList || urlList.isEmpty()) {
			return;
		} else {
			List<MonitorDevice> serverList = monitorDeviceService.findByDeviceType(MainConstants.DEVICE_SERVER);
			// 搜集存在的服务器的IP，汇成IP列表
			List<String> ipList = new ArrayList<String>();
			for (MonitorDevice dv : serverList) {
				if (!ipList.contains(dv.getIp())) {
					ipList.add(dv.getIp());
				}
			}
			
			// 检查URL列表，所属服务器不存在的，从URL列表中删除
			for (int i=0; i<urlList.size(); i++) {
				MonitorUrlResponse hur = urlList.get(i);
				if (!ipList.contains(hur.getServerIp())) {
					urlList.remove(i);
					i--;
				}
			}
			
			if ( null == urlList || urlList.isEmpty()) {
				return;
			}
		}
		
		synchronized (Alerter.urlFaultList) {
			for (MonitorUrlResponse hur : urlList) {
				if (hur.getScan() != 1) // 暂停监控
					continue;
				MonitorResult result =  monitorUrlResponseService.testUrl(hur, false);
				if (result != null) {
					hur.setFlushTime(new Timestamp(System.currentTimeMillis()));
					hur.setStateCode(result.getState());
				} else {
					hur.setFlushTime(new Timestamp(System.currentTimeMillis()));
					hur.setStateCode(-2);
				}
				monitorUrlResponseService.update(hur);
				if (hur.getStateCode() <= 0) {
					alerter.mergeUrlFault(hur.getId(), hur.getServerIp(),
							result==null ? "执行监测时发生错误！" : result.getResultDesc());
				}
			}// for
		}// synchronized (MainFrame.alerter.keyInterfaceFaultList) {
		System.out.print("URL监测完毕。");
	}
	
	/**保存数据**/
	private void saveData(){
		try {
			//保存前清空实时表
			monitorRealtimeUseService.truncateTable();
			monitorRealtimeUseService.batchInsert(useList);
			useList.clear();
			monitorRealtimeDelayService.truncateTable();
			monitorRealtimeDelayService.batchInsert(delayList);
			delayList.clear();
			monitorRealtimeFaultService.truncateTable();
			monitorRealtimeFaultService.batchInsert(faultList);
			faultList.clear();
			monitorRealtimeCpuService.truncateTable();
			monitorRealtimeCpuService.batchInsert(cpuList);
			cpuList.clear();
			
		} catch (Exception e) {
			log.error("HostScan.run save TOP-N  Exception ",e);
		}
		
	}
	/**
	 *  检查进程是否存在，只有当服务器通，且能读到进程表，但进程表中不存在此进程时返回false
	 * @param dev 设备对象
	 * @param procName 进程名称
	 * @return
	 */
	private boolean isProcessExist(MonitorDevice dev, String procName) {
		String ret = "";
		// 当第一次查询是否存在时，procNameList为空，进程初始化
		// 之后因为监测进程是按照服务器的id排序
		// 因此直到现有dev的id和内存中保存的currentDevice不同时才再次读取
		if(procNameList == null || procNameList.isEmpty() || !dev.getId().equals(currentDevice)) {
			String str = "读取 " + dev.getIp() + "的进程表...";
			System.out.println(str);
			/**需要后面hostSnmpQuery移植后更正**/
			ret = hostSnmpQuery.getProcessName( dev, procNameList);
			
			// 读取“超时”、“表空”、“不支持该特性”时返回true
			if(!"OK".equals(ret))
				return true;
			else {
				currentDevice = dev.getId();
			}
		}
		// 读取结果的procNameList中包含进程名称时返回true
		for(String s : procNameList) {
			if(procName.equals(s))
				return true;
		}
		return false;
	}
	
	/**检查设备是否需要扫描**/
	public boolean isToScan(MonitorDevice d){
		MonitorDeviceType deviceType = d.getMonitorDeviceType(); 
		//如果设备类型是虚拟设备，或者设备类型是交换，且备注1=="忽略检查" 则忽略检查
		if( null != deviceType){
			if(deviceType.getCode()==MainConstants.DEVICE_VIRTUAL || (deviceType.getCode() == MainConstants.DEVICE_SWITCH
					&& "忽略检查".equals(d.getNote1())))
				return false;
		}
		 
		return true;
	}
	
	/**原来在下面的alert方法中，因为将参数减少了，改成MonitorDevice，所以将type == -2的情况独立出来**/
	private void alert(String servieIp, String serviceName){
		MonitorRealtimeFault fault = new MonitorRealtimeFault();
		fault.setData("TCP");
		fault.setType("服务");
		fault.setIp(servieIp);
		fault.setName(serviceName);
		this.faultList.add(fault);
	}
	
	/** 设备/主机/端口无反应报警。只在故障一览表中报前N个，报警表中将不报**/
	private void alert(Integer type,MonitorDevice monitorDevice) {
		MonitorRealtimeFault fault = new MonitorRealtimeFault();
		if (type == -2) {
			fault.setData("TCP");
			fault.setType("服务");
		} else if (type == -1) {
			fault.setData("温度");
			fault.setType("温度监控");
		} else {
			fault.setData("SNMP");
			fault.setType( null==monitorDevice.getMonitorDeviceType()?"":monitorDevice.getMonitorDeviceType().getName());
		}
		fault.setIp(monitorDevice.getIp());
		fault.setName(monitorDevice.getName());
		this.faultList.add(fault);
	}
	
	
	public List<MonitorDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<MonitorDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	

	public MonitorRealtimeUseService getMonitorRealtimeUseService() {
		return monitorRealtimeUseService;
	}

	public void setMonitorRealtimeUseService(
			MonitorRealtimeUseService monitorRealtimeUseService) {
		this.monitorRealtimeUseService = monitorRealtimeUseService;
	}

	public MonitorTcpPortService getMonitorTcpPortService() {
		return monitorTcpPortService;
	}

	public void setMonitorTcpPortService(MonitorTcpPortService monitorTcpPortService) {
		this.monitorTcpPortService = monitorTcpPortService;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

	public MonitorServerProcessService getMonitorServerProcessService() {
		return monitorServerProcessService;
	}

	public void setMonitorServerProcessService(
			MonitorServerProcessService monitorServerProcessService) {
		this.monitorServerProcessService = monitorServerProcessService;
	}

	public MonitorCpuDataService getMonitorCpuDataService() {
		return monitorCpuDataService;
	}

	public void setMonitorCpuDataService(MonitorCpuDataService monitorCpuDataService) {
		this.monitorCpuDataService = monitorCpuDataService;
	}

	public MonitorRealtimeDelayService getMonitorRealtimeDelayService() {
		return monitorRealtimeDelayService;
	}

	public void setMonitorRealtimeDelayService(
			MonitorRealtimeDelayService monitorRealtimeDelayService) {
		this.monitorRealtimeDelayService = monitorRealtimeDelayService;
	}

	public MonitorRealtimeFaultService getMonitorRealtimeFaultService() {
		return monitorRealtimeFaultService;
	}

	public void setMonitorRealtimeFaultService(
			MonitorRealtimeFaultService monitorRealtimeFaultService) {
		this.monitorRealtimeFaultService = monitorRealtimeFaultService;
	}

	public MonitorRealtimePortflowService getMonitorRealtimePortflowService() {
		return monitorRealtimePortflowService;
	}

	public void setMonitorRealtimePortflowService(
			MonitorRealtimePortflowService monitorRealtimePortflowService) {
		this.monitorRealtimePortflowService = monitorRealtimePortflowService;
	}

	public MonitorRealtimeCpuService getMonitorRealtimeCpuService() {
		return monitorRealtimeCpuService;
	}

	public void setMonitorRealtimeCpuService(
			MonitorRealtimeCpuService monitorRealtimeCpuService) {
		this.monitorRealtimeCpuService = monitorRealtimeCpuService;
	}

	public HostSnmpQuery getHostSnmpQuery() {
		return hostSnmpQuery;
	}

	public void setHostSnmpQuery(HostSnmpQuery hostSnmpQuery) {
		this.hostSnmpQuery = hostSnmpQuery;
	}

	public MonitorKeyInterfaceService getMonitorKeyInterfaceService() {
		return monitorKeyInterfaceService;
	}

	public void setMonitorKeyInterfaceService(
			MonitorKeyInterfaceService monitorKeyInterfaceService) {
		this.monitorKeyInterfaceService = monitorKeyInterfaceService;
	}

	public MonitorUrlResponseService getMonitorUrlResponseService() {
		return monitorUrlResponseService;
	}

	public void setMonitorUrlResponseService(
			MonitorUrlResponseService monitorUrlResponseService) {
		this.monitorUrlResponseService = monitorUrlResponseService;
	}

	public MonitorPingDestTypeService getMonitorPingDestTypeService() {
		return monitorPingDestTypeService;
	}

	public void setMonitorPingDestTypeService(
			MonitorPingDestTypeService monitorPingDestTypeService) {
		this.monitorPingDestTypeService = monitorPingDestTypeService;
	}



	
}
