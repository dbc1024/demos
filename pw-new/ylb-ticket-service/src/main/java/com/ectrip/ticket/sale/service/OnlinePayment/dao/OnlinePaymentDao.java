package com.ectrip.ticket.sale.service.OnlinePayment.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.sale.service.OnlinePayment.dao.idao.IOnlinePaymentDao;
import com.ectrip.ticket.sale.service.OnlinePayment.model.bean.PaymentOrder;

/**
 * Created by chenxinhao on 16/12/27.
 */
public class OnlinePaymentDao extends GenericDao implements IOnlinePaymentDao{

    /**
     * 查询支付流水信息
     * @param distributorOrderCode 支付系统订单号
     * @return
     */
    public PaymentBill findPaymentBill(String distributorOrderCode) {
        StringBuffer hsql = new StringBuffer("from PaymentBill where distributorOrderCode = '"+distributorOrderCode+"' ");
        List list = find(hsql.toString());
        if(list != null && !list.isEmpty()){
            return (PaymentBill) list.get(0);
        }
        return null;
    }

    public List<PaymentOrder> findPaymentOrders(String payCode,String rzti,String ldti,String empid){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from PaymentOrder where substr(dtmakedate,1,10) >= '"+rzti+"' and substr(dtmakedate,1,10) <= '"+ldti+"' ");
        if(!StringUtils.isBlank(payCode)){
            hsql.append(" and orid = '"+payCode+"' ");
        }
        if(!StringUtils.isBlank(empid)){
            List<Esfemployeetab> emps = find("from Esfemployeetab where empid = '"+empid+"' ");
            if(emps != null && !emps.isEmpty()){
                Esfemployeetab emp = emps.get(0);
                hsql.append(" and iemployeeId = "+emp.getIemployeeid());
            }
        }
        hsql.append(" order by dtmakedate desc ");
        return find(hsql.toString());
    }
}