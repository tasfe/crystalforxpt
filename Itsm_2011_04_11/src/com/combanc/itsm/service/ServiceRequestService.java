package com.combanc.itsm.service;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.ServiceRequestDAO;
import com.combanc.itsm.pojo.ServiceRequest;
import com.combanc.itsm.pojo.ServiceTran;
import com.combanc.itsm.util.StringUtil;

public class ServiceRequestService extends BaseService<ServiceRequest, Integer>{

	private ServiceRequestDAO serviceRequestDAO;

	public PageBean queryForPage(int pageSize, int page, String condition) {

		String hql = "from ServiceRequest as p where 1=1 ";
		hql = hql + condition;
		hql = hql + " order by p.id desc";// 查询语句
		int allRow = serviceRequestDAO.getAllRowCount(hql); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		final int offset = PageBean.countOffset(pageSize, page); // 当前页开始记录
		final int length = pageSize; // 每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		List<ServiceRequest> list = serviceRequestDAO.queryForPage(hql, offset,
				length); // "一页"的记录

		// 把分页信息保存到Bean中
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public PageBean query(ServiceRequest serviceRequest, int pageSize, int page) {
		String sql = "from ServiceRequest as model where 1=1 and model.state!=6 ";
		if (serviceRequest.getRequestNo()!= null&&serviceRequest.getRequestNo().trim().length()!=0) {
			serviceRequest.setRequestNo(StringUtil.decrateString(serviceRequest.getRequestNo()));
			sql = sql + " and model.requestNo like '%" + serviceRequest.getRequestNo() + "%'";
		}
		if (serviceRequest.getUsersByRequestUser().getTruename()!= null&&serviceRequest.getUsersByRequestUser().getTruename().trim().length()!=0) {
			serviceRequest.getUsersByRequestUser().setTruename(StringUtil.decrateString(serviceRequest.getUsersByRequestUser().getTruename()));
			sql = sql + " and model.usersByRequestUser.truename like '%" + serviceRequest.getUsersByRequestUser().getTruename() + "%'";
		}
		if (serviceRequest.getSummary()!= null && serviceRequest.getSummary().trim().length()!=0 ){
			serviceRequest.setSummary(StringUtil.decrateString(serviceRequest.getSummary()));
			sql = sql + " and  model.summary like '%" + serviceRequest.getSummary() + "%'";
		}
		if (serviceRequest.getServiceCategory()!=null&&serviceRequest.getServiceCategory().getId()!=null&&serviceRequest.getServiceCategory().getId()!=-1) {
			sql = sql + " and  model.serviceCategory=" + serviceRequest.getServiceCategory().getId()+ " ";
		}
		if (serviceRequest.getUsersByEngineerId()!=null&&serviceRequest.getUsersByEngineerId().getTruename().trim().length()!=0) {
			serviceRequest.getUsersByEngineerId().setTruename(StringUtil.decrateString(serviceRequest.getUsersByEngineerId().getTruename()));
			sql = sql + " and model.usersByEngineerId.truename like '%" + serviceRequest.getUsersByEngineerId().getTruename() + "%'";
		}
		if (serviceRequest.getEndDate() != null){
			sql = sql + " and  model.submintTime<='" + serviceRequest.getEndDate() + "'";
		}
		if (serviceRequest.getStartDate() != null){
			sql = sql + " and  model.submintTime>='" + serviceRequest.getStartDate() + "'";
		}
		if(serviceRequest.getUsersByEngineerId()!=null&&serviceRequest.getUsersByEngineerId().getId()!=null) {
			sql=sql+ " and  model.usersByEngineerId="+serviceRequest.getUsersByEngineerId().getId();
		}
		sql=sql+ " order by submintTime desc";
		int allRow = serviceRequestDAO.getAllRowCount(sql);    //总记录数
	    int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
	    final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
	    final int length = pageSize;    //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);
		
		//sql = sql + " order by model.requestNo";
	    List<ServiceRequest> list = serviceRequestDAO.queryForPage(sql, offset, length);  
	    
	    PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
	}

