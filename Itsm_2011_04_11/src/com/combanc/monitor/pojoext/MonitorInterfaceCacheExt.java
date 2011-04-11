package com.combanc.monitor.pojoext;

/**
 * MonitorInterfaceCache entity. @author MyEclipse Persistence Tools
 */

public class MonitorInterfaceCacheExt {

	// Fields
// basic info
	private String ip;
	private String port;
	private String interface_;
	private String description;
	private String note;
	private String llocate;
	private String user;
	
	// 端口上的接入用户数
	private int pccount;

	// 1用户接口，2设备接口
	private int type;
	// 1得到有效数据，-1没有得到有效数据，0未轮训
	private int pollStatus;
	// 1接口上线，-1接口下线，-2接口关闭
	private int status;
	
//    "上次时间","当前时间","接口速率"
	private long speed;
	private String strSpeed;
	private long lastTime;
	private long time;
	
	//0-9列是上次值，必须为： "上次发送字节","上次接收字节","上次发送单播包","上次接收单播包",
	// "上次发送广播包","上次接收广播包","上次发送错包","上次接收错包", "上次发送丢包","上次接收丢包",
	private long lastData[]  = new long[10];
	
//	 * 10-19列是当前与上次的差值，必须为： "当前发送字节","当前接收字节","当前发送单播包","当前接收单播包",
//	 * "当前发送广播包","当前接收广播包","当前发送错包","当前接收错包", "当前发送丢包","当前接收丢包"
//	 * 必须有：当前时间、上次时间、接口速率项
	private long data[]  = new long[10];
	
	private String strData[] = new String[18]; // 发送、接收、双向、发送、接收、双向如此循环，共15组数据，最后再加一组平均包长
	
	private long peekData[] = new long[3]; // 发送、接收、双向负载峰值
	
	// Constructors

	/** default constructor */
	public MonitorInterfaceCacheExt() {
		this.pccount = 0;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInterface_() {
		return interface_;
	}

	public void setInterface_(String interface1) {
		interface_ = interface1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLlocate() {
		return llocate;
	}

	public void setLlocate(String llocate) {
		this.llocate = llocate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPccount(int pccount) {
		this.pccount = pccount;
	}

	public int getPccount() {
		return pccount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPollStatus() {
		return pollStatus;
	}

	public void setPollStatus(int pollStatus) {
		this.pollStatus = pollStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setLastData(long lastData[]) {
		this.lastData = lastData;
	}

	public long[] getLastData() {
		return lastData;
	}

	public void setData(long data[]) {
		this.data = data;
	}

	public long[] getData() {
		return data;
	}

	public void setStrData(String strData[]) {
		this.strData = strData;
	}

	public String[] getStrData() {
		return strData;
	}

	public void setStrSpeed(String strSpeed) {
		this.strSpeed = strSpeed;
	}

	public String getStrSpeed() {
		return strSpeed;
	}

	public void setPeekData(long peekData[]) {
		this.peekData = peekData;
	}

	public long[] getPeekData() {
		return peekData;
	}

}