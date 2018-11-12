package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.salesmanager.dao.IReserveDateDAO;

public class ReserveDateDAO extends GenericDao implements IReserveDateDAO{

	/**
	 * *
	 * Describe:查询出年卡类型的产品
	 * @see com.ectrip.system.salesmanager.dao.idao.IReserveDateDAO#getListedmticketype(java.lang.String)
	 * @param scentics
	 * @return
	 * @author lijingrui
	 * Date:2012-12-26
	 */
	public List getListedmticketype(String scentics){
		String sql="from Edmtickettypetab edm where edm.bycategorytype='0014' and edm.byisuse=1 ";
		if(scentics!=null&&!scentics.equals("")){
			sql+=" and edm.iscenicid in ('"+scentics+"')";
		}
		return this.find(sql);
	}

	/**
	 * *
	 * Describe:查询出售出有效的年卡票
	 * @see com.ectrip.system.salesmanager.dao.idao.IReserveDateDAO#showAllvoucherticket(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2012-12-26
	 */
	public PaginationSupport showAllvoucherticket(Long itickettypeid,int page,int pageSize,String url,String manyouno){
		PaginationSupport ps=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select distinct new map(st.id.szsoldticketid as szsoldticketid,st.id.isalesvoucherid as isalesvoucherid,st.id.isalesvoucherdetailsid as isalesvoucherdetailsid,st.id.iticketstationid as iticketstationid,esb.szscenicname as szscenicname,edm.sztickettypename as sztickettypename,st.szticketprintno as szticketprintno,st.dtmakedate as dtmakedate,st.myzj as myzj,st.dtstartdate as dtstartdate,st.dtenddate as dtenddate,st.name2 as name2,st.manyouno as manyouno) from Stssoldtickettab st,Esbscenicareatab esb,Edmtickettypetab edm,Stssoldticketsubtab sk ");
		sql.append(" where st.iscenicid=esb.iscenicid and edm.itickettypeid=st.itickettypeid ");
		if(manyouno!=null&&!manyouno.equals("")){
			sql.append(" and st.manyouno='"+manyouno+"' ");
		}else{
			sql.append(" and st.itickettypeid="+itickettypeid);
		}
		sql.append("  and st.id.isalesvoucherid=sk.id.isalesvoucherid and st.id.isalesvoucherdetailsid=sk.id.isalesvoucherdetailsid and sk.isvalid=1 and st.id.iticketstationid=sk.id.iticketstationid  and st.id.szsoldticketid=sk.id.szsoldticketid");
		sql.append(" order by st.dtmakedate desc");
		ps=this.findPage(sql.toString(), pageSize,page, url);
		List list = ps.getItems();

		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(map.get("szsoldticketid")!=null&&map.get("isalesvoucherid")!=null&&map.get("isalesvoucherdetailsid")!=null&&map.get("iticketstationid")!=null){
					Long szsoldticketid=Long.parseLong(map.get("szsoldticketid").toString());
					Long isalesvoucherid=Long.parseLong(map.get("isalesvoucherid").toString());
					Long isalesvoucherdetailsid=Long.parseLong(map.get("isalesvoucherdetailsid").toString());
					Long iticketstationid=Long.parseLong(map.get("iticketstationid").toString());

					String hsql="select distinct new map(edm.sztickettypename as sztickettypename,ed.szgardengatename as szgardengatename,sk.ipasstimes as ipasstimes,sk.ipassedtimes as ipassedtimes,sk.isvalid as isvalid) from Stssoldticketsubtab sk,Edmtickettypetab edm,Esbgardengatetab ed where edm.itickettypeid=sk.iztickettypeid and  sk.id.szsoldticketid="+szsoldticketid+" and sk.id.isalesvoucherdetailsid="+isalesvoucherdetailsid+" and sk.id.isalesvoucherid="+isalesvoucherid+" and sk.id.iticketstationid="+iticketstationid+" and sk.igardengateid=ed.id.igardengateid";
					List lst=this.find(hsql);

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
	 * Describe:年卡延期保存
	 * @see com.ectrip.system.salesmanager.service.iservice.IReserveDateService#insertupReserve(java.lang.String[], java.lang.String)
	 * @param vouerids
	 * @param stdate
	 * @author lijingrui
	 * Date:2012-12-27
	 */
	public void insertupReserve(String [] vouerids,String stdate,Syslog syslog){
		if(vouerids!=null&&vouerids.length>0){
			for(int x=0;x<vouerids.length;x++){
				String[] details=vouerids[x].split("&");
				Long isalesvoucherid=Long.parseLong(details[0]);
				Long iticketstationid=Long.parseLong(details[1]);
				Long isalesvoucherdetailsid	=Long.parseLong(details[2]);
				Long szsoldticketid=Long.parseLong(details[3]);

				String sql=" from Stssalesvoucherdetailstab st where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid+" and id.isalesvoucherdetailsid="+isalesvoucherdetailsid;

				//销售凭证明细
				List voucherList=this.find(sql);
				if(voucherList!=null&&voucherList.size()>0){
					for(int i=0;i<voucherList.size();i++){
						Stssalesvoucherdetailstab stssalevoucher=(Stssalesvoucherdetailstab)voucherList.get(i);
						String dtenddate=stssalevoucher.getDtenddate();
						if(stdate.compareTo(dtenddate)>0){
							stssalevoucher.setDtenddate(stdate);
							this.update(stssalevoucher);
						}
					}
				}

				String hsql=" from Stssoldtickettab st where st.id.isalesvoucherid="+isalesvoucherid+"  and st.id.isalesvoucherdetailsid="+isalesvoucherdetailsid+" and st.id.iticketstationid="+iticketstationid+" and st.id.szsoldticketid="+szsoldticketid;
				List ticketList=this.find(hsql);
				if(ticketList!=null&&ticketList.size()>0){
					for(int j=0;j<ticketList.size();j++){
						Stssoldtickettab stssoldticket=(Stssoldtickettab)ticketList.get(j);
						String dtenddate=stssoldticket.getDtenddate();
						if(stdate.compareTo(dtenddate)>0){
							stssoldticket.setDtenddate(stdate);
							this.update(stssoldticket);

							String msql="from Stssoldticketsubtab st where st.id.isalesvoucherdetailsid="+stssoldticket.getId().getIsalesvoucherdetailsid()+"  and st.id.isalesvoucherid="+stssoldticket.getId().getIsalesvoucherid()+" and st.id.iticketstationid="+stssoldticket.getId().getIticketstationid()+" and st.id.szsoldticketid="+stssoldticket.getId().getSzsoldticketid();
							List ticketsubList=this.find(msql);
							if(ticketsubList!=null&&ticketsubList.size()>0){
								for(int y=0;y<ticketsubList.size();y++){
									Stssoldticketsubtab ticketsub=(Stssoldticketsubtab)ticketsubList.get(y);
									ticketsub.setDtenddate(stdate);
									this.update(ticketsub);
								}
							}

							syslog.setStlg("0257");
							syslog.setBrief("年卡延期：卡号" + stssoldticket.getSzticketprintno()+" 延期日期"+stdate);
							syslog.setNote("年卡延期成功：卡号" + stssoldticket.getSzticketprintno()+" 延期日期"+stdate);
							syslog.setLogdatetime(Tools.getDayTimes());
							Long logid = getMaxPk("logid", "Syslog");
							syslog.setLogid(logid + 1);
							this.save(syslog);
						}

					}
				}

			}
		}

	}

	/**
	 *
	 * Describe:
	 * @author:chenxinhao
	 * @param myzj 证件号码
	 * @param name1 姓名
	 * @param manyouno 卡号
	 * @param szticketprintno 票号
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-1-6
	 */
	public PaginationSupport showCradInfo(String myzj,String name1,String manyouno,String szticketprintno,int page,int pageSize,String url){
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(s.id.szsoldticketid as szsoldticketid,s.id.isalesvoucherdetailsid as isalesvoucherdetailsid,s.id.isalesvoucherid as isalesvoucherid,s.id.iticketstationid as iticketstationid,s.itickettypeid as itickettypeid,s.iscenicid as iscenicid,s.dtstartdate as dtstartdate,s.dtenddate as dtenddate,s.manyouno as manyouno,s.myzj as myzj,s.name1 as name1,s.szticketprintno as szticketprintno,t.sztickettypename as sztickettypename,p.szscenicname as szscenicname) from Stssoldtickettab s,Edmtickettypetab t,Esbscenicareatab p");
		hsql.append(" where s.iscenicid=p.iscenicid and s.itickettypeid=t.itickettypeid and dtenddate>='"+Tools.getDays()+"' ");
		if(myzj!=null && !"".equals(myzj)){
			hsql.append(" and upper(s.myzj)='"+myzj.toUpperCase()+"' ");
		}
		if(name1!=null && !name1.equals("")){
			hsql.append(" and s.name1 like '%"+name1+"%' ");
		}
		if(manyouno!=null && !"".equals(manyouno)){
			hsql.append(" and upper(s.manyouno)='"+manyouno+"' ");
		}
		if(szticketprintno!=null && !"".equals(szticketprintno)){
			hsql.append(" and upper(s.szticketprintno)='"+szticketprintno+"' ");
		}
		System.out.println(hsql.toString());
		return this.findPage(hsql.toString(), pageSize, page, url);
	}

	public void editIDCard(Stssoldtickettab saleinfo,Syslog syslog){
		Stssoldtickettab saleinfo2 = (Stssoldtickettab) this.get(Stssoldtickettab.class, new StssoldtickettabId(saleinfo.getId().getSzsoldticketid(), saleinfo.getId().getIsalesvoucherdetailsid(), saleinfo.getId().getIsalesvoucherid(), saleinfo.getId().getIticketstationid()));

		syslog.setBrief("信息修改：原证件号码："+saleinfo2.getMyzj()+",新证件号码："+saleinfo.getMyzj()+";原姓名："+saleinfo2.getName1()+",新姓名："+saleinfo.getName1());
		syslog.setNote("信息修改：原证件号码："+saleinfo2.getMyzj()+",新证件号码："+saleinfo.getMyzj()+";原姓名："+saleinfo2.getName1()+",新姓名："+saleinfo.getName1());

		saleinfo2.setName1(saleinfo.getName1());
		saleinfo2.setMyzj(saleinfo.getMyzj());
		this.update(saleinfo2);

		syslog.setStlg("0256");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
}

