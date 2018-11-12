package com.ectrip.ticket.model.permitenter;

/**
 * NumjifensetlistId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NumjifensetlistId implements java.io.Serializable {

	// Fields

	private Long nid;   //规则编号
	private Long seq;	//编号

	// Constructors

	/** default constructor */
	public NumjifensetlistId() {
	}

	/** full constructor */
	public NumjifensetlistId(Long nid, Long seq) {
		this.nid = nid;
		this.seq = seq;
	}

	// Property accessors

	public Long getNid() {
		return this.nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NumjifensetlistId))
			return false;
		NumjifensetlistId castOther = (NumjifensetlistId) other;

		return ((this.getNid() == castOther.getNid()) || (this.getNid() != null
				&& castOther.getNid() != null && this.getNid().equals(
				castOther.getNid())))
				&& ((this.getSeq() == castOther.getSeq()) || (this.getSeq() != null
						&& castOther.getSeq() != null && this.getSeq().equals(
						castOther.getSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getNid() == null ? 0 : this.getNid().hashCode());
		result = 37 * result
				+ (getSeq() == null ? 0 : this.getSeq().hashCode());
		return result;
	}

}