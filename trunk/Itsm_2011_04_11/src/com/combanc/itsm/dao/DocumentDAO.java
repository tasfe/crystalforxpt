package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Document;

/**
 * A data access object (DAO) providing persistence and search support for
 * Document entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.Document
 * @author MyEclipse Persistence Tools
 */

public class DocumentDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DocumentDAO.class);
	// property constants
	public static final String SUMMARY = "summary";
	public static final String CONTEXT = "context";
	public static final String TITLE = "title";
	public static final String KEYWORD = "keyword";
	public static final String VERSION = "version";
	public static final String USER_NAME = "userName";
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String NUM_STRING="num";

	protected void initDao() {
		// do nothing
	}

	public void save(Document transientInstance) {
		log.debug("saving Document instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Document persistentInstance) {
		log.debug("deleting Document instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Document findById(java.lang.Integer id) {
		log.debug("getting Document instance with id: " + id);
		try {
			Document instance = (Document) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Document", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Document instance) {
		log.debug("finding Document instance by example");
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
		log.debug("finding Document instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Document as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySummary(Object summary) {
		return findByProperty(SUMMARY, summary);
	}
	public List findByNum(Object num)
	{
		return findByProperty(NUM_STRING, num);
	}

	public List findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByKeyword(Object keyword) {
		return findByProperty(KEYWORD, keyword);
	}

	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByDepartmentName(Object departmentName) {
		return findByProperty(DEPARTMENT_NAME, departmentName);
	}
	public List findByDepartmentId(int departmentId)
	{
	String queryString = "from Document as model where model.department="+departmentId;
		
		return getHibernateTemplate().find(queryString);
	}
	public List findByCat(int catid) {
		
		String queryString = "from Document as model where model.cat="+catid;
		
		return getHibernateTemplate().find(queryString);
	}
	public List findByUserId(int userId)
	{
	String queryString = "from Document as model where model.users="+userId;
		
		return getHibernateTemplate().find(queryString);
	}
	public List findAll() {
		log.debug("finding all Document instances");
		try {
			String queryString = "from Document";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Document merge(Document detachedInstance) {
		log.debug("merging Document instance");
		try {
			Document result = (Document) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Document instance) {
		log.debug("attaching dirty Document instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Document instance) {
		log.debug("attaching clean Document instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DocumentDAO) ctx.getBean("DocumentDAO");
	}
}