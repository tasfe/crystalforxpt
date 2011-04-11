package com.combanc.monitor.constants;

import java.sql.Timestamp;

import com.combanc.common.Config;
import com.combanc.monitor.util.FileUtils;

/**
 * <p>
 * Title:通用参数类
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
public class MainConstants {
	
	public static Timestamp SERVER_START_TIME = new Timestamp(System.currentTimeMillis());//tomcat 启动时间
	
	public final static int REGULARDATA_LIMIT = 10;	// 接口流速的上限为10Gbmps
	
	public static int LICNUM = 5888;//用户许可数
	
	public static String BASELINE = "基线";

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
	public static final int SUBNET_AP = 4;
	public static final int SUBNET_ENVIRONMENT = 5;
	
	//报警声音文件
	public static final String ALERT_ALARM_FILE =  Config.getContextRealPath() +"monitor/alert/alarm.wav";
	
	// 设备类型常量
	public static final int DEVICE_SWITCH = 1;
	public static final int DEVICE_ROUTER = 2;
	public static final int DEVICE_DEVICE = 3;
	public static final int DEVICE_SERVER = 4;
	public static final int DEVICE_CONTROLLER = 5;
	public static final int DEVICE_AKCP = 6;//温湿度监控设备
	public static final int DEVICE_VIRTUAL = 20;
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
	public static final int SALERT_SERVICE_RESTORE = 22;// 服务恢复
	
	public static final int SALERT_NEW_COMPUTER = 4;	// 新计算机
	public static final int SALERT_CIP_CHANGE = 5;		// 计算机IP改变(C=Computer)
	public static final int SALERT_CDEVICE_CHANGE = 6;	// 上连设备改变
	public static final int SALERT_CNAME_CHANGE = 7;	// 计算机名改变
	public static final int SALERT_CDOMAIN_CHANGE = 8;	// 域或组改变
	public static final int SALERT_CLOGIN_CHANGE = 9;	// 登录名改变
	public static final int SALERT_CINTERFACE_CHANGE = 19;// 接口改变
	
	public static final int SALERT_ARP = 17;			// ARP报警
	public static final int SALERT_SYSLOG = 18;			// SYSLOG
	
	public static final int SALERT_CPU_OVERLOAD = 10;	//CPU占用率超过阈值
	public static final int SALERT_CPU_RESTORE = 23;	//CPU占用率恢复正常
	
	public static final int SALERT_FLOW_OVERLOAD = 11;	//接口流量超过阈值
	public static final int SALERT_FLOW_RESTORE = 24;	//接口流量恢复正常
	
	public static final int SALERT_TEMP_OVERLOAD = 12;	//温度超过阈值
	public static final int SALERT_TEMP_RESTORE = 25;	//温度恢复正常
	
	public static final int SALERT_PROCESS_FAULT = 26;	// 进程不存在
	public static final int SALERT_PROCESS_RESTORE = 27;// 进程恢复
	
	public static final int SALERT_INTERFACE_FAULT = 28;	// 接口异常
	public static final int SALERT_INTERFACE_RESTORE = 29;	// 接口恢复
	
	public static final int SALERT_URL_FAULT = 30;		// URL监控异常
	public static final int SALERT_URL_RESTORE = 31;	// URL监控恢复正常
	
	public static final int SALERT_PING_FAULT = 32;		// Ping监控异常
	public static final int SALERT_PING_RESTORE = 33;	// Ping监控恢复正常
	
	public static final int INTERFACE_STATUS_ONLINE		= 0;	// 接口上线状态
	public static final int INTERFACE_STATUS_OFFLINE	= 1;	// 接口下线状态
	public static final int INTERFACE_STATUS_SHUTDOWN	= 2;	// 接口关闭状态
	public static final int INTERFACE_STATUS_NODATA		= 3;	// 读不到数据

	public static boolean isDevice(int type) {
		if (type == DEVICE_SWITCH || type == DEVICE_ROUTER
				|| type == DEVICE_DEVICE)
			return true;
		return false;
	}
	
	// 判断是否交换/交换+路由
	public static boolean isSwitch(int type) {
		if (type == DEVICE_SWITCH || type == DEVICE_DEVICE)
			return true;
		return false;
	}

	// 判断是否路由/交换+路由
	public static boolean isRouter(int type) {
		if (type == DEVICE_ROUTER || type == DEVICE_DEVICE)
			return true;
		return false;
	}
	
	// 判断是否服务器
	public static boolean isServer(int type) {
		if (type == DEVICE_SERVER )
			return true;
		return false;
	}
	
	// 缺省SNMP_TIME_OUT = 5；SNMP_RETRY = 0；发行设置为1和1，共1、2合计3秒。重试可选2，共3/7秒。
	public static int SNMP_TIME_OUT = 1;
	public static int SNMP_RETRY = 1;
	
	public static int TEMP_LIMEN = 25;// AKCP温度阈值（度）
	public static int CPU_LIMEN = 10;// CPU负荷阈值，百分比
	
	
	public static final String PATH = FileUtils.getClassPath(MainConstants.class) + "\\com\\combanc\\monitor\\txtdata\\";
	public static final String INSTALL_PATH = PATH;
	// 是否演示模式
	public static final boolean DEMO_MODE = false;
	
	// 子网设备发现等SNMP扫描线程，采用固定间隔50。
	public static final int THREAD_GAP = 50;
	
	
	// ping目标类型常量
	public static final int PING_DEST_SERVER = 1;		//服务器
	public static final int PING_DEST_WORKSTATION = 2;	//工作站
	public static final int PING_DEST_PC = 3;			//普通PC
	public static final int PING_DEST_DEVICE = 4;		//网络设备
	public static final int PING_DEST_OTHERS = 5;		//其它
}
