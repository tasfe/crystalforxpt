package com.combanc.monitor.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.algorithm.PortMatch;
import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.dao.MonitorLinkportDAO;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.util.IPUtil;

public class MonitorLinkportService extends BaseService<MonitorLinkport, Integer> {
	
	@Resource
	private MonitorLinkportDAO monitorLinkportDAO;
	private MonitorLinkport monitorLinkport;
	private PortMatch portMatch;
	
	public PortMatch getPortMatch() {
		return portMatch;
	}

	public void setPortMatch(PortMatch portMatch) {
		this.portMatch = portMatch;
	}

	public MonitorLinkport MonitorLinkport() {
		return monitorLinkport;
	}

	public void setMonitorLinkport(MonitorLinkport monitorLinkport) {
		this.monitorLinkport = monitorLinkport;
	}

	public MonitorLinkportDAO getMonitorLinkportDAO() {
		return monitorLinkportDAO;
	}

	public void setMonitorLinkportDAO(MonitorLinkportDAO monitorLinkportDAO) {
		super.setDao(monitorLinkportDAO);
		this.monitorLinkportDAO = monitorLinkportDAO;
	}
	
	public List findBySql(String hql) {
		return monitorLinkportDAO.findBySql(hql);
	}
	
	public List findAll() {
		return monitorLinkportDAO.findAll(MonitorLinkport.class);
	}
	
	public List findBySubnetId(Integer subnetId) {
		String	condition = " and monitorSubnet.id = "+subnetId;
		return findByCondition(condition);
	}
	
	public MonitorLinkport load(Integer id){
		return monitorLinkportDAO.findById(MonitorLinkport.class, id);
	}

	public void save(MonitorLinkport monitorLinkport){
		monitorLinkportDAO.save(monitorLinkport);
	}
	
	public void update(MonitorLinkport monitorLinkport){
		monitorLinkportDAO.update(monitorLinkport);
	}
	
