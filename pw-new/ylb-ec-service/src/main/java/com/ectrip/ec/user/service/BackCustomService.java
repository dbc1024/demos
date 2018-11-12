package com.ectrip.ec.user.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Backcustom;
import com.ectrip.ec.user.dao.idao.IBackCustomDAO;
import com.ectrip.ec.user.service.iservice.IBackCustomService;
import com.ectrip.sys.model.syspar.Syslog;

public class BackCustomService implements IBackCustomService {

	private IBackCustomDAO backCustomDAO;
	/**
	 * Describe:��ȡ�������б�
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#showlistBackcustom(int, int, java.lang.String)
	 * @param pageSize
	 * @param parseInt
	 * @param string
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public PaginationSupport showlistBackcustom(int pageSize, int startInt,String url) {
		return this.backCustomDAO.showlistBackcustom(pageSize, startInt, url);
	}
	
	/**
	 * Describe:��ѯ�������б�
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#searchlistBackcustom(int, int, java.lang.String, java.lang.String)
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public PaginationSupport searchlistBackcustom(int pageSize, int startInt,
			String url, String usid) {
		return this.backCustomDAO.searchlistBackcustom(pageSize, startInt, url, usid);
	}
	
	/**
	 * Describe:�޸ĺ�����
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#viewBackcustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public Backcustom viewBackcustom(String usid) {
		return this.backCustomDAO.viewBackcustom(usid);
	}
	
	/**
	 * Describe:ɾ��������
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#deleteBackcustom(com.ectrip.model.user.Backcustom, com.ectrip.model.syspar.Syslog)
	 * @param backcustom
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public void deleteBackcustom(Backcustom backcustom, Syslog syslog) {
		this.backCustomDAO.deleteBackcustom(backcustom, syslog);
	}

	/**
	 * Describe:���������
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#insertBackcustom(com.ectrip.model.user.Backcustom, com.ectrip.model.syspar.Syslog)
	 * @param backcustom
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public void insertBackcustom(Backcustom backcustom, Syslog syslog) {
		this.backCustomDAO.insertBackcustom(backcustom, syslog);
	}

	/**
	 * Describe:���º�����
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#updateBackcustom(com.ectrip.model.user.Backcustom, com.ectrip.model.syspar.Syslog)
	 * @param backcustom
	 * @param syslog
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public void updateBackcustom(Backcustom backcustom, Syslog syslog) {
		this.backCustomDAO.updateBackcustom(backcustom, syslog);
	}

	/**
	 * Describe:�����û��Ƿ��������ע���û���
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#checkBackcustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public boolean checkBackcustom(String usid) {
		return this.backCustomDAO.checkBackcustom(usid);
	}
	
	/**
	 * Describe:�����û��Ƿ�����ں�������
	 * @see com.ectrip.system.user.service.iservice.IBackCustomService#checkCustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author aozhuozu
	 * Date:2012-10-16
	 */
	public boolean checkCustom(String usid) {
		return this.backCustomDAO.checkCustom(usid);
	}
	
	public IBackCustomDAO getBackCustomDAO() {
		return backCustomDAO;
	}
	public void setBackCustomDAO(IBackCustomDAO backCustomDAO) {
		this.backCustomDAO = backCustomDAO;
	}

}

