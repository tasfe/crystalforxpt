package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojoext.MonitorInterfaceCacheExt;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorInterfaceCache entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorInterfaceCache
 * @author MyEclipse Persistence Tools
 */

public class MonitorInterfaceCacheDAO extends
		BaseHibernateDAO<MonitorInterfaceCache, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorInterfaceCacheDAO.class);
	
	private DeviceSnmpQuery deviceSnmpQuery;
	
	// property constants
	public static final String IP = "ip";
	public static final String PORT = "port";
	public static final String INTERFACE_ = "interface_";
	public static final String DESCRIPTION = "description";
	public static final String NOTE = "note";
	public static final String NOTECN = "notecn";
	public static final String LLOCATE = "llocate";
	public static final String USER = "user";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE1_1 = "note1_1";
	public static final String NOTE2_1 = "note2_1";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}
	
	protected void initDao() {
		// do nothing
	}

	public void save(MonitorInterfaceCache transientInstance) {
		log.debug("saving MonitorInterfaceCache instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorInterfaceCache persistentInstance) {
		log.debug("deleting MonitorInterfaceCache instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorInterfaceCache findById(java.lang.Integer id) {
		log.debug("getting MonitorInterfaceCache instance with id: " + id);
		try {
			MonitorInterfaceCache instance = (MonitorInterfaceCache) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorInterfaceCache", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorInterfaceCache instance) {
		log.debug("finding MonitorInterfaceCache instance by example");
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
		log.debug("finding MonitorInterfaceCache instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorInterfaceCache as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List findByInterface_(Object interface_) {
		return findByProperty(INTERFACE_, interface_);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByNotecn(Object notecn) {
		return findByProperty(NOTECN, notecn);
	}

	public List findByLlocate(Object llocate) {
		return findByProperty(LLOCATE, llocate);
	}

	public List findByUser(Object user) {
		return findByProperty(USER, user);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findByNote1_1(Object note1_1) {
		return findByProperty(NOTE1_1, note1_1);
	}

	public List findByNote2_1(Object note2_1) {
		return findByProperty(NOTE2_1, note2_1);
	}

	public List findByNote3(Object note3) {
		return findByProperty(NOTE3, note3);
	}

	public List findByNote4(Object note4) {
		return findByProperty(NOTE4, note4);
	}

	public List findAll() {
		log.debug("finding all MonitorInterfaceCache instances");
		try {
			String queryString = "from MonitorInterfaceCache";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorInterfaceCache merge(MonitorInterfaceCache detachedInstance) {
		log.debug("merging MonitorInterfaceCache instance");
		try {
			MonitorInterfaceCache result = (MonitorInterfaceCache) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorInterfaceCache instance) {
		log.debug("attaching dirty MonitorInterfaceCache instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorInterfaceCache instance) {
		log.debug("attaching clean MonitorInterfaceCache instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorInterfaceCacheDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorInterfaceCacheDAO) ctx
				.getBean("MonitorInterfaceCacheDAO");
	}

	public int[] batchInsert(final List<MonitorInterfaceCache> interfaces) {
		log.debug("inserting deviceInterface ...");
		try {
			//String sql = "INSERT INTO user (name,age) VALUES(?,?)";

			String sql = "INSERT INTO monitor_interface_cache (device_id,port,interface,description,note,llocate,user,note1,note2) VALUES(?,?,?,?,?,?,?,?,?)";
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			        public void setValues(PreparedStatement ps, int i) throws SQLException {
			        	MonitorInterfaceCache mic = (MonitorInterfaceCache)interfaces.get(i);
			            ps.setInt(1, mic.getMonitorDevice().getId());
			            ps.setString(2, mic.getPort());
			            ps.setString(3, mic.getInterface_());
			            ps.setString(4, mic.getDescription());
			            ps.setString(5, null == mic.getNote()?"" :mic.getNote());
			            ps.setString(6, null == mic.getLlocate()?"" :mic.getLlocate());
			            ps.setString(7, null == mic.getUser()?"" :mic.getUser() );
			            ps.setString(8, null == mic.getNote1()?"" :mic.getNote1() );
			            ps.setString(9, null == mic.getNote2()?"" :mic.getNote2() );
			        }
			      public int getBatchSize() {
			            return interfaces.size();
			        }
			      };
			log.debug("insert successful");
			return jdbcTemplate.batchUpdate(sql, setter);
		} catch (RuntimeException re) {
			log.error("insert failed", re);
			throw re;
		}
	}

	public List<MonitorInterfaceCache> findByDeviceId(Integer deviceId) {
		return findByProperty(MonitorInterfaceCache.class, "monitorDevice.id", (Object)deviceId);
	}
	
	public List findByDeviceAndInterface(Integer deviceId, String interface_) {
		log.debug("finding MonitorInterfaceCache instance with deviceId: "
				+ deviceId + ", interface: " + interface_);
		try {
			String queryString = "from MonitorInterfaceCache as model where model.monitorDevice.id="
					+ deviceId + " and interface_="+interface_;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 根据计算机表查找出所有有计算机连接的接口，并计算接口pc数目
	 * @param deviceId
	 * @param computerList
	 * @return
	 */
	public List<MonitorInterfaceCacheExt> findByDeviceIdAvailable(
			MonitorDevice mDevice, List<MonitorComputer> computerList) {
		if(computerList == null || computerList.size() == 0)
			return null;
		List<MonitorInterfaceCache> interfaceCacheList = this.findByDeviceId(mDevice.getId());
		if(interfaceCacheList == null || interfaceCacheList.size() == 0) {
			deviceSnmpQuery.readDeviceInterface(mDevice, interfaceCacheList, false);
			if(interfaceCacheList == null || interfaceCacheList.size() == 0)
				return null;
		}
		List<MonitorInterfaceCacheExt> interfaceCacheExtList =  new ArrayList<MonitorInterfaceCacheExt>();
		for(MonitorInterfaceCache mic : interfaceCacheList) {
			MonitorInterfaceCacheExt mice = new MonitorInterfaceCacheExt();
			mice.setIp(mic.getMonitorDevice().getIp());
			mice.setPort(mic.getPort());
			mice.setInterface_(mic.getInterface_());
			mice.setDescription(mic.getDescription());
			interfaceCacheExtList.add(mice);
		}
		for(MonitorComputer mc : computerList) {
			for(MonitorInterfaceCacheExt mice : interfaceCacheExtList) {
				if(mice.getInterface_().equals(mc.getDeviceInterface())) {
					mice.setPccount(mice.getPccount() + 1);
					break;
				}
			}
		}
		
		for(int i = interfaceCacheExtList.size() - 1; i >= 0; i--) {
			MonitorInterfaceCacheExt mice = interfaceCacheExtList.get(i);
			if(mice.getPccount() == 0)
				interfaceCacheExtList.remove(mice);
			else
				System.out.println(mice.getInterface_() + " 用户数： " + mice.getPccount());
		}
		
		return interfaceCacheExtList;
	}
}