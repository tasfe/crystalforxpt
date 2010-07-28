package crystal.common.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Telnet {
	private Socket conn;
	private BufferedReader echo;
	private PrintWriter pw;
	private String result;

	public void check(String pwd, String supPwd) throws IOException// telnet时的密码验证,二级验证(思科交换机)
	{
		pw.println(pwd);
		pw.println("en");
		pw.println(supPwd);
	}

	public void check_pwd(String pwd) throws IOException// telnet时的密码验证,一级验证(通用)
	{
		pw.println(pwd);
		pw.println("\n");
	}

	public void intoConfState() throws IOException// 进入思科交换机配置模式
	{
		pw.println("configure terminal");
	}

	public void intoConfState_huawei() throws IOException// 进入华为交换机配置模式
	{
		pw.println("system-view");
	}

	public void IpMacBind(String ip, String mac)// 思科IP-MAC绑定
	{
		try {
			System.out.println("IP绑定");
			pw.println("arp " + ip + " " + mac + " arpa");
			pw.println("\n");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public void UnIpMacBind(String ip, String mac)// 思科解除IP-MAC绑定
	{
		try {
			System.out.println("解除绑定");
			pw.println("no arp " + ip + " " + mac + " arpa");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public void macPortBind(String mac, String port) throws IOException // 思科PORT-MAC绑定
	{
		pw.println("mac-address-table secure " + mac + " " + port);
	}

	public void macPortUnBind(String mac) throws IOException // 思科解除PORT-MAC绑定
	{
		pw.println("no mac-address-table secure " + mac);
	}

	public void read() { // 读取输入流中的数据并返回(通用)

		// 读取缓冲区内容
		System.out.println("准备读取缓冲区内容...");
		try {
			String buff = "";

			do {
				System.out.println(buff);
				buff = echo.readLine();
			} while (buff != null);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}// 等待读取缓冲区
		} catch (IOException ioe) {
			System.out.println("Exception caught printing process output.");
			ioe.printStackTrace();
		}
	}

	public void ipMacBind_huaWei(String ip, String mac) {
		pw.println("arp static " + ip + " " + mac);
	}

	public void UnIpMacBind_huaWei(String ip, String mac) {
		pw.println("undo arp  " + ip);
	}

	public void portMacBind_huaWei(String ip, String mac, String vlan,
			String port) {
		pw.println("arp static " + ip + " " + mac + " " + vlan + " " + port);
	}

	public void exit_cisco_config()// 退出思科交换机
	{
		pw.println("end");
		pw.println("\n");

		pw.println("write memory");
		pw.println("\n");

		pw.println("exit");
		pw.println("\n");
	}

	public void exit_huawei()// 退出华为交换机
	{
		pw.println("quit");
		pw.println("\n");

		pw.println("save");
		pw.println("\n");
		pw.println("y");
		pw.println("\n");
		pw.println("\n");

		pw.println("y");
		pw.println("\n");

		pw.println("quit");
		pw.println("\n");

	}

	public void close() {
		try {
			echo.close();
			pw.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询某交换机下所有端口的MAC地址列表
	public Map getMacs(String switchCode) throws IOException // 思科
	{

		pw.println("show mac-address-table\n");
		pw.flush();

		String buff = null;
		Map map = new HashMap();
		while (!(buff = echo.readLine()).equals(switchCode + "#")) {
			System.out.println(buff);
			if (buff.indexOf(".") > 0) {
				String macAddr = buff.substring(buff.indexOf(".") - 4, buff
						.indexOf(".") + 10);
				String port = "";
				String mac_port = "";
				if (buff.indexOf("Gi") > 0) {
					port = buff.substring(buff.indexOf("Gi"));
					map.put(macAddr, port);
				} else if (buff.indexOf("F") > 0) {
					port = buff.substring(buff.indexOf("F"));
					map.put(macAddr, port);
				}

				// mac_port=mac_port+macAddr;
				System.out.println(mac_port);
				// map.put(macAddr, port);
			}
			pw.println(" "); // 发送空格键显示下一页
			pw.flush();
		}
		return map;
	}

	// 查询某交换机下某个端口下的MAC地址列表
	public Vector getMacsByPort(String switchCode, String port)
			throws IOException // 思科
	{

		pw.println("show mac-address-table int " + port + " \n");
		pw.flush();

		String buff = null;
		Vector v = new Vector();

		while (!(buff = echo.readLine()).equals(switchCode + "#")) {
			if (buff.indexOf(".") > 0) {
				System.out.println(buff);
				String macAddr = buff.substring(buff.indexOf(".") - 4, buff
						.indexOf(".") + 10);
				System.out.println("mac_port=" + macAddr);
				v.add(macAddr);
			}
			pw.println(" "); // 发送空格键显示下一页
			pw.flush();
		}
		return v;
	}

	public Telnet(String host) // 
	{
		try {
			host = host.trim();
			conn = new Socket();
			conn.connect(new InetSocketAddress(host, 23), 50000);// Socket连接
			// 通过桥转换，将socket得到的字节输入流按照gb2312编码方式转换为字符输入流，再封装为带缓冲的过滤流
			echo = new BufferedReader(new InputStreamReader(conn
					.getInputStream(), "gb2312"));

			// 直接使用PrintWriter这个过滤流封装字节输出流为字符流，并带有缓冲，比BufferedWriter方法更多。
			pw = new PrintWriter(conn.getOutputStream(), true);// auto flush

			result += "Connected to" + conn.getInetAddress() + "  "
					+ conn.getPort() + "\n";
		} catch (IOException ex) {
			if (ex.getMessage().indexOf("Connection reset") != -1)// 服务器关闭
				result += "Server down!\n";
			if (ex.getMessage().indexOf("Connection refused connect") != -1)// 服务器不通
				result += "connection refused!nplease check network!\n";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		try {
			Telnet t = new Telnet("192.168.1.1");
			t.check("cisco123", "genprof");
			// t.intoConfState();
			Map map = t.getMacs("ceshi");
			Set s = map.entrySet();
			Iterator it = s.iterator();
			t.close();
			while (it.hasNext()) {
				Map.Entry map1 = (Map.Entry) it.next();
				// System.out.print(map1+"\t");
				System.out.println("key:" + map1.getKey() + "\tvalue:"
						+ map1.getValue());
			}

			// t.read();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
