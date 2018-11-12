package com.ectrip.hqyt.model.response;


import com.ectrip.hqyt.model.enums.InvoiceRefundStatus;
import com.ectrip.hqyt.model.enums.InvoiceStatus;
import com.ectrip.hqyt.model.enums.PaymentStatus;
import com.ectrip.hqyt.model.enums.SettlementStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class JSONInvoice {

    private Long id;
    private List<JSONOffer> offers;
    private String title;
    private JSONMerchant issuer;
    private JSONLegalPerson receiver;
    private InvoiceStatus status;                        //交易订单状态
    private String outTradeNo;
    private BigDecimal totalPrice;
    private JSONMarketplace source;                        //来源
    private PaymentStatus paymentStatus;                //支付状态
    private BigDecimal payedPrice;                        //已支付金额
    private Date paymentDateTime;                        //付款时间
    private SettlementStatus settlementStatus;            //交易结算状态
    private BigDecimal refundedPrice;                    //已退款金额
    private BigDecimal refundablePrice;                    //可以退款金额
    private String tradeNo;                                //系统生成的流水号
    private InvoiceRefundStatus refundStatus;            //交易退款状态
    private BigDecimal settlementAmount;                //待结算金额
    private BigDecimal settlementedAmount;                //已结算金额
    private String currency;                            //币种
    private BigDecimal serviceAmount;                    //服务费
    private String chargeOperator;                        //充值操作人
    private Date txnCompleteTime;                        //担保交易核销完成时间
    private Date validityTimeLimit;
    private Date refundableUntilTime;
    private Date consumeTimeLimit; //消费截止日期
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<JSONOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<JSONOffer> offers) {
        this.offers = offers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JSONMerchant getIssuer() {
        return issuer;
    }

    public void setIssuer(JSONMerchant issuer) {
        this.issuer = issuer;
    }

    public JSONLegalPerson getReceiver() {
        return receiver;
    }

    public void setReceiver(JSONLegalPerson receiver) {
        this.receiver = receiver;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public JSONMarketplace getSource() {
        return source;
    }

    public void setSource(JSONMarketplace source) {
        this.source = source;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getPayedPrice() {
        return payedPrice;
    }

    public void setPayedPrice(BigDecimal payedPrice) {
        this.payedPrice = payedPrice;
    }

    public Date getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Date paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public SettlementStatus getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(SettlementStatus settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public BigDecimal getRefundedPrice() {
        return refundedPrice;
    }

    public void setRefundedPrice(BigDecimal refundedPrice) {
        this.refundedPrice = refundedPrice;
    }

    public BigDecimal getRefundablePrice() {
        return refundablePrice;
    }

    public void setRefundablePrice(BigDecimal refundablePrice) {
        this.refundablePrice = refundablePrice;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public InvoiceRefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(InvoiceRefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public BigDecimal getSettlementedAmount() {
        return settlementedAmount;
    }

    public void setSettlementedAmount(BigDecimal settlementedAmount) {
        this.settlementedAmount = settlementedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(BigDecimal serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public String getChargeOperator() {
        return chargeOperator;
    }

    public void setChargeOperator(String chargeOperator) {
        this.chargeOperator = chargeOperator;
    }

    public Date getTxnCompleteTime() {
        return txnCompleteTime;
    }

    public void setTxnCompleteTime(Date txnCompleteTime) {
        this.txnCompleteTime = txnCompleteTime;
    }

    public Date getValidityTimeLimit() {
        return validityTimeLimit;
    }

    public void setValidityTimeLimit(Date validityTimeLimit) {
        this.validityTimeLimit = validityTimeLimit;
    }

    public Date getRefundableUntilTime() {
        return refundableUntilTime;
    }

    public void setRefundableUntilTime(Date refundableUntilTime) {
        this.refundableUntilTime = refundableUntilTime;
    }

    public Date getConsumeTimeLimit() {
        return consumeTimeLimit;
    }

    public void setConsumeTimeLimit(Date consumeTimeLimit) {
        this.consumeTimeLimit = consumeTimeLimit;
    }
}
