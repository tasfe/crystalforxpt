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

public class ParseIeeeMac {
	
	public static void main(String args[]) throws FileNotFoundException {
		int MAC_LEN = 8;
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
			while((line = br.readLine()) != null) {
				line = line.trim();
				if(line.length() < MAC_LEN) {
					continue;
				}
				mac = line.substring(0, MAC_LEN);
				if(mac.charAt(2) == '-' && mac.charAt(5) == '-') {
					count++;
					company = line.substring(MAC_LEN).trim();
					if(company.startsWith("(hex)")) {
						company = company.substring(5);
					}
					System.out.print(count + "\t" + mac + "\t" + company + "\r\n");
					bw.append(count + "\t" + mac + "\t" + company + "\r\n");
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
}
