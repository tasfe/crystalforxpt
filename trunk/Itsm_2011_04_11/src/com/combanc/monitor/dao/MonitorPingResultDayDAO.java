package com.combanc.monitor.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorPingResultDay;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorPingResultDay entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorPingResultDay
 * @author MyEclipse Persistence Tools
 */

public class MonitorPingResultDayDAO extends BaseHibernateDAO<MonitorPingResultDay, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorPingResultDayDAO.class);
	// property constants
	public static final String SUCCESS_COUNT = "successCount";
	public static final String SEND_COUNT = "sendCount";
	public static final String MIN_REPLY_TIME = "minReplyTime";
	public static final String MAX_REPLY_TIME = "maxReplyTime";
	public static final String SUM_REPLY_TIME = "sumReplyTime";
	public static final String SUM_TTL = "sumTtl";
	public static final String RESPONSE_TIME = "responseTime";
	public static final String NO_RESPONSE_TIME = "noResponseTime";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorPingResultDay transientInstance) {
		log.debug("saving MonitorPingResultDay instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorPingResultDay persistentInstance) {
		log.debug("deleting MonitorPingResultDay instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorPingResultDay findById(java.lang.Integer id) {
		log.debug("getting MonitorPingResultDay instance with id: " + id);
		try {
			MonitorPingResultDay instance = (MonitorPingResultDay) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorPingResultDay", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorPingResultDay instance) {
		log.debug("finding MonitorPingResultDay instance by example");
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
		log.debug("finding MonitorPingResultDay instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorPingResultDay as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySuccessCount(Object successCount) {
		return findByProperty(SUCCESS_COUNT, successCount);
	}

	public List findBySendCount(Object sendCount) {
		return findByProperty(SEND_COUNT, sendCount);
	}

	public List findByMinReplyTime(Object minReplyTime) {
		return findByProperty(MIN_REPLY_TIME, minReplyTime);
	}

	public List findByMaxReplyTime(Object maxReplyTime) {
		return findByProperty(MAX_REPLY_TIME, maxReplyTime);
	}

	public List findBySumReplyTime(Object sumReplyTime) {
		return findByProperty(SUM_REPLY_TIME, sumReplyTime);
	}

	public List findBySumTtl(Object sumTtl) {
		return findByProperty(SUM_TTL, sumTtl);
	}

	public List findByResponseTime(Object responseTime) {
		return findByProperty(RESPONSE_TIME, responseTime);
	}

	public List findByNoResponseTime(Object noResponseTime) {
		return findByProperty(NO_RESPONSE_TIME, noResponseTime);
	}

	public List findAll() {
		log.debug("finding all MonitorPingResultDay instances");
		try {
			String queryString = "from MonitorPingResultDay";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorPingResultDay merge(MonitorPingResultDay detachedInstance) {
		log.debug("merging MonitorPingResultDay instance");
		try {
			MonitorPingResultDay result = (MonitorPingResultDay) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorPingResultDay instance) {
		log.debug("attaching dirty MonitorPingResultDay instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorPingResultDay instance) {
		log.debug("attaching clean MonitorPingResultDay instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorPingResultDayDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorPingResultDayDAO) ctx.getBean("MonitorPingResultDayDAO");
	}
}