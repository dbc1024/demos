package com.ectrip.sys.employee.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.employee.dao.IESPPostsTabDao;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Esppostsdutytab;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
/**
 * 岗位管理
 * @author lijingrui
 */
@Repository
public class ESPPostsTabDAO extends GenericDao implements IESPPostsTabDao{
	
	
	/**
	 * 根据岗位名称 
	 * 列表显示所有的部门
	 */
	public PaginationSupport getlistEspposts(String szpostname,String posttype,int pageSize,
			int startIndex, String url) {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(es.ipostsid as ipostsid,es.szpostsname as szpostsname,es.bycategorytype as bycategorytype,es.szmemo as szmemo,es.byisuse as byisuse,sys1.pmva as posttype) from Esppoststab es,Sysparv5 sys1 where sys1.id.pmcd=es.posttype and sys1.id.pmky='GWTP' ");
		if(szpostname!=null&&!szpostname.equals("")){
			sql.append(" and es.szpostsname like '%"+szpostname+"%' ");
		}
		if(posttype!=null&&!posttype.equals("")){
			sql.append(" and es.posttype='"+posttype+"'");
		}
		sql.append(" order by es.posttype");
		return super.findPage(sql.toString(), pageSize, startIndex, url);
	}

	
	/**
	 * 新增岗位
	 * @throws Exception 
	 */
	public void insertEspposts(Esppoststab post,Syslog syslog){
		String sql="select max(ipostsid) from Esppoststab";
		List list = this.find(sql);
		Long id = null;
		if (list != null && list.size() >= 1 && list.get(0) != null) {
			id = (Long) list.get(0) + 1;
		} else {
			id = new Long(1);
		} 
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");            
        Date date;
		try {
			date = fmt.parse(Tools.getDayTimes());
		} catch (Exception e) {
			e.printStackTrace();
			date=new Date();
		}
		post.setDtcreateddate(date);  //建立日期
		post.setIpostsid(id);
		post.setIversion(new Long(57));
		this.save(post);
		
		syslog.setStlg("0027");
		syslog.setBrief("岗位：" + post.getIpostsid());
		syslog.setNote("新增岗位：" + post.getSzpostsname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 修改岗位信息
	 */
	public void updateEspposts(Esppoststab post,Syslog syslog){
		Esppoststab esp=(Esppoststab) super.get(Esppoststab.class, post.getIpostsid());
		esp.setSzpostscode(post.getSzpostscode());
		esp.setSzcreator(post.getSzcreator());
		esp.setSzpostsname(post.getSzpostsname());
		esp.setBycategorytype(post.getBycategorytype());
		esp.setByisuse(post.getByisuse());
		esp.setSzmemo(post.getSzmemo());
		esp.setPosttype(post.getPosttype());
		this.update(esp);	
		//陈新浩修改 2012-11-28 岗位禁止时，底下所有员工一起禁止
		String sql = "";
		List eplist = new ArrayList();
		List plist = new ArrayList();
		List elist = new ArrayList();
		if(esp.getByisuse()==0l){
			sql = "select ps.iemployeeid from Esfemployeepoststab ps where ps.ipostsid = "+esp.getIpostsid();
			eplist = this.find(sql);
			if(eplist!=null&&eplist.size()>0){
				for(int i=0;i<eplist.size();i++){
					Long iemployeeid = (Long)eplist.get(i);
					sql = "select po.ipostsid from Esppoststab po,Esfemployeepoststab ps where po.ipostsid = ps.ipostsid and po.byisuse = 1 and ps.iemployeeid = "+iemployeeid;
					plist = this.find(sql);
					if(plist!=null&&plist.size()>0){
						continue;
					}else{
						sql = " from Esfemployeetab e where e.iemployeeid = "+iemployeeid;
						elist = this.find(sql);
						if(elist!=null&&elist.size()>0){
							Esfemployeetab es = (Esfemployeetab) elist.get(0);
							es.setByisuse(0l);
							this.update(es);
						}
					}
				}
			}
		}
		//修改结束
		syslog.setStlg("0028");
		syslog.setBrief("岗位：" + post.getIpostsid());
		syslog.setNote("修改岗位：" + post.getSzpostsname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 删除岗位
	 */
	public void deleteEspposts(Esppoststab post,Syslog syslog){
		String sql=" from Esppostsdutytab ed where ed.ipostsid="+post.getIpostsid();
		List list=this.find(sql);
		if(list.size()>0&&list!=null){
			for(int i=0;i<list.size();i++){
				Esppostsdutytab espostduyt=(Esppostsdutytab) list.get(i);
				this.delete(espostduyt);
			}
		}
		this.delete(post);
		
		syslog.setStlg("0029");
		syslog.setBrief("岗位：" + post.getIpostsid());
		syslog.setNote("删除岗位：" + post.getSzpostsname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 根据编号得到岗位基本信息
	 * @throws InvocationTargetException 
	 * @throws Exception 
	 */
	public Esppoststab findEspposts(Long ipostsid) throws Exception {
		String sql="select new map(es.ipostsid as ipostsid,es.szpostscode as szpostscode,es.szpostsname as szpostsname,es.bycategorytype as bycategorytype,es.szmemo as szmemo,es.byisuse as byisuse,sys1.pmva as posttype) from Esppoststab es,Sysparv5 sys1 where sys1.id.pmcd=es.posttype and sys1.id.pmky='GWTP' and es.ipostsid="+ipostsid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esppoststab post = new Esppoststab();
			BeanUtils.populate(post, (Map) list.get(0));
			return post;
		}
	}
	
	/**
	 * *
	 * Describe:判断某岗位与部门是否关联
	 * @see com.ectrip.system.employee.dao.idao.IESPPostsTabDao#getEsfdeptpost(java.lang.Long)
	 * @param ipostsid
	 * @return
	 * @author yangguang
	 * Date:2011-9-23
	 */
	public boolean getEsfdeptpost(Long ipostsid) {
		String sql="select count(es.ideptpostsid) from Esfdeptpoststab es where es.ipostsid="+ipostsid;
		List list = super.find(sql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 根据登录用户id读取有效的岗位
	 * Describe:如果登录用户为平台用户，则读出所有岗位，如果为景区用户，则只出相应景区中的岗位
	 * @auth:huangyuqi
	 * @param employeeId
	 * @return
	 * return:List
	 * Date:2011-10-9
	 */
	public List getAllPost(Long employeeId){
		List list = new ArrayList();
		String sql=" from Galcompanyinfotab pany where pany.icompanyinfoid in(select emp.icompanyinfoid from  Esfemployeetab emp where  emp.iemployeeid="+employeeId+")";
		String hsql="";
		List companylist = this.find(sql);
		if(companylist.size()>=1){
			Galcompanyinfotab company = (Galcompanyinfotab)companylist.get(0);
			if("01".equals(company.getCompanytype())){//平台管理
				 hsql=" from Esppoststab where byisuse=1";
			}
			if("02".equals(company.getCompanytype())){//景区企业
				hsql=" from Esppoststab where byisuse=1 where ipostsid in (select ipostsid from Esfdeptpoststab deptpost where deptpost.ideptid in(select ideptid from Esfdepttab dept where dept.icompanyinfoid ="+company.getIcompanyinfoid()+"))";
			}
		}
		
		list = this.find(hsql);
		return list;
	}
	
}
