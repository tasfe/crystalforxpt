/**
 * 
 */
package com.combanc.itsm.service;

import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.AssetsBaseDAO;
import com.combanc.itsm.dao.AssetsHistoryStateDAO;
import com.combanc.itsm.dao.AssetsInfoDAO;
import com.combanc.itsm.dao.AssetsTypeDAO;
import com.combanc.itsm.htmlobj.AssetsQurey;
import com.combanc.itsm.pojo.AssetsBase;
import com.combanc.itsm.pojo.AssetsInfo;
import com.combanc.itsm.pojo.AssetsType;
import com.combanc.itsm.pojo.Users;
import com.combanc.itsm.util.CartogramInfo;
import com.combanc.itsm.util.ReportUtil;
import com.combanc.itsm.util.SpringBeanProxy;
import com.combanc.monitor.pojo.MonitorComputer;
import com.raisepartner.chartfusion.api.pie3d.Pie3D;

/**
 * @author Administrator
 * 
 */
public class AssetsService extends BaseService<AssetsBase, Integer> {

	private AssetsBaseDAO assetsBaseDAO;
	private AssetsTypeDAO assetsTypeDAO;

	private AssetsHistoryStateDAO assetsHistoryStateDAO;

	private AssetsInfoDAO assetsInfoDAO;

	private static final Log log = LogFactory.getLog(AssetsService.class);
	public void delAssetsByCode(String code) {
		if (code != null) {
			AssetsBase base = assetsBaseDAO.findById(Integer.parseInt(code));
			List list =assetsInfoDAO.findByProperty("assetsBase.code",base.getCode());
			if (base != null) {
				if(list.size()!=0){
					AssetsInfo info=(AssetsInfo)list.get(0);
					assetsInfoDAO.delete(info);
				}
				assetsBaseDAO.delete(base);
			}
		} else {
			log.debug("code null , del assets fail");
		}
	}

