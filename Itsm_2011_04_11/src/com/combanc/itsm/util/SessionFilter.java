package com.combanc.itsm.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SessionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {

		if (((HttpServletRequest) request).getSession().getAttribute("user") == null) {// session不存在需要拦截
			
			String wapString = ((HttpServletRequest) request).getParameter("wap");
			String wapString2 = (String) ((HttpServletRequest) request).getSession().getAttribute("wap");
			if (wapString != null) {
				((HttpServletRequest) request).getSession().setAttribute("wap",wapString);
				((HttpServletRequest) request).getRequestDispatcher("/loginwap.jsp").forward(request, response);
			} else {
				if (wapString2 != null) {				
					chain.doFilter(request, response);
				} else {
					((HttpServletRequest) request).getRequestDispatcher("/indexbank.jsp").forward(request, response);
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
