package com.ectrip.sys.model.syspar;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Sysparv5 entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Sysparv5 implements java.io.Serializable {

	// Fields

	@Id
	private Sysparv5Id id;//主键
	@Column
	private String spmcd;//上级参数
	@Column
	private String systp;//参数级别
	@Column
	private String pmva;//参数A
	@Column
	private String pmvb;//参数B
	@Column
	private String pmvc;//参数C
	@Column
	private String pmvd;//参数D
	@Column
	private String pmve;//参数E
	@Column
	private String pmvf;//参数F
	@Column
	private Long isa;//整形备注A
	@Column
	private Long isb;//整形备注B
	@Column
	private Long isc;//整形备注C
	@Column
	private Long isd;//整形备注D
	@Column
	private Long ise;//整形备注E
	@Column
	private Long isf;//整形备注F
	@Column
	private Long isvalue;//是否启用
	@Column
	private String note;//备注

	@Transient
	private String pmky;//村识
	@Transient
	private String pmcd;//参数码
	@Transient
	private boolean ischecked;//是否选中
	@Transient
	private List list;
	
	
	
	
	// Constructors

	/** default constructor */
	public Sysparv5() {
	}

	/** minimal constructor */
	public Sysparv5(Sysparv5Id id, String systp, String pmva) {
		this.id = id;
		this.systp = systp;
		this.pmva = pmva;
	}

	/** full constructor */
	public Sysparv5(Sysparv5Id id, String spmcd, String systp, String pmva, String pmvb, String pmvc, String pmvd, String pmve, String pmvf, Long isa,
			Long isb, Long isc, Long isd, Long ise, Long isf, Long isvalue, String note) {
		this.id = id;
		this.spmcd = spmcd;
		this.systp = systp;
		this.pmva = pmva;
		this.pmvb = pmvb;
		this.pmvc = pmvc;
		this.pmvd = pmvd;
		this.pmve = pmve;
		this.pmvf = pmvf;
		this.isa = isa;
		this.isb = isb;
		this.isc = isc;
		this.isd = isd;
		this.ise = ise;
		this.isf = isf;
		this.isvalue = isvalue;
		this.note = note;
	}

	// Property accessors

	public Sysparv5Id getId() {
		return this.id;
	}

	public void setId(Sysparv5Id id) {
		this.id = id;
	}

	public String getSpmcd() {
		return this.spmcd;
	}

	public void setSpmcd(String spmcd) {
		this.spmcd = spmcd;
	}

	public String getSystp() {
		return this.systp;
	}

	public void setSystp(String systp) {
		this.systp = systp;
	}

	public String getPmva() {
		return this.pmva;
	}

	public void setPmva(String pmva) {
		this.pmva = pmva;
	}

	public String getPmvb() {
		return this.pmvb;
	}

	public void setPmvb(String pmvb) {
		this.pmvb = pmvb;
	}

	public String getPmvc() {
		return this.pmvc;
	}

	public void setPmvc(String pmvc) {
		this.pmvc = pmvc;
	}

	public String getPmvd() {
		return this.pmvd;
	}

	public void setPmvd(String pmvd) {
		this.pmvd = pmvd;
	}

	public String getPmve() {
		return this.pmve;
	}

	public void setPmve(String pmve) {
		this.pmve = pmve;
	}

	public String getPmvf() {
		return this.pmvf;
	}

	public void setPmvf(String pmvf) {
		this.pmvf = pmvf;
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

	public Long getIsvalue() {
		return this.isvalue;
	}

	public void setIsvalue(Long isvalue) {
		this.isvalue = isvalue;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPmky() {
		return pmky;
	}

	public void setPmky(String pmky) {
		this.pmky = pmky;
	}

	public String getPmcd() {
		return pmcd;
	}

	public void setPmcd(String pmcd) {
		this.pmcd = pmcd;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

}