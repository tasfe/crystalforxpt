package com.combanc.monitor.dao;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorV3Auth;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorV3Auth entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorV3Auth
 * @author MyEclipse Persistence Tools
 */

public class MonitorV3AuthDAO extends BaseHibernateDAO<MonitorV3Auth, Integer> {
	private static final Log log = LogFactory.getLog(MonitorV3AuthDAO.class);
	// property constants
	public static final String PROTOCOL = "protocol";
	public static final String NAME = "name";
	public static final String NOTE = "note";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorV3Auth transientInstance) {
		log.debug("saving MonitorV3Auth instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorV3Auth persistentInstance) {
		log.debug("deleting MonitorV3Auth instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorV3Auth findById(java.lang.Integer id) {
		log.debug("getting MonitorV3Auth instance with id: " + id);
		try {
			MonitorV3Auth instance = (MonitorV3Auth) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorV3Auth", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorV3Auth instance) {
		log.debug("finding MonitorV3Auth instance by example");
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
		log.debug("finding MonitorV3Auth instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorV3Auth as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProtocol(Object protocol) {
		return findByProperty(PROTOCOL, protocol);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findAll() {
		log.debug("finding all MonitorV3Auth instances");
		try {
			String queryString = "from MonitorV3Auth";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorV3Auth merge(MonitorV3Auth detachedInstance) {
		log.debug("merging MonitorV3Auth instance");
		try {
			MonitorV3Auth result = (MonitorV3Auth) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorV3Auth instance) {
		log.debug("attaching dirty MonitorV3Auth instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorV3Auth instance) {
		log.debug("attaching clean MonitorV3Auth instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorV3AuthDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorV3AuthDAO) ctx.getBean("MonitorV3AuthDAO");
	}
}