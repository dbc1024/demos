package com.ectrip.ticket.cyt.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.MathUtil;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.FilmbookModel;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.cyt.model.request.CancelOrderRequestBody.OrderInfo.VisitPerson.Person;
import com.ectrip.ticket.cyt.model.request.CheckPmsDataRequestBody;
import com.ectrip.ticket.cyt.model.request.CreateComplexOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.CreateOTOOrderRequestBody;
import com.ectrip.ticket.cyt.model.request.PushOrderRequestBody;
import com.ectrip.ticket.cyt.service.iservice.ICytOrderDataService;
import com.ectrip.ticket.cyt.service.iservice.ICytOrderService;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.venuemarketing.Programprdmanager;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroltab;
import com.ectrip.ticket.model.venuemarketing.Tripprdsaletab;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venuearea;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.model.venuemarketing.Venueseats;

/**
 * 订单处理服务类
 * @author huhaopeng
 */
@Service
public class cytOrderService extends GenericService implements ICytOrderService {

//    private ITicketService ticketService;
	@Autowired
    private ICytOrderDataService cytOrderDataService;
	@Autowired
    private EcService ecService;
    /*private IOrderService orderService;
    private ICustomInfoService customInfoService;
    private IFilmService filmService;
    private IOrderSaveTrans filmsaveTrans;*/

    /**
     * 校验PMS订单数据
     * @param body
     * @return
     */
    public String checkPmsData(CheckPmsDataRequestBody body){
        CheckPmsDataRequestBody.PmsDatas pmsDatas = body.getPmsDatas();
        StringBuffer message = new StringBuffer();
        if(pmsDatas != null){
            List<CheckPmsDataRequestBody.PmsDatas.PmsData> datas = pmsDatas.getPmsData();
            if(datas != null && !datas.isEmpty()){
                String date = Tools.getDate(Tools.getDays(), -1);
                for (CheckPmsDataRequestBody.PmsDatas.PmsData data : datas){
                    String type = data.getType();
                    if(type.equalsIgnoreCase("CREATE")){//原始数据
                        Map<String,Object> createMap = cytOrderDataService.findCreateOrderData(date);
                        Long quantity = (Long) createMap.get("quantity");
                        Long money = MathUtil.amplify2long((Double) createMap.get("money"), 2);
                        if(quantity.longValue() != Long.parseLong(data.getQuantity())){
                            message.append("下单数量不一致 ");
                        }
                        if(money.longValue() != Long.parseLong(data.getMoney())){
                            message.append("下单金额不一致 ");
                        }
                    }else if(type.equalsIgnoreCase("CONSUME")){//消费数据
                        Map<String,Object> consumeMap = cytOrderDataService.findConsumeOrderData(date);
                        Long quantity = (Long) consumeMap.get("quantity");
                        Long money = MathUtil.amplify2long((Double) consumeMap.get("money"), 2);
                        if(quantity.longValue() != Long.parseLong(data.getQuantity())){
                            message.append("消费数量不一致 ");
                        }
                        if(money.longValue() != Long.parseLong(data.getMoney())){
                            message.append("消费金额不一致 ");
                        }
                    }else if(type.equalsIgnoreCase("REFUND")){//退订数据
                        Map<String,Object> refundMap = cytOrderDataService.findRefundOrderData(date);
                        Long quantity = (Long) refundMap.get("quantity");
                        Long money = MathUtil.amplify2long((Double) refundMap.get("money"), 2);
                        if(quantity.longValue() != Long.parseLong(data.getQuantity())){
                            message.append("退订数量不一致 ");
                        }
                        if(money.longValue() != Long.parseLong(data.getMoney())){
                            message.append("退订金额不一致 ");
                        }
                    }
                }
            }
        }
        return message.toString();
    }

