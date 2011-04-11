package com.combanc.itsm.action.systemmanage;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.pojo.TaskAllocation;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.ServiceCategoryService;
import com.combanc.itsm.service.TaskAllocationService;

public class TaskAllocationAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskAllocation taskAllocation;
	private List taskAllocations;
	private String flag;
	private String actionURI;
	private Integer taskAllocationId;
	private TaskAllocationService taskAllocationService;
	private ServiceCategoryService serviceCategoryService;
	private DepartmentService departmentService;
	private String departments;
	private String teams;
	private String cats;
	private Integer departmentId;
	private Integer type;
	private String serviceCategoryList;
	private RoleteamService roleteamService;
	private List<ServiceCategory> allServiceCategory;
	private List<Department> allDepartmentList;
	private String departmentList;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCatsName() {
		return catsName;
	}

	public void setCatsName(String catsName) {
		this.catsName = catsName;
	}

	public String getDepartmentsName() {
		return departmentsName;
	}

	public void setDepartmentsName(String departmentsName) {
		this.departmentsName = departmentsName;
	}

	/**
	 * @return the serviceCategoryList
	 */
	public String getServiceCategoryList() {
		return serviceCategoryList;
	}

	/**
	 * @param serviceCategoryList
	 *            the serviceCategoryList to set
	 */
	public void setServiceCategoryList(String serviceCategoryList) {
		this.serviceCategoryList = serviceCategoryList;
	}

	/**
	 * @return the departmentList
	 */
	public String getDepartmentList() {
		return departmentList;
	}

	/**
	 * @param departmentList
	 *            the departmentList to set
	 */
	public void setDepartmentList(String departmentList) {
		this.departmentList = departmentList;
	}

	/**
	 * @return the allDepartmentList
	 */
	public List<Department> getAllDepartmentList() {
		return allDepartmentList;
	}

	/**
	 * @param allDepartmentList
	 *            the allDepartmentList to set
	 */
	public void setAllDepartmentList(List<Department> allDepartmentList) {
		this.allDepartmentList = allDepartmentList;
	}


	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the allServiceCategory
	 */
	public List<ServiceCategory> getAllServiceCategory() {
		return allServiceCategory;
	}

	/**
	 * @param allServiceCategory
	 *            the allServiceCategory to set
	 */
	public void setAllServiceCategory(List<ServiceCategory> allServiceCategory) {
		this.allServiceCategory = allServiceCategory;
	}

	/**
	 * @return the cats
	 */
	public String getCats() {
		return cats;
	}

	/**
	 * @param cats
	 *            the cats to set
	 */
	public void setCats(String cats) {
		this.cats = cats;
	}

	/**
	 * @return the roleteamService
	 */
	public RoleteamService getRoleteamService() {
		return roleteamService;
	}

	/**
	 * @param roleteamService
	 *            the roleteamService to set
	 */
	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

	/**
	 * @return the team
	 */
	public String getTeams() {
		return teams;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeams(String teams) {
		this.teams = teams;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departments
	 */
	public String getDepartments() {
		return departments;
	}

	/**
	 * @param departments
	 *            the departments to set
	 */
	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public ServiceCategoryService getServiceCategoryService() {
		return serviceCategoryService;
	}

	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	public TaskAllocation getTaskAllocation() {
		return taskAllocation;
	}

	public void setTaskAllocation(TaskAllocation taskAllocation) {
		this.taskAllocation = taskAllocation;
	}

	public List getTaskAllocations() {
		return taskAllocations;
	}

	public void setTaskAllocations(List taskAllocations) {
		this.taskAllocations = taskAllocations;
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

	public Integer getTaskAllocationId() {
		return taskAllocationId;
	}

	public void setTaskAllocationId(Integer taskAllocationId) {
		this.taskAllocationId = taskAllocationId;
	}

	public TaskAllocationService getTaskAllocationService() {
		return taskAllocationService;
	}

	public void setTaskAllocationService(
			TaskAllocationService taskAllocationService) {
		this.taskAllocationService = taskAllocationService;
	}

	public String list() throws Exception {
		taskAllocations = taskAllocationService.findAll();
		return "success";
	}

	public String save() throws Exception {
		String[] depId = departments.split(":");
		if (!depId.equals("")) {
			for (String d : depId) {
				if (d.equals("")) {
					continue;
				}
				Department dpt = departmentService.findDepartmentById(Integer
						.valueOf(d));
				taskAllocation.getDepartment().add(dpt);

			}
		}
		String[] catsId = cats.split(":");
		if (!catsId.equals("")) {
			for (String d : catsId) {
				if (d.equals("")) {
					continue;
				}
				ServiceCategory dpt = serviceCategoryService.findById(Integer
						.valueOf(d));
				taskAllocation.getCats().add(dpt);

			}
		}
		String[] teamsId = teams.split(":");
		if (!teamsId.equals("")) {
			for (String d : teamsId) {
				if (d.equals("")) {
					continue;
				}
				Roleteam dpt = roleteamService.findById(Integer.valueOf(d));
				taskAllocation.getTeam().add(dpt);
				taskAllocation.setRoleteamByRoleteam(dpt);
			}
		}

		taskAllocationService.save(taskAllocation);
		return "list";
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String event_service() throws Exception {

		return "success";
	}

	public String change_request() throws Exception {
		return "success";
	}

	public String service_engineer() throws Exception {
		return "success";
	}

	public String update() throws Exception {

		String[] depId = departments.split(":");
		if (!depId.equals("")) {
			for (String d : depId) {
				if (d.equals("")) {
					continue;
				}
				Department dpt = departmentService.findDepartmentById(Integer
						.valueOf(d));
				taskAllocation.getDepartment().add(dpt);

			}
		}
		String[] catsId = cats.split(":");
		if (!catsId.equals("")) {
			for (String d : catsId) {
				if (d.equals("")) {
					continue;
				}
				ServiceCategory dpt = serviceCategoryService.findById(Integer
						.valueOf(d));
				taskAllocation.getCats().add(dpt);

			}
		}
		String[] teamsId = teams.split(":");
		if (!teamsId.equals("")) {
			for (String d : teamsId) {
				if (d.equals("")) {
					continue;
				}
				Roleteam dpt = roleteamService.findById(Integer.valueOf(d));
				taskAllocation.getTeam().add(dpt);
				taskAllocation.setRoleteamByRoleteam(dpt);
			}
		}

		taskAllocation.setId(taskAllocationId);
		taskAllocationService.update(taskAllocation);
		return "list";
	}
	private Roleteam roleteam;
	public Roleteam getRoleteam() {
		return roleteam;
	}

	public void setRoleteam(Roleteam roleteam) {
		this.roleteam = roleteam;
	}

	public String updateInput() throws Exception {
		actionURI = "update";

		taskAllocation = taskAllocationService.findById(taskAllocationId);
		if(taskAllocation.getTeam()!=null&& taskAllocation.getTeam().size()>0){
			for(Roleteam r:taskAllocation.getTeam()){
				roleteam=r;
				break;
			}
		}
		if(taskAllocation.getCats()!=null&&taskAllocation.getCats().size()>0){
			cats="";
			for(ServiceCategory cat:taskAllocation.getCats()){
				cats+=cat.getId();
				cats+=":";
			}
		}
		if(taskAllocation.getDepartment()!=null&&taskAllocation.getDepartment().size()>0){
			departments="";
			for(Department dept:taskAllocation.getDepartment()){
				departments+=dept.getId();
				departments+=":";
			}
		}
		return "success";
	}
	private String teamName;
	private String catsName;
	private String departmentsName;
	public String info() throws Exception {
		taskAllocation = taskAllocationService.findById(taskAllocationId);
		if(taskAllocation.getTeam()!=null&& taskAllocation.getTeam().size()>0){
			for(Roleteam r:taskAllocation.getTeam()){
				roleteam=r;
				teamName=r.getName();
				break;
			}
		}
		if(taskAllocation.getCats()!=null&&taskAllocation.getCats().size()>0){
			catsName="";
			for(ServiceCategory cat:taskAllocation.getCats()){
				catsName+=cat.getItemZh();
				catsName+=",";
			}
		}
		if(taskAllocation.getDepartment()!=null&&taskAllocation.getDepartment().size()>0){
			departmentsName="";
			for(Department dept:taskAllocation.getDepartment()){
				departmentsName+=dept.getName();
				departmentsName+=",";
			}
		}
		return "success";
	}
	public String delete() throws Exception {
		Integer id = new Integer(Integer.parseInt(getRequest().getParameter(
				"taskAllocationId")));
		taskAllocationService.deleteById(taskAllocationId);
		return "list";
	}

	public String top3() throws Exception {
		allServiceCategory = serviceCategoryService.findAllByType(type);
		if (taskAllocationId != null && !taskAllocationId.equals("")) {
			TaskAllocation tmpAllocation = taskAllocationService
					.findById(taskAllocationId);
			if (tmpAllocation != null) {
				List<ServiceCategory> lst1 = new ArrayList<ServiceCategory>(
						tmpAllocation.getCats());
				if (!lst1.isEmpty()) {
                      serviceCategoryList="";
					for (ServiceCategory o : lst1) {
						serviceCategoryList = serviceCategoryList + o.getId()+":";

					}

				} else {
					serviceCategoryList = null;
				}

			}
		}

		return "success";
	}

	public String top2() throws Exception {
		allDepartmentList = departmentService.findAll();
		
		if(taskAllocationId!=null&&!taskAllocationId.equals(""))
		{
			TaskAllocation tmpAllocation=taskAllocationService.findById(taskAllocationId);
			if(tmpAllocation!=null)
			{
				List<Department> lst12 =new ArrayList<Department>(tmpAllocation.getDepartment());
				if(!lst12.isEmpty())
				{
					
					departmentList="";
					for(Department d:lst12)
					{
						departmentList=departmentList+d.getId()+":";
						
					}
					
				}else{
					departmentList=null;
				}}
		}
		
		return "success";
	}
}
