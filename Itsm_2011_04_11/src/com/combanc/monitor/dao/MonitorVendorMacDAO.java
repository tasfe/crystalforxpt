package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorVendorMac;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorVendorMac entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorVendorMac
 * @author MyEclipse Persistence Tools
 */

public class MonitorVendorMacDAO extends
		BaseHibernateDAO<MonitorVendorMac, Integer> {
	private static final Log log = LogFactory.getLog(MonitorVendorMacDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String MAC = "mac";
	public static final String ISUSE = "isuse";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorVendorMac transientInstance) {
		log.debug("saving MonitorVendorMac instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorVendorMac persistentInstance) {
		log.debug("deleting MonitorVendorMac instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorVendorMac findById(java.lang.Integer id) {
		log.debug("getting MonitorVendorMac instance with id: " + id);
		try {
			MonitorVendorMac instance = (MonitorVendorMac) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorVendorMac", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorVendorMac instance) {
		log.debug("finding MonitorVendorMac instance by example");
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
		log.debug("finding MonitorVendorMac instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorVendorMac as model where model."
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

	public List findByMac(Object mac) {
		return findByProperty(MAC, mac);
	}

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findAll() {
		log.debug("finding all MonitorVendorMac instances");
		try {
			String queryString = "from MonitorVendorMac";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorVendorMac merge(MonitorVendorMac detachedInstance) {
		log.debug("merging MonitorVendorMac instance");
		try {
			MonitorVendorMac result = (MonitorVendorMac) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorVendorMac instance) {
		log.debug("attaching dirty MonitorVendorMac instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorVendorMac instance) {
		log.debug("attaching clean MonitorVendorMac instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorVendorMacDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorVendorMacDAO) ctx.getBean("MonitorVendorMacDAO");
	}
}