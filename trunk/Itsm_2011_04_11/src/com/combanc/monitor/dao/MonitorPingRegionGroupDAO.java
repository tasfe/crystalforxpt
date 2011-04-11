package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorPingDestType;
import com.combanc.monitor.pojo.MonitorPingRegionGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorPingRegionGroup entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorPingRegionGroup
 * @author MyEclipse Persistence Tools
 */

public class MonitorPingRegionGroupDAO extends BaseHibernateDAO<MonitorPingRegionGroup, Integer>   {
	private static final Log log = LogFactory.getLog(MonitorPingRegionGroupDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorPingRegionGroup transientInstance) {
		log.debug("saving MonitorPingRegionGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorPingRegionGroup persistentInstance) {
		log.debug("deleting MonitorPingRegionGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorPingRegionGroup findById(java.lang.Integer id) {
		log.debug("getting MonitorPingRegionGroup instance with id: " + id);
		try {
			MonitorPingRegionGroup instance = (MonitorPingRegionGroup) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorPingRegionGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorPingRegionGroup instance) {
		log.debug("finding MonitorPingRegionGroup instance by example");
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
		log.debug("finding MonitorPingRegionGroup instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorPingRegionGroup as model where model."
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
		log.debug("finding all MonitorPingRegionGroup instances");
		try {
			String queryString = "from MonitorPingRegionGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorPingRegionGroup merge(MonitorPingRegionGroup detachedInstance) {
		log.debug("merging MonitorPingRegionGroup instance");
		try {
			MonitorPingRegionGroup result = (MonitorPingRegionGroup) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorPingRegionGroup instance) {
		log.debug("attaching dirty MonitorPingRegionGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorPingRegionGroup instance) {
		log.debug("attaching clean MonitorPingRegionGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorPingRegionGroupDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorPingRegionGroupDAO) ctx
				.getBean("MonitorPingRegionGroupDAO");
	}
}