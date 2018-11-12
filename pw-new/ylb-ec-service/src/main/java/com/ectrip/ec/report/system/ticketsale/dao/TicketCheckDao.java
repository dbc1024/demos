package com.ectrip.ec.report.system.ticketsale.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketCheckDao;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.StssalesvouchertabId;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class TicketCheckDao extends GenericDao implements ITicketCheckDao {

    public List getStssoldtickettablist(String printno) {
        List list = new ArrayList();
        String hsql = " from Stssoldtickettab where szticketprintno='" + printno
                + "'";
        list = this.find(hsql);
        return list;
    }

    public List getStssoldtickettablist(Long iticketstationid, Long iserialnum) {
        List list = new ArrayList();
        String hsql = " from Stssoldtickettab where  id.iticketstationid="
                + iticketstationid + " and iserialnum=" + iserialnum;
        list = this.find(hsql);
        return list;
    }

    public Stssalesvouchertab getStssalesvouchertab(Long iticketstationid,
                                                    Long isalesvoucherid) {
        StssalesvouchertabId id = new StssalesvouchertabId(isalesvoucherid,
                iticketstationid);
        Stssalesvouchertab s = (Stssalesvouchertab) this.get(
                Stssalesvouchertab.class, id);
        return s;
    }

    public List getchecklist(Long szsoldticketid, Long isalesvoucherdetailsid,
                             Long isalesvoucherid, Long iticketstationid) {
        List list = new ArrayList();
        String hsql = " select new map( g.szgardengatename as szgardengatename, t.dtmakedate as dtmakedate,t.zfseq as zfseq ) from Ticketchecklist t,Esbgardengatetab g where t.isalesvoucherid="
                + isalesvoucherid
                + " and t.iticketstationid="
                + iticketstationid
                + " and t.szsoldticketid="
                + szsoldticketid
                + " and t.isalesvoucherdetailsid="
                + isalesvoucherdetailsid
                + " and g.id.igardengateid=t.igardengateid and g.id.iscenicid=t.iscenicid";
        list = this.find(hsql);
        return list;
    }

    public Edmtickettypetab getEdmtickettypetab(Long itickettypeid){
        Edmtickettypetab e=(Edmtickettypetab)this.get(Edmtickettypetab.class, itickettypeid);
        return e;
    }
    public List getEsbticketstationtab(String szstationcode){
        List list = new ArrayList();
        String hsql = " from Esbticketstationtab where szstationcode='"+szstationcode+"'";
        list = this.find(hsql);
        return list;
    }

    public PaginationSupport queryCheckTicketList(int radiobutton,String gardes,
                                                  Long itickettypeid, String rzti, String ldti, String starttime, String endtime,
                                                  int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" select new map(st.itickettypeid as itickettypeid,ti.sztickettypename as sztickettypename,st.szticketprintno as szticketprintno,st.manyouno as manyouno,tc.igardengateid as igardengateid,ga.szgardengatename as szgardengatename,tc.iaccessequipid as iaccessequipid,ac.szaccessequipname as szaccessequipname,tc.dtmakedate as dtmakedate,st.manyouno as manyouno,tc.int1 as int1) ");
        hsql.append(" from Ticketchecklist tc,Stssoldtickettab st,Esbgardengatetab ga,Esbaccessequiptab ac,Edmtickettypetab ti ");
        hsql.append(" where tc.szsoldticketid = st.id.szsoldticketid and tc.isalesvoucherdetailsid = st.id.isalesvoucherdetailsid and tc.isalesvoucherid = st.id.isalesvoucherid and tc.iticketstationid = st.id.iticketstationid and st.itickettypeid = ti.itickettypeid and tc.igardengateid = ga.id.igardengateid and tc.iaccessequipid = ac.id.iaccessequipid ");
        if(gardes!=null){
            hsql.append(" and tc.igardengateid in "+gardes+" ");
        }

        if(itickettypeid!=0L){
            hsql.append(" and st.itickettypeid = "+itickettypeid);
        }
        if(radiobutton==0){
            hsql.append(" and substr(tc.dtmakedate,1,10)>='"+rzti+"' and substr(tc.dtmakedate,1,10)<='"+ldti+"' ");
        }else{
            hsql.append(" and substr(tc.dtmakedate,1,10) = '"+Tools.getDays()+"' ");
            hsql.append(" and substr(tc.dtmakedate,12,8)>='"+starttime+"' and substr(tc.dtmakedate,12,8)<='"+endtime+"' ");
        }
        hsql.append(" order by tc.dtmakedate desc");
        return this.findPage(hsql.toString(), pageSize, page, url);
    }

    public List queryCheckTicketList(int radiobutton,String gardes,
                                     Long itickettypeid, String rzti, String ldti, String starttime, String endtime){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" select new map(st.itickettypeid as itickettypeid,ti.sztickettypename as sztickettypename,st.szticketprintno as szticketprintno,st.manyouno as manyouno,tc.igardengateid as igardengateid,ga.szgardengatename as szgardengatename,tc.iaccessequipid as iaccessequipid,ac.szaccessequipname as szaccessequipname,tc.dtmakedate as dtmakedate,st.manyouno as manyouno,tc.int1 as int1) ");
        hsql.append(" from Ticketchecklist tc,Stssoldtickettab st,Esbgardengatetab ga,Esbaccessequiptab ac,Edmtickettypetab ti ");
        hsql.append(" where tc.szsoldticketid = st.id.szsoldticketid and tc.isalesvoucherdetailsid = st.id.isalesvoucherdetailsid and tc.isalesvoucherid = st.id.isalesvoucherid and tc.iticketstationid = st.id.iticketstationid and st.itickettypeid = ti.itickettypeid and tc.igardengateid = ga.id.igardengateid and tc.iaccessequipid = ac.id.iaccessequipid ");
        if(gardes!=null){
            hsql.append(" and tc.igardengateid in "+gardes+" ");
        }

        if(itickettypeid!=0L){
            hsql.append(" and st.itickettypeid = "+itickettypeid);
        }
        if(radiobutton==0){
            hsql.append(" and substr(tc.dtmakedate,1,10)>='"+rzti+"' and substr(tc.dtmakedate,1,10)<='"+ldti+"' ");
        }else{
            hsql.append(" and substr(tc.dtmakedate,1,10) = '"+Tools.getDays()+"' ");
            hsql.append(" and substr(tc.dtmakedate,12,8)>='"+starttime+"' and substr(tc.dtmakedate,12,8)<='"+endtime+"' ");
        }
        hsql.append(" order by tc.dtmakedate desc");
        return this.find(hsql.toString());
    }

    /**
     *
     * Describe:
     * @author: huying
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2015-7-14
     */
    public PaginationSupport queryTicketNoList(String gardes,List gradeList,String szticketprintno,Long iscenicid,Long ibusinessid,String manyouno,
                                               String rzti, String ldti, String rzhs, String ldhs,int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select distinct new map(s.manyouno as manyouno, s.szticketprintno as szticketprintno,s.ibusinessid as ibusinessid, eb.szbusinessname as szbusinessname," +
                " s.mactualsaleprice as mactualsaleprice, s.iscenicid, e.szscenicname as szscenicname,sa.dtmakedate as  dtmakedate )" +
                " from Stssoldtickettab s,Stssoldticketsubtab so, Edmbusinesstab eb, Esbscenicareatab e ,Stssalesvouchertab sa " +
                " WHERE  s.id.szsoldticketid = so.id.szsoldticketid and s.id.isalesvoucherdetailsid = so.id.isalesvoucherdetailsid  " +
                " and s.id.isalesvoucherid = so.id.isalesvoucherid and s.id.iticketstationid = so.id.iticketstationid and s.itickettypeid = so.itickettypeid " +
                " and s.icrowdkindid = so.icrowdkindid and s.ibusinessid = eb.ibusinessid and  s.iscenicid=e.iscenicid and sa.id.isalesvoucherid = so.id.isalesvoucherid and sa.id.iticketstationid = so.id.iticketstationid  ");
	
		
		/*if(ibusinessid.toString().equals("")||ibusinessid==0L){
		
		}else {
			hsql.append(" and s.ibusinessid = "+ibusinessid);
		}
		
		if(iscenicid.toString().equals("")||iscenicid==0L){
			
		}else {
			hsql.append(" and s.iscenicid = "+iscenicid);
		}
		if (manyouno.equals("")||manyouno==null) {
			
		}else {
			hsql.append(" and s.manyouno = "+manyouno);
		}
*/		hsql.append(" and sa.dtmakedate>='"+rzti+" "+rzhs+"' and sa.dtmakedate<='"+ldti+" "+ldhs+"' ");
        hsql.append(" order by s.manyouno asc");
        System.out.println("11111:"+hsql);
        PaginationSupport ps= this.findPage(hsql.toString(), pageSize, page, url);
        List list=ps.getItems();
        for (int i = 0; i < list.size(); i++) {
            Map map=(Map) list.get(i);
            String  printno=map.get("szticketprintno").toString();
            Long ibusiness=Long.parseLong( map.get("ibusinessid").toString());
            Double mactualsaleprice=Double.parseDouble(map.get("mactualsaleprice").toString());
            String sql="select distinct new map(s.manyouno as manyouno,s.szticketprintno as szticketprintno, s.ibusinessid as ibusinessid, eb.szbusinessname as szbusinessname," +
                    " s.mactualsaleprice as mactualsaleprice,so.igardengateid as igardengateid, g.szgardengatename as szgardengatename,so.ipassedtimes as ipassedtimes, " +
                    " s.iscenicid, e.szscenicname as szscenicname,sa.dtmakedate as  dtmakedate ) from Stssoldtickettab s,Stssoldticketsubtab so, Edmbusinesstab eb, Esbscenicareatab e,Stssalesvouchertab sa, Esbgardengatetab g " +
                    " WHERE  s.id.szsoldticketid = so.id.szsoldticketid and s.id.isalesvoucherdetailsid = so.id.isalesvoucherdetailsid  " +
                    " and s.id.isalesvoucherid = so.id.isalesvoucherid and s.id.iticketstationid = so.id.iticketstationid and s.itickettypeid = so.itickettypeid " +
                    " and s.icrowdkindid = so.icrowdkindid and s.ibusinessid = eb.ibusinessid and  s.iscenicid=e.iscenicid  " +
                    " and so.igardengateid = g.id.igardengateid  and sa.id.isalesvoucherid = so.id.isalesvoucherid and sa.id.iticketstationid = so.id.iticketstationid " +
                    " and s.szticketprintno='"+printno+"'"+" and s.ibusinessid="+ibusiness+" and s.mactualsaleprice="+mactualsaleprice + " order by so.igardengateid ";

            System.out.println("2222:"+sql);
            List alList=this.find(sql);
            List list2=new ArrayList();
            for (int k = 0; k < gradeList.size(); k++) {
                Esbgardengatetab e=(Esbgardengatetab) gradeList.get(k);
                for (int j = 0; j < alList.size(); j++) {
                    Map map2=(Map) alList.get(j);
                    String igardengateid=map2.get("igardengateid").toString();
                    if (igardengateid.equals(e.getId().getIgardengateid().toString())) {
                        Map map3=new HashMap();
                        map3.put("ipassedtimes", map2.get("ipassedtimes").toString());
                        list2.add(map3);
                        break;
                    }
                    if(j==alList.size()-1){
                        Map map3=new HashMap();
                        map3.put("ipassedtimes", 0);
                        list2.add(map3);
                        break;
                    }
                }
            }
            map.put("ipasslist",list2);
        }

        return  ps;
    }

    public List findgardeList(String gardes){

        String hsql=" from Esbgardengatetab g where g.id.igardengateid in"+gardes;
        hsql+=" order by g.id.igardengateid";
        List list=this.find(hsql);
        return list;
    }

}
