package com.combanc.monitor.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorArpLog;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorAccessLog entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.motnitor.pojo.MonitorAccessLog
 * @author MyEclipse Persistence Tools
 */

public class MonitorArpLogDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(MonitorArpLogDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String MAC = "mac";
	public static final String UP_DEVICE_IP = "upDeviceIp";
	public static final String INTERFACE_DESC = "interfaceDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorArpLog transientInstance) {
		log.debug("saving MonitorArpLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorArpLog persistentInstance) {
		log.debug("deleting MonitorArpLog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorArpLog findById(java.lang.Integer id) {
		log.debug("getting MonitorArpLog instance with id: " + id);
		try {
			MonitorArpLog instance = (MonitorArpLog) getHibernateTemplate()
					.get("com.combanc.motnitor.pojo.MonitorArpLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorArpLog instance) {
		log.debug("finding MonitorArpLog instance by example");
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
		log.debug("finding MonitorArpLog instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorArpLog as model where model."
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

	public List findByUpDeviceIp(Object upDeviceIp) {
		return findByProperty(UP_DEVICE_IP, upDeviceIp);
	}

	public List findByInterfaceDesc(Object interfaceDesc) {
		return findByProperty(INTERFACE_DESC, interfaceDesc);
	}

	public List findAll() {
		log.debug("finding all MonitorArpLog instances");
		try {
			String queryString = "from MonitorArpLog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorArpLog merge(MonitorArpLog detachedInstance) {
		log.debug("merging MonitorArpLog instance");
		try {
			MonitorArpLog result = (MonitorArpLog) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorArpLog instance) {
		log.debug("attaching dirty MonitorArpLog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorArpLog instance) {
		log.debug("attaching clean MonitorArpLog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorArpLogDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorArpLogDAO) ctx.getBean("MonitorArpLogDAO");
	}
}