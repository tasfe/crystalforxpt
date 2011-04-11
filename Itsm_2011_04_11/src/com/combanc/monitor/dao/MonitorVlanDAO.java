package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorVlan;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorVlan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorVlan
 * @author MyEclipse Persistence Tools
 */

public class MonitorVlanDAO extends BaseHibernateDAO<MonitorVlan, Integer> {
	private static final Log log = LogFactory.getLog(MonitorVlanDAO.class);
	// property constants
	public static final String SEGMENT = "segment";
	public static final String MASK = "mask";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorVlan transientInstance) {
		log.debug("saving MonitorVlan instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorVlan persistentInstance) {
		log.debug("deleting MonitorVlan instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorVlan findById(java.lang.Integer id) {
		log.debug("getting MonitorVlan instance with id: " + id);
		try {
			MonitorVlan instance = (MonitorVlan) getHibernateTemplate().get(
					"com.combanc.monitor.pojo.MonitorVlan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorVlan instance) {
		log.debug("finding MonitorVlan instance by example");
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
		log.debug("finding MonitorVlan instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MonitorVlan as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySegment(Object segment) {
		return findByProperty(SEGMENT, segment);
	}

	public List findByMask(Object mask) {
		return findByProperty(MASK, mask);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all MonitorVlan instances");
		try {
			String queryString = "from MonitorVlan";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorVlan merge(MonitorVlan detachedInstance) {
		log.debug("merging MonitorVlan instance");
		try {
			MonitorVlan result = (MonitorVlan) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorVlan instance) {
		log.debug("attaching dirty MonitorVlan instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorVlan instance) {
		log.debug("attaching clean MonitorVlan instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorVlanDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorVlanDAO) ctx.getBean("MonitorVlanDAO");
	}
}