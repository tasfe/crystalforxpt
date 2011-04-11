package com.combanc.monitor.quartz.initial;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorSystemParam;
/**
 * <p>
 * Title:动态设置流量及CPU采集间隔
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
public class InitialCronTriggerFiveMinutesPoll extends BaseInitializingCronTrigger{

	public InitialCronTriggerFiveMinutesPoll(){
		paramCode = SystemParamConstants.SYSTEM_FLUX_SCAN_GAP_TIME;
		defaultCronExpression = "0 0/5 * * * ?";
	}

}
