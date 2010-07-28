package crystal.service;

import java.io.IOException;
import java.sql.SQLException;

import crystal.hibernate.dao.BackupDAO;

public class BackupService {

	private BackupDAO backupDAO;

	public void setBackupDAO(BackupDAO backupDAO) {
		this.backupDAO = backupDAO;
	}

	public void export(String path, int type) throws SQLException, IOException {
		backupDAO.export(path, type);
	}

	public void importMysql(String fileName, String fileNameHis, int type)
			throws SQLException, IOException {
		backupDAO.importMysql(fileName, fileNameHis, type);
	}
}
