package com.combanc.itsm.dao;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.combanc.itsm.pojo.AssetsState;
import com.combanc.itsm.pojo.AssetsType;

public class AssetStatisticDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AssetStatisticDAO.class);

	// 查询各个资产类型的数量
	public String getTypeStatistic() {
		log.debug("find AssetsStatistic Type");
		try {
			// TODO Auto-generated method stub
			Query query = getSession().createSQLQuery(
							"SELECT aty.name,asstcount.countnum,asstcount.pricenum FROM assets_type aty,"
									+ "(SELECT (SELECT asst.code FROM assets_type asst WHERE asst.id=ab.type_code) AS asstcode,COUNT(*) AS countnum,SUM(ab.price) AS pricenum FROM assets_base ab GROUP BY  LEFT((SELECT asst.code FROM assets_type asst WHERE asst.id=ab.type_code),2)"
									+ ")  asstcount"+ " WHERE LEFT(aty.description,2) = LEFT(asstcount.asstcode,2) AND aty.flag=':0:'");
			List<Object[]> object = query.list();
			String list = "";
			DecimalFormat df = new DecimalFormat("0.00");
			for (Object[] o : object) {
				// o[2]==null?"":df.format(o[2])
				Object s = (Object) (o[2] == null ? "" : df.format(o[2]));
				list += "<AssetStatisticType UserName=\"" + o[0]+ "\" AuditDesc=\"" + o[1] + "\" TypePrice=\"" + s+ "\" > " + "</AssetStatisticType>";
			}
			return "<AssetStatisticTypes>" + list + "</AssetStatisticTypes>";
		} catch (RuntimeException re) {
			log.error("find AssetStatistic Type failed", re);
			throw re;
		}
	}
	//选择设备类型时 查询这个类型的年份统计
	public String getYearStatisticByParam(int typeId){
		// TODO Auto-generated method stub
		log.debug("find getYearStatisticByParam Date");
		try {
			AssetsType assetType = (AssetsType) getHibernateTemplate().get(
					"com.combanc.itsm.pojo.AssetsType", typeId);
			String typeflag = assetType.getFlag();
			String sqlstr = "select * from(SELECT YEAR(ab.in_date) yea,COUNT(*),SUM(ab.price) FROM assets_base ab  WHERE ab.type_code IN(SELECT aty.id FROM assets_type aty WHERE aty.flag LIKE '%"+typeflag+typeId+":%' OR aty.id="+typeId+") GROUP BY LEFT (ab.in_date,4)  ORDER BY YEAR(ab.in_date) DESC LIMIT 15) yearcount order by yearcount.yea ";
			Query query = getSession()
					.createSQLQuery(sqlstr);
			List<Object[]> object = query.list();
			String list = "";
			 DecimalFormat df = new DecimalFormat("0.00");
			for (Object[] o : object) {
				Object year = (Object) (o[0]==null?"未知":o[0]);
				Object s = (Object) (o[2] == null ? "" : df.format(o[2]));
				list += "<AssetStatisticYear Year=\"" + year + "年"	+ "\" YearCount=\"" + o[1] + "\" YearPrice=\"" + s+ "\" > </AssetStatisticYear>";
			}
			return "<AssetStatisticYears>" + list + "</AssetStatisticYears>";
		} catch (RuntimeException re) {
			log.error("find getYearStatisticByParam  failed", re);
			throw re;
		}
	}
	// 获取每年所购的资产数量
	public String getYearStatistic() {
		log.debug("find AssetsStatistic Date");
		try {
			Query query = getSession()
					.createSQLQuery(
							"select * from(SELECT YEAR(ab.in_date) yea,COUNT(*),SUM(ab.price) FROM assets_base ab GROUP BY LEFT (ab.in_date,4)  ORDER BY YEAR(ab.in_date) DESC LIMIT 15) yearcount order by yearcount.yea ");
			List<Object[]> object = query.list();
			String list = "";
			 DecimalFormat df = new DecimalFormat("0.00");
			for (Object[] o : object) {
				Object year = (Object) (o[0]==null?"未知":o[0]);
				Object s = (Object) (o[2] == null ? "" : df.format(o[2]));
				list += "<AssetStatisticYear Year=\"" + year + "年"	+ "\" YearCount=\"" + o[1] + "\" YearPrice=\"" + s+ "\" > </AssetStatisticYear>";
			}
			return "<AssetStatisticYears>" + list + "</AssetStatisticYears>";
		} catch (RuntimeException re) {
			log.error("find AssetStatistic Date failed", re);
			throw re;
		}
	}

	/*
	 * 获取资产状态信息
	 */
	public String getStateStatistic() {

		log.debug("finding getAssetStateStatistic instances");
		try {
			String list = "";
			String queryString = "from AssetsState";
			List<AssetsState> assetStateList = getHibernateTemplate().find(queryString);
			for (int i = 0; i < assetStateList.size(); i++) {
				if (assetStateList.get(i).getId()!=0) {
				Query query = getSession().createSQLQuery(
						"select count(*) from assets_base where state="
								+ assetStateList.get(i).getId());
				List<BigInteger> object = query.list();
				Integer s = object.get(0).intValue();

				list += "<AssetState id=\"" + assetStateList.get(i).getId()
						+ "\" StateName=\"" + assetStateList.get(i).getName()
						+ "\" CountState=\"" + s + "\" > </AssetState>";
				}
			}
			return "<AssetStates>" + list + "</AssetStates>";
		} catch (RuntimeException re) {
			log.error("find getAssetStateStatistic failed", re);
			throw re;
		}
	}
	//查询子类型 根基父节点
	public List getChildType(int pid) {
		String queryString = "from AssetsType model where model.pid=" + pid;
		return getHibernateTemplate().find(queryString);
	}

	// h
	public String geTypeNode(int pid) {
		// TODO Auto-generated method stub
		String list = "";
		List<AssetsType> typeList = getChildType(pid);
		for (int j = 0; j < typeList.size(); j++) {
			int typeId = typeList.get(j).getId();
			list += "<TypeList typeId=\"" + typeId + "\" typeName=\""+ typeList.get(j).getName() + "\" type=\"" + 4 + "\" >";
			list += "<nodetemp typeIdTemp=\"" + 0 + "\" typeNameTemp=\""+ "temp" + "\" > </nodetemp>";
			list += "</TypeList>";
		}
		return "<list>" + list + "</list>";
	}

	// 获取类型树
	public String typeXmlList() {
		try {
			String list = "";
			String queryString = "from AssetsType model where model.pid=0";
			List<AssetsType> totalTypeList = getHibernateTemplate().find(queryString);

			for (int i = 0; i < totalTypeList.size(); i++) {
				int totalTypeId = totalTypeList.get(i).getId();
				list += "<TypeList typeId=\"" + totalTypeId + "\" typeName=\""
						+ totalTypeList.get(i).getName() + "\" > ";
				List<AssetsType> typeList2 = getChildType(totalTypeId);
				for (int j = 0; j < typeList2.size(); j++) {
					int typeId2 = typeList2.get(j).getId();
					list += "<TypeList typeId=\"" + typeId2 + "\" typeName=\""+ typeList2.get(j).getName() + "\" type=\"" + 4+ "\" > ";
					list += "<nodetemp typeIdTemp=\"" + 0+ "\" typeNameTemp=\"" + "temp"+ "\" > </nodetemp>";
					list += "</TypeList>";
				}
				list += "</TypeList>";
			}
			return "<list>" + "<TypeList typeId=\"" + 0 + "\" typeName=\""
					+ "全部类型" + "\"> </TypeList>" + list + "</list>";
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}
