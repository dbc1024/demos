package com.ectrip.ticket.afcset.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.afcset.Esbgardemployee;

public interface IEsbgardEmployeeService {

	/**
	 *
	 * Describe:获取所有的员工
	 * @author:lijingrui
	 * @return
	 * return:List
	 * Date:2015-3-12
	 */
	public List getEmployeeAllList();

	/**
	 *
	 * Describe:显示出员工园门权限信息
	 * @author:lijingrui
	 * @param iemployeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-3-12
	 */
	public PaginationSupport queryListempGarden(Long iemployeeid,int pageSize,int startIndex, String url);

	/**
	 *
	 * Describe:新增员工园门关联
	 * @author:lijingrui
	 * @param esbgardemp
	 * @param gardenes
	 * @param syslog
	 * return:void
	 * Date:2015-3-12
	 */
	public void insertEsbgardEmp(Esbgardemployee esbgardemp,String [] gardenes,Syslog syslog);

	/**
	 *
	 * Describe:修改员工园门关联
	 * @author:lijingrui
	 * @param esbgardemp
	 * @param gardenes
	 * @param syslog
	 * return:void
	 * Date:2015-3-12
	 */
	public void updateEsbgardEmp(Esbgardemployee esbgardemp,String [] gardenes,Syslog syslog);

	/**
	 *
	 * Describe:删除员工园门关联
	 * @author:lijingrui
	 * @param esbgardemp
	 * @param syslog
	 * return:void
	 * Date:2015-3-12
	 */
	public void deleteEsbgardEmp(Esbgardemployee esbgardemp,Syslog syslog);

	/**
	 *
	 * Describe:查看员工园门关联
	 * @author:lijingrui
	 * @param seq
	 * @return
	 * @throws Exception
	 * return:Esbgardemployee
	 * Date:2015-3-12
	 */
	public Esbgardemployee getviewEsbgardEmp(Long seq) throws Exception;

	/**
	 *
	 * Describe:获取所有园门信息
	 * @author:lijingrui
	 * @param scenicids
	 * @return
	 * return:List
	 * Date:2015-3-12
	 */
	public List findListGardengates(String scenicids);

	/**
	 *
	 * Describe:根据员工查找园门信息
	 * @author:lijingrui
	 * @param iemployeeid
	 * @return
	 * return:List
	 * Date:2015-3-12
	 */
	public List checkListGardengates(Long iemployeeid);

	/**
	 *
	 * Describe:根据类型来获取园门
	 * @author:lijingrui
	 * @param type   1-根据登录人所管理的服务商来获取园门   2-根据员工园门权限来获取
	 * @param iemployeeid
	 * @param scenics
	 * @return
	 * return:List
	 * Date:2015-3-12
	 */
	public List showAllesbgardenGate(String type,Long iemployeeid,String scenics);

}

