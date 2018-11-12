package com.ectrip.ticket.common.checkUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ectrip.base.util.BeanUtil;
import com.ectrip.base.util.MathUtil;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.BalanceRequest;
import com.ectrip.hqyt.model.response.JSONBalance;
import com.ectrip.sys.model.balance.Vipbalance;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.PMSVersion;
import com.ectrip.sys.model.syspar.Printticketmanage;
import com.ectrip.sys.model.syspar.Soderollprintmanage;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.order.Seatsaletab;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.StssalesvoucherdetailstabId;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.permitenter.Priceprintmanager;
import com.ectrip.ticket.model.permitenter.PriceprintmanagerId;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.venuemarketing.Trip;
import com.ectrip.ticket.model.venuemarketing.Venuearea;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.model.venuemarketing.Venueseats;
import com.ectrip.ticket.provider.service.IBannerService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.util.JaxWsDynamicClientFactoryUtil;
import com.ectrip.ticket.util.ServerNameConst;

/**
 * Created by cxh on 2016/02/26.
 */
@Component
public class CommonUtil {
    private static ICheckService checkService;
    private static SysService sysService;
    private static EcService ecService;
    private static IBannerService bannerService;
    
    @Autowired
	public void setCheckService(ICheckService checkService) {
		CommonUtil.checkService = checkService;
	}
    @Autowired
	public void setSysService(SysService sysService) {
		CommonUtil.sysService = sysService;
	}
    @Autowired
	public void setEcService(EcService ecService) {
		CommonUtil.ecService = ecService;
	}
    @Autowired
	public void setBannerService(IBannerService bannerService) {
		CommonUtil.bannerService = bannerService;
	}
    
    @Autowired
	private ISaleCenterService saleCenterService;

