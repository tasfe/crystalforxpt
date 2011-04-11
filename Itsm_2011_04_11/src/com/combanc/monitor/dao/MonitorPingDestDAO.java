package com.combanc.monitor.dao;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Set;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorPingDest;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorPingDest entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorPingDest
 * @author MyEclipse Persistence Tools
 */

public class MonitorPingDestDAO extends BaseHibernateDAO<MonitorPingDest, Integer>  {
	private static final Log log = LogFactory.getLog(MonitorPingDestDAO.class);
	// property constants
	public static final String DEST_IP_URL = "destIpUrl";
	public static final String HOST_NAME = "hostName";
	public static final String LOCATION = "location";
	public static final String MINISTRY = "ministry";
	public static final String DEPARTMENT = "department";
	public static final String USER = "user";
	public static final String PHONE = "phone";
	public static final String MAC = "mac";
	public static final String DESCRIPTION = "description";
	public static final String APPLICATION = "application";
	public static final String ASSETS_NO = "assetsNo";
	public static final String DISK_NO = "diskNo";
	public static final String NOTE = "note";
	public static final String IS_RUN = "isRun";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorPingDest transientInstance) {
		log.debug("saving MonitorPingDest instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorPingDest persistentInstance) {
		log.debug("deleting MonitorPingDest instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorPingDest findById(java.lang.Integer id) {
		log.debug("getting MonitorPingDest instance with id: " + id);
		try {
			MonitorPingDest instance = (MonitorPingDest) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorPingDest", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorPingDest instance) {
		log.debug("finding MonitorPingDest instance by example");
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
		log.debug("finding MonitorPingDest instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorPingDest as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDestIpUrl(Object destIpUrl) {
		return findByProperty(DEST_IP_URL, destIpUrl);
	}

	public List findByHostName(Object hostName) {
		return findByProperty(HOST_NAME, hostName);
	}

	public List findByLocation(Object location) {
		return findByProperty(LOCATION, location);
	}

	public List findByMinistry(Object ministry) {
		return findByProperty(MINISTRY, ministry);
	}

	public List findByDepartment(Object department) {
		return findByProperty(DEPARTMENT, department);
	}

	public List findByUser(Object user) {
		return findByProperty(USER, user);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByMac(Object mac) {
		return findByProperty(MAC, mac);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByApplication(Object application) {
		return findByProperty(APPLICATION, application);
	}

	public List findByAssetsNo(Object assetsNo) {
		return findByProperty(ASSETS_NO, assetsNo);
	}

	public List findByDiskNo(Object diskNo) {
		return findByProperty(DISK_NO, diskNo);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByIsRun(Object isRun) {
		return findByProperty(IS_RUN, isRun);
	}

	public List findAll() {
		log.debug("finding all MonitorPingDest instances");
		try {
			String queryString = "from MonitorPingDest";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorPingDest merge(MonitorPingDest detachedInstance) {
		log.debug("merging MonitorPingDest instance");
		try {
			MonitorPingDest result = (MonitorPingDest) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorPingDest instance) {
		log.debug("attaching dirty MonitorPingDest instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorPingDest instance) {
		log.debug("attaching clean MonitorPingDest instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorPingDestDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorPingDestDAO) ctx.getBean("MonitorPingDestDAO");
	}
}