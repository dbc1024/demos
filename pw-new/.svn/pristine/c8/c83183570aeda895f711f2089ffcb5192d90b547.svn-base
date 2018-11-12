package com.ectrip.ec.report.system.ticketsale.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketCheckDao;
import com.ectrip.ec.report.system.ticketsale.service.iservice.ITicketCheckService;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class TicketCheckService implements ITicketCheckService {
    private ITicketCheckDao ticketCheckDao;

    public List getStssoldtickettablist(String printno) {
        return ticketCheckDao.getStssoldtickettablist(printno);
    }

    public ITicketCheckDao getTicketCheckDao() {
        return ticketCheckDao;
    }

    public void setTicketCheckDao(ITicketCheckDao ticketCheckDao) {
        this.ticketCheckDao = ticketCheckDao;
    }

    public List getStssoldtickettablist(Long iticketstationid, Long iserialnum) {
        return ticketCheckDao.getStssoldtickettablist(iticketstationid, iserialnum);
    }

    public Stssalesvouchertab getStssalesvouchertab(Long iticketstationid,
                                                    Long isalesvoucherid) {
        return ticketCheckDao.getStssalesvouchertab(iticketstationid,
                isalesvoucherid);
    }

    public List getchecklist(Long szsoldticketid, Long isalesvoucherdetailsid,
                             Long isalesvoucherid, Long iticketstationid) {
        return ticketCheckDao.getchecklist(szsoldticketid, isalesvoucherdetailsid,
                isalesvoucherid, iticketstationid);
    }
    public Edmtickettypetab getEdmtickettypetab(Long itickettypeid){
        return ticketCheckDao.getEdmtickettypetab(itickettypeid);
    }
    public List getEsbticketstationtab(String szstationcode){
        return ticketCheckDao.getEsbticketstationtab(szstationcode);
    }

    public TOrder getTOrder(String orid,Long iscenicid){
        return (TOrder)ticketCheckDao.get(TOrder.class, new TOrderId(orid,iscenicid));
    }

    public PaginationSupport queryCheckTicketList(int radiobutton,String gardes,
                                                  Long itickettypeid, String rzti, String ldti, String starttime,
                                                  String endtime, int page, int pageSize, String url) {
        return ticketCheckDao.queryCheckTicketList(radiobutton,gardes, itickettypeid, rzti, ldti, starttime, endtime, page, pageSize, url);
    }

    public List queryCheckTicketList(int radiobutton,String gardes,
                                     Long itickettypeid, String rzti, String ldti, String starttime, String endtime){
        return ticketCheckDao.queryCheckTicketList(radiobutton, gardes, itickettypeid, rzti, ldti, starttime, endtime);
    }
    /**
     * *
     * Describe:
     * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketCheckService#queryTicketNoList(java.lang.String, java.util.List, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
     * @return
     * @author huying
     * Date:2015-7-17
     */
    public PaginationSupport queryTicketNoList(String gardes,List gradeList,String szticketprintno,Long iscenicid,Long ibusinessid,String manyouno,
                                               String rzti, String ldti, String rzhs, String ldhs,int page, int pageSize, String url) {
        return ticketCheckDao.queryTicketNoList(gardes, gradeList,szticketprintno,iscenicid, ibusinessid, manyouno, rzti, ldti, rzhs, ldhs, page, pageSize, url);
    }
    /**
     * *
     * Describe:
     * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketCheckService#findgardeList(java.lang.String)
     * @param gardes
     * @return
     * @author huying
     * Date:2015-7-17
     */
    public List findgardeList(String gardes){
        return ticketCheckDao.findgardeList(gardes);
    }

}
