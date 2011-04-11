package com.combanc.monitor.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorAccessLog;

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

public class MonitorAccessLogDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(MonitorAccessLogDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String MAC = "mac";
	public static final String UP_DEVICE_IP = "upDeviceIp";
	public static final String INTERFACE_DESC = "interfaceDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorAccessLog transientInstance) {
		log.debug("saving MonitorAccessLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorAccessLog persistentInstance) {
		log.debug("deleting MonitorAccessLog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorAccessLog findById(java.lang.Integer id) {
		log.debug("getting MonitorAccessLog instance with id: " + id);
		try {
			MonitorAccessLog instance = (MonitorAccessLog) getHibernateTemplate()
					.get("com.combanc.motnitor.pojo.MonitorAccessLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorAccessLog instance) {
		log.debug("finding MonitorAccessLog instance by example");
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
		log.debug("finding MonitorAccessLog instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorAccessLog as model where model."
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
		log.debug("finding all MonitorAccessLog instances");
		try {
			String queryString = "from MonitorAccessLog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorAccessLog merge(MonitorAccessLog detachedInstance) {
		log.debug("merging MonitorAccessLog instance");
		try {
			MonitorAccessLog result = (MonitorAccessLog) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorAccessLog instance) {
		log.debug("attaching dirty MonitorAccessLog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorAccessLog instance) {
		log.debug("attaching clean MonitorAccessLog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorAccessLogDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorAccessLogDAO) ctx.getBean("MonitorAccessLogDAO");
	}
}