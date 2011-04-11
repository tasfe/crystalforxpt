package com.combanc.itsm.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.SchedualedTaskDetailDAO;
import com.combanc.itsm.dao.SchedualedTasksDAO;
import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.SchedualedTaskUser;
import com.combanc.itsm.pojo.SchedualedTasks;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.StringUtil;
import com.combanc.itsm.util.TimeUtil;

public class SchedualedTaskService {
	
	private SchedualedTasksDAO schedualedTasksDAO;
	private SchedualedTaskDetailDAO schedualedTaskDetailDAO;

	public static final String ONLY = "Only";
	public static final String HALF = "Half";
	public static final String QUAR = "Quar";
	public static final String MONTH = "Month";
	public static final String WEEK = "Week";
	public static final String DAY = "Day";
	
	public static final String WAIT = "wait";
	public static final String WORK = "work";
	public static final String FINI = "fini";
	private static int number=1;
	public List findBysql(String sql){
		return this.schedualedTasksDAO.findBysql(sql);
	}
	public void save(SchedualedTasks transientInstance, String members) {
		String formatDate = new SimpleDateFormat("yyMMddHHmmss") .format(new Date());
		int random = new Random().nextInt(10000);
		transientInstance.setProNo("R"+formatDate+random);
		HashSet<SchedualedTaskDetail> set = new HashSet<SchedualedTaskDetail>();
//		HashSet<SchedualedTaskUser> setu = new HashSet<SchedualedTaskUser>();
		if (transientInstance != null ) {
			if (transientInstance.getRate().equals(ONLY)) {
				transientInstance.setApproveAt(null);
				set.add(generateScheTaskDet(transientInstance.getSubmitAt(),transientInstance,members));
			} else if (transientInstance.getRate().equals(HALF)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,transientInstance,members));
					ts1.setTime(TimeUtil.getNextMonth(ts1.getTime(), 6));
				}
			} else if (transientInstance.getRate().equals(QUAR)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,transientInstance,members));
					ts1.setTime(TimeUtil.getNextMonth(ts1.getTime(), 3));
				}
			} else if (transientInstance.getRate().equals(MONTH)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,transientInstance,members));
					ts1.setTime(TimeUtil.getNextMonth(ts1.getTime(), 1));
				}
			} else if (transientInstance.getRate().equals(WEEK)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,transientInstance,members));
					ts1.setTime(TimeUtil.getNextDay(ts1.getTime(), 7));
				}
			} else if (transientInstance.getRate().equals(DAY)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,transientInstance,members));
					ts1.setTime(TimeUtil.getNextDay(ts1.getTime(), 1));
				}
			}
		}
		transientInstance.setSchedualedTaskDetails(set);
		schedualedTasksDAO.save(transientInstance);
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

	public SchedualedTaskDetail generateScheTaskDet(Timestamp timestamp,SchedualedTasks st,String members){
		SchedualedTaskDetail detail = new SchedualedTaskDetail();
		if(members!=null&&!members.equals("")) detail.setEngineer(members);
		detail.setTaskCode(generateCode());
		detail.setState(WAIT);
		detail.setSchedualedTime(timestamp);
		detail.setSchedualedTasks(st);
		detail.setSchedualedTaskUsers(generateScheTaskUser(members,detail));
		return detail;
	}	

	public String generateCode() {
		String code="T";
		String formatDate = new SimpleDateFormat("yyMMdd").format(new Date());
		String temp=String.valueOf(number++);
		String s="";
		if(temp.length()<6){
			for(int i=0;i<6-temp.length();i++) {
				s=s+"0";
			}
		}
		code=code+formatDate+ new Random().nextInt(100)+s+temp;
		return code;
	}	
	
	public void update(SchedualedTasks transientInstance, String members) {
		SchedualedTasks st=schedualedTasksDAO.findById(transientInstance.getId());
		if(transientInstance.getUser()!=null) st.setUser(transientInstance.getUser());
		st.setServerity(transientInstance.getServerity());
		st.setKeyWord(transientInstance.getKeyWord());
		st.setDetail(transientInstance.getDetail());
		st.setSubmitAt(transientInstance.getSubmitAt());
		if(transientInstance.getRate().equals(ONLY)) {
			st.setApproveAt(null);
		}else st.setApproveAt(transientInstance.getApproveAt());
		Set set =st.getSchedualedTaskDetails();
		if (!transientInstance.getRate().equals(st.getRate())
				||(transientInstance.getSubmitAt()!=null&&transientInstance.getSubmitAt().compareTo(st.getSubmitAt())!=0)
				 ||(transientInstance.getApproveAt()!=null&&transientInstance.getApproveAt().compareTo(st.getSubmitAt())!=0)) {
			st.setRate(transientInstance.getRate());
			Iterator it=set.iterator();
			while(it.hasNext()){
				SchedualedTaskDetail std=(SchedualedTaskDetail) it.next();
				if(!std.getState().equals(FINI)){
					it.remove();
					SchedualedTasks sts=std.getSchedualedTasks();
					sts.setSchedualedTaskDetails(null);
					std.setSchedualedTasks(null);
					schedualedTaskDetailDAO.delete(std);
				}
			}
//			HashSet<SchedualedTaskUser> setu = new HashSet<SchedualedTaskUser>();
			if (transientInstance.getRate().equals(ONLY)) {
				transientInstance.setApproveAt(null);
				set.add(generateScheTaskDet(transientInstance.getSubmitAt(),st,members));
			} else if (transientInstance.getRate().equals(HALF)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,st,members));
					ts1.setTime(TimeUtil.getNextMonth(ts1.getTime(), 6));
				}
			} else if (transientInstance.getRate().equals(QUAR)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,st,members));
					ts1.setTime(TimeUtil.getNextMonth(ts1.getTime(), 3));
				}
			} else if (transientInstance.getRate().equals(MONTH)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,st,members));
					ts1.setTime(TimeUtil.getNextMonth(ts1.getTime(), 1));
				}
			} else if (transientInstance.getRate().equals(WEEK)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,st,members));
					ts1.setTime(TimeUtil.getNextDay(ts1.getTime(), 7));
				}
			} else if (transientInstance.getRate().equals(DAY)) {
				Timestamp ts1=new Timestamp(transientInstance.getSubmitAt().getTime());
				Timestamp ts2=new Timestamp(transientInstance.getApproveAt().getTime());
				while(ts1.compareTo(ts2)<=0){
					Timestamp ts=new Timestamp(ts1.getTime());
					set.add(generateScheTaskDet(ts,st,members));
					ts1.setTime(TimeUtil.getNextDay(ts1.getTime(), 1));
				}
			}
		}
		st.setSchedualedTaskDetails(set);
		schedualedTasksDAO.update(st);
	}
	
	public void update2(SchedualedTasks transientInstance){
		schedualedTasksDAO.update(transientInstance);
	}
	
	public void delete(Integer id) {
		SchedualedTasks st=null;
		if(id!=null) {
			st=findById(id);
//			Set set=st.getSchedualedTaskDetails();
//			Iterator it=set.iterator();
//			int flag=0;
//			while(it.hasNext()){
//				SchedualedTaskDetail std=(SchedualedTaskDetail) it.next();
//				if(!std.getState().equals(FINI)){
//					it.remove();
//					SchedualedTasks sts=std.getSchedualedTasks();
//					sts.setSchedualedTaskDetails(null);
//					std.setSchedualedTasks(null);
//					schedualedTaskDetailDAO.delete(std);
//				}else flag=1;
//			}
//			if(flag==0) schedualedTasksDAO.delete(st);
//			else{
//				st.setTmp1("DEL");
//			if(st!=null) schedualedTasksDAO.update(st);
			if(st!=null) schedualedTasksDAO.delete(st);
			}			
//		}		
	}
	
	public List<SchedualedTasks> findAll() {
		return schedualedTasksDAO.findAll(SchedualedTasks.class);
	}
	
	public SchedualedTasks findById(Integer id) {
		return schedualedTasksDAO.findById(id);
	}
	
	public SchedualedTasks findByProNO(String s) {
		return (SchedualedTasks) schedualedTasksDAO.findByProNo(s).get(0);
	}
	
	public PageBean queryForPage(int pageSize, int page, SchedualedTasks condition) {
		String hql = "from SchedualedTasks as model where 1=1 ";
		if (condition != null) {
			if(condition.getServiceCategory().getId()!=null
					&&condition.getServiceCategory().getId()!=-1){
				hql=hql+" and model.serviceCategory.id="+condition.getServiceCategory().getId();
			}
			if(condition.getKeyWord()!=null&&!condition.getKeyWord().trim().equals("")){
				condition.setKeyWord(StringUtil.decrateString(condition.getKeyWord()));
				hql=hql+" and model.keyWord like '%"+condition.getKeyWord()+"%' ";
			}
			if(condition.getSubmitAt()!=null){
				hql = hql + " and  model.submitAt>='" +condition.getSubmitAt() + "'";
			}	
			if(condition.getApproveAt()!=null){
				hql = hql + " and  model.submitAt<='" +condition.getApproveAt() + "'";
			}
		}
		hql = hql + " order by model.submitAt desc";// 查询语句
		
		int allRow = schedualedTasksDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<SchedualedTasks> list = schedualedTasksDAO.queryForPage(hql, offset, length); // "一页"的记录

		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}
	
	public SchedualedTasksDAO getSchedualedTasksDAO() {
		return schedualedTasksDAO;
	}

	public void setSchedualedTasksDAO(SchedualedTasksDAO schedualedTasksDAO) {
		this.schedualedTasksDAO = schedualedTasksDAO;
	}

	public SchedualedTaskDetailDAO getSchedualedTaskDetailDAO() {
		return schedualedTaskDetailDAO;
	}

	public void setSchedualedTaskDetailDAO(
			SchedualedTaskDetailDAO schedualedTaskDetailDAO) {
		this.schedualedTaskDetailDAO = schedualedTaskDetailDAO;
	}
	
	
}
