package com.combanc.commonsupport.dataaccess.factory;


import com.combanc.commonsupport.dataaccess.Abstract.AbstractDataAccessFacade;
import com.combanc.commonsupport.dataaccess.mysql.MySqlDataAccessFacade;
import com.combanc.commonsupport.dataaccess.oracle.OracleDataAccessFacade;
import com.combanc.itsm.util.Jdbc;


/**
 * 创建操作跨数据库的外观类
 * @author jy
 */
public class DataAccessFacadeCreator  {

	private static DataAccessFacadeCreator instance;
	public static String dbType;
	private static String dbDriver;
	private static String url;
	private static String userName;
	private static String password;
	
	public static String getDbType()
	{
		return dbType;
	}

	public static void setDbType(String dbType)
	{
		DataAccessFacadeCreator.dbType = dbType;
	}

	public static String getDbDriver()
	{
		return dbDriver;
	}

	public static void setDbDriver(String dbDriver)
	{
		DataAccessFacadeCreator.dbDriver = dbDriver;
	}

	public static String getUrl()
	{
		return url;
	}

	public static void setUrl(String url)
	{
		DataAccessFacadeCreator.url = url;
	}

	public static String getUserName()
	{
		return userName;
	}

	public static void setUserName(String userName)
	{
		DataAccessFacadeCreator.userName = userName;
	}

	public static String getPassword()
	{
		return password;
	}

	public static void setPassword(String password)
	{
		DataAccessFacadeCreator.password = password;
	}

	public static void setInstance(DataAccessFacadeCreator instance)
	{
		DataAccessFacadeCreator.instance = instance;
	}	

	/**
	 * 获取配置文件中连接字符串
	 * 静态函数
	 * @return instance
	 * @throws ConfigurationException
	 */
	@SuppressWarnings("unchecked")
	public static DataAccessFacadeCreator getInstance() throws Exception {
		if (instance == null) {
			/*
			try{
				String file = "/WEB-INF/classes/applicationContext.xml";
				
				ApplicationContext context = SpringBeanProxyInitServlet.getApplicationContext();
				String webPath = context.getResource("/").getFile().getAbsolutePath();
				SAXReader sax = new SAXReader();
				
				sax.setEntityResolver(new EntityResolver(){      
				    public InputSource resolveEntity(String publicId, String systemId) {      
				        if (publicId.equals("-//SPRING//DTD BEAN//EN")){      
				            InputStream in = getClass().getResourceAsStream("/spring-beans.dtd");      
				            return new InputSource(in);      
				        }      
				        return null;      
				    }
				});
				
				Document doc = sax.read(new FileInputStream(webPath + file));
				Element book = doc.getRootElement();
				
				// 枚举所有子节点
	            for (Iterator node = book.elementIterator(); node.hasNext();) {
	            	Element e = (Element) node.next();
	            	Attribute attribute = e.attribute("id");
	            	
	            	if(attribute != null && "dateSource".equals(attribute.getValue())){
			            // 读取applicationContext.xml中dateSource
	            		List<Element> list = doc.selectNodes(e.getUniquePath()+"/property");
			            if (list != null) {
			                for (Element el : list) {
			                    Attribute attr = el.attribute("name");
			                    if("driverClassName".equals(attr.getValue())){
			                    	 List<Element> valueList = doc.selectNodes(el.getUniquePath()+"/value");
			                    	 if(valueList!=null){
			                    		 Element ele = valueList.get(0);
			                    		 dbDriver = ele.getStringValue();
			                    	 }
			                    }else if("url".equals(attr.getValue())){
			                    	 List<Element> valueList = doc.selectNodes(el.getUniquePath()+"/value");
			                    	 if(valueList!=null){
			                    		 Element ele = valueList.get(0);
			                    		 url = ele.getStringValue();
			                    	 }
			                    }else if("username".equals(attr.getValue())){
			                    	 List<Element> valueList = doc.selectNodes(el.getUniquePath()+"/value");
			                    	 if(valueList!=null){
			                    		 Element ele = valueList.get(0);
			                    		 userName = ele.getStringValue();
			                    	 }
			                    }else if("password".equals(attr.getValue())){
			                    	 List<Element> valueList = doc.selectNodes(el.getUniquePath()+"/value");
			                    	 if(valueList!=null){
			                    		 Element ele = valueList.get(0);
			                    		 password = ele.getStringValue();
			                    	 }
			                    }
			                }
			            }
	               }else if(attribute != null && "sessionFactory".equals(attribute.getValue())){
	            	   // 读取applicationContext.xml中sessionFactory 
	            	   List<Element> list = doc.selectNodes(e.getUniquePath()+"/property/props/prop");
			            if (list != null) {
			                for (Element el : list) {
			                    Attribute attr = el.attribute("key");
			                    if("hibernate.dialect".equals(attr.getValue())){
			                    	dbType = el.getText() ;
			                    }
			                }
			            }
	               }
	            }
	            instance = new DataAccessFacadeCreator();
	            
			}catch(Exception ex){
				ex.printStackTrace();
			}*/
			dbDriver=Jdbc.getString("jdbc.driverClassName");
			url=Jdbc.getString("jdbc.url");
			userName=Jdbc.getString("jdbc.username");
			password=Jdbc.getString("jdbc.password");
			dbType=Jdbc.getString("hibernate.dialect");
			instance = new DataAccessFacadeCreator();
		}
		return instance;
	}

	/**
	 * 创建带参数的数据库外观类
	 * @param facadeName
	 */
	public AbstractDataAccessFacade createDataAccessFacade(String facadeName,String dbDriver, String url, String userName, String password) {
		String fName = facadeName.toLowerCase();
		AbstractDataAccessFacade returnFacade = null;
		if (fName.equalsIgnoreCase("org.hibernate.dialect.OracleDialect")) {
			System.out.println("------------dbType: Oracle");
			returnFacade = new OracleDataAccessFacade(dbDriver, url, userName,password);
		} else if (fName.equalsIgnoreCase("org.hibernate.dialect.MySQLInnoDBDialect")) {
			System.out.println("------------dbType: MySQL");
			returnFacade = new MySqlDataAccessFacade(dbDriver, url, userName,password);
		} else if (fName.equalsIgnoreCase("org.hibernate.dialect.SQLServerDialect")) {
			System.out.println("------------dbType: SqlServer");
		} else if (fName.equalsIgnoreCase("org.hibernate.dialect.DB2Dialect")) {
			System.out.println("------------dbType: DB2");

		} else if (fName.equalsIgnoreCase("org.hibernate.dialect.SybaseDialect")) {
			System.out.println("------------dbType: Sybase");
			
		} else {
			returnFacade = new OracleDataAccessFacade(dbDriver, url, userName,password);
		}
		return returnFacade;
	}

	/**
	 * 创建默认的数据库外观类
	 * @return facadeName
	 * @throws ConfigurationException
	 */
	public AbstractDataAccessFacade createDataAccessFacade() throws Exception {
		return createDataAccessFacade(dbType, dbDriver, url, userName, password);
	}
}
