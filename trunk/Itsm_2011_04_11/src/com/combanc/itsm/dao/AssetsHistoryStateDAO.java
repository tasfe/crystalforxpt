package com.combanc.itsm.dao;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsHistoryState;

/**
 * A data access object (DAO) providing persistence and search support for
 * HistoryDeviceState entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.AssetsHistoryState
 * @author MyEclipse Persistence Tools
 */

public class AssetsHistoryStateDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AssetsHistoryStateDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String STATE = "state";
	public static final String PERSION_ID = "persionId";

	protected void initDao() {
		// do nothing
	}

	public void save(AssetsHistoryState transientInstance) {
		log.debug("saving AssetsHistoryState instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetsHistoryState persistentInstance) {
		log.debug("deleting AssetsHistoryState instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssetsHistoryState findById(java.lang.Long id) {
		log.debug("getting AssetsHistoryState instance with id: " + id);
		try {
			AssetsHistoryState instance = (AssetsHistoryState) getHibernateTemplate()
					.get("com.combanc.itsm.dao.AssetsHistoryState", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsHistoryState instance) {
		log.debug("finding AssetsHistoryState instance by example");
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
		log.debug("finding AssetsHistoryState instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssetsHistoryState as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByPersionId(Object persionId) {
		return findByProperty(PERSION_ID, persionId);
	}

	public List findAll() {
		log.debug("finding all AssetsHistoryState instances");
		try {
			String queryString = "from AssetsHistoryState";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetsHistoryState merge(AssetsHistoryState detachedInstance) {
		log.debug("merging AssetsHistoryState instance");
		try {
			AssetsHistoryState result = (AssetsHistoryState) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsHistoryState instance) {
		log.debug("attaching dirty AssetsHistoryState instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsHistoryState instance) {
		log.debug("attaching clean AssetsHistoryState instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsHistoryStateDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssetsHistoryStateDAO) ctx.getBean("AssetsHistoryState");
	}
}