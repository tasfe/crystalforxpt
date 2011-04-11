package com.combanc.common.core.aop;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import com.combanc.itsm.pojo.SystemLog;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.SystemLogService;
import com.opensymphony.xwork2.ActionContext;

public class LogAspect {

	@Resource
	private SystemLogService systemLogService;

	private Log logger = LogFactory.getLog(LogAspect.class);

	public Object doSystemLog(ProceedingJoinPoint point) throws Throwable {

		String methodName = point.getSignature().getName();

		// 目标方法不为空
		if (StringUtils.isNotEmpty(methodName)) {
			// set与get方法除外
			if (!(methodName.startsWith("set") || methodName.startsWith("get"))) {

				Class targetClass = point.getTarget().getClass();
				Method method = targetClass.getMethod(methodName);

				if (method != null) {

					boolean hasAnnotation = method
							.isAnnotationPresent(Action.class);

					if (hasAnnotation) {
						Action annotation = method.getAnnotation(Action.class);

						String methodDescp = annotation.description();
						if (logger.isDebugEnabled()) {
							logger.debug("Action method:" + method.getName()
									+ " Description:" + methodDescp);
						}
						// 取到当前的操作用户
						HttpSession session = (HttpSession) ActionContext
								.getContext().getSession();
						Users appUser = (Users) session.getAttribute("user");
						if (appUser != null) {
							try {
								SystemLog sysLog = new SystemLog();
								Date date = new Date();
								Timestamp ts = new Timestamp(date.getTime());
								sysLog.setCreateDate(ts);
								sysLog.setUserId(appUser.getId());
								sysLog.setUserName(appUser.getTruename());
								sysLog.setOperation(methodDescp);
								sysLog.setLoginIp(appUser.getLastAccessIp());
								systemLogService.save(sysLog);
							} catch (Exception ex) {
								logger.error(ex.getMessage());
							}
						}

					}
				}

			}
		}
		return point.proceed();
	}

}