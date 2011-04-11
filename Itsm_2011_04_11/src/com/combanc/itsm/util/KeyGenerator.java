package com.combanc.itsm.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class KeyGenerator {

	private Logger logger = Logger.getLogger(KeyGenerator.class);
	private Connection conn;
	private String tableName;
	private String columnName;
	private String head;
	private int numCount;
	private String lockString;
	private String temp;

	/**
	 * @param tableName
	 *            - 表名
	 * @param columnName
	 *            - code 对应 字段名
	 * @param head
	 *            - 缺少最后流水号的 code 前端
	 * @param numCount
	 *            - 最后流水号数字的个数
	 * @param lockString
	 *            - 对应于 LOCK_TABLE表中的字符串名
	 */

	// example used in xxxCreateBO
	// KeyGenerator keyGenerator= new
	// KeyGenerator(getSession().connection(),"TS_GXCPRD","APP_ID","2005210204A",4,"TS_GXCPRD");
	// String key=keyGenerator.getKey();

	public KeyGenerator(Connection conn, String tableName, String columnName,
			String head, int numCount, String lockString) {
		this.conn = conn;
		this.tableName = tableName;
		this.columnName = columnName;
		this.temp = head;
		if (columnName.equals("TASK_CODE")) {
			this.head = head.substring(0, 4);
		} else {
			this.head = head;
		}
		this.numCount = numCount;
		this.lockString = lockString;

	}

	private String computeNewCode(String maxCode, String head, int numCount) {

		String newCode = "";
		if (maxCode != null) {
			int i = head.length();
			int j = maxCode.length();
			int k = j - i;

			if (k > 0) {
				String numPart = maxCode.substring(i, j);
				int theInt = new Integer(numPart).intValue();
				theInt++;
				String numString = new Integer(theInt).toString();
				k = k - numString.length();
				String temp0 = "";
				for (; k > 0; k--) {
					temp0 = temp0 + "0";
				}
				numString = temp0 + numString;
				newCode = head + numString;
			}
		} else {
			String temp0 = "";
			for (int k = numCount - 1; k > 0; k--) {
				temp0 = temp0 + "0";
			}
			newCode = head + temp0 + "1";
		}
		return newCode;
	}

	public String getKey2() {
		String oracleLockStr = " for update ";
		String sqlServerLockStr = " with (holdlock) ";

		String sql1 = " SELECT * FROM " + "LOCK_TABLE ";
		// 用来锁定表中记录
		// 如果是SQLServer数据库用 with (holdlock)，放在where条件前面
		// SQLServer 例子：select * from LOCK_TABLE with (holdlock) where
		// TABLE_NAME like 'aaa%';
		// 如果是oracle数据库用 for update,放在where条件后面
		// Oracle 例子： select * from LOCK_TABLE where TABLE_NAME like 'aaa%' for
		// update;

		sql1 = sql1 + " WHERE " + "TABLE_NAME" + " LIKE '" + lockString.trim()
				+ "'";
		sql1 = sql1 + oracleLockStr;
		System.out.println("KeyGenerator sql=" + sql1);
		String sql2 = " SELECT MAX(" + columnName + ") AS A FROM " + tableName;
		sql2 = sql2 + " WHERE " + columnName + " LIKE '" + head.trim()
				+ "___' ";
		/*
		 * if (columnName.equals("TASK_CODE")) { sql2 =
		 * sql2+" and TASK_DEPARTMENT_SYMBOL = '"+ temp.substring(4, 6) +"'"; }
		 */
		System.out.println("KeyGenerator sql=" + sql2);
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		Statement stmt = null;
		ResultSet rset1 = null;
		String maxCode = "";
		String newCode = "";

		try {
			pstm1 = conn.prepareStatement(sql1);
			pstm1.executeQuery();
			pstm2 = conn.prepareStatement(sql2);
			rset1 = pstm2.executeQuery();
			rset1.next();
			maxCode = rset1.getString("A");
			Integer max = Integer.parseInt(maxCode.substring(4, 7)) + 1;
			// newCode=computeNewCode(maxCode,head,numCount);
			newCode = temp + max.toString();
			logger.info("newCode:" + newCode);
			System.out.println("newCode:" + newCode);
			return newCode;

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rset1 != null)
					rset1.close();
			} catch (SQLException e1) {
			}

			try {
				if (pstm1 != null)
					pstm1.close();
				if (pstm2 != null)
					pstm2.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e1) {
			}
		}

	}

	public String getKey() {

		String oracleLockStr = " for update ";
		String sqlServerLockStr = " with (holdlock) ";

		String sql1 = " SELECT * FROM " + "LOCK_TABLE ";
		// 用来锁定表中记录
		// 如果是SQLServer数据库用 with (holdlock)，放在where条件前面
		// SQLServer 例子：select * from LOCK_TABLE with (holdlock) where
		// TABLE_NAME like 'aaa%';
		// 如果是oracle数据库用 for update,放在where条件后面
		// Oracle 例子： select * from LOCK_TABLE where TABLE_NAME like 'aaa%' for
		// update;

		sql1 = sql1 + " WHERE " + "TABLE_NAME" + " LIKE '" + lockString.trim()
				+ "'";
		sql1 = sql1 + oracleLockStr;
		System.out.println("KeyGenerator sql=" + sql1);
		String sql2 = " SELECT MAX(" + columnName + ") AS A FROM " + tableName;
		sql2 = sql2 + " WHERE " + columnName + " LIKE '" + head.trim() + "%' ";
		if (columnName.equals("TASK_CODE")) {
			sql2 = sql2 + " and TASK_DEPARTMENT_SYMBOL = '"
					+ temp.substring(4, 6) + "'";
		}
		System.out.println("KeyGenerator sql=" + sql2);
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		Statement stmt = null;
		ResultSet rset1 = null;
		String maxCode = "";
		String newCode = "";

		try {
			pstm1 = conn.prepareStatement(sql1);
			pstm1.executeQuery();
			pstm2 = conn.prepareStatement(sql2);
			rset1 = pstm2.executeQuery();
			rset1.next();
			maxCode = rset1.getString("A");

			newCode = computeNewCode(maxCode, head, numCount);

			logger.info("newCode:" + newCode);
			System.out.println("newCode:" + newCode);
			return newCode;

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;

		} finally {
			try {
				if (rset1 != null)
					rset1.close();
			} catch (SQLException e1) {
			}

			try {
				if (pstm1 != null)
					pstm1.close();
				if (pstm2 != null)
					pstm2.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException e1) {
			}
		}

	}

}
