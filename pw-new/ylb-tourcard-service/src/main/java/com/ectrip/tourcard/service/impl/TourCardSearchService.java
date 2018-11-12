package com.ectrip.tourcard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.JSONUtil;
import com.ectrip.base.util.PinYinUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.EcServiceApi;
import com.ectrip.ec.model.app.BaseModel;
import com.ectrip.ec.model.app.ObjectParse;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.CustomRealName;
import com.ectrip.ec.model.user.Userbank;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.enums.ErrorMessage;
import com.ectrip.hqyt.model.enums.InvoiceStatus;
import com.ectrip.hqyt.model.request.GuaranteeRequest;
import com.ectrip.hqyt.model.request.LegalPersonRequest;
import com.ectrip.hqyt.model.request.ProductRequest;
import com.ectrip.hqyt.model.request.QueryOrderRequest;
import com.ectrip.hqyt.model.response.JSONInvoice;
import com.ectrip.sys.SysparServiceApi;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.tourcard.dao.IPassPeopleDAO;
import com.ectrip.tourcard.dao.ITourCardDAO;
import com.ectrip.tourcard.dao.ITourCardDetailDAO;
import com.ectrip.tourcard.dao.ITourCardOrderDAO;
import com.ectrip.tourcard.dao.ITourCardSearchDAO;
import com.ectrip.tourcard.model.PassPeople;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;
import com.ectrip.tourcard.model.TourCardOrder;
import com.ectrip.tourcard.service.ITourCardSearchService;

public class TourCardSearchService extends GenericService implements ITourCardSearchService{

    private static Logger logger = LoggerFactory.getLogger(TourCardSearchService.class);

    private ITourCardSearchDAO tourCardSearchDAO;
    private ITourCardDAO tourCardDAO;
    private ITourCardOrderDAO tourCardOrderDAO;
    private ITourCardDetailDAO tourCardDetailDAO;
    
    
    @Autowired
    private EcServiceApi ecServiceApi;
//  private ICustomRealNameDAO customRealNameDAO;
//  private ICustomDAO customDAO;
//  private IUserbankDAO userbankDAO;
    

    
    @Autowired
    private SysparServiceApi sysparServiceApi;
//    private IGalsourceregiontabDAO galsourceregiontabDAO;
    

    @Autowired
    private IPassPeopleDAO passPeopleDAO;

    /**
     * 301 旅游卡列表
     * @param userId 电话号码
     * @return
     * @throws Exception
     */
    public String getTourCardList(String userId) throws Exception{
        Custom custom = ecServiceApi.findByMobile(userId);
        BaseModel model = new BaseModel();
        model.setLogonstatus("1");
        model.setUsid(userId);
        model.setNodes(tourCardSearchDAO.getTourCardList(custom == null ? "" : custom.getUsid()));
        ObjectParse parse = new ObjectParse();
        return parse.ObjectToXml(model);
    }

