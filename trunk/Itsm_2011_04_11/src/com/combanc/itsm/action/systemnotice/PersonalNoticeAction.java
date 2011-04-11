package com.combanc.itsm.action.systemnotice;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.combanc.common.core.PageBean;
import com.combanc.itsm.pojo.PersonalNotice;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.notice.PersonalNoticeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class PersonalNoticeAction extends ActionSupport {

	private long id;
	private String title;
	private String content;
	private String to;
	private String cc;
	private String read;
	private String from;
	private long userId;
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	private List<PersonalNotice> list;
	private PersonalNoticeService personalNoticeService;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	private int userType;//决定从哪里取邮件;
	
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public List<PersonalNotice> getList() {
		return list;
	}

	public void setList(List<PersonalNotice> list) {
		this.list = list;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}


	public String getTo()
	{
		return to;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public PersonalNoticeService getPersonalNoticeService() {
		return personalNoticeService;
	}

	public void setPersonalNoticeService(PersonalNoticeService personalNoticeService) {
		this.personalNoticeService = personalNoticeService;
	}

	public String sendPersonalNotice() throws Exception 
	{
		//String from ="zhangyong";//需要从session中获取;
	    HttpSession session= ServletActionContext.getRequest().getSession();
		Users u = (Users)session.getAttribute("user");
		String from = u.getUsername();
		this.personalNoticeService.sendNotice(to, cc, title, content, from);
		return SUCCESS;
	}
	
	
	//查询所有的通知;
	public String listPersonalNotice() throws Exception
	{
		if (pageSize < 10) 
		{
			pageSize = 10;
		}

		//获取session的方法;
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users u = (Users)session.getAttribute("user");
		
		Long uid = Long.parseLong(u.getId().toString());
		
		this.pageBean = this.personalNoticeService.queryForPage(Integer.valueOf(pageSize),page,uid);
		
		//List<PersonalNotice> list = this.personalNoticeService.listPersonalNoticeByUserId(uid);
		
		/*HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("list", "list");
		<s:property value="#request.list"/>
		*/
		ActionContext.getContext().put("list", list);
		return SUCCESS;
	}
	
	//删除通知;
	public String deletePersonalNotice() throws Exception
	{
		this.personalNoticeService.deletePersonalNoticeByUserId(id);
		return SUCCESS;
	}
	
	//5.详细列出公告列表;
	public String detailPersonalNotice() throws Exception
	{
		PersonalNotice personalNotice = this.personalNoticeService.getPersonalNoticeById(id);
		personalNotice.setRead(true);
		personalNoticeService.updatePersonNotice(personalNotice);
		ActionContext.getContext().put("personalNotice",personalNotice);
		return SUCCESS;
	}
	
}
