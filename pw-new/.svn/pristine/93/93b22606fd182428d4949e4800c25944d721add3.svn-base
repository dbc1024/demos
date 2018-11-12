package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.rentrl.Rentrlcartickettab;
import com.ectrip.ec.model.rentrl.Rentrlcarvartab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Lineproduct;
import com.ectrip.ticket.model.provider.TicketPrintNo;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.provider.dao.ITicketTypeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketTypeDAO extends GenericDao implements ITicketTypeDAO {

	@Autowired
	private SysService sysService;

    /**
     * 查询某服务商下所有产品列表*
     * Describe:
     * @see com.ectrip.system.provider.dao.idao.ITicketTypeDAO#getTickettypeList(java.lang.Long, int, int, java.lang.String)
     * @param providerId服务商编号
     * @param page页码
     * @param pagesize每页显示数
     * @param url访问地址
     * @param type票类型（基础票、套票、一卡通类型(0100)、消费项目(0101)）
     * @return
     * @author huangyuqi
     * Date:2011-9-22
     */
    public PaginationSupport getTickettypeList(String searchkey,Long providerId,int page, int pagesize,
                                               String url,String type) {
        PaginationSupport ps;

        String hsql="";
        if("01".equals(type) || "".equals(type)){
            hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale, sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.iscenicid="+providerId+" and ttype.bycategorytype <> '0010' and ttype.bycategorytype <> '0101' and ttype.bycategorytype <> '0100' order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid";
            if (searchkey!=null&&!searchkey.equals("")) {
                hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale, sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.sztickettypename like '%"+searchkey+"%' and ttype.iscenicid="+providerId+" and ttype.bycategorytype <> '0010' and ttype.bycategorytype <> '0100' and ttype.bycategorytype <> '0101' order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid ";
            }
        }
        if("02".equals(type)){
            hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.iscenicid="+providerId+" and ttype.bycategorytype = '0010' order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid";
            if (searchkey!=null&&!searchkey.equals("")) {
                hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.sztickettypename like '%"+searchkey+"%' and ttype.iscenicid="+providerId+" and ttype.bycategorytype = '0010' order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid ";
            }
        }
        if("0100".equals(type)){
            hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.iscenicid="+providerId+" and ttype.bycategorytype = '0100'  order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid";
            if (searchkey!=null&&!searchkey.equals("")) {
                hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.sztickettypename like '%"+searchkey+"%' and ttype.iscenicid="+providerId+" and ttype.bycategorytype = '0100'  order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid ";
            }
        }
        if("0101".equals(type)){
            hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.iscenicid="+providerId+" and ttype.bycategorytype = '0101'  order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid";
            if (searchkey!=null&&!searchkey.equals("")) {
                hsql="select new map(ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,sys1.pmva as strbycategorytype,sys2.pmva as strticketway,sys3.pmva as strmediatype,ttype.byusage as byusage)from Edmtickettypetab ttype,Sysparv5 sys1,Sysparv5 sys2,Sysparv5 sys3 where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' and ttype.sztickettypename like '%"+searchkey+"%' and ttype.iscenicid="+providerId+" and ttype.bycategorytype = '0101'  order by ttype.byisuse desc,ttype.isequence desc,ttype.itickettypeid ";
            }
        }

        ps = this.findPage(hsql, pagesize, page, url);


        return ps;
    }

    /**
     * 查询某服务商下所有产品列表
     * Describe:
     * @auth:chenyidong
     * @param providerId服务商编号
     * @param type 票类型（基础、套票、一卡通、消费项目）
     * @return
     * return:PaginationSupport
     * Date:2013-03-22
     */
    public List getTickettypeListSelect(Long providerId,String type){
        
//        String hsql="select new map("
//        		+ " ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,"
//        		+ " ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,"
//        		+ " ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,"
//        		+ " ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,"
//        		+ " ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,"
//        		+ " sys1.pmva as strbycategorytype, sys2.pmva as strticketway, sys3.pmva as strmediatype"
//        		+ " )"
//        		+ " from Edmtickettypetab ttype, Sysparv5 sys1, Sysparv5 sys2, Sysparv5 sys3 "
//        		+ " where ttype.bycategorytype=sys1.id.pmcd and sys1.id.pmky='PRTP' and sys2.id.pmcd = ttype.bymaketicketway and sys2.id.pmky='CPFS' and sys3.id.pmcd=ttype.bymediatype and sys3.id.pmky='CKFS' "
//        		+ " and ttype.iscenicid="+ providerId;
        
    	//【项目重构改造】
        String hsql="select new map("
        		+ " ttype.itickettypeid as itickettypeid,ttype.iscenicid as iscenicid,ttype.sztickettypecode as sztickettypecode,ttype.sztickettypename as sztickettypename,"
        		+ " ttype.iticketnoruleid as iticketnoruleid,ttype.bycategorytype as bycategorytype,ttype.byuselimit as byuselimit,ttype.bymaketicketway as bymaketicketway,"
        		+ " ttype.bymediatype as bymediatype,ttype.szticketprintcode as szticketprintcode,ttype.mcostprice as mcostprice,ttype.mnominalfee as mnominalfee,ttype.byisuse as byisuse,"
        		+ " ttype.bispersontimestat as bispersontimestat,ttype.validityday as validityday,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale,ttype.isequence as isequence,"
        		+ " ttype.issale as issale,ttype.iscansale as iscansale,ttype.iscontrol as iscontrol,ttype.iscontrolsale as iscontrolsale"
        		+ " )"
        		+ " from Edmtickettypetab ttype "
        		+ " where ttype.iscenicid="+ providerId;
        
        
        if("01".equals(type) || "".equals(type)){
            hsql= hsql + " and ttype.bycategorytype <> '0010' and ttype.bycategorytype <> '0101' and ttype.bycategorytype <> '0100' order by ttype.isequence,ttype.itickettypeid ";
        }
        if("02".equals(type)){
            hsql= hsql + " and ttype.bycategorytype = '0010'  order by ttype.isequence,ttype.itickettypeid ";
        }
        if("0100".equals(type)){
            hsql= hsql + " and ttype.bycategorytype = '0100'  order by ttype.isequence,ttype.itickettypeid ";
        }
        if("0101".equals(type)){
            hsql= hsql + " and ttype.bycategorytype = '0101'  order by ttype.isequence,ttype.itickettypeid ";
        }
        
        List<Map> tickettypeList = this.find(hsql);
        
        

        
        if(tickettypeList != null && tickettypeList.size()>0) {
        	
            StringBuilder bycategorytypes = new StringBuilder();
            StringBuilder bymaketicketways = new StringBuilder();
            StringBuilder bymediatypes = new StringBuilder();
            for (Map tickettype : tickettypeList) {
            	String bycategorytype = tickettype.get("bycategorytype").toString();
            	bycategorytypes.append(bycategorytype +",");
            	
            	String bymaketicketway = tickettype.get("bymaketicketway").toString();
            	bymaketicketways.append(bymaketicketway +",");
            	
            	String bymediatype = tickettype.get("bymediatype").toString();
            	bymediatypes.append(bymediatype +",");
    		}
            bycategorytypes.deleteCharAt(bycategorytypes.length() - 1);
            bymaketicketways.deleteCharAt(bymaketicketways.length() - 1);
            bymediatypes.deleteCharAt(bymediatypes.length() - 1);
            
            List<Map> sysparams_pmva_bycategorytype = sysService.getSysparamsByPmkyAndPmcds("PRTP", bycategorytypes.toString());
            List<Map> sysparams_pmva_bymaketicketway = sysService.getSysparamsByPmkyAndPmcds("CPFS", bymaketicketways.toString());
            List<Map> sysparams_pmva_bymediatype = sysService.getSysparamsByPmkyAndPmcds("CKFS", bymediatypes.toString());
        	
        	
        	
        	//循环遍历按原结构赋值赋值
            for (Map tickettype : tickettypeList) {
            	String bycategorytype = tickettype.get("bycategorytype").toString();
            	for (Map map : sysparams_pmva_bycategorytype) {
            		String pmva = map.get("pmva").toString();
            		if(bycategorytype.equals(pmva)) {
            			tickettype.put("bycategorytype", pmva);
            			break;
            		}
    			}
            	
            	String bymaketicketway = tickettype.get("bymaketicketway").toString();
            	for (Map map : sysparams_pmva_bymaketicketway) {
            		String pmva = map.get("pmva").toString();
            		if(bymaketicketway.equals(pmva)) {
            			tickettype.put("bymaketicketway", pmva);
            			break;
            		}
    			}
            	
            	String bymediatype = tickettype.get("bymediatype").toString();
            	for (Map map : sysparams_pmva_bymediatype) {
            		String pmva = map.get("pmva").toString();
            		if(bymediatype.equals(pmva)) {
            			tickettype.put("bymediatype", pmva);
            			break;
            		}
    			}
    		}
            
        }
        
        return tickettypeList;
    }

    /**
     * 根据产品编号查询产品信息*
     * Describe:
     * @see com.ectrip.system.provider.dao.idao.ITicketTypeDAO#queryTickettype(java.lang.Long)
     * @param tickettypeId产品编号
     * @return
     * @author huangyuqi
     * Date:2011-9-22
     */
    public Edmtickettypetab queryTickettype(Long tickettypeId) {

        Edmtickettypetab tickettype=null;
        //得到产品信息
        tickettype = (Edmtickettypetab)this.get(Edmtickettypetab.class, tickettypeId);
        if(tickettype!=null){
            //得到服务商信息
            Esbscenicareatab esbscenicareatab =(Esbscenicareatab) this.get(Esbscenicareatab.class, tickettype.getIscenicid());
            if(esbscenicareatab!=null){
                tickettype.setSzscenicname(esbscenicareatab.getSzscenicname());//服务商名称
            }
            //根据票号规则编号 得到票号规则信息
            Edmticketnoruletab edmticketnoruletab = (Edmticketnoruletab)this.get(Edmticketnoruletab.class, tickettype.getIticketnoruleid());
            if(edmticketnoruletab!=null){
                tickettype.setStrticketrule(edmticketnoruletab.getSzticketnorulename());//票号规则名称
            }
            Sysparv5Id sysparid = null;
            Sysparv5 sysparv5=null;
            if(tickettype.getBycategorytype()!=null && !"".equals(tickettype.getBycategorytype())){
                sysparid =new Sysparv5Id();
                sysparid.setPmcd(tickettype.getBycategorytype());
                sysparid.setPmky("PRTP");//产品类型
                sysparv5 =(Sysparv5) this.get(Sysparv5.class, sysparid);
                if(sysparv5!=null){
                    tickettype.setStrbycategorytype(sysparv5.getPmva());
                }
            }
            if(tickettype.getBymaketicketway()!=null && !"".equals(tickettype.getBymaketicketway())){
                sysparid = new Sysparv5Id();
                sysparid.setPmcd(tickettype.getBymaketicketway());
                sysparid.setPmky("CPFS");//出票方式
                sysparv5 =(Sysparv5) this.get(Sysparv5.class, sysparid);
                if(sysparv5!=null){
                    tickettype.setStrticketway(sysparv5.getPmva());
                }
            }
            if(tickettype.getBymediatype()!=null && !"".equals(tickettype.getBymediatype())){
                sysparid = new Sysparv5Id();
                sysparid.setPmcd(tickettype.getBymediatype());
                sysparid.setPmky("CKFS");//介质类型
                sysparv5 =(Sysparv5) this.get(Sysparv5.class, sysparid);
                if(sysparv5!=null){
                    tickettype.setStrmediatype(sysparv5.getPmva());
                }
            }
            if(tickettype.getIticketnoruleid()!=null && !"".equals(tickettype.getIticketnoruleid())){
                //票号规则
                Edmticketnoruletab rule = (Edmticketnoruletab)this.get(Edmticketnoruletab.class, tickettype.getIticketnoruleid());
                if(rule!=null){
                    tickettype.setStrticketrule(rule.getSzticketnorulename());
                }
            }

            //图库
            String hsqls="select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="+tickettype.getItickettypeid();
            List list = this.find(hsqls);
            tickettype.setList(list);

            if("06".equals(esbscenicareatab.getScenictype())){//酒店
                Hotelproduct hotelproduct = (Hotelproduct)this.get(Hotelproduct.class, tickettype.getItickettypeid());
                if(hotelproduct!=null){
                    Sysparv5Id sys = new Sysparv5Id();
                    sys.setPmcd(hotelproduct.getBedtype());
                    sys.setPmky("BETP");//床型
                    Sysparv5 syspar =(Sysparv5)this.get(Sysparv5.class, sys);
                    hotelproduct.setStrbedtype(syspar.getPmva());
                }
                tickettype.setHotelproduct(hotelproduct);
            }
            if("07".equals(esbscenicareatab.getScenictype())){//旅行社
                Lineproduct lineproduct = (Lineproduct)this.get(Lineproduct.class, tickettype.getItickettypeid());
                if(lineproduct!=null){
//					Sysparv5Id sys = new Sysparv5Id();
//					sys.setPmcd(lineproduct.getStartingmethod());
//					sys.setPmky("CFFS");//出发方式
//					Sysparv5 syspar =(Sysparv5)this.get(Sysparv5.class, sys);
//					lineproduct.setStrstarting(syspar.getPmva());
                    //出发地
//					Galsourceregiontab source1 = (Galsourceregiontab)this.get(Galsourceregiontab.class, new Long(lineproduct.getDeparture()));
//					if(source1!=null){
                    lineproduct.setStrdeparture(lineproduct.getDeparture());
//					}
//					//目的地
//					Galsourceregiontab source2 = (Galsourceregiontab)this.get(Galsourceregiontab.class, new Long(lineproduct.getDestination()));
//					if(source2!=null){
                    lineproduct.setStrdestination(lineproduct.getDestination());
//					}

                }
                tickettype.setLineproduct(lineproduct);
            }

        }

        return tickettype;
    }
    /**
     * 判断产品是否是订单中存在
     * Describe:
     * @auth:huangyuqi
     * @param tickettypeId
     * @return
     * return:boolean
     * Date:2011-9-22
     */
    public boolean querytickettypeByOrder(Long tickettypeId){
        boolean isuse = false;
        int num = 0;
        //判断网上订单服务商出票明细
        String hsql=" from TOrderlist where itickettypeid = "+tickettypeId;
        List list = this.find(hsql);
        if(list!=null && list.size()>=1){
            num=num+1;
        }else{
            num = num+0;
        }
        //判断网上出票服务商子票明细
        String hsql1=" from TZorderlist where itickettypeid = "+tickettypeId;
        List list1 = this.find(hsql1);
        if(list1!=null && list1.size()>=1){
            num=num+1;
        }else{
            num = num+0;
        }
        //判断网上预定服务商订单明细
        String hsql2=" from YOrderlist where itickettypeid = "+tickettypeId;
        List list2 = this.find(hsql2);
        if(list2!=null && list2.size()>=1){
            num=num+1;
        }else{
            num = num+0;
        }

        //判断子票销售明细表
        String hsql3=" from Stscomticketsalesdetailstab where itickettypeid = "+tickettypeId;
        List list3 = this.find(hsql3);
        if(list3!=null && list3.size()>=1){
            num=num+1;
        }else{
            num = num+0;
        }

        //判断销售凭证明细表
        String hsql4=" from Stssalesvoucherdetailstab where itickettypeid = "+tickettypeId;
        List list4 = this.find(hsql4);
        if(list4!=null && list4.size()>=1){
            num=num+1;
        }else{
            num = num+0;
        }

        if(num==0){
            isuse = false;
        }else{
            isuse = true;
        }
        return isuse;
    }

    /**
     * 查询产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param productId产品Id
     * @param providerpdtp服务商类型
     * @return
     * return:Object
     * Date:2011-9-27
     */
    public Object getOthersProduct(Long productId,String providerpdtp){
        Object object =null;
        String hsql="";
        if("06".equals(providerpdtp)){//酒店
            object = this.get(Hotelproduct.class, productId);
        }
        if("07".equals(providerpdtp)){//旅行社
            object = this.get(Lineproduct.class, productId);
        }
        if("01".equals(providerpdtp)){//旅行社
            object = this.get(Edmticketproduct.class, productId);
        }
        if ("10".equals(providerpdtp)) {

        }
        return object;
    }

    /**
     * 根据服务商编号删除产品
     * Describe:
     * @auth:huangyuqi
     * @param providerId
     * return:void
     * Date:2011-9-27
     */
    public void deleteProduct(Long providerId){
        String hsql=" from Edmtickettypetab pri where pri.iscenicid="+providerId;
        List list = this.find(hsql);
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                Edmtickettypetab ticket = (Edmtickettypetab)list.get(i);
                this.delete(ticket);
            }
        }
    }
    /**
     * 根据产品编号删除其它数据
     * Describe:
     * @auth:huangyuqi
     * @param productid
     * return:void
     * Date:2011-10-4
     */
    public void deleteProductOhter(Long productid){

        String hsql="";
        List list =new ArrayList();
        //删除价格拆分表数据
        hsql =" from Edmticketcomposepricetab where id.icrowdkindpriceid in(select icrowdkindpriceid from Edmcrowdkindpricetab where itickettypeid="+productid+")";
        list = this.find(hsql);
        if(list!=null && list.size()>=1){
            for (int i = 0; i < list.size(); i++) {
                Edmticketcomposepricetab pricecompose =  (Edmticketcomposepricetab)list.get(i);
                this.delete(pricecompose);
            }
        }
        //删除价格表数据
        hsql=" from Edmcrowdkindpricetab pri where pri.itickettypeid="+productid;
        list = this.find(hsql);
        if(list.size()>0){
            for (int i = 0; i <list.size(); i++) {
                Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)list.get(i);
                delete(price);
            }
        }
    }
    /**
     * 删除产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param productId
     * @param providerPdtp
     * return:void
     * Date:2011-9-27
     */
    public void deletOtherProduct(Long productId,String providerPdtp){
        List list =null;
        String hsql="";
        if("06".equals(providerPdtp)){//酒店产品
            hsql=" from Hotelproduct where itickettypeid="+productId;
            list = this.find(hsql);
            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    Hotelproduct hotel = (Hotelproduct)list.get(i);
                    this.delete(hotel);
                }
            }
        }
        if("07".equals(providerPdtp)){//旅行社
            hsql=" from Lineproduct where itickettypeid="+productId;
            list = this.find(hsql);
            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    Lineproduct line = (Lineproduct)list.get(i);
                    this.delete(line);
                }
            }
        }
        if("10".equals(providerPdtp)){//租车
            hsql=" from Rentrlcartickettab where itickettypeid="+productId;
            list = this.find(hsql);
            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    Rentrlcartickettab rentrlcartickettab = (Rentrlcartickettab)list.get(i);
                    //有车辆租出不能删除。抛出异常
                    if (rentrlcartickettab.getCarloan()!=0) {
                        throw new RuntimeException();
                    }
                    //将车库数量修改回来
                    Rentrlcarvartab rentrlcarvartab = (Rentrlcarvartab) get(Rentrlcarvartab.class, rentrlcartickettab.getSeq());
                    rentrlcarvartab.setSzrentrlcarloan(rentrlcarvartab.getSzrentrlcarloan()-rentrlcartickettab.getCarnum());
                    this.saveOrUpdate(rentrlcarvartab);
                    this.delete(rentrlcartickettab);
                }
            }
        }
    }

    /**
     * 根据服务商删除产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param providerId服务商编号
     * @param providerPdtp服务商类型
     * return:void
     * Date:2011-9-27
     */
    public void deleteOtherProductByPdno(Long providerId,String providerPdtp){
        String hsql="";
        List list;
        if("06".equals(providerPdtp)){//酒店
            hsql=" from Hotelproduct hotel where hotel.itickettypeid in (select t.itickettypeid from Edmtickettypetab t where t.iscenicid="+providerId+")";
            list = this.find(hsql);
            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    Hotelproduct hotel = (Hotelproduct)list.get(i);
                    this.delete(hotel);
                }
            }
        }
        if("07".equals(providerPdtp)){//旅行社
            hsql=" from Lineproduct line where line.itickettypeid in (select t.itickettypeid from Edmtickettypetab t where t.iscenicid="+providerId+")";
            list = this.find(hsql);
            if(list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    Lineproduct line = (Lineproduct)list.get(i);
                    this.delete(line);
                }
            }
        }

    }

    /**
     * *
     * Describe:显示出某服务商下的产品信息（显示的产品的产品类别为产品附加服务）
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#quckListedmticket(java.lang.Long)
     * @param iscenicid
     * @return
     * @author lijingrui
     * Date:Feb 9, 2012
     */
    public List quckListedmticket(Long iscenicid){
        String sql=" from Edmtickettypetab edm where edm.bycategorytype='120' and edm.iscenicid="+iscenicid;
        List lst=this.find(sql);
        return lst;
    }

    /**
     * *
     * Describe:显示出某产品的附加产品信息
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#showAllhotelduct(java.lang.Long)
     * @param itickettypeid
     * @return
     * @author lijingrui
     * Date:Feb 9, 2012
     */
    public List showAllhotelduct(Long itickettypeid){
        String hsql=" from Hotelduct hd where hd.id.ductid="+itickettypeid;
        return this.find(hsql);
    }

    /**
     * *
     * Describe:显示出某产品的以附加产品的信息（查看）
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#quickviewhotelticket(java.lang.Long)
     * @param itickettypeid
     * @return
     * @author lijingrui
     * Date:Feb 10, 2012
     */
    public List quickviewhotelticket(Long itickettypeid){
        String sql=" from Edmtickettypetab edm where edm.itickettypeid in(select hd.id.hotelid from Hotelduct hd where hd.id.ductid="+itickettypeid+")";
        return this.find(sql);
    }

    public PaginationSupport queryPrintno(Long itickettypeid,String printno,String serialnumber,int page, int pagesize,String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(t.seq as seq,t.tickettypeid as tickettypeid,t.ticketPrintno as ticketPrintno," +
                "t.serialNumber as serialNumber,t.status as status,ticket.sztickettypename as sztickettypename) " +
                "from TicketPrintNo t,Edmtickettypetab ticket where t.tickettypeid = ticket.itickettypeid " +
                " and t.tickettypeid = "+itickettypeid);
        if(!StringUtils.isBlank(printno)){
            hsql.append(" and t.ticketPrintno like '%"+printno+"%' ");
        }
        if(!StringUtils.isBlank(serialnumber)){
            hsql.append(" and t.serialNumber = "+Long.parseLong(serialnumber));
        }
        hsql.append(" order by t.serialNumber desc");
        PaginationSupport ps = this.findPage(hsql.toString(),pagesize,page,url);
        return ps;
    }

    public void addPrintNo(TicketPrintNo printNo,Syslog syslog){
        String serialnumber = printNo.getTicketPrintno().substring(printNo.getSerialFirstIndex().intValue()-1,printNo.getSerialLastIndex().intValue());
        Long serial = 0L;
        try{
            serial = Long.parseLong(serialnumber);
        }catch (Exception e){
            throw new RuntimeException("流水号异常");
        }
        printNo.setSerialNumber(serial);
        printNo.setSeq(getMaxPk("seq","TicketPrintNo")+1L);
        this.save(printNo);
    }

    public void editPrintNo(TicketPrintNo t,Syslog syslog){
        TicketPrintNo printNo = (TicketPrintNo) this.get(TicketPrintNo.class,t.getSeq());
        String serialnumber = printNo.getTicketPrintno().substring(t.getSerialFirstIndex().intValue()-1,t.getSerialLastIndex().intValue());
        Long serial = 0L;
        try{
            serial = Long.parseLong(serialnumber);
        }catch (Exception e){
            throw new RuntimeException("流水号异常");
        }
        printNo.setSerialFirstIndex(t.getSerialFirstIndex());
        printNo.setSerialLastIndex(t.getSerialLastIndex());
        printNo.setStatus(t.getStatus());
        printNo.setSerialNumber(serial);
        this.update(printNo);
    }

	@Override
	public List<Edmticketcomposepricetab> getSonPrice(Long icrowkindid, Long itckettypeid) {
		String hql=" from Edmticketcomposepricetab where id.icrowdkindpriceid="+icrowkindid+" and itickettypeid="+itckettypeid+"";
		return find(hql);
	}

	@Override
	public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid,
			String startdate) {
		String hql = "from Edpofferschemetab where  icrowdkindid=" + icrowkind + "  and  iscenicid=" + iscenicid + " and ibusinessid=" + ibusinessid + " and itickettypeid=" + itickettypeid + " and startdata<='" + startdate + "' and enddata>='" + startdate + "' and byisuse=1 and ioffertype=0 ";
        System.out.println("-===>>" + hql);
        List list = find(hql);
        if (list != null && list.size() > 0) {
            return (Edpofferschemetab) list.get(0);
        } else {
/*		   String sql="from Edpofferschemetab where iscenicid="+iscenicid+" and startdata<='"+startdate+"' and enddata>='"+startdate+"' and byisuse=1 and ioffertype=1 ";
           List lst=this.find(sql);
		   if(lst!=null&&lst.size()>0){
			   return (Edpofferschemetab)lst.get(0);
		   }*/
            return null;
        }
	}

	@Override
	public Productcontrol getTripControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate) {
		// TODO Auto-generated method stub
		 String hql = " from Productcontrol where tripid=" + tripid + " and ivenueid=" + ivenueid + " and ivenueareaid=" + ivenueareaid + " and stdata='" + tourdate + "' and controltype='03'";
	        List list = find(hql);
	        if (list != null && list.size() > 0) {
	            Productcontrol control = (Productcontrol) list.get(0);
	            return control;
	        } else {
	            return null;
	        }
	}

	//根据票种id集合，查询票种信息
	@Override
	public List getTicketTypeListByIds(String itickettypeids) {
		
		String sql = "select distinct new map(ticket.itickettypeid as itickettypeid, ticket.sztickettypename as sztickettypename) from Edmtickettypetab ticket where ticket.itickettypeid in("+ itickettypeids +")";
		
		List list = this.find(sql);
		
		return list;
	}

	/**
	 * 票种下拉列表接口。
     * 组合了景区名称，格式为：景区名称_票种名称
	 */
	public List getTicketTypeSelect(String scenic) {
		
		String sql="";
		//根据服务商查出所有该服务商的票类型
		if (scenic!=null && !scenic.equals("")) {
			sql="select new map("
					+ " type.iscenicid as iscenicid, type.itickettypeid as itickettypeid, type.sztickettypename as sztickettypename"
					+ " ) "
					+ " from Edmtickettypetab type "
					+ " where type.byisuse=1 "
					+ " and type.iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01 and scenic.iscenicid in ("+ scenic +") )";
		}else {
			sql="select new map("
					+ " type.iscenicid as iscenicid, type.itickettypeid as itickettypeid, type.sztickettypename as sztickettypename"
					+ " ) "
					+ " from Edmtickettypetab type "
					+ " where type.byisuse=1 "
					+ " and type.iscenicid in (select scenic.iscenicid from Esbscenicareatab scenic where scenic.scenictype=01)";
			
		}
		
		sql=sql+" order by type.iscenicid, type.itickettypeid";
		List<Map> ticketS = this.find(sql);
		
		if(ticketS!=null && ticketS.size()>0) {
			for (Map ticket : ticketS) {
				String iscenicid = ticket.get("iscenicid").toString();
				Esbscenicareatab esbscen=(Esbscenicareatab)this.get(Esbscenicareatab.class, Long.valueOf(iscenicid));
				
				String sztickettypename = ticket.get("sztickettypename").toString();
				ticket.put("sztickettypename", esbscen.getSzscenicname()+ "_" +sztickettypename);
			}
			
		}
		
		return ticketS;
	}
	
	
	/**
	 * *
	 * Describe:显示出套票的子产品
	 * @see com.ectrip.system.permitenter.dao.idao.IOpwwicketSetDAO#getviewedmticket(java.lang.Long)
	 * @param itickettypeid
	 * @return
	 * @author lijingrui
	 * Date:2011-10-22
	 */
	public List getSonTicketTypeSelect(Long itickettypeid) {
		Edmtickettypetab edm=(Edmtickettypetab) this.get(Edmtickettypetab.class, itickettypeid);
		String sql = null;
		
		if(!edm.getBycategorytype().equals("0010")){
			sql=" select new map("
					+ " type.iscenicid as iscenicid, type.validityday as validityday, type.itickettypeid as itickettypeid, type.sztickettypename as sztickettypename"
					+ " ) "
					+ " from Edmtickettypetab type "
					+ " where type.byisuse=1 and type.itickettypeid="+itickettypeid;
		}else{
			sql=" select new map("
					+ " type.iscenicid as iscenicid, type.itickettypeid as itickettypeid, type.sztickettypename as sztickettypename"
					+ " ) "
					+ " from Edmtickettypetab type where type.byisuse=1 "
					+ " and type.itickettypeid in ("
					+ " select ed.itickettypeid  from Edmticketcomposepricetab ed where ed.id.icrowdkindpriceid in("
					+ " select icrowdkindpriceid from Edmcrowdkindpricetab where itickettypeid="+itickettypeid+") )";
		}
		
		List<Map> tickets = this.find(sql);
		
		return tickets;
	}
}

