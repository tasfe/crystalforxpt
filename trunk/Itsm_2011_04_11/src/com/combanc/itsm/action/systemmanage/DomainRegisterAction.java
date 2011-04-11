package com.combanc.itsm.action.systemmanage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.combanc.itsm.pojo.DomainRegister;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.service.DomainRegisterService;

public class DomainRegisterAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DomainRegisterService domainRegisterService;
	private Integer domainRegisterId;
	private DomainRegister domainRegister;
	private List<DomainRegister> domainRegisters;

	private int page;
	private PageBean pageBean;
	private int pageSize = 10;

	private Integer state;
	private Integer approvalResult;

	HttpServletRequest request = null;

	//管理员列表查看
	public String list() {
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		this.pageBean = domainRegisterService.query(domainRegister, pageSize, page,u);
		return "success";
	}
	
	//用户列表查看
	public String user_list() {
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		this.pageBean = domainRegisterService.query(domainRegister, pageSize, page,u);
		return "success";
	}

	//管理员删除功能
	public String delete() {
		domainRegisterService.delete(domainRegisterId);
		return "success";
	}

	//用户添加新的域名申请
	public String save() {
		if (domainRegister != null) {
			Users u = (Users) (ServletActionContext.getRequest().getSession()
					.getAttribute("user"));
			domainRegister.setSumbitUsers(u);
			String formatDate = new SimpleDateFormat("yyMMddHHmmss")
					.format(new Date());
			int random = new Random().nextInt(100);
			domainRegister.setSerialNumber("YM" + formatDate + random);
			
			domainRegister.setDomain(domainRegister.getDomain());
			domainRegister.setIpAddress(domainRegister.getIpAddress());
			domainRegister.setServerLocation(domainRegister.getServerLocation());
			domainRegister.setUnitsFullName(domainRegister.getUnitsFullName());
			domainRegister.setUnitsAddress(domainRegister.getUnitsAddress());
			domainRegister.setTechnicalContact(domainRegister.getTechnicalContact());
			domainRegister.setPhone(domainRegister.getPhone());
			domainRegister.setEmail(domainRegister.getEmail());

			domainRegister.setForeignDomain(domainRegister.getForeignDomain());
			domainRegister.setForeignIpAddress(domainRegister.getForeignIpAddress());
			domainRegister.setForeignServerLocation(domainRegister.getForeignServerLocation());
			domainRegister.setForeignUnitsFullName(domainRegister.getForeignUnitsFullName());
			domainRegister.setForeignUnitsAddress(domainRegister.getForeignUnitsAddress());
			domainRegister.setForeignTechnicalContact(domainRegister.getForeignTechnicalContact());
			domainRegister.setForeignPhone(domainRegister.getForeignPhone());
			domainRegister.setForeignEmail(domainRegister.getForeignEmail());
			
		

			domainRegister.setUnitsLeader(domainRegister.getUnitsLeader());
			domainRegister.setApplication(domainRegister.getApplication());
			domainRegister.setApplicationDate(domainRegister.getApplicationDate());
			domainRegister.setReceiver(domainRegister.getReceiver());
			domainRegister.setReceiveDate(domainRegister.getReceiveDate());
			domainRegister.setCompleter(domainRegister.getCompleter());
			domainRegister.setCompleteDate(domainRegister.getCompleteDate());

			domainRegister.setState(0);
			domainRegister.setApprovalResult(2);
			domainRegister.setFailReason(domainRegister.getFailReason());

		}
		domainRegisterService.save(domainRegister);
		return "success";
	}

	//管理员域名申请跳转页面
	public String saveInput() {
		return "success";
	}

	//用户域名申请跳转页面
	public String user_saveInput() {
		return "success";
	}

	//管理员域名审批跳转
	public String updateInput() {
		domainRegister = domainRegisterService.findById(domainRegisterId);
		return "success";
	}

	//用户域名修改跳转
	public String user_updateInput() {
		domainRegister = domainRegisterService.findById(domainRegisterId);
		return "success";
	}

	//管理员打印功能
	public String show() {
		domainRegister = domainRegisterService.findById(domainRegisterId);

		return "success";
	}

	//用户预览效果
	public String user_show() {
		domainRegister = domainRegisterService.findById(domainRegisterId);

		return "success";
	}

	//管理员审批域名申请操作
	public String update() {
		domainRegister.setState(1);
		domainRegister.setApprovalResult(domainRegister.getApprovalResult());
		domainRegisterService.update(domainRegister);
		return "list";
	}
	
	//在管理员审批IP申请操作前，用户可以修改域名申请操作
	public String user_update() {
		domainRegister.setState(0);
		domainRegister.setApprovalResult(2);
		domainRegisterService.update(domainRegister);
		return "success";
	}
	
	//管理员执行查询操作
	public String query(){
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		this.pageBean= domainRegisterService.query(domainRegister, pageSize, page,u);

		return "success";
	}
	//用户执行查询操作
	public String query2(){
		Users u = (Users) (ServletActionContext.getRequest().getSession()
				.getAttribute("user"));
		this.pageBean= domainRegisterService.query(domainRegister, pageSize, page,u);

		return "success";
	}
	
	

	//检查域名申请时是否重复
	  public void checkDomain(){   
	           
	    HttpServletResponse response=ServletActionContext.getResponse();   
	     boolean flag=domainRegisterService.isExistDomain(domainRegister.getDomain());   
	      try{   
	             PrintWriter pw=response.getWriter();   
	            pw.print(flag);   
	             pw.close();   
	       }catch(IOException e){   
                e.printStackTrace();
	        }   
	    }  
	  
	  
		
		// 导出域名申请到Excel
		@SuppressWarnings("unchecked")
		public String export() throws Exception {

			List<DomainRegister> domainRegisterListExcel = domainRegisterService.querys(domainRegister);
			HttpServletResponse response = ServletActionContext.getResponse();
			List list = new ArrayList();
			String[] title = new String[] { "申请编号", "申请域名", "对应IP地址：", "服务器存放位置：", "申请单位",
					 "单位所在校内地址",  "技术联系人"
					, "联系电话", "电子邮箱", "申请日期", "申请人签名", "单位负责人"
					, "接收人", "接收日期", "完成人", "完成日期"};

			for (DomainRegister dr : domainRegisterListExcel) {
				
				Object[] object = new Object[] { dr.getSerialNumber(),dr.getDomain()+".ruc.edu.cn",dr.getIpAddress(),
						dr.getServerLocation(),dr.getUnitsFullName(),dr.getUnitsAddress(),
						dr.getTechnicalContact(),dr.getPhone(),dr.getEmail(),
						dr.getApplicationDate(),dr.getApplication(),dr.getUnitsLeader(),dr.getReceiver(),
						dr.getReceiveDate(),dr.getCompleter(),dr.getCompleteDate()
						};
				list.add(object);
			}
			try {
				String fname = "DomainRegisterExcel"; // Excel文件名;
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
				java.lang.reflect.Method[] method = DomainRegister.class
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

	public DomainRegisterService getDomainRegisterService() {
		return domainRegisterService;
	}

	public void setDomainRegisterService(DomainRegisterService domainRegisterService) {
		this.domainRegisterService = domainRegisterService;
	}

	public Integer getDomainRegisterId() {
		return domainRegisterId;
	}

	public void setDomainRegisterId(Integer domainRegisterId) {
		this.domainRegisterId = domainRegisterId;
	}

	public DomainRegister getDomainRegister() {
		return domainRegister;
	}

	public void setDomainRegister(DomainRegister domainRegister) {
		this.domainRegister = domainRegister;
	}

	public List<DomainRegister> getDomainRegisters() {
		return domainRegisters;
	}

	public void setDomainRegisters(List<DomainRegister> domainRegisters) {
		this.domainRegisters = domainRegisters;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(Integer approvalResult) {
		this.approvalResult = approvalResult;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	


	
}
