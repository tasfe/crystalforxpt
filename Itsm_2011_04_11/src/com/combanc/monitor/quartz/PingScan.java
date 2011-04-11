package com.combanc.monitor.quartz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.combanc.monitor.AppContextUtil;
import com.combanc.monitor.alert.alertParser.Alerter;
import com.combanc.monitor.constants.SystemParamConstants;
import com.combanc.monitor.pojo.MonitorPingDest;
import com.combanc.monitor.pojo.MonitorPingResult;
import com.combanc.monitor.pojo.MonitorPingResultDay;
import com.combanc.monitor.pojo.MonitorSystemParam;
import com.combanc.monitor.service.MonitorPingDestService;
import com.combanc.monitor.service.MonitorPingResultService;
import com.combanc.monitor.service.MonitorSystemParamService;
import com.combanc.monitor.util.PingTools;
/**
 * <p>
 * Title:Ping扫描
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
public class PingScan  {

	List<MonitorPingDest> pingDestList = new ArrayList<MonitorPingDest>();
	
	public int pingThreadCount = 32; 		// ping线程数
	public int pingNum = SystemParamConstants.SYSTEM_PING_NUM_DEFUALT_VALUE;					// 每次ping的次数
	public int pingSize = SystemParamConstants.SYSTEM_PING_SIZE_DEFUALT_VALUE;				// ping包长度
	public int pingTimeout = SystemParamConstants.SYSTEM_PING_TIMEOUT_DEFUALT_VALUE;			// 超时时间（ms）
	public int pingLoopGap = 0;					// 循环Ping的时间间隔，非循环Ping时，此值为0
	 
	private MonitorPingResultService monitorPingResultService = null;
	private MonitorPingDestService monitorPingDestService = null;
	private MonitorSystemParamService monitorSystemParamService = null;
	private Alerter alerter = null;
	
	
	
	public MonitorPingResultService getMonitorPingResultService() {
		return monitorPingResultService;
	}

	public void setMonitorPingResultService(
			MonitorPingResultService monitorPingResultService) {
		this.monitorPingResultService = monitorPingResultService;
	}

	public MonitorPingDestService getMonitorPingDestService() {
		return monitorPingDestService;
	}

	public void setMonitorPingDestService(
			MonitorPingDestService monitorPingDestService) {
		this.monitorPingDestService = monitorPingDestService;
	}

	public MonitorSystemParamService getMonitorSystemParamService() {
		return monitorSystemParamService;
	}

	public void setMonitorSystemParamService(
			MonitorSystemParamService monitorSystemParamService) {
		this.monitorSystemParamService = monitorSystemParamService;
	}

	public Alerter getAlerter() {
		return alerter;
	}

	public void setAlerter(Alerter alerter) {
		this.alerter = alerter;
	}

	public PingScan() {
		 
	}
	
	 
	
	public void init(int loopGap){
		try{
			pingDestList = monitorPingDestService.getAll();
			MonitorSystemParam systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_NUM);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				pingNum = Integer.parseInt(systemParam.getValue());
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_SIZE);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				pingSize = Integer.parseInt(systemParam.getValue());
			systemParam  = monitorSystemParamService.findByParamCode(SystemParamConstants.SYSTEM_PING_TIMEOUT);
			if( null != systemParam && !"".equals(systemParam.getValue()))
				pingTimeout = Integer.parseInt(systemParam.getValue());
			this.pingLoopGap = loopGap;
		} catch(Exception e){
			e.printStackTrace(); 
		}
		
	}
	public void scan(int loopGap){
	
		System.out.println("PingScan Start ...");
		this.init(loopGap);
		// 准备好Ping线程
		PingThread pingThreads[] = new PingThread[pingThreadCount];
		for (int i=0; i<pingThreadCount; i++) {
			pingThreads[i] = new PingThread();
		}
		
		// 分配任务
		int i = 0;
		for (MonitorPingDest pd : pingDestList) {
			if (pd.getIsRun()) {
				pingThreads[i%pingThreadCount].addElement(pd);
				i++;
			}
		}
		
		// 启动所有Ping线程
		for (i=0; i<pingThreadCount; i++) {
			pingThreads[i].start();
		}
		
		while(true) {
			boolean bCompleted = true;
			for (i=0; i<pingThreadCount; i++) {
				if (!pingThreads[i].isCompleted()) {
					bCompleted = false;
					break;
				}
			}
			// 如果所有Ping线程都结束，则跳出循环
			if (bCompleted)	
				break;
			try {	// 确保所有ping都结束后，才能开始下一次的ping
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// Ping线程
	class PingThread extends Thread {
		private List<MonitorPingDest> list = new ArrayList<MonitorPingDest>();
		
		public boolean bCompleted = false;
		
		public PingThread() {
			;
		}
		
		public boolean isCompleted() {
			return this.bCompleted;
		}
		
		public void addElement(MonitorPingDest pd) {
			if (pd != null) {
				list.add(pd);
			}
		}
		
		public void run() {
			List<MonitorPingResult> resList = new ArrayList<MonitorPingResult>();
			for (MonitorPingDest pd : list) {
				// 执行ping操作，返回结果
				MonitorPingResult pr = PingTools.ping(pd.getDestIpUrl(), pingNum, pingSize, pingTimeout);
				if (pr != null) {
					pr.setMonitorPingDest(pd);
					pr.setLoopGap(pingLoopGap);
					resList.add(pr);
					// 添加到“上一次的Ping结果缓存”
					synchronized (PingUiPubData.latestPingResultList) {
						boolean hasValue = false;
						for (MonitorPingResult thePr : PingUiPubData.latestPingResultList) {
							if (thePr.getMonitorPingDest().getDestIpUrl().equals(pr.getMonitorPingDest().getDestIpUrl())) {
								PingUiPubData.latestPingResultList.remove(thePr);
								PingUiPubData.latestPingResultList.add(pr);
								hasValue = true;
								break;
							}
						}
						if (!hasValue) {
							PingUiPubData.latestPingResultList.add(pr);
						}
					}
					// 若有PingViewFrame窗口存在，则还要更新PingUiPubData里的统计数据
					if (PingUiPubData.countOfPingView > 0) {
						synchronized (PingUiPubData.pingResultDayList) {
							boolean hasTheRecord = false;
							for (MonitorPingResultDay prd : PingUiPubData.pingResultDayList) {
								if (prd.getMonitorPingDest().getId().equals(pd.getId())) {
									mergePingResultDay(pr, prd);
									hasTheRecord = true;
									break;
								}
							}
							if (!hasTheRecord) {
								MonitorPingResultDay prd = new MonitorPingResultDay(pd, 0, 0, 
										-1, -1, 0, 0, 0, 0, pr.getCompletedTime());
								mergePingResultDay(pr, prd);
								PingUiPubData.pingResultDayList.add(prd);
							}
						}
					}
					// 攒够200个结果写一次数据库
					if (resList.size() >= 200){
						try {
							monitorPingResultService.batchInsert(resList);
							resList.clear();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// 报警检查
					// 必须是自动执行的Ping
					// 且一次都没有Ping通
					if (pingLoopGap != 0 && pr.getSuccessCount() == 0) {	
						synchronized (Alerter.pingFaultList) {
							alerter.mergePingFault(pd);
						}
					}
				}
			}
			if (resList != null && resList.size() > 0) {
				try {
					monitorPingResultService.batchInsert(resList);
					resList.clear();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			bCompleted = true;
		}
		
		// 将一个细节数据合并到统计数据中
		void mergePingResultDay(MonitorPingResult pr, MonitorPingResultDay prd) {
			if (pr == null || prd == null) {
				return;
			}
			if (prd.getMaxReplyTime() < pr.getMaxReplyTime()) {
				prd.setMaxReplyTime(pr.getMaxReplyTime());
        	}
        	if (prd.getMinReplyTime() > pr.getMinReplyTime() || prd.getMinReplyTime() == -1) {
        		prd.setMinReplyTime(pr.getMinReplyTime());
        	}
        	if (prd.getMaxReplyTime() == -1) {
        		prd.setMaxReplyTime(0);
            }
            if (prd.getMinReplyTime() == -1) {
            	prd.setMinReplyTime(0);
            }
        	prd.setSumReplyTime(prd.getSumReplyTime() + pr.getSumReplyTime());
        	prd.setSendCount(prd.getSendCount() + pr.getSendCount());
        	prd.setSuccessCount(prd.getSuccessCount() + pr.getSuccessCount());
        	prd.setSumTtl(prd.getSumTtl() + pr.getSumTtl());
        	if (pr.getLoopGap() > 0) {
        		if (pr.getSuccessCount() > 0) {
            		prd.setResponseTime(prd.getResponseTime() + pr.getLoopGap());
            	} else {
            		prd.setNoResponseTime(prd.getNoResponseTime() + pr.getLoopGap());
            	}
        	}
            prd.setCompletedTime(new Timestamp(System.currentTimeMillis()));
		}
	}
}
