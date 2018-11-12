package com.ectrip.ec.model.order;

import java.util.List;

/**
 * MOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class HismOrder implements java.io.Serializable {

	// Fields

	private String orid;// ��������ˮ��
	private String ortp;// �������� 01 Ԥ�� 02 ��Ʊ 03 ��������
	private String usid; // �οͱ��id
	private String orda; // �µ�����
	private String orti; // �µ�ʱ��
	private Long isjl; // ��������
	private Double mont;// �������
	private Double yhamnt;// �Żݽ��
	private Double zfmont;// ֧���Ľ��
	private String ddzt;// ����״̬ 00 δ֧�� 01��֧�� 02 ��֧��
	private Long isallcp;// �Ƿ�ȫ����Ʊ
	private String zffs;// ֧����ʽ
	private String bank;// ֧������
	private String zfusid;// ����֧����
	private String payorid;// ֧��������
	private String bankdata;// ֧������
	private String banktime;// ֧��ʱ��
	private String note;// ��ע
	private String srid;// ��Ӧԭ������
	private String tpdate;//�˶���ʱ��ʾ �˿�ʱ��,��������ʱ��ʾ����ʱ�� ���˶����˶�ʱҪ��orda+orti ��ʾtpmont��
	private String tpfs;//�޸ķ�ʽ 00 ԭʼԤ��  01 ��Ʊǰ�޸� 02 ��Ʊ���޸�  
	private double tpsx;//�˶�������
	private double tpfl;//�˶���������
	private double tpmont;//�˶��ܽ��  ��ȥtpsx ����ʵ���˶����
	private String stdt;//�״���������
	private Long isa;// ���������ֶ�
	private Long isb;
	private Long isc;//��ƱԱ���
	private Long isd;
	private Long ise;
	private Long isf;//ԭʼ�����г�Ʊǰ�޸ı�־λ  �Ƿ��г�Ʊǰ�޸�
	private Long isg;
	private Long ish;
	private Long isi;//���Ļ�������
	private Long isj;//ԭʼ�����г�Ʊ���޸ı�־λ �Ƿ��г�Ʊ���޸�
	private String notea;// �����ַ����ֶ�
	private String notej;
	private String notei;//
	private String noteh;
	private String noteg;
	private String notef;//�˶���� 00  �����Լ��˶� 01 ��Ʊǰ��ƱԱ�˶� 03 ��Ʊ���ƱԱ�˶�  04 ȷ��ͣ��ϵͳ�˶� 07 ��̨ǿ���˶�
	private String notee;
	private String noted;
	private String notec;
	private String noteb;

	// not database Fields
	private String strusid; // �û���(��˾)
	private String striscenicid; // ��������
	private String strddzt; // ����״̬
	private String strzffs; // ֧����ʽ
	private String strbank; // ����

	private String pmcd;//����״̬ ���
	private String pmva;//����״̬ ����
	
	private List<YOrder> yorders;
	private List<TOrder> torders;

	// Constructors

	/** default constructor */
	public HismOrder() {
	}

	/** minimal constructor */
	public HismOrder(String orid, String ortp, String usid, String orda,
			String orti, Long isjl, Double mont, Double zfmont, Long isallcp) {
		this.orid = orid;
		this.ortp = ortp;
		this.usid = usid;
		this.orda = orda;
		this.orti = orti;
		this.isjl = isjl;
		this.mont = mont;
		this.zfmont = zfmont;
		this.isallcp = isallcp;
	}

	
	// Property accessors
	

	public String getOrid() {
		return this.orid;
	}

	public HismOrder(String orid, String ortp, String usid, String orda,
			String orti, Long isjl, Double mont, Double yhamnt, Double zfmont,
			String ddzt, Long isallcp, String zffs, String bank, String zfusid,
			String payorid, String bankdata, String banktime, String note,
			String srid, String tpdate, String tpfs, double tpsx, double tpfl,
			double tpmont, String stdt, Long isa, Long isb, Long isc, Long isd,
			Long ise, Long isf, Long isg, Long ish, Long isi, Long isj,
			String notea, String notej, String notei, String noteh,
			String noteg, String notef, String notee, String noted,
			String notec, String noteb) {
		super();
		this.orid = orid;
		this.ortp = ortp;
		this.usid = usid;
		this.orda = orda;
		this.orti = orti;
		this.isjl = isjl;
		this.mont = mont;
		this.yhamnt = yhamnt;
		this.zfmont = zfmont;
		this.ddzt = ddzt;
		this.isallcp = isallcp;
		this.zffs = zffs;
		this.bank = bank;
		this.zfusid = zfusid;
		this.payorid = payorid;
		this.bankdata = bankdata;
		this.banktime = banktime;
		this.note = note;
		this.srid = srid;
		this.tpdate = tpdate;
		this.tpfs = tpfs;
		this.tpsx = tpsx;
		this.tpfl = tpfl;
		this.tpmont = tpmont;
		this.stdt = stdt;
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

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getOrtp() {
		return this.ortp;
	}

	public void setOrtp(String ortp) {
		this.ortp = ortp;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public String getOrda() {
		return this.orda;
	}

	public void setOrda(String orda) {
		this.orda = orda;
	}

	public String getOrti() {
		return this.orti;
	}

	public void setOrti(String orti) {
		this.orti = orti;
	}

	public Long getIsjl() {
		return this.isjl;
	}

	public void setIsjl(Long isjl) {
		this.isjl = isjl;
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

	public String getDdzt() {
		return this.ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}

	public Long getIsallcp() {
		return this.isallcp;
	}

	public void setIsallcp(Long isallcp) {
		this.isallcp = isallcp;
	}

	public String getZffs() {
		return this.zffs;
	}

	public void setZffs(String zffs) {
		this.zffs = zffs;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getZfusid() {
		return this.zfusid;
	}

	public void setZfusid(String zfusid) {
		this.zfusid = zfusid;
	}

	public String getPayorid() {
		return this.payorid;
	}

	public void setPayorid(String payorid) {
		this.payorid = payorid;
	}

	public String getBankdata() {
		return this.bankdata;
	}

	public void setBankdata(String bankdata) {
		this.bankdata = bankdata;
	}

	public String getBanktime() {
		return this.banktime;
	}

	public void setBanktime(String banktime) {
		this.banktime = banktime;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSrid() {
		return this.srid;
	}

	public void setSrid(String srid) {
		this.srid = srid;
	}

	public String getTpdate() {
		return tpdate;
	}

	public void setTpdate(String tpdate) {
		this.tpdate = tpdate;
	}

	public String getTpfs() {
		return tpfs;
	}

	public void setTpfs(String tpfs) {
		this.tpfs = tpfs;
	}

	public java.lang.Double getTpsx() {
		return tpsx;
	}

	public void setTpsx(Double tpsx) {
       if(tpsx==null){		
    	   this.tpsx=0.00;
       }else{
		
		this.tpsx = tpsx;
       }
	}

	public Double getTpfl() {
		return tpfl;
	}

	public void setTpfl(Double tpfl) {
		if(tpfl==null){
			this.tpfl=0.00;
		}else{
		   this.tpfl = tpfl;
		} 
	}

	public Double getTpmont() {
		return tpmont;
	}

	public void setTpmont(Double tpmont) {
		if(tpmont==null){
			this.tpmont=0.00;
		}else{
		   this.tpmont = tpmont;
		}
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

	public String getStrusid() {
		return strusid;
	}

	public void setStrusid(String strusid) {
		this.strusid = strusid;
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public List<YOrder> getYorders() {
		return yorders;
	}

	public void setYorders(List<YOrder> yorders) {
		this.yorders = yorders;
	}

	public List<TOrder> getTorders() {
		return torders;
	}

	public void setTorders(List<TOrder> torders) {
		this.torders = torders;
	}

	public String getStrddzt() {
		return strddzt;
	}

	public void setStrddzt(String strddzt) {
		this.strddzt = strddzt;
	}

	public String getStrzffs() {
		return strzffs;
	}

	public void setStrzffs(String strzffs) {
		this.strzffs = strzffs;
	}

	public String getStrbank() {
		return strbank;
	}

	public void setStrbank(String strbank) {
		this.strbank = strbank;
	}

	public String getStdt() {
		return stdt;
	}

	public void setStdt(String stdt) {
		this.stdt = stdt;
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