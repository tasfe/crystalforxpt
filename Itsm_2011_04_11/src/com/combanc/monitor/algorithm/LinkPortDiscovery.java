package com.combanc.monitor.algorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

import org.directwebremoting.WebContextFactory;

import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.Locate;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.combanc.monitor.comet.DwrService;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSegment;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojoext.ArpEntry;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.IpMacEntry;
import com.combanc.monitor.pojoext.MacEntry;
import com.combanc.monitor.pojoext.MonitorLinkportExt;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.util.FileUtils;
import com.combanc.monitor.util.JosqlUtil;
import com.combanc.monitor.util.LinkPortUtilities;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * <p>
 * Title:互连端口发现算法
 * </p>
 * 
 * @author lsj
 * @version 2.0
 */

public class LinkPortDiscovery {//extends Thread { //implements Runnable{
	
	private MonitorSubnetService monitorSubnetService;
	private MonitorLinkportService monitorLinkportService;
	private MonitorDeviceService monitorDeviceService;
	private List<MonitorDevice> deviceList;
	private List<MonitorDevice> errorDeviceList;
	private List<ArpEntry> arpEntryList = new ArrayList<ArpEntry>();
	private DwrService dwrService;
	
	// 子网交换机AFT表
	List<MacEntry> devMacEntryList = new ArrayList<MacEntry>();
	List<MacEntry> subMacEntryList = new ArrayList<MacEntry>();
	// TextDataFile macPortDataFile = new TextDataFile();
	// TableDataSet subAFTDataSet = new TableDataSet();
	// TableDataSet devAFTDataSet = new TableDataSet();
	
	// 三层设备接口表
//	TextDataFile interfaceDataFile = new TextDataFile();
//	TableDataSet interfaceDataSet = new TableDataSet();
	List<InterfaceEntry> interfaceEntryList = new ArrayList<InterfaceEntry>();
	
	// 监控站、所有路由、交换设备接口MAC地址表
//	TextDataFile deviceMACDataFile = new TextDataFile();
//	TableDataSet deviceMACDataSet = new TableDataSet();
	List<IpMacEntry> ipMacEntryList = new ArrayList<IpMacEntry>();

	// 全局连接端口表：IP、端口、接口、接口描述、下连IP、下连端口、下连接口、下连接口描述、子网
	// TableDataSet linkPortDataSet = new TableDataSet();
	List<MonitorLinkport> linkportList = new ArrayList<MonitorLinkport>();
	// 子网连接端口表1
	// TableDataSet linkPortDataSet1 = new TableDataSet();
	List<MonitorLinkportExt> linkportList1 = new ArrayList<MonitorLinkportExt>();
	// 子网端口简单连接表：该端口简单连接的其它设备
	// TableDataSet linkPortDataSet2 = new TableDataSet();
	List<MonitorLinkportExt> linkportList2 = new ArrayList<MonitorLinkportExt>();
	// 子网中间结果表3
	// TableDataSet linkPortDataSet3 = new TableDataSet();
	List<MonitorLinkport> linkportList3 = new ArrayList<MonitorLinkport>();
	// 监控站与设备共网段时位于监控站与三层设备之间直线上所有交换设备的监控站方向端口
	// TableDataSet linkPortDataSet4 = new TableDataSet();
	List<MonitorLinkportExt> linkportList4 = new ArrayList<MonitorLinkportExt>();
	// 三层交换机路由直连表5
	// TableDataSet linkPortDataSet5 = new TableDataSet();
	List<MonitorLinkport> linkportList5 = new ArrayList<MonitorLinkport>();

	// 路由设备IP转发表：
	//TextDataFile ipMacDataFile = new TextDataFile();
	//TableDataSet ipMacDataSet = new TableDataSet();
	
	// 互连端口表对应的子网
	MonitorSubnet monitorSubnet;

	HashSet routers = new HashSet();// 子网中的路由设备
	boolean localAsRouter = false;// 监控站作为路由器标志，表示为单网段无路由设备网络
	String core;// 多三层设备时的拓扑中心设备
	int directNum = 0;// 监控站不通过路由可直达的二层交换机数
	HashSet directSW = new HashSet();// 监控站可直达的二层交换机集合；
	ArrayList pathSW = new ArrayList();// 监控站到三层交换中心路径上的逆序设备集合
	ArrayList c006SW = new ArrayList();// 同一设备双地址的交换模块地址
	ArrayList c006RT = new ArrayList();// 同一设备双地址的路由模块地址
	int noLinkPortDevCount;// 未发现互连端口设备计数

	String seeLocalPort = new String();// 设备看到监控站的端口
	String seeLocalPortIfx = new String();// 设备看到监控站的接口
	String seeLocalPortIfd = new String();// 设备看到监控站的接口的描述
	HashSet seeRouterPorts = new HashSet();// 设备看到三层交换机的端口的集合

	MACPortScan macPortScan;
	L3TopDiscovery l3TopDiscovery;

	DeviceSnmpQuery deviceSnmpQuery;
	
	// JNI调用
	// GetLocalMac gmac;// = new GetLocalMac();

	public LinkPortDiscovery() {
	}
	
	
	public DwrService getDwrService() {
		return dwrService;
	}


	public void setDwrService(DwrService dwrService) {
		this.dwrService = dwrService;
	}


