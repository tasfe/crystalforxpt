package com.combanc.monitor.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorV3Params;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorV3Params entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorV3Params
 * @author MyEclipse Persistence Tools
 */

public class MonitorV3ParamsDAO extends BaseHibernateDAO<MonitorV3Params, Integer> {
	private static final Log log = LogFactory.getLog(MonitorV3ParamsDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NOTE = "note";
	public static final String CONTEXT_NAME = "contextName";
	public static final String CONTEXT_ID = "contextId";
	public static final String USER_NAME = "userName";
	public static final String AUTH_PASSWORD = "authPassword";
	public static final String PRIV_PASSWORD = "privPassword";
	public static final String ENGINE_ID = "engineId";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorV3Params transientInstance) {
		log.debug("saving MonitorV3Params instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorV3Params persistentInstance) {
		log.debug("deleting MonitorV3Params instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorV3Params findById(java.lang.Integer id) {
		log.debug("getting MonitorV3Params instance with id: " + id);
		try {
			MonitorV3Params instance = (MonitorV3Params) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorV3Params", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorV3Params instance) {
		log.debug("finding MonitorV3Params instance by example");
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
		log.debug("finding MonitorV3Params instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorV3Params as model where model."
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

	public List findByContextName(Object contextName) {
		return findByProperty(CONTEXT_NAME, contextName);
	}

	public List findByContextId(Object contextId) {
		return findByProperty(CONTEXT_ID, contextId);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByAuthPassword(Object authPassword) {
		return findByProperty(AUTH_PASSWORD, authPassword);
	}

	public List findByPrivPassword(Object privPassword) {
		return findByProperty(PRIV_PASSWORD, privPassword);
	}

	public List findByEngineId(Object engineId) {
		return findByProperty(ENGINE_ID, engineId);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findByNote3(Object note3) {
		return findByProperty(NOTE3, note3);
	}

	public List findByNote4(Object note4) {
		return findByProperty(NOTE4, note4);
	}

	public List findAll() {
		log.debug("finding all MonitorV3Params instances");
		try {
			String queryString = "from MonitorV3Params";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorV3Params merge(MonitorV3Params detachedInstance) {
		log.debug("merging MonitorV3Params instance");
		try {
			MonitorV3Params result = (MonitorV3Params) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorV3Params instance) {
		log.debug("attaching dirty MonitorV3Params instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorV3Params instance) {
		log.debug("attaching clean MonitorV3Params instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorV3ParamsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorV3ParamsDAO) ctx.getBean("MonitorV3ParamsDAO");
	}
}