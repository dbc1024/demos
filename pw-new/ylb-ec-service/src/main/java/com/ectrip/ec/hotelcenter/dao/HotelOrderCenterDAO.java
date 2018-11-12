package com.ectrip.ec.hotelcenter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.enums.DDZT;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.common.CommonUtil;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.hotelcenter.dao.idao.IHotelOrderCenterDAO;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.RefundbillsRequest;
import com.ectrip.hqyt.model.response.JSONRefundBill;
import com.ectrip.sys.model.balance.Vipbalance;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

@Repository
public class HotelOrderCenterDAO extends GenericDao implements IHotelOrderCenterDAO{
	
	@Autowired
	private TicketService ticketService;

	/**
	 * 根据订单查询要审核的订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid 订单号
	 * @param stdt 入住时间
	 * @param iticketId 产品编号
	 * @return
	 * return:List
	 * Date:2012-3-4
	 */
	public List queryHotelOrder(String orid,String stdt,Long iscenicid){
		StringBuffer hsql = new StringBuffer();
		
//		hsql.append(" select distinct new map ("
//				+ " t.id.iscenicid as iscenicid, t.scenictype as scenictype, t.ornm as ornm, t.orph as orph, t.ddzt as ddzt,"
//				+ " t.dtstartdate as dtstartdate, t.dtenddate as dtenddate,"
//				+ " tl.itickettypeid as itickettypeid, tl.numb as numb, tl.pric as pric, "
//				+ " pro.szscenicname as szscenicname,"
//				+ " prd.sztickettypename as sztickettypename"
//				+ " ) "
//				+ " from TOrder t, TOrderlist tl, Esbscenicareatab pro, Edmtickettypetab prd "
//				+ " where t.id.orid='"+orid+"' and tl.dtstartdate='"+ stdt +"'"
//				+ " and t.id.orid = tl.id.orid and t.id.iscenicid = tl.id.iscenicid "
//				+ " and t.id.iscenicid = pro.iscenicid "
//				+ " and tl.itickettypeid = prd.itickettypeid ");
		
		
		hsql.append(" select distinct new map ("
				+ " t.id.iscenicid as iscenicid, t.scenictype as scenictype, t.ornm as ornm, t.orph as orph, t.ddzt as ddzt,"
				+ " t.dtstartdate as dtstartdate, t.dtenddate as dtenddate,"
				+ " tl.itickettypeid as itickettypeid, tl.numb as numb, tl.pric as pric"
				+ " ) "
				+ " from TOrder t, TOrderlist tl"
				+ " where t.id.orid='"+orid+"' and tl.dtstartdate='"+ stdt +"'"
				+ " and t.id.orid = tl.id.orid and t.id.iscenicid = tl.id.iscenicid "
				);
		
		hsql.append(" and t.id.iscenicid="+iscenicid);
		
//		+ " pro.szscenicname as szscenicname,"
//		+ " prd.sztickettypename as sztickettypename"
		
//      + " and t.id.iscenicid = pro.iscenicid "
//      + " and tl.itickettypeid = prd.itickettypeid "
		
		
		List<Map> list = this.find(hsql.toString());
		
		if(list!=null && list.size()>0) {
			
			StringBuilder iscenicidS = new StringBuilder();
			StringBuilder ticketTypeIds = new StringBuilder();
			
			for (Map map : list) {
				String tOrder_iscenicid = map.get("iscenicid").toString();
        		iscenicidS.append(tOrder_iscenicid+ ",");
        		
        		String typeid = map.get("itickettypeid").toString();
                ticketTypeIds.append(typeid+ ",");
			}
			iscenicidS.deleteCharAt(iscenicidS.length() - 1);
			ticketTypeIds.deleteCharAt(ticketTypeIds.length() - 1);
			
			
			//根据id集合获取服务商列表
        	List<Map> providerList = ticketService.getProvidersByIds(iscenicidS.toString());
        	List<Map> ticketTypeList = ticketService.getTicketTypeListByIds(ticketTypeIds.toString());
        	
        	
        	for (Map map : list) {
        		String tOrder_iscenicid = map.get("iscenicid").toString();
        		
        		for (Map provider : providerList) {
                	String iscenicid1 = provider.get("iscenicid").toString();
                	if(tOrder_iscenicid.equals(iscenicid1)) {
                		String szscenicname = provider.get("szscenicname").toString();
                		String scenictype = provider.get("scenictype").toString();
                		
                		map.put("szscenicname", szscenicname);
                	}
                	
                	String torder_itickettypeid = map.get("itickettypeid").toString();
                	for (Map ticketType : ticketTypeList) {
                		String itickettypeid = ticketType.get("itickettypeid").toString();
                		if(torder_itickettypeid.equals(itickettypeid)) {
                			String sztickettypename = ticketType.get("sztickettypename").toString();
                			map.put("sztickettypename", sztickettypename);
                		}
					}
                	
    			}
			}
		}
		
		
		return list;
	}
	
