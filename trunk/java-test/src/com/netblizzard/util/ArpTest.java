package com.netblizzard.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ArpTest {

	public void startdemo() throws IOException {
		ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "arp -a");

		Process process = builder.start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "GBK");
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			if ("".equals(line) || line.toLowerCase().contains("interface")
					|| line.toLowerCase().contains("internet address"))
				continue;
			System.out.println(line.trim());
			line = line.replaceAll(" ", "");	//去除无用空格
			int index = line.indexOf("-") - 2;	// 获取IP和MAC分界的地址
			System.out.println("IP地址：		" + line.substring(0, index));
			System.out.println("MAC地址：	" + line.substring(index, index + 17).replaceAll("-", " "));
			
		}
	}

	public static void main(String args[]) {
		ArpTest arpTest = new ArpTest();
		try {
			arpTest.startdemo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
