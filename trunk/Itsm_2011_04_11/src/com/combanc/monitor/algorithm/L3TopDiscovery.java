package com.combanc.monitor.algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.RouteEntry;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.util.JosqlUtil;


/**
 * 三层设备之间的互连关系发现
 * @author lsj
 * @version 2.0
 */

class L3TopDiscovery {
	private MonitorSubnetService monitorSubnetService;
	private MonitorLinkportService monitorLinkportService;
	private MonitorDeviceService monitorDeviceService;
	// 设备表
	private List<MonitorDevice> deviceList;
	// 出错设备表
	private List<MonitorDevice> errorDeviceList;

	// 三层设备路由表：
//	TextDataFile rtDataFile = new TextDataFile();
//	TableDataSet devRtDataSet = new TableDataSet();
//	TableDataSet subRtDataSet = new TableDataSet();
	List<RouteEntry> devRouteList = new ArrayList<RouteEntry>();
	List<RouteEntry> subRouteList = new ArrayList<RouteEntry>();
	// 三层设备接口表
//	TextDataFile ifDataFile = new TextDataFile();
//	TableDataSet subIfDataSet = new TableDataSet();
//	TableDataSet devIfDataSet = new TableDataSet();
	List<InterfaceEntry> devInterfaceList = new ArrayList<InterfaceEntry>();
	List<InterfaceEntry> subInterfaceList = new ArrayList<InterfaceEntry>();

	// 全局连接端口表：IP、端口、接口、下连IP、下连端口、下连接口、子网，如果下连IP、下连端口空为叶节点
	// TableDataSet linkPortDataSet = new TableDataSet();
	List<MonitorLinkport> linkportList = new ArrayList<MonitorLinkport>();
	// 子网中间结果表：IP、端口、接口、下连IP、下连端口、下连接口，如果下连IP、下连端口空为叶节点
	// TableDataSet linkPortDataSet3 = new TableDataSet();
	private List<MonitorLinkport> linkportList3 = new ArrayList<MonitorLinkport>();

//	String subnetName;
//	Integer subnetId;
	MonitorSubnet monitorSubnet;

	int noLinkPortDevCount;// 未发现互连端口设备计数
	ArrayList rtList = new ArrayList();// L3拓扑发现路由器队列
	String coreIp;

	DeviceSnmpQuery deviceSnmpQuery;//  = new DeviceSnmpQuery();
	
	public void setMonitorSubnetService(MonitorSubnetService monitorSubnetService) {
		this.monitorSubnetService = monitorSubnetService;
	}

