package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorSystemParam;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorSystemParam entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorSystemParam
 * @author MyEclipse Persistence Tools
 */

public class MonitorSystemParamDAO extends
		BaseHibernateDAO<MonitorSystemParam, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorSystemParamDAO.class);
	// property constants
	public static final String PARAM = "param";
	public static final String VALUE = "value";
	public static final String NOTE = "note";
	public static final String TYPE = "type";
	public static final String ISUSE = "isuse";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorSystemParam transientInstance) {
		log.debug("saving MonitorSystemParam instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorSystemParam persistentInstance) {
		log.debug("deleting MonitorSystemParam instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorSystemParam findById(java.lang.String id) {
		log.debug("getting MonitorSystemParam instance with id: " + id);
		try {
			MonitorSystemParam instance = (MonitorSystemParam) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorSystemParam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorSystemParam instance) {
		log.debug("finding MonitorSystemParam instance by example");
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
		log.debug("finding MonitorSystemParam instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorSystemParam as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByParam(Object param) {
		return findByProperty(PARAM, param);
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findAll() {
		log.debug("finding all MonitorSystemParam instances");
		try {
			String queryString = "from MonitorSystemParam";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorSystemParam merge(MonitorSystemParam detachedInstance) {
		log.debug("merging MonitorSystemParam instance");
		try {
			MonitorSystemParam result = (MonitorSystemParam) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorSystemParam instance) {
		log.debug("attaching dirty MonitorSystemParam instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorSystemParam instance) {
		log.debug("attaching clean MonitorSystemParam instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorSystemParamDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorSystemParamDAO) ctx.getBean("MonitorSystemParamDAO");
	}
}