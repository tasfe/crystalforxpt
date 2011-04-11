package com.combanc.monitor.alert.alertParser;

import java.util.List;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.pojo.MonitorUrlResponse;


/**
 * <p>
 * Title:报警分析
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */

public class ParserTool {

	/**
	 *  查询设备表，得出指定Id的设备对象
	 * @param id
	 * @param deviceList
	 * @return
	 */
	public static MonitorDevice getDeviceFromList( int id, List<MonitorDevice> deviceList ){
		if( deviceList == null ){
			return null;
		}
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorDevice WHERE id = " + id;
		QueryResults qr = null;

		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}

		try {
			qr = q.execute(deviceList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorDevice> retList = qr.getResults();
		if( retList != null && retList.size() > 0 ) {
			return retList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 *  查询互连接口表，得出指定Id的接口对象
	 * @param id
	 * @param linkportList
	 * @return
	 */
	public static MonitorLinkport getLinkportFromList(String ip,int interface_, List<MonitorLinkport> linkportList) {
		if( linkportList == null ){
			return null;
		}
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorLinkport WHERE ip = '"+ip+"' and interface_ = '" + interface_ + "'";
		QueryResults qr = null;

		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}

		try {
			qr = q.execute(linkportList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorLinkport> retList = qr.getResults();
		if( retList != null && retList.size() > 0 ) {
			return retList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 查询Ping监测表，得出指定Id的URL对象
	 * @param thePd
	 * @param pingDestList
	 * @return
	 */
	public static MonitorPingDest getPingDestFromList(MonitorPingDest thePd, List<MonitorPingDest> pingDestList) {
		if (thePd == null || pingDestList == null) {
			return null;
		}
		for (MonitorPingDest pd : pingDestList) {
			if (pd.getId().equals(thePd.getId())) {
				return pd;
			}
		}
		return null;
	}
	
	/**
	 *  查询URL监测表，得出指定Id的URL对象
	 * @param id
	 * @param urlList
	 * @return
	 */
	public static MonitorUrlResponse getUrlFromList( int id, List<MonitorUrlResponse> urlList ){
		if( urlList == null ){
			return null;
		}
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorUrlResponse WHERE id = " + id;
		QueryResults qr = null;

		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}

		try {
			qr = q.execute(urlList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorUrlResponse> retList = qr.getResults();
		if( retList != null && retList.size() > 0 ) {
			return retList.get(0);
		} else {
			return null;
		}
	}
	
	/** 查询设备表，得出指定Id的设备对象 **/
	public static MonitorDevice getDeviceFromListByIp(String ip, List<MonitorDevice> deviceList ){
		if( deviceList == null ){
			return null;
		}
		Query q = new Query();
		String hql = "SELECT * FROM com.combanc.monitor.pojo.MonitorDevice WHERE ip = :ip";
		QueryResults qr = null;

		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", ip);

		try {
			qr = q.execute(deviceList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorDevice> retList = qr.getResults();
		if( retList != null && retList.size() > 0 ) {
			return retList.get(0);
		} else {
			return null;
		}
	}
}
