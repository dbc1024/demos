package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.provider.dao.IBusinessDAO;
@Repository
public class BusinessDAO extends GenericDao implements IBusinessDAO {

	/**
	 * 业务类型列表 
	 * Describe:
	 * @auth:huangyuqi
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url地址
	 * @param condition查询条件
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport getBusinessAllList(int page,int pageSize,String url,String condition){
		PaginationSupport ps = null;
		String hsql="select new map(b.ibusinessid as ibusinessid,b.szbusinesscode as szbusinesscode,b.szbusinessname as szbusinessname,b.businesstype as businesstype,b.isregister as isregister,b.bymarketingway as bymarketingway,b.bywithmember as bywithmember,b.byconfirmemberway as byconfirmemberway,b.btouristsaddr as btouristsaddr,b.bregsalesman as bregsalesman,b.bteamnature as bteamnature,b.bselfcollarselling as bselfcollarselling,b.bticketnounited as bticketnounited,b.byisuse as byisuse ) from Edmbusinesstab b where 1=1 ";
		if(condition!=null && !"".equals(condition)){
			hsql+=" and b.szbusinessname like '%"+condition+"%' ";
		}
		hsql+=" order by b.ibusinessid,b.szbusinesscode ";
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
	/**
	 * 根据业务类型编号得到业务类型对象
	 * Describe:
	 * @auth:huangyuqi
	 * @param businessId
	 * @return
	 * return:Edmbusinesstab
	 * Date:2011-9-29
	 */
	public Edmbusinesstab queryBusiness(Long businessId){
		Edmbusinesstab business=null;
		business = (Edmbusinesstab)this.get(Edmbusinesstab.class, businessId);
		if(business!=null){
			String btype = business.getBusinesstype();//业务种类
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmky("BUTP");
			sysid.setPmcd(btype);
			Sysparv5 sysparv5 = (Sysparv5)this.get(Sysparv5.class, sysid);
			if(sysparv5!=null){
				business.setStrbusinesstype(sysparv5.getPmva());
			}
		}
		return business;
	}
	/**
	 *根据业务类型判断它是否被售价表用到
	 * Describe:
	 * @auth:huangyuqi
	 * @param businessId业务种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean businessIsuse(Long businessId){
		boolean isuse=false;
		int num = 0;
		//查询售价表中是否存在数据
		String hsql1=" from Edmcrowdkindpricetab where ibusinessid="+businessId;
		List list1 = this.find(hsql1);
		if(list1!= null && list1.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		
		String hsql2=" from Stssalesvouchertab where ibusinessid="+businessId;
		List list2 = this.find(hsql2);
		if(list2!= null && list2.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		
		String hsql3=" from Stssoldtickettab where ibusinessid="+businessId;
		List list3 = this.find(hsql3);
		if(list3!= null && list3.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		
		String hsql4=" from TOrder where ibusinessid="+businessId;
		List list4 = this.find(hsql4);
		if(list4!= null && list4.size()>0){
			num=num+1;
		}else{
			num = num+0;
		}
		
		String hsql5=" from YOrder where ibusinessid="+businessId;
		List list5 = this.find(hsql4);
		if(list5!= null && list5.size()>0){
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
	 * 查出所有业务类型
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List businessList(){
		List list = new ArrayList();
		String hsql=" from Edmbusinesstab where byisuse=1";
		list = this.find(hsql);
		return list;
	}
}

