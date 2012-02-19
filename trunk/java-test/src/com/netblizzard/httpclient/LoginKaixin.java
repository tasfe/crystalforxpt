package com.netblizzard.httpclient;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class LoginKaixin {
	private static final String HOME_SITE = "http://www.kaixin001.com/home/?uid=4392068";
	private static final String LOGON_SITE = "http://www.kaixin001.com/login/login_api.php";
	private static String email = "yongyouziyou@hotmail.com";
	private static String password = "1";
	static DefaultHttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) throws Exception {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// formParams.add(new BasicNameValuePair("url",
		// "http://www.kaixin001.com/home"));
		formParams.add(new BasicNameValuePair("email", email));
		formParams.add(new BasicNameValuePair("password", password));
		UrlEncodedFormEntity initEntity = new UrlEncodedFormEntity(formParams, "utf-8");

		HttpPost httpPost = new HttpPost(LOGON_SITE);
		httpPost.setEntity(initEntity);

		HttpResponse response = httpClient.execute(httpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statusCode:" + statusCode);

		if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_SEE_OTHER
				|| statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
			String newUrl = response.getLastHeader("Location").getValue();
			System.out.println("���¶����ҳ��Ϊ��" + newUrl);
			runBroswer(newUrl);
		} else if (statusCode == HttpStatus.SC_OK) {
			System.out.println("--==headers information==--");
			Header headers[] = response.getAllHeaders();
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
					httpClient.getCookieStore().addCookie(cookies2.get(i));
				}
			}

			// �������
			HttpEntity entity = response.getEntity();
			// ��ʾ����
			if (entity != null) {
				String content = EntityUtils.toString(entity);
				System.out.println(content);
				int firstIndex = content.lastIndexOf('=');
				int lastIndex = content.lastIndexOf('/');
				String suffix = content.substring(firstIndex + 1, lastIndex + 1);

				System.out.println("suffix:" + suffix);
				suffix = "home";
				String newUrl = HOME_SITE;

				String newContent = content.substring(0, firstIndex + 1) + newUrl + content.substring(lastIndex + 1);
				HttpEntity entity3 = httpClient.execute(new HttpGet(HOME_SITE)).getEntity();
				System.out.println(EntityUtils.toString(entity3));
				System.out.println("newURL:\n" + newContent);
				runBroswer(newUrl);
			}

		}
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

}
