package crystal.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ImportMySQL implements Import {
    public void importFile(String fileName, Connection conn) throws IOException {
    	
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String next = "";
            while (null != next ) {
                next = br.readLine();
                buffer.append(next);
                if (null != next && next.endsWith(";")) {// just means a whole sql                   
                	try {
                		System.out.println("buffer  " + buffer);
						conn.createStatement().execute(buffer.toString());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    buffer = new StringBuffer();
                }
            }
        } catch(FileNotFoundException ex) {
        	System.out.println("软件配置不存在netmon.sql文件");
        }finally {
            if (null != br)
                br.close();
        }
    }
/*    public static void main(String[] args) throws SQLException, IOException {
        Import import_ = new ImportMySQL();
        //import_.importFile("c:/sql.sql", ConnectionManager.getConnection());
    }*/

}