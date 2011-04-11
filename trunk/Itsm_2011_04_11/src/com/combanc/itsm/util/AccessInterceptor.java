package com.combanc.itsm.util;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AccessInterceptor implements Interceptor {
	public void destroy(){
	}
	
	public void init(){		
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		String name = actionContext.getName();
		Map session = actionContext.getSession();
		if(name.equals("login")||name.equals("indexUI")||name.equals("adminlogin")||name.equals("adminsave")
				||name.equals("showUI")||name.equals("listUI")||name.equals("dlpsUI")) return actionInvocation.invoke();
		else if(session.get("user") == null) {
			return "login";
		}
		return actionInvocation.invoke();// go on
	}

}
