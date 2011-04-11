package com.combanc.monitor.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSubnet;

/**
 * A data access object (DAO) providing persistence and search support for
 * MonitorLinkport entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.combanc.monitor.pojo.MonitorLinkport
 * @author MyEclipse Persistence Tools
 */

public class MonitorLinkportDAO extends
		BaseHibernateDAO<MonitorLinkport, Integer> {
	private static final Log log = LogFactory.getLog(MonitorLinkportDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String PORT = "port";
	public static final String INTERFACE_ = "interface_";
	public static final String DESCRIPTION = "description";
	public static final String DOWNLINK_IP = "downlinkIp";
	public static final String DOWNLINK_PORT = "downlinkPort";
	public static final String DOWNLINK_INTERFACE = "downlinkInterface";
	public static final String DOWNLINK_DESC = "downlinkDesc";
	public static final String INODE = "inode";
	public static final String EDGE = "edge";
	public static final String NOTE1 = "note1";
	public static final String NOTE2 = "note2";
	public static final String NOTE3 = "note3";
	public static final String NOTE4 = "note4";

	protected void initDao() {
		// do nothing
	}

	public void save(MonitorLinkport transientInstance) {
		log.debug("saving MonitorLinkport instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MonitorLinkport persistentInstance) {
		log.debug("deleting MonitorLinkport instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MonitorLinkport findById(java.lang.Integer id) {
		log.debug("getting MonitorLinkport instance with id: " + id);
		try {
			MonitorLinkport instance = (MonitorLinkport) getHibernateTemplate()
					.get("com.combanc.monitor.pojo.MonitorLinkport", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(MonitorLinkport instance) {
		log.debug("finding MonitorLinkport instance by example");
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
		log.debug("finding MonitorLinkport instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MonitorLinkport as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findBySubnetId(Object subnetId) {
		return findByProperty("monitorSubnet.id", subnetId);
	}
	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List findByInterface_(Object interface_) {
		return findByProperty(INTERFACE_, interface_);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByDownlinkIp(Object downlinkIp) {
		return findByProperty(DOWNLINK_IP, downlinkIp);
	}

	public List findByDownlinkPort(Object downlinkPort) {
		return findByProperty(DOWNLINK_PORT, downlinkPort);
	}

	public List findByDownlinkInterface(Object downlinkInterface) {
		return findByProperty(DOWNLINK_INTERFACE, downlinkInterface);
	}

	public List findByDownlinkDesc(Object downlinkDesc) {
		return findByProperty(DOWNLINK_DESC, downlinkDesc);
	}

	public List findByInode(Object inode) {
		return findByProperty(INODE, inode);
	}

	public List findByEdge(Object edge) {
		return findByProperty(EDGE, edge);
	}

	public List findByNote1(Object note1) {
		return findByProperty(NOTE1, note1);
	}

	public List findByNote2(Object note2) {
		return findByProperty(NOTE2, note2);
	}

	public List findByNote3(Object note3) {
		return findByProperty(NOTE3, note3);
	}

	public List findByNote4(Object note4) {
		return findByProperty(NOTE4, note4);
	}

	public List findAll() {
		log.debug("finding all MonitorLinkport instances");
		try {
			String queryString = "from MonitorLinkport";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 查找所有的全连接的互联端口列表（有下联IP）、去掉重复记录
	 * @return
	 */
	public List findAllUniqueWithDownLink() {
		log.debug("finding all MonitorLinkport instances");
		try {
			List<MonitorLinkport> list=new ArrayList<MonitorLinkport>();
			String queryString = "select distinct ip,interface_,downlinkIp,downlinkInterface from MonitorLinkport where downlinkIp is not null and downlinkIp<>''";
			List result= getHibernateTemplate().find(queryString);
			Iterator it=result.iterator();
			while(it.hasNext()){
				Object[] temp = (Object[]) it.next(); 
				MonitorLinkport model=new MonitorLinkport();
				model.setIp(temp[0].toString());
				model.setInterface_(temp[1].toString());
				model.setDownlinkIp(temp[2].toString());
				model.setDownlinkInterface(temp[3].toString());
				list.add(model);
			}
			return list;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	/**
	 * 查找所有的全连接的互联端口列表（有下联IP）、去掉重复记录
	 * @return
	 */
	public List findAllUniqueWithDownLink(List<Integer> subnetList) {
		log.debug("finding all MonitorLinkport instances");
		try {
			List<MonitorLinkport> list=new ArrayList<MonitorLinkport>();
			String queryString = "select distinct ip,interface_,downlinkIp,downlinkInterface from MonitorLinkport where downlinkIp is not null and downlinkIp<>''";
			StringBuffer condition =   new StringBuffer(" and ( 1=2 ");
			for(Integer subnetId : subnetList){
				condition.append(" or monitorSubnet.id=" + subnetId);
			}
			condition.append(")");
			queryString += condition.toString();
			
			List result= getHibernateTemplate().find(queryString);
			Iterator it=result.iterator();
			while(it.hasNext()){
				Object[] temp = (Object[]) it.next(); 
				MonitorLinkport model=new MonitorLinkport();
				model.setIp(temp[0].toString());
				model.setInterface_(temp[1].toString());
				model.setDownlinkIp(temp[2].toString());
				model.setDownlinkInterface(temp[3].toString());
				list.add(model);
			}
			return list;
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MonitorLinkport merge(MonitorLinkport detachedInstance) {
		log.debug("merging MonitorLinkport instance");
		try {
			MonitorLinkport result = (MonitorLinkport) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MonitorLinkport instance) {
		log.debug("attaching dirty MonitorLinkport instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MonitorLinkport instance) {
		log.debug("attaching clean MonitorLinkport instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorLinkportDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MonitorLinkportDAO) ctx.getBean("MonitorLinkportDAO");
	}

	public List appendNote() {
		/*
		String hql = "SELECT lp, di.cacheNoteCn FROM Linkport as lp, DeviceInterface di " +
				"WHERE lp.subnet.subnetId = di.subnet.subnetId AND lp.linkIp = di.cacheIp AND lp.linkInterface = di.cacheInterface";
		String downHql = "SELECT lp, di.cacheNoteCn FROM Linkport as lp, DeviceInterface di " +
		"WHERE lp.subnet.subnetId = di.subnet.subnetId AND lp.downLinkIp = di.cacheIp AND lp.downLinkInterface = di.cacheInterface";
		try {
			List result = null;       
			Iterator it = getHibernateTemplate().find(hql).iterator();  
			Iterator downIt = getHibernateTemplate().find(downHql).iterator();
			while (it.hasNext()) {
			   Object[] temp = (Object[]) it.next();       
			   MonitorLinkport monitorLinkport = (MonitorLinkport) temp[0];
			   Vector row = new Vector();
			   //row.add(linkport.get)
			   String cacheNoteCn = (String) temp[1];
			   //System.out.println("cacheNoteCn    " + cacheNoteCn);
			}   
			return result;
		} catch (RuntimeException e) {
			throw e;
		}
		*/
		return null;
	}

	public void deleteBySubnetId(Integer subnetId){
		log.debug("deleting linkport where subnetId = " + subnetId);
		try {
			String[] sql = new String[1];
			if (subnetId == null)
				sql[0] = "DELETE FROM monitor_linkport";
			else
				sql[0] = "DELETE FROM monitor_linkport WHERE subnet_id = '" + subnetId + "'";
			jdbcTemplate.batchUpdate(sql);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	/**
	 * 删除互连接口表里与设备deviceIp有关的记录
	 * @param deviceIp 
	 */
	public void deleteByDeviceIp(String deviceIp){
		 
		log.debug("deleting linkport where deviceIp = " + deviceIp);
		try {
			String sql= "DELETE FROM monitor_linkport WHERE IP = '" + deviceIp + "' or DOWNLINK_IP='" + deviceIp + "'";
			jdbcTemplate.execute(sql);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public int[] insertLinkPort(final List<MonitorLinkport> linkportList) {
		log.debug("inserting linkport ...");
		try {
			String sql = "INSERT INTO monitor_linkport (ip,subnet_id,port,interface,description,downLink_ip,downLink_port,downLink_interface,downLink_desc,inode,edge) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			        public void setValues(PreparedStatement ps, int i) throws SQLException {
			        	MonitorLinkport ml = linkportList.get(i);
			            ps.setString(1, ml.getIp());
			            ps.setInt(2, ml.getMonitorSubnet().getId());
			            ps.setString(3, ml.getPort());
			            ps.setString(4, ml.getInterface_());
			            ps.setString(5, ml.getDescription());
			            ps.setString(6, ml.getDownlinkIp());
			            ps.setString(7, ml.getDownlinkPort());
			            ps.setString(8, ml.getDownlinkInterface());
			            ps.setString(9, ml.getDownlinkDesc());
			            ps.setInt(10, null == ml.getInode()? 0 :ml.getInode());
			            ps.setInt(11, null == ml.getEdge()? 0 :ml.getEdge());
			        }
			        public int getBatchSize() {
			            return linkportList.size();
			        }
			      };
			      log.debug("insert successful");
			return jdbcTemplate.batchUpdate(sql, setter);
		} catch (RuntimeException re) {
			log.error("insert failed", re);
			throw re;
		}
	}
	
	public int[] insertLinkPort(final int subnetId,final List<MonitorLinkport> linkportList) {
		log.debug("inserting linkport ...");
		try {
			String sql = "INSERT INTO monitor_linkport (ip,subnet_id,port,interface,description,downLink_ip,downLink_port,downLink_interface,downLink_desc,inode,edge) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			        public void setValues(PreparedStatement ps, int i) throws SQLException {
			        	MonitorLinkport ml = linkportList.get(i);
			            ps.setString(1, ml.getIp());
			            ps.setInt(2, subnetId);
			            ps.setString(3, ml.getPort());
			            ps.setString(4, ml.getInterface_());
			            ps.setString(5, ml.getDescription());
			            ps.setString(6, ml.getDownlinkIp());
			            ps.setString(7, ml.getDownlinkPort());
			            ps.setString(8, ml.getDownlinkInterface());
			            ps.setString(9, ml.getDownlinkDesc());
			            ps.setInt(10, null == ml.getInode()? 0 :ml.getInode());
			            ps.setInt(11, null == ml.getEdge()? 0 :ml.getEdge());
			        }
			        public int getBatchSize() {
			            return linkportList.size();
			        }
			      };
			      log.debug("insert successful");
			return jdbcTemplate.batchUpdate(sql, setter);
		} catch (RuntimeException re) {
			log.error("insert failed", re);
			throw re;
		}
	}
	/**
	 * 返回去重的互联端口表
	 * @return
	 */
	public List findUniqueLinkport(Integer subnetId){
		log.debug("inserting linkport ...");
		try {
			String condition = "";
			if(null != subnetId){
				condition = " and SUBNET_ID = " + subnetId;
			}
			String sql="select distinct IP,INTERFACE,DESCRIPTION,DOWNLINK_IP,DOWNLINK_INTERFACE ,DOWNLINK_DESC from monitor_linkport m where DOWNLINK_IP is not null and DOWNLINK_IP<>''"+condition;
			return jdbcTemplate.queryForList(sql);
		} catch (RuntimeException re) {
			log.error("insert failed", re);
			throw re;
			}
	}
	
	 
	
	public int[] batchDelete(final List<MonitorLinkport> linkportList) {
		log.debug("delete linkportList");
		
		String sql = "delete from monitor_linkport where ID=? ";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setInt(1,linkportList.get(i).getId());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return linkportList.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql,setter);
	}
	
	public int[] batchDelete(final MonitorSubnet subnet,final List<MonitorDevice> monitorDeviceList) {
		log.debug("delete linkportList");
		
		String sql = "delete from monitor_linkport where SUBNET_ID=? and (IP=? or DOWNLINK_IP=?)";
		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setInt(1,subnet.getId());
				ps.setString(2,monitorDeviceList.get(i).getIp());
				ps.setString(3,monitorDeviceList.get(i).getIp());
			}
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return monitorDeviceList.size();
			}
		};
		return jdbcTemplate.batchUpdate(sql,setter);
	}
	
	
}