package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.pojo.Users;
import com.combanc.monitor.algorithm.LinkPortDiscovery;
import com.combanc.monitor.algorithm.LinkPortDiscoveryThread;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.dao.MonitorDeviceDAO;
import com.combanc.monitor.dao.MonitorDeviceTypeDAO;
import com.combanc.monitor.dao.MonitorLinkportDAO;
import com.combanc.monitor.dao.MonitorSubnetDAO;
import com.combanc.monitor.dao.MonitorSubnetDeviceDAO;
import com.combanc.monitor.dao.MonitorSubnetTypeDAO;
import com.combanc.monitor.dao.MonitorSystemParamDAO;
import com.combanc.monitor.dao.MonitorTopologyEdgeDAO;
import com.combanc.monitor.dao.MonitorTopologyNodeDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSubnetDevice;
import com.combanc.monitor.pojo.MonitorSubnetType;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojo.MonitorTopologyEdge;
import com.combanc.monitor.pojo.MonitorTopologyNode;
import com.combanc.monitor.pojo.MonitorTopologyParam;
import com.combanc.monitor.quartz.MonitorTopologyPoll;
import com.combanc.monitor.util.IPUtil;


public class MonitorSubnetService extends BaseService<MonitorSubnet, Integer>{
	
	private byte[] lockDis = new byte[0];  // 特别的instance变量   
	private byte[] lock = new byte[0];  // 特别的instance变量   
	@Resource
	private MonitorSubnetDAO monitorSubnetDAO;
	private MonitorSubnetDeviceDAO monitorSubnetDeviceDAO;
	private MonitorSubnetTypeDAO monitorSubnetTypeDAO;
	private LinkPortDiscovery linkportDiscovery;
	private MonitorDeviceDAO monitorDeviceDAO;
	private MonitorLinkportDAO monitorLinkportDAO;
	private MonitorTopologyEdgeDAO monitorTopologyEdgeDAO;
	private MonitorTopologyNodeDAO monitorTopologyNodeDAO;
	private MonitorSystemParamDAO  monitorSystemParamDAO;
	private MonitorDeviceTypeDAO monitorDeviceTypeDAO;
 
	
	private Thread discoveryThread;
	// private Thread linkportDiscovery;
	
//	public Thread getLinkportDiscovery() {
//		return linkportDiscovery;
//	}
//
//	public void setLinkportDiscovery(Thread linkportDiscovery) {
//		this.linkportDiscovery = linkportDiscovery;
//	}
	
	public LinkPortDiscovery getLinkportDiscovery() {
		return linkportDiscovery;
	}

	public MonitorDeviceTypeDAO getMonitorDeviceTypeDAO() {
		return monitorDeviceTypeDAO;
	}

	public void setMonitorDeviceTypeDAO(MonitorDeviceTypeDAO monitorDeviceTypeDAO) {
		this.monitorDeviceTypeDAO = monitorDeviceTypeDAO;
	}

	public MonitorSystemParamDAO getMonitorSystemParamDAO() {
		return monitorSystemParamDAO;
	}

	public void setMonitorSystemParamDAO(MonitorSystemParamDAO monitorSystemParamDAO) {
		this.monitorSystemParamDAO = monitorSystemParamDAO;
	}

	public MonitorTopologyNodeDAO getMonitorTopologyNodeDAO() {
		return monitorTopologyNodeDAO;
	}

	public void setMonitorTopologyNodeDAO(
			MonitorTopologyNodeDAO monitorTopologyNodeDAO) {
		this.monitorTopologyNodeDAO = monitorTopologyNodeDAO;
	}

	public MonitorTopologyEdgeDAO getMonitorTopologyEdgeDAO() {
		return monitorTopologyEdgeDAO;
	}

	public void setMonitorTopologyEdgeDAO(
			MonitorTopologyEdgeDAO monitorTopologyEdgeDAO) {
		this.monitorTopologyEdgeDAO = monitorTopologyEdgeDAO;
	}

	public MonitorLinkportDAO getMonitorLinkportDAO() {
		return monitorLinkportDAO;
	}

	public void setMonitorLinkportDAO(MonitorLinkportDAO monitorLinkportDAO) {
		this.monitorLinkportDAO = monitorLinkportDAO;
	}

	public MonitorDeviceDAO getMonitorDeviceDAO() {
		return monitorDeviceDAO;
	}

	public void setMonitorDeviceDAO(MonitorDeviceDAO monitorDeviceDAO) {
		this.monitorDeviceDAO = monitorDeviceDAO;
	}

