package com.netblizzard.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Phone {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("(\\(\\d{3}\\)|^\\(\\d{3})" +
				"\\s\\d{3}(-|\\s)?\\d{4}");
		try {
			FileReader fr = new FileReader("res/phone.txt");
			BufferedReader in = new BufferedReader(fr);
			String s;
			while ((s = in.readLine()) != null) {
				Matcher matcher = pattern.matcher(s);
				if(matcher.find()) {
					System.out.println(matcher.group());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
