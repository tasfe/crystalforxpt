package com.combanc.monitor.pojo;

import java.util.HashSet;
import java.util.Set;

import com.combanc.monitor.constants.SystemParamConstants;


/**
 * MonitorSubnet entity. @author MyEclipse Persistence Tools
 */

public class MonitorSubnet implements java.io.Serializable {

	// Fields

	private Integer id;
	private MonitorSubnet parentSubnet;
	private MonitorSubnetType monitorSubnetType;
	private String name;
	private Integer subnetOrder;
	private Integer scan;
	private Integer start;
	private String centerIp;
	private Integer nodeTextType = SystemParamConstants.SYSTEM_TOPO_NODE_TEXT_TYPE;//拓扑图设备显示内容
	private String edgeRenderer = SystemParamConstants.SYSTEM_TOPO_EDGE_RENDERER;//拓扑图连线方式：直线/折线/弧线
	private Integer linkDirection = SystemParamConstants.SYSTEM_TOPO_LINK_DIRECTION;//拓扑图链路方向
	private Integer layout = 0;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	private Set monitorLinkports = new HashSet(0);
	private Set childrenSubnet = new HashSet(0);//子菜单

	// Constructors

	/** default constructor */
	public MonitorSubnet() {
	}
	
	

	public String getCenterIp() {
		return centerIp;
	}



	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}



	/** default constructor */
	public MonitorSubnet(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public MonitorSubnet(MonitorSubnetType monitorSubnetType, String name,
			Integer subnetOrder, Integer scan, Integer start, String note1,
			String note2, String note3, String note4, Set monitorLinkports) {
		this.monitorSubnetType = monitorSubnetType;
		this.name = name;
		this.subnetOrder = subnetOrder;
		this.scan = scan;
		this.start = start;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.monitorLinkports = monitorLinkports;
	}

	// Property accessors

	
	public Integer getId() {
		return this.id;
	}

	public Integer getLinkDirection() {
		return linkDirection;
	}



	public void setLinkDirection(Integer linkDirection) {
		this.linkDirection = linkDirection;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public MonitorSubnetType getMonitorSubnetType() {
		return this.monitorSubnetType;
	}

	public void setMonitorSubnetType(MonitorSubnetType monitorSubnetType) {
		this.monitorSubnetType = monitorSubnetType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSubnetOrder() {
		return this.subnetOrder;
	}

	public void setSubnetOrder(Integer subnetOrder) {
		this.subnetOrder = subnetOrder;
	}

	public Integer getScan() {
		return this.scan;
	}

	public void setScan(Integer scan) {
		this.scan = scan;
	}

	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return this.note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public Set getMonitorLinkports() {
		return this.monitorLinkports;
	}

	public void setMonitorLinkports(Set monitorLinkports) {
		this.monitorLinkports = monitorLinkports;
	}



	public MonitorSubnet getParentSubnet() {
		return parentSubnet;
	}



	public void setParentSubnet(MonitorSubnet parentSubnet) {
		this.parentSubnet = parentSubnet;
	}



	public Set getChildrenSubnet() {
		return childrenSubnet;
	}



	public void setChildrenSubnet(Set childrenSubnet) {
		this.childrenSubnet = childrenSubnet;
	}



	public String getEdgeRenderer() {
		return edgeRenderer;
	}



	public void setEdgeRenderer(String edgeRenderer) {
		this.edgeRenderer = edgeRenderer;
	}



	public Integer getNodeTextType() {
		return nodeTextType;
	}



	public void setNodeTextType(Integer nodeTextType) {
		this.nodeTextType = nodeTextType;
	}



	public Integer getLayout() {
		return layout;
	}



	public void setLayout(Integer layout) {
		this.layout = layout;
	}



	



	

}