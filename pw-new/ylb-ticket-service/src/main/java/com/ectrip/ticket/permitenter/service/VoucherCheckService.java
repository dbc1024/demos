package com.ectrip.ticket.permitenter.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.permitenter.dao.IVoucherDAO;
import com.ectrip.ticket.permitenter.service.iservice.IVoucherCheckupService;

public class VoucherCheckService implements IVoucherCheckupService{
	
	IVoucherDAO voucherDao;
	
	public IVoucherDAO getVoucherDao() {
		return voucherDao;
	}
	
	public void setVoucherDao(IVoucherDAO voucherDao) {
		this.voucherDao = voucherDao;
	}
	
	/**
	 * *
	 * Describe:����ƾ֤��ѯ
	 * @see com.ectrip.system.permitenter.service.iservice.IVoucherCheckupService#querySaleTicketList(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param iscenicid
	 * @param iemployeeid
	 * @param rzti
	 * @param ldti
	 * @param type
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-4-15
	 */
	public PaginationSupport querySaleTicketList(Long iscenicid,Long iemployeeid,String rzti,String ldti,String type,int page,int pageSize,String url){
		PaginationSupport ps = null;

		StringBuffer hsql = new StringBuffer();
		if (type.equals("1")) {
			hsql
					.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,employee.szemployeename as employeenamemaker,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,substr(sale.dtmakedate,1,10) as dtmakedate) from Stssalesvouchertab sale,Esfemployeetab emp,Esfemployeetab employee, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5 where ");

		} else {
			hsql
					.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,employee.szemployeename as employeenamemaker,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertablist sale,Esfemployeetab emp,Esfemployeetab employee, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5 where ");

		}
		hsql.append("  substr(sale.dtmakedate,1,10)>='" + rzti
				+ "' and substr(sale.dtmakedate,1,10)<='" + ldti + "' ");
		if (iscenicid!=null) {
			hsql.append(" and sale.iscenicid=" + iscenicid);
		}
		if (iemployeeid!=null) {
			hsql.append(" and sale.ihandler=" + iemployeeid);
		}
//		hsql.append(" and sale.forcedrefund='��̨����' ");
		hsql.append(" and sale.bypostrecord=1 ");
		hsql.append(" and emp.iemployeeid = sale.ihandler and employee.iemployeeid=sale.imaker and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype ");
		hsql.append(" order by sale.szsalesvoucherno desc ");
		System.out.println("===>>>.sql:"+hsql);
		
		ps = voucherDao.findPage(hsql.toString(), pageSize, page, url);
		
		return ps;
	
	}


}

