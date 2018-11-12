package com.ectrip.ticket.sale.service.commonSale.model.pojo;

/**
 * Created by chenxinhao on 2017/5/31.
 */
public class CheckListPojo {

    private String gardenName;//԰������

    private String machineName;//��Ʊ�豸����

    private String checkDate;//��Ʊʱ��

    private Long numb;//��԰����

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Long getNumb() {
        return numb;
    }

    public void setNumb(Long numb) {
        this.numb = numb;
    }
}
