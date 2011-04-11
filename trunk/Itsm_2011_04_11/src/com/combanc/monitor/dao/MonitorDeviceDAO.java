package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorDevice;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorDevice entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorDevice
 * @author MyEclipse Persistence Tools
 */

/**
 * @author Administrator
 *
 */
public class MonitorDeviceDAO extends BaseHibernateDAO<MonitorDevice, Integer> {
	private static final Log log = LogFactory.getLog(MonitorDeviceDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String READ_COMMUNITY = "readCommunity";
	public static final String WRITE_COMMUNITY = "writeCommunity";
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";
	public static final String NAME_CN = "nameCn";
	public static final String CUSTOM_TITLE = "customTitle";
	public static final String PASSWORD_LOGIN = "passwordLogin";
	public static final String PASSWORD_ENABLE = "passwordEnable";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";
	
	public MonitorDeviceDAO() {
		super();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorDevice transientInstance) {
		// transientInstance.setNote3("已添加");
		log.debug("saving MonitorDevice instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(MonitorDevice persistentInstance) {
		log.debug("deleting MonitorDevice instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorDevice findById(java.lang.Integer id) {
		log.debug("getting MonitorDevice instance with id: " + id);
		try {
			MonitorDevice instance = (MonitorDevice) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorDevice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorDevice instance) {
		log.debug("finding MonitorDevice instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MonitorDevice instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorDevice as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByReadCommunity(Object readCommunity) {
		return findByProperty(READ_COMMUNITY, readCommunity);
	}

	public List findByWriteCommunity(Object writeCommunity) {
		return findByProperty(WRITE_COMMUNITY, writeCommunity);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByNameCn(Object nameCn) {
		return findByProperty(NAME_CN, nameCn);
	}

	public List findByCustomTitle(Object customTitle) {
		return findByProperty(CUSTOM_TITLE, customTitle);
	}

	public List findByPasswordLogin(Object passwordLogin) {
		return findByProperty(PASSWORD_LOGIN, passwordLogin);
	}

	public List findByPasswordEnable(Object passwordEnable) {
		return findByProperty(PASSWORD_ENABLE, passwordEnable);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findByNote3(Object note3) {
		return findByProperty(NOTE3, note3);
	}

	public List findByNote4(Object note4) {
		return findByProperty(NOTE4, note4);
	}

	public List findAll() {
		log.debug("finding all MonitorDevice instances");
		try {
			String queryString = "from MonitorDevice";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorDevice merge(MonitorDevice detachedInstance) {
		log.debug("merging MonitorDevice instance");
		try {
			MonitorDevice result = (MonitorDevice) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorDevice instance) {
		log.debug("attaching dirty MonitorDevice instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorDevice instance) {
		log.debug("attaching clean MonitorDevice instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorDeviceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorDeviceDAO) ctx.getBean("MonitorDeviceDAO");
	}
	
	/**
	 * 可选设备
	 * @param code 设备类型编码
	 * @param selectedDeviceIp 已选择的设备Ip列表
	 * @param fromIpAddress 起始Ip
	 * @param endIpAddress 结束Ip
	 * @return 返回可选设备列表
	 */
	public List getAvailableDevice(String code,String[] selectedDeviceId,String fromIpAddress,String endIpAddress) {
		log.debug("finding all MonitorDevice instances");
		try {
			StringBuilder queryString = new StringBuilder("select id,ip,name,description from monitor_device where 1=1");
			if(null!=code&&code.trim().length()>0)
				queryString.append(" and TYPE_CODE=").append(code);
			if(null!=selectedDeviceId&&selectedDeviceId.length>0){
				StringBuilder ips=new StringBuilder();
				for(String id:selectedDeviceId)
				{
					if(!id.equals(""))
						ips.append(id+",");
				}
				queryString.append(ips.length()>0?" and id not in("+ips.substring(0, ips.length()-1)+")":"");
	
			}
			if(null!=fromIpAddress&&fromIpAddress.trim().length()>0)
				queryString .append(" and IP>'").append(fromIpAddress).append("'");
			if(null!=endIpAddress&&endIpAddress.trim().length()>0)
				queryString .append(" and IP<'").append(endIpAddress).append("'");
			return jdbcTemplate.queryForList(queryString.toString());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List getAvailableDevice(String subnetId,String code,String fromIpAddress,String endIpAddress) {
		log.debug("finding all MonitorDevice instances");
		try {
			StringBuilder queryString = new StringBuilder("select id,ip,name,description from monitor_device where 1=1");
			if(null!=code&&code.trim().length()>0)
				queryString.append(" and TYPE_CODE=").append(code);
			if(null!=subnetId&&subnetId.length()>0){
				queryString.append(" and id not in( select DEVICE_ID from monitor_subnet_device where SUBNET_ID="+subnetId+")");
			}
			if(null!=fromIpAddress&&fromIpAddress.trim().length()>0)
				queryString .append(" and IP>'").append(fromIpAddress).append("'");
			if(null!=endIpAddress&&endIpAddress.trim().length()>0)
				queryString .append(" and IP<'").append(endIpAddress).append("'");
			return jdbcTemplate.queryForList(queryString.toString());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 已选设备
	 * @param selectedDeviceIp 已选择的设备Ip列表
	 * @return
	 */
	public List<MonitorDevice> getSelectedDevice(String[] selectedDeviceIp) {
		log.debug("finding all MonitorDevice instances");
		try {
			StringBuilder queryString = new StringBuilder("select id from monitor_device where 1=1");
			if(null!=selectedDeviceIp&&selectedDeviceIp.length>0){
				StringBuilder ips=new StringBuilder();
				for(String ip:selectedDeviceIp)
				{
					if(!ip.equals(""))
						ips.append("'"+ip+"',");
				}
				queryString.append(ips.length()>0?" and IP  in("+ips.substring(0, ips.length()-1)+")":"");
				 
				return jdbcTemplate.queryForList(queryString.toString());
			}
			else
				return null;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	/**
	 * 从已选设备中挑选设备类型为'路由'和'交换+路由'的设备
	 * @param selectedDeviceIp 已选设备
	 * @return 返回设备类型为'路由'和'交换+路由'的设备IP
	 */
	public List<String> getCenterDeviceIP(String[] selectedDeviceId){
		log.debug("finding  devices from the selected device ip which type is the 'router' and 'switch + router'  ");
		try {
			StringBuilder queryString = new StringBuilder("select ip from monitor_device where 1=1 and (TYPE_CODE=2 or TYPE_CODE=3)");
			if(null!=selectedDeviceId&&selectedDeviceId.length>0){
				StringBuilder selectedIds=new StringBuilder();
				for(String id:selectedDeviceId)
				{
					if(!id.equals(""))
						selectedIds.append(id+",");
				}
				queryString.append(selectedIds.length()>0?" and id  in("+selectedIds.substring(0, selectedIds.length()-1)+")":"");
				List list= jdbcTemplate.queryForList(queryString.toString());
				List<String> ips=new ArrayList<String>();
				Iterator it=list.iterator();
				while(it.hasNext()){
					Map result=(Map)it.next();
					ips.add((String) result.get("ip"));
				}
				return ips;
			}
			else
				return null;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	
	/**
	 * 每次计算机扫描结束后批量更新设备的计算技数、快照计算机数字段
	 * @param deviceList
	 * @return
	 */
	public int[] batchUpdate(List<MonitorDevice> monitorDeviceList) {
		log.debug("batchUpdate MonitorDevice");
		final List<MonitorDevice> devices = monitorDeviceList;
		String sql = "UPDATE monitor_device SET policy_id=?, archive_user_num=?, snap_user_num=? WHERE ID=?";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				if (devices.get(i).getMonitorAlertPolicy() == null) {
					ps.setNull(1, Types.INTEGER);
				} else {
					ps.setInt(1, devices.get(i).getMonitorAlertPolicy().getId());
				}
				ps.setInt(2, devices.get(i).getArchiveUserNum());
				ps.setInt(3, devices.get(i).getSnapUserNum());
				ps.setInt(4, devices.get(i).getId());
			}
			public int getBatchSize() {
				return devices.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
}