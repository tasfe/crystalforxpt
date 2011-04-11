package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.Holiday;
import com.combanc.itsm.pojo.Location;

public class HolidayDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(HolidayDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String HOLIDAY = "holiday";

	protected void initDao() {
		// do nothing
	}

	public void save(Holiday transientInstance) {
		log.debug("saving Holiday instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Holiday persistentInstance) {
		log.debug("deleting Holiday instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(Holiday entity) {
		log.debug("updating Location instance");
		try {
			getHibernateTemplate().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public Holiday findById(java.lang.Integer id) {
		log.debug("getting Holiday instance with id: " + id);
		try {
			Holiday instance = (Holiday) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Holiday", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Holiday instance) {
		log.debug("finding Holiday instance by example");
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
		log.debug("finding Holiday instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Holiday as model where model."
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

	public List findByHoliday(Object holiday) {
		return findByProperty(HOLIDAY, holiday);
	}

	public List findAll() {
		log.debug("finding all Holiday instances");
		try {
			String queryString = "from Holiday";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Holiday merge(Holiday detachedInstance) {
		log.debug("merging Holiday instance");
		try {
			Holiday result = (Holiday) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Holiday instance) {
		log.debug("attaching dirty Holiday instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Holiday instance) {
		log.debug("attaching clean Holiday instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static HolidayDAO getFromApplicationContext(ApplicationContext ctx) {
		return (HolidayDAO) ctx.getBean("HolidayDAO");
	}
}