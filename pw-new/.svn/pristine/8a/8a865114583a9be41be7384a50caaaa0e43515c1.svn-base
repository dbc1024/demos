package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.provider.dao.ITicketXgzDAO;
@Repository
public class TicketXgzDAO extends GenericDao implements ITicketXgzDAO{
	/**
	 * *
	 * Describe:显示所属服务商的所有退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#showAllticketxgz(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public PaginationSupport showAllticketxgz(String scenics,int pageSize,int startIndex, String url){
		PaginationSupport ps =null;	
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(txz.gzid as gzid,txz.gzname as gzname,txz.iscenicid as iscenicid,txz.isvalid as isvalid,txz.xyjs as xyjs,txz.xyrate as xyrate,txz.xyrate2 as xyrate2,txz.xyjs2 as xyjs2,txz.xyjs4 as xyjs4,sys1.pmva as lgtp,sys2.pmva as gztype,sys3.pmva as jsfs,sys4.pmva as jstp,esb.szscenicname as szscenicname,edm.sztickettypename as sztickettypename ) from Ticketxgz txz,Esbscenicareatab esb,Edmtickettypetab edm,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4 "+  
					" where txz.iscenicid=esb.iscenicid and txz.itickettypeid=edm.itickettypeid and sys1.id.pmcd=txz.lgtp and sys1.id.pmky='LGTP' and sys2.id.pmcd=txz.gztype and sys2.id.pmky='TDGZ' and sys3.id.pmcd=txz.jsfs and sys3.id.pmky='JSFS'  and sys4.id.pmcd=txz.jstp and sys4.id.pmky='JSZL' ");
		if(scenics!=null&&!scenics.equals("")){
			hsql.append(" and txz.iscenicid in ("+scenics+")");
		}
		ps=this.findPage(hsql.toString(), pageSize, startIndex, url);
		
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if(map.get("gzid")!=null){
					String gzid = map.get("gzid").toString();
					
					String sql=" from Changebackrate where gzid="+Long.parseLong(gzid);
					List changlist=this.find(sql);
					map.put("changlist", changlist);
				}
				
			}
		}
		return ps;
	}
	
	/**
	 * *
	 * Describe:添加退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#insertticketxgz(com.ectrip.model.syspar.Ticketxgz, com.ectrip.model.syspar.Syslog)
	 * @param tickxgz
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public void insertticketxgz(Ticketxgz tickxgz,Syslog syslog){
		Long maxid=this.getMaxPk("gzid", "Ticketxgz");
		tickxgz.setGzid(maxid+1);
		
		this.save(tickxgz);
		
		if(tickxgz.getChangebackrate()!=null&&tickxgz.getChangebackrate().size()>0){
			for(int i=0;i<tickxgz.getChangebackrate().size();i++){
				Changebackrate backrate=tickxgz.getChangebackrate().get(i);
				if(!backrate.getTdfl().equals("")){
					Long changeid=this.getMaxPk("changebackrateid", "Changebackrate");
					backrate.setChangebackrateid(changeid+1);
					backrate.setGzid(tickxgz.getGzid());
					backrate.setTdfl(backrate.getTdfl());
					backrate.setTime(backrate.getTime());
					this.save(backrate);
				}
			}
		}
		
		syslog.setStlg("0169");
		syslog.setBrief("退订规则：" + tickxgz.getGzname());
		syslog.setNote("添加退订规则：" + tickxgz.getGzname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}
	
	/**
	 * *
	 * Describe:修改退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#updateticketxgz(com.ectrip.model.syspar.Ticketxgz, com.ectrip.model.syspar.Syslog)
	 * @param tickxgz
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public void updateticketxgz(Ticketxgz tickxgz,List list,Syslog syslog){
		Ticketxgz ts=(Ticketxgz) this.get(Ticketxgz.class, tickxgz.getGzid());
		ts.setGzname(tickxgz.getGzname());
		ts.setIscenicid(tickxgz.getIscenicid());
		ts.setJstp(tickxgz.getJstp());
		if(ts.getJstp().equals("0001")){
			ts.setItickettypeid(null);
		}else{
			ts.setItickettypeid(tickxgz.getItickettypeid());
		}
		ts.setGztype(tickxgz.getGztype());
		ts.setLgtp(tickxgz.getLgtp());
		ts.setJsfs(tickxgz.getJsfs());
		ts.setXyjs(tickxgz.getXyjs());
		ts.setXyrate(tickxgz.getXyrate());
		ts.setXyjs2(tickxgz.getXyjs2());
		ts.setXyrate2(tickxgz.getXyrate2());
		ts.setIsvalid(tickxgz.getIsvalid());
		ts.setSznote(tickxgz.getSznote());
		ts.setXyjs4(tickxgz.getXyjs4());
		
		this.update(ts);
		
		String sql=" from Changebackrate where gzid="+tickxgz.getGzid();
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			for(int i=0;i<lst.size();i++){
				Changebackrate rate=(Changebackrate) lst.get(i);
				this.delete(rate);
			}
		}
		
		if(tickxgz.getChangebackrate()!=null&&!tickxgz.getChangebackrate().equals("")){
			for(int i=0;i<tickxgz.getChangebackrate().size();i++){
				Changebackrate backrate=tickxgz.getChangebackrate().get(i);
				if(tickxgz.getJsfs().equals("0003")){
					if(backrate.getTdfl()!=null&&!backrate.getTdfl().equals("")){
						Long changeid=this.getMaxPk("changebackrateid", "Changebackrate");
						backrate.setChangebackrateid(changeid+1);
						backrate.setGzid(tickxgz.getGzid());
						backrate.setTdfl(backrate.getTdfl());
						backrate.setTime(backrate.getTime());
						this.save(backrate);
					}
				}else{
					if(backrate.getTime()!=null&&backrate.getTdfl()!=null&&!backrate.getTime().equals("")&&!backrate.getTdfl().equals("")){
						Long changeid=this.getMaxPk("changebackrateid", "Changebackrate");
						backrate.setChangebackrateid(changeid+1);
						backrate.setGzid(tickxgz.getGzid());
						backrate.setTdfl(backrate.getTdfl());
						backrate.setTime(backrate.getTime());
						this.save(backrate);
					}
				}
				
			}
		}
		
		if(list!=null&&list.size()>0){
			for(int x=1;x<list.size();x++){
				Changebackrate backrate=(Changebackrate) list.get(x);
				if(backrate.getTime()!=null&&backrate.getTdfl()!=null&&!backrate.getTime().equals("")&&!backrate.getTdfl().equals("")){
					Long changeid=this.getMaxPk("changebackrateid", "Changebackrate");
					backrate.setChangebackrateid(changeid+1);
					backrate.setGzid(tickxgz.getGzid());
					backrate.setTdfl(backrate.getTdfl());
					backrate.setTime(backrate.getTime());
					this.save(backrate);
				}
				
			}
		}
		
		
		syslog.setStlg("0170");
		syslog.setBrief("退订规则：" + tickxgz.getGzname());
		syslog.setNote("修改退订规则：" + tickxgz.getGzname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:删除退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#deleteticketxgz(com.ectrip.model.syspar.Ticketxgz, com.ectrip.model.syspar.Syslog)
	 * @param tickxgz
	 * @param syslog
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public void deleteticketxgz(Ticketxgz tickxgz,Syslog syslog){
		Ticketxgz t=(Ticketxgz) this.get(Ticketxgz.class, tickxgz.getGzid());
		this.delete(t);
		
		String sql=" from Changebackrate where gzid="+tickxgz.getGzid();
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			for(int i=0;i<lst.size();i++){
				Changebackrate rate=(Changebackrate) lst.get(i);
				this.delete(rate);
			}
		}

		
		syslog.setStlg("0171");
		syslog.setBrief("退订规则：" + tickxgz.getGzname());
		syslog.setNote("删除退订规则：" + tickxgz.getGzname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:查看退票诚信规则信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#getviewticketxgz(java.lang.Long)
	 * @param gzid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public Ticketxgz getviewticketxgz(Long gzid) throws Exception{
		String hsql=new String();
		Ticketxgz t=(Ticketxgz) this.get(Ticketxgz.class, gzid);
		if(t.getItickettypeid()==null||t.getItickettypeid().equals("")){
			hsql="select new map(txz.gzid as gzid,txz.gzname as gzname,txz.iscenicid as iscenicid,txz.isvalid as isvalid,txz.xyjs as xyjs,txz.xyrate as xyrate,txz.xyjs2 as xyjs2,txz.xyrate2 as xyrate2,txz.sznote as sznote,txz.xyjs4 as xyjs4,sys1.pmva as lgtp,sys2.pmva as gztype,sys3.pmva as jsfs,sys4.pmva as jstp,esb.szscenicname as szscenicname) from Ticketxgz txz,Esbscenicareatab esb,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4 "+  
			" where txz.iscenicid=esb.iscenicid and sys1.id.pmcd=txz.lgtp and sys1.id.pmky='LGTP' and sys2.id.pmcd=txz.gztype and sys2.id.pmky='TDGZ' and sys3.id.pmcd=txz.jsfs and sys3.id.pmky='JSFS'  and sys4.id.pmcd=txz.jstp and sys4.id.pmky='JSZL' and txz.gzid="+gzid;
		}else{
			hsql="select new map(txz.gzid as gzid,txz.gzname as gzname,txz.iscenicid as iscenicid,txz.isvalid as isvalid,txz.xyjs as xyjs,txz.xyrate as xyrate,txz.xyjs2 as xyjs2,txz.xyrate2 as xyrate2,txz.sznote as sznote,txz.xyjs4 as xyjs4,sys1.pmva as lgtp,sys2.pmva as gztype,sys3.pmva as jsfs,sys4.pmva as jstp,esb.szscenicname as szscenicname,edm.sztickettypename as sztickettypename) from Ticketxgz txz,Edmtickettypetab edm,Esbscenicareatab esb,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4 "+  
			" where txz.iscenicid=esb.iscenicid and edm.itickettypeid=txz.itickettypeid and sys1.id.pmcd=txz.lgtp and sys1.id.pmky='LGTP' and sys2.id.pmcd=txz.gztype and sys2.id.pmky='TDGZ' and sys3.id.pmcd=txz.jsfs and sys3.id.pmky='JSFS'  and sys4.id.pmcd=txz.jstp and sys4.id.pmky='JSZL' and txz.gzid="+gzid;
		}
		List list = super.find(hsql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Ticketxgz ts=new Ticketxgz();
			String sql=" from Changebackrate where gzid="+gzid;
			ts.setChangebackrate(this.find(sql));
			
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:显示出退票信息和退票费率表信息
	 * @see com.ectrip.system.syspar.dao.idao.ITicketXgzDAO#gettickxgzchangebackview(java.lang.Long)
	 * @param gzid
	 * @return
	 * @author lijingrui
	 * Date:Nov 23, 2011
	 */
	public Ticketxgz gettickxgzchangebackview(Long gzid){
		Ticketxgz t=(Ticketxgz) this.get(Ticketxgz.class,gzid);
		String sql=" from Changebackrate where gzid="+gzid+" order by time";
		t.setChangebackrate(this.find(sql));
		return t;
	}
	
