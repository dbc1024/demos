package com.ectrip.ec.order.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.enums.ZJTP;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.order.dao.idao.IRealnameDao;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;

@Repository
public class RealnameDao extends GenericDao implements IRealnameDao {
	
	@Autowired
	private TicketService ticketService;
	
	
	
	/**
	 * *
	 * Describe:实名制列表
	 * @see com.ectrip.order.dao.idao.IRealnameDao#realnameList(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param orid	订单号
	 * @param usid	用户ID
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public PaginationSupport realnameList(String orid,String usid,int pageSize, int startIndex, String url){
		StringBuffer sql = new StringBuffer();
		
//		sql.append(" select distinct new map(tr.seq as seq,tr.orid as orid,t.id.iscenicid as iscenicid,sc.szscenicname as szscenicname,tr.itickettypeid as itickettypeid,tk.sztickettypename as sztickettypename,tr.icrowdkindid as icrowdkindid,ck.szcrowdkindname as szcrowdkindname,tr.cname as cname,sv.pmva as pmva,tr.idcard as idcard,tr.ischild as ischild,tr.mbnumber as mbnumber,t.ddzt as ddzt) ");
//		sql.append(" from TRealname tr,Esbscenicareatab sc,Edmtickettypetab tk,Edpcrowdkindtab ck,TOrder t,Sysparv5 sv ");
//		sql.append(" where tr.orid = t.id.orid and t.id.iscenicid = sc.iscenicid and tr.itickettypeid = tk.itickettypeid and tr.icrowdkindid = ck.icrowdkindid and sv.id.pmky = 'ZJTP' and tr.zjtp = sv.id.pmcd");
		
//		sql.append(" select distinct new map("
//				+ " tr.seq as seq, tr.orid as orid, tr.itickettypeid as itickettypeid, tr.icrowdkindid as icrowdkindid, tr.cname as cname, "
//				+ " tr.idcard as idcard, tr.ischild as ischild, tr.mbnumber as mbnumber, "
//				+ " t.id.iscenicid as iscenicid, t.ddzt as ddzt,"
//				+ " tk.sztickettypename as sztickettypename, "
//				+ " ck.szcrowdkindname as szcrowdkindname, "
//				+ " sc.szscenicname as szscenicname,  "
//				+ " sv.pmva as pmva"
//				+ ")"
//				+ " from TRealname tr, TOrder t, Esbscenicareatab sc, Edmtickettypetab tk, Edpcrowdkindtab ck, Sysparv5 sv "
//				+ " where tr.orid=t.id.orid and t.id.iscenicid=sc.iscenicid "
//				+ " and tr.itickettypeid=tk.itickettypeid "
//				+ " and tr.icrowdkindid=ck.icrowdkindid "
//				+ " and sv.id.pmky='ZJTP' and tr.zjtp = sv.id.pmcd");
		
		sql.append(" select distinct new map("
				+ " tr.seq as seq, tr.orid as orid, tr.itickettypeid as itickettypeid, tr.icrowdkindid as icrowdkindid, tr.cname as cname, "
				+ " tr.idcard as idcard, tr.ischild as ischild, tr.mbnumber as mbnumber, tr.zjtp as zjtp,"
				+ " t.id.iscenicid as iscenicid, t.ddzt as ddzt"
				+ " )"
				+ " from TRealname tr, TOrder t"
				+ " where tr.orid=t.id.orid");
		
    	
		if(orid!=null&&!orid.equals("")){
			sql.append(" and t.id.orid='"+orid+"'");
		}
		if(usid!=null&&!usid.equals("")){
			sql.append(" and t.usid='"+usid+"'");
		}
		PaginationSupport ps = this.findPage(sql.toString(), pageSize, startIndex, url);
		List<Map> list = ps.getItems();
		if(list != null && !list.isEmpty()){
			
			StringBuilder iscenicidS = new StringBuilder();
			StringBuilder itickettypeidS = new StringBuilder();
			StringBuilder icrowdkindidS = new StringBuilder();
			
			for(Map map : list){
				map.put("idcard", StringUtil.markIdNumber((String)map.get("idcard")));
				
				
				//封装其他服务的查询条件
				String iscenicid = map.get("iscenicid").toString();
				iscenicidS.append(iscenicid+ ",");
				String itickettypeid = map.get("itickettypeid").toString();
				itickettypeidS.append(itickettypeid+ ",");
				String icrowdkindid = map.get("icrowdkindid").toString();
				icrowdkindidS.append(icrowdkindid+ ",");
			}
			iscenicidS.deleteCharAt(iscenicidS.length() - 1);
			itickettypeidS.deleteCharAt(itickettypeidS.length() - 1);
			icrowdkindidS.deleteCharAt(icrowdkindidS.length() - 1);
			
			
//			, Esbscenicareatab sc, Edmtickettypetab tk, Edpcrowdkindtab ck, Sysparv5 sv 
			
//			+ " tk.sztickettypename as sztickettypename, "
//			+ " ck.szcrowdkindname as szcrowdkindname, "
//			+ " sc.szscenicname as szscenicname,  "
//			+ " sv.pmva as pmva"
			
//			 and t.id.iscenicid=sc.iscenicid "
//			 and tr.itickettypeid=tk.itickettypeid "
//			 and tr.icrowdkindid=ck.icrowdkindid "
//			 and sv.id.pmky='ZJTP' and tr.zjtp = sv.id.pmcd
			
			
			//根据id集合获取服务商列表
	    	List<Map> providerList = ticketService.getProvidersByIds(iscenicidS.toString());
	    	List<Map> ticketTypeList = ticketService.getTicketTypeListByIds(itickettypeidS.toString());
	    	List<Map> crowdKindList = ticketService.crowdKindListByIds(icrowdkindidS.toString());
	    	
	    	for(Map map : list){
				
	    		//证件类型赋值
	    		String zjtp = map.get("zjtp").toString();
	    		String pmva = ZJTP.getPmvaByPmcd(zjtp);
	    		map.put("pmva", pmva);
	    		
	    		//服务商名称赋值
	    		String iscenicid = map.get("iscenicid").toString();
	    		for (Map provider : providerList) {
	    			String iscenicid1 = provider.get("iscenicid").toString();
	    			if(iscenicid.equals(iscenicid1)) {
	    				String szscenicname = provider.get("szscenicname").toString();
	    				map.put("szscenicname", szscenicname);
	    			}
				}
	    		
	    		//票种名称赋值
	    		String itickettypeid = map.get("itickettypeid").toString();
	    		for (Map ticketType : ticketTypeList) {
	    			String itickettypeid1 = ticketType.get("itickettypeid").toString();
	    			if(itickettypeid.equals(itickettypeid1)) {
	    				String sztickettypename = ticketType.get("sztickettypename").toString();
	    				map.put("sztickettypename", sztickettypename);
	    			}
				}
	    		
	    		//人群种类名称赋值
	    		String icrowdkindid = map.get("icrowdkindid").toString();
	    		for (Map crowdKind : crowdKindList) {
	    			String icrowdkindid1 = crowdKind.get("icrowdkindid").toString();
	    			if(icrowdkindid.equals(icrowdkindid1)) {
	    				String szcrowdkindname = crowdKind.get("szcrowdkindname").toString();
	    				map.put("szcrowdkindname", szcrowdkindname);
	    			}
				}
	    		
			}
	    	
		}
		return ps;
	}
	/**
	 * *
	 * Describe:删除实名制信息
	 * @see com.ectrip.order.dao.idao.IRealnameDao#delRealname(java.lang.Long)
	 * @param seq
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public void delRealname(Long seq){
		TRealname realname = (TRealname) this.get(TRealname.class, seq);
		this.delete(realname);
	}
	/**
	 * *
	 * Describe:显示实名制信息
	 * @see com.ectrip.order.dao.idao.IRealnameDao#showRealname(java.lang.Long)
	 * @param seq
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public Map showRealname(Long seq){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct new map(tr.seq as seq,tr.orid as orid,tr.iscenicid as iscenicid,sc.szscenicname as szscenicname,tr.itickettypeid as itickettypeid,tk.sztickettypename as sztickettypename,tr.icrowdkindid as icrowdkindid,ck.szcrowdkindname as szcrowdkindname,tr.cname as cname,tr.zjtp as zjtp,sv.pmva as pmva,tr.idcard as idcard,tr.ischild as ischild,tr.mbnumber as mbnumber,t.ddzt as ddzt) ");
		sql.append(" from TRealname tr,Esbscenicareatab sc,Edmtickettypetab tk,Edpcrowdkindtab ck,TOrder t,Sysparv5 sv ");
		sql.append(" where tr.orid = t.id.orid and tr.iscenicid = sc.iscenicid and tr.itickettypeid = tk.itickettypeid and tr.icrowdkindid = ck.icrowdkindid and sv.id.pmky = 'ZJTP' and tr.zjtp = sv.id.pmcd and tr.seq="+seq);
		List list = this.find(sql.toString());
		Map map = new HashMap();
		if(list!=null&&list.size()>0){
			map = (Map) list.get(0);
		}
		return map;
	}
	/**
	 * *
	 * Describe:更新实名制
	 * @see com.ectrip.order.dao.idao.IRealnameDao#updateRealname(com.ectrip.model.order.TRealname)
	 * @param trealname
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public void updateRealname(TRealname trealname){
		if(trealname.getIschild()==0l){
			trealname.setZjtp("01");
		}
		String idcard = trealname.getIdcard().toUpperCase();
		trealname.setIdcard(idcard);
		this.update(trealname);
	}
	/**
	 * *
	 * Describe:计算需实名制的票数量
	 * @see com.ectrip.order.dao.idao.IRealnameDao#countTickets(java.lang.String)
	 * @param orid	订单号
	 * @param usid	用户ID
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public Long countTickets(String orid,String usid){
		long num = 0L;
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct tl.id.iscenicid,tl.itickettypeid,tl.icrowdkindid,tl.numb,tl.icrowdkindpriceid from TOrderlist tl, TOrder t");
		sql.append(" where tl.id.orid = t.id.orid and t.scenictype = '01' and tl.id.orid='"+orid+"' ");
		if(usid!=null&&!usid.equals("")){
			sql.append(" and t.usid='"+usid+"'");
		}
		System.out.println(sql.toString());
		List list = this.find(sql.toString());
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] o = (Object[]) list.get(i);
				// 9-22 修改
				Edmcrowdkindpricetab price=(Edmcrowdkindpricetab) get(Edmcrowdkindpricetab.class, Long.parseLong(o[4].toString()));
				if(price!=null&&price.getIpeoplenumrange()!=null&&price.getIpeoplenumrange().intValue()==1){
					num+=new Long(o[3].toString());
				}
			}
		}
		return num;
	}
	/**
	 * *
	 * Describe:计算已实名制的票数量
	 * @see com.ectrip.order.dao.idao.IRealnameDao#countRealnames(java.lang.String)
	 * @param orid	订单号
	 * @param usid	用户ID
	 * @param iscenicid
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public Long countRealnames(String orid,String usid,Long iscenicid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select distinct t.seq from TRealname t,TOrder to where t.orid='"+orid+"' and t.orid = to.id.orid ");
		if(usid!=null&&!usid.equals("")){
			sql.append(" and to.usid='"+usid+"' ");
		}
		if(iscenicid!=null&&iscenicid!=0L){
			sql.append(" and t.iscenicid = "+iscenicid);
		}
		List list = this.find(sql.toString());
		if(list!=null){
			return new Long(list.size());
		}
		return -1L;
	}
	/**
	 * *
	 * Describe:获取未实名制的列表
	 * @see com.ectrip.order.dao.idao.IRealnameDao#newRealnames(java.lang.String, java.lang.String)
	 * @param orid
	 * @param usid
	 * @return
	 * @author chenxinhao
	 * Date:2012-10-23
	 */
	public List<TOrderlist> newRealnames(String orid,String usid){
		List<TOrderlist> tlist = new ArrayList<TOrderlist>();
//		String sql = new String();
		StringBuffer sql = new StringBuffer();
		String hsql = "";
		sql.append(" select distinct new map(tl.id.orderlistid as orderlistid,tl.id.orid as orid,tl.id.iscenicid as iscenicid,tl.itickettypeid as itickettypeid,tl.icrowdkindid as icrowdkindid,tl.numb as numb,tl.icrowdkindpriceid as icrowdkindpriceid) from TOrderlist tl, TOrder t");
		sql.append(" where tl.id.orid = t.id.orid and t.scenictype = '01' and tl.id.orid='"+orid+"' ");
		if(usid!=null&&!usid.equals("")){
			sql.append(" and t.usid='"+usid+"'");
		}
		List list = this.find(sql.toString());
		if(list !=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Map map = (Map) list.get(i);
				TOrderlist torderlist = new TOrderlist();
				try {
					BeanUtils.populate(torderlist, map);
					TOrderlistId torderlistId = new TOrderlistId(Long.parseLong(torderlist.getOrderlistid()), torderlist.getOrid(), Long.parseLong(torderlist.getIscenicid()));
					torderlist.setId(torderlistId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Edmcrowdkindpricetab price=(Edmcrowdkindpricetab) get(Edmcrowdkindpricetab.class, torderlist.getIcrowdkindpriceid());
				if(price!=null&&price.getIpeoplenumrange()!=null&&price.getIpeoplenumrange().intValue()==1){
					hsql = " from TRealname t where t.itickettypeid = "+torderlist.getItickettypeid()+" and t.iscenicid ="+torderlist.getId().getIscenicid()+" and t.icrowdkindid ="+torderlist.getIcrowdkindid()+" and t.id.orid = '"+torderlist.getId().getOrid()+"'";
					List list2 = this.find(hsql);
					if(list2==null||list2.size()==0){//计算为登记实名制的数量
						tlist.add(torderlist);
					}else if(torderlist.getNumb()>list2.size()){
						torderlist.setNumb(torderlist.getNumb()-list2.size());
						tlist.add(torderlist);
					}
				}
				
			}
		}
//		sql = "select distinct t.id.iscenicid as iscenicid from TOrder t,Hotelprovider h where t.scenictype = '01' and t.id.iscenicid = h.iscenicid and h.inoteger2 != 0 and t.id.orid='"+orid+"' ";
//		if(usid!=null&&!usid.equals("")){
//			sql += " and t.usid='"+usid+"' ";
//		}
//		list = this.find(sql);
//		StringBuffer iscenicids = new StringBuffer();
//		if(list!=null&&list.size()>0){//订单中要登记实名制的服务商
//			iscenicids.append("(");
//			for(int i=0;i<list.size();i++){
//				String iscenicid = list.get(i).toString();
//				iscenicids.append("'"+iscenicid+"'");
//				if(i==list.size()-1){
//					iscenicids.append(")");
//				}else{
//					iscenicids.append(",");
//				}
//			}
//			sql = " from TOrderlist t where t.id.iscenicid in "+iscenicids.toString()+" and t.id.orid = '"+orid+"'";
//			list = this.find(sql);
//			if(list!=null&&list.size()>0){//根据服务商查找TOrderlist
//				for(int i = 0;i<list.size();i++){
//					TOrderlist torderlist = (TOrderlist) list.get(i);
//					sql = " from TRealname t where t.itickettypeid = "+torderlist.getItickettypeid()+" and t.iscenicid ="+torderlist.getId().getIscenicid()+" and t.icrowdkindid ="+torderlist.getIcrowdkindid()+" and t.id.orid = '"+torderlist.getId().getOrid()+"'";
//					List list2 = this.find(sql);
//					if(list2==null||list2.size()==0){//计算为登记实名制的数量
//						tlist.add(torderlist);
//					}else if(torderlist.getNumb()>list2.size()){
//						torderlist.setNumb(torderlist.getNumb()-list2.size());
//						tlist.add(torderlist);
//					}
//				}
//			}
//		}
		return tlist;
	}
	
	/**
     * *
     * Describe:保存实名制
     * @see com.ectrip.book.shopcart.dao.idao.IShopCartDAO#saveRealname(java.util.List)
     * @param list
     * @author chenxinhao
     * Date:2012-10-16
     */
    public void saveRealname(List<TRealname> list){
    	for(int i=0;i<list.size();i++){
    		try {
        		TRealname tRealname=new TRealname();
				long seq = this.getSequenceId("realname_sequence");
	    		tRealname=(TRealname) list.get(i);
	    		tRealname.setSeq(seq);
	    		String idcard = tRealname.getIdcard().toUpperCase();
	    		tRealname.setIdcard(idcard);
	    		this.save(tRealname);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    /**
     * *
     * Describe:订单中已实名制的信息
     * @see com.ectrip.order.dao.idao.IRealnameDao#realnameList(java.lang.String, java.lang.Long, java.lang.Long, java.lang.Long)
     * @param orid
     * @param itickettypeid
     * @param iscenicid
     * @param icrowdkindid
     * @return
     * @author chenxinhao
     * Date:2012-11-26
     */
    public List<TRealname> realnameList(String orid,Long itickettypeid,Long iscenicid,Long icrowdkindid){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" from TRealname t where t.orid = '"+orid+"' and t.itickettypeid = "+itickettypeid+" and t.iscenicid = "+iscenicid+" and t.icrowdkindid = "+icrowdkindid);
    	List<TRealname> rlist = this.find(sql.toString()); 
    	return rlist;
    }
    /**
     * *
     * Describe:2次支付保存实名制
     * @see com.ectrip.order.dao.idao.IRealnameDao#saveOrderRealname(java.util.List, java.lang.String)
     * @param list
     * @param orid
     * @author chenxinhao
     * Date:2012-11-26
     */
    public void saveOrderRealname(List<TRealname> list,String orid){
    	String sql = " from TRealname t where t.orid = '"+orid+"'";
    	List realnameList = this.find(sql);
    	if(list!=null&&list.size()>0){
    		for(int i=0;i<realnameList.size();i++){
    			TRealname tRealname = (TRealname) realnameList.get(i);
    			this.delete(tRealname);
    		}
    	}
    	for(int i=0;i<list.size();i++){
    		try {
        		TRealname tRealname=new TRealname();
				long seq = this.getSequenceId("realname_sequence");
	    		tRealname=(TRealname) list.get(i);
	    		tRealname.setSeq(seq);
	    		String idcard = tRealname.getIdcard().toUpperCase();
	    		tRealname.setIdcard(idcard);
	    		this.save(tRealname);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
}

