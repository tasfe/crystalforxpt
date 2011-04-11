/**
 * 
 */
package com.combanc.itsm.pojo;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.combanc.itsm.util.SpringBeanProxy;

/**
 * 键表
 *
 */
public class KeyTable {
	
	private long id;
	private int sysVersion;
	private long nextAvailable;
	private long cacheSize;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getSysVersion() {
		return sysVersion;
	}
	public void setSysVersion(int sysVersion) {
		this.sysVersion = sysVersion;
	}
	public long getNextAvailable() {
		return nextAvailable;
	}
	public void setNextAvailable(long nextAvailable) {
		this.nextAvailable = nextAvailable;
	}
	public long getCacheSize() {
		return cacheSize;
	}
	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}
	
	
	private static long next = -1;
	private static long last = -1;
	
	public static long getNextKey() {
		return doGetNextKey();
	}
	
	private static synchronized long doGetNextKey() {
		if(next == -1)
			refresh();
		long result = next;
		next++;
		if(next > last)
			refresh();
		return result;
	}
	
	private static void refresh() {
		SessionFactory sessionFactory = (SessionFactory)SpringBeanProxy.getBean("sessionFactory");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Object objResult = session.createQuery("from KeyTable").uniqueResult();
			//if(objResult == null) 
			//	throw new KeyTableNotInitializedException();
			KeyTable keyTable = (KeyTable)objResult;
			next = keyTable.getNextAvailable();
			last = next + keyTable.getCacheSize() - 1;
			keyTable.setNextAvailable(last + 1);
			session.flush();
		}
		finally {
			if(session != null)
				session.close();
		}
	}
}
