package com.ectrip.ticket.sale.service;

import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.Debug;
import com.ectrip.base.util.Encrypt;
import com.ectrip.base.util.EncryptUtil;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.LOrderId;
import com.ectrip.ec.model.order.LOrderlist;
import com.ectrip.ec.model.order.LOrderlistId;
import com.ectrip.ec.model.order.LOrderpeople;
import com.ectrip.ec.model.order.LOrderpeopleId;
import com.ectrip.ec.model.order.LZorderlist;
import com.ectrip.ec.model.order.LZorderlistId;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.ec.model.order.Seatordertab;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.ClientRequest;
import com.ectrip.hqyt.model.request.GuaranteeRequest;
import com.ectrip.hqyt.model.request.LegalPersonRequest;
import com.ectrip.hqyt.model.request.ProductRequest;
import com.ectrip.hqyt.model.response.JSONClient;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Soderollprintmanage;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.common.checkUtils.CommonCheckUtil;
import com.ectrip.ticket.common.checkUtils.CommonUtil;
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
import com.ectrip.ticket.model.order.StssalessettlementtabId;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.StssalesvoucherdetailstabId;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.StssalesvouchertabId;
import com.ectrip.ticket.model.order.Stsschecktab;
import com.ectrip.ticket.model.order.StsschecktabId;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.StssoldticketsubtabId;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.model.order.Ticketprintlist;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.model.stock.StockJson;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Trip;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venuearea;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.model.venuemarketing.Venueseats;
import com.ectrip.ticket.provider.service.ICrowdKindPriceService;
import com.ectrip.ticket.provider.service.IEsbticketWinService;
import com.ectrip.ticket.provider.service.IProviderService;
import com.ectrip.ticket.provider.service.impl.TicketTypeService;
import com.ectrip.ticket.sale.service.iservice.ISaleAutoService;
@Service
public class SaleAutoService implements ISaleAutoService {
    /*ticketTypeService ticketTypeService;
    IMbMessageDAO mbmessageDao;
    private IGenericService genericService;
    private ICustomService cusService;
    private IShopCartService shopcartService;*/
	@Autowired
    private IProviderService providerService;
	
	@Autowired
	private IEsbticketWinService esbticketWinServicetab;
	
	@Autowired
	private TicketTypeService ticketTypeService;
	@Autowired
	private IProviderService ProviderService;
	@Autowired
	private SysService sysService;
	@Autowired
	private EcService ecService;
	@Autowired
	private ICrowdKindPriceService crowdKindPriceService;
	@Autowired
	private IStockService stockService;
    /*public IMbMessageDAO getMbmessageDao() {
        return mbmessageDao;
    }

    public void setMbmessageDao(IMbMessageDAO mbmessageDao) {
        this.mbmessageDao = mbmessageDao;
    }

    public ticketTypeService getticketTypeService() {
        return ticketTypeService;
    }

    public void setticketTypeService(ticketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    public IGenericService getGenericService() {
        return genericService;
    }

    public void setGenericService(IGenericService genericService) {
        this.genericService = genericService;
    }

    public ICustomService getCusService() {
        return cusService;
    }

    public void setCusService(ICustomService cusService) {
        this.cusService = cusService;
    }

    public IShopCartService getShopcartService() {
        return shopcartService;
    }

    public void setShopcartService(IShopCartService shopcartService) {
        this.shopcartService = shopcartService;
    }

    public IProviderService getProviderService() {
        return providerService;
    }

    public void setProviderService(IProviderService providerService) {
        this.providerService = providerService;
    }*/

    public Esbticketwintab getEsbticketwintab(Long iscenicid, String mac) {
        List esblist = sysService.find(" from Esbticketwintab where iscenicid="
                + iscenicid + " and szipaddress='" + mac + "'");
        Esbticketwintab e = (Esbticketwintab) esblist.get(0);
        return e;
    }

