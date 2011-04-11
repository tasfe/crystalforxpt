package com.combanc.monitor.algorithm;

/**
 * <p>Title:读取网络上所有路由设备的ARP表。 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author inscribed
 * @version 1.0
 */
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dx.dataset.DataSet;
import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.comet.DwrService;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorAlertSmalltype;
import com.combanc.monitor.pojo.MonitorArpAlertWhiteMac;
import com.combanc.monitor.pojo.MonitorArpLog;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorSegment;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.pojoext.InterfaceEntry;
import com.combanc.monitor.pojoext.IpMacEntry;
import com.combanc.monitor.quartz.ComputerScan;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorAlertTypeService;
import com.combanc.monitor.service.MonitorArpAlertWhiteMacService;
import com.combanc.monitor.service.MonitorArpLogService;
import com.combanc.monitor.service.MonitorComputerService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.JosqlUtil;

public class ArpModeScan {
	
	long logTime;
	int arpLimen = SystemParamConstants.SYSTEM_ARP_LIMEN_DEFUALT_VALUE;
	SnmpTarget target = new SnmpTarget();
	/** 路由和三层交换机接口mac地址表，对应“设备MAC表” **/
	List<IpMacEntry> ipMacEntryList = new ArrayList<IpMacEntry>();
	/** 三层设备接口表 **/
	List<InterfaceEntry> interfaceEntryList = new ArrayList<InterfaceEntry>();

	HashSet deviceMACHashSet = new HashSet();
	HashSet alertMACHashSet = new HashSet();
	
	/** ARP结果列表 **/
	private List<ArpRecord> arpRecordList = new ArrayList<ArpRecord>();
	
	/** 设备列表 **/
	private List<MonitorDevice> deviceList;
	/** 中途出错设备表 **/
	private List<MonitorDevice> errDeviceList;
	
	
	private MonitorSubnetService monitorSubnetService;			// 子网服务
	private MonitorDeviceService monitorDeviceService;			// 设备服务
	private MonitorComputerService monitorComputerService;		// 计算机服务，实现Arp报警，填写“上连设备及端口”等信息
	private MonitorAlertService monitorAlertService;			// 报警服务
	private MonitorAlertTypeService monitorAlertTypeService;	// 报警类型服务
	private MonitorArpLogService monitorArpLogService;
	private MonitorSystemParamService monitorSystemParamService;
	private MonitorArpAlertWhiteMacService monitorArpAlertWhiteMacService;
	
	private DeviceSnmpQuery deviceSnmpQuery;
	private MACPortScan macPortScan;
	private DwrService dwrService;
	private Alerter alerter;

	
	
	
	public MonitorArpAlertWhiteMacService getMonitorArpAlertWhiteMacService() {
		return monitorArpAlertWhiteMacService;
	}

	public void setMonitorArpAlertWhiteMacService(
			MonitorArpAlertWhiteMacService monitorArpAlertWhiteMacService) {
		this.monitorArpAlertWhiteMacService = monitorArpAlertWhiteMacService;
	}

	public MonitorArpLogService getMonitorArpLogService() {
		return monitorArpLogService;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

	public MonitorAlertTypeService getMonitorAlertTypeService() {
		return monitorAlertTypeService;
	}

	public void setMonitorAlertTypeService(
			MonitorAlertTypeService monitorAlertTypeService) {
		this.monitorAlertTypeService = monitorAlertTypeService;
	}

	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public void setMonitorArpLogService(MonitorArpLogService monitorArpLogService) {
		this.monitorArpLogService = monitorArpLogService;
	}

	public DwrService getDwrService() {
		return dwrService;
	}

	public void setDwrService(DwrService dwrService) {
		this.dwrService = dwrService;
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

	public MonitorComputerService getMonitorComputerService() {
		return monitorComputerService;
	}

	public void setMonitorComputerService(
			MonitorComputerService monitorComputerService) {
		this.monitorComputerService = monitorComputerService;
	}

	public MonitorAlertService getMonitorAlertService() {
		return monitorAlertService;
	}

	public void setMonitorAlertService(MonitorAlertService monitorAlertService) {
		this.monitorAlertService = monitorAlertService;
	}

	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	public MACPortScan getMacPortScan() {
		return macPortScan;
	}

	public void setMacPortScan(MACPortScan macPortScan) {
		this.macPortScan = macPortScan;
	}

	public ArpModeScan() {
		
	}
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			macPortScan.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ARP日志时间：
		logTime = System.currentTimeMillis();
		
		target.setLoadFromCompiledMibs(true);
		target.setTimeout(MainConstants.SNMP_TIME_OUT);
		target.setRetries(MainConstants.SNMP_RETRY);
		
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_ARP_LIMEN);
		if(null != param && null !=param.getValue() && !"".equals(param.getValue())){
			this.arpLimen = Integer.parseInt( param.getValue());
		}
	}

