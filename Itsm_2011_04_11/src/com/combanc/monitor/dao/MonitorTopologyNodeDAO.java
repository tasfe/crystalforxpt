package com.combanc.monitor.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorTopologyNode;

/**
 	* A data access object (DAO) providing persistence and search support for MonitorTopologyNode entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.combanc.monitor.dao.MonitorTopologyNode
  * @author MyEclipse Persistence Tools 
 */

public class MonitorTopologyNodeDAO extends BaseHibernateDAO<MonitorTopologyNode,Integer>  {
		 private static final Log log = LogFactory.getLog(MonitorTopologyNodeDAO.class);
		//property constants
	public static final String DEVICE_ID = "deviceId";
	public static final String CPU_OID = "cpuOid";
	public static final String CPU = "cpu";
	public static final String CPU_AVERAGE = "cpuAverage";
	public static final String CPU_PEAK = "cpuPeak";
	public static final String PEAK_TIME = "peakTime";
	public static final String REPLY_TIME = "replyTime";
	public static final String RUN_TIME = "runTime";
	public static final String STATUS = "status";
	public static final String GRAND_TOTAL = "grandTotal";
	public static final String COUNT = "count";
	public static final String TIME = "time";


	protected void initDao() {
		//do nothing
		this.tableName="monitor_topology_node";
	}
    
    public void save(MonitorTopologyNode transientInstance) {
        log.debug("saving MonitorTopologyNode instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MonitorTopologyNode persistentInstance) {
        log.debug("deleting MonitorTopologyNode instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MonitorTopologyNode findById( java.lang.Integer id) {
        log.debug("getting MonitorTopologyNode instance with id: " + id);
        try {
            MonitorTopologyNode instance = (MonitorTopologyNode) getHibernateTemplate()
                    .get("com.combanc.monitor.pojo.MonitorTopologyNode", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(MonitorTopologyNode instance) {
        log.debug("finding MonitorTopologyNode instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding MonitorTopologyNode instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MonitorTopologyNode as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDeviceId(Object deviceId) {
		return findByProperty(DEVICE_ID, deviceId);
	}
	
	public List findByCpuOid(Object cpuOid) {
		return findByProperty(CPU_OID, cpuOid);
	}
	
	public List findByCpu(Object cpu) {
		return findByProperty(CPU, cpu);
	}
	
	public List findByCpuAverage(Object cpuAverage) {
		return findByProperty(CPU_AVERAGE, cpuAverage);
	}
	
	public List findByCpuPeak(Object cpuPeak) {
		return findByProperty(CPU_PEAK, cpuPeak);
	}
	
	public List findByPeakTime(Object peakTime) {
		return findByProperty(PEAK_TIME, peakTime);
	}
	
	public List findByReplyTime(Object replyTime) {
		return findByProperty(REPLY_TIME, replyTime);
	}
	
	public List findByRunTime(Object runTime) {
		return findByProperty(RUN_TIME, runTime);
	}
	
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}
	
	
	public List findByGrandTotal(Object grandTotal) {
		return findByProperty(GRAND_TOTAL, grandTotal);
	}
	
	public List findByCount(Object count) {
		return findByProperty(COUNT, count);
	}
	

	public List findAll() {
		log.debug("finding all MonitorTopologyNode instances");
		try {
			String queryString = "from MonitorTopologyNode";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findBySubnetId(Integer subnetId) {
		log.debug("finding MonitorTopologyNode instance  instances by subnetId");
		try {
			String queryString = "from  MonitorTopologyNode n where n.device.id in (select d.monitorDevice.id from MonitorSubnetDevice d where d.monitorSubnet.id="+subnetId+")";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		
	}
    public MonitorTopologyNode merge(MonitorTopologyNode detachedInstance) {
        log.debug("merging MonitorTopologyNode instance");
        try {
            MonitorTopologyNode result = (MonitorTopologyNode) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MonitorTopologyNode instance) {
        log.debug("attaching dirty MonitorTopologyNode instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MonitorTopologyNode instance) {
        log.debug("attaching clean MonitorTopologyNode instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MonitorTopologyNodeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MonitorTopologyNodeDAO) ctx.getBean("MonitorTopologyNodeDAO");
	}
	
	 
}