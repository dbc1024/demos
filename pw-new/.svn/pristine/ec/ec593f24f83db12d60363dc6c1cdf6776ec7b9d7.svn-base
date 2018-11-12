package com.ectrip.ticket.sale.service.OnlinePayment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.MathUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.PaymentBill;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.sale.service.OnlinePayment.dao.idao.IOnlinePaymentDao;
import com.ectrip.ticket.sale.service.OnlinePayment.model.ChannelType;
import com.ectrip.ticket.sale.service.OnlinePayment.model.ChargeBackExtraConfig;
import com.ectrip.ticket.sale.service.OnlinePayment.model.OrderStatusType;
import com.ectrip.ticket.sale.service.OnlinePayment.model.PaymentOrderDto;
import com.ectrip.ticket.sale.service.OnlinePayment.model.bean.PaymentOrder;
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
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.OrderChargeQueryResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.OrderChargeResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.PayBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.QueryBillResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.SavePaymentOrderResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.model.response.SearchPaymentOrderResponse;
import com.ectrip.ticket.sale.service.OnlinePayment.service.iservice.IOnlinePaymentService;

/**
 * Created by cxh on 2016/07/20.
 */
@Service
public class OnlinePaymentService extends GenericService implements IOnlinePaymentService {

    public IGenericDao genericDao;
    private IOnlinePaymentDao onlinePaymentDao;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IOnlinePaymentDao getOnlinePaymentDao() {
        return onlinePaymentDao;
    }

    public void setOnlinePaymentDao(IOnlinePaymentDao onlinePaymentDao) {
        this.onlinePaymentDao = onlinePaymentDao;
    }

