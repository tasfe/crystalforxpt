package com.net_blizzard.ntservice;

public class TestService
{
	private static Thread thread=null;
	private static Service service = null;

	/**
	* �˳����񷽷�(�÷��������в��� String [] args)
	* @param args
	*/
	public static void StopService(String[] args)
	{
		System.out.println("ֹͣ����");
		service.setRunFlag(false);
	}
	/**
	* �������񷽷�(�÷��������в��� String [] args)
	* @param args
	*/
	public static void StartService(String[] args)
	{
		System.out.println("��������");
		// ���������߳�
		service = new Service();
		thread=new Thread(service);
		try
		{
			// �������߳��趨Ϊ�û��̣߳��Ա���StartService�����������߳��˳�
			thread.setDaemon(false);
			if(!thread.isDaemon())
			{
				System.out.println("�ɹ��趨�߳�Ϊ�û��̣߳�");
			}

			//���������߳�
			thread.start();
		}
		catch(SecurityException se)
		{
			System.out.println("�߳������趨ʧ�ܣ�");
		}
	}
}