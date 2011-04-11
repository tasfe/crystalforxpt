package com.combanc.monitor.quartz.initial;

import com.combanc.monitor.constants.SystemParamConstants;
/**
 * <p>
 * Title:动态设置设备/主机检查间隔（分钟）
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
public class InitialCronTriggerHostScan extends BaseInitializingCronTrigger{

	public InitialCronTriggerHostScan(){
		paramCode = SystemParamConstants.SYSTEM_CHECK_GAP;
		defaultCronExpression = "0 0/5 * * * ?";
	}
}
