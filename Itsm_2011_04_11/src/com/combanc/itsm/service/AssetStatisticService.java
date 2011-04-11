package com.combanc.itsm.service;

import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.AssetStatisticDAO;
import com.combanc.itsm.pojo.AssetsChange;

public class AssetStatisticService extends BaseService<AssetsChange, Integer> {
	
	private AssetStatisticDAO assetStatisticDAO;
	public AssetStatisticDAO getAssetStatisticDAO() {
		return assetStatisticDAO;
	}
	public void setAssetStatisticDAO(AssetStatisticDAO assetStatisticDAO) {
		this.assetStatisticDAO = assetStatisticDAO;
	}
	/*
	 * 获取设备类型统计数据
	 */
	public String getAssetTypeStatistic(){
		return assetStatisticDAO.getTypeStatistic();
	}
	public String getAssetYearStatistic() {
		// TODO Auto-generated method stub
		return assetStatisticDAO.getYearStatistic();
	}
	public String getTypeXmllist(){
		return assetStatisticDAO.typeXmlList();
	}
	
	public String getAssetStateStatistic(){
		return assetStatisticDAO.getStateStatistic();
	}
	
	public String getAssetTypeNode(int pid){
		return assetStatisticDAO.geTypeNode(pid);
	}
	public String getAssetYearByParam(int typeId){
		if (typeId==0) {
			return assetStatisticDAO.getYearStatistic();
		}else{
			return assetStatisticDAO.getYearStatisticByParam(typeId);
		}
	}
}
