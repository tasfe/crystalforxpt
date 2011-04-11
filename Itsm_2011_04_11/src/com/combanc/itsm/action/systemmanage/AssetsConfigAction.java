package com.combanc.itsm.action.systemmanage;

import java.util.List;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsConfig;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.ConfigRecord;
import com.combanc.itsm.service.AssetsConfigService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.ConfigRecordService;
import com.opensymphony.xwork2.ActionContext;

public class AssetsConfigAction extends BaseActionSupport implements
ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private javax.servlet.http.HttpServletRequest request;
	private AssetsTypeService assetsTypeService;
	private AssetsConfigService assetsConfigService;
	private ConfigRecordService configrecordService;
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCodeId00() {
		return codeId00;
	}

	public void setCodeId00(String codeId00) {
		this.codeId00 = codeId00;
	}

	public String getCodeId10() {
		return codeId10;
	}

	public void setCodeId10(String codeId10) {
		this.codeId10 = codeId10;
	}

	public String getCodeId20() {
		return codeId20;
	}

	public void setCodeId20(String codeId20) {
		this.codeId20 = codeId20;
	}

	public String getCodeId30() {
		return codeId30;
	}

	public void setCodeId30(String codeId30) {
		this.codeId30 = codeId30;
	}

	public String getCodeId40() {
		return codeId40;
	}

	public void setCodeId40(String codeId40) {
		this.codeId40 = codeId40;
	}

	public String getName00() {
		return name00;
	}

	public void setName00(String name00) {
		this.name00 = name00;
	}

	public String getName10() {
		return name10;
	}

	public void setName10(String name10) {
		this.name10 = name10;
	}

	public String getName20() {
		return name20;
	}

	public void setName20(String name20) {
		this.name20 = name20;
	}

	public String getName30() {
		return name30;
	}

	public void setName30(String name30) {
		this.name30 = name30;
	}

	public String getName40() {
		return name40;
	}

	public void setName40(String name40) {
		this.name40 = name40;
	}

	public String getModel00() {
		return model00;
	}

	public void setModel00(String model00) {
		this.model00 = model00;
	}

	public String getModel10() {
		return model10;
	}

	public void setModel10(String model10) {
		this.model10 = model10;
	}

	public String getModel20() {
		return model20;
	}

	public void setModel20(String model20) {
		this.model20 = model20;
	}

	public String getModel30() {
		return model30;
	}

	public void setModel30(String model30) {
		this.model30 = model30;
	}

	public String getModel40() {
		return model40;
	}

	public void setModel40(String model40) {
		this.model40 = model40;
	}

	public String getAssetsType00() {
		return assetsType00;
	}

	public void setAssetsType00(String assetsType00) {
		this.assetsType00 = assetsType00;
	}

	public String getAssetsType10() {
		return assetsType10;
	}

	public void setAssetsType10(String assetsType10) {
		this.assetsType10 = assetsType10;
	}

	public String getAssetsType20() {
		return assetsType20;
	}

	public void setAssetsType20(String assetsType20) {
		this.assetsType20 = assetsType20;
	}

	public String getAssetsType30() {
		return assetsType30;
	}

	public void setAssetsType30(String assetsType30) {
		this.assetsType30 = assetsType30;
	}

	public String getAssetsType40() {
		return assetsType40;
	}

	public void setAssetsType40(String assetsType40) {
		this.assetsType40 = assetsType40;
	}

	public String getAssetsState00() {
		return assetsState00;
	}

	public void setAssetsState00(String assetsState00) {
		this.assetsState00 = assetsState00;
	}

	public String getAssetsState10() {
		return assetsState10;
	}

	public void setAssetsState10(String assetsState10) {
		this.assetsState10 = assetsState10;
	}

	public String getAssetsState20() {
		return assetsState20;
	}

	public void setAssetsState20(String assetsState20) {
		this.assetsState20 = assetsState20;
	}

	public String getAssetsState30() {
		return assetsState30;
	}

	public void setAssetsState30(String assetsState30) {
		this.assetsState30 = assetsState30;
	}

	public String getAssetsState40() {
		return assetsState40;
	}

	public void setAssetsState40(String assetsState40) {
		this.assetsState40 = assetsState40;
	}

	public String getIp00() {
		return ip00;
	}

	public void setIp00(String ip00) {
		this.ip00 = ip00;
	}

	public String getIp10() {
		return ip10;
	}

	public void setIp10(String ip10) {
		this.ip10 = ip10;
	}

	public String getIp20() {
		return ip20;
	}

	public void setIp20(String ip20) {
		this.ip20 = ip20;
	}

	public String getIp30() {
		return ip30;
	}

	public void setIp30(String ip30) {
		this.ip30 = ip30;
	}

	public String getIp40() {
		return ip40;
	}

	public void setIp40(String ip40) {
		this.ip40 = ip40;
	}

	public String getQualityTime00() {
		return qualityTime00;
	}

	public void setQualityTime00(String qualityTime00) {
		this.qualityTime00 = qualityTime00;
	}

	public String getQualityTime10() {
		return qualityTime10;
	}

	public void setQualityTime10(String qualityTime10) {
		this.qualityTime10 = qualityTime10;
	}

	public String getQualityTime20() {
		return qualityTime20;
	}

	public void setQualityTime20(String qualityTime20) {
		this.qualityTime20 = qualityTime20;
	}

	public String getQualityTime30() {
		return qualityTime30;
	}

	public void setQualityTime30(String qualityTime30) {
		this.qualityTime30 = qualityTime30;
	}

	public String getQualityTime40() {
		return qualityTime40;
	}

	public void setQualityTime40(String qualityTime40) {
		this.qualityTime40 = qualityTime40;
	}

	public String getBuyDate00() {
		return buyDate00;
	}

	public void setBuyDate00(String buyDate00) {
		this.buyDate00 = buyDate00;
	}

	public String getBuyDate10() {
		return buyDate10;
	}

	public void setBuyDate10(String buyDate10) {
		this.buyDate10 = buyDate10;
	}

	public String getBuyDate20() {
		return buyDate20;
	}

	public void setBuyDate20(String buyDate20) {
		this.buyDate20 = buyDate20;
	}

	public String getBuyDate30() {
		return buyDate30;
	}

	public void setBuyDate30(String buyDate30) {
		this.buyDate30 = buyDate30;
	}

	public String getBuyDate40() {
		return buyDate40;
	}

	public void setBuyDate40(String buyDate40) {
		this.buyDate40 = buyDate40;
	}

	public String getExitfacotryDate00() {
		return exitfacotryDate00;
	}

	public void setExitfacotryDate00(String exitfacotryDate00) {
		this.exitfacotryDate00 = exitfacotryDate00;
	}

	public String getExitfacotryDate10() {
		return exitfacotryDate10;
	}

	public void setExitfacotryDate10(String exitfacotryDate10) {
		this.exitfacotryDate10 = exitfacotryDate10;
	}

	public String getExitfacotryDate20() {
		return exitfacotryDate20;
	}

	public void setExitfacotryDate20(String exitfacotryDate20) {
		this.exitfacotryDate20 = exitfacotryDate20;
	}

	public String getExitfacotryDate30() {
		return exitfacotryDate30;
	}

	public void setExitfacotryDate30(String exitfacotryDate30) {
		this.exitfacotryDate30 = exitfacotryDate30;
	}

	public String getExitfacotryDate40() {
		return exitfacotryDate40;
	}

	public void setExitfacotryDate40(String exitfacotryDate40) {
		this.exitfacotryDate40 = exitfacotryDate40;
	}

	public String getInDate00() {
		return inDate00;
	}

	public void setInDate00(String inDate00) {
		this.inDate00 = inDate00;
	}

	public String getInDate10() {
		return inDate10;
	}

	public void setInDate10(String inDate10) {
		this.inDate10 = inDate10;
	}

	public String getInDate20() {
		return inDate20;
	}

	public void setInDate20(String inDate20) {
		this.inDate20 = inDate20;
	}

	public String getInDate30() {
		return inDate30;
	}

	public void setInDate30(String inDate30) {
		this.inDate30 = inDate30;
	}

	public String getInDate40() {
		return inDate40;
	}

	public void setInDate40(String inDate40) {
		this.inDate40 = inDate40;
	}

	public String getPrice00() {
		return price00;
	}

	public void setPrice00(String price00) {
		this.price00 = price00;
	}

	public String getPrice10() {
		return price10;
	}

	public void setPrice10(String price10) {
		this.price10 = price10;
	}

	public String getPrice20() {
		return price20;
	}

	public void setPrice20(String price20) {
		this.price20 = price20;
	}

	public String getPrice30() {
		return price30;
	}

	public void setPrice30(String price30) {
		this.price30 = price30;
	}

	public String getPrice40() {
		return price40;
	}

	public void setPrice40(String price40) {
		this.price40 = price40;
	}

	public String getSystem00() {
		return system00;
	}
	
	public String getBuildlocation00() {
		return buildlocation00;
	}

	public void setBuildlocation00(String buildlocation00) {
		this.buildlocation00 = buildlocation00;
	}

	public String getBuildlocation10() {
		return buildlocation10;
	}

	public void setBuildlocation10(String buildlocation10) {
		this.buildlocation10 = buildlocation10;
	}

	public String getBuildlocation20() {
		return buildlocation20;
	}

	public void setBuildlocation20(String buildlocation20) {
		this.buildlocation20 = buildlocation20;
	}

	public String getBuildlocation30() {
		return buildlocation30;
	}

	public void setBuildlocation30(String buildlocation30) {
		this.buildlocation30 = buildlocation30;
	}

	public String getBuildlocation40() {
		return buildlocation40;
	}

	public void setBuildlocation40(String buildlocation40) {
		this.buildlocation40 = buildlocation40;
	}

	public void setSystem00(String system00) {
		this.system00 = system00;
	}

	public String getSystem10() {
		return system10;
	}

	public void setSystem10(String system10) {
		this.system10 = system10;
	}

	public String getSystem20() {
		return system20;
	}

	public void setSystem20(String system20) {
		this.system20 = system20;
	}

	public String getSystem30() {
		return system30;
	}

	public void setSystem30(String system30) {
		this.system30 = system30;
	}

	public String getSystem40() {
		return system40;
	}

	public void setSystem40(String system40) {
		this.system40 = system40;
	}

	public String getLocation00() {
		return location00;
	}

	public void setLocation00(String location00) {
		this.location00 = location00;
	}

	public String getLocation10() {
		return location10;
	}

	public void setLocation10(String location10) {
		this.location10 = location10;
	}

	public String getLocation20() {
		return location20;
	}

	public void setLocation20(String location20) {
		this.location20 = location20;
	}

	public String getLocation30() {
		return location30;
	}

	public void setLocation30(String location30) {
		this.location30 = location30;
	}

	public String getLocation40() {
		return location40;
	}

	public void setLocation40(String location40) {
		this.location40 = location40;
	}

	public String getRemark100() {
		return remark100;
	}

	public void setRemark100(String remark100) {
		this.remark100 = remark100;
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

	public String getRemark300() {
		return remark300;
	}

	public void setRemark300(String remark300) {
		this.remark300 = remark300;
	}

	public String getRemark310() {
		return remark310;
	}

	public void setRemark310(String remark310) {
		this.remark310 = remark310;
	}

	public String getRemark320() {
		return remark320;
	}

	public void setRemark320(String remark320) {
		this.remark320 = remark320;
	}

	public String getRemark330() {
		return remark330;
	}

	public void setRemark330(String remark330) {
		this.remark330 = remark330;
	}

	public String getRemark340() {
		return remark340;
	}

	public void setRemark340(String remark340) {
		this.remark340 = remark340;
	}

	public String getRemark400() {
		return remark400;
	}

	public void setRemark400(String remark400) {
		this.remark400 = remark400;
	}

	public String getRemark410() {
		return remark410;
	}

	public void setRemark410(String remark410) {
		this.remark410 = remark410;
	}

	public String getRemark420() {
		return remark420;
	}

	public void setRemark420(String remark420) {
		this.remark420 = remark420;
	}

	public String getRemark430() {
		return remark430;
	}

	public void setRemark430(String remark430) {
		this.remark430 = remark430;
	}

	public String getRemark440() {
		return remark440;
	}

	public void setRemark440(String remark440) {
		this.remark440 = remark440;
	}

	public String getRemark500() {
		return remark500;
	}

	public void setRemark500(String remark500) {
		this.remark500 = remark500;
	}

	public String getRemark510() {
		return remark510;
	}

	public void setRemark510(String remark510) {
		this.remark510 = remark510;
	}

	public String getRemark520() {
		return remark520;
	}

	public void setRemark520(String remark520) {
		this.remark520 = remark520;
	}

	public String getRemark530() {
		return remark530;
	}

	public void setRemark530(String remark530) {
		this.remark530 = remark530;
	}

	public String getRemark540() {
		return remark540;
	}

	public void setRemark540(String remark540) {
		this.remark540 = remark540;
	}

	public String getRemark600() {
		return remark600;
	}

	public void setRemark600(String remark600) {
		this.remark600 = remark600;
	}

	public String getRemark610() {
		return remark610;
	}

	public void setRemark610(String remark610) {
		this.remark610 = remark610;
	}

	public String getRemark620() {
		return remark620;
	}

	public void setRemark620(String remark620) {
		this.remark620 = remark620;
	}

	public String getRemark630() {
		return remark630;
	}

	public void setRemark630(String remark630) {
		this.remark630 = remark630;
	}

	public String getRemark640() {
		return remark640;
	}

	public void setRemark640(String remark640) {
		this.remark640 = remark640;
	}

	public String getRemark700() {
		return remark700;
	}

	public void setRemark700(String remark700) {
		this.remark700 = remark700;
	}

	public String getRemark710() {
		return remark710;
	}

	public void setRemark710(String remark710) {
		this.remark710 = remark710;
	}

	public String getRemark720() {
		return remark720;
	}

	public void setRemark720(String remark720) {
		this.remark720 = remark720;
	}

	public String getRemark730() {
		return remark730;
	}

	public void setRemark730(String remark730) {
		this.remark730 = remark730;
	}

	public String getRemark740() {
		return remark740;
	}

	public void setRemark740(String remark740) {
		this.remark740 = remark740;
	}

	public String getRemark800() {
		return remark800;
	}

	public void setRemark800(String remark800) {
		this.remark800 = remark800;
	}

	public String getRemark810() {
		return remark810;
	}

	public void setRemark810(String remark810) {
		this.remark810 = remark810;
	}

	public String getRemark820() {
		return remark820;
	}

	public void setRemark820(String remark820) {
		this.remark820 = remark820;
	}

	public String getRemark830() {
		return remark830;
	}

	public void setRemark830(String remark830) {
		this.remark830 = remark830;
	}

	public String getRemark840() {
		return remark840;
	}

	public void setRemark840(String remark840) {
		this.remark840 = remark840;
	}

	public String getRemark900() {
		return remark900;
	}

	public void setRemark900(String remark900) {
		this.remark900 = remark900;
	}

	public String getRemark910() {
		return remark910;
	}

	public void setRemark910(String remark910) {
		this.remark910 = remark910;
	}

	public String getRemark920() {
		return remark920;
	}

	public void setRemark920(String remark920) {
		this.remark920 = remark920;
	}

	public String getRemark930() {
		return remark930;
	}

	public void setRemark930(String remark930) {
		this.remark930 = remark930;
	}

	public String getRemark940() {
		return remark940;
	}

	public void setRemark940(String remark940) {
		this.remark940 = remark940;
	}

	public String getRemark1000() {
		return remark1000;
	}

	public void setRemark1000(String remark1000) {
		this.remark1000 = remark1000;
	}

	public String getRemark1010() {
		return remark1010;
	}

	public void setRemark1010(String remark1010) {
		this.remark1010 = remark1010;
	}

	public String getRemark1020() {
		return remark1020;
	}

	public void setRemark1020(String remark1020) {
		this.remark1020 = remark1020;
	}

	public String getRemark1030() {
		return remark1030;
	}

	public void setRemark1030(String remark1030) {
		this.remark1030 = remark1030;
	}

	public String getRemark1040() {
		return remark1040;
	}

	public void setRemark1040(String remark1040) {
		this.remark1040 = remark1040;
	}

	public String getAssetsProducerBySupportId00() {
		return assetsProducerBySupportId00;
	}

	public void setAssetsProducerBySupportId00(String assetsProducerBySupportId00) {
		this.assetsProducerBySupportId00 = assetsProducerBySupportId00;
	}

	public String getAssetsProducerBySupportId10() {
		return assetsProducerBySupportId10;
	}

	public void setAssetsProducerBySupportId10(String assetsProducerBySupportId10) {
		this.assetsProducerBySupportId10 = assetsProducerBySupportId10;
	}

	public String getAssetsProducerBySupportId20() {
		return assetsProducerBySupportId20;
	}

	public void setAssetsProducerBySupportId20(String assetsProducerBySupportId20) {
		this.assetsProducerBySupportId20 = assetsProducerBySupportId20;
	}

	public String getAssetsProducerBySupportId30() {
		return assetsProducerBySupportId30;
	}

	public void setAssetsProducerBySupportId30(String assetsProducerBySupportId30) {
		this.assetsProducerBySupportId30 = assetsProducerBySupportId30;
	}

	public String getAssetsProducerBySupportId40() {
		return assetsProducerBySupportId40;
	}

	public void setAssetsProducerBySupportId40(String assetsProducerBySupportId40) {
		this.assetsProducerBySupportId40 = assetsProducerBySupportId40;
	}

	public String getAssetsProducerByProduceId00() {
		return assetsProducerByProduceId00;
	}

	public void setAssetsProducerByProduceId00(String assetsProducerByProduceId00) {
		this.assetsProducerByProduceId00 = assetsProducerByProduceId00;
	}

	public String getAssetsProducerByProduceId10() {
		return assetsProducerByProduceId10;
	}

	public void setAssetsProducerByProduceId10(String assetsProducerByProduceId10) {
		this.assetsProducerByProduceId10 = assetsProducerByProduceId10;
	}

	public String getAssetsProducerByProduceId20() {
		return assetsProducerByProduceId20;
	}

	public void setAssetsProducerByProduceId20(String assetsProducerByProduceId20) {
		this.assetsProducerByProduceId20 = assetsProducerByProduceId20;
	}

	public String getAssetsProducerByProduceId30() {
		return assetsProducerByProduceId30;
	}

	public void setAssetsProducerByProduceId30(String assetsProducerByProduceId30) {
		this.assetsProducerByProduceId30 = assetsProducerByProduceId30;
	}

	public String getAssetsProducerByProduceId40() {
		return assetsProducerByProduceId40;
	}

	public void setAssetsProducerByProduceId40(String assetsProducerByProduceId40) {
		this.assetsProducerByProduceId40 = assetsProducerByProduceId40;
	}

	public String getUsersByUsersId00() {
		return usersByUsersId00;
	}

	public void setUsersByUsersId00(String usersByUsersId00) {
		this.usersByUsersId00 = usersByUsersId00;
	}

	public String getUsersByUsersId10() {
		return usersByUsersId10;
	}

	public void setUsersByUsersId10(String usersByUsersId10) {
		this.usersByUsersId10 = usersByUsersId10;
	}

	public String getUsersByUsersId20() {
		return usersByUsersId20;
	}

	public void setUsersByUsersId20(String usersByUsersId20) {
		this.usersByUsersId20 = usersByUsersId20;
	}

	public String getUsersByUsersId30() {
		return usersByUsersId30;
	}

	public void setUsersByUsersId30(String usersByUsersId30) {
		this.usersByUsersId30 = usersByUsersId30;
	}

	public String getUsersByUsersId40() {
		return usersByUsersId40;
	}

	public void setUsersByUsersId40(String usersByUsersId40) {
		this.usersByUsersId40 = usersByUsersId40;
	}

	public String getUsersByChargeId00() {
		return usersByChargeId00;
	}

	public void setUsersByChargeId00(String usersByChargeId00) {
		this.usersByChargeId00 = usersByChargeId00;
	}

	public String getUsersByChargeId10() {
		return usersByChargeId10;
	}

	public void setUsersByChargeId10(String usersByChargeId10) {
		this.usersByChargeId10 = usersByChargeId10;
	}

	public String getUsersByChargeId20() {
		return usersByChargeId20;
	}

	public void setUsersByChargeId20(String usersByChargeId20) {
		this.usersByChargeId20 = usersByChargeId20;
	}

	public String getUsersByChargeId30() {
		return usersByChargeId30;
	}

	public void setUsersByChargeId30(String usersByChargeId30) {
		this.usersByChargeId30 = usersByChargeId30;
	}

	public String getUsersByChargeId40() {
		return usersByChargeId40;
	}

	public void setUsersByChargeId40(String usersByChargeId40) {
		this.usersByChargeId40 = usersByChargeId40;
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

	public String getRemark221() {
		return remark221;
	}

	public void setRemark221(String remark221) {
		this.remark221 = remark221;
	}

	public String getRemark231() {
		return remark231;
	}

	public void setRemark231(String remark231) {
		this.remark231 = remark231;
	}

	public String getRemark241() {
		return remark241;
	}

	public void setRemark241(String remark241) {
		this.remark241 = remark241;
	}

	public String getRemark301() {
		return remark301;
	}

	public void setRemark301(String remark301) {
		this.remark301 = remark301;
	}

	public String getRemark311() {
		return remark311;
	}

	public void setRemark311(String remark311) {
		this.remark311 = remark311;
	}

	public String getRemark321() {
		return remark321;
	}

	public void setRemark321(String remark321) {
		this.remark321 = remark321;
	}

	public String getRemark331() {
		return remark331;
	}

	public void setRemark331(String remark331) {
		this.remark331 = remark331;
	}

	public String getRemark341() {
		return remark341;
	}

	public void setRemark341(String remark341) {
		this.remark341 = remark341;
	}

	public String getRemark401() {
		return remark401;
	}

	public void setRemark401(String remark401) {
		this.remark401 = remark401;
	}

	public String getRemark411() {
		return remark411;
	}

	public void setRemark411(String remark411) {
		this.remark411 = remark411;
	}

	public String getRemark421() {
		return remark421;
	}

	public void setRemark421(String remark421) {
		this.remark421 = remark421;
	}

	public String getRemark431() {
		return remark431;
	}

	public void setRemark431(String remark431) {
		this.remark431 = remark431;
	}

	public String getRemark441() {
		return remark441;
	}

	public void setRemark441(String remark441) {
		this.remark441 = remark441;
	}

	public String getRemark501() {
		return remark501;
	}

	public void setRemark501(String remark501) {
		this.remark501 = remark501;
	}

	public String getRemark511() {
		return remark511;
	}

	public void setRemark511(String remark511) {
		this.remark511 = remark511;
	}

	public String getRemark521() {
		return remark521;
	}

	public void setRemark521(String remark521) {
		this.remark521 = remark521;
	}

	public String getRemark531() {
		return remark531;
	}

	public void setRemark531(String remark531) {
		this.remark531 = remark531;
	}

	public String getRemark541() {
		return remark541;
	}

	public void setRemark541(String remark541) {
		this.remark541 = remark541;
	}

	public String getRemark601() {
		return remark601;
	}

	public void setRemark601(String remark601) {
		this.remark601 = remark601;
	}

	public String getRemark611() {
		return remark611;
	}

	public void setRemark611(String remark611) {
		this.remark611 = remark611;
	}

	public String getRemark621() {
		return remark621;
	}

	public void setRemark621(String remark621) {
		this.remark621 = remark621;
	}

	public String getRemark631() {
		return remark631;
	}

	public void setRemark631(String remark631) {
		this.remark631 = remark631;
	}

	public String getRemark641() {
		return remark641;
	}

	public void setRemark641(String remark641) {
		this.remark641 = remark641;
	}

	public String getRemark701() {
		return remark701;
	}

	public void setRemark701(String remark701) {
		this.remark701 = remark701;
	}

	public String getRemark711() {
		return remark711;
	}

	public void setRemark711(String remark711) {
		this.remark711 = remark711;
	}

	public String getRemark721() {
		return remark721;
	}

	public void setRemark721(String remark721) {
		this.remark721 = remark721;
	}

	public String getRemark731() {
		return remark731;
	}

	public void setRemark731(String remark731) {
		this.remark731 = remark731;
	}

	public String getRemark741() {
		return remark741;
	}

	public void setRemark741(String remark741) {
		this.remark741 = remark741;
	}

	public String getRemark801() {
		return remark801;
	}

	public void setRemark801(String remark801) {
		this.remark801 = remark801;
	}

	public String getRemark811() {
		return remark811;
	}

	public void setRemark811(String remark811) {
		this.remark811 = remark811;
	}

	public String getRemark821() {
		return remark821;
	}

	public void setRemark821(String remark821) {
		this.remark821 = remark821;
	}

	public String getRemark831() {
		return remark831;
	}

	public void setRemark831(String remark831) {
		this.remark831 = remark831;
	}

	public String getRemark841() {
		return remark841;
	}

	public void setRemark841(String remark841) {
		this.remark841 = remark841;
	}

	public String getRemark901() {
		return remark901;
	}

	public void setRemark901(String remark901) {
		this.remark901 = remark901;
	}

	public String getRemark911() {
		return remark911;
	}

	public void setRemark911(String remark911) {
		this.remark911 = remark911;
	}

	public String getRemark921() {
		return remark921;
	}

	public void setRemark921(String remark921) {
		this.remark921 = remark921;
	}

	public String getRemark931() {
		return remark931;
	}

	public void setRemark931(String remark931) {
		this.remark931 = remark931;
	}

	public String getRemark941() {
		return remark941;
	}

	public void setRemark941(String remark941) {
		this.remark941 = remark941;
	}

	public String getRemark1001() {
		return remark1001;
	}

	public void setRemark1001(String remark1001) {
		this.remark1001 = remark1001;
	}

	public String getRemark1011() {
		return remark1011;
	}

	public void setRemark1011(String remark1011) {
		this.remark1011 = remark1011;
	}

	public String getRemark1021() {
		return remark1021;
	}

	public void setRemark1021(String remark1021) {
		this.remark1021 = remark1021;
	}

	public String getRemark1031() {
		return remark1031;
	}

	public void setRemark1031(String remark1031) {
		this.remark1031 = remark1031;
	}

	public String getRemark1041() {
		return remark1041;
	}

	public void setRemark1041(String remark1041) {
		this.remark1041 = remark1041;
	}

	public String getRemark1101() {
		return remark1101;
	}

	public void setRemark1101(String remark1101) {
		this.remark1101 = remark1101;
	}

	public String getRemark1111() {
		return remark1111;
	}

	public void setRemark1111(String remark1111) {
		this.remark1111 = remark1111;
	}

	public String getRemark1121() {
		return remark1121;
	}

	public void setRemark1121(String remark1121) {
		this.remark1121 = remark1121;
	}

	public String getRemark1131() {
		return remark1131;
	}

	public void setRemark1131(String remark1131) {
		this.remark1131 = remark1131;
	}

	public String getRemark1141() {
		return remark1141;
	}

	public void setRemark1141(String remark1141) {
		this.remark1141 = remark1141;
	}

	public String getRemark1201() {
		return remark1201;
	}

	public void setRemark1201(String remark1201) {
		this.remark1201 = remark1201;
	}

	public String getRemark1211() {
		return remark1211;
	}

	public void setRemark1211(String remark1211) {
		this.remark1211 = remark1211;
	}

	public String getRemark1221() {
		return remark1221;
	}

	public void setRemark1221(String remark1221) {
		this.remark1221 = remark1221;
	}

	public String getRemark1231() {
		return remark1231;
	}

	public void setRemark1231(String remark1231) {
		this.remark1231 = remark1231;
	}

	public String getRemark1241() {
		return remark1241;
	}

	public void setRemark1241(String remark1241) {
		this.remark1241 = remark1241;
	}

	public String getRemark1301() {
		return remark1301;
	}

	public void setRemark1301(String remark1301) {
		this.remark1301 = remark1301;
	}

	public String getRemark1311() {
		return remark1311;
	}

	public void setRemark1311(String remark1311) {
		this.remark1311 = remark1311;
	}

	public String getRemark1321() {
		return remark1321;
	}

	public void setRemark1321(String remark1321) {
		this.remark1321 = remark1321;
	}

	public String getRemark1331() {
		return remark1331;
	}

	public void setRemark1331(String remark1331) {
		this.remark1331 = remark1331;
	}

	public String getRemark1341() {
		return remark1341;
	}

	public void setRemark1341(String remark1341) {
		this.remark1341 = remark1341;
	}

	public String getRemark1401() {
		return remark1401;
	}

	public void setRemark1401(String remark1401) {
		this.remark1401 = remark1401;
	}

	public String getRemark1411() {
		return remark1411;
	}

	public void setRemark1411(String remark1411) {
		this.remark1411 = remark1411;
	}

	public String getRemark1421() {
		return remark1421;
	}

	public void setRemark1421(String remark1421) {
		this.remark1421 = remark1421;
	}

	public String getRemark1431() {
		return remark1431;
	}

	public void setRemark1431(String remark1431) {
		this.remark1431 = remark1431;
	}

	public String getRemark1441() {
		return remark1441;
	}

	public void setRemark1441(String remark1441) {
		this.remark1441 = remark1441;
	}

	public String getRemark1501() {
		return remark1501;
	}

	public void setRemark1501(String remark1501) {
		this.remark1501 = remark1501;
	}

	public String getRemark1511() {
		return remark1511;
	}

	public void setRemark1511(String remark1511) {
		this.remark1511 = remark1511;
	}

	public String getRemark1521() {
		return remark1521;
	}

	public void setRemark1521(String remark1521) {
		this.remark1521 = remark1521;
	}

	public String getRemark1531() {
		return remark1531;
	}

	public void setRemark1531(String remark1531) {
		this.remark1531 = remark1531;
	}

	public String getRemark1541() {
		return remark1541;
	}

	public void setRemark1541(String remark1541) {
		this.remark1541 = remark1541;
	}

	public String getRemark1601() {
		return remark1601;
	}

	public void setRemark1601(String remark1601) {
		this.remark1601 = remark1601;
	}

	public String getRemark1611() {
		return remark1611;
	}

	public void setRemark1611(String remark1611) {
		this.remark1611 = remark1611;
	}

	public String getRemark1621() {
		return remark1621;
	}

	public void setRemark1621(String remark1621) {
		this.remark1621 = remark1621;
	}

	public String getRemark1631() {
		return remark1631;
	}

	public void setRemark1631(String remark1631) {
		this.remark1631 = remark1631;
	}

	public String getRemark1641() {
		return remark1641;
	}

	public void setRemark1641(String remark1641) {
		this.remark1641 = remark1641;
	}

	public String getRemark1701() {
		return remark1701;
	}

	public void setRemark1701(String remark1701) {
		this.remark1701 = remark1701;
	}

	public String getRemark1711() {
		return remark1711;
	}

	public void setRemark1711(String remark1711) {
		this.remark1711 = remark1711;
	}

	public String getRemark1721() {
		return remark1721;
	}

	public void setRemark1721(String remark1721) {
		this.remark1721 = remark1721;
	}

	public String getRemark1731() {
		return remark1731;
	}

	public void setRemark1731(String remark1731) {
		this.remark1731 = remark1731;
	}

	public String getRemark1741() {
		return remark1741;
	}

	public void setRemark1741(String remark1741) {
		this.remark1741 = remark1741;
	}

	public String getRemark1801() {
		return remark1801;
	}

	public void setRemark1801(String remark1801) {
		this.remark1801 = remark1801;
	}

	public String getRemark1811() {
		return remark1811;
	}

	public void setRemark1811(String remark1811) {
		this.remark1811 = remark1811;
	}

	public String getRemark1821() {
		return remark1821;
	}

	public void setRemark1821(String remark1821) {
		this.remark1821 = remark1821;
	}

	public String getRemark1831() {
		return remark1831;
	}

	public void setRemark1831(String remark1831) {
		this.remark1831 = remark1831;
	}

	public String getRemark1841() {
		return remark1841;
	}

	public void setRemark1841(String remark1841) {
		this.remark1841 = remark1841;
	}

	public String getRemark1901() {
		return remark1901;
	}

	public void setRemark1901(String remark1901) {
		this.remark1901 = remark1901;
	}

	public String getRemark1911() {
		return remark1911;
	}

	public void setRemark1911(String remark1911) {
		this.remark1911 = remark1911;
	}

	public String getRemark1921() {
		return remark1921;
	}

	public void setRemark1921(String remark1921) {
		this.remark1921 = remark1921;
	}

	public String getRemark1931() {
		return remark1931;
	}

	public void setRemark1931(String remark1931) {
		this.remark1931 = remark1931;
	}

	public String getRemark1941() {
		return remark1941;
	}

	public void setRemark1941(String remark1941) {
		this.remark1941 = remark1941;
	}

	public String getRemark2001() {
		return remark2001;
	}

	public void setRemark2001(String remark2001) {
		this.remark2001 = remark2001;
	}

	public String getRemark2011() {
		return remark2011;
	}

	public void setRemark2011(String remark2011) {
		this.remark2011 = remark2011;
	}

	public String getRemark2021() {
		return remark2021;
	}

	public void setRemark2021(String remark2021) {
		this.remark2021 = remark2021;
	}

	public String getRemark2031() {
		return remark2031;
	}

	public void setRemark2031(String remark2031) {
		this.remark2031 = remark2031;
	}

	public String getRemark2041() {
		return remark2041;
	}

	public void setRemark2041(String remark2041) {
		this.remark2041 = remark2041;
	}

	private List assetsTypeList;
	private List configrecordList;
	private List assetsConfigList;
	private ConfigRecord configrecord;
	private Integer flag=0;
	private Integer pid = 0;
	private Integer id;
	private int page; 
	private int pageSize = 10;
	private PageBean pageBean; 
	private int assetsTypeId;
	private AssetsType assetsType;
	private AssetsBase assetsBase;
	private String info;
	private String assetsname;
	private String codeId00;
	private String codeId10;
	private String codeId20;
	private String codeId30;
	private String codeId40;
	private String name00;
	private String name10;
	private String name20;
	private String name30;
	private String name40;
	private String model00;
	private String model10;
	private String model20;
	private String model30;
	private String model40;
	private String assetsType00;
	private String assetsType10;
	private String assetsType20;
	private String assetsType30;
	private String assetsType40;
	private String itsmType00;
	private String itsmType10;
	private String itsmType20;
	private String itsmType30;
	private String itsmType40;
	private String assetsState00;
	private String assetsState10;
	private String assetsState20;
	private String assetsState30;
	private String assetsState40;
	private String qualityTime00;
	private String qualityTime10;
	private String qualityTime20;
	private String qualityTime30;
	private String qualityTime40;
	private String price00;
	private String price10;
	private String price20;
	private String price30;
	private String price40;
	private String buyDate00;
	private String buyDate10;
	private String buyDate20;
	private String buyDate30;
	private String buyDate40;
	private String exitfacotryDate00;
	private String exitfacotryDate10;
	private String exitfacotryDate20;
	private String exitfacotryDate30;
	private String exitfacotryDate40;
	private String inDate00;
	private String inDate10;
	private String inDate20;
	private String inDate30;
	private String inDate40;
	private String location00;
	private String location10;
	private String location20;
	private String location30;
	private String location40;
	private String buildlocation00;
	private String buildlocation10;
	private String buildlocation20;
	private String buildlocation30;
	private String buildlocation40;
	private String system00;
	private String system10;
	private String system20;
	private String system30;
	private String system40;
	private String ip00;
	private String ip10;
	private String ip20;
	private String ip30;
	private String ip40;
	private String mac00;
	private String mac10;
	private String mac20;
	private String mac30;
	private String mac40;
	private String devicename00;
	private String devicename10;
	private String devicename20;
	private String devicename30;
	private String devicename40;
	private String remark100;
	private String remark110;
	private String remark120;
	private String remark130;
	private String remark140;
	private String remark200;
	private String remark210;
	private String remark220;
	private String remark230;
	private String remark240;
	private String remark300;
	private String remark310;
	private String remark320;
	private String remark330;
	private String remark340;
	private String remark400;
	private String remark410;
	private String remark420;
	private String remark430;
	private String remark440;
	private String remark500;
	private String remark510;
	private String remark520;
	private String remark530;
	private String remark540;
	private String remark600;
	private String remark610;
	private String remark620;
	private String remark630;
	private String remark640;
	private String remark700;
	private String remark710;
	private String remark720;
	private String remark730;
	private String remark740;
	private String remark800;
	private String remark810;
	private String remark820;
	private String remark830;
	private String remark840;
	private String remark900;
	private String remark910;
	private String remark920;
	private String remark930;
	private String remark940;
	private String remark1000;
	private String remark1010;
	private String remark1020;
	private String remark1030;
	private String remark1040;
	private String remark1100;
	private String remark1110;
	private String remark1120;
	private String remark1130;
	private String remark1140;
	private String remark1200;
	private String remark1210;
	private String remark1220;
	private String remark1230;
	private String remark1240;
	private String remark1300;
	private String remark1310;
	private String remark1320;
	private String remark1330;
	private String remark1340;
	private String remark1400;
	private String remark1410;
	private String remark1420;
	private String remark1430;
	private String remark1440;
	private String remark1500;
	private String remark1510;
	private String remark1520;
	private String remark1530;
	private String remark1540;
	private String remark1600;
	private String remark1610;
	private String remark1620;
	private String remark1630;
	private String remark1640;
	private String remark1700;
	private String remark1710;
	private String remark1720;
	private String remark1730;
	private String remark1740;
	private String remark1800;
	private String remark1810;
	private String remark1820;
	private String remark1830;
	private String remark1840;
	private String remark1900;
	private String remark1910;
	private String remark1920;
	private String remark1930;
	private String remark1940;
	private String remark2000;
	private String remark2010;
	private String remark2020;
	private String remark2030;
	private String remark2040;
	private String remark2100;
	private String remark2110;
	private String remark2120;
	private String remark2130;
	private String remark2140;
	private String remark2200;
	private String remark2210;
	private String remark2220;
	private String remark2230;
	private String remark2240;
	private String remark2300;
	private String remark2310;
	private String remark2320;
	private String remark2330;
	private String remark2340;
	private String remark2400;
	private String remark2410;
	private String remark2420;
	private String remark2430;
	private String remark2440;
	private String remark2500;
	private String remark2510;
	private String remark2520;
	private String remark2530;
	private String remark2540;
	public String getMac00() {
		return mac00;
	}

	public void setMac00(String mac00) {
		this.mac00 = mac00;
	}

	public String getMac10() {
		return mac10;
	}

	public void setMac10(String mac10) {
		this.mac10 = mac10;
	}

	public String getMac20() {
		return mac20;
	}

	public void setMac20(String mac20) {
		this.mac20 = mac20;
	}

	public String getMac30() {
		return mac30;
	}

	public void setMac30(String mac30) {
		this.mac30 = mac30;
	}

	public String getMac40() {
		return mac40;
	}

	public void setMac40(String mac40) {
		this.mac40 = mac40;
	}

	public String getDevicename00() {
		return devicename00;
	}

	public void setDevicename00(String devicename00) {
		this.devicename00 = devicename00;
	}

	public String getDevicename10() {
		return devicename10;
	}

	public void setDevicename10(String devicename10) {
		this.devicename10 = devicename10;
	}

	public String getDevicename20() {
		return devicename20;
	}

	public void setDevicename20(String devicename20) {
		this.devicename20 = devicename20;
	}

	public String getDevicename30() {
		return devicename30;
	}

	public void setDevicename30(String devicename30) {
		this.devicename30 = devicename30;
	}

	public String getDevicename40() {
		return devicename40;
	}

	public void setDevicename40(String devicename40) {
		this.devicename40 = devicename40;
	}

	public String getRemark1100() {
		return remark1100;
	}

	public void setRemark1100(String remark1100) {
		this.remark1100 = remark1100;
	}

	public String getRemark1110() {
		return remark1110;
	}

	public void setRemark1110(String remark1110) {
		this.remark1110 = remark1110;
	}

	public String getRemark1120() {
		return remark1120;
	}

	public void setRemark1120(String remark1120) {
		this.remark1120 = remark1120;
	}

	public String getRemark1130() {
		return remark1130;
	}

	public void setRemark1130(String remark1130) {
		this.remark1130 = remark1130;
	}

	public String getRemark1140() {
		return remark1140;
	}

	public void setRemark1140(String remark1140) {
		this.remark1140 = remark1140;
	}

	public String getRemark1200() {
		return remark1200;
	}

	public void setRemark1200(String remark1200) {
		this.remark1200 = remark1200;
	}

	public String getRemark1210() {
		return remark1210;
	}

	public void setRemark1210(String remark1210) {
		this.remark1210 = remark1210;
	}

	public String getRemark1220() {
		return remark1220;
	}

	public void setRemark1220(String remark1220) {
		this.remark1220 = remark1220;
	}

	public String getRemark1230() {
		return remark1230;
	}

	public void setRemark1230(String remark1230) {
		this.remark1230 = remark1230;
	}

	public String getRemark1240() {
		return remark1240;
	}

	public void setRemark1240(String remark1240) {
		this.remark1240 = remark1240;
	}

	public String getRemark1300() {
		return remark1300;
	}

	public void setRemark1300(String remark1300) {
		this.remark1300 = remark1300;
	}

	public String getRemark1310() {
		return remark1310;
	}

	public void setRemark1310(String remark1310) {
		this.remark1310 = remark1310;
	}

	public String getRemark1320() {
		return remark1320;
	}

	public void setRemark1320(String remark1320) {
		this.remark1320 = remark1320;
	}

	public String getRemark1330() {
		return remark1330;
	}

	public void setRemark1330(String remark1330) {
		this.remark1330 = remark1330;
	}

	public String getRemark1340() {
		return remark1340;
	}

	public void setRemark1340(String remark1340) {
		this.remark1340 = remark1340;
	}

	public String getRemark1400() {
		return remark1400;
	}

	public void setRemark1400(String remark1400) {
		this.remark1400 = remark1400;
	}

	public String getRemark1410() {
		return remark1410;
	}

	public void setRemark1410(String remark1410) {
		this.remark1410 = remark1410;
	}

	public String getRemark1420() {
		return remark1420;
	}

	public void setRemark1420(String remark1420) {
		this.remark1420 = remark1420;
	}

	public String getRemark1430() {
		return remark1430;
	}

	public void setRemark1430(String remark1430) {
		this.remark1430 = remark1430;
	}

	public String getRemark1440() {
		return remark1440;
	}

	public void setRemark1440(String remark1440) {
		this.remark1440 = remark1440;
	}

	public String getRemark1500() {
		return remark1500;
	}

	public void setRemark1500(String remark1500) {
		this.remark1500 = remark1500;
	}

	public String getRemark1510() {
		return remark1510;
	}

	public void setRemark1510(String remark1510) {
		this.remark1510 = remark1510;
	}

	public String getRemark1520() {
		return remark1520;
	}

	public void setRemark1520(String remark1520) {
		this.remark1520 = remark1520;
	}

	public String getRemark1530() {
		return remark1530;
	}

	public void setRemark1530(String remark1530) {
		this.remark1530 = remark1530;
	}

	public String getRemark1540() {
		return remark1540;
	}

	public void setRemark1540(String remark1540) {
		this.remark1540 = remark1540;
	}

	public String getRemark1600() {
		return remark1600;
	}

	public void setRemark1600(String remark1600) {
		this.remark1600 = remark1600;
	}

	public String getRemark1610() {
		return remark1610;
	}

	public void setRemark1610(String remark1610) {
		this.remark1610 = remark1610;
	}

	public String getRemark1620() {
		return remark1620;
	}

	public void setRemark1620(String remark1620) {
		this.remark1620 = remark1620;
	}

	public String getRemark1630() {
		return remark1630;
	}

	public void setRemark1630(String remark1630) {
		this.remark1630 = remark1630;
	}

	public String getRemark1640() {
		return remark1640;
	}

	public void setRemark1640(String remark1640) {
		this.remark1640 = remark1640;
	}

	public String getRemark1700() {
		return remark1700;
	}

	public void setRemark1700(String remark1700) {
		this.remark1700 = remark1700;
	}

	public String getRemark1710() {
		return remark1710;
	}

	public void setRemark1710(String remark1710) {
		this.remark1710 = remark1710;
	}

	public String getRemark1720() {
		return remark1720;
	}

	public void setRemark1720(String remark1720) {
		this.remark1720 = remark1720;
	}

	public String getRemark1730() {
		return remark1730;
	}

	public void setRemark1730(String remark1730) {
		this.remark1730 = remark1730;
	}

	public String getRemark1740() {
		return remark1740;
	}

	public void setRemark1740(String remark1740) {
		this.remark1740 = remark1740;
	}

	public String getRemark1800() {
		return remark1800;
	}

	public void setRemark1800(String remark1800) {
		this.remark1800 = remark1800;
	}

	public String getRemark1810() {
		return remark1810;
	}

	public void setRemark1810(String remark1810) {
		this.remark1810 = remark1810;
	}

	public String getRemark1820() {
		return remark1820;
	}

	public void setRemark1820(String remark1820) {
		this.remark1820 = remark1820;
	}

	public String getRemark1830() {
		return remark1830;
	}

	public void setRemark1830(String remark1830) {
		this.remark1830 = remark1830;
	}

	public String getRemark1840() {
		return remark1840;
	}

	public void setRemark1840(String remark1840) {
		this.remark1840 = remark1840;
	}

	public String getRemark1900() {
		return remark1900;
	}

	public void setRemark1900(String remark1900) {
		this.remark1900 = remark1900;
	}

	public String getRemark1910() {
		return remark1910;
	}

	public void setRemark1910(String remark1910) {
		this.remark1910 = remark1910;
	}

	public String getRemark1920() {
		return remark1920;
	}

	public void setRemark1920(String remark1920) {
		this.remark1920 = remark1920;
	}

	public String getRemark1930() {
		return remark1930;
	}

	public void setRemark1930(String remark1930) {
		this.remark1930 = remark1930;
	}

	public String getRemark1940() {
		return remark1940;
	}

	public void setRemark1940(String remark1940) {
		this.remark1940 = remark1940;
	}

	public String getRemark2000() {
		return remark2000;
	}

	public void setRemark2000(String remark2000) {
		this.remark2000 = remark2000;
	}

	public String getRemark2010() {
		return remark2010;
	}

	public void setRemark2010(String remark2010) {
		this.remark2010 = remark2010;
	}

	public String getRemark2020() {
		return remark2020;
	}

	public void setRemark2020(String remark2020) {
		this.remark2020 = remark2020;
	}

	public String getRemark2030() {
		return remark2030;
	}

	public void setRemark2030(String remark2030) {
		this.remark2030 = remark2030;
	}

	public String getRemark2040() {
		return remark2040;
	}

	public void setRemark2040(String remark2040) {
		this.remark2040 = remark2040;
	}

	public String getRemark2100() {
		return remark2100;
	}

	public void setRemark2100(String remark2100) {
		this.remark2100 = remark2100;
	}

	public String getRemark2110() {
		return remark2110;
	}

	public void setRemark2110(String remark2110) {
		this.remark2110 = remark2110;
	}

	public String getRemark2120() {
		return remark2120;
	}

	public void setRemark2120(String remark2120) {
		this.remark2120 = remark2120;
	}

	public String getRemark2130() {
		return remark2130;
	}

	public void setRemark2130(String remark2130) {
		this.remark2130 = remark2130;
	}

	public String getRemark2140() {
		return remark2140;
	}

	public void setRemark2140(String remark2140) {
		this.remark2140 = remark2140;
	}

	public String getRemark2200() {
		return remark2200;
	}

	public void setRemark2200(String remark2200) {
		this.remark2200 = remark2200;
	}

	public String getRemark2210() {
		return remark2210;
	}

	public void setRemark2210(String remark2210) {
		this.remark2210 = remark2210;
	}

	public String getRemark2220() {
		return remark2220;
	}

	public void setRemark2220(String remark2220) {
		this.remark2220 = remark2220;
	}

	public String getRemark2230() {
		return remark2230;
	}

	public void setRemark2230(String remark2230) {
		this.remark2230 = remark2230;
	}

	public String getRemark2240() {
		return remark2240;
	}

	public void setRemark2240(String remark2240) {
		this.remark2240 = remark2240;
	}

	public String getRemark2300() {
		return remark2300;
	}

	public void setRemark2300(String remark2300) {
		this.remark2300 = remark2300;
	}

	public String getRemark2310() {
		return remark2310;
	}

	public void setRemark2310(String remark2310) {
		this.remark2310 = remark2310;
	}

	public String getRemark2320() {
		return remark2320;
	}

	public void setRemark2320(String remark2320) {
		this.remark2320 = remark2320;
	}

	public String getRemark2330() {
		return remark2330;
	}

	public void setRemark2330(String remark2330) {
		this.remark2330 = remark2330;
	}

	public String getRemark2340() {
		return remark2340;
	}

	public void setRemark2340(String remark2340) {
		this.remark2340 = remark2340;
	}

	public String getRemark2400() {
		return remark2400;
	}

	public void setRemark2400(String remark2400) {
		this.remark2400 = remark2400;
	}

	public String getRemark2410() {
		return remark2410;
	}

	public void setRemark2410(String remark2410) {
		this.remark2410 = remark2410;
	}

	public String getRemark2420() {
		return remark2420;
	}

	public void setRemark2420(String remark2420) {
		this.remark2420 = remark2420;
	}

	public String getRemark2430() {
		return remark2430;
	}

	public void setRemark2430(String remark2430) {
		this.remark2430 = remark2430;
	}

	public String getRemark2440() {
		return remark2440;
	}

	public void setRemark2440(String remark2440) {
		this.remark2440 = remark2440;
	}

	public String getRemark2500() {
		return remark2500;
	}

	public void setRemark2500(String remark2500) {
		this.remark2500 = remark2500;
	}

	public String getRemark2510() {
		return remark2510;
	}

	public void setRemark2510(String remark2510) {
		this.remark2510 = remark2510;
	}

	public String getRemark2520() {
		return remark2520;
	}

	public void setRemark2520(String remark2520) {
		this.remark2520 = remark2520;
	}

	public String getRemark2530() {
		return remark2530;
	}

	public void setRemark2530(String remark2530) {
		this.remark2530 = remark2530;
	}

	public String getRemark2540() {
		return remark2540;
	}

	public void setRemark2540(String remark2540) {
		this.remark2540 = remark2540;
	}

	public String getRemark2600() {
		return remark2600;
	}

	public void setRemark2600(String remark2600) {
		this.remark2600 = remark2600;
	}

	public String getRemark2610() {
		return remark2610;
	}

	public void setRemark2610(String remark2610) {
		this.remark2610 = remark2610;
	}

	public String getRemark2620() {
		return remark2620;
	}

	public void setRemark2620(String remark2620) {
		this.remark2620 = remark2620;
	}

	public String getRemark2630() {
		return remark2630;
	}

	public void setRemark2630(String remark2630) {
		this.remark2630 = remark2630;
	}

	public String getRemark2640() {
		return remark2640;
	}

	public void setRemark2640(String remark2640) {
		this.remark2640 = remark2640;
	}

	public String getRemark2700() {
		return remark2700;
	}

	public void setRemark2700(String remark2700) {
		this.remark2700 = remark2700;
	}

	public String getRemark2710() {
		return remark2710;
	}

	public void setRemark2710(String remark2710) {
		this.remark2710 = remark2710;
	}

	public String getRemark2720() {
		return remark2720;
	}

	public void setRemark2720(String remark2720) {
		this.remark2720 = remark2720;
	}

	public String getRemark2730() {
		return remark2730;
	}

	public void setRemark2730(String remark2730) {
		this.remark2730 = remark2730;
	}

	public String getRemark2740() {
		return remark2740;
	}

	public void setRemark2740(String remark2740) {
		this.remark2740 = remark2740;
	}

	public String getRemark2800() {
		return remark2800;
	}

	public void setRemark2800(String remark2800) {
		this.remark2800 = remark2800;
	}

	public String getRemark2810() {
		return remark2810;
	}

	public void setRemark2810(String remark2810) {
		this.remark2810 = remark2810;
	}

	public String getRemark2820() {
		return remark2820;
	}

	public void setRemark2820(String remark2820) {
		this.remark2820 = remark2820;
	}

	public String getRemark2830() {
		return remark2830;
	}

	public void setRemark2830(String remark2830) {
		this.remark2830 = remark2830;
	}

	public String getRemark2840() {
		return remark2840;
	}

	public void setRemark2840(String remark2840) {
		this.remark2840 = remark2840;
	}

	public String getRemark2900() {
		return remark2900;
	}

	public void setRemark2900(String remark2900) {
		this.remark2900 = remark2900;
	}

	public String getRemark2910() {
		return remark2910;
	}

	public void setRemark2910(String remark2910) {
		this.remark2910 = remark2910;
	}

	public String getRemark2920() {
		return remark2920;
	}

	public void setRemark2920(String remark2920) {
		this.remark2920 = remark2920;
	}

	public String getRemark2930() {
		return remark2930;
	}

	public void setRemark2930(String remark2930) {
		this.remark2930 = remark2930;
	}

	public String getRemark2940() {
		return remark2940;
	}

	public void setRemark2940(String remark2940) {
		this.remark2940 = remark2940;
	}

	public String getRemark3000() {
		return remark3000;
	}

	public void setRemark3000(String remark3000) {
		this.remark3000 = remark3000;
	}

	public String getRemark3010() {
		return remark3010;
	}

	public void setRemark3010(String remark3010) {
		this.remark3010 = remark3010;
	}

	public String getRemark3020() {
		return remark3020;
	}

	public void setRemark3020(String remark3020) {
		this.remark3020 = remark3020;
	}

	public String getRemark3030() {
		return remark3030;
	}

	public void setRemark3030(String remark3030) {
		this.remark3030 = remark3030;
	}

	public String getRemark3040() {
		return remark3040;
	}

	public void setRemark3040(String remark3040) {
		this.remark3040 = remark3040;
	}

	private String remark2600;
	private String remark2610;
	private String remark2620;
	private String remark2630;
	private String remark2640;
	private String remark2700;
	private String remark2710;
	private String remark2720;
	private String remark2730;
	private String remark2740;
	private String remark2800;
	private String remark2810;
	private String remark2820;
	private String remark2830;
	private String remark2840;
	private String remark2900;
	private String remark2910;
	private String remark2920;
	private String remark2930;
	private String remark2940;
	private String remark3000;
	private String remark3010;
	private String remark3020;
	private String remark3030;
	private String remark3040;
	
	
	
	private String assetsProducerBySupportId00;
	private String assetsProducerBySupportId10;
	private String assetsProducerBySupportId20;
	private String assetsProducerBySupportId30;
	private String assetsProducerBySupportId40;
	private String assetsProducerByProduceId00;
	private String assetsProducerByProduceId10;
	private String assetsProducerByProduceId20;
	private String assetsProducerByProduceId30;
	private String assetsProducerByProduceId40;
	private String usersByChargeId00;
	private String usersByChargeId10;
	private String usersByChargeId20;
	private String usersByChargeId30;
	private String usersByChargeId40;
	private String usersByChargeIddepartment00;
	private String usersByChargeIddepartment10;
	private String usersByChargeIddepartment20;
	private String usersByChargeIddepartment30;
	private String usersByChargeIddepartment40;
	private String usersByUsersId00;
	private String usersByUsersId10;
	private String usersByUsersId20;
	private String usersByUsersId30;
	private String usersByUsersId40;
	private String usersByUsersIddepartment00;
	private String usersByUsersIddepartment10;
	private String usersByUsersIddepartment20;
	private String usersByUsersIddepartment30;
	private String usersByUsersIddepartment40;
	
	
	private String remark101;
	private String remark111;
	private String remark121;
	private String remark131;
	private String remark141;
	private String remark201;
	private String remark211;
	private String remark221;
	private String remark231;
	private String remark241;
	private String remark301;
	private String remark311;
	private String remark321;
	private String remark331;
	private String remark341;
	private String remark401;
	private String remark411;
	private String remark421;
	private String remark431;
	private String remark441;
	private String remark501;
	private String remark511;
	private String remark521;
	private String remark531;
	private String remark541;
	private String remark601;
	private String remark611;
	private String remark621;
	private String remark631;
	private String remark641;
	private String remark701;
	private String remark711;
	private String remark721;
	private String remark731;
	private String remark741;
	private String remark801;
	private String remark811;
	private String remark821;
	private String remark831;
	private String remark841;
	private String remark901;
	private String remark911;
	private String remark921;
	private String remark931;
	private String remark941;
	private String remark1001;
	private String remark1011;
	private String remark1021;
	private String remark1031;
	private String remark1041;
	private String remark1101;
	private String remark1111;
	private String remark1121;
	private String remark1131;
	private String remark1141;
	private String remark1201;
	private String remark1211;
	private String remark1221;
	private String remark1231;
	private String remark1241;
	private String remark1301;
	private String remark1311;
	private String remark1321;
	private String remark1331;
	private String remark1341;
	private String remark1401;
	private String remark1411;
	private String remark1421;
	private String remark1431;
	private String remark1441;
	private String remark1501;
	private String remark1511;
	private String remark1521;
	private String remark1531;
	private String remark1541;
	private String remark1601;
	private String remark1611;
	private String remark1621;
	private String remark1631;
	private String remark1641;
	private String remark1701;
	private String remark1711;
	private String remark1721;
	private String remark1731;
	private String remark1741;
	private String remark1801;
	private String remark1811;
	private String remark1821;
	private String remark1831;
	private String remark1841;
	private String remark1901;
	private String remark1911;
	private String remark1921;
	private String remark1931;
	private String remark1941;
	private String remark2001;
	private String remark2011;
	private String remark2021;
	private String remark2031;
	private String remark2041;
	private AssetsConfig assetsConfig;
	
	public String getItsmType00() {
		return itsmType00;
	}

	public void setItsmType00(String itsmType00) {
		this.itsmType00 = itsmType00;
	}

	public String getItsmType10() {
		return itsmType10;
	}

	public void setItsmType10(String itsmType10) {
		this.itsmType10 = itsmType10;
	}

	public String getItsmType20() {
		return itsmType20;
	}

	public void setItsmType20(String itsmType20) {
		this.itsmType20 = itsmType20;
	}

	public String getItsmType30() {
		return itsmType30;
	}

	public void setItsmType30(String itsmType30) {
		this.itsmType30 = itsmType30;
	}

	public String getItsmType40() {
		return itsmType40;
	}

	public void setItsmType40(String itsmType40) {
		this.itsmType40 = itsmType40;
	}
	public AssetsConfig getAssetsConfig() {
		return assetsConfig;
	}

	public void setAssetsConfig(AssetsConfig assetsConfig) {
		this.assetsConfig = assetsConfig;
	}

	public AssetsBase getAssetsBase() {
		return assetsBase;
	}

	public void setAssetsBase(AssetsBase assetsBase) {
		this.assetsBase = assetsBase;
	}

	public AssetsType getAssetsType() {
		return assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public int getAssetsTypeId() {
		return assetsTypeId;
	}

	public void setAssetsTypeId(int assetsTypeId) {
		this.assetsTypeId = assetsTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List getAssetsTypeList() {
		return assetsTypeList;
	}

	public void setAssetsTypeList(List assetsTypeList) {
		this.assetsTypeList = assetsTypeList;
	}

	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}

	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String main() throws Exception {
		return "success";
	}
	
	public String maininfo() throws Exception{
		return "success";
	}
	
	public String top() throws Exception {
		return "success";
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String list() throws Exception {
		String queryString="from AssetsConfig where assets_Type_id is null and flag='0'";
		assetsConfigList=assetsConfigService.findbysql(queryString);
		if(assetsConfigList.size()!=0){
			ActionContext.getContext().getSession().put("assetsConfigList", assetsConfigList);
			return "update";
		}else{
			return "success";
		}
		
	}
	
	public String listinfo() throws Exception{
 		String queryString="from AssetsConfig where assets_Type_id='"+id+"' and flag='1'";
		assetsType=assetsTypeService.findById(id);
		assetsConfigList=assetsConfigService.findbysql(queryString);
		if(assetsConfigList.size()!=0){
			ActionContext.getContext().getSession().put("assetsConfigList", assetsConfigList);
			return "updateinfo";
		}else{
			return "success";
		}
	}
	
	public String savebase() throws Exception{
 		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(codeId20);
		assetsConfig.setConfigColumnName(codeId30);
		assetsConfig.setFlag(0);
		if(codeId10=="on"||codeId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(codeId40);
		assetsConfigService.save(assetsConfig);
 		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(name20);
		assetsConfig.setConfigColumnName(name30);
		assetsConfig.setFlag(0);
		if(name10=="on"||name10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(name40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(model20);
		assetsConfig.setConfigColumnName(model30);
		assetsConfig.setFlag(0);
		if(model10=="on"||model10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(model40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(assetsType20);
		assetsConfig.setConfigColumnName(assetsType30);
		assetsConfig.setFlag(0);
		if(assetsType10=="on"||assetsType10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsType40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(itsmType20);
		assetsConfig.setConfigColumnName(itsmType30);
		assetsConfig.setFlag(0);
		if(itsmType10=="on"||itsmType10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(itsmType40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(assetsState20);
		assetsConfig.setConfigColumnName(assetsState30);
		assetsConfig.setFlag(0);
		if(assetsState10=="on"||assetsState10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsState40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(qualityTime20);
		assetsConfig.setConfigColumnName(qualityTime30);
		assetsConfig.setFlag(0);
		if(qualityTime10=="on"||qualityTime10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(qualityTime40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(price20);
		assetsConfig.setConfigColumnName(price30);
		assetsConfig.setFlag(0);
		if(price10=="on"||price10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(price40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(buyDate20);
		assetsConfig.setConfigColumnName(buyDate30);
		assetsConfig.setFlag(0);
		if(buyDate10=="on"||buyDate10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(buyDate40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(exitfacotryDate20);
		assetsConfig.setConfigColumnName(exitfacotryDate30);
		assetsConfig.setFlag(0);
		if(exitfacotryDate10=="on"||exitfacotryDate10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(exitfacotryDate40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(inDate20);
		assetsConfig.setConfigColumnName(inDate30);
		assetsConfig.setFlag(0);
		if(inDate10=="on"||inDate10!=null)
		{assetsConfig.setIschoose(1);
		}else
		{assetsConfig.setIschoose(0);}
		assetsConfig.setConfigStats(inDate40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(location20);
		assetsConfig.setConfigColumnName(location30);
		assetsConfig.setFlag(0);
		if(location10=="on"||location10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(location40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(buildlocation20);
		assetsConfig.setConfigColumnName(buildlocation30);
		assetsConfig.setFlag(0);
		if(buildlocation10=="on"||buildlocation10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(buildlocation40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(system20);
		assetsConfig.setConfigColumnName(system30);
		assetsConfig.setFlag(0);
		if(system10=="on"||system10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(system40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(ip20);
		assetsConfig.setConfigColumnName(ip30);
		assetsConfig.setFlag(0);
		if(ip10=="on"||ip10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(ip40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(mac20);
		assetsConfig.setConfigColumnName(mac30);
		assetsConfig.setFlag(0);
		if(mac10=="on"||mac10!=null)
		{
		assetsConfig.setIschoose(1);
		}else
		{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(mac40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(devicename20);
		assetsConfig.setConfigColumnName(devicename30);
		assetsConfig.setFlag(0);
		if(devicename10=="on"||devicename10!=null)
		{
		assetsConfig.setIschoose(1);
		}else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(devicename40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark120);
		assetsConfig.setConfigColumnName(remark130);
		assetsConfig.setFlag(0);
		if(remark110=="on"||remark110!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark140);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark220);
		assetsConfig.setConfigColumnName(remark230);
		assetsConfig.setFlag(0);
		if(remark210=="on"||remark210!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark240);
		assetsConfigService.save(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark320);
		assetsConfig.setConfigColumnName(remark330);
		assetsConfig.setFlag(0);
		if(remark310=="on"||remark310!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark340);
		assetsConfigService.save(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark420);
		assetsConfig.setConfigColumnName(remark430);
		assetsConfig.setFlag(0);
		if(remark410=="on"||remark410!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark440);
		assetsConfigService.save(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark520);
		assetsConfig.setConfigColumnName(remark530);
		assetsConfig.setFlag(0);
		if(remark510=="on"||remark510!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark540);
		assetsConfigService.save(assetsConfig);
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark620);
		assetsConfig.setConfigColumnName(remark630);
		assetsConfig.setFlag(0);
		if(remark610=="on"||remark610!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark640);
		assetsConfigService.save(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark720);
		assetsConfig.setConfigColumnName(remark730);
		assetsConfig.setFlag(0);
		if(remark710=="on"||remark710!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark740);
		assetsConfigService.save(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark820);
		assetsConfig.setConfigColumnName(remark830);
		assetsConfig.setFlag(0);
		if(remark810=="on"||remark810!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark840);
		assetsConfigService.save(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark920);
		assetsConfig.setConfigColumnName(remark930);
		assetsConfig.setFlag(0);
		if(remark910=="on"||remark910!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark940);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1020);
		assetsConfig.setConfigColumnName(remark1030);
		assetsConfig.setFlag(0);
		if(remark1010=="on"||remark1010!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1040);
		assetsConfigService.save(assetsConfig);

		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1120);
		assetsConfig.setConfigColumnName(remark1130);
		assetsConfig.setFlag(0);
		if(remark1110=="on"||remark1110!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1140);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1220);
		assetsConfig.setConfigColumnName(remark1230);
		assetsConfig.setFlag(0);
		if(remark1210=="on"||remark1210!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1240);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1320);
		assetsConfig.setConfigColumnName(remark1330);
		assetsConfig.setFlag(0);
		if(remark1310=="on"||remark1310!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1340);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1420);
		assetsConfig.setConfigColumnName(remark1430);
		assetsConfig.setFlag(0);
		if(remark1410=="on"||remark1410!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1440);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1520);
		assetsConfig.setConfigColumnName(remark1530);
		assetsConfig.setFlag(0);
		if(remark1510=="on"||remark1510!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1540);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1620);
		assetsConfig.setConfigColumnName(remark1630);
		assetsConfig.setFlag(0);
		if(remark1610=="on"||remark1610!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1640);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1720);
		assetsConfig.setConfigColumnName(remark1730);
		assetsConfig.setFlag(0);
		if(remark1710=="on"||remark1710!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1740);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1820);
		assetsConfig.setConfigColumnName(remark1830);
		assetsConfig.setFlag(0);
		if(remark1810=="on"||remark1810!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1840);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark1920);
		assetsConfig.setConfigColumnName(remark1930);
		assetsConfig.setFlag(0);
		if(remark1910=="on"||remark1910!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1940);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2020);
		assetsConfig.setConfigColumnName(remark2030);
		assetsConfig.setFlag(0);
		if(remark2010=="on"||remark2010!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2040);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2120);
		assetsConfig.setConfigColumnName(remark2130);
		assetsConfig.setFlag(0);
		if(remark2110=="on"||remark2110!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2140);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2220);
		assetsConfig.setConfigColumnName(remark2230);
		assetsConfig.setFlag(0);
		if(remark2210=="on"||remark2210!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2240);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2320);
		assetsConfig.setConfigColumnName(remark2330);
		assetsConfig.setFlag(0);
		if(remark2310=="on"||remark2310!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2340);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2420);
		assetsConfig.setConfigColumnName(remark2430);
		assetsConfig.setFlag(0);
		if(remark2410=="on"||remark2410!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2440);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2520);
		assetsConfig.setConfigColumnName(remark2530);
		assetsConfig.setFlag(0);
		if(remark2510=="on"||remark2510!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2540);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2620);
		assetsConfig.setConfigColumnName(remark2630);
		assetsConfig.setFlag(0);
		if(remark2610=="on"||remark2610!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2640);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2720);
		assetsConfig.setConfigColumnName(remark2730);
		assetsConfig.setFlag(0);
		if(remark2710=="on"||remark2710!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2740);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2820);
		assetsConfig.setConfigColumnName(remark2830);
		assetsConfig.setFlag(0);
		if(remark2810=="on"||remark2810!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2840);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark2920);
		assetsConfig.setConfigColumnName(remark2930);
		assetsConfig.setFlag(0);
		if(remark2910=="on"||remark2910!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2940);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(remark3020);
		assetsConfig.setConfigColumnName(remark3030);
		assetsConfig.setFlag(0);
		if(remark3010=="on"||remark3010!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark3040);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(assetsProducerBySupportId20);
		assetsConfig.setConfigColumnName(assetsProducerBySupportId30);
		assetsConfig.setFlag(0);
		if(assetsProducerBySupportId10=="on"||assetsProducerBySupportId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsProducerBySupportId40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(assetsProducerByProduceId20);
		assetsConfig.setConfigColumnName(assetsProducerByProduceId30);
		assetsConfig.setFlag(0);
		if(assetsProducerByProduceId10=="on"||assetsProducerByProduceId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsProducerByProduceId40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(usersByUsersId20);
		assetsConfig.setConfigColumnName(usersByUsersId30);
		assetsConfig.setFlag(0);
		if(usersByUsersId10=="on"||usersByUsersId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByUsersId40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(usersByUsersIddepartment20);
		assetsConfig.setConfigColumnName(usersByUsersIddepartment30);
		assetsConfig.setFlag(0);
		if(usersByUsersIddepartment10=="on"||usersByUsersIddepartment10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByUsersIddepartment40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(usersByChargeId20);
		assetsConfig.setConfigColumnName(usersByChargeId30);
		assetsConfig.setFlag(0);
		if(usersByChargeId10=="on"||usersByChargeId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByChargeId40);
		assetsConfigService.save(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setConfigName(usersByChargeIddepartment20);
		assetsConfig.setConfigColumnName(usersByChargeIddepartment30);
		assetsConfig.setFlag(0);
		if(usersByChargeIddepartment10=="on"||usersByChargeIddepartment10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByChargeIddepartment40);
		assetsConfigService.save(assetsConfig);
		
				
//		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark121);
// 		assetsConfig.setConfigColumnName(remark131);
// 		assetsConfig.setFlag(1);
// 		if(remark111=="on"||remark111!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark141);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark221);
// 		assetsConfig.setConfigColumnName(remark231);
// 		assetsConfig.setFlag(1);
// 		if(remark211=="on"||remark211!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark241);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
//		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark321);
// 		assetsConfig.setConfigColumnName(remark331);
// 		assetsConfig.setFlag(1);
// 		if(remark311=="on"||remark311!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark341);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
//		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark421);
// 		assetsConfig.setConfigColumnName(remark431);
// 		assetsConfig.setFlag(1);
// 		if(remark411=="on"||remark411!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark441);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark521);
// 		assetsConfig.setConfigColumnName(remark531);
// 		assetsConfig.setFlag(1);
// 		if(remark511=="on"||remark511!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark541);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark621);
// 		assetsConfig.setConfigColumnName(remark631);
// 		assetsConfig.setFlag(1);
// 		if(remark611=="on"||remark611!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark641);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark721);
// 		assetsConfig.setConfigColumnName(remark731);
// 		assetsConfig.setFlag(1);
// 		if(remark711=="on"||remark711!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark741);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark821);
// 		assetsConfig.setConfigColumnName(remark831);
// 		assetsConfig.setFlag(1);
// 		if(remark811=="on"||remark811!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark841);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark921);
// 		assetsConfig.setConfigColumnName(remark931);
// 		assetsConfig.setFlag(1);
// 		if(remark911=="on"||remark911!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark941);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1021);
// 		assetsConfig.setConfigColumnName(remark1031);
// 		assetsConfig.setFlag(1);
// 		if(remark1011=="on"||remark1011!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1041);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1121);
// 		assetsConfig.setConfigColumnName(remark1131);
// 		assetsConfig.setFlag(1);
// 		if(remark1111=="on"||remark1111!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1141);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1221);
// 		assetsConfig.setConfigColumnName(remark1231);
// 		assetsConfig.setFlag(1);
// 		if(remark1211=="on"||remark1211!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1241);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1321);
// 		assetsConfig.setConfigColumnName(remark1331);
// 		assetsConfig.setFlag(1);
// 		if(remark1311=="on"||remark1311!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1341);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1421);
// 		assetsConfig.setConfigColumnName(remark1431);
// 		assetsConfig.setFlag(1);
// 		if(remark1411=="on"||remark1411!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1441);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1521);
// 		assetsConfig.setConfigColumnName(remark1531);
// 		assetsConfig.setFlag(1);
// 		if(remark1511=="on"||remark1511!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1541);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1621);
// 		assetsConfig.setConfigColumnName(remark1631);
// 		assetsConfig.setFlag(1);
// 		if(remark1611=="on"||remark1611!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1641);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1721);
// 		assetsConfig.setConfigColumnName(remark1731);
// 		assetsConfig.setFlag(1);
// 		if(remark1711=="on"||remark1711!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1741);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1821);
// 		assetsConfig.setConfigColumnName(remark1831);
// 		assetsConfig.setFlag(1);
// 		if(remark1811=="on"||remark1811!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1841);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark1921);
// 		assetsConfig.setConfigColumnName(remark1931);
// 		assetsConfig.setFlag(1);
// 		if(remark1911=="on"||remark1911!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark1941);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
// 		
// 		assetsConfig=new AssetsConfig();
// 		assetsConfig.setConfigName(remark2021);
// 		assetsConfig.setConfigColumnName(remark2031);
// 		assetsConfig.setFlag(1);
// 		if(remark2011=="on"||remark2011!=null){
// 		    assetsConfig.setIschoose(1);
// 		}else{
// 			assetsConfig.setIschoose(0);
// 		}
// 		assetsConfig.setConfigStats(remark2041);
// 		assetsConfig.setAssetsType(type);
// 		assetsConfigService.save(assetsConfig);
		flag=1;
		return "success";
			
	}
	
	
	public String save() throws Exception{
		assetsType=assetsTypeService.findById(assetsTypeId);
		
		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark121);
 		assetsConfig.setConfigColumnName(remark131);
 		assetsConfig.setFlag(1);
 		if(remark111=="on"||remark111!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark141);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark221);
 		assetsConfig.setConfigColumnName(remark231);
 		assetsConfig.setFlag(1);
 		if(remark211=="on"||remark211!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark241);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark321);
 		assetsConfig.setConfigColumnName(remark331);
 		assetsConfig.setFlag(1);
 		if(remark311=="on"||remark311!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark341);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark421);
 		assetsConfig.setConfigColumnName(remark431);
 		assetsConfig.setFlag(1);
 		if(remark411=="on"||remark411!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark441);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark521);
 		assetsConfig.setConfigColumnName(remark531);
 		assetsConfig.setFlag(1);
 		if(remark511=="on"||remark511!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark541);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark621);
 		assetsConfig.setConfigColumnName(remark631);
 		assetsConfig.setFlag(1);
 		if(remark611=="on"||remark611!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark641);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark721);
 		assetsConfig.setConfigColumnName(remark731);
 		assetsConfig.setFlag(1);
 		if(remark711=="on"||remark711!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark741);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark821);
 		assetsConfig.setConfigColumnName(remark831);
 		assetsConfig.setFlag(1);
 		if(remark811=="on"||remark811!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark841);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark921);
 		assetsConfig.setConfigColumnName(remark931);
 		assetsConfig.setFlag(1);
 		if(remark911=="on"||remark911!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark941);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1021);
 		assetsConfig.setConfigColumnName(remark1031);
 		assetsConfig.setFlag(1);
 		if(remark1011=="on"||remark1011!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1041);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1121);
 		assetsConfig.setConfigColumnName(remark1131);
 		assetsConfig.setFlag(1);
 		if(remark1111=="on"||remark1111!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1141);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1221);
 		assetsConfig.setConfigColumnName(remark1231);
 		assetsConfig.setFlag(1);
 		if(remark1211=="on"||remark1211!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1241);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1321);
 		assetsConfig.setConfigColumnName(remark1331);
 		assetsConfig.setFlag(1);
 		if(remark1311=="on"||remark1311!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1341);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1421);
 		assetsConfig.setConfigColumnName(remark1431);
 		assetsConfig.setFlag(1);
 		if(remark1411=="on"||remark1411!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1441);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1521);
 		assetsConfig.setConfigColumnName(remark1531);
 		assetsConfig.setFlag(1);
 		if(remark1511=="on"||remark1511!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1541);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1621);
 		assetsConfig.setConfigColumnName(remark1631);
 		assetsConfig.setFlag(1);
 		if(remark1611=="on"||remark1611!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1641);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1721);
 		assetsConfig.setConfigColumnName(remark1731);
 		assetsConfig.setFlag(1);
 		if(remark1711=="on"||remark1711!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1741);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1821);
 		assetsConfig.setConfigColumnName(remark1831);
 		assetsConfig.setFlag(1);
 		if(remark1811=="on"||remark1811!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1841);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark1921);
 		assetsConfig.setConfigColumnName(remark1931);
 		assetsConfig.setFlag(1);
 		if(remark1911=="on"||remark1911!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1941);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setConfigName(remark2021);
 		assetsConfig.setConfigColumnName(remark2031);
 		assetsConfig.setFlag(1);
 		if(remark2011=="on"||remark2011!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark2041);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.save(assetsConfig);
		flag=1;
		return "success";
			
	}
	
	public String updateinfo() throws Exception{
		assetsType=assetsTypeService.findById(assetsTypeId);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark101));
 		assetsConfig.setConfigName(remark121);
 		assetsConfig.setConfigColumnName(remark131);
 		assetsConfig.setFlag(1);
 		if(remark111=="on"||remark111!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark141);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark201));
 		assetsConfig.setConfigName(remark221);
 		assetsConfig.setConfigColumnName(remark231);
 		assetsConfig.setFlag(1);
 		if(remark211=="on"||remark211!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark241);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark301));
 		assetsConfig.setConfigName(remark321);
 		assetsConfig.setConfigColumnName(remark331);
 		assetsConfig.setFlag(1);
 		if(remark311=="on"||remark311!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark341);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark401));
 		assetsConfig.setConfigName(remark421);
 		assetsConfig.setConfigColumnName(remark431);
 		assetsConfig.setFlag(1);
 		if(remark411=="on"||remark411!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark441);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark501));
 		assetsConfig.setConfigName(remark521);
 		assetsConfig.setConfigColumnName(remark531);
 		assetsConfig.setFlag(1);
 		if(remark511=="on"||remark511!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark541);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark601));
 		assetsConfig.setConfigName(remark621);
 		assetsConfig.setConfigColumnName(remark631);
 		assetsConfig.setFlag(1);
 		if(remark611=="on"||remark611!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark641);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark701));
 		assetsConfig.setConfigName(remark721);
 		assetsConfig.setConfigColumnName(remark731);
 		assetsConfig.setFlag(1);
 		if(remark711=="on"||remark711!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark741);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark801));
 		assetsConfig.setConfigName(remark821);
 		assetsConfig.setConfigColumnName(remark831);
 		assetsConfig.setFlag(1);
 		if(remark811=="on"||remark811!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark841);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark901));
 		assetsConfig.setConfigName(remark921);
 		assetsConfig.setConfigColumnName(remark931);
 		assetsConfig.setFlag(1);
 		if(remark911=="on"||remark911!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark941);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1001));
 		assetsConfig.setConfigName(remark1021);
 		assetsConfig.setConfigColumnName(remark1031);
 		assetsConfig.setFlag(1);
 		if(remark1011=="on"||remark1011!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1041);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1101));
 		assetsConfig.setConfigName(remark1121);
 		assetsConfig.setConfigColumnName(remark1131);
 		assetsConfig.setFlag(1);
 		if(remark1111=="on"||remark1111!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1141);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1201));
 		assetsConfig.setConfigName(remark1221);
 		assetsConfig.setConfigColumnName(remark1231);
 		assetsConfig.setFlag(1);
 		if(remark1211=="on"||remark1211!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1241);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1301));
 		assetsConfig.setConfigName(remark1321);
 		assetsConfig.setConfigColumnName(remark1331);
 		assetsConfig.setFlag(1);
 		if(remark1311=="on"||remark1311!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1341);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1401));
 		assetsConfig.setConfigName(remark1421);
 		assetsConfig.setConfigColumnName(remark1431);
 		assetsConfig.setFlag(1);
 		if(remark1411=="on"||remark1411!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1441);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1501));
 		assetsConfig.setConfigName(remark1521);
 		assetsConfig.setConfigColumnName(remark1531);
 		assetsConfig.setFlag(1);
 		if(remark1511=="on"||remark1511!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1541);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1601));
 		assetsConfig.setConfigName(remark1621);
 		assetsConfig.setConfigColumnName(remark1631);
 		assetsConfig.setFlag(1);
 		if(remark1611=="on"||remark1611!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1641);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1701));
 		assetsConfig.setConfigName(remark1721);
 		assetsConfig.setConfigColumnName(remark1731);
 		assetsConfig.setFlag(1);
 		if(remark1711=="on"||remark1711!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1741);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1801));
 		assetsConfig.setConfigName(remark1821);
 		assetsConfig.setConfigColumnName(remark1831);
 		assetsConfig.setFlag(1);
 		if(remark1811=="on"||remark1811!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1841);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark1901));
 		assetsConfig.setConfigName(remark1921);
 		assetsConfig.setConfigColumnName(remark1931);
 		assetsConfig.setFlag(1);
 		if(remark1911=="on"||remark1911!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark1941);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
 		
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(remark2001));
 		assetsConfig.setConfigName(remark2021);
 		assetsConfig.setConfigColumnName(remark2031);
 		assetsConfig.setFlag(1);
 		if(remark2011=="on"||remark2011!=null){
 		    assetsConfig.setIschoose(1);
 		}else{
 			assetsConfig.setIschoose(0);
 		}
 		assetsConfig.setConfigStats(remark2041);
 		assetsConfig.setAssetsType(assetsType);
 		assetsConfigService.update(assetsConfig);
		flag=1;
		return "success";
			
	}

	public String update() throws Exception {
 		assetsConfig=new AssetsConfig();
 		assetsConfig.setId(Integer.parseInt(codeId00));
		assetsConfig.setConfigName(codeId20);
		assetsConfig.setConfigColumnName(codeId30);
		assetsConfig.setFlag(0);
		if(codeId10=="on"||codeId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(codeId40);
		assetsConfigService.update(assetsConfig);
 		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(name00));
		assetsConfig.setConfigName(name20);
		assetsConfig.setConfigColumnName(name30);
		assetsConfig.setFlag(0);
		if(name10=="on"||name10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(name40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(model00));
		assetsConfig.setConfigName(model20);
		assetsConfig.setConfigColumnName(model30);
		assetsConfig.setFlag(0);
		if(model10=="on"||model10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(model40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(assetsType00));
		assetsConfig.setConfigName(assetsType20);
		assetsConfig.setConfigColumnName(assetsType30);
		assetsConfig.setFlag(0);
		if(assetsType10=="on"||assetsType10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsType40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(itsmType00));
		assetsConfig.setConfigName(itsmType20);
		assetsConfig.setConfigColumnName(itsmType30);
		assetsConfig.setFlag(0);
		if(itsmType10=="on"||itsmType10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(itsmType40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(assetsState00));
		assetsConfig.setConfigName(assetsState20);
		assetsConfig.setConfigColumnName(assetsState30);
		assetsConfig.setFlag(0);
		if(assetsState10=="on"||assetsState10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsState40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(qualityTime00));
		assetsConfig.setConfigName(qualityTime20);
		assetsConfig.setConfigColumnName(qualityTime30);
		assetsConfig.setFlag(0);
		if(qualityTime10=="on"||qualityTime10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(qualityTime40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(price00));
		assetsConfig.setConfigName(price20);
		assetsConfig.setConfigColumnName(price30);
		assetsConfig.setFlag(0);
		if(price10=="on"||price10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(price40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(buyDate00));
		assetsConfig.setConfigName(buyDate20);
		assetsConfig.setConfigColumnName(buyDate30);
		assetsConfig.setFlag(0);
		if(buyDate10=="on"||buyDate10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(buyDate40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(exitfacotryDate00));
		assetsConfig.setConfigName(exitfacotryDate20);
		assetsConfig.setConfigColumnName(exitfacotryDate30);
		assetsConfig.setFlag(0);
		if(exitfacotryDate10=="on"||exitfacotryDate10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(exitfacotryDate40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(inDate00));
		assetsConfig.setConfigName(inDate20);
		assetsConfig.setConfigColumnName(inDate30);
		assetsConfig.setFlag(0);
		if(inDate10=="on"||inDate10!=null)
		{assetsConfig.setIschoose(1);
		}else
		{assetsConfig.setIschoose(0);}
		assetsConfig.setConfigStats(inDate40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(location00));
		assetsConfig.setConfigName(location20);
		assetsConfig.setConfigColumnName(location30);
		assetsConfig.setFlag(0);
		if(location10=="on"||location10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(location40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(buildlocation00));
		assetsConfig.setConfigName(buildlocation20);
		assetsConfig.setConfigColumnName(buildlocation30);
		assetsConfig.setFlag(0);
		if(buildlocation10=="on"||buildlocation10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(buildlocation40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(system00));
		assetsConfig.setConfigName(system20);
		assetsConfig.setConfigColumnName(system30);
		assetsConfig.setFlag(0);
		if(system10=="on"||system10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(system40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(ip00));
		assetsConfig.setConfigName(ip20);
		assetsConfig.setConfigColumnName(ip30);
		assetsConfig.setFlag(0);
		if(ip10=="on"||ip10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(ip40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(mac00));
		assetsConfig.setConfigName(mac20);
		assetsConfig.setConfigColumnName(mac30);
		assetsConfig.setFlag(0);
		if(mac10=="on"||mac10!=null)
		{
		assetsConfig.setIschoose(1);
		}else
		{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(mac40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(devicename00));
		assetsConfig.setConfigName(devicename20);
		assetsConfig.setConfigColumnName(devicename30);
		assetsConfig.setFlag(0);
		if(devicename10=="on"||devicename10!=null)
		{
		assetsConfig.setIschoose(1);
		}else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(devicename40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark100));
		assetsConfig.setConfigName(remark120);
		assetsConfig.setConfigColumnName(remark130);
		assetsConfig.setFlag(0);
		if(remark110=="on"||remark110!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark140);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark200));
		assetsConfig.setConfigName(remark220);
		assetsConfig.setConfigColumnName(remark230);
		assetsConfig.setFlag(0);
		if(remark210=="on"||remark210!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark240);
		assetsConfigService.update(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark300));
		assetsConfig.setConfigName(remark320);
		assetsConfig.setConfigColumnName(remark330);
		assetsConfig.setFlag(0);
		if(remark310=="on"||remark310!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark340);
		assetsConfigService.update(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark400));
		assetsConfig.setConfigName(remark420);
		assetsConfig.setConfigColumnName(remark430);
		assetsConfig.setFlag(0);
		if(remark410=="on"||remark410!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark440);
		assetsConfigService.update(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark500));
		assetsConfig.setConfigName(remark520);
		assetsConfig.setConfigColumnName(remark530);
		assetsConfig.setFlag(0);
		if(remark510=="on"||remark510!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark540);
		assetsConfigService.update(assetsConfig);
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark600));
		assetsConfig.setConfigName(remark620);
		assetsConfig.setConfigColumnName(remark630);
		assetsConfig.setFlag(0);
		if(remark610=="on"||remark610!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark640);
		assetsConfigService.update(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark700));
		assetsConfig.setConfigName(remark720);
		assetsConfig.setConfigColumnName(remark730);
		assetsConfig.setFlag(0);
		if(remark710=="on"||remark710!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark740);
		assetsConfigService.update(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark800));
		assetsConfig.setConfigName(remark820);
		assetsConfig.setConfigColumnName(remark830);
		assetsConfig.setFlag(0);
		if(remark810=="on"||remark810!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark840);
		assetsConfigService.update(assetsConfig);
	
	
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark900));
		assetsConfig.setConfigName(remark920);
		assetsConfig.setConfigColumnName(remark930);
		assetsConfig.setFlag(0);
		if(remark910=="on"||remark910!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark940);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1000));
		assetsConfig.setConfigName(remark1020);
		assetsConfig.setConfigColumnName(remark1030);
		assetsConfig.setFlag(0);
		if(remark1010=="on"||remark1010!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1040);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1100));
		assetsConfig.setConfigName(remark1120);
		assetsConfig.setConfigColumnName(remark1130);
		assetsConfig.setFlag(0);
		if(remark1110=="on"||remark1110!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1140);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1200));
		assetsConfig.setConfigName(remark1220);
		assetsConfig.setConfigColumnName(remark1230);
		assetsConfig.setFlag(0);
		if(remark1210=="on"||remark1210!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1240);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1300));
		assetsConfig.setConfigName(remark1320);
		assetsConfig.setConfigColumnName(remark1330);
		assetsConfig.setFlag(0);
		if(remark1310=="on"||remark1310!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1340);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1400));
		assetsConfig.setConfigName(remark1420);
		assetsConfig.setConfigColumnName(remark1430);
		assetsConfig.setFlag(0);
		if(remark1410=="on"||remark1410!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1440);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1500));
		assetsConfig.setConfigName(remark1520);
		assetsConfig.setConfigColumnName(remark1530);
		assetsConfig.setFlag(0);
		if(remark1510=="on"||remark1510!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1540);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1600));
		assetsConfig.setConfigName(remark1620);
		assetsConfig.setConfigColumnName(remark1630);
		assetsConfig.setFlag(0);
		if(remark1610=="on"||remark1610!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1640);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1700));
		assetsConfig.setConfigName(remark1720);
		assetsConfig.setConfigColumnName(remark1730);
		assetsConfig.setFlag(0);
		if(remark1710=="on"||remark1710!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1740);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1800));
		assetsConfig.setConfigName(remark1820);
		assetsConfig.setConfigColumnName(remark1830);
		assetsConfig.setFlag(0);
		if(remark1810=="on"||remark1810!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1840);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark1900));
		assetsConfig.setConfigName(remark1920);
		assetsConfig.setConfigColumnName(remark1930);
		assetsConfig.setFlag(0);
		if(remark1910=="on"||remark1910!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark1940);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2000));
		assetsConfig.setConfigName(remark2020);
		assetsConfig.setConfigColumnName(remark2030);
		assetsConfig.setFlag(0);
		if(remark2010=="on"||remark2010!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2040);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2100));
		assetsConfig.setConfigName(remark2120);
		assetsConfig.setConfigColumnName(remark2130);
		assetsConfig.setFlag(0);
		if(remark2110=="on"||remark2110!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2140);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2200));
		assetsConfig.setConfigName(remark2220);
		assetsConfig.setConfigColumnName(remark2230);
		assetsConfig.setFlag(0);
		if(remark2210=="on"||remark2210!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2240);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2300));
		assetsConfig.setConfigName(remark2320);
		assetsConfig.setConfigColumnName(remark2330);
		assetsConfig.setFlag(0);
		if(remark2310=="on"||remark2310!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2340);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2400));
		assetsConfig.setConfigName(remark2420);
		assetsConfig.setConfigColumnName(remark2430);
		assetsConfig.setFlag(0);
		if(remark2410=="on"||remark2410!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2440);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2500));
		assetsConfig.setConfigName(remark2520);
		assetsConfig.setConfigColumnName(remark2530);
		assetsConfig.setFlag(0);
		if(remark2510=="on"||remark2510!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2540);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2600));
		assetsConfig.setConfigName(remark2620);
		assetsConfig.setConfigColumnName(remark2630);
		assetsConfig.setFlag(0);
		if(remark2610=="on"||remark2610!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2640);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2700));
		assetsConfig.setConfigName(remark2720);
		assetsConfig.setConfigColumnName(remark2730);
		assetsConfig.setFlag(0);
		if(remark2710=="on"||remark2710!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2740);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2800));
		assetsConfig.setConfigName(remark2820);
		assetsConfig.setConfigColumnName(remark2830);
		assetsConfig.setFlag(0);
		if(remark2810=="on"||remark2810!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2840);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark2900));
		assetsConfig.setConfigName(remark2920);
		assetsConfig.setConfigColumnName(remark2930);
		assetsConfig.setFlag(0);
		if(remark2910=="on"||remark2910!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark2940);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(remark3000));
		assetsConfig.setConfigName(remark3020);
		assetsConfig.setConfigColumnName(remark3030);
		assetsConfig.setFlag(0);
		if(remark3010=="on"||remark3010!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(remark3040);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(assetsProducerBySupportId00));
		assetsConfig.setConfigName(assetsProducerBySupportId20);
		assetsConfig.setConfigColumnName(assetsProducerBySupportId30);
		assetsConfig.setFlag(0);
		if(assetsProducerBySupportId10=="on"||assetsProducerBySupportId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsProducerBySupportId40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(assetsProducerByProduceId00));
		assetsConfig.setConfigName(assetsProducerByProduceId20);
		assetsConfig.setConfigColumnName(assetsProducerByProduceId30);
		assetsConfig.setFlag(0);
		if(assetsProducerByProduceId10=="on"||assetsProducerByProduceId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(assetsProducerByProduceId40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(usersByUsersId00));
		assetsConfig.setConfigName(usersByUsersId20);
		assetsConfig.setConfigColumnName(usersByUsersId30);
		assetsConfig.setFlag(0);
		if(usersByUsersId10=="on"||usersByUsersId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByUsersId40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(usersByUsersIddepartment00));
		assetsConfig.setConfigName(usersByUsersIddepartment20);
		assetsConfig.setConfigColumnName(usersByUsersIddepartment30);
		assetsConfig.setFlag(0);
		if(usersByUsersIddepartment10=="on"||usersByUsersIddepartment10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByUsersIddepartment40);
		assetsConfigService.update(assetsConfig);
		
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(usersByChargeId00));
		assetsConfig.setConfigName(usersByChargeId20);
		assetsConfig.setConfigColumnName(usersByChargeId30);
		assetsConfig.setFlag(0);
		if(usersByChargeId10=="on"||usersByChargeId10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByChargeId40);
		assetsConfigService.update(assetsConfig);
		
		assetsConfig=new AssetsConfig();
		assetsConfig.setId(Integer.parseInt(usersByChargeIddepartment00));
		assetsConfig.setConfigName(usersByChargeIddepartment20);
		assetsConfig.setConfigColumnName(usersByChargeIddepartment30);
		assetsConfig.setFlag(0);
		if(usersByChargeIddepartment10=="on"||usersByChargeIddepartment10!=null){
			assetsConfig.setIschoose(1);}
		else{
			assetsConfig.setIschoose(0);
		}
		assetsConfig.setConfigStats(usersByChargeIddepartment40);
		assetsConfigService.update(assetsConfig);
		
		
		flag=1;
		return "success";
	}

	public ConfigRecordService getConfigrecordService() {
		return configrecordService;
	}

	public void setConfigrecordService(ConfigRecordService configrecordService) {
		this.configrecordService = configrecordService;
	}

	public List getConfigrecordList() {
		return configrecordList;
	}

	public void setConfigrecordList(List configrecordList) {
		this.configrecordList = configrecordList;
	}

	public ConfigRecord getConfigrecord() {
		return configrecord;
	}

	public void setConfigrecord(ConfigRecord configrecord) {
		this.configrecord = configrecord;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public javax.servlet.http.HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(javax.servlet.http.HttpServletRequest request) {
		this.request = request;
	}


	public AssetsConfigService getAssetsConfigService() {
		return assetsConfigService;
	}

	public void setAssetsConfigService(AssetsConfigService assetsConfigService) {
		this.assetsConfigService = assetsConfigService;
	}
	public List getAssetsConfigList() {
		return assetsConfigList;
	}

	public void setAssetsConfigList(List assetsConfigList) {
		this.assetsConfigList = assetsConfigList;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAssetsname() {
		return assetsname;
	}

	public void setAssetsname(String assetsname) {
		this.assetsname = assetsname;
	}

	public String title()throws Exception{
		title="自定义表单管理";
		return "success";
	}

	public String getUsersByChargeIddepartment00() {
		return usersByChargeIddepartment00;
	}

	public void setUsersByChargeIddepartment00(String usersByChargeIddepartment00) {
		this.usersByChargeIddepartment00 = usersByChargeIddepartment00;
	}

	public String getUsersByChargeIddepartment10() {
		return usersByChargeIddepartment10;
	}

	public void setUsersByChargeIddepartment10(String usersByChargeIddepartment10) {
		this.usersByChargeIddepartment10 = usersByChargeIddepartment10;
	}

	public String getUsersByChargeIddepartment20() {
		return usersByChargeIddepartment20;
	}

	public void setUsersByChargeIddepartment20(String usersByChargeIddepartment20) {
		this.usersByChargeIddepartment20 = usersByChargeIddepartment20;
	}

	public String getUsersByChargeIddepartment30() {
		return usersByChargeIddepartment30;
	}

	public void setUsersByChargeIddepartment30(String usersByChargeIddepartment30) {
		this.usersByChargeIddepartment30 = usersByChargeIddepartment30;
	}

	public String getUsersByChargeIddepartment40() {
		return usersByChargeIddepartment40;
	}

	public void setUsersByChargeIddepartment40(String usersByChargeIddepartment40) {
		this.usersByChargeIddepartment40 = usersByChargeIddepartment40;
	}

	public String getUsersByUsersIddepartment00() {
		return usersByUsersIddepartment00;
	}

	public void setUsersByUsersIddepartment00(String usersByUsersIddepartment00) {
		this.usersByUsersIddepartment00 = usersByUsersIddepartment00;
	}

	public String getUsersByUsersIddepartment10() {
		return usersByUsersIddepartment10;
	}

	public void setUsersByUsersIddepartment10(String usersByUsersIddepartment10) {
		this.usersByUsersIddepartment10 = usersByUsersIddepartment10;
	}

	public String getUsersByUsersIddepartment20() {
		return usersByUsersIddepartment20;
	}

	public void setUsersByUsersIddepartment20(String usersByUsersIddepartment20) {
		this.usersByUsersIddepartment20 = usersByUsersIddepartment20;
	}

	public String getUsersByUsersIddepartment30() {
		return usersByUsersIddepartment30;
	}

	public void setUsersByUsersIddepartment30(String usersByUsersIddepartment30) {
		this.usersByUsersIddepartment30 = usersByUsersIddepartment30;
	}

	public String getUsersByUsersIddepartment40() {
		return usersByUsersIddepartment40;
	}

	public void setUsersByUsersIddepartment40(String usersByUsersIddepartment40) {
		this.usersByUsersIddepartment40 = usersByUsersIddepartment40;
	}
}
