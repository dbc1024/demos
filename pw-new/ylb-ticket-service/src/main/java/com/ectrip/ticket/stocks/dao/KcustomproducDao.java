package com.ectrip.ticket.stocks.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.stock.Stocktab;
import com.ectrip.ticket.stocks.dao.idao.IStockDao;
import com.ectrip.ticket.stocks.dao.idao.IkcustomproducDao;

public class KcustomproducDao extends GenericDao implements IkcustomproducDao {
    private IStockDao stockDao;

    public void setStockDao(IStockDao stockDao) {
        this.stockDao = stockDao;
    }

    // 查询服务商
    public List selectFWS(Esfemployeetab esfemployeetab) {
        /*
         * String hsql1 =
         * "select new map(es.iscenicid as iscenicid,es.szscenicname as szscenicname) from "
         * +
         * "Esbscenicareatab es where es.scenictype in ('01','03') and es.isjd=0 "
         * ;
         *
         * // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。 if
         * (esfemployeetab.getCompanytype().equals("02")) { String scenics =
         * esfemployeetab.getScenics(); hsql1 = hsql1 + " and es.iscenicid in ("
         * + scenics + ")"; }
         */

        return this
                .find("select new map(e.iscenicid as iscenicid,e.szscenicname as szscenicname) from Esbscenicareatab e where e.byisuse=1 and e.scenictype='01' order by iscenicid");
    }

    // 查询产品列表
    public List selectCP(Esfemployeetab esfemployeetab) {
        /*
         * String hsql2 =
         * "select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypename as sztickettypename)"
         * + " from Edmtickettypetab ttype ";
         *
         * // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。 if
         * (esfemployeetab.getCompanytype().equals("02")) { String scenics =
         * esfemployeetab.getScenics(); hsql2 = hsql2 +
         * " where ttype.iscenicid in(" + scenics + ")"; }
         */

        return this
                .find("select new map(e.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename) from Edmtickettypetab e where e.byisuse=1 and iscenicid in (select iscenicid from Esbscenicareatab where byisuse=1 and scenictype='01') order by iscenicid,itickettypeid");
    }

