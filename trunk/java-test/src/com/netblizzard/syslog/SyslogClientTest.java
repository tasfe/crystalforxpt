package com.netblizzard.syslog;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;

public class SyslogClientTest {
	public static void main(String args[]) {
		// Syslog client for sending syslog to a particular host and port.
		SyslogIF client = Syslog.getInstance("udp");
		client.getConfig().setHost("192.168.70.2");
		client.getConfig().setPort(514);				// udp default port
		client.error("Log Message");
		client.warn("Structured Test Log Message");
	}
}
