package com.combanc.itsm.dao;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.Report;

/**
 * A data access object (DAO) providing persistence and search support for
 * Report entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.Report
 * @author MyEclipse Persistence Tools
 */

public class ReportDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ReportDAO.class);
	// property constants
	public static final String REPORT_TITLE = "reportTitle";
	public static final String REPROT_VALUE = "reprotValue";
	public static final String CREATOR = "creator";
	public static final String LINKTO = "linkto";
	public static final String LINKITEM = "linkitem";
	public static final String REQUDEPT = "requdept";

	protected void initDao() {
		// do nothing
	}

	public void save(Report transientInstance) {
		log.debug("saving Report instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Report persistentInstance) {
		log.debug("deleting Report instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(Report persistentInstance) {
		log.debug("updating Report instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public Report findById(java.lang.Integer id) {
		log.debug("getting Report instance with id: " + id);
		try {
			Report instance = (Report) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Report", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Report instance) {
		log.debug("finding Report instance by example");
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
		log.debug("finding Report instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Report as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByReportTitle(Object reportTitle) {
		return findByProperty(REPORT_TITLE, reportTitle);
	}

	public List findByReprotValue(Object reprotValue) {
		return findByProperty(REPROT_VALUE, reprotValue);
	}

	public List findByCreator(Object creator) {
		return findByProperty(CREATOR, creator);
	}

	public List findByLinkto(Object linkto) {
		return findByProperty(LINKTO, linkto);
	}

	public List findByLinkitem(Object linkitem) {
		return findByProperty(LINKITEM, linkitem);
	}

	public List findByRequdept(Object requdept) {
		return findByProperty(REQUDEPT, requdept);
	}

	public List findAll() {
		log.debug("finding all Report instances");
		try {
			String queryString = "from Report";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Report merge(Report detachedInstance) {
		log.debug("merging Report instance");
		try {
			Report result = (Report) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Report instance) {
		log.debug("attaching dirty Report instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Report instance) {
		log.debug("attaching clean Report instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReportDAO) ctx.getBean("ReportDAO");
	}
}