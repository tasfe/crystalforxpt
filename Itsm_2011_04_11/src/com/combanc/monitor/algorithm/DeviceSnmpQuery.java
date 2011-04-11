package com.combanc.monitor.algorithm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.adventnet.snmp.beans.DataException;
import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.Locate;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.Variant;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorAlertType;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorVendor;
import com.combanc.monitor.pojoext.ArpEntry;
import com.combanc.monitor.pojoext.Ciscov6Entry;
import com.combanc.monitor.pojoext.Ietfv6Entry;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.Juniperv6Entry;
import com.combanc.monitor.pojoext.NeighborEntry;
import com.combanc.monitor.pojoext.NetflowEntry;
import com.combanc.monitor.pojoext.RouteEntry;
import com.combanc.monitor.pojoext.SflowEntry;
import com.combanc.monitor.pojoext.SysinfoEntry;
import com.combanc.monitor.service.MonitorAlertTypeService;
import com.combanc.monitor.service.MonitorInterfaceCacheService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.service.MonitorVendorService;
import com.combanc.monitor.util.DataSetTool;
import com.combanc.monitor.util.JosqlUtil;

/**
 * <p>
 * Title:
 * </p>
 * @author unascribed
 * @version 1.0
 */

// 注：这里的MIB表都是固定行的表，不需要检查行是否重复。而ARP、MAC端口转发表是动
// 态的，就必须检查重复记录
public class DeviceSnmpQuery {
	private MonitorInterfaceCacheService monitorInterfaceCacheService;
	private MonitorVendorService monitorVendorService;
	
	private MonitorAlertTypeService monitorAlertTypeService;
	
	

	public MonitorAlertTypeService getMonitorAlertTypeService() {
		return monitorAlertTypeService;
	}
	public void setMonitorAlertTypeService(
			MonitorAlertTypeService monitorAlertTypeService) {
		this.monitorAlertTypeService = monitorAlertTypeService;
	}

	SnmpTarget target = new SnmpTarget();
	// CISCO/3COM设备VLAN表：
//	TextDataFile vlanDataFile = new TextDataFile();
//	TableDataSet vlanDataSet = new TableDataSet();
	List<String> vlanList = new ArrayList<String>();

	String displayStr;

	public MonitorInterfaceCacheService getMonitorInterfaceCacheService() {
		return monitorInterfaceCacheService;
	}
	public void setMonitorInterfaceCacheService(
			MonitorInterfaceCacheService monitorInterfaceCacheService) {
		this.monitorInterfaceCacheService = monitorInterfaceCacheService;
	}
	public MonitorVendorService getMonitorVendorService() {
		return monitorVendorService;
	}
	public void setMonitorVendorService(MonitorVendorService monitorVendorService) {
		this.monitorVendorService = monitorVendorService;
	}
	public DeviceSnmpQuery() {
		displayStr = "读 ";
		target.setLoadFromCompiledMibs(true);
		target.setTimeout(MainConstants.SNMP_TIME_OUT);
		target.setRetries(MainConstants.SNMP_RETRY);
	}
	
	public void setDisplayStr(String str) {
		displayStr = str + " ";
	}
	
	// 关闭指定设备的指定接口
	// 说明：以BAY 450交换机为例，SET操作完成后，端口需要十几秒的时间来达到稳定的接通/不通状态。
	// .iso.org.dod.internet.mgmt.mib-2.interfaces.ifTable.ifEntry.ifAdminStatus
	// OID "2.2.1.1" ：ifIndex
	// .iso.org.dod.internet.mgmt.mib-2.interfaces.ifTable.ifEntry.ifIndex
	// OID "2.2.1.7" ：ifAdminStatus
	public boolean interfaceSet(String device, String ifIndex, String commStr,
			String select) {
		String result = null;
		String ifOIDPrefix = ".1.3.6.1.2.1.2.2.1.7.";
		target.setTargetHost(device);
		target.setWriteCommunity(commStr);

		target.setObjectID(ifOIDPrefix + ifIndex);
		// SET操作必须俘获
		try {
			if (select.equals("关闭"))
				result = target.snmpSet("2");// down(2)
			if (select.equals("打开"))
				result = target.snmpSet("1");// up(1)
		} catch (DataException e) {
			JOptionPane.showMessageDialog(null, e);
		}

		// SET操作如成功，返回值与所赋值相同；如失败，返回值为null。
		if (result == null)
			return false;
		else
			return true;
	}
	
	// 读取指定设备的接口及描述表，形成CACHE
	public String getIFCache(MonitorDevice mDevice, List<MonitorInterfaceCache> micList,
			boolean cache, boolean isServer) {
		// 2010-08-20
		micList.clear();
		// true:使用CACHE方式，先读文件，如果文件有则返回否则读设备；false:不使用CACHE方式，直接读设备
		if (cache && !isServer) {
			//micList = monitorInterfaceCacheService.findByDeviceId(mDevice.getId());
			List<MonitorInterfaceCache> list = monitorInterfaceCacheService.findByDeviceId(mDevice.getId());
			// 2010-09-20  注意这里查询出来的list是一个新的list，而传入的list指向某一个内存地址，
			// 不能直接把list的地址赋值给micList，必须把所有对象克隆过去
			for(MonitorInterfaceCache mic : list) {
				micList.add(mic);
			}
			if (!micList.isEmpty()) {
				// 如果该设备的端口接口映射已在CACHE表中，则从CACHE中读取
				return "OK";
			}
		}

		// 如果Cache中没有，则直接读取，并保存
		String ret = readDeviceInterface(mDevice, micList, !isServer);
		if (!ret.equals("OK"))
			return ret;
		return "OK";
	}
	
