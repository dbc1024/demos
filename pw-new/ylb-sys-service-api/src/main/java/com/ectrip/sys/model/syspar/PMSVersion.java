package com.ectrip.sys.model.syspar;

/**
 * Created by chenxinhao on 17/1/2.
 */
public class PMSVersion {

    private String versionId;//���汾��

    private String versionNote;//�汾˵��

    private String dbVer;//���ݿ�汾��

    private String saleVer;//��Ʊ����汾��

    private String checkVer;//��Ʊ����汾��

    private String b1Ver;//����

    private String b2Ver;//����

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getVersionNote() {
        return versionNote;
    }

    public void setVersionNote(String versionNote) {
        this.versionNote = versionNote;
    }

    public String getDbVer() {
        return dbVer;
    }

    public void setDbVer(String dbVer) {
        this.dbVer = dbVer;
    }

    public String getSaleVer() {
        return saleVer;
    }

    public void setSaleVer(String saleVer) {
        this.saleVer = saleVer;
    }

    public String getCheckVer() {
        return checkVer;
    }

    public void setCheckVer(String checkVer) {
        this.checkVer = checkVer;
    }

    public String getB1Ver() {
        return b1Ver;
    }

    public void setB1Ver(String b1Ver) {
        this.b1Ver = b1Ver;
    }

    public String getB2Ver() {
        return b2Ver;
    }

    public void setB2Ver(String b2Ver) {
        this.b2Ver = b2Ver;
    }
}
