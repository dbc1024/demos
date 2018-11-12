package com.ectrip.ticket.sale.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Debug;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.ec.model.report.sales.Stssoldfingerprint;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.model.syspar.Webinfotab;
import com.ectrip.ticket.common.checkUtils.CommonCheckUtil;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.centersale.T_orderlist;
import com.ectrip.ticket.model.centersale.T_zorderlist;
import com.ectrip.ticket.model.centersale.Trealname;
import com.ectrip.ticket.model.order.Seatsaletab;
import com.ectrip.ticket.model.order.SeatsaletabId;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.StscomticketsalesdetailstabId;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stsschecktab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.Ticketprintlist;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.TicketPrintNo;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.model.stock.StockJson;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Seatlockdetail;
import com.ectrip.ticket.model.venuemarketing.Seatlocktab;
import com.ectrip.ticket.model.venuemarketing.Seatstatustab;
import com.ectrip.ticket.model.venuemarketing.SeatstatustabId;
import com.ectrip.ticket.model.venuemarketing.Tripprdsaletab;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.model.warehouse.Kcpersonalticketdetailstab;
import com.ectrip.ticket.provider.service.impl.TimeSharingService;
import com.ectrip.ticket.sale.service.OnlinePayment.model.bean.PaymentOrder;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.sale.service.iservice.ISaveVenueService;
import com.ectrip.ticket.stocks.dao.idao.IStocksWareDAO;
import com.ectrip.ticket.util.JaxWsDynamicClientFactoryUtil;
import com.ectrip.ticket.util.ServerNameConst;

@Service("saveVenueService")
public class SaveVenueService implements ISaveVenueService {
	private static final Logger LOGGER = LogManager.getLogger(SaveVenueService.class);
    @Autowired
    private IStocksWareDAO stockswareDao;

	@Autowired
    private ISaleCenterService saleCenterService;
	
	@Autowired
	private EcService ecService;
	@Autowired
	private SysService sysService;
	@Autowired
	private TimeSharingService timeSharingService;
	@Autowired
	private IStockService stockService;

    private static long dunum = 0;

    public Venueprogram getprogram(Long iprogramid) {
        Venueprogram v = (Venueprogram) stockService.get(Venueprogram.class,
                iprogramid);
        return v;
    }

