package com.netblizzard.common.core.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDao<T, ID extends Serializable> extends HibernateDaoSupport {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(BaseDao.class);
	
	private final String PACKAGE = "com.netblizzard";

	public int getCount(String hql) throws Exception {
		int count = 0;
		Query query = getSession().createQuery(hql);
		try {
			count = ((Long) query.iterate().next()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List getPageData(String sql, int currPage, int pageSize)
			throws Exception {
		Query query = getSession().createQuery(sql);
		query.setFirstResult((currPage - 1) * pageSize).setMaxResults(pageSize);
		return query.list();
	}

	private String getObjClassName(Object obj) throws Exception {
		if (obj instanceof String) {

			try {
				ClassLoader classLoader = Thread.currentThread()
						.getContextClassLoader();
				if (classLoader == null)
					classLoader = BaseDao.class.getClassLoader();
				classLoader.loadClass(obj.toString());
			} catch (ClassNotFoundException e) {
				try {
					Class.forName(obj.toString());
				} catch (ClassNotFoundException e1) {
					throw new Exception("该类没查到");
				}
			}
			return obj.toString();
		}
		return obj.getClass().getName();
	}

	@SuppressWarnings("unchecked")
	private String getClassWhere(Object obj, String objAlias) {
		if (null == objAlias || objAlias.trim().length() == 0)
			objAlias = "i";
		Field[] fields = obj.getClass().getDeclaredFields();
		String whereStr = "";
		if (objAlias.trim().length() > 0) {
			objAlias = objAlias.trim() + ".";
		}
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if (!name.equals("serialVersionUID")) {

				String prop = Character.toUpperCase(name.charAt(0))
						+ name.substring(1);
				String mname = "get" + prop;
				Class[] types = new Class[] {};
				try {
					Method method = obj.getClass().getMethod(mname, types);
					Object objValue = method.invoke(obj, new Object[0]);
					if (objValue != null) {
						if (logger.isDebugEnabled()) {
							logger.debug("get proprty name is" + mname
									+ " type is" + objValue.getClass());
						}
						if (objValue instanceof String) {
							whereStr += " and " + objAlias + name + " like '%"
									+ (String) objValue + "%'";
						} else if (objValue instanceof Integer
								|| objValue instanceof Long) {
							whereStr += " and " + objAlias + name + " = "
									+ objValue.toString();
						} else if (objValue instanceof Date) {
							whereStr += " and " + objAlias + name + " = '"
									+ objValue.toString() + "'";
						} else if (objValue.getClass().getName().indexOf(PACKAGE) > -1) {
							String objAliasTemp = objAlias;
							if (objAliasTemp.length() > 0
									&& objAliasTemp.substring(
											objAliasTemp.length() - 1).indexOf(
											".") == -1)
								objAliasTemp += ".";
							whereStr += getClassWhere(objValue, objAliasTemp
									+ name);
						} else {

						}
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		}
		return whereStr;
	}

	@SuppressWarnings("unchecked")
	protected String getSqlFindObjByAlias(Object obj, String objAlias)
			throws Exception {
		if (null == obj)
			new Exception("查询对象为空！");
		String sql = "from " + getObjClassName(obj) + " " + objAlias;
		if (obj instanceof String)
			return sql;
		Field[] fields = obj.getClass().getDeclaredFields();
		String whereStr = "";
		if (objAlias.trim().length() > 0) {
			objAlias = objAlias.trim() + ".";
		}
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			if (!name.equals("serialVersionUID")) {

				String prop = Character.toUpperCase(name.charAt(0))
						+ name.substring(1);
				String mname = "get" + prop;
				Class[] types = new Class[] {};
				try {
					Method method = obj.getClass().getMethod(mname, types);
					Object objValue = method.invoke(obj, new Object[0]);
					// TODO 值对象的判断还待添加
					if (objValue != null) {
						if (logger.isDebugEnabled()) {
							logger.debug("get proprty name is " + mname
									+ " type is" + objValue.getClass());
						}
						if (objValue instanceof String) {
							if (((String) objValue).trim().length() > 0) {
								whereStr += " and " + objAlias + name
										+ " like '%" + (String) objValue + "%'";
							}
						} else if (objValue instanceof Integer
								|| objValue instanceof Long) {
							whereStr += " and " + objAlias + name + " = "
									+ objValue.toString();
						} else if (objValue instanceof Date) {
							whereStr += " and " + objAlias + name + " = '"
									+ objValue.toString() + "'";
						} else if (objValue.getClass().getName().indexOf(
								"com.baseline") > -1) {
							String objAliasTemp = objAlias;
							if (objAliasTemp.length() > 0
									&& objAliasTemp.substring(
											objAliasTemp.length() - 1).indexOf(
											".") == -1)
								objAliasTemp += ".";
							whereStr += getClassWhere(objValue, objAliasTemp
									+ name);
						} else {

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (whereStr.length() > 0)
			sql += " where 1=1 " + whereStr;
		if (logger.isDebugEnabled()) {
			logger.debug(" generating sql=" + sql);
		}
		return sql;
	}

	public String getSqlFind(Object obj, String objAlias, String[] fields,
			String[] values) throws Exception {
		if (null == objAlias || objAlias.trim().length() == 0)
			objAlias = "i";
		if (fields == null || values == null)
			return getSqlFindObjByAlias(obj, objAlias);

		if (fields.length != values.length)
			return null;

		String sql = getSqlFindObjByAlias(obj, objAlias);
		if (objAlias.trim().length() > 0) {
			objAlias = objAlias.trim() + ".";
		}
		if (fields.length > 0) {
			String whereSql = "";
			if (sql.indexOf("where") == -1) {
				sql += " where ";
				whereSql = " 1=1 ";
			}
			for (int i = 0; i < fields.length; i++) {
				String val = values[i].replaceAll("=", "").replaceAll("'", "")
						.replaceAll("like", "").replaceAll("%", "").trim();

				if (fields[i].trim().indexOf(objAlias) != -1) {
					if (val.length() > 0) {
						whereSql += " and " + fields[i] + " " + values[i];
					}
				} else {
					if (val.length() > 0) {
						whereSql += " and " + objAlias + fields[i].trim() + " "
								+ values[i];
					}
				}
			}
			sql += whereSql;
		}
		return sql;

	}

	public String getSqlFind(Object obj, String objAlias, String[] fields,
			String[] values, String order) throws Exception {
		return getSqlFind(obj, objAlias, fields, values)
				+ (order == null || order.trim().length() == 0 ? ""
						: " order by " + order);
	}

	public String getSqlCount(Object obj, String objAlias, String[] fields,
			String[] values) throws Exception {
		return "select count(*) " + getSqlFind(obj, objAlias, fields, values);
	}

	/**
	 * 分页查询
	 *
	 * @param hql
	 *            查询的条件
	 * @param offset
	 *            开始记录
	 * @param length
	 *            一次查询几条记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List queryForPage(final String hql, final int offset,
			final int length) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			}
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByPropertys(Class<T> entityClass,
			String[] propertyNames, Object[] values) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("from " + entityClass.getName());
		strBuffer.append(" as model where ");
		for (int i = 0; i < propertyNames.length; i++) {
			if (i != 0)
				strBuffer.append(" and");
			strBuffer.append(" model.");
			strBuffer.append(propertyNames[i]);
			strBuffer.append("=");
			strBuffer.append("? ");
		}
		String queryString = strBuffer.toString();
		return this.getHibernateTemplate().find(queryString, values);
	}

	/**
	 * 查询所有记录数
	 *
	 * @return 总记录数
	 */
	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size();
	}

	@SuppressWarnings("unchecked")
	public List findBySql(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public static BaseDao getFromApplicationContext(ApplicationContext ctx) {
		return (BaseDao) ctx.getBean("BaseDao");
	}



}