    @Override
    public AddBillResponse addBill(AddBillRequest request) {
        AddBillResponse res = new AddBillResponse();
        try {
            PaymentBill bill = new PaymentBill();
            bill.setPayCode(request.getOrid());
            bill.setUserid(Long.parseLong(request.getUserid()));
            bill.setWinid(Long.parseLong(request.getWinid()));
            bill.setMoney(MathUtil.amplify2long(Double.parseDouble(request.getAmount()), 2));
            bill.setOrderType(1L);
            bill.setStatus(OrderStatusType.NOPAY.getCode());
            bill.setCreateTime(Tools.getDayTimes());
            bill.setDtmakedate(Tools.getDayTimes());
            Esbticketwintab win = (Esbticketwintab) genericDao.get(Esbticketwintab.class, bill.getWinid());
            bill.setWinname(win.getSzticketwinname());
            bill.setWinCode(win.getPayCode());
            bill.setIscenicid(win.getIscenicid());
            Esfemployeetab emp = (Esfemployeetab) genericDao.get(Esfemployeetab.class, bill.getUserid());
            bill.setUsername(emp.getSzemployeename());
            genericDao.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        res.setCode("0000");
        res.setDescribe("保存订单成功");
        res.setOto(request.getOto());
        res.setOrid(request.getOrid());
        res.setAmount(request.getAmount());
        return res;
    }

    @Override
    public PayBillResponse payBill(PayBillRequest request,String newUrl) {
        PayBillResponse res = new PayBillResponse();

        //判断是否输入域名
        if(newUrl == null || newUrl.length()<1){
            newUrl = WebContant.GetKeyValue("DOMAIN");
        }

        try {
            Sysparv5 sysparv5 = (Sysparv5) genericDao.get(Sysparv5.class, new Sysparv5Id("ZFQP", "0001"));
            if (sysparv5 == null || StringUtils.isBlank(sysparv5.getPmva()) || StringUtils.isBlank(sysparv5.getPmvb())
                    || StringUtils.isBlank(sysparv5.getPmvc())) {
                res.setCode("2002");
                res.setDescribe("系统参数不全");
                return res;
            }
            PaymentBill bill = (PaymentBill) genericDao.get(PaymentBill.class, request.getOrid());
            if (bill != null) {
                if (StringUtils.isBlank(bill.getWinCode())) {
                    Esbticketwintab win = (Esbticketwintab) genericDao.get(Esbticketwintab.class, bill.getWinid());
                    if (StringUtils.isBlank(win.getPayCode())) {
                        res.setCode("2001");
                        res.setDescribe("未设置支付桥设备编号");
                        return res;
                    }
                    bill.setWinCode(win.getPayCode());
                }
                if (MathUtil.amplify2long(Double.parseDouble(request.getAmount()), 2) == bill.getMoney()) {
                    if (OrderStatusType.NOPAY.getCode().equalsIgnoreCase(bill.getStatus())) {
                        OrderChargeResponse response = BarPayService.orderPay(bill, request.getAuthCode(), request.getPaymentCode(), sysparv5,newUrl);
                        if ("1000".equals(response.getCode())) {
                            bill.setPaymentChannel(request.getPaymentCode());
                            if (response.getPaid() == 1) {
                                bill.setStatus(OrderStatusType.PAY.getCode());
                                bill.setBackTime(response.getPaidTime());
                                bill.setDistributorOrderCode(response.getId());
                            } else if (response.getPaid() == 4) {
                                bill.setStatus(OrderStatusType.WAIT.getCode());
                                bill.setDistributorOrderCode(response.getId());
                            } else if (response.getPaid() == 0) {//未付款
                                bill.setStatus(OrderStatusType.NOPAY.getCode());
                                bill.setDistributorOrderCode(response.getId());
                            } else {
                                bill.setStatus("S");
                                bill.setDistributorOrderCode(response.getId());
                            }
                        } else if ("1003".equalsIgnoreCase(response.getCode())) {
                            bill.setStatus(OrderStatusType.WAIT.getCode());
                            bill.setDistributorOrderCode(response.getId());
                        } else {
                            res.setCode("2001");
                            res.setDescribe(response.getDescribe());
                            return res;
                        }
                        genericDao.update(bill);
                        if (!StringUtils.isBlank(response.getExtra())) {
                            ChargeBackExtraConfig backExtraConfig = JSON.parseObject(response.getExtra(), ChargeBackExtraConfig.class);
                            if (backExtraConfig != null && !StringUtils.isBlank(backExtraConfig.getQr_code())) {
                                res.setQrCodeUrl(backExtraConfig.getQr_code());
                            }
                        }
                    }
                    res.setCode("0000");
                    res.setOrid(bill.getPayCode());
                    res.setAmount(MathUtil.divide(bill.getMoney().toString(), "100", 2, BigDecimal.ROUND_HALF_UP));
                    res.setStatus(bill.getStatus());
                } else {
                    res.setCode("2001");
                    res.setDescribe("支付金额错误");
                }
            } else {
                res.setCode("2005");
                res.setDescribe("订单不存在");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return res;
    }

    @Override
    public AddAndPayBillResponse addAndPayBill(AddAndPayBillRequest request,String newUrl) {
        AddAndPayBillResponse res = new AddAndPayBillResponse();

        //判断是否输入域名
        if(newUrl == null || newUrl.length()<1){
            newUrl = WebContant.GetKeyValue("DOMAIN");
        }

        try {
            Sysparv5 sysparv5 = (Sysparv5) genericDao.get(Sysparv5.class, new Sysparv5Id("ZFQP", "0001"));
            if (sysparv5 == null || StringUtils.isBlank(sysparv5.getPmva()) || StringUtils.isBlank(sysparv5.getPmvb())
                    || StringUtils.isBlank(sysparv5.getPmvc())) {
                res.setCode("2002");
                res.setDescribe("系统参数不全");
                return res;
            }

            PaymentBill bill = new PaymentBill();
            bill.setPayCode(request.getOrid());
            bill.setUserid(Long.parseLong(request.getUserid()));
            bill.setWinid(Long.parseLong(request.getWinid()));
            bill.setMoney(MathUtil.amplify2long(Double.parseDouble(request.getAmount()), 2));
            bill.setOrderType(1L);
            bill.setStatus(OrderStatusType.NOPAY.getCode());
            bill.setCreateTime(Tools.getDayTimes());
            bill.setDtmakedate(Tools.getDayTimes());
            Esbticketwintab win = (Esbticketwintab) genericDao.get(Esbticketwintab.class, bill.getWinid());
            bill.setWinname(win.getSzticketwinname());
            bill.setWinCode(win.getPayCode());
            bill.setIscenicid(win.getIscenicid());
            Esfemployeetab emp = (Esfemployeetab) genericDao.get(Esfemployeetab.class, bill.getUserid());
            bill.setUsername(emp.getSzemployeename());
            if (StringUtils.isBlank(bill.getWinCode())) {
                res.setCode("2001");
                res.setDescribe("未设置支付桥设备编号");
                return res;
            }
            OrderChargeResponse response = BarPayService.orderPay(bill, request.getAuthCode(), request.getPaymentCode(), sysparv5,newUrl);
            if ("1000".equals(response.getCode())) {
                bill.setPaymentChannel(request.getPaymentCode());
                if (response.getPaid() == 1) {
                    bill.setStatus(OrderStatusType.PAY.getCode());
                    bill.setBackTime(response.getPaidTime());
                    bill.setDistributorOrderCode(response.getId());
                } else if (response.getPaid() == 4) {
                    bill.setStatus(OrderStatusType.WAIT.getCode());
                    bill.setDistributorOrderCode(response.getId());
                } else if (response.getPaid() == 0) {//未付款
                    bill.setStatus(OrderStatusType.NOPAY.getCode());
                    bill.setDistributorOrderCode(response.getId());
                } else {
                    bill.setStatus("S");
                    bill.setDistributorOrderCode(response.getId());
                }
            } else if ("1003".equalsIgnoreCase(response.getCode())) {
                bill.setStatus(OrderStatusType.WAIT.getCode());
                bill.setDistributorOrderCode(response.getId());
            } else {
                res.setCode("2001");
                res.setDescribe(response.getDescribe());
                return res;
            }
            genericDao.save(bill);
            if (!StringUtils.isBlank(response.getExtra())) {
                ChargeBackExtraConfig backExtraConfig = JSON.parseObject(response.getExtra(), ChargeBackExtraConfig.class);
                if (backExtraConfig != null && !StringUtils.isBlank(backExtraConfig.getQr_code())) {
                    res.setQrCodeUrl(backExtraConfig.getQr_code());
                }
            }
            res.setCode("0000");
            res.setOrid(bill.getPayCode());
            res.setAmount(MathUtil.divide(bill.getMoney().toString(), "100", 2, BigDecimal.ROUND_HALF_UP));
            res.setStatus(bill.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }

    @Override
    public QueryBillResponse queryBill(QueryBillRequest request) {
        QueryBillResponse res = new QueryBillResponse();
        try {
            PaymentBill bill = (PaymentBill) genericDao.get(PaymentBill.class, request.getOrid());
            if (bill != null) {
                if (!OrderStatusType.PAY.getCode().equals(bill.getStatus()) && !StringUtils.isBlank(bill.getDistributorOrderCode())) {
                    Sysparv5 sysparv5 = (Sysparv5) genericDao.get(Sysparv5.class, new Sysparv5Id("ZFQP", "0001"));
                    if (sysparv5 == null || StringUtils.isBlank(sysparv5.getPmva()) || StringUtils.isBlank(sysparv5.getPmvb())
                            || StringUtils.isBlank(sysparv5.getPmvc())) {
                        res.setCode("2002");
                        res.setDescribe("系统参数不全");
                        return res;
                    }
                    OrderChargeQueryResponse response = BarPayService.orderQuery(bill.getDistributorOrderCode(), sysparv5);
                    if ("1000".equals(response.getCode())) {
                        if (response.getPaid() == 1) {//已支付
                            bill.setStatus(OrderStatusType.PAY.getCode());
                            bill.setBackTime(response.getPaidTime());
                            bill.setDistributorOrderCode(response.getId());
                        } else if (response.getPaid() == 4) {//处理中
                            bill.setStatus(OrderStatusType.WAIT.getCode());
                            bill.setDistributorOrderCode(response.getId());
                        } else if (response.getPaid() == 0) {//未付款
                            bill.setStatus(OrderStatusType.NOPAY.getCode());
                            bill.setDistributorOrderCode(response.getId());
                        } else {
                            bill.setStatus("S");
                            bill.setDistributorOrderCode(response.getId());
                        }
                    } else if ("1003".equalsIgnoreCase(response.getCode())) {
                        bill.setStatus(OrderStatusType.WAIT.getCode());
                        bill.setDistributorOrderCode(response.getId());
                    } else {
                        res.setCode("2001");
                        res.setDescribe(response.getDescribe());
                        return res;
                    }
                    genericDao.update(bill);
                }
                res.setCode("0000");
                res.setOrid(bill.getPayCode());
                res.setAmount(MathUtil.divide(bill.getMoney(), 100, 2, BigDecimal.ROUND_HALF_UP));
                res.setStatus(bill.getStatus());
            } else {
                res.setCode("2005");
                res.setDescribe("订单不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * 查询环球雅图支付
     * @param request
     * @return
     */
    @Override
    public QueryBillResponse queryHqytBill(QueryBillRequest request) {
        QueryBillResponse res = new QueryBillResponse();
        try {
            List list=genericDao.find("from LOrder where id.orid = '"+request.getOrid()+"'");
            if (list != null && list.size()>0) {
                LOrder lOrder = (LOrder)list.get(0);
                if(lOrder.getDdzt().equals("02"))
                {
                    res.setCode("0000");
                    res.setOrid(request.getOrid());
                    res.setAmount(MathUtil.divide(lOrder.getZfmont(), 100, 2, BigDecimal.ROUND_HALF_UP));
                    res.setStatus((lOrder.getDdzt()==null?"N":(lOrder.getDdzt().equalsIgnoreCase("02")?"Y":"N")));
                }
            } else {
                res.setCode("2005");
                res.setDescribe("订单不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }

    public AddUpacpBillResponse AddUpacpBill(AddUpacpBillRequest request) {
        AddUpacpBillResponse res = new AddUpacpBillResponse();
        try {
            String date = Tools.getDayTimes();
            PaymentBill bill = new PaymentBill();
            bill.setPayCode(request.getOrid());
            bill.setDistributorOrderCode(request.getPayid());
            bill.setUserid(Long.parseLong(request.getUserid()));
            bill.setWinid(Long.parseLong(request.getWinid()));
            bill.setMoney(MathUtil.amplify2long(Double.parseDouble(request.getAmount()), 2));
            bill.setOrderType(1L);
            bill.setPaymentChannel(request.getPaymentCode().toUpperCase());
            bill.setStatus(OrderStatusType.PAY.getCode());
            bill.setCreateTime(date);
            bill.setBackTime(date);
            bill.setDtmakedate(date);
            Esbticketwintab win = (Esbticketwintab) genericDao.get(Esbticketwintab.class, bill.getWinid());
            bill.setWinname(win.getSzticketwinname());
            bill.setWinCode("upacp");
            bill.setIscenicid(win.getIscenicid());
            Esfemployeetab emp = (Esfemployeetab) genericDao.get(Esfemployeetab.class, bill.getUserid());
            bill.setUsername(emp.getSzemployeename());
            bill.setCardNo(request.getCardNo());
            if (!StringUtils.isBlank(request.getMessage())) {
                bill.setMessage(Tools.getBlobByStr(request.getMessage()));
            }
            genericDao.save(bill);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        res.setCode("0000");
        res.setDescribe("保存银联支付流水成功");
        res.setOto(request.getOto());
        res.setOrid(request.getOrid());
        res.setAmount(request.getAmount());
        return res;
    }

    /**
     * 保存支付临时订单
     *
     * @param request
     * @return
     */
    @Override
    public SavePaymentOrderResponse savePaymentOrder(SavePaymentOrderRequest request) {
        SavePaymentOrderResponse res = new SavePaymentOrderResponse();
        try {
            if (StringUtils.isBlank(request.getMessage())) {
                throw new RuntimeException("请求报文数据不能为空");
            }
            PaymentOrder paymentOrder = new PaymentOrder();
            paymentOrder.setOrid(request.getOrid());
            paymentOrder.setMessage(Tools.getBlobByStr(request.getMessage()));
            paymentOrder.setMont(Double.parseDouble(request.getMont()));
            paymentOrder.setDdzt("00");
            paymentOrder.setIemployeeId(Long.parseLong(request.getIemployeeid()));
            paymentOrder.setWinId(Long.parseLong(request.getIticketwinid()));
            paymentOrder.setDtmakedate(Tools.getDayTimes());
            genericDao.save(paymentOrder);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        res.setCode("0000");
        res.setDescribe("保存临时订单成功");
        res.setOrid(request.getOrid());
        res.setZfmont(request.getMont());
        return res;
    }

    @Override
    public SearchPaymentOrderResponse searchPaymentOrder(SearchPaymentOrderRequest request) {
        SearchPaymentOrderResponse res = new SearchPaymentOrderResponse();
        try {
            if (StringUtils.isBlank(request.getRzti())) {
                request.setRzti(Tools.getDays());
            }
            if (StringUtils.isBlank(request.getLdti())) {
                request.setLdti(Tools.getDays());
            }
            PaymentBill paymentBill = onlinePaymentDao.findPaymentBill(request.getOrid());
            String payCode = "";
            if (paymentBill != null) {
                payCode = paymentBill.getPayCode();
            } else {
                payCode = request.getOrid();
            }
            List<PaymentOrder> orders = onlinePaymentDao.findPaymentOrders(payCode, request.getRzti(), request.getLdti(), request.getEmpid());
            List<PaymentOrderDto> paymentOrders = new ArrayList<PaymentOrderDto>();
            if (orders != null && !orders.isEmpty()) {
                for (PaymentOrder paymentOrder : orders) {
                    PaymentBill pay = (PaymentBill) genericDao.get(PaymentBill.class, paymentOrder.getOrid());
                    PaymentOrderDto paymentOrderDto = new PaymentOrderDto();
                    paymentOrderDto.setOrid(paymentOrder.getOrid());
                    paymentOrderDto.setMont(paymentOrder.getMont());
                    paymentOrderDto.setDdzt(paymentOrder.getDdzt());
                    paymentOrderDto.setMessage(Tools.getStrByBlob(paymentOrder.getMessage()));
                    paymentOrderDto.setDtmakedate(paymentOrder.getDtmakedate());
                    if (pay != null) {
                        paymentOrderDto.setPayOrid(pay.getDistributorOrderCode());
                        paymentOrderDto.setPmsOrid(pay.getOrid());
                        if (!StringUtils.isBlank(pay.getPaymentChannel())) {
                            paymentOrderDto.setPaymentType(ChannelType.typeOf(pay.getPaymentChannel()).getName());
                        }
                        if (!StringUtils.isBlank(pay.getStatus())) {
                            paymentOrderDto.setPayStatus(OrderStatusType.typeOf(pay.getStatus()).getName());
                        }
                    }
                    paymentOrders.add(paymentOrderDto);
                    res.setPaymentOrders(paymentOrders);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        res.setCode("0000");
        res.setDescribe("查询支付订单报文成功");
        return res;
    }
}
