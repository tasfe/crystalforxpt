package com.combanc.monitor.algorithm;

import java.util.List;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;
import com.combanc.monitor.pojo.MonitorSystemParam;

public class DataTool {
	// 判断已有设备列表里是否已经含有ip
	public static boolean containDevice(List<MonitorDevice> monitorDeviceList,
			String ip) {
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorDevice WHERE ip = :ip";
		try {
			q.parse(hql);
		} catch (QueryParseException e) {
			e.printStackTrace();
		}
		q.setVariable("ip", ip);

		QueryResults qr = new QueryResults();
		try {
			qr = q.execute(monitorDeviceList);
		} catch (QueryExecutionException e) {
			e.printStackTrace();
		}

		List<MonitorDevice> retList = qr.getResults();
		if (retList != null && retList.size() > 0) {
			// monitorDeviceList.remove(retList.get(0));
			return true;
		} else {
			return false;
		}
	}
	
	public static MonitorDeviceType findDeviceTypeByName(List<MonitorDeviceType> monitorDeviceTypeList, String name) {
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorDeviceType WHERE name = :name";
		try {
			q.parse(hql);
		} catch (QueryParseException e) {
			e.printStackTrace();
		}
		q.setVariable("name", name);
		
		QueryResults qr = new QueryResults();
		try {
			qr = q.execute(monitorDeviceTypeList);
		} catch (QueryExecutionException e) {
			e.printStackTrace();
		}
		
		List<MonitorDeviceType> retList = qr.getResults();
		if (retList != null && retList.size() > 0) {
			return retList.get(0);
		} else {
			return monitorDeviceTypeList.size() > 0 ? monitorDeviceTypeList.get(0) : null;
		}
	}
}
