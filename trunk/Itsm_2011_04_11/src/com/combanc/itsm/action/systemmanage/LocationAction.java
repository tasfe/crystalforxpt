package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.Location;
import com.combanc.itsm.pojo.Roleteam;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.pojo.WorkLog;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.LocationService;
import com.combanc.itsm.service.RoleteamService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.service.worklog.WorkLogService;

public class LocationAction extends BaseActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private Location location;
	private List locations;
	private String flag;
	private String actionURI;
	private Integer locationId;
	private String name;
	private Integer parent;
	private LocationService locationService;
	private int pid;

	private AssetsService assetsService;
	private RoleteamService roleteamService;
	private UserService usersService;
	private WorkLogService workLogService;
	private String msg;
	private String parentName;
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public List getLocations() {
		return locations;
	}

	public void setLocations(List locations) {
		this.locations = locations;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String top() throws Exception {
		locations = locationService.findAll();
		return "success";
	}

	public String top2() throws Exception {
		locations = locationService.findAll();
		return "success";
	}

	public String main() throws Exception {
		return "success";
	}

	public String list() throws Exception {
		locations = locationService.findAllByPid(pid);

		return "success";
	}

	public String addInput() throws Exception {
		parentName="";
		actionURI = "save";
		return "success";
	}

	public String save() throws Exception {

		locationService.savelocation(location);
		pid = location.getPid();

		return "list";
	}

	public String updateInput() throws Exception {
		actionURI = "update";
		location = locationService.findlocationById(locationId);
		if(null!=location){
			parentName=locationService.findlocationById(location.getPid())==null?"":locationService.findlocationById(location.getPid()).getName();
		}
		return "success";
	}

	public String update() throws Exception {
		Location pLocation = locationService.findlocationById(location.getId());
		pLocation.setLocationSc(location.getLocationSc());
		pLocation.setName(location.getName());
		pLocation.setContent(location.getContent());
		locationService.update(pLocation);
		return "list";
	}

	public String delete() throws Exception {

		Location aLocation = locationService.findlocationById(locationId);
		List tList = locationService.findAllByPid(aLocation.getId());
		if (tList != null && tList.size() > 0) {
			msg = "有下属地理位置，请先删除子地理位置";
			return "list";
		}
		List<AssetsBase> list1 = assetsService.findByLocation(locationId);
		for (int i = 0; i < list1.size(); i++) {
			AssetsBase base = (AssetsBase) list1.get(i);
			base.setLocation(null);
			assetsService.update(base);
		}
		List<Users> list2 = usersService.findByLocation(locationId);
		for (int i = 0; i < list2.size(); i++) {
			Users user = (Users) list2.get(i);
			user.setLocation(null);
			usersService.update(user);
		}
		List<Roleteam> list3 = roleteamService.findByLocaton(locationId);
		for (int i = 0; i < list3.size(); i++) {
			Roleteam roleteam = (Roleteam) list3.get(i);
			roleteam.setLocation(null);
			roleteamService.update(roleteam);
		}
		List<WorkLog> list4 = workLogService.findByLocation(locationId);
		for (int i = 0; i < list4.size(); i++) {
			WorkLog workLog = (WorkLog) list4.get(i);
			workLog.setLocation(null);
			workLogService.update(workLog);
		}

		locationService.deleteById(locationId);
		return "list";
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public RoleteamService getRoleteamService() {
		return roleteamService;
	}

	public void setRoleteamService(RoleteamService roleteamService) {
		this.roleteamService = roleteamService;
	}

	public UserService getUsersService() {
		return usersService;
	}

	public void setUsersService(UserService usersService) {
		this.usersService = usersService;
	}

	public WorkLogService getWorkLogService() {
		return workLogService;
	}

	public void setWorkLogService(WorkLogService workLogService) {
		this.workLogService = workLogService;
	}

}
