package com.combanc.monitor.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorVendor;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorVendor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorVendor
 * @author MyEclipse Persistence Tools
 */

public class MonitorVendorDAO extends BaseHibernateDAO<MonitorVendor,Integer> {
	private static final Log log = LogFactory.getLog(MonitorVendorDAO.class);
	// property constants
	public static final String VENDOR = "vendor";
	public static final String CPU_OID = "cpuOid";
	public static final String TEMPERATURE = "temperature";
	public static final String NOTE = "note";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorVendor transientInstance) {
		log.debug("saving MonitorVendor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorVendor persistentInstance) {
		log.debug("deleting MonitorVendor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorVendor findById(java.lang.String id) {
		log.debug("getting MonitorVendor instance with id: " + id);
		try {
			MonitorVendor instance = (MonitorVendor) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorVendor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorVendor instance) {
		log.debug("finding MonitorVendor instance by example");
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
		log.debug("finding MonitorVendor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorVendor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByVendor(Object vendor) {
		return findByProperty(VENDOR, vendor);
	}
	public List findByCpuOid(Object cpuOid) {
		return findByProperty(CPU_OID, cpuOid);
	}

	public List findByTemperature(Object temperature) {
		return findByProperty(TEMPERATURE, temperature);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findAll() {
		log.debug("finding all MonitorVendor instances");
		try {
			String queryString = "from MonitorVendor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorVendor merge(MonitorVendor detachedInstance) {
		log.debug("merging MonitorVendor instance");
		try {
			MonitorVendor result = (MonitorVendor) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorVendor instance) {
		log.debug("attaching dirty MonitorVendor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorVendor instance) {
		log.debug("attaching clean MonitorVendor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorVendorDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorVendorDAO) ctx.getBean("MonitorVendorDAO");
	}
}