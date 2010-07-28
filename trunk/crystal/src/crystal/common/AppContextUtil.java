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

    //������Ϊ��������װ������ͬ������ж��AppContextUtilʵ��
    @SuppressWarnings({ "unused", "unchecked" })
	private static Class getClass(String classname)   
                             throws ClassNotFoundException {   
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();   
        if(classLoader == null)   
            classLoader = AppContextUtil.class.getClassLoader();   
        return (classLoader.loadClass(classname));   
    }
    
    //ע��datasource�ļ���˳����new String[]��˳�򣬺���ػḲ��ǰ���
    private static String[] contextPaths = new File(getPath()+ "datasource-jdbc.xml").exists()
    ? new String[]{"applicationContext.xml", "file:" + getPath()+ "datasource-jdbc.xml"}:new String[]{"applicationContext.xml"};
    private static ApplicationContext appContext = new ClassPathXmlApplicationContext(contextPaths);
   
    /**
	 * ȡ��Spring������������
	 * @return ApplicationContext
	 * */
	public static ApplicationContext getAppContext(){
		return appContext;
	}
	
	/**
	 * ����Ĭ�ϵ�Spring����ȡ��Bean
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
