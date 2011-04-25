package com.net_blizzard.ntservice;

import java.util.Calendar;

class Service implements Runnable
{
	private boolean runFlag = true;

	/**
	* �趨�����߳����б�־ֵ
	* @param runFlag
	*/
	public synchronized void setRunFlag(boolean runFlag)
	{
		this.runFlag = runFlag;
	}

	/**
	* ȡ�÷����߳����б�־ֵ
	* @param void
	*/
	private synchronized boolean getRunFlag()
	{
		return runFlag;
	}

	@Override
	public void run() 
	{
		System.out.println("�����߳̿�ʼ����");
		while(getRunFlag())
		{
			Calendar cal = Calendar.getInstance();
			long mis = cal.getTimeInMillis();
			System.out.println("��ǰʱ�䣺" + mis);
			try 
			{
				Thread.sleep(1000*10);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println("�����߳̽�������");
	}
}