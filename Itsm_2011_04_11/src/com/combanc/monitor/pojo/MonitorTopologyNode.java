package com.combanc.monitor.pojo;



/**
 * MonitorTopologyNode entity. @author MyEclipse Persistence Tools
 */

public class MonitorTopologyNode  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private MonitorDevice device;
     private String cpuOid;
     private String cpu;
     private String cpuAverage;
     private String cpuPeak;
     private String peakTime;
     private String replyTime;
     private String runTime;
     private String status;
     private Long grandTotal;
     private Long count;
     private String time;//轮询时间

    // Constructors

    /** default constructor */
    public MonitorTopologyNode() {
    	cpuOid="";
    	cpu="";
    	cpuAverage="";
    	cpuPeak="";
    	peakTime="";
    	replyTime="";
    	runTime="";
    	status="";
    	grandTotal=0L;
    	count=0L;
    	time="";
    }

    
    /** full constructor */
    public MonitorTopologyNode(MonitorDevice device, String cpuOid, String cpu, String cpuAverage, String cpuPeak, String peakTime, String replyTime, String runTime, String status, Long grandTotal, Long count,String time) {
        this.device = device;
        this.cpuOid = cpuOid;
        this.cpu = cpu;
        this.cpuAverage = cpuAverage;
        this.cpuPeak = cpuPeak;
        this.peakTime = peakTime;
        this.replyTime = replyTime;
        this.runTime = runTime;
        this.status = status;
        this.grandTotal = grandTotal;
        this.count = count;
        this.time=time;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
 

	public MonitorDevice getDevice() {
		return device;
	}


	public void setDevice(MonitorDevice device) {
		this.device = device;
	}


	public String getCpuOid() {
        return this.cpuOid;
    }
    
    public void setCpuOid(String cpuOid) {
        this.cpuOid = cpuOid;
    }

    public String getCpu() {
        return this.cpu;
    }
    
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCpuAverage() {
        return this.cpuAverage;
    }
    
    public void setCpuAverage(String cpuAverage) {
        this.cpuAverage = cpuAverage;
    }

    public String getCpuPeak() {
        return this.cpuPeak;
    }
    
    public void setCpuPeak(String cpuPeak) {
        this.cpuPeak = cpuPeak;
    }

    public String getPeakTime() {
        return this.peakTime;
    }
    
    public void setPeakTime(String peakTime) {
        this.peakTime = peakTime;
    }

    public String getReplyTime() {
        return this.replyTime;
    }
    
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getRunTime() {
        return this.runTime;
    }
    
    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGrandTotal() {
        return this.grandTotal;
    }
    
    public void setGrandTotal(Long grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Long getCount() {
        return this.count;
    }
    
    public void setCount(Long count) {
        this.count = count;
    }


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}
   








}