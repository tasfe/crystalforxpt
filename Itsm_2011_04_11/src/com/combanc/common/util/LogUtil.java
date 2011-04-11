package com.combanc.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.combanc.common.Config;

public class LogUtil {

	static {
		try {
			PropertyConfigurator.configure(Config.getClassesPath() + "log4j.properties");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Log getLogger() {
		return LogFactory.getLog("");
	}
}
