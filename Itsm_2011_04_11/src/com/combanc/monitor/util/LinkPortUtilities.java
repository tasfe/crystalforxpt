package com.combanc.monitor.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.TableDataSet;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.service.MonitorInterfaceCacheService;

public class LinkPortUtilities {
	// 给一个TableDataSet添加LinkPortTable的字段
	public static boolean initLinkPortTable(TableDataSet resultDataSet) {
		// 若为null，则创建一个
		if (resultDataSet == null)
			return false;
		// 字段名列表
		String[] columnName = new String[] { "IP", "端口", "接口", "接口描述", "下连IP",
				"下连端口", "下连接口", "下连接口描述", "子网名", "子网ID", "节点索引", "边索引" };
		// 创建字段对象列表
		Column[] columns = new Column[12];
		// 设定字段类型
		for (int i = 0; i < 12; i++) {
			columns[i] = new Column();
			columns[i].setColumnName(columnName[i]);
			if (i < 9)
				columns[i].setDataType(com.borland.dx.dataset.Variant.STRING);
			else
				columns[i].setDataType(com.borland.dx.dataset.Variant.INT);
		}
		// 给TableDataSet添加列
		resultDataSet.setColumns(columns);
		return true;
	}

	public static boolean listToTable(List<MonitorLinkport> linkPortList,
			TableDataSet resultDataSet) {
		// 如果TableDataSet为空，则重新创建一个
		if (resultDataSet == null) {
			System.out.println("resultDataSet == null");
			return false;
		}
		if (linkPortList != null) {
			DataRow row = new DataRow(resultDataSet);
			// 打开TableDataSet
			if (!resultDataSet.isOpen())
				resultDataSet.open();
			// 遍历List
			for (Iterator<MonitorLinkport> it = linkPortList.iterator(); it.hasNext();) {
				MonitorLinkport ml = it.next();
				row.setString("IP", ml.getIp());
				row.setString("端口", ml.getPort());
				row.setString("接口", ml.getInterface_());
				row.setString("接口描述", ml.getDescription());
				row.setString("下连IP", ml.getDownlinkIp());
				row.setString("下连端口", ml.getDownlinkPort());
				row.setString("下连接口", ml.getDownlinkInterface());
				row.setString("下连接口描述", ml.getDownlinkDesc());
				row.setString("子网名", ml.getMonitorSubnet().getName());
				row.setInt("子网ID", ml.getMonitorSubnet().getId());
				row.setInt("节点索引", ml.getInode());
				row.setInt("边索引", ml.getEdge());
				resultDataSet.addRow(row);
			}
			// 关闭TableDataSet
			resultDataSet.close();
		}
		return true;
	}

	public static boolean listToTableNote(List<MonitorLinkport> linkPortList,
			TableDataSet resultDataSet, Integer subnetId, MonitorInterfaceCacheService monitorInterfaceCacheService) {
		// 如果TableDataSet为空，则重新创建一个
		if (resultDataSet == null) {
			System.out.println("resultDataSet == null");
			return false;
		}
		if (linkPortList != null) {
			//
			List<MonitorInterfaceCache> monitorInterfaceCacheList  = monitorInterfaceCacheService.findBySubnet(subnetId);

			Query q = new Query();
			String sql = "select * from netmon.hibernate.po.MonitorInterfaceCache where cacheIp = :ip and cacheInterface = :ifx";
			try {
				q.parse(sql);
			} catch (QueryParseException e) {
				e.printStackTrace();
			}
			QueryResults qr = null;
			List<MonitorInterfaceCache> tmpList = null;
			
			
			// 打开TableDataSet
			if (!resultDataSet.isOpen())
				resultDataSet.open();
			// 遍历List
			for (Iterator<MonitorLinkport> it = linkPortList.iterator(); it.hasNext();) {
				DataRow row = new DataRow(resultDataSet);
				MonitorLinkport ml = it.next();
				
				q.setVariable("ip", ml.getIp());
				q.setVariable("ifx", ml.getInterface_());
				try {
					qr = q.execute(monitorInterfaceCacheList);
				} catch (QueryExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmpList = qr.getResults();
				if(!tmpList.isEmpty()){
					row.setString("接口备注", tmpList.get(0).getNote());
					qr = null;
					tmpList = null;
				}
				
				q.setVariable("ip", ml.getDownlinkIp());
				q.setVariable("ifx", ml.getDownlinkInterface());
				try {
					qr = q.execute(monitorInterfaceCacheList);
				} catch (QueryExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmpList = qr.getResults();
				if(!tmpList.isEmpty()){
					row.setString("下连接口备注", tmpList.get(0).getNote());
					qr = null;
					tmpList = null;
				}
				
				row.setString("IP", ml.getIp());
				row.setString("端口", ml.getPort());
				row.setString("接口", ml.getInterface_());
				row.setString("接口描述", ml.getDescription());
				row.setString("下连IP", ml.getDownlinkIp());
				row.setString("下连端口", ml.getDownlinkPort());
				row.setString("下连接口", ml.getDownlinkInterface());
				row.setString("下连接口描述", ml.getDownlinkDesc());
				row.setString("子网名", ml.getMonitorSubnet().getName());
				row.setInt("子网ID", ml.getMonitorSubnet().getId());
				row.setInt("节点索引", ml.getInode());
				row.setInt("边索引", ml.getEdge());
				resultDataSet.addRow(row);
			}
			// 关闭TableDataSet
			resultDataSet.close();
		}
		return true;
	}

	public static List<MonitorLinkport> tableToList(TableDataSet linkPortTable) {
		// 创建一个连接端口表的List
		List<MonitorLinkport> resultList = new ArrayList<MonitorLinkport>();
		if (linkPortTable != null) {
			// 打开TableDataSet
			if (!linkPortTable.isOpen())
				linkPortTable.open();
			// 遍历TableDataSet
			for (int i = 0; i < linkPortTable.rowCount(); i++) {
				linkPortTable.goToRow(i);
				MonitorLinkport ml = new MonitorLinkport();
				ml.setIp(linkPortTable.getString("IP"));
				ml.setPort(linkPortTable.getString("端口"));
				ml.setInterface_(linkPortTable.getString("接口"));
				ml.setDescription(linkPortTable.getString("接口描述"));
				ml.setDownlinkIp(linkPortTable.getString("下连IP"));
				ml.setDownlinkPort(linkPortTable.getString("下连端口"));
				ml.setDownlinkInterface(linkPortTable.getString("下连接口"));
				ml.setDescription(linkPortTable.getString("下连接口描述"));
				ml.setInode(linkPortTable.getInt("节点索引"));
				ml.setEdge(linkPortTable.getInt("边索引"));
				ml.setMonitorSubnet(new MonitorSubnet(linkPortTable.getInt("子网ID")));
				resultList.add(ml);
			}
			linkPortTable.close();
		}
		return resultList;
	}
	
	public static boolean distinctForLplist(List<MonitorLinkport> lpList){
		if( lpList == null || lpList.size() <= 0 ){
			return false;
		}
		List<String> strList = new ArrayList<String>();
		for( int i=0; i<lpList.size(); i++ ){
			MonitorLinkport ml = lpList.get(i);
			String str = "" + ml.getMonitorSubnet().getId() + "_"
						+ ml.getIp() + "_"
						+ ml.getInterface_();
			if( strList.contains(str) ){
				lpList.remove(i);
				i--;
			}else{
				strList.add(str);
			}
		}
		strList.clear();
		strList = null;
		return true;
	}
}
