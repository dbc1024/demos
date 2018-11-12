package com.ectrip.ticket.cyt.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.RaftComparepojo;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.Seatyordertab;
import com.ectrip.ec.model.order.SeatyordertabId;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.cyt.service.iservice.ICytOrderDataService;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Programprdmanager;
import com.ectrip.ticket.model.venuemarketing.Seatstatustab;
import com.ectrip.ticket.model.venuemarketing.SeatstatustabId;
import com.ectrip.ticket.model.venuemarketing.Trip;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;
import com.ectrip.ticket.model.venuemarketing.Tripprdsaletab;
import com.ectrip.ticket.permitenter.service.iservice.INumjifenSetService;
import com.ectrip.ticket.provider.service.ITicketTypeService;
import com.ectrip.ticket.stocks.dao.idao.IStocksWareDAO;

@Service
public class cytOrderDataService extends GenericService implements ICytOrderDataService {

    /*private IMOrderDAO morderdao;
    private ITOrderDAO torderdao;
    private ITOrderListDAO torderlistdao;
    private IYOrderDAO yorderdao;
    private IYOrderListDAO yorderlistdao;
    private IMbMessageDAO mbmessageDao;
    private IBalanceCenterDAO balancecenterDao;
    private IPayOrderDAO payorderDao;
    private ITicketDAO ticketDao;
    private IStatisicsDAO statisicsDao;*/

	@Autowired
    private IStocksWareDAO stockswareDao;
    //	private ITicketStockInfoDao ticketStockInfoDao;
	@Autowired
    private EcService ecService;
	@Autowired
    private SysService sysService;
	@Autowired
	private ITicketTypeService ticketTypeService;
	@Autowired
	private INumjifenSetService numjifenSetService;

