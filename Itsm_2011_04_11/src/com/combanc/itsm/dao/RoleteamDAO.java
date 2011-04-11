package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Location;
import com.combanc.itsm.pojo.Roleteam;

public class RoleteamDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(RoleteamDAO.class);

	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String DESCRIPTION = "description";
	public static final String ORDERNO = "orderNo";
	public static final String USEFOR = "useFor";

	protected void initDao() {

	}

	public void save(Roleteam roleteam) {
		log.debug("saving Roleteam instance");
		try {
			getHibernateTemplate().save(roleteam);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roleteam roleteam) {
		log.debug("deleting Roleteam instance");
		try {
			getHibernateTemplate().delete(roleteam);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roleteam findById(Integer id) {
		log.debug("getting Roleteam instance with id: " + id);
		try {
			Roleteam instance = (Roleteam) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Roleteam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByLocationID(Integer id) {
		log.debug("finding Roleteam instance with locationID: " + id);
		try {
			String queryString = "from Roleteam as model where model.location="
					+ id;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by locationID failed", re);
			throw re;
		}
	}

	public List findEByLocationID(Integer id) {
		log.debug("finding Roleteam instance with locationID: " + id);
		try {
			String queryString = "from Roleteam as model where model.type=1 and model.location="
					+ id;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by locationID failed", re);
			throw re;
		}
	}

	public List findByExample(Roleteam instance) {
		log.debug("finding Roleteam instance by example");
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
		log.debug("finding Roleteam instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roleteam as model where model."
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

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByTypeString(String type) {
		return findByType(type);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByOrderNo(Object orderNo) {
		return findByProperty(ORDERNO, orderNo);
	}

	public List findByUseFor(Object useFor) {
		return findByProperty(USEFOR, useFor);
	}
	public List findAllByType()
	{
		return findByType("1");
	}
	public List findByUseForString(String useFor) {

		String sql = "from Roleteam as model where 1=1";

		if (useFor != null) {
			sql = sql + " and model.useFor=" + useFor + " and model.type=1";

		}

		List rList = getHibernateTemplate().find(sql);

		return rList;

	}

	public List findAll() {
		log.debug("finding all Roleteam instances");
		try {
			String queryString = "from Roleteam";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roleteam merge(Roleteam detachedInstance) {
		log.debug("merging Roleteam instance");
		try {
			Roleteam result = (Roleteam) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roleteam instance) {
		log.debug("attaching dirty Roleteam instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roleteam instance) {
		log.debug("attaching clean Roleteam instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RoleteamDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RoleteamDAO) ctx.getBean("RoleteamDAO");
	}

	public Roleteam findRoleteamWithUsersById(Integer roleteamId) {
		return (Roleteam) getHibernateTemplate().find(
				"from Roleteam r join fetch r.users where id = " + roleteamId)
				.get(0);
	}

}