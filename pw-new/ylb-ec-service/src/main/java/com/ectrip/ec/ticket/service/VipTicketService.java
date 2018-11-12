package com.ectrip.ec.ticket.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.app.model.Vipbalance;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.RaftComparepojo;
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
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.ticket.TicketPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Customvip;
import com.ectrip.ec.order.dao.idao.IMOrderDAO;
import com.ectrip.ec.order.dao.idao.ITOrderDAO;
import com.ectrip.ec.order.dao.idao.ITOrderListDAO;
import com.ectrip.ec.order.dao.idao.IYOrderDAO;
import com.ectrip.ec.order.dao.idao.IYOrderListDAO;
import com.ectrip.ec.order.service.iservice.ITZOrderListService;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ec.ticket.dao.idao.IVipticketDAO;
import com.ectrip.ec.ticket.service.iservice.IVipTicketService;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Trip;

public class VipTicketService extends GenericService implements IVipTicketService{
	
	private IVipticketDAO vipticketDao;
	
	 public IMOrderDAO morderdao;
	 public ITOrderDAO torderdao;
	 public ITOrderListDAO torderlistdao;
	 public IYOrderDAO yorderdao;
	 public IYOrderListDAO yorderlistdao;
	 @Autowired
	 private ITZOrderListService tZOrderListService;
	 
	 @Autowired
	 private SysparService sysparService;
//	 private IMbMessageDAO mbmessageDao;
	 private IBalanceCenterDAO balancecenterDao;
	 private ITicketDAO ticketDao;  
	
	

	/**
	 * 
	 * Describe:获取竹筏趟次列表
	 * @auth:lijingrui
	 * @param itickettypeid
	 * @param date
	 * @param zticketid
	 * @param usid
	 * @return
	 * @throws Exception
	 * return:List
	 * Date:Apr 25, 2012
	 */
	public List getRafttripInfoList(String itickettypeid,String date,String zticketid,String usid) throws Exception{
		return vipticketDao.getRafttripInfoList(itickettypeid, date, zticketid, usid);
	}
	
	/**
	 * 
	 * Describe:
	 * @auth:lijingrui
	 * @param newInfo
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * return:List
	 * Date:Apr 26, 2012
	 */
    public List mergenewInfo(List newInfo) throws IllegalAccessException, InvocationTargetException {
    	List mergelist = new ArrayList();
    	OrderPojo pojo = null;
    	OrderPojo pojo1 = null;
    	for (int i = 0; i < newInfo.size(); i++) {
    	    pojo = (OrderPojo) newInfo.get(i);
    	    if (mergelist.size() < 1) {
    		pojo1 = new OrderPojo();
    		BeanUtils.copyProperties(pojo1, pojo);
    		mergelist.add(pojo1);
    	    } else {
    		for (int j = 0; j < mergelist.size(); j++) {
    		    pojo1 = (OrderPojo) mergelist.get(j);
    		    if (pojo.getProductcontrolid() != null && !pojo.getProductcontrolid().equals("")) {
    			if (pojo.getItickettypeid().equals(pojo1.getItickettypeid())
    				&& pojo.getProductcontrolid().equals(pojo1.getProductcontrolid())
    				&& pojo.getTourdate().equals(pojo1.getTourdate())) {
    			    pojo1.setNumb(String.valueOf(Integer.parseInt(pojo1.getNumb()) + Integer.parseInt(pojo.getNumb())));
    			    break;
    			} else {
    			    if (j == mergelist.size() - 1) {
    				mergelist.add(pojo);
    				break;
    			    }
    			}
    		    } else {
    			if (pojo.getItickettypeid().equals(pojo1.getItickettypeid()) && pojo.getTourdate().equals(pojo1.getTourdate())) {
    			    pojo1.setNumb(String.valueOf(Integer.parseInt(pojo1.getNumb()) + Integer.parseInt(pojo.getNumb())));
    			    break;
    			} else {
    			    if (j == mergelist.size() - 1) {
    				mergelist.add(pojo);
    				break;
    			    }
    			}
    		    }
    		}
    	    }
    	}
    	return mergelist;
        }
    
