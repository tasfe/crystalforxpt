package com.combanc.itsm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.IpAddress;

/**
 * A data access object (DAO) psroviding persistence and search support for
 * IpAdress entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.combanc.itsm.pojo.IpAddress
 * @author MyEclipse Persistence Tools
 */

public class IpAddressDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(IpAddressDAO.class);
	// property constants
	public static final String APPLY_IPV4_COUNT = "applyIpv4Count";
	public static final String SERIAL_NUMBER = "serialNumber";
	public static final String APPLY_IPV6_COUNT = "applyIpv6Count";
	public static final String APPLY_IP_PURPOSE = "applyIpPurpose";
	public static final String IP_USE_ROOM = "ipUseRoom";
	public static final String UNITS_FULL_NAME = "unitsFullName";
	public static final String UNITS_ADDRESS = "unitsAddress";
	public static final String UNITS_EXTENT_IP_COUNT = "unitsExtentIpCount";
	public static final String UNITS_EXTENT_IP_PURPOSE = "unitsExtentIpPurpose";
	public static final String TECHNICAL_CONTACT = "technicalContact";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";
	public static final String APPLY_IP_CAUSE = "applyIpCause";
	public static final String UNITS_LEADER = "unitsLeader";
	public static final String APPLICATION = "application";
	public static final String RECEIVER = "receiver";
	public static final String COMPLETER = "completer";
	public static final String APPLY_IPV4_DETAIL = "applyIpv4Detail";
	public static final String APPLY_IPV6_DETAIL = "applyIpv6Detail";
	public static final String UNITS_IPV4_DETAIL = "unitsIpv4Detail";
	public static final String UNITS_IPV6_DETAIL = "unitsIpv6Detail";

	public void save(IpAddress transientInstance) {
		log.debug("saving IpAddress instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(IpAddress transientInstance) {
		log.debug("updating IpAddress instance");
		try {
			getHibernateTemplate().update(transientInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(IpAddress persistentInstance) {
		log.debug("deleting IpAddress instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	// 文件导出查询
	public List<IpAddress> queryByHqls(IpAddress ipAddress) {

		List<IpAddress> list = new ArrayList<IpAddress>();
		String sb = "from IpAddress as model where 1=1";
		String sql = sb.toString();
		try {
			list = getHibernateTemplate().find(sql);
		} catch (Exception e) {
			log.debug(e);
		}
		return list;
	}

	public IpAddress findById(java.lang.Integer id) {
		log.debug("getting IpAddress instance with id: " + id);
		try {
			IpAddress instance = (IpAddress)getHibernateTemplate().get(
					"com.combanc.itsm.pojo.IpAddress", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpAddress instance) {
		log.debug("finding IpAddress instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.IpAddress").add(
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
		log.debug("finding IpAddress instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from IpAddress as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySERIALNUMBER(Object serialNumber) {
		return findByProperty(SERIAL_NUMBER, serialNumber);
	}
	
	public List findByApplyIpv4Count(Object applyIpv4Count) {
		return findByProperty(APPLY_IPV4_COUNT, applyIpv4Count);
	}

	public List findByApplyIpv6Count(Object applyIpv6Count) {
		return findByProperty(APPLY_IPV6_COUNT, applyIpv6Count);
	}

	public List findByApplyIpPurpose(Object applyIpPurpose) {
		return findByProperty(APPLY_IP_PURPOSE, applyIpPurpose);
	}

	public List findByIpUseRoom(Object ipUseRoom) {
		return findByProperty(IP_USE_ROOM, ipUseRoom);
	}

	public List findByUnitsFullName(Object unitsFullName) {
		return findByProperty(UNITS_FULL_NAME, unitsFullName);
	}

	public List findByUnitsAddress(Object unitsAddress) {
		return findByProperty(UNITS_ADDRESS, unitsAddress);
	}

	public List findByUnitsExtentIpCount(Object unitsExtentIpCount) {
		return findByProperty(UNITS_EXTENT_IP_COUNT, unitsExtentIpCount);
	}

	public List findByUnitsExtentIpPurpose(Object unitsExtentIpPurpose) {
		return findByProperty(UNITS_EXTENT_IP_PURPOSE, unitsExtentIpPurpose);
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

	public List findByApplyIpCause(Object applyIpCause) {
		return findByProperty(APPLY_IP_CAUSE, applyIpCause);
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

	public List findByApplyIpv4Detail(Object applyIpv4Detail) {
		return findByProperty(APPLY_IPV4_DETAIL, applyIpv4Detail);
	}

	public List findByApplyIpv6Detail(Object applyIpv6Detail) {
		return findByProperty(APPLY_IPV6_DETAIL, applyIpv6Detail);
	}

	public List findByUnitsIpv4Detail(Object unitsIpv4Detail) {
		return findByProperty(UNITS_IPV4_DETAIL, unitsIpv4Detail);
	}

	public List findByUnitsIpv6Detail(Object unitsIpv6Detail) {
		return findByProperty(UNITS_IPV6_DETAIL, unitsIpv6Detail);
	}

	public List findAll() {
		log.debug("finding all IpAdress instances");
		try {
			String queryString = "from IpAddress";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpAddress merge(IpAddress detachedInstance) {
		log.debug("merging IpAddress instance");
		try {
			IpAddress result = (IpAddress) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpAddress instance) {
		log.debug("attaching dirty IpAddress instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpAddress instance) {
		log.debug("attaching clean IpAddress instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}