	public boolean scan(List<MonitorDevice> tempDeviceList, List<MonitorSegment> segmentList, List<MonitorComputer> snapshotList) {
		if (tempDeviceList.isEmpty())
			return false;
		this.deviceList = tempDeviceList;
		// 快照当前行号：
		int currentRowNum = 0;
		// NETBIOS扫描
		NBTModeScan nbtScan = new NBTModeScan();


		// 1、如果是PING扫描方式
		if (ComputerScan.pingScan) {
		 
			if (!Tools.pingSegment(segmentList)) {
					// 忽略PING错误的判断在TOOLS中完成。
					return false;
			}
			 
			// 等待10秒，使路由器正确生成ARP表，确保SNMP读出正确。
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}// PING扫描方式
		
		// 2010-06-17，由于必须在计算机表中查找到MAC地址才会生成ARP报警
		// 而正确配置不会导致设备出现在计算机表中。 不再读取所有路由设备的IP接口状态表
		// getDeviceMAC，getDeviceMAC函数不再使用
		// 1.5、读路由设备接口状态表（已删除）

		// 2、读路由设备ARP表，如果不成功则删除无效记录并继续到下一子网：
		display("读路由设备ARP表...");
		scanSubnet(deviceList, segmentList, snapshotList);
		display("读路由设备ARP表完毕。");		
		arpAlert();
		// 3、读子网的接入设备，获得MAC/PORT信息，如果不成功继续到下一子网
		if (ComputerScan.l2Scan) {
			display("读交换设备MAC地址转发表...");
			macPortScan.scanDevice(deviceList, snapshotList);
			display("读交换设备MAC地址转发表完毕。");
		}	
		//saveArpLog();
		// 如果没有得到任何入网计算机数据，则返回错误。
		if (snapshotList.isEmpty())
			return false;
		else
			return true;
	}

	// 删除读取不完全子网的记录，防止错误数据返回
	private void delInvalidRec(String snet, DataSet dataSet) {
		if (dataSet.isEmpty())
			return;
		boolean deleted = true;
		dataSet.first();
		do {
			while (deleted) {
				if (dataSet.getString("子网").equals(snet)) {
					if (dataSet.atLast()) {
						dataSet.deleteRow();
						break;
					} else {
						dataSet.deleteRow();
					}// 内层if
					deleted = true;
				} else {
					deleted = false;
				}// 外层if
			}// while1
			deleted = true;
		} while (dataSet.next());
	}

	// 扫描指定子网对应路由设备的ARP表：
	public boolean scanSubnet(List<MonitorDevice> deviceList, List<MonitorSegment> segmentList, List<MonitorComputer> snapshotList) {
		String ret;
	
		errDeviceList = new ArrayList();

		for (MonitorDevice d : deviceList) {
			if (d.getMonitorDeviceType().getCode().equals(MainConstants.DEVICE_SWITCH))
				continue;
			ret = scanDevice(d.getIp(), d.getReadCommunity(), 
					segmentList, snapshotList);
			if (!ret.equals("OK")) {
				// 如果第一次读不成功，则加入出错重读表中：
				errDeviceList.add(d);
			}
		}
		// 如果存在设备没有正确读出，则重读这些设备
		if (!errDeviceList.isEmpty()) {
			for (MonitorDevice e : errDeviceList) {
				ret = scanDevice(e.getIp(), e.getReadCommunity(), 
						segmentList, snapshotList);
				if (ret.equals("超时"))
					// return false;// 重读超时不再继续扫描
					continue;	// 2010-06-17 重读超时则继续
			}
		}// if
		return true;
	}

