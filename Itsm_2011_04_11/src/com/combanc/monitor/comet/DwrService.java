package com.combanc.monitor.comet;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * <p>
 * Title:执行任务
 * </p>
 * <p>
 * Description:反向AJAX：Comet——基于HTTP长连接的服务器推动方式。
 * 客户端向服务器发送请求后，服务器将数据通过response发送给客户端，
 * 但并不会将此response关闭，而是一直通过response将最新的数据发送给客户端浏览器，
 * 直到客户端浏览器关闭。
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
public class DwrService implements ApplicationContextAware{
	
	private ApplicationContext ctx;
	
	public void setApplicationContext(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	/**在前台显示后台消息**/
	public void displayInfo(String information){
		PerformInfo info = new PerformInfo();
		info.setMsg(information);
		info.setTime(new Date());
		info.setType(PerformInfo.TYPE_DISPLAY);
		InfoEvent evt = new InfoEvent(info);
		ctx.publishEvent(evt);
	}
	/**在前台弹出后台消息**/
	public void alertInfo(String information){
		PerformInfo info = new PerformInfo();
		info.setMsg(information);
		info.setTime(new Date());
		info.setType(PerformInfo.TYPE_ALERT);
		InfoEvent evt = new InfoEvent(info);
		ctx.publishEvent(evt);
	}
	




	

}
