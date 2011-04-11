package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.SystemLog;

/**
 * A data access object (DAO) providing persistence and search support for
 * SystemLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.dao.SystemLog
 * @author MyEclipse Persistence Tools
 */

public class SystemLogDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SystemLogDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String USER_NAME = "userName";
	public static final String LOGIN_IP = "loginIp";
	public static final String OPERATION = "operation";

	protected void initDao() {
		// do nothing
	}

	public void save(SystemLog transientInstance) {
		log.debug("saving SystemLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SystemLog persistentInstance) {
		log.debug("deleting SystemLog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SystemLog findById(java.lang.Long id) {
		log.debug("getting SystemLog instance with id: " + id);
		try {
			SystemLog instance = (SystemLog) getHibernateTemplate().get(
					"com.combanc.itsm.dao.SystemLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SystemLog instance) {
		log.debug("finding SystemLog instance by example");
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
		log.debug("finding SystemLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SystemLog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByLoginIp(Object loginIp) {
		return findByProperty(LOGIN_IP, loginIp);
	}

	public List findByOperation(Object operation) {
		return findByProperty(OPERATION, operation);
	}

	public List findAll() {
		log.debug("finding all SystemLog instances");
		try {
			String queryString = "from SystemLog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SystemLog merge(SystemLog detachedInstance) {
		log.debug("merging SystemLog instance");
		try {
			SystemLog result = (SystemLog) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SystemLog instance) {
		log.debug("attaching dirty SystemLog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SystemLog instance) {
		log.debug("attaching clean SystemLog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SystemLogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SystemLogDAO) ctx.getBean("SystemLogDAO");
	}
}