    /**
     *
     * Describe:创建剧场订单
     * @author:chenxinhao
     * @param body
     * @param custom_userid
     * @param maxNostr
     * @return
     * @throws Exception
     * return:String
     * Date:2015-7-21
     */
    public String createComplexOrder(CreateComplexOrderRequestBody body,
                                     String custom_userid, String maxNostr) throws Exception {

        String orid = body.getOrderInfo().getCytOrderId();
        try {
            if (orid == null || "".equals(orid)) {
                String token = body.getOrderInfo().getToken();
                orid = this.cytOrderDataService.getMaxNo((token == null || ""
                        .equals(token)) ? "999" : "888");
                body.getOrderInfo().setTicketPassword(
                        getRandom(body.getOrderInfo().getContactPerson()
                                .getCredentials(), body.getOrderInfo()
                                .getContactPerson().getCredentialsType()));
            }
            // ==================begin 封装数据===========================
            // String orid = this.genericService.getMaxNo(maxNostr);//自己生成的订单ID
            Custom custom = ecService.getCustomByUserId(custom_userid);
            String otaOrderId = body.getOrderInfo().getOtaOrderId();// 对方传过来的订单Id
            String token = body.getOrderInfo().getToken();// 淘宝令牌信息
            String resourceId = body.getOrderInfo().getProduct()
                    .getResourceId();// 售票ID
            String visitDate = body.getOrderInfo().getProduct().getVisitDate();// 首次浏览时间

            String contactPersonName = body.getOrderInfo().getContactPerson()
                    .getName();// 导游或游客名称
            String mobile = body.getOrderInfo().getContactPerson().getMobile();// 电话

            String Credentials = "";// 证件号码
            String CredentialsType = "";// 取票人证件类型

            String orderQuantity = body.getOrderInfo().getOrderQuantity();// 票数
            String sellPrice = body.getOrderInfo().getProduct().getSellPrice();// 票价格
            String orderPrice = body.getOrderInfo().getOrderPrice();// 订单金额
            String tripid = body.getOrderInfo().getProductInfo().getTimeId();//场次ID
            String ivenueareaid = body.getOrderInfo().getProductInfo().getAreaId();//区域ID

            if(tripid == null || "".equals(tripid) || ivenueareaid == null || "".equals(ivenueareaid)){
                throw new RuntimeException("剧场数据不全!");
            }
            // 售价表
            Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) this.cytOrderDataService
                    .get(Edmcrowdkindpricetab.class, new Long(resourceId));
            // 产品表
            Edmtickettypetab edmtickettypetab = (Edmtickettypetab) this.cytOrderDataService
                    .get(Edmtickettypetab.class,
                            new Long(edmcrowdkindpricetab.getItickettypeid()));
            Hotelprovider hp = (Hotelprovider) this.cytOrderDataService.get(Hotelprovider.class, edmtickettypetab.getIscenicid());
            Programprdmanager program = (Programprdmanager) this.cytOrderDataService
                    .get(Programprdmanager.class,
                            new Long(edmcrowdkindpricetab.getItickettypeid()));
            if(program == null){
                throw new RuntimeException("该产品未绑定节目");
            }
            Sysparv5 sys = (Sysparv5) this.cytOrderDataService.get(Sysparv5.class,new Sysparv5Id("COMM","0003"));
            if(sys == null || sys.getIsvalue() == 1){
                if (edmtickettypetab.getByisuse() != 1
                        || (hp.getInoteger3() == 1
                        && "00".equals(edmtickettypetab
                        .getBymaketicketway()) && edmtickettypetab
                        .getByusage() != 1)) {
                    throw new RuntimeException("票张不满足要求!");
                }
            }

            if (new BigDecimal(edmcrowdkindpricetab.getMactualsaleprice()
                    .doubleValue()).multiply(new BigDecimal(100))
                    .setScale(0, BigDecimal.ROUND_HALF_EVEN).doubleValue() != new BigDecimal(
                    sellPrice).doubleValue()
                    || new BigDecimal(edmcrowdkindpricetab
                    .getMactualsaleprice().doubleValue())
                    .multiply(new BigDecimal(orderQuantity))
                    .multiply(new BigDecimal(100))
                    .setScale(0, BigDecimal.ROUND_HALF_EVEN)
                    .doubleValue() != new BigDecimal(orderPrice)
                    .doubleValue()) {
                throw new RuntimeException("票种价格不一致!");
            }
            // 服务商
            Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.cytOrderDataService
                    .get(Esbscenicareatab.class,
                            new Long(edmtickettypetab.getIscenicid()));
            Map checkMap = checkFilm(tripid, program.getIprogramid().toString(), ivenueareaid, visitDate, edmtickettypetab.getItickettypeid().toString());
            if("false".equals(checkMap.get("status").toString())){
                throw new RuntimeException(checkMap.get("msg").toString());
            }
            String venueid = checkMap.get("venueid").toString();//场馆ID
            String tripprdcontroldetailid = checkMap.get("tripprdcontroldetailid").toString();//排班明细ID
            //填充FilmbookModel
            List<FilmbookModel> orderPojolist = new ArrayList<FilmbookModel>();
            List choose = this.ecService.autoChooseRandomSeat(visitDate, Long.parseLong(venueid), Long.parseLong(ivenueareaid), Long.parseLong(tripid), Long.parseLong(orderQuantity));
            if(choose!=null && !choose.isEmpty() && choose.size()==Integer.parseInt(orderQuantity)){
                for(int x=0;x<choose.size();x++){//随机选座
                    Map seatinfo = (Map) choose.get(x);
                    FilmbookModel filmmodel = new FilmbookModel();
                    Venueseats seat = (Venueseats) this.cytOrderDataService.get(Venueseats.class, Long.parseLong(seatinfo.get("seq").toString()));
                    Tripprdcontroldetailtab detail = (Tripprdcontroldetailtab) this.cytOrderDataService.get(Tripprdcontroldetailtab.class, Long.parseLong(tripprdcontroldetailid));
                    Long productid = ecService.getFilmProduct(seat.getIvenueareaid(), detail.getItripprdcontrolid());
                    Tripprdcontroltab control = (Tripprdcontroltab) this.cytOrderDataService.get(Tripprdcontroltab.class, detail.getItripprdcontrolid());
                    Venueprogram prom = (Venueprogram) this.cytOrderDataService.get(Venueprogram.class, control.getIprogramid());
                    Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.cytOrderDataService.get(Edmcrowdkindpricetab.class, Long.parseLong(resourceId));
                    Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.cytOrderDataService.get(Edpcrowdkindtab.class, price.getIcrowdkindid());
                    Venue venu = (Venue) this.cytOrderDataService.get(Venue.class, seat.getIvenueid());
                    filmmodel.setIticketid(productid);
                    filmmodel.setIcrowkindid(crowdkind.getIcrowdkindid());
                    filmmodel.setIprogramid(control.getIprogramid());
                    filmmodel.setItripid(detail.getItripid());
                    filmmodel.setSzprogramname(prom.getSzprogramname());
                    filmmodel.setIcrowdkindpriceid(Long.parseLong(resourceId));
                    filmmodel.setMactualsaleprice(price.getMactualsaleprice());
                    filmmodel.setSeatid(seat.getIvenueseatsid());
                    filmmodel.setIvenueid(seat.getIvenueid());
                    filmmodel.setIvenueareaid(seat.getIvenueareaid());
                    filmmodel.setVenuname(venu.getVenueidname());
                    filmmodel.setNumb(1);
                    filmmodel.setTourdate(visitDate);
                    filmmodel.setCrowkindname(crowdkind.getSzcrowdkindname());
                    filmmodel.setRownum(seat.getIrowserialnum().intValue());
                    filmmodel.setColnum(seat.getIcolumnserialnum().intValue());
                    filmmodel.setSzvenueseatsname(seat.getSzvenueseatsname());
                    filmmodel.setSeatseq(Integer.parseInt(seatinfo.get("ivenueseatsid").toString()));
                    filmmodel.setIscenicid(prom.getIscenicid());
                    orderPojolist.add(filmmodel);
                }
            }else{
                throw new RuntimeException("剧场剩余座位不足，预定失败");
            }
            List<TRealname> list = null;

            Credentials = body.getOrderInfo().getContactPerson()
                    .getCredentials();// 证件号码
            CredentialsType = body.getOrderInfo().getContactPerson()
                    .getCredentialsType();// 取票人证件类型

            LprPojo lproPojo = new LprPojo();
            // lproPojo.setOrnote9(orderId);//将对方传进来的ID暂存在这里
            lproPojo.setPassword(body.getOrderInfo().getTicketPassword());// 取票密码
            lproPojo.setRzti(visitDate);
            lproPojo.setDaoyou(contactPersonName);
            lproPojo.setMobile(mobile);
            lproPojo.setSzregionalid(esbscenicareatab.getSzregionalid()
                    .toString());// 客源地
            lproPojo.setIscenicid(edmtickettypetab.getIscenicid().toString());
            lproPojo.setZjhm(Credentials);// 证件号码

            // ==================end 封装数据===========================

            // 是否是优惠产品单 int isjl = 0不是 isjl = 1是; note=0不明白
            // Map ordermap = ticketService.combinationOrder(custom, orderPojo,
            // note, lprlist, isjl);
            // String note = otaOrderId+":"+token;//把OTA订单号暂时存放在这个字段里面
            // OTAID:token
            OrderCombinDTO dto = ecService.combinationOrder(orid, orderPojolist, custom, lproPojo);
            System.out.println("开始保存订单信息");
            boolean orderSave = ecService.SaveOrder(dto);
            try {
                MOrder morder = (MOrder) cytOrderDataService.get(MOrder.class, orid);
                morder.setNotei(token);
                morder.setNoteh(otaOrderId);
                cytOrderDataService.update(morder);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("订单保存完毕");
            return orid;
        } catch (Exception e) {
            System.out.println("===========创建订单出现异常===========");
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 创建订单
     * Describe:
     * @author:zhangwubin
     * @param body CreateOrderForBeforePaySyncRequestBody 创建订单ResponseBody
     * @param custom Custom 分销商对象
     * @param maxNostr String 订单ID日期后面的3位标示符
     * @return String 系统生成的订单号
     * Date:2014-3-12
     * @throws Exception
     */
    public String addOrder(CreateOTOOrderRequestBody body, String custom_userid,String maxNostr) throws Exception{
        String orid = body.getOrderInfo().getCytOrderId();
        try{
            if(orid == null || "".equals(orid)){
                String token = body.getOrderInfo().getToken();
                orid = this.cytOrderDataService.getMaxNo((token==null || "".equals(token))?"999":"888");
                body.getOrderInfo().setTicketPassword(getRandom(body.getOrderInfo().getContactPerson().getCredentials(),body.getOrderInfo().getContactPerson().getCredentialsType()));
            }
            //==================begin 封装数据===========================
            //String orid = this.genericService.getMaxNo(maxNostr);//自己生成的订单ID
            Custom custom = ecService.getCustomByUserId(custom_userid);
            String otaOrderId = body.getOrderInfo().getOtaOrderId();//对方传过来的订单Id
            String token = body.getOrderInfo().getToken();//淘宝令牌信息
            String resourceId = body.getOrderInfo().getProduct().getResourceId();//售票ID
            String visitDate = body.getOrderInfo().getProduct().getVisitDate();//首次浏览时间

            String contactPersonName = body.getOrderInfo().getContactPerson().getName();//导游或游客名称
            String mobile = body.getOrderInfo().getContactPerson().getMobile();//电话

            String Credentials = "";//证件号码
            String CredentialsType = "";//取票人证件类型

            String orderQuantity = body.getOrderInfo().getOrderQuantity();//票数
            String sellPrice = body.getOrderInfo().getProduct().getSellPrice();//票价格
            String orderPrice = body.getOrderInfo().getOrderPrice();//订单金额


            //售价表
            Edmcrowdkindpricetab edmcrowdkindpricetab= (Edmcrowdkindpricetab) this.cytOrderDataService.get(Edmcrowdkindpricetab.class, new Long(resourceId));
            //产品表
            Edmtickettypetab edmtickettypetab = (Edmtickettypetab)this.cytOrderDataService.get(Edmtickettypetab.class, new Long(edmcrowdkindpricetab.getItickettypeid()));
            Hotelprovider hp = (Hotelprovider) this.cytOrderDataService.get(Hotelprovider.class,edmtickettypetab.getIscenicid());
            Sysparv5 sys = (Sysparv5) this.cytOrderDataService.get(Sysparv5.class,new Sysparv5Id("COMM","0003"));
            if(sys == null || sys.getIsvalue() == 1){
                if (edmtickettypetab.getByisuse() != 1
                        || (hp.getInoteger3() == 1
                        && "00".equals(edmtickettypetab
                        .getBymaketicketway()) && edmtickettypetab
                        .getByusage() != 1)) {
                    throw new RuntimeException("票张不满足要求!");
                }
            }
            System.out.println(new BigDecimal(edmcrowdkindpricetab.getMactualsaleprice().doubleValue())
                    .multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_EVEN).doubleValue()+"<>"+Double.parseDouble(sellPrice));
            System.out.println(new BigDecimal(edmcrowdkindpricetab.getMactualsaleprice().doubleValue())
                    .multiply(new BigDecimal(orderQuantity)).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_EVEN).doubleValue()
                    +"<>"+ Double.parseDouble(orderPrice));

            if(new BigDecimal(edmcrowdkindpricetab.getMactualsaleprice().doubleValue())
                    .multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_EVEN).doubleValue()
                    != new BigDecimal(sellPrice).doubleValue() ||
                    new BigDecimal(edmcrowdkindpricetab.getMactualsaleprice().doubleValue())
                            .multiply(new BigDecimal(orderQuantity)).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_EVEN).doubleValue()
                            != new BigDecimal(orderPrice).doubleValue()){
                throw new RuntimeException("票种价格不一致!");
            }
            //服务商
            Esbscenicareatab esbscenicareatab = (Esbscenicareatab)this.cytOrderDataService.get(Esbscenicareatab.class, new Long(edmtickettypetab.getIscenicid()));

