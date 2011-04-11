package com.combanc.itsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.combanc.common.Config;
import com.combanc.common.SysInfo;
import com.combanc.common.SystemInfo;
import com.combanc.common.LicenseInfo;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.util.DateUtil;
import com.combanc.itsm.util.FileUtil;


public class LicenseAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Customer;
	private String RequestLicense;
	private String ResponseLicense;
	private List<String[]> systemInfo;
	private List<String[]> licenseInfo;
	private final static String SAVELICENSE="saveLicense";
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getRequestLicense() {
		return RequestLicense;
	}
	public void setRequestLicense(String requestLicense) {
		RequestLicense = requestLicense;
	}
	public String getResponseLicense() {
		return ResponseLicense;
	}
	public void setResponseLicense(String responseLicense) {
		ResponseLicense = responseLicense;
	}
	
	public List<String[]> getSystemInfo() {
		return systemInfo;
	}
	public void setSystemInfo(List<String[]> systemInfo) {
		this.systemInfo = systemInfo;
	}
	public List<String[]> getLicenseInfo() {
		return licenseInfo;
	}
	public void setLicenseInfo(List<String[]> licenseInfo) {
		this.licenseInfo = licenseInfo;
	}
	public String saveLicense() {
		String msg = "";
		if (LicenseInfo.verifyLicense(ResponseLicense)) {
			FileUtil.writeText(Config.getContextRealPath() + "WEB-INF/classes/license.dat", ResponseLicense);
			LicenseInfo.update(); 
			msg = "保存成功！";
			return SAVELICENSE;
		} else {
			msg = "无效的许可证！";
			String str="<script language='javascript'>alert('无效的许可证!!!\\n请返回!!!');javascript:history.go(-1);</script>"; 
			HttpServletResponse response = ServletActionContext.getResponse(); 
			response.setContentType("text/html;charset=GBK");//解决中文乱码 
			PrintWriter writer;
			try {
				writer = response.getWriter();
				writer.write(str); 
				writer.flush(); 
				writer.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		addActionMessage(msg);
		return SAVELICENSE;
		
		

	}

	public static boolean needWarning() {
		Date endDate = LicenseInfo.getEndDate();
		if (DateUtil.addMonth(endDate, -3).getTime() < System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
	
	public String getInfo(){
		this.systemInfo = SysInfo.SystemInfo();
		this.licenseInfo = SysInfo.LicenseInfo();
		return SUCCESS;
	}
}
