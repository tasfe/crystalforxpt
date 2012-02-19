package com.netblizzard.httpclient;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class LoginTrain {
	private static final String HOME_SITE = "http://www.12306.cn/mormhweb/kyfw/";
	private static final String QUERY_URI = "http://www.12306.cn/mormhweb/kyfw/";
	private static final String LOGON_SITE = "https://dynamic.12306.cn/otsweb/loginAction.do";
	private static final int LOGON_PORT = 80;
	private static String email = "memoryxpt";
	private static String password = "1";
	static HttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args)  {
		try {
			TrustManager easyTrustManager = new X509TrustManager() {

				public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
					// To change body of implemented methods use File | Settings
					// |
					// File Templates.
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
					// To change body of implemented methods use File | Settings
					// |
					// File Templates.
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[0]; // To
																		// change
					// body of
					// implemented
					// methods
					// use File
					// |
					// Settings
					// | File
					// Templates.
				}
			};

			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
			SSLSocketFactory sf = new SSLSocketFactory(sslcontext);

			Scheme sch = new Scheme("https", 443, sf);

			httpClient.getConnectionManager().getSchemeRegistry().register(sch);

			// �ڶ��������ʹ��post��ʽ
			System.out.println("�ڶ��������ʹ��post��ʽ");
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			formparams.add(new BasicNameValuePair("loginUser.user_name", email));
			formparams.add(new BasicNameValuePair("user.password", password));
			formparams.add(new BasicNameValuePair("randCode", "4G3C"));
			UrlEncodedFormEntity initEntity = new UrlEncodedFormEntity(formparams, "utf-8");

			HttpPost httppost = new HttpPost(LOGON_SITE);
			httppost.setEntity(initEntity);

			HttpResponse response2 = httpClient.execute(httppost);
			int statusCode = response2.getStatusLine().getStatusCode();

			System.out.println("statusCode:" + statusCode);

			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_SEE_OTHER
					|| statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
				String newUrl = response2.getLastHeader("Location").getValue();
				System.out.println("���¶����ҳ��Ϊ��" + newUrl);

				runBroswer(newUrl);
				// Ȼ����HttpGet��ʽ����
			}

			else if (statusCode == HttpStatus.SC_OK) {

				Header headers[] = response2.getAllHeaders();

				System.out.println("--==headers information==--");
				for (Header header : headers) {
					System.out.println(header.getName() + ": " + header.getValue());
				}

				System.out.println("--==cookies information==--");
				List<Cookie> cookies2 = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
				if (cookies2.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies2.size(); i++) {
						System.out.println("- " + cookies2.get(i).toString());
					}
				}
				
				httppost.abort();

				// �������
				HttpEntity entity2 = response2.getEntity();
				// ��ʾ����
				if (entity2 != null) {
					// String newUrl = buileUri();
					URI uri = buileUri();
					System.out.println(uri);
					HttpGet get = new HttpGet(uri);
//					HttpResponse response = httpClient.execute(get);
//					System.out.println(getContent(response.getEntity()));
					
					//System.out.println("newURL:\n" + newUrl);
					
//					String newContent = content.substring(0, firstIndex + 1) + newUrl + content.substring(lastIndex + 1);

					// System.out.println("newURL:\n" + newContent);
					//runBroswer(newUrl);
				}

			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	private static URI buileUri() throws URISyntaxException {
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("orderRequest.train_date", "2012-01-05"));
		paramList.add(new BasicNameValuePair("orderRequest.from_station_telecode", "BXP"));
		paramList.add(new BasicNameValuePair("orderRequest.to_station_telecode", "AYF"));
		paramList.add(new BasicNameValuePair("trainPassType", "QB"));
		paramList.add(new BasicNameValuePair("trainClass", "D#"));
		paramList.add(new BasicNameValuePair("includeStudent", "00"));
		paramList.add(new BasicNameValuePair("seatTypeAndNum", "O#2@"));
		paramList.add(new BasicNameValuePair("orderRequest.start_time_str", "00:00--24:00"));
		return URIUtils.createURI("https", "www.12306.cn", -1, "/mormhweb/kyfw/" + "querySingleAction.do", URLEncodedUtils.format(paramList, "UTF-8"), null);
	}

	public static void runBroswer(String webSite) {
		try {
			Desktop desktop = Desktop.getDesktop();
			if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
				URI uri = new URI(webSite);
				desktop.browse(uri);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
	}
	
	public static String getContent(HttpEntity entity) throws Exception {
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

