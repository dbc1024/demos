package com.ectrip.ticket.model.provider;

import java.sql.Blob;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Esbscenicareatab entity. @author MyEclipse Persistence Tools 服务商
 */
@Entity
@Table(name = "Esbscenicareatab")
public class Esbscenicareatab implements java.io.Serializable {

	// Fields
	@Transient
	private static final String Esbscenicareatab = "Esbscenicareatab";
	@Id
	private Long iscenicid;// 服务商编号
	@Column
	private Long irootid;// 根节点
	@Column
	private Long iparentid;// 父节点
	@Column
	private Integer ilevel;// 层
	@Column
	private Integer ilevelsequence;// 层序号
	@Column
	private Integer bisleaf;// 是否叶子节点
	@Column
	private String szinnerid;// 隶属关系id
	@Column
	private String szinnercode;// 隶属关系代码
	@Column
	private String szinnername;// 隶属关系名称
	@Column
	private String szsceniccode;// 服务商编码
	@Column
	private Long szregionalid;// 服务商名称所属地
	@Column
	private String scenictype;// 服务商类型 06酒店 01景区
	@Column
	private String szscenicname;// 服务商名称
	@Column
	private String szgrade;// 等级
	@Column
	private String szsimpleintroduction;// 简介
	@Column
	private String szphone;// 联系电话
	@Column
	private String szcontact;// 联系人
	@Column
	private Integer iordernumber;// 推荐次序
	@Column
	private String szbookdescription;// 预定说明
	@Column
	private Blob bookdescription;// 预定说明(可图文)
	@Column
	private Integer istogetherbooking;// 网上是否与主服务商一起预定
	@Column
	private Integer istogetherticket;// 网上订单是否与主服务商一起出票
	@Column
	private String szlasttime;// 网上预定当天最后截止时间
	@Column
	private Integer imaxdata;// 最大提前预定天数
	@Column
	private Integer iscanreturn;// 是否可以强制退票
	@Column
	private Integer icanmodify;// 过期是否可以修改
	@Column
	private String szqjaddr;// 全景网址
	@Column
	private String sznetaddr;// 网址
	@Column
	private String createddate;// 创建日期
	@Column
	private String szcreator;// 创建人
	@Column
	private String szlocation;// 位置描述
	@Column
	private Blob sznote;// 详细介绍
	@Column
	private String szaddress;// 地址
	@Column
	private Integer byisuse;// 状态
	@Column
	private String szmemo;// 备注
	@Column
	private Integer isjd;// 是否是景点（0服务商，1景点）
	@Column
	private String verify; // 是否审核
	@Column
	private String longitude;// 经维度
	@Column
	private String topics;// 主题
	@Column
	private String szdata;// 景点用时数
	@Column
	private Integer printreceipt;// 打印回执
	@Column
	private Integer printinvoice;// 打印发票
	@Column
	private Long popupoint;// 人气指数
	@Column
	private Double commentpoint;// 评论分数
	@Column
	private String businesshours;// 营业时间
	@Column
	private String moredescribe;// 景区-补充描述
	@Column
	private String cost;// 费用包含
	@Column
	private String refundrules;// 退票规则
	@Column
	private String moreexplan;// 补充说明
	@Column
	private String entermode;// 入园方式
	@Column
	private String hotelin;// 酒店入住时间
	@Column
	private String hotelleave;// 酒店客户离店时间

	// 数据库中不存在的字段
	@Transient
	private String striparentname;// 上级服务商名称
	@Transient
	private String strgrade;// 等级
	@Transient
	private String strpdtyp;// 类型
	@Transient
	private String strregion;// 上级地区
	@Transient
	private String strarea;// 当前地区
	@Transient
	private List list;
	@Transient
	private String[] upids;// 图片
	@Transient
	private boolean check;
	@Transient
	private String note;// 服务商详细介绍
	@Transient
	private List piclist;// 服务商图片列表
	@Transient
	private List productList;// 产品列表
	@Transient
	private List commentList;// 评论列表
	@Transient
	private double sumremarknum;// 综合评分数
	@Transient
	private double price; // 产品网上价
	@Transient
	private String[] commenteffect;// 评论印象
	@Transient
	private List messagelist;// 留言列表
	@Transient
	private List hotellist;// 酒店列表
	@Transient
	private List jingqulist;// 景区列表
	@Transient
	private List ticlist;
	@Transient
	private Hotelprovider hotelprovider;
	@Transient
	private String strbookdescription;
	@Transient
	private List timelist; // 分時預約lie
	@Transient
	private TimeSharingTicketTab time;

	public String getHotelin() {
		return hotelin;
	}

	public void setHotelin(String hotelin) {
		this.hotelin = hotelin;
	}