    public Esfemployeetab isyuxiao(String md5str, String userid, String password) {
        List emplist = sysService.find("from Esfemployeetab where empid='"
                + userid + "' and byisuse=1");
        Esfemployeetab employee = new Esfemployeetab();
        if (emplist == null || emplist.size() == 0) {
            return null;
        } else {
        	try {
				BeanUtils.populate(employee,(Map) emplist.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
            String loginintime = employee.getLogintime();
            if (loginintime != null && loginintime.length() > 10) {
                if (loginintime.substring(0, 10).equals(Tools.getTodayString())) {
                    if (employee.getZtimes() >= 5) {

                        return null;
                    }
                }
            }
            Encrypt encrypt = new Encrypt();
            password = encrypt.passwordEncrypt(password);
            if (!password.equals(employee.getSzpassword())) {
                if (loginintime != null && loginintime.length() > 10) {
                    if (loginintime.substring(0, 10).equals(
                            Tools.getTodayString())) {
                        if (employee.getZtimes() == null
                                || employee.getZtimes().equals("")) {
                            employee.setZtimes(new Long(1));
                        } else {
                            employee.setZtimes(employee.getZtimes() + 1);
                        }
                    } else {
                        employee.setLogintime(Tools.getDayTimes());
                        employee.setZtimes(new Long(1));
                    }
                } else {
                    employee.setLogintime(Tools.getDayTimes());
                    employee.setZtimes(new Long(1));
                }
                sysService.update(employee);
                return null;
            } else { //

                // 判断是否具有售票权限
                List epdlist = sysService
                        .find("select e.empid,epp.idutyid from Esfemployeetab e,Esfemployeepoststab ep,Esppostsdutytab epp,Espdutytab esp where  e.empid='"
                                + userid
                                + "' and epp.idutyid=esp.idutyid and  upper(esp.szdutycode)='SALERIGHT' and ep.iemployeeid=e.iemployeeid and ep.ipostsid=epp.ipostsid");

                if (epdlist == null || epdlist.size() == 0) {

                    return null;
                } else {
                    return employee;
                }
            }
        }
    }

    public ResultBean saveorder(String message, Long iticketwinid,
                                Long iemployeeid, String orid, Long iscenicid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        LOrder lorder = new LOrder();
        lorder.setIticketwinid(iticketwinid);
        lorder.setIemployeeid(iemployeeid);
        LOrderId lid = new LOrderId();
        lid.setOrid(orid);
        lid.setIscenicid(iscenicid);
        lorder.setDdzt("00");
        lorder.setOrda(Tools.getTodayString());
        lorder.setOrti(Tools.getNowTime());
        lorder.setUsid("guest");
        lorder.setId(lid);
        String sstdt = "";
        String seddt = "";
        double amnt = 0;
        // Esfemployeetab
        // employee=(Esfemployeetab)ticketTypeService.get(Esfemployeetab.class,
        // iemployeeid);
        // Esbticketwintab
        // ewin=(Esbticketwintab)ticketTypeService.get(Esbticketwintab.class,
        // iticketwinid);
        String[] pmessage = message.split(";");
        List olist = new ArrayList();
        for (int i = 0; i < pmessage.length; i++) {
            LOrderlist lo = new LOrderlist();
            LOrderlistId loid = new LOrderlistId();
            loid.setOrid(orid);
            loid.setOrderlistid(new Long(i + 1));
            loid.setIscenicid(iscenicid);
            System.out.println("pmessage[" + i + "]=" + pmessage[i]);
            String los[] = pmessage[i].split(",");
            Long itickettypeid = new Long(los[0]);// 票ID
            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                    Edmtickettypetab.class, itickettypeid);

            Long icrowdkindpriceid = new Long(los[1]);
            Edmcrowdkindpricetab eticketprice = (Edmcrowdkindpricetab) ticketTypeService
                    .get(Edmcrowdkindpricetab.class, icrowdkindpriceid);
            String stdt = los[2];
            Long numb = new Long(los[3]);

            lo.setId(loid);
            lo.setItickettypeid(itickettypeid);
            lo.setIcrowdkindpriceid(icrowdkindpriceid);
            lo.setDtstartdate(stdt);
            String eddt = Tools.getDate(stdt, eticket.getValidityday()
                    .intValue() - 1);
            if (sstdt.equals("")) {
                sstdt = stdt;
            } else {
                if (Tools.getDayNumb(stdt, sstdt) <= 0) {
                    sstdt = stdt;
                }
            }
            if (seddt.equals("")) {
                seddt = eddt;
            } else {
                if (Tools.getDayNumb(eddt, seddt) > 1) {
                    seddt = eddt;
                }
            }
            lo.setDtenddate(eddt);
            lo.setNumb(numb);
            lo.setIcrowdkindid(eticketprice.getIcrowdkindid());
            lo.setPric(eticketprice.getMactualsaleprice());
            lo.setAmnt(eticketprice.getMactualsaleprice() * numb);
            lo.setYhnumb(new Long(0));
            lo.setYhamnt(new Double(0));
            lo.setId(loid);
            amnt = amnt + lo.getAmnt();
            /**
             * 判断产品是按子产品自己有效期还是套票有效期
             */
            int yxq = 1;
            Edmticketproduct et = (Edmticketproduct) ticketTypeService.get(
                    Edmticketproduct.class, itickettypeid);
            if (et == null) {
                yxq = 0;
            } else {
                if (et.getInoteger1() == null || et.getInoteger1() == 0) {
                    yxq = 0;
                }
            }
            // 读出证件号码
            System.out.println("los[4]" + los[4]);
            List zjlist = new ArrayList();
            if (eticketprice.getIpeoplenumrange().longValue() == 1) {
                String zjs = los[4];
                if (zjs != null && !zjs.equals("")) {
                    String zjls[] = zjs.split("[|]");
                    Map<String,Integer> map = new HashMap<String, Integer>();
                    if (zjls.length != numb) {
                        rs.addRow(new String[] { "false",
                                eticket.getSztickettypename() + "实名制的身份证信息数量不对" });
                        return rs;
                    } else {
                        for (int j = 0; j < zjls.length; j++) {
                            String zjp[] = zjls[j].split("&");
                            LOrderpeople lop = new LOrderpeople();
                            LOrderpeopleId lpid = new LOrderpeopleId();
                            lpid.setIscenicid(iscenicid);
                            lpid.setOrderlistid(new Long(i + 1));
                            lpid.setOrid(orid);
                            lpid.setPeopleid(new Long(j));
                            lop.setPname(zjp[1].trim());
                            lop.setPzjhm(zjp[0].trim());
                            lop.setId(lpid);
                            zjlist.add(lop);
                            if(map.containsKey(zjp[0].trim())){
                                map.put(zjp[0].trim(),map.get(zjp[0].trim())+1);
                            }else{
                                map.put(zjp[0].trim(),1);
                            }
                        }
                        String mess = CommonCheckUtil.checkLimitSaleByCard(map, 1, stdt);
                        if(!StringUtils.isBlank(mess)){
                            rs.addRow(new String[] {"false",message});
                            return rs;
                        }
                    }
                } else {
                    rs.addRow(new String[] { "false",
                            eticket.getSztickettypename() + "实名制票务必须登记身份证" });
                    return rs;
                }
            }
            lo.setZjlist(zjlist);
            // 子票数据
            System.out.println("los[5]=" + los[5]);
            String zlo = los[5];
            String zlos[] = zlo.split("[|]");
            List zolist = new ArrayList();
            System.out.println(zlos.length);
            List zpalist = ticketTypeService
                    .find(" from Edmticketcomposepricetab e where icrowdkindpriceid="
                            + icrowdkindpriceid);
            if (zlos.length != zpalist.size()) {
                rs.addRow(new String[] { "false",
                        eticket.getSztickettypename() + "子票信息不全" });
                return rs;
            }
            for (int j = 0; j < zlos.length; j++) {
                System.out.println(zlos[j]);
                String zlols[] = zlos[j].split("&");
                System.out.println(zlols[0]);
                System.out.println(zlols[1]);
                Long iztickettypeid = new Long(zlols[0]);
                List zplist = ticketTypeService
                        .find(" from Edmticketcomposepricetab e where icrowdkindpriceid="
                                + icrowdkindpriceid
                                + " and itickettypeid="
                                + iztickettypeid);
                if (zplist == null || zplist.size() == 0) {
                    rs.addRow(new String[] { "false",
                            "票价编号为" + icrowdkindpriceid + "无子票价格" });
                    return rs;
                } else {
                    List opwwlist = ticketTypeService
                            .find(" from Opwwicketsettab where itickettypeid="
                                    + itickettypeid + " and izticktypeid="
                                    + iztickettypeid + " and byisuse=1");
                    if (opwwlist.size() == 0) {
                        Edmtickettypetab edticket = (Edmtickettypetab) ticketTypeService
                                .get(Edmtickettypetab.class, itickettypeid);
                        rs.addRow(new String[] { "false",
                                edticket.getSztickettypecode() + "的检票园门数据不全" });
                        return rs;
                    }
                }
                Edmtickettypetab ezticket = (Edmtickettypetab) ticketTypeService.get(
                        Edmtickettypetab.class, iztickettypeid);
                Edmticketcomposepricetab edmticketcomposepricetab = (Edmticketcomposepricetab) zplist
                        .get(0);
                LZorderlist lzo = new LZorderlist();
                String zstdt = zlols[1];
                String ztripid = "0";
                if (zlols.length > 3) {
                    ztripid = zlols[2];
                }
                String zivenueseatsid = "0";
                if (zlols.length > 4) {
                    zivenueseatsid = zlols[3];
                }
                LZorderlistId lzid = new LZorderlistId();
                lzid.setIscenicid(iscenicid);
                lzid.setOrid(orid);
                lzid.setOrderlistid(new Long(i + 1));
                lzid.setZorderlistid(new Long(j + 1));
                lzo.setId(lzid);
                lzo.setIcrowdkindid(eticketprice.getIcrowdkindid());
                lzo.setIcrowdkindpriceid(icrowdkindpriceid);
                lzo.setItickettypeid(itickettypeid);
                lzo.setIztickettypeid(iztickettypeid);
                lzo.setZnumb(numb * edmticketcomposepricetab.getNumb());
                lzo.setZpric(edmticketcomposepricetab.getMactualsaleprice());
                lzo.setZamnt(numb * lzo.getZpric());
                lzo.setZyhnumb(new Long(0));
                lzo.setZyhamnt(new Double(0));
                if (yxq == 0) {
                    lzo.setDtstartdate(lo.getDtstartdate());
                    lzo.setDtenddate(lo.getDtenddate());
                } else {
                    lzo.setDtstartdate(zstdt);
                    String zeddt = Tools.getDate(zstdt, ezticket
                            .getValidityday().intValue() - 1);
                    lzo.setDtenddate(zeddt);
                }

                if (Long.parseLong(ztripid) > 0) {
                    List plist = ticketTypeService
                            .find(" from Prdtripvenuemanage where tripid="
                                    + Long.parseLong(ztripid)
                                    + " and itickettypeid=" + iztickettypeid
                                    + " and startdata<='" + zstdt
                                    + "' and enddata>='" + zstdt + "'");
                    Prdtripvenuemanage p = (Prdtripvenuemanage) plist.get(0);
                    lzo.setTripid(Long.parseLong(ztripid));
                    lzo.setDtstartdate(zstdt + " " + p.getStarttime() + ":00");
                    lzo.setDtenddate(zstdt + " " + p.getEndtime() + ":00");
                    if (zivenueseatsid == null || zivenueseatsid.equals("0")) {
                        lzo.setIvenueareaid(p.getIvenueareaid());
                        lzo.setIvenueid(p.getIvenueid());
                        lzo.setIvenueseatsid(new Long(0));
                    } else {
                        lzo.setIvenueareaid(new Long(zivenueseatsid));
                        lzo.setIvenueid(p.getIvenueid());
                        lzo.setIvenueseatsid(new Long(zivenueseatsid));
                    }

                } else {
                    lzo.setTripid(new Long(0));
                    lzo.setIvenueareaid(new Long(0));
                    lzo.setIvenueid(new Long(0));
                    lzo.setIvenueseatsid(new Long(0));
                }
                zolist.add(lzo);
            }
            lo.setZolist(zolist);
            olist.add(lo);
        }
        lorder.setDtstartdate(sstdt);
        lorder.setDtenddate(seddt);
        lorder.setMont(amnt);
        lorder.setYhamnt(new Double(0));
        lorder.setZfmont(amnt);
        //判断库存数据
        List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
        String stockJson = "";
        for (int i = 0; i < olist.size(); i++) {
            LOrderlist lo = (LOrderlist) olist.get(i);
            StockOrderInfo stockOrderInfo = new StockOrderInfo();
            stockOrderInfo.setOrid(lo.getId().getOrid());
            stockOrderInfo.setProviderId(lo.getId().getIscenicid());
            stockOrderInfo.setProductId(lo.getItickettypeid());
            stockOrderInfo.setPriceId(lo.getIcrowdkindpriceid());
            stockOrderInfo.setUsid("guest");
            stockOrderInfo.setStockDate(lo.getDtstartdate());
            stockOrderInfo.setNumb(lo.getNumb());
            stocks.add(stockOrderInfo);
        }
        StockJson stockObject = new StockJson();
        stockObject.setStocks(stocks);
        stockJson = JSON.toJSONString(stockObject);
        if(WebContant.GetKeyValue("IsCenterUrl").equals("1")){
            try {/*
                javax.xml.rpc.Service service = null;
                java.net.URL endpointURL = new java.net.URL(
                        "http://"
                                + url
                                + "/services/centersaleService?wsdl");
                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                        endpointURL, service);
                ssl.setMaintainSession(true);
                com.ectrip.ticket.centersale.client.ResultBean cano = ssl.saveStock(stockJson, "true");
                if(cano.getResult(0,0).equals("false")){
                    rs.addRow(new String[] { "false",cano.getResult(0,1)});
                    return rs;
                }
            */
            	return null;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }else{
            IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
            try{
                stockService.saveStockDetails(stocks,true);
            }catch(Exception e){
                rs.addRow(new String[] { "false",e.getMessage()});
                return rs;
            }
        }
//        ticketTypeService.save(lorder);
        ecService.saveLorder(lorder);
        for (int i = 0; i < olist.size(); i++) {
            LOrderlist lo = (LOrderlist) olist.get(i);
//           ticketTypeService.save(lo);
            ecService.saveLorderList(lo);
            List zjlist = lo.getZjlist();
            if (zjlist != null && zjlist.size() > 0) {
                for (int j = 0; j < zjlist.size(); j++) {
                    LOrderpeople lop = (LOrderpeople) zjlist.get(j);
                    ticketTypeService.save(lop);
                }
            }
            List zolist = lo.getZolist();
            for (int j = 0; j < zolist.size(); j++) {
                LZorderlist lzo = (LZorderlist) zolist.get(j);
//                ticketTypeService.save(lzo);
                ecService.saveLZorderlist(lzo);
            }
        }
        rs.addRow(new String[] { "true", orid + "&" + amnt });
        return rs;
    }
    /**
     * 自主机保存订单
     */
    public ResultBean autoSaveorder(String message, Long iticketwinid,
                                    Long iemployeeid, String orid, Long iscenicid,String timeParam,String url) {
        /*if(cusService==null)
        {
            cusService=(ICustomService)SpringUtil.getBean("cusService");
        }
        if(shopcartService==null)
        {
            shopcartService=(IShopCartService)SpringUtil.getBean("shopcartService");
        }
        if(providerService==null)
        {
            providerService=(IProviderService)SpringUtil.getBean("providerService");
        }
        if(genericService==null)
        {
            genericService=(IGenericService)SpringUtil.getBean("genericService");
        }*/
        if(url==null||url.length()<1){
            url=WebContant.GetKeyValue("CenterUrl");
        }
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        LOrder lorder = new LOrder();
        lorder.setIticketwinid(iticketwinid);
        lorder.setIemployeeid(iemployeeid);
        LOrderId lid = new LOrderId();
        lid.setOrid(orid);
        lid.setIscenicid(iscenicid);
        lorder.setDdzt("00");
        lorder.setOrda(Tools.getTodayString());
        lorder.setOrti(Tools.getNowTime());
        lorder.setUsid("guest");
        lorder.setId(lid);
        String sstdt = "";
        String seddt = "";
        double amnt = 0;
        String mobile="";
        String zffs="";
        String hqyatuDate="";
        // Esfemployeetab
        // employee=(Esfemployeetab)ticketTypeService.get(Esfemployeetab.class,
        // iemployeeid);
        // Esbticketwintab
        // ewin=(Esbticketwintab)ticketTypeService.get(Esbticketwintab.class,
        // iticketwinid);
        String[] pmessage = message.split(";");
        List olist = new ArrayList();
        for (int i = 0; i < pmessage.length; i++) {
            LOrderlist lo = new LOrderlist();
            LOrderlistId loid = new LOrderlistId();
            loid.setOrid(orid);
            loid.setOrderlistid(new Long(i + 1));
            loid.setIscenicid(iscenicid);
            System.out.println("pmessage[" + i + "]=" + pmessage[i]);
            String los[] = pmessage[i].split(",");
            Long itickettypeid = new Long(los[0]);// 票ID
            List<Map> sonTicketTypeSelect = ticketTypeService.getSonTicketTypeSelect(itickettypeid);
            Map eticket = sonTicketTypeSelect.get(0);
//            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(Edmtickettypetab.class, itickettypeid);
            Long icrowdkindpriceid = new Long(los[1]);
            Edmcrowdkindpricetab eticketprice = crowdKindPriceService.queryCrowdKindPrice(icrowdkindpriceid);
//            Edmcrowdkindpricetab eticketprice = (Edmcrowdkindpricetab) ticketTypeService.get(Edmcrowdkindpricetab.class,icrowdkindpriceid);
            String stdt = los[2];
            Long numb = new Long(los[3]);

            lo.setId(loid);
            lo.setItickettypeid(itickettypeid);
            lo.setIcrowdkindpriceid(icrowdkindpriceid);
            lo.setDtstartdate(stdt);
            String eddt = Tools.getDate(stdt, (Integer.parseInt(eticket.get("validityday").toString())) - 1);
//            String eddt = Tools.getDate(stdt, eticket.getValidityday().intValue() - 1);
            if (sstdt.equals("")) {
                sstdt = stdt;
            } else {
                if (Tools.getDayNumb(stdt, sstdt) <= 0) {
                    sstdt = stdt;
                }
            }
            if (seddt.equals("")) {
                seddt = eddt;
            } else {
                if (Tools.getDayNumb(eddt, seddt) > 1) {
                    seddt = eddt;
                }
            }
            lo.setDtenddate(eddt);
            lo.setNumb(numb);
            lo.setIcrowdkindid(eticketprice.getIcrowdkindid());
            lo.setPric(eticketprice.getMactualsaleprice());
            lo.setAmnt(eticketprice.getMactualsaleprice() * numb);
            lo.setYhnumb(new Long(0));
            lo.setYhamnt(new Double(0));
            lo.setId(loid);
            amnt = amnt + lo.getAmnt();
            /**
             * 判断产品是按子产品自己有效期还是套票有效期
             */
            int yxq = 1;
            Edmticketproduct et = (Edmticketproduct) ticketTypeService.get(Edmticketproduct.class, itickettypeid);
            if (et == null) {
                yxq = 0;
            } else {
                if (et.getInoteger1() == null || et.getInoteger1() == 0) {
                    yxq = 0;
                }
            }
            // 读出证件号码
            System.out.println("los[4]" + los[4]);
            List zjlist = new ArrayList();
            if (eticketprice.getIpeoplenumrange().longValue() == 1) {
                String zjs = los[4];
                if (zjs != null && !zjs.equals("")) {
                    String zjls[] = zjs.split("[|]");
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    if (zjls.length != numb) {
                        rs.addRow(new String[] { "false", eticket.get("sztickettypename") + "实名制的身份证信息数量不对" });
//                        rs.addRow(new String[] { "false", eticket.getSztickettypename() + "实名制的身份证信息数量不对" });
                        return rs;
                    } else {
                        for (int j = 0; j < zjls.length; j++) {
                            String zjp[] = zjls[j].split("&");
                            LOrderpeople lop = new LOrderpeople();
                            LOrderpeopleId lpid = new LOrderpeopleId();
                            lpid.setIscenicid(iscenicid);
                            lpid.setOrderlistid(new Long(i + 1));
                            lpid.setOrid(orid);
                            lpid.setPeopleid(new Long(j));
                            lop.setPname(zjp[1].trim());
                            lop.setPzjhm(zjp[0].trim());
                            lop.setId(lpid);
                            zjlist.add(lop);
                            if (map.containsKey(zjp[0].trim())) {
                                map.put(zjp[0].trim(), map.get(zjp[0].trim()) + 1);
                            } else {
                                map.put(zjp[0].trim(), 1);
                            }
                        }
                        String mess = CommonCheckUtil.checkLimitSaleByCard(map, 1, stdt);
                        if (!StringUtils.isBlank(mess)) {
                            rs.addRow(new String[] { "false", message });
                            return rs;
                        }
                    }
                } else {
                    rs.addRow(new String[] { "false",eticket.get("sztickettypename") + "实名制票务必须登记身份证" });
//                    rs.addRow(new String[] { "false", eticket.getSztickettypename() + "实名制票务必须登记身份证" });
                    return rs;
                }
            }
            lo.setZjlist(zjlist);
            // 子票数据
            System.out.println("los[5]=" + los[5]);
            String zlo = los[5];
            String zlos[] = zlo.split("[|]");
            List zolist = new ArrayList();
            System.out.println(zlos.length);
            List zpalist = ticketTypeService
                    .find(" from Edmticketcomposepricetab e where icrowdkindpriceid=" + icrowdkindpriceid);
            if (zlos.length != zpalist.size()) {
                rs.addRow(new String[] { "false", eticket.get("sztickettypename") + "子票信息不全" });
//                rs.addRow(new String[] { "false", eticket.getSztickettypename() + "子票信息不全" });
                return rs;
            }
            for (int j = 0; j < zlos.length; j++) {
                System.out.println(zlos[j]);
                String zlols[] = zlos[j].split("&");
                System.out.println(zlols[0]);
                System.out.println(zlols[1]);
                Long iztickettypeid = new Long(zlols[0]);
                List zplist = ticketTypeService.find(" from Edmticketcomposepricetab e where icrowdkindpriceid="
                        + icrowdkindpriceid + " and itickettypeid=" + iztickettypeid);
                if (zplist == null || zplist.size() == 0) {
                    rs.addRow(new String[] { "false", "票价编号为" + icrowdkindpriceid + "无子票价格" });
                    return rs;
                } else {
                    List opwwlist = ticketTypeService.find(" from Opwwicketsettab where itickettypeid=" + itickettypeid
                            + " and izticktypeid=" + iztickettypeid + " and byisuse=1");
                    if (opwwlist.size() == 0) {
                        Edmtickettypetab edticket = (Edmtickettypetab) ticketTypeService.get(Edmtickettypetab.class,
                                itickettypeid);
                        rs.addRow(new String[] { "false", edticket.getSztickettypecode() + "的检票园门数据不全" });
                        return rs;
                    }
                }
                Edmtickettypetab ezticket = (Edmtickettypetab) ticketTypeService.get(Edmtickettypetab.class, iztickettypeid);
                Edmticketcomposepricetab edmticketcomposepricetab = (Edmticketcomposepricetab) zplist.get(0);
                LZorderlist lzo = new LZorderlist();
                String zstdt = zlols[1];
                String ztripid = "0";
                if (zlols.length > 3) {
                    ztripid = zlols[2];
                }
                String zivenueseatsid = "0";
                if (zlols.length > 4) {
                    zivenueseatsid = zlols[3];
                }
                LZorderlistId lzid = new LZorderlistId();
                lzid.setIscenicid(iscenicid);
                lzid.setOrid(orid);
                lzid.setOrderlistid(new Long(i + 1));
                lzid.setZorderlistid(new Long(j + 1));
                lzo.setId(lzid);
                lzo.setIcrowdkindid(eticketprice.getIcrowdkindid());
                lzo.setIcrowdkindpriceid(icrowdkindpriceid);
                lzo.setItickettypeid(itickettypeid);
                lzo.setIztickettypeid(iztickettypeid);
                lzo.setZnumb(numb * edmticketcomposepricetab.getNumb());
                lzo.setZpric(edmticketcomposepricetab.getMactualsaleprice());
                lzo.setZamnt(numb * lzo.getZpric());
                lzo.setZyhnumb(new Long(0));
                lzo.setZyhamnt(new Double(0));
                if (yxq == 0) {
                    lzo.setDtstartdate(lo.getDtstartdate());
                    lzo.setDtenddate(lo.getDtenddate());
                } else {
                    lzo.setDtstartdate(zstdt);
                    String zeddt = Tools.getDate(zstdt, ezticket.getValidityday().intValue() - 1);
                    lzo.setDtenddate(zeddt);
                }

                if (Long.parseLong(ztripid) > 0) {
                    List plist = ticketTypeService.find(
                            " from Prdtripvenuemanage where tripid=" + Long.parseLong(ztripid) + " and itickettypeid="
                                    + iztickettypeid + " and startdata<='" + zstdt + "' and enddata>='" + zstdt + "'");
                    Prdtripvenuemanage p = (Prdtripvenuemanage) plist.get(0);
                    lzo.setTripid(Long.parseLong(ztripid));
                    lzo.setDtstartdate(zstdt + " " + p.getStarttime() + ":00");
                    lzo.setDtenddate(zstdt + " " + p.getEndtime() + ":00");
                    if (zivenueseatsid == null || zivenueseatsid.equals("0")) {
                        lzo.setIvenueareaid(p.getIvenueareaid());
                        lzo.setIvenueid(p.getIvenueid());
                        lzo.setIvenueseatsid(new Long(0));
                    } else {
                        lzo.setIvenueareaid(new Long(zivenueseatsid));
                        lzo.setIvenueid(p.getIvenueid());
                        lzo.setIvenueseatsid(new Long(zivenueseatsid));
                    }

                } else {
                    lzo.setTripid(new Long(0));
                    lzo.setIvenueareaid(new Long(0));
                    lzo.setIvenueid(new Long(0));
                    lzo.setIvenueseatsid(new Long(0));
                }
                zolist.add(lzo);
            }
            lo.setZolist(zolist);
            //新增手机号
            if(los.length==7)
            {
                mobile=los[6];
                lo.setNotef(mobile);
            }
            if(los.length==8)
            {
                mobile=los[6];
                lo.setNotef(mobile);
                zffs=los[7];
            }
            if(los.length==9){
                mobile=los[6];
                lo.setNotef(mobile);
                zffs=los[7];
                String timeId = los[8];
                String hql = "from TimeSharingTicketTab where id = "+ timeId;
                List find = ticketTypeService.find(hql);
                if(find != null && find.size() > 0) {
                    TimeSharingTicketTab timeSharingTicketTab = (TimeSharingTicketTab) find.get(0);
                    lo.setNoteb(timeSharingTicketTab.getStartDate());
                    lo.setNotec(timeSharingTicketTab.getEndDate());
                    lo.setNoted(timeSharingTicketTab.getProductId());
                    lo.setNotee(timeSharingTicketTab.getId()+"");
                }
            }
            olist.add(lo);
        }
        lorder.setDtstartdate(sstdt);
        lorder.setDtenddate(seddt);
        lorder.setMont(amnt);
        lorder.setYhamnt(new Double(0));
        lorder.setZfmont(amnt);
        //新增支付方式
        lorder.setZffs(zffs);
        // 判断库存数据
        List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
        String stockJson = "";
        List<ProductRequest> products=new ArrayList<ProductRequest>();
        for (int i = 0; i < olist.size(); i++) {
            LOrderlist lo = (LOrderlist) olist.get(i);
            StockOrderInfo stockOrderInfo = new StockOrderInfo();
            stockOrderInfo.setOrid(lo.getId().getOrid());
            stockOrderInfo.setProviderId(lo.getId().getIscenicid());
            stockOrderInfo.setProductId(lo.getItickettypeid());
            stockOrderInfo.setPriceId(lo.getIcrowdkindpriceid());
            stockOrderInfo.setUsid("guest");
            stockOrderInfo.setStockDate(lo.getDtstartdate());
            stockOrderInfo.setNumb(lo.getNumb());
            stocks.add(stockOrderInfo);


            //lizhaodong 新增
            hqyatuDate = lo.getDtenddate();
            ProductRequest productRequest = new ProductRequest();
            productRequest.setExternalId(lo.getItickettypeid());
            Edmtickettypetab ticket = (Edmtickettypetab) ticketTypeService.get(Edmtickettypetab.class,lo.getItickettypeid());
            productRequest.setName(ticket.getSztickettypename());
            productRequest.setPrice(lo.getPric());
            productRequest.setNumb(lo.getNumb());
            products.add(productRequest);
        }
        StockJson stockObject = new StockJson();
        stockObject.setStocks(stocks);
        stockJson = JSON.toJSONString(stockObject);
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {/*
                javax.xml.rpc.Service service = null;
                java.net.URL endpointURL = new java.net.URL(
                        "http://" + url + "/services/centersaleService?wsdl");
                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(endpointURL, service);
                ssl.setMaintainSession(true);
                com.ectrip.ticket.centersale.client.ResultBean cano = ssl.saveStock(stockJson, "true");
                if (cano.getResult(0, 0).equals("false")) {
                    rs.addRow(new String[] { "false", cano.getResult(0, 1) });
                    return rs;
                }
            */} catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
//            IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
            try {
                stockService.saveStockDetails(stocks, true);
            } catch (Exception e) {
                rs.addRow(new String[] { "false", e.getMessage() });
                return rs;
            }
        }
//        ticketTypeService.save(lorder);
        ecService.saveLorder(lorder);
        for (int i = 0; i < olist.size(); i++) {
            LOrderlist lo = (LOrderlist) olist.get(i);
//            ticketTypeService.save(lo);
            ecService.saveLorderList(lo);
            List zjlist = lo.getZjlist();
            if (zjlist != null && zjlist.size() > 0) {
                for (int j = 0; j < zjlist.size(); j++) {
                    LOrderpeople lop = (LOrderpeople) zjlist.get(j);
                    ticketTypeService.save(lop);
                }
            }
            List zolist = lo.getZolist();
            for (int j = 0; j < zolist.size(); j++) {
                LZorderlist lzo = (LZorderlist) zolist.get(j);
//                ticketTypeService.save(lzo);
                //TODO
                ecService.saveLZorderlist(lzo);
            }
        }
        LOrderId lOrderId = new LOrderId(orid,iscenicid);
        Map ma = (Map) ecService.getLorderByLOrderId(lOrderId);
        LOrder m = new LOrder();
        Map mapid = (Map) ma.get("id");
        LOrderId id = new LOrderId();
        
        try {
        	BeanUtils.populate(id, mapid);
        	ma.remove("id");
        	m.setId(id);
			BeanUtils.populate(m, ma);
		} catch (IllegalAccessException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
        Custom custom=ecService.findByMobile(mobile);
        boolean hqyt = CommonUtil.isHqyt();
        if (!hqyt) {
            rs.addRow(new String[] { "false","未启用环球支付"});
        } else {
            if(custom==null)
            {
                custom = ecService.anonymityUserCreate("02", mobile);
                //同步注册结算
                ClientRequest clientRequest = new ClientRequest();

                clientRequest.setRegistrationName(custom.getNote6());
                clientRequest.setBusinessTel(mobile);
                clientRequest.setPassword(custom.getPassword()); //明文给结算-keycloak

                HqytClient client = new HqytClient();
                JSONClient jsonClient = null;
                try {
                    jsonClient = client.createClient(clientRequest);
                } catch(Exception e) {
                    //结算失败，删除电商记录
                	ecService.deleteCustom(custom);
                    //抛出结算接口异常
                    rs.addRow(new String[] { "false","HQYT:"+ e.getMessage()});
                }
                if(jsonClient!=null) {
                    //TODO写入结算系统的regeistrationName和ID
                    custom.setNote6(jsonClient.getRegistrationName()); //结算client的RegistrationName
                    custom.setNote9(jsonClient.getId().toString());  //结算client的ID
                    ecService.updateCustom(custom);
                }else {
                    rs.addRow(new String[] { "false","HQYT:" + "结算系统接口返回异常"});
                }
            }
            //生成订单
            GuaranteeRequest request = new GuaranteeRequest();
            request.setOrid(m.getId().getOrid());
            request.setTotalMoney(m.getZfmont());
            request.setUsid(custom.getUsid());
            request.setUsername(custom.getUsid());
            request.setPhone(custom.getMobile());
            ProviderCompany pc = providerService.queryProviderCompany(iscenicid);
            if(pc != null){
                request.setIssuerId(pc.getHqytId().toString());
            }else{
                System.out.println("============>订单作废002");
                this.ecService.updateOrderDdzt(orid,"98");
                rs.addRow(new String[] { "false", "服务商未开户不可预定" });
            }
            HqytClient client = new HqytClient();
            request.setProducts(products);
            //同步消费有效期
            request.setConsumeTimeLimit(hqyatuDate);

            LegalPersonRequest receiver = new LegalPersonRequest();
            if(custom.getNote9()==null) {
                System.out.println("============>订单作废003");
                ecService.updateOrderDdzt(orid,"98");  //电商-订单作废
                rs.addRow(new String[] { "false", "用户没有同步结算系统的用户ID" });
            }else {
            	receiver.setId(Long.valueOf(custom.getNote9()));
            }
            request.setReceiver(receiver);

            JSONInvoice invoice = null;
            try {
                request.setTitle("散客订单");
                invoice = client.createGuaranteeSk(request);
            } catch(Exception e) {
                System.out.println("============>订单作废004");
                e.printStackTrace();
                System.out.println("============>订单作废004ooooo");
                ecService.updateOrderDdzt(orid,"98");
                //抛出结算接口异常
                rs.addRow(new String[] { "false", "HQYT:" + e.getMessage()});
            }
            if(invoice != null){
                m.setNoteh(invoice.getId().toString());
                ecService.updateLOrder(m);
            }else{
                System.out.println("============>订单作废005");
                ecService.updateOrderDdzt(orid,"98");
                rs.addRow(new String[] { "false", "创建支付订单失败"});
            }
            /*Map<String, String> merchant = CommonUtil.findMerchant();
            SortedMap<String, Object> params = new TreeMap<String, Object>();
            params.put("merchantId", merchant.get("merchantId"));
            params.put("outTradeNo", m.getId().getOrid());
            params.put("returnUrl", CommonUtil.findSmfReturnUrl());
            String sign = MD5Util.createSign(params, merchant.get("key"), "UTF-8");
            params.put("sign", sign);
            //访问链接
            String hqytUrl = CommonUtil.findWxPayUrl() + "?";
            List<String> keys = new ArrayList<String>(params.keySet());
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                String value = String.valueOf(params.get(key));

                //拼接时，不包括最后一个&字符
                if (i == keys.size() - 1) {
                    hqytUrl = hqytUrl + key + "=" + value;
                } else {
                    hqytUrl = hqytUrl + key + "=" + value + "&";
                }
            }*/
        }
        rs.addRow(new String[] { "true", orid + "&" + amnt });
        return rs;
    }

    public ResultBean payOrder(String orid, Long iscenicid, Double yingshou,
                               Double shishou, Double zhaoling, Long iticketwinid,
                               Long iemployeeid, String zffs, Long maxid) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        // 读取订单信息
        ResultBean rb = new ResultBean();
        rb.setColumnCount(9);
        rb.setColumnNames(new String[] { "bymediatype", "bymaketicketway",
                "Yuanmenshuj", "mactualsaleprice", "dtstartdate", "dtenddate",
                "szticketprintno", "dayin", "fujuan" });

        LOrderId lid = new LOrderId();
        lid.setOrid(orid);
        lid.setIscenicid(iscenicid);
        Map map = (Map) ecService.getLorderByLOrderId(lid);
        Map mapid = (Map) map.get("id");
        LOrderId lOrderId = new LOrderId(mapid.get("orid").toString(),Long.valueOf(mapid.get("iscenicid").toString()));
        LOrder lorder = new LOrder();
        lorder.setId(lOrderId);
        map.remove("id");
        try {
			BeanUtils.populate(lorder, map);
		} catch (IllegalAccessException | InvocationTargetException e2) {
			e2.printStackTrace();
		}
        if (lorder == null) {
            rs.addRow(new String[] { "false", "该订单不存在" });
            return rs;
        }

        if (lorder.getZfmont().doubleValue() != yingshou.doubleValue()) {
            rs.addRow(new String[] { "false",
                    lorder.getZfmont() + "实收金额与订单金额不等" + yingshou });
            return rs;
        }
        if (lorder.getIticketwinid().longValue() != iticketwinid.longValue()) {
            rs.addRow(new String[] { "false", "售票窗口ID不对" });
            return rs;
        }
        if (lorder.getIemployeeid().longValue() != iemployeeid.longValue()) {
            rs.addRow(new String[] { "false", "售票员ID不对" });
            return rs;
        }

        Stssalesvouchertab s = new Stssalesvouchertab();
        s.setIscenicid(iscenicid);

        s.setIticketwinid(iticketwinid);
        s.setIbusinessid(new Long(1));
        s.setIhandler(iemployeeid);
        s.setIpayeer(iemployeeid);
        s.setImaker(iemployeeid);
        s.setIauditor(iemployeeid);
        s.setIaccountreceivable(yingshou);
        s.setIacceptmoney(shishou);
        s.setIgivechange(zhaoling);

        String today = Tools.getDays();
        String daytime = Tools.getDayTimes();
        s.setIyear(new Long(today.substring(0, 4)));
        s.setImonth(new Long(today.substring(5, 7)));
        s.setIday(new Long(today.substring(8, 10)));
        s.setDtmakedate(daytime);
        s.setDtauditdate(today);
        s.setUsid(lorder.getUsid());
        s.setDyusid("daoyou");
        s.setBisintegral(new Long(0));
        s.setByprintinvoice(new Long(0));
        s.setBysplitway(new Long(2));
        s.setBisreturn(new Long(1));
        s.setBysalesvouchertype("01");
        s.setBypostrecord(new Long(0));
        s.setBysalesvoucherstate(new Long(1));
        s.setBispay(new Long(0));
        s.setBispayee(new Long(0));
        Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(
                Esbticketwintab.class, s.getIticketwinid());
        StssalesvouchertabId id = new StssalesvouchertabId();
        id.setIticketstationid(e.getIticketstationid());
        id.setIsalesvoucherid(maxid);
        s.setId(id);
        s.setSzsalesvoucherno(orid);
        //自助机售票订单标识
        s.setOrdersource("zzj");
        Stssalessettlementtab st = this.saveStssalessettlementtab(s, zffs);
//        List olist = ticketTypeService.find(" from LOrderlist where id.orid='" + orid
//                + "' and id.iscenicid=" + iscenicid);
        List<LOrderlist> olist = ecService.getLOrderlistByids(orid,iscenicid);
        if (olist == null || olist.size() == 0) {
            rs.addRow(new String[] { "false", "没有售票明细" });
            return rs;
        }
        Esbticketstationtab esbticketstation = (Esbticketstationtab) ticketTypeService
                .get(Esbticketstationtab.class, e.getIticketstationid());
        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, iscenicid);
        List detaillist = new ArrayList();

        int snumb = 0;// 总张数
        int dnumb = 1;// 第几张
        for (int i = 0; i < olist.size(); i++) {
            LOrderlist lo = (LOrderlist) olist.get(i);
            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                    Edmtickettypetab.class, lo.getItickettypeid());
            // 保存售票凭证明细
            Stssalesvoucherdetailstab sd = this.saveStssalesvoucherdetailstab(
                    s, lo, eticket);
//
//            List zolist = ticketTypeService.find(" from LZorderlist where id.orid='"
//                    + orid + "' and id.iscenicid=" + iscenicid
//                    + " and id.orderlistid=" + lo.getId().getOrderlistid());
            List zolist = ecService.getLZorderlistByParam(orid, iscenicid, lo.getId().getOrderlistid());
            // 保存凭证子票明细表
            sd = SaveStscomticketsalesdetailstab(sd, zolist);
            Edmcrowdkindpricetab eticketprice = (Edmcrowdkindpricetab) ticketTypeService
                    .get(Edmcrowdkindpricetab.class, lo.getIcrowdkindpriceid());

            if (eticket.getByusage() == 0) {
                // 一票一人
                if (eticket.getBymaketicketway().equals("00")) {
                    // 现场打印
                    List zjlist = new ArrayList();
                    if (eticketprice.getIpeoplenumrange().longValue() == 1) {
                        // 实名制票必须要有身份证信息
                        zjlist = ticketTypeService
                                .find(" from LOrderpeople where id.orid='"
                                        + orid + "' and id.iscenicid="
                                        + iscenicid + " and id.orderlistid="
                                        + lo.getId().getOrderlistid());
                    }

                    sd = this
                            .saveStssoldtickettabug0make00(sd, s,
                                    esbticketstation.getSzstationcode(),
                                    scenic.getSzsceniccode(),
                                    eticket.getSztickettypecode(),
                                    eticketprice, zjlist);

                } else if (eticket.getBymaketicketway().equals("01")) {
                    // 现场激活
                    rs.addRow(new String[] { "false", "不支持现场激活票务" });
                    return rs;
                }
            } else {
                if (eticket.getBymaketicketway().equals("00")) {
                    sd = this.saveStssoldtickettabug1make00(sd, s,
                            esbticketstation.getSzstationcode(),
                            scenic.getSzsceniccode(),
                            eticket.getSztickettypecode(),
                            eticketprice.getIcrowdkindid());

                } else if (eticket.getBymaketicketway().equals("01")) {
                    // 现场激活
                    rs.addRow(new String[] { "false", "不支持现场激活票务" });
                    return rs;
                }
            }

            detaillist.add(sd);
            snumb = snumb + sd.getMplist().size();
        }

