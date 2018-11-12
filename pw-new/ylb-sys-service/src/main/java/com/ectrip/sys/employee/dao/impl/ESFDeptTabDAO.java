package com.ectrip.sys.employee.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.employee.dao.IESFDeptTabDAO;
import com.ectrip.sys.model.employee.Esfdeptpoststab;
import com.ectrip.sys.model.employee.Esfdepttab;
import com.ectrip.sys.model.employee.Esfemployeetab;
/**
 * @author  yangyang
 * @version 部门管理操作类
 */
@Repository
public class ESFDeptTabDAO extends GenericDao implements IESFDeptTabDAO {

	//按ID删除部门
	/**
	 * @version  功能：删除部门<BR/>
	 * 
	 */
	public void deleteDept(Class cla, Object id) {
		// TODO Auto-generated method stub
		if(id!=null){
			//并在关系表中删除两表的关联
			String sql=" from Esfdeptpoststab where ideptid="+id;
			List pdlist=this.find(sql);
			for(int i=0;i<pdlist.size();i++){
				Esfdeptpoststab dp=(Esfdeptpoststab)pdlist.get(i);
				if(dp!=null){
					this.delete(dp);
				}
			}
			Esfdepttab dept=(Esfdepttab)this.get(cla, (Long)id);
			if(dept!=null){
				this.delete(dept);
			}
		}
	}
	//查询部门下是否还有员工
	public List selectEmpFromDept(Long did){
		String sql="select new map(dept.ideptid as ideptid,dept.szdeptname as szdeptname,emp.iemployeeid as iemployeeid,emp.szemployeename as szemployeename) from Esfdepttab dept,Esfemployeetab emp where dept.ideptid="+did+" and dept.ideptid=emp.ideptid";
		return this.find(sql);
	}
	//查询部门下是否还有下属部门
	public List selectNextDept(Long deptid){
		String sql=" from Esfdepttab where iparentid="+deptid;
		return this.find(sql);
	}
	
