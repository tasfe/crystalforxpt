package com.combanc.itsm.service.flow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.pvm.internal.task.TaskImpl;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.common.jbpm.pv.TaskInfo;
import com.combanc.itsm.dao.TaskDAO;
import com.combanc.itsm.pojo.JbpmTask;
import com.combanc.itsm.pojo.ProcessRun;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.UserService;

public class TaskService extends BaseService<TaskImpl, Integer> {
	@Resource
	private ProcessRunService processRunService;
	private TaskDAO taskDAO;

	@Resource
	private UserService userService;

	public TaskDAO getTaskDAO() {
		return taskDAO;
	}

	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}

	public List<TaskImpl> getTasksByUserId(String userId, PageBean pb) {
		return this.taskDAO.getTasksByUserId(userId, pb);
	}

	public List<TaskInfo> getTaskInfosByUserId(String userId, PageBean pb) {
		List<TaskImpl> list = getTasksByUserId(userId, pb);
		List taskInfoList = new ArrayList();
		for (TaskImpl taskImpl : list) {
			TaskInfo taskInfo = new TaskInfo(taskImpl);
			if (taskImpl.getAssignee() != null) {
				Users user = (Users) this.userService.get(new Integer(taskImpl
						.getAssignee()));
				taskInfo.setAssignee(user.getTruename());
			}

			ProcessRun processRun = this.processRunService.getByPiId(taskImpl
					.getExecutionId());
			if (processRun != null) {
				taskInfo.setTaskName(processRun.getProDefinition().getName()
						+ "--" + taskImpl.getActivityName());
				taskInfo.setActivityName(taskImpl.getActivityName());
			}

			taskInfoList.add(taskInfo);
		}
		return taskInfoList;
	}

	public Set<Integer> getHastenByActivityNameVarKeyLongVal(
			String activityName, String varKey, Long value) {
		List<JbpmTask> jtasks = this.taskDAO.getByActivityNameVarKeyLongVal(
				activityName, varKey, value);
		Set userIds = new HashSet();
		for (JbpmTask jtask : jtasks) {
			if (jtask.getAssignee() == null) {
				List userlist = this.taskDAO.getUserIdByTask(jtask.getTaskId());
				userIds.addAll(userlist);
				List<Integer> groupList = this.taskDAO.getGroupByTask(jtask
						.getTaskId());
				for (Integer l : groupList) {
					List<Users> uList = this.userService.findByRoleId(l);
					List idList = new ArrayList();
					for (Users user : uList) {
						idList.add(user.getId());
					}
					userIds.addAll(idList);
				}
			} else {
				userIds.add(new Long(jtask.getAssignee()));
			}
		}
		return userIds;
	}
}