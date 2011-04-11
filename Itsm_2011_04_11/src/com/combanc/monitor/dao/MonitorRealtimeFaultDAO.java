package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorRealtimeFault;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRealtimeFault entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRealtimeFault
 * @author MyEclipse Persistence Tools
 */

public class MonitorRealtimeFaultDAO extends
		BaseHibernateDAO<MonitorRealtimeFault, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRealtimeFaultDAO.class);
	// property constants
	public static final String DATA = "data";
	public static final String IP = "ip";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String SUBNET_ID = "subnetId";

	protected void initDao() {
		// do nothing
		this.tableName="monitor_realtime_fault";
	}

	public void save(MonitorRealtimeFault transientInstance) {
		log.debug("saving MonitorRealtimeFault instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRealtimeFault persistentInstance) {
		log.debug("deleting MonitorRealtimeFault instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRealtimeFault findById(java.lang.Integer id) {
		log.debug("getting MonitorRealtimeFault instance with id: " + id);
		try {
			MonitorRealtimeFault instance = (MonitorRealtimeFault) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRealtimeFault", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRealtimeFault instance) {
		log.debug("finding MonitorRealtimeFault instance by example");
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
		log.debug("finding MonitorRealtimeFault instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRealtimeFault as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByData(Object data) {
		return findByProperty(DATA, data);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findBySubnetId(Object subnetId) {
		return findByProperty(SUBNET_ID, subnetId);
	}

	public List findAll() {
		log.debug("finding all MonitorRealtimeFault instances");
		try {
			String queryString = "from MonitorRealtimeFault";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRealtimeFault merge(MonitorRealtimeFault detachedInstance) {
		log.debug("merging MonitorRealtimeFault instance");
		try {
			MonitorRealtimeFault result = (MonitorRealtimeFault) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRealtimeFault instance) {
		log.debug("attaching dirty MonitorRealtimeFault instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRealtimeFault instance) {
		log.debug("attaching clean MonitorRealtimeFault instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRealtimeFaultDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRealtimeFaultDAO) ctx.getBean("MonitorRealtimeFaultDAO");
	}
	
	public int[] batchInsert( List< MonitorRealtimeFault> realtimeFaultList) {
		log.debug("batchInsert MonitorRealtimeFault");
		final List< MonitorRealtimeFault> list = realtimeFaultList;
		String sql = "INSERT INTO  monitor_realtime_fault (DATA, IP, NAME, TYPE) VALUES (?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, list.get(i).getData());
				ps.setString(2, list.get(i).getIp());
				ps.setString(3, list.get(i).getName());
				ps.setString(4, list.get(i).getType());
			}
			public int getBatchSize() {
				return list.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
		
	}
	
	public List<MonitorRealtimeFault> findTopN( int topN ) {
		try {
			log.debug("find MonitorRealtimeFault top N");
			String queryString = "from MonitorRealtimeFault ";
			Session ssn = getSession();
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			log.error("find MonitorRealtimeFault top N 异常",e);
			throw e;
		}
	}
}