	/*
	// lsj 设备接口表
	// 读MIB II接口表，结果保存在interfaceDataSet中。分读指定行模式（spec = true)和整表模式（spec =
	// false)。
	public String readDeviceInterfaceDS(MonitorDevice mDevice, DataSet deviceInterfaceDS, boolean save) {
		// 2010-08-20 杨坤 此处多线程时存在问题，用户1在读取接口cache并要存储时，用户2删除了设备
		String[] columnNames = new String[] { "IP", "端口", "接口", "接口描述", "备注",
				"子网", "subnetId", };
		int columnTypes[] = new int[] { Variant.STRING, Variant.STRING,
				Variant.STRING, Variant.STRING, Variant.STRING, Variant.STRING,
				Variant.INT };
		DataSet ifPortDS = DataSetTool.initDataSet(columnNames, columnTypes);
		ifPortDS.open();

		deviceInterfaceDS.open();

		// 接口Cache表
		String[] OIDs = new String[3];
		// mib-2.interfaces.ifTable.ifEntry.ifIndex
		OIDs[0] = ".1.3.6.1.2.1.2.2.1.1";
		// mib-2.interfaces.ifTable.ifEntry.ifDescr
		OIDs[1] = ".1.3.6.1.2.1.2.2.1.2";
		// mib-2.ifMIB.ifMIBObjects.ifXTable.ifXEntry.ifAlias
		OIDs[2] = ".1.3.6.1.2.1.31.1.1.1.18";

		// 端口接口对应表
		String[] portOIDs = new String[2];
		// mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable.dot1dBasePortEntry.dot1dBasePort
		portOIDs[0] = ".1.3.6.1.2.1.17.1.4.1.1";
		// mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable.dot1dBasePortEntry.dot1dBasePortIfIndex
		portOIDs[1] = ".1.3.6.1.2.1.17.1.4.1.2";

		String ret = getSnmpTable("设备接口表", OIDs, mDevice.getIp(), mDevice.getReadCommunity(),
				deviceInterfaceDS);
		if (!ret.equals("OK"))
			return ret;
		String ret2 = getSnmpTable("端口接口对应表", portOIDs, mDevice.getIp(), mDevice.getReadCommunity(),
				ifPortDS);

		List<MonitorInterfaceCache> interfaces = new ArrayList<MonitorInterfaceCache>();
		deviceInterfaceDS.first();
		do {
			MonitorInterfaceCache mic = new MonitorInterfaceCache();
			if (save) {
				mic.setMonitorDevice(mDevice);
				mic.setInterface_(deviceInterfaceDS.getString("接口"));
				mic.setDescription(deviceInterfaceDS.getString("接口描述"));
				mic.setNote(deviceInterfaceDS.getString("备注"));
			}

			// 注端口接口表里没有存储子网和IP项，因为已经确定子网、IP，仅使用接口来匹配端口
			DataRow loc = new DataRow(ifPortDS, new String[] { "接口" });
			loc.setString("接口", deviceInterfaceDS.getString("接口"));
			if (ifPortDS.locate(loc, Locate.FIRST)) {
				// 此处save判断应该去除！，在最后是否存储才判断，且save为false只有一种情况，即refresh接口表的的时候。
				// if (save) {
				mic.setPort(ifPortDS.getString("端口"));
				// 下面的语句一开始没有在if(save)条件判断内
				deviceInterfaceDS.setString("端口", ifPortDS.getString("端口"));
				// }
			}

			// if (save)
			interfaces.add(mic);
		} while (deviceInterfaceDS.next());
		// 读取MAC转发表的时候才save，refresh的时候不save
		if (save) {
			System.out.println("直接读取Cache...并且保存");
			monitorInterfaceCacheService.batchInsert(interfaces);
		}
		return "OK";
	}
*/	
	public String readDeviceInterface(MonitorDevice mDevice, List<MonitorInterfaceCache> micList, boolean save) {
		// 2010-08-20 杨坤 此处多线程时存在问题，用户1在读取接口cache并要存储时，用户2删除了设备
		// 接口Cache表
		String[] OIDs = new String[2];
		// mib-2.interfaces.ifTable.ifEntry.ifIndex
		OIDs[0] = ".1.3.6.1.2.1.2.2.1.1";
		// mib-2.interfaces.ifTable.ifEntry.ifDescr
		OIDs[1] = ".1.3.6.1.2.1.2.2.1.2";
		
		// 2010-09-20 注意接口别名必须分开读，某些国产设备不提供接口别名，一起读导致整个接口表读取失败
		// 接口别名表
		String[] aliasOIDs = new String[1];
		// mib-2.ifMIB.ifMIBObjects.ifXTable.ifXEntry.ifAlias
		aliasOIDs[0] = ".1.3.6.1.2.1.31.1.1.1.18";
		
		// 端口接口对应表
		String[] portOIDs = new String[2];
		// mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable.dot1dBasePortEntry.dot1dBasePort
		portOIDs[0] = ".1.3.6.1.2.1.17.1.4.1.1";
		// mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable.dot1dBasePortEntry.dot1dBasePortIfIndex
		portOIDs[1] = ".1.3.6.1.2.1.17.1.4.1.2";

		String ret = getSnmpTableList("设备接口表", OIDs, mDevice, micList);
		if (!ret.equals("OK"))
			return ret;
		
		// 读取接口别名（接口备注），新建 List<InterfaceEntry>，将接口复制到此list，然后调用getAllIFAlias
		// 读取成功再将备注复制回来
        List<InterfaceEntry> ieList = new ArrayList<InterfaceEntry>();
        for(MonitorInterfaceCache mic : micList) {
        	InterfaceEntry ie = new InterfaceEntry();
        	ie.setInterface_(mic.getInterface_());
        }
        String ret2 = getSnmpTableList("端口接口对应表", portOIDs, mDevice, ieList);
        if(ret2.equals("OK")) {
			for(MonitorInterfaceCache mic : micList) {
	        	for(InterfaceEntry ie : ieList) {
	        		if(ie.getInterface_().equals(mic.getInterface_())) {
	        			mic.setPort(ie.getPort());
	        			break;
	        		}
	        	}
			}
		}
		String ret1 = getAllIFAlias(mDevice, ieList);
		if(ret1.equals("OK")) {
			for(MonitorInterfaceCache mic : micList) {
	        	for(InterfaceEntry ie : ieList) {
	        		if(ie.getInterface_().equals(mic.getInterface_())) {
	        			mic.setNote(ie.getIfxAlias());
	        			break;
	        		}
	        	}
			}
		}

		// 读取MAC转发表的时候才save，refresh的时候不save
		if (save) {
			System.out.println("直接读取Cache...并且保存");
			monitorInterfaceCacheService.batchInsert(micList); // ????不能插入 原因：Connection is read-only

		}
		return "OK";
	}

	// 读IP接口状态：1、读取IP、接口对应。2、读取接口状态（包括index、描述、速率、mac、开闭状态、上线状态）
	public String getIFStatus(MonitorDevice mDevice, List<InterfaceEntry> ieList) {
		String ret;
		// 读接口IP地址表：
		ret = getIFIPTable(mDevice, ieList);
		if (!ret.equals("OK"))
			return ret;
		// 读接口表对应行，得到接口状态
		return getIFTable(mDevice, ieList, true);
		/*
		String ret;
		// 读接口IP地址表：
		ret = getIFIPTable(device, commStr, interfaceDataSet);
		if (!ret.equals("OK"))
			return ret;
		// 读接口表对应行，得到接口状态
		return getIFTable(device, commStr, interfaceDataSet, true);
		*/
	}
	
	public void getDeviceMac(MonitorDevice mDevice) {
		List<InterfaceEntry> ieList = new ArrayList<InterfaceEntry>();
		// 读IP接口状态表，并根据IP查找设备的MAC
	//	String ret = getIFIPTable(mDevice, ieList);
		String ret = getIFStatus(mDevice, ieList);
		
		if (!ret.equals("OK")) {
			mDevice.setMac("");
			return;
		}
		for(InterfaceEntry ie : ieList) {
			if(ie.getIp() != null && ie.getIp().equals(mDevice.getIp())) {
				mDevice.setMac(ie.getMac());
				return;
			}
		}
		mDevice.setMac("");
	}

	// 读设备接口IP地址表
	// 注：发现CISCO路由器可以为一个接口配多个地址。
	public String getIFIPTable(MonitorDevice mDevice, List<InterfaceEntry> ieList) {
		String[] OIDs = new String[2];
		// .iso.org.dod.internet.mgmt.mib-2.ip.ipAddrTable.ipAddrEntry.ipAdEntAddr
		OIDs[0] = ".1.3.6.1.2.1.4.20.1.1";// IP地址
		// .iso.org.dod.internet.mgmt.mib-2.ip.ipAddrTable.ipAddrEntry.ipAdEntIfIndex
		OIDs[1] = ".1.3.6.1.2.1.4.20.1.2";// 接口
		return getSnmpTableList("接口IP地址表", OIDs, mDevice, ieList);
	}

	// 读交换机端口状态：端口、接口、接口描述、开闭、上线接口速率
	public String getPortStatus(MonitorDevice mDevice, boolean MIndex,
			List<InterfaceEntry> ieList) {
		String ret;
		// 首先读BRIDGE-MIB端口/接口对应表：
		ret = getPortIFTable(mDevice, MIndex, ieList);
		if (!ret.equals("OK"))
			return ret;
		// 再读MIB II接口表对应行，获得每个接口的状态
		return getIFTable(mDevice, ieList, true);
	}

	// 读指定设备BRIDGE-MIB 端口/接口表，结果保存在ieList中，与MACPORTSCAN中不同，没有CACHE
	public String getPortIFTable(MonitorDevice mDevice, boolean MIndex, List<InterfaceEntry> ieList) {
		String device = mDevice.getIp();
		String commStr = mDevice.getReadCommunity();
		String ret;
		String[] OIDs = new String[2];
		// mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable.dot1dBasePortEntry.dot1dBasePort
		OIDs[0] = ".1.3.6.1.2.1.17.1.4.1.1";
		// mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable.dot1dBasePortEntry.dot1dBasePortIfIndex
		OIDs[1] = ".1.3.6.1.2.1.17.1.4.1.2";
		// 如果没有设置端口接口表INDEXING模式则返回
		if (!MIndex)
			return getSnmpTableList("端口接口对应表", OIDs, mDevice, ieList);

		// 判断设备是否支持CISCO VTP并使用Community Index方式读
		String[] vlanOids = new String[1];
		// private.enterprises.cisco.ciscoMgmt.ciscoVtpMIB.vtpMIBObjects.vlanInfo.vtpVlanTable.vtpVlanEntry.vtpVlanState
		vlanOids[0] = ".1.3.6.1.4.1.9.9.46.1.3.1.1.2";// vtpVlanState
		// vlanDataSet.deleteAllRows();
		vlanList.clear();
		ret = getSnmpTableList("CISCO VLAN表", vlanOids, mDevice, vlanList);
		if (!ret.equals("OK"))
			return ret;

		// 如果成功读取表明是CISCO设备
		for(String tmp : vlanList) {
			// CISCO设备不包括VLAN号在1000以后的管理VLAN，某些型号的CISCO设备1000号以后的VLAN存在但不能读。
			// CISCO说明要么是BUG，要么是给FDDI、TOKEN RING使用。
			if (tmp.length() > 3)
				continue;
			displayStr = "读 VLAN " + tmp + " ";
			tmp = commStr + "@" + tmp;
			// 2010-09-10 此处setReadCommunity ？
			mDevice.setReadCommunity(tmp);
			ret = getSnmpTableList("端口接口对应表", OIDs, mDevice, ieList);
			// 如果读不成功则认为不支持Community Indexing，退出循环，这时只有不用
			// Community Indexing方法读出的数据。这时因超时会出现较长时间的等待。
			if (!ret.equals("OK") && !ret.equals("表空"))
				break;
		}
		/*
		// 如果成功读取表明是CISCO设备
		vlanDataSet.first();
		String tmp = new String();
		do {
			tmp = vlanDataSet.getString("值");
			// CISCO设备不包括VLAN号在1000以后的管理VLAN，某些型号的CISCO设备1000号以后的VLAN存在但不能读。
			// CISCO说明要么是BUG，要么是给FDDI、TOKEN RING使用。
			if (tmp.length() > 3)
				continue;
			displayStr = "读 VLAN " + tmp + " ";
			tmp = commStr + "@" + tmp;
			// 2010-09-10 此处setReadCommunity ？
			mDevice.setReadCommunity(tmp);
			ret = getSnmpTableList("端口接口对应表", OIDs, mDevice, ieList);
			// 如果读不成功则认为不支持Community Indexing，退出循环，这时只有不用
			// Community Indexing方法读出的数据。这时因超时会出现较长时间的等待。
			if (!ret.equals("OK") && !ret.equals("表空"))
				break;
		} while (vlanDataSet.next());
		*/
		displayStr = "读 ";// 复原因提示VLAN信息而改变的显示字符串
		return "OK";
	}

