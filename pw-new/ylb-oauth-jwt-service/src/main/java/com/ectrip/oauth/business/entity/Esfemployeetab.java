package com.ectrip.oauth.business.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Esfemployeetab entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Esfemployeetab")
public class Esfemployeetab implements UserDetails {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long iemployeeid;
	@Column(name="ideptid")
	private Long ideptid;             //部门ID
	@Column(name="icompanyinfoid")
	private Long icompanyinfoid;      //公司ID
	@Column(name="bissysuser")
	private Long bissysuser;          //可否使用系统
	@Column(name="szpassword")
	private String szpassword;		  //登录密码
	@Column(name="byloginitype")
	private String byloginitype;    //登录方式
	@Column(name="empid")
	private String empid;			//登录用户
	@Column(name="szemployeename")
	private String szemployeename;	//用户名称
	@Column(name="isex")
	private Long isex;				//用户性别
	@Column(name="byisuse")
	private Long byisuse;			//启用状态
	@Column(name="szcardid")
	private String szcardid;		//身份证号
	@Column(name="dtbirthdate")
	private String dtbirthdate;		//出身日期
	@Column(name="photoupid")
	private Long photoupid;			//照片
	@Column(name="sznation")
	private String sznation;		//民族
	@Column(name="bismarry")
	private Long bismarry;			//婚姻状况
	@Column(name="dtentrydate")
	private String dtentrydate;		//入职时间
	@Column(name="szpost")
	private String szpost;			//职务
	@Column(name="szcellphone")
	private String szcellphone;		//联系电话
	@Column(name="szemail")
	private String szemail;			//电子邮箱
	@Column(name="iborthaddress")
	private Long iborthaddress;		//籍贯
	@Column(name="szaccountlocation")
	private String szaccountlocation;//户口所在地
	@Column(name="szhomeaddress")
	private String szhomeaddress;	//家庭地址
	@Column(name="educational")
	private String educational;		//学历
	@Column(name="major")
	private String major;			//专业
	@Column(name="professional")
	private String professional;	//职称
	@Column(name="szschool")
	private String szschool;		//毕业学校
	@Column(name="dtgraduatedate")
	private String dtgraduatedate;	//毕业日期
	@Column(name="szmemo")
	private String szmemo;			//备注
	@Column(name="dnum")
	private Long dnum;				//总登录次数
	@Column(name="ztimes")
	private Long ztimes;			//当日错误登录次数
	@Column(name="logintime")
	private String logintime;		//最后登录时间
	@Column(name="ipaddress")
	private String ipaddress;		//最后登录地址
	@Transient
	private String rzpwd;           //认证码
	@Column(name="emptype")
	private Long emptype;         //用户类别   0--平台用户  1--景区用户 
	// Constructors
  //非系统字段
	@Transient
	private String random;
	@Column(name="rzpwd")
	private String password;
	@Transient
	private String scenics;//对应管理公司ID组合方式为iscenicid,iscenicid
	@Transient
	private String  dutys;//对应职责ID按idutyid,idutyid组成
	@Transient
	private String szcompanyinfoname;//公司名称
	@Transient
	private String emppwd;//登陆时输入的密码
	@Transient
	private String lasttime;//上次时间
	@Transient
	private String lastipaddress;//上次IP
	@Transient
	private String companytype;
	@Transient
	private String szinnername;   //所属地区
	@Transient
	private String acpicupfilename;
	@Transient
	private String acpicadder;
	@Transient
	private Long[] iscenicids; 
	@Transient
	private String btnDuty; //保存员工按钮权限
	
	public String getBtnDuty() {
		return btnDuty;
	}

	public void setBtnDuty(String btnDuty) {
		this.btnDuty = btnDuty;
	}

	public Long[] getIscenicids() {
		return iscenicids;
	}

	public void setIscenicids(Long[] iscenicids) {
		this.iscenicids = iscenicids;
	}

	public String getAcpicupfilename() {
		return acpicupfilename;
	}

	public void setAcpicupfilename(String acpicupfilename) {
		this.acpicupfilename = acpicupfilename;
	}

