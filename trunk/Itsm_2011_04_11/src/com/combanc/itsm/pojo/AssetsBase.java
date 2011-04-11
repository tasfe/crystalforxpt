package com.combanc.itsm.pojo;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AssetsBase entity. @author MyEclipse Persistence Tools
 */

public class AssetsBase implements java.io.Serializable {

	// Fields

	private Integer code;
	private AssetsProducer assetsProducerBySupportId;
	private AssetsProducer assetsProducerByProduceId;
	private Location location;
	private ItsmType itsmType;
	private Users usersByUsersId;
	private Department department;
	private AssetsType assetsType;
	private Users usersByChargeId;
	private Buildlocation buildlocation;
	private AssetsState assetsState;
	private String model;
	private String name;
	private String mac;
	private String devicename;
	private Timestamp inDate;
	private Timestamp buyDate;
	private Timestamp exitfacotryDate;
	private String ip;
	private String qualityTime;
	private String system;
	private Integer des;
	private Integer ishis;
	private Double price;
	private Integer valueUnit;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	private String remark6;
	private String remark7;
	private String remark8;
	private String remark9;
	private String remark10;
	private String codeId;
	private String m_des;
	private String remark11;
	private String remark12;
	private String remark13;
	private String remark14;
	private String remark15;
	private String remark16;
	private String remark17;
	private String remark18;
	private String remark19;
	private String remark20;
	private String remark21;
	private String remark22;
	private String remark23;
	private String remark24;
	private String remark25;
	private String remark26;
	private String remark27;
	private String remark28;
	private String remark29;
	private String remark30;
	private Set assetsInfos = new HashSet(0);
	private Set serviceRequests = new HashSet(0);
	private Set assetsChanges = new HashSet(0);

	// Constructors

	/** default constructor */
	public AssetsBase() {
	}

	/** full constructor */
	public AssetsBase(AssetsProducer assetsProducerBySupportId,
			AssetsProducer assetsProducerByProduceId, Location location,
			ItsmType itsmType, Users usersByUsersId, Department department,
			AssetsType assetsType, Users usersByChargeId,
			Buildlocation buildlocation, AssetsState assetsState, String model,
			String name, String mac, String devicename, Timestamp inDate,
			Timestamp buyDate, Timestamp exitfacotryDate, String ip,
			String qualityTime, String system, Integer des, Integer ishis,
			Double price, Integer valueUnit, String remark1, String remark2,
			String remark3, String remark4, String remark5, String remark6,
			String remark7, String remark8, String remark9, String remark10,
			String codeId, String m_des, String remark11, String remark12,
			String remark13, String remark14, String remark15, String remark16,
			String remark17, String remark18, String remark19, String remark20,
			String remark21, String remark22, String remark23, String remark24,
			String remark25, String remark26, String remark27, String remark28,
			String remark29, String remark30, Set assetsInfos,
			Set serviceRequests, Set assetsChanges) {
		this.assetsProducerBySupportId = assetsProducerBySupportId;
		this.assetsProducerByProduceId = assetsProducerByProduceId;
		this.location = location;
		this.itsmType = itsmType;
		this.usersByUsersId = usersByUsersId;
		this.department = department;
		this.assetsType = assetsType;
		this.usersByChargeId = usersByChargeId;
		this.buildlocation = buildlocation;
		this.assetsState = assetsState;
		this.model = model;
		this.name = name;
		this.mac = mac;
		this.devicename = devicename;
		this.inDate = inDate;
		this.buyDate = buyDate;
		this.exitfacotryDate = exitfacotryDate;
		this.ip = ip;
		this.qualityTime = qualityTime;
		this.system = system;
		this.des = des;
		this.ishis = ishis;
		this.price = price;
		this.valueUnit = valueUnit;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
		this.remark4 = remark4;
		this.remark5 = remark5;
		this.remark6 = remark6;
		this.remark7 = remark7;
		this.remark8 = remark8;
		this.remark9 = remark9;
		this.remark10 = remark10;
		this.codeId = codeId;
		this.m_des = m_des;
		this.remark11 = remark11;
		this.remark12 = remark12;
		this.remark13 = remark13;
		this.remark14 = remark14;
		this.remark15 = remark15;
		this.remark16 = remark16;
		this.remark17 = remark17;
		this.remark18 = remark18;
		this.remark19 = remark19;
		this.remark20 = remark20;
		this.remark21 = remark21;
		this.remark22 = remark22;
		this.remark23 = remark23;
		this.remark24 = remark24;
		this.remark25 = remark25;
		this.remark26 = remark26;
		this.remark27 = remark27;
		this.remark28 = remark28;
		this.remark29 = remark29;
		this.remark30 = remark30;
		this.assetsInfos = assetsInfos;
		this.serviceRequests = serviceRequests;
		this.assetsChanges = assetsChanges;
	}

	// Property accessors

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public AssetsProducer getAssetsProducerBySupportId() {
		return this.assetsProducerBySupportId;
	}

	public void setAssetsProducerBySupportId(
			AssetsProducer assetsProducerBySupportId) {
		this.assetsProducerBySupportId = assetsProducerBySupportId;
	}

	public AssetsProducer getAssetsProducerByProduceId() {
		return this.assetsProducerByProduceId;
	}

