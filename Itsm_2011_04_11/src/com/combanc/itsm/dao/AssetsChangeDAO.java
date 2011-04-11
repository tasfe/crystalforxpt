package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsChange;

/**
 * A data access object (DAO) providing persistence and search support for
 * HistoryDeviceState entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.AssetsHistoryState
 * @author MyEclipse Persistence Tools
 */

public class AssetsChangeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AssetsChangeDAO.class);
	// property constants
	public static final String CODE = "assetsBase.code";
	public static final String STATE = "state";
	public static final String PERSION_ID = "persionId";

	protected void initDao() {
		// do nothing
	}
	
	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size();
	}
	
	public List<AssetsChange> findAll(final String queryString,final int offset, final int length) {
		try {
			Query query = getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void save(AssetsChange transientInstance) {
		log.debug("saving AssetsChange instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(AssetsChange change){
		log.debug("update AssetsChange instance");
		try {
			getHibernateTemplate().saveOrUpdate(change);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(AssetsChange persistentInstance) {
		log.debug("deleting AssetsChange instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssetsChange findById(int id) {
		log.debug("getting AssetsChange instance with id: " + id);
		try {
			AssetsChange instance = (AssetsChange) getHibernateTemplate().get("com.combanc.itsm.pojo.AssetsChange",id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsChange instance) {
		log.debug("finding AssetsChange instance by example");
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
		log.debug("finding AssetsChange instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssetsChange as model where model."
					+ propertyName + "= ? order by changeTime desc ";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByPersionId(Object persionId) {
		return findByProperty(PERSION_ID, persionId);
	}

	public List findAll() {
		log.debug("finding all AssetsChange instances");
		try {
			String queryString = "from AssetsChange";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetsChange merge(AssetsChange detachedInstance) {
		log.debug("merging AssetsChange instance");
		try {
			AssetsChange result = (AssetsChange) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsChange instance) {
		log.debug("attaching dirty AssetsChange instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsChange instance) {
		log.debug("attaching clean AssetsChange instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsChangeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssetsChangeDAO) ctx.getBean("AssetsChange");
	}
}