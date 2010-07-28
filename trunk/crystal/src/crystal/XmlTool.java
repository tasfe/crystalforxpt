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
//�ļ���
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

	// 1����XML�ļ�������ת��ΪString
	/**
	 * doc2String ��xml�ĵ�����תΪString
	 * 
	 * @return �ַ���
	 * @param document
	 */
	public static String doc2String(Document document) {
		String s = "";
		try {
			// ʹ�������������ת��
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// ʹ��GB2312����
			OutputFormat format = new OutputFormat("  ", true, "GB2312");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	// 2��������XML��ʽ��String ת��ΪXML Document
	/**
	 * string2Document ���ַ���תΪDocument
	 * 
	 * @return
	 * @param s
	 *            xml��ʽ���ַ���
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

	// 3����Document���󱣴�Ϊһ��xml�ļ�������
	/**
	 * doc2XmlFile ��Document���󱣴�Ϊһ��xml�ļ�������
	 * 
	 * @return true:����ɹ� flase:ʧ��
	 * @param filename
	 *            ������ļ���
	 * @param document
	 *            ��Ҫ�����document����
	 */
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/* ��document�е�����д���ļ��� */
			// Ĭ��ΪUTF-8��ʽ��ָ��Ϊ"GB2312"
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

	// 4����xml��ʽ���ַ�������Ϊ�����ļ�������ַ�����ʽ������xml�����򷵻�ʧ��
	/**
	 * string2XmlFile ��xml��ʽ���ַ�������Ϊ�����ļ�������ַ�����ʽ������xml�����򷵻�ʧ��
	 * 
	 * @return true:����ɹ� flase:ʧ��
	 * @param filename
	 *            ������ļ���
	 * @param str
	 *            ��Ҫ������ַ���
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

	// 5������һ��xml�ĵ�
	/**
	 * load ����һ��xml�ĵ�
	 * 
	 * @return �ɹ�����Document����ʧ�ܷ���null
	 * @param uri
	 *            �ļ�·��
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
		/** ����xpath��������ftp�ڵ� ���������name����ֵ */
		/*
		 * List list = doc.selectNodes("/config/skinConfig"); Iterator it =
		 * list.iterator(); while (it.hasNext()) { Element ftpElement =
		 * (Element) it.next(); System.out.println("ftp_name=" +
		 * ftpElement.attribute("name").getValue()); } // ֱ��������pathȡ��nameֵ list
		 * = doc.selectNodes("/config/skinConfig/@name"); it = list.iterator();
		 * while (it.hasNext()) { Attribute attribute = (Attribute) it.next();
		 * System.out.println("@name=" + attribute.getValue()); }
		 */
		// ֱ��ȡ��node��ֵ
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
		// ֱ��ȡ��node��ֵ
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
		// ֱ���޸�node��ֵ
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
		// ֱ���޸�node��ֵ
		// rootStr ��ʽ /config/skinConfig/
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
		// ֱ���޸�node��ֵ
		// rootStr ��ʽ /config/skinConfig/
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

	// 9���޸Ļ�ɾ��ĳ��ֵ������
	/** ftp�ڵ�ɾ��ftp-host�ڵ� */
	// ftpElement.remove(hostElement);
	/** ftp�ڵ�ɾ��name���� */
	// ftpElement.remove(nameAttribute);
	/** �޸�ftp-host��ֵ */
	// hostElement.setText("192.168.0.1");
	/** �޸�ftp�ڵ�name���Ե�ֵ */
	// nameAttribute.setValue("ChiFeng");
}
