package com.ectrip.ec.report.system.datereport.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.sales.Defindsbalance;
import com.ectrip.ec.model.report.sales.Defindsbalancelistab;
import com.ectrip.ec.model.report.sales.Definedwarrants;
import com.ectrip.ec.report.system.datereport.dao.idao.IDefinedWarantDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.salesmanager.Ospbankpayeesettab;

public class DefinedWarantDAO extends GenericDao implements IDefinedWarantDAO{

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
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(dw.seq as seq,dw.empid as empid,dw.payeer as payeer,dw.passmoney as passmoney,dw.dtwantdate as dtwantdate,dw.szprintwantno as szprintwantno,dw.szwarentname as szwarentname,dw.szbankaccount as szbankaccount,dw.szmemo as szmemo,esf.szemployeename as szemployeename,esb.szemployeename as szpayename,dw.dtmakedate as dtmakedate,sys1.pmva as pmva) from Definedwarrants dw,Esfemployeetab esf,Esfemployeetab esb,Sysparv5 sys1 where dw.empid=esf.iemployeeid and dw.payeer=esb.iemployeeid and sys1.id.pmky='ZFFS' and sys1.id.pmcd=dw.dtstrog3");
		if(stdate!=null&&!stdate.equals("")){
			sql.append(" and dw.dtwantdate='"+stdate+"'");
		}
		if(empid!=null&&!empid.equals("")){
			sql.append(" and dw.empid="+empid);
		}
		if(icompanyinfoid!=null&&!icompanyinfoid.equals("")){
			sql.append(" and esf.icompanyinfoid="+icompanyinfoid);
		}
		sql.append(" order by dw.dtwantdate desc ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
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
		Double money=0D;
		Double mont=0D;
		String sql="";
		String hsql="";
		
		String msql="from Definedwarrants ds where ds.empid="+empid+"  and ds.dtstrog3='"+zffs+"' order by ds.dtmakedate desc";
		List warntList=this.find(msql);
		if(warntList!=null&&warntList.size()>0){
			Definedwarrants warant=(Definedwarrants) warntList.get(0);
			if(warant.getDtwantdate().compareTo(stdate) <= 0){
				//�۳����
				sql="select sum(sts.iacceptmoney-sts.igivechange) as mont from Stssalesvouchertab sts where sts.bysalesvouchertype!='02' and sts.bispayee=0 and substr(sts.dtmakedate,0,10)>='"+warant.getDtwantdate()+"' and substr(sts.dtmakedate,0,10)<='"+stdate+"' and sts.ihandler="+empid+" and sts.id.isalesvoucherid in  "
					+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+zffs+"' and substr(st.dtmakedate,0,10)>='"+warant.getDtwantdate()+"' and substr(st.dtmakedate,0,10)<='"+stdate+"') ";
				
				//��Ʊ���
				hsql="select sum(sts.iacceptmoney-sts.mhandcharge) as mont from Stssalesvouchertab sts where sts.bysalesvouchertype='02' and sts.bispayee=0 and substr(sts.dtmakedate,0,10)>='"+warant.getDtwantdate()+"' and substr(sts.dtmakedate,0,10)<='"+stdate+"' and sts.ihandler="+empid+" and sts.id.isalesvoucherid in  "
					+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+zffs+"' and substr(st.dtmakedate,0,10)>='"+warant.getDtwantdate()+"' and substr(st.dtmakedate,0,10)<='"+stdate+"') ";

			}else{
				return 0D;
			}
			
		}else{
			//�۳����
			sql="select sum(sts.iacceptmoney-sts.igivechange) as mont from Stssalesvouchertab sts where sts.bysalesvouchertype!='02' and sts.bispayee=0 and substr(sts.dtmakedate,0,10)<='"+stdate+"' and sts.ihandler="+empid+" and sts.id.isalesvoucherid in  "
				+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+zffs+"' and substr(st.dtmakedate,0,10)<='"+stdate+"') ";
			
			//��Ʊ���
			hsql="select sum(sts.iacceptmoney-sts.mhandcharge) as mont from Stssalesvouchertab sts where sts.bysalesvouchertype='02' and sts.bispayee=0 and substr(sts.dtmakedate,0,10)<='"+stdate+"' and sts.ihandler="+empid+" and sts.id.isalesvoucherid in  "
				+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+zffs+"' and substr(st.dtmakedate,0,10)<='"+stdate+"') ";

		}
		
		
//		//�۳����
//		sql="select sum(sts.iacceptmoney-sts.igivechange) as mont from Stssalesvouchertab sts where sts.bysalesvouchertype!='02' and substr(sts.dtmakedate,0,10)='"+stdate+"' and sts.ihandler="+empid+" and sts.id.isalesvoucherid in  "
//				+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+zffs+"' and substr(st.dtmakedate,0,10)='"+stdate+"') ";
//		
//		//��Ʊ���
//		hsql="select sum(sts.iacceptmoney-sts.mhandcharge) as mont from Stssalesvouchertab sts where sts.bysalesvouchertype='02' and substr(sts.dtmakedate,0,10)='"+stdate+"' and sts.ihandler="+empid+" and sts.id.isalesvoucherid in  "
//		+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+zffs+"' and substr(st.dtmakedate,0,10)='"+stdate+"') ";

		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			if(lst.get(0)==null){
				money=0D;
			}else{
				money=(Double)lst.get(0);
			}
		}
		
