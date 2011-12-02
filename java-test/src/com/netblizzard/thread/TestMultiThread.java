package com.netblizzard.thread;

public class TestMultiThread implements Runnable {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " �߳����п�ʼ!");
		TestMultiThread test = new TestMultiThread();
		Thread thread1 = new Thread(test);
		thread1.setName("myThread");
		Thread thread2 = new Thread(test);
		thread1.start();
		thread2.start();
		System.out.println(Thread.currentThread().getName() + " �߳����н���!");
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " �߳����п�ʼ!");
		if("myThread".equals(Thread.currentThread().getName())) {
			//output(Thread.currentThread());
		}
		for (int i = 0; i < 10; i++) {
			System.out.println("----------------------" + i + " " + Thread.currentThread().getName());
			try {
				Thread.sleep((int) Math.random() * 10);
				if("myThread".equals(Thread.currentThread().getName())) {
					System.out.println("thread state -----> " + Thread.currentThread().getState());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " �߳����н���!");

	}

	private void output(Thread thread) {
//		-----��̬����-----
//			static int MAX_PRIORITY
//		�����߳̿��Ծ��е�������ȼ���
		System.out.println("Thread.MAX_PRIORITY\t\t" + Thread.MAX_PRIORITY);
//		����static int MIN_PRIORITY
//		�����߳̿��Ծ��е�������ȼ���
		System.out.println("Thread.MIN_PRIORITY\t\t" + Thread.MIN_PRIORITY);
//		����static int NORM_PRIORITY
//		����������̵߳�Ĭ�����ȼ���
		System.out.println("Thread.NORM_PRIORITY\t\t" + Thread.NORM_PRIORITY);
//		-----���췽��ժҪ-----
//		����Thread(Runnable target)
//		���������µ� Thread ����
		
//		����Thread(String name)
//		���������µ� Thread ����
		
//		-----����ժҪ-----
//		����static Thread currentThread()
//		�������ضԵ�ǰ����ִ�е��̶߳�������á�
		System.out.println(Thread.currentThread());
//		����ClassLoader getContextClassLoader()
//		�������ظ��̵߳������� ClassLoader��
		System.out.println(thread.getContextClassLoader());
//		����long getId()
//		�������ظ��̵߳ı�ʶ����
		System.out.println(thread.getId());
//		����String getName()
//		�������ظ��̵߳����ơ�
		System.out.println(thread.getName());
//		����int getPriority()
//		���������̵߳����ȼ���
		System.out.println(thread.getPriority());
//		����Thread.State getState()
//		�������ظ��̵߳�״̬��
		System.out.println(thread.getState());
//		����ThreadGroup getThreadGroup()
//		�������ظ��߳��������߳��顣
		System.out.println(thread.getThreadGroup());
//		����static boolean holdsLock(Object obj)
//		�������ҽ�����ǰ�߳���ָ���Ķ����ϱ��ּ�������ʱ���ŷ��� true��
		System.out.println(Thread.holdsLock(thread));
//		����void interrupt()
//		�����ж��̡߳�
		
//		����static boolean interrupted()
//		�������Ե�ǰ�߳��Ƿ��Ѿ��жϡ�
		System.out.println(Thread.interrupted());
//		����boolean isAlive()
//		���������߳��Ƿ��ڻ״̬��
		System.out.println(Thread.holdsLock(thread));
//		����boolean isDaemon()
//		�������Ը��߳��Ƿ�Ϊ�ػ��̡߳�
		System.out.println("�߳��Ƿ�Ϊ�ػ��߳�\t\t" + Thread.holdsLock(thread));
//		����boolean isInterrupted()
//		���������߳��Ƿ��Ѿ��жϡ�
		System.out.println("�߳��Ƿ��Ѿ��ж�\t\t" + Thread.holdsLock(thread));
//		����void join()
//		�����ȴ����߳���ֹ��
		
//		����void join(long millis)
//		�����ȴ����߳���ֹ��ʱ���Ϊ millis ���롣
		
//		����void join(long millis, int nanos)
//		�����ȴ����߳���ֹ��ʱ���Ϊ millis ���� + nanos ���롣
		
//		����void resume()
//		�����ѹ�ʱ�� �÷���ֻ�� suspend() һ��ʹ�ã��� suspend() �Ѿ��⵽���ԣ���Ϊ���������������йظ�����Ϣ�������Ϊ�� Thread.stop��Thread.suspend �� Thread.resume �⵽����?��
		
//		����void run()
//		����������߳���ʹ�ö����� Runnable ���ж�����ģ�����ø� Runnable ����� run ����;���򣬸÷�����ִ���κβ��������ء�
		
//		����void setContextClassLoader(ClassLoader cl)
//		�������ø��̵߳������� ClassLoader��
		
//		����void setDaemon(boolean on)
//		���������̱߳��Ϊ�ػ��̻߳��û��̡߳�
		
//		����static void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
//		�������õ��߳�����δ�����쳣��ͻȻ��ֹ������û��Ϊ���̶߳��������������ʱ�����õ�Ĭ�ϴ������
		
//		����void setName(String name)
//		�����ı��߳����ƣ�ʹ֮����� name ��ͬ��
		
//		����void setPriority(int newPriority)
//		���������̵߳����ȼ���
		
//		����void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
//		�������ø��߳�����δ�����쳣��ͻȻ��ֹʱ���õĴ������
		
//		����static void sleep(long millis)
//		������ָ���ĺ��������õ�ǰ����ִ�е��߳�����(��ִͣ��)��
		
//		����static void sleep(long millis, int nanos)
//		������ָ���ĺ�������ָ�������������õ�ǰ����ִ�е��߳�����(��ִͣ��)��
		
//		����void start()
//		����ʹ���߳̿�ʼִ��;Java ��������ø��̵߳� run ������
		
//		����void stop()
//		�����ѹ�ʱ�� �÷������й��еĲ���ȫ�ԡ��� Thread.stop ����ֹ�߳̽��ͷ����Ѿ����������м�����(��Ϊ�ض�ջ���ϴ�����δ��� ThreadDeath �쳣��һ����Ȼ���)�������ǰ����Щ�������������κζ��󶼴���һ�ֲ�һ�µ�״̬�����𻵵Ķ��󽫶������߳̿ɼ������п��ܵ����������Ϊ��stop �����ʹ�ö�Ӧ��ֻ�޸�ĳЩ������ָʾĿ���߳�Ӧ��ֹͣ���еĴ�����ȡ����Ŀ���߳�Ӧ���ڼ��ñ�������������ñ���ָʾ��Ҫֹͣ���У���������з������η��ء����Ŀ���̵߳ȴ��ܳ�ʱ��(�������һ����������)����Ӧʹ�� interrupt �������жϸõȴ����йظ�����Ϣ������ġ�Ϊ�β��޳�ʹ�� Thread.stop��Thread.suspend �� Thread.resume?����
		
//		����void stop(Throwable obj)
//		�����ѹ�ʱ�� �÷������й��еĲ���ȫ�ԡ������ stop() �Ի����ϸ��Ϣ���÷����ĸ���Σ����������������Ŀ���߳�δ׼��������쳣(������û�и÷������̲߳�̫�����׳����Ѽ����쳣)���йظ�����Ϣ�������Ϊ�� Thread.stop��Thread.suspend �� Thread.resume �⵽����?��
		
//		����void suspend()
//		�����ѹ�ʱ���÷����Ѿ��⵽���ԣ���Ϊ�����й��е������������Ŀ���̹߳���ʱ�ڱ����ؼ�ϵͳ��Դ�ļ������ϱ�������������Ŀ���߳����¿�ʼ��ǰ�κ��̶߳����ܷ��ʸ���Դ��������¿�ʼĿ���̵߳��߳����ڵ��� resume ֮ǰ�����ü���������ᷢ����������������ͨ����֤���Լ��ǡ����ᡱ�Ľ��̡��йظ�����Ϣ�������Ϊ�� Thread.stop��Thread.suspend �� Thread.resume �⵽����?��
		
//		����String toString()
//		�������ظ��̵߳��ַ�����ʾ��ʽ�������߳����ơ����ȼ����߳��顣
		System.out.println("thread.toString():   " + thread.toString());
//		����static void yield()
//		������ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̡߳�
		
	}
}
