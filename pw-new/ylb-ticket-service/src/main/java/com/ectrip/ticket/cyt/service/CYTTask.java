/*package com.ectrip.ticket.cyt.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.cyt.client.v1.CYTClient;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.cyt.model.CYTPojo;

public class CYTTask extends TimerTask{
    private ICheckService checkService;

    public ICheckService getCheckService() {
        return checkService;
    }

    public void setCheckService(ICheckService checkService) {
        this.checkService = checkService;
    }

    public CYTTask() {
        super();
    }
    private static Object obj = new Object();
    @SuppressWarnings("rawtypes")
    public void run() {
        try {
            synchronized (obj) {
                IOrderService s = (IOrderService) SpringUtil
                        .getBean("orderService");
                List syses = checkService.find("from Sysparv5 s where s.id.pmky='CYTX' and s.id.pmcd='01'");
                if(syses != null && syses.size() > 0){
                    Sysparv5 sys = (Sysparv5) syses.get(0);
                    if(!getIPv4().contains(sys.getPmvb())) return;
                }
                if(!"0".equals(ConstUtils.supportCYT) && CYTClient.CYTServiceURL != null && !"".equals(CYTClient.CYTServiceURL)){
                    //				ICheckService service = (ICheckService) SpringUtil.getBean("checkService");
                    List<CYTPojo> list = checkService.findNotCallbackCYTPojo();
                    for (CYTPojo pojo : list) {
                        String today = Tools.getDays();
                        String consumedate = pojo.getConsumedate();
                        String pn = pojo.getPrintNum();
                        int count = (pn==null || !pn.matches("\\d*")?0:Integer.parseInt(pn));
                        boolean b = false;
                        if(!StringUtils.isBlank(consumedate)){
                            int day = Tools.getDayNumb(consumedate.substring(0, 10), today);
                            if(count <= 4*day){
                                b = true;
                            }
                        }else{
                            if(count <= 4){
                                b = true;
                            }
                        }
                        if(b){
                            if(CYTClient.noticeOrderConsumed(pojo)){
                                System.out.println("����֪ͨ�ص��ɹ�!");
                                pojo.setState(1);
                                checkService.updatePojo(pojo);
                            }else{
                                System.out.println("����֪ͨ�ص�ʧ��");
                                pojo.setPrintNum(String.valueOf(++count));
                                checkService.updatePojo(pojo);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<String> getIPv4(){
        List<String> list = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    String ip = ips.nextElement().getHostAddress();
                    if(ip.matches("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}")) list.add(ip);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}*/