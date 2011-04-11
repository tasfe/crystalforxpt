package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorPingResult;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorPingResult entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorPingResult
 * @author MyEclipse Persistence Tools
 */

public class MonitorPingResultDAO extends BaseHibernateDAO<MonitorPingResult, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorPingResultDAO.class);
	// property constants
	public static final String SUCCESS_COUNT = "successCount";
	public static final String SEND_COUNT = "sendCount";
	public static final String MIN_REPLY_TIME = "minReplyTime";
	public static final String MAX_REPLY_TIME = "maxReplyTime";
	public static final String SUM_REPLY_TIME = "sumReplyTime";
	public static final String SUM_TTL = "sumTtl";
	public static final String LOOP_GAP = "loopGap";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorPingResult transientInstance) {
		log.debug("saving MonitorPingResult instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorPingResult persistentInstance) {
		log.debug("deleting MonitorPingResult instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorPingResult findById(java.lang.Integer id) {
		log.debug("getting MonitorPingResult instance with id: " + id);
		try {
			MonitorPingResult instance = (MonitorPingResult) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorPingResult", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorPingResult instance) {
		log.debug("finding MonitorPingResult instance by example");
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
		log.debug("finding MonitorPingResult instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorPingResult as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySuccessCount(Object successCount) {
		return findByProperty(SUCCESS_COUNT, successCount);
	}

	public List findBySendCount(Object sendCount) {
		return findByProperty(SEND_COUNT, sendCount);
	}

	public List findByMinReplyTime(Object minReplyTime) {
		return findByProperty(MIN_REPLY_TIME, minReplyTime);
	}

	public List findByMaxReplyTime(Object maxReplyTime) {
		return findByProperty(MAX_REPLY_TIME, maxReplyTime);
	}

	public List findBySumReplyTime(Object sumReplyTime) {
		return findByProperty(SUM_REPLY_TIME, sumReplyTime);
	}

	public List findBySumTtl(Object sumTtl) {
		return findByProperty(SUM_TTL, sumTtl);
	}

	public List findByLoopGap(Object loopGap) {
		return findByProperty(LOOP_GAP, loopGap);
	}

	public List findAll() {
		log.debug("finding all MonitorPingResult instances");
		try {
			String queryString = "from MonitorPingResult";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorPingResult merge(MonitorPingResult detachedInstance) {
		log.debug("merging MonitorPingResult instance");
		try {
			MonitorPingResult result = (MonitorPingResult) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorPingResult instance) {
		log.debug("attaching dirty MonitorPingResult instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorPingResult instance) {
		log.debug("attaching clean MonitorPingResult instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorPingResultDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorPingResultDAO) ctx.getBean("MonitorPingResultDAO");
	}
	
	public int[] batchInsert(List<MonitorPingResult> pingResultList) {
		log.debug("batchInsert MonitorPingResult");
		final List<MonitorPingResult> resultList = pingResultList;
		String sql = "INSERT INTO monitor_ping_result (PING_DEST_ID, SUCCESS_COUNT, SEND_COUNT, " + 
				" MIN_REPLY_TIME, MAX_REPLY_TIME, SUM_REPLY_TIME, SUM_TTL, LOOP_GAP, " +
				"COMPLETED_TIME) values (?,?,?,?,?,?,?,?,?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				if (null != resultList.get(i).getMonitorPingDest() ) {
					ps.setInt(1, resultList.get(i).getMonitorPingDest().getId());
				} else {
					System.out.println("resultList.get("+i+").getPingDest() == null");
					return;
				}
				ps.setInt(2, resultList.get(i).getSuccessCount());
				ps.setInt(3, resultList.get(i).getSendCount());
				ps.setInt(4, resultList.get(i).getMinReplyTime());
				ps.setInt(5, resultList.get(i).getMaxReplyTime());
				ps.setInt(6, resultList.get(i).getSumReplyTime());
				ps.setInt(7, resultList.get(i).getSumTtl());
				ps.setInt(8, resultList.get(i).getLoopGap());
				ps.setTimestamp(9, resultList.get(i).getCompletedTime());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return resultList.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql, setter);
	}
	
}