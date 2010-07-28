package crystal.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import crystal.hibernate.po.MaterialBuy;
import crystal.hibernate.po.ProductSell;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductSell entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see crystal.hibernate.dao.ProductSell
 * @author MyEclipse Persistence Tools
 */

public class ProductSellDAO extends BaseHibernateDAO<ProductSell, Integer> {
	private static final Log log = LogFactory.getLog(ProductSellDAO.class);
	// property constants
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_PRICE = "productPrice";
	public static final String COUNT = "count";
	public static final String TOTOL_PRICE = "totolPrice";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";

	protected void initDao() {
		// do nothing
	}

	public void save(ProductSell transientInstance) {
		log.debug("saving ProductSell instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProductSell persistentInstance) {
		log.debug("deleting ProductSell instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProductSell findById(java.lang.Integer id) {
		log.debug("getting ProductSell instance with id: " + id);
		try {
			ProductSell instance = (ProductSell) getHibernateTemplate().get(
					"crystal.hibernate.dao.ProductSell", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProductSell instance) {
		log.debug("finding ProductSell instance by example");
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
		log.debug("finding ProductSell instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ProductSell as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProductName(Object productName) {
		return findByProperty(PRODUCT_NAME, productName);
	}

	public List findByProductPrice(Object productPrice) {
		return findByProperty(PRODUCT_PRICE, productPrice);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByTotolPrice(Object totolPrice) {
		return findByProperty(TOTOL_PRICE, totolPrice);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findAll() {
		log.debug("finding all ProductSell instances");
		try {
			String queryString = "from ProductSell";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProductSell merge(ProductSell detachedInstance) {
		log.debug("merging ProductSell instance");
		try {
			ProductSell result = (ProductSell) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductSell instance) {
		log.debug("attaching dirty ProductSell instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProductSell instance) {
		log.debug("attaching clean ProductSell instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductSellDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProductSellDAO) ctx.getBean("ProductSellDAO");
	}
}