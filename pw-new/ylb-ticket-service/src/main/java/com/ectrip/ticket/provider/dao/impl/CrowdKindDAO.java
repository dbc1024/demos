package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.provider.dao.ICrowdKindDAO;

@Repository
public class CrowdKindDAO extends GenericDao implements ICrowdKindDAO {
	/**
	 * 人群种类列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param iparentid父编号
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url访问地址
	 * @param condition查询条件
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport getCrowdKindList(Long iparentid,int page,int pageSize,String url,String path,String condition){
		PaginationSupport ps=null;
		String hsql=" from Edpcrowdkindtab where 1=1 ";
		if(condition!=null && !"".equals(condition)){
			hsql+=" and szcrowdkindname like '%"+condition+"%' ";
		}else{
			if(iparentid==null || "".equals(iparentid) || 0==iparentid){
				hsql+=" and ilevel=1 ";
			}else{
				if("up".equals(path)){//上一级
					//查询上一级对象
					Edpcrowdkindtab crowdkind = (Edpcrowdkindtab)this.get(Edpcrowdkindtab.class, iparentid);
					hsql+=" and iparentid="+crowdkind.getIparentid();
				}else{//下一级
					hsql+=" and iparentid = "+iparentid;
				}
				
			}
		}
		hsql+=" order by ilevelsequence,icrowdkindid "; 
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
	/**
	 * 更改层序号
	 * @param ilever 层
	 * @param ileverseq层序号
	 * @param objectId对象id
	 * @param tablename表名
	 */
	public void updateIleverseq(int ilever,int ileverseq,Long objectId,String tablename){
		
		// 先判断此层序号是否已经被用，如果有，则更改些层以上的所有数据
		List isuse = this.getIleverseqList(ilever, ileverseq, objectId,
				tablename);

		if (isuse.size() >= 1) {// 表示已经存在

			String hsql = " from " + tablename + " where ilevel=" + ilever
					+ " and ilevelsequence>=" + ileverseq;
			List list = super.find(hsql);
			if (list.size() >= 1) {
				for (int i = 0; i < list.size(); i++) {
					Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) list.get(0);
					crowdkind.setIlevelsequence(crowdkind.getIlevelsequence() + 1);
					super.update(crowdkind);
				}
			}
		}
	}
	/**
	 * 判断一个层序号是否是被本数据使用
	 * Describe:
	 * @auth:huangyuqi
	 * @param ilever
	 * @param ilerverseq
	 * @param sourceid
	 * @param tablename
	 * @return
	 * return:List
	 * Date:2011-9-29
	 */
	public List getIleverseqList(int ilever, int ilerverseq, Long sourceid,
			String tablename) {
		String sql = " from " + tablename + " where ilevel=" + ilever
				+ " and ilevelsequence=" + ilerverseq;
		List list = this.find(sql);
		if (list.size() >= 1) {
			Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) list.get(0);
			if (sourceid == crowdkind.getIcrowdkindid()) {
				list.remove(0);
			}
		}
		return list;
	}
	
	/**
	 * 判断它是否是最大层级数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param icrowdkindId人群种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean boolMaxIlevel(Long icrowdkindId){
		boolean isuse=false;
		String hsql=" from Edpcrowdkindtab where iparentid="+icrowdkindId;
		List list = this.find(hsql);
		if(list.size()>0){
			isuse=true;
		}
		return isuse;
	}
	
	/**
	 * 判断它在其它地方被使用到
	 * Describe:
	 * @auth:huangyuqi
	 * @param icrowdkindId 人群种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean boolIsuser(Long icrowdkindId){
		boolean isuse = false;
		int num=0;
		
		String hsql1=" from Edmcrowdkindpricetab pric where pric.icrowdkindid="+icrowdkindId;
		List list1 = this.find(hsql1);
		if(list1!=null && list1.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		//优惠方案
		String hsql2=" from Edpofferschemetab where icrowdkindid="+icrowdkindId;
		List list2 = this.find(hsql2);
		if(list2!=null && list2.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		String hsql3=" from Stssoldticketsubtab where icrowdkindid="+icrowdkindId;
		List list3 = this.find(hsql3);
		if(list3!=null && list3.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		String hsql4=" from Stssoldtickettab where icrowdkindid="+icrowdkindId;
		List list4 = this.find(hsql4);
		if(list4!=null && list4.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		String hsql5=" from TOrderlist where icrowdkindid="+icrowdkindId;
		List list5 = this.find(hsql5);
		if(list5!=null && list5.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		String hsql6=" from TZorderlist where icrowdkindid="+icrowdkindId;
		List list6 = this.find(hsql6);
		if(list6!=null && list6.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		String hsql7=" from YOrderlist where icrowdkindid="+icrowdkindId;
		List list7 = this.find(hsql7);
		if(list7!=null && list7.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		if(num>0){
			isuse = true;
		}else{
			isuse = false;
		}
		return isuse;
	}
	
	/**
	 * 查出所有人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List crowdKindList(){
		List list =new ArrayList();
		String hsql=" from Edpcrowdkindtab where byisuse=1 order by ilevelsequence";
		list = this.find(hsql);
		return list;
	}
	@Override
	public List getCrowdKindListByIds(String icrowdkindids) {
		
		String sql = "select distinct new map(kind.icrowdkindid AS icrowdkindid, kind.szcrowdkindname AS szcrowdkindname) from Edpcrowdkindtab kind where kind.icrowdkindid in("+ icrowdkindids +")";
		
		List list = this.find(sql);
		
		return list;
	}

}