	/**
     * 组装消费通知数据
     */
    public  CYTPojo findCYTPojo(String orid,String url) {
        if(url==null || url.length()<1){
            url=WebContant.GetKeyValue("CenterUrl");
        }
        CYTPojo pojo = new CYTPojo();
        pojo.isCYT = false;
        try {
            if (orid.matches("^\\d{8}888\\d{6}")
                    || orid.matches("^\\d{8}999\\d{6}")|| orid.matches("^\\d{8}000\\d{6}")||orid.matches("^\\d{8}236\\d{6}")) {// 核销
                pojo.orid = orid;

//                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
//                        javax.xml.rpc.Service service = null;
//                        java.net.URL endpointURL = new java.net.URL("http://"
//                                + url
//                                + "/services/centersaleService?wsdl");
//                        CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
//                                endpointURL, service);
//                        ssl.setMaintainSession(true);
//                        ResultBean cano = ssl
//                                .getT_order(pojo.orid, 0l);
                    	
                    	ResultBean cano  = saleCenterService.getT_order(pojo.orid, 0l);
                        if (cano.getRowsCount() > 0) {
                            TOrder to = new TOrder();
                            to.setId(new TOrderId(pojo.orid, Long
                                    .parseLong(cano.getResult(0, "iscenicid"))));
                            to.setOrid(pojo.orid);
                            to.setDdzt(cano.getResult(0, "ddzt"));
                            to.setUsid(cano.getResult(0, "usid"));
                            pojo.iscenicid = to.getId().getIscenicid();
                            pojo.cytdto = checkService.findCYTDto(orid,
                                    pojo.iscenicid,url);
                            pojo.cytdto.torder = to;
                            //cano = ssl.getT_orderlist(pojo.orid, to.getId().getIscenicid());
                            cano = saleCenterService.getT_orderlist(pojo.orid, to.getId().getIscenicid());
                            if (cano.getRowsCount() > 0) {
                                TOrderlist tol = new TOrderlist();
                                tol.setNumb(Long.parseLong(cano.getResult(0,"numb")));
                                pojo.cytdto.tOrderlist = tol;
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                } else {
//                    pojo.cytdto = checkService.findCYTDto(orid,url);
//                }
                pojo.orid = orid;
                pojo.posid = pojo.cytdto.sysparv5.getPmvc();
                if(pojo.cytdto.Galcompanyinfotab != null) {
                	pojo.oto_code = pojo.cytdto.Galcompanyinfotab.getSzcompanycode();
                }
                pojo.iscenicid = pojo.cytdto.torder.getId().getIscenicid();
                pojo.orderuserid = pojo.cytdto.torder.getUsid();
                pojo.orderQuantity = String.valueOf(pojo.cytdto.tOrderlist
                        .getNumb());
                pojo.useQuantity = pojo.orderQuantity;
                Long maxid = bannerService.getSequenceId("consume_sequence");
                pojo.sign = pojo.oto_code + maxid.toString();
                pojo.consumedate = Tools.getDayTimes();
                pojo.version = "2.0.0";
                pojo.isCYT = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pojo;
    }

    /**
     * 获取打印数据
     */
    public static String getprintmessage(Stssalesvouchertab s,Stssoldtickettab stsv,
                                         Edmtickettypetab edticket, Esbticketstationtab esbticketstation, boolean isreprint) {
        StringBuffer dayin = new StringBuffer();
        Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) bannerService
                .get(Stssalesvoucherdetailstab.class,
                        new StssalesvoucherdetailstabId(stsv.getId()
                                .getIsalesvoucherdetailsid(), s.getId()
                                .getIsalesvoucherid(), s.getId()
                                .getIticketstationid()));
        List zdetaillist = bannerService
                .find(" from Stscomticketsalesdetailstab where id.iticketstationid="
                        + s.getId().getIticketstationid()
                        + " and id.isalesvoucherid="
                        + s.getId().getIsalesvoucherid()
                        + " and id.isalesvoucherdetailsid= "
                        + sd.getId().getIsalesvoucherdetailsid());
        Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) bannerService
                .get(Edmcrowdkindpricetab.class, sd.getIcrowdkindpriceid());
        Edpcrowdkindtab crowd = (Edpcrowdkindtab) bannerService.get(Edpcrowdkindtab.class, edmcrowdkindpricetab.getIcrowdkindid());

        List printlist = bannerService
                .find(" from Priceprintmanager where id.icrowdkindpriceid="
                        + sd.getIcrowdkindpriceid() + " order by ordernum");
//        List<Sysparv5> syslist = sysService.find(" from Sysparv5 where id.pmky='PRSZ'");
        List<Sysparv5> syslist = sysService.findSysparByPmky("PRSZ", "");
        if (printlist != null && printlist.size() > 0) {
            for (int m = 0; m < printlist.size(); m++) {
                Priceprintmanager p = (Priceprintmanager) printlist.get(m);

                for (int n = 0; n < syslist.size(); n++) {
                    Sysparv5 sv5 = syslist.get(n);

                    if (sv5.getId().getPmcd().equals(p.getId().getPrintno())) {
                        p.setSzprintno(sv5.getPmva());

                        p.setPrinttype(sv5.getIsa().toString());

                        if (sv5.getIsa() == 0) {
                            p.setNote(sv5.getPmvb());
                        }

                    }
                }
            }
        } else {
//            List aprintlist = sysService
//                    .find(" from Printticketmanage p where p.id.iscenicid="
//                            + s.getIscenicid() + " and p.id.ibusinessid="
//                            + s.getIbusinessid() + "  order by p.ordernum ");
            List<Printticketmanage> aprintlist = (List<Printticketmanage>) sysService.getPrintticketmanageList(s.getIscenicid(), s.getIbusinessid());
            if (aprintlist != null && aprintlist.size() > 0) {
                for (int m = 0; m < aprintlist.size(); m++) {
                    Printticketmanage ap = (Printticketmanage) aprintlist
                            .get(m);
                    Priceprintmanager p = new Priceprintmanager();
                    PriceprintmanagerId pid = new PriceprintmanagerId();
                    pid.setIcrowdkindpriceid(sd.getIcrowdkindpriceid());
                    pid.setPrintno(ap.getId().getPrintno());
                    p.setId(pid);
                    p.setNoteprinttype("1");//打不打印标题
                    for (int n = 0; n < syslist.size(); n++) {
                        Sysparv5 sv5 = (Sysparv5) syslist.get(n);
                        if (sv5.getId().getPmcd()
                                .equals(ap.getId().getPrintno())) {
                            p.setSzprintno(sv5.getPmva());
                            if (sv5.getIsb() != null && sv5.getIsb() == 1) {
                                p.setPrinttype("2");//0:自定义 1:系统设置 2:系统扩展字段
                            } else {
                                p.setPrinttype(sv5.getIsa().toString());//0:自定义 1:系统设置 2:系统扩展字段
                            }
                            if (!p.getPrinttype().equals("1")) {
                                p.setNote(sv5.getPmvb());
                            }
                        }
                    }
                    printlist.add(p);
                }
            }
        }
        if (printlist != null && printlist.size() > 0) {

            for (int m = 0; m < printlist.size(); m++) {
                Priceprintmanager p = (Priceprintmanager) printlist.get(m);

                if (p.getId().getPrintno().equals("01")) {
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                    } else {
                        dayin.append(edticket.getSztickettypename() + "(" + crowd.getSzcrowdkindname() + ")");
                        if (isreprint) {
                            dayin.append("(重打印)");
                        }
                    }
                    dayin.append(";");
                }
                if (p.getId().getPrintno().equals("02")) {
                    // 人次
                    if (p.getNoteprinttype().equals("1")) {
                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                    } else {
                        dayin.append(stsv.getIplayerperticket() + "人");
                    }
                    dayin.append(";");
                }

                if (p.getId().getPrintno().equals("03")) {
                    // 价格
                    if (p.getNoteprinttype().equals("1")) {
                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                    } else {
                        dayin.append("￥" + sd.getMactualsaleprice());
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage3");

                }
                if (p.getId().getPrintno().equals("04")) {
                    // 流水号
                    if (p.getNoteprinttype().equals("1")) {
                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                    } else {
                        dayin.append(esbticketstation.getSzstationcode()
                                + stsv.getIserialnum());
                        //计算打印顺序号
                        //计算总票数
                        List stsvlist = bannerService
                                .find(" from Stssoldtickettab  where id.isalesvoucherid="
                                        + stsv.getId().getIsalesvoucherid() + " and id.iticketstationid="
                                        + stsv.getId().getIticketstationid() + " order by id.isalesvoucherdetailsid ,id.szsoldticketid");
                        //计算当前编号
                        Stssoldtickettab sold = (Stssoldtickettab) stsvlist.get(0);
                        Long num = stsv.getIserialnum().longValue() - sold.getIserialnum().longValue() + 1;
                        dayin.append("  (" + num + "/" + stsvlist.size() + ")");
                    }
                    dayin.append(";");
                }

                if (p.getId().getPrintno().equals("05")) {
                    // 有效期
                    if (p.getNoteprinttype().equals("1")) {
                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                    } else {
                        dayin.append(stsv.getDtstartdate().replace("-", ".")
                                + "-" + stsv.getDtenddate().replace("-", "."));
                    }
                    dayin.append(";");
                }

                if (p.getId().getPrintno().equals("06")) {
                    // 竹筏时间
                    for (int n = 0; n < zdetaillist.size(); n++) {

                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getTripid() > 0) {

                            if (p.getNoteprinttype().equals("1")) {

                                dayin.append(p.getSzprintno() + ":");
                            }
                            if (p.getPrinttype().equals("0")) {
                                dayin.append(p.getNote());
                            } else {
                                dayin.append(zstd.getDtstartdate()
                                        .substring(0, 16).replace("-", "."));
                            }
                            dayin.append(";");

                        }

                    }
                }

                if (p.getId().getPrintno().equals("07")) {
                    // 竹筏趟次
                    for (int n = 0; n < zdetaillist.size(); n++) {

                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        Trip t = (Trip) bannerService.get(Trip.class,
                                zstd.getTripid());
                        if (zstd.getTripid() > 0) {
                            if (p.getNoteprinttype().equals("1")) {
                                dayin.append(p.getSzprintno() + ":");
                            }
                            if (p.getPrinttype().equals("0")) {
                                dayin.append(p.getNote());
                            } else {
                                dayin.append(t.getTripname());
                            }
                            dayin.append(";");

                        }

                    }
                    System.out.println("getprintmessage7");
                }

                if (p.getId().getPrintno().equals("08")) {
                    // 导游名称
                    if (!StringUtils.isBlank(s.getDyusid()) && !"null".equalsIgnoreCase(s.getDyusid())
                            && !s.getDyusid().equals("daoyou")) {
                        if (p.getNoteprinttype().equals("1")) {

                            dayin.append(p.getSzprintno() + ":");
                        }
                        Custom c = ecService.getCustomByUserId(s.getDyusid());
                        if (p.getPrinttype().equals("0")) {
                            dayin.append(p.getNote());
                        } else {
                            dayin.append(c.getLname());
                        }
                        dayin.append(";");
                    }
                    System.out.println("getprintmessage8");
                }

                if (p.getId().getPrintno().equals("09")) {
                    // 旅行社名称
                    if (s.getUsid() != null && !s.getUsid().equals("")
                            && !s.getUsid().equals("guset")) {
                        Custom c = ecService.getCustomByUserId(s.getUsid());
                        if (!c.getLgtp().equals("01")) {
                            if (p.getNoteprinttype().equals("1")) {

                                dayin.append(p.getSzprintno() + ":");
                            }
                            if (p.getPrinttype().equals("0")) {
                                dayin.append(p.getNote());
                            } else {
                                dayin.append(c.getCorpname());
                            }
                            dayin.append(";");
                        }
                    }
                    System.out.println("getprintmessage9");
                }

                if (p.getId().getPrintno().equals("10")) {
                    // 客源地
                    if (s.getIregionalid() != null
                            && !s.getIregionalid().equals("")) {
                        if (p.getNoteprinttype().equals("1")) {

                            dayin.append(p.getSzprintno() + ":");
                        }
                        Galsourceregiontab g = (Galsourceregiontab) sysService
                                .getSourceregionById(s.getIregionalid());
                        if (p.getPrinttype().equals("0")) {
                            dayin.append(p.getNote());
                        } else {
                            dayin.append(g.getSzinnername());
                        }
                        dayin.append(";");
                    }
                    System.out.println("getprintmessage10");
                }

                if (p.getId().getPrintno().equals("11")) {
                    // 订单号
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                    } else {
                        dayin.append(s.getSzsalesvoucherno());
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage11");
                }

                if (p.getId().getPrintno().equals("12")) {
                    // 游客类型
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                        dayin.append(";");
                    } else {
                        Edpcrowdkindtab eb = (Edpcrowdkindtab) bannerService
                                .get(Edpcrowdkindtab.class,
                                        stsv.getIcrowdkindid());
                        dayin.append(eb.getSzcrowdkindname());
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage12");
                }

                if (p.getId().getPrintno().equals("13")) {
                    // 有效票类
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());
                        dayin.append(";");
                    } else {
                        if (edticket.getBycategorytype().equals("0010")) {
                            // 套票

                            for (int n = 0; n < zdetaillist.size(); n++) {
                                Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                        .get(n);
                                Edmtickettypetab zticket = (Edmtickettypetab) bannerService
                                        .get(Edmtickettypetab.class,
                                                zstd.getIztickettypeid());
                                if (n > 0) {
                                    dayin.append(",");
                                }
                                dayin.append(zticket.getSztickettypename());
                            }
                            dayin.append(";");
                        } else {
                            dayin.append(edticket.getSztickettypename());
                            dayin.append(";");
                        }
                    }
                    System.out.println("getprintmessage13");
                }
                if (p.getId().getPrintno().equals("14")) {
                    // 证件号码
                    if (stsv.getMyzj() != null && !stsv.getMyzj().equals("")) {
                        if (p.getNoteprinttype().equals("1")) {

                            dayin.append(p.getSzprintno() + ":");
                        }
                        if (p.getPrinttype().equals("0")) {
                            dayin.append(p.getNote());

                        } else {
                            String myzj = stsv.getMyzj();
                            if (myzj.length() > 8) {
                                myzj = myzj.substring(0, myzj.length() - 8) + "*" + myzj.substring(myzj.length() - 4, myzj.length());
                            }
                            dayin.append(myzj + ";姓名:" + stsv.getName1());
                        }
                        dayin.append(";");
                    }
                    System.out.println("getprintmessage14");
                }

                if (p.getId().getPrintno().equals("15")) {
                    // 总金额

                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());

                    } else {
                        dayin.append("￥"
                                + (sd.getMactualsaleprice() * stsv
                                .getIplayerperticket()));
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage15");

                }
                if (p.getId().getPrintno().equals("16")) {
                    // 打印时间
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());

                    } else {
                        dayin.append(Tools.getNowString().replace("-", "."));
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage16");

                }
                if (p.getId().getPrintno().equals("17")) {
                    // 节目
                    for (int n = 0; n < zdetaillist.size(); n++) {
                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getIvenueseatsid() > 0) {
                            Venueprogram vp = (Venueprogram) bannerService
                                    .get(Venueprogram.class,
                                            zstd.getIvenueseatsid());
                            if (p.getNoteprinttype().equals("1")) {

                                dayin.append(p.getSzprintno() + ":");
                            }
                            if (p.getPrinttype().equals("0")) {
                                dayin.append(p.getNote());

                            } else {
                                dayin.append(vp.getSzprogramname());
                            }
                            dayin.append(";");
                        }
                    }
                    System.out.println("getprintmessage17");
                }
                if (p.getId().getPrintno().equals("18")) {
                    // 场馆
                    for (int n = 0; n < zdetaillist.size(); n++) {
                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getIvenueareaid() > 0) {
                            Venuearea va = (Venuearea) bannerService.get(
                                    Venuearea.class, zstd.getIvenueareaid());

                            if (p.getNoteprinttype().equals("1")) {

                                dayin.append(p.getSzprintno() + ":");
                            }
                            if (p.getPrinttype().equals("0")) {
                                dayin.append(p.getNote());

                            } else {
                                dayin.append(va.getIvenueareaname());
                            }
                            dayin.append(";");
                        }
                    }
                    System.out.println("getprintmessage18");
                }
                if (p.getId().getPrintno().equals("19")) {
                    // 座位
                    for (int n = 0; n < zdetaillist.size(); n++) {
                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getIvenueseatsid() > 0) {
                            if (p.getNoteprinttype().equals("1")) {

                                dayin.append(p.getSzprintno() + ":");
                            }
                            Venuearea va = (Venuearea) bannerService.get(
                                    Venuearea.class, zstd.getIvenueareaid());
                            List seatlist = bannerService
                                    .find("from Seatsaletab where id.isalesvoucherid="
                                            + s.getId().getIsalesvoucherid()
                                            + " and id.iticketstationid="
                                            + s.getId().getIticketstationid()
                                            + " and id.isalesvoucherdetailsid="
                                            + stsv.getId()
                                            .getIsalesvoucherdetailsid()
                                            + " and szsoldticketid="
                                            + stsv.getId().getSzsoldticketid()
                                            + " and id.icomticketsalesdetailsid="
                                            + zstd.getId()
                                            .getIcomticketsalesdetailsid());
                            String seatname = "";
                            for (int im = 0; im < seatlist.size(); im++) {
                                Seatsaletab so = (Seatsaletab) seatlist.get(im);
                                List stlist = bannerService
                                        .find(" from Venueseats where ivenueseatsid="
                                                + so.getIseatid()
                                                + " and ivenueareaid="
                                                + so.getIvenueareaid()
                                                + " and ivenueid="
                                                + so.getIvenueid());
                                Venueseats vst = (Venueseats) stlist.get(0);
                                seatname = seatname + vst.getSzvenueseatsname();

                            }
                            if (p.getPrinttype().equals("0")) {
                                dayin.append(p.getNote());

                            } else {
                                dayin.append(va.getIvenueareaname() + seatname);
                            }
                            dayin.append(";");
                        }

                    }
                    System.out.println("getprintmessage19");
                }
                if (p.getId().getPrintno().equals("20")) {
                    // 挂牌价

                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    dayin.append(edmcrowdkindpricetab.getListingprice());
                    dayin.append(";");
                    System.out.println("getprintmessage19");
                }
                if (p.getId().getPrintno().equals("21")) {
                    // 售票员
                    Esfemployeetab esfemployeetab = (Esfemployeetab) sysService
                            .getEsfemployeeInfo( s.getIhandler());

                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());

                    } else {
                        dayin.append(esfemployeetab.getSzemployeename());
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage21");
                }
                if (p.getId().getPrintno().equals("22")) {
                    // 售票点
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    if (p.getPrinttype().equals("0")) {
                        dayin.append(p.getNote());

                    } else {
                        dayin.append(esbticketstation.getSzstationname());
                    }
                    dayin.append(";");
                    System.out.println("getprintmessage22");
                }
                if (p.getId().getPrintno().equals("23")) {
                }
                if (p.getId().getPrintno().equals("24")) {
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }
                    dayin.append("￥" + (edmcrowdkindpricetab.getListingprice() * stsv
                            .getIplayerperticket()));
                    dayin.append(";");
                }
                //lizhaodong 新增入园时段字段返回 开始 2017-08-04
                if (p.getId().getPrintno().equals("28")) {
                    if (p.getNoteprinttype().equals("1")) {

                        dayin.append(p.getSzprintno() + ":");
                    }

                    for (int n = 0; n < zdetaillist.size(); n++) {

                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        dayin.append((zstd.getTimeStart()==null?"":zstd.getTimeStart())+"~"+(zstd.getTimeEnd()==null?"":zstd.getTimeEnd()));
                        dayin.append(";");
                    }

                }

                //lizhaodong 新增入园时段结束

                if (Integer.parseInt(p.getId().getPrintno()) > 24 && Integer.parseInt(p.getId().getPrintno())!=28 ) { //lizhaodong 添加了一个printno 28的控制
                    if (p.getPrinttype().equals("2")) {
                        //系统扩展
                        String sql = "";
                        if (p.getNote().startsWith("price")) {
                            sql = "select PRINTCODENAME('" + edmcrowdkindpricetab.getIcrowdkindpriceid() + "','" + p.getId().getPrintno() + "') as note from dual";
                        } else {
                            sql = "select PRINTCODENAME('" + s.getSzsalesvoucherno() + "','" + p.getId().getPrintno() + "') as note from dual";
                        }
                        List list = null;
                        try {
                            list = bannerService.findBySqlToMap(sql);
                            if (list != null && !list.isEmpty()) {
                                Map map = (Map) list.get(0);
                                if (map != null && map.get("NOTE") != null) {
                                    if (!p.getSzprintno().startsWith("i")) {
                                        dayin.append(p.getSzprintno() + ":");
                                    }
                                    dayin.append(map.get("NOTE").toString());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (p.getNoteprinttype().equals("1")) {
                            dayin.append(p.getSzprintno() + ":");
                        }
                        dayin.append(p.getNote());
                    }
                    dayin.append(";");
                }

            }
            System.out.println(stsv.getSzticketprintno());
            System.out.println("dayin=" + dayin.toString());
        }
        return dayin.toString();
    }

    /**
     * 获取副券打印数据
     */
    public static String getfprintmessage(List sprintlist, Stssalesvouchertab s,
                                          Stssoldtickettab stsv, Edmtickettypetab edticket,
                                          Esbticketstationtab esbticketstation) {

        Stssalesvoucherdetailstab sd = (Stssalesvoucherdetailstab) bannerService
                .get(Stssalesvoucherdetailstab.class,
                        new StssalesvoucherdetailstabId(stsv.getId()
                                .getIsalesvoucherdetailsid(), s.getId()
                                .getIsalesvoucherid(), s.getId()
                                .getIticketstationid()));
        List zdetaillist = bannerService
                .find(" from Stscomticketsalesdetailstab where id.iticketstationid="
                        + s.getId().getIticketstationid()
                        + " and id.isalesvoucherid="
                        + s.getId().getIsalesvoucherid()
                        + " and id.isalesvoucherdetailsid= "
                        + sd.getId().getIsalesvoucherdetailsid());
        Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) bannerService
                .get(Edmcrowdkindpricetab.class,
                        sd.getIcrowdkindpriceid());
        Edpcrowdkindtab crowd = (Edpcrowdkindtab) bannerService.get(Edpcrowdkindtab.class, edmcrowdkindpricetab.getIcrowdkindid());
        StringBuffer fujian = new StringBuffer();

        if (sprintlist != null && sprintlist.size() > 0) {
            fujian.append(stsv.getSzticketprintno() + ";");
            System.out.println("fujian111111=" + fujian.toString());
            for (int m = 0; m < sprintlist.size(); m++) {

                Soderollprintmanage p = (Soderollprintmanage) sprintlist.get(m);
                if (p.getId().getPrintno().equals("01")) {

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(edticket.getSztickettypename() + "(" + crowd.getSzcrowdkindname() + ")");
                    fujian.append(";");
                } else if (p.getId().getPrintno().equals("02")) {
                    // 人次

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(stsv.getIplayerperticket() + "人");
                    fujian.append(";");
                } else if (p.getId().getPrintno().equals("03")) {
                    // 价格

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append("￥" + sd.getMactualsaleprice());
                    fujian.append(";");
                } else if (p.getId().getPrintno().equals("04")) {
                    // 流水号

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(esbticketstation.getSzstationcode()
                            + stsv.getIserialnum());
                    fujian.append(";");
                } else if (p.getId().getPrintno().equals("05")) {
                    // 有效期

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(stsv.getDtstartdate().replace("-", ".") + "-"
                            + stsv.getDtenddate().replace("-", "."));
                    fujian.append(";");
                } else if (p.getId().getPrintno().equals("06")) {
                    // 竹筏时间
                    for (int n = 0; n < zdetaillist.size(); n++) {

                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getTripid() > 0) {

                            fujian.append(p.getSzprintno() + ":");

                            fujian.append(zstd.getDtstartdate()
                                    .substring(0, 16).replace("-", "."));
                            fujian.append(";");

                        }

                    }
                } else if (p.getId().getPrintno().equals("07")) {
                    // 竹筏趟次
                    for (int n = 0; n < zdetaillist.size(); n++) {

                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        Trip t = (Trip) bannerService.get(Trip.class,
                                zstd.getTripid());
                        if (zstd.getTripid() > 0) {

                            fujian.append(p.getSzprintno() + ":");

                            fujian.append(t.getTripname());
                            fujian.append(";");

                        }

                    }

                } else if (p.getId().getPrintno().equals("08")) {
                    // 导游名称
                    if (s.getDyusid() != null && !s.getDyusid().equals("")
                            && !s.getDyusid().equals("daoyou")) {

                        fujian.append(p.getSzprintno() + ":");

                        Custom c = ecService.getCustomByUserId(s.getDyusid());
                        fujian.append(c.getLname());
                        fujian.append(";");
                    }
                } else if (p.getId().getPrintno().equals("09")) {
                    // 旅行社名称
                    if (s.getUsid() != null && !s.getUsid().equals("")
                            && !s.getUsid().equals("guset")) {
                        Custom c = ecService.getCustomByUserId(s.getUsid());
                        if (!c.getLgtp().equals("01")) {

                            fujian.append(p.getSzprintno() + ":");

                            fujian.append(c.getCorpname());
                            fujian.append(";");
                        }
                    }
                }

                if (p.getId().getPrintno().equals("10")) {
                    // 客源地
                    if (s.getIregionalid() != null
                            && !s.getIregionalid().equals("")) {

                        fujian.append(p.getSzprintno() + ":");

                        Galsourceregiontab g = (Galsourceregiontab) sysService
                                .getSourceregionById(s.getIregionalid());
                        fujian.append(g.getSzinnername());
                        fujian.append(";");
                    }
                }

                if (p.getId().getPrintno().equals("11")) {
                    // 订单号

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(s.getSzsalesvoucherno());
                    fujian.append(";");
                }

                if (p.getId().getPrintno().equals("12")) {
                    // 游客类型

                    fujian.append(p.getSzprintno() + ":");
                    Edpcrowdkindtab eb = (Edpcrowdkindtab) bannerService.get(
                            Edpcrowdkindtab.class, stsv.getIcrowdkindid());
                    fujian.append(eb.getSzcrowdkindname());

                    fujian.append(";");
                }

                if (p.getId().getPrintno().equals("13")) {
                    // 有效票类

                    fujian.append(p.getSzprintno() + ":");

                    if (edticket.getBycategorytype().equals("0010")) {
                        // 套票

                        for (int n = 0; n < zdetaillist.size(); n++) {
                            Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                    .get(n);
                            Edmtickettypetab zticket = (Edmtickettypetab) bannerService
                                    .get(Edmtickettypetab.class,
                                            zstd.getIztickettypeid());
                            if (n > 0) {
                                fujian.append(",");
                            }
                            fujian.append(zticket.getSztickettypename());
                        }
                        fujian.append(";");
                    } else {
                        fujian.append(edticket.getSztickettypename());
                        fujian.append(";");
                    }
                }
                if (p.getId().getPrintno().equals("14")) {
                    // 证件号码
                    if (stsv.getMyzj() != null && !stsv.getMyzj().equals("")) {

                        fujian.append(p.getSzprintno() + ":");

                        fujian.append(stsv.getMyzj());
                        fujian.append(";");
                    }
                }

                if (p.getId().getPrintno().equals("15")) {
                    // 总金额

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append("￥"
                            + (sd.getMactualsaleprice() * stsv
                            .getIplayerperticket()));
                    fujian.append(";");

                }
                if (p.getId().getPrintno().equals("16")) {
                    // 打印时间

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(Tools.getNowString().replace("-", "."));
                    fujian.append(";");

                }
                if (p.getId().getPrintno().equals("17")) {
                    // 节目
                    for (int n = 0; n < zdetaillist.size(); n++) {
                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getIvenueseatsid() > 0) {
                            Venueprogram vp = (Venueprogram) bannerService
                                    .get(Venueprogram.class,
                                            zstd.getIvenueseatsid());

                            fujian.append(p.getSzprintno() + ":");

                            fujian.append(vp.getSzprogramname());
                            fujian.append(";");
                        }
                    }
                }
                if (p.getId().getPrintno().equals("18")) {
                    // 场馆
                    for (int n = 0; n < zdetaillist.size(); n++) {
                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getIvenueareaid() > 0) {
                            Venuearea va = (Venuearea) bannerService.get(
                                    Venuearea.class, zstd.getIvenueareaid());

                            fujian.append(p.getSzprintno() + ":");

                            fujian.append(va.getIvenueareaname());
                            fujian.append(";");
                        }
                    }
                }
                if (p.getId().getPrintno().equals("19")) {
                    // 座位
                    for (int n = 0; n < zdetaillist.size(); n++) {
                        Stscomticketsalesdetailstab zstd = (Stscomticketsalesdetailstab) zdetaillist
                                .get(n);
                        if (zstd.getIvenueseatsid() > 0) {

                            fujian.append(p.getSzprintno() + ":");

                            Venuearea va = (Venuearea) bannerService.get(
                                    Venuearea.class, zstd.getIvenueareaid());
                            List seatlist = bannerService
                                    .find("from Seatsaletab where id.isalesvoucherid="
                                            + s.getId().getIsalesvoucherid()
                                            + " and id.iticketstationid="
                                            + s.getId().getIticketstationid()
                                            + " and id.isalesvoucherdetailsid="
                                            + stsv.getId()
                                            .getIsalesvoucherdetailsid()
                                            + " and szsoldticketid="
                                            + stsv.getId().getSzsoldticketid()
                                            + " and id.icomticketsalesdetailsid="
                                            + zstd.getId()
                                            .getIcomticketsalesdetailsid());
                            String seatname = "";
                            for (int im = 0; im < seatlist.size(); im++) {
                                Seatsaletab so = (Seatsaletab) seatlist.get(im);
                                List stlist = bannerService
                                        .find(" from Venueseats where ivenueseatsid="
                                                + so.getIseatid()
                                                + " and ivenueareaid="
                                                + so.getIvenueareaid()
                                                + " and ivenueid="
                                                + so.getIvenueid());
                                Venueseats vst = (Venueseats) stlist.get(0);
                                seatname = seatname + vst.getSzvenueseatsname();

                            }

                            fujian.append(va.getIvenueareaname() + seatname);
                            fujian.append(";");
                        }
                    }
                }
                if (p.getId().getPrintno().equals("20")) {
                    // 挂牌价


                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(edmcrowdkindpricetab.getListingprice());
                    fujian.append(";");
                }
                if (p.getId().getPrintno().equals("21")) {
                    // 售票员
                    Esfemployeetab esfemployeetab = (Esfemployeetab) sysService
                            .getEsfemployeeInfo(s.getIhandler());

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(esfemployeetab.getSzemployeename());
                    fujian.append(";");
                }
                if (p.getId().getPrintno().equals("22")) {
                    // 售票点

                    fujian.append(p.getSzprintno() + ":");

                    fujian.append(esbticketstation.getSzstationname());
                    fujian.append(";");
                }
                if (p.getId().getPrintno().equals("23")) {
                }
                if (p.getId().getPrintno().equals("24")) {

                    fujian.append("￥" + (edmcrowdkindpricetab.getListingprice() * stsv
                            .getIplayerperticket()));
                    fujian.append(";");
                }
                if (Integer.parseInt(p.getId().getPrintno()) > 24) {
                    fujian.append(p.getNote());
                    fujian.append(";");
                }

            }
        }
        System.out.println(stsv.getSzticketprintno());
        System.out.println("fujian=" + fujian.toString());
        return fujian.toString();
    }

    public static Custom xiazaiCustom(String usid,String url) {
        if(url==null || url.length()<1){
            url=WebContant.GetKeyValue("CenterUrl");
        }
        try {
            Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
			Object[] objects = client.invoke("getCustom", usid);
			ResultBean cano = (ResultBean) objects[0];
            if (cano.getRowsCount() > 0) {// 中心服务器有数据
                String[] names = cano.getColumnNames();
                List customlist = new ArrayList();
                for (int i = 0; i < cano.getRowsCount(); i++) {
                    Map map = new HashMap();
                    for (int j = 0; j < cano.getColumnCount(); j++) {
                        String value = cano.getResult(i, j);
                        map.put(names[j], value);
                    }
                    Custom ct = new Custom();
                    ct = BeanUtil.toBean(Custom.class, map);
                    Custom cb = ecService.getCustomByUserId( ct.getUsid());
                    if (cb == null) {
                        bannerService.save(ct);
                        return ct;
                    } else {
                        return cb;
                    }
                }
            }
            // System.out.println("有值");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 获取用户可用余额
     *
     * @param usid
     * @return
     */
    public static double getUserMoney(String usid) {
        double money = 0.0D;
        boolean hqyt = isHqyt();
        if(hqyt){
            String sql = "from ProviderCompany where usid = '"+usid+"' and type = '01' ";
            List list = bannerService.find(sql);
            if(list != null && !list.isEmpty()){
                ProviderCompany pc = (ProviderCompany) list.get(0);
                if(pc.getHqytId() != null && pc.getHqytId() != 0L){
                    HqytClient client = new HqytClient();
                    BalanceRequest request = new BalanceRequest();
                    request.setMerchantId(pc.getHqytId());
                    JSONBalance balance = client.searchBalance(request);
                    if(balance != null){
                        money = balance.getWithdrawable();
                    }
                }
            }
        }else{
            Vipbalance vipbalance = (Vipbalance) sysService.findVipbalanceByUsid(Long.valueOf(usid));
            if (vipbalance != null) {
                money = vipbalance.getPoint().doubleValue();
                double mont = getUserFrozenMoney(usid);
                money = MathUtil.subtract(money, mont);
            }
        }
        return money;
    }

    /**
     * 获取用户冻结金额
     *
     * @param usid 用户ID
     * @return
     */
    public static double getUserFrozenMoney(String usid) {
        double money = 0.0D;
        boolean hqyt = isHqyt();
        if(!hqyt){
            String hsql = "select new map(nvl(sum(mont),0) as mont) from Yfktoxj where (ddzt='97' or ddzt ='04') and usid = '" + usid + "' ";
            //TODO
            List list = sysService.find(hsql);
            if (list != null && !list.isEmpty()) {
                Map map = (Map) list.get(0);
                money = Double.parseDouble(map.get("mont").toString());
            }
        }
        return money;
    }

    /**
     * 读取后一笔的余额
     */
    public static float getUserMaxYfk(String usid) {

        String hql = " select new map( max(seq) as  cs ) From Useryfk   where usid =  '"
                + usid + "'";
        List<Map> list = sysService.find(hql);
        // 默认查询出来的list里存放的是一个Object数组，
        // 但是在这里list里存放的不再是默认的Object数组了，而是Map集合了
        int rc_cs = 0;
        if (list.size() > 0) {
            for (Iterator iterator2 = list.iterator(); iterator2.hasNext(); ) {
                HashMap map = (HashMap) iterator2.next();
                Integer cs = (Integer) map.get("cs");
                if (cs == null) {
                    cs = new Integer(0);
                } else {
                    rc_cs = cs;
                }
            }
        }
        hql = " select new map( vippoint as  vippoint ) From Useryfk   where seq =  " + rc_cs + "";
        // Session session = getSession() ;
        List<Map> list2 = null;
        double vippoint = 0.0f;

        try {
            list2 = bannerService.find(hql);
            // 默认查询出来的list里存放的是一个Object数组，
            // 但是在这里list里存放的不再是默认的Object数组了，而是Map集合了
            if (list2 != null &&list2.size() > 0) {
                for (Iterator iterator2 = list2.iterator(); iterator2.hasNext(); ) {
                    HashMap map = (HashMap) iterator2.next();
                    Double vp = (Double) map.get("vippoint");
                    if (vp == null) {
                        vp = new Double(0f);
                    } else {
                        vippoint = vp;
                    }
                }
            }

        } catch (Exception e) {
            vippoint = 0f;
        }
        return (float) vippoint;
    }

    /**
     * 获取版本号
     * @param type 01:主版本  02:售票软件版本  03:检票软件版本
     * @return
     */
    public static String getVerison(String type){
        String version = "";
        try{
            List<PMSVersion> pmsVersions =  sysService.find("from PMSVersion");
            if(pmsVersions != null && !pmsVersions.isEmpty()){
                PMSVersion pmsVersion = pmsVersions.get(0);
                if("01".equalsIgnoreCase(type)){
                    version = pmsVersion.getVersionId();
                }
                if("02".equalsIgnoreCase(type)){
                    version = pmsVersion.getSaleVer();
                }
                if("03".equalsIgnoreCase(type)){
                    version = pmsVersion.getCheckVer();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return version;
    }


    public static boolean isHqyt(){
    	Sysparv5 sys = sysService.findOne("HQYT","0001");
        if(sys != null && sys.getIsvalue() == 1L){
            return true;
        }
        return false;
    }

    public static String findPayUrl(){
        Sysparv5 sys = sysService.findOne("HQYT","0002");
        if(sys != null){
            return sys.getPmvb();
        }
        return "";
    }
    public static String findPayUrld(){
    	Sysparv5 sys = sysService.findOne("HQYT","0002");
        if(sys != null){
            return sys.getPmvd();
        }
        return "";
    }
    public static String findScanCodeUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0006");
        if(sys != null){
            return sys.getPmvb();
        }
        return "";
    }

    public static String findScanCodeUrl(String url2){
    	Sysparv5 sys = sysService.findOne("HQYT","0006");
        if(sys != null){
            String url = sys.getPmvb();						//全名地址
            String url1 = url.substring(0,url.indexOf("//")+2);  //地址前缀
            url = url.substring(url.indexOf("/")+2);
            String url3 = url.substring(url.indexOf("/"));		//地址后缀
            url = url1+url2+url3;
            return url;
        }
        return "";
    }

    public static String findWdsKey(){
    	Sysparv5 sys = sysService.findOne("HQYT","0005");
        if(sys != null){
            return sys.getPmvb();
        }
        return "";
    }

    public static String findWxReturnUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0003");
        if(sys != null){
            return sys.getPmvc();
        }
        return "";
    }

    public static String findWxReturnUrl(String url2){
    	Sysparv5 sys = sysService.findOne("HQYT","0003");
        if(sys != null){
            String url = sys.getPmvc();						//全名地址
            String url1 = url.substring(0,url.indexOf("//")+2);  //地址前缀
            url = url.substring(url.indexOf("/")+2);
            String url3 = url.substring(url.indexOf("/"));		//地址后缀
            url = url1+url2+url3;
            return url;
        }
        return "";
    }

    public static String findSmfReturnUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0003");
        if(sys != null){
            return sys.getPmvd();
        }
        return "";
    }

    public static String findSmfReturnUrl(String url2){
    	Sysparv5 sys = sysService.findOne("HQYT","0003");
        if(sys != null){
            String url = sys.getPmvd();						//全名地址
            String url1 = url.substring(0,url.indexOf("//")+2);  //地址前缀
            url = url.substring(url.indexOf("/")+2);
            String url3 = url.substring(url.indexOf("/"));		//地址后缀
            url = url1+url2+url3;
            return url;
        }
        return "";
    }

    public static String findReturnUrle(){
    	Sysparv5 sys = sysService.findOne("HQYT","0003");
        if(sys != null){
            return sys.getPmve();
        }
        return "";
    }


    public static String appPayURL(){
    	Sysparv5 sys = sysService.findOne("APP","0001");
        if(sys != null){
            return sys.getPmvb()+"|"+sys.getPmvc();
        }
        return null;
    }

    public static String findWxPayUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0003");
        if(sys != null){
            return sys.getPmvb();
        }
        return "";
    }

    public static String findQrUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0004");
        if(sys != null){
            return sys.getPmvb();
        }
        return "";
    }

    public static String findQrUrl(String url2){
    	Sysparv5 sys = sysService.findOne("HQYT","0004");
        if(sys != null){
            String url = sys.getPmvb();						//全名地址
            String url1 = url.substring(0,url.indexOf("//")+2);  //地址前缀
            url = url.substring(url.indexOf("/")+2);
            String url3 = url.substring(url.indexOf("/"));		//地址后缀
            url = url1+url2+url3;
            return url;
        }
        return "";
    }

    public static String findScanRefuUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0006");
        if(sys != null){
            return sys.getPmvc();
        }
        return "";
    }

    public static String findScanRefuUrl(String url2){
    	Sysparv5 sys = sysService.findOne("HQYT","0006");
        if(sys != null){
            String url = sys.getPmvc();						//全名地址
            String url1 = url.substring(0,url.indexOf("//")+2);  //地址前缀
            url = url.substring(url.indexOf("/")+2);
            String url3 = url.substring(url.indexOf("/"));		//地址后缀
            url = url1+url2+url3;
            return url;
        }
        return "";
    }

    public static String findReturnUrl(){
    	Sysparv5 sys = sysService.findOne("HQYT","0002");
        if(sys != null){
            return sys.getPmvc();
        }
        return "";
    }

    public static String findReturnUrl(String url2){
    	Sysparv5 sys = sysService.findOne("HQYT","0002");
        if(sys != null){
            String url = sys.getPmvc();						//全名地址
            String url1 = url.substring(0,url.indexOf("//")+2);  //地址前缀
            url = url.substring(url.indexOf("/")+2);
            String url3 = url.substring(url.indexOf("/"));		//地址后缀
            url = url1+url2+url3;
            return url;
        }
        return "";
    }

    public static Map<String,String> findMerchant(){
    	Sysparv5 sys = sysService.findOne("HQYT","0001");
        if(sys != null){
            Map<String,String> map = new HashMap<String, String>();
            map.put("merchantId",sys.getPmvb());
            map.put("key",sys.getPmvc());
            return map;
        }
        return null;
    }

    /**
     * 获取商户ID
     * @return
     */
    public static String getMerchantId(){
        Map<String,String> map = findMerchant();
        if(map != null){
            return map.get("merchantId");
        }
        return null;
    }

    public static ProviderCompany findHqytId(String type,String usid,Long iscenicid){
        List list = new ArrayList();
        if(type.equalsIgnoreCase("01")) {
            Custom custom = ecService.getCustomByUserId(usid);
            list = bannerService.find("from ProviderCompany where usid in ('" + usid + "','" + custom.getSusid() + "') and type = '01' ");
        }else{
            list = bannerService.find("from ProviderCompany where iscenicid = "+iscenicid+" and type = '02' ");
        }
        if(list != null && !list.isEmpty()){
            ProviderCompany pc = (ProviderCompany) list.get(0);
            return pc;
        }
        return null;
    }

    /**
     * 订单过期剩余时间
     * @param orderCreateDate 订单创建时间
     * @return
     */
    public static Long getOrderExpiringTime(Date orderCreateDate){
        return (30-1)*60 - (new Date().getTime() - orderCreateDate.getTime())/1000;//30分钟倒计时(注：不-1会出现30:59秒的超时情况)
    }
}
