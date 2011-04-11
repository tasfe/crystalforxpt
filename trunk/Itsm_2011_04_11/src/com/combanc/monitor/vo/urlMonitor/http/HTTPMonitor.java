package com.combanc.monitor.vo.urlMonitor.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;


import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.methods.GetMethod;

import com.combanc.monitor.pojo.MonitorUrlResponse;
import com.combanc.monitor.vo.urlMonitor.model.AbstractMonitor;
import com.combanc.monitor.vo.urlMonitor.model.MonitorResult;
import com.combanc.monitor.vo.urlMonitor.model.PerfResult;

public class HTTPMonitor extends AbstractMonitor {

	public static final String REGEX_LINE = "<[^>]+>|&nbsp;|[ \t\f\\v]";
	public static final String REGEX_ALL = "<[^>]+>|&nbsp;|[ \t\f\r\n\\v]";

	public MonitorResult doMonitor(MonitorUrlResponse hur) {
		MonitorResult result = new MonitorResult(false);
		PerfResult reqReplyTime = new PerfResult(1, false);
		PerfResult[] perfs = { reqReplyTime };
		result.setPerfResults(perfs);

		MonitorUrlResponse p = hur;
	    String ip = hur.getServerIp();
	    
		int port = Integer.valueOf(p.getPort());
		String absUrl = p.getAddress();
		String domain = p.getDomain();
		boolean chkDomain = (p.getDomain() != null);

		result.setParamString("URL:" + absUrl);
		result.setPort(port);
		result.setWontedVal("");
		result.setState(-2);

		if (chkDomain) {
			ip = domain;
		}
		String httpUrl = "http://" + ip + ":" + port + absUrl;
		URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException ex) {
			result.setResultDesc("无效的URL:" + absUrl);
			return result;
		}

		HttpClient client = new HttpClient();

		client.getParams().setSoTimeout(30000);

		GetMethod get = new GetMethod(httpUrl);

		NTCredentials upc = new NTCredentials();
		upc.setUserName((p.getUsername() == null) ? "foo" : p.getUsername());
		upc.setPassword((p.getPassword() == null) ? "bar" : p.getPassword());
		upc.setDomain(ip);
		upc.setHost(ip);

		client.getState().setCredentials(p.getRealm(), null, upc);
		get.setDoAuthentication(true);

		client.setConnectionTimeout(30000);
		double replyTime = 0.0D;
		try {
			long time = System.currentTimeMillis();
			client.executeMethod(get);
			replyTime = System.currentTimeMillis() - time;
			replyTime /= 1000.0D;
		} catch (IllegalArgumentException e) {
			result.setState(-2);
			result.setResultDesc("无效的URL:" + absUrl);
			return closeAndReturn(result, get, null);
		} catch (UnknownHostException e) {
			result.setState(-2);
			result.setResultDesc("无效地址:" + ip);
			return closeAndReturn(result, get, null);
		} catch (ConnectTimeoutException e) {
			result.setState(-2);
			result.setResultDesc("连接目标地址" + ip + "超时");
			return closeAndReturn(result, get, null);
		} catch (ConnectException e) {
			result.setState(-2);
			result.setResultDesc("无法连接到" + ip + "的" + port + "端口");
			return closeAndReturn(result, get, null);
		} catch (SocketTimeoutException e) {
			result.setState(-2);
			result.setResultDesc("成功连接端口:" + port + "\n但读取数据超时或HTTP协议错误");
			return closeAndReturn(result, get, null);
		} catch (IOException e) {
			result.setState(-2);
			result.setResultDesc("未知的通信输入输出错误");
			return closeAndReturn(result, get, null);
		} catch (Throwable e) {
			result.setResultDesc("未知错误");
			return closeAndReturn(result, get, null);
		}

		int statusCode = get.getStatusCode();

		InputStream is = null;

		boolean wonted = true;
		StringBuffer msg = new StringBuffer();
		msg.append("成功连接到端口:" + port + ".\n");

		reqReplyTime.setValue(replyTime);
		reqReplyTime.setObtainable(true);
		if (p.getReplyTime() != -1) {
			if (replyTime <= p.getReplyTime()) {
				wonted = wonted;
				msg.append("页面响应时间:" + replyTime + "秒.\n");
			} else {
				if (wonted)
					;
				wonted = false;
				reqReplyTime.setState(-2);
				msg.append("页面响应时间:" + replyTime + "秒,超过 " + p.getReplyTime()
						+ "秒.\n");
			}

		}


