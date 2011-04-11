package com.combanc.itsm.quartz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.SchedualedTaskUser;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.SchedualedTaskDetailService;
import com.combanc.itsm.service.notice.PersonalNoticeService;
import com.combanc.itsm.util.TimeUtil;

public class QuartzNotice {

	private SchedualedTaskDetailService schedualedTaskDetailService;
	private PersonalNoticeService personalNoticeService;
	
	private static String TITLE="计划任务";
	private static String CONTENT="您有计划任务需要执行!";
	
	public void notice() {
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=new Date();
//		Date date2=new Date(TimeUtil.getNextDay(date1.getTime(), 7));
		List list=schedualedTaskDetailService.queryByWeek(df.format(date1), df.format(date1));
		for(int i=0;i<list.size();i++){
			SchedualedTaskDetail std=(SchedualedTaskDetail) list.get(i);
			Iterator it=std.getSchedualedTaskUsers().iterator();
			while(it.hasNext()){
				Users u=(Users) ((SchedualedTaskUser) it.next()).getUsers();
				personalNoticeService.sendNotice2(u.getId(), u.getUsername(), TITLE, CONTENT+"任务号："+std.getTaskCode()+"。");
			}
		}
	}
	
	public SchedualedTaskDetailService getSchedualedTaskDetailService() {
		return schedualedTaskDetailService;
	}

	public void setSchedualedTaskDetailService(
			SchedualedTaskDetailService schedualedTaskDetailService) {
		this.schedualedTaskDetailService = schedualedTaskDetailService;
	}

	public PersonalNoticeService getPersonalNoticeService() {
		return personalNoticeService;
	}

	public void setPersonalNoticeService(PersonalNoticeService personalNoticeService) {
		this.personalNoticeService = personalNoticeService;
	}

	
}
