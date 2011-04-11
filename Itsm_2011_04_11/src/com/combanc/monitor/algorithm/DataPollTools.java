package com.combanc.monitor.algorithm;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.util.Random;

import com.adventnet.snmp.beans.SnmpTarget;
import com.borland.dx.dataset.DataSet;
import com.combanc.monitor.constants.MainConstants;

public class DataPollTools {

	// 2010-08-20 修改monitor_beans
	DeviceSnmpQuery deviceSnmpQuery;
	
	public DeviceSnmpQuery getDeviceSnmpQuery() {
		return deviceSnmpQuery;
	}

	public void setDeviceSnmpQuery(DeviceSnmpQuery deviceSnmpQuery) {
		this.deviceSnmpQuery = deviceSnmpQuery;
	}

	public DataPollTools() {
	}

	// 读设备接口收发数据，共12项。传入SNMPTARGET，IP、COMMSTR、接口号及64位标志。
	public String[] getFluxData(SnmpTarget target, String host, String ifIndex,
			String commStr, String c64) {
		int errorCode, errorIndex;
		String[] OID = new String[13];
		String[] Result = new String[13];

		if (MainConstants.DEMO_MODE) {
			Result[0] = "demo";
			Result[11] = "up(1)";
			// 接口下线
			if(ifIndex.endsWith("3")) {
				Result[11] = "down(2)";
				Result[12] = "up(1)";
			}
			// 接口关闭
			if(ifIndex.endsWith("6")) {
				Result[11] = "down(2)";
				Result[12] = "down(2)";
			}
			return Result;
		}

		target.setTargetHost(host);
		target.setCommunity(commStr);
		if(c64 == null)
			c64 = "";
		// 是否为64位计数器
		//String tmp1 = c64.trim();
		//String tmp2 = Tools.support64(new DeviceSnmpQuery(null), host, commStr);
		if (c64.trim().equalsIgnoreCase("C64") && 
				"OK".equals(Tools.support64(deviceSnmpQuery, host, commStr))) {
			OID[0] = ".1.3.6.1.2.1.31.1.1.1.10." + ifIndex;// 字节发送，64位计数器
			OID[1] = ".1.3.6.1.2.1.31.1.1.1.6." + ifIndex;// 字节接收
			OID[10] = ".1.3.6.1.2.1.31.1.1.1.15." + ifIndex;// 接口速率
			target.setSnmpVersion(SnmpTarget.VERSION2C);
		} else {
			OID[0] = ".1.3.6.1.2.1.2.2.1.16." + ifIndex;// 字节发送，32位计数器
			OID[1] = ".1.3.6.1.2.1.2.2.1.10." + ifIndex;// 字节接收
			OID[10] = ".1.3.6.1.2.1.2.2.1.5." + ifIndex;// 接口速率
			target.setSnmpVersion(SnmpTarget.VERSION1);
		}

		OID[2] = ".1.3.6.1.2.1.2.2.1.17." + ifIndex;// 单播包发送
		OID[3] = ".1.3.6.1.2.1.2.2.1.11." + ifIndex;// 单播包接收
		OID[4] = ".1.3.6.1.2.1.2.2.1.18." + ifIndex;// 广播包发送
		OID[5] = ".1.3.6.1.2.1.2.2.1.12." + ifIndex;// 广播包接收
		OID[6] = ".1.3.6.1.2.1.2.2.1.20." + ifIndex;// 错包发送
		OID[7] = ".1.3.6.1.2.1.2.2.1.14." + ifIndex;// 错包接收
		OID[8] = ".1.3.6.1.2.1.2.2.1.19." + ifIndex;// 丢包发送
		OID[9] = ".1.3.6.1.2.1.2.2.1.13." + ifIndex;// 丢包接收

		OID[11] = ".1.3.6.1.2.1.2.2.1.8." + ifIndex;// 接口上线状态
		//up(1), down(2), testing(3)
		OID[12] = ".1.3.6.1.2.1.2.2.1.7." + ifIndex;// 接口操作（开闭）状态

		target.setObjectIDList(OID);
		Result = target.snmpGetList();
		
		errorCode = target.getErrorCode();
		errorIndex = target.getErrorIndex();
		// 成功读取
		if (errorCode == 0)
			return Result;
		// 超时
		if (errorCode == 22 && errorIndex == 0)
			return null;// 超时
			// 没有取到全部数据，如LINUX无广播包、错包，重读，这时返回null，改只读字节和单播包
		if (errorCode == 2) {// noSuchName：暂不考虑64位计数器
			OID = new String[7];
			OID[0] = ".1.3.6.1.2.1.2.2.1.16." + ifIndex;// 字节发送
			OID[1] = ".1.3.6.1.2.1.2.2.1.10." + ifIndex;// 字节接收
			OID[2] = ".1.3.6.1.2.1.2.2.1.17." + ifIndex;// 单播包发送
			OID[3] = ".1.3.6.1.2.1.2.2.1.11." + ifIndex;// 单播包接收
			OID[4] = ".1.3.6.1.2.1.2.2.1.5." + ifIndex;// 接口速率
			OID[5] = ".1.3.6.1.2.1.2.2.1.8." + ifIndex;// 接口上线状态
			OID[6] = ".1.3.6.1.2.1.2.2.1.7." + ifIndex;// 接口操作（开闭）状态

			target.setObjectIDList(OID);
			Result = target.snmpGetList();
			errorCode = target.getErrorCode();
			errorIndex = target.getErrorIndex();
			if (errorCode != 0) {
				return null;
			} else {
				// 按相同格式构造数据，未读数据用0填充。
				String[] tmp = new String[13];
				for (int i = 0; i <= 3; i++)
					tmp[i] = Result[i];
				for (int i = 4; i <= 9; i++)
					tmp[i] = "0";// 广播包、错包、丢包
				tmp[10] = Result[4];// 接口速率
				tmp[11] = Result[5];// 上线状态
				tmp[12] = Result[6];// 接口操作（开闭）状态
				return tmp;
			}
		}//
		return null;
	}

