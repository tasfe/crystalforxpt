package com.combanc.itsm.db;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;

public class FileDownAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;// 初始的通过param指定的文件名属性

	private String inputPath;// 指定要被下载的文件路径

	private HttpServletRequest request;

	private Map session;

	public InputStream getInputStream() throws Exception {

		// 通过 ServletContext，也就是application 来读取数据

		inputPath = "/upload/" + (String) session.get("sql.sql");

		return ServletActionContext.getServletContext().getResourceAsStream(
				inputPath);

	}

	public String execute() throws Exception {

		return SUCCESS;

	}

	/** 提供转换编码后的供下载用的文件名 */

	public String getDownloadFileName() {

		fileName = (String) session.get("sql.sql");

		System.out.println(fileName);

		String downFileName = fileName;

		try {

			downFileName = new String(downFileName.getBytes(), "ISO8859-1");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		return downFileName;

	}
}