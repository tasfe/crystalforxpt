package com.combanc.itsm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.TaskDep;
import com.combanc.itsm.pojo.TaskDepId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskDep entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see db.TaskDep
 * @author MyEclipse Persistence Tools
 */

public class TaskDepDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TaskDepDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(TaskDep transientInstance) {
		log.debug("saving TaskDep instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskDep persistentInstance) {
		log.debug("deleting TaskDep instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskDep findById(TaskDepId id) {
		log.debug("getting TaskDep instance with id: " + id);
		try {
			TaskDep instance = (TaskDep) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.TaskDep", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskDep instance) {
		log.debug("finding TaskDep instance by example");
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
		log.debug("finding TaskDep instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TaskDep as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TaskDep instances");
		try {
			String queryString = "from TaskDep";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskDep merge(TaskDep detachedInstance) {
		log.debug("merging TaskDep instance");
		try {
			TaskDep result = (TaskDep) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskDep instance) {
		log.debug("attaching dirty TaskDep instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskDep instance) {
		log.debug("attaching clean TaskDep instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TaskDepDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TaskDepDAO) ctx.getBean("TaskDepDAO");
	}
}