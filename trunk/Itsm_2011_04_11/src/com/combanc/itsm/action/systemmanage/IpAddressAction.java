package com.combanc.itsm.action.systemmanage;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.IpAddress;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.IpAddressService;

public class IpAddressAction extends BaseActionSupport implements 
       ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IpAddressService ipAddressService;
	private Integer ipAddressId;
	private IpAddress ipAddress;
	private List<IpAddress> ipAddresses;
	
	private int page;
	private PageBean pageBean;
	private int pageSize = 10;
	
	private Integer state;
	private Integer approvalResult;
	
	
	HttpServletRequest request=null;
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	//管理员查看IP申请列表
	public String list(){
		this.pageBean= ipAddressService.query(ipAddress, pageSize, page);
		return "success";
	}
	
	//用户查看IP申请列表
	public String user_list(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		Users u = (Users) session.getAttribute("user");
		int userid=u.getId();
		this.pageBean=ipAddressService.query(ipAddress, pageSize, page, u);
	//	this.pageBean= ipAddressService.query(ipAddress, pageSize, page);
		return "success"; 
	}
	
	//管理员执行删除操作
	public String delete(){
	    ipAddressService.delete(ipAddressId);
		return "success";
	}
	
	//管理员执行添加新的IP申请操作
	public String save(){
		if(ipAddress!=null){
			HttpSession session = ServletActionContext.getRequest().getSession();
			Users u = (Users) session.getAttribute("user");
			ipAddress.setSumbitUsers(u);
			String formatDate = new SimpleDateFormat("yyMMddHHmmss") .format(new Date());
			int random = new Random().nextInt(100);
			ipAddress.setSerialNumber("IP"+formatDate+random);
			ipAddress.setApplyIpv4Count(ipAddress.getApplyIpv4Count());
			ipAddress.setApplyIpv6Count(ipAddress.getApplyIpv6Count());
			ipAddress.setApplyIpPurpose(ipAddress.getApplyIpPurpose());
			ipAddress.setIpUseRoom(ipAddress.getIpUseRoom());
			ipAddress.setUnitsFullName(ipAddress.getUnitsFullName());
			ipAddress.setUnitsAddress(ipAddress.getUnitsAddress());
			ipAddress.setUnitsExtentIpCount(ipAddress.getUnitsExtentIpCount());
			ipAddress.setApplyIpPurpose(ipAddress.getApplyIpPurpose());
			ipAddress.setTechnicalContact(ipAddress.getTechnicalContact());
			ipAddress.setPhone(ipAddress.getPhone());
			ipAddress.setEmail(ipAddress.getEmail());
			ipAddress.setApplyIpCause(ipAddress.getApplyIpCause());
			ipAddress.setUnitsLeader(ipAddress.getUnitsLeader());
			ipAddress.setApplication(ipAddress.getApplication());
			ipAddress.setApplicationDate(ipAddress.getApplicationDate());
			ipAddress.setReceiver(ipAddress.getReceiver());
			ipAddress.setReceiveDate(ipAddress.getReceiveDate());
			ipAddress.setCompleter(ipAddress.getCompleter());
			ipAddress.setCompleteDate(ipAddress.getCompleteDate());
			ipAddress.setApplyIpv4Detail(ipAddress.getApplyIpv4Detail());
			ipAddress.setApplyIpv6Detail(ipAddress.getApplyIpv6Detail());
			ipAddress.setState(0);
			ipAddress.setApprovalResult(2);
			ipAddress.setFailReason(ipAddress.getFailReason());
			
		}
		ipAddressService.save(ipAddress);
		return "success";
	}
	
	//管理员添加IP申请跳转
	public String saveInput(){
		return "success";
	}
	//用户添加IP申请跳转
	public String user_saveInput(){
		return "success";
	}
	//管理员执行审批IP申请跳转
	public String updateInput(){
//		ipAddress=(IpAddress)ipAddressService.findById(ipAddressId);
			ipAddress=ipAddressService.findById(ipAddressId);
		return "success";
	}
	
	
	//用户修改IP申请操作
	public String user_updateInput(){
//		ipAddress=(IpAddress)ipAddressService.findById(ipAddressId);
			ipAddress=ipAddressService.findById(ipAddressId);
		return "success";
	}
	
	//管理员执行打印IP申请操作
	public String show(){
		ipAddress=ipAddressService.findById(ipAddressId);

		return "success";
	}
	

	//用户执行IP申请预览操作
	public String user_show(){
		ipAddress=ipAddressService.findById(ipAddressId);

		return "success";
	}
	
	//管理员执行审批操作
	public String update(){
			ipAddress.setState(1);
			ipAddress.setApprovalResult(ipAddress.getApprovalResult());
		ipAddressService.update(ipAddress);
		return "list";
	}
	
	//在管理员审批IP申请操作前，用户可以进行IP地址申请的修改操作
	public String user_update(){
		ipAddress.setState(0);
		ipAddress.setApprovalResult(2);
	ipAddressService.update(ipAddress);
	return "success";
}
	
	//管理员执行查询单条或多条数据操作
	public String query(){
		this.pageBean= ipAddressService.query(ipAddress, pageSize, page);

		return "success";
	}
	
	// 导出IP地址申请到EXCEL
	@SuppressWarnings("unchecked")
	public String export() throws Exception {

		List<IpAddress> ipAddressListExcel = ipAddressService.querys(ipAddress);
		HttpServletResponse response = ServletActionContext.getResponse();
		List list = new ArrayList();
		String[] title = new String[] { "申请编号", "IPv4数量", "IPv6数量", "IP地址使用位置", "申请单位",
				"申请IP地址用途", "单位所在校内地址", "单位现有IP地址数量", "单位现有IP地址用途", "技术联系人"
				, "联系电话", "电子邮箱", "申请日期", "申请人签名", "单位负责人"
				, "接收人", "接收日期", "完成人", "完成日期"};

		for (IpAddress ip : ipAddressListExcel) {
			
			Object[] object = new Object[] { ip.getSerialNumber(),ip.getApplyIpv4Count(),ip.getApplyIpv6Count(),
					ip.getIpUseRoom(),ip.getUnitsFullName(),ip.getApplyIpPurpose(),ip.getUnitsAddress(),
					ip.getUnitsExtentIpCount(),ip.getUnitsExtentIpPurpose(),ip.getTechnicalContact(),ip.getPhone(),ip.getEmail(),
					ip.getApplicationDate(),ip.getApplication(),ip.getUnitsLeader(),ip.getReceiver(),
					ip.getReceiveDate(),ip.getCompleter(),ip.getCompleteDate()
					};
			list.add(object);
		}
		try {
			String fname = "IpRegisterExcel"; // Excel文件名;
			OutputStream os = response.getOutputStream();// 取得输出流;
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ fname + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			writeExcel(os, list, title);

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
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
			@SuppressWarnings("unused")
			java.lang.reflect.Method[] method = IpAddress.class
					.getDeclaredMethods();
			for (int i = 0; i < datas.size(); i++, rowNum++) {// 写sheet
				Object[] cells = (Object[]) datas.get(i);
				putRow(ws, rowNum, cells); // 压一行到sheet
			}
			workbook.write();
			workbook.close(); // 一定要关闭, 否则没有保存Excel
		} catch (RowsExceededException e) {
			System.out.println("jxl write RowsExceededException:"
					+ e.getMessage());
		} catch (WriteException e) {
			System.out.println("jxl write WriteException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("jxl write file i/o exception!, cause by:"
					+ e.getMessage());
		}
	}

	private void putRow(WritableSheet ws, int rowNum, Object[] cells)
			throws RowsExceededException, WriteException {
		for (int j = 0; j < cells.length; j++) {// 写一行
			Label cell = new Label(j, rowNum, "" + cells[j]);
			ws.addCell(cell);
		}
	}
	
	public IpAddressService getIpAddressService() {
		return ipAddressService;
	}
	public void setIpAddressService(IpAddressService ipAddressService) {
		this.ipAddressService = ipAddressService;
	}
	public Integer getIpAddressId() {
		return ipAddressId;
	}
	public void setIpAddressId(Integer ipAddressId) {
		this.ipAddressId = ipAddressId;
	}
	public IpAddress getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(IpAddress ipAddress) {
		this.ipAddress = ipAddress;
	}
	public List<IpAddress> getIpAddresses() {
		return ipAddresses;
	}
	public void setIpAddresses(List<IpAddress> ipAddresses) {
		this.ipAddresses = ipAddresses;
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}
	
	

}
