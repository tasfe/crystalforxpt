package com.combanc.common.schedule;

public abstract class AbstractTask {

	/**
	 * ID,��ͬһ��������Ψһ
	 */
	public abstract long getID();

	/**
	 * ���
	 */
	public abstract String getType();

	/**
	 * ���
	 */
	public abstract String getName();

	/**
	 * Cron���ʽ
	 */
	public abstract String getCronExpression();
}
