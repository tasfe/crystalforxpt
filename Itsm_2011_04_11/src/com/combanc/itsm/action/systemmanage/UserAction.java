package com.combanc.itsm.action.systemmanage;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bsh.This;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Location;
import com.combanc.itsm.pojo.PersonalNotice;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.pojo.WorkLog;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.DocumentService;
import com.combanc.itsm.service.KnowledgeBaseService;
import com.combanc.itsm.service.LocationService;
import com.combanc.itsm.service.SchedualedTaskUserService;
import com.combanc.itsm.service.ServiceLevelService;
import com.combanc.itsm.service.ServiceRequestService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.addressbookmanage.AddressBookManageService;
import com.combanc.itsm.service.notice.PersonalNoticeService;
import com.combanc.itsm.service.task.TaskDepService;
import com.combanc.itsm.service.tree.TreeDateService;
import com.combanc.itsm.service.worklog.WorkLogService;
import com.combanc.itsm.util.Messages;
import com.combanc.itsm.util.StringUtil;

public class UserAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	private Integer userId;
	private Set<Roleteam> roleteams;
	private Users user;
	private List<Users> userList;
	private UserService userService;
	private DepartmentService departmentService;
	private LocationService locationService;
	private AssetsService assetsService;
	private ServiceRequestService serviceRequestService;
	private WorkLogService workLogService;
	private AddressBookManageService addressBookManageService;
	private DocumentService documentService;
	private PersonalNoticeService personalNoticeService;
	private SchedualedTaskUserService schedualedTaskUserService;
	private KnowledgeBaseService knowledgeBaseService;

	
	private String actionURI;
	private String oldPwd;
	private String newPwd;
	private String newPwdx;
	private String phone;
	private String cellphone;
	private String email;
	private List locations;
	private Location location;
	private int page;
	private PageBean pageBean;
	private int pageSize;
	private static String LIST = "list"; //$NON-NLS-1$
	private String message;
	private int start;

	private String keyword;
	private Integer departmentId;
	private String departmentName;
	private String idsList = ""; // 从数据库查到的用户所属角色ids
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public KnowledgeBaseService getKnowledgeBaseService() {
		return knowledgeBaseService;
	}

	public void setKnowledgeBaseService(KnowledgeBaseService knowledgeBaseService) {
		this.knowledgeBaseService = knowledgeBaseService;
	}

	public SchedualedTaskUserService getSchedualedTaskUserService() {
		return schedualedTaskUserService;
	}

	public void setSchedualedTaskUserService(
			SchedualedTaskUserService schedualedTaskUserService) {
		this.schedualedTaskUserService = schedualedTaskUserService;
	}

	public PersonalNoticeService getPersonalNoticeService() {
		return personalNoticeService;
	}

	public void setPersonalNoticeService(
			PersonalNoticeService personalNoticeService) {
		this.personalNoticeService = personalNoticeService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}



	public ServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public void setServiceRequestService(
			ServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

	public WorkLogService getWorkLogService() {
		return workLogService;
	}

	public void setWorkLogService(WorkLogService workLogService) {
		this.workLogService = workLogService;
	}

	public AddressBookManageService getAddressBookManageService() {
		return addressBookManageService;
	}

	public void setAddressBookManageService(
			AddressBookManageService addressBookManageService) {
		this.addressBookManageService = addressBookManageService;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

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
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * @return the locations
	 */
	public List getLocations() {
		return locations;
	}

	/**
	 * @param locations
	 *            the locations to set
	 */
	public void setLocations(List locations) {
		this.locations = locations;
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
	 * @return the locationService
	 */
	public LocationService getLocationService() {
		return locationService;
	}

	/**
	 * @param locationService
	 *            the locationService to set
	 */
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}
	public String nuserselect(){
		System.out.println("departmentName---"+this.departmentName);
		Department department=null;
		String condition="";
		if(this.departmentId!=null && this.departmentId!=-1){
			 department=this.departmentService.findDepartmentById(departmentId);
			 if(department!=null){
				condition+=" and department.code like '"+department.getCode()+"%'";
				
			 }
		}
		if(this.keyword!=null && !this.keyword.equals("")){
			keyword=StringUtil.decrateString(keyword);
			condition+= " and ( truename like '%" + keyword
			+ "%'  or username like '%" + keyword
			+ "%' )";
		}
		if(this.pageSize==0){
			pageSize=6;
		}
		pageBean=this.userService.queryForPage(pageSize,page,condition);
		return "success";
	}
	public String getUserBycondition(){
		System.out.println("UserAction.getUserBycondition()-->requestData: " + requestData);
		//---------------------------- 一、解析JSON请求数据
		JSONObject jsonRequest = null;
		int departId = -1;
		String keyword="";
		String condition="";
		try {
			jsonRequest = new JSONObject(requestData);
			if(!jsonRequest.getString("deptId").equals("")){
				departId = jsonRequest.getInt("deptId");
				Department department=this.departmentService.findDepartmentById(departId);
				if(department!=null){
					condition+=" and department.code like '"+department.getCode()+"%'";
				}
			}
			keyword=jsonRequest.getString("keyword");
			if(!keyword.equals("")){
				keyword=StringUtil.decrateString(keyword);
				condition+= " and ( truename like '%" + keyword
				+ "%'  or username like '%" + keyword
				+ "%' )";
			}
			
			if(jsonRequest.has("condition")){//自定义查询条件
				if(!jsonRequest.getString("condition").equals("")){
					condition+=jsonRequest.getString("condition");
				}
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		//---------------------------- 二、处理业务逻辑
		List<Users> users=this.userService.getUsersByCondition(pageSize, start, condition);
		int totalProperty=this.userService.getCount(condition);
		//---------------------------- 三、构建Json响应数据
		JSONObject jsonResult =new JSONObject();
		JSONArray topics = new JSONArray();
		try {
			for (Users user : users) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("id", user.getId());
				jsonDept.put("username", user.getUsername());
				jsonDept.put("truename", user.getTruename());
				jsonDept.put("email", user.getEmail());
				jsonDept.put("mobilephone", user.getPhone());
				jsonDept.put("departmentid",user.getDepartment().getId());
				jsonDept.put("departmentname",user.getDepartment().getName());
				topics.put(jsonDept);
			}
			jsonResult.put("topics", topics);
			jsonResult.put("totalProperty", totalProperty);
		}catch (JSONException e) {
			
			e.printStackTrace();
		}
		responseData = jsonResult.toString();

		//---------------------------- 四、返回页面
		// 清空
		return "json_data";
	}
	private int type=-1;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String list() throws Exception {
		if (0 == pageSize || "".equals(pageSize)) { //$NON-NLS-1$
			pageSize = 10;
		}
		request.setAttribute("pageSize", pageSize); //$NON-NLS-1$
		// 分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
		//setUserEnabled(type,userId);
		this.pageBean = userService.queryForPage(Integer.valueOf(pageSize),
				page, user,keyword);

		return SUCCESS;
	}
	public void setUserEnabled(int type,Integer userId){
		if(type==-1 ||userId==null){
			return ;
		}
		try{
			Users users=this.userService.findUserById(userId);
			if(null!=users){
				if(type==1){
					//users.setEnabled(0);
				}else{
					if(type==0){
						//users.setEnabled(1);
					}
				}
				this.userService.update(users);
				
			}
		}catch (Exception e) {
			return;
		}
	}
	@SuppressWarnings("unchecked")
	@Action(description = "添加或保存用户信息")
	public String save() throws Exception {
		

		user.setDepartment(departmentService.findDepartmentById(user
				.getDepartment().getId()));
		user.setLocation(locationService.findlocationById(user.getLocation()
				.getId()));
		if (!idsList.equals("")) {
			String[] s = idsList.split(",");
			for (int i = 0; i < s.length; i++) {
				Roleteam u = new Roleteam();
				u.setId((Integer) (Integer.parseInt(s[i])));
				user.getRoleteams().add(u);
			}
		}
		userService.getUserDAO().save(user);
		return "list"; //$NON-NLS-1$
	}

	@Action(description = "添加或保存用户信息")
	public String save2() throws Exception {
		if (user.getManagerId() == -1)
			user.setManagerId(null);
		userService.saveOrUpdate(user);
		return SUCCESS;
	}

	public String addInput() throws Exception {
		actionURI = "save"; //$NON-NLS-1$
		return SUCCESS;
	}

	public String addInput2() throws Exception {
		actionURI = "save2"; //$NON-NLS-1$
		return SUCCESS;
	}

	public String updateInput() throws Exception {
		actionURI = "update"; //$NON-NLS-1$
		user = userService.findUserById(userId);
		if(user!=null&&user.getRoleteams()!=null&&user.getRoleteams().size()>0){
			Set<Roleteam> set = user.getRoleteams();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				Roleteam temp = (Roleteam) it.next();
				idsList = idsList + temp.getId() + ",";
			}
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String update() throws Exception {
		if (!idsList.equals("")) {
			String[] s = idsList.split(",");
			for (int i = 0; i < s.length; i++) {
				Roleteam u = new Roleteam();
				u.setId((Integer) (Integer.parseInt(s[i])));
				user.getRoleteams().add(u);
			}
		}
		userService.update(user);
		return SUCCESS;
	}

	public String delete() throws Exception {

		List list = assetsService.findByUserId(userId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.1")); //$NON-NLS-1$
			return SUCCESS;
		}
		list = documentService.getDocumentsByUserId(userId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.2")); //$NON-NLS-1$
			return SUCCESS;
		}
//		PersonalNotice pNotice = personalNoticeService.findByUserId(userId);
//		if (pNotice != null) {
//			// pNotice.setUserId(null);
//			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.3")); //$NON-NLS-1$
//			return SUCCESS;
//		}
		list =personalNoticeService.listPersonalNoticeByUserId(userId);
		if(list!=null&&!list.isEmpty()){
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.3")); //$NON-NLS-1$
			return SUCCESS;
		}
		list = schedualedTaskUserService.findByUserId(userId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.4")); //$NON-NLS-1$
			return SUCCESS;
		}
		list=serviceRequestService.findByEngineerId(userId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.5")); //$NON-NLS-1$
			return SUCCESS;
		}
		list =serviceRequestService.findByusersByOperatorId(userId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.6")); //$NON-NLS-1$
			return SUCCESS;
		}
		list =serviceRequestService.findByusersByusersByOriginatorId(userId);
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.7")); //$NON-NLS-1$
			return SUCCESS;
		}
		
		list =knowledgeBaseService.getByEngineerId(userId);
		
		if (list != null && !list.isEmpty()) {
			this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.com.combanc.itsm.action.systemmanage.UserAction.9")); //$NON-NLS-1$
			return SUCCESS;
		}
		
		
		List<WorkLog> listw =workLogService.getLogsByUsersId(userId);
		if (listw != null && !list.isEmpty()) {
			for(WorkLog o:listw)
			{
				o.setUsers(null);
				workLogService.update(o);
			}
			
		}
		userService.deleteById(userId);
		this.setMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.8")); //$NON-NLS-1$
		return SUCCESS;
	}

	public String personaldata() throws Exception {

		HttpSession session = getRequest().getSession();
		user = (Users) session.getAttribute("user"); //$NON-NLS-1$
		if(null!=user){
			user=this.userService.findUserById(user.getId());
		}
		this.phone=user.getPhone();
		this.cellphone=user.getCellphone();
		this.email=user.getEmail();
		return SUCCESS;
	}

	/**
	 * @return the oldPwd
	 */
	public String getOldPwd() {
		return oldPwd;
	}

	/**
	 * @param oldPwd
	 *            the oldPwd to set
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String changePwd() throws Exception {
		String resultString = ""; //$NON-NLS-1$
		HttpSession session = getRequest().getSession();
		user = (Users) session.getAttribute("user"); //$NON-NLS-1$
		user = userService.findUserById(user.getId());
		if (this.oldPwd != null && !this.oldPwd.equals("")) { //$NON-NLS-1$
			if(!user.getPassword().equals(this.oldPwd)){
				this.addFieldError("password", "原密码错误!"); //$NON-NLS-1$ //$NON-NLS-2$
				resultString = "failed"; //$NON-NLS-1$
				return resultString;
			}else{
				user.setPassword(this.newPwd);
			}

		}
		if (user != null) {
			user.setPhone(this.phone);
			user.setCellphone(cellphone);
			user.setEmail(email);
			userService.update(user);
			this.addActionMessage(Messages.getString("com.combanc.itsm.action.systemmanage.UserAction.10")); //$NON-NLS-1$
			resultString = SUCCESS;
		}
		return resultString;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public Set<Roleteam> getRoleteams() {
		return roleteams;
	}

	public void setRoleteams(Set<Roleteam> roleteams) {
		this.roleteams = roleteams;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
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

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	/**
	 * @return the newPwdx
	 */
	public String getNewPwdx() {
		return newPwdx;
	}

	/**
	 * @param newPwdx
	 *            the newPwdx to set
	 */
	public void setNewPwdx(String newPwdx) {
		this.newPwdx = newPwdx;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	// 新增的方法:得到所有用户的方法;
	public String listAllUser() throws Exception {
		// 从数据库中查询所有的用户;
		long count = this.userService.getUserCount();

		List<Users> list = this.userService.listUserAsc(0, (int) count);

		// 代表整个xml文档;
		Document document = DocumentHelper.createDocument();
		// 代表文档根元素;
		Element rootElement = document.addElement("users"); //$NON-NLS-1$
		// 增加一个注释;
		rootElement.addComment("This is a comment!"); //$NON-NLS-1$

		// Element userElement = rootElement.addElement("user");

		for (Users user : list) {
			String username = user.getUsername();
			Element userElement = rootElement.addElement("user");// 根据Users对象,创建Element; //$NON-NLS-1$
			// Element element = new Element("users");
			userElement.setText(username);// 设定内容;
			// rootElement.addContent(userElement);
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/xml;charset=utf-8"); //$NON-NLS-1$
		response.setHeader("Cache-Control", "no-cache");// 取消浏览器的缓存; //$NON-NLS-1$ //$NON-NLS-2$

		PrintWriter out = response.getWriter();

		// 按照格式化进行输出;
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8"); //$NON-NLS-1$
		// format.setIndent("    ");

		XMLWriter writer = new XMLWriter(out, format);
		writer.write(document);

		out.flush();// 清空缓存;
		out.close();// 关闭流;

		return null;

	}

	public String top2() throws Exception {

		locations = locationService.findAll();
		if (userId != null)
			location = userService.findUserById(userId).getLocation();

		return SUCCESS;
	}

	public String qurey() throws Exception {

		this.pageBean = userService.queryForPage(pageSize, page, user);

		return SUCCESS;
	}
	public String userlist()
	{
		this.pageBean = userService.queryForPage(pageSize, page, user);

		return SUCCESS;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getIdsList() {
		return idsList;
	}

	public void setIdsList(String idsList) {
		this.idsList = idsList;
	}
	


}