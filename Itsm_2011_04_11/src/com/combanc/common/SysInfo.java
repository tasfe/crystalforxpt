package com.combanc.common;

import java.util.ArrayList;
import java.util.List;

import com.combanc.common.LicenseInfo;
import com.combanc.itsm.util.DateUtil;

public class SysInfo {

	public static List<String[]> LicenseInfo(){
		List<String[]> license = new ArrayList<String[]>();
		//license.add(new String[] { "��Ȩ��授权给", LicenseInfo.getName() });
		//license.add(new String[] { "��Ч����有效期至", DateUtil.toString(LicenseInfo.getEndDate(), "yyyy-MM-dd HH:mm:ss") });
		//license.add(new String[] { "��Ȩ�û���", String.valueOf(new Long(LicenseInfo.getUserLimit())) });
		//license.add(new String[] { "��Ȩ��Ʒ����ITSMitSSSSSRE", LicenseInfo.getProduct() });
		//license.add(new String[] { "��ȨMACַ", LicenseInfo.getMacAddress() });
		license.add(new String[]{"授权给",LicenseInfo.getName()});
		license.add(new String[]{"有效期至",DateUtil.toString(LicenseInfo.getEndDate(), "yyyy-MM-dd HH:mm:ss")});
		license.add(new String []{"授权产品代码","ITSM1.0.1"});
		license.add(new String []{"授权MAC地址",LicenseInfo.getMacAddress()});
		return license;
	}
	
	public static List<String[]> SystemInfo(){
		List<String[]> license = new ArrayList<String[]>();
		license.add(new String[] { "应用程序名称", Config.getAppCode() + "(" + Config.getAppName() + ")" });
		//license.add(new String[] { "������ʱ��", DateUtil.toString(new Date(Long.parseLong(Config.getValue("App.Uptime"))), "yyyy-MM-dd HH:mm:ss") });
		license.add(new String[] { "操作系统名称", Config.getValue("System.OSName") });
		license.add(new String[] { "操作系统版本", Config.getValue("System.OSVersion") });
		license.add(new String[] { "操作系统补丁", Config.getValue("System.OSPatchLevel") });
		license.add(new String[] { "JDK厂商", Config.getValue("System.JavaVendor") });
		license.add(new String[] { "JDK版本", Config.getValue("System.JavaVersion") });
		license.add(new String[] { "JDK主目录", Config.getValue("System.JavaHome") });
		/*
		license.add(new String[] { "Servlet�������", Config.getValue("System.ContainerInfo") });
		license.add(new String[] { "��Servlet������û���", Config.getValue("System.OSUserName") });
		license.add(new String[] {
				"JDK�����ڴ���/��������",
				Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M/" + Runtime.getRuntime().maxMemory() / 1024
						/ 1024 + "M" });
		license.add(new String[] { "�ļ�����", Config.getValue("System.FileEncode") });
		*/
		return license;
	}
	
	public final static void main(String[] args) throws Exception {
		SystemInfo();
	}
		
}
