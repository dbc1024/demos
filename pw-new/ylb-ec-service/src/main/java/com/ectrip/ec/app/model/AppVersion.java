package com.ectrip.ec.app.model;

import java.util.Date;

/**
 * 客户端APP版本号管理
 */
public class AppVersion {

    private Long id;//版本id
    private String appVersion;//版本号
    private String upgradeNotes;//版本升级说明
    private Long isForceUpgrade;//是否强制升级 0不强制， 1强制升级
    private Long clientType;//客户端类型 0 安卓， 1 IOS
    private Date createDate;//创建时间
    //非数据库字段
    private String downloadUrl;//apk下载地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getUpgradeNotes() {
        return upgradeNotes;
    }

    public void setUpgradeNotes(String upgradeNotes) {
        this.upgradeNotes = upgradeNotes;
    }

    public Long getIsForceUpgrade() {
        return isForceUpgrade;
    }

    public void setIsForceUpgrade(Long isForceUpgrade) {
        this.isForceUpgrade = isForceUpgrade;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Long getClientType() {
        return clientType;
    }

    public void setClientType(Long clientType) {
        this.clientType = clientType;
    }
}