    // 判断数据库是否已经有该段时间内库存
    public List SureCustomKC(Stocktab s, QueryOrder order) {
        StringBuffer sb = new StringBuffer();

        sb.append(" from Stocktab where id=" + "'" + s.getId() + "'"
                + " and stocktype=" + "'" + s.getStocktype() + "'"
                + " and ( (startdate<=" + "'" + order.getStartDate() + "'"
                + " and  enddate>=" + "'" + order.getEndDate() + "')");
        sb.append("  or (startdate<=" + "'" + order.getStartDate() + "'"
                + " and enddate>=" + "'" + order.getStartDate() + "')");
        sb.append(" or (startdate>= " + "'" + order.getStartDate() + "'"
                + " and enddate<=" + "'" + order.getEndDate() + "')");
        sb.append(" or (startdate<=" + "'" + order.getEndDate() + "'"
                + " and enddate>=" + "'" + order.getEndDate() + "'))");
        List sList = null;
        try {
            sList = this.find(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sList;
    }

    public List SureCustomKCT(Stocktab s) {// 修改前判断该段日期内是否存在库存
        StringBuffer sb = new StringBuffer();
        Stocktab stocktab = (Stocktab) this.get(Stocktab.class, s.getSeq());
        if (stocktab.getDateType().equals("02")) {
            sb.append(" from Stocktab where id=" + "'" + stocktab.getId() + "'"
                    + " and stocktype=" + "'" + s.getStocktype() + "'"
                    + " and ( (startdate<=" + "'" + s.getStartdate() + "'"
                    + " and  enddate>=" + "'" + s.getEnddate() + "')");
            sb.append("  or (startdate<=" + "'" + s.getStartdate() + "'"
                    + " and enddate>=" + "'" + s.getStartdate() + "')");
            sb.append(" or (startdate>= " + "'" + s.getStartdate() + "'"
                    + " and enddate<=" + "'" + s.getEnddate() + "')");
            sb.append(" or (startdate<=" + "'" + s.getEnddate() + "'"
                    + " and enddate>=" + "'" + s.getEnddate() + "'))");
            sb.append(" and seq!=" + s.getSeq());
            List sList = null;
            sList = this.find(sb.toString());

            return sList;
        } else
            return null;
    }

    // 新增产品库存
    public void addproductKCType(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab stocktab,
                                 String productId, QueryOrder order) {
        Stocktab s = new Stocktab();
        Long seq = getMaxPk("seq", "Stocktab") + 1;

        if (order.getRadiobutton2() == 2) {// 表示日期区间
            s.setSeq(seq);
            s.setStocktype("02");// 系统参数里面的0007代表用户库存
            s.setId(productId);// 服务商id
            s.setStartdate(order.getStartDate());
            s.setEnddate(order.getEndDate());
            s.setStocknumb(stocktab.getStocknumb());
            s.setDtmakedate(Tools.getDayTimes());
            s.setDateType("02");
            s.setIemployeeid(syslog.getIemployeeid());
            this.save(s);
            syslog.setStlg("0328");
            syslog.setBrief(esfemployeetab.getSzemployeename() + "新增产品库存，对象名称为:" + stocktab.getKeyname());
            syslog.setNote("新增库存数量为:" + stocktab.getStocknumb() + "日期为:" + Tools.getDayTimes());
            syslog.setLogdatetime(Tools.getDayTimes());
            Long logid = getMaxPk("logid", "Syslog");
            syslog.setLogid(logid + 1);
            this.save(syslog);
        } else if (order.getRadiobutton2() == 1) {// 表示日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date begin = Date.valueOf(order.getStartDate());
            Date end = Date.valueOf(order.getEndDate());
            double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            double day = between / (24 * 3600);// 日期区间相差多少天
            for (int i = 0; i <= day; i++) {
                Stocktab s2 = new Stocktab();
                Long seq2 = getMaxPk("seq", "Stocktab") + 1;
                Calendar cd = Calendar.getInstance();
                cd.setTime(Date.valueOf(order.getStartDate()));
                cd.add(Calendar.DATE, i);// 增加一天
                String date = sdf.format(cd.getTime());
                s2.setSeq(seq2);
                s2.setStocktype("02");// 系统参数里面的0007代表产品库存
                s2.setId(productId);// 服务商id
                s2.setStartdate(date);
                s2.setEnddate(date);
                s2.setStocknumb(stocktab.getStocknumb());
                s2.setDtmakedate(Tools.getDayTimes());
                s2.setDateType("01");
                s2.setIemployeeid(syslog.getIemployeeid());
                this.save(s2);
                syslog.setStlg("0328");
                syslog.setBrief(esfemployeetab.getSzemployeename() + "新增产品库存,对象名称为:" + stocktab.getKeyname());
                syslog.setNote("新增库存数量为:" + stocktab.getStocknumb() + "日期为:" + Tools.getDayTimes());
                syslog.setLogdatetime(Tools.getDayTimes());
                Long logid = getMaxPk("logid", "Syslog");
                syslog.setLogid(logid + 1);
                this.save(syslog);
            }
        }
    }

    // 新增服务商库存
    public void addfwstKCType(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab stocktab, String fwsId,
                              QueryOrder order) {
        Stocktab s = new Stocktab();
        Long seq = getMaxPk("seq", "Stocktab") + 1;

        if (order.getRadiobutton2() == 2) {// 表示日期区间
            s.setSeq(seq);
            s.setStocktype("01");// 系统参数里面的0007代表用户库存
            s.setId(fwsId);// 服务商id
            s.setStartdate(order.getStartDate());
            s.setEnddate(order.getEndDate());
            s.setStocknumb(stocktab.getStocknumb());
            s.setDtmakedate(Tools.getDayTimes());
            s.setDateType("02");
            s.setIemployeeid(syslog.getIemployeeid());
            this.save(s);

            syslog.setStlg("0328");
            syslog.setBrief(esfemployeetab.getSzemployeename() + "新增服务商库存,对象名称为:" + stocktab.getKeyname() + "日期为:" + Tools.getDayTimes());
            syslog.setNote("新增库存数量为:" + stocktab.getStocknumb());
            syslog.setLogdatetime(Tools.getDayTimes());
            Long logid = getMaxPk("logid", "Syslog");
            syslog.setLogid(logid + 1);
            this.save(syslog);
        } else if (order.getRadiobutton2() == 1) {// 表示日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date begin = Date.valueOf(order.getStartDate());
            Date end = Date.valueOf(order.getEndDate());
            double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            double day = between / (24 * 3600);// 日期区间相差多少天
            for (int i = 0; i <= day; i++) {
                Stocktab s2 = new Stocktab();
                Long seq2 = getMaxPk("seq", "Stocktab") + 1;
                Calendar cd = Calendar.getInstance();
                cd.setTime(Date.valueOf(order.getStartDate()));
                cd.add(Calendar.DATE, i);// 增加一天
                // cd.add(Calendar.MONTH, n);//增加一个月
                String date = sdf.format(cd.getTime());

                System.out.println(date);

                s2.setSeq(seq2);
                s2.setStocktype("01");// 系统参数里面的0007代表产品库存
                s2.setId(fwsId);// 服务商id
                s2.setStartdate(date);
                s2.setEnddate(date);
                s2.setStocknumb(stocktab.getStocknumb());
                s2.setDtmakedate(Tools.getDayTimes());
                s2.setDateType("01");
                s2.setIemployeeid(syslog.getIemployeeid());
                this.save(s2);

                syslog.setStlg("0328");
                syslog.setBrief(esfemployeetab.getSzemployeename() + "新增服务商库存,对象名称为:" + stocktab.getKeyname() + "日期为:" + Tools.getDayTimes());
                syslog.setNote("新增库存数量为:" + stocktab.getStocknumb());
                syslog.setLogdatetime(Tools.getDayTimes());
                Long logid = getMaxPk("logid", "Syslog");
                syslog.setLogid(logid + 1);
                this.save(syslog);
            }
        }
    }

    public PaginationSupport selectProductStockFWS(int pageSize,
                                                   int startInt,// 查询服务商库存
                                                   String url, QueryOrder order, String fwsId, String rzyear,
                                                   String ldyear) {
        StringBuffer sbSQL = new StringBuffer();
        PaginationSupport ps = null;
        Long fwsIds = Long.valueOf(fwsId);
        sbSQL.append("select new map(s.seq as seq,s.stocktype as stocktype,es.szscenicname as szscenicname,s.stocknumb as stocknumb,s.startdate  as startdate,s.enddate as enddate) from Stocktab s,Esbscenicareatab es ");
        sbSQL.append("   where  s.stocktype='01'  and es.iscenicid="
                + fwsIds + " and s.id=" + "'" + fwsIds + "'");
        sbSQL.append(" and ( (s.startdate<=" + "'" + order.getStartDate()
                + "'" + " and  s.enddate>=" + "'" + order.getEndDate()
                + "')");
        sbSQL.append("  or (s.startdate<=" + "'" + order.getStartDate()
                + "'" + " and s.enddate>=" + "'" + order.getStartDate()
                + "')");
        sbSQL.append(" or (s.startdate>= " + "'" + order.getStartDate()
                + "'" + " and s.enddate<=" + "'" + order.getEndDate()
                + "')");
        sbSQL.append(" or (s.startdate<=" + "'" + order.getEndDate() + "'"
                + " and s.enddate>=" + "'" + order.getEndDate() + "'))");
        System.out.println(sbSQL.toString());
        ps = this.findPage(sbSQL.toString(), pageSize, startInt, url);
        if (ps != null && ps.getItems() != null && !ps.getItems().isEmpty()) {
            List list = ps.getItems();
            for (int i = 0; i < list.size(); i++) {
                Map m = (Map) ps.getItems().get(i);
                Long sum = stockDao.sumStockById("01", fwsId,
                        m.get("startdate").toString(), m.get("enddate").toString(),
                        null);
                m.put("sumNum", sum);
            }
        }
        return ps;
    }

    // 查询产品库存列表
    public PaginationSupport selectProductStockCP(int pageSize,
                                                  int startInt, // 查寻产品库存
                                                  String url, QueryOrder order, String productId, String rzyear,
                                                  String ldyear) {
        StringBuffer sbSQL = new StringBuffer();
        PaginationSupport ps = null;
        Long productIds = Long.valueOf(productId);

        sbSQL.append("select new map(s.seq as seq,s.stocktype as stocktype,es.sztickettypename as szscenicname,s.stocknumb as stocknumb,s.startdate  as startdate,s.enddate as enddate) from Stocktab s,Edmtickettypetab es ");
        sbSQL.append("   where   s.stocktype='02'  and es.itickettypeid="
                + productIds + " and s.id=" + "'" + productId + "'");
        sbSQL.append(" and ( (s.startdate<=" + "'" + order.getStartDate()
                + "'" + " and  s.enddate>=" + "'" + order.getEndDate()
                + "')");
        sbSQL.append("  or (s.startdate<=" + "'" + order.getStartDate()
                + "'" + " and s.enddate>=" + "'" + order.getStartDate()
                + "')");
        sbSQL.append(" or (s.startdate>= " + "'" + order.getStartDate()
                + "'" + " and s.enddate<=" + "'" + order.getEndDate()
                + "')");
        sbSQL.append(" or (s.startdate<=" + "'" + order.getEndDate() + "'"
                + " and s.enddate>=" + "'" + order.getEndDate() + "'))");
        ps = this.findPage(sbSQL.toString(), pageSize, startInt, url);
        if (ps != null && ps.getItems()!=null && !ps.getItems().isEmpty()) {
            List list = ps.getItems();
            for (int i = 0; i < list.size(); i++) {
                Map m = (Map) list.get(i);
                Long sum = stockDao.sumStockById("02", productId,
                        m.get("startdate").toString(), m.get("enddate").toString(),
                        null);
                m.put("sumNum", sum);
            }
        }
        return ps;

    }

    public Stocktab editKcuprodStock(Stocktab s) {// 修改/删除/查看库存之前查询改条库存各种信息
        String sql = null;
        Stocktab stocktab = (Stocktab) this.get(Stocktab.class, s.getSeq());
        return stocktab;
    }

    public String selectfwsOrpro(Stocktab s) {// 修改库存之前查询服务商名称或者产品名称
        String sql = null;
        Stocktab stocktab = (Stocktab) this.get(Stocktab.class, s.getSeq());
        if (stocktab.getStocktype().equals("01")) {// 服务商库存
            sql = " from Esbscenicareatab es where es.iscenicid=" + s.getId();
            Esbscenicareatab esb = (Esbscenicareatab) this.find(sql).get(0);
            return esb.getSzscenicname();
        } else if (stocktab.getStocktype().equals("02")) {
            sql = " from Edmtickettypetab es where es.itickettypeid="
                    + s.getId();
            Edmtickettypetab eb = (Edmtickettypetab) this.find(sql).get(0);
            return eb.getSztickettypename();
        }
        return null;
    }

    public String selectfwsOrproT(Stocktab s) {// 查询服务商名称或者产品名称
        String sql = null;

        if (s.getStocktype().equals("01")) {// 服务商库存
            sql = " from Esbscenicareatab es where es.iscenicid=" + s.getId();
            Esbscenicareatab esb = (Esbscenicareatab) this.find(sql).get(0);
            return esb.getSzscenicname();
        } else if (s.getStocktype().equals("02")) {
            sql = " from Edmtickettypetab es where es.itickettypeid="
                    + s.getId();
            Edmtickettypetab eb = (Edmtickettypetab) this.find(sql).get(0);
            return eb.getSztickettypename();
        }
        return null;
    }

    public void updateKCStock(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab s) {// 修改库存
        Stocktab stocktab = (Stocktab) this.get(Stocktab.class, s.getSeq());
        syslog.setStlg("0329");
        if (s.getStocktype().equals("01")) {
            String sql = " from Esbscenicareatab es where es.iscenicid=" + stocktab.getId();
            Esbscenicareatab esb = (Esbscenicareatab) this.find(sql).get(0);
            syslog.setBrief(esfemployeetab.getSzemployeename() + "修改服务商库存信息,对象名称为" + esb.getSzscenicname());
        } else if (s.getStocktype().equals("02")) {

            String sql = " from Edmtickettypetab es where es.itickettypeid=" + stocktab.getId();
            Edmtickettypetab eb = (Edmtickettypetab) this.find(sql).get(0);
            syslog.setBrief(esfemployeetab.getSzemployeename() + "修改产品库存信息,对象名称为" + eb.getSztickettypename());
        }


        syslog.setNote("将原数量" + stocktab.getStocknumb() + "修改为" + s.getStocknumb() + "日期为:" + Tools.getDayTimes());
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);

        stocktab.setStocknumb(s.getStocknumb());
        stocktab.setStartdate(s.getStartdate());
        stocktab.setEnddate(s.getEnddate());
        this.update(stocktab);
        this.save(syslog);

    }

    public void delKCproductstock(Esfemployeetab esfemployeetab, Syslog syslog, Stocktab s) {// 删除库存
        Stocktab stocktab = (Stocktab) this.get(Stocktab.class, s.getSeq());
        this.delete(stocktab);

        syslog.setStlg("0330");
        if (stocktab.getStocktype().equals("01")) {
            String sql = " from Esbscenicareatab es where es.iscenicid=" + stocktab.getId();
            Esbscenicareatab esb = (Esbscenicareatab) this.find(sql).get(0);
            syslog.setBrief(esfemployeetab.getSzemployeename() + "删除服务商库存信息,对象名称为" + esb.getSzscenicname());
        } else if (stocktab.getStocktype().equals("02")) {
            String sql = " from Edmtickettypetab es where es.itickettypeid=" + stocktab.getId();
            Edmtickettypetab eb = (Edmtickettypetab) this.find(sql).get(0);
            syslog.setBrief(esfemployeetab.getSzemployeename() + "删除产品库存信息,对象，名称为" + eb.getSztickettypename());
        }

        syslog.setNote("删除库存数量:" + stocktab.getStocknumb() + "日期为" + Tools.getDayTimes());
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);
    }

}
