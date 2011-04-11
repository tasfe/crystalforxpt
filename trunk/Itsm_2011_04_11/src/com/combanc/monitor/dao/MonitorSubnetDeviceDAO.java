package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSubnetDevice;

public class MonitorSubnetDeviceDAO extends
		BaseHibernateDAO<MonitorSubnetDevice, Integer> {

	private static final Log log = LogFactory
			.getLog(MonitorSubnetDeviceDAO.class);

	/**
	 * 批量插入
	 * 
	 * @param subnetDeviceList
	 * @return
	 */
	public int[] batchInsert(final MonitorSubnet subnet,
			final List<MonitorDevice> monitorDeviceList) {
		log.debug("batchInsert subnetDeviceList");

		String sql = "insert into monitor_subnet_device (SUBNET_ID, DEVICE_ID,ICON,SELECTED,MONITORED,IS_LINK) values (?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setInt(1, subnet.getId());
				ps.setInt(2, monitorDeviceList.get(i).getId());
				String icon="";
				if(monitorDeviceList.get(i).getMonitorDeviceType()!=null)
					icon=monitorDeviceList.get(i).getMonitorDeviceType().getIcon();
				ps.setString(3, icon);
				ps.setInt(4, 1);
				ps.setInt(5, 1);
				ps.setInt(6, 0);
			}

			public int getBatchSize() {
				// TODO Auto-generated method stub
				return monitorDeviceList.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
	public int[] batchDelete(final MonitorSubnet subnet,final List<MonitorDevice> monitorDeviceList) {
		log.debug("delete subnetDeviceList");
		
		String sql = "delete from monitor_subnet_device where SUBNET_ID=? and DEVICE_ID=?";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setInt(1, subnet.getId());
				ps.setInt(2,monitorDeviceList.get(i).getId());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return monitorDeviceList.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql,setter);
	}
	/** 将连接标志置1，表示生成拓扑图时与root相连**/
	public void setLink(Integer subnetId,String ip){
		String hql  = " from MonitorSubnetDevice m where m.monitorSubnet.id="+subnetId+" and monitorDevice.ip='"+ip+"'";
		List<MonitorSubnetDevice> result = this.findByHql(hql);
		if(null != result && result.size()>0){
			MonitorSubnetDevice po = result.get(0);
			po.setIsLink(1);
			this.saveWithFlushMode(po);
		}
	}
	
	public List<MonitorSubnetDevice> findBySubnet(Integer subnetId){
		 return this.findByProperty(MonitorSubnetDevice.class, "monitorSubnet.id", (Object)subnetId);
	}
	/**
	 * 批量删除
	 * @param subnetId 分区Id
	 */
	public void deleteBySubnetId(Integer subnetId){
		List list =this.findBySubnet(subnetId);
		if(list.size()>0)
			this.getHibernateTemplate().deleteAll(list);
	}
	
	public void deleteBySubnetIdWithFlushMode(Integer subnetId){
		List list =this.findBySubnet(subnetId);
		if(list.size()>0){
			for (Iterator it = list.iterator(); it.hasNext();) {
				 this.deleteWithFlushMode((MonitorSubnetDevice)it.next());
			}
		}
			 
	}
	
	public List<MonitorSubnetDevice> getAllInSubnetDevice(){
		String hql = " from MonitorSubnetDevice where monitorSubnet.id is not null";
		return this.findByHql(hql);
	}
	/**
	 * 得到可选的设备列表
	 * @param exceptSubnetId 不包含在这个分区
	 * @param selectedSubnetId 包含在这个分区
	 * @param code 设备类型
	 * @param fromIpAddress 起始IP
	 * @param endIpAddress 结束IP
	 * @return
	 */
	public List getAvailableDevice(String exceptSubnetId,String selectedSubnetId,String code,String fromIpAddress,String endIpAddress) {
		log.debug("finding  MonitorSubnetDevice instances");
		try {
			StringBuilder queryString = new StringBuilder(" from MonitorDevice m where 1=1");
			if(null!=code&&code.trim().length()>0)
				queryString.append(" and m.monitorDeviceType=").append(code);
			if(null!=exceptSubnetId&&exceptSubnetId.length()>0){
				queryString.append(" and m.id not in(select sd.monitorDevice.id from MonitorSubnetDevice sd where sd.monitorSubnet.id="+exceptSubnetId+")");
			}
			if(null!=selectedSubnetId&&selectedSubnetId.length()>0&&!"null".equals(selectedSubnetId)){
				queryString.append(" and m.id in(select sd.monitorDevice.id from MonitorSubnetDevice sd where sd.monitorSubnet.id="+selectedSubnetId+")");
			}
			if(null!=fromIpAddress&&fromIpAddress.trim().length()>0)
				queryString .append(" and m.ip>='").append(fromIpAddress).append("'");
			if(null!=endIpAddress&&endIpAddress.trim().length()>0)
				queryString .append(" and m.ip<='").append(endIpAddress).append("'");
			return this.findByHql(queryString.toString());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
}
