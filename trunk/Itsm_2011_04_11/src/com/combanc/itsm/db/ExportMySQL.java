package com.combanc.itsm.db;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Formatter;
import java.util.StringTokenizer;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;

public class ExportMySQL extends BaseActionSupport implements
		ServletRequestAware, Export {
	private Connection conn;

	private final String SELECT_TABLE = "SELECT * FROM %s;";
	private final String DELETE_TABLE = "DELETE FROM %s;\n";
	private final String INSERT_TABLE = "INSERT INTO `%s`(%s) VALUES\n%s;\n";
	private final String COUNT_TABLE = "SELECT COUNT(*) as totalcount FROM %s;";

	public void exportData(String fileName, String tables, Connection conn,
			boolean withDeleteSQL) throws SQLException, IOException {

		this.conn = conn;
		try {
			StringBuffer allsql = new StringBuffer();
			StringTokenizer s = new StringTokenizer(tables, ";");
			while (s.hasMoreTokens()) {
				String table = s.nextToken();
				Formatter formatter = new Formatter();
				formatter.format(COUNT_TABLE, table);
				ResultSet rs = conn.createStatement().executeQuery(
						formatter.toString());
				int count = 0;
				if (rs.next()) {
					count = rs.getInt(1);
				}
				if (count > 0) {
					if (withDeleteSQL) {
						formatter = new Formatter();
						formatter.format(DELETE_TABLE, table);
						allsql.append(formatter.toString());
					}
					allsql.append(generateInsertSQL(table));
				}
			}
			if (!StringUtils.isEmpty(allsql.toString().trim())) {
				FileWriter fw = null;
				try {
					fw = new FileWriter(fileName);
					fw.write(allsql.toString());
				} finally {
					if (null != fw)
						fw.close();
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	private String generateInsertSQL(String table) {
		Formatter formatter = new Formatter();
		formatter.format(SELECT_TABLE, table);
		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(formatter.toString());
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			if (0 == cols) {
				return "";
			}
			String fields = getFields(rsmd);
			String allvalue = getValues(rs);
			formatter = new Formatter();
			formatter.format(INSERT_TABLE, table, fields, allvalue);
			String result = formatter.toString();
			return result;
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			return "";
		}
	}

	private String getValues(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			if (0 == cols) {
				return "";
			}
			StringBuffer values = new StringBuffer();
			boolean isFirst = true;
			while (rs.next()) {
				StringBuffer value = new StringBuffer();
				for (int i = 1; i <= cols; i++) {
					if (null != rs.getString(i))
						if (rsmd.getColumnType(i) == Types.VARCHAR
								|| rsmd.getColumnType(i) == Types.DATE
								|| rsmd.getColumnType(i) == Types.TIME
								|| rsmd.getColumnType(i) == Types.TIMESTAMP
								|| rsmd.getColumnType(i) == Types.CHAR
								|| rsmd.getColumnType(i) == Types.LONGVARCHAR)
							value.append("'" + rs.getString(i) + "'");
						else
							value.append(rs.getString(i));
					else
						value.append("NULL");
					if (i < cols) {
						value.append(",");
					}
				}
				if (isFirst) {
					values.append("(" + value + ")");
					isFirst = false;
				} else
					values.append(",\n(" + value + ")");
			}
			return values.toString();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			return "";
		}

	}

	private String getFields(ResultSetMetaData rsmd) {
		try {
			int cols = rsmd.getColumnCount();
			if (0 == cols) {
				return "";
			}
			StringBuffer fields = new StringBuffer();
			for (int i = 1; i <= cols; i++) {
				if (i == cols)
					fields.append(rsmd.getColumnName(i));
				else
					fields.append(rsmd.getColumnName(i) + ",");
			}

			return fields.toString();

		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			return "";
		}
	}

	public void backup() throws SQLException, IOException {
		Export export = new ExportMySQL();
		String url = "jdbc:mysql://192.168.10.53:3306/itsm";
		String username = "itsm";
		String password = "combanc";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(url, username,password);
			// export.exportData("c:/sql.sql","assets_base;assets_history_state;assets_info;assets_producer;assets_state;assets_type",conn,true);
			export.exportData("/upload","assets_base;assets_change;assets_config;assets_producer;assets_info;assets_type;config_record",conn, true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Connection conn
		// =SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		// export.exportData("c:/sql.sql",
		// "holiday;assetstype;assets_base;assets_history_state", conn,true);
	}

}