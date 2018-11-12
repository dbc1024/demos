package com.ectrip.ticket.model.provider;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by cxh on 2016/01/18.
 */
@Table(name="TICKET_PRINTNO")
@Entity
public class TicketPrintNo {
	@Id
    private Long seq;//主键ID
    private Long tickettypeid;//产品ID
    private String ticketPrintno;//票号
    @Column(name="SERIAL_FIRST_INDEX")
    private Long serialFirstIndex;//流水号开始位置
    @Column(name="SERIAL_LAST_INDEX")
    private Long serialLastIndex;//流水号截止位置
    private Long serialNumber;//流水号
    private Long status;//状态 0：无效 1：有效 -1：已售

    @Transient
    private String sztickettypename;

    public TicketPrintNo() {
        super();
    }

    public TicketPrintNo(Long seq, Long tickettypeid, String ticketPrintno, Long serialFirstIndex, Long serialLastIndex,
                         Long serialNumber, Long status) {
        this.seq = seq;
        this.tickettypeid = tickettypeid;
        this.ticketPrintno = ticketPrintno;
        this.serialFirstIndex = serialFirstIndex;
        this.serialLastIndex = serialLastIndex;
        this.serialNumber = serialNumber;
        this.status = status;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getTickettypeid() {
        return tickettypeid;
    }

    public void setTickettypeid(Long tickettypeid) {
        this.tickettypeid = tickettypeid;
    }

    public String getTicketPrintno() {
        return ticketPrintno;
    }

    public void setTicketPrintno(String ticketPrintno) {
        this.ticketPrintno = ticketPrintno;
    }

    public Long getSerialFirstIndex() {
        return serialFirstIndex;
    }

    public void setSerialFirstIndex(Long serialFirstIndex) {
        this.serialFirstIndex = serialFirstIndex;
    }

    public Long getSerialLastIndex() {
        return serialLastIndex;
    }

    public void setSerialLastIndex(Long serialLastIndex) {
        this.serialLastIndex = serialLastIndex;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getSztickettypename() {
        return sztickettypename;
    }

    public void setSztickettypename(String sztickettypename) {
        this.sztickettypename = sztickettypename;
    }
}
