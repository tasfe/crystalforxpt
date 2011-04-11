package com.combanc.itsm.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.DocumentService;
import com.combanc.itsm.service.ServiceLevelService;
import com.combanc.itsm.service.ServiceRequestService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.addressbookmanage.AddressBookManageService;
import com.combanc.itsm.service.task.TaskDepService;
import com.combanc.itsm.service.tree.TreeDateService;
import com.combanc.itsm.service.worklog.WorkLogService;
import com.combanc.itsm.util.Messages;

public class DepartmentAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer departmentId;
	private Department department;
	private List<Department> departmentList;
	private String actionURI;
	private DepartmentService departmentService;
	private Integer pid = 0;

	private String message;
	private TaskDepService taskDepService;
	private AssetsService assetsService;
	private ServiceLevelService serviceLevelService;
	private ServiceRequestService serviceRequestService;
	private UserService userService;
	private WorkLogService workLogService;
	private AddressBookManageService addressBookManageService;
	private DocumentService documentService;
	private static String LIST = "list"; //$NON-NLS-1$
	private String teamString;
	private TreeDateService treeDateService;
	private JSONArray jsonDepartsArray;
	private Integer parentId;
	private String parentName;
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getChildren(){
		System.out.println("DepartAction.getChildren()-->requestData: " + requestData);
		//---------------------------- 一、解析JSON请求数据
		JSONObject jsonRequest = null;
		int parentID = 0;
		try {
			jsonRequest = new JSONObject(requestData);
			parentID = jsonRequest.getInt("parentID");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		//---------------------------- 二、处理业务逻辑
		List<Department> departs = this.departmentService.findAllByPid(parentID);
		int level=this.departmentService.getLevel();
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (Department depart : departs) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", depart.getId());
				jsonDept.put("text", depart.getName());
				if(level!=0&&depart.getCode().length()==level){
					jsonDept.put("nt", 1);
				}
				jsonResult.put(jsonDept);
			}
		}catch (JSONException e) {
			
			e.printStackTrace();
		}
		responseData = jsonResult.toString();

		//---------------------------- 四、返回页面
		// 清空
		return "json_data";
	}
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

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public AddressBookManageService getAddressBookManageService() {
		return addressBookManageService;
	}

	public void setAddressBookManageService(
			AddressBookManageService addressBookManageService) {
		this.addressBookManageService = addressBookManageService;
	}

	public String top() throws Exception {
		departmentList = departmentService.findAll();
		return SUCCESS;
	}

	public String top2() throws Exception {
		departmentList = departmentService.findAll();
		return SUCCESS; //$NON-NLS-1$
	}

	public String top3() throws Exception {
		departmentList = departmentService.getAllDeptsOrderLevel();
		return SUCCESS; //$NON-NLS-1$
	}

	public String main() throws Exception {
		return SUCCESS; //$NON-NLS-1$
	}

	public String list() throws Exception {
		// if (this.getMessage() != null && !this.getMessage().equals(""))
		// {this.setMessage(OperationUtil.DEPARTMENT_DELETE);
		// }

		departmentList = departmentService.findAllByPid(pid);

		return SUCCESS; //$NON-NLS-1$
	}

	public String save() throws Exception {
		parentId=-1;
		parentName="";
		List tList = departmentService.findByName(department.getName());
		if (tList != null && tList.size() > 0) {
			message = "部门名称重复，请重新填写！";
			return INPUT;
		} else {
			departmentService.saveDepartment(department);
			pid = department.getPid();
			return LIST;

		}
	}

	public String addInput() throws Exception {
		actionURI = "save"; //$NON-NLS-1$
		return SUCCESS; //$NON-NLS-1$
	}

	public String updateInput() throws Exception {
		actionURI = "update"; //$NON-NLS-1$
		department = departmentService.findDepartmentById(departmentId);
		if(null!=department){
			parentId=department.getPid();
			parentName=departmentService.findDepartmentById(department.getPid())==null?"":departmentService.findDepartmentById(department.getPid()).getName();
		}
		return SUCCESS; //$NON-NLS-1$
	}

	public String update() throws Exception {

		Department pdepartment = departmentService
				.findDepartmentById(department.getId());
		pdepartment.setDescription(department.getDescription());
		List<Department> tList = departmentService.findByName(department
				.getName());
		if (tList == null || tList.isEmpty()) {
			pdepartment.setName(department.getName());
			pdepartment.setPid(department.getPid());
			departmentService.update(pdepartment);
			return LIST;
		} else if (tList.size() == 1) {
			Department d = tList.get(0);
			if (d.getId() .equals(department.getId()) ) {
				pdepartment.setName(department.getName());
				pdepartment.setPid(department.getPid());
				departmentService.update(pdepartment);
				return LIST;
			}
		}

		message = "部门名称重复，请重新填写！";
		departmentId=department.getId();
		return INPUT;

	}

	public String delete() throws Exception {
		pid = departmentService.findDepartmentById(departmentId).getPid();
		List list = departmentService.findAllByPid(departmentId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.0")); //$NON-NLS-1$
			return LIST;
		}
		list = assetsService.findByDepartment(departmentId); // 判断部门是否在别的表中有使用，如有则不能删除
		if (list != null & !list.isEmpty()) {
			// message="部门正在被资产使用，不允许删除！";
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.11")); //$NON-NLS-1$
			return LIST;
		}

		// list=serviceLevelService.findByDepartId(departmentId);
		// if(list.size()>0) {this.setMessage("departmentDelte"); return
		// "list";}

		list = serviceRequestService.findByDepartment(
				"departmentByRequestDept", departmentId); //$NON-NLS-1$
		if (list != null & !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.13")); //$NON-NLS-1$
			return LIST;
		}

		list = taskDepService.findByDep(departmentId);
		if (list != null & !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.14")); //$NON-NLS-1$
			return LIST;
		}

		list = userService.findByDepartment(departmentId);
		if (list != null & !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.15")); //$NON-NLS-1$
			return LIST;
		}

		list = workLogService.getLogsByDepartmentId(departmentId);
		if (list != null & !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.16")); //$NON-NLS-1$
			return LIST;
		}
		list = addressBookManageService
				.getAddressBookByDepartmentId(departmentId);
		if (list != null & !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.2")); //$NON-NLS-1$
			return LIST;
		}
		list = documentService.getDocumentsByDepId(departmentId);
		if (list != null & !list.isEmpty()) {
			this.setMessage(Messages
					.getString("com.combanc.itsm.action.DepartmentAction.1")); //$NON-NLS-1$
			return LIST;
		}
		departmentService.deleteById(departmentId);
		this.setMessage("删除成功！");
		return LIST;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public TaskDepService getTaskDepService() {
		return taskDepService;
	}

	public void setTaskDepService(TaskDepService taskDepService) {
		this.taskDepService = taskDepService;
	}

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public ServiceLevelService getServiceLevelService() {
		return serviceLevelService;
	}

	public void setServiceLevelService(ServiceLevelService serviceLevelService) {
		this.serviceLevelService = serviceLevelService;
	}

	public ServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public void setServiceRequestService(
			ServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public WorkLogService getWorkLogService() {
		return workLogService;
	}

	public void setWorkLogService(WorkLogService workLogService) {
		this.workLogService = workLogService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String departmentList() throws Exception {

		teamString = treeDateService.getTeamListString(0);

		return SUCCESS;
	}
	public JSONArray getJsonDepartsArray() {
		return jsonDepartsArray;
	}
	public void setJsonDepartsArray(JSONArray jsonDepartsArray) {
		this.jsonDepartsArray = jsonDepartsArray;
	}
}
