package com.ectrip.sys.model.other;

/**
 * Created by cxh on 2016/05/23.
 */
public class AnnualCardOption {

    private String name;//����
    private String mobile;//�ֻ���
    private String credentials;//֤������
    private int valid = 0;//��ѯ��Ч���� 0��ȫ�� 1����Ч -1����Ч
    private boolean history = false;//�Ƿ��ѯ����ʷ����

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public boolean isHistory() {
        return history;
    }

    public void setHistory(boolean history) {
        this.history = history;
    }
}
