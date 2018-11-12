package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.permitenter.Esbtripequiptab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.permitenter.dao.IEsbtripequiptabDAO;

public class EsbtripequiptabDAO extends GenericDao implements IEsbtripequiptabDAO{

	/**
	 * *
	 * Describe:�б���ʾ�����еľ����г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getlistesbtripequip(int, int, java.lang.String)
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public PaginationSupport getlistesbtripequip(int pageSize, int startIndex,
			String url) {
		String sql="select new map(esb.itripid as itripid,esb.isceniaid as isceniaid,esb.iscenibid as iscenibid,esb.iscenicaname as iscenicaname,esb.iscenicbname as iscenicbname,esb.byisstend as byisstend,esb.szdata as szdata,esb.szdatb as szdatb,esb.sztripdate as sztripdate,esb.byisvenue as byisvenue,esb.byisuse as byisuse,esb.szmemo as szmemo) from Esbtripequiptab esb order by esb.itripid";
		return this.findPage(sql, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:����г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#addesbtripequip(com.ectrip.model.permitenter.Esbtripequiptab, com.ectrip.model.syspar.Syslog)
	 * @param esbtrip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public void addesbtripequip(Esbtripequiptab esbtrip, Syslog syslog) {
		if(esbtrip.getByisstend()==0&&esbtrip.getByisstend()!=null){
			String hql=" select eg.szgardengatename from Esbgardengatetab eg where eg.byiscont=0 and eg.id.igardengateid="+esbtrip.getIsceniaid();
			esbtrip.setIscenicaname(this.find(hql).get(0).toString());
		}else{
			String sql="select esb.szscenicname from Esbscenicareatab esb where esb.isjd=1 and esb.iscenicid="+esbtrip.getIsceniaid();
			esbtrip.setIscenicaname(this.find(sql).get(0).toString());
		}
		
		if(esbtrip.getByisvenue()==0&&esbtrip.getByisvenue()!=null){
			String hsql=" select eg.szgardengatename from Esbgardengatetab eg where eg.byiscont=1 and eg.id.igardengateid="+esbtrip.getIscenibid();
			esbtrip.setIscenicbname(this.find(hsql).get(0).toString());
		}else{
			String hwql="select esb.szscenicname from Esbscenicareatab esb where esb.isjd=1 and esb.iscenicid="+esbtrip.getIscenibid();
			esbtrip.setIscenicbname(this.find(hwql).get(0).toString());
		}
		
		Long maxid=this.getMaxPk("itripid", "Esbtripequiptab");
		esbtrip.setItripid(maxid+1);
		this.save(esbtrip);
		
		syslog.setStlg("0184");
		syslog.setBrief("����A��  " + esbtrip.getIscenicaname()+"   ����B��"+esbtrip.getIscenicbname());
		syslog.setNote("����A��  " + esbtrip.getIscenicaname()+"   ����B��"+esbtrip.getIscenicbname()+"  �г̺�ʱ��"+esbtrip.getSztripdate());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:�޸��г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#editesbtripequip(com.ectrip.model.permitenter.Esbtripequiptab, com.ectrip.model.syspar.Syslog)
	 * @param esbtrip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public void editesbtripequip(Esbtripequiptab esbtrip, Syslog syslog) {
		Esbtripequiptab equ=(Esbtripequiptab) this.get(Esbtripequiptab.class, esbtrip.getItripid());
//		equ.setIsceniaid(esbtrip.getIsceniaid());
//		equ.setIscenibid(esbtrip.getIscenibid());
//		equ.setByisstend(esbtrip.getByisstend());
//		equ.setByisvenue(esbtrip.getByisvenue());
//		if(equ.getIsceniaid()!=esbtrip.getIsceniaid()&&!equ.getIsceniaid().equals(esbtrip.getIsceniaid())&&equ.getIscenibid()!=esbtrip.getIscenibid()&&!equ.getIscenibid().equals(esbtrip.getIscenibid())){
//			if(equ.getByisstend()==0&&equ.getByisstend()!=null){
//				String hql=" select eg.szgardengatename from Esbgardengatetab eg where eg.byiscont=0 and eg.id.igardengateid="+equ.getIsceniaid();
//				equ.setIscenicaname(this.find(hql).get(0).toString());
//			}else{
//				String sql="select esb.szscenicname from Esbscenicareatab esb where esb.isjd=1 and esb.iscenicid="+equ.getIsceniaid();
//				equ.setIscenicaname(this.find(sql).get(0).toString());
//			}
//			
//			if(equ.getByisvenue()==0&&equ.getByisvenue()!=null){
//				String hsql=" select eg.szgardengatename from Esbgardengatetab eg where eg.byiscont=1 and eg.id.igardengateid="+equ.getIscenibid();
//				equ.setIscenicbname(this.find(hsql).get(0).toString());
//			}else{
//				String hwql="select esb.szscenicname from Esbscenicareatab esb where esb.isjd=1 and esb.iscenicid="+equ.getIscenibid();
//				equ.setIscenicbname(this.find(hwql).get(0).toString());
//			}
//		}
		equ.setSzdata(esbtrip.getSzdata());
		equ.setSzdatb(esbtrip.getSzdatb());
		equ.setSztripdate(esbtrip.getSztripdate());
		equ.setByisuse(esbtrip.getByisuse());
		equ.setSzmemo(esbtrip.getSzmemo());
		this.update(equ);
		
		syslog.setStlg("0185");
		syslog.setBrief("����A��  " + equ.getIscenicaname()+"   ����B��"+equ.getIscenicbname());
		syslog.setNote("����A��  " + equ.getIscenicaname()+"   ����B��"+equ.getIscenicbname()+"  �г̺�ʱ��"+equ.getSztripdate());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * *
	 * Describe:ɾ���г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#delesbtripequip(com.ectrip.model.permitenter.Esbtripequiptab, com.ectrip.model.syspar.Syslog)
	 * @param esbtrip
	 * @param syslog
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public void delesbtripequip(Esbtripequiptab esbtrip, Syslog syslog) {
		Esbtripequiptab esb=(Esbtripequiptab) this.get(Esbtripequiptab.class, esbtrip.getItripid());
		
		syslog.setStlg("0186");
		syslog.setBrief("����A��  " + esb.getIscenicaname()+"   ����B��"+esb.getIscenicbname());
		syslog.setNote("����A��  " + esb.getIscenicaname()+"   ����B��"+esb.getIscenicbname()+"  �г̺�ʱ��"+esb.getSztripdate());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		this.delete(esb);
	}

	/**
	 * *
	 * Describe:�鿴�г���Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getviewesbtripid(java.lang.Long)
	 * @param itripid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public Esbtripequiptab getviewesbtripid(Long itripid) throws Exception {
		String sql="select new map(esb.itripid as itripid,esb.isceniaid as isceniaid,esb.iscenibid as iscenibid,esb.iscenicaname as iscenicaname,esb.iscenicbname as iscenicbname,esb.byisstend as byisstend,esb.szdata as szdata,esb.szdatb as szdatb,esb.sztripdate as sztripdate,esb.byisvenue as byisvenue,esb.byisuse as byisuse,esb.szmemo as szmemo) from Esbtripequiptab esb where esb.itripid="+itripid;
		List lst=this.find(sql);
		if (lst == null || lst.size() < 1) {
			return null;
		} else {
			Esbtripequiptab ts=new Esbtripequiptab();
			BeanUtils.populate(ts, (Map) lst.get(0));
			return ts;
		}
	}
	
	/**
	 * *
	 * Describe:��ʾ����A��Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getListequip(java.lang.String)
	 * @param scenics
	 * @return
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public String getListequip(String scenics,Long byisstend) {
		StringBuffer json = new StringBuffer();
		if(byisstend!=null&&byisstend==0){
			String sql=" from Esbgardengatetab eg where eg.byiscont=0 ";
			if(scenics!=null&&!scenics.equals("")){
				sql+="and eg.iscenicid in ("+scenics+")";
			}
			List list=this.find(sql);
			json.append("[");
			for(int i=0;i<list.size();i++){
				Esbgardengatetab es=(Esbgardengatetab) list.get(i);
				json.append("{\"iscenicid\":\"" + es.getId().getIgardengateid()+ "\",\"scenicname\":\""
						+es.getSzgardengatename()+ "\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
			
			
		}else{
			String hql=" from Esbscenicareatab esb where esb.isjd=1 ";
			if(scenics!=null&&!scenics.equals("")){
				hql+="and esb.irootid in ("+scenics+")";
			}
			List list=this.find(hql);
			json.append("[");
			for(int i=0;i<list.size();i++){
				Esbscenicareatab est=(Esbscenicareatab) list.get(i);
				json.append("{\"iscenicid\":\"" + est.getIscenicid()+ "\",\"scenicname\":\""
						+est.getSzscenicname()+ "\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * *
	 * Describe:��ʾ����B��Ϣ
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getListgardip(java.lang.String, java.lang.Long)
	 * @param scenics
	 * @param byisvenue
	 * @return
	 * @author lijingrui
	 * Date:Dec 26, 2011
	 */
	public String getListgardip(String scenics,Long byisvenue) {
		StringBuffer json = new StringBuffer();
		if(byisvenue!=null&&byisvenue==0){
			String sql=" from Esbgardengatetab eg where eg.byiscont=1 ";
			if(scenics!=null&&!scenics.equals("")){
				sql+="and eg.iscenicid in ("+scenics+")";
			}
			List list=this.find(sql);
			
			json.append("[");
			for(int i=0;i<list.size();i++){
				Esbgardengatetab es=(Esbgardengatetab) list.get(i);
				json.append("{\"iscenicid\":\"" +es.getId().getIgardengateid()+ "\",\"scenicname\":\""
						+es.getSzgardengatename()+ "\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
			
			
		}else{
			String hql=" from Esbscenicareatab esb where esb.isjd=1 ";
			if(scenics!=null&&!scenics.equals("")){
				hql+="and esb.irootid in ("+scenics+")";
			}
			List list=this.find(hql);
			
			json.append("[");
			for(int i=0;i<list.size();i++){
				Esbscenicareatab est=(Esbscenicareatab) list.get(i);
				json.append("{\"iscenicid\":\"" + est.getIscenicid()+ "\",\"scenicname\":\""
						+est.getSzscenicname()+ "\"}");
				if (i != list.size() - 1) {
					json.append(",");
				}
			}
			json.append("]");
		}
		return json.toString();
	}
	
	
	/**
	 * *
	 * Describe:��ʾ����������ʱ
	 * @see com.ectrip.system.permitenter.service.iservice.IEsbtripequiptabService#getViewszdate(java.lang.String)
	 * @param scenicname
	 * @return
	 * @author lijingrui
	 * Date:Dec 26, 2011
	 */
	public String getViewszdate(Long scenicid){
		String sql="select esb.szdata from Esbscenicareatab esb where esb.isjd=1 and esb.iscenicid="+scenicid;
		List list=this.find(sql);
		if(list!=null&&list.size()>0){
			String et=list.get(0).toString();
			return et;
		}else{
			return null;
		}
	}
	
	/**
	 * *
	 * Describe:�ж϶������ʱ�Ƿ����
	 * @see com.ectrip.system.permitenter.dao.idao.IEsbtripequiptabDAO#getshopbeantak(java.lang.Long, java.lang.Long)
	 * @param iscenicaid
	 * @param iscenicbid
	 * @return
	 * @author lijingrui
	 * Date:Dec 24, 2011
	 */
	public boolean getshopbeantak(Long iscenicaid, Long iscenicbid) {
		String sql=" from Esbtripequiptab esb where (esb.isceniaid="+iscenicaid+" and esb.iscenibid="+iscenicbid+") or (esb.isceniaid="+iscenicbid+" and esb.iscenibid="+iscenicaid+")";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return true;
		}else{
			return false;
		}
	}

}

