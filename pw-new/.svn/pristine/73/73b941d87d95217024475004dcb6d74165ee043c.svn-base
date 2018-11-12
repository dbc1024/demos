package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.model.provider.Ordercs;
import com.ectrip.ticket.salesmanager.dao.IOrdercsDAO;

@Repository
public class OrdercsDAO extends GenericDao implements IOrdercsDAO {

	public List queryordercsList(Long ibusinessid) {
		List list = this.find(" from Ordercs where ibusinessid=" + ibusinessid);
		return list;
	}

	public Ordercs queryone(Long seq) {
		Ordercs cs= (Ordercs) this.get(Ordercs.class, seq);
		if(cs!=null){
			Edmbusinesstab eb=(Edmbusinesstab)this.get(Edmbusinesstab.class, cs.getIbusinessid());
			cs.setSzbusinessname(eb.getSzbusinessname());
		}
		return cs;
	}

	public List queryordercs(Long ibusinessid, String ecs) {
		List list = this.find(" from Ordercs where ibusinessid=" + ibusinessid
				+ " and ecs='" + ecs + "'");
		return list;
	}

	public List queryeditordercs(Long ibusinessid, String ecs, Long seq) {
		List list = this.find(" from Ordercs where ibusinessid=" + ibusinessid
				+ " and ecs='" + ecs + "' and seq!=" + seq);
		return list;
	}


	/**
	 * 查看产品价格列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId产品编号
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url访问地址
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-30
	 */
	public PaginationSupport getordercsList(Long ibusinessid,int page,int pageSize,String url){
		PaginationSupport ps =null;
		String hsql=" select new map("
				+ " cs.seq as seq, cs.ibusinessid as ibusinessid, cs.zcs as zcs, cs.ecs as ecs, cs.isequence as isequence, cs.byisuse as byisuse, "
				+ " ed.szbusinessname as szbusinessname"
				+ " ) "
				+ " from Ordercs cs, Edmbusinesstab ed "
				+ " where ed.ibusinessid=cs.ibusinessid ";
		
		if(null!=ibusinessid) {
			hsql= hsql + " and cs.ibusinessid="+ ibusinessid;
		}
		
		hsql= hsql + " order by cs.ibusinessid, cs.isequence";
		
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
	
	
	
	
	public PaginationSupport getordercsviewlist(int page,int pageSize,String url){
		PaginationSupport ps =null;
		String hsql=" select new map(cs.seq as seq,cs.ibusinessid as ibusinessid,cs.zcs as zcs,cs.ecs as ecs,cs.isequence as isequence ,cs.byisuse as byisuse,ed.szbusinessname as szbusinessname) from Ordercs cs,Edmbusinesstab ed where ed.ibusinessid=cs.ibusinessid  order by cs.ibusinessid,cs.isequence";
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
}
