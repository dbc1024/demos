package com.ectrip.ticket.sale.service.commonSale.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.sys.model.authcode.CodeType;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.EsbaccessequiptabId;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.applyorder.Ordertickettourist;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.order.StssoldtickettabId;
import com.ectrip.ticket.model.order.Ticketchecklist;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.sale.service.commonSale.dao.idao.ICommonSaleDao;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.CheckListPojo;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.FingerPrintPojo;
import com.ectrip.ticket.sale.service.commonSale.model.request.CheckFingerPrintRequest;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckFingerPrintResponse;

/**
 * Created by chenxinhao on 2017/3/13.
 */
public class CommonSaleDao extends GenericDao implements ICommonSaleDao {

    public List<Ordertickettourist> findTourists(String orid, String providerId, String productId, String groupId, String credentials) {
        StringBuffer hsql = new StringBuffer();
        hsql.append("from Ordertickettourist where aorid = '" + orid + "' ");
        if (!StringUtils.isBlank(providerId)) {
            hsql.append(" and iscenicid = " + Long.parseLong(providerId));
        }
        if (!StringUtils.isBlank(productId)) {
            hsql.append(" and itickettypeid = " + Long.parseLong(productId));
        }
        if (!StringUtils.isBlank(groupId)) {
            hsql.append(" and icrowdkindid = " + Long.parseLong(groupId));
        }
        if (!StringUtils.isBlank(credentials)) {
            hsql.append(" and zjhm = '" + credentials.toUpperCase() + "' ");
        }
        return this.find(hsql.toString());
    }

    public Opcemployeecardtab findEmp(String ticketNo) {
        String hsql = "from Opcemployeecardtab where upper(icardno) = '" + ticketNo.toUpperCase() + "' ";
        List list = this.find(hsql);
        if (list != null && !list.isEmpty()) {
            return (Opcemployeecardtab) list.get(0);
        }
        return null;
    }

    public Stssoldtickettab findStsso(String ticketNo) {
        String hsql = "from Stssoldtickettab where szticketprintno = '" + ticketNo + "' ";
        List list = this.find(hsql);
        if (list != null && !list.isEmpty()) {
            return (Stssoldtickettab) list.get(0);
        }
        return null;
    }

    public List findStssticket(String ticketNo) {
        String hsql = " from Stssoldtickettab where (szticketprintno = '" + ticketNo + "' or upper(myzj)='" + ticketNo.toUpperCase() + "') and dtenddate>='" + Tools.getDays() + "' and iplayerperticket=1 and byvalidity='00' order by dtmakedate desc ";
        List list = this.find(hsql);
        return list;
    }