    public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder, List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist, List<TRealname> rlist)
            throws IllegalAccessException, InvocationTargetException {
        if (rlist == null || rlist.size() == 0) {
            return saveOrder(orid, morder, yorder, yorderlist, torder, torderlist);
        } else {
            return saveOrder(orid, morder, yorder, yorderlist, torder, torderlist) && saveRealname(rlist, orid);
        }
    }
    public boolean saveRealname(List<TRealname> list, String orid) {
        for (int i = 0; i < list.size(); i++) {
            try {
                TRealname tRealname = new TRealname();
                tRealname = (TRealname) list.get(i);
                //Long seq = this.getSequenceId("realname_sequence");
                Long seq = stockswareDao.getSequenceId("realname_sequence");
                tRealname.setOrid(orid);
                tRealname.setSeq(seq);
                stockswareDao.save(tRealname);
                //this.save(tRealname);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * (非 Javadoc)
     * <p>
     * Title: saveOrder
     * </p>
     * <p>
     * Description: 添加订单 同步以及事务控制 判断钱数是否正确之后在做操作
     * </p>
     *
     * @param morder
     * @param yorder
     * @param yorderlist
     * @param torder
     * @param torderlist
     * @return
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     * @see com.ectrip.order.service.iservice.IOrderService#saveOrder(com.ectrip.model.order.MOrder,
     *      java.util.List, java.util.List, java.util.List, java.util.List)
     */
    public boolean saveOrder(String orid, MOrder morder, List<YOrder> yorder, List<YOrderlist> yorderlist, List<TOrder> torder, List<TOrderlist> torderlist) throws IllegalAccessException,
            InvocationTargetException {
        YZorderlist y_zordderlist = null;
        TOrder t_order = null;
        TOrderlist t_orderlist = null;
        /** 判断 金额 是否 一致 start **/
        // 计算销售订单总钱数 与 主订单的金额比较
        Double sum = 0.00;
        for (int i = 0; i < torder.size(); i++) {
            sum += torder.get(i).getZfmont();
        }
        // 判断总订单MOrder与分订单Torder的金额是否一致
        boolean ismoney = false;// 预定订单 与 销售 订单 金额是否一致 boolean 表示变量
        if (morder.getZfmont().doubleValue() == sum) {
            ismoney = true;
        }
        // 判断 销售订单 t_order 与 t_orderlist 销售订单明细 金额是否一致
        boolean isSum = false;// 销售的 订单与订单明细 金额是否一致 boolean 表示变量
        double mon = 0.00;
        double yhmont = 0.0;
        if (ismoney) {
            for (int i = 0; i < torder.size(); i++) {
                t_order = torder.get(i);
                mon = 0.0;
                for (int j = 0; j < torderlist.size(); j++) {
                    t_orderlist = torderlist.get(j);
                    if (t_orderlist.getId().getIscenicid().intValue() == t_order.getId().getIscenicid().intValue()) {
                        mon += t_orderlist.getAmnt();
                        yhmont += t_orderlist.getYhamnt();
                    }
                }
                System.out.println("t_ordermont:" + t_order.getZfmont());
                System.out.println("t_ordermont:" + mon);
                if (t_order.getZfmont().doubleValue() == mon - yhmont) {
                    isSum = true;
                } else {
                    isSum = false;
                    break;
                }
            }
        }
        /** end **/
        if (isSum) {
            String[] dateArr = new String[torder.size()];
            for (int i = 0; i < torder.size(); i++) {
                TOrder t = torder.get(i);
                dateArr[i] = t.getDtstartdate();
            }
            dateArr = getStartAndEndDate(dateArr);
            System.out.println("==============>>>>min date:" + dateArr[dateArr.length - 1]);
            // 插入预定订单
            System.out.println("orid = " + orid);
            morder.setOrid(orid);
            morder.setStdt(dateArr[dateArr.length - 1]);
//            morderdao.saveMOrder(morder);
            
            // 插入销售订单
            for (int i = 0; i < torder.size(); i++) {
                torder.get(i).getId().setOrid(orid);
                /*torderdao.saveTOrder(torder.get(i));*/
                stockswareDao.save(torder.get(i));
            }
            // 插入销售订单
            for (int i = 0; i < yorder.size(); i++) {
                yorder.get(i).getId().setOrid(orid);
                stockswareDao.save(yorder.get(i));
            }

            for (int i = 0; i < yorderlist.size(); i++) {
                yorderlist.get(i).getId().setOrid(orid);
                stockswareDao.save(yorderlist.get(i));
            }
            // 插入销售订单明细
            for (int i = 0; i < torderlist.size(); i++) {
                t_orderlist = torderlist.get(i);
                t_orderlist.getId().setOrid(orid);
                stockswareDao.save(t_orderlist);
                if (t_orderlist != null && t_orderlist.getZorderlist() != null && t_orderlist.getZorderlist().size() > 0) {
                    for (TZorderlist zorderlist : t_orderlist.getZorderlist()) {
                        zorderlist.getId().setOrid(orid);
                        stockswareDao.save(zorderlist);
                        y_zordderlist = new YZorderlist();
                        y_zordderlist.setId(new YZorderlistId(zorderlist.getId().getZorderlistid(), zorderlist.getId().getOrderlistid(), zorderlist.getId().getOrid(), zorderlist.getId()
                                .getIscenicid()));
                        y_zordderlist.setIcrowdkindpriceid(zorderlist.getIcrowdkindpriceid());
                        y_zordderlist.setIcrowdkindid(zorderlist.getIcrowdkindid());
                        y_zordderlist.setItickettypeid(zorderlist.getItickettypeid());
                        y_zordderlist.setIztickettypeid(zorderlist.getIztickettypeid());
                        y_zordderlist.setDtstartdate(zorderlist.getDtstartdate());
                        y_zordderlist.setDtenddate(zorderlist.getDtenddate());
                        y_zordderlist.setTripid(zorderlist.getTripid());
                        y_zordderlist.setIvenueid(zorderlist.getIvenueid());
                        y_zordderlist.setIvenueareaid(zorderlist.getIvenueareaid());
                        y_zordderlist.setIvenueseatsid(zorderlist.getIvenueseatsid());
                        y_zordderlist.setZpric(zorderlist.getZpric());
                        y_zordderlist.setZnumb(zorderlist.getZnumb());
                        y_zordderlist.setZyhnumb(zorderlist.getZyhnumb());
                        y_zordderlist.setZyhamnt(zorderlist.getZyhamnt());
                        y_zordderlist.setZamnt(zorderlist.getZamnt());
                        y_zordderlist.setJsprice(zorderlist.getJsprice());
                        y_zordderlist.setIsa(0l);
                        y_zordderlist.setIsb(0l);
                        y_zordderlist.setIsc(0l);
                        y_zordderlist.setIsd(0l);
                        y_zordderlist.setIse(0l);
                        y_zordderlist.setIsf(0l);
                        y_zordderlist.setIsg(0l);
                        y_zordderlist.setIsh(0l);
                        y_zordderlist.setIsi(0l);
                        y_zordderlist.setIsj(0l);
                        stockswareDao.save(y_zordderlist);
                    }
                }
            }
            // 0156 新增订单 0157 修改订单 0158 删除订单
//			Orderlog log = new Orderlog();
            //log.setLogid(getMaxPk("logid", "Orderlog") + 1);
//			log.setLogid(torderdao.getMaxPk("logid", "Orderlog") + 1);//源码是上面注释掉的，这个是测试改的
//			log.setOrid(orid);
//			log.setStlg("0157");
//			log.setBrief("预订成功");
//			log.setNote("预订成功");
//			log.setIemployeeid(null);
//			log.setUsid(morder.getUsid());
//			log.setLogtype(Long.parseLong("0"));
//			log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
//			torderdao.save(log);
			/*if (torder.get(0).getOrph() != null && !torder.get(0).getOrph().equals("")) {
				mbmessageDao.sendMessageNew(torder.get(0).getOrph(), "0001", new String[] { morder.getOrid() });// 订单保存发送短信
			}*/
            return true;
        } else {
            return false;
        }
    }

    private String[] getStartAndEndDate(String[] dateArray) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < dateArray.length; i++) {
                for (int j = 0; j < dateArray.length - 1; j++) {
                    Date d1 = sdf.parse(dateArray[j]);
                    Date d2 = sdf.parse(dateArray[j + 1]);
                    if (d1.before(d2)) {
                        String change = dateArray[j];
                        dateArray[j] = dateArray[j + 1];
                        dateArray[j + 1] = change;
                    }
                }
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateArray;
    }



    /**
     *
     * @Title: getTOrderList
     * @Description: 网上订单服务商出票信息
     * @param @param orid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     */
    public List getTOrderList(String orid,String iscenicid) {
    	
        return ecService.getTOrderlists(orid,Long.parseLong(iscenicid));
    }

    /**
     *
     * Describe:根据订单号，服务商编号获取订单产品列表
     *
     * @auth:yangguang
     * @param orid
     * @param iscenicid
     * @return return:List<TOrderlist> Date:2012-3-31
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessExceptio
     *             throws IllegalAccessException, InvocationTargetExceptionn
     */
    public List<TOrderlist> getTOrderlists(String orid, Long iscenicid) throws IllegalAccessException, InvocationTargetException {
        List list = ecService.getTOrderlists(orid, iscenicid);
        TOrderlist torderlist = null;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                torderlist = new TOrderlist();
                BeanUtils.populate(torderlist, (Map) list.get(i));
                torderlist.setNumb(0l);
                list.set(i, torderlist);
            }
        }
        return list;
    }


    public List<TOrderlist> getTOrderList(String orid,Long iscenicid){
        return ecService.getTOrderlists(orid, iscenicid);
    }

    public Map editOrderCenter(List<TOrderlist> orderlistInfo, List<OrderPojo> neworderlist, String[] orids, String oldorid, String iscenicid, String stdt, String ibusiness, String usid,double tpsxmoney,Long icompanyinfoid)
            throws Exception {
        Map returnmap = new HashMap();
        try {
            Custom custom = (Custom) stockswareDao.get(Custom.class, usid);
            MOrder oldmorder = (MOrder) stockswareDao.get(MOrder.class, oldorid);
            Map newmap = null;
            if (neworderlist != null) {
                neworderlist = mergenewInfo(neworderlist);
                newmap = calculateNewOrderInfo(neworderlist, stdt, usid, Long.parseLong(iscenicid));
            }
            Map oldmap = calculateOldOrder(oldorid, iscenicid, orderlistInfo);
            //计算优惠
            List oldtdlist = new ArrayList();// 原订单要退订的产品列表
            List oldaddlist = new ArrayList();// 原订单要新增的产品列表
            List ordereditsonlist = (List) oldmap.get("allzlist");
            List addsonProductlist = null;
            if (newmap != null) {
                addsonProductlist = (List) newmap.get("addzproduct");
            }
            // 把原订单修改之后筛选出来的差异list分成新增的退订的两个集合
            if (ordereditsonlist != null) {
                for (int i = 0; i < ordereditsonlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) ordereditsonlist.get(i);
                    if (compare.getNumb() > 0) {
                        oldaddlist.add(compare);
                    }
                    if (compare.getNumb() < 0) {
                        oldtdlist.add(compare);
                    }
                }
            }
            // 合并原订单修改之后要新增的产品的子产品和新增的产品中的子产品合并
            if (addsonProductlist != null && addsonProductlist.size() > 0 && oldaddlist != null && oldaddlist.size() > 0) {
                for (int i = 0; i < addsonProductlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) addsonProductlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        RaftComparepojo compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())) {
                                compare1.setNumb(compare1.getNumb() + compare.getNumb());
                            } else {
                                if (j == oldaddlist.size() - 1) {
                                    oldaddlist.add(compare);
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue()) {
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
            } else if (addsonProductlist != null) {
                oldaddlist.addAll(addsonProductlist);
            }
            // 筛选出需要收取退订手续费的产品
            // oldtdlist 为要收取手续费的退订产品.
            if (oldtdlist != null && oldaddlist != null) {
                for (int i = 0; i < oldtdlist.size(); i++) {
                    RaftComparepojo compare = (RaftComparepojo) oldtdlist.get(i);
                    for (int j = 0; j < oldaddlist.size(); j++) {
                        RaftComparepojo compare1 = (RaftComparepojo) oldaddlist.get(j);
                        if (compare.getTripid() != null && !compare.getTripid().equals("")) {
                            if (compare.getIticketid().equals(compare1.getIticketid()) && compare.getTourdate().equals(compare1.getTourdate()) && compare.getTripid().equals(compare1.getTripid())
                                    && compare.getPrice() == compare1.getPrice()) {
                                if (Math.abs(compare.getNumb()) <= compare1.getNumb()) {
                                    oldtdlist.remove(compare);
                                    i = i - 1;
                                    break;
                                } else if (Math.abs(compare.getNumb()) > compare1.getNumb()) {
                                    compare.setNumb(compare.getNumb() + compare1.getNumb());
                                    break;
                                }
                            }
                        } else {
                            if (compare.getIticketid().intValue() == compare1.getIticketid().intValue() && compare.getTourdate().equals(compare1.getTourdate())
                                    && compare.getPrice() == compare1.getPrice()) {
                                compare.setNumb(compare1.getNumb() + compare.getNumb());
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
            for (int i = 0; i < chalist.size(); i++) {
                TOrderlist orderlist = (TOrderlist) chalist.get(i);
                if (orderlist.getNumb() > 0) {
                    addlist.add(orderlist);
                } else {
                    backlist.add(orderlist);
                }
            }
            // 如果即修改了原订单,又新增了产品
            if (neworderlist != null && neworderlist.size() > 0 && addlist != null && addlist.size() > 0) {
                // 新增的票种和原来的订单进行对比
                for (int i = 0; i < neworderlist.size(); i++) {
                    OrderPojo newTicket = (OrderPojo) neworderlist.get(i);
                    TOrderlist newproduct = new TOrderlist();
//                    List pricelist = ticketDao.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, custom.getNote2());
                    List pricelist = ecService.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, custom.getNote2());
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setItickettypeid(Long.parseLong(newTicket.getItickettypeid()));
                    newproduct.setIcrowdkindid(Long.parseLong(newTicket.getIcrowdkindid()));
                    pricelist = ecService.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, custom.getNote2());
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
//                    List sonticket = ticketDao.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    List sonticket = ecService.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    newproduct.setZorderlist(new ArrayList<TZorderlist>());
                    for (int y = 0; y < sonticket.size(); y++) {
                        Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                        TZorderlist tzorderlist = new TZorderlist();
                        tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                        tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                        tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                        Edmtickettypetab zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                        if (zticket.getBycategorytype().equals("0003")) {
                            newproduct.setHasraft(1);// 标明含有竹筏类票
                            Productcontrol control = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                            newproduct.setWharfdate(control.getStdata());
                            newproduct.setPdctrolid(control.getProductcontrolid());
                           /* Prdtripvenuemanage prd = ticketDao.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString());*/
                            Prdtripvenuemanage prd = JSON.parseObject(ecService.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString()),Prdtripvenuemanage.class);
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
                        tzorderlist.setZnumb(pricepost.getNumb() * newproduct.getNumb());
                        tzorderlist.setZyhnumb(0l);
                        tzorderlist.setZyhamnt(0.0);
                        tzorderlist.setZamnt(tzorderlist.getZnumb() * pricepost.getMactualsaleprice());
                        newproduct.getZorderlist().add(tzorderlist);
                    }
                    addlist.add(newproduct);
                }
                for (int j = 0; j < addlist.size(); j++) {// 原订单新增的票
                    TOrderlist orderlist = (TOrderlist) addlist.get(j);
                    List<TZorderlist> tzlist = null;
                    if (orderlist.getOrderlistid() != null && !orderlist.getOrderlistid().equals("")) {
                        tzlist = ecService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    } else {
                        tzlist = orderlist.getZorderlist();
                    }
                    orderlist.setZorderlist(new ArrayList<TZorderlist>());
                    for (TZorderlist zlist : tzlist) {// 修改子票数量金额
                        TZorderlist newz = new TZorderlist();
                        BeanUtils.copyProperties(newz, zlist);
                        newz.setId(null);
                        List<Edmticketcomposepricetab> sprices = ticketTypeService.getSonPrice(orderlist.getIcrowdkindpriceid(), zlist.getIztickettypeid());
                        newz.setZnumb(sprices.get(0).getNumb() * orderlist.getNumb());
                        newz.setZamnt(zlist.getZpric() * zlist.getZnumb());
                        orderlist.getZorderlist().add(newz);
                    }
                }
            } else if (neworderlist == null || neworderlist.size() < 1 && addlist != null && addlist.size() > 0) {// 如果只修改了原订单
                for (int j = 0; j < addlist.size(); j++) {
                    TOrderlist orderlist = (TOrderlist) addlist.get(j);
                    List<TZorderlist> tzlist = ecService.getTZorderlist(Long.parseLong(orderlist.getOrderlistid()), oldorid, Long.parseLong(iscenicid));
                    if (orderlist.getZorderlist() == null) {
                        orderlist.setZorderlist(new ArrayList<TZorderlist>());
                    }
                    for (TZorderlist zlist : tzlist) {// 修改子票数量金额
                        TZorderlist newz = new TZorderlist();
                        BeanUtils.copyProperties(newz, zlist);
                        newz.setId(null);
                        Edmtickettypetab ticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, newz.getIztickettypeid());
                        if (ticket.getBycategorytype().equals("0003")) {
                            orderlist.setHasraft(1);
//                            Productcontrol cntrol = ticketDao.getNumberControl(zlist.getTripid(), zlist.getIvenueid(), zlist.getIvenueareaid(), zlist.getDtstartdate().substring(0, 10));
                            Productcontrol cntrol = JSON.parseObject(ecService.getNumberControl(zlist.getTripid(), zlist.getIvenueid(), zlist.getIvenueareaid(), zlist.getDtstartdate().substring(0, 10)),Productcontrol.class);
                            orderlist.setPdctrolid(cntrol.getProductcontrolid());
                            orderlist.setWharfdate(cntrol.getStdata());
                        }
                        List<Edmticketcomposepricetab> sprices = ticketTypeService.getSonPrice(orderlist.getIcrowdkindpriceid(), zlist.getIztickettypeid());
                        newz.setZnumb(sprices.get(0).getNumb() * orderlist.getNumb());
                        newz.setZamnt(zlist.getZpric() * zlist.getZnumb());
                        orderlist.getZorderlist().add(newz);
                    }
                }
            } else if (addlist == null || addlist.size() < 1 && neworderlist != null && neworderlist.size() > 0) {// 只新增了产品
                addlist = new ArrayList();
                for (int i = 0; i < neworderlist.size(); i++) {
                    OrderPojo newTicket = (OrderPojo) neworderlist.get(i);
                    TOrderlist newproduct = new TOrderlist();
                    List pricelist = ecService.getTicketPricelist(Long.parseLong(newTicket.getItickettypeid()), Long.parseLong(newTicket.getIcrowdkindid()), stdt, ibusiness, custom.getNote2());
                    Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
                    Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, Long.parseLong(newTicket.getItickettypeid()));
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
                    List sonticket = ecService.getSonticketlist(kindprice.getIcrowdkindpriceid());
                    newproduct.setZorderlist(new ArrayList<TZorderlist>());
                    for (int y = 0; y < sonticket.size(); y++) {
                        Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(y);
                        TZorderlist tzorderlist = new TZorderlist();
                        tzorderlist.setItickettypeid(newproduct.getItickettypeid());
                        tzorderlist.setIcrowdkindpriceid(kindprice.getIcrowdkindpriceid());
                        tzorderlist.setIcrowdkindid(kindprice.getIcrowdkindid());
                        tzorderlist.setIztickettypeid(pricepost.getItickettypeid());
                        Edmtickettypetab zticket = (Edmtickettypetab) get(Edmtickettypetab.class, pricepost.getItickettypeid());
                        if (zticket.getBycategorytype().equals("0003")) {
                            Productcontrol control = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(newTicket.getProductcontrolid()));
                            Prdtripvenuemanage prd =JSON.parseObject(ecService.getTripInfo(control.getTripid(), control.getIvenueid(), control.getIvenueareaid(), newTicket.getTourdate(), iscenicid, zticket
                                    .getItickettypeid().toString()),Prdtripvenuemanage.class);
                            newproduct.setHasraft(1);// 标明含有竹筏类票
                            newproduct.setPdctrolid(control.getProductcontrolid());
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
            } else {
                returnmap.put("result", false);
                returnmap.put("errtp", "5");// 订单无修改
                return returnmap;
            }
            // 计算出需要退订的金额
            double tdsx = 0.0;
            if (!oldmorder.getDdzt().equals("23")) {
                //tdsx = calculatetdmont(oldtdlist, oldmorder.getUsid(), iscenicid);
                tdsx = tpsxmoney;//换成我们自己的规则算出的手续费
            }
            // 计算出需要增加的金额
            double totalmont = calculateAddMont(addlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
            // 需要退订的钱
            double tdmont = calculateAddMont(backlist, Long.parseLong(iscenicid), oldorid, Long.parseLong(ibusiness));
            double totalyhmont = 0.0;
            for (int x = 0; x < addlist.size(); x++) {
                TOrderlist orderlist = (TOrderlist) addlist.get(x);
                totalyhmont += orderlist.getPric() * orderlist.getYhnumb();
            }
            for (int x = 0; x < backlist.size(); x++) {
                TOrderlist orderlist = (TOrderlist) backlist.get(x);
                totalyhmont -= orderlist.getPric() * orderlist.getYhnumb();
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
            // 合并新增和修改原订单的竹筏
            charraftlist = mergeRaft(charraftlist, newraft);
            Sysparv5 maxcredit = (Sysparv5) stockswareDao.get(Sysparv5.class, new Sysparv5Id("YDJF", "07"));

            istoprat = checkStopRaft(charraftlist, iscenicid);
            // 新增订单 返回要消费的积分
            addNewOrder(addlist, oldorid, orids[0], iscenicid, oldmorder.getUsid(), oldmorder, totalmont, "03", istoprat, tdsx, null,icompanyinfoid);
            // 退订单返回要退订的积分
            addNewOrder(backlist, oldorid, orids[1], iscenicid, oldmorder.getUsid(), oldmorder, tdmont, "02", istoprat, tdsx, oldtdlist,icompanyinfoid);
            // 这里以后oldtdlist中的值将不能使用,因为在封装退订单的时候里面的值已经改变
            TOrder oldtorder = (TOrder) stockswareDao.get(TOrder.class, new TOrderId(oldorid, Long.parseLong(iscenicid)));
            oldtorder.setMont(oldtorder.getMont() + totalmont + tdmont + totalyhmont);
            oldtorder.setZfmont(oldtorder.getZfmont() + totalmont + tdmont);
            oldtorder.setYhamnt(oldtorder.getYhamnt() + totalyhmont);
            oldmorder.setYhamnt(oldmorder.getYhamnt() + totalyhmont);
            // 因为是增量退订一起的,所以直接totlamont 和 tdmont是直接综合算出来的无需分部修改
            if (oldmorder.getTpmont() != null) {
                oldmorder.setTpmont(oldmorder.getTpmont() - totalmont + Math.abs(tdmont));
            } else {
                oldmorder.setTpmont(0 - totalmont + Math.abs(tdmont));
            }
            if (!oldmorder.getDdzt().equals("23")) {
                if (oldmorder.getTpsx() == null) {
                    oldmorder.setTpsx(Math.abs(tdsx));
                } else {
                    oldmorder.setTpsx(oldmorder.getTpsx() + Math.abs(tdsx));
                }
            }
            oldmorder.setIsf(1l);
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                if (oldtorder.getIsi() == null) {
                    oldtorder.setIsi(0l);
                }
                if (oldtorder.getIsh() == null) {
                    oldtorder.setIsh(0l);
                }
                if (oldmorder.getIsi() == null) {
                    oldmorder.setIsi(0l);
                }
                if (oldmorder.getIsh() == null) {
                    oldmorder.setIsh(0l);
                }
            }
            stockswareDao.update(oldtorder);
            stockswareDao.update(oldmorder);
            calculateCredit(oldmorder.getUsid(), charraftlist, iscenicid);
            if (!oldmorder.getDdzt().equals("23")) {
                minusRaftCount(charraftlist, iscenicid);
                minusDayCount(newdaylist, olddaylist, iscenicid);
            }
            returnmap.put("result", true);

            System.out.println("=========================修改订单状态=============================");
            if("27".equals(orids[0])){
                this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "27");
                this.updateMOrderStatusByZeroProduct(oldorid, "27");
            } else {
                this.updateOrderZtByZeroProduct(oldorid, Long.parseLong(iscenicid), "11");
                this.updateMOrderStatusByZeroProduct(oldorid, "11");
            }

            //判断库存信息
            List<TOrderlist> newTorderlists = ecService.getTOrderlists(oldorid,Long.parseLong(iscenicid));
            List<StockOrderInfo> stockOrderInfos = new ArrayList<StockOrderInfo>();
            String stockUsid = oldmorder.getUsid();
            IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
            if(newTorderlists != null && !newTorderlists.isEmpty()){//部分修改
                for (TOrderlist ntl : newTorderlists){
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(ntl.getId().getOrid());
                    stockOrderInfo.setProviderId(ntl.getId().getIscenicid());
                    stockOrderInfo.setProductId(ntl.getItickettypeid());
                    stockOrderInfo.setPriceId(ntl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(stockUsid);
                    stockOrderInfo.setNumb(ntl.getNumb());
                    stockOrderInfo.setStockDate(ntl.getDtstartdate());
                    stockOrderInfos.add(stockOrderInfo);
                }
                stockService.saveStockDetails(stockOrderInfos,false);
            }else{//全退订
                stockService.deleteStockDetails(oldorid,Long.parseLong(iscenicid),null,null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("orderService editOrderCenter 修改订单失败");
        }
        return returnmap;
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getYOrderListChildList
     * </p>
     * <p>
     * Description:获取 orid订单的子订单的增量、退订 网上预订服务商订单 详细信息
     * </p>
     *
     * @param orid
     * @param iscenicid
     * @return
     * @see com.ectrip.order.service.iservice.IOrderService#getYOrderListChildList(String,
     *      String)
     */
    public List getYOrderListChildList(String orid, String iscenicid) {
        return ecService.getYOrderListChildList(orid, iscenicid);
    }

    public List mergenewInfo(List newInfo) throws IllegalAccessException, InvocationTargetException {
        List mergelist = new ArrayList();
        for (int i = 0; i < newInfo.size(); i++) {
            OrderPojo pojo = (OrderPojo) newInfo.get(i);
            if (mergelist.size() < 1) {// 首次进入 加入第一条
                OrderPojo pojo1 = new OrderPojo();
                BeanUtils.copyProperties(pojo1, pojo);
                mergelist.add(pojo1);
            } else {
                for (int j = 0; j < mergelist.size(); j++) {// 比较
                    OrderPojo pojo1 = (OrderPojo) mergelist.get(j);
                    if (pojo.getProductcontrolid() != null && !pojo.getProductcontrolid().equals("")) {
                        if (pojo.getItickettypeid().equals(pojo1.getItickettypeid()) && pojo.getProductcontrolid().equals(pojo1.getProductcontrolid())
                                && pojo.getTourdate().equals(pojo1.getTourdate()) && pojo.getIcrowdkindid().equals(pojo1.getIcrowdkindid())) {// 产品、日期、趟次相同则合并
                            // 这里是含有竹筏趟次时间的
                            pojo1.setNumb(String.valueOf(Integer.parseInt(pojo1.getNumb()) + Integer.parseInt(pojo.getNumb())));
                            break;
                        } else {
                            if (j == mergelist.size() - 1) {// 最后一个加入
                                pojo1 = new OrderPojo();
                                BeanUtils.copyProperties(pojo1, pojo);
                                mergelist.add(pojo1);
                                break;
                            }
                        }
                    } else {// 无竹筏的在这里处理 只判断产品和日期
                        if (pojo.getItickettypeid().equals(pojo1.getItickettypeid()) && pojo.getIcrowdkindid().equals(pojo1.getIcrowdkindid())) {
                            pojo1.setNumb(String.valueOf(Integer.parseInt(pojo1.getNumb()) + Integer.parseInt(pojo.getNumb())));
                            break;
                        } else {
                            if (j == mergelist.size() - 1) {// 最后一个加入
                                OrderPojo pojonew = new OrderPojo();
                                BeanUtils.copyProperties(pojonew, pojo);
                                mergelist.add(pojonew);
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
     * @see com.ectrip.order.service.iservice.IOrderService#calculateNewOrderInfo(java.util.List,
     *      String, String)
     * @param newInfo
     * @param stdt
     * @param usid
     * @return
     * @author yangguang Date:2012-2-8
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    private Map calculateNewOrderInfo(List newInfo, String stdt, String usid, long iscenicid) throws IllegalAccessException, InvocationTargetException {
        Custom custom = (Custom) stockswareDao.get(Custom.class, usid);
        String ibusinessid = custom.getIbusinessid().toString();
        // 统计需要增加的竹筏量和金额
        Map map = new HashMap();
        List finallist = new ArrayList();// 合并同样的票
        List addraft = new ArrayList();// 按照同种票同一天进行分组 过滤没有竹筏日期的
        List daylist = new ArrayList();
        List newsonticketlist = new ArrayList();
        List sonticket = null;
        // 合并新增的集合中同样的票
        for (int i = 0; i < newInfo.size(); i++) {
            OrderPojo orderlist = (OrderPojo) newInfo.get(i);
            // 剔除无数量的产品
            if (orderlist.getNumb() == null || orderlist.getNumb().equals("") || orderlist.getNumb().equals("0")) {
                newInfo.remove(i);
                i = i - 1;
                continue;
            }
            // 合并产品相同且价格相同的产品 (有竹筏的需竹筏日期趟次相同的才能合并)
            if (finallist.size() < 1) {
                OrderPojo neworderlist = new OrderPojo();
                BeanUtils.copyProperties(neworderlist, orderlist);
                finallist.add(neworderlist);
            } else {
                for (int a = 0; a < finallist.size(); a++) {
                    OrderPojo compare = (OrderPojo) finallist.get(a);
                    if ((orderlist.getTourdate() == null || orderlist.getTourdate().equals("")) && (orderlist.getItickettypeid().equals(compare.getItickettypeid()))
                            && (orderlist.getIcrowdkindid().equals(compare.getIcrowdkindid()))) {// 套票不包含竹筏票时只要日期为空且编号相等则叠加数量
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else if (orderlist.getTourdate() != null && orderlist.getTourdate().equals(compare.getTourdate()) && orderlist.getItickettypeid().equals(compare.getItickettypeid())
                            && orderlist.getProductcontrolid().equals(compare.getProductcontrolid()) && orderlist.getIcrowdkindid().equals(compare.getIcrowdkindid())) {
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else {
                        if (a == finallist.size() - 1) {
                            OrderPojo neworderlist = new OrderPojo();
                            BeanUtils.copyProperties(neworderlist, orderlist);
                            finallist.add(neworderlist);
                            break;
                        }
                    }
                }
            }
            // 合并
            if (daylist.size() < 1) {
                if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
                    OrderPojo orderlist1 = new OrderPojo();
                    BeanUtils.copyProperties(orderlist1, orderlist);
                    daylist.add(orderlist1);
                }
            } else {
                for (int j = 0; j < daylist.size(); j++) {
                    OrderPojo compare = (OrderPojo) daylist.get(j);
                    if (orderlist.getItickettypeid().equals(compare.getItickettypeid()) && orderlist.getTourdate() != null && orderlist.getTourdate().equals(compare.getTourdate())) {// 过滤出含有游览日得票
                        // 一般都含有竹筏
                        // 然后根据日期合并，部分趟次
                        compare.setNumb(String.valueOf(Integer.parseInt(compare.getNumb()) + Integer.parseInt(orderlist.getNumb())));
                        break;
                    } else if (j == daylist.size() - 1) {
                        if (orderlist.getTourdate() != null && !orderlist.getTourdate().equals("")) {
                            OrderPojo orderlist1 = new OrderPojo();
                            BeanUtils.copyProperties(orderlist1, orderlist);
                            daylist.add(orderlist1);
                            break;
                        }
                    }
                }
            }
        }
        double amont = 0.0;
        // 根据主产品取出所有的子产品
        for (int i = 0; i < finallist.size(); i++) {
            Productcontrol tripinfo = null;
            boolean israft = false;
            OrderPojo orderlist = (OrderPojo) finallist.get(i);
            List pricelist = ecService.getTicketPricelist(Long.parseLong(orderlist.getItickettypeid()), Long.parseLong(orderlist.getIcrowdkindid()), stdt, custom.getIbusinessid().toString(),
                    custom.getNote2());
            Edmcrowdkindpricetab kindprice = (Edmcrowdkindpricetab) pricelist.get(0);
            double price = ecService.getTicketPrice(orderlist.getItickettypeid().toString(), stdt, kindprice.getIcrowdkindpriceid().toString(), ibusinessid);
            Edpofferschemetab schema = ticketTypeService.getScheme(iscenicid, custom.getIbusinessid(), Long.parseLong(orderlist.getIcrowdkindid()), Long.parseLong(orderlist.getItickettypeid()), stdt);
            if (schema != null) {
                amont += price * (Integer.parseInt(orderlist.getNumb()) / schema.getImultiples().intValue() * schema.getIoffernum().intValue());
            } else {
                amont += price * Integer.parseInt(orderlist.getNumb());
            }
            sonticket = ecService.getSonticketlist(kindprice.getIcrowdkindpriceid());
            if (sonticket != null && sonticket.size() > 0) {// 如果有子产品
                for (int j = 0; j < sonticket.size(); j++) {// 遍历子产品 标记是否含有竹筏
                    // 把子产品合并到集合
                    Edmticketcomposepricetab pricepost = (Edmticketcomposepricetab) sonticket.get(j);
                    Edmtickettypetab ticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, pricepost.getItickettypeid());
                    if (newsonticketlist.size() < 1) {
                        RaftComparepojo raft = new RaftComparepojo();
                        raft.setIticketid(ticket.getItickettypeid());
                        if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
                            tripinfo = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
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
                            RaftComparepojo soncompare = (RaftComparepojo) newsonticketlist.get(x);
                            if (orderlist.getProductcontrolid() != null && !orderlist.getProductcontrolid().equals("")) {
                                tripinfo = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                                if (soncompare.getTourdate() != null && !soncompare.equals("") && soncompare.getTourdate().equals(tripinfo.getStdata())
                                        && soncompare.getTripid().intValue() == tripinfo.getTripid().intValue() && soncompare.getIvenueid().intValue() == tripinfo.getIvenueid().intValue()
                                        && soncompare.getIvenueareaid().intValue() == tripinfo.getIvenueareaid().intValue()
                                        && (ticket.getItickettypeid().intValue() == soncompare.getIticketid().intValue())) {
                                    soncompare.setNumb(soncompare.getNumb() + pricepost.getNumb() * Long.parseLong(orderlist.getNumb()));
                                    break;
                                } else {
                                    if (x == newsonticketlist.size() - 1) {
                                        RaftComparepojo raft = new RaftComparepojo();
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
                                if (soncompare.getIticketid().intValue() == ticket.getItickettypeid().intValue() && pricepost.getMactualsaleprice().doubleValue() == soncompare.getPrice()) {
                                    soncompare.setNumb(soncompare.getNumb() + Long.parseLong(orderlist.getNumb()) * pricepost.getNumb());
                                    break;
                                } else {
                                    if (x == newsonticketlist.size() - 1) {
                                        RaftComparepojo raft = new RaftComparepojo();
                                        raft.setIticketid(ticket.getItickettypeid());
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
                        RaftComparepojo raft = new RaftComparepojo();
                        if (tripinfo != null && tripinfo.getProductcontrolid() != null && !tripinfo.getProductcontrolid().equals("")) {
                            tripinfo = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                            raft.setIticketid(tripinfo.getItickettypeid());
                            raft.setTripid(tripinfo.getTripid());
                            raft.setTourdate(tripinfo.getStdata());
                            raft.setIvenueid(tripinfo.getIvenueid());
                            raft.setIvenueareaid(tripinfo.getIvenueareaid());
                        }
                        raft.setNumb(Long.parseLong(orderlist.getNumb()));
                        addraft.add(raft);
                    } else {
                        tripinfo = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(orderlist.getProductcontrolid()));
                        // TODO 要把同产品 同日起 同趟次的合并
                        for (int x = 0; x < addraft.size(); x++) {
                            RaftComparepojo raft = (RaftComparepojo) addraft.get(x);
                            if (raft.getTourdate().equals(tripinfo.getStdata()) && raft.getTripid().intValue() == tripinfo.getTripid().intValue()
                                    && raft.getIvenueid().intValue() == tripinfo.getIvenueid().intValue() && raft.getIvenueareaid().intValue() == tripinfo.getIvenueareaid().intValue()) {
                                raft.setNumb(raft.getNumb() + Long.parseLong(orderlist.getNumb()));
                                break;
                            } else {
                                if (x == addraft.size() - 1) {
                                    RaftComparepojo raft1 = new RaftComparepojo();
                                    raft1.setTripid(tripinfo.getTripid());
                                    raft1.setTourdate(tripinfo.getStdata());
                                    raft1.setIvenueid(tripinfo.getIvenueid());
                                    raft1.setIvenueareaid(tripinfo.getIvenueareaid());
                                    raft1.setNumb(Long.parseLong(orderlist.getNumb()));
                                    raft1.setIticketid(tripinfo.getItickettypeid());
                                    addraft.add(raft1);
                                    break;
                                }
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

    private Map calculateOldOrder(String orid, String iscenicid, List orderInfo) throws IllegalAccessException, InvocationTargetException {
        // 获取原始订单
        List oldlist = ecService.getTOrderlists(orid, Long.parseLong(iscenicid));
        List chalist = new ArrayList();
        for (int i = 0; i < oldlist.size(); i++) {// 遍历在原订单的基础上修改数量之后需要增加的或者减少的产品以及数量
            TOrderlist oldtorderlist = new TOrderlist();
            BeanUtils.populate(oldtorderlist, (Map) oldlist.get(i));
            for (int j = 0; j < orderInfo.size(); j++) {// 修改后的订单
                TOrderlist newtorderlist = (TOrderlist) orderInfo.get(j);
                if (newtorderlist.getOrderlistid().equals(oldtorderlist.getOrderlistid()) && newtorderlist.getIscenicid().equals(oldtorderlist.getIscenicid())) {
                    if (newtorderlist.getNumb().intValue() != oldtorderlist.getNumb().intValue()) {
                        TOrderlist ctorderlist = new TOrderlist();
                        BeanUtils.copyProperties(ctorderlist, oldtorderlist);
                        // 用修改后的订单数量减去修改前的,若为正数则表明是增加,若为负则表明是退订
                        ctorderlist.setNumb(newtorderlist.getNumb() - oldtorderlist.getNumb());
                        ctorderlist.setId(new TOrderlistId(Long.parseLong(oldtorderlist.getOrderlistid()), oldtorderlist.getOrid(), Long.parseLong(iscenicid)));
                        ctorderlist.setAmnt(oldtorderlist.getPric() * (newtorderlist.getNumb() - oldtorderlist.getNumb()));
                        ctorderlist.setYhnumb(oldtorderlist.getYhnumb());
                        chalist.add(ctorderlist);
                    }
                }
            }
        }
        // 按照日期趟次码头分组竹筏
        List raftlist = new ArrayList();
        List daylist = new ArrayList();// 按照竹筏日期分组
        List allzlist = new ArrayList();
        double amount = 0.0;
        MOrder morder = (MOrder) stockswareDao.get(MOrder.class, orid);
        Custom c = (Custom) stockswareDao.get(Custom.class, morder.getZfusid());
        // 遍历修改后和修改前订单对比出来的产品列表
        for (int i = 0; i < chalist.size(); i++) {
            TOrderlist ctorderlist = (TOrderlist) chalist.get(i);// 修改前后对比的产品
            TOrderlist oldtorderlist = (TOrderlist) stockswareDao.get(TOrderlist.class, new TOrderlistId(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid)));
            // TODO 获取到优惠策略 计算差价
            //			Edmcrowdkindpricetab price111 = ticketDao.getProductPrice(ctorderlist.getItickettypeid().toString(), c.getIbusinessid().toString(), oldtorderlist.getDtstartdate().substring(0, 10),
            //					ctorderlist.getIcrowdkindid().toString(), c.getNote2());
            ctorderlist.setIcrowdkindpriceid(oldtorderlist.getIcrowdkindpriceid());
            Edpofferschemetab schema = ticketTypeService.getScheme(Long.parseLong(iscenicid), c.getIbusinessid(), oldtorderlist.getIcrowdkindid(), oldtorderlist.getItickettypeid(), oldtorderlist
                    .getDtstartdate().substring(0, 10));
            int cnum = 0;// 重新计算应优惠数量
            if (schema != null) {
                // 计算出修改之后总数量可有优惠的数量
                cnum = (oldtorderlist.getNumb().intValue() + ctorderlist.getNumb().intValue()) / schema.getImultiples().intValue() * schema.getIoffernum().intValue();
                // 计算次本次修改之后需要收费产品的数量
                cnum = ctorderlist.getNumb().intValue() - (cnum - oldtorderlist.getYhnumb().intValue());
            } else {
                cnum = ctorderlist.getNumb().intValue();
            }
            amount += oldtorderlist.getPric() * cnum;// 叠加价格
            // 需找子产品
            List tzlist = ecService.getTZorderlist(Long.parseLong(ctorderlist.getOrderlistid()), orid, Long.parseLong(iscenicid));
            // 拿出原订单的对应产品
            for (int j = 0; j < tzlist.size(); j++) {// 循环有差异的子票列表
                TZorderlist tzorderlist = (TZorderlist) tzlist.get(j);
                // 子产品对象
                Edmtickettypetab checkticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, tzorderlist.getIztickettypeid());
                RaftComparepojo newraft = new RaftComparepojo();
                newraft.setTourdate(tzorderlist.getDtstartdate().substring(0, 10));
                newraft.setTripid(tzorderlist.getTripid());
                newraft.setIvenueid(tzorderlist.getIvenueid());
                newraft.setIvenueareaid(tzorderlist.getIvenueareaid());
                newraft.setIvenueseatsid(tzorderlist.getIvenueseatsid());
                newraft.setParentticketid(oldtorderlist.getItickettypeid());
                newraft.setEnddate(tzorderlist.getDtenddate());
                newraft.setIcrowdid(tzorderlist.getIcrowdkindid());
                // 用原订单此产品对应的子票数量除以原订单套票的数量即原订单一张套票对应的子票的张数,然后乘以差异之后的数量即修改之后
                newraft.setNumb(ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                newraft.setIticketid(tzorderlist.getIztickettypeid());
                newraft.setPrice(tzorderlist.getZpric());
                newraft.setYhnum(tzorderlist.getZyhnumb().intValue());// 设置优惠
                // 挑选出针对原订单新增和退订的子产品列表
                if (allzlist.size() < 1) {
                    RaftComparepojo newraft1 = new RaftComparepojo();
                    BeanUtils.copyProperties(newraft1, newraft);
                    allzlist.add(newraft1);
                } else {
                    for (int x = 0; x < allzlist.size(); x++) {
                        RaftComparepojo checkraft = (RaftComparepojo) allzlist.get(x);
                        if (checkticket.getBycategorytype().equals("0003")) {
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && tzorderlist.getTripid().equals(checkraft.getTripid())
                                    && checkraft.getIvenueid().equals(tzorderlist.getIvenueid()) && checkraft.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {// 只要日期、产品相同则叠加数量
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;
                            } else {
                                if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    // 跳出循环
                                    allzlist.add(newraft1);
                                    break;
                                }
                            }
                        } else {
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && checkraft.getIticketid().intValue() == tzorderlist.getIztickettypeid().intValue()
                                    && checkraft.getPrice() == tzorderlist.getZpric().doubleValue()) {// 只要日期、产品相同则叠加数量
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
                                break;
                            } else {
                                if (x == allzlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    RaftComparepojo newraft1 = new RaftComparepojo();
                                    BeanUtils.copyProperties(newraft1, newraft);
                                    // 跳出循环
                                    allzlist.add(newraft1);
                                    break;
                                }
                            }
                        }

                    }
                }
                // 子票是竹筏再检测 不是竹筏直接跳过
                if (checkticket.getBycategorytype().equals("0003")) {
                    // 按照竹筏日期及趟次挑选票及数量
                    if (raftlist.size() < 1) {
                        // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
                        RaftComparepojo newraft1 = new RaftComparepojo();
                        BeanUtils.copyProperties(newraft1, newraft);
                        raftlist.add(newraft1);
                    } else {
                        for (int x = 0; x < raftlist.size(); x++) {// 竹筏趟次根据日期趟次码头分组后的集合
                            RaftComparepojo checkraft1 = (RaftComparepojo) raftlist.get(x);
                            // 如果集合中已存在则只叠加数量 这里忽略子产品对应的主产品只计算竹筏的数量
                            if (checkraft1.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && tzorderlist.getTripid().equals(checkraft1.getTripid())
                                    && checkraft1.getIvenueid().equals(tzorderlist.getIvenueid()) && checkraft1.getIvenueareaid().equals(tzorderlist.getIvenueareaid())) {
                                checkraft1.setNumb(checkraft1.getNumb() + tzorderlist.getZnumb() / oldtorderlist.getNumb() * ctorderlist.getNumb());
                                break;// 叠加完之后跳出循环
                            } else {
                                if (x == raftlist.size() - 1) {// 如果寻到到最后一个还是没有,则把加入列表
                                    // 跳出循环
                                    // 由于newraft此对象用于好几个list,当一个list中对象的这变化了,其他不应该变的也变了,所以这里需要重新创建对象
                                    RaftComparepojo newraft1 = new RaftComparepojo();
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
                            RaftComparepojo checkraft = (RaftComparepojo) daylist.get(x);
                            if (checkraft.getTourdate().equals(tzorderlist.getDtstartdate().substring(0, 10)) && checkraft.getIticketid().intValue() == oldtorderlist.getItickettypeid().intValue()
                                    && checkraft.getTripid().intValue() == tzorderlist.getTripid().intValue()) {
                                checkraft.setNumb(checkraft.getNumb() + ctorderlist.getNumb() * tzorderlist.getZnumb() / oldtorderlist.getNumb());
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

    // 计算退订手续费
    private double calculatetdmont(List oldtdlist, String usid, String iscenicid) throws ParseException {
        Custom custom = (Custom) stockswareDao.get(Custom.class, usid);
        double tdmont = 0.0;

        // 计算出退订的手续费
        for (int i = 0; i < oldtdlist.size(); i++) {
            Changebackrate fl = null;
            Prdtripvenuemanage triptime = null;
            RaftComparepojo compare = (RaftComparepojo) oldtdlist.get(i);
            int cnum = 0;
            if (compare.getYhnum() < Math.abs(compare.getNumb().intValue())) {
                cnum = Math.abs(compare.getNumb().intValue()) - compare.getYhnum();
            }
            Productcontrol control = ticketTypeService.getTripControl(compare.getTripid(), compare.getIvenueid(), compare.getIvenueareaid(), compare.getTourdate());
            if (control == null || control.getBystate().intValue() == 1) {// 没有竹筏控制或者竹筏状态正常时
                Ticketxgz tdrule = JSON.parseObject(ecService.ticketbackrule(compare.getIticketid(), Long.parseLong(iscenicid), custom.getLgtp()),Ticketxgz.class);
                double tdfl = 0.0;
                if (tdrule != null) {// 如果退订规则不为空
                    if (tdrule.getJsfs().equals("0001")) {// 按小时
                        triptime = JSON.parseObject(ecService.getTripInfo(compare.getTripid(), compare.getIvenueid(), compare.getIvenueareaid(), compare.getTourdate(), iscenicid, compare.getIticketid().toString()),Prdtripvenuemanage.class);
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        Date d2 = null;
                        if (triptime != null) {// 如果是竹筏则用诸法的日期 非竹筏用票的结束日期
                            d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
                            d2 = df.parse(Tools.getNowString());
                        } else {
                            d1 = df.parse(compare.getEnddate() + " 23:59:59");
                            d2 = df.parse(Tools.getNowString());
                        }
                        if (d1.before(d2)) {
                            tdfl = tdrule.getXyrate2();
                        } else {
                            long diff = d1.getTime() - d2.getTime();
                            long hours = diff / (1000 * 60 * 60);
                            fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001"), Changebackrate.class);
                            if (fl == null) {
                                tdfl = 0.0;
                            } else {
                                tdfl = fl.getTdfl();
                            }
                        }
                    } else if (tdrule.getJsfs().equals("0002")) {// 按天
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        Date d2 = null;
                        if (triptime != null) {
                            d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
                            d2 = df.parse(Tools.getNowString());
                        } else {
                            d1 = df.parse(compare.getEnddate() + " 23:59:59");
                            d2 = df.parse(Tools.getNowString());
                        }
                        if (d1.before(d2)) {
                            tdfl = tdrule.getXyrate2();
                        } else {
                            long diff = d1.getTime() - d2.getTime();
                            long days = diff / (1000 * 60 * 60 * 24);
//                            fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
                            fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002"), Changebackrate.class);
                            if (fl == null) {
                                tdfl = 0.0;
                            } else {
                                tdfl = fl.getTdfl();
                            }
                        }
                    } else {// 常年
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        Date d2 = null;
                        if (triptime != null) {
                            d1 = df.parse(compare.getTourdate() + " " + triptime.getStarttime() + ":00");
                            d2 = df.parse(Tools.getNowString());
                        } else {
                            d1 = df.parse(compare.getEnddate() + " 23:59:59");
                            d2 = df.parse(Tools.getNowString());
                        }
                        if (d1.before(d2)) {
                            tdfl = tdrule.getXyrate2();
                        } else {
//                            fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
                            fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), "", "0003"), Changebackrate.class);
                            tdfl = fl.getTdfl();
                        }
                    }
                    tdmont += compare.getPrice() * tdfl * cnum;
                }
            }
        }
        return tdmont;
    }

    // 计算传入的list的金额
    private double calculateAddMont(List list, Long iscenicid, String orid, Long ibusinessid) {
        double totalmont = 0.0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TOrderlist torderlist = (TOrderlist) list.get(i);
                /*TOrderlist oldobj = torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), orid, iscenicid, torderlist.getIcrowdkindid(), torderlist.getDtstartdate(),
                        torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());*/
                TOrderlist oldobj = ecService.getTorderlistByProductInfo(torderlist.getItickettypeid(), orid, iscenicid, torderlist.getIcrowdkindid(), torderlist.getDtstartdate(),
                        torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                int cnum = 0;
                int thistime = 0;
                Edpofferschemetab schema = ticketTypeService.getScheme(iscenicid, ibusinessid, torderlist.getIcrowdkindid(), torderlist.getItickettypeid(), torderlist.getDtstartdate().substring(0, 10));
                if (schema != null) {
                    if (oldobj != null) {
                        // 计算出修改之后总数量可有优惠的数量
                        cnum = (oldobj.getNumb().intValue() + torderlist.getNumb().intValue()) / schema.getImultiples().intValue() * schema.getIoffernum().intValue();
                        thistime = cnum - oldobj.getYhnumb().intValue();
                        // 计算次本次修改之后需要收费产品的数量
                        cnum = torderlist.getNumb().intValue() - thistime;
                    } else {
                        thistime = torderlist.getNumb().intValue() / schema.getImultiples().intValue() * schema.getIoffernum().intValue();
                        cnum = torderlist.getNumb().intValue() - thistime;
                    }
                    torderlist.setIoffersschemeid(schema.getIoffersschemeid());
                    torderlist.setYhnumb(new Long(Math.abs(thistime)));
                } else {
                    cnum = torderlist.getNumb().intValue();
                }
                totalmont += torderlist.getPric() * cnum;
            }
        }
        return totalmont;
    }

    // 计算传入的list的金额
    private double calculateAddMont(List list, Long iscenicid, String orid) {
        double totalmont = 0.0;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TOrderlist torderlist = (TOrderlist) list.get(i);
               /* TOrderlist oldobj = torderlistdao.getTorderlistByProductInfo(torderlist.getItickettypeid(), orid, iscenicid, torderlist.getIcrowdkindid(), torderlist.getDtstartdate(),
                        torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());*/
                TOrderlist oldobj = ecService.getTorderlistByProductInfo(torderlist.getItickettypeid(), orid, iscenicid, torderlist.getIcrowdkindid(), torderlist.getDtstartdate(),
                		torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
                int cnum = 0;
                int thistime = 0;
                cnum = torderlist.getNumb().intValue();
                totalmont += torderlist.getPric() * cnum;
            }
        }
        return totalmont;
    }

    public List mergeRaft(List charaftlist, List newraft) throws IllegalAccessException, InvocationTargetException {
        if (newraft != null && newraft.size() > 0 && charaftlist != null && charaftlist.size() > 0) {
            for (int i = 0; i < newraft.size(); i++) {
                RaftComparepojo c1 = (RaftComparepojo) newraft.get(i);
                for (int j = 0; j < charaftlist.size(); j++) {// 以这个循环为对象比较外围对象
                    // 缺少的添加进去
                    RaftComparepojo c2 = (RaftComparepojo) charaftlist.get(j);
                    if (c2.getIticketid().intValue() == c1.getIticketid().intValue() && c2.getTourdate().equals(c1.getTourdate()) && c2.getTripid().intValue() == c1.getTripid().intValue()) {
                        c2.setNumb(c2.getNumb() + c1.getNumb());
                        break;
                    } else {
                        if (j == charaftlist.size() - 1) {
                            RaftComparepojo addcharraft = new RaftComparepojo();
                            BeanUtils.copyProperties(addcharraft, c1);
                            charaftlist.add(addcharraft);
                            break;
                        }
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

    // 计算信用积分(订票用)
    private Long calculateCredit(String usid, List charraftlist, String iscenicid) {
        // TODO 先查询出信用度，再计算出退票需要
        RaftComparepojo c1 = null;
        RaftComparepojo c2 = null;
        Prdtripvenuemanage prd = null;
        Long editnum = 0l;
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                prd = JSON.parseObject(ecService.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString()),Prdtripvenuemanage.class);
                if (c1.getNumb() > 0) {
                    editnum += prd.getIshot() * c1.getNumb();
                } else {
                    editnum += prd.getIshot() * c1.getNumb();
                }
            }
        }
        return editnum;
    }

    private boolean queryPositiveraft(List charraftlist, String iscenicid) {
        // TODO 先查询出信用度，再计算出退票需要
        RaftComparepojo c1 = null;
        RaftComparepojo c2 = null;
        Prdtripvenuemanage prd = null;
        boolean positive = false;// 是否大于0
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                prd = JSON.parseObject(ecService.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString()),Prdtripvenuemanage.class);
                if (prd.getIshot() > 0) {
                    positive = true;
                }
            }
        }
        return positive;
    }

    private int checkStopRaft(List charraftlist, String iscenicid) {
        Productcontrol control = null;
        RaftComparepojo c1 = null;
        Prdtripvenuemanage prd = null;
        int result = 0;
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
//                prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString());
                prd = JSON.parseObject(ecService.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString()),Prdtripvenuemanage.class);
                control = ticketTypeService.getTripControl(prd.getTripid(), prd.getIvenueid(), prd.getIvenueareaid(), c1.getTourdate());
                if (control.getBystate() == 0) {
                    result = 1;
                }
            }
        }
        return result;
    }

    // 新增订单(退订单，或者增量订单)
    private double addNewOrder(List addlist, String oldorid, String neworid, String iscenicid, String currentusid, MOrder oldmorder, double totalmont, String ortp, int stopraft, double sxf,
                               List oldtdlist,Long icompanyinfoid) throws NumberFormatException, Exception {
        Custom user = (Custom) stockswareDao.get(Custom.class, oldmorder.getUsid());
        // 开始封装订单
        double totaltdsx = 0.0;
        if (addlist != null && addlist.size() > 0) {
            // TODO 增量订单neworderlist 和 orderlistInfo与 原订单中中数量为对比后 挑选出来的数量为正数的产品
            oldmorder = (MOrder) stockswareDao.get(MOrder.class, oldorid);
            //Map map = StatisticsService.getMonthDate(Integer.parseInt(oldmorder.getStdt().substring(0, 4)), Integer.parseInt(oldmorder.getStdt().substring(5, 7)));
            Map map = getMonthDate(Integer.parseInt(oldmorder.getStdt().substring(0, 4)), Integer.parseInt(oldmorder.getStdt().substring(5, 7)));
            MOrder morder = new MOrder();
            YOrder yorder = new YOrder();
            morder.setOrid(neworid);
            morder.setOrtp(ortp);// 订单类型 03：增量订单
            morder.setUsid(oldmorder.getUsid());
            morder.setOrda(Tools.getDays());
            morder.setOrti(Tools.getNowString().substring(11, Tools.getNowString().length()));
            morder.setIsj(0l);
            morder.setYhamnt(0.0);// 优惠金额
            morder.setMont(0.0);
            morder.setZfmont(0.0);// 支付的金额
            morder.setIsallcp(0l);// 是否全部出票
            morder.setZffs(oldmorder.getZffs());// 支付方式
            morder.setBank("92");// 支付银行
            morder.setZfusid(oldmorder.getZfusid());// 订单支付人
            morder.setPayorid("");// 支付订单号
            morder.setBankdata(Tools.getDays());// 支付日期
            morder.setBanktime(Tools.getNowTimeString());// 支付时间
            morder.setSrid(oldorid);// 对应原订单号
            morder.setStdt(Tools.getDays());
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                morder.setIsjl(1l);
            } else {
                morder.setIsjl(0l);
            }
            morder.setTpfs("02");//修改方式 00 原始预定  01 出票前修改 02 出票后修改
            stockswareDao.save(morder);
            YOrder oldyorder = (YOrder) stockswareDao.get(YOrder.class, new YOrderId(oldorid, Long.parseLong(iscenicid)));
            yorder.setId(new YOrderId(morder.getOrid(), Long.parseLong(iscenicid)));
            // 根据编号找服务商类型
            Esbscenicareatab esb = (Esbscenicareatab) this.get(Esbscenicareatab.class, Long.parseLong(iscenicid));
            yorder.setScenictype(esb.getScenictype());// 服务商类别 01 景区 06酒店 07 旅行社
            // 08 超市 09 租赁公司
            yorder.setUsid(morder.getUsid());// 游客编号
            yorder.setIbusinessid(user.getIbusinessid());// 业务id
            yorder.setSztravelbillno("");// 行程单号
            yorder.setIregionalid(0l);// 客源地id
            yorder.setTdlx("");// 团队性质
            yorder.setDtstartdate(oldyorder.getDtstartdate());// 游览开始日期
            yorder.setDtenddate(oldyorder.getDtenddate());// 游览结束日期
            yorder.setDyusid("");// 导游id
            yorder.setOrnm("");// 领票人姓名
            yorder.setOrzj("");// 领票人证件类别
            yorder.setOrhm("");// 领票人证件号码
            yorder.setFaxno("");// 传真号
            yorder.setOrph("");// 领票人电话
            yorder.setMont(0.0);
            yorder.setYhamnt(0.0);// 优惠金额
            yorder.setZfmont(0.0);// 支付的金额
            yorder.setYoufei(0.0);// 邮费
            yorder.setTpfs("02");
            // ticketDao.save(torder);
            stockswareDao.save(yorder);
            long indexpk = getMaxPk("id.orderlistid", new String[] { "id.iscenicid" }, new Long[] { Long.parseLong(iscenicid) }, new String[] { "id.orid" }, new String[] { oldorid }, "TOrderlist");
            int yhnum = 0;// 这个积分的..
            double yhamnt = 0.0;// 这个是优惠方案的
            int pk = 0;
            DecimalFormat format = new DecimalFormat("0.00");
            Numjifenset set = numjifenSetService.viewNumjifen(Long.parseLong(iscenicid));
            long totalmonthjf = 0;
            long totalyearjf = 0;
            for (int i = 0; i < addlist.size(); i++) {
                TOrderlist torderlist = (TOrderlist) addlist.get(i);
                YOrderlist yorderlist = new YOrderlist();
                yorderlist.setId(new YOrderlistId(new Long(i) + 1, morder.getOrid(), Long.parseLong(iscenicid)));
                yorderlist.setIcrowdkindpriceid(torderlist.getIcrowdkindpriceid());
                yorderlist.setIcrowdkindid(torderlist.getIcrowdkindid());
                yorderlist.setItickettypeid(torderlist.getItickettypeid());
                yorderlist.setDtstartdate(torderlist.getDtstartdate());// 开始日期
                yorderlist.setDtenddate(torderlist.getDtenddate());// 结束日期
                yorderlist.setPric(torderlist.getPric());// 单价
                yorderlist.setJsprice(torderlist.getJsprice());// 结算价
                yorderlist.setNumb(Math.abs(torderlist.getNumb()));// 数量
                yorderlist.setYhnumb(torderlist.getYhnumb());// 优惠数量
                yorderlist.setAmnt(Math.abs(torderlist.getNumb() * torderlist.getPric()));// 金额
                yorderlist.setYhamnt(Math.abs(torderlist.getYhnumb() * torderlist.getPric()));// 优惠金额
                yorderlist.setIoffersschemeid(torderlist.getIoffersschemeid());// 优惠方案ID
                stockswareDao.save(yorderlist);
                TOrderlist old = ecService.getTorderlistByProductInfo(torderlist.getItickettypeid(), oldorid, Long.parseLong(iscenicid), torderlist.getIcrowdkindid(),
                        torderlist.getDtstartdate(), torderlist.getDtenddate(), torderlist.getWharfdate(), torderlist.getPdctrolid());
				/*Numjifensetlist detail = statisicsDao.getSalesRule(torderlist.getItickettypeid(), set.getNid(), torderlist.getIcrowdkindid(), user.getIbusinessid(),
						torderlist.getDtstartdate());*/
                if (old != null) {// 退订时ID 肯定有, 如果是增量 那么ID可能有
                    // 可能没有有就修改 没有就新增
                    // TODO 先获取原先的信息 然后再修改
					/*	long jf = 0;
					if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
						if (detail.getXffs().equals("03")) {
							// 计算数量变化后的积分
							if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
								jf = (old.getNumb() + torderlist.getNumb())  / detail.getIint3().intValue() * detail.getIint4() + detail.getIint4();
							} else {
								jf = (old.getNumb() + torderlist.getNumb())  / detail.getIint3().intValue() * detail.getIint4();
							}
							jf = jf - old.getIsi();
							totalmonthjf += jf;
							yorderlist.setIsi(Math.abs(jf));
						} else {
							// 计算数量变化后的积分
							if ((old.getNumb() + torderlist.getNumb()) % detail.getIint3() > 0) {
								jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4() + detail.getIint4();
							} else {
								jf = (old.getNumb() + torderlist.getNumb()) / detail.getIint3().intValue() * detail.getIint4();
							}
							jf = jf - old.getIsh();
							totalyearjf += jf;
							yorderlist.setIsh(Math.abs(jf));
						}
					}*/
                    List list = ecService.getTZorderlist(old.getId().getOrderlistid(), old.getId().getOrid(), old.getId().getIscenicid());
                    for (int y = 0; y < list.size(); y++) {
                        double fl = 0.0;
                        TZorderlist newzlist = (TZorderlist) list.get(y);
                        YZorderlist yzorderlist = new YZorderlist();
                        yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId().getOrid(), yorderlist.getId().getIscenicid()));
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
                        yzorderlist.setZyhnumb(Math.abs(newzlist.getZnumb() / old.getNumb() * torderlist.getYhnumb()));
                        yzorderlist.setZyhamnt(yzorderlist.getZyhnumb() * yzorderlist.getZpric());
                        yzorderlist.setZamnt(Math.abs(newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        yzorderlist.setJsprice(newzlist.getJsprice());
                        if (torderlist.getNumb().intValue() > 0) {
                            newzlist.setZyhnumb(newzlist.getZyhnumb() + (torderlist.getYhnumb() * (newzlist.getZnumb() / old.getNumb())));
                        } else {
                            newzlist.setZyhnumb(newzlist.getZyhnumb() - (torderlist.getYhnumb() * (newzlist.getZnumb() / old.getNumb())));
                        }
                        // 这里有可能是退订单，也可能是增量 根据原tzorderlist的子票数量除以torderlist的数量
                        // 再乘以修改后的数量(增量为正，退订为负),再乘以单价，然后加到原有的总价中(增量为证，退订为负)
                        newzlist.setZamnt(Math.abs(newzlist.getZamnt() + newzlist.getZpric() * newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        newzlist.setZnumb(Math.abs(newzlist.getZnumb() + newzlist.getZnumb() / old.getNumb() * torderlist.getNumb()));
                        newzlist.setZyhamnt(newzlist.getZyhnumb() * newzlist.getZpric());
                        Productcontrol control = ticketTypeService.getTripControl(newzlist.getTripid(), newzlist.getIvenueid(), newzlist.getIvenueareaid(), newzlist.getDtstartdate().substring(0, 10));
                        if (ortp.equals("02") && !oldmorder.getDdzt().equals("23")) {// 退订且原订单状态不为申请单
                            if (Math.abs(sxf) > 0) {// 如果按照整个订单统计出来的手续费大于0才填充子票中的手续
                                fl = gettdfl(yzorderlist, oldmorder.getUsid());
                                yzorderlist.setMhandcharge(yzorderlist.getZpric() * fl * yzorderlist.getZnumb());
                                yzorderlist.setTdfl(fl);
                                RaftComparepojo tdcompare = null;
                                for (int x = 0; x < oldtdlist.size(); x++) {
                                    tdcompare = (RaftComparepojo) oldtdlist.get(x);
                                    if (yzorderlist.getTripid().intValue() > 0) {
                                        if (tdcompare.getIticketid().intValue() == yzorderlist.getIztickettypeid().intValue()
                                                && tdcompare.getTourdate().equals(yzorderlist.getDtstartdate().substring(0, 10))
                                                && tdcompare.getTripid().intValue() == yzorderlist.getTripid().intValue() && control != null && control.getBystate().intValue() == 1 && fl > 0) {
                                            // TODO 这里比较数量 若需要退订的大于当前的数量 那么全部收取
                                            // 若小于则填充要收取的数量
                                            if (Math.abs(tdcompare.getNumb()) > yzorderlist.getZnumb()) {
                                                yzorderlist.setSqnumber(yzorderlist.getZnumb());
                                                tdcompare.setNumb(Math.abs(tdcompare.getNumb()) - yzorderlist.getZnumb());
                                            } else {
                                                yzorderlist.setSqnumber(Math.abs(tdcompare.getNumb()));
                                                tdcompare.setNumb(0l);
                                            }
                                        }
                                    } else {
                                        if (tdcompare.getIticketid().intValue() == yzorderlist.getIztickettypeid().intValue()
                                                && tdcompare.getTourdate().equals(yzorderlist.getDtstartdate().substring(0, 10)) && fl > 0
                                                && tdcompare.getPrice() == yzorderlist.getZpric().doubleValue()) {
                                            if (Math.abs(tdcompare.getNumb()) > yzorderlist.getZnumb()) {
                                                yzorderlist.setSqnumber(yzorderlist.getZnumb());
                                                tdcompare.setNumb(Math.abs(tdcompare.getNumb()) - yzorderlist.getZnumb());
                                            } else {
                                                yzorderlist.setSqnumber(Math.abs(tdcompare.getNumb()));
                                                tdcompare.setNumb(0l);
                                            }
                                        }
                                    }
                                }
                            } else {
                                yzorderlist.setMhandcharge(0.0);
                                yzorderlist.setSqnumber(0l);
                            }
                        } else {
                            yzorderlist.setMhandcharge(0.0);
                            yzorderlist.setSqnumber(0l);
                        }
                        stockswareDao.save(yzorderlist);
                        if (newzlist.getZnumb().intValue() < 1) {
                        	stockswareDao.delete(newzlist);
                        } else {
                        	stockswareDao.update(newzlist);
                        }
                        if (ortp.equals("02") && !oldmorder.getDdzt().equals("23")) {
                            if (Math.abs(sxf) > 0) {// 如果按照整个订单统计出来的手续费大于0才填充子票中的手续
                                if (yorderlist.getMhandcharge() != null && !yorderlist.getMhandcharge().equals("")) {
                                    yorderlist.setMhandcharge(yorderlist.getMhandcharge() + yzorderlist.getMhandcharge());
                                } else {
                                    yorderlist.setMhandcharge(yzorderlist.getMhandcharge());
                                }
                            } else {
                                yorderlist.setMhandcharge(0.0);
                            }
                        } else {
                            yorderlist.setMhandcharge(0.0);
                        }
                    }
                    old.setNumb(Math.abs(old.getNumb() + torderlist.getNumb()));
                    old.setAmnt(old.getNumb() * old.getPric());
                    if (torderlist.getNumb().intValue() > 0) {
                        old.setYhnumb(old.getYhnumb() + torderlist.getYhnumb());
                    } else {
                        old.setYhnumb(old.getYhnumb() - torderlist.getYhnumb());
                    }
                    old.setYhamnt(old.getYhnumb() * old.getPric());
					/*if (oldmorder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
						if(detail.getXffs().equals("03")){//月消费
							old.setIsi(old.getIsi()+jf);
						}else{
							old.setIsh(old.getIsh()+jf);
						}
					}*/
                    if (old.getNumb().intValue() < 1) {
                    	stockswareDao.delete(old);
                    } else {
                    	stockswareDao.update(old);
                    }
                } else {
                    pk++;
                    TOrderlist newtorderlist = new TOrderlist();
                    BeanUtils.copyProperties(newtorderlist, torderlist);
                    newtorderlist.setId(new TOrderlistId(new Long(indexpk + pk), oldorid, Long.parseLong(iscenicid)));
                    newtorderlist.setAmnt(Math.abs(newtorderlist.getAmnt()));
                    newtorderlist.setNumb(Math.abs(newtorderlist.getNumb()));
                    newtorderlist.setJsprice(Math.abs(newtorderlist.getJsprice()));
                    long jf=0;
					/*if (oldmorder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
						jf = torderlist.getNumb() / detail.getIint3().intValue() * detail.getIint4();
						if (detail.getXffs().equals("03")) {
							totalmonthjf += jf;
							yorderlist.setIsi(jf);
							newtorderlist.setIsi(jf);
						} else {
							totalyearjf += jf;
							yorderlist.setIsh(jf);
							newtorderlist.setIsh(jf);
						}
					}*/
                    newtorderlist.setYhnumb(torderlist.getYhnumb());
                    newtorderlist.setYhamnt(newtorderlist.getPric() * torderlist.getYhnumb());
                    stockswareDao.saveOrUpdate(newtorderlist);
                    int indexpk2 = 0;
                    List<TZorderlist> tzorderlists = newtorderlist.getZorderlist();
                    for (int y = 0; y < tzorderlists.size(); y++) {// 如果走到这里肯定是新增的票,所以tzorderlist的数量实在传入时已经封装好的,无需再次封装
                        TZorderlist newzlist = new TZorderlist();
                        BeanUtils.copyProperties(newzlist, tzorderlists.get(y));
                        newzlist.setZyhnumb(newzlist.getZnumb() / newtorderlist.getNumb() * newtorderlist.getYhnumb());
                        newzlist.setZyhamnt(newzlist.getZyhnumb() * newzlist.getZpric());
                        newzlist.setId(new TZorderlistId(new Long(y + 1), newtorderlist.getId().getOrderlistid(), oldorid, newtorderlist.getId().getIscenicid()));
                        stockswareDao.save(newzlist);
                        YZorderlist yzorderlist = new YZorderlist();
                        yzorderlist.setId(new YZorderlistId(new Long(y) + 1, yorderlist.getId().getOrderlistid(), yorderlist.getId().getOrid(), yorderlist.getId().getIscenicid()));
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
                        yzorderlist.setZyhnumb(newzlist.getZnumb() / newtorderlist.getNumb() * newtorderlist.getYhnumb());
                        yzorderlist.setZyhamnt(yzorderlist.getZyhnumb() * yzorderlist.getZpric());
                        yzorderlist.setZamnt(Math.abs(newzlist.getZamnt()));
                        yzorderlist.setJsprice(Math.abs(newzlist.getJsprice()));
                        stockswareDao.save(yzorderlist);
                    }
                }
                if (ortp.equals("02") && !oldmorder.getDdzt().equals("23") && Math.abs(sxf) > 0) {// 如果是退订单
                    totaltdsx += yorderlist.getMhandcharge();
                }
                yorder.setYhamnt(yorder.getYhamnt() + yorderlist.getYhamnt());
                yorder.setMont(yorderlist.getAmnt() + yorder.getMont());
                yorder.setZfmont(yorder.getZfmont() + yorderlist.getAmnt() - yorderlist.getYhamnt());
            }
            if (oldmorder.getIsjl() != null && oldmorder.getIsjl().intValue() == 1) {
                yorder.setIsi(totalmonthjf);
                morder.setIsi(totalmonthjf);
                yorder.setIsh(totalyearjf);
                morder.setIsh(totalyearjf);
            }
            if (ortp.equals("02")) {// 退订
                morder.setDdzt("06");// 订单状态 06 退订已退款
                morder.setTpmont(Math.abs(totalmont));
                morder.setNotef("00");
                morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
                morder.setTpsx(Math.abs(sxf));
                yorder.setNotef("00");
                yorder.setTpmont(Math.abs(totalmont));
                yorder.setDdzt("06");// 订单状态 06 退订已退款
                yorder.setTpdate(morder.getTpdate());
                yorder.setTpsx(Math.abs(sxf));
            } else if (ortp.equals("03")) {// 增量
                morder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
                morder.setTpsx(0.0);
                morder.setTpmont(totalmont);
                morder.setTpdate(morder.getOrda() + " " + morder.getOrti());
                morder.setNotef("00");
                yorder.setDdzt("88");// 订单状态 00 未支付 01已支付 02 已完成
                yorder.setTpsx(0.0);
                yorder.setTpmont(totalmont);
                yorder.setTpdate(morder.getTpdate());
            }
            morder.setYhamnt(yorder.getYhamnt());
            morder.setZfmont(yorder.getZfmont());
            morder.setMont(yorder.getMont());
            stockswareDao.update(morder);
            stockswareDao.update(yorder);
			/*	if (ortp.equals("02")) {
				// 计算预付款,退订手续费tdsx一般都是负数
				minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), Math.abs(sxf), ortp,icompanyinfoid);
			} else {
				// 增量订单手续费传0过去
				minusUserYfk(user.getUsid(), oldmorder.getZfusid(), neworid, Math.abs(totalmont), 0, ortp,icompanyinfoid);
			}*/
			/*	if (morder.getIsjl() != null && morder.getIsjl().intValue() == 1) {
//					Usernumjflist usernumjifenlist = new Usernumjflist();
//					usernumjifenlist.setId(new UsernumjflistId(morder.getOrid(), oldmorder.getZfusid()));
//					usernumjifenlist.setNid(BigDecimal.valueOf(set.getNid()));
//					usernumjifenlist.setJftp("04");//年
//					usernumjifenlist.setEtdt(Tools.getDays());
//					usernumjifenlist.setStdt(Tools.getDays());
//					usernumjifenlist.setStdt2(Tools.getDays());
//					usernumjifenlist.setIscenicid(new BigDecimal(iscenicid));
//					usernumjifenlist.setItickettypeid(BigDecimal.valueOf(0));
//					usernumjifenlist.setItickettypeid2(BigDecimal.valueOf(0));
//					if (ortp.equals("02")) {
//						usernumjifenlist.setJflb(BigDecimal.valueOf(-1));
//					} else {
//						usernumjifenlist.setJflb(BigDecimal.valueOf(1));
//					}
//					usernumjifenlist.setPoint(Double.parseDouble(String.valueOf(totalyearjf)));
//					usernumjifenlist.setStdt(Tools.getDays());
//					usernumjifenlist.setZusid(morder.getZfusid());
//					usernumjifenlist.setIsvalid(BigDecimal.valueOf(1));
//					usernumjifenlist.setZusid(morder.getZfusid());
//					statisicsDao.save(usernumjifenlist);
				    Usernumjflist usernumjifenlist = new Usernumjflist();
					usernumjifenlist.setId(new UsernumjflistId(morder.getOrid(), oldmorder.getZfusid()));
					usernumjifenlist.setNid(BigDecimal.valueOf(set.getNid()));
					usernumjifenlist.setJftp("03");//月
					usernumjifenlist.setEtdt(Tools.getDays());
					usernumjifenlist.setStdt(Tools.getDays());
					usernumjifenlist.setStdt2(Tools.getDays());
					usernumjifenlist.setIscenicid(new BigDecimal(iscenicid));
					usernumjifenlist.setItickettypeid(BigDecimal.valueOf(0));
					usernumjifenlist.setItickettypeid2(BigDecimal.valueOf(0));
					if (ortp.equals("02")) {
						usernumjifenlist.setJflb(BigDecimal.valueOf(-1));
					} else {
						usernumjifenlist.setJflb(BigDecimal.valueOf(1));
					}
					usernumjifenlist.setPoint(Double.parseDouble(String.valueOf(totalmonthjf)));
					usernumjifenlist.setStdt(Tools.getDays());
					usernumjifenlist.setZusid(morder.getZfusid());
					usernumjifenlist.setIsvalid(BigDecimal.valueOf(1));
					usernumjifenlist.setZusid(morder.getZfusid());
					statisicsDao.save(usernumjifenlist);
					Usernumjf userjf=null;
					if(usernumjifenlist.getJftp().equals("03")){//月
						userjf = statisicsDao.getJifenByUser(map.get("startDate").toString(), map.get("endDate").toString(), morder.getZfusid(), new Long(1), Long.parseLong(iscenicid));
					}else{
						userjf = statisicsDao.getJifenByUser(map.get("startDate").toString().substring(0,4)+"-01-01", map.get("endDate").toString().substring(0,4)+"-12-31", morder.getZfusid(), new Long(2), Long.parseLong(iscenicid));
					}
					if(usernumjifenlist.getJftp().equals("03")){//月
						userjf.setYpoint(userjf.getYpoint() + totalmonthjf);
					}else{
							userjf.setYpoint(userjf.getYpoint() + totalyearjf);
					}
					statisicsDao.update(userjf);
				}*/
        }
        return totaltdsx;
    }


    // 减掉(或增加)竹筏量
    private boolean minusRaftCount(List charraftlist, String iscenicid) {
        Productcontrol control = null;
        RaftComparepojo c1 = null;
        Prdtripvenuemanage prd = null;
        if (charraftlist != null) {
            for (int i = 0; i < charraftlist.size(); i++) {
                c1 = (RaftComparepojo) charraftlist.get(i);
                /*prd = ticketDao.getTripInfo(c1.getTripid(), c1.getIvenueid(), c1.getIvenueareaid(), c1.getTourdate(), iscenicid, c1.getIticketid().toString());
                control = ticketDao.getTripControl(prd.getTripid(), prd.getIvenueid(), prd.getIvenueareaid(), c1.getTourdate());*/
                control.setSoldnumber(control.getSoldnumber() + c1.getNumb());
                stockswareDao.update(control);
            }
        }
        return true;
    }

    // 减掉(或增加)竹筏量
    private boolean minusDayCount(List newdaylist, List olddaylist, String iscenicid) throws IllegalAccessException, InvocationTargetException {
        RaftComparepojo checkpojo1 = null;
        OrderPojo checkpojo = null;
        Productcontrol control = null;
        if (newdaylist != null && newdaylist.size() > 0) {
            for (int i = 0; i < newdaylist.size(); i++) {
                checkpojo = (OrderPojo) newdaylist.get(i);
                if (olddaylist == null || olddaylist.size() < 1) {
                    checkpojo1 = new RaftComparepojo();
                    control = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
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
                        if (checkpojo1.getIticketid().intValue() == Integer.parseInt(checkpojo.getItickettypeid()) && checkpojo1.getTourdate().equals(checkpojo.getTourdate())) {
                            checkpojo1.setNumb(checkpojo1.getNumb() + Integer.parseInt(checkpojo.getNumb()));
                            break;
                        } else {
                            if (i == newdaylist.size() - 1) {
                                checkpojo1 = new RaftComparepojo();
                                control = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
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
                list = ecService.getNumberControllData(checkpojo1.getIticketid().toString(), checkpojo1.getTourdate(), "02");
                if (list != null && list.size() > 0) {
                    control = new Productcontrol();
                    BeanUtils.populate(control, (Map) list.get(0));
                    control = (Productcontrol) stockswareDao.get(Productcontrol.class, control.getProductcontrolid());
                    control.setSoldnumber(control.getSoldnumber() + checkpojo1.getNumb());
                    stockswareDao.update(control);
                }
            }
        }
        return true;
    }

    public void updateOrderZtByZeroProduct(String orid, Long iscenicid, String ddzt) {
        List list = ecService.getTOrderlists(orid, iscenicid);
        if (list == null || list.size() < 1) {
            TOrder torder = (TOrder) stockswareDao.get(TOrder.class, new TOrderId(orid, iscenicid));
            torder.setDdzt(ddzt);
            stockswareDao.update(torder);
            YOrder yorder = (YOrder) stockswareDao.get(YOrder.class, new YOrderId(orid, iscenicid));
            yorder.setDdzt(ddzt);
            stockswareDao.update(yorder);
        }
    }

    private void updateMOrderStatusByZeroProduct(String orid, String ddzt) {
//        List list1 = torderlistdao.getTOrderlists(orid);
        List list1 = ecService.getTOrderlistByOrid(orid);
        if (list1 == null || list1.size() < 1) {
            MOrder morder = (MOrder) stockswareDao.get(MOrder.class, orid);
            morder.setDdzt(ddzt);
            stockswareDao.update(morder);
        }
    }


    public double gettdfl(YZorderlist zorderlist, String usid) throws ParseException {
        Productcontrol control = null;
        Ticketxgz tdrule = null;
        double tdfl = 0.0;
        Custom custom = (Custom) stockswareDao.get(Custom.class, usid);
        Changebackrate fl = null;
        double tdmont = 0.0;
        control = ticketTypeService.getTripControl(zorderlist.getTripid(), zorderlist.getIvenueid(), zorderlist.getIvenueareaid(), zorderlist.getDtstartdate().substring(0, 10));
        
        if (control == null || control.getBystate().intValue() == 1) {
            tdrule = JSON.parseObject(ecService.ticketbackrule(zorderlist.getIztickettypeid(), zorderlist.getId().getIscenicid(), custom.getLgtp()),Ticketxgz.class);
            if (tdrule != null) {// 如果退订规则不为空
                if (tdrule.getJsfs().equals("0001")) {// 按小时
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long hours = diff / (1000 * 60 * 60);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), String.valueOf(hours), "0001"),Changebackrate.class);
                        if (fl == null) {
                            tdfl = 0.0;
                        } else {
                            tdfl = fl.getTdfl();
                        }
                    }
                } else if (tdrule.getJsfs().equals("0002")) {// 按天
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
//                        fl = morderdao.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002");
                        fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), String.valueOf(days), "0002"),Changebackrate.class);
                        tdfl = fl.getTdfl();
                    }
                } else {// 常年
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = df.parse(zorderlist.getDtenddate());
                    Date d2 = df.parse(Tools.getNowString());
                    long diff = d1.getTime() - d2.getTime();
                    long days = diff / (1000 * 60 * 60 * 24);
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
//                        fl = morderdao.getflByTime(tdrule.getGzid(), "", "0003");
                        fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), "", "0003"),Changebackrate.class);
                        tdfl = fl.getTdfl();
                    }
                }
            }
        }
        return tdfl;
    }

    public static Map getMonthDate(int year, int month) {
        Map map = new HashMap();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        String thisMonthStart = format.format(cal.getTime());// 本月开始（本月1号）;
        System.out.println("开始->" + thisMonthStart);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String thisMonthEnd = format.format(cal.getTime());
        cal.clear();
        System.out.println("结束->" + thisMonthEnd);
        map.put("startDate", thisMonthStart);
        map.put("endDate", thisMonthEnd);
        return map;
    }

   

    /**
     *
     * @Title: getMOrderList
     * @Description: 网上预定订单
     * @param @param orid
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     */
    public List getMOrderList(String orid) {
        return ecService.getMOrderList(orid);
    }


    public Map validateOrderInfo(String orid, List orderInfo, List newInfo, String usid, String stdt, String ibusinessid, String iscenicid,Long icompanyinfoid) throws IllegalAccessException, InvocationTargetException {
        List newOrderInfo = new ArrayList();
        if (newInfo != null && newInfo.size() > 0) {
            newOrderInfo.addAll(newInfo);
        }
        List oldOrderInfo = new ArrayList();
        if (orderInfo != null && orderInfo.size() > 0) {
            oldOrderInfo.addAll(orderInfo);
        }
        Map map = new HashMap();
        Map newInfoMap = null;
        Double needminus = 0.0;
        List newraftlist = null;
        if (newOrderInfo != null && newOrderInfo.size() > 0) {
            newOrderInfo = mergenewInfo(newOrderInfo);
            newInfoMap = calculateNewOrderInfo(newOrderInfo, stdt, usid, Long.parseLong(iscenicid));
            needminus = (Double) newInfoMap.get("amont");// 新增产品的金额
            newraftlist = (List) newInfoMap.get("raftlist");// 新增产品所需的竹筏趟次列表及数量
        }
        Map oldInfoMap = calculateOldOrder(orid, iscenicid, oldOrderInfo);
        List chalist = (List) oldInfoMap.get("chaticket");// 根据产品算出的需要加减的产品列表
        if ((chalist == null || chalist.size() < 1) && (newOrderInfo == null || newOrderInfo.size() < 1)) {
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
		/*VipBalanceUsIdNotebId vipBalanceId = new VipBalanceUsIdNotebId();
		vipBalanceId.setNoteb(icompanyinfoid.toString());
		vipBalanceId.setUsid(usid);
		OTAVipbalance balance = (OTAVipbalance) torderdao.get(OTAVipbalance.class, vipBalanceId);
		// TODO 比较两个map中的值，计算是否可以修改 不可修改返回false
		if (balance.getPoint() < oldneedmount + needminus) {
			map.put("result", false);
			map.put("errtp", "2");// 余额不足
			return map;
		}*/
        map.put("result", true);
        map.put("needmoney", oldneedmount + needminus);
        return map;
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
                    if (c2.compare(c1)) {
                        c2.setNumb(c2.getNumb() + c1.getNumb());
                    } else if (j == list2.size() - 1) {
                        list2.add(c1);
                        break;
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
            control = ticketTypeService.getTripControl(checkpojo.getTripid(), checkpojo.getIvenueid(), checkpojo.getIvenueareaid(), checkpojo.getTourdate());
            if (control == null || control.getBystate() == -1) {
                checkticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, checkpojo.getIticketid());
                checktrip = (Trip) stockswareDao.get(Trip.class, checkpojo.getTripid());
                checkpojo.setErrtp("0");// 停排
                checkpojo.setTicketname(checkticket.getSztickettypename());
                checkpojo.setTripname(checktrip.getTripname());
            } else {
                if (control.getSalablenumber() - control.getSoldnumber() >= checkpojo.getNumb()) {
                    validatelist.remove(checkpojo);
                    i = i - 1;
                } else {
                    checkticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, checkpojo.getIticketid());
                    checktrip = (Trip) stockswareDao.get(Trip.class, checkpojo.getTripid());
                    checkpojo.setErrtp("1");// 数量不足
                    checkpojo.setTicketname(checkticket.getSztickettypename());
                    checkpojo.setTripname(checktrip.getTripname());
                }
            }
        }
        return validatelist;
    }

    // 验证含有竹筏票的日控制
    public List validateDay(List newdaylist, List olddaylist) throws IllegalAccessException, InvocationTargetException {
        OrderPojo checkpojo = null;
        RaftComparepojo checkpojo1 = null;
        Productcontrol control = null;
        if (newdaylist != null && newdaylist.size() > 0) {
            for (int i = 0; i < newdaylist.size(); i++) {
                checkpojo = (OrderPojo) newdaylist.get(i);
                if (olddaylist == null || olddaylist.size() < 1) {
                    checkpojo1 = new RaftComparepojo();
                    control = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
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
                        if (checkpojo1.getIticketid().intValue() == Integer.parseInt(checkpojo.getItickettypeid()) && checkpojo1.getTourdate().equals(checkpojo.getTourdate())) {
                            checkpojo1.setNumb(checkpojo1.getNumb() + Integer.parseInt(checkpojo.getNumb()));
                        } else {
                            if (i == newdaylist.size() - 1) {
                                checkpojo1 = new RaftComparepojo();
                                control = (Productcontrol) stockswareDao.get(Productcontrol.class, Long.parseLong(checkpojo.getProductcontrolid()));
                                checkpojo1.setTourdate(checkpojo.getTourdate());
                                checkpojo1.setIvenueid(control.getIvenueid());
                                checkpojo1.setIvenueareaid(control.getIvenueareaid());
                                checkpojo1.setTripid(control.getTripid());
                                checkpojo1.setNumb(Long.parseLong(checkpojo.getNumb()));
                                checkpojo1.setIticketid(Long.parseLong(checkpojo.getItickettypeid()));
                                // checkpojo1.setErrtp(errtp);
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
                list = ecService.getNumberControllData(checkpojo1.getIticketid().toString(), checkpojo1.getTourdate(), "02");
                if (list != null && list.size() > 0) {
                    control = new Productcontrol();
                    BeanUtils.populate(control, (Map) list.get(0));
                    if (control.getSalablenumber() - control.getSoldnumber() > checkpojo1.getNumb()) {
                        olddaylist.remove(checkpojo1);
                        i = i - 1;
                    } else {
                        checkticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, checkpojo1.getIticketid());
                        checktrip = (Trip) stockswareDao.get(Trip.class, checkpojo1.getTripid());
                        checkpojo1.setErrtp("2");// 已售完
                        checkpojo1.setTicketname(checkticket.getSztickettypename());
                        checkpojo1.setTripname(checktrip.getTripname());
                    }
                } else {
                    checkticket = (Edmtickettypetab) stockswareDao.get(Edmtickettypetab.class, checkpojo1.getIticketid());
                    checktrip = (Trip) stockswareDao.get(Trip.class, checkpojo1.getTripid());
                    checkpojo1.setErrtp("3");// 暂时不可销售
                    checkpojo1.setTicketname(checkticket.getSztickettypename());
                    if (checktrip != null) {
                        checkpojo1.setTripname(checktrip.getTripname());
                    }
                }
            }
        }

        return olddaylist;
    }

    //	public void setTicketStockInfoDao(ITicketStockInfoDao ticketStockInfoDao) {
    //		this.ticketStockInfoDao = ticketStockInfoDao;
    //	}

    /**
     *
     * Describe:根据订单ID获得TOrder
     * @author:zhangwubin
     * @param orid
     * @return
     * return:TOrder
     * Date:2014-4-8
     */
    public TOrder getTOrderByOrid(String orid){
        List<TOrder> list =  stockswareDao.find("from TOrder t where t.id.orid=?", new String[]{orid});
        return(list == null || list.size()==0)?null:list.get(0);
    }
    /**
     * Describe:修改游览日期，畅游通可以修改enddate
     * @author liujianwen
     * @param editlevel 编辑级别 2:严格验证 1:不验证业务(畅游通旅行社可订散客业务产品) 0:不验证业务且不验证有效期(垃圾淘宝订单)
     * @param orid
     * @param stdt
     * @param iscenicid
     * return:void
     * Date:2014-5-23
     */
    public void updateProductDate(int editlevel, String orid, String stdt, String endDate, String iscenicid,String ibusinessid) {
        boolean yz = false;
        yz = ecService.validationEditScheme(orid, stdt, iscenicid, ibusinessid);
        if(yz && editlevel == 0)
            yz = ecService.validationEditDate(orid, stdt, Tools.getDate(stdt, 1), iscenicid);
        if(yz && editlevel == 1){
            int validdays =  ecService.getMaxCanEditDate(orid);
            yz = validdays > 0 &&  ecService.validationEditDate(orid, stdt, Tools.getDate(stdt, validdays - 1), iscenicid);
        }
        if(yz && editlevel == 2)
            yz = ecService.validationEditDatePrice(orid, stdt, iscenicid, ibusinessid);
        if(yz){
            System.out.println("yz进入了这里");
            List torders = ecService.getTOrderlistByOrid(orid);
            String[] dateArr = new String[torders.size()];
            for (int i = 0; i < torders.size(); i++) {
                Map map = (Map) torders.get(i);
                dateArr[i] = map.get("dtstartdate").toString();
            }
            dateArr = getStartAndEndDate(dateArr);
            MOrder morder = (MOrder) stockswareDao.get(MOrder.class, orid);
            morder.setStdt(stdt);
            stockswareDao.update(morder);
            TOrder torder = (TOrder) stockswareDao.get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
            torder.setDtstartdate(stdt);
            torder.setDtenddate(endDate);
            stockswareDao.update(torder);
            //获取所有排除竹筏的torderlist和tzorderlist
            List torderlists = ecService.getTOrderlists(orid, Long.parseLong(iscenicid));
            List tzorderlists = null;
            TOrderlist torderlist = null;
            TZorderlist zorderlist = null;
            Edmtickettypetab ticket = null;
            Object[] obj = null;
            if (torderlists != null) {
                List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
                for (int i = 0; i < torderlists.size(); i++) {
                    torderlist = (TOrderlist) torderlists.get(i);

                    ticket = (Edmtickettypetab) get(Edmtickettypetab.class, torderlist.getItickettypeid());
                    torderlist.setDtstartdate(stdt);
                    if(editlevel == 0){
                        torderlist.setDtenddate(endDate);
                    }else{
                        torderlist.setDtenddate(Tools.getDate(stdt, Integer.parseInt(ticket.getValidityday().toString()) - 1));
                    }
                    tzorderlists = ecService.getTZorderlists(orid, torderlist.getId());
                    stockswareDao.update(torderlist);
                    for (int j = 0; j < tzorderlists.size(); j++) {
                        obj = (Object[]) tzorderlists.get(j);
                        zorderlist = (TZorderlist) obj[0];
                        ticket = (Edmtickettypetab) get(Edmtickettypetab.class, zorderlist.getIztickettypeid());
                        zorderlist.setDtstartdate(stdt + " " + "00:00:00");
                        if(editlevel == 0){
                            zorderlist.setDtenddate(endDate + " " + "23:59:59");
                        }else{
                            zorderlist.setDtenddate(Tools.getDate(stdt, Integer.parseInt(ticket.getValidityday().toString()) - 1) + " " + "23:59:59");
                        }
                        stockswareDao.update(zorderlist);
                    }
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(torderlist.getId().getOrid());
                    stockOrderInfo.setProviderId(torderlist.getId().getIscenicid());
                    stockOrderInfo.setProductId(torderlist.getItickettypeid());
                    stockOrderInfo.setPriceId(torderlist.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(morder.getUsid());
                    stockOrderInfo.setStockDate(torderlist.getDtstartdate());
                    stockOrderInfo.setNumb(torderlist.getNumb());
                    stocks.add(stockOrderInfo);
                }
                IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
                String message = stockService.checkCustomStock(stocks);
                if(StringUtils.isBlank(message)){
                    message = stockService.checkStock(stocks);
                }
                if(!StringUtils.isBlank(message)){
                    throw new RuntimeException(message);
                }
                stockService.deleteStockDetails(orid,Long.parseLong(iscenicid),null,null);
                try{
                    stockService.saveStockDetails(stocks,false);
                }catch(Exception e){
                    throw new RuntimeException("更新库存失败，无法修改浏览日期");
                }
            }
        }else{
            throw new RuntimeException();
        }
    }

    /**
     *
     * Describe:查找排班信息
     * @author:chenxinhao
     * @param iprogramid	节目ID
     * @param ivenueid	场馆ID
     * @param date	日期
     * @return
     * return:Tripprdcontroltab
     * Date:2015-7-21
     */
    public Tripprdcontroltab getTripprdcontroltab(Long iprogramid,Long ivenueid,String date){
        List list = this.find(" from Tripprdcontroltab t where t.iprogramid="+iprogramid+" and t.ivenueid="+ivenueid+" and t.startdata<='"+date+"' and t.enddata>='"+date+"' ");
        if(list!=null && !list.isEmpty()){
            return (Tripprdcontroltab) list.get(0);
        }
        return null;
    }

    /**
     *
     * Describe:查找排班明细信息
     * @author:chenxinhao
     * @param itripprdcontrolid	排班ID
     * @param tripid	场次ID
     * @return
     * return:Tripprdcontroldetailtab
     * Date:2015-7-21
     */
    public Tripprdcontroldetailtab getTripprdcontroldetailtab(Long itripprdcontrolid,Long tripid){
        List list = this.find("from Tripprdcontroldetailtab t where t.itripprdcontrolid="+itripprdcontrolid+" and t.itripid="+tripid);
        if(list!=null && !list.isEmpty()){
            return (Tripprdcontroldetailtab) list.get(0);
        }
        return null;
    }

    /**
     *
     * Describe:查找产品节目绑定信息
     * @author:chenxinhao
     * @param iprogramid	节目ID
     * @param itickettypeid	产品ID
     * @return
     * return:Programprdmanager
     * Date:2015-7-21
     */
    public Programprdmanager getProgramprdmanager(Long iprogramid,Long itickettypeid){
        List list = this.find("from Programprdmanager p where p.iprogramid="+iprogramid+" and p.itickettypeid="+itickettypeid);
        if(list!=null && !list.isEmpty()){
            return (Programprdmanager) list.get(0);
        }
        return null;
    }

    /**
     *
     * Describe:查看排班产品区域关联数据
     * @author:chenxinhao
     * @param itripprdcontrolid	排班ID
     * @param iproductid	产品ID
     * @param ivenueareaid	区域ID
     * @return
     * return:Tripprdsaletab
     * Date:2015-7-21
     */
    public Tripprdsaletab getTripprdsaletab(Long itripprdcontrolid,Long iproductid,Long ivenueareaid){
        List list = this.find("from Tripprdsaletab t where t.itripprdcontrolid="+itripprdcontrolid+" and t.iproductid="+iproductid+" and t.ivenueareaid="+ivenueareaid);
        if(list!=null && !list.isEmpty()){
            return (Tripprdsaletab) list.get(0);
        }
        return null;
    }

    public Map saveeditseat(Custom com, MOrder mor, TOrder torder,
                            List torderlistlist, String neworid,Double tpsxmonth) throws Exception {
        Map returnmap = new HashMap();
        double tpmont = 0;// 退款金额
        double tpyhmont = 0;
        double sxf = 0;// 手续费
        List yorderlistlist = new ArrayList();
        for (int i = 0; i < torderlistlist.size(); i++) {
            TOrderlist t = (TOrderlist) torderlistlist.get(i);
            List list = stockswareDao.findTopNumb("from Seatordertab where id.orid='"+t.getId().getOrid()+"' " +
                    " and id.iscenicid="+t.getId().getIscenicid()+" " +
                    " and id.orderlistid="+t.getId().getOrderlistid()+" order by iseatid desc", t.getNnumb().intValue());
            t.setSlist(list);
            t.setTdyhnumb(0L);
            t.setNumb(t.getNumb() - t.getNnumb());
            t.setAmnt(t.getNumb() * t.getPric());
            t.setYhnumb(t.getYhnumb() - t.getTdyhnumb());
            t.setYhamnt(t.getYhnumb() * t.getPric());
            YOrderlist y = new YOrderlist();
            YOrderlistId yid = new YOrderlistId();

            yid.setOrid(neworid);
            yid.setIscenicid(t.getId().getIscenicid());
            yid.setOrderlistid(t.getId().getOrderlistid());
            y.setId(yid);
            y.setItickettypeid(t.getItickettypeid());
            y.setIcrowdkindid(t.getIcrowdkindid());
            y.setIcrowdkindpriceid(t.getIcrowdkindpriceid());
            y.setDtstartdate(t.getDtstartdate());
            y.setDtenddate(t.getDtenddate());
            y.setNumb(t.getNnumb());
            y.setPric(t.getPric());
            y.setAmnt(t.getNnumb() * t.getPric());
            y.setYhnumb(t.getTdyhnumb());
            y.setYhamnt(t.getTdyhnumb() * t.getPric());
            y.setIoffersschemeid(t.getIoffersschemeid());
            y.setMhandcharge(0D);
            List<Seatordertab> seatlist = t.getSlist();
            y.setSlist(seatlist);

            List tzlistlist = stockswareDao
                    .find(" from TZorderlist  where id.orid='"
                            + t.getId().getOrid() + "' and id.orderlistid="
                            + t.getId().getOrderlistid()
                            + " and id.iscenicid= " + t.getId().getIscenicid());
            List yzlistlist = new ArrayList();
            for (int j = 0; j < tzlistlist.size(); j++) {
                TZorderlist tz = (TZorderlist) tzlistlist.get(j);
                tz.setZnumb(t.getNumb());
                tz.setZamnt(tz.getZnumb() * tz.getZpric());
                tz.setZyhnumb(t.getYhnumb());
                tz.setZyhamnt(tz.getZyhnumb() * tz.getZpric());
                YZorderlist yz = new YZorderlist();
                YZorderlistId yzid = new YZorderlistId();
                yzid.setOrid(neworid);
                yzid.setOrderlistid(tz.getId().getOrderlistid());
                yzid.setIscenicid(tz.getId().getIscenicid());
                yzid.setZorderlistid(tz.getId().getZorderlistid());
                yz.setId(yzid);
                yz.setIcrowdkindid(tz.getIcrowdkindid());
                yz.setIcrowdkindpriceid(tz.getIcrowdkindpriceid());
                yz.setDtstartdate(tz.getDtstartdate());
                yz.setDtenddate(tz.getDtenddate());
                yz.setItickettypeid(tz.getItickettypeid());
                yz.setIztickettypeid(tz.getIztickettypeid());
                yz.setIvenueid(tz.getIvenueid());
                yz.setIvenueareaid(tz.getIvenueareaid());
                yz.setIvenueseatsid(tz.getIvenueseatsid());
                yz.setTripid(tz.getTripid());
                yz.setZnumb(y.getNumb());
                yz.setZpric(tz.getZpric());
                yz.setZamnt(yz.getZnumb() * tz.getZpric());
                yz.setZyhnumb(y.getYhnumb());
                yz.setZyhamnt(y.getYhnumb() * tz.getZpric());
                yz.setIse(tz.getIse());
                yz = this.getsxfYZorderlist(yz, com.getUsid());

                tpmont = tpmont + yz.getZamnt();
                tpyhmont = tpyhmont + yz.getZyhamnt();
                sxf = sxf + yz.getMhandcharge();
                y.setMhandcharge(y.getMhandcharge() + yz.getMhandcharge());
                // 计算手续费
                yzlistlist.add(yz);
            }
            t.setZorderlist(tzlistlist);
            y.setYzorderlistlist(yzlistlist);
            yorderlistlist.add(y);
            // 计算积分返回
        }

        if(tpsxmonth.doubleValue()!=sxf){
            returnmap.put("result", false);
            returnmap.put("msg", "剧场退订手续费错误，退订失败");
        }

        // 更新原订单
        MOrder om = (MOrder) stockswareDao.get(MOrder.class, mor.getOrid());

        for (int i = 0; i < torderlistlist.size(); i++) {
            TOrderlist t = (TOrderlist) torderlistlist.get(i);
            List tzlistlist = t.getZorderlist();
            for (int j = 0; j < tzlistlist.size(); j++) {
                TZorderlist tz = (TZorderlist) tzlistlist.get(j);
                if(tz.getZnumb()==0L){
                	stockswareDao.delete(tz);
                }else{
                	stockswareDao.update(tz);
                }
            }
            if(t.getNumb()==0L){
            	stockswareDao.delete(t);
            }else{
            	stockswareDao.update(t);
            }
            List seatlist = t.getSlist();
            for (int j = 0; j < seatlist.size(); j++) {
                Seatordertab s = (Seatordertab) seatlist.get(j);
                stockswareDao.delete(s);

                SeatstatustabId sttid = new SeatstatustabId();
                sttid.setIseatid(s.getIseatid());
                sttid.setItripid(s.getItripid());
                sttid.setIvenueid(s.getIvenueid());
                sttid.setIvenueareaid(s.getIvenueareaid());
                sttid.setStartdate(s.getStartdate().substring(0, 10));
                Seatstatustab stt = (Seatstatustab) stockswareDao.get(
                        Seatstatustab.class, sttid);
                stockswareDao.delete(stt);
            }

        }
        if (om.getTpmont() == null) {
            om.setTpmont(0D);
        }
        if (om.getTpsx() == null) {
            om.setTpsx(0D);
        }
        if (om.getIsi() == null) {
            om.setIsi(0l);
        }
        if (om.getIsh() == null) {
            om.setIsh(0l);
        }
        om.setIsi(om.getIsi());
        om.setIsh(om.getIsh());
        if(om.getTpmont()!=null && !"".equals(om.getTpmont())){
            om.setTpmont(tpmont + om.getTpmont());
        }else{
            om.setTpmont(tpmont);
        }
        if(om.getTpsx()!=null && !"".equals(om.getTpsx())){
            om.setTpsx(om.getTpsx() + sxf);
        }else{
            om.setTpsx(sxf);
        }


        TOrder ot = (TOrder) stockswareDao.get(TOrder.class, torder.getId());
        YOrder yt = (YOrder) stockswareDao.get(YOrder.class, new YOrderId(torder.getId().getOrid(), torder.getId().getIscenicid()));
        ot.setMont(ot.getMont()-tpmont);
        ot.setZfmont(ot.getZfmont()-tpmont);
        ot.setYhamnt(ot.getYhamnt()-tpyhmont);
        if(yt.getTpmont()!=null && !"".equals(yt.getTpmont())){
            yt.setTpmont(tpmont+yt.getTpmont());
        }else{
            yt.setTpmont(tpmont);
        }
        if(yt.getTpsx()!=null && !"".equals(yt.getTpsx())){
            yt.setTpsx(sxf+yt.getTpsx());
        }else{
            yt.setTpsx(sxf);
        }
        if (ot.getIsi() == null) {
            ot.setIsi(0l);
        }
        if (ot.getIsh() == null) {
            ot.setIsh(0l);
        }
        ot.setIsi(om.getIsi());
        ot.setIsh(om.getIsh());

        int numb = 0;
        List oldTorderlist = this.getTOrderList(torder.getId().getOrid(), torder.getId().getIscenicid());
        if(oldTorderlist!=null &&!oldTorderlist.isEmpty()){
            for(int j=0;j<oldTorderlist.size();j++){
                TOrderlist tls = (TOrderlist) oldTorderlist.get(j);
                numb+=tls.getNumb().intValue();
            }
        }

        if (numb == 0) {
            ot.setDdzt("11");
            yt.setDdzt("11");
        }
        stockswareDao.update(ot);
        stockswareDao.update(yt);
        if (numb == 0) {
            om.setDdzt("11");
        }
        stockswareDao.update(om);
        // 新增退订单
        MOrder newm = new MOrder();
        newm.setOrid(neworid);
        newm.setOrtp("02");// 订单类型 03：增量订单
        newm.setUsid(mor.getUsid());
        newm.setOrda(Tools.getDays());
        newm.setOrti(Tools.getNowString().substring(11,
                Tools.getNowString().length()));
        newm.setIsallcp(0l);// 是否全部出票
        newm.setZffs("06");// 支付方式
        newm.setBank("92");// 支付银行
        newm.setZfusid(mor.getZfusid());// 订单支付人
        newm.setPayorid("");// 支付订单号
        newm.setBankdata(Tools.getDays());// 支付日期
        newm.setBanktime(Tools.getNowTimeString());// 支付时间
        newm.setSrid(mor.getOrid());// 对应原订单号
        if (mor.getIsjl() != null && mor.getIsjl().intValue() == 1) {
            newm.setIsjl(1l);
        } else {
            newm.setIsjl(0l);
        }
        newm.setTpfs("02");
        newm.setYhamnt(tpyhmont);// 优惠金额
        newm.setMont(tpmont);
        newm.setZfmont(tpmont - tpyhmont);// 支付的金额
        stockswareDao.save(newm);
        YOrder yorder = new YOrder();
        yorder.setId(new YOrderId(newm.getOrid(), torder.getId().getIscenicid()));
        // 根据编号找服务商类型
        Esbscenicareatab esb = (Esbscenicareatab) this.get(
                Esbscenicareatab.class, torder.getId().getIscenicid());
        yorder.setScenictype(esb.getScenictype());// 服务商类别 01 景区 06酒店 07 旅行社
        // 08 超市 09 租赁公司
        yorder.setUsid(newm.getUsid());// 游客编号
        yorder.setIbusinessid(com.getIbusinessid());// 业务id
        yorder.setSztravelbillno("");// 行程单号
        yorder.setIregionalid(0l);// 客源地id
        yorder.setTdlx("");// 团队性质
        yorder.setDtstartdate(torder.getDtstartdate());// 游览开始日期
        yorder.setDtenddate(torder.getDtenddate());// 游览结束日期
        yorder.setDyusid(torder.getDyusid());// 导游id
        yorder.setOrnm(torder.getOrnm());// 领票人姓名
        yorder.setOrzj(torder.getOrzj());// 领票人证件类别
        yorder.setOrhm(torder.getOrhm());// 领票人证件号码
        yorder.setFaxno(torder.getFaxno());// 传真号
        yorder.setOrph(torder.getOrph());// 领票人电话
        yorder.setMont(tpmont);
        yorder.setYhamnt(0.0);// 优惠金额
        yorder.setZfmont(tpmont - tpyhmont);// 支付的金额
        yorder.setYoufei(tpyhmont);// 邮费
        yorder.setTpfs("02");
        yorder.setTpmont(tpmont - tpyhmont);
        yorder.setTpsx(sxf);
        stockswareDao.save(yorder);
        for (int i = 0; i < yorderlistlist.size(); i++) {
            YOrderlist y = (YOrderlist) yorderlistlist.get(i);
            stockswareDao.save(y);
            List ylistlist = y.getYzorderlistlist();
            for (int j = 0; j < ylistlist.size(); j++) {
                YZorderlist yz = (YZorderlist) ylistlist.get(j);
                stockswareDao.save(yz);
            }
            List slist = y.getSlist();
            if (slist != null && slist.size() > 0) {
                for (int j = 0; j < slist.size(); j++) {
                    Seatordertab s = (Seatordertab) slist.get(j);
                    Seatyordertab sy = new Seatyordertab();
                    SeatyordertabId syid = new SeatyordertabId();
                    syid.setIscenicid(s.getId().getIscenicid());
                    syid.setOrderlistid(s.getId().getOrderlistid());
                    syid.setOrid(neworid);
                    syid.setZorderlistid(s.getId().getZorderlistid());
                    syid.setSeq(s.getId().getSeq());
                    sy.setId(syid);
                    sy.setIprogramid(s.getIprogramid());
                    sy.setItripid(s.getItripid());
                    sy.setItripprdcontrolid(s.getItripprdcontrolid());
                    sy.setIvenueid(s.getIvenueid());
                    sy.setIvenueareaid(s.getIvenueareaid());
                    sy.setStartdate(s.getStartdate());
                    sy.setDtmakedate(Tools.getDayTime());
                    sy.setIseatid(s.getIseatid());
                    sy.setIsvalid(1L);
                    stockswareDao.save(sy);
                }
            }
        }

        returnmap.put("result", true);
        return returnmap;
    }

    public YZorderlist getsxfYZorderlist(YZorderlist oldtdlist, String usid)
            throws ParseException {
        Custom custom = (Custom) stockswareDao.get(Custom.class, usid);
        Changebackrate fl = null;
        boolean flag = true;
        Tripprdcontroldetailtab tdcd = null;
        if (oldtdlist.getTripid() > 0 && oldtdlist.getIvenueseatsid() > 0) {
            // 场馆票
            Long itripprdcontrolid = oldtdlist.getIse();
            Tripprdcontroltab tdc = (Tripprdcontroltab) stockswareDao.get(
                    Tripprdcontroltab.class, itripprdcontrolid);
            if (tdc.getByisuse() == 0) {
                flag = false;
            } else {
                List tdcdlist = stockswareDao
                        .find(" from Tripprdcontroldetailtab where itripprdcontrolid="
                                + tdc.getItripprdcontrolid()
                                + " and itripid="
                                + oldtdlist.getTripid());
                tdcd = (Tripprdcontroldetailtab) tdcdlist.get(0);
                if (tdcd.getByisuse() == 0) {
                    flag = false;
                }
            }
        }
        double tdfl = 0.0;
        if (flag) {// 没有竹筏控制或者竹筏状态正常时
            /*Ticketxgz tdrule = morderdao.ticketbackrule(oldtdlist
                            .getIztickettypeid(), oldtdlist.getId().getIscenicid(),
                    custom.getLgtp());*/
            Ticketxgz tdrule = JSON.parseObject(ecService.ticketbackrule(oldtdlist
            		.getIztickettypeid(), oldtdlist.getId().getIscenicid(),
            		custom.getLgtp()), Ticketxgz.class);

            if (tdrule != null) {// 如果退订规则不为空
                if (tdrule.getJsfs().equals("0001")) {// 按小时

                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = null;
                    Date d2 = null;
                    if (tdcd != null) {// 如果是竹筏则用诸法的日期 非竹筏用票的结束日期
                        d1 = df.parse(oldtdlist.getDtstartdate().substring(0,
                                10)
                                + " " + tdcd.getStarttime() + ":00");
                        d2 = df.parse(Tools.getNowString());
                    } else {
                        d1 = df.parse(oldtdlist.getDtenddate());
                        d2 = df.parse(Tools.getNowString());
                    }
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        long diff = d1.getTime() - d2.getTime();
                        long hours = diff / (1000 * 60 * 60);
                        /*fl = morderdao.getflByTime(tdrule.getGzid(),
                                String.valueOf(hours), "0001");*/
                        fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(),
                                String.valueOf(hours), "0001"), Changebackrate.class);
                        if (fl == null) {
                            tdfl = 0.0;
                        } else {
                            tdfl = fl.getTdfl();
                        }
                    }
                } else if (tdrule.getJsfs().equals("0002")) {// 按天
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = null;
                    Date d2 = null;
                    if (tdcd != null) {
                        d1 = df.parse(oldtdlist.getDtstartdate().substring(0,
                                10)
                                + " " + tdcd.getStarttime() + ":00");
                        d2 = df.parse(Tools.getNowString());
                    } else {
                        d1 = df.parse(oldtdlist.getDtenddate());
                        d2 = df.parse(Tools.getNowString());
                    }
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        long diff = d1.getTime() - d2.getTime();
                        long days = diff / (1000 * 60 * 60 * 24);
                        /*fl = morderdao.getflByTime(tdrule.getGzid(),
                                String.valueOf(days), "0002");*/
                        fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(),
                                String.valueOf(days), "0002"), Changebackrate.class);
                        if (fl == null) {
                            tdfl = 0.0;
                        } else {
                            tdfl = fl.getTdfl();
                        }
                    }
                } else {// 常年
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date d1 = null;
                    Date d2 = null;
                    if (tdcd != null) {// 如果是竹筏则用诸法的日期 非竹筏用票的结束日期
                        d1 = df.parse(oldtdlist.getDtstartdate().substring(0,
                                10)
                                + " " + tdcd.getStarttime() + ":00");
                        d2 = df.parse(Tools.getNowString());
                    } else {
                        d1 = df.parse(oldtdlist.getDtenddate());
                        d2 = df.parse(Tools.getNowString());
                    }
                    if (d1.before(d2)) {
                        tdfl = tdrule.getXyrate2();
                    } else {
                        /*fl = morderdao
                                .getflByTime(tdrule.getGzid(), "", "0003");*/
                    	fl = JSON.parseObject(ecService.getflByTime(tdrule.getGzid(), "", "0003"), Changebackrate.class);
                        tdfl = fl.getTdfl();
                    }
                }

            }
        }
        oldtdlist.setTdfl(tdfl);
        oldtdlist
                .setMhandcharge((oldtdlist.getZnumb() - oldtdlist.getZyhnumb())
                        * oldtdlist.getZpric() * tdfl);
        return oldtdlist;
    }

    public Map<String,Object> findCreateOrderData(String date){
        String hsql = "select new map(nvl(sum(tl.numb),0) as quantity,nvl(sum(tl.amnt),0) as money) " +
                "from YOrderlist tl,MOrder m where tl.id.orid = m.orid and substr(tl.id.orid,9,3) in ('888','999') " +
                "and m.orda = '"+date+"' ";
        List list = stockswareDao.find(hsql);
        if(list != null && !list.isEmpty()){
            return (Map<String, Object>) list.get(0);
        }
        return null;
    }

    public Map<String,Object> findConsumeOrderData(String date){
        String hsql = "select new map(nvl(sum(tl.numb),0) as quantity,nvl(sum(tl.amnt),0) as money) " +
                "from TOrderlist tl,TOrder t where tl.id.orid = t.id.orid and substr(tl.id.orid,9,3) in ('888','999') " +
                "and substr(t.notec,1,10) = '"+date+"' ";
        List list = stockswareDao.find(hsql);
        if(list != null && !list.isEmpty()){
            return (Map<String, Object>) list.get(0);
        }
        return null;
    }

    public Map<String,Object> findRefundOrderData(String date){
        String hsql = "select new map(nvl(sum(tl.numb),0) as quantity,nvl(sum(tl.amnt),0) as money) " +
                "from YOrderlist tl,MOrder m where tl.id.orid = m.orid and substr(m.srid,9,3) in ('888','999') " +
                "and m.orda = '"+date+"' ";
        List list = stockswareDao.find(hsql);
        if(list != null && !list.isEmpty()){
            return (Map<String, Object>) list.get(0);
        }
        return null;
    }
}

