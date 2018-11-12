package com.ectrip.ec.model.user;

import java.sql.Timestamp;

/**
 * @author Administrator
 * @create 2017/10/19
 **/
public class Userbank implements java.io.Serializable {
    private long ubid;
    private String cardtype;//������
    private String idname;//֤������
    private String idnumber;//֤����
    private String termofvalidity;//��Ч��
    private String cvvr2;
    private String cardnumber;//����
    private long cardphone;//�绰����
    private String userid;//�û�id
    private Timestamp adddate;//���ʱ��
    private String idascription;
    private String cardascription;
    private String issuerBankCode;//�����д���
    private String bankCode;//���б��
    private String bankName;//��������
    private String bindAgrno;//��Э���

    public String getBindAgrno() {
        return bindAgrno;
    }

    public void setBindAgrno(String bindAgrno) {
        this.bindAgrno = bindAgrno;
    }

    public String getIssuerBankCode() {
        return issuerBankCode;
    }

    public void setIssuerBankCode(String issuerBankCode) {
        this.issuerBankCode = issuerBankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getUbid() {
        return ubid;
    }

    public void setUbid(long ubid) {
        this.ubid = ubid;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getTermofvalidity() {
        return termofvalidity;
    }

    public void setTermofvalidity(String termofvalidity) {
        this.termofvalidity = termofvalidity;
    }

    public String getCvvr2() {
        return cvvr2;
    }

    public void setCvvr2(String cvvr2) {
        this.cvvr2 = cvvr2;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public long getCardphone() {
        return cardphone;
    }

    public void setCardphone(long cardphone) {
        this.cardphone = cardphone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timestamp getAdddate() {
        return adddate;
    }

    public void setAdddate(Timestamp adddate) {
        this.adddate = adddate;
    }

    public String getIdascription() {
        return idascription;
    }

    public void setIdascription(String idascription) {
        this.idascription = idascription;
    }

    public String getCardascription() {
        return cardascription;
    }

    public void setCardascription(String cardascription) {
        this.cardascription = cardascription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Userbank that = (Userbank) o;

        if (ubid != that.ubid) return false;
        if (cardtype != that.cardtype) return false;
        if (cardphone != that.cardphone) return false;
        if (idname != null ? !idname.equals(that.idname) : that.idname != null) return false;
        if (idnumber != null ? !idnumber.equals(that.idnumber) : that.idnumber != null) return false;
        if (termofvalidity != null ? !termofvalidity.equals(that.termofvalidity) : that.termofvalidity != null)
            return false;
        if (cvvr2 != null ? !cvvr2.equals(that.cvvr2) : that.cvvr2 != null) return false;
        if (cardnumber != null ? !cardnumber.equals(that.cardnumber) : that.cardnumber != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (adddate != null ? !adddate.equals(that.adddate) : that.adddate != null) return false;
        if (idascription != null ? !idascription.equals(that.idascription) : that.idascription != null) return false;
        if (cardascription != null ? !cardascription.equals(that.cardascription) : that.cardascription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ubid ^ (ubid >>> 32));
        result = 31 * result + (idname != null ? idname.hashCode() : 0);
        result = 31 * result + (idnumber != null ? idnumber.hashCode() : 0);
        result = 31 * result + (termofvalidity != null ? termofvalidity.hashCode() : 0);
        result = 31 * result + (cvvr2 != null ? cvvr2.hashCode() : 0);
        result = 31 * result + (cardnumber != null ? cardnumber.hashCode() : 0);
        result = 31 * result + (int) (cardphone ^ (cardphone >>> 32));
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (adddate != null ? adddate.hashCode() : 0);
        result = 31 * result + (idascription != null ? idascription.hashCode() : 0);
        result = 31 * result + (cardascription != null ? cardascription.hashCode() : 0);
        return result;
    }
}
