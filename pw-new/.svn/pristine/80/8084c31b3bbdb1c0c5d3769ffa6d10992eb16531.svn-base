package com.ectrip.sys.syspar.dao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Hicopertionlog;
import com.ectrip.sys.model.syspar.Icopertionlog;

public interface IIccardlogDao extends IGenericDao {
	/**
	 * 
	 * Describe:IC卡操作当日日志记录
	 * @author:chenxinhao
	 * @param cardno
	 * @param pagesize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-9-8
	 */
	public PaginationSupport showlisticcardlog(String cardno,int pagesize,int startIndex,String url);
	/**
	 * 
	 * Describe:IC卡操作当日日志信息
	 * @author:chenxinhao
	 * @param seq
	 * @return
	 * @throws Exception
	 * return:Icopertionlog
	 * Date:2012-9-8
	 */
	public Icopertionlog findiclogInfo(Long seq) throws Exception;
	/**
	 * 
	 * Describe:IC卡操作历史日志记录
	 * @author:chenxinhao
	 * @param cardno
	 * @param rzti
	 * @param ldti
	 * @param pagesize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-9-8
	 */
	public PaginationSupport showlisthiccardlog(String cardno, String rzti, String ldti, int pagesize, int startIndex, String url);
	/**
	 * 
	 * Describe:IC卡操作历史日志信息
	 * @author:chenxinhao
	 * @param seq
	 * @return
	 * @throws Exception
	 * return:Hicopertionlog
	 * Date:2012-9-8
	 */
	public Hicopertionlog findhiclogInfo(Long seq) throws Exception;
}

