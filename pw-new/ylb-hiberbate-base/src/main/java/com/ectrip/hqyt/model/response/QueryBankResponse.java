package com.ectrip.hqyt.model.response;

import java.util.List;

/**
 * Created by chenxinhao on 2017/2/6.
 */
public class QueryBankResponse extends Response{

    private List<JSONBank> banks;

    public List<JSONBank> getBanks() {
        return banks;
    }

    public void setBanks(List<JSONBank> banks) {
        this.banks = banks;
    }
}
