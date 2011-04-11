package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorReportTask;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorReportTask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorReportTask
 * @author MyEclipse Persistence Tools
 */

public class MonitorReportTaskDAO extends
		BaseHibernateDAO<MonitorReportTask, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorReportTaskDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String INTRO = "intro";
	public static final String URL = "url";
	public static final String CONFIG_XML = "configXml";
	public static final String PROP1 = "prop1";
	public static final String PROP2 = "prop2";
	public static final String PROP3 = "prop3";
	public static final String PROP4 = "prop4";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorReportTask transientInstance) {
		log.debug("saving MonitorReportTask instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorReportTask persistentInstance) {
		log.debug("deleting MonitorReportTask instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorReportTask findById(java.lang.Integer id) {
		log.debug("getting MonitorReportTask instance with id: " + id);
		try {
			MonitorReportTask instance = (MonitorReportTask) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorReportTask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorReportTask instance) {
		log.debug("finding MonitorReportTask instance by example");
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
		log.debug("finding MonitorReportTask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorReportTask as model where model."
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

	public List findByIntro(Object intro) {
		return findByProperty(INTRO, intro);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByConfigXml(Object configXml) {
		return findByProperty(CONFIG_XML, configXml);
	}

	public List findByProp1(Object prop1) {
		return findByProperty(PROP1, prop1);
	}

	public List findByProp2(Object prop2) {
		return findByProperty(PROP2, prop2);
	}

	public List findByProp3(Object prop3) {
		return findByProperty(PROP3, prop3);
	}

	public List findByProp4(Object prop4) {
		return findByProperty(PROP4, prop4);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all MonitorReportTask instances");
		try {
			String queryString = "from MonitorReportTask";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorReportTask merge(MonitorReportTask detachedInstance) {
		log.debug("merging MonitorReportTask instance");
		try {
			MonitorReportTask result = (MonitorReportTask) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorReportTask instance) {
		log.debug("attaching dirty MonitorReportTask instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorReportTask instance) {
		log.debug("attaching clean MonitorReportTask instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorReportTaskDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorReportTaskDAO) ctx.getBean("MonitorReportTaskDAO");
	}
}