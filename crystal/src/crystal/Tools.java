package crystal;

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

import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dbswing.JdbTable;
import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.DataRow;
import com.borland.dx.dataset.DataSet;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.borland.dx.dataset.Variant;

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

	// ��IP��ַ�Ĳ���0ɾ����
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

	// �ж�IP�Ƿ���������
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

		// ע������û�м�������������Ƿ�ƥ��
		// ���IP�Ƿ��������У���IP�����밴λ�룬���������űȽ��Ƿ���ȡ�
		for (int i = 0; i <= 3; i++)
			mid[i] = (int) ip[i] & (int) mask[i];
		if ((mid[0] == (int) subnet[0]) && (mid[1] == (int) subnet[1])
				&& (mid[2] == (int) subnet[2]) && (mid[3] == (int) subnet[3])) {
			return true;
		} else {
			return false;
		}
	}

	// �ж�����ID�������Ƿ�ƥ�䡣
	public static boolean subnetOK(String snStr, String maskStr) {
		int subnet, mask, oldmask;
		int index = 1;
		if (snStr == null || snStr.equals(""))
			return false;
		if (maskStr == null || maskStr.equals(""))
			return false;
		// hashCode�������IP��ַ��������ʾ
		try {
			subnet = InetAddress.getByName(snStr).hashCode();
			mask = InetAddress.getByName(maskStr).hashCode();
		} catch (UnknownHostException e) {
			// ������ܻ�����ַ��������ַ��������⡣
			return false;
		}
		;
		if (mask == -1)
			return false;
		oldmask = mask;
		// ������󲿷�Ϊ������1���Ҳ���Ϊ������0��>>��������λ�������ƶ�1λ�������
		// ����һ��1������0�����ƶ���Ϻ󣬱��ȫ1����-1��
		do {
			mask = mask >> 1;
			index = index * 2;
		} while (mask != -1);
		if (index == 2)
			return false;// �������λΪ254
		// ���������ȡģ����0����ʾ���������������ұ�Ϊ���λ����������ͬ����������ȷ��
		// ����������������������ʱ�����ȫ1��-1��ʱ��ͣ���������1��0���м䣬��ʱ
		// ����ұ߶���0����oldmask��������index,�����ܣ������ж������Ƿ���ȷ������ź������Ƿ�ƥ�䡣
		if (oldmask % index == 0 && subnet % index == 0)
			return true;
		else
			return false;
	}

	// ȡ�������п��õ�IP��
	public static int getSubnetSize(String snMask) {
		int mask;
		int index = 1;
		// hashCode�������IP��ַ��������ʾ
		try {
			mask = InetAddress.getByName(snMask).hashCode();
		} catch (UnknownHostException e) {
			// ������ܻ�����ַ��������ַ��������⡣
			return 0;
		}
		;
		// ��������������
		if (mask == -1)
			return 0;
		// ������󲿷�Ϊ������1���Ҳ���Ϊ������0��>>��������λ�������ƶ�1λ�������
		// ����һ��1������0�����ƶ���Ϻ󣬱��ȫ1����-1��
		do {
			mask = mask >> 1;
			index = index * 2;
		} while (mask != -1);
		// ���������ȡģ����0����ʾ���������������ұ�Ϊ���λ����������ͬ����������ȷ��
		// ������ȫ0��ȫ1��������ַ��
		return index - 2;
	}

	// ȡ�ø��������Ĺ㲥��ַ��
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

	public static boolean pingSubnet(String subnet, DataSet segmentDataSet) {
		if (segmentDataSet.isEmpty())
			return false;
		segmentDataSet.first();
		do {
			if (!segmentDataSet.getString("������").equals(subnet))
				continue;
			if (!pingSegment(subnet, segmentDataSet.getString("����"),
					segmentDataSet.getString("����")))
				return false;
		} while (segmentDataSet.next());
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
			JOptionPane.showMessageDialog(null, "Ping Socket �򿪴���");
			display("Ping Socket �򿪴���");
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
		// �������ݱ���
		// ʹ��UDP�˿�53��ΪDNS��ͨ������ǽ������˸ö˿ڡ�Ŀ�ĵ�ַӦ��ICMP��Protocol unreachable��Ӧ��
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
				display("Ping���� " + subnet + " ���� " + snet + " / " + mask
						+ " ��IP��" + ipstr);
				try {
					sendpacket.setAddress(InetAddress.getByName(ipstr));
				} catch (UnknownHostException ex) {
					if (!MainFrame.ignoreError) {
						JOptionPane.showMessageDialog(null, "Ping " + ipstr
								+ " ����" + ex);
						display("Ping " + ipstr + " ����" + ex);
						return false;
					}
				}
				try {
					sendsocket.send(sendpacket);
				} catch (IOException io) {
					if (!MainFrame.ignoreError) {
						JOptionPane.showMessageDialog(null, "Ping " + ipstr
								+ " ����" + io);
						display("Ping " + ipstr + " ����" + io);
						return false;
					}
				}
				// Delay between two packets.
				if (MainFrame.pingGap > 0)
					try {
						// 10��15��20ms���Ա�����15ms�����룬������·����ѧ��IP����MAC��30ms�����ա�
						Thread.sleep(MainFrame.pingGap);
					} catch (InterruptedException e) {
					}
				;
			}// for j
		}// for i
		sendsocket.close();
		return true;
	}// pingSubnet

	// lsj 2010-03-15
	public static String decodeISO8859(String str) {
		try {
			// ���Ĳ�����
			// str = new String(str.getBytes("ISO8859-1"),"UTF-8");
			// ���Ŀ���
			// str = new String(str.getBytes("ISO8859-1"),"GBK");
			// ���Ŀ���
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
		// һ��������UNICODE������Ҫ�����ֽڣ������м�ո���Ҫ5���ַ���
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
				// ����޷��������򷵻�ԭֵ
				try {
					bytes[j] = (byte) Integer.parseInt(str.substring(i, i + 2),
							16);
				} catch (NumberFormatException e) {
					return str;
				}
				// ���ֵĸ��ֽں͵��ֽڶ�Ϊ�����Դ��������ֺͷǺ��֡�һ�����ֵ��ֽ������ַ�
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

	// ���MAC��ַ��ʽ�Ƿ���ȷ
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
	
	// �ж��Ƿ�������
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	} 


	// ��AVAYA880 001122334455��ʽ��MAC��ַ���ӿո�
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

	// �ж�Mac��ַ�ĺϷ���,yyp 09.11.28
	public static boolean isMac(String sMac) {
		if (sMac == null || sMac.length() == 0)
			return false;
		// ���ָ�ʽ��00-00-00-00-00-00��00 00 00 00 00 00��000000000000
		Pattern macPattern = Pattern
				.compile("([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})( [0-9A-Fa-f]{2}){5}|([0-9A-Fa-f]{2})([0-9A-Fa-f]{2}){5}");
		Matcher macMatcher = macPattern.matcher(sMac);
		return macMatcher.matches();
	}

	// ������Mac��ַͳһ��00 00 00 00 00 00��ʽ,yyp 09.11.28
	public static String transformMacFormat(String sMac) {
		String res = "";
		if (sMac.length() == 12) {
			res = Tools.avayaMacFormat(sMac);
		} else {
			res = sMac.replace('-', ' ').toUpperCase();
		}
		return res;
	}

	// ��00 00 00 00 00 00ת��Ϊ0000.0000.0000
	public static String transMacFormatToPoint(String sMac) {
		String res = transformMacFormat(sMac).toLowerCase();
		String[] tmp = res.split(" ");
		if (tmp.length == 6) {
			res = tmp[0] + tmp[1] + "." + tmp[2] + tmp[3] + "." + tmp[4]
					+ tmp[5];
		}
		return res;
	}
	
	// ��00 00 00 00 00 00ת��Ϊ0000-0000-0000
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
	 * // ���IP��ַ��ʽ�Ƿ���ȷ public static boolean ipFormatOK(String str) { if (str ==
	 * null || str.equals("")) return false; try { InetAddress.getByName(str); }
	 * catch (UnknownHostException e) { return false; } return true; }
	 */

	// ����IP��ʽ�Ƿ���ȷ��֧��IPV4��IPV6��
	public static boolean ipFormatOK(String address) {
		if (address == null || address.length() == 0)
			return false;
		// IPV4������ʽ
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
		if (m.matches()) { // �ж��Ƿ�IPV4��ʽ
			return true;
		} else { // �ж��Ƿ�IPV6��ʽ
			// IPV6������ʽ
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

	// ȡ���ַ�����ʾ��IP��ַ�ĳ�����ֵ
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
	
	// IP��ַת����ʮ��������
    public static long ipToLong(String strIp) { 
    	if (ipFormatOK(strIp) && (!(strIp.indexOf(":") > 0))) {
    		long[] ip = new long[4];   
            //���ҵ�IP��ַ�ַ�����.��λ��   
            int position1 = strIp.indexOf(".");   
            int position2 = strIp.indexOf(".", position1 + 1);   
            int position3 = strIp.indexOf(".", position2 + 1);   
            //��ÿ��.֮����ַ���ת��������   
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
       
    //��ʮ����������ʽת����127.0.0.1��ʽ��ip��ַ   
    public static String longToIP(long longIp) {   
        StringBuffer sb = new StringBuffer("");   
        //ֱ������24λ   
        sb.append(String.valueOf((longIp >>> 24)));   
        sb.append(".");   
        //����8λ��0��Ȼ������16λ   
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));   
        sb.append(".");   
        //����16λ��0��Ȼ������8λ   
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));   
        sb.append(".");   
        //����24λ��0   
        sb.append(String.valueOf((longIp & 0x000000FF)));   
        return sb.toString();   
    }   

	// ���˿ڻ�ӿڸ�ʽ�Ƿ���ȷ
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

	// ʵ���з���ADVENT SNMP API��BUG��ǧ��֮һ�ĸ�����MAC��ַ�����ַ��������ֽ���ʽ
	// ���أ��磺RTL)!x����ʵ�ʵ��ַ�����ʽ��MACӦΪ��52 54 4c 29 21 58
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

	// ʱ�����ͣ�xx days, yy hours, ..ת��Ϊ���ģ�ֻ�������Сʱ
	public static String timeToChinese(String str) {
		String ret = "";
		int dIndex = str.indexOf("days");
		if (dIndex != -1) {
			ret = ret + str.substring(0, dIndex - 1) + "�� ";
			str = str.substring(dIndex + 6);
		} else {
			dIndex = str.indexOf("day");
			if (dIndex != -1) {
				ret = "1�� ";
				str = str.substring(dIndex + 5);
			}
		}

		int hIndex = str.indexOf("hours");
		if (hIndex != -1) {
			ret = ret + str.substring(0, hIndex - 1) + "Сʱ";
			str = str.substring(hIndex + 7);
		} else {
			hIndex = str.indexOf("hour");
			if (hIndex != -1) {
				ret = ret + "1Сʱ";
				str = str.substring(hIndex + 6);
			}
		}

		if (dIndex == -1 && hIndex == -1)
			ret = "< 1Сʱ";

		return ret;
	}

	// ɾ��schema�ļ�
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
		chooser.setDialogTitle("������ͼ");
		chooser.setApproveButtonText("ȷ��");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setSelectedFile(new File(fileName));
		int retVal = chooser.showOpenDialog(jPanel);
		if (retVal != JFileChooser.APPROVE_OPTION)
			return;
		String fname = chooser.getSelectedFile().toString();
		// fname.replaceFirst("���뵵����.txt", "���뵵����.xls");
		File f = new File(fname);
		if (f.exists()) {
			int confirm = JOptionPane.showConfirmDialog(jPanel, "�ļ��Ѵ��ڣ��Ƿ񵼳���",
					"�����ļ�", JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION)
				return;
		}

		// �ɼ��м���
		int colIndex = 0;
		for (int i = 0; i < currentDataSet.getColumnCount(); i++) {
			if (currentDataSet.getColumn(i).getVisible() == 0)
				continue;
			colIndex++;
		}

		// ���쵼����
		Column[] exportCol = new Column[colIndex];
		colIndex = 0;
		for (int i = 0; i < currentDataSet.getColumnCount(); i++) {
			if (currentDataSet.getColumn(i).getVisible() == 0)
				continue;// ���������ɼ���
			exportCol[colIndex] = new Column();
			exportCol[colIndex].setColumnName(currentDataSet.getColumn(i)
					.getColumnName());
			exportCol[colIndex]
					.setDataType(com.borland.dx.dataset.Variant.STRING);
			colIndex++;
		}
		// ���쵼��DATASET
		TableDataSet epDataSet = new TableDataSet();
		epDataSet.setColumns(exportCol);
		epDataSet.open();

		DataRow row = new DataRow(epDataSet);
		// ���������
		for (int i = 0; i < colIndex; i++)
			row.setString(i, exportCol[i].getColumnName());
		epDataSet.addRow(row);

		currentDataSet.enableDataSetEvents(false);
		// ���Ƹ���
		currentDataSet.first();
		String colName = null;
		do {
			for (int i = 0; i < currentDataSet.getColumnCount(); i++) {
				if (currentDataSet.getColumn(i).getVisible() == 0)
					continue;// �������ɼ���
				colName = currentDataSet.getColumn(i).getColumnName();
				if (currentDataSet.getColumn(i).getDataType() == Variant.STRING) {
					String tmpStr = currentDataSet.getString(colName);
					if (colName.equals("����"))
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
		// �����ļ�
		try {
			if (f.getAbsoluteFile().toString().indexOf("���뵵����") > 0) {
				System.out.println(f.getAbsolutePath());
				FileOutputStream fos = new FileOutputStream(f);

				// ����excel���
				WritableWorkbook wbook = Workbook.createWorkbook(fos);
				// ����������
				WritableSheet wsheet = wbook.createSheet("�����������", 0);

				// ��������
				WritableFont wfont = new WritableFont(WritableFont.ARIAL,
						14,
						// WritableFont.NO_BOLD, false,
						WritableFont.BOLD, false,
						jxl.format.UnderlineStyle.NO_UNDERLINE,
						jxl.format.Colour.RED);
				WritableCellFormat titleFormat = new WritableCellFormat(wfont);
				// ���ñ�ͷ
				String[] title = { "IP", "MAC", "�����豸", "�˿�", "�ӿ�", "�ӿ�����",
						"�������", "�����", "��¼��", "�������ʱ��", "ʹ����", "�ص�", "����",
						"��ע1", "��ע2" };
				int[] cellLen = { 15, 18, 15, 8, 8, 20, 14, 10, 10, 20, 10, 10,
						12, 20, 20 };
				for (int i = 0; i < title.length; i++) {
					Label excelTitle = new Label(i, 0, title[i], titleFormat);
					wsheet.addCell(excelTitle);
					wsheet.setColumnView(i, cellLen[i]);
				}
				// excel����к�
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
				 * //Ĭ��ֵ0�������ᴰ�ڣ����������˶����1�к͵�1��ȴ��������
				 * settings.setHorizontalFreeze(1);
				 * settings.setVerticalFreeze(1);
				 */

				// д��excel
				wbook.write();
				wbook.close();
				fos.close();
			} else {
				// ����txt��ʽ����
				TextDataFile tmp = new TextDataFile();
				tmp.setFileName(fname);
				tmp.setDelimiter(""); // �ַ����߽�Ϊ�գ�ȱʡΪ˫����
				tmp.setSeparator("\t"); // �ֶηָ���ΪTAB
				tmp.save(epDataSet);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(jPanel, "��������" + ex);
			return;
		}
		Tools.delSchemaFile(fname);
		JOptionPane.showMessageDialog(jPanel, "��񵼳��ɹ���");
	}

	public static String selectJpgFile(JPanel jPanel, String type,
			String filePath) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(type + "���˱���ͼѡ��");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retVal = chooser.showOpenDialog(jPanel);
		File file = chooser.getSelectedFile();
		if (retVal == JFileChooser.APPROVE_OPTION) {
			if (file.exists()) {
				String tmpName = file.getName().toLowerCase();
				if (!(tmpName.endsWith(".jpg") || tmpName.endsWith(".jpeg") || tmpName
						.endsWith("png"))) {
					JOptionPane.showMessageDialog(chooser,
							"��ѡ��jpg��jpeg����png��ʽͼƬ");
					return filePath;
				}
				// �ֱ���Ϊ1024*768
				/*
				 * ImageIcon tmpIcon = new ImageIcon(file.getAbsolutePath()); if
				 * (! (tmpIcon.getIconWidth() == 1024 && tmpIcon.getIconHeight()
				 * == 768) && ! (tmpIcon.getIconWidth() == 800 &&
				 * tmpIcon.getIconHeight() == 640)) {
				 * JOptionPane.showMessageDialog(chooser,
				 * "��ѡ��ֱ���Ϊ1024*768/800*640��jpg��jpeg����png��ʽͼƬ"); return
				 * filePath; }
				 */
				return file.getAbsolutePath();
			}
		}
		return filePath;
	}

	public static String selectFile(JPanel jPanel, String title, String filePath) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle(title + "ѡ��");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retVal = chooser.showOpenDialog(jPanel);
		File file = chooser.getSelectedFile();
		if (retVal == JFileChooser.APPROVE_OPTION) {
			if (file.exists()) {
				// if (title.equals("�Զ��幤��"))
				// �޸��ļ���ʽ����Ϊ����C:\Program Files\JBSKB.exe���ָ�ʽ�д��ڿո�
				// ��Ҫ�޸�ΪC:\"Program Files\JBSKB.exe"�ĸ�ʽ
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

	// FDBת�����صĶ˿ں�Ϊ�ո�ָ��4�ֽڵ�16�����ַ�����ת��Ϊ10���������ַ�����
	public static String getGWPort1(String port) {
		String result = "";
		StringTokenizer token = new StringTokenizer(port, " ");
		while (token.hasMoreTokens())
			result += token.nextToken();

		// System.out.println(port + "=======" +
		// String.valueOf(Integer.parseInt(result,16)));
		return String.valueOf(Integer.parseInt(result, 16));
	}

	// FDBת�����صĶ˿ں�Ϊ�ո�ָ��4�ֽڵ�16�����ַ����������ֽ�˳����ֽڵĶ�������1λ
	// ����������ɶ˿ںš�
	public static String getGWPort2(String port) {
		String[] result = new String[] { "x", "x", "x", "x", "x", "x", "x", "x" };// ��ʼ��
		String pt = "";
		int base = 0, sub = 0;// �ֽڻ������ֽ���λ��
		// ȡ�������ֽ�
		StringTokenizer token = new StringTokenizer(port, " ");
		int i = 0;
		while (token.hasMoreTokens()) {
			result[i] = token.nextToken();
			i++;
		}
		// �ֽ���ֻ��һ���ֽڷ�0��Ϊ�˿���1λ�ֽڣ����ò�ͬ���ֽڻ�����Ŀǰֻ����8���ֽ�
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
		// ���ұ�����λ�ֽ�ӳ�����Ӧ����š�
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
		// �ֽڻ���+�ֽ���λ���Ϊ�˿ںš�
		return String.valueOf(base + sub);
		// �������ض˿��ַ���Ϊ00 00 20 00��ÿ�ֽڶ�Ӧ�Ķ˿����С�������У���һ�ֽڶ�Ӧ�˿���1-8��
		// �ڶ��ֽڶ�Ӧ�˿���9-16�������ֽڶ�Ӧ��17-24�������ֽڶ�Ӧ�˿���25-32�����Ӷ˿�λ��17-24
		// �飬20�ֽڵ�2����00100000����1λ�ڸ�λ�ֽڣ��ǵ�6λ����ôʵ�ʶ˿�Ϊ16+6=22��
	}

	// ���ô��ڵ���ʾλ��(����ĻΪ����)�ʹ�С
	public static void dispWin(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getPreferredSize();
		// ȷ�����ڴ�С��������Ļ��С��
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

	// ���öԻ������ʾλ��(����ĻΪ����)�ʹ�С
	public static void dispWin(JDialog dialog) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = dialog.getPreferredSize();
		dialog.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	// ȡ��CISCO CDP-MIB���ص�16�������ֽڿո�ָ��IP��ַת��Ϊ��׼��ʽ
	public static String getCiscoIp(String cstr) {
		StringTokenizer token = new StringTokenizer(cstr, " ");// �Կո�ָ�
		String tmp = null, ip = "";
		while (token.hasMoreTokens()) {
			// ����ȡ����һ�еĸ��У�
			tmp = token.nextToken();
			ip = ip + Integer.parseInt(tmp, 16) + ".";
		}
		return ip.substring(0, ip.length() - 1);
	}

	// ����ȡQ-BRIDGE-MIB��PORT�������ص�OID�е�MAC��ַ����������
	// OID���ظ�ʽ��dot1qTpFdbPort.2.0.19.212.67.6.57���������00 13 d4 43 06 39
	public static String getQBMac(String cstr) {
		String[] mac = new String[6];
		String tmp = "";

		for (int i = 5; i >= 0; i--) {
			mac[i] = cstr.substring(cstr.lastIndexOf(".") + 1);// ȡ���һ��.֮����ַ���
			mac[i] = Integer.toHexString(Integer.parseInt(mac[i]));// ת����16���Ƶ��ַ���
			if (mac[i].length() == 1)
				mac[i] = "0" + mac[i];// ����0
			cstr = cstr.substring(0, cstr.lastIndexOf("."));// �Ӻ���ǰ�ֶ��ַ���
		}

		for (int i = 0; i < 5; i++)
			tmp += mac[i] + " ";

		return tmp += mac[5];
	}

	public static String cutDescr(String str) {
		int index;
		// ȥ��CISCO�����е�IOS���֣�Cisco֮��(tm) ֮�䡣
		if (str.startsWith("Cisco"))
			if ((index = str.indexOf("IOS (tm) ")) != -1)
				return "Cisco " + str.substring(index + 9);
		// ȥ��HUAWEI�����е�VRP����
		if (str.startsWith("Huawei"))
			if ((index = str.indexOf("Versatile Routing Platform Software")) != -1)
				return "Huawei " + str.substring(index + 36);
		// ����
		return str;
	}

	// ������ʽ��ip@port
	public static String tcpPing(String ipPort) {
		String target = ipPort.substring(0, ipPort.indexOf("@"));
		int port = Integer.parseInt(ipPort.substring(ipPort.indexOf("@") + 1));
		try {
			Socket sock = new Socket(target, port);
			if (sock != null) {
				sock.close();
				return "����";
			} else
				return "��ʱ";
		} catch (java.io.IOException e) {
			return "��ʱ";
		}
	}

	// ���ݳ���ͬ�����е㣬�ڲ�ͬλ�ý�����Ϣ��ʾ��
	private static void display(String str) {
		MainFrame.statusBar.setText(str);
	}

	// �ṩlong���ʹ������ݵ��ı���ʾ����BPSΪ��λ���ṩK��Mֵ��ÿKΪ1000������1024
	public static String getBandText(long bw) {
		if (bw < 1000)
			return String.valueOf(bw);
		double ds = bw;
		ds = ds / 1000;
		if (ds < 1000)
			return cutEnd0(ds) + "K";// ׼ȷ��С�����һλ
		ds = ds / 1000;
		if (ds < 1000)
			return cutEnd0(ds) + "M";// ׼ȷ��С�����һλ
		ds = ds / 1000;
		return cutEnd0(ds) + "G";// ׼ȷ��С�����һλ
	}

	// ��1��K��M��G���ַ���ת��Ϊlong����
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

	// 2009-7-25�޸ģ���private��Ϊpublic
	// С�����Ϊ0������ȥ��С�����0���Ż���ʾ
	public static String cutEnd0(double d) {
		java.text.DecimalFormat formatter = new java.text.DecimalFormat("#0.0");// ����������ʾ����
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
				if (tmp.indexOf("�ļ���") != -1)
					fc.getComponents()[i].setEnabled(false);
				if (tmp.indexOf("�ļ�����") != -1)
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
			// System.out.println("�ϲ��ļ�---" + fileName);
			File fileSource = new File(sourcePath + fileName);
			File fileTarget = new File(targetPath + fileName);
			FileInputStream ins = new FileInputStream(fileSource);
			// ����Ϊtrue���ļ�׷��
			FileOutputStream outs = new FileOutputStream(fileTarget, true);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = ins.read(bytes)) != -1) {
				// System.out.println("combining....");
				// System.out.println(bytes.toString());
				outs.write(bytes, 0, c);
				if (fileName.equals("�豸��.txt")) {
					// System.out.println(bytes.toString());
				}
			}
			ins.close();
			outs.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "�����ļ��ϲ�����" + ex);
			return false;
		}
		return true;
	}

	// addCode 2009-03-18 end

	// addCode 2009-04-08
	// ǿ����չColumn���
	public static Column[] widen(Column cols[], double scale) {
		if (cols.length <= 0 || scale <= 0)
			return null;
		for (int i = 0; i < cols.length; i++) {
			// boolean���Ͳ�������
			if (cols[i].getDataType() != com.borland.dx.dataset.Variant.BOOLEAN) {
				// ����double�ľ������⣬���scaled֮���ԭ��һ������ǿ�Ƽӿ�1
				if ((int) (cols[i].getWidth() * scale) == (int) (cols[i]
						.getWidth()))
					cols[i].setWidth((int) (cols[i].getWidth() + 1));
				else
					cols[i].setWidth((int) (cols[i].getWidth() * scale));
			}
		}
		return cols;
	}

	// addCode 2009-04-08
	// ǿ����չRowHeader���
	public static void widenRowHeader(JdbTable old, double scale) {
		if (old == null || scale <= 0)
			return;
		// ��ֹ����û���¼ QuerySelect �ع�ʱ��μӿ�RowHeader������35ʱ��ǿ�Ʒ���
		if (old.getRowHeader().getPreferredSize().getWidth() > 35)
			return;

		// ������ھ������⣬��Ȳ��䣬��ǿ�Ƽ�10
		if ((int) (old.getRowHeader().getPreferredSize().getWidth() * scale) == (int) (old
				.getRowHeader().getPreferredSize().getWidth())) {
			System.out.print("���ھ������⣬���¿�Ȳ���");
			old.getRowHeader().setPreferredSize(
					new Dimension((int) (old.getRowHeader().getPreferredSize()
							.getWidth() + 10), (int) (old.getRowHeader()
							.getPreferredSize().getHeight())));
		} else
			old.getRowHeader().setPreferredSize(
					new Dimension((int) (old.getRowHeader().getPreferredSize()
							.getWidth() * scale), (int) (old.getRowHeader()
							.getPreferredSize().getHeight())));
	}
	
	public static boolean CheckJpcap() {
		try {
			Class.forName("jpcap.JpcapCaptor");
			NetworkInterface[] devices = JpcapCaptor.getDeviceList();
			if (devices.length == 0) {
				JOptionPane.showMessageDialog(null, "û���ҵ���������.\n�������Ҫ����ԱȨ�޻���Ҫ��װWinPcap�����.");
				return false;
			} else {
				return true;
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "�Ҳ���Jpcap��. �밲װJpcap.");
			return false;
		} catch (UnsatisfiedLinkError e) {
			JOptionPane.showMessageDialog(null, "�Ҳ���libpcap/WinPcap.\n ��ȷ���Ƿ�װ��libpcap/WinPcap.");
			return false;
		}
	}
	
	// ȡ�ڵ��CPU����Ӧ/����ʱ�䣬�������ݱ��浽�ڵ�����Ӧλ�á�
	public static String getCpuRunTime(SnmpTarget target, String ip, String commStr, String cpuLoadOid) {
		// ���ȶ�ȡϵͳ����ʱ�䲢�����Ӧʱ��
		target.setTimeout(1);
		target.setRetries(0);
		target.setTargetHost(ip);
		target.setCommunity(commStr);
		target.setObjectID(".1.3.6.1.2.1.1.3.0");// mib-2.system.sysUpTime
		long timeUsed = System.currentTimeMillis();
		String result = target.snmpGet();
		timeUsed = System.currentTimeMillis() - timeUsed;

		// Ȼ���ȡCPULOAD
		target.setObjectID(cpuLoadOid);
		// ȡOID�����һλ
		String last = cpuLoadOid.substring(cpuLoadOid.lastIndexOf(".") + 1);
		if (last.equals("0")) // CPUOIDΪҶ�ڵ㣬��CISCO��ʹ��GET����
			result = target.snmpGet();
		// Harbor
		else if (cpuLoadOid.equals(".1.3.6.1.4.1.8212.1.1.4.1.1.4.1"))
			result = target.snmpGet();
		// H3C
		else if (cpuLoadOid.equals(".1.3.6.1.4.1.2011.10.2.6.1.1.1.1.6.7"))
			result = target.snmpGet();
		else
			// Ϊ��Ҷ�ڵ㣬ȡ��һ���MS��ʹ��GETNEXT����
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

		// ����UCD-MIB��CPU����ΪIDLE������ת��ΪBUSY��
		// private.enterprises.ucdavis.systemStats.ssCpuIdle
		if (cpuLoadOid.equals(".1.3.6.1.4.1.2021.11.11.0"))
			now = 100 - now;
		return String.valueOf(now);
	}
}

