package com.combanc.itsm.action.systemmanage;

import java.util.List;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ProblemType;
import com.combanc.itsm.service.ProblemTypeService;

public class ProblemTypeAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private ProblemTypeService problemTypeService;
	private List<ProblemType> problemTypeList;
	private List<ProblemType> allProblemType;
	private ProblemType problemType;
	private Integer problemTypeId;
	private String actionURI;
	private Integer flag = 0;
	private Integer pid = 0;

	public String main() throws Exception {
		return "success";
	}

	public String top() throws Exception {
		allProblemType = problemTypeService.findAll();
		return "success";
	}

	public String list() throws Exception {
		if (0 == flag)
			problemTypeList = problemTypeService.findAllByPid(pid);
		else
			problemTypeList = problemTypeService.findAll();
		return "success";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {
		problemTypeService.save(problemType);
		return "success";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		problemType = problemTypeService.findById(problemTypeId);
		pid = problemType.getPid();
		return "success";
	}

	public String update() throws Exception {
		problemTypeService.update(problemType);
		return "success";
	}

	public String delete() throws Exception {
		problemTypeService.deleteById(problemTypeId);
		return "success";
	}

	public void setProblemTypeService(ProblemTypeService problemTypeService) {
		this.problemTypeService = problemTypeService;
	}

	public List<ProblemType> getProblemTypeList() {
		return problemTypeList;
	}

	public void setProblemTypeList(List<ProblemType> problemTypeList) {
		this.problemTypeList = problemTypeList;
	}

	public List<ProblemType> getAllProblemType() {
		return allProblemType;
	}

	public void setAllProblemType(List<ProblemType> allProblemType) {
		this.allProblemType = allProblemType;
	}

	public ProblemType getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}

	public Integer getProblemTypeId() {
		return problemTypeId;
	}

	public void setProblemTypeId(Integer problemTypeId) {
		this.problemTypeId = problemTypeId;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
