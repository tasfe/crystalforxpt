package com.combanc.itsm.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.ServiceTran;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServiceTran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.ServiceTran
 * @author MyEclipse Persistence Tools
 */

public class ServiceTranDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ServiceTranDAO.class);
	// property constants
	public static final String REQU_NO = "requNo";
	public static final String AUTO_OR_END_TIME = "autoOrEndTime";
	public static final String SERVICE_TILTE = "serviceTilte";
	public static final String SERVICE_FROM = "serviceFrom";
	public static final String SERVICE_TO = "serviceTo";
	public static final String NOTE = "note";
	public static final String TYPE = "type";
	public static final String MINUTES = "minutes";
	public static final String OTHER_NOTE = "otherNote";
	public static final String USERS = "users";

	protected void initDao() {
		// do nothing
	}

	public void save(ServiceTran transientInstance) {
		log.debug("saving ServiceTran instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServiceTran persistentInstance) {
		log.debug("deleting ServiceTran instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(ServiceTran persistentInstance) {
		log.debug("updating ServiceTran instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public ServiceTran findById(java.lang.Integer id) {
		log.debug("getting ServiceTran instance with id: " + id);
		try {
			ServiceTran instance = (ServiceTran) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.ServiceTran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServiceTran instance) {
		log.debug("finding ServiceTran instance by example");
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
		log.debug("finding ServiceTran instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ServiceTran as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRequNo(Object requNo) {
		return findByProperty(REQU_NO, requNo);
	}

	public List findByAutoOrEndTime(Object autoOrEndTime) {
		return findByProperty(AUTO_OR_END_TIME, autoOrEndTime);
	}

	public List findByServiceTilte(Object serviceTilte) {
		return findByProperty(SERVICE_TILTE, serviceTilte);
	}

	public List findByServiceFrom(Object serviceFrom) {
		return findByProperty(SERVICE_FROM, serviceFrom);
	}

	public List findByServiceTo(Object serviceTo) {
		return findByProperty(SERVICE_TO, serviceTo);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByMinutes(Object minutes) {
		return findByProperty(MINUTES, minutes);
	}

	public List findByOtherNote(Object otherNote) {
		return findByProperty(OTHER_NOTE, otherNote);
	}

	public List findByUsers(Object users) {
		return findByProperty(USERS, users);
	}

	public List findAll() {
		log.debug("finding all ServiceTran instances");
		try {
			String queryString = "from ServiceTran";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServiceTran merge(ServiceTran detachedInstance) {
		log.debug("merging ServiceTran instance");
		try {
			ServiceTran result = (ServiceTran) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServiceTran instance) {
		log.debug("attaching dirty ServiceTran instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServiceTran instance) {
		log.debug("attaching clean ServiceTran instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServiceTranDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServiceTranDAO) ctx.getBean("ServiceTranDAO");
	}
}