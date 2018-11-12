package com.ectrip.tourcard.model;

/*旅游卡查询条件对象*/
public class CardQuery {
	
	private String name;		//旅游卡名称
	
	private Long usePeriod;		//旅游卡通用时间:不限制(-1)/周六周日不能使用(0)/节假日不能使用(1)
	
	private String profitNum;	//分润协议号
	
	/*旅游卡有效期*/
	private Long periodType;	//有效期类型:按年（0），按月（1）

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUsePeriod() {
		return usePeriod;
	}

	public void setUsePeriod(Long usePeriod) {
		this.usePeriod = usePeriod;
	}

	public String getProfitNum() {
		return profitNum;
	}

	public void setProfitNum(String profitNum) {
		this.profitNum = profitNum;
	}

	public Long getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Long periodType) {
		this.periodType = periodType;
	}

}
