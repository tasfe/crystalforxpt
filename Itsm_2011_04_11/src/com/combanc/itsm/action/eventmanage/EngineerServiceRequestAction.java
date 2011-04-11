package com.combanc.itsm.action.eventmanage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.ServiceTran;
import com.combanc.itsm.pojo.TaskAllocation;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.SchedualedTaskDetailService;
import com.combanc.itsm.service.ServiceCategoryService;
import com.combanc.itsm.service.ServiceRequestService;
import com.combanc.itsm.service.ServiceTranService;
import com.combanc.itsm.service.TaskAllocationService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.dbsm.DBSMProxyService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.OperationUtil;
import com.combanc.itsm.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
@ SuppressWarnings("all")
public class EngineerServiceRequestAction extends BaseActionSupport implements
ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServiceRequestService serviceRequestService;
	private ServiceTranService serviceTranService;
	private FileUpDownService fileUpDownService;
	private TaskAllocationService taskAllocationService;
	private RoleteamService roleteamService;
	private SchedualedTaskDetailService schedualedTaskDetailService;
	private ServiceCategoryService serviceCategoryService;
	private ServiceRequest serviceRequest;
	private ServiceTran serviceTran;
	private UserService userService;
	private List<ServiceRequest> serviceRequestList;
	private List<ServiceTran> serviceTranList;
	private List<Accessory> accessoryList;
	private List<ServiceRequest> relatedRequestList; //相关联的工单
	private String actionURI;
	private Integer serviceRequestId;
	private Integer state;
	private PageBean pageBean;
	private int pageSize = 10;
	private int page;
	private String dlFileName;
	private String info;  //用于界面显示提示信息
	private String requestNo;// 工单号
	private Integer timer;  //延后时间
	private String danwei;  //延后单位
	private List<ServiceRequest> list0;//指派任务
	private List<ServiceRequest> list1; //状态为1的所有请求列表,受理
	private List<ServiceRequest> list2;//正在处理
	private List<ServiceRequest> list5;//反馈并关闭
	private List<ServiceRequest> list7;//重新激活
	private List<ServiceRequest> moreList;
	private List<SchedualedTaskDetail> listTask;  //待处理任务List
	private Integer statistic0;
	private Integer statistic1; //状态为1的所有请求数
	private Integer statistic2;
	private Integer statisticTask;
	private String message;   //界面显示提示信息
	private Integer flag;
	private Integer isNotice;

    private static final int BUFFER_SIZE = 16 * 1024 ;
    private File[] file;
    private String[] fileFileName;
    private String[] contentType;
    
    private static String STATE="state";
    
	Timestamp ts = new Timestamp(new Date().getTime());
	
	private String nowDate;
	
	private String associate_assets;
	
	private String startSubmitTime;
	private String endSubmitTime;
	private String startFinishTime;
	private String endFinishTime;
	
	
	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getAssociate_assets() {
		return associate_assets;
	}

	public void setAssociate_assets(String associate_assets) {
		this.associate_assets = associate_assets;
	}
	/*
	 * 来自监控系统的工单
	 */
	public boolean saveRequestFromMontor(ServiceRequest serviceRequestFromMonitor) throws Exception{
		if(null==serviceRequestFromMonitor){
			return false;
		}
		return true;
	}
	public String morelist() throws Exception{
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		nowDate=simpleDateFormat.format(calendar.getTime());
		if(null!=state && state!=-1){
			if(state==0){
				moreList=serviceRequestService.findByProperty(STATE, 0);
			}else{
				serviceRequestList = serviceRequestService.findByEngineerId(u.getId());	
				moreList=new ArrayList<ServiceRequest>();
				for(int i=0;i<serviceRequestList.size();i++) {
					ServiceRequest request=serviceRequestList.get(i);
					if(request.getState()==state){
						moreList.add(request);
					}
				}
			}
			return "request";
		}else{
			listTask=schedualedTaskDetailService.queryByUser(u.getId());
			return "task";
		}
	}
	public String init() throws Exception {
		actionURI = "save";
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		nowDate=simpleDateFormat.format(calendar.getTime());
		return "success";
	}
	
	@Action(description="列出所有已关闭或已拒绝请求")
	public String requestlist() throws Exception {
		ServiceRequest request=new ServiceRequest();
		request.setUsersByRequestUser(new Users());
		request.setUsersByEngineerId(null);
		request.setServiceCategory(new ServiceCategory());
//		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
//		request.setUsersByEngineerId(u);
		this.pageBean =serviceRequestService.query2(request, pageSize,page);
		this.actionURI="requestlist";
		return "success";
	}
	
	@Action(description="列出所有未关闭和未拒绝请求")
	public String tracelist() throws Exception {
		ServiceRequest request=new ServiceRequest();
		request.setUsersByRequestUser(new Users());
		request.setUsersByEngineerId(null);
		request.setServiceCategory(new ServiceCategory());
//		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
//		request.setUsersByEngineerId(u);
		this.pageBean =serviceRequestService.query(request, pageSize,page);		
		this.actionURI="tracelist";
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		nowDate=simpleDateFormat.format(calendar.getTime());
		String mess=this.getMessage();
		if(mess!=null&&!mess.equals("")) {
			if(mess.equals("save")) {this.setMessage(OperationUtil.SAVE);}
			else if(mess.equals("assign")){this.setMessage(OperationUtil.ASSIGN);}
			else if(mess.equals("delay")){this.setMessage(OperationUtil.DELAY);}
			else if(mess.equals("reAssign")){this.setMessage(OperationUtil.REASSIGN);}
			else if(mess.equals("accept")){this.setMessage(OperationUtil.ACCEPT);}
			else if(mess.equals("solve")){this.setMessage(OperationUtil.SOLVE);}
			else if(mess.equals("pause")){this.setMessage(OperationUtil.PAUSE);}
			else if(mess.equals("rouse")){this.setMessage(OperationUtil.ROUSE);}
			else if(mess.equals("recate")){this.setMessage(OperationUtil.RECATE);}
			else if(mess.equals("transmit")){this.setMessage(OperationUtil.TRANSMIT);}
			else if(mess.equals("transmiterror")){this.setMessage(OperationUtil.TRANSMITERROR);}
			else if(mess.equals("ret")){this.setMessage(OperationUtil.RET);}
			else if(mess.equals("refuse")){this.setMessage(OperationUtil.REFUSE);}
			else if(mess.equals("feedback")){this.setMessage(OperationUtil.FEEDBACK);}
			else if(mess.equals("clo")){this.setMessage(OperationUtil.CLO);}
			else {this.setMessage(OperationUtil.OPERATE);}
		}
		return "success";
	}
	private String visible;
	
	@Action(description="列出所有待处理请求")
	public String engineer() throws Exception {
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		visible="false";
		Map map=(Map) ActionContext.getContext().getSession().get("privilege");
		if(null!=map){
			if(map.containsKey("17")){
				String str=(String)map.get("17");
				if(str!=null&&!str.equals("")&&str.contains(",distribute,")){
					visible="true";
				}
			}
		}
		if(visible=="true"){
			list0=serviceRequestService.findByProperty(STATE, 0);
		}else{
			list0=new ArrayList<ServiceRequest>();
		}
		listTask=schedualedTaskDetailService.queryByUser(u.getId());
		serviceRequestList = serviceRequestService.findByEngineerId(u.getId());		
		list1=new ArrayList();
		list2=new ArrayList();
		list5=new ArrayList();
		list7=new ArrayList();
		for(int i=0;i<serviceRequestList.size();i++) {
			ServiceRequest request=serviceRequestList.get(i);
			if(request.getState()==1) list1.add(request);
			else if(request.getState()==2) list2.add(request);
			else if(request.getState()==5) list5.add(request);
			else if(request.getState()==7) list7.add(request);
		}	
		this.statistic0=(visible=="true"?list0.size():0);
		this.statistic1=list1.size();
		this.statistic2=list2.size();
		this.statisticTask=listTask.size();
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		nowDate=simpleDateFormat.format(calendar.getTime());
		return "success";
	}
	
	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}
	
	@Action(description="干预事件详细情况")
	public String intervene() throws Exception {
		this.setActionURI("intervene");
		serviceRequest=serviceRequestService.findServiceRequestById(serviceRequest.getId());
	 	accessoryList=fileUpDownService.getAccessorys("service_request", serviceRequest.getId());  //和工单相关联的附件列表
	 	ServiceTran serviceTran=new ServiceTran();
	 	serviceTran.setRequNo(serviceRequest.getRequestNo());
		serviceTran.setType(OperationUtil.TRANSMIT_CN);
		if(serviceRequest.getUsersByEngineerId()!=null) {
			relatedRequestList=serviceRequestService.findByEngineerId(serviceRequest.getUsersByEngineerId().getId());
		}
		serviceTranList=serviceTranService.findByExample(serviceTran);  //和工单（事件）相关联的转交记录
		this.message();
		int state=serviceRequest.getState();
		if(state==0) {
			this.setInfo(getText("serviceRequest.state.0"));
			return "success0";
		}
		else if(state==1){
			List list=new ArrayList();
			list.add(serviceRequest.getUsersByEngineerId().getTruename());
			this.setInfo(getText("serviceRequest.state.1",list));
			return "success0";
		}
		else if(state==2) {
			List list=new ArrayList();
			list.add(serviceRequest.getUsersByEngineerId().getTruename());
			this.setInfo(getText("serviceRequest.state.2",list));
			return "success0";
		}
		else if(state==4) {
			this.setInfo(getText("serviceRequest.state.4"));
			return "success4";
		}
		else if(state==5){
			List list=new ArrayList();
			list.add(serviceRequest.getUsersByEngineerId().getTruename());
			this.setInfo(getText("serviceRequest.state.5",list));
			return "success5";
		}
		else if(state==6) {
			this.setInfo(getText("serviceRequest.state.6"));
			return "success1";
		}
		else {
			this.setInfo(getText("serviceRequest.state.7"));
			return "success0";
		}
	}
	
	@Action(description="查看转交记录详细情况")
	public String showTran() throws Exception {
		serviceTran=serviceTranService.findById(serviceTran.getId());
		 return "success";
	}
	
	public void message(){
		String mess=this.getMessage();
		if(mess!=null&&!mess.equals("")) {
			if(mess.equals("saveError")) {this.setMessage(OperationUtil.SAVE_ERROR);}
			else if(mess.equals("assignError")){this.setMessage(OperationUtil.ASSIGN_ERROR);}
			else if(mess.equals("delayError")){this.setMessage(OperationUtil.DELAY_ERROR);}
			else if(mess.equals("reassignError")){this.setMessage(OperationUtil.ASSIGN_ERROR);}
			else if(mess.equals("acceptError")){this.setMessage(OperationUtil.ACCEPT_ERROR);}
			else if(mess.equals("solveError")){this.setMessage(OperationUtil.SOLVE_ERROR);}
			else if(mess.equals("pauseError")){this.setMessage(OperationUtil.PAUSE_ERROR);}
			else if(mess.equals("transmit_Error")){this.setMessage(OperationUtil.TRANSMIT_ERROR);}
			else if(mess.equals("retError")){this.setMessage(OperationUtil.RET_ERROR);}
			else if(mess.equals("refuseError")){this.setMessage(OperationUtil.REFUSE_ERROR);}
			else if(mess.equals("feedbackError")){this.setMessage(OperationUtil.FEEDBACK_ERROR);}
			else if(mess.equals("cloError")){this.setMessage(OperationUtil.CLO_ERROR);}
		}
	}
	
	@Action(description="查看请求详细情况")
	public String show() throws Exception {
		String result="success0";
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		serviceRequest=serviceRequestService.findServiceRequestById(serviceRequest.getId());
	 	if(serviceRequest!=null) accessoryList=fileUpDownService.getAccessorys("service_request", serviceRequest.getId());  //和工单相关联的附件列表
	 	ServiceTran serviceTran=new ServiceTran();
	 	serviceTran.setRequNo(serviceRequest.getRequestNo());
		serviceTran.setType(OperationUtil.TRANSMIT_CN);
		if(serviceRequest.getUsersByEngineerId()!=null) {
			relatedRequestList=serviceRequestService.findByEngineerId2(serviceRequest.getUsersByEngineerId().getId(),serviceRequest.getId());
		}
		serviceTranList=serviceTranService.findByExample(serviceTran);  //和工单（事件）相关联的转交记录	
		this.message();
		int state=serviceRequest.getState();		
		if(state==0) {
			this.setInfo(getText("serviceRequest.state.0"));
			result="success0";
		}
		else if(state==1&&serviceRequest.getUsersByEngineerId()!=null){
			List list=new ArrayList();
			list.add(serviceRequest.getUsersByEngineerId().getTruename());
			this.setInfo(getText("serviceRequest.state.1",list));
			result="success1";
		}
		else if(state==2&&serviceRequest.getUsersByEngineerId()!=null) {
			List list=new ArrayList();
			list.add(serviceRequest.getUsersByEngineerId().getTruename());
			this.setInfo(getText("serviceRequest.state.2",list));
			result="success2";
		}
		else if(state==4) {
			this.setInfo(getText("serviceRequest.state.4"));
			result="success4";
		}
		else if(state==5){
			List list=new ArrayList();
			list.add(serviceRequest.getUsersByEngineerId().getTruename());
			this.setInfo(getText("serviceRequest.state.5",list));
			result="success5";
		}
		else if(state==6) {
			this.setInfo(getText("serviceRequest.state.6"));
			result="success6";
		}
		else  if(state==7) {
			this.setInfo(getText("serviceRequest.state.7"));
			result="success7";
		}
		else result="success4";
		if(flag!=null&&flag==1) return "success4";//modify by jyxiao 建议使用int而不是其封装类
		if(u!=null&&u.getId()!=null&&serviceRequest.getUsersByEngineerId()!=null&&!serviceRequest.getUsersByEngineerId().getId().equals(u.getId())) {
			result="success4";
		}
		return result;
	}
	
	@Action(description="添加或保存事件请求")
	public String save() throws Exception {
		if(serviceRequest.getUsersByRequestUser().getId()==-1||serviceRequest.getSummary()==null||serviceRequest.getDescription()==null||
				serviceRequest.getSummary().trim().length()==0||serviceRequest.getDescription().trim().length()==0||
				serviceRequest.getServiceCategory().getId()==-1||serviceRequest.getSeverityTypByEmergency()==-1||serviceRequest.getSeverityTypByEssential()==-1) {
			this.setMessage(OperationUtil.SAVE_ERROR);
			return "input";
		}
		if(null!=this.getAssociate_assets()&&!this.getAssociate_assets().equals("")){
			String[] s = this.getAssociate_assets().split(",");
			for (int i = 0; i < s.length; i++) {
				AssetsBase u = new AssetsBase();
				u.setCode((Integer) (Integer.parseInt(s[i])));
				serviceRequest.getAssets().add(u);
			}
		}
		ServiceTran serviceTran=new ServiceTran();
		Users u = userService.findUserById(serviceRequest.getUsersByRequestUser().getId());
		serviceRequest.setDepartmentByRequestDept(u.getDepartment());//申请者部门ID
		serviceRequest.setUsersByOriginatorId(commons());
		String formatDate = new SimpleDateFormat("yyMMddHHmmss") .format(new Date());
		int random = new Random().nextInt(10000);
        serviceRequest.setRequestNo("E"+formatDate+random);
        serviceRequest.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
        serviceRequest.setIp(getLocalIP());//操作的IP 
        if(serviceRequest.getSubmintTime()==null){
        	serviceRequest.setSubmintTime(ts);
        }
//        if(serviceRequest.getUsersByEngineerId().getId()==-1) { //若没有手工指派，根据任务分配机制选择工程师
//        		int i=attribute(u.getDepartment().getId(),serviceRequest.getServiceCategory().getId());
//        		if(i!=0){
//        			Users us=new Users();        		
//        			us.setId(i);
//        			serviceRequest.setUsersByEngineerId(us);
//        			serviceRequest.setAssignTime(ts);
//        			serviceTran.setUsersByServiceTo(us);
//        		}	
//        }        
        if(serviceRequest.getSolution()!=null&&!serviceRequest.getSolution().trim().equals("")) {  //输入了解决方案，直接进入等待用户反馈
        	serviceRequest.setBeginTime(serviceRequest.getSubmintTime());
        	serviceRequest.setFinishTime(serviceRequest.getSubmintTime());
        	serviceRequest.setState(5);
//        	serviceRequest.setUsersByEngineerId(commons());
        	serviceRequest.setUsersByEngineerId(serviceRequest.getUsersByRequestUser());
        }
        else if(flag!=null&&flag==1&&serviceRequest.getUsersByEngineerId()!=null&&serviceRequest.getUsersByEngineerId().getId()!=-1){
        	serviceRequest.setState(1); //设置工单的初始状态为1，待受理 
        	serviceTran.setUsersByServiceTo(serviceRequest.getUsersByEngineerId());
        	if(this.isNotice==1){
        		sendMessage(userService.findUserById(serviceRequest.getUsersByEngineerId().getId()),serviceRequest.getServiceCategory().getId());
        	}
        }
	    else if(flag!=null&&flag==0&&serviceRequest.getAcceptEngineers()!=null&&!serviceRequest.getAcceptEngineers().equals("-1")){
	       Roleteam rl=roleteamService.findById(Integer.valueOf(serviceRequest.getAcceptEngineers()));
	       serviceRequest.setAcceptEngineers(null);
	       if(rl!=null&&rl.getTeamLeader()!=null) {
	    	   Users user=new Users(); user.setId(rl.getTeamLeader());
	    	   serviceRequest.setUsersByEngineerId(user);
	    	   serviceRequest.setState(1);
	    	   if(this.isNotice==1){
	    		   sendMessage(userService.findUserById(serviceRequest.getUsersByEngineerId().getId()),serviceRequest.getServiceCategory().getId());
	    	   }
	       }else {
	    	   serviceRequest.setState(0);
	       }
	    } else{
	    	 serviceRequest.setUsersByEngineerId(null);
		        serviceRequest.setState(0);  //设置工单的初始状态为0，待派单	
	    }
     //   serviceRequest.setPromiseTime(new Timestamp(serviceRequest.getSubmintTime().getTime()+3*86400000));  //承诺完成时间为提交后的三天整
		serviceRequestService.saveServiceRequest(serviceRequest); 		
		serviceRequest=serviceRequestService.findServiceRequestByRequestNo(serviceRequest.getRequestNo());
		 if(this.getFile()!=null){
	        	fileUpload(serviceRequest.getSubmintTime());//调用上传文件方法
	        } 
		 
		serviceTran.setRequNo(serviceRequest.getRequestNo());
		serviceTran.setServiceCategory(serviceRequest.getServiceCategory());
		serviceTran.setUsersByServiceFrom(u);
		serviceTran.setType(OperationUtil.SUBMIT_CN);		
		serviceTran.setOperatorTime(ts);
		serviceTranService.save(serviceTran);	 
		this.setMessage("save");
		return "success";
	}
	
	public Users getUserByRoleteam(String roleId) {
		Users user=null;
		if(roleId!=null&&!roleId.trim().equals("")) {
			Roleteam rl=roleteamService.findById(Integer.valueOf(roleId));
			if(rl!=null&&rl.getTeamLeader()!=null) {
				user=userService.findUserById(rl.getTeamLeader());
			}
		}		
		return user;
	}
	
	public Integer attribute(Integer dep, Integer cat) {
		int id=0,v1=100000;
		List<TaskAllocation> list=taskAllocationService.getTaskAllocationsByDepIdAndCatId(dep, cat);
		for(int i=0;i<list.size();i++) {
			Roleteam roleteam=roleteamService.findById(list.get(i).getRoleteamByRoleteam().getId());
			Iterator it=roleteam.getUserses().iterator();
			while(it.hasNext()) {
				Users u=(Users) it.next();
				int a=serviceRequestService.findByStateAndUserId(1, u.getId()).size();
				int b=serviceRequestService.findByStateAndUserId(2, u.getId()).size();
				if(a+b<v1) {
					v1=a+b;
					id=u.getId();
				}
			}
		}		
		return id;
	}
	
	public String updateInput() throws Exception {
		actionURI = "update";
		serviceRequest = serviceRequestService.findServiceRequestById(serviceRequestId);
		return "success";
	}
	
	public String update() throws Exception {
		serviceRequestService.update(serviceRequest);
		return "init";
	}
	public Users commons() {
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		return u;
	}
	
	@Action(description="指派工程师")
	public String assign() throws Exception {//指派
		if(flag!=null&&flag==1) {
			if(serviceRequest.getUsersByEngineerId()==null||serviceRequest.getUsersByEngineerId().getId()==null
					||serviceRequest.getUsersByEngineerId().getId()==-1||serviceRequest.getUsersByEngineerId().getId()==0) {
				this.setMessage("assignError");
				return "error";
			}
		}else if(serviceRequest.getAcceptEngineers()==null||serviceRequest.getAcceptEngineers().equals("-1")) {
			this.setMessage("assignError");
			return "error";
		}else {
			Users u=getUserByRoleteam(serviceRequest.getAcceptEngineers());  //获得该组的组长
			serviceRequest.setAcceptEngineers(null);
			if(u==null) return "error";
			else {
				serviceRequest.setUsersByEngineerId(u);
				if(true) {
					ServiceRequest temp=serviceRequestService.findServiceRequestById(serviceRequest.getId());
					sendMessage1(u,temp.getServiceCategory().getItemZh());
				}
			}
		}
		if(flag!=null&&flag==1&&this.isNotice==1){
			ServiceRequest temp=serviceRequestService.findServiceRequestById(serviceRequest.getId());
    		sendMessage1(userService.findUserById(serviceRequest.getUsersByEngineerId().getId()),temp.getServiceCategory().getItemZh());
    	}
		this.serviceTranRecode(commons(), serviceRequest.getUsersByEngineerId(), OperationUtil.ASSIGN_CN, serviceRequest.getCause(), ts);		
		serviceRequest.setAssignTime(ts);
		serviceRequest.setUsersByOperatorId(serviceTran.getUsersByServiceFrom());
		serviceRequestService.assign(serviceRequest);
		this.setMessage("assign");
		return "success";
	}
	
	public void sendMessage(Users u,Integer servCateId)  throws Exception {
		String message=OperationUtil.EVENT_MESSAGE;
		if(u!=null&&u.getCellphone()!=null&&!u.getCellphone().trim().equals("")) {
			if(servCateId!=null) {
				ServiceCategory sc=serviceCategoryService.findById(servCateId);
				if(sc!=null&&!sc.getItemZh().equals("")) {
					message=message+"事件类别为："+sc.getItemZh()+"。";
				}
			}
			ArrayList list=new ArrayList();
			list.add(u.getCellphone());
			DBSMProxyService.SendMessage(list,message);
		}
	}
	
	public void sendMessage1(Users u,String servCateName)  throws Exception {
		String message=OperationUtil.EVENT_MESSAGE;
		if(u!=null&&u.getCellphone()!=null&&!u.getCellphone().trim().equals("")) {
			if(servCateName!=null&&!servCateName.equals("")) {
				message=message+"事件类别为："+servCateName+"。";
			}
			ArrayList list=new ArrayList();
			list.add(u.getCellphone());
			DBSMProxyService.SendMessage(list,message);
		}
	}
	
	@Action(description="承诺时间延期")
	public String delay() throws Exception {//承诺时间延期
		if(this.getTimer()==null) {
			this.setMessage("delayError");
			return "error";
		}
		this.serviceTranRecode(commons(), null, OperationUtil.DELAY_CN,OperationUtil.DELAY_CN, ts);
		serviceRequestService.delay(serviceRequest,timer,danwei);
		this.setMessage("delay");
		return "success";
	}
	
	@Action(description="重新指派工程师")
	public String reAssign() throws Exception {//重新指派
		if(flag!=null&&flag==1) {
			if(serviceRequest.getUsersByEngineerId()==null||serviceRequest.getUsersByEngineerId().getId()==null||
					serviceRequest.getUsersByEngineerId().getId()==-1||serviceRequest.getUsersByEngineerId().getId()==0)  {
				this.setMessage("reassignError");
				return "error";
			}
		}else if(serviceRequest.getAcceptEngineers()==null||serviceRequest.getAcceptEngineers().equals("-1")) {
			this.setMessage("reassignError");
			return "error";	
		}else {
				Users u=getUserByRoleteam(serviceRequest.getAcceptEngineers());
				serviceRequest.setAcceptEngineers(null);
				if(u==null) return "error";
				else {
					serviceRequest.setUsersByEngineerId(u);
					if(true) {
						ServiceRequest temp=serviceRequestService.findServiceRequestById(serviceRequest.getId());
						sendMessage1(u,temp.getServiceCategory().getItemZh());
					}
				}
		}
		if(flag!=null&&flag==1&&this.isNotice==1){
			ServiceRequest temp=serviceRequestService.findServiceRequestById(serviceRequest.getId());
    		sendMessage1(userService.findUserById(serviceRequest.getUsersByEngineerId().getId()),temp.getServiceCategory().getItemZh());
    	}
		this.serviceTranRecode(commons(), serviceRequest.getUsersByEngineerId(), OperationUtil.REASSIGN_CN,serviceRequest.getCause(), ts);
		serviceRequestService.reAssign(serviceRequest);
		this.setMessage("reAssign");
		return "success";
	}
	
	@Action(description="工程师受理请求")
	public String accept() throws Exception {//接受
		if(serviceRequest.getPlan()==null||serviceRequest.getPlan().trim().length()==0) {
			this.setMessage("acceptError");
			return "error";
		}
		this.serviceTranRecode(commons(), serviceRequest.getUsersByEngineerId(), OperationUtil.ACCEPT_CN,serviceRequest.getPlan(), ts);
		serviceRequest.setBeginTime(serviceTran.getOperatorTime());
		serviceRequestService.accept(serviceRequest);
		this.setMessage("accept");
		return "success";
	}
	
	@Action(description="快速解决")
	public String solve() throws Exception { //快速解决
		if(serviceRequest.getSolution()==null||serviceRequest.getSolution().trim().length()==0||
				serviceRequest.getSummary()==null||serviceRequest.getSummary().trim().length()==0) {
			this.setMessage("solveError");
			return "error";
		}
		this.serviceTranRecode(commons(),null, OperationUtil.SOLVE_CN, OperationUtil.SOLVE_CN, ts);
		if(serviceRequest.getBeginTime()==null) serviceRequest.setBeginTime(serviceTran.getOperatorTime());
		serviceRequest.setFinishTime(serviceTran.getOperatorTime());
		serviceRequest.setUsersByEngineerId(commons());
		serviceRequestService.solve(serviceRequest);
		this.setMessage("solve");
		return "success";
	}
	
	@Action(description="请求挂起")
	public String pause() throws Exception { //挂起
		if(serviceRequest.getPauseCause()==null||serviceRequest.getPauseCause().trim().length()==0) {
			this.setMessage("pauseError");
			return "error";
		}
		this.serviceTranRecode(commons(),null, OperationUtil.PAUSE_CN,serviceRequest.getPauseCause(), ts);
		serviceRequest.setUsersByEngineerId(serviceTran.getUsersByServiceFrom());
		serviceRequestService.pause(serviceRequest);
		this.setMessage("pause");
		return "success";
	}
	
	@Action(description="唤醒被挂起的请求")
	public String rouse() throws Exception { //唤醒	
		this.serviceTranRecode(commons(),null, OperationUtil.ROUSE_CN,serviceTran.getNote(), ts);
		serviceRequestService.rouse(serviceRequest);
		this.setMessage("rouse");
		return "success";
	}
	
	@Action(description="更改请求类别")
	public String recate() throws Exception { //重新归类
		this.serviceTranRecode(commons(),null, OperationUtil.ALERT_CN,OperationUtil.ALERT_CN, ts);
		serviceRequestService.recate(serviceRequest);
		this.setMessage("recate");
		return "success";
	}
	
	@Action(description="转交请求")
	public String transmit() throws Exception { //转交
		if(flag!=null&&flag==1) {
			if(serviceRequest.getUsersByEngineerId()==null||serviceRequest.getUsersByEngineerId().getId()==null||
					serviceRequest.getUsersByEngineerId().getId()==-1||serviceRequest.getUsersByEngineerId().getId()==0)  {
				this.setMessage("transmit_Error");
				return "errorinput";
			}
		}else if(serviceRequest.getAcceptEngineers()==null||serviceRequest.getAcceptEngineers().equals("-1")) {
			this.setMessage("transmit_Error");
			return "errorinput";
		}else{
			Users u=getUserByRoleteam(serviceRequest.getAcceptEngineers());
			serviceRequest.setAcceptEngineers(null);
			if(u==null) {
				return "error";
			}else {
				serviceRequest.setUsersByEngineerId(u);
				if(true) {
					ServiceRequest temp=serviceRequestService.findServiceRequestById(serviceRequest.getId());
					sendMessage1(u,temp.getServiceCategory().getItemZh());
				}
			}
		}
		if(flag!=null&&flag==1&&this.isNotice==1){
			ServiceRequest temp=serviceRequestService.findServiceRequestById(serviceRequest.getId());
    		sendMessage1(userService.findUserById(serviceRequest.getUsersByEngineerId().getId()),temp.getServiceCategory().getItemZh());
    	}
		this.serviceTranRecode(commons(),serviceRequest.getUsersByEngineerId(), OperationUtil.TRANSMIT_CN,serviceRequest.getTransmitCause(), ts);
//		if(serviceTran.getUsersByServiceFrom()!=null&&serviceTran.getUsersByServiceTo()!=null&&
//				serviceTran.getUsersByServiceFrom().getUsername().equals(serviceTran.getUsersByServiceTo().getUsername())) {
//			this.setMessage("transmiterror");
//			return "error";
//		}
		serviceRequest.setTransmitTime(serviceTran.getOperatorTime());
		serviceRequestService.transmit(serviceRequest);
		if(this.getFile()!=null){
        	fileUpload(serviceTran.getOperatorTime());//调用上传文件方法
        } 
		this.setMessage("transmit");
		return "success";
	}
	
	@Action(description="退回请求")
	public String ret() throws Exception { //退回
		if(serviceRequest.getReturnCause()==null||serviceRequest.getReturnCause().trim().length()==0) {
			this.setMessage("retError");
			return "error";
		}
		this.serviceTranRecode(commons(),null, OperationUtil.RETURN_CN,serviceRequest.getReturnCause(), ts);
		serviceRequestService.ret(serviceRequest);
		this.setMessage("ret");
		return "success";
	}
	
	@Action(description="拒绝请求")
	public String refuse() throws Exception { //拒绝
		if(serviceRequest.getCause()==null||serviceRequest.getCause().trim().length()==0) {
			this.setMessage("refuseError");
			return "error";
		}
		this.serviceTranRecode(commons(),null, OperationUtil.REFUSE_CN,serviceRequest.getCause(), ts);
		serviceRequest.setUsersByEngineerId(serviceTran.getUsersByServiceFrom());
		serviceRequest.setBeginTime(serviceTran.getOperatorTime());
		serviceRequest.setFinishTime(serviceTran.getOperatorTime());
		serviceRequestService.refuse(serviceRequest);
		this.setMessage("refuse");
		return "success";
	}
	
	@Action(description="工程师添加用户反馈")
	public String feedback() throws Exception { //添加用户反馈
		if(serviceRequest.getFeedback()==null||serviceRequest.getFeedback().trim().length()==0) {
			this.setMessage("feedbackError");
			return "error";
		}
		this.serviceTranRecode(commons(),null, OperationUtil.FEEDBACK_CN,serviceRequest.getFeedback(), ts);
		serviceRequestService.feedback(serviceRequest);
		this.setMessage("feedback");
		return "success";
	}
	
	@Action(description="请求解决，完成")
	public String clo() throws Exception { //完成
		if(serviceRequest.getSolution()==null||serviceRequest.getSolution().trim().length()==0) {
			this.setMessage("cloError");
			return "error";
		}
		this.serviceTranRecode(commons(),null, OperationUtil.CLOSE_CN, OperationUtil.CLOSE_CN, ts);
		serviceRequest.setFinishTime(serviceTran.getOperatorTime());
		serviceRequestService.clo(serviceRequest);
		if(this.getFile()!=null){
        	fileUpload(serviceRequest.getFinishTime());//调用上传文件方法
        }
		this.setMessage("clo");
		return "success";
	}
	
	public void serviceTranRecode(Users from, Users to, String type, String note, Timestamp operatorTime) {
		serviceTran.setUsersByServiceFrom(from);
		serviceTran.setUsersByServiceTo(to);
		serviceTran.setType(type);
		serviceTran.setNote(note);
		serviceTran.setOperatorTime(operatorTime);
		serviceTranService.save(serviceTran);
	}
	
	public String delete() throws Exception {
		serviceRequestService.deleteById(serviceRequestId);
		return "list";
	}
	
	@Action(description="查询指定条件下的工单")
	public String query() throws Exception { //根据多个条件条件查询工单,跟踪服务
		if(this.startSubmitTime!=null&&!this.startSubmitTime.equals("")){
			serviceRequest.setStartDate(Timestamp.valueOf(this.startSubmitTime));
		}
		if(this.endSubmitTime!=null&&!this.endSubmitTime.equals("")){
			serviceRequest.setEndDate(Timestamp.valueOf(this.endSubmitTime));
		}
		this.pageBean = serviceRequestService.query(serviceRequest, pageSize,page);
		this.actionURI="query";
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		nowDate=simpleDateFormat.format(calendar.getTime());
		return "success";
	}
	
	@Action(description="查询指定条件下的工单")
	public String query4() throws Exception { //根据多个条件条件查询工单，服务历史
		if(this.startSubmitTime!=null&&!this.startSubmitTime.equals("")){
			serviceRequest.setStartDate(Timestamp.valueOf(this.startSubmitTime+" 00:00:00"));
		}
		if(this.endSubmitTime!=null&&!this.endSubmitTime.equals("")){
			serviceRequest.setEndDate(Timestamp.valueOf(this.endSubmitTime+" 00:00:00"));
		}
		if(this.startFinishTime!=null&&!this.startFinishTime.equals("")){
			serviceRequest.setBeginTime(Timestamp.valueOf(this.startFinishTime+" 00:00:00"));
		}
		if(this.endFinishTime!=null&&!this.endFinishTime.equals("")){
			serviceRequest.setFinishTime(Timestamp.valueOf(this.endFinishTime+" 00:00:00"));
		}
		this.pageBean = serviceRequestService.query2(serviceRequest, pageSize,page);
		this.actionURI="query4";
		return "success";
	}
	
	@Action(description="查询指定状态下的工单")
	public String query2() throws Exception { //查询指定状态下的工单
		this.pageBean = serviceRequestService.findByState(this.getState(), pageSize,page);
		this.actionURI="query2";
		return "success";
	}
	
	@Action(description="查询非指定状态下的工单")
	public String query3() throws Exception { //查询非指定状态下的工单
		this.pageBean = serviceRequestService.findByState2(this.getState(), pageSize,page);
		this.actionURI="query3";
		return "success";
	}
	
	@Action(description="获取所有转交记录")
	public String tranRecode(){ //转交记录
		serviceTran.setType(OperationUtil.TRANSMIT_CN);
		serviceTranList=serviceTranService.findByExample(serviceTran);
		return "success";
	}
	
	@Action(description ="查看工单进度")
	public String propress() {
		serviceTranList = serviceTranService.findAllByrequNo(requestNo);
		return "success";
	}
	
    public void fileUpload(java.sql.Timestamp timestamp) { 
    	for(int i=0;i<this.getFile().length;i++){ 
    		String fileName= StringUtil.generateFileName(fileFileName[i]);
    		String url=ServletActionContext.getServletContext().getRealPath("/Upload")+ "/" +fileName;
			File dstFile = new File(url);
			copy(file[i], dstFile);
			
			Accessory accessory=new Accessory();
			accessory.setName(fileName);
			accessory.setTrueName(fileFileName[i]);
			accessory.setTableId(serviceRequest.getId());
			accessory.setTableName("service_request");
			accessory.setUploadDate(timestamp);
			accessory.setUrl(url);
			fileUpDownService.addUpFileInfo(accessory);			
    	}
	}    
    public InputStream getDownloadFile() {
		return ServletActionContext.getServletContext().getResourceAsStream(this.getDlFileName());
	}
	
	public String download() {
		try{
			String url=ServletActionContext.getServletContext().getRealPath("/Upload")+ "/" +this.dlFileName;
			File   file=new   File(url); 
            if(!file.exists()||!file.isFile()) return ERROR;
			this.dlFileName="/upload/"+this.dlFileName;			
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				//输入到缓冲流
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int i = 0;
				while ((i=in.read(buffer)) != -1) {
					out.write(buffer,0,i);
					i=0;
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}    

	private void alertmessage(String message,String action){ 
		PrintWriter out;  
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("tipMessage", "message");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");  
		try {  
			out=response.getWriter();  
			out.println(" <script type='text/javascript' >");  
			out.println("alert('"+message+"');");
			out.println("window.location.href='"+action+".action'"); 
			out.println(" </script>");  
			out.flush();  
			out.close();
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  

	
	public static String getLocalIP(){   
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
	
	public ServiceRequestService getServiceRequestService() {
		return serviceRequestService;
	}

	public void setServiceRequestService(ServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public List<ServiceTran> getServiceTranList() {
		return serviceTranList;
	}

	public void setServiceTranList(List<ServiceTran> serviceTranList) {
		this.serviceTranList = serviceTranList;
	}

	public Integer getServiceRequestId() {
		return serviceRequestId;
	}

	public void setServiceRequestId(Integer serviceRequestId) {
		this.serviceRequestId = serviceRequestId;
	}
	
	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getContentType() {
		return contentType;
	}

	public void setContentType(String[] contentType) {
		this.contentType = contentType;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}

	public ServiceTranService getServiceTranService() {
		return serviceTranService;
	}

	public void setServiceTranService(ServiceTranService serviceTranService) {
		this.serviceTranService = serviceTranService;
	}

	public List<ServiceRequest> getServiceRequestList() {
		return serviceRequestList;
	}

	public void setServiceRequestList(List<ServiceRequest> serviceRequestList) {
		this.serviceRequestList = serviceRequestList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServiceTran getServiceTran() {
		return serviceTran;
	}

	public void setServiceTran(ServiceTran serviceTran) {
		this.serviceTran = serviceTran;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	public String getDlFileName() {
		return dlFileName;
	}

	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public List<ServiceRequest> getRelatedRequestList() {
		return relatedRequestList;
	}

	public void setRelatedRequestList(List<ServiceRequest> relatedRequestList) {
		this.relatedRequestList = relatedRequestList;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public Integer getTimer() {
		return timer;
	}

	public void setTimer(Integer timer) {
		this.timer = timer;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}
	
	public List<ServiceRequest> getList0() {
		return list0;
	}

	public void setList0(List<ServiceRequest> list0) {
		this.list0 = list0;
	}

	public List<ServiceRequest> getList1() {
		return list1;
	}

	public void setList1(List<ServiceRequest> list1) {
		this.list1 = list1;
	}

	public List<ServiceRequest> getList2() {
		return list2;
	}

	public void setList2(List<ServiceRequest> list2) {
		this.list2 = list2;
	}

	public List<ServiceRequest> getList5() {
		return list5;
	}

	public void setList5(List<ServiceRequest> list5) {
		this.list5 = list5;
	}

	public List<ServiceRequest> getList7() {
		return list7;
	}

	public void setList7(List<ServiceRequest> list7) {
		this.list7 = list7;
	}

	public Integer getStatistic1() {
		return statistic1;
	}

	public void setStatistic1(Integer statistic1) {
		this.statistic1 = statistic1;
	}

	public Integer getStatistic2() {
		return statistic2;
	}

	public void setStatistic2(Integer statistic2) {
		this.statistic2 = statistic2;
	}

	public TaskAllocationService getTaskAllocationService() {
		return taskAllocationService;
	}

	public void setTaskAllocationService(TaskAllocationService taskAllocationService) {
		this.taskAllocationService = taskAllocationService;
	}

	public RoleteamService getRoleteamService() {
		return roleteamService;
	}

	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

	public Integer getStatistic0() {
		return statistic0;
	}

	public void setStatistic0(Integer statistic0) {
		this.statistic0 = statistic0;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<SchedualedTaskDetail> getListTask() {
		return listTask;
	}

	public void setListTask(List<SchedualedTaskDetail> listTask) {
		this.listTask = listTask;
	}

	public SchedualedTaskDetailService getSchedualedTaskDetailService() {
		return schedualedTaskDetailService;
	}

	public void setSchedualedTaskDetailService(
			SchedualedTaskDetailService schedualedTaskDetailService) {
		this.schedualedTaskDetailService = schedualedTaskDetailService;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getIsNotice() {
		return isNotice;
	}

	public void setIsNotice(Integer isNotice) {
		this.isNotice = isNotice;
	}

	public ServiceCategoryService getServiceCategoryService() {
		return serviceCategoryService;
	}

	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public Integer getStatisticTask() {
		return statisticTask;
	}

	public void setStatisticTask(Integer statisticTask) {
		this.statisticTask = statisticTask;
	}

	public List<ServiceRequest> getMoreList() {
		return moreList;
	}

	public void setMoreList(List<ServiceRequest> moreList) {
		this.moreList = moreList;
	}

	public String getStartSubmitTime() {
		return startSubmitTime;
	}

	public void setStartSubmitTime(String startSubmitTime) {
		this.startSubmitTime = startSubmitTime;
	}

	public String getEndSubmitTime() {
		return endSubmitTime;
	}

	public void setEndSubmitTime(String endSubmitTime) {
		this.endSubmitTime = endSubmitTime;
	}

	public String getEndFinishTime() {
		return endFinishTime;
	}

	public void setEndFinishTime(String endFinishTime) {
		this.endFinishTime = endFinishTime;
	}

	public String getStartFinishTime() {
		return startFinishTime;
	}

	public void setStartFinishTime(String startFinishTime) {
		this.startFinishTime = startFinishTime;
	}

}
