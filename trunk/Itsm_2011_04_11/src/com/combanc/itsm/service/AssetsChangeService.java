/**
 * 
 */
package com.combanc.itsm.service;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.support.TransactionTemplate;

import com.combanc.common.core.PageBean;
import com.combanc.common.core.service.BaseService;
import com.combanc.itsm.dao.AssetsChangeDAO;
import com.combanc.itsm.pojo.AssetsChange;


/**
 * @author Administrator
 * 
 */
public class AssetsChangeService extends BaseService<AssetsChange, Integer> {
	
	private AssetsChangeDAO assetsChangeDAO;
	public AssetsChangeDAO getAssetsChangeDAO() {
		return assetsChangeDAO;
	}

	public void setAssetsChangeDAO(AssetsChangeDAO assetsChangeDAO) {
		this.assetsChangeDAO = assetsChangeDAO;
	}

	public TransactionTemplate getTransactonTemplate() {
		return transactonTemplate;
	}

	public void setTransactonTemplate(TransactionTemplate transactonTemplate) {
		this.transactonTemplate = transactonTemplate;
	}

	private TransactionTemplate transactonTemplate;

	private static final Log log = LogFactory.getLog(AssetsChangeService.class);
	
	public void save(AssetsChange change){
		assetsChangeDAO.save(change);
	}
	
	public List findByassetsId(String code){
		return assetsChangeDAO.findByCode(Integer.parseInt(code));
	}
	
	public void delete(AssetsChange assetsChange){
		assetsChangeDAO.delete(assetsChange);
	}
	
    public PageBean queryByPage(AssetsChange assetsChange, int pageSize, int page) {
		
		StringBuffer sb=new StringBuffer("from AssetsChange as model where 1=1 ");
		if(assetsChange!=null){
			if(assetsChange.getAssetsState()!=null){
				if(assetsChange.getAssetsState().getId()!=null&&assetsChange.getAssetsState().getId()>0){
					sb.append(" and model.assetsState.id='"+assetsChange.getAssetsState().getId()+"'");
				}
			}
			if(assetsChange.getUsersByChargeid()!=null){
				if(assetsChange.getUsersByChargeid().getId()!=null&&assetsChange.getUsersByChargeid().getId()>0){
					sb.append(" and model.usersByChargeid.id='"+assetsChange.getUsersByChargeid().getId()+"'");
				}
			}
			if(assetsChange.getUsersByManagersid()!=null){
				if(assetsChange.getUsersByManagersid().getId()!=null&&assetsChange.getUsersByManagersid().getId()>0){
					sb.append(" and model.usersByManagersid.id='"+assetsChange.getUsersByManagersid().getId()+"'");
				}
			}
			if(assetsChange.getUsersByUserid()!=null){
				if(assetsChange.getUsersByUserid().getId()!=null&&assetsChange.getUsersByUserid().getId()>0){
					sb.append(" and model.usersByUserid.id='"+assetsChange.getUsersByUserid().getId()+"'");
				}
			}
			if(assetsChange.getChangeTime()!=null){
				sb.append(" and model.changeTime='"+assetsChange.getChangeTime()+"'");
			}
		}
		sb.append("order by changeTime desc");
		String queryString =sb.toString();
		int currentPage = PageBean.countCurrentPage(page);
		int allRow =assetsChangeDAO.getAllRowCount(queryString); // 总记录数
		int totalPage = PageBean.countTotalPage(pageSize, allRow); // 总页数
		int offset = PageBean.countOffset(pageSize, currentPage); // 当前页开始记录
		int length = pageSize; // 每页记录数

		List<AssetsChange> list = assetsChangeDAO.findAll(queryString,offset,length); 

		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;

	}
	
	public AssetsChange findById(int id){
		return assetsChangeDAO.findById(id);
	}
	
	public void update(AssetsChange change){
		assetsChangeDAO.update(change);
	}
}
