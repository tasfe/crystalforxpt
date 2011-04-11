package com.combanc.itsm.action.engineer;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.core.aop.Action;
import com.combanc.itsm.pojo.PersonalNotice;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.notice.PersonalNoticeService;

public class EngineerShowAction extends BaseActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	private Integer number;
	private List<PersonalNotice> personalNoticeList;
	private PersonalNoticeService personalNoticeService;
	
	
	@Action(description="列出TOP显示通知")
	public String top() throws Exception{
		Users u = (Users)ServletActionContext.getRequest().getSession().getAttribute("user");
		if(u!=null) number=personalNoticeService.listNewPersonalNoticeByUserId(u.getId()).size();
		return "success";
	}

	public PersonalNoticeService getPersonalNoticeService() {
		return personalNoticeService;
	}

	public void setPersonalNoticeService(PersonalNoticeService personalNoticeService) {
		this.personalNoticeService = personalNoticeService;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public List<PersonalNotice> getPersonalNoticeList() {
		return personalNoticeList;
	}

	public void setPersonalNoticeList(List<PersonalNotice> personalNoticeList) {
		this.personalNoticeList = personalNoticeList;
	}
	
}
