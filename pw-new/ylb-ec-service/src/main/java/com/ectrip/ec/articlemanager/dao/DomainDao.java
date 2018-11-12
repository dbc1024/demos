package com.ectrip.ec.articlemanager.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.articlemanager.dao.idao.IDomainDAO;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.sys.model.syspar.Syslog;

public class DomainDao extends GenericDao implements IDomainDAO{
	
	/**
	 * ��ʾ��������
	 */
	public PaginationSupport showListDomain(int pageSize, int startInt, String url,String type){
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(do.seq as seq,do.domainUrl as domainUrl,do.logoMark as logoMark,do.logoPic as logoPic,do.groupId as groupId,ga.szcompanyname as szcompanyname,do.type as type) from Domain do,Galcompanyinfotab ga where ga.icompanyinfoid = do.groupId and type = '"+type+"' order by do.seq asc");
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	
	/**
	 * ��ʾ��ҵ
	 */
	public List showAllColumns() {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select new map(ga.icompanyinfoid as icompanyinfoid,ga.szcompanyname as szcompanyname) from Galcompanyinfotab ga");
		List list = this.find(sql.toString());
		return list; 
	}
	 
	/**
	 * ��ҳ����
	 */
	public PaginationSupport findListDomain(Long id, int pageSize, int startInt, String url, String str) {
		// TODO Auto-generated method stub
		StringBuffer hsql = new StringBuffer();
		System.out.println(str);
		hsql.append(" select * from Domain");
		
		return this.findPage(hsql.toString(), pageSize, startInt, url);
	}
	
	/**
	 * ɾ������
	 */
	public void deleteDomain(Domain domain, Syslog syslog,String user) {
		Domain dom = (Domain) this.get(Domain.class, domain.getSeq());
		
		// TODO Auto-generated method stub
		this.delete(dom);
	}
	
	/**
	 * �������
	 */
	public void insertDomain(Domain domain, Syslog syslog,String user) {
		// TODO Auto-generated method stub
		Domain dom = new Domain();
		Long maxid = this.getMaxPk("seq", "Domain");
		dom.setSeq(maxid+1);
		dom.setGroupId(domain.getGroupId());
		dom.setDomainUrl(domain.getDomainUrl());
		dom.setType(domain.getType());
		dom.setLogoMark(domain.getLogoMark());
		dom.setLogoPic(domain.getLogoPic());
		this.save(dom);
		
	}
	
	/**
	 * �޸�����
	 */
	public void updateDomain(Domain domain, Syslog syslog,String user) {
		// TODO Auto-generated method stub
		
		Domain dom = new Domain();
		dom.setDomainUrl(domain.getDomainUrl());
		dom.setGroupId(domain.getGroupId());
		dom.setLogoMark(domain.getLogoMark());
		dom.setLogoPic(domain.getLogoPic());
		dom.setType(domain.getType());
		dom.setSeq(domain.getSeq());
		this.update(dom);
		
	}
	
	/**
	 * �鿴����
	 */
	public List viewDomain(Long id) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(do.seq as seq,do.domainUrl as domainUrl,do.logoMark as logomark,do.logoPic as logopic,ga.szcompanyname as szcompanyname,do.groupId as groupId,do.type as type) from Domain do,Galcompanyinfotab ga where ga.icompanyinfoid = do.groupId and do.seq="+id);
		String printsql = hsql.toString();
		List list = this.find(printsql);
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	
	
	
}
