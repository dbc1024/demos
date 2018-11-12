package com.ectrip.sys.syspar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.syspar.dao.IPrintticketmanageDAO;

@Repository
public class PrintticketmanageDAO extends GenericDao implements IPrintticketmanageDAO {
	public PaginationSupport managelist(String scenics,int pageSize, int startIndex, String url){
		if (scenics != null && !scenics.equals("")) {
			String hsql = " select new map( p.id.ibusinessid as ibusinessid,p.id.iscenicid as iscenicid,p.id.printno as printno,e.szscenicname as szscenicname,b.szbusinessname as szbusinessname,v5.pmva as szprintno,p.ordernum as ordernum ) from Printticketmanage p,Sysparv5 v5,Edmbusinesstab b,Esbscenicareatab e where p.id.iscenicid in ("
				+ scenics
				+ ") and v5.id.pmky='PRSZ' and v5.id.pmcd=p.id.printno and p.id.iscenicid=e.iscenicid and p.id.ibusinessid=b.ibusinessid order by p.id.iscenicid,p.id.ibusinessid,p.ordernum";

				return this.findPage(hsql, pageSize, startIndex, url);
		} else {
			String hsql = " select new map( p.id.ibusinessid as ibusinessid,p.id.iscenicid as iscenicid,p.id.printno as printno,e.szscenicname as szscenicname,b.szbusinessname as szbusinessname,v5.pmva as szprintno,p.ordernum as ordernum ) from Printticketmanage p,Sysparv5 v5,Edmbusinesstab b,Esbscenicareatab e where v5.id.pmky='PRSZ' and v5.id.pmcd=p.id.printno and p.id.iscenicid=e.iscenicid and p.id.ibusinessid=b.ibusinessid order by p.id.iscenicid,p.id.ibusinessid,p.ordernum";
				return this.findPage(hsql, pageSize, startIndex, url);
		}

	}
	
	public PaginationSupport sodemanagelist(String scenics,int pageSize, int startIndex, String url){
		if (scenics != null && !scenics.equals("")) {
			String hsql = " select new map( p.id.ibusinessid as ibusinessid,p.id.iscenicid as iscenicid,p.id.printno as printno,e.szscenicname as szscenicname,b.szbusinessname as szbusinessname,v5.pmva as szprintno,p.ordernum as ordernum ) from Soderollprintmanage p,Sysparv5 v5,Edmbusinesstab b,Esbscenicareatab e where p.id.iscenicid in ("
				+ scenics
				+ ") and v5.id.pmky='PRSZ' and v5.id.pmcd=p.id.printno and p.id.iscenicid=e.iscenicid and p.id.ibusinessid=b.ibusinessid order by p.id.iscenicid,p.id.ibusinessid,p.ordernum";

				return this.findPage(hsql, pageSize, startIndex, url);
		} else {
			String hsql = " select new map( p.id.ibusinessid as ibusinessid,p.id.iscenicid as iscenicid,p.id.printno as printno,e.szscenicname as szscenicname,b.szbusinessname as szbusinessname,v5.pmva as szprintno,p.ordernum as ordernum ) from Soderollprintmanage p,Sysparv5 v5,Edmbusinesstab b,Esbscenicareatab e where v5.id.pmky='PRSZ' and v5.id.pmcd=p.id.printno and p.id.iscenicid=e.iscenicid and p.id.ibusinessid=b.ibusinessid order by p.id.iscenicid,p.id.ibusinessid,p.ordernum";
				return this.findPage(hsql, pageSize, startIndex, url);
		}

	}
	
