package com.combanc.monitor.constants;

public class OidConstants {
	// H3C.AP
	public static String H3C_AP_VENDOR = "H3C.AP";
	
	// RFC1213 system.sysObjectID ��ȡ���̴��
	public static String SYSTEM_OBJECT_ID = ".1.3.6.1.2.1.1.2.0";
	
	//****************************************************************
	// CISCO AP��		AIRESPACE-WIRELESS-MIB.mib
	// .iso.org.dod.internet.private.enterprises.airespace.bsnWireless.bsnAP.bsnAPTable
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnApIpAddress
	public static String AP_IP = ".1.3.6.1.4.1.14179.2.2.1.1.19";
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnAPDot3MacAddress
	public static String AP_DOT3_MAC = ".1.3.6.1.4.1.14179.2.2.1.1.1";
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnAPEthernetMacAddress
	public static String AP_MAC = ".1.3.6.1.4.1.14179.2.2.1.1.33";
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnAPName
	public static String AP_NAME = ".1.3.6.1.4.1.14179.2.2.1.1.3";
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnAPNetmask
	public static String AP_NETMASK = ".1.3.6.1.4.1.14179.2.2.1.1.26";
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnAPGateway
	public static String AP_GATEWAY = ".1.3.6.1.4.1.14179.2.2.1.1.27";
	// enterprises.airespace.bsnWireless.bsnAP.bsnAPTable.bsnAPEntry.bsnAPOperationStatus
	// associated ( 1 ) , disassociating ( 2 ) , downloading ( 3 )
	public static String AP_OPER_STATUS = ".1.3.6.1.4.1.14179.2.2.1.1.6";
	
	// CISCO ApClient��	AIRESPACE-WIRELESS-MIB.mib
	// .iso.org.dod.internet.private.enterprises.airespace.bsnWireless.bsnEss.bsnMobileStationTable
	// enterprises.airespace.bsnWireless.bsnEss.bsnMobileStationTable.bsnMobileStationEntry.bsnMobileStationMacAddress
	public static String CLIENT_MAC = ".1.3.6.1.4.1.14179.2.1.4.1.1";
	// enterprises.airespace.bsnWireless.bsnEss.bsnMobileStationTable.bsnMobileStationEntry.IpAddress
	public static String CLIENT_IP = ".1.3.6.1.4.1.14179.2.1.4.1.2";
	// enterprises.airespace.bsnWireless.bsnEss.bsnMobileStationTable.bsnMobileStationEntry.APMacAddr
	public static String CLIENT_AP_MAC = ".1.3.6.1.4.1.14179.2.1.4.1.4";
	// enterprises.airespace.bsnWireless.bsnEss.bsnMobileStationTable.bsnMobileStationEntry.bsnMobileStationStatus
	// idle ( 0 ) , aaaPending ( 1 ) , authenticated ( 2 ) , associated ( 3 ) , powersave ( 4 ) , disassociated ( 5 ) ,
	// tobedeleted ( 6 ) , probing ( 7 ) , blacklisted ( 8 )
	public static String CLIENT_STATUS = ".1.3.6.1.4.1.14179.2.1.4.1.9";
	public static String CLIENT_STATUS_ASSOCIATED = "associated(3)";
	public static String CLIENT_STATUS_ASSOCIATED1 = "3";
	
	
	//****************************************************************
	// H3C AP��		H3C-DOT11-APMT-MIB.mib
	// .iso.org.dod.internet.private.enterprises.huawei.h3c.h3cCommon.h3cDot11.h3cDot11APMT.h3cDot11APObjectGroup.h3cDot11APObjectStatusTable
	// .h3cDot11APObjectStatusEntry.h3cDot11APIPAddress
	public static String AP_IP_H3C = ".1.3.6.1.4.1.2011.10.2.75.2.1.1.1.2";
	// .h3cDot11APObjectStatusEntry.h3cDot11APMacAddress
	public static String AP_MAC_H3C = ".1.3.6.1.4.1.2011.10.2.75.2.1.1.1.3";
	// .h3cDot11APObjectStatusEntry.h3cDot11APTemplateNameOfAP
	public static String AP_NAME_H3C = ".1.3.6.1.4.1.2011.10.2.75.2.1.1.1.5";
	// .h3cDot11APObjectStatusEntry.h3cDot11APOperationStatus
	// join ( 1 ) , joinConfirm ( 2 ) , download ( 3 ) , config ( 4 ) , run ( 5 )
	public static String AP_OPER_STATUS_H3C = ".1.3.6.1.4.1.2011.10.2.75.2.1.1.1.4";
	
