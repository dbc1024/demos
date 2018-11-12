package com.ectrip.ec.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.ectrip.ec.model.order.Reservecontrol;

/**
 * Custom entity. @author MyEclipse Persistence Tools
 * 客户信息
 */

@Entity
@Table(name="CUSTOM")
public class Custom implements java.io.Serializable {

	// Fields
	@Id
	private String usid;//用户名
	@Column
	private Long ibusinessid;//业务id
	@Column
	private String password;//密码 
	@Column
	private String lgtp;//注册类别     01散客 02团体
	@Column
 	private String ustp;// 用户类别 01-主用户； 02-子用户            01总社 02分社 03部门
	@Column
	private String status;//用户注册状态 00未审核01正常02无效03审核未通过04删除标识
	@Column
	private String usqx = "11110001000000000000";// 用户权限，共六位，第一位表示管理权限，第二位表示预订权限，第三位表示审核权限，第四位表示支付权限，最后两位为保留值
	/*第1位--管理权限第2位--预定权限第3位--审核权限第4位--支付权限第5位--是否开通商城第6位--C2C资料管理第7位--C2C订单管理    如果主社添加分社  需要把前四位权限写为1  如果是分社添加操作员前四位为0111*/
	@Column
	private Integer autofapiao;//是否自动打印发票
	@Column
	private Integer usdj;//用户等级  0 其他导游 1中级导游 2星级导游
	@Column
	private Integer iszhifuself;
	@Column
	private String zhifuid;//预定员所属支付员                    空
	@Column
	private Integer isauditself;//是否自己审核预订单            空
	@Column
	private String audusid;//预定员所属审核员                   空
	@Column
	private String ttlb;//团体类别   01旅行社   02 导游  
	@Column
	private String susid;//子用户对应母用户
	@Column
	private String corpname;//公司名称
	@Column
	private String tourlicensecode;//旅行社许可证号
	@Column
	private String businesslicensescode;//营业执照号码
	@Column
	private Long tourlicenseupid;//旅行社许可证图片
	@Column
	private Long businesslicensesupid;//营业执照图片
	@Column
	private String lname;//注册人姓名
	@Column
	private String zjlb;//注册人证件类型
	@Column
	private String zjhm;//注册人证件号码
	@Column
	private Long iregionalid;//所属地 散客用户所在地
	@Column
	private String bank;//银行
	@Column
	private String account;//账号
	@Column
	private String bname;//开户人
	@Column
	private String addr;//地址
	@Column
	private String telno;//联系座机号码
	@Column
	private String mobile;//联系手机号码
	@Column
	private String faxno;//传真
	@Column
	private String qq;//QQ
	@Column
	private String msn;//MSN
	@Column
	private String postno;//邮编
	@Column
	private String email;//EMail
	@Column
	private String ldate;//注册日期LDATE
	@Column
	private String lastdate;//上次登录时间
	@Column
	private String logtimes;//共登录次数
	@Column
	private String notea;//公司网址
	@Column
	private String noteb;//简介
	@Column
	private Integer times;//错误登录次数
	@Column
	private String lmdate;//用户最后修改注册信息时间
	@Column
	private String lpdate;//用户最后修改密码时间
	@Column
	private String cdate;//用户最后登陆时间
	@Column
	private String mmqt;//密码找回问题
	@Column
	private String mmda;//密码找回答案
	@Column
	private String daoyouno;//导游证号码
	@Column
	private String note1;
	@Column
	private String note2;
	@Column
	private String note3;
	@Column
	private String note4;
	@Column
	private String note5;
	@Column
	private String note6; //昵称
	@Column
	private String note7; //生日
	@Column
	private String note8;  //客户等级
	@Column
	private String note9;  //VIP卡号
	@Column
	private String note10;//存放用户身份证号
	@Column
	private Long inote1;//导游图像id 散客用户头像id（upfile表）
	@Column
	private Integer inote2;
	@Column
	private Integer inote3;//性别 1 男  2女
	@Column
	private Integer inote4;
	@Column
	private Integer inote5;
	@Column
	private Integer inote6;
	@Column
	private Integer inote7;
	@Column
	private Integer inote8;
	@Column
	private Integer inote9;
	@Column
	private Integer inote10;
	@Column
	private String dtmakedate;
	
	
	//非数据库字段
	@Transient
	private String qudaotype;//找回密码渠道
	@Transient
	private String wenti;//问题
	@Transient
	private String qudaokey;//   
	@Transient
	private String wentikey;//答案
	
