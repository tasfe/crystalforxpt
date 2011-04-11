package com.combanc.itsm.action.systemmanage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.htmlobj.AssetsQurey;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsChange;
import com.combanc.itsm.pojo.AssetsConfig;
import com.combanc.itsm.pojo.AssetsInfo;
import com.combanc.itsm.pojo.AssetsProducer;
import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.Buildlocation;
import com.combanc.itsm.pojo.Department;
import com.combanc.itsm.pojo.ItsmType;
import com.combanc.itsm.pojo.Location;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.AssetsChangeService;
import com.combanc.itsm.service.AssetsConfigService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.BuildlocationService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.ItsmTypeService;
import com.combanc.itsm.service.LocationService;
import com.combanc.itsm.service.ProducerService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.updown.FileUpDownService;
import com.combanc.itsm.util.CartogramInfo;
import com.combanc.itsm.util.StringUtil;
import com.combanc.monitor.pojo.MonitorDeviceType;
import com.combanc.monitor.service.MonitorDeviceTypeService;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author Administrator
 * 
 */
public class AssetsAction extends BaseActionSupport {
	HttpServletRequest request = null;
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AssetsAction.class);
	private AssetsService assetsService;
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	private Map<String, Object> session = null;
	private AssetsTypeService assetsTypeService;
	private AssetsConfigService assetsConfigService;
	private AssetsStateService assetsStateService;
	private BuildlocationService buildlocationService;
	private ItsmTypeService itsmTypeService;
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BuildlocationService getBuildlocationService() {
		return buildlocationService;
	}

	public void setBuildlocationService(BuildlocationService buildlocationService) {
		this.buildlocationService = buildlocationService;
	}

	public ItsmTypeService getItsmTypeService() {
		return itsmTypeService;
	}

	public void setItsmTypeService(ItsmTypeService itsmTypeService) {
		this.itsmTypeService = itsmTypeService;
	}

	private FileUpDownService fileUpDownService;
	private File[] file;
	private String[] fileFileName;
    private String[] contentType;
	private String code0;
	private String codeId0;
	private String name0;
	private String model0;
	private String assetsType0;
	
	private String itsmType0;
    private String assetsState0;
    private String qualityTime0;
    private String price0;
    private String buyDate0;
    private String exitfacotryDate0;
    private String inDate0;
    private String location0;
    private String buildlocation0;
    
    private String system0;
    private String ip0;
    private String mac0;
    private String devicename0;
   
    private String remark10;
    private String remark20;
    private String remark30;
    private String remark40;
    private String remark50;
    private String remark60;
    private String remark70;
	private String remark80;
    private String remark90;
    private String remark100;
    private String remark110;
    private String remark120;
    private String remark130;
    private String remark140;
    private String remark150;
    private String remark160;
    private String remark170;
	private String remark180;
    private String remark190;
    private String remark200;
    private String remark210;
    private String remark220;
    private String remark230;
    private String remark240;
    private String remark250;
    private String remark260;
    private String remark270;
	private String remark280;
    private String remark290;
    private String remark300;
    private String assetsProducerBySupportId0;
    private String assetsProducerByProduceId0;
    private String usersByUsersId0;
    private String usersByChargeId0;
    
	private String id;
	private String remark11;
    private String remark21;
    private String remark31;
    private String remark41;
    private String remark51;
    private String remark61;
    private String remark71;
    private String remark81;
    private String remark91;
    private String remark101;
    private String remark111;
    private String remark121;
    private String remark131;
    private String remark141;
    private String remark151;
    private String remark161;
    private String remark171;
    private String remark181;
    private String remark191;
    private String remark201;
    private String remark211;

	private String actionURI;
	private AssetsBase assets;
	private AssetsType assetsType;
	private List<AssetsBase> assetsList;
	private DepartmentService departmentService;
	private String assetsId;
	private List supportlist;
	private List producelist;
	private List departmentlist;
	private List locationlist;
	private List userlist;
	public List getUserlist() {
		return userlist;
	}

	public void setUserlist(List userlist) {
		this.userlist = userlist;
	}

	private List assetsConfigList;
	private List assetsConfigInfoList;
	
	private AssetsProducer producer;
	private AssetsProducer supporter;
	private AssetsQurey assetsQurey;
	private List<AssetsType> assetTypeList;
	private AssetsType assetType;
	private UserService userService;
	private AssetsInfo info;
	private ProducerService producerService;
	private LocationService locationService;
	private int page;
	private Integer pageSize = 15;
	private PageBean pageBean;
	private CartogramInfo cartogramInfo;
	private CartogramInfo cartogramInfoprice;
	private String flag;
	private int pid = 0;
	private int type;
	private AssetsProducer support;
	private AssetsProducer produce;
	private Location location;
	private AssetsState assetsState;
	private List<Accessory> accessoryList;
	private int ids;
	private String codeIds;
	
	 public String getItsmType0() {
			return itsmType0;
		}

		public void setItsmType0(String itsmType0) {
			this.itsmType0 = itsmType0;
		}

		public String getBuildlocation0() {
			return buildlocation0;
		}

		public void setBuildlocation0(String buildlocation0) {
			this.buildlocation0 = buildlocation0;
		}
	
	 public String getCodeIds() {
		return codeIds;
	}

	public void setCodeIds(String codeIds) {
		this.codeIds = codeIds;
	}

	public CartogramInfo getCartogramInfoprice() {
		return cartogramInfoprice;
	}

	public void setCartogramInfoprice(CartogramInfo cartogramInfoprice) {
		this.cartogramInfoprice = cartogramInfoprice;
	}

	public int getIds() {
		return ids;
	}

	public void setIds(int ids) {
		this.ids = ids;
	}

	public List<Accessory> getAccessoryList() {
		return accessoryList;
	}

	public void setAccessoryList(List<Accessory> accessoryList) {
		this.accessoryList = accessoryList;
	}

	public AssetsState getAssetsState() {
		return assetsState;
	}

	public void setAssetsState(AssetsState assetsState) {
		this.assetsState = assetsState;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getContentType() {
		return contentType;
	}

	public void setContentType(String[] contentType) {
		this.contentType = contentType;
	}

	public String getAssetsState0() {
		return assetsState0;
	}

	public void setAssetsState0(String assetsState0) {
		this.assetsState0 = assetsState0;
	}
    public FileUpDownService getFileUpDownService() {
		return fileUpDownService;
	}

	public void setFileUpDownService(FileUpDownService fileUpDownService) {
		this.fileUpDownService = fileUpDownService;
	}

	public AssetsStateService getAssetsStateService() {
		return assetsStateService;
	}

	public void setAssetsStateService(AssetsStateService assetsStateService) {
		this.assetsStateService = assetsStateService;
	}

	public AssetsInfo getInfo() {
		return info;
	}

	public void setInfo(AssetsInfo info) {
		this.info = info;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AssetsConfigService getAssetsConfigService() {
		return assetsConfigService;
	}

	public void setAssetsConfigService(AssetsConfigService assetsConfigService) {
		this.assetsConfigService = assetsConfigService;
	}
   
    public String getCode0() {
		return code0;
	}

	public void setCode0(String code0) {
		this.code0 = code0;
	}

	public String getCodeId0() {
		return codeId0;
	}

	public void setCodeId0(String codeId0) {
		this.codeId0 = codeId0;
	}

	public String getAssetsProducerBySupportId0() {
		return assetsProducerBySupportId0;
	}

	public void setAssetsProducerBySupportId0(String assetsProducerBySupportId0) {
		this.assetsProducerBySupportId0 = assetsProducerBySupportId0;
	}

	public String getAssetsProducerByProduceId0() {
		return assetsProducerByProduceId0;
	}

	public void setAssetsProducerByProduceId0(String assetsProducerByProduceId0) {
		this.assetsProducerByProduceId0 = assetsProducerByProduceId0;
	}

	public String getLocation0() {
		return location0;
	}

	public void setLocation0(String location0) {
		this.location0 = location0;
	}

	public String getAssetsType0() {
		return assetsType0;
	}

	public void setAssetsType0(String assetsType0) {
		this.assetsType0 = assetsType0;
	}

	public String getModel0() {
		return model0;
	}

	public void setModel0(String model0) {
		this.model0 = model0;
	}

	public String getName0() {
		return name0;
	}

	public void setName0(String name0) {
		this.name0 = name0;
	}

	public String getInDate0() {
		return inDate0;
	}

	public void setInDate0(String inDate0) {
		this.inDate0 = inDate0;
	}

	public String getBuyDate0() {
		return buyDate0;
	}

	public void setBuyDate0(String buyDate0) {
		this.buyDate0 = buyDate0;
	}

	public String getExitfacotryDate0() {
		return exitfacotryDate0;
	}

	public void setExitfacotryDate0(String exitfacotryDate0) {
		this.exitfacotryDate0 = exitfacotryDate0;
	}

	public String getIp0() {
		return ip0;
	}

	public void setIp0(String ip0) {
		this.ip0 = ip0;
	}

	public String getUsersByUsersId0() {
		return usersByUsersId0;
	}

	public void setUsersByUsersId0(String usersByUsersId0) {
		this.usersByUsersId0 = usersByUsersId0;
	}

	public String getUsersByChargeId0() {
		return usersByChargeId0;
	}

	public void setUsersByChargeId0(String usersByChargeId0) {
		this.usersByChargeId0 = usersByChargeId0;
	}

	public String getQualityTime0() {
		return qualityTime0;
	}

	public void setQualityTime0(String qualityTime0) {
		this.qualityTime0 = qualityTime0;
	}

	public String getSystem0() {
		return system0;
	}

	public void setSystem0(String system0) {
		this.system0 = system0;
	}

	public String getPrice0() {
		return price0;
	}

	public void setPrice0(String price0) {
		this.price0 = price0;
	}

	public String getRemark10() {
		return remark10;
	}

	public void setRemark10(String remark10) {
		this.remark10 = remark10;
	}

	public String getRemark20() {
		return remark20;
	}

	public void setRemark20(String remark20) {
		this.remark20 = remark20;
	}

	public String getRemark30() {
		return remark30;
	}

	public void setRemark30(String remark30) {
		this.remark30 = remark30;
	}

	public String getRemark40() {
		return remark40;
	}

	public void setRemark40(String remark40) {
		this.remark40 = remark40;
	}

	public String getRemark50() {
		return remark50;
	}

	public void setRemark50(String remark50) {
		this.remark50 = remark50;
	}

	public String getRemark60() {
		return remark60;
	}

	public void setRemark60(String remark60) {
		this.remark60 = remark60;
	}

	public String getRemark70() {
		return remark70;
	}

	public void setRemark70(String remark70) {
		this.remark70 = remark70;
	}

	public String getRemark80() {
		return remark80;
	}

	public void setRemark80(String remark80) {
		this.remark80 = remark80;
	}

	public String getRemark90() {
		return remark90;
	}

	public void setRemark90(String remark90) {
		this.remark90 = remark90;
	}

	public String getRemark100() {
		return remark100;
	}

	public void setRemark100(String remark100) {
		this.remark100 = remark100;
	}


	public String getRemark11() {
		return remark11;
	}

	public void setRemark11(String remark11) {
		this.remark11 = remark11;
	}

	public String getRemark21() {
		return remark21;
	}

	public void setRemark21(String remark21) {
		this.remark21 = remark21;
	}

	public String getRemark31() {
		return remark31;
	}

	public void setRemark31(String remark31) {
		this.remark31 = remark31;
	}

	public String getRemark41() {
		return remark41;
	}

	public void setRemark41(String remark41) {
		this.remark41 = remark41;
	}

	public String getRemark51() {
		return remark51;
	}

	public void setRemark51(String remark51) {
		this.remark51 = remark51;
	}

	public String getRemark61() {
		return remark61;
	}

	public void setRemark61(String remark61) {
		this.remark61 = remark61;
	}

	public String getRemark71() {
		return remark71;
	}

	public void setRemark71(String remark71) {
		this.remark71 = remark71;
	}

	public String getRemark81() {
		return remark81;
	}

	public void setRemark81(String remark81) {
		this.remark81 = remark81;
	}

	public String getRemark91() {
		return remark91;
	}

	public void setRemark91(String remark91) {
		this.remark91 = remark91;
	}

	public String getRemark101() {
		return remark101;
	}

	public void setRemark101(String remark101) {
		this.remark101 = remark101;
	}

	public String getRemark111() {
		return remark111;
	}

	public void setRemark111(String remark111) {
		this.remark111 = remark111;
	}

	public String getRemark121() {
		return remark121;
	}

	public void setRemark121(String remark121) {
		this.remark121 = remark121;
	}

	public String getRemark131() {
		return remark131;
	}

	public void setRemark131(String remark131) {
		this.remark131 = remark131;
	}

	public String getRemark141() {
		return remark141;
	}

	public void setRemark141(String remark141) {
		this.remark141 = remark141;
	}

	public String getRemark151() {
		return remark151;
	}

	public void setRemark151(String remark151) {
		this.remark151 = remark151;
	}

	public String getRemark161() {
		return remark161;
	}

	public void setRemark161(String remark161) {
		this.remark161 = remark161;
	}

	public String getRemark171() {
		return remark171;
	}

	public void setRemark171(String remark171) {
		this.remark171 = remark171;
	}

	public String getRemark181() {
		return remark181;
	}

	public void setRemark181(String remark181) {
		this.remark181 = remark181;
	}

	public String getRemark191() {
		return remark191;
	}

	public void setRemark191(String remark191) {
		this.remark191 = remark191;
	}

	public String getRemark201() {
		return remark201;
	}

	public void setRemark201(String remark201) {
		this.remark201 = remark201;
	}

	public String getRemark211() {
		return remark211;
	}

	public void setRemark211(String remark211) {
		this.remark211 = remark211;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List getAssetsConfigInfoList() {
		return assetsConfigInfoList;
	}

	public void setAssetsConfigInfoList(List assetsConfigInfoList) {
		this.assetsConfigInfoList = assetsConfigInfoList;
	}

	public List getAssetsConfigList() {
		return assetsConfigList;
	}

	public void setAssetsConfigList(List assetsConfigList) {
		this.assetsConfigList = assetsConfigList;
	}

	public List getLocationlist() {
		return locationlist;
	}

	public void setLocationlist(List locationlist) {
		this.locationlist = locationlist;
	}

	public List getDepartmentlist() {
		return departmentlist;
	}

	public void setDepartmentlist(List departmentlist) {
		this.departmentlist = departmentlist;
	}

	public List getProducelist() {
		return producelist;
	}

	public void setProducelist(List producelist) {
		this.producelist = producelist;
	}

	public List getSupportlist() {
		return supportlist;
	}

	public void setSupportlist(List supportlist) {
		this.supportlist = supportlist;
	}

	public AssetsType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetsType assetType) {
		this.assetType = assetType;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public AssetsAction() {
	}

	public ProducerService getProducerService() {
		return producerService;
	}

	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}

	public AssetsProducer getSupport() {
		return support;
	}

	public void setSupport(AssetsProducer support) {
		this.support = support;
	}

	public AssetsProducer getProduce() {
		return produce;
	}

	public void setProduce(AssetsProducer produce) {
		this.produce = produce;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	private Department department;
	private Users user;
	private Users charge;

	public Users getCharge() {
		return charge;
	}

	public void setCharge(Users charge) {
		this.charge = charge;
	}
	public String listForInc() throws Exception{
		this.pageBean =assetsService.findAll(pageSize, page);
		supportlist=producerService.findAll();
		producelist=producerService.findAll();
		locationlist=locationService.findAll();
		statelist=assetsStateService.findAll();
		userlist=userService.findAllUser();
		return "success";
	}
	public String addInput() throws Exception {
		actionURI = "save";
		return "success";
	}

	public String cartogramqurey() throws Exception {

		if (assets!= null) {
			cartogramInfo = assetsService.getCartogramInfos(assets);
			cartogramInfoprice=assetsService.getCartogramInfoprice(assets);
		}
		return "success";
	}

	public String delete() throws Exception {
//		String url=ServletActionContext.getServletContext().getRealPath("/Upload")+ "/";
//		List list=fileUpDownService.getAccessorys("assets_base",Integer.parseInt(assetsId));
//		for(int i=0;i<list.size();i++){
//			Accessory accessory=(Accessory)list.get(i);
//			fileUpDownService.deleteByIdAndTableName(accessory.getTableId(),accessory.getTableName(),url);
//		}
//		List lists=assetsChangeService.findByassetsId(assetsId);
//		for(int i=0;i<lists.size();i++){
//			assetsChangeService.delete((AssetsChange)lists.get(i));
//		}
		if(message!=1){
//			assetsService.delAssetsByCode(assetsId);
			AssetsBase base=assetsService.findById(Integer.parseInt(assetsId));
			base.setDes(1);
			AssetsState assetsState=new AssetsState();
			assetsState.setId(0);
			base.setAssetsState(assetsState);
			
			List lists=assetsChangeService.findByassetsId(assetsId);
			for(int i=0;i<lists.size();i++){
				AssetsChange change=(AssetsChange)lists.get(i);
				AssetsState state=new AssetsState();
				state.setId(0);
				change.setAssetsState(state);
				assetsChangeService.update(change);
			}
			
			assetsService.updateAssetsByCode(base);
			return "list";
		}else{
			assetsService.delAssetsByCode(assetsId);
			return "success";
		}
	}
	
	public String getActionURI() {
		return actionURI;
	}

	public AssetsBase getAssets() {
		return assets;
	}

	public String getAssetsId() {
		return assetsId;
	}

	public List getAssetsList() {
		return assetsList;
	}

	public AssetsQurey getAssetsQurey() {
		return assetsQurey;
	}

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public AssetsType getAssetsType() {
		return assetsType;
	}

	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}

	public List<AssetsType> getAssetTypeList() {
		return assetTypeList;
	}

	public CartogramInfo getCartogramInfo() {
		return cartogramInfo;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public String getFlag() {
		return flag;
	}

	public Integer getPage() {
		return page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public int getPid() {
		return pid;
	}

	public AssetsProducer getProducer() {
		return producer;
	}

	public AssetsProducer getSupporter() {
		return supporter;
	}

	public int getType() {
		return type;
	}
	private List statelist;

	public List getStatelist() {
		return statelist;
	}

	public void setStatelist(List statelist) {
		this.statelist = statelist;
	}
	
	public String main() throws Exception {
		return "success";
	}
	
	public String alert() throws Exception{
		return "success";
	}
	
	public String batch() throws Exception{
		this.pageBean = assetsService.batchqueryByPage(assets, pageSize, page);
		return "success";
	}
	
	
	
	public String add() throws Exception{
		assetsType=assetsTypeService.findById(pid);
		String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
		assetsConfigList=assetsConfigService.findbysql(queryString);
		String queryString1="from AssetsConfig where assets_Type_id='"+pid+"' and flag='1' and ischoose='1'";
		assetsConfigInfoList=assetsConfigService.findbysql(queryString1);
		ActionContext.getContext().getSession().put("assetsConfigList", assetsConfigList);
		ActionContext.getContext().getSession().put("assetsConfigInfoList", assetsConfigInfoList);
		return "success";
	}

	public String quality() {
		Map session = ActionContext.getContext().getSession();
    	Users user=(Users) session.get("user");
    	int asset=0;
    	if(session.get("asset")!=null){
    		asset=Integer.parseInt(session.get("asset").toString());
    	}
		this.pageBean = assetsService.query(assets, pageSize, page, user, asset);
		return "success";
	}
	
    public String list() throws Exception {
    	Map session = ActionContext.getContext().getSession();
    	Users user=(Users) session.get("user");
    	int asset=0;
    	if(session.get("asset")!=null){
    		asset=Integer.parseInt(session.get("asset").toString());
    	}
    	this.pageBean=assetsService.query(assets, pageSize, page,user,asset);
    	return "success";
	}

	public String queryForInc() throws Exception{
		this.pageBean = assetsService.queryByPage(assets, pageSize, page);
		supportlist=producerService.findAll();
		producelist=producerService.findAll();
		locationlist=locationService.findAll();
		statelist=assetsStateService.findAll();
		return "success";
	}
	
	public String title() throws Exception{
		title="资产新增";
		return "success";
	}
	public String save() throws Exception {
		pid=type;
		AssetsBase assets1=new AssetsBase();
		assets1.setCodeId(codeId0);
		assets1.setName(name0);
		assets1.setModel(model0);
		if(Integer.parseInt(assetsType0)>0){
			AssetsType assetstype=assetsTypeService.findById(Integer.parseInt(assetsType0));
			assets1.setAssetsType(assetstype);
		}
		if(itsmType0!=""&&!itsmType0.equals("")&&itsmType0!=null&&!itsmType0.equals(null)){
			if(Integer.parseInt(itsmType0)>0){
				ItsmType itsmtype=itsmTypeService.findbyid(itsmType0);
				assets1.setItsmType(itsmtype);
			}
		}
		if(Integer.parseInt(assetsState0)>0){
			AssetsState state=assetsStateService.findbyId(Integer.parseInt(assetsState0));
			assets1.setAssetsState(state);
		}
		assets1.setIp(ip0);
		assets1.setMac(mac0);
		assets1.setDevicename(devicename0);
		assets1.setQualityTime(qualityTime0);
		if(buyDate0!=""&&!buyDate0.equals("")&&buyDate0!=null&&!buyDate0.equals(null)){
			assets1.setBuyDate(this.StringTime(buyDate0));
		}
		if(exitfacotryDate0!=""&&!exitfacotryDate0.equals("")&&exitfacotryDate0!=null&&!exitfacotryDate0.equals(null)){
			assets1.setExitfacotryDate(this.StringTime(exitfacotryDate0));
		}
		if(inDate0!=""&&!inDate0.equals("")&&inDate0!=null&&!inDate0.equals(null)){
			assets1.setInDate(this.StringTime(inDate0));
		}
		if(price0!=""&&!price0.equals("")&&price0!=null&&!price0.equals(null)){
			assets1.setPrice(Double.parseDouble(price0));
		}
		assets1.setSystem(system0);
		if(location0!=""&&!location0.equals("")&&location0!=null&&!location0.equals(null)){
			if(Integer.parseInt(location0)>0){
				Location location=locationService.findlocationById(Integer.parseInt(location0));
				assets1.setLocation(location);
			}
		}
		if(buildlocation0!=""&&!buildlocation0.equals("")&&buildlocation0!=null&&!buildlocation0.equals(null)){
			if(Integer.parseInt(buildlocation0)>0){
				Buildlocation buildlocation=buildlocationService.findbyid(buildlocation0);
				assets1.setBuildlocation(buildlocation);
			}
		}
		Object size=assetsService.getAllRows("select max(b.valueUnit) as maxvalue from AssetsBase as b").get(0);
		int max=0;
		if(size!=null){
			max=Integer.parseInt(size.toString());
		}
		assets1.setValueUnit(max+1);
		assets1.setDes(0);
		assets1.setRemark1(remark10);
		assets1.setRemark2(remark20);
		assets1.setRemark3(remark30);
		assets1.setRemark4(remark40);
		assets1.setRemark5(remark50);
		assets1.setRemark6(remark60);
		assets1.setRemark7(remark70);
		assets1.setRemark8(remark80);
		assets1.setRemark9(remark90);
		assets1.setRemark10(remark100);
		assets1.setRemark11(remark110);
		assets1.setRemark12(remark120);
		assets1.setRemark13(remark130);
		assets1.setRemark14(remark140);
		assets1.setRemark15(remark150);
		assets1.setRemark16(remark160);
		assets1.setRemark17(remark170);
		assets1.setRemark18(remark180);
		assets1.setRemark19(remark190);
		assets1.setRemark20(remark200);
		assets1.setRemark21(remark210);
		assets1.setRemark22(remark220);
		assets1.setRemark23(remark230);
		assets1.setRemark24(remark240);
		assets1.setRemark25(remark250);
		assets1.setRemark26(remark260);
		assets1.setRemark27(remark270);
		assets1.setRemark28(remark280);
		assets1.setRemark29(remark290);
		assets1.setRemark30(remark300);
		if(assetsProducerBySupportId0!=""&&!assetsProducerBySupportId0.equals("")&&assetsProducerBySupportId0!=null&&!assetsProducerBySupportId0.equals(null)){
			if(Integer.parseInt(assetsProducerBySupportId0)>0){
				AssetsProducer producer=producerService.findById(Integer.parseInt(assetsProducerBySupportId0));
				assets1.setAssetsProducerBySupportId(producer);
			}
		}
		if(assetsProducerByProduceId0!=""&&!assetsProducerByProduceId0.equals("")&&assetsProducerByProduceId0!=null&&!assetsProducerByProduceId0.equals(null)){
			if(Integer.parseInt(assetsProducerByProduceId0)>0){
				AssetsProducer producer=producerService.findById(Integer.parseInt(assetsProducerByProduceId0));
				assets1.setAssetsProducerByProduceId(producer);
			}
		}
		if(usersByUsersId0!=""&&!usersByUsersId0.equals("")){
			if(Integer.parseInt(usersByUsersId0)>0){
				Users users=userService.findUserById(Integer.parseInt(usersByUsersId0));
				assets1.setUsersByUsersId(users);
			}
		}
		
		if(Integer.parseInt(usersByChargeId0)>0){
			Users users=userService.findUserById(Integer.parseInt(usersByChargeId0));
			assets1.setUsersByChargeId(users);
		}
		assets1.setIshis(0);
		
		assetsService.save(assets1);
		Timestamp time = new Timestamp(new Date().getTime());
		if(this.getFile()!=null){
        	fileUpload(time,assets1.getCode());//调用上传文件方法
        }
		AssetsInfo info=new AssetsInfo();
		info.setAssetsBase(assets1);
		info.setRemark1(remark11);
		info.setRemark2(remark21);
		info.setRemark3(remark31);
		info.setRemark4(remark41);
		info.setRemark5(remark51);
		info.setRemark6(remark61);
		info.setRemark7(remark71);
		info.setRemark8(remark81);
		info.setRemark9(remark91);
		info.setRemark10(remark101);
		info.setRemark11(remark111);
		info.setRemark12(remark121);
		info.setRemark13(remark131);
		info.setRemark14(remark141);
		info.setRemark15(remark151);
		info.setRemark16(remark161);
		info.setRemark17(remark171);
		info.setRemark18(remark181);
		info.setRemark19(remark191);
		info.setRemark20(remark201);
		assetsService.assetsinfosave(info);
		return "patch";

	}
	private static final int BUFFER_SIZE = 16 * 1024 ;
	
	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public void fileUpload(java.sql.Timestamp timestamp,Integer code) { 
    	for(int i=0;i<this.getFile().length;i++){ 
    		String fileName= StringUtil.generateFileName(fileFileName[i]);
    		String url=ServletActionContext.getServletContext().getRealPath("/Upload")+ "/" +fileName;
			File dstFile = new File(url);
			copy(file[i], dstFile);
			
			Accessory accessory=new Accessory();
			accessory.setName(fileName);
			accessory.setTrueName(fileFileName[i]);
			accessory.setTableId(code);
			accessory.setTableName("assets_base");
			accessory.setUploadDate(timestamp);
			accessory.setUrl("/" +fileName);
			fileUpDownService.addUpFileInfo(accessory);			
    	}
	}
	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				//输入到缓冲流
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				int i = 0;
				while ((i=in.read(buffer)) != -1) {
					out.write(buffer,0,i);
					i=0;
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public void setAssets(AssetsBase assets) {
		this.assets = assets;
	}

	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}

	public void setAssetsList(List<AssetsBase> assetsList) {
		this.assetsList = assetsList;
	}

	public void setAssetsQurey(AssetsQurey assetsQurey) {
		this.assetsQurey = assetsQurey;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	public void setAssetTypeList(List<AssetsType> assetTypeList) {
		this.assetTypeList = assetTypeList;
	}

	public void setCartogramInfo(CartogramInfo cartogramInfo) {
		this.cartogramInfo = cartogramInfo;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setProducer(AssetsProducer producer) {
		this.producer = producer;
	}

	public void setSupporter(AssetsProducer supporter) {
		this.supporter = supporter;
	}

	public void setType(int type) {
		this.type = type;
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
	
	private List changelist;

	public List getChangelist() {
		return changelist;
	}

	public void setChangelist(List changelist) {
		this.changelist = changelist;
	}

	public String top() throws Exception {
		if (type == 1 || type == 0) {
			assetTypeList = assetsTypeService.findAll();
		} else {
			assetTypeList = assetsTypeService.findAll();
			for (AssetsType o : assetTypeList) {
				o.setId(o.getId() + 1);
				o.setPid(o.getPid() + 1);
			}
		}
		return "success";
	}

	public String update() throws Exception {
		AssetsBase assets1=assetsService.findById(Integer.parseInt(code0));
		assets1.setCode(Integer.parseInt(code0));
		assets1.setCodeId(codeId0);
		assets1.setName(name0);
		assets1.setModel(model0);
		if(Integer.parseInt(assetsType0)>0){
			AssetsType assetstype=assetsTypeService.findById(Integer.parseInt(assetsType0));
			assets1.setAssetsType(assetstype);
		}
		if(itsmType0!=null&&!itsmType0.equals("")&&Integer.parseInt(itsmType0)>0){
			ItsmType itsmtype=itsmTypeService.findbyid(itsmType0);
			assets1.setItsmType(itsmtype);
		}
		if(assetsState0!=null&&!assetsState0.equals("")&&Integer.parseInt(assetsState0)>0){
			AssetsState state=assetsStateService.findbyId(Integer.parseInt(assetsState0));
			assets1.setAssetsState(state);
		}
		assets1.setIp(ip0);
		assets1.setMac(mac0);
		assets1.setDevicename(devicename0);
		assets1.setQualityTime(qualityTime0);
		if(buyDate0!=""&&!buyDate0.equals("")&&buyDate0!=null&&!buyDate0.equals(null)){
			assets1.setBuyDate(this.StringTime(buyDate0));
		}
		if(exitfacotryDate0!=""&&!exitfacotryDate0.equals("")&&exitfacotryDate0!=null&&!exitfacotryDate0.equals(null)){
			assets1.setExitfacotryDate(this.StringTime(exitfacotryDate0));
		}
		if(inDate0!=""&&!inDate0.equals("")&&inDate0!=null&&!inDate0.equals(null)){
			assets1.setInDate(this.StringTime(inDate0));
		}
		if(price0!=""&&!price0.equals("")&&price0!=null&&!price0.equals(null)){
			assets1.setPrice(Double.parseDouble(price0));
		}
		assets1.setSystem(system0);
		if(location0!=null&&!location0.equals("")&&Integer.parseInt(location0)>0){
			Location location=locationService.findlocationById(Integer.parseInt(location0));
			assets1.setLocation(location);
		}
		if(buildlocation0!=null&&!buildlocation0.equals("")&&Integer.parseInt(buildlocation0)>0){
			Buildlocation buildlocation=buildlocationService.findbyid(buildlocation0);
			assets1.setBuildlocation(buildlocation);
		}
		assets1.setRemark1(remark10);
		assets1.setRemark2(remark20);
		assets1.setRemark3(remark30);
		assets1.setRemark4(remark40);
		assets1.setRemark5(remark50);
		assets1.setRemark6(remark60);
		assets1.setRemark7(remark70);
		assets1.setRemark8(remark80);
		assets1.setRemark9(remark90);
		assets1.setRemark10(remark100);
		assets1.setRemark11(remark110);
		assets1.setRemark12(remark120);
		assets1.setRemark13(remark130);
		assets1.setRemark14(remark140);
		assets1.setRemark15(remark150);
		assets1.setRemark16(remark160);
		assets1.setRemark17(remark170);
		assets1.setRemark18(remark180);
		assets1.setRemark19(remark190);
		assets1.setRemark20(remark200);
		assets1.setRemark21(remark210);
		assets1.setRemark22(remark220);
		assets1.setRemark23(remark230);
		assets1.setRemark24(remark240);
		assets1.setRemark25(remark250);
		assets1.setRemark26(remark260);
		assets1.setRemark27(remark270);
		assets1.setRemark28(remark280);
		assets1.setRemark29(remark290);
		assets1.setRemark30(remark300);
		if(assetsProducerBySupportId0!=null&&!assetsProducerBySupportId0.equals("")&&Integer.parseInt(assetsProducerBySupportId0)>0){
			AssetsProducer producer=producerService.findById(Integer.parseInt(assetsProducerBySupportId0));
			assets1.setAssetsProducerBySupportId(producer);
		}
		
		if(assetsProducerByProduceId0!=null&&!assetsProducerByProduceId0.equals("")&&Integer.parseInt(assetsProducerByProduceId0)>0){
			AssetsProducer producer=producerService.findById(Integer.parseInt(assetsProducerByProduceId0));
			assets1.setAssetsProducerByProduceId(producer);
		}
		if(usersByUsersId0!=""&&!usersByUsersId0.equals("")){
			if(Integer.parseInt(usersByUsersId0)>0){
				Users users=userService.findUserById(Integer.parseInt(usersByUsersId0));
				assets1.setUsersByUsersId(users);
			}
		}
		
		if(Integer.parseInt(usersByChargeId0)>0){
			Users users=userService.findUserById(Integer.parseInt(usersByChargeId0));
			assets1.setUsersByChargeId(users);
		}
		assetsService.AssetsBasesaveOrUpdate(assets1);
		Timestamp time = new Timestamp(new Date().getTime());
		if(this.getFile()!=null){
        	fileUpload(time,Integer.parseInt(code0));//调用上传文件方法
        }
		AssetsInfo info=new AssetsInfo();
		info.setId(Integer.parseInt(id));
		info.setAssetsBase(assets1);
		info.setRemark1(remark11);
		info.setRemark2(remark21);
		info.setRemark3(remark31);
		info.setRemark4(remark41);
		info.setRemark5(remark51);
		info.setRemark6(remark61);
		info.setRemark7(remark71);
		info.setRemark8(remark81);
		info.setRemark9(remark91);
		info.setRemark10(remark101);
		info.setRemark11(remark111);
		info.setRemark12(remark121);
		info.setRemark13(remark131);
		info.setRemark14(remark141);
		info.setRemark15(remark151);
		info.setRemark16(remark161);
		info.setRemark17(remark171);
		info.setRemark18(remark181);
		info.setRemark19(remark191);
		info.setRemark20(remark201);
		assetsService.AssetsInfosaveOrUpdate(info);
		return "list";
	}
	
	private List batchlist=new ArrayList();

	public List getBatchlist() {
		return batchlist;
	}

	public void setBatchlist(List batchlist) {
		this.batchlist = batchlist;
	}

	public String batchupdate() throws Exception {
		id = id.substring(0,id.length()-1); 
		String ids[]=id.split(",");
		for(int i=0;i<ids.length;i++){
			AssetsBase assets1=assetsService.findById(Integer.parseInt(ids[i]));
			List list=assetsService.sqllist("SELECT * FROM assets_base WHERE code_id='"+assets1.getCodeId()+"' AND state IS NOT  null");
			if(list.size()==0){
				assets1.setName(name0);
				assets1.setModel(model0);
				assets1.setAssetsType(assetsTypeService.findById(Integer.parseInt(assetsType0)));
				if(itsmType0!=""&&!itsmType0.equals("")&&itsmType0!=null&&!itsmType0.equals(null)){
					assets1.setItsmType(itsmTypeService.findbyid(itsmType0));
				}
				
				assets1.setAssetsState(assetsStateService.findbyId(Integer.parseInt(assetsState0)));
				assets1.setIp(ip0);
				assets1.setMac(mac0);
				assets1.setDevicename(devicename0);
				assets1.setQualityTime(qualityTime0);
				if(buyDate0!=""&&!buyDate0.equals("")&&buyDate0!=null&&!buyDate0.equals(null)){
					assets1.setBuyDate(this.StringTime(buyDate0));
				}
				if(exitfacotryDate0!=""&&!exitfacotryDate0.equals("")&&exitfacotryDate0!=null&&!exitfacotryDate0.equals(null)){
					assets1.setExitfacotryDate(this.StringTime(exitfacotryDate0));
				}
				if(price0!=""&&!price0.equals("")&&price0!=null&&!price0.equals(null)){
					assets1.setPrice(Double.parseDouble(price0));
				}
				assets1.setSystem(system0);
				if(location0!=""&&!location0.equals("")&&location0!=null&&!location0.equals(null)){
					Location location=locationService.findlocationById(Integer.parseInt(location0));
					assets1.setLocation(location);
				}
				if(buildlocation0!=""&&!buildlocation0.equals("")&&buildlocation0!=null&&!buildlocation0.equals(null)){
					Buildlocation build=buildlocationService.findbyid(buildlocation0);
					assets1.setBuildlocation(build);
				}
				Object size=assetsService.getAllRows("select max(b.valueUnit) as maxvalue from AssetsBase as b").get(0);
				int max=0;
				if(size!=null){
					max=Integer.parseInt(size.toString());
				}
				assets1.setValueUnit(max+1);
				assets1.setDes(0);
				assets1.setRemark1(remark10);
				assets1.setRemark2(remark20);
				assets1.setRemark3(remark30);
				assets1.setRemark4(remark40);
				assets1.setRemark5(remark50);
				assets1.setRemark6(remark60);
				assets1.setRemark7(remark70);
				assets1.setRemark8(remark80);
				assets1.setRemark9(remark90);
				assets1.setRemark10(remark100);
				assets1.setRemark11(remark110);
				assets1.setRemark12(remark120);
				assets1.setRemark13(remark130);
				assets1.setRemark14(remark140);
				assets1.setRemark15(remark150);
				assets1.setRemark16(remark160);
				assets1.setRemark17(remark170);
				assets1.setRemark18(remark180);
				assets1.setRemark19(remark190);
				assets1.setRemark20(remark200);
				assets1.setRemark21(remark210);
				assets1.setRemark22(remark220);
				assets1.setRemark23(remark230);
				assets1.setRemark24(remark240);
				assets1.setRemark25(remark250);
				assets1.setRemark26(remark260);
				assets1.setRemark27(remark270);
				assets1.setRemark28(remark280);
				assets1.setRemark29(remark290);
				assets1.setRemark30(remark300);
				if(assetsProducerBySupportId0!=""&&!assetsProducerBySupportId0.equals("")&&assetsProducerBySupportId0!=null&&!assetsProducerBySupportId0.equals(null)){
					AssetsProducer producer=producerService.findById(Integer.parseInt(assetsProducerBySupportId0));
					assets1.setAssetsProducerBySupportId(producer);
				}
				
				if(assetsProducerByProduceId0!=""&&!assetsProducerByProduceId0.equals("")&&assetsProducerByProduceId0!=null&&!assetsProducerByProduceId0.equals(null)){
					AssetsProducer producer=producerService.findById(Integer.parseInt(assetsProducerByProduceId0));
					assets1.setAssetsProducerByProduceId(producer);
				}
				if(usersByUsersId0!=null&&!usersByUsersId0.equals("")){
					if(Integer.parseInt(usersByUsersId0)>0){
						Users users=userService.findUserById(Integer.parseInt(usersByUsersId0));
					    assets1.setUsersByUsersId(users);
					}
				}
				if(usersByChargeId0!=null&&!usersByChargeId0.equals("")&&usersByChargeId0!="0"&&!usersByChargeId0.equals("0")){
					if(Integer.parseInt(usersByChargeId0)>0){
						Users users=userService.findUserById(Integer.parseInt(usersByChargeId0));
						assets1.setUsersByChargeId(users);
					}
				}
				assets1.setIshis(0);
				assetsService.AssetsBasesaveOrUpdate(assets1);
				Timestamp time = new Timestamp(new Date().getTime());
				if(this.getFile()!=null){
		        	fileUpload(time,Integer.parseInt(ids[i]));//调用上传文件方法
		        }
				AssetsInfo info=assetsService.findbyassetsid(ids[i]);
				info.setRemark1(remark11);
				info.setRemark2(remark21);
				info.setRemark3(remark31);
				info.setRemark4(remark41);
				info.setRemark5(remark51);
				info.setRemark6(remark61);
				info.setRemark7(remark71);
				info.setRemark8(remark81);
				info.setRemark9(remark91);
				info.setRemark10(remark101);
				info.setRemark11(remark111);
				info.setRemark12(remark121);
				info.setRemark13(remark131);
				info.setRemark14(remark141);
				info.setRemark15(remark151);
				info.setRemark16(remark161);
				info.setRemark17(remark171);
				info.setRemark18(remark181);
				info.setRemark19(remark191);
				info.setRemark20(remark201);
				assetsService.AssetsInfosaveOrUpdate(info);
			}else{
				batchlist.add(assets1);
			}
			
		}
		return "success";
	}

	public String updateInput() throws Exception {
		if(message!=1){
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
	 		return "success";
		}else{
			id = id.substring(0,id.length()-1); 
			codeIds="";
			List list=assetsService.sqllist("SELECT code_id FROM assets_base WHERE CODE IN ("+id+")");
			for(int i=0;i<list.size();i++){
				Map code_id=(Map)list.get(i);
				codeIds+=code_id.get("code_id").toString()+",";
			}
			codeIds=codeIds.substring(0,codeIds.length()-1);
			String []ids=id.split(",");
			if(ids.length==1){
				assets=assetsService.findById(Integer.parseInt(ids[0]));
			}
			assetsType=assetsTypeService.findById(type);
			String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
			assetsConfigList=assetsConfigService.findbysql(queryString);
			String queryString1="from AssetsConfig where assets_Type_id='"+type+"' and flag='1' and ischoose='1'";
			assetsConfigInfoList=assetsConfigService.findbysql(queryString1);
			ActionContext.getContext().getSession().put("assetsConfigList", assetsConfigList);
			ActionContext.getContext().getSession().put("assetsConfigInfoList", assetsConfigInfoList);
			return "batchupdate";
		}
		
	}
	
	public String deleteFile()throws Exception{
		String url=ServletActionContext.getServletContext().getRealPath("/Upload")+ "/";
		fileUpDownService.deleteById(ids,url);
		return "success";
	}
	public InputStream getDownloadFile() {
		Accessory accessory=fileUpDownService.getAccessoryById(ids);
		String path="/upload"+accessory.getUrl();
		return ServletActionContext.getServletContext().getResourceAsStream(path);
	}
	
	public String downloads() throws Exception {
		return SUCCESS;
	}
	private String fileName;
	
	public String getFileName()throws UnsupportedEncodingException{
		Accessory accessory=fileUpDownService.getAccessoryById(ids);
		fileName=new String(accessory.getTrueName().getBytes(),"ISO-8859-1"); 
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    private String[] select2;
    private String[] select4;
	public String[] getSelect4() {
		return select4;
	}

	public void setSelect4(String[] select4) {
		this.select4 = select4;
	}

	public String[] getSelect2() {
		return select2;
	}

	public void setSelect2(String[] select2) {
		this.select2 = select2;
	}

	public String excelDownString() throws Exception {
		Map session = ActionContext.getContext().getSession();
    	Users user=(Users) session.get("user");
    	int asset=0;
    	if(session.get("asset")!=null){
    		asset=Integer.parseInt(session.get("asset").toString());
    	}
		StringBuffer sb=new StringBuffer("select ");
		request = ServletActionContext.getRequest();
//		String sql="select codeId,model,count(*) from AssetsBase group by assetsType";
		String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
		assetsConfigList=assetsConfigService.findbysql(queryString);
		
		String []title=null;
		if(select2==null){
			if(select4==null){
				title=new String[assetsConfigList.size()];
				for(int j=0;j<assetsConfigList.size();j++){
					AssetsConfig config=(AssetsConfig)assetsConfigList.get(j);
					title[j]=config.getConfigName();
				}
				sb.append("assets");
			}else{
				title=new String[assetsConfigList.size()+1];
				for(int j=0;j<assetsConfigList.size();j++){
					AssetsConfig config=(AssetsConfig)assetsConfigList.get(j);
					title[j]=config.getConfigName();
				}
				title[assetsConfigList.size()]="统计数量";
				sb.append("assets,count(*) ");
			}
		}else{
			if(select4==null){
				title=new String[select2.length];
				for(int i=0;i<select2.length;i++){
					for(int j=0;j<assetsConfigList.size();j++){
						AssetsConfig config=(AssetsConfig)assetsConfigList.get(j);
						if(select2[i]==config.getConfigColumnName()||select2[i].equals(config.getConfigColumnName())){
							title[i]=config.getConfigName();
							continue;
						}
					}
				}
				sb.append("assets");
			}else{
				title=new String[select2.length+1];
				for(int i=0;i<select2.length;i++){
					for(int j=0;j<assetsConfigList.size();j++){
						AssetsConfig config=(AssetsConfig)assetsConfigList.get(j);
						if(select2[i]==config.getConfigColumnName()||select2[i].equals(config.getConfigColumnName())){
							title[i]=config.getConfigName();
							continue;
						}
					}
				}
				title[select2.length]="统计数量";
				sb.append("assets,count(*) ");
			}
		}
		sb.append(" from AssetsBase assets where assets.assetsState!='' AND assets.des='0' ");
		AssetsBase assetsexcel=(AssetsBase)request.getSession().getAttribute("assetsexcel");
		if(asset==0){
			if(assetsexcel.getName()!=null&&!assetsexcel.getName().equals("")){
				sb.append(" and assets.name like '%"+assetsexcel.getName()+"%' ");
			}
			if(assetsexcel.getCodeId()!=null&&!assetsexcel.getCodeId().equals("")){
				sb.append(" and assets.codeId like '%"+assetsexcel.getCodeId()+"%' ");
			}
			if(assetsexcel.getModel()!=null&&!assetsexcel.getModel().equals("")){
				sb.append(" and assets.model like '%"+assetsexcel.getModel()+"%' ");
			}
			if(assetsexcel.getAssetsProducerByProduceId()!=null){
				if(assetsexcel.getAssetsProducerByProduceId().getId()!=null&&assetsexcel.getAssetsProducerByProduceId().getId()>0){
					sb.append(" and assets.assetsProducerByProduceId.id='"+assetsexcel.getAssetsProducerByProduceId().getId()+"' ");
				}
			}
			if(assetsexcel.getAssetsProducerBySupportId()!=null){
				if(assetsexcel.getAssetsProducerBySupportId().getId()!=null&&assetsexcel.getAssetsProducerBySupportId().getId()>0){
					sb.append(" and assets.assetsProducerBySupportId.id='"+assetsexcel.getAssetsProducerBySupportId().getId()+"' ");
				}
			}
			if(assetsexcel.getAssetsType()!=null){
				if(assetsexcel.getAssetsType().getId()!=null&&assetsexcel.getAssetsType().getId()>0){
					sb.append(" and assets.assetsType.id='"+assetsexcel.getAssetsType().getId()+"' ");
				}
			}
			if(assetsexcel.getAssetsState()!=null){
				if(assetsexcel.getAssetsState().getId()!=null&&assetsexcel.getAssetsState().getId()>0){
					sb.append(" and assets.assetsState.id='"+assetsexcel.getAssetsState().getId()+"' ");
				}
			}
			if(assetsexcel.getLocation()!=null){
				if(assetsexcel.getLocation().getId()!=null&&assetsexcel.getLocation().getId()>0){
					sb.append(" and assets.location.id='"+assetsexcel.getLocation().getId()+"' ");
				}
			}
			
			sb.append(" and assets.usersByChargeId.id='"+user.getId()+"' ");
			
			if(assetsexcel.getUsersByChargeId()!=null){
				if(assetsexcel.getUsersByChargeId().getDepartment().getId()!=null&&assetsexcel.getUsersByChargeId().getDepartment().getId()>0){
					sb.append(" and assets.usersByChargeId.department.id='"+assetsexcel.getUsersByChargeId().getDepartment().getId()+"'");
				}
			}
			
			
			if(assetsexcel.getUsersByUsersId()!=null){
				if(assetsexcel.getUsersByUsersId().getId()!=null&&assetsexcel.getUsersByUsersId().getId()>0){
					sb.append(" and assets.usersByUsersId.id='"+assetsexcel.getUsersByUsersId().getId()+"' ");
				}
			}
			
			if(assetsexcel.getUsersByUsersId()!=null){
				if(assetsexcel.getUsersByUsersId().getDepartment().getId()!=null&&assetsexcel.getUsersByUsersId().getDepartment().getId()>0){
					sb.append(" and assets.usersByUsersId.department.id='"+assetsexcel.getUsersByUsersId().getDepartment().getId()+"' ");
				}
			}
			
			
			if(assetsexcel.getBuildlocation()!=null){
    			if(assetsexcel.getBuildlocation().getId()!=null&&assetsexcel.getBuildlocation().getId()>0){
    				sb.append(" assets.and buildlocation.id='"+assetsexcel.getBuildlocation().getId()+"'");
    			}
    		}
			if(assetsexcel.getBuyDate()!=null){
				sb.append(" and assets.buyDate='"+assetsexcel.getBuyDate()+"' ");
			}
	        if(assetsexcel.getExitfacotryDate()!=null){
	        	sb.append(" and assets.exitfacotryDate='"+assetsexcel.getExitfacotryDate()+"' ");
			}
	        if(assetsexcel.getInDate()!=null){
	        	sb.append(" and assets.inDate='"+assetsexcel.getInDate()+"' ");
	        }
    		
    	}else if(asset==1){
    		if(assetsexcel.getName()!=null&&!assetsexcel.getName().equals("")){
    			sb.append(" and assets.name like '%"+assetsexcel.getName()+"%' ");
    		}
    		if(assetsexcel.getCodeId()!=null&&!assetsexcel.getCodeId().equals("")){
    			sb.append(" and assets.codeId like '%"+assetsexcel.getCodeId()+"%' ");
    		}
    		if(assetsexcel.getModel()!=null&&!assetsexcel.getModel().equals("")){
    			sb.append(" and assets.model like '%"+assetsexcel.getModel()+"%' ");
    		}
    		if(assetsexcel.getAssetsProducerByProduceId()!=null){
    			if(assetsexcel.getAssetsProducerByProduceId().getId()!=null&&assetsexcel.getAssetsProducerByProduceId().getId()>0){
    				sb.append(" and assets.assetsProducerByProduceId.id='"+assetsexcel.getAssetsProducerByProduceId().getId()+"' ");
    			}
    		}
    		if(assetsexcel.getAssetsProducerBySupportId()!=null){
    			if(assetsexcel.getAssetsProducerBySupportId().getId()!=null&&assetsexcel.getAssetsProducerBySupportId().getId()>0){
    				sb.append(" and assets.assetsProducerBySupportId.id='"+assetsexcel.getAssetsProducerBySupportId().getId()+"' ");
    			}
    		}
    		if(assetsexcel.getAssetsType()!=null){
    			if(assetsexcel.getAssetsType().getId()!=null&&assetsexcel.getAssetsType().getId()>0){
    				sb.append(" and assets.assetsType.id='"+assetsexcel.getAssetsType().getId()+"' ");
    			}
    		}
    		if(assetsexcel.getAssetsState()!=null){
    			if(assetsexcel.getAssetsState().getId()!=null&&assetsexcel.getAssetsState().getId()>0){
    				sb.append(" and assets.assetsState.id='"+assetsexcel.getAssetsState().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getLocation()!=null){
    			if(assetsexcel.getLocation().getId()!=null&&assetsexcel.getLocation().getId()>0){
    				sb.append(" and assets.location.id='"+assetsexcel.getLocation().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getUsersByChargeId()!=null){
    			if(assetsexcel.getUsersByChargeId().getId()!=null&&assetsexcel.getUsersByChargeId().getId()>0){
    				sb.append(" and assets.usersByChargeId.id='"+assetsexcel.getUsersByChargeId().getId()+"' ");
    			}
    		}
    		sb.append(" and assets.usersByChargeId.department.id=='"+user.getDepartment().getId()+"' ");
    		if(assetsexcel.getUsersByUsersId()!=null){
    			if(assetsexcel.getUsersByUsersId().getId()!=null&&assetsexcel.getUsersByUsersId().getId()>0){
    				sb.append(" and assets.usersByUsersId.id='"+assetsexcel.getUsersByUsersId().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getUsersByUsersId()!=null){
				if(assetsexcel.getUsersByUsersId().getDepartment().getId()!=null&&assetsexcel.getUsersByUsersId().getDepartment().getId()>0){
					sb.append(" and assets.usersByUsersId.department.id='"+assetsexcel.getUsersByUsersId().getDepartment().getId()+"' ");
				}
			}
    		
    		if(assetsexcel.getBuildlocation()!=null){
    			if(assetsexcel.getBuildlocation().getId()!=null&&assetsexcel.getBuildlocation().getId()>0){
    				sb.append(" and assets.buildlocation.id='"+assetsexcel.getBuildlocation().getId()+"'");
    			}
    		}
    		if(assetsexcel.getBuyDate()!=null){
    			sb.append(" and assets.buyDate='"+assetsexcel.getBuyDate()+"' ");
    		}
            if(assetsexcel.getExitfacotryDate()!=null){
            	sb.append(" and assets.exitfacotryDate='"+assetsexcel.getExitfacotryDate()+"' ");
    		}
            if(assetsexcel.getInDate()!=null){
            	sb.append(" and assets.inDate='"+assetsexcel.getInDate()+"' ");
            }
    	}else if(asset==2){
    		if(assetsexcel.getName()!=null&&!assetsexcel.getName().equals("")){
    			sb.append(" and assets.name like '%"+assetsexcel.getName()+"%' ");
    		}
    		if(assetsexcel.getCodeId()!=null&&!assetsexcel.getCodeId().equals("")){
    			sb.append(" and assets.codeId like '%"+assetsexcel.getCodeId()+"%' ");
    		}
    		if(assetsexcel.getModel()!=null&&!assetsexcel.getModel().equals("")){
    			sb.append(" and assets.model like '%"+assetsexcel.getModel()+"%' ");
    		}
    		if(assetsexcel.getAssetsProducerByProduceId()!=null){
    			if(assetsexcel.getAssetsProducerByProduceId().getId()!=null&&assetsexcel.getAssetsProducerByProduceId().getId()>0){
    				sb.append(" and assets.assetsProducerByProduceId.id='"+assetsexcel.getAssetsProducerByProduceId().getId()+"' ");
    			}
    		}
    		if(assetsexcel.getAssetsProducerBySupportId()!=null){
    			if(assetsexcel.getAssetsProducerBySupportId().getId()!=null&&assetsexcel.getAssetsProducerBySupportId().getId()>0){
    				sb.append(" and assets.assetsProducerBySupportId.id='"+assetsexcel.getAssetsProducerBySupportId().getId()+"' ");
    			}
    		}
    		if(assetsexcel.getAssetsType()!=null){
    			if(assetsexcel.getAssetsType().getId()!=null&&assetsexcel.getAssetsType().getId()>0){
    				sb.append(" and assets.assetsType.id='"+assetsexcel.getAssetsType().getId()+"' ");
    			}
    		}
    		if(assetsexcel.getAssetsState()!=null){
    			if(assetsexcel.getAssetsState().getId()!=null&&assetsexcel.getAssetsState().getId()>0){
    				sb.append(" and assets.assetsState.id='"+assetsexcel.getAssetsState().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getLocation()!=null){
    			if(assetsexcel.getLocation().getId()!=null&&assetsexcel.getLocation().getId()>0){
    				sb.append(" and assets.location.id='"+assetsexcel.getLocation().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getUsersByChargeId()!=null){
    			if(assetsexcel.getUsersByChargeId().getId()!=null&&assetsexcel.getUsersByChargeId().getId()>0){
    				sb.append(" and assets.usersByChargeId.id='"+assetsexcel.getUsersByChargeId().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getUsersByChargeId()!=null){
				if(assetsexcel.getUsersByChargeId().getDepartment().getId()!=null&&assetsexcel.getUsersByChargeId().getDepartment().getId()>0){
					sb.append(" and assets.usersByChargeId.department.id='"+assetsexcel.getUsersByChargeId().getDepartment().getId()+"'");
				}
			}
    		
    		if(assetsexcel.getUsersByUsersId()!=null){
    			if(assetsexcel.getUsersByUsersId().getId()!=null&&assetsexcel.getUsersByUsersId().getId()>0){
    				sb.append(" and assets.usersByUsersId.id='"+assetsexcel.getUsersByUsersId().getId()+"' ");
    			}
    		}
    		
    		if(assetsexcel.getUsersByUsersId()!=null){
				if(assetsexcel.getUsersByUsersId().getDepartment().getId()!=null&&assetsexcel.getUsersByUsersId().getDepartment().getId()>0){
					sb.append(" and assets.usersByUsersId.department.id='"+assetsexcel.getUsersByUsersId().getDepartment().getId()+"' ");
				}
			}
    		
    		if(assetsexcel.getBuildlocation()!=null){
    			if(assetsexcel.getBuildlocation().getId()!=null&&assetsexcel.getBuildlocation().getId()>0){
    				sb.append(" and assets.buildlocation.id='"+assetsexcel.getBuildlocation().getId()+"'");
    			}
    		}
    		if(assetsexcel.getBuyDate()!=null){
    			sb.append(" and assets.buyDate='"+assetsexcel.getBuyDate()+"' ");
    		}
            if(assetsexcel.getExitfacotryDate()!=null){
            	sb.append(" and assets.exitfacotryDate='"+assetsexcel.getExitfacotryDate()+"' ");
    		}
            if(assetsexcel.getInDate()!=null){
            	sb.append(" and assets.inDate='"+assetsexcel.getInDate()+"' ");
            }
    	}
		
		if(select4!=null){
			sb.append(" group by ");
			for(int i=0;i<select4.length;i++){
				sb.append("assets."+select4[i]);
				if(i!=select4.length-1){
					sb.append(",");
				}
			}
		}
		sb.append(" order by assets.valueUnit asc");
		List list=assetsService.getAllRows(sb.toString());
		System.out.println(sb.toString());
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		List data = new ArrayList();
		Object []datas=null;
		if(select2==null){
			if(select4==null){
				for(int i=0;i<list.size();i++){
					AssetsBase base=(AssetsBase)list.get(i);
					datas=new Object[assetsConfigList.size()];
					for(int j=0;j<assetsConfigList.size();j++){
						AssetsConfig config=(AssetsConfig)assetsConfigList.get(j);
						if(config.getConfigColumnName()=="codeId"||config.getConfigColumnName().equals("codeId")){
							datas[j]=base.getCodeId()!=null?base.getCodeId():"";
						}else if(config.getConfigColumnName()=="name"||config.getConfigColumnName().equals("name")){
							datas[j]=base.getName()!=null?base.getName():"";
						}else if(config.getConfigColumnName()=="model"||config.getConfigColumnName().equals("model")){
							datas[j]=base.getModel()!=null?base.getModel():"";
						}else if(config.getConfigColumnName()=="assetsType"||config.getConfigColumnName().equals("assetsType")){
							datas[j]=base.getAssetsType()!=null?base.getAssetsType().getName():"";
						}else if(config.getConfigColumnName()=="itsmType"||config.getConfigColumnName().equals("itsmType")){
							datas[j]=base.getItsmType()!=null?base.getItsmType().getName():"";
						}else if(config.getConfigColumnName()=="assetsState"||config.getConfigColumnName().equals("assetsState")){
							datas[j]=base.getAssetsState()!=null?base.getAssetsState().getName():"";
						}else if(config.getConfigColumnName()=="qualityTime"||config.getConfigColumnName().equals("qualityTime")){
							datas[j]=base.getQualityTime()!=null?base.getQualityTime():"";
						}else if(config.getConfigColumnName()=="price"||config.getConfigColumnName().equals("price")){
							datas[j]=base.getPrice()!=null?base.getPrice():"";
						}else if(config.getConfigColumnName()=="buyDate"||config.getConfigColumnName().equals("buyDate")){
							datas[j]=base.getBuyDate()!=null?this.time(base.getBuyDate()):"";
						}else if(config.getConfigColumnName()=="exitfacotryDate"||config.getConfigColumnName().equals("exitfacotryDate")){
							datas[j]=base.getExitfacotryDate()!=null?this.time(base.getExitfacotryDate()):"";
						}else if(config.getConfigColumnName()=="inDate"||config.getConfigColumnName().equals("inDate")){
							datas[j]=base.getInDate()!=null?this.time(base.getInDate()):"";
						}else if(config.getConfigColumnName()=="location"||config.getConfigColumnName().equals("location")){
							datas[j]=base.getLocation()!=null?base.getLocation().getName():"";
						}else if(config.getConfigColumnName()=="buildlocation"||config.getConfigColumnName().equals("buildlocation")){
							datas[j]=base.getBuildlocation()!=null?base.getBuildlocation().getAllname():"";
						}else if(config.getConfigColumnName()=="system"||config.getConfigColumnName().equals("system")){
							datas[j]=base.getSystem()!=null?base.getSystem():"";
						}else if(config.getConfigColumnName()=="ip"||config.getConfigColumnName().equals("ip")){
							datas[j]=base.getIp()!=null?base.getIp():"";
						}else if(config.getConfigColumnName()=="mac"||config.getConfigColumnName().equals("mac")){
							datas[j]=base.getMac()!=null?base.getMac():"";
						}else if(config.getConfigColumnName()=="devicename"||config.getConfigColumnName().equals("devicename")){
							datas[j]=base.getDevicename()!=null?base.getDevicename():"";
						}else if(config.getConfigColumnName()=="remark1"||config.getConfigColumnName().equals("remark1")){
							datas[j]=base.getRemark1()!=null?base.getRemark1():"";
						}else if(config.getConfigColumnName()=="remark2"||config.getConfigColumnName().equals("remark2")){
							datas[j]=base.getRemark2()!=null?base.getRemark2():"";
						}else if(config.getConfigColumnName()=="remark3"||config.getConfigColumnName().equals("remark3")){
							datas[j]=base.getRemark3()!=null?base.getRemark3():"";
						}else if(config.getConfigColumnName()=="remark4"||config.getConfigColumnName().equals("remark4")){
							datas[j]=base.getRemark4()!=null?base.getRemark4():"";
						}else if(config.getConfigColumnName()=="remark5"||config.getConfigColumnName().equals("remark5")){
							datas[j]=base.getRemark5()!=null?base.getRemark5():"";
						}else if(config.getConfigColumnName()=="remark6"||config.getConfigColumnName().equals("remark6")){
							datas[j]=base.getRemark6()!=null?base.getRemark6():"";
						}else if(config.getConfigColumnName()=="remark7"||config.getConfigColumnName().equals("remark7")){
							datas[j]=base.getRemark7()!=null?base.getRemark7():"";
						}else if(config.getConfigColumnName()=="remark8"||config.getConfigColumnName().equals("remark8")){
							datas[j]=base.getRemark8()!=null?base.getRemark8():"";
						}else if(config.getConfigColumnName()=="remark9"||config.getConfigColumnName().equals("remark9")){
							datas[j]=base.getRemark9()!=null?base.getRemark9():"";
						}else if(config.getConfigColumnName()=="remark10"||config.getConfigColumnName().equals("remark10")){
							datas[j]=base.getRemark10()!=null?base.getRemark10():"";
						}else if(config.getConfigColumnName()=="remark11"||config.getConfigColumnName().equals("remark11")){
							datas[j]=base.getRemark11()!=null?base.getRemark11():"";
						}else if(config.getConfigColumnName()=="remark12"||config.getConfigColumnName().equals("remark12")){
							datas[j]=base.getRemark12()!=null?base.getRemark12():"";
						}else if(config.getConfigColumnName()=="remark13"||config.getConfigColumnName().equals("remark13")){
							datas[j]=base.getRemark13()!=null?base.getRemark13():"";
						}else if(config.getConfigColumnName()=="remark14"||config.getConfigColumnName().equals("remark14")){
							datas[j]=base.getRemark14()!=null?base.getRemark14():"";
						}else if(config.getConfigColumnName()=="remark15"||config.getConfigColumnName().equals("remark15")){
							datas[j]=base.getRemark15()!=null?base.getRemark15():"";
						}else if(config.getConfigColumnName()=="remark16"||config.getConfigColumnName().equals("remark16")){
							datas[j]=base.getRemark16()!=null?base.getRemark16():"";
						}else if(config.getConfigColumnName()=="remark17"||config.getConfigColumnName().equals("remark17")){
							datas[j]=base.getRemark17()!=null?base.getRemark17():"";
						}else if(config.getConfigColumnName()=="remark18"||config.getConfigColumnName().equals("remark18")){
							datas[j]=base.getRemark18()!=null?base.getRemark18():"";
						}else if(config.getConfigColumnName()=="remark19"||config.getConfigColumnName().equals("remark19")){
							datas[j]=base.getRemark19()!=null?base.getRemark19():"";
						}else if(config.getConfigColumnName()=="remark20"||config.getConfigColumnName().equals("remark20")){
							datas[j]=base.getRemark20()!=null?base.getRemark20():"";
						}else if(config.getConfigColumnName()=="remark21"||config.getConfigColumnName().equals("remark21")){
							datas[j]=base.getRemark21()!=null?base.getRemark21():"";;
						}else if(config.getConfigColumnName()=="remark22"||config.getConfigColumnName().equals("remark22")){
							datas[j]=base.getRemark22()!=null?base.getRemark22():"";
						}else if(config.getConfigColumnName()=="remark23"||config.getConfigColumnName().equals("remark23")){
							datas[j]=base.getRemark23()!=null?base.getRemark23():"";
						}else if(config.getConfigColumnName()=="remark24"||config.getConfigColumnName().equals("remark24")){
							datas[j]=base.getRemark24()!=null?base.getRemark24():"";
						}else if(config.getConfigColumnName()=="remark25"||config.getConfigColumnName().equals("remark25")){
							datas[j]=base.getRemark25()!=null?base.getRemark25():"";
						}else if(config.getConfigColumnName()=="remark26"||config.getConfigColumnName().equals("remark26")){
							datas[j]=base.getRemark26()!=null?base.getRemark26():"";
						}else if(config.getConfigColumnName()=="remark27"||config.getConfigColumnName().equals("remark27")){
							datas[j]=base.getRemark27()!=null?base.getRemark27():"";
						}else if(config.getConfigColumnName()=="remark28"||config.getConfigColumnName().equals("remark28")){
							datas[j]=base.getRemark28()!=null?base.getRemark28():"";
						}else if(config.getConfigColumnName()=="remark29"||config.getConfigColumnName().equals("remark29")){
							datas[j]=base.getRemark29()!=null?base.getRemark29():"";
						}else if(config.getConfigColumnName()=="remark30"||config.getConfigColumnName().equals("remark30")){
							datas[j]=base.getRemark30()!=null?base.getRemark30():"";
						}else if(config.getConfigColumnName()=="assetsProducerBySupportId"||config.getConfigColumnName().equals("assetsProducerBySupportId")){
							datas[j]=base.getAssetsProducerBySupportId()!=null?base.getAssetsProducerBySupportId().getName():"";
						}else if(config.getConfigColumnName()=="assetsProducerByProduceId"||config.getConfigColumnName().equals("assetsProducerByProduceId")){
							datas[j]=base.getAssetsProducerByProduceId()!=null?base.getAssetsProducerByProduceId().getName():"";
						}else if(config.getConfigColumnName()=="usersByUsersId"||config.getConfigColumnName().equals("usersByUsersId")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getTruename():"";
						}else if(config.getConfigColumnName()=="usersByUsersIddepartment"||config.getConfigColumnName().equals("usersByUsersIddepartment")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getDepartment().getName():"";
						}else if(config.getConfigColumnName()=="usersByChargeId"||config.getConfigColumnName().equals("usersByChargeId")){
							datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getTruename():"";
						}else if(config.getConfigColumnName()=="usersByChargeIddepartment"||config.getConfigColumnName().equals("usersByChargeIddepartment")){
							datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getDepartment().getName():"";
						}
					}
					data.add(datas);
				}
			}else{
				for(int i=0;i<list.size();i++){
					Object obj[]=(Object[])list.get(i);
					AssetsBase base=(AssetsBase)obj[0];
					datas=new Object[assetsConfigList.size()+1];
					for(int j=0;j<assetsConfigList.size();j++){
						AssetsConfig config=(AssetsConfig)assetsConfigList.get(j);
						if(config.getConfigColumnName()=="codeId"||config.getConfigColumnName().equals("codeId")){
							datas[j]=base.getCodeId()!=null?base.getCodeId():"";
						}else if(config.getConfigColumnName()=="name"||config.getConfigColumnName().equals("name")){
							datas[j]=base.getName()!=null?base.getName():"";
						}else if(config.getConfigColumnName()=="model"||config.getConfigColumnName().equals("model")){
							datas[j]=base.getModel()!=null?base.getModel():"";
						}else if(config.getConfigColumnName()=="assetsType"||config.getConfigColumnName().equals("assetsType")){
							datas[j]=base.getAssetsType()!=null?base.getAssetsType().getName():"";
						}else if(config.getConfigColumnName()=="itsmType"||config.getConfigColumnName().equals("itsmType")){
							datas[j]=base.getItsmType()!=null?base.getItsmType().getName():"";
						}else if(config.getConfigColumnName()=="assetsState"||config.getConfigColumnName().equals("assetsState")){
							datas[j]=base.getAssetsState()!=null?base.getAssetsState().getName():"";
						}else if(config.getConfigColumnName()=="qualityTime"||config.getConfigColumnName().equals("qualityTime")){
							datas[j]=base.getQualityTime()!=null?base.getQualityTime():"";
						}else if(config.getConfigColumnName()=="price"||config.getConfigColumnName().equals("price")){
							datas[j]=base.getPrice()!=null?base.getPrice():"";
						}else if(config.getConfigColumnName()=="buyDate"||config.getConfigColumnName().equals("buyDate")){
							datas[j]=base.getBuyDate()!=null?this.time(base.getBuyDate()):"";
						}else if(config.getConfigColumnName()=="exitfacotryDate"||config.getConfigColumnName().equals("exitfacotryDate")){
							datas[j]=base.getExitfacotryDate()!=null?this.time(base.getExitfacotryDate()):"";
						}else if(config.getConfigColumnName()=="inDate"||config.getConfigColumnName().equals("inDate")){
							datas[j]=base.getInDate()!=null?this.time(base.getInDate()):"";
						}else if(config.getConfigColumnName()=="location"||config.getConfigColumnName().equals("location")){
							datas[j]=base.getLocation()!=null?base.getLocation().getName():"";
						}else if(config.getConfigColumnName()=="buildlocation"||config.getConfigColumnName().equals("buildlocation")){
							datas[j]=base.getBuildlocation()!=null?base.getBuildlocation().getAllname():"";
						}else if(config.getConfigColumnName()=="system"||config.getConfigColumnName().equals("system")){
							datas[j]=base.getSystem()!=null?base.getSystem():"";
						}else if(config.getConfigColumnName()=="ip"||config.getConfigColumnName().equals("ip")){
							datas[j]=base.getIp()!=null?base.getIp():"";
						}else if(config.getConfigColumnName()=="mac"||config.getConfigColumnName().equals("mac")){
							datas[j]=base.getMac()!=null?base.getMac():"";
						}else if(config.getConfigColumnName()=="devicename"||config.getConfigColumnName().equals("devicename")){
							datas[j]=base.getDevicename()!=null?base.getDevicename():"";
						}else if(config.getConfigColumnName()=="remark1"||config.getConfigColumnName().equals("remark1")){
							datas[j]=base.getRemark1()!=null?base.getRemark1():"";
						}else if(config.getConfigColumnName()=="remark2"||config.getConfigColumnName().equals("remark2")){
							datas[j]=base.getRemark2()!=null?base.getRemark2():"";
						}else if(config.getConfigColumnName()=="remark3"||config.getConfigColumnName().equals("remark3")){
							datas[j]=base.getRemark3()!=null?base.getRemark3():"";
						}else if(config.getConfigColumnName()=="remark4"||config.getConfigColumnName().equals("remark4")){
							datas[j]=base.getRemark4()!=null?base.getRemark4():"";
						}else if(config.getConfigColumnName()=="remark5"||config.getConfigColumnName().equals("remark5")){
							datas[j]=base.getRemark5()!=null?base.getRemark5():"";
						}else if(config.getConfigColumnName()=="remark6"||config.getConfigColumnName().equals("remark6")){
							datas[j]=base.getRemark6()!=null?base.getRemark6():"";
						}else if(config.getConfigColumnName()=="remark7"||config.getConfigColumnName().equals("remark7")){
							datas[j]=base.getRemark7()!=null?base.getRemark7():"";
						}else if(config.getConfigColumnName()=="remark8"||config.getConfigColumnName().equals("remark8")){
							datas[j]=base.getRemark8()!=null?base.getRemark8():"";
						}else if(config.getConfigColumnName()=="remark9"||config.getConfigColumnName().equals("remark9")){
							datas[j]=base.getRemark9()!=null?base.getRemark9():"";
						}else if(config.getConfigColumnName()=="remark10"||config.getConfigColumnName().equals("remark10")){
							datas[j]=base.getRemark10()!=null?base.getRemark10():"";
						}else if(config.getConfigColumnName()=="remark11"||config.getConfigColumnName().equals("remark11")){
							datas[j]=base.getRemark11()!=null?base.getRemark11():"";
						}else if(config.getConfigColumnName()=="remark12"||config.getConfigColumnName().equals("remark12")){
							datas[j]=base.getRemark12()!=null?base.getRemark12():"";
						}else if(config.getConfigColumnName()=="remark13"||config.getConfigColumnName().equals("remark13")){
							datas[j]=base.getRemark13()!=null?base.getRemark13():"";
						}else if(config.getConfigColumnName()=="remark14"||config.getConfigColumnName().equals("remark14")){
							datas[j]=base.getRemark14()!=null?base.getRemark14():"";
						}else if(config.getConfigColumnName()=="remark15"||config.getConfigColumnName().equals("remark15")){
							datas[j]=base.getRemark15()!=null?base.getRemark15():"";
						}else if(config.getConfigColumnName()=="remark16"||config.getConfigColumnName().equals("remark16")){
							datas[j]=base.getRemark16()!=null?base.getRemark16():"";
						}else if(config.getConfigColumnName()=="remark17"||config.getConfigColumnName().equals("remark17")){
							datas[j]=base.getRemark17()!=null?base.getRemark17():"";
						}else if(config.getConfigColumnName()=="remark18"||config.getConfigColumnName().equals("remark18")){
							datas[j]=base.getRemark18()!=null?base.getRemark18():"";
						}else if(config.getConfigColumnName()=="remark19"||config.getConfigColumnName().equals("remark19")){
							datas[j]=base.getRemark19()!=null?base.getRemark19():"";
						}else if(config.getConfigColumnName()=="remark20"||config.getConfigColumnName().equals("remark20")){
							datas[j]=base.getRemark20()!=null?base.getRemark20():"";
						}else if(config.getConfigColumnName()=="remark21"||config.getConfigColumnName().equals("remark21")){
							datas[j]=base.getRemark21()!=null?base.getRemark21():"";;
						}else if(config.getConfigColumnName()=="remark22"||config.getConfigColumnName().equals("remark22")){
							datas[j]=base.getRemark22()!=null?base.getRemark22():"";
						}else if(config.getConfigColumnName()=="remark23"||config.getConfigColumnName().equals("remark23")){
							datas[j]=base.getRemark23()!=null?base.getRemark23():"";
						}else if(config.getConfigColumnName()=="remark24"||config.getConfigColumnName().equals("remark24")){
							datas[j]=base.getRemark24()!=null?base.getRemark24():"";
						}else if(config.getConfigColumnName()=="remark25"||config.getConfigColumnName().equals("remark25")){
							datas[j]=base.getRemark25()!=null?base.getRemark25():"";
						}else if(config.getConfigColumnName()=="remark26"||config.getConfigColumnName().equals("remark26")){
							datas[j]=base.getRemark26()!=null?base.getRemark26():"";
						}else if(config.getConfigColumnName()=="remark27"||config.getConfigColumnName().equals("remark27")){
							datas[j]=base.getRemark27()!=null?base.getRemark27():"";
						}else if(config.getConfigColumnName()=="remark28"||config.getConfigColumnName().equals("remark28")){
							datas[j]=base.getRemark28()!=null?base.getRemark28():"";
						}else if(config.getConfigColumnName()=="remark29"||config.getConfigColumnName().equals("remark29")){
							datas[j]=base.getRemark29()!=null?base.getRemark29():"";
						}else if(config.getConfigColumnName()=="remark30"||config.getConfigColumnName().equals("remark30")){
							datas[j]=base.getRemark30()!=null?base.getRemark30():"";
						}else if(config.getConfigColumnName()=="assetsProducerBySupportId"||config.getConfigColumnName().equals("assetsProducerBySupportId")){
							datas[j]=base.getAssetsProducerBySupportId()!=null?base.getAssetsProducerBySupportId().getName():"";
						}else if(config.getConfigColumnName()=="assetsProducerByProduceId"||config.getConfigColumnName().equals("assetsProducerByProduceId")){
							datas[j]=base.getAssetsProducerByProduceId()!=null?base.getAssetsProducerByProduceId().getName():"";
						}else if(config.getConfigColumnName()=="usersByUsersId"||config.getConfigColumnName().equals("usersByUsersId")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getTruename():"";
						}else if(config.getConfigColumnName()=="usersByUsersIddepartment"||config.getConfigColumnName().equals("usersByUsersIddepartment")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getDepartment().getName():"";
						}else if(config.getConfigColumnName()=="usersByChargeId"||config.getConfigColumnName().equals("usersByChargeId")){
							datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getTruename():"";
						}else if(config.getConfigColumnName()=="usersByChargeIddepartment"||config.getConfigColumnName().equals("usersByChargeIddepartment")){
							datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getDepartment().getName():"";
						}
					}
					datas[assetsConfigList.size()]=obj[1];
					data.add(datas);
				}
			}
		}else{
			if(select4==null){
				for(int i=0;i<list.size();i++){
					AssetsBase base=(AssetsBase)list.get(i);
					datas=new Object[select2.length];
					for(int j=0;j<select2.length;j++){
						if(select2[j]=="codeId"||select2[j].equals("codeId")){
							datas[j]=base.getCodeId()!=null?base.getCodeId():"";
						}else if(select2[j]=="name"||select2[j].equals("name")){
							datas[j]=base.getName()!=null?base.getName():"";
						}else if(select2[j]=="model"||select2[j].equals("model")){
							datas[j]=base.getModel()!=null?base.getModel():"";
						}else if(select2[j]=="assetsType"||select2[j].equals("assetsType")){
							datas[j]=base.getAssetsType()!=null?base.getAssetsType().getName():"";
						}else if(select2[j]=="itsmType"||select2[j].equals("itsmType")){
							datas[j]=base.getItsmType()!=null?base.getItsmType().getName():"";
						}else if(select2[j]=="assetsState"||select2[j].equals("assetsState")){
							datas[j]=base.getAssetsState()!=null?base.getAssetsState().getName():"";
						}else if(select2[j]=="qualityTime"||select2[j].equals("qualityTime")){
							datas[j]=base.getQualityTime()!=null?base.getQualityTime():"";
						}else if(select2[j]=="price"||select2[j].equals("price")){
							datas[j]=base.getPrice()!=null?base.getPrice():"";
						}else if(select2[j]=="buyDate"||select2[j].equals("buyDate")){
							datas[j]=base.getBuyDate()!=null?this.time(base.getBuyDate()):"";
						}else if(select2[j]=="exitfacotryDate"||select2[j].equals("exitfacotryDate")){
							datas[j]=base.getExitfacotryDate()!=null?this.time(base.getExitfacotryDate()):"";
						}else if(select2[j]=="inDate"||select2[j].equals("inDate")){
							datas[j]=base.getInDate()!=null?this.time(base.getInDate()):"";
						}else if(select2[j]=="location"||select2[j].equals("location")){
							datas[j]=base.getLocation()!=null?base.getLocation().getName():"";
						}else if(select2[j]=="buildlocation"||select2[j].equals("buildlocation")){
							datas[j]=base.getBuildlocation()!=null?base.getBuildlocation().getAllname():"";
						}else if(select2[j]=="system"||select2[j].equals("system")){
							datas[j]=base.getSystem()!=null?base.getSystem():"";
						}else if(select2[j]=="ip"||select2[j].equals("ip")){
							datas[j]=base.getIp()!=null?base.getIp():"";
						}else if(select2[j]=="mac"||select2[j].equals("mac")){
							datas[j]=base.getMac()!=null?base.getMac():"";
						}else if(select2[j]=="devicename"||select2[j].equals("devicename")){
							datas[j]=base.getDevicename()!=null?base.getDevicename():"";
						}else if(select2[j]=="remark1"||select2[j].equals("remark1")){
							datas[j]=base.getRemark1()!=null?base.getRemark1():"";
						}else if(select2[j]=="remark2"||select2[j].equals("remark2")){
							datas[j]=base.getRemark2()!=null?base.getRemark2():"";
						}else if(select2[j]=="remark3"||select2[j].equals("remark3")){
							datas[j]=base.getRemark3()!=null?base.getRemark3():"";
						}else if(select2[j]=="remark4"||select2[j].equals("remark4")){
							datas[j]=base.getRemark4()!=null?base.getRemark4():"";
						}else if(select2[j]=="remark5"||select2[j].equals("remark5")){
							datas[j]=base.getRemark5()!=null?base.getRemark5():"";
						}else if(select2[j]=="remark6"||select2[j].equals("remark6")){
							datas[j]=base.getRemark6()!=null?base.getRemark6():"";
						}else if(select2[j]=="remark7"||select2[j].equals("remark7")){
							datas[j]=base.getRemark7()!=null?base.getRemark7():"";
						}else if(select2[j]=="remark8"||select2[j].equals("remark8")){
							datas[j]=base.getRemark8()!=null?base.getRemark8():"";
						}else if(select2[j]=="remark9"||select2[j].equals("remark9")){
							datas[j]=base.getRemark9()!=null?base.getRemark9():"";
						}else if(select2[j]=="remark10"||select2[j].equals("remark10")){
							datas[j]=base.getRemark10()!=null?base.getRemark10():"";
						}else if(select2[j]=="remark11"||select2[j].equals("remark11")){
							datas[j]=base.getRemark11()!=null?base.getRemark11():"";
						}else if(select2[j]=="remark12"||select2[j].equals("remark12")){
							datas[j]=base.getRemark12()!=null?base.getRemark12():"";
						}else if(select2[j]=="remark13"||select2[j].equals("remark13")){
							datas[j]=base.getRemark13()!=null?base.getRemark13():"";
						}else if(select2[j]=="remark14"||select2[j].equals("remark14")){
							datas[j]=base.getRemark14()!=null?base.getRemark14():"";
						}else if(select2[j]=="remark15"||select2[j].equals("remark15")){
							datas[j]=base.getRemark15()!=null?base.getRemark15():"";
						}else if(select2[j]=="remark16"||select2[j].equals("remark16")){
							datas[j]=base.getRemark16()!=null?base.getRemark16():"";
						}else if(select2[j]=="remark17"||select2[j].equals("remark17")){
							datas[j]=base.getRemark17()!=null?base.getRemark17():"";
						}else if(select2[j]=="remark18"||select2[j].equals("remark18")){
							datas[j]=base.getRemark18()!=null?base.getRemark18():"";
						}else if(select2[j]=="remark19"||select2[j].equals("remark19")){
							datas[j]=base.getRemark19()!=null?base.getRemark19():"";
						}else if(select2[j]=="remark20"||select2[j].equals("remark20")){
							datas[j]=base.getRemark20()!=null?base.getRemark20():"";
						}else if(select2[j]=="remark21"||select2[j].equals("remark21")){
							datas[j]=base.getRemark21()!=null?base.getRemark21():"";;
						}else if(select2[j]=="remark22"||select2[j].equals("remark22")){
							datas[j]=base.getRemark22()!=null?base.getRemark22():"";
						}else if(select2[j]=="remark23"||select2[j].equals("remark23")){
							datas[j]=base.getRemark23()!=null?base.getRemark23():"";
						}else if(select2[j]=="remark24"||select2[j].equals("remark24")){
							datas[j]=base.getRemark24()!=null?base.getRemark24():"";
						}else if(select2[j]=="remark25"||select2[j].equals("remark25")){
							datas[j]=base.getRemark25()!=null?base.getRemark25():"";
						}else if(select2[j]=="remark26"||select2[j].equals("remark26")){
							datas[j]=base.getRemark26()!=null?base.getRemark26():"";
						}else if(select2[j]=="remark27"||select2[j].equals("remark27")){
							datas[j]=base.getRemark27()!=null?base.getRemark27():"";
						}else if(select2[j]=="remark28"||select2[j].equals("remark28")){
							datas[j]=base.getRemark28()!=null?base.getRemark28():"";
						}else if(select2[j]=="remark29"||select2[j].equals("remark29")){
							datas[j]=base.getRemark29()!=null?base.getRemark29():"";
						}else if(select2[j]=="remark30"||select2[j].equals("remark30")){
							datas[j]=base.getRemark30()!=null?base.getRemark30():"";
						}else if(select2[j]=="assetsProducerBySupportId"||select2[j].equals("assetsProducerBySupportId")){
							datas[j]=base.getAssetsProducerBySupportId()!=null?base.getAssetsProducerBySupportId().getName():"";
						}else if(select2[j]=="assetsProducerByProduceId"||select2[j].equals("assetsProducerByProduceId")){
							datas[j]=base.getAssetsProducerByProduceId()!=null?base.getAssetsProducerByProduceId().getName():"";
						}else if(select2[j]=="usersByUsersId"||select2[j].equals("usersByUsersId")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getTruename():"";
						}else if(select2[j]=="usersByUsersIddepartment"||select2[j].equals("usersByUsersIddepartment")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getDepartment().getName():"";
						}else if(select2[j]=="usersByChargeId"||select2[j].equals("usersByChargeId")){
						    datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getTruename():"";
						}else if(select2[j]=="usersByChargeIddepartment"||select2[j].equals("usersByChargeIddepartment")){
						    datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getDepartment().getName():"";
						}
					}
					data.add(datas);
				}
			}else{
				for(int i=0;i<list.size();i++){
					datas=new Object[select2.length+1];
					Object obj[]=(Object[])list.get(i);
					AssetsBase base=(AssetsBase)obj[0];
					for(int j=0;j<select2.length;j++){
						if(select2[j]=="codeId"||select2[j].equals("codeId")){
							datas[j]=base.getCodeId()!=null?base.getCodeId():"";
						}else if(select2[j]=="name"||select2[j].equals("name")){
							datas[j]=base.getName()!=null?base.getName():"";
						}else if(select2[j]=="model"||select2[j].equals("model")){
							datas[j]=base.getModel()!=null?base.getModel():"";
						}else if(select2[j]=="assetsType"||select2[j].equals("assetsType")){
							datas[j]=base.getAssetsType()!=null?base.getAssetsType().getName():"";
						}else if(select2[j]=="itsmType"||select2[j].equals("itsmType")){
							datas[j]=base.getItsmType()!=null?base.getItsmType().getName():"";
						}else if(select2[j]=="assetsState"||select2[j].equals("assetsState")){
							datas[j]=base.getAssetsState()!=null?base.getAssetsState().getName():"";
						}else if(select2[j]=="qualityTime"||select2[j].equals("qualityTime")){
							datas[j]=base.getQualityTime()!=null?base.getQualityTime():"";
						}else if(select2[j]=="price"||select2[j].equals("price")){
							datas[j]=base.getPrice()!=null?base.getPrice():"";
						}else if(select2[j]=="buyDate"||select2[j].equals("buyDate")){
							datas[j]=base.getBuyDate()!=null?this.time(base.getBuyDate()):"";
						}else if(select2[j]=="exitfacotryDate"||select2[j].equals("exitfacotryDate")){
							datas[j]=base.getExitfacotryDate()!=null?this.time(base.getExitfacotryDate()):"";
						}else if(select2[j]=="inDate"||select2[j].equals("inDate")){
							datas[j]=base.getInDate()!=null?this.time(base.getInDate()):"";
						}else if(select2[j]=="location"||select2[j].equals("location")){
							datas[j]=base.getLocation()!=null?base.getLocation().getName():"";
						}else if(select2[j]=="buildlocation"||select2[j].equals("buildlocation")){
							datas[j]=base.getBuildlocation()!=null?base.getBuildlocation().getAllname():"";
						}else if(select2[j]=="system"||select2[j].equals("system")){
							datas[j]=base.getSystem()!=null?base.getSystem():"";
						}else if(select2[j]=="ip"||select2[j].equals("ip")){
							datas[j]=base.getIp()!=null?base.getIp():"";
						}else if(select2[j]=="mac"||select2[j].equals("mac")){
							datas[j]=base.getMac()!=null?base.getMac():"";
						}else if(select2[j]=="devicename"||select2[j].equals("devicename")){
							datas[j]=base.getDevicename()!=null?base.getDevicename():"";
						}else if(select2[j]=="remark1"||select2[j].equals("remark1")){
							datas[j]=base.getRemark1()!=null?base.getRemark1():"";
						}else if(select2[j]=="remark2"||select2[j].equals("remark2")){
							datas[j]=base.getRemark2()!=null?base.getRemark2():"";
						}else if(select2[j]=="remark3"||select2[j].equals("remark3")){
							datas[j]=base.getRemark3()!=null?base.getRemark3():"";
						}else if(select2[j]=="remark4"||select2[j].equals("remark4")){
							datas[j]=base.getRemark4()!=null?base.getRemark4():"";
						}else if(select2[j]=="remark5"||select2[j].equals("remark5")){
							datas[j]=base.getRemark5()!=null?base.getRemark5():"";
						}else if(select2[j]=="remark6"||select2[j].equals("remark6")){
							datas[j]=base.getRemark6()!=null?base.getRemark6():"";
						}else if(select2[j]=="remark7"||select2[j].equals("remark7")){
							datas[j]=base.getRemark7()!=null?base.getRemark7():"";
						}else if(select2[j]=="remark8"||select2[j].equals("remark8")){
							datas[j]=base.getRemark8()!=null?base.getRemark8():"";
						}else if(select2[j]=="remark9"||select2[j].equals("remark9")){
							datas[j]=base.getRemark9()!=null?base.getRemark9():"";
						}else if(select2[j]=="remark10"||select2[j].equals("remark10")){
							datas[j]=base.getRemark10()!=null?base.getRemark10():"";
						}else if(select2[j]=="remark11"||select2[j].equals("remark11")){
							datas[j]=base.getRemark11()!=null?base.getRemark11():"";
						}else if(select2[j]=="remark12"||select2[j].equals("remark12")){
							datas[j]=base.getRemark12()!=null?base.getRemark12():"";
						}else if(select2[j]=="remark13"||select2[j].equals("remark13")){
							datas[j]=base.getRemark13()!=null?base.getRemark13():"";
						}else if(select2[j]=="remark14"||select2[j].equals("remark14")){
							datas[j]=base.getRemark14()!=null?base.getRemark14():"";
						}else if(select2[j]=="remark15"||select2[j].equals("remark15")){
							datas[j]=base.getRemark15()!=null?base.getRemark15():"";
						}else if(select2[j]=="remark16"||select2[j].equals("remark16")){
							datas[j]=base.getRemark16()!=null?base.getRemark16():"";
						}else if(select2[j]=="remark17"||select2[j].equals("remark17")){
							datas[j]=base.getRemark17()!=null?base.getRemark17():"";
						}else if(select2[j]=="remark18"||select2[j].equals("remark18")){
							datas[j]=base.getRemark18()!=null?base.getRemark18():"";
						}else if(select2[j]=="remark19"||select2[j].equals("remark19")){
							datas[j]=base.getRemark19()!=null?base.getRemark19():"";
						}else if(select2[j]=="remark20"||select2[j].equals("remark20")){
							datas[j]=base.getRemark20()!=null?base.getRemark20():"";
						}else if(select2[j]=="remark21"||select2[j].equals("remark21")){
							datas[j]=base.getRemark21()!=null?base.getRemark21():"";;
						}else if(select2[j]=="remark22"||select2[j].equals("remark22")){
							datas[j]=base.getRemark22()!=null?base.getRemark22():"";
						}else if(select2[j]=="remark23"||select2[j].equals("remark23")){
							datas[j]=base.getRemark23()!=null?base.getRemark23():"";
						}else if(select2[j]=="remark24"||select2[j].equals("remark24")){
							datas[j]=base.getRemark24()!=null?base.getRemark24():"";
						}else if(select2[j]=="remark25"||select2[j].equals("remark25")){
							datas[j]=base.getRemark25()!=null?base.getRemark25():"";
						}else if(select2[j]=="remark26"||select2[j].equals("remark26")){
							datas[j]=base.getRemark26()!=null?base.getRemark26():"";
						}else if(select2[j]=="remark27"||select2[j].equals("remark27")){
							datas[j]=base.getRemark27()!=null?base.getRemark27():"";
						}else if(select2[j]=="remark28"||select2[j].equals("remark28")){
							datas[j]=base.getRemark28()!=null?base.getRemark28():"";
						}else if(select2[j]=="remark29"||select2[j].equals("remark29")){
							datas[j]=base.getRemark29()!=null?base.getRemark29():"";
						}else if(select2[j]=="remark30"||select2[j].equals("remark30")){
							datas[j]=base.getRemark30()!=null?base.getRemark30():"";
						}else if(select2[j]=="assetsProducerBySupportId"||select2[j].equals("assetsProducerBySupportId")){
							datas[j]=base.getAssetsProducerBySupportId()!=null?base.getAssetsProducerBySupportId().getName():"";
						}else if(select2[j]=="assetsProducerByProduceId"||select2[j].equals("assetsProducerByProduceId")){
							datas[j]=base.getAssetsProducerByProduceId()!=null?base.getAssetsProducerByProduceId().getName():"";
						}else if(select2[j]=="usersByUsersId"||select2[j].equals("usersByUsersId")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getTruename():"";
						}else if(select2[j]=="usersByUsersIddepartment"||select2[j].equals("usersByUsersIddepartment")){
							datas[j]=base.getUsersByUsersId()!=null?base.getUsersByUsersId().getDepartment().getName():"";
						}else if(select2[j]=="usersByChargeId"||select2[j].equals("usersByChargeId")){
						    datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getTruename():"";
						}else if(select2[j]=="usersByChargeIddepartment"||select2[j].equals("usersByChargeIddepartment")){
						    datas[j]=base.getUsersByChargeId()!=null?base.getUsersByChargeId().getDepartment().getName():"";
						}
					}
					datas[select2.length]=obj[1];
					data.add(datas);
				}
			}
		}
		try {
			String fname = "Excel";// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ fname+".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, data, title);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public String toExcelchoose() throws Exception{
		request = ServletActionContext.getRequest();
		request.getSession().setAttribute("assetsexcel",assets);
		String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
		assetsConfigList=assetsConfigService.findbysql(queryString);
		return "success";
	}
	

	public void writeExcel(OutputStream out, List datas, String[] title) {
		if (datas == null) {
			throw new IllegalArgumentException("写excel流需要List参数!");
		}
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			WritableSheet ws = workbook.createSheet("sheet 1", 0);
			int rowNum = 0; // 要写的行
			if (title != null) {
				putRow(ws, 0, title);// 压入标题
				rowNum = 1;
			}
			java.lang.reflect.Method[] method = AssetsBase.class.getDeclaredMethods();
			for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
				Object[] cells = (Object[]) datas.get(i);
				putRow(ws, rowNum, cells); // 压一行到shee
			}
			workbook.write();
			workbook.close(); // 一定要关闭, 否则没有保存Excel
		} catch (RowsExceededException e) {
			System.out.println("jxl write RowsExceededException:"+ e.getMessage());
		} catch (WriteException e) {
			System.out.println("jxl write WriteException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("jxl write file i/o exception!, cause by:"+ e.getMessage());
		}
	}

	private void putRow(WritableSheet ws, int rowNum, Object[] cells)
			throws RowsExceededException, WriteException {
		for (int j = 0; j < cells.length; j++) {// 写一行
			Label cell = new Label(j, rowNum, "" + cells[j]);
			ws.addCell(cell);
		}
	}
	
	public Timestamp StringTime(String value) throws Exception{
		String values=value.trim()+" "+"00:00:00";
		return Timestamp.valueOf(values);
	}
	
	public String time(Timestamp tamp){
		Date currentTime = tamp;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = format.format(currentTime);
	    return dateString;
	}
	
	private int message=0;
	
	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public String changes()throws Exception{
		String assetsid[]=id.split(",");
		for(int i=0;i<assetsid.length;i++)
		{
			AssetsChange change=new AssetsChange();
			AssetsBase assetsbase=assetsService.findById(Integer.parseInt(assetsid[i]));
			change.setAssetsBase(assetsbase);
			AssetsState state=null;
			if(changeType!="-1"&&!changeType.equals("-1")){
				state=assetsStateService.findbyId(Integer.parseInt(changeType));
				change.setAssetsState(state);
			}
			if(usersByChargeid!="-1"&&!usersByChargeid.equals("-1")){
				Users userCharge=userService.findUserById(Integer.parseInt(usersByChargeid));//负责人
				change.setUsersByChargeid(userCharge);
			}
			if(usersByManagersid!="-1"&&!usersByManagersid.equals("-1")){
				Users userManagers=userService.findUserById(Integer.parseInt(usersByManagersid));//经办人
				change.setUsersByManagersid(userManagers);
			}
			if(usersByUserid!="-1"&&!usersByUserid.equals("-1")){
				Users users=userService.findUserById(Integer.parseInt(usersByUserid));//使用人
				change.setUsersByUserid(users);
				assetsbase.setUsersByUsersId(users);
			}
			if(changeTime!=""&&!changeTime.equals("")){
				change.setChangeTime(this.StringTime(changeTime));
			}
			if(changeDescription!=null&&!changeDescription.equals("")){
				change.setChangeDescription(changeDescription);
			}
			assetsbase.setAssetsState(state);
			if(ishis=="on"||"on".equals(ishis)){
				assetsbase.setIshis(1);
			}else{
				assetsbase.setIshis(0);
			}
			assetsService.updateAssetsByCode(assetsbase);
			assetsChangeService.save(change);
			
		}
		message=1;
		return "success";
	}
	
	
	
	private String changeTime;
	private String changeType;
	private String usersByChargeid;
	private String usersByManagersid;
	private String usersByUserid;
	private String changeDescription;
	private String ishis;
	public String getIshis() {
		return ishis;
	}

	public void setIshis(String ishis) {
		this.ishis = ishis;
	}

	private AssetsChangeService assetsChangeService;
	

	public AssetsChangeService getAssetsChangeService() {
		return assetsChangeService;
	}

	public void setAssetsChangeService(AssetsChangeService assetsChangeService) {
		this.assetsChangeService = assetsChangeService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Log getLog() {
		return log;
	}

	public String getUsersByChargeid() {
		return usersByChargeid;
	}

	public void setUsersByChargeid(String usersByChargeid) {
		this.usersByChargeid = usersByChargeid;
	}

	public String getUsersByManagersid() {
		return usersByManagersid;
	}

	public void setUsersByManagersid(String usersByManagersid) {
		this.usersByManagersid = usersByManagersid;
	}

	public String getUsersByUserid() {
		return usersByUserid;
	}

	public void setUsersByUserid(String usersByUserid) {
		this.usersByUserid = usersByUserid;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	
	private AssetsChange assetsChange;
	
	public AssetsChange getAssetsChange() {
		return assetsChange;
	}

	public void setAssetsChange(AssetsChange assetsChange) {
		this.assetsChange = assetsChange;
	}

	public String changequery(){
		this.pageBean = assetsChangeService.queryByPage(assetsChange, pageSize, page);
		return "success";
	}
	
	public String changebaseshow(){
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
	
	public String changeshow(){
		assetsChange=assetsChangeService.findById(Integer.parseInt(id));
		return "success";
	}
	
	public String changeupdate(){
		AssetsChange change=new AssetsChange(); 
		change=assetsChangeService.findById(assetsChange.getId());
		AssetsBase assets=change.getAssetsBase();
		AssetsState state=assetsStateService.findbyId(assetsChange.getAssetsState().getId());
		change.setAssetsState(state);
		assets.setAssetsState(state);
		assets.setUsersByUsersId(userService.findUserById(assetsChange.getUsersByUserid().getId()));
		assetsService.updateAssetsByCode(assets);
		change.setUsersByChargeid(userService.findUserById(assetsChange.getUsersByChargeid().getId()));
		change.setUsersByManagersid(userService.findUserById(assetsChange.getUsersByManagersid().getId()));
		change.setUsersByUserid(userService.findUserById(assetsChange.getUsersByUserid().getId()));
		change.setChangeTime(assetsChange.getChangeTime());
		change.setRemark(assetsChange.getRemark());
		change.setChangeDescription(assetsChange.getChangeDescription());
		assetsChangeService.update(change);
		message=1;
		return "success";
	}
	public String print(){
		if(flag.equals("0")){
			assets=assetsService.findById(Integer.parseInt(assetsId));
			return "in";
		}else{
			assetsChange=assetsChangeService.findById(Integer.parseInt(assetsId));
			return "brrow";
		}
	}
	private MonitorDeviceTypeService monitorDeviceTypeService;

	public MonitorDeviceTypeService getMonitorDeviceTypeService() {
		return monitorDeviceTypeService;
	}

	public void setMonitorDeviceTypeService(
			MonitorDeviceTypeService monitorDeviceTypeService) {
		this.monitorDeviceTypeService = monitorDeviceTypeService;
	}

	public String device(){
		this.pageBean =assetsService.findAll(pageSize, page);
		supportlist=producerService.findAll();
		producelist=producerService.findAll();
		locationlist=locationService.findAll();
		statelist=assetsStateService.findAll();
		List list=assetsService.device();
		AssetsState state=(AssetsState)assetsStateService.findbynamesize("使用中").get(0);
		
		for(int i=0;i<list.size();i++){
			Object obj[]=(Object[])list.get(i);
			AssetsBase base=new AssetsBase();
			AssetsInfo info=new AssetsInfo();
			MonitorDeviceType Devicetype=monitorDeviceTypeService.getById(Integer.parseInt(obj[3].toString()));
			AssetsType type=(AssetsType)assetsTypeService.findByName(Devicetype.getName()).get(0);
			base.setCodeId(this.codeId(type));
			base.setAssetsType(type);
			base.setAssetsState(state);
			base.setIp((String)obj[0]);
			base.setMac((String)obj[1]);
			base.setDevicename((String)obj[2]);
			base.setModel((String)obj[4]);
			Object size=assetsService.getAllRows("select max(b.valueUnit) as maxvalue from AssetsBase as b").get(0);
			int max=0;
			if(size!=null){
				max=Integer.parseInt(size.toString());
			}
			base.setValueUnit(max+1);
			base.setDes(0);
//			assetsService.save(base);
//			info.setAssetsBase(base);
//			assetsService.AssetsInfosaveOrUpdate(info);
		}
		return "success";
	}
	
	public String getMac0() {
		return mac0;
	}

	public void setMac0(String mac0) {
		this.mac0 = mac0;
	}

	public String getDevicename0() {
		return devicename0;
	}

	public void setDevicename0(String devicename0) {
		this.devicename0 = devicename0;
	}

	public String getRemark110() {
		return remark110;
	}

	public void setRemark110(String remark110) {
		this.remark110 = remark110;
	}

	public String getRemark120() {
		return remark120;
	}

	public void setRemark120(String remark120) {
		this.remark120 = remark120;
	}

	public String getRemark130() {
		return remark130;
	}

	public void setRemark130(String remark130) {
		this.remark130 = remark130;
	}

	public String getRemark140() {
		return remark140;
	}

	public void setRemark140(String remark140) {
		this.remark140 = remark140;
	}

	public String getRemark150() {
		return remark150;
	}

	public void setRemark150(String remark150) {
		this.remark150 = remark150;
	}

	public String getRemark160() {
		return remark160;
	}

	public void setRemark160(String remark160) {
		this.remark160 = remark160;
	}

	public String getRemark170() {
		return remark170;
	}

	public void setRemark170(String remark170) {
		this.remark170 = remark170;
	}

	public String getRemark180() {
		return remark180;
	}

	public void setRemark180(String remark180) {
		this.remark180 = remark180;
	}

	public String getRemark190() {
		return remark190;
	}

	public void setRemark190(String remark190) {
		this.remark190 = remark190;
	}

	public String getRemark200() {
		return remark200;
	}

	public void setRemark200(String remark200) {
		this.remark200 = remark200;
	}

	public String getRemark210() {
		return remark210;
	}

	public void setRemark210(String remark210) {
		this.remark210 = remark210;
	}

	public String getRemark220() {
		return remark220;
	}

	public void setRemark220(String remark220) {
		this.remark220 = remark220;
	}

	public String getRemark230() {
		return remark230;
	}

	public void setRemark230(String remark230) {
		this.remark230 = remark230;
	}

	public String getRemark240() {
		return remark240;
	}

	public void setRemark240(String remark240) {
		this.remark240 = remark240;
	}

	public String getRemark250() {
		return remark250;
	}

	public void setRemark250(String remark250) {
		this.remark250 = remark250;
	}

	public String getRemark260() {
		return remark260;
	}

	public void setRemark260(String remark260) {
		this.remark260 = remark260;
	}

	public String getRemark270() {
		return remark270;
	}

	public void setRemark270(String remark270) {
		this.remark270 = remark270;
	}

	public String getRemark280() {
		return remark280;
	}

	public void setRemark280(String remark280) {
		this.remark280 = remark280;
	}

	public String getRemark290() {
		return remark290;
	}

	public void setRemark290(String remark290) {
		this.remark290 = remark290;
	}

	public String getRemark300() {
		return remark300;
	}

	public void setRemark300(String remark300) {
		this.remark300 = remark300;
	}

	public String computer() throws SQLException{
//		this.pageBean =assetsService.findAll(pageSize, page);
//		supportlist=producerService.findAll();
//		producelist=producerService.findAll();
//		locationlist=locationService.findAll();
//		statelist=assetsStateService.findAll();
//		List list=assetsService.computer();
//		AssetsType type=(AssetsType)assetsTypeService.findByName("入网计算机").get(0);
//		AssetsState state=(AssetsState)assetsStateService.findbynamesize("使用中").get(0);
//		
//		for(int i=0;i<list.size();i++){
//			Object obj[]=(Object[])list.get(i);
//			
//			AssetsBase base=assetsService.findByPn((String)obj[1]);
//			AssetsInfo info=new AssetsInfo();
//			base.setCodeId(this.codeId(type));
//			base.setAssetsType(type);
//			base.setAssetsState(state);
//			base.setIp((String)obj[0]);
//			base.setPn((String)obj[1]);
//			base.setSn((String)obj[2]);
//			if(base.getCode()!=null){
//				Object size=assetsService.getAllRows("select max(b.valueUnit) as maxvalue from AssetsBase as b").get(0);
//				int max=0;
//				if(size!=null){
//					max=Integer.parseInt(size.toString());
//				}
//				base.setValueUnit(max+1);
//				base.setDes(0);
//			}
//			
			
//			assetsService.save(base);
//			info.setAssetsBase(base);
//			assetsService.AssetsInfosaveOrUpdate(info);
		
//		}
		assetsService.sql("select * from itsm_type");
		return "success";
	}
	
	public String codeId(AssetsType type){
		Calendar cal=Calendar.getInstance(); 
		Timestamp timestamp=new Timestamp(cal.getTime().getTime()); 
		String time=new SimpleDateFormat("yyyyMMdd").format(timestamp);
		String types=type.getId().toString();
		if(type.getId()<10){types=0+""+type;}
		Random random = new Random(); 
		String math=random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10)+""+random.nextInt(10);
		String codeId=types+""+time+""+math;
		return codeId;
	}
}
