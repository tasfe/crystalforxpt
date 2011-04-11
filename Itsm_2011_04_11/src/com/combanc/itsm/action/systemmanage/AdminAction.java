package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ConfigRecord;
import com.combanc.itsm.service.ConfigRecordService;
import com.opensymphony.xwork2.ActionContext;

public class AdminAction extends BaseActionSupport implements
ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private ConfigRecordService configrecordService;
	public ConfigRecordService getConfigrecordService() {
		return configrecordService;
	}
	public void setConfigrecordService(ConfigRecordService configrecordService) {
		this.configrecordService = configrecordService;
	}
	
	private List record;
	private ConfigRecord configre;
	
	public ConfigRecord getConfigre() {
		return configre;
	}
	public void setConfigre(ConfigRecord configre) {
		this.configre = configre;
	}
	public List getRecord() {
		return record;
	}
	public void setRecord(List record) {
		this.record = record;
	}
	
	private String ids;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String adminlogin() throws Exception {
		record=configrecordService.findAll();
		List list=configrecordService.findbysql("from ConfigRecord where isAssetsinfo='1'");
		configre=(ConfigRecord)list.get(0);
		return "success";
	}
	
	public String adminsave() throws Exception{
		ConfigRecord cord=new ConfigRecord();
		List list=configrecordService.findbysql("from ConfigRecord where isAssetsinfo='1'");
		cord=(ConfigRecord)list.get(0);
		cord.setIsAssetsinfo(0);
		configrecordService.update(cord);
		List lists=configrecordService.findbysql("from ConfigRecord where id='"+ids+"'");
		ConfigRecord cords=new ConfigRecord();
		cords=(ConfigRecord)lists.get(0);
		cords.setIsAssetsinfo(1);
		configrecordService.update(cords);
		return "success";
	}
	
}
