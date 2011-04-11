package com.combanc.monitor.algorithm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.Locate;
import com.combanc.monitor.comet.DwrService;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSegment;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojoext.MacEntry;
import com.combanc.monitor.pojoext.PortIfxMap;
import com.combanc.monitor.quartz.ComputerScan;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorInterfaceCacheService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.JosqlUtil;

/**
 * <p>
 * Title:读取所有设备的MAC转发表
 * </p>
 */

public class MACPortScan {
	DeviceSnmpQuery deviceSnmpQuery;
	private MonitorSubnetService monitorSubnetService;
	private MonitorDeviceService monitorDeviceService;
	private MonitorLinkportService monitorLinkportService;
	private MonitorInterfaceCacheService monitorInterfaceCacheService;
	private DwrService dwrService;
	private MonitorSystemParamService monitorSystemParamService;
	
	// 是否忽略扫描异常
	private  boolean ignoreError = true;
	private  boolean l3Scan = true;
	
	
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}
	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}
	public DwrService getDwrService() {
		return dwrService;
	}
	public void setDwrService(DwrService dwrService) {
		this.dwrService = dwrService;
	}
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}
	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	
	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}
	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}

	public MonitorInterfaceCacheService getMonitorInterfaceCacheService() {
		return monitorInterfaceCacheService;
	}

	public void setMonitorInterfaceCacheService(
			MonitorInterfaceCacheService monitorInterfaceCacheService) {
		this.monitorInterfaceCacheService = monitorInterfaceCacheService;
	}

	// 子网交换设备MAC端口发现结果
	// TextDataFile aftDataFile = new TextDataFile();
	// 子网MAC转发表
	// TableDataSet subAFTDataSet = new TableDataSet();
	List<MacEntry> subMacEntryList = new ArrayList<MacEntry>();
	// 设备MAC转发表
	// TableDataSet devAFTDataSet = new TableDataSet();
	List<MacEntry> devMacEntryList = new ArrayList<MacEntry>();

	// 所有交换设备互连端口表：
	// TableDataSet linkPortDataSet = new TableDataSet();
	List<MonitorLinkport> linkportList = new ArrayList<MonitorLinkport>();
	// 设备端口接口映射表：
	// TableDataSet portMapDataSet = new TableDataSet();
	List<PortIfxMap> portIfxMapList = new ArrayList<PortIfxMap>();
	// TableDataSet portMapCache = new TableDataSet();
	List<PortIfxMap> portIfxCacheList = new ArrayList<PortIfxMap>();
	// 设备接口表：接口/接口描述
	// TableDataSet interfaceDataSet = new TableDataSet();
	List<MonitorInterfaceCache> monitorInterfaceCacheList = new ArrayList<MonitorInterfaceCache>();

	// CISCO设备VLAN表：
	// TextDataFile vlanDataFile = new TextDataFile();
	// TableDataSet ciscoVlanDataSet = new TableDataSet();
	// TableDataSet tricomVlanDataSet = new TableDataSet();
	List<String> ciscoVlanList = new ArrayList<String>();
	List<String> tricomVlanList = new ArrayList<String>();
	

	// 中途出错设备表：
	private List<MonitorDevice> errDeviceList = new ArrayList<MonitorDevice>();
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();

	// 运行信息显示条：
	String displayStr;

	String[] aftOids = new String[3];// MAC地址端口转发表
	String[] pifOids = new String[2];// 端口/接口映射表
	String[] ciscoVTPOids = new String[1];
	String[] tricomVlanOids = new String[1];
	int readCount;
	boolean checkMacDup;// 是否检查MAC地址重复
	boolean macRepeat;// MAC地址读出重复记录标志

	SnmpTarget target = new SnmpTarget();

	public MACPortScan() {
	}
	
	public MACPortScan(JLabel jLabel) {
		try {
			init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "MACPortScan,初始化错误:" + e);
		}
	}

	public void init() throws Exception {
		ignoreError = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_IGNORE_ERROR);
		MonitorSystemParam l23Scan = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_L23_SCAN);
		if("2".equals(l23Scan)){
			l3Scan = false;
		}else{
			l3Scan = true;
		}
		
//		if(interfaceDataSet.isOpen())
//			interfaceDataSet.close();
//		if(portMapDataSet.isOpen())
//			portMapDataSet.close();
//		if(subAFTDataSet.isOpen())
//			subAFTDataSet.close();
//		if(devAFTDataSet.isOpen())
//			devAFTDataSet.close();
//		if(linkPortDataSet.isOpen())
//			linkPortDataSet.close();
//		if(ciscoVlanDataSet.isOpen())
//			ciscoVlanDataSet.close();
//		if(tricomVlanDataSet.isOpen())
//			tricomVlanDataSet.close();

		// MAC地址转发表OIDS：.iso.org.dod.internet.mgmt.mib-2.dot1dBridge.dot1dTp.dot1dTpFdbTable
		aftOids[0] = ".1.3.6.1.2.1.17.4.3.1.1";// dot1dTpFdbAddress
		aftOids[1] = ".1.3.6.1.2.1.17.4.3.1.2";// dot1dTpFdbPort
		aftOids[2] = ".1.3.6.1.2.1.17.4.3.1.3";// dot1dTpFdbStatus

		// 端口接口映射表OIDS:mib-2.dot1dBridge.dot1dBase.dot1dBasePortTable
		pifOids[0] = ".1.3.6.1.2.1.17.1.4.1.1";// dot1dBasePort
		pifOids[1] = ".1.3.6.1.2.1.17.1.4.1.2";// dot1dBasePortIfIndex

		// CISCO VTP
		// OIDS:private.enterprises.cisco.ciscoMgmt.ciscoVtpMIB.vtpMIBObjects.vlanInfo.vtpVlanTable
		ciscoVTPOids[0] = ".1.3.6.1.4.1.9.9.46.1.3.1.1.2";// vtpVlanState

		// 3COM VLAN
		// OIDS:private.enterprises.a3Com.generic.genExperimental.genVirtual.a3ComVlanGroup.a3ComVlanGlobalMappingTable
		tricomVlanOids[0] = ".1.3.6.1.4.1.43.10.1.14.1.1.1.2";// a3ComVlanGlobalMappingIfIndex

		// MAC端口表.txt
//		aftDataFile.setFileName(Constants.macPortFileName);
//		aftDataFile.setLoadAsInserted(true);

//		subAFTDataSet.setDataFile(aftDataFile);
//		subAFTDataSet.open();

//		devAFTDataSet.setDataFile(aftDataFile);
//		devAFTDataSet.open();

		//lsj 此处无需查互连端口，在需要进行mac地址过滤的时候按照子网查找互连端口表
//		linkportList = monitorLinkportService.getLinkPortList();
//		LinkPortUtilities.initLinkPortTable(linkPortDataSet);
//		LinkPortUtilities.listToTable(list, linkPortDataSet);
//		if (!linkPortDataSet.isOpen())
//			linkPortDataSet.open();

