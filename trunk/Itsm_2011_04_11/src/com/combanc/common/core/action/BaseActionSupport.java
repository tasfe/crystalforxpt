package com.combanc.common.core.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseActionSupport extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = -6533176789007850647L;

	protected final transient Log logger = LogFactory.getLog(super.getClass());
	
	// 接、传值
	protected String requestData;
	protected String responseData;

	HttpServletRequest request = null;

	private String tableId;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	/**
	 * 
	 * @param list
	 * @param name
	 * @param id
	 * @param nullable
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getOptionList(List list, String name, String id,
			boolean nullable) throws Exception {
		if (null == list)
			return null;
		List optionList = null;
		if (list.size() > 0) {
			optionList = new ArrayList();
			if (nullable)
				optionList.add(new HashMap().put("��ѡ��", ""));
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			try {
				Object obj = it.next();
				String mname = "get" + name.substring(0, 1).toUpperCase()
						+ name.substring(1);
				String mid = "get" + id.substring(0, 1).toUpperCase()
						+ id.substring(1);
				Class[] types = new Class[] {};
				Method method = obj.getClass().getMethod(mname, types);
				Object objValue = method.invoke(obj, new Object[0]);
				String nameValue = objValue == null ? "" : objValue.toString();
				method = obj.getClass().getMethod(mid, types);
				String idValue = method.invoke(obj, new Object[0]).toString();
				optionList.add(new HashMap().put(nameValue, idValue));
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("δ����ȷ��ȡ��ݣ�name=" + name + " id=" + id);
			}
		}
		return optionList;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public int getCurrentPageNumber(String tableId) {
		this.tableId = tableId;
		return getCurrentPageNumber();
	}

	public int getCurrentPageNumber() {
		String rpname = new org.displaytag.util.ParamEncoder(tableId)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		String pageNum = request.getParameter(rpname);
		return pageNum == null ? 1 : (new Integer(pageNum)).intValue();
	}

	public int getOrderID(String tableId) {
		this.tableId = tableId;
		return getOrderID();
	}

	public int getOrderID() {
		String rpname = new org.displaytag.util.ParamEncoder(tableId)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER);
		String orderId = this.request.getParameter(rpname);
		return orderId == null ? 0 : (new Integer(orderId)).intValue();
	}

	public String getOrderName() {
		String rpname = new org.displaytag.util.ParamEncoder(tableId)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT);
		String order = this.request.getParameter(rpname);
		return order == null ? "" : order;
	}

	public boolean isExport() {
		String rpname = new org.displaytag.util.ParamEncoder(tableId)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE);
		String order = this.request.getParameter(rpname);
		return order == null ? false : true;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

}
