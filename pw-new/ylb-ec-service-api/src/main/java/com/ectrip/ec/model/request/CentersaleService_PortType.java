/*package com.ectrip.ec.model.request;


public interface CentersaleService_PortType {
    public ResultBean updateT_order(String orid, Long iscenicid, Long iemployeeid, Double mont) throws java.rmi.RemoteException;
    public ResultBean empztlogin(Long iscenicid, String userid, String password) throws java.rmi.RemoteException;
    public ResultBean getscenic() throws java.rmi.RemoteException;
    public ResultBean emplogin(Long iscenicid, String userid, String password) throws java.rmi.RemoteException;
    public ResultBean getTrip(Long itickettypeid, String stdt) throws java.rmi.RemoteException;
    public ResultBean getAllTrip(Long iscenicid, String stdt) throws java.rmi.RemoteException;
    public ResultBean getT_order(String carno, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean getT_order(String orid) throws java.rmi.RemoteException;
    public ResultBean getT_orderlistty(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean updatetdseatstuts(String seatstatustablists) throws java.rmi.RemoteException;
    public ResultBean getAutoT_orderlist(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean updateseatstuts(String comticketsalesdetail) throws java.rmi.RemoteException;
    public ResultBean updatehfseatlock(Long iseatlockid) throws java.rmi.RemoteException;
    public ResultBean uphfshuijiseat(String comticketsalesdetails, String seatids) throws java.rmi.RemoteException;
    public ResultBean getseatlocklist(String date) throws java.rmi.RemoteException;
    public ResultBean getVenueseatsalecount(Long employeeid, String date) throws java.rmi.RemoteException;
    public ResultBean getareaseatstusts(Long ivenueid, Long ivenueareaid, String stdt, Long tripid) throws java.rmi.RemoteException;
    public ResultBean updateseatlock(Long iseatlockid, long iticknumb) throws java.rmi.RemoteException;
    public ResultBean updatehfseatstuts(String seatstatustablists) throws java.rmi.RemoteException;
    public ResultBean getTripcontrol(Long iscenicid, String stdt) throws java.rmi.RemoteException;
    public ResultBean getProductcontrol(Long itickettypeid, Long tripid, String stdt) throws java.rmi.RemoteException;
    public ResultBean getProductdatacontrol(Long itickettypeid, String stdt) throws java.rmi.RemoteException;
    public ResultBean getT_orderauto(String carno, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean getT_zorderlist(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public int changepassword(String userid, String oldpassword, String newpassword) throws java.rmi.RemoteException;
    public ResultBean chupiaoTRealname(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean getT_orderbyorid(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean updatecptdorder(String productcontrols, String orid, Long iscenicid, double tpmont, double tpsx, String zfusid, String neworid, String mo, String yo, String to, String tl, String tzl) throws java.rmi.RemoteException;
    public ResultBean getT_orderlist(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean getM_orderzfusidstdt(String orid) throws java.rmi.RemoteException;
    public ResultBean getT_zorderlistbyorderlistid(Long orderlistid, String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean getT_orderByWin(String carno, Long iscenicid, Long winid) throws java.rmi.RemoteException;
    public ResultBean getcancelproductcontrol(String productcontrols) throws java.rmi.RemoteException;
    public ResultBean updatecphfT_order(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public void updateRaftcheck(String massages) throws java.rmi.RemoteException;
    public ResultBean getPrdtripvenuemanage(Long itickettypeid, Long tripid, String stdt) throws java.rmi.RemoteException;
    public ResultBean updatehfcptdorder(String productcontrols, String orid, Long iscenicid, double tpmont, double tpsx, String zfusid, String neworid) throws java.rmi.RemoteException;
    public void updateCheckcount(String massages) throws java.rmi.RemoteException;
    public ResultBean getReservecontrol(Long itickettypeid, String usid, String stdt, Long tripid) throws java.rmi.RemoteException;
    public ResultBean updateChangecheckticket(String massages) throws java.rmi.RemoteException;
    public ResultBean cancelstopraftorder(String orid, Long iscenicid, String neworid, Long iemployeeid) throws java.rmi.RemoteException;
    public Long zhuceticketwin(Long iticketwinid, Long iticketstationid, Long iscenicid, String szticketwincode, String szticketwinname, String szipaddress, String dtsellstart, String dtsellend, String bywintype, Long byisuse, String szmemo, Long iversion, String ipaddress) throws java.rmi.RemoteException;
    public ResultBean chupiaoT_orderlist(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean chupiaoT_zorderlist(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean chupiaoT_order(String orid, Long iscenicid) throws java.rmi.RemoteException;
    public ResultBean updateordercancelProductcontrol(String productcontrols, String orid, String szsalesvoucherno) throws java.rmi.RemoteException;
    public void saveUseryfk(String usid, String orid, int types, String yfkfs, Double mont, Double tpsx, String note) throws java.rmi.RemoteException;
    public float getsumjifen(String usid) throws java.rmi.RemoteException;
    public ResultBean getsysparcs(String pmky, String pmcd) throws java.rmi.RemoteException;
    public ResultBean getzhiwen(String carno) throws java.rmi.RemoteException;
    public ResultBean savetuiding(String mo, String yo, String to, String tl, String tzl) throws java.rmi.RemoteException;
    public ResultBean saveStock(String stockJson, String isCheck) throws java.rmi.RemoteException;
    public ResultBean getResultBean() throws java.rmi.RemoteException;
    public ResultBean cancelt_order(String orid, Long iscenicid, Double mont, Long iemployeeid, String szsalesvoucherno, String message, Long isqt, Long forceemid) throws java.rmi.RemoteException;
    public ResultBean cythexiao(String orid) throws java.rmi.RemoteException;
    public ResultBean checkStock(String stockJson) throws java.rmi.RemoteException;
    public String getOrderZffs(String orid) throws java.rmi.RemoteException;
    public ResultBean getCustom(String usid) throws java.rmi.RemoteException;
    public ResultBean deleteStock(String orid, Long provider) throws java.rmi.RemoteException;
    public ResultBean getseatstusts(Long ivenueid, String stdt, Long tripid) throws java.rmi.RemoteException;
    public ResultBean upshuijiseat(String comticketsalesdetails) throws java.rmi.RemoteException;
    public ResultBean updatefhordercancelProductcontrol(String productcontrols, String orid, String szsalesvoucherno) throws java.rmi.RemoteException;
    public ResultBean getVenueseatsalecountbyiscenicid(String scenic, String date) throws java.rmi.RemoteException;
    public ResultBean updateProductcontrol(String productcontrols) throws java.rmi.RemoteException;
    public ResultBean updatecancelProductcontrol(String productcontrols) throws java.rmi.RemoteException;
    public ResultBean updateProductReserve(String productcontrols, String usid) throws java.rmi.RemoteException;
    public ResultBean updatefhcancelProductcontrol(String productcontrols) throws java.rmi.RemoteException;
    public ResultBean updatecancelProductreserve(String productcontrols, String usid) throws java.rmi.RemoteException;
    public ResultBean debookTicket(String type,String torderlists,String newticketinfo,
                                   String user, String orid, String iscenicid) throws java.rmi.RemoteException;
    public ResultBean debookAllTicket(String type, String user, String orid, String iscenicid) throws java.rmi.RemoteException;
}
*/