		List list=this.find(hsql);
		if(list!=null&&list.size()>0){
			if(list.get(0)==null){
				mont=0D;
			}else{
				mont=(Double) list.get(0);
			}
		}
		
		Double keymoney=money-mont;
		
		return keymoney;
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
		String sql="select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys,Ospbankpayeesettab osp where osp.szbankname=sys.id.pmcd and sys.id.pmky='BANK' ";;
		return this.find(sql);
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
		String sql=" from Ospbankpayeesettab where szbankname='"+bankname+"' and byisuse=1 ";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			Ospbankpayeesettab osp=(Ospbankpayeesettab)lst.get(0);
			return osp;
		}else{
			return null;
		}
		
	}

	/**
	 * *
	 * Describe:��ѯ���ɿ���������ҵ�µ�����Ա��
	 * @see com.ectrip.system.permitenter.dao.idao.IDefinedWarantDAO#getListemployee(java.lang.Long)
	 * @param iemployeeid
	 * @return
	 * @author lijingrui
	 * Date:2012-11-30
	 */
	public List getListemployee(Long iemployeeid){
//		Esfemployeetab esf=(Esfemployeetab)this.get(Esfemployeetab.class, iemployeeid);  //esf.icompanyinfoid="+esf.getIcompanyinfoid()+" and
		String sql="select new map(esf.iemployeeid as iemployeeid,esf.empid as empid,esf.szemployeename as szemployeename) from Esfemployeetab esf,Esfemployeepoststab esp,Esppoststab es where esf.iemployeeid=esp.iemployeeid and esp.ipostsid=es.ipostsid and es.szpostscode='SK' ";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return lst;
		}else{  
			String msql="select new map(esf.iemployeeid as iemployeeid,esf.empid as empid,esf.szemployeename as szemployeename) from Esfemployeetab esf where  esf.iemployeeid!="+iemployeeid;
			return this.find(msql);
		}
		
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
		try {
			String startdate="";
			String vsql="";
			String msql="from Definedwarrants ds where ds.empid="+definedwants.getEmpid()+"  and ds.dtstrog3='"+definedwants.getDtstrog3()+"' order by ds.dtmakedate desc";
			List warntList=this.find(msql);
			if(warntList!=null&&warntList.size()>0){
				Definedwarrants warant=(Definedwarrants) warntList.get(0);
				startdate=warant.getDtwantdate();
				if(startdate.compareTo(definedwants.getDtwantdate()) <= 0){
					vsql=" from Stssalesvouchertab sts where substr(sts.dtmakedate,0,10)>='"+startdate+"' and substr(sts.dtmakedate,0,10)<='"+definedwants.getDtwantdate()+"' and sts.ihandler="+definedwants.getEmpid()+" and sts.id.isalesvoucherid in  "
						+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+definedwants.getDtstrog3()+"' and substr(st.dtmakedate,0,10)>='"+startdate+"' and substr(st.dtmakedate,0,10)<='"+definedwants.getDtwantdate()+"') ";
				}
				
			}else{
				vsql=" from Stssalesvouchertab sts where substr(sts.dtmakedate,0,10)<='"+definedwants.getDtwantdate()+"' and sts.ihandler="+definedwants.getEmpid()+" and sts.id.isalesvoucherid in  "
					+"(select distinct st.id.isalesvoucherid from Stssalessettlementtab  st where st.isettlementid='"+definedwants.getDtstrog3()+"' and substr(st.dtmakedate,0,10)<='"+definedwants.getDtwantdate()+"') ";
			}
			List stsslist=this.find(vsql);
			if(stsslist!=null&&stsslist.size()>0){
				for(int x=0;x<stsslist.size();x++){
					Stssalesvouchertab voucher=(Stssalesvouchertab)stsslist.get(x);
					voucher.setBispayee(1L);
					this.update(voucher);
				}
			}
			
			
			Long seq=this.getSequenceId("definedwarrants_sequence");
			definedwants.setSeq(seq);
			definedwants.setDtints2(0L);
			definedwants.setDtstrog1(Tools.getDayTimes());
			definedwants.setDtmakedate(Tools.getDayTimes());
			
			String sql="select max(im.szprintwantno) from Definedwarrants im where im.szprintwantno like '%"+Tools.getDay()+"%' ";
			List lst=this.find(sql);
			StringBuffer stockcode=new StringBuffer();

			String maxidst=(String) lst.get(0);
			if(maxidst==null||"".equals(maxidst)){
				stockcode.append(Tools.getDay());
				stockcode.append("000001");
			}else{
				Long md=new Long(maxidst)+1L;
				stockcode.append(md.toString());
			}
			definedwants.setSzprintwantno(stockcode.toString());
			
			this.save(definedwants);
			
			syslog.setStlg("0248");
			Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, definedwants.getEmpid());
			syslog.setBrief("��ƱԱ�ɿ" + employee.getSzemployeename());
			syslog.setNote("�����ƱԱ�ɿ" + employee.getSzemployeename()+" �ɿ��"+definedwants.getPassmoney());
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		String sql="select new map(dw.seq as seq,dw.empid as empid,dw.payeer as payeer,dw.passmoney as passmoney,dw.dtwantdate as dtwantdate,dw.szprintwantno as szprintwantno,dw.szwarentname as szwarentname,dw.szbankaccount as szbankaccount,dw.szmemo as szmemo,esf.szemployeename as szemployeename,esb.szemployeename as szpayename,emp.szemployeename as szname,dw.dtints1 as dtints1,dw.dtints2 as dtints2,dw.dtstrog1 as dtstrog1,dw.dtstrog2 as dtstrog2,sys1.pmva as dtstrog3) from Definedwarrants dw,Esfemployeetab esf,Esfemployeetab esb,Esfemployeetab emp,Sysparv5 sys1 where dw.empid=esf.iemployeeid and dw.payeer=esb.iemployeeid and emp.iemployeeid=dw.dtints1 and sys1.id.pmky='ZFFS' and sys1.id.pmcd=dw.dtstrog3 and dw.seq="+seq;
		List lst=this.find(sql);
		if (lst == null || lst.size() < 1) {
			return null;
		} else {
			Definedwarrants ts=new Definedwarrants();
			BeanUtils.populate(ts, (Map) lst.get(0));
			if(ts.getSzwarentname()!=null&&!ts.getSzwarentname().equals("")){
				String hsql=" from Sysparv5 sys1 where sys1.id.pmky='BANK' and sys1.id.pmcd='"+ts.getSzwarentname()+"' ";
				List syslist=this.find(hsql);
				if(syslist!=null&&syslist.size()>0){
					Sysparv5 syspar=(Sysparv5) syslist.get(0);
					ts.setSzwarentname(syspar.getPmva());
				}
			}
			
			return ts;
		}
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
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct es.szemployeename,subStr(de.dtwantdate,1,7) from Definedwarrants de,Esfemployeetab es where de.empid = es.iemployeeid and de.dtints2 = 0 ");
		if(empid!=null&&!empid.equals("")){
			sql.append(" and de.empid in ("+empid+")");
		}
		if(date!=null&&!date.equals("")){
			sql.append(" and subStr(de.dtwantdate,1,7) < '"+date+"'");
		}
		return this.find(sql.toString());
	}
	
	/**
	 * *
	 * Describe:��ʾĳ�µ�δ����Ľɿ���Ϣ
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
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(dw.seq as seq,dw.empid as empid,dw.payeer as payeer,dw.passmoney as passmoney,dw.dtwantdate as dtwantdate,dw.szprintwantno as szprintwantno,dw.szwarentname as szwarentname,dw.szbankaccount as szbankaccount,dw.szmemo as szmemo,esf.szemployeename as szemployeename,esb.szemployeename as szpayename,syspar.pmva as dtstrog3) from Definedwarrants dw,Esfemployeetab esf,Esfemployeetab esb,Sysparv5 syspar where dw.empid=esf.iemployeeid and dw.payeer=esb.iemployeeid and syspar.id.pmky='ZFFS' and syspar.id.pmcd=dw.dtstrog3 and dw.dtints2=0 ");
		if(stdate!=null&&!stdate.equals("")){
			sql.append(" and subStr(dw.dtwantdate,1,7)='"+stdate+"'");
		}
		if(empid!=null&&!empid.equals("")){
			sql.append(" and dw.empid="+empid);
		}
		if(icompanyinfoid!=null&&!icompanyinfoid.equals("")){
			sql.append(" and esf.icompanyinfoid="+icompanyinfoid);
		}
		sql.append(" order by dw.dtwantdate desc ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
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
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct es.szemployeename,subStr(de.dtwantdate,1,7) from Definedwarrants de,Esfemployeetab es where de.empid = es.iemployeeid and de.dtints2 = 0");
		if(empid!=null&&!empid.equals("")){
			sql.append(" and de.empid in ("+empid+")");
		}
		if(date!=null&&!date.equals("")){
			sql.append(" and subStr(de.dtwantdate,1,7) = '"+date+"'");
		}
		return this.find(sql.toString());
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
		String sql="select sum(dw.passmoney) as money from Definedwarrants dw where subStr(dw.dtwantdate,1,7) = '"+date+"' and dw.empid="+empid+" and dw.dtints2=0";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Double mont=(Double) list.get(0);
			if(mont==null){
				return new Double(0);
			}
			return mont;
		}else{
			return new Double(0);
		}
		
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
		Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, Long.parseLong(empid));
		String sql="select sum(case when sale.notea != '02' and sale.notea != '07' then sale.mont else -sale.mont end) + sum(sale.dub) as mont from Rsalecounttab sale where sale.mont > 0 and sale.titype = '02' and subStr(sale.times,1,7) = '"+date+"' and sale.empid ='"+employee.getEmpid()+"'";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Double mont=(Double) list.get(0);
			if(mont==null){
				return new Double(0);
			}
			return mont;
		}else{
			return new Double(0);
		}
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
		StringBuffer sql = new StringBuffer();
		sql.append(" from Definedwarrants de where 1=1 ");
		if(empid!=null&&!empid.equals("")){
			sql.append(" and de.empid="+empid);
		}
		if(date!=null&&!date.equals("")){
			sql.append(" and subStr(de.dtwantdate,1,7) = '"+date+"'");
		}
		List list=this.find(sql.toString());
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
		
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
		Long maxid=this.getMaxPk("ibalanceid", "Defindsbalance");
		balance.setIbalanceid(maxid+1);
		balance.setDtmakedate(Tools.getDayTimes());
		balance.setDtbalancedate(Tools.getDayTimes());
		this.save(balance);
		
		String sql=" from Definedwarrants dw where dw.dtints2=0 and dw.dtwantdate>='"+balance.getBaltype1()+"' and dw.dtwantdate<='"+balance.getBaltype2()+"' and dw.empid="+balance.getEmpid();
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Definedwarrants warant=(Definedwarrants) list.get(i);
				warant.setDtints2(1L);
				this.update(warant);
				
				Defindsbalancelistab balancelist=new Defindsbalancelistab();
				Long seq=this.getMaxPk("bid", "Defindsbalancelistab");
				balancelist.setBid(seq+1);
				balancelist.setIbalanceid(balance.getIbalanceid());
				balancelist.setSeq(warant.getSeq());
				balancelist.setSzprintno(warant.getSzprintwantno());
				balancelist.setDtmakedate(Tools.getDayTimes());
				this.save(balancelist);
				
			}
		}
		
		Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, balance.getEmpid());
		syslog.setStlg("0249");
		syslog.setBrief("��ƱԱ�ɿ����ȷ�ϣ���ƱԱ��" + employee.getSzemployeename());
		syslog.setNote("�����ƱԱ�ɿ����ȷ����Ϣ����ƱԱ" + employee.getSzemployeename()+",�ɿ��ܽ�"+balance.getPassmony());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	
	/**
	 * *
	 * Describe:�鿴�ɿ�����¼
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getListbalance(java.lang.Long, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param empid
	 * @param stdate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-12
	 */
	public PaginationSupport getListbalance(Long empid,String stdate,int pageSize,int startIndex, String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(fs.ibalanceid as ibalanceid,fs.empid as empid,fs.ihadder as ihadder,fs.dtbalancedate as dtbalancedate,fs.passmony as passmony,fs.xsmont as xsmont,fs.baltype1 as baltype1,fs.baltype2 as baltype2,esf.szemployeename as szemployeename,emp.szemployeename as szempname) from Defindsbalance fs,Esfemployeetab esf,Esfemployeetab emp where fs.empid=esf.iemployeeid and fs.ihadder=emp.iemployeeid ");
		if(empid!=null&&!empid.equals("")&&empid!=10000){
			sql.append(" and fs.empid="+empid);
		}
		if(stdate!=null&&!stdate.equals("")){
			sql.append(" and fs.baltype1<= '"+stdate+"' and fs.baltype2>= '"+stdate+"'");
		}
		return  this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:�鿴�ɿ������ϸ��Ϣ
	 * @see com.ectrip.report.system.datereport.service.iservice.IDefinedWarantService#getViewbalanceListab(java.lang.Long, java.lang.String, java.lang.Long, int, int, java.lang.String)
	 * @param ibalanceid
	 * @param stdate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-12
	 */
	public PaginationSupport getViewbalanceListab(Long ibalanceid,String startdate,String enddate,int pageSize,int startIndex, String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(fs.ibalanceid as ibalanceid,fs.bid as bid,dw.seq as seq,dw.empid as empid,dw.payeer as payeer,dw.passmoney as passmoney,dw.dtwantdate as dtwantdate,dw.szprintwantno as szprintwantno,dw.szwarentname as szwarentname,dw.szbankaccount as szbankaccount,dw.szmemo as szmemo,esf.szemployeename as szemployeename,esb.szemployeename as szpayename) from Defindsbalancelistab fs,Definedwarrants dw,Esfemployeetab esf,Esfemployeetab esb where dw.empid=esf.iemployeeid and dw.payeer=esb.iemployeeid and dw.seq=fs.seq and fs.ibalanceid="+ibalanceid);
		if(startdate!=null&&!startdate.equals("")&&enddate!=null&&!enddate.equals("")){
			sql.append(" and dw.dtwantdate>='"+startdate+"' and dw.dtwantdate<='"+enddate+"' ");
		}
		return this.findPage(sql.toString(), pageSize, startIndex, url);
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
		StringBuffer sql = new StringBuffer();
		sql.append(" from Definedwarrants de where 1=1 ");
		if(empid!=null&&!empid.equals("")){
			sql.append(" and de.empid="+empid);
		}
		if(startdate!=null&&!startdate.equals("")&&enddate!=null&&!enddate.equals("")){
			sql.append(" and de.dtwantdate >= '"+startdate+"' and de.dtwantdate <= '"+enddate+"' ");
		}
		List list=this.find(sql.toString());
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
		
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
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct es.szemployeename,de.dtwantdate from Definedwarrants de,Esfemployeetab es where de.empid = es.iemployeeid and de.dtints2 = 0 ");
		if(empid!=null&&!empid.equals("")){
			sql.append(" and de.empid in ("+empid+")");
		}
		if(startdate!=null&&!startdate.equals("")&&enddate!=null&&!enddate.equals("")){
			sql.append(" and de.dtwantdate < '"+startdate+"'");
		}
		return this.find(sql.toString());
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
		String sql="select sum(dw.passmoney) as money from Definedwarrants dw where dw.dtwantdate >= '"+startdate+"' and dw.dtwantdate <= '"+enddate+"' and dw.empid="+empid+" and dw.dtints2=0";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Double mont=(Double) list.get(0);
			if(mont==null){
				return new Double(0);
			}
			return mont;
		}else{
			return new Double(0);
		}
		
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
		Esfemployeetab employee=(Esfemployeetab) this.get(Esfemployeetab.class, Long.parseLong(empid));
		String sql="select sum(case when sale.notea != '02' and sale.notea != '07' then sale.mont else -sale.mont end) + sum(sale.dub) as mont from Rsalecounttab sale where sale.titype = '01' and sale.times>= '"+startdate+"' and sale.times <= '"+enddate+"' and sale.empid ='"+employee.getEmpid()+"'";
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			Double mont=(Double) list.get(0);
			if(mont==null){
				return new Double(0);
			}
			return mont;
		}else{
			return new Double(0);
		}
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

		StringBuffer sql=new StringBuffer();
		sql.append("select new map(dw.seq as seq,dw.empid as empid,dw.payeer as payeer,dw.passmoney as passmoney,dw.dtwantdate as dtwantdate,dw.szprintwantno as szprintwantno,dw.szwarentname as szwarentname,dw.szbankaccount as szbankaccount,dw.szmemo as szmemo,esf.szemployeename as szemployeename,esb.szemployeename as szpayename,syspar.pmva as dtstrog3) from Definedwarrants dw,Esfemployeetab esf,Esfemployeetab esb,Sysparv5 syspar where dw.empid=esf.iemployeeid and dw.payeer=esb.iemployeeid and syspar.id.pmky='ZFFS' and syspar.id.pmcd=dw.dtstrog3 and dw.dtints2=0 ");
		if(startdate!=null&&!startdate.equals("")&&enddate!=null&&!enddate.equals("")){
			sql.append(" and dw.dtwantdate >= '"+startdate+"' and dw.dtwantdate <= '"+enddate+"' ");
		}
		if(empid!=null&&!empid.equals("")){
			sql.append(" and dw.empid="+empid);
		}
		if(icompanyinfoid!=null&&!icompanyinfoid.equals("")){
			sql.append(" and esf.icompanyinfoid="+icompanyinfoid);
		}
		sql.append(" order by dw.dtwantdate desc ");
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:��ѯĳ���ڶ��Ƿ����
	 * @see com.ectrip.report.system.datereport.dao.idao.IDefinedWarantDAO#defindantcheckin(java.lang.String, java.lang.String, java.lang.String)
	 * @param empid
	 * @param startdate
	 * @param enddate
	 * @return
	 * @author lijingrui
	 * Date:2013-5-23
	 */
	public List checkFindbalance(String empid,String startdate,String enddate){

		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct es.szemployeename,de.dtwantdate from Definedwarrants de,Esfemployeetab es where de.empid = es.iemployeeid and de.dtints2 = 0");
		if(empid!=null&&!empid.equals("")){
			sql.append(" and de.empid in ("+empid+")");
		}
		if(startdate!=null&&!startdate.equals("")&&enddate!=null&&!enddate.equals("")){
			sql.append(" and de.dtwantdate >= '"+startdate+"' and de.dtwantdate <= '"+enddate+"' ");
		}
		List lst=this.find(sql.toString());
		if(lst!=null&&lst.size()>0){
			return lst;
		}else{
			return null;
		}
	
	}
	
}

