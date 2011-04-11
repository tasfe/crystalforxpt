package com.combanc.monitor.util;

import java.util.List;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojoext.ArpEntry;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.IpMacEntry;
import com.combanc.monitor.pojoext.MacEntry;
import com.combanc.monitor.pojoext.MonitorLinkportExt;
import com.combanc.monitor.pojoext.PortIfxMap;

/**
 * @author Administrator
 *
 */
public class JosqlUtil {
	/**
	 * 根据IP地址获取MonitorDevice
	 * @param monitorDeviceList	设备list
	 * @param ip	设备ip
	 * @return	返回ip对应的对象MonitorDevice
	 */
	public static MonitorDevice findDeviceByIp(List<MonitorDevice> monitorDeviceList, String ip) {
		if(monitorDeviceList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorDevice where ip = :ip";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		try {
			qr = q.execute(monitorDeviceList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorDevice> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0);
		}
		return null;
	}
	
	/**
	 * 检查互连端口list中是否有subnetId和ip的记录
	 * @param monitorLinkportList 互连端口表list
	 * @param subnetId	子网id
	 * @param ip	设备ip
	 * @return	含有返回true
	 */
	public static boolean containSubnetIdDeviceIp(List<MonitorLinkport> monitorLinkportList, Integer subnetId, String ip) {
		if(monitorLinkportList == null)
			return true;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorLinkport where MonitorSubnet.id = :subnetId and ip = :ip";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("subnetId", subnetId);
		q.setVariable("ip", ip);
		try {
			qr = q.execute(monitorLinkportList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorLinkport> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}
	
	// 检查List<ArpEntry>是否已含有此IP
	/**
	 * 检查List<ArpEntry>是否已含有此IP
	 * @param arpEntryList	arp项list
	 * @param ip	ip地址
	 * @return	含有则返回true
	 */
	public static ArpEntry containDeviceIp(List<ArpEntry> arpEntryList, String ip) {
		if(arpEntryList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.ArpEntry where ip = :ip";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		try {
			qr = q.execute(arpEntryList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<ArpEntry> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0);
		}
		return null;
	}

	/**
	 * 查找接口Cache List是否含有此接口，获取接口对应的接口描述
	 * @param monitorInterfaceCacheList	接口Cache List
	 * @param ifx 	接口
	 * @return	对应的接口描述
	 */
	public static String containInterfaceCache(
			List<MonitorInterfaceCache> monitorInterfaceCacheList, String ifx) {
		if (monitorInterfaceCacheList == null)
			return "";
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorInterfaceCache where interface_ = :ifx";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ifx", ifx);
		try {
			qr = q.execute(monitorInterfaceCacheList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorInterfaceCache> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0).getDescription();
		}
		return "";
		
	}

	/*
	 * 已使用重写MacEntry的contain 和 hasCode方法，来判断包含
	public static boolean containMacEntry(MacEntry me,
			List<MacEntry> devMacEntryList) {
		if (devMacEntryList == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.MacEntry where mac = :mac and port =:port and uplinkDevice =:device";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("mac", me.getMac());
		q.setVariable("port", me.getPort());
		q.setVariable("device", me.getUplinkDevice());
		try {
			qr = q.execute(devMacEntryList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MacEntry> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}
	*/
	
	/**
	 * 根据mac地址从devMacEntryList里面查找对应的MacEntry
	 * @param macEntryList	mac转发表对应的list
	 * @param mac	mac地址
	 * @return	List<MacEntry> 查找到的所有符合条件的MacEntry的list，找不到返回null
	 */
	public static List<MacEntry> findMacEntry(List<MacEntry> macEntryList, String mac) {
		if (macEntryList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.MacEntry where mac = :mac";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("mac", mac);
		try {
			qr = q.execute(macEntryList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MacEntry> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList;
		}
		return null;
	}

	/**
	 * 从端口接口映射cache表里获取设备device对应的端口接口映射表
	 * @param portIfxCacheList	端口接口映射表cache
	 * @param device	需要查找端口接口映射的设备ip
	 * @return	设备对应的端口接口映射表
	 */
	public static List<PortIfxMap> containIp(List<PortIfxMap> portIfxCacheList, String device) {
		if (portIfxCacheList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.PortIfxMap where ip = :ip";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", device);
		try {
			qr = q.execute(portIfxCacheList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<PortIfxMap> retList = qr.getResults();
//		if(retList != null && retList.size() > 0) {
//			return true;
//		}
//		return false;
		return retList;
	}

	/**
	 * 在端口接口映射表中根据端口查找对应的接口
	 * @param portIfxMapList	端口接口映射表
	 * @param port	端口
	 * @return	返回对应的接口
	 */
	public static String findIfxByPort(List<PortIfxMap> portIfxMapList,
			String port) {
		if (portIfxMapList == null)
			return "";
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.PortIfxMap where port =:port";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("port", port);
		try {
			qr = q.execute(portIfxMapList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<PortIfxMap> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0).getInterface_();
		}
		return "";
	}

	/**
	 * 判断linkportList里面是否含有此ip、接口ifx的项
	 * @param linkportList
	 * @param uplinkDevice
	 * @param ifx
	 * @return
	 */
	public static boolean containIpIfx(List<MonitorLinkport> linkportList, String ip, String ifx) {
		if (linkportList == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorLinkport where ip = :ip and interface_ =:ifx";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		q.setVariable("ifx", ifx);
		try {
			qr = q.execute(linkportList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorLinkport> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断交换机、路由器、网管机的mac地址List中是否已经含有ime，没含有则添加
	 * @param ipMacEntryList	交换机、路由器、网管机的mac地址List
	 * @param ime 	交换机、路由器、网管机的mac地址
	 * @return	含有返回true
	 */
	public static boolean containIpMac(List<IpMacEntry> ipMacEntryList,
			IpMacEntry ime) {
		if (ipMacEntryList == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.IpMacEntry where ip = :ip and mac =:mac";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ime.getIp());
		q.setVariable("mac", ime.getMac());
		try {
			qr = q.execute(ipMacEntryList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<IpMacEntry> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断交换机、路由器、网管机的mac地址List中是否已经含有mac
	 * @param ipMacEntryList	交换机、路由器、网管机的mac地址List
	 * @param mac	mac地址
	 * @return
	 */
	public static IpMacEntry containMac(List<IpMacEntry> ipMacEntryList, String mac) {
		if (ipMacEntryList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.IpMacEntry where mac =:mac";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("mac", mac);
		try {
			qr = q.execute(ipMacEntryList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<IpMacEntry> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0);
		}
		return null;
	}

	// 2010-09-10 此处list并非泛型List，用Josql查询是否有问题？
	public static boolean conatinPortIfx(List list, String port,
			String ifx) {
		if (list == null)
			return false;
//		List<InterfaceEntry> ieList = new ArrayList<InterfaceEntry>();
//		for(Object o : list) {
//			ieList.add((InterfaceEntry)o);
//		}
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.InterfaceEntry where port =:port and interface_=:ifx";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("port", port);
		q.setVariable("ifx", ifx);
		try {
			qr = q.execute(list);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		// List<IpMacEntry> retList = qr.getResults();
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断linkport扩展list里面是否含有ip、port对应的记录项
	 * @param linkportList1	MonitorLinkportExt的list
	 * @param ip	ip地址
	 * @param port	端口
	 * @return	含有这返回true
	 */
	public static boolean conatinIpPort(List<MonitorLinkportExt> linkportList1,
			String ip, String port) {
		if (linkportList1 == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.MonitorLinkportExt where ip =:ip and port=:port";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		q.setVariable("port", port);
		try {
			qr = q.execute(linkportList1);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 在互连端口表的中间表2中查找是否含有ip，port，下连ip的记录
	 * @param linkportList2	MonitorLinkportExt的list
	 * @param ip
	 * @param port
	 * @param downlinkIp
	 * @return	含有返回true
	 */
	public static boolean conatinIpPortDownlink(
			List<MonitorLinkportExt> linkportList2, String ip, String port,
			String downlinkIp) {
		if (linkportList2 == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.MonitorLinkportExt where ip =:ip and port=:port and downlinkIp=:downlinkIp";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		q.setVariable("port", port);
		q.setVariable("downlinkIp", downlinkIp);
		try {
			qr = q.execute(linkportList2);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 在互连端口表的中间表2中查找是否含有ip，下连ip的记录
	 * @param linkportList2	MonitorLinkportExt的list
	 * @param ip
	 * @param port
	 * @param downlinkIp
	 * @return	含有返回true
	 */
	public static MonitorLinkportExt conatinIpDownlinkExt(
			List<MonitorLinkportExt> linkportList2, String ip, String downlinkIp) {
		if (linkportList2 == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.MonitorLinkportExt where ip =:ip and downlinkIp=:downlinkIp";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		q.setVariable("downlinkIp", downlinkIp);
		try {
			qr = q.execute(linkportList2);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return (MonitorLinkportExt) retList.get(0);
		}
		return null;
	}

	/**
	 * L3拓扑发现算法调用，在子网接口list里面查找是否含有此ip，不含有则加入list
	 * @param subInterfaceList	子网接口list
	 * @param ip	设备ip
	 * @return	如果含有返回InterfaceEntry对象，否则返回null
	 */
	public static InterfaceEntry containIpSubInterface(
			List<InterfaceEntry> subInterfaceList, String ip) {
		if (subInterfaceList == null)
			return null;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojoext.InterfaceEntry where ip = :ip";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		try {
			qr = q.execute(subInterfaceList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<InterfaceEntry> retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return retList.get(0);
		}
		return null;
	}

	/**
	 * 互连端口表里是否含有此ip、接口的记录
	 * @param linkportList	互连端口list
	 * @param ip
	 * @param ifx
	 * @return
	 */
	public static boolean conatinIpInterface(
			List<MonitorLinkport> linkportList, String ip, String ifx) {
		if (linkportList == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorLinkport where ip =:ip and interface_=:ifx";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		q.setVariable("ifx", ifx);
		try {
			qr = q.execute(linkportList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 用于检查互连端口表中是否有未发现连接关系的设备
	 * @param linkportList	互连端口list
	 * @param ip	设备ip
	 * @return	含有则返回true
	 */
	public static boolean containIpLinkport(List<MonitorLinkport> linkportList,
			String ip) {
		if (linkportList == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorLinkport where ip =:ip";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		try {
			qr = q.execute(linkportList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	
	/**
	 * 三层拓扑发现算法调用，查找中间临时互连端口表里ip 、 下联ip是否存在
	 * @param linkportList3	互连端口中间表
	 * @param ip
	 * @param downlinkIp
	 * @return
	 */
	public static boolean conatinIpDownlink(
			List<MonitorLinkport> linkportList3, String ip, String downlinkIp) {
		if (linkportList3 == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorLinkport where ip =:ip and downlinkIp=:downlinkIp";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);
		q.setVariable("downlinkIp", downlinkIp);
		try {
			qr = q.execute(linkportList3);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断计算机快照表中是否已有此mac的记录，防止重复加入
	 * @param snapshotList	计算机快照表
	 * @param mac			计算机mac	
	 * @return				包含则返回true
	 */
	public static boolean containIpSnapshot(List<MonitorComputer> snapshotList,
			String mac) {
		// TODO Auto-generated method stub
		if (snapshotList == null)
			return false;
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorComputer where mac =:mac";
		QueryResults qr = null;
		
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("mac", mac);
		try {
			qr = q.execute(snapshotList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List retList = qr.getResults();
		if(retList != null && retList.size() > 0) {
			return true;
		}
		return false;
	}
}
