package com.combanc.monitor.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorPingResponseRecord;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorPingResponseRecord entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.combanc.monitor.pojo.MonitorPingResponseRecord
 * @author MyEclipse Persistence Tools
 */

public class MonitorPingResponseRecordDAO extends BaseHibernateDAO<MonitorPingResponseRecord, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorPingResponseRecordDAO.class);
	// property constants
	public static final String EXCUTE_COUNT = "excuteCount";
	public static final String RESPONSE_STATUS = "responseStatus";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorPingResponseRecord transientInstance) {
		log.debug("saving MonitorPingResponseRecord instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorPingResponseRecord persistentInstance) {
		log.debug("deleting MonitorPingResponseRecord instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorPingResponseRecord findById(java.lang.Integer id) {
		log.debug("getting MonitorPingResponseRecord instance with id: " + id);
		try {
			MonitorPingResponseRecord instance = (MonitorPingResponseRecord) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorPingResponseRecord",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorPingResponseRecord instance) {
		log.debug("finding MonitorPingResponseRecord instance by example");
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
		log.debug("finding MonitorPingResponseRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorPingResponseRecord as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByExcuteCount(Object excuteCount) {
		return findByProperty(EXCUTE_COUNT, excuteCount);
	}

	public List findByResponseStatus(Object responseStatus) {
		return findByProperty(RESPONSE_STATUS, responseStatus);
	}

	public List findAll() {
		log.debug("finding all MonitorPingResponseRecord instances");
		try {
			String queryString = "from MonitorPingResponseRecord";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorPingResponseRecord merge(
			MonitorPingResponseRecord detachedInstance) {
		log.debug("merging MonitorPingResponseRecord instance");
		try {
			MonitorPingResponseRecord result = (MonitorPingResponseRecord) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorPingResponseRecord instance) {
		log.debug("attaching dirty MonitorPingResponseRecord instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorPingResponseRecord instance) {
		log.debug("attaching clean MonitorPingResponseRecord instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorPingResponseRecordDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorPingResponseRecordDAO) ctx
				.getBean("MonitorPingResponseRecordDAO");
	}
}