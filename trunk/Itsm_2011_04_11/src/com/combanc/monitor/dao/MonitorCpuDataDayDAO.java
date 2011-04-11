package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorCpuDataDay;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorCpuDataDay entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorCpuDataDay
 * @author MyEclipse Persistence Tools
 */

public class MonitorCpuDataDayDAO extends
		BaseHibernateDAO<MonitorCpuDataDay, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorCpuDataDayDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String CPU = "cpu";
	public static final String MAX_CPU = "maxCpu";
	public static final String MIN_CPU = "minCpu";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorCpuDataDay transientInstance) {
		log.debug("saving MonitorCpuDataDay instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorCpuDataDay persistentInstance) {
		log.debug("deleting MonitorCpuDataDay instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorCpuDataDay findById(java.lang.Integer id) {
		log.debug("getting MonitorCpuDataDay instance with id: " + id);
		try {
			MonitorCpuDataDay instance = (MonitorCpuDataDay) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorCpuDataDay", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorCpuDataDay instance) {
		log.debug("finding MonitorCpuDataDay instance by example");
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
		log.debug("finding MonitorCpuDataDay instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorCpuDataDay as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByCpu(Object cpu) {
		return findByProperty(CPU, cpu);
	}

	public List findByMaxCpu(Object maxCpu) {
		return findByProperty(MAX_CPU, maxCpu);
	}

	public List findByMinCpu(Object minCpu) {
		return findByProperty(MIN_CPU, minCpu);
	}

	public List findAll() {
		log.debug("finding all MonitorCpuDataDay instances");
		try {
			String queryString = "from MonitorCpuDataDay";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorCpuDataDay merge(MonitorCpuDataDay detachedInstance) {
		log.debug("merging MonitorCpuDataDay instance");
		try {
			MonitorCpuDataDay result = (MonitorCpuDataDay) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorCpuDataDay instance) {
		log.debug("attaching dirty MonitorCpuDataDay instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorCpuDataDay instance) {
		log.debug("attaching clean MonitorCpuDataDay instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorCpuDataDayDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorCpuDataDayDAO) ctx.getBean("MonitorCpuDataDayDAO");
	}
	
	public int[] batchInsert(List<MonitorCpuDataDay> cpudataList) {
		log.debug("batchInsert MonitorCpuDataDay");
		final List<MonitorCpuDataDay> cpudata = cpudataList;
		String sql = "insert into monitor_cpu_data_day (IP,CPU, MAX_CPU, MIN_CPU, GATHER_TIME,DATA_INDEX) values (?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, cpudata.get(i).getIp());
				ps.setFloat(2, cpudata.get(i).getCpu());
				ps.setFloat(3, cpudata.get(i).getMaxCpu());
				ps.setFloat(4, cpudata.get(i).getMinCpu());
				ps.setTimestamp(5, cpudata.get(i).getGatherTime());
				ps.setInt(6, cpudata.get(i).getDataIndex());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return cpudata.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
	public List findByPropertys(String ip,  Timestamp start, Timestamp end,Integer dataIndex) {
		log.debug("finding MonitorCpuDataDay instance with propertys ");
		try {
			String queryString = "from MonitorCpuDataDay as model where model.ip = ? and gatherTime >= ? and gatherTime <= ? and dataIndex = ? order by gatherTime asc";
			return getHibernateTemplate().find(queryString, new Object[]{ip, start, end,dataIndex});
		} catch (RuntimeException re) {
			log.error("find by propertys name failed", re);
			throw re;
		}
	}

}