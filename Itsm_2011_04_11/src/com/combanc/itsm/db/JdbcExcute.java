package com.combanc.itsm.db;

import java.sql.*;
import java.util.*;

/**
 * 
 * @author combanc
 * @desc 底层数据库操作封装
 */
public class JdbcExcute {
	/**
	 * 获取链接
	 * 
	 * @return
	 */
	public Connection getCon() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");// 加载驱动
			String url = "jdbc:mysql://localhost:3306/itsm?useUnicode=true&characterEncoding=utf-8&useOldAliasMetadataBehavior=true";
			conn = DriverManager.getConnection(url, "root", "root");// 建立连接
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 得到查询结果集的字段结构
	 * 
	 * @param rs
	 * @return 字段结果集
	 */
	/*
	 * public HashMap getDataColl(ResultSet rs){ HashMap mapcoll = new
	 * HashMap(); try { ResultSetMetaData rsmd = rs.getMetaData(); for(int
	 * i=1;i<=rsmd.getColumnCount();i++){ String
	 * filedName=rsmd.getColumnName(i); System.out.println("*******"+filedName);
	 * //String fileType = rsmd.getColumnTypeName(i); mapcoll.put(filedName,
	 * ""); } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return mapcoll; }
	 */
	/**
	 * 获得查询数据,使用HashMap进行封装
	 * 
	 * @param sqlstr
	 *            查询的SQL语句
	 * @return
	 */
	public ArrayList selQuery(String sqlstr) {
		Connection connetcion = null;
		Statement stm = null;
		try {
			connetcion = getCon();
			connetcion.setAutoCommit(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ArrayList datalist = new ArrayList();
		ResultSet rs = null;
		if (sqlstr.equals(""))
			return null;
		try {
			stm = connetcion.createStatement();
			rs = stm.executeQuery(sqlstr);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colum = rsmd.getColumnCount();
			// HashMap map = getDataColl(rs);
			// Object[] keys = map.keySet().toArray();
			while (rs.next()) {
				HashMap dataSet = new HashMap();
				for (int i = 1; i <= colum; i++) {
					String strFieldName = rsmd.getColumnName(i).toLowerCase();
					// if(keys[i].toString().equals("COLUMN_NAME"))keys[i]="Field";
					// if(keys[i].toString().equals("COLUMN_TYPE"))keys[i]="Type";
					// if(keys[i].toString().equals("IS_NULLABLE"))keys[i]="Null";
					// if(keys[i].toString().equals("COLUMN_KEY"))keys[i]="Key";
					// if(keys[i].toString().equals("COLUMN_DEFAULT"))keys[i]="Default";
					// if(keys[i].toString().equals("EXTRA"))keys[i]="Extra";
					dataSet.put(strFieldName, rs.getObject(i));
				}
				datalist.add(dataSet);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null)
					stm.close();
				if (connetcion != null)
					connetcion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return datalist;
	}

	/**
	 * 执行数据库增删改操作
	 * 
	 * @param sqlstr
	 *            增删改的SQL语句
	 * @return 成功:0 失败:-1
	 */
	public int saveOrUpdate(String sqlstr) {
		Connection connetcion = null;
		try {
			connetcion = getCon();
			connetcion.setAutoCommit(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			connetcion.createStatement().execute(sqlstr);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				if (!connetcion.isClosed()) {
					connetcion.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JdbcExcute dbExcute = new JdbcExcute();
		System.out.println(dbExcute.selQuery("SELECT * FROM department"));
	}
}
