package com.ectrip.sys.employee.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.employee.dao.IESPPostsDutyTabDao;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.sys.model.employee.Esppostsbtndutytab;
import com.ectrip.sys.model.employee.Esppostsdutytab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;

@Repository
public class ESPPostsDutyTabDAO extends GenericDao implements IESPPostsDutyTabDao{

	/**
	 * lijingrui
	 * 显示 岗位与职责的关联
	 */
	public List<Espdutytab> getAllpost(String posttype) {
		String sql="";
//		if(posttype.equals("01")){
//			sql=" select es.idutyid as idutyid,es.szdutyname as szdutyname,es.ilevel as ilevel from Espdutytab es where es.byisuse=1  start with  es.idutyid in (select ed.idutyid from Espdutytab ed where ed.ilevel=1 and ed.byisuse=1)   connect by prior  es.idutyid=es.iparentid ";
//		}else{
//			sql=" select es.idutyid as idutyid,es.szdutyname as szdutyname,es.ilevel as ilevel from Espdutytab es where es.byisuse=1 and es.dutype='"+posttype+"'  start with  es.idutyid in (select ed.idutyid from Espdutytab ed where ed.ilevel=1 and ed.byisuse=1)   connect by prior  es.idutyid=es.iparentid ";
//		}
		sql=" select es.idutyid as idutyid,es.szdutyname as szdutyname,es.ilevel as ilevel from Espdutytab es where es.byisuse=1 and es.dutype='"+posttype+"'  start with  es.idutyid in (select ed.idutyid from Espdutytab ed where ed.ilevel=1 and ed.byisuse=1)   connect by prior  es.idutyid=es.iparentid ";
		//String hsql = " from Espdutytab es where es.ilevel=1 and es.dutype='"+posttype+"'  and es.byisuse=1  ";
		//List list1 = this.find(hsql);
		
		List list  = this.find(sql);
		
		//System.out.println("this.getSession().createSQLQuery(sql).list();"+ list.size());
		return list;
		//return getSonDutyTab(list1,posttype);
		
	}
	
	public List getSonDutyTab(List list, String posttype) {
		String hsql = " from Espdutytab es where es.dutype='"+posttype+"'  and es.byisuse=1  ";
		List list1 = this.find(hsql);
		if (list1 != null && list1.size() >= 1) {
			for (int i = 0; i < list1.size(); i++) {
				Espdutytab custom = (Espdutytab) list1.get(i);
				list.add(custom.getIdutyid());
				list = getSonDutyTab(list,posttype);
			}
		}
		return list;
	}
	
	/**
	 * 某个岗位的所有职责
	 */
	public List<Espdutytab> reviterId(Long ipostsid) {
		String sql="select idutyid from Espdutytab where idutyid in(select idutyid from Esppostsdutytab where ipostsid="+ipostsid+")";
		return this.find(sql);
	}
	
	
	/**
	 * 显示与岗位 关联的岗位职责信息
	 * @param ipostsid
	 * @return
	 */
	public List<Esppostsdutytab> revisterRegid(Long ipostsid) {
		String sql=" from Esppostsdutytab where ipostsid="+ipostsid;
		return this.find(sql);
	}
	
	/**
	 * 保存 某岗位与职责的关联
	 */
	@SuppressWarnings({ "rawtypes"})
	public void updates(Esppoststab post,Esppostsdutytab postduty,String[] idutyids,Syslog syslog) {
		//单独增加旅游卡权限
		if(Arrays.asList(idutyids).contains("10274")) {
			String hql=" from Esppostsbtndutytab where bipostsid="+post.getIpostsid();
			List lst=this.find(hql);
			if(lst!=null && lst.size()>0){}
				for(int i=0;i<lst.size();i++){
					this.delete(lst.get(i));
			}
			for(int i=0;i<idutyids.length;i++){
				Esppostsbtndutytab es=new Esppostsbtndutytab();
				es.setBidutyid(new Long(idutyids[i]));
				String sql="select max(bipostsdutyid) from Esppostsbtndutytab";
				List list = this.find(sql);
				Long id = null;
				if (list != null && list.size() >= 1 && list.get(0) != null) {
					id = (Long) list.get(0) + 1;
				} else {
					id = new Long(1);
				}
				es.setBipostsdutyid(id);
				es.setBipostsid(post.getIpostsid());
				/*es.setByisuse(postduty.getByisuse());
				es.setSzmemo(postduty.getSzmemo());
				
				es.setBisinquire("0");  //查询权
				es.setBismanage("0");	 //管理权
				es.setBisdisable("0");	 //禁用权
				es.setIversion(new Long(57));*/
				
				this.save(es);
			}
		}else {
			String hql=" from Esppostsdutytab where ipostsid="+post.getIpostsid();
			List lst=this.find(hql);
			if(lst!=null && lst.size()>0){}
				for(int i=0;i<lst.size();i++){
					this.delete(lst.get(i));
			}
			for(int i=0;i<idutyids.length;i++){
				Esppostsdutytab es=new Esppostsdutytab();
				es.setIdutyid(new Long(idutyids[i]));
				
				String sql="select max(ipostsdutyid) from Esppostsdutytab";
				List list = this.find(sql);
				Long id = null;
				if (list != null && list.size() >= 1 && list.get(0) != null) {
					id = (Long) list.get(0) + 1;
				} else {
					id = new Long(1);
				}
				es.setIpostsdutyid(id);
				es.setIpostsid(post.getIpostsid());
				es.setByisuse(postduty.getByisuse());
				es.setSzmemo(postduty.getSzmemo());
				
				es.setBisinquire("0");  //查询权
				es.setBismanage("0");	 //管理权
				es.setBisdisable("0");	 //禁用权
				es.setIversion(new Long(57));
				
				this.save(es);
			}
		Esppoststab ps=(Esppoststab) this.get(Esppoststab.class, post.getIpostsid());
		syslog.setStlg("0030");
		syslog.setBrief("岗位职责：" + ps.getIpostsid()+" ,"+ps.getSzpostsname());
		syslog.setNote("新增/修改岗位职责：" + ps.getIpostsid()+" ,"+ps.getSzpostsname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}	
	}
	/**
	 * 获取旅游卡按钮权限
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Espdutytab> getTourcardBtnDuty() throws RuntimeException{
		String sql="SELECT es.idutyid AS idutyid,es.szdutyname AS szdutyname, es.ilevel AS ilevel"
			+ " FROM Espdutytab es WHERE"
			+ " es.byisuse=1 AND es.dutype='02' and es.idutyid = 10274 or es.IPARENTID = 10274";
		List list  = this.find(sql);
		return list;
	}
	
	/**
	 * 旅游卡按钮权限
	 */
	@SuppressWarnings("unchecked")
	public List<Espdutytab> reviterTourcardId(Long ipostsid) throws RuntimeException {
		String sql="select idutyid from Espdutytab where idutyid in(select bidutyid from Esppostsbtndutytab where bipostsid="+ipostsid+")";
		return this.find(sql);
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Esppostsbtndutytab> revisterTourcardRegid(Long ipostsid) throws RuntimeException {
		String sql=" from Esppostsbtndutytab where bipostsid="+ipostsid;
		return this.find(sql);
	}
	
}
