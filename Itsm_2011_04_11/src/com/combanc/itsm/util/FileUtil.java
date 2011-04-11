package com.combanc.itsm.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.regex.Pattern;

public class FileUtil {
	public static boolean writeText(String fileName, String content) {
		return writeText(fileName, content, "GBK");
	}

	public static boolean writeText(String fileName, String content, String encoding) {
		try {
			writeByte(fileName, content.getBytes(encoding));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static byte[] readByte(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(fileName);
			byte[] r = new byte[fis.available()];
			fis.read(r);
			fis.close();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readByte(File f) {
		try {
			/* ${_ZVING_LICENSE_CODE_} */

			FileInputStream fis = new FileInputStream(f);
			byte[] r = readByte(fis);
			fis.close();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readByte(InputStream is) {
		try {
			byte[] r = new byte[is.available()];
			is.read(r);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean writeByte(String fileName, byte[] b) {
		try {
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName));
			fos.write(b);
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean writeByte(File f, byte[] b) {
		try {
			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(f));
			fos.write(b);
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String readText(File f) {
		return readText(f, "GBK");
	}

	public static String readText(File f, String encoding) {
		try {
			InputStream is = new FileInputStream(f);
			String str = readText(is, encoding);
			is.close();
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readText(InputStream is, String encoding) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readText(String fileName) {
		return readText(fileName, "GBK");
	}

	public static String readText(String fileName, String encoding) {
		File file=new File(fileName);
		if(file==null || !file.exists()){
			return null;
		}
		try {
			InputStream is = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(is, encoding));
			StringBuffer sb = new StringBuffer();
			String line;
			int c = br.read();
			if (!encoding.equalsIgnoreCase("utf-8") || c != 65279) {
				sb.append((char) c);
			}
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			br.close();
			is.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String readURLText(String urlPath) {
		return readURLText(urlPath, "GBK");
	}

	/**
	 * ���urlPath��ȡ��ҳ���
	 * 
	 * @param urlPath
	 * @param encoding
	 * @return
	 */
	public static String readURLText(String urlPath, String encoding) {
		try {
			URL url = new URL(urlPath);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), encoding));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
			in.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����·�����ļ������ļ��У���ɾ��
	 * 
	 * @param path
	 * @return
	 */
	public static boolean delete(String path) {
		File file = new File(path);
		return delete(file);
	}

	/**
	 * ����·�����ļ������ļ��У���ɾ��
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delete(File file) {
		if (!file.exists()) {
			LogUtil.getLogger().warn("�ļ����ļ��в����ڣ�" + file);
			return false;
		}
		if (file.isFile()) {
			return file.delete();
		} else {
			return FileUtil.deleteDir(file);
		}
	}

	/**
	 * ɾ���ļ��У���ɾ���Լ�����
	 * 
	 * @param path
	 * @return boolean
	 */
	private static boolean deleteDir(File dir) {
		try {
			return deleteFromDir(dir) && dir.delete(); // ��ɾ������������������ɾ����ļ���
		} catch (Exception e) {
			LogUtil.getLogger().warn("ɾ���ļ��в�����");
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * �����ļ���
	 * 
	 * @param path
	 * @return
	 */
	public static boolean mkdir(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return true;
	}

	/**
	 * �ļ���֧��ʹ��������ʽ���ļ�·����֧��������ʽ��
	 */
	public static boolean deleteEx(String fileName) {
		int index1 = fileName.lastIndexOf("\\");
		int index2 = fileName.lastIndexOf("/");
		index1 = index1 > index2 ? index1 : index2;
		String path = fileName.substring(0, index1);
		String name = fileName.substring(index1 + 1);
		File f = new File(path);
		if (f.exists() && f.isDirectory()) {
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (Pattern.matches(name, files[i].getName())) {
					files[i].delete();
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * ɾ���ļ�������������ļ�,����ɾ���Լ�����
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteFromDir(String dirPath) {
		File file = new File(dirPath);
		return deleteFromDir(file);
	}

	/**
	 * ɾ���ļ�������������ļ�,����ɾ���Լ�����
	 * 
	 * @param file
	 * @return
	 */
	public static boolean deleteFromDir(File dir) {
		if (!dir.exists()) {
			LogUtil.getLogger().warn("�ļ��в����ڣ�" + dir);
			return false;
		}
		if (!dir.isDirectory()) {
			LogUtil.getLogger().warn(dir + "�����ļ���");
			return false;
		}
		File[] tempList = dir.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (!delete(tempList[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean copy(String oldPath, String newPath, FileFilter filter) {
		File oldFile = new File(oldPath);
		File[] oldFiles = oldFile.listFiles(filter);
		boolean flag = true;
		if (oldFiles != null) {
			for (int i = 0; i < oldFiles.length; i++) {
				if (!copy(oldFiles[i], newPath + "/" + oldFiles[i].getName())) {
					flag = false;
				}
			}
		}
		return flag;
	}

	public static boolean copy(String oldPath, String newPath) {
		File oldFile = new File(oldPath);
		return copy(oldFile, newPath);
	}

	public static boolean copy(File oldFile, String newPath) {
		if (!oldFile.exists()) {
			LogUtil.getLogger().warn("�ļ������ļ��в����ڣ�" + oldFile);
			return false;
		}
		if (oldFile.isFile()) {
			return copyFile(oldFile, newPath);
		} else {
			return copyDir(oldFile, newPath);
		}
	}

	/**
	 * ���Ƶ����ļ�
	 * 
	 * @param oldFile
	 * @param newPath
	 * @return boolean
	 */
	private static boolean copyFile(File oldFile, String newPath) {
		if (!oldFile.exists()) { // �ļ�����ʱ
			LogUtil.getLogger().warn("�ļ������ڣ�" + oldFile);
			return false;
		}
		if (!oldFile.isFile()) { // �ļ�����ʱ
			LogUtil.getLogger().warn(oldFile + "�����ļ�");
			return false;
		}
		try {
			int byteread = 0;
			InputStream inStream = new FileInputStream(oldFile); // ����ԭ�ļ�
			FileOutputStream fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			fs.close();
			inStream.close();
		} catch (Exception e) {
			LogUtil.getLogger().warn("���Ƶ����ļ�" + oldFile.getPath() + "������");
			// e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * ��������ļ�������
	 * 
	 * @param oldDir
	 * @param newPath
	 * @return boolean
	 */
	private static boolean copyDir(File oldDir, String newPath) {
		if (!oldDir.exists()) { // �ļ�����ʱ
			LogUtil.getLogger().info("�ļ��в����ڣ�" + oldDir);
			return false;
		}
		if (!oldDir.isDirectory()) { // �ļ�����ʱ
			LogUtil.getLogger().info(oldDir + "�����ļ���");
			return false;
		}
		try {
			(new File(newPath)).mkdirs(); // ����ļ��в����� ��b���ļ���
			File[] files = oldDir.listFiles();
			File temp = null;
			for (int i = 0; i < files.length; i++) {
				temp = files[i];
				if (temp.isFile()) {
					if (!FileUtil.copyFile(temp, newPath + "/" + temp.getName())) {
						return false;
					}
				} else if (temp.isDirectory()) {// ��������ļ���
					if (!FileUtil.copyDir(temp, newPath + "/" + temp.getName())) {
						return false;
					}
				}
			}
			return true;
		} catch (Exception e) {
			LogUtil.getLogger().info("��������ļ������ݲ�����");
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * �ƶ��ļ���ָ��Ŀ¼
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static boolean move(String oldPath, String newPath) {
		return copy(oldPath, newPath) && delete(oldPath);
	}

	/**
	 * �ƶ��ļ���ָ��Ŀ¼
	 * 
	 * @param oldFile
	 * @param newPath
	 */
	public static boolean move(File oldFile, String newPath) {
		return copy(oldFile, newPath) && delete(oldFile);
	}

	public static void serialize(Serializable obj, String fileName) {
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(obj);
			s.flush();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] serialize(Serializable obj) {
		try {
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			ObjectOutputStream s = new ObjectOutputStream(b);
			s.writeObject(obj);
			s.flush();
			s.close();
			return b.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object unserialize(String fileName) {
		try {
			FileInputStream in = new FileInputStream(fileName);
			ObjectInputStream s = new ObjectInputStream(in);
			Object o = s.readObject();
			s.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object unserialize(byte[] bs) {
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(bs);
			ObjectInputStream s = new ObjectInputStream(in);
			Object o = s.readObject();
			s.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ��һ��Mapx���������л�,��ֵֻ��Ϊ�ַ�<br>
	 */
	public static byte[] mapToBytes(Mapx map) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			Object[] ks = map.keyArray();
			Object[] vs = map.valueArray();
			for (int i = 0; i < map.size(); i++) {
				String k = String.valueOf(ks[i]);
				Object v = vs[i];
				if (v == null) {
					bos.write(new byte[] { 0 });
				} else if (v instanceof String) {
					bos.write(new byte[] { 1 });
				} else if (v instanceof Long) {
					bos.write(new byte[] { 2 });
				} else if (v instanceof Integer) {
					bos.write(new byte[] { 3 });
				} else if (v instanceof Boolean) {
					bos.write(new byte[] { 4 });
				} else if (v instanceof Date) {
					bos.write(new byte[] { 5 });
				} else if (v instanceof Mapx) {
					bos.write(new byte[] { 6 });
				} else if (v instanceof Serializable) {
					bos.write(new byte[] { 7 });
				} else {
					throw new RuntimeException("δ֪���������:" + v.getClass().getName());
				}
				byte[] bs = k.getBytes();
				bos.write(NumberUtil.toBytes(bs.length));
				bos.write(bs);
				if (v == null) {
					continue;
				} else if (v instanceof String) {
					bs = v.toString().getBytes();
					bos.write(NumberUtil.toBytes(bs.length));
					bos.write(bs);
				} else if (v instanceof Long) {
					bos.write(NumberUtil.toBytes(((Long) v).longValue()));
				} else if (v instanceof Integer) {
					bos.write(NumberUtil.toBytes(((Integer) v).intValue()));
				} else if (v instanceof Boolean) {
					bos.write(((Boolean) v).booleanValue() ? 1 : 0);
				} else if (v instanceof Date) {
					bos.write(NumberUtil.toBytes(((Date) v).getTime()));
				} else if (v instanceof Mapx) {
					byte[] arr = mapToBytes((Mapx) v);
					bos.write(NumberUtil.toBytes(arr.length));
					bos.write(arr);
				} else if (v instanceof Serializable) {
					byte[] arr = serialize((Serializable) v);
					bos.write(NumberUtil.toBytes(arr.length));
					bos.write(arr);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	public static Mapx bytesToMap(byte[] arr) {
		ByteArrayInputStream bis = new ByteArrayInputStream(arr);
		int b = -1;
		Mapx map = new Mapx();
		byte[] kbs = new byte[4];
		byte[] vbs = null;
		try {
			while ((b = bis.read()) != -1) {
				bis.read(kbs);
				int len = NumberUtil.toInt(kbs);
				vbs = new byte[len];
				bis.read(vbs);
				String k = new String(vbs);
				Object v = null;
				if (b == 1) {
					bis.read(kbs);
					len = NumberUtil.toInt(kbs);
					vbs = new byte[len];
					bis.read(vbs);
					v = new String(vbs);
				} else if (b == 2) {
					vbs = new byte[8];
					bis.read(vbs);
					v = new Long(NumberUtil.toLong(vbs));
				} else if (b == 3) {
					vbs = new byte[4];
					bis.read(vbs);
					v = new Integer(NumberUtil.toInt(vbs));
				} else if (b == 4) {
					int i = bis.read();
					v = new Boolean(i == 1 ? true : false);
				} else if (b == 5) {
					vbs = new byte[8];
					bis.read(vbs);
					v = new Date(NumberUtil.toLong(vbs));
				} else if (b == 6) {
					bis.read(kbs);
					len = NumberUtil.toInt(kbs);
					vbs = new byte[len];
					bis.read(vbs);
					v = bytesToMap(vbs);
				} else if (b == 7) {
					bis.read(kbs);
					len = NumberUtil.toInt(kbs);
					vbs = new byte[len];
					bis.read(vbs);
					v = unserialize(vbs);
				}
				map.put(k, v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		// Mapx map = new Mapx();
		// map.put("URL", "http://test.suncars.com00");
		// map.put("NO", new Long(34234234));
		byte[] bs =
				new byte[] { 1, 0, 0, 0, 3, 85, 114, 108, 0, 0, 0, 22, 104, 116, 116, 112, 58, 47, 47, 110, 101, 119, 115, 46,
						99, 104, 101, 115, 104, 105, 46, 99, 111, 109, 0, 0, 0, 6, 82, 101, 102, 85, 114, 108, 0, 0, 0, 7, 99,
						104, 97, 114, 115, 101, 116, 4, 0, 0, 0, 13, 105, 115, 84, 101, 120, 116, 67, 111, 110, 116, 101, 110,
						116, 0, 0, 0, 0, 16, 108, 97, 115, 116, 109, 111, 100, 105, 102, 105, 101, 100, 68, 97, 116, 101, 2, 0,
						0, 0, 16, 108, 97, 115, 116, 68, 111, 119, 110, 108, 111, 97, 100, 84, 105, 109, 101, 0, 0, 0, 0, 0, 0,
						0, 0, 3, 0, 0, 0, 5, 108, 101, 118, 101, 108, 0, 0, 0, 0, 0, 0, 0, 12, 69, 114, 114, 111, 114, 77, 101,
						115, 115, 97, 103, 101, 4, 0, 0, 0, 9, 105, 115, 80, 97, 103, 101, 85, 114, 108, 0, 0, 0, 0, 4, 102, 111,
						114, 109 };
		Mapx map = bytesToMap(bs);
		System.out.println(map);
	}
}
