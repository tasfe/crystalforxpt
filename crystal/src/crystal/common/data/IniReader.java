package crystal.common.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class IniReader {
	protected HashMap sections = new HashMap();
	private transient String currentSecion;
	private transient Properties current;

	public IniReader(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		read(reader);
		reader.close();
	}

	protected void read(BufferedReader reader) throws IOException {
		String line;
		while ((line = reader.readLine()) != null) {
			parseLine(line);
		}
	}

	protected void parseLine(String line) {
		line = line.trim();
		if (line.matches("\\[.*\\]")) {
//			if (current != null) {
//				sections.put(currentSecion, current);
//			}
			currentSecion = line.replaceFirst("\\[(.*)\\]", "$1");
			current = new Properties();
			if (current != null) {
				sections.put(currentSecion, current);
			}
		} else if (line.matches(".*=.*")) {
			if (current != null) {
				int i = line.indexOf('=');
				String name = line.substring(0, i);
				String value = line.substring(i + 1);
				current.setProperty(name, value);
			}
		}
	}

	public String getValue(String section, String name) {
		Properties p = (Properties) sections.get(section);

		if (p == null) {
			return null;
		}

		String value = p.getProperty(name);
		return value;
	}
	
	public void setValue(String section,String key, String value)
	{
		Properties p = (Properties) sections.get(section);
		if (p != null) {
		 if(!p.containsKey (key)) {  
            return;  
		}  
		p.put (key, value);  
		}
	}
	
	public void save(String section,String filename)
	{
		 try {  
			 Properties p = (Properties) sections.get(section);
			 FileWriter fw = new FileWriter (filename);  
			 BufferedWriter bw = new BufferedWriter (fw);  
			 bw.write ( "[CONF]");  
		     bw.newLine ();
			 Enumeration<Object> enumeration = p.keys();
			 while   (enumeration.hasMoreElements())   {
	            Object   key   =   enumeration.nextElement();
	            bw.write (key.toString() + "=" + p.get(key));  
 			    bw.newLine ();
	         } 
			   bw.close ();  
			   fw.close ();  
		}catch (Exception ex) {  
			    ex.printStackTrace();  
		}  
	}

	public static void main(String[] args) throws IOException {
		IniReader reader = new IniReader("D:\\workspace\\netmon19\\conf.ini");
  		System.out.println(reader.getValue("CONF", "rjw_trans"));
  		System.out.println(reader.getValue("CONF", "demo"));
  		reader.setValue("CONF", "recoverFun", "false");
  		reader.save("CONF", "D:\\workspace\\netmon19\\conf.ini");
  		
//		 System.out.println(reader.getValue("TestSect1", "kkk 2")); 
//		 System.out.println(reader.getValue("TestSect3", "cugb")); 
//  	 System.out.println(reader.getValue("TestSect3", "kkk 6")); 
	}

}

