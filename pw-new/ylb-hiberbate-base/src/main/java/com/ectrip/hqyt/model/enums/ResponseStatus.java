package com.ectrip.hqyt.model.enums;

public enum ResponseStatus {
    SUCCESS,
    ERROR;

    public int value(){
        return this.ordinal();
    }
}
