package com.combanc.itsm.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.type.Type;

/**
 * <p>
 * 俬俢帺摦惗惉僋儔僗
 * </p>
 * <p>
 * 乽擔晅亄斣崋乿偺僼僅乕儅僢僩偱俬俢傪惗惉偡傞
 * </p>
 * <p>
 * 儅僢僺儞僌僼傽僀儖拞偺僷儔儊乕僞偺rule偱擔晅偺僼僅乕儅僢僩 偍傛傃斣崋偺寘悢傪掕媊偱偒傞
 * </p>
 */
public class MfsIdGenerator implements IdentifierGenerator, Configurable {
	/**
	 * Logger for this class
	 */
	private static final Log _log = LogFactory.getLog(MfsIdGenerator.class);

	/** 僗僉乕儅 */
	private String schema = "";

	/** 僥乕僽儖柤 */
	private String table = "";

	/** 僇儔儉柤 */
	private String column = "";

	/** 惗惉儖乕儖 */
	private String rule = "";

	private StringBuffer sql = new StringBuffer();

	/**
	 * 僐儞僼傿僌儗乕僔儑儞
	 */
	public void configure(Type type, Properties params, Dialect d)
			throws MappingException {
		if (_log.isTraceEnabled()) {
			_log.trace("configure(Type, Properties, Dialect) - start");
		}

		table = params.getProperty("table");
		if (table == null)
			table = params.getProperty(PersistentIdentifierGenerator.TABLE);
		column = params.getProperty("column");
		if (column == null)
			column = params.getProperty(PersistentIdentifierGenerator.PK);
		// this.rule = PropertiesHelper.getString("rule", params, "");
		// this.schema = PropertiesHelper.getString("schema", params, "");
		if ("".equals(table) || "".equals(column) || "".equals(rule)) {
			throw new MappingException("parameter invalid.");
		}
		if (!"".equals(schema)) {
			table = schema + '.' + table;
		}
		sql.append(" select ");
		sql.append(column);
		sql.append(" from ");
		sql.append(table);
		sql.append(" order by ");
		sql.append(column + " desc ");

		if (_log.isTraceEnabled()) {
			_log.trace("configure(Type, Properties, Dialect) - end");
		}
	}

	/**
	 * 俬俢帺摦惗惉
	 */
	public synchronized Serializable generate(SessionImplementor session,
			Object object) throws HibernateException {
		if (_log.isTraceEnabled()) {
			_log.trace("generate(SessionImplementor, Object) - start");
		}

		if (!"".equals(sql.toString())) {
			Serializable returnSerializable;
			try {
				returnSerializable = getNext(session.connection());

				if (_log.isDebugEnabled()) {
					_log
							.debug("generate() -  : Serializable returnSerializable = "
									+ returnSerializable);

					_log.trace("generate(SessionImplementor, Object) - end");
				}
				return returnSerializable;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (_log.isTraceEnabled()) {
			_log.trace("generate(SessionImplementor, Object) - end");
		}
		return null;
	}

	private Serializable getNext(Connection conn) throws SQLException {

		if (_log.isTraceEnabled()) {
			_log.trace("getNext(Connection) - start");
			_log.trace("getNext() - sql:" + sql.toString());
		}

		String next = "";
		PreparedStatement st = conn.prepareStatement(sql.toString());
		ResultSet rs = null;
		try {
			rs = st.executeQuery();
			if (rs.next()) {
				next = rs.getString(1);
				if (rs.wasNull()) {
					next = constructId("");
				} else {
					next = constructId(next);
				}
			} else {
				next = constructId("");
			}
		} finally {
			if (rs != null)
				rs.close();
			st.close();
		}
		if (_log.isTraceEnabled()) {
			_log.trace("getNext(Connection) - end");
		}
		return next;
	}

	private String constructId(String next) {
		if (_log.isTraceEnabled()) {
			_log.trace("constructId(String) - start");
		}
		String result = "";
		long lNext = 1;
		String currentDate = "";

		StringTokenizer st = new StringTokenizer(rule, ",");
		while (st.hasMoreTokens()) {
			String temp = st.nextToken();
			if (temp.startsWith("*")) {
				SimpleDateFormat formatter = new SimpleDateFormat(temp
						.substring(1));
				currentDate = formatter
						.format(Calendar.getInstance().getTime());
				result += currentDate;
			} else if (temp.startsWith("#")) {
				int digitCount = Integer.parseInt(temp.substring(1));
				if (!"".equals(next)) {
					String prevDate = next.substring(0, currentDate.length());
					if (currentDate.equals(prevDate)) {
						next = next.substring(currentDate.length(), next
								.length());
						lNext = Long.parseLong(next) + 1;
					}
				}
				// result += ConvertUtil.getZeroString(lNext, digitCount);
			}
		}

		if (_log.isTraceEnabled()) {
			_log.trace("constructId(String) - end");
		}
		return result;
	}

}
