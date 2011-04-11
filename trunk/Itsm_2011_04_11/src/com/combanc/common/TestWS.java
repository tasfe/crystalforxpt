package com.combanc.common;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.combanc.commonsupport.dataaccess.Abstract.AbstractDataAccessFacade;
import com.combanc.commonsupport.dataaccess.factory.DataAccessFacadeCreator;



public class TestWS {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		/*
		 Call call= login();
		 if(null==call){
			 System.out.println("webservice调用异常!!!");
			 return ;
		 }
         call.setOperationName("toUpper");//测试方法转变为小写
		 String res = (String) call.invoke( new Object[] {"coDEDEDEDE111111~code222222"} );
		 System.out.println("统一变为大写--------"+res);
		 
		 	String url = "http://192.168.80.14:8080/services/AssetService";//192.168.80.14:8080/Itsm1.0此部分根据环境修改;登录
			Service service = new Service();
	        Call callAnother=null;
	        try {
	        	callAnother = (Call) service.createCall();
	        	callAnother.setTargetEndpointAddress(new java.net.URL(url) );
	        	callAnother.setOperationName("toUpper");
	            String loginResult=(String)callAnother.invoke(new Object[]{"adminSSadmin"});
	            System.out.println("another-------"+loginResult);
	            
	        } catch (ServiceException e) {
	            e.printStackTrace();
	        }
     
		  HttpClient http = new HttpClient(); 
		  GetMethod get = new GetMethod("http://localhost:8080/js/TableTree4J.js"); 
		  try{ 
		  get.addRequestHeader("accept-encoding", "gzip,deflate"); 
		  get.addRequestHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; Alexa Toolbar; Maxthon 2.0)"); 
		  int er = http.executeMethod(get); 
		  if(er==200){ 
		   System.out.println(get.getResponseContentLength()); 
		   String html = get.getResponseBodyAsString(); 
		   System.out.println(html); 
		   System.out.println(html.getBytes().length); 
		  } 
		}finally{ 
		   get.releaseConnection(); 
		} 
		 */
		//使用JDBC示例
		AbstractDataAccessFacade daf = DataAccessFacadeCreator.getInstance().createDataAccessFacade();
		String sqlString = daf.getAbstractChart().getDailyIncRequestCount("", "");
		System.out.println("测试用户总数:---"+((Map)daf.getList(sqlString).get(0)).get("COUNT").toString());
		
		} 


	
	public static Call login() throws MalformedURLException, RemoteException{
		String url = "http://192.168.80.14:8080/services/AssetService";//192.168.80.14:8080/Itsm1.0此部分根据环境修改;登录
		Service service = new Service();
		service.setMaintainSession(true);//很重要，代表客户端维护SESSION
        Call call=null;
        try {
            call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(url) );
            call.setOperationName("login");//登录
            String loginResult=(String)call.invoke(new Object[]{"admin","admin"});
            System.out.println("loginResult-------"+loginResult);
            
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return call;
	}
}
class TestThread implements Runnable{

	public void run() {
		
	}
	
}
