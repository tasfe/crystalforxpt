/**
 * 
 */
package com.combanc.itsm.util;

import org.springframework.context.ApplicationContext;

/**
 * spring bean代理
 */
public class SpringBeanProxy {

	/**
	 * 	Spring Application Environment��
	 */
	private static ApplicationContext applicationContext = null;
	
	static synchronized void setApplicationContext(ApplicationContext applicationContext) {
		SpringBeanProxy.applicationContext = applicationContext;
	}
	
	/**
	 * Get the name of the instance of Bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		if (applicationContext == null) {
			throw new RuntimeException("Application Environment error!");
		}
		return applicationContext.getBean(beanName);
	}
}
