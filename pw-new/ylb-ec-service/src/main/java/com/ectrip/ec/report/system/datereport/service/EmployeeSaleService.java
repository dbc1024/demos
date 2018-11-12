package com.ectrip.ec.report.system.datereport.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.datereport.dao.idao.IEmployeeSaleDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IEmployeeSaleService;
import com.ectrip.sys.model.employee.Esfemployeetab;

public class EmployeeSaleService implements IEmployeeSaleService {
	private IEmployeeSaleDAO employeesaleDao;
	

	public IEmployeeSaleDAO getEmployeesaleDao() {
		return employeesaleDao;
	}
	public void setEmployeesaleDao(IEmployeeSaleDAO employeesaleDao) {
		this.employeesaleDao = employeesaleDao;
	}
	/**
	 * ��ƱԱÿ����Ʊ��ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeId��ƱԱ���
	 * @param type�������01��Ʊ��02��Ʊ��
	 * @param rzti����
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-6
	 */
	public PaginationSupport queryEmpSaleDate(String emps,Long employeeId,String type,String rzti,int page,int pageSize,String url){
		return employeesaleDao.queryEmpSaleDate(emps,employeeId,type, rzti, page, pageSize, url);
	}
	/**
	 * ��ƱԱ��ʷ��Ʊ��ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param employeeId��ƱԱ���
	 * @param type�������01��Ʊ��02��Ʊ��
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-6
	 */
	public PaginationSupport queryEmpSaleHository(String emps,Long employeeId,String type,String rzti,String ldti,int page,int pageSize,String url){
		return employeesaleDao.queryEmpSaleHository(emps,employeeId, type,rzti, ldti, page, pageSize, url);
	}
	
	/**
	 * �����統�ճ�Ʊ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployee
	 * @param rzti
	 * @param ldti
	 * @param usid
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-20
	 */
	public PaginationSupport getLxsSaleTicket(Esfemployeetab esfemployee,String rzti,String ldti,String usid,int page,int pageSize,String url){
		return employeesaleDao.getLxsSaleTicket(esfemployee, rzti,ldti,usid, page, pageSize, url);
	}
	

}

