package com.combanc.monitor.quartz;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TooManyListenersException;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;
import org.springframework.context.ApplicationContext;

import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.Locate;
import com.borland.dx.dataset.ReadRow;
import com.borland.dx.dataset.RowFilterListener;
import com.borland.dx.dataset.RowFilterResponse;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.algorithm.ArpModeScan;
import com.combanc.monitor.algorithm.MACPortScan;
import com.combanc.monitor.comet.DwrService;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorAccessLog;
import com.combanc.monitor.pojo.MonitorAlert;
import com.combanc.monitor.pojo.MonitorComputer;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorSegment;
import com.combanc.monitor.pojo.MonitorSubnet;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorAccessLogService;
import com.combanc.monitor.service.MonitorAlertService;
import com.combanc.monitor.service.MonitorAlertSmalltypeService;
import com.combanc.monitor.service.MonitorAlertTypeService;
import com.combanc.monitor.service.MonitorComputerService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorSegmentService;
import com.combanc.monitor.service.MonitorSubnetService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.FileUtils;

/**
 * <p>
 * Title:计算机扫描
 * </p>
 * <p>
 * Description:可以定时扫描，轮询扫描
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author Combanc
 * @version 1.0
 */
public class ComputerScan  extends Thread {
	
	// 当前日期
	String currentDate;
	public String deleteGap = SystemParamConstants.SYSTEM_DELETE_GAP_DEFUALT_VALUE;
	public String l23Scan;// 替换l2Scan+l3Scan 0表示扫描ARP+MAC，1表示仅扫描扫描ARP表，2表示仅扫描MAC转发表
	public static boolean scanLoopRunning = false; // 表示正在扫描过程中，标识ScanLoop线程的状态
	
	public static boolean l3Scan = true;// 是否扫描ARP表
	public static boolean l2Scan = true;// 是否扫描MAC转发表
	public static boolean pingScan;// 使用PING预扫描还是不使用
	
	public boolean compareBase = true;// 是否比较档案并报警
	public boolean addLog = true;// 是否比较档案并报警
	public boolean arpNbt;// SNMP模式是否进行WINS扫描
	// 报警设置：
	public boolean newHost = true;;// 新计算机是否报警
	public boolean ipChangedAlert;// IP变化是否报警
	public boolean computerNameChangedAlert;// 计算机名变化是否报警
	public boolean domainChangedAlert;// 域或组变化是否报警
	public boolean userChangedAlert;// 登录名变化是否报警
	public boolean linkChangedAlert;// 域或组变化是否报警
	public boolean changeAlert;// 改变是否报警
	public boolean changeRefresh;// 改变是否刷新
	public boolean change = false; //是否需要刷新报警面板
 
	
	public int scanGapTime = 0;// 两次扫描间的间隔，ms为单位，0表示扫描一次。
	public boolean ignoreError;// 是否忽略扫描异常：PING/出错/超时/同一MAC出现在不同端口
	
	// 日志表及文件
	TextDataFile logDataFile = new TextDataFile();
	TableDataSet logDataSet = new TableDataSet();
	private List<MonitorAccessLog> monitorAccessLogList = new ArrayList<MonitorAccessLog>();
	// 日志表过滤器：当跨天时进行过滤操作
	RowFilterListener logFilter;
	// 跨天日志过滤日期
	String logFilteDate;
	

	// 基准子网表及文件，扫描中不存盘，初始全部记录的扫描标志为false。
	private List<MonitorSubnet> monitorSubnetList;
	private List<MonitorSegment> monitorSegmentList = new ArrayList<MonitorSegment>();
	private List<MonitorComputer> monitorComputerList = new ArrayList<MonitorComputer>();
	private List<MonitorComputer> monitorSnapshotList = new ArrayList<MonitorComputer>();//计算机快照列表
	private List<MonitorDevice> deviceList = new ArrayList<MonitorDevice>();
	
	private MonitorSystemParamService monitorSystemParamService;
	private MonitorComputerService monitorComputerService;
	private MonitorSubnetService monitorSubnetService;
	private MonitorDeviceService monitorDeviceService;
	private MonitorSegmentService monitorSegmentService;
	private MonitorAlertService monitorAlertService;
	private MonitorAlertTypeService monitorAlertTypeService;
	private MonitorAlertSmalltypeService monitorAlertSmalltypeService;
	private MonitorAccessLogService monitorAccessLogService;
	
	private ArpModeScan arpModeScan;
	private MACPortScan macPortScan;
	private Alerter alerter;
	private DwrService dwrService;
	
