package com.ectrip.ticket.model.provider;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
/**
 * Edmtickettypetab entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Edmtickettypetab")
public class Edmtickettypetab implements java.io.Serializable {

	// Fields

	private static final String Edmtickettypetab = "Edmtickettypetab";

	@Id
	private Long itickettypeid;//产品id
	private Long iscenicid ;//服务商id(关联服务商表)
	private Long iticketnoruleid;//票号规则id(关联票号规则表）
	private String sztickettypecode;//产品代码
	private String sztickettypename;//产品名称
	private String bycategorytype;//产品种类
	private Long byusage;//使用人群（0 -- 常规票, 1-- 员工卡,2 -- 居民卡,3 -- 充值票,4 -- 结算卡,5 -- 贵宾票,6 -- 贵宾票+ 结算）
	private Long byuselimit;//使用限制（0一次性，1长期性）
	private String bymaketicketway;//出票方式（00现场打印,01现场激活，02打印激活03不做打印）
	private String bymediatype;//介质类型（00条码，01感应卡RI型，02感应卡RWI型，03感应卡RWII型）
	private String szticketprintcode;//印刷代码
	private Double mcostprice;//采购价
	private Double mnominalfee;//工本费
	private Long byisuse;//状态
	private String szmemo;//备注
	private Long bispersontimestat;//是否参与入园统计
	private Long validityday;//有效天数
	private Long issale;//是否销售受限制
	private Long iscansale;//受限产品无受限数据是否直接销售
	private Long iscontrol;//是否数量受限制
	private Long iscontrolsale;//受限产品无受限数据是否直接销售
	private Long isequence;//排序
	private String icid;	//IC卡

	private Long int1;
	private Long int2;
	private Long int3 = 0L;//是否分时预约 1:是 0:否
	private Long int4;
	private Long int5;
	private String note1;  //产品活动类型
	private String note2;  //审核状态
	private String note3;
	private String note4;
	private String note5; //新增分时时段时将分时和库存信息拼接为JSON字符存放到该字段中

	//添加的非数据库字段
    @Transient
	private String strbycategorytype;//产品种类参数
    @Transient
	private String strticketway;//出票方式参数
    @Transient
	private String strmediatype;//介质类型参数
    @Transient
	private String strticketrule;//票号规则名称
    @Transient
	private String szscenicname;//服务商名称

    @Transient
	private String[] upids;//图片
    @Transient
	private List list;//图库列表


    @Transient
	private Edmcrowdkindpricetab price;//价格表
    @Transient
	private Hotelproduct hotelproduct;//酒店附属属性
    @Transient
	private Lineproduct lineproduct;//旅行社附属属性
    @Transient
	private Long sumtick;  //已销售产品总量
    @Transient
	private Double szticktype;  //打折
    @Transient
	private double sumremarknum;//综合评分数
    @Transient
	private Double mactualsaleprice; //实际售价
    @Transient
	private List priceList;
    @Transient
	private double jprice;//均价
    @Transient
	private String bname;
    @Transient
	private List timeList;// 分时list
    @Transient
	private String times;
    @Transient
	private TimeSharingTicketTab timesharingtickettab;

	public TimeSharingTicketTab getTimesharingtickettab() {
		return timesharingtickettab;
	}

	public void setTimesharingtickettab(TimeSharingTicketTab timesharingtickettab) {
		this.timesharingtickettab = timesharingtickettab;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public List getTimeList() {
		return timeList;
	}

	public void setTimeList(List timeList) {
		this.timeList = timeList;
	}

	public List getPriceList() {
		return priceList;
	}

	public void setPriceList(List priceList) {
		this.priceList = priceList;
	}

	public double getJprice() {
		return jprice;
	}

	public void setJprice(double jprice) {
		this.jprice = jprice;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Double getSzticktype() {
		return szticktype;
	}

	public void setSzticktype(Double szticktype) {
		this.szticktype = szticktype;
	}

	public Long getSumtick() {
		return sumtick;
	}

	public void setSumtick(Long sumtick) {
		this.sumtick = sumtick;
	}

	public Edmcrowdkindpricetab getPrice() {
		return price;
	}

	public Double getMactualsaleprice() {
		return mactualsaleprice;
	}

	public void setMactualsaleprice(Double mactualsaleprice) {
		this.mactualsaleprice = mactualsaleprice;
	}

	public void setPrice(Edmcrowdkindpricetab price) {
		this.price = price;
	}

	public double getSumremarknum() {
		return sumremarknum;
	}

	public void setSumremarknum(double sumremarknum) {
		this.sumremarknum = sumremarknum;
	}

	public Hotelproduct getHotelproduct() {
		return hotelproduct;
	}

	public void setHotelproduct(Hotelproduct hotelproduct) {
		this.hotelproduct = hotelproduct;
	}

	public Lineproduct getLineproduct() {
		return lineproduct;
	}

	public void setLineproduct(Lineproduct lineproduct) {
		this.lineproduct = lineproduct;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getIcid() {
		return icid;
	}

	public void setIcid(String icid) {
		this.icid = icid;
	}

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String[] getUpids() {
		return upids;
	}

	public void setUpids(String[] upids) {
		this.upids = upids;
	}


	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getStrticketrule() {
		return strticketrule;
	}

	public void setStrticketrule(String strticketrule) {
		this.strticketrule = strticketrule;
	}

	public String getStrticketway() {
		return strticketway;
	}

	public void setStrticketway(String strticketway) {
		this.strticketway = strticketway;
	}

	public String getStrmediatype() {
		return strmediatype;
	}

	public void setStrmediatype(String strmediatype) {
		this.strmediatype = strmediatype;
	}

	public String getStrbycategorytype() {
		return strbycategorytype;
	}

	public void setStrbycategorytype(String strbycategorytype) {
		this.strbycategorytype = strbycategorytype;
	}

	/** default constructor */
	public Edmtickettypetab() {
	}

	/** minimal constructor */
	public Edmtickettypetab(Long itickettypeid,
							Long iscenicid ,Long iticketnoruleid,
							String sztickettypecode, String sztickettypename, String bycategorytype,
							Long byusage, Long byuselimit, String bymaketicketway,
							String bymediatype, String szticketprintcode, Double mcostprice,
							Double mnominalfee, Long byisuse, Long bispersontimestat,
							Long validityday, Long iscontrol,Long iscontrolsale, Long issale,Long iscansale,Long isequence) {
		this.itickettypeid = itickettypeid;
		this.iscenicid = iscenicid;
		this.iticketnoruleid=iticketnoruleid;
		this.sztickettypecode = sztickettypecode;
		this.sztickettypename = sztickettypename;
		this.bycategorytype = bycategorytype;
		this.byusage = byusage;
		this.byuselimit = byuselimit;
		this.bymaketicketway = bymaketicketway;
		this.bymediatype = bymediatype;
		this.szticketprintcode = szticketprintcode;
		this.mcostprice = mcostprice;
		this.mnominalfee = mnominalfee;
		this.byisuse = byisuse;
		this.bispersontimestat = bispersontimestat;
		this.validityday = validityday;
		this.iscontrol = iscontrol;
		this.iscontrolsale = iscontrolsale;
		this.issale = issale;
		this.iscansale = iscansale;
		this.isequence = isequence;
	}

	/** full constructor */
	public Edmtickettypetab(Long itickettypeid,
							Long iscenicid ,Long iticketnoruleid,
							String sztickettypecode, String sztickettypename, String bycategorytype,
							Long byusage, Long byuselimit, String bymaketicketway,
							String bymediatype, String szticketprintcode, Double mcostprice,
							Double mnominalfee, Long byisuse, String szmemo, Long bispersontimestat,
							Long validityday, Long iscontrol,Long iscontrolsale, Long issale,Long iscansale, Long isequence) {
		this.itickettypeid = itickettypeid;
		this.iscenicid = iscenicid;
		this.iticketnoruleid=iticketnoruleid;
		this.sztickettypecode = sztickettypecode;
		this.sztickettypename = sztickettypename;
		this.bycategorytype = bycategorytype;
		this.byusage = byusage;
		this.byuselimit = byuselimit;
		this.bymaketicketway = bymaketicketway;
		this.bymediatype = bymediatype;
		this.szticketprintcode = szticketprintcode;
		this.mcostprice = mcostprice;
		this.mnominalfee = mnominalfee;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.bispersontimestat = bispersontimestat;
		this.validityday = validityday;
		this.issale = issale;
		this.iscansale = iscansale;
		this.iscontrol = iscontrol;
		this.iscontrolsale = iscontrolsale;
		this.isequence = isequence;
	}

	// Property accessors

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}



	public Long getIticketnoruleid() {
		return iticketnoruleid;
	}

	public void setIticketnoruleid(Long iticketnoruleid) {
		this.iticketnoruleid = iticketnoruleid;
	}

	public String getSztickettypecode() {
		return this.sztickettypecode;
	}

	public void setSztickettypecode(String sztickettypecode) {
		this.sztickettypecode = sztickettypecode;
	}

	public String getSztickettypename() {
		return this.sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public String getBycategorytype() {
		return this.bycategorytype;
	}

	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}

	public Long getByusage() {
		return this.byusage;
	}

	public void setByusage(Long byusage) {
		this.byusage = byusage;
	}

	public Long getByuselimit() {
		return this.byuselimit;
	}

	public void setByuselimit(Long byuselimit) {
		this.byuselimit = byuselimit;
	}

	public String getBymaketicketway() {
		return this.bymaketicketway;
	}

	public void setBymaketicketway(String bymaketicketway) {
		this.bymaketicketway = bymaketicketway;
	}

	public String getBymediatype() {
		return this.bymediatype;
	}

	public void setBymediatype(String bymediatype) {
		this.bymediatype = bymediatype;
	}

	public String getSzticketprintcode() {
		return this.szticketprintcode;
	}

	public void setSzticketprintcode(String szticketprintcode) {
		this.szticketprintcode = szticketprintcode;
	}

	public Double getMcostprice() {
		return this.mcostprice;
	}

	public void setMcostprice(Double mcostprice) {
		this.mcostprice = mcostprice;
	}

	public Double getMnominalfee() {
		return this.mnominalfee;
	}

	public void setMnominalfee(Double mnominalfee) {
		this.mnominalfee = mnominalfee;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getBispersontimestat() {
		return this.bispersontimestat;
	}

	public void setBispersontimestat(Long bispersontimestat) {
		this.bispersontimestat = bispersontimestat;
	}

	public Long getValidityday() {
		return this.validityday;
	}

	public void setValidityday(Long validityday) {
		this.validityday = validityday;
	}


	public Long getIsequence() {
		return isequence;
	}

	public void setIsequence(Long isequence) {
		this.isequence = isequence;
	}

	public Long getIscontrol() {
		return iscontrol;
	}

	public void setIscontrol(Long iscontrol) {
		this.iscontrol = iscontrol;
	}

	public Long getIscontrolsale() {
		return iscontrolsale;
	}

	public void setIscontrolsale(Long iscontrolsale) {
		this.iscontrolsale = iscontrolsale;
	}

	public Long getIssale() {
		return issale;
	}

	public void setIssale(Long issale) {
		this.issale = issale;
	}

	public Long getIscansale() {
		return iscansale;
	}

	public void setIscansale(Long iscansale) {
		this.iscansale = iscansale;
	}

	public Long getInt1() {
		return int1;
	}

	public void setInt1(Long int1) {
		this.int1 = int1;
	}

	public Long getInt2() {
		return int2;
	}

	public void setInt2(Long int2) {
		this.int2 = int2;
	}

	public Long getInt3() {
		return int3;
	}

	public void setInt3(Long int3) {
		this.int3 = int3;
	}

	public Long getInt4() {
		return int4;
	}

	public void setInt4(Long int4) {
		this.int4 = int4;
	}

	public Long getInt5() {
		return int5;
	}

	public void setInt5(Long int5) {
		this.int5 = int5;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}


}