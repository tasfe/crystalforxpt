package com.combanc.itsm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.mapping.Array;

import com.combanc.itsm.dao.TaskAllocationDAO;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.ServiceCategory;
import com.combanc.itsm.pojo.TaskAllocation;

public class TaskAllocationService {
	public TaskAllocationDAO taskAllocationDAO;
	public DepartmentService departmentService;

	/**
	 * @return the departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService
	 *            the departmentService to set
	 */
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * @return the serviceCategoryService
	 */
	public ServiceCategoryService getServiceCategoryService() {
		return serviceCategoryService;
	}

	/**
	 * @param serviceCategoryService
	 *            the serviceCategoryService to set
	 */
	public void setServiceCategoryService(
			ServiceCategoryService serviceCategoryService) {
		this.serviceCategoryService = serviceCategoryService;
	}

	public ServiceCategoryService serviceCategoryService;

	public TaskAllocationDAO getTaskAllocationDAO() {
		return taskAllocationDAO;
	}

	public void setTaskAllocationDAO(TaskAllocationDAO taskAllocationDAO) {
		this.taskAllocationDAO = taskAllocationDAO;
	}

	public List<TaskAllocation> findAll() {
		return taskAllocationDAO.findAll();
	}

	public void save(TaskAllocation taskAllocation) {

		taskAllocationDAO.save(taskAllocation);
	}

	public void update(TaskAllocation taskAllocaiton) {
		taskAllocationDAO.update(taskAllocaiton);
	}

	public void saveOrUpdate(TaskAllocation taskAllocation) {
		taskAllocationDAO.attachDirty(taskAllocation);
	}

	public TaskAllocation findById(Integer taskAllocationId) {
		return taskAllocationDAO.findById(taskAllocationId);
	}

	public void deleteById(Integer taskAllocationId) {
		TaskAllocation taskAllocation = null;
		if (taskAllocationId != null) {
			taskAllocation = (TaskAllocation) taskAllocationDAO
					.findById(taskAllocationId);
		}
		if (taskAllocation != null) {
			taskAllocationDAO.delete(taskAllocation);
		}
	}

	public List findByDeptAndCate(Integer dep, Integer cat) {
		return taskAllocationDAO.findByDeptAndCate(dep, cat);
	}

	public List<TaskAllocation> getTaskAllocationsByDepIdAndCatId(int depId,
			int catId) {

		TaskAllocation instance = new TaskAllocation();

		Department department = departmentService.findDepartmentById(depId);
		Set<TaskAllocation> aa = department.getTaskAllocations();

		ServiceCategory typeCategory = serviceCategoryService.findById(catId);
		Set<TaskAllocation> bb = typeCategory.getTaskAllocations();

		List<TaskAllocation> tList = new ArrayList<TaskAllocation>();

		Iterator<TaskAllocation> diterator = aa.iterator();

		while (diterator.hasNext()) {
			int dId = diterator.next().getId();
			Iterator<TaskAllocation> titerator = bb.iterator();
			while (titerator.hasNext()) {
				TaskAllocation taskAllocation = (TaskAllocation) titerator
						.next();
				int tid = taskAllocation.getId();
				if (tid == dId) {
					tList.add(taskAllocation);
				}

			}

		}

		return tList;

	}
}
