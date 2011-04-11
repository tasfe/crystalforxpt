package com.combanc.itsm.action.systemmanage;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsConfig;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.AssetsConfigService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsStateService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.service.LocationService;
import com.combanc.itsm.service.ProducerService;
import com.combanc.itsm.service.UserService;
import com.combanc.itsm.util.StringUtil;

public class AssetsImportAction extends BaseActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	private List data=new ArrayList();
	private AssetsConfigService assetsConfigService;
	private AssetsService assetsService;
	private AssetsStateService assetsStateService;
	private DepartmentService departmentService;
    private AssetsTypeService assetsTypeService;
    private LocationService locationService;
    private ProducerService producerService;
    private UserService userService;
	private File file;
	private String fileName;
	private File fileuser;
	private String fileNameuser;
	
	public File getFileuser() {
		return fileuser;
	}

	public void setFileuser(File fileuser) {
		this.fileuser = fileuser;
	}

	public String getFileNameuser() {
		return fileNameuser;
	}

	public void setFileNameuser(String fileNameuser) {
		this.fileNameuser = fileNameuser;
	}

	public AssetsConfigService getAssetsConfigService() {
		return assetsConfigService;
	}

	public void setAssetsConfigService(AssetsConfigService assetsConfigService) {
		this.assetsConfigService = assetsConfigService;
	}

	public AssetsStateService getAssetsStateService() {
		return assetsStateService;
	}

	public void setAssetsStateService(AssetsStateService assetsStateService) {
		this.assetsStateService = assetsStateService;
	}
	
    public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public AssetsTypeService getAssetsTypeService() {
		return assetsTypeService;
	}

	public void setAssetsTypeService(AssetsTypeService assetsTypeService) {
		this.assetsTypeService = assetsTypeService;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public ProducerService getProducerService() {
		return producerService;
	}

	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	

	public String AssetsImport() {
		request = ServletActionContext.getRequest();
		int j=0;
		int count=0;
		int one=0;
		List<String> errorList = new ArrayList<String>();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath("/upload");
		errorList.add("导入完成！");
		StringBuffer table=new StringBuffer();
		String []titles=null;
		StringBuffer title=new StringBuffer("<tr style=' line-height:22px; color:#ff0000; font-weight:200; background:#cde9fc; font-size:12px;'><th>序号</th>");
		if (file != null) {
			String targetFileName = StringUtil.generateFileName(fileName);
			File target = new File(targetDirectory, targetFileName);
			try {
				FileUtils.copyFile(file, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Workbook book = Workbook.getWorkbook(new File(targetDirectory+ "\\" + targetFileName));
				Sheet sheet = book.getSheet(0);
				String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
				List lists=assetsConfigService.findbysql(queryString);
				StringBuffer sb=new StringBuffer("insert into assets_base(");
				titles=new String[lists.size()+2];
				titles[0]="资产信息";
				for(int i=0;i<lists.size();i++){
					AssetsConfig config=(AssetsConfig)lists.get(i);
					if(config.getConfigColumnName().equals("codeId")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("code_id,");
					}else if(config.getConfigColumnName().equals("name")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("name,");
					}else if(config.getConfigColumnName().equals("model")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("model,");
					}else if(config.getConfigColumnName().equals("assetsType")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("type_code,");
					}else if(config.getConfigColumnName().equals("itsmType")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("itsmtype,");
					}else if(config.getConfigColumnName().equals("assetsState")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("state,");
					}else if(config.getConfigColumnName().equals("qualityTime")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("quality_time,");
					}else if(config.getConfigColumnName().equals("price")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("price,");
					}else if(config.getConfigColumnName().equals("buyDate")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("buy_date,");
					}else if(config.getConfigColumnName().equals("exitfacotryDate")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("exitfacotry_Date,");
					}else if(config.getConfigColumnName().equals("inDate")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("in_date,");
					}else if(config.getConfigColumnName().equals("location")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("place_id,");
					}else if(config.getConfigColumnName().equals("buildlocation")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("buildlocation,");
					}else if(config.getConfigColumnName().equals("system")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("system,");
					}else if(config.getConfigColumnName().equals("ip")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("ip,");
					}else if(config.getConfigColumnName().equals("mac")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("mac,");
					}else if(config.getConfigColumnName().equals("devicename")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("devicename,");
					}else if(config.getConfigColumnName().equals("remark1")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark1,");
					}else if(config.getConfigColumnName().equals("remark2")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark2,");
					}else if(config.getConfigColumnName().equals("remark3")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark3,");
					}else if(config.getConfigColumnName().equals("remark4")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark4,");
					}else if(config.getConfigColumnName().equals("remark5")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark5,");
					}else if(config.getConfigColumnName().equals("remark6")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark6,");
					}else if(config.getConfigColumnName().equals("remark7")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark7,");
					}else if(config.getConfigColumnName().equals("remark8")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark8,");
					}else if(config.getConfigColumnName().equals("remark9")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark9,");
					}else if(config.getConfigColumnName().equals("remark10")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark10,");
					}else if(config.getConfigColumnName().equals("remark11")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark11,");
					}else if(config.getConfigColumnName().equals("remark12")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark12,");
					}else if(config.getConfigColumnName().equals("remark13")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark13,");
					}else if(config.getConfigColumnName().equals("remark14")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark14,");
					}else if(config.getConfigColumnName().equals("remark15")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark15,");
					}else if(config.getConfigColumnName().equals("remark16")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark16,");
					}else if(config.getConfigColumnName().equals("remark17")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark17,");
					}else if(config.getConfigColumnName().equals("remark18")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark18,");
					}else if(config.getConfigColumnName().equals("remark19")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark19,");
					}else if(config.getConfigColumnName().equals("remark20")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark20,");
					}else if(config.getConfigColumnName().equals("remark21")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark21,");
					}else if(config.getConfigColumnName().equals("remark22")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark22,");
					}else if(config.getConfigColumnName().equals("remark23")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark23,");
					}else if(config.getConfigColumnName().equals("remark24")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark24,");
					}else if(config.getConfigColumnName().equals("remark25")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark25,");
					}else if(config.getConfigColumnName().equals("remark26")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark26,");
					}else if(config.getConfigColumnName().equals("remark27")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark27,");
					}else if(config.getConfigColumnName().equals("remark28")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark28,");
					}else if(config.getConfigColumnName().equals("remark29")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark29,");
					}else if(config.getConfigColumnName().equals("remark30")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("remark30,");
					}else if(config.getConfigColumnName().equals("assetsProducerBySupportId")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("support_id,");
					}else if(config.getConfigColumnName().equals("assetsProducerByProduceId")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("produce_id,");
					}else if(config.getConfigColumnName().equals("department")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("department_code,");
					}else if(config.getConfigColumnName().equals("usersByUsersId")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("users_id,");
					}else if(config.getConfigColumnName().equals("usersByChargeId")){
						title.append("<th>"+config.getConfigName()+"</th>");
						titles[i+1]=config.getConfigName();
						sb.append("charge_id,");
					}
				}
				title.append("<th>错误信息</th></tr>");
				titles[lists.size()+1]="错误信息";
				sb.append("value_unit,des) values(");
				count=sheet.getRows()-2;
				request.getSession().setAttribute("count", count);
				for (int i=2;i<sheet.getRows();i++) {
					System.out.println(i);
					one=i-1;
					request.getSession().setAttribute("one", one);
					//insert into 语句开始拼装
					StringBuffer error=new StringBuffer();
					StringBuffer values=new StringBuffer("");
					String codeId=null;
					for(int m=0;m<lists.size();m++){
						AssetsConfig config=(AssetsConfig)lists.get(m);
						if(config.getConfigColumnName().equals("codeId")){
							//资产编号
							codeId=sheet.getCell(m,i).getContents().trim();
							String codeid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select * from assets_base where code_id='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()==0){
									codeid=sheet.getCell(m,i).getContents().trim();
									values.append("'"+codeid+"',");
								}else{
									error.append("资产编号重复。");
								}
							}else{
								error.append("资产编号不能为空。");
							}
						}else if(config.getConfigColumnName().equals("name")){
							//资产名称
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								error.append("资产名称不能为空。");
							}
						}else if(config.getConfigColumnName().equals("model")){
							//资产型号
							values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
						}else if(config.getConfigColumnName().equals("assetsType")){
							//资产类别
							String Typeid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from assets_type where name='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map type=(Map)list.get(0);
									Typeid=type.get("id").toString();
									values.append("'"+Typeid+"',");
								}else{
									error.append("系统中没有配置此类别。");
								}
							}else{
								error.append("资产类别不能为空。");
							}
						}else if(config.getConfigColumnName().equals("itsmType")){
							String itsmtype=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from itsm_type where name='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map type=(Map)list.get(0);
									itsmtype=type.get("id").toString();
									values.append("'"+itsmtype+"',");
								}else{
									error.append("系统中没有配置此运维类别。");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("assetsState")){
							//资产状态
							String Stateid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from assets_state where name='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map state=(Map)list.get(0);
									Stateid=state.get("id").toString();
									values.append("'"+Stateid+"',");
								}else{
									error.append("系统中没有配置此状态。");
								}
							}else{
								error.append("资产状态不能为空。");
							}
						}else if(config.getConfigColumnName().equals("qualityTime")){
							//保修年限（月）
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								if(this.isint(sheet.getCell(m,i).getContents().trim())){
									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("保修年限请填写正整数");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("price")){
							//资产单价
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								if(this.isdouble(sheet.getCell(m,i).getContents().trim())){
									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("资产单价格式错误，标准格式为1200.00。");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("buyDate")){
							//采购日期
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								String a=sheet.getCell(m,i).getContents().trim();
								if(this.date(sheet.getCell(m,i).getContents().trim())){
									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("采购日期格式错误，标准格式为“YYYY-MM-DD”。");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("exitfacotryDate")){
							//出厂日期
							String a=sheet.getCell(m,i).getContents().trim();
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								if(this.date(sheet.getCell(m,i).getContents().trim())){
									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("出厂日期格式错误，标准格式为“YYYY-MM-DD”。");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("inDate")){
							//入库日期
							String a=sheet.getCell(m,i).getContents().trim();
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								if(this.date(sheet.getCell(m,i).getContents().trim())){
									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("入库日期格式错误，标准格式为“YYYY-MM-DD”。");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("location")){
							//地理区域
							String Locationid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from location where name='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map location=(Map)list.get(0);
									Locationid=location.get("id").toString();
								}else{
									error.append("系统中没有配置此地理区域。");
								}
							}
							values.append(Locationid+",");		
						}else if(config.getConfigColumnName().equals("buildlocation")){
							//存放位置
							String Buildlocation=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from buildlocation where allname='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map buildlocation=(Map)list.get(0);
									Buildlocation=buildlocation.get("id").toString();
								}else{
									error.append("系统中没有配置此存放位置。");
								}
							}
							values.append(Buildlocation+",");
						}else if(config.getConfigColumnName().equals("system")){
							//系统
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("ip")){
							//IP
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("mac")){
							//MAC
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("devicename")){
							//设备名称
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark1")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark2")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark3")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark4")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark5")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark6")){
							sb.append("remark6,");
						}else if(config.getConfigColumnName().equals("remark7")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark8")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark9")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark10")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark11")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark12")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark13")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark14")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark15")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark16")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark17")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark18")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark19")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark20")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark21")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark22")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark23")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark24")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark25")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark26")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark27")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark28")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark29")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("remark30")){
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("assetsProducerBySupportId")){
							//供应商
							String Supportid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from assets_producer where name='"+sheet.getCell(m,i).getContents().trim()+"' and type='1' or type='3'");
								if(list.size()!=0){
									Map support=(Map)list.get(0);
									Supportid=support.get("id").toString();
								}else{
									error.append("系统中没有配置此供应商。");
								}
							}
							values.append(Supportid+",");
						}else if(config.getConfigColumnName().equals("assetsProducerByProduceId")){
							//制造商
							String Producerid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from assets_producer where name='"+sheet.getCell(m,i).getContents().trim()+"'and type='2' or type='3'");
								if(list.size()!=0){
									Map producer=(Map)list.get(0);
									Producerid=producer.get("id").toString();
								}else{
									error.append("系统中没有配置此制造商。");
								}
							}
							values.append(Producerid+",");
						}else if(config.getConfigColumnName().equals("department")){
							//所属部门
							String Departmentid=null;
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from department where name='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map department=(Map)list.get(0);
									Departmentid=department.get("id").toString();
								}else{
									error.append("系统中没有配置此所属部门");
								}
							}else{
								error.append("请填写所属部门");
							}
							values.append(Departmentid+",");
						}else if(config.getConfigColumnName().equals("usersByUsersId")){
							//使用人
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from users where username='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map user=(Map)list.get(0);
									values.append("'"+user.get("id").toString()+"',");
//									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("系统中无此使用人信息。");
								}
							}else{
								values.append(null+",");
							}
						}else if(config.getConfigColumnName().equals("usersByChargeId")){
							//负责人
							if(sheet.getCell(m,i).getContents().trim()!=null&&!sheet.getCell(m,i).getContents().trim().equals("")){
								List list=assetsService.sqllist("select id from users where username='"+sheet.getCell(m,i).getContents().trim()+"'");
								if(list.size()!=0){
									Map user=(Map)list.get(0);
									values.append("'"+user.get("id").toString()+"',");
//									values.append("'"+sheet.getCell(m,i).getContents().trim()+"',");
								}else{
									error.append("系统中无此负责人信息。");
								}
							}else{
								error.append("请填写负责人");
							}		
						}
					}
					Map max=(Map)assetsService.sqllist("select max(value_unit)+1 as valueunit from assets_base").get(0);
					String maxvalue=max.get("valueunit")==null?maxvalue="1":max.get("valueunit").toString();
					values.append("'"+maxvalue+"',");
					values.append("'0')");	
					
					String errors=error.toString();
					if(errors.length()==0){
						boolean ifsave=assetsService.sql(sb.toString()+values.toString());
						if(ifsave){
							Map code=(Map)assetsService.sqllist("select code from assets_base where code_id='"+codeId+"'").get(0);
							String assets_base_id=code.get("code").toString();
							String sqlinfo="insert into assets_info(assets_base_id) values("+assets_base_id+")";
							assetsService.sql(sqlinfo);
						}
					}else{
						j++;
						table.append("<tr style=' line-height:22px; color:#ff0000; background:#ffffff; font-size:12px;'>");
						table.append("<td align='center'>"+j+"</td>");
						Object[] obj = new Object[lists.size()+1];
						for(int n=0;n<lists.size();n++){
							AssetsConfig configs=(AssetsConfig)lists.get(n);
							if(configs.getConfigColumnName().equals("codeId")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("name")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("model")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("assetsType")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("itsmType")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("assetsState")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("qualityTime")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("price")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("buyDate")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("exitfacotryDate")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("inDate")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("location")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("buildlocation")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("system")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("ip")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("mac")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("devicename")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark1")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark2")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark3")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark4")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark5")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark6")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark7")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark8")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark9")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark10")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark11")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark12")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark13")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark14")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark15")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark16")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark17")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark18")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark19")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark20")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark21")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark22")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark23")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark24")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark25")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark26")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark27")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark28")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark29")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("remark30")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("assetsProducerBySupportId")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("assetsProducerByProduceId")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("department")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("usersByUsersId")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}else if(configs.getConfigColumnName().equals("usersByChargeId")){
								table.append("<td align='center'>"+sheet.getCell(n,i).getContents().trim()+"</td>");
								obj[n]=sheet.getCell(n,i).getContents().trim();
							}
						}
						table.append("<td align='left'>"+errors+"</td></tr>");
						obj[lists.size()]=errors;
						data.add(obj);
					}
				}
				book.close();
			}catch(Exception e){
				e.printStackTrace();
				errorList.clear();
				errorList.add("数据导入失败，请按照模板提示填写数据！");
			}
		}
		request.setAttribute("errorList", errorList);
		request.setAttribute("title",title.toString());
		request.setAttribute("table", table.toString());
		request.getSession().setAttribute("datas",data);
		request.getSession().setAttribute("titles",titles);
		return SUCCESS;
	}
	
	
	public String AssetsImportuser() {
		int j=0;
		List<String> errorList = new ArrayList<String>();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath("/upload");
		errorList.add("导入完成！");
		String []titles={"用户信息","登陆名称","用户全名","所在部门","所在区域","错误信息"};
		String title="<tr style=' line-height:22px; color:#ff0000; font-weight:200; background:#cde9fc; font-size:12px;'><th>序号</th>"+
		"<th>登陆名称</th><th>用户全名</th><th>所在部门</th><th>所在区域</th><th>错误信息</th><tr>";
		StringBuffer table=new StringBuffer();
		if (fileuser != null) {
			String targetFileName = StringUtil.generateFileName(fileNameuser);
			File target = new File(targetDirectory, targetFileName);
			try {
				FileUtils.copyFile(fileuser, target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Workbook book = Workbook.getWorkbook(new File(targetDirectory+ "\\" + targetFileName));
				Sheet sheet = book.getSheet(0);
				StringBuffer errors=new StringBuffer();;
				for (int i=2;i<sheet.getRows();i++) {
					System.out.println(i);
					if(sheet.getCell(0,i).getContents().trim()!=null&&!sheet.getCell(0,i).getContents().trim().equals("")){
						String dusername="select id from users where username='"+sheet.getCell(0,i).getContents().trim()+"'";
						List list2=assetsService.sqllist(dusername);
						if(list2.size()>0){
							errors.append("此用户登陆账号重复！");
						}
					}
					if(sheet.getCell(1,i).getContents().trim()==null||sheet.getCell(1,i).getContents().trim().equals("")){
						errors.append("此用户全名不能为空！");
					}
					String department=null;
					if(sheet.getCell(2,i).getContents().trim()!=null&&!sheet.getCell(2,i).getContents().trim().equals("")){
						String dep="SELECT id FROM department WHERE name='"+sheet.getCell(2,i).getContents().trim()+"'";
						List list=assetsService.sqllist(dep);
						if(list.size()!=0){
							Map depart=(Map)list.get(0);
							department=depart.get("id").toString();
						}else{
							errors.append("无此用户所在部门！");
						}
					}else{
						department="1";
					}
					String location=null;
					if(sheet.getCell(3,i).getContents().trim()!=null&&!sheet.getCell(3,i).getContents().trim().equals("")){
						String locations="select id from location where name='"+sheet.getCell(3,i).getContents().trim()+"'";
						List list1=assetsService.sqllist(locations);
						if(list1.size()!=0){
							Map loca=(Map)list1.get(0);
							location=loca.get("id").toString();
						}else{
							errors.append("无此用户所在区域！");
						}
					}else{
						location="1";
					}
					if(errors.length()==0){
						String sb="INSERT INTO users (username,PASSWORD,truename,departmentID,locationID) VALUES('"+sheet.getCell(0,i).getContents().trim()+"','111111','"+sheet.getCell(1,i).getContents().trim()+"','"+sheet.getCell(2,i).getContents().trim()+"','"+sheet.getCell(3,i).getContents().trim()+"')";
						assetsService.sql(sb);
					}else{
						j++;
						table.append("<tr style=' line-height:22px; color:#ff0000; background:#ffffff; font-size:12px;'>");
						table.append("<td align='center'>"+j+"</td>");
						table.append("<td align='center'>"+sheet.getCell(0,i).getContents().trim()+"</td>");
						table.append("<td align='center'>"+sheet.getCell(1,i).getContents().trim()+"</td>");
						table.append("<td align='center'>"+sheet.getCell(2,i).getContents().trim()+"</td>");
						table.append("<td align='center'>"+sheet.getCell(3,i).getContents().trim()+"</td>");
						table.append("<td align='left'>"+errors+"</td></tr>");
						Object[] obj ={sheet.getCell(0,i).getContents().trim(),sheet.getCell(1,i).getContents().trim(),sheet.getCell(2,i).getContents().trim(),sheet.getCell(3,i).getContents().trim(),errors};
						data.add(obj);
					}
				}
				book.close();
			}catch(Exception e){
				e.printStackTrace();
				errorList.clear();
				errorList.add("数据导入失败，请按照模板提示填写数据！");
			}
		}
		request = ServletActionContext.getRequest();
		request.setAttribute("errorList", errorList);
		request.setAttribute("title",title);
		request.setAttribute("table", table.toString());
		request.getSession().setAttribute("datas",data);
		request.getSession().setAttribute("titles",titles);
		return SUCCESS;
	}
	
	
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String download() throws Exception {
		return SUCCESS;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName()throws UnsupportedEncodingException{
		fileName=new String("Excel导入模板.xls".getBytes(),"ISO-8859-1"); 
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	//日期格式判断
	public boolean date(String sDate){
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
	    String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
	             + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
	             + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
	             + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
	             + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
	             + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
	    Pattern patterns = Pattern.compile(datePattern1);
        Matcher match = patterns.matcher(sDate);
        if(match.matches()){
        	patterns = Pattern.compile(datePattern2);
            match = patterns.matcher(sDate);
            return true;
        }else{
        	return false;
        }
	}
	
	//Double判断
	public boolean isdouble(String isdouble){
		try{
			Double.parseDouble(isdouble);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isint(String isint){
		try{
			Integer.parseInt(isint);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public String excelDown() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		data=(List)request.getSession().getAttribute("datas");
		String title[]=(String[])request.getSession().getAttribute("titles");
		try {
			String fname = new String("导入出错信息".getBytes(),"ISO-8859-1");// Excel文件名
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
	
	public String usertemplateDown() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		List datas=new ArrayList();
		String []title={"用户信息","登陆名称","用户全名","所在部门","所在区域"};
		Object [] obj={"assets","资产管理员","开发中心","所有区域"};
		datas.add(obj);
		try {
			String fname = new String("用户信息EXCEL导入模板".getBytes(),"ISO-8859-1");// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ fname+".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, datas, title);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	public String templateDown() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String queryString="from AssetsConfig where assets_Type_id is null and flag='0' and ischoose='1'";
		List list=assetsConfigService.findbysql(queryString);
		List datas=new ArrayList();
		int size=list.size();
		String [] title=new String[size+1];
		title[0]="资产信息";
		Object [] obj=new Object[size];
		for(int i=0;i<list.size();i++){
			AssetsConfig config=(AssetsConfig)list.get(i);
			title[i+1]=config.getConfigName();
			if(config.getConfigColumnName().equals("codeId")){
				obj[i]="20110309";
			}else if(config.getConfigColumnName().equals("name")){
				obj[i]="名称";
			}else if(config.getConfigColumnName().equals("model")){
				obj[i]="型号";
			}else if(config.getConfigColumnName().equals("assetsType")){
				obj[i]="类别名称";
			}else if(config.getConfigColumnName().equals("itsmType")){
				obj[i]="运维类别名称";
			}else if(config.getConfigColumnName().equals("assetsState")){
				obj[i]="状态名称";
			}else if(config.getConfigColumnName().equals("qualityTime")){
				obj[i]="12";
			}else if(config.getConfigColumnName().equals("price")){
				obj[i]="1000.00";
			}else if(config.getConfigColumnName().equals("buyDate")){
				obj[i]="2011-03-09";
			}else if(config.getConfigColumnName().equals("exitfacotryDate")){
				obj[i]="2011-03-09";
			}else if(config.getConfigColumnName().equals("inDate")){
				obj[i]="2011-03-09";
			}else if(config.getConfigColumnName().equals("location")){
				obj[i]="区域名称";
			}else if(config.getConfigColumnName().equals("buildlocation")){
				obj[i]="存放地名称（全称）";
			}else if(config.getConfigColumnName().equals("system")){
				obj[i]="系统";
			}else if(config.getConfigColumnName().equals("ip")){
				obj[i]="192.168.10.10";
			}else if(config.getConfigColumnName().equals("mac")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("devicename")){
				obj[i]="设备名称";
			}else if(config.getConfigColumnName().equals("remark1")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark2")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark3")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark4")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark5")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark6")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark7")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark8")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark9")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark10")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark11")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark12")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark13")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark14")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark15")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark16")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark17")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark18")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark19")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark20")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark21")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark22")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark23")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark24")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark25")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark26")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark27")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark28")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark29")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("remark30")){
				obj[i]="";
			}else if(config.getConfigColumnName().equals("assetsProducerBySupportId")){
				obj[i]="供应商名称";
			}else if(config.getConfigColumnName().equals("assetsProducerByProduceId")){
				obj[i]="制造商名称";
			}else if(config.getConfigColumnName().equals("department")){
				obj[i]="所属部门名称";
			}else if(config.getConfigColumnName().equals("usersByUsersId")){
				obj[i]="使用人";
			}else if(config.getConfigColumnName().equals("usersByChargeId")){
				obj[i]="负责人";
			}
		}
		datas.add(obj);
		
		try {
			String fname = new String("资产信息EXCEL导入模板".getBytes(),"ISO-8859-1");// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ fname+".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, datas, title);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public void writeExcel(OutputStream out, List datas, String[] title) {
		if (datas == null) {
			throw new IllegalArgumentException("写excel流需要List参数!");
		}
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(out);
			WritableSheet ws = workbook.createSheet("sheet 1", 0);
			int rowNum = 2; // 要写的行
			if(title!=null){
				titleRow(ws,title);// 压入标题
			}
			java.lang.reflect.Method[] method = AssetsBase.class.getDeclaredMethods();
			for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
				Object[] cells = (Object[]) datas.get(i);
				dataRow(ws, rowNum, cells); // 压一行到shee
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
	//表头字体及表格样式
	private void titleRow(WritableSheet ws, Object[] cells)throws RowsExceededException, WriteException {
		WritableCellFormat wcfF=titleFontcenter();
		Label cell=null;
		if(cells[0]!=""){
			ws.mergeCells(0,0,cells.length-2,0);//合并单元格，参数格式（开始列，开始行，结束列，结束行）
		    cell=new Label(0,0,""+cells[0],wcfF);
		    ws.addCell(cell);
		}
		for(int j=1;j<cells.length;j++){
			cell = new Label(j-1, 1, "" + cells[j],wcfF);
			ws.setColumnView(j-1,20);
			ws.addCell(cell);
		}
	}
    //添加数据
	private void dataRow(WritableSheet ws, int rowNum, Object[] cells)
			throws RowsExceededException, WriteException {
		WritableCellFormat wcfF=dataFontcenter();
		for (int j = 0; j < cells.length; j++) {// 写一行
			Label cell = new Label(j, rowNum, "" + cells[j],wcfF);
			ws.addCell(cell);
		}
	}
	//字体居中加粗样式
	private WritableCellFormat titleFontcenter() throws RowsExceededException, WriteException {
		WritableFont wf = new WritableFont(WritableFont.TIMES,12, WritableFont.BOLD, false); 
		WritableCellFormat wcfF = new WritableCellFormat(wf); 
		wcfF.setAlignment(jxl.format.Alignment.CENTRE);//把水平对齐方式指定为居中 
		wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中 
		return wcfF;
	}
	//字体居中不加粗样式
	private WritableCellFormat dataFontcenter() throws RowsExceededException, WriteException {
		WritableCellFormat wcfF = new WritableCellFormat(); 
		wcfF.setAlignment(jxl.format.Alignment.CENTRE);//把水平对齐方式指定为居中 
		wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//把垂直对齐方式指定为居中 
		return wcfF;
	}
}