	// 计算并刷新当前行的有关数据。表的数据列必须对应指定的数据顺序。
	/*
	 * 0-9列是上次值，必须为： "上次发送字节","上次接收字节","上次发送单播包","上次接收单播包",
	 * "上次发送广播包","上次接收广播包","上次发送错包","上次接收错包", "上次发送丢包","上次接收丢包", //
	 * 10-19列是当前与上次的差值，必须为： "当前发送字节","当前接收字节","当前发送单播包","当前接收单播包",
	 * "当前发送广播包","当前接收广播包","当前发送错包","当前接收错包", "当前发送丢包","当前接收丢包"
	 * 必须有：当前时间、上次时间、接口速率项
	 */
	// 将字符串数组中的数据保存到DATASET的当前行的指定位置上。
	// 第十一个数据为接口速率。第十二个数据为接口状态，用来显示，不需计算。
	public void saveData(DataSet dataSet, String[] data, String c64) {
		long[] now = new long[11];// 当前值，第11个值为接口速率，初值为0
		long gap, time, timeGap;
		int i;
		if (data[0].equals("demo")) {
			Random rdm = new Random(System.currentTimeMillis()
					/ (dataSet.getRow() + 1));// 产生随机数种子
			now[0] = dataSet.getLong(0) + rdm.nextInt(10000000);// bps
			now[1] = dataSet.getLong(1) + rdm.nextInt(10000000);// bps
			now[2] = dataSet.getLong(2) + rdm.nextInt(10000);// uni
			now[3] = dataSet.getLong(3) + rdm.nextInt(10000);// uni
			now[4] = dataSet.getLong(4) + rdm.nextInt(1000);// bro
			now[5] = dataSet.getLong(5) + rdm.nextInt(1000);// bro
			now[6] = dataSet.getLong(6) + rdm.nextInt(100);// 错包
			now[7] = dataSet.getLong(7) + rdm.nextInt(100);// 错包
			now[8] = dataSet.getLong(8) + rdm.nextInt(100);// 丢包
			now[9] = dataSet.getLong(9) + rdm.nextInt(100);// 丢包
			now[10] = 100000000;// 接口速率100M，比最大随机数大10倍
		} else {
			// 如果有数据返回，则将不空并且格式正确的解析，其它使用初值0。不包括第十二个接口状态。
			// 中兴2826出现SNMP返回正确，但12个返回数中有三个为NULL的情况。
			for (i = 0; i < 11; i++)
				if (data[i] != null)
					try {
						now[i] = Long.parseLong(data[i]);
					} catch (NumberFormatException e) {
					}
		}
		// 取当前时间：
		time = System.currentTimeMillis();
		if(c64 == null)
			c64 = "";
		// 如果是第一组有效数据，将当前数据保存到表中后返回
		if (dataSet.getLong("上次时间") == 0) {
			for (i = 0; i < 10; i++)
				dataSet.setLong(i, now[i]);
			dataSet.setLong("上次时间", time);
			dataSet.setLong("当前时间", 0);
			if (c64.equals("C64"))
				dataSet.setLong("接口速率", now[10] * 1000000);
			else
				dataSet.setLong("接口速率", now[10]);
			return;
		}

		// 计算两次时间差
		timeGap = time - dataSet.getLong("上次时间");

		// 计算两次差值、当前带宽并将数据保存到表中。共5组10个数据。
		for (i = 0; i < 10; i++) {
			// 如果当前值大于等于前次值，则取差；如果小于则表明32位计数器归零，使用上次差值做近似。
			if (now[i] >= dataSet.getLong(i)) {
				gap = now[i] - dataSet.getLong(i);
				dataSet.setLong(i + 10, gap);// 刷新当前差值
			} else {
				gap = dataSet.getLong(i + 10);// 32位计数器归零，使用上次差值做近似
			}
			dataSet.setLong(i, now[i]); // 刷新前次值：
		}

		dataSet.setLong("上次时间", time);// 刷新上次时间
		dataSet.setLong("当前时间", timeGap);// 刷新时间差值
		if (c64.equals("C64"))
			dataSet.setLong("接口速率", now[10] * 1000000);
		else
			dataSet.setLong("接口速率", now[10]);
	}
}
