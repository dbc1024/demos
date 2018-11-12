package com.ectrip.sys.syspar.dao.impl;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.syspar.dao.IReceiptDAO;
import com.ectrip.ticket.model.provider.Esbscenicareatab;


public class ReceiptDAO extends GenericDao implements IReceiptDAO {
	/**
	 * 在线订单收据打印 应移动到电商模块
	 */
    public String getordermessage(String orid, String iscenicid, int type) {/*

        StringBuffer returnstr = new StringBuffer();
        MOrder m = (MOrder) this.get(MOrder.class, orid);
        TOrder t = (TOrder) this.get(TOrder.class, new TOrderId(orid, new Long(
                iscenicid)));
        YOrder y = (YOrder) this.get(YOrder.class, new YOrderId(orid, new Long(
                iscenicid)));
        returnstr.append("电子商务在线订单");
        if (type == 1) {
            returnstr.append("(重打)");
        }
        returnstr.append(",");
        Custom c = (Custom) get(Custom.class, m.getUsid());
        if (c!=null&&c.getLgtp().equals("02")) {
            returnstr.append("单位名称:" + c.getCorpname() + ",");
            returnstr.append("客户类型:团队,");
            returnstr.append("客户编号:" + c.getUsid() + ",");
            returnstr.append("取票用户:" + t.getOrnm() + ",");
            returnstr.append("证件号码:" + t.getOrhm() + ",");
        } else {
            returnstr.append("客户类型:散客,");
            returnstr.append("客户编号:" + c.getUsid());
            returnstr.append("取票用户:" + t.getOrnm() + ",");
            returnstr.append("证件号码:" + t.getOrhm() + ",");
        }

        returnstr.append("订单号码:" + t.getId().getOrid() + ",");
        if (t.getOrfl().equals("01")) {
            returnstr.append("游览日期:" + t.getStdt() + ",");
        } else if ((t.getOrfl().equals("06"))) {
            returnstr.append("入住日期:" + t.getStdt() + ",");
        }
        if(t.getIregionalid()!=null&&t.getIregionalid()>0){
            Galsourceregiontab g=(Galsourceregiontab)get(Galsourceregiontab.class,t.getIregionalid());
            if(g.getSzinnername()!=null&&!g.getSzinnername().trim().equals("")){
                returnstr.append("客源地:" + g.getSzinnername().replaceAll("[,]", "_") + ",");
            }else{
                returnstr.append("客源地:" + (g.getSzregionalname()==null?"":g.getSzregionalname().replaceAll("[,]", "_")) + ",");
            }
        }
        Sysparv5 v5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id(
                "ZFFS", m.getZffs()));
        returnstr.append("支付方式:" + v5.getPmva() + ",");
        String constandsql = "from Sysparv5 where id.pmky='RECE' and spmcd='****' and pmvc='1' order by  to_number(id.pmcd)";
        List constants = find(constandsql);
        Sysparv5 constant = null;
        constant = (Sysparv5) constants.get(1);
        returnstr.append(constant.getPmvb() + ",");
        constant = (Sysparv5) constants.get(2);
        returnstr.append(constant.getPmvb() + ",");
        constant = (Sysparv5) constants.get(3);
        returnstr.append(constant.getPmvb() + ",&");
        List tList = this.find(" from TOrderlist where id.orid='" + orid
                + "' and id.iscenicid=" + iscenicid);
        for (int i = 0; i < tList.size(); i++) {
            TOrderlist l = (TOrderlist) tList.get(i);
            Edmtickettypetab e = (Edmtickettypetab) get(Edmtickettypetab.class,
                    l.getItickettypeid());
            Edpcrowdkindtab ek = (Edpcrowdkindtab) get(Edpcrowdkindtab.class,
                    l.getIcrowdkindid());
            returnstr.append(e.getSztickettypename() + "("
                    + ek.getSzcrowdkindname() + "),");
            returnstr.append(l.getPric() + "×" + l.getNumb() + ",");
            returnstr.append(l.getAmnt() - l.getYhamnt());
            if (l.getYhnumb() > 0) {
                returnstr.append("(免" + l.getYhnumb() + ")");
            }
            returnstr.append(",");
        }
        Stssalesvouchertab sts = (Stssalesvouchertab) this.find(
                " from Stssalesvouchertab where szsalesvoucherno='" + orid
                        + "' and iscenicid=" + iscenicid).get(0);
        double tkmont=0;
        List cptdlist=this.find(" from Stssalesvouchertab where isticketstationid="+sts.getId().getIticketstationid()+" and issalesvoucherid="+sts.getId().getIsalesvoucherid());

        for(int i=0;i<cptdlist.size();i++){
            Stssalesvouchertab tdsts=(Stssalesvouchertab)cptdlist.get(i);
            StringBuffer backsql = new StringBuffer(
                    "select new map(e.sztickettypename as sztickettypename,c.szcrowdkindname as szcrowdkindname,s.iticketnum as iticketnum,s.iplayerperticket as iplayerperticket,s.iuseablenessnum as iuseablenessnum,s.iamount as iamount,s.meventmoney as meventmoney,s.mderatemoney as mderatemoney,s.ideratenums as ideratenums,s.mactualsaleprice as mactualsaleprice) from Stssalesvoucherdetailstab s,Edmtickettypetab e,Edmcrowdkindpricetab p,Edpcrowdkindtab c where s.id.isalesvoucherid="+tdsts.getId().getIsalesvoucherid()+" and  s.id.iticketstationid="
                            + tdsts.getId().getIticketstationid()
                            + " and s.icrowdkindpriceid=p.icrowdkindpriceid and s.itickettypeid=e.itickettypeid and c.icrowdkindid=p.icrowdkindid");
            List backlist = find(backsql.toString());
            if (backlist != null && backlist.size() > 0) {
                for (int j = 0; j < backlist.size(); j++) {
                    Map detailmap = (Map) backlist.get(j);
                    returnstr.append(detailmap.get("sztickettypename") + "("
                            + detailmap.get("szcrowdkindname")
                            + "),");
                    returnstr.append(Double.parseDouble(detailmap.get(
                            "mactualsaleprice").toString())
                            + "×"
                            + Integer.parseInt(detailmap.get("iamount")
                            .toString()) * -1 + ",");
                    returnstr.append(-1
                            * (Double.parseDouble(detailmap.get(
                            "meventmoney").toString()) - Double
                            .parseDouble(detailmap.get(
                                    "mderatemoney").toString())));
                    tkmont = tkmont
                            + (Double.parseDouble(detailmap.get(
                            "meventmoney").toString()) - Double
                            .parseDouble(detailmap.get(
                                    "mderatemoney").toString()));
                    int ideratenums = Integer.parseInt(detailmap.get(
                            "ideratenums").toString())
                            * -1;
                    if (ideratenums > 0) {
                        returnstr.append("(免" + ideratenums + ")");
                    }

                    returnstr.append(",");

                }
            }
        }
        returnstr.delete(returnstr.length() - 1, returnstr.length());
        returnstr.append("&,");
        returnstr.append("合计:" + (t.getMont() - t.getYhamnt()-tkmont));
        if (y.getTpsx() != null && y.getTpsx() > 0) {
            returnstr.append("手续费:" + y.getTpsx());
        }
        returnstr.append(",");
        constant = (Sysparv5) constants.get(4);
        Esfemployeetab em = (Esfemployeetab) get(Esfemployeetab.class,
                t.getIsc());
        returnstr.append(constant.getPmvb() + ":" + em.getSzemployeename()
                + ",");
        constant = (Sysparv5) constants.get(5);

        Esbticketstationtab est = (Esbticketstationtab) get(
                Esbticketstationtab.class, sts.getId().getIticketstationid());

        returnstr.append(constant.getPmvb() + ":" + est.getSzstationname()
                + ",");
        constant = (Sysparv5) constants.get(6);
        returnstr.append(constant.getPmvb() + ":" + Tools.getDayTimes() + ",");
        Esbscenicareatab provider = (Esbscenicareatab) get(
                Esbscenicareatab.class, Long.parseLong(iscenicid));
        constant = (Sysparv5) constants.get(7);
        returnstr.append(constant.getPmvb() + ":,");
        constant = (Sysparv5) constants.get(8);
        returnstr.append(constant.getPmvb() + ",");
        constant = (Sysparv5) constants.get(9);
        returnstr.append(constant.getPmvb() + ":" + provider.getSznetaddr()
                + ",");
        constant = (Sysparv5) constants.get(10);
        returnstr.append(constant.getPmvb() + ",");
        constant = (Sysparv5) constants.get(11);
        returnstr.append(constant.getPmvb() + "");
        return returnstr.toString();
    */
    	return null;
    }

