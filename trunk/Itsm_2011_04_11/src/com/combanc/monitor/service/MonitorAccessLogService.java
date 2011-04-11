package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.dao.MonitorAccessLogDAO;
import com.combanc.monitor.pojo.MonitorAccessLog;
import com.combanc.monitor.pojo.MonitorDevice;

public class MonitorAccessLogService extends BaseService<MonitorAccessLog, Integer>{

	private MonitorAccessLogDAO monitorAccessLogDAO;

	public MonitorAccessLogDAO getMonitorAccessLogDAO() {
		return monitorAccessLogDAO;
	}

	public void setMonitorAccessLogDAO(MonitorAccessLogDAO monitorAccessLogDAO) {
		super.setDao(monitorAccessLogDAO);
		this.monitorAccessLogDAO = monitorAccessLogDAO;
	}
	
	public List<MonitorAccessLog> findByDate(Date day){
		 
		Timestamp startTime = new Timestamp(day.getTime());
		TimeUtil.toDayStart(startTime);
		Timestamp endTime	= new Timestamp(day.getTime());
		TimeUtil.toDayEnd(endTime);
		String hql = " from MonitorAccessLog m where firstTime>='"+startTime.toString()+"' and firstTime<='"+endTime.toString()+"'";
		return monitorAccessLogDAO.findByHql(hql);
	}
	
	public MonitorAccessLog findByIpAndDate(String ip,Date day){
		 
		Timestamp startTime = new Timestamp(day.getTime());
		TimeUtil.toDayStart(startTime);
		Timestamp endTime	= new Timestamp(day.getTime());
		TimeUtil.toDayEnd(endTime);
		String hql = " from MonitorAccessLog m where ip = '"+ip+"' and firstTime>='"+startTime.toString()+"' and firstTime<='"+endTime.toString()+"'";
		List<MonitorAccessLog> result = monitorAccessLogDAO.findByHql(hql);
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	
	public PageBean queryForPage(int pageSize,int page,String condition){

        String hql = "from MonitorAccessLog as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.id desc";
        int allRow = monitorAccessLogDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<MonitorDevice> list = monitorAccessLogDAO.queryForPage(hql,offset, length);        //"一页"的记录
        
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
	
	 
}
