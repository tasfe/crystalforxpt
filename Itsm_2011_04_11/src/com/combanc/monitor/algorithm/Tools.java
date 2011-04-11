package com.combanc.monitor.algorithm;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jcifs.netbios.NbtAddress;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.josql.Query;
import org.josql.QueryExecutionException;
import org.josql.QueryParseException;
import org.josql.QueryResults;

import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.borland.dx.dataset.Variant;
import com.combanc.monitor.pojo.MonitorSegment;
import com.combanc.monitor.pojo.MonitorSystemParam;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class Tools {

	public Tools() {
	}
	
	/**
	 * 比较计算两次差值
	 * @param lastRecv 上次接收字节
	 * @param lastSend 上次发送字节
	 * @param nowRecv 本次接收字节
	 * @param nowSend 本次发送字节
	 * @return long[]
	 */
	public static long[] compuData(long lastRecv, long lastSend, long nowRecv,	long nowSend) {
		//if (lastRecv >= 0 && lastSend >= 0) {
		if (lastRecv !=-1 && lastSend != -1) {
			long recvGap, sendGap;
			// 计算两次接收字节差：
			if (nowRecv >= lastRecv)
				recvGap = nowRecv - lastRecv;
			else
				// 如果此时32位计数器复位，则需要加上32位无符号整数的最大值4294967296，如果是机器
				// 重起，则无法判断，出现1天的数据错误
				recvGap = nowRecv + Long.parseLong("4294967296") - lastRecv;
			// 如果是64位计数器复位或机器重起，出现负值，则该日流量按0计算。
			if (recvGap < 0)
				recvGap = 0;

			// 计算两次发送字节差：
			if (nowSend >= lastSend)
				sendGap = nowSend - lastSend;
			else
				sendGap = nowSend + Long.parseLong("4294967296") - lastSend;
			if (sendGap < 0)
				sendGap = 0;

			// 计算两次字节总数差：
			return new long[] { sendGap + recvGap, sendGap, recvGap };
		} else {
			return new long[] { -1, -1, -1 };
		}
	}
	// 将IP地址的补齐0删除。
	public static String ipFormat(String ip) {
		String result = "";
		String tmp;
		StringTokenizer token = new StringTokenizer(ip, ".");
		while (token.hasMoreTokens()) {
			tmp = token.nextToken();
			result += Integer.parseInt(tmp) + ".";
		}
		return result.substring(0, result.lastIndexOf("."));
	}

	// 判断IP是否在子网中
	public static boolean ipInSubnet(String ipStr, String snStr, String maskStr) {
		byte[] ip = new byte[4];
		byte[] subnet = new byte[4];
		byte[] mask = new byte[4];
		int[] mid = new int[4];

		try {
			ip = InetAddress.getByName(ipStr).getAddress();
			subnet = InetAddress.getByName(snStr).getAddress();
			mask = InetAddress.getByName(maskStr).getAddress();
		} catch (UnknownHostException e) {
			return false;
		}
		;

		// 注：这里没有检查子网与掩码是否匹配
		// 检查IP是否在子网中，将IP与掩码按位与，再与子网号比较是否相等。
		for (int i = 0; i <= 3; i++)
			mid[i] = (int) ip[i] & (int) mask[i];
		if ((mid[0] == (int) subnet[0]) && (mid[1] == (int) subnet[1])
				&& (mid[2] == (int) subnet[2]) && (mid[3] == (int) subnet[3])) {
			return true;
		} else {
			return false;
		}
	}

	// 判断子网ID与掩码是否匹配。
	public static boolean subnetOK(String snStr, String maskStr) {
		int subnet, mask, oldmask;
		int index = 1;
		if (snStr == null || snStr.equals(""))
			return false;
		if (maskStr == null || maskStr.equals(""))
			return false;
		// hashCode函数获得IP地址的整数表示
		try {
			subnet = InetAddress.getByName(snStr).hashCode();
			mask = InetAddress.getByName(maskStr).hashCode();
		} catch (UnknownHostException e) {
			// 如果不能获得其地址，输入的字符串有问题。
			return false;
		}
		;
		if (mask == -1)
			return false;
		oldmask = mask;
		// 掩码的左部分为连续的1，右部分为连续的0。>>带符号移位，向右移动1位后，在左边
		// 引入一个1，所有0向右移动完毕后，变成全1，既-1。
		do {
			mask = mask >> 1;
			index = index * 2;
		} while (mask != -1);
		if (index == 2)
			return false;// 掩码最后位为254
		// 如果子网号取模等于0，表示可以整除，即其右边为零的位数与掩码相同，子网号正确。
		// 子网掩码连续带符号右移时，变成全1（-1）时，停在左侧连续1和0的中间，这时
		// 如果右边都是0，则oldmask可以整除index,否则不能，依次判断掩码是否正确及网络号和掩码是否匹配。
		if (oldmask % index == 0 && subnet % index == 0)
			return true;
		else
			return false;
	}

	// 取得子网中可用的IP数
	public static int getSubnetSize(String snMask) {
		int mask;
		int index = 1;
		// hashCode函数获得IP地址的整数表示
		try {
			mask = InetAddress.getByName(snMask).hashCode();
		} catch (UnknownHostException e) {
			// 如果不能获得其地址，输入的字符串有问题。
			return 0;
		}
		;
		// 不包括主机掩码
		if (mask == -1)
			return 0;
		// 掩码的左部分为连续的1，右部分为连续的0。>>带符号移位，向右移动1位后，在左边
		// 引入一个1，所有0向右移动完毕后，变成全1，既-1。
		do {
			mask = mask >> 1;
			index = index * 2;
		} while (mask != -1);
		// 如果子网号取模等于0，表示可以整除，即其右边为零的位数与掩码相同，子网号正确。
		// 不包括全0和全1的两个地址。
		return index - 2;
	}

	// 取得给定子网的广播地址：
	public static String getBroadcastAddr(String snet, String mask) {
		int mask3value, mask4value, netID3value, netID4value;
		String mask3str, mask4str, netID3str, netID4str;
		String netID = snet;
		String netmask = mask;
		String tmpID = netID;

		netID = netID.substring(0, netID.lastIndexOf("."));
		netID = netID.substring(0, netID.lastIndexOf("."));

		tmpID = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		tmpID = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());

		netID3str = tmpID.substring(0, tmpID.indexOf("."));
		netID3value = Integer.parseInt(netID3str);
		netID4str = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		netID4value = Integer.parseInt(netID4str);

		netmask = netmask.substring(netmask.indexOf(".") + 1, netmask.length());
		netmask = netmask.substring(netmask.indexOf(".") + 1, netmask.length());
		mask3str = netmask.substring(0, netmask.indexOf("."));
		mask3value = Integer.parseInt(mask3str);
		mask4str = netmask
				.substring(netmask.indexOf(".") + 1, netmask.length());
		mask4value = Integer.parseInt(mask4str);
		// Hosts ID not include all 1.
		return netID + "." + String.valueOf(netID3value + 255 - mask3value)
				+ "." + String.valueOf(netID4value + 255 - mask4value);
	}

	public static boolean pingSubnet(String subnet, List<MonitorSegment> monitorSegmentList) {
		if (monitorSegmentList.isEmpty())
			return false;
		// 2010-09-10 ?? 网段表是否需要跟子网表关联
		for(MonitorSegment ms : monitorSegmentList) {
			if(!pingSegment(subnet, ms.getNetworkSegment(), ms.getMask()))
					return false;
		}
		/*
		segmentDataSet.first();
		do {
			if (!segmentDataSet.getString("子网名").equals(subnet))
				continue;
			if (!pingSegment(subnet, segmentDataSet.getString("网段"),
					segmentDataSet.getString("掩码")))
				return false;
		} while (segmentDataSet.next());
		*/
		return true;
	}

	public static boolean pingSegment(String subnet, String snet, String mask) {
		int i, j;
		int mask3value, mask4value, netID3value, netID4value;
		String mask3str, mask4str, netID3str, netID4str, tmpID;
		String[] netID3array = new String[256];

		DatagramSocket sendsocket;
		DatagramPacket sendpacket;
		byte data[] = new byte[10];
		String netID, netmask, ipstr;

		netID = snet;
		netmask = mask;

		try {
			sendsocket = new DatagramSocket();
		} catch (SocketException se) {
			JOptionPane.showMessageDialog(null, "Ping Socket 打开错误。");
			System.out.println("Ping Socket 打开错误。");
			return false;
		}

		tmpID = netID;
		// Get netID's first two bytes as IP string's prefix.
		netID = netID.substring(0, netID.lastIndexOf("."));
		netID = netID.substring(0, netID.lastIndexOf("."));

		// Get netID's last two bytes.
		tmpID = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		tmpID = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		// Get netID third byte.
		netID3str = tmpID.substring(0, tmpID.indexOf("."));
		// Get netID start value of byte 3.
		netID3value = Integer.parseInt(netID3str);
		// Get netID forth bytes.
		netID4str = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		// Get netID start value of byte 4.
		netID4value = Integer.parseInt(netID4str);

		// Get netmask lasy two bytes.
		netmask = netmask.substring(netmask.indexOf(".") + 1, netmask.length());
		netmask = netmask.substring(netmask.indexOf(".") + 1, netmask.length());
		// Get netmask third byte.
		mask3str = netmask.substring(0, netmask.indexOf("."));
		// Get netmask start value of byte 3.
		mask3value = Integer.parseInt(mask3str);
		// Get netmask forth bytes.
		mask4str = netmask
				.substring(netmask.indexOf(".") + 1, netmask.length());
		// Get netmask start value of byte 4.
		mask4value = Integer.parseInt(mask4str);
		// Generate netID 3rd byte string for result ipstr construction.
		for (i = 0; i < 256 - mask3value; i++)
			netID3array[i] = String.valueOf(netID3value + i);
		// 构造数据报：
		// 使用UDP端口53，为DNS，通常防火墙不会过滤该端口。目的地址应以ICMP的Protocol unreachable来应答。
		sendpacket = new DatagramPacket(data, 2, null, 53);

		// Generate IP string of specified Network ID and Network Mask.
		// Byte 3 start index 0, end 256-mask3value, offset netID3value.
		// Byte 4 start index 0, end 256-mask4value, offset netID4value.
		for (i = 0; i < 256 - mask3value; i++) {
			for (j = 0; j < 256 - mask4value; j++) {
				if (i == 0 && j == 0)
					continue;// Hosts ID not include all 0.
				if (i == 255 - mask3value && j == 255 - mask4value)
					continue;// Hosts ID not include all 1.
				// Construct IP string: IP first 2 bytes + 3rd byte + 4th
				// byte.
				ipstr = netID + "." + netID3array[i] + "."
						+ String.valueOf(netID4value + j);
				System.out.println("Ping子网 " + subnet + " 网段 " + snet + " / "
						+ mask + " ，IP：" + ipstr);
				try {
					sendpacket.setAddress(InetAddress.getByName(ipstr));
				} catch (UnknownHostException ex) {
					// 注释2010
					// if (!MainFrame.ignoreError) {
					// JOptionPane.showMessageDialog(null, "Ping " + ipstr
					// + " 错误：" + ex);
					// display("Ping " + ipstr + " 错误：" + ex);
					// return false;
					// }
				}
				try {
					sendsocket.send(sendpacket);
				} catch (IOException io) {
					// 注释2010
					// if (!MainFrame.ignoreError) {
					// JOptionPane.showMessageDialog(null, "Ping " + ipstr
					// + " 错误：" + io);
					// display("Ping " + ipstr + " 错误：" + io);
					// return false;
					// }
				}
				// Delay between two packets.
				// 注释2010
				// if (MainFrame.pingGap > 0)
				// try {
				// // 10、15、20ms测试表明，15ms较理想，可以让路由器学到IP及其MAC。30ms更保险。
				// Thread.sleep(MainFrame.pingGap);
				// } catch (InterruptedException e) {
				// }
				// ;
			}// for j
		}// for i
		sendsocket.close();
		return true;
	}// pingSubnet
	
	public static boolean pingSegment(List<MonitorSegment> monitorSegmentList) {
		if (monitorSegmentList.isEmpty())
			return false;
		// 2010-09-10 ?? 网段表是否需要跟子网表关联
		for(MonitorSegment ms : monitorSegmentList) {
			if(!pingSegment( ms.getNetworkSegment(), ms.getMask()))
					return false;
		}
		/*
		segmentDataSet.first();
		do {
			if (!segmentDataSet.getString("子网名").equals(subnet))
				continue;
			if (!pingSegment(subnet, segmentDataSet.getString("网段"),
					segmentDataSet.getString("掩码")))
				return false;
		} while (segmentDataSet.next());
		*/
		return true;
	}
	
	public static boolean pingSegment( String snet, String mask) {
		int i, j;
		int mask3value, mask4value, netID3value, netID4value;
		String mask3str, mask4str, netID3str, netID4str, tmpID;
		String[] netID3array = new String[256];

		DatagramSocket sendsocket;
		DatagramPacket sendpacket;
		byte data[] = new byte[10];
		String netID, netmask, ipstr;

		netID = snet;
		netmask = mask;

		try {
			sendsocket = new DatagramSocket();
		} catch (SocketException se) {
			JOptionPane.showMessageDialog(null, "Ping Socket 打开错误。");
			System.out.println("Ping Socket 打开错误。");
			return false;
		}

		tmpID = netID;
		// Get netID's first two bytes as IP string's prefix.
		netID = netID.substring(0, netID.lastIndexOf("."));
		netID = netID.substring(0, netID.lastIndexOf("."));

		// Get netID's last two bytes.
		tmpID = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		tmpID = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		// Get netID third byte.
		netID3str = tmpID.substring(0, tmpID.indexOf("."));
		// Get netID start value of byte 3.
		netID3value = Integer.parseInt(netID3str);
		// Get netID forth bytes.
		netID4str = tmpID.substring(tmpID.indexOf(".") + 1, tmpID.length());
		// Get netID start value of byte 4.
		netID4value = Integer.parseInt(netID4str);

		// Get netmask lasy two bytes.
		netmask = netmask.substring(netmask.indexOf(".") + 1, netmask.length());
		netmask = netmask.substring(netmask.indexOf(".") + 1, netmask.length());
		// Get netmask third byte.
		mask3str = netmask.substring(0, netmask.indexOf("."));
		// Get netmask start value of byte 3.
		mask3value = Integer.parseInt(mask3str);
		// Get netmask forth bytes.
		mask4str = netmask
				.substring(netmask.indexOf(".") + 1, netmask.length());
		// Get netmask start value of byte 4.
		mask4value = Integer.parseInt(mask4str);
		// Generate netID 3rd byte string for result ipstr construction.
		for (i = 0; i < 256 - mask3value; i++)
			netID3array[i] = String.valueOf(netID3value + i);
		// 构造数据报：
		// 使用UDP端口53，为DNS，通常防火墙不会过滤该端口。目的地址应以ICMP的Protocol unreachable来应答。
		sendpacket = new DatagramPacket(data, 2, null, 53);

		// Generate IP string of specified Network ID and Network Mask.
		// Byte 3 start index 0, end 256-mask3value, offset netID3value.
		// Byte 4 start index 0, end 256-mask4value, offset netID4value.
		for (i = 0; i < 256 - mask3value; i++) {
			for (j = 0; j < 256 - mask4value; j++) {
				if (i == 0 && j == 0)
					continue;// Hosts ID not include all 0.
				if (i == 255 - mask3value && j == 255 - mask4value)
					continue;// Hosts ID not include all 1.
				// Construct IP string: IP first 2 bytes + 3rd byte + 4th
				// byte.
				ipstr = netID + "." + netID3array[i] + "."
						+ String.valueOf(netID4value + j);
				System.out.println("Ping 网段 " + snet + " / "
						+ mask + " ，IP：" + ipstr);
				try {
					sendpacket.setAddress(InetAddress.getByName(ipstr));
				} catch (UnknownHostException ex) {
					// 注释2010
					// if (!MainFrame.ignoreError) {
					// JOptionPane.showMessageDialog(null, "Ping " + ipstr
					// + " 错误：" + ex);
					// display("Ping " + ipstr + " 错误：" + ex);
					// return false;
					// }
				}
				try {
					sendsocket.send(sendpacket);
				} catch (IOException io) {
					// 注释2010
					// if (!MainFrame.ignoreError) {
					// JOptionPane.showMessageDialog(null, "Ping " + ipstr
					// + " 错误：" + io);
					// display("Ping " + ipstr + " 错误：" + io);
					// return false;
					// }
				}
				// Delay between two packets.
				// 注释2010
				// if (MainFrame.pingGap > 0)
				// try {
				// // 10、15、20ms测试表明，15ms较理想，可以让路由器学到IP及其MAC。30ms更保险。
				// Thread.sleep(MainFrame.pingGap);
				// } catch (InterruptedException e) {
				// }
				// ;
			}// for j
		}// for i
		sendsocket.close();
		return true;
	}// pingSubnet

	// lsj 2010-03-15
	public static String decodeISO8859(String str) {
		try {
			// 中文不可用
			// str = new String(str.getBytes("ISO8859-1"),"UTF-8");
			// 中文可用
			// str = new String(str.getBytes("ISO8859-1"),"GBK");
			// 中文可用
			// str = new String(str.getBytes("ISO8859-1"),"GB2312");
			str = new String(str.getBytes("ISO8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return str;
	}

	public static String decodeGBK(String str) {
		String result = new String();
		byte[] bytes = new byte[256];
		int i = 0, j = 0, len = 0;
		// 一个完整的UNICODE编码需要两个字节，加上中间空格需要5个字符。
		if (str.length() < 5)
			return str;
		if (str.length() > 768)
			return str;

		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ')
				continue;
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
				continue;
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'f')
				continue;
			break;
		}

		if (i == str.length()) {
			for (i = 0; i <= str.length() - 2; i++) {
				// 如果无法解析，则返回原值
				try {
					bytes[j] = (byte) Integer.parseInt(str.substring(i, i + 2),
							16);
				} catch (NumberFormatException e) {
					return str;
				}
				// 汉字的高字节和低字节都为负，以此来区别汉字和非汉字。一个汉字的字节算半个字符
				if (bytes[j] < 0) {
					len++;
				} else {
					len += 2;
				}
				;
				i += 2;
				j++;
			}

			java.io.ByteArrayInputStream bin = new java.io.ByteArrayInputStream(
					bytes);
			try {
				java.io.BufferedReader reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(bin, "GBK"));
				result = reader.readLine();
			} catch (Exception e) {
				System.err.println(e);
			}
			if (result.length() >= len / 2)
				return result.substring(0, len / 2);
			else
				return result;
		} else {
			return str;
		}
	}

	// 检查MAC地址格式是否正确
	public static boolean macFormatOK(String str) {
		if (str == null || str.equals("") || str.length() != 17)
			return false;

		int[] index1 = new int[] { 0, 1, 3, 4, 6, 7, 9, 10, 12, 13, 15, 16 };
		int[] index2 = new int[] { 2, 5, 8, 11, 14 };
		int i;

		for (i = 0; i < index1.length; i++) {
			if (str.charAt(index1[i]) >= '0' && str.charAt(index1[i]) <= '9')
				continue;
			if (str.charAt(index1[i]) >= 'a' && str.charAt(index1[i]) <= 'f')
				continue;
			return false;
		}

		for (i = 0; i < index2.length; i++)
			if (str.charAt(index2[i]) != ' ')
				return false;

		if (str.equals("00 00 00 00 00 00"))
			return false;

		return true;
	}

	// 判断是否是数字
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	// 将AVAYA880 001122334455格式的MAC地址增加空格：
	public static String avayaMacFormat(String str) {
		if (str == null || str.equals("") || str.length() != 12)
			return null;
		int i;

		String result = str.substring(0, 2);

		for (i = 1; i < 6; i++) {
			result += " ";
			result += str.substring(i * 2, i * 2 + 2);
		}

		return result;
	}

	// 判断Mac地址的合法性,yyp 09.11.28
	public static boolean isMac(String sMac) {
		if (sMac == null || sMac.length() == 0)
			return false;
		// 三种格式：00-00-00-00-00-00或00 00 00 00 00 00或000000000000
		Pattern macPattern = Pattern
				.compile("([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})( [0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})([0-9A-Fa-f]{2}){5}");
		Matcher macMatcher = macPattern.matcher(sMac);
		return macMatcher.matches();
	}

	// 将三种Mac地址统一成00 00 00 00 00 00格式,yyp 09.11.28
	public static String transformMacFormat(String sMac) {
		String res = "";
		if (sMac.length() == 12) {
			res = Tools.avayaMacFormat(sMac);
		} else {
			res = sMac.replace('-', ' ').toUpperCase();
		}
		return res;
	}

	// 将00 00 00 00 00 00转换为0000.0000.0000
	public static String transMacFormatToPoint(String sMac) {
		String res = transformMacFormat(sMac).toLowerCase();
		String[] tmp = res.split(" ");
		if (tmp.length == 6) {
			res = tmp[0] + tmp[1] + "." + tmp[2] + tmp[3] + "." + tmp[4]
					+ tmp[5];
		}
		return res;
	}

	// 将00 00 00 00 00 00转换为0000-0000-0000
	public static String transMacFormatToBar(String sMac) {
		String res = transformMacFormat(sMac).toLowerCase();
		String[] tmp = res.split(" ");
		if (tmp.length == 6) {
			res = tmp[0] + tmp[1] + "-" + tmp[2] + tmp[3] + "-" + tmp[4]
					+ tmp[5];
		}
		return res;
	}

	/*
	 * // 检查IP地址格式是否正确 public static boolean ipFormatOK(String str) { if (str ==
	 * null || str.equals("")) return false; try { InetAddress.getByName(str); }
	 * catch (UnknownHostException e) { return false; } return true; }
	 */

	/**
	 *  检验IP格式是否正确（支持IPV4和IPV6）
	 */
	public static boolean ipFormatOK(String address) {
		if (address == null || address.length() == 0)
			return false;
		// IPV4正则表达式
		String regex0 = "(2[0-4]\\d)" + "|(25[0-5])";
		String regex1 = "1\\d{2}";
		String regex2 = "[1-9]\\d";
		String regex3 = "\\d";
		String regex = "(" + regex0 + ")|(" + regex1 + ")|(" + regex2 + ")|("
				+ regex3 + ")";
		regex = "(" + regex + ").(" + regex + ").(" + regex + ").(" + regex
				+ ")";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(address);
		if (m.matches()) { // 判断是否IPV4格式
			return true;
		} else { // 判断是否IPV6格式
			// IPV6正则表达式
			boolean result = false;
			String regHex = "(\\p{XDigit}{1,4})";
			String regIPv6Full = "^(" + regHex + ":){7}" + regHex + "$";
			String regIPv6AbWithColon = "^(" + regHex + "(:|::)){0,6}" + regHex
					+ "$";
			String regIPv6AbStartWithDoubleColon = "^(" + "::(" + regHex
					+ ":){0,5}" + regHex + ")$";
			String regIPv6 = "^(" + regIPv6Full + ")|("
					+ regIPv6AbStartWithDoubleColon + ")|("
					+ regIPv6AbWithColon + ")$";
			// System.out.println("regIPv6 =" + regIPv6);
			if (address.indexOf(":") != -1) {
				if (address.length() <= 39) {
					String addressTemp = address;
					int doubleColon = 0;
					while (addressTemp.indexOf("::") != -1) {
						addressTemp = addressTemp.substring(addressTemp
								.indexOf("::") + 2, addressTemp.length());
						doubleColon++;
					}
					if (doubleColon <= 1) {
						result = address.matches(regIPv6);
					}
				}
			}
			return result;
		}
	}
	/**
	 *  过滤掉IP不合法和没有端口
	 * @param list
	 */
	public static boolean filter(String strIp,String strInterface){
		 if(null==strIp||"".equals(strIp)||!Tools.ipFormatOK(strIp)||null==strInterface||"".equals(strInterface))
			 return false;
		 else
			 return true;
	}
	// 取得字符串表示的IP地址的长整数值
	public static long getIpLong(String ip) {
		if (ipFormatOK(ip) && (!(ip.indexOf(":") > 0))) {
			StringTokenizer token = new StringTokenizer(ip, ".");
			long ret = 0;
			while (token.hasMoreTokens())
				ret = ret * 256 + Long.parseLong(token.nextToken());
			return ret;
		}
		return -1;
	}

	// IP地址转换成十进制整数
	public static long ipToLong(String strIp) {
		if (ipFormatOK(strIp) && (!(strIp.indexOf(":") > 0))) {
			long[] ip = new long[4];
			// 先找到IP地址字符串中.的位置
			int position1 = strIp.indexOf(".");
			int position2 = strIp.indexOf(".", position1 + 1);
			int position3 = strIp.indexOf(".", position2 + 1);
			// 将每个.之间的字符串转换成整型
			try {
				ip[0] = Long.parseLong(strIp.substring(0, position1));
				ip[1] = Long.parseLong(strIp
						.substring(position1 + 1, position2));
				ip[2] = Long.parseLong(strIp
						.substring(position2 + 1, position3));
				ip[3] = Long.parseLong(strIp.substring(position3 + 1));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return -1;
			}
			return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
		}
		return -1;
	}

	// 将十进制整数形式转换成127.0.0.1形式的ip地址
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	// 检查端口或接口格式是否正确
	public static boolean portFormatOK(String str) {
		if (str == null || str.equals(""))
			return false;
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	// 实验中发现ADVENT SNMP API有BUG：千分之一的概率下MAC地址不以字符串而以字节形式
	// 返回，如：RTL)!x，而实际的字符串形式的MAC应为：52 54 4c 29 21 58
	public static String wrongBytesToStr(String str) {
		byte[] mac = new byte[6];
		String macStr = "";
		String tempStr;

		mac = str.getBytes();
		/**
		 * Return value is a byte array, convert it to hex style string, such as
		 * "00 10 dc 04 f7 61".
		 */
		for (int i = 0; i < 6; i++) {
			tempStr = Integer.toHexString(mac[i]);
			/**
			 * If value of current byte > 0, length of return string of
			 * toHexString is not more than 2, if the length is 1, must add "0"
			 * before the return string.
			 */
			if (mac[i] >= 0) {
				if (tempStr.length() < 2)
					tempStr = "0".concat(tempStr);
			}
			/**
			 * If value of current byte < 0, length of return string of
			 * toHexString is 8, the first 6 char is 'f', the last 2 char is the
			 * correct result.
			 */
			else {
				tempStr = tempStr.substring(6);
			}
			macStr = macStr.concat(tempStr).concat(" ");
		}// for
		// Not include the last char ' ' of string.
		macStr = macStr.substring(0, 17);
		return macStr;
	}

	// 时间类型：xx days, yy hours, ..转化为中文，只保留天和小时
	public static String timeToChinese(String str) {
		String ret = "";
		int dIndex = str.indexOf("days");
		if (dIndex != -1) {
			ret = ret + str.substring(0, dIndex - 1) + "天 ";
			str = str.substring(dIndex + 6);
		} else {
			dIndex = str.indexOf("day");
			if (dIndex != -1) {
				ret = "1天 ";
				str = str.substring(dIndex + 5);
			}
		}

		int hIndex = str.indexOf("hours");
		if (hIndex != -1) {
			ret = ret + str.substring(0, hIndex - 1) + "小时";
			str = str.substring(hIndex + 7);
		} else {
			hIndex = str.indexOf("hour");
			if (hIndex != -1) {
				ret = ret + "1小时";
				str = str.substring(hIndex + 6);
			}
		}

		if (dIndex == -1 && hIndex == -1)
			ret = "< 1小时";

		return ret;
	}

	// 删除schema文件
	public static void delSchemaFile(String fname) {
		int index = fname.lastIndexOf(".txt");
		if (index == -1)
			return;
		String sname = fname.substring(0, index) + ".SCHEMA";
		File f = new File(sname);
		if (f.exists())
			f.delete();
	}

	public static void exportTxtFile(JPanel jPanel,
			TableDataSet currentDataSet, String fileName) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("导出视图");
		chooser.setApproveButtonText("确认");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setSelectedFile(new File(fileName));
		int retVal = chooser.showOpenDialog(jPanel);
		if (retVal != JFileChooser.APPROVE_OPTION)
			return;
		String fname = chooser.getSelectedFile().toString();
		// fname.replaceFirst("接入档案表.txt", "接入档案表.xls");
		File f = new File(fname);
		if (f.exists()) {
			int confirm = JOptionPane.showConfirmDialog(jPanel, "文件已存在，是否导出？",
					"导出文件", JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION)
				return;
		}

		// 可见列计数
		int colIndex = 0;
		for (int i = 0; i < currentDataSet.getColumnCount(); i++) {
			if (currentDataSet.getColumn(i).getVisible() == 0)
				continue;
			colIndex++;
		}

		// 构造导出列
		Column[] exportCol = new Column[colIndex];
		colIndex = 0;
		for (int i = 0; i < currentDataSet.getColumnCount(); i++) {
			if (currentDataSet.getColumn(i).getVisible() == 0)
				continue;// 不包括不可见列
			exportCol[colIndex] = new Column();
			exportCol[colIndex].setColumnName(currentDataSet.getColumn(i)
					.getColumnName());
			exportCol[colIndex]
					.setDataType(com.borland.dx.dataset.Variant.STRING);
			colIndex++;
		}
		// 构造导出DATASET
		TableDataSet epDataSet = new TableDataSet();
		epDataSet.setColumns(exportCol);
		epDataSet.open();

		DataRow row = new DataRow(epDataSet);
		// 构造标题行
		for (int i = 0; i < colIndex; i++)
			row.setString(i, exportCol[i].getColumnName());
		epDataSet.addRow(row);

		currentDataSet.enableDataSetEvents(false);
		// 复制各行
		currentDataSet.first();
		String colName = null;
		do {
			for (int i = 0; i < currentDataSet.getColumnCount(); i++) {
				if (currentDataSet.getColumn(i).getVisible() == 0)
					continue;// 跳过不可见列
				colName = currentDataSet.getColumn(i).getColumnName();
				if (currentDataSet.getColumn(i).getDataType() == Variant.STRING) {
					String tmpStr = currentDataSet.getString(colName);
					if (colName.equals("描述"))
						tmpStr = tmpStr.replaceAll("\r\n", " ").replaceAll(
								"\r", " ").replaceAll("\n", " ");
					row.setString(colName, tmpStr);
				} else if (currentDataSet.getColumn(i).getDataType() == Variant.LONG)
					row.setString(colName, String.valueOf(currentDataSet
							.getLong(colName)));
				else if (currentDataSet.getColumn(i).getDataType() == Variant.BOOLEAN)
					row.setString(colName, String.valueOf(currentDataSet
							.getBoolean(colName)));
				else if (currentDataSet.getColumn(i).getDataType() == Variant.TIMESTAMP)
					row.setString(colName, new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").format(currentDataSet
							.getTimestamp(colName)));
				// row.setString(colName,
				// currentDataSet.getTimestamp(colName).toLocaleString());
			}
			epDataSet.addRow(row);
		} while (currentDataSet.next());
		currentDataSet.enableDataSetEvents(true);
		// 导出文件
		try {
			if (f.getAbsoluteFile().toString().indexOf("接入档案表") > 0) {
				System.out.println(f.getAbsolutePath());
				FileOutputStream fos = new FileOutputStream(f);

				// 建立excel表格
				WritableWorkbook wbook = Workbook.createWorkbook(fos);
				// 工作表名称
				WritableSheet wsheet = wbook.createSheet("入网计算机表", 0);

				// 设置字体
				WritableFont wfont = new WritableFont(WritableFont.ARIAL,
						14,
						// WritableFont.NO_BOLD, false,
						WritableFont.BOLD, false,
						jxl.format.UnderlineStyle.NO_UNDERLINE,
						jxl.format.Colour.RED);
				WritableCellFormat titleFormat = new WritableCellFormat(wfont);
				// 设置表头
				String[] title = { "IP", "MAC", "上连设备", "端口", "接口", "接口描述",
						"计算机名", "域或组", "登录名", "最近发现时间", "使用人", "地点", "部门",
						"备注1", "备注2" };
				int[] cellLen = { 15, 18, 15, 8, 8, 20, 14, 10, 10, 20, 10, 10,
						12, 20, 20 };
				for (int i = 0; i < title.length; i++) {
					Label excelTitle = new Label(i, 0, title[i], titleFormat);
					wsheet.addCell(excelTitle);
					wsheet.setColumnView(i, cellLen[i]);
				}
				// excel表格行号
				int num = 1;
				if (epDataSet.isEmpty())
					return;
				epDataSet.first();
				epDataSet.next();
				Label[] content = new Label[title.length];
				do {
					for (int i = 0; i < title.length; i++) {
						content[i] = new Label(i, num, epDataSet
								.getString(title[i]));
						wsheet.addCell(content[i]);
					}
					num++;
				} while (epDataSet.next());
				/*
				 * SheetSettings settings = new SheetSettings(wsheet);
				 * //默认值0代表不冻结窗口，但是设置了冻结第1行和第1列却不起作用
				 * settings.setHorizontalFreeze(1);
				 * settings.setVerticalFreeze(1);
				 */

				// 写入excel
				wbook.write();
				wbook.close();
				fos.close();
			} else {
				// 按照txt格式导出
				TextDataFile tmp = new TextDataFile();
				tmp.setFileName(fname);
				tmp.setDelimiter(""); // 字符串边界为空，缺省为双引号
				tmp.setSeparator("\t"); // 字段分隔符为TAB
				tmp.save(epDataSet);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(jPanel, "导出错误：" + ex);
			return;
		}
		Tools.delSchemaFile(fname);
		JOptionPane.showMessageDialog(jPanel, "表格导出成功！");
	}

	public static String selectJpgFile(JPanel jPanel, String type,
			String filePath) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(type + "拓扑背景图选择");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retVal = chooser.showOpenDialog(jPanel);
		File file = chooser.getSelectedFile();
		if (retVal == JFileChooser.APPROVE_OPTION) {
			if (file.exists()) {
				String tmpName = file.getName().toLowerCase();
				if (!(tmpName.endsWith(".jpg") || tmpName.endsWith(".jpeg") || tmpName
						.endsWith("png"))) {
					JOptionPane.showMessageDialog(chooser,
							"请选择jpg，jpeg或者png格式图片");
					return filePath;
				}
				// 分辨率为1024*768
				/*
				 * ImageIcon tmpIcon = new ImageIcon(file.getAbsolutePath()); if
				 * (! (tmpIcon.getIconWidth() == 1024 && tmpIcon.getIconHeight()
				 * == 768) && ! (tmpIcon.getIconWidth() == 800 &&
				 * tmpIcon.getIconHeight() == 640)) {
				 * JOptionPane.showMessageDialog(chooser,
				 * "请选择分辨率为1024*768/800*640的jpg，jpeg或者png格式图片"); return
				 * filePath; }
				 */
				return file.getAbsolutePath();
			}
		}
		return filePath;
	}

	public static String selectFile(JPanel jPanel, String title, String filePath) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(title + "选择");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retVal = chooser.showOpenDialog(jPanel);
		File file = chooser.getSelectedFile();
		if (retVal == JFileChooser.APPROVE_OPTION) {
			if (file.exists()) {
				// if (title.equals("自定义工具"))
				// 修改文件格式，因为对于C:\Program Files\JBSKB.exe这种格式中存在空格，
				// 需要修改为C:\"Program Files\JBSKB.exe"的格式
				String tmpPath = file.getAbsolutePath();
				int index = tmpPath.indexOf(":\\");
				if (index > 0)
					tmpPath = tmpPath.substring(0, index + 2) + "\""
							+ tmpPath.substring(index + 2) + "\"" + " ";
				return tmpPath;
			}
		}
		return filePath;
	}

	// FDB转发表返回的端口号为空格分割的4字节的16进制字符串，转化为10进制整数字符串。
	public static String getGWPort1(String port) {
		String result = "";
		StringTokenizer token = new StringTokenizer(port, " ");
		while (token.hasMoreTokens())
			result += token.nextToken();

		// System.out.println(port + "=======" +
		// String.valueOf(Integer.parseInt(result,16)));
		return String.valueOf(Integer.parseInt(result, 16));
	}

	// FDB转发表返回的端口号为空格分割的4字节的16进制字符串，根据字节顺序和字节的二进制置1位
	// 的情况来生成端口号。
	public static String getGWPort2(String port) {
		String[] result = new String[] { "x", "x", "x", "x", "x", "x", "x", "x" };// 初始化
		String pt = "";
		int base = 0, sub = 0;// 字节基数和字节置位号
		// 取得所有字节
		StringTokenizer token = new StringTokenizer(port, " ");
		int i = 0;
		while (token.hasMoreTokens()) {
			result[i] = token.nextToken();
			i++;
		}
		// 字节中只有一个字节非0，为端口置1位字节，设置不同的字节基数。目前只考虑8个字节
		if (!result[0].equals("x") && !result[0].equals("00")) {
			base = 0;
			pt = result[0];
		}
		if (!result[1].equals("x") && !result[1].equals("00")) {
			base = 8;
			pt = result[1];
		}
		if (!result[2].equals("x") && !result[2].equals("00")) {
			base = 16;
			pt = result[2];
		}
		if (!result[3].equals("x") && !result[3].equals("00")) {
			base = 24;
			pt = result[3];
		}
		if (!result[4].equals("x") && !result[4].equals("00")) {
			base = 32;
			pt = result[4];
		}
		if (!result[5].equals("x") && !result[5].equals("00")) {
			base = 40;
			pt = result[5];
		}
		if (!result[6].equals("x") && !result[6].equals("00")) {
			base = 48;
			pt = result[6];
		}
		if (!result[7].equals("x") && !result[7].equals("00")) {
			base = 56;
			pt = result[7];
		}
		// 查找表：将置位字节映射成相应的序号。
		String[] subPort = new String[9];
		subPort[0] = "";
		subPort[1] = "01";
		subPort[2] = "02";
		subPort[3] = "04";
		subPort[4] = "08";
		subPort[5] = "10";
		subPort[6] = "20";
		subPort[7] = "40";
		subPort[8] = "80";

		for (i = 1; i < 9; i++)
			if (subPort[i].equals(pt))
				sub = i;
		// 字节基数+字节置位序号为端口号。
		return String.valueOf(base + sub);
		// 例：返回端口字符串为00 00 20 00。每字节对应的端口组从小到大排列，第一字节对应端口组1-8，
		// 第二字节对应端口组9-16，第三字节对应组17-24，第四字节对应端口组25-32。例子端口位于17-24
		// 组，20字节的2进制00100000，置1位在高位字节，是第6位，那么实际端口为16+6=22。
	}

	// 设置窗口的显示位置(以屏幕为中心)和大小
	public static void dispWin(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getPreferredSize();
		// 确保窗口大小不超过屏幕大小：
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setSize(frameSize);
		frame.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		// frame.show();
		frame.setVisible(true);
	}

	// 设置对话框的显示位置(以屏幕为中心)和大小
	public static void dispWin(JDialog dialog) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = dialog.getPreferredSize();
		dialog.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	// 取得CISCO CDP-MIB返回的16进制四字节空格分割的IP地址转化为标准格式
	public static String getCiscoIp(String cstr) {
		StringTokenizer token = new StringTokenizer(cstr, " ");// 以空格分隔
		String tmp = null, ip = "";
		while (token.hasMoreTokens()) {
			// 依次取出第一行的各列：
			tmp = token.nextToken();
			ip = ip + Integer.parseInt(tmp, 16) + ".";
		}
		return ip.substring(0, ip.length() - 1);
	}

	// 将读取Q-BRIDGE-MIB中PORT参数返回的OID中的MAC地址解析出来。
	// OID返回格式：dot1qTpFdbPort.2.0.19.212.67.6.57，解析结果00 13 d4 43 06 39
	public static String getQBMac(String cstr) {
		String[] mac = new String[6];
		String tmp = "";

		for (int i = 5; i >= 0; i--) {
			mac[i] = cstr.substring(cstr.lastIndexOf(".") + 1);// 取最后一个.之后的字符串
			mac[i] = Integer.toHexString(Integer.parseInt(mac[i]));// 转换成16进制的字符串
			if (mac[i].length() == 1)
				mac[i] = "0" + mac[i];// 补齐0
			cstr = cstr.substring(0, cstr.lastIndexOf("."));// 从后向前分断字符串
		}

		for (int i = 0; i < 5; i++)
			tmp += mac[i] + " ";

		return tmp += mac[5];
	}

	public static String cutDescr(String str) {
		int index;
		// 去掉CISCO描述中的IOS部分：Cisco之后到(tm) 之间。
		if (str.startsWith("Cisco"))
			if ((index = str.indexOf("IOS (tm) ")) != -1)
				return "Cisco " + str.substring(index + 9);
		// 去掉HUAWEI描述中的VRP部分
		if (str.startsWith("Huawei"))
			if ((index = str.indexOf("Versatile Routing Platform Software")) != -1)
				return "Huawei " + str.substring(index + 36);
		// 其它
		return str;
	}

	// 参数格式：ip@port
	public static String tcpPing(String ipPort) {
		String target = ipPort.substring(0, ipPort.indexOf("@"));
		int port = Integer.parseInt(ipPort.substring(ipPort.indexOf("@") + 1));
		try {
			Socket sock = new Socket(target, port);
			if (sock != null) {
				sock.close();
				return "正常";
			} else
				return "超时";
		} catch (java.io.IOException e) {
			return "超时";
		}
	}

	// 提供long类型带宽数据的文本显示，以BPS为单位，提供K、M值。每K为1000，不是1024
	public static String getBandText(long bw) {
		if (bw < 1000)
			return String.valueOf(bw);
		double ds = bw;
		ds = ds / 1000;
		if (ds < 1000)
			return cutEnd0(ds) + "K";// 准确到小数点后一位
		ds = ds / 1000;
		if (ds < 1000)
			return cutEnd0(ds) + "M";// 准确到小数点后一位
		ds = ds / 1000;
		return cutEnd0(ds) + "G";// 准确到小数点后一位
	}
	
	// 对量规GAUGE类型的接口速率做转换，使显示易读
	public static String getIfSpeed(String str) {
		long ifspd = 0;
		try {
			ifspd = Long.parseLong(str);
		} catch (NumberFormatException e) {
			return "-";
		}
		if (ifspd <= 0)
			return "0bps";
		if (ifspd < 1000)
			return ifspd + "bps";
		ifspd = (ifspd + 500) / 1000;// 四舍五入
		if (ifspd < 1000)
			return ifspd + "Kbps";
		ifspd = (ifspd + 500) / 1000;// 四舍五入，否则低速广域网线路可能出现较大误差如1.544M变成1M而不是2M。
		return ifspd + "Mbps";
	}

	// 将1、K、M、G的字符串转换为long类型
	public static long getBandLong(String text) {
		long result = 0;
		if (text.indexOf("K") > 0)
			try {
				result = Long.parseLong(text.substring(0, text.length() - 1)) * 1024;
			} catch (NumberFormatException ex) {
				result = 0;
			}
		else if (text.indexOf("M") > 0)
			try {
				result = Long.parseLong(text.substring(0, text.length() - 1)) * 1024 * 1024;
			} catch (NumberFormatException ex) {
				result = 0;
			}
		else if (text.indexOf("G") > 0)
			try {
				result = Long.parseLong(text.substring(0, text.length() - 1)) * 1024 * 1024 * 1024;
			} catch (NumberFormatException ex) {
				result = 0;
			}
		else {
			try {
				result = Long.parseLong(text.substring(0, text.length() - 1));
			} catch (NumberFormatException ex) {
				result = 0;
			}
		}
		return result;
	}

	// 2009-7-25修改，从private改为public
	// 小数点后为0的数据去掉小数点和0，优化显示
	public static String cutEnd0(double d) {
		java.text.DecimalFormat formatter = new java.text.DecimalFormat("#0.0");// 带宽数据显示精度
		String tmp = formatter.format(d);
		if (tmp.substring(tmp.lastIndexOf(".") + 1).equals("0"))
			return tmp.substring(0, tmp.lastIndexOf("."));
		else
			return tmp;
	}

	public static void fcSetup(JComponent fc) {
		String tmp;
		for (int i = 0; i < fc.getComponentCount(); i++) {
			if (JLabel.class.isInstance(fc.getComponents()[i])) {
				tmp = ((JLabel) fc.getComponents()[i]).getText();
				if (tmp.indexOf("文件名") != -1)
					fc.getComponents()[i].setEnabled(false);
				if (tmp.indexOf("文件类型") != -1)
					fc.getComponents()[i].setEnabled(false);
			}
			if (JTextField.class.isInstance(fc.getComponents()[i]))
				fc.getComponents()[i].setEnabled(false);
			if (JComponent.class.isInstance(fc.getComponents()[i]))
				fcSetup((JComponent) fc.getComponents()[i]);
		}
	}

	public static boolean combineFiles(String[] files, String sourcePath,
			String targetPath) {
		if (sourcePath.equals(targetPath)) {
			return false;
		}
		for (int i = 0; i < files.length; i++) {
			combineFile(files[i], sourcePath, targetPath);
		}
		return true;
	}

	public static boolean combineFile(String fileName, String sourcePath,
			String targetPath) {
		try {
			// System.out.println("合并文件---" + fileName);
			File fileSource = new File(sourcePath + fileName);
			File fileTarget = new File(targetPath + fileName);
			FileInputStream ins = new FileInputStream(fileSource);
			// 设置为true，文件追加
			FileOutputStream outs = new FileOutputStream(fileTarget, true);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = ins.read(bytes)) != -1) {
				// System.out.println("combining....");
				// System.out.println(bytes.toString());
				outs.write(bytes, 0, c);
				if (fileName.equals("设备表.txt")) {
					// System.out.println(bytes.toString());
				}
			}
			ins.close();
			outs.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "配置文件合并错误：" + ex);
			return false;
		}
		return true;
	}

	// addCode 2009-03-18 end

	// addCode 2009-04-08
	// 强制扩展Column宽度
	public static Column[] widen(Column cols[], double scale) {
		if (cols.length <= 0 || scale <= 0)
			return null;
		for (int i = 0; i < cols.length; i++) {
			// boolean类型不做处理
			if (cols[i].getDataType() != com.borland.dx.dataset.Variant.BOOLEAN) {
				// 由于double的精度问题，如果scaled之后和原先一样，则强制加宽1
				if ((int) (cols[i].getWidth() * scale) == (int) (cols[i]
						.getWidth()))
					cols[i].setWidth((int) (cols[i].getWidth() + 1));
				else
					cols[i].setWidth((int) (cols[i].getWidth() * scale));
			}
		}
		return cols;
	}

	public static boolean CheckJpcap() {
		try {
			Class.forName("jpcap.JpcapCaptor");
			NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			if (devices.length == 0) {
				JOptionPane.showMessageDialog(null,
						"没有找到可用网卡.\n你可能需要管理员权限或需要安装WinPcap软件包.");
				return false;
			} else {
				return true;
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "找不到Jpcap包. 请安装Jpcap.");
			return false;
		} catch (UnsatisfiedLinkError e) {
			JOptionPane.showMessageDialog(null,
					"找不到libpcap/WinPcap.\n 请确认是否安装了libpcap/WinPcap.");
			return false;
		}
	}

	// 取节点的CPU和响应/运行时间，并将数据保存到节点表的相应位置。
	public static String getCpuRunTime(SnmpTarget target, String ip,
			String commStr, String cpuLoadOid) {
		// 首先读取系统运行时间并获得响应时间
		target.setTimeout(1);
		target.setRetries(0);
		target.setTargetHost(ip);
		target.setCommunity(commStr);
		target.setObjectID(".1.3.6.1.2.1.1.3.0");// mib-2.system.sysUpTime
		long timeUsed = System.currentTimeMillis();
		String result = target.snmpGet();
		timeUsed = System.currentTimeMillis() - timeUsed;

		// 然后读取CPULOAD
		target.setObjectID(cpuLoadOid);
		// 取OID的最后一位
		String last = cpuLoadOid.substring(cpuLoadOid.lastIndexOf(".") + 1);
		if (last.equals("0")) // CPUOID为叶节点，如CISCO，使用GET方法
			result = target.snmpGet();
		// Harbor
		else if (cpuLoadOid.equals(".1.3.6.1.4.1.8212.1.1.4.1.1.4.1"))
			result = target.snmpGet();
		// H3C
		else if (cpuLoadOid.equals(".1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7"))
			result = target.snmpGet();
		else
			// 为非叶节点，取第一项，如MS，使用GETNEXT方法
			result = target.snmpGetNext();

		int errorCode = target.getErrorCode();
		int errorIndex = target.getErrorIndex();
		if (errorCode != 0) {
			if (errorCode == 22 && errorIndex == 0)
				return "-1";
			return "-1";
		}

		long now;
		try {
			now = Long.parseLong(result);
		} catch (NumberFormatException e) {
			return "-1";
		}

		// 主机UCD-MIB的CPU定义为IDLE，这里转化为BUSY。
		// private.enterprises.ucdavis.systemStats.ssCpuIdle
		if (cpuLoadOid.equals(".1.3.6.1.4.1.2021.11.11.0"))
			now = 100 - now;
		return String.valueOf(now);
	}

	/*
	 * // -------------------------------- 数据采集和存储部分 -------------------------
	 * // 读取厂商代码，然后从厂商表中查找CPUOID，不分主机或设备，默认为UCD-SNMP public static String
	 * getVendorCpuOid(DeviceSnmpQuery deviceSnmpQuery, TableDataSet
	 * vendorDataSet, String ip, String commStr) { String vendor =
	 * deviceSnmpQuery.getVendorOID(ip, commStr); if (vendor == null) return
	 * "超时";// 如果没有读出，则使用CISCO CPUOID，否则不发包响应时间为0。
	 * 
	 * DataRow vloc = new DataRow(vendorDataSet, "VENDOR");
	 * vloc.setString("VENDOR", vendor); if (vendorDataSet.locate(vloc,
	 * Locate.FIRST)) return vendorDataSet.getString("CPU"); else return
	 * ".1.3.6.1.4.1.2021.11.11.0";// 如果没有则缺省使用UCD-SNMP的，但不能为空，否则不发包响应时间为0。 }
	 * 	 */
	  // 判断是否提供64位计数器
	public static String support64(DeviceSnmpQuery deviceSnmpQuery, String ip,
			String commStr) {
		String inOid64 = ".1.3.6.1.2.1.31.1.1.1.6";// 64位ifInOctets //
		// 注1：最后一个参数为Version:0-SnmpVersion1,1-SnmpVersion2C.64位计数器在V1中无定义 //
		// 华为使用V1、V2都可以读64位计数器。CISCO、港湾只能使用V2读。STAR S4226MF只能用V1读，不能用V2读，怪！ //
		// 注2：CISCO设备如果支持64位，但用V1判断时返回空，必须用V2判断是否有数据；GS2不提供多播和广播的64位计数器 //
		// 3COMSUPERSTACK2
		// /3、INTEL460T不支持64位，用V1判断时返回空，使用V2判断时超时。判断这样的设备会导致超时，使采样时间延长。 //
		// 注3：同时将V1和V2的OID放在一个OidList中，可能不能正确返回数据。
		return deviceSnmpQuery.hasTableEntry(ip, commStr, inOid64,
				SnmpTarget.VERSION2C);
	}


	// getValueByParam
	public static MonitorSystemParam getValueByParam(String param) {
		Query q = new Query();
		String hql = "SELECT * FROM netmon.hibernate.po.SystemParam WHERE code = :param";
		QueryResults qr = null;
		try {
			q.parse(hql);
		} catch (QueryParseException ex) {
			ex.printStackTrace();
		}
		q.setVariable("param", param);

		try {
			// 注释2010
			// qr = q.execute(MainFrame.systemParamList);
			qr = q.execute(new ArrayList<MonitorSystemParam>());
		} catch (QueryExecutionException ex) {
			ex.printStackTrace();
		}
		List<MonitorSystemParam> retList = qr.getResults();
		if (retList != null && retList.size() > 0) {
			return retList.get(0);
		} else {
			return null;
		}
	}

	// 参数是否需要更新
	public static MonitorSystemParam isParamUpdate(String param, String value) {
		MonitorSystemParam sp = getValueByParam(param);
		if (sp == null)
			return null;
		if (sp.getValue().equals(value))
			return null;
		sp.setValue(value);
		return sp;
	}
	
	public static long getLinkLoad(long bytes, long ifspd, long timeGap) {
		if (ifspd <= 0)
			return 0;
		long bw = bytes * 1000 / timeGap;
		return (bw * 8 * 100 + ifspd / 2) / ifspd;// 四舍五入
	}
}

