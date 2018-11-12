package com.ectrip.ec.model.user;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Hscomment entity. @author MyEclipse Persistence Tools
 */

public class Hscomment implements java.io.Serializable {

	// Fields

	private Long seq;//����
	private String usid;//�����ˣ����δ��¼���ۣ���ip��ַ��
	private Long oeid;//���۶�����
	private String ptype;//�������ͣ�01�����̣�02��Ʒ��03���£�04������
	private String orid;//�����ţ������������Ϊ����ʱ��
	private String ctitle;//���۱���
	private String note;//��������
	private String createdate;//����ʱ��
	private Double remarknum;//�ۺ�������
	private Double fenjingnum;//�羰����
	private Double xinjianum;//�Լ۷���
	private String effect;//ӡ��
	private String ip;//IP��ַ
	private String status;//״̬��00δ���01���ͨ��02��� ��ͨ����
	private String shdate;//���ʱ��
	private Long shusid;//�����
	private Long inta;//�����ֶ�
	private Long intb;//�����ֶ�
	private String notea;//�����ֶ�
	private String noteb;//�����ֶ�
	
	//�����ݿ��ֶ�
	private String corpname;//�û�����
	private String szemployeename;//��� ������
	private String empid;//��� ���û���
	private String strptype;//������������
	private String strnote;
	private String strctitle;
	
	private Double sumnum;//�ۺ�������
	private Map effectList;
	
	
	

	// Constructors

	/** default constructor */
	public Hscomment() {
	}

	/** minimal constructor */
	public Hscomment(Long seq) {
		this.seq = seq;
	}

	
	public Hscomment(Long seq, String usid, Long oeid, String ptype,
			String orid, String ctitle, String note, String createdate,
			Double remarknum, Double fenjingnum, Double xinjianum,
			String effect, String ip, String status, String shdate,
			Long shusid, Long inta, Long intb, String notea, String noteb,
			String corpname, String szemployeename, String empid,
			String strptype, String strnote, String strctitle, Double sumnum) {
		super();
		this.seq = seq;
		this.usid = usid;
		this.oeid = oeid;
		this.ptype = ptype;
		this.orid = orid;
		this.ctitle = ctitle;
		this.note = note;
		this.createdate = createdate;
		this.remarknum = remarknum;
		this.fenjingnum = fenjingnum;
		this.xinjianum = xinjianum;
		this.effect = effect;
		this.ip = ip;
		this.status = status;
		this.shdate = shdate;
		this.shusid = shusid;
		this.inta = inta;
		this.intb = intb;
		this.notea = notea;
		this.noteb = noteb;
		this.corpname = corpname;
		this.szemployeename = szemployeename;
		this.empid = empid;
		this.strptype = strptype;
		this.strnote = strnote;
		this.strctitle = strctitle;
		this.sumnum = sumnum;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUsid() {
		return this.usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public Long getOeid() {
		return this.oeid;
	}

	public void setOeid(Long oeid) {
		this.oeid = oeid;
	}

	public String getPtype() {
		return this.ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getCtitle() {
		return this.ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	
	public Double getRemarknum() {
		return remarknum;
	}

	public void setRemarknum(Double remarknum) {
		this.remarknum = remarknum;
	}

	public Double getFenjingnum() {
		return fenjingnum;
	}

	public void setFenjingnum(Double fenjingnum) {
		this.fenjingnum = fenjingnum;
	}

	public Double getXinjianum() {
		return xinjianum;
	}

	public void setXinjianum(Double xinjianum) {
		this.xinjianum = xinjianum;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShdate() {
		return this.shdate;
	}

	public void setShdate(String shdate) {
		this.shdate = shdate;
	}

	public Long getShusid() {
		return this.shusid;
	}

	public void setShusid(Long shusid) {
		this.shusid = shusid;
	}

	public Long getInta() {
		return this.inta;
	}

	public void setInta(Long inta) {
		this.inta = inta;
	}

	public Long getIntb() {
		return this.intb;
	}

	public void setIntb(Long intb) {
		this.intb = intb;
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

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getSzemployeename() {
		return szemployeename;
	}

	public void setSzemployeename(String szemployeename) {
		this.szemployeename = szemployeename;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getStrptype() {
		return strptype;
	}

	public void setStrptype(String strptype) {
		this.strptype = strptype;
	}

	public String getStrnote() {
		return strnote;
	}

	public void setStrnote(String strnote) {
		this.strnote = strnote;
	}

	public String getStrctitle() {
		return strctitle;
	}

	public void setStrctitle(String strctitle) {
		this.strctitle = strctitle;
	}

	public Double getSumnum() {
		return sumnum;
	}

	public void setSumnum(Double sumnum) {
		this.sumnum = sumnum;
	}

	public Map getEffectList() {
		return effectList;
	}

	public void setEffectList(Map effectList) {
		this.effectList = effectList;
	}

}