package com.combanc.itsm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.DocumentCat;

public class DocumentCatDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DocumentCatDAO.class);
	public static final String PID = "pid";
	public static final String DOCUMENT = "document";
	public static final String DOCUMENT_SC = "documentSC";
	public static final String CODE = "code";

	protected void initDao() {
	}

	public void save(DocumentCat transientInstance) {
		log.debug("saving DocumentCat instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentCat persistentInstance) {
		log.debug("deleting DocumentCat instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentCat findById(java.lang.Integer id) {
		log.debug("getting DocumentCat instance with id: " + id);
		try {
			DocumentCat instance = (DocumentCat) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.DocumentCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocumentCat instance) {
		log.debug("finding DocumentCat instance by example");
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
		log.debug("finding DocumentCat instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DocumentCat as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findByDocument(Object document) {
		return findByProperty(DOCUMENT, document);
	}

	public List findByDocumentSc(Object documentSc) {
		return findByProperty(DOCUMENT_SC, documentSc);
	}

	public List findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List findAll() {
		log.debug("finding all DocumentCat instances");
		try {
			String queryString = "from DocumentCat";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocumentCat findByIdWithAuthority(final Integer id) {
		return (DocumentCat) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hql = "from DocumentCat d join fetch d.documentAuthoritys where d.id = "
								+ id;
						List<DocumentCat> list = session.createQuery(hql)
								.list();
						DocumentCat documentCat = null;
						if (list != null && list.size() != 0) {
							documentCat = list.get(0);
						}
						return documentCat;
					}
				});
	}

	public DocumentCat merge(DocumentCat detachedInstance) {
		log.debug("merging DocumentCat instance");
		try {
			DocumentCat result = (DocumentCat) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentCat instance) {
		log.debug("attaching dirty DocumentCat instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentCat instance) {
		log.debug("attaching clean DocumentCat instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentCatDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocumentCatDAO) ctx.getBean("DocumentCatDAO");
	}
}