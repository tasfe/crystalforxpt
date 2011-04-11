package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorZoneVlan;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorZoneVlan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorZoneVlan
 * @author MyEclipse Persistence Tools
 */

public class MonitorZoneVlanDAO extends
		BaseHibernateDAO<MonitorZoneVlan, Integer> {
	private static final Log log = LogFactory.getLog(MonitorZoneVlanDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorZoneVlan transientInstance) {
		log.debug("saving MonitorZoneVlan instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorZoneVlan persistentInstance) {
		log.debug("deleting MonitorZoneVlan instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorZoneVlan findById(java.lang.Integer id) {
		log.debug("getting MonitorZoneVlan instance with id: " + id);
		try {
			MonitorZoneVlan instance = (MonitorZoneVlan) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorZoneVlan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorZoneVlan instance) {
		log.debug("finding MonitorZoneVlan instance by example");
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
		log.debug("finding MonitorZoneVlan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorZoneVlan as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all MonitorZoneVlan instances");
		try {
			String queryString = "from MonitorZoneVlan";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorZoneVlan merge(MonitorZoneVlan detachedInstance) {
		log.debug("merging MonitorZoneVlan instance");
		try {
			MonitorZoneVlan result = (MonitorZoneVlan) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorZoneVlan instance) {
		log.debug("attaching dirty MonitorZoneVlan instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorZoneVlan instance) {
		log.debug("attaching clean MonitorZoneVlan instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorZoneVlanDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorZoneVlanDAO) ctx.getBean("MonitorZoneVlanDAO");
	}
}