package com.ectrip.ec.model.usernumjf;

/**
 * UsernumjflistId entity. @author MyEclipse Persistence Tools
 */

public class UsernumjflistId implements java.io.Serializable {

    // Fields

    private String orid;//������ˮ��
    private String usid;//�û�ID

    // Constructors

    /** default constructor */
    public UsernumjflistId() {
    }

    /** full constructor */
    public UsernumjflistId(String orid, String usid) {
	this.orid = orid;
	this.usid = usid;
    }

    // Property accessors

    public String getOrid() {
	return this.orid;
    }

    public void setOrid(String orid) {
	this.orid = orid;
    }

    public String getUsid() {
	return this.usid;
    }

    public void setUsid(String usid) {
	this.usid = usid;
    }

    public boolean equals(Object other) {
	if ((this == other))
	    return true;
	if ((other == null))
	    return false;
	if (!(other instanceof UsernumjflistId))
	    return false;
	UsernumjflistId castOther = (UsernumjflistId) other;

	return ((this.getOrid() == castOther.getOrid()) || (this.getOrid() != null && castOther.getOrid() != null && this.getOrid().equals(
		castOther.getOrid())))
		&& ((this.getUsid() == castOther.getUsid()) || (this.getUsid() != null && castOther.getUsid() != null && this.getUsid()
			.equals(castOther.getUsid())));
    }

    public int hashCode() {
	int result = 17;

	result = 37 * result + (getOrid() == null ? 0 : this.getOrid().hashCode());
	result = 37 * result + (getUsid() == null ? 0 : this.getUsid().hashCode());
	return result;
    }

}