	public List sceniclist(String scenics, String scenictype) {
		List sceniclist = new ArrayList();
		String hsql = "";
		if (scenics != null && !scenics.equals("")) {
			hsql = "from Esbscenicareatab where iscenicid in (" + scenics
					+ ") and scenictype ='" + scenictype + "'";

		} else {
			hsql = "from Esbscenicareatab where  scenictype ='" + scenictype
					+ "' order by iscenicid";

		}

		sceniclist = this.find(hsql);
		return sceniclist;
	}
	public List businesslist(){
		List businesslist = new ArrayList();
		String hsql = " from Edmbusinesstab where byisuse=1";
		businesslist= this.find(hsql);
		return businesslist;
	}
	public List printnolist(){
		List printnolist = new ArrayList();
		String hsql = " from Sysparv5 v5 where v5.id.pmky='PRSZ' and v5.systp=1 and isvalue=1 order by v5.id.pmcd";
		printnolist= this.find(hsql);
		return printnolist;
	}
	public PaginationSupport managelist(Long iscenicid,Long ibusinessid,int pageSize, int startIndex, String url){
		String hsql = " select new map( p.id.ibusinessid as ibusinessid,p.id.iscenicid as iscenicid,p.id.printno as printno,e.szscenicname as szscenicname,b.szbusinessname as szbusinessname,v5.pmva as szprintno,p.ordernum as ordernum ) from Printticketmanage p,Sysparv5 v5,Edmbusinesstab b,Esbscenicareatab e where p.id.iscenicid="+iscenicid+" and p.id.ibusinessid="+ibusinessid+" and v5.id.pmky='PRSZ' and v5.id.pmcd=p.id.printno and p.id.iscenicid=e.iscenicid and p.id.ibusinessid=b.ibusinessid order by p.id.iscenicid,p.id.ibusinessid,p.ordernum";

			return this.findPage(hsql, pageSize, startIndex, url);
	}
	public PaginationSupport sodemanagelist(Long iscenicid,Long ibusinessid,int pageSize, int startIndex, String url){
		String hsql = " select new map( p.id.ibusinessid as ibusinessid,p.id.iscenicid as iscenicid,p.id.printno as printno,e.szscenicname as szscenicname,b.szbusinessname as szbusinessname,v5.pmva as szprintno,p.ordernum as ordernum ) from Soderollprintmanage p,Sysparv5 v5,Edmbusinesstab b,Esbscenicareatab e where p.id.iscenicid="+iscenicid+" and p.id.ibusinessid="+ibusinessid+" and v5.id.pmky='PRSZ' and v5.id.pmcd=p.id.printno and p.id.iscenicid=e.iscenicid and p.id.ibusinessid=b.ibusinessid order by p.id.iscenicid,p.id.ibusinessid,p.ordernum";

			return this.findPage(hsql, pageSize, startIndex, url);
	}
	
	public List manageplist(Long iscenicid,Long ibusinessid){
		List printnolist = new ArrayList();
		String hsql =" from Printticketmanage p where p.id.iscenicid="+iscenicid+" and p.id.ibusinessid="+ibusinessid+" order by ordernum ";
		printnolist= this.find(hsql);
		return printnolist;
	}
	
	/**
	 * 
	 * Describe:根据价格ID查询
	 * @author: huying
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2015-6-24
	 */
	public List mppricelist(Long icrowdkindpriceid){
		List printnolist = new ArrayList();
		String hsql =" from Priceprintmanager p where p.id.icrowdkindpriceid="+icrowdkindpriceid+" order by ordernum ";
		printnolist= this.find(hsql);
		return printnolist;
	}
	
	public List managesplist(Long iscenicid,Long ibusinessid){
		List printnolist = new ArrayList();
		String hsql =" from Soderollprintmanage p where p.id.iscenicid="+iscenicid+" and p.id.ibusinessid="+ibusinessid+" order by ordernum ";
		printnolist= this.find(hsql);
		return printnolist;
	}

	public PaginationSupport queryPrintList(Long icrowdkindpriceid,int pageSize, int startIndex, String url) {
		PaginationSupport ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select new map(p.id.icrowdkindpriceid as icrowdkindpriceid,p.id.printno as printno,p.ordernum as ordernum,p.noteprinttype as noteprinttype," +
		" p.note as note,p.printtype as printtype,p.dtmakedate as dtmakedate,t.sztickettypename as  sztickettypename,c.icrowdkindpricecode as icrowdkindpricecode," +
		" s.szscenicname as szscenicname,sys.pmva as pmva )" +
		" from Priceprintmanager p ,Edmcrowdkindpricetab c,Edmtickettypetab t,Esbscenicareatab s,Sysparv5 sys" +
		" where  p.id.icrowdkindpriceid=c.icrowdkindpriceid and c.itickettypeid=t.itickettypeid and t.iscenicid=s.iscenicid and sys.id.pmky='PRSZ'" +
		" and sys.id.pmcd=p.id.printno" +
		" and p.id.icrowdkindpriceid="+icrowdkindpriceid+" order by p.id.icrowdkindpriceid,p.id.printno,p.ordernum ");
		ps=this.findPage(sql.toString(), pageSize, startIndex, url);
		return ps;
	}
	
}

