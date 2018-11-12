package com.ectrip.hqyt.model.response;

/**
 * Created by chenxinhao on 17/1/13.
 */
public class JSONBalance {

    private Double liability;

    private Double withdrawable;

    public Double getLiability() {
        return liability;
    }

    public void setLiability(Double liability) {
        this.liability = liability;
    }

    public Double getWithdrawable() {
        return withdrawable;
    }

    public void setWithdrawable(Double withdrawable) {
        this.withdrawable = withdrawable;
    }
}
