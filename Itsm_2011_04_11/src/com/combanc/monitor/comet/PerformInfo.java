package com.combanc.monitor.comet;

import java.util.Date;

/**
 * <p>
 * Title:信息载体
 * </p>
 * <p>
 * Description:可以根据user或session id判断是否是自己的信息.
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
public class PerformInfo {
	public static String TYPE_DISPLAY = "0";//打印
	public static String TYPE_ALERT = "1";//弹出
	private int id;
	private String msg;
	private Date time;
	/**显示样式，打印 0 弹出 1**/
	private String type = TYPE_DISPLAY;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	
	
}
