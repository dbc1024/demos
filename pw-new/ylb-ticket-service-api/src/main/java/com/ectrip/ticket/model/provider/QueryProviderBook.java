package com.ectrip.ticket.model.provider;

/**
 * 散客前台预订查询类（此类数据库中不存在）
 * @author huangyuqi
 */
public class QueryProviderBook implements java.io.Serializable {

	private Long iscenicid;//服务商编号
	private String scenictype;//服务商类型
	private String szscenicname;//服务商名称
	private String szgrade;//等级
	private Integer isjd;//是否是景点（0服务商，1景点）
	private Long szregionalid;//服务商名称所属地
	private String topics;//主题

	private Long icrowdkindpriceid;// 售价id
	private Long ipricecategoryid;// 售价类别

	private  Long icrowdkindid;//人群种类Id（关联人群种类 Edpcrowdkindtab）
	private  Long ibusinessid;//业务类型id （关联业务类型 Edmbusinesstab）

	private  Long itickettypeid;//产品id （关联产品表 Edmtickettypetab）
	private String bycategorytype;//产品种类
	private String sztickettypename;//产品名称

	private String yule;//娱乐设施
	private String canying;//餐饮设施
	private String zxjb;//酒店类型
	private String jwqj;//价格区间
	private String meet;//会议设施
	private String location;//交通位置
	private String credit;//可接受的信用卡
	private String hotelservice;//酒店固有服务

	private String bprice;//最小价格
	private String eprice;//最大价格
	private String orderseq;//排序方式

	private String lgtp;//用户类别
	private String ptype;//评论对象类型


	public Long getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getScenictype() {
		return scenictype;
	}
	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}
	public String getSzscenicname() {
		return szscenicname;
	}
	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}
	public String getSzgrade() {
		return szgrade;
	}
	public void setSzgrade(String szgrade) {
		this.szgrade = szgrade;
	}
	public Integer getIsjd() {
		return isjd;
	}
	public void setIsjd(Integer isjd) {
		this.isjd = isjd;
	}
	public Long getSzregionalid() {
		return szregionalid;
	}
	public void setSzregionalid(Long szregionalid) {
		this.szregionalid = szregionalid;
	}
	public Long getIcrowdkindpriceid() {
		return icrowdkindpriceid;
	}
	public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
		this.icrowdkindpriceid = icrowdkindpriceid;
	}
	public Long getIpricecategoryid() {
		return ipricecategoryid;
	}
	public void setIpricecategoryid(Long ipricecategoryid) {
		this.ipricecategoryid = ipricecategoryid;
	}
	public Long getIcrowdkindid() {
		return icrowdkindid;
	}
	public void setIcrowdkindid(Long icrowdkindid) {
		this.icrowdkindid = icrowdkindid;
	}
	public Long getIbusinessid() {
		return ibusinessid;
	}
	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}
	public Long getItickettypeid() {
		return itickettypeid;
	}
	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}
	public String getYule() {
		return yule;
	}
	public void setYule(String yule) {
		this.yule = yule;
	}
	public String getCanying() {
		return canying;
	}
	public void setCanying(String canying) {
		this.canying = canying;
	}
	public String getZxjb() {
		return zxjb;
	}
	public void setZxjb(String zxjb) {
		this.zxjb = zxjb;
	}
	public String getJwqj() {
		return jwqj;
	}
	public void setJwqj(String jwqj) {
		this.jwqj = jwqj;
	}
	public String getMeet() {
		return meet;
	}
	public void setMeet(String meet) {
		this.meet = meet;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getHotelservice() {
		return hotelservice;
	}
	public void setHotelservice(String hotelservice) {
		this.hotelservice = hotelservice;
	}
	public String getBycategorytype() {
		return bycategorytype;
	}
	public void setBycategorytype(String bycategorytype) {
		this.bycategorytype = bycategorytype;
	}
	public String getSztickettypename() {
		return sztickettypename;
	}
	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}
	public String getBprice() {
		return bprice;
	}
	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
	public String getEprice() {
		return eprice;
	}
	public void setEprice(String eprice) {
		this.eprice = eprice;
	}
	public String getOrderseq() {
		return orderseq;
	}
	public void setOrderseq(String orderseq) {
		this.orderseq = orderseq;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	public String getLgtp() {
		return lgtp;
	}
	public void setLgtp(String lgtp) {
		this.lgtp = lgtp;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}




}

