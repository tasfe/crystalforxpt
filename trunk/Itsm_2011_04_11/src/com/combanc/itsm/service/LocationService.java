package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.LocationDAO;
import com.combanc.itsm.pojo.Location;

public class LocationService {

	private LocationDAO locationDAO;
	private Integer parent;

	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}

	public List<Location> findAll() {
		return locationDAO.findAll();
	}

	public List<Location> findAllByPid(Integer pid) {
		return locationDAO.findByPid(pid);
	}
	
	public List<Location> findByName(String name){
		return locationDAO.findByName(name);
	}
	
	public Location findlocationById(Integer locationId) {
		return (Location) locationDAO.findById(Location.class, locationId);
	}

	public void savelocation(Location location) {
				
		locationDAO.save(location);
		Location plLocation =locationDAO.findById(location.getPid());
		location.setCode(plLocation.getCode()+"|"+location.getId());
		locationDAO.update(location);
		
	}

	public void update(Location location) {
		locationDAO.update(location);
	}

	public void saveOrUpdate(Location location) {
		locationDAO.attachDirty(location);
	}

	public void deleteById(Integer locationId) {
		Location location = null;
		if (locationId != null)
			location = (Location) locationDAO.findById(Location.class,
					locationId);
		if (location != null)
			locationDAO.delete(location);
	}
	public List<Location> findAllByCode(String code) {
		return locationDAO.findByCode(code);
	}
}
