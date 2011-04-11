package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.util.TimeUtil;
import com.combanc.monitor.dao.MonitorArpLogDAO;
import com.combanc.monitor.pojo.MonitorArpLog;
import com.combanc.monitor.pojo.MonitorDevice;

public class MonitorArpLogService extends BaseService<MonitorArpLog, Integer>{

	private MonitorArpLogDAO monitorArpLogDAO;

	public MonitorArpLogDAO getMonitorArpLogDAO() {
		return monitorArpLogDAO;
	}

	public void setMonitorArpLogDAO(MonitorArpLogDAO monitorArpLogDAO) {
		super.setDao(monitorArpLogDAO);
		this.monitorArpLogDAO = monitorArpLogDAO;
	}
	
	public MonitorArpLog findByIpAndDate(String ip,Date day){
		 
		Timestamp startTime = new Timestamp(day.getTime());
		TimeUtil.toDayStart(startTime);
		Timestamp endTime	= new Timestamp(day.getTime());
		TimeUtil.toDayEnd(endTime);
		String hql = " from MonitorArpLog m where ip = '"+ip+"' and firstTime>='"+startTime.toString()+"' and firstTime<='"+endTime.toString()+"'";
		List<MonitorArpLog> result = monitorArpLogDAO.findByHql(hql);
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	public PageBean queryForPage(int pageSize,int page,String condition){

        String hql = "from MonitorArpLog as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.id desc";
        int allRow = monitorArpLogDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<MonitorDevice> list = monitorArpLogDAO.queryForPage(hql,offset, length);        //"一页"的记录
        
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
