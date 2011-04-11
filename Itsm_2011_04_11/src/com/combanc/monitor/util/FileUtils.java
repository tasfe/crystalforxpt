package com.combanc.monitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import javax.swing.JOptionPane;

public class FileUtils {
	/**
	 * 
	 * Description:取得当前类所在的文件
	 * 
	 * @param clazz
	 * @return
	 * @mail sunyujia@yahoo.cn
	 * @since：Sep 21, 2008 12:32:10 PM
	 */
	public static File getClassFile(Class clazz) {
		URL path = clazz.getResource(clazz.getName().substring(
				clazz.getName().lastIndexOf(".") + 1)
				+ ".class");
		if (path == null) {
			String name = clazz.getName().replaceAll("[.]", "/");
			path = clazz.getResource("/" + name + ".class");
		}
		return new File(path.getFile());
	}

	/**
	 * 
	 * Description:同getClassFile 解决中文编码问题
	 * 
	 * @param clazz
	 * @return
	 * @mail sunyujia@yahoo.cn
	 * @since：Sep 21, 2008 1:10:12 PM
	 */
	public static String getClassFilePath(Class clazz) {
		try {
			return java.net.URLDecoder.decode(getClassFile(clazz)
					.getAbsolutePath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * Description:取得当前类所在的ClassPath目录
	 * 
	 * @param clazz
	 * @return
	 * @mail sunyujia@yahoo.cn
	 * @since：Sep 21, 2008 12:32:27 PM
	 */
	public static File getClassPathFile(Class clazz) {
		File file = getClassFile(clazz);
		for (int i = 0, count = clazz.getName().split("[.]").length; i < count; i++)
			file = file.getParentFile();
		if (file.getName().toUpperCase().endsWith(".JAR!")) {
			file = file.getParentFile();
		}
		return file;
	}

	/**
	 * 
	 * Description: 同getClassPathFile 解决中文编码问题
	 * 
	 * @param clazz
	 * @return
	 * @mail sunyujia@yahoo.cn
	 * @since：Sep 21, 2008 1:10:37 PM
	 */
	public static String getClassPath(Class clazz) {
		try {
			return java.net.URLDecoder.decode(getClassPathFile(clazz)
					.getAbsolutePath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(getClassFilePath(FileUtils.class));
		System.out.println(getClassPath(FileUtils.class));
	}
	
	public static boolean copyFilesByType(String fileExtName, String sourcePath,
			String targetPath) {
		if (fileExtName == null)
			return false;
		if (samePath(sourcePath, targetPath))
			return false;
		File path = new File(sourcePath);
		String[] files = path.list();
		if (files == null)
			return false;
		if (files.length < 1)
			return false;

		String tmp;
		int index;
		for (int i = 0; i < files.length; i++) {
			tmp = files[i];
			index = tmp.lastIndexOf(".");
			if (index == -1)
				continue;
			tmp = tmp.substring(index + 1, tmp.length());
			if (!tmp.equals(fileExtName))
				continue;
			if (!copyFile(sourcePath, files[i], targetPath))
				return false;
		}
		return true;
	}
	
	private static boolean copyFile(String sourcePath, String fileName,
			String targetPath) {
		try {
			File file_in = new File(sourcePath + fileName);
			File file_out = new File(targetPath + fileName);
			FileInputStream ins = new FileInputStream(file_in);
			FileOutputStream outs = new FileOutputStream(file_out);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = ins.read(bytes)) != -1)
				outs.write(bytes, 0, c);
			ins.close();
			outs.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "文件复制错误：" + ex);
			return false;
		}
		return true;
	}

	private static boolean samePath(String sourcePath, String targetPath) {
		if (sourcePath.equals(targetPath)) {
			JOptionPane.showMessageDialog(null, "错误：源路径与目标路径相同。");
			return true;
		}
		return false;
	}
}
