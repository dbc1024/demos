package com.ectrip.ec.report.system.datereport.dao;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.sales.Stscomticketsalesdetailstabls;
import com.ectrip.ec.model.report.sales.Stssalessettlementtablist;
import com.ectrip.ec.model.report.sales.Stssalesvoucherdetailstablist;
import com.ectrip.ec.model.report.sales.Stssalesvouchertablist;
import com.ectrip.ec.model.report.sales.Stssoldticketsubtablist;
import com.ectrip.ec.report.system.datereport.dao.idao.ISaleDataTransferDao;
import com.ectrip.ticket.model.order.Stscomticketsalesdetailstab;
import com.ectrip.ticket.model.order.Stssalessettlementtab;
import com.ectrip.ticket.model.order.Stssalesvoucherdetailstab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.Stssoldtickettablist;

/**
 * Created by cxh on 2016/05/09.
 */
public class SaleDataTransferDao extends GenericDao implements ISaleDataTransferDao{

    public List findSaleId(String date){
        String hsql = "select new map(id.isalesvoucherid as isalesvoucherid,id.iticketstationid as iticketstationid) from " +
                "Stssalesvouchertab where substr(dtmakedate,1,10) < '"+date+"' and id.isalesvoucherid not in " +
                "(select id.isalesvoucherid from Stssoldtickettab where substr(dtenddate,1,10)>='"+ Tools.getDays()+"')";
        List list = this.find(hsql.toString());
        return list;
    }

    public Stssalesvouchertab findStssalesvouchertab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stssalesvouchertab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        if(list != null && !list.isEmpty()){
            return (Stssalesvouchertab) list.get(0);
        }
        return null;
    }

    public List<Stssalesvoucherdetailstab> findStssalesvoucherdetailstab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stssalesvoucherdetailstab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        return list;
    }

    public List<Stssoldtickettab> findStssoldtickettab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stssoldtickettab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        return list;
    }

    public List<Stssoldticketsubtab> findStssoldticketsubtab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stssoldticketsubtab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        return list;
    }

    public List<Stssalessettlementtab> findStssalessettlementtab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stssalessettlementtab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        return list;
    }

    public List<Stssoldticketattesttab> findStssoldticketattesttab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stssoldticketattesttab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        return list;
    }

    public List<Stscomticketsalesdetailstab> findStscomticketsalesdetailstab(Long isalesvoucherid, Long iticketstationid){
        String hsql = "from Stscomticketsalesdetailstab where id.isalesvoucherid="+isalesvoucherid+" and id.iticketstationid="+iticketstationid;
        List list = this.find(hsql);
        return list;
    }

    public void saveStssalesvouchertablist(Stssalesvouchertab stssalesvouchertab){
        String json = JSON.toJSONString(stssalesvouchertab);
        Stssalesvouchertablist stssalesvouchertablist = JSON.parseObject(json, Stssalesvouchertablist.class);
        this.save(stssalesvouchertablist);
    }

    public void saveStssalessettlementtablist(Stssalessettlementtab stssalessettlementtab){
        String json = JSON.toJSONString(stssalessettlementtab);
        Stssalessettlementtablist stssalessettlementtablist = JSON.parseObject(json, Stssalessettlementtablist.class);
        this.save(stssalessettlementtablist);
    }

    public void saveStssalesvoucherdetailstablist(Stssalesvoucherdetailstab stssalesvoucherdetailstab){
        String json = JSON.toJSONString(stssalesvoucherdetailstab);
        Stssalesvoucherdetailstablist stssalesvoucherdetailstablist = JSON.parseObject(json, Stssalesvoucherdetailstablist.class);
        this.save(stssalesvoucherdetailstablist);
    }

    public void saveStscomticketsalesdetailstabls(Stscomticketsalesdetailstab stscomticketsalesdetailstab){
        String json = JSON.toJSONString(stscomticketsalesdetailstab);
        Stscomticketsalesdetailstabls stscomticketsalesdetailstabls = JSON.parseObject(json,Stscomticketsalesdetailstabls.class);
        this.save(stscomticketsalesdetailstabls);
    }

    public void saveStssoldtickettablist(Stssoldtickettab stssoldtickettab){
        String json = JSON.toJSONString(stssoldtickettab);
        Stssoldtickettablist stssoldtickettablist = JSON.parseObject(json, Stssoldtickettablist.class);
        this.save(stssoldtickettablist);
    }

    public void saveStssoldticketsubtablist(Stssoldticketsubtab stssoldticketsubtab){
        String json = JSON.toJSONString(stssoldticketsubtab);
        Stssoldticketsubtablist stssoldticketsubtablist = JSON.parseObject(json, Stssoldticketsubtablist.class);
        this.save(stssoldticketsubtablist);
    }

    public List findOrder(String rzti,String ldti,boolean iscyt) throws Exception {
        String sql = "select CASt(t.orid as varchar(17)) as orid,t.iscenicid as iscenicid from t_order t where t.ddzt = '11' " +
                "and substr(t.notec,1,10) >= ? and substr(t.notec,1,10) <= ? and t.orid not in (" +
                "select s.szsalesvoucherno from stssalesvouchertab s union all select s.szsalesvoucherno from stssalesvouchertablist s) ";
        if(iscyt){
            sql += " and substr(t.orid,9,3) in ('888','999')";
        }
        List list = this.findBySqlToMap(sql,rzti,ldti);
        return list;
    }
}
