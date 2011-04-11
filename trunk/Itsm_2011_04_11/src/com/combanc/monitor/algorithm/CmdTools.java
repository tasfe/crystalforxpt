package com.combanc.monitor.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.combanc.monitor.pojoext.ArpEntry;

public class CmdTools {
	// 2010-09-10 vista等系统中可能存在中文字符解析
	public static void getLocalArp(List<ArpEntry> arpList) throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "arp -a");

		Process process = builder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "GBK");
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			if ("".equals(line) || line.toLowerCase().contains("interface")
					|| line.toLowerCase().contains("internet address")
					|| line.toLowerCase().contains("internet 地址")
					|| line.toLowerCase().contains("接口"))
				continue;
			ArpEntry ae = new ArpEntry();
			line = line.replaceAll(" ", "");	//去除无用空格
			int index = line.indexOf("-") - 2;	// 获取IP和MAC分界的地址
			ae.setIp(line.substring(0, index));
			ae.setMac(line.substring(index, index + 17).replaceAll("-", " "));
			ae.setUplinkDevice("localhost");
			arpList.add(ae);
		}
	}
}
