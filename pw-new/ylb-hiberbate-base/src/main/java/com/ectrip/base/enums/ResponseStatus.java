package com.ectrip.base.enums;

public enum ResponseStatus {
    SUCCESS,
    ERROR;

    public int value(){
        return this.ordinal();
    }
}
