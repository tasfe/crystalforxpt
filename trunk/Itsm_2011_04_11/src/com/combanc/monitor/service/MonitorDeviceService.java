package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.util.DateUtil;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.algorithm.DataPollTools;
import com.combanc.monitor.algorithm.DeviceInterfaceStatus;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.SnmpDeviceScan;
import com.combanc.monitor.algorithm.SnmpQuery;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.dao.MonitorAlertPolicyDAO;
import com.combanc.monitor.dao.MonitorComputerDAO;
import com.combanc.monitor.dao.MonitorCpuDataDAO;
import com.combanc.monitor.dao.MonitorCpuDataDayDAO;
import com.combanc.monitor.dao.MonitorCpuDataHourDAO;
import com.combanc.monitor.dao.MonitorDeviceDAO;
import com.combanc.monitor.dao.MonitorDeviceTypeDAO;
import com.combanc.monitor.dao.MonitorInterfaceCacheDAO;
import com.combanc.monitor.dao.MonitorLinkportDAO;
import com.combanc.monitor.dao.MonitorRealtimeDelayDAO;
import com.combanc.monitor.dao.MonitorRealtimeUseDAO;
import com.combanc.monitor.dao.MonitorRegularDataDAO;
import com.combanc.monitor.dao.MonitorRegularDataDayDAO;
import com.combanc.monitor.dao.MonitorRegularDataHourDAO;
import com.combanc.monitor.dao.MonitorSystemParamDAO;
import com.combanc.monitor.pojo.MonitorAlertPolicy;
import com.combanc.monitor.pojo.MonitorBaseCpuData;
import com.combanc.monitor.pojo.MonitorBaseRegularData;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorRealtimeDelay;
import com.combanc.monitor.pojo.MonitorRealtimeUse;
import com.combanc.monitor.pojo.MonitorRegularData;
import com.combanc.monitor.pojo.MonitorRegularDataDay;
import com.combanc.monitor.pojo.MonitorRegularDataHour;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.MonitorInterfaceCacheExt;
import com.combanc.monitor.pojoext.MonitorUserTopologyParam;
import com.combanc.monitor.util.ApplicationContextUtil;

public class MonitorDeviceService extends BaseService<MonitorDevice, Integer> {
	
	@Resource
	private MonitorDeviceDAO monitorDeviceDAO;
	private MonitorDevice monitorDevice;
	private DeviceSnmpQuery deviceSnmpQuery;
	private MonitorAlertPolicyDAO monitorAlertPolicyDAO;
	private MonitorComputerDAO monitorComputerDAO;
	private MonitorSystemParamDAO  monitorSystemParamDAO;
	private MonitorInterfaceCacheDAO  monitorInterfaceCacheDAO;
	private DeviceInterfaceStatus deviceInterfaceStatus;

	private SnmpQuery snmpQuery;
	
	private MonitorCpuDataDAO monitorCpuDataDAO;
	private MonitorCpuDataHourDAO monitorCpuDataHourDAO;
	private MonitorCpuDataDayDAO monitorCpuDataDayDAO;
	private MonitorRealtimeUseDAO monitorRealtimeUseDAO;
	private MonitorLinkportDAO monitorLinkportDAO ;
	private MonitorRegularDataService monitorRegularDataService;
	private MonitorRegularDataHourService monitorRegularDataHourService;
	private MonitorRegularDataDayService monitorRegularDataDayService;
	
	
	 

	public MonitorRegularDataHourService getMonitorRegularDataHourService() {
		return monitorRegularDataHourService;
	}

	public void setMonitorRegularDataHourService(
			MonitorRegularDataHourService monitorRegularDataHourService) {
		this.monitorRegularDataHourService = monitorRegularDataHourService;
	}

	public MonitorRegularDataDayService getMonitorRegularDataDayService() {
		return monitorRegularDataDayService;
	}

	public void setMonitorRegularDataDayService(
			MonitorRegularDataDayService monitorRegularDataDayService) {
		this.monitorRegularDataDayService = monitorRegularDataDayService;
	}

	public MonitorRegularDataService getMonitorRegularDataService() {
		return monitorRegularDataService;
	}

	public void setMonitorRegularDataService(
			MonitorRegularDataService monitorRegularDataService) {
		this.monitorRegularDataService = monitorRegularDataService;
	}

	public MonitorLinkportDAO getMonitorLinkportDAO() {
		return monitorLinkportDAO;
	}

	public void setMonitorLinkportDAO(MonitorLinkportDAO monitorLinkportDAO) {
		this.monitorLinkportDAO = monitorLinkportDAO;
	}

	public MonitorRealtimeUseDAO getMonitorRealtimeUseDAO() {
		return monitorRealtimeUseDAO;
	}

