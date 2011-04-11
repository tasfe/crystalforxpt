package com.combanc.itsm.dao;


import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ServiceLevel;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServiceLevel entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see db.ServiceLevel
 * @author MyEclipse Persistence Tools
 */

public class ServiceLevelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ServiceLevelDAO.class);
	// property constants
	public static final String DEPART_ID = "departId";
	public static final String TITLE = "title";
	public static final String REQUEST_NO = "requestNo";
	public static final String ENGINEER_ID = "engineerId";
	public static final String CASE_ = "case_";
	public static final String DELAY_TIME = "delayTime";
	public static final String TOTLE_TIME = "totleTime";
	public static final String PLAN = "plan";

	public void save(ServiceLevel transientInstance) {
		log.debug("saving ServiceLevel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServiceLevel persistentInstance) {
		log.debug("deleting ServiceLevel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServiceLevel findById(java.lang.Integer id) {
		log.debug("getting ServiceLevel instance with id: " + id);
		try {
			ServiceLevel instance = (ServiceLevel) getSession().get(
					"com.combanc.itsm.pojo.ServiceLevel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServiceLevel instance) {
		log.debug("finding ServiceLevel instance by example");
		try {
			List results = getSession().createCriteria("db.ServiceLevel").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ServiceLevel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServiceLevel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDepartId(Object departId) {
		return findByProperty(DEPART_ID, departId);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByRequestNo(Object requestNo) {
		return findByProperty(REQUEST_NO, requestNo);
	}

	public List findByEngineerId(Object engineerId) {
		return findByProperty(ENGINEER_ID, engineerId);
	}

	public List findByCase_(Object case_) {
		return findByProperty(CASE_, case_);
	}

	public List findByDelayTime(Object delayTime) {
		return findByProperty(DELAY_TIME, delayTime);
	}

	public List findByTotleTime(Object totleTime) {
		return findByProperty(TOTLE_TIME, totleTime);
	}

	public List findByPlan(Object plan) {
		return findByProperty(PLAN, plan);
	}

	public List findAll() {
		log.debug("finding all ServiceLevel instances");
		try {
			String queryString = "from ServiceLevel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServiceLevel merge(ServiceLevel detachedInstance) {
		log.debug("merging ServiceLevel instance");
		try {
			ServiceLevel result = (ServiceLevel) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServiceLevel instance) {
		log.debug("attaching dirty ServiceLevel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServiceLevel instance) {
		log.debug("attaching clean ServiceLevel instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}