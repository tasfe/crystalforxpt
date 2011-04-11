package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ServiceCategory;

public class ServiceCategoryDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ServiceCategoryDAO.class);
	public static final String ITEM = "item";
	public static final String TYPE = "type";
	public static final String CODE = "code";
	public static final String ITEM_ZH = "itemZh";
	public static final String EXPLAINER = "explainer";
	public static final String ICO_URL = "icoUrl";
	public static final String OPEN_OBJECT = "openObject";
	public static final String SELF_SERVICE = "selfService";
	public static final String DESCRIPTION = "description";
	public static final String ISFORMGUIDE = "isFormguide";
	public static final String FORMGUIDE_ID = "formguideId";
	public static final String DEF_ESS = "defEss";
	public static final String DEF_EME = "defEme";
	public static final String IS_DEF = "isDef";
	public static final String PID = "pid";
	public static final String WORKFLOW = "workflow";
	public static final String APPROVER = "approver";
	public static final String CHARSET = "charset";
	public static final String SLA = "sla";

	protected void initDao() {
	}

	public void save(ServiceCategory transientInstance) {
		log.debug("saving ServiceCategory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServiceCategory persistentInstance) {
		log.debug("deleting ServiceCategory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServiceCategory findById(java.lang.Integer id) {
		log.debug("getting ServiceCategory instance with id: " + id);
		try {
			ServiceCategory instance = (ServiceCategory) getHibernateTemplate()
					.get("com.combanc.itsm.pojo.ServiceCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServiceCategory instance) {
		log.debug("finding ServiceCategory instance by example");
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
		log.debug("finding ServiceCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServiceCategory as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByItem(Object item) {
		return findByProperty(ITEM, item);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}
	public List findByIntType(int type) {
		return findByProperty(TYPE, type);
	}
	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findByItemZh(Object itemZh) {
		return findByProperty(ITEM_ZH, itemZh);
	}

	public List findByExplainer(Object explainer) {
		return findByProperty(EXPLAINER, explainer);
	}

	public List findByIcoUrl(Object icoUrl) {
		return findByProperty(ICO_URL, icoUrl);
	}

	public List findByOpenObject(Object openObject) {
		return findByProperty(OPEN_OBJECT, openObject);
	}

	public List findBySelfService(Object selfService) {
		return findByProperty(SELF_SERVICE, selfService);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByIsFormguide(Object isFormguide) {
		return findByProperty(ISFORMGUIDE, isFormguide);
	}

	public List findByFormguideId(Object formguideId) {
		return findByProperty(FORMGUIDE_ID, formguideId);
	}

	public List findByDefEss(Object defEss) {
		return findByProperty(DEF_ESS, defEss);
	}

	public List findByDefEme(Object defEme) {
		return findByProperty(DEF_EME, defEme);
	}

	public List findByIsDef(Object isDef) {
		return findByProperty(IS_DEF, isDef);
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByWorkflow(Object workflow) {
		return findByProperty(WORKFLOW, workflow);
	}

	public List findByApprover(Object approver) {
		return findByProperty(APPROVER, approver);
	}

	public List findByCharset(Object charset) {
		return findByProperty(CHARSET, charset);
	}

	public List findBySla(Object sla) {
		return findByProperty(SLA, sla);
	}

	public List findAll() {
		log.debug("finding all ServiceCategory instances");
		try {
			String queryString = "from ServiceCategory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServiceCategory merge(ServiceCategory detachedInstance) {
		log.debug("merging ServiceCategory instance");
		try {
			ServiceCategory result = (ServiceCategory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServiceCategory instance) {
		log.debug("attaching dirty ServiceCategory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServiceCategory instance) {
		log.debug("attaching clean ServiceCategory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServiceCategoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServiceCategoryDAO) ctx.getBean("ServiceCategoryDAO");
	}
}