    public String getSalesVoucher(String isalevoucherid, String iscenicid,
                                  int type) {
        String sysparsql = "from Sysparv5 where id.pmky='RECE' and spmcd='****' and pmvc='0' ";
        String constandsql = "from Sysparv5 where id.pmky='RECE' and spmcd='****' and pmvc='1' order by  to_number(id.pmcd)";
        StringBuffer returnstr = new StringBuffer();
        List list = find(sysparsql);
        String forcedrefund="";
        StringBuffer sql = new StringBuffer(
                "select new map(s.id.isalesvoucherid as isalesvoucherid,s.bysalesvouchertype as bysalesvouchertype,s.forcedrefund as forcedrefund,");
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Sysparv5 bl = (Sysparv5) list.get(i);
                sql.append(bl.getPmva() + " as " + bl.getPmvd());
                sql.append(",");
            }
            sql.append(" s.id.iticketstationid as iticketstationid,nvl(s.mhandcharge,0) as mhandcharge) from Stssalesvouchertab s,Custom c,Esfemployeetab employee,Esbticketstationtab st where s.iscenicid="
                    + iscenicid
                    + " and (s.id.isalesvoucherid="
                    + isalevoucherid
                    + " or s.szsalesvoucherno='"
                    + isalevoucherid
                    + "') and c.usid=s.usid and employee.iemployeeid=s.ipayeer and st.iticketstationid=s.id.iticketstationid");
            List constants = find(constandsql);
            Sysparv5 constant = null;
            List salelist = find(sql.toString());
            Map salemap = null;
            if (salelist != null && salelist.size() > 0) {
                salemap = (Map) salelist.get(0);
                if (type == 1) {
                    returnstr.append("重打");
                }else if(type==2)//退票
                {
                    returnstr.append("");
                    forcedrefund=salemap.get("forcedrefund").toString();
                }
                Sysparv5 pzlb;
                try {
                    pzlb = (Sysparv5) get(Sysparv5.class, new Sysparv5Id(
                            "PZLB", salemap.get("bysalesvouchertype")
                            .toString()));
                    if (pzlb != null) {
                        returnstr.append(pzlb.getPmva());
                    }
                } catch (Exception e1) {
                    pzlb = new Sysparv5();
                }
                constant = (Sysparv5) constants.get(0);
                returnstr.append(constant.getPmvb() + ",");
                for (int i = 0; i < list.size(); i++) {
                    Sysparv5 sys = (Sysparv5) list.get(i);
                    if (sys.getPmva().equals("c.lgtp")) {
                        if (salemap.get("lgtp").toString().equals("01")) {
                            returnstr.append("散客");
                        } else if (salemap.get("lgtp").toString().equals("02")) {
                            returnstr.append("团队");
                        }
                    } else {
                        /*if (salemap.get(sys.getPmvd()) == null) {
                            returnstr.append(sys.getPmvb() + ":,");
                        } else {
                            returnstr.append(sys.getPmvb() + ":"
                                    + salemap.get(sys.getPmvd()) + ",");
                        }*/
                        if (salemap.get(sys.getPmvd()) == null) {
                            if(null!=sys && null!=sys.getPmvb() && !sys.getPmvb().equals("单位名称"))//lizhaodong 新增 去掉售票员字段显示
                            {
                                returnstr.append(sys.getPmvb() + ":,");
                            }
                        } else {
                            if(null!=sys && null!=sys.getPmvb() && !sys.getPmvb().equals("单位名称"))//lizhaodong 新增 去掉售票员字段显示
                            {
                                returnstr.append(sys.getPmvb() + ":"
                                        + salemap.get(sys.getPmvd()) + ",");
                            }
                        }
                    }
                }
                if (pzlb.getPmva().equals("02")) {
                    try {
                        int mhandcharge = Integer.parseInt(salemap.get(
                                "mhandcharge").toString());
                        if (mhandcharge > 0) {
                            returnstr.append("手续费:"
                                    + salemap.get("mhandcharge"));
                        }
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                constant = (Sysparv5) constants.get(1);
                returnstr.append(constant.getPmvb() + ",");
                constant = (Sysparv5) constants.get(2);
                returnstr.append(constant.getPmvb() + ",");
                constant = (Sysparv5) constants.get(3);
                returnstr.append(constant.getPmvb() + ",&");
            } else {
                return "";
            }
            double smont = 0;
            sql.delete(0, sql.length());
            sql.append("select new map(e.sztickettypename as sztickettypename,c.szcrowdkindname as szcrowdkindname,s.iticketnum as iticketnum,s.iplayerperticket as iplayerperticket,s.iuseablenessnum as iuseablenessnum,s.iamount as iamount,s.meventmoney as meventmoney,s.mderatemoney as mderatemoney,s.ideratenums as ideratenums,s.mactualsaleprice as mactualsaleprice) from Stssalesvoucherdetailstab s,Edmtickettypetab e,Edmcrowdkindpricetab p,Edpcrowdkindtab c, Stssalesvouchertab sc where (s.id.isalesvoucherid="
                    + salemap.get("isalesvoucherid").toString()
                    + " or sc.szsalesvoucherno='"+salemap.get("isalesvoucherid").toString()+"') and sc.id.isalesvoucherid = s.id.isalesvoucherid and sc.id.iticketstationid = s.id.iticketstationid and s.id.iticketstationid="
                    + salemap.get("iticketstationid")
                    + " and s.icrowdkindpriceid=p.icrowdkindpriceid and s.itickettypeid=e.itickettypeid and c.icrowdkindid=p.icrowdkindid");
            List details = find(sql.toString());
            System.out.println("============.>>>detailssql:" + sql);
            for (int i = 0; i < details.size(); i++) {
                Map detailmap = (Map) details.get(i);
                returnstr.append(detailmap.get("sztickettypename") + "("
                        + detailmap.get("szcrowdkindname") + "),");
                returnstr.append(Double.parseDouble(detailmap.get(
                        "mactualsaleprice").toString())
                        + "×" + detailmap.get("iamount").toString() + ",");
                returnstr.append(Double.parseDouble(detailmap
                        .get("meventmoney").toString())
                        - Double.parseDouble(detailmap.get("mderatemoney")
                        .toString()));
                smont = smont
                        + Double.parseDouble(detailmap.get("meventmoney")
                        .toString())
                        - Double.parseDouble(detailmap.get("mderatemoney")
                        .toString());
                int ideratenums = Integer.parseInt(detailmap.get("ideratenums")
                        .toString());
                if (ideratenums > 0) {
                    returnstr.append("(免" + ideratenums + ")");
                }
                returnstr.append(",");
            }

            StringBuffer saleid = new StringBuffer();
            List backsales = find("select new map(id.isalesvoucherid as isalesvoucherid) from Stssalesvouchertab where issalesvoucherid="
                    + salemap.get("isalesvoucherid")
                    + " and isticketstationid="
                    + salemap.get("iticketstationid") + "");
            System.out
                    .println("===>>.bakids:"
                            + "select new map(id.isalesvoucherid as isalesvoucherid) from Stssalesvouchertab where issalesvoucherid="
                            + salemap.get("isalesvoucherid")
                            + " and isticketstationid="
                            + salemap.get("iticketstationid") + "");

            if (backsales != null && backsales.size() > 0) {
                for (int x = 0; x < backsales.size(); x++) {
                    Map map = (Map) backsales.get(x);
                    saleid.append(map.get("isalesvoucherid").toString());
                    if (x != backsales.size() - 1) {
                        saleid.append(",");
                    }
                }
                StringBuffer backsql = new StringBuffer(
                        "select new map(e.sztickettypename as sztickettypename,c.szcrowdkindname as szcrowdkindname,s.iticketnum as iticketnum,s.iplayerperticket as iplayerperticket,s.iuseablenessnum as iuseablenessnum,s.iamount as iamount,s.meventmoney as meventmoney,s.mderatemoney as mderatemoney,s.ideratenums as ideratenums,s.mactualsaleprice as mactualsaleprice) from Stssalesvoucherdetailstab s,Edmtickettypetab e,Edmcrowdkindpricetab p,Edpcrowdkindtab c where s.id.isalesvoucherid in("
                                + saleid
                                + ") and s.id.iticketstationid="
                                + salemap.get("iticketstationid")
                                + " and s.icrowdkindpriceid=p.icrowdkindpriceid and s.itickettypeid=e.itickettypeid and c.icrowdkindid=p.icrowdkindid");
                System.out.println("backsql:" + backsql);
                List backlist = find(backsql.toString());
                if (backlist != null && backlist.size() > 0) {
                    for (int i = 0; i < backlist.size(); i++) {
                        Map detailmap = (Map) backlist.get(i);
                        returnstr
                                .append(detailmap.get("sztickettypename") + "("
                                        + detailmap.get("szcrowdkindname")
                                        + "),");
                        returnstr.append(Double.parseDouble(detailmap.get(
                                "mactualsaleprice").toString())
                                + "×"
                                + Integer.parseInt(detailmap.get("iamount")
                                .toString()) * -1 + ",");
                        returnstr.append(-1
                                * (Double.parseDouble(detailmap.get(
                                "meventmoney").toString()) - Double
                                .parseDouble(detailmap.get(
                                        "mderatemoney").toString())));
                        smont = smont
                                + -1
                                * (Double.parseDouble(detailmap.get(
                                "meventmoney").toString()) - Double
                                .parseDouble(detailmap.get(
                                        "mderatemoney").toString()));
                        int ideratenums = Integer.parseInt(detailmap.get(
                                "ideratenums").toString())
                                * -1;
                        if (ideratenums > 0) {
                            returnstr.append("(免" + ideratenums + ")");
                        }
                        if (i == backlist.size() - 1) {
                            returnstr.append("&,");
                        } else {
                            returnstr.append(",");
                        }
                    }
                }
            } else {
                returnstr.delete(returnstr.length() - 1, returnstr.length());
                returnstr.append("&,");
            }

            returnstr.append("合计:" + smont);
            returnstr.append(",");
            constant = (Sysparv5) constants.get(4);
            returnstr.append(constant.getPmvb() + ":" + salemap.get("empid")
                    + ",");
            constant = (Sysparv5) constants.get(5);
            returnstr.append(constant.getPmvb() + ":"
                    + salemap.get("szstationname") + ",");
            constant = (Sysparv5) constants.get(6);
            returnstr.append(constant.getPmvb() + ":" + Tools.getDayTimes()
                    + ",");
            Esbscenicareatab provider = (Esbscenicareatab) get(
                    Esbscenicareatab.class, Long.parseLong(iscenicid));
            constant = (Sysparv5) constants.get(7);
            returnstr.append(constant.getPmvb() + ":,");
           /* constant = (Sysparv5) constants.get(8);//暂时去掉了欢迎使用***系统提示
            returnstr.append(constant.getPmvb() + ",");
            constant = (Sysparv5) constants.get(9);////网址
            returnstr.append(constant.getPmvb() + ":" + provider.getSznetaddr()
                    + ",");
            constant = (Sysparv5) constants.get(10);//备注
            returnstr.append(constant.getPmvb() + ",");
            constant = (Sysparv5) constants.get(11);//温馨提示
            returnstr.append(constant.getPmvb() + "");
            */
            if(type==2)//退票
            {
                returnstr.append("退票原因:"+forcedrefund);
            }
        }
        return returnstr.toString();
    }

}
