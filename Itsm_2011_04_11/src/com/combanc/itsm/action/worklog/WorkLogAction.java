package com.combanc.itsm.action.worklog;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.htmlobj.WorkLogQurey;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.pojo.WorkLog;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.tree.TreeDateService;
import com.combanc.itsm.service.worklog.WorkLogService;

public class WorkLogAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private WorkLogService workLogService;
	private WorkLog workLog;
	private int id;
	private WorkLogQurey workLogQurey;
	private List<Department> departmentList;
	private String treeString;

	private DepartmentService departmentService;
	private TreeDateService treeDateService;
	private List hotUsersList;
	private List hotLogsList;
	private String actionURL="";
//	private HttpServletRequest request = null;
//    private HttpServletResponse response=null;



	/**
	 * @return the hotUsersList
	 */
	
	public List getHotUsersList() {
		return hotUsersList;
	}

	public String getActionURL() {
		return actionURL;
	}

	public void setActionURL(String actionURL) {
		this.actionURL = actionURL;
	}

	/**
	 * @param hotUsersList
	 *            the hotUsersList to set
	 */
	public void setHotUsersList(List hotUsersList) {
		this.hotUsersList = hotUsersList;
	}

	/**
	 * @return the hotLogsList
	 */
	public List getHotLogsList() {
		return hotLogsList;
	}

	/**
	 * @param hotLogsList
	 *            the hotLogsList to set
	 */
	public void setHotLogsList(List hotLogsList) {
		this.hotLogsList = hotLogsList;
	}

	/**
	 * @return the treeString
	 */
	public String getTreeString() {
		return treeString;
	}

	/**
	 * @param treeString
	 *            the treeString to set
	 */
	public void setTreeString(String treeString) {
		this.treeString = treeString;
	}

	/**
	 * @return the treeDateService
	 */
	public TreeDateService getTreeDateService() {
		return treeDateService;
	}

	/**
	 * @param treeDateService
	 *            the treeDateService to set
	 */
	public void setTreeDateService(TreeDateService treeDateService) {
		this.treeDateService = treeDateService;
	}

	private Integer pid = 0;

	private int page=1;
	private PageBean pageBean;
	private int pageSize = 10;

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}

	/**
	 * @return the departmentList
	 */
	public List<Department> getDepartmentList() {
		return departmentList;
	}

	/**
	 * @param departmentList
	 *            the departmentList to set
	 */
	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	/**
	 * @return the departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService
	 *            the departmentService to set
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @return the workLogQurey
	 */
	public WorkLogQurey getWorkLogQurey() {
		return workLogQurey;
	}

	/**
	 * @param workLogQurey
	 *            the workLogQurey to set
	 */
	public void setWorkLogQurey(WorkLogQurey workLogQurey) {
		this.workLogQurey = workLogQurey;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the pageBean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}

	/**
	 * @param pageBean
	 *            the pageBean to set
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	/**
	 * @return the workLog
	 */
	public WorkLog getWorkLog() {
		return workLog;
	}

	/**
	 * @param workLog
	 *            the workLog to set
	 */
	public void setWorkLog(WorkLog workLog) {
		this.workLog = workLog;
	}

	/**
	 * @return the workLogService
	 */
	public WorkLogService getWorkLogService() {
		return workLogService;
	}

	/**
	 * @param workLogService
	 *            the workLogService to set
	 */
	public void setWorkLogService(WorkLogService workLogService) {
		this.workLogService = workLogService;
	}

	public String main() throws Exception {
		treeString = treeDateService.getTreeString(null);

		System.out.println(treeString);
		// departmentList = departmentService.findAll();
		return "success";
	}

	public String top() throws Exception {

		treeString = treeDateService.getTreeString(null);

		System.out.println(treeString);
		// departmentList = departmentService.findAll();
		return "success";
	}

//	public void getChildrenXml() throws Exception
//	{
//		
//		request.setCharacterEncoding("utf-8");
//        response.setContentType("text/xml;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        List<Department> dplList=treeDateService.getChildrenXml(Integer.valueOf(id));
//
//        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        out.println("<nodes>");
//    for(int i=0;i<dplList.size();i++){
//       Department node = dplList.get(i);
//       out.println("<node nodeId='"+node.getId()+"' parentId='"+node.getPid()+"' hrefAddress='"+"list.action?workLogQurey.workLog.department.id="
//				+ node.getId() + "&type=1"+"'>"+node.getName());
//       Set<Users> uSet = node.getUserses();
//		Iterator<Users> iterator = uSet.iterator();
//
//		while (iterator.hasNext()) {
//			 out.println("<node nodeId='"+node.getId()+"' parentId='"+node.getPid()+"' hrefAddress='"+"list.action?workLogQurey.workLog.department.id="
//						+ node.getId() + "&type=1"+"'>"+node.getName());
//		}
//
//       
//       out.println("</node>");
//    }
//        out.println("</nodes>");
//    }

	public String save() {
		if (workLog != null) {
			Users u = (Users) (ServletActionContext.getRequest().getSession()
					.getAttribute("user"));
			workLog.setUsers(u);// 申请者ID
			workLog.setDepartment(u.getDepartment());// 申请者部门ID
			workLog.setLocation(u.getLocation());
			workLog.setTimesumbit(new Timestamp(new Date().getTime()));
			workLog.setWordpress(0);
			workLogService.save(workLog);
			workLogService.updateNumWorkLog(u);
			return SUCCESS;
		} else
			return ERROR;
	}

	public String update() {
		if (workLog != null) {
			WorkLog tmpLog=workLogService.getWorkLogById(workLog.getId());
			tmpLog.setContent(workLog.getContent());
			tmpLog.setNote(workLog.getNote());
			tmpLog.setTitle(workLog.getTitle());
			tmpLog.setType(workLog.getType());
			tmpLog.setTime(workLog.getTime());
			workLogService.update(tmpLog);
			return SUCCESS;
		} else
			return ERROR;
	}
	

	@Action(description = "删除工作日志")
	public String delete() {

		boolean ok = false;

		if (workLog != null) {

			ok = workLogService.deleteById(workLog.getId());

			if (ok)

				return SUCCESS;

			else {

				return ERROR;
			}

		} else

			return ERROR;
	}

	// 列表显示;

	public String list() throws Exception {

		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		this.pageBean = workLogService.query(u, workLogQurey, pageSize, page,
				pid);

		return "success";

	}

	public String query() throws Exception {

		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));

		this.pageBean = workLogService.query(u, workLogQurey, pageSize, page,
				pid);

		return "success";

	}
	public String myquery() throws Exception {

		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));

		this.pageBean = workLogService.myquery(u, workLogQurey, pageSize, page,
				pid);

		return "success";

	}

	public String addInPut() throws Exception {
		
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		if (workLog != null) {
			actionURL="update";
			workLog = workLogService.getWorkLogById(workLog.getId());

		} else {
			actionURL="save";
			workLog = new WorkLog();
			if (u != null)
				workLog.setTitle(workLogService.getTitle(u));
		}

		return SUCCESS;
	}

	public String show() throws Exception {

		workLog = workLogService.getWorkLogById(workLog.getId());
		workLog.setWordpress(workLog.getWordpress()+1);
        workLogService.update(workLog);
		return SUCCESS;
	}

	public String hot() throws Exception {
		hotUsersList = workLogService.getHotUsers();
		hotLogsList = workLogService.getHotLogs();
		return SUCCESS;
	}
	public String departmentList() throws Exception
	{
		return SUCCESS;
	}

}
