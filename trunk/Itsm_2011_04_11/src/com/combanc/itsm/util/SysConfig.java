package com.combanc.itsm.util;


import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;


public class SysConfig {
	
	//所有的配置文件
	private static List<SysConfigItem> sysItems = null;
	private static boolean initialized = false;
	
	public static void init() {
		if (initialized)
			return;
		try {
			sysItems = new ArrayList<SysConfigItem>();
			String webPath = getRealPath();
			webPath = webPath.substring(0, webPath.lastIndexOf("WEB-INF"));
			DocumentBuilderFactory domBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = domBuilderFactory.newDocumentBuilder();
			Document doc = domBuilder.parse(webPath + "\\WEB-INF\\systemconfig.xml");
			NodeList synNodes = doc.getElementsByTagName("sys");
			for(int i = 0; i < synNodes.getLength(); i++) {
				NamedNodeMap attribs = synNodes.item(i).getAttributes();
				SysConfigItem item = new SysConfigItem();
				item.setSysLdap("true".equals(attribs.getNamedItem("sysIsLdap").getNodeValue())?true:false);
				sysItems.add(item);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		initialized = true;
	}

	public static String getRealPath() {
		
		 String realPath = SysConfig.class.getClassLoader().getResource("").getFile();
		 java.io.File file = new java.io.File(realPath);
		 realPath = file.getAbsolutePath();
		 try {
		 realPath = java.net.URLDecoder.decode(realPath, "utf-8");
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		 return realPath;
	}

	public static List<SysConfigItem> getSysItems()
	{
		SysConfig.init();
		return sysItems;
	}
}
