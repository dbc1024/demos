package com.ectrip.sys.syspar.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;

public interface IPrinttickemanageService {
	/**
	 * 
	 * Describe:
	 * @author: huying
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2015-6-24
	 */
	public List mppricelist(Long icrowdkindpriceid);
	/**
	 * 
	 * Describe:
	 * @author: huying
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-6-24
	 */
	public PaginationSupport managelist(String scenics,int pageSize, int startIndex, String url);
	public PaginationSupport sodemanagelist(String scenics,int pageSize, int startIndex, String url);	
	public List sceniclist(String scenics, String scenictype);
	public List businesslist();
	public List printnolist();
	public void saveprintno(List printlist);
	public PaginationSupport managelist(Long iscenicid,Long ibusinessid,int pageSize, int startIndex, String url);
	public PaginationSupport sodemanagelist(Long iscenicid,Long ibusinessid,int pageSize, int startIndex, String url);
	public void savesodeprintno(List printlist);
	public List manageplist(Long iscenicid,Long ibusinessid) ;
	public List managesplist(Long iscenicid,Long ibusinessid) ;

	/**
	 * 
	 * Describe:
	 * @author: huying
	 * @param icrowdkindpriceid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-6-24
	 */
	public PaginationSupport queryPrintList(Long icrowdkindpriceid,int pageSize, int startIndex, String url);
	/**
	 * 
	 * Describe:
	 * @author: huying
	 * @param printlist
	 * return:void
	 * Date:2015-6-24
	 */
	public void saveprint(List printlist);
	
	public List<?> queryPrintListByScenicIdAndIbusinessId(Long scenicId,Long ibusinessId);
}