	public String getAcpicadder() {
		return acpicadder;
	}

	public void setAcpicadder(String acpicadder) {
		this.acpicadder = acpicadder;
	}

	public String getCompanytype() {
		return companytype;
	}

	public void setCompanytype(String companytype) {
		this.companytype = companytype;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	

	public String getLastipaddress() {
		return lastipaddress;
	}

	public void setLastipaddress(String lastipaddress) {
		this.lastipaddress = lastipaddress;
	}

	public String getScenics() {
		return scenics;
	}

	public void setScenics(String scenics) {
		this.scenics = scenics;
	}

	public String getSzcompanyinfoname() {
		return szcompanyinfoname;
	}

	public void setSzcompanyinfoname(String szcompanyinfoname) {
		this.szcompanyinfoname = szcompanyinfoname;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	/** default constructor */
	public Esfemployeetab() {
	}

	/** minimal constructor */
	public Esfemployeetab(Long iemployeeid, Long ideptid, Long icompanyinfoid, Long bissysuser,
			String szpassword, String byloginitype, String empid,
			String szemployeename, Long isex, Long byisuse, String szcardid,
			Long photoupid) {
		this.iemployeeid = iemployeeid;
		this.ideptid=ideptid;
		this.icompanyinfoid = icompanyinfoid;
		this.bissysuser = bissysuser;
		this.szpassword = szpassword;
		this.byloginitype = byloginitype;
		this.empid = empid;
		this.szemployeename = szemployeename;
		this.isex = isex;
		this.byisuse = byisuse;
		this.szcardid = szcardid;
		this.photoupid = photoupid;
	}

	/** full constructor */
	public Esfemployeetab(Long iemployeeid, Long ideptid,
			Long icompanyinfoid, Long bissysuser, String szpassword,
			String byloginitype, String empid, String szemployeename, Long isex,
			Long byisuse, String szcardid, String dtbirthdate, Long photoupid,
			String sznation, Long bismarry, String dtentrydate, String szpost,
			String szcellphone, String szemail, Long iborthaddress,
			String szaccountlocation, String szhomeaddress, String educational,
			String major, String professional, String szschool,
			String dtgraduatedate, String szmemo, Long dnum, Long ztimes,
			String logintime, String ipaddress,String rzpwd,Long emptype) {
		this.iemployeeid = iemployeeid;
		this.ideptid = ideptid;
		this.icompanyinfoid = icompanyinfoid;
		this.bissysuser = bissysuser;
		this.szpassword = szpassword;
		this.byloginitype = byloginitype;
		this.empid = empid;
		this.szemployeename = szemployeename;
		this.isex = isex;
		this.byisuse = byisuse;
		this.szcardid = szcardid;
		this.dtbirthdate = dtbirthdate;
		this.photoupid = photoupid;
		this.sznation = sznation;
		this.bismarry = bismarry;
		this.dtentrydate = dtentrydate;
		this.szpost = szpost;
		this.szcellphone = szcellphone;
		this.szemail = szemail;
		this.iborthaddress = iborthaddress;
		this.szaccountlocation = szaccountlocation;
		this.szhomeaddress = szhomeaddress;
		this.educational = educational;
		this.major = major;
		this.professional = professional;
		this.szschool = szschool;
		this.dtgraduatedate = dtgraduatedate;
		this.szmemo = szmemo;
		this.dnum = dnum;
		this.ztimes = ztimes;
		this.logintime = logintime;
		this.ipaddress = ipaddress;
		this.rzpwd=rzpwd;
		this.emptype=emptype;
	}

	// Property accessors

	public Long getIemployeeid() {
		return this.iemployeeid;
	}

	public void setIemployeeid(Long iemployeeid) {
		this.iemployeeid = iemployeeid;
	}

	public Long getIdeptid() {
		return ideptid;
	}

	public void setIdeptid(Long ideptid) {
		this.ideptid = ideptid;
	}

	public Long getIcompanyinfoid() {
		return this.icompanyinfoid;
	}

	public void setIcompanyinfoid(Long icompanyinfoid) {
		this.icompanyinfoid = icompanyinfoid;
	}

	public Long getBissysuser() {
		return this.bissysuser;
	}

	public void setBissysuser(Long bissysuser) {
		this.bissysuser = bissysuser;
	}

	public String getSzpassword() {
		return this.szpassword;
	}

	public void setSzpassword(String szpassword) {
		this.szpassword = szpassword;
	}

	public String getByloginitype() {
		return this.byloginitype;
	}

	public void setByloginitype(String byloginitype) {
		this.byloginitype = byloginitype;
	}

	public String getEmpid() {
		return this.empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getSzemployeename() {
		return this.szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

	public Long getIsex() {
		return this.isex;
	}

	public void setIsex(Long isex) {
		this.isex = isex;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzcardid() {
		return this.szcardid;
	}

	public void setSzcardid(String szcardid) {
		this.szcardid = szcardid;
	}

	public String getDtbirthdate() {
		return this.dtbirthdate;
	}

	public void setDtbirthdate(String dtbirthdate) {
		this.dtbirthdate = dtbirthdate;
	}

	public Long getPhotoupid() {
		return this.photoupid;
	}

	public void setPhotoupid(Long photoupid) {
		this.photoupid = photoupid;
	}

	public String getSznation() {
		return this.sznation;
	}

	public void setSznation(String sznation) {
		this.sznation = sznation;
	}

	public Long getBismarry() {
		return this.bismarry;
	}

	public void setBismarry(Long bismarry) {
		this.bismarry = bismarry;
	}

	public String getDtentrydate() {
		return this.dtentrydate;
	}

	public void setDtentrydate(String dtentrydate) {
		this.dtentrydate = dtentrydate;
	}

	public String getSzpost() {
		return this.szpost;
	}

	public void setSzpost(String szpost) {
		this.szpost = szpost;
	}

	public String getSzcellphone() {
		return this.szcellphone;
	}

	public void setSzcellphone(String szcellphone) {
		this.szcellphone = szcellphone;
	}

	public String getSzemail() {
		return this.szemail;
	}

	public void setSzemail(String szemail) {
		this.szemail = szemail;
	}

	public Long getIborthaddress() {
		return this.iborthaddress;
	}

	public void setIborthaddress(Long iborthaddress) {
		this.iborthaddress = iborthaddress;
	}

	public String getSzaccountlocation() {
		return this.szaccountlocation;
	}

	public void setSzaccountlocation(String szaccountlocation) {
		this.szaccountlocation = szaccountlocation;
	}

	public String getSzhomeaddress() {
		return this.szhomeaddress;
	}

	public void setSzhomeaddress(String szhomeaddress) {
		this.szhomeaddress = szhomeaddress;
	}

	public String getEducational() {
		return this.educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getSzschool() {
		return this.szschool;
	}

	public void setSzschool(String szschool) {
		this.szschool = szschool;
	}

	public String getDtgraduatedate() {
		return this.dtgraduatedate;
	}

	public void setDtgraduatedate(String dtgraduatedate) {
		this.dtgraduatedate = dtgraduatedate;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getDnum() {
		return this.dnum;
	}

	public void setDnum(Long dnum) {
		this.dnum = dnum;
	}

	public Long getZtimes() {
		return this.ztimes;
	}

	public void setZtimes(Long ztimes) {
		this.ztimes = ztimes;
	}

	public String getLogintime() {
		return this.logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getEmppwd() {
		return emppwd;
	}

	public void setEmppwd(String emppwd) {
		this.emppwd = emppwd;
	}

	public String getDutys() {
		return dutys;
	}

	public void setDutys(String dutys) {
		this.dutys = dutys;
	}

	public String getSzinnername() {
		return szinnername;
	}

	public void setSzinnername(String szinnername) {
		this.szinnername = szinnername;
	}

	public String getRzpwd() {
		return rzpwd;
	}

	public void setRzpwd(String rzpwd) {
		this.rzpwd = rzpwd;
	}

	public Long getEmptype() {
		return emptype;
	}

	public void setEmptype(Long emptype) {
		this.emptype = emptype;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return auths;
	}

	@Override
	public String getUsername() {
		return this.empid;
	}

	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}