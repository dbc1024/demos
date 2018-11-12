package com.ectrip.ticket.afcset.service.impl;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbgardEmployeeDAO;
import com.ectrip.ticket.afcset.service.IEsbgardEmployeeService;
import com.ectrip.ticket.model.afcset.Esbgardemployee;

public class EsbgardEmployeeService implements IEsbgardEmployeeService{

	IEsbgardEmployeeDAO gardemployeeDAO;

	public IEsbgardEmployeeDAO getGardemployeeDAO() {
		return gardemployeeDAO;
	}

	public void setGardemployeeDAO(IEsbgardEmployeeDAO gardemployeeDAO) {
		this.gardemployeeDAO = gardemployeeDAO;
	}

	/**
	 * *
	 * Describe:获取所有的员工
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#getEmployeeAllList()
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List getEmployeeAllList(){
		return gardemployeeDAO.getEmployeeAllList();
	}

	/**
	 * *
	 * Describe:显示出员工园门权限信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#queryListempGarden(java.lang.Long, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public PaginationSupport queryListempGarden(Long iemployeeid,int pageSize,int startIndex, String url){
		return gardemployeeDAO.queryListempGarden(iemployeeid, pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:新增员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#insertEsbgardEmp(com.ectrip.model.afcset.Esbgardemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbgardemp
	 * @param gardenes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public void insertEsbgardEmp(Esbgardemployee esbgardemp,String [] gardenes,Syslog syslog){
		gardemployeeDAO.insertEsbgardEmp(esbgardemp, gardenes, syslog);
	}

	/**
	 * *
	 * Describe:修改员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#updateEsbgardEmp(com.ectrip.model.afcset.Esbgardemployee, java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param esbgardemp
	 * @param gardenes
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public void updateEsbgardEmp(Esbgardemployee esbgardemp,String [] gardenes,Syslog syslog){
		gardemployeeDAO.updateEsbgardEmp(esbgardemp, gardenes, syslog);
	}

	/**
	 * *
	 * Describe:删除员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#deleteEsbgardEmp(com.ectrip.model.afcset.Esbgardemployee, com.ectrip.model.syspar.Syslog)
	 * @param esbgardemp
	 * @param syslog
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public void deleteEsbgardEmp(Esbgardemployee esbgardemp,Syslog syslog){
		gardemployeeDAO.deleteEsbgardEmp(esbgardemp, syslog);
	}

	/**
	 * *
	 * Describe:查看员工园门关联
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#getviewEsbgardEmp(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public Esbgardemployee getviewEsbgardEmp(Long seq) throws Exception{
		return gardemployeeDAO.getviewEsbgardEmp(seq);
	}

	/**
	 * *
	 * Describe:获取所有园门信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#findListGardengates(java.lang.String)
	 * @param scenicids
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List findListGardengates(String scenicids){
		return gardemployeeDAO.findListGardengates(scenicids);
	}

	/**
	 * *
	 * Describe:根据员工查找园门信息
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#checkListGardengates(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List checkListGardengates(Long iemployeeid){
		return gardemployeeDAO.checkListGardengates(iemployeeid);
	}

	/**
	 * *
	 * Describe:根据类型来获取园门
	 * @see com.ectrip.system.afcset.service.iservice.IEsbgardEmployeeService#showAllesbgardenGate(java.lang.String, java.lang.Long, java.lang.String)
	 * @param type   1-根据登录人所管理的服务商来获取园门   2-根据员工园门权限来获取
	 * @param iemployeeid
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:2015-3-12
	 */
	public List showAllesbgardenGate(String type,Long iemployeeid,String scenics){
		return gardemployeeDAO.showAllesbgardenGate(type, iemployeeid, scenics);
	}

}

