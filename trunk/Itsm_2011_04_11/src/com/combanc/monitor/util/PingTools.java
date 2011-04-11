package com.combanc.monitor.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import com.combanc.monitor.pojo.MonitorPingResult;

public class PingTools {

	public PingTools() {
		;
	}
	
	private static void InitPingResult(MonitorPingResult res, int n) {
		res.setMaxReplyTime(-1);
		res.setMinReplyTime(-1);
		res.setSendCount(n);
		res.setSuccessCount(0);
		res.setSumReplyTime(0);
		res.setSumTtl(0);
	}
	public static MonitorPingResult ping(String destIp, int n, int size, int timeout) {
		MonitorPingResult res = new MonitorPingResult();
		InitPingResult(res, n);
        try
        {
        	String lineStr = "";
            Process process;
            process = Runtime.getRuntime().exec( "cmd /c " + 
            		"ping " + " -n " + n + " -l " + size + " -w " + timeout + " " + destIp );
            BufferedReader br = new BufferedReader( new InputStreamReader(process.getInputStream() ) );
            while ( ( lineStr = br.readLine() ) != null )
            {
            	int offsetByte = -1;
            	int offsetTime = -1;
            	int offsetTtl  = -1;
            	int nByte = 0;
            	int nTime = 0;
            	int nTtl  = 0;
                if (lineStr.startsWith("Reply")) {
                	//System.out.println(" 正在Ping " + destIp + " ......");
                	//PingViewFrame.jLabelStatus.setText(" 正在Ping " + destIp + " ......");
                	offsetByte = lineStr.indexOf("bytes");
                	offsetTime = lineStr.indexOf("time");
                	offsetTtl  = lineStr.indexOf("TTL");
                	nByte = Integer.parseInt(lineStr.substring(offsetByte+6, offsetTime-1));
                	if (lineStr.charAt(offsetTime+4) == '=') {
                		nTime = Integer.parseInt(lineStr.substring(offsetTime+5, offsetTtl-3));
                	} else {
                		nTime = 0;
                	}
                	nTtl = Integer.parseInt(lineStr.substring(offsetTtl+4));
                	
                	if (res.getMaxReplyTime() < nTime) {
                		res.setMaxReplyTime(nTime);
                	}
                	if (res.getMinReplyTime() > nTime || res.getMinReplyTime() == -1) {
                		res.setMinReplyTime(nTime);
                	}
                	res.setSumReplyTime(res.getSumReplyTime()+nTime);
                	res.setSuccessCount(res.getSuccessCount()+1);
                	res.setSumTtl(res.getSumTtl()+nTtl);
                } else if (lineStr.startsWith("来自") && !lineStr.endsWith("。")) {
                	//System.out.println(lineStr);
                	//PingViewFrame.jLabelStatus.setText(" 正在Ping " + destIp + " ......");
                	offsetByte = lineStr.indexOf("字节");
                	offsetTime = lineStr.indexOf("时间");
                	offsetTtl  = lineStr.indexOf("TTL");
                	nByte = Integer.parseInt(lineStr.substring(offsetByte+3, offsetTime-1));
                	if (lineStr.charAt(offsetTime+2) == '=') {
                		nTime = Integer.parseInt(lineStr.substring(offsetTime+3, offsetTtl-3));
                	} else {
                		nTime = 0;
                	}
                	nTtl = Integer.parseInt(lineStr.substring(offsetTtl+4));
                	
                	if (res.getMaxReplyTime() < nTime) {
                		res.setMaxReplyTime(nTime);
                	}
                	if (res.getMinReplyTime() > nTime || res.getMinReplyTime() == -1) {
                		res.setMinReplyTime(nTime);
                	}
                	res.setSumReplyTime(res.getSumReplyTime()+nTime);
                	res.setSuccessCount(res.getSuccessCount()+1);
                	res.setSumTtl(res.getSumTtl()+nTtl);
                }
            }

            process.waitFor();
            if (res.getMaxReplyTime() == -1) {
            	res.setMaxReplyTime(0);
            }
            if (res.getMinReplyTime() == -1) {
            	res.setMinReplyTime(0);
            }
            res.setCompletedTime(new Timestamp(System.currentTimeMillis()));
            /*System.out.println("res.getMaxReplyTime() = " + res.getMaxReplyTime());
            System.out.println("res.getMinReplyTime() = " + res.getMinReplyTime());
            System.out.println("res.getSumReplyTime() = " + res.getSumReplyTime());
            System.out.println("res.getSendCount() = " + res.getSendCount());
            System.out.println("res.getSuccessCount() = " + res.getSuccessCount());
            System.out.println("res.getSumTtl() = " + res.getSumTtl());*/
            return res;
            
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return null;
        }
	}
}
