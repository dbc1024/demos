package com.ectrip.tourcard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;

@Entity
@Table(name="TOURCARDDETAIL")
public class TourCardDetail {
	
	@Id
	private Long id;
	
	@Column
	private String code;	//旅游卡代码
	@Column
	private String cardNumber;	//旅游卡卡号
	@Column
	private String name;	//旅游卡名称
	@Column
	private Long historyCardFlag; //是否为历史卡：0不是，1是
	@Column
	private String bankCardNum;	//银行卡卡号
	@Column
	private String profitNum;	//分润协议号
	@Column
	private String username;	//姓名
	@Column
	private String userId;	//
	@Column
	private String identityNum;	//身份证号
	@Column
	private Double price;		//单价
	@Column
	private String organization;//单位/学校
	@Column
	private String workNum;		//工号/学号
	
	
	
	
	/*旅游卡有效期*/
	@Column
	private String periodStartDate;	//有效期开始日期
	@Column
	private String periodEndDate;	//有效期开始日期
	
	@Column
	private Long timesFlag;			//是否限定次数：不限制(-1)/限制（0）
	@Column
	private Long effectiveTimes;	//有效次数
	@Column
	private Long usedTimes;	//已使用次数
	@Column
	private Long leaveTimes;	//剩余次数
	@Column
	private String identityArea;	//身份信息归属地
	@Column
	private String scenics;		//四川-广元：明月峡、剑门关
	@Column
	private String descpt;		//说明
	@Column
	private Long status;	//删除-1，未开通0，已开通1，已过期未续费2，已过期已续费3,已过期但不能再续费4，如分润协议号停用，或旅游卡已下架.getStatus()有做特殊处理
	@Column
	private String createTime;	//创建时间
	@Column
	private String createMan;	//创建人
	@Column
	private String imageAddr;//图片地址
	@Column
	private Long writeoffstatus;//核销状态：0或者null：没有核销 1核销成功 2：核销失败
	@Column
	private Long jsid;//结算id
	@Column
	private String spare1;//备用字段
	@Column
	private String spare2;
	@Column
	private String spare3;

	//非数据库字段
	@Transient
	private String markedIdentityNum;//遮蔽显示的身份证号
	@Transient
	private String imageUrl;//图片完整地址

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getHistoryCardFlag() {
		return historyCardFlag;
	}

	public void setHistoryCardFlag(Long historyCardFlag) {
		this.historyCardFlag = historyCardFlag;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getProfitNum() {
		return profitNum;
	}

	public void setProfitNum(String profitNum) {
		this.profitNum = profitNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
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

	public Long getUsedTimes() {
		return usedTimes;
	}

	public void setUsedTimes(Long usedTimes) {
		this.usedTimes = usedTimes;
	}

	public Long getLeaveTimes() {
		return leaveTimes;
	}

	public void setLeaveTimes(Long leaveTimes) {
		this.leaveTimes = leaveTimes;
	}

	public String getIdentityArea() {
		return identityArea;
	}

	public void setIdentityArea(String identityArea) {
		this.identityArea = identityArea;
	}

	public String getScenics() {
		return scenics;
	}

	public void setScenics(String scenics) {
		this.scenics = scenics;
	}

	public String getDescpt() {
		return descpt;
	}

	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

	public Long getStatus() {
		//状态验证
		if(status != null && status == 1L) {
			//有效次数用完，或已过期
			if ((timesFlag != null && leaveTimes != null && timesFlag == 0L && leaveTimes <= 0) || (periodEndDate != null && Tools.getDays().compareTo(periodEndDate) > 0)) {
				status = 2L;
			}
		}
		if(status != null && status == 2L) {
			//用户退订了门票，可使用次数+1后，旅游卡可能并未过期
			if (timesFlag != null && leaveTimes != null && timesFlag == 0L && leaveTimes > 0 && (periodEndDate != null && Tools.getDays().compareTo(periodEndDate) <= 0)) {
				status = 1L;
			}
		}
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public Long getTimesFlag() {
		return timesFlag;
	}

	public void setTimesFlag(Long timesFlag) {
		this.timesFlag = timesFlag;
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

	public Long getWriteoffstatus() {
		return writeoffstatus;
	}

	public void setWriteoffstatus(Long writeoffstatus) {
		this.writeoffstatus = writeoffstatus;
	}

	public String getSpare1() {
		return spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	public String getSpare2() {
		return spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

	public String getSpare3() {
		return spare3;
	}

	public void setSpare3(String spare3) {
		this.spare3 = spare3;
	}

	public Long getJsid() {
		return jsid;
	}

	public void setJsid(Long jsid) {
		this.jsid = jsid;
	}
}
