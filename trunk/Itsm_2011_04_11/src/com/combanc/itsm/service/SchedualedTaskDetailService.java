package com.combanc.itsm.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.combanc.itsm.dao.SchedualedTaskDetailDAO;
import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.SchedualedTasks;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.TimeUtil;

public class SchedualedTaskDetailService {

	private SchedualedTaskDetailDAO schedualedTaskDetailDAO;
	
	public List<SchedualedTaskDetail> queryByCalendar(String start){
		String hql = "from SchedualedTaskDetail as model where 1=1 ";
		if(start==null||start.equals("")) {
			Date date=new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			hql=hql+ " and  model.schedualedTime='" +df.format(date)+"'";
		}else {
			hql=hql+ " and  model.schedualedTime='" +start+"'";
		}
		List<SchedualedTaskDetail> list=schedualedTaskDetailDAO.findByHql(hql);
		return list;
	}
	
	public List<SchedualedTaskDetail> queryByWeek(String start,String end){
		String hql = "from SchedualedTaskDetail as model where 1=1 ";
		if(start!=null&&!start.equals("")&&end!=null&&!end.equals("")) {
			hql=hql+ " and  model.schedualedTime>='" +start+"'"
				+ " and  model.schedualedTime<='" +end+"'";
		}else {
			TimeUtil tu=new TimeUtil();
			hql=hql+ " and  model.schedualedTime>='" +tu.getMondayOFWeek()+"'"
			+ " and  model.schedualedTime<='" +tu.getCurrentWeekday()+"'";			
		}
		hql=hql+" order by schedualedTime ASC";
		List<SchedualedTaskDetail> list=schedualedTaskDetailDAO.findByHql(hql);
		return list;
	}
	
	public List<SchedualedTaskDetail> queryByUser(Integer id){
		List<SchedualedTaskDetail> list=null;
		if(id!=null&&id>0){
			String hql = "from SchedualedTaskDetail as model where 1=1 ";	
			hql=hql+" and model.engineer like '%,"+id+",%'";
			hql=hql+" order by schedualedTime ASC";
			list=schedualedTaskDetailDAO.findByHql(hql);
		}
		return list;
	}
	
	public void solve(SchedualedTaskDetail schedualedTaskDetail) throws Exception {		
		schedualedTaskDetailDAO.update(schedualedTaskDetail);		
	}
	
	public void finish(SchedualedTaskDetail schedualedTaskDetail) throws Exception {
		SchedualedTaskDetail std=schedualedTaskDetailDAO.findById(schedualedTaskDetail.getId());
		std.setState(schedualedTaskDetail.getState());
		std.setAdvice(schedualedTaskDetail.getAdvice());
		std.setReviewTime(new Timestamp(new Date().getTime()));
		std.setSarLvl(schedualedTaskDetail.getSarLvl());
		std.setReviewEngi(schedualedTaskDetail.getReviewEngi());
		schedualedTaskDetailDAO.update(std);		
	}
	
	public void deleteById(Integer id) {
		SchedualedTaskDetail std = null;
		if (id != null){
			std = (SchedualedTaskDetail) schedualedTaskDetailDAO.findById(SchedualedTaskDetail.class, id);
			SchedualedTasks st=std.getSchedualedTasks();
			st.setSchedualedTaskDetails(null);
			std.setSchedualedTasks(null);
			schedualedTaskDetailDAO.delete(std);
		}			
	}
	
	public void update(SchedualedTaskDetail schedualedTaskDetail) {		
		schedualedTaskDetailDAO.update(schedualedTaskDetail);		
	}
	
	public SchedualedTaskDetail findById(Integer id){		
		return schedualedTaskDetailDAO.findById(id);
	}
	
	public SchedualedTaskDetailDAO getSchedualedTaskDetailDAO() {
		return schedualedTaskDetailDAO;
	}

	public void setSchedualedTaskDetailDAO(
			SchedualedTaskDetailDAO schedualedTaskDetailDAO) {
		this.schedualedTaskDetailDAO = schedualedTaskDetailDAO;
	}
}