		if (true) {
			if (statusCode == 200) {
				wonted = true;
				msg.append("回应码等于200.\n");
			} else {
				msg.append("回应码不等于200(" + statusCode + ").\n");
				result.setResultDesc(msg.toString());
				result.setCurrentVal("服务运行.回应码等于" + statusCode);
				return closeAndReturn(result, get, is);
			}

		}

		if ((p.getContain() != null) || (p.getNocontain() != null)) {
			try {
				is = get.getResponseBodyAsStream();
			} catch (IOException ex2) {
				result.setState(-2);
				result.setResultDesc("无法连接到" + port + "端口.");
				result.setCurrentVal("服务停止");
				return closeAndReturn(result, get, is);
			}
		} else {
			if (wonted)
				result.setState(1);
			else {
				result.setState(-2);
			}
			result.setResultDesc(msg.toString());
			return closeAndReturn(result, get, is);
		}

		if ((p.getContain() != null) && (p.getNocontain() != null)) {
			String content = getAllNotHtmlContent(is);
			if (content.indexOf(p.getContain()) > -1) {
				wonted = wonted;
				msg.append("页面内容包含:" + p.getContain() + ".\n");
			} else {
				if (wonted)
					;
				wonted = false;
				msg.append("页面内容不包含:" + p.getContain() + ".\n");
			}

			if (content.indexOf(p.getNocontain()) > -1) {
				if (wonted)
					;
				wonted = false;
				msg.append("页面内容包含:" + p.getNocontain() + ".\n");
			} else {
				wonted = wonted;
				msg.append("页面内容不包含:" + p.getNocontain() + ".\n");
			}
		} else if (p.getContain() != null) {
			if (chkContainByEach(is, p.getContain())) {
				wonted = wonted;
				msg.append("页面内容包含:" + p.getContain() + ".\n");
			} else {
				if (wonted)
					;
				wonted = false;
				msg.append("页面内容不包含:" + p.getContain() + ".\n");
			}
		} else if (p.getNocontain() != null) {
			if (chkContainByEach(is, p.getNocontain())) {
				if (wonted)
					;
				wonted = false;
				msg.append("页面内容包含:" + p.getNocontain() + ".\n");
			} else {
				wonted = wonted;
				msg.append("页面内容不包含:" + p.getNocontain() + ".\n");
			}
		}

		if (wonted)
			result.setState(1);
		else {
			result.setState(-2);
		}
		result.setResultDesc(msg.toString());
		return closeAndReturn(result, get, is);
	}

	private String getAllContent(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		try {
			String str = reader.readLine();
			while (str != null) {
				sb.append(str + "\n");
				str = reader.readLine();
			}
		} catch (IOException ex) {
		}
		return sb.toString();
	}

	private String getAllNotHtmlContent(InputStream is) {
		return getAllContent(is)
				.replaceAll("<[^>]+>|&nbsp;|[ \t\f\r\n\\v]", "");
	}

	private boolean chkContainByAll(InputStream is, String txt) {
		return (getAllNotHtmlContent(is).indexOf(txt) > -1);
	}

	private boolean chkContainByEach(InputStream is, String txt) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String str = reader.readLine();
			while (str != null) {
				str = str.replaceAll("<[^>]+>|&nbsp;|[ \t\f\\v]", "");
				if (str.indexOf(txt) > -1) {
					return true;
				}
				str = reader.readLine();
			}
		} catch (IOException ex) {
			return false;
		}
		return false;
	}

	private MonitorResult closeAndReturn(MonitorResult mr, GetMethod get,
			InputStream is) {
		close(get, is);
		return mr;
	}

	private void close(GetMethod get, InputStream is) {
		try {
			if (is != null) {
				is.close();
			}
			if (get != null)
				get.releaseConnection();
		} catch (Exception ex) {
		}
	}
	
	public static void main(String[] args) throws IOException {
		MonitorUrlResponse url = new MonitorUrlResponse();
		url.setDomain("www.combanc.com.cn");
		url.setPort("80");
		url.setContain("北京康邦科技有限公司");
		url.setAddress("/");
		url.setReplyTime(5);
		HTTPMonitor monitor = new HTTPMonitor();
		MonitorResult result = monitor.doMonitor(url);
		System.out.println(result.getResultDesc());
    }

}
