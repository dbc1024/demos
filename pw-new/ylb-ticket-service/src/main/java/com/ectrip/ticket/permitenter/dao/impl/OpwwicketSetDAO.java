package com.ectrip.ticket.permitenter.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.permitenter.dao.IOpwwicketSetDAO;

@Repository
public class OpwwicketSetDAO extends GenericDao implements IOpwwicketSetDAO{
	
	@Autowired
	private SysService sysService;
	/**
	 * *
	 * Describe:显示所有的检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getlistwicketset(int, int, java.lang.String)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public PaginationSupport getlistwicketset(String scenics, int pageSize, int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		
//		sql.append("select new map("
//				+ " op.iwicketsetid as iwicketsetid,"
//				+ " es.szgardengatename as szgardengatename,"
//				+ " ed.sztickettypename as sztickettypename,"
//				+ " edm.sztickettypename as szticketname,"
//				+ " op.ilimittotaltimes as ilimittotaltimes, op.byisuse as byisuse, op.issettimeticket as issettimeticket, op.istimeticket as istimeticket,"
//				+ " sys2.pmva as byconsumemode,"
//				+ " sys3.pmva as bywicketctrltype,"
//				+ " sys4.pmva as bywicketconsumetype,"
//				+ " sys5.pmva as byregfingerprinttype,"
//				+ " sys6.pmva as byticketnodeal,"
//				+ " sys7.pmva as msingleconsume"
//				+ " ) "
//				+ " from Opwwicketsettab op, Edmtickettypetab ed, Edmtickettypetab edm, Esbgardengatetab es,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4,Sysparv5 sys5,Sysparv5 sys6,Sysparv5 sys7"
//				+ " where op.itickettypeid=ed.itickettypeid and op.izticktypeid=edm.itickettypeid and op.igardengateid=es.id.igardengateid "
//				+ " and sys2.id.pmcd=op.byconsumemode and sys2.id.pmky='XFSH' "
//				+ " and sys3.id.pmcd=op.bywicketctrltype and sys3.id.pmky='JPSH' "
//				+ " and sys4.id.pmcd=op.bywicketconsumetype and sys4.id.pmky='KPSH' "
//				+ " and sys5.id.pmcd=op.byregfingerprinttype and sys5.id.pmky='SFWP' "
//				+ " and sys6.id.pmcd=op.byticketnodeal and sys6.id.pmky='PHSP' "
//				+ " and sys7.id.pmcd=To_char(op.msingleconsume) and sys7.id.pmky='JPQZ' "
//				+ " and op.iscentcid in ("+ scenics +")");
		
		sql.append("select new map("
				+ " op.iwicketsetid as iwicketsetid,"
				+ " es.szgardengatename as szgardengatename,"
				+ " ed.sztickettypename as sztickettypename,"
				+ " edm.sztickettypename as szticketname,"
				+ " op.ilimittotaltimes as ilimittotaltimes, op.byisuse as byisuse, op.issettimeticket as issettimeticket, op.istimeticket as istimeticket,"
				+ " op.byconsumemode as byconsumemode, op.bywicketctrltype as bywicketctrltype, op.bywicketconsumetype as bywicketconsumetype, op.byregfingerprinttype as byregfingerprinttype, op.byticketnodeal as byticketnodeal, op.msingleconsume as msingleconsume"
				+ " ) "
				+ " from Opwwicketsettab op, Edmtickettypetab ed, Edmtickettypetab edm, Esbgardengatetab es"
				+ " where op.itickettypeid=ed.itickettypeid and op.izticktypeid=edm.itickettypeid and op.igardengateid=es.id.igardengateid ");
		
		if(scenics!=null && !scenics.equals("")){
			sql.append(" and op.iscentcid in ("+scenics+") ");
		}
		
		sql.append(" order by es.szgardengatename,ed.sztickettypename,edm.sztickettypename");
		
//		+ " sys2.pmva as byconsumemode,"
//		+ " sys3.pmva as bywicketctrltype,"
//		+ " sys4.pmva as bywicketconsumetype,"
//		+ " sys5.pmva as byregfingerprinttype,"
//		+ " sys6.pmva as byticketnodeal,"
//		+ " sys7.pmva as msingleconsume"
		

		
		PaginationSupport ps = this.findPage(sql.toString(), pageSize, startIndex, url);
		List<Map> items = ps.getItems();
		if(items!=null && items.size()>0) {
			StringBuilder byconsumemodeS = new StringBuilder();
        	StringBuilder bywicketctrltypeS = new StringBuilder();
        	StringBuilder bywicketconsumetypeS = new StringBuilder();
        	StringBuilder byregfingerprinttypeS = new StringBuilder();
        	StringBuilder byticketnodealS = new StringBuilder();
        	StringBuilder msingleconsumeS = new StringBuilder();
			
			
			for (Map item : items) {
				String byconsumemode = item.get("byconsumemode").toString();
				byconsumemodeS.append(byconsumemode+ ",");
				
				String bywicketctrltype = item.get("bywicketctrltype").toString();
				bywicketctrltypeS.append(bywicketctrltype+ ",");
				
				String bywicketconsumetype = item.get("bywicketconsumetype").toString();
				bywicketconsumetypeS.append(bywicketconsumetype+ ",");
				
				String byregfingerprinttype = item.get("byregfingerprinttype").toString();
				byregfingerprinttypeS.append(byregfingerprinttype+ ",");
				
				String byticketnodeal = item.get("byticketnodeal").toString();
				byticketnodealS.append(byticketnodeal+ ",");
				
				String msingleconsume = item.get("msingleconsume").toString();
				msingleconsumeS.append(msingleconsume+ ",");
			}
			byconsumemodeS.deleteCharAt(byconsumemodeS.length() - 1);
			bywicketctrltypeS.deleteCharAt(bywicketctrltypeS.length() - 1);
			bywicketconsumetypeS.deleteCharAt(bywicketconsumetypeS.length() - 1);
			byregfingerprinttypeS.deleteCharAt(byregfingerprinttypeS.length() - 1);
			byticketnodealS.deleteCharAt(byticketnodealS.length() - 1);
			msingleconsumeS.deleteCharAt(msingleconsumeS.length() - 1);
			
			
			//+ " and sys2.id.pmcd=op.byconsumemode and sys2.id.pmky='XFSH' "
			//+ " and sys3.id.pmcd=op.bywicketctrltype and sys3.id.pmky='JPSH' "
			//+ " and sys4.id.pmcd=op.bywicketconsumetype and sys4.id.pmky='KPSH' "
			//+ " and sys5.id.pmcd=op.byregfingerprinttype and sys5.id.pmky='SFWP' "
			//+ " and sys6.id.pmcd=op.byticketnodeal and sys6.id.pmky='PHSP' "
			//+ " and sys7.id.pmcd=To_char(op.msingleconsume) and sys7.id.pmky='JPQZ' "
			
			List<Map> byconsumemode_sysparams = sysService.getSysparamsByPmkyAndPmcds("XFSH", byconsumemodeS.toString());
			List<Map> bywicketctrltype_sysparams = sysService.getSysparamsByPmkyAndPmcds("JPSH", bywicketctrltypeS.toString());
			List<Map> bywicketconsumetype_sysparams = sysService.getSysparamsByPmkyAndPmcds("KPSH", bywicketconsumetypeS.toString());
			List<Map> byregfingerprinttype_sysparams = sysService.getSysparamsByPmkyAndPmcds("SFWP", byregfingerprinttypeS.toString());
			List<Map> byticketnodeal_sysparams = sysService.getSysparamsByPmkyAndPmcds("PHSP", byticketnodealS.toString());
			List<Map> msingleconsume_sysparams = sysService.getSysparamsByPmkyAndPmcds("JPQZ", msingleconsumeS.toString());
			
			
			for (Map item : items) {
				String byconsumemode = item.get("byconsumemode").toString();
				for (Map sysparam : byconsumemode_sysparams) {
					String pmcd = sysparam.get("pmcd").toString();
					if(byconsumemode.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
        				item.put("byconsumemode", pmva);
						
					}
				}
				
				String bywicketctrltype = item.get("bywicketctrltype").toString();
				for (Map sysparam : bywicketctrltype_sysparams) {
					String pmcd = sysparam.get("pmcd").toString();
					if(bywicketctrltype.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
        				item.put("bywicketctrltype", pmva);
						
					}
				}
				
				String bywicketconsumetype = item.get("bywicketconsumetype").toString();
				for (Map sysparam : bywicketconsumetype_sysparams) {
					String pmcd = sysparam.get("pmcd").toString();
					if(bywicketconsumetype.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
        				item.put("bywicketconsumetype", pmva);
						
					}
				}
				
				String byregfingerprinttype = item.get("byregfingerprinttype").toString();
				for (Map sysparam : byregfingerprinttype_sysparams) {
					String pmcd = sysparam.get("pmcd").toString();
					if(byregfingerprinttype.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
        				item.put("byregfingerprinttype", pmva);
						
					}
				}
				
				String byticketnodeal = item.get("byticketnodeal").toString();
				for (Map sysparam : byticketnodeal_sysparams) {
					String pmcd = sysparam.get("pmcd").toString();
					if(byticketnodeal.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
        				item.put("byticketnodeal", pmva);
						
					}
				}
				
				String msingleconsume = item.get("msingleconsume").toString();
				for (Map sysparam : msingleconsume_sysparams) {
					String pmcd = sysparam.get("pmcd").toString();
					if(msingleconsume.equals(pmcd)) {
						String pmva = sysparam.get("pmva").toString();
        				item.put("msingleconsume", pmva);
						
					}
				}
			}
		}
		
		
		
		
		return ps;
	}
	
	/**
	 * *
	 * Describe:添加检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#insertwicketset(com.ectrip.model.permitenter.Opwwicketsettab, com.ectrip.model.syspar.Syslog)
	 * @param wicketset
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public void insertwicketset(Opwwicketsettab wicketset, Syslog syslog) {
		Long maxid=this.getMaxPk("iwicketsetid", "Opwwicketsettab");
		wicketset.setIwicketsetid(maxid+1);
		System.out.println(wicketset.getIwicketsetid()+"=="+wicketset.getIgardengateid());
		String sql=" from Esbgardengatetab where id.igardengateid="+wicketset.getIgardengateid();
		List list=this.find(sql);
		Esbgardengatetab es=(Esbgardengatetab) list.get(0);
		wicketset.setIscentcid(es.getId().getIscenicid());
		this.save(wicketset);
		if(wicketset.getBywicketconsumetype().equals("03")){
			String sql2 = " from Opwwicketsettab op where op.igardengateid ="+wicketset.getIgardengateid()+" and op.bywicketconsumetype !='03' ";
			List list2 = this.find(sql2);
			if(list2!=null&&!list2.isEmpty()){
				for(int i=0;i<list2.size();i++){
					Opwwicketsettab op = (Opwwicketsettab) list2.get(i);
					op.setBywicketconsumetype(wicketset.getBywicketconsumetype());
					op.setByregfingerprinttype("00");
					this.update(op);
				}
			}
		}
//		syslog.setStlg("0077");
//		syslog.setBrief("检票设置：" + wicketset.getIwicketsetid());
//		syslog.setNote("添加检票设置：" + wicketset.getIwicketsetid());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:修改检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#updatewicketset(com.ectrip.model.permitenter.Opwwicketsettab, com.ectrip.model.syspar.Syslog)
	 * @param wicketset
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public void updatewicketset(Opwwicketsettab wicketset, Syslog syslog) {
		Opwwicketsettab op=(Opwwicketsettab) this.get(Opwwicketsettab.class, wicketset.getIwicketsetid());
		op.setIlimittotaltimes(wicketset.getIlimittotaltimes());
		op.setMlimitconsume(wicketset.getMlimitconsume());
		op.setMsingletimes(wicketset.getMsingletimes());
		op.setMsingleconsume(wicketset.getMsingleconsume());
		op.setByconsumemode(wicketset.getByconsumemode());
		op.setItimeinterval(wicketset.getItimeinterval());
		op.setBywicketctrltype(wicketset.getBywicketctrltype());
		op.setBywicketconsumetype(wicketset.getBywicketconsumetype());
		op.setMsingleconsume(wicketset.getMsingleconsume());
		op.setByregfingerprinttype(wicketset.getByregfingerprinttype());
		op.setByticketnodeal(wicketset.getByticketnodeal());
		op.setByisuse(wicketset.getByisuse());
		op.setIssettimeticket(wicketset.getIssettimeticket());
		op.setIstimeticket(wicketset.getIstimeticket());
		
		this.update(op);
		
		if(wicketset.getBywicketconsumetype().equals("03")){
			List list = showgradeTicket(op.getIgardengateid(),"");
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					Opwwicketsettab op2 = (Opwwicketsettab) list.get(i);
					op2.setBywicketconsumetype(wicketset.getBywicketconsumetype());
					op2.setByregfingerprinttype("00");
					this.update(op2);
				}
			}
		}else{
			List list = showgradeTicket(op.getIgardengateid(),"03");
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					Opwwicketsettab op2 = (Opwwicketsettab) list.get(i);
					op2.setBywicketconsumetype(wicketset.getBywicketconsumetype());
					op2.setByregfingerprinttype("00");
					this.update(op2);
				}
			}
		}
		
//		syslog.setStlg("0078");
//		syslog.setBrief("检票设置：" + op.getIwicketsetid());
//		syslog.setNote("修改检票设置：" + op.getIwicketsetid());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:删除检票设置信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#deletewicketset(com.ectrip.model.permitenter.Opwwicketsettab, com.ectrip.model.syspar.Syslog)
	 * @param wicketset
	 * @param syslog
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public void deletewicketset(Opwwicketsettab wicketset, Syslog syslog) {
		Opwwicketsettab op=(Opwwicketsettab) this.get(Opwwicketsettab.class, wicketset.getIwicketsetid());
		this.delete(op);
		
//		syslog.setStlg("0079");
//		syslog.setBrief("检票设置：" + op.getIwicketsetid());
//		syslog.setNote("删除检票设置：" + op.getIwicketsetid());
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:根据ID查看检票设置
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getviewOpwicketID(java.lang.Long)
	 * @param iwicketsetid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 * @throws Exception 
	 */
	public Opwwicketsettab getviewOpwicketID(Long iwicketsetid) throws Exception {
		
		String sql="select new map("
				+ " op.iwicketsetid as iwicketsetid, op.ilimittotaltimes as ilimittotaltimes,"
				+ " edm.sztickettypename as szname,"
				+ " es.szgardengatename as szgardengatename,"
				+ " ed.sztickettypename as sztickettypename,"
				+ " ep.szscenicname as szscenicname,"
				+ " op.byconsumemode as byconsumemode,op.bywicketctrltype as bywicketctrltype,op.bywicketconsumetype as bywicketconsumetype,op.byregfingerprinttype as byregfingerprinttype,op.byticketnodeal as byticketnodeal,"
				+ " op.byisuse as byisuse,op.mlimitconsume as mlimitconsume,op.msingletimes as msingletimes,op.msingleconsume as msingleconsume,op.itimeinterval as itimeinterval"
				+ " ) "
				+ " from Opwwicketsettab op,Edmtickettypetab ed,Edmtickettypetab edm,Esbgardengatetab es,Esbscenicareatab ep"
				+ " where op.itickettypeid=ed.itickettypeid and op.izticktypeid=edm.itickettypeid and op.igardengateid=es.id.igardengateid and op.iscentcid=ep.iscenicid "
				+ " and op.iwicketsetid="+iwicketsetid;
		
		
//		+ " and sys2.id.pmcd=op.byconsumemode and sys2.id.pmky='XFSH' "
//		+ " and sys3.id.pmcd=op.bywicketctrltype and sys3.id.pmky='JPSH' "
//		+ " and sys4.id.pmcd=op.bywicketconsumetype and sys4.id.pmky='KPSH' "
//		+ " and sys5.id.pmcd=op.byregfingerprinttype and sys5.id.pmky='SFWP' "
//		+ " and sys6.id.pmcd=op.byticketnodeal and sys6.id.pmky='PHSP' "
		
		
		
		
		
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			
			Map opwwicketsettabMap = (Map) list.get(0);
			
			String byconsumemode = opwwicketsettabMap.get("byconsumemode").toString();
			List<Map> byconsumemode_sysparams = sysService.getSysparamsByPmkyAndPmcds("XFSH", byconsumemode);
			if(byconsumemode_sysparams!=null && byconsumemode_sysparams.size()>0) {
        		String pmva = byconsumemode_sysparams.get(0).get("pmva").toString();
        		opwwicketsettabMap.put("byconsumemode", pmva);
        	}
			
			String bywicketctrltype = opwwicketsettabMap.get("bywicketctrltype").toString();
			List<Map> bywicketctrltype_sysparams = sysService.getSysparamsByPmkyAndPmcds("JPSH", bywicketctrltype);
			if(bywicketctrltype_sysparams!=null && bywicketctrltype_sysparams.size()>0) {
        		String pmva = bywicketctrltype_sysparams.get(0).get("pmva").toString();
        		opwwicketsettabMap.put("bywicketctrltype", pmva);
        	}
			
			String bywicketconsumetype = opwwicketsettabMap.get("bywicketconsumetype").toString();
			List<Map> bywicketconsumetype_sysparams = sysService.getSysparamsByPmkyAndPmcds("KPSH", bywicketconsumetype);
			if(bywicketconsumetype_sysparams!=null && bywicketconsumetype_sysparams.size()>0) {
        		String pmva = bywicketconsumetype_sysparams.get(0).get("pmva").toString();
        		opwwicketsettabMap.put("bywicketconsumetype", pmva);
        	}
			
			String byregfingerprinttype = opwwicketsettabMap.get("byregfingerprinttype").toString();
			List<Map> byregfingerprinttype_sysparams = sysService.getSysparamsByPmkyAndPmcds("SFWP", byregfingerprinttype);
			if(byregfingerprinttype_sysparams!=null && byregfingerprinttype_sysparams.size()>0) {
        		String pmva = byregfingerprinttype_sysparams.get(0).get("pmva").toString();
        		opwwicketsettabMap.put("byregfingerprinttype", pmva);
        	}
			
			String byticketnodeal = opwwicketsettabMap.get("byticketnodeal").toString();
			List<Map> byticketnodeal_sysparams = sysService.getSysparamsByPmkyAndPmcds("PHSP", byticketnodeal);
			if(byticketnodeal_sysparams!=null && byticketnodeal_sysparams.size()>0) {
        		String pmva = byticketnodeal_sysparams.get(0).get("pmva").toString();
        		opwwicketsettabMap.put("byticketnodeal", pmva);
        	}
			
			
			Opwwicketsettab ts=new Opwwicketsettab();
			BeanUtils.populate(ts, opwwicketsettabMap);
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:显示圆门信息
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getgardengatelist()
	 * @return
	 * @author lijingrui
	 * Date:2011-10-5
	 */
	public List getgardengatelist(String scenics) {
		StringBuffer sql=new StringBuffer();
		sql.append(" from Esbgardengatetab es ");
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" where es.id.iscenicid in ("+scenics+")");
		}
		List list=this.find(sql.toString());
		if (list.size()<1) {
			Esbgardengatetab esbgardengatetab=new Esbgardengatetab();
			EsbgardengatetabId esbgardengatetabId=new EsbgardengatetabId();
			esbgardengatetabId.setIgardengateid(-1L);
			esbgardengatetab.setId(esbgardengatetabId);
			esbgardengatetab.setSzgardengatename("无");
			list.add(esbgardengatetab);
		}
		return list;
	}
	
	/**
	 * 
	 * Describe:显示所属的票类型
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:2011-10-5
	 */
