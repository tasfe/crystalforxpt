package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.SeverityTyp;

public class SeverityTypDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SeverityTypDAO.class);
	public static final String SEVERITY_TYPE = "severityType";
	public static final String SEVERITY_VALUE = "severityValue";
	public static final String CATEGORY = "category";
	public static final String TYPE = "type";

	protected void initDao() {
	}

	public void save(SeverityTyp transientInstance) {
		log.debug("saving SeverityTyp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SeverityTyp persistentInstance) {
		log.debug("deleting SeverityTyp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SeverityTyp findById(java.lang.Integer id) {
		log.debug("getting SeverityTyp instance with id: " + id);
		try {
			SeverityTyp instance = (SeverityTyp) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.SeverityTyp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SeverityTyp instance) {
		log.debug("finding SeverityTyp instance by example");
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
		log.debug("finding SeverityTyp instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SeverityTyp as model where model."
					+ propertyName + "= ?"+" order by severityValue";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySeverityType(Object severityType) {
		return findByProperty(SEVERITY_TYPE, severityType);
	}

	public List findBySeverityValue(Object severityValue) {
		return findByProperty(SEVERITY_VALUE, severityValue);
	}

	public List findByCategory(Object category) {
		return findByProperty(CATEGORY, category);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all SeverityTyp instances");
		try {
			String queryString = "from SeverityTyp as s where s.category=3 order by severityValue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	// public List findAll() {
	// log.debug("finding all SeverityTyp instances");
	// try {
	// String queryString = "from SeverityTyp ";
	// return getHibernateTemplate().find(queryString);
	// } catch (RuntimeException re) {
	// log.error("find all failed", re);
	// throw re;
	// }
	// }

	public SeverityTyp merge(SeverityTyp detachedInstance) {
		log.debug("merging SeverityTyp instance");
		try {
			SeverityTyp result = (SeverityTyp) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SeverityTyp instance) {
		log.debug("attaching dirty SeverityTyp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SeverityTyp instance) {
		log.debug("attaching clean SeverityTyp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SeverityTypDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SeverityTypDAO) ctx.getBean("SeverityTypDAO");
	}

	// dwr
	public List findByIntType(int type) {
		return findByCategory(type);
	}
}