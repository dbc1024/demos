package com.ectrip.ec.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.feign.service.TicketService;

import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.order.dao.idao.ITOrderDAO;

/**
 * 
 * @ClassName: TOrderDAO
 * @Description:
 * @author Dicky
 * @date Oct 11, 2011 2:48:01 PM
 * 
 */
@Repository
public class TOrderDAO extends GenericDao implements ITOrderDAO {
	
	@Autowired
	private TicketService ticketService;
	/**
	 * (?? Javadoc)
	 * <p>
	 * Title: saveTOrder
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param torder
	 * @see com.ectrip.order.dao.idao.ITOrderDAO#saveTOrder(com.ectrip.model.order.TOrder)
	 */
	public void saveTOrder(TOrder torder) {
		this.save(torder);
	}

	/**
	 * (?? Javadoc)
	 * <p>
	 * Title: getTOrderList
	 * </p>
	 * <p>
	 * Description:  TOrder
	 * </p>
	 * 
	 * @param ori
	 * @param iscenicid
	 * @return
	 * @see com.ectrip.order.dao.idao.ITOrderDAO#getTOrderList(java.lang.String, java.lang.String)
	 */
	public List getTOrderList(String orid) {
		
//	    String hql="select new map("
//	    		+ " t.isi as isi, t.ish as ish, t.id.orid as orid, t.id.iscenicid as iscenicid, t.usid as usid, t.ddzt as ddzt, t.dyusid as dyusid,"
//	    		+ " t.scenictype as scenictype, t.dtstartdate as dtstartdate, t.dtenddate as dtenddate,"
//	    		+ " t.mont as mont, t.zfmont as zfmont, t.orph as orph,t.ornm as ornm, t.orhm as orhm, t.noted as noted, t.notee as notee,"
//	    		+ " t.notea as notea, t.yhamnt as yhamnt, t.ischupiao as ischupiao, "
//	    		+ " e.szscenicname as szscenicname"
//	    		+ " ) "
//	    		+ " from TOrder t, Esbscenicareatab e "
//	    		+ " where t.id.orid='"+ orid + "'"
//	    		+ " and t.id.iscenicid=e.iscenicid";
		
		
		String hql="select new map("
	    		+ " t.isi as isi, t.ish as ish, t.id.orid as orid, t.id.iscenicid as iscenicid, t.usid as usid, t.ddzt as ddzt, t.dyusid as dyusid,"
	    		+ " t.scenictype as scenictype, t.dtstartdate as dtstartdate, t.dtenddate as dtenddate,"
	    		+ " t.mont as mont, t.zfmont as zfmont, t.orph as orph,t.ornm as ornm, t.orhm as orhm, t.noted as noted, t.notee as notee,"
	    		+ " t.notea as notea, t.yhamnt as yhamnt, t.ischupiao as ischupiao"
	    		+ " ) "
	    		+ " from TOrder t"
	    		+ " where t.id.orid='"+ orid + "'";
		
		//+ " e.szscenicname as szscenicname"
		//+ " and t.id.iscenicid=e.iscenicid"
		
		List<Map> list = find(hql);
		
		if(list!=null && list.size()>0) {
			
			StringBuilder iscenicidS = new StringBuilder();
			for (Map map : list) {
				
				String iscenicid = map.get("iscenicid").toString();
        		iscenicidS.append(iscenicid+ ",");
			}
			iscenicidS.deleteCharAt(iscenicidS.length() - 1);
			
			//根据id集合获取服务商列表
        	List<Map> providerList = ticketService.getProvidersByIds(iscenicidS.toString());
        	
        	
        	for (Map map : list) {
				String iscenicid = map.get("iscenicid").toString();
        		
        		for (Map provider : providerList) {
                	String iscenicid1 = provider.get("iscenicid").toString();
                	if(iscenicid.equals(iscenicid1)) {
                		String szscenicname = provider.get("szscenicname").toString();
                		map.put("szscenicname", szscenicname);
                	}
    			}
			}
        	
        	
        	
		}
	    
	    return list;
	}
	
	
	
	
	public List getTOrderLists(String orid){
		String hql="select new map(e.szscenicname as szscenicname,t.itickettypeid as itickettypeid,t.starttime as starttime,t.endtime as endtime,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate) from TOrderlist t,Esbscenicareatab e where t.id.orid='"
				+ orid + "' and t.id.iscenicid=e.iscenicid";
		System.out.println("============"+hql.toString());
		return find(hql);
	}
	
