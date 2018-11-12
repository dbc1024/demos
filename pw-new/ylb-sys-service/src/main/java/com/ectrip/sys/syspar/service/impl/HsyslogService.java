package com.ectrip.sys.syspar.service.impl;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Hcustomlog;
import com.ectrip.sys.model.syspar.Horderlog;
import com.ectrip.sys.model.syspar.Hsyslog;
import com.ectrip.sys.syspar.dao.impl.HsyslogDao;
import com.ectrip.sys.syspar.service.IHsyslogService;

public class HsyslogService extends GenericService implements IHsyslogService {
	HsyslogDao hsyslogDao;	
	
	public HsyslogDao getHsyslogDao() {
		return hsyslogDao;
	}

	public void setHsyslogDao(HsyslogDao hsyslogDao) {
		this.hsyslogDao = hsyslogDao;
	}
	/**
	 * *
	 * Describe:显示后台操作日志
	 * @see com.ectrip.system.syspar.service.iservice.IHsyslogService#showlisthsyslog(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param stlg
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthsyslog(Long iemployeeid, String stlg,String rzti,String ldti,int flag, int pageSize, int startIndex, String url) {
		return this.hsyslogDao.showlisthsyslog(iemployeeid, stlg, rzti,ldti,flag, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:查看后台操作日志
	 * @see com.ectrip.system.syspar.service.iservice.IHsyslogService#getlogids(java.lang.Long)
	 * @param logid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public Hsyslog getlogids(Long logid) throws Exception {
		return this.hsyslogDao.getlogids(logid);
	}
	/**
	 * *
	 * Describe:显示前台操作日志
	 * @see com.ectrip.system.syspar.service.iservice.IHsyslogService#showlisthcustomlog(java.lang.String, java.lang.String, java.lang.String, int, int, int, java.lang.String)
	 * @param usid
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthcustomlog(String usid, String rzti,String ldti, int flag, int pageSize, int startIndex, String url) {
		return this.hsyslogDao.showlisthcustomlog(usid, rzti, ldti, flag, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:查看前台操作日志
	 * @see com.ectrip.system.syspar.service.iservice.IHsyslogService#getsysidview(java.lang.Long)
	 * @param sysid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public Hcustomlog getsysidview(Long sysid) throws Exception {
		return this.hsyslogDao.getsysidview(sysid);
	}

	/**
	 * *
	 * Describe:显示订单操作日志
	 * @see com.ectrip.system.syspar.service.iservice.IHsyslogService#showlisthorderlog(java.lang.String, java.lang.String, java.lang.String, int, int, int, java.lang.String)
	 * @param usid
	 * @param rzti
	 * @param ldti
	 * @param flag
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public PaginationSupport showlisthorderlog(String usid, String rzti,String ldti, int flag, int pageSize, int startIndex, String url) {
		return this.hsyslogDao.showlisthorderlog(usid, rzti, ldti, flag, pageSize, startIndex, url);
	}
	/**
	 * *
	 * Describe:查看订单操作日志
	 * @see com.ectrip.system.syspar.service.iservice.IHsyslogService#getlogidhorderview(java.lang.Long)
	 * @param logid
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2012-8-8
	 */
	public Horderlog getlogidhorderview(Long logid) throws Exception {
		return this.hsyslogDao.getlogidhorderview(logid);
	}
	
}

