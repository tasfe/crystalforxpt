package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.BuildlocationDAO;
import com.combanc.itsm.pojo.Buildlocation;
import com.combanc.itsm.pojo.Department;

public class BuildlocationService extends BaseService<Buildlocation, Integer> {

	private BuildlocationDAO buildlocationDAO;

	
	public BuildlocationDAO getBuildlocationDAO() {
		return buildlocationDAO;
	}

	public void setBuildlocationDAO(BuildlocationDAO buildlocationDAO) {
		this.buildlocationDAO = buildlocationDAO;
	}

	public void save(Buildlocation build){
		Buildlocation buildlocation=buildlocationDAO.findById(build.getPid());
		build.setCode(this.generateCode(buildlocation));
		build.setAllname(this.genrateName(build));
		buildlocationDAO.save(build);
	}
	
	/*
	 * 生成部门编码,使用三位编码，如:001002
	 */
	public String generateCode(Buildlocation parent){
		List<Buildlocation> buildlocations=null;
		buildlocations=this.buildlocationDAO.findByPid(parent==null?null:parent.getId());//找兄弟节点
		if(buildlocations==null||buildlocations.isEmpty()){//不存在兄弟
			if(null==parent){//父亲也为空的话，代表第一次添加
				return "001";
			}else{
				return parent.getCode()+"001";
			}
		}
		String code="";
		//
		Buildlocation max=buildlocations.get(0);
		int maxCode=0;
		for(Buildlocation buildlocation:buildlocations){
			int currCode=Integer.valueOf(buildlocation.getCode().substring(buildlocation.getCode().length()-3, buildlocation.getCode().length()));
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
	
	public String genrateName(Buildlocation build){
		String name="";
		if(build.getPid()==1){
			name=build.getName();
		}else{
			String allname=buildlocationDAO.findById(build.getPid()).getAllname();
			name=allname+build.getName();
		}
		return name;
	}
	
	public void update(Buildlocation buile){
		buildlocationDAO.update(buile);
	}
	
	public List findAll(){
		return buildlocationDAO.findAll();
	}
	
	public List findAllByPid(int pid){
		return buildlocationDAO.findByPid(pid);
	}
	
	public Buildlocation findByName(String name){
		Buildlocation build=new Buildlocation();
		List list=buildlocationDAO.findByName(name);
		if(list.size()==0){
			build=null;
		}else{
			build=(Buildlocation)buildlocationDAO.findByName(name).get(0);
		}
		return build;
	}
	
	public Buildlocation findlocationById(int id){
		Buildlocation build=new Buildlocation();
		build=(Buildlocation)buildlocationDAO.findById(id);
		return build;
	}
	
	public void delete(Buildlocation build){
		buildlocationDAO.delete(build);
	}
	
	public Buildlocation findbyid(String id){
		Buildlocation build=buildlocationDAO.findById(Integer.parseInt(id));
		return build;
	}
	
}
