package com.ectrip.sys.syspar.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;

public interface IPrintticketmanageDAO extends IGenericDao {
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
	public List manageplist(Long iscenicid,Long ibusinessid);
	public List managesplist(Long iscenicid,Long ibusinessid);
	public PaginationSupport managelist(Long iscenicid,Long ibusinessid,int pageSize, int startIndex, String url);
		public PaginationSupport sodemanagelist(Long iscenicid,Long ibusinessid,int pageSize, int startIndex, String url);

		/**
		 * *
		 * Describe:
		 * @see com.ectrip.system.syspar.dao.idao.ISysparDao#queryPrintList(java.lang.Long)
		 * @param icrowdkindpriceid
		 * @return
		 * @author huying
		 * Date:2015-6-23
		 */
		public PaginationSupport queryPrintList(Long icrowdkindpriceid,int pageSize, int startIndex, String url);
		
		
}

