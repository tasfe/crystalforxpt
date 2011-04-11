package com.combanc.itsm.action.systemmanage;

import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsInfo;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.Buildlocation;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.AssetsChangeService;
import com.combanc.itsm.service.AssetsConfigService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.BuildlocationService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.opensymphony.xwork2.ActionContext;

public class ShowAssetsAction extends BaseActionSupport implements
ServletRequestAware {

	private static final Log log = LogFactory.getLog(AssetsAction.class);
	private static final long serialVersionUID = 1L;
	private String actionURI;
	private AssetsBase assets;
	private String assetsId;
	private AssetsService assetsService;
	private AssetsType assetsType;
	private AssetsTypeService assetsTypeService;
	private AssetsConfigService assetsConfigService;
	private AssetsStateService assetsStateService;
	private BuildlocationService buildlocationService;

	public BuildlocationService getBuildlocationService() {
		return buildlocationService;
	}

	public void setBuildlocationService(BuildlocationService buildlocationService) {
		this.buildlocationService = buildlocationService;
	}

	public AssetsStateService getAssetsStateService() {
		return assetsStateService;
	}

	public void setAssetsStateService(AssetsStateService assetsStateService) {
		this.assetsStateService = assetsStateService;
	}

	public AssetsConfigService getAssetsConfigService() {
		return assetsConfigService;
	}

	public void setAssetsConfigService(AssetsConfigService assetsConfigService) {
		this.assetsConfigService = assetsConfigService;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public AssetsBase getAssets() {
		return assets;
	}

	public void setAssets(AssetsBase assets) {
		this.assets = assets;
	}

	public String getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}

	public AssetsType getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}

	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	private DepartmentService departmentService;
	private int page;
	private PageBean pageBean;
	private Integer pageSize = 15;

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public String qurey() throws Exception {
		Map session = ActionContext.getContext().getSession();
    	Users user=(Users) session.get("user");
    	int asset=0;
    	if(session.get("asset")!=null){
    		asset=Integer.parseInt(session.get("asset").toString());
    	}
		pageBean = assetsService.query(assets, pageSize, page, user, asset);
		return "success";

	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private AssetsInfo info;
	private List assetsConfigList;
	private List assetsConfigInfoList;
	public AssetsInfo getInfo() {
		return info;
	}

	public void setInfo(AssetsInfo info) {
		this.info = info;
	}

	public List getAssetsConfigList() {
		return assetsConfigList;
	}

	public void setAssetsConfigList(List assetsConfigList) {
		this.assetsConfigList = assetsConfigList;
	}

	public List getAssetsConfigInfoList() {
		return assetsConfigInfoList;
	}

	public void setAssetsConfigInfoList(List assetsConfigInfoList) {
		this.assetsConfigInfoList = assetsConfigInfoList;
	}

	private List<Accessory> accessoryList;
	private FileUpDownService fileUpDownService;

	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	public String show() throws Exception {
		assets = assetsService.getAssetsByCode(assetsId);
		assetsType=assets.getAssetsType();
		accessoryList=fileUpDownService.getAccessorys("assets_base",assets.getCode());
		info=assetsService.findbyassetsid(assetsId);
		String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
		assetsConfigList=assetsConfigService.findbysql(queryString);
		String queryString1="from AssetsConfig where assets_Type_id='"+assetsType.getId()+"' and flag='1' and ischoose='1'";
		assetsConfigInfoList=assetsConfigService.findbysql(queryString1);
		ActionContext.getContext().getSession().put("assetsConfigList", assetsConfigList);
		ActionContext.getContext().getSession().put("assetsConfigInfoList", assetsConfigInfoList);
 		changelist=assetsChangeService.findByassetsId(assetsId);
		return "success";
	}
	private AssetsChangeService assetsChangeService;
	public AssetsChangeService getAssetsChangeService() {
		return assetsChangeService;
	}

	public void setAssetsChangeService(AssetsChangeService assetsChangeService) {
		this.assetsChangeService = assetsChangeService;
	}

	private List changelist;

	public List getChangelist() {
		return changelist;
	}

	public void setChangelist(List changelist) {
		this.changelist = changelist;
	}

	
	public String main() throws Exception {
		return "success";
	}
	
	public String title() throws Exception{
		return "success";
	}
	
	public String top(){
		return "success";
	}
	
	public String tree(){
		//---------------------------- 二、处理业务逻辑
		List<AssetsState> asstesStates=assetsStateService.findAll();
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		AssetsBase assetsbase=null;
		Map session = ActionContext.getContext().getSession();
    	Users user=(Users) session.get("user");
    	int asset=0;
    	if(session.get("asset")!=null){
    		asset=Integer.parseInt(session.get("asset").toString());
    	}
		try {
			for (AssetsState asstesState : asstesStates) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", asstesState.getId());
				assetsbase=new AssetsBase();
				assetsbase.setAssetsState(asstesState);
				if(asset==0){
					assetsbase.setUsersByChargeId(user);
				}else if(asset==1){
					assetsbase.setDepartment(user.getDepartment());
				}
				int size = assetsService.getAssetsSizeByState(assetsbase);
				jsonDept.put("text", asstesState.getName()+"("+size+")");
				jsonResult.put(jsonDept);
			}
		}catch (JSONException e) {
			
			e.printStackTrace();
		}
		responseData = jsonResult.toString();

		//---------------------------- 四、返回页面
		// 清空
		return "json_data";
	}
	
	public String typetree(){
		//---------------------------- 一、解析JSON请求数据
		JSONObject jsonRequest = null;
		int parentID = 0;
		try {
			jsonRequest = new JSONObject(requestData);
			parentID = jsonRequest.getInt("parentID");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		//---------------------------- 二、处理业务逻辑
		List<AssetsType> asstesTypes =assetsTypeService.findAllByPid(parentID);
		Map session = ActionContext.getContext().getSession();
    	Users user=(Users) session.get("user");
    	int asset=0;
    	if(session.get("asset")!=null){
    		asset=Integer.parseInt(session.get("asset").toString());
    	}
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (AssetsType asstesType : asstesTypes) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", asstesType.getId());
				String sql="SELECT COUNT(*) FROM AssetsBase WHERE assetsType.flag like '%"+asstesType.getFlag()+asstesType.getId()+":%'";
				String sqls="select count(*) from AssetsBase where assetsType.id='"+asstesType.getId()+"'";
				if(asset==0){
					sql=sql+" and usersByChargeId.id='"+user.getId()+"'";
					sqls=sqls+" and usersByChargeId.id='"+user.getId()+"'";
				}else if(asset==1){
					sql=sql+" and department.id='"+user.getDepartment().getId()+"'";
					sqls=sqls+" and department.id='"+user.getDepartment().getId()+"'";
				}
				Object size=assetsService.getAllRows(sql).get(0);
				if(size.toString().equals("0")){
					size=assetsService.getAllRows(sqls).get(0);
				}
				jsonDept.put("text", asstesType.getName()+"("+size.toString()+")");
				jsonDept.put("showname",asstesType.getName());
				
				jsonResult.put(jsonDept);
			}
		}catch (JSONException e) {
			
			e.printStackTrace();
		}
		responseData = jsonResult.toString();

		return "json_data";
	}
	
	
	public String buildlocationtree(){
		//---------------------------- 一、解析JSON请求数据
		JSONObject jsonRequest = null;
		int parentID = 0;
		try {
			jsonRequest = new JSONObject(requestData);
			parentID = jsonRequest.getInt("parentID");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		//---------------------------- 二、处理业务逻辑
		List<Buildlocation> buildlocations= buildlocationService.findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (Buildlocation buildlocation : buildlocations) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", buildlocation.getId());
				String sql=null;
				if(buildlocation.getId()==1){
					sql="SELECT COUNT(*) FROM AssetsBase WHERE buildlocation.code like '%"+buildlocation.getCode()+"%'";
				}else{
					sql="SELECT COUNT(*) FROM AssetsBase WHERE buildlocation.code='"+buildlocation.getCode()+"'";
				}
				Object size=assetsService.getAllRows(sql).get(0); 
				jsonDept.put("text", buildlocation.getName()+"("+size.toString()+")");				
				jsonResult.put(jsonDept);
			}
		}catch (JSONException e) {
			
			e.printStackTrace();
		}
		responseData = jsonResult.toString();

		return "json_data";
	}
	
	public String departmenttree(){
		//---------------------------- 一、解析JSON请求数据
		JSONObject jsonRequest = null;
		int parentID = 0;
		try {
			jsonRequest = new JSONObject(requestData);
			parentID = jsonRequest.getInt("parentID");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		//---------------------------- 二、处理业务逻辑
		List<Department> departments=departmentService.findAllByPid(parentID);
//		List<AssetsType> asstesTypes =assetsTypeService.findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (Department department : departments) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", department.getId());
				String sql=null;
				if(department.getId()==1){
					sql="SELECT COUNT(*) FROM AssetsBase WHERE usersByChargeId.department.code like '%"+department.getCode()+"%' and assetsState.id!='' and des='0' ";
				}else{
					sql="SELECT COUNT(*) FROM AssetsBase WHERE usersByChargeId.department.code='"+department.getCode()+"' and assetsState.id!='' and des='0' ";
				}
				Object size=assetsService.getAllRows(sql).get(0); 
				jsonDept.put("showname",department.getName());
				jsonDept.put("text", department.getName()+"("+size.toString()+")");				
				jsonResult.put(jsonDept);
			}
		}catch (JSONException e) {
			
			e.printStackTrace();
		}
		responseData = jsonResult.toString();

		return "json_data";
	}
	public String state(){
		return "success";
	}
	
	public String department(){
		return "success";
	}
	public String buildlocation(){
		return "success";
	}
	public String type(){
		return "success";
	}
	
}
