package com.combanc.monitor.quartz;


import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.combanc.monitor.comet.DwrService;
import com.combanc.monitor.constants.SystemParamConstants;


public class ScanLoopTimer extends BaseScan{
	

	private ComputerScan computerScan;
	
	private DwrService dwrService;
	
	public ScanLoopTimer(){
		 paramCode = SystemParamConstants.SYSTEM_SCAN_GAP_TIME;
		 defaultCronExpression = "0 0 10,16,20 * * ?";
		 cronTriggerName ="cronTriggerScanLoopTimerTask";
	}
	
	public void startScan(){
		dwrService.displayInfo("计算机扫描开始......");
		if(!ComputerScan.scanLoopRunning){
			computerScan = new ComputerScan(ctx);
			computerScan.start();
		}
	}
	
	public void stopScan(){
		ComputerScan.scanLoopRunning = false;
		if (computerScan != null) {
			if (computerScan.isAlive()) {
				computerScan.stop();

			}
			// 确保内存收集程序能清除。
			computerScan = null;
		}
		dwrService.displayInfo("计算机扫描终止." );
		 
	}


	public ComputerScan getComputerScan() {
		return computerScan;
	}

	public void setComputerScan(ComputerScan computerScan) {
		this.computerScan = computerScan;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
		
	}

	public DwrService getDwrService() {
		return dwrService;
	}

	public void setDwrService(DwrService dwrService) {
		this.dwrService = dwrService;
	}

	 
	
	

}