	public List getTOrderList(String orid,String iscenicid) {
	    String hql="select new map(t.isi as isi,t.id.orid as orid,t.id.iscenicid as iscenicid,t.usid as usid,t.ddzt as ddzt,t.dyusid as dyusid,t.scenictype as scenictype,e.szscenicname as szscenicname,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.mont as mont,t.zfmont as zfmont,t.orph as orph,t.ornm as ornm,t.orhm as orhm,t.yhamnt as yhamnt) from TOrder t,Esbscenicareatab e where t.id.orid='"
						+ orid + "' and t.id.iscenicid=e.iscenicid and t.id.iscenicid="+iscenicid+"";
	    return find(hql);
	}
	
	 public List<TOrder> getTOrdersByOrid(String orderId){
		 List<TOrder> tl = this.find("from TOrder t where t.id.orid='"+orderId+"' ");
		 return tl;
	 }

	/** 
	 * (?? Javadoc)
	 * <p> 
	 * Title: getTOrder
	 * </p>
	 * <p>
	 * Description:???Torder????????
	 * </p>
	 * 
	 * @param orid
	 * @param iscenicid
	 * @return
	 * @see com.ectrip.order.dao.idao.ITOrderDAO#getTOrder(java.lang.String, java.lang.String)
	 */
	public TOrder getTOrder(String orid, String iscenicid) throws Exception{
		TOrderId id = new TOrderId(orid, new Long(iscenicid));
		return (TOrder) this.get(TOrder.class, id);
	}
	