//	public String getedmtickettypelist(Long igardengateid) {
//	//	String sql=" from Edmtickettypetab where iscenicid in (select cm.id.iscenicid from Companyscenic cm where cm.id.icompanyinfoid="+icompanyinfoid+")";
//		StringBuffer json = new StringBuffer();
//		String sql="from Edmtickettypetab where byisuse=1 and iscenicid=(select es.id.iscenicid from Esbgardengatetab es where es.id.igardengateid="+igardengateid+")";
//		List list = this.find(sql);
//		json.append("[");
//		for (int i = 0; i < list.size(); i++) {
//			Edmtickettypetab e = (Edmtickettypetab) list.get(i);
//			json.append("{\"itickettypeid\":\"" + e.getItickettypeid()+ "\",\"sztickettypename\":\""
//					+e.getSztickettypename()+ "\"}");
//			if (i != list.size() - 1) {
//				json.append(",");
//			}
//		}
//		json.append("]");
//		return json.toString();
//	}
	/***
	 * Describe: 根据登录人所属服务商显示所有的票类型
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getedmtickettypelist(java.lang.String)
	 * @param scenic 登录人所属的服务商
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-23
	 */
	public String getedmtickettypelist(String scenic){
		StringBuffer json = new StringBuffer();
		//根据服务商查出所有该服务商的票类型
		String sql="from Edmtickettypetab where byisuse=1 and iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01)";
		if (scenic!=null&&!scenic.equals("")) {
			sql="from Edmtickettypetab where byisuse=1 and iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01 and scenic.iscenicid in ("+scenic+"))";
//			sql+=" and iscenicid="+scenic;
		}
		sql=sql+" order by iscenicid,itickettypeid";
		List list = this.find(sql);
		//生成JSON
		json.append("[");
		if (list==null||list.size()<1) {
			json.append("{\"itickettypeid\":\"" + -1 + "\",\"sztickettypename\":\""
					+"无"+ "\"}");
		}
		else{
			for (int i = 0; i < list.size(); i++) {
				Edmtickettypetab e = (Edmtickettypetab) list.get(i);
				
				Esbscenicareatab esbscen=(Esbscenicareatab)this.get(Esbscenicareatab.class, e.getIscenicid());
				
				json.append("{\"itickettypeid\":\"" + e.getItickettypeid()+ "\",\"sztickettypename\":\""
						+esbscen.getSzscenicname()+"_"+e.getSztickettypename()+ "\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
		}
		json.append("]");
		return json.toString();
	}
	
	/***
	 * Describe: 根据票类型ID获得所有对应的服务商的园门
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getGardengate(java.lang.Long)
	 * @param itickettypeid 票类型ID
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-23
	 */
	public List getGardengate(Long itickettypeid){
		Edmtickettypetab edm=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String sql=null;
		//如果是套票，就查出该套票所有的子票
		if(!edm.getBycategorytype().equals("0010")){
			sql=" from Edmtickettypetab where byisuse=1 and itickettypeid="+itickettypeid;
		}else{
			sql=" from Edmtickettypetab where byisuse=1 and itickettypeid in(select ed.itickettypeid  from Edmticketcomposepricetab ed where ed.id.icrowdkindpriceid in(select icrowdkindpriceid from Edmcrowdkindpricetab where itickettypeid="+itickettypeid+") )";
		}
		List<Edmtickettypetab> list = this.find(sql);
		//根据所有的子票的类型，查出所有的服务商
		String scenic="";
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Edmtickettypetab edmtickettypetab = (Edmtickettypetab) iterator.next();
			scenic+=edmtickettypetab.getIscenicid();
			if (iterator.hasNext()) {
				scenic+=",";
			}
		}
		
		//根据查出的服务商找到他们对应的园门
		String sql2 = "select new map(gate.id.igardengateid as igardengateid, gate.szgardengatename as igardengatename) from Esbgardengatetab gate";
		if(scenic!=null &&! scenic.equals("")){
			sql2 = sql2+ " where gate.id.iscenicid in ("+ scenic+ ")";
		}
		
		List<Map> gateList = this.find(sql2);
		
		return gateList;
	}
	
	/***
	 * Describe:  初始化票类型服务商的所有园门
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getInitGardengate(java.lang.String)
	 * @param scenic 登录人所属的服务商
	 * @return
	 * @author ChaoYuHang
	 * Date:2012-8-23
	 */
	public List getInitGardengate(String scenic){
		//根据登录人所属的服务商获得所有该服务商的所有票类型
		String sql="from Edmtickettypetab where byisuse=1 and iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01) ";
//		if (scenic!=null&&scenic.equals("")) {
//			sql+=" and iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01)";
//		}
		if (scenic!=null&&!scenic.equals("")) {
			sql="from Edmtickettypetab where byisuse=1 and iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01 and scenic.iscenicid in ("+scenic+"))";
		}
		List list = this.find(sql);
		
		if (list==null||list.size()<1) {
			List emptyList=new ArrayList();
			Esbgardengatetab esbgardengatetab=new Esbgardengatetab();
			EsbgardengatetabId esbgardengatetabId=new EsbgardengatetabId();
			esbgardengatetabId.setIgardengateid(-1L);
			esbgardengatetab.setId(esbgardengatetabId);
			esbgardengatetab.setSzgardengatename("无");
			emptyList.add(esbgardengatetab);
			return emptyList;
		}
		else{
			//获取第一条票类型记录
			Edmtickettypetab edmtickettypetab=(Edmtickettypetab) list.get(0);
			Long itickettypeid=edmtickettypetab.getItickettypeid();
			//如果这条记录是套票，查询它的子票
			if(!edmtickettypetab.getBycategorytype().equals("0010")){
				sql=" from Edmtickettypetab where byisuse=1 and itickettypeid="+itickettypeid;
			}else{
				sql=" from Edmtickettypetab where byisuse=1 and itickettypeid in(select ed.itickettypeid  from Edmticketcomposepricetab ed where ed.id.icrowdkindpriceid in(select icrowdkindpriceid from Edmcrowdkindpricetab where itickettypeid="+itickettypeid+") )";
			}
			List<Edmtickettypetab> list_init = this.find(sql);
			//根据票类型查找该票的服务商
			String scenic_init="";
			for (Iterator iterator = list_init.iterator(); iterator.hasNext();) {
				Edmtickettypetab edmtickettypetab_init = (Edmtickettypetab) iterator.next();
				scenic_init+=edmtickettypetab_init.getIscenicid();
				if (iterator.hasNext()) {
					scenic_init+=",";
				}
			}
			//返回这些服务商的所有园门
			return this.getgardengatelist(scenic_init);
		}
	}
	/**
	 * *
	 * Describe:显示出套票的子产品
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getviewedmticket(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-22
	 */
	public String getviewedmticket(Long itickettypeid) {
		StringBuffer json = new StringBuffer();
		Edmtickettypetab edm=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String sql=null;
		if(!edm.getBycategorytype().equals("0010")){
			sql=" from Edmtickettypetab where byisuse=1 and itickettypeid="+itickettypeid;
		}else{
			sql=" from Edmtickettypetab where byisuse=1 and itickettypeid in(select ed.itickettypeid  from Edmticketcomposepricetab ed where ed.id.icrowdkindpriceid in(select icrowdkindpriceid from Edmcrowdkindpricetab where itickettypeid="+itickettypeid+") )";
		}
		List list = this.find(sql);
		json.append("[");
		if (list==null||list.size()<1) {
			json.append("{\"itickettypeid\":\"" + -1 + "\",\"sztickettypename\":\""
					+ "无" + "\"}");
		}
		else{
			for (int i = 0; i < list.size(); i++) {
				Edmtickettypetab e = (Edmtickettypetab) list.get(i);
				json.append("{\"itickettypeid\":\"" + e.getItickettypeid()+ "\",\"sztickettypename\":\""
						+e.getSztickettypename()+ "\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
		}
		json.append("]");
		return json.toString();
	}
	
	/**
	 * *
	 * Describe:根据园门id、产品id、子产品id判断存在 （添加时候）
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#showgradeTicket(java.lang.Long, java.lang.Long, java.lang.Long)
	 * @param igardengateid
	 * @param itickettypeid
	 * @param izticktypeid
	 * @return
	 * @author lijingrui
	 * Date:Nov 11, 2011
	 */
	public boolean showgradeTicket(Long igardengateid, Long itickettypeid,Long izticktypeid) {
		String sql=" from Opwwicketsettab op where op.igardengateid="+igardengateid+" and op.itickettypeid="+itickettypeid+" and op.izticktypeid="+izticktypeid;
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public List showgradeTicket(Long igardengateid,String bywicketconsumetype) {
		String sql=" from Opwwicketsettab op where op.igardengateid="+igardengateid;
		if(bywicketconsumetype!=null&&!"".equals(bywicketconsumetype)){
			sql+=" and op.bywicketconsumetype='"+bywicketconsumetype+"' ";
		}
		List lst=this.find(sql);
		return lst;
	}
}

