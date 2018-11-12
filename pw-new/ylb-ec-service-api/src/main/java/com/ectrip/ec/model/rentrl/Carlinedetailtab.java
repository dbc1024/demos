package com.ectrip.ec.model.rentrl;

import java.sql.Blob;
import java.util.List;

/**
 * Carlinedetailtab entity. @author MyEclipse Persistence Tools
 */

public class Carlinedetailtab implements java.io.Serializable {

	// Fields

	private Long seq;//����
	private String pmcd; //�⳵·�߲�Ʒ���
	private Long rentaltype;//����
	private String usetime;//��ʱ�ο�
	private Blob linedescription;//·�߽���
	private Long mileage;//�ο����
	private String region;//·������
	private String dtmakedate;//����ʱ��
	/*�����ֶ�*/
	private Long note1;
	private Long note2;
	private Long note3;
	private Long note4;
	private Long note5;
	private String chartext1;//��������
	private String chartext2;//ͼƬUPID
	private String chartext3;
	private String chartext4;
	private String chartext5;
	
	//�����ݿ��ֶ�
	private String blobstr;
	private String[] upids;//ͼƬ
	private List list;//ͼ���б�

	// Constructors

	public String getBlobstr() {
		return blobstr;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setBlobstr(String blobstr) {
		this.blobstr = blobstr;
	}

	/** default constructor */
	public Carlinedetailtab() {
	}

	/** minimal constructor */
	public Carlinedetailtab(Long seq, String usetime,
			Blob linedescription, Long mileage, String region,
			Long note1, Long note2, Long note3,
			Long note4, Long note5, String chartext1,
			String chartext2, String chartext3, String chartext4,
			String chartext5) {
		this.seq = seq;
		this.usetime = usetime;
		this.linedescription = linedescription;
		this.mileage = mileage;
		this.region = region;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
		this.chartext1 = chartext1;
		this.chartext2 = chartext2;
		this.chartext3 = chartext3;
		this.chartext4 = chartext4;
		this.chartext5 = chartext5;
	}

	/** full constructor */
	public Carlinedetailtab(Long seq, String pmcd,
			Long rentaltype, String usetime, Blob linedescription,
			Long mileage, String region, String dtmakedate,
			Long note1, Long note2, Long note3,
			Long note4, Long note5, String chartext1,
			String chartext2, String chartext3, String chartext4,
			String chartext5) {
		this.seq = seq;
		this.pmcd = pmcd;
		this.rentaltype = rentaltype;
		this.usetime = usetime;
		this.linedescription = linedescription;
		this.mileage = mileage;
		this.region = region;
		this.dtmakedate = dtmakedate;
		this.note1 = note1;
		this.note2 = note2;
		this.note3 = note3;
		this.note4 = note4;
		this.note5 = note5;
		this.chartext1 = chartext1;
		this.chartext2 = chartext2;
		this.chartext3 = chartext3;
		this.chartext4 = chartext4;
		this.chartext5 = chartext5;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getPmcd() {
		return this.pmcd;
	}

	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}

	public Long getRentaltype() {
		return this.rentaltype;
	}

	public void setRentaltype(Long rentaltype) {
		this.rentaltype = rentaltype;
	}

	public String getUsetime() {
		return this.usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public Blob getLinedescription() {
		return this.linedescription;
	}

	public void setLinedescription(Blob linedescription) {
		this.linedescription = linedescription;
	}

	public Long getMileage() {
		return this.mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDtmakedate() {
		return this.dtmakedate;
	}

	public void setDtmakedate(String dtmakedate) {
		this.dtmakedate = dtmakedate;
	}

	public Long getNote1() {
		return this.note1;
	}

	public void setNote1(Long note1) {
		this.note1 = note1;
	}

	public Long getNote2() {
		return this.note2;
	}

	public void setNote2(Long note2) {
		this.note2 = note2;
	}

	public Long getNote3() {
		return this.note3;
	}

	public void setNote3(Long note3) {
		this.note3 = note3;
	}

	public Long getNote4() {
		return this.note4;
	}

	public void setNote4(Long note4) {
		this.note4 = note4;
	}

	public Long getNote5() {
		return this.note5;
	}

	public void setNote5(Long note5) {
		this.note5 = note5;
	}

	public String getChartext1() {
		return this.chartext1;
	}

	public void setChartext1(String chartext1) {
		this.chartext1 = chartext1;
	}

	public String getChartext2() {
		return this.chartext2;
	}

	public void setChartext2(String chartext2) {
		this.chartext2 = chartext2;
	}

	public String getChartext3() {
		return this.chartext3;
	}

	public void setChartext3(String chartext3) {
		this.chartext3 = chartext3;
	}

	public String getChartext4() {
		return this.chartext4;
	}

	public void setChartext4(String chartext4) {
		this.chartext4 = chartext4;
	}

	public String getChartext5() {
		return this.chartext5;
	}

	public void setChartext5(String chartext5) {
		this.chartext5 = chartext5;
	}

	public String[] getUpids() {
		return upids;
	}

	public void setUpids(String[] upids) {
		this.upids = upids;
	}

}