    public List<String> findGardenids(StssoldtickettabId id) {
        List<String> groupIds = new ArrayList<String>();
        AuthCode system = getAuthCode(CodeType.SYSTEM.getCode(), "");
        if (system != null) {
            String hsql = "select distinct new map(igardengateid as igardengateid) from Stssoldticketsubtab where id.szsoldticketid = " + id.getSzsoldticketid() + " and " +
                    "id.isalesvoucherdetailsid = " + id.getIsalesvoucherdetailsid() + " and " +
                    "id.isalesvoucherid = " + id.getIsalesvoucherid() + " and id.iticketstationid = " + id.getIticketstationid();
            List list = this.find(hsql);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String igardengateid = map.get("igardengateid").toString();
                    AuthCode gardenCode = getAuthCode(CodeType.GARDEN.getCode(), igardengateid);
                    if (gardenCode != null) {
                        groupIds.add(system.getAuthCode() + "_" + gardenCode.getAuthCode() + "_" + gardenCode.getOrginId());
                    }
                }
            }
        }
        return groupIds;
    }

    public List<String> findEmpGardenids() {
        List<String> groupIds = new ArrayList<String>();
        AuthCode system = getAuthCode(CodeType.SYSTEM.getCode(), "");
        if (system != null) {
            String hsql = "select new map(id.igardengateid as igardengateid) from Esbgardengatetab where byisuse = 1";
            List list = this.find(hsql);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    String igardengateid = map.get("igardengateid").toString();
                    AuthCode gardenCode = getAuthCode(CodeType.GARDEN.getCode(), igardengateid);
                    if (gardenCode != null) {
                        groupIds.add(system.getAuthCode() + "_" + gardenCode.getAuthCode() + "_" + gardenCode.getOrginId());
                    }
                }
            }
        }
        return groupIds;
    }

    public AuthCode getAuthCode(String codeType, String orginId) {
        String hsql = "from AuthCode where codeType = '" + codeType + "' ";
        if (!StringUtils.isBlank(orginId)) {
            hsql += " and orginId = '" + orginId + "' ";
        }
        List list = this.find(hsql.toString());
        if (list != null && !list.isEmpty()) {
            return (AuthCode) list.get(0);
        }
        return null;
    }

    public CheckFingerPrintResponse findFingerPrints(CheckFingerPrintRequest request) {
        CheckFingerPrintResponse response = new CheckFingerPrintResponse();
        List<FingerPrintPojo> fingerPrintPojos = new ArrayList<FingerPrintPojo>();
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(st.szticketprintno as szticketprintno,se.bsfilebinary as bsfilebinary) from Stssalesvouchertab s,Stssoldtickettab st,Stssoldticketattesttab se ");
        hsql.append(" where s.id.isalesvoucherid = st.id.isalesvoucherid and s.id.iticketstationid = st.id.iticketstationid ");
        hsql.append(" and st.id.isalesvoucherid = se.id.isalesvoucherid and st.id.iticketstationid = se.id.iticketstationid ");
        hsql.append(" and st.id.isalesvoucherdetailsid = se.id.isalesvoucherdetailsid and st.id.szsoldticketid = se.id.szsoldticketid ");
        hsql.append(" and s.dtmakedate >= '" + request.getBeginTime() + "' and s.dtmakedate <= '" + request.getEndTime() + "' order by s.dtmakedate");
        PaginationSupport ps = findPage(hsql.toString(), request.getPageSize(), request.getPage(), "");
        if (ps != null) {
            response.setTotalCount(ps.getTotalCount());
            List list = ps.getItems();
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    FingerPrintPojo pojo = new FingerPrintPojo();
                    pojo.setTicketNo((String) map.get("szticketprintno"));
                    pojo.setFingerPrint((String) map.get("bsfilebinary"));
                    fingerPrintPojos.add(pojo);
                }
            }
        }
        response.setFingerPrints(fingerPrintPojos);
        return response;
    }

    public List<CheckListPojo> getchecklist(Stssoldtickettab stss) {
        List<CheckListPojo> pojos = new ArrayList<CheckListPojo>();
        List<Ticketchecklist> cklist = find("from Ticketchecklist s where s.isalesvoucherid=" + stss.getId().getIsalesvoucherid() + " and s.iticketstationid ="
                + stss.getId().getIticketstationid() + " and s.szsoldticketid =" + stss.getId().getSzsoldticketid() + " and s.isalesvoucherdetailsid=" + stss.getId().getIsalesvoucherdetailsid()
                + " order by dtmakedate desc");
        if (cklist != null && !cklist.isEmpty()) {
            for (Ticketchecklist check : cklist) {
                CheckListPojo pojo = new CheckListPojo();
                Esbgardengatetab garden = (Esbgardengatetab) get(Esbgardengatetab.class, new EsbgardengatetabId(check.getIgardengateid(), check.getIscenicid()));
                if (garden != null) {
                    pojo.setGardenName(garden.getSzgardengatename());
                }
                Esbaccessequiptab machine = (Esbaccessequiptab) get(Esbaccessequiptab.class, new EsbaccessequiptabId(check.getIaccessequipid(), check.getIscenicid(),check.getIgardengateid()));
                if (machine != null) {
                    pojo.setMachineName(machine.getSzaccessequipname());
                }
                pojo.setCheckDate(check.getDtmakedate());
                pojo.setNumb(check.getInt1());
                pojos.add(pojo);
            }
        }
        return pojos;
    }
}