	public PageBean query2(ServiceRequest serviceRequest, int pageSize, int page) {
		String sql = "from ServiceRequest as model where 1=1 and ( model.state=6 or model.state=4) ";
		if (serviceRequest.getRequestNo()!= null&&serviceRequest.getRequestNo().trim().length()!=0) {
			serviceRequest.setRequestNo(StringUtil.decrateString(serviceRequest.getRequestNo()));
			sql = sql + " and model.requestNo like '%" + serviceRequest.getRequestNo() + "%'";
		}
		if (serviceRequest.getUsersByRequestUser().getTruename()!= null&&serviceRequest.getUsersByRequestUser().getTruename().trim().length()!=0) {
			serviceRequest.getUsersByRequestUser().setTruename(StringUtil.decrateString(serviceRequest.getUsersByRequestUser().getTruename()));
			sql = sql + " and model.usersByRequestUser.truename like '%" + serviceRequest.getUsersByRequestUser().getTruename() + "%'";
		}
		if (serviceRequest.getSummary()!= null && serviceRequest.getSummary().trim().length()!=0 ){
			serviceRequest.setSummary(StringUtil.decrateString(serviceRequest.getSummary()));
			sql = sql + " and  model.summary like '%" + serviceRequest.getSummary() + "%'";
		}
		if (serviceRequest.getServiceCategory()!=null&&serviceRequest.getServiceCategory().getId()!=null&&serviceRequest.getServiceCategory().getId()!=-1) {
			sql = sql + " and  model.serviceCategory=" + serviceRequest.getServiceCategory().getId()+ " ";
		}
		if (serviceRequest.getUsersByEngineerId()!=null&&serviceRequest.getUsersByEngineerId().getTruename().trim().length()!=0) {
			serviceRequest.getUsersByEngineerId().setTruename(StringUtil.decrateString(serviceRequest.getUsersByEngineerId().getTruename()));
			sql = sql + " and model.usersByEngineerId.truename like '%" + serviceRequest.getUsersByEngineerId().getTruename() + "%'";
		}
		if (serviceRequest.getDepartmentByRequestDept()!=null&&serviceRequest.getDepartmentByRequestDept().getId()!=null&&serviceRequest.getDepartmentByRequestDept().getId()!=-1) {
			sql = sql + " and model.departmentByRequestDept=" + serviceRequest.getDepartmentByRequestDept().getId() + " ";
		}
		if (serviceRequest.getEndDate() != null&&serviceRequest.getStartDate() != null){
			sql = sql + " and  model.submintTime<='" + serviceRequest.getEndDate() + "' and  model.submintTime>='" + serviceRequest.getStartDate() + "'";
		}
		if (serviceRequest.getIsFinished()!=null&&serviceRequest.getIsFinished()!=-1){
			sql = sql + " and  model.isFinished=" + serviceRequest.getIsFinished()+" ";
		}
		if (serviceRequest.getBeginTime() != null&&serviceRequest.getFinishTime() != null){
			sql = sql + " and  model.finishTime>='" + serviceRequest.getBeginTime() + "' and  model.finishTime<='" + serviceRequest.getFinishTime() + "'";
		}
		if(serviceRequest.getUsersByEngineerId()!=null&&serviceRequest.getUsersByEngineerId().getId()!=null) {
			sql=sql+ " and  model.usersByEngineerId="+serviceRequest.getUsersByEngineerId().getId();
		}
		sql=sql+ " order by submintTime desc";
		int allRow = serviceRequestDAO.getAllRowCount(sql);    //总记录数
	    int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
	    final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
	    final int length = pageSize;    //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

	    System.out.println(sql);
		//sql = sql + " order by model.requestNo";
	    List<ServiceRequest> list = serviceRequestDAO.queryForPage(sql, offset, length);  
	    
	    PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();
        return pageBean;
	}
	
	public List<ServiceRequest> findAll() {
		return serviceRequestDAO.findAll(ServiceRequest.class);
	}

	public ServiceRequest findServiceRequestById(Integer serviceRequestId) {
		return (ServiceRequest) serviceRequestDAO.findById(
				ServiceRequest.class, serviceRequestId);
	}
	
