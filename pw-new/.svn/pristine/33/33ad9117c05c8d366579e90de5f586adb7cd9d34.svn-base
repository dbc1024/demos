package com.ectrip.ticket.model.afcset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 准进设备表 Esbaccessequiptab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Esbaccessequiptab implements java.io.Serializable {

	// Fields

	@Id
	private EsbaccessequiptabId id;// ID
	@Column
	private Long iequiptypeid;// 所属设备类型
	@Column
	private String szaccessequipcode;// 代码
	@Column
	private String szaccessequipname;// 名称
	@Column
	private String szfactorycode;// 厂家编号
	@Column
	private String szipaddress;// 本地IP
	@Column
	private String szserverip;// 服务器IP
	@Column
	private String sznetmask;// 子网掩码
	@Column
	private String szgateway;// 网关
	@Column
	private String szdnsip;// DNS
	@Column
	private String bycoretype;// 机芯类型
	@Column
	private String byctrldir;// 控制方向
	@Column
	private String byworkmode;// 工作模式
	@Column
	private String bynetworkmode;// 网络方式
	@Column
	private Long byisuse;// 启用状态
	@Column
	private String szmemo;// 备注
	@Column
	private Long iversion;// 版本
	@Column
	private Long isa;
	@Column
	private Long isb;
	@Column
	private String notea;
	@Column
	private String noteb;

	// 非数据库字段
	@Transient
	private String striscenicid; // 服务商名称
	@Transient
	private String strigardengateid;// 园门名称
	@Transient
	private Long iscenicid;// 服务商编号
	@Transient
	private Long igardengateid;// 园门编号
	@Transient
	private Long iaccessequipid;// 准进设备编号
	@Transient
	private String striequiptypeid;// 设备类型名称

	// Constructors

	/** default constructor */
	public Esbaccessequiptab() {
	}

	/** minimal constructor */
	public Esbaccessequiptab(EsbaccessequiptabId id, Long iequiptypeid,
							 String szaccessequipcode, String szaccessequipname,
							 String szgateway, Long byisuse) {
		this.id = id;
		this.iequiptypeid = iequiptypeid;
		this.szaccessequipcode = szaccessequipcode;
		this.szaccessequipname = szaccessequipname;
		this.szgateway = szgateway;
		this.byisuse = byisuse;
	}

	/** full constructor */
	public Esbaccessequiptab(EsbaccessequiptabId id, Long iequiptypeid,
							 String szaccessequipcode, String szaccessequipname,
							 String szfactorycode, String szipaddress, String szserverip,
							 String sznetmask, String szgateway, String szdnsip,
							 String bycoretype, String byctrldir, String byworkmode,
							 String bynetworkmode, Long byisuse, String szmemo, Long iversion,Long isa,Long isb,String notea,String noteb) {
		this.id = id;
		this.iequiptypeid = iequiptypeid;
		this.szaccessequipcode = szaccessequipcode;
		this.szaccessequipname = szaccessequipname;
		this.szfactorycode = szfactorycode;
		this.szipaddress = szipaddress;
		this.szserverip = szserverip;
		this.sznetmask = sznetmask;
		this.szgateway = szgateway;
		this.szdnsip = szdnsip;
		this.bycoretype = bycoretype;
		this.byctrldir = byctrldir;
		this.byworkmode = byworkmode;
		this.bynetworkmode = bynetworkmode;
		this.byisuse = byisuse;
		this.szmemo = szmemo;
		this.iversion = iversion;
		this.isa = isa;
		this.isb = isb;
		this.notea = notea;
		this.noteb = noteb;
	}

	// Property accessors

	public Long getIgardengateid() {
		return igardengateid;
	}

	public Long getIaccessequipid() {
		return iaccessequipid;
	}

	public void setIaccessequipid(Long iaccessequipid) {
		this.iaccessequipid = iaccessequipid;
	}

	public void setIgardengateid(Long igardengateid) {
		this.igardengateid = igardengateid;
	}

	public EsbaccessequiptabId getId() {
		return this.id;
	}

	public void setId(EsbaccessequiptabId id) {
		this.id = id;
	}

	public Long getIequiptypeid() {
		return this.iequiptypeid;
	}

	public void setIequiptypeid(String iequiptypeid) {
		if (null != iequiptypeid && !"".equals(iequiptypeid)) {
			this.iequiptypeid = new Long(iequiptypeid);
		}
	}

	public String getStriscenicid() {
		return striscenicid;
	}

	public void setStriscenicid(String striscenicid) {
		this.striscenicid = striscenicid;
	}

	public String getStrigardengateid() {
		return strigardengateid;
	}

	public String getStriequiptypeid() {
		return striequiptypeid;
	}

	public void setStriequiptypeid(String striequiptypeid) {
		this.striequiptypeid = striequiptypeid;
	}

	public void setStrigardengateid(String strigardengateid) {
		this.strigardengateid = strigardengateid;
	}

	public String getBycoretype() {
		return bycoretype;
	}

	public void setBycoretype(String bycoretype) {
		this.bycoretype = bycoretype;
	}

	public String getByctrldir() {
		return byctrldir;
	}

	public void setByctrldir(String byctrldir) {
		this.byctrldir = byctrldir;
	}

	public String getByworkmode() {
		return byworkmode;
	}

	public void setByworkmode(String byworkmode) {
		this.byworkmode = byworkmode;
	}

	public String getBynetworkmode() {
		return bynetworkmode;
	}

	public void setBynetworkmode(String bynetworkmode) {
		this.bynetworkmode = bynetworkmode;
	}

	public Long getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(Long iscenicid) {
		this.iscenicid = iscenicid;
	}

	public void setIequiptypeid(Long iequiptypeid) {
		this.iequiptypeid = iequiptypeid;
	}

	public String getSzaccessequipcode() {
		return this.szaccessequipcode;
	}

	public void setSzaccessequipcode(String szaccessequipcode) {
		this.szaccessequipcode = szaccessequipcode;
	}

	public String getSzaccessequipname() {
		return this.szaccessequipname;
	}

	public void setSzaccessequipname(String szaccessequipname) {
		this.szaccessequipname = szaccessequipname;
	}

	public String getSzfactorycode() {
		return this.szfactorycode;
	}

	public void setSzfactorycode(String szfactorycode) {
		this.szfactorycode = szfactorycode;
	}

	public String getSzipaddress() {
		return this.szipaddress;
	}

	public void setSzipaddress(String szipaddress) {
		this.szipaddress = szipaddress;
	}

	public String getSzserverip() {
		return this.szserverip;
	}

	public void setSzserverip(String szserverip) {
		this.szserverip = szserverip;
	}

	public String getSznetmask() {
		return this.sznetmask;
	}

	public void setSznetmask(String sznetmask) {
		this.sznetmask = sznetmask;
	}

	public String getSzgateway() {
		return this.szgateway;
	}

	public void setSzgateway(String szgateway) {
		this.szgateway = szgateway;
	}

	public String getSzdnsip() {
		return this.szdnsip;
	}

	public void setSzdnsip(String szdnsip) {
		this.szdnsip = szdnsip;
	}

	public Long getByisuse() {
		return this.byisuse;
	}

	public void setByisuse(Long byisuse) {
		this.byisuse = byisuse;
	}

	public String getSzmemo() {
		return this.szmemo;
	}

	public void setSzmemo(String szmemo) {
		this.szmemo = szmemo;
	}

	public Long getIversion() {
		return this.iversion;
	}

	public void setIversion(Long iversion) {
		this.iversion = iversion;
	}

	public Long getIsa() {
		return isa;
	}

	public void setIsa(Long isa) {
		this.isa = isa;
	}

	public Long getIsb() {
		return isb;
	}

	public void setIsb(Long isb) {
		this.isb = isb;
	}

	public String getNotea() {
		return notea;
	}

	public void setNotea(String notea) {
		this.notea = notea;
	}

	public String getNoteb() {
		return noteb;
	}

	public void setNoteb(String noteb) {
		this.noteb = noteb;
	}

}