package com.combanc.monitor.vo.urlMonitor.https.httpclientssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class BaseX509TrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[]{};
	}
}