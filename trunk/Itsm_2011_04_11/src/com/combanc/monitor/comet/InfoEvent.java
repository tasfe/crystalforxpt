package com.combanc.monitor.comet;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * Title:
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
 * @author Combanc
 * @version 1.0
 */
public class InfoEvent extends ApplicationEvent {

	public InfoEvent(Object source){
		super(source);
	}
	
}
