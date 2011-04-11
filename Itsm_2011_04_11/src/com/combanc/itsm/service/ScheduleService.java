package com.combanc.itsm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.itsm.dao.ScheduleDAO;
import com.combanc.itsm.pojo.SchedualedTaskDetail;
import com.combanc.itsm.pojo.Schedule;

public class ScheduleService {
	
	private ScheduleDAO	 scheduleDAO;

	public ScheduleDAO getScheduleDAO() {
		return scheduleDAO;
	}

	public void setScheduleDAO(ScheduleDAO scheduleDAO) {
		this.scheduleDAO = scheduleDAO;
	}
	
	public void save(Schedule schedule){
		scheduleDAO.save(schedule);
	}
	
	public void update(Schedule schedule){
		scheduleDAO.update(schedule);
	}
	
	public void delete(String id){
		Schedule schedule = scheduleDAO.findById(Integer.valueOf(id));
		scheduleDAO.delete(schedule);
	}
	
	public void delete(String[] ids){
		for(String id:ids){
			Schedule schedule = scheduleDAO.findById(Integer.valueOf(id));
			scheduleDAO.delete(schedule);
		}
	}
	
	public Schedule load(Integer id){
		return scheduleDAO.findById(Integer.valueOf(id));
	}
	
	  public List findByDate(String tDate, String uid){
	    	return scheduleDAO.findByDate(tDate, uid);
		}
	  
	  public List<Schedule> findAll(){
		  return scheduleDAO.findAll();
	  }
	  
	  public Schedule findById(Integer scheduleId){
		  return (Schedule)scheduleDAO.findById(scheduleId);
	  }
	
	/** 
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    @SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize,int page, int uid){

        String hql = "from Schedule as p where p.userByAssigner.id!=p.userByExecutor.id order by p.adate desc,p.hour  desc";        //查询语句
        String hql1 = "from Schedule as p where p.userByExecutor.id = "+uid+" order by p.adate desc,p.hour desc"; 
        if (uid != 0) {
        	hql = hql1;
        } 
        int allRow = scheduleDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<Schedule> list = scheduleDAO.queryForPage(hql,offset,length);        //"一页"的记录
        
        //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
    }
    
    
    public PageBean queryForPages(int pageSize,int page, int uid){
        String hql = "from Schedule as p where p.userByExecutor.id = "+uid+" order by p.adate desc,p.hour desc"; //查询语句
        int allRow = scheduleDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<Schedule> list = scheduleDAO.queryForPage(hql,offset,length);        //"一页"的记录
        
        //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
    }
    
    public List<Schedule> queryByCalendar(String start){
		String hql = "from Schedule as model where 1=1 ";
		if(start==null||start.equals("")) {
			Date date=new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			hql=hql+ " and  model.adate='" +df.format(date)+"'";
		}else {
			hql=hql+ " and  model.adate='" +start+"'";
		}
		System.out.println(hql);
		List<Schedule> list=scheduleDAO.findByHql(hql);
		return list;
	}
    
    public List findByDate(String tDate){
    	return scheduleDAO.findByDate(tDate);
	}
    
  
}
