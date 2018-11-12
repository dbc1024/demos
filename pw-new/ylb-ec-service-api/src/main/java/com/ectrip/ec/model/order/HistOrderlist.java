package com.ectrip.ec.model.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TOrderlist entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HistOrderlist implements java.io.Serializable {

    // Fields

    private HistOrderlistId id;
    private String orderlistid;// ����������ȡ������
    private String orid;// ����������ȡ������
    private String iscenicid;
    private String sztickettypename;// ��Ʒ����
    private Long itickettypeid;// ��ƷID
    private Long icrowdkindpriceid;// �ۼ�ID
    private Long icrowdkindid;// ��Ⱥ����ID
    private String dtstartdate;// ��������
    private String dtenddate;// ʧЧ����
    private Double pric;// ����PRIC
    private Double jsprice;// ����۸�
    private Long numb;// ����NUMB
    private Long nnumb;// �������� ����Ӧ���ݿ�
    private Long yhnumb;// �Ż�����
    private Double amnt;// ���AMNT
    private Double yhamnt;// �Żݽ��
    private Long ioffersschemeid;// �Żݷ���ID
    private Long isa;
    private Long isb;
    private Long isc;
    private Long isd;
    private Long ise;
    private Long isf;
    private Long isg;
    private Long ish;
    private Long isi;
    private Long isj;
    private String notea;
    private String notej;
    private String notei;
    private String noteh;
    private String noteg;
    private String notef;
    private String notee;
    private String noted;
    private String notec;
    private String noteb;
    private List<HistZorderlist> zorderlist;// ����Ԥ��������
    private String zj;// �����ݿ��ֶ�
    private String szcrowdkindname;// �����ݿ��ֶ� ��¼�۸�����
    private Double mhandcharge;

    // �����ݿ��ֶ�
    private String scenictype;
    private String wharfname;
    private String tripname;
    private String wharftime;
    private String wharfdate;
    private String szscenicname;
    private List<TRealname> rlist;
    
    // Constructors

    public Double getMhandcharge() {
	return mhandcharge;
    }

    public void setMhandcharge(Double mhandcharge) {
	this.mhandcharge = mhandcharge;
    }

    public String getZj() {
	return zj;
    }

    public String getSzcrowdkindname() {
	return szcrowdkindname;
    }

    public void setSzcrowdkindname(String szcrowdkindname) {
	this.szcrowdkindname = szcrowdkindname;
    }

    public void setZj(String zj) {
	this.zj = zj;
    }

    /** default constructor */
    public HistOrderlist() {
    }

    /** minimal constructor */
    public HistOrderlist(HistOrderlistId id, Long itickettypeid, Long icrowdkindid, String dtstartdate, String dtenddate, Double pric, Long numb,
	    Double amnt) {
	this.id = id;
	this.itickettypeid = itickettypeid;
	this.icrowdkindid = icrowdkindid;
	this.dtstartdate = dtstartdate;
	this.dtenddate = dtenddate;
	this.pric = pric;
	this.numb = numb;
	this.amnt = amnt;
    }

    /** full constructor */
    public HistOrderlist(HistOrderlistId id, Long itickettypeid, Long icrowdkindpriceid, Long icrowdkindid, String dtstartdate, String dtenddate,
	    Double pric, Double jsprice, Long numb, Long yhnumb, Double amnt, Double yhamnt, Long ioffersschemeid, Long isa, Long isb,
	    Long isc, Long isd, Long ise, Long isf, Long isg, Long ish, Long isi, Long isj, String notea, String notej, String notei,
	    String noteh, String noteg, String notef, String notee, String noted, String notec, String noteb, Set TZorderlists) {
	this.id = id;
	this.itickettypeid = itickettypeid;
	this.icrowdkindpriceid = icrowdkindpriceid;
	this.icrowdkindid = icrowdkindid;
	this.dtstartdate = dtstartdate;
	this.dtenddate = dtenddate;
	this.pric = pric;
	this.jsprice = jsprice;
	this.numb = numb;
	this.yhnumb = yhnumb;
	this.amnt = amnt;
	this.yhamnt = yhamnt;
	this.ioffersschemeid = ioffersschemeid;
	this.isa = isa;
	this.isb = isb;
	this.isc = isc;
	this.isd = isd;
	this.ise = ise;
	this.isf = isf;
	this.isg = isg;
	this.ish = ish;
	this.isi = isi;
	this.isj = isj;
	this.notea = notea;
	this.notej = notej;
	this.notei = notei;
	this.noteh = noteh;
	this.noteg = noteg;
	this.notef = notef;
	this.notee = notee;
	this.noted = noted;
	this.notec = notec;
	this.noteb = noteb;

    }

    // Property accessors

    public List<HistZorderlist> getZorderlist() {
	return zorderlist;
    }

    public void setZorderlist(List<HistZorderlist> zorderlist) {
	this.zorderlist = zorderlist;
    }

    public HistOrderlistId getId() {
	return this.id;
    }

    public void setId(HistOrderlistId id) {
	this.id = id;
    }

    public Long getItickettypeid() {
	return this.itickettypeid;
    }

    public void setItickettypeid(Long itickettypeid) {
	this.itickettypeid = itickettypeid;
    }

    public Long getIcrowdkindpriceid() {
	return this.icrowdkindpriceid;
    }

    public void setIcrowdkindpriceid(Long icrowdkindpriceid) {
	this.icrowdkindpriceid = icrowdkindpriceid;
    }

    public Long getIcrowdkindid() {
	return this.icrowdkindid;
    }

    public void setIcrowdkindid(Long icrowdkindid) {
	this.icrowdkindid = icrowdkindid;
    }

    public String getDtstartdate() {
	return this.dtstartdate;
    }

    public void setDtstartdate(String dtstartdate) {
	this.dtstartdate = dtstartdate;
    }

    public String getDtenddate() {
	return this.dtenddate;
    }

    public void setDtenddate(String dtenddate) {
	this.dtenddate = dtenddate;
    }

    public Double getPric() {
	return this.pric;
    }

    public void setPric(Double pric) {
	this.pric = pric;
    }

    public Double getJsprice() {
	return jsprice;
    }

    public void setJsprice(Double jsprice) {
	this.jsprice = jsprice;
    }

    public Long getNumb() {
	return this.numb;
    }

    public void setNumb(Long numb) {
	this.numb = numb;
    }

    public Long getYhnumb() {
	return this.yhnumb;
    }

    public void setYhnumb(Long yhnumb) {
	this.yhnumb = yhnumb;
    }

    public Double getAmnt() {
	return this.amnt;
    }

    public void setAmnt(Double amnt) {
	this.amnt = amnt;
    }

    public Double getYhamnt() {
	return this.yhamnt;
    }

    public void setYhamnt(Double yhamnt) {
	this.yhamnt = yhamnt;
    }

    public Long getIoffersschemeid() {
	return this.ioffersschemeid;
    }

    public void setIoffersschemeid(Long ioffersschemeid) {
	this.ioffersschemeid = ioffersschemeid;
    }

    public Long getIsa() {
	return this.isa;
    }

    public void setIsa(Long isa) {
	this.isa = isa;
    }

    public Long getIsb() {
	return this.isb;
    }

    public void setIsb(Long isb) {
	this.isb = isb;
    }

    public Long getIsc() {
	return this.isc;
    }

    public void setIsc(Long isc) {
	this.isc = isc;
    }

    public Long getIsd() {
	return this.isd;
    }

    public void setIsd(Long isd) {
	this.isd = isd;
    }

    public Long getIse() {
	return this.ise;
    }

    public void setIse(Long ise) {
	this.ise = ise;
    }

    public Long getIsf() {
	return this.isf;
    }

    public void setIsf(Long isf) {
	this.isf = isf;
    }

    public Long getIsg() {
	return this.isg;
    }

    public void setIsg(Long isg) {
	this.isg = isg;
    }

    public Long getIsh() {
	return this.ish;
    }

    public void setIsh(Long ish) {
	this.ish = ish;
    }

    public Long getIsi() {
	return this.isi;
    }

    public void setIsi(Long isi) {
	this.isi = isi;
    }

    public Long getIsj() {
	return this.isj;
    }

    public void setIsj(Long isj) {
	this.isj = isj;
    }

    public String getNotea() {
	return this.notea;
    }

    public void setNotea(String notea) {
	this.notea = notea;
    }

    public String getNotej() {
	return this.notej;
    }

    public void setNotej(String notej) {
	this.notej = notej;
    }

    public String getNotei() {
	return this.notei;
    }

    public void setNotei(String notei) {
	this.notei = notei;
    }

    public String getNoteh() {
	return this.noteh;
    }

    public void setNoteh(String noteh) {
	this.noteh = noteh;
    }

    public String getNoteg() {
	return this.noteg;
    }

    public void setNoteg(String noteg) {
	this.noteg = noteg;
    }

    public String getNotef() {
	return this.notef;
    }

    public void setNotef(String notef) {
	this.notef = notef;
    }

    public String getNotee() {
	return this.notee;
    }

    public void setNotee(String notee) {
	this.notee = notee;
    }

    public String getNoted() {
	return this.noted;
    }

    public void setNoted(String noted) {
	this.noted = noted;
    }

    public String getNotec() {
	return this.notec;
    }

    public void setNotec(String notec) {
	this.notec = notec;
    }

    public String getNoteb() {
	return this.noteb;
    }

    public void setNoteb(String noteb) {
	this.noteb = noteb;
    }

    public String getSztickettypename() {
	return sztickettypename;
    }

    public void setSztickettypename(String sztickettypename) {
	this.sztickettypename = sztickettypename;
    }

    public String getIscenicid() {
	return iscenicid;
    }

    public void setIscenicid(String iscenicid) {
	this.iscenicid = iscenicid;
    }

    public String getOrderlistid() {
	return orderlistid;
    }

    public void setOrderlistid(String orderlistid) {
	this.orderlistid = orderlistid;
    }

    public String getOrid() {
	return orid;
    }

    public void setOrid(String orid) {
	this.orid = orid;
    }

    public Long getNnumb() {
	return nnumb;
    }

    public void setNnumb(Long nnumb) {
	this.nnumb = nnumb;
    }

    public String getWharfname() {
	return wharfname;
    }

    public void setWharfname(String wharfname) {
	this.wharfname = wharfname;
    }

    public String getTripname() {
	return tripname;
    }

    public void setTripname(String tripname) {
	this.tripname = tripname;
    }

    public String getWharftime() {
	return wharftime;
    }

    public void setWharftime(String wharftime) {
	this.wharftime = wharftime;
    }

    public String getWharfdate() {
	return wharfdate;
    }

    public void setWharfdate(String wharfdate) {
	this.wharfdate = wharfdate;
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

	public List<TRealname> getRlist() {
		return rlist;
	}

	public void setRlist(List<TRealname> rlist) {
		this.rlist = rlist;
	}
    
}