	/**
	 * 审核订单
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid 订单号
	 * @param ddzt 订单状态
	 * @param orderlist 订单列表
	 * @param syslog 系统日志
	 * @param orderlog 订单操作日志
	 * return:void
	 * Date:2012-3-5
	 */
	public void updateHotelOrder(String orid,String ddzt,Long iticketId,String hycode,Syslog syslog,Orderlog orderlog){
//		Edmtickettypetab esf=(Edmtickettypetab) this.get(Edmtickettypetab.class, iticketId);
		
		String hsql1 = " from MOrder where orid='"+orid+"' ";
		String hsql2 = " from TOrder where id.orid='"+orid+"' and id.iscenicid="+iticketId;
		String hsql3 = " from YOrder where id.orid='"+orid+"' and id.iscenicid="+iticketId;
		List morderlist = this.find(hsql1);
		List torderlist = this.find(hsql2);
		List yorderlist = this.find(hsql3);
		
		String ddzts ="00";
		if (ddzt.equals("02") || ddzt.equals("03") || ddzt.equals("04")) {//02 审核通过前台现付 03 审核通过需担保04 审核通过未支付（需网上支付）
			ddzts="00";
		} else if (ddzt.equals("05")) {//审核通过已付款
			ddzts="02";				
		} else if (ddzt.equals("06")) {//审核未通过酒店无房
			ddzts="20";	
		} else if (ddzt.equals("07") || ddzt.equals("08")
					|| ddzt.equals("09")) {//07 网上支付已入住08 前台现付已入住09 担保已入住
			ddzts="11";
		}else if (ddzt.equals("10") || ddzt.equals("12")) {//10	货物已送出 11	货物已接收 12	已支付
			ddzts="02";
		}else if (ddzt.equals("11")) {//11	货物已接收
			ddzts="11";
		}
		
		if(!"01".equals(ddzt)){
		
			if(torderlist!=null && torderlist.size()>=1){
				for (int i = 0; i < torderlist.size(); i++) {
					TOrder torder = (TOrder)torderlist.get(i);
					if("08".equals(torder.getScenictype())&&"02".equals(torder.getDdzt())){
						if(!"".equals(hycode)){
							torder.setNoteh(hycode);//商品货运号
						}
					}
					torder.setDdzt(ddzts);
					this.update(torder);
				}
			}
			
			if(yorderlist!=null && yorderlist.size()>=1){
				for (int i = 0; i < yorderlist.size(); i++) {
					YOrder yorder = (YOrder)yorderlist.get(i);
					if(yorder.getScenictype().equals("08")&&yorder.getDdzt().equals("02")){
						yorder.setNoteh(hycode);//商品货运号
					}
					yorder.setDdzt(ddzts);
					this.update(yorder);
				}
			}
			String sql=" from TOrder where orid='"+orid+"' ";
			List list=this.find(sql);
			int numb=0;
			if(list!=null&&list.size()>=1){
				for(int x=0;x<list.size();x++){
					TOrder td=(TOrder) list.get(x);
					if(td.getDdzt().equals(ddzts)){
						numb=numb+1;
					}
				}
			}
			if(numb==list.size()){
				if(morderlist!=null && morderlist.size()>=1){
					for (int i = 0; i < morderlist.size(); i++) {
						MOrder morder = (MOrder)morderlist.get(i);
						morder.setDdzt(ddzts);
						if(ddzts.equals("02")&&(morder.getZffs()==null||morder.getZffs().equals(""))){
							morder.setZffs("00");  //现金支付
 						}
						this.update(morder);
					}
				}
			}
			
			
			syslog.setStlg("0012");
			syslog.setBrief("修改订单结算价，订单号："+orid   );
			syslog.setNote("修改订单结算价，订单号："+orid );
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
			
			Esbscenicareatab esb=(Esbscenicareatab) this.get(Esbscenicareatab.class, iticketId);
		 	orderlog.setLogid(getMaxPk("logid", "Orderlog") + 1);
		    orderlog.setOrid(orid);
		    orderlog.setStlg("0157");
		    if("20".equals(ddzts)){
		    	 orderlog.setBrief(esb.getSzscenicname()+"操作未通过");
				 orderlog.setNote("操作未通过");
		    }else{
		    	orderlog.setBrief(esb.getSzscenicname()+"操作成功");
			    orderlog.setNote("操作成功");
		    }
		    
		    orderlog.setUsid("");
		    orderlog.setLogtype(Long.parseLong("1"));	    
		    orderlog.setLogdatetime(Tools.getDayTimes());
		    this.save(orderlog);
		}
		
	}
	
