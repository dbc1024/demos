package com.ectrip.sys.model.authcode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import java.sql.Blob;

/**
 * Created by chenxinhao on 2017/2/24.
 */
@Entity
@Table(name = "AUTHCODE")
public class AuthCode {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "assigned")
    private Long id;//主键

    @Column
    private String codeType;//授权码类型
    @Column
    private String authCode;//授权码
    @Column
    private String orginId;//原始ID
    @Column
    private String dtmakedate;//操作时间
    @Column
    private Blob jsonMessage;//额外信息
    @Column
    private String baiduStatus;//百度状态
    @Column
    private String regionCode;//PMS授权码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getOrginId() {
        return orginId;
    }

    public void setOrginId(String orginId) {
        this.orginId = orginId;
    }

    public String getDtmakedate() {
        return dtmakedate;
    }

    public void setDtmakedate(String dtmakedate) {
        this.dtmakedate = dtmakedate;
    }

    public Blob getJsonMessage() {
        return jsonMessage;
    }

    public void setJsonMessage(Blob jsonMessage) {
        this.jsonMessage = jsonMessage;
    }

    public String getBaiduStatus() {
        return baiduStatus;
    }

    public void setBaiduStatus(String baiduStatus) {
        this.baiduStatus = baiduStatus;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
