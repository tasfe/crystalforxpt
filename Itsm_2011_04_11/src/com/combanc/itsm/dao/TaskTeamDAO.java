package com.combanc.itsm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.TaskTeam;
import com.combanc.itsm.pojo.TaskTeamId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaskTeam entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see db.TaskTeam
 * @author MyEclipse Persistence Tools
 */

public class TaskTeamDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(TaskTeamDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(TaskTeam transientInstance) {
		log.debug("saving TaskTeam instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaskTeam persistentInstance) {
		log.debug("deleting TaskTeam instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaskTeam findById(TaskTeamId id) {
		log.debug("getting TaskTeam instance with id: " + id);
		try {
			TaskTeam instance = (TaskTeam) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.TaskTeam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaskTeam instance) {
		log.debug("finding TaskTeam instance by example");
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
		log.debug("finding TaskTeam instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TaskTeam as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all TaskTeam instances");
		try {
			String queryString = "from TaskTeam";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaskTeam merge(TaskTeam detachedInstance) {
		log.debug("merging TaskTeam instance");
		try {
			TaskTeam result = (TaskTeam) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaskTeam instance) {
		log.debug("attaching dirty TaskTeam instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaskTeam instance) {
		log.debug("attaching clean TaskTeam instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TaskTeamDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TaskTeamDAO) ctx.getBean("TaskTeamDAO");
	}
}