	public void setLinkportDiscovery(LinkPortDiscovery linkportDiscovery) {
		this.linkportDiscovery = linkportDiscovery;
	}

	public MonitorSubnetTypeDAO getMonitorSubnetTypeDAO() {
		return monitorSubnetTypeDAO;
	}


	public void setMonitorSubnetTypeDAO(MonitorSubnetTypeDAO monitorSubnetTypeDAO) {
		this.monitorSubnetTypeDAO = monitorSubnetTypeDAO;
	}

	public MonitorSubnetDeviceDAO getMonitorSubnetDeviceDAO() {
		return monitorSubnetDeviceDAO;
	}

	public void setMonitorSubnetDeviceDAO(
			MonitorSubnetDeviceDAO monitorSubnetDeviceDAO) {
		this.monitorSubnetDeviceDAO = monitorSubnetDeviceDAO;
	}

	public MonitorSubnetDAO getMonitorSubnetDAO() {
		return monitorSubnetDAO;
	}

	public void setMonitorSubnetDAO(MonitorSubnetDAO monitorSubnetDAO) {
		super.setDao(monitorSubnetDAO);
		this.monitorSubnetDAO = monitorSubnetDAO;
	}
	public List<MonitorSubnet> getAllBySubnetType(Integer code) {
		return monitorSubnetDAO.findByProperty("monitorSubnetType.code", code);
	}
	
