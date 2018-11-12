package com.ectrip.tourcard.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.MD5Util;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.app.AndroidStateCode;
import com.ectrip.ec.model.app.BaseModel;
import com.ectrip.ec.model.app.ObjectParse;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.OrderInfo;
import com.ectrip.ec.model.order.OrderToDTO;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.Vcitable;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.CustomRealName;
import com.ectrip.ec.model.user.UserbankFlow;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.ClientRequest;
import com.ectrip.hqyt.model.request.GuaranteeLykRequest;
import com.ectrip.hqyt.model.request.LegalPersonRequest;
import com.ectrip.hqyt.model.request.ProductRequest;
import com.ectrip.hqyt.model.request.QueryPreBinding;
import com.ectrip.hqyt.model.response.JSONClient;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.sys.model.balance.Zhifu;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.tourcard.feign.service.EcService;
import com.ectrip.tourcard.feign.service.SysService;
import com.ectrip.tourcard.feign.service.TicketService;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;
import com.ectrip.tourcard.service.ITourCardSearchService;
import com.ectrip.tourcard.service.ITravelCardService;

public class TravelCardService implements ITravelCardService {

    private static Logger logger = LoggerFactory.getLogger(TravelCardService.class);

    @Autowired
    private EcService ecService;//电商服务API
    
    @Autowired
    private TicketService ticketService;//票务服务API
    
    @Autowired
    public ITourCardSearchService tourCardSearchService;
    
    @Autowired
    public IGenericService genericService;
    
    @Autowired
    private SysService sysService;//系统服务API
   
    
    
    
    
    /**
     *  发送短信
     * @param revmbno
     * @return
     */
    public Map<String, Object> sendMessage(String revmbno){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            System.out.println(revmbno);
            String date = Tools.getNowString();
            //List<Custom> listc = ecService.find(" from Custom where mobile='"+revmbno+"' ");
            List<Custom> listc = genericService.find("from Custom where mobile=?", new Object[]{revmbno});

            //step1: 验证手机号是否已经注册过了
            if (listc.size() == 0 || listc.isEmpty()) {
                resultMap.put("status", 2);
                resultMap.put("message", "不存在该用户，请先注册！");
                return resultMap;
            }


            //step2: 保存短信，定时任务发送
            Mbmessage mbmessage = null;
            String random = this.getFixLenthString(4);
            List<Mbmessage> list = genericService.find("from Mbmessage where revmbno=?",new Object[]{revmbno});
            if (list.size() > 0 || !list.isEmpty()) {
                mbmessage = list.get(0);
            }

            String info = "您的验证码是：" + random + "，请保存好验证码！并在2分钟内使用！";
            if (mbmessage != null) {
                System.out.println("<<<<<<<<<<<<<<<<<<<<重发验证码" + random+">>>>>>>>>>>>>>>>>>>");
                String ran = random;
                mbmessage.setRevmbno(revmbno);
                mbmessage.setIsok(0);// 未发送
                mbmessage.setNote(info);
                mbmessage.setDtime(date);
                mbmessage.setQuantity(1);
                genericService.update(mbmessage);

            } else {
                mbmessage = new Mbmessage();
                Long seq = genericService.getMaxPk("seq", "Mbmessage");
                mbmessage.setSeq(seq + 1);
                mbmessage.setRevmbno(revmbno);
                mbmessage.setDtime(date);
                mbmessage.setIsok(0);
                mbmessage.setNote(info.toString());
                mbmessage.setQuantity(1);
                genericService.save(mbmessage);

            }
            // step3: 保存验证码信息
            Vcitable vcitable = null;
//            List<Vcitable> listvc = ecService.find("from Vcitable where usid=?", new Object[]{revmbno});
//            if (listvc.size() > 0 || !listvc.isEmpty()) {
//                vcitable = listvc.get(0);
//            }
            if (vcitable != null) {
                vcitable.setCode(random);
                vcitable.setDtmakedate(Tools.getDayTimes());
                vcitable.setType("02");
                vcitable.setUsetime(2 * 60L);
                vcitable.setUsername(revmbno);
                vcitable.setDtmakedate(Tools.getNowString());
                vcitable.setUsid(revmbno);
                genericService.update(vcitable);
            } else {
                vcitable = new Vcitable();
                vcitable.setCode(random);
                vcitable.setDtmakedate(Tools.getDayTimes());
                vcitable.setType("02");
                vcitable.setUsetime(2 * 60L);
                vcitable.setUsername(revmbno);
                vcitable.setDtmakedate(Tools.getNowString());
                vcitable.setUsid(revmbno);
                genericService.save(vcitable);
            }


            sysService.DbSendInfo();
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status", 1);
            resultMap.put("message", "发送验证码失败请从新获取！");
            return resultMap;
        }

