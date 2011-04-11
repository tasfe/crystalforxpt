package com.combanc.monitor.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorSwitchModel;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorSwitchModel entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorSwitchModel
 * @author MyEclipse Persistence Tools
 */

public class MonitorSwitchModelDAO extends
		BaseHibernateDAO<MonitorSwitchModel, Integer> {
	private static final Log log = LogFactory
			.getLog(MonitorSwitchModelDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String NOTE = "note";
	public static final String FORMAT = "format";
	public static final String MODIFIABLE = "modifiable";
	public static final String LOGIN = "login";
	public static final String BACKUP = "backup";
	public static final String IP_MAC = "ipMac";
	public static final String ARP_DISBAND = "arpDisband";
	public static final String INTERFACE_OPEN = "interfaceOpen";
	public static final String INTERFACE_SHUTDOWN = "interfaceShutdown";
	public static final String SNMP_CONFIG = "snmpConfig";
	public static final String IOS_BACKUP = "iosBackup";
	public static final String OTHER = "other";
	public static final String SAVE = "save";
	public static final String LOGOUT = "logout";
	public static final String ISUSE = "isuse";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorSwitchModel transientInstance) {
		log.debug("saving MonitorSwitchModel instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorSwitchModel persistentInstance) {
		log.debug("deleting MonitorSwitchModel instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorSwitchModel findById(java.lang.Integer id) {
		log.debug("getting MonitorSwitchModel instance with id: " + id);
		try {
			MonitorSwitchModel instance = (MonitorSwitchModel) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorSwitchModel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MonitorSwitchModel instance) {
		log.debug("finding MonitorSwitchModel instance by example");
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
		log.debug("finding MonitorSwitchModel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorSwitchModel as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByNote(Object note) {
		return findByProperty(NOTE, note);
	}

	public List findByFormat(Object format) {
		return findByProperty(FORMAT, format);
	}

	public List findByModifiable(Object modifiable) {
		return findByProperty(MODIFIABLE, modifiable);
	}

	public List findByLogin(Object login) {
		return findByProperty(LOGIN, login);
	}

	public List findByBackup(Object backup) {
		return findByProperty(BACKUP, backup);
	}

	public List findByIpMac(Object ipMac) {
		return findByProperty(IP_MAC, ipMac);
	}

	public List findByArpDisband(Object arpDisband) {
		return findByProperty(ARP_DISBAND, arpDisband);
	}

	public List findByInterfaceOpen(Object interfaceOpen) {
		return findByProperty(INTERFACE_OPEN, interfaceOpen);
	}

	public List findByInterfaceShutdown(Object interfaceShutdown) {
		return findByProperty(INTERFACE_SHUTDOWN, interfaceShutdown);
	}

	public List findBySnmpConfig(Object snmpConfig) {
		return findByProperty(SNMP_CONFIG, snmpConfig);
	}

	public List findByIosBackup(Object iosBackup) {
		return findByProperty(IOS_BACKUP, iosBackup);
	}

	public List findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findBySave(Object save) {
		return findByProperty(SAVE, save);
	}

	public List findByLogout(Object logout) {
		return findByProperty(LOGOUT, logout);
	}

	public List findByIsuse(Object isuse) {
		return findByProperty(ISUSE, isuse);
	}

	public List findAll() {
		log.debug("finding all MonitorSwitchModel instances");
		try {
			String queryString = "from MonitorSwitchModel";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorSwitchModel merge(MonitorSwitchModel detachedInstance) {
		log.debug("merging MonitorSwitchModel instance");
		try {
			MonitorSwitchModel result = (MonitorSwitchModel) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorSwitchModel instance) {
		log.debug("attaching dirty MonitorSwitchModel instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorSwitchModel instance) {
		log.debug("attaching clean MonitorSwitchModel instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorSwitchModelDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorSwitchModelDAO) ctx.getBean("MonitorSwitchModelDAO");
	}
}