	// 读MIB II接口表，结果保存在ieList中。分读指定行模式（spec = true)和整表模式（spec =
	// false)。
	@SuppressWarnings("unchecked")
	public String getIFTable(MonitorDevice mDevice,	List<InterfaceEntry> ieList, boolean spec) {
		String[] OIDs = new String[6];
		// mib-2.interfaces.ifTable.ifEntry.ifIndex
		OIDs[0] = ".1.3.6.1.2.1.2.2.1.1";
		// mib-2.interfaces.ifTable.ifEntry.ifDescr
		OIDs[1] = ".1.3.6.1.2.1.2.2.1.2";
		// mib-2.interfaces.ifTable.ifEntry.ifSpeed
		OIDs[2] = ".1.3.6.1.2.1.2.2.1.5";

//		String hql = "from MonitorDevice as md where  md.ip = '" + device + "' and md.note1 like 'C64'";
//		List<MonitorDevice> list = monitorDeviceService.findBySql(hql);
//		for (MonitorDevice md : list) {
//			OIDs[2] = ".1.3.6.1.2.1.31.1.1.1.15";		// 带宽，64位计数器
//		}
		String note1 = mDevice.getNote1();
		if(note1 != null && note1.equalsIgnoreCase("c64")) {
			OIDs[2] = ".1.3.6.1.2.1.31.1.1.1.15";		// 带宽，64位计数器
		}

		// mib-2.interfaces.ifTable.ifEntry.ifPhysAddress
		OIDs[3] = ".1.3.6.1.2.1.2.2.1.6";
		// mib-2.interfaces.ifTable.ifEntry.ifAdminStatus
		OIDs[4] = ".1.3.6.1.2.1.2.2.1.7";
		// mib-2.interfaces.ifTable.ifEntry.ifOperStatus
		OIDs[5] = ".1.3.6.1.2.1.2.2.1.8";
		// 读接口表指定行：
		if (spec)
			return getSpecifiedRows("接口表指定行", "接口", OIDs, mDevice, ieList);
		// 读整个接口表：
		else
			return getSnmpTableList("接口表", OIDs, mDevice, ieList);
	}

	// 读指定设备的路由表：
	public String getRouterTable(MonitorDevice mDevice, List<RouteEntry> reList) {
		String note1 = mDevice.getNote1();
		String[] OIDs = new String[5];
		// lsj 2009-08-10 JUNIPER 路由表特殊读取方法 CIDR
		if (note1 != null && (note1.toUpperCase().equals("CIDR")
				|| note1.toUpperCase().equals("JUNIPER"))) {
			System.out.println("CIDR......");
			// IP-FORWARD-MIB(RFC-2096) CIDR
			// .iso.org.dod.internet.mgmt.mib-2.ip.ipForward.ipCidrRouteTable.ipCidrRouteEntry
			OIDs[0] = ".1.3.6.1.2.1.4.24.4.1.1"; // OID of ipCidrRouteDest
			OIDs[1] = ".1.3.6.1.2.1.4.24.4.1.5"; // OID of ipCidrRouteIfIndex
			OIDs[2] = ".1.3.6.1.2.1.4.24.4.1.4"; // OID of ipCidrRouteNextHop
			OIDs[3] = ".1.3.6.1.2.1.4.24.4.1.6"; // OID of ipCidrRouteType
			OIDs[4] = ".1.3.6.1.2.1.4.24.4.1.2"; // OID of ipCidrRouteMask
		} else {
			OIDs[0] = ".1.3.6.1.2.1.4.21.1.1";// OID of ipRouteDest
			OIDs[1] = ".1.3.6.1.2.1.4.21.1.2";// OID of ipRouteInterface
			OIDs[2] = ".1.3.6.1.2.1.4.21.1.7";// OID of ipRouteNextHop
			OIDs[3] = ".1.3.6.1.2.1.4.21.1.8";// OID of ipRouteType
			OIDs[4] = ".1.3.6.1.2.1.4.21.1.11";// OID of ipRouteMask
		}
		String ret = getSnmpTableList("路由表", OIDs, mDevice, reList);
		if (!ret.equals("OK"))
			return ret;
		return appendIFDescr(mDevice, reList);
	}// getRouterTable;
	
	// 读指定设备的IP地址转发表：
	@SuppressWarnings("unchecked")
	public String getIPForward(MonitorDevice mDevice, List<ArpEntry> aeList) {
		String[] OIDs = new String[4];
		OIDs[0] = ".1.3.6.1.2.1.4.22.1.1";// OID of ipNetToMediaIfIndex
		OIDs[1] = ".1.3.6.1.2.1.4.22.1.2";// OID of ipNetToMediaPhysAddress
		OIDs[2] = ".1.3.6.1.2.1.4.22.1.3";// OID of ipNetToMediaNetAddress
		OIDs[3] = ".1.3.6.1.2.1.4.22.1.4";// OID of ipNetToMediaType
		String ret = getSnmpTableList("ARP表", OIDs, mDevice, aeList);
		if (!ret.equals("OK"))
			return ret;
		return appendIFDescr(mDevice, aeList);
	}

	// 读指定设备的接口/MAC地址对应：
	public String getIFMACTable(MonitorDevice mDevice, List<InterfaceEntry> ieList) {
		String[] OIDs = new String[2];
		// mib-2.interfaces.ifTable.ifEntry.ifIndex
		OIDs[0] = ".1.3.6.1.2.1.2.2.1.1";
		// mib-2.interfaces.ifTable.ifEntry.ifPhysAddress
		OIDs[1] = ".1.3.6.1.2.1.2.2.1.6";
		return getSnmpTableList("接口地址表", OIDs, mDevice, ieList);
	}

