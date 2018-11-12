package com.ectrip.ec.report.system.datereport.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;

/**
 * Created by cxh on 2016/05/09.
 */
public interface ISaleDataTransferDao extends IGenericDao {

    public List findSaleId(String date);
    public Stssalesvouchertab findStssalesvouchertab(Long isalesvoucherid, Long iticketstationid);
    public List<Stssalesvoucherdetailstab> findStssalesvoucherdetailstab(Long isalesvoucherid, Long iticketstationid);
    public List<Stssoldtickettab> findStssoldtickettab(Long isalesvoucherid, Long iticketstationid);
    public List<Stssoldticketsubtab> findStssoldticketsubtab(Long isalesvoucherid, Long iticketstationid);
    public List<Stssalessettlementtab> findStssalessettlementtab(Long isalesvoucherid, Long iticketstationid);
    public List<Stssoldticketattesttab> findStssoldticketattesttab(Long isalesvoucherid, Long iticketstationid);
    public List<Stscomticketsalesdetailstab> findStscomticketsalesdetailstab(Long isalesvoucherid, Long iticketstationid);
    public void saveStssalesvouchertablist(Stssalesvouchertab stssalesvouchertab);
    public void saveStssalessettlementtablist(Stssalessettlementtab stssalessettlementtab);
    public void saveStssalesvoucherdetailstablist(Stssalesvoucherdetailstab stssalesvoucherdetailstab);
    public void saveStscomticketsalesdetailstabls(Stscomticketsalesdetailstab stscomticketsalesdetailstab);
    public void saveStssoldtickettablist(Stssoldtickettab stssoldtickettab);
    public void saveStssoldticketsubtablist(Stssoldticketsubtab stssoldticketsubtab);
    public List findOrder(String rzti,String ldti,boolean iscyt) throws Exception;
}
