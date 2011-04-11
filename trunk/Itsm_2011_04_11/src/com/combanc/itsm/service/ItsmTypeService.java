package com.combanc.itsm.service;

import java.util.List;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ItsmTypeDAO;
import com.combanc.itsm.pojo.ItsmType;

public class ItsmTypeService extends BaseService<ItsmType, Integer>{

	private ItsmTypeDAO itsmTypeDAO;

	
	public ItsmTypeDAO getItsmTypeDAO() {
		return itsmTypeDAO;
	}

	public void setItsmTypeDAO(ItsmTypeDAO itsmTypeDAO) {
		this.itsmTypeDAO = itsmTypeDAO;
	}

	public void save(ItsmType build){
		ItsmType buildlocation=itsmTypeDAO.findById(build.getPid());
		build.setCode(this.generateCode(buildlocation));
		itsmTypeDAO.save(build);
	}
	
	
	/*
	 * 生成部门编码,使用三位编码，如:001002
	 */
	public String generateCode(ItsmType parent){
		List<ItsmType> itsmtypes=null;
		itsmtypes=this.itsmTypeDAO.findByPid(parent==null?null:parent.getId());//找兄弟节点
		if(itsmtypes==null||itsmtypes.isEmpty()){//不存在兄弟
			if(null==parent){//父亲也为空的话，代表第一次添加
				return "001";
			}else{
				return parent.getCode()+"001";
			}
		}
		String code="";
		//
		ItsmType max=itsmtypes.get(0);
		int maxCode=0;
		for(ItsmType itsmtype:itsmtypes){
			int currCode=Integer.valueOf(itsmtype.getCode().substring(itsmtype.getCode().length()-3, itsmtype.getCode().length()));
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
	
	public void update(ItsmType buile){
		itsmTypeDAO.update(buile);
	}
	
	public List findAll(){
		return itsmTypeDAO.findAll();
	}
	
	public List findAllByPid(int pid){
		return itsmTypeDAO.findByPid(pid);
	}
	
	public ItsmType findByName(String name){
		ItsmType build=new ItsmType();
		List list=itsmTypeDAO.findByName(name);
		if(list.size()==0){
			build=null;
		}else{
			build=(ItsmType)itsmTypeDAO.findByName(name).get(0);
		}
		return build;
	}
	
	public ItsmType findlocationById(int id){
		ItsmType build=new ItsmType();
		build=(ItsmType)itsmTypeDAO.findById(id);
		return build;
	}
	
	public void delete(ItsmType build){
		itsmTypeDAO.delete(build);
	}
	
	public ItsmType findbyid(String id){
		ItsmType build=itsmTypeDAO.findById(Integer.parseInt(id));
		return build;
	}
	
}
