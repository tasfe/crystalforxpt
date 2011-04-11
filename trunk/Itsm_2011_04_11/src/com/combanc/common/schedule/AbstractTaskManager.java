package com.combanc.common.schedule;

import com.combanc.common.util.Mapx;


public abstract class AbstractTaskManager {
	/**
	 * �����Ѿ����ö�ʱ�ƻ�������
	 */
	public abstract Mapx getUsableTasks();

	/**
	 * ������������
	 */
	public abstract Mapx getConfigEnableTasks();

	/**
	 * ����ĳ�������Cron���ʽ
	 */
	public abstract String getTaskCronExpression(long id);

	/**
	 * ִ��ָ��id������
	 */
	public abstract void execute(long id);

	/**
	 * ָ��id�������Ƿ���������
	 */
	public abstract boolean isRunning(long id);

	/**
	 * ����������
	 */
	public abstract String getCode();

	/**
	 * �������
	 */
	public abstract String getName();
}