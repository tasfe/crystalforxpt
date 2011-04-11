package com.combanc.itsm.service.dbsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.huawei.eie.api.sm.DBSMProxy;
import com.huawei.eie.api.sm.SmSendBean;


/**
 * 
 * <p>
 * Title: mas
 * </p>
 * 
 * <p>
 * Description: <br>
 * smproxy 的示例测试类，覆盖了所有的测试接口。<br>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <br>
 * <br>
 * <br>
 * 
 * <br>
 * <br>
 * <br>
 * <p>
 * Company: www.huawei.com
 * </p>
 * 
 * @author www.huawei.com
 * @version 1.0
 */
public class DBSMProxyService {
	public DBSMProxyService() {
	}

	/**
	 * phoneNum 发送短信的目的手机
	 *  msg 短信内容
	 * 
	 * 
	 * @throws Exception
	 */
	public  static void SendMessage(ArrayList<String> phoneNum, String msg)
			throws Exception {
		DBSMProxy smproxy = createProxy();

		Send(smproxy, phoneNum, msg); // 发送消息测试接口。
		// GetReceivedSm(smproxy, 10); // 接收消息测试接口。
		// QuerysmResult(smproxy);// 查询消息测试接口。

		// 退出登陆。
		smproxy.logout();

		// 销毁连接。
		smproxy.destroy();
	}

	/**
	 * msg为发送短信信息 phoneNum为发送短信的目的手机号
	 * 
	 * @return DBSMProxy
	 * @throws Exception
	 */
	private static  DBSMProxy createProxy() throws Exception {
		DBSMProxy smproxy = new DBSMProxy();

		// "./apiconf/smApiConf.xml"
		try {
			smproxy.initConn();// 自动查找配置文件的方式初始化，参见上面函数说明。
		} catch (Exception ex) {
			try {

				smproxy.initConn("smApiConf.xml");
			} catch (Exception exx) {
				Map paras = new HashMap();

				// 企业信息机短信接口db的url。
				paras
						.put("url",
								"jdbc:microsoft:sqlserver://202.112.126.211:1393;DatabaseName=DB_CustomSMS");

				// 企业信息机短信接口db登陆用户名。
				paras.put("user", "itil201011");

				// 企业信息机短信接口db登陆的密码。
				paras.put("password", "itil@201011!");

				// 以下示例提供了3种初始化连接的方法。
				smproxy.initConn(paras);// 通过上面设定的参数初始化。

			}
		}
		// 手动查找配置文件的方式，参见上面函数说明。
		// smproxy.initConn("./apiconf/smapiconf.xml");

		// 登陆企业信息机mas短信应用接口，业务登陆用户名、业务登陆密码。
		try {

			smproxy.login("itil201011", "Itil@201011!");
		} catch (Exception ex) {
			ex.printStackTrace();
			smproxy.login("admin", "Mas12345*");
		}

		return smproxy;
	}

	/**
	 * 发送测试代码样例。
	 * 
	 * @param smproxy
	 *            DBSMProxy
	 * @param count
	 *            int
	 * @throws Exception
	 */
	private final static void Send(DBSMProxy smproxy,
			ArrayList<String> phoneNum, String msg) throws Exception {
		SmSendBean bean = new SmSendBean();
		ArrayList<String> arrs = new ArrayList();

		Iterator<String> its = phoneNum.iterator();
		while (its.hasNext()) {
			String num = (String) its.next();
			arrs.add(num);
		}

		String[] addrs = new String[arrs.size()];
		arrs.toArray(addrs);
		bean.setSmDestAddrs(addrs);

		// 构造消息内容。
		bean.setSmMsgContent(msg);
		long firstTime = System.currentTimeMillis();

		// 发送出去。
		int[] ret = null;
		int randomInt = (int) (Math.random() * 10);
		bean.setPriority((int) (randomInt * 1.0 / 2.5));
		if (randomInt >= 5) {
			bean.setSendType(bean.SMSEND_TYPE_WAPPUSH);
		} else {
			bean.setSendType(bean.SMSEND_TYPE_TEXT);
		}

		if (Math.random() * 10 >= 5) {
			ret = smproxy.sendSm(bean);
		} else {
			int ret00 = smproxy.sendMultiSm(bean);
			ret = new int[] { ret00 };
		}

		long lastTime = System.currentTimeMillis();
		System.out.println("======================================");
		System.out.println(" inserted:" + phoneNum.size()
				+ " records,consumed " + (lastTime - firstTime) + " ms");
		System.out.println(" avg Speed:" + (phoneNum.size()) * 1000.0
				/ (lastTime - firstTime));
		System.out.println("======================================");
		System.out.print("returned sm_id(s):");
		for (int index = 0; index < ret.length; index++) {
			System.out.print(" " + ret[index]);
		}
		System.out.println("");

	}

	/**
	 * 测试方法主入口。
	 * 
	 * @param args
	 *            String[]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ArrayList list = new ArrayList();
		list.add("13520457513");

		DBSMProxyService dbsmProxyService = new DBSMProxyService();
		dbsmProxyService.SendMessage(list, "测试");
	}
}
