package com.combanc.monitor.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.service.MonitorAccessLogService;
import com.combanc.monitor.service.MonitorArpLogService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorSubnetService;

public class MonitorLogAction extends BaseActionSupport implements ServletRequestAware{

	
	private String actionURI;
	private int page;
    private PageBean pageBean;
    private String condition ;
    private String start;
    private String upDeviceIp;
    private String keyName;
    private String keyValue;
    private Integer subnetId;
    private String type;
    
    HttpServletRequest request = null;
    
    private MonitorAccessLogService monitorAccessLogService;
    
    private MonitorArpLogService monitorArpLogService;
    
    private MonitorSubnetService monitorSubnetService;
    
    public String accessLogList(){
    	actionURI = "accessLogList";
	    String condition = "";
	    
	    Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
		if (null != this.pageBean && this.pageBean .getPageSize()>0) {
			pageSize = this.pageBean.getPageSize();
		}
		
        this.pageBean = monitorAccessLogService.queryForPage(pageSize, page, condition);
		return SUCCESS;
	}
    
  
	public String queryAccessLog() throws Exception {
		actionURI = "queryAccessLog";
		String condition = "";
		
		Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
		if (null != this.pageBean && this.pageBean .getPageSize()>0) {
			pageSize = this.pageBean.getPageSize();
		}
		if(null != start && !"".equals(start)){
			Timestamp startTime = Timestamp.valueOf(start + " 00:00:00");
			TimeUtil.toDayStart(startTime);
			Timestamp endTime	= Timestamp.valueOf(start + " 00:00:00");
			TimeUtil.toDayEnd(endTime);
			condition = "  and firstTime>='"+startTime.toString()+"' and firstTime<='"+endTime.toString()+"'";
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
						 condition += "  and upDeviceIp in ("+str+")";
					 }
					 
				}
			}else{
				condition += "  and upDeviceIp='"+upDeviceIp+"'";
			}
			
		}
		if(null != keyValue && !"".equals(keyValue)){
			if("IP".equals(keyName))
				condition += "  and ip='"+keyValue+"'";
			else if("MAC".equals(keyName))
				condition += "  and mac='"+keyValue+"'";
			
		}
		request.setAttribute("pageSize", pageSize);
		pageBean =  monitorAccessLogService.queryForPage(pageSize, page, condition);

		return SUCCESS;
	}
	/** ARP日志 **/
	public String arpLogList(){
	    	actionURI = "arpLogList";
		    String condition = "";
		    Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
			if (null != this.pageBean && this.pageBean .getPageSize()>0) {
				pageSize = this.pageBean.getPageSize();
			}
			
	        this.pageBean = monitorArpLogService.queryForPage(pageSize, page, condition);
			return SUCCESS;
	}
	
	/** ARP日志 查找**/
	public String queryArpLog() throws Exception {
		actionURI = "queryArpLog";
		String condition = "";
		Integer pageSize = PageBean.DEFAULT_PAGE_SIZE;
		if (null != this.pageBean && this.pageBean .getPageSize()>0) {
			pageSize = this.pageBean.getPageSize();
		}
		if(null != start && !"".equals(start)){
			Timestamp startTime = Timestamp.valueOf(start + " 00:00:00");
			TimeUtil.toDayStart(startTime);
			Timestamp endTime	= Timestamp.valueOf(start + " 00:00:00");
			TimeUtil.toDayEnd(endTime);
			condition = "  and firstTime>='"+startTime.toString()+"' and firstTime<='"+endTime.toString()+"'";
		}
		if(null != type && !"".equals(type)&& !"全部".equals(type)){
			
			condition += "  and type='"+type+"'";
		}
		if(null != keyValue && !"".equals(keyValue)){
			if("IP".equals(keyName))
				condition += "  and ip='"+keyValue+"'";
			else if("MAC".equals(keyName))
				condition += "  and mac='"+keyValue+"'";
			
		}
		 
		pageBean =  monitorArpLogService.queryForPage(pageSize, page, condition);
		return SUCCESS;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public MonitorAccessLogService getMonitorAccessLogService() {
		return monitorAccessLogService;
	}

	public void setMonitorAccessLogService(
			MonitorAccessLogService monitorAccessLogService) {
		this.monitorAccessLogService = monitorAccessLogService;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public Integer getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(Integer subnetId) {
		this.subnetId = subnetId;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}


	public MonitorArpLogService getMonitorArpLogService() {
		return monitorArpLogService;
	}


	public void setMonitorArpLogService(MonitorArpLogService monitorArpLogService) {
		this.monitorArpLogService = monitorArpLogService;
	}


	public String getUpDeviceIp() {
		return upDeviceIp;
	}

	public void setUpDeviceIp(String upDeviceIp) {
		this.upDeviceIp = upDeviceIp;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}

	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	
	
	
}
