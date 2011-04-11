package com.combanc.itsm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.DomainRegister;
import com.combanc.itsm.pojo.IpAddress;

/**
 * A data access object (DAO) providing persistence and search support for
 * Domain entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.DomainRegister
 * @author MyEclipse Persistence Tools
 */

public class DomainRegisterDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DomainRegisterDAO.class);
	// property constants
	public static final String SERIAL_NUMBER = "serialNumber";
	public static final String DOMAIN = "domain";
	public static final String IP_ADDRESS = "ipAddress";
	public static final String SERVER_LOCATION = "serverLocation";
	public static final String UNITS_FULL_NAME = "unitsFullName";
	public static final String UNITS_ADDRESS = "unitsAddress";
	public static final String TECHNICAL_CONTACT = "technicalContact";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String FOREIGN_DOMAIN = "foreignDomain";
	public static final String FOREIGN_IP_ADDRESS = "foreignIpAddress";
	public static final String FOREIGN_SERVER_LOCATION = "foreignServerLocation";
	public static final String FOREIGN_UNITS_FULL_NAME = "foreignUnitsFullName";
	public static final String FOREIGN_UNITS_ADDRESS = "foreignUnitsAddress";
	public static final String FOREIGN_TECHNICAL_CONTACT = "foreignTechnicalContact";
	public static final String FOREIGN_PHONE = "foreignPhone";
	public static final String FOREIGN_EMAIL = "foreignEmail";
	public static final String REMARK = "remark";
	public static final String UNITS_LEADER = "unitsLeader";
	public static final String APPLICATION = "application";
	public static final String RECEIVER = "receiver";
	public static final String COMPLETER = "completer";
	public static final String STATE = "state";
	public static final String APPROVAL_RESULT = "approvalResult";
	public static final String FAIL_REASON = "failReason";

	public void save(DomainRegister transientInstance) {
		log.debug("saving DomainRegister instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DomainRegister persistentInstance) {
		log.debug("deleting DomainRegister instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DomainRegister findById(java.lang.Integer id) {
		log.debug("getting DomainRegister instance with id: " + id);
		try {
			DomainRegister instance = (DomainRegister) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.DomainRegister", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	// 文件导出查询
	public List<DomainRegister> queryByHqls(DomainRegister domainRegister) {

		List<DomainRegister> list = new ArrayList<DomainRegister>();
		String sb = "from DomainRegister as model where 1=1";
		String sql = sb.toString();
		try {
			list = getHibernateTemplate().find(sql);
		} catch (Exception e) {
			log.debug(e);
		}
		return list;
	}

	public List findByExample(DomainRegister instance) {
		log.debug("finding DomainRegister instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.DomainRegister").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DomainRegister instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DomainRegister as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySerialNumber(Object serialNumber) {
		return findByProperty(SERIAL_NUMBER, serialNumber);
	}

	public List findByDomain(Object domain) {
		return findByProperty(DOMAIN, domain);
	}

	public List findByIpAddress(Object ipAddress) {
		return findByProperty(IP_ADDRESS, ipAddress);
	}

	public List findByServerLocation(Object serverLocation) {
		return findByProperty(SERVER_LOCATION, serverLocation);
	}

	public List findByUnitsFullName(Object unitsFullName) {
		return findByProperty(UNITS_FULL_NAME, unitsFullName);
	}

	public List findByUnitsAddress(Object unitsAddress) {
		return findByProperty(UNITS_ADDRESS, unitsAddress);
	}

	public List findByTechnicalContact(Object technicalContact) {
		return findByProperty(TECHNICAL_CONTACT, technicalContact);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByForeignDomain(Object foreignDomain) {
		return findByProperty(FOREIGN_DOMAIN, foreignDomain);
	}

	public List findByForeignIpAddress(Object foreignIpAddress) {
		return findByProperty(FOREIGN_IP_ADDRESS, foreignIpAddress);
	}

	public List findByForeignServerLocation(Object foreignServerLocation) {
		return findByProperty(FOREIGN_SERVER_LOCATION, foreignServerLocation);
	}

	public List findByForeignUnitsFullName(Object foreignUnitsFullName) {
		return findByProperty(FOREIGN_UNITS_FULL_NAME, foreignUnitsFullName);
	}

	public List findByForeignUnitsAddress(Object foreignUnitsAddress) {
		return findByProperty(FOREIGN_UNITS_ADDRESS, foreignUnitsAddress);
	}

	public List findByForeignTechnicalContact(Object foreignTechnicalContact) {
		return findByProperty(FOREIGN_TECHNICAL_CONTACT,
				foreignTechnicalContact);
	}

	public List findByForeignPhone(Object foreignPhone) {
		return findByProperty(FOREIGN_PHONE, foreignPhone);
	}

	public List findByForeignEmail(Object foreignEmail) {
		return findByProperty(FOREIGN_EMAIL, foreignEmail);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findByUnitsLeader(Object unitsLeader) {
		return findByProperty(UNITS_LEADER, unitsLeader);
	}

	public List findByApplication(Object application) {
		return findByProperty(APPLICATION, application);
	}

	public List findByReceiver(Object receiver) {
		return findByProperty(RECEIVER, receiver);
	}

	public List findByCompleter(Object completer) {
		return findByProperty(COMPLETER, completer);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByApprovalResult(Object approvalResult) {
		return findByProperty(APPROVAL_RESULT, approvalResult);
	}

	public List findByFailReason(Object failReason) {
		return findByProperty(FAIL_REASON, failReason);
	}

	public List findAll() {
		log.debug("finding all DomainRegister instances");
		try {
			String queryString = "from Domain";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DomainRegister merge(DomainRegister detachedInstance) {
		log.debug("merging DomainRegister instance");
		try {
			DomainRegister result = (DomainRegister) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DomainRegister instance) {
		log.debug("attaching dirty DomainRegister instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DomainRegister instance) {
		log.debug("attaching clean DomainRegister instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}