	public MonitorSubnetService getMonitorSubnetService() {
		return monitorSubnetService;
	}

	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}

	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}

	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	public List<MonitorLinkport> getLinkportList3() {
		return linkportList3;
	}

	public void setLinkportList3(List<MonitorLinkport> linkportList3) {
		this.linkportList3 = linkportList3;
	}

	public L3TopDiscovery() {
	}
	/*
	public L3TopDiscovery(MonitorSubnet monitorSubnet, String ip) {
		this.monitorSubnet = monitorSubnet;
		this.coreIp = ip;
		try {
			jbInit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "L3TopDiscovery,初始化错误:" + e);
		}
	}
	*/

	public void init(MonitorSubnet monitorSubnet, String ip) throws Exception {
		this.monitorSubnet = monitorSubnet;
		this.coreIp = ip;
		deviceList = monitorSubnetService.getSelectedDeviceBySubnet(monitorSubnet.getId());

		errorDeviceList = deviceList;

//		LinkPortUtilities.initLinkPortTable(linkPortDataSet);
//		linkPortDataSet.open();

//		LinkPortUtilities.initLinkPortTable(linkPortDataSet3);
//		linkPortDataSet3.open();

//		rtDataFile.setFileName(MainFrame.routerFileName);
//		rtDataFile.setLoadAsInserted(true);
//
//		subRtDataSet.setDataFile(rtDataFile);
//		subRtDataSet.open();
//
//		devRtDataSet.setDataFile(rtDataFile);
//		devRtDataSet.open();

//		ifDataFile.setFileName(MainFrame.interfaceFileName);
//		ifDataFile.setLoadAsInserted(true);
//
//		devIfDataSet.setDataFile(ifDataFile);
//		devIfDataSet.open();

//		Column column3 = new Column();
//		column3.setColumnName("直连路由设备");
//		column3.setDataType(com.borland.dx.dataset.Variant.STRING);
//		subIfDataSet.setDataFile(ifDataFile);
//		subIfDataSet.setColumns(new Column[] { column3 });
//		subIfDataSet.open();
	}

	// 扫描指定子网对应的所有链路层设备/VLAN的BRIDGE-MIB中的MAC地址/端口映射表：
	public void run() {
		String info;
		// MainFrame.jLabelScan.setVisible(true);
		if (discovery()) {
			// 显示互连端口发现情况
			if (noLinkPortDevCount == 0)
				info = "拓扑发现完毕，请使用“拓扑图布局”功能布局和生成拓扑图。";
			else
				info = "有 " + noLinkPortDevCount
						+ "台设备未发现互连端口。请重新发现或使用“设备互连端口”查询功能补充设置。";
			// 删除原拓扑图
			File f = new File(monitorSubnet.getName() + "拓扑图.tpg");
			if (f.exists())
				f.delete();
			JOptionPane.showMessageDialog(null, info, "发现结果",
					JOptionPane.INFORMATION_MESSAGE);
		}
		display("子网拓扑发现完毕。");
		// 恢复按钮原文字：
		// 2010-08-20
//		MainFrame.jLabelScan.setVisible(false);
//		MainFrame.queryThreadRun = false;
	}// run

	public boolean discovery() {
		if (!getL3Top())
			return false;
		// 将保存在表3中的发现结果存到连接端口全局表中
		copyResult();

		// 检查是否每个设备都发现了互连端口
		haveLink();

		// 保存各表：
		try {
			// 将数据记录TableDataSet转为list
//			List<MonitorLinkport> list = LinkPortUtilities
//					.tableToList(linkPortDataSet);
			// 将list存数据库
			System.out.println("l3Linkport.size()  >>>>>>>>>>>" + linkportList.size());
			monitorLinkportService.saveLinkPortList(monitorSubnet.getId(), linkportList);
		} catch (Exception e) {
		}
		return true;
	}

	public boolean getL3Top() {
		init();

		if (!scanRouters())
			return false;
		// 发现3层设备连接接口
		display("发现三层设备互连端口，请稍候...");

		l3TopDisc();

		// 保存三层连接表：每条记录都是成对的
		// 2010-08-20	此处需要存储，LinkPortDiscovery文件会调用三层连接表的结果
		// 但目前可以直接访问内存中的	linkportList3
//		try {
//			TextDataFile tmp = new TextDataFile();
//			tmp.setFileName("三层连接表.txt");
//			tmp.save(linkPortDataSet3);
//		} catch (Exception e) {
//		}
		return true;
	}

	private void init() {
		boolean deleted;
		// 删除连接端口表中该子网的所有记录
		/*
		 * if (!linkPortDataSet.isEmpty()) { deleted = true; do { while
		 * (deleted) { if (linkPortDataSet.getString("子网").equals(subnetName)) {
		 * if (linkPortDataSet.atLast()) { linkPortDataSet.deleteRow(); break; }
		 * else { linkPortDataSet.deleteRow(); }// 内层if deleted = true; } else {
		 * deleted = false; }// 外层if }// while1 deleted = true; } while
		 * (linkPortDataSet.next()); }// if
		 */
		// 删除设备表中非子网的所有记录
		/*
		 * if (!deviceDataSet.isEmpty()) { deleted = true; do { while (deleted)
		 * { if (!deviceDataSet.getString("子网").equals(subnetName)) { if
		 * (deviceDataSet.atLast()) { deviceDataSet.deleteRow(); break; } else {
		 * deviceDataSet.deleteRow(); }// 内层if deleted = true; } else { deleted
		 * = false; }// 外层if }// while1 deleted = true; } while
		 * (deviceDataSet.next()); }// if
		 */}//

	// ---------------------------------扫描三层设备--------------------------------
	private boolean scanRouters() {
		int devCount = 0;
		/*
		 * DataRow row = new DataRow(errDeviceDataSet);
		 * errDeviceDataSet.deleteAllRows();
		 */
		errorDeviceList = new ArrayList();
//		subIfDataSet.deleteAllRows();
		// subRtDataSet.deleteAllRows();
		subInterfaceList.clear();
		subRouteList.clear();

		for (MonitorDevice md : deviceList) {
			if (md.getMonitorDeviceType().getCode().equals(MainConstants.DEVICE_SWITCH))
				continue;
			devCount++;
			deviceSnmpQuery.setDisplayStr("读子网 " + monitorSubnet.getName() + " 第 "
					+ devCount + " 台路由设备 ");
			// 如果第一次读不成功则加入出错设备表，第一次扫描结束后重新读一遍。
			if (!readRouter(md)) {
				errorDeviceList.add(md);
			}
		}

		// 重读第一次没有读出来的设备：
		if (!errorDeviceList.isEmpty()) {
			for (MonitorDevice e : errorDeviceList) {
				deviceSnmpQuery.setDisplayStr("重读子网 " + monitorSubnet.getName() + "路由设备 ");
				// 如果第二次没有正确读出路由器的ARP表，则发现不能继续进行
				if (!readRouter(e)) {
					int confirm = JOptionPane.showConfirmDialog(null, "读子网 "
							+ monitorSubnet.getName() + " 路由设备 " + e.getIp() + " 失败。\n"
							+ "是否继续发现？如果继续可能因此异常导致发现结果不完整。", "发现异常",
							JOptionPane.YES_NO_OPTION);
					if (confirm != JOptionPane.YES_OPTION)
						return false;
				}
			}

		}// if
		return true;
	}

	private boolean readRouter(MonitorDevice mDevice) {
		String ret;
		String device = mDevice.getIp();
		// 读IP接口表，获得IP/接口/MAC/上线状态信息
//		devIfDataSet.deleteAllRows();
		devInterfaceList.clear();
		ret = deviceSnmpQuery.getIFStatus(mDevice, devInterfaceList);
		if (!ret.equals("OK"))
			return false;
		// 读路由表
		// devRtDataSet.deleteAllRows();
		devRouteList.clear();
		ret = deviceSnmpQuery.getRouterTable(mDevice, devRouteList);
		if (!ret.equals("OK"))
			return false;

		addIfInfo(device);// 将接口及MAC地址信息加入子网路由设备接口表和设备MAC地址表
		addRtInfo(device);// 将设备路由表的非直连NEXTHOP记录加入子网路由表
		return true;
	}

	// 将当前设备的接口IP地址增加到表中：IP为关键字，不重复
	private void addIfInfo(String device) {
		if (devInterfaceList.isEmpty())
			return;
		for (InterfaceEntry ie : devInterfaceList) {
			// BAY8610能读出IP/接口关系，但接口表中只有物理接口，没有IP/VLAN软接口，因而读不出IP接口状态，
			// 不能只选择上线的接口。 if(devIfDataSet.getBoolean("上线"))筛选条件去掉。
			if (JosqlUtil.containIpSubInterface(subInterfaceList, device) == null) {
				InterfaceEntry ieSub = new InterfaceEntry();
				ieSub.setIp(ie.getIp());
				ieSub.setInterface_(ie.getInterface_());
				ieSub.setDescription(ie.getDescription());
				ieSub.setLinkedDevice(device);
				subInterfaceList.add(ieSub);
			}
		}
		/*
		DataRow row1 = new DataRow(subIfDataSet);
		DataRow loc1 = new DataRow(subIfDataSet, "IP");

		devIfDataSet.first();
		do {
			// BAY8610能读出IP/接口关系，但接口表中只有物理接口，没有IP/VLAN软接口，因而读不出IP接口状态，
			// 不能只选择上线的接口。 if(devIfDataSet.getBoolean("上线"))筛选条件去掉。
			loc1.setString("IP", devIfDataSet.getString("IP"));
			if (!subIfDataSet.locate(loc1, Locate.FIRST)) {
				row1.setString("IP", devIfDataSet.getString("IP"));
				row1.setString("接口", devIfDataSet.getString("接口"));
				row1.setString("接口描述", devIfDataSet.getString("接口描述"));
				row1.setString("直连路由设备", device);
				subIfDataSet.addRow(row1);
			}
		} while (devIfDataSet.next());
		*/
	}

	// 将当前路由器的路由表加入子网路由表中
	private void addRtInfo(String device) {
		if (devRouteList.isEmpty())
			return;
		

		for(RouteEntry re : devRouteList) {
			RouteEntry subRe = new RouteEntry();
			subRe.setLinkedDevice(device);
			subRe.setSegment(re.getSegment());
			subRe.setMask(re.getMask());
			subRe.setType(re.getType());
			subRe.setNextHop(re.getNextHop());
			subRe.setInterface_(re.getInterface_());
			subRe.setDescription(re.getDescription());
			subRouteList.add(subRe);
		}
		/*
		DataRow row = new DataRow(subRtDataSet);
		devRtDataSet.first();
		do {
			row.setString("直连路由设备", device);
			row.setString("网段", devRtDataSet.getString("网段"));
			row.setString("掩码", devRtDataSet.getString("掩码"));
			row.setString("类型", devRtDataSet.getString("类型"));
			row.setString("下一跳", devRtDataSet.getString("下一跳"));
			row.setString("接口", devRtDataSet.getString("接口"));
			row.setString("接口描述", devRtDataSet.getString("接口描述"));
			subRtDataSet.addRow(row);
		} while (devRtDataSet.next());
		*/
	}

	// -------------------------3层拓扑发现------------------------------------------
	// 从种子路由器开始，广度优先做三层拓扑发现
	private void l3TopDisc() {
		int listPt = 0;// 队列头指针
		rtList.add(listPt, coreIp);// 将种子路由器作为队列头

		// 如果队列不空则继续
		while (listPt < rtList.size()) {
			// 找到当前队列指针所指路由设备的非直连下一跳，如果二者之间还没有连接，则连接它们
			linkNextHop((String) rtList.get(listPt));
			listPt++;// 指针后移到队列中的下一路由设备
		}
	}

	// 连接当前路由器的所有邻居
	private void linkNextHop(String device) {
		if (subRouteList.isEmpty())
			return;
		String[] nextHop;
		String[] localIf;
//		DataRow loc = new DataRow(linkPortDataSet3,
//				new String[] { "IP", "下连IP" });

		int curRow;
		for(RouteEntry re : subRouteList) {
			if (!re.getLinkedDevice().equals(device))
				continue;
			if (re.getType() != null && re.getType().equals("直连"))
				continue;
			curRow = subRouteList.indexOf(re);
			// 找到非直连下一跳的设备地址、对应接口
			nextHop = getNextHop(re.getNextHop());
			if (nextHop != null) {
				// 如果device到nexthop的记录不存在，并且nexthop到device的记录也不存在时连接二者
				if (!JosqlUtil.conatinIpDownlink(linkportList3, device, nextHop[0]) 
						&& !JosqlUtil.conatinIpDownlink(linkportList3, nextHop[0], device)) {
					// 取得非直连下一跳IP对应的当前设备的转发接口
					localIf = getLocalInterface(curRow, device, re.getNextHop());
					if (localIf != null)
						addToResult(device, localIf[0], localIf[1], nextHop[0],
								nextHop[1], nextHop[2]);
					else
						addToResult(device, re.getInterface_(), re
								.getDescription(), nextHop[0], nextHop[1],
								nextHop[2]);
				}
				// 如果邻居不在队列中则加入
				if (!rtList.contains(nextHop[0]))
					rtList.add(nextHop[0]);
			}
			/*
			if (nextHop != null) {
				// 如果device到nexthop的记录不存在，并且nexthop到device的记录也不存在时连接二者
				loc.setString("IP", device);
				loc.setString("下连IP", nextHop[0]);
				if (!linkPortDataSet3.locate(loc, Locate.FIRST)) {
					loc.setString("IP", nextHop[0]);
					loc.setString("下连IP", device);
					if (!linkPortDataSet3.locate(loc, Locate.FIRST)) {
						// 取得非直连下一跳IP对应的当前设备的转发接口
						localIf = getLocalInterface(curRow, device, re.getNextHop());
						if (localIf != null)
							addToResult(device, localIf[0], localIf[1],
									nextHop[0], nextHop[1], nextHop[2]);
						else
							addToResult(device, re.getInterface_(), re
									.getDescription(), nextHop[0], nextHop[1],
									nextHop[2]);
					}
				}
				// 如果邻居不在队列中则加入
				if (!rtList.contains(nextHop[0]))
					rtList.add(nextHop[0]);
			}
			*/
		}
		/*
		subRtDataSet.first();
		do {
			if (!subRtDataSet.getString("直连路由设备").equals(device))
				continue;
			if (subRtDataSet.getString("类型").equals("直连"))
				continue;
			// 找到非直连下一跳的设备地址、对应接口
			nextHop = getNextHop(subRtDataSet.getString("下一跳"));
			if (nextHop != null) {
				// 如果device到nexthop的记录不存在，并且nexthop到device的记录也不存在时连接二者
				loc.setString("IP", device);
				loc.setString("下连IP", nextHop[0]);
				if (!linkPortDataSet3.locate(loc, Locate.FIRST)) {
					loc.setString("IP", nextHop[0]);
					loc.setString("下连IP", device);
					if (!linkPortDataSet3.locate(loc, Locate.FIRST)) {
						// 取得非直连下一跳IP对应的当前设备的转发接口
						localIf = getLocalInterface(device, subRtDataSet
								.getString("下一跳"));
						if (localIf != null)
							addToResult(device, localIf[0], localIf[1],
									nextHop[0], nextHop[1], nextHop[2]);
						else
							addToResult(device, subRtDataSet.getString("接口"),
									subRtDataSet.getString("接口描述"), nextHop[0],
									nextHop[1], nextHop[2]);
					}
				}
				// 如果邻居不在队列中则加入
				if (!rtList.contains(nextHop[0]))
					rtList.add(nextHop[0]);
			}
		} while (subRtDataSet.next());
		*/
	}

	// 根据路由表的NEXTHOP项接口获得邻居IP及其接口信息，注意NEXTHOP的IP不一定是邻居的IP
	// 可能只是其某个接口的IP
	private String[] getNextHop(String ip) {
		String[] result = null;
		InterfaceEntry ie = JosqlUtil.containIpSubInterface(subInterfaceList, ip);
		if(ie != null) {
			result = new String[3];
			result[0] = ie.getLinkedDevice();
			result[1] = ie.getInterface_();
			result[2] = ie.getDescription();
		}
		/*
		DataRow loc = new DataRow(subIfDataSet, "IP");
		loc.setString("IP", ip);
		if (subIfDataSet.locate(loc, Locate.FIRST)) {
			result = new String[3];
			result[0] = subIfDataSet.getString("直连路由设备");
			result[1] = subIfDataSet.getString("接口");
			result[2] = subIfDataSet.getString("接口描述");
		}
		*/
		return result;
	}

	// 取得非直连下一跳IP对应的当前设备的转发接口，查找路由表，找到其所在网段的转发接口
	private String[] getLocalInterface(int curRow, String device, String addr) {
		String[] result = null;
		
		// int curRow = subRtDataSet.getRow();
		for(int i = curRow; i < subRouteList.size(); i++) {
			RouteEntry re = subRouteList.get(i);
			if (!re.getLinkedDevice().equals(device))
				continue;
			if (!re.getType().equals("直连"))
				continue;
			if (!Tools.ipInSubnet(addr, re.getSegment(),
					re.getMask()))
				continue;
			result = new String[2];
			result[0] = re.getInterface_();
			result[1] = re.getDescription();
			break;
		}
		
//		subRtDataSet.goToRow(curRow);
		/*
		int curRow = subRtDataSet.getRow();
		subRtDataSet.first();
		do {
			if (!subRtDataSet.getString("直连路由设备").equals(device))
				continue;
			if (!subRtDataSet.getString("类型").equals("直连"))
				continue;
			if (!Tools.ipInSubnet(addr, subRtDataSet.getString("网段"),
					subRtDataSet.getString("掩码")))
				continue;
			result = new String[2];
			result[0] = subRtDataSet.getString("接口");
			result[1] = subRtDataSet.getString("接口描述");
			break;
		} while (subRtDataSet.next());
		subRtDataSet.goToRow(curRow);
		*/
		return result;
	}

	// 将匹配成功的设备/接口对加入三层互连结果表中
	private void addToResult(String srcDev, String srcIf, String srcIfDesrc,
			String dstDev, String dstIf, String dstIfDesrc) {
		MonitorLinkport ml = new MonitorLinkport();
		ml.setIp(srcDev);
		ml.setInterface_(srcIf);
		ml.setDescription(srcIfDesrc);
		ml.setDownlinkIp(dstDev);
		ml.setDownlinkInterface(dstIf);
		ml.setDownlinkDesc(dstIfDesrc);
		linkportList3.add(ml);
		/*
		DataRow row = new DataRow(linkPortDataSet3);
		row.setString("IP", srcDev);
		row.setString("接口", srcIf);
		row.setString("接口描述", srcIfDesrc);
		row.setString("下连IP", dstDev);
		row.setString("下连接口", dstIf);
		row.setString("下连接口描述", dstIfDesrc);
		linkPortDataSet3.addRow(row);
		*/
	}

	// 将结果表3保存到连接端口表中。记录在该表中的顺序与拓扑图生成的结果无关。
	private void copyResult() {
		System.out.println("linkportList3   size  " + linkportList3.size());
		if (linkportList3.isEmpty())
			return;
		// DataRow row = new DataRow(linkPortDataSet);
		
		// 首先复制成对记录：
		for(MonitorLinkport ml3 : linkportList3) {
			MonitorLinkport ml = new MonitorLinkport();
			ml.setIp(ml3.getIp());
			ml.setInterface_(ml3.getInterface_());
			ml.setDescription(ml3.getDescription());
			ml.setDownlinkIp(ml3.getDownlinkIp());
			ml.setDownlinkInterface(ml3.getDownlinkInterface());
			ml.setDownlinkDesc(ml3.getDownlinkDesc());
			ml.setMonitorSubnet(monitorSubnet);
			linkportList.add(ml);
		}
		// 两个直连路由器之间只有唯一一对接口互连。
		// 路由设备的每个接口唯一，但同一接口可以有多个连接，在连接表中同一设备的同一接口不出现两次。
		// 然后根据下位下连IP、下连接口是否已作为上位IP、上位接口出现决定加入
		
		// 2010-08-20 此处判断是否不需要判断子网ID ？？
//		DataRow loc = new DataRow(linkPortDataSet, new String[] { "IP", "接口",
//				"子网ID" });
		for(MonitorLinkport ml3 : linkportList3) {
//			loc.setString("IP", linkPortDataSet3.getString("下连IP"));
//			loc.setString("接口", linkPortDataSet3.getString("下连接口"));
//			loc.setInt("子网ID", subnetId);
			// if (!linkPortDataSet.locate(loc, Locate.FIRST)) {
			if(!JosqlUtil.conatinIpInterface(linkportList, ml3.getDownlinkIp(), ml3.getDownlinkInterface())) {
				MonitorLinkport ml = new MonitorLinkport();
				ml.setIp(ml3.getDownlinkIp());
				ml.setInterface_(ml3.getDownlinkInterface());
				ml.setDescription(ml3.getDownlinkDesc());
				ml.setDownlinkIp("");
				ml.setDownlinkInterface("");
				ml.setDownlinkDesc("");
				ml.setMonitorSubnet(monitorSubnet);
				linkportList.add(ml);
			}
		}
		
		/*
		linkPortDataSet3.first();
		// 首先复制成对记录：
		do {
			
			row.setString("IP", linkPortDataSet3.getString("IP"));
			row.setString("接口", linkPortDataSet3.getString("接口"));
			row.setString("接口描述", linkPortDataSet3.getString("接口描述"));
			row.setString("下连IP", linkPortDataSet3.getString("下连IP"));
			row.setString("下连接口", linkPortDataSet3.getString("下连接口"));
			row.setString("下连接口描述", linkPortDataSet3.getString("下连接口描述"));
			row.setString("子网名", monitorSubnet.getName());
			row.setInt("子网ID", subnetId);
			linkPortDataSet.addRow(row);
		} while (linkPortDataSet3.next());
		// 两个直连路由器之间只有唯一一对接口互连。
		// 路由设备的每个接口唯一，但同一接口可以有多个连接，在连接表中同一设备的同一接口不出现两次。
		// 然后根据下位下连IP、下连接口是否已作为上位IP、上位接口出现决定加入
		DataRow loc = new DataRow(linkPortDataSet, new String[] { "IP", "接口",
				"子网ID" });
		linkPortDataSet3.first();
		do {
			loc.setString("IP", linkPortDataSet3.getString("下连IP"));
			loc.setString("接口", linkPortDataSet3.getString("下连接口"));
			loc.setInt("子网ID", subnetId);
			if (!linkPortDataSet.locate(loc, Locate.FIRST)) {
				row.setString("IP", linkPortDataSet3.getString("下连IP"));
				row.setString("接口", linkPortDataSet3.getString("下连接口"));
				row.setString("接口描述", linkPortDataSet3.getString("下连接口描述"));
				row.setString("下连IP", "");
				row.setString("下连接口", "");
				row.setString("下连接口描述", "");
				row.setString("子网名", monitorSubnet.getName());
				row.setInt("子网ID", subnetId);
				linkPortDataSet.addRow(row);
			}
		} while (linkPortDataSet3.next());
		*/
	}

	// 检查是否每个设备都发现了互连端口
	private void haveLink() {
		// 检查子网交换设备中是否有未发现互连端口的设备：
//		DataRow loc = new DataRow(linkPortDataSet,
//				new String[] { "IP", "子网ID" });
		noLinkPortDevCount = 0;
		for (MonitorDevice md : deviceList) {
//			loc.setString("IP", md.getIp());
//			loc.setInt("子网ID", subnetId);
			// if (!linkPortDataSet.locate(loc, Locate.FIRST)) {
			if (!JosqlUtil.containIpLinkport(linkportList, md.getIp())) {
				noLinkPortDevCount++;
				JOptionPane.showMessageDialog(null, "设备 " + md.getIp()
						+ " 没有发现互连端口。");
			}
		}
	}

	private void display(String str) {
		// MainFrame.statusBar.setText(str);
		System.out.println(str);
	}
}