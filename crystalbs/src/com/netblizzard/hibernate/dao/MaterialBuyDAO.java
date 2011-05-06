package com.netblizzard.hibernate.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.netblizzard.common.core.dao.BaseHibernateDAO;
import com.netblizzard.hibernate.pojo.MaterialBuy;
import com.sun.org.apache.commons.logging.Log;
import com.sun.org.apache.commons.logging.LogFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MaterialBuy entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see crystal.hibernate.dao.MaterialBuy
 * @author MyEclipse Persistence Tools
 */

public class MaterialBuyDAO extends BaseHibernateDAO<MaterialBuy, Integer> {
	private static final Log log = LogFactory.getLog(MaterialBuyDAO.class);
	// property constants
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_PRICE = "materialPrice";
	public static final String COUNT = "count";
	public static final String TOTAL_PRICE = "totalPrice";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";

	protected void initDao() {
		// do nothing
	}

	public void save(MaterialBuy transientInstance) {
		log.debug("saving MaterialBuy instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MaterialBuy persistentInstance) {
		log.debug("deleting MaterialBuy instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MaterialBuy findById(java.lang.Integer id) {
		log.debug("getting MaterialBuy instance with id: " + id);
		try {
			MaterialBuy instance = (MaterialBuy) getHibernateTemplate().get(
					"crystal.hibernate.dao.MaterialBuy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MaterialBuy instance) {
		log.debug("finding MaterialBuy instance by example");
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
		log.debug("finding MaterialBuy instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MaterialBuy as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMaterialName(Object materialName) {
		return findByProperty(MATERIAL_NAME, materialName);
	}

	public List findByMaterialPrice(Object materialPrice) {
		return findByProperty(MATERIAL_PRICE, materialPrice);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByTotalPrice(Object totalPrice) {
		return findByProperty(TOTAL_PRICE, totalPrice);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findAll() {
		log.debug("finding all MaterialBuy instances");
		try {
			String queryString = "from MaterialBuy";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MaterialBuy merge(MaterialBuy detachedInstance) {
		log.debug("merging MaterialBuy instance");
		try {
			MaterialBuy result = (MaterialBuy) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MaterialBuy instance) {
		log.debug("attaching dirty MaterialBuy instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MaterialBuy instance) {
		log.debug("attaching clean MaterialBuy instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MaterialBuyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MaterialBuyDAO) ctx.getBean("MaterialBuyDAO");
	}
}