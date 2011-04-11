package com.combanc.itsm.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.combanc.itsm.dao.SeverityTypDAO;
import com.combanc.itsm.pojo.SeverityTyp;

public class SeverityTypService {
	public static final Integer CATEGORY1 = 1;
	public static final Integer CATEGORY2 = 2;
	public static final Integer CATEGORY3 = 3;
	public static final Integer CATEGORY4 = 4;
	public static final Integer CATEGORY5 = 5;
	public static final Integer CATEGORY6 = 6;
	private SeverityTypDAO severityTypDAO;

	public void deleteById(Integer severityTypId) {
		SeverityTyp severityTyp = null;
		if (severityTypId != null)
			severityTyp = severityTypDAO.findById(severityTypId);
		if (severityTyp != null)
			severityTypDAO.delete(severityTyp);
	}

	public List<List<SeverityTyp>> findAllSeverityTyp() {
		List<SeverityTyp> allSeverityTyp = severityTypDAO
				.findBySql("from SeverityTyp s order by s.category asc, s.severityValue desc");
		return sortAllWithCategory(allSeverityTyp);
	}

	public SeverityTyp findById(Integer severityTypId) {
		return severityTypDAO.findById(severityTypId);
	}

	public List<SeverityTyp> findByCategory(Integer category) {
		return severityTypDAO
				.findBySql("from SeverityTyp s where s.category = " + category
						+ " order by s.severityValue desc");
	}

	public void save(SeverityTyp severityTyp) {
		severityTypDAO.save(severityTyp);
	}

	public void update(SeverityTyp severityTyp) {
		severityTypDAO.update(severityTyp);
	}

	public SeverityTypDAO getSeverityTypDAO() {
		return severityTypDAO;
	}

	public void setSeverityTypDAO(SeverityTypDAO severityTypDAO) {
		this.severityTypDAO = severityTypDAO;
	}

	private List<List<SeverityTyp>> sortAllWithCategory(
			List<SeverityTyp> allSeverityTyp) {
		List<List<SeverityTyp>> severityList = new ArrayList<List<SeverityTyp>>();
		List<Integer> categoryList = severityTypDAO
				.findBySql("select distinct s.category from SeverityTyp s order by s.category");
		for (Integer i : categoryList) {
			List<SeverityTyp> list = new ArrayList<SeverityTyp>();
			for (Iterator<SeverityTyp> it = allSeverityTyp.iterator(); it
					.hasNext();) {
				SeverityTyp s = it.next();
				if (i.equals(s.getCategory())) {
					list.add(s);
				}
			}
			severityList.add(list);
		}
		return severityList;
	}
}
