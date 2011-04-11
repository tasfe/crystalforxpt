
package com.combanc.monitor.quartz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.combanc.monitor.algorithm.Tools;
import com.combanc.monitor.comet.DwrService;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorCpuData;
import com.combanc.monitor.pojo.MonitorCpuDataDay;
import com.combanc.monitor.pojo.MonitorCpuDataHour;
import com.combanc.monitor.pojo.MonitorDevice;
import com.combanc.monitor.pojo.MonitorInterfaceCache;
import com.combanc.monitor.pojo.MonitorLinkport;
import com.combanc.monitor.pojo.MonitorRegularData;
import com.combanc.monitor.pojo.MonitorRegularDataDay;
import com.combanc.monitor.pojo.MonitorRegularDataHour;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorCpuDataDayService;
import com.combanc.monitor.service.MonitorCpuDataHourService;
import com.combanc.monitor.service.MonitorCpuDataService;
import com.combanc.monitor.service.MonitorDeviceService;
import com.combanc.monitor.service.MonitorInterfaceCacheService;
import com.combanc.monitor.service.MonitorLinkportService;
import com.combanc.monitor.service.MonitorRegularDataDayService;
import com.combanc.monitor.service.MonitorRegularDataHourService;
import com.combanc.monitor.service.MonitorRegularDataService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.ReportPubData;
/**
 * <p>
 * Title:每天定时进行分钟向小时数据，小时向天数据的合并处理
 * </p>
 * <p>
 * Description: 对于流量数据表，小时表和天表中存的是一小时或一天中，平局的5分钟数据量，最大和最小的5分钟数据量
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: Combanc
 * </p>
 * @author 
 * @version 
 */

public class MinuteDataMergePoll {

	private MonitorDeviceService monitorDeviceService;
	
	private MonitorCpuDataDayService monitorCpuDataDayService;
	
	private MonitorCpuDataService monitorCpuDataService;
	
	private MonitorCpuDataHourService monitorCpuDataHourService;
	
	private MonitorLinkportService monitorLinkportService;
	
	private MonitorRegularDataService monitorRegularDataService;
	
	private MonitorRegularDataHourService monitorRegularDataHourService;
	
	private MonitorRegularDataDayService monitorRegularDataDayService;
	
	private MonitorSystemParamService monitorSystemParamService;
	
	private MonitorInterfaceCacheService monitorInterfaceCacheService;
	
	private DwrService dwrService;
	
	final static long ONE_DAY_LONG		= 1000*60*60*24;
	
	Timestamp endTimeMerge		= null;	// 数据合并的末尾时间戳
	
	
	List<MonitorLinkport> linkportList = new ArrayList<MonitorLinkport>(); // 互联端口表全表
	
	public MinuteDataMergePoll(){
		
	}
	
