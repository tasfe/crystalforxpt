package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ServiceTran;
import com.combanc.itsm.service.ServiceTranService;

public class ServiceTranAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceTran serviceTran;
	private List serviceTrans;
	private String flag;
	private String actionURI;
	private Integer serviceTranId;
	private ServiceTranService serviceTranService;

	public ServiceTran getServiceTran() {
		return serviceTran;
	}

	public void setServiceTran(ServiceTran serviceTran) {
		this.serviceTran = serviceTran;
	}

	public List getServiceTrans() {
		return serviceTrans;
	}

	public void setServiceTrans(List serviceTrans) {
		this.serviceTrans = serviceTrans;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public Integer getServiceTranId() {
		return serviceTranId;
	}

	public void setServiceTranId(Integer serviceTranId) {
		this.serviceTranId = serviceTranId;
	}

	public ServiceTranService getServiceTranService() {
		return serviceTranService;
	}

	public void setServiceTranService(ServiceTranService serviceTranService) {
		this.serviceTranService = serviceTranService;
	}

	public String list() throws Exception {
		serviceTrans = serviceTranService.findAll();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		serviceTranService.save(serviceTran);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		serviceTran = serviceTranService.findServiceTranById(serviceTranId);
		return "success";
	}

	public String update() throws Exception {
		serviceTranService.update(serviceTran);
		return "list";
	}

	public String delete() throws Exception {
		Integer id = Integer.parseInt(getRequest()
				.getParameter("serviceTranId"));
		serviceTranService.deleteById(id);
		return "list";
	}
}
