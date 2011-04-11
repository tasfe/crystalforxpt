package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorAlertType;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorAlertType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorAlertType
 * @author MyEclipse Persistence Tools
 */

public class MonitorAlertTypeDAO extends
		BaseHibernateDAO<MonitorAlertType, Integer> {
	private static final Log log = LogFactory.getLog(MonitorAlertTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ISUSE = "isuse";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorAlertType transientInstance) {
		log.debug("saving MonitorAlertType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorAlertType persistentInstance) {
		log.debug("deleting MonitorAlertType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorAlertType findById(java.lang.Integer id) {
		log.debug("getting MonitorAlertType instance with id: " + id);
		try {
			MonitorAlertType instance = (MonitorAlertType) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorAlertType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorAlertType instance) {
		log.debug("finding MonitorAlertType instance by example");
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
		log.debug("finding MonitorAlertType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorAlertType as model where model."
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

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findAll() {
		log.debug("finding all MonitorAlertType instances");
		try {
			String queryString = "from MonitorAlertType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorAlertType merge(MonitorAlertType detachedInstance) {
		log.debug("merging MonitorAlertType instance");
		try {
			MonitorAlertType result = (MonitorAlertType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorAlertType instance) {
		log.debug("attaching dirty MonitorAlertType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorAlertType instance) {
		log.debug("attaching clean MonitorAlertType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorAlertTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorAlertTypeDAO) ctx.getBean("MonitorAlertTypeDAO");
	}
}