package com.ectrip.ticket.sale.service.OnlinePayment.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.ticket.sale.service.OnlinePayment.model.bean.PaymentOrder;

/**
 * Created by chenxinhao on 16/12/27.
 */
public interface IOnlinePaymentDao extends IGenericDao{

    /**
     * 查询支付流水信息
     * @param distributorOrderCode 支付系统订单号
     * @return
     */
    PaymentBill findPaymentBill(String distributorOrderCode);

    /**
     * 查询支付临时订单
     * @param payCode PMS系统支付号
     * @param rzti  开始日期
     * @param ldti  截止日期
     * @param empid 售票员登录名
     * @return
     */
    List<PaymentOrder> findPaymentOrders(String payCode, String rzti, String ldti, String empid);
}
