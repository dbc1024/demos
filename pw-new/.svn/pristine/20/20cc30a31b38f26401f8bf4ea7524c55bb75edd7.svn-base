package com.ectrip.ec.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Administrator
 * @create 2017/11/24
 **/
@Entity
@Table(name="CustomRealName")
public class CustomRealName implements java.io.Serializable {
	
	@Id
	private String userId;//用户id
	@Column
    private String realName;//真名
	@Column
    private String idNumber;//加密后的证件号
	@Transient
    private String decryptedIdNumber;//解密后的证件号
    @Column
    public  Long flowId;//UserbankFlow.flowid
    @Column
    private Date createDate;//创建时间

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDecryptedIdNumber() {
        return decryptedIdNumber;
    }

    public void setDecryptedIdNumber(String decryptedIdNumber) {
        this.decryptedIdNumber = decryptedIdNumber;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }
}
