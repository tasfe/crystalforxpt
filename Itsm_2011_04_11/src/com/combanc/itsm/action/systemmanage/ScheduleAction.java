package com.combanc.itsm.action.systemmanage;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Schedule;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.ScheduleService;
import com.combanc.itsm.util.DateUtilSchedule;
import com.combanc.itsm.util.ZhangYongUtil;

public class ScheduleAction extends BaseActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	private ScheduleService scheduleService;
	private String id;
	private String[] ids;
	private Schedule schedule;
	private List schedules;

	private int page; // 第几页
	private PageBean pageBean; // 包含分布信息的bean

	private String message;
//	private int uid;
	private String cDate;
	private int flag;
	private String curDate;
	private String dateStart;
	private String dateEnd;
	private String content;
	private String adate;

	private String title;
	private String detail;

	private Integer scheduleId;
	
	private Users user;
	private String uid;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getCDate() {
		return cDate;
	}

	public void setCDate(String date) {
		cDate = date;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) { // 若URL中无此参数,会默认为第1页
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String list() {// 查询预约

		this.pageBean = scheduleService.queryForPage(10, page, 0);
		page = 0;

		return SUCCESS;
	}

	public String my() {// 我的日程
	// schedules=scheduleService.findAll();

		Users userByAssigner = (Users) (ServletActionContext.getRequest()
				.getSession().getAttribute("user"));
		int userid = userByAssigner.getId();
		this.pageBean = scheduleService.queryForPages(10, page, userid);
		return SUCCESS;
	}

	public String add() throws Exception {// 预约登记
		return "success";
	}

	public String save() throws Exception {
		if (schedule == null) {
			this.setMessage(ZhangYongUtil.SAVE_ERROR);
		}
		Users userByAssigner = (Users) (ServletActionContext.getRequest()
				.getSession().getAttribute("user"));
		// 在session中获取用户信息，然后在页面中给予显示。
		schedule.setUserByAssigner(userByAssigner);
		schedule.setUserByExecutor(schedule.getUserByExecutor());
		schedule.setContent(schedule.getContent());
		schedule.setAdate(schedule.getAdate());
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		schedule.setAtime(ts);
		schedule.setHour(schedule.getHour());
		schedule.setMinute(schedule.getMinute());
		schedule.setLevel(schedule.getLevel());
		scheduleService.save(schedule);

		return "success";
	}

	public String edit() throws Exception {
		schedule = scheduleService.load(Integer.valueOf(id));
		return "success";
	}

	public String update() {
		scheduleService.update(schedule);
		return "list";
	}

	public String detail() {
		schedule = scheduleService.findById(scheduleId);
		return "success";
	}
	
	public String mydetail() {
		schedule = scheduleService.findById(scheduleId);
		return "success";
	}

	public String month() {
//		Users userByExecutor = (Users) (ServletActionContext.getRequest()
//				.getSession().getAttribute("user"));
//		int userid = userByExecutor.getId();
	    uid= ServletActionContext.getRequest().getParameter("uid");
		//String uid= user.getId()+"";
		
		String curDate = "";
		int firstDayOnWeek = 0;
		int dayInMonth = 0;
		int countTR = 0;

		if (this.getFlag() == 1) {
			curDate = DateUtilSchedule.toString(DateUtilSchedule.addMonth(
					DateUtilSchedule.parse(this.getDateStart()), 1));
		} else if (this.getFlag() == -1) {
			curDate = DateUtilSchedule.toString(DateUtilSchedule.addMonth(
					DateUtilSchedule.parse(this.getDateStart()), -1));
		} else {
			curDate = DateUtilSchedule.getCurrentDate();
		}

		StringBuffer sb = new StringBuffer();

		sb.append("<table width='100%' cellspacing='0' cellpadding='0' border='1' bordercolor='#aaccee'>");
		sb.append("<tr bgcolor='#DCF0FB' align='center'>");
		sb.append("<td width='15%' height='30'><font color='#00ff00'>星期日</font></td>");
		sb.append("<td width='14%'>星期一</td>");
		sb.append("<td width='14%'>星期二</td>");
		sb.append("<td width='14%'>星期三</td>");
		sb.append("<td width='14%'>星期四</td>");
		sb.append("<td width='14%'>星期五</td>");
		sb.append("<td width='15%'><font color='#00ff00'>星期六</font></td>");
		sb.append("</tr>");

		firstDayOnWeek = DateUtilSchedule.getDayOfWeek(DateUtilSchedule
				.getFirstDayOfMonth(curDate));
		dayInMonth = DateUtilSchedule.getMaxDayOfMonth(DateUtilSchedule
				.parse(curDate));
		countTR = (int) Math.ceil((dayInMonth + firstDayOnWeek - 1) / 7.0D);
		int m = 1;

		for (int n = 1; n <= countTR; ++n) {
			sb.append("<tr valign='top'>");
			for (int i = 1; i <= 7; ++i) {
				sb.append("<td>");
				if (((i < firstDayOnWeek) && (n == 1)) || (m > dayInMonth)) {
					sb.append("&nbsp;");
				} else {
					sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0' ");
					if ((((i >= firstDayOnWeek) || (n != 1)))
							&& (m <= dayInMonth))
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
					// List<Schedule> schedule
					// =scheduleService.queryByCalendar(tDate);
					//List<Schedule> scheduleList = scheduleService.findByDate(tDate);
					//String uid=userid+"";
			        List<Schedule> scheduleList = scheduleService.findByDate(tDate, uid);

					if (DateUtilSchedule.getCurrentDate().equals(tDate))
						sb.append("<td colspan='2' height='55' bgcolor='#FFFFCC' onmouseover=\"this.bgColor='#EDFBD2'\" onmouseout=\"this.bgColor='#FFFFCC'\">");
					else
						sb.append("<td colspan='2' height='55' onmouseover=\"this.bgColor='#EDFBD2'\" onmouseout=\"this.bgColor=''\">");

					for (Iterator it = scheduleList.iterator(); it.hasNext();) {
						Schedule obj = (Schedule) it.next();
						String str = obj.getContent().toString();
						if (str.length() > 12)
							str = str.substring(0, 12) + "...";
						int a=curDate.lastIndexOf("-");
						sb.append("&nbsp;&nbsp;<a href='#'>" + obj.getHour() + ":"
								+ obj.getMinute() + " " + str + "</a>");
						sb.append("<br>");
					}
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					// if(schedule.size()>0) {
					// int a=curDate.lastIndexOf("-");
					// sb.append("&nbsp;&nbsp;<a href='#'
					// onClick=\"work('"+curDate.substring(0, a+1)+(m-1)+"')\">"
					// +" <font color='red'>"+schedule.size()+"</font></a>");
					// }
					// sb.append("<br>");
					// sb.append("</td>");
					// sb.append("</tr>");
					// sb.append("</table>");
				}
				sb.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		this.setCurDate(curDate);
		this.setDateStart(dateStart);
		this.setContent(sb.toString());
		//this.setUid(uid);
		ServletActionContext.getRequest().setAttribute("uid", uid);
		return "success";
	}

	public String delete() {

		if (null != id) {
			scheduleService.delete(id);
			id = null;
		} else if (null != ids) {
			scheduleService.delete(ids);
		}
		return "success";
	}

	public String mynote() {
		if (schedule == null) {
			this.setMessage(ZhangYongUtil.SAVE_ERROR);
		} else {
			if (schedule.getUserByExecutor() != null
					&& schedule.getContent() == null
					&& schedule.getAdate() == null
					&& schedule.getAtime() != null
					&& schedule.getHour() == null
					&& schedule.getMinute() == null
					&& schedule.getUserByAssigner() != null
					&& schedule.getTitle() != null
					&& schedule.getDetail() != null
					&& schedule.getStatus() == null) {
				Users userByAssigner = (Users) (ServletActionContext
						.getRequest().getSession().getAttribute("user"));
				int userid = userByAssigner.getId();
				this.pageBean = scheduleService.queryForPages(10, page, userid);
			}
		}

		return "success";
	}

	public String shownote() {
		schedule = scheduleService.findById(scheduleId);
		return "success";
	}

	public String addnoteInput() {
		return "success";
	}

	public String addnote() {
		if (schedule == null) {
			this.setMessage(ZhangYongUtil.SAVE_ERROR);
		} else {
			Users userByAssigner = (Users) (ServletActionContext.getRequest()
					.getSession().getAttribute("user"));
			schedule.setUserByAssigner(userByAssigner);
			schedule.setTitle(schedule.getTitle());
			schedule.setDetail(schedule.getDetail());
			schedule.setAtime(schedule.getAtime());

		}
		scheduleService.save(schedule);
		return "success";

	}

	public List getSchedules() {
		return schedules;
	}

	public void setSchedules(List schedules) {
		this.schedules = schedules;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
