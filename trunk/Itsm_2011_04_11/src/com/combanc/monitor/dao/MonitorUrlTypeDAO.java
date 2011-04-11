package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorUrlType;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorUrlType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorUrlType
 * @author MyEclipse Persistence Tools
 */

public class MonitorUrlTypeDAO extends
		BaseHibernateDAO<MonitorUrlType, Integer> {
	private static final Log log = LogFactory.getLog(MonitorUrlTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CONFIGER = "configer";
	public static final String MONITOR = "monitor";
	public static final String EXTEND_OID = "extendOid";
	public static final String SORT = "sort";
	public static final String AUTO_DISCOVER = "autoDiscover";
	public static final String PORTS = "ports";
	public static final String TIMEOUT = "timeout";
	public static final String MONITOR_INTERVAL = "monitorInterval";
	public static final String ERRATUM_INTERVAL = "erratumInterval";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorUrlType transientInstance) {
		log.debug("saving MonitorUrlType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorUrlType persistentInstance) {
		log.debug("deleting MonitorUrlType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorUrlType findById(java.lang.String id) {
		log.debug("getting MonitorUrlType instance with id: " + id);
		try {
			MonitorUrlType instance = (MonitorUrlType) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorUrlType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorUrlType instance) {
		log.debug("finding MonitorUrlType instance by example");
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
		log.debug("finding MonitorUrlType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorUrlType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByConfiger(Object configer) {
		return findByProperty(CONFIGER, configer);
	}

	public List findByMonitor(Object monitor) {
		return findByProperty(MONITOR, monitor);
	}

	public List findByExtendOid(Object extendOid) {
		return findByProperty(EXTEND_OID, extendOid);
	}

	public List findBySort(Object sort) {
		return findByProperty(SORT, sort);
	}

	public List findByAutoDiscover(Object autoDiscover) {
		return findByProperty(AUTO_DISCOVER, autoDiscover);
	}

	public List findByPorts(Object ports) {
		return findByProperty(PORTS, ports);
	}

	public List findByTimeout(Object timeout) {
		return findByProperty(TIMEOUT, timeout);
	}

	public List findByMonitorInterval(Object monitorInterval) {
		return findByProperty(MONITOR_INTERVAL, monitorInterval);
	}

	public List findByErratumInterval(Object erratumInterval) {
		return findByProperty(ERRATUM_INTERVAL, erratumInterval);
	}

	public List findAll() {
		log.debug("finding all MonitorUrlType instances");
		try {
			String queryString = "from MonitorUrlType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorUrlType merge(MonitorUrlType detachedInstance) {
		log.debug("merging MonitorUrlType instance");
		try {
			MonitorUrlType result = (MonitorUrlType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorUrlType instance) {
		log.debug("attaching dirty MonitorUrlType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorUrlType instance) {
		log.debug("attaching clean MonitorUrlType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorUrlTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorUrlTypeDAO) ctx.getBean("MonitorUrlTypeDAO");
	}
}