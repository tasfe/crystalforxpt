package com.combanc.common;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.combanc.common.util.Mapx;

public class Config {
	private static String configFileName;

	private static Mapx configMap = null;

	private static long LastUpdateTime;

	private static long RefershPeriod = 60000;

	public static int OnlineUserCount = 0;

	public static int LoginUserCount = 0;

	public static boolean isDatabaseConfiged;// 数据库是否己配置

	public static void readConfigFileName(String fileName) {
		configFileName = fileName;
		init();
	}

	protected static void init() {
		if (System.currentTimeMillis() - LastUpdateTime > RefershPeriod) {
			if (configMap == null) {
				configMap = new Mapx();
			}
			String path = getContextRealPath();
			configMap.put("App.ContextRealPath", path);
/*			configFileName = getRealConfigFileName();
			File f = new File(configFileName);
			if (!f.exists()) {
				System.out.println("配置文件" + configFileName + "未找到!");
				throw new RuntimeException("配置文件未找到!");
			}*/
			configMap.put("App.Code", "ITSM");
			configMap.put("App.Name", "康邦IT运维系统");
			configMap.put("System.JavaVersion", System.getProperty("java.version"));
			configMap.put("System.JavaVendor", System.getProperty("java.vendor"));
			configMap.put("System.JavaHome", System.getProperty("java.home"));
			configMap.put("System.OSPatchLevel", System.getProperty("sun.os.patch.level"));// 其他JDK以后补充
			configMap.put("System.OSArch", System.getProperty("os.arch"));
			configMap.put("System.OSVersion", System.getProperty("os.version"));
			configMap.put("System.OSName", System.getProperty("os.name"));
			configMap.put("System.OSUserLanguage", System.getProperty("user.language"));
			configMap.put("System.OSUserName", System.getProperty("user.name"));
			configMap.put("System.LineSeparator", System.getProperty("line.separator"));
			configMap.put("System.FileSeparator", System.getProperty("file.separator"));
			configMap.put("System.FileEncode", System.getProperty("file.encoding"));
			//loadConfig(f);

			if (LastUpdateTime == 0) {
				System.out.println("----" + configMap.get("App.Code") + "(" + configMap.get("App.Name")
						+ "): Config Initialized----");
			}
			LastUpdateTime = System.currentTimeMillis();

			if (configMap.get("Database.Default.Type") != null) {
/*				DataTable dt = new QueryBuilder("select type,value from zdconfig").executeDataTable();
				for (int i = 0; dt != null && i < dt.getRowCount(); i++) {
					Config.setValue(dt.getString(i, 0), dt.getString(i, 1));
				}*/
			}
		}
	}

