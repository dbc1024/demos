package com.ectrip.ticket.model.afcset;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Esbaccessstatustab entity. @author MyEclipse Persistence Tools
 */

@Entity
public class Esbaccessstatustab implements java.io.Serializable {

	// Fields

	@Id
	private Long seq;
	private Long iscenicid;    	  //景区ID
	private Long istatuswinid;    //点ID  园门ID   售票点ID
	private Long istatusgardid;    //机器ID 闸机ID  窗口
	private String statusbtype;    //设备类别 01 检票终端   02 售票终端    SBLX
	private String typestatus;    // 00 总状态  01 条码阅读器  02  读卡器  03  指纹仪 04 身份证读卡器  与sysparv5中  SBZT 添加编码对应
	private Long byisuse;    	  //状态     0   不正常  1  正常
	private String statusdtime;    //	检测时间
	private Long intext1;
	private Long intext2;
	private Long intext3;
	private Long intext4;
	private Long intext5;
	private String charext1;
	private String charext2;
	private String charext3;
	private String charext4;
	private String charext5;

	//非数据库字段
	@Transient
	private String scenicidname;  //景区名称
	@Transient
	private String statuswinname; //点名称
	@Transient
	private String statusgardname; //机器名称
	@Transient
	private String btype;
	@Transient
	private String status;

	// Constructors

	/** default constructor */
	public Esbaccessstatustab() {
	}

	/** minimal constructor */
	public Esbaccessstatustab(Long seq, Long iscenicid,
							  Long istatuswinid, Long istatusgardid,
							  String statusbtype, String typestatus, Long byisuse,
							  String statusdtime) {
		this.seq = seq;
		this.iscenicid = iscenicid;
		this.istatuswinid = istatuswinid;
		this.istatusgardid = istatusgardid;
		this.statusbtype = statusbtype;
		this.typestatus = typestatus;
		this.byisuse = byisuse;
		this.statusdtime = statusdtime;
	}

	/** full constructor */
	public Esbaccessstatustab(Long seq, Long iscenicid,
							  Long istatuswinid, Long istatusgardid,
							  String statusbtype, String typestatus, Long byisuse,
							  String statusdtime, Long intext1, Long intext2,
							  Long intext3, Long intext4, Long intext5,
							  String charext1, String charext2, String charext3, String charext4,
							  String charext5) {
		this.seq = seq;
		this.iscenicid = iscenicid;
		this.istatuswinid = istatuswinid;
		this.istatusgardid = istatusgardid;
		this.statusbtype = statusbtype;
		this.typestatus = typestatus;
		this.byisuse = byisuse;
		this.statusdtime = statusdtime;
		this.intext1 = intext1;
		this.intext2 = intext2;
		this.intext3 = intext3;
		this.intext4 = intext4;
		this.intext5 = intext5;
		this.charext1 = charext1;
		this.charext2 = charext2;
		this.charext3 = charext3;
		this.charext4 = charext4;
		this.charext5 = charext5;
	}

	// Property accessors

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getIscenicid() {
		return this.iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public Long getIstatuswinid() {
		return this.istatuswinid;
	}

	public void setIstatuswinid(Long istatuswinid) {
		this.istatuswinid = istatuswinid;
	}

	public Long getIstatusgardid() {
		return this.istatusgardid;
	}

	public void setIstatusgardid(Long istatusgardid) {
		this.istatusgardid = istatusgardid;
	}

	public String getStatusbtype() {
		return this.statusbtype;
	}

	public void setStatusbtype(String statusbtype) {
		this.statusbtype = statusbtype;
	}

	public String getTypestatus() {
		return this.typestatus;
	}

	public void setTypestatus(String typestatus) {
		this.typestatus = typestatus;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getStatusdtime() {
		return this.statusdtime;
	}

	public void setStatusdtime(String statusdtime) {
		this.statusdtime = statusdtime;
	}

	public Long getIntext1() {
		return this.intext1;
	}

	public void setIntext1(Long intext1) {
		this.intext1 = intext1;
	}

	public Long getIntext2() {
		return this.intext2;
	}

	public void setIntext2(Long intext2) {
		this.intext2 = intext2;
	}

	public Long getIntext3() {
		return this.intext3;
	}

	public void setIntext3(Long intext3) {
		this.intext3 = intext3;
	}

	public Long getIntext4() {
		return this.intext4;
	}

	public void setIntext4(Long intext4) {
		this.intext4 = intext4;
	}

	public Long getIntext5() {
		return this.intext5;
	}

	public void setIntext5(Long intext5) {
		this.intext5 = intext5;
	}

	public String getCharext1() {
		return this.charext1;
	}

	public void setCharext1(String charext1) {
		this.charext1 = charext1;
	}

	public String getCharext2() {
		return this.charext2;
	}

	public void setCharext2(String charext2) {
		this.charext2 = charext2;
	}

	public String getCharext3() {
		return this.charext3;
	}

	public void setCharext3(String charext3) {
		this.charext3 = charext3;
	}

	public String getCharext4() {
		return this.charext4;
	}

	public void setCharext4(String charext4) {
		this.charext4 = charext4;
	}

	public String getCharext5() {
		return this.charext5;
	}

	public void setCharext5(String charext5) {
		this.charext5 = charext5;
	}

	public String getScenicidname() {
		return scenicidname;
	}

	public void setScenicidname(String scenicidname) {
		this.scenicidname = scenicidname;
	}

	public String getStatuswinname() {
		return statuswinname;
	}

	public void setStatuswinname(String statuswinname) {
		this.statuswinname = statuswinname;
	}

	public String getStatusgardname() {
		return statusgardname;
	}

	public void setStatusgardname(String statusgardname) {
		this.statusgardname = statusgardname;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}