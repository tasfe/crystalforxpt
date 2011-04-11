package com.combanc.itsm.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * DeviceInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.AssetsInfo
 * @author MyEclipse Persistence Tools
 */

public class AssetsInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AssetsInfoDAO.class);
	// property constants
	public static final String FREQUENCY = "frequency";
	public static final String NAME = "name";
	public static final String PRODUCER = "producer";
	public static final String TYPE = "type";
	public static final String CAPACITY = "capacity";
	public static final String MAC = "mac";
	public static final String MODEL = "model";
	public static final String PARTITION = "partition";
	public static final String SIZE = "size";
	public static final String USER_NAME = "userName";
	public static final String USER_INFO = "userInfo";
	public static final String USER_MENUS = "userMenus";
	public static final String STROE_PLACE = "stroePlace";
	public static final String VERSION = "version";
	public static final String USERFUL = "userful";
	public static final String DES = "des";
	public static final String TEMP1 = "temp1";
	public static final String TEMP2 = "temp2";
	public static final String TEMP3 = "temp3";

	protected void initDao() {
		// do nothing
	}

	public void save(AssetsInfo transientInstance) {
		log.debug("saving AssetsInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void sql(String sql) throws SQLException{
		log.debug("sql");
		try {
			getHibernateTemplate().getSessionFactory().getCurrentSession().connection().prepareStatement(sql);
			log.debug("sql successful");
		} catch (RuntimeException re) {
			log.error("sql failed", re);
			throw re;
		}
	}
	
	
	public void saveorupdate(AssetsInfo info){
		log.debug("update AssetsInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(info);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(AssetsInfo persistentInstance) {
		log.debug("deleting AssetsInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssetsInfo findById(java.lang.Long id) {
		log.debug("getting AssetsInfo instance with id: " + id);
		try {
			AssetsInfo instance = (AssetsInfo) getHibernateTemplate().get(
					"com.combanc.itsm.dao.AssetsInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsInfo instance) {
		log.debug("finding AssetsInfo instance by example");
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
		log.debug("finding AssetsInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByFrequency(Object frequency) {
		return findByProperty(FREQUENCY, frequency);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByProducer(Object producer) {
		return findByProperty(PRODUCER, producer);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByCapacity(Object capacity) {
		return findByProperty(CAPACITY, capacity);
	}

	public List findByMac(Object mac) {
		return findByProperty(MAC, mac);
	}

	public List findByModel(Object model) {
		return findByProperty(MODEL, model);
	}

	public List findByPartition(Object partition) {
		return findByProperty(PARTITION, partition);
	}

	public List findBySize(Object size) {
		return findByProperty(SIZE, size);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByUserInfo(Object userInfo) {
		return findByProperty(USER_INFO, userInfo);
	}

	public List findByUserMenus(Object userMenus) {
		return findByProperty(USER_MENUS, userMenus);
	}

	public List findByStroePlace(Object stroePlace) {
		return findByProperty(STROE_PLACE, stroePlace);
	}

	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List findByUserful(Object userful) {
		return findByProperty(USERFUL, userful);
	}

	public List findByDes(Object des) {
		return findByProperty(DES, des);
	}

	public List findByTemp1(Object temp1) {
		return findByProperty(TEMP1, temp1);
	}

	public List findByTemp2(Object temp2) {
		return findByProperty(TEMP2, temp2);
	}

	public List findByTemp3(Object temp3) {
		return findByProperty(TEMP3, temp3);
	}

	public List findAll() {
		log.debug("finding all AssetsInfo instances");
		try {
			String queryString = "from AssetsInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssetsInfo merge(AssetsInfo detachedInstance) {
		log.debug("merging AssetsInfo instance");
		try {
			AssetsInfo result = (AssetsInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsInfo instance) {
		log.debug("attaching dirty AssetsInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsInfo instance) {
		log.debug("attaching clean AssetsInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssetsInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AssetsInfoDAO) ctx.getBean("AssetsInfoDAO");
	}
}