package com.ectrip.hqyt.model.response;

import java.util.List;

/**
 * Created by chenxinhao on 2017/2/6.
 */
public class QueryRateResponse extends Response{

    private List<JSONRate> rates;

    public List<JSONRate> getRates() {
        return rates;
    }

    public void setRates(List<JSONRate> rates) {
        this.rates = rates;
    }
}
