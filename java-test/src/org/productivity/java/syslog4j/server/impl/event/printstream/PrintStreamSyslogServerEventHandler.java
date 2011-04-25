package org.productivity.java.syslog4j.server.impl.event.printstream;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.SyslogServerSessionEventHandlerIF;
import org.productivity.java.syslog4j.util.SyslogUtility;

import com.netblizzard.syslog.Tools;

public class PrintStreamSyslogServerEventHandler implements
		SyslogServerSessionEventHandlerIF {
	private static final long serialVersionUID = 6036415838696050746L;
	protected PrintStream stream = null;
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public PrintStreamSyslogServerEventHandler(PrintStream stream) {
		this.stream = stream;
	}

	public void initialize(SyslogServerIF syslogServer) {
	}

	public Object sessionOpened(SyslogServerIF syslogServer,
			SocketAddress socketAddress) {
		return null;
	}

	public void event(Object session, SyslogServerIF syslogServer,
			SocketAddress socketAddress, SyslogServerEventIF event) {
//		String date = (event.getDate() == null ? new Date() : event.getDate()).toString();
		Date date = event.getDate() == null ? new Date() : event.getDate();
		String facility = SyslogUtility.getFacilityString(event.getFacility());
		String level = SyslogUtility.getLevelString(event.getLevel());

		this.stream.println("modified 2011-04-25 ----> {" + facility + "} " + formatter.format(date) + " " + level + " "
				+ Tools.decodeGBK(event.getMessage()));
		this.stream.println("decodeISO8859£º" + Tools.decodeISO8859(event.getMessage()));
		try {
			this.stream.println("to gb2312£º" + new String(event.getMessage().getBytes(), "iso8859-1"));
			this.stream.println("to utf-8£º" + new String(event.getMessage().getBytes(), "utf-8"));
			this.stream.println("to utf-8£º" + new String(event.getMessage().getBytes("iso8859-1"), "utf-8"));
			this.stream.println("to utf-8£º" + new String(event.getMessage().getBytes("iso8859-1"), "gb2312"));
			this.stream.println("to utf-8£º" + new String(event.getMessage().getBytes("iso8859-1"), "gbk"));
			this.stream.println("to utf-8£º" + new String(event.getMessage().getBytes("utf-8"), "gb2312"));
			this.stream.println("to utf-8£º" + new String(event.getMessage().getBytes("utf-8"), "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void exception(Object session, SyslogServerIF syslogServer,
			SocketAddress socketAddress, Exception exception) {
	}

	public void sessionClosed(Object session, SyslogServerIF syslogServer,
			SocketAddress socketAddress, boolean timeout) {
	}

	public void destroy(SyslogServerIF syslogServer) {
	}
}