	/**
	 * *
	 * Describe:显示服务商信息
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#findListesbticket(java.lang.String)
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public List findListesbticket(String scenics){
		StringBuffer sql=new StringBuffer();
		sql.append(" from Esbscenicareatab where isjd=0 and scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or sys1.spmcd='01'))");
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" and iscenicid in ("+scenics+")");
		}
		sql.append(" order by iscenicid");
		return this.find(sql.toString());
	}
	
	/**
	 * *
	 * Describe:显示所属的票类型
	 * @see com.ectrip.system.syspar.service.iservice.ITicketXgzService#getedmtickettypelist(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:Nov 18, 2011
	 */
	public String getedmtickettypelist(Long iscenicid){
		StringBuffer json = new StringBuffer();
		String sql="from Edmtickettypetab where byisuse=1 and bycategorytype!='0010' and iscenicid="+iscenicid;
		List list = this.find(sql);
		json.append("[");
		for (int i = 0; i < list.size(); i++) {
			Edmtickettypetab e = (Edmtickettypetab) list.get(i);
			json.append("{\"itickettypeid\":\"" + e.getItickettypeid()+ "\",\"sztickettypename\":\""
					+e.getSztickettypename()+ "\"}");
			if (i != list.size() - 1) {
				json.append(",");
			}
		}
		json.append("]");
		return json.toString();
	}
	/**
	 * 
	 * Describe:显示所属的票类型(包含套票)
	 * @author:chenxinhao
	 * @param iscenicid
	 * @return
	 * return:String
	 * Date:2013-1-23
	 */
	public String getedmtickettypelist2(Long iscenicid){
		StringBuffer json = new StringBuffer();
		String sql="from Edmtickettypetab where byisuse=1 and iscenicid="+iscenicid;
		List list = this.find(sql);
		json.append("[");
		for (int i = 0; i < list.size(); i++) {
			Edmtickettypetab e = (Edmtickettypetab) list.get(i);
			json.append("{\"itickettypeid\":\"" + e.getItickettypeid()+ "\",\"sztickettypename\":\""
					+e.getSztickettypename()+ "\"}");
			if (i != list.size() - 1) {
				json.append(",");
			}
		}
		json.append("]");
		return json.toString();
	}
}

