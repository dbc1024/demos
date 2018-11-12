package com.ectrip.ec.report.system.datereport.dao.idao;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;

public interface IEmployeeSaleDAO extends IGenericDao {
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
	public PaginationSupport queryEmpSaleDate(String emps,Long employeeId,String type,String rzti,int page,int pageSize,String url);
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
	public PaginationSupport queryEmpSaleHository(String emps,Long employeeId,String type,String rzti,String ldti,int page,int pageSize,String url);
	
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
	public PaginationSupport getLxsSaleTicket(Esfemployeetab esfemployee,String rzti,String ldti,String usid,int page,int pageSize,String url);
	
}

