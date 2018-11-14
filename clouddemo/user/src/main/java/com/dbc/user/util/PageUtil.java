package com.dbc.user.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author: CSZ
 * @date: 2018/11/13 11:33
 */
public class PageUtil {



    /**
     * 总记录数
     */
    private long totalCount;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 每页条数
     */
    private long pageSize;
    /**
     * 页码
     */
    private long pageNum;
    /**
     * 列表数据
     */
    private List<?> list;




    public static PageUtil result(Page<Object> page){
        PageInfo pageInfo = new PageInfo<>(page.getResult());

        PageUtil pageUtil = new PageUtil();
        pageUtil.setTotalCount(pageInfo.getTotal());
        pageUtil.setTotalPage(pageInfo.getPages());
        pageUtil.setPageNum(pageInfo.getPageNum());
        pageUtil.setList(pageInfo.getList());
        pageUtil.setPageSize(pageInfo.getPageSize());

        return pageUtil;
    }


    public static PageUtil result(IPage<?> page){

        PageUtil pageUtil = new PageUtil();
        pageUtil.setTotalCount(page.getTotal());
        pageUtil.setTotalPage(page.getPages());
        pageUtil.setPageNum(page.getCurrent());
        pageUtil.setList(page.getRecords());
        pageUtil.setPageSize(page.getSize());

        return pageUtil;
    }


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
