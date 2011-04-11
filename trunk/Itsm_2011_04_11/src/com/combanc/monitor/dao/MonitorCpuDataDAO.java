package com.combanc.monitor.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorCpuData;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorCpuData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorCpuData
 * @author MyEclipse Persistence Tools
 */

public class MonitorCpuDataDAO extends
		BaseHibernateDAO<MonitorCpuData, Integer> {
	private static final Log log = LogFactory.getLog(MonitorCpuDataDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String CPU = "cpu";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorCpuData transientInstance) {
		log.debug("saving MonitorCpuData instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorCpuData persistentInstance) {
		log.debug("deleting MonitorCpuData instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorCpuData findById(java.lang.Integer id) {
		log.debug("getting MonitorCpuData instance with id: " + id);
		try {
			MonitorCpuData instance = (MonitorCpuData) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorCpuData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorCpuData instance) {
		log.debug("finding MonitorCpuData instance by example");
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
		log.debug("finding MonitorCpuData instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorCpuData as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertys(String ip,  Timestamp start, Timestamp end,Integer dataIndex) {
		log.debug("finding MonitorCpuData instance with propertys ");
		try {
			String queryString = "from MonitorCpuData as model where model.ip = ? and gatherTime >= ? and gatherTime <= ? and dataIndex = ? order by gatherTime asc";
			return getHibernateTemplate().find(queryString, new Object[]{ip, start, end,dataIndex});
		} catch (RuntimeException re) {
			log.error("find by propertys name failed", re);
			throw re;
		}
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByCpu(Object cpu) {
		return findByProperty(CPU, cpu);
	}

	public List findAll() {
		log.debug("finding all MonitorCpuData instances");
		try {
			String queryString = "from MonitorCpuData";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorCpuData merge(MonitorCpuData detachedInstance) {
		log.debug("merging MonitorCpuData instance");
		try {
			MonitorCpuData result = (MonitorCpuData) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorCpuData instance) {
		log.debug("attaching dirty MonitorCpuData instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorCpuData instance) {
		log.debug("attaching clean MonitorCpuData instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorCpuDataDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorCpuDataDAO) ctx.getBean("MonitorCpuDataDAO");
	}
	
}