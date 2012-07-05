package com.netblizzard.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mac {
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("([0-9A-Fa-f]{2}\\s){2}[0-9A-Fa-f]{2}");
			String[] str = {"00 aF 12", "0Z ab 32", "12 34"};
			for(String s : str) {
				Matcher matcher = pattern.matcher(s);
				if(matcher.matches()) {
					System.out.println(s + "   ∆•≈‰≥…π¶");
				}
			}
			
			
	}
}
