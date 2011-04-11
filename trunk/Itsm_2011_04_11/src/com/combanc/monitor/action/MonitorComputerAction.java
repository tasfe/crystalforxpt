package com.combanc.monitor.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.algorithm.DeviceInterfaceStatus;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.service.MonitorComputerService;
import com.combanc.monitor.service.MonitorSubnetService;

public class MonitorComputerAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 5262432297378607950L;
	HttpServletRequest request = null;
	
	private String actionURI;
	
	private String computerId;
	
	private String upDeviceIp;//上连设备IP
	
	private Integer subnetId;//上连设备所在分区Id
	
	private Integer sanpshotCount;//在线计算机总数
	
	private Integer sanpshotOfflineCount;//不在线计算机总数
	
	private Integer status;//状态

	private MonitorComputer monitorComputer;
	private MonitorSubnetService monitorSubnetService;
	private MonitorComputerService monitorComputerService;
	private List<MonitorDevice> monitorDeviceList = new ArrayList<MonitorDevice>();
	private List<MonitorComputer> monitorComputerList = new ArrayList<MonitorComputer>();

	DeviceInterfaceStatus deviceInterfaceStatus;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSanpshotOfflineCount() {
		return sanpshotOfflineCount;
	}
	public void setSanpshotOfflineCount(Integer sanpshotOfflineCount) {
		this.sanpshotOfflineCount = sanpshotOfflineCount;
	}
	public Integer getSanpshotCount() {
		return sanpshotCount;
	}
	public void setSanpshotCount(Integer sanpshotCount) {
		this.sanpshotCount = sanpshotCount;
	}
	
	public String getUpDeviceIp() {
		return upDeviceIp;
	}
	public void setUpDeviceIp(String upDeviceIp) {
		this.upDeviceIp = upDeviceIp;
	}
	public Integer getSubnetId() {
		return subnetId;
	}
	public void setSubnetId(Integer subnetId) {
		this.subnetId = subnetId;
	}
	public void setComputerId(String computerId) {
		this.computerId = computerId;
	}
	public String getComputerId() {
		return computerId;
	}
	public void setMonitorComputer(MonitorComputer monitorComputer) {
		this.monitorComputer = monitorComputer;
	}
	public MonitorComputer getMonitorComputer() {
		return monitorComputer;
	}
	public DeviceInterfaceStatus getDeviceInterfaceStatus() {
		return deviceInterfaceStatus;
	}
	public void setDeviceInterfaceStatus(DeviceInterfaceStatus deviceInterfaceStatus) {
		this.deviceInterfaceStatus = deviceInterfaceStatus;
	}

	private int page;
    private PageBean pageBean;
    
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
	
	public void setMonitorComputerService(MonitorComputerService monitorComputerService) {
		this.monitorComputerService = monitorComputerService;
	}
	public MonitorComputerService getMonitorComputerService() {
		return monitorComputerService;
	}
	public List<MonitorDevice> getLinkportList() {
		return monitorDeviceList;
	}
	public void setLinkportList(List<MonitorDevice> monitorDeviceList) {
		this.monitorDeviceList = monitorDeviceList;
	}
	public void setMonitorComputerList(List<MonitorComputer> monitorComputerList) {
		this.monitorComputerList = monitorComputerList;
	}
	public List<MonitorComputer> getMonitorComputerList() {
		return monitorComputerList;
	}
	
	public String save() throws Exception {
		monitorComputerService.save(monitorComputer);
		return SUCCESS;
	}
	
	public String update() throws Exception {
		MonitorComputer po = monitorComputerService.get(monitorComputer.getId());
		if(null != po){
			po.setUser(monitorComputer.getUser());
			po.setPlace(monitorComputer.getPlace());
			po.setDepartment(monitorComputer.getDepartment());
			po.setNote1(monitorComputer.getNote1());
			po.setNote2(monitorComputer.getNote2());
		}
		monitorComputerService.update(po);
		return SUCCESS;
	}
	
	public String delete() {
		String[] id=computerId.split(",");
		for(int i=0;i<id.length;i++){
			monitorComputerService.remove(Integer.valueOf(id[i]));
		}
		return "success";
	}

	public List<MonitorDevice> getMonitorDeviceList() {
		return monitorDeviceList;
	}
	public void setMonitorDeviceList(List<MonitorDevice> monitorDeviceList) {
		this.monitorDeviceList = monitorDeviceList;
	}
	
	public String list() {
		actionURI = "computerList";
	    String condition = "";
	    
	    if(null != subnetId && !"".equals(subnetId)){
			 List<MonitorDevice> list = monitorSubnetService.getSelectedDeviceBySubnet(subnetId);
			 if(list.size()>0){
				 String str = "";
				 for(MonitorDevice device : list){
					 str += "'"+device.getIp()+"',";
				 }
				 if(str.length()>0){
					 str =str.substring(0, str.length()-1);
					 condition += "  and p.monitorDevice.ip in ("+str+")";
				 }
			 }else{
				 condition = " and 1=2";
			 }
			 
			 
		}
	    
	    this.sanpshotCount = this.monitorComputerService.getSnapshotCount(condition);
	    Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
		if (null != this.pageBean && this.pageBean .getPageSize()>0) {
			pageSize = this.pageBean.getPageSize();
		}
		 
        this.pageBean = monitorComputerService.queryForPage(pageSize, page, condition);
        sanpshotOfflineCount  = this.pageBean.getAllRow() -  this.sanpshotCount;
        return SUCCESS;
	}
	public String query() throws Exception {
		actionURI = "computerQuery";
		String condition = "";
		if(null != this.monitorComputer){
			if(null != this.monitorComputer.getIp() && this.monitorComputer.getIp().length()>0){
				condition += " and p.ip = '"+ monitorComputer.getIp() +"'";
			}
			if(null != this.monitorComputer.getMac() && this.monitorComputer.getMac().length()>0){
				condition += " and p.mac = '"+ monitorComputer.getMac() +"'";
			}
			if(null != this.monitorComputer.getDiscoveryTime() ){
				Timestamp startTime = new Timestamp(this.monitorComputer.getDiscoveryTime().getTime());
				Timestamp endTime =   new Timestamp(this.monitorComputer.getDiscoveryTime().getTime());
				TimeUtil.toDayEnd(endTime);
					
				condition += "  and p.discoveryTime>='"+startTime.toString()+"' and p.discoveryTime<='"+endTime.toString()+"'";
				
			}
		}
		
		
		if(null != upDeviceIp ){
			if("--全部--".equals(upDeviceIp)){
				if(null != subnetId && !"".equals(subnetId)){
					 List<MonitorDevice> list = monitorSubnetService.getSelectedDeviceBySubnet(subnetId);
					 String str = "";
					 for(MonitorDevice device : list){
						 str += "'"+device.getIp()+"',";
					 }
					 if(str.length()>0){
						 str =str.substring(0, str.length()-1);
						 condition += "  and p.monitorDevice.ip in ("+str+")";
					 }
					 
				}
			}else{
				condition += "  and p.monitorDevice.ip='"+upDeviceIp+"'";
			}
			
		}
		if(this.status.equals(1))
			this.sanpshotCount = 0;
		else
			this.sanpshotCount = this.monitorComputerService.getSnapshotCount(condition);
		if(this.status.equals(0))
			condition += " and snapshot = true";
		if(this.status.equals(1))
			condition += " and snapshot = false";
		
		Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
		if (null != this.pageBean && this.pageBean .getPageSize()>0) {
			pageSize = this.pageBean.getPageSize();
		}
		this.pageBean = monitorComputerService.queryForPage(pageSize, page, condition);
		sanpshotOfflineCount  = this.pageBean.getAllRow() -  this.sanpshotCount;
		return SUCCESS;
	}
	
  
	public String addInput() throws Exception {
		actionURI = "deviceAdd";
		return SUCCESS;
	}
	
	public String updateInput() throws Exception {
		monitorComputer=monitorComputerService.get(Integer.valueOf(computerId));
		return SUCCESS;
	}
	
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String deviceInterface() throws Exception {
		// monitorComputer =monitorComputerService.get(Integer.valueOf(deviceId));
//		deviceInterfaceStatus.setMonitorDevice(monitorComputer);
		//deviceInterfaceStatus.init(Integer.valueOf(deviceId));
		deviceInterfaceStatus.init(monitorComputer.getId());
		deviceInterfaceStatus.pollFirst(monitorComputer.getId());
		return SUCCESS;
	}
}
