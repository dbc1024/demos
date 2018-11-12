package com.ectrip.ticket.sale.service.commonSale.model.response;

import java.util.List;

import com.ectrip.ticket.sale.service.card.core.Response;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.FingerPrintPojo;

/**
 * Created by chenxinhao on 2017/7/7.
 */
public class CheckFingerPrintResponse extends Response{

    private Integer totalCount;

    private List<FingerPrintPojo> fingerPrints;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<FingerPrintPojo> getFingerPrints() {
        return fingerPrints;
    }

    public void setFingerPrints(List<FingerPrintPojo> fingerPrints) {
        this.fingerPrints = fingerPrints;
    }
}
