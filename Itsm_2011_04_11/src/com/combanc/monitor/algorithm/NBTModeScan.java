package com.combanc.monitor.algorithm;

import com.borland.dx.dataset.DataSet;
import com.combanc.monitor.constants.MainConstants;

/**
 * <p>Title: NETBIOS扫描</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class NBTModeScan {
  int maxThreadNum = 64;//最大进程数
//NBTIPScan为内部进程类。
  NBTIPScan[] ipScan = new NBTIPScan[maxThreadNum];//线程数组
  int rowPointer;//表行指针
  int threadNum;//线程计数
//用来保存当前线程波的发现结果：主机名、计算机名、域或组、登录名
  String[][] threadReturn = new String[maxThreadNum][4];

  public NBTModeScan() {
  }

//从表的当前行开始用NETBIOS扫描表中的每个IP到表尾。
  public boolean snmpModeScan(DataSet arpDataSet,String subnet,int startRowNum){
    if(arpDataSet.isEmpty())return false;
    if(!arpDataSet.goToRow(startRowNum)) return false;

    int i,j;
//初始化：
    rowPointer = arpDataSet.getRow();
    threadNum = 0;

    for(i = 0; i < maxThreadNum; i++)
       for(j = 0; j < 4; j++)
           threadReturn[i][j] = "-";

    do{
       if(threadNum < maxThreadNum){
          display("扫描子网 "+subnet+" IP地址Windows属性，IP："+arpDataSet.getString("IP"));
//用线程名传递参数
          ipScan[threadNum] = new NBTIPScan(arpDataSet.getString("IP")+":"+threadNum);
          ipScan[threadNum].start();
//两个线程之间间隔50MS.
          try{
              Thread.sleep(MainConstants.THREAD_GAP);
          }catch(InterruptedException e){};

          threadNum++;
          continue ;
       }//if
//线程数组满后，等待它们结束。
        display("扫描子网 "+subnet+" IP地址Windows属性，等待应答...");
        waitThreadGroupEnd();
//将返回结果转存到档案表中
        snmpResultDB(arpDataSet);
//为下一波次的线程做准备。
        rowPointer = arpDataSet.getRow();
        threadNum = 0;
        for(i = 0; i < maxThreadNum; i++)
            for(j = 0; j < 4; j++)
                threadReturn[i][j] = "-";
//注意：NEXT操作已移动到了下一行，扫描当前行记录
        ipScan[threadNum] = new NBTIPScan(arpDataSet.getString("IP")+":"+threadNum);
        ipScan[threadNum].start();
//两个线程之间间隔50MS.
        try{
          Thread.sleep(MainConstants.THREAD_GAP);
        }catch(InterruptedException e){};
        threadNum++;

    }while(arpDataSet.next());
//等待尾巴线程结束。
    display("扫描子网 "+subnet+" IP地址Windows属性，等待应答...");
    waitThreadGroupEnd();
    snmpResultDB(arpDataSet);
    return true;
  }

//SNMP模式的一个进程波次结束后，将发现结果更新到表中，同时显示发现结果。
  private void snmpResultDB(DataSet arpDataSet){
    int curRow = arpDataSet.getRow();
    for(int i = 0; i < threadNum; i++){
        arpDataSet.goToRow(rowPointer + i);
//updateRow
        arpDataSet.setString("主机名",threadReturn[i][0]);
        arpDataSet.setString("计算机名",threadReturn[i][1]);
        arpDataSet.setString("域或组",threadReturn[i][2]);
        arpDataSet.setString("登录名",threadReturn[i][3]);
    }//for
    arpDataSet.goToRow(curRow);
  }

//等待当前线程波次结束。
  private void waitThreadGroupEnd(){
    int k;
    boolean allEnd,current;
//Wait all left threads to end.
    allEnd = true;
    current = false;
    while(allEnd){
      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){};
      current = false;
      for( k = 0; k < threadNum; k++){
//首先判断进程是否空，如果不空再判断其是否活动，否则当进程已退出变成空时，将出错
        if(ipScan[k] != null)
           current = current || ipScan[k].isAlive();
      }
      allEnd = current;
    }//while
  }

//根据程序不同的运行点，在不同位置进行信息显示。
  private void display(String str){
	  System.out.println(str);
  }

//注意：内部进程类！
  class NBTIPScan extends Thread{
    public NBTIPScan(String arg){
      this.setName(arg);
    }

//通过线程名字来传递参数（IP:数据下标)
    public void run()
    {
       int threadIndex;
       String ipStr,nameStr,domainStr,userStr;
       String hostStr = null;
       String arg = this.getName();

       ipStr = arg.substring(0,arg.indexOf(":"));
       threadIndex = Integer.parseInt(arg.substring(arg.lastIndexOf(":")+1,arg.length()));

       NBTCall nbt = new NBTCall(ipStr);
       nameStr = nbt.getComputerName();
       domainStr = nbt.getDomainName();
       userStr = nbt.getUserName();

       synchronized(threadReturn){
//hostStr空表示没有进行主机名解析，不空但与IP地址相等表示解析但没有得到结果
         if(hostStr != null && !hostStr.equals(ipStr))
            threadReturn[threadIndex][0] = hostStr;
         if(nameStr != null)
            threadReturn[threadIndex][1] = nameStr;
         if(domainStr != null)
            threadReturn[threadIndex][2] = domainStr;
         if(userStr != null)
            threadReturn[threadIndex][3] = userStr;
       }
    }//run
  }//NBTIpScan
}
