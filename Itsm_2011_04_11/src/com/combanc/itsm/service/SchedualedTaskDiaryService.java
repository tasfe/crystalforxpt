package com.combanc.itsm.service;

import java.util.List;

import com.combanc.itsm.dao.SchedualedTaskDiaryDAO;
import com.combanc.itsm.pojo.SchedualedTaskDiary;

public class SchedualedTaskDiaryService {
	
	private SchedualedTaskDiaryDAO stdDAO;

	public SchedualedTaskDiaryDAO getStdDAO() {
		return stdDAO;
	}

	public void setStdDAO(SchedualedTaskDiaryDAO stdDAO) {
		this.stdDAO = stdDAO;
	}
	
	public void save(SchedualedTaskDiary std) {
		stdDAO.save(std);
	}
	public void findById(Integer id) {
		stdDAO.findById(id);
	}
	public List<SchedualedTaskDiary> findByTaskDetailId(Integer taskId){
		return stdDAO.findByTaskDetailId(taskId);
	}
	
}
