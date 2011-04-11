package com.combanc.itsm.action.systemmanage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.htmlobj.AssetsQurey;
import com.combanc.itsm.htmlobj.HtmlAssets;
import com.combanc.itsm.pojo.Accessory;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.service.AssetStatisticService;
import com.combanc.itsm.service.AssetsService;
import com.combanc.itsm.service.AssetsTypeService;
import com.combanc.itsm.service.DepartmentService;
import com.combanc.itsm.util.ReportUtil;
import com.raisepartner.chartfusion.api.pie3d.Pie3D;

public class AssetsStatisticAction extends BaseActionSupport {
	private static final long serialVersionUID = 5262432297378607950L;

	private String xmlString = "";
	private AssetsBase assets;
	private AssetsService assetsService;
	private String startDate;
	private String endDate;
	private String startDate1;
	private String endDate1;
	
	
	public String getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(String startDate1) {
		this.startDate1 = startDate1;
	}

	public String getEndDate1() {
		return endDate1;
	}
	
	public void setEndDate1(String endDate1) {
		this.endDate1 = endDate1;
	}

	private String startDate2;
	private String endDate2;
	private String date1;
	private String date2;
	private DepartmentService departmentService;
	private AssetsTypeService assetsTypeService;
	int number1, number2;
	private int page;
	private int pageSize = 5;
	private PageBean pageBean;
	
	private String typeXml;
	private String yearXml;
	private String stateXml;
	private AssetStatisticService assetStatisticService;
	
	public String getStateXml() {
		return stateXml;
	}

	public void setStateXml(String stateXml) {
		this.stateXml = stateXml;
	}

	public String getYearXml() {
		return yearXml;
	}

	public void setYearXml(String yearXml) {
		this.yearXml = yearXml;
	}

	
	
	public AssetStatisticService getAssetStatisticService() {
		return assetStatisticService;
	}

	public void setAssetStatisticService(AssetStatisticService assetStatisticService) {
		this.assetStatisticService = assetStatisticService;
	}

	public String getTypeXml() {
		return typeXml;
	}

	public void setTypeXml(String typeXml) {
		this.typeXml = typeXml;
	}
	
	
	public String index() {
		this.typeXml=assetStatisticService.getAssetTypeStatistic();
		this.yearXml=assetStatisticService.getAssetYearStatistic();
		this.stateXml=assetStatisticService.getAssetStateStatistic();
		return SUCCESS;
	}

