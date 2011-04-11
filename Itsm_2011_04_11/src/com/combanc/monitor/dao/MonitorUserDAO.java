package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorUser
 * @author MyEclipse Persistence Tools
 */

public class MonitorUserDAO extends BaseHibernateDAO<MonitorUser, Integer> {
	private static final Log log = LogFactory.getLog(MonitorUserDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String FULL_NAME = "fullName";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";
	public static final String MOBILE = "mobile";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorUser transientInstance) {
		log.debug("saving MonitorUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorUser persistentInstance) {
		log.debug("deleting MonitorUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorUser findById(java.lang.Integer id) {
		log.debug("getting MonitorUser instance with id: " + id);
		try {
			MonitorUser instance = (MonitorUser) getHibernateTemplate().get(
					"com.combanc.monitor.pojo.MonitorUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorUser instance) {
		log.debug("finding MonitorUser instance by example");
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
		log.debug("finding MonitorUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MonitorUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByFullName(Object fullName) {
		return findByProperty(FULL_NAME, fullName);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all MonitorUser instances");
		try {
			String queryString = "from MonitorUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorUser merge(MonitorUser detachedInstance) {
		log.debug("merging MonitorUser instance");
		try {
			MonitorUser result = (MonitorUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorUser instance) {
		log.debug("attaching dirty MonitorUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorUser instance) {
		log.debug("attaching clean MonitorUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorUserDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorUserDAO) ctx.getBean("MonitorUserDAO");
	}
}