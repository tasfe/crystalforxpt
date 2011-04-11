package com.combanc.itsm.action.systemmanage;

import java.util.List;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.SeverityTyp;
import com.combanc.itsm.service.SeverityTypService;

public class SeverityTypAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private SeverityTypService severityTypService;
	private List<List<SeverityTyp>> severityTypList;
	private SeverityTyp severityTyp;
	private Integer severityTypId;
	private String actionURI;

	public String list() throws Exception {
		severityTypList = severityTypService.findAllSeverityTyp();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "add";
		return "success";
	}

	public String add() throws Exception {
		System.out.println(severityTyp.getId());
		severityTypService.save(severityTyp);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		severityTyp = severityTypService.findById(severityTypId);
		return "success";
	}

	public String update() throws Exception {
		severityTypService.update(severityTyp);
		return "list";
	}

	public String delete() throws Exception {
		severityTypService.deleteById(severityTypId);
		return "list";
	}

	public void setSeverityTypService(SeverityTypService severityTypService) {
		this.severityTypService = severityTypService;
	}

	public List<List<SeverityTyp>> getSeverityTypList() {
		return severityTypList;
	}

	public void setSeverityTypList(List<List<SeverityTyp>> severityTypList) {
		this.severityTypList = severityTypList;
	}

	public SeverityTyp getSeverityTyp() {
		return severityTyp;
	}

	public void setSeverityTyp(SeverityTyp severityTyp) {
		this.severityTyp = severityTyp;
	}

	public Integer getSeverityTypId() {
		return severityTypId;
	}

	public void setSeverityTypId(Integer severityTypId) {
		this.severityTypId = severityTypId;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

}
