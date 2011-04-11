package com.combanc.itsm.action;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsProducer;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.Buildlocation;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.ItsmType;
import com.combanc.itsm.pojo.Location;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.tree.TreeDateService;

public class UtilTreeAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private TreeDateService treeDateService;
	private Users user;
	private int page;
	private Integer pageSize = 10;
	private PageBean pageBean;
	public TreeDateService getTreeDateService() {
		return treeDateService;
	}

	public void setTreeDateService(TreeDateService treeDateService) {
		this.treeDateService = treeDateService;
	}
	public String getChildren(){
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
		List<AssetsType> asstesTypes = this.treeDateService.getAssetsTypeService().findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (AssetsType asstesType : asstesTypes) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", asstesType.getId());
				jsonDept.put("text", asstesType.getName());
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

	public String department(){
		return "success";
	}
	
	public String department1(){
		return "success";
	}
	
	public String getdepartmentChildren(){
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
		List<Department> departments = this.treeDateService.getDepartmentService().findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (Department department : departments) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", department.getId());
				jsonDept.put("text", department.getName());
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
	
	
	public String assetstype(){
		return "success";
	}
	
	
	
	public String itsmtype(){
		return "success";
	}
	
	public String getitsmtypeChildren(){
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
		List<ItsmType> itsmTypes = this.treeDateService.getItsmTypeService().findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (ItsmType itsmType : itsmTypes) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", itsmType.getId());
				jsonDept.put("text", itsmType.getName());
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
	
	public String buildlocation(){
		return "success";
	}
	
	public String assetsState(){
		return "success";
	}
	
	public String getassetsStateChildren(){
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
		List<AssetsState> assetsStates = this.treeDateService.getAssetsStateService().findAll();
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (AssetsState assetsState : assetsStates) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", assetsState.getId());
				jsonDept.put("text", assetsState.getName());
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
	
	public String location(){
		return "success";
	}
	
	public String getlocationChildren(){
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
		List<Location> locations = this.treeDateService.getLocationService().findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (Location location : locations) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", location.getId());
				jsonDept.put("text", location.getName());
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
	
	public String getbuildlocationChildren(){
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
		List<Buildlocation> buildlocations = this.treeDateService.getBuildlocationService().findAllByPid(parentID);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (Buildlocation buildlocation : buildlocations) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", buildlocation.getId());
				jsonDept.put("text", buildlocation.getName());
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
	
	
	public String support(){
		return "success";
	}
	
	public String getsupportChildren(){
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
		List<AssetsProducer> supports = this.treeDateService.getProducerService().getAssetsProducerDAO().findByIntType(1);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (AssetsProducer support : supports) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", support.getId());
				jsonDept.put("text", support.getName());
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
	
	public String producer(){
		return "success";
	}
	
	public String getproducerChildren(){
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
		List<AssetsProducer> producers = this.treeDateService.getProducerService().getAssetsProducerDAO().findByIntType(2);
		//---------------------------- 三、构建Json响应数据
		JSONArray jsonResult = new JSONArray();
		try {
			for (AssetsProducer producer : producers) {
				JSONObject jsonDept = new JSONObject();
				jsonDept.put("cls", "file");
				jsonDept.put("id", producer.getId());
				jsonDept.put("text", producer.getName());
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
	
	
	public String user(){
		this.pageBean =treeDateService.getUserService().findUsers(user,pageSize,page);
		return "success";
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
