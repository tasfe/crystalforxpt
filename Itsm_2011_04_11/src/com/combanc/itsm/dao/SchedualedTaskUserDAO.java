package com.combanc.itsm.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.SchedualedTaskUser;

public class SchedualedTaskUserDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SchedualedTaskUserDAO.class);

	// property constants

	public void save(SchedualedTaskUser transientInstance) {
		log.debug("saving SchedualedTaskUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SchedualedTaskUser persistentInstance) {
		log.debug("deleting SchedualedTaskUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SchedualedTaskUser findById(java.lang.Integer id) {
		log.debug("getting SchedualedTaskUser instance with id: " + id);
		try {
			SchedualedTaskUser instance = (SchedualedTaskUser) getSession().get("com.combanc.itsm.pojo.SchedualedTaskUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SchedualedTaskUser instance) {
		log.debug("finding SchedualedTaskUser instance by example");
		try {
			List results = getSession().createCriteria("com.combanc.itsm.pojo.SchedualedTaskUser").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchedualedTaskUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SchedualedTaskUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByTaskIdAndUserId(Object taskId,Object userId) {
		log.debug("finding SchedualedTaskUser instance with TaskId and UserId: "
				+ taskId + ", " + userId);
		try {
			String queryString = "from SchedualedTaskUser as model where model.schedualedTaskDetail="+taskId+"and model.users="+userId;			
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SchedualedTaskUser instances");
		try {
			String queryString = "from SchedualedTaskUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SchedualedTaskUser merge(SchedualedTaskUser detachedInstance) {
		log.debug("merging SchedualedTaskUser instance");
		try {
			SchedualedTaskUser result = (SchedualedTaskUser) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SchedualedTaskUser instance) {
		log.debug("attaching dirty SchedualedTaskUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SchedualedTaskUser instance) {
		log.debug("attaching clean SchedualedTaskUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


		public List findByUserId(int userId)
		{
		String queryString = "from SchedualedTaskUser as model where model.users="+userId;
			
			return getHibernateTemplate().find(queryString);
		}
	
}