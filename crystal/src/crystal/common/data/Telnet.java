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

	public void check(String pwd, String supPwd) throws IOException// telnetʱ��������֤,������֤(˼�ƽ�����)
	{
		pw.println(pwd);
		pw.println("en");
		pw.println(supPwd);
	}

	public void check_pwd(String pwd) throws IOException// telnetʱ��������֤,һ����֤(ͨ��)
	{
		pw.println(pwd);
		pw.println("\n");
	}

	public void intoConfState() throws IOException// ����˼�ƽ���������ģʽ
	{
		pw.println("configure terminal");
	}

	public void intoConfState_huawei() throws IOException// ���뻪Ϊ����������ģʽ
	{
		pw.println("system-view");
	}

	public void IpMacBind(String ip, String mac)// ˼��IP-MAC��
	{
		try {
			System.out.println("IP��");
			pw.println("arp " + ip + " " + mac + " arpa");
			pw.println("\n");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public void UnIpMacBind(String ip, String mac)// ˼�ƽ��IP-MAC��
	{
		try {
			System.out.println("�����");
			pw.println("no arp " + ip + " " + mac + " arpa");
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public void macPortBind(String mac, String port) throws IOException // ˼��PORT-MAC��
	{
		pw.println("mac-address-table secure " + mac + " " + port);
	}

	public void macPortUnBind(String mac) throws IOException // ˼�ƽ��PORT-MAC��
	{
		pw.println("no mac-address-table secure " + mac);
	}

	public void read() { // ��ȡ�������е����ݲ�����(ͨ��)

		// ��ȡ����������
		System.out.println("׼����ȡ����������...");
		try {
			String buff = "";

			do {
				System.out.println(buff);
				buff = echo.readLine();
			} while (buff != null);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}// �ȴ���ȡ������
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

	public void exit_cisco_config()// �˳�˼�ƽ�����
	{
		pw.println("end");
		pw.println("\n");

		pw.println("write memory");
		pw.println("\n");

		pw.println("exit");
		pw.println("\n");
	}

	public void exit_huawei()// �˳���Ϊ������
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

	// ��ѯĳ�����������ж˿ڵ�MAC��ַ�б�
	public Map getMacs(String switchCode) throws IOException // ˼��
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
			pw.println(" "); // ���Ϳո����ʾ��һҳ
			pw.flush();
		}
		return map;
	}

	// ��ѯĳ��������ĳ���˿��µ�MAC��ַ�б�
	public Vector getMacsByPort(String switchCode, String port)
			throws IOException // ˼��
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
			pw.println(" "); // ���Ϳո����ʾ��һҳ
			pw.flush();
		}
		return v;
	}

	public Telnet(String host) // 
	{
		try {
			host = host.trim();
			conn = new Socket();
			conn.connect(new InetSocketAddress(host, 23), 50000);// Socket����
			// ͨ����ת������socket�õ����ֽ�����������gb2312���뷽ʽת��Ϊ�ַ����������ٷ�װΪ������Ĺ�����
			echo = new BufferedReader(new InputStreamReader(conn
					.getInputStream(), "gb2312"));

			// ֱ��ʹ��PrintWriter�����������װ�ֽ������Ϊ�ַ����������л��壬��BufferedWriter�������ࡣ
			pw = new PrintWriter(conn.getOutputStream(), true);// auto flush

			result += "Connected to" + conn.getInetAddress() + "  "
					+ conn.getPort() + "\n";
		} catch (IOException ex) {
			if (ex.getMessage().indexOf("Connection reset") != -1)// �������ر�
				result += "Server down!\n";
			if (ex.getMessage().indexOf("Connection refused connect") != -1)// ��������ͨ
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
