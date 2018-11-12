package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.dao.IEsbticketWinDAO;
import com.ectrip.ticket.provider.service.IEsbticketWinService;

/**
 * @author lizd
 *
 */
/**
 * @author lizd
 *
 */
/**
 * @author lizd
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class EsbticketWinService extends GenericService implements IEsbticketWinService{
	
	private IEsbticketWinDAO esbwinDao;
	
	
	/**
	 * *
	 * Describe:显示所有的售票窗口信息  根据窗口名称查询
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketWinService#getlistTicketWin(java.lang.String, int, int, java.lang.String)
	 * @param szticketwinname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-4
	 */
	public PaginationSupport getlistTicketWin(String szticketwinname,String scenics,
			int pageSize, int startIndex, String url) {
		return esbwinDao.getlistTicketWin(szticketwinname,scenics, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:添加售票窗口
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#insertTicketWin(com.ectrip.model.provider.Esbticketwintab, com.ectrip.model.syspar.Syslog)
	 * @param ticketwin
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-4
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void insertTicketWin(Esbticketwintab ticketwin, Syslog syslog) {
		esbwinDao.insertTicketWin(ticketwin, syslog);
	}
	/**
	 * *
	 * Describe:修改售票窗口
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#updateTicketWin(com.ectrip.model.provider.Esbticketwintab, com.ectrip.model.syspar.Syslog)
	 * @param ticketwin
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-4
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateTicketWin(Esbticketwintab ticketwin, Syslog syslog) {
		esbwinDao.updateTicketWin(ticketwin, syslog);
	}
	/**
	 * *
	 * Describe:删除售票窗口
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#deleteTicketWin(com.ectrip.model.provider.Esbticketwintab, com.ectrip.model.syspar.Syslog)
	 * @param ticketwin
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-4
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deleteTicketWin(Esbticketwintab ticketwin, Syslog syslog) {
		esbwinDao.deleteTicketWin(ticketwin, syslog);
	}
	/**
	 * *
	 * Describe:根据ID查看售票窗口信息
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#getviewEsbticketWin(java.lang.Long)
	 * @param iticketwinid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-4
	 */
	public Esbticketwintab getviewEsbticketWin(Long iticketwinid) throws Exception{
		return esbwinDao.getviewEsbticketWin(iticketwinid);
	}
	/**
	 * *
	 * Describe:根据服务商id查看所有售票窗口信息
	 * @see com.ectrip.system.provider.dao.idao.IEsbticketWinDAO#reviters()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-4
	 */
	public List<Esbticketwintab> findListesbticket(String scenics) {
		return esbwinDao.findListesbticket(scenics);
	}
	/**
	 * *
	 * Describe:根据售票ID查询
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketWinService#getiiticketID(java.lang.Long)
	 * @param iticketwinid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public Esbticketwintab getszIpassword(String szipaddress) {
		return esbwinDao.getszIpassword(szipaddress);
	}
	
	/**
	 * 获取销售权限列表 根据窗口Id查询权限
	 */
	public PaginationSupport getWinPermissionByIticketWinId(Long iticketWinId,int page,int pageSize) {
		return esbwinDao.getWinPermissionByIticketWinId(iticketWinId,page,pageSize);
		
	}
	

	/**
	 * 添加窗口售票权限
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void addWinPermission(Long Iticketwinid,String Icrowdkindpriceids,Syslog syslog) {
		esbwinDao.addWinPermission(Iticketwinid,Icrowdkindpriceids,syslog);
	}
	/**
	 * 修改窗口售票权限
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateTicketWinLimits(Long Iticketwinid,String Icrowdkindpriceids,Syslog syslog) {
		esbwinDao.updateTicketWinLimits(Iticketwinid,Icrowdkindpriceids,syslog);
	}

	/**
	 * 删除窗口售票权限
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deleteTicketWinLimits(Long iticketwinlimitsId, Syslog syslog) {
		esbwinDao.deleteTicketWinLimits(iticketwinlimitsId,syslog);
		
	}
	
	/**
	 * 查看有效的窗口
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeId后台登录人id
	 * @return
	 * return:List
	 * Date:2011-10-6
	 */
	public List getAllTicketWinList(Long employeeId){
		return esbwinDao.getAllTicketWinList(employeeId);
	}
	

	/**
	 * *
	 * Describe:判断此售票窗口下是否有窗口设置
	 * @see com.ectrip.system.permitenter.service.iservice.IOpwwicketSetService#getlistEsbticketequied(java.lang.Long)
	 * @param iticketwinid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-14
	 */
	public boolean getlistEsbticketequied(Long iticketwinid) {
		return esbwinDao.getlistEsbticketequied(iticketwinid);
	}

	public IEsbticketWinDAO getEsbwinDao() {
		return esbwinDao;
	}

	@Autowired
	public void setEsbwinDao(IEsbticketWinDAO esbwinDao) {
		this.esbwinDao = esbwinDao;
		setGenericDao(esbwinDao);
	}

	@Override
	public void getTicketWinLimitsList(Long Iticketwinid) {
		// TODO Auto-generated method stub
		
	}

}

