package com.ectrip.ec.report.system.ticketsale.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public interface ITicketCheckService {
    public List getStssoldtickettablist(String printno);
    public List getStssoldtickettablist(Long iticketstationid,Long iserialnum);
    public Stssalesvouchertab getStssalesvouchertab(Long iticketstationid,Long isalesvoucherid);
    public List getchecklist(Long szsoldticketid,Long isalesvoucherdetailsid,Long isalesvoucherid,Long iticketstationid);
    public Edmtickettypetab getEdmtickettypetab(Long itickettypeid);
    public List getEsbticketstationtab(String szstationcode);
    public TOrder getTOrder(String orid,Long iscenicid);
    public PaginationSupport queryCheckTicketList(int radiobutton,String gardes,
                                                  Long itickettypeid, String rzti, String ldti, String starttime, String endtime,
                                                  int page, int pageSize, String url);
    public List queryCheckTicketList(int radiobutton,String gardes,
                                     Long itickettypeid, String rzti, String ldti, String starttime, String endtime);

    public PaginationSupport queryTicketNoList(String gardes,List gradeList,String szticketprintno,Long iscenicid,Long ibusinessid,String manyouno,
                                               String rzti, String ldti, String rzhs, String ldhs,int page, int pageSize, String url);
    public List findgardeList(String gardes);

}

