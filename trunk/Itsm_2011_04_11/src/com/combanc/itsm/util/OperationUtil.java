/**
 * 
 */
package com.combanc.itsm.util;

/**
 * @author Administrator
 * 
 */
public  class OperationUtil {
	
	public static final String SUBMIT_EN = "submit";	
	public static final String ACCEPT_EN = "accept";	
	public static final String INTERVENE_EN ="intervene";	
	public static final String REFUSE_EN ="refuse";
	public static final String SATISFACTION_EN="staticfaction";
	
	public static final String ASSIGN_CN ="指派";
	public static final String REASSIGN_CN ="重新指派";
	public static final String ALERT_CN ="重新归类";
	public static final String DELAY_CN ="延期";
	public static final String FEEDBACK_CN ="关闭";
	public static final String PAUSE_CN ="挂起";
	public static final String ROUSE_CN ="唤醒";
	public static final String SUBMIT_CN ="提交申请";	
    public static final String ACCEPT_CN = "接受申请";	
	public static final String INTERVENE_CN ="干预申请";	
	public static final String SOLVE_CN ="快速解决";	
	public static final String REFUSE_CN ="拒绝申请";
	public static final String TRANSMIT_CN ="转交";
	public static final String RETURN_CN ="退回";
	public static final String CLOSE_CN ="完成";	
	public static final String SATISFACTION_CN="提交反馈";

	public static final String SAVE="您的请求已提交成功！";
	public static final String SAVE_ERROR="请填写正确的信息！";
	public static final String OPERATE="操作成功！";
	public static final String ASSIGN="任务指派成功！";
	public static final String ASSIGN_ERROR="请选择指派的工程师！";
	public static final String REASSIGN="任务已重新指派成功！";
	public static final String DELAY="延期操作成功！";
	public static final String DELAY_ERROR="请输入延期时间！";
	public static final String ACCEPT="接受请求成功！";
	public static final String ACCEPT_ERROR="请填写初步分析意见及解决方案！";
	public static final String SOLVE="操作成功，该请求已快速解决！";
	public static final String SOLVE_ERROR="请填写摘要和解决方案！";
	public static final String PAUSE="请求已挂起！";
	public static final String PAUSE_ERROR="请填写挂起原因！";
	public static final String ROUSE="请求已成功激活！";
	public static final String ROUSE_ERROR="请填写激活原因！";
	public static final String RECATE="修改类别成功！";
	public static final String TRANSMIT="转交成功！";
	public static final String TRANSMIT_ERROR="请选择要转交的工程师！";
	public static final String TRANSMITERROR="不能转交给自己！";
	public static final String RET="请求已退回！";
	public static final String RET_ERROR="请填写退回原因！";
	public static final String REFUSE="请求已拒绝！";
	public static final String REFUSE_ERROR="请填写拒绝原因！";
	public static final String FEEDBACK="反馈意见添加成功，请求已关闭！";
	public static final String FEEDBACK_ERROR="请填写反馈意见！";
	public static final String CLO="操作成功，等待用户反馈！";
	public static final String CLO_ERROR="请填写结果或解决方案！";
	
	public static final String DEPARTMENT_DELETE="该部门正在使用，不能删除！";
	public static final String EVENT_MESSAGE="您有一条新的事件服务请求，请您处理或转派！";
	
	public static String fromEntoCh(String enStr) {

		if (enStr == null)
			return "未知";
		else if (enStr.equals(SUBMIT_EN)) {
			return SUBMIT_CN;
		} else if (enStr.equals(ACCEPT_EN)) {
			return ACCEPT_CN;
		} else if (enStr.equals(INTERVENE_EN)) {
			return INTERVENE_CN;
		} else if (enStr.equals(REFUSE_EN)) {
			return REFUSE_CN;
		} else {

			return "";
		}
	}

	public static String fromChtoEn(String chStr) {
		if (chStr == null) {
			return null;
		} else if (chStr.equals(SUBMIT_CN)) {
			return SUBMIT_EN;
		} else if (chStr.equals(ACCEPT_CN)) {
			return ACCEPT_EN;
		} else if (chStr.equals(INTERVENE_CN)) {
			return INTERVENE_EN;
		} else if (chStr.equals(REFUSE_CN)) {
			return REFUSE_EN;
		} else {
			return "";
		}
	}
}
