package com.ectrip.ticket.model.stock;

/**
 * Created by cxh on 2015/11/26.
 */
public class StockOrderInfo {
    private String orid;//订单号
    private Long providerId;//服务商ID
    private Long productId;//产品ID
    private Long priceId;//价格ID
    private String usid;//用户ID
    private Long numb;//数量
    private String stockDate;//日期
    private Long timeId;//分时时段Id
    
    public Long getTimeId() {
    	if(timeId == null) {
    		timeId = 0L;
    	}
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

	public String getOrid() {
        return orid;
    }

    public void setOrid(String orid) {
        this.orid = orid;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public Long getNumb() {
        return numb;
    }

    public void setNumb(Long numb) {
        this.numb = numb;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }
}