	public void setAssetsProducerByProduceId(
			AssetsProducer assetsProducerByProduceId) {
		this.assetsProducerByProduceId = assetsProducerByProduceId;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ItsmType getItsmType() {
		return this.itsmType;
	}

	public void setItsmType(ItsmType itsmType) {
		this.itsmType = itsmType;
	}

	public Users getUsersByUsersId() {
		return this.usersByUsersId;
	}

	public void setUsersByUsersId(Users usersByUsersId) {
		this.usersByUsersId = usersByUsersId;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public AssetsType getAssetsType() {
		return this.assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public Users getUsersByChargeId() {
		return this.usersByChargeId;
	}

	public void setUsersByChargeId(Users usersByChargeId) {
		this.usersByChargeId = usersByChargeId;
	}

	public Buildlocation getBuildlocation() {
		return this.buildlocation;
	}

	public void setBuildlocation(Buildlocation buildlocation) {
		this.buildlocation = buildlocation;
	}

	public AssetsState getAssetsState() {
		return this.assetsState;
	}

	public void setAssetsState(AssetsState assetsState) {
		this.assetsState = assetsState;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDevicename() {
		return this.devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public Timestamp getInDate() {
		return this.inDate;
	}

	public void setInDate(Timestamp inDate) {
		this.inDate = inDate;
	}

	public Timestamp getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}

	public Timestamp getExitfacotryDate() {
		return this.exitfacotryDate;
	}

	public void setExitfacotryDate(Timestamp exitfacotryDate) {
		this.exitfacotryDate = exitfacotryDate;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getQualityTime() {
		return this.qualityTime;
	}

	public void setQualityTime(String qualityTime) {
		this.qualityTime = qualityTime;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public Integer getDes() {
		return this.des;
	}

	public void setDes(Integer des) {
		this.des = des;
	}

	public Integer getIshis() {
		return this.ishis;
	}

	public void setIshis(Integer ishis) {
		this.ishis = ishis;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getValueUnit() {
		return this.valueUnit;
	}

	public void setValueUnit(Integer valueUnit) {
		this.valueUnit = valueUnit;
	}

	public String getRemark1() {
		return this.remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return this.remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return this.remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getRemark6() {
		return this.remark6;
	}

	public void setRemark6(String remark6) {
		this.remark6 = remark6;
	}

	public String getRemark7() {
		return this.remark7;
	}

	public void setRemark7(String remark7) {
		this.remark7 = remark7;
	}

	public String getRemark8() {
		return this.remark8;
	}

	public void setRemark8(String remark8) {
		this.remark8 = remark8;
	}

	public String getRemark9() {
		return this.remark9;
	}

	public void setRemark9(String remark9) {
		this.remark9 = remark9;
	}

	public String getRemark10() {
		return this.remark10;
	}

	public void setRemark10(String remark10) {
		this.remark10 = remark10;
	}

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getM_des() {
		return m_des;
	}

	public void setM_des(String mDes) {
		m_des = mDes;
	}

	public String getRemark11() {
		return this.remark11;
	}

	public void setRemark11(String remark11) {
		this.remark11 = remark11;
	}

	public String getRemark12() {
		return this.remark12;
	}

	public void setRemark12(String remark12) {
		this.remark12 = remark12;
	}

	public String getRemark13() {
		return this.remark13;
	}

	public void setRemark13(String remark13) {
		this.remark13 = remark13;
	}

	public String getRemark14() {
		return this.remark14;
	}

	public void setRemark14(String remark14) {
		this.remark14 = remark14;
	}

	public String getRemark15() {
		return this.remark15;
	}

	public void setRemark15(String remark15) {
		this.remark15 = remark15;
	}

	public String getRemark16() {
		return this.remark16;
	}

	public void setRemark16(String remark16) {
		this.remark16 = remark16;
	}

	public String getRemark17() {
		return this.remark17;
	}

	public void setRemark17(String remark17) {
		this.remark17 = remark17;
	}

	public String getRemark18() {
		return this.remark18;
	}

	public void setRemark18(String remark18) {
		this.remark18 = remark18;
	}

	public String getRemark19() {
		return this.remark19;
	}

	public void setRemark19(String remark19) {
		this.remark19 = remark19;
	}

	public String getRemark20() {
		return this.remark20;
	}

	public void setRemark20(String remark20) {
		this.remark20 = remark20;
	}

	public String getRemark21() {
		return this.remark21;
	}

	public void setRemark21(String remark21) {
		this.remark21 = remark21;
	}

	public String getRemark22() {
		return this.remark22;
	}

	public void setRemark22(String remark22) {
		this.remark22 = remark22;
	}

	public String getRemark23() {
		return this.remark23;
	}

	public void setRemark23(String remark23) {
		this.remark23 = remark23;
	}

	public String getRemark24() {
		return this.remark24;
	}

	public void setRemark24(String remark24) {
		this.remark24 = remark24;
	}

	public String getRemark25() {
		return this.remark25;
	}

	public void setRemark25(String remark25) {
		this.remark25 = remark25;
	}

	public String getRemark26() {
		return this.remark26;
	}

	public void setRemark26(String remark26) {
		this.remark26 = remark26;
	}

	public String getRemark27() {
		return this.remark27;
	}

	public void setRemark27(String remark27) {
		this.remark27 = remark27;
	}

	public String getRemark28() {
		return this.remark28;
	}

	public void setRemark28(String remark28) {
		this.remark28 = remark28;
	}

	public String getRemark29() {
		return this.remark29;
	}

	public void setRemark29(String remark29) {
		this.remark29 = remark29;
	}

	public String getRemark30() {
		return this.remark30;
	}

	public void setRemark30(String remark30) {
		this.remark30 = remark30;
	}

	public Set getAssetsInfos() {
		return this.assetsInfos;
	}

	public void setAssetsInfos(Set assetsInfos) {
		this.assetsInfos = assetsInfos;
	}

	public Set getServiceRequests() {
		return this.serviceRequests;
	}

	public void setServiceRequests(Set serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	public Set getAssetsChanges() {
		return this.assetsChanges;
	}

	public void setAssetsChanges(Set assetsChanges) {
		this.assetsChanges = assetsChanges;
	}

}