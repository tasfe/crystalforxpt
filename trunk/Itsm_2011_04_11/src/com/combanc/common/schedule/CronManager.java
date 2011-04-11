package com.combanc.common.schedule;

import java.io.File;
import java.util.List;
import java.util.Timer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.combanc.common.Config;
import com.combanc.common.util.LogUtil;
import com.combanc.common.util.Mapx;

/**
 * 初始化一个定时器，该定时器按指定的间隔调用CronMontior中的run()
 * 
 * @author wyuch
 */
public class CronManager {
	private Timer mTimer; // 仅用来定期调用CronMontior.run()的计时器

	private CronMonitor mMonitor;

	private Mapx map = new Mapx();

	private static CronManager instance;

	private long interval;

	public static synchronized CronManager getInstance() {
		if (instance == null) {
			instance = new CronManager();
		}
		return instance;
	}

	public Mapx getManagers() {
		return map;
	}

	private CronManager() {
		loadConfig();
		mTimer = new Timer(true);
		mMonitor = new CronMonitor();
		mTimer.schedule(mMonitor, 0, interval);
		LogUtil.getLogger().info("----" + Config.getAppCode() + "(" + Config.getAppName() + "): CronManager Initialized----");
	}

	private void loadConfig() {
		String path = Config.getContextRealPath() + "WEB-INF/classes/framework.xml";
		SAXReader reader = new SAXReader(false);
		Document doc;
		try {
			doc = reader.read(new File(path));
			Element root = doc.getRootElement();
			Element cron = root.element("cron");
			List types = cron.elements();
			GeneralTaskManager gtm = new GeneralTaskManager();
			map.put(gtm.getCode(), gtm);
			for (int i = 0; i < types.size(); i++) {
				Element type = (Element) types.get(i);
				String tag = type.getName();
				if (tag.equals("config")) {
					String name = type.attributeValue("name");
					String value = type.getText();
					if (name.equals("RefreshInterval")) {
						this.interval = Long.parseLong(value);
					}
				} else if (tag.equals("taskManager")) {
					String className = type.attributeValue("class");
					try {
						Object o = Class.forName(className).newInstance();
						if (o instanceof AbstractTaskManager) {
							AbstractTaskManager ctm = (AbstractTaskManager) o;
							map.put(ctm.getCode(), ctm);
						} else {
							throw new RuntimeException("指定的类" + className + "不是CronTaskManager的子类.");
						}
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else if (tag.equals("task")) {// 一般任务
					String className = type.attributeValue("class");
					try {
						Object o = Class.forName(className).newInstance();
						if (o instanceof GeneralTask) {
							GeneralTask gt = (GeneralTask) Class.forName(className).newInstance();
							gtm.add(gt);
						} else {
							throw new RuntimeException("指定的类" + className + "不是GeneralTask的子类.");
						}
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 本方法用于后台界面显示
	 */
	public Mapx getTaskTypes() {
		Mapx rmap = new Mapx();
		Object[] vs = map.valueArray();
		for (int i = 0; i < map.size(); i++) {
			AbstractTaskManager ctm = (AbstractTaskManager) vs[i];
			if (ctm instanceof GeneralTaskManager) {
				continue;
			}
			rmap.put(ctm.getCode(), ctm.getName());
		}
		return rmap;
	}

	/**
	 * 本方法用于后台界面显示
	 */
	public Mapx getConfigEnableTasks(String code) {
		AbstractTaskManager ctm = (AbstractTaskManager) map.get(code);
		if (ctm == null) {
			return null;
		}
		return ctm.getConfigEnableTasks();
	}

	public AbstractTaskManager getCronTaskManager(String code) {
		return (AbstractTaskManager) map.get(code);
	}

	public void destory() {
		mMonitor.destory();
		mTimer.cancel();
	}

	public long getInterval() {
		return interval;
	}
}
