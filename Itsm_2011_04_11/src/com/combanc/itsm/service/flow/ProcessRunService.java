package com.combanc.itsm.service.flow;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessInstance;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ProcessRunDAO;
import com.combanc.itsm.pojo.ProDefinition;
import com.combanc.itsm.pojo.ProcessRun;
import com.combanc.itsm.pojo.Users;

public class ProcessRunService extends BaseService<ProcessRun, Integer> {
	private ProcessRunDAO processRunDAO;

	public ProcessRunDAO getProcessRunDAO() {
		return processRunDAO;
	}

	public void setProcessRunDAO(ProcessRunDAO processRunDAO) {
		this.processRunDAO = processRunDAO;
	}

	@Resource
	private JbpmService jbpmService;

	public ProcessRun getByExeId(String exeId) {
		ProcessInstance pi = this.jbpmService.getProcessInstanceByExeId(exeId);
		if (pi != null) {
			return getByPiId(pi.getId());
		}
		return null;
	}

	public ProcessRun getByTaskId(String taskId) {
		ProcessInstance pi = this.jbpmService
				.getProcessInstanceByTaskId(taskId);
		if (pi != null) {
			return getByPiId(pi.getId());
		}
		return null;
	}

	public ProcessRun getByPiId(String piId) {
		return this.processRunDAO.getByPiId(piId);
	}

	public ProcessRun initNewProcessRun(ProDefinition proDefinition) {
		ProcessRun processRun = new ProcessRun();
		Users user = null;

		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		Timestamp ts = new Timestamp(curDate.getTime());

		processRun.setSubject(proDefinition.getName() + sdf.format(curDate)
				+ "(" + user.getTruename() + ")");
		processRun.setCreator(user.getTruename());
		processRun.setUsers(user);
		processRun.setCreatetime(ts);
		processRun.setProDefinition(proDefinition);

		return processRun;
	}

	public void removeByDefId(Integer defId) {
		List processRunList = this.processRunDAO.getByDefId(defId,
				new PageBean(0, 25));
		for (int i = 0; i < processRunList.size(); ++i) {
			this.dao.remove((ProcessRun) processRunList.get(i));
		}

		if (processRunList.size() == 25)
			removeByDefId(defId);
	}

	public List<ProcessRun> getByUserIdSubject(Long userId, String subject,
			PageBean pb) {
		return this.processRunDAO.getByUserIdSubject(userId, subject, pb);
	}
}