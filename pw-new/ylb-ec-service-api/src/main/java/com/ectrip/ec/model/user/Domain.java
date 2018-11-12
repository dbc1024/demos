package com.ectrip.ec.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 域名管理表
 * @author xielei
 *
 */

@Entity
@Table
public class Domain implements java.io.Serializable {

	@Id
	private Long seq;
	/**
	 * 域名
	 */
	private String domainUrl;
	/**
	 * 企业名称
	 */
	private String groupId;
	/**
	 * 文字商标
	 */
	private String logoMark; 
	/**
	 * 图片商标
	 */
	private String  logoPic;
	/**
	 * 类型      1。取得对应的企业id    2.  取得对应的图片。3.取得 对应的视频
	 */
	private String type;

	// Constructors

	/** default constructor */
	public Domain() {
	}

	/** full constructor */
	public Domain(Long seq,String domainUrl, String groupId, String logoMark,String logoPic,String type) {
		this.seq = seq;
		this.domainUrl = domainUrl;
		this.groupId = groupId;
		this.logoMark = logoMark;
		this.logoPic = logoPic;
		this.type = type;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDomainUrl() {
		return domainUrl;
	}

	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getLogoMark() {
		return logoMark;
	}

	public void setLogoMark(String logoMark) {
		this.logoMark = logoMark;
	}

	public String getLogoPic() {
		return logoPic;
	}

	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}

}