	public void setMonitorRealtimeUseDAO(MonitorRealtimeUseDAO monitorRealtimeUseDAO) {
		this.monitorRealtimeUseDAO = monitorRealtimeUseDAO;
	}

	public MonitorCpuDataHourDAO getMonitorCpuDataHourDAO() {
		return monitorCpuDataHourDAO;
	}

	public void setMonitorCpuDataHourDAO(MonitorCpuDataHourDAO monitorCpuDataHourDAO) {
		this.monitorCpuDataHourDAO = monitorCpuDataHourDAO;
	}

	public MonitorCpuDataDayDAO getMonitorCpuDataDayDAO() {
		return monitorCpuDataDayDAO;
	}

	public void setMonitorCpuDataDayDAO(MonitorCpuDataDayDAO monitorCpuDataDayDAO) {
		this.monitorCpuDataDayDAO = monitorCpuDataDayDAO;
	}

	public MonitorCpuDataDAO getMonitorCpuDataDAO() {
		return monitorCpuDataDAO;
	}

	public void setMonitorCpuDataDAO(MonitorCpuDataDAO monitorCpuDataDAO) {
		this.monitorCpuDataDAO = monitorCpuDataDAO;
	}

	public SnmpQuery getSnmpQuery() {
		return snmpQuery;
	}

	public void setSnmpQuery(SnmpQuery snmpQuery) {
		this.snmpQuery = snmpQuery;
	}

	public MonitorAlertPolicyDAO getMonitorAlertPolicyDAO() {
		return monitorAlertPolicyDAO;
	}

