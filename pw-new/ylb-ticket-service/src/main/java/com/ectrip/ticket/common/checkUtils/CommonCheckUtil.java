package com.ectrip.ticket.common.checkUtils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.afcset.dao.IEsbaccessequiptabDAO;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;

/**
 * Created by cxh on 2016/01/14.
 */
@Component
public class CommonCheckUtil {
	
	@Autowired
	private IEsbaccessequiptabDAO esbaccessequiptabDAO;

    /**
     * 判断实名制证件号限购
     * @param map 证件号购买数量 key:证件号 value:数量
     * @param saleType 销售渠道 0：线上  1：线下
     * @return
     */
    public static String checkLimitSaleByCard(Map<String,Integer> map,int saleType,String tourdate){
        IGenericService genericService = (IGenericService) SpringUtil.getBean("genericService");
        Sysparv5 v5 = (Sysparv5) genericService.get(Sysparv5.class,new Sysparv5Id("COMM","0005"));
        if(v5 != null){//总控制
            if(!StringUtils.isBlank(v5.getPmvb()) && !"0".equals(v5.getPmvb())){
                int canBuyNumb = Integer.parseInt(v5.getPmvb());
                for (String key : map.keySet()){
                    int numb = map.get(key);
                    if(numb > canBuyNumb){
                        return "证件号"+key+"超过限购数量["+canBuyNumb+"]";
                    }else{
                        String sql = "from Stssoldtickettab where myzj = '"+key+"' and dtstartdate = '"+tourdate+"'";
                        List list = genericService.find(sql);
                        if(list != null && !list.isEmpty()){
                            numb += list.size();
                        }
                        sql = "select tr.seq as seq from TRealname tr,TOrder t where tr.idcard = '"+key+"'" +
                                " and tr.orid = t.id.orid and t.dtstartdate = '"+tourdate+"' and t.ddzt = '02' ";
                        list = genericService.find(sql);
                        if(list != null && !list.isEmpty()){
                            numb += list.size();
                        }
                        if(numb > canBuyNumb){
                            return "证件号"+key+"超过限购数量["+canBuyNumb+"]";
                        }
                    }
                }
            }else if(saleType == 0 && !StringUtils.isBlank(v5.getPmvc()) && !"0".equals(v5.getPmvc())){//线上限购
                int canBuyNumb = Integer.parseInt(v5.getPmvc());
                for (String key : map.keySet()){
                    int numb = map.get(key);
                    if(numb > canBuyNumb){
                        return "证件号"+key+"超过限购数量["+canBuyNumb+"]";
                    }else{
                        String sql = "select tr.seq as seq from TRealname tr,TOrder t where tr.idcard = '"+key+"'" +
                                " and tr.orid = t.id.orid and t.dtstartdate = '"+tourdate+"' and t.ddzt = '02' ";
                        List list = genericService.find(sql);
                        if(list != null && !list.isEmpty()){
                            numb += list.size();
                        }
                        if(numb > canBuyNumb){
                            return "证件号"+key+"超过限购数量["+canBuyNumb+"]";
                        }
                    }
                }
            }else if(saleType == 1 && !StringUtils.isBlank(v5.getPmvd()) && !"0".equals(v5.getPmvd())){//线下限购
                int canBuyNumb = Integer.parseInt(v5.getPmvd());
                for (String key : map.keySet()){
                    int numb = map.get(key);
                    if(numb > canBuyNumb){
                        return "证件号"+key+"超过限购数量["+canBuyNumb+"]";
                    }else{
                        String sql = "select distinct new map(st.szticketprintno as szticketprintno) from Stssoldtickettab st,Stssalesvouchertab s where st.myzj = '"+key+"' and st.dtstartdate = '"+tourdate+"' " +
                                " and st.id.isalesvoucherid = s.id.isalesvoucherid and st.id.iticketstationid = s.id.iticketstationid and substr(s.szsalesvoucherno,9,3) not in ('000','888','999','777')";
                        List list = genericService.find(sql);
                        if(list != null && !list.isEmpty()){
                            numb += list.size();
                        }
                        if(numb > canBuyNumb){
                            return "证件号"+key+"超过限购数量["+canBuyNumb+"]";
                        }
                    }
                }
            }
        }
        return "";
    }