        // 读取打印顺序设置

        // 读取副卷打印设置
        List sprintlist = this
                .getsodeprintmanage(iscenicid, s.getIbusinessid());
//        List syslist = ticketTypeService.find(" from Sysparv5 where id.pmky='PRSZ'");
        List syslist = sysService.findSysparByPmky("PRSZ", "");
        if (sprintlist != null && sprintlist.size() > 0) {
            for (int m = 0; m < sprintlist.size(); m++) {
                Soderollprintmanage p = (Soderollprintmanage) sprintlist.get(m);
                for (int n = 0; n < syslist.size(); n++) {
                    Sysparv5 sv5 = (Sysparv5) syslist.get(n);
                    if (sv5.getId().getPmcd().equals(p.getId().getPrintno())) {
                        p.setSzprintno(sv5.getPmva());
                        p.setPrintype(sv5.getIsa().toString());
                        if (sv5.getIsa() == 0) {
                            p.setNote(sv5.getPmvb());
                        }
                    }
                }
            }
        }

        try {
            if(!StringUtils.isBlank(zffs)){
                List<PaymentBill> paymentBillByOrid = ecService.getPaymentBillByOrid(orid);
                if(paymentBillByOrid.size()>0){
                	PaymentBill bill = paymentBillByOrid.get(0);
                    bill.setOrid(orid);
                    ticketTypeService.update(bill);
                    lorder.setDdzt("11");
                    ticketTypeService.update(lorder);
                }
            }
            ticketTypeService.save(s);
            ticketTypeService.save(st);
            System.out.println("开始保存数据2");
            Sysparv5 sv5 = sysService.findOne("PRCS", "01");
            String printcs = "0";
            if (sv5 != null) {
                printcs = sv5.getPmva();
            }
            for (int i = 0; i < detaillist.size(); i++) {
                Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
                        .get(i);
                System.out.println("Isalesvoucherdetailsid="
                        + sd.getId().getIsalesvoucherdetailsid());
                ticketTypeService.save(sd);

                List zdetaillist = sd.getComlist();
                for (int j = 0; j < zdetaillist.size(); j++) {
                    Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                            .get(j);
                    System.out.println("Isalesvoucherdetailsid="
                            + zstd.getId().getIsalesvoucherdetailsid());

                    ticketTypeService.save(zstd);
                }

                List cdetaillist = sd.getMplist();
                for (int j = 0; j < cdetaillist.size(); j++) {
                	//售出门票表
                    Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist
                            .get(j);
                    // 添加返回数据
                    Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService
                            .get(Edmtickettypetab.class,
                                    stsv.getItickettypeid());
                    String bymediatype = eticket.getBymediatype();// 介质类型
                    String bymaketicketway = eticket.getBymaketicketway();// 出票方式
                    String Yuanmenshuj = "";// 园门数据
                    String mactualsaleprice = sd.getMactualsaleprice()
                            .toString();// 单价
                    String dtstartdate = stsv.getDtstartdate();// 开始日期
                    String dtenddate = stsv.getDtenddate();// 结束日期
                    String szticketprintno = stsv.getSzticketprintno();
                    szticketprintno = changeSzprintNo(szticketprintno,orid,s.getUsid(),eticket);

                    ticketTypeService.save(stsv);

                    List cdzetaillist = stsv.getZlist();
                    for (int k = 0; k < cdzetaillist.size(); k++) {
                    	//售出门票子表
                        Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
                                .get(k);
                        ticketTypeService.save(stss);
                    }
                    List clist = stsv.getClist();
                    for (int k = 0; k < clist.size(); k++) {
                        Stsschecktab stss = (Stsschecktab) clist.get(k);
                        ticketTypeService.save(stss);
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
                                    Long printid = ticketTypeService
                                            .getSequenceId("PRINT_ID");
                                    t.setPrintid(printid);
                                    ticketTypeService.save(t);
                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                    System.out.println(e1.getMessage());
                                }
                            }
                        }
                    }
                    if (eticket.getBymaketicketway().equals("00")) {

                        String dayin = CommonUtil.getprintmessage(s, stsv, eticket,
                                esbticketstation,false);

                        String fujian = CommonUtil.getfprintmessage(sprintlist, s,
                                stsv, eticket, esbticketstation);

                        rb.addRow(new String[] { bymediatype, bymaketicketway,
                                Yuanmenshuj, mactualsaleprice, dtstartdate,
                                dtenddate, szticketprintno, dayin, fujian });
                    } else {
                        rb.addRow(new String[] { bymediatype, bymaketicketway,
                                Yuanmenshuj, mactualsaleprice, dtstartdate,
                                dtenddate, szticketprintno, "", "" });
                    }

                }
            }

        } catch (Exception ex) {
            System.out.println("dddd,Exception" + e.toString());
            throw new RuntimeException(ex.getMessage());
        }
        return rb;
    }

    // 结算凭证表
    public Stssalessettlementtab saveStssalessettlementtab(
            Stssalesvouchertab s, String zffs) {
        Stssalessettlementtab st = new Stssalessettlementtab();
        StssalessettlementtabId sid = new StssalessettlementtabId();
        sid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
        sid.setIticketstationid(s.getId().getIticketstationid());
        sid.setIsalessettlementid(new Long(1));
        st.setId(sid);
        st.setSettlementdata(s.getDtauditdate());
        st.setSettlementtime(Tools.getDayTimes().substring(11));
        st.setIsettlementid(zffs);
        st.setMsettlementmoney(s.getIacceptmoney());
        st.setIversion(new Long(1));
        st.setDtmakedate(Tools.getDayTimes());
        return st;
    }

    public Stssalesvoucherdetailstab saveStssalesvoucherdetailstab(
            Stssalesvouchertab s, LOrderlist lo, Edmtickettypetab eticket) {
        Stssalesvoucherdetailstab sd = new Stssalesvoucherdetailstab();
        StssalesvoucherdetailstabId sdid = new StssalesvoucherdetailstabId();
        sdid.setIsalesvoucherdetailsid(lo.getId().getOrderlistid());
        sdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
        sdid.setIticketstationid(s.getId().getIticketstationid());
        sd.setId(sdid);
        sd.setIticketwinid(s.getIticketwinid());
        sd.setIcrowdkindpriceid(lo.getIcrowdkindpriceid());
        sd.setItickettypeid(lo.getItickettypeid());
        if (eticket.getByusage() == 0) {
            sd.setIplayerperticket(new Long(1));// 人/张
            sd.setIticketplayer(lo.getNumb());// 人次
            sd.setIticketnum(lo.getNumb());
        } else {
            sd.setIticketnum(new Long(1));// 张数
            sd.setIticketplayer(lo.getNumb());
            sd.setIplayerperticket(lo.getNumb());// 人/张
        }
        sd.setDtstartdate(lo.getDtstartdate());
        sd.setDtenddate(lo.getDtenddate());
        sd.setSzstartserial("0");
        sd.setSzendserial("0");
        sd.setIstartid(new Long(0));
        sd.setIendid(new Long(0));
        sd.setIoffersschemeid(new Long(0));
        sd.setIamount(lo.getNumb());
        sd.setIpresentnums(new Long(0));// 退订数量
        sd.setIderatenums(lo.getYhnumb());
        sd.setIfactnum(new Long(0));
        sd.setIuseablenessnum(lo.getNumb());// 使用数量
        sd.setMactualsaleprice(lo.getPric());// 实际售价
        sd.setMeventmoney(lo.getAmnt());// 实际发生金额
        sd.setMderatemoney(lo.getYhamnt());// 减免金额
        sd.setMpresentmoney(new Double(0));// 退订金额
        sd.setMnominalfee(new Double(0));// 工本费
        sd.setMdeposit(new Double(0));
        sd.setMhandcharge(new Double(0));
        sd.setByconsumetype("00");
        sd.setIconsumenum(new Double(0));
        sd.setMtotalamount(lo.getAmnt());
        sd.setItotalnumber(lo.getNumb());// 总次数
        sd.setItotalminutes(new Long(0));
        sd.setByisout(new Long(1));
        sd.setDtmakedate(Tools.getDayTimes());
        sd.setIversion(new Long(0));
        sd.setTimeStart(lo.getNoteb());
        sd.setTimeEnd(lo.getNotec());
        return sd;
    }

    public Stssalesvoucherdetailstab SaveStscomticketsalesdetailstab(
            Stssalesvoucherdetailstab sd, List zolist) {
        System.out.println("Stssalesvoucherdetailstab1");
        List zdetaillist = new ArrayList();
        for (int i = 0; i < zolist.size(); i++) {
            LZorderlist zlo = (LZorderlist) zolist.get(i);
            Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
            StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
            Long isalesvoucherdetailsid = new Long(zlo.getId().getOrderlistid());
            Long icrowdkindpriceid = new Long(sd.getIcrowdkindpriceid());
            Long itickettypeid = new Long(sd.getItickettypeid());
            Long iztickettypeid = new Long(zlo.getIztickettypeid());
            Long isplitamount = zlo.getZnumb();
            Long tripid = new Long(0);
            Long ivenueareaid = new Long(0);
            Long ivenueseatsid = new Long(0);
            String dtstartdate = zlo.getDtstartdate();
            String dtenddate = zlo.getDtenddate();
            if (zlo.getTripid() != null) {
                tripid = zlo.getTripid();
            }
            if (zlo.getIvenueareaid() != null) {
                ivenueareaid = zlo.getIvenueareaid();
            }
            if (zlo.getIvenueareaid() != null) {
                ivenueseatsid = zlo.getIvenueseatsid();
            }
            zstdid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
            zstdid.setIticketstationid(sd.getId().getIticketstationid());
            zstdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
            zstd.setIcrowdkindpriceid(icrowdkindpriceid);
            zstd.setItickettypeid(itickettypeid);
            zstd.setIztickettypeid(iztickettypeid);
            zstd.setMhandcharge(new Double(0));
            zstd.setDtmakedate(Tools.getDayTimes());
            zstd.setTimeStart(sd.getTimeStart());
            zstd.setTimeEnd(sd.getTimeEnd());
            Prdtripvenuemanage p = null;
            System.out.println("Stssalesvoucherdetailstab2");
            if (tripid.longValue() > 0) {
                List plist = ticketTypeService
                        .find(" from Prdtripvenuemanage where tripid=" + tripid
                                + " and itickettypeid=" + iztickettypeid
                                + " and startdata<='" + dtstartdate
                                + "' and enddata>='" + dtstartdate + "'");
                p = (Prdtripvenuemanage) plist.get(0);
                zstd.setTripid(tripid);
                zstd.setDtstartdate(dtstartdate + " " + p.getStarttime()
                        + ":00");
                zstd.setDtenddate(dtstartdate + " " + p.getEndtime() + ":00");
                Calendar now = Calendar.getInstance(TimeZone
                        .getTimeZone("GMT+08:00"));
                SimpleDateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                if (ivenueareaid == 0) {
                    zstd.setIvenueareaid(p.getIvenueareaid());
                    zstd.setIvenueid(p.getIvenueid());
                } else {
                    zstd.setIvenueareaid(ivenueareaid);
                    zstd.setIvenueid(p.getIvenueid());
                }
                zstd.setIvenueseatsid(ivenueseatsid);
            } else {
                zstd.setTripid(new Long(0));
                zstd.setIvenueareaid(new Long(0));
                zstd.setIvenueid(new Long(0));
                zstd.setIvenueseatsid(new Long(0));
                if(dtstartdate.length() > 10){
                    zstd.setDtstartdate(dtstartdate);
                    zstd.setDtenddate(dtenddate);
                }else{
                    zstd.setDtstartdate(dtstartdate+" 00:00:00");
                    zstd.setDtenddate(dtenddate+" 23:59:59");
                }
                Edmtickettypetab eticket = (Edmtickettypetab) this.ticketTypeService
                        .get(Edmtickettypetab.class,
                                new Long(zlo.getItickettypeid()));
                if (eticket != null && eticket.getInt1() != null
                        && eticket.getInt1() == 1) {
                    // 有效开始时间已当前时间开始
                    if(zstd.getDtstartdate().substring(0,10).equals(Tools.getDays())){
                        String nowtime=Tools.getNowTime();
                        zstd.setDtstartdate(zstd.getDtstartdate().substring(0,10)+" "+nowtime);
                        zstd.setDtenddate(Tools.getDate(zstd.getDtenddate().substring(0,10), 1) + " "
                                + nowtime);
                    }else{
                        zstd.setDtstartdate(zstd.getDtstartdate().substring(0,10)+" 00:00:00");
                        zstd.setDtenddate(zstd.getDtenddate().substring(0,10) + " 23:59:59");
                    }

                }
            }
            System.out.println("Stssalesvoucherdetailstab3");
            zstd.setIversion(new Long(0));
            List list = ticketTypeService
                    .find(" from Edmticketcomposepricetab where id.icrowdkindpriceid="
                            + icrowdkindpriceid
                            + " and itickettypeid='"
                            + iztickettypeid + "'");
            Edmticketcomposepricetab edt = (Edmticketcomposepricetab) list
                    .get(0);
            zstdid.setIcomticketsalesdetailsid(edt.getId()
                    .getIticketcomposepriceid());
            zstd.setId(zstdid);
            zstd.setIsplitamount(isplitamount * edt.getNumb());
            zstd.setMsplitprice(edt.getMactualsaleprice());
            zstd.setMsplitmoney(edt.getMactualsaleprice() * edt.getNumb()
                    * isplitamount);
            zstd.setIderatenums(sd.getIderatenums());
            zstd.setMderatemoney(sd.getIderatenums()
                    * edt.getMactualsaleprice());
            zdetaillist.add(zstd);
        }
        sd.setComlist(zdetaillist);
        return sd;
    }

    public Stssalesvoucherdetailstab saveStssoldtickettabug0make00(
            Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
            String szstationcode, String szsceniccode, String sztickettypecode,
            Edmcrowdkindpricetab eticketprice, List zjlist) {
        // 读取游客类型
        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, s.getIscenicid());
        Edpcrowdkindtab edkind = (Edpcrowdkindtab) ticketTypeService.get(
                Edpcrowdkindtab.class, eticketprice.getIcrowdkindid());
        Edmtickettypetab edmticket = (Edmtickettypetab) this.ticketTypeService.get(
                Edmtickettypetab.class, sd.getItickettypeid());
        Edmticketproduct ep = (Edmticketproduct) this.ticketTypeService.get(
                Edmticketproduct.class, sd.getItickettypeid());
        List cdetaillist = new ArrayList();
        for (int j = 1; j <= sd.getIticketnum().intValue(); j++) {
            Stssoldtickettab stsv = new Stssoldtickettab();
            StssoldtickettabId stsvid = new StssoldtickettabId();
            stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
            stsvid.setIticketstationid(sd.getId().getIticketstationid());
            stsvid.setIsalesvoucherdetailsid(sd.getId()
                    .getIsalesvoucherdetailsid());
            stsvid.setSzsoldticketid(new Long(j));
            stsv.setId(stsvid);
            stsv.setIscenicid(s.getIscenicid());
            stsv.setIcrowdkindid(eticketprice.getIcrowdkindid());
            stsv.setSzcrowdkindname(edkind.getSzcrowdkindname());
            stsv.setItickettypeid(sd.getItickettypeid());
            stsv.setUsid(s.getUsid());
            stsv.setIbusinessid(s.getIbusinessid());
            stsv.setDyusid(s.getDyusid());
            stsv.setIplayerperticket(new Long(1));// 人次数
            stsv.setDtstartdate(sd.getDtstartdate());
            stsv.setDtenddate(sd.getDtenddate());
            stsv.setMhandcharge(new Double(0));
            stsv.setByvalidity("00");
            stsv.setDtmakedate(s.getDtmakedate());
            stsv.setBymaketicketway("00");
            List<Map> iserialnumlist = new ArrayList();
            try {
                iserialnumlist = ticketTypeService
                        .findBySqlToMap("select ticketid_sequence.nextval  from dual");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Long iserialnum = new Long(
                    (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());
            stsv.setIserialnum(iserialnum);
            String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
            StringBuffer printno = new StringBuffer();
            printno.append(szstationcode);
            printno.append(szsceniccode);
            printno.append(sztickettypecode);
            if (newmaxorno.length() < 6) {
                for (int b = 0; b < 6 - newmaxorno.length(); b++) {
                    printno.append("0");
                }
            }
            printno.append(newmaxorno);
            String szprintno = Tools.ticketMakeMd5(printno.toString());
            stsv.setSzticketprintno(szprintno);
            stsv.setMremainmoney(new Double(0));
            stsv.setMpresentmoney(new Double(0));
            stsv.setMactualsaleprice(sd.getMactualsaleprice());
            stsv.setIpresentnum(new Long(0));
            stsv.setIremainnum(new Long(1));// 剩余数量
            stsv.setMnominalfee(new Double(0));
            stsv.setMdeposit(new Double(0));
            stsv.setByticketpurpose("00");
            stsv.setBisrefundbalance(new Long(1));
            stsv.setByactivation("02");

            if (eticketprice.getIpeoplenumrange() == 1) {
                // 实名制
                LOrderpeople lp = (LOrderpeople) zjlist.get(j - 1);
                stsv.setMyzj(lp.getPzjhm());
                stsv.setName1(lp.getPname());
            }
            List sczlist = new ArrayList();
            List clist = new ArrayList();
            // 将售出门票子表信息保存
            List comlist = sd.getComlist();
            int a = 1;
            for (int k = 1; k <= comlist.size(); k++) {

                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) comlist
                        .get(k - 1);
                List opwwlist = ticketTypeService
                        .find(" from Opwwicketsettab where itickettypeid="
                                + zstd.getItickettypeid()
                                + " and izticktypeid="
                                + zstd.getIztickettypeid() + " and byisuse=1");
                for (int m = 1; m <= opwwlist.size(); m++) {

                    Opwwicketsettab opww = (Opwwicketsettab) opwwlist
                            .get(m - 1);
                    Stssoldticketsubtab stss = new Stssoldticketsubtab();
                    StssoldticketsubtabId stssid = new StssoldticketsubtabId();
                    stssid.setIticketstationid(s.getId().getIticketstationid());
                    stssid.setSzsoldticketid(new Long(j));
                    stssid.setSzsoldticketsubid(new Long(a));
                    stssid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stssid.setIsalesvoucherdetailsid(zstd.getId()
                            .getIsalesvoucherdetailsid());
                    stss.setId(stssid);

                    stss.setIgardengateid(opww.getIgardengateid());
                    stss.setIscenicid(s.getIscenicid());
                    stss.setItickettypeid(zstd.getItickettypeid());
                    stss.setIztickettypeid(zstd.getIztickettypeid());
                    stss.setBychecktype(new Long(0));
                    stss.setByconsumemode(opww.getByconsumemode());
                    stss.setIpasstimes(opww.getIlimittotaltimes());
                    stss.setIpassedtimes(new Long(0));
                    stss.setMsingletimes(zstd.getIsplitamount());
                    stss.setMpasstimes(new Long(1));
                    stss.setMsingledtimes(new Long(0));
                    stss.setMlimitconsume(new Double(0));
                    stss.setMsingleconsume(new Double(0));
                    stss.setMconsumed(new Double(0));
                    stss.setByisout(new Long(1));
                    stss.setIsvalid(new Long(1));
                    stss.setDtmakedate(Tools.getDayTimes());
                    stss.setBylastcheckdir(new Long(0));
                    stss.setIcrowdkindid(eticketprice.getIcrowdkindid());
                    stss.setTripid(zstd.getTripid());
                    stss.setTimeStart(zstd.getTimeStart());
                    stss.setTimeEnd(zstd.getTimeEnd());
                    if (zstd.getTripid() > 0) {
                        // 存在竹筏
                        Trip t = (Trip) ticketTypeService.get(Trip.class,
                                zstd.getTripid());
                        stsv.setSztripid(t.getTripname());
                        stsv.setTriptime(zstd.getDtstartdate());
                    }
                    stss.setDtbegindate(zstd.getDtstartdate());
                    stss.setDtenddate(zstd.getDtenddate());

                    sczlist.add(stss);
                    Stsschecktab stsc = new Stsschecktab();
                    StsschecktabId stscid = new StsschecktabId();
                    stscid.setIticketstationid(s.getId().getIticketstationid());
                    stscid.setSzsoldticketid(new Long(j));
                    stscid.setSzsoldticketsubid(new Long(a));
                    a++;
                    stscid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stscid.setIsalesvoucherdetailsid(zstd.getId()
                            .getIsalesvoucherdetailsid());
                    stsc.setId(stscid);

                    stsc.setIgardengateid(opww.getIgardengateid());
                    stsc.setIscenicid(s.getIscenicid());
                    stsc.setItickettypeid(zstd.getItickettypeid());
                    stsc.setIztickettypeid(zstd.getIztickettypeid());
                    stsc.setBychecktype(new Long(0));
                    stsc.setByconsumemode(opww.getByconsumemode());
                    stsc.setIpasstimes(opww.getIlimittotaltimes());
                    stsc.setIpassedtimes(new Long(0));
                    stsc.setMsingletimes(zstd.getIsplitamount());
                    stsc.setMpasstimes(new Long(1));
                    stsc.setMsingledtimes(new Long(0));
                    stsc.setMlimitconsume(new Double(0));
                    stsc.setMsingleconsume(new Double(0));
                    stsc.setMconsumed(new Double(0));
                    stsc.setByisout(new Long(1));
                    stsc.setIsvalid(new Long(1));
                    stsc.setDtmakedate(Tools.getDayTimes());
                    stsc.setBylastcheckdir(new Long(0));
                    stsc.setIcrowdkindid(eticketprice.getIcrowdkindid());
                    stsc.setTripid(zstd.getTripid());
                    if (zstd.getTripid() > 0) {
                        // 存在竹筏
                        Trip t = (Trip) ticketTypeService.get(Trip.class,
                                zstd.getTripid());
                        stsv.setSztripid(t.getTripname());
                        stsv.setTriptime(zstd.getDtstartdate());
                    }
                    stsc.setDtbegindate(zstd.getDtstartdate());
                    stsc.setDtenddate(zstd.getDtenddate());
                    stsc.setSzscenicname(scenic.getSzscenicname());
                    stsc.setSzcrowdkindname(edkind.getSzcrowdkindname());
                    stsc.setSzmemo(edkind.getSzmemo());
                    stsc.setSztickettypename(edmticket.getSztickettypename());
                    stsc.setByusage(edmticket.getByusage());
                    stsc.setByuselimit(edmticket.getByuselimit());
                    stsc.setValidityday(edmticket.getValidityday());
                    // 产品属性表

                    if (ep != null) {
                        if (ep.getInoteger3() != null) {
                            stsc.setIsfristtimaeyz(ep.getInoteger3());
                        } else {
                            stsc.setIsfristtimaeyz(0L);
                        }
                        if (ep.getInoteger2() != null) {
                            stsc.setIsfristactive(ep.getInoteger2());
                        } else {
                            stsc.setIsfristactive(0L);
                        }
                    } else {
                        stsc.setIsfristtimaeyz(0L);
                        stsc.setIsfristactive(0L);
                    }
                    // 检票设置表属性
                    stsc.setBywicketconsumetype(opww.getBywicketconsumetype());
                    stsc.setByregfingerprinttype(opww.getByregfingerprinttype());
                    stsc.setItimeinterval(opww.getItimeinterval());
                    stsc.setSzticketprintno(stsv.getSzticketprintno());
                    stsc.setManyouno(stsv.getManyouno());
                    stsc.setMyzj(stsv.getMyzj());
                    stsc.setName1(stsv.getName1());
                    stsc.setZjno1(stsv.getZjno1());
                    stsc.setMactualsaleprice(stsv.getMactualsaleprice());
                    stsc.setIbusinessid(stsv.getIbusinessid());
                    clist.add(stsc);
                }
            }
            stsv.setClist(clist);
            stsv.setZlist(sczlist);

            cdetaillist.add(stsv);

        }
        sd.setMplist(cdetaillist);
        return sd;
    }

    public Stssalesvoucherdetailstab saveStssoldtickettabug1make00(
            Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
            String szstationcode, String szsceniccode, String sztickettypecode,
            Long icrowdkindid) {
        List cdetaillist = new ArrayList();
        Edpcrowdkindtab edkind = (Edpcrowdkindtab) ticketTypeService.get(
                Edpcrowdkindtab.class, icrowdkindid);
        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, s.getIscenicid());

        Edmtickettypetab edmticket = (Edmtickettypetab) this.ticketTypeService.get(
                Edmtickettypetab.class, sd.getItickettypeid());
        Edmticketproduct ep = (Edmticketproduct) this.ticketTypeService.get(
                Edmticketproduct.class, sd.getItickettypeid());
        Stssoldtickettab stsv = new Stssoldtickettab();
        StssoldtickettabId stsvid = new StssoldtickettabId();
        stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
        stsvid.setIticketstationid(sd.getId().getIticketstationid());
        stsvid.setIsalesvoucherdetailsid(sd.getId().getIsalesvoucherdetailsid());
        stsvid.setSzsoldticketid(new Long(1));
        stsv.setId(stsvid);
        stsv.setIscenicid(s.getIscenicid());
        stsv.setIcrowdkindid(icrowdkindid);
        stsv.setSzcrowdkindname(edkind.getSzcrowdkindname());
        stsv.setItickettypeid(sd.getItickettypeid());
        stsv.setUsid(s.getUsid());
        stsv.setIbusinessid(s.getIbusinessid());
        stsv.setDyusid(s.getDyusid());
        stsv.setIplayerperticket(sd.getIticketplayer());// 人次数
        stsv.setDtstartdate(sd.getDtstartdate());
        stsv.setDtenddate(sd.getDtenddate());
        stsv.setMhandcharge(new Double(0));
        stsv.setByvalidity("00");
        stsv.setDtmakedate(s.getDtmakedate());
        stsv.setBymaketicketway("00");
        List<Map> iserialnumlist = new ArrayList();

        try {
            iserialnumlist = ticketTypeService
                    .findBySqlToMap("select ticketid_sequence.nextval  from dual");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Long iserialnum = new Long(
                (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());
        stsv.setIserialnum(iserialnum);
        String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
        StringBuffer printno = new StringBuffer();
        printno.append(szstationcode);
        printno.append(szsceniccode);
        printno.append(sztickettypecode);
        Debug.print("newmaxorno" + newmaxorno);
        if (newmaxorno.length() < 6) {
            for (int b = 0; b < 6 - newmaxorno.length(); b++) {
                printno.append("0");
            }
        }

        printno.append(newmaxorno);
        String szprintno = Tools.ticketMakeMd5(printno.toString());
        stsv.setSzticketprintno(szprintno);
        stsv.setMremainmoney(new Double(0));
        stsv.setMpresentmoney(new Double(0));
        stsv.setMactualsaleprice(sd.getMactualsaleprice());
        stsv.setIpresentnum(new Long(0));
        stsv.setIremainnum(sd.getIplayerperticket());// 剩余数量
        stsv.setMnominalfee(new Double(0));
        stsv.setMdeposit(new Double(0));
        stsv.setByticketpurpose("00");
        stsv.setBisrefundbalance(new Long(1));
        stsv.setByactivation("02");

        List comlist = sd.getComlist();
        int a = 1;
        List sczlist = new ArrayList();
        List clist = new ArrayList();
        for (int k = 1; k <= comlist.size(); k++) {
            Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) comlist
                    .get(k - 1);
            if (zstd.getTripid() > 0 && zstd.getIvenueseatsid() > 0) {
                // 存在节目票
                Trip t = (Trip) ticketTypeService.get(Trip.class, zstd.getTripid());
                stsv.setSztripid(t.getTripname());
                stsv.setTriptime(zstd.getDtstartdate());
                Venueprogram vp = (Venueprogram) this.ticketTypeService.get(
                        Venueprogram.class, zstd.getIvenueseatsid());
                stsv.setSzprogramname(vp.getSzprogramname());
                Venue vn = (Venue) this.ticketTypeService.get(Venue.class,
                        zstd.getIvenueid());
                stsv.setVenueidname(vn.getVenueidname());
                Venuearea va = (Venuearea) this.ticketTypeService.get(Venuearea.class,
                        zstd.getIvenueareaid());
                stsv.setIvenueareaname(va.getIvenueareaname());
                String seatname = "";
                List seatlist = zstd.getSeatlist();
                for (int n = 0; n < seatlist.size(); n++) {
                    Seatsaletab so = (Seatsaletab) seatlist.get(n);
                    if (so.getId().getSzsoldticketid().longValue() == stsv
                            .getId().getSzsoldticketid().longValue()) {
                        List stlist = this.ticketTypeService
                                .find(" from Venueseats where ivenueseatsid="
                                        + so.getIseatid()
                                        + " and ivenueareaid="
                                        + so.getIvenueareaid()
                                        + " and ivenueid=" + so.getIvenueid());
                        Venueseats vst = (Venueseats) stlist.get(0);
                        seatname = seatname + vst.getSzvenueseatsname();
                    }
                }
                stsv.setSzvenueseatsname(seatname);

            }
            List opwwlist = ticketTypeService
                    .find(" from Opwwicketsettab where itickettypeid="
                            + zstd.getItickettypeid() + " and izticktypeid="
                            + zstd.getIztickettypeid() + " and byisuse=1");
            for (int m = 1; m <= opwwlist.size(); m++) {
                Opwwicketsettab opww = (Opwwicketsettab) opwwlist.get(m - 1);
                Stssoldticketsubtab stss = new Stssoldticketsubtab();
                StssoldticketsubtabId stssid = new StssoldticketsubtabId();
                stssid.setIticketstationid(s.getId().getIticketstationid());
                stssid.setSzsoldticketid(new Long(1));
                stssid.setSzsoldticketsubid(new Long(a));

                stssid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                stssid.setIsalesvoucherdetailsid(zstd.getId()
                        .getIsalesvoucherdetailsid());
                stss.setId(stssid);
                stss.setIgardengateid(opww.getIgardengateid());
                stss.setIscenicid(s.getIscenicid());
                stss.setItickettypeid(zstd.getItickettypeid());
                stss.setIztickettypeid(zstd.getIztickettypeid());
                stss.setBychecktype(new Long(0));
                stss.setByconsumemode(opww.getByconsumemode());
                
                if (opww.getIlimittotaltimes().longValue() == 0) {
                    // 多次进园门
                    stss.setIpasstimes(opww.getIlimittotaltimes());
                } else {
                    // 限制次数进园门
                    stss.setIpasstimes(zstd.getIsplitamount()
                            * opww.getIlimittotaltimes());
                }
                if (opww.getBywicketconsumetype().equals("01")) {
                    // 一检一人
                    stss.setMpasstimes(new Long(1));
                } else {
                    // 一检多人
                    stss.setMpasstimes(zstd.getIsplitamount());
                }
                stss.setIpassedtimes(new Long(0));
                stss.setMsingletimes(zstd.getIsplitamount());
                stss.setMsingledtimes(new Long(0));
                stss.setMlimitconsume(new Double(0));
                stss.setMsingleconsume(new Double(0));
                stss.setMconsumed(new Double(0));
                stss.setByisout(new Long(1));
                stss.setIsvalid(new Long(1));
                stss.setDtmakedate(Tools.getDayTimes());
                stss.setBylastcheckdir(new Long(0));
                stss.setIcrowdkindid(icrowdkindid);
                stss.setTripid(zstd.getTripid());

                stss.setDtbegindate(zstd.getDtstartdate());
                stss.setDtenddate(zstd.getDtenddate());
                stss.setTimeStart(zstd.getTimeStart());
                stss.setTimeEnd(zstd.getTimeEnd());
                sczlist.add(stss);
                Stsschecktab stsc = new Stsschecktab();
                StsschecktabId stscid = new StsschecktabId();
                stscid.setIticketstationid(s.getId().getIticketstationid());
                stscid.setSzsoldticketid(new Long(1));
                stscid.setSzsoldticketsubid(new Long(a));
                a++;
                stscid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                stscid.setIsalesvoucherdetailsid(zstd.getId()
                        .getIsalesvoucherdetailsid());
                stsc.setId(stscid);

                stsc.setIgardengateid(opww.getIgardengateid());
                stsc.setIscenicid(s.getIscenicid());
                stsc.setItickettypeid(zstd.getItickettypeid());
                stsc.setIztickettypeid(zstd.getIztickettypeid());
                stsc.setBychecktype(new Long(0));
                stsc.setByconsumemode(opww.getByconsumemode());
                if (opww.getIlimittotaltimes().longValue() == 0) {
                    // 多次进园门
                    stsc.setIpasstimes(opww.getIlimittotaltimes());
                } else {
                    // 限制次数进园门
                    stsc.setIpasstimes(zstd.getIsplitamount()
                            * opww.getIlimittotaltimes());
                }
                if (opww.getBywicketconsumetype().equals("01")) {
                    // 一检一人
                    stsc.setMpasstimes(new Long(1));
                } else {
                    // 一检多人
                    stsc.setMpasstimes(zstd.getIsplitamount());
                }
                stsc.setIpassedtimes(new Long(0));
                stsc.setMsingletimes(zstd.getIsplitamount());
                stsc.setMsingledtimes(new Long(0));
                stsc.setMlimitconsume(new Double(0));
                stsc.setMsingleconsume(new Double(0));
                stsc.setMconsumed(new Double(0));
                stsc.setByisout(new Long(1));
                stsc.setIsvalid(new Long(1));
                stsc.setDtmakedate(Tools.getDayTimes());
                stsc.setBylastcheckdir(new Long(0));
                stsc.setIcrowdkindid(icrowdkindid);
                stsc.setTripid(zstd.getTripid());

                stsc.setDtbegindate(zstd.getDtstartdate());
                stsc.setDtenddate(zstd.getDtenddate());
                stsc.setSzscenicname(scenic.getSzscenicname());
                stsc.setSzcrowdkindname(edkind.getSzcrowdkindname());
                stsc.setSzmemo(edkind.getSzmemo());
                stsc.setSztickettypename(edmticket.getSztickettypename());
                stsc.setByusage(edmticket.getByusage());
                stsc.setByuselimit(edmticket.getByuselimit());
                stsc.setValidityday(edmticket.getValidityday());
                // 产品属性表

                if (ep != null) {
                    if (ep.getInoteger3() != null) {
                        stsc.setIsfristtimaeyz(ep.getInoteger3());
                    } else {
                        stsc.setIsfristtimaeyz(0L);
                    }
                    if (ep.getInoteger2() != null) {
                        stsc.setIsfristactive(ep.getInoteger2());
                    } else {
                        stsc.setIsfristactive(0L);
                    }
                } else {
                    stsc.setIsfristtimaeyz(0L);
                    stsc.setIsfristactive(0L);
                }
                // 检票设置表属性
                stsc.setBywicketconsumetype(opww.getBywicketconsumetype());
                stsc.setByregfingerprinttype(opww.getByregfingerprinttype());
                stsc.setItimeinterval(opww.getItimeinterval());
                stsc.setSzticketprintno(stsv.getSzticketprintno());
                stsc.setManyouno(stsv.getManyouno());
                stsc.setMyzj(stsv.getMyzj());
                stsc.setName1(stsv.getName1());
                stsc.setZjno1(stsv.getZjno1());
                stsc.setMactualsaleprice(stsv.getMactualsaleprice());
                stsc.setIbusinessid(stsv.getIbusinessid());
                clist.add(stsc);

            }
        }
        stsv.setClist(clist);
        stsv.setZlist(sczlist);
        cdetaillist.add(stsv);
        sd.setMplist(cdetaillist);

        return sd;
    }

    public List getprintmanage(Long iscenicid, Long ibusinessid) {
        String sql = " from Printticketmanage p where p.id.iscenicid="
                + iscenicid + " and p.id.ibusinessid=" + ibusinessid
                + "  order by p.ordernum ";
        List<Map> list = new ArrayList();
        try {
            list = ticketTypeService.find(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getsodeprintmanage(Long iscenicid, Long ibusinessid) {
        String sql = " from Soderollprintmanage p where p.id.iscenicid="
                + iscenicid + " and p.id.ibusinessid=" + ibusinessid
                + "  order by p.ordernum ";
        List<Map> list = new ArrayList();
        try {
            list = sysService.find(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("size=" + list.size());
        return list;
    }

    public ResultBean getAutoT_orderlist2(String orid, Long iscenicid) {
        TOrder to = (TOrder) ticketTypeService.get(TOrder.class, new TOrderId(orid,
                iscenicid));
        to.setIschupiao(1L);
        ticketTypeService.save(to);
        String sql = " select orderlistid,CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.itickettypeid,t.icrowdkindid,t.dtstartdate,t.dtenddate,t.icrowdkindpriceid,t.pric,t.numb,t.yhnumb,t.amnt,t.yhamnt,et.sztickettypename,ew.szcrowdkindname,et.bymaketicketway,et.bymediatype,v5.pmva as strmaketype,v55.pmva as strmediatype,ep.inote1 from t_orderlist t,Edpcrowdkindtab ew,Edmtickettypetab et,sysparv5 v5 ,sysparv5 v55,Edmcrowdkindpricetab ep where  t.orid=? and t.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid = ? ) or iscenicid = "
                + iscenicid
                + ")) and ew.icrowdkindid=t.icrowdkindid and t.itickettypeid=et.itickettypeid and v5.pmky='CPFS' and v5.pmcd=et.bymaketicketway and v55.pmky='CKFS' and v55.pmcd =et.bymediatype and ep.icrowdkindpriceid=t.icrowdkindpriceid  order by orderlistid";
        List<Map> list = new ArrayList();
        try {
            list = ticketTypeService.findBySqlToMap(sql, orid, iscenicid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Map map : list) {
            Long itickettypeid = new Long(map.get("ITICKETTYPEID").toString());
            // 判断是否是打印票 非打印票不能自助出票
            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                    Edmtickettypetab.class, itickettypeid);
            if (!eticket.getBymaketicketway().equals("00")) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "false",
                        "该订单存在非打印票，不能在自助出票机出票" });
            }
            Long orderlistid = new Long(map.get("ORDERLISTID").toString());

            StringBuffer zdail = new StringBuffer();
            StringBuffer zdail1 = new StringBuffer();
            String sql1 = " select t.zorderlistid,t.orderlistid,CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.itickettypeid,t.iztickettypeid,t.dtstartdate,t.dtenddate,t.zpric,t.znumb,t.zyhnumb,t.zyhamnt,t.zamnt,et.sztickettypename,zet.sztickettypename as szztickettypename,t.tripid,t.ivenueid,t.ivenueareaid,t.ivenueseatsid,tr.tripname,v.ivenueareaname,ve.venueidname,vp.szprogramname,t.ise from t_zorderlist t left outer join  trip tr on tr.tripid=t.tripid left outer join Venuearea v on v.ivenueareaid=t.ivenueareaid left outer join Venue ve on ve.ivenueid=t.ivenueid left outer join venueprogram  vp on vp.iprogramid=t.ivenueseatsid,Edmtickettypetab et,Edmtickettypetab zet where  t.orid='"
                    + orid
                    + "' and t.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid = "
                    + iscenicid
                    + " ) or iscenicid = "
                    + iscenicid
                    + "))   "

                    + " and t.orderlistid="
                    + orderlistid
                    + " and  t.iztickettypeid=zet.itickettypeid  and t.itickettypeid=et.itickettypeid ";
            List<Map> list1 = new ArrayList();
            try {
                list1 = ticketTypeService.findBySqlToMap(sql1);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for (Map map1 : list1) {
                String seatids = "";
                if (map1.get("TRIPID") != null) {
                    if (new Long(map1.get("TRIPID").toString()) > 0) {

                        try {
                            List list2 = this.ticketTypeService
                                    .findBySqlToMap("select s.iseatid from seatordertab s where s.orid="
                                            + orid
                                            + " and s.iscenicid="
                                            + map1.get("ISCENICID")
                                            + " and s.orderlistid="
                                            + orderlistid
                                            + " and s.zorderlistid="
                                            + map1.get("ZORDERLISTID")
                                            + " and s.isvalid=1");
                            if (list2 != null && list2.size() > 0) {
                                for (int a = 0; a < list2.size(); a++) {
                                    Map map2 = (Map) list2.get(a);
                                    if (a == 0) {
                                        seatids = map2.get("ISEATID")
                                                .toString();
                                    } else {
                                        seatids = seatids
                                                + ">"
                                                + map2.get("ISEATID")
                                                .toString();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        zdail1 = zdail1.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + map1.get("TRIPID").toString() + "&"
                                + map1.get("TRIPNAME").toString() + "&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString());
                        zdail = zdail.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + map1.get("TRIPID").toString() + "&"
                                + map1.get("TRIPNAME").toString() + "&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString());

                        List tlist = ticketTypeService
                                .find(" from Productcontrol where controltype='03' and iscenicid="
                                        + iscenicid
                                        + " and itickettypeid="
                                        + map1.get("IZTICKETTYPEID").toString()
                                        + " and tripid="
                                        + map1.get("TRIPID").toString()
                                        + " and stdata='"
                                        + map1.get("DTSTARTDATE").toString()
                                        .substring(0, 10) + "'");
                        if (tlist != null && tlist.size() > 0) {
                            Productcontrol p = (Productcontrol) tlist.get(0);
                            if (p.getBystate() == 1) {
                                zdail1 = zdail1.append("&" + p.getBystate()
                                        + "&正常" + "!");
                                zdail = zdail.append("&" + p.getBystate()
                                        + "&正常" + "!");
                            } else if (p.getBystate() == 0) {
                                zdail1 = zdail1.append("&" + p.getBystate()
                                        + "&开始停排" + "!");
                                zdail = zdail.append("&" + p.getBystate()
                                        + "&开始停排" + "!");
                            } else {
                                zdail1 = zdail1.append("&" + p.getBystate()
                                        + "&确认停排" + "!");
                                zdail = zdail.append("&" + p.getBystate()
                                        + "&确认停排" + "!");
                            }
                        } else {
                            zdail1 = zdail1.append("&1&正常");
                            zdail = zdail.append("&1&正常");
                        }
                        zdail = zdail.append("&"
                                + map1.get("IVENUEID").toString() + "&"
                                + map1.get("IVENUEAREAID").toString() + "&"
                                + map1.get("VENUEIDNAME").toString() + "&"
                                + map1.get("IVENUESEATSID").toString() + "&"
                                + map1.get("SZPROGRAMNAME").toString() + "&"
                                + map1.get("ISE").toString() + "&" + seatids
                                + "!");
                        zdail1 = zdail1.append("&"
                                + map1.get("IVENUEID").toString() + "&"
                                + map1.get("IVENUEAREAID").toString() + "&"
                                + map1.get("VENUEIDNAME").toString() + "&"
                                + map1.get("IVENUESEATSID").toString() + "&"
                                + map1.get("SZPROGRAMNAME").toString() + "&"
                                + map1.get("ISE").toString() + "&" + seatids
                                + "!");
                    } else {

                        zdail = zdail.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + "0&&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString()
                                + "&1&正常&0&0&&0&&&!");
                        zdail1 = zdail1.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + "0&&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString()
                                + "&1&正常&0&0&&0&&&!");
                    }
                } else {
                    zdail = zdail.append(map1.get("IZTICKETTYPEID") + "&"
                            + map1.get("SZZTICKETTYPENAME").toString() + "&"
                            + "0&&" + map1.get("DTSTARTDATE").toString() + "&"
                            + map1.get("DTENDDATE").toString()
                            + "&1&正常&0&0&&0&&&!");
                    zdail1 = zdail1.append(map1.get("IZTICKETTYPEID") + "&"
                            + map1.get("SZZTICKETTYPENAME").toString() + "&"
                            + "0&&" + map1.get("DTSTARTDATE").toString() + "&"
                            + map1.get("DTENDDATE").toString()
                            + "&1&正常&0&0&&0&&&!");
                }
            }
            String zzdail1 = zdail1.toString();
            if (!zzdail1.equals("")) {
                zzdail1 = zzdail1.substring(0, zzdail1.length() - 1);
                map.put("ZZDAIL", zzdail1);
            } else {
                map.put("ZZDAIL", "");
            }
            String zzdail = zdail.toString();
            if (!zzdail.equals("")) {
                zzdail = zzdail.substring(0, zdail.length() - 1);
                map.put("ZDAIL", zzdail);
            } else {
                map.put("ZDAIL", "");
            }

        }
        return MapToResultBean.toResultBean(list);

    }

    public ResultBean getAutoT_orderlist(String orid, Long iscenicid) {

        String sql = " select orderlistid,CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.itickettypeid,t.icrowdkindid,t.dtstartdate,t.dtenddate,t.icrowdkindpriceid,t.pric,t.numb,t.yhnumb,t.amnt,t.yhamnt,et.sztickettypename,ew.szcrowdkindname,et.bymaketicketway,et.bymediatype,v5.pmva as strmaketype,v55.pmva as strmediatype from t_orderlist t,Edpcrowdkindtab ew,Edmtickettypetab et,sysparv5 v5 ,sysparv5 v55 where  t.orid=? and t.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid = ? ) or iscenicid = "
                + iscenicid
                + ")) and ew.icrowdkindid=t.icrowdkindid and t.itickettypeid=et.itickettypeid and v5.pmky='CPFS' and v5.pmcd=et.bymaketicketway and v55.pmky='CKFS' and v55.pmcd =et.bymediatype  order by orderlistid";
        List<Map> list = new ArrayList();
        try {
            list = ticketTypeService.findBySqlToMap(sql, orid, iscenicid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (Map map : list) {
            Long itickettypeid = new Long(map.get("ITICKETTYPEID").toString());
            // 判断是否是打印票 非打印票不能自助出票
            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                    Edmtickettypetab.class, itickettypeid);
            if (!eticket.getBymaketicketway().equals("00")) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "false",
                        "该订单存在非打印票，不能在自助出票机出票" });
            }
            Long orderlistid = new Long(map.get("ORDERLISTID").toString());
            StringBuffer zdail = new StringBuffer();
            StringBuffer zdail1 = new StringBuffer();
            String sql1 = " select t.zorderlistid,t.orderlistid,CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.itickettypeid,t.iztickettypeid,t.dtstartdate,t.dtenddate,t.zpric,t.znumb,t.zyhnumb,t.zyhamnt,t.zamnt,et.sztickettypename,zet.sztickettypename as szztickettypename,t.tripid,t.ivenueareaid,tr.tripname,v.ivenueareaname from t_zorderlist t left outer join  trip tr on tr.tripid=t.tripid left outer join Venuearea v on v.ivenueareaid=t.ivenueareaid,Edmtickettypetab et,Edmtickettypetab zet where  t.orid='"
                    + orid
                    + "' and t.iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid = "
                    + iscenicid
                    + " ) or iscenicid = "
                    + iscenicid
                    + "))   "

                    + " and t.orderlistid="
                    + orderlistid
                    + " and  t.iztickettypeid=zet.itickettypeid  and t.itickettypeid=et.itickettypeid ";
            List<Map> list1 = new ArrayList();
            try {
                list1 = ticketTypeService.findBySqlToMap(sql1);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for (Map map1 : list1) {
                if (map1.get("TRIPID") != null) {
                    if (new Long(map1.get("TRIPID").toString()) > 0) {
                        zdail1 = zdail1.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + map1.get("TRIPID").toString() + "&"
                                + map1.get("TRIPNAME").toString() + "&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString());
                        zdail = zdail.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + map1.get("TRIPID").toString() + "&"
                                + map1.get("TRIPNAME").toString() + "&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString());

                        List tlist = ticketTypeService
                                .find(" from Productcontrol where controltype='03' and iscenicid="
                                        + iscenicid
                                        + " and itickettypeid="
                                        + map1.get("IZTICKETTYPEID").toString()
                                        + " and tripid="
                                        + map1.get("TRIPID").toString()
                                        + " and stdata='"
                                        + map1.get("DTSTARTDATE").toString()
                                        .substring(0, 10) + "'");
                        if (tlist != null && tlist.size() > 0) {
                            Productcontrol p = (Productcontrol) tlist.get(0);
                            if (p.getBystate() == 1) {
                                zdail1 = zdail1.append("&" + p.getBystate()
                                        + "&正常" + "!");
                                zdail = zdail.append("&" + p.getBystate()
                                        + "&正常" + "!");
                            } else if (p.getBystate() == 0) {
                                zdail1 = zdail1.append("&" + p.getBystate()
                                        + "&开始停排" + "!");
                                zdail = zdail.append("&" + p.getBystate()
                                        + "&开始停排" + "!");
                            } else {
                                zdail1 = zdail1.append("&" + p.getBystate()
                                        + "&确认停排" + "!");
                                zdail = zdail.append("&" + p.getBystate()
                                        + "&确认停排" + "!");
                            }
                        } else {
                            zdail1 = zdail1.append("&1&正常" + "!");
                            zdail = zdail.append("&1&正常" + "!");
                        }
                    } else {

                        zdail = zdail.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + "0&&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString() + "&1&正常!");
                        zdail1 = zdail1.append(map1.get("IZTICKETTYPEID") + "&"
                                + map1.get("SZZTICKETTYPENAME").toString()
                                + "&" + "0&&"
                                + map1.get("DTSTARTDATE").toString() + "&"
                                + map1.get("DTENDDATE").toString() + "&1&正常!");
                    }
                } else {
                    zdail = zdail.append(map1.get("IZTICKETTYPEID") + "&"
                            + map1.get("SZZTICKETTYPENAME").toString() + "&"
                            + "0&&" + map1.get("DTSTARTDATE").toString() + "&"
                            + map1.get("DTENDDATE").toString() + "&1&正常!");
                    zdail1 = zdail1.append(map1.get("IZTICKETTYPEID") + "&"
                            + map1.get("SZZTICKETTYPENAME").toString() + "&"
                            + "0&&" + map1.get("DTSTARTDATE").toString() + "&"
                            + map1.get("DTENDDATE").toString() + "&1&正常!");
                }
            }
            String zzdail1 = zdail1.toString();
            if (!zzdail1.equals("")) {
                zzdail1 = zzdail1.substring(0, zzdail1.length() - 1);
                map.put("ZZDAIL", zzdail1);
            } else {
                map.put("ZZDAIL", "");
            }
            String zzdail = zdail.toString();
            if (!zzdail.equals("")) {
                zzdail = zzdail.substring(0, zdail.length() - 1);
                map.put("ZDAIL", zzdail);
            } else {
                map.put("ZDAIL", "");
            }

        }
        return MapToResultBean.toResultBean(list);

    }

    public ResultBean savetorder(T_order t_order, List listorder,
                                 List listzorder, Long iemployeeid, Long iticketwinid, Long maxid,
                                 List Trealnamelist) throws Exception {
        System.out.println("savetorder0");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        // 读取订单信息
        ResultBean rb = new ResultBean();
        rb.setColumnCount(9);
        rb.setColumnNames(new String[] { "bymediatype", "bymaketicketway",
                "Yuanmenshuj", "mactualsaleprice", "dtstartdate", "dtenddate",
                "szticketprintno", "dayin", "fujuan" });
        System.out.println("savetorder1");
        Stssalesvouchertab s = this.savetStssalesvouchertab(t_order,
                iemployeeid, maxid, iticketwinid);
        s.setOrdersource("nzzj");
        String zffs = "";
        if (t_order.getDdzt().equals("02")) {
            if (t_order.getOrid().matches("^\\d{8}999\\d{6}")
                    || t_order.getOrid().matches("^\\d{8}888\\d{6}")
                    /*|| t_order.getOrid().matches("^\\d{8}777\\d{6}")*/) {
                zffs = "08";
            } else {
                zffs = "01";
            }
        } else {
            zffs = "00";
        }
        System.out.println("savetorder2");
        Stssalessettlementtab st = this.saveStssalessettlementtab(s, zffs);
        Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(
                Esbticketwintab.class, iticketwinid);
        Esbticketstationtab esbticketstation = (Esbticketstationtab) ticketTypeService
                .get(Esbticketstationtab.class, e.getIticketstationid());
        System.out.println("Iticketstationid=" + e.getIticketstationid());
        System.out.println("Szstationcode="
                + esbticketstation.getSzstationcode());
        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, new Long(t_order.getIscenicid()));
        List detaillist = new ArrayList();
        int snumb = 0;// 总张数
        int dnumb = 1;// 第几张
        System.out.println("savetorder3");
        for (int i = 0; i < listorder.size(); i++) {
            T_orderlist tlist = (T_orderlist) listorder.get(i);
            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                    Edmtickettypetab.class, new Long(tlist.getItickettypeid()));
            // 保存售票凭证明细
            System.out.println("savetorder4");
            Stssalesvoucherdetailstab sd = this.saveStssalesvoucherdetailstab(
                    s, tlist, eticket);
            System.out.println("savetorder5");
            // 保存售出门票子表明细
            sd = this.SaveautoStscomticketsalesdetailstab(sd, listzorder);
            System.out.println("savetorder6");
            Edmcrowdkindpricetab eticketprice = (Edmcrowdkindpricetab) ticketTypeService
                    .get(Edmcrowdkindpricetab.class,
                            new Long(tlist.getIcrowdkindpriceid()));

            if (eticket.getByusage() == 0) {
                // 一票一人

                // 现场打印
                System.out.println("savetorder7");
                sd = this.saveautoStssoldtickettabug0make00(sd, s,
                        esbticketstation.getSzstationcode(),
                        scenic.getSzsceniccode(),
                        eticket.getSztickettypecode(), eticketprice,
                        Trealnamelist);

            } else {

                System.out.println("savetorder8");
                sd = this.saveStssoldtickettabug1make00(sd, s,
                        esbticketstation.getSzstationcode(),
                        scenic.getSzsceniccode(),
                        eticket.getSztickettypecode(),
                        eticketprice.getIcrowdkindid());

            }
            System.out.println("savetorder9");
            detaillist.add(sd);
            snumb = snumb + sd.getMplist().size();
        }
        System.out.println("savetorder10");

        // 读取副卷打印设置
        List sprintlist = this.getsodeprintmanage(
                new Long(t_order.getIscenicid()), s.getIbusinessid());
        List syslist = ticketTypeService.find(" from Sysparv5 where id.pmky='PRSZ'");

        if (sprintlist != null && sprintlist.size() > 0) {
            for (int m = 0; m < sprintlist.size(); m++) {
                Soderollprintmanage p = (Soderollprintmanage) sprintlist.get(m);
                for (int n = 0; n < syslist.size(); n++) {
                    Sysparv5 sv5 = (Sysparv5) syslist.get(n);
                    if (sv5.getId().getPmcd().equals(p.getId().getPrintno())) {
                        p.setSzprintno(sv5.getPmva());
                        p.setPrintype(sv5.getIsa().toString());
                        if (sv5.getIsa() == 0) {
                            p.setNote(sv5.getPmvb());
                        }
                    }
                }
            }
        }
        try {
            ticketTypeService.save(s);
            ticketTypeService.save(st);
            System.out.println("开始保存数据2");
            Sysparv5 sv5 = (Sysparv5) ticketTypeService.get(Sysparv5.class,
                    new Sysparv5Id("PRCS", "01"));
            String printcs = "0";
            if (sv5 != null) {
                printcs = sv5.getPmva();
            }
            for (int i = 0; i < detaillist.size(); i++) {
                Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
                        .get(i);
                System.out.println("Isalesvoucherdetailsid="
                        + sd.getId().getIsalesvoucherdetailsid());
                System.out.println("开始保存数据2.1");
                ticketTypeService.save(sd);
                System.out.println("开始保存数据2.2");
                List zdetaillist = sd.getComlist();
                System.out.println("开始保存数据2.3");
                for (int j = 0; j < zdetaillist.size(); j++) {
                    System.out.println("开始保存数据2.4");
                    Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                            .get(j);
                    System.out.println("Isalesvoucherdetailsid="
                            + zstd.getId().getIsalesvoucherdetailsid());
                    System.out.println("开始保存数据2.5");
                    List seatlist = zstd.getSeatlist();
                    if (seatlist != null && seatlist.size() > 0) {
                        for (int k = 0; k < seatlist.size(); k++) {
                            Seatsaletab so = (Seatsaletab) seatlist.get(k);
                            ticketTypeService.save(so);
                        }
                    }
                    ticketTypeService.save(zstd);
                }
                System.out.println("开始保存数据3");
                List cdetaillist = sd.getMplist();
                System.out.println("cdetaillist.size()=" + cdetaillist.size());
                for (int j = 0; j < cdetaillist.size(); j++) {
                    Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist
                            .get(j);
                    // 添加返回数据
                    System.out.println("开始保存数据4");
                    Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService
                            .get(Edmtickettypetab.class,
                                    stsv.getItickettypeid());
                    String bymediatype = eticket.getBymediatype();// 介质类型
                    String bymaketicketway = eticket.getBymaketicketway();// 出票方式
                    String Yuanmenshuj = "";// 园门数据
                    String mactualsaleprice = sd.getMactualsaleprice()
                            .toString();// 单价
                    String orid = s.getSzsalesvoucherno();
                    String dtstartdate = stsv.getDtstartdate();// 开始日期
                    String dtenddate = stsv.getDtenddate();// 结束日期
                    String szticketprintno = stsv.getSzticketprintno();
                    szticketprintno = changeSzprintNo(szticketprintno,orid,s.getUsid(),eticket);

                    System.out.println("开始保存数据7");
                    ticketTypeService.save(stsv);
                    System.out.println("开始保存数据8");
                    List cdzetaillist = stsv.getZlist();
                    for (int k = 0; k < cdzetaillist.size(); k++) {
                        Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
                                .get(k);
                        ticketTypeService.save(stss);
                    }
                    List clist = stsv.getClist();
                    for (int k = 0; k < clist.size(); k++) {
                        Stsschecktab stss = (Stsschecktab) clist.get(k);
                        ticketTypeService.save(stss);
                    }
                    System.out.println("开始保存数据9");
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
                                    Long printid = ticketTypeService
                                            .getSequenceId("PRINT_ID");
                                    t.setPrintid(printid);
                                    ticketTypeService.save(t);
                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                    System.out.println(e1.getMessage());
                                }
                            }
                        }
                    }
                    System.out.println("开始保存数据10");
                    if (eticket.getBymaketicketway().equals("00")) {

                        String dayin = CommonUtil.getprintmessage(s, stsv, eticket,
                                esbticketstation,false);

                        String fujian = CommonUtil.getfprintmessage(sprintlist, s,
                                stsv, eticket, esbticketstation);

                        rb.addRow(new String[] { bymediatype, bymaketicketway,
                                Yuanmenshuj, mactualsaleprice, dtstartdate,
                                dtenddate, szticketprintno, dayin, fujian });
                    } else {
                        rb.addRow(new String[] { bymediatype, bymaketicketway,
                                Yuanmenshuj, mactualsaleprice, dtstartdate,
                                dtenddate, szticketprintno, "", "" });
                    }
                    dnumb++;
                }
            }

        } catch (Exception ex) {
            System.out.println("dddd,Exception" + e.toString());
            throw new RuntimeException(ex.getMessage());
        }
        return rb;

    }

    public Stssalesvouchertab savetStssalesvouchertab(T_order t_order,
                                                      Long iemployeeid, Long maxid, Long iticketwinid) {
        Stssalesvouchertab s = new Stssalesvouchertab();
        s.setIscenicid(new Long(t_order.getIscenicid()));
        s.setIticketwinid(iticketwinid);
        s.setIbusinessid(new Long(t_order.getIbusinessid()));
        s.setIhandler(iemployeeid);
        s.setIpayeer(iemployeeid);
        s.setImaker(iemployeeid);
        s.setIauditor(iemployeeid);
        s.setIaccountreceivable(new Double(t_order.getZfmont()));// 实收
        s.setIacceptmoney(new Double(t_order.getZfmont()));// 应收
        s.setIgivechange(new Double(0));// 找零
        s.setSzsalesvoucherno(t_order.getOrid());
        s.setIyear(new Long(Tools.getDays().substring(0, 4)));
        s.setImonth(new Long(Tools.getDays().substring(5, 7)));
        s.setIday(new Long(Tools.getDays().substring(8, 10)));
        s.setDtmakedate(Tools.getDayTimes());
        s.setDtauditdate(t_order.getDtstartdate());
        s.setUsid(t_order.getUsid());
        s.setBisintegral(new Long(0));
        s.setByprintinvoice(new Long(0));
        s.setBysplitway(new Long(2));
        s.setBisreturn(new Long(1));
        s.setBysalesvouchertype("01");
        s.setBypostrecord(new Long(0));
        s.setBysalesvoucherstate(new Long(1));
        s.setBispay(new Long(0));
        s.setBispayee(new Long(0));
        s.setSztravelbillno(t_order.getSztravelbillno());
        s.setIregionalid(new Long(t_order.getIregionalid()));
        if (t_order.getDyusid() == null || t_order.getDyusid().equals("")
                || t_order.getDyusid().equals("null")
                || t_order.getDyusid().equals("NULL")) {
            s.setDyusid("daoyou");
        } else {
            s.setDyusid(t_order.getDyusid());
        }
        s.setOrnote1(t_order.getOrnote1());
        s.setOrnote2(t_order.getOrnote2());
        s.setOrnote3(t_order.getOrnote3());
        s.setOrnote4(t_order.getOrnote4());
        s.setOrnote5(t_order.getOrnote5());
        s.setOrnote6(t_order.getOrnote6());
        s.setOrnote7(t_order.getOrnote7());
        s.setOrnote8(t_order.getOrnote8());
        s.setOrnote9(t_order.getOrnote9());
        s.setOrnote10(t_order.getOrnote10());
        s.setTdlx(t_order.getTdlx());
        Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(
                Esbticketwintab.class, s.getIticketwinid());
        StssalesvouchertabId id = new StssalesvouchertabId();
        id.setIticketstationid(e.getIticketstationid());
        id.setIsalesvoucherid(maxid);
        s.setId(id);
        // s.getId().setIsalesvoucherid(maxid);
        return s;
    }

    public Stssalesvoucherdetailstab saveStssalesvoucherdetailstab(
            Stssalesvouchertab s, T_orderlist tlist, Edmtickettypetab eticket) {
        Stssalesvoucherdetailstab sd = new Stssalesvoucherdetailstab();
        StssalesvoucherdetailstabId sdid = new StssalesvoucherdetailstabId();
        sdid.setIsalesvoucherdetailsid(new Long(tlist.getOrderlistid()));
        sdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
        sdid.setIticketstationid(s.getId().getIticketstationid());
        sd.setId(sdid);
        sd.setIticketwinid(s.getIticketwinid());
        sd.setIcrowdkindpriceid(new Long(tlist.getIcrowdkindpriceid()));
        sd.setItickettypeid(new Long(tlist.getItickettypeid()));
        sd.setDtstartdate(tlist.getDtstartdate());
        sd.setDtenddate(tlist.getDtenddate());
        sd.setIstartid(new Long(0));
        sd.setIendid(new Long(0));
        sd.setSzstartserial("0");
        sd.setSzendserial("0");
        System.out.println("tlist.getIoffersschemeid()="
                + tlist.getIoffersschemeid());
        if (tlist.getIoffersschemeid() == null || "".equals(tlist.getIoffersschemeid()) || tlist.getIoffersschemeid().equals("null")) {
            sd.setIoffersschemeid(0L);
        } else {
            sd.setIoffersschemeid(new Long(tlist.getIoffersschemeid()));
        }
        sd.setIamount(new Long(tlist.getNumb()));
        sd.setIpresentnums(new Long(0));
        sd.setIderatenums(new Long(tlist.getYhnumb()));
        sd.setIfactnum(new Long(0));
        sd.setIuseablenessnum(new Long(tlist.getNumb()));// 使用数量
        sd.setMactualsaleprice(new Double(tlist.getPric()));// 实际售价
        sd.setMeventmoney(new Double(tlist.getAmnt()));// 实际发生金额
        sd.setMderatemoney(new Double(tlist.getYhamnt()));// 减免金额
        sd.setMpresentmoney(new Double(0));// 赠送金额
        sd.setMnominalfee(new Double(0));// 工本费
        sd.setMdeposit(new Double(0));
        sd.setMhandcharge(new Double(0));
        sd.setByconsumetype("00");
        sd.setIconsumenum(new Double(0));
        sd.setMtotalamount(new Double(tlist.getAmnt()));
        sd.setItotalnumber(new Long(tlist.getNumb()));
        sd.setItotalminutes(new Long(0));
        sd.setByisout(new Long(0));
        sd.setDtmakedate(Tools.getDayTimes());
        sd.setIversion(new Long(0));

        if (eticket.getByusage() == 0) {
            sd.setIplayerperticket(new Long(1));// 人/张
            sd.setIticketplayer(new Long(tlist.getNumb()));// 人次
            sd.setIticketnum(new Long(tlist.getNumb()));
        } else {
            sd.setIticketnum(new Long(1));// 张数
            sd.setIticketplayer(new Long(tlist.getNumb()));
            sd.setIplayerperticket(new Long(tlist.getNumb()));// 人/张
        }

        return sd;
    }

    public Stssalesvoucherdetailstab SaveautoStscomticketsalesdetailstab(
            Stssalesvoucherdetailstab sd, List listzorder) {
        // System.out.println("SaveautoStscomticketsalesdetailstab");
        List zdetaillist = new ArrayList();
        int yxq = 1;
        Edmticketproduct et = (Edmticketproduct) ticketTypeService.get(
                Edmticketproduct.class, new Long(sd.getItickettypeid()));
        if (et == null) {
            yxq = 0;
        } else {
            if (et.getInoteger1() == null || et.getInoteger1() == 0) {
                yxq = 0;
            }
        }
        Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                Edmtickettypetab.class, sd.getItickettypeid());
        // System.out.print("SaveautoStscomticketsalesdetailstab2");
        for (int i = 0; i < listzorder.size(); i++) {
            T_zorderlist zlo = (T_zorderlist) listzorder.get(i);
            // System.out.print("SaveautoStscomticketsalesdetailstab3");
            if (sd.getId().getIsalesvoucherdetailsid().longValue() == Long
                    .parseLong(zlo.getOrderlistid())) {
                Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
                StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
                Long isalesvoucherdetailsid = new Long(zlo.getOrderlistid());
                Long icrowdkindpriceid = new Long(sd.getIcrowdkindpriceid());
                Long itickettypeid = new Long(sd.getItickettypeid());
                Long iztickettypeid = new Long(zlo.getIztickettypeid());// 陈新浩2015-02-03修改，保存子票ID，原来保存主产品ID
                Long isplitamount = new Long(zlo.getZnumb());
                Long tripid = new Long(0);
                Long ivenueid = new Long(0);
                Long ivenueareaid = new Long(0);
                Long ivenueseatsid = new Long(0);
                Long itripprdcontrolid = new Long(0);
                String dtstartdate = zlo.getDtstartdate();

                String dtenddate = zlo.getDtenddate();
                // System.out.print("SaveautoStscomticketsalesdetailstab4");
                // System.out.print("zlo.getTripid()="+zlo.getTripid());
                if (zlo.getTripid() != null) {
                    tripid = new Long(zlo.getTripid());
                }
                // System.out.print("zlo.getIvenueid()="+zlo.getIvenueid());
                if (zlo.getIvenueid() != null) {
                    ivenueid = new Long(zlo.getIvenueid());
                }
                // System.out.print("zlo.getIvenueareaid()="+zlo.getIvenueareaid());
                if (zlo.getIvenueareaid() != null) {
                    ivenueareaid = new Long(zlo.getIvenueareaid());
                }
                // System.out.print("zlo.getIvenueareaid()="+zlo.getIvenueareaid());
                if (zlo.getIvenueareaid() != null) {
                    ivenueseatsid = new Long(zlo.getIvenueseatsid());
                }
                if (zlo.getIse() != null && !zlo.getIse().equals("null")
                        && !zlo.getIse().equals("")) {
                    System.out.print("zlo.getIse()=" + zlo.getIse());
                    itripprdcontrolid = new Long(zlo.getIse());
                }
                // System.out.print("SaveautoStscomticketsalesdetailstab5");
                zstd.setItripprdcontrolid(itripprdcontrolid);
                zstdid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
                zstdid.setIticketstationid(sd.getId().getIticketstationid());
                zstdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
                zstdid.setIcomticketsalesdetailsid(new Long(zlo
                        .getZorderlistid()));
                zstd.setId(zstdid);
                zstd.setIcrowdkindpriceid(icrowdkindpriceid);
                zstd.setItickettypeid(itickettypeid);
                zstd.setIztickettypeid(iztickettypeid);
                zstd.setMhandcharge(new Double(0));
                zstd.setDtmakedate(Tools.getDayTimes());
                zstd.setIvenueareaid(ivenueareaid);
                zstd.setIvenueid(ivenueid);
                zstd.setIvenueseatsid(ivenueseatsid);
                zstd.setItripprdcontrolid(itripprdcontrolid);
                zstd.setTripid(tripid);
                // System.out.print("SaveautoStscomticketsalesdetailstab6");
                if (zstd.getTripid().longValue() > 0) {
                    zstd.setDtstartdate(zlo.getDtstartdate());
                    zstd.setDtenddate(zlo.getDtenddate());
                } else {
                    if (yxq == 0) {
                        if (zlo.getDtstartdate().length() > 10) {
                            zstd.setDtstartdate(zlo.getDtstartdate());
                            zstd.setDtenddate(zlo.getDtenddate());
                        } else {
                            zstd.setDtstartdate(zlo.getDtstartdate()
                                    + " 00:00:00");
                            zstd.setDtenddate(zlo.getDtenddate() + " 23:59:59");
                        }
                    } else {

                        if (zlo.getDtstartdate().length() > 10) {

                            zstd.setDtstartdate(zlo.getDtstartdate());
                            zstd.setDtenddate(zlo.getDtenddate());
                        } else {
                            zstd.setDtstartdate(zlo.getDtstartdate()
                                    + " 00:00:00");
                            zstd.setDtenddate(zlo.getDtenddate() + " 23:59:59");
                        }
                    }
                }
                if (eticket!=null&&eticket.getInt1()!=null&&eticket.getInt1() == 1) {
                    // 有效开始时间已当前时间开始
                    if (Tools.getTodayString().equals(zstd.getDtstartdate().substring(0,10))) {
                        String nowtime=Tools.getNowTime();
                        zstd.setDtstartdate(zstd.getDtstartdate().substring(0,10)+" "+nowtime);
                        zstd.setDtenddate(Tools.getDate(zstd.getDtenddate().substring(0,10), 1) + " "
                                + nowtime);
                    }else{
                        zstd.setDtstartdate(zstd.getDtstartdate().substring(0,10)+" 00:00:00");
                        zstd.setDtenddate(zstd.getDtenddate().substring(0,10) + " 23:59:59");
                    }
                }

                // System.out.print("SaveautoStscomticketsalesdetailstab7");
                zstd.setIversion(new Long(0));
                zstd.setIsplitamount(new Long(zlo.getZnumb()));
                zstd.setMsplitprice(new Double(zlo.getZpric()));
                zstd.setMsplitmoney(new Double(zlo.getZamnt()));
                zstd.setIderatenums(new Long(zlo.getZyhnumb()));
                zstd.setMderatemoney(new Double(zlo.getZyhamnt()));
                if (zlo.getSeats() != null) {
                    zstd.setSeatsid(zlo.getSeats());
                }
                // System.out.print("SaveautoStscomticketsalesdetailstab8");
                if (itripprdcontrolid > 0) {
                    // 保存座位的List
                    List seatlist = new ArrayList();
                    String seats = zlo.getSeats();
                    if (!seats.equals("null") && !seats.equals("")
                            && !seats.equals("NULL")) {
                        String[] seatids = seats.split(">");
                        for (int j = 0; j < seatids.length; j++) {
                            Seatsaletab so = new Seatsaletab();
                            SeatsaletabId soid = new SeatsaletabId();
                            soid.setIsalesvoucherdetailsid(new Long(zlo
                                    .getOrderlistid()));
                            soid.setIcomticketsalesdetailsid(new Long(zlo
                                    .getZorderlistid()));
                            soid.setIsalesvoucherid(sd.getId()
                                    .getIsalesvoucherid());
                            soid.setIticketstationid(sd.getId()
                                    .getIticketstationid());
                            soid.setSeq(new Long(j + 1));
                            if (eticket.getByusage() == 0) {
                                soid.setSzsoldticketid(new Long(j + 1));
                            } else {
                                soid.setSzsoldticketid(new Long(1));
                            }
                            so.setId(soid);
                            so.setIprogramid(new Long(zlo.getIvenueseatsid()));
                            so.setIseatid(new Long(seatids[j]));
                            so.setIsvalid(1L);
                            so.setItripid(zstd.getTripid());
                            so.setItripprdcontrolid(zstd.getItripprdcontrolid());
                            so.setIvenueid(zstd.getIvenueid());
                            so.setIvenueareaid(zstd.getIvenueareaid());
                            so.setDtmakedate(Tools.getDayTimes());
                            so.setStartdate(zlo.getDtstartdate().substring(0,
                                    10));
                            seatlist.add(so);
                        }
                    }
                    zstd.setSeatlist(seatlist);
                }

                zdetaillist.add(zstd);

            }
            sd.setComlist(zdetaillist);
        }
        return sd;
    }

    public Stssalesvoucherdetailstab saveautoStssoldtickettabug0make00(
            Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
            String szstationcode, String szsceniccode, String sztickettypecode,
            Edmcrowdkindpricetab eticketprice, List trlist) {
        // 读取游客类型
        Edpcrowdkindtab edkind = (Edpcrowdkindtab) ticketTypeService.get(
                Edpcrowdkindtab.class, eticketprice.getIcrowdkindid());

        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, s.getIscenicid());

        Edmtickettypetab edmticket = (Edmtickettypetab) this.ticketTypeService.get(
                Edmtickettypetab.class, sd.getItickettypeid());
        Edmticketproduct ep = (Edmticketproduct) this.ticketTypeService.get(
                Edmticketproduct.class, sd.getItickettypeid());
        List cdetaillist = new ArrayList();
        for (int j = 1; j <= sd.getIticketnum().intValue(); j++) {
            Stssoldtickettab stsv = new Stssoldtickettab();
            StssoldtickettabId stsvid = new StssoldtickettabId();
            stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
            stsvid.setIticketstationid(sd.getId().getIticketstationid());
            stsvid.setIsalesvoucherdetailsid(sd.getId()
                    .getIsalesvoucherdetailsid());
            stsvid.setSzsoldticketid(new Long(j));
            stsv.setId(stsvid);
            stsv.setIscenicid(s.getIscenicid());
            stsv.setIcrowdkindid(eticketprice.getIcrowdkindid());
            stsv.setSzcrowdkindname(edkind.getSzcrowdkindname());
            stsv.setItickettypeid(sd.getItickettypeid());
            stsv.setUsid(s.getUsid());
            stsv.setIbusinessid(s.getIbusinessid());
            stsv.setDyusid(s.getDyusid());
            stsv.setIplayerperticket(new Long(1));// 人次数
            stsv.setDtstartdate(sd.getDtstartdate());
            stsv.setDtenddate(sd.getDtenddate());
            stsv.setMhandcharge(new Double(0));
            stsv.setByvalidity("00");
            stsv.setDtmakedate(s.getDtmakedate());
            stsv.setBymaketicketway("00");
            List<Map> iserialnumlist = new ArrayList();
            try {
                iserialnumlist = ticketTypeService
                        .findBySqlToMap("select ticketid_sequence.nextval  from dual");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Long iserialnum = new Long(
                    (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());
            stsv.setIserialnum(iserialnum);
            String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
            StringBuffer printno = new StringBuffer();
            printno.append(szstationcode);
            printno.append(szsceniccode);
            printno.append(sztickettypecode);
            if (newmaxorno.length() < 6) {
                for (int b = 0; b < 6 - newmaxorno.length(); b++) {
                    printno.append("0");
                }
            }
            printno.append(newmaxorno);
            String szprintno = Tools.ticketMakeMd5(printno.toString());
            stsv.setSzticketprintno(szprintno);
            stsv.setMremainmoney(new Double(0));
            stsv.setMpresentmoney(new Double(0));
            stsv.setMactualsaleprice(sd.getMactualsaleprice());
            stsv.setIpresentnum(new Long(0));
            stsv.setIremainnum(new Long(1));// 剩余数量
            stsv.setMnominalfee(new Double(0));
            stsv.setMdeposit(new Double(0));
            stsv.setByticketpurpose("00");
            stsv.setBisrefundbalance(new Long(1));
            stsv.setByactivation("02");
            if (trlist != null && trlist.size() > 0) {
                for (int r = 0; r < trlist.size(); r++) {
                    Trealname realname = (Trealname) trlist.get(r);
                    if (Long.parseLong(realname.getIcrowdkindid()) == stsv
                            .getIcrowdkindid().longValue()
                            && Long.parseLong(realname.getItickettypeid()) == stsv
                            .getItickettypeid().longValue()) {
                        stsv.setMyzj(realname.getIdcard());
                        stsv.setName1(realname.getCname());
                        trlist.remove(r);
                        break;
                    }
                }
            }
            List sczlist = new ArrayList();
            // 将售出门票子表信息保存
            List clist = new ArrayList();

            List comlist = sd.getComlist();
            int a = 1;
            for (int k = 1; k <= comlist.size(); k++) {

                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) comlist
                        .get(k - 1);
                List opwwlist = ticketTypeService
                        .find(" from Opwwicketsettab where itickettypeid="
                                + zstd.getItickettypeid()
                                + " and izticktypeid="
                                + zstd.getIztickettypeid() + " and byisuse=1");
                for (int m = 1; m <= opwwlist.size(); m++) {

                    Opwwicketsettab opww = (Opwwicketsettab) opwwlist
                            .get(m - 1);
                    Stssoldticketsubtab stss = new Stssoldticketsubtab();
                    StssoldticketsubtabId stssid = new StssoldticketsubtabId();
                    stssid.setIticketstationid(s.getId().getIticketstationid());
                    stssid.setSzsoldticketid(new Long(j));
                    stssid.setSzsoldticketsubid(new Long(a));

                    stssid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stssid.setIsalesvoucherdetailsid(zstd.getId()
                            .getIsalesvoucherdetailsid());
                    stss.setId(stssid);

                    stss.setIgardengateid(opww.getIgardengateid());
                    stss.setIscenicid(s.getIscenicid());
                    stss.setItickettypeid(zstd.getItickettypeid());
                    stss.setIztickettypeid(zstd.getIztickettypeid());
                    stss.setBychecktype(new Long(0));
                    stss.setByconsumemode(opww.getByconsumemode());
                    stss.setIpasstimes(opww.getIlimittotaltimes());
                    stss.setIpassedtimes(new Long(0));
                    stss.setMsingletimes(zstd.getIsplitamount());
                    stss.setMpasstimes(new Long(1));
                    stss.setMsingledtimes(new Long(0));
                    stss.setMlimitconsume(new Double(0));
                    stss.setMsingleconsume(new Double(0));
                    stss.setMconsumed(new Double(0));
                    stss.setByisout(new Long(1));
                    stss.setIsvalid(new Long(1));
                    stss.setDtmakedate(Tools.getDayTimes());
                    stss.setBylastcheckdir(new Long(0));
                    stss.setIcrowdkindid(eticketprice.getIcrowdkindid());
                    stss.setTripid(zstd.getTripid());

                    stss.setDtbegindate(zstd.getDtstartdate());
                    stss.setDtenddate(zstd.getDtenddate());

                    sczlist.add(stss);
                    Stsschecktab stsc = new Stsschecktab();
                    StsschecktabId stscid = new StsschecktabId();
                    stscid.setIticketstationid(s.getId().getIticketstationid());
                    stscid.setSzsoldticketid(new Long(j));
                    stscid.setSzsoldticketsubid(new Long(a));
                    a++;
                    stscid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stscid.setIsalesvoucherdetailsid(zstd.getId()
                            .getIsalesvoucherdetailsid());
                    stsc.setId(stscid);

                    stsc.setIgardengateid(opww.getIgardengateid());
                    stsc.setIscenicid(s.getIscenicid());
                    stsc.setItickettypeid(zstd.getItickettypeid());
                    stsc.setIztickettypeid(zstd.getIztickettypeid());
                    stsc.setBychecktype(new Long(0));
                    stsc.setByconsumemode(opww.getByconsumemode());
                    if (opww.getIlimittotaltimes().longValue() == 0) {
                        // 多次进园门
                        stsc.setIpasstimes(opww.getIlimittotaltimes());
                    } else {
                        // 限制次数进园门
                        stsc.setIpasstimes(zstd.getIsplitamount()
                                * opww.getIlimittotaltimes());
                    }
                    if (opww.getBywicketconsumetype().equals("01")) {
                        // 一检一人
                        stsc.setMpasstimes(new Long(1));
                    } else {
                        // 一检多人
                        stsc.setMpasstimes(zstd.getIsplitamount());
                    }
                    stsc.setIpassedtimes(new Long(0));
                    stsc.setMsingletimes(zstd.getIsplitamount());
                    stsc.setMsingledtimes(new Long(0));
                    stsc.setMlimitconsume(new Double(0));
                    stsc.setMsingleconsume(new Double(0));
                    stsc.setMconsumed(new Double(0));
                    stsc.setByisout(new Long(1));
                    stsc.setIsvalid(new Long(1));
                    stsc.setDtmakedate(Tools.getDayTimes());
                    stsc.setBylastcheckdir(new Long(0));
                    stsc.setIcrowdkindid(eticketprice.getIcrowdkindid());
                    stsc.setTripid(zstd.getTripid());
                    if (zstd.getTripid() > 0 && zstd.getIvenueseatsid() > 0) {
                        // 存在节目票
                        Trip t = (Trip) ticketTypeService.get(Trip.class,
                                zstd.getTripid());
                        stsv.setSztripid(t.getTripname());
                        stsv.setTriptime(zstd.getDtstartdate());
                        Venueprogram vp = (Venueprogram) this.ticketTypeService.get(
                                Venueprogram.class, zstd.getIvenueseatsid());
                        stsv.setSzprogramname(vp.getSzprogramname());
                        Venue vn = (Venue) this.ticketTypeService.get(Venue.class,
                                zstd.getIvenueid());
                        stsv.setVenueidname(vn.getVenueidname());
                        Venuearea va = (Venuearea) this.ticketTypeService.get(
                                Venuearea.class, zstd.getIvenueareaid());
                        stsv.setIvenueareaname(va.getIvenueareaname());
                        String seatname = "";
                        List seatlist = zstd.getSeatlist();
                        for (int n = 0; n < seatlist.size(); n++) {
                            Seatsaletab so = (Seatsaletab) seatlist.get(n);
                            if (so.getId().getSzsoldticketid().longValue() == stsv
                                    .getId().getSzsoldticketid().longValue()) {
                                List stlist = this.ticketTypeService
                                        .find(" from Venueseats where ivenueseatsid="
                                                + so.getIseatid()
                                                + " and ivenueareaid="
                                                + so.getIvenueareaid()
                                                + " and ivenueid="
                                                + so.getIvenueid());
                                Venueseats vst = (Venueseats) stlist.get(0);
                                seatname = seatname + vst.getSzvenueseatsname();
                            }
                        }
                        stsv.setSzvenueseatsname(seatname);

                    }
                    stsc.setDtbegindate(zstd.getDtstartdate());
                    stsc.setDtenddate(zstd.getDtenddate());
                    stsc.setSzscenicname(scenic.getSzscenicname());
                    stsc.setSzcrowdkindname(edkind.getSzcrowdkindname());
                    stsc.setSzmemo(edkind.getSzmemo());
                    stsc.setSztickettypename(edmticket.getSztickettypename());
                    stsc.setByusage(edmticket.getByusage());
                    stsc.setByuselimit(edmticket.getByuselimit());
                    stsc.setValidityday(edmticket.getValidityday());
                    // 产品属性表

                    if (ep != null) {
                        if (ep.getInoteger3() != null) {
                            stsc.setIsfristtimaeyz(ep.getInoteger3());
                        } else {
                            stsc.setIsfristtimaeyz(0L);
                        }
                        if (ep.getInoteger2() != null) {
                            stsc.setIsfristactive(ep.getInoteger2());
                        } else {
                            stsc.setIsfristactive(0L);
                        }
                    } else {
                        stsc.setIsfristtimaeyz(0L);
                        stsc.setIsfristactive(0L);
                    }
                    // 检票设置表属性
                    stsc.setBywicketconsumetype(opww.getBywicketconsumetype());
                    stsc.setByregfingerprinttype(opww.getByregfingerprinttype());
                    stsc.setItimeinterval(opww.getItimeinterval());
                    stsc.setSzticketprintno(stsv.getSzticketprintno());
                    stsc.setManyouno(stsv.getManyouno());
                    stsc.setMyzj(stsv.getMyzj());
                    stsc.setName1(stsv.getName1());
                    stsc.setZjno1(stsv.getZjno1());
                    stsc.setMactualsaleprice(stsv.getMactualsaleprice());
                    stsc.setIbusinessid(stsv.getIbusinessid());
                    clist.add(stsc);
                }
            }

            stsv.setZlist(sczlist);
            stsv.setClist(clist);
            cdetaillist.add(stsv);

        }
        sd.setMplist(cdetaillist);
        return sd;
    }

    public ResultBean savebenditorder(String orid, Long iscenicid,
                                      Long iemployeeid, Long iticketwinid, Long maxid) throws Exception {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        // 读取订单信息
        ResultBean rb = new ResultBean();
        rb.setColumnCount(9);
        rb.setColumnNames(new String[] { "bymediatype", "bymaketicketway",
                "Yuanmenshuj", "mactualsaleprice", "dtstartdate", "dtenddate",
                "szticketprintno", "dayin", "fujuan" });
//        TOrderId lid = new TOrderId();
//        lid.setOrid(orid);
//        lid.setIscenicid(iscenicid);
        TOrder lorder = ecService.getTOrderInfo(orid, iscenicid.toString());
        if (lorder == null) {
            rs.addRow(new String[] { "false", "该订单不存在" });
            return rs;
        }
        Stssalesvouchertab s = this.savebendiStssalesvouchertab(lorder,
                iemployeeid, maxid, iticketwinid);
        s.setOrdersource("nzzj");
        String zffs = "";
        if (lorder.getDdzt().equals("02")) {
            if (lorder.getId().getOrid().matches("^\\d{8}999\\d{6}")
                    || lorder.getId().getOrid().matches("^\\d{8}888\\d{6}")) {
                zffs = "08";
            } else {
                zffs = "01";

            }
        } else {
            zffs = "00";
        }
        List trlist = ecService.find("from TRealname where orid='" + orid
                + "' and iscenicid= " + iscenicid);
        Stssalessettlementtab st = this.saveStssalessettlementtab(s, zffs);
        Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(
                Esbticketwintab.class, iticketwinid);
        Esbticketstationtab esbticketstation = (Esbticketstationtab) ticketTypeService
                .get(Esbticketstationtab.class, e.getIticketstationid());
        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, iscenicid);
        List detaillist = new ArrayList();
        int snumb = 0;// 总张数
        int dnumb = 1;// 第几张
        List<TOrderlist> olist = ecService.findTOrderList(orid, iscenicid);
        for (int i = 0; i < olist.size(); i++) {
        	TOrderlist lo = olist.get(i);
            Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                    Edmtickettypetab.class, lo.getItickettypeid());
            // 保存售票凭证明细
            Stssalesvoucherdetailstab sd = this.savebendiStssalesvoucherdetailstab(s, lo, eticket);
            List<TZorderlist> zolist = ecService.getTZorderlist(lo.getId().getOrderlistid(), orid, iscenicid);
            // 保存售出门票子表明细
            sd = this.SavebendiStscomticketsalesdetailstab(sd, zolist);
            Edmcrowdkindpricetab eticketprice = (Edmcrowdkindpricetab) ticketTypeService
                    .get(Edmcrowdkindpricetab.class, lo.getIcrowdkindpriceid());
            if (eticket.getByusage() == 0) {
                // 一票一人
                if (eticket.getBymaketicketway().equals("00")) {
                    // 现场打印
                    List zjlist = new ArrayList();

                    sd = this.savebendiStssoldtickettabug0make00(sd, s,
                                    esbticketstation.getSzstationcode(),
                                    scenic.getSzsceniccode(),
                                    eticket.getSztickettypecode(),
                                    eticketprice, trlist);

                } else if (eticket.getBymaketicketway().equals("01")) {
                    // 现场激活
                    rs.addRow(new String[] { "false", "不支持现场激活票务" });
                    return rs;
                }
            } else {
                if (eticket.getBymaketicketway().equals("00")) {
                    sd = this.saveStssoldtickettabug1make00(sd, s,
                            esbticketstation.getSzstationcode(),
                            scenic.getSzsceniccode(),
                            eticket.getSztickettypecode(),
                            eticketprice.getIcrowdkindid());

                } else if (eticket.getBymaketicketway().equals("01")) {
                    // 现场激活
                    rs.addRow(new String[] { "false", "不支持现场激活票务" });
                    return rs;
                }
            }

            detaillist.add(sd);
            snumb = snumb + sd.getMplist().size();
        }

        // 读取副卷打印设置
        List sprintlist = this.getsodeprintmanage(
                lorder.getId().getIscenicid(), s.getIbusinessid());
