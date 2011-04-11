package com.combanc.monitor.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorPingTimePoint;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorPingTimePoint entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorPingTimePoint
 * @author MyEclipse Persistence Tools
 */

public class MonitorPingTimePointDAO extends BaseHibernateDAO<MonitorPingTimePoint, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorPingTimePointDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorPingTimePoint transientInstance) {
		log.debug("saving MonitorPingTimePoint instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorPingTimePoint persistentInstance) {
		log.debug("deleting MonitorPingTimePoint instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorPingTimePoint findById(java.lang.Integer id) {
		log.debug("getting MonitorPingTimePoint instance with id: " + id);
		try {
			MonitorPingTimePoint instance = (MonitorPingTimePoint) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorPingTimePoint", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorPingTimePoint instance) {
		log.debug("finding MonitorPingTimePoint instance by example");
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
		log.debug("finding MonitorPingTimePoint instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorPingTimePoint as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all MonitorPingTimePoint instances");
		try {
			String queryString = "from MonitorPingTimePoint";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorPingTimePoint merge(MonitorPingTimePoint detachedInstance) {
		log.debug("merging MonitorPingTimePoint instance");
		try {
			MonitorPingTimePoint result = (MonitorPingTimePoint) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorPingTimePoint instance) {
		log.debug("attaching dirty MonitorPingTimePoint instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorPingTimePoint instance) {
		log.debug("attaching clean MonitorPingTimePoint instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorPingTimePointDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorPingTimePointDAO) ctx.getBean("MonitorPingTimePointDAO");
	}
}