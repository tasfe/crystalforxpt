package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorMacInfo;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorMacInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorMacInfo
 * @author MyEclipse Persistence Tools
 */

public class MonitorMacInfoDAO extends
		BaseHibernateDAO<MonitorMacInfo, Integer> {
	private static final Log log = LogFactory.getLog(MonitorMacInfoDAO.class);
	// property constants
	public static final String MAC = "mac";
	public static final String IP = "ip";
	public static final String ROOM = "room";
	public static final String MOVABLE = "movable";
	public static final String USER_NAME = "userName";
	public static final String PHONE_NUM = "phoneNum";
	public static final String EMAIL = "email";
	public static final String USER_NUM = "userNum";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorMacInfo transientInstance) {
		log.debug("saving MonitorMacInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorMacInfo persistentInstance) {
		log.debug("deleting MonitorMacInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorMacInfo findById(java.lang.Integer id) {
		log.debug("getting MonitorMacInfo instance with id: " + id);
		try {
			MonitorMacInfo instance = (MonitorMacInfo) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorMacInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorMacInfo instance) {
		log.debug("finding MonitorMacInfo instance by example");
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
		log.debug("finding MonitorMacInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorMacInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMac(Object mac) {
		return findByProperty(MAC, mac);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByRoom(Object room) {
		return findByProperty(ROOM, room);
	}

	public List findByMovable(Object movable) {
		return findByProperty(MOVABLE, movable);
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByPhoneNum(Object phoneNum) {
		return findByProperty(PHONE_NUM, phoneNum);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByUserNum(Object userNum) {
		return findByProperty(USER_NUM, userNum);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findAll() {
		log.debug("finding all MonitorMacInfo instances");
		try {
			String queryString = "from MonitorMacInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorMacInfo merge(MonitorMacInfo detachedInstance) {
		log.debug("merging MonitorMacInfo instance");
		try {
			MonitorMacInfo result = (MonitorMacInfo) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorMacInfo instance) {
		log.debug("attaching dirty MonitorMacInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorMacInfo instance) {
		log.debug("attaching clean MonitorMacInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorMacInfoDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorMacInfoDAO) ctx.getBean("MonitorMacInfoDAO");
	}
}