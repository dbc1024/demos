package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketstationtab;

public interface IEsbticketStationDAO extends IGenericDao{
	
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
	public PaginationSupport getlistEsbticket(String szstationname,String scenics,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:添加门票站点
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void insertEsbticket(Esbticketstationtab esbticket,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改门票站点
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void updateEsbticket(Esbticketstationtab esbticket,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除门票站点
	 * @auth:lijingrui
	 * @param esbticket
	 * @param syslog
	 * return:void
	 * Date:2011-9-29
	 */
	public void deleteEsbticket(Esbticketstationtab esbticket,Syslog syslog);
	
	/**
	 * 
	 * Describe:根据ID查看门票站点
	 * @auth:lijingrui
	 * @return
	 * return:Esbticketstationtab
	 * Date:2011-9-29
	 */
	public Esbticketstationtab getviewEsbticket(Long iticketstationid) throws Exception;
	
	/**
	 * 
	 * Describe:显示服务商信息
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List findListesbticket(Esfemployeetab esfemployeetab,String scenictype,int isjd,String isonlyjq);
	
	/**
	 * 
	 * Describe:显示所有的门票站点信息
	 * @auth:lijingrui
	 * @return
	 * return:List<Esbticketstationtab>
	 * Date:2011-9-30
	 */
	public List showAllesbticket();
	public List showAllesbticketwin();
	/**
	 * 
	 * Describe:检查售票站点下是否含有售票窗口
	 * @auth:lijingrui
	 * @param iticketstationid
	 * @return
	 * return:boolean
	 * Date:2011-10-14
	 */
	public boolean getlistEsbticketwin(Long iticketstationid);
	
	
	public List getTicketWinbycode(String szstationcode);
	
	public List findListesbticketNotscenic(Esfemployeetab esfemployeetab,String scenictype, int isjd, String isonlyjq);
	
	public List showAllesbticket(String scenics);
}