	//添加一新部门
	public void insertDept(Esfdepttab dept,Long companyId,Long parentid,List postsList) {
		// TODO Auto-generated method stub
		if(parentid == null|| parentid == 0){//添加顶级部门
			dept.setIdeptid(this.getMaxPk("ideptid", "Esfdepttab")+1);
			dept.setIrootid(dept.getIdeptid());//根节点
			dept.setIparentid(new Long(0));//父节点
			dept.setIlevel(new Long(1));//所在的层级
			String sql="select max(ilevelsequence) from Esfdepttab where ilevel=1";
			List llist=this.find(sql);
			dept.setIlevelsequence(new Long(llist.get(0).toString())+1);//层序号
			
			dept.setIcompanyinfoid(companyId);
			dept.setSzinnerid(dept.getIdeptid()+"");//隶属关系ID
			dept.setSzinnercode(dept.getIdeptid()+"");//隶属关系代码
			dept.setSzinnername("总部门");//隶属关系代码
			
	//		dept.setSzdeptcode(dept.getIdeptid()+"");//部门代码
		//	dept.setBisleaf("0");
			dept.setIversion(new Long(1));
			dept.setByisuse(new Long(1));//使用状态
			dept.setBisdelete("0");//是否删除
			dept.setBishistory("0");//是否历史数据
			
		}else{//添加下属部门
//			System.out.println("add next dept ===");
			Esfdepttab parentdept=(Esfdepttab)this.get(Esfdepttab.class, parentid);
			dept.setIdeptid(this.getMaxPk("ideptid", "Esfdepttab")+1);
			dept.setIrootid(parentdept.getIrootid());//根节点
			dept.setIparentid(parentdept.getIdeptid());//父节点
			dept.setIlevel(parentdept.getIlevel()+1);//所在的层级
			String sql="select max(ilevelsequence) from Esfdepttab where ilevel="+dept.getIlevel();
			List llist=this.find(sql);
			if(llist==null||llist.size()<1||llist.get(0)==null){
			    dept.setIlevelsequence(1l);//层序号
			}else{
			    dept.setIlevelsequence(new Long(llist.get(0).toString())+1);//层序号
			}
			
			
			dept.setIcompanyinfoid(companyId);
			dept.setSzinnerid(parentdept.getSzinnerid()+","+dept.getIdeptid());//隶属关系ID
			dept.setSzinnercode(dept.getIdeptid()+"");//隶属关系代码
			dept.setSzinnername("下属部门");//隶属关系代码
			
	//		dept.setSzdeptcode(dept.getIdeptid()+"");//部门代码
		//	dept.setBisleaf("0");
			dept.setIversion(new Long(1));
			dept.setByisuse(new Long(1));//使用状态
			dept.setBisdelete("0");//是否删除
			dept.setBishistory("0");//是否历史数据
			
		}
		
//		Galcompanyinfotab comp=(Galcompanyinfotab)this.get(Galcompanyinfotab.class, dept.getIcompanyinfoid());
//		dept.setGalcompanyinfotab(comp);
		this.save(dept);
		
		//在关系表中添加对应关系
		if(postsList!=null&&postsList.size()!=0){
			for(int i=0;i<postsList.size();i++){
				
				Esfdeptpoststab dp=new Esfdeptpoststab();
				dp.setIdeptpostsid(this.getMaxPk("ideptpostsid", "Esfdeptpoststab")+1);
				dp.setIdeptid(dept.getIdeptid());
				dp.setIpostsid(new Long(postsList.get(i).toString()));
				dp.setIversion(new Long(1));
				
				this.save(dp);
			}
		}
	}
	//修改部门信息
	/**
	 * @version  功能：修改部门信息
	 * @parameter dept :修改后的部门信息<BR/>
	 *            id :要修改的部门ID
	 */
	public void update(Esfdepttab dept, Object id, List selList) {
		Esfdepttab sdept=(Esfdepttab)this.get(Esfdepttab.class, id);
		if(sdept!=null){
			sdept.setSzdeptname(dept.getSzdeptname());
			sdept.setByisuse(dept.getByisuse());
			sdept.setBisleaf(dept.getBisleaf());
			sdept.setIversion(sdept.getIversion()+1);
			sdept.setSzmemo(dept.getSzmemo());
			sdept.setSzdeptcode(dept.getSzdeptcode());
			this.update(sdept);
			//陈新浩修改 2012-11-28 部门禁用，底下员工同时禁用
			if(sdept.getByisuse()==0L){
				String sql = " from Esfemployeetab es where es.ideptid = "+id;
				List list = this.find(sql);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						Esfemployeetab es = (Esfemployeetab) list.get(i);
						es.setByisuse(0L);
						this.update(es);
					}
				}
			}
			//修改结束
			//先删除关系表，然后再加进去
			String hsql=" from Esfdeptpoststab where ideptid="+sdept.getIdeptid();
			List dplist=this.find(hsql);
			Esfdeptpoststab eds=null;
			if(dplist!=null&&dplist.size()!=0){
				eds=(Esfdeptpoststab)dplist.get(0);
				for(int i=0;i<dplist.size();i++){
					this.delete((Esfdeptpoststab)dplist.get(i));
				}
			}
			
