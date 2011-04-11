package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ErrorType;

public class ErrorTypeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ErrorTypeDAO.class);
	public static final String NAME = "name";
	public static final String REASON = "reason";

	protected void initDao() {
	}

	public void save(ErrorType transientInstance) {
		log.debug("saving ErrorType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ErrorType persistentInstance) {
		log.debug("deleting ErrorType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ErrorType findById(java.lang.Integer id) {
		log.debug("getting ErrorType instance with id: " + id);
		try {
			ErrorType instance = (ErrorType) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.ErrorType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ErrorType instance) {
		log.debug("finding ErrorType instance by example");
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
		log.debug("finding ErrorType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ErrorType as model where model."
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

	public List findByReason(Object reason) {
		return findByProperty(REASON, reason);
	}

	public List findAll() {
		log.debug("finding all ErrorType instances");
		try {
			String queryString = "from ErrorType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ErrorType merge(ErrorType detachedInstance) {
		log.debug("merging ErrorType instance");
		try {
			ErrorType result = (ErrorType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ErrorType instance) {
		log.debug("attaching dirty ErrorType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ErrorType instance) {
		log.debug("attaching clean ErrorType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ErrorTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ErrorTypeDAO) ctx.getBean("ErrorTypeDAO");
	}
}