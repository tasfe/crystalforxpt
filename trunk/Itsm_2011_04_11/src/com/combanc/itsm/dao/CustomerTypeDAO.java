package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.CustomerType;

/**
 * A data access object (DAO) providing persistence and search support for
 * Accessory entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see itsm.db.test.Accessory
 * @author MyEclipse Persistence Tools
 */

public class CustomerTypeDAO extends BaseHibernateDAO<CustomerType,Integer> {
	private static final Log log = LogFactory.getLog(CustomerTypeDAO.class);
	// property constants

	public void save(Accessory transientInstance) {
		log.debug("saving Accessory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Accessory persistentInstance) {
		log.debug("deleting Accessory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Accessory findById(java.lang.Integer id) {
		log.debug("getting Accessory instance with id: " + id);
		try {
			Accessory instance = (Accessory) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.CustomerType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Accessory instance) {
		log.debug("finding Accessory instance by example");
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
		log.debug("finding Accessory instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from CustomerType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findAll() {
		log.debug("finding all Accessory instances");
		try {
			String queryString = "from CustomerType";

			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Accessory merge(Accessory detachedInstance) {
		log.debug("merging Accessory instance");
		try {
			Accessory result = (Accessory) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Accessory instance) {
		log.debug("attaching dirty Accessory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Accessory instance) {
		log.debug("attaching clean Accessory instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}