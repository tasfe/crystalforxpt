package com.combanc.itsm.service;

import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsProducer;

public interface IAssetsService {

	public boolean save(AssetsBase assets);

	public AssetsBase getAssetsByCode(String code);

	public void delAssetsByCode(String code);

	public void updateAssetsByCode(AssetsBase assets);

	public AssetsProducer getProducerOrSupperterAssets(int code);

}
