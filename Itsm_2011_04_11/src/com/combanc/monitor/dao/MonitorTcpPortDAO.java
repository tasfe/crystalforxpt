package com.combanc.monitor.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorTcpPort;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorTcpPort entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.dao.MonitorTcpPort
 * @author MyEclipse Persistence Tools
 */

public class MonitorTcpPortDAO extends BaseHibernateDAO<MonitorTcpPort, Integer> {
	private static final Log log = LogFactory.getLog(MonitorTcpPortDAO.class);
	// property constants
	public static final String PORT_NAME = "portName";
	public static final String PORT_TYPE = "portType";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorTcpPort transientInstance) {
		log.debug("saving MonitorTcpPort instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorTcpPort persistentInstance) {
		log.debug("deleting MonitorTcpPort instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorTcpPort findById(java.lang.String id) {
		log.debug("getting MonitorTcpPort instance with id: " + id);
		try {
			MonitorTcpPort instance = (MonitorTcpPort) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorTcpPort", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorTcpPort instance) {
		log.debug("finding MonitorTcpPort instance by example");
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
		log.debug("finding MonitorTcpPort instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorTcpPort as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPortName(Object portName) {
		return findByProperty(PORT_NAME, portName);
	}

	public List findByPortType(Object portType) {
		return findByProperty(PORT_TYPE, portType);
	}

	public List findAll() {
		log.debug("finding all MonitorTcpPort instances");
		try {
			String queryString = "from MonitorTcpPort";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorTcpPort merge(MonitorTcpPort detachedInstance) {
		log.debug("merging MonitorTcpPort instance");
		try {
			MonitorTcpPort result = (MonitorTcpPort) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorTcpPort instance) {
		log.debug("attaching dirty MonitorTcpPort instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorTcpPort instance) {
		log.debug("attaching clean MonitorTcpPort instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorTcpPortDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorTcpPortDAO) ctx.getBean("MonitorTcpPortDAO");
	}
}