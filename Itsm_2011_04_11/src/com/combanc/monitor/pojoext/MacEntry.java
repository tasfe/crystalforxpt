package com.combanc.monitor.pojoext;

public class MacEntry {
	String mac;
	String uplinkDevice;
	String port;
	String interface_;
	String description;
	
	public MacEntry() {
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUplinkDevice() {
		return uplinkDevice;
	}

	public void setUplinkDevice(String uplinkDevice) {
		this.uplinkDevice = uplinkDevice;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getInterface_() {
		return interface_;
	}

	public void setInterface_(String interface1) {
		interface_ = interface1;
	}

	public String uplinkDevice() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	
	// 重写equals方法和hasCode方法，可以使用list.contains进行是否包含对象的判断
	public boolean equals(Object o) {
		if (this == o) // 先检查是否其自反性，后比较o是否为空。这样效率高
			return true;
		if (o == null)
			return false;
		if (!(o instanceof MacEntry))
			return false;
		
		MacEntry me = (MacEntry) o;
//		java.lang.NullPointerException
//				at com.combanc.monitor.pojoext.MacEntry.equals(MacEntry.java:68)
		return me.getMac().equals(this.mac) && me.getPort().equals(this.port)
				&& me.getUplinkDevice().equals(this.uplinkDevice);
	}
	
	public int hashCode() {
		int result = 17;
		result = 37 * result + mac.hashCode();
		result = 37 * result + port.hashCode();
		result = 37 * result + uplinkDevice.hashCode();
		return result;
	}
}
