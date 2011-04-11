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
import com.combanc.monitor.pojo.MonitorRegularData;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRegularData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRegularData
 * @author MyEclipse Persistence Tools
 */

public class MonitorRegularDataDAO extends
		BaseHibernateDAO<MonitorRegularData, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRegularDataDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String INTERFACE_ = "interface_";
	public static final String TX_BYTE = "txByte";
	public static final String RX_BYTE = "rxByte";
	public static final String BI_TRAFFIC = "biTraffic";
	public static final String DELIVERY_TRAFFIC = "deliveryTraffic";
	public static final String RECEIVE_TRAFFIC = "receiveTraffic";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorRegularData transientInstance) {
		log.debug("saving MonitorRegularData instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRegularData persistentInstance) {
		log.debug("deleting MonitorRegularData instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRegularData findById(java.lang.Integer id) {
		log.debug("getting MonitorRegularData instance with id: " + id);
		try {
			MonitorRegularData instance = (MonitorRegularData) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRegularData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRegularData instance) {
		log.debug("finding MonitorRegularData instance by example");
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
		log.debug("finding MonitorRegularData instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRegularData as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByPropertys(String ip, String ifIndex, Timestamp start, Timestamp end) {
		log.debug("finding MonitorRegularData instance with ip: "+ ip + " and ifIndex:"+ifIndex + " and gatherTime from "+start +" to "+ end);
		try {
			String queryString = "from MonitorRegularData as p where p.ip= ? and p.interface_ = ? and p.gatherTime >= ? and p.gatherTime <= ?";
			return getHibernateTemplate().find(queryString, new Object[]{ip, ifIndex, start,end});
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

	public List findByDeliveryTraffic(Object deliveryTraffic) {
		return findByProperty(DELIVERY_TRAFFIC, deliveryTraffic);
	}

	public List findByReceiveTraffic(Object receiveTraffic) {
		return findByProperty(RECEIVE_TRAFFIC, receiveTraffic);
	}

	public List findAll() {
		log.debug("finding all MonitorRegularData instances");
		try {
			String queryString = "from MonitorRegularData";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRegularData merge(MonitorRegularData detachedInstance) {
		log.debug("merging MonitorRegularData instance");
		try {
			MonitorRegularData result = (MonitorRegularData) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRegularData instance) {
		log.debug("attaching dirty MonitorRegularData instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRegularData instance) {
		log.debug("attaching clean MonitorRegularData instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRegularDataDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRegularDataDAO) ctx.getBean("MonitorRegularDataDAO");
	}
	
	public int[] batchInsert(List<MonitorRegularData> regulardataList) {
		log.debug("batchInsert monitor_regular_data");
		final List<MonitorRegularData> regulardata = regulardataList;
		String sql = "insert into monitor_regular_data (IP, INTERFACE,TX_BYTE, RX_BYTE, BI_TRAFFIC, DELIVERY_TRAFFIC, RECEIVE_TRAFFIC, GATHER_TIME) values (?,?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, regulardata.get(i).getIp());
				ps.setString(2, regulardata.get(i).getInterface_());
				ps.setLong(3, regulardata.get(i).getTxByte());
				ps.setLong(4, regulardata.get(i).getRxByte());
				ps.setLong(5, regulardata.get(i).getBiTraffic());
				ps.setLong(6, regulardata.get(i).getDeliveryTraffic());
				ps.setLong(7, regulardata.get(i).getReceiveTraffic());
				ps.setTimestamp(8, regulardata.get(i).getGatherTime());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return regulardata.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
}