	public String history() throws ParseException { // 资产历史对比统计
		date1 = "" + startDate + "/" + endDate;
		date2 = "" + startDate2 + "/" + endDate2;

		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String queryString1 = "from AssetsBase as model where model.des='0' and 1=1 "; // queryString1查询历史时段资产数目
		String queryString2, hql; // queryString2查询当前时段资产数目，hql查询这两个时段总资产数
		if (assets.getDepartment()!=null) {
			if(assets.getDepartment().getId()!=null&&assets.getDepartment().getId()>0){
				queryString1 = queryString1 + " and model.department.id='"+ assets.getDepartment().getId() + "'";
			}
		}
		if (assets.getAssetsState()!=null) {
			if(assets.getAssetsState().getId()>0){
				queryString1 = queryString1 + " and model.assetsState.id='"+ assets.getAssetsState().getId() + "'";
			}
		}
		if (assets.getAssetsType()!=null) {
			if(assets.getAssetsType().getId()!=null&&assets.getAssetsType().getId()>0){
				queryString1 = queryString1 + " and model.assetsType.id='" + assets.getAssetsType().getId() + "'";
			}
		}
		queryString2 = queryString1;
		hql = queryString1;

		if (startDate2!=null&&endDate2!=null&&startDate!=null&&endDate!= null) {
			queryString1 = queryString1 + " and model.inDate>='"+format1.format(format1.parse(startDate))+ "' and model.inDate<='"+ format1.format(format1.parse(endDate)) + "'";
			queryString2 = queryString2 + " and model.inDate>='"+ format1.format(format1.parse(startDate2))+ "' and model.inDate<='"+ format1.format(format1.parse(endDate2)) + "'";
			hql = hql + " and ((model.inDate>='"+ format1.format(format1.parse(startDate))+ "' and model.inDate<='"+ format1.format(format1.parse(endDate))
					+ "') or( model.inDate>='"+ format1.format(format1.parse(startDate2))+ "' and model.inDate<='"+ format1.format(format1.parse(endDate2)) + "')) order by model.inDate desc";
		}
		
		number1 = assetsService.getAllRowCount(queryString1); // 历史时段资产数目
		number2 = assetsService.getAllRowCount(queryString2); // 当前时段资产数目
		this.pageBean = assetsService.queryHistoryStatistic(hql, pageSize, page); // pageBean.list存放两个时段资产
		Pie3D chart = new Pie3D();
		chart.setCaption("资产历史状况对比");
		chart.setShowFCMenuItem("0");
		chart.setDecimals("0");
		chart.setEnableSmartLabels("1");
		chart.setBgColor("99CCFF,FFFFFF");
		chart.setBgAlpha("40,100");
		chart.setBaseFont("宋体");
		chart.setBaseFontSize("12");
		chart.setEnableRotation("1");
		ReportUtil.setValue(chart, date1, String.valueOf(number1));
		ReportUtil.setValue(chart, date2, String.valueOf(number2));
		xmlString = chart.toString();
		return SUCCESS;
	}
	
	public String DataStatistic() throws ParseException{
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "from AssetsBase as model where model.des='0' and 1=1"; 
		if (assets.getDepartment()!=null) {
			if(assets.getDepartment().getId()!=null&&assets.getDepartment().getId()>0){
				sql = sql + " and model.department.id='"+ assets.getDepartment().getId() + "'";
			}
		}
		if (assets.getAssetsState()!=null) {
			if(assets.getAssetsState().getId()>0){
				sql = sql + " and model.assetsState.id='"+ assets.getAssetsState().getId() + "'";
			}
		}
		if (assets.getAssetsType()!=null) {
			if(assets.getAssetsType().getId()!=null&&assets.getAssetsType().getId()>0){
				sql = sql + " and model.assetsType.id='" + assets.getAssetsType().getId() + "'";
			}
		}
		if(assets.getAssetsProducerByProduceId()!=null){
			if(assets.getAssetsProducerByProduceId().getId()>0){
				sql = sql + " and model.assetsProducerByProduceId.id='" + assets.getAssetsProducerByProduceId().getId() + "'";
			}
		}
		if(assets.getAssetsProducerBySupportId()!=null){
			if(assets.getAssetsProducerBySupportId().getId()>0){
				sql=sql+" and model.assetsProducerBySupportId.id='"+assets.getAssetsProducerBySupportId().getId()+"'";
			}
		}
		sql=sql+"and model.buyDate>='"+format1.format(format1.parse(startDate1))+"' and model.buyDate<='"+format1.format(format1.parse(endDate1))+
		"' order by model.assetsType.id,model.buyDate asc ";
		this.pageBean=assetsService.queryHistoryStatistic(sql,10, page);
		return "success";
	}
	
	
	