	public java.util.List<AssetsBase> findAll() {
		return assetsBaseDAO.findAll();
	}
	public PageBean findAll(int pageSize, int page) {
		String queryString = "from AssetsBase as model where model.assetsState.id!='' order by valueUnit asc";
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsBase> list = assetsBaseDAO.findAll(queryString,offset,length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public List<AssetsBase> findVagueByTypeCode(int pid) {

		List<AssetsBase> assetList = assetsBaseDAO.findByTypeCode(pid);

		List<AssetsType> typeList = assetsTypeDAO
				.findAllByAssetsTypePid(pid);
		for (int i = 0; i < typeList.size(); i++) {
			List<AssetsBase> tList = assetsBaseDAO.findByTypeCode(typeList.get(i).getId());
			assetList.addAll(tList);
		}

		return assetList;

	}
	
	public List<AssetsBase> findByLocation(Integer id) {

		List<AssetsBase> assetList = assetsBaseDAO.findByLocation(id);
		return assetList;

	}
	
	public List<AssetsBase> findByDepartment(Integer id) {

		List<AssetsBase> assetList = assetsBaseDAO.findByDepartment(id);
		return assetList;

	}
	
	public int getAllRowCount(String sql){
		return assetsBaseDAO.getAllRowCount(sql);
	}
	
	public List getAllRows(String sql){
		return assetsBaseDAO.getAllRow(sql);
	}

	public PageBean findVagueByTypeCodeForPage(int pageSize, int page, int pid) {

		PageBean pageBean = new PageBean();
		String hql = "from AssetsBase as model where 1=1 ";

		hql = hql + "and model.assetsType = " + pid;
		int allRow = assetsBaseDAO.getAllRowCount(hql);
		int totalPage = PageBean.countTotalPage(pageSize, allRow);
		if (page > totalPage) {
			page = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, page);
		final int length = pageSize;
		final int currentPage = PageBean.countCurrentPage(page);
		List<AssetsBase> list = assetsBaseDAO.queryForPage(hql, offset, length);

		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();

		return pageBean;

	}
	
	public AssetsInfo findbyassetsid(String assetsid){
		List list=assetsInfoDAO.findByProperty("assetsBase.code",Integer.parseInt(assetsid));
		if(list.isEmpty()){
			return null;
		}else{
			AssetsInfo info=(AssetsInfo)list.get(0);
			return info;
		}
	}

	public AssetsBaseDAO getAssetsBaseDAO() {
		return assetsBaseDAO;
	}

	public AssetsBase getAssetsByCode(String code) {
		AssetsBase assets;
		if (code!=null&&!code.equals(null)) {
			assets = assetsBaseDAO.findById(Integer.parseInt(code));
		} else {
			log.debug("assets code is null");
			return null;
		}

		return assets;
	}

	public List<AssetsBase> getAssetsByPidAssets(int pid) {

		return assetsBaseDAO.findByTypeCode(pid);

	}

	public List<AssetsBase> getAssetsByState(int state) {
		return assetsBaseDAO.findByState(state);
	}

	public List<AssetsBase> getAssetsByQuality() {
		return assetsBaseDAO.findByQuality();
	}

	public AssetsBaseDAO getAssetsDAO() {
		return assetsBaseDAO;
	}

	public AssetsHistoryStateDAO getAssetsHistoryStateDAO() {
		return assetsHistoryStateDAO;
	}

	public AssetsInfoDAO getAssetsInfoDAO() {
		return assetsInfoDAO;
	}

	public AssetsTypeDAO getAssetsTypeDAO() {
		return assetsTypeDAO;
	}
	
	public AssetsInfoDAO getDeviceInfoDAO() {
		return assetsInfoDAO;
	}

	public AssetsHistoryStateDAO getHistoryDeviceStateDAO() {
		return assetsHistoryStateDAO;
	}

	public List<AssetsBase> query(AssetsQurey assets) {
		return assetsBaseDAO.queryByHql(assets);
	}
	
	public List<AssetsBase> queryhql(AssetsBase assetsBase,Users user,int asset){
		StringBuffer sql=new StringBuffer("from AssetsBase as model where model.assetsState.id!='' and model.des='0' ");
    	if(assetsBase!=null){
    		if(asset==0){
    			if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
    				sql.append(" and model.name like '%"+assetsBase.getName()+"%' ");
    			}
    			if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
    				sql.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
    			}
    			if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
    				sql.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
    			}
    			if(assetsBase.getAssetsProducerByProduceId()!=null){
    				if(assetsBase.getAssetsProducerByProduceId().getId()!=null&&assetsBase.getAssetsProducerByProduceId().getId()>0){
    					sql.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
    				}
    			}
    			if(assetsBase.getAssetsProducerBySupportId()!=null){
    				if(assetsBase.getAssetsProducerBySupportId().getId()!=null&&assetsBase.getAssetsProducerBySupportId().getId()>0){
    					sql.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
    				}
    			}
    			if(assetsBase.getAssetsType()!=null){
    				if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
    					sql.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
    				}
    			}
    			if(assetsBase.getAssetsState()!=null){
    				if(assetsBase.getAssetsState().getId()!=null&&assetsBase.getAssetsState().getId()>0){
    					sql.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
    				}
    			}
    			if(assetsBase.getLocation()!=null){
    				if(assetsBase.getLocation().getId()!=null&&assetsBase.getLocation().getId()>0){
    					sql.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
    				}
    			}
    			if(assetsBase.getUsersByUsersId()!=null){
    				if(assetsBase.getUsersByUsersId().getDepartment()!=null){
    					if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
        					sql.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"'");
        				}
    				}
    				
    			}
    			if(assetsBase.getUsersByUsersId()!=null){
    				if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
    					sql.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
    				}
    			}
    			sql.append(" and model.usersByChargeId.id='"+user.getId()+"' ");
    			if(assetsBase.getUsersByChargeId()!=null){
    				if(assetsBase.getUsersByChargeId().getDepartment()!=null){
    					if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
        					sql.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"'");
        				}
    				}
    				
    			}
    			if(assetsBase.getBuildlocation()!=null){
        			if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
        				sql.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"'");
        			}
        		}
    			if(assetsBase.getBuyDate()!=null){
    				sql.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
    			}
    	        if(assetsBase.getExitfacotryDate()!=null){
    	        	sql.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
    			}
    	        if(assetsBase.getInDate()!=null){
    	        	sql.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
    	        }
        		
        	}else if(asset==1){
        		if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
        			sql.append(" and model.name like '%"+assetsBase.getName()+"%' ");
        		}
        		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
        			sql.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
        		}
        		if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
        			sql.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
        		}
        		if(assetsBase.getAssetsProducerByProduceId()!=null){
        			if(assetsBase.getAssetsProducerByProduceId().getId()!=null&&assetsBase.getAssetsProducerByProduceId().getId()>0){
        				sql.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsProducerBySupportId()!=null){
        			if(assetsBase.getAssetsProducerBySupportId().getId()!=null&&assetsBase.getAssetsProducerBySupportId().getId()>0){
        				sql.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsType()!=null){
        			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
        				sql.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsState()!=null){
        			if(assetsBase.getAssetsState().getId()!=null&&assetsBase.getAssetsState().getId()>0){
        				sql.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
        			}
        		}
        		
        		if(assetsBase.getLocation()!=null){
        			if(assetsBase.getLocation().getId()!=null&&assetsBase.getLocation().getId()>0){
        				sql.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
        				sql.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getDepartment()!=null){
        				if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
            				sql.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"'");
            			}
        			}
        		}
        		if(assetsBase.getUsersByChargeId()!=null){
        			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
        				sql.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
        			}
        		}
        		sql.append(" and model.usersByChargeId.department.id='"+user.getDepartment().getId()+"' ");
        		if(assetsBase.getBuildlocation()!=null){
        			if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
        				sql.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"'");
        			}
        		}
        		if(assetsBase.getBuyDate()!=null){
        			sql.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
        		}
                if(assetsBase.getExitfacotryDate()!=null){
                	sql.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
        		}
                if(assetsBase.getInDate()!=null){
                	sql.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
                }
        	}else if(asset==2){
        		if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
        			sql.append(" and model.name like '%"+assetsBase.getName()+"%' ");
        		}
        		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
        			sql.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
        		}
        		if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
        			sql.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
        		}
        		if(assetsBase.getAssetsProducerByProduceId()!=null){
        			if(assetsBase.getAssetsProducerByProduceId().getId()!=null&&assetsBase.getAssetsProducerByProduceId().getId()>0){
        				sql.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsProducerBySupportId()!=null){
        			if(assetsBase.getAssetsProducerBySupportId().getId()!=null&&assetsBase.getAssetsProducerBySupportId().getId()>0){
        				sql.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsType()!=null){
        			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
        				sql.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsState()!=null){
        			if(assetsBase.getAssetsState().getId()!=null&&assetsBase.getAssetsState().getId()>0){
        				sql.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
        			}
        		}
        		
        		if(assetsBase.getLocation()!=null){
        			if(assetsBase.getLocation().getId()!=null&&assetsBase.getLocation().getId()>0){
        				sql.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
        				sql.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getDepartment()!=null){
        				if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
            				sql.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
            			}
        			}
        			
        		}
        		
        		if(assetsBase.getUsersByChargeId()!=null){
        			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
        				sql.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
        			}
        		}
        		
        		if(assetsBase.getUsersByChargeId()!=null){
        			if(assetsBase.getUsersByChargeId().getDepartment()!=null){
        				if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
            				sql.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"' ");
            			}
        			}
        			
        		}
        		if(assetsBase.getBuildlocation()!=null){
        			if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
        				sql.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"'");
        			}
        		}
        		if(assetsBase.getBuyDate()!=null){
        			sql.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
        		}
                if(assetsBase.getExitfacotryDate()!=null){
                	sql.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
        		}
                if(assetsBase.getInDate()!=null){
                	sql.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
                }
        	}
    	}else{
    		if(asset==0){
    			sql.append(" and model.usersByChargeId.id='"+user.getId()+"' ");
        	}else if(asset==1){
        		sql.append(" and model.usersByChargeId.department.id='"+user.getDepartment().getId()+"' ");
        	}
    	}
    	sql.append("order by valueUnit asc");
    	String queryString = sql.toString();
		return assetsBaseDAO.getAllRow(queryString);	
	}
	
	public List<AssetsBase> querys(AssetsBase assets){
		return assetsBaseDAO.queryByHqls(assets);		
	}
	
	public List<AssetsBase> querySql(String Sql){
		return assetsBaseDAO.findByHql(Sql);
	}
	
        public PageBean qualityqueryByPage(AssetsBase assetsBase, int pageSize, int page) {
		
		StringBuffer sb=new StringBuffer("from AssetsBase as model where assetsState.sequence!='' and model.des='0' ");
		if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
			sb.append(" and model.name like '%"+assetsBase.getName()+"%' ");
		}
		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
			sb.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
		}
		if(assetsBase.getAssetsType()!=null){
			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
				sb.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
			}
		}
		if(assetsBase.getAssetsState()!=null){
			if(assetsBase.getAssetsState().getId()>0){
				sb.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
			}
		}
		if(assetsBase.getUsersByUsersId()!=null){
			if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
				sb.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
			}
		}
		if(assetsBase.getUsersByUsersId()!=null){
			if(assetsBase.getUsersByUsersId().getDepartment()!=null){
				if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
					sb.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
				}
			}
			
		}
		
		if(assetsBase.getUsersByChargeId()!=null){
			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
				sb.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
			}
		}
		
		if(assetsBase.getUsersByChargeId()!=null){
			if(assetsBase.getUsersByChargeId().getDepartment()!=null){
				if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
					sb.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"' ");
				}
			}
			
		}
		if(assetsBase.getExitfacotryDate()!=null){
			sb.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"'");
		}
		if(assetsBase.getBuyDate()!=null){
			sb.append(" and model.buyDate='"+assetsBase.getBuyDate()+"'");
		}
		if(assetsBase.getInDate()!=null){
			sb.append(" and model.inDate='"+assetsBase.getInDate()+"'");
		}
		sb.append("order by assetsState.sequence asc");
		String queryString =sb.toString();
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数
		List<AssetsBase> list = assetsBaseDAO.findAll(queryString,offset,length);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	    }
    public PageBean query(AssetsBase assetsBase, int pageSize, int page,Users user,int asset) {
    	StringBuffer sql=new StringBuffer("from AssetsBase as model where model.assetsState.id!='' and model.des='0' ");
    	if(assetsBase!=null){
    		if(asset==0){
    			if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
    				sql.append(" and model.name like '%"+assetsBase.getName()+"%' ");
    			}
    			if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
    				sql.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
    			}
    			if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
    				sql.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
    			}
    			if(assetsBase.getAssetsProducerByProduceId()!=null){
    				if(assetsBase.getAssetsProducerByProduceId().getId()!=null&&assetsBase.getAssetsProducerByProduceId().getId()>0){
    					sql.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
    				}
    			}
    			if(assetsBase.getAssetsProducerBySupportId()!=null){
    				if(assetsBase.getAssetsProducerBySupportId().getId()!=null&&assetsBase.getAssetsProducerBySupportId().getId()>0){
    					sql.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
    				}
    			}
    			if(assetsBase.getAssetsType()!=null){
    				if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
    					sql.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
    				}
    			}
    			if(assetsBase.getAssetsState()!=null){
    				if(assetsBase.getAssetsState().getId()!=null&&assetsBase.getAssetsState().getId()>0){
    					sql.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
    				}
    			}
    			if(assetsBase.getLocation()!=null){
    				if(assetsBase.getLocation().getId()!=null&&assetsBase.getLocation().getId()>0){
    					sql.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
    				}
    			}
    			if(assetsBase.getUsersByUsersId()!=null){
    				if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
    					sql.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
    				}
    			}
    			if(assetsBase.getUsersByUsersId()!=null){
    				if(assetsBase.getUsersByUsersId().getDepartment()!=null){
    					if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
        					sql.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
        				}
    				}
    			}
    			
    			sql.append(" and model.usersByChargeId.id='"+user.getId()+"' ");
    			
    			if(assetsBase.getUsersByChargeId()!=null){
    				if(assetsBase.getUsersByChargeId().getDepartment()!=null){
    					if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
        					sql.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"' ");
        				}
    				}
    			}
    			
    			if(assetsBase.getBuildlocation()!=null){
        			if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
        				sql.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"'");
        			}
        		}
    			if(assetsBase.getBuyDate()!=null){
    				sql.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
    			}
    	        if(assetsBase.getExitfacotryDate()!=null){
    	        	sql.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
    			}
    	        if(assetsBase.getInDate()!=null){
    	        	sql.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
    	        }
        		
        	}else if(asset==1){
        		if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
        			sql.append(" and model.name like '%"+assetsBase.getName()+"%' ");
        		}
        		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
        			sql.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
        		}
        		if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
        			sql.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
        		}
        		if(assetsBase.getAssetsProducerByProduceId()!=null){
        			if(assetsBase.getAssetsProducerByProduceId().getId()!=null&&assetsBase.getAssetsProducerByProduceId().getId()>0){
        				sql.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsProducerBySupportId()!=null){
        			if(assetsBase.getAssetsProducerBySupportId().getId()!=null&&assetsBase.getAssetsProducerBySupportId().getId()>0){
        				sql.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsType()!=null){
        			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
        				sql.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsState()!=null){
        			if(assetsBase.getAssetsState().getId()!=null&&assetsBase.getAssetsState().getId()>0){
        				sql.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
        			}
        		}
        		
        		if(assetsBase.getLocation()!=null){
        			if(assetsBase.getLocation().getId()!=null&&assetsBase.getLocation().getId()>0){
        				sql.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
        				sql.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getDepartment()!=null){
        				if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
            				sql.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
            			}
        			}
        		}
        		if(assetsBase.getUsersByChargeId()!=null){
        			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
        				sql.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
        			}
        		}
        		sql.append(" and model.usersByChargeId.department.id='"+user.getDepartment().getId()+"' ");
        		if(assetsBase.getBuildlocation()!=null){
        			if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
        				sql.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"'");
        			}
        		}
        		if(assetsBase.getBuyDate()!=null){
        			sql.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
        		}
                if(assetsBase.getExitfacotryDate()!=null){
                	sql.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
        		}
                if(assetsBase.getInDate()!=null){
                	sql.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
                }
        	}else if(asset==2){
        		if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
        			sql.append(" and model.name like '%"+assetsBase.getName()+"%' ");
        		}
        		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
        			sql.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
        		}
        		if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
        			sql.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
        		}
        		if(assetsBase.getAssetsProducerByProduceId()!=null){
        			if(assetsBase.getAssetsProducerByProduceId().getId()!=null&&assetsBase.getAssetsProducerByProduceId().getId()>0){
        				sql.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsProducerBySupportId()!=null){
        			if(assetsBase.getAssetsProducerBySupportId().getId()!=null&&assetsBase.getAssetsProducerBySupportId().getId()>0){
        				sql.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsType()!=null){
        			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
        				sql.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
        			}
        		}
        		if(assetsBase.getAssetsState()!=null){
        			if(assetsBase.getAssetsState().getId()!=null&&assetsBase.getAssetsState().getId()>0){
        				sql.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
        			}
        		}
        		if(assetsBase.getLocation()!=null){
        			if(assetsBase.getLocation().getId()!=null&&assetsBase.getLocation().getId()>0){
        				sql.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
        			}
        		}
        		
        		if(assetsBase.getUsersByChargeId()!=null){
        			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
        				sql.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
        			}
        		}
        		
        		if(assetsBase.getUsersByChargeId()!=null){
        			if(assetsBase.getUsersByChargeId().getDepartment()!=null){
        				if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
            				sql.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"'");
            			}
        			}
        		}
        		
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
        				sql.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
        			}
        		}
        		if(assetsBase.getUsersByUsersId()!=null){
        			if(assetsBase.getUsersByUsersId().getDepartment()!=null){
        				if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
            				sql.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
            			}
        			}
        		}
        		
        		if(assetsBase.getBuildlocation()!=null){
        			if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
        				sql.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"'");
        			}
        		}
        		if(assetsBase.getBuyDate()!=null){
        			sql.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
        		}
                if(assetsBase.getExitfacotryDate()!=null){
                	sql.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
        		}
                if(assetsBase.getInDate()!=null){
                	sql.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
                }
        	}
    	}else{
    		if(asset==0){
    			sql.append(" and model.usersByChargeId.id='"+user.getId()+"' ");
        	}else if(asset==1){
        		sql.append(" and model.usersByChargeId.department.id='"+user.getDepartment().getId()+"' ");
        	}
    	}
    	sql.append("order by valueUnit asc");
    	String queryString = sql.toString();
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsBase> list = assetsBaseDAO.findAll(queryString,offset,length);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
    }
	
	public PageBean queryByPage(AssetsBase assetsBase, int pageSize, int page) {
		
		StringBuffer sb=new StringBuffer("from AssetsBase as model where model.assetsState.id!=''");
		if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
			sb.append(" and model.name like '%"+assetsBase.getName()+"%' ");
		}
		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
			sb.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
		}
		if(assetsBase.getModel()!=null&&!assetsBase.getModel().equals("")){
			sb.append(" and model.model like '%"+assetsBase.getModel()+"%' ");
		}
		if(assetsBase.getAssetsProducerByProduceId()!=null){
			if(assetsBase.getAssetsProducerByProduceId().getId()>0){
				sb.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
			}
		}
		if(assetsBase.getAssetsProducerBySupportId()!=null){
			if(assetsBase.getAssetsProducerBySupportId().getId()>0){
				sb.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
			}
		}
		if(assetsBase.getAssetsType()!=null){
			if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
				sb.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
			}
		}
		if(assetsBase.getAssetsState()!=null){
			if(assetsBase.getAssetsState().getId()>0){
				sb.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
			}
		}
		if(assetsBase.getLocation()!=null){
			if(assetsBase.getLocation().getId()>0){
				sb.append(" and model.location.id='"+assetsBase.getLocation().getId()+"' ");
			}
		}
		if(assetsBase.getUsersByChargeId()!=null){
			if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
				sb.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
			}
		}
		
		if(assetsBase.getUsersByChargeId()!=null){
			if(assetsBase.getUsersByChargeId().getDepartment()!=null){
				if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
					sb.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"'");
    			}
			}
		}
		
		if(assetsBase.getUsersByUsersId()!=null){
			if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
				sb.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
			}
		}
		if(assetsBase.getUsersByUsersId()!=null){
			if(assetsBase.getUsersByUsersId().getDepartment()!=null){
				if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
					sb.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
    			}
			}
		}
		
		
		if(assetsBase.getBuyDate()!=null){
			sb.append(" and model.buyDate='"+assetsBase.getBuyDate()+"' ");
		}
        if(assetsBase.getExitfacotryDate()!=null){
        	sb.append(" and model.exitfacotryDate='"+assetsBase.getExitfacotryDate()+"' ");
		}
        if(assetsBase.getInDate()!=null){
        	sb.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
        }
		
		sb.append("order by valueUnit asc");
		String queryString =sb.toString();
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsBase> list = assetsBaseDAO.findAll(queryString,offset,length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}
	
	
        public PageBean batchqueryByPage(AssetsBase assetsBase, int pageSize, int page) {
    	StringBuffer sb=new StringBuffer("from AssetsBase as model where model.assetsState.id is null ");
        if(assetsBase!=null){
    		if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
    			sb.append(" and model.codeId like '%"+assetsBase.getCodeId()+"%' ");
    		}
            if(assetsBase.getInDate()!=null){
            	sb.append(" and model.inDate='"+assetsBase.getInDate()+"' ");
            }
            if(assetsBase.getM_des()!=null){
            	sb.append(" and model.m_des like '%"+assetsBase.getM_des()+"%'");
            }
        }
		String queryString =sb.toString();
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsBase> list = assetsBaseDAO.findAll(queryString,offset,length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}
	
        public PageBean queryByPages(AssetsBase assetsBase, int pageSize, int page) {
		
		StringBuffer sb=new StringBuffer("from AssetsBase as model where model.des='0' and 1=1 ");
		if(assetsBase!=null){
			if(assetsBase.getName()!=null&&!assetsBase.getName().equals("")){
				sb.append(" and model.name like '%"+assetsBase.getName()+"%' ");
			}
			if(assetsBase.getCodeId()!=null&&!assetsBase.getCodeId().equals("")){
				sb.append(" and model.codeId like'%"+assetsBase.getCodeId()+"%' ");
			}
			if(assetsBase.getAssetsProducerByProduceId()!=null){
				if(assetsBase.getAssetsProducerByProduceId().getId()>0){
					sb.append(" and model.assetsProducerByProduceId.id='"+assetsBase.getAssetsProducerByProduceId().getId()+"' ");
				}
			}
			if(assetsBase.getAssetsProducerBySupportId()!=null){
				if(assetsBase.getAssetsProducerBySupportId().getId()>0){
					sb.append(" and model.assetsProducerBySupportId.id='"+assetsBase.getAssetsProducerBySupportId().getId()+"' ");
				}
			}
			if(assetsBase.getAssetsType()!=null){
				if(assetsBase.getAssetsType().getId()!=null&&assetsBase.getAssetsType().getId()>0){
					sb.append(" and model.assetsType.id='"+assetsBase.getAssetsType().getId()+"' ");
				}
			}
			if(assetsBase.getAssetsState()!=null){
				if(assetsBase.getAssetsState().getId()>0){
					sb.append(" and model.assetsState.id='"+assetsBase.getAssetsState().getId()+"' ");
				}
			}
			if(assetsBase.getUsersByChargeId()!=null){
				if(assetsBase.getUsersByChargeId().getId()!=null&&assetsBase.getUsersByChargeId().getId()>0){
					sb.append(" and model.usersByChargeId.id='"+assetsBase.getUsersByChargeId().getId()+"' ");
				}
			}
			
			if(assetsBase.getUsersByChargeId()!=null){
				if(assetsBase.getUsersByChargeId().getDepartment()!=null){
					if(assetsBase.getUsersByChargeId().getDepartment().getId()!=null&&assetsBase.getUsersByChargeId().getDepartment().getId()>0){
						sb.append(" and model.usersByChargeId.department.id='"+assetsBase.getUsersByChargeId().getDepartment().getId()+"'");
	    			}
				}
			}
			
			if(assetsBase.getUsersByUsersId()!=null){
				if(assetsBase.getUsersByUsersId().getId()!=null&&assetsBase.getUsersByUsersId().getId()>0){
					sb.append(" and model.usersByUsersId.id='"+assetsBase.getUsersByUsersId().getId()+"' ");
				}
			}
			if(assetsBase.getUsersByUsersId()!=null){
				if(assetsBase.getUsersByUsersId().getDepartment()!=null){
					if(assetsBase.getUsersByUsersId().getDepartment().getId()!=null&&assetsBase.getUsersByUsersId().getDepartment().getId()>0){
						sb.append(" and model.usersByUsersId.department.id='"+assetsBase.getUsersByUsersId().getDepartment().getId()+"' ");
	    			}
				}
			}
			if(assetsBase.getBuildlocation()!=null){
				if(assetsBase.getBuildlocation().getId()!=null&&assetsBase.getBuildlocation().getId()>0){
					sb.append(" and model.buildlocation.id='"+assetsBase.getBuildlocation().getId()+"' ");
				}
			}
		}
		sb.append("order by model.assetsState.sequence asc");
		String queryString =sb.toString();
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsBase> list = assetsBaseDAO.findAll(queryString,offset,length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}

	public PageBean queryHistoryStatistic(String queryString, int pageSize,
			int page) {

		int currentPage = PageBean.countCurrentPage(page);
		int allRow = assetsBaseDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsBase> list = assetsBaseDAO.queryStatistic(queryString,
				offset, length);

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}

	public boolean batchInserts(final List<AssetsBase> assetsBases) {
		boolean a=assetsBaseDAO.batchInsert(assetsBases);
		if(a){
			for(int i=0;i<assetsBases.size();i++){
				AssetsInfo info=new AssetsInfo();
				info.setAssetsBase(assetsBases.get(i));
				assetsInfoDAO.save(info);
			}
		}
		return a;
	}
	
	public boolean sql(String sql) throws SQLException{
		return assetsBaseDAO.sql(sql);
	}
	public List sqllist(String sql) throws SQLException{
		return assetsBaseDAO.sqllist(sql);
	}

	public void save(AssetsBase assets) {
		try {
			assetsBaseDAO.save(assets);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void assetsinfosave(AssetsInfo info){
		try {
			assetsInfoDAO.save(info);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	public void assetsinfodelete(AssetsInfo info){
		assetsInfoDAO.delete(info);
	}
	

	public void setAssetsBaseDAO(AssetsBaseDAO assetsBaseDAO) {
		this.assetsBaseDAO = assetsBaseDAO;
	}

	public void setAssetsDAO(AssetsBaseDAO assetsBaseDAO) {
		this.assetsBaseDAO = assetsBaseDAO;
	}

	public void setAssetsHistoryStateDAO(
			AssetsHistoryStateDAO assetsHistoryStateDAO) {
		this.assetsHistoryStateDAO = assetsHistoryStateDAO;
	}

	public void setAssetsInfoDAO(AssetsInfoDAO assetsInfoDAO) {
		this.assetsInfoDAO = assetsInfoDAO;
	}

	public void setAssetsTypeDAO(AssetsTypeDAO assetsTypeDAO) {
		this.assetsTypeDAO = assetsTypeDAO;
	}

	public void setDeviceInfoDAO(AssetsInfoDAO assetsInfoDAO) {
		this.assetsInfoDAO = assetsInfoDAO;
	}

	public void setHistoryDeviceStateDAO(
			AssetsHistoryStateDAO assetsHistoryStateDAO) {
		this.assetsHistoryStateDAO = assetsHistoryStateDAO;
	}

	@SuppressWarnings("unchecked")
	public void updateAssetsByCode(AssetsBase assets) {
		if (assets != null) {
			assetsBaseDAO.update(assets);

		}
	}

	public void AssetsBasesaveOrUpdate(AssetsBase assets){
		assetsBaseDAO.saveOrUpdate(assets);
	}
	
	public void AssetsInfosaveOrUpdate(AssetsInfo info){
		assetsInfoDAO.saveorupdate(info);
	}
	
	
	public CartogramInfo getCartogramInfos(AssetsBase assets) {
		int qureySize = assetsBaseDAO.findSizeByAssetsBase(assets);
		String sql="from AssetsBase as model where model.assetsState.id!='' and model.des='0'";
		int totle = this.getAllRowCount(sql);

		String percent = percent(qureySize, totle);

		CartogramInfo cartogramInfo = new CartogramInfo();
		cartogramInfo.setAll(totle + "");
		cartogramInfo.setTotle(qureySize + "");
		cartogramInfo.setPercent(percent);

		Pie3D chart = new Pie3D();
		chart.setCaption("资产数量百分比统计");
		chart.setShowFCMenuItem("0");
		chart.setDecimals("0");
		chart.setEnableSmartLabels("1");
		chart.setBgColor("99CCFF,FFFFFF");
		chart.setBgAlpha("40,100");
		chart.setBaseFont("宋体");
		chart.setBaseFontSize("12");

		ReportUtil.setValue(chart, "查询资产个数", String.valueOf(qureySize));
		ReportUtil.setValue(chart, "其他资产总数", String.valueOf(totle - qureySize));
		cartogramInfo.setXmlString(chart.toString());

		return cartogramInfo;
	}
	
	public CartogramInfo getCartogramInfoprice(AssetsBase assets) {
		String sqlall="select sum(model.price) as allprice from AssetsBase as model where model.assetsState.id!='' and  model.des='0'";
		Object all=assetsBaseDAO.getAllRow(sqlall).get(0);
		double allprice=0;
		DecimalFormat df = new DecimalFormat("0.##");    
		Double allprice1= new Double("0");  
		if(all!=null){
			allprice=Double.parseDouble(all.toString());
			allprice1= new Double(all.toString());  
		}
		String sql = "select sum(model.price) as allprice from AssetsBase as model where model.assetsState.id!='' and  model.des='0'";
		if (assets.getAssetsProducerByProduceId()!=null) {
			if(assets.getAssetsProducerByProduceId().getId()>0){
				sql = sql + " and  model.assetsProducerByProduceId.id='"+ assets.getAssetsProducerByProduceId().getId() + "'";
			}
		}
		if (assets.getAssetsProducerBySupportId()!=null) {
			if(assets.getAssetsProducerBySupportId().getId()>0){
				sql = sql + " and  model.assetsProducerBySupportId.id='"+ assets.getAssetsProducerBySupportId().getId()+ "'";
			}
		}
		if (assets.getAssetsType()!=null) {
			if(assets.getAssetsType().getId()!=null&&assets.getAssetsType().getId()>0){
				sql = sql + " and  model.assetsType.id='" + assets.getAssetsType().getId() + "'";
			}
		}
		if (assets.getAssetsState()!=null) {
			if(assets.getAssetsState().getId()>0){
				sql = sql + " and  model.assetsState.id='" + assets.getAssetsState().getId() + "'";
			}
		}
		if (assets.getLocation()!=null) {
			if(assets.getLocation().getId()>0){
				sql = sql + " and  model.location.id='" +assets.getLocation().getId()+ "'";
			}
		}
		if(assets.getUsersByChargeId()!=null){
			if(assets.getUsersByChargeId().getId()!=null&&assets.getUsersByChargeId().getId()>0){
				sql = sql + " and model.usersByChargeId.id='"+assets.getUsersByChargeId().getId()+"' ";
			}
		}
		
		if(assets.getUsersByChargeId()!=null){
			if(assets.getUsersByChargeId().getDepartment()!=null){
				if(assets.getUsersByChargeId().getDepartment().getId()!=null&&assets.getUsersByChargeId().getDepartment().getId()>0){
					sql = sql + " and model.usersByChargeId.department.id='"+assets.getUsersByChargeId().getDepartment().getId()+"'";
    			}
			}
		}
		
		if(assets.getUsersByUsersId()!=null){
			if(assets.getUsersByUsersId().getId()!=null&&assets.getUsersByUsersId().getId()>0){
				sql = sql + " and model.usersByUsersId.id='"+assets.getUsersByUsersId().getId()+"' ";
			}
		}
		if(assets.getUsersByUsersId()!=null){
			if(assets.getUsersByUsersId().getDepartment()!=null){
				if(assets.getUsersByUsersId().getDepartment().getId()!=null&&assets.getUsersByUsersId().getDepartment().getId()>0){
					sql = sql + " and model.usersByUsersId.department.id='"+assets.getUsersByUsersId().getDepartment().getId()+"' ";
    			}
			}
		}
		Object query=assetsBaseDAO.getAllRow(sql).get(0);
		double queryprice=0;
		Double queryprice1= new Double("0");  
		if(query!=null){
			queryprice=Double.parseDouble(query.toString());
			queryprice1= new Double(query.toString());  
		}
		String percent = percent(queryprice, allprice);
		CartogramInfo cartogramInfo = new CartogramInfo();
		cartogramInfo.setAll(df.format(allprice1));
		cartogramInfo.setTotle(df.format(queryprice1));
		cartogramInfo.setPercent(percent);
		Pie3D chart = new Pie3D();
		chart.setCaption("资产价值百分比统计");
		chart.setShowFCMenuItem("0");
		chart.setDecimals("0");
		chart.setEnableSmartLabels("1");
		chart.setBgColor("99CCFF,FFFFFF");
		chart.setBgAlpha("40,100");
		chart.setBaseFont("宋体");
		chart.setBaseFontSize("12");
		ReportUtil.setValue(chart, "查询资产价值", String.valueOf(queryprice1));
		ReportUtil.setValue(chart, "其他资产价值", String.valueOf(allprice1 - queryprice1));
		cartogramInfo.setXmlString(chart.toString());
		return cartogramInfo;
	}
	
	
	public static String percent(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		str = nf.format(p3);
		return str;
	}

	public int getAssetsSizeByState(AssetsBase assets) {

		return assetsBaseDAO.findSizeByAssetsBase(assets);
	}
	
	
	public AssetsBase findById(int code){
		return assetsBaseDAO.findById(code);
	}
	
	public AssetsBase findByCodeId(String codeId){
		return assetsBaseDAO.findByCodeId(codeId);
	}
	
	public AssetsBase findByPn(String pn){
		return assetsBaseDAO.findByPn(pn);
	}
	
	public List findByProperty(String code,String value){
		return assetsBaseDAO.findByProperty(code, value);
	}
	public List<AssetsBase> findByUserId(Integer userid)
	{
		return assetsBaseDAO.findByUserId(userid);
	}
	
	public List computer(){
		return assetsBaseDAO.computer();
	}
	
	public List device(){
		return assetsBaseDAO.device();
	}
}
