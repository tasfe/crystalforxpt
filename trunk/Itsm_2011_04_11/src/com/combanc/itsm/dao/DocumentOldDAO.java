package com.combanc.itsm.dao;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.DocumentOld;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentOld entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.DocumentOld
 * @author MyEclipse Persistence Tools
 */

public class DocumentOldDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DocumentOldDAO.class);
	// property constants
	public static final String SUMMARY = "summary";
	public static final String CONTEXT = "context";
	public static final String TITLE = "title";
	public static final String KEYWORD = "keyword";
	public static final String VERSION = "version";
	public static final String USER_ID = "userId";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String USER_NAME = "userName";
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String VERSION_CHAIN = "versionChain";
	public static final String CAT_ID = "catId";
	public static final String NUM = "num";

	protected void initDao() {
		// do nothing
	}

	public void save(DocumentOld transientInstance) {
		log.debug("saving DocumentOld instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentOld persistentInstance) {
		log.debug("deleting DocumentOld instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentOld findById(java.lang.Integer id) {
		log.debug("getting DocumentOld instance with id: " + id);
		try {
			DocumentOld instance = (DocumentOld) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.DocumentOld", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocumentOld instance) {
		log.debug("finding DocumentOld instance by example");
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
		log.debug("finding DocumentOld instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DocumentOld as model where model."
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

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByDepartmentId(Object departmentId) {
		return findByProperty(DEPARTMENT_ID, departmentId);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByDepartmentName(Object departmentName) {
		return findByProperty(DEPARTMENT_NAME, departmentName);
	}

	public List findByVersionChain(Object versionChain) {
		return findByProperty(VERSION_CHAIN, versionChain);
	}

	public List findByCatId(Object catId) {
		return findByProperty(CAT_ID, catId);
	}

	public List findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	public List findAll() {
		log.debug("finding all DocumentOld instances");
		try {
			String queryString = "from DocumentOld";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocumentOld merge(DocumentOld detachedInstance) {
		log.debug("merging DocumentOld instance");
		try {
			DocumentOld result = (DocumentOld) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentOld instance) {
		log.debug("attaching dirty DocumentOld instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentOld instance) {
		log.debug("attaching clean DocumentOld instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentOldDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocumentOldDAO) ctx.getBean("DocumentOldDAO");
	}

	/**
	 * @param num
	 * @param version
	 */
	public DocumentOld gethistory(String num, Integer version) {
		    String hql="from DocumentOld as model where model.num ="+num+" and model.version = "
		    +version;
		List<DocumentOld> dolist =getHibernateTemplate().find(hql);
		if(dolist!=null)
		return dolist.get(0);
		else{
			return null;
		}
	}
}