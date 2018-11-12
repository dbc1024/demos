package com.ectrip.tourcard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.WebContant;

@Entity
@Table(name="TOURCARDORDER")
public class TourCardOrder {
	
	@Id
	private String orderID;			//单号，本系统编码
	@Column
	private String tradeNo;			//订单编号，由结算系统生成
	@Transient
	private String outTradeNo;		//同orderId
	@Column
	private Date createDate;		//创建时间
	@Column
	private String userId;	    //买家账号
	@Column
	private String tourCommission;	//旅发委发行
	@Column
	private String cardCode;		//旅游卡代码
	@Column
	private String cardName;		//旅游卡名称
	@Column
	private String cardNum;		    //旅游卡卡号
	@Column
	private String profitNum;	    //分润协议号
	@Column
	private String userName;	    //姓名
	@Column
	private String identityNum;		//身份证
	@Column
	private String mobileNum;		    //手机号
	@Column
	private String bankCardPublishAddr;	//银行卡发行
	@Column
	private String bankCardBelongAddr;	//银行卡归属地
	@Column
	private String identityNumAddr;	//身份证信息归属地
	@Column
	private Long periodType;		//有效期类型:按年（0），按月（1）
	@Column
	private Long periodNumber;		//有效期数量：1年/1月
	@Column
	private String periodStartDate;   //有效期开始日期
	@Column
	private String periodEndDate;		//有效期结束日期
	@Column
	private Long timesFlag;			//是否限定次数：不限制(-1)/限制（0）
	@Column
	private Long effectiveTimes;  //有效次数
	@Column
	private Double price;           //单价
	@Column(name="ISCENICIDS")
	private String isSenicids;      //可使用景区
	@Transient
	private String scenicName;      //可使用景区名
	@Column
	private Long payStatus;         //支付状态 1 已支付；0 未支付, -1 删除
	@Column
	private Long openingStatus;     //开通状态 -1 未开通；0 正常（已开通）；1 已过期
	@Column
	private String descpt;     //说明

	@Transient
	private String csti;//有效开始日期
	@Transient
	private String ceti;//有效结束日期

	@Transient
	private String fsti;//创建开始日期
	@Transient
	private String feti;//创建结束日期

	@Column
	private Long tourCardDetailId; //TourCardDetail.id
	@Column
	private String imageAddr;//图片地址
	@Column
	private Long jsid;//结算id

	//非数据库字段
	//@Transient 加这个注解，JOSN.toJSONString 报JSONException: create asm serializer error
	@Transient
	private String merchantId; //APP平台的商户ID
	@Transient
	private Long expiringTime;//订单过期倒计时时间，单位秒
	@Transient
	private String payStatusDesc;
	@Transient
	private String markedIdentityNum;//遮蔽显示的身份证号
	@Transient
	private TourCard tourCard;
	@Transient
	private String imageUrl;//图片完整地址

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTourCommission() {
		return tourCommission;
	}

	public void setTourCommission(String tourCommission) {
		this.tourCommission = tourCommission;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getProfitNum() {
		return profitNum;
	}

	public void setProfitNum(String profitNum) {
		this.profitNum = profitNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}


	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getBankCardPublishAddr() {
		return bankCardPublishAddr;
	}

	public void setBankCardPublishAddr(String bankCardPublishAddr) {
		this.bankCardPublishAddr = bankCardPublishAddr;
	}

	public String getBankCardBelongAddr() {
		return bankCardBelongAddr;
	}

	public void setBankCardBelongAddr(String bankCardBelongAddr) {
		this.bankCardBelongAddr = bankCardBelongAddr;
	}

	public String getIdentityNumAddr() {
		return identityNumAddr;
	}

	public void setIdentityNumAddr(String identityNumAddr) {
		this.identityNumAddr = identityNumAddr;
	}

	public String getPeriodStartDate() {
		return periodStartDate;
	}

	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	public String getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	public Long getEffectiveTimes() {
		return effectiveTimes;
	}

	public void setEffectiveTimes(Long effectiveTimes) {
		this.effectiveTimes = effectiveTimes;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsSenicids() {
		return isSenicids;
	}

	public String getScenicName() {
		return scenicName;
	}

	public void setScenicName(String scenicName) {
		this.scenicName = scenicName;
	}

	public void setIsSenicids(String isSenicids) {
		this.isSenicids = isSenicids;
	}

	public Long getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Long payStatus) {
		this.payStatus = payStatus;
	}

	public Long getOpeningStatus() {
		return openingStatus;
	}

	public void setOpeningStatus(Long openingStatus) {
		this.openingStatus = openingStatus;
	}

	public String getDescpt() {
		return descpt;
	}

	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

	public String getCsti() {
		return csti;
	}

	public void setCsti(String csti) {
		this.csti = csti;
	}

	public String getCeti() {
		return ceti;
	}

	public void setCeti(String ceti) {
		this.ceti = ceti;
	}

	public String getFsti() {
		return fsti;
	}

	public void setFsti(String fsti) {
		this.fsti = fsti;
	}

	public String getFeti() {
		return feti;
	}

	public void setFeti(String feti) {
		this.feti = feti;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Long getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Long periodType) {
		this.periodType = periodType;
	}

	public Long getPeriodNumber() {
		return periodNumber;
	}

	public void setPeriodNumber(Long periodNumber) {
		this.periodNumber = periodNumber;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getTimesFlag() {
		return timesFlag;
	}

	public void setTimesFlag(Long timesFlag) {
		this.timesFlag = timesFlag;
	}

	public Long getTourCardDetailId() {
		return tourCardDetailId;
	}

	public void setTourCardDetailId(Long tourCardDetailId) {
		this.tourCardDetailId = tourCardDetailId;
	}

	public Long getExpiringTime() {
		return expiringTime;
	}

	public void setExpiringTime(Long expiringTime) {
		this.expiringTime = expiringTime;
	}

	public TourCard getTourCard() {
		return tourCard;
	}

	public void setTourCard(TourCard tourCard) {
		this.tourCard = tourCard;
	}

	public String getPayStatusDesc() {
		if(payStatus == 0L){
			return "未支付";
		}else if(payStatus == 1L){
			return "已支付";
		}
		return "";
	}

	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	public String getMarkedIdentityNum() {
		return markedIdentityNum;
	}

	public void setMarkedIdentityNum(String markedIdentityNum) {
		this.markedIdentityNum = markedIdentityNum;
	}

	public String getImageAddr() {
		return imageAddr;
	}

	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}

	public String getImageUrl() {
		if(StringUtil.isEmpty(this.imageAddr)){
			return "";
		}else {
			return WebContant.GetKeyValue("IMGHOST") + "/" + this.imageAddr;
		}
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getJsid() {
		return jsid;
	}

	public void setJsid(Long jsid) {
		this.jsid = jsid;
	}
}
