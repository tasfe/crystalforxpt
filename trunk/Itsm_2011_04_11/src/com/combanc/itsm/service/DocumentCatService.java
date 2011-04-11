package com.combanc.itsm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.combanc.itsm.dao.DocumentAuthorityDAO;
import com.combanc.itsm.dao.DocumentCatDAO;
import com.combanc.itsm.pojo.DocumentAuthority;
import com.combanc.itsm.pojo.DocumentCat;

public class DocumentCatService {
	private DocumentCatDAO documentCatDAO;
	private DocumentAuthorityDAO documentAuthorityDAO;

	public List<DocumentCat> findAll() {
		return documentCatDAO.findAll();
	}

	public DocumentCat findById(Integer documentCatId) {
		DocumentCat documentCat = documentCatDAO
				.findByIdWithAuthority(documentCatId);
		if (documentCat == null)
			documentCat = documentCatDAO.findById(documentCatId);
		return documentCat;
	}

	public List<DocumentCat> findAllByPid(Integer pid) {
		return documentCatDAO.findByPid(pid);
	}

	public void save(DocumentCat documentCat) {
		documentCatDAO.save(documentCat);
		Integer parentId = documentCat.getPid();
		if (parentId != null && parentId != 0) {
			DocumentCat parent = documentCatDAO.findById(parentId);
			documentCat.setCode(parent.getCode() + "|" + documentCat.getId()
					);
		} else {
			documentCat.setCode("|" + documentCat.getId());
		}
//		documentCat.setDocumentAuthoritys(getDocumentAuthoritys(checkbox,
//				documentCat.getId()));
		documentCatDAO.update(documentCat);
	}

	public void update(DocumentCat documentCat, List<String> checkbox) {
//		documentAuthorityDAO.deleteAllByDid(documentCat.getId());
//		documentCat.setDocumentAuthoritys(getDocumentAuthoritys(checkbox,
//				documentCat.getId()));
		
		documentCatDAO.update(documentCat);
	}

	public void deleteById(Integer documentCatId) {
		DocumentCat documentCat = null;
		if (documentCatId != null) {
			documentCat = documentCatDAO.findById(documentCatId);
		}
		if (documentCat != null) {
			String code = documentCat.getCode();
			List<DocumentCat> list = documentCatDAO
					.findBySql("from DocumentCat d where d.code like '%" + code
							+ "%'");
			for (DocumentCat d : list) {
				documentAuthorityDAO.deleteAllByDid(d.getId());
				documentCatDAO.delete(d);
			}
		}
	}

	public Set<DocumentAuthority> getDocumentAuthoritys(List<String> checkbox,
			Integer did) {
		Set<DocumentAuthority> set = new HashSet<DocumentAuthority>();
		StringBuffer buffer = new StringBuffer();
		for (String s : checkbox) {
			buffer.append(s);
		}
		String[] authoritys = buffer.toString().split("@");
		for (int i = 0; i < authoritys.length; i++) {
			String[] ss = authoritys[i].split("#");
			if (ss.length > 1) {
				DocumentAuthority da = new DocumentAuthority();
				da.setDid(did);
				for (int j = 0; j < ss.length; j++) {
					if (j == 0) {
						da.setTid(Integer.valueOf(ss[0]));
					} else {
						if ("S".equals(ss[j])) {
							da.setAuthoritySel(1);
						} else if ("A".equals(ss[j])) {
							da.setAuthorityAdd(1);
						} else if ("U".equals(ss[j])) {
							da.setAuthorityUpd(1);
						} else if ("D".equals(ss[j])) {
							da.setAuthorityDel(1);
						}
					}
				}
				set.add(da);
			}
		}
		return set;
	}

	public DocumentCatDAO getDocumentCatDAO() {
		return documentCatDAO;
	}

	public void setDocumentCatDAO(DocumentCatDAO documentCatDAO) {
		this.documentCatDAO = documentCatDAO;
	}

	public DocumentAuthorityDAO getDocumentAuthorityDAO() {
		return documentAuthorityDAO;
	}

	public void setDocumentAuthorityDAO(
			DocumentAuthorityDAO documentAuthorityDAO) {
		this.documentAuthorityDAO = documentAuthorityDAO;
	}

	/**
	 * @param documentCatId
	 * @return
	 */
	public boolean containsChild(Integer documentCatId) {
		List dList =documentCatDAO.findByPid(documentCatId);
		if(dList!=null&&!dList.isEmpty())
		{
			return true;
		}
		return false;
	}

}
