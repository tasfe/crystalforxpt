package com.netblizzard.httpclient;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class LoginRenren {
	private static final String LOGON_SITE = "http://www.renren.com/PLogin.do";
	private static final String REDIRECT_URL = "http://www.renren.com/home";
	private static String email = "yongyouziyouto0@sohu.com";
	private static String password = "111111";
	static HttpClient httpClient = new DefaultHttpClient();

	public static void main(String[] args) {
		try {
			List<NameValuePair> formParams = new ArrayList<NameValuePair>();
			formParams.add(new BasicNameValuePair("email", email));
			formParams.add(new BasicNameValuePair("password", password));
			UrlEncodedFormEntity initEntity = new UrlEncodedFormEntity(formParams, "utf-8");

			HttpPost httpPost = new HttpPost(LOGON_SITE);
			httpPost.setEntity(initEntity);

			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("statusCode:" + statusCode);

//			301 ��ʾ�ⶫ�������ƶ����µĵط��ˡ�
//			302 ��ʾ�ⶫ����ʱ�ƶ����µĵط��ˡ����û�и� URL����ô��ֻ�Ǹ�����������ⶫ���ƶ����µĵط��ˣ��� 404 ��ͬ������������Ҳ�֪����������� URL������������ܻ��ض���ָ�� URL����ʵ��Ӧ���У������������ 303 �Դ���
//			303 ʵ��Ӧ���У�302 �� 303 ���ơ�
//			307 ��ʾ��ʱ�ض��������ض��򲻻ᱻ���棬����������������ˣ���ʱ�ض���һ��ҳ�档
			if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_SEE_OTHER
					|| statusCode == HttpStatus.SC_TEMPORARY_REDIRECT) {
				String newUrl = response.getLastHeader("Location").getValue();
				System.out.println("���¶����ҳ��Ϊ��" + newUrl);
				runBroswer(newUrl);
				// Ȼ����HttpGet��ʽ����
			} else if (statusCode == HttpStatus.SC_OK) {
				Header headers[] = response.getAllHeaders();

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

				// �������
				HttpEntity entity = response.getEntity();
				// ��ʾ����
				if (entity != null) {
					String content = EntityUtils.toString(entity);
					// System.out.println(content);
					runBroswer(REDIRECT_URL);
				}
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
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
