package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.permitenter.Upsalesetlement;
import com.ectrip.ticket.permitenter.dao.IUpdatesalevoucherDAO;

public class UpdatesalevoucherDAO extends GenericDao implements IUpdatesalevoucherDAO{

	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ������ƾ֤�еĽ�����Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IUpdatesalevoucherService#checkListsetlement(java.lang.String)
	 * @param ticketno
	 * @return
	 * @author lijingrui
	 * Date:2013-5-21
	 */
	public List checkListsetlement(String ticketno){
		String sql="select new map(st.id.isalesvoucherid as isalesvoucherid,st.imaker as imaker,stss.isettlementid as isettlementid,esf.szemployeename as szemployeename,sts.meventmoney as meventmoney,sts.iticketnum as iticketnum,sts.szstartserial as szstartserial,sts.szendserial as szendserial,sys1.pmva as zffs,edm.sztickettypename as sztickettypename,esb.szstationname as szstationname,st.dtmakedate as dtmakedate) from Stssalesvouchertab st,Stssalesvoucherdetailstab sts,Stssalessettlementtab stss,Sysparv5 sys1,Esfemployeetab esf,Edmtickettypetab edm,Esbticketstationtab esb where st.id.isalesvoucherid=sts.id.isalesvoucherid and st.id.isalesvoucherid=stss.id.isalesvoucherid"+
					" and stss.isettlementid=sys1.id.pmcd and sys1.id.pmky='ZFFS' and st.bysalesvouchertype='01' and esf.iemployeeid=st.imaker and edm.itickettypeid=sts.itickettypeid and esb.iticketstationid=st.id.iticketstationid and st.id.isalesvoucherid in (select sd.id.isalesvoucherid from Stssoldtickettab sd where sd.szticketprintno='"+ticketno+"')";
		return this.find(sql);
	}
	
	/**
	 * *
	 * Describe:���� ��������Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IUpdatesalevoucherService#insertSetlement(java.lang.String, java.lang.String)
	 * @param tieketno
	 * @param zffs
	 * @author lijingrui
	 * Date:2013-5-21
	 */
	public void insertSetlement(String tieketno,String zffs,Esfemployeetab esfemployeetab){
		String sql="select new map(st.id.isalesvoucherid as isalesvoucherid, st.imaker as imaker,stss.isettlementid as isettlementid,esf.szemployeename as szemployeename,sts.meventmoney as meventmoney,sts.iticketnum as iticketnum,sts.szstartserial as szstartserial,sts.szendserial as szendserial,sys1.pmva as zffs,edm.sztickettypename as sztickettypename,esb.szstationname as szstationname,st.dtmakedate as dtmakedate) from Stssalesvouchertab st,Stssalesvoucherdetailstab sts,Stssalessettlementtab stss,Sysparv5 sys1,Esfemployeetab esf,Edmtickettypetab edm,Esbticketstationtab esb where st.id.isalesvoucherid=sts.id.isalesvoucherid and st.id.isalesvoucherid=stss.id.isalesvoucherid "+
				" and stss.isettlementid=sys1.id.pmcd and sys1.id.pmky='ZFFS' and st.bysalesvouchertype='01' and esf.iemployeeid=st.imaker and edm.itickettypeid=sts.itickettypeid and esb.iticketstationid=st.id.iticketstationid and st.id.isalesvoucherid in (select sd.id.isalesvoucherid from Stssoldtickettab sd where sd.szticketprintno='"+tieketno+"')";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Map map = null;
			for(int i=0;i<list.size();i++){
				map = (Map) list.get(i);
				Long iemployeeid=Long.parseLong(map.get("imaker").toString());
				String mentsetid=map.get("isettlementid").toString();
				String dtmakedate=map.get("dtmakedate").toString();
				String startcode=map.get("szstartserial").toString();
				String endcode=map.get("szendserial").toString();
				Long mont=Long.parseLong(map.get("iticketnum").toString());
				Double money=Double.parseDouble(map.get("meventmoney").toString());
				Long isalesvoucherid=Long.parseLong(map.get("isalesvoucherid").toString());
				
				if(mentsetid!=null&&!mentsetid.equals("")&&!mentsetid.equals(zffs)){
					Upsalesetlement mentsale=new Upsalesetlement();
					Long maxid=this.getMaxPk("seq", "Upsalesetlement");
					mentsale.setSeq(maxid+1);
					mentsale.setIemployeeid(iemployeeid);
					mentsale.setMentdata(dtmakedate.substring(0, 10));
					mentsale.setSzstartcode(startcode);
					mentsale.setSzendcode(endcode);
					mentsale.setMont(mont);
					mentsale.setMoney(money);
					mentsale.setYzffs(mentsetid);
					mentsale.setUzffs(zffs);
					mentsale.setDtmakedate(Tools.getDayTimes());
					mentsale.setIda(1L);  //�޸�����
					mentsale.setIdb(esfemployeetab.getIemployeeid());
					
					mentsale.setIsa(map.get("isalesvoucherid").toString());
					
					this.save(mentsale);
					
					String hsql=" from Stssalessettlementtab sts where sts.id.isalesvoucherid= "+isalesvoucherid;
					List lst=this.find(hsql);
					if(lst!=null&&lst.size()>0){
						for(int x=0;x<lst.size();x++){
							Stssalessettlementtab setlement=(Stssalessettlementtab)lst.get(x);
							setlement.setIsettlementid(zffs);
							this.update(setlement);
						} 
					}
					
				}
				
				
			}
		}
		
	}
	
	/**
	 * *
	 * Describe:��������ѯ
	 * @see com.ectrip.system.permitenter.service.iservice.IUpdatesalevoucherService#querySalementList(java.lang.Long, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param iemployeeid
	 * @param rzti
	 * @param ldti
	 * @param type
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2013-6-3
	 */
	public PaginationSupport querySalementList(Long iemployeeid,String rzti,String ldti,String type,int page,int pageSize,String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(esf.szemployeename as szemployeename,up.mentdata as mentdata,up.szstartcode as szstartcode,up.szendcode as szendcode,up.mont as mont,up.money as money,sys1.pmva as yzffs,sys2.pmva as uzffs,up.dtmakedate as dtmakedate,ep.szemployeename as employeename) from Upsalesetlement up,Esfemployeetab esf,Esfemployeetab ep,Sysparv5 sys1,Sysparv5 sys2 where up.iemployeeid=esf.iemployeeid and up.idb=ep.iemployeeid and up.yzffs=sys1.id.pmcd and sys1.id.pmky='ZFFS' and up.uzffs=sys2.id.pmcd and sys2.id.pmky='ZFFS' ");
		if(iemployeeid!=null&&!iemployeeid.equals("")&&iemployeeid!=0){
			sql.append(" and up.iemployeeid="+iemployeeid);
		}
		if(rzti!=null&&!rzti.equals("")&&ldti!=null&&!ldti.equals("")){
			sql.append(" and substr(up.dtmakedate,0,10)>='"+rzti+"' and substr(up.dtmakedate,0,10)<='"+ldti+"' ");
		}
		sql.append(" order by up.dtmakedate desc,up.iemployeeid ");
		return this.findPage(sql.toString(), pageSize, page, url);
	}
	
}

