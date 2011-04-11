package com.combanc.itsm.service;

import java.util.List;
import com.combanc.itsm.dao.DocumentAuthorityDAO;
import com.combanc.itsm.pojo.DocumentAuthority;

public class DocumentAuthorityService {
	private DocumentAuthorityDAO documentAuthorityDAO;

	public String findAllByDidToString(Integer did) {
		List<DocumentAuthority> list = documentAuthorityDAO.findByDid(did);
		StringBuffer buffer = new StringBuffer();
		for (DocumentAuthority d : list) {
			buffer.append("@").append(d.getTid());
			buffer.append("#").append(d.getAuthoritySel());
			buffer.append("#").append(d.getAuthorityAdd());
			buffer.append("#").append(d.getAuthorityUpd());
			buffer.append("#").append(d.getAuthorityDel());
		}
		return buffer.toString();
	}

	public List<DocumentAuthority> findAllByTid(Integer tid) {
		return documentAuthorityDAO.findByTid(tid);
	}

	public void deleteAllByTid(Integer tid) {
		documentAuthorityDAO.deleteAllByTid(tid);
	}

	public void deleteAllByDid(Integer did) {
		documentAuthorityDAO.deleteAllByDid(did);
	}

	public DocumentAuthorityDAO getDocumentAuthorityDAO() {
		return documentAuthorityDAO;
	}

	public void setDocumentAuthorityDAO(
			DocumentAuthorityDAO documentAuthorityDAO) {
		this.documentAuthorityDAO = documentAuthorityDAO;
	}

}