    /**
     * 302 旅游卡详情
     * @param userId 电话号码
     * @param cardId 旅游卡ID
     * @return
     * @throws Exception
     */
    public String getTourCard(String userId, Long cardId) throws Exception {
        TourCard tourCard;
        try {
            String imghost= WebContant.GetKeyValue("IMGHOST")+"/";
            String defaultPath =  "http://"+ WebContant.GetKeyValue("DOMAIN")+"/images/tourcard/tourcard.jpg";
            tourCard = (TourCard) tourCardDAO.get(TourCard.class, cardId);
            String imageAddr = tourCard.getImageAddr();

            if(imageAddr!=null&&!"".equals(imageAddr)) {
                tourCard.setImageUrl(imghost+tourCard.getImageAddr());
            }else{
                tourCard.setImageUrl(defaultPath);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(tourCard);
    }

    /**
     * 303 创建旅游卡订单
     * @param userId 电话号码
     * @param cardId 旅游卡ID
     * @param tourCardDetailId 旅游卡明细ID (续费时传)
     * @return
     * @throws Exception
     */
    public String saveTourCardOrder(String userId, Long cardId, Long tourCardDetailId) {
        TourCardOrder order = new TourCardOrder();
        try {
            Date today = new Date();
            Custom custom = ecServiceApi.findByMobile(userId);
            CustomRealName customRealName = ecServiceApi.get(custom.getUsid());
            TourCard tourCard = (TourCard) tourCardDAO.get(TourCard.class, cardId);
            HqytClient client = new HqytClient();
            JSONInvoice invoice = null;
            GuaranteeRequest request = new GuaranteeRequest();

            //检查旅游卡是否已下架
            if(tourCard.getStatus() != 1L){
                if(tourCardDetailId != null && tourCardDetailId != 0L){
                    TourCardDetail detail = (TourCardDetail)tourCardDetailDAO.get(TourCardDetail.class, tourCardDetailId);
                    if(detail != null) {
                        detail.setStatus(4L);
                        tourCardDetailDAO.saveOrUpdate(detail);
                    }
                }
                return JSONUtil.toErrorJsonStr("该旅游卡已经停用，请重新选择旅游卡");
            }

            //判断是否实名制
            if(customRealName==null||customRealName.getIdNumber()==null||"".equals(customRealName.getIdNumber())){
                return JSONUtil.toErrorJsonStr("亲~购买旅游卡请先实名认证");
            }

//            //根据身份证号前6位检查身份证购买权限
//            if((tourCard.getIdentityCardFlag() == 0L && !this.checkIdentityPermission(tourCard.getIdentityCardIds(), DesEncryptUtil.decrypt(customRealName.getIdNumber())))){//有身份证购买限制
//                return JSONUtil.toErrorJsonStr("您的身份证信息未满足开通旅游卡条件，请重新选择旅游卡");
//            }

            //根据身份证号前6位检查身份证购买权限  (checkPassPeopleId  检查用户是否为有特殊购买权限的用户)
            if((tourCard.getIdentityCardFlag() == 0L && !this.checkIdentityPermission(tourCard.getIdentityCardIds(), DesEncryptUtil.decrypt(customRealName.getIdNumber())))
                    &&!checkPassPeopleId(customRealName.getIdNumber(), tourCard.getCode())){//有身份证购买限制
                return JSONUtil.toErrorJsonStr("您的身份证信息未满足开通旅游卡条件，请重新选择旅游卡");
            }

            //获取已存在有效的未支付订单
            TourCardOrder existOrder = tourCardOrderDAO.getUnpayOrder(custom.getUsid(), tourCard.getCode());
            //String merchantId = CommonUtil.getMerchantId();
            String merchantId = sysparServiceApi.getMerchantId();
            if(existOrder != null){
                if(existOrder.getPayStatus() == 0L){//未支付的订单去结算系统核实一下支付状态
                    QueryOrderRequest queryOrderRequest = new QueryOrderRequest();
                    queryOrderRequest.setOrid(existOrder.getOrderID());
                    invoice = client.queryOrder(queryOrderRequest);
                    InvoiceStatus invoiceStatus = invoice != null ? invoice.getStatus() : null;
                    if(invoiceStatus != null && InvoiceStatus.SUCCESS.getText().equals(invoiceStatus.getText()) || InvoiceStatus.TOBECONFIRM.getText().equals(invoiceStatus.getText())){
                        this.updateTourCardOrder(existOrder.getOrderID());
                        return JSONUtil.toErrorJsonStr("该订单已支付");
                    }
                }
                order = existOrder;
            }else {//新建订单

                String orid = this.getMaxNo("");

                //step1结算系统订单创建
                request.setOrid(orid);
                request.setUsid(custom.getUsid());
                request.setTotalMoney(tourCard.getPrice());
                request.setUsername(custom.getUsername());
                request.setPhone(custom.getMobile());

                List<ProductRequest> products = new ArrayList<ProductRequest>();
                ProductRequest productRequest = new ProductRequest();
                productRequest.setName(tourCard.getName());
                productRequest.setPrice(tourCard.getPrice());
                productRequest.setNumb(1L);
                productRequest.setExternalId(tourCard.getId());
                products.add(productRequest);

                request.setProducts(products);
                request.setConsumeTimeLimit(DateUtils.formatDate(DateUtils.addMinute(today, 30)));//半小时内未付款，订单失效

                LegalPersonRequest receiver = new LegalPersonRequest();
                if (StringUtil.isEmpty(custom.getNote9())) {
                    return JSONUtil.toErrorJsonStr("用户没有同步结算系统的用户ID");
                }
                receiver.setId(Long.valueOf(custom.getNote9()));
                request.setReceiver(receiver);
                request.setIssuerId(merchantId);//购买旅游卡使用平台ID，不用景区服务商ID
                request.setSplited("true");
                request.setSplitAgreementNo(tourCard.getProfitNum());
                request.setTourCardCode(tourCard.getCode());
                request.setTourCardName(tourCard.getName());
                request.setTourCardNo(orid);

                //调用结算系统
                try {
                    invoice = client.appGuaranteeSk(request);
                }catch (Exception e){
                    String ergMsg = e.getMessage();
                    logger.error(ergMsg, e);
                    if(StringUtil.denull(ergMsg).contains("停用"))
                        return JSONUtil.toErrorJsonStr("该旅游卡已经停用，请重新选择旅游卡");
                    else
                        return JSONUtil.toErrorJsonStr(e.getMessage());
                }
                logger.debug(JSON.toJSONString(invoice));

                //step2 创建业务系统订单数据
                if (invoice == null && StringUtil.isEmpty(invoice.getTradeNo())) {
                    return JSONUtil.toErrorJsonStr(ErrorMessage.ORDER_CREATE_FAIL.getText());
                }
                logger.debug("maxNo:" + orid);
                order.setOrderID(orid);
                order.setTradeNo(invoice.getTradeNo());
                order.setCreateDate(new Date());
                order.setUserId(custom.getUsid());
                order.setUserName(customRealName.getRealName());
                order.setIdentityNum(customRealName.getIdNumber());
                order.setMobileNum(custom.getMobile());
                order.setTourCommission(tourCard.getTourCommission());
                order.setCardCode(tourCard.getCode());
                order.setCardName(tourCard.getName());
                order.setProfitNum(tourCard.getProfitNum());
                order.setPeriodType(tourCard.getPeriodType());
                order.setPeriodNumber(tourCard.getPeriodNumber());
                order.setTimesFlag(tourCard.getTimesFlag());
                if(tourCard.getTimesFlag() == -1L) {
                    order.setEffectiveTimes(0L);
                }else{
                    order.setEffectiveTimes(tourCard.getEffectiveTimes());

                }
                order.setPrice(tourCard.getPrice());
                order.setIsSenicids(tourCard.getScenics());
                order.setPayStatus(0L);
                order.setOpeningStatus(-1L);
                order.setDescpt(tourCard.getDescpt());
                order.setImageAddr(tourCard.getImageAddr());
                order.setTourCardDetailId(tourCardDetailId);
                order.setJsid(invoice.getId());

                tourCardOrderDAO.save(order);
            }
            order.setMerchantId(merchantId);
            order.setOutTradeNo(order.getOrderID());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr(ErrorMessage.UNKNOWN_ERROR.getText());
        }
        String res = JSONUtil.toSuccessJsonStr(order);
        logger.debug(res);
        return res;
    }

    /**
     * 用户是否有身份证权限
     * @param idNumber
     * @return false 没有权限  true 有权限
     */
    public boolean checkPassPeopleId(String idNumber, String code){
        boolean b = false;
        String idNumbers = DesEncryptUtil.decrypt(idNumber);
        PassPeople passPeople =  passPeopleDAO.getPassPeopleByIdAndCode(idNumbers, code);
        if(passPeople!=null&&passPeople.getId()!=null){
            b = true;
        }
        return b;
    }

    /**
     * 304 更新订单信息及支付状态
     * @param orderId 订单编号
     * @return
     * @throws Exception
     */
    public TourCardDetail updateTourCardOrder(String orderId) {
        TourCardOrder order;
        TourCardDetail detail = new TourCardDetail();
        try {
            order = (TourCardOrder) tourCardOrderDAO.get(TourCardOrder.class, orderId);
            Custom custom = ecServiceApi.findByMobile(order.getUserId());
            CustomRealName customRealName = ecServiceApi.get(order.getUserId());

            //防止重复创建旅游卡明细
            if(order.getPayStatus() == 1L && order.getTourCardDetailId() != null && order.getTourCardDetailId() != 0L){
                return (TourCardDetail)tourCardDetailDAO.get(TourCardDetail.class, order.getTourCardDetailId());
            }

            //续费旅游卡，更新旧旅游卡明细状态
            if(order.getTourCardDetailId() != null && order.getTourCardDetailId() != 0L){//续费
                logger.info("旧旅游卡明细ID ： " + order.getTourCardDetailId());
                TourCardDetail oldDetail = (TourCardDetail)tourCardDetailDAO.get(TourCardDetail.class, order.getTourCardDetailId());
                oldDetail.setStatus(3L);//已过期已续费
                tourCardDetailDAO.saveOrUpdate(oldDetail);
            }

            //生成新的旅游卡明细
            if (orderId != null && orderId.trim().length() > 0) {
                Date today = new Date();
                order.setPeriodStartDate(Tools.getDays());
                if(order.getPeriodType() == null){}
                else if(order.getPeriodType() == 0L) {//加年数
                    order.setPeriodEndDate(DateUtils.formatDate(DateUtils.addDay(DateUtils.addYear(today, order.getPeriodNumber().intValue()), -1)));
                }else if(order.getPeriodType() == 1L){//加月数
                    order.setPeriodEndDate(DateUtils.formatDate(DateUtils.addDay(DateUtils.addMonth(today, order.getPeriodNumber().intValue()), -1)));
                }else if(order.getPeriodType() == 2L){//加天数
                    order.setPeriodEndDate(DateUtils.formatDate(DateUtils.addDay(DateUtils.addDay(today, order.getPeriodNumber().intValue()), -1)));
                }
                order.setPayStatus(1L);//已支付
                order.setOpeningStatus(0L);//已开通
                order.setCardNum(PinYinUtil.getFirst2PinYinHeadChar(order.getCardName()) + orderId);//旅游卡卡号
                order.setTourCardDetailId(tourCardDetailDAO.getMaxPk("id", "TourCardDetail") + 1);

                //setp3.创建旅游卡明细
                detail.setId(order.getTourCardDetailId());
                detail.setCode(order.getCardCode());
                detail.setCardNumber(order.getCardNum());
                detail.setName(order.getCardName());
                detail.setHistoryCardFlag(0L);
                detail.setProfitNum(order.getProfitNum());
                detail.setUsername(order.getUserName());
                detail.setUserId(order.getUserId());
                detail.setIdentityNum(order.getIdentityNum());
                detail.setPrice(order.getPrice());
                detail.setPeriodStartDate(order.getPeriodStartDate());
                detail.setPeriodEndDate(order.getPeriodEndDate());
                detail.setTimesFlag(order.getTimesFlag());
                detail.setUsedTimes(0L);
                if(order.getTimesFlag() == -1L) {
                    detail.setEffectiveTimes(0L);
                    detail.setLeaveTimes(0L);
                }else{
                    detail.setEffectiveTimes(order.getEffectiveTimes());
                    detail.setLeaveTimes(order.getEffectiveTimes());
                }
                detail.setScenics(order.getIsSenicids());
                detail.setDescpt(order.getDescpt());
                detail.setImageAddr(order.getImageAddr());
                detail.setStatus(1L);
                detail.setCreateTime(DateUtils.getTodayStr());
                detail.setJsid(order.getJsid());

                tourCardOrderDAO.saveOrUpdate(order);
                tourCardDetailDAO.save(detail);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
        return detail;
    }


    /**
     * 305 是否存在历史旅游卡
     * @param mobile 手机号
     * @param cardCode 旅游卡代码
     * @return 旅游卡明细ID
     */
    @Deprecated//客户端unused
    public String isAlreadyExistHisoryTourCard(String mobile, String cardCode) throws Exception{
        Custom custom = ecServiceApi.findByMobile(mobile);
        CustomRealName customRealName = ecServiceApi.get(custom.getUsid());
        if(customRealName == null){
            return JSONUtil.toErrorJsonStr("该用户没有实名");
        }
        TourCardDetail detail = this.tourCardDetailDAO.getTourCardDetail(cardCode, customRealName.getIdNumber());
        if(detail == null){
            return JSONUtil.toErrorJsonStr("该用户不存在历史旅游卡");
        }else{
            return JSONUtil.toSuccessJsonStr(detail.getId());
        }
    }

    /**
     * 306 绑定历史旅游卡
     * @param tourCardDetailId 旅游卡明细ID
     * @param mobile 用户手机号
     * @return
     */
    public String bindingHistoryTourCard(String tourCardDetailId, String mobile) throws Exception{
        TourCardDetail detail;
        try {
            detail = (TourCardDetail) this.get(TourCardDetail.class, Long.valueOf(tourCardDetailId));
            Custom custom = ecServiceApi.findByMobile(mobile);

            detail.setUserId(custom.getUsid());
            detail.setStatus(1L);//开通
            tourCardDetailDAO.saveOrUpdate(detail);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr("绑定历史旅游卡失败");
        }
        return JSONUtil.toSuccessJsonStr(detail.getCardNumber());
    }

    /**
     * 307 获取用户已开通的旅游卡列表
     * @param mobile 手机号
     * @return
     */
    public String getUserTourCardDetails(String mobile){
        List<TourCardDetail> details;
        try {
            Custom custom = ecServiceApi.findByMobile(mobile);
            details = tourCardDetailDAO.getUserTourCardDetails(custom.getUsid());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(details);
    }

    /**
     * 308 旅游卡详情 (点击续费时)
     * @param tourCardDetailId 旅游卡明细ID
     * @return
     */
    public String getTourCardByCardCode(String tourCardDetailId){
        TourCard tourCard;
        try {
            TourCardDetail detail = (TourCardDetail)tourCardDetailDAO.get(TourCardDetail.class, Long.valueOf(tourCardDetailId));
            tourCard = tourCardDAO.getTourCardByCardCode(detail.getCode());
            if(tourCard == null || tourCard.getStatus() != 1L){
                detail.setStatus(4L);
                tourCardDetailDAO.saveOrUpdate(detail);
                return JSONUtil.toErrorJsonStr("该旅游卡已经停用，请重新选择旅游卡");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(tourCard);
    }


    /**
     * 309 检验用户是否是已实名的用户
     * @param mobile 手机号
     * @return true 已实名， false 未实名
     */
    public String isRealNameUser(String mobile){
        try {
            Custom custom = ecServiceApi.findByMobile(mobile);
            CustomRealName customRealName = ecServiceApi.get(custom.getUsid());
            Userbank userbank = ecServiceApi.findUserBank(custom.getUsid());
            if(userbank == null && customRealName == null) {//userbank for app v1.0,  customRealName for app v2.0
                return JSONUtil.toSuccessJsonStr("false");
            }else{
                return JSONUtil.toSuccessJsonStr("true");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
    }

    /**
     * 310 存在的可绑定历史旅游卡列表
     * @param mobile 手机号
     * @return 历史旅游卡列表
     */
    public String getHistoryTourCards(String mobile){
        List<TourCardDetail> list = new ArrayList<TourCardDetail>();
        try {
            Custom custom = ecServiceApi.findByMobile(mobile);
            CustomRealName customRealName = ecServiceApi.get(custom.getUsid());
            if(customRealName == null){
                return JSONUtil.toErrorJsonStr("用户未实名");
            }
            list = tourCardDetailDAO.getHistoryTourCardDetails(customRealName.getIdNumber());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(list);
    }

    /**
     * 311 获取旅游卡订单详情
     * @param orderId 订单号
     * @return
     */
    public String getTourCardOrder(String orderId){
        TourCardOrder order = null;
        try {
            order = (TourCardOrder)tourCardOrderDAO.get(TourCardOrder.class, orderId);
            TourCard tourCard = tourCardDAO.getTourCardByCardCode(order.getCardCode());
            if(order != null){
//                order.setTourCard(tourCard);
                //order.setMerchantId(CommonUtil.getMerchantId());
                order.setMerchantId(sysparServiceApi.getMerchantId());
                order.setExpiringTime(this.getOrderExpiringTime(order.getCreateDate()));
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(order);
    }

    /**
     * 312 获取已过期旅游卡列表
     * @param mobile
     * @return
     */
    public String getExpiredTourCardDetailList(String mobile){
        List<TourCardDetail> details;
        try {
            Custom custom = ecServiceApi.findByMobile(mobile);
            details = tourCardDetailDAO.getUserExpiredTourCardDetails(custom.getUsid());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(details);
    }

    /**
     * 313 获取我的旅游卡列表 for APP v2.0
     * @param mobile
     * @return
     */
    public String getUserAllTourCardDetails(String mobile){
        List<TourCardDetail> details;
        try {
            Custom custom = ecServiceApi.findByMobile(mobile);
            details = tourCardDetailDAO.getUserAllTourCardDetails(custom.getUsid());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return JSONUtil.toErrorJsonStr(ErrorMessage.DATA_REQ_FAIL.getText());
        }
        return JSONUtil.toSuccessJsonStr(details);
    }

    /**
     * 校验身份证购买权限
     * @param areaIds
     * @param identityNum
     * @return
     */
    private boolean checkIdentityPermission(String areaIds, String identityNum){
        boolean result = true;
//        if(tourCard.getIdentityCardFlag() == 0L){//有身份证购买限制
            String errMsg = "您的身份证信息未满足购买旅游卡条件，请重新选择旅游卡";
            if(areaIds == null){
                return true;
            }
            String[] areaIdArr = areaIds.split(",");
            if(areaIdArr != null && areaIdArr.length > 0){
                String userAreaCode = identityNum.substring(0,6);
                Galsourceregiontab userAreaId = sysparServiceApi.findByCode(userAreaCode);
                Map<String,Object> areasMap = new HashMap<String,Object>();
                areasMap.put("areas", areaIdArr);
                List<Galsourceregiontab> areas = sysparServiceApi.findByIds(JSON.toJSONString(areasMap));
                Map<String, Galsourceregiontab> map= new HashMap<String, Galsourceregiontab>();
                if(areas != null && !areas.isEmpty()){
                    for(Galsourceregiontab area : areas){
                        String[] innercodes = area.getSzinnercode().split(",");
                        if(innercodes.length > 1 && map.containsKey(innercodes[innercodes.length - 2])){//取父节点的code
                            Galsourceregiontab parentArea = map.get(innercodes[innercodes.length - 2]);
                            parentArea.getChildIds().add(area.getSzregionalcode());
                        }
                        map.put(area.getSzregionalcode(), area);
                    }
                    if(map.get("1") == null){//非中国地区
                        result = false;//不满足条件
                    }
                    if(!map.get("1").getChildIds().isEmpty()) {
                        //针对中国地区
                        Galsourceregiontab area = map.get(userAreaCode.substring(0, 2));
                        if (area == null) {//省级
                            result = false;//不满足条件
                        } else if (!area.getChildIds().isEmpty()) {
                            area = map.get(userAreaCode.substring(0, 4));
                            if (area == null) {//市级
                                result = false;//不满足条件
                            } else if (!area.getChildIds().isEmpty()) {
                                area = map.get(userAreaCode.substring(0, 6));
                                if (area == null) {//区县级
                                    result = false;//不满足条件
                                }
                            }
                        }
                    }//else 权限只到中国
                }
            }else{
                result = false;
            }
//        }
        return result;
    }

    /**
     * 订单过期剩余时间
     * @param orderCreateDate 订单创建时间
     * @return
     */
    public Long getOrderExpiringTime(Date orderCreateDate){
        return (30-1)*60 - (new Date().getTime() - orderCreateDate.getTime())/1000;//30分钟倒计时(注：不-1会出现30:59秒的超时情况)
    }

	@Override
	public List<TourCard> getTourCard(String userId) {
		return tourCardSearchDAO.getTourCard(userId);
	}

	@Override
	public TourCardDetail getTourCarDetail(Object carNo) {
		return tourCardSearchDAO.getTourCarDetail(carNo);
	}

}
