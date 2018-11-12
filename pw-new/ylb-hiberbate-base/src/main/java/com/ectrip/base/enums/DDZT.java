package com.ectrip.base.enums;

/**
 * 订单状态相关参数
 * 原系统中其pmky='DDZT'
 *
 */
public enum DDZT {

	DDZT1("00","未付款"),
	DDZT2("01","已付款"),
	DDZT3("02","已支付"),
	DDZT4("03","退订需退款"),
	DDZT5("04","会计审核通过需退款"),
	DDZT6("05","会计审核未通过"),
	DDZT7("06","退订已退款"),
	DDZT8("07","抢订需退款"),
	DDZT9("08","抢订已退款"),
	DDZT10("09","支付不符需退款"),
	DDZT11("10","支付不符已退款"),
	DDZT12("11","已出票"),
	DDZT13("12","报团申请未审核"),
	DDZT14("13","报团申请审核不通过"),
	DDZT15("14","报团申请已审核通过"),
	DDZT16("15","报团变更申请未审核"),
	DDZT17("17","退定已转预付款"),
	DDZT18("18","订单审核通过"),
	DDZT19("19","前台审核未通过"),
	DDZT20("20","后台审核未通过"),
	DDZT21("21","财务已结算"),
	DDZT22("22","订单需后台二次审核"),
	DDZT23("23","已支付[申请单]"),
	DDZT24("24","未支付[申请单]"),
	DDZT25("25","抢订失败,退付款退还"),
	DDZT26("26","实名制订单自动拆分"),
	DDZT27("27","全退订"),
	DDZT28("30","已退订(退款中)"),
	DDZT29("31","退款中"),
	DDZT30("33","退款失败"),
	DDZT31("88","增量已支付"),
	DDZT32("94","退订需后台审核"),
	DDZT33("95","订单后台需审核"),
	DDZT34("96","已结帐(支付)"),
	DDZT35("97","订单需审核"),
	DDZT36("98","垃圾（作废）订单"),
	DDZT37("99","包含列表中所有订单状态"),
	;
	
	
	
	private String pmcd;//参数码
	private String pmva;//参数值
	
	
	
	private DDZT(String pmcd, String pmva) {
		this.pmcd = pmcd;
		this.pmva = pmva;
	}
	
	public static String getPmvaByPmcd(String pmcd) {
		DDZT[] values = DDZT.values();
		for (DDZT ddzt : values) {
			if(pmcd.equals(ddzt.pmcd)) {
				return ddzt.pmva;
			}
		}
		return "";
	}
	
	
	
	
	public String getPmcd() {
		return pmcd;
	}
	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}
	public String getPmva() {
		return pmva;
	}
	public void setPmva(String pmva) {
		this.pmva = pmva;
	}
	
	
}
