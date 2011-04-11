package com.combanc.monitor.vo.systemParam;

/**
 * <p>
 * Title:故障轮询系统设置
 * </p>
 * <p>
 * Description:设置间隔
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class FaultScan {
	
	/**故障检查间隔**/
	private String CheckGap;
	/**异常后的监测时间间隔**/
	private String ErrorGap;
	
	public FaultScan(){
		
	}
	public String getCheckGap() {
		return CheckGap;
	}
	public void setCheckGap(String checkGap) {
		CheckGap = checkGap;
	}
	public String getErrorGap() {
		return ErrorGap;
	}
	public void setErrorGap(String errorGap) {
		ErrorGap = errorGap;
	}
	
	
}
