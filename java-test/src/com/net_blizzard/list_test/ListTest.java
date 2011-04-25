package com.net_blizzard.list_test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String args[]) {
		IpMacEntry ime1 = new IpMacEntry();
		ime1.setIp("192.168.1.1");
		ime1.setMac("aaaa");
		IpMacEntry ime2 = new IpMacEntry();
		ime2.setIp("192.168.1.1");
		ime2.setMac("aaaa");
		
		Integer t1 = new Integer(1);
		Integer t2 = new Integer(1);
		
		List<IpMacEntry> list = new ArrayList<IpMacEntry>();
		list.add(ime1);
		System.out.println(ime1.toString() + "   " + ime2.toString());
		System.out.println(ime1.equals(ime2));
		System.out.println(t1.equals(t2));
		System.out.println(list.contains(ime2));
	}
}
