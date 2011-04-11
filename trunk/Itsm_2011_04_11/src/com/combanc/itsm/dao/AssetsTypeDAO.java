package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsType;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssetsType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.AssetsType
 * @author MyEclipse Persistence Tools
 */

public class AssetsTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AssetsTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String FLAG = "flag";
	public static final String DESCRIPTION = "description";
	public static final String PID = "pid";

	protected void initDao() {
		// do nothing
	}

	public void save(AssetsType transientInstance) {
		log.debug("saving AssetsType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetsType persistentInstance) {
		log.debug("deleting AssetsType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(AssetsType persistentInstance) {
		log.debug("updating AssetsType instance");
		try {
			getHibernateTemplate().update(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public AssetsType findById(Integer id) {
		log.debug("getting AssetsType instance with id: " + id);
		try {
			AssetsType instance = (AssetsType) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.AssetsType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsType instance) {
		log.debug("finding AssetsType instance by example");
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
		log.debug("finding AssetsType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsType as model where model."
					+ propertyName + "= ?"; 
			List list=getHibernateTemplate().find(queryString, value);
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}
	
	public List findByNames(String name) {
		return findByProperty(NAME, name);
	}

	public List findByFlag(Object flag) {
		return findByProperty(FLAG, flag);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List<AssetsType> findAllByAssetsTypePid(int pid) {
		String queryString;
		try {
			queryString = "from AssetsType where flag like '%:" + pid + ":%' ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findAll() {
		log.debug("finding all AssetsType instances");
		try {
			String queryString = "from AssetsType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	public List findAlls(String id) {
		log.debug("finding all AssetsType instances");
		try {
			String queryString = "from AssetsType model where model.id!="+id;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	
	
	

	public AssetsType merge(AssetsType detachedInstance) {
		log.debug("merging AssetsType instance");
		try {
			AssetsType result = (AssetsType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsType instance) {
		log.debug("attaching dirty AssetsType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsType instance) {
		log.debug("attaching clean AssetsType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AssetsTypeDAO) ctx.getBean("AssetsTypeDAO");
	}
}