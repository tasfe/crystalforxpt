package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorComputer;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorComputer entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorComputer
 * @author MyEclipse Persistence Tools
 */

public class MonitorComputerDAO extends
		BaseHibernateDAO<MonitorComputer, Integer> {
	private static final Log log = LogFactory.getLog(MonitorComputerDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String MAC = "mac";
	public static final String DEVICE_PORT = "devicePort";
	public static final String DEVICE_INTERFACE = "deviceInterface";
	public static final String INTERFACE_DESC = "interfaceDesc";
	public static final String HOST_NAME = "hostName";
	public static final String NAME = "name";
	public static final String DOMAIN = "domain";
	public static final String LOGIN_NAME = "loginName";
	public static final String SEGMENT = "segment";
	public static final String USER = "user";
	public static final String PLACE = "place";
	public static final String DEPARTMENT = "department";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorComputer transientInstance) {
		log.debug("saving MonitorComputer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorComputer persistentInstance) {
		log.debug("deleting MonitorComputer instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorComputer findById(java.lang.Integer id) {
		log.debug("getting MonitorComputer instance with id: " + id);
		try {
			MonitorComputer instance = (MonitorComputer) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorComputer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorComputer instance) {
		log.debug("finding MonitorComputer instance by example");
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
		log.debug("finding MonitorComputer instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorComputer as model where model."
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

	public List findByMac(Object mac) {
		return findByProperty(MAC, mac);
	}

	public List findByDevicePort(Object devicePort) {
		return findByProperty(DEVICE_PORT, devicePort);
	}

	public List findByDeviceInterface(Object deviceInterface) {
		return findByProperty(DEVICE_INTERFACE, deviceInterface);
	}

	public List findByInterfaceDesc(Object interfaceDesc) {
		return findByProperty(INTERFACE_DESC, interfaceDesc);
	}

	public List findByHostName(Object hostName) {
		return findByProperty(HOST_NAME, hostName);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByDomain(Object domain) {
		return findByProperty(DOMAIN, domain);
	}

	public List findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	public List findBySegment(Object segment) {
		return findByProperty(SEGMENT, segment);
	}

	public List findByUser(Object user) {
		return findByProperty(USER, user);
	}

	public List findByPlace(Object place) {
		return findByProperty(PLACE, place);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
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
		log.debug("finding all MonitorComputer instances");
		try {
			String queryString = "from MonitorComputer";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorComputer merge(MonitorComputer detachedInstance) {
		log.debug("merging MonitorComputer instance");
		try {
			MonitorComputer result = (MonitorComputer) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorComputer instance) {
		log.debug("attaching dirty MonitorComputer instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorComputer instance) {
		log.debug("attaching clean MonitorComputer instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorComputerDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorComputerDAO) ctx.getBean("MonitorComputerDAO");
	}

	// 删除指定日期之前的入网计算机档案
	public void delBeforeDate(Timestamp time) {
		String logStr = "delete monitor_computer before Date  ";
		log.debug(logStr + time.toString());
		try {
			String[] sql = new String[1];
			sql[0] = "DELETE FROM monitor_computer WHERE discovery_time < '" + time + "'";
			jdbcTemplate.batchUpdate(sql);

			log.debug(logStr + time.toString() + "  successful");
		} catch (RuntimeException re) {
			log.error(logStr + time.toString() + "  failed", re);
			throw re;
		}
	}

	// 按设备IP归类，返回各个设备下的计算机数
	// 格式：DEVICE_ID, count
	public List getComputerCount() {
		String logStr = "get computer count list " ;
		log.debug(logStr);
		List rows = null;
		try {
			String  sql = "SELECT DEVICE_ID, count(ID) as num from monitor_computer group by DEVICE_ID";
			rows = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows; 
	}
	
	//2009.07.28 批量插入计算机记录
	// "IP" MAC" 上连设备")); "端口")); 接口")); ("接口描述")); "计算机名"))" +
	// "; "域或组")); "登录名")); "主机名")); "最近发现时间")); subnetId));
	public int[] batchInsert(List<MonitorComputer> monitorComputerList){
		log.debug("batchInsert MonitorComputer");
		final List<MonitorComputer> computer = monitorComputerList;
		String sql = "INSERT INTO monitor_computer (ip, mac, device_id, device_port, device_interface, interface_description, host_name, computer_name,domain, login_name, discovery_time, snapshot) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, computer.get(i).getIp());
				ps.setString(2, computer.get(i).getMac());
				ps.setInt(3, computer.get(i).getMonitorDevice().getId());
				ps.setString(4, computer.get(i).getDevicePort());
				ps.setString(5, computer.get(i).getDeviceInterface());
				ps.setString(6, computer.get(i).getInterfaceDescription());
				ps.setString(7, computer.get(i).getHostName());
				ps.setString(8, computer.get(i).getComputerName());
				ps.setString(9, computer.get(i).getDomain());
				ps.setString(10, computer.get(i).getLoginName());
				ps.setTimestamp(11, computer.get(i).getDiscoveryTime());
				ps.setBoolean(12, computer.get(i).getSnapshot());
			}
			public int getBatchSize() {
				return computer.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
	//2009.08.17 批量更新计算机记录
	//"IP" MAC" 上连设备")); "端口")); 接口")); ("接口描述")); "计算机名"))" +
	//		"; "域或组")); "登录名")); "主机名")); "最近发现时间")); subnetId)); 			
	public int[] batchUpdate(List<MonitorComputer> monitorComputerList){
		log.debug("batchUpdate MonitorComputer");
		final List<MonitorComputer> computer = monitorComputerList;
		String sql = "UPDATE monitor_computer SET ip=?, device_id=?, device_port=?, " 
			+ "device_interface=?, interface_description=?, host_name=?, " 
			+ "computer_name=?, domain=?, login_name=?, discovery_time=?, user=?, " 
			+ "place=?, department=?, registered=?, snapshot=? WHERE mac = ?";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, computer.get(i).getIp());
				ps.setInt(2, computer.get(i).getMonitorDevice().getId());
				ps.setString(3, computer.get(i).getDevicePort());
				ps.setString(4, computer.get(i).getDeviceInterface());
				ps.setString(5, computer.get(i).getInterfaceDescription());
				ps.setString(6, computer.get(i).getHostName());
				ps.setString(7, computer.get(i).getComputerName());
				ps.setString(8, computer.get(i).getDomain());
				ps.setString(9, computer.get(i).getLoginName());
				ps.setTimestamp(10, computer.get(i).getDiscoveryTime());
				ps.setString(11, computer.get(i).getUser());
				ps.setString(12, computer.get(i).getPlace());
				ps.setString(13, computer.get(i).getDepartment());
				ps.setBoolean(14, computer.get(i).getRegistered());
				ps.setBoolean(15, computer.get(i).getSnapshot());
				ps.setString(16, computer.get(i).getMac());
			}
			public int getBatchSize() {
				return computer.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}

}