// WINS/NETBIOS服务调用
class NBTCall {
	String IPStr = null;
	String computerName = null;
	String domainName = null;
	String userName = null;

	public NBTCall(String ip) {
		IPStr = ip;
		getAllNames();
	}

	private void getAllNames() {
		int i;
		String temp;
		NbtAddress[] addrs;

		try {
			addrs = NbtAddress.getAllByAddress(IPStr);
		} catch (UnknownHostException e) {
			return;
		}
		// 清华实验中166.111.37.80使用nbtscan扫描发现为-no name-。在这里后面的macToString[addrs[0])
		// 报错：java.long.ArrayIndexOutOfBoundsException。实验发现这时的addrs不为null，
		// 但其length为0，如果引用addrs[0]则报错，特在此做检查。怪！实验表明，这种情况是
		// 因为这台机器的计算机名/域名键值与子网中的其它机器名字相同（域名可以不同）。
		if (addrs.length == 0)
			return;

		for (i = 0; i < addrs.length; i++) {
			try {
				if (computerName == null && !addrs[i].isGroupAddress()
						&& addrs[i].getNameType() == 0) {// 计算机名
					temp = addrs[i].toString();
					// temp的格式为NETBIOS名<名字类型>
					temp = temp.substring(0, temp.indexOf("<"));
					// 对于安装了IIS的计算机来说，其计算机名字有两个：计算机名和IS~计算机名，如果后者
					// 先注册，就要排除后者，得到真正的没有IIS识别前缀的计算机名。
					// 判断在名字中是否出现了~符号，再判断其之前的字符串是否为IS~（既安装了IIS），如
					// 果是则忽略它，取下一个名字判断。
					if (temp.indexOf("~") != -1)
						if (temp.substring(0, temp.indexOf("~") + 1).equals(
								"IS~"))
							continue;
					computerName = temp;
					continue;
				}
			} catch (UnknownHostException e) {
				return;
			}

			try {
				if (domainName == null && addrs[i].isGroupAddress()
						&& addrs[i].getNameType() == 0) {// 组或域名
					temp = addrs[i].toString();
					domainName = temp.substring(0, temp.indexOf("<"));
					continue;
				}// if
			} catch (UnknownHostException e) {
				return;
			}

			if (addrs[i].getNameType() == 3) {
				// 这一语句在极个别的情况下出：java.lang.StringIndexOutOfBoundsException:String
				// Index out of range:0 错误。
				// 位置：java.lang.String.charAt/jcifs.netbios.Name.toString(200)/jcifs.netbios.NbtAddress.toString(828)。
				// 原因不知，未改正，不影响程序运行，不带来错误结果。
				try {
					temp = addrs[i].toString();
				} catch (Exception ex) {
					continue;
				}

				temp = temp.substring(0, temp.indexOf("<"));
				if (!temp.equals(computerName))
					userName = temp;
				continue;
			}
		}// for
	}

	// 取得主机名的字符串
	public String getComputerName() {
		return computerName;
	}

	public String getDomainName() {
		return domainName;
	}

	public String getUserName() {
		return userName;
	}

	public static void toCenter(Window w) {
		Point location;
		Dimension w_size = w.getSize();
		Dimension s_size = Toolkit.getDefaultToolkit().getScreenSize();

		if ((w.getOwner() != null) && (w.getOwner().isShowing())) {
			Point offset = w.getOwner().getLocationOnScreen();
			Dimension d = w.getOwner().getSize();
			location = new Point(offset.x + d.width / 2 - (w_size.width / 2),
					offset.y + d.height / 2 - (w_size.height / 2));
		} else {
			location = new Point(s_size.width / 2 - (w_size.width / 2),
					s_size.height / 2 - (w_size.height / 2));
		}

		if (location.y < 0) {
			location.y = 0;
		}
		if (location.x > s_size.width - w_size.width) {
			location.x = (s_size.width - w_size.width);
		}
		w.setLocation(location);
	}

	
}
