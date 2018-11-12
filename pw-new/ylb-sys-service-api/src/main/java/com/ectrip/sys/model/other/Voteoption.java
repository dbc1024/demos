package com.ectrip.sys.model.other;


/**
 * ͶƱѡ��. @author MyEclipse Persistence Tools
 */
public class Voteoption implements java.io.Serializable {

	// Fields

	private Long ivoteoptionid; // ͶƱѡ��ID
	private Long ivoteid; // ͶƱ����ID
	private String content; // ѡ������� 
	private Long isumnum; // Ʊ��

	// Constructors

	/** default constructor */
	public Voteoption() {
	}

	/** minimal constructor */
	public Voteoption(Long ivoteoptionid, Long ivoteid,
			String content) {
		this.ivoteoptionid = ivoteoptionid;
		this.ivoteid = ivoteid;
		this.content = content;
	}

	/** full constructor */
	public Voteoption(Long ivoteoptionid, Long ivoteid,
			String content, Long isumnum) {
		this.ivoteoptionid = ivoteoptionid;
		this.ivoteid = ivoteid;
		this.content = content;
		this.isumnum = isumnum;
	}

	// Property accessors

	public Long getIvoteoptionid() {
		return this.ivoteoptionid;
	}

	public void setIvoteoptionid(Long ivoteoptionid) {
		this.ivoteoptionid = ivoteoptionid;
	}

	public Long getIvoteid() {
		return this.ivoteid;
	}

	public void setIvoteid(Long ivoteid) {
		this.ivoteid = ivoteid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getIsumnum() {
		return this.isumnum;
	}

	public void setIsumnum(Long isumnum) {
		this.isumnum = isumnum;
	}

}