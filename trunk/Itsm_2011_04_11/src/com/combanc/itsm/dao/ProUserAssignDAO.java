package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ProUserAssign;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProUserAssign entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.dao.ProUserAssign
 * @author MyEclipse Persistence Tools
 */

public class ProUserAssignDAO extends BaseHibernateDAO<ProUserAssign, Integer> {

	private static final Log log = LogFactory.getLog(ProUserAssignDAO.class);
	// property constants
	public static final String DEPLOY_ID = "deployId";
	public static final String ACTIVITY_NAME = "activityName";
	public static final String ROLE_ID = "roleId";
	public static final String ROLE_NAME = "roleName";
	public static final String USER_ID = "userId";
	public static final String USERNAME = "username";
	public static final String IS_SIGNED = "isSigned";

	protected void initDao() {
		// do nothing
	}

	public void save(ProUserAssign transientInstance) {
		log.debug("saving ProUserAssign instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ProUserAssign persistentInstance) {
		log.debug("deleting ProUserAssign instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProUserAssign findById(java.lang.Integer id) {
		log.debug("getting ProUserAssign instance with id: " + id);
		try {
			ProUserAssign instance = (ProUserAssign) getHibernateTemplate()
					.get("com.combanc.itsm.dao.ProUserAssign", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public ProUserAssign getByDeployIdActivityName(String deployId,
			String activityName) {
		log
				.debug("getting ProUserAssign instance with deployId and activityName: "
						+ deployId + activityName);
		try {
			String hql = "from ProUserAssign pua where pua.deployId=? and pua.activityName=?";
			return (ProUserAssign) findUnique(hql, new Object[] { deployId,
					activityName });
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ProUserAssign instance) {
		log.debug("finding ProUserAssign instance by example");
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
		log.debug("finding ProUserAssign instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ProUserAssign as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDeployId(Object deployId) {
		return findByProperty(DEPLOY_ID, deployId);
	}

	public List findByActivityName(Object activityName) {
		return findByProperty(ACTIVITY_NAME, activityName);
	}

	public List findByRoleId(Object roleId) {
		return findByProperty(ROLE_ID, roleId);
	}

	public List findByRoleName(Object roleName) {
		return findByProperty(ROLE_NAME, roleName);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByIsSigned(Object isSigned) {
		return findByProperty(IS_SIGNED, isSigned);
	}

	public List findAll() {
		log.debug("finding all ProUserAssign instances");
		try {
			String queryString = "from ProUserAssign";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ProUserAssign merge(ProUserAssign detachedInstance) {
		log.debug("merging ProUserAssign instance");
		try {
			ProUserAssign result = (ProUserAssign) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ProUserAssign instance) {
		log.debug("attaching dirty ProUserAssign instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProUserAssign instance) {
		log.debug("attaching clean ProUserAssign instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProUserAssignDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ProUserAssignDAO) ctx.getBean("ProUserAssignDAO");
	}
}