	public void setMonitorAlertPolicyDAO(MonitorAlertPolicyDAO monitorAlertPolicyDAO) {
		this.monitorAlertPolicyDAO = monitorAlertPolicyDAO;
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

	public MonitorDeviceDAO getMonitorDeviceDAO() {
		return monitorDeviceDAO;
	}

	public void setMonitorDeviceDAO(MonitorDeviceDAO monitorDeviceDAO) {
		super.setDao(monitorDeviceDAO);
		this.monitorDeviceDAO = monitorDeviceDAO;
	}
	
	public MonitorComputerDAO getMonitorComputerDAO() {
		return monitorComputerDAO;
	}

	public void setMonitorComputerDAO(MonitorComputerDAO monitorComputerDAO) {
		this.monitorComputerDAO = monitorComputerDAO;
	}

	public MonitorSystemParamDAO getMonitorSystemParamDAO() {
		return monitorSystemParamDAO;
	}

	public void setMonitorSystemParamDAO(MonitorSystemParamDAO monitorSystemParamDAO) {
		this.monitorSystemParamDAO = monitorSystemParamDAO;
	}

	public MonitorInterfaceCacheDAO getMonitorInterfaceCacheDAO() {
		return monitorInterfaceCacheDAO;
	}

	public void setMonitorInterfaceCacheDAO(
			MonitorInterfaceCacheDAO monitorInterfaceCacheDAO) {
		this.monitorInterfaceCacheDAO = monitorInterfaceCacheDAO;
	}

	public List findBySql(String hql) {
		return monitorDeviceDAO.findBySql(hql);
	}
	
	public List findAll() {
		return monitorDeviceDAO.findAll(MonitorDevice.class);
	}
	/**返回有效的可监测的设备列表 **/
	public List findEffectiveList() {
		List<MonitorDevice> list = monitorDeviceDAO.findAll(MonitorDevice.class);
		List<MonitorDevice> newList = new ArrayList<MonitorDevice>();
		for(MonitorDevice po :list){
			if(null != po.getMonitorDeviceType() 
					&& null != po.getMonitorDeviceType().getCode()
					&& !po.getMonitorDeviceType().getCode().equals(MainConstants.DEVICE_VIRTUAL)){
				newList.add(po);
			}
		}
		return newList;
	}
	public MonitorDevice load(Integer id){
		return monitorDeviceDAO.findById(MonitorDevice.class, id);
	}

	public void save(MonitorDevice monitorDevice){
		if((monitorDevice.getNameCn() == null || monitorDevice.getNameCn().equals(""))
				&& monitorDevice.getName() != null)
			monitorDevice.setNameCn(monitorDevice.getName());
		monitorDeviceDAO.save(monitorDevice);
	}
	
	public void update(MonitorDevice monitorDevice){
		monitorDeviceDAO.update(monitorDevice);
	}
	
	public void delete(MonitorDevice monitorDevice){
		monitorDeviceDAO.delete(monitorDevice);
		monitorLinkportDAO.deleteByDeviceIp(monitorDevice.getIp());
	}
	
	public DeviceInterfaceStatus getDeviceInterfaceStatus() {
		return deviceInterfaceStatus;
	}
	
	public void setDeviceInterfaceStatus(DeviceInterfaceStatus deviceInterfaceStatus) {
		this.deviceInterfaceStatus = deviceInterfaceStatus;
	}

    /** 
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    @SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize,int page,String condition){

        String hql = "from MonitorDevice as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.id asc";
        int allRow = monitorDeviceDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<MonitorDevice> list = monitorDeviceDAO.queryForPage(hql,offset, length);        //"一页"的记录
        
        //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
    }
	
//	public void batchInsert(List deviceList){
//		monitorDeviceDAO.batchInsert(deviceList);
//	}
//	
//	public void batchUpdate(List deviceList){
//		monitorDeviceDAO.batchUpdate(deviceList);
//	}
    
    /**
	 * 设备列表
	 * @param code 设备类型编码
	 * @param selectedDeviceIp 已选择的设备Ip列表
	 * @param fromIpAddress 起始Ip
	 * @param endIpAddress 结束Ip
	 * @return 返回可选设备列表
	 */
    public List getAvailableDevice(String code,String[] selectedDeviceIp,String fromIpAddress,String endIpAddress) {
    	return monitorDeviceDAO.getAvailableDevice(code, selectedDeviceIp, fromIpAddress, endIpAddress);
    }
    
    /**
	 * 已选设备
	 * @param selectedDeviceIp 已选择的设备Ip列表
	 * @return
	 */
    public List getSelectedDevice(String[] selectedDeviceIp) {
    	return monitorDeviceDAO.getSelectedDevice(selectedDeviceIp);
    }
    
    
	/**
	 * 读取单台设备信息
	 * @param ip 设备IP地址
	 * @param readCommunity 设备SNMP 读Community
	 * @return MonitorDevice 读取错误则返回null
	 * @throws Exception
	 */
	public MonitorDevice readDeviceInfo(String ip, String readCommunity,String vendorMacId)
			throws Exception {
		//DeviceSnmpQuery deviceSnmpQuery = new DeviceSnmpQuery();
		String[] devDescr = deviceSnmpQuery.getDescr(ip, readCommunity);
		if (devDescr == null) {
			return null;
		}
		
		
		MonitorDevice md = new MonitorDevice();
		md.setIp(ip);
		md.setReadCommunity(readCommunity);
		md.setDescription(Tools.cutDescr(devDescr[0]));
		md.setName(Tools.decodeISO8859(devDescr[1]));
		md.setNameCn(Tools.decodeISO8859(devDescr[1]));
		deviceSnmpQuery.getDeviceMac(md);
		return md;
	}

	public List<MonitorDevice> findByIp(String ip) {
		return monitorDeviceDAO.findByIp(ip);
	}
	
	public MonitorDevice findDeviceByIp(String ip) {
		List<MonitorDevice> result = monitorDeviceDAO.findByIp(ip);
		if(null != result && result.size()>0)
			return result.get(0);
		else
			return null;
	}
	
	/**
	 * 根据设备类型查找设备
	 * @param typeId
	 * @return
	 */
	public List findByDeviceType(Integer typeId) {
		return monitorDeviceDAO.findByProperty("monitorDeviceType.code", typeId);
	}
	
	// MonitorDeviceType 相关函数
	
	private MonitorDeviceTypeDAO monitorDeviceTypeDAO;

	public MonitorDeviceTypeDAO getMonitorDeviceTypeDAO() {
		return monitorDeviceTypeDAO;
	}

	public void setMonitorDeviceTypeDAO(MonitorDeviceTypeDAO monitorDeviceTypeDAO) {
		this.monitorDeviceTypeDAO = monitorDeviceTypeDAO;
	}

	public List findAllDeviceTypes() {
		return monitorDeviceTypeDAO.findAll();
	}
	
	public MonitorDevice findById(Integer id) {
		return monitorDeviceDAO.findById(id);
	}
	public List findDeviceInterfaceList(Integer deviceId){
		MonitorDevice device=monitorDeviceDAO.findById(MonitorDevice.class, deviceId);
		
		List<MonitorInterfaceCache> micList=new ArrayList<MonitorInterfaceCache>();
		if(null == device )
			return micList;
		if(null != device.getMonitorDeviceType() && MainConstants.DEVICE_VIRTUAL == device.getMonitorDeviceType().getCode())
			return micList;
		boolean isServer = false;
		if(null != device.getMonitorDeviceType())
			isServer = MainConstants.isServer(device.getMonitorDeviceType().getCode());
		deviceSnmpQuery.getIFCache(device,micList,true,isServer);
		return micList;
		
	}

	public void batchUpdate(List deviceList){
		monitorDeviceDAO.batchUpdate(deviceList);
	}
	
	/**
	 * 获取接口状态
	 * @param dv
	 * @param interfaceNum
	 * @return 
	 *  -1									// 失败
	 *  INTERFACE_STATUS_ONLINE		= 0;	// 接口上线状态
	 *  INTERFACE_STATUS_OFFLINE		= 1;	// 接口下线状态
	 *  INTERFACE_STATUS_SHUTDOWN	= 2;	// 接口关闭状态
	 *  INTERFACE_STATUS_NODATA		= 3;	// 读不到数据
	 */
	public int getInterfaceStatus(MonitorDevice dv, String interfaceNum) {
		if ( null ==  dv|| null == interfaceNum  || interfaceNum.isEmpty())
			return -1;
		DataPollTools dataPollTools = new DataPollTools();
		SnmpTarget target = new SnmpTarget();
		String counter64 = "";
		String c64 = dv.getNote1();
		if (c64 != null && c64.length() > 0)
			counter64 = c64.toUpperCase();
		String[] pollData = dataPollTools.getFluxData(target, dv.getIp(),
				interfaceNum, dv.getReadCommunity(), counter64);
		// 如果读到数据：
		if (pollData != null) {
			if (pollData[11].equals("1") || pollData[11].equals("up(1)"))
				return MainConstants.INTERFACE_STATUS_ONLINE;// 接口上线
			else {
				// 接口关闭置-2，接口开up(1)置-1
				if (pollData[12].equals("2") || pollData[12].equals("down(2)")) {
					return MainConstants.INTERFACE_STATUS_SHUTDOWN;// 接口关闭
				}else
					return MainConstants.INTERFACE_STATUS_OFFLINE;// 接口下线
			}
		} else {
			return MainConstants.INTERFACE_STATUS_NODATA;// 没有得到数据
		}
	}
	
	/**解除策略**/
	public void relievePolicy(Integer deviceId){
		MonitorDevice device =  monitorDeviceDAO.findById(deviceId);
		if(null != device && null != device.getMonitorAlertPolicy()){
			device.setMonitorAlertPolicy(null);
			monitorDeviceDAO.update(device);
		}
	}
	
	/**应用策略**/
	public void applyPolicy(Integer policyId,Integer deviceId){
		MonitorAlertPolicy policy = monitorAlertPolicyDAO.findById(policyId);
		MonitorDevice device =  monitorDeviceDAO.findById(deviceId);
		if(null != device && null != policy){
			
			device.setMonitorAlertPolicy(policy);
			monitorDeviceDAO.update(device);
		}
	}
	
	/**
	 * flex端调用函数
	 * @param deviceId	设备id
	 * @param type		类型，0代表来在档案，1代表来自快照
	 * @return	三个列表构成的一个对象MonitorUserTopologyParam
	 */
	public MonitorUserTopologyParam findByDeviceId(Integer deviceId, int type) {
		MonitorDevice mDevice = findById(deviceId);
		MonitorUserTopologyParam param =new MonitorUserTopologyParam();
		param.setMonitorDevice(mDevice);
		List<MonitorSystemParam> systemParamList=monitorSystemParamDAO.findAll();
		param.setSystemParamList(systemParamList);
		List<MonitorComputer> computerList = new ArrayList<MonitorComputer>();
		if(type == 1) {
			computerList = monitorComputerDAO.findByPropertys(MonitorComputer.class, new String[] {"monitorDevice.id", "snapshot"},  new Object[]{deviceId, true});
		} else {
			computerList = monitorComputerDAO.findByProperty("monitorDevice.id", deviceId);
		}
		param.setComputerList(computerList);
		List<MonitorInterfaceCacheExt> interfaceCacheExtList=monitorInterfaceCacheDAO.findByDeviceIdAvailable(mDevice, computerList);
		try{
			interfaceCacheExtList = deviceInterfaceStatus.pollFirst(deviceId, interfaceCacheExtList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		param.setInterfaceCacheExtList(interfaceCacheExtList);
		return  param;
	}
	/**
	 * 搜索发现设备
	 * @param startIp 起始IP
	 * @param endIp	结束IP
	 * @param readCommunity 
	 * @return
	 * @throws InterruptedException 
	 */
	public List<MonitorDevice> deviceScan(String startIp,String endIp,String readCommunity) {
		List<MonitorDevice> monitorDeviceList = this.findAll();
		List<MonitorDeviceType> monitorDeviceTypeList = this.findAllDeviceTypes();
		List<MonitorDevice> newMonitorDeviceList = new ArrayList<MonitorDevice>();
		 
		try {
			snmpQuery = new SnmpQuery(startIp, endIp,
					readCommunity, monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
			snmpQuery.start();
			snmpQuery.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//等线程结束后再继续运行
		for(MonitorDevice md : newMonitorDeviceList){
			deviceSnmpQuery.getDeviceMac(md);
		}
		return newMonitorDeviceList;
	}
	
	public String stopDeviceScan(){
		
			if (snmpQuery != null) {
				if (snmpQuery.isAlive()) {
					//snmpDeviceScan.waitThreadGroupEnd();
					snmpQuery.stop();

				}
				// 确保内存收集程序能清除。
				snmpQuery = null;
				
			}
		
		
		return "停止设备搜索";
	}
	/**
	 * 返回CPU、温湿度历史
	 * @param ip 设备IP地址
	 * @param day 查询的天数 1|7|30天
	 * @param dataType 数据类型 1：CPU历史， 2：温度， 3：湿度
	 * @return  
	 */
	public List<String> deviceDataHistory(String ip,int day,int dataType){
		
		List<String> xml = new ArrayList<String>();
		//确定开始结束时间
		Timestamp startTime, endTime;
		Date today = new Date();
		
		 
		switch(day){
			case 1://1天==24小时，读五分钟数据表
				today = setTime(new Timestamp(today.getTime()));
				startTime =TimeUtil.getStartTimeIn24Hours(today);
				endTime = new Timestamp(today.getTime());
				long timeGap1 = 1000*60*5;//时间间隔 5分钟
				List<MonitorBaseCpuData> dataList1 = monitorCpuDataDAO.findByPropertys(ip, startTime, endTime, dataType);
				xml = getDataXml(startTime,endTime,timeGap1,dataList1);
				break;
			case 7://7天，读小时数据表
				java.text.DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				String value1 = format1.format(today);
				today = java.sql.Date.valueOf(value1);
				startTime =new Timestamp(TimeUtil.getNextDay(today.getTime(), -5));
				endTime = TimeUtil.getLastestDay(today);
				long timeGap2 = 1000*60*60;//时间间隔 1小时
				List<MonitorBaseCpuData> dataList2 = monitorCpuDataHourDAO.findByPropertys(ip, startTime, endTime, dataType);
				xml = getDataXml(startTime,endTime,timeGap2,dataList2);
				 
				break;
			case 30://30天，读天数据表
				java.text.DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
				String value = format2.format(today);
				today = java.sql.Date.valueOf(value);
				startTime =new Timestamp(TimeUtil.getNextDay(today.getTime(), -28));
				endTime = TimeUtil.getLastestDay(today);
				long timeGap3 = 1000*60*60*24;//时间间隔 1天
				List<MonitorBaseCpuData> dataList3 = monitorCpuDataDayDAO.findByPropertys(ip, startTime, endTime, dataType);
				xml = getDataXml(startTime,endTime,timeGap3,dataList3);
				break;
			default:
				startTime =TimeUtil.getStartTimeIn24Hours(today);
				endTime = new Timestamp(today.getTime());
				endTime = new Timestamp(today.getTime());
				long timeGap4 = 1000*60*5;//时间间隔 5分钟
				List<MonitorBaseCpuData> dataList4 = monitorCpuDataDAO.findByPropertys(ip, startTime, endTime, dataType);
				xml = getDataXml(startTime,endTime,timeGap4,dataList4);
				break;
		}
		
		 
		return xml;
	}
	/**
	 * 根据起止时间，返回CPU、温湿度历史 
	 * @param ip
	 * @param dataType
	 * @param strStart 
	 * @param strEnd
	 * @return
	 */
	public List<String> deviceDataHistory(String ip,int dataType,String strStart,String strEnd){
		List<String> xml = new ArrayList<String>();
		//确定开始结束时间
		Timestamp startTime, endTime;
		long ONE_DAY_TIME = 1 * 24 * 60 * 60 * 1000;
		long SEVEN_DAY_TIME = 7 * 24 * 60 * 60 * 1000;
		startTime = Timestamp.valueOf(strStart);
		endTime = Timestamp.valueOf(strEnd);
		long diff = endTime.getTime() - startTime.getTime();
		 
		if(diff <= ONE_DAY_TIME){
			//把起止时间设为5分钟的倍数
			startTime = this.setTime(startTime);
			endTime = this.setTime(endTime);
			long timeGap1 = 1000*60*5;//时间间隔 5分钟
			List<MonitorBaseCpuData> dataList1 = monitorCpuDataDAO.findByPropertys(ip, startTime, endTime, dataType);
			xml = getDataXml(startTime,endTime,timeGap1,dataList1);
		}else if(diff <= SEVEN_DAY_TIME){
			//把起止时间设为整点时刻
			long timeGap2 = 1000*60*60;//时间间隔 1小时
			startTime.setMinutes(0);
			startTime.setSeconds(0);
			startTime.setNanos(0);
			endTime.setMinutes(0);
			endTime.setSeconds(0);
			endTime.setNanos(0);
			List<MonitorBaseCpuData> dataList2 = monitorCpuDataHourDAO.findByPropertys(ip, startTime, endTime, dataType);
			xml = getDataXml(startTime,endTime,timeGap2,dataList2);
		}else{
			TimeUtil.toDayStart(startTime);//把起止时间转换到凌晨整点时刻
			TimeUtil.toDayStart(endTime);
			long timeGap3 = 1000*60*60*24;//时间间隔 1天
			List<MonitorBaseCpuData> dataList3 = monitorCpuDataDayDAO.findByPropertys(ip, startTime, endTime, dataType);
			xml = getDataXml(startTime,endTime,timeGap3,dataList3);
		}
			 
		
		return xml;
	}
	/**
	 * 返回历史时间及数据拼接的字符串
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param timeGap 时间间隔
	 * @param dataList 历史数据
	 * @return
	 */
	public List<String> getDataXml(Timestamp startTime, Timestamp endTime,long timeGap,List<MonitorBaseCpuData> dataList){
		HashMap dataMap = setDataMap(startTime,endTime,timeGap);
		putMapValue(dataList,dataMap);
		StringBuffer lable = new StringBuffer();//时间--横坐标 格式为'1|2|3......|7'
		StringBuffer value = new StringBuffer();//值--竖坐标 格式为'2|0|3......|9'
		lable.append(DateUtil.getYYYYMMDDHHMM(startTime));
		value.append(dataMap.get(DateUtil.getYYYYMMDDHHMM(startTime)));		
		
		long start = startTime.getTime();
		while(start<endTime.getTime()){
			start += timeGap;
			String hhmm = DateUtil.getYYYYMMDDHHMM(new Timestamp(start));
			String strLable = DateUtil.getMMDDHHMM(new Timestamp(start));
			lable.append("|"+strLable);
			value.append("|"+dataMap.get(hhmm));				
		}
		List<String> xml = new ArrayList<String>();
		xml.add(lable.toString());
		xml.add(value.toString());
		return xml;
		
	}
	/**
	 * 设置Map数据
	 * @param startTime 
	 * @param endTime
	 * @param timeGap
	 * @return
	 */
	public HashMap setDataMap(Timestamp startTime, Timestamp endTime,long timeGap){
		long start = startTime.getTime();
		HashMap dataMap = new HashMap();
		dataMap.put(DateUtil.getYYYYMMDDHHMM(startTime), "0");
		while(start<endTime.getTime()){
			start += timeGap;
			String hhmm = DateUtil.getYYYYMMDDHHMM(new Timestamp(start));
			dataMap.put(hhmm, "0");
			
		}
		return dataMap;
	}
	
	public void putMapValue(List<MonitorBaseCpuData> dataList,HashMap dataMap){
		for(MonitorBaseCpuData po :dataList){
			String hhmm = DateUtil.getYYYYMMDDHHMM(setTime(po.getGatherTime()));
			if(!po.getCpu().equals(-1f))
				dataMap.put(hhmm, po.getCpu());
		}
		
	}
	/**
	 * 设置时间
	 * @param time  8:46 返回8:45 8:48返回8:50 
	 * @return
	 */
	public Timestamp setTime(Timestamp time){
		int m = time.getMinutes();
		int remainder = m % 5;
		if(remainder>0){
			if(remainder<3)
				return new Timestamp(time.getTime() - remainder * 60 * 1000);
			else
				return new Timestamp(time.getTime() + (5 - remainder) * 60 * 1000);
		}else{
			return   time;
		}
	}
	/**
	 * 查找设备接口的历史流量
	 * @param ip
	 * @param ifIndex
	 * @param day
	 * @param strStart
	 * @param strEnd
	 * @return
	 */
	public List<String> deviceInterfaceDataHistory(String ip,String ifIndex,String unit ,int day,String strStart,String strEnd){
		String ifDescription = "";//接口描述
		//得到接口描述信息
		MonitorDevice device = this.findDeviceByIp(ip);
		if(null != device){
			List<MonitorInterfaceCache>  list = monitorInterfaceCacheDAO.findByDeviceAndInterface(device.getId(), ifIndex);
			if(list.size()>0)
				ifDescription = list.get(0).getDescription();
		}
		
		boolean result = true;//有没有查询结果
		List<String> xml = new ArrayList<String>();
		String strGap = "间隔 5分钟";
		//确定开始结束时间
		Timestamp startTime, endTime;
		java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strStart1 = "";
		String strEnd1 = "";
		//如果day>0，表示没有指定起始结束时间，查找24小时/7天/30天的数据
		if(day > 0){
			Date today = new Date();
			switch(day){
				case 1://1天==24小时，读五分钟数据表
					today = setTime(new Timestamp(today.getTime()));
					startTime =TimeUtil.getStartTimeIn24Hours(today);
					endTime = new Timestamp(today.getTime());
					strStart1 = format.format(setTime(startTime));
					strEnd1 = format.format(setTime(endTime));
					long timeGap1 = 1000*60*5;//时间间隔 5分钟
					strGap = "间隔 5分钟";
					List<MonitorRegularData> dataList = monitorRegularDataService.findInterfaceHistory(ip,ifIndex, startTime, endTime);
					xml = getInterfaceHistoryXml(unit,startTime,endTime,timeGap1,dataList);
					if(dataList.size()==0){
						result = false;
					} 
					break;
				case 7://7天，读小时数据表
					java.text.DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					String value1 = format1.format(today);
					today = java.sql.Date.valueOf(value1);
					startTime =new Timestamp(TimeUtil.getNextDay(today.getTime(), -5));
					endTime = TimeUtil.getLastestDay(today);
					strStart1 = format.format(setTime(startTime));
					strEnd1 = format.format(setTime(endTime));
					long timeGap2 = 1000*60*60;//时间间隔 1小时
					strGap = "间隔 1小时";
					List<MonitorRegularDataHour> dataList2 = monitorRegularDataHourService.findInterfaceHistory(ip,ifIndex, startTime, endTime);
					xml = getInterfaceHistoryXml(unit,startTime,endTime,timeGap2,dataList2);
					if(dataList2.size()==0){
						result = false;
					} 
					break;
				case 30://30天，读天数据表
					java.text.DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
					String value = format2.format(today);
					today = java.sql.Date.valueOf(value);
					startTime =new Timestamp(TimeUtil.getNextDay(today.getTime(), -28));
					endTime = TimeUtil.getLastestDay(today);
					strStart1 = format.format(setTime(startTime));
					strEnd1 = format.format(setTime(endTime));
					long timeGap3 = 1000*60*60*24;//时间间隔 1天
					strGap = "间隔 1天";
					List<MonitorRegularDataDay> dataList3 = monitorRegularDataDayService.findInterfaceHistory(ip,ifIndex, startTime, endTime);
					xml = getInterfaceHistoryXml(unit,startTime,endTime,timeGap3,dataList3);
					if(dataList3.size()==0){
						result = false;
					} 
					break;
				default:
					
					break;
			}
		}else{
			long ONE_DAY_TIME = 1 * 24 * 60 * 60 * 1000;
			long SEVEN_DAY_TIME = 7 * 24 * 60 * 60 * 1000;
			startTime = Timestamp.valueOf(strStart);
			endTime = Timestamp.valueOf(strEnd);
			long diff = endTime.getTime() - startTime.getTime();
			 
			if(diff <= ONE_DAY_TIME){
				//把起止时间设为5分钟的倍数
				startTime = this.setTime(startTime);
				endTime = this.setTime(endTime);
				strStart1 = format.format(startTime);
				strEnd1 = format.format(endTime);
				long timeGap1 = 1000*60*5;//时间间隔 5分钟
				strGap = "间隔 5分钟";
				List<MonitorRegularData> dataList = monitorRegularDataService.findInterfaceHistory(ip,ifIndex, startTime, endTime);
				xml = getInterfaceHistoryXml(unit,startTime,endTime,timeGap1,dataList);
				if(dataList.size()==0){
					result = false;
				} 
			}else if(diff <= SEVEN_DAY_TIME){
				//把起止时间设为整点时刻
				long timeGap2 = 1000*60*60;//时间间隔 1小时
				startTime.setMinutes(0);
				startTime.setSeconds(0);
				startTime.setNanos(0);
				endTime.setMinutes(0);
				endTime.setSeconds(0);
				endTime.setNanos(0);
				strStart1 = format.format(startTime);
				strEnd1 = format.format(endTime);
				strGap = "间隔 1小时";
				List<MonitorRegularDataHour> dataList2 = monitorRegularDataHourService.findInterfaceHistory(ip,ifIndex, startTime, endTime);
				xml = getInterfaceHistoryXml(unit,startTime,endTime,timeGap2,dataList2);
				if(dataList2.size()==0){
					result = false;
				} 
				
			}else{
				TimeUtil.toDayStart(startTime);//把起止时间转换到凌晨整点时刻
				TimeUtil.toDayStart(endTime);
				strStart1 = format.format(startTime);
				strEnd1 = format.format(endTime);
				long timeGap3 = 1000*60*60*24;//时间间隔 1天
				strGap = "间隔 1天";
				List<MonitorRegularDataDay> dataList3 = monitorRegularDataDayService.findInterfaceHistory(ip,ifIndex, startTime, endTime);
				xml = getInterfaceHistoryXml(unit,startTime,endTime,timeGap3,dataList3);
				if(dataList3.size()==0){
					result = false;
				} 
				
			}
		}
		
		String title = ip+"第"+ifIndex+"接口(" +ifDescription+")历史流量";
		String subTitle = strStart1+"--"+strEnd1+"  ("+strGap+")";
		xml.add(title);
		xml.add(subTitle);
		xml.add(String.valueOf(result));
		return xml;
		
		
	}
	
	public List<String> getInterfaceHistoryXml(String unit,Timestamp startTime, Timestamp endTime,long timeGap,List dataList){
		HashMap dataMap = setInterfaceHistoryDataMap(startTime,endTime,timeGap);
		putInterfaceHistoryMapValue(dataList,dataMap);
		StringBuffer lable = new StringBuffer();//时间--横坐标 格式为'1|2|3......|7'
		StringBuffer value1 = new StringBuffer();//值--竖坐标 格式为'2|0|3......|9'
		StringBuffer value2 = new StringBuffer();//值--竖坐标 格式为'2|0|3......|9'
		StringBuffer value3 = new StringBuffer();//值--竖坐标 格式为'2|0|3......|9'
		
		// 设置流量单位
        Double dUnit = 1000.0D;
		if(unit.equals("1")){
			dUnit = 1000.0D * 1000.0D;
		}
		
		Object key = DateUtil.getYYYYMMDDHHMM(startTime);
		lable.append(key);
		Object obj = dataMap.get(key);
		MonitorBaseRegularData po = (MonitorBaseRegularData) obj;
		value1.append(filterAndConversion(po.getBiTraffic(),dUnit));		
		value2.append(filterAndConversion(po.getReceiveTraffic(),dUnit));	
		value3.append(filterAndConversion(po.getDeliveryTraffic(),dUnit));	
		
		long start = startTime.getTime();
		while(start<endTime.getTime()){
			start += timeGap;
			String hhmm = DateUtil.getYYYYMMDDHHMM(new Timestamp(start));
			String strLable = DateUtil.getMMDDHHMM(new Timestamp(start));
			lable.append("|"+strLable);
			obj = dataMap.get(hhmm);
			po = (MonitorBaseRegularData) obj;
			value1.append("|"+filterAndConversion(po.getBiTraffic(),dUnit));	
			value2.append("|"+filterAndConversion(po.getReceiveTraffic(),dUnit));	
			value3.append("|"+filterAndConversion(po.getDeliveryTraffic(),dUnit));	
		}
		List<String> xml = new ArrayList<String>();
		xml.add(lable.toString());
		xml.add(value1.toString());
		xml.add(value2.toString());
		xml.add(value3.toString());
		return xml;
		
	}
	/**
	 * 过滤异常值并进行单位转换
	 * @param value
	 * @param changeUnit
	 * @return
	 */
	private String filterAndConversion(Long value ,Double dUnit){
		String result = "null";
		if( null != value && value * 8  / 300.0D / 1000000000 <= MainConstants.REGULARDATA_LIMIT){// 过滤异常值
			result = String.valueOf(value * 8  / 300.0D / dUnit);
			//注意：数据库小时表和天表中的平局流量指的是5分钟的平均字节数
		}
		return result;
	}
	public HashMap setInterfaceHistoryDataMap(Timestamp startTime, Timestamp endTime,long timeGap){
		long start = startTime.getTime();
		HashMap dataMap = new HashMap();
		dataMap.put(DateUtil.getYYYYMMDDHHMM(startTime), new MonitorRegularData());
		while(start<endTime.getTime()){
			start += timeGap;
			String hhmm = DateUtil.getYYYYMMDDHHMM(new Timestamp(start));
			dataMap.put(hhmm, new MonitorRegularData());
			
		}
		return dataMap;
	}
	public void putInterfaceHistoryMapValue(List dataList,HashMap dataMap){
		for (Iterator it = dataList.iterator();it.hasNext();){      
			MonitorBaseRegularData po = (MonitorBaseRegularData)it.next();
			String key = DateUtil.getYYYYMMDDHHMM(setTime(po.getGatherTime()));
			dataMap.put(key,  po);
		}  
	}
	
	/**
	 * 得到设备的可用率
	 * @param ip
	 * @return
	 */
	public List<Integer> getDeviceUseData(String ip){
		List <Integer> result = new ArrayList<Integer>();
		Integer effective = 1;
		Integer unEffective = 0;
		List<MonitorRealtimeUse> list =  monitorRealtimeUseDAO.findByIp(ip);
		if(null != list && list.size()>0){
			MonitorRealtimeUse use = list.get(0);
			effective = use.getEffectiveNum().intValue();
			unEffective = use.getTotalNum().intValue() - effective;
		}
		result.add(effective);
		result.add(unEffective);
		
		return result;
	}
	/**
	 * 得到设备的反应时间
	 * @param ip
	 * @return
	 */
	public Long getDeviceDelayTime(String ip){
		Long delay = -1l;
		MonitorDevice device = this.findDeviceByIp(ip);
		if(null != device){
			long timeUsed = System.currentTimeMillis();//用时
			String ret = deviceSnmpQuery.getVendorOID(device);
			delay = System.currentTimeMillis() - timeUsed;
		}
		
		return delay;
		 
		
	}
}