            List<OrderPojo> orderPojolist = new ArrayList<OrderPojo>();//票集合
            OrderPojo orderPojo = new OrderPojo();
            orderPojo.setItickettypeid(edmcrowdkindpricetab.getItickettypeid().toString());
            orderPojo.setIscenicid(edmtickettypetab.getIscenicid().toString());
            orderPojo.setTicketid(edmcrowdkindpricetab.getItickettypeid().toString());

            List<OrderPojo> pricelist = new ArrayList<OrderPojo>();
            OrderPojo op1 = new OrderPojo();
            op1.setIcrowdkindid(edmcrowdkindpricetab.getIcrowdkindid().toString());
            op1.setIcrowdkindpriceid(Integer.parseInt(edmcrowdkindpricetab.getIcrowdkindpriceid().toString()));
            double p = Integer.parseInt(sellPrice)/100;//分转成元
            op1.setMactualsaleprice(p);//价格
            op1.setNumb(orderQuantity);//票数
            pricelist.add(op1);
            orderPojo.setPrice(pricelist);
            orderPojo.setTourdate(visitDate);//给票设置首次浏览日期
            orderPojo.setIssale(Integer.parseInt(edmtickettypetab.getIssale().toString()));
            orderPojolist.add(orderPojo);

            //是否实名制
            List<CreateOTOOrderRequestBody.OrderInfo.VisitPerson.Person> person = body.getOrderInfo().getVisitPerson().getPerson();
            List<TRealname> list = null;

