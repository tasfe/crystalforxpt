package com.combanc.itsm.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Menu;

/**
 	* A data access object (DAO) providing persistence and search support for Menu entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.combanc.itsm.dao.Menu
  * @author MyEclipse Persistence Tools 
 */

public class MenuDAO extends BaseHibernateDAO<Menu, Integer>  {
		 private static final Log log = LogFactory.getLog(MenuDAO.class);
		//property constants
	public static final String PARENT_ID = "parentId";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String URL = "url";
	public static final String VISIABLE = "visiable";
	public static final String ICON = "icon";
	public static final String ORDER_FLAG = "orderFlag";
	public static final String PRIV_FIELD = "privField";
	public static final String PROP1 = "prop1";
	public static final String PROP2 = "prop2";
	public static final String MEMO = "memo";
	public static final String ADD_USER = "addUser";
	public static final String MODIFY_USER = "modifyUser";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Menu transientInstance) {
        log.debug("saving Menu instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Menu persistentInstance) {
        log.debug("deleting Menu instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Menu findById( java.lang.Long id) {
        log.debug("getting Menu instance with id: " + id);
        try {
            Menu instance = (Menu) getHibernateTemplate()
                    .get("com.combanc.itsm.dao.Menu", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Menu instance) {
        log.debug("finding Menu instance by example");
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
      log.debug("finding Menu instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Menu as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByParentId(Object parentId) {
		log.debug("finding Menu instance with property: " + PARENT_ID
	            + ", value: " + parentId);
	      try {
	         String queryString = "from Menu as model where model." 
	         						+ PARENT_ID + "= ?"+" order by orderFlag ASC";
			 return getHibernateTemplate().find(queryString, parentId);
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
	
	public List findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List findByUrl(Object url
	) {
		return findByProperty(URL, url
		);
	}
	
	public List findByVisiable(Object visiable
	) {
		return findByProperty(VISIABLE, visiable
		);
	}
	
	public List findByIcon(Object icon
	) {
		return findByProperty(ICON, icon
		);
	}
	
	public List findByOrderFlag(Object orderFlag
	) {
		return findByProperty(ORDER_FLAG, orderFlag
		);
	}
	
	public List findByPrivField(Object privField
	) {
		return findByProperty(PRIV_FIELD, privField
		);
	}
	
	public List findByProp1(Object prop1
	) {
		return findByProperty(PROP1, prop1
		);
	}
	
	public List findByProp2(Object prop2
	) {
		return findByProperty(PROP2, prop2
		);
	}
	
	public List findByMemo(Object memo
	) {
		return findByProperty(MEMO, memo
		);
	}
	
	public List findByAddUser(Object addUser
	) {
		return findByProperty(ADD_USER, addUser
		);
	}
	
	public List findByModifyUser(Object modifyUser
	) {
		return findByProperty(MODIFY_USER, modifyUser
		);
	}
	

	public List findAll() {
		log.debug("finding all Menu instances");
		try {
			String queryString = "from Menu";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
		
    public Menu merge(Menu detachedInstance) {
        log.debug("merging Menu instance");
        try {
            Menu result = (Menu) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Menu instance) {
        log.debug("attaching dirty Menu instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Menu instance) {
        log.debug("attaching clean Menu instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static MenuDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (MenuDAO) ctx.getBean("MenuDAO");
	}
}