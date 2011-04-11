package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Schedule;

/**
 * A data access object (DAO) providing persistence and search support for
 * Schedule entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.Schedule
 * @author MyEclipse Persistence Tools
 */

public class ScheduleDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ScheduleDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String HOUR = "hour";
	public static final String MINUTE = "minute";
	public static final String LEVEL = "level";
	public static final String STATUS = "status";
	public static final String TITLE = "title";
	public static final String DETAIL = "detail";

	public void save(Schedule transientInstance) {
		log.debug("saving Schedule instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(Schedule transientInstance) {
		log.debug("updating Schedule instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("updating successful");
		} catch (RuntimeException re) {
			log.error("updating failed", re);
			throw re;
		}
	}


	public void delete(Schedule persistentInstance) {
		log.debug("deleting Schedule instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Schedule findById(java.lang.Integer id) {
		log.debug("getting Schedule instance with id: " + id);
		try {
			Schedule instance = (Schedule) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Schedule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Schedule instance) {
		log.debug("finding Schedule instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.Schedule").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public List findByDate(String tDate, String uid) {
		log.debug("finding Schedule instance by example");
		try {
			String queryString = "from Schedule as model where model.userByExecutor.id = "+uid+" and model.adate = '"+tDate+"'";
		return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByDate(String tDate) {
		log.debug("finding Schedule instance by example");
		try {
			String queryString = "from Schedule as model where  model.adate = '"+tDate+"'";
		return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}



	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Schedule instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Schedule as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByHour(Object hour) {
		return findByProperty(HOUR, hour);
	}

	public List findByMinute(Object minute) {
		return findByProperty(MINUTE, minute);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	
	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByDetail(Object detail) {
		return findByProperty(DETAIL, detail);
	}

	public List findAll() {
		log.debug("finding all Schedule instances");
		try {
			String queryString = "from Schedule";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Schedule merge(Schedule detachedInstance) {
		log.debug("merging Schedule instance");
		try {
			Schedule result = (Schedule) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Schedule instance) {
		log.debug("attaching dirty Schedule instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Schedule instance) {
		log.debug("attaching clean Schedule instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static ScheduleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ScheduleDAO) ctx.getBean("ScheduleDAO");
	}
}