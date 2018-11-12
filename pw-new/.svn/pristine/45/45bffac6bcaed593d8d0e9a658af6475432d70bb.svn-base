package com.ectrip.ticket.model.permitenter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Numjifenset entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="Numjifenset")
public class Numjifenset implements java.io.Serializable {

	// Fields

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long nid;            	//规则编号
	@Column(name="iscenicid",length=20,nullable=true)
	private Long iscenicid;		//服务商ID
	@Column(name="minnum",length=20)
	private Long minnum;		//总数最小数量
	@Column(name="maxnum",length=20)
	private Long maxnum;		//总数最大数量
	@Column(name="jflb",length=20)
	private Long jflb;		//规则类别  消费规则:-1, 优惠规则1
	@Column(name="jnum",length=20)
	private Long jnum;		//规则基数
	@Column(name="point",length=20)
	private Long point;		//积分
	
	//非数据库字段
	private String szscenicname;  //服务商名称

	// Constructors

	public String getSzscenicname() {
		return szscenicname;
	}

	public void setSzscenicname(String szscenicname) {
		this.szscenicname = szscenicname;
	}

	/** default constructor */
	public Numjifenset() {
	}

	/** minimal constructor */
	public Numjifenset(Long nid, Long iscenicid, Long jflb, Long jnum,
			Long point) {
		this.nid = nid;
		this.iscenicid = iscenicid;
		this.jflb = jflb;
		this.jnum = jnum;
		this.point = point;
	}

	/** full constructor */
	public Numjifenset(Long nid, Long iscenicid, Long minnum, Long maxnum,
			Long jflb, Long jnum, Long point) {
		this.nid = nid;
		this.iscenicid = iscenicid;
		this.minnum = minnum;
		this.maxnum = maxnum;
		this.jflb = jflb;
		this.jnum = jnum;
		this.point = point;
	}

	// Property accessors

	public Long getNid() {
		return this.nid;
	}

	public void setNid(Long nid) {
		this.nid = nid;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getMinnum() {
		return this.minnum;
	}

	public void setMinnum(Long minnum) {
		this.minnum = minnum;
	}

	public Long getMaxnum() {
		return this.maxnum;
	}

	public void setMaxnum(Long maxnum) {
		this.maxnum = maxnum;
	}

	public Long getJflb() {
		return this.jflb;
	}

	public void setJflb(Long jflb) {
		this.jflb = jflb;
	}

	public Long getJnum() {
		return this.jnum;
	}

	public void setJnum(Long jnum) {
		this.jnum = jnum;
	}

	public Long getPoint() {
		return this.point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

}