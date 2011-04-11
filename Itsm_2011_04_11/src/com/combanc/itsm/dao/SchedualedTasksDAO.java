package com.combanc.itsm.dao;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.SchedualedTasks;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;


public class SchedualedTasksDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SchedualedTasksDAO.class);
	// property constants
	public static final String IMPACT = "impact";
	public static final String URGENCY = "urgency";
	public static final String PRO_NO = "proNo";
	public static final String KEY_WORD = "keyWord";
	public static final String PRO_OBJ = "proObj";
	public static final String SUBMIT = "submit";
	public static final String SERVERITY = "serverity";
	public static final String DETAIL = "detail";
	public static final String UP_FILE = "upFile";
	public static final String APPLICATION = "application";
	public static final String RATE = "rate";
	public static final String CONFIGURE = "configure";
	public static final String TMP1 = "tmp1";
	public static final String TMP2 = "tmp2";
	public static final String TMP3 = "tmp3";
	public static final String TMP4 = "tmp4";
	public static final String TMP5 = "tmp5";
	public List findBysql(String sql){
		return getHibernateTemplate().find(sql);
	}
	public void save(SchedualedTasks transientInstance) {
		log.debug("saving SchedualedTasks instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SchedualedTasks persistentInstance) {
		log.debug("deleting SchedualedTasks instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SchedualedTasks findById(java.lang.Integer id) {
		log.debug("getting SchedualedTasks instance with id: " + id);
		try {
			SchedualedTasks instance = (SchedualedTasks) getSession().get(
					"com.combanc.itsm.pojo.SchedualedTasks", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SchedualedTasks instance) {
		log.debug("finding SchedualedTasks instance by example");
		try {
			List results = getSession().createCriteria(
					"com.combanc.itsm.pojo.SchedualedTasks").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SchedualedTasks instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SchedualedTasks as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByImpact(Object impact) {
		return findByProperty(IMPACT, impact);
	}

	public List findByUrgency(Object urgency) {
		return findByProperty(URGENCY, urgency);
	}

	public List findByProNo(Object proNo) {
		return findByProperty(PRO_NO, proNo);
	}

	public List findByKeyWord(Object keyWord) {
		return findByProperty(KEY_WORD, keyWord);
	}

	public List findByProObj(Object proObj) {
		return findByProperty(PRO_OBJ, proObj);
	}

	public List findBySubmit(Object submit) {
		return findByProperty(SUBMIT, submit);
	}

	public List findByServerity(Object serverity) {
		return findByProperty(SERVERITY, serverity);
	}

	public List findByDetail(Object detail) {
		return findByProperty(DETAIL, detail);
	}

	public List findByUpFile(Object upFile) {
		return findByProperty(UP_FILE, upFile);
	}

	public List findByApplication(Object application) {
		return findByProperty(APPLICATION, application);
	}

	public List findByRate(Object rate) {
		return findByProperty(RATE, rate);
	}

	public List findByConfigure(Object configure) {
		return findByProperty(CONFIGURE, configure);
	}

	public List findByTmp1(Object tmp1) {
		return findByProperty(TMP1, tmp1);
	}

	public List findByTmp2(Object tmp2) {
		return findByProperty(TMP2, tmp2);
	}

	public List findByTmp3(Object tmp3) {
		return findByProperty(TMP3, tmp3);
	}

	public List findByTmp4(Object tmp4) {
		return findByProperty(TMP4, tmp4);
	}

	public List findByTmp5(Object tmp5) {
		return findByProperty(TMP5, tmp5);
	}

	public List findAll() {
		log.debug("finding all SchedualedTasks instances");
		try {
			String queryString = "from SchedualedTasks";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SchedualedTasks merge(SchedualedTasks detachedInstance) {
		log.debug("merging SchedualedTasks instance");
		try {
			SchedualedTasks result = (SchedualedTasks) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SchedualedTasks instance) {
		log.debug("attaching dirty SchedualedTasks instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SchedualedTasks instance) {
		log.debug("attaching clean SchedualedTasks instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}