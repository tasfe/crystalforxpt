package com.combanc.itsm.action.systemmanage;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.pojo.SeverityTyp;
import com.combanc.itsm.service.ServiceCategoryService;
import com.combanc.itsm.service.SeverityTypService;
import com.combanc.itsm.service.tree.TreeDateService;

public class ServiceCategoryAction extends BaseActionSupport {
	private static final long serialVersionUID = 1L;
	private ServiceCategoryService serviceCategoryService;
	private SeverityTypService severityTypService;
	private List<ServiceCategory> serviceCategoryList;
	private List<ServiceCategory> allServiceCategory;
	private ServiceCategory serviceCategory;
	private Integer serviceCategoryId;
	private Integer type;
	private Integer pid = 0;
	private String actionURI;
	private List<SeverityTyp> defEssList;
	private List<SeverityTyp> defEmeList;
	private String teamString;
	private TreeDateService treeDateService;
	
	public String getTeamString() {
		return teamString;
	}

	public void setTeamString(String teamString) {
		this.teamString = teamString;
	}
   @Resource
	public void setTreeDateService(TreeDateService treeDateService) {
		this.treeDateService = treeDateService;
	}

	public String main() throws Exception {
		return "success";
	}

	public String top() throws Exception {
		allServiceCategory = serviceCategoryService.findAllByType(type);
		return "success";
	}
	public String top2() throws Exception {
		allServiceCategory = serviceCategoryService.findAllByType(type);
		return "success";
	}
	public String top3() throws Exception {
		allServiceCategory = serviceCategoryService.findAllByType(type);
		return "success";
	}
	public String top4() throws Exception {
		allServiceCategory = serviceCategoryService.findAllByType(1);
		return "success";
	}
	public String list() throws Exception {
		if (ServiceCategoryService.PROJECTTASK_TYPE == type) {
			serviceCategoryList = serviceCategoryService.findAllByTypeAndPid(
					type, pid);
			return "projecttask";
		} else {
			if (pid == 0)
				serviceCategoryList = serviceCategoryService
						.findAllByTypeAndPid(type, pid);
			else
				serviceCategoryList = serviceCategoryService.findAllByPid(pid);
			return "success";
		}
	}

	public String addInput() throws Exception {
		actionURI = "save";
		if (ServiceCategoryService.EVENT_TYPE == type) {
			defEssList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY1);
			defEmeList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY2);
			return "event";
		} else if (ServiceCategoryService.PLANTASK_TYPE == type) {
			return "plantask";
		} else if (ServiceCategoryService.CHANGEREQUEST_TYPE == type) {
			defEssList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY1);
			defEmeList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY2);
			return "changerequest";
		} else if (ServiceCategoryService.PROJECT_TYPE == type) {
			return "project";
		} else if (ServiceCategoryService.PROJECTTASK_TYPE == type) {
			return "projecttask";
		} else {
			return null;
		}
	}

	public String save() throws Exception {
		serviceCategoryService.save(serviceCategory);
		return "success";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		if (ServiceCategoryService.EVENT_TYPE == type) {
			serviceCategory = serviceCategoryService
					.findById(serviceCategoryId);
			pid = serviceCategory.getPid();
			defEssList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY1);
			defEmeList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY2);
			return "event";
		} else if (ServiceCategoryService.PLANTASK_TYPE == type) {
			serviceCategory = serviceCategoryService
					.findById(serviceCategoryId);
			pid = serviceCategory.getPid();
			return "plantask";
		} else if (ServiceCategoryService.CHANGEREQUEST_TYPE == type) {
			serviceCategory = serviceCategoryService
					.findById(serviceCategoryId);
			pid = serviceCategory.getPid();
			defEssList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY1);
			defEmeList = severityTypService
					.findByCategory(SeverityTypService.CATEGORY2);
			return "changerequest";
		} else if (ServiceCategoryService.PROJECT_TYPE == type) {
			serviceCategory = serviceCategoryService
					.findById(serviceCategoryId);
			pid = serviceCategory.getPid();
			return "project";
		} else if (ServiceCategoryService.PROJECTTASK_TYPE == type) {
			serviceCategory = serviceCategoryService
					.findById(serviceCategoryId);
			return "projecttask";
		} else {
			return null;
		}
	}

	public String update() throws Exception {
		serviceCategoryService.update(serviceCategory);
		return "success";
	}

	public String delete() throws Exception {
		serviceCategoryService.deleteById(serviceCategoryId);
		if (ServiceCategoryService.PROJECTTASK_TYPE == type)
			return "projecttask";
		else
			return "success";
	}

	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	public void setSeverityTypService(SeverityTypService severityTypService) {
		this.severityTypService = severityTypService;
	}

	public List<ServiceCategory> getServiceCategoryList() {
		return serviceCategoryList;
	}

	public void setServiceCategoryList(List<ServiceCategory> serviceCategoryList) {
		this.serviceCategoryList = serviceCategoryList;
	}

	public List<ServiceCategory> getAllServiceCategory() {
		return allServiceCategory;
	}

	public void setAllServiceCategory(List<ServiceCategory> allServiceCategory) {
		this.allServiceCategory = allServiceCategory;
	}

	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public Integer getServiceCategoryId() {
		return serviceCategoryId;
	}

	public void setServiceCategoryId(Integer serviceCategoryId) {
		this.serviceCategoryId = serviceCategoryId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public List<SeverityTyp> getDefEssList() {
		return defEssList;
	}

	public void setDefEssList(List<SeverityTyp> defEssList) {
		this.defEssList = defEssList;
	}

	public List<SeverityTyp> getDefEmeList() {
		return defEmeList;
	}

	public void setDefEmeList(List<SeverityTyp> defEmeList) {
		this.defEmeList = defEmeList;
	}
    public String catList() throws Exception {
		
		teamString = treeDateService.getServiceCategoryListString(0);

		return SUCCESS;
	}
}
