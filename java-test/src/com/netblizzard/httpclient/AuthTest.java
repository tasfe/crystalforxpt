package com.netblizzard.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class AuthTest extends BasicHttpResponse{
	private static String LOGIN_SITE = "http://www.kaixin001.com/login/index.php";
	private static String user = "yongyou@hotmail.com";
	private static String password = "19821014";
	
	public static void main(String[] args) throws Exception {
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpContext localContext = new BasicHttpContext();
//		HttpGet httpget = new HttpGet("http://www.kaixin001.com/"); 
//		HttpResponse response = httpclient.execute(httpget, localContext);
//
//		AuthState proxyAuthState = (AuthState) localContext.getAttribute(
//		    ClientContext.PROXY_AUTH_STATE);
//		System.out.println("Proxy auth scope: " + proxyAuthState.getAuthScope());
//		System.out.println("Proxy auth scheme: " + proxyAuthState.getAuthScheme());
//		System.out.println("Proxy auth credentials: " + proxyAuthState.getCredentials());
//		AuthState targetAuthState = (AuthState) localContext.getAttribute(
//		    ClientContext.TARGET_AUTH_STATE);
//		System.out.println("Target auth scope: " + targetAuthState.getAuthScope());
//		System.out.println("Target auth scheme: " + targetAuthState.getAuthScheme());
//		System.out.println("Target auth credentials: " + targetAuthState.getCredentials());
		
		
//		
//		HttpHost targetHost = new HttpHost("www.kaixin001.com", 80, "http"); 
//
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		httpclient.getCredentialsProvider().setCredentials(
//		        new AuthScope(targetHost.getHostName(), targetHost.getPort()), 
//		        new UsernamePasswordCredentials(user, password));
//
//		// Create AuthCache instance
//		AuthCache authCache = new BasicAuthCache();
//		// Generate BASIC scheme object and add it to the local auth cache
//		BasicScheme basicAuth = new BasicScheme();
//		authCache.put(targetHost, basicAuth);
//
//		// Add AuthCache to the execution context
//		BasicHttpContext localcontext = new BasicHttpContext();
//		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);        
//
//		HttpGet httpget = new HttpGet("/");
//		for (int i = 0; i < 1; i++) {
//		    HttpResponse response = httpclient.execute(targetHost, httpget, localcontext);
//		    HttpEntity entity = response.getEntity();
//		    System.out.println(parseEntity(entity));
//		    // EntityUtils.consume(entity);
//		}
		
		
		
		AuthTest kx = new AuthTest();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httpost = new HttpPost(LOGIN_SITE);
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("email", user));
		qparams.add(new BasicNameValuePair("password", password));

		httpost.setEntity(new UrlEncodedFormEntity(qparams, HTTP.UTF_8));
		HttpResponse response = httpclient.execute(httpost);
		kx.showResponseStatus(response);

		// HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		kx.getContent(entity);

		// Login
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		System.out.println("Post logon cookies:");
		kx.setCookie(httpclient, cookies);

		// Redirect to home page
		String homepage = "http://www.kaixin001.com/home/";
		String content = null;
		content = kx.enterComponentContent(homepage, "Home page", httpclient, cookies, response, entity);
		// System.out.println(content);
		
		// Component
//		String componet = "http://www.kaixin001.com/!rich/market.php";
//		content = kx.enterComponentContent(componet, "Component", httpclient, cookies, response, entity);

		// --------------------------------------------
		kx.writeToFile("c:/kaixin.html", content);

		// When HttpClient instance is no longer needed,
		// shut down the connection manager to ensure
		// immediate deallocation of all system resources
		httpclient.getConnectionManager().shutdown();
	}
	
	public void setCookie(DefaultHttpClient httpclient, List<Cookie> cookies) {
		if (cookies.isEmpty()) {
			System.out.println("Cookie is empty.");
			return;
		} else {
			for (int i = 0; i < cookies.size(); i++) {
				System.out.println((i + 1) + " - " + cookies.get(i).toString());
				httpclient.getCookieStore().addCookie(cookies.get(i));
			}
			System.out.println();
		}
	}

	// "开心网其它组件URL如下，大家可以添加上自己喜欢的组件URL。"
	// "http://www.kaixin001.com/!slave/index.php", "朋友买卖"
	// "http://www.kaixin001.com/!parking/index.php", "争车位"
	// "http://www.kaixin001.com/!house/index.php?_lgmode=pri", "买房子"
	// "http://www.kaixin001.com/!house/index.php?_lgmode=pri&t=49"
	// "http://www.kaixin001.com/!house/garden/index.php","花园"
	// "http://www.kaixin001.com/!rich/market.php", "超级大亨"
	public String enterComponentContent(String url, String componentName, DefaultHttpClient httpclient, List<Cookie> cookies, HttpResponse response,
			HttpEntity entity) throws Exception {
		System.out.println("--- Enter: " + componentName + " ---");
		System.out.println("--- Url:   " + url + " ---");
		setCookie(httpclient, cookies);
		HttpGet httpget = new HttpGet(url);
		response = httpclient.execute(httpget);

		entity = response.getEntity();
		return getContent(entity);
	}

	public void showResponseStatus(HttpResponse response) {
		// System.out.println(response.getProtocolVersion());
		// System.out.println(response.getStatusLine().getStatusCode());
		// System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
		System.out.println("-------------------------\r\n");
	}
	
	public void writeToFile(String file, HttpEntity entity) throws Exception {
		writeToFile(file, EntityUtils.toString(entity));
	}

	public void writeToFile(String file, String data) throws Exception {
		FileUtils.writeStringToFile(new File(file), data, "UTF-8");
	} 

	
	private static String parseEntity(HttpEntity entity) throws IllegalStateException, IOException {
		StringBuilder str = new StringBuilder();
		if (entity != null) {
		    InputStream instream = entity.getContent();
		    InputStreamReader isr = new InputStreamReader(instream, "utf-8");
		    BufferedReader br = new BufferedReader(isr);
		    String tmpLine;
		    int line = 0;
			while ((tmpLine = br.readLine()) != null && (++line < 3000)) {
				// System.out.println(begin);
				str.append(tmpLine + "\n");
			}
		    br.close();
		    isr.close();
		    instream.close();
		}
		return str.toString();
	}
	
	public String getContent(HttpEntity entity) throws Exception {
		if (entity != null) {
			entity = new BufferedHttpEntity(entity);
			long len = entity.getContentLength();
			System.out.println("Length: " + len);
			System.out.println("====================\r\n");
			return EntityUtils.toString(entity, "UTF-8");
		} else {
			System.out.println("entity is null.");
			return null;
		}
	}
}
