package com.combanc.monitor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.springframework.jdbc.core.RowMapper;

import com.combanc.common.core.dao.BaseHibernateDAO;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorTopologyEdge;

public class MonitorTopologyEdgeDAO extends BaseHibernateDAO<MonitorTopologyEdge, Integer>{
	private static final Log log = LogFactory.getLog(MonitorTopologyEdgeDAO.class);
	
	//property constants
	public static final String IP = "ip";
	public static final String INTERFACE_ = "interface_";
	public static final String DOWNLINK_IP = "downlinkIp";
	public static final String DOWNLINK_INTERFACE = "downlinkInterface";
	public static final String SEND_BYTE = "sendByte";
	public static final String RECEIVE_BYTE = "receiveByte";
	public static final String SEND_UNICAST_PACKET = "sendUnicastPacket";
	public static final String RECEIVE_UNICAST_PACKET = "receiveUnicastPacket";
	public static final String SEND_BROADCAST_PACKET = "sendBroadcastPacket";
	public static final String RECEIVE_BROADCAST_PACKET = "receiveBroadcastPacket";
	public static final String SEND_WRONG_PACKET = "sendWrongPacket";
	public static final String RECEIVE_WRONG_PACKET = "receiveWrongPacket";
	public static final String SEND_LOST_PACKET = "sendLostPacket";
	public static final String RECEIVE_LOST_PACKET = "receiveLostPacket";
	public static final String INTERFACE_RATE = "interfaceRate";
	public static final String BILATERAL_PEAK = "bilateralPeak";
	public static final String SEND_PEAK = "sendPeak";
	public static final String RECEIVE_PEAK = "receivePeak";
	
	protected void initDao() {
		//do nothing
		this.tableName="monitor_topology_edge";
	}
	
	public List<MonitorTopologyEdge> findByCriteria(DetachedCriteria criteria) {  
		log.debug("finding MonitorTopologyEdge instance by criteria");  
		try {  
			List<MonitorTopologyEdge> results = getHibernateTemplate().findByCriteria(criteria);  
			log.debug("find by criteria successful, result size: " + results.size());  
			return results;  
			} catch (RuntimeException re) {  
				log.error("find by criteria failed", re);  
				throw re;  
			}  
	}  
	 
	/**
	 * 返回拓扑图中互连接口的数据信息
	 * @param ip 上连IP
	 * @param interface_ 上连接口
	 * @param downlinkIp 下连IP
	 * @param downlinkInterface 下连接口
	 * @return
	 */
	public MonitorTopologyEdge findByCondition(String ip,String interface_,String downlinkIp,String downlinkInterface){
		 
		 String condition = "";
		 if(null != ip && !"".equals(ip))
			 condition += " and ip ='" + ip +"'";
		 if(null != ip && !"".equals(ip))
			 condition += " and interface_ ='" + interface_ +"'";
		 if(null != ip && !"".equals(ip))
			 condition += " and downlinkIp ='" + downlinkIp +"'";
		 if(null != ip && !"".equals(ip))
			 condition += " and downlinkInterface ='" + downlinkInterface +"'";
		 
		 String hql = " from MonitorTopologyEdge where 1=1 " + condition;
		 List<MonitorTopologyEdge> result = this.findByHql(hql);
		 if(null != result && result.size()>0)
			 return  result.get(0);
		 else
			 return null;
	}
	
	

	 
}
