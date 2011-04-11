package com.combanc.monitor.quartz;

import java.util.ArrayList;
import java.util.List;

import com.combanc.monitor.pojo.MonitorPingResponseRecord;
import com.combanc.monitor.pojo.MonitorPingResult;
import com.combanc.monitor.pojo.MonitorPingResultDay;

public class PingUiPubData {
	
	// 公共数据，存储今天的Ping统计数据
	public static List<MonitorPingResultDay> pingResultDayList = new ArrayList<MonitorPingResultDay>();
	// 公共数据，存储今天的Ping通断状态数据
	public static List<MonitorPingResponseRecord> pingResponseRecList = new ArrayList<MonitorPingResponseRecord>();
	
	// 存储最近一次ping的结果
	public static List<MonitorPingResult> latestPingResultList = new ArrayList<MonitorPingResult>();
	
	public static int countOfPingView = 0;		// 表示PingViewFrame的实例个数
}
