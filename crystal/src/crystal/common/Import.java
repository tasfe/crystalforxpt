package crystal.common;

import java.io.IOException;
import java.sql.Connection;

public interface Import {
	public void importFile(String fileName, Connection conn) throws IOException;
}