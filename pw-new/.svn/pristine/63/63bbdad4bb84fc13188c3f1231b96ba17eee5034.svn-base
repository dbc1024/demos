package com.ectrip.ticket.model.salesmanager;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 园门卡证 entity.<br>
 *
 * @author Jzhenhua,Created TIME 2011-10-06
 */
@Entity
@Table
public class Opcemployeecardtab implements java.io.Serializable {

    // Fields
	@Id
    private Long iemployeecardid;// 卡证ID
    private Long iemployeeid;// 员工
    private Long itickettypeid; // 产品ID
    private String iagentno;// 放行标识　　为１　放行卡
    private String icardno;// 卡号
    private String szticketprintno;// 卡证印刷号
    private String iserialnum;// 落杠标识　　为１　落杠
    private String byticketstate;// 卡证状态,0:正常,1::禁用
    private String szmemo;// 备注
    private Double mremainmoney;// 余额
    private Double mpresentmoney;// 优惠金额
    private Long ipresentnum;// 优惠数量
    private Long iremainnum;// 剩余数量
    private String byconsumetype;//是否入园  0否  1 是    消费类型,0:按通过次数,1:按消费金额,2:通过次数+消费金额,3:按总次数,4:按总金额,5:按总次数+总金额,6:按分钟
    private Long iversion;// 版本
    private String usid;		//导游名称
    private Long byisdaoyou;   //是否导游

    private String szemployname;  //用户姓名
    private String dtstartdate;   //开始日期
    private String dtenddate;     //截止日期
    private String emptype;       //用户类型
    private Long jpnumbs;         //检票次数
    private Long yjnumbs;         //已检次数
    private String memoyy;        //语音提示
    private Long isa;             //是否VIP卡
    private Long isb;
    private Long isc;
    private Long isd;
    private Long ise;
    private Long isf;
    private String notea;
    private String noteb;
    private String notec;
    private String noted;
    private String notee;
    private String notef;

    // 非数据库字段
    @Transient
    private String strbyconsumetype;//卡证状态
    @Transient
    private String strbyticketstate;//消费类型
    @Transient
    private String striemployeeid;//员工姓名
    @Transient
    private String sztickettypename; //产品名称


    public String getSztickettypename() {
        return sztickettypename;
    }

