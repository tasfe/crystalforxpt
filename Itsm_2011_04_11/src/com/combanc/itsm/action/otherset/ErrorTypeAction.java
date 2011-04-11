package com.combanc.itsm.action.otherset;

import java.util.List;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ErrorType;
import com.combanc.itsm.service.ErrorTypeService;

public class ErrorTypeAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private ErrorTypeService errorTypeService;
	private List<ErrorType> errorTypeList;
	private ErrorType errorType;
	private Integer errorTypeId;
	private String actionURI;

	public String list() throws Exception {
		errorTypeList = errorTypeService.findAll();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "add";
		return "success";
	}

	public String add() throws Exception {
		errorTypeService.save(errorType);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		errorType = errorTypeService.findById(errorTypeId);
		return "success";
	}

	public String update() throws Exception {
		errorTypeService.update(errorType);
		return "list";
	}

	public String delete() throws Exception {
		errorTypeService.deleteById(errorTypeId);
		return "list";
	}

	public Integer getErrorTypeId() {
		return errorTypeId;
	}

	public void setErrorTypeId(Integer errorTypeId) {
		this.errorTypeId = errorTypeId;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public List<ErrorType> getErrorTypeList() {
		return errorTypeList;
	}

	public void setErrorTypeList(List<ErrorType> errorTypeList) {
		this.errorTypeList = errorTypeList;
	}

	public void setErrorTypeService(ErrorTypeService errorTypeService) {
		this.errorTypeService = errorTypeService;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

}
