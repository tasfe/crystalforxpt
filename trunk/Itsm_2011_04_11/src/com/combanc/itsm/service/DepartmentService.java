package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.DepartmentDAO;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Location;

public class DepartmentService {

	private DepartmentDAO departmentDAO;
	/*
	 * 获取树的最大深度
	 */
	public int getLevel(){
		return departmentDAO.getLevel();
	}
	/*
	 * 根据父节点CODE查找其下所有子节点
	 */
	public List<Department> getSubDepartmentsByCode(String code){
		return this.departmentDAO.getSubDepartmentsByCode(code);
	}
	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public List<Department> findAll() {
		return departmentDAO.findAll(Department.class);
	}
	public List<Department> getAllDeptsOrderLevel(){
		return departmentDAO.getAllDeptsOrderLevel();
	}
	public List<Department> findByName(String name){
		return departmentDAO.findByProperty("name", name);
	}

	public List<Department> findAllByPid(Integer pid) {
		return departmentDAO.findByPid(pid);
	}

	public List<Department> findByCode(String pid) {
		return departmentDAO.findByCode(pid);
	}
	
	public Department findDepartmentById(Integer departmentId) {
		return (Department) departmentDAO.findById(Department.class,
				departmentId);
	}
	/*
	 * 父亲节点不能为空
	 */
	public void saveDepartment(Department Department) {
		Department pDepartment =departmentDAO.findById(Department.getPid());
		Department.setCode(this.generateCode(pDepartment));
		departmentDAO.save(Department);
		//Department.setCode(pDepartment.getCode()+Department.getId()+"$");
		//departmentDAO.update(Department);
		
	}
	/*
	 * 生成部门编码,使用三位编码，如:001002
	 */
	@SuppressWarnings("unchecked")
	public String generateCode(Department parent){
		List<Department> departments=null;
		departments=this.departmentDAO.findByPid(parent==null?null:parent.getId());//找兄弟节点
		if(departments==null||departments.isEmpty()){//不存在兄弟
			if(null==parent){//父亲也为空的话，代表第一次添加
				return "001";
			}else{
				return parent.getCode()+"001";
			}
		}
		String code="";
		//
		Department max=departments.get(0);
		int maxCode=0;
		for(Department department:departments){
			int currCode=Integer.valueOf(department.getCode().substring(department.getCode().length()-3, department.getCode().length()));
			if(currCode>maxCode){
				maxCode=currCode;
			}
		}
		if(String.valueOf(max.getCode()).length()==3){
			code=valueOf(String.valueOf(maxCode+1));
		}else{
			code=max.getCode().substring(0,max.getCode().length()-3)+valueOf(String.valueOf(maxCode+1));
		}
		return code;
	}
	public String valueOf(String str){
		if(str==null||str.isEmpty()){
			return "000";
		}
		try{
			int code=Integer.valueOf(str);	
			if(code<10&&code>0){
				return "00"+code;
			}
			if(code<100&&code>=10){
				return "0"+code;
			}else{
				return String.valueOf(code);
			}
		}catch (Exception e) {
			return "000";
		}
	}
	public void update(Department Department) {
		departmentDAO.update(Department);
	}

	public void saveOrUpdate(Department Department) {
		departmentDAO.saveOrUpdate(Department);
	}

	public void deleteById(Integer departmentId) {
		Department Department = null;
		if (departmentId != null)
			Department = (Department) departmentDAO.findById(Department.class,
					departmentId);
		if (Department != null)
			departmentDAO.delete(Department);
	}
}