	@Transient
	private String adder;//图片路径
	@Transient
	private String fname;//图片名称
	@Transient
	private Reservecontrol reservecontrol;
	@Transient
	private String usqx1;// 管理权限

	@Transient
	private String usqx2;// 预订权限

	@Transient
	private String usqx3;// 审核权限

	@Transient
	private String usqx4;// 支付权限

	@Transient
	private String usqx5 = "0";// 是否开通商城

	@Transient
	private String usqx6;
	
	@Transient
	private String usqx7;
	@Transient
	private String usqx8;
	@Transient
	private String usqx9;
	@Transient
	private String usqx10;
	@Transient
	private String usqx11;
	@Transient
	private String usqx12;
	@Transient
	private String usqx13;
	@Transient
	private String usqx14;
	
	public String getUsqx14() {
		return usqx14;
	}

	public void setUsqx14(String usqx14) {
		this.usqx14 = usqx14;
	}

	public String getUsqx15() {
		return usqx15;
	}

	public void setUsqx15(String usqx15) {
		this.usqx15 = usqx15;
	}

	public String getUsqx16() {
		return usqx16;
	}

	public void setUsqx16(String usqx16) {
		this.usqx16 = usqx16;
	}

	public String getUsqx17() {
		return usqx17;
	}

	public void setUsqx17(String usqx17) {
		this.usqx17 = usqx17;
	}

	public String getUsqx18() {
		return usqx18;
	}

	public void setUsqx18(String usqx18) {
		this.usqx18 = usqx18;
	}

	public String getUsqx19() {
		return usqx19;
	}

	public void setUsqx19(String usqx19) {
		this.usqx19 = usqx19;
	}

	public String getUsqx20() {
		return usqx20;
	}

	public void setUsqx20(String usqx20) {
		this.usqx20 = usqx20;
	}

	@Transient
	private String usqx15;
	@Transient
	private String usqx16;
	@Transient
	private String usqx17;
	@Transient
	private String usqx18;
	@Transient
	private String usqx19;
	@Transient
	private String usqx20;
	
	@Transient
	private String password2;//确认密码
	@Transient
	private String username;//用户名
	@Transient
	private String szregionalname;//客源地
	@Transient
	private String szbusinessname;//业务名称
	// Constructors

	/** default constructor */
	public Custom() {
	}

	/** minimal constructor */
	public Custom(String usid, Long ibusinessid, String password, String lgtp, String ustp,
			String status, String usqx, Integer autofapiao, String ldate,
			String lpdate) {
		this.usid = usid;
		this.ibusinessid = ibusinessid;
		this.password = password;
		this.lgtp = lgtp;
		this.ustp = ustp;
		this.status = status;
		this.usqx = usqx;
		this.autofapiao = autofapiao;
		this.ldate = ldate;
		this.lpdate = lpdate;
	}

