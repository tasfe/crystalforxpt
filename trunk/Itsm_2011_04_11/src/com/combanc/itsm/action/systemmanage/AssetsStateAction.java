package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.service.AssetsChangeService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;

public class AssetsStateAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private AssetsStateService assetsStateService;
	private AssetsService assetsService;
	public AssetsService getAssetsService() {
		return assetsService;
	}
	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}
	public AssetsChangeService getAssetsChangeService() {
		return assetsChangeService;
	}
	public void setAssetsChangeService(AssetsChangeService assetsChangeService) {
		this.assetsChangeService = assetsChangeService;
	}

	private AssetsChangeService assetsChangeService;
	public AssetsStateService getAssetsStateService() {
		return assetsStateService;
	}
	public void setAssetsStateService(AssetsStateService assetsStateService) {
		this.assetsStateService = assetsStateService;
	}
	
	private List assetsStatelist;
	private AssetsState assetsState;
	private int message=0;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	public List getAssetsStatelist() {
		return assetsStatelist;
	}
	public void setAssetsStatelist(List assetsStatelist) {
		this.assetsStatelist = assetsStatelist;
	}
	public AssetsState getAssetsState() {
		return assetsState;
	}
	public void setAssetsState(AssetsState assetsState) {
		this.assetsState = assetsState;
	}
	public String list(){
		assetsStatelist=assetsStateService.findAll();
		message=message;
		return "success";
	}
	public String add(){
		List list=assetsStateService.findbySequence(assetsState.getSequence());
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				AssetsState state=(AssetsState)list.get(i);
				state.setSequence(state.getSequence()+1);
				assetsStateService.update(state);
			}
		}
		assetsStateService.save(assetsState);
		message=1;
		return "success";
	}
	
	public String del(){
		AssetsState state=assetsStateService.findbyId(id);
		List list=assetsStateService.findbySequence(state.getSequence());
		if(list.size()!=0){
			for(int i=0;i<list.size();i++){
				AssetsState states=(AssetsState)list.get(i);
				states.setSequence(states.getSequence()-1);
				assetsStateService.update(states);
			}
		}
		String sqlbase="from AssetsBase model where model.assetsState.id="+id;
		String sqlchange="from AssetsChange model where model.assetsState.id="+id;
		int i=assetsService.getAllRowCount(sqlbase);
		int j=assetsChangeService.getAssetsChangeDAO().getAllRowCount(sqlchange);
		if(i==0&&j==0){
			assetsStateService.delete(state);
		}else{
			message=1;
		}
		return "success";
	}
	
	public String show(){
		assetsState=assetsStateService.findbyId(id);
		return "success";
	}
	
	public String update(){
		List list=assetsStateService.findbySequence(assetsState.getSequence());
		if(list.size()!=0){
			AssetsState state1=(AssetsState)list.get(0);
			if(state1.getSequence()>assetsState.getSequence()){
				assetsStateService.update(assetsState);
				message=1;
			}else{
				for(int i=0;i<list.size();i++){
					AssetsState state=(AssetsState)list.get(i);
					state.setSequence(state.getSequence()+1);
					assetsStateService.update(state);
				}
				assetsStateService.update(assetsState);
				message=1;
			}
		}else{
			assetsStateService.update(assetsState);
			message=1;
		}
		return "success";
	}
	
}
