package com.ectrip.ec.report.system.datereport.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.report.sales.Stscomticketsalesdetailstabls;
import com.ectrip.ec.model.report.sales.StscomticketsalesdetailstablsId;
import com.ectrip.ec.model.report.sales.Stssalessettlementtablist;
import com.ectrip.ec.model.report.sales.StssalessettlementtablistId;
import com.ectrip.ec.model.report.sales.Stssalesvoucherdetailstablist;
import com.ectrip.ec.model.report.sales.StssalesvoucherdetailstablistId;
import com.ectrip.ec.model.report.sales.Stssalesvouchertablist;
import com.ectrip.ec.model.report.sales.StssalesvouchertablistId;
import com.ectrip.ec.model.report.sales.Stssoldticketsubtablist;
import com.ectrip.ec.model.report.sales.StssoldticketsubtablistId;
import com.ectrip.ec.model.report.sales.Stssoldtickettablist;
import com.ectrip.ec.model.report.sales.StssoldtickettablistId;
import com.ectrip.ec.report.system.datereport.dao.idao.ISaleDataTransferDao;
import com.ectrip.ec.report.system.datereport.service.iservice.ISaleDataTransferService;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;

/**
 * Created by cxh on 2016/05/09.
 */
public class SaleDataTransferService extends GenericService implements ISaleDataTransferService {

    ISaleDataTransferDao saleDataTransferDao;

    public ISaleDataTransferDao getSaleDataTransferDao() {
        return saleDataTransferDao;
    }

    public void setSaleDataTransferDao(ISaleDataTransferDao saleDataTransferDao) {
        this.saleDataTransferDao = saleDataTransferDao;
    }

    public List findSaleId(String date) {
        return this.saleDataTransferDao.findSaleId(date);
    }

