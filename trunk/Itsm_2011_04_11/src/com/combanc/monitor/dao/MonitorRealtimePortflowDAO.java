package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorRealtimePortflow;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorRealtimePortflow entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorRealtimePortflow
 * @author MyEclipse Persistence Tools
 */

public class MonitorRealtimePortflowDAO extends
		BaseHibernateDAO<MonitorRealtimePortflow, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorRealtimePortflowDAO.class);
	// property constants
	public static final String DEVICE_IP = "deviceIp";
	public static final String DEVICE_NAME = "deviceName";
	public static final String INTERFACE_ = "interface_";
	public static final String INTERFACE_DESCIPTION = "interfaceDescription";
	public static final String IS_LINKPORT = "isLinkPort";
	public static final String BILATERAL_TRAFFIC = "bilateralTraffic";
	public static final String DELIVERY_TRAFFIC = "deliveryTraffic";
	public static final String RECEIVE_TRAFFIC = "receiveTraffic";

	protected void initDao() {
		// do nothing
		this.tableName="monitor_realtime_portflow";
	}

	public void save(MonitorRealtimePortflow transientInstance) {
		log.debug("saving MonitorRealtimePortflow instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorRealtimePortflow persistentInstance) {
		log.debug("deleting MonitorRealtimePortflow instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorRealtimePortflow findById(java.lang.Integer id) {
		log.debug("getting MonitorRealtimePortflow instance with id: " + id);
		try {
			MonitorRealtimePortflow instance = (MonitorRealtimePortflow) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorRealtimePortflow", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorRealtimePortflow instance) {
		log.debug("finding MonitorRealtimePortflow instance by example");
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
		log.debug("finding MonitorRealtimePortflow instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorRealtimePortflow as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDeviceIp(Object deviceIp) {
		return findByProperty(DEVICE_IP, deviceIp);
	}

	public List findByDeviceDesc(Object isLinkport) {
		return findByProperty(IS_LINKPORT, isLinkport);
	}

	public List findByInterface_(Object interface_) {
		return findByProperty(INTERFACE_, interface_);
	}

	public List findByBilateralTraffic(Object bilateralTraffic) {
		return findByProperty(BILATERAL_TRAFFIC, bilateralTraffic);
	}

	public List findByDeliveryTraffic(Object deliveryTraffic) {
		return findByProperty(DELIVERY_TRAFFIC, deliveryTraffic);
	}

	public List findByReceiveTraffic(Object receiveTraffic) {
		return findByProperty(RECEIVE_TRAFFIC, receiveTraffic);
	}

	public List findAll() {
		log.debug("finding all MonitorRealtimePortflow instances");
		try {
			String queryString = "from MonitorRealtimePortflow";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorRealtimePortflow merge(
			MonitorRealtimePortflow detachedInstance) {
		log.debug("merging MonitorRealtimePortflow instance");
		try {
			MonitorRealtimePortflow result = (MonitorRealtimePortflow) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorRealtimePortflow instance) {
		log.debug("attaching dirty MonitorRealtimePortflow instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorRealtimePortflow instance) {
		log.debug("attaching clean MonitorRealtimePortflow instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorRealtimePortflowDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorRealtimePortflowDAO) ctx
				.getBean("MonitorRealtimePortflowDAO");
	}
	
	public int[] batchInsert( List< MonitorRealtimePortflow> realtimePortflowList) {
		log.debug("batchInsert MonitorRealtimePortflow");
		final List< MonitorRealtimePortflow> list = realtimePortflowList;
		String sql = "INSERT INTO  monitor_realtime_portflow (DEVICE_IP, DEVICE_NAME,DEVICE_TYPE, INTERFACE, INTERFACE_DESCIPTION,IS_LINKPORT,TX_BYTE,RX_BYTE,BILATERAL_TRAFFIC,DELIVERY_TRAFFIC,RECEIVE_TRAFFIC,GATHER_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, list.get(i).getDeviceIp());
				ps.setString(2, list.get(i).getDeviceName());
				ps.setString(3, list.get(i).getDeviceType());
				ps.setString(4, list.get(i).getInterface_());
				ps.setString(5, list.get(i).getInterfaceDescription());
				ps.setInt(6, list.get(i).getIsLinkport());
				ps.setLong(7, list.get(i).getTxByte());
				ps.setLong(8, list.get(i).getRxByte());
				ps.setLong(9, list.get(i).getBilateralTraffic());
				ps.setLong(10, list.get(i).getDeliveryTraffic());
				ps.setLong(11, list.get(i).getReceiveTraffic());
				ps.setTimestamp(12, list.get(i).getGatherTime());
				
			}
			public int getBatchSize() {
				return list.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
		
	}
	
	public int[] batchUpdate( List< MonitorRealtimePortflow> realtimePortflowList) {
		log.debug("batchUpdate MonitorRealtimePortflow");
		final List< MonitorRealtimePortflow> list = realtimePortflowList;
		String sql = "UPDATE  monitor_realtime_portflow " +
				" set DEVICE_IP = ?, " +
				" DEVICE_NAME = ?, " +
				" DEVICE_TYPE = ?, " +
				" INTERFACE = ? , " +
				" INTERFACE_DESCIPTION = ?," +
				" IS_LINKPORT = ?, " +
				" TX_BYTE = ?, " +
				" RX_BYTE = ? ," +
				" BILATERAL_TRAFFIC = ?, " +
				" DELIVERY_TRAFFIC = ?, " +
				" RECEIVE_TRAFFIC = ?, " +
				" GATHER_TIME = ? where ID = ?";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, list.get(i).getDeviceIp());
				ps.setString(2, list.get(i).getDeviceName());
				ps.setString(3, list.get(i).getDeviceType());
				ps.setString(4, list.get(i).getInterface_());
				ps.setString(5, list.get(i).getInterfaceDescription());
				ps.setInt(6, list.get(i).getIsLinkport());
				ps.setLong(7, list.get(i).getTxByte());
				ps.setLong(8, list.get(i).getRxByte());
				ps.setLong(9, list.get(i).getBilateralTraffic());
				ps.setLong(10, list.get(i).getDeliveryTraffic());
				ps.setLong(11, list.get(i).getReceiveTraffic());
				ps.setTimestamp(12, list.get(i).getGatherTime());
				ps.setInt(13, list.get(i).getId());
			}
			public int getBatchSize() {
				return list.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
		
	}
	
	public List<MonitorRealtimePortflow> findTopN(Integer subnetId, int topN ,Integer type) {
		try {
			List<MonitorRealtimePortflow> list=new ArrayList<MonitorRealtimePortflow>();
			log.debug("find MonitorRealtimePortflow top N");
			String condition = "";
			if(null != subnetId){
					condition = "and a.DEVICE_IP in (SELECT d.IP FROM monitor_device d,monitor_subnet_device m where d.ID = m.DEVICE_ID and m.SUBNET_ID="+subnetId+")";
			}
			String queryString =" select a.ID,a.DEVICE_IP,a.DEVICE_NAME,a.DEVICE_TYPE,a.INTERFACE,a.INTERFACE_DESCIPTION,"	+	
								" a.IS_LINKPORT,a.BILATERAL_TRAFFIC,a.DELIVERY_TRAFFIC,a.RECEIVE_TRAFFIC,a.GATHER_TIME from monitor_realtime_portflow  a," +
								" (select DEVICE_IP,INTERFACE ,max(GATHER_TIME) as GATHER_TIME from monitor_realtime_portflow where IS_LINKPORT="+type+" group by DEVICE_IP,INTERFACE) b" +
								" where a.DEVICE_IP=b.DEVICE_IP " +
								" and a.INTERFACE=b.INTERFACE " +
								" and a.GATHER_TIME=b.GATHER_TIME " +
								condition +
								" order by BILATERAL_TRAFFIC desc limit "+topN;
			List result=this.getJdbcTemplate().queryForList(queryString);
			Iterator it=result.iterator();
			while(it.hasNext()){
				Map m=(Map) it.next();
				MonitorRealtimePortflow po=new MonitorRealtimePortflow();
				po.setId(Integer.valueOf(m.get("ID").toString()));
				po.setDeviceIp(m.get("DEVICE_IP").toString());
				po.setDeviceName(null == m.get("DEVICE_NAME")?"":m.get("DEVICE_NAME").toString());
				po.setDeviceType(null == m.get("DEVICE_TYPE")?"":m.get("DEVICE_TYPE").toString());
				po.setInterface_(m.get("INTERFACE").toString());
				po.setInterfaceDescription(null == m.get("INTERFACE_DESCIPTION") ? "" : m.get("INTERFACE_DESCIPTION").toString());
				po.setIsLinkport(Integer.valueOf(m.get("IS_LINKPORT").toString()));
				po.setBilateralTraffic(Long.valueOf(m.get("BILATERAL_TRAFFIC").toString()));
				po.setDeliveryTraffic(Long.valueOf(m.get("DELIVERY_TRAFFIC").toString()));
				po.setReceiveTraffic(Long.valueOf(m.get("RECEIVE_TRAFFIC").toString()));
				po.setGatherTime(Timestamp.valueOf(m.get("GATHER_TIME").toString()));
				list.add(po);
			}
			
			return list;
		} catch (RuntimeException e) {
			log.error("find MonitorRealtimePortflow top N 异常",e);
			throw e;
		}
	}
}