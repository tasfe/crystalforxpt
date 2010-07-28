package crystal.common;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class AppContextUtil{

    private final static AppContextUtil instance= new AppContextUtil();   
    public  final static AppContextUtil getInstance(){
        return instance;
    }   
    private AppContextUtil(){}

    //下面是为了消除类装载器不同而造成有多个AppContextUtil实例
    @SuppressWarnings({ "unused", "unchecked" })
	private static Class getClass(String classname)   
                             throws ClassNotFoundException {   
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();   
        if(classLoader == null)   
            classLoader = AppContextUtil.class.getClassLoader();   
        return (classLoader.loadClass(classname));   
    }
    
    //注意datasource的加载顺序按照new String[]的顺序，后加载会覆盖前面的
    private static String[] contextPaths = new File(getPath()+ "datasource-jdbc.xml").exists()
    ? new String[]{"applicationContext.xml", "file:" + getPath()+ "datasource-jdbc.xml"}:new String[]{"applicationContext.xml"};
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext(contextPaths);
   
    /**
	 * 取得Spring的配置上下文
	 * @return ApplicationContext
	 * */
	public static ApplicationContext getAppContext(){
		return appContext;
	}
	
	/**
	 * 根据默认的Spring设置取得Bean
	 * @param String beanName
	 * @return Object
	 * */
	public Object getBean(String beanName){
		return appContext.getBean(beanName);
	}
	
	public static String getPath() {
		File f = new File("netmon19.jar");
		String tmp = f.getAbsolutePath();
		return tmp.substring(0, tmp.lastIndexOf("\\") + 1);
	}
}  
