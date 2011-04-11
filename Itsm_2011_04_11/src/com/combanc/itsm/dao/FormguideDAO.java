package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Formguide;

public class FormguideDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(FormguideDAO.class);
	public static final String TEMP1 = "temp1";
	public static final String TEMP2 = "temp2";
	public static final String TEMP3 = "temp3";
	public static final String TEMP4 = "temp4";
	public static final String TEMP5 = "temp5";
	public static final String TEMP6 = "temp6";
	public static final String TEMP7 = "temp7";
	public static final String TEMP8 = "temp8";
	public static final String TEMP9 = "temp9";
	public static final String TEMP10 = "temp10";
	public static final String TEMP11 = "temp11";
	public static final String TEMP12 = "temp12";
	public static final String TEMP13 = "temp13";
	public static final String TEMP14 = "temp14";
	public static final String TEMP15 = "temp15";
	public static final String TEMP16 = "temp16";
	public static final String TEMP17 = "temp17";
	public static final String TEMP18 = "temp18";
	public static final String TEMP19 = "temp19";
	public static final String TEMP20 = "temp20";

	protected void initDao() {
		// do nothing
	}

	public void save(Formguide transientInstance) {
		log.debug("saving Formguide instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Formguide persistentInstance) {
		log.debug("deleting Formguide instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Formguide findById(java.lang.Integer id) {
		log.debug("getting Formguide instance with id: " + id);
		try {
			Formguide instance = (Formguide) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Formguide", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Formguide instance) {
		log.debug("finding Formguide instance by example");
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
		log.debug("finding Formguide instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Formguide as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
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

	public List findByTemp4(Object temp4) {
		return findByProperty(TEMP4, temp4);
	}

	public List findByTemp5(Object temp5) {
		return findByProperty(TEMP5, temp5);
	}

	public List findByTemp6(Object temp6) {
		return findByProperty(TEMP6, temp6);
	}

	public List findByTemp7(Object temp7) {
		return findByProperty(TEMP7, temp7);
	}

	public List findByTemp8(Object temp8) {
		return findByProperty(TEMP8, temp8);
	}

	public List findByTemp9(Object temp9) {
		return findByProperty(TEMP9, temp9);
	}

	public List findByTemp10(Object temp10) {
		return findByProperty(TEMP10, temp10);
	}

	public List findByTemp11(Object temp11) {
		return findByProperty(TEMP11, temp11);
	}

	public List findByTemp12(Object temp12) {
		return findByProperty(TEMP12, temp12);
	}

	public List findByTemp13(Object temp13) {
		return findByProperty(TEMP13, temp13);
	}

	public List findByTemp14(Object temp14) {
		return findByProperty(TEMP14, temp14);
	}

	public List findByTemp15(Object temp15) {
		return findByProperty(TEMP15, temp15);
	}

	public List findByTemp16(Object temp16) {
		return findByProperty(TEMP16, temp16);
	}

	public List findByTemp17(Object temp17) {
		return findByProperty(TEMP17, temp17);
	}

	public List findByTemp18(Object temp18) {
		return findByProperty(TEMP18, temp18);
	}

	public List findByTemp19(Object temp19) {
		return findByProperty(TEMP19, temp19);
	}

	public List findByTemp20(Object temp20) {
		return findByProperty(TEMP20, temp20);
	}

	public List findAll() {
		log.debug("finding all Formguide instances");
		try {
			String queryString = "from Formguide";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Formguide merge(Formguide detachedInstance) {
		log.debug("merging Formguide instance");
		try {
			Formguide result = (Formguide) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Formguide instance) {
		log.debug("attaching dirty Formguide instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Formguide instance) {
		log.debug("attaching clean Formguide instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FormguideDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FormguideDAO) ctx.getBean("FormguideDAO");
	}
}