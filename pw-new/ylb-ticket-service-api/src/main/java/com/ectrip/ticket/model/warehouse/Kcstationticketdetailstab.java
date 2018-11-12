package com.ectrip.ticket.model.warehouse;

/**仓库结存明细
 * Kcstationticketdetailstab entity. @author MyEclipse Persistence Tools
 */

public class Kcstationticketdetailstab  implements java.io.Serializable {


    // Fields

    private Long idetailsid;
    private Long itickettypeid;			//票ID
    private Long iwarehouseid;				//仓库ID
    private Long iamount;					//数量
    private Long int1;
    private Long int2;
    private String note1;
    private String note2;


    // Constructors

    /** default constructor */
    public Kcstationticketdetailstab() {
    }

    /** minimal constructor */
    public Kcstationticketdetailstab(Long idetailsid, Long itickettypeid, Long iwarehouseid, Long iamount) {
        this.idetailsid = idetailsid;
        this.itickettypeid = itickettypeid;
        this.iwarehouseid = iwarehouseid;
        this.iamount = iamount;
    }

    /** full constructor */
    public Kcstationticketdetailstab(Long idetailsid, Long itickettypeid, Long iwarehouseid, Long iamount, Long int1, Long int2, String note1, String note2) {
        this.idetailsid = idetailsid;
        this.itickettypeid = itickettypeid;
        this.iwarehouseid = iwarehouseid;
        this.iamount = iamount;
        this.int1 = int1;
        this.int2 = int2;
        this.note1 = note1;
        this.note2 = note2;
    }


    // Property accessors

    public Long getIdetailsid() {
        return this.idetailsid;
    }

    public void setIdetailsid(Long idetailsid) {
        this.idetailsid = idetailsid;
    }

    public Long getItickettypeid() {
        return this.itickettypeid;
    }

    public void setItickettypeid(Long itickettypeid) {
        this.itickettypeid = itickettypeid;
    }

    public Long getIwarehouseid() {
        return this.iwarehouseid;
    }

    public void setIwarehouseid(Long iwarehouseid) {
        this.iwarehouseid = iwarehouseid;
    }

    public Long getIamount() {
        return this.iamount;
    }

    public void setIamount(Long iamount) {
        this.iamount = iamount;
    }

    public Long getInt1() {
        return this.int1;
    }

    public void setInt1(Long int1) {
        this.int1 = int1;
    }

    public Long getInt2() {
        return this.int2;
    }

    public void setInt2(Long int2) {
        this.int2 = int2;
    }

    public String getNote1() {
        return this.note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return this.note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }









}