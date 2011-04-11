package com.netblizzard.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import crystal.hibernate.po.MaterialBuy;
import crystal.hibernate.po.MaterialCategory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see crystal.hibernate.po.MaterialCategory
 * @author MyEclipse Persistence Tools
 */

public class MaterialCategoryDAO extends BaseHibernateDAO<MaterialCategory, Integer> {
	private static final Log log = LogFactory.getLog(MaterialCategoryDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";
	public static final String IS_USE = "isUse";

	protected void initDao() {
		// do nothing
	}

	public void save(MaterialCategory transientInstance) {
		log.debug("saving MaterialCategory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialCategory persistentInstance) {
		log.debug("deleting MaterialCategory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialCategory findById(java.lang.Integer id) {
		log.debug("getting MaterialCategory instance with id: " + id);
		try {
			MaterialCategory instance = (MaterialCategory) getHibernateTemplate()
					.get("crystal.hibernate.po.MaterialCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MaterialCategory instance) {
		log.debug("finding MaterialCategory instance by example");
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
		log.debug("finding MaterialCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MaterialCategory as model where model."
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

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByIsUse(Object isUse) {
		return findByProperty(IS_USE, isUse);
	}

	public List findAll() {
		log.debug("finding all MaterialCategory instances");
		try {
			String queryString = "from MaterialCategory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialCategory merge(MaterialCategory detachedInstance) {
		log.debug("merging MaterialCategory instance");
		try {
			MaterialCategory result = (MaterialCategory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialCategory instance) {
		log.debug("attaching dirty MaterialCategory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialCategory instance) {
		log.debug("attaching clean MaterialCategory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MaterialCategoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MaterialCategoryDAO) ctx.getBean("MaterialCategoryDAO");
	}
}