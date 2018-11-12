package com.ectrip.ec.articlemanager.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.articlemanager.dao.idao.IDomainDAO;
import com.ectrip.ec.articlemanager.service.iservice.IDomainService;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.sys.model.syspar.Syslog;

public class DomainService extends GenericService implements IDomainService{
	public IDomainDAO domaindao;
	
	public IDomainDAO getDomaindao() {
		return domaindao;
	}

	public void setDomaindao(IDomainDAO domaindao) {
		this.domaindao = domaindao;
	}
	
	/**
	 * ��ʾ��������
	 */
	public PaginationSupport showListDomain( int pageSize, int startInt, String url,String type) {
		// TODO Auto-generated method stub
		return this.domaindao.showListDomain( pageSize, startInt, url,type);
	}
	
	/**
	 * ��ʾ������ҵ
	 */
	public List showAllColumns() {
		// TODO Auto-generated method stub
		return this.domaindao.showAllColumns();
	}
	
	/**
	 * ��ҳ����
	 */
	public PaginationSupport findListDomain(Long id, int pageSize, int startInt, String url, String str) {
		// TODO Auto-generated method stub
		return this.domaindao.findListDomain(id, pageSize, startInt, url, str);
	}
	
	/**
	 * ��������
	 */
	public void insertDomain(Domain domain, Syslog syslog,String user) {
		// TODO Auto-generated method stub
		this.domaindao.insertDomain(domain, syslog,user);
	}
	
	/**
	 * ɾ������
	 */
	public void deleteDomain(Domain domain, Syslog syslog,String user) {
		// TODO Auto-generated method stub
		this.domaindao.deleteDomain(domain, syslog,user);
	}
	
	/**
	 * �鿴����
	 */
	public List viewDomain(Long id) throws Exception {
		// TODO Auto-generated method stub
 		return this.domaindao.viewDomain(id);
	}
	
	/**
	 * �޸�����
	 */
	public void updateDomain(Domain domain, Syslog syslog,String user) {
		// TODO Auto-generated method stub
		this.domaindao.updateDomain(domain, syslog,user);
	}

	
}
