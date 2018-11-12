package com.ectrip.ec.articlemanager.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.sys.model.syspar.Syslog;

public interface IDomainDAO extends IGenericDao{
	
	/**
	 * ��ʾ
	 * @param cmid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 */
	public PaginationSupport showListDomain(int pageSize, int startInt, String url,String type);
	
	/**
	 * ��ʾ������ҵ
	 * @return list
	 */
	public List showAllColumns();
	
	/**
	 * ��ҳ����
	 * @param id
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param str
	 * @return
	 */
	public PaginationSupport findListDomain(Long id,int pageSize, int startInt, String url,String str);
	
	/**
	 * �������
	 * @param domain
	 * @param syslog
	 */
	public void insertDomain(Domain domain,Syslog syslog,String user);
	
	/**
	 * ɾ������
	 * @param domain
	 * @param syslog
	 */
	public void deleteDomain(Domain domain,Syslog syslog,String user);
	
	/**
	 * �޸�����
	 * @param domain
	 * @param syslog
	 */
	public void updateDomain(Domain domain,Syslog syslog,String user);
	
	/**
	 * �鿴����
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List viewDomain(Long id)throws Exception;
	
	
}
