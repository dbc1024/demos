package com.ectrip.ticket.afcset.service.impl;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Esbequiptypetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbequiptypetabDAO;
import com.ectrip.ticket.afcset.service.IEsbequiptypetabService;

public class EsbequiptypetabService extends GenericService implements
		IEsbequiptypetabService {
	IEsbequiptypetabDAO esbequiptypetabDAO;

	public IEsbequiptypetabDAO getEsbequiptypetabDAO() {
		return esbequiptypetabDAO;
	}

	public void setEsbequiptypetabDAO(IEsbequiptypetabDAO esbequiptypetabDAO) {
		this.esbequiptypetabDAO = esbequiptypetabDAO;
	}

	/**
	 * *
	 * Describe:删除设备类型信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService#deleteesbequiptype(com.ectrip.model.provider.Esbequiptypetab, com.ectrip.model.syspar.Syslog)
	 * @param esbequip
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 1, 2011
	 */
	public void deleteesbequiptype(Esbequiptypetab esbequip, Syslog syslog) {
		esbequiptypetabDAO.deleteesbequiptype(esbequip, syslog);

	}

	/**
	 * *
	 * Describe:显示登录人所在企业管理的服务商
	 * @see com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService#getListscenicar(java.lang.String)
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:Nov 1, 2011
	 */
	public List getListscenicar(String scenics) {
		return esbequiptypetabDAO.getListscenicar(scenics);
	}

	/**
	 * *
	 * Describe:根据ID查看设备类型信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService#getviewequiptype(java.lang.Long)
	 * @param iequiptypeid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Nov 1, 2011
	 */
	public Esbequiptypetab getviewequiptype(Long iequiptypeid) throws Exception {
		return esbequiptypetabDAO.getviewequiptype(iequiptypeid);
	}

	/**
	 * *
	 * Describe:添加设备类型信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService#insertesbequiptype(com.ectrip.model.provider.Esbequiptypetab, com.ectrip.model.syspar.Syslog)
	 * @param esbequip
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 1, 2011
	 */
	public void insertesbequiptype(Esbequiptypetab esbequip, Syslog syslog) {
		esbequiptypetabDAO.insertesbequiptype(esbequip, syslog);
	}

	/**
	 * *
	 * Describe:列表显示设备类型信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService#showListprdcontrol(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Nov 1, 2011
	 */
	public PaginationSupport showListprdcontrol(String scenics, int pageSize,
												int startIndex, String url) {
		return esbequiptypetabDAO.showListprdcontrol(scenics, pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:修改设备类型信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbequiptypetabService#updateesbequiptype(com.ectrip.model.provider.Esbequiptypetab, com.ectrip.model.syspar.Syslog)
	 * @param esbequip
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 1, 2011
	 */
	public void updateesbequiptype(Esbequiptypetab esbequip, Syslog syslog) {
		esbequiptypetabDAO.updateesbequiptype(esbequip, syslog);
	}

}