	public String getXmlString() {
		return xmlString;
	}

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}


	public AssetsService getAssetsService() {
		return assetsService;
	}

	public void setAssetsService(AssetsService assetsService) {
		this.assetsService = assetsService;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
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

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
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

	public AssetsBase getAssets() {
		return assets;
	}

	public void setAssets(AssetsBase assets) {
		this.assets = assets;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(String startDate2) {
		this.startDate2 = startDate2;
	}

	public String getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(String endDate2) {
		this.endDate2 = endDate2;
	}
	
	public String down() throws Exception {
		return SUCCESS;
	}
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String excelDown() throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String sql = "from AssetsBase as model where model.des='0'"; 
		if (assets.getDepartment()!=null) {
			if(assets.getDepartment().getId()!=null&&assets.getDepartment().getId()>0){
				sql = sql + " and model.department.id='"+ assets.getDepartment().getId() + "'";
			}
		}
		if (assets.getAssetsState()!=null) {
			if(assets.getAssetsState().getId()>0){
				sql = sql + " and model.assetsState.id='"+ assets.getAssetsState().getId() + "'";
			}
		}
		if (assets.getAssetsType()!=null) {
			if(assets.getAssetsType().getId()!=null&&assets.getAssetsType().getId()>0){
				sql = sql + " and model.assetsType.id='" + assets.getAssetsType().getId() + "'";
			}
		}
		if(assets.getAssetsProducerByProduceId()!=null){
			if(assets.getAssetsProducerByProduceId().getId()>0){
				sql = sql + " and model.assetsProducerByProduceId.id='" + assets.getAssetsProducerByProduceId().getId() + "'";
			}
		}
		if(assets.getAssetsProducerBySupportId()!=null){
			if(assets.getAssetsProducerBySupportId().getId()>0){
				sql=sql+" and model.assetsProducerBySupportId.id='"+assets.getAssetsProducerBySupportId().getId()+"'";
			}
		}
		sql=sql+"and model.buyDate>='"+format1.format(format1.parse(startDate1))+"' and model.buyDate<='"+format1.format(format1.parse(endDate1))+
		"' order by model.assetsType.id,model.buyDate asc ";
		List<AssetsBase> assetsListExcel=assetsService.querySql(sql);
		
		
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		List ll = new ArrayList();
		String[] title = new String[] {
				"序号",
				"资产编码",
				"资产名称",
				"资产型号",
				"资产类别",
				"资产状态",
				"保修年限（月）",
				"资产单价",
				"采购日期",
				"出厂日期",
				"入库日期",
				"存放位置",
				"供应商",
				"制造商",
				"所属部门",
				"负责人" ,
				"负责人电话",
				"负责人手机",
				"负责人邮箱"
				};
		for (int i=0;i<assetsListExcel.size();i++) {
			AssetsBase a=assetsListExcel.get(i);
			Object[] date = new Object[] {
					i+1,
					a.getCodeId(),
					a.getName()!=null?a.getName():"",
					a.getModel()!=null?a.getModel():"",
					a.getAssetsType()!=null?a.getAssetsType().getName():"",
					a.getAssetsState()!=null?a.getAssetsState().getName():"",
					a.getQualityTime()!=null?a.getQualityTime():"",
					a.getPrice()!=null?a.getPrice():"",	
					a.getBuyDate()!=null?this.time(a.getBuyDate()):"",
					a.getExitfacotryDate()!=null?this.time(a.getExitfacotryDate()):"",
					a.getInDate()!=null?this.time(a.getInDate()):"",
					a.getLocation()!=null?a.getLocation().getName():"",
					a.getAssetsProducerBySupportId()!=null?a.getAssetsProducerBySupportId().getName():"",
					a.getAssetsProducerByProduceId()!=null?a.getAssetsProducerByProduceId().getName():"",
					a.getDepartment()!=null?a.getDepartment().getName():"",
					a.getUsersByChargeId()!=null?a.getUsersByChargeId().getTruename():"",
				    a.getUsersByChargeId()!=null?a.getUsersByChargeId().getPhone():"",
				    a.getUsersByChargeId()!=null?a.getUsersByChargeId().getCellphone():"",
				    a.getUsersByChargeId()!=null?a.getUsersByChargeId().getEmail():""
					};
			ll.add(date);
		}

		try {
			String fname = "Excel";// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ fname+".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, ll, title);
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
	
	public String time(Timestamp tamp){
		Date currentTime = tamp;
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = format.format(currentTime);
	    return dateString;
	}
}
