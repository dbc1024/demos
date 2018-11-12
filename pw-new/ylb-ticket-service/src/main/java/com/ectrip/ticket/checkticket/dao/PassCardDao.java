package com.ectrip.ticket.checkticket.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ticket.checkticket.dao.idao.IPassCardDao;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;

@Service
public class PassCardDao extends GenericDao implements IPassCardDao {
    /**
     * * Describe:判断卡号类型
     *
     * @param accid           闸机ID
     * @param szticketprintno 卡号
     * @return
     * @author chenxinhao Date:2012-9-20
     * @see com.ectrip.checkticket.dao.idao.IPassCardDao#CheckPassCrad(java.lang.String,
     * java.lang.String)
     */
    public String CheckPassCrad(String accid, String szticketprintno) {
        String date = Tools.getDayTimes();
        Esbaccessequiptab acc = (Esbaccessequiptab) this.find(
                " from Esbaccessequiptab where id.iaccessequipid=" + accid).get(0);

        StringBuffer msql = new StringBuffer(" from Stssoldtickettab s where s.myzj = '" + szticketprintno + "' and s.dtstartdate <='" + Tools.getDays() + "' and s.dtenddate>='"
                + Tools.getDays()
                + "' order by s.dtmakedate desc ");
        List list = new ArrayList();
        List ticketList = this.find(msql.toString());
        if (ticketList == null || ticketList.size() == 0) {

            list = this
                    .find(" from Opcemployeecardtab op where op.icardno = '"
                            + szticketprintno + "'");
            if (list == null || list.size() == 0) {// 判断该卡号是否是员工
                return "0"; // 都不是
            } else {
                Opcemployeecardtab op = (Opcemployeecardtab) list.get(0);
                if (op.getByisdaoyou() == null || op.getByisdaoyou() == 0) {
                    return "2"; // 是员工
                } else {
                    return "1"; // 是员工
                }
            }

        } else if (ticketList.size() > 0) {// 在售出门票表中，返回一个票号
            String printno = "";
            boolean bs = true;
            for (int x = 0; x < ticketList.size(); x++) {
                Stssoldtickettab soldticket = (Stssoldtickettab) ticketList.get(x);

                StringBuffer vsql = new StringBuffer(" from Stssoldticketsubtab su where su.id.szsoldticketid="
                        + soldticket.getId().getSzsoldticketid()
                        + " and su.id.isalesvoucherdetailsid="
                        + soldticket.getId().getIsalesvoucherdetailsid()
                        + " and su.id.isalesvoucherid="
                        + soldticket.getId().getIsalesvoucherid()
                        + " and su.id.iticketstationid="
                        + soldticket.getId().getIticketstationid()
                        + " and su.igardengateid = "
                        + acc.getId().getIgardengateid()
                        + " and su.dtbegindate <='"
                        + date
                        + "' and su.dtenddate>='" + date + "' and su.isvalid=1");
                List ticketsubList = this.find(vsql.toString());
                if (ticketsubList != null && ticketsubList.size() > 0) {
                    printno = soldticket.getSzticketprintno();
                    bs = false;
                    break;
                }
            }

            if (bs) {
                for (int x = 0; x < ticketList.size(); x++) {
                    Stssoldtickettab soldticket = (Stssoldtickettab) ticketList.get(x);

                    Edmtickettypetab ticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, soldticket.getItickettypeid());
                    if (ticket != null && ticket.getBycategorytype().equals("0004")) {
                        StringBuffer vsql = new StringBuffer(" from Stssoldticketsubtab su where su.id.szsoldticketid="
                                + soldticket.getId().getSzsoldticketid()
                                + " and su.id.isalesvoucherdetailsid="
                                + soldticket.getId().getIsalesvoucherdetailsid()
                                + " and su.id.isalesvoucherid="
                                + soldticket.getId().getIsalesvoucherid()
                                + " and su.id.iticketstationid="
                                + soldticket.getId().getIticketstationid()
                                + " and su.igardengateid = "
                                + acc.getId().getIgardengateid()
                                + " and substr(su.dtbegindate,1,10) <='"
                                + Tools.getDays()
                                + "' and substr(su.dtenddate,1,10)>='" + Tools.getDays() + "' and su.isvalid=1");
                        List ticketsubList = this.find(vsql.toString());
                        if (ticketsubList != null && ticketsubList.size() > 0) {
                            printno = soldticket.getSzticketprintno();
                            bs = false;
                            break;
                        }
                    }

                }
            }

            if (!bs) {
                return printno;
            } else {
                list = this
                        .find(" from Opcemployeecardtab op where op.icardno = '"
                                + szticketprintno + "'");
                if (list == null || list.size() == 0) {// 判断该卡号是否是员工
                    return "0"; // 都不是
                } else {
                    Opcemployeecardtab op = (Opcemployeecardtab) list.get(0);
                    if (op.getByisdaoyou() == null || op.getByisdaoyou() == 0) {
                        return "2"; // 是员工
                    } else {
                        return "1"; // 是员工
                    }
                }
            }

        }

        return "0";
    }

    /**
     * * Describe:判断卡号类型
     *
     * @param accid           闸机ID
     * @param szticketprintno 卡号
     * @return
     * @author chenxinhao Date:2012-9-20
     * @see com.ectrip.checkticket.dao.idao.IPassCardDao#CheckPassCrad(java.lang.String,
     * java.lang.String)
     */
    public String CheckPassCradoneTable(String accid, String szticketprintno) {
        String date = Tools.getNowString();
        Esbaccessequiptab acc = (Esbaccessequiptab) this.find(
                " from Esbaccessequiptab where id.iaccessequipid=" + accid).get(0);
        String sql = "select su.szticketprintno from Stsschecktab su where  su.igardengateid = "
                + acc.getId().getIgardengateid()
                + " and su.myzj = '"
                + szticketprintno
                + "' and su.dtbegindate <='"
                + date
                + "' and su.dtenddate>='"
                + date
                + "' and su.isvalid=1 group by su.szticketprintno order by su.dtmakedate desc";

        List list = new ArrayList();
        list = this.find(sql);
        if (list == null || list.size() == 0) {// 判断该卡号在售出门票表中是否是唯一
            list = this.find(" from Opcemployeecardtab op where op.icardno = '"
                    + szticketprintno + "'");
            if (list == null || list.size() == 0) {// 判断该卡号是否是员工
                return "0"; // 都不是
            } else {
                Opcemployeecardtab op = (Opcemployeecardtab) list.get(0);
                if (op.getByisdaoyou() == null || op.getByisdaoyou() == 0) {
                    return "2"; // 是员工
                } else {
                    return "1"; // 是员工
                }
            }
        } else if (list.size() == 1) {// 在售出门票表中唯一，返回票号
            return (String) list.get(0);
        } else {// 在售出门票表中存在多条数据
            return "-1";
        }
    }
}
