package crystal.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import crystal.hibernate.po.Material;
import crystal.hibernate.po.MaterialBuy;

/**
 * A data access object (DAO) providing persistence and search support for
 * Material entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see crystal.hibernate.dao.Material
 * @author MyEclipse Persistence Tools
 */

public class MaterialDAO extends BaseHibernateDAO<Material, Integer> {
	private static final Log log = LogFactory.getLog(MaterialDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String TYPE = "type";
	public static final String TYPE_NUMBER = "typeNumber";
	public static final String HEIGHT = "height";
	public static final String WIDTH = "width";
	public static final String COLOR = "color";
	public static final String COUNT_BUY = "countBuy";
	public static final String COUNT = "count";
	public static final String BATCH_NUM = "batchNum";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";

	protected void initDao() {
		// do nothing
	}

	public void save(Material transientInstance) {
		log.debug("saving Material instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Material persistentInstance) {
		log.debug("deleting Material instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Material findById(java.lang.Integer id) {
		log.debug("getting Material instance with id: " + id);
		try {
			Material instance = (Material) getHibernateTemplate().get(
					"crystal.hibernate.dao.Material", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Material instance) {
		log.debug("finding Material instance by example");
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
		log.debug("finding Material instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Material as model where model."
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

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByTypeNumber(Object typeNumber) {
		return findByProperty(TYPE_NUMBER, typeNumber);
	}

	public List findByHeight(Object height) {
		return findByProperty(HEIGHT, height);
	}

	public List findByWidth(Object width) {
		return findByProperty(WIDTH, width);
	}

	public List findByColor(Object color) {
		return findByProperty(COLOR, color);
	}

	public List findByCountBuy(Object countBuy) {
		return findByProperty(COUNT_BUY, countBuy);
	}

	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}

	public List findByBatchNum(Object batchNum) {
		return findByProperty(BATCH_NUM, batchNum);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findAll() {
		log.debug("finding all Material instances");
		try {
			String queryString = "from Material ORDER BY name ASC";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Material merge(Material detachedInstance) {
		log.debug("merging Material instance");
		try {
			Material result = (Material) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Material instance) {
		log.debug("attaching dirty Material instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Material instance) {
		log.debug("attaching clean Material instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MaterialDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MaterialDAO) ctx.getBean("MaterialDAO");
	}
}