	public List<ArpEntry> getArpEntryList() {
		return arpEntryList;
	}
	public void setArpEntryList(List<ArpEntry> arpEntryList) {
		this.arpEntryList = arpEntryList;
	}
	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}
	public void setMonitorLinkportService(MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}
	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}
	public void setmonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}
	public MonitorDeviceService getmonitorDeviceService() {
		return monitorDeviceService;
	}
	public MACPortScan getMacPortScan() {
		return macPortScan;
	}
	public void setMacPortScan(MACPortScan macPortScan) {
		this.macPortScan = macPortScan;
	}
	public L3TopDiscovery getL3TopDiscovery() {
		return l3TopDiscovery;
	}
	public void setL3TopDiscovery(L3TopDiscovery l3TopDiscovery) {
		this.l3TopDiscovery = l3TopDiscovery;
	}
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}
	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}
	
	/**
	 * @param monitorSubnet
	 * @param core	有多台三层设备时，所指定的核心设备
	 * @throws Exception synchronized throws Exception 
	 */
	public  void init(MonitorSubnet monitorSubnet, String core) {
		// gmac = new GetLocalMac();
		this.monitorSubnet = monitorSubnet;
		this.core = core;
		
		linkportList.clear();
		arpEntryList.clear();
		linkportList3.clear();
		linkportList4.clear();
		
		try {
			macPortScan.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2010-08-20
		// gmac = new GetLocalMac();
		deviceList = monitorSubnetService.getSelectedEffectiveDeviceBySubnet(monitorSubnet.getId());
		errorDeviceList = deviceList;

//		String path = FileUtils.getClassPath(this.getClass()) + "\\com\\combanc\\monitor\\txtdata\\";
//		File f = new File(path + String.valueOf(monitorSubnet.getId()));
//		if(!f.exists()) 
//			f.mkdirs();
//		String tpath = path + String.valueOf(monitorSubnet.getId()) + "\\";
//		FileUtils.copyFilesByType("txt", path, tpath);
//		FileUtils.copyFilesByType("schema", path, tpath);
//		path = tpath;
		// System.out.println(tpath);
		
		// macPortDataFile.setFileName(path + "MAC端口表.txt");
		// System.out.println(Constants.macPortFileName);
		// macPortDataFile.setFileName(Constants.macPortFileName);
		// macPortDataFile.setLoadAsInserted(true);
		
//		subAFTDataSet.setDataFile(macPortDataFile);
//		subAFTDataSet.open();
//		subAFTDataSet.deleteAllRows();

//		devAFTDataSet.setDataFile(macPortDataFile);
//		devAFTDataSet.open();
//		devAFTDataSet.deleteAllRows();

		//interfaceDataFile.setFileName(Constants.interfaceFileName);
//		interfaceDataFile.setFileName(path + "接口表.txt");
//		interfaceDataFile.setLoadAsInserted(true);
//		interfaceDataSet.setDataFile(interfaceDataFile);
//		interfaceDataSet.open();

		//deviceMACDataFile.setFileName(Constants.devMacFileName);
//		deviceMACDataFile.setFileName(path + "设备MAC表.txt");
//		deviceMACDataFile.setLoadAsInserted(true);
//		deviceMACDataSet.setDataFile(deviceMACDataFile);
//		deviceMACDataSet.open();
//		deviceMACDataSet.deleteAllRows();

		// 给linkPortDataSet添加固有字段
//		LinkPortUtilities.initLinkPortTable(linkPortDataSet);
//		linkPortDataSet.open();
//		linkPortDataSet.deleteAllRows();
		

//		Column column1 = new Column();
//		column1.setColumnName("类型");
//		column1.setDataType(com.borland.dx.dataset.Variant.STRING);
//		Column column2 = new Column();
//		column2.setColumnName("方向");
//		column2.setDataType(com.borland.dx.dataset.Variant.STRING);
//		LinkPortUtilities.initLinkPortTable(linkPortDataSet1);
//		// linkPortDataSet1.setColumns(new Column[] { column1, column2 });// 类型、方向
//		linkPortDataSet1.addColumn(column1);
//		linkPortDataSet1.addColumn(column2);
//		linkPortDataSet1.open();

//		Column column3 = new Column();
//		column3.setColumnName("类型");
//		column3.setDataType(com.borland.dx.dataset.Variant.STRING);
//		Column column4 = new Column();
//		column4.setColumnName("方向");
//		column4.setDataType(com.borland.dx.dataset.Variant.STRING);
//		LinkPortUtilities.initLinkPortTable(linkPortDataSet2);
//		// linkPortDataSet2.setColumns(new Column[] { column3, column4 });// 类型、方向
//		linkPortDataSet2.addColumn(column3);
//		linkPortDataSet2.addColumn(column4);
//		linkPortDataSet2.open();

//		LinkPortUtilities.initLinkPortTable(linkPortDataSet3);
//		linkPortDataSet3.open();
//		linkPortDataSet3.deleteAllRows();
		

//		Column column5 = new Column();
//		column5.setColumnName("所见数");
//		column5.setDataType(com.borland.dx.dataset.Variant.INT);

		// LinkPortUtilities.initLinkPortTable(linkPortDataSet4);
		// linkPortDataSet4.setColumns(new Column[] { column5 });// 所见数
//		linkPortDataSet4.addColumn(column5);
//		linkPortDataSet4.open();
//		linkPortDataSet4.deleteAllRows();
		
		//ipMacDataFile.setFileName(Constants.arpFileName);
//		ipMacDataFile.setFileName(path + "IP转发表.txt");
//		ipMacDataFile.setLoadAsInserted(true);
//		ipMacDataSet.setDataFile(ipMacDataFile);
//		Column column6 = new Column();
//		column6.setColumnName("上连设备");
//		column6.setDataType(com.borland.dx.dataset.Variant.STRING);
		// ipMacDataSet.setColumns(new Column[] { column6 });
		// 注意这里的ipMacDataSet是从文件load的列，中间过程可能保存了，
		// 多次调用discovery的时候，会有列冲突的错误，需要进行判断
//		if(ipMacDataSet.hasColumn("上连设备") == null)
//			ipMacDataSet.addColumn(column6);
//		ipMacDataSet.open();
//		ipMacDataSet.deleteAllRows();
	}

	// 扫描指定子网对应的所有链路层设备/VLAN的BRIDGE-MIB中的MAC地址/端口映射表： synchronized
	public  synchronized String run() {
		String info = "";
		if (discovery()) {
			// 显示互连端口发现情况
			if (noLinkPortDevCount == 0)
				info = "拓扑发现结束，正打开拓扑图，可以进行进一步修改。";
			else
				info = "拓扑发现结束，有 " + noLinkPortDevCount
						+ "台设备未发现互连端口。请重新发现或使用“设备互连端口”查询功能补充设置。";
			// 删除原拓扑图
			File f = new File(monitorSubnet.getName() + "拓扑图.tpg");
			if (f.exists())
				f.delete();
			
			
		}
		 return info;
	}// run

	public boolean discovery() {
		initDiscovery();

		// 交换设备MAC地址取得：读取子网路由设备的IP转发表，获得设备的MAC地址
		if (!getSWMacRouter())
			return false;

		getSWMacSwitch();

		// 路由设备接口MAC地址取得：直接读取路由设备接口表中的MAC地址
		if (!getRouterMac())
			return false;

		// 扫描子网交换设备获得所有MAC转发表信息：
		if (!scanSwitchs())
			return false;

		display("发现交换设备互连端口，请稍候...");
		// 如果某个端口包含某设备的一个MAC地址，则该端口为互连端口，并且简单连接该设备。
		// 产生互连端口表和简单连接表：
		findLinkPort();

		// 当网络不是单网段监控站路由、监控站与部分或全部交换设备同网段时监控站与路由器
		// 之间的交换设备可能因监控站方向的简单连接关系不完整而不收敛，这时补充这一段设备的
		// 简单连接关系使之完整
		if (directNum > 0 && !localAsRouter) {
			display("补充互连端口简单连接关系，请稍候...");
			appendSimpleLink();
		}

		// 取得互连端口的设备类型：交换或交换+路由
		getTable1PortType();
		// 根据二层交换机是否看到中心三层交换机为所有连接端口设置方向：向心或离心
		getTable1PortDirection();
		// 填写简单连接表的类型和方向
		fillTable2PortTD();

		// 保存简单连接表：调试和分析异常
		// 2010-08-20
//		try {
//			TextDataFile tmp = new TextDataFile();
//			tmp.setFileName(monitorSubnet.getName() + "简单连接表.txt");
//			tmp.save(linkPortDataSet2);
//		} catch (Exception e) {
//		}

		// 发现交换拓扑：
		display("计算交换设备端口互连关系，请稍候...");
		findL2Top();

		if (routers.size() > 1)
			findL3Top();
		// 将保存在表3中的发现结果存到连接端口表中
		copyResult();

		// 将数据记录TableDataSet转为list
		System.out.println("linkportList.size()  >>>>>>>>>>>" + linkportList.size());
		monitorLinkportService.saveLinkPortList(monitorSubnet.getId(), linkportList);
		
		// 检查是否每个二层交换设备都发现了互连端口
		haveLinkPort(linkportList);

		// 保存各表：
		// 2010-08-20
//		try {
//			deviceMACDataFile.save(deviceMACDataSet);
//			macPortDataFile.save(subAFTDataSet);
//		} catch (Exception e) {
//		}
		
		return true;
	}

	private void initDiscovery() {
		ipMacEntryList.clear();
		// deviceMACDataSet.deleteAllRows();// testpoint
		
		// 将路由、交换+路由类型设备加入路由器集合，将双地址设备加入列表，设置有关标志
		// 4006路由模块备注1填“交换模块”，备注2填交换模块IP地址
		
		for (MonitorDevice md : deviceList) {
			// 2010-10-30 测试一，测试IP接口状态的读取
//			interfaceEntryList.clear();
//			deviceSnmpQuery.getIFStatus(md, interfaceEntryList);
//			for(InterfaceEntry ie : interfaceEntryList) {
//				System.out.println(ie.getIp() + "   " + ie.getMac());
//			}
			// 2010-10-30 测试二，测试 端口接口对应表的读取
//			interfaceEntryList.clear();
//			deviceSnmpQuery.getPortStatus(md, false, interfaceEntryList);
//			for(InterfaceEntry ie : interfaceEntryList) {
//				System.out.println(md.getIp() + "   " + ie.getMac()+ "   " + ie.getPort()
//						+ "   " + ie.getInterface_()+ "   " + ie.getDescription() + " openup ==  " + ie.isOpenup() 
//						+ " online == " + ie.isOnline() );
//			}
			
			// 2010-10-30 测试三，测试 路由表的读取
//			List<RouteEntry> reList = new ArrayList<RouteEntry>();
//			reList.clear();
//			deviceSnmpQuery.getRouterTable(md, reList);
//			for(RouteEntry re : reList) {
//				System.out.println(md.getIp() + "   " + re.getSegment()+ "   " + re.getMask()
//						+ "   " + re.getInterface_() + "   " + re.getDescription() + " 类型 == <" + re.getType() 
//						+ ">   直连路由设备 == " + re.getLinkedDevice() );
//			}
			
			// 2010-10-30 测试四，测试 NDP表的读取 测试设备 202.113.138.195   tjufe
//			List<NeighborEntry> neList = new ArrayList<NeighborEntry>();
//			neList.clear();
//			deviceSnmpQuery.getHdpTable(md, neList);
//			for(NeighborEntry ne : neList) {
//				System.out.println(md.getIp() + "   " + ne.getInterface_()+ "   " + ne.getDescription()
//						+ "   " + ne.getDownIp() + "   " + ne.getDownDescription());
//			}
			
			if (md.getMonitorDeviceType().getCode() != MainConstants.DEVICE_SWITCH) {
				routers.add(md.getIp());
				if (md.getMonitorDeviceType().getCode() == MainConstants.DEVICE_ROUTER && md.getNote1() != null
						&& md.getNote1().equals("交换模块")) {
					c006SW.add(md.getNote2());// 如果类型为路由并且备注2不空
					c006RT.add(md.getIp());
				}
				if (md.getIp().equals("localhost"))
					localAsRouter = true;
			}
		}

		// 判断监控站是否与部分或全部交换设备在同一网段，产生测试流量，生成监控站ARP表
		//System.out.println("begin testDevices  " + monitorSubnet.getId());
		testDevices();
		//System.out.println("testDevices  " + monitorSubnet.getId());
		// 读取监控站IP转发表，这样当部分或全部设备与监控站在同一网段时能够正确获得这
		// 些设备的IP/MAC地址。如果监控工作站与任何一个设备不在相同网段，则其IP转发表可能
		// 空，所以不判断返回状态。
		// 2010-08-20 暂时注释掉本地mac读取，jni调用有问题
		// 2010-09-10 jni调用有问题，尝试改用cmd命令行读取，具体的环境中可能存在问题
		try {
			CmdTools.getLocalArp(arpEntryList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(ArpEntry ae : arpEntryList) {
//			System.out.println(ae.getIp() + "  " + ae.getMac());
//		}
		/*
		int tempIndex;
		String tempEntry;
		System.out.println(gmac.toString());
		int tempNumEntries = gmac.getDwNumEntries();
		for (int i = 0; i < tempNumEntries; i++) {
			ArpEntry ae = new ArpEntry();
			tempEntry = gmac.getPhysAddr(i);
			tempIndex = tempEntry.indexOf(' ');
			ae.setIp(tempEntry.substring(0, tempIndex));
			System.out.println("JNI 调用   " + ae.getIp());
			ae.setMac(tempEntry.substring(tempIndex + 1));
			ae.setUplinkDevice("localhost");
			arpEntryList.add(ae);
		}
		gmac.deleteDynamicArp(tempNumEntries);
		*/
		
		// 通过判断本机ARP表中是否出现交换机IP地址来判断监控站是否与交换机处于相同网段，
		// 如果处于相同网段，则需要使用监控站MAC地址来发现同网段交换机的互连端口。
		if (arpEntryList.isEmpty())
			return;

		// 将监控站ARP表中出现的交换类型设备加到监控站直达设备集合中
		for (MonitorDevice md : deviceList) {
			if (md.getMonitorDeviceType().getCode() != MainConstants.DEVICE_SWITCH)
				continue;
			if (JosqlUtil.containDeviceIp(arpEntryList,  md.getIp()) != null) {
				directNum++;
				directSW.add(md.getIp());
			}
		}
	}

	// 扫描所有二、三层交换机
	private boolean scanSwitchs() {
		int devCount = 0;
		String ret;
		// subAFTDataSet.deleteAllRows();
		subMacEntryList.clear();
		
		
		List<MonitorSubnet> list = monitorSubnetService.getAll();
		// 2010-09-10
		DataSet currentDataSet = null;
		List<MonitorSegment> monitorSegmentList = null;
		// macPortScan.scan(list, monitorSegmentList, currentDataSet);
		// macPortScan.scanSubnet("12345", 59, deviceList, currentDataSet);
		
		errorDeviceList = new ArrayList<MonitorDevice>();
		for (MonitorDevice md : deviceList) {
			if (md.getMonitorDeviceType().getCode() == MainConstants.DEVICE_ROUTER)
				continue;
			devCount++;
			// 测试子网中各设备状态，并确保产生监控站、网关到各交换设备的有效通讯，确保在读取
			// 该设备AFT表时设备的级连口已学习到监控站、网关或交换设备的MAC地址。
			testDevices();
			// devAFTDataSet.deleteAllRows();
			devMacEntryList.clear();
			boolean MIndex = false;
			boolean PIndex = false;
			String note2 = "";
			if (md.getMonitorVendorMac() != null) {
				if (md.getMonitorVendorMac().getMac().length() > 0
						&& md.getMonitorVendorMac().getMac().equals("m")) {
					MIndex = true;
				}
				if (md.getMonitorVendorMac().getMac().length() > 0
						&& md.getMonitorVendorMac().getMac().equals("p")) {
					PIndex = true;
				}
				if (md.getMonitorVendorMac().getMac().length() > 0
						&& !md.getMonitorVendorMac().getMac().equals("m")
						&& !md.getMonitorVendorMac().getMac().equals("p")) {
					note2 = md.getMonitorVendorMac().getMac().toUpperCase();
				}
			}

			ret = macPortScan.scanDevice(md, MIndex, PIndex, note2,
					devCount, devMacEntryList);

			// 如果第一次读不成功则加入出错设备表，第一次扫描结束后重读一遍。
			// 注意：做发现时设备的MAC地址转发表不得为空，否则无法发现该设备的级连口
			if (ret.equals("OK")) {
				addToSubnetAFT();
			} else {
				errorDeviceList.add(md);
			}
		}

		// 重读第一次没有读出来的设备：
		if (!errorDeviceList.isEmpty()) {
			for (MonitorDevice md : errorDeviceList) {
				devCount++;
				testDevices();
				// devAFTDataSet.deleteAllRows();
				devMacEntryList.clear();
				boolean MIndex = false;
				boolean PIndex = false;
				String note2 = "";
				if (md.getMonitorVendorMac() != null) {
					if (md.getMonitorVendorMac().getMac().length() > 0
							&& md.getMonitorVendorMac().getMac().equals("m")) {
						MIndex = true;
					}
					if (md.getMonitorVendorMac().getMac().length() > 0
							&& md.getMonitorVendorMac().getMac().equals("p")) {
						PIndex = true;
					}
					if (md.getMonitorVendorMac().getMac().length() > 0
							&& !md.getMonitorVendorMac().getMac().equals("m")
							&& !md.getMonitorVendorMac().getMac().equals("p")) {
						note2 = md.getMonitorVendorMac().getMac().toUpperCase();
					}
				}
				ret = macPortScan.scanDevice(md,
						MIndex,// MIndex
						PIndex,// PIndex
						note2,
						devCount,
						devMacEntryList);
				if (ret.equals("OK")) {
					addToSubnetAFT();
				} else {
					// 如果第二次仍然没有读出，则提示用户是否继续：
					/*int confirm = JOptionPane.showConfirmDialog(null, "读子网 "
							+ monitorSubnet.getName() + " 交换设备 " + md.getIp() + "MAC地址转发表"
							+ ret + "。\n" + "是否继续发现？如果继续可能因此异常导致发现结果不完整。",
							"发现异常", JOptionPane.YES_NO_OPTION);
					if (confirm != JOptionPane.YES_OPTION)
						return false;*/
					return true;
				}
			}
		}// if
		return true;
	}// scanSwitchs

	// 将设备AFT表中的MAC/端口信息加入子网AFT表。
	private void addToSubnetAFT() {
		if(devMacEntryList.isEmpty())
			return;
		for(MacEntry me : devMacEntryList) {
			subMacEntryList.add(me);
		}
	/*
		if (devAFTDataSet.isEmpty())
			return;
		DataRow row = new DataRow(subAFTDataSet);

		devAFTDataSet.first();
		do {
			// 将设备AFT加入子网MAC转发表：
			row.setString("MAC", devAFTDataSet.getString("MAC"));
			row.setString("端口", devAFTDataSet.getString("端口"));
			row.setString("接口", devAFTDataSet.getString("接口"));
			row.setString("接口描述", devAFTDataSet.getString("接口描述"));
			row.setString("上连设备", devAFTDataSet.getString("上连设备"));
			subAFTDataSet.addRow(row);
		} while (devAFTDataSet.next());
	*/
	}

	// 测试子网中各交换设备SNMP是否工作正常，检查交换设备AFT表是否空，产生发现互连端口所需的流量。
	private void testDevices() {
		String oid = ".1.3.6.1.2.1.1.1.0";// mib-2.system.sysDescr
		String ret;
		int devCount = 0;
		// 记录当前行指针，确保调用返回时行指针的正确位置
		for (MonitorDevice md : deviceList) {
			devCount++;
			ret = deviceSnmpQuery
					.hasEntry(md.getIp(), md.getReadCommunity(), oid);
			display("测试子网 " + monitorSubnet.getName() + " 第 " + devCount + " 台" + " 设备 "
					+ md.getIp() + "  " + ret + "。");
		}
		// 等待1秒确保设备生成CACHE记录
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		;
	}

	// 从目标子网中所有三层设备的IP转发表中获得交换设备的IP/MAC
	private boolean getSWMacRouter() {
		int devCount = 0;
		String ret;
		// 产生测试流量，确保路由设备/监控站IP转发表中生成交换设备IP地址的记录
		testDevices();
		for (MonitorDevice md : deviceList) {
			if (md.getMonitorDeviceType().getCode() == MainConstants.DEVICE_SWITCH)
				continue;
			devCount++;
			deviceSnmpQuery.setDisplayStr("读子网 " + monitorSubnet.getName() + " 第 "
					+ devCount + " 台路由设备 ");
			// 注：在同一自治域中，一个IP地址只能出现在一个路由设备的IP转发表中，该路由设备
			// 的IP转发表中只有该IP地址的一条记录。读设备（包括localhost）IP转发表时已做了IP地址重复检查。
			ret = deviceSnmpQuery.getIPForward(md, arpEntryList);
			System.out.println("arpEntry.size =====  " + arpEntryList.size()); 
			if (!ret.equals("OK")) {
				System.out.println("读子网 " + monitorSubnet.getName() + " 路由设备 "
						+ md.getIp() + " IP地址转发表" + ret + "。\n"
						+ "是否继续发现？如果继续可能因此异常导致发现结果不完整。");
				// return false;
			}
			// 为ARP表标注上连设备
			for(ArpEntry ae : arpEntryList) {
				if(ae.getUplinkDevice() == null || "".equals(ae.getUplinkDevice())) {
					ae.setUplinkDevice(md.getIp());
				}
			}
		}

		// 将目标子网中网络设备的IP/MAC地址增加到设备MAC地址表中：
		for(MonitorDevice md : deviceList) {
			ArpEntry ae = JosqlUtil.containDeviceIp(arpEntryList, md.getIp());
			if(ae != null) {
				IpMacEntry ime = new IpMacEntry();
				ime.setIp(ae.getIp());
				ime.setMac(ae.getMac());
				if(!JosqlUtil.containIpMac(ipMacEntryList, ime))
					ipMacEntryList.add(ime);
			}
		}
		
		/*
		DataRow loc = new DataRow(ipMacDataSet, "IP");
		DataRow row1 = new DataRow(deviceMACDataSet);
		for (MonitorDevice md : deviceList) {
			loc.setString("IP", md.getIp());
			if (ipMacDataSet.locate(loc, Locate.FIRST)) {
				row1.setString("IP", ipMacDataSet.getString("IP"));
				row1.setString("MAC", ipMacDataSet.getString("MAC"));
				row1.setString("子网", monitorSubnet.getName());
				if (!deviceMACDataSet.locate(row1, Locate.FIRST))
					deviceMACDataSet.addRow(row1);
			}
		}
		*/
		return true;
	}

	// 从目标子网交换设备的IP接口状态表中获得其IP/MAC，作为不能从三层设备获得设备IP/MAC
	// 的补充，如三层设备为HW8016、ENTERSYS N7，以及无路由简单网络。
	private void getSWMacSwitch() {
		int devCount = 0;
		String curDev, ret;
		for (MonitorDevice md : deviceList) {
			devCount++;
			deviceSnmpQuery.setDisplayStr("读子网 " + monitorSubnet.getName() + " 第 "
					+ devCount + " 台交换设备 ");
			// interfaceDataSet.deleteAllRows();
			interfaceEntryList.clear();
			curDev = md.getIp();
			// 读IP接口状态：
			ret = deviceSnmpQuery.getIFStatus(md, interfaceEntryList);
			if (!ret.equals("OK"))
				continue;
			// 将交换设备的IP/MAC地址增加到设备MAC地址表中：
			for(InterfaceEntry ie : interfaceEntryList) {
				// 过滤掉MAC地址空的记录
				if ("".equals(ie.getMac()))
					continue;
				// 过滤掉IP地址不同的记录
				if (!curDev.equals(ie.getIp()))
					continue;
				IpMacEntry ime = new IpMacEntry();
				ime.setIp(curDev);
				ime.setMac(ie.getMac());
				// row.setString("子网", monitorSubnet.getName());
				if(!JosqlUtil.containIpMac(ipMacEntryList, ime))
					ipMacEntryList.add(ime);
			}
		}
//		for(IpMacEntry ime : ipMacEntryList)
//			System.out.println(ime.getIp() + "   " + ime.getMac());
	}

	// 读监控站和子网路由设备的MAC地址
	private boolean getRouterMac() {
		String ret;
		int devCount = 0;
		// 如果监控站与某些或全部交换设备在同一网段，则需要使用监控站MAC地址来发现相同网段设备
		// 的互连端口。在监控站不与任何交换机同网段的情况下如果使用监控站MAC地址来发现互连
		// 端口，会出现判断直连监控站端口为互连端口的错误。
		// 2010-08-20
//		if (directNum > 0 && !localAsRouter)
//			addLocalMAC();

		for (MonitorDevice md : deviceList) {
			if (md.getMonitorDeviceType().getCode() == MainConstants.DEVICE_SWITCH)
				continue;
			// 读设备接口MAC地址
			devCount++;
			ret = getDeviceMAC(md, monitorSubnet.getName(), devCount);

			if (!ret.equals("OK")) {
				/*int confirm = JOptionPane.showConfirmDialog(null, "读子网 "
						+ monitorSubnet.getName() + " 路由设备 " + md.getIp() + " 接口地址表" + ret
						+ "。\n" + "是否继续发现？如果继续可能因此异常导致发现结果不完整。", "发现异常",
						JOptionPane.YES_NO_OPTION);
				if (confirm != JOptionPane.YES_OPTION)
					return false;*/
				return true;
			}
		}
		return true;
	}
/*
	// JNI调用 GetLocalMac 把网管站的所有mac地址加入到deviceMACDataSet里面
	private void addLocalMAC() {
		String[] macs = gmac.getMacs();
		DataRow localRow = new DataRow(deviceMACDataSet);
		for (int i = 0; i < macs.length; i++) {
			localRow.setString("IP", "localhost");
			localRow.setString("MAC", macs[i]);
			localRow.setString("子网", monitorSubnet.getName());
			// 将不重复的设备/MAC/子网记录加入设备MAC地址表中
			if (!deviceMACDataSet.locate(localRow, Locate.FIRST))
				deviceMACDataSet.addRow(localRow);
		}
	}
	*/

	// 将三层交换机各接口的MAC地址增加到设备MAC表中。
	private String getDeviceMAC(MonitorDevice mDevice, String subnet,
			int devCount) {
		// 从该设备的接口表中读取其MAC地址：
		// interfaceDataSet.deleteAllRows();
		interfaceEntryList.clear();
		deviceSnmpQuery.setDisplayStr("读子网 " + subnet + " 第 " + devCount
				+ " 台路由设备 ");
		// 将设备的接口/MAC信息读到表中
		String ret = deviceSnmpQuery.getIFMACTable(mDevice, interfaceEntryList);
		if (!ret.equals("OK"))
			return ret;

		String device = mDevice.getIp();
		for(InterfaceEntry ie : interfaceEntryList) {
			IpMacEntry ime = new IpMacEntry();
			ime.setIp(device);
			ime.setMac(ie.getMac());
			// row.setString("子网", monitorSubnet.getName());
			// 将不重复的设备/MAC/子网记录加入设备MAC地址表中
			if(!JosqlUtil.containIpMac(ipMacEntryList, ime))
				ipMacEntryList.add(ime);
		}
		// 2010-09-30 测试读取三层设备的MAC地址
//		for(IpMacEntry ime : ipMacEntryList) {
//			System.out.println("getIFMacTable    " + ime.getIp() + "   " + ime.getMac());
//		}
		return "OK";
		
		/*
		DataRow row = new DataRow(deviceMACDataSet);
		// 从该设备的接口表中读取其MAC地址：
		interfaceDataSet.deleteAllRows();
		deviceSnmpQuery.setDisplayStr("读子网 " + subnet + " 第 " + devCount
				+ " 台路由设备 ");
		// 将设备的接口/MAC信息读到表中
		String ret = deviceSnmpQuery.getIFMACTable(device, commStr,
				interfaceDataSet);
		if (!ret.equals("OK"))
			return ret;

		interfaceDataSet.first();
		do {
			row.setString("IP", device);
			row.setString("MAC", interfaceDataSet.getString("MAC"));
			row.setString("子网", subnet);
			// 将不重复的设备/MAC/子网记录加入设备MAC地址表中
			if (!deviceMACDataSet.locate(row, Locate.FIRST))
				deviceMACDataSet.addRow(row);
		} while (interfaceDataSet.next());
		return "OK";
		*/
	}

	// ------------------------------发现交换机互连端口和简单连接关系--------------------
	private void findLinkPort() {
//		System.out.println("findLinkPort   ===============  " + subMacEntryList.size());
//		System.out.println("ipMacEntryList   ===============  " + ipMacEntryList.size());
		linkportList1.clear();
		linkportList2.clear();
		
		for(MacEntry me : subMacEntryList) {

			IpMacEntry ime = JosqlUtil.containMac(ipMacEntryList, me.getMac());
			if(ime != null) {
				// System.out.println(ime.getIp() + "   " + ime.getMac());
				// 加入连接端口表1：
				if(!JosqlUtil.conatinIpPort(linkportList1, me.getUplinkDevice(), me.getPort())) {
					MonitorLinkportExt mle = new MonitorLinkportExt();
					mle.setIp(me.getUplinkDevice());
					mle.setPort(me.getPort());
					mle.setInterface_(me.getInterface_());
					mle.setDescription(me.getDescription());
					linkportList1.add(mle);
				}
				// 加入简单连接表2：
				if(!JosqlUtil.conatinIpPortDownlink(linkportList2, me.getUplinkDevice(), me.getPort(), ime.getIp())) {
					MonitorLinkportExt mle2 = new MonitorLinkportExt();
					mle2.setIp(me.getUplinkDevice());
					mle2.setPort(me.getPort());
					mle2.setInterface_(me.getInterface_());
					mle2.setDescription(me.getDescription());
					mle2.setDownlinkIp(ime.getIp());
					linkportList2.add(mle2);
				}
			}
		}
//		System.out.println("linkportList1   ===============  " + linkportList1.size());
//		System.out.println("linkportList2   ===============  " + linkportList2.size());
		/*
		DataRow macLoc = new DataRow(deviceMACDataSet, "MAC");
		DataRow loc1 = new DataRow(linkPortDataSet1,
				new String[] { "IP", "端口" });
		DataRow row1 = new DataRow(linkPortDataSet1);
		DataRow loc2 = new DataRow(linkPortDataSet2, new String[] { "IP", "端口",
				"下连IP" });
		DataRow row2 = new DataRow(linkPortDataSet2);

		linkPortDataSet1.deleteAllRows();
		linkPortDataSet2.deleteAllRows();

		for(MacEntry me : subMacEntryList) {
			macLoc.setString("MAC", me.getMac());

			if (deviceMACDataSet.locate(macLoc, Locate.FIRST)) {
				// 加入连接端口表1：
				loc1.setString("IP", me.getUplinkDevice());
				loc1.setString("端口", me.getPort());
				if (!linkPortDataSet1.locate(loc1, Locate.FIRST)) {
					row1.setString("IP", me.getUplinkDevice());
					row1.setString("端口", me.getPort());
					row1.setString("接口", me.getInterface_());
					row1.setString("接口描述", me.getDescription());
					linkPortDataSet1.addRow(row1);
				}
				// 加入简单连接表2：
				loc2.setString("IP", me.getUplinkDevice());
				loc2.setString("端口", me.getPort());
				loc2.setString("下连IP", deviceMACDataSet.getString("IP"));
				if (!linkPortDataSet2.locate(loc2, Locate.FIRST)) {
					row2.setString("IP", me.getUplinkDevice());
					row2.setString("端口", me.getPort());
					row2.setString("接口", me.getInterface_());
					row2.setString("接口描述", me.getDescription());
					row2.setString("下连IP", deviceMACDataSet.getString("IP"));
					linkPortDataSet2.addRow(row2);
				}
			}
		}
		*/
		
		
		/*
		subAFTDataSet.first();
		do {
			macLoc.setString("MAC", subAFTDataSet.getString("MAC"));

			if (deviceMACDataSet.locate(macLoc, Locate.FIRST)) {
				// 加入连接端口表1：
				loc1.setString("IP", subAFTDataSet.getString("上连设备"));
				loc1.setString("端口", subAFTDataSet.getString("端口"));
				if (!linkPortDataSet1.locate(loc1, Locate.FIRST)) {
					row1.setString("IP", subAFTDataSet.getString("上连设备"));
					row1.setString("端口", subAFTDataSet.getString("端口"));
					row1.setString("接口", subAFTDataSet.getString("接口"));
					row1.setString("接口描述", subAFTDataSet.getString("接口描述"));
					linkPortDataSet1.addRow(row1);
				}
				// 加入简单连接表2：
				loc2.setString("IP", subAFTDataSet.getString("上连设备"));
				loc2.setString("端口", subAFTDataSet.getString("端口"));
				loc2.setString("下连IP", deviceMACDataSet.getString("IP"));
				if (!linkPortDataSet2.locate(loc2, Locate.FIRST)) {
					row2.setString("IP", subAFTDataSet.getString("上连设备"));
					row2.setString("端口", subAFTDataSet.getString("端口"));
					row2.setString("接口", subAFTDataSet.getString("接口"));
					row2.setString("接口描述", subAFTDataSet.getString("接口描述"));
					row2.setString("下连IP", deviceMACDataSet.getString("IP"));
					linkPortDataSet2.addRow(row2);
				}
			}
		} while (subAFTDataSet.next());
		System.out.println("=============== linkPortDataSet1  " + linkPortDataSet1.getColumnCount());
		System.out.println("=============== linkPortDataSet2  " + linkPortDataSet2.getColumnCount());
		*/
	}// findLinkPort

	// 取得互连端口的设备类型：二层还是三层交换机
	private void getTable1PortType() {
		if (linkportList1.isEmpty())
			return;

		for (MonitorDevice md : deviceList) {
			// 填写互连端口表的类型项
			for(MonitorLinkportExt mle : linkportList1) {
				if (mle.getIp().equals(md.getIp())) {
					// 将c006的交换模块的记录标记为交换+路由而不是原来的交换，其它用设备的类型
					// updateRow
					if (c006SW.contains(md.getIp()))
						mle.setType("交换+路由");
					else
						mle.setType(md.getMonitorDeviceType().getName());
				}
			}
		}
		/*
		for (MonitorDevice md : deviceList) {
			// 填写互连端口表的类型项
			linkPortDataSet1.first();
			do {
				if (linkPortDataSet1.getString("IP").equals(md.getIp())) {
					// 将c006的交换模块的记录标记为交换+路由而不是原来的交换，其它用设备的类型
					// updateRow
					if (c006SW.contains(md.getIp()))
						linkPortDataSet1.setString("类型", "交换+路由");
					else
						linkPortDataSet1.setString("类型", md.getMonitorDeviceType()
								.getName());
				}
			} while (linkPortDataSet1.next());
		}
		*/
	}

	// 填写交换机连接端口的方向，包括三层交换机
	private void getTable1PortDirection() {
		if (linkportList1.isEmpty())
			return;
		for (MonitorLinkportExt mle : linkportList1) {
			// updateRow
			if (portSeeRouter(mle.getIp(), mle.getPort()))
				mle.setDirection("向心");
			else
				mle.setDirection("离心");
		}
	}

	// 某交换设备的某端口看到了三层交换机
	private boolean portSeeRouter(String device, String port) {
		for(MonitorLinkportExt mle : linkportList2) {
			if (!mle.getIp().equals(device))
				continue;
			if (!mle.getPort().equals(port))
				continue;
			if (routers.contains(mle.getDownlinkIp()))
				return true;
		}
		/*
		linkPortDataSet2.first();
		do {
			if (!linkPortDataSet2.getString("IP").equals(device))
				continue;
			if (!linkPortDataSet2.getString("端口").equals(port))
				continue;
			if (routers.contains(linkPortDataSet2.getString("下连IP")))
				return true;
		} while (linkPortDataSet2.next());
		*/
		return false;
	}

	private void fillTable2PortTD() {
		if (linkportList1.isEmpty())
			return;
//		DataRow loc2 = new DataRow(linkPortDataSet2, new String[] { "IP", "端口" });
		for (MonitorLinkportExt mle : linkportList1) {
//			loc2.setString("IP", mle.getIp());
//			loc2.setString("端口", mle.getPort());
			// 逻辑改为直接遍历linkportList2
			for (MonitorLinkportExt mle2 : linkportList2) {
				if (mle2.getIp() != null && mle2.getIp().equals(mle.getIp())
						&& mle2.getPort() != null
						&& mle2.getPort().equals(mle.getPort())) {
					mle2.setType(mle.getType());
					mle2.setDirection(mle.getDirection());
				}
			}
			/*
			if (!linkPortDataSet2.locate(loc2, Locate.FIRST))
				continue;
			// 填写第一条记录
			// updateRow
			linkPortDataSet2.setString("类型", mle.getType());
			linkPortDataSet2.setString("方向", mle.getDirection());
			// 填写其它记录
			while (linkPortDataSet2.locate(loc2, Locate.NEXT)) {
				// updateRow
				linkPortDataSet2.setString("类型", mle.getType());
				linkPortDataSet2.setString("方向", mle.getDirection());
			}
			*/
		}
	}

	// 检查是否每个二层交换机都发现了互连端口
	private void haveLinkPort(List<MonitorLinkport> list) {
		// 检查子网交换设备中是否有未发现互连端口的设备：
		noLinkPortDevCount = 0;
		for (MonitorDevice md : deviceList) {
			if (md.getMonitorDeviceType().getCode() != MainConstants.DEVICE_SWITCH)
				continue;
			if(!JosqlUtil.containSubnetIdDeviceIp(list, monitorSubnet.getId(), md.getIp())) {
				noLinkPortDevCount++;
				System.out.println("设备 " + md.getIp() + " 没有发现互连端口。");
			}
		}
	}

	// ----------------------------------二层交换机互连拓扑发现---------------------------------
	private void findL2Top() {
		// 发现交换端口连接关系
		findPortRelation();

		// 将各二层交换机子树的根与中心三层交换机相连。如果网络以LOCALHOST为路由器，则不连接到交换中心
		if (!localAsRouter)
			l2LinkL3();
	}

	// 发现二层交换机互连端口的配对关系
	private void findPortRelation() {
		String lowDev;
		boolean haveLeaf;
		// 按广度优先遍历连接端口表。每一遍遍历发现当前生成树的所有叶节点设备，叶节点只被
		// 另一个设备的一个离心端口唯一看到。一遍便历结束后，开始下一次遍历，只到找不
		// 叶节点或表已空。
		while (!linkportList.isEmpty()) {
			haveLeaf = false;
			for (MonitorLinkportExt mle : linkportList1) {
				// 以备注1作为删除标志
				if (mle.getNote1() != null && mle.getNote1().equals("删除"))
					continue;
				// 只考虑周边二层交换机上的离心方向的端口
				if (!mle.getType().equals("交换"))
					continue;
				if (mle.getDirection().equals("向心"))
					continue;
				lowDev = portOnlySeeOne(mle.getIp(), mle.getPort());
				if (lowDev == null)
					continue;
				matchTwoDevice(mle.getIp(), mle.getPort(), mle.getInterface_(),
						mle.getDescription(), lowDev);
				haveLeaf = true;
			}
			// 如果找到叶节点，则删除连接端口表1做了删除标记的记录
			if (haveLeaf)
				delTable1();
			else
				break;
		}// while
	}// findPortRelation

	// 当前端口只看到一个设备：
	private String portOnlySeeOne(String device, String port) {
		// 2010-09-10 list和dataset不同，list并不会有行指针移动，直接用get(index)获取元素。
		// 因此无需记录行指针，并在操作后恢复
//		int curRow = linkPortDataSet2.getRow();
		String result = null;
		int seeNum = 0;

		for(MonitorLinkportExt mle : linkportList2) {
			if (!mle.getIp().equals(device))
				continue;
			if (!mle.getPort().equals(port))
				continue;
			result = mle.getDownlinkIp();
			seeNum++;
		}
		/*
		linkPortDataSet2.first();
		do {
			if (!linkPortDataSet2.getString("IP").equals(device))
				continue;
			if (!linkPortDataSet2.getString("端口").equals(port))
				continue;
			result = linkPortDataSet2.getString("下连IP");
			seeNum++;
		} while (linkPortDataSet2.next());
		*/
		
//		linkPortDataSet2.goToRow(curRow);

		if (seeNum == 1)
			return result;
		else
			return null;
	}

	// 当前端口在离心方向上只看到了一个设备，找到这个设备所有向心方向的端口，将向心
	// 方向端口与当前端口匹配成对加入结果表中，并将端口对从连接端口表、简单连接表中
	// 删除，从简单连接表中删除所有简单连接该叶节点设备的记录。
	private void matchTwoDevice(String highDev, String highPort, String highIf,
			String highIfDescr, String lowDev) {
		// 2010-09-10 curRow ??
		// int curRow = linkPortDataSet1.getRow();
		for (MonitorLinkportExt mle : linkportList1) {
			if (mle.getNote1() != null && mle.getNote1().equals("删除"))
				continue;
			if (!mle.getIp().equals(lowDev))
				continue;
			if (!mle.getDirection().equals("向心"))
				continue;
			// 将端口配对加入结果表中。
			addToResult(highDev, highPort, highIf, highIfDescr, mle.getIp(),
					mle.getPort(), mle.getInterface_(), mle.getDescription());
			// 删除上连端口在连接端口表1和简单连接表2中的所有记录：
			markTable1(mle.getIp(), mle.getPort());
			delTable2(mle.getIp(), mle.getPort());
		}

		// 删除简单连接表2中所有下连IP为当前叶节点设备的记录
		delSimpleLinkDest(lowDev);
		// 删除当前下连端口在连接端口表1和简单连接表2中的所有记录：
		markTable1(highDev, highPort);
		delTable2(highDev, highPort);
		
		// 2010-09-10
		// linkPortDataSet1.goToRow(curRow);
	}

	// 标记连接端口表1中指定设备端口记录为删除
	private void markTable1(String ip, String port) {
		// 2010-09-10 curRow ??
		// int curRow = linkPortDataSet1.getRow();
		for (MonitorLinkportExt mle : linkportList1) {
			if (mle.getIp().equals(ip) && mle.getPort().equals(port)) {
				// updateRow
				mle.setNote1("删除");// 以note1作为删除标志
			}
		}
		
		// 2010-09-10
		// linkPortDataSet1.goToRow(curRow);
	}

	// 删除连接端口表1中做了删除标记的记录
	private void delTable1() {
		int size = linkportList1.size();
		String nt1;
		for (int i = size - 1; i >= 0; i--) {
			nt1 = linkportList1.get(i).getNote1();
			if(nt1 != null && "删除".equals(nt1)) {
				linkportList1.remove(i);
			}
		}
		/*
		boolean deleted = true;

		linkPortDataSet1.first();
		do {
			while (deleted) {
				if (linkPortDataSet1.getString("子网名").equals("删除")) {
					if (linkPortDataSet1.atLast()) {
						linkPortDataSet1.deleteRow();
						break;
					} else {
						linkPortDataSet1.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (linkPortDataSet1.next());
		*/
	}

	// 删除简单连接表2中指定设备端口的所有记录
	private void delTable2(String ip, String port) {
//		boolean deleted = true;

		int size = linkportList2.size();
		MonitorLinkportExt mle;
		for (int i = size - 1; i >= 0; i--) {
			mle = linkportList2.get(i);
			if(mle.getIp().equals(ip) && mle.getPort().equals(port)) {
				linkportList2.remove(i);
			}
		}
		
		/*
		linkPortDataSet2.first();
		do {
			while (deleted) {
				if (linkPortDataSet2.getString("IP").equals(ip)
						&& linkPortDataSet2.getString("端口").equals(port)) {
					if (linkPortDataSet2.atLast()) {
						linkPortDataSet2.deleteRow();
						break;
					} else {
						linkPortDataSet2.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (linkPortDataSet2.next());
		*/
	}

	// 删除简单连接表2中所有下连IP为指定IP的记录
	private void delSimpleLinkDest(String ip) {
		int size = linkportList2.size();
		MonitorLinkportExt mle;
		for (int i = size - 1; i >= 0; i--) {
			mle = linkportList2.get(i);
			if(mle.getDownlinkIp().equals(ip)) {
				linkportList2.remove(i);
			}
		}
		/*
		boolean deleted = true;

		linkPortDataSet2.first();
		do {
			while (deleted) {
				if (linkPortDataSet2.getString("下连IP").equals(ip)) {
					if (linkPortDataSet2.atLast()) {
						linkPortDataSet2.deleteRow();
						break;
					} else {
						linkPortDataSet2.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (linkPortDataSet2.next());
		*/
	}

	// 将各三层交换机与其下的各二层交换机子树的根相连
	private void l2LinkL3() {
		if (linkportList1.isEmpty())
			return;
		// 优先连接到三层交换机的物理口上
		linkToL3();
		// 如果不能匹配则连接到三层交换机的逻辑口（VLAN口或子接口上），再不匹配则连接到中心设备或虚拟核心上。
		linkToCore();
	}

	private void linkToL3() {
		for (MonitorLinkportExt mle : linkportList1) {
			// 子树根二层交换机向心方向的端口被看到该交换机的三层交换机的端口所匹配。
			if (mle.getNote1() != null && mle.getNote1().equals("删除"))
				continue;
			if (!mle.getType().equals("交换"))
				continue;
			if (!mle.getDirection().equals("向心"))
				continue;
			for(MonitorLinkportExt mle2 : linkportList2) {
				if (mle2.getType().equals("交换"))
					continue;
				if (mle2.getDirection().equals("向心"))
					continue;
				if (mle2.getDownlinkIp().equals(mle.getIp())) {
					matchTwoDevice(mle2.getIp(), mle2.getPort(), mle2
							.getInterface_(), mle2.getDescription(), mle
							.getIp());
					break;
				}// if
			}
			/*
			linkPortDataSet2.first();
			do {
				if (linkPortDataSet2.getString("类型").equals("交换"))
					continue;
				if (linkPortDataSet2.getString("方向").equals("向心"))
					continue;
				if (linkPortDataSet2.getString("下连IP").equals(
						mle.getIp())) {
					matchTwoDevice(linkPortDataSet2.getString("IP"),
							linkPortDataSet2.getString("端口"), 
							linkPortDataSet2.getString("接口"), 
							linkPortDataSet2.getString("接口描述"), 
							mle.getIp());
					break;
				}// if
			} while (linkPortDataSet2.next());
			*/
		}
		// 从互连端口表1中删除所有设置了删除标记的端口
		delTable1();
	}

	private void linkToCore() {
		Object[] rts;
		String core;
		if (routers.size() == 1) {// 如果只有一个三层设备，则CORE使用该设备的地址
			rts = routers.toArray();
			core = (String) rts[0];
		} else {// 如果有多个三层设备，则虚拟成CORE。
			core = "core";
		}

		String[] l3LinkPort;
		for (MonitorLinkportExt mle : linkportList1) {
			if (!mle.getType().equals("交换"))
				continue;
			if (mle.getDirection().equals("向心")) {
				l3LinkPort = getL3LinkPort(mle.getIp());
				// 如果某设备的ARP表中存在该设备记录，优先使用ARP连接。
				if (l3LinkPort != null) {
					addToResult(l3LinkPort[0], "", l3LinkPort[1],
							l3LinkPort[2], mle.getIp(), mle.getPort(), mle
									.getInterface_(), mle.getDescription());
					// 如果所有ARP表中都不存在该设备记录，则使用虚拟核心。
				} else {
					addToResult(core, "", "", "", mle.getIp(), mle.getPort(),
							mle.getInterface_(), mle.getDescription());
				}
				// 从互连端口表1中标记删除该端口
				markTable1( mle.getIp(), mle.getPort());
				// 从简单连接端口表2中删除该记录
				delTable2( mle.getIp(), mle.getPort());
			}
		}
		// 从互连端口表1中删除所有设置了删除标记的端口
		delTable1();
	}

	// 2010-09-10 finish
	private String[] getL3LinkPort(String ip) {
		for(ArpEntry ae : arpEntryList) {
			if(ae.getUplinkDevice() == null)
				continue;
			if("localhost".equals(ae.getUplinkDevice()))
				continue;
			if(ae.getIp().equals(ip)) {
				String[] tmp = new String[3];
				tmp[0] = ae.getUplinkDevice();
				tmp[1] = ae.getInterface_();
				tmp[2] = ae.getDescription();
				return tmp;
			}
		}
		return null;
		/*
		ipMacDataSet.first();
		do {
			if (ipMacDataSet.getString("上连设备").equals("localhost"))
				continue;
			if (ipMacDataSet.getString("IP").equals(ip)) {
				String[] tmp = new String[3];
				tmp[0] = ipMacDataSet.getString("上连设备");
				tmp[1] = ipMacDataSet.getString("接口");
				tmp[2] = ipMacDataSet.getString("接口描述");
				return tmp;
			}
		} while (ipMacDataSet.next());
		return null;
		*/
	}

	// ------------------------监控站可直达某些二层交换机时补充简单连接关系 --------------
	private void appendSimpleLink() {
		genTable4();

		if (!linkportList4.isEmpty()) {
			// 2010-09-20
			genSeeNum();
			genSimpleLink();
		}

		// 删除简单连接表2中所有下连IP为监控站的记录
		delSimpleLinkDest("localhost");
	}

	// 找到所有既看到监控站、又看到三层交换机、并且端口不同的设备
	private void genTable4() {
//		DataRow row4 = new DataRow(linkPortDataSet4);

		for (MonitorDevice md : deviceList) {
			// directSW中只有二层交换机
			if (!directSW.contains(md.getIp()))
				continue;

			// 找到该设备看到监控站的端口和看到路由器的端口的集合
			getSeeLocalAndRouterPort(md.getIp());
			if (!seeLocalPort.equals("") && !seeRouterPorts.isEmpty()
					&& !seeRouterPorts.contains(seeLocalPort)) {
				// 记录看到监控站的离心方向的端口
//				row4.setString("IP", md.getIp());
//				row4.setString("端口", seeLocalPort);
//				row4.setString("接口", seeLocalPortIfx);
//				row4.setString("接口描述", seeLocalPortIfd);
//				linkPortDataSet4.addRow(row4);
				MonitorLinkportExt mle = new MonitorLinkportExt();
				mle.setIp(md.getIp());
				mle.setPort(seeLocalPort);
				mle.setInterface_(seeLocalPortIfx);
				mle.setDescription(seeLocalPortIfd);
				linkportList4.add(mle);
				// 将该设备计入逆序路径二层交换机集合
				pathSW.add(md.getIp());
			}
		}
	}

	// 找到某设备看到监控站的端口和看到路由设备的端口集合
	private void getSeeLocalAndRouterPort(String device) {
		seeLocalPort = "";
		seeRouterPorts.clear();

		for(MonitorLinkportExt mle : linkportList2) {
			if (!mle.getIp().equals(device))
				continue;
			if (mle.getDownlinkIp().equals("localhost")) {
				seeLocalPort = mle.getPort();
				seeLocalPortIfx = mle.getInterface_();
				seeLocalPortIfd = mle.getDescription();
			}
			if (routers.contains(mle.getDownlinkIp()))
				seeRouterPorts.add(mle.getPort());
		}
		/*
		linkPortDataSet2.first();
		do {
			if (!linkPortDataSet2.getString("IP").equals(device))
				continue;
			if (linkPortDataSet2.getString("下连IP").equals("localhost")) {
				seeLocalPort = linkPortDataSet2.getString("端口");
				seeLocalPortIfx = linkPortDataSet2.getString("接口");
				seeLocalPortIfd = linkPortDataSet2.getString("接口描述");
			}
			if (routers.contains(linkPortDataSet2.getString("下连IP")))
				seeRouterPorts.add(linkPortDataSet2.getString("端口"));
		} while (linkPortDataSet2.next());
		*/
	}

	// 统计表4中每个逆序路径二层交换机所有向心方向端口看到的逆序设备数
	private void genSeeNum() {
		int seeNum;
//		DataRow loc1 = new DataRow(linkPortDataSet1,
//				new String[] { "IP", "端口" });

		for(MonitorLinkportExt mle : linkportList4) {
			seeNum  = 0;
			for(MonitorLinkportExt mle2 : linkportList2) {
				if (!mle2.getIp().equals(mle.getIp()))
					continue;
				if (!JosqlUtil.conatinIpPort(linkportList1, mle2.getIp(), mle2.getPort()))
					continue;
				if (mle.getDirection().equals("离心"))
					continue;
				if (pathSW.contains(mle2.getDownlinkIp()))
					seeNum++;
			}
			/*
			linkPortDataSet2.first();
			do {
				if (!linkPortDataSet2.getString("IP").equals(mle.getIp()))
					continue;
//				loc1.setString("IP", linkPortDataSet2.getString("IP"));
//				loc1.setString("端口", linkPortDataSet2.getString("端口"));
				if (!JosqlUtil.conatinIpPort(linkportList1, linkPortDataSet2.getString("IP"), linkPortDataSet2.getString("端口")))
					continue;
				if (mle.getDirection().equals("离心"))
					continue;
				if (pathSW.contains(linkPortDataSet2.getString("下连IP")))
					seeNum++;
			} while (linkPortDataSet2.next());
			*/
			// updateRow
			mle.setSeenNum(seeNum);
			// 按看到同网段设备数的多少从大到小排序
			// 2010-09-10
			Comparator c = new Comparator() {
				public int compare(Object o1, Object o2) {
					int a = ((MonitorLinkportExt)o1).getSeenNum();
					int b = ((MonitorLinkportExt)o2).getSeenNum();
					return a - b;
				}
			};
			Collections.sort(linkportList4);
			for(MonitorLinkportExt mle1 : linkportList4) {
				System.out.println(" =====  " + mle1.getSeenNum());
			}
//			linkPortDataSet4.setSort(new com.borland.dx.dataset.SortDescriptor(
//					new String[] { "所见数" }, false, true));
		}
		/*
		linkPortDataSet4.first();
		do {
			seeNum = 0;
			linkPortDataSet2.first();
			do {
				if (!linkPortDataSet2.getString("IP").equals(
						linkPortDataSet4.getString("IP")))
					continue;
//				loc1.setString("IP", linkPortDataSet2.getString("IP"));
//				loc1.setString("端口", linkPortDataSet2.getString("端口"));
				if (!JosqlUtil.conatinIpPort(linkportList1, linkPortDataSet2.getString("IP"), linkPortDataSet2.getString("端口")))
					continue;
				if (mle.getDirection().equals("离心"))
					continue;
				if (pathSW.contains(linkPortDataSet2.getString("下连IP")))
					seeNum++;
			} while (linkPortDataSet2.next());
			// updateRow
			linkPortDataSet4.setInt("所见数", seeNum);
		} while (linkPortDataSet4.next());
		// 按看到同网段设备数的多少从大到小排序
		linkPortDataSet4.setSort(new com.borland.dx.dataset.SortDescriptor(
				new String[] { "所见数" }, false, true));
		*/
	}

	// 补充逆序二层交换机及看到监控站的三层交换机端口的简单连接关系
	private void genSimpleLink() {
		int curRow;
		String dstDev, dstPort;
//		DataRow loc2 = new DataRow(linkPortDataSet2, new String[] { "IP", "端口", "下连IP" });
//		DataRow row2 = new DataRow(linkPortDataSet2);
		// linkPortDataSet4.first();
		MonitorLinkportExt mle = linkportList4.get(0);

		// 向心方向看到最多逆序二层交换机的设备的离心方向的端口直连监控站，将该端口删除
		dstDev = mle.getIp();
		dstPort = mle.getPort();
		markTable1(dstDev, dstPort);
		delTable1();
		delTable2(dstDev, dstPort);

		// 2010-09-10      此处逻辑需慎重验证
		for(int i = 1; i < linkportList4.size(); i ++) {
			// curRow = linkPortDataSet4.getRow();
			// mle = linkportList4.get(i);
			// 在所有位于当前设备之后的设备的监控站方向的端口增加到当前设备的简单连接记录：
			for(int j = i; j < linkportList4.size(); j++) {
				mle = linkportList4.get(j);
//				loc2.setString("IP", mle.getIp());
//				loc2.setString("端口", mle.getPort());
//				loc2.setString("下连IP", dstDev);
				// if (!linkPortDataSet2.locate(loc2, Locate.FIRST)) {
				if(!JosqlUtil.conatinIpPortDownlink(linkportList2, mle.getIp(), mle.getPort(), dstDev)) {
					MonitorLinkportExt mle2 = new MonitorLinkportExt();
					mle2.setIp(mle.getIp());
					mle2.setPort(mle.getPort());
					mle2.setInterface_(mle.getInterface_());
					mle2.setDescription(mle.getDescription());
					mle2.setDownlinkIp(dstDev);
					linkportList2.add(mle2);
				}
			}
			// linkPortDataSet4.goToRow(curRow);
			mle = linkportList4.get(i);
			dstDev = mle.getIp();
		}// for
		
		/*
		linkPortDataSet4.first();

		// 向心方向看到最多逆序二层交换机的设备的离心方向的端口直连监控站，将该端口删除
		dstDev = linkPortDataSet4.getString("IP");
		markTable1(dstDev, linkPortDataSet4.getString("端口"));
		delTable1();
		delTable2(dstDev, linkPortDataSet4.getString("端口"));

		while (linkPortDataSet4.next()) {
			curRow = linkPortDataSet4.getRow();
			// 在所有位于当前设备之后的设备的监控站方向的端口增加到当前设备的简单连接记录：
			do {
				loc2.setString("IP", linkPortDataSet4.getString("IP"));
				loc2.setString("端口", linkPortDataSet4.getString("端口"));
				loc2.setString("下连IP", dstDev);
				if (!linkPortDataSet2.locate(loc2, Locate.FIRST)) {
					row2.setString("IP", linkPortDataSet4.getString("IP"));
					row2.setString("端口", linkPortDataSet4.getString("端口"));
					row2.setString("接口", linkPortDataSet4.getString("接口"));
					row2.setString("接口描述", linkPortDataSet4.getString("接口描述"));
					row2.setString("下连IP", dstDev);
					linkPortDataSet2.addRow(row2);
				}
			} while (linkPortDataSet4.next());
			linkPortDataSet4.goToRow(curRow);
			dstDev = linkPortDataSet4.getString("IP");
		}// while
*/
		// 增加看到监控站的三层交换机端口到每个逆序路径二层交换机的简单连接记录
		for (MonitorLinkportExt mle2 : linkportList2) {
			// loc22.setString("下连IP", "localhost");
			// loc22.setString("类型", "交换+路由");
			if (mle2.getDownlinkIp() != null
					&& mle2.getDownlinkIp().equals("localhost")
					&& mle2.getType() != null && mle2.getType().equals("交换+路由")) {
				String sw = mle2.getIp();
				String pt = mle2.getPort();
				String ifx = mle2.getInterface_();
				String ifd = mle2.getDescription();
				for (int i = 0; i < pathSW.size(); i++) {
					if (!JosqlUtil.conatinIpPortDownlink(linkportList2, sw, pt, (String) pathSW.get(i))) {
						MonitorLinkportExt mleNew = new MonitorLinkportExt();
						mleNew.setIp(sw);
						mleNew.setPort(pt);
						mleNew.setInterface_(ifx);
						mleNew.setDescription(ifd);
						mleNew.setDownlinkIp((String) pathSW.get(i));
						linkportList2.add(mleNew);
					}
				}// for
			}
		}
		/*
		// 增加看到监控站的三层交换机端口到每个逆序路径二层交换机的简单连接记录
		DataRow loc22 = new DataRow(linkPortDataSet2, new String[] { "下连IP", "类型" });
		loc22.setString("下连IP", "localhost");
		loc22.setString("类型", "交换+路由");
		if (linkPortDataSet2.locate(loc22, Locate.FIRST)) {
			String sw = linkPortDataSet2.getString("IP");
			String pt = linkPortDataSet2.getString("端口");
			String ifx = linkPortDataSet2.getString("接口");
			String ifd = linkPortDataSet2.getString("接口描述");
			for (int i = 0; i < pathSW.size(); i++) {
				loc2.setString("IP", sw);
				loc2.setString("端口", pt);
				loc2.setString("下连IP", (String) pathSW.get(i));
				if (!linkPortDataSet2.locate(loc2, Locate.FIRST)) {
					row2.setString("IP", sw);
					row2.setString("端口", pt);
					row2.setString("接口", ifx);
					row2.setString("接口描述", ifd);
					row2.setString("下连IP", (String) pathSW.get(i));
					linkPortDataSet2.addRow(row2);
				}
			}// for
		}// if
		*/
	}

	// --------------------------------三层拓扑发现---------------------------------
	private void findL3Top() {
		try {
			// 发现设备的交换互连端口及其简单连接关系
			// L3TopDiscovery l3TopDiscovery = new L3TopDiscovery(monitorSubnet, core);
			l3TopDiscovery.init(monitorSubnet, core);
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "L3TopDiscovery,初始化错误:" + e);
			return;
		}
		if (!l3TopDiscovery.getL3Top())
			return;
		// 发现结果保存在简单连接表文件中
//		TextDataFile l3LinkFile = new TextDataFile();
//		l3LinkFile.setFileName("三层连接表.txt");
//		linkPortDataSet5.setDataFile(l3LinkFile);
//		linkPortDataSet5.open();

		linkportList5 = l3TopDiscovery.getLinkportList3();
		// 将三层交换机直连VLAN接口映射为物理端口
		if (linkportList5.isEmpty())
			return;
//		linkPortDataSet5.first();
//		do {
//			updateRecord();
//		} while (linkPortDataSet5.next());
		for(MonitorLinkport ml : linkportList5) {
			updateRecord(ml);
		}

		for (MonitorLinkport ml : linkportList5) {
			addToResult(ml.getIp(), ml.getPort(), ml.getInterface_(), ml
					.getDescription(), ml.getDownlinkIp(),
					ml.getDownlinkPort(), ml.getDownlinkInterface(), ml
							.getDownlinkDesc());
			// 如果三层接口被替换成二层端口，则将二层端口从表1中删除，但不需要从表2中删除，因为
			// 这时端口看到的个数信息已无用，这时也不需要删除某个设备节点。
			if (ml.getPort() != null && !ml.getPort().equals(""))
				markTable1(ml.getIp(), ml.getPort());
			if (ml.getPort() != null && !ml.getDownlinkPort().equals(""))
				markTable1(ml.getDownlinkIp(), ml.getDownlinkPort());
		}
		/*
		linkPortDataSet5.first();
		do {
			addToResult(linkPortDataSet5.getString("IP"), 
					linkPortDataSet5.getString("端口"), 
					linkPortDataSet5.getString("接口"),
					linkPortDataSet5.getString("接口描述"), 
					linkPortDataSet5.getString("下连IP"), 
					linkPortDataSet5.getString("下连端口"), 
					linkPortDataSet5.getString("下连接口"), 
					linkPortDataSet5.getString("下连接口描述"));
			// 如果三层接口被替换成二层端口，则将二层端口从表1中删除，但不需要从表2中删除，因为
			// 这时端口看到的个数信息已无用，这时也不需要删除某个设备节点。
			if (!linkPortDataSet5.getString("端口").equals(""))
				markTable1(linkPortDataSet5.getString("IP"), linkPortDataSet5.getString("端口"));
			if (!linkPortDataSet5.getString("下连端口").equals(""))
				markTable1(linkPortDataSet5.getString("下连IP"), linkPortDataSet5.getString("下连端口"));
		} while (linkPortDataSet5.next());
		*/
		delTable1();
	}

	private void updateRecord(MonitorLinkport ml) {
		if (ml == null)
			return;
		String rtIpUp, rtIpDown, switchip;
//		DataRow loc = new DataRow(linkPortDataSet2, new String[] { "IP", "下连IP" });
		rtIpUp = ml.getIp();
		rtIpDown = ml.getDownlinkIp();

		MonitorLinkportExt mle2;
		// 首先找到上位设备看到下位设备的物理端口并更新记录。分两种情况：普通设备和6006设备
		if (!c006RT.contains(rtIpUp)) {
//			loc.setString("IP", rtIpUp);
//			loc.setString("下连IP", rtIpDown);
			mle2 = JosqlUtil.conatinIpDownlinkExt(linkportList2, rtIpUp, rtIpDown);
			if(mle2 != null) {
			// if (linkPortDataSet2.locate(loc, Locate.FIRST)) {
				// updateRow
				ml.setPort(mle2.getPort());
				ml.setInterface_(mle2.getInterface_());
				ml.setDescription(mle2.getDescription());
			}
		} else {
			switchip = (String) c006SW.get(c006RT.indexOf(rtIpUp));
//			loc.setString("IP", switchip);
//			loc.setString("下连IP", rtIpDown);
			mle2 = JosqlUtil.conatinIpDownlinkExt(linkportList2, switchip, rtIpDown);
			if (mle2 != null) {
				// updateRow
				ml.setIp(switchip);
				ml.setPort(mle2.getPort());
				ml.setInterface_(mle2.getInterface_());
				ml.setDescription(mle2.getDescription());
			}
		}
		// 然后找到下位设备看到上位设备的物理端口并更新记录，也分两种情况：普通设备和6006设备
		if (!c006RT.contains(rtIpDown)) {
//			loc.setString("IP", rtIpDown);
//			loc.setString("下连IP", rtIpUp);
			mle2 = JosqlUtil.conatinIpDownlinkExt(linkportList2, rtIpUp, rtIpDown);
			if (mle2 != null) {
				// if (linkPortDataSet2.locate(loc, Locate.FIRST)) {
				// updateRow
				ml.setPort(mle2.getPort());
				ml.setInterface_(mle2.getInterface_());
				ml.setDescription(mle2.getDescription());
			}
		} else {
			switchip = (String) c006SW.get(c006RT.indexOf(rtIpDown));
//			loc.setString("IP", switchip);
//			loc.setString("下连IP", rtIpUp);
			mle2 = JosqlUtil.conatinIpDownlinkExt(linkportList2, switchip, rtIpDown);
			if (mle2 != null) {
				// updateRow
				ml.setIp(switchip);
				ml.setPort(mle2.getPort());
				ml.setInterface_(mle2.getInterface_());
				ml.setDescription(mle2.getDescription());
			}
		}
	}

	// 将匹配成功的设备/端口对加入结果表中
	private void addToResult(String srcDev, String srcPort, String srcIf,
			String srcIfDesrc, String dstDev, String dstPort, String dstIf,
			String dstIfDesrc) {
		MonitorLinkport ml = new MonitorLinkport();
		ml.setIp(srcDev);
		ml.setPort(srcPort);
		ml.setInterface_(srcIf);
		ml.setDescription(srcIfDesrc);
		ml.setDownlinkIp(dstDev);
		ml.setDownlinkPort(dstPort);
		ml.setDownlinkInterface(dstIf);
		ml.setDownlinkDesc(dstIfDesrc);
		linkportList3.add(ml);
		/*
		DataRow row = new DataRow(linkPortDataSet3);
		row.setString("IP", srcDev);
		row.setString("端口", srcPort);
		row.setString("接口", srcIf);
		row.setString("接口描述", srcIfDesrc);
		row.setString("下连IP", dstDev);
		row.setString("下连端口", dstPort);
		row.setString("下连接口", dstIf);
		row.setString("下连接口描述", dstIfDesrc);
		linkPortDataSet3.addRow(row);
		*/
	}

	// 将结果表3保存到连接端口表中。记录在该表中的顺序与拓扑图生成的结果无关。
	private void copyResult() {
		if (linkportList3.isEmpty())
			return;
		// 首先复制成对记录：
		for(MonitorLinkport ml3 : linkportList3) {
			MonitorLinkport ml = new MonitorLinkport();
			ml.setIp(ml3.getIp());
			ml.setPort(ml3.getPort());
			ml.setInterface_(ml3.getInterface_());
			ml.setDescription(ml3.getDescription());
			ml.setDownlinkIp(ml3.getDownlinkIp());
			ml.setDownlinkPort(ml3.getDownlinkPort());
			ml.setDownlinkInterface(ml3.getDownlinkInterface());
			ml.setDownlinkDesc(ml3.getDownlinkDesc());
			ml.setMonitorSubnet(monitorSubnet);
			ml.setInode(ml3.getInode() == null ? 0 : ml3.getInode());
			ml.setEdge(ml3.getEdge() == null ? 0 : ml3.getEdge());
			linkportList.add(ml);
		}
		// 两个设备之间只有唯一一对接口互连。
		// 路由设备的每个接口唯一，但同一接口可以有多个连接，在连接表中同一设备的同一接口不出现两次。
		// 然后根据下位下连IP、下连接口是否已作为上位IP、上位接口出现决定加入
		for(MonitorLinkport ml3 : linkportList3) {
			if (ml3.getDownlinkIp() == null || "".equals(ml3.getDownlinkIp()))
				continue;
			if (ml3.getDownlinkInterface() == null || "".equals(ml3.getDownlinkInterface()))
				continue;
			// 2010-09-20 此处判断时无需加上subnetId一项，因为此处的linkportList都是针对此子网的
//			loc.setString("IP", linkPortDataSet3.getString("下连IP"));
//			loc.setString("接口", linkPortDataSet3.getString("下连接口"));
//			loc.setInt("子网ID", monitorSubnet.getId());
			if(!JosqlUtil.containIpIfx(linkportList,  ml3.getDownlinkIp(), ml3.getDownlinkInterface())) {
				MonitorLinkport ml = new MonitorLinkport();
				ml.setIp(ml3.getDownlinkIp());
				ml.setPort(ml3.getDownlinkPort());
				ml.setInterface_(ml3.getDownlinkInterface());
				ml.setDescription(ml3.getDownlinkDesc());
				ml.setDownlinkIp("");
				ml.setDownlinkPort("");
				ml.setDownlinkInterface("");
				ml.setDownlinkDesc("");
				ml.setMonitorSubnet(monitorSubnet);
				ml.setInode(ml3.getInode() == null ? 0 : ml3.getInode());
				ml.setEdge(ml3.getEdge() == null ? 0 : ml3.getEdge());
				linkportList.add(ml);
			}
		}

		// 如果退出时连接端口表1不空，表明存在没有匹配成功的记录，把这些记录也加入结果表中，保证表的完整
		// 结果表3中的端口连接记录都是从表1中删除的，所以不会重复
		if (!linkportList1.isEmpty()) {
			for(MonitorLinkportExt mle : linkportList1) {
				MonitorLinkport ml = new MonitorLinkport();
				ml.setIp(mle.getIp());
				ml.setPort(mle.getPort());
				ml.setInterface_(mle.getInterface_());
				ml.setDescription(mle.getDescription());
				ml.setMonitorSubnet(monitorSubnet);
				ml.setInode(mle.getInode() == null ? 0 : mle.getInode());
				ml.setEdge(mle.getEdge() == null ? 0 : mle.getEdge());
				linkportList.add(ml);
			}
		}// if
		
	/*
	// 将结果表3保存到连接端口表中。记录在该表中的顺序与拓扑图生成的结果无关。
	private void copyResult() {
		if (linkportList3.isEmpty())
			return;
		DataRow row = new DataRow(linkPortDataSet);
		linkPortDataSet3.first();
		// 首先复制成对记录：
		do {
			row.setString("IP", linkPortDataSet3.getString("IP"));
			row.setString("端口", linkPortDataSet3.getString("端口"));
			row.setString("接口", linkPortDataSet3.getString("接口"));
			row.setString("接口描述", linkPortDataSet3.getString("接口描述"));
			row.setString("下连IP", linkPortDataSet3.getString("下连IP"));
			row.setString("下连端口", linkPortDataSet3.getString("下连端口"));
			row.setString("下连接口", linkPortDataSet3.getString("下连接口"));
			row.setString("下连接口描述", linkPortDataSet3.getString("下连接口描述"));
			row.setString("子网名", monitorSubnet.getName());
			row.setInt("子网ID", monitorSubnet.getId());
			linkPortDataSet.addRow(row);
		} while (linkPortDataSet3.next());

		// 两个设备之间只有唯一一对接口互连。
		// 路由设备的每个接口唯一，但同一接口可以有多个连接，在连接表中同一设备的同一接口不出现两次。
		// 然后根据下位下连IP、下连接口是否已作为上位IP、上位接口出现决定加入
		DataRow loc = new DataRow(linkPortDataSet, new String[] { "IP", "接口",
				"子网ID" });
		linkPortDataSet3.first();
		do {
			if (linkPortDataSet3.getString("下连IP").equals(""))
				continue;
			if (linkPortDataSet3.getString("下连接口").equals(""))
				continue;
			loc.setString("IP", linkPortDataSet3.getString("下连IP"));
			loc.setString("接口", linkPortDataSet3.getString("下连接口"));
			loc.setInt("子网ID", monitorSubnet.getId());
			if (!linkPortDataSet.locate(loc, Locate.FIRST)) {
				row.setString("IP", linkPortDataSet3.getString("下连IP"));
				row.setString("端口", linkPortDataSet3.getString("下连端口"));
				row.setString("接口", linkPortDataSet3.getString("下连接口"));
				row.setString("接口描述", linkPortDataSet3.getString("下连接口描述"));
				row.setString("下连IP", "");
				row.setString("下连端口", "");
				row.setString("下连接口", "");
				row.setString("下连接口描述", "");
				row.setString("子网名", monitorSubnet.getName());
				row.setInt("子网ID", monitorSubnet.getId());
				linkPortDataSet.addRow(row);
			}
		} while (linkPortDataSet3.next());

		// 如果退出时连接端口表1不空，表明存在没有匹配成功的记录，把这些记录也加入结果表中，保证表的完整
		// 结果表3中的端口连接记录都是从表1中删除的，所以不会重复
		if (!linkPortDataSet1.isEmpty()) {
			linkPortDataSet1.first();
			do {
				row.setString("IP", linkPortDataSet1.getString("IP"));
				row.setString("端口", linkPortDataSet1.getString("端口"));
				row.setString("接口", linkPortDataSet1.getString("接口"));
				row.setString("接口描述", linkPortDataSet1.getString("接口描述"));
				row.setString("子网名", monitorSubnet.getName());
				row.setInt("子网ID", monitorSubnet.getId());
				linkPortDataSet.addRow(row);
			} while (linkPortDataSet1.next());
		}// if
		*/
	}

	private void display(String str) {
		 System.out.println(str);
	}
}