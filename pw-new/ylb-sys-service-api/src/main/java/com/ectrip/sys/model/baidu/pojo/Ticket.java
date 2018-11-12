package com.ectrip.sys.model.baidu.pojo;

import java.util.List;

/**
 * Created by chenxinhao on 2017/3/1.
 */
public class Ticket {

    private List<String> groupIds;

    private String ticketId;

    private String startTime;

    private String endTime;

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
