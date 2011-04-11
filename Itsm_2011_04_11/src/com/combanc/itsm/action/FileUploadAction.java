package com.combanc.itsm.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 16 * 1024;

	private File[] file;
	private String[] fileName;
	private String[] contentType;

	public String[] getContentType() {
		return contentType;
	}

	public void setFileContentType(String[] contentType) {
		this.contentType = contentType;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileName() {
		return fileName;
	}

	public void setFileFileName(String[] fileName) {
		this.fileName = fileName;
	}

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				// ���뵽������
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int i = 0;
				while ((i = in.read(buffer)) != -1) {
					out.write(buffer, 0, i);
					i = 0;
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String fileUpload() {
		System.out.println("你好");
		// HttpServletRequest request = ServletActionContext.getRequest();
		// String[] paths = request.getParameterValues("file");
		// this.setFile(paths);
		for (int i = 0; i < this.getFile().length; i++) {

			System.out.println("================="
					+ fileName[i]
					+ "========="
					+ ServletActionContext.getServletContext().getRealPath(
							"WebRoot/UploadImages"));
			File dstFile = new File(ServletActionContext.getServletContext()
					.getRealPath("/UploadImages")
					+ "/" + fileName[i]);
			copy(file[i], dstFile);
		}
		// HttpServletRequest request = ServletActionContext.getRequest();
		// request.setAttribute("filenames", fileName);
		return SUCCESS;
	}

}
