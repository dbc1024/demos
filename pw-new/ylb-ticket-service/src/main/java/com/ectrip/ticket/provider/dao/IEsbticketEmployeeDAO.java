package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbticketemployee;

public interface IEsbticketEmployeeDAO extends IGenericDao{
	
	/**
	 * 
	 * Describe:获取所有的员工
	 * @author:lijingrui
	 * @return
	 * return:List
	 * Date:2015-2-6
	 */
	public List getEmployeeAllList();
	
	/**
	 * 
	 * Describe:显示出员工售票点权限信息
	 * @author:lijingrui
	 * @param iemployeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-2-6
	 */
	public PaginationSupport queryListempTicket(Long iemployeeid,int pageSize,int startIndex, String url);
	
	/**
	 * 
	 * Describe:新增员工售票点关联
	 * @author:lijingrui
	 * @param esbtickemp
	 * @param syslog
	 * return:void
	 * Date:2015-2-6
	 */
	public void insertEsbticketEmp(Esbticketemployee esbtickemp,String [] stationes,Syslog syslog);
	
	/**
	 * 
	 * Describe:修改员工售票点关联
	 * @author:lijingrui
	 * @param esbtickemp
	 * @param syslog
	 * return:void
	 * Date:2015-2-6
	 */
	public void updateEsbticketEmp(Esbticketemployee esbtickemp,String [] stationes,Syslog syslog);
	
	/**
	 * 
	 * Describe:删除员工售票点关联
	 * @author:lijingrui
	 * @param esbtickemp
	 * @param syslog
	 * return:void
	 * Date:2015-2-6
	 */
	public void deleteEsbticketEmp(Esbticketemployee esbtickemp,Syslog syslog);
	
	/**
	 * 
	 * Describe:查看员工售票点关联
	 * @author:lijingrui
	 * @param seq
	 * @return
	 * @throws Exception
	 * return:Esbticketemployee
	 * Date:2015-2-6
	 */
	public Esbticketemployee getviewEsbticketEmp(Long seq) throws Exception;
	
	/**
	 * 
	 * Describe:获取所有售票点信息
	 * @author:lijingrui
	 * @param scenicids
	 * @return
	 * return:List
	 * Date:2015-2-6
	 */
	public List findListemptick(String scenicids);
	
	/**
	 * 
	 * Describe:根据员工查找售票点信息
	 * @author:lijingrui
	 * @param iemployeeid
	 * @return
	 * return:List
	 * Date:2015-2-6
	 */
	public List checkListemptick(Long iemployeeid);
	
	/**
	 * 
	 * Describe:根据类型来获取售票点
	 * @author:lijingrui
	 * @param type  1-根据登录人所管理的服务商来获取售票点   2-根据员工售票点权限来获取
	 * @param scenics
	 * @return
	 * return:List
	 * Date:2015-2-6
	 */
	public List showAllesbticket(String type,Long iemployeeid,String scenics);
}