    public void setSztickettypename(String sztickettypename) {
        this.sztickettypename = sztickettypename;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Long getByisdaoyou() {
        return byisdaoyou;
    }

    public void setByisdaoyou(Long byisdaoyou) {
        this.byisdaoyou = byisdaoyou;
    }

    public String getStriemployeeid() {
        return striemployeeid;
    }

    public void setStriemployeeid(String striemployeeid) {
        this.striemployeeid = striemployeeid;
    }

    
    @Transient
    private Set opcemployeecardsubtabs = new HashSet(0);

    // Constructors

    /** default constructor */
    public Opcemployeecardtab() {
    }

    /** minimal constructor */
    public Opcemployeecardtab(Long iemployeecardid,
                              Long iemployeeid, Long itickettypeid,
                              String iagentno, String icardno, String szticketprintno,
                              String iserialnum, String byticketstate) {
        this.iemployeecardid = iemployeecardid;
        this.iemployeeid = iemployeeid;
        this.itickettypeid = itickettypeid;
        this.iagentno = iagentno;
        this.icardno = icardno;
        this.szticketprintno = szticketprintno;
        this.iserialnum = iserialnum;
        this.byticketstate = byticketstate;
    }

    /** full constructor */
    public Opcemployeecardtab(Long iemployeecardid,
                              Long iemployeeid, Long itickettypeid,
                              String iagentno, String icardno, String szticketprintno,
                              String iserialnum, String byticketstate, String szmemo,
                              Double mremainmoney, Double mpresentmoney, Long ipresentnum,
                              Long iremainnum, String byconsumetype,
                              Long iversion, Set opcemployeecardsubtabs) {
        this.iemployeecardid = iemployeecardid;
        this.iemployeeid = iemployeeid;
        this.itickettypeid = itickettypeid;
        this.iagentno = iagentno;
        this.icardno = icardno;
        this.szticketprintno = szticketprintno;
        this.iserialnum = iserialnum;
        this.byticketstate = byticketstate;
        this.szmemo = szmemo;
        this.mremainmoney = mremainmoney;
        this.mpresentmoney = mpresentmoney;
        this.ipresentnum = ipresentnum;
        this.iremainnum = iremainnum;
        this.byconsumetype = byconsumetype;
        this.iversion = iversion;
        this.opcemployeecardsubtabs = opcemployeecardsubtabs;
    }

    // Property accessors

    public Long getIemployeecardid() {
        return this.iemployeecardid;
    }

    public void setIemployeecardid(Long iemployeecardid) {
        this.iemployeecardid = iemployeecardid;
    }

    public void setIemployeecardid(String iemployeecardid){
        if (null != iemployeecardid && !"".equals(iemployeecardid))
            this.iemployeecardid = new Long(iemployeecardid);
    }

    public Long getItickettypeid() {
        return itickettypeid;
    }

    public void setItickettypeid(Long itickettypeid) {
        this.itickettypeid = itickettypeid;
    }

    public String getIagentno() {
        return this.iagentno;
    }

    public void setIagentno(String iagentno) {
        this.iagentno = iagentno;
    }

    public String getIcardno() {
        return this.icardno;
    }

    public void setIcardno(String icardno) {
        this.icardno = icardno;
    }

    public String getSzticketprintno() {
        return this.szticketprintno;
    }

    public void setSzticketprintno(String szticketprintno) {
        this.szticketprintno = szticketprintno;
    }

    public String getIserialnum() {
        return this.iserialnum;
    }

    public void setIserialnum(String iserialnum) {
        this.iserialnum = iserialnum;
    }

    public String getSzmemo() {
        return this.szmemo;
    }

    public void setSzmemo(String szmemo) {
        this.szmemo = szmemo;
    }

    public Double getMremainmoney() {
        return this.mremainmoney;
    }

    public void setMremainmoney(Double mremainmoney) {
        this.mremainmoney = mremainmoney;
    }

    public Double getMpresentmoney() {
        return this.mpresentmoney;
    }

    public void setMpresentmoney(Double mpresentmoney) {
        this.mpresentmoney = mpresentmoney;
    }

    public Long getIpresentnum() {
        return this.ipresentnum;
    }

    public void setIpresentnum(Long ipresentnum) {
        this.ipresentnum = ipresentnum;
    }

    public void setIpresentnum(String ipresentnum) {
        if (null != ipresentnum && !"".equals(ipresentnum))
            this.ipresentnum = new Long(ipresentnum);
    }

    public Long getIremainnum() {
        return this.iremainnum;
    }

    public void setIremainnum(Long iremainnum) {
        this.iremainnum = iremainnum;
    }

    public void setIremainnum(String iremainnum) {
        if (null != iremainnum && !"".equals(iremainnum))
            this.iremainnum = new Long(iremainnum);
    }

    public String getByticketstate() {
        return byticketstate;
    }

    public void setByticketstate(String byticketstate) {
        this.byticketstate = byticketstate;
    }

    public String getByconsumetype() {
        return byconsumetype;
    }

    public void setByconsumetype(String byconsumetype) {
        this.byconsumetype = byconsumetype;
    }

    public Long getIversion() {
        return this.iversion;
    }

    public void setIversion(Long iversion) {
        this.iversion = iversion;
    }

    public Set getOpcemployeecardsubtabs() {
        return this.opcemployeecardsubtabs;
    }

    public void setOpcemployeecardsubtabs(Set opcemployeecardsubtabs) {
        this.opcemployeecardsubtabs = opcemployeecardsubtabs;
    }

    public Long getIemployeeid() {
        return iemployeeid;
    }

    public void setIemployeeid(Long iemployeeid) {
        this.iemployeeid = iemployeeid;
    }

    public String getStrbyconsumetype() {
        return strbyconsumetype;
    }

    public void setStrbyconsumetype(String strbyconsumetype) {
        this.strbyconsumetype = strbyconsumetype;
    }

    public String getStrbyticketstate() {
        return strbyticketstate;
    }

    public void setStrbyticketstate(String strbyticketstate) {
        this.strbyticketstate = strbyticketstate;
    }

    public String getSzemployname() {
        return szemployname;
    }

    public void setSzemployname(String szemployname) {
        this.szemployname = szemployname;
    }

    public String getDtstartdate() {
        return dtstartdate;
    }

    public void setDtstartdate(String dtstartdate) {
        this.dtstartdate = dtstartdate;
    }

    public String getDtenddate() {
        return dtenddate;
    }

    public void setDtenddate(String dtenddate) {
        this.dtenddate = dtenddate;
    }

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }

    public Long getJpnumbs() {
        return jpnumbs;
    }

    public void setJpnumbs(Long jpnumbs) {
        this.jpnumbs = jpnumbs;
    }

    public Long getYjnumbs() {
        return yjnumbs;
    }

    public void setYjnumbs(Long yjnumbs) {
        this.yjnumbs = yjnumbs;
    }

    public String getMemoyy() {
        return memoyy;
    }

    public void setMemoyy(String memoyy) {
        this.memoyy = memoyy;
    }

    public Long getIsa() {
        return isa;
    }

    public void setIsa(Long isa) {
        this.isa = isa;
    }

    public Long getIsb() {
        return isb;
    }

    public void setIsb(Long isb) {
        this.isb = isb;
    }

    public Long getIsc() {
        return isc;
    }

    public void setIsc(Long isc) {
        this.isc = isc;
    }

    public Long getIsd() {
        return isd;
    }

    public void setIsd(Long isd) {
        this.isd = isd;
    }

    public Long getIse() {
        return ise;
    }

    public void setIse(Long ise) {
        this.ise = ise;
    }

    public Long getIsf() {
        return isf;
    }

    public void setIsf(Long isf) {
        this.isf = isf;
    }

    public String getNotea() {
        return notea;
    }

    public void setNotea(String notea) {
        this.notea = notea;
    }

    public String getNoteb() {
        return noteb;
    }

    public void setNoteb(String noteb) {
        this.noteb = noteb;
    }

    public String getNotec() {
        return notec;
    }

    public void setNotec(String notec) {
        this.notec = notec;
    }

    public String getNoted() {
        return noted;
    }

    public void setNoted(String noted) {
        this.noted = noted;
    }

    public String getNotee() {
        return notee;
    }

    public void setNotee(String notee) {
        this.notee = notee;
    }

    public String getNotef() {
        return notef;
    }

    public void setNotef(String notef) {
        this.notef = notef;
    }
}