	public List<ServiceRequest> findByEngineerId(Integer engineerId) {
		return serviceRequestDAO.findByEngineerId(engineerId);
	}
	
	public List<ServiceRequest> findByEngineerId2(Integer engineerId, Integer id) {
		return serviceRequestDAO.findByEngineerId2(engineerId,id);
	}
	
	public void saveServiceRequest(ServiceRequest ServiceRequest) {
		serviceRequestDAO.save(ServiceRequest);
	}

	public void update(ServiceRequest ServiceRequest) {
		serviceRequestDAO.update(ServiceRequest);
	}

	public void update(ServiceTran serviceTran, ServiceRequest serviceRequest) {
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		serviceRequestDAO.update(request);
	}
	
	public void delay(ServiceRequest serviceRequest,Integer timer, String danwei) throws Exception {//延期
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		long MILLIS;
		if(danwei.equals("d")) MILLIS=timer*86400000;
		else if(danwei.equals("h")) MILLIS=timer*3600000;
		else MILLIS=timer*60000;
		if(request.getPromiseTime()!=null)
		request.setPromiseTime(new Timestamp(request.getPromiseTime().getTime()+MILLIS));
		serviceRequestDAO.update(request);
	}
	
	public void assign(ServiceRequest serviceRequest) throws Exception {//指派
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setUsersByEngineerId(serviceRequest.getUsersByEngineerId());
		request.setUsersByOperatorId(serviceRequest.getUsersByOperatorId());
		request.setCause(serviceRequest.getCause());
		request.setAssignTime(serviceRequest.getAssignTime());
		request.setState(1);
		serviceRequestDAO.update(request);
	}
	
	public void reAssign(ServiceRequest serviceRequest) throws Exception {//重新指派
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setUsersByEngineerId(serviceRequest.getUsersByEngineerId());
		request.setCause(serviceRequest.getCause());
		request.setFinishTime(null);
		if(serviceRequest.getSeverityTypByEmergency()!=-1) {
			request.setSeverityTypByEmergency(serviceRequest.getSeverityTypByEmergency());
			if(serviceRequest.getSeverityTypByEssential()!=-1){
				request.setSeverityTypByEssential(serviceRequest.getSeverityTypByEssential());
				request.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
			}else {
				request.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*request.getSeverityTypByEssential());
			}				
		}else if(serviceRequest.getSeverityTypByEssential()!=-1){
				request.setSeverityTypByEssential(serviceRequest.getSeverityTypByEssential());
				request.setPriorityLvl(request.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
		}	
		request.setState(1);
		serviceRequestDAO.update(request);
	}
	
	public void recate(ServiceRequest serviceRequest) throws Exception {//重新归类
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setServiceCategory(serviceRequest.getServiceCategory());
		if(serviceRequest.getSeverityTypByEmergency()!=-1) {
			request.setSeverityTypByEmergency(serviceRequest.getSeverityTypByEmergency());
			if(serviceRequest.getSeverityTypByEssential()!=-1){
				request.setSeverityTypByEssential(serviceRequest.getSeverityTypByEssential());
				request.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
			}else {
				request.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*request.getSeverityTypByEssential());
			}				
		}else if(serviceRequest.getSeverityTypByEssential()!=-1){
				request.setSeverityTypByEssential(serviceRequest.getSeverityTypByEssential());
				request.setPriorityLvl(request.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
		}		
		serviceRequestDAO.update(request);
	}
	
