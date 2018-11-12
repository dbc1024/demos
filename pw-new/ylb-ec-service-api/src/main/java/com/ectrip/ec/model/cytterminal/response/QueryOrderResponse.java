package com.ectrip.ec.model.cytterminal.response;
import java.util.List;

import com.ectrip.ec.model.cytterminal.OrderInfo;
import com.ectrip.ec.model.cytterminal.Response;

/**
 * @author yongkang.liao 2015/10/9.
 */
public class QueryOrderResponse extends Response{
    public List<OrderInfo> orderInfos;

    public QueryOrderResponse() {
        super();
    }

    public QueryOrderResponse(List<OrderInfo> orderInfos) {
        super();
        this.orderInfos = orderInfos;
    }

    public List<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }
}
