package com.combanc.itsm.util;

import static java.io.File.separator;

import javax.servlet.http.HttpServletRequest;

public class FileStorage 
{
	public static String FILE_STORAGE = "upload";
	
	public static String SYSTEM_NOTICE_STORAGE = "systemNoticeStorage";
	
	public static String DOCUMENT_STORAGE="documentStorage";
	
	//获得硬盘上某一模块对应的路径;通用的返回路径的方法;
	public static String getFileStorage(HttpServletRequest request,String desc)
	{
		return request.getSession().getServletContext().getRealPath(FILE_STORAGE + separator + desc);
	}
	
	public static String getSystemNoticeStorage(HttpServletRequest request)
	{
		return getFileStorage(request,SYSTEM_NOTICE_STORAGE);
		
	}
	public static String getUpLoadPath(HttpServletRequest request)
	{
		return request.getSession().getServletContext().getRealPath(FILE_STORAGE);
	}
	public static String getDocumentPath(HttpServletRequest request)
	{
		return getFileStorage(request,DOCUMENT_STORAGE);
	}
}
