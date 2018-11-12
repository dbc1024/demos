package com.ectrip.ec.report.system.ticketsale.dao;


import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ISaleListDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;

public class SaleListDAO extends GenericDao implements ISaleListDAO {

	/**
	 * ��ѯ��Ʊ��ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usids
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport querySaleAllList(String rzti,String ldti,String usids,String types,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(cus.bname as bname,t.usid as usid,cus.corpname as corpname,t.ornm as ornm,t.id.orid as orid ,t.isc as isc,em.szemployeename as szemployeename,t.notec as notec,yl.itickettypeid as itickettypeid ,et.sztickettypename as sztickettypename, kind.szcrowdkindname as szcrowdkindname,yl.pric as pric ,sum(yl.numb) as numb,sum(yl.amnt) as mont )  from TOrder t,TOrderlist yl,Custom cus,Edpcrowdkindtab kind,Edmtickettypetab et,Esfemployeetab em ");
		hsql.append(" where substr(t.notec,1,10)>='"+rzti+"' and substr(t.notec,1,10)<='"+ldti+"' and    t.ddzt='11' ");	
		hsql.append(" and cus.ibusinessid = "+types);
		hsql.append(" and t.usid  in ("+usids+")");
		hsql.append(" and t.id.iscenicid=yl.id.iscenicid and t.id.orid=yl.id.orid and t.usid = cus.usid  and kind.icrowdkindid = yl.icrowdkindid and yl.itickettypeid=et.itickettypeid  and t.isc=em.iemployeeid  group by cus.bname,t.usid,cus.corpname,t.ornm,t.id.orid,t.isc,em.szemployeename,t.notec,yl.itickettypeid,et.sztickettypename, kind.szcrowdkindname,yl.pric order by cus.bname,t.usid,t.id.orid ");	
  
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		
		return ps;
	}
	
	/**
	 * ��ѯ��Ʊ�˶���ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usids
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport queryTuiDingAllList(String rzti,String ldti,String usids,String types,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(cus.bname as bname,m.usid as usid,cus.corpname as corpname,m.isc as isc,m.orid as orid,m.srid as srid ,v5.pmva as strtpfs,v55.pmva as strtdlb,m.orda as orda,m.orti as orti,yl.itickettypeid as itickettypeid ,et.sztickettypename as sztickettypename, kind.szcrowdkindname as szcrowdkindname,yl.pric as pric ,sum(yl.numb) as numb,sum(yl.amnt) as mont,sum(yl.mhandcharge) as mhandcharge )  from MOrder m,YOrderlist yl,Custom cus,Edpcrowdkindtab kind,Edmtickettypetab et,Sysparv5 v5,Sysparv5 v55 ");
		hsql.append(" where m.orda>='"+rzti+"' and m.orda<='"+ldti+"' and  m.ortp in ('02','03') and m.ddzt in ('06','88') and tpfs='01'");	
		hsql.append(" and cus.ibusinessid = "+types);
		hsql.append(" and m.usid  in ("+usids+")");
		hsql.append(" and m.orid = yl.id.orid and m.usid = cus.usid  and kind.icrowdkindid = yl.icrowdkindid and yl.itickettypeid=et.itickettypeid and  m.tpfs=v5.id.pmcd and v5.id.pmky='TPFS' and  m.notef=v55.id.pmcd and v55.id.pmky='TDLB'  group by cus.bname,m.usid,cus.corpname,m.orid,m.srid,m.isc,v5.pmva,v55.pmva,m.orda,m.orti,yl.itickettypeid ,et.sztickettypename, kind.szcrowdkindname,yl.pric order by cus.bname,m.usid,m.orid ");
	
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		if(ps!=null){
			List list = ps.getItems();
			if(list!=null && list.size()>=1){
				Map map = null;
				for (int i = 0; i < list.size(); i++) {
					map = (Map)list.get(i); 				
					if(map.get("isc")!=null && !"".equals(map.get("isc"))&& !"0".equals(map.get("isc"))){
						Esfemployeetab e=(Esfemployeetab)this.get(Esfemployeetab.class, new Long(map.get("isc").toString()));
					  map.put("szemployeename", e.getSzemployeename());
					}				
				}
			}
			
		}
		return ps;
	}
	
	/**
	 * ��ѯͣ����ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param usids
	 * @param types
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-19
	 */
	public PaginationSupport queryTingPaiAllList(String rzti,String ldti,String usids,String types,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(cus.bname as bname,m.usid as usid,cus.corpname as corpname,em.szemployeename as szemployeename,m.orid as orid ,m.orda as orda,m.orti as orti,yl.itickettypeid as itickettypeid ,et.sztickettypename as sztickettypename, kind.szcrowdkindname as szcrowdkindname,yl.pric as pric ,sum(yl.numb) as numb,sum(yl.amnt) as mont )  from MOrder m,YOrderlist yl,Custom cus,Edpcrowdkindtab kind,Edmtickettypetab et,Esfemployeetab em ");
		hsql.append(" where m.orda>='"+rzti+"' and m.orda<='"+ldti+"' and m.ortp='02' and m.ddzt='06' and m.notef='04' ");	
		hsql.append(" and cus.ibusinessid = "+types);
		hsql.append(" and m.usid in ("+usids+")");
		hsql.append(" and m.orid = yl.id.orid and m.usid = cus.usid  and kind.icrowdkindid = yl.icrowdkindid and yl.itickettypeid=et.itickettypeid and m.isc=em.iemployeeid group by cus.bname,m.usid ,cus.corpname,em.szemployeename,m.orid,m.orda,m.orti,yl.itickettypeid ,et.sztickettypename, kind.szcrowdkindname,yl.pric  order by cus.bname,m.usid,m.orid ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	public List querySaleAllList(String rzti, String ldti, String usids,
			String types) {
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(cus.bname as bname,t.usid as usid,cus.corpname as corpname,t.ornm as ornm,t.id.orid as orid ,t.isc as isc,em.szemployeename as szemployeename,t.notec as notec,yl.itickettypeid as itickettypeid ,et.sztickettypename as sztickettypename, kind.szcrowdkindname as szcrowdkindname,yl.pric as pric ,sum(yl.numb) as numb,sum(yl.amnt) as mont )  from TOrder t,TOrderlist yl,Custom cus,Edpcrowdkindtab kind,Edmtickettypetab et,Esfemployeetab em ");
		hsql.append(" where substr(t.notec,1,10)>='"+rzti+"' and substr(t.notec,1,10)<='"+ldti+"' and    t.ddzt='11' ");	
		hsql.append(" and cus.ibusinessid = "+types);
		hsql.append(" and t.usid  in ("+usids+")");
		hsql.append(" and t.id.iscenicid=yl.id.iscenicid and t.id.orid=yl.id.orid and t.usid = cus.usid  and kind.icrowdkindid = yl.icrowdkindid and yl.itickettypeid=et.itickettypeid  and t.isc=em.iemployeeid  group by cus.bname,t.usid,cus.corpname,t.ornm,t.id.orid,t.isc,em.szemployeename,t.notec,yl.itickettypeid,et.sztickettypename, kind.szcrowdkindname,yl.pric order by cus.bname,t.usid,t.id.orid ");	
		System.out.println(hsql);
		List list = this.find(hsql.toString());
		return list;
	}

}

