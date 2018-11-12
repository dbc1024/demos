package com.ectrip.ec.model.order;

import java.util.HashSet;
import java.util.Set;

/**
 * TOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HistOrder implements java.io.Serializable{

	// Fields

	private HistOrderId id;
	private String orid;
	private String iscenicid;
	private String szscenicname;//��������
	private String scenictype;//��������� 01 ���� 06�Ƶ� 07 ������ 08 ���� 09 ���޹�˾
	private String orfl; //��������
	private String usid;//�οͱ��
	private Long ibusinessid;//ҵ��id
	private String sztravelbillno;//�г̵���
	private Long iregionalid;//��Դ��id
	private String tdlx;//�Ŷ�����
	private String ddzt;//�Ӷ���״̬
	private String dtstartdate;//��������
	private String dtenddate;//ʧЧ����
	private String dyusid;//����id
	private String ornm;//��Ʊ������
	private String orzj;//��Ʊ��֤�����
	private String orhm;//��Ʊ��֤������
	private String orph;//��Ʊ�˵绰
	private String faxno;//�����
	private Double mont;//�������
	private Double yhamnt;//�Żݽ��
	private Double zfmont;//֧���Ľ��
	private Double youfei;//�ʷ�YOUFEI
	private Long isjfjf;//�Ƿ��Ѿ��������
	private Long ischupiao;//��Ʊ����״̬
	private String fempid;//ǿ���˶�Ա
	private String forcedrefund;//ǿ����Ʊԭ��
	private Long isa;
	private Long isb;
	private Long isc;
	private Long isd;
	private Long ise;
	private Long isf;
	private Long isg;
	private Long ish;
	private Long isi;//���Ļ���
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
	private Double tpmony;   //��Ʊ���
	//�û�����
	private String orda;
	private String corpname;
	//֧��ʱ��
	private String stdt;
	
	//�Ƿ��ֱ�ӳ�Ʊ
	private String isprint;
	// Constructors
	private boolean caneditday;//�Ƿ���޸��״���������
	
	private boolean canedit=false;//�Ƿ���޸�
	private boolean canback=false;//�Ƿ����
	private boolean canview=false;//�Ƿ�ɲ鿴
	/** default constructor */
	public HistOrder() {
	}

	/** minimal constructor */
	public HistOrder(HistOrderId id, String scenictype, String orfl, String usid,
			Long ibusinessid, String ddzt, String dtstartdate,
			String dtenddate, String ornm, String orzj, String orhm,
			Double mont, Double zfmont) {
		this.id = id;
		this.scenictype = scenictype;
		this.orfl = orfl;
		this.usid = usid;
		this.ibusinessid = ibusinessid;
		this.ddzt = ddzt;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.ornm = ornm;
		this.orzj = orzj;
		this.orhm = orhm;
		this.mont = mont;
		this.zfmont = zfmont;
	}

	/** full constructor */
	public HistOrder(HistOrderId id, String scenictype, String orfl, String usid,
			Long ibusinessid, String sztravelbillno, Long iregionalid,
			String tdlx, String ddzt, String dtstartdate, String dtenddate,
			String dyusid, String ornm, String orzj, String orhm, String orph,
			String faxno, Double mont, Double yhamnt, Double zfmont,
			Double youfei, Long isjfjf, Long ischupiao, String fempid,
			String forcedrefund, Long isa, Long isb, Long isc, Long isd,
			Long ise, Long isf, Long isg, Long ish, Long isi, Long isj,
			String notea, String notej, String notei, String noteh,
			String noteg, String notef, String notee, String noted,
			String notec, String noteb, Set TOrderlists) {
		this.id = id;
		this.scenictype = scenictype;
		this.orfl = orfl;
		this.usid = usid;
		this.ibusinessid = ibusinessid;
		this.sztravelbillno = sztravelbillno;
		this.iregionalid = iregionalid;
		this.tdlx = tdlx;
		this.ddzt = ddzt;
		this.dtstartdate = dtstartdate;
		this.dtenddate = dtenddate;
		this.dyusid = dyusid;
		this.ornm = ornm;
		this.orzj = orzj;
		this.orhm = orhm;
		this.orph = orph;
		this.faxno = faxno;
		this.mont = mont;
		this.yhamnt = yhamnt;
		this.zfmont = zfmont;
		this.youfei = youfei;
		this.isjfjf = isjfjf;
		this.ischupiao = ischupiao;
		this.fempid = fempid;
		this.forcedrefund = forcedrefund;
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

	public HistOrderId getId() {
		return this.id;
	}

	public void setId(HistOrderId id) {
		this.id = id;
	}

	public String getScenictype() {
		return this.scenictype;
	}

	public void setScenictype(String scenictype) {
		this.scenictype = scenictype;
	}

	public String getOrfl() {
		return this.orfl;
	}

	public void setOrfl(String orfl) {
		this.orfl = orfl;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getIbusinessid() {
		return this.ibusinessid;
	}

	public void setIbusinessid(Long ibusinessid) {
		this.ibusinessid = ibusinessid;
	}

	public String getSztravelbillno() {
		return this.sztravelbillno;
	}

	public void setSztravelbillno(String sztravelbillno) {
		this.sztravelbillno = sztravelbillno;
	}

	public Long getIregionalid() {
		return this.iregionalid;
	}

	public void setIregionalid(Long iregionalid) {
		this.iregionalid = iregionalid;
	}

	public String getTdlx() {
		return this.tdlx;
	}

	public void setTdlx(String tdlx) {
		this.tdlx = tdlx;
	}

	public String getDdzt() {
		return this.ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
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

	public String getDyusid() {
		return this.dyusid;
	}

	public void setDyusid(String dyusid) {
		this.dyusid = dyusid;
	}

	public String getOrnm() {
		return this.ornm;
	}

	public void setOrnm(String ornm) {
		this.ornm = ornm;
	}

	public String getOrzj() {
		return this.orzj;
	}

	public void setOrzj(String orzj) {
		this.orzj = orzj;
	}

	public String getOrhm() {
		return this.orhm;
	}

	public void setOrhm(String orhm) {
		this.orhm = orhm;
	}

	public String getOrph() {
		return this.orph;
	}

	public void setOrph(String orph) {
		this.orph = orph;
	}

	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public Double getMont() {
		return this.mont;
	}

	public void setMont(Double mont) {
		this.mont = mont;
	}

	public Double getYhamnt() {
		return this.yhamnt;
	}

	public void setYhamnt(Double yhamnt) {
		this.yhamnt = yhamnt;
	}

	public Double getZfmont() {
		return this.zfmont;
	}

	public void setZfmont(Double zfmont) {
		this.zfmont = zfmont;
	}

	public Double getYoufei() {
		return this.youfei;
	}

	public void setYoufei(Double youfei) {
		this.youfei = youfei;
	}

	public Long getIsjfjf() {
		return this.isjfjf;
	}

	public void setIsjfjf(Long isjfjf) {
		this.isjfjf = isjfjf;
	}

	public Long getIschupiao() {
		return this.ischupiao;
	}

	public void setIschupiao(Long ischupiao) {
		this.ischupiao = ischupiao;
	}

	public String getFempid() {
		return this.fempid;
	}

	public void setFempid(String fempid) {
		this.fempid = fempid;
	}

	public String getForcedrefund() {
		return this.forcedrefund;
	}

	public void setForcedrefund(String forcedrefund) {
		this.forcedrefund = forcedrefund;
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

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	public String getOrid() {
		return orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getStdt() {
		return stdt;
	}

	public void setStdt(String stdt) {
		this.stdt = stdt;
	}

	public String getOrda() {
		return orda;
	}

	public void setOrda(String orda) {
		this.orda = orda;
	}

	public boolean isCaneditday() {
	    return caneditday;
	}

	public void setCaneditday(boolean caneditday) {
	    this.caneditday = caneditday;
	}

	public Double getTpmony() {
	    return tpmony;
	}

	public void setTpmony(Double tpmony) {
	    this.tpmony = tpmony;
	}

	public String getIsprint() {
	    return isprint;
	}

	public void setIsprint(String isprint) {
	    this.isprint = isprint;
	}

	public boolean isCanedit() {
		return canedit;
	}

	public void setCanedit(boolean canedit) {
		this.canedit = canedit;
	}

	public boolean isCanback() {
		return canback;
	}

	public void setCanback(boolean canback) {
		this.canback = canback;
	}

	public boolean isCanview() {
		return canview;
	}

	public void setCanview(boolean canview) {
		this.canview = canview;
	}
	
	
	
}