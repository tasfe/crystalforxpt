package com.combanc.monitor.service;

import java.util.List;

import javax.annotation.Resource;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.dao.MonitorAlertDAO;
import com.combanc.monitor.dao.MonitorAlertSmalltypeDAO;
import com.combanc.monitor.dao.MonitorAlertTypeDAO;
import com.combanc.monitor.pojo.MonitorAlert;

public class MonitorAlertService extends BaseService<MonitorAlert, Integer> {
	
	@Resource
	private MonitorAlertDAO monitorAlertDAO;
	private MonitorAlertTypeDAO monitorAlertTypeDAO;
	private MonitorAlertSmalltypeDAO monitorAlertSmalltypeDAO;
	private MonitorAlert monitorAlert;
	
	public MonitorAlertDAO getMonitorAlertDAO() {
		return monitorAlertDAO;
	}

	public void setMonitorAlertDAO(MonitorAlertDAO monitorAlertDAO) {
		this.monitorAlertDAO = monitorAlertDAO;
	}

	public MonitorAlertTypeDAO getMonitorAlertTypeDAO() {
		return monitorAlertTypeDAO;
	}

	public void setMonitorAlertTypeDAO(MonitorAlertTypeDAO monitorAlertTypeDAO) {
		this.monitorAlertTypeDAO = monitorAlertTypeDAO;
	}

	public MonitorAlertSmalltypeDAO getMonitorAlertSmalltypeDAO() {
		return monitorAlertSmalltypeDAO;
	}

	public void setMonitorAlertSmalltypeDAO(
			MonitorAlertSmalltypeDAO monitorAlertSmalltypeDAO) {
		this.monitorAlertSmalltypeDAO = monitorAlertSmalltypeDAO;
	}

	public MonitorAlert getMonitorAlert() {
		return monitorAlert;
	}

	public void setMonitorAlert(MonitorAlert monitorAlert) {
		this.monitorAlert = monitorAlert;
	}

	public List findBySql(String hql) {
		return monitorAlertDAO.findBySql(hql);
	}
	public MonitorAlert findAlertBySql(String hql) {
		List<MonitorAlert> list =  monitorAlertDAO.findTopBySql(hql, 1);
		if(list.size()>0){
			return list.get(0);
		}else
			return null;
		
	}
	public List findByAlertId(Integer alertId) {
		return monitorAlertDAO.findByProperty("monitorAlert.id", alertId);
	}
	
	public List findByIp(String ip,int type,int limit) {
		String typeCondition = " and 1=1";
		if(type>0)
			typeCondition = " and m.monitorAlertType.code=" + type;
		String hql = "from MonitorAlert m where (m.ip='"+ip+"' or m.uplink='"+ip+"')"+typeCondition+" order by m.lastTime desc ";
		return monitorAlertDAO.findTopBySql(hql, limit);
	}
	
	public List findAll() {
		return monitorAlertDAO.findAll(MonitorAlert.class);
	}
	
	public MonitorAlert load(Integer id){
		return monitorAlertDAO.findById(MonitorAlert.class, id);
	}

	public void save(MonitorAlert monitorAlert){
		monitorAlertDAO.save(monitorAlert);
	}
	
	public void update(MonitorAlert monitorAlert){
		monitorAlertDAO.update(monitorAlert);
	}
	
	public void delete(MonitorAlert monitorAlert){
		monitorAlertDAO.delete(monitorAlert);
	}
	
	public void deleteById(Integer id){
		MonitorAlert monitorAlert = monitorAlertDAO.findById(id);
		if(null!=monitorAlert)
			monitorAlertDAO.delete(monitorAlert);
	}

    /** 
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    @SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize,int page,String condition){

        String hql = "from MonitorAlert as p where 1 = 1 ";        //查询语句
        if(null!=condition)
        	hql = hql + condition;
        hql = hql + " order by p.lastTime desc";
        int allRow = monitorAlertDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<MonitorAlert> list = monitorAlertDAO.queryForPage(hql,offset, length);        //"一页"的记录
        
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
    
    public List query(int size, String condition){
        String hql = "from MonitorAlert as p where 1 = 1 ";
        hql = (new StringBuilder(String.valueOf(hql))).append(condition).toString();
        hql = (new StringBuilder(String.valueOf(hql))).append(" order by p.id desc").toString();
        return monitorAlertDAO.findTopBySql(hql, size);
    }
    
    public List<MonitorAlert> findLatestBySubnet(int subnetId,int topN){
		String hql  = "from MonitorAlert as p where 1 = 1 " +
				" and p.ip in (select s.monitorDevice.ip from com.combanc.monitor.pojo.MonitorSubnetDevice s where s.monitorSubnet.id="+subnetId+")" +
				" or p.uplink in (select s.monitorDevice.ip from com.combanc.monitor.pojo.MonitorSubnetDevice s where s.monitorSubnet.id="+subnetId+")" +
				" order by p.lastTime desc" ;
		return monitorAlertDAO.queryForPage(hql,0, topN);
	}
	
  
}

