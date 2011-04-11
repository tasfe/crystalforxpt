package com.combanc.itsm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.lang.RandomStringUtils;

public class CharacterUtil 
{
	//通过该方法,将产生长度为length的随机字符串;
	public static String randomString(int length)
	{
		return RandomStringUtils.randomAlphabetic(length);
	}
	
	//通过该方法,可以将特定的字符串用,分开;//str:表示要分割的字符串;
	public static List<String> getSplitedList(String str,String delim)
	{
		List<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str,delim);
		
		while(st.hasMoreTokens())
		{
			list.add(st.nextToken());
		}
		return list;
	}
}
