package com.netblizzard.thread;

public class TestMultiThread implements Runnable {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
		TestMultiThread test = new TestMultiThread();
		Thread thread1 = new Thread(test);
		thread1.setName("myThread");
		Thread thread2 = new Thread(test);
		thread1.start();
		thread2.start();
		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " 线程运行开始!");
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
		System.out.println(Thread.currentThread().getName() + " 线程运行结束!");

	}

	private void output(Thread thread) {
//		-----静态属性-----
//			static int MAX_PRIORITY
//		　　线程可以具有的最高优先级。
		System.out.println("Thread.MAX_PRIORITY\t\t" + Thread.MAX_PRIORITY);
//		　　static int MIN_PRIORITY
//		　　线程可以具有的最低优先级。
		System.out.println("Thread.MIN_PRIORITY\t\t" + Thread.MIN_PRIORITY);
//		　　static int NORM_PRIORITY
//		　　分配给线程的默认优先级。
		System.out.println("Thread.NORM_PRIORITY\t\t" + Thread.NORM_PRIORITY);
//		-----构造方法摘要-----
//		　　Thread(Runnable target)
//		　　分配新的 Thread 对象。
		
//		　　Thread(String name)
//		　　分配新的 Thread 对象。
		
//		-----方法摘要-----
//		　　static Thread currentThread()
//		　　返回对当前正在执行的线程对象的引用。
		System.out.println(Thread.currentThread());
//		　　ClassLoader getContextClassLoader()
//		　　返回该线程的上下文 ClassLoader。
		System.out.println(thread.getContextClassLoader());
//		　　long getId()
//		　　返回该线程的标识符。
		System.out.println(thread.getId());
//		　　String getName()
//		　　返回该线程的名称。
		System.out.println(thread.getName());
//		　　int getPriority()
//		　　返回线程的优先级。
		System.out.println(thread.getPriority());
//		　　Thread.State getState()
//		　　返回该线程的状态。
		System.out.println(thread.getState());
//		　　ThreadGroup getThreadGroup()
//		　　返回该线程所属的线程组。
		System.out.println(thread.getThreadGroup());
//		　　static boolean holdsLock(Object obj)
//		　　当且仅当当前线程在指定的对象上保持监视器锁时，才返回 true。
		System.out.println(Thread.holdsLock(thread));
//		　　void interrupt()
//		　　中断线程。
		
//		　　static boolean interrupted()
//		　　测试当前线程是否已经中断。
		System.out.println(Thread.interrupted());
//		　　boolean isAlive()
//		　　测试线程是否处于活动状态。
		System.out.println(Thread.holdsLock(thread));
//		　　boolean isDaemon()
//		　　测试该线程是否为守护线程。
		System.out.println("线程是否为守护线程\t\t" + Thread.holdsLock(thread));
//		　　boolean isInterrupted()
//		　　测试线程是否已经中断。
		System.out.println("线程是否已经中断\t\t" + Thread.holdsLock(thread));
//		　　void join()
//		　　等待该线程终止。
		
//		　　void join(long millis)
//		　　等待该线程终止的时间最长为 millis 毫秒。
		
//		　　void join(long millis, int nanos)
//		　　等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。
		
//		　　void resume()
//		　　已过时。 该方法只与 suspend() 一起使用，但 suspend() 已经遭到反对，因为它具有死锁倾向。有关更多信息，请参阅为何 Thread.stop、Thread.suspend 和 Thread.resume 遭到反对?。
		
//		　　void run()
//		　　如果该线程是使用独立的 Runnable 运行对象构造的，则调用该 Runnable 对象的 run 方法;否则，该方法不执行任何操作并返回。
		
//		　　void setContextClassLoader(ClassLoader cl)
//		　　设置该线程的上下文 ClassLoader。
		
//		　　void setDaemon(boolean on)
//		　　将该线程标记为守护线程或用户线程。
		
//		　　static void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
//		　　设置当线程由于未捕获到异常而突然终止，并且没有为该线程定义其他处理程序时所调用的默认处理程序。
		
//		　　void setName(String name)
//		　　改变线程名称，使之与参数 name 相同。
		
//		　　void setPriority(int newPriority)
//		　　更改线程的优先级。
		
//		　　void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)
//		　　设置该线程由于未捕获到异常而突然终止时调用的处理程序。
		
//		　　static void sleep(long millis)
//		　　在指定的毫秒数内让当前正在执行的线程休眠(暂停执行)。
		
//		　　static void sleep(long millis, int nanos)
//		　　在指定的毫秒数加指定的纳秒数内让当前正在执行的线程休眠(暂停执行)。
		
//		　　void start()
//		　　使该线程开始执行;Java 虚拟机调用该线程的 run 方法。
		
//		　　void stop()
//		　　已过时。 该方法具有固有的不安全性。用 Thread.stop 来终止线程将释放它已经锁定的所有监视器(作为沿堆栈向上传播的未检查 ThreadDeath 异常的一个自然后果)。如果以前受这些监视器保护的任何对象都处于一种不一致的状态，则损坏的对象将对其他线程可见，这有可能导致任意的行为。stop 的许多使用都应由只修改某些变量以指示目标线程应该停止运行的代码来取代。目标线程应定期检查该变量，并且如果该变量指示它要停止运行，则从其运行方法依次返回。如果目标线程等待很长时间(例如基于一个条件变量)，则应使用 interrupt 方法来中断该等待。有关更多信息，请参阅《为何不赞成使用 Thread.stop、Thread.suspend 和 Thread.resume?》。
		
//		　　void stop(Throwable obj)
//		　　已过时。 该方法具有固有的不安全性。请参阅 stop() 以获得详细信息。该方法的附加危险是它可用于生成目标线程未准备处理的异常(包括若没有该方法该线程不太可能抛出的已检查的异常)。有关更多信息，请参阅为何 Thread.stop、Thread.suspend 和 Thread.resume 遭到反对?。
		
//		　　void suspend()
//		　　已过时。该方法已经遭到反对，因为它具有固有的死锁倾向。如果目标线程挂起时在保护关键系统资源的监视器上保持有锁，则在目标线程重新开始以前任何线程都不能访问该资源。如果重新开始目标线程的线程想在调用 resume 之前锁定该监视器，则会发生死锁。这类死锁通常会证明自己是“冻结”的进程。有关更多信息，请参阅为何 Thread.stop、Thread.suspend 和 Thread.resume 遭到反对?。
		
//		　　String toString()
//		　　返回该线程的字符串表示形式，包括线程名称、优先级和线程组。
		System.out.println("thread.toString():   " + thread.toString());
//		　　static void yield()
//		　　暂停当前正在执行的线程对象，并执行其他线程。
		
	}
}
