package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorServerProcess;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorServerProcess entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorServerProcess
 * @author MyEclipse Persistence Tools
 */

public class MonitorServerProcessDAO extends
		BaseHibernateDAO<MonitorServerProcess, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorServerProcessDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String START = "start";
	public static final String NOTE = "note";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorServerProcess transientInstance) {
		log.debug("saving MonitorServerProcess instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorServerProcess persistentInstance) {
		log.debug("deleting MonitorServerProcess instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorServerProcess findById(java.lang.Integer id) {
		log.debug("getting MonitorServerProcess instance with id: " + id);
		try {
			MonitorServerProcess instance = (MonitorServerProcess) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorServerProcess", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorServerProcess instance) {
		log.debug("finding MonitorServerProcess instance by example");
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
		log.debug("finding MonitorServerProcess instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorServerProcess as model where model."
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

	public List findByStart(Object start) {
		return findByProperty(START, start);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findAll() {
		log.debug("finding all MonitorServerProcess instances");
		try {
			String queryString = "from MonitorServerProcess";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorServerProcess merge(MonitorServerProcess detachedInstance) {
		log.debug("merging MonitorServerProcess instance");
		try {
			MonitorServerProcess result = (MonitorServerProcess) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorServerProcess instance) {
		log.debug("attaching dirty MonitorServerProcess instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorServerProcess instance) {
		log.debug("attaching clean MonitorServerProcess instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorServerProcessDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorServerProcessDAO) ctx.getBean("MonitorServerProcessDAO");
	}
	
	public List findByProcStart(Object procStart) {
		log.debug("finding MonitorServerProcess instance with property: "
				+ START + ", value: " + procStart);
		try {
			String queryString = "from MonitorServerProcess as model where model."
					+ START + "= ? order by model.monitorDevice.id desc";
			return getHibernateTemplate().find(queryString, procStart);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}