	public void pause(ServiceRequest serviceRequest) throws Exception {//挂起
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setUsersByEngineerId(serviceRequest.getUsersByEngineerId());
		request.setPauseCause(serviceRequest.getPauseCause());
		request.setState(7);
		serviceRequestDAO.update(request);
	}
	public void rouse(ServiceRequest serviceRequest) throws Exception {//唤醒
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setUsersByEngineerId(null);
		request.setState(0);
		serviceRequestDAO.update(request);
	}
	public void accept(ServiceRequest serviceRequest) throws Exception {//接受
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setPlan(serviceRequest.getPlan());
		request.setServiceCategory(serviceRequest.getServiceCategory());
		request.setState(2);
		request.setBeginTime(serviceRequest.getBeginTime());
		if(serviceRequest.getSeverityTypByEmergency()!=-1) {
			request.setSeverityTypByEmergency(serviceRequest.getSeverityTypByEmergency());
			if(serviceRequest.getSeverityTypByEssential()!=-1){
				request.setSeverityTypByEssential(serviceRequest.getSeverityTypByEssential());
				request.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
			}else {
				request.setPriorityLvl(serviceRequest.getSeverityTypByEmergency()*request.getSeverityTypByEssential());
			}				
		}else if(serviceRequest.getSeverityTypByEssential()!=-1){
				request.setSeverityTypByEssential(serviceRequest.getSeverityTypByEssential());
				request.setPriorityLvl(request.getSeverityTypByEmergency()*serviceRequest.getSeverityTypByEssential());
		}	
		serviceRequestDAO.update(request);
	}
	
	public void solve(ServiceRequest serviceRequest) throws Exception { //快速解决
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setSolution(serviceRequest.getSolution());
		request.setBeginTime(serviceRequest.getBeginTime());
		request.setFinishTime(serviceRequest.getFinishTime());
		/*
		 * 当下一个工单状态为待反馈时，此时应将工程师置为请求人
		 */
		//request.setUsersByEngineerId(serviceRequest.getUsersByEngineerId());//此有问题
		request.setUsersByEngineerId(request.getUsersByRequestUser());
		
		
		request.setIsFinished(1);
		request.setState(5);
		serviceRequestDAO.update(request);
	}
	
	public void transmit(ServiceRequest serviceRequest) throws Exception { //转交
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setUsersByEngineerId(serviceRequest.getUsersByEngineerId());
		request.setTransmitTime(serviceRequest.getTransmitTime());
		request.setTransmitCause(serviceRequest.getTransmitCause());
		request.setState(1);
		serviceRequestDAO.update(request);
	}
	
	public void ret(ServiceRequest serviceRequest) throws Exception { //退回
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setReturnCause(serviceRequest.getReturnCause());
		request.setState(0);
		request.setUsersByEngineerId(null);
		serviceRequestDAO.update(request);
	}
	
	public void refuse(ServiceRequest serviceRequest) throws Exception { //拒绝
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setFinishTime(serviceRequest.getFinishTime());
		if(request.getBeginTime()==null)
		request.setBeginTime(serviceRequest.getBeginTime());
		request.setUsersByEngineerId(serviceRequest.getUsersByEngineerId());
		request.setIsFinished(1);
		request.setState(4);
		serviceRequestDAO.update(request);
	}
	
	public void feedback(ServiceRequest serviceRequest) throws Exception {  //添加用户反馈
		
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setFeedback(serviceRequest.getFeedback());
		request.setServiceLvl(serviceRequest.getServiceLvl());
		request.setState(6);
		if(request.getIsFinished()!=new Integer(1))
		{
			request.setIsFinished(1);
		}
		serviceRequestDAO.update(request);
	}
	
	public void clo(ServiceRequest serviceRequest) throws Exception { //关闭
		ServiceRequest request=serviceRequestDAO.findById(serviceRequest.getId());
		request.setIsFinished(serviceRequest.getIsFinished());
		request.setErrorCause(serviceRequest.getErrorCause());
		request.setAddToKnowledge(serviceRequest.getAddToKnowledge());
		if(serviceRequest.getServiceCategory().getId()!=-1)  request.setServiceCategory(serviceRequest.getServiceCategory());
		if(serviceRequest.getSummary()!=null&&serviceRequest.getSummary().trim().length()!=0) request.setSummary(serviceRequest.getSummary());
		request.setSolution(serviceRequest.getSolution());
		request.setFinishTime(serviceRequest.getFinishTime());
		
		/*
		 * 当下一个工单状态为待反馈时，此时应将工程师置为请求人
		 */
		request.setUsersByEngineerId(request.getUsersByRequestUser());
		request.setState(5);
		
		serviceRequestDAO.update(request);
	}
	
