package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.employee.Esppoststab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.salesmanager.Osppostsaleslimitstab;
import com.ectrip.ticket.salesmanager.dao.IPostSalesLimitsDAO;

public class PostSalesLimitsDAO extends GenericDao implements
		IPostSalesLimitsDAO {

	/**
	 *  分页查询岗位权限列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pagesize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-10-5
	 */
	public PaginationSupport getPostSaleLimitsList(int page,int pagesize,String url){
		PaginationSupport ps = null;
		String hsql="select new map(sal.ipostsaleslimitsid as ipostsaleslimitsid,sal.icrowdkindpriceid as icrowdkindpriceid,sal.ipostsid as ipostsid,post.szpostsname as strpost) from Osppostsaleslimitstab sal,Esppoststab post,Edmcrowdkindpricetab pri where sal.ipostsid = post.ipostsid and sal.icrowdkindpriceid =pri.icrowdkindpriceid";
		ps = this.findPage(hsql, pagesize, page, url);

		List list = ps.getItems();

		if (list != null && list.size() > 0) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if( map.get("icrowdkindpriceid")!=null){//售价编号
					Object actcontent = map.get("icrowdkindpriceid");

					String hsqls="select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
							"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
							" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+actcontent ;
					List strlist = this.find(hsqls);
					if(strlist.size()>=1){
						for (int j = 0; j< strlist.size(); j++) {
							Object[] a =(Object[]) strlist.get(j);
							map.put("strcrowdkindprice", a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//价格组合名称
						}
					}
				}
			}
		}

		return ps;
	}
	/**
	 * 根据岗销售编号得到岗位销售信息
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsaleId
	 * @return
	 * return:Osppostsaleslimitstab
	 * Date:2011-10-5
	 */
	public Osppostsaleslimitstab queryPostSaleLimits(Long postsaleId){
		Osppostsaleslimitstab postsale = null;
		//岗位销售权限信息
		postsale =(Osppostsaleslimitstab) this.get(Osppostsaleslimitstab.class, postsaleId);
		if(postsale!=null){
			//岗们信息
			Esppoststab posts = (Esppoststab)this.get(Esppoststab.class, postsale.getIpostsid());
			if(posts!=null){
				postsale.setStrpost(posts.getSzpostsname());
			}
			String hsql=" select pri.icrowdkindpriceid,prd.sztickettypename,cro.szcrowdkindname,bui.szbusinessname,pri.startdata,pri.enddata from Edmcrowdkindpricetab pri,Edmtickettypetab prd,Edpcrowdkindtab cro, " +
					"Edmbusinesstab bui  where pri.itickettypeid=prd.itickettypeid and pri.icrowdkindid=cro.icrowdkindid " +
					" and pri.ibusinessid=bui.ibusinessid and prd.bycategorytype in(select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where  sys1.id.pmky='PDTP' and (sys1.id.pmcd='01'or spmcd='01'))) and pri.icrowdkindpriceid="+postsale.getIcrowdkindpriceid() ;

			List list = this.find(hsql);
			if(list.size()>=1){
				for (int i = 0; i < list.size(); i++) {
					Object[] a =(Object[]) list.get(i);
					postsale.setStrcrowdkindprice(a[1]+"_"+a[2]+"_"+a[3]+"_"+a[4]+"_"+a[5]);//价格组合名称
				}
			}
		}
		return postsale;
	}

	/**
	 * 岗位销售权限增加
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsalelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void insertPostSaleLimits(List postsalelimitslist,Syslog syslog){

		Osppostsaleslimitstab sales = (Osppostsaleslimitstab)postsalelimitslist.get(0);

		String sql=" from Osppostsaleslimitstab where ipostsid="+sales.getIpostsid();
		List salelist = this.find(sql);
		if(salelist.size()>=1){
			Osppostsaleslimitstab salelimit=null;
			for (int i = 0; i < salelist.size(); i++) {
				salelimit =(Osppostsaleslimitstab) salelist.get(i);
				this.delete(salelimit);
			}
		}

		for (int i = 0; i < postsalelimitslist.size(); i++) {

			Osppostsaleslimitstab postlimit = (Osppostsaleslimitstab)postsalelimitslist.get(i);

			long maxsale = this.getMaxPk("ipostsaleslimitsid", "Osppostsaleslimitstab");
			postlimit.setIpostsaleslimitsid(maxsale+1);
			this.save(postlimit);//保存

			Syslog syslogs = new Syslog();
			syslogs.setIemployeeid(syslog.getIemployeeid());
			syslogs.setStlg("0067");
			syslogs.setBrief("岗位销售权限增加：" + postlimit.getIpostsaleslimitsid() );
			syslogs.setNote("岗位销售权限增加：" + postlimit.getIpostsaleslimitsid()+",价格："+postlimit.getIcrowdkindpriceid()+",岗位："+postlimit.getIpostsid());
			syslogs.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslogs.setLogid(logid + 1);
			this.save(syslogs);
		}
	}
	/**
	 * 岗位销售权限修改
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsalelimits
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void updatePostSaleLimits(List postsalelimitslist,Syslog syslog){


		for (int i = 0; i < postsalelimitslist.size(); i++) {

			Osppostsaleslimitstab postlimit = (Osppostsaleslimitstab)postsalelimitslist.get(i);

			long maxsale = this.getMaxPk("ipostsaleslimitsid", "Osppostsaleslimitstab");
			postlimit.setIpostsaleslimitsid(maxsale+1);

			String hsql=" from Osppostsaleslimitstab where icrowdkindpriceid="+postlimit.getIcrowdkindpriceid()+" and ipostsid="+postlimit.getIpostsid();
			List winlist = this.find(hsql);
			if(winlist!=null && winlist.size()>=1){
				continue;
			}else{
				this.save(postlimit);//保存

				Syslog syslogs = new Syslog();
				syslogs.setIemployeeid(syslog.getIemployeeid());
				syslogs.setStlg("0068");
				syslogs.setBrief("岗位销售权限修改：" + postlimit.getIpostsaleslimitsid() );
				syslogs.setNote("岗位销售权限修改：" + postlimit.getIpostsaleslimitsid()+",价格："+postlimit.getIcrowdkindpriceid()+",岗位："+postlimit.getIpostsid());
				syslogs.setLogdatetime(Tools.getDayTimes());
				Long logid = this.getMaxPk("logid", "Syslog");
				syslogs.setLogid(logid + 1);
				this.save(syslogs);
			}
		}
	}
	/**
	 * 岗位销售权限删除
	 * Describe:
	 * @auth:huangyuqi
	 * @param iosppostsalelimitsid
	 * @param syslog
	 * return:void
	 * Date:2011-10-5
	 */
	public void deletePostSaleLimits(Long iosppostsalelimitsid,Syslog syslog){

		Osppostsaleslimitstab ostsalelimits=(Osppostsaleslimitstab)this.get(Osppostsaleslimitstab.class, iosppostsalelimitsid);
		syslog.setStlg("0069");
		syslog.setBrief("岗位销售权限：" + ostsalelimits.getIpostsaleslimitsid() );
		syslog.setNote("岗位销售权限删除：" + ostsalelimits.getIpostsaleslimitsid() );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);

		this.deleteByKey(Osppostsaleslimitstab.class, iosppostsalelimitsid);
	}

	/**
	 * 根据岗销售编号得到岗位销售信息列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param postsaleId
	 * @return
	 * return:Osppostsaleslimitstab
	 * Date:2011-10-5
	 */
	public List queryPostSaleLimitsList(Long postsaleId){
		List list = new ArrayList();
		Osppostsaleslimitstab postlimit =  this.queryPostSaleLimits(postsaleId);
		String sql=" from Osppostsaleslimitstab where ipostid="+postlimit.getIpostsid();
		list = this.find(sql);
		return list;
	}

}

