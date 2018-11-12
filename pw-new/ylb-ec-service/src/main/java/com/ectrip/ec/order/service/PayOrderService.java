package com.ectrip.ec.order.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.common.TwoBarUtil;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.Payback;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.Seatyordertab;
import com.ectrip.ec.model.order.SeatyordertabId;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.ticket.TicketPojo;
import com.ectrip.ec.model.user.Credit;
import com.ectrip.ec.model.user.Creditdetail;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ec.model.usernumjf.Usernumjflist;
import com.ectrip.ec.model.usernumjf.UsernumjflistId;
import com.ectrip.ec.order.dao.idao.IPayOrderDAO;
import com.ectrip.ec.order.service.iservice.IPayOrderService;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.stock.Stockdetailtab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Seatstatustab;
import com.ectrip.ticket.model.venuemarketing.SeatstatustabId;

@Service
public class PayOrderService extends GenericService implements IPayOrderService {

    private static SimpleDateFormat df = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private IPayOrderDAO payorderDao;
    private ITicketDAO ticketDao;
    
    @Autowired
    private SysparService sysparService;
//    private IBankDAO bankDao;
    
    private IBalanceCenterDAO balancecenterDao;
    
    @Autowired
    private TicketService ticketService;
//    private IStatisicsDAO ticketService;

//    private IPullSeatService pullseatService;
//    private IStockDao stockDao;

    private List mergeDayRaft(List tzorderlists) throws IllegalAccessException,
            InvocationTargetException {
        List afterMerge = new ArrayList();
        for (int i = 0; i < tzorderlists.size(); i++) {
            TZorderlist z = (TZorderlist) tzorderlists.get(i);
            if (afterMerge.size() < 1) {
                TZorderlist z1 = new TZorderlist();
                BeanUtils.copyProperties(z1, z);
                afterMerge.add(z1);
            } else {
                for (int j = 0; j < afterMerge.size(); j++) {
                    TZorderlist z1 = (TZorderlist) afterMerge.get(j);
                    if (z1.getDtstartdate().substring(0, 10)
                            .equals(z.getDtstartdate().subSequence(0, 10))
                            && z1.getItickettypeid().intValue() == z
                            .getItickettypeid().intValue()) {
                        z1.setZnumb(z1.getZnumb().intValue() + z.getZnumb());
                        break;
                    } else {
                        if (j == afterMerge.size() - 1) {
                            TZorderlist newzlist = new TZorderlist();
                            BeanUtils.copyProperties(newzlist, z);
                            afterMerge.add(newzlist);
                            break;
                        }
                    }
                }
            }
        }
        return afterMerge;
    }

    /**
     *
     * Describe:
     *
     * @auth:yangguang
     * @param usid
     * @param orid
     * @return -2:shujuyichang 0:zhengchang -1:shuliangyuejie return:int
     *         Date:2012-4-18
     */
    public int volidationCredit(String usid, String orid) {
        Custom custom = null;
        Custom pcustom = null;
        double credit = 0.0;
        String fenshe = "";
        while (true) {
            custom = (Custom) payorderDao.get(Custom.class, usid);
            if (custom.getSusid() == null || custom.getSusid().equals("")
                    || custom.getUsid().equals(custom.getSusid())) {
                return -2;
            } else {
                pcustom = (Custom) payorderDao.get(Custom.class,
                        custom.getSusid());
                if (custom.getLgtp().equals("02")
                        && custom.getUstp().equals("02")
                        && pcustom.getUstp().equals("01")) {
                    fenshe = custom.getUsid();
                    break;
                } else {
                    usid = pcustom.getUsid();
                    continue;
                }
            }
        }
        boolean isover = true;
        List tzlist = payorderDao.find(" from TZorderlist where id.orid='"
                + orid + "'");
        for (int i = 0; i < tzlist.size(); i++) {
            TZorderlist tz = (TZorderlist) tzlist.get(i);
            if (tz.getTripid().intValue() > 0) {
                List pvlist = payorderDao
                        .find(" from Prdtripvenuemanage where itickettypeid="
                                + tz.getIztickettypeid() + " and tripid="
                                + tz.getTripid() + " and startdata<='"
                                + tz.getDtstartdate().substring(0, 10)
                                + "' and enddata>='"
                                + tz.getDtstartdate().substring(0, 10) + "'");
                if(pvlist!=null && !pvlist.isEmpty()){
                    Prdtripvenuemanage pv = (Prdtripvenuemanage) pvlist.get(0);
                    credit += pv.getIshot() * tz.getZnumb();
                    if (pv.getIshot() > 0) {
                        if (isover) {
                            isover = false;
                        }
                    }
                }
            }
        }
        Credit c = (Credit) payorderDao.get(Credit.class, fenshe);
        Sysparv5 overnumer = (Sysparv5) payorderDao.get(Sysparv5.class,
                new Sysparv5Id("YDJF", "02"));
        if (!isover) {
            if (c.getCreditnumb() + credit > Double.parseDouble(overnumer
                    .getPmvb()) && !isover) {
                return -1;
            }
        }
        return 1;
    }