	// 读指定设备的指定SNMP表：
	private String getSnmpTable(String tableName, String[] oids, String device,
			String commStr, DataSet dataSet) {
		int readCount = 0;
		String result[] = new String[oids.length];
		String indexOid, tempStr, oidStr;
		int indexOidLen;
		boolean firstRecord = true;

		target.setTargetHost(device);
		target.setCommunity(commStr);
		indexOid = oids[0];
		indexOidLen = indexOid.length();
		target.setObjectIDList(oids);

		display(displayStr + device + " " + tableName + "...");
		while (true) {
			result = target.snmpGetNextList();

			if (result == null) {
				display(displayStr + device + " " + tableName + "SNMP超时。");
				return "超时";
			}

			oidStr = target.getSnmpOID().toString();
			if (oidStr.length() < indexOidLen) {
				if (firstRecord) {
					display(displayStr + device + " MIB中没有" + tableName
							+ "或表空。");
					return "表空";
				} else {
					display(displayStr + device + " " + tableName + "完毕。");
					return "OK";
				}
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord) {
					display(displayStr + device + " MIB中没有" + tableName
							+ "或表空。");
					return "表空";
				} else {
					display(displayStr + device + " " + tableName + "完毕。");
					return "OK";
				}
			}

			readCount++;
			display(displayStr + device + " " + tableName + " " + readCount
					+ " 条记录。");
			firstRecord = false;

			// 这两个表使用OID，所以在这里保存
			if (tableName.equals("CISCO VLAN表")) {
				if (result[0].equals("1") || result[0].equals("operational(1)")) {
					DataRow row = new DataRow(dataSet);
					row.setString("值", oidStr
							.substring(oidStr.lastIndexOf(".") + 1));
					dataSet.addRow(row);
				}
			} else if (tableName.equals("CDP表")) {
				if (result[0].length() == 11) {
					String tmp = oidStr.substring(0, oidStr.lastIndexOf("."));
					tmp = tmp.substring(tmp.lastIndexOf(".") + 1);
					DataRow row = new DataRow(dataSet);
					row.setString("接口", tmp);
					row.setString("下连IP", Tools.getCiscoIp(result[0]));
					row.setString("下连接口描述", result[1]);
					dataSet.addRow(row);
				}
			} else if (tableName.equals("CISCOV6表")) {
				DataRow row = new DataRow(dataSet);
				// 从返回OID的最后一位得到接口号
				row.setString("接口", oidStr
						.substring(oidStr.lastIndexOf(".") + 1));
				row.setString("MTU", result[0]);
				row.setString("最大报文长度", result[1]);
				dataSet.addRow(row);
			} else if (tableName.equals("HDP表")) {
				// 去掉接口前缀.1.3.6.1.4.1.2011.6.7.5.6.1.1.
				String tmp = oidStr.substring(30);
				// 去掉接口后缀得到接口
				tmp = tmp.substring(0, tmp.indexOf("."));
				DataRow row = new DataRow(dataSet);
				row.setString("接口", tmp);
				row.setString("下连IP", result[0]);
				row.setString("下连接口描述", result[1]);
				dataSet.addRow(row);
			} else if (tableName.equals("设备接口表")) {
				DataRow row = new DataRow(dataSet);
				row.setString("IP", device);
				// lsj 2009.09.23
				// row.setString("端口", result[0]);
				row.setString("端口", "-");
				row.setString("接口", result[0]);
				row.setString("接口描述", result[1]);
				row.setString("备注", result[2]);
				dataSet.addRow(row);
			} else {
				restoreDataSet(tableName, result, dataSet);
			}
			// 两条记录间不等待。与扫描模版无关。
		}// while
	}
	
	// 读指定设备的指定SNMP表：
	@SuppressWarnings("unchecked")
	private String getSnmpTableList(String tableName, String[] oids, MonitorDevice mDevice, List list) {
		String device = mDevice.getIp();
		int readCount = 0;
		String result[] = new String[oids.length];
		String indexOid, tempStr, oidStr;
		int indexOidLen;
		boolean firstRecord = true;

		target.setTargetHost(mDevice.getIp());
		target.setCommunity(mDevice.getReadCommunity());
		indexOid = oids[0];
		indexOidLen = indexOid.length();
		target.setObjectIDList(oids);

		display(displayStr + device + " " + tableName + "...");
		while (true) {
			result = target.snmpGetNextList();

			if (result == null) {
				display(displayStr + device + " " + tableName + "SNMP超时。");
				return "超时";
			}

			oidStr = target.getSnmpOID().toString();
			if (oidStr.length() < indexOidLen) {
				if (firstRecord) {
					display(displayStr + device + " MIB中没有" + tableName
							+ "或表空。");
					return "表空";
				} else {
					display(displayStr + device + " " + tableName + "完毕。");
					return "OK";
				}
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord) {
					display(displayStr + device + " MIB中没有" + tableName
							+ "或表空。");
					return "表空";
				} else {
					display(displayStr + device + " " + tableName + "完毕。");
					return "OK";
				}
			}

			readCount++;
			display(displayStr + device + " " + tableName + " " + readCount
					+ " 条记录。");
			firstRecord = false;

			// 这两个表使用OID，所以在这里保存
			if (tableName.equals("CISCO VLAN表")) {
				if (result[0].equals("1") || result[0].equals("operational(1)")) {
					/*
					DataRow row = new DataRow(dataSet);
					row.setString("值", oidStr
							.substring(oidStr.lastIndexOf(".") + 1));
					dataSet.addRow(row);
					*/
				}
			} else if (tableName.equals("CDP表")) {
				if (result[0].length() == 11) {
					String tmp = oidStr.substring(0, oidStr.lastIndexOf("."));
					tmp = tmp.substring(tmp.lastIndexOf(".") + 1);
					NeighborEntry ne = new NeighborEntry();
					ne.setInterface_(tmp);
					ne.setDownIp(Tools.getCiscoIp(result[0]));
					ne.setDownDescription(result[1]);
					list.add(ne);
				}
			} else if (tableName.equals("HDP表")) {
				// 去掉接口前缀.1.3.6.1.4.1.2011.6.7.5.6.1.1.
				String tmp = oidStr.substring(30);
				// 去掉接口后缀得到接口
				tmp = tmp.substring(0, tmp.indexOf("."));
				NeighborEntry ne = new NeighborEntry();
				ne.setInterface_(tmp);
				ne.setDownIp(result[0]);
				ne.setDownDescription(result[1]);
				list.add(ne);
			} else if (tableName.equals("CISCOV6表")) {
				Ciscov6Entry ce = new Ciscov6Entry();
				// 从返回OID的最后一位得到接口号
				ce.setInterface_(oidStr.substring(oidStr.lastIndexOf(".") + 1));
				ce.setMtu(result[0]);
				ce.setMaxSize(result[1]);
				list.add(ce);
			} else {
				saveList(tableName, mDevice, result, list);
			}
			// 两条记录间不等待。与扫描模版无关。
		}// while
	}
	
	// 读表中的指定行。指定行的INDEX由DATASET中columnName列值指定。不读无关的行。
	// 2010-09-10 被 接口名表、接口别名表、接口表指定行（getIFTable，接口表对应行，获得每个接口的状态）调用
	// 目前来看所有的调用都是interfaceDataSet的，即List<InterfaceEntry>
	private String getSpecifiedRows(String tableName, String columnName,
			String[] oids, MonitorDevice mDevice, List<InterfaceEntry> ieList) {
		String device = mDevice.getIp();
		if (ieList.isEmpty())
			return "表空";
		int i;
		int readCount = 0;
		String rowOids[] = new String[oids.length];
		String result[] = new String[oids.length];
		int errorCode, errorIndex;

		target.setTargetHost(mDevice.getIp());
		target.setCommunity(mDevice.getReadCommunity());

		display(displayStr + device + " " + tableName + "...");
		String tmp = "";
		long temp;
		for(InterfaceEntry ie : ieList) {
//			if(!ie.getIp().equals(mDevice.getIp()))
//				continue;
			// 生成指定列的OIDS：
			// 此处所有函数调用时，columnName都是“接口”
			if("接口".equals(columnName))
				tmp = ie.getInterface_();
			for (i = 0; i < oids.length; i++)
				//rowOids[i] = oids[i] + "." + dataSet.getString(columnName);
				rowOids[i] = oids[i] + "." + tmp;
				
			target.setObjectIDList(rowOids);
			result = target.snmpGetList();
			// if(result != null)
			// System.out.println("getAlias……………" + device +
			// "     columnName    " + columnName + "     "+ result[0]);
			errorCode = target.getErrorCode();
			errorIndex = target.getErrorIndex();

			// 如果超时返回，其它错误（主要是IP接口不在接口表中）当前行无数据，继续读下一条记录
			if (errorCode != 0) {
				if (errorCode == 22 && errorIndex == 0)
					return "超时";
				continue;
			}

			readCount++;
			display(displayStr + device + " " + tableName + " " + readCount
					+ " 条记录。");

			// 2010-09-20 此处直接更新ieList的相关信息，因为不同于dataset有个指针可以移动到某一行
			// List再传递参数保存不方便
			// saveList(tableName, mDevice, result, ieList);
			if("接口表指定行".equals(tableName)) {
				// 做编码转换，使采用中文编码的设备或主机的描述信息能够正确显示。但对CISCO 1900出错。
				// 发现其DESCR与IFINDEX相同。
				result[1] = Tools.decodeGBK(result[1]);
				// updateRow
				ie.setDescription(result[1]);
				ie.setSpeed(result[2]);
				ie.setMac(result[3]);
				// 接口有三种管理状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
				if (result[4].equals("1") || result[4].equals("up(1)"))
					ie.setOpenup(true);
				else
					ie.setOpenup(false);
				// 接口有三种上线状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
				if (result[5].equals("1") || result[5].equals("up(1)"))
					ie.setOnline(true);
				else
					ie.setOnline(false);
			}
			if ("接口名表".equals(tableName)) {// 读出接口名（IF-MIB，NAME）
				// updateRow
				ie.setIfxName(result[0]);
			}

			if ("接口别名表".equals(tableName)) {// 读出接口别名（IF-MIB，ALIAS）
				// updateRow
				ie.setIfxAlias(result[0]);
			}
			// 两条记录间不等待。与扫描模版无关。
		}
		display(displayStr + device + " " + tableName + "完毕。");
		return "OK";
	}

	private void restoreDataSet(String tableName, String[] Result,
			DataSet dataSet) {
		DataRow row = new DataRow(dataSet);

		if (tableName.equals("接口CACHE表")) {
			if (!Tools.portFormatOK(Result[0]))
				return;
			row.setString("接口", Result[0]);
			row.setString("接口描述", Result[1]);
			dataSet.addRow(row);
		}

		if (tableName.equals("接口IP地址表")) {
			if (Result[0].equals("127.0.0.1"))
				return;
			row.setString("IP", Result[0]);
			row.setString("接口", Result[1]);
			dataSet.addRow(row);
		}

		if (tableName.equals("端口接口对应表")) {
			// 检查端口、接口格式是否正确，同一设备不能有重复的端口/接口记录
			if (!Tools.portFormatOK(Result[0]))
				return;
			if (!Tools.portFormatOK(Result[1]))
				return;
			DataRow loc = new DataRow(dataSet, new String[] { "端口", "接口" });
			loc.setString("端口", Result[0]);
			loc.setString("接口", Result[1]);
			if (!dataSet.locate(loc, Locate.FIRST)) {
				row.setString("端口", Result[0]);
				row.setString("接口", Result[1]);
				// lsj 7.30 bug 没有子网列 row.setString("子网", monitorSubnet);
				dataSet.addRow(row);
			}
		}

		if (tableName.equals("接口表")) {
			// 检查接口格式是否正确
			if (!Tools.portFormatOK(Result[0]))
				return;
			row.setString("接口", Result[0]);
			row.setString("接口描述", Tools.decodeGBK(Result[1]));
			row.setString("接口速率", Tools.getIfSpeed(Result[2]));
			row.setString("MAC", Result[3]);
			// 接口有三种管理状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
			if (Result[4].equals("1") || Result[4].equals("up(1)"))
				row.setBoolean("开闭", true);
			else
				row.setBoolean("开闭", false);
			// 接口有三种上线状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
			if (Result[5].equals("1") || Result[5].equals("up(1)"))
				row.setBoolean("上线", true);
			else
				row.setBoolean("上线", false);
			dataSet.addRow(row);
		}

		if (tableName.equals("接口表指定行")) {
			ifScanToDB(Result, dataSet);
		}

		if (tableName.equals("接口名表")) {// 读出接口名（IF-MIB，NAME）
			// updateRow
			dataSet.setString("接口名", Result[0]);
		}

		if (tableName.equals("接口别名表")) {// 读出接口别名（IF-MIB，ALIAS）
			// updateRow
			dataSet.setString("备注", Result[0]);
		}
/*
		if (tableName.equals("路由表")) {
			row.setString("网段", Result[0]);
			row.setString("接口", Result[1]);
			row.setString("掩码", Result[4]);
			row.setString("下一跳", Result[2]);
			if (Result[3].equals("3") || Result[3].equals("direct(3)")) {
				row.setString("类型", "直连");
			} else {
				if (Result[3].equals("4") || Result[3].equals("indirect(4)"))
					row.setString("类型", "非直连");
				else
					row.setString("类型", "其它");
			}
			row.setString("直连路由设备", device);
			row.setString("子网", monitorSubnet);
			dataSet.addRow(row);
		}
*/
		if (tableName.equals("ARP表")) {
			// 同一设备不能有重复的ARP记录
			DataRow loc = new DataRow(dataSet, "IP");
			loc.setString("IP", Result[2]);
			if (!dataSet.locate(loc, Locate.FIRST)) {
				row.setString("接口", Result[0]);
				row.setString("IP", Result[2]);
				if (Result[1].length() == 6)
					Result[1] = Tools.wrongBytesToStr(Result[1]);
				// 检查MAC地址格式是否合法：
				if (!Tools.macFormatOK(Result[1]))
					return;

				row.setString("MAC", Result[1]);
				if (Result[3].equals("1") || Result[3].equals("other(1)"))
					row.setString("类型", "其它");
				if (Result[3].equals("2") || Result[3].equals("invalid(2)"))
					row.setString("类型", "无效");
				// 锐捷3760 2 可信
				if (Result[3].equals("3") || Result[3].equals("dynamic(3)"))
					row.setString("类型", "动态");
				if (Result[3].equals("4") || Result[3].equals("static(4)"))
					row.setString("类型", "静态");
				dataSet.addRow(row);
			}
		}

		if (tableName.equals("接口地址表")) {
			if (!Tools.portFormatOK(Result[0]))
				return;
			if (!Tools.macFormatOK(Result[1]))
				return;
			row.setString("接口", Result[0]);
			row.setString("MAC", Result[1]);
			dataSet.addRow(row);
		}

		if (tableName.equals("SFLOW表")) {
			row.setString("采集器", Result[0]);
			row.setString("地址", Result[1]);
			row.setString("端口", Result[2]);
			dataSet.addRow(row);
		}

		if (tableName.equals("NETFLOW表")) {
			row.setString("源地址", Result[0]);
			row.setString("源端口", Result[1]);
			row.setString("目的地址", Result[2]);
			row.setString("目的端口", Result[3]);
			row.setString("协议", Result[4]);
			row.setString("流量", Result[5]);
			dataSet.addRow(row);
		}

		if (tableName.equals("IETFV6表")) {
			row.setString("接口", Result[0]);
			row.setString("接收包", Result[1]);
			row.setString("接收丢包", Result[2]);
			row.setString("接收多播包", Result[3]);
			row.setString("发送包", Result[4]);
			row.setString("发送丢包", Result[5]);
			row.setString("发送多播包", Result[6]);
			dataSet.addRow(row);
		}
		if (tableName.equals("JUNIPERV6表")) {
			row.setString("接收报文", Result[0]);
			row.setString("发送报文", Result[1]);
			dataSet.addRow(row);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void saveList(String tableName, MonitorDevice mDevice, String[] result, List list) {
		if (tableName.equals("设备接口表")) {
			MonitorInterfaceCache mic = new MonitorInterfaceCache();
			mic.setMonitorDevice(mDevice);
			mic.setPort("-");
			mic.setInterface_(result[0]);
			mic.setDescription(Tools.decodeGBK(result[1]));
			// mic.setNote(result[2]);
			list.add(mic);
		}
		if (tableName.equals("接口IP地址表")) {
			if (result[0].equals("127.0.0.1"))
				return;
			InterfaceEntry ie = new InterfaceEntry();
			ie.setIp(result[0]);
			ie.setInterface_(result[1]);
			list.add(ie);
		}
		// 2010-09-10
		if (tableName.equals("接口表")) {
			// 检查接口格式是否正确
			if (!Tools.portFormatOK(result[0]))
				return;
			InterfaceEntry ie = new InterfaceEntry();
			ie.setInterface_(result[0]);
			ie.setDescription(Tools.decodeGBK(result[1]));
			ie.setSpeed(result[2]);
			ie.setMac(result[3]);
			// 接口有三种管理状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
			if (result[4].equals("1") || result[4].equals("up(1)"))
				ie.setOpenup(true);
			else
				ie.setOpenup(false);
			// 接口有三种上线状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
			if (result[5].equals("1") || result[5].equals("up(1)"))
				ie.setOnline(true);
			else
				ie.setOnline(false);
			list.add(ie);
		}
		if (tableName.equals("端口接口对应表")) {
			// 检查端口、接口格式是否正确，同一设备不能有重复的端口/接口记录
			if (!Tools.portFormatOK(result[0]))
				return;
			if (!Tools.portFormatOK(result[1]))
				return;
			if (!JosqlUtil.conatinPortIfx(list, result[0], result[1])) {
				InterfaceEntry ie = new InterfaceEntry();
				ie.setPort(result[0]);
				ie.setInterface_(result[1]);
				// lsj 7.30 bug 没有子网列 row.setString("子网", monitorSubnet);
				list.add(ie);
			}
		}
		if (tableName.equals("路由表")) {
			RouteEntry re = new RouteEntry();
			re.setSegment(result[0]);
			re.setMask(result[4]);
			re.setInterface_(result[1]);
			re.setNextHop(result[2]);
			if (result[3].equals("3") || result[3].equals("direct(3)")) {
				re.setType("直连");
			} else {
				if (result[3].equals("4") || result[3].equals("indirect(4)"))
					re.setType("非直连");
				else
					re.setType("其它");
			}
			re.setLinkedDevice(mDevice.getIp());
			// row.setString("子网", monitorSubnet);
			// dataSet.addRow(row);
			list.add(re);
		}
		if (tableName.equals("ARP表")) {
			// 同一设备不能有重复的ARP记录
			if (JosqlUtil.containDeviceIp(list, result[2]) == null) {
				ArpEntry an = new ArpEntry();
				an.setInterface_(result[0]);
				an.setIp(result[2]);
				if (result[1].length() == 6)
					result[1] = Tools.wrongBytesToStr(result[1]);
				// 检查MAC地址格式是否合法：
				if (!Tools.macFormatOK(result[1]))
					return;
				an.setMac(result[1]);
				if (result[3].equals("1") || result[3].equals("other(1)"))
					an.setType("其它");
				if (result[3].equals("2") || result[3].equals("invalid(2)"))
					an.setType("无效");
				// 锐捷3760 2 可信
				if (result[3].equals("3") || result[3].equals("dynamic(3)"))
					an.setType("动态");
				if (result[3].equals("4") || result[3].equals("static(4)"))
					an.setType("静态");
				list.add(an);
			}
		} 
		if (tableName.equals("接口地址表")) {
			if (!Tools.portFormatOK(result[0]))
				return;
			if (!Tools.macFormatOK(result[1]))
				return;
			InterfaceEntry ie = new InterfaceEntry();
			ie.setInterface_(result[0]);
			ie.setMac(result[1]);
			list.add(ie);
//			row.setString("接口", result[0]);
//			row.setString("MAC", result[1]);
//			dataSet.addRow(row);
		}
		
		// 非常用表格
		if (tableName.equals("SFLOW表")) {
			SflowEntry se = new SflowEntry();
			se.setOwner(result[0]);
			se.setAddress(result[1]);
			se.setPort(result[2]);
			list.add(se);
		}
		if (tableName.equals("NETFLOW表")) {
			NetflowEntry ne = new NetflowEntry();
			ne.setSrcAddress(result[0]);
			ne.setSrcPort(result[1]);
			ne.setDstAddress(result[2]);
			ne.setDstPort(result[3]);
			ne.setProtocol(result[4]);
			ne.setFlow(result[5]);
			list.add(ne);
		}
		if (tableName.equals("IETFV6表")) {
			Ietfv6Entry ie = new Ietfv6Entry();
			ie.setInterface_(result[0]);
			ie.setRxReceive(result[1]);
			ie.setRxDiscard(result[2]);
			ie.setRxMulticast(result[3]);
			ie.setTxReceive(result[4]);
			ie.setTxDiscard(result[5]);
			ie.setTxMulticast(result[6]);
			list.add(ie);
		}
		if (tableName.equals("JUNIPERV6表")) {
			Juniperv6Entry je = new Juniperv6Entry();
			je.setReceive(result[0]);
			je.setTransmit(result[1]);
			list.add(je);
		}
	}

	// 将接口各项信息填入当前行。
	private void ifScanToDB(String[] Result, DataSet interfaceDataSet) {
		// 做编码转换，使采用中文编码的设备或主机的描述信息能够正确显示。但对CISCO 1900出错。
		// 发现其DESCR与IFINDEX相同。
		Result[1] = Tools.decodeGBK(Result[1]);
		// updateRow
		interfaceDataSet.setString("接口描述", Result[1]);
		interfaceDataSet.setString("接口速率", Tools.getIfSpeed(Result[2]));
		interfaceDataSet.setString("MAC", Result[3]);
		// 接口有三种管理状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
		if (Result[4].equals("1") || Result[4].equals("up(1)"))
			interfaceDataSet.setBoolean("开闭", true);
		else
			interfaceDataSet.setBoolean("开闭", false);
		// 接口有三种上线状态：UP、DOWN和TEST，在TEST模式下该接口不能使用，等同于DOWN。
		if (Result[5].equals("1") || Result[5].equals("up(1)"))
			interfaceDataSet.setBoolean("上线", true);
		else
			interfaceDataSet.setBoolean("上线", false);
	}

	public String getSysInfo(String device, String commStr, List<SysinfoEntry> seList) {
		String[] result = new String[5];
		target.setTargetHost(device);
		target.setCommunity(commStr);

		String[] OIDs = new String[5];
		// mib-2.system.sysDescr
		OIDs[0] = ".1.3.6.1.2.1.1.1";
		// mib-2.system.sysObjectID
		OIDs[1] = ".1.3.6.1.2.1.1.2";
		// mib-2.system.sysName
		OIDs[2] = ".1.3.6.1.2.1.1.5";
		// mib-2.system.sysLocation
		OIDs[3] = ".1.3.6.1.2.1.1.6";
		// mib-2.system.sysUpTime
		OIDs[4] = ".1.3.6.1.2.1.1.3";

		target.setObjectIDList(OIDs);
		result = target.snmpGetNextList();
		if (result == null) {
			display(displayStr + device + " 系统信息SNMP超时。");
			return "超时";
		}
		
		SysinfoEntry se = new SysinfoEntry();
		se.setDescription(Tools.decodeGBK(result[0]));
		se.setContact(Tools.decodeGBK(result[1]));
		se.setName(Tools.decodeGBK(result[2]));
		se.setLocation(Tools.decodeGBK(result[3]));
		se.setRuntime(result[4]);
		seList.add(se);
		display(displayStr + device + " 系统信息完毕。");
		return "OK";
	}

	// 读取设备描述及名称信息
	public String[] getDescr(String device, String commStr) {
		String[] result;
		target.setTargetHost(device);
		target.setCommunity(commStr);
		String[] OIDs = new String[2];
		// mib-2.system.sysDescr
		OIDs[0] = ".1.3.6.1.2.1.1.1.0";
		// mib-2.system.sysName
		OIDs[1] = ".1.3.6.1.2.1.1.5.0";
		target.setObjectIDList(OIDs);
		result = target.snmpGetList();

		if (result == null) {
			display(displayStr + device + " 描述名称信息SNMP超时。");
			return result;
		}
		display(displayStr + device + " 描述名称信息完毕。");
		return result;
	}

	public String getVendorOID(MonitorDevice mDevice) {
		Log logger = LogFactory.getLog(DeviceSnmpQuery.class);
		String device = mDevice.getIp();
		String commStr = mDevice.getReadCommunity();
		String result;
		target.setTargetHost(device);
		target.setCommunity(commStr);

		// system.sysObjectID
		String OID = ".1.3.6.1.2.1.1.2.0";

		target.setObjectID(OID);
//		logger.info("\n");
//		logger.info("begin getVendorOID  " + device);
//		logger.info("before target.snmpGet()  "	+ device);
		result = target.snmpGet();
		if (result == null) {
//			logger.info("result == null  " + device);
			return null;
		}
		// if( Result.length() <= 42 ) return "0.0";//返回值错误，DCS7608。
		if (result.length() <= 42 && !result.startsWith(".1.3.6.1.4.1")) {
//			logger.info("result.length() <= 42 && !Result.startsWith(.1.3.6.1.4.1)"	+ device);
//			logger.info("return 0.0  " + device);
			return "0.0";// 返回值错误，DCS7608。
		}
		if (result.startsWith(".1.3.6.1.4.1")) {
//			logger.info("result.startsWith(.1.3.6.1.4.1)  " + device);
			result = result.substring(13);
		} else {
//			logger.info("result. OK  " + device + "  " + result);
			result = result.substring(42);// 去掉.iso.org.dod.internet.private.enterprises.前缀
		}
		if (result.indexOf(".") != -1) {// SUN去掉前缀后只有OID43，所以要判断是否还有后缀
//			logger.info("result.indexOf(.) != -1   " + result);
			result = result.substring(0, result.indexOf("."));// 取得厂商OID
		}
		// 注：如果装载了CISCO-VTP/CPU-MIB，则厂商OID为cisco而不是9，因为可以将9解释为cisco
//		logger.info("result return  " + result);
		return result;
	}
	
	public String getH3cOid(MonitorDevice mDevice) {
		// rfc2737-entity.mib
		String[] oids = new String[3];
		// mib-2.entityMIB.entityMIBObjects.entityPhysical.entPhysicalTable.entPhysicalClass
		oids[0] = ".1.3.6.1.2.1.47.1.1.1.1.5";
		// mib-2.entityMIB.entityMIBObjects.entityPhysical.entPhysicalTable.entPhysicalDescr
		oids[1] = ".1.3.6.1.2.1.47.1.1.1.1.2";
		// mib-2.entityMIB.entityMIBObjects.entityPhysical.entPhysicalTable.entPhysicalName
		oids[2] = ".1.3.6.1.2.1.47.1.1.1.1.7";

		String retOid = ".1.3.6.1.4.1.25506.2.6.1.1.1.1.6.";
		
		String tableName = "设备实体表";
		int readCount = 0;
		String result[] = new String[oids.length];
		String indexOid, tempStr, oidStr;
		int indexOidLen;
		boolean firstRecord = true;

		String device = mDevice.getIp();
		String commStr = mDevice.getReadCommunity();
		target.setTargetHost(device);
		target.setCommunity(commStr);
		indexOid = oids[0];
		indexOidLen = indexOid.length();
		target.setObjectIDList(oids);

		String oid = "";
		display(displayStr + device + " " + tableName + "...");
		while (true) {
			result = target.snmpGetNextList();
			oid = target.getSnmpOID().toString();

			if (result == null) {
				display(displayStr + device + " " + tableName + "SNMP超时。");
				return "超时";
			}

			oidStr = target.getSnmpOID().toString();
			if (oidStr.length() < indexOidLen) {
				if (firstRecord) {
					display(displayStr + device + " MIB中没有" + tableName
							+ "或表空。");
					return "表空";
				} else {
					display(displayStr + device + " " + tableName + "完毕。");
					return "OK";
				}
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord) {
					display(displayStr + device + " MIB中没有" + tableName
							+ "或表空。");
					return "表空";
				} else {
					display(displayStr + device + " " + tableName + "完毕。");
					return "OK";
				}
			}

			readCount++;
			display(displayStr + device + " " + tableName + " " + readCount
					+ " 条记录。");
			firstRecord = false;

			// 这两个表使用OID，所以在这里保存
			if((result[0].equals("module(9)") || result[0].equals("9"))
					&& !result[2].contains("virtual")) {
				String nStr = oid.substring(oid.lastIndexOf(".") + 1);
				// 因为使用GetNext读取，这里把实体的N值先减1
				int nValue = 0;
				try {
					nValue = Integer.parseInt(nStr);
					if(nValue > 0)
						nStr = String.valueOf(nValue - 1);
				}catch(NumberFormatException nfe) {
				}
				retOid += nStr;
 				return retOid;
			}
			firstRecord = false;
			//return "超时";
		}// while
	}

	/**获取AKCP的温度**/
	public long getAkcpTemp(MonitorDevice mDevice) {
		String result;
		target.setTargetHost(mDevice.getIp());
		target.setCommunity(mDevice.getReadCommunity());
		//
		String OID = ".1.3.6.1.4.1.3854.1.2.2.1.16.1.3.0";

		target.setObjectID(OID);
		result = target.snmpGet();

		long now;
		try {
			now = Long.parseLong(result);
		} catch (NumberFormatException e) {
			return -1;
		}
		return now;
	}
	
	// 读指定设备指定接口的管理状态：
	public String getIFAdminStatus(String device, String commStr, String ifIndex) {
		target.setTargetHost(device);
		target.setCommunity(commStr);

		// mib-2.interfaces.ifTable.ifEntry.ifAdminStatus
		String OID = ".1.3.6.1.2.1.2.2.1.7" + "." + ifIndex;

		target.setObjectID(OID);
		String result = target.snmpGet();

		if (result == null) {
			display(displayStr + device + " 接口开闭状态SNMP超时。");
			return "超时";
		}

		if (result.equals("1") || result.equals("up(1)"))
			return "开";
		if (result.equals("2") || result.equals("down(2)"))
			return "闭";
		return "测试";
	}

	// 读指定设备接口的描述及上线状态
	public String[] getIFInfo(String device, String commStr, String ifIndex) {
		target.setTargetHost(device);
		target.setCommunity(commStr);
		String[] oids = new String[3];
		String[] result = new String[3];

		// mib-2.interfaces.ifTable.ifEntry.ifDescr
		oids[0] = ".1.3.6.1.2.1.2.2.1.2." + ifIndex;
		// mib-2.interfaces.ifTable.ifEntry.ifAdminStatus
		oids[1] = ".1.3.6.1.2.1.2.2.1.7." + ifIndex;
		// mib-2.interfaces.ifTable.ifEntry.ifOperStatus
		oids[2] = ".1.3.6.1.2.1.2.2.1.8." + ifIndex;
		target.setObjectIDList(oids);
		result = target.snmpGetList();

		if (result == null)
			return null;

		result[0] = Tools.decodeGBK(result[0]);
		// 管理状态：
		if (result[1].equals("1") || result[1].equals("up(1)"))
			result[1] = "打开";
		if (result[1].equals("2") || result[1].equals("down(2)"))
			result[1] = "关闭";
		// 操作状态：
		if (result[2].equals("1") || result[2].equals("up(1)"))
			result[2] = "上线";
		if (result[2].equals("2") || result[2].equals("down(2)"))
			result[2] = "下线";

		return result;
	}

	// 读指定设备的接口名：IF-MIB
	public String getIFName(String device, String commStr, String ifIndex) {
		target.setTargetHost(device);
		target.setCommunity(commStr);

		// .iso.org.dod.internet.mgmt.mib-2.ifMIB.ifMIBObjects.ifXTable.ifXEntry.ifName
		String oid = ".1.3.6.1.2.1.31.1.1.1.1." + ifIndex;

		target.setObjectID(oid);
		return target.snmpGet();
	}

	public String getAllIFName(MonitorDevice mDevice, List<InterfaceEntry> ieList) {
		String[] OIDs = new String[1];
		// .iso.org.dod.internet.mgmt.mib-2.ifMIB.ifMIBObjects.ifXTable.ifXEntry.ifName
		OIDs[0] = ".1.3.6.1.2.1.31.1.1.1.1";
		return getSpecifiedRows("接口名表", "接口", OIDs, mDevice, ieList);
	}

	public String getAllIFAlias(MonitorDevice mDevice, List<InterfaceEntry> ieList) {
		String[] OIDs = new String[1];
		// mib-2.ifMIB.ifMIBObjects.ifXTable.ifXEntry.ifAlias
		OIDs[0] = ".1.3.6.1.2.1.31.1.1.1.18";
		return getSpecifiedRows("接口别名表", "接口", OIDs, mDevice, ieList);
	}

	// 是否有指定的表项
	// Version:0-SnmpVersion1,1-SnmpVersion2C.
	public String hasTableEntry(String device, String commStr, String indexOid,
			int version) {
		String result = new String();
		String tempStr;
		int indexOidLen;

		target.setTargetHost(device);
		target.setCommunity(commStr);
		target.setSnmpVersion(version);

		indexOidLen = indexOid.length();
		target.setObjectID(indexOid);

		result = target.snmpGetNext();

		if (result == null)
			return "超时";

		tempStr = target.getSnmpOID().toString();
		if (tempStr.length() < indexOidLen)
			return "表空";

		tempStr = tempStr.substring(0, indexOidLen);
		if (!tempStr.equals(indexOid))
			return "表空";

		return "OK";
	}

	// 是否有指定的项目
	public String hasEntry(String device, String commStr, String oid) {
		target.setTargetHost(device);
		target.setCommunity(commStr);
		target.setObjectID(oid);

		target.snmpGet();

		int errorCode = target.getErrorCode();
		int errorIndex = target.getErrorIndex();

		if (errorCode != 0) {
			if (errorCode == 22 && errorIndex == 0)
				return "超时";
			return "不支持该特性";
		}

		return "OK";
	}

	// 将接口描述项增加到表中
	// 使用到的地方CDP、NDP、CISCOV6、ARP表、路由表
	public <T> String appendIFDescr(MonitorDevice mDevice, List<T> list) {
		TableDataSet tmpDataSet = new TableDataSet();
		String[] columnNames = new String[] { "IP", "端口", "接口", "接口描述", "备注", };
		int columnTypes[] = new int[] { Variant.STRING, Variant.STRING,
				Variant.STRING, Variant.STRING, Variant.STRING };
		tmpDataSet = DataSetTool.initDataSet(columnNames, columnTypes);
		tmpDataSet.open();
		tmpDataSet.deleteAllRows();
		List<MonitorInterfaceCache> monitorInterfaceCacheList = new ArrayList<MonitorInterfaceCache> ();
		String ret = getIFCache(mDevice, monitorInterfaceCacheList, true, false);
		if (!ret.equals("OK"))
			return ret;

		// 查找并填充接口描述
		String ifx = "";
		String desc = "";
		for(T t : list) {
			if(t instanceof ArpEntry) {
				ArpEntry an = (ArpEntry)t;
				ifx = an.getInterface_();
				desc = JosqlUtil.containInterfaceCache(monitorInterfaceCacheList, ifx);
				if(desc != null) {
					an.setDescription(desc);
				}
			}
			if(t instanceof RouteEntry) {
				RouteEntry re = (RouteEntry)t;
				ifx = re.getInterface_();
				desc = JosqlUtil.containInterfaceCache(monitorInterfaceCacheList, ifx);
				if(desc != null) {
					re.setDescription(desc);
				}
			}
		}
		return "OK";
	}

	// 读指定设备的CDP Cache表：CISCO-CDP-MIB
	public String getCdpTable(MonitorDevice mDevice, List<NeighborEntry> neList) {
		String[] OIDs = new String[2];
		// cdpCacheTable.cdpCacheEntry.cdpCacheAddress：对方地址
		OIDs[0] = ".1.3.6.1.4.1.9.9.23.1.2.1.1.4";
		// cdpCacheTable.cdpCacheEntry.cdpCacheDevicePort：对方接口描述或接口名
		OIDs[1] = ".1.3.6.1.4.1.9.9.23.1.2.1.1.7";
		return getSnmpTableList("CDP表", OIDs, mDevice, neList);
	}

	// 读指定设备的hwNDPPortNbTable:HUAWEI_HGMP_MIB
	public String getHdpTable(MonitorDevice mDevice, List<NeighborEntry> neList) {
		String[] OIDs = new String[2];
		// hwNDPPortNbDeviceId：对方MAC
		OIDs[0] = ".1.3.6.1.4.1.2011.6.7.5.6.1.1";
		// hwNDPPortNbPortName：对方接口描述
		OIDs[1] = ".1.3.6.1.4.1.2011.6.7.5.6.1.2";
		return getSnmpTableList("HDP表", OIDs, mDevice, neList);
	}

	// 读指定设备的sflow.sFlowMIB.sFlowAgent.sFlowRcvrTable
	public String getSFlow(MonitorDevice mDevice, List<SflowEntry> seList) {
		String[] OIDs = new String[3];
		// sFlowRcvrOwner：采集器
		OIDs[0] = ".1.3.6.1.4.1.14706.1.1.4.1.2";
		// sFlowRcvrAddress：地址
		OIDs[1] = ".1.3.6.1.4.1.14706.1.1.4.1.6";
		// sFlowRcvrPort：端口
		OIDs[2] = ".1.3.6.1.4.1.14706.1.1.4.1.7";

		return getSnmpTableList("SFLOW表", OIDs, mDevice, seList);
	}

	// 读指定设备的ciscoNetflowMIB.ciscoNetflowMIBObjects.cnfTopFlows.cnfTopFlowsTable
	public String getNetFlow(MonitorDevice mDevice, List<NetflowEntry> neList) {
		String[] OIDs = new String[6];
		// cnfTopFlowsSrcAddress：源地址
		OIDs[0] = ".1.3.6.1.4.1.9.9.387.1.7.8.1.3";
		// cnfTopFlowsSrcPort：源端口
		OIDs[1] = ".1.3.6.1.4.1.9.9.387.1.7.8.1.10";
		// cnfTopFlowsDstAddress：目的地址
		OIDs[2] = ".1.3.6.1.4.1.9.9.387.1.7.8.1.6";
		// cnfTopFlowsDstPort：目的端口
		OIDs[3] = ".1.3.6.1.4.1.9.9.387.1.7.8.1.11";
		// cnfTopFlowsProtocol：协议
		OIDs[4] = ".1.3.6.1.4.1.9.9.387.1.7.8.1.19";
		// cnfTopFlowsBytes：流量
		OIDs[5] = ".1.3.6.1.4.1.9.9.387.1.7.8.1.24";

		return getSnmpTableList("NETFLOW表", OIDs, mDevice, neList);
	}

	public String getIETFV6(MonitorDevice mDevice, List<Ietfv6Entry> ieList) {
		String[] OIDs = new String[7];
		// ipv6IfDescr
		OIDs[0] = ".1.3.6.1.2.1.55.1.5.1.2";
		// ipv6IfStatsInReceives
		OIDs[1] = ".1.3.6.1.2.1.55.1.6.1.1";
		// ipv6IfStatsInDiscards
		OIDs[2] = ".1.3.6.1.2.1.55.1.6.1.8";
		// ipv6IfStatsInMcastPkts
		OIDs[3] = ".1.3.6.1.2.1.55.1.6.1.19";
		// ipv6IfStatsOutForwDatagrams
		OIDs[4] = ".1.3.6.1.2.1.55.1.6.1.10";
		// ipv6IfStatsOutDiscards
		OIDs[5] = ".1.3.6.1.2.1.55.1.6.1.12";
		// ipv6IfStatsOutMcastPkts
		OIDs[6] = ".1.3.6.1.2.1.55.1.6.1.20";
		return getSnmpTableList("IETFV6表", OIDs, mDevice, ieList);
	}

	public String getCiscoV6(MonitorDevice mDevice, List<Ciscov6Entry> ceList) {
		String[] OIDs = new String[2];
		// cIpv6InterfaceEffectiveMtu
		OIDs[0] = ".1.3.6.1.4.1.9.10.86.1.2.3.1.2";
		// cIpv6InterfaceReasmMaxSize
		OIDs[1] = ".1.3.6.1.4.1.9.10.86.1.2.3.1.3";
		return getSnmpTableList("CISCOV6表", OIDs, mDevice, ceList);
	}

	public String getJuniperV6(MonitorDevice mDevice, List<Juniperv6Entry> jeList) {
		String[] OIDs = new String[2];
		// jnxIpv6StatsReceives
		OIDs[0] = ".1.3.6.1.4.1.2636.3.11.1.1.1";
		// jnxIpv6StatsRawOuts
		OIDs[1] = ".1.3.6.1.4.1.2636.3.11.1.1.16";
		return getSnmpTableList("JUNIPERV6表", OIDs, mDevice, jeList);
	}

	private void display(String str) {
		// System.out.println(str);
	}
	
	
	// CPU、流量读取相关
	/**
	 * 获取设备的CpuOid
	 * @param mDevice	设备对象
	 * @return			对应的CpuOid
	 */
	public String getVendorCpuOid(MonitorDevice mDevice) {
		String cpuOid = mDevice.getCpuOid();
		if(cpuOid != null && !"".equals(cpuOid)) {
			return cpuOid;
		}
		String vendor = getVendorOID(mDevice);
		if (vendor == null)
			return "超时";// 如果没有读出，则使用CISCO CPUOID，否则不发包响应时间为0。
		// H3C动态获取CPU Oid值
		if(vendor.equals("25506"))
			return getH3cOid(mDevice);
		MonitorVendor monitorVendor =monitorVendorService.findByVendor(vendor);
		if(monitorVendor != null)
			return monitorVendor.getCpuOid();
		else
			return ".1.3.6.1.4.1.2021.11.11.0";// 如果没有则缺省使用UCD-SNMP的，但不能为空，否则不发包响应时间为0。
	}
	
	/**
	 * 取节点的CPU和响应/运行时间，并将数据保存到节点表的相应位置。
	 * @param mDevice		设备对象
	 * @param cpuLoadOid	cpuOid值
	 * @return				获取到的CPU值，超时、无此变量或者获取结果不在1~100范围内都返回-1
	 */
	public String getCpuRunTime(MonitorDevice mDevice,String cpuLoadOid) {
		String result;
		target.setTargetHost(mDevice.getIp());
		target.setCommunity(mDevice.getReadCommunity());
		target.setObjectID(cpuLoadOid);
		// 取OID的最后一位
		String last = cpuLoadOid.substring(cpuLoadOid.lastIndexOf(".") + 1);
		if (last.equals("0")) // CPUOID为叶节点，如CISCO，使用GET方法
			result = target.snmpGet();
		// Harbor
		else if (cpuLoadOid.equals(".1.3.6.1.4.1.8212.1.1.4.1.1.4.1"))
			result = target.snmpGet();
		// H3C
		else if (cpuLoadOid.equals(".1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7"))
			result = target.snmpGet();
		else
			// 为非叶节点，取第一项，如MS，使用GETNEXT方法
			result = target.snmpGetNext();

		int errorCode = target.getErrorCode();
		int errorIndex = target.getErrorIndex();
		if (errorCode != 0) {
			if (errorCode == 22 && errorIndex == 0)
				return "-1";
			return "-1";
		}

		long now;
		try {
			now = Long.parseLong(result);
		} catch (NumberFormatException e) {
			return "-1";
		}
		
		if(cpuLoadOid.indexOf("25506") > 0 && (now < 0 || now >100)) {
			target.setObjectID(".1.3.6.1.4.1.2011.2.23.1.18.1.3.0");
			result = target.snmpGet();
			try {
				now = Long.parseLong(result);
			} catch (NumberFormatException e) {
				return "-1";
			}
		}

		// 主机UCD-MIB的CPU定义为IDLE，这里转化为BUSY。
		// private.enterprises.ucdavis.systemStats.ssCpuIdle
		if (cpuLoadOid.equals(".1.3.6.1.4.1.2021.11.11.0"))
			now = 100 - now;
		return String.valueOf(now);
	}
	
	/**
	 * 取节点的运行时间
	 * @param mDevice	设备对象
	 * @return	 String		运行时间
	 */
	public String getSysUpTime(MonitorDevice mDevice) {
		// 此处为何要单独设置超时时间？
//		target.setTimeout(1);
//		target.setRetries(0);
		target.setTargetHost(mDevice.getIp());
		target.setCommunity(mDevice.getReadCommunity());
		target.setObjectID(".1.3.6.1.2.1.1.3.0");// mib-2.system.sysUpTime
		return target.snmpGet();
	}
	
}
