package com.netblizzard.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.netblizzard.common.core.dao.BaseHibernateDAO;
import com.netblizzard.hibernate.pojo.ProductCategory;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see crystal.hibernate.dao.ProductCategory
 * @author MyEclipse Persistence Tools
 */

public class ProductCategoryDAO extends BaseHibernateDAO<ProductCategory, Integer> {
	private static final Log log = LogFactory.getLog(ProductCategoryDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";
	public static final String IS_USE = "isUse";

	protected void initDao() {
		// do nothing
	}

	public void save(ProductCategory transientInstance) {
		log.debug("saving ProductCategory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProductCategory persistentInstance) {
		log.debug("deleting ProductCategory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProductCategory findById(java.lang.Integer id) {
		log.debug("getting ProductCategory instance with id: " + id);
		try {
			ProductCategory instance = (ProductCategory) getHibernateTemplate()
					.get("crystal.hibernate.po.ProductCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProductCategory instance) {
		log.debug("finding ProductCategory instance by example");
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
		log.debug("finding ProductCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProductCategory as model where model."
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
		log.debug("finding all ProductCategory instances");
		try {
			String queryString = "from ProductCategory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProductCategory merge(ProductCategory detachedInstance) {
		log.debug("merging ProductCategory instance");
		try {
			ProductCategory result = (ProductCategory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductCategory instance) {
		log.debug("attaching dirty ProductCategory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProductCategory instance) {
		log.debug("attaching clean ProductCategory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductCategoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductCategoryDAO) ctx.getBean("ProductCategoryDAO");
	}
}