package com.combanc.itsm.db;

//操作业务类

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class DbOper {
	private String tableName;
	private String dbName = "itsm";
	private String _username = "root";
	private String _password = "root";
	private String _host = "localhost";
	private String _port = "3306";

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String get_username() {
		return _username;
	}

	public void set_username(String _username) {
		this._username = _username;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}

	public String get_host() {
		return _host;
	}

	public void set_host(String _host) {
		this._host = _host;
	}

	public String get_port() {
		return _port;
	}

	public void set_port(String _port) {
		this._port = _port;
	}

	/**
	 * 查询指定数据库的所有表名
	 * 
	 * @param dbName
	 *            数据库名称
	 * @return ArrayList
	 */
	public ArrayList selAllTableInfo(String dbName) {
		// 返回的结果集
		ArrayList<HashMap> resultList = new ArrayList<HashMap>();
		JdbcExcute exec = new JdbcExcute();
		String sql = "select table_name, table_type,engine from information_schema.tables where table_schema='"
				+ this.dbName + "' order by table_name asc";
		resultList = exec.selQuery(sql);
		return resultList;
	}

	/**
	 * 查询指定表的表结构
	 * 
	 * @param tableName
	 *            表名
	 * @return ArrayList
	 */
	public ArrayList selTableStructure(String tableName) {
		ArrayList<HashMap> resultList = new ArrayList<HashMap>();
		JdbcExcute exec = new JdbcExcute();
		String sql = "desc " + tableName;
		resultList = exec.selQuery(sql);
		return resultList;
	}

	/**
	 * 根据路径生成备份数据库的Shell字符串
	 * 
	 * @param targetName
	 *            要备份的对象名：只能为表名和数据库名称
	 * @return 实际执行的shell命令
	 */
	public String getBackupShellString(String targetName) {
		String basepath = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String backFile = "";
		String database_tools_path = basepath.substring(6,
				basepath.length() - 4)
				+ "dbtools/";// 备份工具路径
		if (targetName.equals(this.dbName)) {// 若要备份整个数据库
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			backFile = "F:/database/" + targetName + "_"
					+ sdf.format(new Date()) + ".sql";// 要备份的文件
			targetName = "";
		} else {
			backFile = "F:/tables/" + targetName + ".sql";
		}
		String OSType = System.getProperty("os.name");
		String shellStr = "";
		if (OSType.indexOf("Windows") != -1) {
			shellStr = database_tools_path + "mysqldumpwin.exe -h "
					+ this._host + " -P" + this._port + "   -u"
					+ this._username + " -p" + this._password
					+ " --result-file=" + backFile
					+ " --default-character-set=gbk " + this.dbName + " "
					+ targetName;
		} else {
			shellStr = database_tools_path + "mysqldump -h " + this._host
					+ " -P" + this._port + "   -u" + this._username + " -p"
					+ this._password + " --result-file=" + backFile
					+ " --default-character-set=gbk " + this.dbName + " "
					+ targetName;
		}
		System.out.print("##############" + shellStr);
		return shellStr;
	}

	/**
	 * 备份数据库
	 * 
	 * @param targetName
	 *            要备份的对象名：只能为表名和数据库名称
	 * @return 成功:TRUE 失败:FALSE 备份表直接备份在指定文件夹，备份库则按日期备份到指定的文件夹
	 * 
	 */
	public boolean backup(String targetName) {

		String backFilePath = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String backDirString = "F:/database/";// 默认备份库
		try {
			if (!targetName.equals(this.dbName)) {// 备份表
				File tableDir = new File("F:/tables/");
				if (!tableDir.exists()) {// 存放表的文件夹不存在
					tableDir.mkdir();
					System.out.println("--------->" + tableDir);
				}
				backFilePath = "F:/tables/" + targetName + ".sql";// 要备份的文件

			} else {// 备份库
				backFilePath = "F:/database/" + targetName + "_"
						+ sdf.format(new Date()) + ".sql";// 要备份的文件
				File backDir = new File(backDirString);
				if (!backDir.exists()) {// 存放库的文件夹不存在
					backDir.mkdir();
				}
			}
			// 判断要备份的文件是否已存在
			File backFile = new File(backFilePath);
			if (backFile.exists()) {
				backFile.delete();
			}
			Runtime runt = Runtime.getRuntime();
			// Process proc =
			// runt.exec("D:/myec6_tomcat/webapps/cms/dbtools/mysqldumpwin.exe
			// -h 127.0.0.1
			// -P3306 -uroot -p123 --result-file=F:/tables/menuinfo.sql
			// --default-character-set=gbk bizoss_cms menuinfo");
			Process proc = runt.exec(getBackupShellString(targetName));
			int tag = proc.waitFor();// 等待进程终止
			if (tag == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 恢复数据库
	 * 
	 * @param targetName
	 *            要备份的对象名：只能为表名和数据库名称
	 * @return 成功:TRUE 失败:FALSE
	 */
	public boolean restore(String targetName) {
		try {
			Runtime runt = Runtime.getRuntime();
			Process proc;
			String cmdtext = this.getRestoreShellString(targetName);
			if (System.getProperty("os.name").indexOf("Windows") != -1) {
				String[] cmd = { "cmd", "/c", cmdtext };
				proc = runt.exec(cmd);
			} else {
				String[] cmd = { "sh", "-c", cmdtext };
				proc = runt.exec(cmd);
			}
			System.out.println(cmdtext);
			int tag = proc.waitFor();// 等待进程终止
			System.out.println("进程返回值为tag:" + tag);
			if (tag == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据路径生成恢复数据库的Shell字符串
	 * 
	 * @param targetName
	 *            targetName 要还原的对象名：只能为表名和数据库名称
	 * @return 恢复数据时实际执行的shell
	 */
	public String getRestoreShellString(String targetName) {
		String basepath = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String database_tools_path = basepath.substring(6,
				basepath.length() - 4)
				+ "dbtools/";// 备份工具路径
		String backFile = "";// 已备份的文件
		if (targetName.indexOf(this.dbName) == -1) {// 还原表
			backFile = "f:/tables/" + targetName + ".sql";
		} else {// 还原库
			backFile = "f:/database/" + targetName;
		}
		String OSType = System.getProperty("os.name");
		String shellStr = "";
		if (OSType.indexOf("Windows") != -1) {
			shellStr = database_tools_path + "mysqlwin.exe -h " + this._host
					+ " -P" + this._port + "   -u" + this._username + " -p"
					+ this._password + " --default-character-set=gbk "
					+ this.dbName + " < " + backFile;
		} else {
			shellStr = database_tools_path + "mysql -h " + this._host + " -P"
					+ this._port + "   -u" + this._username + " -p"
					+ this._password + "   --default-character-set=gbk "
					+ this.dbName + " < " + backFile;
		}
		return shellStr;
	}

	public static void main(String[] args) {
		DbOper db = new DbOper();
		System.out.println(db.selAllTableInfo("itsm"));
		System.out.println(db.selTableStructure("bookinfo"));
		System.out.println(db.backup("holiday"));
		System.out.println(System.getProperty("os.name"));
		System.out.println(db.restore("holiday"));

	}

}
