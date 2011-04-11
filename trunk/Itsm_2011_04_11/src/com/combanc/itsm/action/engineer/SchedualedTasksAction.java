package com.combanc.itsm.action.engineer;


import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.Schedualed;
import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.SchedualedTaskUser;
import com.combanc.itsm.pojo.SchedualedTasks;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.ServiceTran;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.SchedualedTaskDetailService;
import com.combanc.itsm.service.SchedualedTaskService;
import com.combanc.itsm.service.SchedualedTaskUserService;
import com.combanc.itsm.service.ServiceTranService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.DateUtil;
import com.combanc.itsm.util.DateUtilSchedule;
import com.combanc.itsm.util.FileStorage;
import com.combanc.itsm.util.FileUtility;
import com.combanc.itsm.util.StringUtil;
import com.combanc.itsm.util.TimeUtil;

public class SchedualedTasksAction extends BaseActionSupport implements
		ServletRequestAware {

	private SchedualedTasks schedualedTasks;
	private SchedualedTaskDetail schedualedTaskDetail;
	private SchedualedTaskUser schedualedTaskUser;
	private List<SchedualedTasks> schedualedTasksList;
	private List<SchedualedTaskDetail> schedualedTaskDetails;
	private List<List<SchedualedTaskDetail>> schecualedTaskDetailList;
	private String members;
	private List<Users> userList;
	private List<ServiceTran> serviceTranList;
	private List relatedRequestList; //相关联的工单
	
	private SchedualedTaskService schedualedTaskService;
	private SchedualedTaskDetailService schedualedTaskDetailService;
	private FileUpDownService fileUpDownService;
	private UserService userService;
	private SchedualedTaskUserService schedualedTaskUserService;
	private ServiceTranService serviceTranService;
	
	private String content;
	private String dateStart;
	private String dateEnd;
	private int flag;
	private String curDate;
	private List<Accessory> accessoryList;
	private String info;
	private Integer id;
	private int accessoryId;
	private String actionURI;
	private Map weekMap;
	private String dlFileName;
	private Schedualed schedualed;
	private String solution;
	private ServiceTran serviceTran;
	
	private static final long serialVersionUID = 1L;
	private PageBean pageBean;
	private int pageSize = 10;
	private int page;
	private static final int BUFFER_SIZE = 16 * 1024;
	private File[] file;
	private String[] fileFileName;
	private String[] contentType;

	private static final String WAIT = "wait";
	private static final String WORK = "work";
	private static final String FINI = "fini";
	private static final String SCHEDUALEDTASK="schedualed_tasks";
	
	public String add() throws Exception {
		return "success";
	}
	
	public String list() throws Exception{
		this.pageBean =schedualedTaskService.queryForPage(pageSize,page,null);			
		List list=this.pageBean.getList();	
		if(list.size()>0) count(list);
		this.setActionURI("list");
		return "success";
	}
	
	public void count(List list) throws Exception{
		for(int i=0;i<list.size();i++) {
			SchedualedTasks st= (SchedualedTasks) list.get(i);
			Set set=st.getSchedualedTaskDetails();
			Iterator it=set.iterator();
			while(it.hasNext()) {				
				SchedualedTaskDetail std=(SchedualedTaskDetail) it.next();
				if(std.getState().equals(WAIT)) st.setSum1(st.getSum1()+1);
				else if(std.getState().equals(WORK)) st.setSum2(st.getSum2()+1);
				else st.setSum3(st.getSum3()+1);
			}
		}
	}
	
	public String query() throws Exception{
		this.pageBean =schedualedTaskService.queryForPage(pageSize,page,schedualedTasks);
		List list=this.pageBean.getList();	
		if(list.size()>0) count(list);
		this.setActionURI("query");
		return "success";
	}
	
	public String week() throws Exception{
		if(flag==1){
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date ts1=df.parse(dateStart);
			Date ts2=df.parse(dateEnd);
			ts1.setTime(TimeUtil.getNextDay(ts1.getTime(), 7));
			ts2.setTime(TimeUtil.getNextDay(ts2.getTime(), 7));
			this.setDateStart(df.format(ts1));
			this.setDateEnd(df.format(ts2));
		}else if(flag==-1){
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date ts1=df.parse(dateStart);
			Date ts2=df.parse(dateEnd);
			ts1.setTime(TimeUtil.getNextDay(ts1.getTime(), -7));
			ts2.setTime(TimeUtil.getNextDay(ts2.getTime(), -7));
			this.setDateStart(df.format(ts1));
			this.setDateEnd(df.format(ts2));
		}else if(flag==0) {
			TimeUtil tu=new TimeUtil();
			this.setDateStart(tu.getMondayOFWeek());
			this.setDateEnd(tu.getCurrentWeekday());
		}
		schedualedTaskDetails=schedualedTaskDetailService.queryByWeek(dateStart,dateEnd);
		if(schedualedTaskDetails.size()>0) {
			List array[]=new ArrayList[7];
			for(int k=0;k<array.length;k++) {
				array[k]=new ArrayList<SchedualedTaskDetail>();
			}
			Timestamp ts=schedualedTaskDetails.get(0).getSchedualedTime();
			for(int i=0;i<schedualedTaskDetails.size();i++){
				SchedualedTaskDetail scd=schedualedTaskDetails.get(i);
				int b=DateUtil.show2(ts.getTime(),scd.getSchedualedTime().getTime());
				if(b>=0&&b<=6) {
					array[b].add(scd);
				}
			}
			schecualedTaskDetailList=new ArrayList<List<SchedualedTaskDetail>>();
			for(int j=0;j<array.length;j++) {
				if(array[j].size()>0)
				schecualedTaskDetailList.add(array[j]);
			}
		}
		return "success";
	}
	
	public String calendar() throws Exception{
		return "success";
	}
	
	public String work() throws Exception{
		schedualedTaskDetails=schedualedTaskDetailService.queryByCalendar(dateStart);
		return "success";
	}
	
	public String show() throws Exception{
		String result="success3";
		schedualedTaskDetail=schedualedTaskDetailService.findById(schedualedTaskDetail.getId());
		accessoryList=fileUpDownService.getAccessorys("schedualed_tasks", schedualedTaskDetail.getSchedualedTasks().getId());  //和工单相关联的附件列表
		ServiceTran st=new ServiceTran();
		st.setRequNo(schedualedTaskDetail.getSchedualedTasks().getProNo());
		serviceTranList=serviceTranList=serviceTranService.findByExample(st);
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		relatedRequestList=schedualedTaskDetailService.queryByUser(schedualedTaskDetail.getSchedualedTasks().getUser().getId());		
		int flag=0;
		if(u!=null&&schedualedTaskDetail.getSchedualedTasks().getUser().getId().equals(u.getId())) flag=2;
		else {
			Iterator it=schedualedTaskDetail.getSchedualedTaskUsers().iterator();
			while(it.hasNext()) {
				SchedualedTaskUser stu=(SchedualedTaskUser) it.next();
				if(stu.getUsers().getId().equals(u.getId())) {
					flag=1;
				}
			}
		}		
		if(schedualedTaskDetail.getState().equals(WAIT)) {
			this.setInfo(getText("schedualed.task.wait"));
			if(flag==2) result="success0";
			else if(flag==1) result="success1";
			else result="success3";
		}else if(schedualedTaskDetail.getState().equals(WORK)) {
			this.setInfo(getText("schedualed.task.work"));
			if(flag!=0) result="success2";
			else result="success3";
		}else {
			this.setInfo(getText("schedualed.task.finish"));
			result="success3";
		}
		return result;
	}
	public String show1() throws Exception{
		if(schedualedTaskDetail.getId()!=null) schedualedTaskDetail=schedualedTaskDetailService.findById(schedualedTaskDetail.getId());
		this.setActionURI("show1");
		return "success";
	}
	public String solve() throws Exception{ //负责人完成任务
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		SchedualedTaskDetail std=schedualedTaskDetailService.findById(schedualedTaskDetail.getId());
		std.setState(WORK);
		std.setFinishedTime(new Timestamp(new Date().getTime()));
		if(u!=null) std.setFiniEngineer(u.getTruename());
		std.setSolution(schedualedTaskDetail.getSolution());		
		schedualedTaskDetailService.solve(std);
		if(this.getFile()!=null){
        	fileUpload(std.getSchedualedTasks().getId());//调用上传文件方法
        }
		return "success";
	}
	
	public String transmit() throws Exception{  //转交计划任务
		if(schedualedTasks!=null&&schedualedTasks.getId()!=null&&schedualedTasks.getUser()!=null){
			SchedualedTasks st=schedualedTaskService.findById(schedualedTasks.getId());
			st.setUser(schedualedTasks.getUser());
			schedualedTaskService.update2( st);
			if(this.getFile()!=null){
	        	fileUpload(st.getId());//调用上传文件方法
	        }
			Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
			ServiceTran serviceTran=new ServiceTran();
			serviceTran.setRequNo(st.getProNo());
			serviceTran.setUsersByServiceFrom(u);
			serviceTran.setUsersByServiceTo(schedualedTasks.getUser());
			serviceTran.setType("转交计划任务");
			serviceTran.setNote(this.solution);
			serviceTran.setOperatorTime(new Timestamp(new Date().getTime()));
			serviceTranService.save(serviceTran);
		}		
		return "success";
	}
	
	public String review() throws Exception{
		schedualedTaskDetail.setState(FINI);
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));
		schedualedTaskDetail.setReviewEngi(u.getTruename());
		schedualedTaskDetailService.finish(schedualedTaskDetail);		
		return "success";
	}
	
	public String finish() throws Exception{
		Users u = (Users) (ServletActionContext.getRequest().getSession().getAttribute("user"));	
		List list=schedualedTaskUserService.findByTaskIdAndUserId(schedualedTaskDetail.getId(), u.getId());
		if(list.size()>0) {
			Timestamp ts=new Timestamp(new Date().getTime());
			SchedualedTaskUser temp=(SchedualedTaskUser)list.get(0);
			temp.setFinishTime(ts);
			temp.setFinishResult(solution);
			temp.setFlag(1);
			String s=TimeUtil.getTwoDay(ts.toString(),schedualedTaskDetail.getSchedualedTime().toString());
			if(s!=null){
				Integer day=Integer.parseInt(s);
				temp.setOutOfDay(day>0?day:0);
			}
			schedualedTaskUserService.update(temp);
			if(this.getFile()!=null){
	        	fileUpload(temp.getSchedualedTaskDetail().getSchedualedTasks().getId());//调用上传文件方法
	        }
		}
		return "success";
	}
	
	public String showTran() throws Exception {
		serviceTran=serviceTranService.findById(serviceTran.getId());
		 return "success";
	}
	
	public String showStu() throws Exception{
		if(schedualedTaskUser.getId()!=null) schedualedTaskUser=schedualedTaskUserService.findById(schedualedTaskUser.getId());
		else return "error";
		return "success";
	}
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String save() throws Exception {	
		if(this.getSchedualedTasks()==null) return "error";
		try{
			message="";
			List list =this.schedualedTaskService.findBysql(" from SchedualedTasks where serviceCategory.id="+schedualedTasks.getServiceCategory().getId()+"" +
					" and rate='"+schedualedTasks.getRate()+"' and keyWord='"+schedualedTasks.getKeyWord()+"' and detail='"+schedualedTasks.getDetail()+"'");
			if(null!=list&&list.size()>0){
				message="计划任务可能重复，请重新输入!";
				return "error";
			}
		}catch(Exception ex){
			message="发生未知错误，请检查服务器日志。";
			return "error";
		}
		schedualedTaskService.save(schedualedTasks,members);
		schedualedTasks=schedualedTaskService.findByProNO(schedualedTasks.getProNo());
		if(this.getFile()!=null){
        	fileUpload(schedualedTasks.getId());//调用上传文件方法
        } 
		return "success";
	}
	
	public String deletestd() throws Exception {
		schedualedTaskDetailService.deleteById(id);
		return "success";
	}
	
	public String deletest() throws Exception {
		schedualedTaskService.delete(id);
		boolean fileisok = fileUpDownService.deleteByIdAndTableName(id, SCHEDUALEDTASK,
				FileStorage.getUpLoadPath(ServletActionContext.getRequest()));
		return "success";
	}
	
	public String users() throws Exception {
		if(members!=null&&!members.equals("")) {
			List list=new ArrayList();
			String array[]=members.split(",");				
			for(int j=0;j<array.length;j++) {
				if(!array[j].equals("")){
					Users u=userService.findUserById(Integer.parseInt(array[j]));
					if(u!=null)list.add(u);
				}
			}
			this.setUserList(list);
		}
		actionURI="users";
		return "success";
	}
	
	public String updateInput() throws Exception {
		schedualedTasks=schedualedTaskService.findById(id);	
		Set set=schedualedTasks.getSchedualedTaskDetails();
		if(set.iterator()!=null&&set.iterator().hasNext())
		this.setMembers(((SchedualedTaskDetail) set.iterator().next()).getEngineer());
		accessoryList=fileUpDownService.getAccessorys("schedualed_tasks",id);  //和工单相关联的附件列表
		this.setId(schedualedTasks.getUser().getId());
		return "success";
	}
	
	public String update() throws Exception {
		if(this.getSchedualedTasks()==null) return "error";
			schedualedTaskService.update(schedualedTasks,members);
			if(this.getFile()!=null){
	        	fileUpload(schedualedTasks.getId());//调用上传文件方法
	        } 	
		return "success";
	}
	
	public String updatestdinput() throws Exception {		
		return "success";
	}
	
	public String updatestd() throws Exception {
		SchedualedTaskDetail std=schedualedTaskDetailService.findById(id);
		if(members!=null&&!members.equals("")){
			Set set=std.getSchedualedTaskUsers();
			Iterator it=set.iterator();
			while(it.hasNext()){
				SchedualedTaskUser stu=(SchedualedTaskUser) it.next();
				stu.getSchedualedTaskDetail().setSchedualedTaskUsers(null);
				stu.setSchedualedTaskDetail(null);
				schedualedTaskUserService.delete(stu);
			}
			std.setSchedualedTaskUsers(generateScheTaskUser(members,std));
		}
		std.setEngineer(members);
		schedualedTaskDetailService.update(std);
		return "success";
	}
	
	public String updatestd(SchedualedTaskDetail std,String members) throws Exception {
		if(members!=null&&!members.equals("")){
			Set set=std.getSchedualedTaskUsers();
			Iterator it=set.iterator();
			while(it.hasNext()){
				SchedualedTaskUser stu=(SchedualedTaskUser) it.next();
				stu.getSchedualedTaskDetail().setSchedualedTaskUsers(null);
				stu.setSchedualedTaskDetail(null);
				schedualedTaskUserService.delete(stu);
			}
			std.setSchedualedTaskUsers(generateScheTaskUser(members,std));
		}
		std.setEngineer(members);
		schedualedTaskDetailService.update(std);
		return "success";
	}
	
	public Set generateScheTaskUser(String members,SchedualedTaskDetail detail) {		
		Set setUser=new HashSet();
		String array[]=members.split(",");
		for(int i=0;i<array.length;i++) {
			String s=array[i];
			if(!s.equals("")) {
				SchedualedTaskUser stu=new SchedualedTaskUser();
				stu.setSchedualedTaskDetail(detail);
				Users users=new Users();
				users.setId(Integer.parseInt(s));
				stu.setUsers(users);
				setUser.add(stu);
			}
		}
		return setUser;
	}
	
	public String arrange() throws Exception {
		if(id!=null){
			schedualedTasks=schedualedTaskService.findById(id);
			weekMap=new HashMap();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			TimeUtil tu=new TimeUtil();
			String mon=tu.getMondayOFWeek();
			this.setDateStart(mon);
			Date monDate=sdf.parse(mon);
			weekMap.put(1, mon);
			for(int i=2;i<=7;i++){
				Date date=new Date(tu.getNextDay(monDate.getTime(),i-1));
				weekMap.put(i,sdf.format(date));
			}
		}else return "error";		                       
		return "success";
	}
	
	public String updatebywd() throws Exception {
		if(id==null||members==null||members.equals("")||dateStart==null) return "error";
		else {
			schedualedTasks=schedualedTaskService.findById(id);
			if(schedualedTasks==null) return "error";
			else {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Date date=new Date();
				Date now=sdf.parse(dateStart);
				Set set=schedualedTasks.getSchedualedTaskDetails();
				Iterator it=set.iterator();
				while(it.hasNext()){
					SchedualedTaskDetail std=(SchedualedTaskDetail) it.next();
					while(schedualedTasks.getSubmitAt().compareTo(now)<=0
							&&schedualedTasks.getApproveAt().compareTo(now)>=0
							&&now.compareTo(date)>=0
							&&std.getSchedualedTime().compareTo(now)==0){
//						if(!members.contains(","+schedualedTasks.getPrincipal()+",")) std.setEngineer(","+schedualedTasks.getPrincipal()+members);
//						else std.setEngineer(members);
						updatestd(std,members);
//						schedualedTaskDetailService.update(std);
						now=new Date(TimeUtil.getNextDay(now.getTime(),7));
					}
				}
			}
		}		
		return "success";
	}
	
	public String deleteAcc(){
		if(accessoryId!=0) fileUpDownService.delAcessoryByAcessoryId(accessoryId);		
		return "success";
	}
	
	public String cronInput() {
		return "success";
	}
	
	public String cron() {
		if(schedualed==null) return "error";
		else{			
			if(schedualed.getType().equals("Cron")) {
			}else if(schedualed.getType().equals("Time")) {
				
			}else {
				
			}
		}
		return "success";
	}
	
	public String update2(Integer id,String cronExp){
		SchedualedTasks st=schedualedTaskService.findById(id);
		Iterator it=st.getSchedualedTaskDetails().iterator();
		while(it.hasNext()){
			SchedualedTaskDetail std=(SchedualedTaskDetail) it.next();
			std.setCronExpress(cronExp);
		}
		schedualedTaskService.update2(st);
		return "success";
	}
	
	public String month() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth==null){
//			return LOGIN;
//		}
	    String curDate = "";
	    int firstDayOnWeek = 0;
	    int dayInMonth = 0;
	    int countTR = 0;
	    
	    if(this.getFlag()==1){
	    	curDate = DateUtilSchedule.toString(DateUtilSchedule.addMonth(DateUtilSchedule.parse(this.getDateStart()), 1));
	    }else if(this.getFlag()==-1) {
	    	curDate = DateUtilSchedule.toString(DateUtilSchedule.addMonth(DateUtilSchedule.parse(this.getDateStart()), -1));
	    }else{
	    	curDate = DateUtilSchedule.getCurrentDate();
	    }
	    
	    StringBuffer sb = new StringBuffer();

	    sb.append("<table width='100%' cellspacing='0' cellpadding='0' border='1' bordercolor='#aaccee'>");
	    sb.append("<tr bgcolor='#DCF0FB' align='center'>");
	    sb.append("<td width='15%' height='30'><font color='green'>星期日</font></td>");
	    sb.append("<td width='14%'>星期一</td>");
	    sb.append("<td width='14%'>星期二</td>");
	    sb.append("<td width='14%'>星期三</td>");
	    sb.append("<td width='14%'>星期四</td>");
	    sb.append("<td width='14%'>星期五</td>");
	    sb.append("<td width='15%'><font color='green'>星期六</font></td>");
	    sb.append("</tr>");

	    firstDayOnWeek = DateUtilSchedule.getDayOfWeek(DateUtilSchedule.getFirstDayOfMonth(curDate));
	    dayInMonth = DateUtilSchedule.getMaxDayOfMonth(DateUtilSchedule.parse(curDate));
	    countTR = (int)Math.ceil((dayInMonth + firstDayOnWeek - 1) / 7.0D);
	    int m = 1;
	    
	    for (int n = 1; n <= countTR; ++n) {
	      sb.append("<tr valign='top'>");
	      for (int i = 1; i <= 7; ++i) {
	        sb.append("<td>");
	        if (((i < firstDayOnWeek) && (n == 1)) || (m > dayInMonth)) {
	          sb.append("&nbsp;");
	        } else {
	          sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' ");
	          if ((((i >= firstDayOnWeek) || (n != 1))) && (m <= dayInMonth))
	            sb.append("onDblClick='add(" + m + ");'");

	          sb.append(">");
	          sb.append("<tr>");
	          sb.append("<td width='40' height='20' align='center' valign='middle' style='border-bottom:#aaccee 1px solid; border-right:#aaccee 1px solid; background-color:#DCF0FB'>");
	          if (((i < firstDayOnWeek) && (n == 1)) || (m > dayInMonth)) {
	            sb.append("&nbsp;");
	          } else {
	            sb.append(m + "日");
	            ++m;
	            sb.append("<br>");
	          }
	          sb.append("<td bgcolor='#E9F0F8'>&nbsp;</td>");
	          sb.append("</tr>");
	          sb.append("<tr valign='top'>");

	          Calendar cal = Calendar.getInstance();
	          cal.setTime(DateUtilSchedule.parse(curDate));
	          cal.set(5, m - 1);
	          String tDate = DateUtilSchedule.sdfd.format(cal.getTime());
	          List<SchedualedTaskDetail> stds =schedualedTaskDetailService.queryByCalendar(tDate);

	          if (DateUtilSchedule.getCurrentDate().equals(tDate))
	            sb.append("<td colspan='2' height='55' bgcolor='#FFFFCC' onmouseover=\"this.bgColor='#EDFBD2'\" onmouseout=\"this.bgColor='#FFFFCC'\">");
	          else
	            sb.append("<td colspan='2' height='55' onmouseover=\"this.bgColor='#EDFBD2'\" onmouseout=\"this.bgColor=''\">");
	            if(stds.size()>0) {
	            	int a=curDate.lastIndexOf("-");
	            	sb.append("&nbsp;&nbsp;<a href='#' onClick=\"work('"+curDate.substring(0, a+1)+(m-1)+"')\">"
	            			+" <font style='color:red'>"+stds.size()+"</font></a>");
	            }	            
	            sb.append("<br>");
	          sb.append("</td>");
	          sb.append("</tr>");
	          sb.append("</table>");
	        }
	        sb.append("</td>");
	      }
	      sb.append("</tr>");
	    }
	    sb.append("</table>");
	    this.setCurDate(curDate);
		this.setContent(sb.toString());
	    return "success";
	}
	
	public void fileUpload(Integer id) {		
		Timestamp ts=new Timestamp(new Date().getTime());
    	for(int i=0;i<this.getFile().length;i++){ 
    		String fileName= StringUtil.generateFileName(fileFileName[i]);
    		String url=ServletActionContext.getServletContext().getRealPath("/upload")+ "/" +fileName;
			File dstFile = new File(url);
			FileUtility.copy(file[i], dstFile);	
			
			Accessory accessory=new Accessory();
			accessory.setName(fileName);
			accessory.setTrueName(fileFileName[i]);
			accessory.setTableId(id);
			accessory.setTableName(SCHEDUALEDTASK);
			accessory.setUploadDate(ts);
			accessory.setUrl("/upload/"+fileName);
			fileUpDownService.addUpFileInfo(accessory);			
    	}
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

	public SchedualedTasks getSchedualedTasks() {
		return schedualedTasks;
	}

	public void setSchedualedTasks(SchedualedTasks schedualedTasks) {
		this.schedualedTasks = schedualedTasks;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public SchedualedTaskService getSchedualedTaskService() {
		return schedualedTaskService;
	}

	public void setSchedualedTaskService(SchedualedTaskService schedualedTaskService) {
		this.schedualedTaskService = schedualedTaskService;
	}

	public SchedualedTaskDetailService getSchedualedTaskDetailService() {
		return schedualedTaskDetailService;
	}

	public void setSchedualedTaskDetailService(
			SchedualedTaskDetailService schedualedTaskDetailService) {
		this.schedualedTaskDetailService = schedualedTaskDetailService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getCurDate() {
		return curDate;
	}

	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}

	public List<SchedualedTasks> getSchedualedTasksList() {
		return schedualedTasksList;
	}

	public void setSchedualedTasksList(List<SchedualedTasks> schedualedTasksList) {
		this.schedualedTasksList = schedualedTasksList;
	}

	public List<SchedualedTaskDetail> getSchedualedTaskDetails() {
		return schedualedTaskDetails;
	}

	public void setSchedualedTaskDetails(
			List<SchedualedTaskDetail> schedualedTaskDetails) {
		this.schedualedTaskDetails = schedualedTaskDetails;
	}

	public List<List<SchedualedTaskDetail>> getSchecualedTaskDetailList() {
		return schecualedTaskDetailList;
	}

	public void setSchecualedTaskDetailList(
			List<List<SchedualedTaskDetail>> schecualedTaskDetailList) {
		this.schecualedTaskDetailList = schecualedTaskDetailList;
	}

	public SchedualedTaskDetail getSchedualedTaskDetail() {
		return schedualedTaskDetail;
	}

	public void setSchedualedTaskDetail(SchedualedTaskDetail schedualedTaskDetail) {
		this.schedualedTaskDetail = schedualedTaskDetail;
	}

	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public Map getWeekMap() {
		return weekMap;
	}

	public void setWeekMap(Map weekMap) {
		this.weekMap = weekMap;
	}

	public String getDlFileName() {
		return dlFileName;
	}

	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	public SchedualedTaskUserService getSchedualedTaskUserService() {
		return schedualedTaskUserService;
	}

	public void setSchedualedTaskUserService(
			SchedualedTaskUserService schedualedTaskUserService) {
		this.schedualedTaskUserService = schedualedTaskUserService;
	}

	public int getAccessoryId() {
		return accessoryId;
	}

	public void setAccessoryId(int accessoryId) {
		this.accessoryId = accessoryId;
	}

	public Schedualed getSchedualed() {
		return schedualed;
	}

	public void setSchedualed(Schedualed schedualed) {
		this.schedualed = schedualed;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public SchedualedTaskUser getSchedualedTaskUser() {
		return schedualedTaskUser;
	}

	public void setSchedualedTaskUser(SchedualedTaskUser schedualedTaskUser) {
		this.schedualedTaskUser = schedualedTaskUser;
	}

	public ServiceTranService getServiceTranService() {
		return serviceTranService;
	}

	public void setServiceTranService(ServiceTranService serviceTranService) {
		this.serviceTranService = serviceTranService;
	}

	public List<ServiceTran> getServiceTranList() {
		return serviceTranList;
	}

	public void setServiceTranList(List<ServiceTran> serviceTranList) {
		this.serviceTranList = serviceTranList;
	}

	public List getRelatedRequestList() {
		return relatedRequestList;
	}

	public void setRelatedRequestList(List relatedRequestList) {
		this.relatedRequestList = relatedRequestList;
	}

	public ServiceTran getServiceTran() {
		return serviceTran;
	}

	public void setServiceTran(ServiceTran serviceTran) {
		this.serviceTran = serviceTran;
	}	
	
}
