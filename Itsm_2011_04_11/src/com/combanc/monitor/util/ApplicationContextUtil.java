package com.combanc.monitor.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * <p>
 * Title:ApplicationContextUtil
 * </p>
 * <p>
 * Description: 实现org.springframework.context.ApplicationContextAware接口来让
 * Spring在启动的时候为我们注入ApplicationContext对象.之后我们就可以调用其
 * getBean("beanName")方法来得到由Spring 管理所有对象.
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
public class ApplicationContextUtil implements ApplicationContextAware{
	
	private static ApplicationContext context;
	//声明一个静态变量保存
	public void setApplicationContext(ApplicationContext contex) throws BeansException {
		this.context=contex;
	}
	public static ApplicationContext getContext(){
		return context;
	} 

}