	/**
     * Describe:根据条件查询订单 
     * 
     * @auth:lijingrui
     * @param esfemployeetab登录人信息
     * @param order订单查询条件类
     * @param page页码
     * @param pageSize毎页显示数
     * @param url访问地址
     * @return return:PaginationSupport Date:2011-10-31
     */
    public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order,String pdtp, int page, int pageSize, String url) {
	PaginationSupport ps = null;

	StringBuffer hsql = new StringBuffer();
	StringBuffer where = new StringBuffer();
	
//	hsql.append("select distinct new map("
//			+ " yor.id.orid as orid, yor.usid as usid, mor.orda as orda, yor.id.iscenicid as iscenicid, yor.ornm as ornm,"
//			+ " yor.orhm as orhm, yor.orph as orph, yor.ddzt as ddzt, yor.mont as summont, yor.tpmont as tpmont,"
//			+ " yor.tpsx as tpsx, yor.dtstartdate as dtstartdate, yor.noteh as noteh, "
//			+ " mor.payorid as payorid,"
//			+ " us.corpname as corpname, "
//			+ " es.szscenicname as szscenicname, es.scenictype as scenictype, "
//			+ " v5.pmva as strddzt"
//			+ " ) "
//			+ " from YOrder yor, MOrder mor, Custom us, Sysparv5 v5, Esbscenicareatab es ");
	
	hsql.append("select distinct new map("
			+ " yor.id.orid as orid, yor.usid as usid, mor.orda as orda, yor.id.iscenicid as iscenicid, yor.ornm as ornm,"
			+ " yor.orhm as orhm, yor.orph as orph, yor.ddzt as ddzt, yor.mont as summont, yor.tpmont as tpmont,"
			+ " yor.tpsx as tpsx, yor.dtstartdate as dtstartdate, yor.noteh as noteh, "
			+ " mor.payorid as payorid,"
			+ " us.corpname as corpname"
			+ " ) "
			+ " from YOrder yor, MOrder mor, Custom us");
	
	where.append(" where mor.ortp='01' and yor.scenictype!='01' ");
	
	
	
	if (0 == order.getErrorsid()) {
	    // 所有服务商
	    if (0L == order.getIscenicid()) {
			// 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
			if (esfemployeetab.getCompanytype().equals("02")) {
			    String scenics = esfemployeetab.getScenics();
			    where.append(" and yor.id.iscenicid  in (" + scenics + ") ");
			}
	    } else {// 指定服务商
	    	where.append(" and yor.id.iscenicid =" + order.getIscenicid());
	    }

	    if (1 == order.getRadiobutton1()) {// 下单用户
	    	where.append(" and mor.usid='" + order.getUsid() + "' ");
	    }

	    if (order.getDyusid() != null && !"".equals(order.getDyusid())) {// 导游
	    	where.append(" and yor.dyusid ='" + order.getDyusid() + "' ");
	    }
	    if (order.getStrornm() != null && !"".equals(order.getStrornm())) {// 导游名称模糊
	    	where.append(" and	yor.ornm like ('%" + order.getStrornm() + "%') ");
	    }
	    if (0 == order.getRadiobutton2()) {// 首日游览日期
	    	where.append(" and mor.stdt>='" + order.startDate + "' and mor.stdt<='" + order.getEndDate() + "' ");
	    } else if (1 == order.getRadiobutton2()) {// 下订单日期
	    	where.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate() + "' ");
	    } else if (2 == order.getRadiobutton2()) {// 支付日期日期
	    	where.append(" and mor.bankdata>='" + order.getStartDate() + "' and mor.bankdata<='" + order.getEndDate() + "' ");
	    } else if (4 == order.getRadiobutton2()) {// 出票时间
	    	where.append(" and substr(yor.notec,1,10)>='" + order.getStartDate() + "' and substr(yor.notec,1,10) <='"
			+ order.getEndDate() + "' ");
	    } else if (3 == order.getRadiobutton2()) {// 竹筏日期
			hsql.append(",TZorderlist tzl");
			where.append(" and tzl.id.orid = yor.id.orid and tzl.id.iscenicid=yor.id.iscenicid and tzl.tripid>0 and substr(tzl.dtstartdate,1,10)>='"
				+ order.getStartDate() + "' and substr(tzl.dtstartdate,1,10)<='" + order.getEndDate() + "'");
	    }
	    if ("99".equals(order.getDdzt())) {// 所有
	    	where.append(" and yor.ddzt in ('00','02','11','18','21','20','95','98')");
	    } else {
	    	where.append(" and yor.ddzt ='" + order.getDdzt() + "'");
	    }

	}
	if (1 == order.getErrorsid()) {
	    // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
	    if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
			String scenics = esfemployeetab.getScenics();
			where.append(" and yor.id.iscenicid in (" + scenics + ")  ");
	    }
	    if (0 == order.getRadiobutton3()) {// 订单号
			if (order.getOrid() != null && !"".equals(order.getOrid())) {
			    where.append(" and mor.orid='" + order.getOrid() + "' ");
			}
	    } else if (1 == order.getRadiobutton3()) {// 联系人姓名
			if (order.getStrornm() != null && !"".equals(order.getStrornm())) {
			    where.append(" and yor.ornm like '%" + order.getStrornm() + "%' ");
			}
	    } else if (2 == order.getRadiobutton3()) {// 联系人证件号码
			if (order.getOrhm() != null && !"".equals(order.getOrhm())) {
			    where.append(" and lower(yor.orhm) = lower('" + order.getOrhm() + "') ");
			}
	    } else if (3 == order.getRadiobutton3()) {// 逾期未领订单
	    	where.append(" and yor.dtstartdate < '" + Tools.getDays() + "' and yor.ddzt='02' and yor.mont>0 ");
	    } else if (4 == order.getRadiobutton3()) {// 行程单号
			if (order.getSztravelbillno() != null && !"".equals(order.getSztravelbillno())) {
			    where.append(" and yor.sztravelbillno ='" + order.getSztravelbillno() + "' ");
			}
	    } else if (5 == order.getRadiobutton3()) { // 支付订单号
			if (order.getPayorid() != null && !"".equals(order.getPayorid())) {
			    where.append(" and mor.payorid='" + order.getPayorid() + "' ");
			}
	    }
	}
	
//	hsql.append("select distinct new map("
//	+ " yor.id.orid as orid, yor.usid as usid, mor.orda as orda, yor.id.iscenicid as iscenicid, yor.ornm as ornm,"
//	+ " yor.orhm as orhm, yor.orph as orph, yor.ddzt as ddzt, yor.mont as summont, yor.tpmont as tpmont,"
//	+ " yor.tpsx as tpsx, yor.dtstartdate as dtstartdate, yor.noteh as noteh, "
//	+ " mor.payorid as payorid,"
//	+ " us.corpname as corpname, "
//	+ " es.szscenicname as szscenicname, es.scenictype as scenictype, "
//	+ " v5.pmva as strddzt"
//	+ " ) "
//	+ " from YOrder yor, MOrder mor, Custom us, Sysparv5 v5, Esbscenicareatab es ");
	
