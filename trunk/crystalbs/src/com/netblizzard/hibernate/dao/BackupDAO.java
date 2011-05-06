package com.netblizzard.hibernate.dao;

import com.netblizzard.common.core.dao.BaseHibernateDAO;
import com.netblizzard.hibernate.pojo.Material;

public class BackupDAO extends BaseHibernateDAO<Material, Integer> {
	final int CONF = 1;
	final int HISTORY = 2;
/*
	public void export(String path) throws SQLException, IOException {
		Export export = new ExportMySQL();
		SessionFactory o = this.getSessionFactory();
		DataSource ds = SessionFactoryUtils.getDataSource(o);
		Connection conn = ds.getConnection();
		export.exportData(path + "\\crystal.sql",
				"material_category;material;material_buy;"
						+ ";product_category;product;product_sell", conn, true);
	}

	public void importMysql(String fileName, String fileNameHis, int type)
			throws SQLException, IOException {
		Import importMysql = new ImportMySQL();
		SessionFactory o = this.getSessionFactory();
		DataSource ds = SessionFactoryUtils.getDataSource(o);
		Connection conn = ds.getConnection();
		importMysql.importFile(fileName, conn);
	}

	public static BackupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BackupDAO) ctx.getBean("BackupDAO");
	}
*/
}
