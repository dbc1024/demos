package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketemployee;
import com.ectrip.ticket.provider.dao.IEsbticketEmployeeDAO;
import com.ectrip.ticket.provider.service.IEsbticketEmployeeService;

@Service
public class EsbticketEmployeeService implements IEsbticketEmployeeService{

	public IEsbticketEmployeeDAO ticketemployeeDao;

	public IEsbticketEmployeeDAO getTicketemployeeDao() {
		return ticketemployeeDao;
	}

	public void setTicketemployeeDao(IEsbticketEmployeeDAO ticketemployeeDao) {
		this.ticketemployeeDao = ticketemployeeDao;
	}
	
	/**
	 * *
	 * Describe:获取所有的员工
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#getEmployeeAllList()
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List getEmployeeAllList(){
		return ticketemployeeDao.getEmployeeAllList();
	}
	
	/**
	 * *
	 * Describe:显示出员工售票点权限信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#queryListempTicket(java.lang.Long, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public PaginationSupport queryListempTicket(Long iemployeeid,int pageSize,int startIndex, String url){
		return ticketemployeeDao.queryListempTicket(iemployeeid, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:新增员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#insertEsbticketEmp(com.ectrip.model.provider.Esbticketemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbtickemp
	 * @param stationes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public void insertEsbticketEmp(Esbticketemployee esbtickemp,String [] stationes,Syslog syslog){
		ticketemployeeDao.insertEsbticketEmp(esbtickemp, stationes,syslog);
	}
	
	/**
	 * *
	 * Describe:修改员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#updateEsbticketEmp(com.ectrip.model.provider.Esbticketemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbtickemp
	 * @param stationes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public void updateEsbticketEmp(Esbticketemployee esbtickemp,String [] stationes,Syslog syslog){
		ticketemployeeDao.updateEsbticketEmp(esbtickemp, stationes, syslog);
	}
	
	/**
	 * *
	 * Describe:删除员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#deleteEsbticketEmp(com.ectrip.model.provider.Esbticketemployee, com.ectrip.model.syspar.Syslog)
	 * @param esbtickemp
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public void deleteEsbticketEmp(Esbticketemployee esbtickemp,Syslog syslog){
		ticketemployeeDao.deleteEsbticketEmp(esbtickemp, syslog);
	}
	
	/**
	 * *
	 * Describe:查看员工售票点关联
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#getviewEsbticketEmp(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public Esbticketemployee getviewEsbticketEmp(Long seq) throws Exception{
		return ticketemployeeDao.getviewEsbticketEmp(seq);
	}
	
	/**
	 * *
	 * Describe:获取所有售票点信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#findListemptick(java.lang.String)
	 * @param scenicids
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List findListemptick(String scenicids){
		return ticketemployeeDao.findListemptick(scenicids);
	}
	
	/**
	 * *
	 * Describe:根据员工查找售票点信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#checkListemptick(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List checkListemptick(Long iemployeeid){
		return ticketemployeeDao.checkListemptick(iemployeeid);
	}

	/**
	 * *
	 * Describe:根据类型来获取售票点
	 * @see com.ectrip.system.provider.service.iservice.IEsbticketEmployeeService#showAllesbticket(java.lang.String, java.lang.String)
	 * @param type  1-根据登录人所管理的服务商来获取售票点   2-根据员工售票点权限来获取
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:2015-2-6
	 */
	public List showAllesbticket(String type,Long iemployeeid,String scenics){
		return  ticketemployeeDao.showAllesbticket(type,iemployeeid, scenics);
	}
	
}