	// H3C client��		H3C-DOT11-STATION-MIB.mib
	// .iso.org.dod.internet.private.enterprises.huawei.h3c.h3cCommon.h3cDot11.h3cDot11STATION.h3cDot11StationMtGroup.h3cDot11StationAssociateTable
	// .h3cDot11StationAssociateEntry.h3cDot11StationMACAddress
	public static String CLIENT_MAC_H3C = ".1.3.6.1.4.1.2011.10.2.75.3.1.1.1.23";
	// .h3cDot11StationAssociateEntry.h3cDot11StationIPAddress
	public static String CLIENT_IP_H3C = ".1.3.6.1.4.1.2011.10.2.75.3.1.1.1.2";
	// .iso.org.dod.internet.private.enterprises.huawei.h3c.h3cCommon.h3cDot11.h3cDot11STATION.h3cDot11StationMtGroup.h3cDot11StationAPRelationTable
	// .h3cDot11StationAPRelationEntry.h3cDot11CurrAPID
	public static String CLIENT_AP_ID_H3C = ".1.3.6.1.4.1.2011.10.2.75.3.1.2.1.1";
	// .h3cDot11StationAssociateEntry.h3cDot11StationUpTime
	//  "The time in seconds, how long this station has been associated with this device." 
	public static String CLIENT_UPTIME_H3C = ".1.3.6.1.4.1.2011.10.2.75.3.1.1.1.5";
	
	//****************************************************************
	// AKCP�¶�
	public static String AKCP_TEMPERATURE = ".1.3.6.1.4.1.3854.1.2.2.1.16.1.3.0";
	// AKCPʪ��
	public static String AKCP_HUMILITY = ".1.3.6.1.4.1.3854.1.2.2.1.17.1.3.0";
	
	//****************************************************************
	// �豸ʵ��?rfc2737-entity.mib����ȡH3C CPUʱʹ��
	// mib-2.entityMIB.entityMIBObjects.entityPhysical.entPhysicalTable.entPhysicalClass
	public static String ENTITY_CLASS = ".1.3.6.1.2.1.47.1.1.1.1.5";
	// mib-2.entityMIB.entityMIBObjects.entityPhysical.entPhysicalTable.entPhysicalDescr
	public static String ENTITY_DESCR = ".1.3.6.1.2.1.47.1.1.1.1.2";
	// mib-2.entityMIB.entityMIBObjects.entityPhysical.entPhysicalTable.entPhysicalName
	public static String ENTITY_NAME = ".1.3.6.1.2.1.47.1.1.1.1.7";
	
	/** 字节发送 **/
	public static String IF_OUT_OCTETS = ".1.3.6.1.2.1.2.2.1.16.";
	/** 字节接收 **/
	public static String IF_IN_OCTETS = ".1.3.6.1.2.1.2.2.1.10.";
	/** 单播包发送 **/
	public static String IF_OUT_UCAST_PKTS = ".1.3.6.1.2.1.2.2.1.17.";
	/** 单播包接收 **/
	public static String IF_IN_UCAST_PKTS = ".1.3.6.1.2.1.2.2.1.11.";
	/** 广播包发送 **/
	public static String IF_OUT_NUCAST_PKTS = ".1.3.6.1.2.1.2.2.1.18.";
	/** 广播包接收 **/
	public static String IF_IN_NUCAST_PKTS = ".1.3.6.1.2.1.2.2.1.12.";
	/** 错包发送 **/
	public static String IF_OUT_ERRORS = ".1.3.6.1.2.1.2.2.1.20.";
	/** 错包接收 **/
	public static String IF_IN_ERRORS = ".1.3.6.1.2.1.2.2.1.14.";
	/** 丢包发送 **/
	public static String IF_OUT_DISCARDS = ".1.3.6.1.2.1.2.2.1.19.";
	/** 丢包接收 **/
	public static String IF_IN_DISCARDS = ".1.3.6.1.2.1.2.2.1.13.";
	
}
