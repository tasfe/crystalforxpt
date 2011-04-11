package com.combanc.commonsupport.dataaccess.mysql;

import com.combanc.commonsupport.dataaccess.Abstract.AbstractChart;
import com.combanc.itsm.util.OperationUtil;
public class MysqlChart extends AbstractChart {
	/*
	 * 事件数目
	 */
	public String getDailyIncRequestCount(String startTime, String endTime){
		String sql = "";
		if(null==startTime||startTime==""){
			return sql;
		}
		if(null==endTime||endTime==""){
			return sql;
		}
		sql = " select count(*) as num,DATE_FORMAT(submint_time,'%Y-%m-%d' )  as daily  from   (select id, submint_time  from service_request where DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i') > '"+startTime+"'  and   DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i')  < '"+endTime+"'  ) as inc group by daily  order by daily ";
		return sql;
	}
	/*
	 * 事件平均处理时长
	 */
	public String getDailyIncDealTime(String startTime, String endTime){
		String sql = "";
		if(null==startTime||startTime==""){
			return sql;
		}
		if(null==endTime||endTime==""){
			return sql;
		}
		sql = " select id, submint_time as SUBMITTIME, finish_time as FACTFINISHTIME, DATE_FORMAT(submint_time,'%Y-%m-%d' )  as daily  from service_request where DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i') > '"+startTime+"'  and   DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i')  < '"+endTime+"'  order by daily ";
		return sql;
	}
	/*
	 * 事件请求状态，关闭与未关闭
	 */
	public String getIncidentState(String startTime,String endTime){
		String sql = "";
		if(null==startTime||startTime==""){
			return sql;
		}
		if(null==endTime||endTime==""){
			return sql;
		}
		sql = " select count(*) as num, is_finished as flowendstate from service_request where DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i') > '"+startTime+"'  and   DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i')  < '"+endTime+"'   group by flowendstate";
		return sql;
	}
	/*
	 * 工程师处理请求统计
	 */
	public String getStatistic(String startTime,String endTime,String otherCondition){
		String sql = "";
		if(null==startTime||startTime==""){
			return sql;
		}
		if(null==endTime||endTime==""){
			return sql;
		}
		sql=" select  truename ,state,count(*) as num from service_request r1,users r2 where ( r1.state=1 or r1.state=2 ) and r1.engineerid=r2.id and DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i') > '"+startTime+"'  and   DATE_FORMAT(submint_time, '%Y-%m-%d %H:%i')  < '"+endTime+"' group by truename,state order by truename";
		return sql;
	}
	public String getWorkFinish(int engineerId,String startTime,String endTime){
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append(" select  t2.truename as username,count(*) as num from service_tran t1,users t2 ");
		strBuilder.append(" where t1.service_from=t2.id and ( t1.type ='"+OperationUtil.SOLVE_CN+"' or t1.type='"+OperationUtil.CLOSE_CN+"') ");
		if(engineerId!=0){
			strBuilder.append(" and t1.service_from="+engineerId+" ");
		}
		if(null!=startTime&&!startTime.equals("")){
			strBuilder.append(" and DATE_FORMAT(t1.operator_time, '%Y-%m-%d %H:%i') > '"+startTime+"'");
		}
		if(null!=endTime&&!endTime.equals("")){
			strBuilder.append(" and DATE_FORMAT(t1.operator_time, '%Y-%m-%d %H:%i') < '"+endTime+"'");
		}
		strBuilder.append(" group by username order by username");
		return strBuilder.toString();
	}
	public String getWorkFinishDetail(int engineerId,String startTime,String endTime){
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append(" select TIMESTAMPDIFF(HOUR,submint_time,finish_time ) as diff,t3.truename as username ,t1.request_no as requno from service_request t1,service_tran t2,users t3 ");
		strBuilder.append(" where t2.service_from=t3.id and  t1.request_no=t2.requ_no and ( t2.type ='"+OperationUtil.SOLVE_CN+"' or t2.type='"+OperationUtil.CLOSE_CN+"') ");
		if(engineerId!=0){
			strBuilder.append(" and t2.service_from="+engineerId+" ");
		}
		if(null!=startTime&&!startTime.equals("")){
			strBuilder.append(" and DATE_FORMAT(t2.operator_time, '%Y-%m-%d %H:%i') > '"+startTime+"'");
		}
		if(null!=endTime&&!endTime.equals("")){
			strBuilder.append(" and DATE_FORMAT(t2.operator_time, '%Y-%m-%d %H:%i') < '"+endTime+"'");
		}
		strBuilder.append(" order by username");
		return strBuilder.toString();
	}
}
