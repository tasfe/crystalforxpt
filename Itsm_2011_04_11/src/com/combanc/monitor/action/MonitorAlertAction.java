package com.combanc.monitor.action;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorAlertSmalltype;
import com.combanc.monitor.pojo.MonitorAlertType;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorAlertSmalltypeService;
import com.combanc.monitor.service.MonitorAlertTypeService;
import com.combanc.monitor.service.MonitorSystemParamService;

public class MonitorAlertAction extends BaseActionSupport implements ServletRequestAware{
	
	HttpServletRequest request = null;
	
	private MonitorAlertService monitorAlertService;
	private MonitorAlertTypeService monitorAlertTypeService;
	private MonitorAlertSmalltypeService monitorAlertSmalltypeService;
	private MonitorSystemParamService monitorSystemParamService;
	private MonitorAlertType monitorAlertType;
	private MonitorAlertSmalltype monitorAlertSmalltype;
	
	private int page;
    private PageBean pageBean;
    private String condition ;
    
    /** 报警升级门限（报limitToAddLevel次警后，提升报警级别） **/
    private Integer limitToAddLevel = SystemParamConstants.SYSTEM_TO_ADD_LEVEL_DEFUALT_VALUE;
    
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}
	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}
	public Integer getLimitToAddLevel() {
		return limitToAddLevel;
	}
	public void setLimitToAddLevel(Integer limitToAddLevel) {
		this.limitToAddLevel = limitToAddLevel;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
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
	public MonitorAlertType getMonitorAlertType() {
		return monitorAlertType;
	}
	public void setMonitorAlertType(MonitorAlertType monitorAlertType) {
		this.monitorAlertType = monitorAlertType;
	}
	public MonitorAlertService getMonitorAlertService() {
		return monitorAlertService;
	}
	public void setMonitorAlertService(MonitorAlertService monitorAlertService) {
		this.monitorAlertService = monitorAlertService;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public MonitorAlertTypeService getMonitorAlertTypeService() {
		return monitorAlertTypeService;
	}
	public void setMonitorAlertTypeService(
			MonitorAlertTypeService monitorAlertTypeService) {
		this.monitorAlertTypeService = monitorAlertTypeService;
	}
	
	public MonitorAlertSmalltype getMonitorAlertSmalltype() {
		return monitorAlertSmalltype;
	}
	public void setMonitorAlertSmalltype(MonitorAlertSmalltype monitorAlertSmalltype) {
		monitorAlertSmalltype = monitorAlertSmalltype;
	}
	
	public MonitorAlertSmalltypeService getMonitorAlertSmalltypeService() {
		return monitorAlertSmalltypeService;
	}
	public void setMonitorAlertSmalltypeService(
			MonitorAlertSmalltypeService monitorAlertSmalltypeService) {
		this.monitorAlertSmalltypeService = monitorAlertSmalltypeService;
	}
	/**主页浏览所有**/
	public String listAll(){

		return SUCCESS;
	}
	/** 根据条件显示**/
	public String listByCondition(){
		
		MonitorSystemParam param  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_LIMIT_TO_ADD_LEVEL);
		if(null != param && null != param.getValue() && !"".equals(param.getValue()))
			this.limitToAddLevel = Integer.valueOf(param.getValue());

		if(null != monitorAlertType && null != monitorAlertType.getCode()){
			 
				condition = " and p.monitorAlertType.code = "+  monitorAlertType.getCode();
		}else{
			String typeId = request.getParameter("type");
			condition = "";
			if(null != typeId && !"all".equals(typeId)){
				condition = " and p.monitorAlertType.code = "+ typeId;
				monitorAlertType = monitorAlertTypeService.get(Integer.parseInt(typeId));
			} 
		}
		
		Integer pageSize =  PageBean.DEFAULT_PAGE_SIZE;
		if(null!=pageBean && pageBean.getPageSize()>0)
			pageSize =  pageBean.getPageSize();
		condition += " and p.lastTime >='" + MainConstants.SERVER_START_TIME.toString()+"'";
		pageBean = monitorAlertService.queryForPage(pageSize, page, condition);
		
		return SUCCESS;
	}
	/** 删除报警**/
	public String delete(){
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			String[] ids = id.split(",");
			if(ids.length>0){
				for(int i=0 ;i<ids.length;i++){
					monitorAlertService.deleteById(Integer.parseInt(ids[i]));
				}
			}
		}
		return SUCCESS;
	}
	/** 查找报警**/
	public String searchInput(){
		return SUCCESS;
	}
	/** 查找报警返回结果**/
	public String searchResult(){
		condition ="";
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String selectedItem = request.getParameter("selectedItem");
		String[] selectedItemId = selectedItem.split("@");
		if(selectedItemId.length>0){
			String str = "";
			for(int i=0;i<selectedItemId.length;i++)
				str = str + selectedItemId[i] + ",";
			str = "(" + str.substring(0, str.length() - 1) + ")";
			condition = condition + " AND p.monitorAlertSmalltype.code in " + str;
		}
		Timestamp startTime = Timestamp.valueOf(start + " 00:00:00");
		Timestamp endTime = Timestamp.valueOf(end + " 23:59:59");
		String timesql = " AND firstTime >= '" + startTime + 
		"' AND firstTime <= '" + endTime + "'" ;
		condition = condition + timesql;
		Integer pageSize =  PageBean.DEFAULT_PAGE_SIZE;
		if(null!=pageBean && pageBean.getPageSize()>0)
			pageSize =  pageBean.getPageSize();
		pageBean = monitorAlertService.queryForPage(pageSize, page, condition);
		monitorAlertType =null;
		return SUCCESS;
	}
	
	/** 查看报警说明**/
	public String description(){
		String id = request.getParameter("id");
		if(null != id && id.length()>0){
			Integer code = Integer.parseInt(id);
			this.monitorAlertSmalltype = monitorAlertSmalltypeService.get(code);
		}
		return SUCCESS;
	}

}