	/** full constructor */
	public Custom(String usid, Long ibusinessid, String password, String lgtp, String ustp,
			String status, String usqx, Integer autofapiao, Integer usdj,
			Integer iszhifuself, String zhifuid, Integer isauditself,
			String audusid, String ttlb, String susid, String corpname,
			String tourlicensecode, String businesslicensescode,
			Long tourlicenseupid, Long businesslicensesupid,
			String lname, String zjlb, String zjhm, Long iregionalid,
			String bank, String account, String bname, String addr,
			String telno, String mobile, String faxno, String qq, String msn,
			String postno, String email, String ldate, String lastdate,
			String logtimes, String notea, String noteb, Integer times,
			String lmdate, String lpdate, String cdate, String mmqt,
			String mmda, String note1, String note2, String note3,
			String note4, String note5, String note6, String note7,
			String note8, String note9, String note10, Long inote1,
			Integer inote2, Integer inote3, Integer inote4,
			Integer inote5, Integer inote6, Integer inote7,
			Integer inote8, Integer inote9, Integer inote10) {
		this.usid = usid;
		this.ibusinessid=ibusinessid;
		this.password = password;
		this.lgtp = lgtp;
		this.ustp = ustp;
		this.status = status;
		this.usqx = usqx;
		this.autofapiao = autofapiao;
		this.usdj = usdj;
		this.iszhifuself = iszhifuself;
		this.zhifuid = zhifuid;
		this.isauditself = isauditself;
		this.audusid = audusid;
		this.ttlb = ttlb;
		this.susid = susid;
		this.corpname = corpname;
		this.tourlicensecode = tourlicensecode;
		this.businesslicensescode = businesslicensescode;
		this.tourlicenseupid = tourlicenseupid;
		this.businesslicensesupid = businesslicensesupid;
		this.lname = lname;
		this.zjlb = zjlb;
		this.zjhm = zjhm;
		this.iregionalid = iregionalid;
		this.bank = bank;
		this.account = account;
		this.bname = bname;
		this.addr = addr;
		this.telno = telno;
		this.mobile = mobile;
		this.faxno = faxno;
		this.qq = qq;
		this.msn = msn;
		this.postno = postno;
		this.email = email;
		this.ldate = ldate;
		this.lastdate = lastdate;
		this.logtimes = logtimes;
		this.notea = notea;
		this.noteb = noteb;
		this.times = times;
		this.lmdate = lmdate;
		this.lpdate = lpdate;
		this.cdate = cdate;
		this.mmqt = mmqt;
		this.mmda = mmda;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
		this.note6 = note6;
		this.note7 = note7;
		this.note8 = note8;
		this.note9 = note9;
		this.note10 = note10;
		this.inote1 = inote1;
		this.inote2 = inote2;
		this.inote3 = inote3;
		this.inote4 = inote4;
		this.inote5 = inote5;
		this.inote6 = inote6;
		this.inote7 = inote7;
		this.inote8 = inote8;
		this.inote9 = inote9;
		this.inote10 = inote10;
	}

