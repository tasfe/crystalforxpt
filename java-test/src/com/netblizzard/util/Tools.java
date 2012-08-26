package com.netblizzard.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
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

//import jpcap.JpcapCaptor;
//import jpcap.NetworkInterface;

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
		// 统一转换为00 00 00 00 00 00的格式，同时转换为小写
		str = str.toLowerCase();

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
			if (res != null) {
				res = res.toUpperCase();
			}
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
	
	public static String ipHexToDec(String ip) {
		String ips[] = ip.split(" ");
		try {
			ip = Integer.valueOf(ips[0], 16).toString();
			for(int i = 1;i< ips.length;i++) {
				ip = ip + "." + Integer.valueOf(ips[i], 16).toString();
			}
		} catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return ip;
	}

	/*
	 * // 检查IP地址格式是否正确 public static boolean ipFormatOK(String str) { if (str ==
	 * null || str.equals("")) return false; try { InetAddress.getByName(str); }
	 * catch (UnknownHostException e) { return false; } return true; }
	 */

	// 检验IP格式是否正确（支持IPV4和IPV6）
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
			if (address.split("\\.").length != 4) {
				return false;
			} else {
				return true;
			}
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
			//System.out.println("regIPv6 =" + regIPv6);
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
            //先找到IP地址字符串中.的位置   
            int position1 = strIp.indexOf(".");   
            int position2 = strIp.indexOf(".", position1 + 1);   
            int position3 = strIp.indexOf(".", position2 + 1);
            if (position1 < 1 || position2 < 1 || position3 < 1) {
            	return -1;
            }
            //将每个.之间的字符串转换成整型   
            try {
            	ip[0] = Long.parseLong(strIp.substring(0, position1));   
                ip[1] = Long.parseLong(strIp.substring(position1+1, position2));   
                ip[2] = Long.parseLong(strIp.substring(position2+1, position3));   
                ip[3] = Long.parseLong(strIp.substring(position3+1)); 
            } catch(NumberFormatException e) {
				e.printStackTrace();
				return -1;
			}
            return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];  
    	}
    	return -1;
    }   
       
    //将十进制整数形式转换成127.0.0.1形式的ip地址   
    public static String longToIP(long longIp) {   
        StringBuffer sb = new StringBuffer("");   
        //直接右移24位   
        sb.append(String.valueOf((longIp >>> 24)));   
        sb.append(".");   
        //将高8位置0，然后右移16位   
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));   
        sb.append(".");   
        //将高16位置0，然后右移8位   
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));   
        sb.append(".");   
        //将高24位置0   
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
	
	public static String getApId(String oid) {
		String result = "";
		String ch = "";
		int ich = 0;
		while (oid.indexOf(".") >= 0) {
			ch = oid.substring(oid.lastIndexOf(".") + 1);// 取最后一个.之后的字符串
			if("20".equals(ch))
				break;
			try {
				ich = Integer.parseInt(ch);// 转换成16进制的字符串
			}catch(NumberFormatException nfe) {
				ich = 0;
			}
			if (ich > 0) {
				result = String.valueOf((char)ich) + result;
			} else {
				return "";
			}
			oid = oid.substring(0, oid.lastIndexOf("."));// 从后向前分断字符串
		}
		return result;
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

	/*public static boolean CheckJpcap() {
		try {
			Class.forName("jpcap.JpcapCaptor");
			NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			if (devices.length == 0) {
				JOptionPane.showMessageDialog(null, "没有找到可用网卡.\n你可能需要管理员权限或需要安装WinPcap软件包.");
				return false;
			} else {
				return true;
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "找不到Jpcap包. 请安装Jpcap.");
			return false;
		} catch (UnsatisfiedLinkError e) {
			JOptionPane.showMessageDialog(null, "找不到libpcap/WinPcap.\n 请确认是否安装了libpcap/WinPcap.");
			return false;
		}
	}*/
	
	
	
	
	
	// -------------------------------- 数据采集和存储部分 -------------------------
	public static int getIntFromChinese(String param) {
		if(param == null || "".equals(param))
			return 0;
		String strs[] = new String[] {
				"一","二","三","四","五","六","七","八","九","十",
				"十一","十二","十三","十四","十五","十六","十七","十八","十九"
		};
		for(int i = 0;i< strs.length;i++) {
			if(param.startsWith(strs[i] + "病房"))
				return i + 1;
		}
		return 0;
	}
	
	// 删除子网拓扑图文件
	public static void delFiles(String subnet) {
		File f1 = new File(subnet + "拓扑图.tpg");
		if (f1.exists())
			f1.delete();
		File f2 = new File(subnet + "简单连接表.txt");
		if (f2.exists())
			f2.delete();
		File f3 = new File(subnet + "简单连接表.schema");
		if (f3.exists())
			f3.delete();
		File f4 = new File(subnet + "主机拓扑图.tpg");
		if (f4.exists())
			f4.delete();
	}
	
	private static void display(String str) {
		System.out.println(str);
	}
}
