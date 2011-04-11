package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.IpNetcenter;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpNetcenter entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.IpNetcenter
 * @author MyEclipse Persistence Tools
 */

public class IpNetcenterDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(IpNetcenterDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";

	public void save(IpNetcenter transientInstance) {
		log.debug("saving IpNetcenter instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpNetcenter persistentInstance) {
		log.debug("deleting IpNetcenter instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpNetcenter findById(java.lang.Integer id) {
		log.debug("getting IpNetcenter instance with id: " + id);
		try {
			IpNetcenter instance = (IpNetcenter) getSession().get(
					"com.combanc.itsm.pojo.IpNetcenter", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpNetcenter instance) {
		log.debug("finding IpNetcenter instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.IpNetcenter").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpNetcenter instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from IpNetcenter as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findAll() {
		log.debug("finding all IpNetcenter instances");
		try {
			String queryString = "from IpNetcenter";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpNetcenter merge(IpNetcenter detachedInstance) {
		log.debug("merging IpNetcenter instance");
		try {
			IpNetcenter result = (IpNetcenter) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpNetcenter instance) {
		log.debug("attaching dirty IpNetcenter instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpNetcenter instance) {
		log.debug("attaching clean IpNetcenter instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}