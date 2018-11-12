package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.permitenter.dao.INumjifenSetlistDAO;

public class NumjifenSetlistDAO extends GenericDao implements INumjifenSetlistDAO{
	
	/**
	 * *
	 * Describe:��ʾ��ĳ�����µ�������������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#showAllListnumjifen(java.lang.Long, int, int, java.lang.String)
	 * @param nid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public PaginationSupport showAllListnumjifen(Long nid,int pageSize,int startIndex, String url){
		PaginationSupport ps =null;
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(nt.id.nid as nid,nt.id.seq as seq,nt.itickettypeid as itickettypeid,nt.itickettypeid2 as itickettypeid2,nt.payfs as payfs,nt.stdt as stdt,nt.etdt as etdt,nt.isvalid as isvalid,nt.tgdx as tdgx,sys1.pmva as tgdx,sys2.pmva as tgfs,sys3.pmva as jsfs,sys4.pmva as xffs,nt.iint3 as iint3,nt.iint4 as iint4,nt.note5 as note5,nt.note6 as note6) from Numjifensetlist nt,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4 where nt.tgdx=sys1.id.pmcd and sys1.id.pmky='TGDX' and "+
				"nt.tgfs=sys2.id.pmcd and sys2.id.pmky='TGFS' and nt.jsfs=sys3.id.pmcd and sys3.id.pmky='JSGS' and nt.xffs=sys4.id.pmcd and sys4.id.pmky='XFFS' ");
		if(nid!=null){
			sql.append(" and nt.id.nid="+nid);
		}
		sql.append(" order by nt.id.seq");
		ps = this.findPage(sql.toString(), pageSize, startIndex, url);
		List lst = ps.getItems();
		if(lst!=null&&lst.size()>0){
			Map map = null;
			for (int i = 0; i < lst.size(); i++) {
				map = (Map) lst.get(i);
				if( map.get("itickettypeid")!=null){//��Ʒ���
					String itickettypeid = map.get("itickettypeid").toString();
					String tgdx = map.get("tdgx").toString();
					if(tgdx.equals("01")){ 
						Esbscenicareatab esb=(Esbscenicareatab) this.get(Esbscenicareatab.class, Long.parseLong(itickettypeid));
						map.put("sztickettypename", esb.getSzscenicname());
					}
					if(tgdx.equals("03")){
						Edmtickettypetab edm=(Edmtickettypetab) this.get(Edmtickettypetab.class, Long.parseLong(itickettypeid));
						map.put("sztickettypename",edm.getSztickettypename());
					}
					
				}
			}
		}
		return ps;
	}
	
	/**
	 * *
	 * Describe:�����������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#insertnumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public void insertnumjifensetlist(Numjifensetlist numsetlist){
		
		this.save(numsetlist);
	}
	
	/**
	 * *
	 * Describe:�޸���������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#updatenumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public void updatenumjifensetlist(Numjifensetlist numsetlist){
		Numjifensetlist num=(Numjifensetlist) this.get(Numjifensetlist.class, numsetlist.getId());
		num.setItickettypeid(numsetlist.getItickettypeid());
		num.setItickettypeid2(numsetlist.getItickettypeid2());
		num.setPayfs(numsetlist.getPayfs());
		num.setTgdx(numsetlist.getTgdx());
		num.setTgfs(numsetlist.getTgfs());
		num.setJsfs(numsetlist.getJsfs());
		num.setStdt(numsetlist.getStdt());
		num.setEtdt(numsetlist.getEtdt());
		num.setXffs(numsetlist.getXffs());
		num.setIsvalid(numsetlist.getIsvalid());
		num.setIint1(numsetlist.getIint1());
		num.setIint2(numsetlist.getIint2());
		num.setIint3(numsetlist.getIint3());
		num.setIint4(numsetlist.getIint4());
		num.setNote5(numsetlist.getNote5());
		num.setNote6(numsetlist.getNote6());
		
		this.update(num);
	}
	
	/**
	 * *
	 * Describe:ɾ����������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#deletenumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public void deletenumjifensetlist(Numjifensetlist numsetlist){
		String sql=" from Numjifensetlist num where num.id.nid="+numsetlist.getNid()+" and num.id.seq="+numsetlist.getSeq();
		Numjifensetlist numset=(Numjifensetlist) this.find(sql).get(0);
		this.delete(numset);
	}
	
	/**
	 * *
	 * Describe:�鿴��������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#viewnumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @return
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 * @throws Exception 
	 */
	public Numjifensetlist viewnumjifensetlist(Numjifensetlist numsetlist) throws Exception{
		String sql="select new map(nt.id.nid as nid,nt.id.seq as seq,nt.itickettypeid as itickettypeid,nt.itickettypeid2 as itickettypeid2,nt.payfs as payfs,nt.stdt as stdt,nt.etdt as etdt,nt.isvalid as isvalid,nt.tgdx as tgdx,sys1.pmva as tdgx,sys2.pmva as tgfs,sys3.pmva as jsfs,sys4.pmva as xffs,nt.iint1 as iint1,nt.iint2 as iint2,nt.iint3 as iint3,nt.iint4 as iint4,nt.note5 as note5,nt.note6 as note6) from Numjifensetlist nt,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3,Sysparv5 sys4 where nt.tgdx=sys1.id.pmcd and sys1.id.pmky='TGDX' and "+
		"nt.tgfs=sys2.id.pmcd and sys2.id.pmky='TGFS' and nt.jsfs=sys3.id.pmcd and sys3.id.pmky='JSGS' and nt.xffs=sys4.id.pmcd and sys4.id.pmky='XFFS' and nt.id.nid="+numsetlist.getId().getNid()+" and nt.id.seq="+numsetlist.getId().getSeq();
		List lst=this.find(sql);
		if (lst == null || lst.size() < 1) {
			return null;
		} else {
			Numjifensetlist ts=new Numjifensetlist();
			BeanUtils.populate(ts, (Map) lst.get(0));
			if(ts.getTgdx().equals("01")){
				Esbscenicareatab esb=(Esbscenicareatab) this.get(Esbscenicareatab.class, ts.getItickettypeid());
				ts.setSztickettypename(esb.getSzscenicname());
			}else if(ts.getTgdx().equals("03")){
				Edmtickettypetab edm=(Edmtickettypetab) this.get(Edmtickettypetab.class, ts.getItickettypeid());
				ts.setSztickettypename(edm.getSztickettypename());
				
				Edmbusinesstab business=(Edmbusinesstab)this.get(Edmbusinesstab.class, ts.getIint2());
				ts.setBussinessname(business.getSzbusinessname());
				
				Edpcrowdkindtab edpcrowdkindtab =(Edpcrowdkindtab) this.get(Edpcrowdkindtab.class, ts.getIint1()); 
				ts.setCrowkindname(edpcrowdkindtab.getSzcrowdkindname());
				
			}
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:��ʾ��ĳ�������µĲ�Ʒ��Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#getEdmticketlist(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public List getEdmticketlist(Long iscenicid){
		String sql=" from Edmtickettypetab edm where edm.iscenicid="+iscenicid;
		List lst=this.find(sql);
		return lst;
	}
}