	public String scanDevice(String device, String commStr,List<MonitorSegment> segmentList, List<MonitorComputer> snapshotList) {
		
		int readCount = 0, i;
		String result[] = new String[3];
		String tempStr;
		String indexOid;
		int indexOidLen;
		boolean firstRecord = true;
		int errorCode, errorIndex;
		String errorStr;
		String retStr = "";

		target.setTargetHost(device);
		target.setCommunity(commStr);

		String[] ipToMACOIDs = new String[3];
		ipToMACOIDs[0] = ".1.3.6.1.2.1.4.22.1.3";// OID of
		// ipNetToMediaNetAddress,IP
		indexOid = ipToMACOIDs[0];
		indexOidLen = indexOid.length();
		ipToMACOIDs[1] = ".1.3.6.1.2.1.4.22.1.2";// OID of
		// ipNetToMediaPhysAddress,MAC
		ipToMACOIDs[2] = ".1.3.6.1.2.1.4.22.1.4";// OID of ipNetToMediaType
		target.setObjectIDList(ipToMACOIDs);

		display("读路由设备 " + device + " IP地址转发表...");
		while (true) {
			result = target.snmpGetNextList();

			errorCode = target.getErrorCode();
			errorIndex = target.getErrorIndex();

			if (errorCode != 0) {
				if (errorCode == 22 && errorIndex == 0) {// 超时
					display("读路由设备 " + device + " IP地址转发表超时。");
					return "超时";
				}
				display("读路由设备 " + device + " IP地址转发表出错。");
				return "出错";
			}

			tempStr = target.getSnmpOID().toString();
			if (tempStr.length() < indexOidLen) {
				if (firstRecord)
					return "表空";
				else
					return "OK";
			}

			tempStr = tempStr.substring(0, indexOidLen);
			if (!tempStr.equals(indexOid)) {
				if (firstRecord)
					return "表空";
				else
					return "OK";
			}

			readCount++;
			display("读路由设备 " + device + " IP地址转发表 "
					+ readCount + " 条记录。");
			firstRecord = false;
			if (deviceMACHashSet.contains(result[1])) {
				// System.out.println("remove: IP   " + result[0] + "---------"
				// + result[1]);
				continue;
			} else {
				// System.out.println("subnet:  " + subnet + "    " + result[0]
				// + "----" + result[1] + "----" +result[2] + "----");
				saveList(result,  segmentList, snapshotList);
			}
		} // while
	}// scanOneRt

	private void saveList(String result[], List<MonitorSegment> segmentList, List<MonitorComputer> snapshotList) {
		// 将读出的记录加入ARP日志中
		restoreLog(result);
		Timestamp now = new Timestamp(0);
		now.setTime(System.currentTimeMillis());
		
		// 只包括动态、静态类型的记录，不包括其它和无效类型的记录
		// other(1), invalid(2), dynamic(3), static(4)
		if (result[2].equals("1") || result[2].equals("other(1)"))
			return;
		if (result[2].equals("2") || result[2].equals("invalid(2)"))
			return;

		// 检查IP是否在该子网的网段中。如果网段表中没有该子网的记录，则认为IP在子网中。
		//if (ipNotInSegment(result[0], mSubnet.getName(), segmentList))
		//	return;

		// 实验中发现ADVENT SNMP API有BUG：千分之一的概率下MAC地址不以字符串而以字节形式
		// 返回，如：RTL)!x，而实际的字符串形式的MAC应为：52 54 4c 29 21 58。这种情况下
		// 必须转换。
		if (result[1].length() == 6)
			result[1] = Tools.wrongBytesToStr(result[1]);
		// 检查MAC地址格式是否合法：
		if (!Tools.macFormatOK(result[1]))
			return;

		// 以MAC/子网对作为唯一有效标识加入表中
		// 2010-10-10 逻辑改为只以MAC作为唯一标识，因为计算机表中已经不包含子网信息，而是和设备关联
		if (!JosqlUtil.containIpSnapshot(snapshotList, result[1])) {
			// 将IP地址的最后一个字节统一转成3位长，使排序符合习惯。
			MonitorComputer ms = new MonitorComputer();
			ms.setIp(result[0]);		//IP
			ms.setMac(result[1]);		// MAC
			ms.setHostName("-");		// 初始化主机名
			ms.setComputerName("-");	// 初始化计算机名
			ms.setDomain("-");			// 初始化域或组名
			ms.setLoginName("-");		// 初始化登录名
			ms.setMonitorDevice(null);	// 初始上连设备
			ms.setDevicePort("-");		// 初始化端口
			ms.setDeviceInterface("-");	// 初始化接口
			ms.setInterfaceDescription("-");	// 初始化接口描述
//			newRow.setString("子网", subnet);// 子网
//			newRow.setInt("subnetId", subnetId);
			ms.setDiscoveryTime(now);
			snapshotList.add(ms);
		}
	}
	
