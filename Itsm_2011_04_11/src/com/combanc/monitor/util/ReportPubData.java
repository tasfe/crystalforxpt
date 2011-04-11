package com.combanc.monitor.util;

public class ReportPubData {
	
	// 报表时间类型
	public final static int REPORT_TYPE_DAY       = 0;	// 天报表
	public final static int REPORT_TYPE_WEEK      = 1;	// 周报表
	public final static int REPORT_TYPE_MONTH     = 2;	// 月报表
	public final static int REPORT_TYPE_SEASON    = 3;	// 季度报表
	public final static int REPORT_TYPE_HALF_YEAR = 4;	// 半年报表
	public final static int REPORT_TYPE_YEAR      = 5;	// 年报表
	public final static int REPORT_TYPE_CUSTOM    = 6;	// 自定义报表
	
	// 指标类型
	final static int INDICATOR_TYPE_RESPONSE_TIME	= 0;// 响应时间
	final static int INDICATOR_TYPE_CPU_OCCU 		= 1;// CPU占用率
	final static int INDICATOR_TYPE_DATA_FLOW		= 2;// 数据流量
	final static int INDICATOR_TYPE_MEM_OCCU		= 3;// 内存占用率
	final static int INDICATOR_TYPE_VIRMEM_OCCU		= 4;// 虚存占用率
	final static int INDICATOR_TYPE_DISK_OCCU		= 5;// 磁盘占用率
	final static int INDICATOR_TYPE_PROCESS_NUM		= 6;// 进程数
	final static int INDICATOR_TYPE_PORT_LINK_NUM	= 7;// 端口连接数
	final static int INDICATOR_TYPE_AVAILABILITY	= 8;// 设备可用性
	
	// 图表类型
	final static int CHART_TYPE_THREAD	= 0;	// 曲线图
	final static int CHART_TYPE_COLUMN	= 1;	// 柱状图
	final static int CHART_TYPE_PIE		= 2;	// 饼状图
	
	// 取值类型
	final static int VALUE_TYPE_AVERAGE	= 0;	// 平均值
	final static int VALUE_TYPE_MAX		= 1;	// 最大值
	final static int VALUE_TYPE_MIN		= 2;	// 最小值
	
	// 数据表数据采集的时间密度
	public final static int TABLE_TIME_TYPE_MINUTE	= 0;	// 分钟数据表
	public final static int TABLE_TIME_TYPE_HOUR	= 1;	// 小时数据表
	public final static int TABLE_TIME_TYPE_DAY		= 2;	// 天数据表
	
	// 数据流量单位的类型
	public final static int DATAFLOW_UNIT_TYPE_KBPS	= 0;	// Kbps
	public final static int DATAFLOW_UNIT_TYPE_MBPS	= 1;	// Mbps

	// 数据库中历史数据的生命周期
	public static int MINUTE_TABLE_LIFE_TIME	= 7;	// 分钟表中数据记录的生命长度
	public static int HOUR_TABLE_LIFE_TIME 	= 90;	// 小时表中数据记录的生命长度
	// 设定的数据库合并时间（当有足够长时间没有进行数据合并时，出发数据合并）
	public final static long DATA_MERGE_CYCLETIME	= 1000L*3600*24*1;
	
	// 横向对比报表中的时间对比类别
	final static int HOUR_IN_DAY		= 0;	// 各天的某小时
	final static int DAY_IN_WEEK		= 1;	// 各周的某天
	final static int DAY_IN_MONTH		= 2;	// 各月的某天
	final static int MONTH_IN_YEAR		= 3;	// 各年的某月
	final static int SEASON_IN_YEAR		= 4;	// 各年的某季度
	
	final static int REPORT_NUM_LIMIT	= 24;	// 一次产生报表数量的限制
	
	public static boolean bMerge_ing	= false;	// 标记是否正在进行数据合并，避免线程冲突

	//public static boolean mergeFinished = false;	//标记当天是否已经进行过数据合并
}	
