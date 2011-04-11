package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorUrlService;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorUrlService entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorUrlService
 * @author MyEclipse Persistence Tools
 */

public class MonitorUrlServiceDAO extends
		BaseHibernateDAO<MonitorUrlService, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorUrlServiceDAO.class);
	// property constants
	public static final String PARAMETER = "parameter";
	public static final String DESCRIPTION = "description";
	public static final String MONITORINTERVAL = "monitorinterval";
	public static final String DAYOFWEEK = "dayofweek";
	public static final String DAILY_START = "dailyStart";
	public static final String DAILY_END = "dailyEnd";
	public static final String SEVERITY = "severity";
	public static final String ENABLED = "enabled";
	public static final String STATE = "state";
	public static final String LASTMSG = "lastmsg";
	public static final String DESC_TYPE = "descType";
	public static final String WONTEDDESC = "wonteddesc";
	public static final String ERRATUM_INTERVAL = "erratumInterval";
	public static final String ERRATUM_COUNT = "erratumCount";
	public static final String SRV_NAME = "srvName";
	public static final String STAMP = "stamp";
	public static final String LAST_STATE = "lastState";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorUrlService transientInstance) {
		log.debug("saving MonitorUrlService instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorUrlService persistentInstance) {
		log.debug("deleting MonitorUrlService instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorUrlService findById(java.lang.Integer id) {
		log.debug("getting MonitorUrlService instance with id: " + id);
		try {
			MonitorUrlService instance = (MonitorUrlService) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorUrlService", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorUrlService instance) {
		log.debug("finding MonitorUrlService instance by example");
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
		log.debug("finding MonitorUrlService instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorUrlService as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByParameter(Object parameter) {
		return findByProperty(PARAMETER, parameter);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByMonitorinterval(Object monitorinterval) {
		return findByProperty(MONITORINTERVAL, monitorinterval);
	}

	public List findByDayofweek(Object dayofweek) {
		return findByProperty(DAYOFWEEK, dayofweek);
	}

	public List findByDailyStart(Object dailyStart) {
		return findByProperty(DAILY_START, dailyStart);
	}

	public List findByDailyEnd(Object dailyEnd) {
		return findByProperty(DAILY_END, dailyEnd);
	}

	public List findBySeverity(Object severity) {
		return findByProperty(SEVERITY, severity);
	}

	public List findByEnabled(Object enabled) {
		return findByProperty(ENABLED, enabled);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByLastmsg(Object lastmsg) {
		return findByProperty(LASTMSG, lastmsg);
	}

	public List findByDescType(Object descType) {
		return findByProperty(DESC_TYPE, descType);
	}

	public List findByWonteddesc(Object wonteddesc) {
		return findByProperty(WONTEDDESC, wonteddesc);
	}

	public List findByErratumInterval(Object erratumInterval) {
		return findByProperty(ERRATUM_INTERVAL, erratumInterval);
	}

	public List findByErratumCount(Object erratumCount) {
		return findByProperty(ERRATUM_COUNT, erratumCount);
	}

	public List findBySrvName(Object srvName) {
		return findByProperty(SRV_NAME, srvName);
	}

	public List findByStamp(Object stamp) {
		return findByProperty(STAMP, stamp);
	}

	public List findByLastState(Object lastState) {
		return findByProperty(LAST_STATE, lastState);
	}

	public List findAll() {
		log.debug("finding all MonitorUrlService instances");
		try {
			String queryString = "from MonitorUrlService";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorUrlService merge(MonitorUrlService detachedInstance) {
		log.debug("merging MonitorUrlService instance");
		try {
			MonitorUrlService result = (MonitorUrlService) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorUrlService instance) {
		log.debug("attaching dirty MonitorUrlService instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorUrlService instance) {
		log.debug("attaching clean MonitorUrlService instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorUrlServiceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorUrlServiceDAO) ctx.getBean("MonitorUrlServiceDAO");
	}
}