    public void transfer(Long isalesvoucherid, Long iticketstationid) {
        Stssalesvouchertab stssalesvouchertab = this.saleDataTransferDao.findStssalesvouchertab(isalesvoucherid, iticketstationid);
        if (stssalesvouchertab != null) {
            //��ѯԭʼ����
            List<Stssalessettlementtab> stssalessettlementtabs = this.saleDataTransferDao.findStssalessettlementtab(isalesvoucherid, iticketstationid);
            List<Stssalesvoucherdetailstab> stssalesvoucherdetailstabs = this.saleDataTransferDao.findStssalesvoucherdetailstab(isalesvoucherid, iticketstationid);
            List<Stssoldtickettab> stssoldtickettabs = this.saleDataTransferDao.findStssoldtickettab(isalesvoucherid, iticketstationid);
            List<Stssoldticketsubtab> stssoldticketsubtabs = this.saleDataTransferDao.findStssoldticketsubtab(isalesvoucherid, iticketstationid);
            List<Stssoldticketattesttab> stssoldticketattesttabs = this.saleDataTransferDao.findStssoldticketattesttab(isalesvoucherid, iticketstationid);
            List<Stscomticketsalesdetailstab> stscomticketsalesdetailstabs = this.saleDataTransferDao.findStscomticketsalesdetailstab(isalesvoucherid, iticketstationid);

            //ת�Ƶ���ʷ����
            this.saleDataTransferDao.saveStssalesvouchertablist(stssalesvouchertab);
            if (stssalessettlementtabs != null && !stssalessettlementtabs.isEmpty()) {
                for (Stssalessettlementtab stssalessettlementtab : stssalessettlementtabs) {
                    this.saleDataTransferDao.saveStssalessettlementtablist(stssalessettlementtab);
                }
            }
            if (stssalesvoucherdetailstabs != null && !stssalesvoucherdetailstabs.isEmpty()) {
                for (Stssalesvoucherdetailstab stssalesvoucherdetailstab : stssalesvoucherdetailstabs) {
                    this.saleDataTransferDao.saveStssalesvoucherdetailstablist(stssalesvoucherdetailstab);
                }
            }
            if(stscomticketsalesdetailstabs != null && !stscomticketsalesdetailstabs.isEmpty()){
                for (Stscomticketsalesdetailstab stscomticketsalesdetailstab : stscomticketsalesdetailstabs){
                    this.saleDataTransferDao.saveStscomticketsalesdetailstabls(stscomticketsalesdetailstab);
                }
            }
            if (stssoldtickettabs != null && !stssoldtickettabs.isEmpty()) {
                for (Stssoldtickettab stssoldtickettab : stssoldtickettabs) {
                    this.saleDataTransferDao.saveStssoldtickettablist(stssoldtickettab);
                }
            }
            if (stssoldticketsubtabs != null && !stssoldticketsubtabs.isEmpty()) {
                for (Stssoldticketsubtab stssoldticketsubtab : stssoldticketsubtabs) {
                    this.saleDataTransferDao.saveStssoldticketsubtablist(stssoldticketsubtab);
                }
            }
            //ɾ��ԭʼ����
            if (stssoldticketattesttabs != null && !stssoldticketattesttabs.isEmpty()) {
                for (Stssoldticketattesttab stssoldticketattesttab : stssoldticketattesttabs) {
                    this.saleDataTransferDao.delete(stssoldticketattesttab);
                }
            }
            if (stssoldticketsubtabs != null && !stssoldticketsubtabs.isEmpty()) {
                for (Stssoldticketsubtab stssoldticketsubtab : stssoldticketsubtabs) {
                    this.saleDataTransferDao.delete(stssoldticketsubtab);
                }
            }
            if (stssoldtickettabs != null && !stssoldtickettabs.isEmpty()) {
                for (Stssoldtickettab stssoldtickettab : stssoldtickettabs) {
                    this.saleDataTransferDao.delete(stssoldtickettab);
                }
            }
            if(stscomticketsalesdetailstabs != null && !stscomticketsalesdetailstabs.isEmpty()){
                for (Stscomticketsalesdetailstab stscomticketsalesdetailstab : stscomticketsalesdetailstabs){
                    this.saleDataTransferDao.delete(stscomticketsalesdetailstab);
                }
            }
            if (stssalesvoucherdetailstabs != null && !stssalesvoucherdetailstabs.isEmpty()) {
                for (Stssalesvoucherdetailstab stssalesvoucherdetailstab : stssalesvoucherdetailstabs) {
                    this.saleDataTransferDao.delete(stssalesvoucherdetailstab);
                }
            }
            if (stssalessettlementtabs != null && !stssalessettlementtabs.isEmpty()) {
                for (Stssalessettlementtab stssalessettlementtab : stssalessettlementtabs) {
                    this.saleDataTransferDao.delete(stssalessettlementtab);
                }
            }
            this.saleDataTransferDao.delete(stssalesvouchertab);
        }
    }

    public List findOrder(String rzti, String ldti, boolean iscyt) throws Exception {
        return this.saleDataTransferDao.findOrder(rzti, ldti, iscyt);
    }