//		portMapDataSet = DataSetTool.initDataSet(columnNames0, columnTypes0);
//		portMapDataSet.open();
//		portMapCache = DataSetTool.initDataSet(columnNames0, columnTypes0);
//		portMapCache.open();

		// 2010-08-20  需要按照特定子网查？？
		List<MonitorInterfaceCache> deviceInterfaceList = monitorInterfaceCacheService.findAll();
		if (!deviceInterfaceList.isEmpty()) {
			for (MonitorInterfaceCache mic : deviceInterfaceList) {
				PortIfxMap pim = new PortIfxMap();
				pim.setIp(mic.getMonitorDevice().getIp());
				pim.setPort(mic.getPort());
				pim.setInterface_(mic.getInterface_());
				pim.setDescription(mic.getDescription());
				// 2010-08-20
//				row.setString("子网", mic.getSubnet().getSubnetName());
//				row.setInt("subnetId", mic.getSubnet().getSubnetId());
				portIfxCacheList.add(pim);
			}
		}

//		String[] columnNames = new String[] { "IP", "端口", "接口", "接口描述", "备注",
//				"中文备注", "subnetId", "子网" };
//		int columnTypes[] = new int[] { Variant.STRING, Variant.STRING,
//				Variant.STRING, Variant.STRING, Variant.STRING, Variant.STRING,
//				Variant.INT, Variant.STRING };
//		interfaceDataSet = DataSetTool.initDataSet(columnNames, columnTypes);
//		interfaceDataSet.open();

//		vlanDataFile.setFileName(Constants.c1FileName);
//		vlanDataFile.setLoadAsInserted(true);
//		ciscoVlanDataSet.setDataFile(vlanDataFile);
//		ciscoVlanDataSet.open();
//
//		tricomVlanDataSet.setDataFile(vlanDataFile);
//		tricomVlanDataSet.open();

