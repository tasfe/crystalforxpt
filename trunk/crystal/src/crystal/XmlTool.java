package crystal;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
//文件包
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlTool {
	public XmlTool() {
		super();
	}

	// 1、将XML文件的内容转化为String
	/**
	 * doc2String 将xml文档内容转为String
	 * 
	 * @return 字符串
	 * @param document
	 */
	public static String doc2String(Document document) {
		String s = "";
		try {
			// 使用输出流来进行转化
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 使用GB2312编码
			OutputFormat format = new OutputFormat("  ", true, "GB2312");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	// 2、将符合XML格式的String 转化为XML Document
	/**
	 * string2Document 将字符串转为Document
	 * 
	 * @return
	 * @param s
	 *            xml格式的字符串
	 */
	public static Document string2Document(String s) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}

	// 3、将Document对象保存为一个xml文件到本地
	/**
	 * doc2XmlFile 将Document对象保存为一个xml文件到本地
	 * 
	 * @return true:保存成功 flase:失败
	 * @param filename
	 *            保存的文件名
	 * @param document
	 *            需要保存的document对象
	 */
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/* 将document中的内容写入文件中 */
			// 默认为UTF-8格式，指定为"GB2312"
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GB2312");
			XMLWriter writer = new XMLWriter(
					new FileWriter(new File(filename)), format);
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	// 4、将xml格式的字符串保存为本地文件，如果字符串格式不符合xml规则，则返回失败
	/**
	 * string2XmlFile 将xml格式的字符串保存为本地文件，如果字符串格式不符合xml规则，则返回失败
	 * 
	 * @return true:保存成功 flase:失败
	 * @param filename
	 *            保存的文件名
	 * @param str
	 *            需要保存的字符串
	 */
	public static boolean string2XmlFile(String str, String filename) {
		boolean flag = true;
		try {
			Document doc = DocumentHelper.parseText(str);
			flag = doc2XmlFile(doc, filename);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	// 5、载入一个xml文档
	/**
	 * load 载入一个xml文档
	 * 
	 * @return 成功返回Document对象，失败返回null
	 * @param uri
	 *            文件路径
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

	public static String getNodeValue(String fileName, String rootStr,
			String node) {
		Document doc = load(fileName);
		// Element root = doc.getRootElement();
		/** 先用xpath查找所有ftp节点 并输出它的name属性值 */
		/*
		 * List list = doc.selectNodes("/config/skinConfig"); Iterator it =
		 * list.iterator(); while (it.hasNext()) { Element ftpElement =
		 * (Element) it.next(); System.out.println("ftp_name=" +
		 * ftpElement.attribute("name").getValue()); } // 直接用属性path取得name值 list
		 * = doc.selectNodes("/config/skinConfig/@name"); it = list.iterator();
		 * while (it.hasNext()) { Attribute attribute = (Attribute) it.next();
		 * System.out.println("@name=" + attribute.getValue()); }
		 */
		// 直接取得node的值
		List list = doc.selectNodes(rootStr + node);
		// List list = doc.selectNodes("/config/skinConfig/skin");
		Iterator it = list.iterator();
		Element element = (Element) it.next();
		// System.out.println("element-- " + node + " --value is " +
		// element.getText());
		return element.getText();
	}

	public static String[] getNodeValues(String fileName, String rootStr,
			String[] nodes) {
		if (nodes.length <= 0)
			return null;
		Document doc = load(fileName);
		// 直接取得node的值
		String results[] = new String[nodes.length];
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
		return results;
	}

	public static void setNodeValue(String fileName, String node, String value) {
		Document document = load(fileName);
		// 直接修改node的值
		List list = document.selectNodes("/config/skinConfig/" + node);
		Iterator it = list.iterator();
		Element element = (Element) it.next();
		element.setText(value);
		doc2XmlFile(document, "config.xml");
	}

	public static void setNodesValue(String fileName, String rootStr,
			String[] nodes, String[] values) {
		if (nodes.length != values.length) {
			System.out.println("nodes.length != values.length");
			return;
		}
		Document document = load(fileName);
		// 直接修改node的值
		// rootStr 格式 /config/skinConfig/
		List list = null;
		for (int i = 0; i < nodes.length; i++) {
			list = document.selectNodes(rootStr + nodes[i]);
			Iterator it = list.iterator();
			Element element = (Element) it.next();
			element.setText(values[i]);
		}
		doc2XmlFile(document, fileName);
	}
	
	public static void addNode(String fileName, String rootStr,
			String node) {
		if ("".equals(fileName) || !new File(fileName).exists()
				|| "".equals(rootStr) || "".equals(node))
			return;
		Document document = load(fileName);
		// 直接修改node的值
		// rootStr 格式 /config/skinConfig/
		List list = document.selectNodes(rootStr);
		Iterator it = list.iterator();
		if (it.hasNext()) {
			Element element = (Element) it.next();
			if(!element.selectNodes(node).isEmpty()) {
				element.addElement(node);
			}
		}
		doc2XmlFile(document, fileName);
	}

	// 9、修改或删除某个值或属性
	/** ftp节点删除ftp-host节点 */
	// ftpElement.remove(hostElement);
	/** ftp节点删除name属性 */
	// ftpElement.remove(nameAttribute);
	/** 修改ftp-host的值 */
	// hostElement.setText("192.168.0.1");
	/** 修改ftp节点name属性的值 */
	// nameAttribute.setValue("ChiFeng");
}
