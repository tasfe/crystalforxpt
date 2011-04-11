package com.combanc.itsm.quartz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.service.AssetsService;

public class QuartzJob {
	private static final Log log = LogFactory.getLog(AssetsBase.class);

	private AssetsService assetsService;
	private AssetsBase assetsBase;

	public AssetsBase getAssetsBase() {
		return assetsBase;
	}

	public void setAssetsBase(AssetsBase assetsBase) {
		this.assetsBase = assetsBase;
	}

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public void work() {
		System.out.println("Quartz的任务调度！！！");
		prewarning();
	}

	public List<AssetsBase> prewarning() {
		// Date date1 = new Date();
		// Long second1 = date1.getTime();
		// System.out.print(second1);
		// Long second2 = second1 + 60 * 60 * 24 * 1000 * 5;
		// Long second3 = assetsBase.getInDate().getTime()
		// + Integer.parseInt(assetsBase.getQualityTime());
		//
		// DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// Calendar calendar1 = Calendar.getInstance();
		// calendar1.setTimeInMillis(second2);
		// String date2 = formatter.format(calendar1.getTime());
		//
		// Calendar calendar2 = Calendar.getInstance();
		// calendar2.setTimeInMillis(second3);
		// String date3 = formatter.format(calendar2.getTime());
		//
		// System.out.println(calendar1.getTime());
		// System.out.println();
		// System.out.println(calendar2.getTime());
		// //
		// Session session = null;
		// try {
		// session.beginTransaction();
		// String queryString =
		// "from AssetsBase where sysdate+5=AssetsBase.in_date+AssetsBase.quality_time";
		// Query queryObject = session.createQuery(queryString);
		// return queryObject.list();
		// } catch (RuntimeException re) {
		// log.error("find all failed", re);
		// throw re;
		// } finally {
		// try {
		// session.close();
		// return null;
		// } catch (Exception ex) {
		// }
		// }
		return null;
	}
}
