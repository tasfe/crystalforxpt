package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorBuilding;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorBuilding entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorBuilding
 * @author MyEclipse Persistence Tools
 */

public class MonitorBuildingDAO extends
		BaseHibernateDAO<MonitorBuilding, Integer> {
	private static final Log log = LogFactory.getLog(MonitorBuildingDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorBuilding transientInstance) {
		log.debug("saving MonitorBuilding instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorBuilding persistentInstance) {
		log.debug("deleting MonitorBuilding instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorBuilding findById(java.lang.Integer id) {
		log.debug("getting MonitorBuilding instance with id: " + id);
		try {
			MonitorBuilding instance = (MonitorBuilding) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorBuilding", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorBuilding instance) {
		log.debug("finding MonitorBuilding instance by example");
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
		log.debug("finding MonitorBuilding instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorBuilding as model where model."
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
		log.debug("finding all MonitorBuilding instances");
		try {
			String queryString = "from MonitorBuilding";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorBuilding merge(MonitorBuilding detachedInstance) {
		log.debug("merging MonitorBuilding instance");
		try {
			MonitorBuilding result = (MonitorBuilding) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorBuilding instance) {
		log.debug("attaching dirty MonitorBuilding instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorBuilding instance) {
		log.debug("attaching clean MonitorBuilding instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorBuildingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorBuildingDAO) ctx.getBean("MonitorBuildingDAO");
	}
}