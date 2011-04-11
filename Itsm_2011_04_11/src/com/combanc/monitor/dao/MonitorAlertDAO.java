package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorAlert;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorAlert entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorAlert
 * @author MyEclipse Persistence Tools
 */

public class MonitorAlertDAO extends BaseHibernateDAO<MonitorAlert, Integer> {
	private static final Log log = LogFactory.getLog(MonitorAlertDAO.class);
	// property constants
	public static final String FILE = "file";
	public static final String OLD = "old";
	public static final String VALUE = "value";
	public static final String LIMEN = "limen";
	public static final String IP = "ip";
	public static final String MAC = "mac";
	public static final String UPLINK = "uplink";
	public static final String PORT = "port";
	public static final String INTERFACE_ = "interface_";
	public static final String DESCRIPTION = "description";
	public static final String HOST_NAME = "hostName";
	public static final String COMP_NAME = "compName";
	public static final String DOMAIN = "domain";
	public static final String LOGIN_NAME = "loginName";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorAlert transientInstance) {
		log.debug("saving MonitorAlert instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorAlert persistentInstance) {
		log.debug("deleting MonitorAlert instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorAlert findById(java.lang.Integer id) {
		log.debug("getting MonitorAlert instance with id: " + id);
		try {
			MonitorAlert instance = (MonitorAlert) getHibernateTemplate().get(
					"com.combanc.monitor.pojo.MonitorAlert", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorAlert instance) {
		log.debug("finding MonitorAlert instance by example");
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
		log.debug("finding MonitorAlert instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorAlert as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFile(Object file) {
		return findByProperty(FILE, file);
	}

	public List findByOld(Object old) {
		return findByProperty(OLD, old);
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findByLimen(Object limen) {
		return findByProperty(LIMEN, limen);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByMac(Object mac) {
		return findByProperty(MAC, mac);
	}

	public List findByUplink(Object uplink) {
		return findByProperty(UPLINK, uplink);
	}

	public List findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List findByInterface_(Object interface_) {
		return findByProperty(INTERFACE_, interface_);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByHostName(Object hostName) {
		return findByProperty(HOST_NAME, hostName);
	}

	public List findByCompName(Object compName) {
		return findByProperty(COMP_NAME, compName);
	}

	public List findByDomain(Object domain) {
		return findByProperty(DOMAIN, domain);
	}

	public List findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	public List findAll() {
		log.debug("finding all MonitorAlert instances");
		try {
			String queryString = "from MonitorAlert";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorAlert merge(MonitorAlert detachedInstance) {
		log.debug("merging MonitorAlert instance");
		try {
			MonitorAlert result = (MonitorAlert) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorAlert instance) {
		log.debug("attaching dirty MonitorAlert instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorAlert instance) {
		log.debug("attaching clean MonitorAlert instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorAlertDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorAlertDAO) ctx.getBean("MonitorAlertDAO");
	}
	
	public List findTopBySql(String hql, int top){
        HibernateTemplate ht = getHibernateTemplate();
        ht.setMaxResults(top);
        List list = ht.find(hql);
        ht.setMaxResults(0);
        return list;
    }

}