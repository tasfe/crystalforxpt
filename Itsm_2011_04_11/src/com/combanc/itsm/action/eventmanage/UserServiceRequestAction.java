package com.combanc.itsm.action.eventmanage;

import java.io.File;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.ServiceTran;
import com.combanc.itsm.pojo.TaskAllocation;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.ServiceCategoryService;
import com.combanc.itsm.service.ServiceRequestService;
import com.combanc.itsm.service.ServiceRequestStatisticService;
import com.combanc.itsm.service.ServiceTranService;
import com.combanc.itsm.service.TaskAllocationService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.CartogramInfo;
import com.combanc.itsm.util.DateHelp;
import com.combanc.itsm.util.FileUtility;
import com.combanc.itsm.util.OperationUtil;
import com.combanc.itsm.util.ServiceLv;
import com.combanc.itsm.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

public class UserServiceRequestAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private String actionURI;
	private String[] contentType;
	private File[] file;
	private int state;
	private String[] fileName;
	private String dlFileName;
	private String trueName;
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	private String info; // 用于界面显示提示信息
	private List<Accessory> accessoryList;
	private ServiceCategory serviceCategory;
	private List<ServiceCategory> serviceCategoryList;
	private ServiceRequest serviceRequest;
	private Integer serviceRequestId;
	private String requestNo;// 工单号
	private HttpServletRequest request;
	private List<ServiceRequest> serviceRequestList;
	private List<ServiceTran> serviceTranList;
	private static final int BUFFER_SIZE = 16 * 1024;
	private Users users;
	private int userId;
	private CartogramInfo cartogramInfo;
	private String flag;
	private int pid = 0;
	private int type;
	private ServiceCategoryService serviceCategoryService;
	private UserService userService;
	private ServiceTranService serviceTranService;
	private ServiceRequestService serviceRequestService;
	private FileUpDownService fileUpDownService;
	private EngineerServiceRequestAction engineerServiceRequestAction;
	private ServiceTran serviceTran;
	private List<ServiceRequest> relatedRequestList; // 相关联的工单
	private Timestamp timestamp = new Timestamp(new Date().getTime());
	private Integer timer; // 延后时间
	private String danwei; // 延后单位
	private TaskAllocationService taskAllocationService;
	private int serviceLvl;
	private String feedback;
	List<ServiceLv> serviceLvList;
	private RoleteamService roleteamService;
	private ServiceRequestStatisticService serviceRequestStatisticService;
	private String nowDate;
	public ServiceRequestStatisticService getServiceRequestStatisticService() {
		return serviceRequestStatisticService;
	}

	public void setServiceRequestStatisticService(
			ServiceRequestStatisticService serviceRequestStatisticService) {
		this.serviceRequestStatisticService = serviceRequestStatisticService;
	}

	/**
	 * @return the taskAllocationService
	 */
	public TaskAllocationService getTaskAllocationService() {
		return taskAllocationService;
	}

	/**
	 * @param taskAllocationService
	 *            the taskAllocationService to set
	 */
	public void setTaskAllocationService(
			TaskAllocationService taskAllocationService) {
		this.taskAllocationService = taskAllocationService;
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
	 * @return the danwei
	 */
	public String getDanwei() {
		return danwei;
	}

	/**
	 * @param danwei
	 *            the danwei to set
	 */
	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the timer
	 */
	public Integer getTimer() {
		return timer;
	}

	/**
	 * @param timer
	 *            the timer to set
	 */
	public void setTimer(Integer timer) {
		this.timer = timer;
	}

	/**
	 * @return the serviceLvList
	 */
	public List<ServiceLv> getServiceLvList() {
		return serviceLvList;
	}

	/**
	 * @param serviceLvList
	 *            the serviceLvList to set
	 */
	public void setServiceLvList(List<ServiceLv> serviceLvList) {
		this.serviceLvList = serviceLvList;
	}

	/**
	 * @return the serviceLvl
	 */
	public int getServiceLvl() {
		return serviceLvl;
	}

	/**
	 * @param serviceLvl
	 *            the serviceLvl to set
	 */
	public void setServiceLvl(int serviceLvl) {
		this.serviceLvl = serviceLvl;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback
	 *            the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * @return the trueName
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * @param trueName
	 *            the trueName to set
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * @return the dlFileName
	 */
	public String getDlFileName() {
		return dlFileName;
	}

	/**
	 * @param dlFileName
	 *            the dlFileName to set
	 */
	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	/**
	 * @return the accessoryList
	 */
	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	/**
	 * @param accessoryList
	 *            the accessoryList to set
	 */
	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
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

	public List<ServiceRequest> getRelatedRequestList() {
		return relatedRequestList;
	}

	public void setRelatedRequestList(List<ServiceRequest> relatedRequestList) {
		this.relatedRequestList = relatedRequestList;
	}

	/**
	 * @return the fileUpDownService
	 */
	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	/**
	 * @param fileUpDownService
	 *            the fileUpDownService to set
	 */
	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	/**
	 * 
	 */
	public String accept() throws Exception {// 接受
		engineerServiceRequestAction.accept();
		return "success";
	}

	public String solve() throws Exception {// 快速解决
		engineerServiceRequestAction.solve();
		return "success";
	}

	public String transmit() throws Exception {// 转交
		engineerServiceRequestAction.transmit();
		return "success";
	}

	public Users commons() {
		Users users = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		return users;
	}

	@Action(description = "指派工程师")
	public String assign() throws Exception {// 指派
		serviceTran.setUsersByServiceFrom(commons());
		serviceTran.setUsersByServiceTo(serviceRequest.getUsersByEngineerId());
		serviceTran.setOperatorTime(timestamp);
		serviceTran.setType(OperationUtil.ASSIGN_CN);
		serviceTran.setNote(serviceRequest.getCause());
		serviceTranService.save(serviceTran);
		serviceRequestService.assign(serviceRequest);
		return "success";
	}

	@Action(description = "退回请求")
	public String reback() throws Exception { // 退回
		serviceTran.setUsersByServiceFrom(commons());
		serviceTran.setOperatorTime(timestamp);
		serviceTran.setType(OperationUtil.RETURN_CN);
		serviceTranService.save(serviceTran);
		serviceRequestService.ret(serviceRequest);
		return "success";
	}

	@Action(description = "拒绝请求")
	public String refuse() throws Exception { // 拒绝
		serviceTran.setUsersByServiceFrom(commons());
		serviceTran.setOperatorTime(timestamp);
		serviceTran.setType(OperationUtil.REFUSE_CN);
		serviceTran.setNote(serviceRequest.getCause());
		serviceTranService.save(serviceTran);
		serviceRequestService.refuse(serviceRequest);
		return "success";
	}

	@Action(description = "添加用户反馈")
	public String feedback() throws Exception { // 添加用户反馈
		serviceTran.setUsersByServiceFrom(commons());
		serviceTran.setOperatorTime(timestamp);
		serviceTran.setType(OperationUtil.FEEDBACK_CN);
		serviceTran.setNote(serviceRequest.getFeedback());
		serviceTranService.save(serviceTran);
		serviceRequestService.feedback(serviceRequest);
		return "success";
	}

	@Action(description = "请求解决，完成")
	public String clo() throws Exception { // 完成
		serviceTran.setUsersByServiceFrom(commons());
		serviceTran.setOperatorTime(timestamp);
		serviceTran.setType(OperationUtil.CLOSE_CN);
		serviceTran.setNote(serviceRequest.getSolution());
		serviceTranService.save(serviceTran);
		serviceRequest.setFinishTime(serviceTran.getOperatorTime());
		serviceRequestService.clo(serviceRequest);
		return "success";
	}

	@Action(description = "承诺时间延期")
	public String delay() throws Exception {// 承诺时间延期
		serviceTran.setUsersByServiceFrom(commons());
		serviceTran.setOperatorTime(timestamp);
		serviceTran.setType(OperationUtil.DELAY_CN);
		serviceTran.setNote(OperationUtil.DELAY_CN);
		serviceTranService.save(serviceTran);
		serviceRequestService.delay(serviceRequest, timer, danwei);
		return "success";
	}

	public String asst() throws Exception {
		// serviceRequest=serviceRequestService.findServiceRequestById(serviceRequestId);
		return "success";
	}

	public String inci() throws Exception {
		// serviceRequest=serviceRequestService.findServiceRequestById(serviceRequestId);
		return "success";
	}

	private static void down(String url, String name) {
		/*
		 * OutputStream out = null; FileInputStream fin = null; try { fin = new
		 * FileInputStream(url);
		 * 
		 * int buf = 4096; byte buffer[] = new byte[buf];
		 * ServletActionContext.getResponse().setHeader("Content-disposition",
		 * "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
		 * 
		 * out= ServletActionContext.getResponse().getOutputStream();
		 * 
		 * 
		 * 
		 * for (int size = 0;(size = fin.read(buffer)) != -1;) {
		 * out.write(buffer, 0, size); }
		 * 
		 * out.flush();
		 * 
		 * 
		 * } catch (FileNotFoundException e) {
		 * 
		 * 
		 * 
		 * } catch (IOException e) {
		 * 
		 * 
		 * 
		 * } finally { if (fin != null) { try { fin.close();
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } if (out != null) { try { out.close();
		 * 
		 * // ServletActionContext.getPageContext().pushBody();
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * } }
		 */}

	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}

	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		return ipAddrStr;
	}

	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String categoryList() throws Exception {

		serviceCategoryList = serviceCategoryService
				.findAllByPid(serviceCategory.getId());
		serviceCategory = serviceCategoryService.findById(serviceCategory
				.getId());
		if (serviceCategoryList != null && !serviceCategoryList.isEmpty()) {
			return "success";
		} else {
			actionURI = "save";
			return "save";
		}
	}

	public String delete() throws Exception {
		serviceRequestService.deleteById(serviceRequestId);
		return "list";
	}

	@Action(description = "上传附件")
	public void fileUpload() {
		System.out.println("文件大小" + this.getFile().length);
		System.out.println("文件有" + this.getFile());
		// HttpServletRequest request = ServletActionContext.getRequest();
		// String[] paths = request.getParameterValues("file");
		// this.setFile(paths);
		fileName = fileName[0].toString().split("%");
		for (int i = 0; i < this.getFile().length; i++) {
			// System.out.println("================="
			// + fileName[i]
			// + "========="
			// + ServletActionContext.getServletContext().getRealPath(
			// "WebRoot/Upload"));
			String name = StringUtil.generateFileName(fileName[i + 1]);
			String url = ServletActionContext.getServletContext().getRealPath(
					"/Upload")
					+ "/" + name;
			File dstFile = new File(url);
			System.out.println("文件路径" + dstFile);
			FileUtility.copy(file[i], dstFile);
			Accessory accessory = new Accessory();
			accessory.setName(name);
			accessory.setTrueName(fileName[i + 1]);

			accessory.setTableId(serviceRequest.getId());
			accessory.setTableName("service_request");
			accessory.setUploadDate(serviceRequest.getSubmintTime());
			accessory.setUrl(url);
			fileUpDownService.addUpFileInfo(accessory);
		}

	}

	public String down() throws Exception {
		this.trueName = fileUpDownService.getAccessoryByName(dlFileName)
				.getTrueName();
		this.dlFileName = "/upload/" + this.dlFileName;

		return SUCCESS;

	}

	public InputStream getInputStream() {

		return ServletActionContext.getServletContext().getResourceAsStream(
				"/" + dlFileName);

	}

	public String init() throws Exception {
		serviceCategoryList = (List<ServiceCategory>) serviceCategoryService
				.findAllByPid(0);
		actionURI = "save";
		return "success";
	}

	public String requestlist() throws Exception {
		serviceRequestList = serviceRequestService.findAll();
		return SUCCESS;
	}

	@Action(description = "用户提交新请求并记录操作")
	public String save() throws Exception {
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		serviceRequest.setUsersByRequestUser(u);// 申请者ID
		serviceRequest.setDepartmentByRequestDept(u.getDepartment());// 申请者部门ID
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		serviceRequest.setSubmintTime(ts);// 操作的系统时间
		serviceRequest.setIp(getLocalIP());// 操作的IP
		serviceRequest.setRequestNo(StringUtil.getRequestNo());
		// ServiceCategory serviceCategory = new ServiceCategory();
		ServiceCategory sc = serviceCategoryService.findById(serviceCategory
				.getId());

		serviceRequest.setState(0);
		serviceRequest.setServiceCategory(sc);// 申请的服务类别
		if (serviceRequest.getUsersByEngineerId() != null) {
			Users engineer = userService.findUserById(serviceRequest
					.getUsersByEngineerId().getId());
			serviceRequest.setUsersByEngineerId(engineer);
			serviceRequest.setState(3);
		}
		serviceRequest.setPriorityLvl(serviceRequest
				.getSeverityTypByEmergency()
				* serviceRequest.getSeverityTypByEssential());
		ServiceTran sTran = new ServiceTran();
		sTran.setNote(serviceRequest.getMessage());
		sTran.setOperatorTime(serviceRequest.getSubmintTime());
		sTran.setRequNo(serviceRequest.getRequestNo());
		sTran.setServiceCategory(sc);
		sTran.setUsers(u.getUsername());
		sTran.setUsersByServiceFrom(u);
		sTran.setType(OperationUtil.SUBMIT_CN);
		serviceTranService.save(sTran);
		// 自动分配
		int i = attribute(u.getDepartment().getId(), serviceRequest
				.getServiceCategory().getId());
		if (i != 0) {
			Users us = new Users();
			us.setId(i);
			serviceRequest.setUsersByEngineerId(us);
			serviceRequest.setAssignTime(ts);
			sTran.setUsersByServiceTo(us);
			serviceRequest.setState(1);

			ServiceTran sTran2 = new ServiceTran();
			sTran2.setNote(serviceRequest.getMessage());
			sTran2.setOperatorTime(new Timestamp(date.getTime()));
			sTran2.setRequNo(serviceRequest.getRequestNo());
			sTran2.setServiceCategory(sc);
			sTran2.setUsers("系统自动分派");
			sTran2.setUsersByServiceFrom(u);
			sTran2.setType(OperationUtil.ASSIGN_CN);
			serviceTranService.save(sTran2);
		}

		// serviceTranService.saveOrUpdate(sTran);
		// serviceRequest.getServiceTransSet().add(sTran);
		// serviceRequest.getServiceTransSet().addAll(serviceTranService.findAllByrequNo(sTran.getRequNo()));

		serviceRequestService.saveServiceRequest(serviceRequest);
		serviceRequest = serviceRequestService
				.findServiceRequestByRequestNo(serviceRequest.getRequestNo());
		if (this.getFile() != null) {
			fileUpload();// 调用上传文件方法
		}

		// 保存操作记录

		return "init";
	}

	
	private List<String> dailyInc;

	public List<String> getDailyInc() {
		return dailyInc;
	}

	public void setDailyInc(List<String> dailyInc) {
		this.dailyInc = dailyInc;
	}

	@Action(description = "IT经理列出所有待处理请求")
	public String itmanager() throws Exception {
		/*
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		serviceRequestList = serviceRequestService.findByEngineerId(u.getId());
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		nowDate=simpleDateFormat.format(calendar.getTime());
		*/
		return "success";
	}

	public String update() throws Exception {
		serviceRequestService.update(serviceRequest);
		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		serviceRequest = serviceRequestService
				.findServiceRequestById(serviceRequestId);
		return "success";
	}

	@Action(description = "查询指定条件下的工单")
	public String query() throws Exception { // 根据条件查询工单
		this.pageBean = serviceRequestService.query(serviceRequest, pageSize,
				page);
		return "success";
	}

	@Action(description = "查询指定状态下的工单")
	public String query2() throws Exception { // 查询指定状态工单

		this.pageBean = serviceRequestService.findByState(serviceRequest
				.getState(), pageSize, page);
		return "success";
	}

	@Action(description = "用户查询服务请求")
	public String tracelist() throws Exception {
		Users user = (Users) ActionContext.getContext().getSession()
				.get("user");
		if (user == null)
			return "error";
		request = ServletActionContext.getRequest();

		/*
		 * String pageSize = request.getParameter("pageSize"); if (pageSize
		 * ==null|| "".equals(pageSize)) {
		 * 
		 * pageSize="10"; } request.setAttribute("pageSize", pageSize); //
		 * 分页的pageBean,参数pageSize表示每页显示记录数,page为当前页
		 */
		String condition;
		if (pageSize < 10) {
			pageSize = 10;
		}
		condition = "and state=" + state + " and usersByRequestUser="
				+ user.getId();
		this.pageBean = serviceRequestService.queryForPage(Integer
				.valueOf(pageSize), page, condition);

		return "success";
	}

	@Action(description = "查看事件详细情况")
	public String show() throws Exception {// IT经理查看服务请求
		serviceRequest = serviceRequestService
				.findServiceRequestById(serviceRequest.getId());
		accessoryList = fileUpDownService.getAccessorys("service_request",
				serviceRequest.getId()); // 和工单相关联的附件列表
		ServiceTran serviceTran = new ServiceTran();
		serviceTran.setRequNo(serviceRequest.getRequestNo());
		serviceTran.setType(OperationUtil.TRANSMIT_CN);
		if (serviceRequest.getUsersByEngineerId() != null) {
			relatedRequestList = serviceRequestService
					.findByEngineerId(serviceRequest.getUsersByEngineerId()
							.getId());
		}
		serviceTranList = serviceTranService.findByExample(serviceTran); // 和工单（事件）相关联的转交记录
		int state = serviceRequest.getState();
		if (state == 0) {
			this.setInfo("该服务请求没有分配任务，具备“指派任务”权限的工程师或相关责任人均可进行任务分配。");

		} else if (state == 1) {
			this.setInfo("该服务请求正在等待工程师\""
					+ serviceRequest.getUsersByEngineerId().getTruename()
					+ "\"响应并受理。");

		} else if (state == 2) {
			this.setInfo("该服务请求正在由工程师\""
					+ serviceRequest.getUsersByEngineerId().getTruename()
					+ "\"处理中。");

		} else if (state == 4) {
			this.setInfo("该服务请求已经被拒绝，处于关闭状态。相关责任人可以在七天内将被拒绝的请求重新开放。");

		} else if (state == 5) {
			this.setInfo("该服务请求已处理完成，正在等待申请人\""
					+ serviceRequest.getUsersByRequestUser().getTruename()
					+ "\"反馈。");
		} else if (state == 6) {
			this.setInfo("该服务请求已关闭。");

		} else {
			this.setInfo("该服务请求暂时被挂起。");

		}
		return "success";
	}

	@Action(description = "干预事件详细情况")
	public String interposeShow() {// IT经理干预服务请求
		// IT经理查看服务请求
		serviceRequest = serviceRequestService
				.findServiceRequestById(serviceRequest.getId());
		accessoryList = fileUpDownService.getAccessorys("service_request",
				serviceRequest.getId()); // 和工单相关联的附件列表
		ServiceTran serviceTran = new ServiceTran();
		serviceTran.setRequNo(serviceRequest.getRequestNo());
		serviceTran.setType(OperationUtil.TRANSMIT_CN);
		if (serviceRequest.getUsersByEngineerId() != null) {
			relatedRequestList = serviceRequestService
					.findByEngineerId(serviceRequest.getUsersByEngineerId()
							.getId());
		}
		serviceTranList = serviceTranService.findByExample(serviceTran); // 和工单（事件）相关联的转交记录
		int state = serviceRequest.getState();
		if (state == 0) {
			this.setInfo("该服务请求没有分配任务，具备“指派任务”权限的工程师或相关责任人均可进行任务分配。");

		} else if (state == 1) {
			this.setInfo("该服务请求正在等待工程师\""
					+ serviceRequest.getUsersByEngineerId().getTruename()
					+ "\"响应并受理。");

		} else if (state == 2) {
			this.setInfo("该服务请求正在由工程师\""
					+ serviceRequest.getUsersByEngineerId().getTruename()
					+ "\"处理中。");

		} else if (state == 4) {
			this.setInfo("该服务请求已经被拒绝，处于关闭状态。相关责任人可以在七天内将被拒绝的请求重新开放。");

		} else if (state == 5) {
			this.setInfo("该服务请求已处理完成，正在等待申请人\""
					+ serviceRequest.getUsersByRequestUser().getTruename()
					+ "\"反馈。");
		} else if (state == 6) {
			this.setInfo("该服务请求已关闭。");

		} else {
			this.setInfo("该服务请求暂时被挂起。");

		}
		return "success";

	}

	@Action(description = "用户查看工单进度")
	public String propressInfo() {
		serviceTranList = serviceTranService.findAllByrequNo(requestNo);

		return "success";
	}

	@Action(description = "IT经理查看工单进度")
	public String viewprogress() {
		serviceTranList = serviceTranService.findAllByrequNo(requestNo);
		return "success";
	}

	@Action(description = "用户查看历史工单进度")
	public String propressInfoHistory() {

		serviceTranList = serviceTranService.findAllByrequNo(requestNo);

		return "success";

	}

	@Action(description = "用户查询工单进度")
	public String showReqInfo() throws Exception {
		if (requestNo != null) {
			serviceRequest = serviceRequestService
					.findServiceRequestByRequestNo(requestNo);
			serviceTranList = serviceTranService.findAllByrequNo(requestNo);

			accessoryList = fileUpDownService
					.getAccessoryByTableNameAndTableId("service_request",
							serviceRequest.getId());

		}
		return "success";
	}

	@Action(description = "用户查询历史工单进度")
	public String showReqInfoHistory() throws Exception {
		if (requestNo != null) {
			serviceRequest = serviceRequestService
					.findServiceRequestByRequestNo(requestNo);
			serviceTranList = serviceTranService.findAllByrequNo(requestNo);

			accessoryList = fileUpDownService
					.getAccessoryByTableNameAndTableId("service_request",
							serviceRequest.getId());

		}
		return "success";

	}

	@Action(description = "查看申请人详细信息")
	public String showUserInfo() throws Exception {
		if (userId > 0) {

			users = userService.findUserById(userId);
			return "success";
		} else {

			return "fail";
		}

	}

	@Action(description = "提交用户满意度")
	public String doSatisfaction() throws Exception {
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		if (u != null) {

			if (requestNo != null) {

				serviceRequest = serviceRequestService
						.findServiceRequestByRequestNo(requestNo);
				if (serviceLvl > 0) {
					serviceRequest.setServiceLvl(serviceLvl);
					serviceRequest.setFeedback(feedback);
					serviceRequest.setState(6);
					serviceRequestService.update(serviceRequest);

					ServiceTran sTran = new ServiceTran();
					sTran.setNote(serviceRequest.getMessage());
					sTran.setOperatorTime(new Timestamp(System
							.currentTimeMillis()));
					sTran.setRequNo(serviceRequest.getRequestNo());
					sTran.setUsers(u.getUsername());
					sTran.setType(OperationUtil.SATISFACTION_CN);
					serviceTranService.save(sTran);
				}
				addActionMessage("提交反馈成功！");
				return SUCCESS;
			} else {
				addActionMessage("提交反馈失败！");
				return ERROR;

			}

		} else {

			return ERROR;
		}

	}

	public String showStatisfaction() throws Exception {
		if (requestNo != null) {
			serviceLvList = ServiceLv.getServiceLv();
		}
		return SUCCESS;
	}

	public Integer attribute(Integer dep, Integer cat) {
		int id = 0, v1 = 100000;
		List<TaskAllocation> list = taskAllocationService
				.getTaskAllocationsByDepIdAndCatId(dep, cat);
		for (int i = 0; i < list.size(); i++) {

			TaskAllocation taskAllocation = list.get(i);
			Set<Roleteam> teams = taskAllocation.getTeam();

			Iterator tIterator = teams.iterator();
			while (tIterator.hasNext()) {

				Roleteam team = (Roleteam) tIterator.next();
				Iterator it = team.getUserses().iterator();
				while (it.hasNext()) {
					Users u = (Users) it.next();
					int a = serviceRequestService.findByStateAndUserId(1,
							u.getId()).size();
					int b = serviceRequestService.findByStateAndUserId(2,
							u.getId()).size();
					if (a + b < v1) {
						v1 = a + b;
						id = u.getId();
					}
				}

			}
			// Roleteam roleteam = roleteamService.findById(list.get(i)
			// .getRoleteamByRoleteam().getId());

		}
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setActionURI(String actionURI) {

		this.actionURI = actionURI;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public void setServiceCategoryList(List<ServiceCategory> serviceCategoryList) {
		this.serviceCategoryList = serviceCategoryList;
	}

	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public void setServiceRequestId(Integer serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}

	public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
		this.serviceRequestList = serviceRequestList;
	}

	public void setServiceRequestService(
			ServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

	public void setServiceTranList(List<ServiceTran> serviceTranList) {
		this.serviceTranList = serviceTranList;
	}

	public void setServiceTranService(ServiceTranService serviceTranService) {
		this.serviceTranService = serviceTranService;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getActionURI() {
		return actionURI;
	}

	public String[] getContentType() {
		return contentType;
	}

	public File[] getFile() {
		return file;
	}

	public String[] getFileName() {
		return fileName;
	}

	public int getPage() {
		return page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}

	public List<ServiceCategory> getServiceCategoryList() {
		return serviceCategoryList;
	}

	public ServiceCategoryService getServiceCategoryService() {
		return serviceCategoryService;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public Integer getServiceRequestId() {
		return serviceRequestId;
	}

	public List<ServiceRequest> getServiceRequestList() {
		return serviceRequestList;
	}

	public ServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public List<ServiceTran> getServiceTranList() {
		return serviceTranList;
	}

	public ServiceTranService getServiceTranService() {
		return serviceTranService;
	}

	public int getState() {
		return state;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public CartogramInfo getCartogramInfo() {
		return cartogramInfo;
	}

	public void setCartogramInfo(CartogramInfo cartogramInfo) {
		this.cartogramInfo = cartogramInfo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setContentType(String[] contentType) {
		this.contentType = contentType;
	}

	public EngineerServiceRequestAction getEngineerServiceRequestAction() {
		return engineerServiceRequestAction;
	}

	public void setEngineerServiceRequestAction(
			EngineerServiceRequestAction engineerServiceRequestAction) {
		this.engineerServiceRequestAction = engineerServiceRequestAction;
	}

	public ServiceTran getServiceTran() {
		return serviceTran;
	}

	public void setServiceTran(ServiceTran serviceTran) {
		this.serviceTran = serviceTran;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
}
