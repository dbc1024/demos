package com.ectrip.ec.report.system.datereport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.report.system.datereport.dao.idao.IPrintCodeDAO;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class PrintCodeDAO extends GenericDao implements IPrintCodeDAO{

	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ��Ʊ�ŵ�״̬
	 * @see com.ectrip.report.system.datereport.dao.idao.IPrintCodeDAO#showAllListcode(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param serialcode
	 * @param scenics
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:2012-8-22
	 */
	public PaginationSupport showAllListcode(String serialcode,String scenics,int page,int pageSize,String url) throws Exception{
		PaginationSupport ps =null;	
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(st.id.szsoldticketid as szsoldticketid,st.id.isalesvoucherid as isalesvoucherid,st.id.isalesvoucherdetailsid as isalesvoucherdetailsid,st.id.iticketstationid as iticketstationid,esb.szscenicname as szscenicname,edm.sztickettypename as sztickettypename,st.szticketprintno as szticketprintno,st.dtmakedate as dtmakedate) from Stssoldtickettab st,Esbscenicareatab esb,Edmtickettypetab edm where ( st.szticketprintno='"+serialcode+"' or st.myzj='"+serialcode+"') and st.iscenicid=esb.iscenicid and edm.itickettypeid=st.itickettypeid");
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" and st.iscenicid in ("+scenics+")");
		}
		sql.append(" order by st.dtmakedate desc");
		ps=this.findPage(sql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				Long b=0L;//�ж��Ƿ��Ʊ
				Long a=0L;//�Ƿ���Ч
				map = (Map) list.get(i);
				if(map.get("szsoldticketid")!=null&&map.get("isalesvoucherid")!=null&&map.get("isalesvoucherdetailsid")!=null&&map.get("iticketstationid")!=null){
					Long szsoldticketid=Long.parseLong(map.get("szsoldticketid").toString());
					Long isalesvoucherid=Long.parseLong(map.get("isalesvoucherid").toString());
					Long isalesvoucherdetailsid=Long.parseLong(map.get("isalesvoucherdetailsid").toString());
					Long iticketstationid=Long.parseLong(map.get("iticketstationid").toString());
					
					String hsql="select new map(edm.sztickettypename as sztickettypename,ed.szgardengatename as szgardengatename,sk.ipasstimes as ipasstimes,sk.ipassedtimes as ipassedtimes,sk.isvalid as isvalid) from Stssoldticketsubtab sk,Edmtickettypetab edm,Esbgardengatetab ed where edm.itickettypeid=sk.iztickettypeid and  sk.id.szsoldticketid="+szsoldticketid+" and sk.id.isalesvoucherdetailsid="+isalesvoucherdetailsid+" and sk.id.isalesvoucherid="+isalesvoucherid+" and sk.id.iticketstationid="+iticketstationid+" and sk.igardengateid=ed.id.igardengateid";
					List lst=this.find(hsql);
					if(lst!=null&&lst.size()>0){
						for(int x=0;x<lst.size();x++){
							Stssoldticketsubtab stssold=new Stssoldticketsubtab(); 
							BeanUtils.populate(stssold, (Map) lst.get(x));
							if(stssold.getIpasstimes().equals(stssold.getIpassedtimes())){//�Ƿ��Ʊ
								b++;
							}
							if(stssold.getIsvalid()!=null&&stssold.getIsvalid()==1){//�Ƿ���Ч
								a++;
							}
						}
						if(a!=0&&b==0){
							map.put("isstaus", "����");
						}else if(a!=0&&b!=0){
							map.put("isstaus", "�Ѽ�");
						}else{
							map.put("isstaus", "��Ʊ");
						}
					}
					
					map.put("ticketList", lst);
				}
			}
			return ps;
		}else{
			return null;
		}
	}
	
	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ��Ʊ���Ƿ�������
	 * @see com.ectrip.report.system.datereport.dao.idao.IPrintCodeDAO#showWarehouse(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param serialcode
	 * @param scenics
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-8-22
	 */
	public PaginationSupport showWarehouse(String serialcode,String scenics,int page,int pageSize,String url){
		PaginationSupport ps =null;	
		StringBuffer msql=new StringBuffer();
		msql.append(" from Edmticketnoruletab rule where rule.byisuse=1 ");
		if(scenics!=null&&!scenics.equals("")){
			msql.append(" and rule.iscenicid in ("+scenics+")");
		}
		List lst=this.find(msql.toString());
		if(lst!=null&&lst.size()>0){
			for(int i=0;i<lst.size();i++){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(i);
				if(ticketrule.getIticketnolen()==serialcode.length()){
					String lsh=serialcode.substring(ticketrule.getIntons2()+ ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ ticketrule.getItickettypecodepos()+ ticketrule.getIserialnolen());
					StringBuffer hsql=new StringBuffer("select new map(pk.idetailsid as idetailsid,pk.ireceiverid as ireceiverid,pk.itickettypeid as itickettypeid,pk.szstartticketcode as szstartticketcode,pk.szendticketcode as szendticketcode,pk.istartserial as istartserial,pk.iendserial as iendserial,pk.iamount as iamount,esf.szemployeename as szemployeename,edm.sztickettypename as sztickettypename) from Iompersonalticketdetails pk,Edmtickettypetab edm,Esfemployeetab esf where pk.itickettypeid=edm.itickettypeid and esf.iemployeeid=pk.ireceiverid ");
					if (ticketrule.getIntons1() != null
							&& ticketrule.getIntons1() == 1) {
						Pattern p = Pattern.compile("^[0-9]+$");
						boolean b = p.matcher(lsh).matches();
						if(b==true){
							hsql.append(" and pk.istartserial<="+Long.parseLong(lsh)+"  and pk.iendserial>="+Long.parseLong(lsh));
						}else{
							continue;
						}
					} else {
						hsql.append(" and pk.istartserial<="+Tools.Text36ToConvert(lsh)+"  and pk.iendserial>="+Tools.Text36ToConvert(lsh));
					}
//					ps=this.findPage(hsql.toString(), pageSize, page, url);
					List hList=this.find(hsql.toString());
					List list=new ArrayList();
					if(hList!=null&&hList.size()>0){
						 Map map = null;
						for(int x=0;x<hList.size();x++){
							map=(Map)hList.get(x);
							Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, Long.parseLong(map.get("itickettypeid").toString()));
							StringBuffer code=new StringBuffer();
							code.append(edmticket.getSzticketprintcode());
							//��ȡƱ���е�Ʊ������Ϣ�����Ƚ�
							String tkcode=serialcode.substring(ticketrule.getIntons2(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos());
							Integer zc=ticketrule.getItickettypecodepos();
							if(edmticket.getSzticketprintcode().length()<zc.intValue()){
								for (int b = 0; b <zc - edmticket.getSzticketprintcode().length(); b++) {
									code.append("0");
								}
							}else if(edmticket.getSzticketprintcode().length()>zc.intValue()){
								continue;
							}
							if(code.toString().equals(tkcode)){
								StringBuffer ksql=new StringBuffer("select new map(pk.idetailsid as idetailsid,pk.ireceiverid as ireceiverid,pk.itickettypeid as itickettypeid,pk.szstartticketcode as szstartticketcode,pk.szendticketcode as szendticketcode,pk.istartserial as istartserial,pk.iendserial as iendserial,pk.iamount as iamount,esf.szemployeename as szemployeename,edm.sztickettypename as sztickettypename) from Iompersonalticketdetails pk,Edmtickettypetab edm,Esfemployeetab esf where pk.itickettypeid=edm.itickettypeid and esf.iemployeeid=pk.ireceiverid and pk.itickettypeid="+Long.parseLong(map.get("itickettypeid").toString()));
								if (ticketrule.getIntons1() != null
										&& ticketrule.getIntons1() == 1) {
									ksql.append(" and pk.istartserial<="+Long.parseLong(lsh)+"  and pk.iendserial>="+Long.parseLong(lsh));
								} else {
									ksql.append(" and pk.istartserial<="+Tools.Text36ToConvert(lsh)+"  and pk.iendserial>="+Tools.Text36ToConvert(lsh));
								}
								
								ps=this.findPage(ksql.toString(), pageSize, page, url);
								return ps;
							}else{
								continue;
							}
						}
					}else{
						continue ;
					}
				}else{
					continue;
				}
			}
		}else{
			return null;
		}
		return null;
	}
	
	/**
	 * *
	 * Describe:��ѯ�ۼ���Ʊ��Ϣ
	 * @see com.ectrip.report.system.datereport.dao.idao.IPrintCodeDAO#querySaleTicketbyprintno(java.lang.String, int)
	 * @param ticketno
	 * @param type
	 * @return
	 * @author chenxinhao
	 * Date:2012-12-27
	 */
	public List querySaleTicketbyprintno(String ticketno){
		StringBuffer hsql = new StringBuffer();
		StringBuffer hishsql = new StringBuffer();
		List qlist = new ArrayList();//����ȫ����Ϣ
		hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,detail.mactualsaleprice as mactualsaleprice,detail.iticketnum as iticketnum,detail.meventmoney as meventmoney,detail.mhandcharge as mhandcharge,detail.dtstartdate as dtstartdate,detail.dtenddate as dtenddate,stk.myzj as myzj,stk.szticketprintno as szticketprintno,detail.iplayerperticket as iplayerperticket) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettab stk,Stssalesvoucherdetailstab detail,Edmtickettypetab e where  (stk.szticketprintno='"
				+ ticketno + "' or stk.myzj='"+ticketno.toUpperCase()+"' ) ");
		hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
		hsql.append(" and stk.id.isalesvoucherdetailsid = detail.id.isalesvoucherdetailsid and detail.id.isalesvoucherid = sale.id.isalesvoucherid and detail.id.iticketstationid = sale.id.iticketstationid and detail.itickettypeid = e.itickettypeid and stk.itickettypeid = detail.itickettypeid ");
		hsql.append(" order by sale.szsalesvoucherno desc ");
			
		hishsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,detail.mactualsaleprice as mactualsaleprice,detail.iticketnum as iticketnum,detail.meventmoney as meventmoney,detail.mhandcharge as mhandcharge,detail.dtstartdate as dtstartdate,detail.dtenddate as dtenddate,stk.myzj as myzj,stk.szticketprintno as szticketprintno,detail.iplayerperticket as iplayerperticket) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettablist stk,Stssalesvoucherdetailstablist detail,Edmtickettypetab e where ( stk.szticketprintno='"
				+ ticketno + "' or stk.myzj='"+ticketno.toUpperCase()+"' ) ");
		hishsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
		hishsql.append(" and stk.id.isalesvoucherdetailsid = detail.id.isalesvoucherdetailsid and detail.id.isalesvoucherid = sale.id.isalesvoucherid and detail.id.iticketstationid = sale.id.iticketstationid and detail.itickettypeid = e.itickettypeid and stk.itickettypeid = detail.itickettypeid ");
		hishsql.append(" order by sale.szsalesvoucherno desc ");
		List salelist = this.find(hsql.toString());//��Ʊ��Ϣ
		List hissalelist = this.find(hishsql.toString());//��ʷ��Ʊ��Ϣ
		if(hissalelist!=null&&hissalelist.size()>0){
			for(int i=0;i<hissalelist.size();i++){
				salelist.add(hissalelist.get(i));
			}
		}
		
		List backlist = new ArrayList();//��Ʊ��Ϣ
		List checklist = new ArrayList();//��Ʊ��Ϣ
		if(salelist!=null&&salelist.size()>0){
			qlist.add(salelist);
			for(int i=0;i<salelist.size();i++){
				Map map = (Map) salelist.get(i);
				String ticketcode = (String) map.get("szticketprintno");
				Long isalesvoucherid = (Long) map.get("isalesvoucherid");
				Long iticketstationid = (Long) map.get("iticketstationid");
				//��ѯ��Ʊ��Ϣ
				StringBuffer hsql2 = new StringBuffer();
				StringBuffer hishsql2 = new StringBuffer();
				hsql2.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,sall.szstartserial as szstartserial,sall.dtstartdate as dtstartdate,sall.dtenddate as dtenddate,sall.meventmoney as meventmoney,sall.mhandcharge as mhandcharge) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssalesvoucherdetailstab sall,Edmtickettypetab e where  sall.szstartserial='" + ticketcode + "' ");
				hsql2.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype   and sall.id.isalesvoucherid=sale.id.isalesvoucherid and sall.id.iticketstationid=sale.id.iticketstationid and sall.itickettypeid = e.itickettypeid ");
				hsql2.append(" order by sale.szsalesvoucherno desc ");
				//��ѯ��ʷ��Ʊ��Ϣ
				hishsql2.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,sall.szstartserial as szstartserial,sall.dtstartdate as dtstartdate,sall.dtenddate as dtenddate,sall.meventmoney as meventmoney,sall.mhandcharge as mhandcharge) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssalesvoucherdetailstablist sall,Edmtickettypetab e where  sall.szstartserial='" + ticketcode + "'  ");
				hishsql2.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and sall.id.isalesvoucherid=sale.id.isalesvoucherid  and sall.id.iticketstationid=sale.id.iticketstationid and sall.itickettypeid = e.itickettypeid ");
				hishsql2.append(" order by sale.szsalesvoucherno desc ");
				List list = this.find(hsql2.toString());
				if(list==null||list.size()<1){
					list = new ArrayList();
				}
				List hislist = this.find(hishsql2.toString());
				if(hislist!=null&&hislist.size()>0){
					for(int j=0;j<hislist.size();j++){
						list.add(hislist.get(j));
					}
				}
				if(list!=null&&list.size()>0){
					for(int j = 0;j<list.size();j++){
						Map map2 = (Map) list.get(j);
						map2.put("szticketprintno", ticketcode);
						backlist.add(map2);
					}
				}
				//��ѯ��Ʊ��Ϣ����
				//��ѯ��Ʊ��Ϣ
				StringBuffer hsql3 = new StringBuffer();
				//��ѯ��ʷ��Ʊ��Ϣ
				StringBuffer hishsql3 = new StringBuffer();
				hsql3.append("select distinct new map(su.isvalid as isvalid,s.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno, s.szticketprintno as szticketprintno,s.iserialnum as iserialnum,su.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,su.iztickettypeid as iztickettypeid,ez.sztickettypename as zsztickettypename,g.szgardengatename as szgardengatename,su.ipassedtimes as ipassedtimes,su.dtbegindate as dtbegindate,su.dtenddate as dtenddate,su.dtlastcheckdate as dtlastcheckdate) from Stssalesvouchertab sale,Stssoldtickettab s ,Stssoldticketsubtab su,Edmtickettypetab e,Edmtickettypetab ez,Esbgardengatetab g  where ");
				hsql3.append(" s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid+" and s.szticketprintno = '"+ticketcode+"' and s.id.szsoldticketid=su.id.szsoldticketid and s.id.isalesvoucherdetailsid =su.id.isalesvoucherdetailsid and s.id.isalesvoucherid=su.id.isalesvoucherid and s.id.iticketstationid=su.id.iticketstationid and su.itickettypeid=e.itickettypeid and su.iztickettypeid=ez.itickettypeid  and g.id.igardengateid=su.igardengateid and sale.id.isalesvoucherid=s.id.isalesvoucherid and s.id.iticketstationid=sale.id.iticketstationid");
					
				hishsql3.append("select distinct new map(su.isvalid as isvalid,s.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno, s.szticketprintno as szticketprintno,s.iserialnum as iserialnum,su.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,su.iztickettypeid as iztickettypeid,ez.sztickettypename as zsztickettypename,g.szgardengatename as szgardengatename,su.ipassedtimes as ipassedtimes,su.dtbegindate as dtbegindate,su.dtenddate as dtenddate,su.dtlastcheckdate as dtlastcheckdate) from Stssalesvouchertablist sale,Stssoldtickettablist s ,Stssoldticketsubtablist su,Edmtickettypetab e,Edmtickettypetab ez,Esbgardengatetab g  where  ");				
				hishsql3.append(" s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid+" and s.szticketprintno = '"+ticketcode+"' and s.id.szsoldticketid=su.id.szsoldticketid and s.id.isalesvoucherdetailsid =su.id.isalesvoucherdetailsid and s.id.isalesvoucherid=su.id.isalesvoucherid and s.id.iticketstationid=su.id.iticketstationid and su.itickettypeid=e.itickettypeid and su.iztickettypeid=ez.itickettypeid  and g.id.igardengateid=su.igardengateid and sale.id.isalesvoucherid=s.id.isalesvoucherid and s.id.iticketstationid=sale.id.iticketstationid");
				List list2 = this.find(hsql3.toString());
				List hislist2 = this.find(hishsql3.toString());
				if(hislist2!=null&&hislist2.size()>0){
					for(int x = 0;x<hislist2.size();x++){
						list2.add(hislist2.get(x));
					}
				}
				if(list2!=null&&list2.size()>0){
					for(int x = 0;x<list2.size();x++){
						Map map3 = (Map) list2.get(x);
						checklist.add(map3);
					}
				}
			}
			qlist.add(backlist);
			qlist.add(checklist);
			return qlist;
		}
		return null;
	}
	
	/**
	 * *
	 * Describe:��ѯ�ۼ���Ʊ��Ϣ
	 * @see com.ectrip.report.system.datereport.dao.idao.IPrintCodeDAO#querySaleTicketbyprintno(java.lang.String, int)
	 * @param ticketno
	 * @param type
	 * @return
	 * @author chenxinhao
	 * Date:2012-12-27
	 */
	public List querySaleTicketbyprintno(String ticketno,int type){
		System.out.println(type);
		StringBuffer hsql = new StringBuffer();
		List qlist = new ArrayList();//����ȫ����Ϣ
		if(type==0){
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,detail.mactualsaleprice as mactualsaleprice,detail.iticketnum as iticketnum,detail.meventmoney as meventmoney,detail.mhandcharge as mhandcharge,detail.dtstartdate as dtstartdate,detail.dtenddate as dtenddate,stk.myzj as myzj,stk.szticketprintno as szticketprintno,detail.iplayerperticket as iplayerperticket) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettab stk,Stssalesvoucherdetailstab detail,Edmtickettypetab e where  (stk.szticketprintno='"
					+ ticketno + "' or stk.myzj='"+ticketno.toUpperCase()+"' ) ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
			hsql.append(" and detail.id.isalesvoucherid = sale.id.isalesvoucherid and detail.id.iticketstationid = sale.id.iticketstationid and detail.itickettypeid = e.itickettypeid and stk.itickettypeid = detail.itickettypeid ");
			hsql.append(" order by sale.szsalesvoucherno desc ");
			System.out.println(hsql.toString());
		}else{
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,detail.mactualsaleprice as mactualsaleprice,detail.iticketnum as iticketnum,detail.meventmoney as meventmoney,detail.mhandcharge as mhandcharge,detail.dtstartdate as dtstartdate,detail.dtenddate as dtenddate,stk.myzj as myzj,stk.szticketprintno as szticketprintno,detail.iplayerperticket as iplayerperticket) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettablist stk,Stssalesvoucherdetailstablist detail,Edmtickettypetab e where ( stk.szticketprintno='"
					+ ticketno + "' or stk.myzj='"+ticketno.toUpperCase()+"' ) ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
			hsql.append(" and detail.id.isalesvoucherid = sale.id.isalesvoucherid and detail.id.iticketstationid = sale.id.iticketstationid and detail.itickettypeid = e.itickettypeid and stk.itickettypeid = detail.itickettypeid ");
			hsql.append(" order by sale.szsalesvoucherno desc ");
			System.out.println(hsql.toString());
		}
		List salelist = this.find(hsql.toString());//��Ʊ��Ϣ
		List backlist = new ArrayList();//��Ʊ��Ϣ
		List checklist = new ArrayList();//��Ʊ��Ϣ
		if(salelist!=null&&salelist.size()>0){
			qlist.add(salelist);
			for(int i=0;i<salelist.size();i++){
				Map map = (Map) salelist.get(i);
				String ticketcode = (String) map.get("szticketprintno");
				Long isalesvoucherid = (Long) map.get("isalesvoucherid");
				Long iticketstationid = (Long) map.get("iticketstationid");
				//��ѯ��Ʊ��Ϣ
				StringBuffer hsql2 = new StringBuffer();
				if(type==0){
					hsql2.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,sall.szstartserial as szstartserial,sall.dtstartdate as dtstartdate,sall.dtenddate as dtenddate,sall.meventmoney as meventmoney,sall.mhandcharge as mhandcharge) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssalesvoucherdetailstab sall,Edmtickettypetab e where  sall.szstartserial='" + ticketcode + "' ");
				    hsql2.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype   and sall.id.isalesvoucherid=sale.id.isalesvoucherid and sall.id.iticketstationid=sale.id.iticketstationid and sall.itickettypeid = e.itickettypeid ");
				    hsql2.append(" order by sale.szsalesvoucherno desc ");
				}else{
					hsql2.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,e.sztickettypename as sztickettypename,sall.szstartserial as szstartserial,sall.dtstartdate as dtstartdate,sall.dtenddate as dtenddate,sall.meventmoney as meventmoney,sall.mhandcharge as mhandcharge) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssalesvoucherdetailstablist sall,Edmtickettypetab e where  sall.szstartserial='" + ticketcode + "'  ");
					hsql2.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and sall.id.isalesvoucherid=sale.id.isalesvoucherid  and sall.id.iticketstationid=sale.id.iticketstationid and sall.itickettypeid = e.itickettypeid ");
					hsql2.append(" order by sale.szsalesvoucherno desc ");
				}
				List list = this.find(hsql2.toString());
				if(list!=null&&list.size()>0){
					for(int j = 0;j<list.size();j++){
						Map map2 = (Map) list.get(j);
						map2.put("szticketprintno", ticketcode);
						backlist.add(map2);
					}
				}
				//��ѯ��Ʊ��Ϣ����
				//��ѯ��Ʊ��Ϣ
				StringBuffer hsql3 = new StringBuffer();
				if(type==0){
					hsql3.append("select distinct new map(s.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno, s.szticketprintno as szticketprintno,s.iserialnum as iserialnum,su.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,su.iztickettypeid as iztickettypeid,ez.sztickettypename as zsztickettypename,g.szgardengatename as szgardengatename,su.ipassedtimes as ipassedtimes,su.dtbegindate as dtbegindate,su.dtenddate as dtenddate,su.dtmakedate as dtmakedate,su.isvalid as isvalid ) from Stssalesvouchertab sale,Stssoldtickettab s ,Stssoldticketsubtab su,Edmtickettypetab e,Edmtickettypetab ez,Esbgardengatetab g  where ");
				}else{
					hsql3.append("select distinct new map(s.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno, s.szticketprintno as szticketprintno,s.iserialnum as iserialnum,su.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,su.iztickettypeid as iztickettypeid,ez.sztickettypename as zsztickettypename,g.szgardengatename as szgardengatename,su.ipassedtimes as ipassedtimes,su.dtbegindate as dtbegindate,su.dtenddate as dtenddate,su.dtmakedate as dtmakedate,su.isvalid as isvalid ) from Stssalesvouchertablist sale,Stssoldtickettablist s ,Stssoldticketsubtablist su,Edmtickettypetab e,Edmtickettypetab ez,Esbgardengatetab g  where  ");				
				}
				hsql3.append(" s.id.isalesvoucherid="+isalesvoucherid+" and s.id.iticketstationid="+iticketstationid+" and s.szticketprintno = '"+ticketcode+"' and s.id.szsoldticketid=su.id.szsoldticketid and s.id.isalesvoucherdetailsid =su.id.isalesvoucherdetailsid and s.id.isalesvoucherid=su.id.isalesvoucherid and s.id.iticketstationid=su.id.iticketstationid and su.itickettypeid=e.itickettypeid and su.iztickettypeid=ez.itickettypeid  and g.id.igardengateid=su.igardengateid and g.id.iscenicid=s.iscenicid and sale.id.isalesvoucherid=s.id.isalesvoucherid and s.id.iticketstationid=sale.id.iticketstationid");
				List list2 = this.find(hsql3.toString());
				if(list2!=null&&list2.size()>0){
					for(int x = 0;x<list2.size();x++){
						Map map3 = (Map) list2.get(x);
						checklist.add(map3);
					}
				}
			}
			qlist.add(backlist);
			qlist.add(checklist);
			return qlist;
		}
		return null;
	}
	/**
	 * *
	 * Describe:����Ʊ�Ų�ѯ��Ʊ���Ƿ�������
	 * @see com.ectrip.report.system.datereport.dao.idao.IPrintCodeDAO#showWarehouse(java.lang.String, java.lang.String)
	 * @param serialcode
	 * @param scenics
	 * @return
	 * @author chenxinhao
	 * Date:2012-12-27
	 */
	public List showWarehouse(String serialcode,String scenics){
		StringBuffer msql=new StringBuffer();
		msql.append(" from Edmticketnoruletab rule where rule.byisuse=1 ");
		if(scenics!=null&&!scenics.equals("")){
			msql.append(" and rule.iscenicid in ("+scenics+")");
		}
		List lst=this.find(msql.toString());
		if(lst!=null&&lst.size()>0){
			for(int i=0;i<lst.size();i++){
				Edmticketnoruletab ticketrule=(Edmticketnoruletab) lst.get(i);
				if(ticketrule.getIticketnolen()==serialcode.length()){
					String lsh=serialcode.substring(ticketrule.getIntons2()+ ticketrule.getItickettypecodepos(), ticketrule.getIntons2()+ ticketrule.getItickettypecodepos()+ ticketrule.getIserialnolen());
					StringBuffer hsql=new StringBuffer("select new map(pk.idetailsid as idetailsid,pk.ireceiverid as ireceiverid,pk.itickettypeid as itickettypeid,pk.szstartticketcode as szstartticketcode,pk.szendticketcode as szendticketcode,pk.istartserial as istartserial,pk.iendserial as iendserial,pk.iamount as iamount,esf.szemployeename as szemployeename,edm.sztickettypename as sztickettypename) from Iompersonalticketdetails pk,Edmtickettypetab edm,Esfemployeetab esf where pk.itickettypeid=edm.itickettypeid and esf.iemployeeid=pk.ireceiverid ");
					if (ticketrule.getIntons1() != null
							&& ticketrule.getIntons1() == 1) {
						Pattern p = Pattern.compile("^[0-9]+$");
						boolean b = p.matcher(lsh).matches();
						if(b==true){
							hsql.append(" and pk.istartserial<="+Long.parseLong(lsh)+"  and pk.iendserial>="+Long.parseLong(lsh));
						}else{
							continue;
						}
					} else {
						hsql.append(" and pk.istartserial<="+Tools.Text36ToConvert(lsh)+"  and pk.iendserial>="+Tools.Text36ToConvert(lsh));
					}
					List hList =this.find(hsql.toString());
					if(hList!=null&&hList.size()>0){
						boolean bs=false;
						for(int x=0;x<hList.size();x++){
							Map map=(Map) hList.get(x);
							Edmtickettypetab edmticket=(Edmtickettypetab) this.get(Edmtickettypetab.class, Long.parseLong(map.get("itickettypeid").toString()));
							StringBuffer code=new StringBuffer();
							code.append(edmticket.getSzticketprintcode());
							//��ȡƱ���е�Ʊ������Ϣ�����Ƚ�
							String tkcode=serialcode.substring(ticketrule.getIntons2(), ticketrule.getIntons2()+ticketrule.getItickettypecodepos());
							Integer zc=ticketrule.getItickettypecodepos();
							if(edmticket.getSzticketprintcode().length()<zc.intValue()){
								for (int b = 0; b <zc - edmticket.getSzticketprintcode().length(); b++) {
									code.append("0");
								}
							}else if(edmticket.getSzticketprintcode().length()>zc.intValue()){
								continue;
							}
							if(code.toString().equals(tkcode)){
								bs=true;
							}
							
						}
						if(bs){
							return hList;
						}else{
							continue;
						}
					}else{
						continue ;
					}
				}else{
					continue;
				}
			}
		}else{
			return null;
		}
		return null;
	}
}