	// 将读出的记录加入ARP日志中
	private void restoreLog(String[] result) {
		
		boolean hasValue = false;
		ArpRecord theAr = new ArpRecord(result[1]);
		for (ArpRecord ar : arpRecordList) {
			if (theAr.equals(ar)) {
				ar.mergeRecord(theAr);
				hasValue = true;
				break;
			}
		}
		if (!hasValue) {
			arpRecordList.add(theAr);
		}
		
		MonitorArpLog arpLog = monitorArpLogService.findByIpAndDate(result[0], new Date(this.logTime));
		if(null == arpLog){
			arpLog = new MonitorArpLog();
			arpLog.setIp(result[0]);
			arpLog.setMac(result[1]);
			arpLog.setType("-");
			if (result[2].equals("1") || result[2].equals("other(1)"))
				arpLog.setType("其它");
			if (result[2].equals("2") || result[2].equals("invalid(2)"))
				arpLog.setType("无效");
			if (result[2].equals("3") || result[2].equals("dynamic(3)"))
				arpLog.setType("动态");
			if (result[2].equals("4") || result[2].equals("static(4)"))
				arpLog.setType("静态");
			arpLog.setFirstTime(new Timestamp(this.logTime));
			arpLog.setLastTime(new Timestamp(this.logTime));
			monitorArpLogService.save(arpLog);
		}else{
			arpLog.setLastTime(new Timestamp(this.logTime));
			monitorArpLogService.update(arpLog);
		}
		arpLog.setIp(result[0]);
/*		DataRow loc = new DataRow(logDataSet,
				new String[] { "IP", "MAC", "子网" });
		DataRow row = new DataRow(logDataSet);

		loc.setString("IP", result[0]);
		loc.setString("MAC", result[1]);
		loc.setString("子网", subnet);
		boolean hasValue = false;
		ArpRecord theAr = new ArpRecord(result[1], subnet);
		for (ArpRecord ar : arpRecordList) {
			if (theAr.equals(ar)) {
				ar.mergeRecord(theAr);
				hasValue = true;
				break;
			}
		}
		if (!hasValue) {
			arpRecordList.add(theAr);
		}
		if (!logDataSet.locate(loc, Locate.FIRST)) {
			row.setString("IP", result[0]); // IP
			row.setString("MAC", result[1]); // MAC
			row.setString("类型", "-");
			if (result[2].equals("1") || result[2].equals("other(1)"))
				row.setString("类型", "其它");
			if (result[2].equals("2") || result[2].equals("invalid(2)"))
				row.setString("类型", "无效");
			if (result[2].equals("3") || result[2].equals("dynamic(3)"))
				row.setString("类型", "动态");
			if (result[2].equals("4") || result[2].equals("static(4)"))
				row.setString("类型", "静态");
			row.setTimestamp("首次发现时间", logTime);
			row.setTimestamp("最后发现时间", logTime);
			row.setString("子网", subnet);
			logDataSet.addRow(row);
			// }
		} else {
			logDataSet.setTimestamp("最后发现时间", logTime);
		}
		*/
	}

