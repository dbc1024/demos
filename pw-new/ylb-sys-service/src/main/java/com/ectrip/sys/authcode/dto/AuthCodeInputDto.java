package com.ectrip.sys.authcode.dto;

import javax.persistence.Entity;

/**
 * Created by chenxinhao on 2017/2/23.
 */
@Entity
public class AuthCodeInputDto {

    private Long id;//����

    private String codeType;//��Ȩ������

    private String status;//״̬

    private String searchName;//ģ����ѯ����

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