//	 and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid
	
	
//	where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
	where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid");
	
	where.append(" and yor.scenictype='"+ pdtp +"' ");
	where.append(" order by yor.id.orid desc ");
	
	ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);

	List<Map> list = ps.getItems();
	if (list.size() >= 1) {
		
		//封装服务商查询条件
		StringBuilder iscenicidS = new StringBuilder();
		for (Map map : list) {
			String iscenicid = map.get("iscenicid").toString();
			iscenicidS.append(iscenicid+ ",");
		}
		iscenicidS.deleteCharAt(iscenicidS.length() - 1);
    	
    	//根据id集合获取服务商列表
    	List<Map> providerList = ticketService.getProvidersByIds(iscenicidS.toString());
		
    	
    	//szscenicname,scenictype,strddzt赋值
    	for (Map map : list) {
    		
    		//strddzt赋值
    		String ddzt = map.get("ddzt").toString();
            String pmva = DDZT.getPmvaByPmcd(ddzt);
            map.put("strddzt", pmva);
            
            //szscenicname,scenictype赋值
            String iscenicid = map.get("iscenicid").toString();
            for (Map provider : providerList) {
            	String iscenicid1 = provider.get("iscenicid").toString();
            	if(iscenicid.equals(iscenicid1)) {
            		String szscenicname = provider.get("szscenicname").toString();
            		String scenictype = provider.get("scenictype").toString();
            		
            		map.put("szscenicname", szscenicname);
            		map.put("scenictype", scenictype);
            	}
			}
		}
		
		
		
	    Map map = null;
	    String orid = "";
	    for (int i = 0; i < list.size(); i++) { 
			map = (Map) list.get(i);
			
			orid = map.get("orid").toString();
			String ddzt = map.get("ddzt").toString();
			String strddzt = map.get("strddzt").toString();
			
			// 判断前台订单状态
//			String ddzt_hsql = "select new map(m.ddzt as ddzt ,s.pmva as strddzt) "
//					+ " from MOrder m,Sysparv5 s "
//					+ " where m.ddzt=s.id.pmcd and s.id.pmky='DDZT' and m.srid='"+ orid + "'";
			
			String ddzt_hsql = "select new map(m.ddzt as ddzt) "
					+ " from MOrder m"
					+ " where m.srid='"+ orid + "'";
			
			List ddzt_list = find(ddzt_hsql);
			if (ddzt_list != null && ddzt_list.size() > 0) {
				Map ddzt_map = (Map) ddzt_list.get(0);
				
				String ddzt1 = ddzt_map.get("ddzt").toString();
				String pmva = DDZT.getPmvaByPmcd(ddzt1);
				
//				String two_ddzt = ddzt_map.get("strddzt").toString();
				String two_ddzt = pmva;
				
				if (two_ddzt != null && !two_ddzt.trim().equals("")) {
					strddzt = strddzt + "(" + two_ddzt + ")";
					map.put("strddzt", strddzt);
					map.put("isUnsubscribe", 1);//是否“已支付（退订已退款）”
				}
			}
			
			if (map.get("orid") != null) {// 订单号
			    orid = map.get("orid").toString();
			    Long cenicid = new Long(map.get("iscenicid").toString());
			    // String
//			    String sql = "select distinct new map("
//			    		+ " tzl.itickettypeid as itickettypeid, tzl.pric as pric, tzl.numb as numb, tzl.amnt as amnt, tzl.isj as isj,"
//			    		+ " tor.ornm as ornm, tor.orph as orph, tor.orhm as orhm, tor.orzj as orzj, tor.dtstartdate as dtstartdate,"
//			    		+ " prd.sztickettypename as sztickettypename"
//			    		+ " )"
//			    		+ " from TOrder tor, Sysparv5 sys1, Edmtickettypetab prd, TOrderlist tzl, MOrder mor  "
//			    		+ " where ( mor.orid='"+ orid + "' or mor.srid='"+ orid+ "') "
//			    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
//			    		+ " and tor.ddzt=sys1.id.pmcd and sys1.id.pmky='DDZT' "
//			    		+ " and tor.id.iscenicid = tzl.id.iscenicid  and tzl.id.orid = tor.id.orid and tzl.itickettypeid = prd.itickettypeid "
//			    		+ " order by tzl.isj desc";
			    
			    String sql = "select distinct new map("
			    		+ " tzl.itickettypeid as itickettypeid, tzl.pric as pric, tzl.numb as numb, tzl.amnt as amnt, tzl.isj as isj,"
			    		+ " tor.ornm as ornm, tor.orph as orph, tor.orhm as orhm, tor.orzj as orzj, tor.dtstartdate as dtstartdate"
			    		+ " )"
			    		+ " from TOrder tor, TOrderlist tzl, MOrder mor  "
			    		+ " where ( mor.orid='"+ orid + "' or mor.srid='"+ orid+ "') "
			    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
			    		+ " and tor.id.iscenicid=tzl.id.iscenicid and tzl.id.orid=tor.id.orid"
			    		+ " order by tzl.isj desc";
			    
			    // and tzl.itickettypeid=prd.itickettypeid 
			    //+ " prd.sztickettypename as sztickettypename"
			    
			    List<Map> torderList = this.find(sql);
			    if (torderList.size() >= 1) {
			    	
			    	
			    	StringBuilder ticketTypeIds = new StringBuilder();
			    	
					for (int j = 0; j < torderList.size(); j++) {
					    Map zmap = (Map) torderList.get(j);
					    if (null != zmap.get("isj")) {
							if (Long.parseLong(zmap.get("isj").toString()) == -1) {
							    zmap.put("numb", new Long(zmap.get("numb").toString()) * -1);
							}
					    }
					    
					    //封装票种id
					    String typeid = zmap.get("itickettypeid").toString();
                        ticketTypeIds.append(typeid);
                        ticketTypeIds.append(",");
					}
					map.put("torderlists", torderList);
					
					
					//根据票种id集合，查询票种信息
                    ticketTypeIds.deleteCharAt(ticketTypeIds.length() - 1);
                    List<Map> ticketTypeList = ticketService.getTicketTypeListByIds(ticketTypeIds.toString());
                    
                    
                    for (Map torder : torderList) {
                    	String torder_itickettypeid = torder.get("itickettypeid").toString();
						
                    	for (Map ticketType : ticketTypeList) {
                    		String itickettypeid = ticketType.get("itickettypeid").toString();
                    		if(torder_itickettypeid.equals(itickettypeid)) {
                    			String sztickettypename = ticketType.get("sztickettypename").toString();
                    			torder.put("sztickettypename", sztickettypename);
                    		}
						}
					}
                    
			    }
	
			}
			
	    }
	}

	return ps;
    }
	
    /**
	 * *
	 * Describe:查看订单基本信息
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#getMOrderList(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-2
	 */
	public MOrder getMOrderList(String orid) {
		MOrder morder=(MOrder) this.get(MOrder.class, orid);
		String sql=" from Sysparv5 sys1 where sys1.id.pmky='DDZT' and sys1.id.pmcd='"+morder.getDdzt()+"'";
		List list=this.find(sql);
		Sysparv5 sys1=(Sysparv5) list.get(0);
		morder.setPmva(sys1.getPmva());
		
		return morder;
		
	}
	
	/**
	 * *
	 * Describe:查看订单预订信息
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#getTOrderList(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-2
	 */
	public List getTOrderList(String orid,Long iscenicid) {
	    String hql="select new map(t.isi as isi,t.id.orid as orid,t.id.iscenicid as iscenicid,t.usid as usid,t.ddzt as ddzt,t.dyusid as dyusid,t.scenictype as scenictype,e.szscenicname as szscenicname,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.mont as mont,t.zfmont as zfmont,t.orph as orph,t.ornm as ornm,t.orhm as orhm ,ct.zjhm as zjhm,ct.mobile as mobile,s.pmva as pmva) from TOrder t,Esbscenicareatab e,Custom ct,Sysparv5 s where t.id.orid='"
						+ orid + "' and t.id.iscenicid=e.iscenicid and ct.usid=t.usid and s.id.pmky='DDZT' and s.id.pmcd=t.ddzt and t.id.iscenicid="+iscenicid;
	    return find(hql);
	}
	
	
	/**
	 * *
	 * Describe:查看订单明细
	 * @see com.ectrip.system.hotelcenter.service.iservice.IHotelOrderCenterService#getTOrderlists(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-2
	 */
	public List<TOrderlist> getTOrderlists(String orid,Long iscenicid) {
		String hql = "select new map(td.id.orid as orid,td.dtstartdate as dtstartdate,td.dtenddate as dtenddate,td.pric as pric,td.jsprice as jsprice,td.numb as numb,td.amnt as amnt,td.yhamnt as yhamnt,e.szscenicname as szscenicname,ed.sztickettypename as sztickettypename) from  TOrderlist td,Esbscenicareatab e,Edmtickettypetab ed where td.id.orid='" + orid + "' and td.id.iscenicid=e.iscenicid and td.itickettypeid=ed.itickettypeid and td.id.iscenicid="+iscenicid;
		List list =this.find(hql);
		if (list != null && list.size() > 0) {
		    return list;
		} else {
		    return null;
		}
	}
	
	/**
	 * *
	 * Describe:获取退订金额
	 * @see com.ectrip.system.hotelcenter.dao.idao.IHotelOrderCenterDAO#lookUpzfmont(java.lang.String)
	 * @param orid
	 * @return
	 * @author lijingrui
	 * Date:2012-7-3
	 */
	public Double lookUpzfmont(String orid,Long iscenicid){
		String sql=" from TOrder where id.orid='"+orid+"' and id.iscenicid="+iscenicid;
		List list=this.find(sql);
		double totalmont = 0.0;
		TOrder torder = null;
		if (list != null && list.size() > 0) {
			torder=(TOrder) list.get(0);
			totalmont=torder.getZfmont();
		}
		
		return totalmont;
	}
	
	/**
	 * *
	 * Describe:订单强制退订
	 * @see com.ectrip.system.hotelcenter.dao.idao.IHotelOrderCenterDAO#showViewzfmont(java.lang.String, java.lang.Long, java.lang.Double, com.ectrip.model.employee.Esfemployeetab)
	 * @param orid
	 * @param iscenicid
	 * @param totalmont
	 * @param esfemployeetab
	 * @author lijingrui
	 * Date:2012-7-3
	 * @throws Exception 
	 */
	public void showViewzfmont(String orid,Long iscenicid,Double totalmont,Esfemployeetab esfemployeetab,String usid,Syslog syslog,Orderlog orderlog) throws Exception{
		String hsql1 = " from MOrder where orid='"+orid+"' ";
		String hsql2 = " from TOrder where id.orid='"+orid+"' and id.iscenicid="+iscenicid;
		String hsql3 = " from YOrder where id.orid='"+orid+"' and id.iscenicid="+iscenicid;
		List morderlist = this.find(hsql1);
		List torderlist = this.find(hsql2);
		List yorderlist = this.find(hsql3);
		
		String ddzts="06";  //退订已退款
		MOrder mder = (MOrder)morderlist.get(0);
		String neworid = this.getMaxNo("000");
			
			if(morderlist!=null && morderlist.size()>=1){
				for (int i = 0; i < morderlist.size(); i++) {
					MOrder morder = (MOrder)morderlist.get(i);
					if(morder.getTpmont()==null||morder.getTpmont().equals("")){
						morder.setTpmont(totalmont);
					}else{
						morder.setTpmont(morder.getTpmont()+totalmont);
					}
					if(morder.getDdzt()!=null&&(morder.getDdzt().equals("95")||morder.getDdzt().equals("00"))){
						morder.setZfmont(morder.getZfmont()-totalmont);
					}
					
					this.update(morder);
				}
				
				//网上预定订单
				MOrder md = new MOrder();
				md.setOrid(neworid);
				md.setSrid(orid);
				md.setDdzt(ddzts);
				md.setOrtp("02");//// 订单类型 01 预定 02 退票 03 增量订单
				md.setMont(totalmont);//订单金额
				md.setZfmont(totalmont);// 支付的金额					
				if(mder.getDdzt().equals("02")){
					md.setTpfs("01");//退订方式（ 01 出票前修改）
				}else if(mder.getDdzt().equals("11")){
					md.setTpfs("02");//退订方式（ 02 出票后修改）
				}else{
					md.setTpfs("01");
				}
				
				md.setTpmont(totalmont);//实退金额
				md.setTpdate(mder.getOrda()+" "+mder.getOrti());
				md.setIsj(1L);
				md.setNotef("07");
				
				md.setUsid(mder.getUsid());// 游客编号id
				md.setOrda(mder.getOrda());//下单日期
				md.setOrti(mder.getOrti());// 下单时间
				md.setIsjl(0L);// 奖励订单
				md.setYhamnt(mder.getYhamnt());// 优惠金额
				md.setIsallcp(0L);// 是否全部出票
				md.setTpsx(0d);//退订手续费
				md.setTpfl(0d);//退订手续费率
				md.setStdt(mder.getStdt());//首次消费日期					
				md.setIsf(0L);
				md.setIsa(0L);
				md.setIsb(0L);
				md.setIsd(0L);
				md.setIse(0L);
				md.setIsg(0L);
				md.setIsh(0L);
				md.setIsi(0L);
				
				this.save(md);
			}
		
		if(torderlist!=null && torderlist.size()>=1){
			//订单状态为已支付     出票前退订
			if(mder.getDdzt().equals("02")){
				for (int i = 0; i < torderlist.size(); i++) {
					TOrder torder = (TOrder)torderlist.get(i);
					torder.setMont(torder.getMont()-totalmont);//订单金额
					torder.setZfmont(torder.getZfmont()-totalmont);//支付的金额
					this.update(torder);
				}
			}else if(mder.getDdzt().equals("11")){
				TOrder oder=(TOrder)torderlist.get(0);
				TOrder torder=new TOrder();
				TOrderId tdid=new TOrderId();
				tdid.setOrid(neworid);// 重新生成订单号
				tdid.setIscenicid(oder.getId().getIscenicid());
				torder.setId(tdid);
				torder.setScenictype(oder.getScenictype());//服务商类型
				torder.setOrfl(oder.getOrfl());
				torder.setUsid(oder.getUsid());//游客编号
				torder.setIbusinessid(oder.getIbusinessid());//业务类型（散客业务）
				torder.setIregionalid(oder.getIregionalid());//客源地
				torder.setDtstartdate(oder.getDtstartdate());//启用日期
				torder.setDtenddate(oder.getDtenddate());//失效日期
				torder.setDdzt(ddzts);			//退订已退款
				torder.setOrnm(oder.getOrnm());//领票人姓名
				torder.setOrzj(oder.getOrzj());//领票人证件类别
				torder.setOrhm(oder.getOrhm());//领票人证件号码
				torder.setOrph(oder.getOrph());//领票人电话
				torder.setFaxno(oder.getFaxno());//传真号
				torder.setMont(totalmont);//订单金额
				torder.setYhamnt(oder.getYhamnt());//优惠金额
				torder.setZfmont(totalmont);//支付的金额
				torder.setYoufei(oder.getYoufei());//邮费
				
				torder.setIsa(0L);
				torder.setIsb(0L);
				torder.setIsc(0L);
				torder.setIsd(0L);
				torder.setIse(0L);
				torder.setIsf(0L);
				torder.setIsg(0L);
				torder.setIsh(0L);
				torder.setIsi(0L);
				torder.setIsj(new Long(-1));
				torder.setNotej(orid);
				torder.setNotef("07"); //后台强制退订
				this.save(torder);
			}else{
				for (int i = 0; i < torderlist.size(); i++) {
					TOrder torder = (TOrder)torderlist.get(i);
					torder.setMont(torder.getMont()-totalmont);//订单金额
					torder.setZfmont(torder.getZfmont()-totalmont);//支付的金额
					this.update(torder);
				}
			}
			
		}
		
		String tql=" from TOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" order by ts.amnt desc";
		List tdlist=this.find(tql);
		if(tdlist!=null&&tdlist.size()>=1){
			TOrderlist newTicket = (TOrderlist)tdlist.get(0);
			// 新增差价产品
			TOrderlist newproduct = new TOrderlist();
			TOrderlistId todid=new TOrderlistId();
			todid.setIscenicid(iscenicid);
			String tpsql="select max(ts.id.orderlistid) from TOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid;
			Long seq=(Long) this.find(tpsql).get(0)==null?1:(Long) this.find(tpsql).get(0)+1;
			todid.setOrderlistid(seq);
			if(mder.getDdzt().equals("02")){
				todid.setOrid(orid);
			}else if(mder.getDdzt().equals("11")){
				todid.setOrid(neworid);
			}else{
				todid.setOrid(orid);
			}
			newproduct.setId(todid);
			
			newproduct.setItickettypeid(newTicket.getItickettypeid());
			newproduct.setIcrowdkindid(newTicket.getIcrowdkindid());
			newproduct.setIcrowdkindpriceid(newTicket.getIcrowdkindpriceid());
			newproduct.setDtstartdate(newTicket.getDtstartdate());
			newproduct.setDtenddate(newTicket.getDtenddate());
			newproduct.setPric(new Double(-1*totalmont));
			newproduct.setJsprice(new Double(-1*totalmont));
			newproduct.setNumb(1L);
			newproduct.setYhnumb(0l);
			newproduct.setAmnt(new Double(-1*totalmont));
			newproduct.setYhamnt(0.0);	
			this.save(newproduct);
			
			String tzql=" from TZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" and ts.id.orderlistid="+newTicket.getId().getOrderlistid()+" and ts.itickettypeid="+newTicket.getItickettypeid();
			List tzlist=this.find(tzql);
			TZorderlist tzderlist=(TZorderlist) tzlist.get(0);
			TZorderlist tzorderlist = new TZorderlist();
			TZorderlistId tzderid=new TZorderlistId();
			tzderid.setIscenicid(iscenicid);
			
			if(mder.getDdzt().equals("02")){
				tzderid.setOrid(orid);
			}else if(mder.getDdzt().equals("11")){
				tzderid.setOrid(neworid);
			}else{
				tzderid.setOrid(orid);
			}
			tzderid.setOrderlistid(newproduct.getId().getOrderlistid());
			String tzpsql="select max(ts.id.zorderlistid) from TZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+"and ts.id.orderlistid="+newTicket.getId().getOrderlistid();
			Long seq2=(Long) this.find(tzpsql).get(0)==null?1:(Long) this.find(tzpsql).get(0)+1;
			tzderid.setZorderlistid(seq2);
			tzorderlist.setId(tzderid);
			
		    tzorderlist.setItickettypeid(tzderlist.getItickettypeid());
		    tzorderlist.setIcrowdkindpriceid(tzderlist.getIcrowdkindpriceid());
		    tzorderlist.setIcrowdkindid(tzderlist.getIcrowdkindid());
		    tzorderlist.setIztickettypeid(tzderlist.getIztickettypeid());
			tzorderlist.setDtstartdate(newTicket.getDtstartdate());
			tzorderlist.setDtenddate(newTicket.getDtenddate());
		    tzorderlist.setZpric(new Double(-1*totalmont));
		    tzorderlist.setJsprice(new Double(-1*totalmont));
		    tzorderlist.setZnumb(new Long(1));
		    tzorderlist.setZyhnumb(0l);
		    tzorderlist.setZyhamnt(0.0);
		    tzorderlist.setZamnt(new Double(-1*totalmont));
			this.save(tzorderlist);
		}	    
		
		
		if(yorderlist!=null && yorderlist.size()>=1){
			for (int x = 0; x < yorderlist.size(); x++) {
				YOrder yorder = (YOrder)yorderlist.get(x);
				if(yorder.getTpmont()==null||yorder.getTpmont().equals("")){
					yorder.setTpmont(totalmont);
				}else{
					yorder.setTpmont(totalmont+yorder.getTpmont());  //退款金额
				}
				yorder.setTpdate(Tools.getTodayString()); //退款时间
				this.update(yorder);
			}
			YOrder yorder = (YOrder)yorderlist.get(0);
			YOrder yder=new YOrder();
			YOrderId yid=new YOrderId();
			yid.setOrid(neworid);
			yid.setIscenicid(iscenicid);
			yder.setId(yid);
			yder.setDdzt(ddzts);
			
			yder.setOrtp("02");//// 订单类型 01 预定 02 退票 03 增量订单
			yder.setMont(totalmont);//订单金额
			yder.setZfmont(totalmont);// 支付的金额					
			yder.setTpmont(totalmont);//实退金额
			yder.setTpdate(mder.getOrda()+" "+mder.getOrti());
			yder.setIsj(1L);
			yder.setNotef("07");
			yder.setNotej(orid);
			
			yder.setScenictype(yorder.getScenictype());//服务商类型
			yder.setUsid(yorder.getUsid());//游客编号
			yder.setIbusinessid(yorder.getIbusinessid());//业务类型（散客业务）
			yder.setIregionalid(yorder.getIregionalid());//客源地
			yder.setDtstartdate(yorder.getDtstartdate());//启用日期
			yder.setDtenddate(yorder.getDtenddate());//失效日期
			yder.setOrnm(yorder.getOrnm());//领票人姓名
			yder.setOrzj(yorder.getOrzj());//领票人证件类别
			yder.setOrhm(yorder.getOrhm());//领票人证件号码
			yder.setOrph(yorder.getOrph());//领票人电话
			yder.setFaxno(yorder.getFaxno());//传真号
			yder.setYhamnt(yorder.getYhamnt());//优惠金额
			yder.setYoufei(yorder.getYoufei());//邮费
			
			yder.setIsa(yorder.getIsa());
			yder.setIsb(yorder.getIsb());
			yder.setIsc(yorder.getIsc());
			yder.setIsd(yorder.getIsd());
			yder.setIse(yorder.getIse());
			yder.setIsf(yorder.getIsf());
			yder.setIsg(yorder.getIsg());
			yder.setIsh(yorder.getIsh());
			yder.setIsi(yorder.getIsi());
			this.save(yder);
			
			String yql=" from YOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" order by ts.amnt desc";
			List ydlist=this.find(yql);
			if(ydlist!=null&&ydlist.size()>=1){
				YOrderlist newydTicket = (YOrderlist)ydlist.get(0);
				// 新增差价产品
				YOrderlist newydproduct = new YOrderlist();
				String ypsql="select max(ts.id.orderlistid) from YOrderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid;
				Long seq3=(Long) this.find(ypsql).get(0)==null?1:(Long) this.find(ypsql).get(0)+1;
				YOrderlistId yodstid=new YOrderlistId(seq3,neworid,newydTicket.getId().getIscenicid());
				newydproduct.setId(yodstid);
				
				newydproduct.setItickettypeid(newydTicket.getItickettypeid());
				newydproduct.setIcrowdkindid(newydTicket.getIcrowdkindid());
				newydproduct.setIcrowdkindpriceid(newydTicket.getIcrowdkindpriceid());
				newydproduct.setPric(new Double(-1*totalmont));
				newydproduct.setJsprice(new Double(-1*totalmont));
				newydproduct.setDtstartdate(newydTicket.getDtstartdate());
				newydproduct.setDtenddate(newydTicket.getDtenddate());
				newydproduct.setNumb(1L);
				newydproduct.setAmnt(new Double(-1*totalmont));
				newydproduct.setYhnumb(0l);
				newydproduct.setYhamnt(0.0);
				newydproduct.setIsj(1L);
				newydproduct.setNotef("07");
				this.save(newydproduct);
				
				String yzql=" from YZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" and ts.id.orderlistid="+newydTicket.getId().getOrderlistid()+" and ts.itickettypeid="+newydTicket.getItickettypeid();
				List yzlist=this.find(yzql);
				YZorderlist yzderlist=(YZorderlist) yzlist.get(0);
				
				YZorderlist yzorderlist = new YZorderlist();
				String yzpsql="select max(ts.id.zorderlistid) from YZorderlist ts where ts.id.orid='"+orid+"' and ts.id.iscenicid="+iscenicid+" and ts.id.orderlistid="+newydTicket.getId().getOrderlistid();
				Long seq4=(Long) this.find(yzpsql).get(0)==null?1:(Long) this.find(yzpsql).get(0)+1;
				YZorderlistId yzdid=new YZorderlistId(seq4, yzderlist.getId().getOrderlistid(), neworid, iscenicid);
				yzorderlist.setId(yzdid);
				yzorderlist.setItickettypeid(yzderlist.getItickettypeid());
				yzorderlist.setIcrowdkindpriceid(yzderlist.getIcrowdkindpriceid());
				yzorderlist.setIcrowdkindid(yzderlist.getIcrowdkindid());
				yzorderlist.setIztickettypeid(yzderlist.getIztickettypeid());
				yzorderlist.setZpric(new Double(-1*totalmont));
				yzorderlist.setJsprice(new Double(-1*totalmont));
				yzorderlist.setDtstartdate(yzderlist.getDtstartdate());
				yzorderlist.setDtenddate(yzderlist.getDtenddate());
				yzorderlist.setZnumb(new Long(1));
				yzorderlist.setZyhnumb(0l);
				yzorderlist.setZyhamnt(0.0);
				yzorderlist.setZamnt(new Double(-1*totalmont));
				yzorderlist.setIsj(1L);
				yzorderlist.setNotef("07");
				this.save(yzorderlist);
				    
			}
			
		}
		
		if(mder.getDdzt().equals("02")||mder.getDdzt().equals("11")){
			boolean hqyt = CommonUtil.isHqyt();
			if(hqyt){
				HqytClient client = new HqytClient();
				RefundbillsRequest request = new RefundbillsRequest();
				request.setId(Long.parseLong(mder.getNoteh()));
				request.setRefundMoney(totalmont);
				request.setRefundOrid(neworid);
				request.setReason(esfemployeetab.getEmpid()+"后台订单强制退订");
				request.setMemo("退款金额:"+totalmont);
				try{
					JSONRefundBill refundBill = client.refundbills(request);
					if(refundBill != null){
						mder.setNoteh(refundBill.getInvoice().getId().toString());
						this.update(mder);
					}else{
						throw new RuntimeException("退订失败:申请退订失败");
					}
				}catch (Exception e){
					throw new RuntimeException("退订失败:"+e.getMessage());
				}
			}else{
				Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, usid);
				if(vipbalance!=null){
					vipbalance.setPoint(vipbalance.getPoint()+totalmont);
					this.update(vipbalance);
				}else{
					vipbalance = new Vipbalance();
					vipbalance.setUsid(usid);
					vipbalance.setAcctype("01");
					vipbalance.setPoint(totalmont);
					this.save(vipbalance);
				}

				String hql = "select max(h.seq) from Useryfk h";

				// 用户预付款 先增加全退订金额
				Useryfk yfk = new Useryfk();
				yfk.setUsid(usid);
				yfk.setBdate(Tools.getTodayString());
				yfk.setSzmemo("用户  [" + esfemployeetab.getEmpid() + "]修改订单");
				yfk.setOrderid(orid);
				yfk.setPoint(totalmont);
				yfk.setYfklb(1);// 退订转预付款
				yfk.setYfksc("02");
				yfk.setNote("退订转预付款");

				yfk.setCztp(0);
				int seqs = 0;
				List seqsList = this.find(hql);
				if (seqsList.size() > 0) {
					if (seqsList.get(0) == null) {
						seqs = 0;
					} else {
						seqs = Integer.parseInt(seqsList.get(0) == null ? "0" : seqsList.get(0).toString());
					}
				}
				yfk.setSeq(seqs + 1);
				this.save(yfk);

				// 平台预付款 先从平台预付款转出
				List sysList = this.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
				Sysparv5 sys = null;

				sys = (Sysparv5) sysList.get(0);// 取平台帐号
				Useryfk ptyfk = new Useryfk();
				int seq = 0;
				List seqList = this.find(hql);
				if (seqList.size() > 0) {
					if (seqList.get(0) == null) {
						seq = 0;
					} else {
						seq = Integer.parseInt(seqList.get(0) == null ? "0" : seqList.get(0).toString());
					}
				}
				ptyfk.setSeq(seq + 1);
				ptyfk.setUsid(sys.getPmva());// 用户
				ptyfk.setBdate(Tools.getTodayString());
				ptyfk.setOrderid(orid);
				ptyfk.setPoint(totalmont);
				ptyfk.setYfklb(-1);
				ptyfk.setYfksc("13"); // 用户消费预付款 那平台就是用户预付款转入
				ptyfk.setNote("用户订单强制退订");

				ptyfk.setCztp(1);
				this.save(ptyfk);
			}
		}
		
		
		syslog.setStlg("0012");
		syslog.setBrief("订单强制退订，订单号："+orid   );
		syslog.setNote("订单强制退订，订单号："+orid );
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	 	orderlog.setLogid(getMaxPk("logid", "Orderlog") + 1);
	    orderlog.setOrid(orid);
	    orderlog.setStlg("0209");
	    orderlog.setUsid("");
	    orderlog.setLogtype(Long.parseLong("1"));	    
	    orderlog.setLogdatetime(Tools.getDayTimes());
	    orderlog.setBrief("后台订单强制退订");
	    if(orderlog.getNote()==null||orderlog.getNote().equals("")){
	    	orderlog.setNote(orderlog.getBrief());
	    }
	    this.save(orderlog);
	}
	
	/**
	 * *
	 * Describe:查询逾期未领的订单数
	 * @see com.ectrip.system.hotelcenter.dao.idao.IHotelOrderCenterDAO#queryYuQiOrderNumb(com.ectrip.model.employee.Esfemployeetab, java.lang.String)
	 * @param esfemployeetab
	 * @param scenictype
	 * @return
	 * @author lijingrui
	 * Date:2012-12-4
	 */
    public int queryYuQiOrderNumb(Esfemployeetab esfemployeetab,String scenictype) {

	int numb = 0;

	String today = Tools.getDays();// 当前日期

	StringBuffer hsql = new StringBuffer();
	hsql.append(" from TOrder tor where  tor.dtstartdate<'" + today + "' and tor.ddzt='02' ");
	// 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
	if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
	    String scenics = esfemployeetab.getScenics();
	    if (scenics == null || scenics.equals("")) {
		return 0;
	    }
	    hsql.append(" and tor.id.iscenicid in (" + scenics + ")  ");
	}
	
	if(scenictype!=null&&!scenictype.equals("")){
		hsql.append(" and tor.scenictype='"+scenictype+"'");
	}

	List list = this.find(hsql.toString());
	numb = list.size();

	return numb;
    }
}

