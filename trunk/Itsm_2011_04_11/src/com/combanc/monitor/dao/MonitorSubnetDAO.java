package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorSubnet;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorSubnet entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorSubnet
 * @author MyEclipse Persistence Tools
 */

public class MonitorSubnetDAO extends BaseHibernateDAO<MonitorSubnet, Integer> {
	private static final Log log = LogFactory.getLog(MonitorSubnetDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String SUBNET_ORDER = "subnetOrder";
	public static final String SCAN = "scan";
	public static final String START = "start";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorSubnet transientInstance) {
		log.debug("saving MonitorSubnet instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void updateFromDwr(MonitorSubnet transientInstance) {
		log.debug("saving MonitorSubnet instance");
		try {
			 Session session=getSession();
			 FlushMode flushMode=session.getFlushMode();
			 session.setFlushMode(FlushMode.AUTO);

			 session.update(transientInstance);
			 session.flush();
			 session.setFlushMode(flushMode);
			
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void delete(MonitorSubnet persistentInstance) {
		log.debug("deleting MonitorSubnet instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorSubnet findById(java.lang.Integer id) {
		log.debug("getting MonitorSubnet instance with id: " + id);
		try {
			MonitorSubnet instance = (MonitorSubnet) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorSubnet", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorSubnet instance) {
		log.debug("finding MonitorSubnet instance by example");
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
		log.debug("finding MonitorSubnet instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorSubnet as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(String name) {
		return findByProperty(NAME, name);
	}

	public List findBySubnetOrder(Object subnetOrder) {
		return findByProperty(SUBNET_ORDER, subnetOrder);
	}

	public List findByScan(Object scan) {
		return findByProperty(SCAN, scan);
	}

	public List findByStart(Object start) {
		return findByProperty(START, start);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findByNote3(Object note3) {
		return findByProperty(NOTE3, note3);
	}

	public List findByNote4(Object note4) {
		return findByProperty(NOTE4, note4);
	}

	public List findAll() {
		log.debug("finding all MonitorSubnet instances");
		try {
			String queryString = "from MonitorSubnet";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorSubnet merge(MonitorSubnet detachedInstance) {
		log.debug("merging MonitorSubnet instance");
		try {
			MonitorSubnet result = (MonitorSubnet) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorSubnet instance) {
		log.debug("attaching dirty MonitorSubnet instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorSubnet instance) {
		log.debug("attaching clean MonitorSubnet instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorSubnetDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorSubnetDAO) ctx.getBean("MonitorSubnetDAO");
	}
	
}