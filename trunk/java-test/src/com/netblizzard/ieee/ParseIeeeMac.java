package com.netblizzard.ieee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseIeeeMac {
	private static final int MAC_LEN = 8;
	
	public static void main(String args[]) throws FileNotFoundException {
		FileInputStream in = new FileInputStream("c:/oui.txt");
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		
		FileOutputStream out = new FileOutputStream(new File("c:/mac.txt"));
		OutputStreamWriter osw = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(osw);
		
		String line = "";
		String mac = "";
		String company = "";
		int count = 0;
		try {
			bw.append("ID\tMAC\tVENDOR\n");
			while((line = br.readLine()) != null) {
				line = line.trim();
				if(line.length() < MAC_LEN) {
					continue;
				}
				mac = line.substring(0, MAC_LEN);
				// 有些公司名字中含有XX-XX-X 格式的字符串，最后一位是别的字符，所以最好使用正则表达式对MAC地址做严格判断
				if(checkMac(mac)) {
					count++;
					company = line.substring(MAC_LEN).trim();
					if(company.startsWith("(hex)")) {
						company = company.substring(5).replaceAll("\t", "").replaceAll("\"", "");
					}
					mac = mac.replaceAll("-", " ");
					System.out.print(count + "\t" + mac + "\t" + company + "\n");
					bw.append(count + "\t" + mac + "\t" + company + "\n");
				} else {
					continue;
				}
			}
			bw.flush();
			bw.close();
			osw.close();
			out.close();
			br.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----> count = " + count);
	}
	
	private static boolean checkMac(String mac) {
		Pattern macPattern = Pattern.compile("([0-9A-Fa-f]{2})(-[0-9A-Fa-f]{2}){2}");
		Matcher macMatcher = macPattern.matcher(mac);
		if(macMatcher.find()) {
			return true;
		}
		return false;
	}
}