            Credentials = body.getOrderInfo().getContactPerson().getCredentials();//证件号码
            CredentialsType = body.getOrderInfo().getContactPerson().getCredentialsType();//取票人证件类型
            // 是否实名制: 0-不实名制， 1-实名制
            if(edmcrowdkindpricetab.getIpeoplenumrange() == 1){
                if (person != null &&person.size() > 0) {
                    list = new ArrayList<TRealname>();
                    for(int i = 0; i < person.size(); i++){
                        TRealname trealname = new TRealname();
                        trealname.setCname(person.get(i).getName());
                        trealname.setIcrowdkindid(edmcrowdkindpricetab.getIcrowdkindid());
                        trealname.setIdcard(person.get(i).getCredentials());
                        trealname.setIscenicid(esbscenicareatab.getIscenicid());
                        if("ID_CARD".equals(person.get(i).getCredentialsType())){
                            trealname.setIschild(1L);
                            trealname.setZjtp("01");//身份证
                        } else {
                            trealname.setIschild(0L);
                            trealname.setZjtp("02");//02导游证
                        }
                        trealname.setItickettypeid(edmcrowdkindpricetab.getItickettypeid());
                        trealname.setMbnumber("");
                        trealname.setOrid(orid);
                        list.add(trealname);
                    }
                }
            }

            List<LprPojo> lprlist = new ArrayList<LprPojo>();//领票人信息集合
            LprPojo lproPojo = new LprPojo();
            //lproPojo.setOrnote9(orderId);//将对方传进来的ID暂存在这里
            lproPojo.setPassword(body.getOrderInfo().getTicketPassword());//取票密码
            lproPojo.setRzti(visitDate);
            lproPojo.setDaoyou(contactPersonName);
            lproPojo.setMobile(mobile);
            lproPojo.setSzregionalid(esbscenicareatab.getSzregionalid().toString());//客源地
            lproPojo.setIscenicid(edmtickettypetab.getIscenicid().toString());
            lproPojo.setZjhm(Credentials);//证件号码
            lprlist.add(lproPojo);

            bookTicket(orderPojolist,lprlist, custom);
            //==================end 封装数据===========================

