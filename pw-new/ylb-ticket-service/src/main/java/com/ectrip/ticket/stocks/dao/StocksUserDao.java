
package com.ectrip.ticket.stocks.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.stock.Stocktab;
import com.ectrip.ticket.stocks.dao.idao.IStocksUserDao;

public class StocksUserDao extends GenericDao implements IStocksUserDao {
    /**
     * 首页
     */
    public PaginationSupport initInfo(int pageSize, int startInt, String url) {
        StringBuffer sbSQL = new StringBuffer();
        PaginationSupport ps = null;
        sbSQL.append("select new map(s.seq as seq,s.customStockType as customStockType,s.customStockId as customStockId,s.id as usid,s.stocknumb as stocknumb,s.startdate  as startdate,s.enddate as enddate) from Stocktab s "
                + " where s.stocktype='04'");
        ps = this.findPage(sbSQL.toString(), pageSize, startInt, url);
        List list = ps.getItems();

        System.out.println("sql:" + sbSQL);
        System.out.println("size:" + list.size());
        if (list != null && list.size() > 0) {
            Map map = null;
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                String customStockType = (String) map.get("customStockType");
                Long customStockId = Long.parseLong(map.get("customStockId").toString());
                if (customStockType != null && "01".equals(customStockType)) {
                    Esbscenicareatab esbscenic = (Esbscenicareatab) this.get(
                            Esbscenicareatab.class, customStockId);

                    if (esbscenic != null) {
                        map.put("szproductname", esbscenic.getSzscenicname());
                    } else {
                        map.put("szproductname", "无效服务商");
                    }
                } else if ("02".equals(customStockType)) {

                    Edmtickettypetab edmticket = (Edmtickettypetab) this.get(
                            Edmtickettypetab.class, customStockId);
                    if (edmticket != null) {
                        map.put("szproductname",
                                edmticket.getSztickettypename());
                    } else {
                        map.put("szproductname", "无效产品");
                    }

                    // System.out.println("Edmtickettypetab="+edmticket);

                }
                String usid = map.get("usid").toString();
                Custom custom = (Custom) this.get(Custom.class, usid);

                map.put("customname", custom.getCorpname());
            }

        }
        return ps;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List selectUser() {
        String hsql3 = "select new map(c.usid as usid,c.corpname as corpname) from Custom c where c.lgtp='02' and c.ttlb='01' and c.ustp='01' and c.status='01' ";
        return this.find(hsql3);
    }

    public List selectUserStockForF() {
        String sql = "select new map(e.iscenicid as iscenicid,e.szscenicname as szscenicname) from Esbscenicareatab e where e.byisuse=1 and e.scenictype='01' order by iscenicid";
        return this.find(sql);
    }

