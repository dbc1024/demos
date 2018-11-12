package com.ectrip.ec.order.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.order.dao.idao.IOrderModifyDao;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderModifyDao extends GenericDao implements IOrderModifyDao {

	public List queryOrderModify(TOrder tOrder) {
		String hql = "select new map(t.orzj as orzj,t.dtenddate as dtenddate,t.isi as isi,t.ish as ish,t.id.orid as orid,t.id.iscenicid as iscenicid,t.usid as usid,t.ddzt as ddzt,t.dyusid as dyusid,t.scenictype as scenictype,e.szscenicname as szscenicname,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.mont as mont,t.zfmont as zfmont,t.orph as orph,t.ornm as ornm,t.orhm as orhm,t.noted as noted,t.notee as notee,t.notea as notea,t.yhamnt  as yhamnt) from TOrder t,Esbscenicareatab e where t.id.orid='"
				+ tOrder.getId().getOrid().trim() + "' and t.id.iscenicid=e.iscenicid";
		return this.find(hql);
	}

	public List<TOrderlist> queryOrderList(String orid, Long iscenicid) {
		String hql = "select new map(tl.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,tl.icrowdkindpriceid as icrowdkindpriceid,tl.icrowdkindid as icrowdkindid,tl.dtstartdate as dtstartdate,tl.dtenddate as dtenddate,tl.pric as pric,tl.numb as numb,tl.yhnumb as yhnumb,tl.amnt as amnt,tl.yhamnt as yhamnt) from TOrderlist tl,Edmtickettypetab edm where tl.itickettypeid=edm.itickettypeid  and tl.id.orid='" + orid + "' and tl.id.iscenicid="
				+ iscenicid;
		List lst=this.find(hql);
		if(lst!=null&&lst.size()>0){
			Map map=null;
			for(int x=0;x<lst.size();x++){
				map=(Map) lst.get(x);
				
				List crowkindList=null;
				
				if(map.get("icrowdkindpriceid")!=null&&!map.get("icrowdkindpriceid").toString().equals("")){
					Edmcrowdkindpricetab crowkind=(Edmcrowdkindpricetab)this.get(Edmcrowdkindpricetab.class, Long.parseLong(map.get("icrowdkindpriceid").toString()));
					
					String sql=" select new map(edk.icrowdkindpriceid as icrowdkindpriceid, esb.szcrowdkindname||'/'||edk.mactualsaleprice as mactualsaleprice) from Edmcrowdkindpricetab edk,Edpcrowdkindtab esb where edk.icrowdkindid=esb.icrowdkindid and edk.itickettypeid="+Long.parseLong(map.get("itickettypeid").toString())+"  and edk.icrowdkindid="+crowkind.getIcrowdkindid()+"  and edk.note1='"+crowkind.getNote1()+"' and edk.ibusinessid="+crowkind.getIbusinessid();
					crowkindList=this.find(sql);
					
				}
				
				map.put("crowkindList", crowkindList);
			}
		}
		return lst;
	}

}
