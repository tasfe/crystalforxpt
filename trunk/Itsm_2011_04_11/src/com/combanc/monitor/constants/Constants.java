package com.combanc.monitor.constants;

import java.io.File;
import java.net.URLDecoder;

import com.combanc.monitor.util.FileUtils;

// 通用参数类
public class Constants {
	public Constants() {
	}
	
	public static final int PAGE_SIZE = 12;

	//获取tomcat的classes所在的路径
	//static Class objClass = Constants.class.getClass(); 
	// static String strRealPath  = URLDecoder.decode(objClass.getClassLoader().getResource(".").getPath());
	// strRealPath = URLDecoder.decode(strRealPath, "UTF-8"); 
	// File objFile = new File(URLDecoder.decode(objClass.getClassLoader().getResource(".").getPath())); 
	// static String strRealPath = new File(URLDecoder.decode(objClass.getClassLoader().getResource(".").getPath())).getParent();
	
	// public static final String path = Constants.class.getClass().getResource("/").getPath();
	// public static final String path = strRealPath;
	public static final String path = FileUtils.getClassPath(Constants.class) + "\\com\\combanc\\monitor\\txtdata\\";
	public static final String paramsFileName = path + "扫描设置表.txt";
	public static final String c1FileName = path + "单列表.txt";
	public static final String logFileName = path + "日志表.txt";
	public static final String interfaceFileName = path + "接口表.txt";
	public static final String routerFileName = path + "路由表.txt";
	public static final String macPortFileName = path + "MAC端口表.txt";
	public static final String argumentFileName = path + "系统参数表.txt";
	public static final String snmpFileName = path + "SNMP对象表.txt";
	public static final String arpFileName = path + "IP转发表.txt";
	public static final String sysFileName = path + "系统信息表.txt";
	public static final String devMacFileName = path + "设备MAC表.txt";
	public static final String operLogFileName = path + "操作日志表.txt";
	public static final String vendorFileName = path + "厂商表.txt";
	public static final String neibourFileName = "邻居表.txt";
	public static final String userFileName = path + "用户表.txt";
	public static final String userSubnetFileName = path + "用户分区配置表.txt";
	// bug 2009-04-25 未验证 D:\netmon19\\log\\conf_bak
	public static final String confBakDir = path + "log\\conf_bak";
	public static final String keyDevIf = path + "关键设备-接口.txt";
	public static final String InstallPath = path;
	
	
	// 每种报警类型，一次最多允许发送短信的条数
	public static final int SHORT_MSG_MAX_NUM = 5;
	// 默认的异常重试次数
	public static final int DEFAULT_ERROR_RETRY_COUNT = 1;
	public static final int DEFAULT_LIMEN_RETRY_COUNT = 1;
	public static final int DEFAULT_TEMP_RETRY_COUNT = 1;
	
	// 子网类型常量
	public static final int SUBNET_SWITCH = 1;
	public static final int SUBNET_ROUTER = 2;
	public static final int SUBNET_SERVER = 3;
	
	//报警声音文件
	public static final String ALERT_ALARM_FILE = "alarm.wav";
	
	// 设备类型常量
	public static final int DEVICE_SWITCH = 1;
	public static final int DEVICE_ROUTER = 2;
	public static final int DEVICE_DEVICE = 3;
	public static final int DEVICE_SERVER = 4;
	public static final int DEVICE_VIRTUAL = 5;
	public static String DEVICE_SERVICE_NAME = "服务";
	
	// 报警类型常量
	public static final int ALERT_FAULT = 1;
	public static final int ALERT_PC = 2;
	public static final int ALERT_LIMEN = 3;
	public static final int ALERT_TRAP = 4;
	public static final int ALERT_OTHER =5;
	
	// 报警小类型常量
	public static final int SALERT_DEVICE = 1;			// 设备无反应
	public static final int SALERT_SERVER = 2;			// 服务器无反应
	public static final int SALERT_SERVICE = 3;			// 服务无反应
	public static final int SALERT_DEVICE_RESTORE = 20;	// 设备恢复
	public static final int SALERT_SERVER_RESTORE = 21;	// 服务器恢复
	public static final int SALERT_SERVICE_RESTORE = 22;	// 服务恢复
	
	public static final int SALERT_NEW_COMPUTER = 4;		// 新计算机
	public static final int SALERT_CIP_CHANGE = 5;		// 计算机IP改变(C=Computer)
	public static final int SALERT_CDEVICE_CHANGE = 6;	// 上连设备改变
	public static final int SALERT_CNAME_CHANGE = 7;		// 计算机名改变
	public static final int SALERT_CDOMAIN_CHANGE = 8;	// 域或组改变
	public static final int SALERT_CLOGIN_CHANGE = 9;		// 登录名改变
	public static final int SALERT_CINTERFACE_CHANGE = 19;// 接口改变
	
	public static final int SALERT_ARP = 17;// ARP报警
	public static final int SALERT_SYSLOG = 18;		// SYSLOG
	
	public static final int SALERT_CPU_OVERLOAD = 10;		//CPU占用率超过阈值
	public static final int SALERT_CPU_RESTORE = 23;		//CPU占用率恢复正常
	
	public static final int SALERT_FLOW_OVERLOAD = 11;	//接口流量超过阈值
	public static final int SALERT_FLOW_RESTORE = 24;		//接口流量恢复正常
	
	public static final int SALERT_TEMP_OVERLOAD = 12;	//温度超过阈值
	public static final int SALERT_TEMP_RESTORE = 25;		//温度恢复正常
	
	public static final int SALERT_PROCESS_FAULT = 26;	// 进程不存在
	public static final int SALERT_PROCESS_RESTORE = 27;	// 进程恢复

	// 是否演示模式
	public static final boolean demoMode = false;

	public static boolean isDevice(int type) {
		if (type == DEVICE_SWITCH || type == DEVICE_ROUTER
				|| type == DEVICE_DEVICE)
			return true;
		return false;
	}
	
	// 缺省SNMP_TIME_OUT = 5；SNMP_RETRY = 0；发行设置为1和1，共1、2合计3秒。重试可选2，共3/7秒。
	public static int SNMP_TIME_OUT = 1;
	public static int SNMP_RETRY = 1;
	
	public static int TEMP_LIMEN = 25;// AKCP温度阈值（度）
	public static int CPU_LIMEN = 10;// CPU负荷阈值，百分比
	
	public static int licnum = 5888;
	
	public static String BASELINE = "基线";
}
