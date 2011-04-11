package com.netblizzard.common.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.netblizzard.common.core.CriteriaCommand;
import com.netblizzard.common.core.FieldCommandImpl;
import com.netblizzard.common.core.PageBean;
import com.netblizzard.common.core.QueryFilter;
import com.netblizzard.common.core.SortCommandImpl;

/**
 * DAO操作基类<br>
 * 本DAO层实现了通用的数据操作
 *
 * @author 杨坤
 *
 * @param <T>
 *            POJO实体对象
 * @param <ID>
 *            ID
 */
@SuppressWarnings("unchecked")
public class BaseHibernateDAO<T, ID extends Serializable> extends
		HibernateDaoSupport {

	private static final Logger logger = Logger
			.getLogger(BaseHibernateDAO.class);
	private Class<T> persistentClass;

	protected Map<String, String> querys = new HashMap();

	protected JdbcTemplate jdbcTemplate;

	protected  String tableName;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public BaseHibernateDAO() {
	}

	public Class<T> getPersistentClass() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return persistentClass;
	}

	public T get(Class<T> entityClass, ID id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public T get(ID id) {
		return (T) getHibernateTemplate().get(getPersistentClass(), id);
	}

		/**
	 * 保存指定实体类
	 *
	 * @param entityobj
	 *            实体类
	 */
	public void save(T entity) {
		try {
			getHibernateTemplate().save(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体类成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("保存实体异常," + entity.getClass().getName(), e);
			throw e;
		}
	}
	/**
	 * 保存指定实体类  （指定FlushMode）
	 * @param entity 实体类
	 */
	public void saveWithFlushMode(T entity){
		try {
			 Session session=getSession();
			 FlushMode flushMode=session.getFlushMode();
			 session.setFlushMode(FlushMode.AUTO);
	
			 session.save(entity);
			 session.flush();
			 session.setFlushMode(flushMode);
			 logger.debug("保存实体类成功," + entity.getClass().getName());
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}
	
	public void evict(T entity) {
		getHibernateTemplate().evict(entity);
	}
	/**
	* added by dongyp 清理session
	*/
	public void sessionClear() {
		getHibernateTemplate().clear();
	}
	
	/**
	 * 更新
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}
	
	/**
	 * 更新指定实体类  （指定FlushMode）
	 * @param entity 实体类
	 */
	public void updateWithFlushMode(T entity) {
		try {
			 Session session=getSession();
			 session.clear();
			 FlushMode flushMode=session.getFlushMode();
			 session.setFlushMode(FlushMode.AUTO);
	
			 session.update(entity);
			 session.flush();
			 session.setFlushMode(flushMode);
			 logger.debug("更新实体类成功," + entity.getClass().getName());
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}
	
	
	/**
	 * 删除指定实体
	 *
	 * @param entityobj
	 *            实体类
	 */
	public void delete(T entity) {
		try {
			getHibernateTemplate().delete(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("删除实体类成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除实体异常", e);
			throw e;
		}
	}
	
	public void deleteWithFlushMode(T entity) {
		try {
			 Session session=getSession();
			 session.clear();
			 FlushMode flushMode=session.getFlushMode();
			 session.setFlushMode(FlushMode.AUTO);
			 session.delete(entity);
			 session.flush();
			 session.setFlushMode(flushMode);
			 logger.debug("删除实体类成功," + entity.getClass().getName());
		} catch (RuntimeException re) {
			logger.error("删除实体异常", re);
			throw re;
		}
	}
	
	public void remove(ID id) {
		getHibernateTemplate().delete(get(id));
	}
	
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 获取所有实体集合
	 *
	 * @param entityClass
	 *            实体
	 * @return 集合
	 */
	public List<T> findAll(Class<T> entityClass) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查找所有实体：" + entityClass.getName());
			}
			return getHibernateTemplate().find("from " + entityClass.getName());
		} catch (RuntimeException e) {
			logger.error("查找指定实体集合异常，实体：" + entityClass.getName(), e);
			throw e;
		}
	}
	
	/**
	 * 获取所有实体集合的前n条记录
	 *
	 * @param entityClass
	 *            实体
	 * @return 集合
	 */
	public List<T> findTopN(Class<T> entityClass, int topN) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("查找topN实体：" + entityClass.getName());
			}
			String queryString = "from " + entityClass.getName()
					+ " order by id";
			Session ssn = getSession();
			return ssn.createQuery(queryString).setMaxResults(topN).list();
		} catch (RuntimeException e) {
			logger.error("查找指定实体集合topN异常，实体：" + entityClass.getName(), e);
			throw e;
		}
	}
	
	/**
	 * 更新或保存指定实体
	 *
	 * @param entity
	 *            实体类
	 */
	public void saveOrUpdate(T entity) {
		try {
			getHibernateTemplate().saveOrUpdate(entity);
			if (logger.isDebugEnabled()) {
				logger.debug("更新或者保存实体成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("更新或保存实体异常", e);
			throw e;
		}
	}
	
	/**
	 * 查找指定ID实体类对象
	 *
	 * @param entityClass
	 *            实体Class
	 * @param id
	 *            实体ID
	 * @return 实体对象
	 */
	public T findById(Class<T> entityClass, ID id) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找ID为" + id + "的实体：" + entityClass.getName());
			}
			return (T) getHibernateTemplate().get(entityClass, id);
		} catch (RuntimeException e) {
			logger.error("查找指定ID实体异常，ID：" + id, e);
			throw e;
		}
	}
	
	/**
	 * 查询指定HQL，并返回集合
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变的参数列表
	 * @return 集合
	 */
	public List<Object> find(String hql, Object... values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查询指定HQL语句," + hql);
			}
			return getHibernateTemplate().find(hql, values);
		} catch (RuntimeException e) {
			logger.error("查询指定HQL异常，HQL：" + hql, e);
			throw e;
		}
	}
	
	/**
	 * 按照HQL语句查询唯一对象.
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数集合
	 * @return OBJECT对象
	 */
	public Object findUnique(final String hql, final Object[] values) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查询返回唯一结果的HQL语句," + hql);
			}
			return getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					Query query = createQuery(s, hql, values);
					return query.uniqueResult();
				}
			});
		} catch (RuntimeException e) {
			logger.error("查询指定HQL异常，HQL：" + hql, e);
			throw e;
		}
	}
	
	/**
	 * 查找指定HQL并返回INT型
	 *
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数列表
	 * @return INT
	 */
	public int findInt(final String hql, final Object... values) {
		return 0;
	}
	
	/**
	 * 获取指定实体Class指定条件的记录总数
	 *
	 * @param entityClass
	 *            实体Class
	 * @param where
	 *            HQL的查询条件,支持参数列表
	 * @param values
	 *            可变参数列表
	 * @return 记录总数
	 */
	public int findTotalCount(Class<T> entityClass, final String where,
			final Object... values) {
		String hql = "select count(e) from " + entityClass.getName() + " as e "
				+ where;
		return findInt(hql, values);
	}
	
	/**
	 * 获取指定实体Class的记录总数
	 *
	 * @param entityClass
	 *            实体Class
	 * @return 记录总数
	 */
	public int findTotalCount(Class<T> entityClass) {
		return findTotalCount(entityClass, "");
	}
	
	/**
	 * 查找指定属性的实体集合
	 *
	 * @param entityClass
	 *            实体
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 */
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找指定属性：" + propertyName + "为" + value + "的实体"
						+ entityClass.getName());
			}
			String queryStr = "from " + entityClass.getName()
					+ " as model where model." + propertyName + "=?";
			return getHibernateTemplate().find(queryStr, value);
		} catch (RuntimeException e) {
			logger.error("查找指定条件实体集合异常，条件：" + propertyName, e);
			throw e;
		}
	}
	
	/**
	 * 查找指定subnetId，指定属性的实体集合，不重复
	 *
	 * @param entityClass
	 *            实体
	 * @param subnetId
	 *            实体
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 */
	public List findByPropertySubnetId(Class<T> entityClass, Integer subnetId,
			String propertyName) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("开始查找指定属性：" + propertyName + "为" + "的实体"
						+ entityClass.getName());
			}
			String queryStr = "";
			if (subnetId.equals(-1)) {
				queryStr = "select distinct " + propertyName + " from "
						+ entityClass.getName();
				return getHibernateTemplate().find(queryStr);
			} else {
				queryStr = "select distinct " + propertyName + " from "
						+ entityClass.getName()
						+ " as model where model.subnet.subnetId=?";
				return getHibernateTemplate().find(queryStr, subnetId);
			}
	
		} catch (RuntimeException e) {
			logger.error("查找指定条件实体集合异常，条件：" + propertyName, e);
			throw e;
		}
	}
	
	/**
	 * 模糊查询指定条件对象集合 <br>
	 * 用法：可以实例化一个空的T对象，需要查询某个字段，就set该字段的条件然后调用本方法<br>
	 * 缺点：目前测试貌似只能支持String的模糊查询，虽然有办法重写，但没必要，其他用HQL<br>
	 *
	 * @param entity
	 *            条件实体
	 * @return 结合
	 */
	public List<T> findByExample(T entity) {
		try {
			List<T> results = getHibernateTemplate().findByExample(entity);
			return results;
		} catch (RuntimeException re) {
			logger.error("查找指定条件实体集合异常", re);
			throw re;
		}
	}
	
	/**
	 * 补充方法(未测) 据说可以无视session的状态持久化对象
	 *
	 * @param entity
	 *            实体类
	 * @return 持久后的实体类
	 */
	public T merge(T entity) {
		try {
			T result = (T) getHibernateTemplate().merge(entity);
			return result;
		} catch (RuntimeException re) {
			logger.error("merge异常", re);
			throw re;
		}
	}
	
	/**
	 * 清除实体的锁定状态<br>
	 * 方法未测
	 *
	 * @param entity
	 *            实体
	 */
	public void attachClean(T entity) {
		try {
			getHibernateTemplate().lock(entity, LockMode.NONE);
		} catch (RuntimeException re) {
			logger.error("实体解锁异常", re);
			throw re;
		}
	}
	
	public void flush() {
		getHibernateTemplate().flush();
	}
	
	/**
	 * 根据查询条件与参数列表创建Query对象
	 *
	 * @param session
	 *            Hibernate会话
	 * @param hql
	 *            HQL语句
	 * @param objects
	 *            参数列表
	 * @return Query对象
	 */
	public Query createQuery(Session session, String hql, Object... objects) {
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}
	
	/**
	 * 通过多个属性组合查询
	 *
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应于propertyNames的值 return 匹配的结果
	 */
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
	 * 通过多个属性统计数量
	 *
	 * @param propertyNames
	 *            属性名称数组
	 * @param values
	 *            对应的属性值数组 return
	 */
	public int countByPropertys(Class<T> entityClass, String[] propertyNames,
			Object[] values) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("select count(*) from " + entityClass.getName());
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
		Query query = this.getSession().createQuery(queryString);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
	
		List list = query.list();
		Long result = (Long) list.get(0);
		return result.intValue();
	}
	
	/**
	 * 查找T并通过某一属性排序
	 *
	 * @param property
	 *            排序依据的顺序
	 * @param isSequence
	 *            是否顺序排序,false为倒序
	 */
	public List<T> findAndOrderByProperty(Class<T> entityClass,
			int firstResult, int fetchSize, String propertyName,
			boolean isSequence) {
		String queryString = "from " + entityClass.getName()
				+ " as model order by model." + propertyName;
		if (isSequence == false) {
			queryString = queryString + " DESC";
		}
	
		Query queryObject = getSession().createQuery(queryString);
		queryObject.setFirstResult(firstResult);
		queryObject.setMaxResults(fetchSize);
		return queryObject.list();
	
	}
	
	/**
	 * 查找所有并通过某个属性排序
	 *
	 * @param propertyName
	 *            排序依据的属性名称
	 * @param isSequence
	 *            是否顺序排列
	 */
	public List<T> findAllAndOrderByProperty(Class<T> entityClass,
			String propertyName, boolean isSequence) {
		String queryString = "from " + entityClass.getName()
				+ " as model order by model." + propertyName;
		if (isSequence == false) {
			queryString = queryString + " DESC";
		}
	
		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();
	}
	
	public List<T> findByHql(final String hql, final Object[] objs) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (objs != null) {
					for (int i = 0; i < objs.length; ++i) {
						query.setParameter(i, objs[i]);
					}
				}
				return query.list();
			}
		});
	}
	
	public List findBySql(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public List<T> findByHql(String hql) {
		return findByHql(hql, null);
	}
	
	public void updateBySql(String hql) {
		getHibernateTemplate().bulkUpdate(hql);
	}
	
	public List<T> getAll() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from "
						+ BaseHibernateDAO.this.getPersistentClass().getName();
				Query query = session.createQuery(hql);
				return query.list();
			}
		});
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
	
	public Long getTotalItems(String queryString, final Object[] values) {
		int orderByIndex = queryString.toUpperCase().indexOf(" ORDER BY ");
	
		if (orderByIndex != -1) {
			queryString = queryString.substring(0, orderByIndex);
		}
	
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(
				queryString, queryString, Collections.EMPTY_MAP,
				(SessionFactoryImplementor) getSessionFactory());
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		final String sql = "select count(*) from ("
				+ queryTranslator.getSQLString() + ") tmp_count_t";
	
		Object reVal = getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if (values != null) {
					for (int i = 0; i < values.length; ++i) {
						query.setParameter(i, values[i]);
					}
				}
				return query.uniqueResult();
			}
		});
		return new Long(reVal.toString());
	}
	
	public List<T> findByHql(final String hql, final Object[] objs,
			final int firstResult, final int pageSize) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(firstResult).setMaxResults(pageSize);
				if (objs != null) {
					for (int i = 0; i < objs.length; ++i) {
						query.setParameter(i, objs[i]);
					}
				}
				return query.list();
			}
		});
	}
	
	public List<T> findByHql(String hql, Object[] objs, PageBean pb) {
		int totalItems = getTotalItems(hql, objs).intValue();
		pb.setTotalPage(totalItems);
		return findByHql(hql, objs, pb.getFirstResult(), pb.getPageSize());
	}
	
	public int getCountByFilter(final QueryFilter filter) {
		Integer count = (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session
								.createCriteria(getPersistentClass());
						for (int i = 0; i < filter.getCommands().size(); ++i) {
							CriteriaCommand command = (CriteriaCommand) filter
									.getCommands().get(i);
							if (!(command instanceof SortCommandImpl)) {
								criteria = command.execute(criteria);
							}
						}
						criteria.setProjection(Projections.rowCount());
						return criteria.uniqueResult();
					}
				});
		if (count == null)
			return new Integer(0).intValue();
		return count.intValue();
	}
	
	private Criteria setCriteriaByQueryFilter(Criteria criteria,
			QueryFilter filter) {
		for (int i = 0; i < filter.getCommands().size(); ++i) {
			criteria = ((CriteriaCommand) filter.getCommands().get(i))
					.execute(criteria);
		}
	
		criteria.setFirstResult(filter.getPageBean().getFirstResult());
		criteria.setMaxResults(filter.getPageBean().getPageSize());
	
		return criteria;
	}
	
	public List getAll(final QueryFilter queryFilter) {
		if (StringUtils.isNotEmpty(queryFilter.getFilterName())) {
			return getAll2(queryFilter);
		}
	
		int totalCounts = getCountByFilter(queryFilter);
	
		queryFilter.getPageBean().setTotalPage(totalCounts);
	
		List resultList = (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session
								.createCriteria(getPersistentClass());
	
						queryFilter.getAliasSet().clear();
						setCriteriaByQueryFilter(criteria, queryFilter);
						return criteria.list();
					}
				});
		return resultList;
	}
	
	@SuppressWarnings("static-access")
	public List getAll2(QueryFilter queryFilter) {
		String hql = ((String) this.querys.get(queryFilter.getFilterName()))
				.trim();
	
		String newHql = null;
		String condition = null;
		String groupBy = null;
	
		int orderIndex = hql.toUpperCase().indexOf(" ORDER BY ");
		int whereIndex = hql.toUpperCase().indexOf(" WHERE ");
	
		if (orderIndex < 0) {
			orderIndex = hql.length();
		}
		if (whereIndex < 0) {
			whereIndex = hql.length();
		}
	
		if (whereIndex < 0) {
			condition = " where 1=1 ";
		} else {
			condition = hql.substring(whereIndex + 7, orderIndex);
	
			this.logger.debug("condition:" + condition);
	
			Pattern groupByPattern = Pattern.compile(" GROUP BY [\\w|.]+");
			Matcher m = groupByPattern.matcher(condition.toUpperCase());
	
			if (m.find()) {
				groupBy = condition.substring(m.start(), m.end());
				condition = condition.replace(groupBy, " ");
			}
			condition = " where (" + condition + ")";
		}
	
		String sortDesc = "";
	
		for (int i = 0; i < queryFilter.getCommands().size(); ++i) {
			CriteriaCommand command = (CriteriaCommand) queryFilter
					.getCommands().get(i);
			if (command instanceof FieldCommandImpl) {
				condition = condition + " and "
						+ ((FieldCommandImpl) command).getPartHql();
			} else if (command instanceof SortCommandImpl) {
				if (!"".equals(sortDesc)) {
					sortDesc = sortDesc + ",";
				}
				sortDesc = sortDesc + ((SortCommandImpl) command).getPartHql();
			}
		}
	
		newHql = hql.substring(0, whereIndex);
	
		if (queryFilter.getAliasSet().size() > 0) {
			int fromIndex = newHql.indexOf(" FROM ");
			String entityAliasName = null;
			if (fromIndex > 0) {
				String afterFrom = newHql.substring(fromIndex + 6);
	
				String[] keys = afterFrom.split("[ ]");
				if ((keys.length > 1)
						&& (!keys[1].toUpperCase().equals("ORDER"))
						&& (!keys[1].toUpperCase().equals("JOIN"))) {
					entityAliasName = keys[1];
				}
	
				if (entityAliasName == null) {
					entityAliasName = "vo";
					newHql = newHql.replace(keys[0], keys[0] + " "
							+ entityAliasName);
				}
	
			}
	
			String joinHql = "";
			Iterator it = queryFilter.getAliasSet().iterator();
			while (it.hasNext()) {
				String joinVo = (String) it.next();
				joinHql = joinHql + " join " + entityAliasName + "." + joinVo
						+ " " + joinVo;
			}
	
			if (!"".equals(joinHql)) {
				newHql = newHql + joinHql;
			}
		}
	
		newHql = newHql + condition;
	
		if (groupBy != null) {
			newHql = newHql + groupBy + " ";
		}
	
		if (!"".equals(sortDesc))
			newHql = newHql + " order by " + sortDesc;
		else {
			newHql = newHql + hql.substring(orderIndex);
		}
	
		Object[] params = queryFilter.getParamValueList().toArray();
	
		int totalItems = getTotalItems(newHql, params).intValue();
		queryFilter.getPageBean().setTotalPage(totalItems);
		if (this.logger.isDebugEnabled()) {
			this.logger.debug("new hql:" + newHql);
		}
		return find(newHql, params, queryFilter.getPageBean().getFirstResult(),
				queryFilter.getPageBean().getPageSize());
	}
	
	/**
	 * 查询所有记录数
	 *
	 * @return 总记录数
	 */
	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size();
	}
	
	public void setQuerys(Map<String, String> querys) {
		this.querys = querys;
	}
	
	
	
	
	protected void removeObj(Class<T> c, Long id)
	{
		T obj = (T) this.getHibernateTemplate().get(c, id);
	
		this.getHibernateTemplate().delete(obj);
	}
	
	protected void removeObjs(Class<T> c, List<Long> ids)
	{
		for(Long id : ids)
		{
			this.removeObj(c, id);
		}
	}
	
	protected void updateObj(T obj)
	{
		this.getHibernateTemplate().saveOrUpdate(obj);
	}
	
	protected void updateObjs(List<T> objs)
	{
		this.getHibernateTemplate().saveOrUpdateAll(objs);
	}
	
	
	protected T retrieveObj(Class<T> c, Long id)
	{
		return (T) this.getHibernateTemplate().get(c, id);
	}
	
	protected List<T> retrieveObjs(String queryString)
	{
		return (List<T>) this.getHibernateTemplate().find(queryString);
	}
	
	protected List<T> retrieveObjs(String queryString, String... value)
	{
		return (List<T>) this.getHibernateTemplate().find(queryString, value);
	}
	
	protected List<T> retrieveObjs(final String queryString,
			final String[] value, final int start, final int number)
	{
		return this.getHibernateTemplate().executeFind(new HibernateCallback()
		{
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException
			{
				Query query = session.createQuery(queryString);
	
				for (int i = 0; i < value.length; ++i)
				{
					query = query.setString(i, value[i]);
				}
	
				query.setFirstResult(start);
				query.setMaxResults(number);
	
				return (List<T>) query.list();
			}
		});
	}
	
	protected List<T> retrieveObjs(final String queryString,
			final String value, final int start, final int number)
	{
		String values[] = { value };
	
		return this.retrieveObjs(queryString, values, start, number);
	}
	
	protected T retrieveObj(String queryString, String... value)
	{
		List<T> objects = this.retrieveObjs(queryString, value);
	
		if (null != objects)
		{
			if (0 == objects.size())
			{
				return null;
			}
			else
			{
				return objects.get(0);
			}
		}
		else
		{
			return null;
		}
	}
	
	protected T retrieveObj(String queryString)
	{
		List list = this.getHibernateTemplate().find(queryString);
		if(list==null||list.isEmpty())
			{return null;}
		Object o = list.get(0);
		return (T)o;
	}
	
	
	protected void storeObj(T obj)
	{
		this.getHibernateTemplate().saveOrUpdate(obj);
	}
	
	protected void storeObjs(List<T> objs)
	{
		this.getHibernateTemplate().saveOrUpdateAll(objs);
	}
	
	protected long retrieveObjsCount(final String queryString,
			final String... value)
	{
		Object count = this.getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException
					{
						Query query = session.createQuery("select count(*) "
								+ queryString);
	
						for (int i = 0; i < value.length; ++i)
						{
							query = query.setString(i, value[i]);
						}
	
						return query.iterate().next();
					}
				});
	
		return (Long) count;
	}
	
	protected long retrieveObjsCount(String queryString)
	{
		String[] value = new String[0];
	
		return this.retrieveObjsCount(queryString, value);
	}
	
	/**清空表**/
	public void truncateTable(){
		if(null!=this.tableName&&!"".equals(this.tableName)){
			String logStr = "truncate   "+this.tableName ;
			try {
				String[]  sql ={"truncate  "+this.tableName};
				jdbcTemplate.batchUpdate(sql);
			} catch (RuntimeException re) {
				throw re;
			}
		}
	
	}

}