            // 是否是优惠产品单 int isjl = 0不是   isjl = 1是;  note=0不明白
            //Map ordermap = ticketService.combinationOrder(custom, orderPojo, note, lprlist, isjl);
            //			String note = otaOrderId+":"+token;//把OTA订单号暂时存放在这个字段里面 OTAID:token
            Map ordermap = ecService.combinationOrder(custom, orderPojolist, null, lprlist, 0);
            MOrder morder = (MOrder) ordermap.get("morder");
            morder.setNotei(token);
            morder.setNoteh(otaOrderId);
            morder.setOrdersource("fx");//添加订单来源"fx"(分销平台)
            
            
            List<TOrder> torders = (List<TOrder>) ordermap.get("torder");
            List<TOrderlist> torderlist = (List<TOrderlist>) ordermap.get("torderlist");
            List<YOrder> yorder = (List<YOrder>) ordermap.get("yorder");
            List<YOrderlist> yorderlist = (List<YOrderlist>) ordermap.get("yorderlist");
            //验证库存信息
            List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
            if(torderlist != null && !torderlist.isEmpty()){
                for(TOrderlist tl : torderlist){
                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setOrid(orid);
                    stockOrderInfo.setProviderId(tl.getId().getIscenicid());
                    stockOrderInfo.setProductId(tl.getItickettypeid());
                    stockOrderInfo.setPriceId(tl.getIcrowdkindpriceid());
                    stockOrderInfo.setUsid(morder.getUsid());
                    stockOrderInfo.setStockDate(tl.getDtstartdate());
                    stockOrderInfo.setNumb(tl.getNumb());
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
            }
            System.out.println("开始保存订单信息");
            boolean orderSave = cytOrderDataService.saveOrder(orid, morder, yorder, yorderlist, torders, torderlist, list);
            System.out.println("订单保存完毕");
            return orid;
        }catch(Exception e){
            System.out.println("===========创建订单出现异常===========");
            e.printStackTrace();
            throw e;
        }

    }
    public void bookTicket(List<OrderPojo> orderPojolist,List<LprPojo> lprlist,Custom custom) throws Exception {
        // 此map中存放的是3个 map 第一个是所有的基础票 第二个是所有的套票 第三个是合并了所有同日期同产品的套票
        Map map = (Map) ecService.splitAllTicketList(orderPojolist);
        // 获取基础票
        List<OrderPojo> basetickets = (List<OrderPojo>) map.get("baseticket");
        // 合并的套票
        List<OrderPojo> mergekickets = (List<OrderPojo>) map.get("mergekicket");
        // 合并的套票含趟次的
        List<OrderPojo> samenesttickets = (List<OrderPojo>) map.get("samenestticket");
        // 所有套票
        List<OrderPojo> nesttickets = (List<OrderPojo>) map.get("nestticket");
        basetickets.addAll(mergekickets);

        List<OrderPojo> tripticket = null;

        samenesttickets.addAll((List<OrderPojo>) map.get("baseticket"));
        //调用方法参数修改，以前传的是价格分组，现在需要传usid 2015-02-05 任先平
        Map mymap = ecService.packagingScheme(samenesttickets,custom.getIbusinessid(),custom.getUsid(),lprlist);
        ecService.fillLprInfo(lprlist);
    }


    public Map<String,String> checkFilm(String tripid,String iprogramid,String ivenueareaid,String date,String productid){
        Map<String,String> map = new HashMap<String,String>();
        Edmtickettypetab ticket = (Edmtickettypetab) this.cytOrderDataService.get(Edmtickettypetab.class, Long.parseLong(productid));
        if(!"0004".equals(ticket.getBycategorytype())){
            map.put("status", "false");
            map.put("msg", "非剧场票产品不可走该预定通道");
            return map;
        }
        Venuearea venuearea = (Venuearea) this.cytOrderDataService.get(Venuearea.class, Long.parseLong(ivenueareaid));
        if(venuearea == null){
            map.put("status", "false");
            map.put("msg", "剧场区域不存在，请检查区域数据是否正确");
            return map;
        }
        Venue venue = (Venue) this.cytOrderDataService.get(Venue.class, venuearea.getIvenueid());
        if(venue == null){
            map.put("status", "false");
            map.put("msg", "剧场不存在，请检查数据是否正确");
            return map;
        }
        Venueprogram gram=(Venueprogram) this.cytOrderDataService.get(Venueprogram.class, Long.parseLong(iprogramid));
        if(gram == null){
            map.put("status", "false");
            map.put("msg", "剧场节目不存在，请检查数据是否正确");
            return map;
        }
        Tripprdcontroltab tripprdcontroltab = this.cytOrderDataService.getTripprdcontroltab(Long.parseLong(iprogramid), venue.getIvenueid(), date);
        if(tripprdcontroltab == null){
            map.put("status", "false");
            map.put("msg", "剧场排班信息不存在，请检查数据是否正确");
            return map;
        }
        Tripprdcontroldetailtab tripprdcontroldetailtab = this.cytOrderDataService.getTripprdcontroldetailtab(tripprdcontroltab.getItripprdcontrolid(), Long.parseLong(tripid));
        if(tripprdcontroldetailtab == null){
            map.put("status", "false");
            map.put("msg", "剧场排班明细信息不存在，请检查数据是否正确");
            return map;
        }
        Programprdmanager programprdmanager = this.cytOrderDataService.getProgramprdmanager(Long.parseLong(iprogramid), Long.parseLong(productid));
        if(programprdmanager == null){
            map.put("status", "false");
            map.put("msg", "剧场节目无绑定改产品数据，不可销售");
            return map;
        }
        Tripprdsaletab tripprdsaletab = this.cytOrderDataService.getTripprdsaletab(tripprdcontroltab.getItripprdcontrolid(), Long.parseLong(productid), Long.parseLong(ivenueareaid));
        if(tripprdsaletab == null){
            map.put("status", "false");
            map.put("msg", "剧场节目区域产品无绑定数据，不可销售");
            return map;
        }
        map.put("status", "true");
        map.put("venueid", venue.getIvenueid().toString());
        map.put("tripprdcontrolid", tripprdcontroltab.getItripprdcontrolid().toString());
        map.put("tripprdcontroldetailid", tripprdcontroldetailtab.getItripprdcontroldetailid().toString());
        return map;
    }

    /**
     *
     * Describe:退订
     * @author:zhangwubin
     * @param orid
     * @param iscenicid
     * @param custom
     * @throws Exception
     */
    public boolean cancelOrder(String orid, String iscenicid,Custom custom,Double tpsxmonth,Long icompanyinfoid) throws Exception{
        boolean result = true;
        List mlist = this.cytOrderDataService.getMOrderList(orid);
        MOrder morder = (MOrder) this.cytOrderDataService.get(MOrder.class, orid);
        BeanUtils.populate(morder, (Map) mlist.get(0));
        String[] orids = new String[2];
        List<TOrderlist> torderlists = cytOrderDataService.getTOrderList(orid, Long.parseLong(iscenicid));
        try {
            Edmtickettypetab ticket = (Edmtickettypetab) cytOrderDataService.get(Edmtickettypetab.class, torderlists.get(0).getItickettypeid());
            if("0004".equals(ticket.getBycategorytype())){
                torderlists.get(0).setNnumb(torderlists.get(0).getNumb());
                String maxNoStr = getRefundOridFeature(orid.substring(8,11));
                String neworid = this.cytOrderDataService.getMaxNo(maxNoStr);
                TOrder torder = (TOrder) cytOrderDataService.get(TOrder.class,new TOrderId(orid, Long.parseLong(iscenicid)));
                Map returnmap = cytOrderDataService.saveeditseat(custom, morder, torder, torderlists, neworid, tpsxmonth);
                result = (Boolean) returnmap.get("result");
                if(result == false){
                    throw new RuntimeException(returnmap.get("msg").toString());
                }
            }else{
                torderlists = cytOrderDataService.getTOrderlists(orid, Long.parseLong(iscenicid));
                String maxNoStr = getRefundOridFeature(orid.substring(8,11));
                orids[0] = "27";
                orids[1] = this.cytOrderDataService.getMaxNo(maxNoStr);
                Map returnmap = cytOrderDataService.editOrderCenter(torderlists, null, orids, orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), custom.getUsid(),tpsxmonth,icompanyinfoid);
                result = (Boolean) returnmap.get("result");
            }
        } catch (Exception e) {
            //Auto-generated catch block
            result = false;
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("=================cancelOrder结束====================");
        return result;
    }

    /**
     *
     * Describe:退票
     * @author:zhangwubin
     * @param refundrulelist
     * @return
     * return:boolean
     * Date:2014-3-17
     * @throws java.text.ParseException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    public synchronized  boolean refund(String orid, int refundquantity,double tdsx){
        boolean result = false;
        Long providerid = null;//供应商ID
        TOrder torder = (TOrder) cytOrderDataService.getTOrderByOrid(orid);
        String isid = torder.getId().getIscenicid().toString();
        Custom custom = new Custom();
        custom.setUsid(torder.getUsid());
        custom.setIbusinessid(torder.getIbusinessid());
        try{
            if(refundquantity == 0){//全部退票
                System.out.println("======================全退====================");
                result = cancelOrder(orid, isid, custom,tdsx,providerid);//全退
            } else {
                //部分退订
                System.out.println("======================部分退订====================");
                result = cancelOrder(orid, isid, custom,tdsx,providerid,refundquantity);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;

    }

    /**
     *
     * Describe:部分退订
     * @author:zhangwubin
     * @param orid
     * @param custom
     * @param iscenicid
     * @param tpsxmonth
     * @param icompanyinfoid
     * @param torderlists
     * @return
     * @throws IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     * return:boolean
     * Date:2014-3-31
     */
    public boolean cancelOrder(String orid, String iscenicid,Custom custom,Double tpsxmonth,Long icompanyinfoid,int refundquantity) throws IllegalAccessException, InvocationTargetException{
        boolean flag = true;
        MOrder morder = (MOrder) this.cytOrderDataService.get(MOrder.class, orid);
        Custom orderuser = (Custom) this.cytOrderDataService.get(Custom.class, morder.getZfusid());
        List<TOrderlist> torderlists1 = cytOrderDataService.getTOrderList(orid,Long.parseLong(iscenicid));
        TOrderlist temp = new TOrderlist();
        org.apache.commons.beanutils.BeanUtils.copyProperties(temp, torderlists1.get(0));
        Edmtickettypetab ticket = (Edmtickettypetab) this.cytOrderDataService.get(Edmtickettypetab.class, temp.getItickettypeid());
        if("0004".equals(ticket.getBycategorytype())){
            try {
                torderlists1.get(0).setNnumb(Long.valueOf(refundquantity));
                String maxNoStr = getRefundOridFeature(orid.substring(8,11));
                String neworid = this.cytOrderDataService.getMaxNo(maxNoStr);
                TOrder torder = (TOrder) cytOrderDataService.get(TOrder.class,new TOrderId(orid, Long.parseLong(iscenicid)));
                Map returnmap = cytOrderDataService.saveeditseat(custom, morder, torder, torderlists1, neworid, tpsxmonth);
                flag = (Boolean) returnmap.get("result");
            } catch (Exception e) {
                e.printStackTrace();
                flag = false;
                throw new RuntimeException(e);
            }
        }else{
            List<TOrderlist> torderlists = new ArrayList<TOrderlist>();
            torderlists.add(temp);
            torderlists.get(0).setNumb(torderlists.get(0).getNumb() - refundquantity);//原来的票数 - 退票的数量
            torderlists.get(0).setOrderlistid(torderlists.get(0).getId().getOrderlistid().toString());
            torderlists.get(0).setOrid(torderlists.get(0).getId().getOrid());
            torderlists.get(0).setIscenicid(torderlists.get(0).getId().getIscenicid().toString());

            // returnmap 键result代表验证状态,false表示不通过,true表示通过.
            // result值为false时,errtp值表示错误类型.
            // errtp:0代表日控制不足，即票已售完,1代表生产量不足，也可以认为票数量不满足当前预定的数量.当状态为0、1时，键errlist对应的值记录着错误的详细信息
            // errtp:2代表用户剩余预付款不足以支付当前订单修改以后要支付的金额,3代表订单已经超过可修改时间
            // 4代表订单状态为不可修改状态,5代表订单无修改 6代表信誉度超过预定趟次积分为正数的排7:积分不足8:
            // errlist中对象属性errtp:0代表停排，1数量不足，2已售完，3暂时不可销售

            // 验证修改过后的订单是否满足各种可修改的条件
            Map returnmap = cytOrderDataService.validateOrderInfo(orid, torderlists, null, morder.getZfusid(), morder.getStdt(), custom.getIbusinessid().toString(), iscenicid,icompanyinfoid);
            Boolean result = (Boolean) returnmap.get("result");
            flag = result;
            if (result.booleanValue()) {
                try {
                    String maxNoStr = getRefundOridFeature(orid.substring(8,11));
                    String[] orids = new String[2];
                    orids[0] = this.cytOrderDataService.getMaxNo(maxNoStr);
                    orids[1] = this.cytOrderDataService.getMaxNo(maxNoStr);
                    returnmap = cytOrderDataService.editOrderCenter(torderlists, null, orids, orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), custom.getUsid(),tpsxmonth,icompanyinfoid);
                    flag = (Boolean) returnmap.get("result");
                } catch (Exception e) {
                    e.printStackTrace();
                    flag = false;
                    throw new RuntimeException(e);
                }
            }
        }
        return flag;
    }
    /**
     *
     * Describe:同步订单
     * @author:zhangwubin
     * @param pofbpsrb
     * return:boolean
     * Date:2014-3-27
     * @throws java.lang.reflect.InvocationTargetException
     * @throws IllegalAccessException
     */
    public boolean synchronOrder(PushOrderRequestBody pofbpsrb) {
        boolean flag = true;
        String orid = pofbpsrb.getOrderInfo().getPartnerOrderId();//订单ID
        //取票人信息
        PushOrderRequestBody.OrderInfo.ContactPerson contactPerson = pofbpsrb.getOrderInfo().getContactPerson();
        //游客信息
        PushOrderRequestBody.OrderInfo.VisitPerson visitPerson = pofbpsrb.getOrderInfo().getVisitPerson();
        try{
            TOrder torder ;
            MOrder morder;
            TOrderlist torderlist;
            String startdate = pofbpsrb.getOrderInfo().getStartDate(),
                    enddate = pofbpsrb.getOrderInfo().getEndDate();
            System.out.println("startdate:"+startdate+",enddate:"+enddate);

            if(startdate != null && !"".equals(startdate)){
                morder = (MOrder) cytOrderDataService.get(MOrder.class, orid);
                torder = (TOrder) cytOrderDataService.find("from TOrder t where t.id.orid=?",new String[]{orid}).get(0);
                torderlist = (TOrderlist) cytOrderDataService.find("from TOrderlist t where t.id.orid=?",new String[]{orid}).get(0);
                Edmtickettypetab ticket = (Edmtickettypetab) cytOrderDataService.get(Edmtickettypetab.class, torderlist.getItickettypeid());
                if(!"0004".equals(ticket.getBycategorytype())){
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = df.parse(startdate);
                    Date d3 = df.parse(torder.getDtstartdate());
                    Date d2 = df.parse(Tools.getDays());
                    Esbscenicareatab esbscenicareatab = (Esbscenicareatab)cytOrderDataService
                            .get(Esbscenicareatab.class, torder.getId().getIscenicid());
                    //				boolean istaobao = morder.getNotei() != null && !"".equals(morder.getNotei());//是否是淘宝订单
                    boolean istaobao = true;//现在不止是淘宝订单，也能修改截止日期
                    if (istaobao && df.parse(enddate).before(d1)) {
                        System.out.println("日期判断1:"+df.parse(enddate).before(d1));
                        flag = false;
                    } else if (d1.before(d2)) {
                        System.out.println("日期判断1:"+d1.before(d2));
                        flag = false;
                    } else if
                        //				((d3.before(d2) || d3.equals(d2))
                            ((d3.before(d2))
                                    && esbscenicareatab.getIcanmodify() == 0) {
                        System.out.println("日期判断3:");
                        flag = false;
                    } else{
                        System.out.println("进行日期修改更新");
                        try{
                            cytOrderDataService.updateProductDate(istaobao?0:1,orid, startdate, enddate, torder.getId().getIscenicid().toString(),torder.getIbusinessid().toString());
                        }catch(Exception e){
                            throw new RuntimeException(e.getMessage());
                        }
                    }
                }
            }
            if(flag){
                torder = (TOrder) cytOrderDataService.find("from TOrder t where t.id.orid=?",new String[]{orid}).get(0);
                YOrder yorder = (YOrder) cytOrderDataService.find("from YOrder y where y.id.orid=?",new String[]{orid}).get(0);
                YOrderlist yorderlist = (YOrderlist) cytOrderDataService.find("from YOrderlist y where y.id.orid=?", new String[]{orid}).get(0);
                //售价表
                Edmcrowdkindpricetab edmcrowdkindpricetab= (Edmcrowdkindpricetab) this.cytOrderDataService.get(Edmcrowdkindpricetab.class, yorderlist.getIcrowdkindpriceid());
                List<PushOrderRequestBody.OrderInfo.VisitPerson.Person> persons = visitPerson.getPerson();
                // 是否实名制: 0-不实名制， 1-实名制
                System.out.println("获取价格："+edmcrowdkindpricetab);
                if(edmcrowdkindpricetab.getIpeoplenumrange() == 0){
                    System.out.println("111111111");
                    if(contactPerson != null){
                        System.out.println("22222222");
                        String credentials = contactPerson.getCredentials();
                        String credentialsType = contactPerson.getCredentialsType();
                        boolean tflag= false;
                        if(credentials != null && !"".equals(credentials)){
                            yorder.setOrhm(credentials);//领票人证件号码
                            torder.setOrhm(credentials);//领票人证件号码
                            tflag = true;
                        }
                        if(contactPerson.getName() != null && !"".equals(contactPerson.getName())){
                            yorder.setOrnm(contactPerson.getName());//领票人姓名
                            torder.setOrnm(contactPerson.getName());//领票人姓名
                            tflag = true;
                        }
                        if(credentialsType != null && !"".equals(credentialsType)) {
                            yorder.setOrzj(credentialsType);//领票人证件类型
                            torder.setOrzj(credentialsType);//领票人证件类型
                            tflag = true;
                        }
                        if(contactPerson.getMobile() != null&& !"".equals(contactPerson.getMobile())){
                            yorder.setOrph(contactPerson.getMobile());//联系电话
                            torder.setOrph(contactPerson.getMobile());
                            tflag = true;
                        }
                        if(tflag){
                            cytOrderDataService.update(yorder);
                            cytOrderDataService.update(torder);
                        }
                    }
                } else{
                    flag = false;
                }
                //			else {
                //				//同步实名制的同时同步领票人姓名跟电话
                //				if(contactPerson != null){
                //					String name = contactPerson.getName();//领票人姓名
                //					String mobile = contactPerson.getMobile();//联系电话
                //					yorder.setOrnm(name);//领票人姓名
                //					yorder.setOrph(mobile);//联系电话
                //
                //					torder.setOrnm(name);//领票人姓名
                //					torder.setOrph(mobile);//联系电话
                //
                //					orderService.update(yorder);
                //					orderService.update(torder);
                //				}
                //
                //				if (persons != null && persons.size() > 0) {
                //					List<TRealname> list = new ArrayList<TRealname>();
                //					Long itickettypeid = yorderlist.getItickettypeid();
                //					Long iscenicid = yorder.getId().getIscenicid();
                //					Long icrowdkindid = yorderlist.getIcrowdkindid();
                //					for(int i = 0; i < persons.size(); i++){
                //						TRealname trealname = new TRealname();
                //						trealname.setCname(persons.get(i).getName());
                //						trealname.setIcrowdkindid(icrowdkindid);
                //						trealname.setIdcard(persons.get(i).getCredentials());
                //						trealname.setIscenicid(iscenicid);
                //						if("ID_CARD".equals(persons.get(i).getCredentialsType())){
                //							trealname.setIschild(1L);
                //							trealname.setZjtp("01");//身份证
                //						} else {
                //							trealname.setIschild(0L);
                //							trealname.setZjtp("02");//02导游证
                //						}
                //						trealname.setItickettypeid(itickettypeid);
                //						trealname.setMbnumber("");
                //						trealname.setOrid(orid);
                //						list.add(trealname);
                //					}
                //					orderService.deleteRealname(orid, itickettypeid, iscenicid, icrowdkindid);
                //					orderService.saveRealname(list, orid);
                //				}
                //			}
            }
        }catch(Exception e){
            System.out.println("error");
            e.printStackTrace();
            flag = false;
            throw new RuntimeException(e);
        }
        System.out.println("结束返回");
        return flag;
    }

    /**
     * 根据电话生成取票密码
     *
     * @param str
     * @return
     */
    public String getRandom(String credentials, String credentialsType) {
        if(!"ID_CARD".equals(credentialsType)){
            return "00000000";
        } else {
            int number = credentials.length();
            return credentials.substring(number - 12, number - 4);
        }

        //		if("".equals(str) || str == null)
        //			return "12345678";
        //		int left = 3;
        //		int right = str.length() - 1;
        //		int x = 0;
        //		int y = 0;
        //		StringBuffer sb = new StringBuffer();
        //		String[][] password = new String[][] {
        //				{ "93", "06", "66", "31", "48", "20", "59", "89", "67", "81" },
        //				{ "40", "68", "74", "88", "57", "01", "91", "96", "82", "05" },
        //				{ "16", "03", "21", "28", "50", "25", "90", "35", "07", "25" },
        //				{ "78", "44", "17", "57", "51", "02", "06", "40", "35", "31" },
        //				{ "86", "01", "67", "96", "50", "20", "95", "03", "11", "33" },
        //				{ "26", "20", "43", "90", "23", "42", "33", "73", "67", "77" },
        //				{ "53", "42", "48", "41", "34", "11", "10", "17", "18", "42" },
        //				{ "16", "86", "21", "90", "09", "07", "50", "99", "32", "40" },
        //				{ "03", "49", "81", "99", "20", "96", "72", "58", "00", "11" },
        //				{ "25", "47", "24", "22", "36", "66", "61", "53", "98", "83" } };
        //		if ((str.length() - 3) < 8) {
        //			if (str.length() < 8) {
        //				int num = 8 - str.length();
        //				StringBuffer add = new StringBuffer();
        //				for (int i = 0; i < num; i++) {
        //					add.append(i);
        //				}
        //				str += add.toString();
        //			}
        //			left = 0;
        //		}
        //		for (int i = 0; i < 4; i++) {
        //			x = str.charAt(left) - '0';
        //			y = str.charAt(right) - '0';
        //			sb.append(password[x][y]);
        //			left++;
        //			right--;
        //		}
        //		sb.append(orid.substring(orid.length() - 2));
        //		return sb.toString();
    }
    /**
     *
     * Describe:退订订单号由999改成998  888改成889（避免与畅游通冲突）
     * @author liujianwen
     * @param feature 订单特征
     * @return
     * return:String
     * Date:2015-5-4
     */
    private String getRefundOridFeature(String feature){
        if("999".equals(feature)) return "998";
        if("888".equals(feature)) return "889";
        return feature;
    }

}

