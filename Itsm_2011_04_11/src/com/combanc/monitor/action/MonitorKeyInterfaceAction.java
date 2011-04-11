package com.combanc.monitor.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorKeyInterface;
import com.combanc.monitor.service.MonitorKeyInterfaceService;

public class MonitorKeyInterfaceAction extends BaseActionSupport implements ServletRequestAware{
	
	HttpServletRequest request = null;
	
	private String actionURI;
	
	private String id;
	
	private MonitorKeyInterfaceService monitorKeyInterfaceService;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public MonitorKeyInterfaceService getMonitorKeyInterfaceService() {
		return monitorKeyInterfaceService;
	}

	public void setMonitorKeyInterfaceService(
			MonitorKeyInterfaceService monitorKeyInterfaceService) {
		this.monitorKeyInterfaceService = monitorKeyInterfaceService;
	}
	/** 查看关键接口列表**/
	public String list(){
		List<MonitorKeyInterface> list = monitorKeyInterfaceService.getAll();
		request.setAttribute("list", list);
		return SUCCESS;
	}
	/** 删除关键接口**/
	public String delete() {
		String[] deleteId=id.split(",");
		for(int i=0;i<deleteId.length;i++){
			monitorKeyInterfaceService.remove(Integer.valueOf(deleteId[i]));
		}
		return SUCCESS;
	}
	
	/** 启用关键接口**/
	public String enableMonitoring() {
		if(null != id){
			if("all".equals(id)){
				monitorKeyInterfaceService.isMonitor(true);
			}else{
				String[] ifId=id.split(",");
				for(int i=0;i<ifId.length;i++){
					MonitorKeyInterface po = monitorKeyInterfaceService.get(Integer.valueOf(ifId[i]));
					po.setIsMonitor(true);
					monitorKeyInterfaceService.update(po);
				}
				
			}
		}
		
		return SUCCESS;
	}
	/** 停止监测关键接口**/
	public String desableMonitoring() {
		if(null != id){
			if("all".equals(id)){
				monitorKeyInterfaceService.isMonitor(false);
			}else{
				String[] ifId=id.split(",");
				for(int i=0;i<ifId.length;i++){
					MonitorKeyInterface po = monitorKeyInterfaceService.get(Integer.valueOf(ifId[i]));
					po.setIsMonitor(false);
					monitorKeyInterfaceService.update(po);
				}
				
			}
		}
		return SUCCESS;
	}
	

}