        resultMap.put("status", 0);
        resultMap.put("data", "发送验证码成功，请注意查收！");
        return resultMap;
    }

    /**
     * 实名认证
     * @param param
     * @return
     */
    @Deprecated // unused
    public Map<String, Object> saveUserBank(String[] param){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String usid = param[0];//用户id
        String pwd = param[1];
        JSONObject jsonValue = JSONObject.parseObject(param[2]);
        String cardType = jsonValue.getString("cardType");//卡类型
        String idName = jsonValue.getString("idName"); //名称
        String idNumber = jsonValue.getString("idNumber"); //身份证号
        String idity = jsonValue.getString("idity"); //卡有效期
        String cvvr = jsonValue.getString("cvvr"); //安全码
        String cardNumber = jsonValue.getString("cardNumber"); //卡号
        String cardPhone = jsonValue.getString("cardPhone"); //手机号
        String random = jsonValue.getString("random"); //验证码

        if(usid==null||"".equals(usid)||pwd==null||"".equals(pwd)){
            resultMap.put("status", 1);
            resultMap.put("message", "用户信息不全,请重新登录！");
            return resultMap;
        }

        //step1：判断填写信息是否正确
        {
            //判断是否有该用户
            Map map = ecService.findUserByPwd(usid, pwd);
            if(map==null||map.size()<=0){
                resultMap.put("status", 2);
                resultMap.put("message", "不存在该用户，请先注册！");
                return resultMap;
            }


            //判断验证码
            if(cardPhone==null||"".equals(cardPhone)){
                resultMap.put("status", 3);
                resultMap.put("message", "手机号为空！");
                return resultMap;
            }
            Pattern p = null;
            Matcher m = null;
            p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
            m = p.matcher(cardPhone);
            if(!m.matches()){
                resultMap.put("status", 9);
                resultMap.put("message", "手机号不正确请填写正确手机号！");
                return resultMap;
            }
            if(random==null||"".equals(random)){
                resultMap.put("status", 4);
                resultMap.put("message", "验证码为空！");
                return resultMap;
            }
            Vcitable vcitable = ecService.getValidate(cardPhone);
            if (!vcitable.getCode().equals(random)) {// 验证激活码不正确
                resultMap.put("status", 5);
                resultMap.put("message", "验证码错误！");
                return resultMap;
            } else {
                Long db_time = vcitable.getUsetime();
                String make_time = vcitable.getDtmakedate();
                try {
                    System.out.println(Tools.getNowString() + "," + db_time);
                    Long date = df.parse(Tools.getNowString()).getTime()
                            - df.parse(make_time).getTime();
                    if (date / 1000 >= db_time) {// 判断验证码是否超时
                        resultMap.put("status", 6);
                        resultMap.put("message", "验证码超时，请重新获取！");
                        return resultMap;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resultMap.put("status", 7);
                    resultMap.put("message", "时间格式不对  yyyy-MM-dd HH:mm:ss！");
                    return resultMap;
                }
            }

            //判断是否为空
            if(cardType==null||"".equals(cardType) || idName==null||"".equals(idName) || idNumber==null||"".equals(idNumber)
                    ||idity==null||"".equals(idity) || cardNumber==null||"".equals(cardNumber)){
                resultMap.put("status", 8);
                resultMap.put("message", "填写信息不完整，请完善信息!");
                return resultMap;
            }


            //判断省身证号是否正确
            String str ="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
            Pattern pattern = Pattern.compile(str);
            if(!pattern.matcher(idNumber).matches()){
                resultMap.put("status", 10);
                resultMap.put("message", "请输入正确的身份证号");
                return resultMap;
            }

        }

        //step2:对接交易结算实名认证
        {

        }

        //step3:保存实名信息
        try {
            CustomRealName realNameObj = (CustomRealName)genericService.get(CustomRealName.class, usid);
            if(realNameObj == null){
                realNameObj = new CustomRealName();
                realNameObj.setRealName(idName);
                realNameObj.setUserId(usid);
                realNameObj.setIdNumber(DesEncryptUtil.encrypt(idNumber));
                realNameObj.setCreateDate(new Date());
                genericService.save(realNameObj);
            }else{
                if(!idName.equals(realNameObj.getRealName()) || !DesEncryptUtil.encrypt(idNumber).equals(realNameObj.getIdNumber())){
                    resultMap.put("status", 12);
                    resultMap.put("message", "与存在实名信息不一致！");
                    return resultMap;
                }
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            resultMap.put("status", 13);
            resultMap.put("message", "实名认证失败！");
            return resultMap;
        }
        resultMap.put("status", 0);
        resultMap.put("data", "认证成功！");
        return resultMap;
    }


    /*
	 * 生成激活码 返回长度为【strLength】的随机数，在前面补0
	 */
    private String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }


    /**
     * 旅游卡专享列表（区分景区）
     * @param param
     * @return
     */
    public Map<String, Object> userTravelCard(String[] param){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> listValue = new ArrayList<Map<String, Object>>();
        String phone = param[0];//用户id
        String pwd = param[1];
        String usid = "";

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
            }
            Map map = ecService.findUserByPhone(phone, pwd);
            usid = String.valueOf(map.get("usid"));
        }

        //step2:判断该用户是否实名制
        {
            CustomRealName customRealName = (CustomRealName)genericService.get(CustomRealName.class, usid);
            if(customRealName==null){
                resultMap.put("status", 3);
                resultMap.put("message", "该用户没有实名制！");
                return resultMap;
            }
        }

        //step3：获取用户的旅游卡下所有景区信息
        //用户旅游卡信息
        List<TourCard> list = tourCardSearchService.getTourCard(usid);
        if(list==null||list.size()<=0){
            resultMap.put("status", 4);
            resultMap.put("message", "该用户没有有效的旅游卡！");
            return resultMap;
        }

        Iterator<TourCard> iterator= list.iterator();
        //旅游卡所在景区信息(旅游卡+景区：区分旅游卡)
        while(iterator.hasNext()){
            Map<String, Object> scenicMap = new HashMap<String, Object>();
            TourCard tourCard = (TourCard) iterator.next();
            List<Map<String, Object>> listSc = ecService.getSenice(tourCard.getIscenicids());

            scenicMap.put("NAME", tourCard.getName());//旅游卡名称
            scenicMap.put("value", listSc);//景区信息
            listValue.add(scenicMap);
        }

        resultMap.put("status", 0);
        resultMap.put("data",  listValue);
        return resultMap;
    }

    /**
     * 旅游卡专享列表（不区分景区）
     * @param param
     * @return
     */
    public Map<String, Object> userTravelCardl(String[] param){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> listValue = new ArrayList<Map<String, Object>>();
        String phone = param[0];//用户id
        String pwd = param[1];
        String usid = "";

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
            }
            Map map = ecService.findUserByPhone(phone, pwd);
            usid = String.valueOf(map.get("usid"));
        }

        //step2:判断该用户是否实名制
        {
            CustomRealName customRealName = (CustomRealName)genericService.get(CustomRealName.class, usid);
            if(customRealName==null){
                resultMap.put("status", 3);
                resultMap.put("message", "该用户没有实名制！");
                return resultMap;
            }
        }

        //step3：获取用户的旅游卡下所有景区信息
        //用户旅游卡信息
        List<TourCard> list = tourCardSearchService.getTourCard(usid);
        if(list==null||list.size()<=0){
            resultMap.put("status", 4);
            resultMap.put("message", "该用户没有有效的旅游卡！");
            return resultMap;
        }

        Iterator<TourCard> iterator= list.iterator();

        //旅游卡下的景区
        String iscenids = "";
        while(iterator.hasNext()){
            TourCard tourCard = (TourCard) iterator.next();
            iscenids = iscenids+tourCard.getIscenicids()+",";
        }
        List<Map<String, Object>> listIscenid = ecService.getSenice(iscenids);
        listValue = listIscenid;

        resultMap.put("status", 0);
        resultMap.put("data",  listValue);
        return resultMap;
    }

    /**
     * 获取旅游卡门票详细信息
     * @param param
     * @return
     */
    public Map<String, Object> getTicketInfo(String[] param){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String usid = param[0];
        String pwd = param[1];
        String scienid = param[2];

        //判断用户是否登陆
        String status = ecService.valilogin(usid, pwd);
        if("1".equals(status)){
            resultMap.put("status", status);
            resultMap.put("message",  "请先登陆");
        }
        if("2".equals(status)){
            resultMap.put("status", status);
            resultMap.put("message",  "密码错误");
        }

        try{
            Map<String, Object> ticketInfo = ecService.getTicketInfo(scienid);
            List<Map<String, Object>> jsonObject = ecService.getScenceImg(scienid);
            ticketInfo.put("picture", jsonObject);

            resultMap.put("status", 0);
            resultMap.put("data",  ticketInfo);
        }catch (Exception e){
            resultMap.put("status", 1);
            resultMap.put("message",  "获取门票信息失败");
        }

        return resultMap;
    }


    /**
     * 保存订单
     * @param xml 订单信息
     * @param orid 订单id
     * @return
     * @throws Exception
     */
    public String newSaveOrder(String xml, String orid) throws Exception{
        ObjectParse parse = new ObjectParse();
        BaseModel model = parse.XmlToObject(xml);
        BaseModel newModel = null;
        Long iscenicid = 0L;
        String hqyatuDate = null;
        MOrder m = (MOrder) genericService.get(MOrder.class, orid);
        List<ProductRequest> products = new ArrayList<ProductRequest>();
        String OwnerNumber = "";
        double totalFee = 0;
        Object tourCardNo = "";//旅游卡卡号
        String telphone = "";//
        String usid = "";
        String playTime = "";//游玩日期
        String isenceName = "";//景区名称


        try{//保存订单
            //获取用户登录信息
            newModel = validateUser(model.getUsid(), model.getPwd());
            newModel.setLogonstatus(model.getLogonstatus());
            newModel.setPwd(model.getPwd());
            newModel.setNodes(model.getNodes());

            if("NULL".equals(model.getObject())||model.getObject()==null){
                newModel.setLogonstatus("0");
                newModel.setMsgtp("联系人信息不可为空!");
                return JSON.toJSONString(newModel);
            }

            //判断用户是否登录
            if (newModel.getLogonstatus().equals("1")) {
                Map user = ecService.findUser(model.getUsid(), model.getPwd());
                usid = user.get("usid").toString();
                LprPojo lpr = new LprPojo();
                Map object = (Map) model.getObject();
                tourCardNo = object.get("cardNumber");
                telphone = String.valueOf(object.get("telphone"));
                object.put("state",0);
                object.put("mont",0);
                object.put("isSync","NULL");
                object.put("zforid","1");
                newModel.setObject(object);

                //封装领票人
                String holdername = (String)object.get("holdername");lpr.setDaoyou(holdername);
                telphone = (String)object.get("telphone");lpr.setMobile(telphone);
                String printpwd = (String)object.get("printpwd");lpr.setPassword(printpwd);
                String orzj = (String)object.get("orzj");lpr.setZjlb(orzj);
                String cardno = (String)object.get("cardno");
                Custom custom = ecService.findByMobile(lpr.getMobile());
                CustomRealName customRealName = (CustomRealName)genericService.get(CustomRealName.class, custom.getUsid());
                if(StringUtil.isNotEmpty(cardno) && cardno.contains("****************")){//为兼容app v1.0
                    lpr.setZjhm(DesEncryptUtil.decrypt(customRealName.getIdNumber()));
                }else{
                    lpr.setZjhm(cardno);
                }
                String seq = "".equals(object.get("seq"))?"":(String)object.get("seq");lpr.setSeq(seq);

                //step1:判断是否满足订单条件（包括手机号、领票人身份证号、预定时间、最大预定日期、预定次数）
                if (lpr.getDaoyou() == null || lpr.getDaoyou().equals("")) {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("联系人姓名不能为空!");
                    return JSON.toJSONString(newModel);
                }

                if (lpr.getMobile() == null || lpr.getMobile().equals("")) {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("联系电话不能为空!");
                    return JSON.toJSONString(newModel);
                } else {
                    Pattern p1 = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
                    boolean c = p1.matcher(lpr.getMobile()).matches();
                    OwnerNumber = lpr.getMobile();
                    if (!c) {
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("请输入正确的手机号码");
                        return JSON.toJSONString(newModel);
                    }
                }
                if (lpr.getZjhm() == null || lpr.getZjhm().equals("")) {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("证件号码不能为空");
                    return JSON.toJSONString(newModel);
                } else {
                    if (lpr.getZjlb() != null && lpr.getZjlb().equals("01")) {
                        Pattern p1 = Pattern
                                .compile("^\\d{15}|(\\d{17}(\\d|X|x))$");
                        boolean c = p1.matcher(lpr.getZjhm()).matches();
                        if (!c) {
                            newModel.setLogonstatus("0");
                            newModel.setMsgtp("请输入正确的身份证号码");
                            return JSON.toJSONString(newModel);
                        }
                    }
                }

                if (newModel.getNodes() == null || newModel.getNodes().size() == 0) {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("请选择购买产品!");
                    return JSON.toJSONString(newModel);
                }
                //判断预定日期
                String playtime = String.valueOf(((Map) newModel.getNodes().get(0)).get("playtime"));

                Esbscenicareatab provider = (Esbscenicareatab) genericService.get(Esbscenicareatab.class, Long.valueOf(object.get("providerid").toString()));
                isenceName = provider.getSzscenicname();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sdf.parse(playtime).before(sdf.parse(Tools.getDays()))) {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("预定日期不能小于当前日期");
                    return JSON.toJSONString(newModel);
                }

                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                c1.setTime(sf.parse(Tools.getNowString()));
                c2.setTime(sf.parse(Tools.getDays() + " " + provider.getSzlasttime() + ":00"));
                System.out.println(c1.compareTo(c2));
                if (playtime.equals(Tools.getDays()) && c1.compareTo(c2) >= 0) {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("当日预订截止时间为" + provider.getSzlasttime() + "");
                    return JSON.toJSONString(newModel);
                }



                Map<String, Object> csMap = ecService.getTourCardInfo(tourCardNo);
                String periodendDate  = String.valueOf(csMap.get("PERIODENDDATE"));
                if(csMap==null){
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("没有找到所拥有旅游卡的详细信息！");
                    return JSON.toJSONString(newModel);
                }
                //判断预定次数
                String timesFlag = String.valueOf(csMap.get("TIMESFLAG"));//0限制次数 -1不限制
                int usedtimes = Integer.parseInt(String.valueOf(csMap.get("USEDTIMES")!=null?csMap.get("USEDTIMES"):0));
               // int leaveTimes = Integer.parseInt(String.valueOf(csMap.get("LEAVETIMES")));//剩余次数
                Object effrctive = csMap.get("EFFECTIVETIMES");//总次数
                if(timesFlag.equals("0")&&effrctive!=null&&usedtimes>=(Integer.parseInt(String.valueOf(effrctive)))){
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("旅游卡次数已用完！");
                    return JSON.toJSONString(newModel);
                }


                //step2:判断用户的游玩时间是否买了多张票（相同时间的票只能买一张）
                //判 断时间
                for (Map note : newModel.getNodes()) {
                    //判断时间
                    String date = String.valueOf(note.get("playtime")).trim();
                    String useperiod = String.valueOf(csMap.get("USEPERIOD"));
                    if("0".equals(useperiod)&&valiWeekday(date)){//星期六天不能使用
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("星期六、星期天不能使用该旅游卡买票");
                        return JSON.toJSONString(newModel);
                    }
                    if("1".equals(useperiod)&&ecService.getHoliday(date)){//节假日不能使用该旅游卡买票
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("节假日不能使用该旅游卡买票");
                        return JSON.toJSONString(newModel);
                    }

                    //入园时间不能超过旅游卡有效期
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = df.parse(date.trim());//游玩时间
                    Date date2 = df.parse(periodendDate.trim());//旅游卡有效期
                    if(date1.after(date2)){
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("游玩日期已过旅游卡有效期!");
                        return JSON.toJSONString(newModel);
                    }

                    //查看是否已经购票
                    Map<String, Object> lykOrderList = ecService.getLykOrderList(note.get("itickettypeid").toString(), Long.parseLong(note.get("icrowdkindid").toString()),
                            Long.valueOf(note.get("iscenicid").toString()), date, usid);

                    String status =  String.valueOf(lykOrderList.get("status"));
                    if(status.equals("0")) {
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp(String.valueOf(lykOrderList.get("message")));
                        return JSON.toJSONString(newModel);
                    }
                }

                //step3:保存订单信息
                //实名制信息
                List<TRealname> listRealName = new ArrayList<TRealname>();

                //封装订单信息
                List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
                for (Map note : newModel.getNodes()) {
                    //判断票价
                    Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) genericService.get(Edmcrowdkindpricetab.class, Long.parseLong(note.get("icrowdkindpriceid").toString()));
                    if(edmcrowdkindpricetab.getIpeoplenumrange()!=1){
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("该门票不为实名制门票！");
                        return JSON.toJSONString(newModel);
                    }

                    //票价开始结束时间
                    String endData = edmcrowdkindpricetab.getEnddata();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date endDates = simpleDateFormat.parse(endData);
                    Date playDate = simpleDateFormat.parse((String) note.get("playtime"));
                    if(endDates.before(playDate)){
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("该门票已过最迟预定时间！");
                        return JSON.toJSONString(newModel);
                    }

                    OrderInfo orderinfo = new OrderInfo();
                    hqyatuDate = String.valueOf(note.get("playtime"));
                    iscenicid = Long.valueOf(note.get("iscenicid").toString());
                    orderinfo.setIscenicid(Long.valueOf(note.get("iscenicid").toString()));//服务商id
                    orderinfo.setItickettypeid(Long.valueOf(note.get("itickettypeid").toString()));//产品id
                    orderinfo.setPlaytime((String) note.get("playtime"));//
                    playTime = String.valueOf(note.get("playtime"));
                    orderinfo.setNum(Long.valueOf(note.get("num").toString()));
                    orderinfo.setIcrowdkindid(Long.valueOf(note.get("icrowdkindid").toString()));
                    orderinfo.setNote1( (String) note.get("note1"));
                    orderInfos.add(orderinfo);

                    TRealname tRealname = new TRealname();
                    tRealname.setItickettypeid(Long.valueOf(note.get("itickettypeid").toString()));
                    tRealname.setIcrowdkindid(Long.parseLong(note.get("icrowdkindid").toString()));
                    tRealname.setIscenicid(Long.valueOf(note.get("iscenicid").toString()));
                    tRealname.setCname(lpr.getDaoyou());
                    tRealname.setIdcard(lpr.getZjhm());
                    tRealname.setIschild(1L);
                    tRealname.setZjtp("01");
                    listRealName.add(tRealname);
                }

                // 领票人集合
                List<LprPojo> lprs = new ArrayList<LprPojo>();
                lpr.setRzti(playtime);
                lpr.setIscenicid(new String().valueOf(orderInfos.get(0).getIscenicid()));
                lprs.add(lpr);

                //判断是否有该用户没有则创建并同步到交易结算
                if(custom==null){
                    custom = ecService.anonymityUserCreate("02", lpr.getMobile());
                    System.out.println("anonymityUserCreate");
                    //同步注册结算
                    ClientRequest clientRequest = new ClientRequest();

                    clientRequest.setRegistrationName(custom.getNote6());
                    clientRequest.setBusinessTel(lpr.getMobile());
                    clientRequest.setPassword(custom.getPassword()); //明文给结算-keycloak

                    HqytClient client = new HqytClient();
                    JSONClient jsonClient = null;
                    try {
                        jsonClient = client.createClient(clientRequest);
                    } catch(Exception e) {
                        //结算失败，删除电商记录
                    	genericService.delete(custom);
                        //抛出结算接口异常
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("HQYT:" + e.getMessage());
                        return JSON.toJSONString(newModel);
                    }

                    if(jsonClient!=null) {
                        //TODO写入结算系统的regeistrationName和ID
                        custom.setNote6(jsonClient.getRegistrationName()); //结算client的RegistrationName
                        custom.setNote9(jsonClient.getId().toString());  //结算client的ID
                        genericService.update(custom);
                    }else {
                        newModel.setLogonstatus("0");
                        newModel.setMsgtp("HQYT:" + "结算系统接口返回异常");
                        return JSON.toJSONString(newModel);
                    }
                }



                //保存订单
                OrderCombinDTO orderCombinDTO = OrderToDTO.toOrderCombinDTO(orderInfos,
                        null,user.get("usid").toString(), listRealName, null, lprs, orid);
                orderCombinDTO.setOrderSource("lykgp");//标识旅游卡购票
                orderCombinDTO.setNotee(object.get("cardNumber").toString());//卡号

                boolean result = ecService.SaveOrder(orderCombinDTO);
                System.out.println("orid:" + orid);

                //同步订单
                String casher = "1";
                boolean isSync = true;// 是否进行订单同步
                if (result) {
                    m = (MOrder) genericService.get(MOrder.class, orid);
                }else {
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("订单保存失败,请重新尝试");
                    return JSON.toJSONString(newModel);
                }

                object.put("mont",m.getZfmont());
                String zforid = findByOrderid(orid, "10");
                if ("-1".equals(zforid) || "0".equals(zforid)) {
                    object.put("mont",0);
                    newModel.setLogonstatus("0");
                    newModel.setMsgtp("获取支付号失败!");
                    return JSON.toJSONString(newModel);
                } else {
                    object.put("zforid", zforid);
                }
                object.put("isSync",isSync);
                object.put("state", "1");
                object.put("casher",casher);
            }else{
                newModel.setLogonstatus("0");
                newModel.setMsgtp("登录错误，请重新登录!");
                return JSON.toJSONString(newModel);
            }
        }catch (Exception e) {
            e.printStackTrace();
            newModel.setLogonstatus("0");
            newModel.setMsgtp("订单保存失败!");
            return JSON.toJSONString(newModel);
        }

        TourCardDetail tourCardDetail = tourCardSearchService.getTourCarDetail(tourCardNo);

        //step3: 调用交易结算
        GuaranteeLykRequest request = new GuaranteeLykRequest();
        //判断该商户号是否开户
        ProviderCompany pc = ticketService.queryProviderCompany(iscenicid);
        if(pc != null){
            request.setIssuerId(pc.getHqytId().toString());
        }else{
            this.ecService.updateOrderDdzt(orid,"98");
            newModel.setLogonstatus("0");
            newModel.setMsgtp("服务商未开户不可预定");
            return JSON.toJSONString(newModel);
        }

        //判断用户没有同步结算系统的用户ID
        Map  map= ecService.findUser(model.getUsid(),model.getPwd());
        LegalPersonRequest receiver = new LegalPersonRequest();
        if(map!=null && map.get("note9")==null) {
            this.ecService.updateOrderDdzt(orid,"98");  //电商-订单作废
            newModel.setLogonstatus("0");
            newModel.setMsgtp("用户没有同步结算系统的用户ID");
            return JSON.toJSONString(newModel);
        }
        receiver.setId(Long.valueOf(map.get("note9").toString()));
        request.setReceiver(receiver);

        //封装结算订单信息
        try{
            List<TOrderlist> torderList = ecService.getTOrderlistByOrid(orid);
            if(torderList != null && !torderList.isEmpty()){
                for (TOrderlist tl : torderList){
                    ProductRequest productRequest = new ProductRequest();
                    Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) genericService.get(Edmcrowdkindpricetab.class, tl.getIcrowdkindpriceid());

                    productRequest.setExternalId(tl.getItickettypeid());

                    Edmtickettypetab ticket = (Edmtickettypetab) genericService.get(Edmtickettypetab.class,tl.getItickettypeid());
                    if(ticket!=null)
                    {
                        productRequest.setName(ticket.getSztickettypename());
                    }
                    productRequest.setPrice(edmcrowdkindpricetab.getListingprice());
                    productRequest.setNumb(tl.getNumb());
                    products.add(productRequest);
                    totalFee +=edmcrowdkindpricetab.getListingprice();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            //作废订单，并删除实名制信息
            this.ecService.updateOrderDdzt(orid,"98");
            newModel.setLogonstatus("0");
            newModel.setMsgtp(e.getMessage());
            return JSON.toJSONString(newModel);
        }

        try {
            //旅游卡必要信息
            request.setBusinessType("TRAVELIN");// 业务类别
            request.setSplitAgreementNo(tourCardDetail.getProfitNum());//分润协议编号
            request.setTourCardCode(tourCardDetail.getCode());//旅游卡代码
            request.setTourCardName(tourCardDetail.getName());//旅游卡名称
            request.setTourCardNo(tourCardDetail.getCardNumber());//旅游卡卡号
            request.setBookName(tourCardDetail.getUsername());//游客姓名
            String tourCardIdent = DesEncryptUtil.decrypt(tourCardDetail.getIdentityNum());
            request.setBookIdCard(tourCardIdent);//游客身份证号
            request.setBookPhone(telphone);//游客手机号
            request.setHistoryTourCard(tourCardDetail.getHistoryCardFlag()==0?false:true);//是否历史旅游卡

            //调用交易结算
            request.setOrid(orid);
            request.setUsid(model.getUsid());
            request.setTotalMoney(totalFee);
            request.setUsername(model.getUsername());
            request.setPhone(OwnerNumber);
            request.setProducts(products);
            request.setConsumeTimeLimit(hqyatuDate);
            HqytClient client = new HqytClient();
            JSONInvoice invoice = null;

            request.setTitle("旅游卡订单");
            invoice = client.appGuaranteeSkLyk(request);
            if(invoice != null){
                m.setNoteh(invoice.getId().toString());
                genericService.update(m);
                //step4：创建订单成功后直接修改状态
                ecService.updateOrderStatus(orid,"", "0","13",0,"02",null,"01","1", usid);
                //旅游卡使用次数+1
                ecService.useTourTicket(String.valueOf(tourCardNo), usid);
                //发送短信
//                sysService.sendMessageNew(telphone, "0025", new String[]{orid, playTime, isenceName});
                sysService.sendMessageNew(telphone, "0025", orid+playTime+isenceName);
            }else{
                this.ecService.updateOrderDdzt(orid,"98");
                newModel.setLogonstatus("0");
                newModel.setMsgtp("创建支付订单失败");
                return JSON.toJSONString(newModel);
                //throw new RuntimeException("创建支付订单失败");
            }

        } catch(Exception e) {
            e.printStackTrace();
            this.ecService.updateOrderDdzt(orid,"98");
            //抛出结算接口异常
            newModel.setLogonstatus("0");
            newModel.setMsgtp("HQYT:" + e.getMessage());
            return JSON.toJSONString(newModel);
        }

        newModel.setLogonstatus("1");
        newModel.setMsgtp("订单预订成功");
        return JSON.toJSONString(newModel);
    }

    private BaseModel validateUser(String usid, String pwd) {
        BaseModel model = new BaseModel();
        model.setUsid(usid);
        //status：1-用户不存在      2-密码错误    0-登录成功
        String status = ecService.valilogin(usid, pwd);
        System.out.println("用户登录："+status);
        if (status.equals("0")) {
            Map map = ecService.findUser(usid, pwd);
            System.out.println("map="+map);
            if (map == null) {
                model.setMsgtp(AndroidStateCode.LOGIN_CANTBOOK);
            }else{
                if (map.get("lname") == null || map.get("lname").equals("")) {
                    model.setUsername("");
                } else {
                    model.setUsername(map.get("lname").toString());
                }
                if (map.get("usid") == null || map.get("usid").equals("")) {
                    model.setUsername("");
                } else {
                    model.setUsid(map.get("usid").toString());
                }
            }
        } else if(status.equals("1")){
            model.setMsgtp(AndroidStateCode.LOGIN_NOUSER);
        }else if(status.equals("2")){
            model.setMsgtp(AndroidStateCode.LOGIN_PWDERROR);
        }

        if(status.equals("1")||status.equals("2")){
            model.setLogonstatus("0");//登录失败，状态离线
        }else{
            model.setLogonstatus("1");//登录成功，状态在线
        }

        return model;
    }

    public String findByOrderid(String orid, String bank) {
        MOrder mo = (MOrder) this.genericService.get(MOrder.class, orid);
        if (mo != null) {
            String orti = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Zhifu zf = new Zhifu();
            try {
                zf.setOrid(orid);
                zf.setUsid(mo.getUsid());
                zf.setMont(Double.valueOf(mo.getMont()));
                zf.setPayid(getZhifuNewMaxNo());
                zf.setOrti(orti);
                int seq = this.zhifumaxseq();
                zf.setSeq(seq);
                zf.setIsok(0);
                zf.setBank(bank);
                zf.setTdmont(Double.valueOf("0"));
                this.genericService.save(zf);// 保存支付信息
                return zf.getPayid();
            } catch (Exception e) {
                e.printStackTrace();
                return "-1";
            }
        } else {
            return "0";
        }
    }

    /**
     * 取最大支付号
     */
    public String getZhifuNewMaxNo() throws Exception {
        String zfqz = this.retrieveSysparKey("ZFQZ");
        if (zfqz == null || zfqz.equals("")) {
            zfqz = "ZF";
        } else {
            if (zfqz.length() > 2) {
                zfqz = zfqz.substring(0, 2);
            }
        }
        return this.genericService.getZhifuMaxNo().replaceAll("ZF", zfqz).trim();
    }

    /**
     * 读取支付前缀参数，无表示没有
     *
     * @param pmky
     * @return
     */
    String retrieveSysparKey(String pmky) {

        try {
            List list = new ArrayList();
            String hsql = "select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys where ";
            hsql += " sys.id.pmky='" + pmky
                    + "' and sys.id.pmcd not like'%*%' order by sys.id.pmcd";
            list = this.genericService.find(hsql);

            // List list = this.retrieveSysparKey("ZFQZ") ; //读取支付前缀
            String zfqz = "";
            if (list.size() > 0) {
                Map v5 = (Map) list.get(0);
                if (v5.get("pmcd").equals("0001")) // 是否判断预付款
                {
                    zfqz = (String) v5.get("pmva");
                } else {
                    zfqz = "";
                }
            }
            return zfqz;
        } catch (Exception e) {
            return "";
        }
    }

    public int zhifumaxseq(){
        int seq = 0;
        List list = this.genericService.find("select nvl(max(z.seq),0) as seq from Zhifu z");
        if(list!=null&&list.size()>0){
            seq = (Integer)list.get(0);
        }
        return seq+1;
    }

    /**
     * 根据用户获取所拥有的旅游卡
     * @param param
     * @return
     */
    public Map<String, Object> getUserCard(String[] param){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String usid = param[0];//用户手机号
        String pwd = param[1];
        String isencise = param[2];

        //step1:判断是否有该用户
        {
            if (usid == null || "".equals(usid) || pwd == null || "".equals(pwd)) {
                resultMap.put("status", 1);
                resultMap.put("message", "没有传用户信息！");
                return resultMap;
            }
            Map map = ecService.findUserByPwd(usid, pwd);
            if (map == null || map.size() <= 0) {
                resultMap.put("status", 2);
                resultMap.put("message", "没有该用户！");
                return resultMap;
            }
        }

        Custom custom = ecService.getCustomByPhone(usid);
        List<Map<String, Object>> cardList = this.ecService.getUserCard(custom.getUsid(),isencise);
        if(cardList==null||cardList.size()<=0){
            resultMap.put("status", 3);
            resultMap.put("message", "没有查找到旅游卡！");
            return resultMap;
        }
        resultMap.put("status", 0);
        resultMap.put("data", cardList);
        return resultMap;
    }

    /**
     * 验证星期六星期天
     * @param date
     * @return
     */
    public boolean valiWeekday(String date){
        boolean b = false;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date dates = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dates);
            if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
             b = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return b;
    }


    /**
     * @param params
     * @return 用户取消预定
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ParseException
     */
    public Map<String, Object> orderRefused(String[] params ) throws IllegalAccessException,
            InvocationTargetException, ParseException {
        String phone = params[0];
        String pwd = params[1];
        String orid =  params[2];
        String iscenicid = params[3];
        String cardNum = params[4];

        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<TOrderlist> torderlists = new ArrayList<TOrderlist>();

        this.ecService.getCustomByPhone(phone);
        List mlist = this.ecService.getMOrderList(orid);
        MOrder morder = new MOrder();
        BeanUtils.populate(morder, (Map) mlist.get(0));

        //step1:判断是否有该用户
        {
            if (phone == null || "".equals(phone) || pwd == null || "".equals(pwd)) {
                resultMap.put("status", 1);
                resultMap.put("message", "没有传用户信息！");
                return resultMap;
            }
            Map map = ecService.findUserByPwd(phone, pwd);
            if (map == null || map.size() <= 0) {
                resultMap.put("status", 2);
                resultMap.put("message", "没有该用户！");
                return resultMap;
            }
        }
        Custom custom = ecService.getCustomByPhone(phone);

        if(!"lykgp".equals(morder.getOrdersource())){
            resultMap.put("status", 7);
            resultMap.put("message", "对不起,该笔订单不为旅游卡购票订单不能取消预定，如有疑问请联系管理人员.！");
            return resultMap;
        }
        if (!morder.getUsid().equals(custom.getUsid())) {
            resultMap.put("status", 3);
            resultMap.put("message", "对不起,您没有权限，如有疑问请联系管理人员.！");
            return resultMap;
        }
        if (iscenicid == null || iscenicid.equals("")) {
            resultMap.put("status", 4);
            resultMap.put("message", "服务商编号不能为空！");
            return resultMap;
        }
        String[] orids = new String[2];
        TOrder tds = (TOrder) this.genericService.get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
        String ddzt = tds.getDdzt();
        if (!ddzt.equals("02") && !ddzt.equals("23") && !ddzt.equals("30")) {
            resultMap.put("status", 5);
            resultMap.put("message", "只有订单状态为[已支付]的订单才可以退订！");
            return resultMap;
        }
        if(tds.getIschupiao() != null && tds.getIschupiao() == 1L){
            resultMap.put("status", 6);
            resultMap.put("message", "该订单已被锁定，不允许退订！");
            return resultMap;
        }
        torderlists = ecService.getTOrderlists(orid, Long.parseLong(iscenicid));
        try {
            orids[0] = "";
            orids[1] = this.genericService.getMaxNo("000");
            resultMap = ecService.editOrderSankeCenterl(torderlists, null, orids, orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), custom.getUsid());
            //使用次数+1
            ecService.refuseTourTicket(cardNum, custom.getUsid());
            for (int i = 0; i < orids.length; i++) {
                MOrder editmorder = (MOrder) genericService.get(MOrder.class, orids[i]);// 获取修改的订单
                // 若为空则下次循环
                if (editmorder != null) {
                    Orderlog log = new Orderlog();
                    log.setLogid(genericService.getMaxPk("logid", "Orderlog") + 1);
                    log.setOrid(orid);
                    log.setStlg("0158");
                    log.setBrief("修改订单");
                    log.setNote("");
                    List editlist = ecService.getYOrderListChildList(orids[i], iscenicid);
                    if (editlist != null && editlist.size() > 0) {
                        if (editmorder.getOrtp().equals("02")) {
                            if (log.getNote() != null && !log.getNote().trim().equals("")) {
                                log.setNote("</br>");
                            }
                            log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
                        } else if (editmorder.getOrtp().equals("03")) {
                            if (log.getNote() != null && !log.getNote().trim().equals("")) {
                                log.setNote("</br>");
                            }
                            log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
                        }
                        for (int j = 0; j < editlist.size(); j++) {
                            Map editMap = (Map) editlist.get(j);
                            System.out.println("map value" + editMap);
                            log.setNote(log.getNote() + "[" + editMap.get("szscenicname").toString() + "]&nbsp;&nbsp;[" + editMap.get("sztickettypename").toString() + "]&nbsp;&nbsp;["
                                    + editMap.get("szcrowdkindname").toString() + "]&nbsp;&nbsp;" + editMap.get("numb").toString() + "张");
                        }
                    }
                    log.setIemployeeid(null);
                    log.setUsid(custom.getUsid());
                    log.setLogtype(Long.parseLong("0"));
                    log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
                    genericService.save(log);
                }
            }
        } catch (Exception e) {
            resultMap.put("status", 6);
            resultMap.put("message", "退订失败！");
            return resultMap;
        }

        resultMap.put("status", 0);
        resultMap.put("message", "退订成功！");
        return resultMap;
    }

    /**
     * 获取用户结算系统id和
     * @param params
     * @return
     */
    public Map<String, Object> getUserJsInfo(String params[]){
        String phone = params[0];
        Custom custom = ecService.getCustomByPhone(phone);
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();

        map.put("returnUrl","/android/goback/backSuccess.html");
        map.put("usid",custom.getUsid());

        resultMap.put("status", 0);
        resultMap.put("data", map);

        return resultMap;
    }


    /**
     * 获取银行卡信息并绑定
     * @param params
     * @return
     */
    @Deprecated
    public Map<String, Object> getUserBankList(String params[]){
        return null;
    }

    /**
     * 解绑旅游卡
     * @param params
     * @return
     */
    public Map<String, Object> jiebangUserBank(String [] params){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String phone = params[0];
        String pwd = params[1];
        String ubid = params[2];

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
            }
        }

        Custom custom = ecService.getCustomByPhone(phone);
        Map<String, String> merchant = sysService.findMerchant();
        SortedMap<String, Object> sortMap = new TreeMap<String, Object>();
       // sortMap.put("merchantId", 3);//merchant.get("merchantId")
        sortMap.put("legalPersonId", custom.getNote9());//结算系统用户id
        sortMap.put("bindAgrNo", ubid);//绑卡协议号

        //请求字符窜
        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = sortMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            sb.append(next.getKey());
            sb.append("=");
            sb.append(next.getValue());
            sb.append("&");
        }
        String sign2 = MD5Util.createSign(sortMap, merchant.get("key"),"UTF-8");
        sb.append("sign="+sign2);

        try{
            HqytClient client = new HqytClient();
            JSONObject jsonObject = client.legalPersonId(sb.toString());
            if("1".equals(jsonObject.getString("retCode"))){
                //ecService.deleteUserBank(ubid);//解绑成功删除数据
                resultMap.put("status", 0);
                resultMap.put("data", "解绑成功！");
                return resultMap;
            }
            resultMap.put("status", 4);
            resultMap.put("message", jsonObject.getString("retMsg"));
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", 4);
            resultMap.put("data", "解绑失败！");
            return resultMap;
        }


        return resultMap;
    }

    /**
     *  获取用户实名信息
     * @param params
     * @return
     */
    public Map<String, Object> getUserRealNameInfo(String[] params){
        return getUserRealNameInfoV2(params, false);

    }

    /**
     *  获取用户实名信息
     * @param params
     * @param isNewVersion 是否是新版本2.0
     * @return
     */
    public Map<String, Object> getUserRealNameInfoV2(String[] params, boolean isNewVersion){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String phone = params[0];
        String pwd = params[1];

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
                return resultMap;
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
                return resultMap;
            }
        }

        Custom custom = ecService.getCustomByPhone(phone);
        CustomRealName customRealName = (CustomRealName)genericService.get(CustomRealName.class, custom.getUsid());
        if(customRealName != null){
            customRealName.setDecryptedIdNumber(DesEncryptUtil.decrypt(customRealName.getIdNumber()));
            resultMap.put("status", 0);
            if(isNewVersion) {
                resultMap.put("data", customRealName);
            }else{//兼容app v1.0
                Map userbankMap = new HashMap();
                userbankMap.put("idname", customRealName.getRealName());
                userbankMap.put("idnumber", StringUtil.markIdNumber(DesEncryptUtil.decrypt(customRealName.getIdNumber()), 1, 1));
                resultMap.put("data",userbankMap);
            }
            return resultMap;
        }

        resultMap.put("status", 3);
        resultMap.put("message",  "没有查找到用户信息，请先实名认证！");
        return resultMap;

    }

    public Map<String, Object> getUserBankInfoList(String[] params){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SortedMap<String, Object> sortMap = new TreeMap<String, Object>();
        Map<String, String> merchant = sysService.findMerchant();
        String phone = params[0];
        String pwd = params[1];

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
                return resultMap;
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
                return resultMap;
            }
        }

        //去交易结算获取信息
        Custom custom = ecService.getCustomByPhone(phone);
        sortMap.put("merchantId", merchant.get("merchantId"));//merchant.get("merchantId")
        sortMap.put("legalPersonId", custom.getNote9() );//

        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = sortMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            sb.append(next.getKey());
            sb.append("=");
            sb.append(next.getValue());
            sb.append("&");
        }
        String sign2 = MD5Util.createSign(sortMap, merchant.get("key"),"UTF-8");
        sb.append("sign="+sign2);

        HqytClient client = new HqytClient();
        JSONArray jsonArray = client.qpaybindList(sb.toString());

        if(jsonArray!=null&&jsonArray.size()>0){
            resultMap.put("status", 0);
            resultMap.put("data",  jsonArray);
            return resultMap;
        }

        //判断用户是否实名
        CustomRealName customRealName = (CustomRealName)genericService.get(CustomRealName.class, custom.getUsid());
        if(customRealName != null){
            resultMap.put("status", 4);
            resultMap.put("message",  null);
            return resultMap;
        }

        resultMap.put("status", 3);
        resultMap.put("message",  "没有查找到用户绑卡信息，请先实名认证！");
        return resultMap;

    }

    /**
     * 保存银行卡绑定流水
     * @param params
     * @return
     */
    public Map<String, Object> saveUserBankFlow(String params[]){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String phone = params[0];
        String pwd = params[1];
        String value = params[2];

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
                return resultMap;
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
                return resultMap;
            }
        }

        //保存银行卡流水
        Custom custom = ecService.getCustomByPhone(phone);
        JSONObject jsonObject = JSONObject.parseObject(value);
        //判断银行卡是否已经绑定