// WINS/NETBIOS�������
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
		// �廪ʵ����166.111.37.80ʹ��nbtscanɨ�跢��Ϊ-no name-������������macToString[addrs[0])
		// ����java.long.ArrayIndexOutOfBoundsException��ʵ�鷢����ʱ��addrs��Ϊnull��
		// ����lengthΪ0���������addrs[0]�򱨴����ڴ�����顣�֣�ʵ����������������
		// ��Ϊ��̨�����ļ������/������ֵ�������е���������������ͬ���������Բ�ͬ����
		if (addrs.length == 0)
			return;

		for (i = 0; i < addrs.length; i++) {
			try {
				if (computerName == null && !addrs[i].isGroupAddress()
						&& addrs[i].getNameType() == 0) {// �������
					temp = addrs[i].toString();
					// temp�ĸ�ʽΪNETBIOS��<��������>
					temp = temp.substring(0, temp.indexOf("<"));
					// ���ڰ�װ��IIS�ļ������˵�����������������������������IS~����������������
					// ��ע�ᣬ��Ҫ�ų����ߣ��õ�������û��IISʶ��ǰ׺�ļ��������
					// �ж����������Ƿ������~���ţ����ж���֮ǰ���ַ����Ƿ�ΪIS~���Ȱ�װ��IIS������
					// �������������ȡ��һ�������жϡ�
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
						&& addrs[i].getNameType() == 0) {// �������
					temp = addrs[i].toString();
					domainName = temp.substring(0, temp.indexOf("<"));
					continue;
				}// if
			} catch (UnknownHostException e) {
				return;
			}

			if (addrs[i].getNameType() == 3) {
				// ��һ����ڼ����������³���java.lang.StringIndexOutOfBoundsException:String
				// Index out of range:0 ����
				// λ�ã�java.lang.String.charAt/jcifs.netbios.Name.toString(200)/jcifs.netbios.NbtAddress.toString(828)��
				// ԭ��֪��δ��������Ӱ��������У���������������
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

	// ȡ�����������ַ���
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