    public void orderTransfer(String orid, Long iscenicid, Long iticketwinid, Long maxid) {
        List stl = this.saleDataTransferDao.find("from Stssalesvouchertablist where szsalesvoucherno = '"+orid+"'");
        if(stl != null && !stl.isEmpty()){
            return;
        }
        TOrder t = (TOrder) this.saleDataTransferDao.get(TOrder.class, new TOrderId(orid, iscenicid));
        List listorder = this.saleDataTransferDao.find(" from TOrderlist t where t.id.orid='"
                + orid + "' and t.id.iscenicid=" + iscenicid + " and t.numb>0 order by orderlistid");
        List listzorder = this.saleDataTransferDao.find(" from TZorderlist t where t.id.orid='" + orid
                + "' and t.id.iscenicid=" + iscenicid + " and t.znumb>0 order by orderlistid");
        Stssalesvouchertablist s = new Stssalesvouchertablist();
        Esbscenicareatab scenic = (Esbscenicareatab) this.saleDataTransferDao.get(
                Esbscenicareatab.class, t.getId().getIscenicid());
        s.setIscenicid(t.getId().getIscenicid());
        s.setIticketwinid(iticketwinid);
        s.setIbusinessid(t.getIbusinessid());
        s.setIhandler(t.getIsc());
        s.setIpayeer(t.getIsc());
        s.setImaker(t.getIsc());
        s.setIauditor(t.getIsc());
        s.setIaccountreceivable(t.getZfmont());// ʵ��
        s.setIacceptmoney(t.getZfmont());// Ӧ��
        s.setIgivechange(new Double(0));// ����
        s.setSzsalesvoucherno(t.getId().getOrid());
        String today = t.getNotec().substring(0, 10);
        String daytime = t.getNotec();
        s.setIyear(new Long(today.substring(0, 4)));
        s.setImonth(new Long(today.substring(5, 7)));
        s.setIday(new Long(today.substring(8, 10)));
        s.setDtmakedate(daytime);
        s.setDtauditdate(t.getDtstartdate());
        s.setUsid(t.getUsid());
        s.setBisintegral(new Long(0));
        s.setByprintinvoice(new Long(0));
        s.setBysplitway(new Long(2));
        s.setBisreturn(new Long(1));
        s.setBysalesvouchertype("01");
        s.setBypostrecord(new Long(0));
        s.setBysalesvoucherstate(new Long(1));
        s.setBispay(new Long(0));
        s.setBispayee(new Long(0));
        s.setSztravelbillno(t.getSztravelbillno());
        s.setIregionalid(new Long(t.getIregionalid()));
        if (t.getDyusid() == null || t.getDyusid().equals("")) {
            s.setDyusid("daoyou");
        } else {
            s.setDyusid(t.getDyusid());
        }
        s.setTdlx(t.getTdlx());
        Esbticketwintab e = (Esbticketwintab) this.saleDataTransferDao.get(
                Esbticketwintab.class, s.getIticketwinid());
        StssalesvouchertablistId id = new StssalesvouchertablistId();
        id.setIticketstationid(e.getIticketstationid());
        s.setId(id);

        s.getId().setIsalesvoucherid(maxid);
        Stssalessettlementtablist st = new Stssalessettlementtablist();
        StssalessettlementtablistId sid = new StssalessettlementtablistId();
        sid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
        sid.setIticketstationid(s.getId().getIticketstationid());
        sid.setIsalessettlementid(new Long(1));
        st.setId(sid);
        st.setSettlementdata(today);
        st.setSettlementtime(daytime.substring(11));
        st.setDtmakedate(daytime);

        st.setIsettlementid((orid.matches("^\\d{8}999\\d{6}") || orid.matches("^\\d{8}888\\d{6}") || orid.matches("^\\d{8}777\\d{6}")) ? "08" : "01");

        st.setMsettlementmoney(t.getZfmont());
        st.setIversion(new Long(1));
        Esbticketstationtab esbticketstation = (Esbticketstationtab) this.saleDataTransferDao
                .get(Esbticketstationtab.class, e.getIticketstationid());
        List detaillist = new ArrayList();
        List cdetaillist = new ArrayList();
        Long szsoldticketid = new Long(1);
        for (int i = 0; i < listorder.size(); i++) {
            TOrderlist tlist = (TOrderlist) listorder.get(i);
            Stssalesvoucherdetailstablist sd = new Stssalesvoucherdetailstablist();
            StssalesvoucherdetailstablistId sdid = new StssalesvoucherdetailstablistId();
            sdid.setIsalesvoucherdetailsid(tlist.getId().getOrderlistid());
            sdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
            sdid.setIticketstationid(s.getId().getIticketstationid());
            sd.setId(sdid);
            sd.setIticketwinid(s.getIticketwinid());
            sd.setIcrowdkindpriceid(tlist.getIcrowdkindpriceid());
            sd.setItickettypeid(tlist.getItickettypeid());
            sd.setIplayerperticket(new Long(1));// ��/��
            sd.setIticketnum(tlist.getNumb());// ����
            sd.setIticketplayer(tlist.getNumb());// �˴�
            sd.setDtstartdate(tlist.getDtstartdate());
            sd.setDtenddate(tlist.getDtenddate());
            sd.setIstartid(new Long(0));
            sd.setIendid(new Long(0));
            sd.setSzstartserial("0");
            sd.setSzendserial("0");
            sd.setIoffersschemeid(new Long(0));
            sd.setIamount(tlist.getNumb());
            sd.setIpresentnums(new Long(0));
            sd.setIderatenums(new Long(0));
            sd.setIfactnum(new Long(0));
            sd.setIuseablenessnum(tlist.getNumb());// ʹ������
            sd.setMactualsaleprice(tlist.getPric());// ʵ���ۼ�
            sd.setMeventmoney(tlist.getAmnt());// ʵ�ʷ������
            sd.setMderatemoney(new Double(0));// ������
            sd.setMpresentmoney(new Double(0));// ���ͽ��
            sd.setMnominalfee(new Double(0));// ������
            sd.setMdeposit(new Double(0));
            sd.setMhandcharge(new Double(0));
            sd.setByconsumetype("00");
            sd.setIconsumenum(new Double(0));
            sd.setMtotalamount(tlist.getAmnt());
            sd.setItotalnumber(tlist.getNumb());
            sd.setItotalminutes(new Long(0));
            sd.setByisout(new Long(0));
            sd.setDtmakedate(daytime);
            sd.setIversion(new Long(0));
            detaillist.add(sd);
            Edmtickettypetab eticket = (Edmtickettypetab) this.saleDataTransferDao.get(
                    Edmtickettypetab.class, tlist.getItickettypeid());
            // ����۳���Ʊ��

            for (int j = 1; j <= sd.getIticketnum().intValue(); j++) {
                Stssoldtickettablist stsv = new Stssoldtickettablist();
                StssoldtickettablistId stsvid = new StssoldtickettablistId();
                stsvid.setIsalesvoucherid(sd.getId().getIsalesvoucherid());
                stsvid.setIticketstationid(sd.getId().getIticketstationid());
                stsvid.setIsalesvoucherdetailsid(sd.getId()
                        .getIsalesvoucherdetailsid());
                stsvid.setSzsoldticketid(szsoldticketid);
                szsoldticketid = szsoldticketid + 1;
                stsv.setId(stsvid);
                stsv.setIscenicid(s.getIscenicid());
                stsv.setIcrowdkindid(tlist.getIcrowdkindid());
                stsv.setItickettypeid(sd.getItickettypeid());
                stsv.setUsid(s.getUsid());
                stsv.setIbusinessid(s.getIbusinessid());
                stsv.setDyusid(s.getDyusid());
                stsv.setIplayerperticket(sd.getIplayerperticket());// �˴���
                stsv.setDtstartdate(sd.getDtstartdate());
                stsv.setDtenddate(sd.getDtenddate());
                stsv.setMhandcharge(new Double(0));
                stsv.setDtmakedate(daytime);
                stsv.setByvalidity("00");

                List<Map> iserialnumlist = new ArrayList();

                try {
                    iserialnumlist = this.saleDataTransferDao
                            .findBySqlToMapnocolsesession("select ticketid_sequence.nextval  from dual");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                Long iserialnum = new Long(
                        (((Map) iserialnumlist.get(0)).get("NEXTVAL"))
                                .toString());
                stsv.setIserialnum(iserialnum);
                String newmaxorno = Tools.ConvertTo36Text(iserialnum, 0);
                StringBuffer printno = new StringBuffer();
                printno.append(esbticketstation.getSzstationcode());
                printno.append(scenic.getSzsceniccode());
                printno.append(eticket.getSztickettypecode());
                if (newmaxorno.length() < 6) {
                    for (int b = 0; b < 6 - newmaxorno.length(); b++) {
                        printno.append("0");
                    }
                }
                printno.append(newmaxorno);
                String szprintno = Tools.ticketMakeMd5(printno.toString());
                stsv.setSzticketprintno(szprintno);
                stsv.setMremainmoney(new Double(0));
                stsv.setMpresentmoney(new Double(0));
                stsv.setMactualsaleprice(sd.getMactualsaleprice());
                stsv.setIpresentnum(new Long(0));
                stsv.setIremainnum(new Long(1));// ʣ������
                stsv.setMnominalfee(new Double(0));
                stsv.setMdeposit(new Double(0));
                stsv.setByticketpurpose("00");
                stsv.setBisrefundbalance(new Long(1));
                stsv.setByactivation("02");
                cdetaillist.add(stsv);

            }
        }

        szsoldticketid = new Long(0);
        List zdetaillist = new ArrayList();
        List cdzetaillist = new ArrayList();
        int n = 1;
        long orderlistid = 0;
        long onumb = 0;
        for (int i = 0; i < listzorder.size(); i++) {

            TZorderlist zlist = (TZorderlist) listzorder.get(i);
            Edmtickettypetab edticket = (Edmtickettypetab) this.saleDataTransferDao.get(
                    Edmtickettypetab.class, new Long(zlist.getItickettypeid()));

            Stscomticketsalesdetailstabls zstd = new Stscomticketsalesdetailstabls();
            StscomticketsalesdetailstablsId zstdid = new StscomticketsalesdetailstablsId();
            zstdid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
            zstdid.setIticketstationid(s.getId().getIticketstationid());
            zstdid.setIsalesvoucherdetailsid(zlist.getId().getOrderlistid());
            zstdid.setIcomticketsalesdetailsid(zlist.getId().getZorderlistid());
            zstd.setIcrowdkindpriceid(zlist.getIcrowdkindpriceid());
            zstd.setItickettypeid(zlist.getItickettypeid());
            zstd.setIztickettypeid(zlist.getIztickettypeid());
            zstd.setTripid(zlist.getTripid());
            zstd.setMhandcharge(new Double(0));
            zstd.setDtmakedate(daytime);

            zstd.setIvenueareaid(new Long(0));
            zstd.setIvenueid(new Long(0));
            zstd.setIvenueseatsid(new Long(0));
            if (zlist.getDtstartdate().length() > 10) {
                zstd.setDtstartdate(zlist.getDtstartdate());
                zstd.setDtenddate(zlist.getDtenddate());
            } else {
                zstd.setDtstartdate(zlist.getDtstartdate() + "00:00:00");
                zstd.setDtenddate(zlist.getDtenddate() + "23:59:59");
            }
            zstd.setIversion(new Long(0));
            zstd.setIsplitamount(new Long(zlist.getZnumb()));
            zstd.setMsplitprice(new Double(zlist.getZpric()));
            zstd.setMsplitmoney(new Double(zlist.getZamnt()));
            zstd.setId(zstdid);
            zdetaillist.add(zstd);

            if (orderlistid == 0) {
                orderlistid = zlist.getId().getOrderlistid();
                onumb = zlist.getZnumb().longValue();
            } else {
                if (orderlistid != zlist.getId().getOrderlistid().longValue()) {
                    szsoldticketid = szsoldticketid + onumb;
                    onumb = zlist.getZnumb().longValue();
                    orderlistid = zlist.getId().getOrderlistid();
                }
            }

            List opwwlist = this.saleDataTransferDao
                    .find(" from Opwwicketsettab where itickettypeid="
                            + zlist.getItickettypeid() + " and izticktypeid="
                            + zlist.getIztickettypeid());
            if (opwwlist.size() == 0) {
                throw new RuntimeException("��Ʒ"
                        + edticket.getSztickettypename() + "û�м�Ʊ԰�ţ����ܳ�Ʊ");
            }
            for (int k = 0; k < opwwlist.size(); k++) {

                Opwwicketsettab opww = (Opwwicketsettab) opwwlist.get(k);
                for (int m = 1; m <= zlist.getZnumb().longValue(); m++) {
                    Stssoldticketsubtablist stss = new Stssoldticketsubtablist();
                    StssoldticketsubtablistId stssid = new StssoldticketsubtablistId();
                    stssid.setIticketstationid(s.getId().getIticketstationid());
                    stssid.setSzsoldticketid(szsoldticketid + m);
                    stssid.setSzsoldticketsubid(new Long(n));
                    n = n + 1;
                    stssid.setIsalesvoucherid(s.getId().getIsalesvoucherid());
                    stssid.setIsalesvoucherdetailsid(zlist.getId()
                            .getOrderlistid());
                    stss.setId(stssid);
                    stss.setIgardengateid(opww.getIgardengateid());
                    stss.setIscenicid(s.getIscenicid());
                    stss.setItickettypeid(zlist.getItickettypeid());
                    stss.setIztickettypeid(zlist.getIztickettypeid());
                    stss.setBychecktype(new Long(0));
                    stss.setByconsumemode(opww.getByconsumemode());
                    stss.setIpasstimes(opww.getIlimittotaltimes());
                    stss.setIpassedtimes(opww.getIlimittotaltimes());
                    stss.setMsingletimes(opww.getMsingletimes());
                    stss.setMlimitconsume(new Double(0));
                    stss.setMsingleconsume(new Double(0));
                    stss.setMconsumed(new Double(0));
                    stss.setByisout(new Long(1));
                    stss.setIsvalid(new Long(1));
                    stss.setDtmakedate(daytime);
                    stss.setBylastcheckdir(new Long(0));
                    stss.setIcrowdkindid(new Long(zlist.getIcrowdkindid()));
                    if (zlist.getTripid().longValue() > 0) {
                        if (opww.getIlimittotaltimes() == 1) {
                            stss.setTripid(new Long(zlist.getTripid()));
                            if (zlist.getDtstartdate().length() > 10) {
                                stss.setDtbegindate(zlist.getDtstartdate());
                                stss.setDtenddate(zlist.getDtenddate());
                            }
                        } else {
                            stss.setTripid(new Long(0));
                            stss.setDtbegindate(zlist.getDtstartdate());
                            stss.setDtenddate(zlist.getDtenddate());
                        }
                    } else {
                        stss.setTripid(new Long(0));
                        if (zlist.getDtstartdate().length() > 10) {
                            stss.setDtbegindate(zlist.getDtstartdate());
                            stss.setDtenddate(zlist.getDtenddate());

                        } else {
                            stss.setDtbegindate(zlist.getDtstartdate()
                                    + "00:00:00");
                            stss.setDtenddate(zlist.getDtenddate() + "23:59:59");
                        }
                    }
                    cdzetaillist.add(stss);
                }
            }
        }
        try {
            this.saleDataTransferDao.save(s);
            this.saleDataTransferDao.save(st);

            for (int i = 0; i < detaillist.size(); i++) {
                Stssalesvoucherdetailstablist sd = (Stssalesvoucherdetailstablist) detaillist
                        .get(i);
                this.saleDataTransferDao.save(sd);
            }

            for (int i = 0; i < cdetaillist.size(); i++) {
                Stssoldtickettablist stsv = (Stssoldtickettablist) cdetaillist.get(i);
                this.saleDataTransferDao.save(stsv);

            }
            for (int i = 0; i < zdetaillist.size(); i++) {
                Stscomticketsalesdetailstabls zstd = (Stscomticketsalesdetailstabls) zdetaillist
                        .get(i);
                this.saleDataTransferDao.save(zstd);
            }
            for (int i = 0; i < cdzetaillist.size(); i++) {
                Stssoldticketsubtablist stss = (Stssoldticketsubtablist) cdzetaillist
                        .get(i);
                this.saleDataTransferDao.save(stss);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("ת������");
        }

    }
}