//		errDeviceList = monitorDeviceService.findAll();
//		deviceList = errDeviceList;

		target.setLoadFromCompiledMibs(true);
		target.setTimeout(MainConstants.SNMP_TIME_OUT);
		target.setRetries(MainConstants.SNMP_RETRY);
	}

	/** 扫描给定子网表对应的所有L2设备的BRIDGE-MIB中的MAC地址/端口映射表：用于只扫描交换机不扫描路由器的情况**/
	public boolean scan(List<MonitorDevice> monitordeviceList, List<MonitorSegment> monitorSegmentList,
			List<MonitorComputer> snapshotList) {
		if (monitordeviceList.isEmpty())
			return false;
//		if (deviceList.isEmpty())
//			return false;
		// 逐一处理各子网：
//		for (MonitorSubnet ms : monitorSubnetList) {
//			if (ms.getScan().equals(1)) {
				// 如果不进行三层扫描，并且设置了PING扫描，则先PING扫描，然后再扫描二层设备：
				// 如果是ARP模式扫描，则是否PING在读路由器ARP表前进行，不在这里考虑。
				if (!ComputerScan.l3Scan && ComputerScan.pingScan) {
				
					if (!Tools.pingSegment(monitorSegmentList)) {
						// 忽略PING错误的判断在TOOLS中完成。
						return false;
					}
					display("处理Ping数据，请稍候...");
					// 等待10秒，使交换机正确生成MAC端口表，确保SNMP读出正确。
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					;
				}
				display("扫描交换设备 ...");
				deviceList = monitordeviceList;
				return scanDevice( deviceList, snapshotList);
				
//			}
//		}
		
	}

	// 扫描指定子网对应的所有链路层设备/VLAN的BRIDGE-MIB中的MAC地址/端口映射表：
	public boolean scanDevice(List<MonitorDevice> deviceList, List<MonitorComputer> snapshotList) {
		
		// 设备MAC地址表、端口接口表改变标志，用来区分首次还是后来读该设备
		String ret;
		int devCount = 0;
		// DataRow row = new DataRow(errDeviceDataSet);

//		subAFTDataSet.deleteAllRows();// 子网设备端口接口表
		subMacEntryList.clear();
		errDeviceList = new ArrayList<MonitorDevice>();

		for (MonitorDevice md : deviceList) {
			// 只扫描交换和交换+路由类型的交换机
			if (md.getMonitorDeviceType().getCode().equals(2))
				continue;

			devCount++;
			//devAFTDataSet.deleteAllRows();
			devMacEntryList.clear();
			boolean MIndex = false;
			boolean PIndex = false;
			String vendorMac = "";
			if (md.getMonitorVendorMac() != null) {
				if (md.getMonitorVendorMac().getMac().length() > 0
						&& md.getMonitorVendorMac().getMac().equals("m")) {
					MIndex = true;
				} else if (md.getMonitorVendorMac().getMac().length() > 0
						&& md.getMonitorVendorMac().getMac().equals("p")) {
					PIndex = true;
				}
				// 其它，获取厂商表
				else if (md.getMonitorVendorMac().getMac().length() > 0) {
					vendorMac = md.getMonitorVendorMac().getMac().toUpperCase();
				}
			}
			ret = scanDevice(md, MIndex, PIndex, vendorMac, devCount, devMacEntryList);
			// 因读接口、端口/接口、转发表表，数据必须完整才加入。如果表空则不加入。
			if (ret.equals("OK"))
				addToSubnetAFT();
			// 注意：做扫描时设备的MAC地址转发表可以为空
			if (!ret.equals("OK") && !ret.equals("表空")) {
				// 如果第一次读不成功，则加入出错重读表中：
				errDeviceList.add(md);
			}
		}

		// 如果存在设备没有正确读出，则重读这些设备
		if (!errDeviceList.isEmpty()) {
			for (MonitorDevice md : errDeviceList) {
				devCount++;
				// devAFTDataSet.deleteAllRows();
				devMacEntryList.clear();
				boolean MIndex = false;
				boolean PIndex = false;
				String vendorMac = "";
				if (md.getMonitorVendorMac() != null) {
					if (md.getMonitorVendorMac().getMac().length() > 0
							&& md.getMonitorVendorMac().getMac().equals("m")) {
						MIndex = true;
					} else if (md.getMonitorVendorMac().getMac().length() > 0
							&& md.getMonitorVendorMac().getMac().equals("p")) {
						PIndex = true;
					}
					// 其它，获取厂商表
					else if (md.getMonitorVendorMac().getMac().length() > 0) {
						vendorMac = md.getMonitorVendorMac().getMac().toUpperCase();
					}
				}
				ret = scanDevice(md, MIndex, PIndex, vendorMac, devCount, devMacEntryList);
				if (ret.equals("OK"))
					addToSubnetAFT();
			}
		}// if

		// 把设备互连端口的MAC地址记录删除。
		filteLinkPortRecord();

		// 检查是否有重复的MAC地址。如果不忽略重复，提示设备互连端口的发现或设置有问题，
		// 并按错误返回；如果忽略，则过滤掉重复的记录，按正确返回。
		if (!checkMacDup())
			return false;
		
		// 连接包含三层信息的表和MAC/端口表
		connectDataSet( snapshotList, deviceList);
		// 除了只进行二层扫描的情况外，需删除没有上连设备、接口的记录。
		// 保证1）发现的可靠性，2）解决IP转发表、MAC转发表时间窗口不同的问题。
		if (l3Scan)
			delUnMatchRecord(snapshotList);
	
		return true;

	}

	// 将设备的MAC转发表加入到子网的MAC转发表中
	private void addToSubnetAFT() {
		if (devMacEntryList.isEmpty())
			return;
		for (MacEntry me : devMacEntryList) {
			subMacEntryList.add(me);
		}
	}

	// 1、删除交换设备AFT表中的设备互连端口记录。
	// 2、删除思科设备的Port-channel记录。CDP发现模式下，连接表中为物理端口，但MAC表
	// 中为聚合端口，为了不在连接表中再配置聚合端口，简化配置，在这里删除它们。
	private void filteLinkPortRecord() {
		if (subMacEntryList.isEmpty())
			return;
		linkportList = monitorLinkportService.findUniqueLegitimateList();
		if (linkportList.isEmpty())
			return;
		
		// Iterator<MacEntry> it = subMacEntryList.iterator();
		int count = subMacEntryList.size();
		System.out.println("before  count " + count);
		for(int i = count - 1; i >= 0;i--) {
			MacEntry me = subMacEntryList.get(i);
			if(JosqlUtil.containIpIfx(linkportList, me.getUplinkDevice(), me.getInterface_())) {
				// System.out.println("移除mac转发记录  " + me.getMac() + "   " + me.getUplinkDevice() + "  " + me.getDescription());
				subMacEntryList.remove(i);
			}
				
			else {
				String ifDescr = me.getDescription();
				// 如果接口描述中包含"Port-channel"字样则删除该记录
				if (ifDescr != null && ifDescr.regionMatches(0, "Port-channel", 0, 12)) {
					System.out.println("移除mac转发记录:原因port-channel  " + me.getUplinkDevice() + "  " + me.getDescription());
					subMacEntryList.remove(i);
				}
			}
		}
		count = subMacEntryList.size();
		System.out.println("after  count " + count);
		
		// 2010-09-10
		// 过滤port-channel
		
		/*
		DataRow loc = new DataRow(linkPortDataSet, new String[] { "IP", "接口",
				"子网名" });
		subAFTDataSet.first();
		boolean deleted = true;
		do {
			while (deleted) {
				loc.setString("IP", subAFTDataSet.getString("上连设备"));
				loc.setString("接口", subAFTDataSet.getString("接口"));
				loc.setString("子网名", subnet);
				if (linkPortDataSet.locate(loc, Locate.FIRST)) {
					if (subAFTDataSet.atLast()) {
						subAFTDataSet.deleteRow();
						break;
					} else {
						subAFTDataSet.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (subAFTDataSet.next());

		if (subAFTDataSet.isEmpty())
			return;
		String ifDescr = "";
		subAFTDataSet.first();
		deleted = true;
		do {
			while (deleted) {
				ifDescr = subAFTDataSet.getString("接口描述");
				// 如果接口描述中包含"Port-channel"字样则删除该记录
				if (ifDescr.regionMatches(0, "Port-channel", 0, 12)) {
					if (subAFTDataSet.atLast()) {
						subAFTDataSet.deleteRow();
						break;
					} else {
						subAFTDataSet.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (subAFTDataSet.next());
		*/
	}//

	// 检查是否有重复的MAC地址。首先收集不重复的MAC地址集合，再比较源MAC地址集合。
	// 如果相等表示没有重复的MAC地址。如果不等，则存在。如果不忽略错误则将当前结果
	// 保存在快照表中返回失败，使后续处理终止。如果忽略错误则删除同一MAC出现在不同
	// 端口的所有记录。
	private boolean checkMacDup() {
		if (subMacEntryList.isEmpty())
			return true;
		// 获得子网不重复的MAC地址集合，保存在tmpDataSet中。
		List<String> macList = new ArrayList<String>();
		int macCount = 0;
		for(MacEntry me : subMacEntryList) {
			if(!macList.contains(me.getMac())) {
				macCount++;
				macList.add(me.getMac());
			}
		}
		// 如果没有重复MAC地址则返回正确，继续后续处理。
		if (macCount == subMacEntryList.size())
			return true;

		// 将当前MAC端口发现的重复MAC情况复制到错误表中供分析。
		List<MacEntry> errMacEntryList = new ArrayList<MacEntry>();
		List<String> errList = new ArrayList<String>();
		errMacEntryList.clear();

		for(String mac : macList) {
			List<MacEntry> retList = JosqlUtil.findMacEntry(subMacEntryList, mac);
			// 如果有两条以上记录，则把它们都加入errMacEntryList中，此list只是为调试使用，无实际意义
			// 而errList记录出现两次以上的mac地址，方便后面对subMacEntryList进行过滤，去除errList中出现的mac
			if(retList != null && retList.size() > 1) {
				errList.add(mac);
				for(MacEntry me : retList)
					errMacEntryList.add(me);
			}
		}

		// 如果不忽略错误：
		if (!ignoreError) {
			// 保存以便查看，结果保存在当前快照文件中
			// lsj 2009-08-28 已经不存在快照表，需要修改
			/*
			 * try { errorDataFile.save(errorDataSet); } catch (Exception e) { }
			 */
			JOptionPane.showMessageDialog(null,
					"扫描异常：发现相同MAC地址出现在不同端口，详情见接入快照。\n"
							+ "如果遗漏了交换机互连端口，请在互连端口表中补充设置。\n"
							+ "如果是同一计算机刚从一个端口移动到另一个端口，请5分钟后再进行扫描。");
			return false;
		}

		// 如果忽略错误，则删除相同MAC地址出现在不同端口的所有记录：
		if (errMacEntryList.isEmpty())
			return true;
		for(MacEntry me : errMacEntryList) {
			System.out.println("重复mac出现在不同端口   " + me.getMac() + "  " + me.getInterface_() + "   " + me.getUplinkDevice());
		}
		
		// 对subMacEntryList进行过滤，去除errList中出现的mac
		int count = subMacEntryList.size();
		for(int i = count - 1; i >= 0;i--) {
			if(errList.contains(subMacEntryList.get(i).getMac()))
				subMacEntryList.remove(i);
		}
		return true;
	}

	// 在子网范围内，将快照表与MAC端口表连接，使用MAC/子网对进行定位。
	private void connectDataSet(List<MonitorComputer> snapshotList, List<MonitorDevice> deviceList) {
		if (subMacEntryList.isEmpty())
			return;
		// 进行三层扫描的情况下，无论SNMP+WINS还是WINS，都把发现的上连设备和端口信息更新
		// 到快照中。如果不进行三层扫描，只进行二层扫描，则排除重复的结果。
		if (l3Scan) {
			if (snapshotList.isEmpty())
				return;
			for(MonitorComputer ms : snapshotList) {
//				if (!currentDataSet.getString("子网").equals(subnet))
//					continue;
				List<MacEntry> retList = JosqlUtil.findMacEntry(subMacEntryList, ms.getMac());
				if (retList != null && retList.size() > 0) {
					MacEntry me = retList.get(0);
					// updateRow
					ms.setMonitorDevice(JosqlUtil.findDeviceByIp(deviceList, me.getUplinkDevice()));
					ms.setDevicePort(me.getPort());
					ms.setDeviceInterface(me.getInterface_());
					ms.setInterfaceDescription(me.getDescription());
				}
			}
		}// if

		// 如果不做第三层扫描，只做第二层扫描，则把子网MAC地址表拷贝到快照
		if (!l3Scan) {
			for(MacEntry me : subMacEntryList) {
				MonitorComputer ms = new MonitorComputer();
				ms.setIp("-");
				ms.setMac(me.getMac());
				ms.setMonitorDevice(JosqlUtil.findDeviceByIp(deviceList, me.getUplinkDevice()));
				ms.setDevicePort(me.getPort());
				ms.setDeviceInterface(me.getInterface_());
				ms.setInterfaceDescription(me.getDescription());
				ms.setHostName("-");
				ms.setComputerName("-");
				ms.setDomain("-");
				ms.setLoginName("-");
				ms.setDiscoveryTime(new Timestamp(System.currentTimeMillis()));
				snapshotList.add(ms);
			}
		}// if
	}

	// 将没有发现上连设备/端口的不完全匹配记录删除。
	// 以交换机的时间窗为主，只保留发现了上连设备/端口
	private void delUnMatchRecord( List<MonitorComputer> snapshotList) {
		if (snapshotList.isEmpty())
			return;
		int count = snapshotList.size();
		for (int i = count - 1; i >= 0; i--) {
			MonitorComputer ms = snapshotList.get(i);
			if (ms.getMonitorDevice() == null || ms.getDevicePort().equals("-"))
				snapshotList.remove(i);
		}
	}

	// --------------------------------- 设备扫描 ----------------------------------

	// 扫描指定设备的BRIDGE-MIB中的MAC地址转发/端口映射表
	public String scanDevice(MonitorDevice mDevice, boolean MIndex,
			boolean PIndex, String nt2, int devCount, List<MacEntry> devMacEntryList) {
		String commStr = mDevice.getReadCommunity();
		String nt1 = mDevice.getNote1();

		String pre, pos, ciscoRet, tricomRet, ret, note2 = "";
		// 初始化该设备的MAC转发表、端口接口表、接口描述表
		// ?? 2010-08-20  此处的devMacEntryList为传入参数，而portIfxMapList为全局list ??
		devMacEntryList.clear();
		portIfxMapList.clear();
//		portMapDataSet.open();
//		portMapDataSet.deleteAllRows();
		
		// 备注2转化为大写
		if (nt2 != null && !nt2.equals(""))
			note2 = nt2.toUpperCase();

		// 设置显示字符串，为0表示只查询一个设备，不是扫描子网中的某个设备
		if (devCount > 0)
			displayStr = "读 第 " + devCount + " 台" + " 交换设备 "
					+ mDevice.getIp();
		else
			displayStr = "读交换设备 " + mDevice.getIp();

		// 一、MINDEX优先级1。按普通方法读，先读端口/接口对应表，再读MAC转发表。划分VLAN情况下允许相同MAC地址出现在不同端口。
		if (!MIndex) {
			// （一）、首先用CACHE方法读端口/接口对应表
			ret = readCache("端口接口对应表", pifOids, mDevice.getIp(), commStr, 
					devCount, portIfxMapList);
			if (!ret.equals("OK"))
				return ret;
			// 使用DB OID初始化。一考虑一般设备，二防止误配置跳过指定型号而出错。
			String tmpOids[] = new String[2];
			tmpOids[0] = ".1.3.6.1.2.1.17.4.3.1.1";// dot1dTpFdbAddress
			tmpOids[1] = ".1.3.6.1.2.1.17.4.3.1.2";// dot1dTpFdbPort

			// （二）、Q桥优先级2。按Q-BRIDGE方式读
			if (PIndex) {
				// qBridgeMIB.qBridgeMIBObjects.dot1qTp.dot1qTpFdbTable
				tmpOids[0] = ".1.3.6.1.2.1.17.7.1.2.2.1.2";// Port，MAC在返回OID中
				tmpOids[1] = ".1.3.6.1.2.1.17.7.1.2.2.1.3";// Status
				ret = readTableList("QB表", tmpOids, mDevice.getIp(), commStr, 
						devCount, devMacEntryList);
				if (!ret.equals("OK"))
					return ret;
				// （三）、备注2有特殊读法标记优先级3。
			} else {
				boolean readDBridge = true;// 缺省读BRIDGE-MIB中的数据
				int i = note2.indexOf(".");
				if (i != -1 && i > 0) {
					pre = note2.substring(0, i);// 格式：RG.1926F+
					pos = note2.substring(i + 1);
					// （1）RG：私有MIB
					if (pre.equals("RG")) {
						// 1、RG802.1x
						if (pos.equals("1924F+")) {// 在V2.3/V2.41上测试无此OID
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.3.1.20.1.1.3";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.3.1.20.1.1.2";// Port
						}
						if (pos.equals("2024M")) {// 在堆叠的V2.41上测试无此OID。
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.8.1.20.1.1.3";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.8.1.20.1.1.2";// Port
						}
						if (pos.equals("1926G+")) {// 在V2.0上测试OK
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.9.1.20.1.1.3";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.9.1.20.1.1.2";// Port
						}
						if (pos.equals("1926F+")) {// 在V2.1上测试OK。
							// s1926fPlusMib.s1926fPlusObjects.s1926fPlusAuthUserInfo.s1926fPlusAuthUserTable
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.12.1.20.1.1.3";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.12.1.20.1.1.2";// Port
						}
						if (pos.equals("2024E")) {// 未测试，但2024EMIB表明应该OK。
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.17.1.20.1.1.3";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.17.1.20.1.1.2";// Port
						}
						// 2126/50G-START-AAA-MIB
						if (pos.equals("2126G") || pos.equals("2150G") || pos.equals("2150")) {// 测试OK
							// switchMib.startMgmt.startAAAMIB.startAAAMIBObjects.startAuthUserObjects.startAuthUserTable
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.10.2.19.1.3.2.1.2";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.10.2.19.1.3.2.1.6";// Port
						}
						// 2、RG2126/2150端口绑定，START-SECURITY-MIB
						if (pos.equals("BD")) {
							// startMgmt.startSecurityMIB.startSecurityMIBObjects.startSecurityAddressObjects.startSecurityAddressTable
							tmpOids[0] = ".1.3.6.1.4.1.4881.1.1.10.2.6.1.2.1.1.2";// Address
							tmpOids[1] = ".1.3.6.1.4.1.4881.1.1.10.2.6.1.2.1.1.3";// Port
						}
						ret = readTableList("RG表", tmpOids, mDevice.getIp(), commStr, devCount, devMacEntryList);
						// 支持1X但无用户在线时2126/50返回空，1926F+返回不支持，这时不返回错误而转读DBRIDGE。超时返回错误。
						if (ret.equals("超时"))
							return ret;
						readDBridge = true;// 读取D-Bridge获得未起特性端口上的数据，保证数据完整性。
					}// RG
					// （2）GW：私有MIB，FlexHammer24及BigHammer6806不支持。
					/*
					if (pre.equals("GW")) {
						tmpOids = new String[3];
						// harbourAgent.harbourFdb.harbourAdvancedFDBGroup.harbourAdvancedFDBTable作为港湾默认
						tmpOids[0] = ".1.3.6.1.4.1.8212.1.12.2.1.1.2";// Address
						tmpOids[1] = ".1.3.6.1.4.1.8212.1.12.2.1.1.3";// Port
						tmpOids[2] = ".1.3.6.1.4.1.8212.1.12.2.1.1.4";// Status

						if (pos.equals("1")) {
							// 端口返回解释方法1
							ret = readTable("GW.1表", tmpOids, mDevice.getIp(), commStr,
									subnet, devCount, macDataSet);
						} else if (pos.equals("2")) {
							// 端口返回解释方法2
							ret = readTable("GW.2表", tmpOids, mDevice.getIp(), commStr,
									subnet, devCount, macDataSet);
						} else {// U2,U24
							// harbourAgent.harbourFdb.harbourBaseFDBGroup.harbourBaseFDBTable使用BASE表不是ADV表！
							tmpOids[0] = ".1.3.6.1.4.1.8212.1.12.1.2.1.1";// Address
							tmpOids[1] = ".1.3.6.1.4.1.8212.1.12.1.2.1.3";// Port
							tmpOids[2] = ".1.3.6.1.4.1.8212.1.12.1.2.1.4";// Status
							// 端口返回解释方法2
							ret = readTable("GW.3表", tmpOids, mDevice.getIp(), commStr,
									subnet, devCount, macDataSet);
						}
						if (!ret.equals("OK"))
							return ret;
						readDBridge = false;// 已读港湾私有MIB。其BRIDGE-MIB空或数据不全，不需读取。
					}// GW
					// (3)HW：不读DB表的STATUS项
					if (pre.equals("HW")) {
						tmpOids[0] = ".1.3.6.1.2.1.17.4.3.1.1";// dot1dTpFdbAddress
						tmpOids[1] = ".1.3.6.1.2.1.17.4.3.1.2";// dot1dTpFdbPort
						ret = readTable("HW表", tmpOids, mDevice.getIp(), commStr,
								subnet, devCount, macDataSet);
						if (!ret.equals("OK"))
							return ret;
						readDBridge = false;// 需要再读DB以提高速度。
					}// HW
					// (4)中兴2826：只读一项数据可以得到全表，但读两项或三项则得不到全表。表中有自身的端口/状态记录。
					// 这里读端口项，从返回OID中解析出MAC（使用与QB相同的方法）。由于表示自身的端口记录不在端口/接口映射
					// 表中，所以被过滤掉，不会出现在最后的结果中。
					if (pre.equals("ZX")) {
						tmpOids = new String[1];
						tmpOids[0] = ".1.3.6.1.2.1.17.4.3.1.2";// dot1dTpFdbPort
						ret = readTable("ZX表", tmpOids, mDevice.getIp(), commStr,
								subnet, devCount, macDataSet);
						if (!ret.equals("OK"))
							return ret;
						readDBridge = false;// 需要再读DB以提高速度。
					}// ZX
					*/
				}// 如果备注2有内容
				// 普通读取优先级最低4。没有备注的直接读MAC转发表。有备注的，已按私有MIB读完，这里根据需要读DBridge读，保证数据完整。
				
				if (readDBridge) {
					// 非INDEXING方式下，相同端口/MAC地址不能重复出现,否则MIB实现有问题，如华为、实达老版本读上万条不完。
					checkMacDup = true;
					ret = readTableList("MAC地址转发表", aftOids, mDevice.getIp(), commStr, devCount, devMacEntryList);
					if (!ret.equals("OK"))
						return ret;
				}
				System.out.println(mDevice.getIp() + "  MAC转发记录数目    " + devMacEntryList.size());
				for(MacEntry mac : devMacEntryList) {
					// System.out.println(mDevice.getIp() + "   " + mac.getMac());
				}
				
			}// PIndex else
		}// !MIndex

		// 如果以COMMUNITY INDEX方式读MAC转发表和端口/接口表
		if (MIndex) {
			// 如果支持CISCO VTPMIB并且VLAN表空则读取VLAN表，同一交换域中CISCO设备VLAN表相同
			// ciscoVlanDataSet.deleteAllRows();
			ciscoVlanList.clear();
			ciscoRet = readTableList("CISCO VLAN表", ciscoVTPOids, mDevice.getIp(), commStr,
					 devCount, ciscoVlanList);
			// 如果超时则无法判断设备只否支持INDEX，返回错误
			if (ciscoRet.equals("超时"))
				return ciscoRet;

			// ---------------------------如果支持3COM VLAN
			// MIB则读取VLAN表，同一交换域中3COM设备的VLAN表不同
			// tricomVlanDataSet.deleteAllRows();
			tricomVlanList.clear();
			tricomRet = readTableList("3COM VLAN表", tricomVlanOids, mDevice.getIp(),
					commStr, devCount, tricomVlanList);
			if (tricomRet.equals("超时"))
				return tricomRet;
			// 如果不是支持VLAN读取的CISCO或3COM设备则返回不支持，表明配置错误。
			if (!ciscoRet.equals("OK") && !tricomRet.equals("OK"))
				return "不支持";

			// --------------------------以Community Indexing方式读设备
			// 为CISCO设备：
			if (ciscoRet.equals("OK")) {
				//ciscoVlanDataSet.first();
				//do {
				for(String tmp : ciscoVlanList) {
					// tmp = ciscoVlanDataSet.getString("值");
					// 设置显示字符串
					if (devCount > 0)
						displayStr = "读第 " + devCount + " 台"
								+ " 交换设备 " + mDevice.getIp() + " VLAN " + tmp;
					else
						displayStr = "读交换设备 " + mDevice.getIp() + " VLAN " + tmp;

					// 某些型号的CISCO设备VLAN1002-1005（留给FDDI、TOKENRING等，类似于VLAN1的特殊VLAN）存在
					// 但读取超时。
					if (tmp.equals("1002") || tmp.equals("1003")
							|| tmp.equals("1004") || tmp.equals("1005"))
						continue;

					tmp = commStr + "@" + tmp;
					// 按VLAN读，相同端口/MAC地址（如网关）可以出现在不同VLAN中，允许端/口MAC重复出现；
					checkMacDup = false;
					ret = readTableList("MAC地址转发表", aftOids, mDevice.getIp(), tmp,
							devCount, devMacEntryList);
					// OK或表空时继续，可能某个VLAN的数据为空。2820等可读VLAN但不支持INDEXING的交换机返回超时。
					if (ret.equals("超时"))
						return ret;
					// 注意：按VLAN直接读端口接口对应表实例，而不是通过端口接口CACHE表读取，这样2900/3500端口接口表会被读很多次。
					ret = readTableList("端口接口对应表", pifOids, mDevice.getIp(), tmp, 
							devCount, portIfxMapList);
					// OK或表空时继续，可能某个VLAN的数据为空。2820等可读VLAN但不支持INDEXING的交换机返回超时。
					if (ret.equals("超时"))
						return ret;
					// if(ret.equals("表空")) continue;
				} //while (ciscoVlanDataSet.next());
			}// CISCO

			// 为3COM设备
			if (tricomRet.equals("OK")) {
				// tricomVlanDataSet.first();
				// do {
				for(String tmp : tricomVlanList) {
					// tmp = tricomVlanDataSet.getString("值");
					// 注1：3COM SuperStack
					// 3用Community可以读出全部AFT数据，使用VLAN号只读出某个VLAN的数据，
					// 在INDEX模式下，除VLAN1外的数据将被读两遍。
					// 注2：SuperStack II用Community只能读出VLAN1的数据，需要读所有VLAN才能得到全部数据。
					// 注3：3COM的设备管理IP地址只能配置在VLAN1中。但访问该设备IP的SNMP应答包虽然通过级连口
					// 发往网关，但访问设备IP的SNMP流量不一定使网关MAC地址出现在AFT表；但该设备用户IP访问网
					// 关时的流量却使网关MAC地址出现在AFT中。一个例子是清华的166.111.2.249。这是3COM的一个BUG。
					// 设置显示字符串
					if (devCount > 0)
						displayStr = "读第 " + devCount + " 台"
								+ " 交换设备 " + mDevice.getIp() + " VLAN " + tmp;
					else
						displayStr = "读交换设备 " + mDevice.getIp() + " VLAN " + tmp;

					// 3COM设备可能使用到大于1000的VLAN号，如166.111.94.34/cErnEt。
					tmp = commStr + "@" + tmp;
					// 按VLAN读，相同端口/MAC地址（如网关）可以出现在不同VLAN中，允许端/口MAC重复出现；
					checkMacDup = false;
					ret = readTableList("MAC地址转发表", aftOids, mDevice.getIp(), tmp, 
							devCount, devMacEntryList);
					// 如果超时则认为不支持Community Indexing，当然这样判断可能不准确，但支持却超时的概率不大。
					// 这时只有不用Community Indexing方法读出的数据。这时因超时会出现较长时间的等待。
					// OK或表空时继续，可能某个VLAN的数据为空。3900等可读VLAN但不支持INDEXING的交换机返回超时。
					if (ret.equals("超时"))
						return ret;
				} // while (tricomVlanDataSet.next());

				// 3COM的端口/接口影射不INDEXING，以CACHE方式读取。
				ret = readCache("端口接口对应表", pifOids, mDevice.getIp(), commStr, 
						devCount, portIfxMapList);
				if (!ret.equals("OK"))
					return ret;
			}// 3COM

		}// Community Indexing

		// 为读接口CACHE表设置显示字符串，表示是在读子网的某个设备
		if (devCount > 0)
			deviceSnmpQuery.setDisplayStr("读第 " + devCount
					+ " 台" + " 交换设备 ");
		// 2010-08-20
		// System.out.println("subnetId   " + subnetId);
		ret = deviceSnmpQuery.getIFCache(mDevice, monitorInterfaceCacheList, true, false);
		ret = "OK";
		if (!ret.equals("OK")) {
			return ret;
		}

		// 将接口、接口描述填充到表中：
		fillInterfaceField(devMacEntryList, note2);
		// 删除无接口项的不完整记录
		filteNoIFRecord(devMacEntryList);
	
		return "OK";
	}

	private String readTable(String tableName, String[] OIDs, String device,
			String commStr, int devCount, DataSet dataSet) {
		String[] result = new String[3];
		String indexOid, oidStr, tempStr;
		int indexOidLen;
		boolean firstRecord = true;
		int errorCode, errorIndex;
		String errorStr;
		String retStr = "";

		target.setTargetHost(device);
		target.setCommunity(commStr);
		target.setObjectIDList(OIDs);

		readCount = 0;
		macRepeat = false;

		indexOid = OIDs[0];
		indexOidLen = indexOid.length();

		while (true) {
			result = target.snmpGetNextList();

			errorCode = target.getErrorCode();
			errorIndex = target.getErrorIndex();
			errorStr = target.getErrorString();

			if (errorCode != 0) {
				if (errorCode == 2 && errorIndex == 1) {// MIB中无此变量
					errorStr = displayStr + " " + tableName + " 不支持。";
					retStr = "不支持";
				}

				if (errorCode == 22 && errorIndex == 0) {// 超时
					errorStr = displayStr + " " + tableName + " 超时。";
					retStr = "超时";
				}

				display(errorStr);
				return retStr;
			}

			oidStr = target.getSnmpOID().toString();

			if (oidStr.length() < indexOidLen) {
				if (firstRecord)
					return "表空";
				else
					return "OK";
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord)
					return "表空";
				else
					return "OK";
			}

			readCount++;
			display(displayStr + " " + tableName + " " + readCount + " 条记录。");
			restoreDataSet(tableName, result, oidStr, device,  dataSet);
			// DB读出相同端口相同MAC记录时不再继续读，按正确返回。
			if (tableName.equals("MAC地址转发表") && checkMacDup && macRepeat)
				return "OK";
			firstRecord = false;
		}// while
	}
	
	private String readTableList(String tableName, String[] OIDs, String device,
			String commStr, int devCount, List list) {
		String[] result = new String[3];
		String indexOid, oidStr, tempStr;
		int indexOidLen;
		boolean firstRecord = true;
		int errorCode, errorIndex;
		String errorStr;
		String retStr = "";

		target.setTargetHost(device);
		target.setCommunity(commStr);
		target.setObjectIDList(OIDs);

		readCount = 0;
		macRepeat = false;

		indexOid = OIDs[0];
		indexOidLen = indexOid.length();

		while (true) {
			result = target.snmpGetNextList();
			
			errorCode = target.getErrorCode();
			errorIndex = target.getErrorIndex();
			errorStr = target.getErrorString();

			if (errorCode != 0) {
				if (errorCode == 2 && errorIndex == 1) {// MIB中无此变量
					errorStr = displayStr + " " + tableName + " 不支持。";
					retStr = "不支持";
				}

				if (errorCode == 22 && errorIndex == 0) {// 超时
					errorStr = displayStr + " " + tableName + " 超时。";
					retStr = "超时";
				}

				display(errorStr);
				return retStr;
			}

			oidStr = target.getSnmpOID().toString();

			if (oidStr.length() < indexOidLen) {
				if (firstRecord)
					return "表空";
				else
					return "OK";
			}

			tempStr = oidStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord)
					return "表空";
				else
					return "OK";
			}

			readCount++;
			// restoreDataSet(tableName, result, oidStr, device, subnet, devMacEntryList);
			// saveList(tableName, result, oidStr, device, subnet, devMacEntryList);
			 saveList(tableName, result, oidStr, device,  list);
			// DB读出相同端口相同MAC记录时不再继续读，按正确返回。
			if (tableName.equals("MAC地址转发表") && checkMacDup && macRepeat)
				return "OK";
			firstRecord = false;
		}// while
	}

	private void restoreDataSet(String tableName, String[] result,
			String oidStr, String device, DataSet dataSet) {
		DataRow row = new DataRow(dataSet);

		if (tableName.equals("MAC地址转发表")) {
			// result[0]：MAC，result[1]：端口，result[2]：状态
			// 只考虑学习(3)和管理类型(5)的记录，不考虑无效(invalid2)、自身(self4)和其它类型(other1)的MAC地址。
			if (result[2].equals("1") || result[2].equals("other(1)"))
				return;
			if (result[2].equals("2") || result[2].equals("invalid(2)"))
				return;
			if (result[2].equals("4") || result[2].equals("self(4)"))
				return;
			// 针对AVAYA880将"000aeb201405"格式的MAC地址做格式转化
			if (result[0] != null && result[0].length() == 12)
				result[0] = Tools.avayaMacFormat(result[0]);
			// 检查MAC地址是否合法，AVAYAP130上出现地址为空的情况。
			if (!Tools.macFormatOK(result[0]))
				return;
			// 检查端口是否合法，EXTREME SUMMIT上出现非整数端口情况
			if (!Tools.portFormatOK(result[1]))
				return;

			row.setString("MAC", result[0]);
			row.setString("端口", result[1]);
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}
		if (tableName.equals("RG表")) {
			row.setString("MAC", result[0]);
			row.setString("端口", result[1]);
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}
		if (tableName.equals("GW.1表")) {
			// harbourAdvancedFDBEntry表类型：Dynamic(1),Permanent(2),SystemL3Permanent(3)。过滤掉类型为3的数据。
			if (result[2].equals("3"))
				return;
			row.setString("MAC", result[0]);
			// 港湾：HammerOS Version 2.2(Build 00063)on uHammer2948，以及5610。
			// 将以空格分割字节的4字节16进制字符串转化为10进制字符串，获得端口号，并与端口接口表匹配。
			row.setString("端口", Tools.getGWPort1(result[1]));
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}
		if (tableName.equals("GW.2表")) {
			// harbourAdvancedFDBEntry表类型：Dynamic(1),Permanent(2),SystemL3Permanent(3)。
			// harbourBaseFDBEntry表类型：Aged(1),Permanent(2),SendToCPU(3),MultiPermanent(4),Temp(5)。
			// 无论BASE/ADV都过滤掉类型为3的数据。BASE的4、5类型实测中没有出现。
			if (result[2].equals("3"))
				return;
			row.setString("MAC", result[0]);
			// 港湾：uHammer2824、5010。将以空格分割字节的4字节16进制的置位字符串转化为端口号。
			row.setString("端口", Tools.getGWPort2(result[1]));
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}
		// HW的DB特殊读法：STATUS始终为learned。不与考虑，否则只能读到第一口的数据。
		if (tableName.equals("HW表")) {
			row.setString("MAC", result[0]);
			row.setString("端口", result[1]);
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}
		if (tableName.equals("QB表")) {// MAC地址在返回OID中，这样读有最好的通用性。并且HW只能这样读。
			// 注意：没有按其类型other(1),invalid(2),learned(3),self(4),mgmt(5)进行过滤，测试的设备（HW、RG）
			// 数据全部为类型3。
			row.setString("MAC", Tools.getQBMac(oidStr));
			row.setString("端口", result[0]);
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}
		if (tableName.equals("ZX表")) {// MAC地址在返回OID中，同QB。
			row.setString("MAC", Tools.getQBMac(oidStr));
			row.setString("端口", result[0]);
			row.setString("接口", "-");
			row.setString("接口描述", "-");
			row.setString("上连设备", device);
		}

		// Cisco VTP-MIB，得到VLAN号
		if (tableName.equals("CISCO VLAN表")) {
			if (!result[0].equals("1") && !result[0].equals("operational(1)"))
				return;
			row.setString("值", oidStr.substring(oidStr.lastIndexOf(".") + 1));
		}
		// 3Com VLAN-MIB，得到VLAN号
		if (tableName.equals("3COM VLAN表")) {
			row.setString("值", oidStr.substring(oidStr.lastIndexOf(".") + 1));
		}
		if (tableName.equals("端口接口对应表")) {
			// 检查端口、接口格式是否正确
			if (!Tools.portFormatOK(result[0]))
				return;
			if (!Tools.portFormatOK(result[1]))
				return;
			row.setString("IP", device);
			row.setString("端口", result[0]);
			row.setString("接口", result[1]);
			row.setString("接口描述", "-");
		}

		// 将记录加入表中，并避免记录重复。
		if (!dataSet.locate(row, Locate.FIRST))
			dataSet.addRow(row);
		else if (tableName.equals("MAC地址转发表"))
			macRepeat = true;
	}
	
	@SuppressWarnings("unchecked")
	private  void saveList(String tableName, String[] result,
			String oidStr, String device, List list) {
			// List<MacEntry> devMacEntryList) {
		MacEntry me = new MacEntry();
		
		if (tableName.equals("MAC地址转发表")) {
			// result[0]：MAC，result[1]：端口，result[2]：状态
			// 只考虑学习(3)和管理类型(5)的记录，不考虑无效(invalid2)、自身(self4)和其它类型(other1)的MAC地址。
			if (result[2].equals("1") || result[2].equals("other(1)"))
				return;
			if (result[2].equals("2") || result[2].equals("invalid(2)"))
				return;
			if (result[2].equals("4") || result[2].equals("self(4)"))
				return;
			// 针对AVAYA880将"000aeb201405"格式的MAC地址做格式转化
			if (result[0] != null && result[0].length() == 12)
				result[0] = Tools.avayaMacFormat(result[0]);
			// 检查MAC地址是否合法，AVAYAP130上出现地址为空的情况。
			if (!Tools.macFormatOK(result[0]))
				return;
			// 检查端口是否合法，EXTREME SUMMIT上出现非整数端口情况
			if (!Tools.portFormatOK(result[1]))
				return;

			me.setMac(result[0]);
			me.setPort(result[1]);
			me.setInterface_("-");
			me.setDescription("-");
			me.setUplinkDevice(device);
		}
		if (tableName.equals("RG表")) {
			me.setMac(result[0]);
			me.setPort(result[1]);
			me.setInterface_("-");
			me.setDescription("-");
			me.setUplinkDevice(device);
		}
		
		// MAC转发表之外的表的存储
		// Cisco VTP-MIB，得到VLAN号
		if (tableName.equals("CISCO VLAN表")) {
			if (!result[0].equals("1") && !result[0].equals("operational(1)"))
				return;
			list.add(oidStr.substring(oidStr.lastIndexOf(".") + 1));
			return;
			// row.setString("值", oidStr.substring(oidStr.lastIndexOf(".") + 1));
		}
		// 3Com VLAN-MIB，得到VLAN号
		if (tableName.equals("3COM VLAN表")) {
			list.add(oidStr.substring(oidStr.lastIndexOf(".") + 1));
			return;
			// row.setString("值", oidStr.substring(oidStr.lastIndexOf(".") + 1));
		}
		if (tableName.equals("端口接口对应表")) {
			// 检查端口、接口格式是否正确
			if (!Tools.portFormatOK(result[0]))
				return;
			if (!Tools.portFormatOK(result[1]))
				return;
			PortIfxMap pim = new PortIfxMap();
			pim.setIp(device);
			pim.setPort(result[0]);
			pim.setInterface_(result[1]);
			pim.setDescription("-");
			// row.setString("子网", subnet);
			list.add(pim);
			return;
		}

		
		// 将记录加入表中，并避免记录重复。
		if(!list.contains(me)) {
			list.add(me);
		}
		else if (tableName.equals("MAC地址转发表"))
			macRepeat = true;
		/*			 
		if(me.getUplinkDevice().equals("192.168.1.1")) {
			System.out.println("list add :      " + me.getMac() + "  " + me.getPort() +  "  " + me.getUplinkDevice());
			for(int i = 0;i<1;i++) {
				MacEntry metest = new MacEntry();
				metest.setMac("00 17 61 7c 0f 81");
				metest.setPort("2");
				metest.setUplinkDevice("192.168.1.1");
				// if(JosqlUtil.containMacEntry(metest, list))
				if(list.contains(metest))
					System.out.println("contains   " + i + "   " + metest.getMac());
			}
		}
		*/
	}

	// 填写AFT表的接口、接口描述信息
	private void fillInterfaceField(List<MacEntry> devMacEntryList, String note2) {
		if (devMacEntryList.isEmpty())
			return;
		if (portIfxMapList.isEmpty())
			return;
		if (monitorInterfaceCacheList.isEmpty())
			return;

		for(MacEntry me : devMacEntryList) {
			// 填写接口项：
			if (note2.equals("ALCATEL")) {// 返回的端口值是接口值，设置接口项。
				me.setInterface_(me.getPort());
			} else {
				// loc.setString("端口", macDataSet.getString("端口"));
				String ifx = JosqlUtil.findIfxByPort(portIfxMapList, me.getPort());
				if(ifx != null && !"".equals(ifx)) {
					me.setInterface_(ifx);
				}
			}
			// 填写接口描述项
			String desc = JosqlUtil.containInterfaceCache(monitorInterfaceCacheList, me.getInterface_());
			if(desc != null) {
				me.setDescription(desc);
			}
		}
		/*
		DataRow loc = new DataRow(portMapDataSet, "端口");
		
		macDataSet.first();
		do {
			// 填写接口项：
			if (note2.equals("ALCATEL")) {// 返回的端口值是接口值，设置接口项。
				macDataSet.setString("接口", macDataSet.getString("端口"));
			} else {
				loc.setString("端口", macDataSet.getString("端口"));
				if (portMapDataSet.locate(loc, Locate.FIRST)) {
					macDataSet.setString("接口", portMapDataSet.getString("接口"));
				}
			}
			// 填写接口描述项
			String desc = JosqlUtil.containInterfaceCache(monitorInterfaceCacheList, macDataSet.getString("接口"));
			if(desc != null) {
				macDataSet.setString("接口描述", desc);
			}
		} while (macDataSet.next());
		*/
	}

	// CACHE方式读端口/接口映射表
	private String readCache(String tableName, String[] OIDs, String device,
			String commStr,  int devCount, List<PortIfxMap> portIfxMapList) {
		String ret;
		
		// 如果该设备的端口接口映射已在CACHE表中，则从CACHE中读取
		List<PortIfxMap> list = JosqlUtil.containIp(portIfxCacheList, device);
		// Cache中查到的添加到端口接口对应表里
		if(list != null || list.size() > 0) {
			for(PortIfxMap pim : list) {
				portIfxMapList.add(pim);
			} 
		}
		
		// 如果CACHE中没有则实际读取
		ret = readTableList(tableName, OIDs, device, commStr, devCount,	portIfxMapList);
		if (!ret.equals("OK"))
			return ret;

		// 将结果保存到端口/接口映射CACHE表中
/*		DataRow rw = new DataRow(portMapCache);
		dataSet.first();
		do {
			rw.setString("IP", device);
			rw.setString("端口", dataSet.getString("端口"));
			rw.setString("接口", dataSet.getString("接口"));
			rw.setString("接口描述", "-");
			rw.setString("子网", subnet);
			if (!portMapCache.locate(rw, Locate.FIRST))
				portMapCache.addRow(rw);
		} while (dataSet.next());
		// 保存CACHE表：
		try {
			// lsj 2009-08-28 need to modify
			// portMapDataFile.save(portMapCache);
		} catch (Exception e) {
		}
*/	
		return "OK";
	}// readCache()

	// 删除设备AFT表中无接口项的记录。无接口的可能原因：端口接口表读取不完整，或读出了
	// 端口号不在端口接口映射范围的记录，如BAY450T。
	private void filteNoIFRecord(List<MacEntry> devMacEntryList) {
		if (devMacEntryList.isEmpty())
			return;
		// 2010-08-20
		int count =  devMacEntryList.size();
		for(int i = count - 1;i >= 0;i--) {
			String ifx = devMacEntryList.get(i).getInterface_();
			if(ifx == null || "-".equals(ifx))
				devMacEntryList.remove(i);
		}
	/*
		boolean deleted = true;
		macDataSet.first();
		do {
			while (deleted) {
				// 如果不在端口接口映射表中不存在该端口则删除：
				if (macDataSet.getString("接口").equals("-")) {
					if (macDataSet.atLast()) {
						macDataSet.deleteRow();
						break;
					} else {
						macDataSet.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (macDataSet.next());
		*/
	}//

	private void display(String str) {
		dwrService.displayInfo(str);
		// System.out.println(str);
	}
}
