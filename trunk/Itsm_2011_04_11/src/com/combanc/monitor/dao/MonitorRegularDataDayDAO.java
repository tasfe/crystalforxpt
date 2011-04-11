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
import com.combanc.monitor.pojo.MonitorRegularDataDay;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRegularDataDay entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRegularDataDay
 * @author MyEclipse Persistence Tools
 */

public class MonitorRegularDataDayDAO extends
		BaseHibernateDAO<MonitorRegularDataDay, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRegularDataDayDAO.class);
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

	public void save(MonitorRegularDataDay transientInstance) {
		log.debug("saving MonitorRegularDataDay instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRegularDataDay persistentInstance) {
		log.debug("deleting MonitorRegularDataDay instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRegularDataDay findById(java.lang.Integer id) {
		log.debug("getting MonitorRegularDataDay instance with id: " + id);
		try {
			MonitorRegularDataDay instance = (MonitorRegularDataDay) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRegularDataDay", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRegularDataDay instance) {
		log.debug("finding MonitorRegularDataDay instance by example");
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
		log.debug("finding MonitorRegularDataDay instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRegularDataDay as model where model."
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
		log.debug("finding all MonitorRegularDataDay instances");
		try {
			String queryString = "from MonitorRegularDataDay";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRegularDataDay merge(MonitorRegularDataDay detachedInstance) {
		log.debug("merging MonitorRegularDataDay instance");
		try {
			MonitorRegularDataDay result = (MonitorRegularDataDay) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRegularDataDay instance) {
		log.debug("attaching dirty MonitorRegularDataDay instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRegularDataDay instance) {
		log.debug("attaching clean MonitorRegularDataDay instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRegularDataDayDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRegularDataDayDAO) ctx
				.getBean("MonitorRegularDataDayDAO");
	}
	
	public int[] batchInsert(List<MonitorRegularDataDay> regulardataList) {
		log.debug("batchInsert monitor_regular_data_day");
		final List<MonitorRegularDataDay> regulardataDay = regulardataList;
		String sql = "insert into monitor_regular_data_day (IP, INTERFACE,  " +
						" BI_TRAFFIC, MAX_BI_TRAFFIC,MIN_BI_TRAFFIC," +
						" DELIVERY_TRAFFIC,MAX_DELIVERY_TRAFFIC,MIN_DELIVERY_TRAFFIC," +
						" RECEIVE_TRAFFIC, MAX_RECEIVE_TRAFFIC,MIN_RECEIVE_TRAFFIC," +
						" GATHER_TIME) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, regulardataDay.get(i).getIp());
				ps.setString(2, regulardataDay.get(i).getInterface_());
				ps.setLong(3, regulardataDay.get(i).getBiTraffic());
				ps.setLong(4, regulardataDay.get(i).getMaxBiTraffic());
				ps.setLong(5, regulardataDay.get(i).getMinBiTraffic());
				ps.setLong(6, regulardataDay.get(i).getDeliveryTraffic());
				ps.setLong(7, regulardataDay.get(i).getMaxDeliveryTraffic());
				ps.setLong(8, regulardataDay.get(i).getMinDeliveryTraffic());
				ps.setLong(9, regulardataDay.get(i).getReceiveTraffic());
				ps.setLong(10, regulardataDay.get(i).getMaxReceiveTraffic());
				ps.setLong(11, regulardataDay.get(i).getMinReceiveTraffic());
				ps.setTimestamp(12, regulardataDay.get(i).getGatherTime());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return regulardataDay.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
	public void delBeforeDate(Timestamp time) {
		String logStr = "delete monitor_regular_data_day before " + time.toString();
		log.debug(logStr);
		try {
			String[] sql = new String[1];
			sql[0] = "DELETE FROM monitor_regular_data_day WHERE GATHER_TIME < '" + time + "'";
			jdbcTemplate.batchUpdate(sql);

			log.debug(logStr + "  successful");
		} catch (RuntimeException re) {
			log.error(logStr + "  failed", re);
			throw re;
		}
	}
	
	
	public List findByPropertys(String ip, String ifIndex, Timestamp start, Timestamp end) {
		log.debug("finding MonitorRegularDataDay instance with ip: "+ ip + " and ifIndex:"+ifIndex + " and gatherTime from "+start +" to "+ end);
		try {
			String queryString = "from MonitorRegularDataDay as p where p.ip= ? and p.interface_ = ? and p.gatherTime >= ? and p.gatherTime <= ?";
			return getHibernateTemplate().find(queryString, new Object[]{ip, ifIndex, start,end});
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}