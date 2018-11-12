package com.ectrip.sys.baidu.task;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.baidu.client.BaiduClient;
import com.ectrip.sys.model.baidu.bean.BaiduTicket;
import com.ectrip.sys.model.baidu.response.Response;
import com.ectrip.ticket.model.order.Stssoldtickettab;

/**
 * Created by chenxinhao on 2017/3/16.
 */
public class BaiduTask {

    private IGenericDao genericDao;

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public void run(){
        List<BaiduTicket> tickets = genericDao.find("from BaiduTicket t where status = '0' ");
        if(tickets != null && !tickets.isEmpty()){
            for (BaiduTicket ticket : tickets){
                boolean b = checkPrintNo(ticket.getTicketNo());
                System.out.println(ticket.getTicketNo()+":"+b);
                if(b){
                    List<String> list = new ArrayList<String>();
                    list.add(ticket.getTicketNo());
                    Response response = BaiduClient.deleteVisitorTicket(ticket.getVisitorId(),list);
                    if(response != null && "1000".equalsIgnoreCase(response.getCode())){
                        ticket.setStatus("1");
                        ticket.setUpdateTime(Tools.getDayTimes());
                        genericDao.update(ticket);
                    }
                }
            }
        }
    }

    /**
     * �鿴Ʊ���Ƿ���Ч
     * @param ticketNo
     * @return
     */
    public boolean checkPrintNo(String ticketNo){
        boolean flag = false;
        String hsql = "from Stssoldtickettab s where szticketprintno = '"+ticketNo+"' ";
        List list = genericDao.find(hsql);
        if(list != null && !list.isEmpty()){
            Stssoldtickettab stold = (Stssoldtickettab) list.get(0);
            hsql = "from Stssoldticketsubtab s where s.id.szsoldticketid="+stold.getId().getSzsoldticketid()+" " +
                    " and s.id.isalesvoucherdetailsid="+stold.getId().getIsalesvoucherdetailsid()+" " +
                    " and s.id.isalesvoucherid="+stold.getId().getIsalesvoucherid()+" " +
                    " and s.id.iticketstationid="+stold.getId().getIticketstationid()+" and s.ipasstimes > 0 " +
                    " and s.ipassedtimes < s.ipasstimes and s.isvalid = 1";
            List list2 = genericDao.find(hsql);
            if(list2 == null || list2.isEmpty()){
                hsql = "from Stssoldticketsubtab s where s.id.szsoldticketid="+stold.getId().getSzsoldticketid()+" " +
                        " and s.id.isalesvoucherdetailsid="+stold.getId().getIsalesvoucherdetailsid()+" " +
                        " and s.id.isalesvoucherid="+stold.getId().getIsalesvoucherid()+" " +
                        " and s.id.iticketstationid="+stold.getId().getIticketstationid()+" and s.ipasstimes = 0 and s.isvalid = 1";
                List list3 = genericDao.find(hsql);
                if(list3 == null || list3.isEmpty()){
                    flag = true;
                }
            }
        }else{
            flag = true;
        }
        return flag;
    }
}
