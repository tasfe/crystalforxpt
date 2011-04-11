package com.combanc.monitor.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import javax.annotation.Resource;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.dao.MonitorComputerDAO;
import com.combanc.monitor.pojo.MonitorComputer;

public class MonitorComputerService extends BaseService<MonitorComputer, Integer> {
	
	@Resource
	private MonitorComputerDAO monitorComputerDAO;
	private MonitorComputer monitorComputer;
	private DeviceSnmpQuery deviceSnmpQuery;
	
	
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	public MonitorComputer getMonitorDevice() {
		return monitorComputer;
	}

	public void setMonitorDevice(MonitorComputer monitorComputer) {
		this.monitorComputer = monitorComputer;
	}

	public MonitorComputerDAO getMonitorComputerDAO() {
		return monitorComputerDAO;
	}

	public void setMonitorComputerDAO(MonitorComputerDAO monitorComputerDAO) {
		super.setDao(monitorComputerDAO);
		this.monitorComputerDAO = monitorComputerDAO;
	}
	
	public List findBySql(String hql) {
		return monitorComputerDAO.findBySql(hql);
	}
	
	public List findAll() {
		return monitorComputerDAO.findAll(MonitorComputer.class);
	}
	
	public MonitorComputer load(Integer id){
		return monitorComputerDAO.findById(MonitorComputer.class, id);
	}

	public void save(MonitorComputer monitorComputer){
		monitorComputerDAO.save(monitorComputer);
	}
	
	public void update(MonitorComputer monitorComputer){
		monitorComputerDAO.update(monitorComputer);
	}
	
	public void delete(MonitorComputer monitorComputer){
		monitorComputerDAO.delete(monitorComputer);
	}
	
	// 删除指定日期之前的入网计算机档案
	public void delBeforeDate(Timestamp time) {
		monitorComputerDAO.delBeforeDate(time);
	}

//	public List getPageData(int pageNum, int pageSize,String order) throws Exception {
//		return monitorComputerDAO.getDevicepageData(pageNum, pageSize, order);
//	}
//	
//	public int getPageDataSize() throws Exception {
//		return monitorComputerDAO.getDevicePageDataSize();
//	}
    /** 
     * 分页查询
     * @param currentPage 当前第几页
     * @param pageSize 每页大小
     * @return 封闭了分页信息(包括记录集list)的Bean
     */
    @SuppressWarnings("unchecked")
	public PageBean queryForPage(int pageSize,int page,String condition){

        String hql = "from MonitorComputer as p where 1 = 1 ";        //查询语句
        hql = hql + condition;
        hql = hql + " order by p.discoveryTime desc";
        int allRow = monitorComputerDAO.getAllRowCount(hql);    //总记录数
        int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
        final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
        final int length = pageSize;    //每页记录数
        final int currentPage = PageBean.countCurrentPage(page);
        List<MonitorComputer> list = monitorComputerDAO.queryForPage(hql,offset, length);        //"一页"的记录
        
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

    // 按设备IP归类，返回各个设备下的计算机数
	// 格式：Connection_device, count
	public List getComputerCount() {
		return monitorComputerDAO.getComputerCount();
	}


	public void batchInsert(List deviceList){
		monitorComputerDAO.batchInsert(deviceList);
	}
	
	public void batchUpdate(List deviceList){
		monitorComputerDAO.batchUpdate(deviceList);
	}
/*    
    /**
	 * 设备列表
	 * @param code 设备类型编码
	 * @param selectedDeviceIp 已选择的设备Ip列表
	 * @param fromIpAddress 起始Ip
	 * @param endIpAddress 结束Ip
	 * @return 返回可选设备列表
	 
    public List getAvailableDevice(String code,String[] selectedDeviceIp,String fromIpAddress,String endIpAddress) {
    	return monitorComputerDAO.getAvailableDevice(code, selectedDeviceIp, fromIpAddress, endIpAddress);
    }
    
    /**
	 * 已选设备
	 * @param selectedDeviceIp 已选择的设备Ip列表
	 * @return
	 
    public List getSelectedDevice(String[] selectedDeviceIp) {
    	return monitorComputerDAO.getSelectedDevice(selectedDeviceIp);
    }
    
	/**
	 * 读取单台设备信息
	 * @param ip 设备IP地址
	 * @param readCommunity 设备SNMP 读Community
	 * @return MonitorComputer 读取错误则返回null
	 * @throws Exception
	 
	public MonitorComputer readDeviceInfo(String ip, String readCommunity)
			throws Exception {
		//DeviceSnmpQuery deviceSnmpQuery = new DeviceSnmpQuery();
		String[] devDescr = deviceSnmpQuery.getDescr(ip, readCommunity);
		if (devDescr == null) {
			return null;
		}
		MonitorComputer md = new MonitorComputer();
		md.setIp(ip);
		md.setReadCommunity(readCommunity);
		md.setDescription(Tools.cutDescr(devDescr[0]));
		md.setName(Tools.decodeISO8859(devDescr[1]));
		md.setNameCn(Tools.decodeISO8859(devDescr[1]));
		return md;
	}
*/
	/** 删除旧计算机表  **/
	public void delOldComputer(String overdueTime) {
		GregorianCalendar now = new GregorianCalendar();
		 
		if (overdueTime.equals("一周")) {
			now.add(GregorianCalendar.DAY_OF_YEAR, -7);
		} else if (overdueTime.equals("两周")) {
			now.add(GregorianCalendar.DAY_OF_YEAR, -14);
		} else if (overdueTime.equals("一个月")) {
			now.add(GregorianCalendar.MONTH, -1);
		} else if (overdueTime.equals("三个月")) {
			now.add(GregorianCalendar.MONTH, -3);
		} else if (overdueTime.equals("六个月")) {
			now.add(GregorianCalendar.MONTH, -6);
		} else if (overdueTime.equals("一年")) {
			now.add(GregorianCalendar.YEAR, -1);
		}
		Timestamp delTime = new Timestamp(now.getTimeInMillis());
		monitorComputerDAO.delBeforeDate(delTime);
	}
	public MonitorComputer findByMac(String mac){
		List<MonitorComputer> result = monitorComputerDAO.findByMac(mac);
		if(result.size()>0)
			return result.get(0);
		else
			return null;
		
	}

	/**
	 * 重置计算机表的snapshot字段为false，效果等同于删除快照表
	 * 
	 */
	public void resetSnapshotFlag() {
		monitorComputerDAO.getJdbcTemplate().execute("UPDATE monitor_computer SET snapshot = false");
	}
	/**
	 * 查看分区内设备的最新计算机快照列表
	 * @param subnetId
	 * @param topN
	 * @return
	 */
	public List<MonitorComputer> findLatestBySubnet(int subnetId,int topN){
		String hql  = "from MonitorComputer as p where 1 = 1 and snapshot = true" +
				" and p.monitorDevice.id in (select s.monitorDevice.id from com.combanc.monitor.pojo.MonitorSubnetDevice s where s.monitorSubnet.id="+subnetId+") order by discoveryTime desc";
		return monitorComputerDAO.queryForPage(hql,0, topN);
	}
	
	public Integer getSnapshotCount(String condition){
		String hql = " from MonitorComputer as p where snapshot = true " + condition;
		return monitorComputerDAO.getAllRowCount(hql);    //记录数
	}
}