	public PageBean queryForPage(int pageSize, int page, String condition) {
		String hql = "from MonitorSubnet as p where 1=1 ";
		hql = hql + condition;
		hql = hql + " order by p.monitorSubnetType.code desc";// 查询语句
		int allRow = monitorSubnetDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<Users> list = monitorSubnetDAO.queryForPage(hql, offset, length); // "一页"的记录
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
	public List query(String condition){
		
		String hql = "from MonitorSubnet as p where 1=1 ";
		hql = hql + condition;
		return monitorSubnetDAO.findByHql(hql);
		
	}
	
	public PageBean search(MonitorSubnet subnet,int pageSize, int page) {
		String condition="";
		if(null!=subnet.getName()&&!subnet.getName().toString().trim().equals("")){
			subnet.setName(subnet.getName().trim());
			condition=condition + " and p.name like '%" + subnet.getName()+"%'";
		}
		if(null!=subnet.getMonitorSubnetType()&&subnet.getMonitorSubnetType().getCode()!=0) {
			condition=condition + " and p.monitorSubnetType.code=" +subnet.getMonitorSubnetType().getCode();
		}
        return this.queryForPage(pageSize, page, condition);
	}

	public void batchInsert(MonitorSubnet subnet, List<MonitorDevice> monitorDeviceList){
		monitorSubnetDeviceDAO.batchInsert(subnet,monitorDeviceList);
	} 
	public void saveSubnetDevice(MonitorSubnetDevice monitorSubnetDevice){
		monitorSubnetDeviceDAO.save(monitorSubnetDevice);
	} 
	
	public String batchDetele(MonitorSubnet subnet, List<MonitorDevice> monitorDeviceList){
		String message = "";
		try{
			int[] m = monitorSubnetDeviceDAO.batchDelete(subnet,monitorDeviceList);
			int[] n = monitorLinkportDAO.batchDelete(subnet, monitorDeviceList);
			int num1 = 0; //成功删除的设备总数
			int num2 = 0; //成功删除的互连接口总数
			
			for(int i=0;i<m.length;i++)
				num1 += m[i];
			for(int i=0;i<n.length;i++)
				num2 += n[i];
			
			if(num1>0)
				message = num1+"台设备";
			if(num2>0)
				message += ","+num2+"条互连接口记录";
			if(message.length()>0)
				message = "成功删除"+message+"!";
			else
				message = "删除失败!";
				
		}catch(Exception e){
			e.printStackTrace();
			message = "删除失败";
		}
		return message;
		
	} 
	
	@SuppressWarnings("unchecked")
	public List<MonitorDevice> getSelectedDeviceBySubnet(Integer subnetId){
		List<MonitorDevice> selectedDevice=new ArrayList<MonitorDevice>();
		List<MonitorSubnetDevice> monitorSubnetDeviceList= monitorSubnetDeviceDAO.findBySubnet(subnetId); 
		for(MonitorSubnetDevice model : monitorSubnetDeviceList){
			selectedDevice.add(model.getMonitorDevice());
		}
		return selectedDevice;
		
	}
	
	
	public PageBean queryDeviceForPage(int pageSize,int page,String condition){

        String hql = "from MonitorSubnetDevice as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.monitorDevice.id asc";
        int allRow = monitorSubnetDeviceDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        
        List<MonitorDevice> selectedDevice=new ArrayList<MonitorDevice>();
		List<MonitorSubnetDevice> monitorSubnetDeviceList= monitorSubnetDeviceDAO.queryForPage(hql,offset, length); //"一页"的记录 
		for(MonitorSubnetDevice model : monitorSubnetDeviceList){
			selectedDevice.add(model.getMonitorDevice());
		}
       
        
        //把分页信息保存到Bean中
        PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(selectedDevice);
        pageBean.init();
        return pageBean;
    }
	
	/**
	 * 返回分区内可检测的设备列表（去除没有指定设备类型的、设备类型是虚拟设备、或者读community是空的设备）
	 * @param subnetId
	 * @return
	 */

	public List<MonitorDevice> getSelectedEffectiveDeviceBySubnet(Integer subnetId){
		List<MonitorDevice> selectedDevice=new ArrayList<MonitorDevice>();
		List<MonitorSubnetDevice> monitorSubnetDeviceList= monitorSubnetDeviceDAO.findBySubnet(subnetId); 
		for(MonitorSubnetDevice model : monitorSubnetDeviceList){
			if(null == model.getMonitorDevice().getMonitorDeviceType()
					||  model.getMonitorDevice().getMonitorDeviceType().equals(MainConstants.DEVICE_VIRTUAL)
					||  null == model.getMonitorDevice().getReadCommunity()
					|| "".equals(model.getMonitorDevice().getReadCommunity()))
				continue;
			selectedDevice.add(model.getMonitorDevice());
		}
		return selectedDevice;
		
	}
	
	public List<MonitorDevice> getDeviceBySubnet(List<MonitorSubnet> subnetList){
		String hql ="select distinct monitorDevice from MonitorSubnetDevice m where 1=1 ";
		StringBuffer condition =   new StringBuffer(" and ( 1=2 ");
		for(MonitorSubnet subnet : subnetList){
			condition.append(" or m.monitorSubnet.id=" + subnet.getId());
		}
		condition.append(")");
		hql += condition.toString();
		List result = monitorSubnetDeviceDAO.findByHql(hql.toString());
		return result;
		
	}
	
	public List<MonitorDevice> getDeviceBySubnetIdList(List<Integer> subnetList){
		String hql ="select distinct monitorDevice from MonitorSubnetDevice m where 1=1 ";
		StringBuffer condition =   new StringBuffer(" and ( 1=2 ");
		for(Integer subnetId : subnetList){
			condition.append(" or m.monitorSubnet.id=" + subnetId);
		}
		condition.append(")");
		hql += condition.toString();
		List result = monitorSubnetDeviceDAO.findByHql(hql.toString());
		return result;
		
	}

	public List<MonitorSubnetDevice> getAllInSubnetDevice(){
		 
		return monitorSubnetDeviceDAO.getAll();
	
	}
	/**
	 * 返回分区内的设备列表、互联端口列表和系统属性设置信息，生成拓扑图时用到这些 
	 */
	public  MonitorTopologyParam getSubnetDeviceBySubnet(Integer subnetId){
		MonitorTopologyParam param = null;
		MonitorSubnet subnet = monitorSubnetDAO.findById(subnetId);
		this.subnetLayout(subnetId);
		if(null != subnet){
			MonitorTopologyPoll.subnetMap.put(subnetId, new Timestamp(System.currentTimeMillis()));
			param = new MonitorTopologyParam();
			param.setSubnet(subnet);
			List<MonitorSystemParam> systemParamList=monitorSystemParamDAO.findAll();
			param.setSystemParamList(systemParamList);
			List<MonitorSubnetDevice> subnetDeviceList=monitorSubnetDeviceDAO.findBySubnet(subnetId);
			param.setSubnetDeviceList(subnetDeviceList);
			 
			String	condition = "from MonitorLinkport as p where  monitorSubnet.id = "+subnetId +" and downlinkIp<>''";
			List<MonitorLinkport> result=monitorLinkportDAO.findByProperty("monitorSubnet.id", subnetId);
			List<MonitorLinkport> linkportList=monitorLinkportDAO.findByHql(condition);
			param.setLinkportList(linkportList);
		}
		return  param;
	}
	public MonitorSubnetType getSubnetType(Integer id){
		return monitorSubnetTypeDAO.findById(id);
	}

	public MonitorSubnet findById(Integer id) {
		return monitorSubnetDAO.findById(id);
	}
	/**
	 * 获取分区中连线的相关信息
	 * @param subnetId
	 * @return
	 */
	 
	public List<MonitorTopologyEdge> getEdgesInfo(Integer subnetId) {
		long startTime=System.currentTimeMillis();   //获取开始时间  


		

		//List<MonitorTopologyEdge> edgeInfoList=monitorTopologyEdgeDAO.findBySubnetId(subnetId);
	
		
		String hql = " from MonitorLinkport m where m.downlinkIp is not null and m.downlinkIp<>'' and monitorSubnet.id="+subnetId;
		List<MonitorLinkport> linkportList=monitorLinkportDAO.findByHql(hql);
		 
		List<MonitorTopologyEdge> edgeInfoResult=monitorTopologyEdgeDAO.findAll(MonitorTopologyEdge.class);
		List<MonitorTopologyEdge> edgeInfoList=new ArrayList<MonitorTopologyEdge>() ; 
		for (MonitorTopologyEdge edgeInfo : edgeInfoResult) {
			for (MonitorLinkport lp : linkportList) {
				if(edgeInfo.getIp().equals(lp.getIp())
						&&edgeInfo.getInterface_().equals(lp.getInterface_())
						&&edgeInfo.getDownlinkIp().equals(lp.getDownlinkIp())
						&&edgeInfo.getDownlinkInterface().equals(lp.getDownlinkInterface())){
					edgeInfoList.add(edgeInfo);
					break;
				}
			}
			
		}
		long endTime=System.currentTimeMillis(); //获取结束时间  


		System.out.println("程序运行时间： "+(endTime-startTime)+"ms");  
		return  edgeInfoList;
	}
	
	/**
	 * 获取分区中设备信息
	 * @param subnetId
	 * @return
	 */
	public List<MonitorTopologyNode>  getNodesInfo(Integer subnetId) {
		List<MonitorTopologyNode> nodeList=monitorTopologyNodeDAO.findBySubnetId(subnetId);
		return nodeList;
		
	}
	
	/**
	 * 拓扑图数据刷新
	 * @param subnetId
	 * @return
	 */
	public List refreshTopology(Integer subnetId) { 
		List list=new ArrayList();
		list.add(this.getEdgesInfo(subnetId));
		list.add(this.getNodesInfo(subnetId));
		MonitorTopologyPoll.subnetMap.put(subnetId, new Timestamp(System.currentTimeMillis()));
		return list;
		
	}
	/**
	 * 互联端口发现
	 * @param algorithm 传入算法 1:通用算法，2:思科CDP，3:华为NDP
	 * @param centerIP 当有多个三层设备时，所选核心IP地址
	 * @param subnetId 分区ID
	 * @return true:执行成功 false:执行失败
	 */
	public  String linkportDiscovery(String algorithm,String centerIp,String subnetId){
		synchronized(this.lockDis){
			try {
				String info="";
				MonitorSubnet monitorSubnet=monitorSubnetDAO.findById(Integer.valueOf(subnetId));
				System.out.println("thread - " + monitorSubnet.getName() + "   " + linkportDiscovery.toString());
				
				monitorSubnet.setCenterIp(centerIp);
				monitorSubnetDAO.updateFromDwr(monitorSubnet);
				 
				discoveryThread = new LinkPortDiscoveryThread(monitorSubnet,centerIp);
				discoveryThread.run();
//				linkportDiscovery.init(monitorSubnet, centerIp);
//				info = linkportDiscovery.run();
				return info;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "发现过程中出现错误："+e.toString();
			}
		}
		
	}
	
	public void cencelDiscovery(){
		try {
			if (discoveryThread != null) {
				if (discoveryThread.isAlive()) {
					discoveryThread.stop();

				}
				// 确保内存收集程序能清除。
				discoveryThread = null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}
	
	public List<MonitorSubnet> findAll(){
		return monitorSubnetDAO.findAll();
	}
	
	public List<MonitorSubnet> findExcept(Integer subnetId){
		List<MonitorSubnet> list = monitorSubnetDAO.findAll();
		Iterator it = list.iterator();
		while(it.hasNext()){
			MonitorSubnet subnet = (MonitorSubnet) it.next();
			if(subnet.getId().equals(subnetId))
				it.remove();
		}
		return list;
	}
	/**
	 * 返回可选择的上层分区列表
	 * @param subnetId
	 * @return
	 */
	public List<MonitorSubnet> getAvalibaleParentList(Integer subnetId){
		if(null!=subnetId)
			return monitorSubnetDAO.findByHql("from MonitorSubnet where id<>"+subnetId);
		else
			return null;
	}
	//删除分区
	public boolean deleteSubnet(Integer subnetId){
		try {
		     monitorSubnetDAO.remove(subnetId);
		     return true;
		}catch (Exception e) {
			 
			e.printStackTrace();
			return false;
		}
	}
	public void deleteDevicesBySubnetId(Integer subnetId){
		monitorSubnetDeviceDAO.deleteBySubnetId(subnetId);
	}
	
	public boolean saveTopology(MonitorSubnet subnet,ArrayList<MonitorSubnetDevice> subnetdevices,ArrayList<MonitorLinkport> linkports){
		 synchronized(lock) { 
			 if(subnet.getId()>0){
					MonitorSubnet oldsubnet=monitorSubnetDAO.findById(subnet.getId());
					oldsubnet.setEdgeRenderer(subnet.getEdgeRenderer());
					oldsubnet.setNodeTextType(subnet.getNodeTextType());
					oldsubnet.setCenterIp(subnet.getCenterIp());
					oldsubnet.setLinkDirection(subnet.getLinkDirection());
					oldsubnet.setLayout(1);
					monitorSubnetDAO.updateWithFlushMode(oldsubnet);
					//分区原有设备列表
					List<MonitorSubnetDevice> oldSubnetDeviceList=monitorSubnetDeviceDAO.findBySubnet(subnet.getId());
					//分区原来的互联端口记录列表
					List<MonitorLinkport> oldLinkportList=monitorLinkportDAO.findByProperty("monitorSubnet.id", subnet.getId());
					
					for(MonitorSubnetDevice subnetdevice : subnetdevices){
						subnetdevice.setMonitorSubnet(subnet);
						subnetdevice.setIsLink(0);
						MonitorDevice device=subnetdevice.getMonitorDevice();
						
						if(device.getId()==null||device.getId()==0) {
							//新增设备
							if(null != device.getMonitorDeviceType()){
								MonitorDeviceType type = this.monitorDeviceTypeDAO.get(device.getMonitorDeviceType().getCode());
								device.setMonitorDeviceType(type);
							}
							monitorDeviceDAO.saveWithFlushMode(device);
						}
						else {
							//修改设备信息
							MonitorDevice dev=monitorDeviceDAO.findById(device.getId());
							dev.setName(device.getName());
							monitorDeviceDAO.updateWithFlushMode(dev);
							subnetdevice.setMonitorDevice(dev);
						}
						 if(subnetdevice.getId()==null||subnetdevice.getId()==0){
							 //分区新增设备
							 monitorSubnetDeviceDAO.saveWithFlushMode(subnetdevice);
						 }
						 else{
							 monitorSubnetDeviceDAO.updateWithFlushMode(subnetdevice);
							//从原来的分区设备列表中删除已更新的记录，剩下的就是需要删除的记录
							 Iterator it=oldSubnetDeviceList.iterator();
							 while(it.hasNext()){
								 MonitorSubnetDevice model=(MonitorSubnetDevice)it.next();
								 if(model.getId().equals(subnetdevice.getId())) { 
									 it.remove(); 
									 break;
								 }
							 }
						 }
							 
					}
					//删除设备
					for(MonitorSubnetDevice model:oldSubnetDeviceList){
						monitorSubnetDeviceDAO.deleteWithFlushMode(model);
					}
					if (linkports != null) {
						// 删除指定子网的记录
						monitorLinkportDAO.deleteBySubnetId(subnet.getId());
						// 存入list
						monitorLinkportDAO.insertLinkPort(subnet.getId(),linkports);

					}
					this.subnetLayout(subnet.getId());
					return true;
					  
				}
				else
					return false;
				
		 } 
		
	}
	//生成拓扑图图之前先保存分区
	public boolean updateSubnetFromDWR(MonitorSubnet newSubnet){
		try {
			MonitorSubnet subnet=monitorSubnetDAO.findById(newSubnet.getId());
			subnet.setName(newSubnet.getName());
			subnet.setScan(newSubnet.getScan());
			subnet.setStart(newSubnet.getStart());
			monitorSubnetDAO.updateWithFlushMode(subnet);
			return true;
		} catch (RuntimeException re) {
			return false;
		}
		
	}
	/**
	 * 返回可选择的设备列表
	 * @param typecode
	 * @param selectedDeviceId
	 * @param fromIpAddress
	 * @param endIpAddress
	 * @return
	 */
	public List<MonitorDevice> getAvailableDevice(String typecode,String[] selectedDeviceId,String fromIpAddress,String endIpAddress){
		List<MonitorDevice> deviceList=new ArrayList<MonitorDevice>();
		List list=monitorDeviceDAO.getAvailableDevice(typecode, selectedDeviceId, fromIpAddress, endIpAddress);
		if(list.size()>0){
			Iterator it=list.iterator();
			while(it.hasNext()){
				
				Map p=(Map) it.next();
				MonitorDevice device=monitorDeviceDAO.findById(Integer.valueOf(p.get("id").toString()));
				 
				deviceList.add(device);
			}
		}
		return deviceList;
		
	}
	
	public List<MonitorDevice> getAvailableDevice(String exceptSubnetId,String selectedSubnetId,String typecode,String fromIpAddress,String endIpAddress){
		return monitorSubnetDeviceDAO.getAvailableDevice(exceptSubnetId,selectedSubnetId,typecode,  fromIpAddress, endIpAddress);
	}
	
	 
	
	public List<MonitorSubnet> findByScan(Object value){
		return monitorSubnetDAO.findByScan(value);
	}
	
	public Integer countDeviceBySubnet(Integer subnetId){
		List list = monitorSubnetDeviceDAO.findBySubnet(subnetId);
		return list.size();
	}
	
	public Integer countLinkportBySubnet(Integer subnetId){
		List list = monitorLinkportDAO.findBySubnetId(subnetId);
		return list.size();
	}
	
	public List<MonitorSubnetDevice> findBySubnetAndIp(Integer subnetId,String ip){
		String hql =" from MonitorSubnetDevice m where m.monitorSubnet.id="+subnetId+" and m.monitorDevice.ip='"+ip+"'";
		return monitorSubnetDeviceDAO.findByHql(hql);
	}
	/** 分区拓扑图布局 主要是判断是否存在孤立的设备或者设备群，将它们的标志isLink置为1**/
	public void subnetLayout(Integer subnetId){
		try{
			List<MonitorSubnetDevice> subnetDeviceList = monitorSubnetDeviceDAO.findBySubnet(subnetId);
			Map map = new HashMap();
			for(MonitorSubnetDevice sd : subnetDeviceList){
				MonitorDevice device = sd.getMonitorDevice();
				map.put(device.getIp(),0);
			}
			List<MonitorLinkport> linkportList = monitorLinkportDAO.findBySubnetId(subnetId);
			for(MonitorSubnetDevice sd : subnetDeviceList){
					List<MonitorLinkport> tempLinkportList = new ArrayList<MonitorLinkport>();
					for(MonitorLinkport po : linkportList){
						po.setWasVisited(false);
						tempLinkportList.add(po);
					}
					
					MonitorDevice device = sd.getMonitorDevice();
					if(!linkRoot(device.getIp(),map,tempLinkportList)){
						map.put(device.getIp(), 1);
						monitorSubnetDeviceDAO.setLink(subnetId, device.getIp());
					}
				 
				
			}
			int i=0;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public boolean linkRoot(String ip,Map map,List<MonitorLinkport> linkportList){
		boolean result = false;
		Iterator it = linkportList.iterator();
		while(it.hasNext()){
			MonitorLinkport l = (MonitorLinkport) it.next();
			if(!l.isWasVisited()){
				String uplinkLp = l.getIp();//上连IP
				String downlinkIp = l.getDownlinkIp();//下连IP
			
				if((uplinkLp.equals(ip) && map.get(downlinkIp).equals(1))
						||(downlinkIp.equals(ip) && map.get(uplinkLp).equals(1)))
					return true;
				else{
					if(uplinkLp.equals(ip)){
						l.setWasVisited(true);
						result= linkRoot(downlinkIp,map,linkportList);
					} else if(downlinkIp.equals(ip)){
						l.setWasVisited(true);
						result= linkRoot(uplinkLp,map,linkportList);
					}
				}
				if(result)
					return true;
			}
			
			 
		}
		return result;
	}
	
	public MonitorSubnet openTopology(Integer subnetId){
			 return this.findById(subnetId);

	}
	 
 
}
