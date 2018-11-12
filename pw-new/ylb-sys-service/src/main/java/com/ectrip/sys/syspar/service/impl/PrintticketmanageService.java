package com.ectrip.sys.syspar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Printticketmanage;
import com.ectrip.sys.model.syspar.Soderollprintmanage;
import com.ectrip.sys.syspar.dao.IPrintticketmanageDAO;
import com.ectrip.sys.syspar.service.IPrinttickemanageService;
import com.ectrip.ticket.model.permitenter.Priceprintmanager;

@Service
public class PrintticketmanageService implements IPrinttickemanageService {
	@Autowired
	IPrintticketmanageDAO printticketmanageDAO;

	

	public PaginationSupport managelist(String scenics, int pageSize,
			int startIndex, String url) {
		return printticketmanageDAO.managelist(scenics, pageSize, startIndex, url);
	}

	public List sceniclist(String scenics, String scenictype) {
		return printticketmanageDAO.sceniclist(scenics, scenictype);
	}

	public List businesslist() {
		return printticketmanageDAO.businesslist();
	}

	public PaginationSupport managelist(Long iscenicid, Long ibusinessid,
			int pageSize, int startIndex, String url) {
		return printticketmanageDAO.managelist(iscenicid, ibusinessid, pageSize,
				startIndex, url);
	}

	public List printnolist() {
		return printticketmanageDAO.printnolist();
	}
	public List manageplist(Long iscenicid,Long ibusinessid){
		return printticketmanageDAO.manageplist(iscenicid,ibusinessid);
	}
	
	public void saveprintno(List printlist) {
		Printticketmanage printticketmanage = (Printticketmanage) printlist.get(0);
		List plist = printticketmanageDAO
				.find(" from Printticketmanage p where p.id.iscenicid="
						+ printticketmanage.getId().getIscenicid()
						+ " and p.id.ibusinessid="
						+ printticketmanage.getId().getIbusinessid());
		if (plist != null && plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				Printticketmanage p = (Printticketmanage) plist.get(i);
				printticketmanageDAO.delete(p);
			}
		}
		for (int i = 0; i < printlist.size(); i++) {
			Printticketmanage p = (Printticketmanage) printlist.get(i);
			printticketmanageDAO.save(p);
		}
	}
	/**
	 * *
	 * Describe:
	 * @see com.ectrip.system.syspar.service.iservice.IPrinttickemanageService#mppricelist(java.lang.Long)
	 * @param icrowdkindpriceid
	 * @return
	 * @author huying
	 * Date:2015-6-24
	 */
	public List mppricelist(Long icrowdkindpriceid){
		return printticketmanageDAO.mppricelist(icrowdkindpriceid);
	}
	
	public void saveprint(List printlist) {
		Priceprintmanager priceprintmanager = (Priceprintmanager) printlist.get(0);
		List plist = printticketmanageDAO
				.find(" from Priceprintmanager p where p.id.icrowdkindpriceid="+priceprintmanager.getId().getIcrowdkindpriceid());
						
		if (plist != null && plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				Priceprintmanager p = (Priceprintmanager) plist.get(i);
				printticketmanageDAO.delete(p);
			}
		}
		for (int i = 0; i < printlist.size(); i++) {
			Priceprintmanager p = (Priceprintmanager) printlist.get(i);
			printticketmanageDAO.save(p);
		}
	}

	public PaginationSupport sodemanagelist(String scenics, int pageSize,
			int startIndex, String url) {
		return printticketmanageDAO.sodemanagelist(scenics, pageSize, startIndex,
				url);
	}

	public PaginationSupport sodemanagelist(Long iscenicid, Long ibusinessid,
			int pageSize, int startIndex, String url) {
		return printticketmanageDAO.sodemanagelist(iscenicid, ibusinessid,
				pageSize, startIndex, url);
	}

	public void savesodeprintno(List printlist) {
		Soderollprintmanage soderollprintmanage = (Soderollprintmanage) printlist
				.get(0);
		List plist = printticketmanageDAO
				.find(" from Soderollprintmanage p where p.id.iscenicid="
						+ soderollprintmanage.getId().getIscenicid()
						+ " and p.id.ibusinessid="
						+ soderollprintmanage.getId().getIbusinessid());
		if (plist != null && plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				Soderollprintmanage p = (Soderollprintmanage) plist.get(i);
				printticketmanageDAO.delete(p);
			}
		}
		for (int i = 0; i < printlist.size(); i++) {
			Soderollprintmanage p = (Soderollprintmanage) printlist.get(i);
			printticketmanageDAO.save(p);
		}
	}


    /**
     * *
     * Describe:
     * @see com.ectrip.system.syspar.service.iservice.ISysparService#queryPrintList(java.lang.Long)
     * @param icrowdkindpriceid
     * @return
     * @author huying
     * Date:2015-6-23
     */
	public PaginationSupport queryPrintList(Long icrowdkindpriceid,int pageSize, int startIndex, String url) {
		// TODO Auto-generated method stub
		return printticketmanageDAO.queryPrintList(icrowdkindpriceid, pageSize, startIndex, url);
	}

	public List managesplist(Long iscenicid, Long ibusinessid) {
		// TODO Auto-generated method stub
		return printticketmanageDAO.managesplist(iscenicid, ibusinessid);
	}

	@Override
	public List<?> queryPrintListByScenicIdAndIbusinessId(Long scenicId, Long ibusinessId) {
		
		return printticketmanageDAO.manageplist(scenicId, ibusinessId);
	}
}
