package com.combanc.monitor.pojo;

import java.sql.Timestamp;


/**
 * MonitorTopologyEdge entity. @author MyEclipse Persistence Tools
 */

public class MonitorTopologyEdge  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String ip;
     private String interface_;
     private String downlinkIp;
     private String downlinkInterface;
     private String dataSource;
     private Long sendByte;
     private Long receiveByte;
     private Long sendUnicastPacket;
     private Long receiveUnicastPacket;
     private Long sendBroadcastPacket;
     private Long receiveBroadcastPacket;
     private Long sendWrongPacket;
     private Long receiveWrongPacket;
     private Long sendLostPacket;
     private Long receiveLostPacket;
 	 private Long diffSendByte;
	 private Long diffReceiveByte;
	 private Long diffSendUnicastPacket;
	 private Long diffReceiveUnicastPacket;
	 private Long diffSendBroadcastPacket;
	 private Long diffReceiveBroadcastPacket;
	 private Long diffSendWrongPacket;
	 private Long diffReceiveWrongPacket;
	 private Long diffSendLostPacket;
	 private Long diffReceiveLostPacket;
     private Long time;
     private Long timeGap;
     private Long interfaceRate;
     private Long bilateralPeak;
     private Long bilateralPeakTime;
     private Long sendPeak;
     private Long sendPeakTime;
     private Long receivePeak;
     private Long receivePeakTime;


    // Constructors

    /** default constructor */
    public MonitorTopologyEdge() {
    	
    	 sendByte=0L;
         receiveByte=0L;
         sendUnicastPacket=0L;
         receiveUnicastPacket=0L;
         sendBroadcastPacket=0L;
         receiveBroadcastPacket=0L;
         sendWrongPacket=0L;
         receiveWrongPacket=0L;
         sendLostPacket=0L;
         receiveLostPacket=0L;
     	 diffSendByte=0L;
    	 diffReceiveByte=0L;
    	 diffSendUnicastPacket=0L;
    	 diffReceiveUnicastPacket=0L;
    	 diffSendBroadcastPacket=0L;
    	 diffReceiveBroadcastPacket=0L;
    	 diffSendWrongPacket=0L;
    	 diffReceiveWrongPacket=0L;
    	 diffSendLostPacket=0L;
    	 diffReceiveLostPacket=0L;
         time=0L;
         timeGap=0L;
         interfaceRate=0L;
         bilateralPeak=0L;
         bilateralPeakTime=0L;
         sendPeak=0L;
         sendPeakTime=0L;
         receivePeak=0L;
         receivePeakTime=0L;
    }

    
    /** full constructor */
    public MonitorTopologyEdge(String ip, String interface_, String downlinkIp,
			String downlinkInterface, String dataSource,Long sendByte, Long receiveByte,
			Long sendUnicastPacket, Long receiveUnicastPacket,
			Long sendBroadcastPacket, Long receiveBroadcastPacket,
			Long sendWrongPacket, Long receiveWrongPacket, Long sendLostPacket,
			Long receiveLostPacket, Long diffSendByte, Long diffReceiveByte,
			Long diffSendUnicastPacket, Long diffReceiveUnicastPacket,
			Long diffSendBroadcastPacket, Long diffReceiveBroadcastPacket,
			Long diffSendWrongPacket, Long diffReceiveWrongPacket,
			Long diffSendLostPacket, Long diffReceiveLostPacket, Long time,Long timeGap,
			Long interfaceRate, Long bilateralPeak, Long bilateralPeakTime,
			Long sendPeak, Long sendPeakTime, Long receivePeak,
			Long receivePeakTime) {
		this.ip = ip;
		this.interface_ = interface_;
		this.downlinkIp = downlinkIp;
		this.downlinkInterface = downlinkInterface;
		this.dataSource=dataSource;
		this.sendByte = sendByte;
		this.receiveByte = receiveByte;
		this.sendUnicastPacket = sendUnicastPacket;
		this.receiveUnicastPacket = receiveUnicastPacket;
		this.sendBroadcastPacket = sendBroadcastPacket;
		this.receiveBroadcastPacket = receiveBroadcastPacket;
		this.sendWrongPacket = sendWrongPacket;
		this.receiveWrongPacket = receiveWrongPacket;
		this.sendLostPacket = sendLostPacket;
		this.receiveLostPacket = receiveLostPacket;
		this.diffSendByte = diffSendByte;
		this.diffReceiveByte = diffReceiveByte;
		this.diffSendUnicastPacket = diffSendUnicastPacket;
		this.diffReceiveUnicastPacket = diffReceiveUnicastPacket;
		this.diffSendBroadcastPacket = diffSendBroadcastPacket;
		this.diffReceiveBroadcastPacket = diffReceiveBroadcastPacket;
		this.diffSendWrongPacket = diffSendWrongPacket;
		this.diffReceiveWrongPacket = diffReceiveWrongPacket;
		this.diffSendLostPacket = diffSendLostPacket;
		this.diffReceiveLostPacket = diffReceiveLostPacket;
		this.time = time;
		this.timeGap = timeGap;
		this.interfaceRate = interfaceRate;
		this.bilateralPeak = bilateralPeak;
		this.bilateralPeakTime = bilateralPeakTime;
		this.sendPeak = sendPeak;
		this.sendPeakTime = sendPeakTime;
		this.receivePeak = receivePeak;
		this.receivePeakTime = receivePeakTime;
	}

   
    // Property accessors

    public String getDataSource() {
		return dataSource;
	}


	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}


	public Long getTimeGap() {
		return timeGap;
	}


	public void setTimeGap(Long timeGap) {
		this.timeGap = timeGap;
	}


	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getInterface_() {
        return this.interface_;
    }
    
    public void setInterface_(String interface_) {
        this.interface_ = interface_;
    }

    public String getDownlinkIp() {
        return this.downlinkIp;
    }
    
    public void setDownlinkIp(String downlinkIp) {
        this.downlinkIp = downlinkIp;
    }

    public String getDownlinkInterface() {
        return this.downlinkInterface;
    }
    
    public void setDownlinkInterface(String downlinkInterface) {
        this.downlinkInterface = downlinkInterface;
    }

    public Long getSendByte() {
        return this.sendByte;
    }
    
    public void setSendByte(Long sendByte) {
        this.sendByte = sendByte;
    }

    public Long getReceiveByte() {
        return this.receiveByte;
    }
    
    public void setReceiveByte(Long receiveByte) {
        this.receiveByte = receiveByte;
    }

    public Long getSendUnicastPacket() {
        return this.sendUnicastPacket;
    }
    
    public void setSendUnicastPacket(Long sendUnicastPacket) {
        this.sendUnicastPacket = sendUnicastPacket;
    }

    public Long getReceiveUnicastPacket() {
        return this.receiveUnicastPacket;
    }
    
    public void setReceiveUnicastPacket(Long receiveUnicastPacket) {
        this.receiveUnicastPacket = receiveUnicastPacket;
    }

    public Long getSendBroadcastPacket() {
        return this.sendBroadcastPacket;
    }
    
    public void setSendBroadcastPacket(Long sendBroadcastPacket) {
        this.sendBroadcastPacket = sendBroadcastPacket;
    }

    public Long getReceiveBroadcastPacket() {
        return this.receiveBroadcastPacket;
    }
    
    public void setReceiveBroadcastPacket(Long receiveBroadcastPacket) {
        this.receiveBroadcastPacket = receiveBroadcastPacket;
    }

    public Long getSendWrongPacket() {
        return this.sendWrongPacket;
    }
    
    public void setSendWrongPacket(Long sendWrongPacket) {
        this.sendWrongPacket = sendWrongPacket;
    }

    public Long getReceiveWrongPacket() {
        return this.receiveWrongPacket;
    }
    
    public void setReceiveWrongPacket(Long receiveWrongPacket) {
        this.receiveWrongPacket = receiveWrongPacket;
    }

    public Long getSendLostPacket() {
        return this.sendLostPacket;
    }
    
    public void setSendLostPacket(Long sendLostPacket) {
        this.sendLostPacket = sendLostPacket;
    }

    public Long getReceiveLostPacket() {
        return this.receiveLostPacket;
    }
    
    public void setReceiveLostPacket(Long receiveLostPacket) {
        this.receiveLostPacket = receiveLostPacket;
    }

    public Long getTime() {
        return this.time;
    }
    
    public void setTime(Long time) {
        this.time = time;
    }

    public Long getInterfaceRate() {
        return this.interfaceRate;
    }
    
    public void setInterfaceRate(Long interfaceRate) {
        this.interfaceRate = interfaceRate;
    }

    public Long getBilateralPeak() {
        return this.bilateralPeak;
    }
    
    public void setBilateralPeak(Long bilateralPeak) {
        this.bilateralPeak = bilateralPeak;
    }

    public Long getBilateralPeakTime() {
        return this.bilateralPeakTime;
    }
    
    public void setBilateralPeakTime(Long bilateralPeakTime) {
        this.bilateralPeakTime = bilateralPeakTime;
    }

    public Long getSendPeak() {
        return this.sendPeak;
    }
    
    public void setSendPeak(Long sendPeak) {
        this.sendPeak = sendPeak;
    }

    public Long getSendPeakTime() {
        return this.sendPeakTime;
    }
    
    public void setSendPeakTime(Long sendPeakTime) {
        this.sendPeakTime = sendPeakTime;
    }

    public Long getReceivePeak() {
        return this.receivePeak;
    }
    
    public void setReceivePeak(Long receivePeak) {
        this.receivePeak = receivePeak;
    }

    public Long getReceivePeakTime() {
        return this.receivePeakTime;
    }
    
    public void setReceivePeakTime(Long receivePeakTime) {
        this.receivePeakTime = receivePeakTime;
    }


	public Long getDiffSendByte() {
		return diffSendByte;
	}


	public void setDiffSendByte(Long diffSendByte) {
		this.diffSendByte = diffSendByte;
	}


	public Long getDiffReceiveByte() {
		return diffReceiveByte;
	}


	public void setDiffReceiveByte(Long diffReceiveByte) {
		this.diffReceiveByte = diffReceiveByte;
	}


	public Long getDiffSendUnicastPacket() {
		return diffSendUnicastPacket;
	}


	public void setDiffSendUnicastPacket(Long diffSendUnicastPacket) {
		this.diffSendUnicastPacket = diffSendUnicastPacket;
	}


	public Long getDiffReceiveUnicastPacket() {
		return diffReceiveUnicastPacket;
	}


	public void setDiffReceiveUnicastPacket(Long diffReceiveUnicastPacket) {
		this.diffReceiveUnicastPacket = diffReceiveUnicastPacket;
	}


	public Long getDiffSendBroadcastPacket() {
		return diffSendBroadcastPacket;
	}


	public void setDiffSendBroadcastPacket(Long diffSendBroadcastPacket) {
		this.diffSendBroadcastPacket = diffSendBroadcastPacket;
	}


	public Long getDiffReceiveBroadcastPacket() {
		return diffReceiveBroadcastPacket;
	}


	public void setDiffReceiveBroadcastPacket(Long diffReceiveBroadcastPacket) {
		this.diffReceiveBroadcastPacket = diffReceiveBroadcastPacket;
	}


	public Long getDiffSendWrongPacket() {
		return diffSendWrongPacket;
	}


	public void setDiffSendWrongPacket(Long diffSendWrongPacket) {
		this.diffSendWrongPacket = diffSendWrongPacket;
	}


	public Long getDiffReceiveWrongPacket() {
		return diffReceiveWrongPacket;
	}


	public void setDiffReceiveWrongPacket(Long diffReceiveWrongPacket) {
		this.diffReceiveWrongPacket = diffReceiveWrongPacket;
	}


	public Long getDiffSendLostPacket() {
		return diffSendLostPacket;
	}


	public void setDiffSendLostPacket(Long diffSendLostPacket) {
		this.diffSendLostPacket = diffSendLostPacket;
	}


	public Long getDiffReceiveLostPacket() {
		return diffReceiveLostPacket;
	}


	public void setDiffReceiveLostPacket(Long diffReceiveLostPacket) {
		this.diffReceiveLostPacket = diffReceiveLostPacket;
	}
   








}