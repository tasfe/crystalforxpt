package com.combanc.common.core.service;
/**
 * 抽象服务
 *
 */
public abstract class AbstractSrv {
	
	protected static final String COUNT(String item) {
		if (item == null || item.length() == 0)
			item = "*";
		return " count(" + item + ")";
	}
	
	protected static final String SUM(String item) {
		return " sum(" + item + ")";
	}
	
	protected static final String ROUND(String item) {
		return " (" + item + ") ";
	}
	
	protected static final String LENGTHB(String item) {
		return " lengthb(" + item + ") ";
	}
	
	protected static final String LENGTH(String item) {
		return " length(" + item + ") ";
	}
	
	protected static final String decrateString(String string) {
		if (string == null || string.length() == 0)
			return "%";
		return "%" + string + "%";
	}
}
