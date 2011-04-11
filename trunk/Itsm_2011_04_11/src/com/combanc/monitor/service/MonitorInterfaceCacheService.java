package com.combanc.monitor.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.monitor.algorithm.DeviceInterfaceStatus;
import com.combanc.monitor.algorithm.DeviceSnmpQuery;
import com.combanc.monitor.dao.MonitorDeviceDAO;
import com.combanc.monitor.dao.MonitorInterfaceCacheDAO;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojoext.MonitorInterfaceCacheExt;

public class MonitorInterfaceCacheService extends BaseService<MonitorInterfaceCache, Integer> {
	private MonitorInterfaceCacheDAO monitorInterfaceCacheDAO;
	private DeviceInterfaceStatus deviceInterfaceStatus;
	private MonitorDeviceDAO monitorDeviceDAO;

	public MonitorDeviceDAO getMonitorDeviceDAO() {
		return monitorDeviceDAO;
	}
	public void setMonitorDeviceDAO(MonitorDeviceDAO monitorDeviceDAO) {
		this.monitorDeviceDAO = monitorDeviceDAO;
	}
	public void setMonitorInterfaceCacheDAO(MonitorInterfaceCacheDAO monitorInterfaceCacheDAO) {
		super.setDao(monitorInterfaceCacheDAO);
		this.monitorInterfaceCacheDAO = monitorInterfaceCacheDAO;
	}
	public DeviceInterfaceStatus getDeviceInterfaceStatus() {
		return deviceInterfaceStatus;
	}
	public void setDeviceInterfaceStatus(DeviceInterfaceStatus deviceInterfaceStatus) {
		this.deviceInterfaceStatus = deviceInterfaceStatus;
	}
	public MonitorInterfaceCacheDAO getMonitorInterfaceCacheDAO() {
		return monitorInterfaceCacheDAO;
	}
	
	// 注意这里是根据子网查找设备，根据设备在查找设备接口cache
	public List findBySubnet(Integer subnetId) {
		// 2010-08-20
		return null;
	}
	public List<MonitorInterfaceCache> findAll() {
		return monitorInterfaceCacheDAO.findAll();
	}
	
	public void batchInsert( List<MonitorInterfaceCache> interfaces) {
		monitorInterfaceCacheDAO.batchInsert(interfaces);
	}
	public List<MonitorInterfaceCache> findByDeviceId(Integer deviceId) {
		return monitorInterfaceCacheDAO.findByDeviceId(deviceId);
	}
	
	public List<MonitorInterfaceCache> findByDeviceIp(String deviceIp) {
		List<MonitorDevice> deviceList = monitorDeviceDAO.findByIp(deviceIp);
		if(null != deviceList && deviceList.size()>0){
			MonitorDevice device= deviceList.get(0);
			return monitorInterfaceCacheDAO.findByDeviceId(device.getId());
		}
		return null;
	}
	
	public synchronized List<MonitorInterfaceCacheExt> getDeviceInterfaceStatusFirst(Integer deviceId){
		List<MonitorInterfaceCacheExt> list = null;
		try{
			System.out.println("test  " + deviceId);
			deviceInterfaceStatus.init(Integer.valueOf(deviceId));
			deviceInterfaceStatus.pollFirst(deviceId);
			list = deviceInterfaceStatus.getInterfaceCacheExtList();
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 第二次轮询设备，传入前一次轮询结果的MonitorInterfaceCacheExt的list，并轮询，做差计算结果返回此次数据
	 * @param deviceId	设备id，用来查询设备，并设置SnmpTarget的ip、readCommunity值
	 * @param interfaceCacheExtList
	 * @return
	 */
	public synchronized List<MonitorInterfaceCacheExt> getDeviceInterfaceStatusSecond(Integer deviceId, List<MonitorInterfaceCacheExt> interfaceCacheExtList) {
		try{
			// deviceInterfaceStatus.pollSecond(index);
			// list = deviceInterfaceStatus.pollSecond(index, interfaceCacheExtList);
			interfaceCacheExtList = deviceInterfaceStatus.pollSecond(deviceId, interfaceCacheExtList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return interfaceCacheExtList;
		
	}
	/**根据设备ID,接口号查询目标对象**/
	public MonitorInterfaceCache findByDeviceAndInterface(Integer deviceId,String interface_){
		List<MonitorInterfaceCache> result=monitorInterfaceCacheDAO.findByDeviceAndInterface(deviceId, interface_);
		if(result.size()>0)
			return result.get(0);
		else
			return null;
	}
}
