package com.combanc.itsm.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtility 
{
    private static final int BUFFER_SIZE = 16 * 1024 ;
	//1.根据文件的名字,来获取文件的扩展名;
	public static String getExtName(String fileName)
	{
		if(-1 == fileName.lastIndexOf("."))
		{
			return "";
			
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());
	}
	
	//产生随机生成的字符串名字;
	public static String randomNameFile(String fileName)
	{
		if(-1 == fileName.lastIndexOf("."))
		{
			return CharacterUtil.randomString(5);
		}
		return CharacterUtil.randomString(5) + fileName.substring(fileName.lastIndexOf("."),fileName.length());
	}
	public static void copy( File src,  File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				// 输入到缓冲流
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int i = 0;
				while ((i = in.read(buffer)) != -1) {
					out.write(buffer, 0, i);
					i = 0;
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
