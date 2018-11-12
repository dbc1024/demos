package com.ectrip.ticket.sale.service.commonSale.model.request;

import com.ectrip.ticket.sale.service.card.core.Request;

/**
 * Created by chenxinhao on 2017/5/31.
 */
public class FindCheckListRequest extends Request {

    private String ticketNo;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }
}
