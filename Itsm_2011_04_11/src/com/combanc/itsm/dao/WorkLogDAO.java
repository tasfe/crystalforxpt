package com.combanc.itsm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.pojo.WorkLog;

/**
 * A data access object (DAO) providing persistence and search support for
 * WorkLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see db.WorkLog
 * @author MyEclipse Persistence Tools
 */

public class WorkLogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(WorkLogDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String TITLE = "title";
	public static final String TYPE = "type";

	public void save(WorkLog transientInstance) {
		log.debug("saving WorkLog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(WorkLog persistentInstance) {
		log.debug("deleting WorkLog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
		//	getSession().delete(persistentInstance);
			//remove(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WorkLog findById(java.lang.Integer id) {
		log.debug("getting WorkLog instance with id: " + id);
		try {
			WorkLog instance = (WorkLog) getHibernateTemplate().get("com.combanc.itsm.pojo.WorkLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
		
	}

	public List findByExample(WorkLog instance) {
		log.debug("finding WorkLog instance by example");
		try {
			List results = getSession().createCriteria("com.combanc.itsm.pojo.WorkLog").add(
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
		log.debug("finding WorkLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WorkLog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}
	public List<WorkLog> findByGroupId(Object groupId)
	{
		log.debug("finding  WorkLog By groupId");
		
		String sql = "from WorkLog as model where 1=1 and model.department.id="+groupId;
		
		return getHibernateTemplate().find(sql);
			
	}
	public List<WorkLog> findByUserId(Object userId)
	{
		log.debug("finding  WorkLog By groupId");
		
		String sql = "from WorkLog as model where 1=1 and model.users.id="+userId;
		
		return getHibernateTemplate().find(sql);
			
	}
	
	public List<WorkLog> findByLocation(Integer id)
	{
		log.debug("finding  WorkLog By groupId");
		
		String sql = "from WorkLog as model where 1=1 and model.location="+id;
		
		return getHibernateTemplate().find(sql);
			
	}
	
	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all WorkLog instances");
		try {
			String queryString = "from WorkLog";
			Query queryObject = getSession().createQuery(queryString);
			getSession().close();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WorkLog merge(WorkLog detachedInstance) {
		log.debug("merging WorkLog instance");
		try {
			WorkLog result = (WorkLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WorkLog instance) {
		log.debug("attaching dirty WorkLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(WorkLog instance) {
		log.debug("attaching clean WorkLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List findByProperties(final String queryString, final int offset,
			final int length) {
		log.debug("finding WorkLog instance with properties: ");
		try {
			Query query = getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			getSession().close();
			return query.list();
		} catch (RuntimeException re) {
			log.error("find by properties name failed", re);
			throw re;
		}
	}
	public List<WorkLog> getTopByNumWordPress()
	{
		
		String sql="from WorkLog us order by us.wordpress desc limit 10";  
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		List tmpList=session.createQuery(sql).setMaxResults(10).list();
		return tmpList;
		
	}
}