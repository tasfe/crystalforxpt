package com.combanc.itsm.dao;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.AssetsProducer;

public class AssetsProducerDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AssetsProducerDAO.class);
	// property constants
	public static final String TEL = "tel";
	public static final String LEVEL = "level";
	public static final String PERSION = "persion";
	public static final String PERSION_TEL = "persionTel";
	public static final String ADDRESS = "address";
	public static final String DESCRIPTION = "description";
	public static final String TYPE = "type";
	public static final String NAME = "name";

	public List<AssetsProducer> findAll(){
		return getHibernateTemplate().find("from AssetsProducer");
	}
	public void save(AssetsProducer transientInstance) {
		log.debug("saving AssetsProducer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetsProducer persistentInstance) {
		log.debug("deleting AssetsProducer instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssetsProducer findById(java.lang.Integer id) {
		log.debug("getting AssetsProducer instance with id: " + id);
		try {
			AssetsProducer instance = (AssetsProducer) getHibernateTemplate()
					.get("com.combanc.itsm.pojo.AssetsProducer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void update(AssetsProducer AssetsProducer) {
		log.debug("updating AssetsProducer instance ");
		try {
			getHibernateTemplate().update(AssetsProducer);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsProducer instance) {
		log.debug("finding AssetsProducer instance by example");
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

	public List findByProperties(final String queryString, final int offset,
			final int length) {
		log.debug("finding AssetsProducer instance with properties: ");
		try {
			Query query = getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find by properties name failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AssetsProducer instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssetsProducer as model where model."+propertyName+"=3 or model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size();
	}

	public List findByTel(Object tel) {
		return findByProperty(TEL, tel);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findByPersion(Object persion) {
		return findByProperty(PERSION, persion);
	}

	public List findByPersionTel(Object persionTel) {
		return findByProperty(PERSION_TEL, persionTel);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}
	
	public List findByNames(String name) {
		try {
			String queryString = "from AssetsProducer as model where  model.name=?";
			List list=getHibernateTemplate().find(queryString, name);
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<AssetsProducer> findAll(final String queryString,
			final int offset, final int length) {
		log.debug("finding all AssetsProducer instances");
		try {
			// String queryString = "from AssetsProducer";
			Query query = getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetsProducer merge(AssetsProducer detachedInstance) {
		log.debug("merging AssetsProducer instance");
		try {
			AssetsProducer result = (AssetsProducer) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsProducer instance) {
		log.debug("attaching dirty AssetsProducer instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsProducer instance) {
		log.debug("attaching clean AssetsProducer instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	//
	// dwr
	public List findByIntType(int type) {
		return findByType(type);
	}
}