package com.combanc.monitor.quartz.initial;

import com.combanc.monitor.constants.SystemParamConstants;

/**
 * <p>
 * Title:异常后的检查间隔（分钟）
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
public class InitialCronTriggerErrorScan  extends BaseInitializingCronTrigger{
	
	public InitialCronTriggerErrorScan(){
		paramCode = SystemParamConstants.SYSTEM_ERROR_GAP;
		defaultCronExpression = "0 0/1 * * * ?";
	}
}
