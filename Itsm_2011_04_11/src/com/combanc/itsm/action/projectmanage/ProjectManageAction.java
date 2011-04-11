package com.combanc.itsm.action.projectmanage;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.pojo.ProjectManage;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.projectmanage.ProjectManageService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class ProjectManageAction extends ActionSupport {

	private Long id;
	private String projectName;
	private String projectManager;
	private String projectLocation;
	private int projectType;
	private Date beginTime;
	private Date endTime;
	private String projectContent;
	private String file;
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	private String projectCreater;
	private List<ProjectManage> list;
	private ProjectManage projectManage;
	private ProjectManageService projectManageService;

	public String getProjectCreater() {
		return projectCreater;
	}

	public void setProjectCreater(String projectCreater) {
		this.projectCreater = projectCreater;
	}

	public List<ProjectManage> getList() {
		return list;
	}

	public void setList(List<ProjectManage> list) {
		this.list = list;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ProjectManage getProjectManage() {
		return projectManage;
	}

	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public ProjectManageService getProjectManageService() {
		return projectManageService;
	}

	public void setProjectManageService(ProjectManageService projectManageService) {
		this.projectManageService = projectManageService;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getProjectLocation() {
		return projectLocation;
	}

	public void setProjectLocation(String projectLocation) {
		this.projectLocation = projectLocation;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// 跳到创建新项目的页面
	public String newProject() throws Exception {

		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		if (u != null) {
			projectCreater = this.projectManageService.getProjectCreater(u);
		}
		return SUCCESS;
	}

	// 创建新的项目;
	public String createProject() throws Exception {

		ProjectManage project = new ProjectManage();

		project.setProjectName(projectName);
		project.setProjectManager(projectManager);
		project.setProjectContent(projectContent);
		project.setProjectType(projectType);
		project.setProjectCreater(projectCreater);
		project.setProjectLocation(projectLocation);
		project.setBeginTime(new Timestamp(beginTime.getTime()));
		project.setEndTime(new Timestamp(endTime.getTime()));

		// project.setEndTime(endTime);
		// /SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date time = sdm.format(beginTime);
		// project.setBeginTime(Timestamp.valueOf(beginTime));
		// project.setEndTime(new Timestamp(new Date().getTime()));
		// project.setBeginTime(Timestamp.valueOf(endTime));

		this.projectManageService.saveProject(project);

		return SUCCESS;
	}

	// 跟踪项目工作进度;
	public String traceProject() throws Exception {

		if (pageSize < 10) {

			pageSize = 10;
		}

		this.pageBean = this.projectManageService.queryProject(Integer
				.valueOf(pageSize), page);

		return SUCCESS;
	}

	// 列表显示;
	public String queryProject() throws Exception {

		if (pageSize < 10) {

			pageSize = 10;
		}
		this.pageBean = this.projectManageService.queryProject(Integer
				.valueOf(pageSize), page);

		return SUCCESS;
	}

}
