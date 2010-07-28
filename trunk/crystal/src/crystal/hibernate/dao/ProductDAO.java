package crystal.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import crystal.hibernate.po.MaterialBuy;
import crystal.hibernate.po.Product;

/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see crystal.hibernate.dao.Product
 * @author MyEclipse Persistence Tools
 */

public class ProductDAO extends BaseHibernateDAO<Product, Integer> {
	private static final Log log = LogFactory.getLog(ProductDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String COUNT_MAKE = "countMake";
	public static final String COUNT = "count";
	public static final String COLOR = "color";
	public static final String HEIGHT = "height";
	public static final String WIDTH = "width";
	public static final String MCOUNT1 = "mcount1";
	public static final String MCOUNT2 = "mcount2";
	public static final String MCOUNT3 = "mcount3";
	public static final String MCOUNT4 = "mcount4";
	public static final String MCOUNT5 = "mcount5";
	public static final String MCOUNT6 = "mcount6";
	public static final String MCOUNT7 = "mcount7";
	public static final String MCOUNT8 = "mcount8";
	public static final String MCOUNT9 = "mcount9";
	public static final String MCOUNT10 = "mcount10";
	public static final String MCOUNT11 = "mcount11";
	public static final String MCOUNT12 = "mcount12";
	public static final String MCOUNT13 = "mcount13";
	public static final String MCOUNT14 = "mcount14";
	public static final String MCOUNT15 = "mcount15";
	public static final String MCOUNT16 = "mcount16";
	public static final String MCOUNT17 = "mcount17";
	public static final String MCOUNT18 = "mcount18";
	public static final String MCOUNT19 = "mcount19";
	public static final String MCOUNT20 = "mcount20";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";

	protected void initDao() {
		// do nothing
	}

	public void save(Product transientInstance) {
		log.debug("saving Product instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Product findById(java.lang.Integer id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getHibernateTemplate().get(
					"crystal.hibernate.dao.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Product instance) {
		log.debug("finding Product instance by example");
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
		log.debug("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Product as model where model."
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

	public List findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	public List findByCountMake(Object countMake) {
		return findByProperty(COUNT_MAKE, countMake);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByColor(Object color) {
		return findByProperty(COLOR, color);
	}

	public List findByHeight(Object height) {
		return findByProperty(HEIGHT, height);
	}

	public List findByWidth(Object width) {
		return findByProperty(WIDTH, width);
	}

	public List findByMcount1(Object mcount1) {
		return findByProperty(MCOUNT1, mcount1);
	}

	public List findByMcount2(Object mcount2) {
		return findByProperty(MCOUNT2, mcount2);
	}

	public List findByMcount3(Object mcount3) {
		return findByProperty(MCOUNT3, mcount3);
	}

	public List findByMcount4(Object mcount4) {
		return findByProperty(MCOUNT4, mcount4);
	}

	public List findByMcount5(Object mcount5) {
		return findByProperty(MCOUNT5, mcount5);
	}

	public List findByMcount6(Object mcount6) {
		return findByProperty(MCOUNT6, mcount6);
	}

	public List findByMcount7(Object mcount7) {
		return findByProperty(MCOUNT7, mcount7);
	}

	public List findByMcount8(Object mcount8) {
		return findByProperty(MCOUNT8, mcount8);
	}

	public List findByMcount9(Object mcount9) {
		return findByProperty(MCOUNT9, mcount9);
	}

	public List findByMcount10(Object mcount10) {
		return findByProperty(MCOUNT10, mcount10);
	}

	public List findByMcount11(Object mcount11) {
		return findByProperty(MCOUNT11, mcount11);
	}

	public List findByMcount12(Object mcount12) {
		return findByProperty(MCOUNT12, mcount12);
	}

	public List findByMcount13(Object mcount13) {
		return findByProperty(MCOUNT13, mcount13);
	}

	public List findByMcount14(Object mcount14) {
		return findByProperty(MCOUNT14, mcount14);
	}

	public List findByMcount15(Object mcount15) {
		return findByProperty(MCOUNT15, mcount15);
	}

	public List findByMcount16(Object mcount16) {
		return findByProperty(MCOUNT16, mcount16);
	}

	public List findByMcount17(Object mcount17) {
		return findByProperty(MCOUNT17, mcount17);
	}

	public List findByMcount18(Object mcount18) {
		return findByProperty(MCOUNT18, mcount18);
	}

	public List findByMcount19(Object mcount19) {
		return findByProperty(MCOUNT19, mcount19);
	}

	public List findByMcount20(Object mcount20) {
		return findByProperty(MCOUNT20, mcount20);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findAll() {
		log.debug("finding all Product instances");
		try {
			String queryString = "from Product ORDER BY name ASC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Product instance) {
		log.debug("attaching dirty Product instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDAO) ctx.getBean("ProductDAO");
	}
}