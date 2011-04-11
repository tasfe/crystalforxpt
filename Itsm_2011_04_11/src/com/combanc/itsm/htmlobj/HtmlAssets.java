package com.combanc.itsm.htmlobj;

import com.combanc.itsm.pojo.AssetsBase;

public class HtmlAssets extends AssetsBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6177148921187335451L;
	private String stateString;
	private String departmentString;
	private String device_typeString;
	private String departmentCode;
	private String supportId;
	private String produceId;
	private String typeCode;
	private String placeId;

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	private AssetsBase assets;

	public AssetsBase getAssets() {
		return assets;
	}

	public void setAssets(AssetsBase assets) {
		this.assets = assets;
	}

	public String getDevice_typeString() {
		return device_typeString;
	}

	public void setDevice_typeString(String deviceTypeString) {
		device_typeString = deviceTypeString;
	}

	public String getDepartmentString() {
		return departmentString;
	}

	public String getStateString() {
		return stateString;
	}

	public void setDepartmentString(String departmentString) {
		this.departmentString = departmentString;
	}

	public void setStateString(String stateString) {
		this.stateString = stateString;
	}
}
