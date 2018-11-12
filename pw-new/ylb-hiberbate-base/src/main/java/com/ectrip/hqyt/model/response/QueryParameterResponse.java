package com.ectrip.hqyt.model.response;

import java.util.List;

/**
 * Created by chenxinhao on 2017/4/11.
 */
public class QueryParameterResponse extends Response{

    private List<JSONParameter> parameters;

    public List<JSONParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<JSONParameter> parameters) {
        this.parameters = parameters;
    }
}