    public List orderJifen(String orid, String zfusid) {
        Custom zfuser = (Custom) payorderDao.get(Custom.class, zfusid);
        List list = payorderDao.getTorder(orid);
        List jifenlist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            TOrder torder = (TOrder) list.get(i);
            // 获取游览日期当月月初和月末时间
            Map map = DateUtils.getMonthDate(
                    Integer.parseInt(torder.getDtstartdate().substring(0, 4)),
                    Integer.parseInt(torder.getDtstartdate().substring(5, 7)));
            Esbscenicareatab provider = (Esbscenicareatab) payorderDao.get(
                    Esbscenicareatab.class, torder.getId().getIscenicid());
            // 获取服务商积分消费规则
            Numjifenset set = ticketService.getNumjifenset(torder.getId()
                    .getIscenicid().toString());
            // 查询产品列表
            List orderlist = payorderDao.getTorderlistByTorder(torder);
            // 用户月积分
            String userjfmonthJSON = ticketService.getJifenByUser(
                    map.get("startDate").toString(), map.get("endDate")
                            .toString(), zfusid, new Long(1), torder.getId()
                            .getIscenicid());
            Usernumjf userjfmonth = JSON.parseObject(userjfmonthJSON, Usernumjf.class);
            // 用户年积分
            String userjfyearJSON = ticketService.getJifenByUser(torder
                            .getDtstartdate().substring(0, 4) + "-01-01", torder
                            .getDtstartdate().substring(0, 4) + "-12-31", zfusid,
                    new Long(2), torder.getId().getIscenicid());
            Usernumjf userjfyear = JSON.parseObject(userjfyearJSON, Usernumjf.class);
            long jfmonth = 0;
            long jfyear = 0;
            for (int j = 0; j < orderlist.size(); j++) {
                TOrderlist ticket = (TOrderlist) orderlist.get(j);
                Numjifensetlist detail = ticketService.getSalesRule(
                        ticket.getItickettypeid(), set.getNid(),
                        ticket.getIcrowdkindid(), zfuser.getIbusinessid(),
                        ticket.getDtstartdate());
                if (detail != null) {
                    if (detail.getXffs().equals("03")) {// 月积分
                        if (ticket.getNumb() % detail.getIint3() > 0) {
                            jfmonth += ticket.getNumb() / detail.getIint3()
                                    * detail.getIint4() * +detail.getIint4();
                        } else {
                            jfmonth += ticket.getNumb() / detail.getIint3()
                                    * detail.getIint4();
                        }
                    } else {
                        if (ticket.getNumb() % detail.getIint3() > 0) {
                            jfyear += ticket.getNumb() / detail.getIint3()
                                    * detail.getIint4() + detail.getIint4();
                        } else {
                            jfyear += ticket.getNumb() / detail.getIint3()
                                    * detail.getIint4();
                        }
                    }
                }
            }
            Usernumjflist jifendetail = null;
            if (jfmonth > 0) {
                jifendetail = new Usernumjflist();
                jifendetail.setIscenicid(new BigDecimal(torder.getId()
                        .getIscenicid()));
                jifendetail.setSalesjifen(String.valueOf(jfmonth));
                jifendetail.setType(1);
                jifendetail.setSurplusjifen(String.valueOf(userjfmonth
                        .getPoint() - userjfmonth.getYpoint()));
                jifendetail.setMonth(torder.getDtstartdate().substring(0, 4));
                jifendetail.setProvider(provider.getSzscenicname());
                jifenlist.add(jifendetail);
            }
            if (jfyear > 0) {
                jifendetail = new Usernumjflist();
                jifendetail.setIscenicid(new BigDecimal(torder.getId()
                        .getIscenicid()));
                jifendetail.setSalesjifen(String.valueOf(jfyear));
                jifendetail.setSurplusjifen(String.valueOf(userjfyear
                        .getPoint() - userjfyear.getYpoint()));
                jifendetail.setType(2);
                jifendetail.setMonth(torder.getDtstartdate().substring(0, 4));
                jifendetail.setProvider(provider.getSzscenicname());
                jifenlist.add(jifendetail);
            }
        }
        return jifenlist;
    }

    public static void main(String[] args) {
    }

    public List validUserjifen(String orid, String zfusid) {
        Custom zfuser = (Custom) payorderDao.get(Custom.class, zfusid);
        List list = payorderDao.getTorder(orid);
        List jifenlist = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            TOrder torder = (TOrder) list.get(i);
            // 获取游览日期当月月初和月末时间
            Map map = DateUtils.getMonthDate(
                    Integer.parseInt(torder.getDtstartdate().substring(0, 4)),
                    Integer.parseInt(torder.getDtstartdate().substring(5, 7)));
            Esbscenicareatab provider = (Esbscenicareatab) payorderDao.get(
                    Esbscenicareatab.class, torder.getId().getIscenicid());
            // 获取服务商积分消费规则
            Numjifenset set = ticketService.getNumjifenset(torder.getId()
                    .getIscenicid().toString());
            // 查询产品列表
            List orderlist = payorderDao.getTorderlistByTorder(torder);
            // 用户月积分
            String userjfmonthJSON = ticketService.getJifenByUser(
                    map.get("startDate").toString(), map.get("endDate")
                            .toString(), zfusid, new Long(1), torder.getId()
                            .getIscenicid());
            Usernumjf userjfmonth = JSON.parseObject(userjfmonthJSON, Usernumjf.class);
            
            
            // 用户年积分
            String userjfyearJSON = ticketService.getJifenByUser(torder
                            .getDtstartdate().substring(0, 4) + "-01-01", torder
                            .getDtstartdate().substring(0, 4) + "-12-31", zfusid,
                    new Long(2), torder.getId().getIscenicid());
            Usernumjf userjfyear = JSON.parseObject(userjfyearJSON, Usernumjf.class);
            
            
            double jfmonth = 0.0;
            double jfyear = 0.0;
            for (int j = 0; j < orderlist.size(); j++) {
                TOrderlist ticket = (TOrderlist) orderlist.get(j);
                Numjifensetlist detail = ticketService.getSalesRule(
                        ticket.getItickettypeid(), set.getNid(),
                        ticket.getIcrowdkindid(), zfuser.getIbusinessid(),
                        ticket.getDtstartdate());
                if (detail.getXffs().equals("03")) {// 月积分
                    if (ticket.getNumb() % detail.getIint3().intValue() > 0) {
                        jfmonth += ticket.getNumb()
                                / detail.getIint3().intValue()
                                + detail.getIint4().intValue();
                    } else {
                        jfmonth += ticket.getNumb()
                                / detail.getIint3().intValue();
                    }
                } else {
                    if (ticket.getNumb() % detail.getIint3().intValue() > 0) {
                        jfyear += ticket.getNumb()
                                / detail.getIint3().intValue()
                                + detail.getIint4().intValue();
                    } else {
                        jfyear += ticket.getNumb()
                                / detail.getIint3().intValue();
                    }
                }
            }
            if (jfmonth > 0) {
                if (userjfmonth == null
                        || userjfmonth.getPoint() - userjfmonth.getYpoint() < jfmonth) {
                    Usernumjflist jifendetail = new Usernumjflist();
                    jifendetail.setIscenicid(new BigDecimal(torder.getId()
                            .getIscenicid()));
                    jifendetail.setSalesjifen(String.valueOf(jfmonth));
                    jifendetail.setType(1);
                    if (userjfmonth != null) {
                        jifendetail.setSurplusjifen(String.valueOf(userjfmonth
                                .getPoint() - userjfmonth.getYpoint()));
                    } else {
                        jifendetail.setSurplusjifen("0");
                    }
                    jifendetail.setMonth(torder.getDtstartdate()
                            .substring(0, 4));
                    jifendetail.setProvider(provider.getSzscenicname());
                    jifenlist.add(jifendetail);
                }
            }
            if (jfyear > 0) {
                if (userjfyear == null
                        || userjfyear.getPoint() - userjfyear.getYpoint() < jfyear) {
                    Usernumjflist jifendetail = new Usernumjflist();
                    jifendetail.setIscenicid(new BigDecimal(torder.getId()
                            .getIscenicid()));
                    jifendetail.setSalesjifen(String.valueOf(jfyear));
                    if (userjfyear != null) {
                        jifendetail.setSurplusjifen(String.valueOf(userjfyear
                                .getPoint() - userjfyear.getYpoint()));
                    } else {
                        jifendetail.setSurplusjifen("0");
                    }
                    jifendetail.setType(2);
                    jifendetail.setMonth(torder.getDtstartdate()
                            .substring(0, 4));
                    jifendetail.setProvider(provider.getSzscenicname());
                    jifenlist.add(jifendetail);
                }
            }
        }
        return jifenlist;
    }

    public void minusUserjifen(String orid) {
        MOrder morder = (MOrder) payorderDao.get(MOrder.class, orid);
        Custom custom = (Custom) payorderDao.get(Custom.class,
                morder.getZfusid());
        List list = payorderDao.getTorder(orid);
        Map map = DateUtils.getMonthDate(
                Integer.parseInt(morder.getStdt().substring(0, 4)),
                Integer.parseInt(morder.getStdt().substring(5, 7)));
        for (int i = 0; i < list.size(); i++) {
            TOrder torder = (TOrder) list.get(i);
            YOrder yorder = (YOrder) payorderDao.get(YOrder.class,
                    new YOrderId(torder.getId().getOrid(), torder.getId()
                            .getIscenicid()));
            Numjifenset set = ticketService.getNumjifenset(torder.getId()
                    .getIscenicid().toString());
            double monthjf = 0;
            double yearjf = 0;
            List orderlist = payorderDao.getTorderlistByTorder(torder);
            for (int j = 0; j < orderlist.size(); j++) {
                TOrderlist ticket = (TOrderlist) orderlist.get(j);
                Numjifensetlist detail = ticketService.getSalesRule(
                        ticket.getItickettypeid(), set.getNid(),
                        ticket.getIcrowdkindid(), custom.getIbusinessid(),
                        ticket.getDtstartdate());
                if (detail.getXffs().equals("03")) {// 月积分
                    if (ticket.getNumb() % detail.getIint3() > 0) {
                        monthjf += ticket.getNumb() / detail.getIint3()
                                * detail.getIint4() + detail.getIint4();
                        ticket.setIsi(ticket.getNumb() / detail.getIint3()
                                * detail.getIint4() + detail.getIint4());
                    } else {
                        monthjf += ticket.getNumb() / detail.getIint3()
                                * detail.getIint4();
                        ticket.setIsi(ticket.getNumb() / detail.getIint3()
                                * detail.getIint4());
                    }
                } else {
                    if (ticket.getNumb() % detail.getIint3() > 0) {
                        yearjf += ticket.getNumb() / detail.getIint3()
                                * detail.getIint4() + detail.getIint4();
                        ticket.setIsh(ticket.getNumb() / detail.getIint3()
                                * detail.getIint4() + detail.getIint4());
                    } else {
                        yearjf += ticket.getNumb() / detail.getIint3()
                                * detail.getIint4();
                        ticket.setIsh(ticket.getNumb() / detail.getIint3()
                                * detail.getIint4());
                    }
                }
                payorderDao.update(ticket);
            }
            List yorderlist = payorderDao.getYorderlistByTorder(torder);
            for (int j = 0; j < yorderlist.size(); j++) {
                YOrderlist yticket = (YOrderlist) yorderlist.get(j);
                Numjifensetlist detail = ticketService.getSalesRule(
                        yticket.getItickettypeid(), set.getNid(),
                        yticket.getIcrowdkindid(), custom.getIbusinessid(),
                        yticket.getDtstartdate());
                if (detail.getXffs().equals("03")) {// 月积分
                    if (yticket.getNumb() % detail.getIint3() > 0) {
                        yticket.setIsi(yticket.getNumb() / detail.getIint3()
                                * detail.getIint4() + detail.getIint4());
                    } else {
                        yticket.setIsi(yticket.getNumb() / detail.getIint3()
                                * detail.getIint4());
                    }
                } else {
                    if (yticket.getNumb() % detail.getIint3() > 0) {
                        yticket.setIsh(yticket.getNumb() / detail.getIint3()
                                * detail.getIint4() + detail.getIint4());
                    } else {
                        yticket.setIsh(yticket.getNumb() / detail.getIint3()
                                * detail.getIint4());
                    }
                }
                payorderDao.update(yticket);
            }
            if (yearjf > 0) {
                String userjfyearJSON = ticketService.getJifenByUser(torder
                        .getDtstartdate().substring(0, 4) + "-01-01", torder
                        .getDtstartdate().substring(0, 4) + "-12-31", morder
                        .getZfusid(), new Long(2), torder.getId()
                        .getIscenicid());
                Usernumjf userjfyear = JSON.parseObject(userjfyearJSON, Usernumjf.class);
                
                
                userjfyear.setYpoint(userjfyear.getYpoint() + yearjf);
                torder.setIsh((long) yearjf);
                yorder.setIsh((long) yearjf);
                Usernumjflist jifenlist = new Usernumjflist();
                jifenlist.setId(new UsernumjflistId(orid, morder.getZfusid()));
                jifenlist.setItickettypeid(BigDecimal.valueOf(0));
                jifenlist.setItickettypeid2(BigDecimal.valueOf(0));
                jifenlist.setNid(BigDecimal.valueOf(set.getNid()));
                jifenlist.setIscenicid(BigDecimal.valueOf(torder.getId()
                        .getIscenicid()));
                jifenlist.setStdt2(Tools.getDays());
                jifenlist.setEtdt(Tools.getDays());
                jifenlist.setJflb(BigDecimal.valueOf(-1));
                jifenlist.setPoint(Double.valueOf(yearjf));
                jifenlist.setStdt(Tools.getDays());
                jifenlist.setZusid(morder.getZfusid());
                jifenlist.setIsvalid(BigDecimal.valueOf(1));
                jifenlist.setJftp("04");
                this.update(userjfyear);
            }
            if (monthjf > 0) {
                String userjfmonthJSON = ticketService.getJifenByUser(
                        map.get("startDate").toString(), map.get("endDate")
                                .toString(), morder.getZfusid(), new Long(1),
                        torder.getId().getIscenicid());
                Usernumjf userjfmonth = JSON.parseObject(userjfmonthJSON, Usernumjf.class);
                
                
                userjfmonth.setYpoint(userjfmonth.getYpoint() + monthjf);
                torder.setIsi((long) monthjf);
                yorder.setIsi((long) monthjf);
                Usernumjflist jifenlist = new Usernumjflist();
                jifenlist.setId(new UsernumjflistId(orid, morder.getZfusid()));
                jifenlist.setItickettypeid(BigDecimal.valueOf(0));
                jifenlist.setItickettypeid2(BigDecimal.valueOf(0));
                jifenlist.setNid(BigDecimal.valueOf(set.getNid()));
                jifenlist.setIscenicid(BigDecimal.valueOf(torder.getId()
                        .getIscenicid()));
                jifenlist.setStdt2(Tools.getDays());
                jifenlist.setEtdt(Tools.getDays());
                jifenlist.setJflb(BigDecimal.valueOf(-1));
                jifenlist.setPoint(Double.valueOf(monthjf));
                jifenlist.setStdt(Tools.getDays());
                jifenlist.setZusid(morder.getZfusid());
                jifenlist.setIsvalid(BigDecimal.valueOf(1));
                jifenlist.setJftp("03");
                this.save(jifenlist);
                this.update(userjfmonth);
            }
            this.update(torder);
            this.update(yorder);
        }
        payorderDao.update(morder);
    }

    /**
     *
     * Describe:减少竹筏生产量 根据订单编号
     *
     * @auth:yangguang
     * @param orid
     * @return return:int -1:无此订单 .-2:已支付. -3:支付金额不符 . 1:正常状态 -5:修改库存失败 支付失败
     *         -6:数据异常,请联系管理员检查用户数据Date:2011-11-15
     * @throws Exception
     */
    public int updateOrderStatus(String orid, String payid, String mont,
                                 String bank, int isok, String ddzt, String orderType, String zffs,
                                 String note, String zfusid) throws Exception {
        try {
            MOrder morder = (MOrder) payorderDao.get(MOrder.class, orid);
            if (morder.getDdzt().equals("02") || morder.getDdzt().equals("23")
                    || morder.getDdzt().equals("11")) {
                return -2;
            }
            if (!note.equals("1")) {
                // 产品 日期 服务商 分组
                List grouptzorderlistByRaftDay = payorderDao
                        .getTZorderlist(orid);
                // 产品 趟次 分组
                List tzorderlist = payorderDao.getTZorderlistByOrid(orid);
                TZorderlist zt = null;
                // 更新库存
                if (tzorderlist != null && tzorderlist.size() > 0) {// 趟次控制
                    for (int i = 0; i < tzorderlist.size(); i++) {
                        zt = new TZorderlist();
                        BeanUtils.populate(zt, (Map) tzorderlist.get(i));
                        payorderDao.updateStock(orid, zt);
                    }
                }
                if (grouptzorderlistByRaftDay != null
                        && grouptzorderlistByRaftDay.size() > 0) {
                    grouptzorderlistByRaftDay = mergeDayRaft(grouptzorderlistByRaftDay);
                    for (int j = 0; j < grouptzorderlistByRaftDay.size(); j++) {// 日控制
                        zt = new TZorderlist();
                        zt = (TZorderlist) grouptzorderlistByRaftDay.get(j);
                        payorderDao.updateDayControl(orid, zt.getDtstartdate()
                                .substring(0, 10), zt.getItickettypeid()
                                .toString(), zt.getId().getIscenicid()
                                .toString(), zt.getZnumb().intValue());
                    }
                }
            }
            //畅游通订单在支付是保存库存数据
            if(orid.matches("^\\d{8}999\\d{6}") || orid.matches("^\\d{8}888\\d{6}")){
                List list = payorderDao.find("from TOrderlist where id.orid = '"+orid+"'");
                if(list != null && !list.isEmpty()){
                    List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
                    for(int i=0;i<list.size();i++){
                        TOrderlist tl = (TOrderlist) list.get(i);
                        StockOrderInfo stockOrderInfo = new StockOrderInfo();
                        stockOrderInfo.setOrid(tl.getId().getOrid());
                        stockOrderInfo.setProviderId(tl.getId().getIscenicid());
                        stockOrderInfo.setProductId(tl.getItickettypeid());
                        stockOrderInfo.setPriceId(tl.getIcrowdkindpriceid());
                        stockOrderInfo.setUsid(morder.getUsid());
                        stockOrderInfo.setStockDate(tl.getDtstartdate());
                        stockOrderInfo.setNumb(tl.getNumb());
                        stocks.add(stockOrderInfo);
                    }
//                    IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
                    try {
                    	ticketService.saveStockDetails(JSON.toJSONString(stocks), true);
                    }catch (Exception e){
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
            sysparService.updateOrderStatus(orid, payid, mont, bank, isok, ddzt,
                    orderType, zffs, zfusid, note);
            if (morder.getIsjl().intValue() == 1) {
                minusUserjifen(orid);// 减少积分
            }
            // 场馆座位 yangguang 添加
            List list = payorderDao.getOrderFilmSeat(orid);
            if (list == null || list.size() < 1) {
                list = JSON.parseArray(sysparService.pullSeat(orid), Seatordertab.class);
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Seatordertab seat = (Seatordertab) list.get(i);
                    seat.setIsvalid(1L);
                    payorderDao.update(seat);
                    Seatyordertab seaty = (Seatyordertab) payorderDao.get(
                            Seatyordertab.class, new SeatyordertabId(seat
                                    .getId().getSeq(), seat.getId().getOrid(),
                                    seat.getId().getIscenicid(), seat.getId()
                                    .getOrderlistid(), seat.getId()
                                    .getZorderlistid()));
                    seaty.setIsvalid(1l);
                    payorderDao.update(seaty);
                    Seatstatustab status = new Seatstatustab();
                    status.setId(new SeatstatustabId(seat.getIvenueid(), seat
                            .getIvenueareaid(), seat.getItripid(), seat
                            .getIseatid(), seat.getStartdate().substring(0, 10)));
                    status.setStatus(1l);
                    status.setDtmakedate(Tools.getDayTimes());
                    payorderDao.save(status);

                }
            }
            // 场馆座位 yangguang 添加
            // 0156 新增订单 0157 修改订单 0158 删除订单
            Orderlog log = new Orderlog();
            log.setLogid(payorderDao.getMaxPk("logid", "Orderlog") + 1);
            log.setOrid(orid);
            log.setStlg("0176");
            log.setBrief("订单" + orid + "支付或预订成功");
            Sysparv5 syspar = (Sysparv5) this.payorderDao.get(Sysparv5.class,
                    new Sysparv5Id("ZFFS", zffs));
            log.setNote("订单" + orid + "支付或预订成功," + "支付方式:" + syspar.getPmva());
            log.setIemployeeid(null);
            log.setUsid(morder.getUsid());
            log.setLogtype(Long.parseLong("0"));
            log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
            payorderDao.save(log);
            // 当时畅游通订单时屏蔽，无需积分处理
            if (!orid.matches("^\\d{8}999\\d{6}")
                    && !orid.matches("^\\d{8}888\\d{6}") && !note.equals("1")) {
                // YDJF 03代表预订黄金增加积分
                Creditdetail creditdetail = new Creditdetail();
                Custom custom = null;
                Custom pcustom = null;
                String usid = morder.getUsid();
                while (true) {
                    custom = (Custom) payorderDao.get(Custom.class, usid);
                    if (custom.getSusid() == null
                            || custom.getSusid().equals("")
                            || custom.getUsid().equals(custom.getSusid())) {
                        throw new RuntimeException("用户数据异常,支付订单异常终止" + usid);
                    } else {
                        pcustom = (Custom) payorderDao.get(Custom.class,
                                custom.getSusid());
                        if (custom.getLgtp().equals("02")
                                && custom.getUstp().equals("02")
                                && pcustom.getUstp().equals("01")) {
                            creditdetail.setUsid(custom.getUsid());
                            // ncreditdetail.setUsid(zfusid);
                            break;
                        } else {
                            usid = pcustom.getUsid();
                            continue;
                        }
                    }
                }
                creditdetail.setZusid(morder.getUsid());
                creditdetail.setCtype("01");
                creditdetail.setOrid(orid);
                creditdetail.setCreditnumb(new Long(0));
                List tzlist = payorderDao
                        .find(" from TZorderlist where id.orid='" + orid + "'");
                for (int i = 0; i < tzlist.size(); i++) {
                    TZorderlist tz = (TZorderlist) tzlist.get(i);
                    if (tz.getTripid().intValue() > 0
                            && (tz.getIvenueseatsid() == null || tz
                            .getIvenueseatsid().intValue() < 1)) {
                        List pvlist = payorderDao
                                .find(" from Prdtripvenuemanage where itickettypeid="
                                        + tz.getIztickettypeid()
                                        + " and tripid="
                                        + tz.getTripid()
                                        + " and startdata<='"
                                        + tz.getDtstartdate().substring(0, 10)
                                        + "' and enddata>='"
                                        + tz.getDtstartdate().substring(0, 10)
                                        + "'");
                        Prdtripvenuemanage pv = (Prdtripvenuemanage) pvlist
                                .get(0);
                        creditdetail.setCreditnumb(creditdetail.getCreditnumb()
                                + pv.getIshot() * tz.getZnumb());
                    }
                }
                creditdetail.setCseq(payorderDao.getMaxPk("cseq",
                        "Creditdetail") + 1);
                payorderDao.save(creditdetail);
                Credit c = (Credit) payorderDao.get(Credit.class,
                        creditdetail.getUsid());
                if (c != null) {
                    c.setCreditnumb(c.getCreditnumb()
                            + creditdetail.getCreditnumb());
                    payorderDao.update(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("支付订单出现异常:" + orid + ","
                    + e.getMessage());
        }
        return 1;
    }

    /**
     * 散客用户更改订单状态 Describe:
     *
     * @auth:huangyuqi
     * @param orid
     * @param payid
     * @param mont
     * @param bank
     * @param isok
     * @param ddzt
     * @param orderType
     * @param zffs
     * @param note
     * @param zfusid
     * @return
     * @throws Exception
     *             return:int Date:2012-2-23
     */
    public int updateOrderByShanKe(String orid, String payid, String mont,
                                   String bank, int isok, String ddzt, String orderType, String zffs,
                                   String note, String zfusid) throws Exception {
        MOrder morder = (MOrder) payorderDao.get(MOrder.class, orid);
        sysparService.updateOrderStatus(orid, payid, mont, bank, isok, ddzt,
                orderType, zffs, zfusid, note);
        if (!note.equals("1")) {
            // 产品 日期 服务商 分组
            List grouptzorderlistByRaftDay = payorderDao.getTZorderlist(orid);
            // 产品 趟次 分组
            List tzorderlist = payorderDao.getTZorderlistByOrid(orid);
            TZorderlist zt = null;
            // 更新库存

            if (tzorderlist != null && tzorderlist.size() > 0) {// 趟次控制
                for (int i = 0; i < tzorderlist.size(); i++) {
                    zt = new TZorderlist();
                    BeanUtils.populate(zt, (Map) tzorderlist.get(i));
                    payorderDao.updateStock(orid, zt);
                }
            }
            if (grouptzorderlistByRaftDay != null
                    && grouptzorderlistByRaftDay.size() > 0) {
                grouptzorderlistByRaftDay = mergeDayRaft(grouptzorderlistByRaftDay);
                for (int j = 0; j < grouptzorderlistByRaftDay.size(); j++) {// 日控制
                    zt = new TZorderlist();
                    zt = (TZorderlist) grouptzorderlistByRaftDay.get(j);
                    payorderDao.updateDayControl(orid, zt.getDtstartdate()
                                    .substring(0, 10),
                            zt.getItickettypeid().toString(), zt.getId()
                                    .getIscenicid().toString(), zt.getZnumb()
                                    .intValue());
                }
            }
        }

        // 场馆座位 yangguang 添加
        List list = payorderDao.getOrderFilmSeat(orid);
        if (list == null || list.size() < 1) {
            list = JSON.parseArray(sysparService.pullSeat(orid), Seatordertab.class);
        }
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Seatordertab seat = (Seatordertab) list.get(i);
                seat.setIsvalid(1L);
                payorderDao.update(seat);
                Seatyordertab seaty = (Seatyordertab) payorderDao.get(
                        Seatyordertab.class, new SeatyordertabId(seat.getId()
                                .getSeq(), seat.getId().getOrid(), seat.getId()
                                .getIscenicid(), seat.getId().getOrderlistid(),
                                seat.getId().getZorderlistid()));
                seaty.setIsvalid(1l);
                payorderDao.update(seaty);
                Seatstatustab status = new Seatstatustab();
                status.setId(new SeatstatustabId(seat.getIvenueid(), seat
                        .getIvenueareaid(), seat.getItripid(), seat
                        .getIseatid(), seat.getStartdate().substring(0, 10)));
                status.setStatus(1l);
                status.setDtmakedate(Tools.getDayTimes());
                payorderDao.save(status);

            }
        }
        // 场馆座位 yangguang 添加
        // 0156 新增订单 0157 修改订单 0158 删除订单
        Orderlog log = new Orderlog();
        log.setLogid(payorderDao.getMaxPk("logid", "Orderlog") + 1);
        log.setOrid(orid);
        log.setStlg("0176");
        log.setBrief("订单" + orid + "支付或预订成功");
        Sysparv5 syspar = (Sysparv5) this.payorderDao.get(Sysparv5.class,
                new Sysparv5Id("ZFFS", zffs));
        log.setNote("订单" + orid + "支付或预订成功," + "支付方式:" + syspar.getPmva());
        log.setIemployeeid(null);
        log.setUsid(morder.getUsid());
        log.setLogtype(Long.parseLong("0"));
        log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
        payorderDao.save(log);

        return 1;
    }

    public List volidateTicketDayControl(String orid)
            throws IllegalAccessException, InvocationTargetException {
        List grouptzorderlistByRaftDay = payorderDao.getTZorderlist(orid);
        List tripnotEnough = new ArrayList();
        grouptzorderlistByRaftDay = mergeDayRaft(grouptzorderlistByRaftDay);
        for (int j = 0; j < grouptzorderlistByRaftDay.size(); j++) {// 日控制
            TZorderlist zt = new TZorderlist();
            BeanUtils.copyProperties(zt, grouptzorderlistByRaftDay.get(j));
            Edmtickettypetab ticket = (Edmtickettypetab) payorderDao.get(
                    Edmtickettypetab.class, zt.getItickettypeid());
            if (Tools.getDayNumbCha(zt.getDtstartdate().substring(0, 10),
                    Tools.getDays()) > 0) {
                TicketPojo errticket = new TicketPojo();
                errticket.setTicketname(ticket.getSztickettypename());
                errticket.setTourdate(zt.getDtstartdate().substring(0, 10));
                errticket.setType("3");// 过期产品
                if (tripnotEnough == null || tripnotEnough.size() < 1) {
                    tripnotEnough = new ArrayList();
                }
                tripnotEnough.add(errticket);
            } else {
                TOrderlist t = (TOrderlist) payorderDao.get(TOrderlist.class,
                        new TOrderlistId(zt.getId().getOrderlistid(), zt
                                .getId().getOrid(), zt.getId().getIscenicid()));
                boolean result = payorderDao.validateDayControl(orid, t);
                if (!result) {// 没有通过的
                    // ishave.add(zt.getItickettypeid().toString());
                    if (tripnotEnough == null || tripnotEnough.size() < 1) {
                        tripnotEnough = new ArrayList();
                    }
                    TicketPojo errticket = new TicketPojo();
                    errticket.setTicketname(ticket.getSztickettypename());
                    errticket.setTourdate(zt.getDtstartdate().substring(0, 10));
                    errticket.setType("1");// 日控制
                    tripnotEnough.add(errticket);
                }

            }
        }
        return tripnotEnough;
    }

    public List volidateTicketTripControl(String orid)
            throws IllegalAccessException, InvocationTargetException,
            ParseException {
        MOrder morder = (MOrder) payorderDao.get(MOrder.class, orid);
        if (morder.getDdzt().equals("23") || morder.getDdzt().equals("24")) {
            return null;
        }
        List tzorderlist = payorderDao.getTZorderlistByOrid(orid);
        List tripnotEnough = new ArrayList();
        for (int i = 0; i < tzorderlist.size(); i++) {// 趟次控制
            TZorderlist zorderlist = new TZorderlist();
            BeanUtils.populate(zorderlist, (Map) tzorderlist.get(i));
            if (zorderlist.getTripid() != null
                    && !zorderlist.getTripid().equals("")
                    && zorderlist.getTripid().intValue() != 0) {
                Productcontrol control = payorderDao.getProductcontrol(
                        zorderlist.getIztickettypeid().toString(), zorderlist
                                .getDtstartdate().substring(0, 10), zorderlist
                                .getIvenueid().toString(), zorderlist
                                .getIvenueareaid().toString(), zorderlist
                                .getTripid().toString());
                Date time = df
                        .parse(Tools.getDays() + " " + Tools.getNowTime());
                Date time1 = df.parse(zorderlist.getDtstartdate());
                if (time1.before(time)) {
                    TicketPojo errticket = new TicketPojo();
                    errticket.setStarttime(control.getStarttime());
                    errticket.setEndtime(control.getEndtime());
                    errticket.setTripname(control.getTripname());
                    errticket.setWharf(control.getVenueidname());
                    errticket.setTicketname(control.getSztickettypename());
                    errticket.setTourdate(zorderlist.getDtstartdate()
                            .substring(0, 10));
                    errticket.setType("2");
                    tripnotEnough.add(errticket);
                } else {
                    if (!payorderDao.validateTripControl(zorderlist)) {
                        if (tripnotEnough != null && tripnotEnough.size() > 1) {
                            tripnotEnough = new ArrayList();
                        }
                        TicketPojo errticket = new TicketPojo();
                        if (zorderlist.getTripid() != null
                                && zorderlist.getTripid().intValue() != 0) {
                            control = payorderDao.getProductcontrol(zorderlist
                                            .getIztickettypeid().toString(), zorderlist
                                            .getDtstartdate().substring(0, 10),
                                    zorderlist.getIvenueid().toString(),
                                    zorderlist.getIvenueareaid().toString(),
                                    String.valueOf(zorderlist.getTripid()
                                            .intValue()));
                            errticket.setStarttime(control.getStarttime());
                            errticket.setEndtime(control.getEndtime());
                            errticket.setTripname(control.getTripname());
                            errticket.setWharf(control.getVenueidname());
                        }
                        errticket.setTicketname(control.getSztickettypename());
                        errticket.setTourdate(zorderlist.getDtstartdate()
                                .substring(0, 10));
                        errticket.setType("2");
                        tripnotEnough.add(errticket);
                    }
                }
            }
        }
        return tripnotEnough;
    }

    /**
     *
     * Describe:
     *
     * @auth:yangguang
     * @param orid
     * @param zfusid
     * @param errorType
     *            0:抢排 1:信用低超额 return:void Date:2012-4-18
     */
    public void payFailture(String orid, String zfusid, String errorType) {
        MOrder morder = (MOrder) payorderDao.get(MOrder.class, orid);
        morder.setDdzt("25");
        payorderDao.update(morder);
        List torders = payorderDao.getTorder(orid);
        for (int i = 0; i < torders.size(); i++) {
            TOrder torder = (TOrder) torders.get(i);
            torder.setDdzt("25");
            payorderDao.update(torder);
        }
        List yorders = payorderDao.getYorder(orid);
        for (int i = 0; i < yorders.size(); i++) {
            YOrder yorder = (YOrder) yorders.get(i);
            yorder.setDdzt("25");
            payorderDao.update(yorder);
        }
        Useryfk yfk = new Useryfk();
        yfk.setUsid(zfusid);
        yfk.setBdate(Tools.getTodayString());
        Integer seqs = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
        if (errorType.equals("0")) {
            yfk.setSzmemo("下单失败");
            yfk.setNote("退订转预付款");
        } else {
            yfk.setSzmemo("信用度不够");
            yfk.setNote("信用度不够");
        }

        yfk.setOrderid(orid);
        yfk.setPoint(morder.getZfmont());
        yfk.setYfklb(1);// 退订转预付款
        yfk.setYfksc("02");
        yfk.setSeq(++seqs);
        yfk.setCztp(0);
        balancecenterDao.useryfksave(yfk);

        // 平台预付款 先从平台预付款转出
        List sysList = balancecenterDao
                .find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
        Sysparv5 sys = null;
        sys = (Sysparv5) sysList.get(0);// 取平台帐号
        Useryfk ptyfk = new Useryfk();
        ptyfk.setUsid(sys.getPmva());// 用户
        ptyfk.setBdate(Tools.getTodayString());
        ptyfk.setOrderid(orid);
        ptyfk.setPoint(morder.getZfmont());
        ptyfk.setYfklb(-1);// 预付款转出
        ptyfk.setYfksc("13");
        if (errorType.equals("0")) {
            ptyfk.setSzmemo("下单失败,退还预付款");
            ptyfk.setNote("用户退订退款");
        } else {
            yfk.setSzmemo("信用度不够");
            yfk.setNote("信用度不够");
        }
        ptyfk.setCztp(0);
        ptyfk.setSeq(++seqs);
        balancecenterDao.useryfksave(ptyfk);

    }

    public void addPaybackInfo(String orid, String backurl, String usid) {
        try {
            Payback back = new Payback();
            back.setOrderid(orid);
            back.setPayusid(usid);
            back.setBackurl(backurl);
            back.setBacktime(Tools.getDayTime());
            this.save(back);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("订单重复支付异常:" + orid);
        }

    }

    /**
     * 订单拆分专用
     * @param orid
     * @param zfusid
     */
    public void savePayFailture2(String orid, String zfusid) {
        MOrder morder = (MOrder) payorderDao.get(MOrder.class, orid);
        Useryfk yfk = new Useryfk();
        Integer seqs = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
        yfk.setUsid(zfusid);
        yfk.setBdate(Tools.getTodayString());
        yfk.setSzmemo("下单失败");
        yfk.setNote("退订转预付款");

        yfk.setOrderid(orid);
        yfk.setPoint(morder.getZfmont());
        yfk.setYfklb(1);// 退订转预付款
        yfk.setYfksc("02");

        yfk.setCztp(0);
        seqs = seqs + 1;
        yfk.setSeq(seqs.intValue());
        balancecenterDao.useryfksave(yfk);

        // 平台预付款 先从平台预付款转出
        List sysList = balancecenterDao
                .find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
        Sysparv5 sys = null;
        sys = (Sysparv5) sysList.get(0);// 取平台帐号
        Useryfk ptyfk = new Useryfk();
        ptyfk.setUsid(sys.getPmva());// 用户
        ptyfk.setBdate(Tools.getTodayString());
        ptyfk.setOrderid(orid);
        ptyfk.setPoint(morder.getZfmont());
        ptyfk.setYfklb(-1);// 预付款转出
        ptyfk.setYfksc("13");
        ptyfk.setSzmemo("下单失败,退还预付款");
        ptyfk.setNote("用户退订退款");
        ptyfk.setCztp(0);
        seqs = seqs + 1;
        ptyfk.setSeq(seqs.intValue());
        balancecenterDao.useryfksave(ptyfk);

    }

    public void saveSplitOrder(String oldOrid,String[] orids,String zfusid,String newUrl) throws Exception{
      
    	//判断是否输入域名
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	//作废原订单
        savePayFailture2(oldOrid, zfusid);
        //生成新订单
        saveNewOrder(oldOrid, orids,newUrl);
        //修改原订单状态
        MOrder morder = (MOrder) payorderDao.get(MOrder.class, oldOrid);
        morder.setDdzt("26");
        payorderDao.update(morder);
        List torders = payorderDao.getTorder(oldOrid);
        for (int i = 0; i < torders.size(); i++) {
            TOrder torder = (TOrder) torders.get(i);
            torder.setDdzt("26");
            payorderDao.update(torder);
        }
        List yorders = payorderDao.getYorder(oldOrid);
        for (int i = 0; i < yorders.size(); i++) {
            YOrder yorder = (YOrder) yorders.get(i);
            yorder.setDdzt("26");
            payorderDao.update(yorder);
        }
    }

    /**
     * 订单拆分时使用
     * @param orid 原订单号
     * @param orids 新订单号数组
     * @throws Exception
     */
    public void saveNewOrder(String orid,String[] orids,String newUrl) throws Exception {
    	
    	//判断是否输入域名
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	
        MOrder oldmorder = (MOrder) payorderDao.get(MOrder.class,orid);
        List<TOrderlist> oldtls = payorderDao.find("from TOrderlist where orid = '"+orid+"' ");
        int size = 0;
        //找出原订单产品列表
        if(oldtls != null && !oldtls.isEmpty()) {
            for (int i = 0; i < oldtls.size(); i++) {
                TOrderlist otl = oldtls.get(i);
                int numb = otl.getNumb().intValue();
                //原订单TOrder
                TOrder oldtorder = (TOrder) payorderDao.get(TOrder.class,new TOrderId(otl.getId().getOrid(),otl.getId().getIscenicid()));
                //原订单YOrder
                YOrder oldyorder = (YOrder) payorderDao.get(YOrder.class,new YOrderId(otl.getId().getOrid(),otl.getId().getIscenicid()));
                //原订单子票列表tzorderlist
                List<TZorderlist> oldtzls = payorderDao.find("from TZorderlist where id.orid ='" + otl.getId().getOrid() + "' " +
                        " and id.iscenicid = " + otl.getId().getIscenicid() + " and id.orderlistid=" + otl.getId().getOrderlistid());
                //原订单子票列表yzorderlist
                List<YZorderlist> oldyzls = payorderDao.find("from YZorderlist where id.orid ='" + otl.getId().getOrid() + "' " +
                        " and id.iscenicid = " + otl.getId().getIscenicid() + " and id.orderlistid=" + otl.getId().getOrderlistid());
                //原订单yorderlist
                YOrderlist oyl = (YOrderlist) payorderDao.get(YOrderlist.class, new YOrderlistId(otl.getId().getOrderlistid(),
                        otl.getId().getOrid(), otl.getId().getIscenicid()));
                //根据产品数量进行循环,一个数量一个订单
                for (int j = 0; j < numb; j++) {
                    //获取新的订单号
                    String neworid = orids[size++];
                    //组装MOrder
                    MOrder newmorder = new MOrder();
                    BeanUtils.copyProperties(newmorder, oldmorder);
                    newmorder.setOrid(neworid);
                    newmorder.setMont(otl.getPric());
                    newmorder.setZfmont(otl.getPric());
                    newmorder.setZffs("06");
                    newmorder.setBank("92");
                    newmorder.setDdzt("02");
                    newmorder.setSrid(orid);
                    newmorder.setIsg(1L);
                    payorderDao.save(newmorder);
                    //组装TOrder
                    TOrder newtorder = new TOrder();
                    BeanUtils.copyProperties(newtorder,oldtorder);
                    newtorder.getId().setOrid(neworid);
                    newtorder.getId().setIscenicid(otl.getId().getIscenicid());
                    newtorder.setMont(otl.getPric());
                    newtorder.setZfmont(otl.getPric());
                    newtorder.setDdzt("02");
                    payorderDao.save(newtorder);
                    //组装YOrder
                    YOrder newyorder = new YOrder();
                    BeanUtils.copyProperties(newyorder,oldyorder);
                    newyorder.getId().setOrid(neworid);
                    newyorder.getId().setIscenicid(oyl.getId().getIscenicid());
                    newyorder.setMont(otl.getPric());
                    newyorder.setZfmont(otl.getPric());
                    newyorder.setDdzt("02");
                    payorderDao.save(newyorder);
                    //组装TOrderlist
                    TOrderlist ntl = new TOrderlist();
                    BeanUtils.copyProperties(ntl,otl);
                    ntl.getId().setOrid(neworid);
                    ntl.getId().setIscenicid(otl.getId().getIscenicid());
                    ntl.getId().setOrderlistid(1L);
                    ntl.setNumb(1L);
                    ntl.setAmnt(otl.getPric());
                    payorderDao.save(ntl);
                    //组装YOrderlist
                    YOrderlist nyl = new YOrderlist();
                    BeanUtils.copyProperties(nyl,oyl);
                    nyl.getId().setOrid(neworid);
                    nyl.getId().setIscenicid(oyl.getId().getIscenicid());
                    nyl.getId().setOrderlistid(1L);
                    nyl.setNumb(1L);
                    nyl.setAmnt(oyl.getPric());
                    payorderDao.save(nyl);
                    //组装TZorderlist
                    for(int x=0;x<oldtzls.size();x++){
                        TZorderlist otzl = oldtzls.get(x);
                        TZorderlist ntzl = new TZorderlist();
                        BeanUtils.copyProperties(ntzl,otzl);
                        ntzl.getId().setOrid(neworid);
                        ntzl.getId().setIscenicid(otzl.getId().getIscenicid());
                        ntzl.getId().setOrderlistid(ntl.getId().getOrderlistid());
                        ntzl.getId().setZorderlistid(x+1L);
                        ntzl.setZnumb(1L);
                        ntzl.setZamnt(ntzl.getZpric());
                        payorderDao.save(ntzl);
                    }
                    //组装YZorderlist
                    for(int x=0;x<oldyzls.size();x++){
                        YZorderlist oyzl = oldyzls.get(x);
                        YZorderlist nyzl = new YZorderlist();
                        BeanUtils.copyProperties(nyzl,oyzl);
                        nyzl.getId().setOrid(neworid);
                        nyzl.getId().setIscenicid(oyzl.getId().getIscenicid());
                        nyzl.getId().setOrderlistid(nyl.getId().getOrderlistid());
                        nyzl.getId().setZorderlistid(x+1L);
                        nyzl.setZnumb(1L);
                        nyzl.setZamnt(nyzl.getZpric());
                        payorderDao.save(nyzl);
                    }
                    //组装实名制信息
                    List<TRealname> realnames = payorderDao.find("from TRealname where orid = '"+orid+"' and iscenicid = "+otl.getId().getIscenicid()+"" +
                            " and itickettypeid = "+otl.getItickettypeid()+" and icrowdkindid = "+otl.getIcrowdkindid());
                    if(realnames != null && !realnames.isEmpty()){
                        TRealname realname = realnames.get(0);
                        realname.setOrid(neworid);
                        payorderDao.update(realname);
                    }
                    minusUserYfk(newmorder.getZfusid(),neworid,newmorder.getZfmont());
                    //保存库存
                    List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
                    StockOrderInfo stock = new StockOrderInfo();
                    stock.setOrid(neworid);
                    stock.setProviderId(ntl.getId().getIscenicid());
                    stock.setProductId(ntl.getItickettypeid());
                    stock.setPriceId(ntl.getIcrowdkindpriceid());
                    stock.setUsid(newmorder.getUsid());
                    stock.setNumb(ntl.getNumb());
                    stock.setStockDate(ntl.getDtstartdate());
                    stocks.add(stock);
                    saveStockDetails(stocks);
                    //查询预付款流水
                    List ulist = payorderDao.find("from Useryfk u where u.orderid='"+neworid+"' and u.usid = '"+newmorder.getZfusid()+"'" +
                            " and u.yfklb=-1 and u.yfksc='03' and u.point = "+newmorder.getZfmont());
                    if(ulist == null || ulist.isEmpty()){
                        throw new RuntimeException("下单失败");
                    }
                    Useryfk useryfk = (Useryfk) ulist.get(0);
                    newmorder.setPayorid(useryfk.getSeq().toString());
                    newmorder.setBankdata(useryfk.getBdate().substring(0,10));
                    newmorder.setBanktime(useryfk.getBdate().substring(11));
                    payorderDao.update(newmorder);
                    //发送二维码；
                    new TwoBarUtil().makeTwoBarcode(neworid, newmorder, null,newUrl);
                }
            }
            //删除旧库存
            ticketService.deleteStockDetails(orid,null,null,null);
        }
    }

    private boolean minusUserYfk(String zfusid, String orid, double mont) {
        // 用户预付款 先增加全退订金额

        Useryfk yfk = new Useryfk();
        Integer seqs = this.balancecenterDao.getMaxSeq("Useryfk", "seq");
        yfk.setUsid(zfusid);
        yfk.setBdate(Tools.getDayTimes());
        yfk.setSzmemo("用户  [" + zfusid + "]支付订单" + orid);
        yfk.setOrderid(orid);
        yfk.setPoint(mont);
        yfk.setYfklb(-1);
        yfk.setYfksc("03"); // 消费预付款
        yfk.setNote("订单消费");
        yfk.setCztp(0);
        seqs = seqs + 1;
        yfk.setSeq(seqs.intValue());
        balancecenterDao.useryfksave(yfk);

        // 平台预付款 先从平台预付款转出
        List sysList = payorderDao.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
        Sysparv5 sys = null;

        sys = (Sysparv5) sysList.get(0);// 取平台帐号
        Useryfk ptyfk = new Useryfk();
        ptyfk.setUsid(sys.getPmva());// 用户
        ptyfk.setBdate(Tools.getTodayString());
        ptyfk.setOrderid(orid);
        ptyfk.setPoint(mont);
        ptyfk.setYfklb(1);
        ptyfk.setYfksc("14"); // 用户消费预付款 那平台就是用户预付款转入
        ptyfk.setNote("用户订单消费");
        ptyfk.setCztp(0);
        seqs = seqs + 1 ;
        ptyfk.setSeq(seqs.intValue());
        balancecenterDao.useryfksave(ptyfk);

        return true;
    }


    /**
     *保存库存信息
     * @param stockOrderInfos 为最新的订单数据
     */
    public void  saveStockDetails(List<StockOrderInfo> stockOrderInfos){
        if(stockOrderInfos != null && !stockOrderInfos.isEmpty()){
            StringBuffer seqs = new StringBuffer();
            for(StockOrderInfo stockOrderInfo : stockOrderInfos){
                //判断库存明细是否存在
                Stockdetailtab stockdetailtab = ticketService.selectStockDetail(stockOrderInfo);
                if(stockdetailtab == null){//无库存明细信息
                    stockdetailtab = new Stockdetailtab();
                    stockdetailtab.setOrid(stockOrderInfo.getOrid());
                    stockdetailtab.setProviderid(stockOrderInfo.getProviderId());
                    stockdetailtab.setProductid(stockOrderInfo.getProductId());
                    stockdetailtab.setPriceid(stockOrderInfo.getPriceId());
                    stockdetailtab.setUsid(stockOrderInfo.getUsid());
                    stockdetailtab.setNumb(stockOrderInfo.getNumb());
                    stockdetailtab.setConsumedate(stockOrderInfo.getStockDate());
                    stockdetailtab.setDtmakedate(Tools.getDayTimes());
                    this.save(stockdetailtab);
                    stockdetailtab = ticketService.selectStockDetail(stockOrderInfo);
                    seqs.append(stockdetailtab.getSeq()+",");
                }else{//有库存明细信息
                    seqs.append(stockdetailtab.getSeq()+",");
                    stockdetailtab.setNumb(stockOrderInfo.getNumb());
                    this.update(stockdetailtab);
                }
            }
            String noinclude = "";
            if(!StringUtils.isBlank(seqs.toString())){
                noinclude  = seqs.toString().substring(0, seqs.toString().length()-1);
            }
            ticketService.deleteStockDetails(stockOrderInfos.get(0).getOrid(),stockOrderInfos.get(0).getProviderId(),null,noinclude);
        }
    }
}
