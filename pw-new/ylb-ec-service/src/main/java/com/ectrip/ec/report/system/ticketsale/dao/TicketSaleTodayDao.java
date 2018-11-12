package com.ectrip.ec.report.system.ticketsale.dao;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketSaleTodayDao;

public class TicketSaleTodayDao extends GenericDao implements ITicketSaleTodayDao{
	
	/**
	 * Describe:��Ʊ������ʷ��ˮ��ѯ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleTodayService#querySaleTicketList(java.lang.String, java.lang.String, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param rzti
	 * @param ldti
	 * @param type
	 * @param iemployeeid
	 * @param parseInt
	 * @param pageSize
	 * @param string
	 * @return
	 * @author aozhuozu
	 * Date:2012-8-29
	 */
	public PaginationSupport querySaleTicketList(String rzti, String ldti,
			String type, Long iemployeeid, int parseInt, int pageSize,
			String url,String manyouno){
		PaginationSupport ps = null;
		StringBuffer hsql=new StringBuffer();
		hsql.append("select distinct new map(stss.id.isalesvoucherid as isalesvoucherid,s.ipayeer as iemployeeid,em.szemployeename as szemployeename,sment.isettlementid as isettlementid,v52.pmva as settlement,s.bysalesvouchertype as bysalesvouchertype,v51.pmva as salesvouchertype,s.ibusinessid as ibusinessid,e.szbusinessname as szbusinessname,et.isequence as isequence,et.bymaketicketway as bymaketicketway,sdetail.itickettypeid as itickettypeid,et.sztickettypename as sztickettypename,sdetail.mactualsaleprice as mactualsaleprice,stss.iplayerperticket as iplayerperticket,stss.szticketprintno as szticketprintno,sdetail.iticketplayer  as iticketplayer,stss.manyouno as manyouno,sment.dtmakedate as dtmakedate ) ");
		//sum(sdetail.iticketnum) as iticketnum,sum(sdetail.mtotalamount) as meventmoney,sum(sdetail.mhandcharge) as mhandcharge,
		hsql.append("from Stssalesvouchertab s,Stssalessettlementtab sment,Stssoldtickettab stss,Edmbusinesstab e,Sysparv5 v51,Sysparv5 v52,Stssalesvoucherdetailstab sdetail,Edmtickettypetab et ,Esfemployeetab em ");
		hsql.append("where sment.id.isalesvoucherid=s.id.isalesvoucherid and sment.id.iticketstationid=s.id.iticketstationid and e.ibusinessid=s.ibusinessid and v52.id.pmky='ZFFS' and v52.id.pmcd=sment.isettlementid and v51.id.pmky='PZLB' and v51.id.pmcd=s.bysalesvouchertype and sdetail.id.isalesvoucherid=s.id.isalesvoucherid and sdetail.id.iticketstationid=s.id.iticketstationid and sdetail.itickettypeid=et.itickettypeid and s.ipayeer=em.iemployeeid and stss.id.isalesvoucherdetailsid=sdetail.id.isalesvoucherdetailsid and stss.id.isalesvoucherid=sdetail.id.isalesvoucherid and stss.id.iticketstationid=sdetail.id.iticketstationid ");
		if(manyouno!=null&&!manyouno.equals("")){//��������ԭ���п���ʱ������������ѯ����
			hsql.append(" and stss.manyouno = '"+manyouno+"'");
		}else{
			hsql.append("and s.ipayeer="+iemployeeid+"" );
//			hsql.append("and stss.dtmakedate>='"+rzti+"' and stss.dtmakedate<='"+ldti+"' ");
			hsql.append("and substr(stss.dtmakedate,1,10)>='"+rzti+"' and substr(stss.dtmakedate,1,10)<='"+ldti+"' ");
		}
//		hsql.append("group  by s.ipayeer,em.szemployeename, sment.isettlementid,v52.pmva,s.bysalesvouchertype,v51.pmva,s.ibusinessid,e.szbusinessname,et.isequence,et.bymaketicketway,sdetail.itickettypeid,et.sztickettypename,sdetail.mactualsaleprice,stss.iplayerperticket,stss.szticketprintno ");
		hsql.append("order by stss.id.isalesvoucherid desc,s.ipayeer,em.szemployeename, sment.isettlementid,v52.pmva,s.bysalesvouchertype,v51.pmva,s.ibusinessid,e.szbusinessname,et.isequence,et.bymaketicketway,sdetail.itickettypeid,et.sztickettypename,sdetail.mactualsaleprice,stss.iplayerperticket,stss.szticketprintno");
	
		ps=this.findPage(hsql.toString(), pageSize, parseInt, url);
		return ps;
	}
}

