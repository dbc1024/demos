/*package com.ectrip.sale.service.OnlinePayment.action;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.cytterminal.DataTool;
import com.ectrip.cytterminal.DataTrans;
import com.ectrip.lakala.pojo.respose.OrderChargeResponse;
import com.ectrip.model.order.PaymentBill;
import com.ectrip.model.syspar.Sysparv5;
import com.ectrip.model.syspar.Sysparv5Id;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

*//**
 * Created by cxh on 2016/09/21.
 *//*
public class PaymentBackAction {

    private static final String contentType = "text/html;charset=utf-8";

    public void paymentCallBack(){
        IGenericDao genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        PrintWriter out = null;
        HttpServletRequest req = ServletActionContext.getRequest();
        ServletActionContext.getResponse().setContentType(contentType);
        //��ȡ�������Ȼ��ʹ��
        try {
            out = ServletActionContext.getResponse().getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try{
            String reqParam = req.getParameter("reqParam");
            DataTrans dataTrans = JSON.parseObject(reqParam, DataTrans.class);
            Sysparv5 sysparv5 = (Sysparv5) genericDao.get(Sysparv5.class, new Sysparv5Id("ZFQP", "0001"));
            if(DataTool.checkData(sysparv5.getPmvb(), dataTrans.getData(), dataTrans.getSigned())){
                OrderChargeResponse response = JSON.parseObject(dataTrans.getData(), OrderChargeResponse.class);
                if(response.getPaid() == 1){
                    String orderNo = response.getOrderNo();
                    PaymentBill bill = (PaymentBill) genericDao.get(PaymentBill.class,response.getOrderNo());
                    if(bill != null){
                        bill.setStatus("Y");
                        bill.setBackTime(response.getPaidTime());
                        bill.setDistributorOrderCode(response.getId());
                        genericDao.update(bill);
                        out.print("success");
                    }else{
                        out.println("fail");
                    }
                }else{
                    out.println("fail");
                }
            }else{
                out.print("fail");
            }
        }catch (Exception e){
            e.printStackTrace();
            out.print("fail");
        }finally {
            out.flush();
            out.close();
        }
    }
}
*/