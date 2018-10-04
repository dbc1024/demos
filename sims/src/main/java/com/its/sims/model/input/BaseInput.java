package com.its.sims.model.input;

/**
 * Created by csz on 2017/6/8.
 */
public class BaseInput {

    private int page = 1;//当前页码

    private int pagesize = 10;//页大小

    private String keyword;//通用查询关键字

    //sql分页条件
    public int getBegin(){
        return (page - 1) * pagesize;
    }
    public int getEnd(){
        return pagesize;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