	public ComputerScan(ApplicationContext ac){
		arpModeScan = (ArpModeScan) ac.getBean("ArpModeScan");
		macPortScan = (MACPortScan) ac.getBean("MACPortScan");
		alerter = (Alerter) ac.getBean("Alerter");
		dwrService = (DwrService) ac.getBean("DwrService");
		monitorSystemParamService = (MonitorSystemParamService) ac.getBean("MonitorSystemParamService");
		monitorComputerService = (MonitorComputerService) ac.getBean("MonitorComputerService");
		monitorSubnetService = (MonitorSubnetService) ac.getBean("MonitorSubnetService");
		monitorDeviceService = (MonitorDeviceService) ac.getBean("MonitorDeviceService");
		monitorSegmentService = (MonitorSegmentService) ac.getBean("MonitorSegmentService");
		monitorAlertService = (MonitorAlertService) ac.getBean("MonitorAlertService");
		monitorAlertTypeService = (MonitorAlertTypeService) ac.getBean("MonitorAlertTypeService");
		monitorAlertSmalltypeService = (MonitorAlertSmalltypeService) ac.getBean("MonitorAlertSmalltypeService");
		monitorAccessLogService = (MonitorAccessLogService) ac.getBean("MonitorAccessLogService");
	}
	
	public boolean init(){
		loadScanParams();
		// 取当前日期
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		 
		monitorSubnetList = monitorSubnetService.findByScan(1);
		// 检查是否设置了子网
		if (monitorSubnetList.isEmpty()) {
			dwrService.alertInfo("无目标分区记录，请完成目标子网设置后再进行扫描。");
			return false;
		}
		deviceList = monitorSubnetService.getDeviceBySubnet(monitorSubnetList);
		//不扫描虚拟设备
		Iterator it = deviceList.iterator();
		while(it.hasNext()){
			 MonitorDevice device = (MonitorDevice) it.next();
			 if(null != device.getMonitorDeviceType() && MainConstants.DEVICE_VIRTUAL == device.getMonitorDeviceType().getCode())
				 it.remove();
		}
		if (deviceList.isEmpty()) {
			dwrService.alertInfo("分区内没有可扫描的设备，请完成设置后再进行扫描。");
			return false;
		}
		monitorSegmentList = monitorSegmentService.getAll();
		monitorComputerList = monitorComputerService.findAll();
		// 扫描开始时即清空计算机类报警表
		synchronized (Alerter.computerAlertList) {
			Alerter.computerAlertList.clear();
		}
		
	 
		
		logDataFile.setFileName(getLogDataFileName());
		logDataFile.setLoadAsInserted(true);
		logDataSet.setDataFile(logDataFile);
		logDataSet.open();

		// 构造日志表行过滤器
		logFilter = new com.borland.dx.dataset.RowFilterListener() {
			public void filterRow(ReadRow row, RowFilterResponse response) {
				logDataSet_filterRow(row, response);
			}
		};
		return true;
		
	}
	
	/**
	 *  获得初始日志文件名。如果日志目录下已存在，使用当日文件名；如果不存在，使用安装
	 *  目录下的空日志文件名。
	 * @return
	 */
	private String getLogDataFileName() {
		// 取当前日期
		String now = getDate(new GregorianCalendar());
		String path = FileUtils.getClassPath(MainConstants.class) + "\\com\\combanc\\monitor\\txtdata\\";
		now = path + "log\\" + now + ".txt";
		File file = new File(now);
		// 如果当日日志文件已经存在，则以当日日志文件初始化日志表，如果不存在，以空日志文件初始化。
		if (!file.exists())
			now = path + "日志表.txt";
		System.out.println("日志路径  " + now);
		return now;
	}
	
	// 日志表行过滤器
	void logDataSet_filterRow(ReadRow row, RowFilterResponse response) {
		String rDate;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
		rDate = formatter.format(row.getTimestamp("首次发现时间"));
		if (!logFilteDate.equals("all")) {
			if (rDate.equals(logFilteDate))
				response.add();
			else
				response.ignore();
		} else {
			response.add();
		}
	}
	
