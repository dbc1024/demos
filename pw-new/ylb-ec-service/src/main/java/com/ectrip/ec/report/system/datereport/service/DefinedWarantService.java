package com.ectrip.ec.report.system.datereport.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.report.sales.Defindsbalance;
import com.ectrip.ec.model.report.sales.Definedwarrants;
import com.ectrip.ec.report.system.datereport.dao.idao.IDefinedWarantDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IDefinedWarantService;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Ospbankpayeesettab;

public class DefinedWarantService implements IDefinedWarantService{
	
	private IDefinedWarantDAO dfindwarantDao;

	public IDefinedWarantDAO getDfindwarantDao() {
		return dfindwarantDao;
	}

	public void setDfindwarantDao(IDefinedWarantDAO dfindwarantDao) {
		this.dfindwarantDao = dfindwarantDao;
	}
	
	/**
	 * *
	 * Describe:��ʾ��ƱԱ�ɿ���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IDefinedWarantDAO#showListdefinedant(java.lang.String, int, int, java.lang.String)
	 * @param stdate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-11-30
	 */
	public PaginationSupport showListdefinedant(Long empid,String stdate,Long icompanyinfoid,int pageSize,int startIndex, String url){
		return dfindwarantDao.showListdefinedant(empid,stdate,icompanyinfoid, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:��ȡĳ��������ƱԱ�����۽��
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#lookCheckviewmoney(java.lang.String, java.lang.String, java.lang.String)
	 * @param zffs
	 * @param empid
	 * @param stdate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-2
	 */
	public Double lookCheckviewmoney(String zffs,Long empid,String stdate){
		return dfindwarantDao.lookCheckviewmoney(zffs, empid, stdate);
	}
	
	/**
	 * *
	 * Describe:��ѯ���ɿ������б���Ϣ
	 * @see com.ectrip.report.system.datereport.dao.idao.IDefinedWarantDAO#getListospbankpay()
	 * @return
	 * @author lijingrui
	 * Date:2013-3-2
	 */
	public List getListospbankpay(){
		return dfindwarantDao.getListospbankpay();
	}

	/**
	 * *
	 * Describe:��ѯ���ɿ�������Ϣ�µ��˺�
	 * @see com.ectrip.report.system.datereport.dao.idao.IDefinedWarantDAO#showUpbankcount(java.lang.String)
	 * @param bankname
	 * @return
	 * @author lijingrui
	 * Date:2013-3-2
	 */
	public Ospbankpayeesettab showUpbankcount(String bankname){
		return dfindwarantDao.showUpbankcount(bankname);
	}

	/**
	 * *
	 * Describe:��ѯ����¼��������ҵ�µ�����Ա��
	 * @see com.ectrip.system.permitenter.dao.idao.IDefinedWarantDAO#getListemployee(java.lang.Long)
	 * @param companyid
	 * @return
	 * @author lijingrui
	 * Date:2012-11-30
	 */
	public List getListemployee(Long iemployeeid){
		return dfindwarantDao.getListemployee(iemployeeid);
	}
	
	/**
	 * *
	 * Describe:�����ƱԱ�ɿ���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IDefinedWarantDAO#addDefinedwarant(com.ectrip.model.report.sales.Definedwarrants, com.ectrip.model.syspar.Syslog)
	 * @param definedwants
	 * @param syslog
	 * @author lijingrui
	 * Date:2012-11-30
	 */
	public void addDefinedwarant(Definedwarrants definedwants,Syslog syslog){
		dfindwarantDao.addDefinedwarant(definedwants, syslog);
	}
	
	/**
	 * *
	 * Describe:�鿴��ƱԱ�ɿ���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IDefinedWarantDAO#viewDefinedwarant(java.lang.Long)
	 * @param seq
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2012-11-30
	 */
	public Definedwarrants viewDefinedwarant(Long seq) throws Exception{
		return dfindwarantDao.viewDefinedwarant(seq);
	}

	/**
	 * 
	 * Describe:��ѯ��ƱԱ��ĳʱ���ǰ�Ƿ���δ����Ľɿ��¼
	 * @author:chenxinhao
	 * @param empid		��ƱԱID�����ǵ�����ƱԱID��Ҳ������ƱԱ���ID����1,2,3,4
	 * @param date		ʱ��
	 * @return			������ƱԱ���ƺ�δ������·�
	 * return:List
	 * Date:2012-12-7
	 */
	public List checkdefindate(String empid,String date){
		return dfindwarantDao.checkdefindate(empid, date);
	}
	
	/**
	 * *
	 * Describe:��ʾĳ�µĽɿ���Ϣ
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#checkListdefinedant(java.lang.Long, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param empid
	 * @param stdate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-10
	 */
	public PaginationSupport checkListdefinedant(Long empid,String stdate,Long icompanyinfoid,int pageSize,int startIndex, String url){
		return dfindwarantDao.checkListdefinedant(empid, stdate, icompanyinfoid, pageSize, startIndex, url);
	}
	
	/**
	 * 
	 * Describe:��ѯ�����Ƿ����
	 * @author:chenxinhao
	 * @param empid
	 * @param date
	 * @return
	 * return:List
	 * Date:2012-12-10
	 */
	public List defindantcheckin(String empid,String date){
		return dfindwarantDao.defindantcheckin(empid, date);
	}
	
	/**
	 * *
	 * Describe:��ѯ�� �ɿ��ܽ��
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getBalancemony(java.lang.String, java.lang.String)
	 * @param empid
	 * @param date
	 * @return
	 * @author lijingrui
	 * Date:2012-12-10
	 */
	public Double getBalancemony(String empid,String date){
		return dfindwarantDao.getBalancemony(empid, date);
	}
	
	/**
	 * *
	 * Describe:��ѯ�� �����ܽ��
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getBalancexsmony(java.lang.String, java.lang.String)
	 * @param empid
	 * @param date
	 * @return
	 * @author lijingrui
	 * Date:2012-12-10
	 */
	public Double getBalancexsmony(String empid,String date){
		return dfindwarantDao.getBalancexsmony(empid, date);
	}
	
	/**
	 * *
	 * Describe:�жϴ���ƱԱ��ĳ���Ƿ�ɹ���
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getViewfindwarant(java.lang.Long, java.lang.String)
	 * @param empid
	 * @param date
	 * @return
	 * @author lijingrui
	 * Date:2012-12-11
	 */
	public boolean getViewfindwarant(Long empid,String date){
		return dfindwarantDao.getViewfindwarant(empid, date);
	}
	
	/**
	 * *
	 * Describe:�ɿ�����¼����
	 * @see com.ectrip.report.system.datereport.dao.idao.IDefinedWarantDAO#getSavedefindbalance(com.ectrip.model.report.sales.Defindsbalance, java.util.List, com.ectrip.model.syspar.Syslog)
	 * @param balance
	 * @param list
	 * @param syslog
	 * @return
	 * @author lijingrui
	 * Date:2012-12-11
	 */
	public void getSavedefindbalance(Defindsbalance balance,Syslog syslog){
	  dfindwarantDao.getSavedefindbalance(balance, syslog);
	}
	
	/**
	 * *
	 * Describe:�鿴�ɿ�����¼
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getListbalance(java.lang.Long, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param empid
	 * @param stdate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-12
	 */
	public PaginationSupport getListbalance(Long empid,String stdate,int pageSize,int startIndex, String url){
		return  dfindwarantDao.getListbalance(empid, stdate, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:�鿴�ɿ������ϸ��Ϣ
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getViewbalanceListab(java.lang.Long, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param ibalanceid
	 * @param stdate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-12
	 */
	public PaginationSupport getViewbalanceListab(Long ibalanceid,String startdate,String enddate,int pageSize,int startIndex, String url){
		return dfindwarantDao.getViewbalanceListab(ibalanceid, startdate,enddate, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getEmployeeAllList(java.lang.Long)
	 * @param employeeId
	 * @return
	 * @author lijingrui
	 * Date:2013-5-3
	 */
	public List getEmployeeAllList(Long employeeId){
		List list = new ArrayList();
		String sql=" from Galcompanyinfotab pany where pany.icompanyinfoid in(select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="+employeeId+")";
		String hsql="";
		List companylist = this.dfindwarantDao.find(sql);
		if(companylist.size()>=1){
			Galcompanyinfotab company = (Galcompanyinfotab)companylist.get(0);
			if("01".equals(company.getCompanytype())){//ƽ̨����
				 hsql=" from Esfemployeetab where byisuse=1 and iemployeeid in(select iemployeeid from Esfemployeepoststab where ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0) ) ))";
			}
			if("02".equals(company.getCompanytype())){//������ҵ
				String msql=" from Esfemployeepoststab where iemployeeid="+employeeId+" and ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0)))";
				List slist=this.dfindwarantDao.find(msql);
				if(slist!=null&&slist.size()>0){
					hsql="from Esfemployeetab where byisuse=1 and iemployeeid="+employeeId;
				}else{
					hsql=" from Esfemployeetab where byisuse=1 and icompanyinfoid in (select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="+employeeId+") and iemployeeid in(select iemployeeid from Esfemployeepoststab where ipostsid in (select ipostsid from Esppoststab where posttype='02' and ipostsid in (select ipostsid from Esppostsdutytab where idutyid in (select idutyid from Espdutytab where issellticket=0) ) ))";
				}
				
			}
		}
		
		list = this.dfindwarantDao.find(hsql);
		
		return  list;
	}
	
	/**
	 * *
	 * Describe:�жϴ���ƱԱ��ĳ���ڶ����Ƿ�ɹ���
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getViewfindwarant(java.lang.Long, java.lang.String, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-22
	 */
	public boolean getViewfindwarant(Long empid,String startdate,String enddate){
		return dfindwarantDao.getViewfindwarant(empid, startdate, enddate);
	}
	
	/**
	 * *
	 * Describe:��ѯ��ƱԱ��ĳʱ���ǰ�Ƿ���δ����Ľɿ��¼
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#checkdefindate(java.lang.String, java.lang.String, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-22
	 */
	public List checkdefindate(String empid,String startdate,String enddate){
		return dfindwarantDao.checkdefindate(empid, startdate, enddate);
	}
	
	/**
	 * *
	 * Describe:��ѯ�� �ɿ��ܽ��
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getBalancemony(java.lang.String, java.lang.String, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-22
	 */
	public Double getBalancemony(String empid,String startdate,String enddate){
		return dfindwarantDao.getBalancemony(empid, startdate, enddate);
	}
	
	/**
	 * *
	 * Describe:��ѯ�� �����ܽ��
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getBalancexsmony(java.lang.String, java.lang.String, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-22
	 */
	public Double getBalancexsmony(String empid,String startdate,String enddate){
		return dfindwarantDao.getBalancexsmony(empid, startdate, enddate);
	}
	
	/**
	 * *
	 * Describe:��ʾĳ���ڶ��ڵĽɿ���Ϣ
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#checkListdefinedant(java.lang.Long, java.lang.String, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @param icompanyinfoid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-5-22
	 */
	public PaginationSupport checkListdefinedant(Long empid,String startdate,String enddate,Long icompanyinfoid,int pageSize,int startIndex, String url){
		return dfindwarantDao.checkListdefinedant(empid, startdate, enddate, icompanyinfoid, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:��ѯĳ���ڶ��Ƿ����
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#defindantcheckin(java.lang.String, java.lang.String, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-23
	 */
	public List checkFindbalance(String empid,String startdate,String enddate){
		return  dfindwarantDao.checkFindbalance(empid, startdate, enddate);
	}

}