	private static void loadConfig(File f) {
		SAXReader reader = new SAXReader(false);
		Document doc;
		try {
			doc = reader.read(f);
			Element root = doc.getRootElement();
			Element application = root.element("application");
			List elements = application.elements();
			for (int i = 0; i < elements.size(); i++) {
				Element ele = (Element) elements.get(i);
				configMap.put("App." + ele.attributeValue("name"), ele.getTextTrim());
			}
			Element databases = root.element("databases");
			if (databases != null) {
				List dbs = databases.elements();
				for (int i = 0; i < dbs.size(); i++) {
					Element ele = (Element) dbs.get(i);
					String dbname = ele.attributeValue("name").trim();
					List configs = ele.elements();
					for (int k = 0; k < configs.size(); k++) {
						ele = (Element) configs.get(k);
						configMap.put("Database." + dbname + "." + ele.attributeValue("name"), ele.getTextTrim());
					}
				}
				isDatabaseConfiged = true;
			} else {
				isDatabaseConfiged = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void update() {
		init();
	}

	protected static String getConfigFileName() {
		return configFileName;
	}

	protected static void setConfigFileName(String fileName) {
		configFileName = fileName;
	}

	public static String getValue(String configName) {
		if (System.currentTimeMillis() - LastUpdateTime > RefershPeriod) {
			init();
		}
		return (String) configMap.get(configName);
	}

	public static void setValue(String configName, String configValue) {
		if (System.currentTimeMillis() - LastUpdateTime > RefershPeriod) {
			init();
		}
		configMap.put(configName, configValue);
	}

	/**
	 * 返回class所在目录的实际路径
	 */
	public static String getClassesPath() {
		String path = Config.class.getResource("Config.class").getPath();
		int index = path.indexOf("WEB-INF");
		if (index < 0) {
			path = path.substring(0, path.indexOf("/com/") + 1);
		} else {
			path = path.substring(0, index) + "WEB-INF/classes/";
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
			if (path.startsWith("/")) {
				path = path.substring(1);
			} else if (path.startsWith("file:/")) {
				path = path.substring(6);
			}
		} else {
			if (path.startsWith("file:/")) {
				path = path.substring(5);
			}
		}
		path = path.replaceAll("%20", " ");
		return path;
	}

	/**
	 * WEB应用下返回应用的实际路径
	 */
	public static String getContextRealPath() {
		if (configMap != null) {
			String str = (String) configMap.get("App.ContextRealPath");
			if (str != null) {
				return str;
			}
		}
		String path = Config.class.getResource("Config.class").getPath();
		int index = path.indexOf("WEB-INF");
		if (index < 0) {
			path = path.substring(0, path.indexOf("/com/") + 1);
		} else {
			path = path.substring(0, index);
		}
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
			if (path.startsWith("/")) {
				path = path.substring(1);
			} else if (path.startsWith("file:/")) {
				path = path.substring(6);
			}
		} else {
			if (path.startsWith("file:/")) {
				path = path.substring(5);
			}
		}
		path = path.replaceAll("%20", " ");
		return path;
	}

	public static String getRealConfigFileName() {
		String path = Config.class.getResource("/").getPath();
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
			if (path.startsWith("/")) {
				path = path.substring(1);
			} else if (path.startsWith("file:/")) {
				path = path.substring(6);
			}
		} else {
			if (path.startsWith("file:/")) {
				path = path.substring(5);
			}
		}
		path = path.replaceAll("%20", " ");
		return path + "framework.xml";
	}

	public static String getContextPath() {
		return Config.getValue("App.ContextPath");
	}

	public static String getLogLevel() {
		return Config.getValue("App.LogLevel");
	}

	public static String getAppCode() {
		return Config.getValue("App.Code");
	}

	public static String getAppName() {
		return Config.getValue("App.Name");
	}

	public static boolean isDebugMode() {
		return "true".equalsIgnoreCase(Config.getValue("App.DebugMode"));
	}

	public static String getJavaVersion() {
		return Config.getValue("System.JavaVersion");
	}

	public static String getJavaVendor() {
		return Config.getValue("System.JavaVendor");
	}

	public static String getJavaHome() {
		return Config.getValue("System.JavaHome");
	}

	public static String getContainerInfo() {
		return Config.getValue("System.ContainerInfo");
	}

	public static String getOSName() {
		return Config.getValue("System.OSName");
	}

	public static String getOSPatchLevel() {
		return Config.getValue("System.OSVersion");
	}

	public static String getOSArch() {
		return Config.getValue("System.OSArch");
	}

	public static String getOSVersion() {
		return Config.getValue("System.OSVersion");
	}

	public static String getOSUserLanguage() {
		return Config.getValue("System.OSUserLanguage");
	}

	public static String getOSUserName() {
		return Config.getValue("System.OSUserName");
	}

	public static String getLineSeparator() {
		return Config.getValue("System.LineSeparator");
	}

	public static String getFileSeparator() {
		return Config.getValue("System.FileSeparator");
	}

	public static String getFileEncode() {
		return Config.getValue("System.FileEncode");
	}

	public static int getLoginUserCount() {
		return LoginUserCount;
	}

	public static int getOnlineUserCount() {
		return OnlineUserCount;
	}
}
