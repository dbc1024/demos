package com.ectrip.ticket.sale.service.OnlinePayment.service.iservice;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.AddAndPayBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.AddBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.AddUpacpBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.PayBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.QueryBillRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.SavePaymentOrderRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.request.SearchPaymentOrderRequest;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.AddAndPayBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.AddBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.AddUpacpBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.PayBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.QueryBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.SavePaymentOrderResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.SearchPaymentOrderResponse;

/**
 * Created by cxh on 2016/07/20.
 */
public interface IOnlinePaymentService extends IGenericService{

    /**
     * 保存支付临时订单
     * @param request
     * @return
     */
    public SavePaymentOrderResponse savePaymentOrder(SavePaymentOrderRequest request);

    public SearchPaymentOrderResponse searchPaymentOrder(SearchPaymentOrderRequest request);

    public AddBillResponse addBill(AddBillRequest request);

    public PayBillResponse payBill(PayBillRequest request,String url);

    public AddAndPayBillResponse addAndPayBill(AddAndPayBillRequest request,String url);

    public QueryBillResponse queryBill(QueryBillRequest request);

    public AddUpacpBillResponse AddUpacpBill(AddUpacpBillRequest request);

    public QueryBillResponse queryHqytBill(QueryBillRequest request);
}
