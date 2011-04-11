package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorUrlResponse;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorUrlResponse entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorUrlResponse
 * @author MyEclipse Persistence Tools
 */

public class MonitorUrlResponseDAO extends
		BaseHibernateDAO<MonitorUrlResponse, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorUrlResponseDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String ADDRESS = "address";
	public static final String PORT = "port";
	public static final String DOMAIN = "domain";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String REALM = "realm";
	public static final String CONTAIN = "contain";
	public static final String NOCONTAIN = "nocontain";
	public static final String SERVER_IP = "serverIp";
	public static final String REPLY_TIME = "replyTime";
	public static final String SCAN = "scan";
	public static final String STATE_CODE = "stateCode";
	public static final String TYPE = "type";
	public static final String SUBNET = "subnet";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorUrlResponse transientInstance) {
		log.debug("saving MonitorUrlResponse instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorUrlResponse persistentInstance) {
		log.debug("deleting MonitorUrlResponse instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorUrlResponse findById(java.lang.Integer id) {
		log.debug("getting MonitorUrlResponse instance with id: " + id);
		try {
			MonitorUrlResponse instance = (MonitorUrlResponse) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorUrlResponse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorUrlResponse instance) {
		log.debug("finding MonitorUrlResponse instance by example");
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
		log.debug("finding MonitorUrlResponse instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorUrlResponse as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List findByDomain(Object domain) {
		return findByProperty(DOMAIN, domain);
	}

	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByRealm(Object realm) {
		return findByProperty(REALM, realm);
	}

	public List findByContain(Object contain) {
		return findByProperty(CONTAIN, contain);
	}

	public List findByNocontain(Object nocontain) {
		return findByProperty(NOCONTAIN, nocontain);
	}

	public List findByServerIp(Object serverIp) {
		return findByProperty(SERVER_IP, serverIp);
	}

	public List findByReplyTime(Object replyTime) {
		return findByProperty(REPLY_TIME, replyTime);
	}

	public List findByScan(Object scan) {
		return findByProperty(SCAN, scan);
	}

	public List findByStateCode(Object stateCode) {
		return findByProperty(STATE_CODE, stateCode);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findBySubnet(Object subnet) {
		return findByProperty(SUBNET, subnet);
	}

	public List findAll() {
		log.debug("finding all MonitorUrlResponse instances");
		try {
			String queryString = "from MonitorUrlResponse";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorUrlResponse merge(MonitorUrlResponse detachedInstance) {
		log.debug("merging MonitorUrlResponse instance");
		try {
			MonitorUrlResponse result = (MonitorUrlResponse) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorUrlResponse instance) {
		log.debug("attaching dirty MonitorUrlResponse instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorUrlResponse instance) {
		log.debug("attaching clean MonitorUrlResponse instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorUrlResponseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorUrlResponseDAO) ctx.getBean("MonitorUrlResponseDAO");
	}
}