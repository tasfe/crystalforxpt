package com.netblizzard.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Html {
	public static void main(String[] args) {
		try {
			String s = genHtml("res/html.txt");
			System.out.println("Before replace ---> " + s);
			s = s.replaceAll("<[^<>]+>", "");
			System.out.println("After replace ---> " + s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String genHtml(String url) throws IOException {
		StringBuilder input = new StringBuilder();
		FileReader fr = new FileReader(url);
		BufferedReader reader = new BufferedReader(fr);
		String str = null;
		while ((str = reader.readLine()) != null) {
			input.append(str);
		}
		return input.toString();
	}
}
