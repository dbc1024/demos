package com.ectrip.sys.model.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Companyscenic entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Companyscenic implements java.io.Serializable {

	// Fields
	@Id
	private CompanyscenicId id;
	private String cytonly;//1畅游通专用
//	private Esbscenicareatab esbscenicareatab;
//	private Galcompanyinfotab galcompanyinfotab;
	
	@Transient
	private Long icompanyinfoid;
	@Transient
	private Long iscenicid;
	
	// Constructors
	@Transient
	private boolean check;//是否选中

	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	/** default constructor */
	public Companyscenic() {
	}
	public Companyscenic(CompanyscenicId id) {
		this.id = id;
	}
	/** full constructor */
	/*public Companyscenic(CompanyscenicId id, Esbscenicareatab esbscenicareatab,
			Galcompanyinfotab galcompanyinfotab) {
		this.id = id;
		this.esbscenicareatab = esbscenicareatab;
		this.galcompanyinfotab = galcompanyinfotab;
	}*/

	// Property accessors

	public CompanyscenicId getId() {
		return this.id;
	}

	public void setId(CompanyscenicId id) {
		this.id = id;
	}
	public Long getIcompanyinfoid() {
		return icompanyinfoid;
	}
	public void setIcompanyinfoid(Long icompanyinfoid) {
		this.icompanyinfoid = icompanyinfoid;
	}
	public Long getIscenicid() {
		return iscenicid;
	}
	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}
	public String getCytonly() {
		return cytonly;
	}
	public void setCytonly(String cytonly) {
		this.cytonly = cytonly;
	}

	/*public Esbscenicareatab getEsbscenicareatab() {
		return this.esbscenicareatab;
	}

	public void setEsbscenicareatab(Esbscenicareatab esbscenicareatab) {
		this.esbscenicareatab = esbscenicareatab;
	}

	public Galcompanyinfotab getGalcompanyinfotab() {
		return this.galcompanyinfotab;
	}

	public void setGalcompanyinfotab(Galcompanyinfotab galcompanyinfotab) {
		this.galcompanyinfotab = galcompanyinfotab;
	}*/

}