	/**
	 * 转换到当天的23:59:59.999
	 * @param ts
	 */
	@SuppressWarnings("deprecation")
	private void toDayEnd(Timestamp   ts) {
		ts.setHours(23);
		ts.setMinutes(59);
		ts.setSeconds(59);
		ts.setNanos(999999999);
	}
	private void init(){
		
		// 创建未进行统计合并的分钟数据的时间区间
		// 天数据的最新时间是起始时间，由各指标表格分别确定
		// 昨天23：59：59.999999999为结束时间
		Date yesterday = new Date();
		yesterday.setTime( (new Date()).getTime() - ONE_DAY_LONG );
		endTimeMerge = new Timestamp( yesterday.getTime() );
		toDayEnd(endTimeMerge);		// 转到23:59:59.999999999 
		
	}
	/**
	 * 合并数据,每四小时进行一次
	 */
	public void merge(){
		if (!ReportPubData.bMerge_ing) { // 没有数据处理线程正在工作
			Timestamp latestDataTime = getLatestDataTime();
			if (latestDataTime != null) {
				if (System.currentTimeMillis()
						- latestDataTime.getTime() > ReportPubData.DATA_MERGE_CYCLETIME) {
					dwrService.displayInfo("进行合并数据的操作...");
					ReportPubData.bMerge_ing = true;	// 置合并进行标识
					init();
					operateOnCpudata();
					String type=monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PARAM_FLUX_TYPE).getValue();
					if("1".equals(type)) {
						operateOnLinkportRegulardata();//根据设定，如果仅扫描互联端口，则仅合并互联端口流量
					} else {
						operateOnDeviceInterfaceRegulardata();//根据设定，如果扫描全部设备端口，则处理全部设备端口流量
					}
					ReportPubData.bMerge_ing = false;	// 清空合并进行标识
				} else {
					dwrService.displayInfo("小时数据和天数据较新，不需要进行分钟数据的合并操作！");
				} 
				
			}else {
				dwrService.displayInfo("没有得到最新合并数据的数据时间！");
			}
		}else {
			dwrService.displayInfo("上一次的合并操作还没有结束，本次合并取消！");
		}
	}
	/**
	 * 得到数据库的最新数据的时间
	 * @return
	 */
	private Timestamp getLatestDataTime() {
		// 获取互联端口表，为按端口遍历分钟流量表做准备
		linkportList = monitorLinkportService.findUniqueLegitimateList();
		
		// 如果没有得到设备列表，返回0
		if (linkportList == null || linkportList.size() <= 0)
			return null;
		MonitorLinkport lp = linkportList.get(linkportList.size() - 1);

		// 计算数据查询的起始时间（天数据的最新时间）
		Timestamp startTimeMerge = null;
		try {
			String timesql = " IP = '" + lp.getIp()
					+ "'  and INTERFACE = " + lp.getInterface_();
			String sqlQueryTime = "SELECT GATHER_TIME from monitor_regular_data_day where GATHER_TIME=(select max(GATHER_TIME) from monitor_regular_data_day where"
					+ timesql + ")";
			SqlRowSet rowset = monitorRegularDataService.queryForRowSet(sqlQueryTime);
			if (rowset != null && rowset.next()) {
				startTimeMerge = rowset.getTimestamp("GATHER_TIME");
				return startTimeMerge;
			} else {
				sqlQueryTime = "SELECT GATHER_TIME from monitor_regular_data_day where GATHER_TIME=(select min(GATHER_TIME) from monitor_regular_data_day where"
						+ timesql + ")";
				rowset = monitorRegularDataService.queryForRowSet(sqlQueryTime);
				if (rowset != null && rowset.next()) {
					startTimeMerge = rowset.getTimestamp("GATHER_TIME");
					return startTimeMerge;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			System.out.println("异常捕获在持久层:" + e.getMessage());
			return null;
		}
	}
	/**
	 * 合并cpu数据
	 */
	private void operateOnCpudata(){
		long startTime = System.currentTimeMillis();
		// 获取设备表，为按设备遍历各分钟表做准备
		List<MonitorDevice> deviceList =  monitorDeviceService.findEffectiveList();
		
		// 按设备，遍历CPU分钟数据
		if( deviceList != null && deviceList.size() > 0 ){
			List<MonitorDevice> dvList = new ArrayList<MonitorDevice>();	// 存放设备的列表
			for( int i=0; i<deviceList.size(); i++ ) {
				MonitorDevice dv = deviceList.get(i);
				if( Tools.ipFormatOK(dv.getIp()) ){	// 检测IP的合法性
					// 为提高查询效率，10台设备一起查
					if( dvList.size() < 9 ) {
						dvList.add(dv);
					}else{
						dvList.add(dv);
						mergeCpuData( dvList );	// 合并CPU历史数据
						dvList.clear();	
					}
				}
			}
			if( dvList.size() > 0 ){
				mergeCpuData( dvList );	// 合并CPU历史数据
				dvList.clear();	
			}
			// 清空deviceList
			deviceList.clear();
			deviceList = null;
		} 
		long endTime = System.currentTimeMillis();
		dwrService.displayInfo("合并CPU数据，共用时" + (endTime-startTime)/1000.0 + "秒" );
	}
	/**
	 * 合并昨天Cpudata分钟数据为小时数据,合并昨天Cpudata小时数据为天数据
	 * @param dvList
	 */
	private void mergeCpuData(List<MonitorDevice> dvList){
		if( dvList == null || dvList.size() <= 0 ){
			return;
		}
		// 遍历Ip的list形成SQL语句
		String sqlMerge = "SELECT distinct GATHER_TIME,IP,CPU,DATA_INDEX from monitor_cpu_data c where  ";
		boolean hasValue = false;	// 标记有无记录
		for( int i=0; i<dvList.size(); i++ ){
			
			String  deviceIP = dvList.get(i).getIp();
			// 计算数据查询的起始时间（天数据的最新时间）
			Timestamp startTimeMerge = null;
			try {
				String timesql = "  IP = '" + deviceIP + "'";
				String sqlQueryTime = "SELECT GATHER_TIME from monitor_cpu_data_day where GATHER_TIME=(select max(GATHER_TIME) from monitor_cpu_data_day where  "  + timesql + ")";
				SqlRowSet rowset = monitorCpuDataDayService.queryForRowSet(sqlQueryTime);
				if( rowset != null && rowset.next() ) {
					startTimeMerge = rowset.getTimestamp("GATHER_TIME");
				}
				else{
					sqlQueryTime = "SELECT GATHER_TIME from monitor_cpu_data where GATHER_TIME=(select min(GATHER_TIME) from monitor_cpu_data where"  + timesql + ")";
					rowset = monitorCpuDataService.queryForRowSet(sqlQueryTime);
					if( rowset != null && rowset.next() ) {
						startTimeMerge = rowset.getTimestamp("GATHER_TIME");
					}
					else {
						continue;
					}
				}
			}catch(Exception e){
				System.out.println( "MergeCpuData:" + e.getMessage() ); 
			}
			hasValue = true;	
			// 创建关于时间约束的sql语句
			String timesqlMerge = "GATHER_TIME >= '" + startTimeMerge + 
								"' and GATHER_TIME <= '" + endTimeMerge.toString()+ "'" ;
			if( sqlMerge.charAt(sqlMerge.length()-1) == ')' ){
				sqlMerge += " or (IP='" + deviceIP
							+ "' and " + timesqlMerge + ")";
			}else{
				sqlMerge += "(IP='" + deviceIP 
							+ "'  and " + timesqlMerge + ")";
			}
		}
		// 如果没有记录，则退出本次查询
		if( !hasValue ){
			return;
		}

		// 查询数据库，得到多台（最多10台）设备的CPU分钟数据
		List resultList = null;
		try {
			resultList = monitorCpuDataService.queryForList(sqlMerge);
		}catch(Exception e){
			System.out.println( "MergeCpuData:" + e.getMessage() ); 
		}
		if( resultList == null || resultList.size() <= 0 ){
			return;
		}
		int dataIndex = 1;
		for( int t=0; t<dvList.size(); t++ ){
		
			String  deviceIp = dvList.get(t).getIp();
			 
			String sMsg = "正在合并：" + deviceIp + "（设备） " + "的CPU占用率历史数据...";
			dwrService.displayInfo(sMsg);
			List<MonitorCpuData> cpudataMinuteList = new ArrayList<MonitorCpuData>();//CPU
			List<MonitorCpuData> temperatureMinuteList = new ArrayList<MonitorCpuData>();//温度
			List<MonitorCpuData> humidityMinuteList = new ArrayList<MonitorCpuData>();//湿度
			for(int j=0; j<resultList.size(); j++){
				Map rowMap = (Map)resultList.get(j);
				if( deviceIp.equals((String)rowMap.get("IP")) ){
					MonitorCpuData cd = new MonitorCpuData();
					cd.setGatherTime( (Timestamp)rowMap.get("GATHER_TIME") );
					cd.setIp( (String)rowMap.get("IP") );
					cd.setCpu( (Float)rowMap.get("CPU") );
					 
					if(rowMap.get("Data_Index") != null){
						cd.setDataIndex((Integer)rowMap.get("DATA_INDEX"));
					}else{
						cd.setDataIndex(1);//默认1
					}
					
					if(cd.getDataIndex().equals(1)) {
						cpudataMinuteList.add(cd);
					} else if(cd.getDataIndex().equals(2)){
						temperatureMinuteList.add(cd);
					}else if(cd.getDataIndex().equals(3)){
						humidityMinuteList.add(cd);
					}
					 
					
					resultList.remove(j);	// 为减少时间复杂度，删除用过的记录
					j--;
				}
			}
			 
		
			// 遍历查询结果
			System.out.println("CPU分钟数据读取："+ "\tIP=" + deviceIp + "\t记录数" + cpudataMinuteList.size());
			dwrService.displayInfo("CPU分钟数据读取："+ "\tIP=" + deviceIp + "\t记录数" + cpudataMinuteList.size());
			// 合并CPU数据
			if(cpudataMinuteList != null && cpudataMinuteList.size() > 0) {
				mergeHourDay(deviceIp, cpudataMinuteList, 1);
			}
			// 合并温度数据
			if(temperatureMinuteList != null && temperatureMinuteList.size() > 0) { 
				mergeHourDay(deviceIp, temperatureMinuteList, 2);
			}
			// 合并湿度数据
			if(humidityMinuteList != null && humidityMinuteList.size() > 0) {
				mergeHourDay(deviceIp, humidityMinuteList, 3);
			}
			cpudataMinuteList.clear();
			cpudataMinuteList = null;
			humidityMinuteList.clear();
			humidityMinuteList = null;
			temperatureMinuteList.clear();
			temperatureMinuteList = null;
			
			
			 
		}
		resultList.clear();
		resultList = null;
	}
	
	private void mergeHourDay(String deviceIp, List<MonitorCpuData> cpudataMinuteList, int dataIndex) {
		Comparator comp = new CpuDatacomparator();
		Collections.sort(cpudataMinuteList, comp); 
		// 将查询结果组成小时数据list
		List<MonitorCpuDataHour> cpuDataHourList = new ArrayList<MonitorCpuDataHour>();
		float cpuDataMaxHour = 0;
		float cpuDataMinHour = 100;
		float cpuDataSumHour = 0;
		int countInOneHour = 0;
		// 将查询结果组成天数据list
		List<MonitorCpuDataDay> cpudataDayList = new ArrayList<MonitorCpuDataDay>();
		float cpuDataMaxDay = 0;
		float cpuDataMinDay = 100;
		float cpuDataSumDay = 0;
		int countInOneDay = 0;
		// 上一条记录的采集时间
		Timestamp privTime = null;
		
		try{
			for(int i=0; i<cpudataMinuteList.size(); i++) {
				MonitorCpuData cd = cpudataMinuteList.get(i);
				
				if( privTime != null ) {	// 如果不是第一条记录
					// 当遍历到新的一小时，将上一小时的统计记录保存。
					if( cd.getGatherTime().getHours() != privTime.getHours() ) {
						//System.out.println(cd.getDataGatherTime());
						// 添加一条记录到小时数据列表
						if( countInOneHour != 0 ) {
							addCpuHourDataInList(cpuDataHourList, deviceIp,  cpuDataSumHour/countInOneHour,
									cpuDataMaxHour, cpuDataMinHour, privTime.getTime(),dataIndex);
						}
						// 攒够200条数据，写一次数据库
						if( cpuDataHourList.size() >= 200 ) {
							// 将小时数据list写入数据库
							System.out.println("CPU小时数据写入：记录数" + cpuDataHourList.size());
							monitorCpuDataHourService.batchInsert(cpuDataHourList);
							cpuDataHourList.clear();
						}
						cpuDataSumHour = 0;	// 重置小时内的数据累加
						countInOneHour = 0;	// 重置小时内的分钟数据计数器
						cpuDataMaxHour = 0;	// 重置小时内的最大数据
						cpuDataMinHour = 100;	// 重置小时内的最小数据
						
					}
					// 当遍历到新的一天，将上一天的统计记录保存。
					if( cd.getGatherTime().getDay() != privTime.getDay() ) {
						//System.out.println(cd.getDataGatherTime());
						// 添加一条记录到天数据列表
						if( countInOneDay != 0 ) {
							addCpuDayDataInList(cpudataDayList, deviceIp,  cpuDataSumDay/countInOneDay,
									cpuDataMaxDay, cpuDataMinDay, privTime.getTime(),dataIndex);
						}
						// 攒够100条数据，写一次数据库
						if( cpudataDayList.size() >= 100 ) {
							// 将小时数据list写入数据库
							System.out.println("CPU天数据写入：记录数" + cpudataDayList.size());
							monitorCpuDataDayService.batchInsert(cpudataDayList);
							cpudataDayList.clear();
						}
						cpuDataSumDay = 0;	// 重置天内的数据累加
						countInOneDay = 0;	// 重置天内的分钟数据计数器
						cpuDataMaxDay = 0;	// 重置小时内的最大数据
						cpuDataMinDay = 100;// 重置小时内的最小数据
					}
					privTime = cd.getGatherTime();	// 记录本条数据的时间
				}
				
				// 记录本次分钟数据
				if( cd.getCpu() >= 0 && cd.getCpu() <= 100 ) {	// 过滤异常数据
					cpuDataSumHour += cd.getCpu();	// 累计一小时内的分钟数据
					countInOneHour++;					// 累计一小时内的分钟数据计数器
					cpuDataSumDay += cd.getCpu();	// 累计一天内的分钟数据
					countInOneDay++;					// 累计一天内的分钟数据计数器
					
					// 处理最大值，最小值
					if( cpuDataMaxHour < cd.getCpu() ) {
						cpuDataMaxHour = cd.getCpu();
					}
					if( cpuDataMinHour > cd.getCpu() ) {
						cpuDataMinHour = cd.getCpu();
					}
					if( cpuDataMaxDay < cd.getCpu() ) {
						cpuDataMaxDay = cd.getCpu();
					}
					if( cpuDataMinDay > cd.getCpu() ) {
						cpuDataMinDay = cd.getCpu();
					}
				}
				
				// 当遍历到最后一条记录，将还未保存的统计记录保存
				if( i == cpudataMinuteList.size() - 1 ) {
					if(privTime == null )
						privTime=cd.getGatherTime();
					//System.out.println(cd.getDataGatherTime());
					// 添加一条记录到小时数据列表
					if( countInOneHour != 0 ) {
						addCpuHourDataInList(cpuDataHourList, deviceIp, cpuDataSumHour/countInOneHour,
								cpuDataMaxHour, cpuDataMinHour, privTime.getTime(),dataIndex);
					} else { // 如果没有一条可用数据，说明不可用，置-1
						addCpuHourDataInList(cpuDataHourList, deviceIp,  -1,
								cpuDataMaxHour, cpuDataMinHour, privTime.getTime(),dataIndex);
					}
					// 将小时数据list写入数据库
					System.out.println("CPU小时数据写入：记录数" + cpuDataHourList.size());
					monitorCpuDataHourService.batchInsert(cpuDataHourList);
					cpuDataHourList.clear();
	
					cpuDataSumHour = 0;	// 重置小时内的数据累加
					countInOneHour = 0;	// 重置小时内的分钟数据计数器
	
					//System.out.println(cd.getDataGatherTime());
					// 添加一条记录到天数据列表
					if( countInOneDay != 0 ) {
						addCpuDayDataInList(cpudataDayList, deviceIp,  cpuDataSumDay/countInOneDay,
								cpuDataMaxDay, cpuDataMinDay, privTime.getTime(),dataIndex);
					} else { // 如果没有一条可用数据，说明不可用，置-1
						addCpuDayDataInList(cpudataDayList, deviceIp,  -1,
								cpuDataMaxDay, cpuDataMinDay, privTime.getTime(),dataIndex);
					}
					// 将天数据list写入数据库
					System.out.println("CPU天数据写入：记录数" + cpudataDayList.size());
					monitorCpuDataDayService.batchInsert(cpudataDayList);
					cpudataDayList.clear();
	
					cpuDataSumDay = 0;	// 重置天内的数据累加
					countInOneDay = 0;	// 重置天内的分钟数据计数器
				}
				privTime = cd.getGatherTime();	// 记录本条数据的时间
			}
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
		
	}
	 
	/**
	 *  添加一条记录到小时数据列表
	 * @param cpudataHourList
	 * @param sIP
	 * @param cpuData
	 * @param cpuMaxData
	 * @param cpuMinData
	 * @param milliTime
	 */
	private void addCpuHourDataInList(List<MonitorCpuDataHour> cpuDataHourList, String sIP, 
			float cpuData, float cpuMaxData, float cpuMinData, long milliTime,int dataIndex) {
		// 生成一条小时数据
		MonitorCpuDataHour cpudataHour = new MonitorCpuDataHour();
		cpudataHour.setIp(sIP);					// 给IP字段赋值
		cpudataHour.setCpu(cpuData);		// 给CPU平均值字段赋值
		cpudataHour.setMaxCpu(cpuMaxData);	// 给CPU最大值字段赋值
		cpudataHour.setMinCpu(cpuMinData);	// 给CPU最小值字段赋值
		cpudataHour.setDataIndex(dataIndex);	// data_index
		// 将上一小时的59分59秒999作为一小时的结束时间，记录到小时数据中
		Timestamp time = new Timestamp(milliTime);
		time.setMinutes(59);
		time.setSeconds(59);
		time.setNanos(999);
		cpudataHour.setGatherTime(time);// 给采集时间字段赋值
		cpuDataHourList.add(cpudataHour);	// 将小时数据记录加入list
	}
	
	/**
	 * 添加一条记录到天数据列表
	 * @param cpuDataDayList
	 * @param sIP
	 * @param cpuData
	 * @param cpuMaxData
	 * @param cpuMinData
	 * @param milliTime
	 */
	private void addCpuDayDataInList(List<MonitorCpuDataDay> cpuDataDayList, String sIP, 
			float cpuData, float cpuMaxData, float cpuMinData, long milliTime ,int dataIndex) {
		// 生成一条天数据
		MonitorCpuDataDay cpudataDay = new MonitorCpuDataDay();
		cpudataDay.setIp(sIP);					// 给IP字段赋值
		cpudataDay.setCpu(cpuData);			// 给CPU平均值字段赋值
		cpudataDay.setMaxCpu(cpuMaxData);	// 给CPU最大值字段赋值
		cpudataDay.setMinCpu(cpuMinData);	// 给CPU最小值字段赋值
		cpudataDay.setDataIndex(dataIndex);	// data_index
		// 将上一天的23点59分59秒999作为一天的结束时间，记录到天数据中
		Timestamp time = new Timestamp(milliTime);
		time.setHours(23);
		time.setMinutes(59);
		time.setSeconds(59);
		time.setNanos(999);
		cpudataDay.setGatherTime(time);	// 给采集时间字段赋值
		cpuDataDayList.add(cpudataDay);		// 将天数据记录加入list
	}
	/**
	 *  Cpudata比较器(按采集时间从小到达)
	 * @author Administrator
	 *
	 */
	class CpuDatacomparator implements Comparator {
		public int compare( Object o1, Object o2 ) {
			MonitorCpuData cd1 = (MonitorCpuData)o1;
			MonitorCpuData cd2 = (MonitorCpuData)o2;
			if( cd1.getGatherTime().getTime() > cd2.getGatherTime().getTime() )
			  return 1;
			else
			  return 0;
		}
	}
	
	/**
	 * Regulardata比较器(按采集时间从小到达)
	 * @author Administrator
	 *
	 */
	class RegularDatacomparator implements Comparator {
		public int compare( Object o1, Object o2 ) {
			MonitorRegularData rgd1 = (MonitorRegularData)o1;
			MonitorRegularData rgd2 = (MonitorRegularData)o2;
			if( rgd1.getGatherTime().getTime() > rgd2.getGatherTime().getTime() )
			  return 1;
			else
			  return 0;
		}
	}
	
	
	
	
	
	/**
	 * 合并互联端口流量
	 */
	private void operateOnLinkportRegulardata() {
		long startTime = System.currentTimeMillis();
		// 获取互联端口表，为按端口遍历分钟流量表做准备
		List<MonitorLinkport> linkportList = new ArrayList<MonitorLinkport>();	// 互联端口表全表
		linkportList = monitorLinkportService.findUniqueLegitimateList();
		// 按互联端口表，遍历流量分钟数据
		String sMsg = null;
		if( linkportList != null && linkportList.size() > 0 ){
			List<MonitorLinkport> lpList = new ArrayList<MonitorLinkport>();	// 存放Linkport的列表
		 
			for(  int i=0; i<linkportList.size(); i++ ) {
				MonitorLinkport lp = linkportList.get(i);
				if( Tools.ipFormatOK(lp.getIp()) && lp.getInterface_().length() > 0 ){	// 检测IP的合法性和接口不能为空
					if( lpList.size() < 9  ) {	// 为提高查询效率，10个端口一起查
						lpList.add(lp);
					}else{
						lpList.add(lp);
						mergeLinkportRegularData( lpList );	// 合并接口历史数据
						lpList.clear();	
					}
				}
			}
			if( lpList.size() > 0 ){
				mergeLinkportRegularData( lpList );	// 合并接口历史数据
				lpList.clear();
			}
			// 清空linkportList
			linkportList.clear();
			linkportList = null;
		} 
		long endTime = System.currentTimeMillis();
		dwrService.displayInfo("合并流量数据，共用时" + (endTime-startTime)/1000.0 + "秒");
	}
	
	/**
	 *  按互联端口表的List遍历处理 ,合并昨天Regulardata分钟数据为小时数据；合并昨天Regulardata小时数据为天数据.
	 * @param lpList
	 */
	private void mergeLinkportRegularData( List<MonitorLinkport> lpList ){
		
		if( lpList == null || lpList.size() <= 0 ){
			return;
		}
		// 遍历Linkport的list形成SQL语句
		String sqlMerge = "SELECT distinct GATHER_TIME,  IP, INTERFACE, TX_BYTE, RX_BYTE from monitor_regular_data where ";
		boolean hasValue = false;	// 标记有无记录
		
		for( int i=0; i<lpList.size(); i++){
			
			String deviceIp = lpList.get(i).getIp();
			String linkInterface = lpList.get(i).getInterface_();
			if( deviceIp.length() == 0 || linkInterface.length() == 0 )
				continue;
			// 计算数据查询的起始时间（天数据的最新时间）
			Timestamp startTimeMerge = null;
			try {
				String timesql = " IP = '" + deviceIp + 
								"' and INTERFACE = " + linkInterface;
				String sqlQueryTime = "SELECT GATHER_TIME from monitor_regular_data_day where GATHER_TIME=(select max(GATHER_TIME) from monitor_regular_data_day where"  + timesql + ")";
				SqlRowSet rowset = monitorRegularDataDayService.queryForRowSet(sqlQueryTime);
				if( rowset != null && rowset.next() ) {
					startTimeMerge = rowset.getTimestamp("GATHER_TIME");
				}
				else{
					sqlQueryTime = "SELECT GATHER_TIME from monitor_regular_data where GATHER_TIME=(select min(GATHER_TIME) from monitor_regular_data where"  + timesql + ")";
					rowset = monitorRegularDataService.queryForRowSet(sqlQueryTime);
					if( rowset != null && rowset.next() ) {
						startTimeMerge = rowset.getTimestamp("GATHER_TIME");
					}else {
						continue;
					}
				}
			}catch(Exception e){
				System.out.println("MergeRegularData:"+e.getMessage()); 
			}
			hasValue = true;
			// 创建关于时间约束的hql语句
			String timesqlMerge = "GATHER_TIME >= '" + startTimeMerge + 
						"' and GATHER_TIME <= '" + endTimeMerge + "'";
			if( sqlMerge.charAt(sqlMerge.length()-1) == ')' ){
				sqlMerge += " or (IP='" +  deviceIp 
							+ "' and INTERFACE = '" + linkInterface
			    			+ "' and " + timesqlMerge + ")";
			}else{
				sqlMerge += " (IP='" +  deviceIp 
							+ "' and INTERFACE = '" + linkInterface
			    			+ "' and " + timesqlMerge + ")";
			}
		}
		
		// 如果没有记录，则推出本次查询
		if( !hasValue ){
			return;
		}
		
		// 查询数据库，得到多接口（最多10个）的流量分钟数据
		List resultList = null;
		try {
			resultList = monitorRegularDataService.queryForList(sqlMerge);
		}catch(Exception e){
			System.out.println( "MergeRegularData:" + e.getMessage() ); 
		}
		
		if( resultList == null || resultList.size() <= 0 ){
			return;
		}
		
		for( int t=0; t<lpList.size(); t++ ){
			String deviceIp = lpList.get(t).getIp();
			String linkInterface = lpList.get(t).getInterface_();
			String sMsg = "正在合并：" +deviceIp + "（设备） " + linkInterface + "（接口） " 
					+ "的接口流量历史数据...";
			System.out.print(sMsg);
			dwrService.displayInfo(sMsg);
			// 将结果List转换为对象List
			List<MonitorRegularData> regulardataMinuteList = new ArrayList<MonitorRegularData>();
			for( int j=0; j<resultList.size(); j++){
				Map rowMap = (Map)resultList.get(j);
				if( deviceIp.equals( (String)rowMap.get("IP") ) &&
					linkInterface.equals( (String)rowMap.get("INTERFACE") ) ) {
					MonitorRegularData rd = new MonitorRegularData();
					rd.setBiTraffic(-1L);
					rd.setDeliveryTraffic(-1L);
					rd.setReceiveTraffic(-1L); 
					rd.setGatherTime( (Timestamp)rowMap.get("GATHER_TIME") );
					rd.setInterface_( (String)rowMap.get("INTERFACE") );
					rd.setIp( (String)rowMap.get("IP") );
					rd.setRxByte( Long.parseLong(rowMap.get("RX_BYTE").toString()) );
					rd.setTxByte( Long.parseLong(rowMap.get("TX_BYTE").toString()) );
					regulardataMinuteList.add(rd);
					resultList.remove(j);	// 为减少时间复杂度，删除用过的记录
					j--;
				}
			}
			// 将结果List转换为对象List end
			
			// regulardataMinuteList按采集时间排序
			Comparator comp = new RegularDatacomparator();
			Collections.sort(regulardataMinuteList, comp); 
			
			// 计算两条记录间的流量差
			monitorRegularDataService.plusArrayListForMerge(regulardataMinuteList);
			mergeRegularDataToHourDay(deviceIp,linkInterface,regulardataMinuteList);
			
			
		}
		resultList.clear();
		resultList = null;
	}
	
	private void mergeRegularDataToHourDay(String deviceIp,String linkInterface,List<MonitorRegularData> regulardataMinuteList){
		
		// 将查询结果组成小时数据list和天数据
		List<MonitorRegularDataHour> regulardataHourList = new ArrayList<MonitorRegularDataHour>();
		List<MonitorRegularDataDay> regulardataDayList = new ArrayList<MonitorRegularDataDay>();
		
		int countInOneHour = 0;
		int countInOneDay = 0;
		
		long regularDataBiMaxHour = 0;
		long regularDataBiMinHour = 999999999;
		long regularDataBiSumHour = 0;
		long regularDataBiMaxDay = 0;
		long regularDataBiMinDay = 999999999;
		long regularDataBiSumDay = 0;
		
		long regularDataReceMaxHour = 0;
		long regularDataReceMinHour = 999999999;
		long regularDataReceSumHour = 0;
		long regularDataReceMaxDay = 0;
		long regularDataReceMinDay = 999999999;
		long regularDataReceSumDay = 0;
		
		long regularDataSendMaxHour = 0;
		long regularDataSendMinHour = 999999999;
		long regularDataSendSumHour = 0;
		long regularDataSendMaxDay = 0;
		long regularDataSendMinDay = 999999999;
		long regularDataSendSumDay = 0;
		
		// 上一条记录的采集时间
		Timestamp privTime = null;
		
		// 遍历查询结果
		System.out.println("流量分钟数据读取：IP=" + deviceIp + "\tInterface=" + linkInterface 
				+ "\t记录数" + regulardataMinuteList.size());
		
		try{
			for( int i=0; i<regulardataMinuteList.size(); i++ ) {
				MonitorRegularData rd = regulardataMinuteList.get(i);
				if( privTime != null ) {	// 如果不是第一条记录
					// 当遍历到新的一小时，将上一小时的统计记录保存。
					if( rd.getGatherTime().getHours() != privTime.getHours() ){
						// 添加一条流量数据到小时数据List中
						if( countInOneHour != 0 ) {
							addRegularHourDataInList(regulardataHourList, deviceIp,  linkInterface, 
									regularDataBiSumHour/countInOneHour, regularDataBiMaxHour, regularDataBiMinHour, 
									regularDataReceSumHour/countInOneHour, regularDataReceMaxHour, regularDataReceMinHour, 
									regularDataSendSumHour/countInOneHour, regularDataSendMaxHour, regularDataSendMinHour, 
									privTime.getTime());
						}
						// 攒够200条数据，写一次数据库
						if( regulardataHourList.size() >= 200 ) {
							// 将小时数据list写入数据库
							System.out.println("流量小时数据写入：记录数" + regulardataHourList.size());
							monitorRegularDataHourService.batchInsert(regulardataHourList);
							regulardataHourList.clear();
						}
						regularDataBiSumHour = 0;	// 重置一小时内的双向数据累加
						regularDataReceSumHour = 0;	// 重置一小时内的接收数据累加
						regularDataSendSumHour = 0;	// 重置一小时内的发送数据累加
						countInOneHour = 0;			// 重置一小时内的分钟数据计数器
						regularDataBiMaxHour = 0;	// 重置小时内双向最大值
						regularDataReceMaxHour = 0;	// 重置小时内接收最大值
						regularDataSendMaxHour = 0;	// 重置小时内发送最大值
						regularDataBiMinHour = 999999999;	// 重置小时内双向最小值
						regularDataReceMinHour = 999999999;	// 重置小时内接收最小值
						regularDataSendMinHour = 999999999;	// 重置小时内双向最小值
					}
					// 当遍历到新的一天，将上一天的统计记录保存。
					if( rd.getGatherTime().getDay() != privTime.getDay() ) {
						// 添加一条流量数据到天数据List中
						if( countInOneDay != 0 ) {
							addRegularDayDataInList(regulardataDayList, deviceIp,  linkInterface, 
									regularDataBiSumDay/countInOneDay, regularDataBiMaxDay, regularDataBiMinDay, 
									regularDataReceSumDay/countInOneDay, regularDataReceMaxDay, regularDataReceMinDay, 
									regularDataSendSumDay/countInOneDay, regularDataSendMaxDay, regularDataSendMinDay, 
									privTime.getTime());
						}
						// 攒够100条数据，写一次数据库
						if( regulardataDayList.size() >= 100  ) {
							// 将天数据list写入数据库
							System.out.println("流量天数据写入：记录数" + regulardataDayList.size());
							monitorRegularDataDayService.batchInsert(regulardataDayList);
							regulardataDayList.clear();
						}
						regularDataBiSumDay = 0;	// 重置一天内的双向数据累加
						regularDataReceSumDay = 0;	// 重置一天内的接收数据累加
						regularDataSendSumDay = 0;	// 重置一天内的发送数据累加
						countInOneDay = 0;			// 重置一天内的分钟数据计数器
						regularDataBiMaxDay = 0;	// 重置天内双向最大值
						regularDataReceMaxDay = 0;	// 重置天内接收最大值
						regularDataSendMaxDay = 0;	// 重置天内发送最大值
						regularDataBiMinDay = 999999999;	// 重置天内双向最小值
						regularDataReceMinDay = 999999999;	// 重置天内接收最小值
						regularDataSendMinDay = 999999999;	// 重置天内双向最小值
					}
					privTime = rd.getGatherTime();	// 记录本条数据的时间		
				}
				
				long biTraffic   = rd.getBiTraffic() ;			// 本条记录的双向数据
				long receTraffic = rd.getReceiveTraffic() ;	// 本条记录的接收数据
				long sendTraffic = rd.getDeliveryTraffic();	// 本条记录的发送数据
				if( i< regulardataMinuteList.size()&&(biTraffic < 0 || receTraffic < 0 || sendTraffic < 0) ){
					privTime = rd.getGatherTime();	// 记录本条数据的时间
					continue;
				}
				
				// 本条分钟数据与上一条，在同一个小时内
				regularDataBiSumHour	+= biTraffic;	// 累计一小时内的双向分钟数据
				regularDataReceSumHour	+= receTraffic; // 累计一小时内的接收分钟数据
				regularDataSendSumHour	+= sendTraffic; // 累计一小时内的发送分钟数据
				regularDataBiSumDay		+= biTraffic;	// 累计一天内的双向分钟数据
				regularDataReceSumDay	+= receTraffic;	// 累计一天内的接收分钟数据
				regularDataSendSumDay	+= sendTraffic;	// 累计一天内的发送分钟数据
				countInOneHour++;					// 累计一小时内的分钟数据计数器
				countInOneDay++;					// 累计一天内的分钟数据计数器
				
				// 处理最大值，最小值
				if( regularDataBiMaxHour < biTraffic ) {
					regularDataBiMaxHour = biTraffic;
				}
				if( regularDataBiMinHour > biTraffic ) {
					regularDataBiMinHour = biTraffic;
				}
				if( regularDataReceMaxHour < receTraffic ) {
					regularDataReceMaxHour = receTraffic;
				}
				if( regularDataReceMinHour > receTraffic ) {
					regularDataReceMinHour = receTraffic;
				}
				if( regularDataSendMaxHour < sendTraffic ) {
					regularDataSendMaxHour = sendTraffic;
				}
				if( regularDataSendMinHour > sendTraffic ) {
					regularDataSendMinHour = sendTraffic;
				}
				if( regularDataBiMaxDay < biTraffic ) {
					regularDataBiMaxDay = biTraffic;
				}
				if( regularDataBiMinDay > biTraffic ) {
					regularDataBiMinDay = biTraffic;
				}
				if( regularDataReceMaxDay < receTraffic ) {
					regularDataReceMaxDay = receTraffic;
				}
				if( regularDataReceMinDay > receTraffic ) {
					regularDataReceMinDay = receTraffic;
				}
				if( regularDataSendMaxDay < sendTraffic ) {
					regularDataSendMaxDay = sendTraffic;
				}
				if( regularDataSendMinDay > sendTraffic ) {
					regularDataSendMinDay = sendTraffic;
				}
				
				// 当遍历到最后一条记录，将还未保存的统计记录保存
				if( i == regulardataMinuteList.size()-1 ) {
					
					if(null== privTime  )
						privTime = rd.getGatherTime();	// 记录本条数据的时间	
					
					// 添加一条流量数据到小时数据List中
					if( countInOneHour != 0 ) {
						addRegularHourDataInList(regulardataHourList, deviceIp,  linkInterface, 
								regularDataBiSumHour/countInOneHour, regularDataBiMaxHour, regularDataBiMinHour, 
								regularDataReceSumHour/countInOneHour, regularDataReceMaxHour, regularDataReceMinHour, 
								regularDataSendSumHour/countInOneHour, regularDataSendMaxHour, regularDataSendMinHour, 
								privTime.getTime());
					}
					// 将小时数据list写入数据库
					System.out.println("流量小时数据写入：记录数" + regulardataHourList.size());
					monitorRegularDataHourService.batchInsert(regulardataHourList);
					regulardataHourList.clear();
	
					regularDataBiSumHour = 0;	// 重置一小时内的双向数据累加
					regularDataReceSumHour = 0;	// 重置一小时内的接收数据累加
					regularDataSendSumHour = 0;	// 重置一小时内的发送数据累加
					countInOneHour = 0;			// 重置一小时内的分钟数据计数器
						
					// 添加一条流量数据到天数据List中
					if( countInOneDay != 0 ) {
						addRegularDayDataInList(regulardataDayList, deviceIp,  linkInterface, 
								regularDataBiSumDay/countInOneDay, regularDataBiMaxDay, regularDataBiMinDay, 
								regularDataReceSumDay/countInOneDay, regularDataReceMaxDay, regularDataReceMinDay, 
								regularDataSendSumDay/countInOneDay, regularDataSendMaxDay, regularDataSendMinDay, 
								privTime.getTime());
					}
					// 将天数据list写入数据库
					System.out.println("流量天数据写入：记录数" + regulardataDayList.size());
					monitorRegularDataDayService.batchInsert(regulardataDayList);
					regulardataDayList.clear();
	
					regularDataBiSumDay = 0;	// 重置一天内的双向数据累加
					regularDataReceSumDay = 0;	// 重置一天内的接收数据累加
					regularDataSendSumDay = 0;	// 重置一天内的发送数据累加
					countInOneDay = 0;			// 重置一天内的分钟数据计数器
				}
				privTime = rd.getGatherTime();	// 记录本条数据的时间		
			}
		}catch(Exception e){
			System.out.println("异常捕获在持久层:"+e.getMessage()); 
		}
		regulardataMinuteList.clear();
		regulardataMinuteList = null;
				
	}
	

	/**
	 * 处理全部设备端口流量
	 */
	private void operateOnDeviceInterfaceRegulardata(){
		
		long startTime = System.currentTimeMillis();
		// 获取设备接口表，为按端口遍历分钟流量表做准备
		List<MonitorInterfaceCache> deviceInterfaceList = new ArrayList<MonitorInterfaceCache>();
		deviceInterfaceList = monitorInterfaceCacheService.findAll();
		// 按设备端口表，遍历流量分钟数据
		String sMsg = null;
		if( deviceInterfaceList != null && deviceInterfaceList.size() > 0 ){
			List<MonitorInterfaceCache> diList = new ArrayList<MonitorInterfaceCache>();	// 存放DeviceInterface的列表
			for(  int i=0; i<deviceInterfaceList.size(); i++ ) {
				MonitorInterfaceCache di = deviceInterfaceList.get(i);
				if( Tools.ipFormatOK(di.getMonitorDevice().getIp()) && di.getInterface_().length() > 0 ){	// 检测IP的合法性和接口不能为空
					if( diList.size() < 9  ) {	// 为提高查询效率，10个端口一起查
						diList.add(di);
					}else{
						diList.add(di);
						mergeDeviceInterfaceRegularData( diList );	// 合并接口历史数据
						diList.clear();	
					}
				}
			}
			if( diList.size() > 0 ){
				mergeDeviceInterfaceRegularData( diList );	// 合并接口历史数据
				diList.clear();
			}
			// 清空linkportList
			deviceInterfaceList.clear();
			deviceInterfaceList = null;
		}
	}
	
	/**
	 *  按设备端口表的List遍历处理,合并昨天Regulardata分钟数据为小时数据；合并昨天Regulardata小时数据为天数据.
	 */
	private void mergeDeviceInterfaceRegularData(List<MonitorInterfaceCache> diList){
		if( diList == null || diList.size() <= 0 )
			return;
		
		// 遍历Linkport的list形成SQL语句
		String sqlMerge = "SELECT distinct GATHER_TIME,  IP, INTERFACE, TX_BYTE, RX_BYTE from monitor_regular_data where ";
		boolean hasValue = false;	// 标记有无记录
		
		for( int i=0; i<diList.size(); i++){
			String deviceIp = diList.get(i).getMonitorDevice().getIp();
			String linkInterface = diList.get(i).getInterface_();
			if( deviceIp.length() == 0 || linkInterface.length() == 0 )
				continue;
			// 计算数据查询的起始时间（天数据的最新时间）
			Timestamp startTimeMerge = null;
			try {
				String timesql = " IP = '" + deviceIp + 
								"' and INTERFACE = " + linkInterface;
				String sqlQueryTime = "SELECT GATHER_TIME from monitor_regular_data_day where GATHER_TIME=(select max(GATHER_TIME) from monitor_regular_data_day where"  + timesql + ")";
				SqlRowSet rowset = monitorRegularDataDayService.queryForRowSet(sqlQueryTime);
				if( rowset != null && rowset.next() ) {
					startTimeMerge = rowset.getTimestamp("GATHER_TIME");
				}
				else{
					sqlQueryTime = "SELECT GATHER_TIME from monitor_regular_data where GATHER_TIME=(select min(GATHER_TIME) from monitor_regular_data where"  + timesql + ")";
					rowset = monitorRegularDataService.queryForRowSet(sqlQueryTime);
					if( rowset != null && rowset.next() ) {
						startTimeMerge = rowset.getTimestamp("GATHER_TIME");
					}else {
						continue;
					}
				}
			}catch(Exception e){
				System.out.println("MergeRegularData:"+e.getMessage()); 
			}
			hasValue = true;
			
			// 创建关于时间约束的hql语句
			String timesqlMerge = "GATHER_TIME >= '" + startTimeMerge + 
						"' and GATHER_TIME <= '" + endTimeMerge + "'";
			if( sqlMerge.charAt(sqlMerge.length()-1) == ')' ){
				sqlMerge += " or (IP='" +  deviceIp 
							+ "' and INTERFACE = '" + linkInterface
			    			+ "' and " + timesqlMerge + ")";
			}else{
				sqlMerge += " (IP='" +  deviceIp 
							+ "' and INTERFACE = '" + linkInterface
			    			+ "' and " + timesqlMerge + ")";
			}
		}
		// 如果没有记录，则退出本次查询
		if( !hasValue ){
			return;
		}
		
		// 查询数据库，得到多接口（最多10个）的流量分钟数据
		List resultList = null;
		try {
			resultList = monitorRegularDataService.queryForList(sqlMerge);
		}catch(Exception e){
			System.out.println( "MergeRegularData:" + e.getMessage() ); 
		}
		
		if( resultList == null || resultList.size() <= 0 ){
			return;
		}
		
		for( int t=0; t<diList.size(); t++ ){
			String deviceIp = diList.get(t).getMonitorDevice().getIp();
			String linkInterface = diList.get(t).getInterface_();
			String sMsg = "正在合并：" + deviceIp + "（设备） " + linkInterface + "（接口） " + "的接口流量历史数据...";
			System.out.print(sMsg);
			
			// 将结果List转换为对象List
			List<MonitorRegularData> regulardataMinuteList = new ArrayList<MonitorRegularData>();
			for( int j=0; j<resultList.size(); j++){
				Map rowMap = (Map)resultList.get(j);
				if( deviceIp.equals( (String)rowMap.get("IP") ) &&
					linkInterface.equals( (String)rowMap.get("INTERFACE") ) ) {
					MonitorRegularData rd = new MonitorRegularData();
					rd.setBiTraffic(-1L);
					rd.setDeliveryTraffic(-1L);
					rd.setReceiveTraffic(-1L); 
					rd.setGatherTime( (Timestamp)rowMap.get("GATHER_TIME") );
					rd.setInterface_( (String)rowMap.get("INTERFACE") );
					rd.setIp( (String)rowMap.get("IP") );
					rd.setRxByte( Long.parseLong(rowMap.get("RX_BYTE").toString()) );
					rd.setTxByte( Long.parseLong(rowMap.get("TX_BYTE").toString()) );
					regulardataMinuteList.add(rd);
					resultList.remove(j);	// 为减少时间复杂度，删除用过的记录
					j--;
				}
			}
			
			// regulardataMinuteList按采集时间排序
			Comparator comp = new RegularDatacomparator();
			Collections.sort(regulardataMinuteList, comp); 
			
			// 计算两条记录间的流量差
			monitorRegularDataService.plusArrayListForMerge(regulardataMinuteList);
			mergeRegularDataToHourDay(deviceIp,linkInterface,regulardataMinuteList);
		}
		
		resultList.clear();
		resultList = null;
	}
	/**
	 *  添加一条流量数据到小时数据List中
	 * @param regulardataHourList
	 * @param sIP
	 * @param linkInterface
	 * @param hourBiData
	 * @param hourBiMaxData
	 * @param hourBiMinData
	 * @param hourReceData
	 * @param hourReceMaxData
	 * @param hourReceMinData
	 * @param hourSendData
	 * @param hourSendMaxData
	 * @param hourSendMinData
	 * @param milliTime
	 */
	private void addRegularHourDataInList(List<MonitorRegularDataHour> regulardataHourList, String sIP,
		    String linkInterface, long hourBiData, long hourBiMaxData,
			long hourBiMinData, long hourReceData, long hourReceMaxData, long hourReceMinData,
			long hourSendData, long hourSendMaxData, long hourSendMinData, long milliTime ) {
		MonitorRegularDataHour regulardataHour = new MonitorRegularDataHour();
		regulardataHour.setIp(sIP);			// 给IP字段赋值
		regulardataHour.setInterface_(linkInterface);// 给接口字段赋值
		regulardataHour.setBiTraffic(hourBiData);// 给流量双向平均值字段赋值
		regulardataHour.setMaxBiTraffic( hourBiMaxData);	// 给Regular双向最大值字段赋值
		regulardataHour.setMinBiTraffic( hourBiMinData );	// 给Regular双向最小值字段赋值
		regulardataHour.setReceiveTraffic(hourReceData);		// 给流量接收平均值字段赋值
		regulardataHour.setMaxReceiveTraffic(hourReceMaxData);	// 给Regular接收最大值字段赋值
		regulardataHour.setMinReceiveTraffic(hourReceMinData);	// 给Regular接收最小值字段赋值
		regulardataHour.setDeliveryTraffic(hourSendData);		// 给流量发送平均值字段赋值
		regulardataHour.setMaxDeliveryTraffic( hourSendMaxData);// 给Regular发送最大值字段赋值
		regulardataHour.setMinDeliveryTraffic( hourSendMinData);// 给Regular发送最小值字段赋值
		// 将上一小时的59分59秒999作为一小时的结束时间，记录到小时数据中
		Timestamp time = new Timestamp(milliTime);
		time.setMinutes(59);
		time.setSeconds(59);
		time.setNanos(999999999);
		regulardataHour.setGatherTime(time);	// 给采集时间字段赋值
		regulardataHourList.add(regulardataHour);	// 将小时数据记录加入list
	}
	
	/** 
	 * 添加一条流量数据到天数据List中
	 * @param regulardataDayList
	 * @param sIP
	 * @param linkInterface
	 * @param dayBiData
	 * @param dayBiMaxData
	 * @param dayBiMinData
	 * @param dayReceData
	 * @param dayReceMaxData
	 * @param dayReceMinData
	 * @param daySendData
	 * @param daySendMaxData
	 * @param daySendMinData
	 * @param milliTime
	 */
	private void addRegularDayDataInList(List<MonitorRegularDataDay> regulardataDayList, String sIP,
			 String linkInterface, long dayBiData, long dayBiMaxData,
			long dayBiMinData, long dayReceData, long dayReceMaxData, long dayReceMinData,
			long daySendData, long daySendMaxData, long daySendMinData, long milliTime ) {
		MonitorRegularDataDay regulardataDay = new MonitorRegularDataDay();
		regulardataDay.setIp(sIP);			// 给IP字段赋值
		regulardataDay.setInterface_(linkInterface);// 给接口字段赋值
		regulardataDay.setBiTraffic(dayBiData);// 给流量双向平均值字段赋值
		regulardataDay.setMaxBiTraffic( dayBiMaxData );	// 给Regular双向最大值字段赋值
		regulardataDay.setMinBiTraffic( dayBiMinData );	// 给Regular双向最小值字段赋值
		regulardataDay.setReceiveTraffic(dayReceData);// 给流量接收平均值字段赋值
		regulardataDay.setMaxReceiveTraffic(dayReceMaxData );	// 给Regular接收最大值字段赋值
		regulardataDay.setMinReceiveTraffic(dayReceMinData);	// 给Regular接收最小值字段赋值
		regulardataDay.setDeliveryTraffic(daySendData);// 给流量发送平均值字段赋值
		regulardataDay.setMaxDeliveryTraffic(daySendMaxData);	// 给Regular发送最大值字段赋值
		regulardataDay.setMinDeliveryTraffic(daySendMinData);	// 给Regular发送最小值字段赋值
		// 将上一天的23点59分59秒999作为一天的结束时间，记录到天数据中
		Timestamp time = new Timestamp(milliTime);
		time.setHours(23);
		time.setMinutes(59);
		time.setSeconds(59);
		time.setNanos(999);
		regulardataDay.setGatherTime(time);	// 给采集时间字段赋值
		regulardataDayList.add(regulardataDay);	// 将天数据记录加入list
	}		
	
	
	public MonitorDeviceService getMonitorDeviceService() {
		return monitorDeviceService;
	}
	public void setMonitorDeviceService(MonitorDeviceService monitorDeviceService) {
		this.monitorDeviceService = monitorDeviceService;
	}
	public MonitorCpuDataDayService getMonitorCpuDataDayService() {
		return monitorCpuDataDayService;
	}
	public void setMonitorCpuDataDayService(
			MonitorCpuDataDayService monitorCpuDataDayService) {
		this.monitorCpuDataDayService = monitorCpuDataDayService;
	}
	public MonitorCpuDataService getMonitorCpuDataService() {
		return monitorCpuDataService;
	}
	public void setMonitorCpuDataService(MonitorCpuDataService monitorCpuDataService) {
		this.monitorCpuDataService = monitorCpuDataService;
	}

	public MonitorCpuDataHourService getMonitorCpuDataHourService() {
		return monitorCpuDataHourService;
	}

	public void setMonitorCpuDataHourService(
			MonitorCpuDataHourService monitorCpuDataHourService) {
		this.monitorCpuDataHourService = monitorCpuDataHourService;
	}

	public MonitorLinkportService getMonitorLinkportService() {
		return monitorLinkportService;
	}

	public void setMonitorLinkportService(
			MonitorLinkportService monitorLinkportService) {
		this.monitorLinkportService = monitorLinkportService;
	}

	public MonitorRegularDataService getMonitorRegularDataService() {
		return monitorRegularDataService;
	}

	public void setMonitorRegularDataService(
			MonitorRegularDataService monitorRegularDataService) {
		this.monitorRegularDataService = monitorRegularDataService;
	}

	public MonitorRegularDataHourService getMonitorRegularDataHourService() {
		return monitorRegularDataHourService;
	}

	public void setMonitorRegularDataHourService(
			MonitorRegularDataHourService monitorRegularDataHourService) {
		this.monitorRegularDataHourService = monitorRegularDataHourService;
	}

	public MonitorRegularDataDayService getMonitorRegularDataDayService() {
		return monitorRegularDataDayService;
	}

	public void setMonitorRegularDataDayService(
			MonitorRegularDataDayService monitorRegularDataDayService) {
		this.monitorRegularDataDayService = monitorRegularDataDayService;
	}

	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public MonitorInterfaceCacheService getMonitorInterfaceCacheService() {
		return monitorInterfaceCacheService;
	}

	public void setMonitorInterfaceCacheService(
			MonitorInterfaceCacheService monitorInterfaceCacheService) {
		this.monitorInterfaceCacheService = monitorInterfaceCacheService;
	}

	public DwrService getDwrService() {
		return dwrService;
	}

	public void setDwrService(DwrService dwrService) {
		this.dwrService = dwrService;
	}

	 

	 


	
}
