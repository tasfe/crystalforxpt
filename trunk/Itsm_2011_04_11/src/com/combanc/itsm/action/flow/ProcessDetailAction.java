package com.combanc.itsm.action.flow;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.itsm.pojo.ProDefinition;
import com.combanc.itsm.service.flow.ProDefinitionService;

public class ProcessDetailAction extends BaseActionSupport implements
	ServletRequestAware {
	  @Resource
	  private ProDefinitionService proDefinitionService;
	  private ProDefinition proDefinition;

	  public ProDefinition getProDefinition()
	  {
	    return this.proDefinition;
	  }

	  public void setProDefinition(ProDefinition proDefinition) {
	    this.proDefinition = proDefinition;
	  }

	  public String execute() throws Exception
	  {
	    String defId = getRequest().getParameter("defId");
	    this.proDefinition = ((ProDefinition)this.proDefinitionService.get(new Integer(defId)));
	    return "success";
	  }
}
