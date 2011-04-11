package com.combanc.itsm.action.wap;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsChange;
import com.combanc.itsm.pojo.AssetsInfo;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.service.AssetsChangeService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.UserService;

public class WapAssetsAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(WapAssetsAction.class);
	private AssetsService assetsService;
	private AssetsStateService assetsStateService;
	private UserService userService;
	private AssetsChangeService assetsChangeService;
	private AssetsTypeService assetsTypeService;
	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}
	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public AssetsChangeService getAssetsChangeService() {
		return assetsChangeService;
	}
	public void setAssetsChangeService(AssetsChangeService assetsChangeService) {
		this.assetsChangeService = assetsChangeService;
	}
	public AssetsStateService getAssetsStateService() {
		return assetsStateService;
	}
	public void setAssetsStateService(AssetsStateService assetsStateService) {
		this.assetsStateService = assetsStateService;
	}
	public AssetsService getAssetsService() {
		return assetsService;
	}
	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}
	public static Log getLog() {
		return log;
	}
	
	private String codeId;
	private AssetsBase base;
	private String code;
	private AssetsChange assetsChange;
	
	public AssetsChange getAssetsChange() {
		return assetsChange;
	}
	public void setAssetsChange(AssetsChange assetsChange) {
		this.assetsChange = assetsChange;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public AssetsBase getBase() {
		return base;
	}
	public void setBase(AssetsBase base) {
		this.base = base;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	private int message=0;
	public int getMessage() {
		return message;
	}
	public void setMessage(int message) {
		this.message = message;
	}
	
	public String search()throws Exception{
		if(!codeId.equals("")){
			base=assetsService.findByCodeId(codeId);
			if(base!=null){
				message=1;
			}
		}
		return "success";
	}
	public String main()throws Exception{
		return "success";
	}
	public String functionlist()throws Exception{
		return "success";
	}
	public String change()throws Exception{
		base=assetsService.findById(Integer.parseInt(code));
		statelist=assetsStateService.findAll();
		userlist=userService.getUserDAO().findAll();
		Date date=new Date();
		DateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		changeTime=formats.format(date).toString();
		remark=remark;
		return "success";
	}
	private List typelist;
	public List getTypelist() {
		return typelist;
	}
	public void setTypelist(List typelist) {
		this.typelist = typelist;
	}
	public String inStorechoose()throws Exception{
		typelist=assetsTypeService.findAll();
		Date date=new Date();
		DateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
		inDate=formats.format(date).toString();
		return "success";
	}
	private String assetstype;
	private String inDate;
	public String getAssetstype() {
		return assetstype;
	}
	public void setAssetstype(String assetstype) {
		this.assetstype = assetstype;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String instoresave()throws Exception{
		AssetsBase bases=assetsService.findByCodeId(codeId);
		typelist=assetsTypeService.findAll();
		assetstype=assetstype;
		inDate=inDate;
		if(bases==null){
			AssetsType assetsType=assetsTypeService.findById(Integer.parseInt(assetstype));
			AssetsBase assets=new AssetsBase();
			assets.setCodeId(codeId);
			assets.setAssetsType(assetsType);
			assets.setInDate(this.StringTime(inDate));
			assetsService.save(assets);
			AssetsInfo info=new AssetsInfo();
			info.setAssetsBase(assets);
			assetsService.assetsinfosave(info);
			message=1;
			
		}else{
			message=2;
		}
		return "success";
	}
	
	private int type;
	private int Charge;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCharge() {
		return Charge;
	}
	public void setCharge(int charge) {
		Charge = charge;
	}
	public int getManagers() {
		return Managers;
	}
	public void setManagers(int managers) {
		Managers = managers;
	}
	public int getUser() {
		return User;
	}
	public void setUser(int user) {
		User = user;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getChangeDescription() {
		return changeDescription;
	}
	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	private int Managers;
	private int User;
	private String changeTime;
	private String remark;
	private String changeDescription;
	
	
	public String changesave()throws Exception{
		AssetsChange change=new AssetsChange();
		base=assetsService.findById(Integer.parseInt(code));
		statelist=assetsStateService.findAll();
		userlist=userService.findAllUser();
		change.setAssetsBase(base);
		AssetsState state=null;
		if(type!=-1){
			state=assetsStateService.findbyId(type);
			change.setAssetsState(state);
		}
		if(Charge!=-1){
			change.setUsersByChargeid(userService.findUserById(Charge));//负责人
		}
		if(Managers!=-1){
			change.setUsersByManagersid(userService.findUserById(Managers));//经办人
		}
		if(User!=-1){
			change.setUsersByUserid(userService.findUserById(User));//使用人
		}
		change.setChangeTime(this.StringTime(changeTime));
		change.setRemark(remark);
		change.setChangeDescription(changeDescription);
		base.setAssetsState(state);
		assetsService.updateAssetsByCode(base);
		assetsChangeService.save(change);
		message=1;
		return "success";
	}
	
	private List statelist;
	
	public List getStatelist() {
		return statelist;
	}
	public void setStatelist(List statelist) {
		this.statelist = statelist;
	}
	private List userlist;
	public List getUserlist() {
		return userlist;
	}
	public void setUserlist(List userlist) {
		this.userlist = userlist;
	}
	
	public Timestamp StringTime(String value) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return new Timestamp(simpleDateFormat.parse(value).getTime());
	}
}
