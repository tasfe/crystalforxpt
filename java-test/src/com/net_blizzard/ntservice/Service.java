package com.net_blizzard.ntservice;

import java.util.Calendar;

class Service implements Runnable
{
	private boolean runFlag = true;

	/**
	* 设定服务线程运行标志值
	* @param runFlag
	*/
	public synchronized void setRunFlag(boolean runFlag)
	{
		this.runFlag = runFlag;
	}

	/**
	* 取得服务线程运行标志值
	* @param void
	*/
	private synchronized boolean getRunFlag()
	{
		return runFlag;
	}

	@Override
	public void run() 
	{
		System.out.println("服务线程开始运行");
		while(getRunFlag())
		{
			Calendar cal = Calendar.getInstance();
			long mis = cal.getTimeInMillis();
			System.out.println("当前时间：" + mis);
			try 
			{
				Thread.sleep(1000*10);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		System.out.println("服务线程结束运行");
	}
}