    public static boolean changeDate(String type,String printNo,String rzti,String ldti){
        IGenericDao genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        try{
            if(type.equals("02")){//订单号
                String sql = "from Stssalesvouchertab where szsalesvoucherno = '"+printNo+"'";
                List list = genericDao.find(sql);
                if(list != null && !list.isEmpty()){
                    Stssalesvouchertab stss = (Stssalesvouchertab) list.get(0);
                    sql = "from Stssoldtickettab where id.isalesvoucherid="+stss.getId().getIsalesvoucherid()+
                            " and id.iticketstationid="+stss.getId().getIticketstationid();
                    List slist = genericDao.find(sql);
                    if(slist != null && !slist.isEmpty()){
                        for(int i=0;i<slist.size();i++){
                            Stssoldtickettab stsso = (Stssoldtickettab) slist.get(i);

                            String sql2 = "from Stssoldticketsubtab where id.szsoldticketid="+stsso.getId().getSzsoldticketid()+
                                    " and id.isalesvoucherdetailsid"+stsso.getId().getIsalesvoucherdetailsid()+" " +
                                    " and id.isalesvoucherid=" + stsso.getId().getIsalesvoucherid()+
                                    " and id.iticketstationid="+stsso.getId().getIticketstationid();
                            List sulist = genericDao.find(sql2);
                            if(sulist != null && sulist.isEmpty()){
                                for(int j = 0;j<sulist.size();j++){
                                    Stssoldticketsubtab so = (Stssoldticketsubtab) sulist.get(j);
                                    if(!StringUtils.isBlank(rzti)){
                                        so.setDtbegindate(rzti + " 00:00:00");
                                    }
                                    if(StringUtils.isBlank(ldti)){
                                        so.setDtenddate(ldti + " 23:59:59");
                                    }
                                    genericDao.update(so);
                                }
                            }

                            if(!StringUtils.isBlank(rzti)){
                                stsso.setDtstartdate(rzti);
                            }
                            if(StringUtils.isBlank(ldti)){
                                stsso.setDtenddate(ldti);
                            }
                            genericDao.update(stsso);
                        }
                    }
                }
            }else if(type.equals("01")){//票号
                String sql = "from Stssoldtickettab where szticketprintno = '"+printNo+"' ";
                List list = genericDao.find(sql);
                if(list != null && !list.isEmpty()){
                    for(int i=0;i<list.size();i++){
                        Stssoldtickettab stsso = (Stssoldtickettab) list.get(i);

                        String sql2 = "from Stssoldticketsubtab where id.szsoldticketid="+stsso.getId().getSzsoldticketid()+
                                " and id.isalesvoucherdetailsid"+stsso.getId().getIsalesvoucherdetailsid()+" " +
                                " and id.isalesvoucherid=" + stsso.getId().getIsalesvoucherid()+
                                " and id.iticketstationid="+stsso.getId().getIticketstationid();
                        List sulist = genericDao.find(sql2);
                        if(sulist != null && sulist.isEmpty()){
                            for(int j = 0;j<sulist.size();j++){
                                Stssoldticketsubtab so = (Stssoldticketsubtab) sulist.get(j);
                                if(!StringUtils.isBlank(rzti)){
                                    so.setDtbegindate(rzti + " 00:00:00");
                                }
                                if(StringUtils.isBlank(ldti)){
                                    so.setDtenddate(ldti + " 23:59:59");
                                }
                                genericDao.update(so);
                            }
                        }

                        if(!StringUtils.isBlank(rzti)){
                            stsso.setDtstartdate(rzti);
                        }
                        if(StringUtils.isBlank(ldti)){
                            stsso.setDtenddate(ldti);
                        }
                        genericDao.update(stsso);
                    }
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * @param mobile 手机号码
     * @param ip IP地址
     * @param type 短信类型
     * @return
     */
    public static String checkMobileMessage(String mobile,String ip,String type){
        IGenericDao genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        List list = genericDao.find("select nvl(count(*),0) from Mbmessage where ip='"+ip+"' and substr(dtime,1,10) = '"+ Tools.getDays()+"' and type = '"+type+"'");
        if(list != null && !list.isEmpty()){
            int count = Integer.parseInt(list.get(0).toString());
            if(count >= 20){
                return "您的重试次数已超过当日IP最大限制20次";
            }
        }
        list = genericDao.find("select nvl(count(*),0) from Mbmessage where revmbno = '"+mobile+"' and substr(dtime,1,10) = '"+Tools.getDays()+"' and type = '"+type+"' ");
        if(list != null && !list.isEmpty()){
            int count = Integer.parseInt(list.get(0).toString());
            if(count >= 5){
                return "您的重试次数已超过当日号码最大限制5次";
            }
        }
        return null;
    }

    /**
     * 根据闸机ID判断服务商是否允许直接出票
     * @param posId 闸机ID
     * @return
     */
    public  boolean checkConsumeOnline(String posId){
        boolean b = false;
        //IGenericDao genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        List list = esbaccessequiptabDAO.find("from Esbaccessequiptab where id.iaccessequipid = "+posId);
        if(list != null && !list.isEmpty()){
            Esbaccessequiptab access = (Esbaccessequiptab) list.get(0);
            Esbscenicareatab scenic = (Esbscenicareatab) esbaccessequiptabDAO.get(Esbscenicareatab.class,access.getId().getIscenicid());
            Hotelprovider hp = (Hotelprovider) esbaccessequiptabDAO.get(Hotelprovider.class,scenic.getIscenicid());
            if(hp != null && hp.getInoteger3() == 1){
                b = true;
            }
        }
        return b;
    }
}
