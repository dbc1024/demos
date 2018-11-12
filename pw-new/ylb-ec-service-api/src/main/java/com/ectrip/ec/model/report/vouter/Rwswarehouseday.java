package com.ectrip.ec.model.report.vouter;

/**�����ͳ���ձ���
 * Rwswarehouseday entity. @author MyEclipse Persistence Tools
 */

public class Rwswarehouseday implements java.io.Serializable {

	// Fields

	private Long statid;  				  //���ͳ�Ʊ�ID
	private Long statdate;				  //����ʱ��
	private String szwarehousename;		  //�ֿ�����
	private Long iwarehouseid;			  //�ֿ�ID
	private String sztickettypename;	  //Ʊ����
	private Long itickettypeid;			  //ƱID
	private Long recentremain;			  //���ڽ�������
	private Long numout;				  //��������
	private Long numin;					  //�������
	private Long endremain;				  //��ĩ�������
	private Long int1;
	private Long int2;
	private String note1;
	private String note2;

	// Constructors

	/** default constructor */
	public Rwswarehouseday() {
	}

	/** minimal constructor */
	public Rwswarehouseday(Long statid) {
		this.statid = statid;
	}

	/** full constructor */
	public Rwswarehouseday(Long statid, Long statdate,
			String szwarehousename, Long iwarehouseid,
			String sztickettypename, Long itickettypeid,
			Long recentremain, Long numout, Long numin, Long endremain,
			Long int1, Long int2, String note1, String note2) {
		this.statid = statid;
		this.statdate = statdate;
		this.szwarehousename = szwarehousename;
		this.iwarehouseid = iwarehouseid;
		this.sztickettypename = sztickettypename;
		this.itickettypeid = itickettypeid;
		this.recentremain = recentremain;
		this.numout = numout;
		this.numin = numin;
		this.endremain = endremain;
		this.int1 = int1;
		this.int2 = int2;
		this.note1 = note1;
		this.note2 = note2;
	}

	// Property accessors

	public Long getStatid() {
		return this.statid;
	}

	public void setStatid(Long statid) {
		this.statid = statid;
	}

	public Long getStatdate() {
		return this.statdate;
	}

	public void setStatdate(Long statdate) {
		this.statdate = statdate;
	}

	public String getSzwarehousename() {
		return this.szwarehousename;
	}

	public void setSzwarehousename(String szwarehousename) {
		this.szwarehousename = szwarehousename;
	}

	public Long getIwarehouseid() {
		return this.iwarehouseid;
	}

	public void setIwarehouseid(Long iwarehouseid) {
		this.iwarehouseid = iwarehouseid;
	}

	public String getSztickettypename() {
		return this.sztickettypename;
	}

	public void setSztickettypename(String sztickettypename) {
		this.sztickettypename = sztickettypename;
	}

	public Long getItickettypeid() {
		return this.itickettypeid;
	}

	public void setItickettypeid(Long itickettypeid) {
		this.itickettypeid = itickettypeid;
	}

	public Long getRecentremain() {
		return this.recentremain;
	}

	public void setRecentremain(Long recentremain) {
		this.recentremain = recentremain;
	}

	public Long getNumout() {
		return this.numout;
	}

	public void setNumout(Long numout) {
		this.numout = numout;
	}

	public Long getNumin() {
		return this.numin;
	}

	public void setNumin(Long numin) {
		this.numin = numin;
	}

	public Long getEndremain() {
		return this.endremain;
	}

	public void setEndremain(Long endremain) {
		this.endremain = endremain;
	}

	public Long getInt1() {
		return this.int1;
	}

	public void setInt1(Long int1) {
		this.int1 = int1;
	}

	public Long getInt2() {
		return this.int2;
	}

	public void setInt2(Long int2) {
		this.int2 = int2;
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

}