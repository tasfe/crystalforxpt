package com.combanc.monitor.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * <p>Title: XML解析器</p>
 *
 * <p>Description: 实现了对xml文档的操作</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * <p>Company: Combanc</p>
 *
 * @author 
 * @version 1.0
 */
public class XmlTool {

	public XmlTool() {
		super();
	}
	
	// 5、载入一个xml文档
	/**
	 * load 载入一个xml文档
	 * @param filename 文件路径
	 * @return 成功返回Document对象，失败返回null
	 */
	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 获取指定节点名的指定属性
	 * @param fileName 
	 * @param rootStr
	 * @param node
	 * @return
	 */
	public static String getNodeValue(String fileName, String rootStr,String node) {
		Document doc = load(fileName);
		/** 先用xpath查找所有ftp节点 并输出它的name属性值 */
		// 直接取得node的值
		List list = doc.selectNodes(rootStr + node);
		Iterator it = list.iterator();
		Element element = (Element) it.next();
		return element.getText();
	}
	/**
	 * 获取指定节点名的指定属性
	 * @param fileName
	 * @param rootStr
	 * @param nodes
	 * @return
	 */
	public static String[] getNodeValues(String fileName, String rootStr,String[] nodes) {
		if (nodes.length <= 0)
			return null;
		Document doc = load(fileName);
		// 直接取得node的值
		String results[] = new String[nodes.length];
		if (doc != null) {
			for (int i = 0; i < nodes.length; i++) {
				List list = doc.selectNodes(rootStr + nodes[i]);
				if(list.isEmpty()) {
					results[i]="";
					continue;
				}
				Iterator it = list.iterator();
				Element element = (Element) it.next();
				results[i] = element.getText();
			}
		}
		return results;
	}
}
