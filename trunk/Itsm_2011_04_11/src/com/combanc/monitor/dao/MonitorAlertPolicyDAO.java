package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorAlertPolicy;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorAlertPolicy entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorAlertPolicy
 * @author MyEclipse Persistence Tools
 */

public class MonitorAlertPolicyDAO extends
		BaseHibernateDAO<MonitorAlertPolicy, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorAlertPolicyDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";
	public static final String CHECK_GAP = "checkGap";
	public static final String ERROR_GAP = "errorGap";
	public static final String ERROR_RETRY = "errorRetry";
	public static final String LIMEN_RETRY = "limenRetry";
	public static final String TEMP_RETRY = "tempRetry";
	public static final String DAY_OF_WEEK = "dayOfWeek";
	public static final String DAILY_START = "dailyStart";
	public static final String DAILY_END = "dailyEnd";
	public static final String MOBILE_SWITCH = "mobileSwitch";
	public static final String EMAIL_SWITCH = "emailSwitch";
	public static final String SOUND_SWITCH = "soundSwitch";
	public static final String ALERT_RECEIVERS = "alertReceivers";
	public static final String ALERT_TYPES = "alertTypes";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorAlertPolicy transientInstance) {
		log.debug("saving MonitorAlertPolicy instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorAlertPolicy persistentInstance) {
		log.debug("deleting MonitorAlertPolicy instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorAlertPolicy findById(java.lang.Integer id) {
		log.debug("getting MonitorAlertPolicy instance with id: " + id);
		try {
			MonitorAlertPolicy instance = (MonitorAlertPolicy) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorAlertPolicy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorAlertPolicy instance) {
		log.debug("finding MonitorAlertPolicy instance by example");
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
		log.debug("finding MonitorAlertPolicy instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorAlertPolicy as model where model."
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

	public List findByCheckGap(Object checkGap) {
		return findByProperty(CHECK_GAP, checkGap);
	}

	public List findByErrorGap(Object errorGap) {
		return findByProperty(ERROR_GAP, errorGap);
	}

	public List findByErrorRetry(Object errorRetry) {
		return findByProperty(ERROR_RETRY, errorRetry);
	}

	public List findByLimenRetry(Object limenRetry) {
		return findByProperty(LIMEN_RETRY, limenRetry);
	}

	public List findByTempRetry(Object tempRetry) {
		return findByProperty(TEMP_RETRY, tempRetry);
	}

	public List findByDayOfWeek(Object dayOfWeek) {
		return findByProperty(DAY_OF_WEEK, dayOfWeek);
	}

	public List findByDailyStart(Object dailyStart) {
		return findByProperty(DAILY_START, dailyStart);
	}

	public List findByDailyEnd(Object dailyEnd) {
		return findByProperty(DAILY_END, dailyEnd);
	}

	public List findByMobileSwitch(Object mobileSwitch) {
		return findByProperty(MOBILE_SWITCH, mobileSwitch);
	}

	public List findByEmailSwitch(Object emailSwitch) {
		return findByProperty(EMAIL_SWITCH, emailSwitch);
	}

	public List findBySoundSwitch(Object soundSwitch) {
		return findByProperty(SOUND_SWITCH, soundSwitch);
	}

	public List findByAlertReceivers(Object alertReceivers) {
		return findByProperty(ALERT_RECEIVERS, alertReceivers);
	}

	public List findByAlertTypes(Object alertTypes) {
		return findByProperty(ALERT_TYPES, alertTypes);
	}

	public List findAll() {
		log.debug("finding all MonitorAlertPolicy instances");
		try {
			String queryString = "from MonitorAlertPolicy";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorAlertPolicy merge(MonitorAlertPolicy detachedInstance) {
		log.debug("merging MonitorAlertPolicy instance");
		try {
			MonitorAlertPolicy result = (MonitorAlertPolicy) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorAlertPolicy instance) {
		log.debug("attaching dirty MonitorAlertPolicy instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorAlertPolicy instance) {
		log.debug("attaching clean MonitorAlertPolicy instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorAlertPolicyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorAlertPolicyDAO) ctx.getBean("MonitorAlertPolicyDAO");
	}
}