    /**
     * * Describe:
     * 
     * @see com.ectrip.order.service.iservice.IOrderService#calculateNewOrderInfo(java.util.List, java.lang.String, java.lang.String)
     * @param newInfo
     * @param stdt
     * @param ibusinessid
     * @return
     * @author yangguang Date:2012-2-8
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private Map calculateNewOrderInfo(List newInfo, String stdt, String ibusinessid) throws IllegalAccessException,
	    InvocationTargetException {

    	// 统计需要增加的竹筏量和金额
    	Map map = new HashMap();
    	double amont = 0.0;
    	OrderPojo orderlist = null;
    	OrderPojo orderlist1 = null;
    	OrderPojo compare = null;
    	RaftComparepojo raft = null;
    	RaftComparepojo soncompare = null;
    	Productcontrol tripinfo = null;
    	double price = 0.0;
    	List finallist = new ArrayList();// 合并同样的票
    	List addraft = new ArrayList();// 按照同种票同一天进行分组 过滤没有竹筏日期的
    	List daylist = new ArrayList();
    	List newsonticketlist = new ArrayList();
    	List sonticket = null;
    	Edmtickettypetab ticket = null;
    	Edmcrowdkindpricetab kindprice = null;
    	Edmticketcomposepricetab pricepost = null;
    	boolean israft = false;
    	// 合并新增的集合中同样的票
    	for (int i = 0; i < newInfo.size(); i++) {

    	    orderlist = (OrderPojo) newInfo.get(i);
    	    // 剔除无数量的产品
    	    if (orderlist.getNumb() == null || orderlist.getNumb().equals("") || orderlist.getNumb().equals("0")) {
    		newInfo.remove(i);
    		i = i - 1;
    		continue;
    	    }
    	    // 合并产品相同且价格相同的产品 (有竹筏的需竹筏日期趟次相同的才能合并)
    	    if (finallist.size() < 1) {
    		finallist.add(orderlist);
    	    } else {
    		for (int a = 0; a < finallist.size(); a++) {
    		    compare = (OrderPojo) finallist.get(a);
    		    if (orderlist.getTourdate() == null && orderlist.getItickettypeid() == compare.getItickettypeid()) {// 套票不包含竹筏票时只要日期为空且编号相等则叠加数量
    			compare.setNumb(compare.getNumb() + orderlist.getNumb());
    		    } else if (orderlist.getTourdate() != null && orderlist.getTourdate().equals(orderlist.getTourdate())
    			    && orderlist.getItickettypeid() == compare.getItickettypeid()
    			    && orderlist.getProductcontrolid() == orderlist.getProductcontrolid()) {
    			compare.setNumb(compare.getNumb() + orderlist.getNumb());
    		    } else if (a == finallist.size() - 1) {
    			finallist.add(orderlist);
    			break;
    		    }
    		}
    	    }
    	    // 合并
    	    if (daylist.size() < 1) {
    		if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
    		    orderlist1 = new OrderPojo();
    		    BeanUtils.copyProperties(orderlist1, orderlist);
    		    daylist.add(orderlist1);
    		}
    	    } else {
    		for (int j = 0; j < daylist.size(); j++) {
    		    compare = (OrderPojo) daylist.get(j);
    		    if (orderlist.getItickettypeid() == compare.getItickettypeid() && orderlist.getTourdate() != null
    			    && orderlist.getTourdate().equals(compare.getTourdate())) {// 过滤出含有游览日得票
    			// 一般都含有竹筏
    			// 然后根据日期合并，部分趟次
    			compare.setNumb(compare.getNumb() + orderlist.getNumb());
    			break;
    		    } else if (j == (finallist.size() - 1)) {
    			if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
    			    orderlist1 = new OrderPojo();
    			    BeanUtils.copyProperties(orderlist1, orderlist);
    			    daylist.add(orderlist);
    			    break;
    			}
    		    }
    		}
    	    }
    	}
    	List pricelist = null;
    	// 根据主产品取出所有的子产品

    	for (int i = 0; i < finallist.size(); i++) {
    	    israft = false;
    	    orderlist = (OrderPojo) finallist.get(i);
    	    pricelist = ticketDao.getTicketPricelist(Long.parseLong(orderlist.getItickettypeid()), Long.parseLong(orderlist
    		    .getIcrowdkindid()), stdt, ibusinessid);
    	    kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
    	    price = ticketDao.getTicketPrice(orderlist.getItickettypeid().toString(), stdt, kindprice.getIcrowdkindpriceid().toString(),
    		    ibusinessid);
    	    amont += price * Integer.parseInt(orderlist.getNumb());
    	    sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
    	    if (sonticket != null && sonticket.size() > 0) {// 如果有子产品
    		for (int j = 0; j < sonticket.size(); j++) {// 遍历子产品 标记是否含有竹筏
    		    // 把子产品合并到集合
    		    pricepost = (Edmticketcomposepricetab) sonticket.get(j);
    		    ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, pricepost.getItickettypeid());
    		    if (newsonticketlist.size() < 1) {
    			raft = new RaftComparepojo();
    			raft.setIticketid(ticket.getItickettypeid());
    			if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
    			    tripinfo = (Productcontrol) ticketDao
    				    .get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
    			    raft.setTripid(tripinfo.getTripid());
    			    raft.setTourdate(tripinfo.getStdata());
    			    raft.setIvenueid(tripinfo.getIvenueid());
    			    raft.setIvenueareaid(tripinfo.getIvenueareaid());
    			}
    			raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    			raft.setPrice(pricepost.getMactualsaleprice());
    			newsonticketlist.add(raft);
    		    } else {
    			for (int x = 0; x < newsonticketlist.size(); x++) {
    			    soncompare = (RaftComparepojo) newsonticketlist.get(x);
    			    if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
    				tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist
    					.getProductcontrolid()));
    				if (soncompare.getTourdate().equals(tripinfo.getStdata()) && soncompare.getTripid() == tripinfo.getTripid()
    					&& soncompare.getIvenueid() == tripinfo.getIvenueid()
    					&& soncompare.getIvenueareaid() == raft.getIvenueareaid()) {
    				    soncompare.setNumb(soncompare.getNumb() + pricepost.getNumb() * Long.parseLong(orderlist.getNumb()));
    				    break;
    				} else {
    				    if (x == newsonticketlist.size() - 1) {
    					raft = new RaftComparepojo();
    					raft.setIticketid(tripinfo.getItickettypeid());
    					raft.setTripid(tripinfo.getTripid());
    					raft.setTourdate(tripinfo.getStdata());
    					raft.setIvenueid(tripinfo.getIvenueid());
    					raft.setIvenueareaid(tripinfo.getIvenueareaid());
    					raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    					raft.setPrice(pricepost.getMactualsaleprice());
    					newsonticketlist.add(raft);
    					break;
    				    }
    				}
    			    } else {
    				if (soncompare.getIticketid().intValue() == ticket.getItickettypeid().intValue()) {
    				    soncompare.setNumb(soncompare.getNumb() + Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    				} else {
    				    if (x == newsonticketlist.size() - 1) {
    					raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    					raft.setPrice(pricepost.getMactualsaleprice());
    					newsonticketlist.add(raft);
    					break;
    				    }
    				}
    			    }
    			}
    		    }
    		    if (ticket.getBycategorytype().equals("0003")) {// 子产品为竹筏
    			israft = true;
    		    }
    		}
    		if (israft) {
    		    if (addraft.size() < 1) {
    			raft = new RaftComparepojo();
    			if (tripinfo.getProductcontrolid() != null && !tripinfo.getProductcontrolid().equals("")) {
    			    tripinfo = (Productcontrol) ticketDao
    				    .get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
    			    raft.setIticketid(tripinfo.getItickettypeid());
    			    raft.setTripid(tripinfo.getTripid());
    			    raft.setTourdate(tripinfo.getStdata());
    			    raft.setIvenueid(tripinfo.getIvenueid());
    			    raft.setIvenueareaid(tripinfo.getIvenueareaid());
    			}
    			raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    			addraft.add(raft);
    		    } else {
    			tripinfo = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
    			// TODO 要把同产品 同日起 同趟次的合并
    			for (int x = 0; x < addraft.size(); x++) {
    			    raft = (RaftComparepojo) addraft.get(x);
    			    if (raft.getTourdate().equals(tripinfo.getStdata()) && raft.getTripid() == tripinfo.getTripid()
    				    && raft.getIvenueid() == tripinfo.getIvenueid() && raft.getIvenueareaid() == raft.getIvenueareaid()) {
    				raft.setNumb(raft.getNumb() + Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    				break;
    			    } else if (x == addraft.size() - 1) {
    				raft = new RaftComparepojo();
    				raft.setTripid(tripinfo.getTripid());
    				raft.setTourdate(tripinfo.getStdata());
    				raft.setIvenueid(tripinfo.getIvenueid());
    				raft.setIvenueareaid(tripinfo.getIvenueareaid());
    				raft.setNumb(Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
    				raft.setIticketid(tripinfo.getItickettypeid());
    				addraft.add(raft);
    				break;
    			    }
    			}
    		    }
    		}
    	    }
    	}
    	map.put("amont", amont);// 新增产品所需的金额
    	map.put("raftlist", addraft);// 新增的按照日期、竹筏趟次统计出的需要新增的竹筏列表及数量
    	map.put("daylist", daylist);// 新增的按照产品、日期统计出的产品列表
    	map.put("addzproduct", newsonticketlist);// 把新增的产品全部拆成子票
    	return map;
    	
    }
	
    /**
     * 
     * Describe:
     * @auth:lijingrui
     * @param orid
     * @param iscenicid
     * @param orderInfo
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * return:Map
     * Date:Apr 26, 2012
     */
    private Map calculateOldOrder(String orid, String iscenicid, List orderInfo) throws IllegalAccessException, InvocationTargetException {
    	// 获取原始订单
    	List oldlist = torderlistdao.getTOrderListList(orid, iscenicid);
    	List chalist = new ArrayList();
    	TOrderlist newtorderlist = null;
    	TOrderlist oldtorderlist = null;
    	TOrderlist ctorderlist = null;
    	for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
    	    oldtorderlist = new TOrderlist();
    	    BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));
    	    for (int j = 0; j < orderInfo.size(); j++) {// 修改后的订单
    		newtorderlist = (TOrderlist) orderInfo.get(j);
    		if (newtorderlist.getOrderlistid().equals(oldtorderlist.getOrderlistid())
    			&& newtorderlist.getIscenicid().equals(oldtorderlist.getIscenicid())) {
    		    if (newtorderlist.getNumb().intValue() != oldtorderlist.getNumb().intValue()) {
    			ctorderlist = new TOrderlist();
    			BeanUtils.copyProperties(ctorderlist, oldtorderlist);
    			// 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
    			ctorderlist.setNumb(newtorderlist.getNumb() - oldtorderlist.getNumb());
    			ctorderlist.setId(new TOrderlistId(Long.parseLong(oldtorderlist.getOrderlistid()), oldtorderlist.getOrid(), Long
    				.parseLong(iscenicid)));
    			ctorderlist.setAmnt(oldtorderlist.getPric() * (newtorderlist.getNumb() - oldtorderlist.getNumb()));
    			chalist.add(ctorderlist);
    		    }
    		}
    	    }
    	}
    	// 按照日期趟次码头分组竹筏
    	List raftlist = new ArrayList();
    	List daylist = new ArrayList();// 按照竹筏日期分组
    	List allzlist = new ArrayList();
    	List tzlist = null;
    	TZorderlist tzorderlist = null;
    	RaftComparepojo checkraft = null;
    	RaftComparepojo newraft = null;
    	RaftComparepojo newraft1 = null;
    	Edmtickettypetab checkticket = null;
    	double amount = 0.0;
    	// 遍历修改后和修改前订单对比出来的产品列表
    	for (int i = 0; i < chalist.size(); i++) {
    	    ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
    	    oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(ctorderlist.getOrderlistid()),
    		    orid, Long.parseLong(iscenicid)));
    	    amount += oldtorderlist.getPric() * ctorderlist.getNumb();// 叠加价格
    	    // 需找子产品
    	    tzlist = tZOrderListService.getTZorderlist(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid));
    	    // 拿出原订单的对应产品
    	    for (int j = 0; j < tzlist.size(); j++) {// 循环有差异的子票列表
    		tzorderlist = (TZorderlist) tzlist.get(j);
    		// 子产品对象
    		checkticket = (Edmtickettypetab) yorderlistdao.get(Edmtickettypetab.class, tzorderlist.getIztickettypeid());
    		newraft = new RaftComparepojo();
    		newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
    		newraft.setTripid(tzorderlist.getTripid());
    		newraft.setIvenueid(tzorderlist.getIvenueid());
    		newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
    		newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
    		newraft.setParentticketid(oldtorderlist.getItickettypeid());
    		// 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
    		newraft.setNumb(ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
    		newraft.setIticketid(tzorderlist.getIztickettypeid());
    		newraft.setPrice(tzorderlist.getZpric());
    		// 挑选出针对原订单新增和退订的产品列表
    		if (allzlist.size() < 1) {
    		    newraft1 = new RaftComparepojo();
    		    BeanUtils.copyProperties(newraft1, newraft);
    		    allzlist.add(newraft1);
    		} else {
    		    for (int x = 0; x < allzlist.size(); x++) {
    			checkraft = (RaftComparepojo) allzlist.get(x);
    			if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10))
    				&& checkraft.getIticketid().intValue() == tzorderlist.getIztickettypeid().intValue()) {
    			    checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb()
    				    / oldtorderlist.getNumb());
    			    break;
    			} else {
    			    if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
    				newraft1 = new RaftComparepojo();
    				BeanUtils.copyProperties(newraft1, newraft);
    				// 跳出循环
    				allzlist.add(newraft1);
    				break;
    			    }
    			}
    		    }
    		}
    		// 子票是竹筏再检测 不是竹筏直接跳过
    		if (checkticket.getBycategorytype().equals("0003")) {
    		    // 按照竹筏日期及趟次挑选票及数量
    		    if (raftlist.size() < 1) {
    			// 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
    			newraft1 = new RaftComparepojo();
    			BeanUtils.copyProperties(newraft1, newraft);
    			raftlist.add(newraft1);
    		    } else {
    			for (int x = 0; x < raftlist.size(); x++) {// 竹筏趟次根据日期趟次码头分组后的集合
    			    RaftComparepojo checkraft1 = (RaftComparepojo) raftlist.get(x);
    			    // 如果集合中已存在则只叠加数量 这里忽略子产品对应的主产品只计算竹筏的数量
    			    if (checkraft1.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10))
    				    && tzorderlist.getTripid().equals(checkraft1.getTripid())
    				    && checkraft1.getIvenueid().equals(tzorderlist.getIvenueid())
    				    && checkraft1.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {
    				checkraft1.setNumb(checkraft1.getNumb() + tzorderlist.getZnumb() / oldtorderlist.getNumb()
    					* ctorderlist.getNumb());
    				break;// 叠加完之后跳出循环
    			    } else {
    				if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
    				    // 跳出循环
    				    // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
    				    newraft1 = new RaftComparepojo();
    				    BeanUtils.copyProperties(newraft1, newraft);
    				    raftlist.add(newraft);
    				    break;
    				}
    			    }
    			}
    		    }
    		    // 只统计日控制的话
    		    // 只需要判断是否含有竹筏就行所以也就只需要循环一次
    		    // 直接跳出.
    		    // 根据日期挑选出来的需要新增和退订的竹筏列表
    		    if (daylist.size() < 1) {
    			newraft = new RaftComparepojo();
    			newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
    			newraft.setTripid(tzorderlist.getTripid());
    			newraft.setIvenueid(tzorderlist.getIvenueid());
    			newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
    			newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
    			newraft.setParentticketid(oldtorderlist.getItickettypeid());
    			// 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
    			newraft.setNumb(ctorderlist.getNumb());
    			newraft.setIticketid(oldtorderlist.getItickettypeid());
    			daylist.add(newraft);
    		    } else {
    			for (int x = 0; x < daylist.size(); x++) {
    			    checkraft = (RaftComparepojo) daylist.get(x);
    			    if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10))
    				    && checkraft.getIticketid().intValue() == oldtorderlist.getItickettypeid()
    				    && checkraft.getTripid().intValue() == tzorderlist.getTripid().intValue()) {
    				checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb()
    					/ oldtorderlist.getNumb());
    				break;// 叠加完之后跳出循环
    			    } else {
    				if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
    				    // 跳出循环
    				    newraft = new RaftComparepojo();
    				    newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
    				    newraft.setTripid(tzorderlist.getTripid());
    				    newraft.setIvenueid(tzorderlist.getIvenueid());
    				    newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
    				    newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
    				    newraft.setParentticketid(oldtorderlist.getItickettypeid());
    				    // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
    				    newraft.setNumb(ctorderlist.getNumb());
    				    newraft.setIticketid(oldtorderlist.getItickettypeid());
    				    daylist.add(newraft);
    				    break;
    				}
    			    }
    			}
    		    }
    		}
    	    }
    	}
    	Map map = new HashMap();
    	map.put("chaticket", chalist);// 订单修改前后对比出的产品差异列表
    	map.put("amount", amount);// 修改订单之后的差异金额，正则需要用户支付，负则退还用户
    	map.put("charraft", raftlist);// 订单修改前后统计出的竹筏趟次 新增与退订的列表
    	map.put("chaday", daylist);// 订单修改前后统计的产品按照日期产品分组的列表
    	map.put("allzlist", allzlist);// 所有要增减的子票列表
    	return map;
        }
    
    
    /**
     * 
     * Describe:计算退订手续费
     * @auth:lijingrui
     * @param oldtdlist
     * @param usid
     * @param iscenicid
     * @return
     * @throws ParseException
     * return:double
     * Date:Apr 26, 2012
     */
    private double calculatetdmont(List oldtdlist, String usid, String iscenicid) throws ParseException {
	RaftComparepojo compare = null;
	Productcontrol control = null;
	Ticketxgz tdrule = null;
	Custom custom = (Custom) morderdao.get(Custom.class, usid);
	Changebackrate fl = null;
	double tdmont = 0.0;
	Prdtripvenuemanage triptime = null;
	// 计算出退订的手续费
	for (int i = 0; i < oldtdlist.size(); i++) {
	    compare = (RaftComparepojo) oldtdlist.get(i);
	    control = ticketDao
		    .getTripControl(compare.getTripid(), compare.getIvenueid(), compare.getIvenueareaid(), compare.getTourdate());
	    if (control == null || control.getBystate().intValue() == 1) {
		tdrule = morderdao.ticketbackrule(compare.getIticketid(), Long.parseLong(iscenicid), custom.getLgtp());
		double tdfl=0.0;
		if (tdrule != null) {// 如果退订规则不为空
		    if (tdrule.getJsfs().equals("0001")) {// 按小时
			triptime = ticketDao.getTripInfo(compare.getTripid(), compare.getIvenueid(), compare.getIvenueareaid(), compare
				.getTourdate(), iscenicid, compare.getIticketid().toString());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
			Date d2 = df.parse(Tools.getNowString());
			if(d1.before(d2)){
			    tdfl=tdrule.getXyrate();
			}else{
			    long diff = d1.getTime() - d2.getTime();
			    long hours = diff / (1000 * 60 * 60);
			    fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001");
			    tdfl=fl.getTdfl();
			}
		    } else if (tdrule.getJsfs().equals("0002")) {// 按天
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
			Date d2 = df.parse(Tools.getNowString());
			if(d1.before(d2)){
			    tdfl=tdrule.getXyrate();
			}else{
			    long diff = d1.getTime() - d2.getTime();
			    long days = diff / (1000 * 60 * 60 * 24);
			    fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
			    tdfl=fl.getTdfl();
			}
		    } else {// 常年
			fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
		    }
		    tdmont += compare.getPrice() *tdfl* compare.getNumb();
		}
	    }
	}
	return tdmont;
    }
    
    /**
     * 
     * Describe: 计算传入的list的金额
     * @auth:lijingrui
     * @param list
     * @return
     * return:double
     * Date:Apr 26, 2012
     */
    private double calculateAddMont(List list) {
	double totalmont = 0.0;
	TOrderlist torderlist = null;
	if (list != null && list.size() > 0) {
	    for (int i = 0; i < list.size(); i++) {
		torderlist = (TOrderlist) list.get(i);
		totalmont += torderlist.getAmnt();
	    }
	}
	return totalmont;
    }
    

    /**
     * 
     * Describe: 新增订单(退订单，或者增量订单)
     * @auth:lijingrui
     * @param addlist
     * @param oldorid
     * @param neworid
     * @param iscenicid
     * @param currentusid
     * @param oldmorder
     * @param totalmont
     * @param ortp
     * @param stopraft
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException
     * return:double
     * Date:Apr 26, 2012
     */
    private double addNewOrder(List addlist, String oldorid, String neworid, String iscenicid, String currentusid, MOrder oldmorder,
	    double totalmont, String ortp, int stopraft) throws IllegalAccessException, InvocationTargetException, ParseException {

    	Custom user = (Custom) yorderdao.get(Custom.class, oldmorder.getUsid());
    	// 开始封装订单
    	MOrder morder = null;
    	YOrder yorder = null;
    	YOrderlist yorderlist = null;
    	YZorderlist yzorderlist = null;
    	RaftComparepojo compare = null;
    	double totaltdsx = 0.0;
    	if (addlist != null && addlist.size() > 0) {
    	    // TODO 增量订单neworderlist 和 orderlistInfo与 原订单中中数量为对比后 挑选出来的数量为正数的产品
    	    oldmorder = (MOrder) ticketDao.get(MOrder.class, oldorid);
    	    morder = new MOrder();
    	    yorder = new YOrder();
    	    morder.setOrid(neworid);
    	    morder.setOrtp(ortp);// 订单类型 03：增量订单
    	    morder.setUsid(oldmorder.getUsid());
    	    morder.setOrda(Tools.getDays());
    	    morder.setOrti(Tools.getNowString().substring(11, Tools.getNowString().length()));
    	    morder.setIsj(0l);
    	    morder.setYhamnt(0.0);// 优惠金额
    	    morder.setMont(Math.abs(totalmont));
    	    morder.setYhamnt(0.0);
    	    morder.setZfmont(Math.abs(totalmont));// 支付的金额
    	    morder.setIsallcp(0l);// 是否全部出票
    	    morder.setZffs("06");// 支付方式
    	    morder.setBank("92");// 支付银行
    	    morder.setZfusid(oldmorder.getZfusid());// 订单支付人
    	    morder.setPayorid("");// 支付订单号
    	    morder.setBankdata(Tools.getDays());// 支付日期
    	    morder.setBanktime(Tools.getNowTimeString());// 支付时间
    	    morder.setSrid(oldorid);// 对应原订单号
    	    morder.setIsjl(0l);
    	    morder.setTpfs("02");

    	    ticketDao.save(morder);
    	    yorder.setId(new YOrderId(morder.getOrid(), Long.parseLong(iscenicid)));
    	    yorder.setScenictype("01");// 服务商类别 01 景区 06酒店 07 旅行社 08 超市 09 租赁公司
    	    yorder.setUsid(morder.getUsid());// 游客编号
    	    yorder.setIbusinessid(user.getIbusinessid());// 业务id
    	    yorder.setSztravelbillno("");// 行程单号
    	    yorder.setIregionalid(0l);// 客源地id
    	    yorder.setTdlx("");// 团队性质
    	    yorder.setDtstartdate(Tools.getDays());// 游览开始日期
    	    yorder.setDtenddate(Tools.getDays());// 游览结束日期
    	    yorder.setDyusid("");// 导游id
    	    yorder.setOrnm("");// 领票人姓名
    	    yorder.setOrzj("");// 领票人证件类别
    	    yorder.setOrhm("");// 领票人证件号码
    	    yorder.setFaxno("");// 传真号
    	    yorder.setOrph("");// 领票人电话
    	    yorder.setMont(Math.abs(totalmont));// 订单金额
    	    yorder.setYhamnt(0.0);// 优惠金额
    	    yorder.setZfmont(Math.abs(totalmont));// 支付的金额
    	    yorder.setYoufei(0.0);// 邮费
    	    yorder.setTpfs("02");
    	    // ticketDao.save(torder);
    	    ticketDao.save(yorder);
    	    TOrderlist torderlist = null;
    	    TOrderlist old = null;
    	    TOrderlist newtorderlist = null;
    	    TZorderlist newzlist = null;
    	    long indexpk = getMaxPk("id.orderlistid", new String[] { "id.iscenicid" }, new Long[] { Long.parseLong(iscenicid) },
    		    new String[] { "id.orid" }, new String[] { oldorid }, "TOrderlist");
    	    int pk = 0;

    	    for (int i = 0; i < addlist.size(); i++) {
    		torderlist = (TOrderlist) addlist.get(i);
    		yorderlist = new YOrderlist();
    		yorderlist.setId(new YOrderlistId(new Long(i) + 1, morder.getOrid(), Long.parseLong(iscenicid)));
    		yorderlist.setIcrowdkindpriceid(torderlist.getIcrowdkindpriceid());
    		yorderlist.setIcrowdkindid(torderlist.getIcrowdkindid());
    		yorderlist.setItickettypeid(torderlist.getItickettypeid());
    		yorderlist.setDtstartdate(torderlist.getDtstartdate());// 开始日期
    		yorderlist.setDtenddate(torderlist.getDtenddate());// 结束日期
    		yorderlist.setPric(torderlist.getPric());// 单价
    		yorderlist.setJsprice(torderlist.getJsprice());// 结算价
    		yorderlist.setNumb(Math.abs(torderlist.getNumb()));// 数量
    		yorderlist.setYhnumb(0l);// 优惠数量
    		yorderlist.setAmnt(Math.abs(torderlist.getAmnt()));// 金额
    		yorderlist.setYhamnt(Math.abs(torderlist.getYhamnt()));// 优惠金额
    		yorderlist.setIoffersschemeid(0l);// 优惠方案ID
    		yorderlistdao.save(yorderlist);
    		if (torderlist.getId() != null) {// 退订时ID 肯定有, 如果是增量 那么ID可能有 可能没有有就修改 没有就新增
    		    old = (TOrderlist) ticketDao.get(TOrderlist.class, torderlist.getId());
    		    // TODO 先获取原先的信息 然后再修改
    		    List list = tZOrderListService.getTZorderlist(old.getId().getOrderlistid(), old.getId().getOrid(), old.getId()
    			    .getIscenicid());
    		    for (int y = 0; y < list.size(); y++) {
    			double tdsx = 0.0;
    			newzlist = (TZorderlist) list.get(y);

    			yzorderlist = new YZorderlist();
    			yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId()
    				.getOrid(), yorderlist.getId().getIscenicid()));
    			yzorderlist.setIcrowdkindpriceid(newzlist.getIcrowdkindpriceid());
    			yzorderlist.setIcrowdkindid(newzlist.getIcrowdkindid());
    			yzorderlist.setItickettypeid(newzlist.getItickettypeid());
    			yzorderlist.setIztickettypeid(newzlist.getIztickettypeid());
    			yzorderlist.setDtstartdate(newzlist.getDtstartdate());
    			yzorderlist.setDtenddate(newzlist.getDtenddate());
    			yzorderlist.setTripid(newzlist.getTripid());
    			yzorderlist.setIvenueid(newzlist.getIvenueid());
    			yzorderlist.setIvenueareaid(newzlist.getIvenueareaid());
    			yzorderlist.setIvenueseatsid(newzlist.getIvenueseatsid());
    			yzorderlist.setZpric(newzlist.getZpric());
    			yzorderlist.setZnumb(Math.abs(newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
    			yzorderlist.setZyhnumb(0l);
    			yzorderlist.setZyhamnt(0.0);
    			yzorderlist.setZamnt(Math.abs(newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
    			yzorderlist.setJsprice(newzlist.getJsprice());
    			newzlist.setZamnt(Math.abs(newzlist.getZamnt() + newzlist.getZpric() * newzlist.getZnumb() / old.getNumb()
    				* torderlist.getNumb()));
    			newzlist.setZnumb(Math.abs(newzlist.getZnumb() + newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
    			if (ortp.equals("02")&&!oldmorder.getDdzt().equals("23")) {//退订且原订单状态不为申请单
    			    tdsx = gettdsx(yzorderlist, oldmorder.getUsid());
    			    yzorderlist.setMhandcharge(tdsx);
    			}else{
    			    yzorderlist.setMhandcharge(0.0);
    			}
    			yorderlistdao.save(yzorderlist);
    			if (newzlist.getZnumb().intValue() < 1) {
    			    yorderlistdao.delete(newzlist);
    			} else {
    			    yorderlistdao.update(newzlist);
    			}
    			if (ortp.equals("02")&&!oldmorder.getDdzt().equals("23")) {
    			    if (yorderlist.getMhandcharge() != null && !yorderlist.getMhandcharge().equals("")) {
    				yorderlist.setMhandcharge(yorderlist.getMhandcharge() + tdsx);
    			    } else {
    				yorderlist.setMhandcharge(tdsx);
    			    }
    			}else{
    			    yorderlist.setMhandcharge(0.0);
    			}

    		    }
    		    old.setNumb(Math.abs(old.getNumb() + torderlist.getNumb()));
    		    old.setAmnt(Math.abs(torderlist.getAmnt() + old.getAmnt()));
    		    if (old.getNumb().intValue() < 1) {
    			yorderlistdao.delete(old);
    		    } else {
    			yorderlistdao.update(old);
    		    }
    		} else {
    		    pk++;
    		    newtorderlist = new TOrderlist();
    		    BeanUtils.copyProperties(newtorderlist, torderlist);
    		    newtorderlist.setId(new TOrderlistId(new Long(indexpk + pk), oldorid, Long.parseLong(iscenicid)));
    		    newtorderlist.setAmnt(Math.abs(newtorderlist.getAmnt()));
    		    newtorderlist.setNumb(Math.abs(newtorderlist.getNumb()));
    		    newtorderlist.setJsprice(Math.abs(newtorderlist.getJsprice()));
    		    yorderlistdao.saveOrUpdate(newtorderlist);
    		    int indexpk2 = 0;
    		    List<TZorderlist> tzorderlists = newtorderlist.getZorderlist();
    		    for (int y = 0; y < tzorderlists.size(); y++) {
    			newzlist = new TZorderlist();
    			BeanUtils.copyProperties(newzlist, tzorderlists.get(y));
    			newzlist.setId(new TZorderlistId(new Long(y + 1), newtorderlist.getId().getOrderlistid(), oldorid, newtorderlist
    				.getId().getIscenicid()));
    			yorderlistdao.save(newzlist);
    			yzorderlist = new YZorderlist();
    			yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId()
    				.getOrid(), yorderlist.getId().getIscenicid()));
    			yzorderlist.setIcrowdkindpriceid(newzlist.getIcrowdkindpriceid());
    			yzorderlist.setIcrowdkindid(newzlist.getIcrowdkindid());
    			yzorderlist.setItickettypeid(newzlist.getItickettypeid());
    			yzorderlist.setIztickettypeid(newzlist.getIztickettypeid());
    			yzorderlist.setDtstartdate(newzlist.getDtstartdate());
    			yzorderlist.setDtenddate(newzlist.getDtenddate());
    			yzorderlist.setTripid(newzlist.getTripid());
    			yzorderlist.setIvenueid(newzlist.getIvenueid());
    			yzorderlist.setIvenueareaid(newzlist.getIvenueareaid());
    			yzorderlist.setIvenueseatsid(newzlist.getIvenueseatsid());
    			yzorderlist.setZpric(newzlist.getZpric());
    			yzorderlist.setZnumb(Math.abs(newzlist.getZnumb()));
    			yzorderlist.setZyhnumb(0l);
    			yzorderlist.setZyhamnt(0.0);
    			yzorderlist.setZamnt(Math.abs(newzlist.getZamnt()));
    			yzorderlist.setJsprice(Math.abs(newzlist.getJsprice()));
    			yorderlistdao.save(yzorderlist);
    		    }
    		}
    		if (ortp.equals("02")) {
    		    totaltdsx += yorderlist.getMhandcharge();
    		}
    	    }

    	    if (ortp.equals("02")) {// 退订
    		morder.setDdzt("06");// 订单状态 06 退订已退款
    		morder.setTpsx(Math.abs(totaltdsx));
    		morder.setTpmont(Math.abs(totalmont));
    		morder.setNotef("00");
    		morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
    		// morder.setNotef("05");
    	    } else if (ortp.equals("03")) {// 增量
    		morder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
    		morder.setTpsx(0.0);
    		morder.setTpmont(totalmont);
    		morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
    		morder.setNotef("00");
    	    }
    	    ticketDao.update(morder);
    	    if (ortp.equals("02")) {// 退订
    		yorder.setNotef("00");
    		yorder.setTpsx(Math.abs(totaltdsx));
    		yorder.setTpmont(Math.abs(totalmont));
    		yorder.setDdzt("06");// 订单状态 06 退订已退款
    		yorder.setTpdate(morder.getTpdate());
    		// yorder.setNotef("05");
    	    } else if (ortp.equals("03")) {// 增量
    		yorder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
    		yorder.setTpsx(0.0);
    		yorder.setTpmont(totalmont);
    		yorder.setTpdate(morder.getTpdate());
    	    }

    	    ticketDao.update(yorder);
    	    // 计算预付款,退订手续费tdsx一般都是负数

    	    minusUserYfk(user.getUsid(), oldmorder.getZfusid(), oldmorder.getOrid(), Math.abs(totalmont), totaltdsx, ortp);
    	}
    	return totaltdsx;
        
    }
    
    /**
     * 
     * Describe:
     * @auth:lijingrui
     * @param zorderlist
     * @param usid
     * @return
     * @throws ParseException
     * return:double
     * Date:Apr 26, 2012
     */
    public double gettdsx(YZorderlist zorderlist, String usid) throws ParseException {
    	Productcontrol control = null;
    	Ticketxgz tdrule = null;
    	Custom custom = (Custom) morderdao.get(Custom.class, usid);
    	Changebackrate fl = null;
    	double tdmont = 0.0;
    	Prdtripvenuemanage triptime = null;
    	control = ticketDao.getTripControl(zorderlist.getTripid(), zorderlist.getIvenueid(), zorderlist.getIvenueareaid(), zorderlist
    		.getDtstartdate().substring(0, 10));
    	if (control == null || control.getBystate().intValue() == 1) {
    	    tdrule = morderdao.ticketbackrule(zorderlist.getIztickettypeid(), zorderlist.getId().getIscenicid(), custom.getLgtp());
    	    if (tdrule != null) {// 如果退订规则不为空
    		double tdfl=0.0;
    		if (tdrule.getJsfs().equals("0001")) {// 按小时
    		    triptime = ticketDao.getTripInfo(zorderlist.getTripid(), zorderlist.getIvenueid(), zorderlist.getIvenueareaid(),
    			    zorderlist.getDtstartdate().substring(0, 10), zorderlist.getId().getIscenicid().toString(), zorderlist
    				    .getIztickettypeid().toString());
    		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		    Date d1 = df.parse(zorderlist.getDtstartdate());
    		    Date d2 = df.parse(Tools.getNowString());
    		    long diff = d1.getTime() - d2.getTime();
    		    long hours = diff / (1000 * 60 * 60);
    		    if(d1.before(d2)){
    			tdfl=tdrule.getXyrate2();
    		    }else{
    			fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001");
    			tdfl=fl.getTdfl();
    		    }
    		} else if (tdrule.getJsfs().equals("0002")) {// 按天
    		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		    Date d1 = df.parse(zorderlist.getDtstartdate());
    		    Date d2 = df.parse(Tools.getNowString());
    		    long diff = d1.getTime() - d2.getTime();
    		    long days = diff / (1000 * 60 * 60 * 24);
    		    if(d1.before(d2)){
    			tdfl=tdrule.getXyrate2();
    		    }else{
    			fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
    			tdfl=fl.getTdfl();
    		    }
    		} else {// 常年
    		    fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
    		}
    		tdmont += zorderlist.getZpric() * tdfl * zorderlist.getZnumb();
    	    }
    	}
    	return tdmont;
        }
    
    /**
     * 
     * Describe:
     * @auth:lijingrui
     * @param currentusid
     * @param zfusid
     * @param orid
     * @param mont
     * @param tdsx
     * @param type
     * @return
     * return:boolean
     * Date:Apr 26, 2012
     */
    private boolean minusUserYfk(String currentusid, String zfusid, String orid, double mont, double tdsx, String type) {
    	// 用户预付款 先增加全退订金额

    	Useryfk yfk = new Useryfk();
    	Integer seqs = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
    	yfk.setUsid(zfusid);
    	yfk.setBdate(Tools.getTodayString());
    	yfk.setSzmemo("用户  [" + currentusid + "]修改订单");
    	yfk.setOrderid(orid);
    	yfk.setPoint(mont);
    	if (type.equals("03")) {
    	    yfk.setYfklb(-1);
    	    yfk.setYfksc("03"); // 消费预付款
    	    yfk.setNote("订单消费");
    	} else {
    	    yfk.setYfklb(1);// 退订转预付款 
    	    yfk.setYfksc("02");
    	    yfk.setNote("退订转预付款");
    	}
    	yfk.setCztp(0);
    	yfk.setSeq(seqs + 1);
    	balancecenterDao.useryfksave(yfk);

    	// 平台预付款 先从平台预付款转出
    	List sysList = morderdao.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
    	Sysparv5 sys = null;

    	sys = (Sysparv5) sysList.get(0);// 取平台帐号
    	Useryfk ptyfk = new Useryfk();
    	ptyfk.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq") + 1);
    	ptyfk.setUsid(sys.getPmva());// 用户
    	ptyfk.setBdate(Tools.getTodayString());
    	ptyfk.setOrderid(orid);
    	ptyfk.setPoint(mont);
    	if (type.equals("03")) {
    	    ptyfk.setYfklb(1);
    	    ptyfk.setYfksc("14"); // 用户消费预付款 那平台就是用户预付款转入
    	    ptyfk.setNote("用户订单消费");
    	} else {
    	    ptyfk.setYfklb(-1);// 预付款转出
    	    ptyfk.setYfksc("13");
    	    ptyfk.setNote("用户退订退款");
    	}
    	ptyfk.setCztp(0);
    	balancecenterDao.useryfksave(ptyfk);

    	if (tdsx > 0) {
    	    // 然后收取用户手续费
    	    Useryfk yfk1 = new Useryfk();
    	    Integer seqs1 = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
    	    yfk1.setUsid(zfusid);
    	    yfk1.setBdate(Tools.getTodayString());
    	    yfk1.setSzmemo("用户  [" + currentusid + "]修改订单");
    	    yfk1.setOrderid(orid);
    	    yfk1.setPoint(tdsx);
    	    yfk1.setYfklb(-1);
    	    yfk1.setYfksc("17"); // 消费预付款
    	    yfk1.setNote("退订收取手续费");
    	    yfk1.setCztp(0);
    	    yfk1.setSeq(seqs1 + 1);
    	    balancecenterDao.useryfksave(yfk1);

    	    // 平台用户收入手续
    	    Useryfk ptyfk1 = new Useryfk();
    	    ptyfk1.setSeq(balancecenterDao.getMaxSeq("Useryfk", "seq") + 1);
    	    ptyfk1.setUsid(sys.getPmva());// 用户
    	    ptyfk1.setBdate(Tools.getTodayString());
    	    ptyfk1.setOrderid(orid);
    	    ptyfk1.setPoint(tdsx);
    	    ptyfk1.setYfklb(1);
    	    ptyfk1.setYfksc("16"); // 用户消费预付款 那平台就是用户预付款转入
    	    ptyfk1.setNote("退订收入手续费");
    	    ptyfk1.setCztp(0);
    	    balancecenterDao.useryfksave(ptyfk1);
    	}

    	return true;
        }
    
    /**
     * *
     * Describe:
     * @see com.ectrip.ticket.service.iservice.IVipTicketService#editOrderCenter(java.util.List, java.util.List, java.lang.String[], java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     * @param orderlistInfo
     * @param neworderlist
     * @param orids
     * @param oldorid
     * @param iscenicid
     * @param stdt
     * @param ibusiness
     * @param usid
     * @return
     * @throws Exception
     * @author lijingrui
     * Date:Apr 26, 2012
     */
	 public Map editOrderCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid,
			    String iscenicid, String stdt, String ibusiness, String usid) throws Exception {
		 	MOrder oldmorder = (MOrder) morderdao.get(MOrder.class, oldorid);
			Map returnmap = new HashMap();
			Map newmap = null;
			if (neworderlist != null) {
			    neworderlist = mergenewInfo(neworderlist);
			    newmap = calculateNewOrderInfo(neworderlist, stdt, ibusiness);
			}
			Map oldmap = calculateOldOrder(oldorid, iscenicid, orderlistInfo);
			List oldtdlist = new ArrayList();// 原订单要退订的产品列表
			List oldaddlist = new ArrayList();// 原订单要新增的产品列表
			List ordereditsonlist = (List) oldmap.get("allzlist");
			List addsonProductlist = null;
			if (newmap != null) {
			    addsonProductlist = (List) newmap.get("addzproduct");
			}
			RaftComparepojo compare1 = null;
			RaftComparepojo compare = null;
			// 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
			if (ordereditsonlist != null) {
			    for (int i = 0; i < ordereditsonlist.size(); i++) {
				compare = (RaftComparepojo) ordereditsonlist.get(i);
				if (compare.getNumb() > 0) {
				    oldaddlist.add(compare);
				}
				if (compare.getNumb() < 0) {
				    oldtdlist.add(compare);
				}
			    }
			}
			// 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
			if (addsonProductlist != null && oldaddlist != null) {
			    for (int i = 0; i < addsonProductlist.size(); i++) {
				compare = (RaftComparepojo) addsonProductlist.get(i);
				for (int j = 0; j < oldaddlist.size(); j++) {
				    compare1 = (RaftComparepojo) oldaddlist.get(j);
				    if (compare.getTripid() != null && !compare.getTripid().equals("")) {
					if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate())
						&& compare.getTripid().equals(compare1.getTripid())) {
					    compare1.setNumb(compare1.getNumb() + compare.getNumb());
					} else {
					    if (j == oldaddlist.size() - 1) {
						oldaddlist.add(compare);
						break;
					    }
					}
				    } else {
					if (compare.getIticketid() == compare1.getIticketid() && compare.getTourdate().equals(compare1.getTourdate())) {
					    compare1.setNumb(compare1.getNumb() + compare.getNumb());
					} else {
					    if (j == oldaddlist.size() - 1) {
						oldaddlist.add(compare);
						break;
					    }
					}
				    }
				}
			    }
			}
			// 筛选出需要收取退订手续费的产品
			// oldtdlist 为要收取手续费的退订产品.
			if (oldtdlist != null && oldaddlist != null) {
			    for (int i = 0; i < oldtdlist.size(); i++) {
				compare = (RaftComparepojo) oldtdlist.get(i);
				for (int j = 0; j < oldaddlist.size(); j++) {
				    compare1 = (RaftComparepojo) oldaddlist.get(j);
				    if (compare.getTripid() != null && !compare.getTripid().equals("")) {
					if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate())
						&& compare.getTripid().equals(compare1.getTripid())) {
					    if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
						oldtdlist.remove(compare);
						i = i - 1;
						break;
					    } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
						compare.setNumb(compare1.getNumb() + compare1.getNumb());
						break;
					    }
					}
				    } else {
					if (compare.getIticketid() == compare1.getIticketid() && compare.getTourdate().equals(compare1.getTourdate())) {
					    compare1.setNumb(compare1.getNumb() + compare.getNumb());
					    break;
					}
				    }
				}
			    }
			}
			// 原订单修改后的差异集合
			List chalist = (List) oldmap.get("chaticket");
			List addlist = new ArrayList();
			List backlist = new ArrayList();
			TOrderlist orderlist = null;
			OrderPojo newTicket = null;
			for (int i = 0; i < chalist.size(); i++) {
			    orderlist = (TOrderlist) chalist.get(i);
			    if (orderlist.getNumb() > 0) {
				addlist.add(orderlist);
			    } else {
				backlist.add(orderlist);
			    }
			}
			List sonticket = null;
			Edmcrowdkindpricetab kindprice = null;
			Edmtickettypetab ticket = null;
			Edmtickettypetab zticket = null;
			List pricelist = null;
			List<TZorderlist> tzlist = null;
			Edmticketcomposepricetab pricepost = null;
			TOrderlist newproduct = null;
			TOrderlist oldtorderlist = null;
			TZorderlist tzorderlist = null;
			Productcontrol tripinfo = null;
			boolean isadd = false;
			// 如果即修改了原订单,又新增了产品
			if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
			    // 新增的票种和原来的订单进行对比
			    for (int i = 0; i < neworderlist.size(); i++) {
				newTicket = (OrderPojo) neworderlist.get(i);
				for (int j = 0; j < addlist.size(); j++) {
				    orderlist = (TOrderlist) addlist.get(j);
				    // 如果产品相同
				    if (orderlist.getItickettypeid().intValue() == Integer.parseInt(newTicket.getItickettypeid())) {
					tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long
						.parseLong(iscenicid));
					// 且newTicket有浏览日期(有浏览日期说明票含竹筏)
					if (newTicket.getTourdate() != null) {
					    // 且浏览日期相同
					    tripinfo = (Productcontrol) ticketDao
						    .get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
					    // 拿出原订单的对应产品
					    oldtorderlist = (TOrderlist) yorderlistdao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(orderlist
						    .getOrderlistid()), oldorid, Long.parseLong(iscenicid)));
					    // TODO 比较子 票的趟次编号和新增票的趟次编号
					    for (TZorderlist zlist : tzlist) {// 循环有差异的子票列表
						if (zlist.getTripid() != null && zlist.getTripid() != 0 && tripinfo.getTripid().equals(zlist.getTripid())
							&& orderlist.getIcrowdkindid().equals(newTicket.getIcrowdkindid())
							&& tripinfo.getStdata().equals(newTicket.getTourdate())) {
						    orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
						    isadd = true;
						}
					    }
					} else {
					    orderlist.setNumb(orderlist.getNumb() + Long.parseLong(newTicket.getNumb()));
					    isadd = true;
					}
					if (orderlist.getZorderlist() == null || orderlist.getZorderlist().size() < 1) {
					    orderlist.setZorderlist(tzlist);
					} else {
					    for (TZorderlist zlist : orderlist.getZorderlist()) {// 循环有差异的子票列表
						zlist.setZnumb(zlist.getZnumb() + zlist.getZnumb() / orderlist.getNumb()
							* Integer.parseInt(newTicket.getNumb()));
						zlist.setZamnt(zlist.getZpric() * zlist.getZnumb());
					    }
					}
				    }
				    if (!isadd && j == addlist.size() - 1) {
					newproduct = new TOrderlist();
					TOrderlist comp = null;
					pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket
						.getIcrowdkindid()), stdt, ibusiness);
					kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
					ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
					if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
					    comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long
						    .parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt, Tools.getDate(stdt, ticket.getValidityday()
						    .intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
					} else {
					    comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long
						    .parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt, Tools.getDate(stdt, ticket.getValidityday()
						    .intValue() - 1), oldmorder.getStdt(), null);
					}
					if (comp != null) {
					    BeanUtils.copyProperties(newproduct, comp);
					    // List<TZorderlist> list = yorderlistdao
					    // .getTZorderlist(comp.getId().getOrderlistid(), oldorid, Long.parseLong(iscenicid));
					    // for (TZorderlist zlist : list) {
					    // zlist.setZnumb(zlist.getZnumb() + zlist.getZnumb() / comp.getNumb() * Integer.parseInt(newTicket.getNumb()));
					    // }
					    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
					    newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
					    // comp.setZorderlist(list);
					    addlist.add(newproduct);
					    break;
					} else {
					    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
					    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
					    pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket
						    .getIcrowdkindid()), stdt, ibusiness);
					    kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
					    newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
					    newproduct.setDtstartdate(stdt);
					    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
					    newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
					    newproduct.setPric(kindprice.getMactualsaleprice());
					    newproduct.setJsprice(kindprice.getJsprice());
					    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
					    newproduct.setYhnumb(0l);
					    newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
					    newproduct.setYhamnt(0.0);
					    sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
					    newproduct.setZorderlist(new ArrayList<TZorderlist>());
					    for (int y = 0; y < sonticket.size(); y++) {
						pricepost = (Edmticketcomposepricetab) sonticket.get(y);
						tzorderlist = new TZorderlist();
						tzorderlist.setItickettypeid(newproduct.getItickettypeid());
						tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
						tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
						tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
						zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
						if (zticket.getBycategorytype().equals("0003")) {
						    Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket
							    .getProductcontrolid()));
						    Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control
							    .getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket.getItickettypeid().toString());
						    tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
						    tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
						    tzorderlist.setTripid(control.getTripid());
						    tzorderlist.setIvenueid(prd.getIvenueid());
						    tzorderlist.setIvenueareaid(prd.getIvenueareaid());
						} else {
						    tzorderlist.setDtstartdate(stdt + " 00:00:00");
						    tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
						}
						tzorderlist.setZpric(pricepost.getMactualsaleprice());
						tzorderlist.setJsprice(pricepost.getJsprice());
						tzorderlist.setZnumb(pricepost.getNumb());
						tzorderlist.setZyhnumb(0l);
						tzorderlist.setZyhamnt(0.0);
						tzorderlist.setZamnt(0.0);
						newproduct.getZorderlist().add(tzorderlist);
					    }
					    addlist.add(newproduct);
					    break;
					}
				    }
				}
			    }
			} else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
			    for (int j = 0; j < addlist.size(); j++) {
				orderlist = (TOrderlist) addlist.get(j);
				tzlist = tZOrderListService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
				orderlist.setZorderlist(tzlist);
			    }
			} else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
			    addlist = new ArrayList();
			    TOrderlist comp = null;
			    for (int i = 0; i < neworderlist.size(); i++) {
				newTicket = (OrderPojo) neworderlist.get(i);
				newproduct = new TOrderlist();
				pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket
					.getIcrowdkindid()), stdt, ibusiness);
				kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
				ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
				if (newTicket.getProductcontrolid() != null && !newTicket.getProductcontrolid().equals("")) {
				    comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long
					    .parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt, Tools.getDate(stdt, ticket.getValidityday()
					    .intValue() - 1), newTicket.getTourdate(), Long.parseLong(newTicket.getProductcontrolid()));
				} else {
				    comp = torderlistdao.getTorderlistByProductInfo(Long.parseLong(newTicket.getItickettypeid()), oldorid, Long
					    .parseLong(iscenicid), kindprice.getIcrowdkindid(), stdt, Tools.getDate(stdt, ticket.getValidityday()
					    .intValue() - 1), oldmorder.getStdt(), null);
				}
				if (comp != null) {
				    BeanUtils.copyProperties(newproduct, comp);
				    // List<TZorderlist> list = yorderlistdao
				    // .getTZorderlist(comp.getId().getOrderlistid(), oldorid, Long.parseLong(iscenicid));
				    // for (TZorderlist zlist : list) {
				    // zlist.setZnumb(zlist.getZnumb() + zlist.getZnumb() / comp.getNumb() * Integer.parseInt(newTicket.getNumb()));
				    // }
				    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
				    newproduct.setAmnt(newproduct.getPric() * Long.parseLong(newTicket.getNumb()));
				    // comp.setZorderlist(list);
				    addlist.add(newproduct);
				} else {
				    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
				    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
				    newproduct.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
				    newproduct.setDtstartdate(stdt);
				    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
				    newproduct.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1));
				    newproduct.setPric(kindprice.getMactualsaleprice());
				    newproduct.setJsprice(kindprice.getJsprice());
				    newproduct.setNumb(Long.parseLong(newTicket.getNumb()));
				    newproduct.setYhnumb(0l);
				    newproduct.setAmnt(newproduct.getPric() * newproduct.getNumb());
				    newproduct.setYhamnt(0.0);
				    sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
				    newproduct.setZorderlist(new ArrayList<TZorderlist>());
				    for (int y = 0; y < sonticket.size(); y++) {
					pricepost = (Edmticketcomposepricetab) sonticket.get(y);
					tzorderlist = new TZorderlist();
					tzorderlist.setItickettypeid(newproduct.getItickettypeid());
					tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
					tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
					tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
					zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
					if (zticket.getBycategorytype().equals("0003")) {
					    Productcontrol control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(newTicket
						    .getProductcontrolid()));
					    Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control
						    .getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket.getItickettypeid().toString());
					    tzorderlist.setDtstartdate(newTicket.getTourdate() + " " + prd.getStarttime() + ":00");
					    tzorderlist.setDtenddate(newTicket.getTourdate() + " " + prd.getEndtime() + ":00");
					    tzorderlist.setTripid(control.getTripid());
					    tzorderlist.setIvenueid(prd.getIvenueid());
					    tzorderlist.setIvenueareaid(prd.getIvenueareaid());
					} else {
					    tzorderlist.setDtstartdate(stdt + " 00:00:00");
					    tzorderlist.setDtenddate(Tools.getDate(stdt, ticket.getValidityday().intValue() - 1) + " 23:59:59");
					}
					tzorderlist.setZpric(pricepost.getMactualsaleprice());
					tzorderlist.setJsprice(pricepost.getJsprice());
					tzorderlist.setZnumb(new Long(pricepost.getNumb().intValue() * Integer.parseInt(newTicket.getNumb())));
					tzorderlist.setZyhnumb(0l);
					tzorderlist.setZyhamnt(0.0);
					tzorderlist.setZamnt(tzorderlist.getZnumb() * tzorderlist.getZpric());
					newproduct.getZorderlist().add(tzorderlist);
				    }
				    addlist.add(newproduct);
				}
			    }
			} else {
			    returnmap.put("result", false);
			    returnmap.put("errtp", "5");//订单无修改
			    return returnmap;
			}
			// TODO 全部通过的话,再修改订单,否则返回错误信息
			// TODO 增量 退订 减少或者增加预付款 计算信用度
			// 计算出需要退订的金额
			double tdsx=0.0;
			if(!oldmorder.getDdzt().equals("23")){
			    tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
			}
			// 计算出需要增加的金额
			double totalmont = calculateAddMont(addlist);
			// 需要退订的钱
			double tdmont = calculateAddMont(backlist);

			Vipbalance balance = (Vipbalance) torderdao.get(Vipbalance.class, oldmorder.getZfusid());
			// TODO 比较两个map中的值，计算是否可以修改 不可修改返回false
			if (balance.getPoint() < Math.abs(tdsx) + totalmont + tdmont) {
			    returnmap.put("result", false);
			    returnmap.put("errtp", "2");// 余额不足
			    return returnmap;
			}
			List charraftlist = null;
			List newdaylist = null;
			List olddaylist = null;
			int istoprat = 0;
			if (oldmap != null) {
			    charraftlist = (List) oldmap.get("charraft");
			    olddaylist = (List) oldmap.get("chaday");
			}
			List newraft = null;
			if (newmap != null) {
			    newdaylist = (List) newmap.get("daylist");
			    newraft = (List) newmap.get("raftlist");
			}
			Sysparv5 maxcredit = (Sysparv5) morderdao.get(Sysparv5.class, new Sysparv5Id("YDJF", "02"));
			// 合并新增和修改原订单的竹筏
			charraftlist = mergeRaft(charraftlist, newraft);


			istoprat = checkStopRaft(charraftlist, iscenicid);

			// 新增订单
			addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat);

			// TODO 退订单
			addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat);
			TOrder oldtorder = (TOrder) yorderlistdao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
			oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont);
			oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
			YOrder oldyorder = (YOrder) yorderlistdao.get(YOrder.class, new YOrderId(oldtorder.getId().getOrid(), oldtorder.getId()
				.getIscenicid()));
			// 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
			if (oldyorder.getTpmont() != null) {
			    oldyorder.setTpmont(oldyorder.getTpmont() - totalmont + Math.abs(tdmont));
			} else {
			    oldyorder.setTpmont(0 - totalmont + Math.abs(tdmont));
			}
			if(!oldmorder.getDdzt().equals("23")){
			    if (oldyorder.getTpsx() != null) {
				oldyorder.setTpsx(oldyorder.getTpsx() + Math.abs(tdsx));
			    } else {
				oldyorder.setTpsx(Math.abs(tdsx));
			    }
			    if (oldmorder.getTpsx() == null) {
				    oldmorder.setTpsx(Math.abs(tdsx));
			    } else {
				    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
			    }
			}
			
			// if ((Math.abs(tdmont) - totalmont) > 0) {
			// oldmorder.setTpmont(Math.abs(Math.abs(tdmont) - totalmont-Math.abs(tdsx)));
			// }
			// if (tdsx < 0) {
			// oldmorder.setTpfs("02");
			// oldmorder.setTpsx(Math.abs(tdsx));
			// }
			if (oldmorder.getTpmont() != null) {
			    oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
			} else {
			    oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
			}
			
			// oldmorder.setMont(oldmorder.getMont()+totalmont+tdmont);
			// oldmorder.setZfmont(oldmorder.getZfmont() + totalmont+tdmont);
			oldmorder.setIsf(1l);
			oldyorder.setIsf(1l);
			oldtorder.setIsf(1l);
			yorderlistdao.update(oldyorder);
			yorderlistdao.update(oldtorder);
			yorderlistdao.update(oldmorder);
	//		calculateCredit(oldmorder.getUsid(), charraftlist, iscenicid);
			if(!oldmorder.getDdzt().equals("23")){
			    minusRaftCount(charraftlist, iscenicid);
			    minusDayCount(newdaylist, olddaylist, iscenicid);
			}
			returnmap.put("result", true);
			Orderlog log = new Orderlog();
			log.setLogid(getMaxPk("logid", "Orderlog") + 1);
			log.setOrid(oldorid);
			log.setStlg("0158");
			log.setBrief("修改订单");
			log.setNote("修改订单");
			log.setIemployeeid(null);
			log.setUsid(usid);
			log.setLogtype(Long.parseLong("0"));
			log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
			torderdao.save(log);
			if (oldtorder.getOrph() != null && !oldtorder.getOrph().equals("")) {
				sysparService.sendMessageNew(oldtorder.getOrph(), "0004", oldtorder.getId().getOrid());
			}