	/**初始化参数**/
	public void loadScanParams(){
		MonitorSystemParam param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_DELETE_GAP);
		if(null != param && null != param.getValue() && !"".equals(param.getValue())){
			deleteGap = param.getValue();
		}
		param = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_L23_SCAN);
		if(null != param && null != param.getValue() && !"".equals(param.getValue())){
			l23Scan = param.getValue();
			if("0".equals(l23Scan)){
				l3Scan = true;
				l2Scan = true;
				compareBase = true;
				addLog = true;
			} else {
				compareBase = false;
				addLog = false;
				arpNbt = false;// 不做NBT扫描
				if("1".equals(l23Scan)){
					l3Scan = true;
					l2Scan = false;
				} else if("2".equals(l23Scan)){
					l3Scan = false;
					l2Scan = true;
				}
			}
				
		}
		newHost = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_NEW_HOST);
		ipChangedAlert = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_IP_CHANGED_ALERT);
		linkChangedAlert = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_LINK_CHANGED_ALERT);
		computerNameChangedAlert = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_COMPUTER_NAME_CHANGED_ALERT);
		domainChangedAlert = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_DOMAIN_CHANGED_ALERT);
		userChangedAlert = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_USER_CHANGED_ALERT);
		changeAlert = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_CHANGE_ALERT);
		changeRefresh = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_CHANGE_REFRESH); 
		pingScan = monitorSystemParamService.getBooleanParam(SystemParamConstants.SYSTEM_PING_SCAN); 
	}
	
	
	public void run() {
		
		if(!init()) return;
		
		// 开始时间和结束时间，用来计算扫描用时
		GregorianCalendar beginTime, endTime;
		// 一次扫描的结束日期：
		String scanEndDate;
		// 设置当前日期：取日期的年月日，年月日与时间的中间是空格
		GregorianCalendar gc = new GregorianCalendar();
		currentDate = getDate(gc);
		// 检查是否有用户扫描：
		//boolean userScan = false;
		// 反复循环，直至停止按钮按下，执行STOP方法后停止。
		//while (true) {
		scanLoopRunning = true;
		beginTime = new GregorianCalendar();
		// 第一步：接入扫描
		//if (userScan) {
			monitorSnapshotList.clear();
			// 生成快照
			// 定期删除旧计算机表 ，演示模式不定期删除
			monitorComputerService.delOldComputer(deleteGap);
			if (genSnapShot()) {	// 扫描
				// 检查用户许可数，将超过许可数部分的记录删除
				checkLicNum();
				dwrService.displayInfo("处理扫描数据，可能需要一些时间，请稍侯...");
				// 如果需要比较快照表与档案，则更新档案表和报警表
				//保存更新过的档案表和报警表。
				if (compareBase) {
					compareAndAlert();
					synchronized (Alerter.computerAlertList) {
						alerter.checkComputerAlert();
					}
				}
				
				// 如果需要将快照加入日志，则将快照表内容更新到日志表中
				if (addLog)
					addCurrentToAccessLog();
					//addCurrentToLogDataSet();
				// 取扫描结束时的时间和日期，用于判断本次扫描是否跨天的情况，为保存日志提供日期。
				scanEndDate = getDate(new GregorianCalendar());
				// 确保各文件的安全保存，采用同步控制：
				// 档案表的最近上网时间被更新，在学习模式可能增加新记录。
				if (compareBase) {
					saveArchives();
				}
				//  保存本次扫描后的快照表，供快照查询；
//				monitorSnapshotService.deleteAll();
//				monitorSnapshotService.batchInsert(monitorSnapshotList);
				
				// 统计并保存各个设备下的档案用户数和快照用户数
				saveUserCountToDevice();
				// 保存日志表到日志文件
//				if (addLog)
//					saveLogDataSetToFile(scanEndDate);
			}
			
		//}
			
		
		scanLoopRunning = false;
		dwrService.displayInfo("计算机扫描结束!");
	}
	
	/** gc 获得xxxx-xx-xx 的年月日格式，"-"分隔 **/
	private String getDate(GregorianCalendar gc) {
		return String.valueOf(gc.get(GregorianCalendar.YEAR))
				+ // .toString();
				"-" + String.valueOf(gc.get(GregorianCalendar.MONTH) + 1) + "-"
				+ String.valueOf(gc.get(GregorianCalendar.DAY_OF_MONTH));
	}
	
	/** 进行网络层 扫描 或者链路层扫描**/
	private boolean genSnapShot() {
		// 网络层扫描
		if (l3Scan) {
			arpModeScan.init();
//			arpScan.setAlertTable(alertTable);
			// 扫描网络层设备并将发现的IP存入arpDataSet。
			if (!arpModeScan.scan(deviceList, monitorSegmentList, monitorSnapshotList)) {
				arpModeScan = null;
				return false;
			}
			arpModeScan = null;
		}else {// 不进行网络层扫描，只进行链路层扫描：
			try {
				macPortScan.init();
			} catch (Exception e) {
				macPortScan = null;
				return false;
			}
			if (!macPortScan.scan(deviceList, monitorSegmentList, monitorSnapshotList)) {
				macPortScan = null;
				return false;
			}
			macPortScan = null;
		}
		return true;
	}
	
	/**  检查用户许可数，将超过许可数部分的记录删除 **/
	private void checkLicNum() {
		if (MainConstants.LICNUM == 5888)
			return;// 无用户数限制
		if (monitorSnapshotList.size() <= MainConstants.LICNUM)
			return;
		// 如果记录数超过许可数，则到表的最后一行，逐行删除，直至记录数与许可数相等。
		for (int i = monitorSnapshotList.size() - 1; i > MainConstants.LICNUM - 1; i--) {
			monitorSnapshotList.remove(i);
		}
	}
	
	/** 比较当前表和档案表,根据设置更新并报警 **/
	private void compareAndAlert() {
		if (monitorSnapshotList.isEmpty())
			return;
		List<MonitorComputer> tempComputer = new ArrayList();
		for (MonitorComputer ss : monitorSnapshotList) {
			String sql = "select * from com.combanc.monitor.pojo.MonitorComputer where mac = :mac";
			Query q = new Query();
			try {
				q.parse(sql);
			} catch (QueryParseException e) {
				e.printStackTrace();
			}
			q.setVariable("mac", ss.getMac());
			QueryResults qr = null;
			try {
				qr = q.execute(monitorComputerList);
			} catch (QueryExecutionException e) {
				e.printStackTrace();
			}
			List<MonitorComputer> res = qr.getResults();
			// 1、新计算机/MAC地址：
			if (res.isEmpty()) {
				if (changeAlert && newHost)
					alertNewHost(changeRefresh, ss);
				// if (changeRefresh)
				// addNewHost(ss);
				continue;// 继续下一记录
			}
			// 2、已有计算机/同一MAC地址：
			// IP、上连设备、端口/接口/描述为档案/快照必有数据项，只比较是否相等即可。
			if (!res.isEmpty()) {
				MonitorComputer po = new MonitorComputer();
				po = res.get(0);
				if (!ss.getIp().equals(po.getIp())) {
					if (changeAlert && ipChangedAlert)
						itemChanged(MainConstants.SALERT_CIP_CHANGE, po, ss); //计算机IP改变
					if (changeRefresh)
						po.setIp(ss.getIp());
				}
				if (!ss.getMonitorDevice().equals(po.getMonitorDevice())) {
					if (changeAlert && linkChangedAlert)
						itemChanged(MainConstants.SALERT_CDEVICE_CHANGE, po, ss); //上连设备改变
					if (changeRefresh)
						po.setMonitorDevice(ss.getMonitorDevice());
				}

				if (!ss.getDevicePort().equals(po.getDevicePort())) {
					if (changeAlert && linkChangedAlert)
						itemChanged(MainConstants.SALERT_CINTERFACE_CHANGE, po, ss);//接口改变
					if (changeRefresh) {
						po.setDevicePort(ss.getDevicePort());
						po.setDeviceInterface(ss.getDeviceInterface());
						po.setInterfaceDescription(ss.getInterfaceDescription());
					}
				}
				// 在不扫描NETBIOS端口或扫描但无有效数据的情况下，则数据有效（不为-）时才处理数据
				// 如果已有记录，但前次数据无效而当前数据有效，则自动更新
				if (!ss.getComputerName().equals("-")
						&& !po.getComputerName().equals("-")
						&& !ss.getComputerName().equals(po.getComputerName())) {
					if (changeAlert && computerNameChangedAlert)
						itemChanged(MainConstants.SALERT_CNAME_CHANGE, po, ss);// 计算机名改变
					if (changeRefresh)
						po.setComputerName(ss.getComputerName());
				} else if (!ss.getComputerName().equals("-") && po.getComputerName().equals("-")) {
					po.setComputerName(ss.getComputerName());
				}

				if (!ss.getDomain().equals("-") && !po.getDomain().equals("-")
						&& !ss.getDomain().equals(po.getDomain())) {
					if (changeAlert && domainChangedAlert)
						itemChanged(MainConstants.SALERT_CDOMAIN_CHANGE, po, ss);// 域或组改变
					if (changeRefresh)
						po.setDomain(ss.getDomain());
				} else if (!ss.getDomain().equals("-") && po.getDomain().equals("-")) {
					po.setDomain(ss.getDomain());
				}

				if (!ss.getLoginName().equals("-")
						&& !po.getLoginName().equals("-")
						&& !ss.getLoginName().equals(po.getLoginName())) {
					if (changeAlert && userChangedAlert)
						itemChanged(MainConstants.SALERT_CLOGIN_CHANGE, po, ss);// 登录名改变
					if (changeRefresh)
						po.setLoginName(ss.getLoginName());
				} else if (!ss.getLoginName().equals("-") && po.getLoginName().equals("-")) {
					po.setLoginName(ss.getLoginName());
				}
				po.setDiscoveryTime(ss.getDiscoveryTime());
				tempComputer.add(po);
				change = true;
			}
		}
		
	}
	
	/**
	 *  新计算机报警，增量，只控制台报警。
	 * @param refresh
	 * @param computer
	 */
	private void alertNewHost(boolean refresh, MonitorComputer computer) {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		String hql = " from com.combanc.monitor.pojo.MonitorAlert where monitorAlertSmalltype.code = 4 and mac = '"+computer.getMac()+"' order by id desc";
		List<MonitorAlert> res = monitorAlertService.findBySql(hql);
		MonitorAlert po = new MonitorAlert();
		if (!res.isEmpty()) {
			po = res.get(0);
			po.setLastTime(ts);
			monitorAlertService.update(po);
		} else {
			po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_PC));
			po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(MainConstants.SALERT_NEW_COMPUTER));
			po.setFile(refresh == true ? 1 : 0);
			po.setFirstTime(ts);
			po.setLastTime(ts);
			po.setIp(computer.getIp());
			po.setHostName(computer.getHostName());
			po.setMac(computer.getMac());
			po.setCompName(computer.getComputerName());
			po.setDomain(computer.getDomain());
			po.setLoginName(computer.getLoginName());
			po.setUplink(computer.getMonitorDevice().getIp());
			po.setPort(computer.getDevicePort());
			po.setInterface_(computer.getDeviceInterface());
			po.setDescription(computer.getInterfaceDescription());
			monitorAlertService.save(po);
			synchronized (Alerter.computerAlertList) {
				Alerter.computerAlertList.add(po);
			}

		}
		change = true;
	}// alertNewHost
	
	/**  计算机网络设置改变报警，增量，只控制台报警 **/
	private void itemChanged(Integer type, MonitorComputer computer,
			MonitorComputer cs) {
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());

		String sql = " from com.combanc.monitor.pojo.MonitorAlert where monitorAlertSmalltype.code = "+type+" and mac = '"+computer.getMac()+"' ";
		
		if(type.equals(MainConstants.SALERT_CIP_CHANGE))
			sql = sql + " and ip = '"+cs.getIp()+"'";
		if(type.equals(MainConstants.SALERT_CDEVICE_CHANGE))
			sql = sql + " and uplink = '"+computer.getMonitorDevice().getIp()+"'";
		if(type.equals(MainConstants.SALERT_CNAME_CHANGE))
			sql = sql + " and compName = '"+cs.getComputerName()+"'";
		if(type.equals(MainConstants.SALERT_CDOMAIN_CHANGE))
			sql = sql + " and domain = '"+cs.getDomain()+"'";
		if(type.equals(MainConstants.SALERT_CLOGIN_CHANGE))
			sql = sql + " and loginName = '"+cs.getLoginName()+"'";
		if(type.equals(MainConstants.SALERT_CINTERFACE_CHANGE))
			sql = sql + " and interface_ = '"+cs.getDeviceInterface()+"'";
		sql = sql + "order by id desc ";
		List<MonitorAlert> res = monitorAlertService.findBySql(sql);

		MonitorAlert po = new MonitorAlert();
		if (!res.isEmpty()) {
			po = res.get(0);
			po.setLastTime(ts);
			monitorAlertService.update(po);
		} else {
			po.setMonitorAlertType(monitorAlertTypeService.get(MainConstants.ALERT_PC));
			po.setMonitorAlertSmalltype(monitorAlertSmalltypeService.get(type));
			po.setFile(changeRefresh == true ? 1 : 0);
			po.setFirstTime(ts);
			po.setLastTime(ts);
			po.setIp(cs.getIp());
			po.setHostName(cs.getHostName());
			po.setMac(cs.getMac());
			po.setCompName(cs.getComputerName());
			po.setDomain(cs.getDomain());
			po.setLoginName(cs.getLoginName());
			po.setUplink(computer.getMonitorDevice().getIp());
			po.setPort(computer.getDevicePort());
			po.setInterface_(computer.getDeviceInterface());
			po.setDescription(computer.getInterfaceDescription());
			 
			if (type == MainConstants.SALERT_CIP_CHANGE)
				po.setOld(computer.getIp());
			else if (type == MainConstants.SALERT_CDEVICE_CHANGE)
				po.setOld(computer.getMonitorDevice().getIp());
			else if (type == MainConstants.SALERT_CNAME_CHANGE)
				po.setOld(computer.getComputerName());
			else if (type == MainConstants.SALERT_CDOMAIN_CHANGE)
				po.setOld(computer.getDomain());
			else if (type == MainConstants.SALERT_CLOGIN_CHANGE)
				po.setOld(computer.getLoginName());
			else if (type == MainConstants.SALERT_CINTERFACE_CHANGE)
				po.setOld(computer.getDevicePort());
			monitorAlertService.save(po);
			synchronized (Alerter.computerAlertList) {
				Alerter.computerAlertList.add(po);
			}
		}
		change = true;
	}// itemChanged
	private void addCurrentToAccessLog(){
		
		if (monitorSnapshotList.isEmpty())
			return;
		// 取快照表当前记录的最近发现时间项，与当前日期比较，不等（跨日）则加入日志表，等
		// 则在日志表中查找，没有找到则加入日志表，找到则修改最后发现时间。
		for(MonitorComputer snapshot : monitorSnapshotList){
			MonitorAccessLog dayAccessLog = this.monitorAccessLogService.findByIpAndDate(snapshot.getIp(),new Date());
			if(null != dayAccessLog){
				dayAccessLog.setMac(snapshot.getMac());
				dayAccessLog.setUpDeviceIp(snapshot.getMonitorDevice().getIp());
				dayAccessLog.setInterfaceDescription(snapshot.getInterfaceDescription());
				dayAccessLog.setLastTime(snapshot.getDiscoveryTime());
				monitorAccessLogService.update(dayAccessLog);
			}else{
				dayAccessLog =new MonitorAccessLog();
				dayAccessLog.setIp(snapshot.getIp());
				dayAccessLog.setMac(snapshot.getMac());
				dayAccessLog.setUpDeviceIp(snapshot.getMonitorDevice().getIp());
				dayAccessLog.setInterfaceDescription(snapshot.getInterfaceDescription());
				dayAccessLog.setFirstTime(snapshot.getDiscoveryTime());
				dayAccessLog.setLastTime(snapshot.getDiscoveryTime());
				monitorAccessLogService.save(dayAccessLog);
				
			}
		}
		
		
	}
	/** 用快照表更新日志表 **/
	private void addCurrentToLogDataSet() {
		if (monitorSnapshotList.isEmpty())
			return;
		String recDate;
		DataRow row = new DataRow(logDataSet);
		DataRow loc = new DataRow(logDataSet, new String[] { "IP", "主机名",
				"MAC", "计算机名", "域或组", "登录名", "上连设备", "端口" });
		// 取快照表当前记录的最近发现时间项，与当前日期比较，不等（跨日）则加入日志表，等
		// 则在日志表中查找，没有找到则加入日志表，找到则修改最后发现时间。
		for(MonitorComputer snapshot : monitorSnapshotList){
			recDate = getDate(new GregorianCalendar());
			if (recDate.equals(currentDate)) {
				loc.setString("IP", snapshot.getIp());
				loc.setString("主机名", snapshot.getHostName());
				loc.setString("MAC", snapshot.getMac());
				loc.setString("计算机名",snapshot.getComputerName());
				loc.setString("域或组", snapshot.getDomain());
				loc.setString("登录名", snapshot.getLoginName());
				loc.setString("上连设备", snapshot.getMonitorDevice().getIp());
				loc.setString("端口", snapshot.getDevicePort());
				// 查找该记录是否存在：
				if (logDataSet.locate(loc, Locate.FIRST)) {
					// 存在，更新最后发现时间
					logDataSet.setTimestamp("最后发现时间", snapshot.getDiscoveryTime());
					// 不存在，产生新的记录
				} else {
					row.setString("IP", snapshot.getIp());
					row.setString("主机名", snapshot.getHostName());
					row.setString("MAC", snapshot.getMac());
					row.setString("计算机名",snapshot.getComputerName());
					row.setString("域或组", snapshot.getDomain());
					row.setString("登录名", snapshot.getLoginName());
					row.setString("上连设备",snapshot.getMonitorDevice().getIp());
					row.setString("端口", snapshot.getDevicePort());
					row.setString("接口", snapshot.getDeviceInterface());
					row.setString("接口描述", snapshot.getInterfaceDescription());
					row.setTimestamp("首次发现时间",snapshot.getDiscoveryTime());
					row.setTimestamp("最后发现时间", snapshot.getDiscoveryTime());
					logDataSet.addRow(row);
				}
			}else {
				row.setString("IP", snapshot.getIp());
				row.setString("主机名", snapshot.getHostName());
				row.setString("MAC", snapshot.getMac());
				row.setString("计算机名",snapshot.getComputerName());
				row.setString("域或组", snapshot.getDomain());
				row.setString("登录名", snapshot.getLoginName());
				row.setString("上连设备",snapshot.getMonitorDevice().getIp());
				row.setString("端口", snapshot.getDevicePort());
				row.setString("接口", snapshot.getDeviceInterface());
				row.setString("接口描述", snapshot.getInterfaceDescription());
				row.setTimestamp("首次发现时间",snapshot.getDiscoveryTime());
				row.setTimestamp("最后发现时间", snapshot.getDiscoveryTime());
				logDataSet.addRow(row);
			}
		}
	}
	
	private void saveArchives() {
		List<MonitorComputer> oldList = monitorComputerService.findAll();
		List<MonitorComputer> updateList = new ArrayList<MonitorComputer>();
		List<MonitorComputer> newList = new ArrayList<MonitorComputer>();

		// 设置所有计算机的snapshot位为false，等同于原先的清空快照表
		monitorComputerService.resetSnapshotFlag();
		
		for(MonitorComputer ms : monitorSnapshotList) {
			// 超过用户许可数时不能进行增加操作
			if (MainConstants.LICNUM != 5888
					&& (oldList.size() + newList.size() >= MainConstants.LICNUM))
				break;
			boolean hasComputer = false;
			MonitorComputer oldComputer = null;
			for (MonitorComputer ocomputer : oldList) {
				if (ocomputer.getMac().equals(ms.getMac())) {
					hasComputer = true;
					oldComputer = ocomputer;
				}
			}
			
			if (hasComputer && oldComputer != null) {
				oldComputer.setIp(ms.getIp());
				oldComputer.setMonitorDevice(ms.getMonitorDevice());
				oldComputer.setDevicePort(ms.getDevicePort());
				oldComputer.setDeviceInterface(ms.getDeviceInterface());
				oldComputer.setInterfaceDescription(ms.getInterfaceDescription());
				// 计算机存在时，如果新扫描出来的计算机名不等于"-"，才更新，防止覆盖原先的计算机名
				if(!ms.getComputerName().equals("-"))
					oldComputer.setComputerName(ms.getComputerName());
				oldComputer.setDomain(ms.getDomain());
				oldComputer.setLoginName(ms.getLoginName());
				oldComputer.setHostName(ms.getHostName());
				oldComputer.setDiscoveryTime(ms.getDiscoveryTime());
				oldComputer.setSnapshot(true);
				updateList.add(oldComputer);
			} else {
				MonitorComputer tcomputer = new MonitorComputer();
				tcomputer.setIp(ms.getIp());
				tcomputer.setMac(ms.getMac());
				tcomputer.setMonitorDevice(ms.getMonitorDevice());
				tcomputer.setDevicePort(ms.getDevicePort());
				tcomputer.setDeviceInterface(ms.getComputerName());
				tcomputer.setInterfaceDescription(ms.getInterfaceDescription());
				// 计算机不存在时保存登录名
				tcomputer.setComputerName(ms.getComputerName());
				tcomputer.setDomain(ms.getDomain());
				tcomputer.setLoginName(ms.getLoginName());
				tcomputer.setHostName(ms.getHostName());
				tcomputer.setDiscoveryTime(ms.getDiscoveryTime());
				tcomputer.setSnapshot(true);
				newList.add(tcomputer);
			}
		}// for(MonitorSnapshot ms : monitorSnapshotList) {

		// changeRefresh 归档
		monitorComputerService.batchInsert(newList);
		monitorComputerService.batchUpdate(updateList);
	}

	private void saveUserCountToDevice() {
		// 获取档案计算机表，各个设备下的计算机数
		List archiveComputerCountList = monitorComputerService.getComputerCount();
		for (MonitorDevice dv : deviceList) {
			// 获取指定设备下的快照计算机数
			int snapUserNum = getSnapCoumpterCountByDevice(dv.getIp());
			dv.setSnapUserNum(snapUserNum);
			// 获取指定设备下的档案计算机数
			int archiveUserNum = getArchiveCoumpterCountByDevice(archiveComputerCountList, dv.getId());
			dv.setArchiveUserNum(archiveUserNum);
		}
		monitorDeviceService.batchUpdate(deviceList);
		
	}
	
	/** 获取指定设备下的快照计算机数 **/
	private int getSnapCoumpterCountByDevice(String deviceIp) {
		Query q = new Query();
		String hql = "";
		QueryResults qr = null;

		hql = "SELECT id FROM com.combanc.monitor.pojo.MonitorComputer WHERE monitorDevice.ip = :ip";
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("ip", deviceIp);
		try {
			qr = q.execute(monitorSnapshotList);
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<Integer> res = qr.getResults();
		if (!res.isEmpty())
			return res.size();
		return 0;
	}
	
	/** 获取指定设备下的档案计算机数 **/
	private int getArchiveCoumpterCountByDevice(List archiveComputerCountList, Integer deviceId) {
		String strDeviceId = deviceId.toString();
		if (archiveComputerCountList != null && !archiveComputerCountList.isEmpty()) {
			Iterator it = archiveComputerCountList.iterator();
		    while(it.hasNext()){
		        Map result = (Map)it.next();
		        if (result.get("DEVICE_ID") != null 
		        		&& strDeviceId.equals(result.get("DEVICE_ID").toString())) {
		        	return Integer.parseInt(result.get("num").toString());
		        }
		    }
		    return 0;
		} else {
			return 0;
		}
	}
	
	/** 将日志表保存到日志文件 **/
	private void saveLogDataSetToFile(String scanEndDate) {
		// 如果本次扫描结束日期与当前日期相同，则保存
		if (scanEndDate.equals(currentDate)) {
			logDataFile.setFileName(MainConstants.INSTALL_PATH + "log\\"+ currentDate + ".txt");
			try {
				logDataFile.save(logDataSet);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// 如果本次扫描结束日期与当前日期不同（即跨天），则将日志分成两部分，前一日的记录
			// 保存到前一日的文件中，后一日的保存到后一日的文件中。
		} else {
			// 将行过滤器加载到日志表上
			try {
				logDataSet.addRowFilterListener(logFilter);
			} catch (TooManyListenersException e) {
				e.printStackTrace();
			}
			// 按当前日期过滤并保存日志表到当天日志文件：
			logFilteDate = currentDate;
			logDataSet.refilter();
			logDataFile.setFileName(MainConstants.INSTALL_PATH + "log\\"
					+ currentDate + ".txt");
			try {
				logDataFile.save(logDataSet);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// 按扫描结束日期过滤并保存日志表表到跨天日志文件：
			logFilteDate = scanEndDate;
			logDataSet.refilter();
			logDataFile.setFileName(MainConstants.INSTALL_PATH + "log\\"
					+ scanEndDate + ".txt");
			try {
				logDataFile.save(logDataSet);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// 恢复完整视图：
			logFilteDate = "all";
			logDataSet.refilter();
			// 删除日志表行过滤器
			logDataSet.removeRowFilterListener(logFilter);
			// 删除日志表中当日记录，使只保留跨日记录，完成日志表更替。
			deleteLogCurrentDateRow(currentDate);
			// 更新当前日期标志
			currentDate = scanEndDate;
		}// if
	}
	/**删除日志表中当日记录，使只保留跨日记录，完成日志表更替。**/
	private void deleteLogCurrentDateRow(String currentDate) {
		if (logDataSet.isEmpty())
			return;

		String rDate;
		boolean deleted = true;
		logDataSet.first();

		do {
			// 注：删除当前行后，如果该行不是行尾，则下一行成为当前行。如果该行是行尾，则上一
			// 行成为当前行。
			while (deleted) {
				// SimpleDateFormat formatter = new
				// SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d");
				rDate = formatter.format(logDataSet.getTimestamp("首次发现时间"));
				if (rDate.equals(currentDate)) {
					// 如果到达行尾，删除该行后跳出内层循环，接着外层循环随之结束。
					if (logDataSet.atLast()) {
						logDataSet.deleteRow();
						break;
					} else {
						// 如果没有到达行尾，删除该行但不跳出内层循环。
						logDataSet.deleteRow();
					}// 内层if
					// 设置已删除标志，通过内层循环处理成为当前行的下一行
					deleted = true;
				} else {
					// 如果该记录不满足条件，则设置未删除标志，使内层循环结束。
					deleted = false;
				}// 外层if
			}// while1
			// 内层循环结束的条件是当前行不满足删除条件，重新设置未删除标志，以便处理下一条记录。
			// 如果内层循环到达行尾后跳出，则外层循环自动结束。
			deleted = true;
		} while (logDataSet.next());
	}
	
	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public MonitorComputerService getMonitorComputerService() {
		return monitorComputerService;
	}

	public void setMonitorComputerService(
			MonitorComputerService monitorComputerService) {
		this.monitorComputerService = monitorComputerService;
	}

	public ArpModeScan getArpModeScan() {
		return arpModeScan;
	}

	public void setArpModeScan(ArpModeScan arpModeScan) {
		this.arpModeScan = arpModeScan;
	}

	public MACPortScan getMacPortScan() {
		return macPortScan;
	}

	public void setMacPortScan(MACPortScan macPortScan) {
		this.macPortScan = macPortScan;
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

	public MonitorSegmentService getMonitorSegmentService() {
		return monitorSegmentService;
	}

	public void setMonitorSegmentService(MonitorSegmentService monitorSegmentService) {
		this.monitorSegmentService = monitorSegmentService;
	}

//	public MonitorSnapshotService getMonitorSnapshotService() {
//		return monitorSnapshotService;
//	}
//
//	public void setMonitorSnapshotService(
//			MonitorSnapshotService monitorSnapshotService) {
//		this.monitorSnapshotService = monitorSnapshotService;
//	}

	public MonitorAlertService getMonitorAlertService() {
		return monitorAlertService;
	}

	public void setMonitorAlertService(MonitorAlertService monitorAlertService) {
		this.monitorAlertService = monitorAlertService;
	}

	public MonitorAlertTypeService getMonitorAlertTypeService() {
		return monitorAlertTypeService;
	}

	public void setMonitorAlertTypeService(
			MonitorAlertTypeService monitorAlertTypeService) {
		this.monitorAlertTypeService = monitorAlertTypeService;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

	public MonitorAlertSmalltypeService getMonitorAlertSmalltypeService() {
		return monitorAlertSmalltypeService;
	}

	public void setMonitorAlertSmalltypeService(
			MonitorAlertSmalltypeService monitorAlertSmalltypeService) {
		this.monitorAlertSmalltypeService = monitorAlertSmalltypeService;
	}
	public DwrService getDwrService() {
		return dwrService;
	}
	public void setDwrService(DwrService dwrService) {
		this.dwrService = dwrService;
	}

	
	
	
}
