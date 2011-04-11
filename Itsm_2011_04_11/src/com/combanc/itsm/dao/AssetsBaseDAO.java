package com.combanc.itsm.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.common.core.dao.HibernateSessionFactory;
import com.combanc.itsm.htmlobj.AssetsQurey;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.Location;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;

public class AssetsBaseDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(AssetsBaseDAO.class);
	// property constants
	private JdbcTemplate jdbcTemplate;

	public static final String MODEL = "model";
	public static final String TYPE_CODE = "type_Code";
	public static final String NAME = "name";
	public static final String PN = "pn";
	public static final String SN = "sn";
	public static final String DEPARTMENT_CODE = "departmentCode";
	public static final String PERSION_NAME = "persionName";
	public static final String PERSION_TEL = "persionTel";
	public static final String PERSION_MAIL = "persionMail";
	public static final String IP = "ip";
	public static final String QUALITY_TIME = "qualityTime";
	public static final String SYSTEM = "system";
	public static final String DES = "des";
	public static final String PRODUCE_ID = "produceId";
	public static final String STATE = "state";
	public static final String USER_ID = "userId";
	public static final String PID = "pid";

	protected void initDao() {
		// do nothing
	}
	
	
	public AssetsBase findByCodeId(String codeId){
		AssetsBase base=null;
		String sql="from AssetsBase as model where model.codeId='"+codeId+"'";
		List list=getHibernateTemplate().find(sql);
		if(list.size()!=0){
			base=(AssetsBase)list.get(0);
		}
		return base; 
	}
	
	public AssetsBase findByPn(String pn){
		AssetsBase base=null;
		String sql="from AssetsBase as model where model.pn='"+pn+"'";
		List list=getHibernateTemplate().find(sql);
		if(list.size()!=0){
			base=(AssetsBase)list.get(0);
		}
		return base; 
	}
	

	public void save(AssetsBase transientInstance) {
		log.debug("saving AssetsBase instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssetsBase persistentInstance) {
		log.debug("deleting AssetsBase instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void update(Location entity) {
		log.debug("updating Location instance");
		try {
			getHibernateTemplate().update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public void saveOrUpdate(AssetsBase assetsbase){
		log.debug("updating AssetsBase instance");
		try {
			getHibernateTemplate().saveOrUpdate(assetsbase);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public AssetsBase findById(int code) {
		log.debug("getting AssetsBase instance with id: " + code);
		try {
			AssetsBase instance = (AssetsBase) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.AssetsBase", code);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssetsBase instance) {
		log.debug("finding AssetsBase instance by example");
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
		log.debug("finding AssetsBase instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AssetsBase as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByLocation(Integer id) {
		log.debug("finding AssetsBase instance with property: Location  value: " + id);
		try {
			String queryString = "from AssetsBase as model where model.location="+id;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByDepartment(Integer id) {
		log.debug("finding AssetsBase instance with property: Department  value: " + id);
		try {
			String queryString = "from AssetsBase as model where model.department="+id;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByModel(Object model) {
		return findByProperty(MODEL, model);
	}

	public List findByTypeCode(Object typeCode) {
		return findByProperty(TYPE_CODE, typeCode);
	}
	
	
	public List findByTypeCodes(String code){
		try {
			String queryString = "from AssetsBase as model where model.assetsType.id='"+code+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByProducerid(String code){
		try {
			String queryString = "from AssetsBase as model where model.assetsProducerByProduceId.id='"+code+"'" +
			"or model.assetsProducerBySupportId.id='"+code+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByPn(Object pn) {
		return findByProperty(PN, pn);
	}

	public List findBySn(Object sn) {
		return findByProperty(SN, sn);
	}

	public List findByDepartmentCode(Object departmentCode) {
		return findByProperty(DEPARTMENT_CODE, departmentCode);
	}

	public List findByPersionName(Object persionName) {
		return findByProperty(PERSION_NAME, persionName);
	}

	public List findByPersionTel(Object persionTel) {
		return findByProperty(PERSION_TEL, persionTel);
	}

	public List findByPersionMail(Object persionMail) {
		return findByProperty(PERSION_MAIL, persionMail);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByQualityTime(Object qualityTime) {
		return findByProperty(QUALITY_TIME, qualityTime);
	}

	public List findBySystem(Object system) {
		return findByProperty(SYSTEM, system);
	}

	public List findByDes(Object des) {
		return findByProperty(DES, des);
	}

	public List findByProduceId(Object produceId) {
		return findByProperty(PRODUCE_ID, produceId);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByUserId(Object userId) {
        String queryString = "from AssetsBase as model where model.usersByUsersId="+userId+"or model.usersByChargeId="+userId;
		
		return getHibernateTemplate().find(queryString);
	}

	public List findByQuality() {
		log.debug("finding all AssetsBase instances");
		try {
			String queryString = "from AssetsBase";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all AssetsBase instances");
		try {
			String queryString = "from AssetsBase";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<AssetsBase> findAll(final String queryString,final int offset, final int length) {
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

	public AssetsBase merge(AssetsBase detachedInstance) {
		log.debug("merging AssetsBase instance");
		try {
			AssetsBase result = (AssetsBase) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssetsBase instance) {
		log.debug("attaching dirty AssetsBase instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssetsBase instance) {
		log.debug("attaching clean AssetsBase instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<AssetsBase> queryByHqls(AssetsBase assets){
		List<AssetsBase> records = new java.util.ArrayList<AssetsBase>();
		StringBuffer sb=new StringBuffer("from AssetsBase as model where model.des='0' ");
		if (assets.getName() != null && !assets.getName().equals("")) {
			sb.append(" and model.name like '%"+assets.getName()+"%' ");
		}
		if (assets.getCode() != null && !assets.getCode().equals("")) {
			sb.append(" and model.codeId like '"+assets.getCodeId()+"'");
		}
		if (assets.getAssetsProducerByProduceId().getId()>0) {
			sb.append(" and model.assetsProducerByProduceId.id='"+assets.getAssetsProducerByProduceId().getId()+"'");
		}
		if (assets.getAssetsProducerBySupportId().getId()> 0) {
			sb.append(" and model.assetsProducerBySupportId.id='"+assets.getAssetsProducerBySupportId().getId()+"'");
		}
		if (assets.getAssetsType().getId()!=null&&assets.getAssetsType().getId()>0){
			sb.append(" and model.assetsType.id='"+assets.getAssetsType().getId()+"'");
		}
		if (assets.getAssetsState().getId() > 0) {
			sb.append(" and model.assetsState.id='"+assets.getAssetsState().getId()+"' ");
		}
		if (assets.getLocation().getId()>0){
			sb.append(" and model.assets.location.id='"+assets.getLocation().getId()+"'");
		}
		if (assets.getDepartment().getId()!=null&&assets.getDepartment().getId()>0) {
			sb.append(" and model.assets.department.id='"+assets.getDepartment().getId()+"'");
		}
		sb.append(" order by assetsState.sequence");
		String sql=sb.toString();

		try {
			records = getHibernateTemplate().find(sql);
		} catch (Exception e) {
			log.debug(e);
		}
		return records;
	}
	
	
	public List<AssetsBase> queryByHql(AssetsQurey AssetsBase) {

		List<AssetsBase> records = new java.util.ArrayList<AssetsBase>();

		String sql = "from AssetsBase as model where 1=1 ";
		if (AssetsBase.getName() != null && !AssetsBase.getName().equals("")) {
			sql = sql + "  and  model.name like '%" + AssetsBase.getName()
					+ "%'";
		}
		if (AssetsBase.getCode() != null && !AssetsBase.getCode().equals("")) {
			sql = sql + "  and  model.code like '%" + AssetsBase.getCode()
					+ "%'";
		}
		if (AssetsBase.getPruducer() > 0) {

			sql = sql + " and  model.assetsProducerByProduceId='"
					+ AssetsBase.getPruducer() + "'";
		}
		if (AssetsBase.getSupport() > 0) {

			sql = sql + " and  model.assetsProducerBySupportId='"
					+ AssetsBase.getSupport() + "'";

		}
		if (AssetsBase.getType() > 0) {
			sql = sql + " and  model.assetsType='" + AssetsBase.getType() + "'";
		}
		if (AssetsBase.getState() > 0) {
			sql = sql + " and  model.state='" + AssetsBase.getState() + "'";
		}
		if (AssetsBase.getPlace() > 0) {
			sql = sql + " and  model.location='" + AssetsBase.getPlace() + "'";
		}
		if (AssetsBase.getDepartment() > 0) {
			sql = sql + " and  model.department='" + AssetsBase.getDepartment()
					+ "'";
		}
		if (AssetsBase.getEndDate() != null) {
			sql = sql + " and  model.inDate<='" + AssetsBase.getEndDate() + "'";
		}
		if (AssetsBase.getStartDate() != null) {
			sql = sql + " and  model.inDate>='" + AssetsBase.getStartDate()
					+ "'";
		}
		sql = sql + " order by model.state";

		try {
			// session = DaoMoudelApi.openSession();
			records = getHibernateTemplate().find(sql);

		} catch (Exception e) {
			log.debug(e);
		}

		return records;
	}

	public List<AssetsBase> queryStatistic(String queryString, int offset,
			int length) {
		log.debug("finding Assets instance with properties: ");
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Query query = getSession().createQuery(queryString);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find by properties name failed", re);
			throw re;
		}
	}
	
	public boolean sql(String sql) throws SQLException{
		boolean flag=true;
		log.debug("sql");
		try {
			jdbcTemplate.execute(sql);
			log.debug("sql successful");
		} catch (RuntimeException re) {
			flag=false;
			log.error(sql);
			log.error("sql failed", re);
			throw re;
		}
		return flag;
	}
	
	public List sqllist(String sql) throws SQLException{
		List list=null;
		log.debug("sql");
		try {
			list=jdbcTemplate.queryForList(sql);
			log.debug("sql successful");
		} catch (RuntimeException re) {
			log.error("sql failed", re);
			throw re;
		}
		return list;
	}

	public boolean batchInsert(List<AssetsBase> assetsBases) {
		log.debug("batchInsert Weektable");
		boolean issuccesssave=true;
		try{
			for(int i=0;i<assetsBases.size();i++){
				this.save((AssetsBase)assetsBases.get(i));
			}
		}catch(Exception e){
			e.printStackTrace();
			issuccesssave=false;
		}
		return issuccesssave;
	}

	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public Integer findSizeByAssetsBase(AssetsBase assetsBase) {
		int size = 0;
		String sql = "from AssetsBase as model where model.assetsState.id!='' and  model.des='0' and 1=1 ";
//		if (assetsBase.getName() != null && !assetsBase.getName().equals("")) {
//			sql = sql + "  and  model.name like '%" + assetsBase.getName()+ "%'";
//		}
//		if (assetsBase.getCode() != null && !assetsBase.getCode().equals("")) {
//			sql = sql + "  and  model.code like '%" + assetsBase.getCode()+ "%'";
//		}
		if (assetsBase.getAssetsProducerByProduceId()!=null) {
			if(assetsBase.getAssetsProducerByProduceId().getId()>0){
				sql = sql + " and  model.assetsProducerByProduceId.id='"+ assetsBase.getAssetsProducerByProduceId().getId() + "'";
			}
		}
		if (assetsBase.getAssetsProducerBySupportId()!=null) {
			if(assetsBase.getAssetsProducerBySupportId().getId()>0){
				sql = sql + " and  model.assetsProducerBySupportId.id='"+ assetsBase.getAssetsProducerBySupportId().getId()+ "'";
			}
		}
		if (assetsBase.getAssetsType()!=null) {
			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
				sql = sql + " and  model.assetsType.id='" + assetsBase.getAssetsType().getId() + "'";
			}
		}
		if (assetsBase.getAssetsState()!=null) {
			if(assetsBase.getAssetsState().getId()>0){
				sql = sql + " and  model.assetsState.id='" + assetsBase.getAssetsState().getId() + "'";
			}
		}
		if (assetsBase.getLocation()!=null) {
			if(assetsBase.getLocation().getId()>0){
				sql = sql + " and  model.location.id='" +assetsBase.getLocation().getId()+ "'";
			}
		}
		
		if(assetsBase.getUsersByChargeId()!=null){
			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
				sql=sql+" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"'";
			}
		}
		if (assetsBase.getDepartment()!=null) {
			if(assetsBase.getDepartment().getId()!=null&&assetsBase.getDepartment().getId()>0){
				sql = sql + " and  model.department.id='" +assetsBase.getDepartment().getId()+ "'";
			}
		}
//		if (assetsBase.getEndDate() != null) {
//			sql = sql + " and  model.inDate<='" + assetsBase.getEndDate() + "'";
//		}
//		if (assetsBase.getStartDate() != null) {
//			sql = sql + " and  model.inDate>='" + assetsBase.getStartDate()+ "'";
//		}
		try {
			size = getHibernateTemplate().find(sql).size();
		} catch (Exception e) {
			log.debug(e);
		}
		return size;
	}

	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size();
	}

	public List<AssetsBase> getAllRow(String hql) {
		return getHibernateTemplate().find(hql);
	}
	
	public List getAllRows(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public static AssetsBaseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AssetsBaseDAO) ctx.getBean("AssetsBaseDAO");
	}

	public com.combanc.common.core.PageBean findAllByPage(int pageSize,
			int page, String hql) {
		int allRow = getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (page > totalPage) {
			page = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, page);
		final int length = pageSize;
		final int currentPage = PageBean.countCurrentPage(page);
		List<AssetsBase> list = queryForPage(hql, offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}

	public List<AssetsBase> queryByPageSQL(final String sql, final int offset,
			final int length) {

		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				query.setFirstResult(offset);
				query.setMaxResults(length);
				List list = query.list();
				return list;
			}
		});
		return list;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List computer() {
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql="call computer()";
				Query query = session.createSQLQuery(sql);
				List list = query.list();
				return list;
			}
		});
		return list;
	}
	
    public List device() {
		
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql="call device()";
				Query query = session.createSQLQuery(sql);
				List list = query.list();
				return list;
			}
		});
		return list;
	}
    
}