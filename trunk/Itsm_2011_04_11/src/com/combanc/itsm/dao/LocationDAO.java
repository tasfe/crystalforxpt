package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Location;

public class LocationDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(LocationDAO.class);

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String PID = "pid";
	public static final String CODE = "code";

	protected void initDao() {

	}

	public void save(Location transientInstance) {
		log.debug("saving Location instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Location persistentInstance) {
		log.debug("deleting Location instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(Location entity) {
		log.debug("updating Location instance");
		try {
			getHibernateTemplate().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public Location findById(Integer id) {
		log.debug("getting Location instance with id: " + id);
		try {
			Location instance = (Location) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Location", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Location instance) {
		log.debug("finding Location instance by example");
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
		log.debug("finding Location instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Location as model where model."
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

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findAll() {
		log.debug("finding all Location instances");
		try {
			String queryString = "from Location";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Location merge(Location detachedInstance) {
		log.debug("merging Location instance");
		try {
			Location result = (Location) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Location instance) {
		log.debug("attaching dirty Location instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Location instance) {
		log.debug("attaching clean Location instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LocationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LocationDAO) ctx.getBean("LocationDAO");
	}
}