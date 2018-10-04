package com.its.sims.model;

/**
 * Created by csz on 2017/8/16.
 */
public class AjaxResult {

    private boolean success;

    private String description;

    private Object data;

    public AjaxResult() {
        this(true, "操作成功！");
    }

    public AjaxResult(boolean success) {
        this.success = success;
    }

    public AjaxResult(boolean success, String description) {
        this.success = success;
        this.description = description;
    }

    public AjaxResult(boolean success, String description, Object data) {
        this.success = success;
        this.description = description;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
