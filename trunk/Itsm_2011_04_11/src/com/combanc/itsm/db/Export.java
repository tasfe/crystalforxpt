package com.combanc.itsm.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface Export {

	public void exportData(String fileName, String tables, Connection conn,boolean withDeleteSQL) throws SQLException, IOException;
}
