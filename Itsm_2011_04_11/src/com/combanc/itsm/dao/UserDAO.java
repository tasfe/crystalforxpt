package com.combanc.itsm.dao;

import java.util.List;

import javax.naming.directory.DirContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;

import com.combanc.common.core.Constants;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.itsm.pojo.Users;


public class UserDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UserDAO.class);

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String TRUENAME = "truename";
	public static final String MANAGER_ID = "managerId";
	public static final String USERTYPE = "usertype";
	public static final String PHONE = "phone";
	public static final String CELLPHONE = "cellphone";
	public static final String ADDRESS = "address";
	public static final String EMAIL = "email";
	public static final String LAST_ACCESS_IP = "lastAccessIp";
	public static final String STATUS = "status";
	public static final String ONLINE = "online";
	public static final String DESCRIPTION = "description";
	public static final String LOCATION = "location";
	public static final String SEX = "sex";
	public static final String ADD1 = "add1";
	public static final String ADD2 = "add2";
	public static final String ADD3 = "add3";
	
	private LdapTemplate ldapTemplate;

	@Override
	protected void initDao() {

	}
	
	/**
	 * 根据用户名密码验证
	 * @param userDn
	 * @param credentials
	 * @return
	 */
	public boolean authenticate(String userDn, String credentials) {
		DirContext ctx = null;
		try {
			ctx = ldapTemplate.getContextSource().getContext(userDn,
					credentials);
			return true;
		} catch (Exception e) {
			// Contextcreationfailed-authenticationdidnotsucceed

			return false;
		} finally {
			// ItisimperativethatthecreatedDirContextinstanceisalwaysclosed
			LdapUtils.closeContext(ctx);
		}
	}

	public void save(Users transientInstance) {
		log.debug("saving TUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Users persistentInstance) {
		log.debug("deleting TUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Users findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			Users instance = (Users) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.Users", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Users instance) {
		log.debug("finding Users instance by example");
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
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findEngineer() {
		log.debug("finding Engineer instance");
		try {
			String queryString = "from Users as model where model.usertype='engineer'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByTruename(Object truename) {
		return findByProperty(TRUENAME, truename);
	}

	public List findByManagerId(Object managerId) {
		return findByProperty(MANAGER_ID, managerId);
	}

	public List findByUsertype(Object usertype) {
		return findByProperty(USERTYPE, usertype);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByCellphone(Object cellphone) {
		return findByProperty(CELLPHONE, cellphone);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByLastAccessIp(Object lastAccessIp) {
		return findByProperty(LAST_ACCESS_IP, lastAccessIp);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByOnline(Object online) {
		return findByProperty(ONLINE, online);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByLocation(Integer location) {
		if(location==null) return null;
		try {
			return getHibernateTemplate().find( "from Users u  where u.location = " + location);
		} catch (RuntimeException re) {
			log.error("find by property location failed", re);
			throw re;
		}
	}
	
	public List findByDepartment(Integer department) {
		if(department==null) return null;
		try {
			return getHibernateTemplate().find( "from Users u  where u.department = " + department);
		} catch (RuntimeException re) {
			log.error("find by property department failed", re);
			throw re;
		}
	}
	
	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByAdd1(Object add1) {
		return findByProperty(ADD1, add1);
	}

	public List findByAdd2(Object add2) {
		return findByProperty(ADD2, add2);
	}

	public List findByAdd3(Object add3) {
		return findByProperty(ADD3, add3);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from Users as model where model.id!=1";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Users> findBySql(String queryString,final int offset,final int length){
		try {
			Query query = getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/*
	 * 查找用户类别为非用户的用户
	 */
	public List findUserNoCustomer() {
		log.debug("finding all User instances without usertype equals user");
		try {
			String queryString = "from Users u where u.usertype<>'user'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}

	public Users getUserWithDeptById(Integer userId) {
		return (Users) getHibernateTemplate().find(
				"from Users u join fetch u.location where id = " + userId).get(
				0);
	}

	public Users getUserWithLocationById(Integer userId) {
		return (Users) getHibernateTemplate().find(
				"from Users u join fetch u.department where id = " + userId)
				.get(0);
	}

	public Users getUserWithDeptAndLocationById(Integer userId) {
		return (Users) getHibernateTemplate().find(
				"from Users u join fetch u.department where id = " + userId)
				.get(0);
	}

	public List findByRoleId(Integer roleId) {
		String hql = "select vo from Users vo join vo.roleteams as roles where roles.id=? and vo.useFor =?";
		return findByHql(hql, new Object[] { roleId, Constants.FLAG_UNDELETED });
	}

	public List findByRole(Integer roleId) {
		String hql = "select vo from Users vo join vo.roleteams roles where roles.id=? and vo.useFor = ?";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED };
		return findByHql(hql, objs);
	}
	
	//新增加的方法;
	public Users searchUser (String query)
	{
		return (Users) retrieveObj(query);
	}
	
	
	//得到用户总数;
	public long getUserCount(String queryString)
	{
		return retrieveObjsCount(queryString);
	}
	//search
	public List<Users> searchUsers (String query)
	{
		return retrieveObjs(query);
	}

	public List<Users> searchUsers (String query, String value)
	{
		return retrieveObjs(query, value);
	}

	public List<Users> searchUsers (String query, String[] value)
	{
		return retrieveObjs(query, value);
	}

	public List<Users> searchUsers (String query, String value, int start, int number)
	{
		return retrieveObjs(query, value, start, number);
	}

	public List<Users> searchUsers (String query, String[] value, int start, int number)
	{
		return retrieveObjs(query, value, start, number);
	}

	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}

	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	
	public List<Users> getTopByNumWorkLog()
	{
		
		String sql="from Users us order by us.add1 desc limit 10";  
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		List tmpList=session.createQuery(sql).setMaxResults(10).list();
		return tmpList;
		
	}
	
}