	public String getHotelleave() {
		return hotelleave;
	}

	public void setHotelleave(String hotelleave) {
		this.hotelleave = hotelleave;
	}

	public String getMoredescribe() {
		return moredescribe;
	}

	public void setMoredescribe(String moredescribe) {
		this.moredescribe = moredescribe;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getRefundrules() {
		return refundrules;
	}

	public void setRefundrules(String refundrules) {
		this.refundrules = refundrules;
	}

	public String getMoreexplan() {
		return moreexplan;
	}

	public void setMoreexplan(String moreexplan) {
		this.moreexplan = moreexplan;
	}

	public String getEntermode() {
		return entermode;
	}

	public void setEntermode(String entermode) {
		this.entermode = entermode;
	}

	public List getTimelist() {
		return timelist;
	}

	public void setTimelist(List timelist) {
		this.timelist = timelist;
	}

	public TimeSharingTicketTab getTime() {
		return time;
	}

	public void setTime(TimeSharingTicketTab time) {
		this.time = time;
	}

	@Transient
	private Long icompanyinfoid; // 供应商ID - 非数据库字段

	public Blob getBookdescription() {
		return bookdescription;
	}

	public void setBookdescription(Blob bookdescription) {
		this.bookdescription = bookdescription;
	}

	public Long getPopupoint() {
		return popupoint;
	}

	public void setPopupoint(Long popupoint) {
		this.popupoint = popupoint;
	}

	public Double getCommentpoint() {
		return commentpoint;
	}

	public void setCommentpoint(Double commentpoint) {
		this.commentpoint = commentpoint;
	}

	public String getBusinesshours() {
		return businesshours;
	}

	public void setBusinesshours(String businesshours) {
		this.businesshours = businesshours;
	}

	public String getStrbookdescription() {
		return strbookdescription;
	}

	public void setStrbookdescription(String strbookdescription) {
		this.strbookdescription = strbookdescription;
	}

	@Column
	private String zxjb;// 酒类类型
	@Column
	private String pmva;// 参数A -- 酒店类型名
	@Column
	private String xxnr;// 详细内容

	/**
	 * default constructor
	 */
	public Esbscenicareatab() {
	}

	/**
	 * minimal constructor
	 */
	public Esbscenicareatab(Long iscenicid, Long irootid, Long iparentid, Integer ilevel, Integer ilevelsequence,
			Integer bisleaf, String szinnerid, String szinnercode, String szinnername, String szsceniccode,
			Long szregionalid, String scenictype, String szscenicname, String szgrade, Integer iordernumber,
			Integer istogetherbooking, Integer istogetherticket, String szlasttime, Integer imaxdata,
			Integer iscanreturn, Integer icanmodify, String szaddress, Integer byisuse, Integer isjd,
			Integer printreceipt, Integer printinvoice) {
		this.iscenicid = iscenicid;
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szsceniccode = szsceniccode;
		this.szregionalid = szregionalid;
		this.scenictype = scenictype;
		this.szscenicname = szscenicname;
		this.szgrade = szgrade;
		this.iordernumber = iordernumber;
		this.istogetherbooking = istogetherbooking;
		this.istogetherticket = istogetherticket;
		this.szlasttime = szlasttime;
		this.imaxdata = imaxdata;
		this.iscanreturn = iscanreturn;
		this.icanmodify = icanmodify;
		this.szaddress = szaddress;
		this.byisuse = byisuse;
		this.isjd = isjd;
		this.printreceipt = printreceipt;
		this.printinvoice = printinvoice;
	}

	/**
	 * full constructor
	 */
	public Esbscenicareatab(Long iscenicid, Long irootid, Long iparentid, Integer ilevel, Integer ilevelsequence,
			Integer bisleaf, String szinnerid, String szinnercode, String szinnername, String szsceniccode,
			Long szregionalid, String scenictype, String szscenicname, String szgrade, String szsimpleintroduction,
			String szphone, String szcontact, Integer iordernumber, String szbookdescription, Integer istogetherbooking,
			Integer istogetherticket, String szlasttime, Integer imaxdata, Integer iscanreturn, Integer icanmodify,
			String szqjaddr, String sznetaddr, String createddate, String szcreator, String szlocation, Blob sznote,
			String szaddress, Integer byisuse, String szmemo, Integer isjd, String longitude, String szdata,
			String topics, Integer printreceipt, Integer printinvoice) {
		this.iscenicid = iscenicid;
		this.irootid = irootid;
		this.iparentid = iparentid;
		this.ilevel = ilevel;
		this.ilevelsequence = ilevelsequence;
		this.bisleaf = bisleaf;
		this.szinnerid = szinnerid;
		this.szinnercode = szinnercode;
		this.szinnername = szinnername;
		this.szsceniccode = szsceniccode;
		this.szregionalid = szregionalid;
		this.scenictype = scenictype;
		this.szscenicname = szscenicname;
		this.szgrade = szgrade;
		this.szsimpleintroduction = szsimpleintroduction;
		this.szphone = szphone;
		this.szcontact = szcontact;
		this.iordernumber = iordernumber;
		this.szbookdescription = szbookdescription;
		this.istogetherbooking = istogetherbooking;
		this.istogetherticket = istogetherticket;
		this.szlasttime = szlasttime;
		this.imaxdata = imaxdata;
		this.iscanreturn = iscanreturn;
		this.icanmodify = icanmodify;
		this.szqjaddr = szqjaddr;
		this.sznetaddr = sznetaddr;
		this.createddate = createddate;
		this.szcreator = szcreator;
		this.szlocation = szlocation;
		this.sznote = sznote;
		this.szaddress = szaddress;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.isjd = isjd;
		this.longitude = longitude;
		this.szdata = szdata;
		this.topics = topics;
		this.printinvoice = printinvoice;
		this.printreceipt = printreceipt;
	}

	// Property accessors

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public void setIscenicid(String iscenicid) {
		this.iscenicid = new Long(iscenicid);
	}

	public Long getIrootid() {
		return this.irootid;
	}

	public void setIrootid(Long irootid) {
		this.irootid = irootid;
	}

	public Long getIparentid() {
		return this.iparentid;
	}

	public void setIparentid(Long iparentid) {
		this.iparentid = iparentid;
	}

	public Integer getIlevel() {
		return this.ilevel;
	}

	public void setIlevel(Integer ilevel) {
		this.ilevel = ilevel;
	}

	public Integer getIlevelsequence() {
		return this.ilevelsequence;
	}

	public void setIlevelsequence(Integer ilevelsequence) {
		this.ilevelsequence = ilevelsequence;
	}

	public Integer getBisleaf() {
		return this.bisleaf;
	}

	public void setBisleaf(Integer bisleaf) {
		this.bisleaf = bisleaf;
	}

	public String getSzinnerid() {
		return this.szinnerid;
	}

	public void setSzinnerid(String szinnerid) {
		this.szinnerid = szinnerid;
	}

	public String getSzinnercode() {
		return this.szinnercode;
	}

	public void setSzinnercode(String szinnercode) {
		this.szinnercode = szinnercode;
	}

	public String getSzinnername() {
		return this.szinnername;
	}

	public void setSzinnername(String szinnername) {
		this.szinnername = szinnername;
	}

	public String getSzsceniccode() {
		return this.szsceniccode;
	}

	public void setSzsceniccode(String szsceniccode) {
		this.szsceniccode = szsceniccode;
	}

	public Long getSzregionalid() {
		return this.szregionalid;
	}

	public void setSzregionalid(Long szregionalid) {
		this.szregionalid = szregionalid;
	}

	public String getScenictype() {
		return this.scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}

	public String getSzscenicname() {
		return this.szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getSzgrade() {
		return this.szgrade;
	}

	public void setSzgrade(String szgrade) {
		this.szgrade = szgrade;
	}

	public String getSzsimpleintroduction() {
		return this.szsimpleintroduction;
	}

	public void setSzsimpleintroduction(String szsimpleintroduction) {
		this.szsimpleintroduction = szsimpleintroduction;
	}

	public String getSzphone() {
		return this.szphone;
	}

	public void setSzphone(String szphone) {
		this.szphone = szphone;
	}

	public String getSzcontact() {
		return this.szcontact;
	}

	public void setSzcontact(String szcontact) {
		this.szcontact = szcontact;
	}

	public Integer getIordernumber() {
		return this.iordernumber;
	}

	public void setIordernumber(Integer iordernumber) {
		this.iordernumber = iordernumber;
	}

	public String getSzbookdescription() {
		return this.szbookdescription;
	}

	public void setSzbookdescription(String szbookdescription) {
		this.szbookdescription = szbookdescription;
	}

	public Integer getIstogetherbooking() {
		return this.istogetherbooking;
	}

	public void setIstogetherbooking(Integer istogetherbooking) {
		this.istogetherbooking = istogetherbooking;
	}

	public Integer getIstogetherticket() {
		return this.istogetherticket;
	}

	public void setIstogetherticket(Integer istogetherticket) {
		this.istogetherticket = istogetherticket;
	}

	public String getSzlasttime() {
		return this.szlasttime;
	}

	public void setSzlasttime(String szlasttime) {
		this.szlasttime = szlasttime;
	}

	public Integer getImaxdata() {
		return this.imaxdata;
	}

	public void setImaxdata(Integer imaxdata) {
		this.imaxdata = imaxdata;
	}

	public Integer getIscanreturn() {
		return this.iscanreturn;
	}

	public void setIscanreturn(Integer iscanreturn) {
		this.iscanreturn = iscanreturn;
	}

	public Integer getIcanmodify() {
		return this.icanmodify;
	}

	public void setIcanmodify(Integer icanmodify) {
		this.icanmodify = icanmodify;
	}

	public String getSzqjaddr() {
		return this.szqjaddr;
	}

	public void setSzqjaddr(String szqjaddr) {
		this.szqjaddr = szqjaddr;
	}

	public String getSznetaddr() {
		return this.sznetaddr;
	}

	public void setSznetaddr(String sznetaddr) {
		this.sznetaddr = sznetaddr;
	}

	public String getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}

	public String getSzcreator() {
		return this.szcreator;
	}

	public void setSzcreator(String szcreator) {
		this.szcreator = szcreator;
	}

	public String getSzlocation() {
		return this.szlocation;
	}

	public void setSzlocation(String szlocation) {
		this.szlocation = szlocation;
	}

	public Blob getSznote() {
		return sznote;
	}

	public void setSznote(Blob sznote) {
		this.sznote = sznote;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSzaddress() {
		return this.szaddress;
	}

	public void setSzaddress(String szaddress) {
		this.szaddress = szaddress;
	}

	public Integer getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Integer byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public String getStriparentname() {
		return striparentname;
	}

	public void setStriparentname(String striparentname) {
		this.striparentname = striparentname;
	}

	public String getStrgrade() {
		return strgrade;
	}

	public void setStrgrade(String strgrade) {
		this.strgrade = strgrade;
	}

	public String getStrpdtyp() {
		return strpdtyp;
	}

	public void setStrpdtyp(String strpdtyp) {
		this.strpdtyp = strpdtyp;
	}

	public String getStrregion() {
		return strregion;
	}

	public void setStrregion(String strregion) {
		this.strregion = strregion;
	}

	public String getStrarea() {
		return strarea;
	}

	public void setStrarea(String strarea) {
		this.strarea = strarea;
	}

	public Integer getIsjd() {
		return isjd;
	}

	public void setIsjd(Integer isjd) {
		this.isjd = isjd;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSzdata() {
		return szdata;
	}

	public void setSzdata(String szdata) {
		this.szdata = szdata;
	}

	public List getPiclist() {
		return piclist;
	}

	public void setPiclist(List piclist) {
		this.piclist = piclist;
	}

	public List getCommentList() {
		return commentList;
	}

	public void setCommentList(List commentList) {
		this.commentList = commentList;
	}

	public double getSumremarknum() {
		return sumremarknum;
	}

	public void setSumremarknum(double sumremarknum) {
		this.sumremarknum = sumremarknum;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String[] getCommenteffect() {
		return commenteffect;
	}

	public void setCommenteffect(String[] commenteffect) {
		this.commenteffect = commenteffect;
	}

	public String getXxnr() {
		return xxnr;
	}

	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}

	public String getZxjb() {
		return zxjb;
	}

	public void setZxjb(String zxjb) {
		this.zxjb = zxjb;
	}

	public String getPmva() {
		return pmva;
	}

	public void setPmva(String pmva) {
		this.pmva = pmva;
	}

	public List getTiclist() {
		return ticlist;
	}

	public void setTiclist(List ticlist) {
		this.ticlist = ticlist;
	}

	public Hotelprovider getHotelprovider() {
		return hotelprovider;
	}

	public void setHotelprovider(Hotelprovider hotelprovider) {
		this.hotelprovider = hotelprovider;
	}

	public List getProductList() {
		return productList;
	}

	public void setProductList(List productList) {
		this.productList = productList;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
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

	public List getMessagelist() {
		return messagelist;
	}

	public void setMessagelist(List messagelist) {
		this.messagelist = messagelist;
	}

	public List getHotellist() {
		return hotellist;
	}

	public void setHotellist(List hotellist) {
		this.hotellist = hotellist;
	}

	public List getJingqulist() {
		return jingqulist;
	}

	public void setJingqulist(List jingqulist) {
		this.jingqulist = jingqulist;
	}

	public Integer getPrintreceipt() {
		return printreceipt;
	}

	public void setPrintreceipt(Integer printreceipt) {
		this.printreceipt = printreceipt;
	}

	public Integer getPrintinvoice() {
		return printinvoice;
	}

	public void setPrintinvoice(Integer printinvoice) {
		this.printinvoice = printinvoice;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public Long getIcompanyinfoid() {
		return icompanyinfoid;
	}

	public void setIcompanyinfoid(Long icompanyinfoid) {
		this.icompanyinfoid = icompanyinfoid;
	}

}