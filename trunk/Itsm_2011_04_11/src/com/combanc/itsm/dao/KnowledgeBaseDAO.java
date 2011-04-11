package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.KnowledgeBase;

/**
 * A data access object (DAO) providing persistence and search support for
 * KnowledgeBase entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.KnowledgeBase
 * @author MyEclipse Persistence Tools
 */

public class KnowledgeBaseDAO extends BaseHibernateDAO<KnowledgeBase,Integer> {
	private static final Log log = LogFactory.getLog(KnowledgeBaseDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String CATEGORY_ID = "categoryId";
	public static final String SYMPTOM = "symptom";
	public static final String FILE_NAME = "fileName";
	public static final String SOLUTION = "solution";
	public static final String ENGINEER_ID = "engineerId";
	public static final String MODE = "mode";
	public static final String INDEXCODE ="indexcode";

	public void save(KnowledgeBase transientInstance) {
		log.debug("saving KnowledgeBase instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(KnowledgeBase persistentInstance) {
		log.debug("deleting KnowledgeBase instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*public void update(KnowledgeBase persistentInstance) {
		log.debug("updating KnowledgeBase instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}*/

	public KnowledgeBase findById(java.lang.Integer id) {
		log.debug("getting KnowledgeBase instance with id: " + id);
		try {
			KnowledgeBase instance = (KnowledgeBase) getHibernateTemplate().get("com.combanc.itsm.pojo.KnowledgeBase", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(KnowledgeBase instance) {
		log.debug("finding KnowledgeBase instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.KnowledgeBase").add(
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
		log.debug("finding KnowledgeBase instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from KnowledgeBase as model where model."
					+ propertyName + "= ?";
			Query queryObject = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByCategoryId(Object categoryId) {
		return findByProperty(CATEGORY_ID, categoryId);
	}

	public List findBySymptom(Object symptom) {
		return findByProperty(SYMPTOM, symptom);
	}

	public List findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List findBySolution(Object solution) {
		return findByProperty(SOLUTION, solution);
	}

	public List findByEngineerId(Object engineerId) {
		 String queryString = "from KnowledgeBase as model where model.engineerId="+engineerId;
			
			return getHibernateTemplate().find(queryString);
	
	}

	public List findByMode(Object mode) {
		return findByProperty(MODE, mode);
	}
	
	public List findByIndexcode(Object indexcode) {
		return findByProperty(INDEXCODE, indexcode);
	}

	public List findAll() {
		log.debug("finding all KnowledgeBase instances");
		try {
			String queryString = "from KnowledgeBase";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public KnowledgeBase merge(KnowledgeBase detachedInstance) {
		log.debug("merging KnowledgeBase instance");
		try {
			KnowledgeBase result = (KnowledgeBase) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(KnowledgeBase instance) {
		log.debug("attaching dirty KnowledgeBase instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(KnowledgeBase instance) {
		log.debug("attaching clean KnowledgeBase instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static KnowledgeBaseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (KnowledgeBaseDAO) ctx.getBean("KnowledgeBaseDAO");
	}
	
	public List findByProperties(final String queryString, final int offset,
			final int length) {
		log.debug("finding KnowledgeBase instance with properties: ");
		try {
			Query query =getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find by properties name failed", re);
			throw re;
		}
	}
	
	
}