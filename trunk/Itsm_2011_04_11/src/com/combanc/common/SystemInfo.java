package com.combanc.common;

import java.io.InputStream;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.combanc.itsm.util.FileUtil;

public class SystemInfo {
	public final static String getMacAddress() {
		String os = System.getProperty("os.name").toLowerCase();
		try {
			String cmd = "ipconfig /all";
			if (os.indexOf("windows") < 0) {
				cmd = "ifconfig";
			}
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream is = proc.getInputStream();
			String output = FileUtil.readText(is, "GBK");
			Pattern p = Pattern.compile("([0-9a-zA-z]{2}[\\:\\-]){5}[0-9a-zA-z]{2}", Pattern.DOTALL);
			Matcher m = p.matcher(output);
			int lastIndex = 0;
			StringBuffer sb = new StringBuffer();
			while (m.find(lastIndex)) {
				if (lastIndex != 0) {
					sb.append(",");
				}
				sb.append(m.group(0));
				lastIndex = m.end();
			}
			return sb.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public final static void main(String[] args) {
		try {
			System.out.println("  Operating System: " + System.getProperty("os.name"));
			System.out.println("  IP/Localhost: " + InetAddress.getLocalHost().getHostAddress());
			System.out.println("  MAC Address: " + getMacAddress());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}