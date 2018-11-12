package com.ectrip.sys.model.employee;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class Esppostsbtndutytab implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long bipostsdutyid;
	private Long bipostsid;
	private Long bidutyid;
	public Long getBipostsdutyid() {
		return bipostsdutyid;
	}
	public void setBipostsdutyid(Long bipostsdutyid) {
		this.bipostsdutyid = bipostsdutyid;
	}
	public Long getBipostsid() {
		return bipostsid;
	}
	public void setBipostsid(Long bipostsid) {
		this.bipostsid = bipostsid;
	}
	public Long getBidutyid() {
		return bidutyid;
	}
	public void setBidutyid(Long bidutyid) {
		this.bidutyid = bidutyid;
	}
}