//        String cardnumber = jsonObject.getString("accNo");
//        List<Userbank> userbanks = ecService.getUSerBankByNo(cardnumber);
//        if(userbanks!=null&&userbanks.size()>0){
//            resultMap.put("status", 3);
//            resultMap.put("message",  "该银行卡已绑定！");
//            return resultMap;
//        }
//        HqytClient client2 = new HqytClient();
//        SortedMap<String, Object> sortMap2 = new TreeMap<String, Object>();
//        sortMap2.put("merchantId", merchant.get("merchantId"));//merchant.get("merchantId")
//        sortMap2.put("legalPersonId", custom.getNote9() );//
//
//        StringBuffer sb = new StringBuffer();
//        Iterator<Map.Entry<String, Object>> iterator = sortMap.entrySet().iterator();
//        while (iterator.hasNext()){
//            Map.Entry<String, Object> next = iterator.next();
//            sb.append(next.getKey());
//            sb.append("=");
//            sb.append(next.getValue());
//            sb.append("&");
//        }
//        String sign2 = MD5Util.createSign(sortMap, merchant.get("key"),"UTF-8");
//        sb.append("sign="+sign2);
//
//        HqytClient client = new HqytClient();
//        JSONArray jsonArray = client.qpaybindList(sb.toString());


        Long flowid = 0L;
        String url = "";
        try{
            flowid = genericService.getSequenceId("USERBANKFLOW_ID");

            //调用交易结算获取绑定地址
            SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
            Map<String, String> merchant = sysService.findMerchant();
//            sortedMap.put("merchantId", merchant.get("merchantId"));
//            sortedMap.put("outTradeNo", flowid);
//            sortedMap.put("accNo", jsonObject.getString("accNo"));//银行卡号
//            sortedMap.put("customerNm", jsonObject.getString("customerNm"));//银行卡姓名
//            sortedMap.put("certifId", jsonObject.getString("certifId"));//身份证号
//            sortedMap.put("phoneNo", jsonObject.getLong("phoneNo"));//手机号
//            sortedMap.put("legalPersonId", custom.getNote9());//绑卡用户在结算系统id
//            sortedMap.put("notifyUrl", WebContant.GetKeyValue("DOMAIN")+"/js/userBankNotify.action");
//            sortedMap.put("returnUrl", WebContant.GetKeyValue("DOMAIN")+"/android/goback/backSuccess.html");
//
//            StringBuffer sb = new StringBuffer();
//            Iterator<Map.Entry<String, Object>> iterator = sortedMap.entrySet().iterator();
//            while (iterator.hasNext()){
//                Map.Entry<String, Object> next = iterator.next();
//                sb.append(next.getKey());
//                sb.append("=");
//                sb.append(next.getValue());
//                sb.append("&");
//            }
//            String sign2 = MD5Util.createSign(sortedMap, merchant.get("key"),"UTF-8");
//            sb.append("sign="+sign2);

            String certifId = jsonObject.getString("certifId");
            QueryPreBinding queryPreBinding = new QueryPreBinding();
            queryPreBinding.setMerchantId(merchant.get("merchantId"));
            queryPreBinding.setMerTxnNo(String.valueOf(flowid));
            queryPreBinding.setAccNo(jsonObject.getString("accNo"));
            queryPreBinding.setCustomerNm(jsonObject.getString("customerNm"));
            if(StringUtil.isNotEmpty(certifId) && certifId.contains("****************")) {//兼容app v1.0
                CustomRealName customRealName = (CustomRealName)genericService.get(CustomRealName.class, custom.getUsid());
                if(customRealName !=null) {
                    queryPreBinding.setCertifId(DesEncryptUtil.decrypt(customRealName.getIdNumber()));
                }
            }else{
                queryPreBinding.setCertifId(certifId);
            }
            queryPreBinding.setPhoneNo(jsonObject.getString("phoneNo"));
            queryPreBinding.setLegalPersonId(custom.getNote9());
            queryPreBinding.setNotifyUrl("http://"+WebContant.GetKeyValue("DOMAIN")+"/js/userBankNotify.action");//"http://"+WebContant.GetKeyValue("DOMAIN")+"/js/userBankNotify.action"
            queryPreBinding.setReturnUrl("http://"+WebContant.GetKeyValue("DOMAIN")+"/js/returnUrl.action");//"http://"+WebContant.GetKeyValue("DOMAIN")


            HqytClient client = new HqytClient();
            JSONObject jsonObject1 = client.qpayCardOpen(queryPreBinding);
            if(jsonObject1!=null&&jsonObject1.get("bindUrl")!=null){
                url = jsonObject1.getString("bindUrl");

                //保存流水信息
                UserbankFlow userbankFlow = new UserbankFlow();
                userbankFlow.setFlowid(flowid);
                userbankFlow.setUserid(custom.getUsid());
                userbankFlow.setIdnumber(DesEncryptUtil.encrypt(jsonObject.getString("certifId")));//身份证号
                userbankFlow.setIdname(jsonObject.getString("customerNm"));//姓名
                userbankFlow.setAdddate(new Timestamp(new Date().getTime()));
                genericService.save(userbankFlow);
            }else {
                resultMap.put("status", 5);
                resultMap.put("message",  jsonObject1.getString("retMsg"));
                return resultMap;
            }

        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", 4);
            resultMap.put("message",  "保存失败！");
            return resultMap;
        }

        resultMap.put("status", 0);
        resultMap.put("data", url);
        return resultMap;
    }

    /**
     * 银行卡卡BIN查询
     * @param params
     * @return
     */
    public Map<String, Object> getbankBIn(String[] params){

        Map<String, Object> resultMap = new HashMap<String, Object>();
        String phone = params[0];
        String pwd = params[1];
        String bankCardNo = params[2];

        //step1:判断是否有该用户
        {
            String status =  ecService.valilogin(phone,pwd);
            if("1".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "用户不存在");
                return resultMap;
            }
            if("2".equals(status)){
                resultMap.put("status", status);
                resultMap.put("message",  "密码错误");
                return resultMap;
            }
        }

        //结算获取信息
        Custom custom = ecService.getCustomByPhone(phone);
        Map<String, String> merchant = sysService.findMerchant();
        SortedMap<String, Object> sortMap = new TreeMap<String, Object>();
        sortMap.put("merchantId", merchant.get("merchantId"));//merchant.get("merchantId")
        sortMap.put("bankCardNo", bankCardNo );//

        StringBuffer sb = new StringBuffer();
        Iterator<Map.Entry<String, Object>> iterator = sortMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            sb.append(next.getKey());
            sb.append("=");
            sb.append(next.getValue());
            sb.append("&");
        }
        String sign2 = MD5Util.createSign(sortMap, merchant.get("key"),"UTF-8");
        sb.append("sign="+sign2);

        try{
            HqytClient client = new HqytClient();
            JSONObject jsonObject = client.qpayGetCardBank(sb.toString());
            if(jsonObject!=null&&jsonObject.size()>2){
                resultMap.put("status", 0);
                resultMap.put("data", jsonObject);
                return resultMap;
            }
            resultMap.put("status", 4);
            resultMap.put("message", jsonObject.getString("describe"));
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", 5);
            resultMap.put("message", "获取信息失败！");
            return resultMap;
        }
        return resultMap;

    }


    public List findOrderAll(String usid, String pwd, String orid,
                             String iscenictype, String ddzt) {

        List list = null;
        List list1 = null;
        List listzf = new ArrayList();

        StringBuffer sql = new StringBuffer("select distinct cast(m.ORID as varchar2(27)) as ORID, " +
                "                 t.ISCENICID ," +
                "                 t.SCENICTYPE ," +
                "                 t.ORFL ," +
                "                 m.USID ," +
                "                 t.IREGIONALID ," +
                "                 m.DDZT ," +
                "                 m.ORDA ," +
                "                 m.ORTI ," +
                "                 t.DTSTARTDATE ," +
                "                 t.DTENDDATE ," +
                "                 t.DTMAKEDATE ," +
                "                 t.DYUSID ," +
                "                 t.ORPH ," +
                "                 t.FAXNO ," +
                "                 m.MONT ," +
                "                 m.YHAMNT ," +
                "                 m.ZFMONT ," +
                "                 m.ISALLCP ," +
                "                 t.ISJFJF ," +
                "                 t.ISCHUPIAO," +
                "                 t.FEMPID ," +
                "                 t.FORCEDREFUND ," +
                "                 t.NOTEA ," +
                "                 t.ORNM ," +
                "                 t.ORZJ ," +
                "                 t.ORHM ," +
                "                 esb.SZSCENICNAME ," +
                "                 esb.SZADDRESS ," +
                "                 case when m.DDZT='27' then (select cast(m2.notec as nvarchar2(10)) from M_ORDER m2 where m2.srid=m.orid and rownum=1 ) else ssy.PMVA end as PMVA," +
                "                 m.ORDERSOURCE ," +
                "                 m.NOTEE ," +
                "                 '01' as productType," +
                "                 tor.NUMB as num ," +
                "                 tor.PRIC ," +
                "                 t.SCENICTYPE ," +
                "                 m.NOTEE ," +
                "                 eet.SZTICKETTYPENAME ," +
                "                 edp.SZCROWDKINDNAME ," +
                "                 (select u.upadder from Secenicpicture s,upfile u where s.upid=u.upid and s.iscenicid=esb.ISCENICID and (tor.orid is null or s.itickettypeid=tor.itickettypeid) and rownum=1) as upadder," +
                "                 (select u.upfilename from Secenicpicture s,upfile u where s.upid=u.upid and s.iscenicid=esb.ISCENICID and (tor.orid is null or s.itickettypeid=tor.itickettypeid) and rownum=1) as upfilename," +
                "                 tu.NAME " +
                "   from T_ORDER t" +
                "        join M_ORDER m on t.ORID = m.ORID and (m.ISDELETED=0 or m.ISDELETED is null) and (m.ddzt<>'00' or (m.ddzt='00' and m.dtmakedate>'" + DateUtils.formatDate(DateUtils.addMinute(new Date(), -29), DateUtils.DATETIME_PATTERN)+"'))"+ //未支付订单添加未超时条件(有1分钟偏差)
                "        join ESBSCENICAREATAB esb on esb.ISCENICID = t.ISCENICID " +
                "        join SYSPARV5 ssy on ssy.PMKY = 'DDZT' and ssy.PMCD = m.DDZT" +
                "        left join T_ORDERLIST tor on t.ORID = tor.ORID and tor.ISCENICID = t.ISCENICID" +
                "        left join EDMTICKETTYPETAB eet on tor.ITICKETTYPEID=eet.ITICKETTYPEID " +
                "        left join TOURCARDDETAIL tu on tu.CARDNUMBER=m.NOTEE" +
                "        left join EDMCROWDKINDPRICETAB edm on tor.ICROWDKINDPRICEID = edm.ICROWDKINDPRICEID" +
                "        left join EDPCROWDKINDTAB edp on edp.ICROWDKINDID = edm.ICROWDKINDID" +
                "  where 1=1 ");//放1=1是因为不想去判断第一个and，哈哈


		if (usid != null && !"".equals(usid.trim())) {
			sql.append(" and t.usid='" + usid + "'");
			//System.out.println("=======sql=========" + sql);
		} else {
			//System.out.println("=======sql=========" + sql);
			return null;
		}
		if (ddzt != null && !"".equals(ddzt.trim())&& !"''".equals(ddzt.trim())&&!"null".equals(ddzt)) {
			sql.append(" and m.ddzt='" + ddzt + "'");
		}else{//查询所有
			sql.append(" and m.ddzt in ('00', '02', '27', '30', '31', '33', '11')" );
		}
		if (orid != null && !"".equals(orid.trim())
				&& !"''".equals(orid.trim())) {
			sql.append(" and t.orid ='" + orid + "'");
		}
		if (iscenictype != null && !"".equals(iscenictype.trim())
				&& !"''".equals(iscenictype.trim())) {
			    //李进 读取所有订单；
				sql.append(" and t.scenictype='" + iscenictype + "'");
		}
		sql.append(" order by t.dtmakedate desc");

        try {
            list = ecService.getOrderLists(sql.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        if (!list.isEmpty() && list != null) {
            //System.out.println("======55554");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("======111");
                Map map = (Map) list.get(i);
                String zfsql = "select new map(t.orid as orid,t.usid as usid,t.payid as payid,t.mont as mont) from Zhifu t where t.usid='"
                        + map.get("usid").toString()+ "' and t.orid='"
                        + map.get("orid").toString() + "' order by t.seq desc";
                listzf = genericService.find(zfsql);
                //System.out.println("======2222");
                if (!listzf.isEmpty() && listzf != null) {
                    Map map1 = (Map) listzf.get(0);
                    map.put("zf", map1.get("payid"));
                }
                //System.out.println("======333333");
                if (map.get("orid") != null) {
                    List yclist = ecService.getYOrderChildList(map.get(
                            "orid").toString());
                    //System.out.println("======44444444");
                    //System.out.println("==yclist.size()===44444444"							+ yclist.size());
                    if (yclist != null && yclist.size() > 0) {

                        //System.out.println("ffdfdf"+map.get(								"orid").toString());

                        //System.out.println("======55555555555");
                        Map ymap = (Map) yclist.get(0);

                        String yddzt = ymap.get("ddzt") ==null?"" :ymap.get("ddzt").toString();
                        //System.out.println("yddzt"+yddzt);
                        if (yddzt .equals("") !=false)
                        {
                            List li = query("DDZT", " pmcd='" + yddzt + "'");
                            if (li != null && li.size() > 0) {
                                Map sys = (Map) query("DDZT",
                                        " pmcd='" + yddzt + "'").get(0);
                                //System.out.println("sys" + sys);
                                map.put("ddzt", yddzt);
                                map.put("pmva", sys.get("pmva").toString());
                            }
                        }
                        //System.out.println("yddzt65555555");
                    }
                }

                //退订的订单状态处理(除有旅游卡退订订单)
                if("27".equals(map.get("ddzt"))){
                    String refundddzt = (String)map.get("pmva");
                    String prefix = "lykgp".equals(map.get("ordersource")) ? "退订" : "退款";
                    map.put("refundddzt", refundddzt);
                    if("0".equals(refundddzt)){
                        map.put("pmva", prefix + "失败");
                    }else if("1".equals(refundddzt)){
                        map.put("pmva", prefix + "成功");
                    }else if("2".equals(refundddzt)){
                        map.put("pmva", prefix + "中");
                    }
                }
            }
        }
        //合并旅游卡订单(取消订单不添加)
        if (!"27".equals(ddzt)) {
            List tourCardOrderList = this.findTourCardOrder(usid, ddzt);
            if(tourCardOrderList != null && !tourCardOrderList.isEmpty()) {
                list.addAll(tourCardOrderList);
                //按时间排序
                Collections.sort(list, new Comparator() {
                    public int compare(Object o1, Object o2) {
                        Map map1 = (Map) o1;
                        Map map2 = (Map) o2;
                        String date1 = formatCreateDate(map1);
                        String date2 = formatCreateDate(map2);
                        if (date2 != null) {
                            return date2.compareTo(date1);
                        }
                        return -1;
                    }
                });
            }
        }

        return list;
    }

    public List query(String pmky, String pmcdwhere) {
        List list = new ArrayList();
        String hsql = "select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys where ";
        hsql += " sys.id.pmky='"+ pmky + "' and " + pmcdwhere;
        list = genericService.find(hsql);
        return list;
    }

    /**
     * 查找未失效的旅游卡订单列表
     * @param userId 用户ID
     * @param payStatusStr 原订单支付状态码
     * @return
     */
    public  List findTourCardOrder(String userId, String payStatusStr) {
        String imghost= WebContant.GetKeyValue("IMGHOST")+"/";
        String defaultimg = "http://"+ WebContant.GetKeyValue("DOMAIN")+"/images/tourcard/tourcard.jpg";
        if ("null".equalsIgnoreCase(payStatusStr)) {
            payStatusStr = null;
        }
        Long payStatus;
        if ("00".equals(payStatusStr)) {
            payStatus = 0L;
        } else if ("02".equals(payStatusStr)) {
            payStatus = 1L;
        } else {
            payStatus = -99L;
        }
        String merchantId = sysService.getMerchantId();
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("select tco.orderID as orid, tco.userId as usid, tco.createDate as dtmakedate, ");
        //不用case转，只会拿到第一个中文字符
        stringBuilder.append("case when tco.payStatus=1 then cast('已支付' as varchar2(6)) else cast('未付款' as varchar2(6)) end as pmva, '0'||tco.payStatus as ddzt,tco.price as pric, tco.price as zfmont, tco.cardName as szscenicname, cast('02' as varchar2(2)) as productType, 1 as num, ");
        stringBuilder.append("tcd.periodstartdate as periodstartdate, tcd.periodenddate as periodenddate, tco.username as username,");
        stringBuilder.append(merchantId);
        stringBuilder.append(" as merchantId, NVL2(tco.imageaddr,'"+imghost+"'||tco.imageaddr,'"+defaultimg+"') as imgurl");
        stringBuilder.append(" from TourCardOrder tco left join TourCardDetail tcd on tco.tourCardDetailId=tcd.id");
        stringBuilder.append(" where tco.userId=? and (tco.payStatus=? or ('ALL'=? and tco.payStatus in (0, 1))) and ((tco.payStatus=0 and tco.createDate>?) or tco.payStatus=1)");
        List list = null;
        try{
            list = genericService.findBySqlToMap(stringBuilder.toString(),
                userId, payStatus, StringUtil.isEmpty(payStatusStr) ? "ALL" : payStatus.toString(), DateUtils.addMinute(new Date(), -30));
            list = StringUtil.keyConvertToLowerCase(list, "productType", "merchantId");//
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    public String formatCreateDate(Map map){
        Object date = map.get("dtmakedate");
        if(date == null){
            date = map.get("DTMAKEDATE");
            if(date == null){
                return null;
            }
        }
        return date instanceof String ? (String)date: DateUtils.formatDate((Date)date, DateUtils.DATETIME_PATTERN);
    }


}
