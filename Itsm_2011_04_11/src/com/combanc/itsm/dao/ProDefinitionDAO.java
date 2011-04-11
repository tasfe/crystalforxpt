package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.ProDefinition;

/**
 	* A data access object (DAO) providing persistence and search support for ProDefinition entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.combanc.itsm.dao.ProDefinition
  * @author MyEclipse Persistence Tools 
 */

public class ProDefinitionDAO extends BaseHibernateDAO<ProDefinition, Integer>  {
		 private static final Log log = LogFactory.getLog(ProDefinitionDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String DEPLOY_ID = "deployId";
	public static final String DEF_XML = "defXml";
	public static final String DRAW_DEF_XML = "drawDefXml";
	public static final String IS_DEFAULT = "isDefault";



	protected void initDao() {
		//do nothing
	}
    
    public void save(ProDefinition transientInstance) {
        log.debug("saving ProDefinition instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ProDefinition persistentInstance) {
        log.debug("deleting ProDefinition instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ProDefinition findById( java.lang.Integer id) {
        log.debug("getting ProDefinition instance with id: " + id);
        try {
            ProDefinition instance = (ProDefinition) getHibernateTemplate()
                    .get("com.combanc.itsm.pojo.ProDefinition", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public ProDefinition findByName( java.lang.String name) {
        log.debug("getting ProDefinition instance with name: " + name);
        try {
            ProDefinition instance = (ProDefinition) getHibernateTemplate()
                    .get("com.combanc.itsm.pojo.ProDefinition", name);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findByExample(ProDefinition instance) {
        log.debug("finding ProDefinition instance by example");
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
      log.debug("finding ProDefinition instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ProDefinition as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByDescription(Object description
	) {
		return findByProperty(DESCRIPTION, description
		);
	}
	
	public List findByDeployId(Object deployId
	) {
		return findByProperty(DEPLOY_ID, deployId
		);
	}
	
	public List findByDefXml(Object defXml
	) {
		return findByProperty(DEF_XML, defXml
		);
	}
	
	public List findByDrawDefXml(Object drawDefXml
	) {
		return findByProperty(DRAW_DEF_XML, drawDefXml
		);
	}
	
	public List findByIsDefault(Object isDefault
	) {
		return findByProperty(IS_DEFAULT, isDefault
		);
	}
	

	public List findAll() {
		log.debug("finding all ProDefinition instances");
		try {
			String queryString = "from ProDefinition";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ProDefinition merge(ProDefinition detachedInstance) {
        log.debug("merging ProDefinition instance");
        try {
            ProDefinition result = (ProDefinition) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ProDefinition instance) {
        log.debug("attaching dirty ProDefinition instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ProDefinition instance) {
        log.debug("attaching clean ProDefinition instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ProDefinitionDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ProDefinitionDAO) ctx.getBean("ProDefinitionDAO");
	}
}