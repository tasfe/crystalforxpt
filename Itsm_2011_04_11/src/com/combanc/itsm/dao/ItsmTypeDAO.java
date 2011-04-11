package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ItsmType;

public class ItsmTypeDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ItsmTypeDAO.class);

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PID = "pid";
	public static final String CODE = "code";

	protected void initDao() {

	}

	public void save(ItsmType transientInstance) {
		log.debug("saving ItsmType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ItsmType persistentInstance) {
		log.debug("deleting ItsmType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(ItsmType entity) {
		log.debug("updating ItsmType instance");
		try {
			getHibernateTemplate().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public ItsmType findById(Integer id) {
		log.debug("getting ItsmType instance with id: " + id);
		try {
			ItsmType instance = (ItsmType) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.ItsmType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ItsmType instance) {
		log.debug("finding ItsmType instance by example");
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
		log.debug("finding ItsmType instance with property: " + propertyName
				+ ", value: " + value);
		try {
			if(value==null && propertyName.toLowerCase().equals("pid")){
				return getHibernateTemplate().find(" from ItsmType where pid is null or pid=0");
			}
			String queryString = "from ItsmType as model where model."
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

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findAll() {
		log.debug("finding all ItsmType instances");
		try {
			String queryString = "from ItsmType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ItsmType merge(ItsmType detachedInstance) {
		log.debug("merging BuildLocation instance");
		try {
			ItsmType result = (ItsmType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ItsmType instance) {
		log.debug("attaching dirty ItsmType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItsmType instance) {
		log.debug("attaching clean ItsmType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ItsmTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ItsmTypeDAO) ctx.getBean("itsmTypeDAO");
	}
}