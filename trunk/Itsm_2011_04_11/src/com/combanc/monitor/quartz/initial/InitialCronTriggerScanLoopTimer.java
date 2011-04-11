package com.combanc.monitor.quartz.initial;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorSystemParam;
/**
 * <p>
 * Title:动态设置计算机扫描间隔
 * </p>
 * <p>
 * Description: 
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
public class InitialCronTriggerScanLoopTimer extends BaseInitializingCronTrigger {

	public InitialCronTriggerScanLoopTimer(){
		paramCode = SystemParamConstants.SYSTEM_SCAN_GAP_TIME;
		defaultCronExpression = "0 0 10,16,20 * * ?";
	}
}
