package com.combanc.monitor.util.listener;

import java.io.InputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听tomcat，在tomcat启动后先清空实时数据表里之前保存的数据
 * @author Administrator
 *
 */
public class InitRealTimeTableListener implements ServletContextListener{

	private ServletContext context = null;
 
	public void contextDestroyed(ServletContextEvent arg0) {
		this.context = null;
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		this.context = arg0.getServletContext();
		TruncateTableTask task = new TruncateTableTask();
		task.run();
		
	}
}
