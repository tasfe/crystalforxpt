package com.combanc.monitor.comet;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import org.springframework.web.context.ServletContextAware;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener; 

/**
 * <p>
 * Title:监听事件，发送信息到页面
 * </p>
 * <p>
 * Description:实现ApplicationListener,ServletContextAware接口，对中文需要编码
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
public class NotifyClient implements ApplicationListener,ServletContextAware{
	
	private ServletContext servletContext = null;
	
	public void setServletContext( ServletContext servletContext ){
		this.servletContext = servletContext;
	} 
	/**
	 * 这里主要通过WebContext类获得DWR应用的WEB上下文，用ServletContext获得DWRServlet的上下文，
	 * 以及通过WEB上下文获取访问本应用的客户端浏览器的ScriptSession。
	 * 通过ScriptSession，可以在服务器端向客户端浏览器发出执行js方法的指令，并把服务器端数据传送给js方法
	 */
	public void onApplicationEvent(ApplicationEvent event) {
	    if (event instanceof InfoEvent) {
	      PerformInfo info = (PerformInfo)event.getSource();
	      
	      //ServerContextFactory 用于访问当前的web应用上下文信息（dwr内部封装的ServerContext）
	      ServerContext ctx = ServerContextFactory.get(servletContext );
	      
	      Collection<ScriptSession> sessions =  ctx.getScriptSessionsByPage("/bottom.jsp");  
	      for (ScriptSession session : sessions) {
	          ScriptBuffer script = new ScriptBuffer();
	          String s=null;
	          try {
	            s = java.net.URLEncoder.encode(info.getMsg(),"UTF-8");
	            s = s.replace("+", "%20");
	          } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	          }
	          if(PerformInfo.TYPE_DISPLAY.equals(info.getType())){
	        	  script.appendScript("putInfo('").appendScript(s).appendScript("');");
	          }else if (PerformInfo.TYPE_ALERT.equals(info.getType())){
	        	  script.appendScript("alert(decodeURI('").appendScript(s).appendScript("'));");
	          }
	        	  
	         // System.out.println(script.toString());
	          session.addScript(script);
	      }        
	    }
	}

}
