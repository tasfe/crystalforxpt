package com.combanc.monitor.constants;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.combanc.common.Config;

/**
 * <p>
 * Title:系统参数类
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
public class SystemParamConstants {
	/**流量采集类型**/
	public static String SYSTEM_PARAM_FLUX_TYPE = "FluxType";
	/**计算机表过期自动删除时间 code 默认值**/
	public static String SYSTEM_DELETE_GAP = "DeleteGap";		
	public static String SYSTEM_DELETE_GAP_DEFUALT_VALUE = "三个月";	
	
	// =======警示阈值和服务器阈值====================
	/**CPU负荷阈值，百分比**/
	public static String SYSTEM_CPU_LIMEN = "CpuLimen";
	public static int SYSTEM_CPU_LIMEN_DEFUALT_VALUE = 10;
	/** 接口带宽警示阈值，百分比**/
	public static String SYSTEM_BAND_LIMEN = "BandLimen";
	public static int SYSTEM_BAND_LIMEN_DEFUALT_VALUE = 5;
	/** 单播包警示阈值（个/ 秒）**/
	public static String SYSTEM_UNICAST_LIMEN = "UnicastLimen";
	public static int SYSTEM_UNICAST_LIMEN_DEFUALT_VALUE = 1000;
	/** 广播包警示阈值（个/ 秒）**/
	public static String SYSTEM_BROADCAST_LIMEN = "BroadcastLimen";
	public static int SYSTEM_BROADCAST_LIMEN_DEFUALT_VALUE = 100;
	/**AKCP温度阈值（度）**/
	public static String SYSTEM_TEMP_LIMEN = "TempLimen";
	public static int SYSTEM_TEMP_LIMEN_DEFUALT_VALUE = 25;
	/**Arp警示阈值（个）**/
	public static String SYSTEM_ARP_LIMEN = "ArpLimen";
	public static int SYSTEM_ARP_LIMEN_DEFUALT_VALUE = 5;
	
	/**TCP连接数**/
	public static String SYSTEM_TCP_CONN = "TcpConn";
	/**进程数**/
	public static String SYSTEM_PROC_NUM = "ProcNum";
	/**内存使用率（%）**/
	public static String SYSTEM_MEM_RATE = "MemRate";
	/**虚存使用率（%）**/
	public static String SYSTEM_VMEM_RATE = "VmemRate";
	/**磁盘使用率（%）**/
	public static String SYSTEM_DISK_RATE = "DiskRate";
	//====警示阈值和服务器阈值 end====================
	
	// =======计算机扫描参数====================
	/**间隔**/
	public static String SYSTEM_SCAN_GAP_TIME = "ScanGapTime";
	/**是否进行Netbios扫描**/
	public static String SYSTEM_ARP_NBT = "ArpNbt";	
	/**是否进行Ping预扫描**/
	public static String SYSTEM_PING_SCAN = "PingScan";	
	/**是否忽略mac转发异常，默认忽略**/
	public static String SYSTEM_IGNORE_ERROR = "IgnoreError";	
	/**mac扫描+arp扫描**/
	public static String SYSTEM_L_SCAN = "LScan";	
	/**替换L2Scan+L3Scan mac扫描arp扫描，**/
	public static String SYSTEM_L23_SCAN = "L23Scan";	
	/**定时扫描时间1**/
	public static String SYSTEM_SCAN_HOUR0 = "ScanHour0";	
	/**定时扫描时间2**/
	public static String SYSTEM_SCAN_HOUR1 = "ScanHour1";	
	/**定时扫描时间3**/
	public static String SYSTEM_SCAN_HOUR2 = "ScanHour2";	
 
	/**策略:报警并归档/不报警但归档**/
	public static String SYSTEM_CHANGE_REFRESH = "ChangeRefresh";		// 报警后进行归档
	
	// 计算机类型报警参数：
	/**计算机信息改变后报警**/
	public static String SYSTEM_CHANGE_ALERT = "ChangeAlert";	
	/**新计算机**/
	public static String SYSTEM_NEW_HOST = "NewHost";
	/**IP地址改变**/
	public static String SYSTEM_IP_CHANGED_ALERT = "IpChangedAlert";
	/**登录名改变**/
	public static String SYSTEM_USER_CHANGED_ALERT = "UserChangedAlert";
	/**计算机名改变**/
	public static String SYSTEM_COMPUTER_NAME_CHANGED_ALERT = "ComputerNameChangedAlert";
	/**域或组改变**/
	public static String SYSTEM_DOMAIN_CHANGED_ALERT = "DomainChangedAlert";
	/**交换机接口改变**/
	public static String SYSTEM_LINK_CHANGED_ALERT = "LinkChangedAlert";
	// ========计算机扫描参数 end ==============
	
	// ========流量及CPU采集 ==============
	/**启用、禁用**/
	public static  String SYSTEM_FLUX_SWITCH = "FluxSwitch";
	/**采集间隔**/
	public static String SYSTEM_FLUX_SCAN_GAP_TIME = "FluxScanGapTime";
	/**采集类型**/
	public static String SYSTEM_FLUX_TYPE = "FluxType";
	/**分钟保存时长**/
	public static String SYSTEM_MINUTE_DATA_KEEP = "MinuteDataKeep";
	public static int SYSTEM_MINUTE_DATA_KEEP_DEFUALT_VALUE = 7;
	/**小时保存时长**/
	public static String SYSTEM_HOUR_DATA_KEEP = "HourDataKeep";
	public static int SYSTEM_HOUR_DATA_KEEP_DEFUALT_VALUE = 90;
	
	// ========流量及CPU采集 end ==============
	
	// ========数据保存时长 ==============
	/**报警保存记录数**/
	public static String SYSTEM_ALERT_DATA_KEEP = "AlertDataKeep";
	public static int SYSTEM_ALERT_DATA_KEEP_DEFUALT_VALUE = 30000;
	// ========数据保存时长 end ==============
	
	// ========拓扑轮询 ==============
	/**轮询间隔**/
	public static  String SYSTEM_POLL_GAP = "pollGap";
	/**是否在拓扑图中标识出向心广播包最多的节点***/
	public static String SYSTEM_MAX_BROADCAST = "maxBroadcast";
	/**是否在拓扑图中标识出向心广播包最多的节点**/
	public static String SYSTEM_MAX_FLOW = "maxFlow";
	/**背景颜色**/
	public static String SYSTEM_BACK_COLOR = "backColor";
	/**默认背景图片路径**/
	public static String SYSTEM_BACK_PIC_PATH = "backPicPath";

	// ========拓扑轮询 end ==============
	
	
	
	/** TopN**/
	public static String SYSTEM_TOP_N = "TopN";
	/** SNMP重试次数**/
	public static String SYSTEM_SNMP_RETRY = "SnmpRetry";
	/** SNMP 网络超时时间**/
	public static String SYSTEM_SNMP_TIME_OUT = "SnmpTimeOut";
	/** 声音报警**/
	public static String SYSTEM_SOUND_SWTICH = "SoundSwitch";
	/** 短信报警**/
	public static String SYSTEM_MOBILE_SWTICH = "MobileSwitch";
	/** 邮件报警**/
	public static String SYSTEM_EMAIL_SWTICH = "EmailSwitch";
	
	/** 短信猫序列号**/
	public static String SYSTEM_SMS_SN = "SmsSn";
	/** 发送SMTP服务器**/
	public static String SYSTEM_SMTP_SERVER = "SmtpServer";
	/** 发送Email帐号**/
	public static String SYSTEM_MAIL_SENDER = "MailSender";
	/** 发送Email帐号密码**/
	public static String SYSTEM_MAIL_SENDER_PWD = "MailSenderPwd";
	/** 报警升级门限**/
	public static String SYSTEM_LIMIT_TO_ADD_LEVEL = "LimitToAddLevel";
	
	public static int SYSTEM_LIMIT_TO_ADD_LEVEL_DEFUALT_VALUE = 100;
	
	/**故障检查间隔**/
	public static String SYSTEM_CHECK_GAP = "CheckGap";
	/**异常后的监测时间间隔**/
	public static String SYSTEM_ERROR_GAP = "ErrorGap";
	
	/**故障检查间隔 默认值**/
	public static int SYSTEM_CHECK_GAP_DEFUALT_VALUE = 10;
	/**异常后的监测时间间隔 默认值**/
	public static int SYSTEM_ERROR_GAP_DEFUALT_VALUE = 1;
	
	
	/** 邮件报警 默认关**/
	public static String SYSTEM_EMAIL_SWTICH_DEFUALT_VALUE = "0";
	/** 发送SMTP服务器 默认关**/
	public static String SYSTEM_SMTP_SERVER_DEFUALT_VALUE = "0";
	/** 短信报警  默认关**/
	public static String SYSTEM_MOBILE_SWTICH_DEFUALT_VALUE = "0";
	/** 声音报警 默认关**/
	public static String SYSTEM_SOUND_SWTICH_DEFUALT_VALUE = "0";
	/** 报警升级门限  默认100**/
	public static int SYSTEM_TO_ADD_LEVEL_DEFUALT_VALUE = 100;
	
	// ========Ping 扫描设置 ==============
	public static String SYSTEM_PING_NUM = "PingNum";					// ping次数
	
	public static String SYSTEM_PING_SIZE = "PingSize";					// ping包长度
	
	public static String SYSTEM_PING_TIMEOUT = "PingTimeout";			// ping超时时间
	
	public static String SYSTEM_PING_LOOP_GAP = "PingLoopGap";			// ping扫描间隔时间
	
	public static String SYSTEM_PING_DETAIL_LIFE = "PingDetailLife";	//  ping细节表保存时间
	
	public static String SYSTEM_PING_REPORT_LIFE = "PingReportLife";	// ping统计表保存时间
	
	public static String SYSTEM_PING_EXCUTE_MODE = "PingExcuteMode";	// ping执行模式
	public static String SYSTEM_PING_DAY_IN_WEEK = "PingDayInWeek";			// ping 时间(星期)
	public static String SYSTEM_PING_IS_DEFINE_HOURS = "PingIsDefineHours";					// 在下列时间段才监测
	
	public static String SYSTEM_PING_AUTO_EXCUTE_MODE  = "PingAutoExcuteMode";// ping的自动执行模式
	public static String SYSTEM_PING_START_TIME_OF_DAY  = "PingStartTimeOfDay";// Ping开始时间
	public static String SYSTEM_PING_END_TIME_OF_DAY  = "PingEndTimeOfDay";// ping的自动执行模式
	
	
	public static int SYSTEM_PING_NUM_DEFUALT_VALUE = 4;				// ping次数默认值
	public static int SYSTEM_PING_SIZE_DEFUALT_VALUE = 32;				// ping包长度默认值
	public static int SYSTEM_PING_TIMEOUT_DEFUALT_VALUE = 4000;			// ping超时时间默认值
	public static int SYSTEM_PING_DETAIL_LIFE_DEFUALT_VALUE = 2;		// ping细节表保存时间默认值
	public static int SYSTEM_PING_REPORT_LIFE_DEFUALT_VALUE = 183;		// ping统计表保存时间默认值
	public static int SYSTEM_PING_EXCUTE_MODE_DEFUALT_VALUE = 1;		// ping执行模式默认值 1:自动执行 0:手动执行 
	public static int SYSTEM_PING_AUTO_EXCUTE_MODE_DEFUALT_VALUE = 1;	// ping的自动执行模式默认值 1:循环执行 0:定时执行
	// ========Ping 扫描设置 end  ==============
	
	/** .properties文件路径 **/
	public static String SYSTEM_MONITOR_PROPERTIES_PATH = Config.getClassesPath() +"monitor.properties";
	/** 设备拓扑中分批采集设备信息个数 **/
	public static Integer SYSTEM_TOPO_DEVICE_SIZE = (null == SystemParamConstants.readValue("topoDeviceSize")? 20:Integer.valueOf(SystemParamConstants.readValue("topoDeviceSize")));
	/** 设备拓扑中分批采集互连接口信息个数 **/
	public static Integer SYSTEM_TOPO_LINKPORT_SIZE = (null == SystemParamConstants.readValue("topoLinkportSize")? 20:Integer.valueOf(SystemParamConstants.readValue("topoLinkportSize")));
	/** 设备拓扑中分区请求老化时间 **/
	public static Long SYSTEM_TOPO_REQUEST_TIME_OUT = (null == SystemParamConstants.readValue("topoRequestTimeOut")? 120000:Long.valueOf(SystemParamConstants.readValue("topoRequestTimeOut")));
	/** 设备拓扑中采集数据老化时间 **/
	public static Long SYSTEM_TOPO_INFO_AGING_TIME = (null == SystemParamConstants.readValue("topoInfoAgingTime")? 300000:Long.valueOf(SystemParamConstants.readValue("topoInfoAgingTime")));
	
	/** 拓扑图设备显示内容 默认IP **/
	public static Integer SYSTEM_TOPO_NODE_TEXT_TYPE = (null == SystemParamConstants.readValue("topoNodeTextType")? 1:Integer.valueOf(SystemParamConstants.readValue("topoNodeTextType")));
	/** 拓扑图链路方向 默认双向 **/
	public static Integer SYSTEM_TOPO_LINK_DIRECTION = (null == SystemParamConstants.readValue("topoLinkDirection")? 3:Integer.valueOf(SystemParamConstants.readValue("topoLinkDirection")));
	/** 拓扑图连线方式：直线（默认）/折线/弧线 **/
	public static String SYSTEM_TOPO_EDGE_RENDERER = (null == SystemParamConstants.readValue("topoEdgeRenderer")? "BaseEdgeRenderer":SystemParamConstants.readValue("topoEdgeRenderer"));
	

	
	 //根据key读取value
	public static String readValue(String key) {
	  Properties props = new Properties();
	        try {
	         InputStream in = new BufferedInputStream (new FileInputStream(SYSTEM_MONITOR_PROPERTIES_PATH));
	         props.load(in);
	         String value = props.getProperty (key);
	         System.out.println(key+value);
	         return value;
	        } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	        }
	 }
}