			for(int i=0;i<selList.size();i++){
				Esfdeptpoststab dp=new Esfdeptpoststab();
				dp.setIdeptpostsid(this.getMaxPk("ideptpostsid", "Esfdeptpoststab")+1);
				dp.setIdeptid(sdept.getIdeptid());
				dp.setIpostsid(new Long(selList.get(i).toString()));
				if(eds==null||eds.getIversion()==null||eds.getIversion().equals("")){
					dp.setIversion(new Long(1));
				}else{
					dp.setIversion(eds.getIversion()+1);
				}
				
				this.save(dp);
			}
		}
	}
	
	//所有部门列表
	public PaginationSupport findPage(Long deptid,Long comid,int pageSize, int startIndex, String url) {
		// TODO Auto-generated method stub
		
		String hsql="select new map(dept.ideptid as ideptid,dept.icompanyinfoid as icompanyinfoid,dept.irootid as irootid,dept.iparentid as iparentid,dept.ilevel as ilevel,dept.ilevelsequence as ilevelsequence,dept.bisleaf as bisleaf,dept.szinnercode as szinnercode,dept.szinnername as szinnername,dept.szdeptcode as szdeptcode,dept.szdeptname as szdeptname,dept.szmemo as szmemo,dept.byisuse as byisuse,dept.iversion as iversion,dept.bisdelete as bisdelete,dept.bishistory as bishistory,com.szcompanyname as szcompanyname) from Esfdepttab dept,Galcompanyinfotab com "+
					" where dept.bisdelete=0 and  dept.iparentid="+deptid+" and dept.icompanyinfoid="+comid+" and com.icompanyinfoid=dept.icompanyinfoid order by dept.ideptid";
		return this.findPage(hsql, pageSize, startIndex, url);
	}
	//显示上一级部门
	public PaginationSupport findPage2(Long levelid,Long comid,int pageSize, int startIndex, String url) {
		String hsql="select new map(dept.ideptid as ideptid,dept.icompanyinfoid as icompanyinfoid,dept.irootid as irootid,dept.iparentid as iparentid,dept.ilevel as ilevel,dept.ilevelsequence as ilevelsequence,dept.bisleaf as bisleaf,dept.szinnercode as szinnercode,dept.szinnername as szinnername,dept.szdeptcode as szdeptcode,dept.szdeptname as szdeptname,dept.szmemo as szmemo,dept.byisuse as byisuse,dept.iversion as iversion,dept.bisdelete as bisdelete,dept.bishistory as bishistory,com.szcompanyname as szcompanyname) from Esfdepttab dept,Galcompanyinfotab com "+
		" where dept.bisdelete=0 and  dept.ilevel="+levelid+" and dept.icompanyinfoid="+comid+" and com.icompanyinfoid=dept.icompanyinfoid order by dept.ideptid";
		
		return this.findPage(hsql, pageSize, startIndex, url);
	}
	//按条件模糊查询 
	public PaginationSupport findPage3(String queryid,String queryMess,Esfdepttab dept,int pageSize, int startIndex, String url) {
		// TODO Auto-generated method stub
		
		String hsql="select new map(dept.ideptid as ideptid,dept.icompanyinfoid as icompanyinfoid,dept.irootid as irootid,dept.iparentid as iparentid,dept.ilevel as ilevel,dept.ilevelsequence as ilevelsequence,dept.bisleaf as bisleaf,dept.szinnercode as szinnercode,dept.szinnername as szinnername,dept.szdeptcode as szdeptcode,dept.szdeptname as szdeptname,dept.szmemo as szmemo,dept.byisuse as byisuse,dept.iversion as iversion,dept.bisdelete as bisdelete,dept.bishistory as bishistory,com.szcompanyname as szcompanyname) from Esfdepttab dept,Galcompanyinfotab com  "+
					" where com.icompanyinfoid=dept.icompanyinfoid and dept.bisdelete=0 and dept.ilevel="+(dept.getIlevel()+1)+" and dept.icompanyinfoid="+dept.getIcompanyinfoid();
		StringBuffer sb=new StringBuffer(hsql);
		if(queryid!=null&&!queryid.equals("")){
			sb.append(" and dept.ideptid="+queryid);
		}
		if(queryMess!=null&&!queryMess.equals("")){
			sb.append(" and dept.szdeptname like '%"+queryMess+"%'");
		}
		sb.append(" order by dept.ideptid");
	
		return this.findPage(sb.toString(), pageSize, startIndex, url);
	}
	/**
	 * 功能 ：  显示对应的岗位（是景区企业对应景区岗位；平台企业对应平台岗位）
	 * @param 公司ID
	 */
	public List allPosts(String comid){
		String sql=" from Esppoststab where byisuse=1 and posttype='"+comid+"'";
	
		return this.find(sql);
	}
	/**
	 * 按部门ID列出关系表中的数据
	 */
	public List allPostsById(Long deptid){
		String sql="";
		if(deptid!=null&&deptid!=0){
			sql=" from Esfdeptpoststab where ideptid="+deptid;
		}
		return this.find(sql);
	}
	public List selectDeptByName(String dname) {
		// TODO Auto-generated method stub
		String sql=" from Esfdepttab where szdeptname='"+dname+"'";
		return this.find(sql);
	}
}