	 public List<Map> getTOrder(String orid){
		 List list = new java.util.ArrayList<>();
	        String sql = " select cast(t.orid as varchar(17)) as orid,t.iscenicid,t.usid,t.ibusinessid,t.sztravelbillno,t.iregionalid,t.tdlx,cast(t.ddzt as varchar(2)) as ddzt,t.dtenddate,t.dtstartdate,t.dyusid,t.ornm,t.orhm,t.mont,t.yhamnt,t.zfmont,t.ischupiao,t.notea,t.ornote1,t.ornote2,t.ornote3,t.ornote4,t.ornote5,t.ornote6,t.ornote7,t.ornote8,t.ornote9,t.ornote10 from T_order t where t.orid='"
	                + orid+"'";
	        try {
	            list = this.findBySqlToMap(sql);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return list;
	 }

	/**
	 * (?? Javadoc)
	 * <p>
	 * Title: updateTOrder
	 * </p>
	 * <p>
	 * Description:????TOrder
	 * </p>
	 * 
	 * @param torder
	 * @see com.ectrip.order.dao.idao.ITOrderDAO#updateTOrder(com.ectrip.model.order.TOrder)
	 */
	public void updateTOrder(TOrder torder) {
		this.saveOrUpdate(torder);
	}

	public List getRealnemeList(String iscenicid,String idcard) {
		// TODO Auto-generated method stub
		String hql;
		if(!StringUtil.isEmpty(iscenicid))
		{
			hql= "select new map(t.orid as orid,t.idcard as idcard,t.iscenicid as iscenicid) From TRealname t,TOrder to " +
					" where t.idcard='"+idcard+"' and  t.iscenicid="+iscenicid+" and t.orid=to.id.orid and to.dtstartdate<='"+Tools.getDays()+"' " +
					"and to.dtenddate>='"+ Tools.getDays()+"' order by t.orid desc";
		}else
		{
			hql= "select new map(t.orid as orid,t.idcard as idcard,t.iscenicid as iscenicid) From TRealname t,TOrder to " +
					" where t.idcard='"+idcard+"' and t.orid=to.id.orid and to.dtstartdate<='"+Tools.getDays()+"' " +
					"and to.dtenddate>='"+ Tools.getDays()+"' order by t.orid desc";
		}
		return  find(hql);
	}
	
	public String getOrderIdByIdcard(Long iscenicid,String idcard) {
		 //查询是实名制的
        String sql = "select new map(t.id.orid as orid) from TRealname tr,TOrder t where tr.orid = t.id.orid and tr.iscenicid = t.id.iscenicid " +
                " and t.id.iscenicid="+iscenicid+" and t.dtstartdate <='"+Tools.getDays()+"' and t.dtenddate>='"+Tools.getDays()+"' " +
                " and tr.idcard = '"+idcard+"' order by t.id.orid desc";
        List list = this.find(sql);
        if(list != null && !list.isEmpty()){
            for(int i=0;i<list.size();i++){
                Map map = (Map) list.get(i);
                String orid = map.get("orid").toString();
                //出票订单明细表
                String sql1 = "from TOrderlist where id.orid = '"+orid+"' and id.iscenicid="+iscenicid;
                List tlist = this.find(sql1);
                if(tlist != null && tlist.size() == 1){
                    TOrderlist tl = (TOrderlist) tlist.get(0);
                    if(tl.getNumb() == 1){
                        return orid;
                    }
                }
            }
        }
        
        return null;
	}
	
	public MOrder getMorderInfo(String orderId) {
		 List<MOrder> ml = this.find("from MOrder m where m.orid='"+orderId+"' and (m.ddzt='02' or m.ddzt='11' or (m.ddzt='00' and m.zffs='09'))");
		 return ml.get(0);
	}
	
	public List getSatordertab(String orderId, String iscenicid, String orderlistid, String zorderlistid) {
		 try {
			List list2 = this.findBySqlToMap("select s.iseatid from seatordertab s where s.orid=" + orderId + " and s.iscenicid=" + iscenicid + " and s.orderlistid=" + orderlistid + " and s.zorderlistid=" + zorderlistid + " and s.isvalid=1");
			return list2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}

	@Override
	public List<TOrder> getTOrderForListByOrid(String orid) throws Exception {
		String sql = "from TOrder t where t.id.orid='"+ orid + "'";
		return this.find(sql);
	}

	
	public List<TZorderlist> getTzOrderList(String orid, String iscenicids) {
		String hql = "from TZorderlist where id.orid='"+orid+"' and tripid>0";
		return this.find(hql);		
	}
	
	public List<Map> getTzOrderLists(String orid, String iscenicids){
		String sql = " select CASt(orid as varchar(17)) as orid,zorderlistid,orderlistid,iscenicid,icrowdkindpriceid,icrowdkindid,itickettypeid,iztickettypeid,dtstartdate,dtenddate,tripid,ivenueid,ivenueareaid,ivenueseatsid,zpric,znumb,zyhnumb,zyhamnt,zamnt,isa,isb,isc,isd,ise,isf,isg,ish,isi,isj,notea,notej,notei,noteh,noteg,notef,notee,noted,notec,noteb,jsprice from T_zorderlist t where  orid="+orid+"  and iscenicid in ("+iscenicids+") and znumb>0 order by t.orderlistid,zorderlistid";
		try {
			return this.findBySqlToMap(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 public ResultBean updateT_order(String orid, Long iscenicid,
             Long iemployeeid, Double mont) {
		 ResultBean rs = new ResultBean();
	        try {
	            rs.setColumnCount(2);
	            rs.setColumnNames(new String[] { "returnstats", "message" });
	            String returnstats = "";
	            String message = "";

	            MOrder m = (MOrder) this.get(MOrder.class, orid);

	            YOrder yorder = (YOrder) this.get(YOrder.class, new YOrderId(
	                    orid, iscenicid));
	            yorder.setNotec(Tools.getNowString());
	            yorder.setIsc(iemployeeid);

	            // 2014-03-05 lijingrui 修改 网上现场支付订单状态 00 支付方式 05
	            if (!m.getDdzt().equals("00") && !m.getZffs().equals("05")) {
	                if (!yorder.getDdzt().equals("02")) {
	                    rs.addRow(new String[] { "fasle", "订单状态不是已付款状态不允许出票" });
	                    return rs;
	                }
	            }

	            yorder.setDdzt("11");
	            yorder.setNotec(Tools.getNowString());
	            yorder.setIsc(iemployeeid);
	            this.update(yorder);
	            List listtorder = this.find("from TOrder where id.orid='"
	                    + orid + "'");
	            boolean b = true;
	            for (int i = 0; i < listtorder.size(); i++) {
	                TOrder t = (TOrder) listtorder.get(i);

	                if (t.getId().getIscenicid().longValue() == iscenicid
	                        .longValue()) {
	                    if (t.getZfmont().doubleValue() != mont) {
	                        rs.addRow(new String[] { "fasle", "出票金额与订单金额不符" });
	                        return rs;
	                    }
	                    // 2014-03-05 lijingrui 修改 网上现场支付订单状态 00 支付方式 05
	                    if (!m.getDdzt().equals("00") && !m.getZffs().equals("05")) {
	                        if (!t.getDdzt().equals("02")) {
	                            rs.addRow(new String[] { "fasle",
	                                    "订单状态不是已付款状态不允许出票" });
	                            return rs;
	                        }
	                    }

	                    t.setDdzt("11");
	                    t.setNotec(Tools.getNowString());
	                    t.setIsc(iemployeeid);
	                    this.update(t);
	                } else {
	                    if (t.getDdzt().equals("02")) {
	                        b = false;
	                    }
	                }
	            }
	            if (b) {
	                // 该订单已经全部出票

	                if (m.getNotea() != null && m.getNotea().equals("50")) {
	                    m.setNotea("51");
	                }
	                if (m.getNotea() != null && m.getNotea().equals("02")) {
	                    m.setNotea("11");
	                }
	                m.setDdzt("11");
	                this.update(m);
	            }
	            rs.addRow(new String[] { "true", "成功" });
	            return rs;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            rs.addRow(new String[] { "false", e.getMessage() });
	            return rs;
	        }
	 }
}
