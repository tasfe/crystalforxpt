package com.combanc.itsm.action.flow;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.combanc.common.core.QueryFilter;
import com.combanc.common.core.action.BaseActionSupport;
import com.combanc.common.util.BeanUtil;
import com.combanc.itsm.db.StringUtils;
import com.combanc.itsm.pojo.ProDefinition;
import com.combanc.itsm.pojo.ProType;
import com.combanc.itsm.service.flow.JbpmService;
import com.combanc.itsm.service.flow.ProDefinitionService;
import com.combanc.itsm.service.flow.ProTypeService;

public class ProDefinitionAction extends BaseActionSupport {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private ProTypeService proTypeService;

	@Resource
	private JbpmService jbpmService;
	private ProDefinition proDefinition;
	private Integer defId;

	public Integer getDefId() {
		return defId;
	}

	public void setDefId(Integer defId) {
		this.defId = defId;
	}

	public ProDefinition getProDefinition() {
		return this.proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	public ProDefinitionService getProDefinitionService() {
		return proDefinitionService;
	}

	public void setProDefinitionService(ProDefinitionService proDefinitionService) {
		this.proDefinitionService = proDefinitionService;
	}

	public ProTypeService getProTypeService() {
		return proTypeService;
	}

	public void setProTypeService(ProTypeService proTypeService) {
		this.proTypeService = proTypeService;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());

		String typeId = getRequest().getParameter("typeId");

		if ((StringUtils.isNotEmpty(typeId)) && (!"0".equals(typeId))) {
			filter.addFilter("Q_proType.typeId_L_EQ", typeId);
		}

		List list = this.proDefinitionService.getAll(filter);

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");

		return "success";
	}

	public String get() {
		if (this.defId != null) {
			this.proDefinition = ((ProDefinition) this.proDefinitionService
					.get(this.defId));
		} else {
			this.proDefinition = new ProDefinition();
			String proTypeId = getRequest().getParameter("proTypeId");
			if (StringUtils.isNotEmpty(proTypeId)) {
				ProType proType = (ProType) this.proTypeService.get(Integer
						.valueOf(proTypeId));
				this.proDefinition.setProType(proType);
			}

		}

		return "success";
	}

	public String defSave() {
		this.logger.info("...eneter defSave......");

		if (StringUtils.isNotEmpty(this.proDefinition.getDrawDefXml())) {
			Long uuid = Long.valueOf(Math.abs(UUID.randomUUID()
					.getLeastSignificantBits()));

			save();
		}

		return "success";
	}

	public String save() {
		Integer proTypeId = this.proDefinition.getProType().getTypeId();
		if (proTypeId != null) {
			ProType proType = (ProType) this.proTypeService.get(proTypeId);
			this.proDefinition.setProType(proType);
		}
		if (this.proDefinition.getDefId() != null) {
			ProDefinition proDef = (ProDefinition) this.proDefinitionService
					.get(this.proDefinition.getDefId());
			try {
				BeanUtil.copyNotNullProperties(proDef, this.proDefinition);
				this.jbpmService.saveOrUpdateDeploy(proDef);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		} else {
			Date curDate = new Date();
			Timestamp ts = new Timestamp(curDate.getTime());
			this.proDefinition.setCreatetime(ts);

			if (this.logger.isDebugEnabled()) {
				this.logger.info("---start deploy---");
			}

			this.jbpmService.saveOrUpdateDeploy(this.proDefinition);
		}

		return "success";
	}
}