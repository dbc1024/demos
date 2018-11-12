package com.ectrip.hqyt.model.response;

public class CreateClientResponse extends Response{

    private JSONClient client;

    public JSONClient getClient() {
        return client;
    }

    public void setClient(JSONClient client) {
        this.client = client;
    }
}
