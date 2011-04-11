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
import com.combanc.monitor.pojo.MonitorRegularDataHour;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRegularDataHour entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRegularDataHour
 * @author MyEclipse Persistence Tools
 */

public class MonitorRegularDataHourDAO extends
		BaseHibernateDAO<MonitorRegularDataHour, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRegularDataHourDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String INTERFACE_ = "interface_";
	public static final String TX_BYTE = "txByte";
	public static final String RX_BYTE = "rxByte";
	public static final String BI_TRAFFIC = "biTraffic";
	public static final String MAX_BI_TRAFFIC = "maxBiTraffic";
	public static final String MIN_BI_TRAFFIC = "minBiTraffic";
	public static final String DELIVERY_TRAFFIC = "deliveryTraffic";
	public static final String MAX_DELIVERY_TRAFFIC = "maxDeliveryTraffic";
	public static final String MIN_DELIVERY_TRAFFIC = "minDeliveryTraffic";
	public static final String RECEIVE_TRAFFIC = "receiveTraffic";
	public static final String MAX_RECEIVE_TRAFFIC = "maxReceiveTraffic";
	public static final String MIN_RECEIVE_TRAFFIC = "minReceiveTraffic";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorRegularDataHour transientInstance) {
		log.debug("saving MonitorRegularDataHour instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRegularDataHour persistentInstance) {
		log.debug("deleting MonitorRegularDataHour instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRegularDataHour findById(java.lang.Integer id) {
		log.debug("getting MonitorRegularDataHour instance with id: " + id);
		try {
			MonitorRegularDataHour instance = (MonitorRegularDataHour) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRegularDataHour", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRegularDataHour instance) {
		log.debug("finding MonitorRegularDataHour instance by example");
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
		log.debug("finding MonitorRegularDataHour instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRegularDataHour as model where model."
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

	public List findByInterface_(Object interface_) {
		return findByProperty(INTERFACE_, interface_);
	}

	public List findByTxByte(Object txByte) {
		return findByProperty(TX_BYTE, txByte);
	}

	public List findByRxByte(Object rxByte) {
		return findByProperty(RX_BYTE, rxByte);
	}

	public List findByBiTraffic(Object biTraffic) {
		return findByProperty(BI_TRAFFIC, biTraffic);
	}

	public List findByMaxBiTraffic(Object maxBiTraffic) {
		return findByProperty(MAX_BI_TRAFFIC, maxBiTraffic);
	}

	public List findByMinBiTraffic(Object minBiTraffic) {
		return findByProperty(MIN_BI_TRAFFIC, minBiTraffic);
	}

	public List findByDeliveryTraffic(Object deliveryTraffic) {
		return findByProperty(DELIVERY_TRAFFIC, deliveryTraffic);
	}

	public List findByMaxDeliveryTraffic(Object maxDeliveryTraffic) {
		return findByProperty(MAX_DELIVERY_TRAFFIC, maxDeliveryTraffic);
	}

	public List findByMinDeliveryTraffic(Object minDeliveryTraffic) {
		return findByProperty(MIN_DELIVERY_TRAFFIC, minDeliveryTraffic);
	}

	public List findByReceiveTraffic(Object receiveTraffic) {
		return findByProperty(RECEIVE_TRAFFIC, receiveTraffic);
	}

	public List findByMaxReceiveTraffic(Object maxReceiveTraffic) {
		return findByProperty(MAX_RECEIVE_TRAFFIC, maxReceiveTraffic);
	}

	public List findByMinReceiveTraffic(Object minReceiveTraffic) {
		return findByProperty(MIN_RECEIVE_TRAFFIC, minReceiveTraffic);
	}

	public List findAll() {
		log.debug("finding all MonitorRegularDataHour instances");
		try {
			String queryString = "from MonitorRegularDataHour";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRegularDataHour merge(MonitorRegularDataHour detachedInstance) {
		log.debug("merging MonitorRegularDataHour instance");
		try {
			MonitorRegularDataHour result = (MonitorRegularDataHour) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRegularDataHour instance) {
		log.debug("attaching dirty MonitorRegularDataHour instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRegularDataHour instance) {
		log.debug("attaching clean MonitorRegularDataHour instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRegularDataHourDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRegularDataHourDAO) ctx
				.getBean("MonitorRegularDataHourDAO");
	}
	
	public int[] batchInsert(List<MonitorRegularDataHour> regulardataList) {
		log.debug("batchInsert monitor_regular_data_hour");
		final List<MonitorRegularDataHour> regulardataHour = regulardataList;
		String sql = "insert into monitor_regular_data_hour (IP, INTERFACE,  " +
						" BI_TRAFFIC, MAX_BI_TRAFFIC,MIN_BI_TRAFFIC," +
						" DELIVERY_TRAFFIC,MAX_DELIVERY_TRAFFIC,MIN_DELIVERY_TRAFFIC," +
						" RECEIVE_TRAFFIC, MAX_RECEIVE_TRAFFIC,MIN_RECEIVE_TRAFFIC," +
						" GATHER_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, regulardataHour.get(i).getIp());
				ps.setString(2, regulardataHour.get(i).getInterface_());
				ps.setLong(3, regulardataHour.get(i).getBiTraffic());
				ps.setLong(4, regulardataHour.get(i).getMaxBiTraffic());
				ps.setLong(5, regulardataHour.get(i).getMinBiTraffic());
				ps.setLong(6, regulardataHour.get(i).getDeliveryTraffic());
				ps.setLong(7, regulardataHour.get(i).getMaxDeliveryTraffic());
				ps.setLong(8, regulardataHour.get(i).getMinDeliveryTraffic());
				ps.setLong(9, regulardataHour.get(i).getReceiveTraffic());
				ps.setLong(10, regulardataHour.get(i).getMaxReceiveTraffic());
				ps.setLong(11, regulardataHour.get(i).getMinReceiveTraffic());
				ps.setTimestamp(12, regulardataHour.get(i).getGatherTime());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return regulardataHour.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
	public List findByPropertys(String ip, String ifIndex, Timestamp start, Timestamp end) {
		log.debug("finding MonitorRegularDataHour instance with ip: "+ ip + " and ifIndex:"+ifIndex + " and gatherTime from "+start +" to "+ end);
		try {
			String queryString = "from MonitorRegularDataHour as p where p.ip= ? and p.interface_ = ? and p.gatherTime >= ? and p.gatherTime <= ?";
			return getHibernateTemplate().find(queryString, new Object[]{ip, ifIndex, start,end});
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}