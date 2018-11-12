package com.ectrip.ticket.webservice.sale;

import javax.jws.WebService;

@WebService
public interface IOnlinePaymentWebService {

    public Object savePaymentOrder(String data);

    public Object searchPaymentOrder(String data);

    public Object addBill(String data);

    public Object payBill(String data,String newUrl);

    public Object addAndPayBill(String data,String newUrl);

    /**
     * 扫码
     * @param data
     * @return
     */
    public Object hqytPay(String data);

    public Object queryBill(String data);

    /**
     * 查询环球支付订单状态
     * @param data
     * @return
     */
    public Object queryHqytBill(String data);

    /**
     * 保存银联支付流水接口
     * @param data
     * @return
     */
    public Object AddUpacpBill(String data);

}