//        List syslist = sysService.find(" from Sysparv5 where id.pmky='PRSZ'");
        List<Sysparv5> syslist = (List<Sysparv5>) sysService.findSysparByPmky("PRSZ", "");
        if (sprintlist != null && sprintlist.size() > 0) {
            for (int m = 0; m < sprintlist.size(); m++) {
                Soderollprintmanage p = (Soderollprintmanage) sprintlist.get(m);
                for (int n = 0; n < syslist.size(); n++) {
                    Sysparv5 sv5 = (Sysparv5) syslist.get(n);
                    if (sv5.getId().getPmcd().equals(p.getId().getPrintno())) {
                        p.setSzprintno(sv5.getPmva());
                        p.setPrintype(sv5.getIsa().toString());
                        if (sv5.getIsa() == 0) {
                            p.setNote(sv5.getPmvb());
                        }
                    }
                }
            }
        }
        try {
            System.out.println("开始保存数据1:更新t_order");
//            YOrder yorder = (YOrder) ecService.getYOrderListChildList(orid, iscenicid.toString());
            YOrder yorder = ecService.getYOrderInfoById(orid, iscenicid);
            yorder.setNotec(Tools.getNowString());
            yorder.setIsc(iemployeeid);
            if (!yorder.getDdzt().equals("02")) {
                rs.addRow(new String[] { "fasle", "订单状态不是已付款状态不允许出票" });
                return rs;
            }
            yorder.setDdzt("11");//11
            yorder.setNotec(Tools.getNowString());
            yorder.setIsc(iemployeeid);
            ecService.updateYOrder(yorder);
            List<TOrder> listtorder = ecService.getTOrdersByOrid(orid);
            boolean b = true;
            for (int i = 0; i < listtorder.size(); i++) {
//                TOrder t = (TOrder) listtorder.get(i);
            	TOrder t = listtorder.get(i);
                if (t.getId().getIscenicid().longValue() == iscenicid) {
                    if (t.getZfmont().doubleValue() != s.getIacceptmoney()) {
                        rs.addRow(new String[] { ""
                        		+ "fasle", "出票金额与订单金额不符" });
                        return rs;
                    }
                    if (!t.getDdzt().equals("02")) {
                        rs.addRow(new String[] { "fasle", "订单状态不是已付款状态不允许出票" });
                        return rs;
                    }
                    t.setDdzt("11");
                    t.setNotec(Tools.getNowString());
                    t.setIsc(iemployeeid);
                    ecService.updateTOrder(t);
                } else {
                    if (t.getDdzt().equals("02")) {
                        b = false;
                    }
                }
            }
            if (b) {
                // 该订单已经全部出票
                MOrder m = (MOrder) ecService.getMorderInfo(orid);
                if (m.getNotea() != null && m.getNotea().equals("50")) {
                    m.setNotea("51");
                }
                if (m.getNotea() != null && m.getNotea().equals("02")) {
                    m.setNotea("11");
                }
                m.setDdzt("11");
                ecService.updateMOrder(m);
            }

            ticketTypeService.save(s);
            ticketTypeService.save(st);
            Sysparv5 sv5 = (Sysparv5) sysService.findOne("PRCS", "01");
            String printcs = "0";
            if (sv5 != null) {
                printcs = sv5.getPmva();
            }
            for (int i = 0; i < detaillist.size(); i++) {
                Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) detaillist
                        .get(i);
                System.out.println("Isalesvoucherdetailsid="
                        + sd.getId().getIsalesvoucherdetailsid());
                ticketTypeService.save(sd);

                List zdetaillist = sd.getComlist();
                for (int j = 0; j < zdetaillist.size(); j++) {
                    Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                            .get(j);
                    List seatlist = zstd.getSeatlist();
                    if (seatlist != null && seatlist.size() > 0) {
                        for (int k = 0; k < seatlist.size(); k++) {
                            Seatsaletab so = (Seatsaletab) seatlist.get(k);
                            ticketTypeService.save(so);
                        }
                    }
                    ticketTypeService.save(zstd);
                }
                List cdetaillist = sd.getMplist();
                for (int j = 0; j < cdetaillist.size(); j++) {
                    Stssoldtickettab stsv = (Stssoldtickettab) cdetaillist
                            .get(j);
                    // 添加返回数据
                    Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService
                            .get(Edmtickettypetab.class,
                                    stsv.getItickettypeid());
                    String bymediatype = eticket.getBymediatype();// 介质类型
                    String bymaketicketway = eticket.getBymaketicketway();// 出票方式
                    String Yuanmenshuj = "";// 园门数据
                    String mactualsaleprice = sd.getMactualsaleprice()
                            .toString();// 单价
                    String dtstartdate = stsv.getDtstartdate();// 开始日期
                    String dtenddate = stsv.getDtenddate();// 结束日期
                    String szticketprintno = stsv.getSzticketprintno();

                    ticketTypeService.save(stsv);

                    List cdzetaillist = stsv.getZlist();
                    for (int k = 0; k < cdzetaillist.size(); k++) {
                        Stssoldticketsubtab stss = (Stssoldticketsubtab) cdzetaillist
                                .get(k);
                        ticketTypeService.save(stss);
                    }
                    List checklist = stsv.getClist();
                    for (int k = 0; k < checklist.size(); k++) {
                        Stsschecktab stss = (Stsschecktab) checklist.get(k);
                        ticketTypeService.save(stss);
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
                                    Long printid = ticketTypeService
                                            .getSequenceId("PRINT_ID");
                                    t.setPrintid(printid);
                                    ticketTypeService.save(t);
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                    System.out.println(e1.getMessage());
                                }
                            }
                        }
                    }

                    if (eticket.getBymaketicketway().equals("00")) {

                        String dayin = CommonUtil.getprintmessage(s, stsv, eticket,
                                esbticketstation,false);

                        String fujian = CommonUtil.getfprintmessage(sprintlist, s,
                                stsv, eticket, esbticketstation);

                        rb.addRow(new String[] { bymediatype, bymaketicketway,
                                Yuanmenshuj, mactualsaleprice, dtstartdate,
                                dtenddate, szticketprintno, dayin, fujian });
                    } else {
                        rb.addRow(new String[] { bymediatype, bymaketicketway,
                                Yuanmenshuj, mactualsaleprice, dtstartdate,
                                dtenddate, szticketprintno, "", "" });
                    }
                    dnumb++;
                }
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        return rb;

    }

    public Stssalesvouchertab savebendiStssalesvouchertab(TOrder t_order,
                                                          Long iemployeeid, Long maxid, Long iticketwinid) {
        Stssalesvouchertab s = new Stssalesvouchertab();
        s.setIscenicid(t_order.getId().getIscenicid());
        s.setIticketwinid(iticketwinid);
        s.setIbusinessid(t_order.getIbusinessid());
        s.setIhandler(iemployeeid);
        s.setIpayeer(iemployeeid);
        s.setImaker(iemployeeid);
        s.setIauditor(iemployeeid);
        s.setIaccountreceivable(t_order.getZfmont());// 实收
        s.setIacceptmoney(t_order.getZfmont());// 应收
        s.setIgivechange(new Double(0));// 找零
        s.setSzsalesvoucherno(t_order.getId().getOrid());
        s.setIyear(new Long(Tools.getDays().substring(0, 4)));
        s.setImonth(new Long(Tools.getDays().substring(5, 7)));
        s.setIday(new Long(Tools.getDays().substring(8, 10)));
        s.setDtmakedate(Tools.getDayTimes());
        s.setDtauditdate(t_order.getDtstartdate());
        s.setUsid(t_order.getUsid());
        s.setBisintegral(new Long(0));
        s.setByprintinvoice(new Long(0));
        s.setBysplitway(new Long(2));
        s.setBisreturn(new Long(1));
        s.setBysalesvouchertype("01");
        s.setBypostrecord(new Long(0));
        s.setBysalesvoucherstate(new Long(1));
        s.setBispay(new Long(0));
        s.setBispayee(new Long(0));
        s.setSztravelbillno(t_order.getSztravelbillno());
        s.setIregionalid(new Long(t_order.getIregionalid()));
        if (t_order.getDyusid() == null || t_order.getDyusid().equals("")) {
            s.setDyusid("daoyou");
        } else {
            s.setDyusid(t_order.getDyusid());
        }
        s.setOrnote1(t_order.getOrnote1());
        s.setOrnote2(t_order.getOrnote2());
        s.setOrnote3(t_order.getOrnote3());
        s.setOrnote4(t_order.getOrnote4());
        s.setOrnote5(t_order.getOrnote5());
        s.setOrnote6(t_order.getOrnote6());
        s.setOrnote7(t_order.getOrnote7());
        s.setOrnote8(t_order.getOrnote8());
        s.setOrnote9(t_order.getOrnote9());
        s.setOrnote10(t_order.getOrnote10());
        s.setTdlx(t_order.getTdlx());
        Esbticketwintab e = (Esbticketwintab) ticketTypeService.get(
                Esbticketwintab.class, s.getIticketwinid());
        StssalesvouchertabId id = new StssalesvouchertabId();
        id.setIticketstationid(e.getIticketstationid());
        id.setIsalesvoucherid(maxid);
        s.setId(id);
        // s.getId().setIsalesvoucherid(maxid);
        return s;
    }

    public Stssalesvoucherdetailstab savebendiStssalesvoucherdetailstab(
            Stssalesvouchertab s, TOrderlist lo, Edmtickettypetab eticket) {
        Stssalesvoucherdetailstab sd = new Stssalesvoucherdetailstab();
        StssalesvoucherdetailstabId sdid = new StssalesvoucherdetailstabId();
        sdid.setIsalesvoucherdetailsid(lo.getId().getOrderlistid());
        sdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
        sdid.setIticketstationid(s.getId().getIticketstationid());
        sd.setId(sdid);
        sd.setIticketwinid(s.getIticketwinid());
        sd.setIcrowdkindpriceid(lo.getIcrowdkindpriceid());
        sd.setItickettypeid(lo.getItickettypeid());
        if (eticket.getByusage() == 0) {
            sd.setIplayerperticket(new Long(1));// 人/张
            sd.setIticketplayer(lo.getNumb());// 人次
            sd.setIticketnum(lo.getNumb());
        } else {
            sd.setIticketnum(new Long(1));// 张数
            sd.setIticketplayer(lo.getNumb());
            sd.setIplayerperticket(lo.getNumb());// 人/张
        }
        sd.setDtstartdate(lo.getDtstartdate());
        sd.setDtenddate(lo.getDtenddate());
        sd.setSzstartserial("0");
        sd.setSzendserial("0");
        sd.setIstartid(new Long(0));
        sd.setIendid(new Long(0));
        sd.setIoffersschemeid(new Long(0));
        sd.setIamount(lo.getNumb());
        sd.setIpresentnums(new Long(0));// 退订数量
        sd.setIderatenums(lo.getYhnumb());
        sd.setIfactnum(new Long(0));
        sd.setIuseablenessnum(lo.getNumb());// 使用数量
        sd.setMactualsaleprice(lo.getPric());// 实际售价
        sd.setMeventmoney(lo.getAmnt());// 实际发生金额
        sd.setMderatemoney(lo.getYhamnt());// 减免金额
        sd.setMpresentmoney(new Double(0));// 退订金额
        sd.setMnominalfee(new Double(0));// 工本费
        sd.setMdeposit(new Double(0));
        sd.setMhandcharge(new Double(0));
        sd.setByconsumetype("00");
        sd.setIconsumenum(new Double(0));
        sd.setMtotalamount(lo.getAmnt());
        sd.setItotalnumber(lo.getNumb());// 总次数
        sd.setItotalminutes(new Long(0));
        sd.setByisout(new Long(1));
        sd.setDtmakedate(Tools.getDayTimes());
        sd.setIversion(new Long(0));
        return sd;
    }

    public Stssalesvoucherdetailstab SavebendiStscomticketsalesdetailstab(
            Stssalesvoucherdetailstab sd, List zolist) {
        int yxq = 1;
        Edmticketproduct et = (Edmticketproduct) ticketTypeService.get(
                Edmticketproduct.class, new Long(sd.getItickettypeid()));
        if (et == null) {
            yxq = 0;
        } else {
            if (et.getInoteger1() == null || et.getInoteger1() == 0) {
                yxq = 0;
            }
        }
        Edmtickettypetab eticket = (Edmtickettypetab) ticketTypeService.get(
                Edmtickettypetab.class, sd.getItickettypeid());
        List zdetaillist = new ArrayList();
        for (int i = 0; i < zolist.size(); i++) {
            TZorderlist zlo = (TZorderlist) zolist.get(i);
            Stscomticketsalesdetailstab zstd = new Stscomticketsalesdetailstab();
            StscomticketsalesdetailstabId zstdid = new StscomticketsalesdetailstabId();
            Long isalesvoucherdetailsid = zlo.getId().getOrderlistid();
            Long icrowdkindpriceid = sd.getIcrowdkindpriceid();
            Long itickettypeid = sd.getItickettypeid();
            Long iztickettypeid = zlo.getIztickettypeid();
            Long isplitamount = zlo.getZnumb();
            Long tripid = new Long(0);
            Long ivenueid = new Long(0);
            Long ivenueareaid = new Long(0);
            Long ivenueseatsid = new Long(0);
            Long itripprdcontrolid = new Long(0);
            String dtstartdate = zlo.getDtstartdate();
            String dtenddate = zlo.getDtenddate();
            if (zlo.getTripid() != null) {
                tripid = new Long(zlo.getTripid());
            }
            if (zlo.getIvenueid() != null) {
                ivenueid = new Long(zlo.getIvenueid());
            }
            if (zlo.getIvenueareaid() != null) {
                ivenueareaid = new Long(zlo.getIvenueareaid());
            }
            if (zlo.getIvenueareaid() != null) {
                ivenueseatsid = new Long(zlo.getIvenueseatsid());
            }
            if (zlo.getIse() != null) {
                itripprdcontrolid = new Long(zlo.getIse());
            }
            System.out.println("tripid=" + tripid);
            System.out.println("ivenueareaid=" + ivenueareaid);
            System.out.println("ivenueid=" + ivenueid);
            System.out.println("ivenueseatsid=" + ivenueseatsid);
            System.out.println("itripprdcontrolid=" + itripprdcontrolid);
            System.out.println("Stscomticketsalesdetails4444444444444444");
            zstd.setTripid(tripid);
            zstd.setIvenueareaid(ivenueareaid);
            zstd.setIvenueid(ivenueid);
            zstd.setIvenueseatsid(ivenueseatsid);
            zstd.setItripprdcontrolid(itripprdcontrolid);
            zstdid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
            zstdid.setIticketstationid(sd.getId().getIticketstationid());
            zstdid.setIsalesvoucherdetailsid(isalesvoucherdetailsid);
            zstdid.setIcomticketsalesdetailsid(zlo.getId().getZorderlistid());
            zstd.setId(zstdid);
            zstd.setIcrowdkindpriceid(icrowdkindpriceid);
            zstd.setItickettypeid(itickettypeid);
            zstd.setIztickettypeid(iztickettypeid);
            zstd.setMhandcharge(new Double(0));
            zstd.setDtmakedate(Tools.getDayTimes());
            System.out.println("Stscomticketsalesdetails55555555555555555");
            if (zstd.getTripid().longValue() > 0) {
                zstd.setDtstartdate(zlo.getDtstartdate());
                zstd.setDtenddate(zlo.getDtenddate());
            } else {
                if (yxq == 0) {
                    if (zlo.getDtstartdate().length() > 10) {
                        zstd.setDtstartdate(zlo.getDtstartdate());
                        zstd.setDtenddate(zlo.getDtenddate());
                    } else {
                        zstd.setDtstartdate(zlo.getDtstartdate() + " 00:00:00");
                        zstd.setDtenddate(zlo.getDtenddate() + " 23:59:59");
                    }
                } else {

                    if (zlo.getDtstartdate().length() > 10) {

                        zstd.setDtstartdate(zlo.getDtstartdate());
                        zstd.setDtenddate(zlo.getDtenddate());
                    } else {
                        zstd.setDtstartdate(zlo.getDtstartdate() + " 00:00:00");
                        zstd.setDtenddate(zlo.getDtenddate() + " 23:59:59");
                    }
                }
            }
            System.out
                    .println("Stscomticketsalesdetails666666666666666666666666");
            if (eticket!=null&&eticket.getInt1()!=null&&eticket.getInt1() == 1) {
                // 有效开始时间已当前时间开始
                if (Tools.getTodayString().equals(zstd.getDtstartdate().substring(0,10))) {
                    String nowtime=Tools.getNowTime();
                    zstd.setDtstartdate(zstd.getDtstartdate().substring(0,10)+" "+nowtime);
                    zstd.setDtenddate(Tools.getDate(zstd.getDtenddate().substring(0,10), 1) + " "
                            + nowtime);
                }
            }

            zstd.setIversion(new Long(0));
            zstd.setIsplitamount(new Long(zlo.getZnumb()));
            zstd.setMsplitprice(new Double(zlo.getZpric()));
            zstd.setMsplitmoney(new Double(zlo.getZamnt()));
            zstd.setIderatenums(new Long(zlo.getZyhnumb()));
            zstd.setMderatemoney(new Double(zlo.getZyhamnt()));
            zstd.setSeatsid(zlo.getSeats());
            System.out
                    .println("Stscomticketsalesdetails7777777777777777777777");
            if (itripprdcontrolid > 0) {
                // 保存座位的List
                List seatlist = new ArrayList();
                List slist = this.ticketTypeService
                        .find(" from Seatordertab where id.orid='"
                                + zlo.getId().getOrid() + "' and id.iscenicid="
                                + zlo.getId().getIscenicid()
                                + " and id.orderlistid="
                                + zlo.getId().getOrderlistid()
                                + " and id.zorderlistid="
                                + zlo.getId().getZorderlistid());
                System.out
                        .println("Stscomticketsalesdetails8888888888888888888888888888888");
                for (int j = 0; j < slist.size(); j++) {
                    Seatordertab sod = (Seatordertab) slist.get(j);
                    Seatsaletab so = new Seatsaletab();
                    SeatsaletabId soid = new SeatsaletabId();
                    soid.setIsalesvoucherdetailsid(zlo.getId().getOrderlistid());
                    soid.setIcomticketsalesdetailsid(zlo.getId()
                            .getZorderlistid());
                    soid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
                    soid.setIticketstationid(sd.getId().getIticketstationid());
                    soid.setSeq(new Long(j + 1));
                    if (eticket.getByusage() == 0) {
                        soid.setSzsoldticketid(new Long(j + 1));
                    } else {
                        soid.setSzsoldticketid(new Long(1));
                    }
                    so.setId(soid);
                    so.setIprogramid(new Long(zlo.getIvenueseatsid()));
                    so.setIseatid(sod.getIseatid());
                    so.setIsvalid(1L);
                    so.setItripid(zstd.getTripid());
                    so.setItripprdcontrolid(zstd.getItripprdcontrolid());
                    so.setIvenueid(zstd.getIvenueid());
                    so.setIvenueareaid(zstd.getIvenueareaid());
                    so.setDtmakedate(Tools.getDayTimes());
                    so.setStartdate(zlo.getDtstartdate().substring(0, 10));
                    System.out.println("Iseatid=" + so.getIseatid());
                    seatlist.add(so);
                }
                System.out.println("seatlist.size()=" + seatlist.size());

                zstd.setSeatlist(seatlist);
            }
            zdetaillist.add(zstd);
        }
        sd.setComlist(zdetaillist);
        return sd;
    }

    public Stssalesvoucherdetailstab savebendiStssoldtickettabug0make00(
            Stssalesvoucherdetailstab sd, Stssalesvouchertab s,
            String szstationcode, String szsceniccode, String sztickettypecode,
            Edmcrowdkindpricetab eticketprice, List trlist) {
        // 读取游客类型
        System.out.println("savebendiStssoldtickettabug0make001");
        Esbscenicareatab scenic = (Esbscenicareatab) ticketTypeService.get(
                Esbscenicareatab.class, s.getIscenicid());
        Edpcrowdkindtab edkind = (Edpcrowdkindtab) ticketTypeService.get(
                Edpcrowdkindtab.class, eticketprice.getIcrowdkindid());
        Edmtickettypetab edmticket = (Edmtickettypetab) this.ticketTypeService.get(
                Edmtickettypetab.class, sd.getItickettypeid());
        Edmticketproduct ep = (Edmticketproduct) this.ticketTypeService.get(
                Edmticketproduct.class, sd.getItickettypeid());
        List cdetaillist = new ArrayList();
        for (int j = 1; j <= sd.getIticketnum().intValue(); j++) {
            Stssoldtickettab stsv = new Stssoldtickettab();
            StssoldtickettabId stsvid = new StssoldtickettabId();
            stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
            stsvid.setIticketstationid(sd.getId().getIticketstationid());
            stsvid.setIsalesvoucherdetailsid(sd.getId()
                    .getIsalesvoucherdetailsid());
            stsvid.setSzsoldticketid(new Long(j));
            stsv.setId(stsvid);
            stsv.setIscenicid(s.getIscenicid());
            stsv.setIcrowdkindid(eticketprice.getIcrowdkindid());
            stsv.setSzcrowdkindname(edkind.getSzcrowdkindname());
            stsv.setItickettypeid(sd.getItickettypeid());
            stsv.setUsid(s.getUsid());
            stsv.setIbusinessid(s.getIbusinessid());
            stsv.setDyusid(s.getDyusid());
            stsv.setIplayerperticket(new Long(1));// 人次数
            stsv.setDtstartdate(sd.getDtstartdate());
            stsv.setDtenddate(sd.getDtenddate());
            stsv.setMhandcharge(new Double(0));
            stsv.setByvalidity("00");
            stsv.setDtmakedate(s.getDtmakedate());
            stsv.setBymaketicketway("00");
            List<Map> iserialnumlist = new ArrayList();
            try {
                iserialnumlist = ticketTypeService
                        .findBySqlToMap("select ticketid_sequence.nextval  from dual");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            Long iserialnum = new Long(
                    (((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());
            stsv.setIserialnum(iserialnum);
            String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
            StringBuffer printno = new StringBuffer();
            printno.append(szstationcode);
            printno.append(szsceniccode);
            printno.append(sztickettypecode);
            if (newmaxorno.length() < 6) {
                for (int b = 0; b < 6 - newmaxorno.length(); b++) {
                    printno.append("0");
                }
            }
            printno.append(newmaxorno);
            String szprintno = Tools.ticketMakeMd5(printno.toString());
            stsv.setSzticketprintno(szprintno);
            stsv.setMremainmoney(new Double(0));
            stsv.setMpresentmoney(new Double(0));
            stsv.setMactualsaleprice(sd.getMactualsaleprice());
            stsv.setIpresentnum(new Long(0));
            stsv.setIremainnum(new Long(1));// 剩余数量
            stsv.setMnominalfee(new Double(0));
            stsv.setMdeposit(new Double(0));
            stsv.setByticketpurpose("00");
            stsv.setBisrefundbalance(new Long(1));
            stsv.setByactivation("02");
            if (trlist != null && trlist.size() > 0) {
                for (int r = 0; r < trlist.size(); r++) {
//                    TRealname realname = (TRealname) trlist.get(r);
                	Map map = (Map) trlist.get(r);
                	TRealname realname = new TRealname();
                	try {
						BeanUtils.populate(realname, map);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
                    if (realname.getIcrowdkindid().longValue() == stsv
                            .getIcrowdkindid().longValue()
                            && realname.getItickettypeid().longValue() == stsv
                            .getItickettypeid().longValue()) {
                        stsv.setMyzj(realname.getIdcard());
                        stsv.setName1(realname.getCname());
                        trlist.remove(r);
                        break;
                    }
                }
            }
            List sczlist = new ArrayList();
            List clist = new ArrayList();
            // 将售出门票子表信息保存
            List comlist = sd.getComlist();
            int a = 1;
            for (int k = 1; k <= comlist.size(); k++) {
                System.out.println("savebendiStssoldtickettabug0make002");
                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) comlist
                        .get(k - 1);
                System.out.println("zstd.getTripid()=" + zstd.getTripid());
                System.out.println("zstd.getIvenueseatsid()="
                        + zstd.getIvenueseatsid());
                if (zstd.getTripid() > 0 && zstd.getIvenueseatsid() > 0) {
                    // 存在节目票
                    System.out.println("保存节目信息到stsv");
                    Trip t = (Trip) ticketTypeService
                            .get(Trip.class, zstd.getTripid());
                    stsv.setSztripid(t.getTripname());
                    stsv.setTriptime(zstd.getDtstartdate());
                    Venueprogram vp = (Venueprogram) this.ticketTypeService.get(
                            Venueprogram.class, zstd.getIvenueseatsid());
                    stsv.setSzprogramname(vp.getSzprogramname());
                    Venue vn = (Venue) this.ticketTypeService.get(Venue.class,
                            zstd.getIvenueid());
                    stsv.setVenueidname(vn.getVenueidname());
                    Venuearea va = (Venuearea) this.ticketTypeService.get(
                            Venuearea.class, zstd.getIvenueareaid());
                    stsv.setIvenueareaname(va.getIvenueareaname());
                    String seatname = "";
                    List seatlist = zstd.getSeatlist();
                    System.out.println("seatlist=" + seatlist.size());
                    for (int n = 0; n < seatlist.size(); n++) {
                        Seatsaletab so = (Seatsaletab) seatlist.get(n);
                        System.out.println("so.getId().getSzsoldticketid()="
                                + so.getId().getSzsoldticketid());
                        System.out.println("stsv.getId().getSzsoldticketid()="
                                + stsv.getId().getSzsoldticketid());
                        if (so.getId().getSzsoldticketid().longValue() == stsv
                                .getId().getSzsoldticketid().longValue()) {
                            System.out.println("so.getIseatid()"
                                    + so.getIseatid());
                            List stlist = this.ticketTypeService
                                    .find(" from Venueseats where ivenueseatsid="
                                            + so.getIseatid()
                                            + " and ivenueareaid="
                                            + so.getIvenueareaid()
                                            + " and ivenueid="
                                            + so.getIvenueid());
                            Venueseats vst = (Venueseats) stlist.get(0);
                            seatname = seatname + vst.getSzvenueseatsname();
                        }
                    }
                    System.out.println("seatname=" + seatname);
                    stsv.setSzvenueseatsname(seatname);

                }
                List opwwlist = ticketTypeService
                        .find(" from Opwwicketsettab where itickettypeid="
                                + zstd.getItickettypeid()
                                + " and izticktypeid="
                                + zstd.getIztickettypeid() + " and byisuse=1");
                for (int m = 1; m <= opwwlist.size(); m++) {

                    Opwwicketsettab opww = (Opwwicketsettab) opwwlist
                            .get(m - 1);
                    Stssoldticketsubtab stss = new Stssoldticketsubtab();
                    StssoldticketsubtabId stssid = new StssoldticketsubtabId();
                    stssid.setIticketstationid(s.getId().getIticketstationid());
                    stssid.setSzsoldticketid(new Long(j));
                    stssid.setSzsoldticketsubid(new Long(a));
                    stssid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stssid.setIsalesvoucherdetailsid(zstd.getId()
                            .getIsalesvoucherdetailsid());
                    stss.setId(stssid);

                    stss.setIgardengateid(opww.getIgardengateid());
                    stss.setIscenicid(s.getIscenicid());
                    stss.setItickettypeid(zstd.getItickettypeid());
                    stss.setIztickettypeid(zstd.getIztickettypeid());
                    stss.setBychecktype(new Long(0));
                    stss.setByconsumemode(opww.getByconsumemode());
                    stss.setIpasstimes(opww.getIlimittotaltimes());
                    stss.setIpassedtimes(new Long(0));
                    stss.setMsingletimes(zstd.getIsplitamount());
                    stss.setMpasstimes(new Long(1));
                    stss.setMsingledtimes(new Long(0));
                    stss.setMlimitconsume(new Double(0));
                    stss.setMsingleconsume(new Double(0));
                    stss.setMconsumed(new Double(0));
                    stss.setByisout(new Long(1));
                    stss.setIsvalid(new Long(1));
                    stss.setDtmakedate(Tools.getDayTimes());
                    stss.setBylastcheckdir(new Long(0));
                    stss.setIcrowdkindid(eticketprice.getIcrowdkindid());
                    stss.setTripid(zstd.getTripid());

                    stss.setDtbegindate(zstd.getDtstartdate());
                    stss.setDtenddate(zstd.getDtenddate());

                    sczlist.add(stss);

                    Stsschecktab stsc = new Stsschecktab();
                    StsschecktabId stscid = new StsschecktabId();
                    stscid.setIticketstationid(s.getId().getIticketstationid());
                    stscid.setSzsoldticketid(new Long(j));
                    stscid.setSzsoldticketsubid(new Long(a));
                    a++;
                    stscid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stscid.setIsalesvoucherdetailsid(zstd.getId()
                            .getIsalesvoucherdetailsid());
                    stsc.setId(stscid);

                    stsc.setIgardengateid(opww.getIgardengateid());
                    stsc.setIscenicid(s.getIscenicid());
                    stsc.setItickettypeid(zstd.getItickettypeid());
                    stsc.setIztickettypeid(zstd.getIztickettypeid());
                    stsc.setBychecktype(new Long(0));
                    stsc.setByconsumemode(opww.getByconsumemode());
                    stsc.setIpasstimes(opww.getIlimittotaltimes());
                    stsc.setIpassedtimes(new Long(0));
                    stsc.setMsingletimes(zstd.getIsplitamount());
                    stsc.setMpasstimes(new Long(1));
                    stsc.setMsingledtimes(new Long(0));
                    stsc.setMlimitconsume(new Double(0));
                    stsc.setMsingleconsume(new Double(0));
                    stsc.setMconsumed(new Double(0));
                    stsc.setByisout(new Long(1));
                    stsc.setIsvalid(new Long(1));
                    stsc.setDtmakedate(Tools.getDayTimes());
                    stsc.setBylastcheckdir(new Long(0));
                    stsc.setIcrowdkindid(eticketprice.getIcrowdkindid());
                    stsc.setTripid(zstd.getTripid());

                    stsc.setDtbegindate(zstd.getDtstartdate());
                    stsc.setDtenddate(zstd.getDtenddate());
                    stsc.setSzscenicname(scenic.getSzscenicname());
                    stsc.setSzcrowdkindname(edkind.getSzcrowdkindname());
                    stsc.setSzmemo(edkind.getSzmemo());
                    stsc.setSztickettypename(edmticket.getSztickettypename());
                    stsc.setByusage(edmticket.getByusage());
                    stsc.setByuselimit(edmticket.getByuselimit());
                    stsc.setValidityday(edmticket.getValidityday());
                    // 产品属性表

                    if (ep != null) {
                        if (ep.getInoteger3() != null) {
                            stsc.setIsfristtimaeyz(ep.getInoteger3());
                        } else {
                            stsc.setIsfristtimaeyz(0L);
                        }
                        if (ep.getInoteger2() != null) {
                            stsc.setIsfristactive(ep.getInoteger2());
                        } else {
                            stsc.setIsfristactive(0L);
                        }
                    } else {
                        stsc.setIsfristtimaeyz(0L);
                        stsc.setIsfristactive(0L);
                    }
                    // 检票设置表属性
                    stsc.setBywicketconsumetype(opww.getBywicketconsumetype());
                    stsc.setByregfingerprinttype(opww.getByregfingerprinttype());
                    stsc.setItimeinterval(opww.getItimeinterval());
                    stsc.setSzticketprintno(stsv.getSzticketprintno());
                    stsc.setManyouno(stsv.getManyouno());
                    stsc.setMyzj(stsv.getMyzj());
                    stsc.setName1(stsv.getName1());
                    stsc.setZjno1(stsv.getZjno1());
                    stsc.setMactualsaleprice(stsv.getMactualsaleprice());
                    stsc.setIbusinessid(stsv.getIbusinessid());
                    clist.add(stsc);

                }
            }
            stsv.setClist(clist);
            stsv.setZlist(sczlist);
            cdetaillist.add(stsv);
        }

        sd.setMplist(cdetaillist);
        return sd;
    }

    public ResultBean cancelorder(String orid,String url){
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] {"returnstats","message"});
//        List lorderlist = this.ticketTypeService.find("from LOrder where id.orid='"+orid+"' ");
        List lorderlist = ecService.getLorderByOrid(orid);
        if(lorderlist == null || lorderlist.size() == 0){
            rs.addRow(new String[] {"true","取消成功"});
            return rs;
        }else{
            for(int i=0;i<lorderlist.size();i++){
                LOrder l = (LOrder) lorderlist.get(i);
                l.setDdzt("98");
                ecService.updateLOrder(l);
            }
            if(WebContant.GetKeyValue("IsCenterUrl").equals("1")){
                try {/*
                    javax.xml.rpc.Service service = null;
                    java.net.URL endpointURL = new java.net.URL(
                            "http://"
                                    + url
                                    + "/services/centersaleService?wsdl");
                    CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                            endpointURL, service);
                    ssl.setMaintainSession(true);
                    ssl.deleteStock(orid, null);
                */} catch (Exception e2) {
                    e2.printStackTrace();
                }
            }else{
//                IStockService stockService = (IStockService) SpringUtil.getBean("stockService");
                stockService.deleteStockDetails(orid,null,null,null);
            }
            rs.addRow(new String[] {"true","取消成功"});
            return rs;
        }
    }

    public String changeSzprintNo(String szticketprintno,String orid,String usid,Edmtickettypetab ticket){
        try{
            Hotelprovider hp = (Hotelprovider) ticketTypeService.get(Hotelprovider.class,ticket.getIscenicid());
            if(hp != null && !StringUtils.isBlank(hp.getNoted9())){
                String website = hp.getNoted9().replace("{1}", szticketprintno).replace("{2}", orid).replace("{3}", URLEncoder.encode(ticket.getSztickettypename()))
                        .replace("{4}", ticket.getIscenicid().toString());
                if(website.contains("{99}")){
                    Custom custom = (Custom) this.ticketTypeService.get(Custom.class,usid);
                    if(custom != null && custom.getTtlb().equals("99")){
                        website = website.replace("{5}",usid);
                    }else{
                        usid = "offline";
                        website = website.replace("{5}",usid);
                    }
                    String signauture = EncryptUtil.MD5Hex(usid+orid+szticketprintno+"skyring999");
                    website = website.replace("{99}",signauture.substring(3,9));
                }else{
                    website = website.replace("{5}",usid);
                }
                szticketprintno = website+"#"+szticketprintno;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return szticketprintno;
    }

	/*public ResultBean getTicketTimeCount(String productCode) {
	 	//根据产品编码获取，分时时段列表
        String timeHql = "select new map(time.id as id,time.startDate as startDate,time.endDate as endDate,time.tatalStock as tatalStock,"
        		+ "time.currStock as currStock,time.saleStock as saleStock,time.productId as productId) "
        		+ "from TimeSharingTicketTab time where time.productId = '"+productCode+"'";
       List<Map> find = ticketTypeService.find(timeHql);
       if(find==null)
       {
    	   find=new ArrayList<Map>();
       }
       return MapToResultBean.toResultBean(find);
	}*/
}
