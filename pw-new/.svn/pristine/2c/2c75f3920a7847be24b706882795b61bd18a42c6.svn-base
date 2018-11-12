package com.ectrip.ticket.provider.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.provider.dao.IEsbticketStationDAO;
import com.ectrip.ticket.provider.service.IEsbticketStationService;

@Service
@Transactional(propagation=Propagation.SUPPORTS)
public class EsbticketStationService extends GenericService implements IEsbticketStationService{
	
	private IEsbticketStationDAO esbticketDao;
	@Autowired
	public void setEsbticketDao(IEsbticketStationDAO esbticketDao) {
		this.esbticketDao = esbticketDao;
		setGenericDao(esbticketDao);
	}
	/**
	 * 
	 * Describe:显示所有的门票站点信息  并根据名称查询
	 * @auth:lijingrui
	 * @param szstationname
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport getlistEsbticket(String szstationname,String scenics,int pageSize,int startIndex, String url){
		return esbticketDao.getlistEsbticket(szstationname,scenics, pageSize, startIndex, url);
	}
	
	/**
	 * 
	 * Describe:添加门票站点
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertEsbticket(Esbticketstationtab esbticket,Syslog syslog){
		esbticketDao.insertEsbticket(esbticket, syslog);
	}
	
	/**
	 * 
	 * Describe:修改门票站点
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateEsbticket(Esbticketstationtab esbticket,Syslog syslog){
		esbticketDao.updateEsbticket(esbticket, syslog);
	}
	
	/**
	 * 
	 * Describe:删除门票站点
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteEsbticket(Esbticketstationtab esbticket,Syslog syslog){
		esbticketDao.deleteEsbticket(esbticket, syslog);
	}
	
	/**
	 * 
	 * Describe:根据ID查看门票站点
	 * @auth:lijingrui
	 * @return
	 * return:Esbticketstationtab
	 * Date:2011-9-29
	 */
	public Esbticketstationtab getviewEsbticket(Long iticketstationid) throws Exception{
		return esbticketDao.getviewEsbticket(iticketstationid);
	}
	
	/**
	 * *
	 * Describe:显示服务商信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketStationService#reviters()
	 * @return
	 * @author lijingrui
	 * Date:2011-9-30
	 */
	public List findListesbticket(Esfemployeetab esfemployeetab,String scenictype,int isjd,String isonlyjq) {
		return esbticketDao.findListesbticket(esfemployeetab, scenictype, isjd, isonlyjq);
	}
	
	/**
	 * *
	 * Describe:显示所有的门票站点信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketStationService#showAllesbticket()
	 * @return
	 * @author lijingrui
	 * Date:2011-9-30
	 */
	public List showAllesbticket() {
		return esbticketDao.showAllesbticket();
	}
	
	public List showAllesbticketwin() {
		return esbticketDao.showAllesbticketwin();
	}
	
	/**
	 * 
	 * Describe:查询非某个景区类型的服务商
	 * @auth:yangguang
	 * @param esfemployeetab
	 * @param scenictype
	 * @param isjd
	 * @param isonlyjq
	 * @return
	 * return:List
	 * Date:2012-6-9
	 */
	public List findListesbticketNotscenic(Esfemployeetab esfemployeetab,String scenictype, int isjd, String isonlyjq){
	    return esbticketDao.findListesbticketNotscenic(esfemployeetab, scenictype, isjd, isonlyjq);
	}
	
	/**
	 * *
	 * Describe:检查售票站点下是否含有售票窗口
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketStationService#getlistEsbticketwin(java.lang.Long)
	 * @param iticketstationid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-14
	 */
	public boolean getlistEsbticketwin(Long iticketstationid) {
		return esbticketDao.getlistEsbticketwin(iticketstationid);
	}
	public List getTicketWinbycode(String szstationcode){
	
		return esbticketDao.getTicketWinbycode(szstationcode);
	}
	public List showAllesbticket(String scenics){
		return esbticketDao.showAllesbticket(scenics);
	}
}
