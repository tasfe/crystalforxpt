package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Ldapadapter;

/**
 	* A data access object (DAO) providing persistence and search support for Ldapadapter entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.combanc.itsm.dao.Ldapadapter
  * @author MyEclipse Persistence Tools 
 */

public class LdapadapterDAO extends BaseHibernateDAO<Ldapadapter, Integer>  {
		 private static final Log log = LogFactory.getLog(LdapadapterDAO.class);
		//property constants
	public static final String DOMAINCONTROLLER = "domaincontroller";
	public static final String INITIAL_CONTEXT_FACTORY = "initialContextFactory";
	public static final String IS_SECURED = "isSecured";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String BASE_DN = "baseDn";
	public static final String SEARCH_FILTER = "searchFilter";
	public static final String LOGIN_ATTRIBUTE = "loginAttribute";
	public static final String MAIL_ATTRIBUTE = "mailAttribute";
	public static final String DN_ATTRIBUTE = "dnAttribute";
	public static final String LDAPSERVER_TYPE = "ldapserverType";
	public static final String ISIMPORTED = "isimported";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Ldapadapter transientInstance) {
        log.debug("saving Ldapadapter instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Ldapadapter persistentInstance) {
        log.debug("deleting Ldapadapter instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Ldapadapter findById( java.lang.Long id) {
        log.debug("getting Ldapadapter instance with id: " + id);
        try {
            Ldapadapter instance = (Ldapadapter) getHibernateTemplate()
                    .get("com.combanc.itsm.dao.Ldapadapter", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Ldapadapter instance) {
        log.debug("finding Ldapadapter instance by example");
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
      log.debug("finding Ldapadapter instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Ldapadapter as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDomaincontroller(Object domaincontroller
	) {
		return findByProperty(DOMAINCONTROLLER, domaincontroller
		);
	}
	
	public List findByInitialContextFactory(Object initialContextFactory
	) {
		return findByProperty(INITIAL_CONTEXT_FACTORY, initialContextFactory
		);
	}
	
	public List findByIsSecured(Object isSecured
	) {
		return findByProperty(IS_SECURED, isSecured
		);
	}
	
	public List findByUsername(Object username
	) {
		return findByProperty(USERNAME, username
		);
	}
	
	public List findByPassword(Object password
	) {
		return findByProperty(PASSWORD, password
		);
	}
	
	public List findByBaseDn(Object baseDn
	) {
		return findByProperty(BASE_DN, baseDn
		);
	}
	
	public List findBySearchFilter(Object searchFilter
	) {
		return findByProperty(SEARCH_FILTER, searchFilter
		);
	}
	
	public List findByLoginAttribute(Object loginAttribute
	) {
		return findByProperty(LOGIN_ATTRIBUTE, loginAttribute
		);
	}
	
	public List findByMailAttribute(Object mailAttribute
	) {
		return findByProperty(MAIL_ATTRIBUTE, mailAttribute
		);
	}
	
	public List findByDnAttribute(Object dnAttribute
	) {
		return findByProperty(DN_ATTRIBUTE, dnAttribute
		);
	}
	
	public List findByLdapserverType(Object ldapserverType
	) {
		return findByProperty(LDAPSERVER_TYPE, ldapserverType
		);
	}
	
	public List findByIsimported(Object isimported
	) {
		return findByProperty(ISIMPORTED, isimported
		);
	}
	

	public List findAll() {
		log.debug("finding all Ldapadapter instances");
		try {
			String queryString = "from Ldapadapter";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Ldapadapter merge(Ldapadapter detachedInstance) {
        log.debug("merging Ldapadapter instance");
        try {
            Ldapadapter result = (Ldapadapter) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ldapadapter instance) {
        log.debug("attaching dirty Ldapadapter instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Ldapadapter instance) {
        log.debug("attaching clean Ldapadapter instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static LdapadapterDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (LdapadapterDAO) ctx.getBean("LdapadapterDAO");
	}
}