	public void delete(MonitorLinkport monitorLinkport){
		monitorLinkportDAO.delete(monitorLinkport);
	}

//	public List getPageData(int pageNum, int pageSize,String order) throws Exception {
//		return monitorLinkportDAO.getDevicepageData(pageNum, pageSize, order);
//	}
//	
//	public int getPageDataSize() throws Exception {
//		return monitorLinkportDAO.getDevicePageDataSize();
//	}
    /** 
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    @SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize,int page,String condition){

        String hql = "from MonitorLinkport as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.id asc";
        int allRow = monitorLinkportDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<MonitorLinkport> list = monitorLinkportDAO.queryForPage(hql,offset, length);        //"一页"的记录
        
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
    
    public List<MonitorLinkport> findByCondition(String condition){
        String hql = "from MonitorLinkport as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.id asc";
        return monitorLinkportDAO.findByHql(hql);       

    }
	
//	public void batchInsert(List deviceList){
//		monitorLinkportDAO.batchInsert(deviceList);
//	}
//	
//	public void batchUpdate(List deviceList){
//		monitorLinkportDAO.batchUpdate(deviceList);
//	}

	public List findByIp(String ip) {
		// TODO Auto-generated method stub
		return monitorLinkportDAO.findByIp(ip);
	}
	
	
	
	// 查找全部内容
	public List<MonitorLinkport> getLinkPortList() {
		monitorLinkportDAO.appendNote();
		return monitorLinkportDAO.findAll();
	}
	
	// 查找子网下互联端口表
	public List<MonitorLinkport> getLinkPortListBySubnetId(Integer subnetId) {
		List<MonitorLinkport> result=monitorLinkportDAO.findByProperty("monitorSubnet.id", subnetId);
		List<MonitorLinkport> list=new ArrayList<MonitorLinkport>() ;
		for (MonitorLinkport lp : result) {
			if (lp.getDownlinkIp()!= null && IPUtil.isIP(lp.getDownlinkIp())) { // 若存在下位机
				list.add(lp);
			}
		}
		return list;
	}
	
	// 查找子网下互联端口表，不排除下位接口为空的情况
	public List<MonitorLinkport> getLinkPortListAllBySubnetId(Integer subnetId) {
		List<MonitorLinkport> result = monitorLinkportDAO.findByProperty("monitorSubnet.id", subnetId);
		return result;
	}
	
	// 将某一子网的连接端口表存入数据库
	public boolean saveLinkPortList(Integer subnetId, List<MonitorLinkport> list) {
		if (list != null) {
			// 删除指定子网的记录
			monitorLinkportDAO.deleteBySubnetId(subnetId);
			// 存入list
			Iterator it = list.iterator();
			while(it.hasNext()){
				MonitorLinkport po = (MonitorLinkport) it.next();
				if(null==po.getDownlinkIp()||"".equals(po.getDownlinkIp()))
					it.remove();
			}
			monitorLinkportDAO.insertLinkPort(list);
			return true;
		} else
			return false;
	}
	/**
	 * 返回去重后的、合法的互联端口列表 只有（IP、接口、接口描述），包括上联和下联
	 * @return
	 */
	public List<MonitorLinkport> findUniqueLegitimateList(){
		List  lpList=monitorLinkportDAO.findUniqueLinkport(null);
		List<MonitorLinkport> list=new ArrayList<MonitorLinkport>();
		Iterator it=lpList.iterator();
		while(it.hasNext()){
			 Map result=(Map) it.next();
			 String strIp= result.get("IP").toString();
			 String strInterface= result.get("INTERFACE").toString();
			 String strDescription= result.get("DESCRIPTION").toString();
			 String strDownlinkIp= result.get("DOWNLINK_IP").toString();
			 String strDownlinkInterface= result.get("DOWNLINK_INTERFACE").toString();
			 String strDownlinkDesc= result.get("DOWNLINK_DESC").toString();
			 if(Tools.filter(strIp,strInterface)){
				 MonitorLinkport linkport=new MonitorLinkport();
				 linkport.setIp(strIp);
				 linkport.setInterface_(strInterface);
				 linkport.setDescription(strDescription);
				 list.add(linkport);
			 }
			 if(Tools.filter(strDownlinkIp,strDownlinkInterface)){
				 MonitorLinkport linkport=new MonitorLinkport();
				 linkport.setIp(strDownlinkIp);
				 linkport.setInterface_(strDownlinkInterface);
				 linkport.setDownlinkDesc(strDownlinkDesc);
				 list.add(linkport);
			 }
				 
		}
		 
		return list;
	
	}
	/**	返回有效的互联接口记录，如果下联接口存在且合法，就取下联接口，否则取上联**/
	public List<MonitorLinkport> findEffectiveLinkports(Integer subnetId){
		List  lpList=monitorLinkportDAO.findUniqueLinkport(subnetId);
		List<MonitorLinkport> list=new ArrayList<MonitorLinkport>();
		Iterator it=lpList.iterator();
		while(it.hasNext()){
			 Map result=(Map) it.next();
			 String strIp= result.get("IP").toString();
			 String strInterface= result.get("INTERFACE").toString();
			 String strDescription= result.get("DESCRIPTION").toString();
			 String strDownlinkIp= result.get("DOWNLINK_IP").toString();
			 String strDownlinkInterface= result.get("DOWNLINK_INTERFACE").toString();
			 String strDownlinkDesc= result.get("DOWNLINK_DESC").toString();
			 if(Tools.filter(strDownlinkIp,strDownlinkInterface)){
				 MonitorLinkport linkport=new MonitorLinkport();
				 linkport.setIp(strDownlinkIp);
				 linkport.setInterface_(strDownlinkInterface);
				 linkport.setDescription(strDescription);
				 list.add(linkport);
			 } else{
				 if(Tools.filter(strIp,strInterface)){
					 MonitorLinkport linkport=new MonitorLinkport();
					 linkport.setIp(strIp);
					 linkport.setInterface_(strInterface);
					 linkport.setDownlinkDesc(strDownlinkDesc);
					 list.add(linkport);
				 }
				 
			 }
			
				 
		}
		 
		return list;
	
	}
 
	
	/**判断设备的接口是否是互联端口**/
	public boolean isLinkPort(String ip,String interface_){
		List<MonitorLinkport> list = this.findUniqueLegitimateList();
		boolean result = false;
		for(MonitorLinkport lp:list){
			if(null != lp.getInterface_()
					&& !"".equals(lp.getInterface_())
					&& ip.equals(lp.getIp())
					&& interface_.equals(lp.getInterface_())){
				result = true;
				break;
			}
		}
		return result;
	}

	public List<String> discoveryLinkport(Integer subnetId,String ip1,String ip2){

		if(portMatch.portMatch(subnetId,ip1,ip2)){
			List<String> list = new ArrayList<String>();
			//把发现出来的上位接口 上位接口描述 下位接口 下位接口描述依次放进列表中，并返回
			list.add(portMatch.highIf);
			list.add(portMatch.highIfDescr);
			list.add(portMatch.lowIf);
			list.add(portMatch.lowIfDescr);
			return list;
		}else{
			return null;
		}
		
	}

	 
}

