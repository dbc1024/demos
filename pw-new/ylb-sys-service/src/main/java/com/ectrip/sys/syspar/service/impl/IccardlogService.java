package com.ectrip.sys.syspar.service.impl;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Hicopertionlog;
import com.ectrip.sys.model.syspar.Icopertionlog;
import com.ectrip.sys.syspar.dao.IIccardlogDao;
import com.ectrip.sys.syspar.service.IIccardlogService;

public class IccardlogService extends GenericService implements
		IIccardlogService {
	private IIccardlogDao iccardlogDao;

	public IIccardlogDao getIccardlogDao() {
		return iccardlogDao;
	}

	public void setIccardlogDao(IIccardlogDao iccardlogDao) {
		this.iccardlogDao = iccardlogDao;
	}
	/**
	 * *
	 * Describe:IC卡操作当日日志
	 * @see com.ectrip.system.syspar.service.iservice.IIccardlogService#showlisticcardlog(java.lang.String, int, int, java.lang.String)
	 * @param cardno
	 * @param pagesize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public PaginationSupport showlisticcardlog(String cardno, int pagesize, int startIndex, String url) {
		return this.iccardlogDao.showlisticcardlog(cardno,pagesize, startIndex, url);
	}
	/**
	 * *
	 * Describe:根据日志编号查找日志信息
	 * @see com.ectrip.system.syspar.service.iservice.IIccardlogService#findiclogInfo(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public Icopertionlog findiclogInfo(Long seq) throws Exception{
		return this.iccardlogDao.findiclogInfo(seq);
	}
	/**
	 * *
	 * Describe:IC卡操作历史日志
	 * @see com.ectrip.system.syspar.service.iservice.IIccardlogService#showlisthiccardlog(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param cardno
	 * @param rzti
	 * @param ldti
	 * @param pagesize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public PaginationSupport showlisthiccardlog(String cardno, String rzti,String ldti, int pagesize, int startIndex, String url) {
		return this.iccardlogDao.showlisthiccardlog(cardno, rzti, ldti, pagesize, startIndex, url);
	}
	/**
	 * *
	 * Describe:根据日志编号查找历史日志信息
	 * @see com.ectrip.system.syspar.service.iservice.IIccardlogService#findhiclogInfo(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-9-8
	 */
	public Hicopertionlog findhiclogInfo(Long seq) throws Exception {
		return this.iccardlogDao.findhiclogInfo(seq);
	}
	
}

