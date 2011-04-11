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
import com.combanc.monitor.pojo.MonitorRealtimeUse;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRealtimeUse entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRealtimeUse
 * @author MyEclipse Persistence Tools
 */

public class MonitorRealtimeUseDAO extends
		BaseHibernateDAO<MonitorRealtimeUse, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRealtimeUseDAO.class);
	// property constants
	public static final String TOTAL_NUM = "totalNum";
	public static final String EFFECTIVE_NUM = "effectiveNum";
	public static final String IP = "ip";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String EXIST = "exist";
	public static final String RATE = "rate";
	public static final String SUBNET_ID = "subnetId";
	
	protected void initDao() {
		// do nothing
		this.tableName="monitor_realtime_use";
	}

	public void save(MonitorRealtimeUse transientInstance) {
		log.debug("saving MonitorRealtimeUse instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRealtimeUse persistentInstance) {
		log.debug("deleting MonitorRealtimeUse instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRealtimeUse findById(java.lang.Integer id) {
		log.debug("getting MonitorRealtimeUse instance with id: " + id);
		try {
			MonitorRealtimeUse instance = (MonitorRealtimeUse) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRealtimeUse", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRealtimeUse instance) {
		log.debug("finding MonitorRealtimeUse instance by example");
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
		log.debug("finding MonitorRealtimeUse instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRealtimeUse as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTotalNum(Object totalNum) {
		return findByProperty(TOTAL_NUM, totalNum);
	}

	public List findByEffectiveNum(Object effectiveNum) {
		return findByProperty(EFFECTIVE_NUM, effectiveNum);
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

	public List findByExist(Object exist) {
		return findByProperty(EXIST, exist);
	}

	public List findByRate(Object rate) {
		return findByProperty(RATE, rate);
	}

	public List findBySubnetId(Object subnetId) {
		return findByProperty(SUBNET_ID, subnetId);
	}

	public List findAll() {
		log.debug("finding all MonitorRealtimeUse instances");
		try {
			String queryString = "from MonitorRealtimeUse";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRealtimeUse merge(MonitorRealtimeUse detachedInstance) {
		log.debug("merging MonitorRealtimeUse instance");
		try {
			MonitorRealtimeUse result = (MonitorRealtimeUse) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRealtimeUse instance) {
		log.debug("attaching dirty MonitorRealtimeUse instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRealtimeUse instance) {
		log.debug("attaching clean MonitorRealtimeUse instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRealtimeUseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRealtimeUseDAO) ctx.getBean("MonitorRealtimeUseDAO");
	}
	
	public int[] batchInsert( List<MonitorRealtimeUse> realtimeUseList) {
		log.debug("batchInsert MonitorRealtimeUse");
		final List<MonitorRealtimeUse> list = realtimeUseList;
		String sql = "INSERT INTO  monitor_realtime_use (TOTAL_NUM, EFFECTIVE_NUM, IP, NAME, TYPE,EXIST,RATE) VALUES (?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setFloat(1, list.get(i).getTotalNum());
				ps.setFloat(2, list.get(i).getEffectiveNum());
				ps.setString(3, list.get(i).getIp());
				ps.setString(4, list.get(i).getName());
				ps.setString(5, list.get(i).getType());
				ps.setBoolean(6, list.get(i).getExist());
				ps.setFloat(7, list.get(i).getRate());
			}
			public int getBatchSize() {
				return list.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
		
	}
	
	public List<MonitorRealtimeUse> findTopN( int topN ) {
		try {
			log.debug("find MonitorRealtimeUse top N");
			String queryString = "from MonitorRealtimeUse order by rate ";
			Session ssn = getSession();
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			log.error("find MonitorRealtimeUse top N 异常",e);
			throw e;
		}
	}
	
	public List<MonitorRealtimeUse> findTopN( int subnetId,int topN ) {
		try {
			log.debug("find MonitorRealtimeUse top N");
			String queryString = "from MonitorRealtimeUse where ip in" +
					"(select s.monitorDevice.ip from com.combanc.monitor.pojo.MonitorSubnetDevice s where s.monitorSubnet.id="+subnetId+")" +
					"order by rate ";
			Session ssn = getSession();
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			log.error("find MonitorRealtimeUse top N 异常",e);
			throw e;
		}
	}
}