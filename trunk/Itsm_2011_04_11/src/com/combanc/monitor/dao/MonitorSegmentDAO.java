package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorSegment;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorSegment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorSegment
 * @author MyEclipse Persistence Tools
 */

public class MonitorSegmentDAO extends
		BaseHibernateDAO<MonitorSegment, Integer> {
	private static final Log log = LogFactory.getLog(MonitorSegmentDAO.class);
	// property constants
	public static final String NETWORK_SEGMENT = "networkSegment";
	public static final String MASK = "mask";
	public static final String CONNECTED_DEVICE = "connectedDevice";
	public static final String DEVICE_PORT = "devicePort";
	public static final String DEVICE_INTERFACE = "deviceInterface";
	public static final String INTERFACE_DESCRIPTION = "interfaceDescription";
	public static final String NEXT_HOP = "nextHop";
	public static final String TYPE = "type";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorSegment transientInstance) {
		log.debug("saving MonitorSegment instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorSegment persistentInstance) {
		log.debug("deleting MonitorSegment instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorSegment findById(java.lang.Integer id) {
		log.debug("getting MonitorSegment instance with id: " + id);
		try {
			MonitorSegment instance = (MonitorSegment) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorSegment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorSegment instance) {
		log.debug("finding MonitorSegment instance by example");
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
		log.debug("finding MonitorSegment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorSegment as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNetworkSegment(Object networkSegment) {
		return findByProperty(NETWORK_SEGMENT, networkSegment);
	}

	public List findByMask(Object mask) {
		return findByProperty(MASK, mask);
	}

	public List findByConnectedDevice(Object connectedDevice) {
		return findByProperty(CONNECTED_DEVICE, connectedDevice);
	}

	public List findByDevicePort(Object devicePort) {
		return findByProperty(DEVICE_PORT, devicePort);
	}

	public List findByDeviceInterface(Object deviceInterface) {
		return findByProperty(DEVICE_INTERFACE, deviceInterface);
	}

	public List findByInterfaceDescription(Object interfaceDescription) {
		return findByProperty(INTERFACE_DESCRIPTION, interfaceDescription);
	}

	public List findByNextHop(Object nextHop) {
		return findByProperty(NEXT_HOP, nextHop);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
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
		log.debug("finding all MonitorSegment instances");
		try {
			String queryString = "from MonitorSegment";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorSegment merge(MonitorSegment detachedInstance) {
		log.debug("merging MonitorSegment instance");
		try {
			MonitorSegment result = (MonitorSegment) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorSegment instance) {
		log.debug("attaching dirty MonitorSegment instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorSegment instance) {
		log.debug("attaching clean MonitorSegment instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorSegmentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorSegmentDAO) ctx.getBean("MonitorSegmentDAO");
	}
}