    public ResultBean getprogram(Long iscenicid, String stdt,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("WWWDOMAIN");
    	}
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct n.iprogramid,n.szprogramname,n.szprogramdeac,n.bywebsaletype,n.bycashsaletype from Tripprdcontroltab t,Venueprogram n where t.startdata<='"
                + stdt + "' and t.enddata>='" + stdt + "' and t.byisuse=1");

        if (iscenicid.intValue() > 0) {
            sql.append(" and n.iscenicid=" + iscenicid);
        }
        sql.append(" and t.iprogramid=n.iprogramid and n.byisuse=1");
        List<Map> list = new ArrayList();
        List<Map> list3 = new ArrayList();
        try {
            list = stockService.findBySqlToMap(sql.toString());
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);

                Long iprogramid = new Long(map.get("IPROGRAMID").toString());
                // 读取一张图片

                List<Map> list1 = stockService
                        .findBySqlToMap("select u.upadder,u.upfilename from Venueprogrampic p,Upfile u where p.iprogramid="
                                + iprogramid
                                + " and p.upid=u.upid order by p.upid ");
                if (list1.size() > 0) {
                    Map map1 = (Map) list1.get(0);
                    map.put("programpic",
                                     url
                                    + map1.get("UPADDER").toString()
                                    + map1.get("UPFILENAME").toString());
                } else {
                    map.put("programpic", "");
                }
                // 读取排班数据
                List<Map> list2 = stockService
                        .findBySqlToMap("select t.itripprdcontrolid,t.ivenueid,v.venueidname,td.itripid,tr.tripname,td.starttime,td.iadvanceminute,td.ilagminute from Tripprdcontroldetailtab td,Tripprdcontroltab t,Venue v,Trip tr where t.iprogramid="
                                + iprogramid
                                + " and td.itripprdcontrolid=t.itripprdcontrolid and  t.ivenueid=v.ivenueid and t.byisuse=1 and td.byisuse=1 and td.itripid=tr.tripid and t.startdata<='"
                                + stdt
                                + "' and t.enddata>='"
                                + stdt
                                + "' order by td.starttime");
                StringBuffer detail = new StringBuffer();
                for (Map map2 : list2) {
                    if (stdt.equals(Tools.getTodayString())) {
                        String starttime = Tools.getTodayString() + " "
                                + map2.get("STARTTIME").toString() + ":00";
                        SimpleDateFormat df = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                        Date d1 = df.parse(starttime);
                        Calendar begcalendar = Calendar.getInstance();
                        begcalendar.setTime(d1);
                        Object ilagminute = map2.get("ILAGMINUTE");
                        if (ilagminute != null
                                && ilagminute.toString().matches("^\\d+")) {
                            begcalendar.add(Calendar.MINUTE,
                                    Integer.parseInt(ilagminute.toString()));
                        }
                        Calendar now = Calendar.getInstance(TimeZone
                                .getTimeZone("GMT+08:00"));
                        if (now.before(begcalendar)) {
                            detail.append(map2.get("ITRIPPRDCONTROLID")
                                    .toString());
                            detail.append("#");
                            detail.append(map2.get("IVENUEID").toString());
                            detail.append("#");
                            detail.append(map2.get("VENUEIDNAME").toString());
                            detail.append("#");
                            detail.append(map2.get("ITRIPID").toString());
                            detail.append("#");
                            detail.append(map2.get("TRIPNAME").toString());
                            detail.append("#");
                            detail.append(map2.get("STARTTIME").toString());
                            detail.append("#");
                            detail.append(map2.get("IADVANCEMINUTE").toString());
                            detail.append("#");
                            detail.append(map2.get("ILAGMINUTE").toString());
                            detail.append("!");
                        }
                    } else {
                        detail.append(map2.get("ITRIPPRDCONTROLID").toString());
                        detail.append("#");
                        detail.append(map2.get("IVENUEID").toString());
                        detail.append("#");
                        detail.append(map2.get("VENUEIDNAME").toString());
                        detail.append("#");
                        detail.append(map2.get("ITRIPID").toString());
                        detail.append("#");
                        detail.append(map2.get("TRIPNAME").toString());
                        detail.append("#");
                        detail.append(map2.get("STARTTIME").toString());
                        detail.append("#");
                        detail.append(map2.get("IADVANCEMINUTE").toString());
                        detail.append("#");
                        detail.append(map2.get("ILAGMINUTE").toString());
                        detail.append("!");
                    }

                }
                String trips = detail.toString();

                if (!trips.equals("")) {
                    trips = trips.substring(0, trips.length() - 1);
                    map.put("trips", trips);
                    list3.add(map);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(list3);
    }

    public ResultBean Getvenue(Long ivenueid) {
        List<Map> stslist = new ArrayList();
        try {
            stslist = stockService
                    .findBySqlToMap("select * from Venue where ivenueid="
                            + ivenueid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(stslist);
    }

    public ResultBean Getseat(Long ivenueid) {
        List<Map> list = new ArrayList();
        try {
            list = stockService
                    .findBySqlToMap("select vs.ivenueseatsid,vs.szvenueseatscode,vs.szvenueseatsname,vs.ivenueareaid,ve.ivenueareaname,vs.irowserialnum,vs.icolumnserialnum,vs.byisuse,v.irowscount,v.icolumnscount from Venueseats vs,Venuearea ve,Venue v where  vs.ivenueid="
                            + ivenueid
                            + " and vs.ivenueareaid=ve.ivenueareaid and vs.ivenueid=ve.ivenueid and vs.ivenueid=v.ivenueid order by vs.irowserialnum,icolumnserialnum");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(list);
    }

    public ResultBean Getseatstusts(Long ivenueid, String stdt, Long tripid) {
        List<Map> list = new ArrayList();
        try {
            list = stockService
                    .findBySqlToMap("select s.iseatid,s.ivenueid,s.ivenueareaid,s.itripid,s.startdate,s.status from Seatstatustab s where  s.startdate='"
                            + stdt
                            + "' and  s.ivenueid="
                            + ivenueid
                            + " and s.itripid=" + tripid);
            System.out
                    .println("select s.iseatid,s.ivenueid,s.ivenueareaid,s.itripid,s.startdate,s.status from Seatstatustab s where  s.startdate='"
                            + stdt
                            + "' and  s.ivenueid="
                            + ivenueid
                            + " and s.itripid=" + tripid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(list);
    }

    public ResultBean Getareapricve(Long Itripprdcontrolid, String stdt,
                                    String groupid, Long ibusinessid) {
        List<Map> pplist = new ArrayList();
        List list = stockService
                .find(" from Tripprdsaletab where itripprdcontrolid="
                        + Itripprdcontrolid);
        for (int i = 0; i < list.size(); i++) {
            Tripprdsaletab ts = (Tripprdsaletab) list.get(i);
            Edmtickettypetab es = (Edmtickettypetab) stockService.get(
                    Edmtickettypetab.class, ts.getIproductid());
            Long iscenicid = es.getIscenicid();
            String[] groupids = groupid.split("&");
            String groupno = "0000";

            for (int j = 0; j < groupids.length; j++) {
                String[] gg = groupids[j].split("_");
                if (gg[0].equals(iscenicid.toString())) {
                    groupno = gg[1];
                }
            }
            try {
                List<Map> plist = stockService
                        .findBySqlToMap("select et.sztickettypename, e.itickettypeid,e.mactualsaleprice,ek.szcrowdkindname,e.icrowdkindpriceid,e.icrowdkindid,e.ibusinessid,et.bymaketicketway,et.bymediatype,e.note1,e.ipeoplenumrange from Edmcrowdkindpricetab e ,Edpcrowdkindtab ek,Edmtickettypetab et where e.itickettypeid="
                                + ts.getIproductid()
                                + " and e.startdata<='"
                                + stdt
                                + "' and e.enddata>='"
                                + stdt
                                + "' and e.note1='"
                                + groupno
                                + "' and e.ibusinessid="
                                + ibusinessid
                                + "  and ek.icrowdkindid=e.icrowdkindid and et.itickettypeid=e.itickettypeid and e.isscene=1 and e.byisuse=1 and et.byisuse=1");

                for (int j = 0; j < plist.size(); j++) {
                    Map map = plist.get(j);
                    map.put("ivenueareaid", ts.getIvenueareaid().toString());
                    pplist.add(plist.get(j));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return MapToResultBean.toResultBean(pplist);
    }

    public synchronized ResultBean updateseatstuts(String comticketsalesdetails)
            throws Exception {
        List seatlist = new ArrayList();
        String[] comticketsalesdetail = comticketsalesdetails.split(":");
        for (int i = 0; i < comticketsalesdetail.length; i++) {
            String[] zdetail = comticketsalesdetail[i].split("&");

            Long tripid = new Long(zdetail[5]);
            String dtstartdate = zdetail[8];
            String dtenddate = zdetail[9];
            Long ivenueid = new Long(zdetail[6]);
            Long ivenueareaid = new Long(zdetail[7]);
            if (zdetail.length >= 12) {
                Long iprogramid = new Long(zdetail[10]);
                String seats = zdetail[11];
                if (iprogramid > 0) {
                    // 存在剧院票
                    Venueprogram v = (Venueprogram) stockService.get(
                            Venueprogram.class, iprogramid);
                    boolean flag = false;
                    if (v.getBycashsaletype() == 1) {
                        for (int j = 0; j < seatlist.size(); j++) {
                            Seatstatustab stst = (Seatstatustab) seatlist
                                    .get(j);
                            if (stst.getId().getIvenueid() == ivenueid
                                    && stst.getId().getIvenueareaid() == ivenueareaid
                                    && stst.getId().getItripid() == tripid
                                    && stst.getId().getStartdate()
                                    .equals(dtstartdate)) {
                                flag = true;
                                stst.setIseatids(stst.getIseatids() + seats);
                            }
                        }
                        if (!flag) {
                            Seatstatustab stst = new Seatstatustab();
                            SeatstatustabId ststid = new SeatstatustabId();
                            ststid.setIvenueid(ivenueid);
                            ststid.setIvenueareaid(ivenueareaid);
                            ststid.setStartdate(dtstartdate);
                            ststid.setItripid(tripid);
                            stst.setId(ststid);
                            stst.setIseatids(seats);
                            seatlist.add(stst);
                        }

                    }
                }
            }
        }
        for (int i = 0; i < seatlist.size(); i++) {
            Seatstatustab stst = (Seatstatustab) seatlist.get(i);
            String seats = stst.getIseatids();
            String[] seat = seats.split(">");
            String sseat = "";
            for (int j = 0; j < seat.length; j++) {
                if (seat[j] != null && !seat[j].equals("")) {
                    if (sseat.equals("")) {
                        sseat = seat[j];
                    } else {
                        sseat = sseat + "," + seat[j];
                    }
                }
            }
            List list = this.stockService
                    .findBySqlToMap("select iseatid from Seatstatustab where ivenueid="
                            + stst.getId().getIvenueid()
                            + " and ivenueareaid="
                            + stst.getId().getIvenueareaid()
                            + " and itripid="
                            + stst.getId().getItripid()
                            + " and startdate='"
                            + stst.getId().getStartdate()
                            + "' and iseatid in (" + sseat + ")");
            if (list != null && list.size() > 0) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", "座位已售出，订票不成功" });
                return rs;
            }
        }
        try {
            // 开始保存座位状态
            for (int i = 0; i < seatlist.size(); i++) {
                Seatstatustab stst = (Seatstatustab) seatlist.get(i);
                String seats = stst.getIseatids();
                String[] seat = seats.split(">");
                for (int j = 0; j < seat.length; j++) {
                    if (seat[j] != null && !seat[j].equals("")) {
                        Seatstatustab nstst = new Seatstatustab();
                        SeatstatustabId nststid = new SeatstatustabId();
                        nststid.setIvenueid(stst.getId().getIvenueid());
                        nststid.setIvenueareaid(stst.getId().getIvenueareaid());
                        nststid.setItripid(stst.getId().getItripid());
                        nststid.setStartdate(stst.getId().getStartdate());
                        nststid.setIseatid(new Long(seat[j]));
                        nstst.setId(nststid);
                        nstst.setStatus(1L);
                        nstst.setDtmakedate(Tools.getDayTimes());
                        stockService.save(nstst);
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("运行出错，有座位已售出");
        }
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        rs.addRow(new String[] { "true", "座位预定成功" });
        return rs;

    }

    public ResultBean updatehfseatstuts(String comticketsalesdetails) {

        String[] comticketsalesdetail = comticketsalesdetails.split(":");
        for (int i = 0; i < comticketsalesdetail.length; i++) {
            String[] zdetail = comticketsalesdetail[i].split("&");

            Long tripid = new Long(zdetail[5]);
            String dtstartdate = zdetail[8];
            String dtenddate = zdetail[9];
            Long ivenueid = new Long(zdetail[6]);
            Long ivenueareaid = new Long(zdetail[7]);
            if (zdetail.length >= 12) {
                Long iprogramid = new Long(zdetail[10]);
                String seats = zdetail[11];
                if (iprogramid > 0) {
                    // 存在剧院票
                    String[] seatid = seats.split(">");
                    for (int j = 0; j < seatid.length; j++) {
                        // Seatstatustab stst = new Seatstatustab();
                        SeatstatustabId ststid = new SeatstatustabId();
                        ststid.setIvenueid(ivenueid);
                        ststid.setIvenueareaid(ivenueareaid);
                        ststid.setStartdate(dtstartdate);
                        ststid.setItripid(tripid);
                        ststid.setIseatid(new Long(seatid[j]));
                        Seatstatustab stst = (Seatstatustab) this.stockService
                                .get(Seatstatustab.class, ststid);
                        if (stst != null) {
                            this.stockService.delete(stst);
                        }

                    }
                }
            }
        }
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        rs.addRow(new String[] { "true", "座位预定成功" });
        return rs;

    }

    public ResultBean saveorder42(String salesvouchers,
                                  String salesvoucherdetails, String comticketsalesdetails,
                                  Long maxid, String szsalesvoucherno,String url, String... param)
            throws Exception {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        // 开始保存订单

        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        Long seatlockid = 0L;
        String seatids = "";
        boolean haveseat1 = false;
        try {
            String[] salesvoucher = salesvouchers.split("&");
            Long iscenicid = new Long(salesvoucher[0]);

            Esbscenicareatab scenic = (Esbscenicareatab) stockswareDao.get(
                    Esbscenicareatab.class, iscenicid);

            Long iticketwinid = new Long(salesvoucher[1]);
            Esbticketwintab e = (Esbticketwintab) stockswareDao.get(
                    Esbticketwintab.class, iticketwinid);

            Esbticketstationtab esbticketstation = (Esbticketstationtab) stockswareDao
                    .get(Esbticketstationtab.class, e.getIticketstationid());
            Double iaccountreceivable = new Double(salesvoucher[5]);

            Double iacceptmoney = new Double(salesvoucher[4]);

            if (iacceptmoney < iaccountreceivable) {
                System.out.println("实收款应该大于等于应收金额 ");
                rs.addRow(new String[] { "false", "实收款应该大于等于应收金额" });
                return rs;
            }
            // 将订单信息参数转换成map对像
            System.out.println("最新的订单保存saveorder421 ");
            Stssalesvouchertab s = new Stssalesvouchertab();

            if (param != null && param.length > 0) {
                System.out.println(param[0]);
                if (param[0] != null && !param[0].equals("")) {
                    String[] ordercs = param[0].split("&");
                    Map returnMap = new HashMap();
                    for (int i = 0; i < ordercs.length; i++) {
                        if (!ordercs.equals("")) {
                            String[] ss = ordercs[i].split(":");
                            System.out.println(ss.length);
                            if (ss.length == 2) {
                                returnMap.put(ss[0].toLowerCase(), ss[1]);
                            }
                        }
                    }
                    s = (Stssalesvouchertab) saleCenterService.convertMap(
                            Stssalesvouchertab.class, returnMap);
                }
                if (param != null && param.length > 1) {
                    System.out.println("param[1]" + param[1]);
                    seatlockid = new Long(param[1]);
                }
            }

            // System.out.println("1111111111111111111111");
            // 生成凭证
            int sjcpnumb = 0;

            System.out.println("最新的订单保存saveorder422 ");
            s = saleCenterService.saveStssalesvouchertab(salesvouchers, maxid,
                    szsalesvoucherno, s);
            Custom cum = null;
            if (s.getIbusinessid() != 1) {
                cum = ecService.getCustomByUserId(s.getUsid());
            }
            String[] comticketsalesdetail = comticketsalesdetails.split(":");
            for (int i = 0; i < comticketsalesdetail.length; i++) {
                String[] zdetail = comticketsalesdetail[i].split("&");
                if (zdetail.length >= 12) {
                    if (zdetail[10] != null
                            && !zdetail[10].equalsIgnoreCase("null")
                            && !zdetail[10].equals("")) {
                        haveseat1 = true;
                    }

                }
                Long isalesvoucherdetailsid = new Long(zdetail[0]);
                Long icrowdkindpriceid = new Long(zdetail[1]);
                Long itickettypeid = new Long(zdetail[2]);
                Long iztickettypeid = new Long(zdetail[3]);
                Long isplitamount = new Long(zdetail[4]);
                // Long tripid = new Long(zdetail[5]);
                String dtstartdate = zdetail[8];
                String dtenddate = zdetail[9];
                Prdtripvenuemanage p = null;
                Edmcrowdkindpricetab emt = (Edmcrowdkindpricetab) stockswareDao
                        .get(Edmcrowdkindpricetab.class, icrowdkindpriceid);

                Edmtickettypetab edticket = (Edmtickettypetab) stockswareDao.get(
                        Edmtickettypetab.class, itickettypeid);
                if (s.getIbusinessid() != 1) {

                    // 获取价格分组
                    String groupno = this.searchJgfz(s.getUsid(),
                            edticket.getIscenicid());

                    if (!emt.getNote1().equals(groupno)) {
                        rs.addRow(new String[] {
                                "false",
                                "编号" + emt.getIcrowdkindpricecode()
                                        + "产品有不属于对应用户价格组中的票务" });
                        return rs;
                    }
                }
                if (edticket.getBymaketicketway().equals("00")) {

                    List printlist = this.stockswareDao
                            .find(" from Priceprintmanager where id.icrowdkindpriceid="
                                    + icrowdkindpriceid
                                    + " order by ordernum");
                    if (printlist == null || printlist.size() == 0) {
                        Edmcrowdkindpricetab ep = (Edmcrowdkindpricetab) this.stockswareDao
                                .get(Edmcrowdkindpricetab.class,
                                        emt.getIcrowdkindpriceid());
                        /*printlist = this.stockswareDao.find("from Printticketmanage where id.iscenicid="
                                        + edticket.getIscenicid()
                                        + " and id.ibusinessid=" + ep.getIbusinessid());*/
                        //查询景区服务商统一的打印设置信息
                        printlist = sysService.getPrintticketmanageList(edticket.getIscenicid(), ep.getIbusinessid());
                        if (printlist == null || printlist.size() == 0) {
							rs.addRow(new String[] { "false", "编号" + edticket.getSztickettypename() + "产品价格没有打印设置不能出票" });
                            return rs;
                        }
                    }

                }
                List list = stockswareDao.find(" from Edmticketcomposepricetab where id.icrowdkindpriceid="
                                + icrowdkindpriceid
                                + " and itickettypeid='"
                                + iztickettypeid + "'");
                if (list == null || list.size() == 0) {
                    rs.addRow(new String[] { "false",
                            "票价编号为" + icrowdkindpriceid + "无子票价格" });
                    return rs;
                } else {
                    List opwwlist = stockswareDao
                            .find(" from Opwwicketsettab where itickettypeid="
                                    + itickettypeid + " and izticktypeid="
                                    + iztickettypeid + " and byisuse=1");

                    if (opwwlist.size() == 0) {

                        rs.addRow(new String[] { "false",
                                edticket.getSztickettypecode() + "的检票园门数据不全" });
                        return rs;
                    }
                }
            }
            String zffs = "00";
            if (salesvoucher.length >= 11) {
                zffs = salesvoucher[10];// 支付方式
            }

            // 目前结算只有一种方式，结算凭证
            Stssalessettlementtab st = saleCenterService
                    .saveStssalessettlementtab(s, zffs);
            System.out.println("最新的订单保存saveorder423 ");
            List detaillist = new ArrayList();
            List cdetaillist = new ArrayList();
            List zdetaillist = new ArrayList();
            List cdzetaillist = new ArrayList();
            List cdchecklist = new ArrayList();
            List cseatlist = new ArrayList();
            String[] salesvoucherdetail = salesvoucherdetails.split(":");
            double smont = 0;
            List zjlist = new ArrayList();
            long maxnumb = 2;// 同一种票同一游览日期最多预定人数
            /*Sysparv5 pv5 = (Sysparv5) this.stockService.get(Sysparv5.class,
                    new Sysparv5Id());*/
            Sysparv5 pv5 = sysService.findOne("SMSL", "01");
            if (pv5 != null) {
                maxnumb = Long.parseLong(pv5.getPmva());
            }
            System.out.println("最新的订单保存saveorder424 ");
            List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
            String stockJson = "";
            for (int i = 0; i < salesvoucherdetail.length; i++) {
                System.out.println("最新的订单保存saveorder4241 ");
                String isalesvoucherdetail = salesvoucherdetail[i];
                String[] detail = isalesvoucherdetail.split("&");
                System.out.println("最新的订单保存saveorder4242 ");
                Long itickettypeid = new Long(detail[2]);
                Long icrowdkindpriceid = new Long(detail[1]);
                Edmtickettypetab eticket = (Edmtickettypetab) stockswareDao.get(
                        Edmtickettypetab.class, itickettypeid);
                Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) stockswareDao
                        .get(Edmcrowdkindpricetab.class, icrowdkindpriceid);
                // 验证该价格是否能销售
                System.out.println("最新的订单保存saveorder4243 ");
                List winlimit = stockswareDao
                        .find(" from Ospticketwinlimitstab s where s.iticketwinid="
                                + s.getIticketwinid()
                                + " and s.icrowdkindpriceid="
                                + icrowdkindpriceid);
                if (winlimit == null || winlimit.size() == 0) {

					rs.addRow(new String[] { "false", eticket.getSztickettypename() + "的价格为"
							+ edmcrowdkindpricetab.getIcrowdkindpricecode() + "在本窗口不能销售" });
                    return rs;
                }
                System.out.println("最新的订单保存saveorder4245 ");
                List slimitst = stockswareDao
                        .find(" from Ospsaleslimitstab s where s.iemployeeid="
                                + s.getIhandler() + " and s.icrowdkindpriceid="
                                + icrowdkindpriceid);
                if (slimitst == null || slimitst.size() == 0) {

					rs.addRow(new String[] { "false", eticket.getSztickettypename() + "的价格为"
							+ edmcrowdkindpricetab.getIcrowdkindpricecode() + "在您不能销售" });
                    return rs;
                }
                System.out.println("最新的订单保存saveorder4246 ");
                String bymaketicketway = "00";
                String bymediatype = "00";
                String szticketprintno = "";
                Long piserialnum = new Long(0);
                Long iticketnum = new Long(detail[3]);
                sjcpnumb = sjcpnumb + iticketnum.intValue();
                if (detail.length > 6) {
                    bymaketicketway = detail[6];// 判断现场激活还是现场打印
                    bymediatype = detail[7];// 判断介质
                }
                System.out.println("最新的订单保存saveorder4247 ");
                if (bymaketicketway.equals("01") || bymaketicketway.equals("03")) {
                    // 李经锐修改 2012-09-05 增加判断 预制票 现场激活 还要判断介质类型
					if (eticket.getBymediatype().equals("00") || eticket.getBymediatype().equals("01")) {
                        szticketprintno = detail[8];
                    }

                    // piserialnum = new Long(szticketprintno.substring(6, 12));
                }
                System.out.println("最新的订单保存saveorder4248 ");
                String manyouno = "";
                String myzj = "";
                String myname = "";
                String mytelno = "";
                if (e.getBywintype().equals("0003")) {
                    // 窗口为年卡窗口
                    if (eticket.getBycategorytype().equals("0014")) {
						if (iticketnum > 1) {

							rs.addRow(new String[] { "false", eticket.getSztickettypename() + "只能单票销售" });
							return rs;
						}
                    } else {

                        rs.addRow(new String[] { "false", "年卡窗口只能办理年卡业务" });
                        return rs;

                    }
					if (detail.length < 13) {

						manyouno = detail[9];
						myzj = detail[10];

						if (myzj.equals("")) {

							rs.addRow(new String[] { "false", "年卡办理应填写有效证件" });
							return rs;
						}
					} else {
                        manyouno = detail[9];
                        myzj = detail[10];
                        myname = detail[11];
                        mytelno = detail[12];
                        if (myzj.equals("")) {

                            rs.addRow(new String[] { "false", "年卡办理应填写有效证件" });
                            return rs;
                        }
                        if (mytelno.equals("")) {

                            rs.addRow(new String[] { "false", "年卡办理应填写电话号码" });
                            return rs;
                        }
                        if (myname.equals("")) {

                            rs.addRow(new String[] { "false", "年卡办理应填写持卡人姓名" });
                            return rs;
                        }
                    }

                } else {
                    if (edmcrowdkindpricetab.getIpeoplenumrange().longValue() == 1) {
                        if (detail.length >= 12) {
                            myzj = detail[10];
                            myname = detail[11];
                            if (myzj.equals("")) {

                                rs.addRow(new String[] { "false", "实名制票务需输入证件号" });
                                return rs;
                            }
                            if (myname.equals("")) {

                                rs.addRow(new String[] { "false", "实名制票务填写姓名" });
                                return rs;
                            }
                        }
                    }
                }
                System.out.println("最新的订单保存saveorder4249 ");
//                stssalesvoucherdetailstab
                Stssalesvoucherdetailstab sd = saleCenterService
                        .saveStssalesvoucherdetailstab(s, isalesvoucherdetail,
                                szticketprintno);
                smont = smont + sd.getMeventmoney() - sd.getMderatemoney();

                sd.setManyouno(manyouno);
                sd.setMyzj(myzj);
                sd.setName1(myname);
                sd.setZjno1(mytelno);
                sd.setStritickettypeid(eticket.getSztickettypename());

                if (edmcrowdkindpricetab.getIpeoplenumrange().longValue() == 1) {
                    if (!sd.getMyzj().equals("")) {
                        boolean b = false;
                        for (int a = 0; a < zjlist.size(); a++) {
                            Stssoldtickettab zjstsv = (Stssoldtickettab) zjlist
                                    .get(a);
                            if (zjstsv.getMyzj().equals(sd.getMyzj())) {
                                if (zjstsv.getItickettypeid().longValue() == sd
                                        .getItickettypeid()) {
                                    if (zjstsv.getDtstartdate().equals(
                                            sd.getDtstartdate())) {
                                        b = true;
                                        zjstsv.setIplayerperticket(zjstsv
                                                .getIplayerperticket()
                                                + sd.getIticketnum());
                                        //判断证件限购数量
                                        Map<String,Integer> map = new HashMap<String, Integer>();
                                        map.put(sd.getMyzj(),zjstsv.getIplayerperticket().intValue());
                                        String message = CommonCheckUtil.checkLimitSaleByCard(map,1,sd.getDtstartdate());
                                        if(!StringUtils.isBlank(message)){
                                            rs.addRow(new String[] {message});
                                            return rs;
                                        }
                                    }
                                }
                            }
                        }
                        if (b == false) {
                            // 没有匹配的数据
                            Stssoldtickettab zjstsv = new Stssoldtickettab();
                            zjstsv.setItickettypeid(sd.getItickettypeid());
                            zjstsv.setDtstartdate(sd.getDtstartdate());
                            zjstsv.setMyzj(sd.getMyzj());
                            zjstsv.setIplayerperticket(new Long(1));
                            zjlist.add(zjstsv);
                            //判断证件限购数量
                            Map<String,Integer> map = new HashMap<String, Integer>();
                            map.put(sd.getMyzj(),zjstsv.getIplayerperticket().intValue());
                            String message = CommonCheckUtil.checkLimitSaleByCard(map,1,sd.getDtstartdate());
                            if(!StringUtils.isBlank(message)){
                                rs.addRow(new String[] {message});
                                return rs;
                            }
                        }
                    }
                }
                System.out.println("最新的订单保存saveorder42410 ");
                if (eticket.getByusage() == 0) {

                    sd.setIticketnum(iticketnum);
                    sd.setIticketplayer(iticketnum);
                } else {
                    sd.setIticketplayer(iticketnum);
                    sd.setIplayerperticket(iticketnum);// 人/张

                }
                detaillist.add(sd);
                // 凭证明细子表
                System.out.println("子票数据");
                zdetaillist = SaveStscomticketsalesdetailstab(zdetaillist, s,sd, comticketsalesdetails);
                // 座位数据保存列表
                System.out.println("子票座位数据");
                cseatlist = this.SaveSeatsaletab(cseatlist, sd, zdetaillist,eticket.getByusage());
                // 添加售出门票表
                if (eticket.getByusage() == 0) {
                    // 一票一人
                    if (bymaketicketway.equals("00")) {
                        // 现场打 印
                        cdetaillist = saleCenterService.saveStssoldtickettabug0make00(cdetaillist, sd,
                                        s, esbticketstation.getSzstationcode(),
                                        scenic.getSzsceniccode(),
                                        eticket.getSztickettypecode(),
                                        edmcrowdkindpricetab.getIcrowdkindid());

                    } else if (bymaketicketway.equals("01")) {
                        // 现场激活
                        cdetaillist = saleCenterService.saveStssoldtickettabug0make01(cdetaillist, sd,
                                        s, szticketprintno,
                                        edmcrowdkindpricetab.getIcrowdkindid());
                    } else if (bymaketicketway.equals("02")) {
                        // 身份证出票
                        cdetaillist = saleCenterService
                                .saveStssoldtickettabug0make00(cdetaillist, sd,
                                        s, esbticketstation.getSzstationcode(),
                                        scenic.getSzsceniccode(),
                                        eticket.getSztickettypecode(),
                                        edmcrowdkindpricetab.getIcrowdkindid());
                    }else if(bymaketicketway.equals("03")){//自选票号
                        cdetaillist = saleCenterService
                                .saveStssoldtickettabug0make01(cdetaillist, sd,
                                        s, szticketprintno,
                                        edmcrowdkindpricetab.getIcrowdkindid());
                    }
                } else if (eticket.getByusage() == 1) {
                    // 一票多人
                    if (bymaketicketway.equals("00")
                            || bymaketicketway.equals("02")
                            || bymaketicketway.equals("01")) {
                        // 现场打印

                        cdetaillist = saleCenterService.saveStssoldtickettabug1make00(cdetaillist, sd,
                                        s, esbticketstation.getSzstationcode(),
                                        scenic.getSzsceniccode(),
                                        eticket.getSztickettypecode(),
                                        edmcrowdkindpricetab.getIcrowdkindid());
                    } else {

                        // 现场激活
                        rs.addRow(new String[] {
                                "false",
                                "产品" + eticket.getSztickettypename()
                                        + "票，不支持一票多人" });
                        rs.addRow(new String[]{"false1","timecount_minus"});
                        return rs;
                    }
                }
                System.out.println("门票子表添加数据");
                // 添加售出门票子表
                if (eticket.getByusage() == 0) {
                    // 一票一人
                    cdzetaillist = saleCenterService
                            .SaveStssoldticketsubtabug0(cdzetaillist,
                                    zdetaillist, s, sd,
                                    edmcrowdkindpricetab.getIcrowdkindid());
                    cdchecklist = saleCenterService.SaveStsschecktabug0(
                            cdchecklist, zdetaillist, cdetaillist, s, sd,
                            edmcrowdkindpricetab.getIcrowdkindid());
                } else if (eticket.getByusage() == 1) {
                    // 一票多人
                    Debug.print("添加售出门票子表");
                    cdzetaillist = saleCenterService
                            .SaveStssoldticketsubtabug1(cdzetaillist,
                                    zdetaillist, s, sd,
                                    edmcrowdkindpricetab.getIcrowdkindid());
                    cdchecklist = saleCenterService.SaveStsschecktabug1(
                            cdchecklist, zdetaillist, cdetaillist, s, sd,
                            edmcrowdkindpricetab.getIcrowdkindid());
                }
                // 库存数据
                StockOrderInfo stockOrderInfo = new StockOrderInfo();
                stockOrderInfo.setOrid(s.getSzsalesvoucherno());
                stockOrderInfo.setProviderId(eticket.getIscenicid());
                stockOrderInfo.setProductId(itickettypeid);
                stockOrderInfo.setPriceId(Long.parseLong(detail[1]));
                stockOrderInfo.setUsid(s.getUsid());
                stockOrderInfo.setNumb(iticketnum);
                stockOrderInfo.setStockDate(detail[4].toString());
                stocks.add(stockOrderInfo);

            }
            System.out.println("最新的订单保存saveorder425 ");
            if (smont != iaccountreceivable) {
                rs.addRow(new String[] { "false", "实收款金额不对" });
                rs.addRow(new String[]{"false1","timecount_minus"});
                return rs;
            }

            // 预制票 售票时 操作个人结存明细信息
            boolean des = saleCenterService.addPersonaldetails(s.getIhandler(),
                    salesvoucherdetail);
            if (des) {
                rs.addRow(new String[] { "false", "售票员手中没售出票号或者售出票号与售票员手中票号冲突" });
                rs.addRow(new String[]{"false1","timecount_minus"});
                return rs;
            }
            Seatlocktab seatlock = null;
            System.out.println("最新的订单保存saveorder426 ");
            if (seatlockid > 0) {
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {

                    try {
                    	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
        				Object[] objects = client.invoke("updateseatlock", seatlockid, sjcpnumb);
        				ResultBean cano = (ResultBean) objects[0];
                        // 预留量更新
                        /*com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                                .updateseatlock(seatlockid, sjcpnumb);*/
                        String returnstats = cano.getResult(0, 0);
                        if (returnstats.equals("false")) {
                            rs.addRow(new String[] { "false",
                                    cano.getResult(0, 1) });
                            rs.addRow(new String[]{"false1","timecount_minus"});
                            return rs;
                        } else {
                            // 获取座位ID
                            seatids = cano.getResult(0, 1);
                        }
                    } catch (Exception e11) {
                        System.out.println(e11.getMessage());
                        rs.addRow(new String[] { "false", "连接中心服务器失败，不能读取锁定订单" });
                        rs.addRow(new String[]{"false1","timecount_minus"});
                        return rs;
                    }
                } else {

                    seatlock = (Seatlocktab) stockService.get(Seatlocktab.class,
                            seatlockid);
                    if (!seatlock.getStatus().equals("01")) {
                        rs.addRow(new String[] { "false",
                                "编号" + seatlockid + "的锁定单不是锁定状态,不能售票" });
                        rs.addRow(new String[]{"false1","timecount_minus"});
                    }

                    if (sjcpnumb > seatlock.getSeatcounts()) {
                        rs.addRow(new String[] { "false", "销售数量不能大于锁定座位数量" });
                        rs.addRow(new String[]{"false1","timecount_minus"});
                        return rs;
                    }

                }
            } else {
                if (haveseat1) {
                    System.out.println("最新的订单保存saveorder427 ");
                    if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                        System.out.println("最新的订单保存saveorder428 ");
                        try {
                        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            				Object[] objects = client.invoke("upshuijiseat", comticketsalesdetails);
            				ResultBean cano = (ResultBean) objects[0];
                            // 预留量更新
                            /*com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                                    .upshuijiseat(comticketsalesdetails);*/
                            String returnstats = cano.getResult(0, 0);
                            if (returnstats.equals("false")) {
                                rs.addRow(new String[] { "false",
                                        cano.getResult(0, 1) });
                                rs.addRow(new String[]{"false1","timecount_minus"});
                                return rs;
                            } else {
                                // 获取座位ID
                                seatids = cano.getResult(0, 1);
                            }
                        } catch (Exception e11) {
                            System.out.println(e11.getMessage());
                            rs.addRow(new String[] { "false",
                                    "连接中心服务器失败，不能读取锁定订单" });
                            rs.addRow(new String[]{"false1","timecount_minus"});
                            return rs;
                        }
                    }
                }
            }
            System.out.println("开始保存数据1");
            //保存库存数据
            if(stocks != null && !stocks.isEmpty()){
                String stockUsid = s.getUsid();
//                Custom c = (Custom) stockswareDao.get(Custom.class,stockUsid);
                Custom c = ecService.getCustomByUserId(stockUsid);
                if(c.getIbusinessid().longValue() == 2L && c.getUstp().equals("02") && c.getUsqx().startsWith("0111")){
                    stockUsid = c.getSusid();
                }else if(c.getIbusinessid().longValue() == 3L && c.getUstp().equals("02")){
                    stockUsid = c.getSusid();
                }
                for (StockOrderInfo stockOrderInfo : stocks){
                    stockOrderInfo.setUsid(stockUsid);
                }
                StockJson stockObject = new StockJson();
                stockObject.setStocks(stocks);
                stockJson = JSON.toJSONString(stockObject);
            }
            Sysparv5 sys = sysService.findOne("COMM","0006");
            if(sys == null){
                sys = new Sysparv5();
                sys.setIsvalue(1L);
            }
            if (WebContant.GetKeyValue("IsCenterUrl").equals("1") && sys.getIsvalue() == 1L) {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
    				Object[] objects = client.invoke("saveStock", stockJson, "true");
    				ResultBean cano = (ResultBean) objects[0];
                    /*com.ectrip.ticket.centersale.client.ResultBean cano = ssl.saveStock(stockJson, "true");*/
                    String status = cano.getResult(0,0);
					if (status.equalsIgnoreCase("false")) {
						rs.addRow(new String[] { "false", cano.getResult(0, 1) });
						rs.addRow(new String[] { "false1", "timecount_minus" });
						return rs;
					}
                } catch (Exception e11) {
                	LOGGER.info(StringUtil.toString_02(e11));
                    stockService.saveStockDetails(stocks, true);
                }
            }else{
                try{
                    stockService.saveStockDetails(stocks, true);
                }catch (Exception e1){
                	LOGGER.info(StringUtil.toString_02(e1));
                    rs.addRow(new String[] { "false",e1.getMessage() });
                    rs.addRow(new String[]{"false1","timecount_minus"});
                    return rs;
                }
            }
            //拉卡拉，保存流水号
            if(salesvoucher.length>13){
                String zfid = salesvoucher[13];// 支付方式
                if(zffs.equals("02") || zffs.equals("30") || zffs.equals("31") || zffs.equals("32") || zffs.equals("33") || zffs.equals("51")){//微信，支付宝支付
                    PaymentBill bill = (PaymentBill) stockService.get(PaymentBill.class,zfid);
                    if(bill != null){
                        bill.setOrid(szsalesvoucherno);
                    }else{
                        throw new RuntimeException("没有对应第三方支付记录");
                    }
                    PaymentOrder paymentOrder = (PaymentOrder) stockService.get(PaymentOrder.class,zfid);
                    if(paymentOrder != null){
                        if(paymentOrder.getDdzt().equals("00")){
                            paymentOrder.setDdzt("01");
                        }else{
                            throw new RuntimeException("非未出票第三方支付订单不可出票");
                        }
                    }else{
                        throw new RuntimeException("没有对应的第三方支付订单信息");
                    }
                    stockService.update(bill);
                    stockService.update(paymentOrder);
                }
            }
            this.saveorder(s, st, detaillist, cdetaillist, cseatlist,
                    zdetaillist, cdzetaillist, cdchecklist, seatlockid, seatids);
            System.out.println("开始保存数据8");
            rs.addRow(new String[] { "true",
                    s.getId().getIsalesvoucherid().toString() });

        } catch (Exception e) {
            if (seatlockid > 0) {
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                    	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
        				Object[] objects = client.invoke("updatehfseatlock", seatlockid);
        				ResultBean cano = (ResultBean) objects[0];
                        // 预留量更新
                        /*ssl.updatehfseatlock(seatlockid);*/

                    } catch (Exception e11) {
                        throw new RuntimeException(e11.getMessage());
                    }
                }
            } else {
                if (haveseat1) {
                    if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {

                        try {
                        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                            // 预留量更新
                            if (!seatids.equals("")) {
                               client.invoke("uphfshuijiseat", comticketsalesdetails,seatids);
                            }
                        } catch (Exception e11) {
                            throw new RuntimeException(e11.getMessage());
                        }
                    }
                }
                e.printStackTrace();
                System.out.println("save42,Exception" + e.toString());
                throw new RuntimeException(e.getMessage());
            }
        }
        return rs;
    }
    public synchronized boolean saveorder(Stssalesvouchertab s,
                                          Stssalessettlementtab st, List detaillist, List cdetaillist,
                                          List cseatlist, List zdetaillist, List cdzetaillist,
                                          List cdchecklist, Long seatlockid, String seatids) throws Exception {
        System.out.println("保存数据开始");
        
        //添加订单来源 ck线下售票触摸屏订单标识
        s.setOrdersource("ck");
        stockService.save(s);
        stockService.save(st);
        System.out.println("开始保存数据2");
        for (int i = 0; i < detaillist.size(); i++) {
            Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
                    .get(i);
            if(sd.getIticketnum().intValue() == 0){//删除数量为0的产品
                detaillist.remove(i);
                i--;
            }else{
                stockService.save(sd);
            }

        }
        System.out.println("开始保存数据3");
        Sysparv5 sv5 =  sysService.findOne("PRCS", "01");
        /*Sysparv5 sv5 = (Sysparv5) stockService.get(Sysparv5.class,
                new Sysparv5Id("PRCS", "01"));*/
        String printcs = "0";
        if (sv5 != null) {
            printcs = sv5.getPmva();
        }
        System.out.println("开始保存数据4");
        for (int i = 0; i < cdetaillist.size(); i++) {
            Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist.get(i);
            stockService.save(stsv);

            String hsql = "from TicketPrintNo where ticketPrintno = '"+stsv.getSzticketprintno()+"' ";
            List lst = this.stockService.find(hsql);
            if(lst != null && !lst.isEmpty()){
                TicketPrintNo tp = (TicketPrintNo) lst.get(0);
                tp.setStatus(-1L);
                this.stockService.update(tp);
            }

            if (stsv.getBymaketicketway().equals("00")) {
                if (s.getBysalesvouchertype().equals("01")) {
                    if (printcs.equals("1")) {
                        Ticketprintlist t = new Ticketprintlist();
                        t.setSzsalesvoucherno(s.getSzsalesvoucherno());
                        t.setIemployeeid(s.getIhandler());
                        t.setPrinttype("01");
                        t.setSzticketprintno(stsv.getSzticketprintno());
                        t.setPrinttime(Tools.getDayTimes());
                        t.setIsok(new Long(0));
                        try {
                            Long printid = stockService.getSequenceId("PRINT_ID");
                            t.setPrintid(printid);
                            stockService.save(t);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            System.out.println(e1.getMessage());
                        }
                    }
                }
            }
        }
        System.out.println("开始保存数据5");
        for (int i = 0; i < zdetaillist.size(); i++) {
            Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                    .get(i);
            if(zstd.getIsplitamount().intValue() == 0){//删除数量为0的数据
                zdetaillist.remove(i);
                i--;
            }else{
            	stockService.save(zstd);
            }
        }
        if (WebContant.GetKeyValue("IsCenterUrl").equals("0")) {
            // 没有中心服务器 数据直接保存
            try {
                for (int i = 0; i < cseatlist.size(); i++) {
                    Seatsaletab cseat = (Seatsaletab) cseatlist.get(i);
                    Seatstatustab stst = new Seatstatustab();
                    SeatstatustabId ststid = new SeatstatustabId();
                    ststid.setIvenueid(cseat.getIvenueid());
                    ststid.setIvenueareaid(cseat.getIvenueareaid());
                    ststid.setItripid(cseat.getItripid());
                    ststid.setIseatid(cseat.getIseatid());
                    ststid.setStartdate(cseat.getStartdate());
                    stst.setStatus(1L);
                    stst.setId(ststid);
                    stst.setDtmakedate(Tools.getDayTimes());
                    Seatstatustab stst2 = (Seatstatustab) stockService.get(Seatstatustab.class, ststid);
                    if(stst2!=null){
                        throw new RuntimeException("座位已售出，请重新预定");
                    }
                    stockService.save(stst);

                }
                if (seatlockid == 0) {
                    try {
                        boolean flag = this.saveshuijiseat(zdetaillist);
                        if (!flag) {
                            throw new RuntimeException("座位数不够");
                        }
                    } catch (Exception e1) {
                        System.out.println("save42,Exception1" + e1.toString());
                        throw new RuntimeException(e1.getMessage());
                    }
                } else {
                    try {
                        this.saveshuijiseat(zdetaillist, seatlockid);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                        throw new RuntimeException(e1.getMessage());
                    }
                }
            } catch (Exception e1) {
                System.out.println("save42,Exception" + e1.toString());
                throw new RuntimeException("有座位已售出，请重新预定");

            }
        } else {

            this.saveshuijiseat(zdetaillist, seatids);

        }
        System.out.println("保存座位数据");
        for (int i = 0; i < cseatlist.size(); i++) {
            Seatsaletab cseat = (Seatsaletab) cseatlist.get(i);
            System.out.println("座位号：" + cseat.getIseatid());
            stockService.save(cseat);
        }
        System.out.println("开始保存数据6");
        for (int i = 0; i < cdzetaillist.size(); i++) {

            Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
                    .get(i);

            stockService.save(stss);
        }
        for (int i = 0; i < cdchecklist.size(); i++) {

            Stsschecktab stss = (Stsschecktab) cdchecklist.get(i);

            stockService.save(stss);
        }

        System.out.println("保存数据结束");
        return true;
    }

    /**
     * 凭证明细表
     *
     * @param zdetaillist
     *            凭证明细表中票号
     * @param s
     * @param sd
     * @param comticketsalesdetails
     *            s * @return
     * @throws Exception 
     */
    public List SaveStscomticketsalesdetailstab(List zdetaillist,
                                                Stssalesvouchertab s, Stssalesvoucherdetailstab sd,
                                                String comticketsalesdetails) throws Exception {
        String[] comticketsalesdetail = comticketsalesdetails.split(":");

        // 2014-06-28 lijingrui修改 是否允许自定义价格
        Double mactualsaleprice = sd.getMactualsaleprice();
        Double price = 0D; // 子票价格
        boolean b = false;
        Edmcrowdkindpricetab edmprice = null;
        Edmticketproduct tp = (Edmticketproduct) this.stockswareDao.get(
                Edmticketproduct.class, sd.getItickettypeid());
        if (tp != null) {
            if (tp.getInoteger4() != null && tp.getInoteger4() != 0) {
                b = true;
                edmprice = (Edmcrowdkindpricetab) this.stockswareDao.get(
                        Edmcrowdkindpricetab.class, sd.getIcrowdkindpriceid());
            }
        }
        
        for (int i = 0; i < comticketsalesdetail.length; i++) {
            String[] zdetail = comticketsalesdetail[i].split("&");
            Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
            StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
            Long isalesvoucherdetailsid = new Long(zdetail[0]);
            if (isalesvoucherdetailsid.longValue() == sd.getId()
                    .getIsalesvoucherdetailsid()) {
                Long icrowdkindpriceid = new Long(zdetail[1]);
                Long itickettypeid = new Long(zdetail[2]);
                Long iztickettypeid = new Long(zdetail[3]);
                Long isplitamount = new Long(zdetail[4]);
                Long tripid = new Long(zdetail[5]);
                Long ivenueid = new Long(zdetail[6]);
                Long ivenueareaid = new Long(zdetail[7]);
                String dtstartdate = zdetail[8];
                String dtenddate = zdetail[9];
                Long iprogramid = 0L;
                String seats = "";
                long timeid = 0;
                String productid = null;
                String timeStart = null;
                String timeEnd = null; 
                System.out.println("zdetail.length=" + zdetail.length);
                if (zdetail.length >= 11) {
                    iprogramid = new Long(zdetail[10]);
                }
                if (zdetail.length >= 12) {
                    seats = zdetail[11];
                }
                //判断分时预约
                if(zdetail.length == 14) {
                	timeid = Long.parseLong(zdetail[12]);
                	productid = zdetail[13];
                	String hql = "from TimeSharingTicketTab where id = "+ timeid +" and productId = '"+productid+"'";
                	List find = timeSharingService.find(hql);
                	if(find != null && find.size() > 0) {
                		TimeSharingTicketTab timeSharingTicketTab = (TimeSharingTicketTab) find.get(0);
                		timeStart = timeSharingTicketTab.getStartDate();
                		timeEnd = timeSharingTicketTab.getEndDate();
                		timeSharingService.UpdateStock(timeSharingTicketTab.getId(), timeSharingTicketTab.getProductId(), isplitamount.intValue(), "minus");
                	}
                }
                /**
                 * 判断产品是按子产品自己有效期还是套票有效期
                 */
                Edmticketproduct et = (Edmticketproduct) stockswareDao.get(
                        Edmticketproduct.class, itickettypeid);
                if (et == null) {
                    dtstartdate = sd.getDtstartdate();
                    dtenddate = sd.getDtenddate();
                } else {
                    if (et.getInoteger1() == null || et.getInoteger1() == 0) {
                        dtstartdate = sd.getDtstartdate();
                        dtenddate = sd.getDtenddate();
                    }
                }
                zstdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                zstdid.setIticketstationid(s.getId().getIticketstationid());
                zstdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
                zstd.setIcrowdkindpriceid(icrowdkindpriceid);
                zstd.setItickettypeid(itickettypeid);
                zstd.setIztickettypeid(iztickettypeid);
                zstd.setMhandcharge(new Double(0));
                zstd.setDtmakedate(Tools.getDayTimes());
                zstd.setTripid(tripid);
                zstd.setIvenueareaid(ivenueareaid);
                zstd.setIvenueid(ivenueid);
                zstd.setIvenueseatsid(iprogramid);
                zstd.setSeatsid(seats);
                zstd.setTimeStart(timeStart);
                zstd.setTimeEnd(timeEnd);
                zstd.setTimeId(timeid);
                // 根据节目 场馆 趟次 查找排班数据，读取 节目演出时间
                if (iprogramid > 0) {
                    try {
                        List<Map> tlist = this.stockswareDao
                                .findBySqlToMap("select td.starttime,td.endtime,td.iadvanceminute,td.ilagminute,t.itripprdcontrolid from Tripprdcontroltab t,Tripprdcontroldetailtab td where t.iprogramid="
                                        + iprogramid
                                        + " and t.ivenueid="
                                        + ivenueid
                                        + "  and  t.itripprdcontrolid=td.itripprdcontrolid and t.startdata<='"
                                        + dtstartdate
                                        + "' and t.enddata>='"
                                        + dtstartdate
                                        + "' and td.itripid= "
                                        + tripid);
                        Map map = tlist.get(0);
                        dtstartdate = sd.getDtstartdate();
                        dtenddate = sd.getDtenddate();

                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                        Calendar startTime = Calendar.getInstance();
                        startTime.setTime(sdf.parse(dtstartdate + " "
                                + map.get("STARTTIME") + ":00"));
						
					    /*startTime.add( Calendar.MINUTE,
					    Integer.parseInt(map.get("IADVANCEMINUTE").toString()) * -1);*/
						 
                        zstd.setDtstartdate(sdf.format(startTime.getTime()));

                        Calendar endTime = Calendar.getInstance();
                        endTime.setTime(sdf.parse(dtenddate + " "
                                + map.get("ENDTIME") + ":59"));
						
						  /*endTime.add(Calendar.MINUTE,
						 Integer.parseInt(map.get( "ILAGMINUTE").toString()));*/
						 
                        zstd.setDtenddate(sdf.format(endTime.getTime()));

                        zstd.setItripprdcontrolid(new Long(map.get(
                                "ITRIPPRDCONTROLID").toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Edmcrowdkindpricetab ep = (Edmcrowdkindpricetab) this.stockswareDao
                            .get(Edmcrowdkindpricetab.class, icrowdkindpriceid);
                    if (Tools.getTodayString().equals(dtstartdate)) {
                        Edmtickettypetab eticket = (Edmtickettypetab) this.stockswareDao
                                .get(Edmtickettypetab.class, itickettypeid);
                        if (eticket.getInt1() != null && eticket.getInt1() == 1) {
                            // 有效开始时间已当前时间开始
                            String nowtime = Tools.getNowTime();
                            zstd.setDtstartdate(dtstartdate + " " + nowtime);
                            zstd.setDtenddate(Tools.getDate(dtenddate, 1) + " "
                                    + nowtime);

                        } else {
                            zstd.setDtstartdate(dtstartdate + " 00:00:00");
                            zstd.setDtenddate(dtenddate + " 23:59:59");
                            if (ep.getInote2() != null && ep.getInote2() == 1) {
                                if (Tools.getDayNumb(dtenddate, ep.getEnddata()) < 1) {

                                    zstd.setDtenddate(ep.getEnddata()
                                            + " 23:59:59");
                                }
                            }
                        }
                    } else {
                        zstd.setDtstartdate(dtstartdate + " 00:00:00");
                        zstd.setDtenddate(dtenddate + " 23:59:59");
                        if (ep.getInote2() != null && ep.getInote2() == 1) {
                            if (Tools.getDayNumb(dtenddate, ep.getEnddata()) < 1) {

                                zstd.setDtenddate(ep.getEnddata() + " 23:59:59");
                            }
                        }
                    }
                }
                zstd.setIversion(new Long(0));
                List list = stockswareDao.find(" from Edmticketcomposepricetab where id.icrowdkindpriceid="
                                + icrowdkindpriceid
                                + " and itickettypeid='"
                                + iztickettypeid + "'");
                Edmticketcomposepricetab edt = (Edmticketcomposepricetab) list
                        .get(0);
                zstdid.setIcomticketsalesdetailsid(edt.getId()
                        .getIticketcomposepriceid());
                zstd.setId(zstdid);
                zstd.setIsplitamount(isplitamount * edt.getNumb());

                // 允许自定义价格 2014-06-28
                if (b) {
                    // 根据子票价格所占百分比来获取更改后的子票价格
                    Double jiage = 0D;
                    if (i == comticketsalesdetail.length - 1) {
                        jiage = sd.getMactualsaleprice() - price;
                    } else {
                        if(edmprice.getMactualsaleprice().doubleValue() == 0){
                            jiage = 0D;
                        }else{
                            jiage = Double.parseDouble(String.format(
                                    "%.2f",
                                    edt.getMactualsaleprice()
                                            / edmprice.getMactualsaleprice()
                                            * sd.getMactualsaleprice()));
                        }

                        price += jiage;
                    }

                    zstd.setMsplitprice(jiage);
                    zstd.setMsplitmoney(jiage * edt.getNumb() * isplitamount);
                    zstd.setMderatemoney(sd.getIderatenums() * jiage);

                } else {
                    zstd.setMsplitprice(edt.getMactualsaleprice());
                    zstd.setMsplitmoney(edt.getMactualsaleprice()
                            * edt.getNumb() * isplitamount);
                    zstd.setMderatemoney(sd.getIderatenums()
                            * edt.getMactualsaleprice());
                }

                zstd.setIderatenums(sd.getIderatenums());

                zdetaillist.add(zstd);
            }
        }
        return zdetaillist;
    }

    /**
     * 凭证明细表
     *
     */
    public List SaveSeatsaletab(List cseatlist, Stssalesvoucherdetailstab sd,
                                List zdetaillist, Long byusage) {
        if (byusage == 0) {
            // 一票一人
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                        .get(i);
                if (zstd.getId().getIsalesvoucherdetailsid().longValue() == sd
                        .getId().getIsalesvoucherdetailsid().longValue()) {
                    if (zstd.getIvenueseatsid() > 0) {
                        // 保存有节目
                        String seats = zstd.getSeatsid();
                        if (seats != null && !seats.equals("")) {
                            String[] seatids = seats.split(">");
                            for (int j = 1; j <= zstd.getIsplitamount(); j++) {
                                Seatsaletab seat = new Seatsaletab();
                                SeatsaletabId seatid = new SeatsaletabId();
                                seatid.setSeq(new Long(j));
                                seatid.setSzsoldticketid(new Long(j));
                                seatid.setIsalesvoucherid(zstd.getId()
                                        .getIsalesvoucherid());
                                seatid.setIticketstationid(zstd.getId()
                                        .getIticketstationid());
                                seatid.setIsalesvoucherdetailsid(zstd.getId()
                                        .getIsalesvoucherdetailsid());
                                seatid.setIcomticketsalesdetailsid(zstd.getId()
                                        .getIcomticketsalesdetailsid());
                                seat.setId(seatid);
                                seat.setIprogramid(zstd.getIvenueseatsid());
                                seat.setItripid(zstd.getTripid());
                                seat.setIvenueid(zstd.getIvenueid());
                                seat.setIvenueareaid(zstd.getIvenueareaid());
                                seat.setIseatid(new Long(seatids[j - 1]));
                                seat.setDtmakedate(Tools.getDayTimes());
                                seat.setIsvalid(1L);
                                seat.setItripprdcontrolid(zstd
                                        .getItripprdcontrolid());
                                seat.setStartdate(zstd.getDtstartdate()
                                        .substring(0, 10));
                                cseatlist.add(seat);
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                        .get(i);
                if (zstd.getId().getIsalesvoucherdetailsid().longValue() == sd
                        .getId().getIsalesvoucherdetailsid().longValue()) {
                    if (zstd.getIvenueseatsid() > 0) {
                        // 保存有节目
                        String seats = zstd.getSeatsid();
                        if (seats != null && !seats.equals("")) {
                            String[] seatids = seats.split(">");
                            for (int j = 1; j <= zstd.getIsplitamount(); j++) {
                                Seatsaletab seat = new Seatsaletab();
                                SeatsaletabId seatid = new SeatsaletabId();
                                seatid.setSeq(new Long(j));
                                seatid.setSzsoldticketid(new Long(1));
                                seatid.setIsalesvoucherid(zstd.getId()
                                        .getIsalesvoucherid());
                                seatid.setIticketstationid(zstd.getId()
                                        .getIticketstationid());
                                seatid.setIsalesvoucherdetailsid(zstd.getId()
                                        .getIsalesvoucherdetailsid());
                                seatid.setIcomticketsalesdetailsid(zstd.getId()
                                        .getIcomticketsalesdetailsid());
                                seat.setId(seatid);
                                seat.setIprogramid(zstd.getIvenueseatsid());
                                seat.setItripid(zstd.getTripid());
                                seat.setIvenueid(zstd.getIvenueid());
                                seat.setIvenueareaid(zstd.getIvenueareaid());
                                seat.setIseatid(new Long(seatids[j - 1]));
                                seat.setDtmakedate(Tools.getDayTimes());
                                seat.setItripprdcontrolid(zstd
                                        .getItripprdcontrolid());
                                seat.setStartdate(zstd.getDtstartdate()
                                        .substring(0, 10));
                                cseatlist.add(seat);

                            }
                        }
                    }
                }
            }
        }
        System.out.println(cseatlist.size());
        return cseatlist;
    }

    public ResultBean updatetdseatstuts(String Seatstatustablists) {

        String[] comticketsalesdetail = Seatstatustablists.split(":");
        for (int i = 0; i < comticketsalesdetail.length; i++) {
            String[] zdetail = comticketsalesdetail[i].split("&");

            Long ivenueid = new Long(zdetail[0]);// 场馆ID
            Long ivenueareaid = new Long(zdetail[1]);// 场馆区域ID
            Long itripid = new Long(zdetail[2]);// 场次ID
            Long iseatid = new Long(zdetail[3]);// 座位ID
            String startdate = zdetail[4];// 日期
            SeatstatustabId ststid = new SeatstatustabId();
            ststid.setIvenueid(ivenueid);
            ststid.setIvenueareaid(ivenueareaid);
            ststid.setStartdate(startdate);
            ststid.setItripid(itripid);
            ststid.setIseatid(iseatid);
            Seatstatustab stst = (Seatstatustab) this.stockService.get(
                    Seatstatustab.class, ststid);
            if (stst != null) {
                this.stockService.delete(stst);
            }

        }

        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        rs.addRow(new String[] { "true", "座位预定成功" });
        return rs;

    }

    public ResultBean getprogrambyproductid(Long iproductid, String stdt,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("WWWDOMAIN");
    	}
        StringBuffer sql = new StringBuffer();
        sql.append("select  t.iproductid,t.ivenueareaid,tc.ivenueid,n.iprogramid,n.szprogramname,n.szprogramdeac,n.bywebsaletype,n.bycashsaletype from Tripprdsaletab t ,Tripprdcontroltab tc,venueprogram n where  t.iproductid="
                + iproductid
                + " and t.itripprdcontrolid=tc.itripprdcontrolid and tc.startdata<='"
                + stdt
                + "' and tc.enddata>='"
                + stdt
                + "' and tc.byisuse=1  and n.iprogramid=tc.iprogramid and n.byisuse=1");
        List<Map> list = new ArrayList();
        try {
            list = stockService.findBySqlToMap(sql.toString());

            for (Map map : list) {
                Long iprogramid = new Long(map.get("IPROGRAMID").toString());
                Long ivenueid = new Long(map.get("IVENUEID").toString());
                // 读取一张图片
                List<Map> list1 = stockService
                        .findBySqlToMap("select u.upadder,u.upfilename from Venueprogrampic p,Upfile u where p.iprogramid="
                                + iprogramid
                                + " and p.upid=u.upid order by p.upid ");
                if (list1.size() > 0) {
                    Map map1 = (Map) list1.get(0);
                    map.put("programpic",
                                    url
                                    + map1.get("UPADDER").toString()
                                    + map1.get("UPFILENAME").toString());
                } else {
                    map.put("programpic", "");
                }

                // 读取排班数据
                List<Map> list2 = stockService
                        .findBySqlToMap("select t.itripprdcontrolid,t.ivenueid,v.venueidname,td.itripid,tr.tripname,td.starttime from Tripprdcontroldetailtab td,Tripprdcontroltab t,Venue v,trip tr where t.iprogramid="
                                + iprogramid
                                + " and t.ivenueid="
                                + ivenueid
                                + " and  td.itripprdcontrolid=t.itripprdcontrolid and  t.ivenueid=v.ivenueid and t.byisuse=1 and td.byisuse=1 and td.itripid=tr.tripid and t.startdata<='"
                                + stdt
                                + "' and t.enddata>='"
                                + stdt
                                + "' order by td.starttime");
                StringBuffer detail = new StringBuffer();
                for (Map map2 : list2) {
                    detail.append(map2.get("ITRIPPRDCONTROLID").toString());
                    detail.append("#");
                    detail.append(map2.get("IVENUEID").toString());
                    detail.append("#");
                    detail.append(map2.get("VENUEIDNAME").toString());
                    detail.append("#");
                    detail.append(map2.get("ITRIPID").toString());
                    detail.append("#");
                    detail.append(map2.get("TRIPNAME").toString());
                    detail.append("#");
                    detail.append(map2.get("STARTTIME").toString());
                    detail.append("#");
                    detail.append(map2.get("IADVANCEMINUTE").toString());
                    detail.append("#");
                    detail.append(map2.get("ILAGMINUTE").toString());
                    detail.append("!");
                }
                String trips = detail.toString();
                if (!trips.equals("")) {
                    trips = trips.substring(0, trips.length() - 1);
                }
                map.put("trips", trips);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(list);
    }

    public ResultBean Getareaseatstusts(Long ivenueid, Long ivenueareaid,
                                        String stdt, Long tripid) {
        List<Map> list = new ArrayList();
        try {
            list = stockService
                    .findBySqlToMap("select s.iseatid,s.ivenueid,s.ivenueareaid,s.itripid,s.startdate,s.status from Seatstatustab s where  s.startdate='"
                            + stdt
                            + "' and  s.ivenueid="
                            + ivenueid
                            + " and s.ivenueareaid= "
                            + ivenueareaid
                            + " and s.itripid=" + tripid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(list);
    }

    public ResultBean savetorder42(T_order t_order, List listorder,
                                   List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,
                                   List trlist, String param1) throws Exception {
    	ResultBean rs = new ResultBean();
        try {
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			Stssalesvouchertab s = saleCenterService.savetStssalesvouchertab(
			        t_order, iemployeeid, maxid, iticketwinid, param1);
			s.setOrdersource("nck");
			Esbscenicareatab scenic = (Esbscenicareatab) stockService.get(
			        Esbscenicareatab.class, new Long(t_order.getIscenicid()));
			Stssalessettlementtab st = saleCenterService
			        .savetStssalessettlementtab(s, t_order, param1);
			Esbticketwintab e = (Esbticketwintab) stockService.get(
			        Esbticketwintab.class, s.getIticketwinid());
			Esbticketstationtab esbticketstation = (Esbticketstationtab) stockService
			        .get(Esbticketstationtab.class, e.getIticketstationid());
			// 是否允许过期出票
			/*Sysparv5 sys1 = (Sysparv5) this.stockService.find(
			        " from Sysparv5 where id.pmky='SFGQ' and id.pmcd='1' ")
			        .get(0);*/
			Sysparv5 sys1 = sysService.findOne("SFGQ", "1");
			for (int i = 0; i < listzorder.size(); i++) {
			    T_zorderlist zlist = (T_zorderlist) listzorder.get(i);
			    Edmtickettypetab edticket = (Edmtickettypetab) stockService.get(
			            Edmtickettypetab.class, new Long(zlist.getItickettypeid()));
			    if(sys1.getPmva().equals("1")){
			        if (Tools.getDayTimes().compareTo(zlist.getDtenddate()) > 0) {

			            rs.addRow(new String[] { "false",
			                    "产品" + edticket.getSztickettypename() + "已过期" });
			            return rs;
			        }
			    }
			    if (scenic.getScenictype().equals("01")) {
			        /*Sysparv5 sv5 = (Sysparv5) stockService.get(Sysparv5.class,
			                new Sysparv5Id("OFCK", "****"));*/
			        Sysparv5 sv5 = sysService.findOne("OFCK", "****");
			        if (sv5 != null && sv5.getPmva().equals("true")) {

			        } else {
						List opwwlist = stockService.find(" from Opwwicketsettab where itickettypeid="
								+ zlist.getItickettypeid() + " and izticktypeid=" + zlist.getIztickettypeid());
			            if (opwwlist.size() == 0) {
							rs.addRow(new String[] { "false", "产品" + edticket.getSztickettypename() + "没有检票园门，不能出票" });
			                return rs;
			            }

			        }

			    }

			}
			// stockService.save(st);
			List detaillist = new ArrayList();
			List cdetaillist = new ArrayList();
			List zdetaillist = new ArrayList();
			List cdzetaillist = new ArrayList();
			List cdchecklist = new ArrayList();
			List seatlist = new ArrayList();
			Long szsoldticketid = new Long(1);
			for (int i = 0; i < listorder.size(); i++) {
			    T_orderlist tlist = (T_orderlist) listorder.get(i);
			    // 售出凭证明细
			    Stssalesvoucherdetailstab sd = saleCenterService
			            .savetStssalesvoucherdetailstab(s, tlist);
			    Edmtickettypetab eticket = (Edmtickettypetab) stockService.get(
			            Edmtickettypetab.class, new Long(tlist.getItickettypeid()));
			    if (eticket.getBymaketicketway().equals("00")) {
					List printlist = this.stockService.find(" from Priceprintmanager where id.icrowdkindpriceid="
							+ tlist.getIcrowdkindpriceid() + " order by ordernum");
			        if (printlist == null || printlist.size() == 0) {
			            Edmcrowdkindpricetab ep = (Edmcrowdkindpricetab) this.stockService
			                    .get(Edmcrowdkindpricetab.class,
			                            new Long(tlist.getIcrowdkindpriceid()));
			            printlist = this.stockService
			                    .find("from Printticketmanage where id.iscenicid="
			                            + eticket.getIscenicid()
			                            + " and id.ibusinessid=" + ep.getIbusinessid());
			            if (printlist == null || printlist.size() == 0) {
							rs.addRow(new String[] { "false", "编号" + eticket.getSztickettypename() + "产品价格没有打印设置不能出票" });
			                return rs;
			            }
			        }
			    }

			    if (eticket.getByusage() == 0) {
 			        sd.setIticketnum(new Long(tlist.getNumb()));
			        sd.setIticketplayer(new Long(tlist.getNumb()));
			    } else {
			        sd.setIplayerperticket(new Long(tlist.getNumb()));
			        sd.setIticketplayer(new Long(tlist.getNumb()));
			    }
			    detaillist.add(sd);
			    // 售出凭证子明细
			    // 添加售出门票表
			    zdetaillist = saleCenterService.SavetStscomticketsalesdetailstab(
			            zdetaillist, s, tlist, listzorder);
			    seatlist = this.SavetSeatsaletab(seatlist, s, tlist, listzorder,
			            eticket.getByusage());
			    if (eticket.getByusage() == 0) {
			        // 一票一人
			        if (eticket.getBymaketicketway().equals("00")
			                || eticket.getBymaketicketway().equals("02")) {
			            // 现场打 印
			            cdetaillist = saleCenterService
			                    .saveStssoldtickettabug0make00(cdetaillist, sd, s,
			                            esbticketstation.getSzstationcode(), scenic
			                                    .getSzsceniccode(), eticket
			                                    .getSztickettypecode(), new Long(
			                                    tlist.getIcrowdkindid()));
			            Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) stockService
			                    .get(Edmcrowdkindpricetab.class,
			                            sd.getIcrowdkindpriceid());
			            if (trlist != null && trlist.size() > 0) {

			                for (int w = 0; w < cdetaillist.size(); w++) {
			                    Stssoldtickettab soldticket = (Stssoldtickettab) cdetaillist
			                            .get(w);
			                    if (t_order.getDyusid() == null
			                            || t_order.getDyusid().equals("")) {
			                        soldticket.setDyusid("daoyou");
			                    }
			                    for (int r = 0; r < trlist.size(); r++) {
			                        Trealname realname = (Trealname) trlist.get(r);
			                        if (Long.parseLong(realname.getIcrowdkindid()) == soldticket
			                                .getIcrowdkindid().longValue()
			                                && Long.parseLong(realname
			                                .getItickettypeid()) == soldticket
			                                .getItickettypeid().longValue()) {
			                            soldticket.setMyzj(realname.getIdcard());
			                            soldticket.setName1(realname.getCname());
			                            trlist.remove(r);
			                            break;
			                        }
			                    }
			                }
			            }

			        } else if (eticket.getBymaketicketway().equals("01")) {
			            // 现场激活
			            cdetaillist = saleCenterService
			                    .saveStssoldtickettabug0make01(cdetaillist, sd, s,
			                            tlist.getSzticketprintno(),
			                            new Long(tlist.getIcrowdkindid()));
			        }
			    } else if (eticket.getByusage() == 1) {
			        // 一票多人
			        if (eticket.getBymaketicketway().equals("00")
			                || eticket.getBymaketicketway().equals("02")
			                || eticket.getBymaketicketway().equals("01")) {
			            // 现场打印
			            cdetaillist = saleCenterService
			                    .saveStssoldtickettabug1make00(cdetaillist, sd, s,
			                            esbticketstation.getSzstationcode(), scenic
			                                    .getSzsceniccode(), eticket
			                                    .getSztickettypecode(), new Long(
			                                    tlist.getIcrowdkindid()));
			        } else {
			            // 现场激活
						rs.addRow(new String[] { "false", "产品" + eticket.getSztickettypename() + "激活票，不支持一票多人" });
			            return rs;
			        }
			    }
			    if (eticket.getByusage() == 0) {
			        // 一票一人
			        cdzetaillist = saleCenterService.SaveStssoldticketsubtabug0(
			                cdzetaillist, zdetaillist, s, sd,
			                new Long(tlist.getIcrowdkindid()));
			        cdchecklist = saleCenterService.SaveStsschecktabug0(
			                cdchecklist, zdetaillist, cdetaillist, s, sd, new Long(
			                        tlist.getIcrowdkindid()));
			    } else if (eticket.getByusage() == 1) {
			        // 一票多人
			        cdzetaillist = saleCenterService.SaveStssoldticketsubtabug1(
			                cdzetaillist, zdetaillist, s, sd,
			                new Long(tlist.getIcrowdkindid()));
			        cdchecklist = saleCenterService.SaveStsschecktabug1(
			                cdchecklist, zdetaillist, cdetaillist, s, sd, new Long(
			                        tlist.getIcrowdkindid()));
			    }
			}
			stockService.save(s);
			stockService.save(st);
			for (int i = 0; i < detaillist.size(); i++) {
			    Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
			            .get(i);
			    stockService.save(sd);
			}
			/*Sysparv5 sv5 = (Sysparv5) stockService.get(Sysparv5.class,
			        new Sysparv5Id("PRCS", "01"));*/
			Sysparv5 sv5 = sysService.findOne("PRCS", "01");
			String printcs = "0";
			if (sv5 != null) {
			    printcs = sv5.getPmva();
			}
			for (int i = 0; i < cdetaillist.size(); i++) {
			    Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist.get(i);
			    stockService.save(stsv);
			    if (stsv.getBymaketicketway().equals("00")) {
			        if (printcs.equals("1")) {
			            Ticketprintlist t = new Ticketprintlist();
			            t.setIemployeeid(iemployeeid);
			            t.setSzsalesvoucherno(s.getSzsalesvoucherno());
			            t.setPrinttype("01");
			            t.setSzticketprintno(stsv.getSzticketprintno());
			            t.setPrinttime(Tools.getDayTimes());
			            t.setIsok(new Long(0));
			            Long printid = stockService.getSequenceId("PRINT_ID");
			            t.setPrintid(printid);
			        }
			    }
			}
			for (int i = 0; i < zdetaillist.size(); i++) {
			    Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
			            .get(i);
			    stockService.save(zstd);
			}
			for (int i = 0; i < cdzetaillist.size(); i++) {
			    Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
			            .get(i);
			    stockService.save(stss);
			}
			for (int i = 0; i < cdchecklist.size(); i++) {

			    Stsschecktab stss = (Stsschecktab) cdchecklist.get(i);

			    stockService.save(stss);
			}
			for (int i = 0; i < seatlist.size(); i++) {
			    Seatsaletab so = (Seatsaletab) seatlist.get(i);
			    stockService.save(so);
			}
			// lijingrui修改 2012-08-30 订单中含有预制票 操作个人结存明细表
			for (int i = 0; i < listorder.size(); i++) {
			    T_orderlist tlist = (T_orderlist) listorder.get(i);
			    Edmtickettypetab edmticket = (Edmtickettypetab) this.stockService
			            .get(Edmtickettypetab.class,
			                    Long.parseLong(tlist.getItickettypeid()));
			    if (tlist.getBymaketicketway().equals("01")) {
			        if (edmticket.getBymediatype().equals("00")
			                || edmticket.getBymediatype().equals("01")) {
			            String szticketprintno = tlist.getSzticketprintno();
			            String[] iserial = szticketprintno.split("[|]");
			            boolean sf = saleCenterService.checkEditIompersonHouse(
			                    s.getIhandler(), iserial, s.getIscenicid(),
			                    Long.parseLong(tlist.getItickettypeid()),
			                    Long.parseLong(tlist.getNumb()));
			            if (sf) {
							rs.addRow(new String[] { "false", "售票员手中没售出票号或者售出票号与售票员手中票号冲突" });
			                return rs;
			            }
			        } else {
			            Hotelprovider provider = (Hotelprovider) this.stockService
			                    .get(Hotelprovider.class, s.getIscenicid());
			            if (provider != null && provider.getInoteger5() == 1) {
			                String psql = " from Kcpersonalticketdetailstab per where per.itickettypeid="
			                        + Long.parseLong(tlist.getItickettypeid())
			                        + " and per.ireceiverid=" + s.getIhandler();
			                List kcList = this.stockService.findTopNumb(psql, 1);
			                if (kcList != null && kcList.size() > 0) {
			                    Kcpersonalticketdetailstab person = (Kcpersonalticketdetailstab) kcList
			                            .get(0);
			                    if (person.getIamount() > Long.parseLong(tlist
			                            .getNumb())) {
			                        person.setIamount(person.getIamount()
			                                - Long.parseLong(tlist.getNumb()));
			                        this.stockService.update(person);
			                    } else if (person.getIamount() == Long
			                            .parseLong(tlist.getNumb())) {
			                        this.stockService.delete(person);
			                    } else {
									rs.addRow(new String[] { "false",
											"现场激活感应卡" + edmticket.getSztickettypename() + "数量不足" });
			                    }
			                    // 短信公告提醒
			                    // 获取售票员手中票的数量与系统参数中设置的数量做对比 不足 添加站内公告信息
			                    String ksql1 = "select sum(ps.iamount) as iamount from Kcpersonalticketdetailstab ps where ps.itickettypeid="
			                            + Long.parseLong(tlist.getItickettypeid())
			                            + " and ps.ireceiverid=" + s.getIhandler();
			                    List mounList = this.stockService.find(ksql1);
			                    if (mounList != null && mounList.size() > 0) {
			                        Long numberount = (Long) mounList.get(0);
			                        String sysql1 = "select sys1.pmvb as pmvb from Sysparv5 sys1 where sys1.id.pmky='REMD' and sys1.id.pmcd='0002' ";
			                        List pmvList = this.stockService.find(sysql1);
			                        if (pmvList != null && pmvList.size() > 0) {
			                            Long pmvb = Long.parseLong(pmvList.get(0)
			                                    .toString());

			                            if (numberount != null && pmvb != null
			                                    && numberount < pmvb) {
			                                Webinfotab webinfo = new Webinfotab();
			                                Long maxids = this.stockService.getMaxPk(
			                                        "seq", "Webinfotab");
			                                webinfo.setSeq(maxids + 1);
			                                webinfo.setIemployeeid(s.getIhandler());
			                                webinfo.setIhadder(s.getIhandler());
			                                webinfo.setSzmemo(edmticket
			                                        .getSztickettypename()
			                                        + "剩余数量：" + numberount);
			                                webinfo.setReminddate(Tools
			                                        .getDayTimes());
			                                this.stockService.save(webinfo);
			                            }

			                            if (numberount == null
			                                    || numberount.equals("")) {
			                                Webinfotab webinfo = new Webinfotab();
			                                Long maxids = this.stockService.getMaxPk(
			                                        "seq", "Webinfotab");
			                                webinfo.setSeq(maxids + 1);
			                                webinfo.setIemployeeid(s.getIhandler());
			                                webinfo.setIhadder(s.getIhandler());
			                                webinfo.setSzmemo(edmticket
			                                        .getSztickettypename()
			                                        + "已售完,请重新领取!");
			                                webinfo.setReminddate(Tools
			                                        .getDayTimes());
			                                this.stockService.save(webinfo);
			                            }
			                        }

			                    } else {
			                        Webinfotab webinfo = new Webinfotab();
			                        Long maxids = this.stockService.getMaxPk("seq",
			                                "Webinfotab");
			                        webinfo.setSeq(maxids + 1);
			                        webinfo.setIemployeeid(s.getIhandler());
			                        webinfo.setIhadder(s.getIhandler());
			                        webinfo.setSzmemo(edmticket
			                                .getSztickettypename() + "已售完,请重新领取!");
			                        webinfo.setReminddate(Tools.getDayTimes());
			                        this.stockService.save(webinfo);
			                    }

			                }

			            }

			        }
			    }
			}

			rs.addRow(new String[] { "true",s.getId().getIsalesvoucherid().toString() });
			return rs;
		} catch (Exception e) {
			LOGGER.error("保存线下出票信息表异常："+StringUtil.toString_02(e));
			rs.addRow(new String[] { "false","保存线下出票信息异常"});
			return rs;
		}
    }

    public List SavetSeatsaletab(List seatlist, Stssalesvouchertab s,
                                 T_orderlist tlist, List listzorder, Long byusage) {
        int yxq = 1;

        for (int i = 0; i < listzorder.size(); i++) {
            T_zorderlist zlist = (T_zorderlist) listzorder.get(i);

            if (zlist.getOrderlistid().equals(tlist.getOrderlistid())) {
                String seats = zlist.getSeats();
                System.out.println("seats=" + seats);
                if (!seats.equals("null") && !seats.equals("")
                        && !seats.equals("NULL")) {
                    String[] seatids = seats.split(">");
                    for (int j = 0; j < seatids.length; j++) {
                        Seatsaletab so = new Seatsaletab();
                        SeatsaletabId soid = new SeatsaletabId();
                        soid.setIsalesvoucherdetailsid(new Long(tlist
                                .getOrderlistid()));
                        soid.setIcomticketsalesdetailsid(new Long(zlist
                                .getZorderlistid()));
                        soid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                        soid.setIticketstationid(s.getId()
                                .getIticketstationid());
                        soid.setSeq(new Long(j + 1));
                        if (byusage == 0) {
                            soid.setSzsoldticketid(new Long(j + 1));
                        } else {
                            soid.setSzsoldticketid(new Long(1));
                        }
                        so.setId(soid);
                        so.setIprogramid(new Long(zlist.getIvenueseatsid()));
                        so.setIseatid(new Long(seatids[j]));
                        so.setIsvalid(1L);
                        so.setItripid(new Long(zlist.getTripid()));
                        so.setItripprdcontrolid(new Long(zlist.getIse()));
                        so.setIvenueid(new Long(zlist.getIvenueid()));
                        so.setIvenueareaid(new Long(zlist.getIvenueareaid()));
                        so.setDtmakedate(Tools.getDayTimes());
                        so.setStartdate(zlist.getDtstartdate().substring(0, 10));
                        seatlist.add(so);
                    }
                }
            }
        }
        return seatlist;
    }

    public boolean saveshuijiseat(List zdetaillist) throws Exception {
        System.out.println("开始保存座位1");

        try {
            String sjseatid = "";
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                        .get(i);
                String sql = "";
                String paixu = "";
                if (dunum % 2 == 0) {
                    paixu = " asc";
                } else {
                    paixu = " asc";
                }
                // dunum++;
                if (zstd.getIvenueseatsid() > 0) {
                    if (zstd.getSeatsid() == null
                            || zstd.getSeatsid().equals("")) {
                        Edmtickettypetab eticket = (Edmtickettypetab) this.stockService
                                .get(Edmtickettypetab.class,
                                        zstd.getItickettypeid());
                        if (sjseatid.equals("")) {
                            sql = "select * from ( select s.ivenueseatsid from Venueseats s where  s.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and st.ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and itripid="
                                    + zstd.getTripid()
                                    + " and startdate='"
                                    + zstd.getDtstartdate().substring(0, 10)
                                    + "') order by s.ivenueseatsid "
                                    + paixu
                                    + " ) where rownum<= "
                                    + zstd.getIsplitamount();
                        } else {
                            sql = "select * from (select s.ivenueseatsid from Venueseats s where  s.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and st.ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and itripid="
                                    + zstd.getTripid()
                                    + " and startdate='"
                                    + zstd.getDtstartdate().substring(0, 10)
                                    + "') and ivenueseatsid not in ("
                                    + sjseatid
                                    + ") order by s.ivenueseatsid "
                                    + paixu
                                    + " ) where rownum<="
                                    + zstd.getIsplitamount();
                        }

                        List list = this.stockService.findBySqlToMap(sql);

                        if (list.size() < zstd.getIsplitamount()) {
                            return false;
                        }

                        for (int j = 1; j <= zstd.getIsplitamount(); j++) {
                            Seatsaletab seat = new Seatsaletab();
                            SeatsaletabId seatid = new SeatsaletabId();
                            seatid.setSeq(new Long(j));
                            if (eticket.getByusage() == 0) {
                                seatid.setSzsoldticketid(new Long(j));
                            } else {
                                seatid.setSzsoldticketid(new Long(1));
                            }
                            seatid.setIsalesvoucherid(zstd.getId()
                                    .getIsalesvoucherid());
                            seatid.setIticketstationid(zstd.getId()
                                    .getIticketstationid());
                            seatid.setIsalesvoucherdetailsid(zstd.getId()
                                    .getIsalesvoucherdetailsid());
                            seatid.setIcomticketsalesdetailsid(zstd.getId()
                                    .getIcomticketsalesdetailsid());
                            seat.setId(seatid);
                            seat.setIprogramid(zstd.getIvenueseatsid());
                            seat.setItripid(zstd.getTripid());
                            seat.setIvenueid(zstd.getIvenueid());
                            seat.setIvenueareaid(zstd.getIvenueareaid());
                            // 取一个座位保存一个座位

                            Map map = (Map) list.get(j - 1);
                            seat.setIseatid(new Long(map.get("IVENUESEATSID")
                                    .toString()));
                            if (sjseatid.equals("")) {
                                sjseatid = map.get("IVENUESEATSID").toString();
                            } else {
                                sjseatid = sjseatid + ","
                                        + map.get("IVENUESEATSID").toString();
                            }

                            seat.setDtmakedate(Tools.getDayTimes());
                            seat.setIsvalid(1L);
                            seat.setItripprdcontrolid(zstd
                                    .getItripprdcontrolid());
                            seat.setStartdate(zstd.getDtstartdate().substring(
                                    0, 10));

                            stockService.save(seat);
                            Seatstatustab stst = new Seatstatustab();
                            SeatstatustabId ststid = new SeatstatustabId();
                            ststid.setIvenueid(seat.getIvenueid());
                            ststid.setIvenueareaid(seat.getIvenueareaid());
                            ststid.setItripid(seat.getItripid());
                            ststid.setIseatid(seat.getIseatid());
                            ststid.setStartdate(seat.getStartdate());
                            stst.setStatus(1L);
                            stst.setId(ststid);
                            stst.setDtmakedate(Tools.getDayTimes());
                            stockService.save(stst);
                        }
                    }
                }
            }
            System.out.println("开始保存座位2");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("保存座位失败");
        }
    }

    public boolean saveshuijiseat(List zdetaillist, Long iseatlockid) {
        // 读取未受座位
        try {
            Seatlocktab seatlock = (Seatlocktab) this.stockService.get(
                    Seatlocktab.class, iseatlockid);
            List seatlist = this.stockService
                    .find(" from Seatlockdetail where id.iseatlockid="
                            + iseatlockid);
            int sknum = 0;
            Stscomticketsalesdetailstab zzstd = (Stscomticketsalesdetailstab) zdetaillist
                    .get(0);
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                        .get(i);
                if (zstd.getIvenueseatsid() > 0) {
                    if (zstd.getSeatsid() == null
                            || zstd.getSeatsid().equals("")) {
                        Edmtickettypetab eticket = (Edmtickettypetab) this.stockService
                                .get(Edmtickettypetab.class,
                                        zstd.getItickettypeid());

                        for (int j = 1; j <= zstd.getIsplitamount(); j++) {
                            Seatsaletab seat = new Seatsaletab();
                            SeatsaletabId seatid = new SeatsaletabId();
                            seatid.setSeq(new Long(j));
                            if (eticket.getByusage() == 0) {
                                seatid.setSzsoldticketid(new Long(j));
                            } else {
                                seatid.setSzsoldticketid(new Long(1));
                            }
                            seatid.setIsalesvoucherid(zstd.getId()
                                    .getIsalesvoucherid());
                            seatid.setIticketstationid(zstd.getId()
                                    .getIticketstationid());
                            seatid.setIsalesvoucherdetailsid(zstd.getId()
                                    .getIsalesvoucherdetailsid());
                            seatid.setIcomticketsalesdetailsid(zstd.getId()
                                    .getIcomticketsalesdetailsid());
                            seat.setId(seatid);
                            seat.setIprogramid(zstd.getIvenueseatsid());
                            seat.setItripid(zstd.getTripid());
                            seat.setIvenueid(zstd.getIvenueid());
                            seat.setIvenueareaid(zstd.getIvenueareaid());
                            Seatlockdetail sld = (Seatlockdetail) seatlist
                                    .get(sknum);
                            sknum = sknum + 1;
                            seat.setIseatid(sld.getIseatid());
                            sld.setStatus("02");
                            stockService.update(sld);
                            seat.setDtmakedate(Tools.getDayTimes());
                            seat.setIsvalid(1L);
                            seat.setItripprdcontrolid(zstd
                                    .getItripprdcontrolid());
                            seat.setStartdate(zstd.getDtstartdate().substring(
                                    0, 10));

                            stockService.save(seat);

                        }
                    }
                }

            }

            if (sknum < seatlock.getSeatcounts().longValue()) {
                for (int i = sknum; i < seatlock.getSeatcounts(); i++) {
                    SeatstatustabId ststid = new SeatstatustabId();
                    ststid.setIvenueid(seatlock.getIvenueid());
                    ststid.setIvenueareaid(seatlock.getIvenueareaid());
                    ststid.setItripid(seatlock.getItripid());

                    ststid.setStartdate(seatlock.getStartdate());
                    Seatlockdetail sld = (Seatlockdetail) seatlist.get(i);
                    sld.setStatus("00");
                    stockService.update(sld);
                    ststid.setIseatid(sld.getIseatid());
                    Seatstatustab sts = (Seatstatustab) stockService.get(
                            Seatstatustab.class, ststid);
                    stockService.delete(sts);
                }
            }
            seatlock.setStatus("02");
            seatlock.setIsalesvoucherid(zzstd.getId().getIsalesvoucherid());
            seatlock.setIticketstationid(zzstd.getId().getIticketstationid());
            stockService.update(seatlock);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存座位失败");
        }
    }

    public boolean saveshuijiseat(List zdetaillist, String seatids) {
        // 读取未受座位
        try {
            String[] seatidd = seatids.split(",");
            int sknum = 0;
            Stscomticketsalesdetailstab zzstd = (Stscomticketsalesdetailstab) zdetaillist
                    .get(0);
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                        .get(i);
                if (zstd.getIvenueseatsid() > 0) {
                    if (zstd.getSeatsid() == null
                            || zstd.getSeatsid().equals("")) {
                        Edmtickettypetab eticket = (Edmtickettypetab) this.stockService
                                .get(Edmtickettypetab.class,
                                        zstd.getItickettypeid());

                        for (int j = 1; j <= zstd.getIsplitamount(); j++) {
                            Seatsaletab seat = new Seatsaletab();
                            SeatsaletabId seatid = new SeatsaletabId();
                            seatid.setSeq(new Long(j));
                            if (eticket.getByusage() == 0) {
                                seatid.setSzsoldticketid(new Long(j));
                            } else {
                                seatid.setSzsoldticketid(new Long(1));
                            }
                            seatid.setIsalesvoucherid(zstd.getId()
                                    .getIsalesvoucherid());
                            seatid.setIticketstationid(zstd.getId()
                                    .getIticketstationid());
                            seatid.setIsalesvoucherdetailsid(zstd.getId()
                                    .getIsalesvoucherdetailsid());
                            seatid.setIcomticketsalesdetailsid(zstd.getId()
                                    .getIcomticketsalesdetailsid());
                            seat.setId(seatid);
                            seat.setIprogramid(zstd.getIvenueseatsid());
                            seat.setItripid(zstd.getTripid());
                            seat.setIvenueid(zstd.getIvenueid());
                            seat.setIvenueareaid(zstd.getIvenueareaid());
                            seat.setIseatid(new Long(seatidd[sknum]));
                            sknum = sknum + 1;

                            seat.setDtmakedate(Tools.getDayTimes());
                            seat.setIsvalid(1L);
                            seat.setItripprdcontrolid(zstd
                                    .getItripprdcontrolid());
                            seat.setStartdate(zstd.getDtstartdate().substring(
                                    0, 10));

                            stockService.save(seat);

                        }
                    }
                }

            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存座位失败");
        }
    }

    public ResultBean Getseatlocklist(String date) {
        String sql = " select s.iseatlockid,s.usname,s.tel,s.collectname,s.idnumber,s.iprogramid,n.szprogramname,s.ivenueid,v.venueidname,s.ivenueareaid,va.ivenueareaname,t.itripprdcontrolid,td.starttime,s.itripid,s.seatcounts,'未出票' as status,s.usid,(s.usname || '(' || c.corpname || ')') as corpname,c.ibusinessid,c.note2 from seatlocktab s,Venueprogram n,Venue v,Venuearea va,Tripprdcontroltab t,Tripprdcontroldetailtab td,Custom c where s.startdate='"
                + date
                + "' and s.status='01' and s.iprogramid=n.iprogramid and v.ivenueid=s.ivenueid and va.ivenueareaid=s.ivenueareaid  and t.iprogramid=s.iprogramid and t.ivenueid=s.ivenueid and t.startdata<=s.startdate and t.enddata>=s.startdate and td.itripprdcontrolid=t.itripprdcontrolid and td.itripid=s.itripid and c.usid=s.usid ";
        List list = new ArrayList();
        try {
            list = stockService.findBySqlToMap(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MapToResultBean.toResultBean(list);
    }

    public ResultBean updateseatlock(Long iseatlockid, Long iticknumb) {
        // 更新锁定座位单 ，修改座位数据 返回座位数据

        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });
        Seatlocktab seatlocktab = (Seatlocktab) this.stockService.get(
                Seatlocktab.class, iseatlockid);
        if (!seatlocktab.getStatus().equals("01")) {
            rs.addRow(new String[] { "false",
                    "编号" + iseatlockid + "的锁定单不是锁定状态,不能售票" });
        }
        if (iticknumb > seatlocktab.getSeatcounts()) {
            rs.addRow(new String[] { "false", "销售数量不能大于锁定座位数量" });
            return rs;
        }
        seatlocktab.setStatus("02");
        stockService.update(seatlocktab);
        String seatids = "";
        List seatlist = this.stockService
                .find(" from Seatlockdetail where id.iseatlockid="
                        + seatlocktab.getIseatlockid());
        for (int i = 0; i < seatlist.size(); i++) {
            if (i < iticknumb) {
                Seatlockdetail sd = (Seatlockdetail) seatlist.get(i);
                if (i == 0) {
                    seatids = sd.getIseatid() + ",";
                } else {
                    seatids = "," + sd.getIseatid();
                }
                sd.setStatus("02");
                stockService.update(sd);
            } else {
                SeatstatustabId ststid = new SeatstatustabId();
                ststid.setIvenueid(seatlocktab.getIvenueid());
                ststid.setIvenueareaid(seatlocktab.getIvenueareaid());
                ststid.setItripid(seatlocktab.getItripid());
                ststid.setStartdate(seatlocktab.getStartdate());
                Seatlockdetail sld = (Seatlockdetail) seatlist.get(i);
                sld.setStatus("00");
                stockService.update(sld);
                ststid.setIseatid(sld.getIseatid());
                Seatstatustab sts = (Seatstatustab) stockService.get(
                        Seatstatustab.class, ststid);
                stockService.delete(sts);
            }
        }
        rs.addRow(new String[] { "true", seatids });
        return rs;
    }

    public ResultBean updatehfseatlock(Long iseatlockid) {
        // 更新锁定座位单 ，修改座位数据 返回座位数据

        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });
        Seatlocktab seatlocktab = (Seatlocktab) this.stockService.get(
                Seatlocktab.class, iseatlockid);

        seatlocktab.setStatus("01");
        stockService.update(seatlocktab);

        List seatlist = this.stockService
                .find(" from Seatlockdetail where id.iseatlockid="
                        + seatlocktab.getIseatlockid() + " and status='02'");
        for (int i = 0; i < seatlist.size(); i++) {
            Seatlockdetail sd = (Seatlockdetail) seatlist.get(i);

            sd.setStatus("01");
            stockService.update(sd);
        }
        rs.addRow(new String[] { "true", "" });
        return rs;
    }

    public ResultBean Getareapricve(Long Itripprdcontrolid, String stdt,
                                    String groupid, Long ibusinessid, Long ivenueareaid) {
        List<Map> pplist = new ArrayList();
        List list = stockService
                .find(" from Tripprdsaletab where itripprdcontrolid="
                        + Itripprdcontrolid + " and ivenueareaid="
                        + ivenueareaid);
        for (int i = 0; i < list.size(); i++) {
            Tripprdsaletab ts = (Tripprdsaletab) list.get(i);
            try {
                List<Map> plist = stockService
                        .findBySqlToMap("select et.sztickettypename, e.itickettypeid,e.mactualsaleprice,ek.szcrowdkindname,e.icrowdkindpriceid,e.icrowdkindid,e.ibusinessid,et.bymaketicketway,et.bymediatype,e.note1 from Edmcrowdkindpricetab e ,Edpcrowdkindtab ek,Edmtickettypetab et where e.itickettypeid="
                                + ts.getIproductid()
                                + " and e.startdata<='"
                                + stdt
                                + "' and e.enddata>='"
                                + stdt
                                + "' and e.note1='"
                                + groupid
                                + "' and e.ibusinessid="
                                + ibusinessid
                                + "  and ek.icrowdkindid=e.icrowdkindid and et.itickettypeid=e.itickettypeid  and e.isscene=1 and e.byisuse=1 and et.byisuse=1 order by ek.ilevelsequence");

                for (int j = 0; j < plist.size(); j++) {
                    Map map = plist.get(j);
                    map.put("ivenueareaid", ts.getIvenueareaid().toString());
                    pplist.add(plist.get(j));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return MapToResultBean.toResultBean(pplist);
    }

    public ResultBean getVenueseatsalecount(Long employeeid, String date) {
        // 读取该用户服务商的所有场馆
        System.out.println("getVenueseatsalecount0");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "message" });
        Esfemployeetab emp = (Esfemployeetab) this.stockService.get(
                Esfemployeetab.class, employeeid);
        List slist = this.stockService
                .find(" from Companyscenic where id.icompanyinfoid="
                        + emp.getIcompanyinfoid());
        String sinc = "";
        for (int i = 0; i < slist.size(); i++) {
            Companyscenic c = (Companyscenic) slist.get(i);
            if (i == 0) {
                sinc = c.getId().getIscenicid().toString();
            } else {
                sinc = sinc + "," + c.getId().getIscenicid().toString();
            }
        }
        System.out.println("sinc=" + sinc);
        List vlist = this.stockService.find(" from Venue where iscenicid in ("
                + sinc + ")");
        System.out.println("vlist=" + vlist.size());
        if (vlist == null || vlist.size() == 0) {
            return rs;
        } else {
            Long ivenueid = ((Venue) vlist.get(0)).getIvenueid();
            System.out.println("ivenueid" + ivenueid);
            // 读取场馆区域对应的座位数量信息
            try {
                String zsql = " select vs.ivenueareaid,va.ivenueareaname,count(vs.ivenueseatsid) as znumb from venueseats vs ,venuearea va where vs.ivenueid="
                        + ivenueid
                        + " and vs.ivenueid=va.ivenueid and vs.ivenueareaid=va.ivenueareaid group by vs.ivenueareaid,va.ivenueareaname order by vs.ivenueareaid ";
                List<Map> zlist = this.stockService.findBySqlToMap(zsql);
                // 读取当前日期的该场馆趟次信息
                String trql = "select td.itripid as itripid,td.starttime,tp.tripname from Tripprdcontroltab t,Tripprdcontroldetailtab td,trip tp where t.startdata<='"
                        + date
                        + "' and t.enddata>='"
                        + date
                        + "' and t.ivenueid="
                        + ivenueid
                        + " and td.itripprdcontrolid=t.itripprdcontrolid  and td.itripid=tp.tripid order by td.starttime";

                List<Map> tlist = this.stockService.findBySqlToMap(trql);
                System.out.println("tlist.size()=" + tlist.size());
                if (tlist == null || tlist.size() == 0) {

                    return rs;
                }
                for (int i = 0; i < tlist.size(); i++) {

                    String message = "";
                    message = date.substring(5, 10);
                    Map map = tlist.get(i);
                    Long itripid = new Long(map.get("ITRIPID").toString());
                    String tripname = map.get("TRIPNAME").toString();
                    String starttime = map.get("STARTTIME").toString();
                    message = message + " " + starttime + ":";
                    String ssql = "select  st.ivenueareaid ,count(iseatid) as salenumb from Seatstatustab st where st.ivenueid="
                            + ivenueid
                            + "  and startdate='"
                            + date
                            + "' and st.itripid="
                            + itripid
                            + " group by  st.ivenueareaid order by st.ivenueareaid ";
                    List<Map> salelist = this.stockService.findBySqlToMap(ssql);
                    for (int j = 0; j < zlist.size(); j++) {
                        Map zmap = zlist.get(j);
                        String ivenueareaname = zmap.get("IVENUEAREANAME")
                                .toString();

                        String ivenueareaid = zmap.get("IVENUEAREAID")
                                .toString();
                        Long znumb = new Long(zmap.get("ZNUMB").toString());
                        Long salenumb = 0L;
                        for (int k = 0; k < salelist.size(); k++) {
                            Map salemap = salelist.get(k);
                            if (ivenueareaid.equals(salemap.get("IVENUEAREAID")
                                    .toString())) {
                                salenumb = new Long(salemap.get("SALENUMB")
                                        .toString());
                            }
                        }
                        long synumb = znumb.longValue() - salenumb.longValue();
                        message = message + ivenueareaname + "(" + synumb
                                + "),";
                    }
                    message = message.substring(0, message.length() - 1);
                    System.out.println("message=" + message);
                    rs.addRow(new String[] { message });
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }
        // 计算已售座位数

        return rs;
    }

    public ResultBean getVenueseatsalecountbyiscenicid(String scenic,
                                                       String date) {
        // 读取该用户服务商的所有场馆
        System.out.println("getVenueseatsalecount0");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "message" });

        List vlist = this.stockService.find(" from Venue where iscenicid in ("
                + scenic + ")");
        System.out.println("vlist=" + vlist.size());
        if (vlist == null || vlist.size() == 0) {
            return rs;
        } else {
            Long ivenueid = ((Venue) vlist.get(0)).getIvenueid();
            System.out.println("ivenueid" + ivenueid);
            // 读取场馆区域对应的座位数量信息
            try {
                String zsql = " select vs.ivenueareaid,va.ivenueareaname,count(vs.ivenueseatsid) as znumb from venueseats vs ,venuearea va where vs.ivenueid="
                        + ivenueid
                        + " and vs.ivenueid=va.ivenueid and vs.ivenueareaid=va.ivenueareaid group by vs.ivenueareaid,va.ivenueareaname order by vs.ivenueareaid ";
                List<Map> zlist = this.stockService.findBySqlToMap(zsql);
                // 读取当前日期的该场馆趟次信息
                String trql = "select td.itripid as itripid,td.starttime,tp.tripname from Tripprdcontroltab t,Tripprdcontroldetailtab td,trip tp where t.startdata<='"
                        + date
                        + "' and t.enddata>='"
                        + date
                        + "' and t.ivenueid="
                        + ivenueid
                        + " and td.itripprdcontrolid=t.itripprdcontrolid  and td.itripid=tp.tripid order by td.starttime";

                List<Map> tlist = this.stockService.findBySqlToMap(trql);
                System.out.println("tlist.size()=" + tlist.size());
                if (tlist == null || tlist.size() == 0) {

                    return rs;
                }
                for (int i = 0; i < tlist.size(); i++) {

                    String message = "";
                    message = date.substring(5, 10);
                    Map map = tlist.get(i);
                    Long itripid = new Long(map.get("ITRIPID").toString());
                    String tripname = map.get("TRIPNAME").toString();
                    String starttime = map.get("STARTTIME").toString();
                    message = message + " " + starttime + ":";
                    String ssql = "select  st.ivenueareaid ,count(iseatid) as salenumb from Seatstatustab st where st.ivenueid="
                            + ivenueid
                            + "  and startdate='"
                            + date
                            + "' and st.itripid="
                            + itripid
                            + " group by  st.ivenueareaid order by st.ivenueareaid ";
                    List<Map> salelist = this.stockService.findBySqlToMap(ssql);
                    for (int j = 0; j < zlist.size(); j++) {
                        Map zmap = zlist.get(j);
                        String ivenueareaname = zmap.get("IVENUEAREANAME")
                                .toString();

                        String ivenueareaid = zmap.get("IVENUEAREAID")
                                .toString();
                        Long znumb = new Long(zmap.get("ZNUMB").toString());
                        Long salenumb = 0L;
                        for (int k = 0; k < salelist.size(); k++) {
                            Map salemap = salelist.get(k);
                            if (ivenueareaid.equals(salemap.get("IVENUEAREAID")
                                    .toString())) {
                                salenumb = new Long(salemap.get("SALENUMB")
                                        .toString());
                            }
                        }
                        long synumb = znumb.longValue() - salenumb.longValue();
                        message = message + ivenueareaname + "(" + synumb
                                + "),";
                    }
                    message = message.substring(0, message.length() - 1);
                    System.out.println("message=" + message);
                    rs.addRow(new String[] { message });
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println(e.toString());
            }
        }
        // 计算已售座位数

        return rs;
    }

    /**
     * * Describe:服务商优惠判断
     *
     * @see com.ectrip.sale.service.iservice.ISaveVenueService#checkEdpofferschemetab(java.lang.String)
     * @param offerschme
     * @return
     * @author lijingrui Date:2014-4-11
     */
    public ResultBean checkEdpofferschemetab(String offerschme) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "icrowdkindpriceid", "stdt", "numb",
                "yhnumb", "yhamnt", "sjmont" });

        HashSet groups = new HashSet();
        List voucherList = new ArrayList();
        boolean b = false; // 判断 散客业务的优惠 现没有散客业务的售票优惠
        if (offerschme != null && offerschme.length() > 0) {
            String[] prices = offerschme.split("#");
            if (prices != null && prices.length > 0) {
                for (int i = 0; i < prices.length; i++) {
                    String ticketes = prices[i];
                    String[] voucher = ticketes.split(",");

                    Long icrowdkindpriceid = new Long(voucher[0]);
                    String stdt = voucher[1];
                    Long numb = new Long(voucher[2]);

                    Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) stockService
                            .get(Edmcrowdkindpricetab.class, icrowdkindpriceid);
                    if (edmcrowdkindpricetab != null) {
                        if (edmcrowdkindpricetab.getIbusinessid() == 1) { // 散客业务
                            b = true;
                            Double sjmont = edmcrowdkindpricetab
                                    .getMactualsaleprice() * numb;
                            rs.addRow(new String[] {
                                    icrowdkindpriceid.toString(), stdt,
                                    numb.toString(), "0", "0",
                                    sjmont.toString() });
                        } else {
                            Edmtickettypetab edmticket = (Edmtickettypetab) stockService
                                    .get(Edmtickettypetab.class,
                                            edmcrowdkindpricetab
                                                    .getItickettypeid());
                            groups.add(edmticket.getIscenicid());

                            Object[] object = new Object[] {
                                    edmticket.getIscenicid(),
                                    icrowdkindpriceid, stdt, numb,
                                    edmcrowdkindpricetab.getMactualsaleprice() };
                            voucherList.add(object);
                        }
                    }

                }

                if (b) {
                    return rs;
                }

                // 先循环服务商 根据服务商来查找其下的价格信息
                Iterator ite = groups.iterator();
                while (ite.hasNext()) {
                    Object obj = ite.next();

                    Edpofferschemetab edpoffer = new Edpofferschemetab();
                    List schemelist = new ArrayList();
                    List productList = new ArrayList();
                    Long znums = 0L; // 获取服务商优惠总数量

                    String yhlx = "0";// 获取优惠方式 0 -最高价 1-最低价
                    Hotelprovider hotel = (Hotelprovider) this.stockService
                            .get(Hotelprovider.class,
                                    Long.parseLong(obj.toString()));
                    if (hotel != null && hotel.getInoteger8() != null) {
                        yhlx = hotel.getInoteger8().toString();
                    }

                    if (voucherList != null && voucherList.size() > 0) {
                        for (int x = 0; x < voucherList.size(); x++) {
                            Object[] ject = (Object[]) voucherList.get(x);
                            Long iscenicid = Long.parseLong(ject[0].toString());
                            Long icrowdkindpriceid = Long.parseLong(ject[1]
                                    .toString());
                            String startdate = ject[2].toString();
                            Long numb = Long.parseLong(ject[3].toString());

                            if (obj.toString().equals(iscenicid.toString())) {

                                // 根据其游览日期获取某服务商优惠，并找到其游览日期在同一优惠下的信息
                                // 如果某游览日期不在此服务商优惠，先保存数组中之后在查找判断优惠
                                if (edpoffer != null) {
                                    if (startdate.compareTo(edpoffer
                                            .getStartdata()) >= 0
                                            && edpoffer.getEnddata().compareTo(
                                            startdate) >= 0) {
                                        schemelist.add(ject);
                                        znums += numb;
                                    } else {
                                        productList.add(ject);
                                    }
                                }

                                List offerList = stockService
                                        .find("from Edpofferschemetab where iscenicid='"
                                                + iscenicid
                                                + "' and startdata<='"
                                                + startdate
                                                + "' and enddata>='"
                                                + startdate
                                                + "' and byisuse=1 and ioffertype=1 ");
                                // 判断是否存在服务商优惠 如果没有判断指定产品优惠
                                if (offerList != null && offerList.size() > 0) { // 服务商优惠
                                    edpoffer = (Edpofferschemetab) offerList
                                            .get(0);

                                    schemelist.add(ject);
                                    znums += numb;

                                } else {// 指定产品优惠
                                    Long yhnumb = 0L;
                                    Double yhamnt = 0D;
                                    Double sjmont = 0D;
                                    Double mont = 0D;
                                    Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) stockService
                                            .get(Edmcrowdkindpricetab.class,
                                                    icrowdkindpriceid);
                                    List edlist = stockService
                                            .find(" from Edpofferschemetab where itickettypeid="
                                                    + edmcrowdkindpricetab
                                                    .getItickettypeid()
                                                    + " and ibusinessid="
                                                    + edmcrowdkindpricetab
                                                    .getIbusinessid()
                                                    + " and icrowdkindid="
                                                    + edmcrowdkindpricetab
                                                    .getIcrowdkindid()
                                                    + " and startdata<='"
                                                    + startdate
                                                    + "' and enddata>='"
                                                    + startdate
                                                    + "' and byisuse=1  and ioffertype=0 ");
                                    // 根据价格里面的业务，人群 ，产品 查询 对应的 事前人数优惠
                                    mont = edmcrowdkindpricetab
                                            .getMactualsaleprice() * numb;

                                    if (edlist != null && edlist.size() > 0) {
                                        Edpofferschemetab edsc = (Edpofferschemetab) edlist
                                                .get(0);
                                        if (numb >= edsc.getImultiples()) {
                                            yhnumb = (numb / edsc
                                                    .getImultiples())
                                                    * edsc.getIoffernum();
                                        }
                                    }
                                    yhamnt = edmcrowdkindpricetab
                                            .getMactualsaleprice() * yhnumb;
                                    sjmont = mont - yhamnt;
                                    rs.addRow(new String[] {
                                            icrowdkindpriceid.toString(),
                                            startdate, numb.toString(),
                                            yhnumb.toString(),
                                            yhamnt.toString(),
                                            sjmont.toString() });
                                }
                            }

                        }

                    }

                    if (schemelist != null && schemelist.size() > 0) {
                        Long yhnum = (znums / edpoffer.getImultiples())
                                * edpoffer.getIoffernum();
                        if (yhnum > 0) {
                            // 最高价优惠 排序
                            if (yhlx.equals("0")) {
                                Collections.sort(schemelist,
                                        new Comparator<Object[]>() {
                                            public int compare(Object[] o1,
                                                               Object[] o2) {
                                                double price1 = Double
                                                        .parseDouble(o1[4]
                                                                .toString());
                                                double price2 = Double
                                                        .parseDouble(o2[4]
                                                                .toString());
                                                if (price2 > price1) {
                                                    return 1;
                                                } else if (price2 == price1) {
                                                    return 0;
                                                } else {
                                                    return -1;
                                                }
                                            }
                                        });
                            }
                            // 最低价优惠 排序
                            if (yhlx.equals("1")) {
                                Collections.sort(schemelist,
                                        new Comparator<Object[]>() {
                                            public int compare(Object[] o1,
                                                               Object[] o2) {
                                                double price1 = Double
                                                        .parseDouble(o1[4]
                                                                .toString());
                                                double price2 = Double
                                                        .parseDouble(o2[4]
                                                                .toString());
                                                if (price1 > price2) {
                                                    return 1;
                                                } else if (price1 == price2) {
                                                    return 0;
                                                } else {
                                                    return -1;
                                                }
                                            }
                                        });

                            }

                            for (int i = 0; i < schemelist.size(); i++) {
                                Object[] ject = (Object[]) schemelist.get(i);
                                Long iscenicid = Long.parseLong(ject[0]
                                        .toString());
                                Long icrowdkindpriceid = Long.parseLong(ject[1]
                                        .toString());
                                String startdate = ject[2].toString();
                                Long numb = Long.parseLong(ject[3].toString());
                                Double mactualsaleprice = Double
                                        .parseDouble(ject[4].toString());
                                Double sjmont = mactualsaleprice * numb;

                                if (yhnum > numb) {
                                    rs.addRow(new String[] {
                                            icrowdkindpriceid.toString(),
                                            startdate, numb.toString(),
                                            numb.toString(), sjmont.toString(),
                                            "0" });
                                    yhnum -= numb;
                                } else {
                                    Double yhmont = mactualsaleprice * yhnum;
                                    Double sjmoney = sjmont - yhmont;
                                    rs.addRow(new String[] {
                                            icrowdkindpriceid.toString(),
                                            startdate, numb.toString(),
                                            yhnum.toString(),
                                            yhmont.toString(),
                                            sjmoney.toString() });
                                    yhnum = 0L;
                                }

                            }

                        } else { // 存在服务商优惠 数量不符合条件
                            for (int i = 0; i < schemelist.size(); i++) {
                                Object[] ject = (Object[]) schemelist.get(i);
                                Long iscenicid = Long.parseLong(ject[0]
                                        .toString());
                                Long icrowdkindpriceid = Long.parseLong(ject[1]
                                        .toString());
                                String startdate = ject[2].toString();
                                Long numb = Long.parseLong(ject[3].toString());
                                Double mactualsaleprice = Double
                                        .parseDouble(ject[4].toString());
                                Double sjmont = mactualsaleprice * numb;

                                rs.addRow(new String[] {
                                        icrowdkindpriceid.toString(),
                                        startdate, numb.toString(), "0", "0",
                                        sjmont.toString() });
                            }
                        }
                    }

                    // 同一服务商下某产品销售游览日期不在某服务商优惠日期内，判断其游览日期是否在服务商优惠别的优惠日期中
                    if (productList != null && productList.size() > 0) {
                        List list = new ArrayList();
                        List lst = queryListschemeUp(productList, yhlx, list);
                        if (lst != null && lst.size() > 0) {
                            for (int i = 0; i < lst.size(); i++) {
                                Object[] ject = (Object[]) lst.get(i);

                                rs.addRow(new String[] { ject[1].toString(),
                                        ject[2].toString(), ject[3].toString(),
                                        ject[4].toString(), ject[5].toString(),
                                        ject[6].toString() });
                            }
                        }
                    }
                }

            }
        }

        return rs;
    }

    /**
     *
     * Describe:判断优惠
     *
     * @author:lijingrui
     * @param productList
     * @param type
     * @param lst
     * @return return:List Date:2014-4-22
     */
    public List queryListschemeUp(List productList, String type, List lst) {
        List list = lst;

        List schemelist = new ArrayList();
        List notelist = new ArrayList();
        Edpofferschemetab edpoffer = new Edpofferschemetab();
        Long znums = 0L; // 获取服务商优惠总数量

        for (int i = 0; i < productList.size(); i++) {
            Object[] ject = (Object[]) productList.get(i);
            Long iscenicid = Long.parseLong(ject[0].toString());
            Long icrowdkindpriceid = Long.parseLong(ject[1].toString());
            String startdate = ject[2].toString();
            Long numb = Long.parseLong(ject[3].toString());
            Double mactualsaleprice = Double.parseDouble(ject[4].toString());

            if (edpoffer != null) {
                if (startdate.compareTo(edpoffer.getStartdata()) >= 0
                        && edpoffer.getEnddata().compareTo(startdate) >= 0) {
                    schemelist.add(ject);
                    znums += numb;
                } else {
                    notelist.add(ject);
                }
            }

            List offerList = stockService
                    .find("from Edpofferschemetab where iscenicid='"
                            + iscenicid + "' and startdata<='" + startdate
                            + "' and enddata>='" + startdate
                            + "' and byisuse=1 and ioffertype=1 ");
            // 判断是否存在服务商优惠 如果没有判断指定产品优惠
            if (offerList != null && offerList.size() > 0) { // 服务商优惠
                edpoffer = (Edpofferschemetab) offerList.get(0);

                schemelist.add(ject);
                znums += numb;
            } else {
                Object[] object = new Object[] { iscenicid, icrowdkindpriceid,
                        startdate, numb, "0", "0", mactualsaleprice * numb };
                list.add(object);
            }
        }

        if (schemelist != null && schemelist.size() > 0) {
            Long yhnum = (znums / edpoffer.getImultiples())
                    * edpoffer.getIoffernum();
            if (yhnum > 0) {
                // 最高价优惠 排序
                if (type.equals("0")) {
                    Collections.sort(schemelist, new Comparator<Object[]>() {
                        public int compare(Object[] o1, Object[] o2) {
                            double price1 = Double.parseDouble(o1[4].toString());
                            double price2 = Double.parseDouble(o2[4].toString());
                            if (price2 > price1) {
                                return 1;
                            } else if (price2 == price1) {
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    });
                }
                // 最低价优惠 排序
                if (type.equals("1")) {
                    Collections.sort(schemelist, new Comparator<Object[]>() {
                        public int compare(Object[] o1, Object[] o2) {
                            double price1 = Double.parseDouble(o1[4].toString());
                            double price2 = Double.parseDouble(o2[4].toString());
                            if (price1 > price2) {
                                return 1;
                            } else if (price1 == price2) {
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    });

                }

                for (int i = 0; i < schemelist.size(); i++) {
                    Object[] ject = (Object[]) schemelist.get(i);
                    Long iscenicid = Long.parseLong(ject[0].toString());
                    Long icrowdkindpriceid = Long.parseLong(ject[1].toString());
                    String startdate = ject[2].toString();
                    Long numb = Long.parseLong(ject[3].toString());
                    Double mactualsaleprice = Double.parseDouble(ject[4]
                            .toString());
                    Double sjmont = mactualsaleprice * numb;

                    if (yhnum > numb) {
                        Object[] object = new Object[] {
                                icrowdkindpriceid.toString(), startdate,
                                numb.toString(), numb.toString(),
                                sjmont.toString(), "0" };
                        list.add(object);
                        yhnum -= numb;
                    } else {
                        Double yhmont = mactualsaleprice * yhnum;
                        Double sjmoney = sjmont - yhmont;
                        Object[] object = new Object[] {
                                icrowdkindpriceid.toString(), startdate,
                                numb.toString(), yhnum.toString(),
                                yhmont.toString(), sjmoney.toString() };
                        list.add(object);
                        yhnum = 0L;
                    }

                }

            } else { // 存在服务商优惠 数量不符合条件
                for (int i = 0; i < schemelist.size(); i++) {
                    Object[] ject = (Object[]) schemelist.get(i);
                    Long iscenicid = Long.parseLong(ject[0].toString());
                    Long icrowdkindpriceid = Long.parseLong(ject[1].toString());
                    String startdate = ject[2].toString();
                    Long numb = Long.parseLong(ject[3].toString());
                    Double mactualsaleprice = Double.parseDouble(ject[4]
                            .toString());
                    Double sjmont = mactualsaleprice * numb;

                    Object[] object = new Object[] {
                            icrowdkindpriceid.toString(), startdate,
                            numb.toString(), "0", "0", sjmont.toString() };
                    list.add(object);
                }
            }
        }

        if (notelist != null && notelist.size() > 0) {
            queryListschemeUp(notelist, type, list);
        }

        return list;
    }

    public Seatlocktab Getseatlockbyid(Long iseatlockid) {

        Seatlocktab seatlocktab = (Seatlocktab) this.stockService.get(
                Seatlocktab.class, iseatlockid);
        return seatlocktab;

    }

    public String GetEmployeesenic(Long employeeid) {

        Esfemployeetab emp = (Esfemployeetab) this.stockService.get(
                Esfemployeetab.class, employeeid);
        List slist = this.stockService
                .find(" from Companyscenic where id.icompanyinfoid="
                        + emp.getIcompanyinfoid());
        String sinc = "";
        for (int i = 0; i < slist.size(); i++) {
            Companyscenic c = (Companyscenic) slist.get(i);
            if (i == 0) {
                sinc = c.getId().getIscenicid().toString();
            } else {
                sinc = sinc + "," + c.getId().getIscenicid().toString();
            }
        }
        return sinc;

    }

    public synchronized ResultBean upshuijiseat(String comticketsalesdetails) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });
        try {
            String sjseatid = "";
            String[] comticketsalesdetail = comticketsalesdetails.split(":");

            for (int i = 0; i < comticketsalesdetail.length; i++) {
                String[] zdetail = comticketsalesdetail[i].split("&");
                Long icrowdkindpriceid = new Long(zdetail[1]);
                Long itickettypeid = new Long(zdetail[2]);
                Long iztickettypeid = new Long(zdetail[3]);
                Long isplitamount = new Long(zdetail[4]);
                Long tripid = new Long(zdetail[5]);
                Long ivenueid = new Long(zdetail[6]);
                Long ivenueareaid = new Long(zdetail[7]);
                String dtstartdate = zdetail[8];
                String dtenddate = zdetail[9];
                Long iprogramid = 0L;
                String seats = "";

                if (zdetail.length >= 11) {
                    iprogramid = new Long(zdetail[10]);
                }
                if (zdetail.length >= 12) {
                    seats = zdetail[11];
                }
                String sql = "";
                String paixu = "";
                if (dunum % 2 == 0) {
                    paixu = " asc";
                } else {
                    paixu = " asc";
                }
                // 根据节目 场馆 趟次 查找排班数据，读取 节目演出时间
                if (iprogramid > 0) {
                    if (seats == null || seats.equals("")) {

                        if (sjseatid.equals("")) {
                            sql = "select * from ( select s.ivenueseatsid from Venueseats s where  s.ivenueid="
                                    + ivenueid
                                    + " and ivenueareaid="
                                    + ivenueareaid
                                    + " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
                                    + ivenueid
                                    + " and st.ivenueareaid="
                                    + ivenueareaid
                                    + " and itripid="
                                    + tripid
                                    + " and startdate='"
                                    + dtstartdate.substring(0, 10)
                                    + "') order by s.ivenueseatsid "
                                    + paixu
                                    + " ) where rownum<= " + isplitamount;
                        } else {
                            sql = "select * from (select s.ivenueseatsid from Venueseats s where  s.ivenueid="
                                    + ivenueid
                                    + " and ivenueareaid="
                                    + ivenueareaid
                                    + " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
                                    + ivenueid
                                    + " and st.ivenueareaid="
                                    + ivenueareaid
                                    + " and itripid="
                                    + tripid
                                    + " and startdate='"
                                    + dtstartdate.substring(0, 10)
                                    + "') and ivenueseatsid not in ("
                                    + sjseatid
                                    + ") order by s.ivenueseatsid "
                                    + paixu
                                    + " ) where rownum<="
                                    + isplitamount;
                        }

                        List list = this.stockService.findBySqlToMap(sql);

                        if (list.size() < isplitamount) {
                            rs.addRow(new String[] { "false", "没有满足的座位" });
                            return rs;
                        }

                        for (int j = 1; j <= isplitamount; j++) {

                            // 取一个座位保存一个座位
                            Map map = (Map) list.get(j - 1);

                            if (sjseatid.equals("")) {
                                sjseatid = map.get("IVENUESEATSID").toString();
                            } else {
                                sjseatid = sjseatid + ","
                                        + map.get("IVENUESEATSID").toString();
                            }
                            Seatstatustab stst = new Seatstatustab();
                            SeatstatustabId ststid = new SeatstatustabId();
                            ststid.setIvenueid(ivenueid);
                            ststid.setIvenueareaid(ivenueareaid);
                            ststid.setItripid(tripid);
                            ststid.setIseatid(new Long(map.get("IVENUESEATSID")
                                    .toString()));
                            ststid.setStartdate(dtstartdate.substring(0, 10));
                            stst.setStatus(1L);
                            stst.setId(ststid);
                            stst.setDtmakedate(Tools.getDayTimes());
                            stockService.save(stst);
                        }
                    }
                }
            }
            rs.addRow(new String[] { "true", sjseatid });
            return rs;
        } catch (Exception e) {
            rs.addRow(new String[] { "false", "没有满足的座位" });
            return rs;
        }
    }

    public ResultBean uphfshuijiseat(String comticketsalesdetails,
                                     String seatids) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });
        try {

            String[] comticketsalesdetail = comticketsalesdetails.split(":");
            String[] seatidd = seatids.split("&");
            int itnumb = 0;
            for (int i = 0; i < comticketsalesdetail.length; i++) {
                String[] zdetail = comticketsalesdetail[i].split("&");
                Long icrowdkindpriceid = new Long(zdetail[1]);
                Long itickettypeid = new Long(zdetail[2]);
                Long iztickettypeid = new Long(zdetail[3]);
                Long isplitamount = new Long(zdetail[4]);
                Long tripid = new Long(zdetail[5]);
                Long ivenueid = new Long(zdetail[6]);
                Long ivenueareaid = new Long(zdetail[7]);
                String dtstartdate = zdetail[8];
                String dtenddate = zdetail[9];
                Long iprogramid = 0L;
                String seats = "";

                if (zdetail.length >= 11) {
                    iprogramid = new Long(zdetail[10]);
                }
                if (zdetail.length >= 12) {
                    seats = zdetail[11];
                }
                int sjseat = 0;
                // 根据节目 场馆 趟次 查找排班数据，读取 节目演出时间
                if (iprogramid > 0) {
                    if (seats == null || seats.equals("")) {
                        String sjseatid = "";
                        for (int j = 0; j < isplitamount; j++) {
                            if (j == 0) {
                                sjseatid = seatidd[itnumb];
                            } else {
                                sjseatid = sjseatid + "," + seatidd[itnumb];
                            }
                            itnumb = itnumb + 1;
                        }
                        List list = this.stockService
                                .find(" from Seatstatustab where ivenueid="
                                        + ivenueid + " and ivenueareaid="
                                        + ivenueareaid + " and itripid= "
                                        + tripid + " and startdate='"
                                        + dtstartdate.substring(0, 10)
                                        + "' and iseatid in (" + sjseatid + ")");
                        for (int k = 0; k < list.size(); k++) {
                            Seatstatustab s = (Seatstatustab) list.get(k);
                            this.stockService.delete(s);
                        }
                    }
                }
            }
            rs.addRow(new String[] { "true", "" });
            return rs;
        } catch (Exception e) {
            rs.addRow(new String[] { "false", "没有满足的座位" });
            return rs;
        }
    }

    /**
     * * Describe:获取某用户的价格分组 服务商id可为空
     *
     * @see com.ectrip.ticket.service.iservice.ITicketService#searchJgfz(java.lang.String,
     *      java.lang.Long)
     * @param usid
     * @param iscenicid
     * @return
     * @author lijingrui Date:2014-4-16
     */
    public String searchJgfz(String usid, Long iscenicid) {
        StringBuffer result = new StringBuffer();
        StringBuffer hsql = new StringBuffer(
                "select gz.usid as usid,gz.iscenicid as iscenicid,gz.pmcd as pmcd from Jgfz gz,(select connect_by_root usid as susid,connect_by_root corpname as scorpname,connect_by_root bname as bname,connect_by_root ibusinessid as ibusinessid,usid from custom c  START WITH c.lgtp = '02' and ttlb = '01' and ustp='01' connect by prior c.usid = c.susid) c where c.susid=gz.usid and gz.byisuse=1 and c.usid='"
                        + usid + "' ");
        if (iscenicid != null && !iscenicid.equals("")) {
            hsql.append(" and gz.iscenicid=" + iscenicid);
        }
        List<Map> lst = new ArrayList<Map>();
        try {
            lst = this.stockService.findBySqlToMap(hsql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (lst != null && lst.size() > 0) {
            for (int i = 0; i < lst.size(); i++) {
                Map map = (Map) lst.get(i);
                if (map.get("PMCD") != null) {
                    if (i == 0) {
                        result.append(map.get("PMCD").toString());
                    } else {
                        result.append("," + map.get("PMCD").toString());
                    }

                }
            }
            return result.toString();
        } else {
            result.append("0000");
            return result.toString();
        }

    }

    public ResultBean upshuijiseat(List zdetaillist) throws Exception {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });
        try {
            String sjseatid = "";
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                        .get(i);
                String sql = "";
                String paixu = "";
                if (dunum % 2 == 0) {
                    paixu = " asc";
                } else {
                    paixu = " asc";
                }
                // dunum++;
                if (zstd.getIvenueseatsid() > 0) {
                    if (zstd.getSeatsid() == null
                            || zstd.getSeatsid().equals("")) {
                        Edmtickettypetab eticket = (Edmtickettypetab) this.stockService
                                .get(Edmtickettypetab.class,
                                        zstd.getItickettypeid());
                        if (sjseatid.equals("")) {
                            sql = "select * from ( select s.ivenueseatsid from Venueseats s where  s.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and st.ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and itripid="
                                    + zstd.getTripid()
                                    + " and startdate='"
                                    + zstd.getDtstartdate().substring(0, 10)
                                    + "') order by s.ivenueseatsid "
                                    + paixu
                                    + " ) where rownum<= "
                                    + zstd.getIsplitamount();
                        } else {
                            sql = "select * from (select s.ivenueseatsid from Venueseats s where  s.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and byisuse=1 and ivenueseatsid not in (select iseatid from Seatstatustab st where st.ivenueid="
                                    + zstd.getIvenueid()
                                    + " and st.ivenueareaid="
                                    + zstd.getIvenueareaid()
                                    + " and itripid="
                                    + zstd.getTripid()
                                    + " and startdate='"
                                    + zstd.getDtstartdate().substring(0, 10)
                                    + "') and ivenueseatsid not in ("
                                    + sjseatid
                                    + ") order by s.ivenueseatsid "
                                    + paixu
                                    + " ) where rownum<="
                                    + zstd.getIsplitamount();
                        }

                        List list = this.stockService.findBySqlToMap(sql);

                        if (list.size() < zstd.getIsplitamount()) {
                            rs.addRow(new String[] { "false", "没有满足的座位" });
                            return rs;
                        }

                        for (int j = 1; j <= zstd.getIsplitamount(); j++) {

                            // 取一个座位保存一个座位

                            Map map = (Map) list.get(j - 1);

                            if (sjseatid.equals("")) {
                                sjseatid = map.get("IVENUESEATSID").toString();
                            } else {
                                sjseatid = sjseatid + ","
                                        + map.get("IVENUESEATSID").toString();
                            }

                            Seatstatustab stst = new Seatstatustab();
                            SeatstatustabId ststid = new SeatstatustabId();
                            ststid.setIvenueid(zstd.getIvenueid());
                            ststid.setIvenueareaid(zstd.getIvenueareaid());
                            ststid.setItripid(zstd.getTripid());
                            ststid.setIseatid(new Long(map.get("IVENUESEATSID")
                                    .toString()));
                            ststid.setStartdate(zstd.getDtstartdate()
                                    .substring(0, 10));
                            stst.setStatus(1L);
                            stst.setId(ststid);
                            stst.setDtmakedate(Tools.getDayTimes());
                            stockService.save(stst);
                        }
                    }
                }
            }

            rs.addRow(new String[] { "true", "" });
            return rs;
        } catch (Exception e) {
            rs.addRow(new String[] { "false", "没有满足的座位" });
            return rs;
        }
    }

    /**
     * * Describe:获取接待用户的控制量
     *
     * @see com.ectrip.sale.service.iservice.ISaveVenueService#queryStockWarenumb(java.lang.String,
     *      java.lang.String)
     * @param usid
     * @param iscenicid
     * @return
     * @author lijingrui Date:2015-1-5
     */
    public ResultBean queryStockWarenumb(String usid, String iscenicid) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });

        try {
            IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
            Long stockNumb = stockService.sumJdStock(usid,iscenicid);
            if(stockNumb == null){
                rs.addRow(new String[]{"true", "未设置库存量"});
            }else{
                rs.addRow(new String[] { "true", stockNumb.toString() });
            }
            return rs;

        } catch (Exception e) {
            rs.addRow(new String[] { "false", "没有用户控制量" });
            return rs;
        }

    }

    /**
     * * Describe:根据票号获取指纹信息
     *
     * @see com.ectrip.sale.service.iservice.ISaveVenueService#checkTicketzhiwe(java.lang.String)
     * @param szticketprintno
     * @return
     * @author lijingrui Date:2015-1-15
     */
    public ResultBean checkTicketzhiwe(String szticketprintno) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });

        String sql = "select distinct new map(sts.bsfilebinary as bsfilebinary) from Stssoldtickettab st,Stssoldticketattesttab sts where sts.id.szsoldticketid=st.id.szsoldticketid "
                + " and sts.id.isalesvoucherid=st.id.isalesvoucherid and sts.id.isalesvoucherdetailsid=st.id.isalesvoucherdetailsid and st.id.iticketstationid=sts.id.iticketstationid "
                + " and st.szticketprintno='" + szticketprintno + "'";
        List lst = this.stockService.find(sql);
        if (lst != null && lst.size() > 0) {
            Map map = (Map) lst.get(0);
            rs.addRow(new String[] { "true", map.get("bsfilebinary").toString() });
        } else {
            rs.addRow(new String[] { "false", "未录入指纹!" });
        }
        return rs;
    }

    /**
     * * Describe:根据身份证号码获取导游计时票信息
     *
     * @see com.ectrip.sale.service.iservice.ISaveVenueService#queryListzjhmPrint(java.lang.String)
     * @param zjhm
     * @return
     * @author lijingrui Date:2015-2-4
     */
    public ResultBean queryListzjhmPrint(String zjhm) {
        List list = new ArrayList();
        Sysparv5 sys = (Sysparv5) this.stockService.get(Sysparv5.class,
                new Sysparv5Id("DYJS", "****"));
        if (sys != null && sys.getIsvalue() == 1L && sys.getIsa() == 1L) {
            if (sys.getPmvb() != null && !"".equals(sys.getPmvb())) {
                String tickettype = sys.getPmvb();// 需要验证的产品编码

                String startDate = Tools.getDayTimes();
                String hsql = "select new map(st.id.szsoldticketid as szsoldticketid,st.id.isalesvoucherdetailsid as isalesvoucherdetailsid,st.id.isalesvoucherid as isalesvoucherid,st.id.iticketstationid as iticketstationid,st.itickettypeid as itickettypeid,edm.sztickettypename as sztickettypename,st.icrowdkindid as icrowdkindid,st.iscenicid as iscenicid,st.ibusinessid as ibusinessid,st.dtstartdate as dtstartdate,st.dtenddate as dtenddate,st.szticketprintno as szticketprintno,st.mactualsaleprice as mactualsaleprice,st.myzj as myzj,s.szsalesvoucherno as szsalesvoucherno)"
                        + " from Stssalesvouchertab s,Stssoldtickettab st,Stssoldticketsubtab sts,Edmtickettypetab edm where sts.id.isalesvoucherdetailsid=st.id.isalesvoucherdetailsid and sts.id.isalesvoucherid=st.id.isalesvoucherid and "
                        + "sts.id.iticketstationid=st.id.iticketstationid and st.id.szsoldticketid=sts.id.szsoldticketid and edm.itickettypeid=st.itickettypeid and st.itickettypeid=sts.itickettypeid and edm.itickettypeid=sts.itickettypeid and s.id.isalesvoucherid = st.id.isalesvoucherid and s.id.iticketstationid = st.id.iticketstationid and s.id.isalesvoucherid = sts.id.isalesvoucherid and s.id.iticketstationid = st.id.iticketstationid "
                        + "and edm.bycategorytype='"
                        + tickettype
                        + "' and st.myzj='"
                        + zjhm
                        + "' and sts.dtenddate >='"
                        + Tools.getDayTimes()
//						+ "' and substr(st.dtmakedate,1,10)='"
//						+ Tools.getDays()
                        + "' and sts.dtbegindate<='"
                        + startDate + "' ";
                list = stockService.find(hsql);
                if (list != null && list.size() > 0) {
                    Map map = (Map) list.get(0);

                    String sql = " from Stssoldfingerprint where zjhm='" + zjhm
                            + "'";
                    List lst = this.stockService.find(sql);
                    if (lst != null && lst.size() > 0) {
                        Stssoldfingerprint stss = (Stssoldfingerprint) lst
                                .get(0);

                        map.put("BSFILEBINARY", stss.getBsfilebinary());
                    } else {
                        map.put("BSFILEBINARY", "");
                    }

                    return MapToResultBean.toResultBean(list);
                }
            }
        }

        return null;
    }

    /**
     * * Describe:保存导游截止日期 或者录入指纹
     *
     * @see com.ectrip.sale.service.iservice.ISaveVenueService#checkSaveprintStssold(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     * @param szsoldticketid
     * @param isalesvoucherid
     * @param isalesvoucherdetailsid
     * @param iticketstationid
     * @param zjhm
     * @param bsfilebinary
     * @return
     * @author lijingrui Date:2015-2-10
     */
    public ResultBean checkSaveprintStssold(String szsoldticketid,
                                            String isalesvoucherid, String isalesvoucherdetailsid,
                                            String iticketstationid, String zjhm, String bsfilebinary) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);

        rs.setColumnNames(new String[] { "returnstats", "message" });

        List szsstlist = stockService
                .find("from Stssoldticketsubtab where  id.isalesvoucherid="
                        + isalesvoucherid + " and id.iticketstationid="
                        + iticketstationid
                        + "   and  id.isalesvoucherdetailsid="
                        + isalesvoucherdetailsid + " and id.szsoldticketid="
                        + szsoldticketid);
        if (szsstlist != null && szsstlist.size() > 0) {

            for (int a = 0; a < szsstlist.size(); a++) {

                Stssoldticketsubtab stsub = (Stssoldticketsubtab) szsstlist
                        .get(a);
                stsub.setDtenddate(Tools.getDayTimes());
                this.stockService.update(stsub);
            }

            String sql = " from Stssoldfingerprint where zjhm='" + zjhm + "'";
            List lst = this.ecService.find(sql);
            if (lst == null || lst.size() < 1) {
                Stssoldfingerprint stssold = new Stssoldfingerprint();
                Long maxid = this.stockService.getMaxPk("iprintid",
                        "Stssoldfingerprint");
                stssold.setIprintid(maxid + 1L);
                stssold.setZjhm(zjhm);
                stssold.setBsfilebinary(bsfilebinary);

                this.stockService.save(stssold);
            }

            rs.addRow(new String[] { "true", "录入截止日期成功!" });

        } else {
            rs.addRow(new String[] { "false", "未获取到数据!" });
        }

        return rs;
    }

    public Sysparv5 Getsyspar(String pmky, String pmcd) {
        Sysparv5 sysparv5 = (Sysparv5) this.sysService.findOne(pmky, pmcd);
        return sysparv5;

    }
}
