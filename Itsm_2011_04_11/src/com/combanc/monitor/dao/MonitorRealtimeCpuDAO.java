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
import com.combanc.monitor.pojo.MonitorRealtimeCpu;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRealtimeCpu entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRealtimeCpu
 * @author MyEclipse Persistence Tools
 */

public class MonitorRealtimeCpuDAO extends
		BaseHibernateDAO<MonitorRealtimeCpu, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRealtimeCpuDAO.class);
	// property constants
	public static final String DATA = "data";
	public static final String IP = "ip";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String SUBNET_ID = "subnetId";
	
	protected void initDao() {
		// do nothing
		this.tableName="monitor_realtime_cpu";
	}

	public void save(MonitorRealtimeCpu transientInstance) {
		log.debug("saving MonitorRealtimeCpu instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRealtimeCpu persistentInstance) {
		log.debug("deleting MonitorRealtimeCpu instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRealtimeCpu findById(java.lang.Integer id) {
		log.debug("getting MonitorRealtimeCpu instance with id: " + id);
		try {
			MonitorRealtimeCpu instance = (MonitorRealtimeCpu) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRealtimeCpu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRealtimeCpu instance) {
		log.debug("finding MonitorRealtimeCpu instance by example");
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
		log.debug("finding MonitorRealtimeCpu instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRealtimeCpu as model where model."
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
		log.debug("finding all MonitorRealtimeCpu instances");
		try {
			String queryString = "from MonitorRealtimeCpu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRealtimeCpu merge(MonitorRealtimeCpu detachedInstance) {
		log.debug("merging MonitorRealtimeCpu instance");
		try {
			MonitorRealtimeCpu result = (MonitorRealtimeCpu) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRealtimeCpu instance) {
		log.debug("attaching dirty MonitorRealtimeCpu instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRealtimeCpu instance) {
		log.debug("attaching clean MonitorRealtimeCpu instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRealtimeCpuDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRealtimeCpuDAO) ctx.getBean("MonitorRealtimeCpuDAO");
	}
	
	public int[] batchInsert( List< MonitorRealtimeCpu> realtimeCpuList) {
		log.debug("batchInsert MonitorRealtimeCpu");
		final List< MonitorRealtimeCpu> list = realtimeCpuList;
		String sql = "INSERT INTO  monitor_realtime_cpu (DATA, IP, NAME, TYPE,GATHER_TIME) VALUES (?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setFloat(1, list.get(i).getData());
				ps.setString(2, list.get(i).getIp());
				ps.setString(3, list.get(i).getName());
				ps.setString(4, list.get(i).getType());
				ps.setTimestamp(5, list.get(i).getGatherTime());
			}
			public int getBatchSize() {
				return list.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
		
	}
	
	public List<MonitorRealtimeCpu> findTopN( int topN ) {
		try {
			log.debug("find MonitorRealtimeCpu top N");
			String queryString = "from MonitorRealtimeCpu order by data desc";
			Session ssn = getSession();
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			log.error("find MonitorRealtimeCpu top N 异常",e);
			throw e;
		}
	}
	
	public List<MonitorRealtimeCpu> findTopN( int subnetId,int topN ) {
		try {
			log.debug("find MonitorRealtimeCpu top N");
			String queryString = "from MonitorRealtimeCpu where ip in" +
					"(select s.monitorDevice.ip from com.combanc.monitor.pojo.MonitorSubnetDevice s where s.monitorSubnet.id="+subnetId+")" +
					" order by data desc";
			Session ssn = getSession();
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			log.error("find MonitorRealtimeCpu top N 异常",e);
			throw e;
		}
	}
}