	// Property accessors

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}
	
	public Long getIbusinessid() {
		return ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLgtp() {
		return this.lgtp;
	}

	public void setLgtp(String lgtp) {
		this.lgtp = lgtp;
	}

	public String getUstp() {
		return this.ustp;
	}

	public void setUstp(String ustp) {
		this.ustp = ustp;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsqx() {
		return this.usqx;
	}

	public void setUsqx(String usqx) {
		this.usqx = usqx;
	}

	public Integer getAutofapiao() {
		return this.autofapiao;
	}

	public void setAutofapiao(Integer autofapiao) {
		this.autofapiao = autofapiao;
	}

	public Integer getUsdj() {
		return this.usdj;
	}

	public void setUsdj(Integer usdj) {
		this.usdj = usdj;
	}

	public Integer getIszhifuself() {
		return this.iszhifuself;
	}

	public void setIszhifuself(Integer iszhifuself) {
		this.iszhifuself = iszhifuself;
	}

	public String getZhifuid() {
		return this.zhifuid;
	}

	public void setZhifuid(String zhifuid) {
		this.zhifuid = zhifuid;
	}

	public Integer getIsauditself() {
		return this.isauditself;
	}

	public void setIsauditself(Integer isauditself) {
		this.isauditself = isauditself;
	}

	public String getAudusid() {
		return this.audusid;
	}

	public void setAudusid(String audusid) {
		this.audusid = audusid;
	}

	public String getTtlb() {
		return this.ttlb;
	}

	public void setTtlb(String ttlb) {
		this.ttlb = ttlb;
	}

	public String getSusid() {
		return this.susid;
	}

	public void setSusid(String susid) {
		this.susid = susid;
	}

	public String getCorpname() {
		return this.corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getTourlicensecode() {
		return this.tourlicensecode;
	}

	public void setTourlicensecode(String tourlicensecode) {
		this.tourlicensecode = tourlicensecode;
	}

	public String getBusinesslicensescode() {
		return this.businesslicensescode;
	}

	public void setBusinesslicensescode(String businesslicensescode) {
		this.businesslicensescode = businesslicensescode;
	}

	public Long getTourlicenseupid() {
		return this.tourlicenseupid;
	}

	public void setTourlicenseupid(Long tourlicenseupid) {
		this.tourlicenseupid = tourlicenseupid;
	}

	public Long getBusinesslicensesupid() {
		return this.businesslicensesupid;
	}

	public void setBusinesslicensesupid(Long businesslicensesupid) {
		this.businesslicensesupid = businesslicensesupid;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getZjlb() {
		return this.zjlb;
	}

	public void setZjlb(String zjlb) {
		this.zjlb = zjlb;
	}

	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public Long getIregionalid() {
		return this.iregionalid;
	}

	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getPostno() {
		return this.postno;
	}

	public void setPostno(String postno) {
		this.postno = postno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLdate() {
		return this.ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getLastdate() {
		return this.lastdate;
	}

	public void setLastdate(String lastdate) {
		this.lastdate = lastdate;
	}

	public String getLogtimes() {
		return this.logtimes;
	}

	public void setLogtimes(String logtimes) {
		this.logtimes = logtimes;
	}

	public String getNotea() {
		return this.notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
	}

	public String getNoteb() {
		return this.noteb;
	}

	public void setNoteb(String noteb) {
		this.noteb = noteb;
	}

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getLmdate() {
		return this.lmdate;
	}

	public void setLmdate(String lmdate) {
		this.lmdate = lmdate;
	}

	public String getLpdate() {
		return this.lpdate;
	}

	public void setLpdate(String lpdate) {
		this.lpdate = lpdate;
	}

	public String getCdate() {
		return this.cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getMmqt() {
		return this.mmqt;
	}

	public void setMmqt(String mmqt) {
		this.mmqt = mmqt;
	}

	public String getMmda() {
		return this.mmda;
	}

	public void setMmda(String mmda) {
		this.mmda = mmda;
	}

	public String getNote1() {
		return this.note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return this.note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return this.note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return this.note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return this.note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}

	public String getNote6() {
		return this.note6;
	}

	public void setNote6(String note6) {
		this.note6 = note6==null?null:note6.replace("Ectrip", "yilvbao");
	}

	public String getNote7() {
		return this.note7;
	}

	public void setNote7(String note7) {
		this.note7 = note7;
	}

	public String getNote8() {
		return this.note8;
	}

	public void setNote8(String note8) {
		this.note8 = note8;
	}

	public String getNote9() {
		return this.note9;
	}

	public void setNote9(String note9) {
		this.note9 = note9;
	}

	public String getNote10() {
		return this.note10;
	}

	public void setNote10(String note10) {
		this.note10 = note10;
	}

	public Long getInote1() {
		return this.inote1;
	}

	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}

	public Integer getInote2() {
		return this.inote2;
	}

	public void setInote2(Integer inote2) {
		this.inote2 = inote2;
	}

	public Integer getInote3() {
		return this.inote3;
	}

	public void setInote3(Integer inote3) {
		this.inote3 = inote3;
	}

	public Integer getInote4() {
		return this.inote4;
	}

	public void setInote4(Integer inote4) {
		this.inote4 = inote4;
	}

	public Integer getInote5() {
		return this.inote5;
	}

	public void setInote5(Integer inote5) {
		this.inote5 = inote5;
	}

	public Integer getInote6() {
		return this.inote6;
	}

	public void setInote6(Integer inote6) {
		this.inote6 = inote6;
	}

	public Integer getInote7() {
		return this.inote7;
	}

	public void setInote7(Integer inote7) {
		this.inote7 = inote7;
	}

	public Integer getInote8() {
		return this.inote8;
	}

	public void setInote8(Integer inote8) {
		this.inote8 = inote8;
	}

	public Integer getInote9() {
		return this.inote9;
	}

	public void setInote9(Integer inote9) {
		this.inote9 = inote9;
	}

	public Integer getInote10() {
		return this.inote10;
	}

	public void setInote10(Integer inote10) {
		this.inote10 = inote10;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getUsqx1() {
		return usqx1;
	}

	public void setUsqx1(String usqx1) {
		this.usqx1 = usqx1;
	}

	public String getUsqx2() {
		return usqx2;
	}

	public void setUsqx2(String usqx2) {
		this.usqx2 = usqx2;
	}

	public String getUsqx3() {
		return usqx3;
	}

	public void setUsqx3(String usqx3) {
		this.usqx3 = usqx3;
	}

	public String getUsqx4() {
		return usqx4;
	}

	public void setUsqx4(String usqx4) {
		this.usqx4 = usqx4;
	}

	public String getUsqx5() {
		return usqx5;
	}

	public void setUsqx5(String usqx5) {
		this.usqx5 = usqx5;
	}

	public String getUsqx6() {
		return usqx6;
	}

	public void setUsqx6(String usqx6) {
		this.usqx6 = usqx6;
	}

	public String getUsqx7() {
		return usqx7;
	}

	public void setUsqx7(String usqx7) {
		this.usqx7 = usqx7;
	}

	public String getUsqx8() {
		return usqx8;
	}

	public void setUsqx8(String usqx8) {
		this.usqx8 = usqx8;
	}

	public String getUsqx9() {
		return usqx9;
	}

	public void setUsqx9(String usqx9) {
		this.usqx9 = usqx9;
	}

	public String getUsqx10() {
		return usqx10;
	}

	public void setUsqx10(String usqx10) {
		this.usqx10 = usqx10;
	}

	public String getUsqx11() {
		return usqx11;
	}

	public void setUsqx11(String usqx11) {
		this.usqx11 = usqx11;
	}

	public String getUsqx12() {
		return usqx12;
	}

	public void setUsqx12(String usqx12) {
		this.usqx12 = usqx12;
	}

	public String getUsqx13() {
		return usqx13;
	}

	public void setUsqx13(String usqx13) {
		this.usqx13 = usqx13;
	}

	public String getSzregionalname() {
		return szregionalname;
	}

	public void setSzregionalname(String szregionalname) {
		this.szregionalname = szregionalname;
	}

	public String getSzbusinessname() {
		return szbusinessname;
	}

	public void setSzbusinessname(String szbusinessname) {
		this.szbusinessname = szbusinessname;
	}
/*
	public Reservecontrol getReservecontrol() {
		return reservecontrol;
	}

	public void setReservecontrol(Reservecontrol reservecontrol) {
		this.reservecontrol = reservecontrol;
	}
	
	*/

	public String getDaoyouno() {
		return daoyouno;
	}

	public void setDaoyouno(String daoyouno) {
		this.daoyouno = daoyouno;
	}

	public String getAdder() {
		return adder;
	}

	public void setAdder(String adder) {
		this.adder = adder;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getQudaotype() {
		return qudaotype;
	}

	public void setQudaotype(String qudaotype) {
		this.qudaotype = qudaotype;
	}

	public String getWenti() {
		return wenti;
	}

	public void setWenti(String wenti) {
		this.wenti = wenti;
	}

	public String getQudaokey() {
		return qudaokey;
	}

	public void setQudaokey(String qudaokey) {
		this.qudaokey = qudaokey;
	}

	public String getWentikey() {
		return wentikey;
	}

	public void setWentikey(String wentikey) {
		this.wentikey = wentikey;
	}

	public Reservecontrol getReservecontrol() {
		return reservecontrol;
	}

	public void setReservecontrol(Reservecontrol reservecontrol) {
		this.reservecontrol = reservecontrol;
	}

	public String getDtmakedate() {
	    return dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
	    this.dtmakedate = dtmakedate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}