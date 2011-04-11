package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSubnetType;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorSubnetType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorSubnetType
 * @author MyEclipse Persistence Tools
 */

public class MonitorSubnetTypeDAO extends
		BaseHibernateDAO<MonitorSubnet, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorSubnetTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ISUSE = "isuse";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorSubnetType transientInstance) {
		log.debug("saving MonitorSubnetType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorSubnetType persistentInstance) {
		log.debug("deleting MonitorSubnetType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorSubnetType findById(java.lang.Integer id) {
		log.debug("getting MonitorSubnetType instance with id: " + id);
		try {
			MonitorSubnetType instance = (MonitorSubnetType) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorSubnetType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorSubnetType instance) {
		log.debug("finding MonitorSubnetType instance by example");
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
		log.debug("finding MonitorSubnetType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorSubnetType as model where model."
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

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findAll() {
		log.debug("finding all MonitorSubnetType instances");
		try {
			String queryString = "from MonitorSubnetType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorSubnetType merge(MonitorSubnetType detachedInstance) {
		log.debug("merging MonitorSubnetType instance");
		try {
			MonitorSubnetType result = (MonitorSubnetType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorSubnetType instance) {
		log.debug("attaching dirty MonitorSubnetType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorSubnetType instance) {
		log.debug("attaching clean MonitorSubnetType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorSubnetTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorSubnetTypeDAO) ctx.getBean("MonitorSubnetTypeDAO");
	}
}