package com.ectrip.ticket.statistics.dao;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.statistics.dao.idao.ICreditDAO;

public class CreditDAO extends GenericDao implements ICreditDAO{

	/**
	 * *
	 * Describe:查看用户信誉度
	 * @see com.ectrip.system.statistics.dao.idao.ICreditDAO#showAllcreditList(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Jun 18, 2012
	 */
	public PaginationSupport showAllcreditList(String usid, int pageSize,
											   int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(c.usid as usid,c.creditnumb as creditnumb,ct.lname as lname,ct.corpname as corpname) from Credit c,Custom ct where c.usid=ct.usid ");
		if(usid!=null&&!usid.equals("")){
			sql.append(" and c.usid='"+usid+"'");
		}
		sql.append(" order by ct.bname ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

	/**
	 * *
	 * Describe:查看用户信誉度消费明细
	 * @see com.ectrip.system.statistics.dao.idao.ICreditDAO#showAllcreditDetailList(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param orid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Jun 18, 2012
	 */
	public PaginationSupport showAllcreditDetailList(String usid, String orid,
													 int pageSize, int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(cd.cseq as cseq,cd.usid as usid,cd.zusid as zusid,cd.orid as orid,cd.ctype as ctype,cd.creditnumb as creditnumb) from Creditdetail cd where cd.usid='"+usid+"'");
		if(orid!=null&&!orid.equals("")){
			sql.append(" and cd.orid='"+orid+"'");
		}
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}

}

