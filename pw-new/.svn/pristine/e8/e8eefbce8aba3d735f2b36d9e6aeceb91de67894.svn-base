package com.ectrip.sys.employee.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IGALCompanyInfoTabDAO;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.CompanyscenicId;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.upload.model.Upfile;

/**
 * 
 * @author yangyang
 * @version 企业信息操作类
 *
 */
@Repository
public class GALCompanyInfoTabDAO extends GenericDao implements IGALCompanyInfoTabDAO{
	

	//删除企业信息
	/**
	 * @version 按企业ID删除<BR/>
	 * remarks: 删除某个公司时，需判断公司下是否有部门和员工，如果有，则不能删除
	 */
	
	public void deletecompany(Galcompanyinfotab comp) {
		// 删除企业的时，要先在企业与服务商的关系表中删除相应的数据

		if (comp.getCompanytype().equals("02")) {
			String hsql = " from Companyscenic where id.icompanyinfoid="
					+ comp.getIcompanyinfoid();
			List list = this.find(hsql);
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					Companyscenic s = (Companyscenic) list.get(i);
					this.delete(s);
				}
			}
			
		}
		
		if(comp.getSzcompanyemblem()!=null&&!comp.getSzcompanyemblem().equals("")){
			Upfile up=(Upfile) this.get(Upfile.class, Long.parseLong(comp.getSzcompanyemblem()));
			this.delete(up);
		}
		
		this.delete(comp);
	}

	

	/**
	 * * Describe:
	 * 
	 * @see com.ectrip.system.employee.service.iservice.IGALCompanyInfoService#companyQueryList(java.lang.String,
	 *      int, int, int, int, java.lang.String)
	 * @param mark
	 *            控制 上一次 还是下一层
	 * @param comid
	 *            企业的ID 即获取此企业的下级列表
	 * @param szinfocomid
	 *            //上级企业ID
	 * @param page
	 * @param pagesize
	 * @param url
	 * @author yangguang Date:2011-9-19
	 */
	public PaginationSupport companyQueryList(String mark, Long comid,
			int page, int pagesize, String url) {
		String hsql = "select new map(company.icompanyinfoid as icompanyinfoid,company.szinfocomid as szinfocomid,company.szcompanyemblem as szcompanyemblem,company.szcompanyname as szcompanyname,company.szaddress as szaddress,company.sztel as sztel,company.szfax as szfax,company.szbankaccount as szbankaccount,company.szemail as szemail,company.companytype as companytype,company.szwebsite as szwebsite,company.szmemo as szmemo,company.iversion as iversion,sp.pmva as strcompanytype,company.szcompanycode as szcompanycode) "
				+ " from Galcompanyinfotab company,Sysparv5 sp where sp.id.pmky='CMTP' and sp.id.pmcd=company.companytype ";
		StringBuffer sbf=new StringBuffer(hsql);
		if (comid == 0) {
			sbf.append(" and szinfocomid=0 order by icompanyinfoid ");
		} else {
			sbf.append(" and company.szinfocomid=" + comid);
		}
		return this.findPage(sbf.toString(), page, pagesize, url);
	}
	
	/**
	 * 功能 ：按条件模糊查询
	 */
	public PaginationSupport companyQueryListTJ(String flag, int parentid,
			String queryid, String queryMess, int page, int pagesize, String url) {
		String hsql = "select new map(company.icompanyinfoid as icompanyinfoid,company.szinfocomid as szinfocomid,company.szcompanyemblem as szcompanyemblem,company.szcompanyname as szcompanyname,company.szaddress as szaddress,company.sztel as sztel,company.szfax as szfax,company.szbankaccount as szbankaccount,company.szemail as szemail,company.companytype as companytype,company.szwebsite as szwebsite,company.szmemo as szmemo,company.iversion as iversion,sp.pmva as strcompanytype,company.szcompanycode as szcompanycode)"
				+ " from Galcompanyinfotab company,Sysparv5 sp where sp.id.pmky='CMTP' and sp.id.pmcd=company.companytype ";
		StringBuffer sbf=new StringBuffer(hsql);
		if (flag != null && flag.equals("0")&&queryid!=null&&!queryid.equals("")) {
			sbf.append(" and company.icompanyinfoid=" + queryid );
		} else if (flag != null && flag.equals("1")&&queryMess!=null&&!queryMess.equals("")) {
			sbf.append(" and company.szcompanyname like '%" + queryMess + "%'");
		}
		sbf.append(" and company.szinfocomid=" + parentid + " order by company.icompanyinfoid");
		return this.findPage(sbf.toString(), page, pagesize, url);
	}

	/**
	 * 功能 ：查询是否含有子级
	 * @param icompanyinfoid
	 * @return true 有子级 false 没子级
	 */
	public boolean isHaveSon(String icompanyinfoid) {
		String hql = "select count(*) from Galcompanyinfotab company where company.szinfocomid="
				+ icompanyinfoid + "";
		List list = super.find(hql);
		Long count = (Long) list.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能 ：查询是否
	 * @param icompanyinfoid
	 * @return false 不是平台企业 true 平台企业
	 */
	public boolean isPlatform(String icompanyinfoid) {
		String hql = "from Galcompanyinfotab company where company.icompanyinfoid="
				+ icompanyinfoid + "";
		List list = super.find(hql);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能 ：添加新的企业
	 */
	public void addNewCompany(Galcompanyinfotab comp) {
		System.out.println("add company ------"+comp.getSzinfocomid());
		comp.setIcompanyinfoid(this.getMaxPk("icompanyinfoid", "Galcompanyinfotab")+1);
		comp.setIversion(new Long(1));
		this.save(comp);
		
		if (comp.getCompanytype().equals("02")) {
			Long[] iscenics = comp.getIscenicids();
			for (int i = 0; i < iscenics.length; i++) {
				Companyscenic cs = new Companyscenic();
				CompanyscenicId csid = new CompanyscenicId();
				csid.setIcompanyinfoid(comp.getIcompanyinfoid());
				csid.setIscenicid(iscenics[i]);
				cs.setId(csid);
				this.save(cs);
			}
		}
	}


	/**
	 * 功能 ：根据ID获取公司信息
	 * @param icompanyinfoid
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Galcompanyinfotab getCompanyById(Long icompanyinfoid) throws IllegalAccessException, InvocationTargetException{
		String hql = "select new map(company.icompanyinfoid as icompanyinfoid,company.szinfocomid as szinfocomid,company.szinfocomid as szinfocomid,company.szcompanyemblem as szcompanyemblem,company.szcompanyname as szcompanyname,company.szaddress as szaddress,company.sztel as sztel,company.szfax as szfax,company.szbankaccount as szbankaccount,company.szemail as szemail,company.companytype as companytype,company.szwebsite as szwebsite,company.szmemo as szmemo,company.iversion as iversion,sp.pmva as strcompanytype) from Galcompanyinfotab company,Sysparv5 sp where company.companytype=sp.id.pmcd and sp.id.pmky='CMTP' and company.icompanyinfoid="
			+ icompanyinfoid + "";
		List list=this.find(hql);
		if(list!=null&list.size()>0){
			Galcompanyinfotab company=new Galcompanyinfotab();
			BeanUtils.populate(company, (Map) list.get(0));
			return company;
		}else{
			return null;
		}
		
	}

	/**
	 * 功能 ：修改公司信息
	 * @param comp
	 * @param parent
	 */
	public void updateCompany(Galcompanyinfotab comp) {
		Galcompanyinfotab com=(Galcompanyinfotab)this.get(Galcompanyinfotab.class, comp.getIcompanyinfoid());
		if(com!=null){
            com.setSzcompanycode(comp.getSzcompanycode()); //任 新加修改企业编码
			com.setCompanytype(comp.getCompanytype());
			com.setSzaddress(comp.getSzaddress());
			com.setSzbankaccount(comp.getSzbankaccount());
			com.setSzcompanyemblem(comp.getSzcompanyemblem());
			com.setSzcompanyname(comp.getSzcompanyname());
			com.setSzemail(comp.getSzemail());
			com.setSzfax(comp.getSzfax());
			com.setSzinfocomid(comp.getSzinfocomid());
			com.setSzmemo(comp.getSzmemo());
			com.setSztel(comp.getSztel());
			com.setSzwebsite(comp.getSzwebsite());
			com.setSzinfocomid(comp.getSzinfocomid());
			
			String sql="select max(iversion) from Galcompanyinfotab where icompanyinfoid="+comp.getIcompanyinfoid();
			List list=this.find(sql);
			com.setIversion(new Long(list.get(0).toString())+1);
		}
		this.update(com);
		
		if (comp.getCompanytype().equals("02")) {
			Long[] iscenics = comp.getIscenicids();
			StringBuffer scenics=new StringBuffer();
			for (int k = 0; k < iscenics.length; k++) {
				scenics.append(iscenics[k]);
				if(k!=iscenics.length-1){
					scenics.append(",");
				}
			}
			
			String msql = " from Companyscenic where id.icompanyinfoid="+ comp.getIcompanyinfoid()+" and id.iscenicid not in ("+scenics+")";
			List lst=this.find(msql);
			if(lst!=null&&lst.size()>0){
				for(int x=0;x<lst.size();x++){
					Companyscenic compay = (Companyscenic) lst.get(x);
					this.delete(compay);
				}
			}
			
			String hsql = " from Companyscenic where id.icompanyinfoid="+ comp.getIcompanyinfoid();
			List list = this.find(hsql);
			
			for (int k = 0; k < iscenics.length; k++) {
				Long iscenicid=Long.parseLong(iscenics[k].toString());
				boolean b=false;
				if(list!=null&&list.size()>0){
					for (int i = 0; i < list.size(); i++) {
						Companyscenic s = (Companyscenic) list.get(i);
						if(s.getId().getIscenicid().equals(iscenicid) ){
							b=false;
							break;
						}else{
							b=true;
						}
					}
					if(b){
						Companyscenic cs = new Companyscenic();
						CompanyscenicId csid = new CompanyscenicId();
						csid.setIcompanyinfoid(comp.getIcompanyinfoid());
						csid.setIscenicid(iscenicid);
						cs.setId(csid);
						cs.setCytonly("0");
						this.save(cs);
					}
				}else{
					
					Companyscenic cs = new Companyscenic();
					CompanyscenicId csid = new CompanyscenicId();
					csid.setIcompanyinfoid(comp.getIcompanyinfoid());
					csid.setIscenicid(iscenicid);
					cs.setId(csid);
					cs.setCytonly("0");
					this.save(cs);
					
				}
				
			}
			
		}
	}

	
	/**
	 * 功能 ：根据企业类型获取企业数量
	 * @param companytype  企业类型
	 * @return
	 */
	public int getPlatformCount(String companytype){
		String hql="select count(*) from Galcompanyinfotab company where company.companytype='"+companytype+"'";
		List list = super.find(hql);
		if(list!=null&&list.size()>0){
			int count=Integer.parseInt(list.get(0).toString());
			return count;
		}else{
			return 0;
		}
	}
	/**
	 * 功能 ：查询企业服务商
	 */
	public List getCompanyScenics(List scids){

		if(scids.size()==0){//顶级企业添加顶级服务商
			String sql=" from Esbscenicareatab where ilevel=1 and byisuse=1 ";
			return this.find(sql);
		}else{//下属企业添加其上级企业的服务商及其服务商的下属服务商
			List sclist=new ArrayList();
			for(int i=0;i<scids.size();i++){
				String hsql=" from Esbscenicareatab where byisuse=1 and iscenicid="+((Companyscenic)scids.get(i)).getId().getIscenicid();
				List slist=this.find(hsql);
				if(slist.size()!=0){
					sclist.add(slist.get(0));
					String hql=" from Esbscenicareatab where byisuse=1 and iparentid="+((Esbscenicareatab)slist.get(0)).getIscenicid();
					List clist=this.find(hql);
					for(Object o:clist){
						sclist.add(o);
					}
				}
			}
			return sclist;
		}
	}
	/**
	 * 功能 ：根据ID查找出它所有的下属
	 */
	public List getScenicsByOneid(Long parentid){
		String sql = null;
		if(parentid==null||parentid.equals("")||parentid==0){//顶级企业添加顶级服务商
			sql="select sa.iscenicid as iscenicid,sa.ilevel as ilevel,sa.iparentid as iparentid,sa.szscenicname as szscenicname from Esbscenicareatab sa where sa.ilevel=1";
			
		}else{//下属企业添加其上级企业的服务商及其服务商的下属服务商
			sql="select sa.iscenicid as iscenicid,sa.ilevel as ilevel,sa.iparentid as iparentid,sa.szscenicname as szscenicname " +
			"from esbscenicareatab sa where 1=1 start with sa.iscenicid in (select cn.iscenicid from companyscenic cn where cn.icompanyinfoid="+parentid+") " +
			"connect by prior sa.iscenicid=sa.iparentid";
			
		}
		return this.find(sql);
	}
	/**
	 * 功能 ：在关系表中查询企业所选择的服务商
	 */
	public List<Companyscenic> getSelectedComscenics(Long comid){
		String sql=" from Companyscenic where icompanyinfoid="+comid;
		return this.find(sql);
	}

	
	public List getComscenics(Long comid) {
		String sql = "select  es.szscenicname as szscenicname  from Companyscenic cs,Esbscenicareatab es where cs.id.icompanyinfoid="
				+ comid + " and cs.id.iscenicid=es.iscenicid";
		return this.find(sql);
	}

	/**
	 * 酒店获取景区服务商(勾选了的)
	 */
	public List getHotelCom(Long comid,String domainname) {
		String sql = null;
		if(!domainname.equals("www.redarmycity.com")){
			 sql = "select  es.iscenicid as iscenicid  from Companyscenic cs,Esbscenicareatab es,ProviderCompany pro where cs.id.icompanyinfoid="
				+ comid + " and cs.id.iscenicid=es.iscenicid and pro.iscenicid=es.iscenicid and  pro.type='02' and pro.status='1'" ;
		}else{
			sql ="select  es.iscenicid as iscenicid  from Companyscenic cs,Esbscenicareatab es where cs.id.icompanyinfoid="
					+ comid + " and cs.id.iscenicid=es.iscenicid" ;
		}
			return this.find(sql);
	}
	
	public List getComscenicsId(Long comid) {
		String sql = "select  es.iscenicid as iscenicid  from Companyscenic cs,Esbscenicareatab es,ProviderCompany pro where cs.id.iscenicid=es.iscenicid and pro.iscenicid=es.iscenicid and pro.type='02' and pro.status='1'" ;
		if(comid != null){
			sql+=" and cs.id.icompanyinfoid="+ comid;
		}
		return this.find(sql);
	}
	public List getComscenicsName(Long comid){ 
		String sql = "select new map(es.szscenicname as szscenicname,es.szsceniccode as szsceniccode) "
				+ " from Companyscenic cs,Esbscenicareatab es where  1=1 ";
		if(comid != null){
			sql +=" and cs.id.icompanyinfoid ="+ comid;			
		}
		sql +=  " and cs.id.iscenicid=es.iscenicid group by es.szscenicname,es.szsceniccode";

		return this.find(sql);
	}
	/**
	 * 首页获取景区服务商
	 */
	public List getComsName(Long comid){ 
		String sql = "select new map(es.szscenicname as szscenicname,es.szsceniccode as szsceniccode) "
				+ " from Companyscenic cs,Esbscenicareatab es,ProviderCompany pro where  1=1 and es.scenictype = '01' "
				+ " and pro.iscenicid=es.iscenicid and  pro.type='02' and pro.status='1' ";
		if(comid != null){
			sql +=" and cs.id.icompanyinfoid ="+ comid;			
		}
		sql +=  " and cs.id.iscenicid=es.iscenicid group by es.szscenicname,es.szsceniccode";

		return this.find(sql);
	}
	/**
	 * 功能 ：保存企业与服务商
	 * 
	 * @param comid
	 *          企业编号
	 */
	public void saveCompanyScenics(Galcompanyinfotab comp) {
		// 在企业与服务商关系表中添加数据
		Long[] iscrnics = comp.getIscenicids();
		if (iscrnics != null && iscrnics.length != 0) {
			for (int i = 0; i < iscrnics.length; i++) {
				Companyscenic cms = new Companyscenic();
				CompanyscenicId csid = new CompanyscenicId();
				csid.setIcompanyinfoid(comp.getIcompanyinfoid());
				csid.setIscenicid(iscrnics[i]);
				cms.setId(csid);
				this.save(cms);
			}
		}
	}
	
	public PaginationSupport companyQueryListbycomid(String mark, Long comid,
			int page, int pagesize, String url) {
		String hsql = "select new map(company.icompanyinfoid as icompanyinfoid,company.szinfocomid as szinfocomid,company.szcompanyemblem as szcompanyemblem,company.szcompanyname as szcompanyname,company.szaddress as szaddress,company.sztel as sztel,company.szfax as szfax,company.szbankaccount as szbankaccount,company.szemail as szemail,company.companytype as companytype,company.szwebsite as szwebsite,company.szmemo as szmemo,company.iversion as iversion,sp.pmva as strcompanytype)"
				+ " from Galcompanyinfotab company,Sysparv5 sp where sp.id.pmky='CMTP' and sp.id.pmcd=company.companytype";
		StringBuffer sbf=new StringBuffer(hsql);
		
			sbf.append(" and company.icompanyinfoid=" + comid);
		
		return this.findPage(sbf.toString(), page, pagesize, url);
	}
	/**
	 * Describe: 用户关联的服务商
	 * @author luoxin
	 * @date 2014-03-27
	 * @param iemployee
	 * @return list
	 */
	public List getEmployeeScenicbyIemployeeid(Long iemployeeid) {
		// TODO Auto-generated method stub
		return this.find("from EmployeeScenic where iemployeeid="+iemployeeid);
	}
	
	/**
	 * Describe: 服务商
	 * @author luoxin
	 * @date 2014-03-27
	 * @param iscenicid
	 * @return Esbscenicareatab
	 */
	public Esbscenicareatab getScenic(Long iscenicid) {
		// TODO Auto-generated method stub
		return (Esbscenicareatab) this.get(Esbscenicareatab.class, iscenicid);
	}



	@Override
	public List getIscenicidsByIcompanyinfoid(Long icompanyinfoid) {
		
		String hsql = "select new map(cs.id.icompanyinfoid as icompanyinfoid, cs.id.iscenicid as iscenicid) from Companyscenic cs ";
		if(null != icompanyinfoid) {
			hsql = hsql + " where cs.id.icompanyinfoid="+ icompanyinfoid;
		}
		
		return this.find(hsql);
	}
}
