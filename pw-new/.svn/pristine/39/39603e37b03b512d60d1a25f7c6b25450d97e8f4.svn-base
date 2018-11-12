package com.ectrip.ticket.model.provider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Esbticketwintab entity.
 *
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table
public class Esbticketwintab implements java.io.Serializable {

    // Fields
	@Id
    private Long iticketwinid;//窗口ID
    private Long iticketstationid;//站点ID
    private Long iscenicid;//服务商ID
    private String szticketwincode;//窗口编码
    private String szticketwinname;//窗口名称
    private String szipaddress;//MAC地址
    private String dtsellstart;//开始售票时间
    private String dtsellend;//停止售票时间
    private String bywintype;//业务类型
    private Long byisuse;//状态
    private String szmemo;//备注
    private Long iversion;
    private String ipaddress;//ip地址
    private String icid;
    private Long isty;//是否通用
    private String payCode;//支付桥设备号

    public Long getIsty() {
        return isty;
    }

    public void setIsty(Long isty) {
        this.isty = isty;
    }

    //非数据库字段
    @Transient
    private String szstationname;
    @Transient
    private String szscenicname;

    // Constructors

    /**
     * default constructor
     */
    public Esbticketwintab() {
    }

    /**
     * full constructor
     */
    public Esbticketwintab(Long iticketwinid, Long iticketstationid,
                           Long iscenicid, String szticketwincode, String szticketwinname,
                           String szipaddress, String dtsellstart, String dtsellend,
                           String bywintype, Long byisuse, String szmemo, Long iversion, String ipaddress) {
        this.iticketwinid = iticketwinid;
        this.iticketstationid = iticketstationid;
        this.iscenicid = iscenicid;
        this.szticketwincode = szticketwincode;
        this.szticketwinname = szticketwinname;
        this.szipaddress = szipaddress;
        this.dtsellstart = dtsellstart;
        this.dtsellend = dtsellend;
        this.bywintype = bywintype;
        this.byisuse = byisuse;
        this.szmemo = szmemo;
        this.iversion = iversion;
        this.ipaddress = ipaddress;
    }

    // Property accessors

    public Long getIticketwinid() {
        return this.iticketwinid;
    }

    public void setIticketwinid(Long iticketwinid) {
        this.iticketwinid = iticketwinid;
    }

    public Long getIticketstationid() {
        return this.iticketstationid;
    }

    public void setIticketstationid(Long iticketstationid) {
        this.iticketstationid = iticketstationid;
    }

    public Long getIscenicid() {
        return this.iscenicid;
    }

    public void setIscenicid(Long iscenicid) {
        this.iscenicid = iscenicid;
    }

    public String getSzticketwincode() {
        return this.szticketwincode;
    }

    public void setSzticketwincode(String szticketwincode) {
        this.szticketwincode = szticketwincode;
    }

    public String getSzticketwinname() {
        return this.szticketwinname;
    }

    public void setSzticketwinname(String szticketwinname) {
        this.szticketwinname = szticketwinname;
    }

    public String getSzipaddress() {
        return this.szipaddress;
    }

    public void setSzipaddress(String szipaddress) {
        this.szipaddress = szipaddress;
    }

    public String getDtsellstart() {
        return this.dtsellstart;
    }

    public void setDtsellstart(String dtsellstart) {
        this.dtsellstart = dtsellstart;
    }

    public String getDtsellend() {
        return this.dtsellend;
    }

    public void setDtsellend(String dtsellend) {
        this.dtsellend = dtsellend;
    }

    public String getBywintype() {
        return this.bywintype;
    }

    public void setBywintype(String bywintype) {
        this.bywintype = bywintype;
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

    public String getSzstationname() {
        return szstationname;
    }

    public void setSzstationname(String szstationname) {
        this.szstationname = szstationname;
    }

    public String getSzscenicname() {
        return szscenicname;
    }

    public void setSzscenicname(String szscenicname) {
        this.szscenicname = szscenicname;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getIcid() {
        return icid;
    }

    public void setIcid(String icid) {
        this.icid = icid;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }
}