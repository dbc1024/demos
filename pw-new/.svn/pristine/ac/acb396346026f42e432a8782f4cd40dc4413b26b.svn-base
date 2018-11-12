package com.ectrip.ticket.model.provider;

/**
 * Hotelproduct entity. @author MyEclipse Persistence Tools
 */

public class Hotelproduct implements java.io.Serializable {

	// Fields

	private static final String Hotelproduct = "Hotelproduct";

	private Long itickettypeid;//产品Id
	private String bedtype;//床型
	private Integer breakfasttype;//酒店早餐类型（0无，1单早，2双早）
	private Integer widebandtype;//宽带类型（0无，1免费宽带，2收费宽带）
	private Integer weektype;//周未价类型（0周五、六，1周五、六、日，2周四、五、六，3周四、五、六、日）
	private String panoramaaddr;//全景图网址
	private Integer deposittype; //定金方式(0--金额 1--百分比)
	private double  deposit;// 定金

	private String noted1;//售后服务,景区固有服务
	private String noted2;//支付事项,景区娱乐设施
	private String noted3;//关于优惠
	private String noted4;
	private String noted5;
	private Long inoteger1;
	private Long inoteger2;
	private Long inoteger3;
	private Long inoteger4;
	private Long inoteger5;
	private double numter1;
	private double numter2;
	private double numter3;
	private double numter4;
	private double numter5;

	//非数据库字段
	private String strbedtype;
	private String strbreak;
	private String strwide;



	// Constructors

	/** default constructor */
	public Hotelproduct() {
	}

	/** minimal constructor */
	public Hotelproduct(Long itickettypeid,String bedtype,
						Integer breakfasttype,
						Integer widebandtype, Integer weektype) {
		this.itickettypeid = itickettypeid;
		this.bedtype = bedtype;
		this.breakfasttype = breakfasttype;
		this.widebandtype = widebandtype;
		this.weektype = weektype;
	}

	/** full constructor */
	public Hotelproduct(Long itickettypeid,String bedtype,Integer breakfasttype,
						Integer widebandtype, Integer weektype, String panoramaaddr
			,Integer deposittype,double deposit) {
		this.itickettypeid = itickettypeid;
		this.bedtype = bedtype;
		this.breakfasttype = breakfasttype;
		this.widebandtype = widebandtype;
		this.weektype = weektype;
		this.panoramaaddr = panoramaaddr;
		this.deposittype = deposittype;
		this.deposit = deposit;
	}

	// Property accessors

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Integer getBreakfasttype() {
		return this.breakfasttype;
	}

	public void setBreakfasttype(Integer breakfasttype) {
		this.breakfasttype = breakfasttype;
		if(0==breakfasttype){
			setStrbreak("无");
		}
		if(1==breakfasttype){
			setStrbreak("单早");
		}
		if(2==breakfasttype){
			setStrbreak("双早");
		}

	}

	public Integer getWidebandtype() {
		return this.widebandtype;
	}

	public void setWidebandtype(Integer widebandtype) {
		this.widebandtype = widebandtype;
		if(0==widebandtype){
			setStrwide("无");
		}
		if(1==widebandtype){
			setStrwide("免费宽带");
		}
		if(2==widebandtype){
			setStrwide("收费宽带");
		}
	}

	public Integer getWeektype() {
		return this.weektype;
	}

	public void setWeektype(Integer weektype) {
		this.weektype = weektype;
	}

	public String getPanoramaaddr() {
		return this.panoramaaddr;
	}

	public void setPanoramaaddr(String panoramaaddr) {
		this.panoramaaddr = panoramaaddr;
	}

	public Integer getDeposittype() {
		return deposittype;
	}

	public void setDeposittype(Integer deposittype) {
		this.deposittype = deposittype;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public String getStrbreak() {
		return strbreak;
	}

	public void setStrbreak(String strbreak) {
		this.strbreak = strbreak;
	}

	public String getStrwide() {
		return strwide;
	}

	public void setStrwide(String strwide) {
		this.strwide = strwide;
	}

	public String getBedtype() {
		return bedtype;
	}

	public void setBedtype(String bedtype) {
		this.bedtype = bedtype;
	}

	public String getStrbedtype() {
		return strbedtype;
	}

	public void setStrbedtype(String strbedtype) {
		this.strbedtype = strbedtype;
	}

	public String getNoted1() {
		return noted1;
	}

	public void setNoted1(String noted1) {
		this.noted1 = noted1;
	}

	public String getNoted2() {
		return noted2;
	}

	public void setNoted2(String noted2) {
		this.noted2 = noted2;
	}

	public String getNoted3() {
		return noted3;
	}

	public void setNoted3(String noted3) {
		this.noted3 = noted3;
	}

	public String getNoted4() {
		return noted4;
	}

	public void setNoted4(String noted4) {
		this.noted4 = noted4;
	}

	public String getNoted5() {
		return noted5;
	}

	public void setNoted5(String noted5) {
		this.noted5 = noted5;
	}

	public Long getInoteger1() {
		return inoteger1;
	}

	public void setInoteger1(Long inoteger1) {
		this.inoteger1 = inoteger1;
	}

	public Long getInoteger2() {
		return inoteger2;
	}

	public void setInoteger2(Long inoteger2) {
		this.inoteger2 = inoteger2;
	}

	public Long getInoteger3() {
		return inoteger3;
	}

	public void setInoteger3(Long inoteger3) {
		this.inoteger3 = inoteger3;
	}

	public Long getInoteger4() {
		return inoteger4;
	}

	public void setInoteger4(Long inoteger4) {
		this.inoteger4 = inoteger4;
	}

	public Long getInoteger5() {
		return inoteger5;
	}

	public void setInoteger5(Long inoteger5) {
		this.inoteger5 = inoteger5;
	}

	public double getNumter1() {
		return numter1;
	}

	public void setNumter1(double numter1) {
		this.numter1 = numter1;
	}

	public double getNumter2() {
		return numter2;
	}

	public void setNumter2(double numter2) {
		this.numter2 = numter2;
	}

	public double getNumter3() {
		return numter3;
	}

	public void setNumter3(double numter3) {
		this.numter3 = numter3;
	}

	public double getNumter4() {
		return numter4;
	}

	public void setNumter4(double numter4) {
		this.numter4 = numter4;
	}

	public double getNumter5() {
		return numter5;
	}

	public void setNumter5(double numter5) {
		this.numter5 = numter5;
	}

}