package com.combanc.monitor.algorithm;

import com.combanc.monitor.constants.MainConstants;
import com.combanc.monitor.util.FileUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

//JNI方式获得本机MAC地址
class GetLocalMac {

  public GetLocalMac() {
	  System.setProperty("java.library.path", MainConstants.PATH);
	  final String path = FileUtils.getClassPath(MainConstants.class) + "\\com\\combanc\\monitor";
  }
  public native static int getDwNumEntries();
  public native static String getPhysAddr(int i);
  public native static void deleteDynamicArp(int i);
  public native String[] getMacs();

  static
  {
	  	System.out.println("================================    " + System.getProperty("java.library.path") + "   &&&&&&&&&&&&   " + MainConstants.PATH);
	// System.setProperty("java.library.path", Constants.path);
		System.out.println("********************************    " + System.getProperty("java.library.path"));
    System.loadLibrary("RegConnect") ;
    System.loadLibrary("ArpTable");
  }
}