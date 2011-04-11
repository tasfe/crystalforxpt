package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorBuildingZone;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorBuildingZone entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorBuildingZone
 * @author MyEclipse Persistence Tools
 */

public class MonitorBuildingZoneDAO extends
		BaseHibernateDAO<MonitorBuildingZone, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorBuildingZoneDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorBuildingZone transientInstance) {
		log.debug("saving MonitorBuildingZone instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorBuildingZone persistentInstance) {
		log.debug("deleting MonitorBuildingZone instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorBuildingZone findById(java.lang.Integer id) {
		log.debug("getting MonitorBuildingZone instance with id: " + id);
		try {
			MonitorBuildingZone instance = (MonitorBuildingZone) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorBuildingZone", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorBuildingZone instance) {
		log.debug("finding MonitorBuildingZone instance by example");
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
		log.debug("finding MonitorBuildingZone instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorBuildingZone as model where model."
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

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findAll() {
		log.debug("finding all MonitorBuildingZone instances");
		try {
			String queryString = "from MonitorBuildingZone";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorBuildingZone merge(MonitorBuildingZone detachedInstance) {
		log.debug("merging MonitorBuildingZone instance");
		try {
			MonitorBuildingZone result = (MonitorBuildingZone) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorBuildingZone instance) {
		log.debug("attaching dirty MonitorBuildingZone instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorBuildingZone instance) {
		log.debug("attaching clean MonitorBuildingZone instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorBuildingZoneDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorBuildingZoneDAO) ctx.getBean("MonitorBuildingZoneDAO");
	}
}