	private void saveArpLog() {
	/*	logDataFile.setFileName(logFile);
		try {
			logDataFile.save(logDataSet);
		} catch (Exception ex) {
		}*/
	}
	/** ARP 报警 **/
	private void arpAlert() {
 		synchronized (Alerter.arpAlertList) {
 			Alerter.arpAlertList.clear();
		}
		for (ArpRecord ar : arpRecordList) {
			if ("00 00 00 00 00 00".equals(ar.getMac())) {
				continue;
			}
			if (ar.getMac().indexOf("00 00 00 00") >= 0) {
				continue;
			}
			// 如果超过ARP报警阈值，且mac不存在于白名单中，则报警
			if (ar.getCount() >= arpLimen && !isExistInWhiteMacList(ar.getMac())) {
				MonitorAlert alert = new MonitorAlert();
				// ARP报警 type 5-其它， small type 17-ARP报警
				alert.setMonitorAlertSmalltype(new MonitorAlertSmalltype(MainConstants.SALERT_ARP));
				alert.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_OTHER));
				alert.setFile(0);
				alert.setMac(ar.getMac());
				Timestamp firstTime = new Timestamp(System.currentTimeMillis());
				alert.setFirstTime(firstTime);
				alert.setLastTime(firstTime);
				// 查找计算机表，如果能找到mac子网对，则填充上连设备和端口，接口，接口描述等选项
				// 是否填充 IP 选项待考虑
				// 这样在报警窗口里可以直接使用“接口测控”来测控上连设备的相应接口
				MonitorComputer computer = monitorComputerService.findByMac( ar.getMac());
				if (computer != null ) {
					alert.setIp(computer.getIp());
					alert.setUplink(computer.getMonitorDevice().getIp());
					alert.setPort(computer.getDevicePort());
					alert.setInterface_(computer.getDeviceInterface());
					alert.setDescription(computer.getInterfaceDescription());
					monitorAlertService.save(alert);
					synchronized (Alerter.arpAlertList) {
						Alerter.arpAlertList.add(alert);
					}
				}
			}
		}
		synchronized (Alerter.arpAlertList) {
			alerter.checkArpAlert();
		}

	}

	/*private void arpAlert(DataSet arpDataSet, Integer subnetId, String subnetName) {
		synchronized (MainFrame.alerter.arpAlertList) {
			MainFrame.alerter.arpAlertList.clear();
		}
		DataRow loc = new DataRow(arpDataSet, new String[] { "MAC", "子网" });
		int rowFirst = 0;
		alertMACHashSet.clear();
		arpDataSet.first();
		do {
			if (arpDataSet.getString("MAC").equals("00 00 00 00 00 00")) {
				//System.out.println("跳过ARP报警： 00 00 00 00 00 00");
				continue;
			}
			if (arpDataSet.getString("MAC").indexOf("00 00 00 00") >= 0) {
				//System.out.println("跳过ARP报警：" + arpDataSet.getString("MAC"));
				continue;
			}
			loc.setString("MAC", arpDataSet.getString("MAC"));
			loc.setString("子网", arpDataSet.getString("子网"));
			int macNum = 0;
			String mac = arpDataSet.getString("MAC");
			String macSub = mac + "-" + arpDataSet.getString("子网");
			// HashSet记录已经遍历过的mac sub对，跳过
			if (alertMACHashSet.contains(macSub)) {
				continue;
			} else {
				alertMACHashSet.add(macSub);
			}
			if (arpDataSet.locate(loc, Locate.FIRST)) {
				// 初始记录的行数
				rowFirst = arpDataSet.getRow();
				macNum++;
			}
			while (arpDataSet.locate(loc, Locate.NEXT)) {
				macNum++;
			}
			// 如果超过ARP报警阈值，且mac不存在于白名单中，则报警
			if (macNum >= MainFrame.arpLimen && !isExistInWhiteMacList(arpDataSet.getString("MAC"))) {
				Alert alert = new Alert();
				// ARP报警 type 5-其它， small type 17-ARP报警
				alert.setAlertSmalltype(new AlertSmalltype(Constants.SALERT_ARP));
				alert.setAlertType(monitorAlertTypeService.findById(Constants.ALERT_OTHER));
				alert.setAlertFile(0);
				alert.setAlertMac(arpDataSet.getString("MAC"));
				alert.setSubnet(new Subnet(subnetId));
				Timestamp firstTime = new Timestamp(System.currentTimeMillis());
				alert.setAlertFirstTime(firstTime);
				// 查找计算机表，如果能找到mac子网对，则填充上连设备和端口，接口，接口描述等选项
				// 是否填充 IP 选项待考虑
				// 这样在报警窗口里可以直接使用“接口测控”来测控上连设备的相应接口
				Computer computer = monitorComputerService.findBySubnetMac(subnetId,
						arpDataSet.getString("MAC"));
				if (computer != null && arpDataSet.getString("子网").equals(subnetName)) {
					alert.setAlertIp(computer.getIp());
					alert.setAlertUplink(computer.getConnectionDevice());
					alert.setAlertPort(computer.getDevicePort());
					alert.setAlertInterface(computer.getDeviceInterface());
					alert.setAlertIfDesc(computer.getInterfaceDescription());
					Integer id = monitorAlertService.save(alert);
					Alert p = monitorAlertService.findById(id);
					synchronized (MainFrame.alerter.arpAlertList) {
						MainFrame.alerter.arpAlertList.add(p);
					}
				}
			}
			arpDataSet.goToRow(rowFirst);

		} while (arpDataSet.next());
		synchronized (MainFrame.alerter.arpAlertList) {
			MainFrame.alerter.checkArpAlert();
		}
	}*/

	boolean isExistInWhiteMacList(String sMac) {
		if (sMac == null || sMac.isEmpty())
			return false;
		MonitorArpAlertWhiteMac arpWhiteMac = monitorArpAlertWhiteMacService.findByMac(sMac);
		if(null != arpWhiteMac)
			return true;
		else
			return false;
	}

	boolean ipNotInSegment(String ip, String subnet, List<MonitorSegment> segmentList) {
		if (segmentList == null || segmentList.isEmpty())
			return false;// 如果没有设置网段则不过滤
		boolean subnetHaveSegment = false;
		for(MonitorSegment ms : segmentList) {
			// 2010-10-10
			if (!ms.getMonitorSubnet().getName().equals(subnet))
				continue;
			subnetHaveSegment = true;
			if (Tools.ipInSubnet(ip, ms.getNetworkSegment(), ms.getMask()))
				return false;
		}
		if (!subnetHaveSegment)
			return false;
		return true;
		/*
		segmentDataSet.first();
		do {
			if (!segmentDataSet.getString("子网名").equals(subnet))
				continue;
			subnetHaveSegment = true;
			if (Tools.ipInSubnet(ip, segmentDataSet.getString("网段"),
					segmentDataSet.getString("掩码")))
				return false;
		} while (segmentDataSet.next());
		if (!subnetHaveSegment)
			return false;
		return true;
		*/
	}

	private void display(String str) {
		dwrService.displayInfo(str);
		 System.out.println(str);
	}
	
	class ArpRecord {
		String sMac = "";
		int nCount = 1;
		
		
		public ArpRecord(String sMac) {
			this.sMac = sMac;
		}
		
		String getMac() {
			return sMac;
		}
		void setMac(String sMac) {
			this.sMac = sMac;
		}
		
		int getCount() {
			return this.nCount;
		}
		void addCount() {
			this.nCount++;
		}
		
		
		
		boolean equals(ArpRecord arpRecord) {
			if (this.sMac.equals(arpRecord.sMac)) {
				return true;
			} else {
				return false;
			}
		}
		
		boolean mergeRecord(ArpRecord arpRecord) {
			if (this.equals(arpRecord)) {
				this.nCount++;
				return true;
			} else {
				return false;
			}
		}
		
	}
}
