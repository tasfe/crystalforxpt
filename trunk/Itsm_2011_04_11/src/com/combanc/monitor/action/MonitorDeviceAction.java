package com.combanc.monitor.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.common.core.Constants;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.util.DateUtil;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.algorithm.DeviceInterfaceStatus;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.algorithm.MACPortScan;
import com.combanc.monitor.algorithm.SnmpDeviceScan;
import com.combanc.monitor.algorithm.tool.CpuPollTool;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorBaseCpuData;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSubnetDevice;
import com.combanc.monitor.pojoext.ArpEntry;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.MacEntry;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorCpuDataDayService;
import com.combanc.monitor.service.MonitorCpuDataHourService;
import com.combanc.monitor.service.MonitorCpuDataService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorDeviceTypeService;
import com.combanc.monitor.service.MonitorSubnetService;

public class MonitorDeviceAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 5262432297378607950L;
	
	private static final long ONE_DAY_TIME = 1 * 24 * 60 * 60 * 1000;
	private static final long SEVER_DAY_TIME = 7 * 24 * 60 * 60 * 1000;
	private static final long ONE_MONTH_TIME = 30 * 24 * 60 * 60 * 1000;
	
	HttpServletRequest request = null;
	
	private String actionURI;
	
	private String deviceId;
	private String newDeviceIp;
	private String startIp;
	private String endIp;
	private String readCommunity;
	private Integer subnetId;
	
	private MonitorSubnet monitorSubnet;
	
	
	public MonitorSubnet getMonitorSubnet() {
		return monitorSubnet;
	}
	public void setMonitorSubnet(MonitorSubnet monitorSubnet) {
		this.monitorSubnet = monitorSubnet;
	}

	private MonitorDevice monitorDevice;
	private MonitorSubnetService monitorSubnetService;
	private MonitorDeviceService monitorDeviceService;
	private MonitorDeviceTypeService monitorDeviceTypeService;
	private MonitorCpuDataService monitorCpuDataService;
	private MonitorCpuDataHourService monitorCpuDataHourService;
	private MonitorCpuDataDayService monitorCpuDataDayService;
	private MonitorAlertService monitorAlertService;
	
	private List<MonitorDevice> monitorDeviceList = new ArrayList<MonitorDevice>();
	private List<MonitorDevice> newMonitorDeviceList = new ArrayList<MonitorDevice>();
	private List<MonitorDeviceType> monitorDeviceTypeList = new ArrayList<MonitorDeviceType>();
	private List<MonitorAlert> monitorAlertList = new ArrayList<MonitorAlert>();

	DeviceInterfaceStatus deviceInterfaceStatus;
	DeviceSnmpQuery deviceSnmpQuery;
	MACPortScan macPortScan;
	
	
	
    public Integer getSubnetId() {
		return subnetId;
	}
	public void setSubnetId(Integer subnetId) {
		this.subnetId = subnetId;
	}
	public MACPortScan getMacPortScan() {
		return macPortScan;
	}
	public void setMacPortScan(MACPortScan macPortScan) {
		this.macPortScan = macPortScan;
	}
	public MonitorAlertService getMonitorAlertService() {
		return monitorAlertService;
	}
	public void setMonitorAlertService(MonitorAlertService monitorAlertService) {
		this.monitorAlertService = monitorAlertService;
	}
	public List<MonitorDeviceType> getMonitorDeviceTypeList() {
		return monitorDeviceTypeList;
	}
	public void setMonitorDeviceTypeList(
			List<MonitorDeviceType> monitorDeviceTypeList) {
		this.monitorDeviceTypeList = monitorDeviceTypeList;
	}
	 
	public List<MonitorAlert> getMonitorAlertList() {
		return monitorAlertList;
	}
	public void setMonitorAlertList(List<MonitorAlert> monitorAlertList) {
		this.monitorAlertList = monitorAlertList;
	}
	public MonitorDeviceTypeService getMonitorDeviceTypeService() {
		return monitorDeviceTypeService;
	}
	public void setMonitorDeviceTypeService(
			MonitorDeviceTypeService monitorDeviceTypeService) {
		this.monitorDeviceTypeService = monitorDeviceTypeService;
	}
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}
	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	public MonitorCpuDataHourService getMonitorCpuDataHourService() {
		return monitorCpuDataHourService;
	}
	public void setMonitorCpuDataHourService(
			MonitorCpuDataHourService monitorCpuDataHourService) {
		this.monitorCpuDataHourService = monitorCpuDataHourService;
	}
	public MonitorCpuDataDayService getMonitorCpuDataDayService() {
		return monitorCpuDataDayService;
	}
	public void setMonitorCpuDataDayService(
			MonitorCpuDataDayService monitorCpuDataDayService) {
		this.monitorCpuDataDayService = monitorCpuDataDayService;
	}
	public MonitorCpuDataService getMonitorCpuDataService() {
		return monitorCpuDataService;
	}
	public void setMonitorCpuDataService(MonitorCpuDataService monitorCpuDataService) {
		this.monitorCpuDataService = monitorCpuDataService;
	}
	public DeviceInterfaceStatus getDeviceInterfaceStatus() {
		return deviceInterfaceStatus;
	}
	public void setDeviceInterfaceStatus(DeviceInterfaceStatus deviceInterfaceStatus) {
		this.deviceInterfaceStatus = deviceInterfaceStatus;
	}

	private int page = 1;
    private PageBean pageBean;

	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getNewDeviceIp() {
		return newDeviceIp;
	}
	public void setNewDeviceIp(String newDeviceIp) {
		this.newDeviceIp = newDeviceIp;
	}
	public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getReadCommunity() {
		return readCommunity;
	}
	public void setReadCommunity(String readCommunity) {
		this.readCommunity = readCommunity;
	}
	public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public PageBean getPageBean() {
        return pageBean;
    }
    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public String getActionURI() {
		return actionURI;
	} 
	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
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

	public List<MonitorDevice> getLinkportList() {
		return monitorDeviceList;
	}
	public void setLinkportList(List<MonitorDevice> monitorDeviceList) {
		this.monitorDeviceList = monitorDeviceList;
	}
	public void setNewMonitorDeviceList(List<MonitorDevice> newMonitorDeviceList) {
		this.newMonitorDeviceList = newMonitorDeviceList;
	}
	public List<MonitorDevice> getNewMonitorDeviceList() {
		return newMonitorDeviceList;
	}

	public String save() throws Exception {
		checkVendorNull(monitorDevice);
		monitorDeviceService.save(monitorDevice);
		if(null == subnetId)
			return SUCCESS;
		else{
			MonitorSubnet subnet = this.monitorSubnetService.findById(subnetId);
			List<MonitorDevice> monitorDeviceList = new ArrayList<MonitorDevice>();
			monitorDeviceList.add(monitorDevice);
			monitorSubnetService.batchInsert(subnet, monitorDeviceList);
			return "subnetDevices";
		}
	}
	
	public String searchSave() throws Exception {
		//如果是在分区设置页面搜索的新设备，需要把分区和设备关联起来，即插入到表Monitor_subnet_device
		List<MonitorDevice> list = new ArrayList<MonitorDevice>();
		
		if (newDeviceIp.equals("all")) {
			for (MonitorDevice md : newMonitorDeviceList) {
				monitorDeviceService.save(md);
			}
			list = newMonitorDeviceList;
			
		} else {
			String[] ips=newDeviceIp.split(",");
			for(int i=0;i<ips.length;i++){
				
				for (Iterator it = newMonitorDeviceList.iterator();it.hasNext();){      
					MonitorDevice md = (MonitorDevice)it.next();
					if(md.getIp().equals(ips[i])) {
						monitorDeviceService.save(md);
						list.add(md);
					} 

				}  
				
				
			}
		}
		
		//把新搜索出来的设备加入到分区内
		if(null != subnetId){
			MonitorSubnet subnet = monitorSubnetService.findById(subnetId);
			if(null != subnet)
				this.monitorSubnetService.batchInsert(subnet, list);
			request.setAttribute("subnetId", subnetId);
			return "subnet";
		}
		return SUCCESS;
	}
	
	public String update() throws Exception {
		checkVendorNull(monitorDevice);
		monitorDeviceService.update(monitorDevice);
		if(null == subnetId)
			return SUCCESS;
		else
			return "subnetDevices";
	}
	
	private void checkVendorNull(MonitorDevice monitorDevice2) {
		if(null == monitorDevice.getMonitorVendorMac().getId())
			monitorDevice.setMonitorVendorMac(null);
	}
	
	public String delete() {
		String[] id=deviceId.split(",");
		for(int i=0;i<id.length;i++){
			MonitorDevice device = monitorDeviceService.findById(Integer.valueOf(id[i]));
			if(null != device){
				monitorDeviceService.delete(device);
			}
		}
		if(null == subnetId)
			return "success";
		else
			return "subnetDevices";
	}

	public List<MonitorDevice> getMonitorDeviceList() {
		return monitorDeviceList;
	}
	public void setMonitorDeviceList(List<MonitorDevice> monitorDeviceList) {
		this.monitorDeviceList = monitorDeviceList;
	}
	@SuppressWarnings("unchecked")
	public String list() {
		actionURI = "deviceList";
		String ip = (String)request.getParameter("ip");
	    String condition = "";
	    Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
		if (null != this.pageBean && this.pageBean.getPageSize()>0) {
			pageSize = this.pageBean.getPageSize();
		}
		 
		request.setAttribute("pageSize", pageSize);
		super.setServletRequest(request);
        this.pageBean = monitorDeviceService.queryForPage(pageSize, page, condition);
        request.setAttribute("ip", ip);
        return SUCCESS;
	}

	public String addInput() throws Exception {
		actionURI = "deviceAdd";
		return SUCCESS;
	}
	
	public String updateInput() throws Exception {
		monitorDevice=monitorDeviceService.get(Integer.valueOf(deviceId));
		return SUCCESS;
		
	}
	/** 查找设备**/
	public String queryInput() throws Exception {
		return SUCCESS;
	}
	
	/** 查找设备**/
	public String query() throws Exception {
		actionURI = "deviceQuery";
		String condition = "";
		if(null == this.monitorSubnet){
			if(null != this.monitorDevice){
				if(null != this.monitorDevice.getIp() && this.monitorDevice.getIp().trim().length()>0){
					condition += " and p.ip='" + this.monitorDevice.getIp().trim() +"'";
				}
				if(null != this.monitorDevice.getMac() && this.monitorDevice.getMac().trim().length()>0){
					condition += " and p.mac='" + this.monitorDevice.getMac().trim() +"'";
				}
				if(null != this.monitorDevice.getMonitorDeviceType() 
						&& null != this.monitorDevice.getMonitorDeviceType().getCode()
						&& this.monitorDevice.getMonitorDeviceType().getCode()>0){
					condition += " and p.monitorDeviceType.code =" + this.monitorDevice.getMonitorDeviceType().getCode();
				}
			}
			Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
			if (null != this.pageBean && this.pageBean.getPageSize()>0) {
					pageSize = this.pageBean.getPageSize();
			}
				 
			request.setAttribute("pageSize", pageSize);
			super.setServletRequest(request);
		    this.pageBean = monitorDeviceService.queryForPage(pageSize, page, condition);
		    
		}else{
			if(null != this.monitorSubnet.getId() ){
				condition += " and p.monitorSubnet.id=" + this.monitorSubnet.getId() ;
			}
			if(null != this.monitorDevice){
				
				if(null != this.monitorDevice.getIp() && this.monitorDevice.getIp().trim().length()>0){
					condition += " and p.monitorDevice.ip='" + this.monitorDevice.getIp().trim() +"'";
				}
				if(null != this.monitorDevice.getMac() && this.monitorDevice.getMac().trim().length()>0){
					condition += " and p.monitorDevice.mac='" + this.monitorDevice.getMac().trim() +"'";
				}
				if(null != this.monitorDevice.getMonitorDeviceType() 
						&& null != this.monitorDevice.getMonitorDeviceType().getCode()
						&& this.monitorDevice.getMonitorDeviceType().getCode()>0){
					condition += " and p.monitorDevice.monitorDeviceType.code =" + this.monitorDevice.getMonitorDeviceType().getCode();
				}
			}
			Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
			if (null != this.pageBean && this.pageBean.getPageSize()>0) {
					pageSize = this.pageBean.getPageSize();
			}
				 
			request.setAttribute("pageSize", pageSize);
			super.setServletRequest(request);
		    this.pageBean = monitorSubnetService.queryDeviceForPage(pageSize, page, condition);
		}
		
		return SUCCESS;
	}
	
	public String detail() throws Exception {
		if(null != deviceId){
			monitorDevice = monitorDeviceService.get(Integer.valueOf(deviceId));
		}else {
			String ip = request.getParameter("deviceIp");
			if(null != ip && ip.length()>0){
				monitorDevice = monitorDeviceService.findDeviceByIp(ip);
			}
		}
		if(null != monitorDevice){
			int limit = 30;
			List<MonitorAlert> monitorAlertList0 = monitorAlertService.findByIp(monitorDevice.getIp(),0,limit);
			List<MonitorAlert> monitorAlertList1 = monitorAlertService.findByIp(monitorDevice.getIp(),1,limit);
			List<MonitorAlert> monitorAlertList2 = monitorAlertService.findByIp(monitorDevice.getIp(),2,limit);
			List<MonitorAlert> monitorAlertList3 = monitorAlertService.findByIp(monitorDevice.getIp(),3,limit);
			List<MonitorAlert> monitorAlertList4 = monitorAlertService.findByIp(monitorDevice.getIp(),4,limit);
			List<MonitorAlert> monitorAlertList5 = monitorAlertService.findByIp(monitorDevice.getIp(),5,limit);
			request.setAttribute("monitorAlertList0", monitorAlertList0);
			request.setAttribute("monitorAlertList1", monitorAlertList1);
			request.setAttribute("monitorAlertList2", monitorAlertList2);
			request.setAttribute("monitorAlertList3", monitorAlertList3);
			request.setAttribute("monitorAlertList4", monitorAlertList4);
			request.setAttribute("monitorAlertList5", monitorAlertList5);
		}
		return SUCCESS;
	}
	
	// 搜索一个网段的设备
	public String searchInput() throws Exception {
		actionURI = "deviceSearch";
		return SUCCESS;
	}
	public String searchResult() throws Exception {
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String deviceInterface() throws Exception {
		// monitorDevice =monitorDeviceService.get(Integer.valueOf(deviceId));
//		deviceInterfaceStatus.setMonitorDevice(monitorDevice);
		//deviceInterfaceStatus.init(Integer.valueOf(deviceId));
		deviceInterfaceStatus.init(monitorDevice.getId());
		deviceInterfaceStatus.pollFirst(monitorDevice.getId());
		return SUCCESS;
	}
	
	/** 查看CPU历史**/
	public String cpuHistory() throws Exception {
		if(null != deviceId){
			monitorDevice =monitorDeviceService.get(Integer.valueOf(deviceId));

		}else{
			String ip = request.getParameter("ip");
			String start =  request.getParameter("start");
			String end =  request.getParameter("end");
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			monitorDevice = monitorDeviceService.findDeviceByIp(ip);
		}
		return SUCCESS;
		
	}
	/** cpu测控**/
	public String cpuMonitor(){
		String ip = request.getParameter("ip");
		request.setAttribute("ip", ip);
		return SUCCESS;
	}
	/** 温湿度测控**/
	public String akcpMonitor(){
		String ip = request.getParameter("ip");
		request.setAttribute("ip", ip);
		return SUCCESS;
	}
	/** 接口测控**/
	public String interfaceMonitor(){
		String ip = request.getParameter("ip");
		String ifIndex = request.getParameter("ifIndex");
		String ifDescr = "",ifDescrDisp = "";// 接口显示信息，初始化与接口描述相同
		String status = "";// 显示管理和操作状态
		String devDescr = "";
		if(null != ip && ip.length()>0){
			MonitorDevice device = monitorDeviceService.findDeviceByIp(ip);
			if(null != device){
				devDescr = device.getName();
				// 获取接口描述、上线状态和接口名。注意：接口名在IF-MIB中，以GETLIST方式如果读空，
				// 则整个结果空。这里MIB II与IF MIB读分开
				String[] info = deviceSnmpQuery.getIFInfo(ip, device.getReadCommunity(),ifIndex); 
				String ifName = deviceSnmpQuery.getIFName(ip, device.getReadCommunity(),ifIndex);
				
				if (info != null) {
					status = info[1] + "：" + info[2];
					ifDescr = info[0];
					ifDescrDisp = ifDescr;
				}
				
				// 显示接口名：接口描述
				if (ifName != null && !ifName.equals("") && !ifName.equals(ifDescr))
					ifDescrDisp = ifDescr + "，" + ifName;
//				// 取接口CACHE中的接口描述，如果不同则替换，实现接口描述的人工标注
//				String ifNote = getIfNote();
//				if (ifNote != null && !ifNote.equals(""))
//					ifDescrDisp = ifDescrDisp + "，" + ifNote;
				
			}
			
		}
		
		
		request.setAttribute("ip", ip);
		request.setAttribute("ifIndex", ifIndex);
		// 显示接口描述信息
		request.setAttribute("ifDescrDisp", ifDescrDisp);
		// 显示设备描述信息
		request.setAttribute("devDescr", devDescr);
		// 显示管理和操作状态
		request.setAttribute("status", status);
		return SUCCESS;
	}
	/** 查看接口历史流量**/
	public String interfaceHistory(){
		String ip = request.getParameter("ip");
		String ifIndex = request.getParameter("ifIndex");
		request.setAttribute("ip", ip);
		request.setAttribute("ifIndex", ifIndex);
		return SUCCESS;
		
	}
	/** 设备ARP表**/
	public String deviceArpList(){
		String ret = new String();
		List<ArpEntry> arpEntryList = new ArrayList<ArpEntry>();
		if(null != deviceId){
			monitorDevice =monitorDeviceService.get(Integer.valueOf(deviceId));
			ret = deviceSnmpQuery.getIPForward(monitorDevice, arpEntryList);
			if(!"OK".equals(ret))
				request.setAttribute("message", "读设备 "+monitorDevice.getIp()+"IP转发表"+ret);
		}
		request.setAttribute("arpEntryList", arpEntryList);
		return SUCCESS;
	}
	/** 设备MAC转发表**/
	public String deviceMacList(){
		String ret = new String();
		List<MacEntry> devMacEntryList = new ArrayList<MacEntry>();
		if(null != deviceId){
			monitorDevice =monitorDeviceService.get(Integer.valueOf(deviceId));
			boolean MIndex = false;
			boolean PIndex = false;
			String vendorMac = "";
			if (monitorDevice.getMonitorVendorMac() != null) {
				if (monitorDevice.getMonitorVendorMac().getMac().length() > 0
						&& monitorDevice.getMonitorVendorMac().getMac().equals("m")) {
					MIndex = true;
				} else if (monitorDevice.getMonitorVendorMac().getMac().length() > 0
						&& monitorDevice.getMonitorVendorMac().getMac().equals("p")) {
					PIndex = true;
				}
				// 其它，获取厂商表
				else if (monitorDevice.getMonitorVendorMac().getMac().length() > 0) {
					vendorMac = monitorDevice.getMonitorVendorMac().getMac().toUpperCase();
				}
			}
			try {
				macPortScan.init();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ret = macPortScan.scanDevice(monitorDevice, MIndex, PIndex, vendorMac, 1, devMacEntryList);
			if(!"OK".equals(ret))
				request.setAttribute("message", "读设备 "+monitorDevice.getIp()+"MAC转发表"+ret);
		}
		request.setAttribute("devMacEntryList", devMacEntryList);
		return SUCCESS;
	}
}
