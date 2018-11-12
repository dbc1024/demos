package com.ectrip.tourcard.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="TOURCARD")
public class TourCard implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column
	private String code;			//旅游卡代码
	
	@Column
	private String name;			//旅游卡名称

	@Column
	private String tourCommission;	//旅发委
	
	@Column
	private String profitNum;		//分润协议号
	
	@Column
	private Double price;			//单价
	
	@Column
	private String iscenicids;		//可使用景区（id）：",1,3,56,89,"
	
	@Column
	private String scenics;		//四川-广元：明月峡、剑门关
	
	@Column
	private Long bankCardFlag;	//银行卡购买权限：不限（-1）
	
	@Column
	private Long identityCardFlag;	//身份信息购买权限:不限制(-1)/限制(0)
	@Column
	private String identityCardIds;		//可购买地区id:"1,541,2541,1201,45"
	@Column
	private String identityCardAreas;	//可购买地区名称
	
	/*旅游卡有效期*/
	@Column
	private Long periodType;		//有效期类型:按年（0），按月（1），按天（2）
	@Column
	private Long periodNumber;		//有效期数量：1年/1月
	@Column
	private String periodStartDate;	//多余字段：现用于记录旅游卡“是否曾经有过上架操作”,否（0）/是（1）
	@Column
	private String periodEndDate;	//多余字段（暂未使用）
	
	@Column
	private Long timesFlag;			//是否限定次数：不限制(-1)/限制（0）
	@Column
	private Long effectiveTimes;	//旅游卡有效次数:可使用次数(99)
	
	@Column
	private Long usePeriod;			//旅游卡通用时间:不限制(-1)/周六周日不能使用(0)/节假日不能使用(1)
	
	@Column
	private Long status;			//删除(-1)/下架(0)/上架(1)
	
	@Column
	private String descpt;			//说明
	
	@Column
	private String sortWeight;		//排序权重

	@Column
	private String imageAddr;//图片地址

	//非数据库字段
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTourCommission() {
		return tourCommission;
	}

	public void setTourCommission(String tourCommission) {
		this.tourCommission = tourCommission;
	}

	public String getProfitNum() {
		return profitNum;
	}

	public void setProfitNum(String profitNum) {
		this.profitNum = profitNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIscenicids() {
		return iscenicids;
	}

	public void setIscenicids(String iscenicids) {
		this.iscenicids = iscenicids;
	}

	public String getScenics() {
		return scenics;
	}

	public void setScenics(String scenics) {
		this.scenics = scenics;
	}

	

	

	public Long getBankCardFlag() {
		return bankCardFlag;
	}

	public void setBankCardFlag(Long bankCardFlag) {
		this.bankCardFlag = bankCardFlag;
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

	public Long getUsePeriod() {
		return usePeriod;
	}

	public void setUsePeriod(Long usePeriod) {
		this.usePeriod = usePeriod;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDescpt() {
		return descpt;
	}

	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

	public Long getIdentityCardFlag() {
		return identityCardFlag;
	}

	public void setIdentityCardFlag(Long identityCardFlag) {
		this.identityCardFlag = identityCardFlag;
	}

	public String getIdentityCardIds() {
		return identityCardIds;
	}

	public void setIdentityCardIds(String identityCardIds) {
		this.identityCardIds = identityCardIds;
	}

	public String getIdentityCardAreas() {
		return identityCardAreas;
	}

	public void setIdentityCardAreas(String identityCardAreas) {
		this.identityCardAreas = identityCardAreas;
	}

	public Long getTimesFlag() {
		return timesFlag;
	}

	public void setTimesFlag(Long timesFlag) {
		this.timesFlag = timesFlag;
	}

	public String getSortWeight() {
		return sortWeight;
	}

	public void setSortWeight(String sortWeight) {
		this.sortWeight = sortWeight;
	}

	public String getImageAddr() {
		return imageAddr;
	}

	public void setImageAddr(String imageAddr) {
		this.imageAddr = imageAddr;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