	public List<ServiceTran> findAllByrequNo(String requNo) {
		String queryString;
		Session session = null;
		try {
			queryString = "from ServiceTran model where model.requNo='" + requNo
					+ "' order by model.operatorTime";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {

			throw re;
		} finally {
			try {
				session.close();
			} catch (Exception ex) {
			}
		}
	}

	public PageBean findByState(Integer state, int pageSize, int page) throws Exception { //根据状态查找

		String sql = "from ServiceRequest as model where 1=1 ";
		if(state!=null&&state!=-1)	sql=sql+" and model.state="+state;
		
		int allRow = serviceRequestDAO.getAllRowCount(sql);    //总记录数
	    int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
	    final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
	    final int length = pageSize;    //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

	    List<ServiceRequest> list = serviceRequestDAO.queryForPage(sql, offset, length);  
	    
	    PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();		

//		if(state!=null&&state==-1){
//			pageBean.setList(serviceRequestDAO.findAll());
//       
//		}
//		else{
//			 pageBean.setList(serviceRequestDAO.findByProperty("state", state));
////		}
//
//		 pageBean.init();	

        return pageBean;
	}
	
	public PageBean findByState2(Integer state, int pageSize, int page) throws Exception { //根据不是该状态下的工单

		String sql = "from ServiceRequest as model where 1=1 ";
		if(state!=null)	sql=sql+" and model.state!="+state;
		
		int allRow = serviceRequestDAO.getAllRowCount(sql);    //总记录数
	    int totalPage = PageBean.countTotalPage(pageSize, allRow);    //总页数
	    final int offset = PageBean.countOffset(pageSize, page);    //当前页开始记录
	    final int length = pageSize;    //每页记录数
		final int currentPage = PageBean.countCurrentPage(page);

	    List<ServiceRequest> list = serviceRequestDAO.queryForPage(sql, offset, length);  
	    
	    PageBean pageBean = new PageBean();
        pageBean.setPageSize(pageSize);    
        pageBean.setCurrentPage(currentPage);
        pageBean.setAllRow(allRow);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(list);
        pageBean.init();

        return pageBean;
	}
	public void saveOrUpdate(ServiceRequest ServiceRequest) {
		serviceRequestDAO.saveOrUpdate(ServiceRequest);
	}

	public void deleteById(Integer serviceRequestId) {
		ServiceRequest ServiceRequest = null;
		if (serviceRequestId != null)
			ServiceRequest = (ServiceRequest) serviceRequestDAO.findById(
					ServiceRequest.class, serviceRequestId);
		if (ServiceRequest != null)
			serviceRequestDAO.delete(ServiceRequest);
	}

	public ServiceRequestDAO getServiceRequestDAO() {
		return serviceRequestDAO;
	}

	public void setServiceRequestDAO(ServiceRequestDAO serviceRequestDAO) {
		this.serviceRequestDAO = serviceRequestDAO;
	}

	public List<ServiceRequestService> findByStateAndUserId( int state, int userId) {
		return serviceRequestDAO.findByStateAndUserId(state,userId);
	}

	public ServiceRequest findServiceRequestByRequestNo(String requestNo) {
		return (ServiceRequest) serviceRequestDAO.findByRequstNo(requestNo);
	}
	public List findByProperty(String propertyName, Object value) {
		return serviceRequestDAO.findByProperty(propertyName, value);
	}
	public List findByDepartment(String propertyName, Integer value) {
		return serviceRequestDAO.findByDepartment(propertyName, value);
	}
	public List<ServiceRequest> findByusersByOperatorId(Integer usersByOperatorId) {
		return serviceRequestDAO.findByusersByOperatorId(usersByOperatorId);
	}
	public List<ServiceRequest> findByusersByusersByOriginatorId(Integer usersByOriginatorId) {
		return serviceRequestDAO.findByusersByusersByOriginatorId(usersByOriginatorId);
	}
	public List<ServiceRequest> findNotClosed()
        {
            return serviceRequestDAO.findNotClosed();
        }

    public List<ServiceRequest> findNotFinishedByUserId(Integer id) {
           String queryString = "from ServiceRequest as model where (model.isFinished!="
                + "1 or model.isFinished is null or model.isFinished=0) and model.usersByEngineerId="+id ;

     
      return serviceRequestDAO.findByHql(queryString);
    }
}
