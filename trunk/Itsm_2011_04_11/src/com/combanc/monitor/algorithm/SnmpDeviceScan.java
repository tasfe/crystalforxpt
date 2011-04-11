package com.combanc.monitor.algorithm;

import java.util.List;


import com.adventnet.snmp.beans.SnmpTarget;
import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorDeviceType;

/**
 * <p>
 * Title: 发现给定子网中不重复的SNMP对象，并给出正确的类型
 * </p>
 */

public class SnmpDeviceScan {
	int maxThreadNum = 64;// 最大线程数
	String snmpCommStr;
	SnmpIPScan[] ipScan = new SnmpIPScan[maxThreadNum];// 线程数组
	int threadNum;// 线程计数
	// 用来保存当前线程波的发现结果：IP、描述、名称、类型
	String[][] threadReturn = new String[maxThreadNum][4];

	public SnmpDeviceScan() {
	}

	// 扫描起止IP地址范围：
	public boolean snmpScanSubnet(String sip, String eip, String commStr,
			List<MonitorDevice> monitorDeviceList, List<MonitorDeviceType> monitorDeviceTypeList, List<MonitorDevice> newMonitorDeviceList) {
		snmpCommStr = commStr;
		String ipstr = "";
		String preStr1, preStr2; // 存储截取IPv6地址的前缀，后缀，后缀循环，合并地址之后进行snmp搜索
		long postStr1, postStr2;
		int m, n;
		long tmp;
		long sl = Tools.getIpLong(sip); // 取得起始地址的长整数
		long el = Tools.getIpLong(eip); // 取得结束地址的长整数

		// 初始化：
		threadNum = 0;
		for (m = 0; m < maxThreadNum; m++)
			for (n = 0; n < 2; n++)
				threadReturn[m][n] = "-";
		if (sl == -1 || el == -1) {
			// 请修改考虑一种情况，起始地址分别为IPv4和IPv6地址时并不报错。
			// if所代表的是IPv4到 v6的映射形式的地址，else代表只包含":"的标准IPv6地址
			if (sip.indexOf(".") > 0) {
				preStr1 = sip.substring(0, sip.lastIndexOf("."));
				postStr1 = Long.parseLong(sip.substring(sip.lastIndexOf(".") + 1));
				preStr2 = eip.substring(0, eip.lastIndexOf("."));
				postStr2 = Long.parseLong(eip.substring(eip.lastIndexOf(".") + 1));
				if (!preStr1.equals(preStr2))
					return false;
				for (long l = postStr1; l <= postStr2; l++) {
					ipstr = preStr1 + "." + String.valueOf(l);
					display(" SNMP对象发现，地址：" + ipstr);
					if (threadNum < maxThreadNum) {
						ipScan[threadNum] = new SnmpIPScan(ipstr + ":" + threadNum);
						ipScan[threadNum].start();
						try {
							Thread.sleep(MainConstants.THREAD_GAP);
						} catch (InterruptedException e) {
						}
						;
						threadNum++;
						continue;
					} else {
						// 线程数组满后，等待它们结束。
						display(" SNMP对象发现，等待应答...");
						waitThreadGroupEnd();
						// 将返回结果转存到档案表，在转存的过程中显示发现的结果。
						saveSnmpResult(monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
						// 清空返回结果数组，为下一波次的线程做准备。
						threadNum = 0;
						for (m = 0; m < maxThreadNum; m++)
							for (n = 0; n < 2; n++)
								threadReturn[m][n] = "-";

						ipScan[threadNum] = new SnmpIPScan(ipstr + ":" + threadNum);
						ipScan[threadNum].start();
						try {
							Thread.sleep(MainConstants.THREAD_GAP);
						} catch (InterruptedException e) {
						}
						;
						threadNum++;
					}
				}
				// 等待剩余的线程结束。
				display(" SNMP对象发现，等待应答...");
				waitThreadGroupEnd();
				saveSnmpResult(monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
				display(" SNMP对象发现完毕。");
				return true;
			}
			// 针对IPv6地址的搜索
			else {
				preStr1 = sip.substring(0, sip.lastIndexOf(":"));
				postStr1 = Long.parseLong(sip.substring(sip.lastIndexOf(":") + 1), 16);
				preStr2 = eip.substring(0, eip.lastIndexOf(":"));
				postStr2 = Long.parseLong(eip.substring(eip.lastIndexOf(":") + 1), 16);
				if (!preStr1.equals(preStr2))
					return false;
				for (long l = postStr1; l <= postStr2; l++) {
					ipstr = preStr1 + ":" + Long.toHexString(l);
					display(" SNMP对象发现，地址：" + ipstr);
					if (threadNum < maxThreadNum) {
						ipScan[threadNum] = new SnmpIPScan(ipstr + ":"+ threadNum);
						ipScan[threadNum].start();
						try {
							Thread.sleep(MainConstants.THREAD_GAP);
						} catch (InterruptedException e) {
						}
						;
						threadNum++;
						continue;
					}
					// 线程数组满后，等待它们结束。
					display(" SNMP对象发现，等待应答...");
					waitThreadGroupEnd();
					// 将返回结果转存到档案表，在转存的过程中显示发现的结果。
					saveSnmpResult(monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
					// 清空返回结果数组，为下一波次的线程做准备。
					threadNum = 0;
					for (m = 0; m < maxThreadNum; m++)
						for (n = 0; n < 2; n++)
							threadReturn[m][n] = "-";

					ipScan[threadNum] = new SnmpIPScan(ipstr + ":" + threadNum);
					ipScan[threadNum].start();
					try {
						Thread.sleep(MainConstants.THREAD_GAP);
					} catch (InterruptedException e) {
					}
					;
					threadNum++;
				}
				// 等待尾巴线程结束。
				display(" SNMP对象发现，等待应答...");
				waitThreadGroupEnd();
				saveSnmpResult(monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
				display(" SNMP对象发现完毕。");
				return true;
			}
		} else {
			// 生成起止地址间的每一个地址，并加到线程数组中。
			for (long l = sl; l <= el; l++) {
				tmp = l;
				ipstr = "";
				// 模除256，从最后一位开始得到IP地址的每一字节。
				for (int i = 0; i < 4; i++) {
					if (i < 3) { // 二、三、四字节前有点。
						ipstr = "." + (tmp % 256) + ipstr;
						tmp /= 256;
					} else { // 第一字节前无点。
						ipstr = (tmp % 256) + ipstr;
					}
				}

				display(" SNMP对象发现，地址：" + ipstr);
				if (threadNum < maxThreadNum) {
					ipScan[threadNum] = new SnmpIPScan(ipstr + ":" + threadNum);
					ipScan[threadNum].start();
					try {
						Thread.sleep(MainConstants.THREAD_GAP);
					} catch (InterruptedException e) {
					}
					;
					threadNum++;
					continue;
				} else {
					// 线程数组满后，等待它们结束。
					display(" SNMP对象发现，等待应答...");
					waitThreadGroupEnd();
					// 将返回结果转存到档案表，在转存的过程中显示发现的结果。
					saveSnmpResult(monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
					// 清空返回结果数组，为下一波次的线程做准备。
					threadNum = 0;
					for (m = 0; m < maxThreadNum; m++)
						for (n = 0; n < 2; n++)
							threadReturn[m][n] = "-";

					ipScan[threadNum] = new SnmpIPScan(ipstr + ":" + threadNum);
					ipScan[threadNum].start();
					try {
						Thread.sleep(MainConstants.THREAD_GAP);
					} catch (InterruptedException e) {
					}
					;
					threadNum++;
				}
			} // for l
			// 等待剩余线程结束。
			display(" SNMP对象发现，等待应答...");
			waitThreadGroupEnd();
			saveSnmpResult(monitorDeviceList, monitorDeviceTypeList, newMonitorDeviceList);
			display(" SNMP对象发现完毕。");
			return true;
		}
	}// scanSubnet

	// 一个进程波次结束后，将发现结果转存到表中，同时显示发现结果。
	private void saveSnmpResult(List<MonitorDevice> monitorDeviceList, List<MonitorDeviceType> monitorDeviceTypeList, List<MonitorDevice> newMonitorDeviceList) {
		for (int i = 0; i < threadNum; i++) {
			if (!threadReturn[i][1].equals("-")) {// 描述
				MonitorDevice md = new MonitorDevice();
				// 如果做设备发现则过滤掉结果中的主机；如果做主机发现则过滤掉结果中的非主机类型。
				// 由于港湾、ALCATEL设备被作为主机发现，这里去掉过滤
				// if(scanDevice && !scanHost && type.equals("主机")) continue;
				// if(!scanDevice && scanHost && !type.equals("主机")) continue;
				
				// 如果已有该记录，则处理下一条
				if (DataTool.containDevice(monitorDeviceList, threadReturn[i][0]))
					continue;
				md.setIp(threadReturn[i][0]);
				md.setReadCommunity(snmpCommStr);
				md.setDescription(Tools.cutDescr(threadReturn[i][1]));// 简化CISCO、HUAWEI描述
				md.setName(threadReturn[i][2]);
				md.setNameCn(threadReturn[i][2]);
				// threadReturn[i][3]返回值为交换、路由、主机等
				md.setMonitorDeviceType(DataTool.findDeviceTypeByName(monitorDeviceTypeList, threadReturn[i][3]));
				md.setNote3("新增");// 初始化状态
				newMonitorDeviceList.add(md);
			}// if
		}
	}

	// 等待当前线程波次结束。
	private void waitThreadGroupEnd() {
		int k;
		boolean allEnd, current;
		// Wait all left threads to end.
		allEnd = true;
		current = false;
		while (allEnd) {
			// Use thread sleep() method to prevent process blocking. In
			// blocking model, some
			// error may appear, one known error is, 192.168.3.1 not found. Test
			// refer that
			// 1000 ms is OK.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			;
			current = false;
			for (k = 0; k < threadNum; k++) {
				// 首先判断进程是否空，如果不空再判断其是否活动，否则当进程已退出变成空时，将出错
				if (ipScan[k] != null)
					current = current || ipScan[k].isAlive();
			}
			allEnd = current;
		}// while
	}

	private void display(String str) {
		System.out.println(str);
	}

	// 注意：内部进程类！
	class SnmpIPScan extends Thread {
		public SnmpIPScan(String arg) {
			this.setName(arg);
		}

		// 通过线程名字来传递参数：IP:线程下标
		public void run() {
			String arg = this.getName();
			String[] result = new String[4];// SnmpGetnextList return, in string
											// array.
			// 格式：IP:线程下标
			// String targetHost = arg.substring(0,arg.indexOf(":"));
			String targetHost = arg.substring(0, arg.lastIndexOf(":"));
			// int threadIndex =
			// Integer.parseInt(arg.substring(arg.indexOf(":")+1,arg.length()));
			int threadIndex = Integer.parseInt(arg.substring(arg
					.lastIndexOf(":") + 1, arg.length()));
			boolean ipForward = false;
			boolean macForward = false;
			int service;
			String type = new String();

			SnmpTarget target = new SnmpTarget();
			target.setTargetHost(targetHost);
			target.setCommunity(snmpCommStr);
			target.setLoadFromCompiledMibs(true);
			target.setTimeout(MainConstants.SNMP_TIME_OUT);
			target.setRetries(MainConstants.SNMP_RETRY);

			String[] oid = new String[5];
			// .iso.org.dod.internet.mgmt.mib-2.system.sysDescr
			oid[0] = ".1.3.6.1.2.1.1.1";
			// .iso.org.dod.internet.mgmt.mib-2.system.sysName
			oid[1] = ".1.3.6.1.2.1.1.5";
			// .iso.org.dod.internet.mgmt.mib-2.system.sysServices
			oid[2] = ".1.3.6.1.2.1.1.7";
			// .iso.org.dod.internet.mgmt.mib-2.ip.ipForwarding
			oid[3] = ".1.3.6.1.2.1.4.1";
			// .iso.org.dod.internet.mgmt.mib-2.dot1dBridge.dot1dBase.dot1dBaseType
			oid[4] = ".1.3.6.1.2.1.17.1.3";

			target.setObjectIDList(oid);
			result = target.snmpGetNextList();

			if (result == null || target.getErrorCode() != 0)
				return;
			// 根据sysService得到分类
			service = getSysService(result[2]);
			if (service == 0)
				return;// 出错
			// 确认是否提供IP转发（路由）服务和MAC转发（交换）
			if (result[3] != null
					&& (result[3].equals("1") || result[3]
							.equals("forwarding(1)")))
				ipForward = true;
			if (result[4] != null
					&& (result[4].equals("2") || result[4]
							.equals("transparent-only(2)")))
				macForward = true;
			// 根据分类和转发标志得到类型：
			type = "SNMP主机";
			if (macForward && !ipForward)
				type = "交换";
			if (!macForward && ipForward)
				type = "路由";
			if (macForward && ipForward) {
				if (service == 1)
					type = "交换";
				if (service == 2)
					type = "路由";
				if (service == 3)
					type = "交换+路由";
			}

			synchronized (threadReturn) {
				threadReturn[threadIndex][0] = targetHost;
				threadReturn[threadIndex][1] = result[0];
				threadReturn[threadIndex][2] = result[1];
				threadReturn[threadIndex][3] = type;
			}
		}// run

		private int getSysService(String service) {
			int sysInt;

			try {
				sysInt = Integer.parseInt(service);
			} catch (NumberFormatException e) {
				return 0;// 错误
			}

			boolean l1 = false;// 物理层
			boolean l2 = false;// 链路层
			boolean l3 = false;// 网络层

			if (sysInt >= 64)
				sysInt -= 64;
			if (sysInt >= 32)
				sysInt -= 32;
			if (sysInt >= 16)
				sysInt -= 16;
			if (sysInt >= 8)
				sysInt -= 8;
			if (sysInt >= 4) {
				l3 = true;
				sysInt -= 4;
			}

			if (sysInt >= 2) {
				l2 = true;
				sysInt -= 2;
			}

			if (sysInt >= 1)
				l1 = true;

			if (l2 && !l3)
				return 1;// 只有二层
			if (!l2 && l3)
				return 2;// 只有三层
			if (l2 && l3)
				return 3;// 同时有二层和三层
			return 4;// 其它
		}
	}// SnmpIpScan
}
