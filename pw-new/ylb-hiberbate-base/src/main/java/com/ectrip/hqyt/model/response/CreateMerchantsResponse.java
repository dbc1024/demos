package com.ectrip.hqyt.model.response;

/**
 * Created by chenxinhao on 2017/2/6.
 */
public class CreateMerchantsResponse extends Response{

    private JSONMerchant merchant;

    public JSONMerchant getMerchant() {
        return merchant;
    }

    public void setMerchant(JSONMerchant merchant) {
        this.merchant = merchant;
    }
}
