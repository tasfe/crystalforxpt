package com.combanc.itsm.action.systemmanage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.AssetsProducer;
import com.combanc.itsm.service.ProducerService;

/**
 * @author Administrator
 * 
 */
public class ProducerAction extends BaseActionSupport implements
		ServletRequestAware {

	/**
* 
*/
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(ProducerAction.class);
	private ProducerService producerService;
	private String actionURI;
	private AssetsProducer producer;
	private int page;
	private int pageSize = 10;
	private PageBean pageBean;
	private int howquery=0;
	private int message=0;

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int getHowquery() {
		return howquery;
	}

	public void setHowquery(int howquery) {
		this.howquery = howquery;
	}

	public String list() throws Exception {
		if(howquery==0){
			this.pageBean = producerService.findAll(pageSize, page);
		}else{
			this.pageBean = producerService.query(producer, pageSize, page);
		}
		return "success";
	}

	public String main() throws Exception {
		return "success";
	}

	public String top() throws Exception {
		this.pageBean = producerService.findAll(pageSize, page);
		return "success";

	}

	public String add() throws Exception { // 转向增加页面
		actionURI = "save";
		return "success";
	}

	public String updateFind() throws Exception { // 转向更新页面前执行，找出要更新的POJO
		producer = producerService.findById(producer.getId());
		return "success";
	}

	public String update() throws Exception { // 执行更新
		producerService.update(producer);
		message=3;
		return "success";
	}

	public String save() { // 保存信息
		if (producerService.save(producer)) {
			log.debug(producer);
			message=3;
			return "success";
		} else {
			return "error";
		}
	}

	public String query() { // 根据输入条件查询
		this.pageBean = producerService.query(producer, pageSize, page);
		return "success";
	}

	public String delete() throws Exception {// 删除信息
		List list=producerService.isnull(producer.getId().toString());
		if(list.size()==0)
		{
			producerService.deleteById(producer.getId());
			message=2;
		}else{
			message=1;
		}
		return "success";
	}

	public ProducerService getProducerService() {
		return producerService;
	}

	public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}

	public String getActionURI() {
		return actionURI;
	}

	public void setActionURI(String actionURI) {
		this.actionURI = actionURI;
	}

	public AssetsProducer getProducer() {
		return producer;
	}

	public void setProducer(AssetsProducer producer) {
		this.producer = producer;
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

}