//			if (tdsx > 0) {
//			    List sysList = morderdao.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
//			    Sysparv5 sys = null;
//			    sys = (Sysparv5) sysList.get(0);// 取平台帐号
//			    Useryfk ptyfk = new Useryfk();
//			    ptyfk.setSeq(new Integer(morderdao.getMaxPk("Useryfk", "seq").toString()) + 1);
//			    ptyfk.setUsid(sys.getPmva());// 用户
//			    ptyfk.setBdate(Tools.getTodayString());
//			    ptyfk.setOrderid(orids[1]);
//			    ptyfk.setPoint(tdsx);
//			    ptyfk.setYfklb(1);// 预付款转出
//			    ptyfk.setYfksc("13");
//			    ptyfk.setNote("用户退订手续费");
//			    ptyfk.setCztp(0);
//			    balancecenterDao.useryfksave(ptyfk);
//			}
			return returnmap;
	 
	 }
	 
	 
	 
	    public List mergeRaft(List charaftlist, List newraft) {
	    	RaftComparepojo c1 = null;
	    	RaftComparepojo c2 = null;
	    	if (newraft != null && newraft.size() > 0 && charaftlist != null && charaftlist.size() > 0) {
	    	    for (int i = 0; i < newraft.size(); i++) {
	    		c1 = (RaftComparepojo) newraft.get(i);
	    		for (int j = 0; j < charaftlist.size(); j++) {// 以这个循环为对象比较外围对象
	    		    // 缺少的添加进去
	    		    c2 = (RaftComparepojo) charaftlist.get(j);
	    		    if (c2.getIticketid().intValue() == c1.getIticketid().intValue() && c2.getTourdate().equals(c1.getTourdate())
	    			    && c2.getTripid().intValue() == c1.getTripid().intValue()) {
	    			c2.setNumb(c2.getNumb() + c1.getNumb());
	    		    } else if (j == charaftlist.size() - 1) {
	    			charaftlist.add(c1);
	    		    }
	    		}
	    	    }
	    	} else {
	    	    if (charaftlist == null || charaftlist.size() < 1) {
	    		if (newraft != null) {
	    		    charaftlist.addAll(newraft);
	    		}
	    	    }
	    	}
	    	return charaftlist;
	        }
	    
	    
	    private int checkStopRaft(List charraftlist, String iscenicid) {
	    	Productcontrol control = null;
	    	RaftComparepojo c1 = null;
	    	Prdtripvenuemanage prd = null;
	    	int result = 0;
	    	if (charraftlist != null) {
	    	    for (int i = 0; i < charraftlist.size(); i++) {
	    		c1 = (RaftComparepojo) charraftlist.get(i);
	    		prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1
	    			.getIticketid().toString());
	    		control = ticketDao.getTripControl(prd.getTripid(), prd.getIvenueid(), prd.getIvenueareaid(), c1.getTourdate());
	    		if (control.getBystate() == 0) {
	    		    result = 1;
	    		}
	    	    }
	    	}
	    	return result;
	        }
	    
	    
	    // 减掉(或增加)竹筏量
	    private boolean minusRaftCount(List charraftlist, String iscenicid) {
		Productcontrol control = null;
		RaftComparepojo c1 = null;
		Prdtripvenuemanage prd = null;
		if (charraftlist != null) {
		    for (int i = 0; i < charraftlist.size(); i++) {
			c1 = (RaftComparepojo) charraftlist.get(i);
			prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1
				.getIticketid().toString());
			control = ticketDao.getTripControl(prd.getTripid(), prd.getIvenueid(), prd.getIvenueareaid(), c1.getTourdate());
			control.setSoldnumber(control.getSoldnumber() + c1.getNumb());
			ticketDao.update(control);
		    }
		}
		return true;
	    }

	    // 减掉(或增加)竹筏量
	    private boolean minusDayCount(List newdaylist, List olddaylist, String iscenicid) throws IllegalAccessException,
		    InvocationTargetException {
		RaftComparepojo checkpojo1 = null;
		OrderPojo checkpojo = null;
		Productcontrol control = null;
		if (newdaylist != null && newdaylist.size() > 0) {
		    for (int i = 0; i < newdaylist.size(); i++) {
			checkpojo = (OrderPojo) newdaylist.get(i);
			if (olddaylist == null || olddaylist.size() < 1) {
			    checkpojo1 = new RaftComparepojo();
			    control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
			    checkpojo1.setIvenueid(control.getIvenueid());
			    checkpojo1.setIvenueareaid(control.getIvenueareaid());
			    checkpojo1.setTripid(control.getTripid());
			    checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
			    checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
			    // checkpojo1.setErrtp(errtp);
			    checkpojo1.setTourdate(checkpojo.getTourdate());
			    olddaylist.add(checkpojo1);
			} else {
			    for (int j = 0; j < olddaylist.size(); j++) {
				checkpojo1 = (RaftComparepojo) olddaylist.get(j);
				if (checkpojo1.getIticketid().intValue() == Integer.parseInt(checkpojo.getItickettypeid())
					&& checkpojo1.getTourdate().equals(checkpojo.getTourdate())) {
				    checkpojo1.setNumb(checkpojo1.getNumb() + Integer.parseInt(checkpojo.getNumb()));
				    break;
				} else {
				    if (i == newdaylist.size() - 1) {
					checkpojo1 = new RaftComparepojo();
					control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo
						.getProductcontrolid()));
					checkpojo1.setTourdate(checkpojo.getTourdate());
					checkpojo1.setIvenueid(control.getIvenueid());
					checkpojo1.setIvenueareaid(control.getIvenueareaid());
					checkpojo1.setTripid(control.getTripid());
					checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
					checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
					olddaylist.add(checkpojo1);
					break;
				    }
				}
			    }
			}
		    }
		}
		if (olddaylist != null && olddaylist.size() > 0) {
		    control = null;
		    List list = null;
		    Edmtickettypetab checkticket = null;
		    Trip checktrip = null;
		    for (int i = 0; i < olddaylist.size(); i++) {
			checkpojo1 = (RaftComparepojo) olddaylist.get(i);
			list = ticketDao.getNumberControllData(checkpojo1.getIticketid().toString(), checkpojo1.getTourdate(), "02");
			if (list != null && list.size() > 0) {
			    control = new Productcontrol();
			    BeanUtils.populate(control, (Map) list.get(0));
			    control = (Productcontrol) ticketDao.get(Productcontrol.class, control.getProductcontrolid());
			    control.setSoldnumber(control.getSoldnumber() + checkpojo1.getNumb());
			    ticketDao.update(control);
			}
		    }
		}
		return true;
	    }
	    
	    
	    /**
	     * 
	     * Describe:
	     * @auth:lijingrui
	     * @param orid
	     * @param orderInfo
	     * @param newInfo
	     * @param usid
	     * @param stdt
	     * @param ibusinessid
	     * @param iscenicid
	     * @return
	     * @throws IllegalAccessException
	     * @throws InvocationTargetException
	     * return:Map
	     * Date:Apr 26, 2012
	     */
	    public Map validateOrderInfo(String orid, List orderInfo, List newInfo, String usid, String stdt, String ibusinessid, String iscenicid)
	    	throws IllegalAccessException, InvocationTargetException {
	    	Map map = new HashMap();
	    	Map newInfoMap = null;
	    	Double needminus = 0.0;
	    	List newraftlist = null;
	    	if (newInfo != null && newInfo.size() > 0) {
	    	    newInfo = mergenewInfo(newInfo);
	    	    newInfoMap = calculateNewOrderInfo(newInfo, stdt, ibusinessid);
	    	    needminus = (Double) newInfoMap.get("amont");// 新增产品的金额
	    	    newraftlist = (List) newInfoMap.get("raftlist");// 新增产品所需的竹筏趟次列表及数量
	    	}
	    	Map oldInfoMap = calculateOldOrder(orid, iscenicid, orderInfo);
	    	List chalist = (List) oldInfoMap.get("chaticket");// 根据产品算出的需要加减的产品列表
	    	if ((chalist == null || chalist.size() < 1) && (newInfo == null || newInfo.size() < 1)) {
	    	    map.put("result", false);
	    	    map.put("errtp", "5");// 日控制
	    	    return map;
	    	}
	    	List charraft = (List) oldInfoMap.get("charraft");// 根据竹筏信息算出来的需要加减的竹筏量
	    	List newdayslist = null;
	    	List olddayslist = null;
	    	if (newInfoMap != null) {
	    	    newdayslist = (List) newInfoMap.get("daylist");
	    	}
	    	if (oldInfoMap != null) {
	    	    olddayslist = (List) oldInfoMap.get("chaday");
	    	}
	    	List exceptiondays = validateDay(newdayslist, olddayslist);
	    	if (exceptiondays != null && exceptiondays.size() > 0) {
	    	    map.put("result", false);
	    	    map.put("errtp", "0");// 日控制
	    	    map.put("errlist", exceptiondays);
	    	    return map;
	    	} else {
	    	    // TODO 首先判断竹筏量是否满足,即原订单中退订的票种含有的竹筏量与修改订单之后新增的竹筏量相比较
	    	    List exceptionlist = validateRaft(newraftlist, charraft);
	    	    if (exceptionlist != null && exceptionlist.size() > 0) {
	    		map.put("result", false);
	    		map.put("errtp", "1");// 趟次控制
	    		map.put("errlist", exceptionlist);
	    		return map;
	    	    }
	    	}
	    	Double oldneedmount = (Double) oldInfoMap.get("amount");
	    	Vipbalance balance = (Vipbalance) torderdao.get(Vipbalance.class, usid);
	    	// TODO 比较两个map中的值，计算是否可以修改 不可修改返回false
	    	if (balance.getPoint() < oldneedmount + needminus) {
	    	    map.put("result", false);
	    	    map.put("errtp", "2");// 余额不足
	    	    return map;
	    	}
	    	map.put("result", true);
	    	map.put("needmoney", oldneedmount + needminus);
	    	return map;
	    }
	    	    
	    	    
	    /**
	     * 
	     * Describe:验证含有竹筏票的日控制
	     * @auth:lijingrui
	     * @param newdaylist
	     * @param olddaylist
	     * @return
	     * @throws IllegalAccessException
	     * @throws InvocationTargetException
	     * return:List
	     * Date:Apr 26, 2012
	     */	    
	     public List validateDay(List newdaylist, List olddaylist) throws IllegalAccessException, InvocationTargetException {
	    	 OrderPojo checkpojo = null;
	    		RaftComparepojo checkpojo1 = null;
	    		Productcontrol control = null;
	    		if (newdaylist != null && newdaylist.size() > 0) {
	    		    for (int i = 0; i < newdaylist.size(); i++) {
	    			checkpojo = (OrderPojo) newdaylist.get(i);
	    			if (olddaylist == null || olddaylist.size() < 1) {
	    			    checkpojo1 = new RaftComparepojo();
	    			    control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
	    			    checkpojo1.setIvenueid(control.getIvenueid());
	    			    checkpojo1.setIvenueareaid(control.getIvenueareaid());
	    			    checkpojo1.setTripid(control.getTripid());
	    			    checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
	    			    checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
	    			    // checkpojo1.setErrtp(errtp);
	    			    checkpojo1.setTourdate(checkpojo.getTourdate());
	    			    olddaylist.add(checkpojo1);
	    			} else {
	    			    for (int j = 0; j < olddaylist.size(); j++) {
	    				checkpojo1 = (RaftComparepojo) olddaylist.get(j);
	    				if (checkpojo1.getIticketid().intValue() == Integer.parseInt(checkpojo.getItickettypeid())
	    					&& checkpojo1.getTourdate().equals(checkpojo.getTourdate())) {
	    				    checkpojo1.setNumb(checkpojo1.getNumb() + Integer.parseInt(checkpojo.getNumb()));
	    				} else {
	    				    if (i == newdaylist.size() - 1) {
	    					checkpojo1 = new RaftComparepojo();
	    					control = (Productcontrol) ticketDao.get(Productcontrol.class, Long.parseLong(checkpojo
	    						.getProductcontrolid()));
	    					checkpojo1.setTourdate(checkpojo.getTourdate());
	    					checkpojo1.setIvenueid(control.getIvenueid());
	    					checkpojo1.setIvenueareaid(control.getIvenueareaid());
	    					checkpojo1.setTripid(control.getTripid());
	    					checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
	    					checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
	    					// checkpojo1.setErrtp(errtp);
	    					olddaylist.add(checkpojo1);
	    				    }
	    				}
	    			    }
	    			}
	    		    }
	    		}
	    		if (olddaylist != null && olddaylist.size() > 0) {
	    		    control = null;
	    		    List list = null;
	    		    Edmtickettypetab checkticket = null;
	    		    Trip checktrip = null;
	    		    for (int i = 0; i < olddaylist.size(); i++) {
	    			checkpojo1 = (RaftComparepojo) olddaylist.get(i);
	    			list = ticketDao.getNumberControllData(checkpojo1.getIticketid().toString(), checkpojo1.getTourdate(), "02");
	    			if (list != null && list.size() > 0) {
	    			    control = new Productcontrol();
	    			    BeanUtils.populate(control, (Map) list.get(0));
	    			    if (control.getSalablenumber() - control.getSoldnumber() > checkpojo1.getNumb()) {
	    				olddaylist.remove(checkpojo1);
	    				i = i - 1;
	    			    } else {
	    				checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo1.getIticketid());
	    				checktrip = (Trip) ticketDao.get(Trip.class, checkpojo1.getTripid());
	    				checkpojo1.setErrtp("2");// 已售完
	    				checkpojo1.setTicketname(checkticket.getSztickettypename());
	    				checkpojo1.setTripname(checktrip.getTripname());
	    			    }
	    			} else {
	    			    checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo1.getIticketid());
	    			    checktrip = (Trip) ticketDao.get(Trip.class, checkpojo1.getTripid());
	    			    checkpojo1.setErrtp("3");// 暂时不可销售
	    			    checkpojo1.setTicketname(checkticket.getSztickettypename());
	    			    checkpojo1.setTripname(checktrip.getTripname());
	    			}
	    		    }
	    		}

	    		return olddaylist;
	    }
	    
	    
	    /**
	     * 
	     * Describe:验证竹筏,返回竹筏不足的趟次
	     * 
	     * @auth:yangguang
	     * @param newraftlist
	     * @param charraft
	     * @return return:List Date:2012-2-9
	     */
	    private List validateRaft(List newraftlist, List charraft) {
		// TODO 两个参数list进行合并
		List validatelist = null;
		RaftComparepojo checkpojo = null;
		if (newraftlist != null && newraftlist.size() > 0 && charraft != null && charraft.size() > 0) {
		    List list1 = new ArrayList();
		    List list2 = new ArrayList();
		    RaftComparepojo c1 = null;
		    RaftComparepojo c2 = null;
		    if (newraftlist.size() >= charraft.size()) {
			list1.addAll(newraftlist);
			list2.addAll(charraft);
		    } else {
			list1.addAll(charraft);
			list2.addAll(newraftlist);
		    }
		    for (int i = 0; i < list1.size(); i++) {
			c1 = (RaftComparepojo) list1.get(i);
			for (int j = 0; j < list2.size(); j++) {// 以这个循环为对象比较外围对象
			    // 缺少的添加进去
			    c2 = (RaftComparepojo) list2.get(j);
			    if (c2.equals(c1)) {
				c2.setNumb(c2.getNumb() + c1.getNumb());
			    } else if (j == list2.size() - 1) {
				list2.add(c1);
			    }
			}
		    }
		    validatelist = list2;
		} else if (newraftlist == null || newraftlist.size() < 1) {
		    validatelist = charraft;
		} else if (charraft == null || charraft.size() < 1) {
		    validatelist = newraftlist;
		} else {
		    return null;
		}
		Productcontrol control = null;
		Edmtickettypetab checkticket = null;
		Trip checktrip = null;
		for (int i = 0; i < validatelist.size(); i++) {
		    checkpojo = (RaftComparepojo) validatelist.get(i);
		    control = ticketDao.getTripControl(checkpojo.getTripid(), checkpojo.getIvenueid(), checkpojo.getIvenueareaid(), checkpojo
			    .getTourdate());
		    if (control == null || control.getBystate() == -1) {
			checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo.getIticketid());
			checktrip = (Trip) ticketDao.get(Trip.class, checkpojo.getTripid());
			checkpojo.setErrtp("0");// 停排
			checkpojo.setTicketname(checkticket.getSztickettypename());
			checkpojo.setTripname(checktrip.getTripname());
		    } else {
			if (control.getSalablenumber() - control.getSoldnumber() >= checkpojo.getNumb()) {
			    validatelist.remove(checkpojo);
			    i = i - 1;
			} else {
			    checkticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, checkpojo.getIticketid());
			    checktrip = (Trip) ticketDao.get(Trip.class, checkpojo.getTripid());
			    checkpojo.setErrtp("1");// 数量不足
			    checkpojo.setTicketname(checkticket.getSztickettypename());
			    checkpojo.setTripname(checktrip.getTripname());
			}
		    }
		}
		return validatelist;
	    }
	    
	    /**
	     * *
	     * Describe:显示VIP人数信息
	     * @see com.ectrip.ticket.service.iservice.IVipTicketService#getViewcustomvip(java.lang.String)
	     * @param usid
	     * @return
	     * @throws Exception
	     * @author lijingrui
	     * Date:Apr 27, 2012
	     */
		 public Customvip getViewcustomvip(String usid) throws Exception{
			 String sql="select new map(cv.usid as usid,cv.cvnum as cvnum,cv.cvtype as cvtype) from Customvip cv where cv.usid='"+usid+"' and '"+Tools.getDays()+"'>=cv.stdate and '"+Tools.getDays()+"'<=cv.endate and cv.byisuse=1 ";
			 List list=this.find(sql);
			 if(list!=null&&list.size()>0){
				 Customvip cuts=new Customvip();
				 BeanUtils.populate(cuts, (Map)list.get(0));
				 return cuts;
			 }else{
				 return null;
			 }
			
		 }
		 
		 /**
		  * 
		  * Describe:显示套票中基础票中的竹筏票价格
		  * @auth:lijingrui
		  * @param itickettypeid
		  * @param ibusinessid
		  * @return
		  * @throws Exception
		  * return:Edmticketcomposepricetab
		  * Date:Apr 27, 2012
		  */
		 public Edmticketcomposepricetab getViewpricetab(Long itickettypeid,Long ibusinessid,String stdate)throws Exception{
			 String sql="select new map(edm.itickettypeid as itickettypeid,edm.mactualsaleprice as mactualsaleprice,edm.jsprice as jsprice,ek.sztickettypename as sztickettypename) from Edmticketcomposepricetab edm,Edmtickettypetab ek where edm.id.icrowdkindpriceid in (select pri.icrowdkindpriceid from Edmcrowdkindpricetab pri where pri.itickettypeid ="+itickettypeid+" and pri.startdata<='"+stdate+"'  and pri.enddata>='"+stdate+"' and pri.ibusinessid="+ibusinessid+") and ek.itickettypeid=edm.itickettypeid and ek.bycategorytype='0003' ";
			 List list=this.find(sql);
			 Edmticketcomposepricetab post=new Edmticketcomposepricetab();
			 BeanUtils.populate(post, (Map)list.get(0));
			 return post;
		 }
		 
		 /**
		     * 
		     * Describe:根据当前用户所属业务类型获取现可售的票得名称,并按照票得名称进行分组
		     * 
		     * @auth:yangguang
		     * @param ibusinessid
		     * @return return:PaginationSupport Date:2011-10-9
		     */
		    public List getTicketList(String ibusinessid) {
				return vipticketDao.getTicketList(ibusinessid);
		    }
		  
		    /**
		     * * Describe:获取票务信息，并封装价格列表
		     * 
		     * @see com.ectrip.ticket.service.iservice.ITicketService#getTickInfoList(java.lang.String, int, int, java.lang.String)
		     * @param ibusinessid
		     * @param pageSize
		     * @param startIndex
		     * @param url
		     * @return
		     * @author yangguang Date:2011-10-9
		     * @throws InvocationTargetException
		     * @throws IllegalAccessException
		     */
		    public List getTickInfoList(String ibusinessid) throws IllegalAccessException, InvocationTargetException {
		    	List list = vipticketDao.getTicketList(ibusinessid);
				TicketPojo ticket = null;
				if (list != null && list.size() > 0) {
				    for (int i = 0; i < list.size(); i++) {
					ticket = new TicketPojo();
					BeanUtils.populate(ticket, (Map) list.get(i));
					ticket.setPricelist(ticketDao.getPriceList(ticket.getTicketid().toString(), ibusinessid));
					if (ticket.getBycategorytype() != null && !ticket.getBycategorytype().equals("")
						&& ticket.getBycategorytype().equals("0010")) {
					    ticket.setSonTicket(ticketDao.getSonTicketList(ticket.getTicketid().toString()));
					}
					list.set(i, ticket);
				    }
				}
				return list;
		    }
		    
	 /**
	  * *
	  * Describe:判断VIP用户是否到期
	  * @see com.ectrip.ticket.service.iservice.IVipTicketService#getTickdate(java.lang.String)
	  * @param usid
	  * @return
	  * @author lijingrui
	  * Date:Apr 28, 2012
	  */
	 public boolean getTickdate(String usid){
		 Customvip ctvm=(Customvip) this.get(Customvip.class, usid);
		 String sql=" from Customvip ct where ct.stdate<='"+Tools.getDays()+"' and ct.endate>='"+Tools.getDays()+"' and ct.usid='"+usid+"' ";
		 List list=this.find(sql);
		 if(list.size()>0&&list!=null){
			 return true;
		 }else{
			 return false;
		 }
		 
	 }  
	 
	 /**
	  * *
	  * Describe:获取网上订单服务商出票明细
	  * @see com.ectrip.ticket.service.iservice.IVipTicketService#getTOrderListList(java.lang.String, java.lang.String)
	  * @param orid
	  * @param iscenicid
	  * @return
	  * @throws IllegalAccessException
	  * @throws InvocationTargetException
	  * @author lijingrui
	  * Date:Apr 28, 2012
	  */
	 public List getTOrderListList(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException{
		 return vipticketDao.getTOrderListList(orid, iscenicid);
	 }
	 
	 /**
	  * *
	  * Describe:判断VIP用户在某天只能预订一次
	  * @see com.ectrip.ticket.service.iservice.IVipTicketService#QuickvipDate(java.lang.String, java.lang.String)
	  * @param usid
	  * @param data
	  * @return
	  * @author lijingrui
	  * Date:Apr 29, 2012
	  */
	 public boolean QuickvipDate(String usid,String data){
		 String sql=" from TOrder td where td.usid='"+usid+"' and td.dtstartdate<='"+data+"' and td.dtenddate>='"+data+"' ";
		 List list=this.find(sql);
		 if(list!=null&&list.size()>0){
			 return true;
		 }else{
			 return false;
		 }
		 
	 }
}

