package com.ectrip.ec.articlemanager.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.sys.model.syspar.Syslog;

public interface IDomainService extends IGenericService{
	
	/*
	 * ��ʾ��������
	 */
	public PaginationSupport showListDomain(int pageSize, int startInt, String url,String type);
	
	/*
	 * ��ʾ������Ŀ
	 */
	public List showAllColumns();
	
	/*
	 * ��ҳ����
	 */
	public PaginationSupport findListDomain(Long id,int pageSize, int startInt, String url,String str);
	
	/*
	 * �������
	 */
	public void insertDomain(Domain domain,Syslog syslog,String user);
	
	/*
	 * ɾ������
	 */
	public void deleteDomain(Domain domain,Syslog syslog,String user);
	
	/*
	 * �޸�����
	 */
	public void updateDomain(Domain domain,Syslog syslog,String user);
	
	/*
	 * ��ѯ����
	 */
	public List viewDomain(Long id)throws Exception;

}
