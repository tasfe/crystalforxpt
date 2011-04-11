package com.combanc.monitor.vo.urlMonitor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.combanc.monitor.pojo.MonitorUrlResponse;

public abstract class AbstractMonitor implements IMonitor {
	protected boolean debug;
	private static final String[] LOGTYPE_NAME = { "信息", "调试", "警告", "错误" };

	private static final SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public AbstractMonitor() {
		this.debug = false;
	}

	private static String getTypeName(int logType) {
		if ((logType < 0) || (logType >= LOGTYPE_NAME.length)) {
			return LOGTYPE_NAME[0];
		}
		return LOGTYPE_NAME[logType];
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	private void log(String msg) {
		log(1, msg, null);
	}

	private void log(int logType, String msg) {
		log(logType, msg, null);
	}

	private void log(String msg, Throwable ex) {
		log(3, msg, ex);
	}

	private void log(int logType, String msg, Throwable ex) {
		if (!(this.debug)) {
			return;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(format.format(new Date()));
		sb.append("\t");
		sb.append(getTypeName(logType));
		sb.append("\t");
		sb.append(msg);
		System.out.println(sb);
		if (ex != null)
			ex.printStackTrace();
	}

	protected String composeLog(MonitorUrlResponse hur, String log) {
		String ip = hur.getServerIp();
		StringBuffer sb = new StringBuffer();
		return "[" + hur.getServerIp() + "-" 
				+ (hur.getType().equals(2) ? "HTTPS" : "HTTP") 
				+ "-" + hur.getId()+ "]-" + log;
	}

}

