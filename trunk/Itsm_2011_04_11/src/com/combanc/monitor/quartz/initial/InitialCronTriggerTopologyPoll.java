package com.combanc.monitor.quartz.initial;

import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorSystemParam;
/**
 * <p>
 * Title:动态设置拓扑轮询间隔
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
public class InitialCronTriggerTopologyPoll extends BaseInitializingCronTrigger{

	public InitialCronTriggerTopologyPoll(){
		paramCode = SystemParamConstants.SYSTEM_POLL_GAP;
		defaultCronExpression = "0 0/5 * * * ?";
	}
}