    // 增加用户库存
    public void addUserStock(Syslog syslog, Stocktab stocktab) {
        Stocktab s = new Stocktab();
        Long seq = getMaxPk("seq", "Stocktab") + 1;
        System.out.println("true or false:///"
                + "02".equals(stocktab.getDateType()));
        if ("02".equals(stocktab.getDateType())) {// 表示区间

            s.setSeq(seq);
            s.setStocktype("04");// 系统参数里面的0007代表用户库存
            s.setId(stocktab.getId());
            s.setStartdate(stocktab.getStartdate());
            s.setEnddate(stocktab.getEnddate());
            s.setStocknumb(stocktab.getStocknumb());
            s.setDtmakedate(Tools.getDayTimes());
            s.setDateType("02");
            s.setIemployeeid(syslog.getIemployeeid());
            s.setCustomStockType(stocktab.getCustomStockType());
            s.setCustomStockId(stocktab.getCustomStockId());
            this.save(s);
            String type = null;
            String name = null;

            if (stocktab.getCustomStockType() != null && "01".equals(stocktab.getCustomStockType())) {
                type = "服务商";
                Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this
                        .get(Esbscenicareatab.class, Long.parseLong(stocktab.getCustomStockId()));
                name = esbscenicareatab.getSzscenicname();
            } else {
                type = "产品";
                Edmtickettypetab edmticket = (Edmtickettypetab) this
                        .get(Edmtickettypetab.class, Long.parseLong(stocktab.getCustomStockId()));
                name = edmticket.getSztickettypename();
            }
            Custom custom = (Custom) this.get(Custom.class, stocktab.getId());
            syslog.setStlg("0328");

            syslog.setBrief("增加用户库存信息");
            syslog.setNote("操作人:" + syslog.getSzemployeename() + "," + "增加的类型:" + type + "," + "服务商/用户名:" + name + "," + "用户名:" + custom.getCorpname() + "," + "增加数量:" + stocktab.getStocknumb()
                    + "," + "起始日期:" + stocktab.getStartdate() + "," + "结束日期:" + stocktab.getEnddate());

            syslog.setLogdatetime(Tools.getDayTimes());
            Long logid = getMaxPk("logid", "Syslog");
            syslog.setLogid(logid + 1);
            this.save(syslog);
        } else if ("01".equals(stocktab.getDateType())) {// 表示日期
            System.out.println("jinlai///日期类型");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date begin = Date.valueOf(stocktab.getStartdate());
            Date end = Date.valueOf(stocktab.getEnddate());
            double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
            double day = between / (24 * 3600);// 日期区间相差多少天
            for (int i = 0; i <= day; i++) {
                Stocktab s2 = new Stocktab();
                Long seq2 = null;
                try {
                    seq2 = getMaxPk("seq", "Stocktab") + 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Calendar cd = Calendar.getInstance();
                cd.setTime(Date.valueOf(stocktab.getStartdate()));
                cd.add(Calendar.DATE, i);// 增加一天
                String date = sdf.format(cd.getTime());
                s2.setSeq(seq2);
                s2.setStocktype("04");// 系统参数里面的0007代表用户库存
                s2.setId(stocktab.getId());//
                s2.setStartdate(date);
                s2.setEnddate(date);
                s2.setStocknumb(stocktab.getStocknumb());
                s2.setDtmakedate(Tools.getDayTimes());
                s2.setDateType("01");
                s2.setIemployeeid(syslog.getIemployeeid());
                s2.setCustomStockType(stocktab.getCustomStockType());
                s2.setCustomStockId(stocktab.getCustomStockId());
                try {
                    this.save(s2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String type = null;
                String name = null;

                if (stocktab.getCustomStockType() != null && "01".equals(stocktab.getCustomStockType())) {
                    type = "服务商";
                    Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this
                            .get(Esbscenicareatab.class, Long.parseLong(stocktab.getCustomStockId()));
                    name = esbscenicareatab.getSzscenicname();
                } else {
                    type = "产品";
                    Edmtickettypetab edmticket = (Edmtickettypetab) this
                            .get(Edmtickettypetab.class, Long.parseLong(stocktab.getCustomStockId()));
                    name = edmticket.getSztickettypename();
                }
                Custom custom = (Custom) this.get(Custom.class, stocktab.getId());
                syslog.setStlg("0328");

                syslog.setBrief("增加用户库存信息");
                syslog.setNote("操作人:" + syslog.getSzemployeename() + "," + "增加的类型:" + type + "," + "服务商/用户名:" + name + "," + "用户名:" + custom.getCorpname() + ","
                        + "增加数量:" + stocktab.getStocknumb() + "," + "起始日期:" + stocktab.getStartdate() + "," + "结束日期:" + stocktab.getEnddate());

                syslog.setLogdatetime(Tools.getDayTimes());
                Long logid = getMaxPk("logid", "Syslog");
                syslog.setLogid(logid + 1);
                this.save(syslog);
            }
        }

    }

    /**
     * 根据主键seq查询单个用户库存
     */
    public Stocktab selectUserBySeq(Long seq) {
        Stocktab stocktab = (Stocktab) this.get(Stocktab.class, seq);
        return stocktab;
    }

    /**
     * 根据输入的条件查询用户库存
     */
    public PaginationSupport selectUserStock(Stocktab stocktab, int pageSize,
                                             int page, String url) {
        StringBuffer sbSQL = new StringBuffer();
        PaginationSupport ps = null;
        if ("".equals(stocktab.getId()) || stocktab.getId() == null) {
            //如果没有选用户条件
            sbSQL.append("select new map(s.seq as seq,s.customStockType as customStockType,s.customStockId as customStockId,s.id as usid,s.stocknumb as stocknumb,s.startdate  as startdate,s.enddate as enddate) from Stocktab s "
                    + " where s.stocktype='04' and s.customStockType="
                    + "'"
                    + stocktab.getCustomStockType()
                    + "'"
                    + " and s.customStockId="
                    + "'"
                    + stocktab.getCustomStockId()
                    + "'");
        } else {
            sbSQL.append("select new map(s.seq as seq,s.customStockType as customStockType,s.customStockId as customStockId,s.id as usid,s.stocknumb as stocknumb,s.startdate  as startdate,s.enddate as enddate) from Stocktab s "
                    + " where s.stocktype='04' and s.customStockType="
                    + "'"
                    + stocktab.getCustomStockType()
                    + "'"
                    + " and s.customStockId="
                    + "'"
                    + stocktab.getCustomStockId()
                    + "'"
                    + " and s.id="
                    + "'"
                    + stocktab.getId()
                    + "'");
        }
        sbSQL.append(" and ( (s.startdate<=" + "'"
                + stocktab.getStartdate() + "'" + " and  s.enddate>=" + "'"
                + stocktab.getEnddate() + "')");
        sbSQL.append("  or (s.startdate<=" + "'" + stocktab.getStartdate()
                + "'" + " and s.enddate>=" + "'" + stocktab.getStartdate()
                + "')");
        sbSQL.append(" or (s.startdate>= " + "'" + stocktab.getStartdate()
                + "'" + " and s.enddate<=" + "'" + stocktab.getEnddate()
                + "')");
        sbSQL.append(" or (startdate<=" + "'" + stocktab.getEnddate() + "'"
                + " and s.enddate>=" + "'" + stocktab.getEnddate() + "'))");
        ps = this.findPage(sbSQL.toString(), pageSize, page, url);
        List list = ps.getItems();

        System.out.println("sql:" + sbSQL);
        System.out.println("size:" + list.size());
        if (list != null && list.size() > 0) {
            Map map = null;
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                String customStockType = (String) map.get("customStockType");
                Long customStockId = Long.parseLong(map.get("customStockId")
                        .toString());
                if (customStockType != null && "01".equals(customStockType)) {
                    Esbscenicareatab esbscenic = (Esbscenicareatab) this.get(
                            Esbscenicareatab.class, customStockId);

                    if (esbscenic != null) {
                        map.put("szproductname", esbscenic.getSzscenicname());
                    } else {
                        map.put("szproductname", "无效服务商");
                    }
                } else if ("02".equals(customStockType)) {

                    Edmtickettypetab edmticket = (Edmtickettypetab) this.get(
                            Edmtickettypetab.class, customStockId);
                    if (edmticket != null) {
                        map.put("szproductname",
                                edmticket.getSztickettypename());
                    } else {
                        map.put("szproductname", "无效产品");
                    }

                    // System.out.println("Edmtickettypetab="+edmticket);

                }
                String usid = map.get("usid").toString();
                Custom custom = (Custom) this.get(Custom.class, usid);

                map.put("customname", custom.getCorpname());
            }

        }
        return ps;
    }

    // 判断数据库是否已经有该段时间内库存
    public List searchUserStock(Stocktab stocktab) {
        StringBuffer sb = new StringBuffer();

        sb.append(" from Stocktab where id=" + "'" + stocktab.getId() + "'"
                + " and stocktype=" + "'04'" + " and customStockType=" + "'" + stocktab.getCustomStockType() + "'" +
                " and customStockId=" + "'" + stocktab.getCustomStockId() + "'" + " and ( (startdate<=" + "'"
                + stocktab.getStartdate() + "'" + " and  enddate>=" + "'"
                + stocktab.getEnddate() + "')");
        sb.append("  or (startdate<=" + "'" + stocktab.getStartdate() + "'"
                + " and enddate>=" + "'" + stocktab.getStartdate() + "')");
        sb.append(" or (startdate>= " + "'" + stocktab.getStartdate() + "'"
                + " and enddate<=" + "'" + stocktab.getEnddate() + "')");
        sb.append(" or (startdate<=" + "'" + stocktab.getEnddate() + "'"
                + " and enddate>=" + "'" + stocktab.getEnddate() + "'))");
        List sList = null;
        try {
            sList = this.find(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sList;
    }


}
