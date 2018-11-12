package com.ectrip.ec.report.system.order.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.enums.DDZT;
import com.ectrip.base.util.MathUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.Reservecontrol;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.report.system.order.dao.idao.IQueryOrderDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;

@Repository
public class QueryOrderDAO extends GenericDao implements IQueryOrderDAO {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private SysparService sysparService;
	
    /**
     * 查询逾期未领的订单数 Describe:
     *
     * @auth:huangyuqi
     * @param esfemployeetab
     * @return return:int Date:2011-10-31
     */
    public int queryYuQiOrderNumb(Esfemployeetab esfemployeetab) {

        int numb = 0;

        String today = Tools.getDays();// 当前日期

        StringBuffer hsql = new StringBuffer();
        hsql.append(" from TOrder tor where tor.mont>0 and tor.dtstartdate<'" + today + "' and tor.ddzt='02' ");
        // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
        if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
            String scenics = esfemployeetab.getScenics();
            if (scenics == null || scenics.equals("")) {
                return 0;
            }
            hsql.append(" and tor.id.iscenicid in (" + scenics + ")  ");
        }

        List list = this.find(hsql.toString());
        numb = list.size();

        return numb;
    }

    /**
     * 根据条件查询订单 Describe:
     *
     * @auth:huangyuqi
     * @param esfemployeetab登录人信息
     * @param order订单查询条件类
     * @param page页码
     * @param pageSize毎页显示数
     * @param url访问地址
     * @return return:PaginationSupport Date:2011-10-31
     */
    public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
        PaginationSupport ps = null;

        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        
//        hsql.append("select distinct new map("
//        		+ " yor.id.orid as orid, yor.usid as usid, mor.orda as orda, yor.id.iscenicid as iscenicid, yor.mont as summont, yor.ddzt as ddzt,"
//        		+ " yor.tpmont as tpmont, yor.tpsx as tpsx, yor.iregionalid as iregionalid, yor.ornm as ornm, yor.orhm as orhm, yor.orph as orph,"
//        		+ " es.szscenicname as szscenicname, es.scenictype as scenictype,"
//        		+ " mor.payorid as payorid,"
//        		+ " us.corpname as corpname, "
//        		+ " v5.pmva as strddzt,"
//        		+ " t.ischupiao as ischupiao)"
//        		+ " from YOrder yor, MOrder mor, Custom us, Sysparv5 v5, Esbscenicareatab es, TOrder t ");
        
        hsql.append("select distinct new map("
        		+ " yor.id.orid as orid, yor.usid as usid, mor.orda as orda, yor.id.iscenicid as iscenicid, yor.mont as summont, yor.ddzt as ddzt,"
        		+ " yor.tpmont as tpmont, yor.tpsx as tpsx, yor.iregionalid as iregionalid, yor.ornm as ornm, yor.orhm as orhm, yor.orph as orph,"
        		+ " mor.payorid as payorid,"
        		+ " us.corpname as corpname,"
        		+ " t.ischupiao as ischupiao)"
        		+ " from YOrder yor, MOrder mor, Custom us, TOrder t ");
       
        
        where.append(" where (mor.ordersource != 'lykgp' or mor.ordersource is null) and mor.ortp='01' and t.id.orid = yor.id.orid and t.id.iscenicid = yor.id.iscenicid ");
        
        
        if (0 == order.getErrorsid()) {
            // 所有服务商
            if (0L == order.getIscenicid()) {
                // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
                if (esfemployeetab.getCompanytype().equals("02")) {
                    String scenics = esfemployeetab.getScenics();
                    where.append(" and yor.id.iscenicid  in (" + scenics + ") ");
                }
            } else {// 指定服务商
                where.append(" and yor.id.iscenicid =" + order.getIscenicid());
            }
            if(4 == order.getRadiobutton1()){//散客
                where.append(" and us.lgtp='01' ");
            }
            if (3 == order.getRadiobutton1()) {// 下单用户
                where.append(" and mor.usid='" + order.getUsid() + "' ");
            }
            if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1() || 5 == order.getRadiobutton1()) {// 分社
                // 总社  //OTA用户
                where.append(" and mor.usid in (" + order.getUsids() + ") ");
            }

            if (order.getDyusid() != null && !"".equals(order.getDyusid())) {// 导游
                where.append(" and yor.dyusid ='" + order.getDyusid() + "' ");
            }
            if (order.getStrornm() != null && !"".equals(order.getStrornm())) {// 导游名称模糊
                where.append(" and	(yor.ornm like '%" + order.getStrornm()
                        + "%' or us.corpname like '%"+order.getStrornm()+"%') ");
            }
            if (0 == order.getRadiobutton2()) {// 首日游览日期
                where.append(" and mor.stdt>='" + order.startDate + "' and mor.stdt<='" + order.getEndDate() + "' ");
            } else if (1 == order.getRadiobutton2()) {// 下订单日期
                where.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate() + "' ");
            } else if (2 == order.getRadiobutton2()) {// 支付日期日期
                where.append(" and mor.bankdata>='" + order.getStartDate() + "' and mor.bankdata<='" + order.getEndDate() + "' ");
            } else if (4 == order.getRadiobutton2()) {// 出票时间
                where.append(" and substr(yor.notec,1,10)>='" + order.getStartDate() + "' and substr(yor.notec,1,10) <='"
                        + order.getEndDate() + "' ");
            } else if (3 == order.getRadiobutton2()) {// 竹筏日期
                hsql.append(",TZorderlist tzl");
                where
                        .append(" and tzl.id.orid = yor.id.orid and tzl.id.iscenicid=yor.id.iscenicid and tzl.tripid>0 and substr(tzl.dtstartdate,1,10)>='"
                                + order.getStartDate() + "' and substr(tzl.dtstartdate,1,10)<='" + order.getEndDate() + "'");
            }
            if ("99".equals(order.getDdzt())) {// 所有
                where.append(" and yor.ddzt in ('00','02','11','18','21','20','27','30','95','98')");
            } else {
                if("11".equals(order.getDdzt())){
                    where.append(" and yor.ddzt in ('11','27') ");
                }else{
                    where.append(" and yor.ddzt ='" + order.getDdzt() + "'");
                }

            }

        }
        if (1 == order.getErrorsid()) {
            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
            if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
                String scenics = esfemployeetab.getScenics();
                where.append(" and yor.id.iscenicid in (" + scenics + ")  ");
            }
            if (0 == order.getRadiobutton3()) {// 订单号
                if (order.getOrid() != null && !"".equals(order.getOrid())) {
                    where.append(" and mor.orid='" + order.getOrid() + "' ");
                }
            } else if (1 == order.getRadiobutton3()) {// 联系人姓名
                if (order.getStrornm() != null && !"".equals(order.getStrornm())) {
                    where.append(" and yor.ornm like '%" + order.getStrornm() + "%' ");
                }
            } else if (2 == order.getRadiobutton3()) {// 联系人证件号码
                if (order.getOrhm() != null && !"".equals(order.getOrhm())) {
                    where.append(" and lower(yor.orhm) = lower('" + order.getOrhm() + "') ");
                }
            } else if (3 == order.getRadiobutton3()) {// 逾期未领订单
                where.append(" and yor.dtstartdate<'" + Tools.getDays() + "' and yor.ddzt='02' and yor.mont>0 ");
            } else if (4 == order.getRadiobutton3()) {// 行程单号
                if (order.getSztravelbillno() != null && !"".equals(order.getSztravelbillno())) {
                    where.append(" and yor.sztravelbillno ='" + order.getSztravelbillno() + "' ");
                }
            } else if (5 == order.getRadiobutton3()) { // 支付订单号
                if (order.getPayorid() != null && !"".equals(order.getPayorid())) {
                    where.append(" and mor.payorid='" + order.getPayorid() + "' ");
                }
            }
        }
        
//        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid");
        where.append(" order by yor.id.orid desc ");
        
        
        ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);

        
//		+ " es.szscenicname as szscenicname, es.scenictype as scenictype,"
//		+ " v5.pmva as strddzt,"
        
//        and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid
        
        
        List<Map> list = ps.getItems();
        if (list.size() >= 1) {
        	
        	StringBuilder iscenicidS = new StringBuilder();
        	for (Map map : list) {
        		//赋值系统参数
                String ddzt = map.get("ddzt").toString();
                String pmva = DDZT.getPmvaByPmcd(ddzt);
                map.put("strddzt", pmva);
        		
        		String iscenicid = map.get("iscenicid").toString();
        		iscenicidS.append(iscenicid+ ",");
			}
        	iscenicidS.deleteCharAt(iscenicidS.length() - 1);
        	
        	//根据id集合获取服务商列表
        	List<Map> providerList = ticketService.getProvidersByIds(iscenicidS.toString());
        	
            Map map = null;
            String orid = "";
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                
                
                String iscenicid = map.get("iscenicid").toString();
                for (Map provider : providerList) {
                	String iscenicid1 = provider.get("iscenicid").toString();
                	if(iscenicid.equals(iscenicid1)) {
                		String szscenicname = provider.get("szscenicname").toString();
                		String scenictype = provider.get("scenictype").toString();
                		
                		map.put("szscenicname", szscenicname);
                		map.put("scenictype", scenictype);
                	}
    			}
                
                
                
                if(map.get("orhm") != null && map.get("orhm").toString().length()>6) {//格式化身份证显示
                    map.put("orhm", StringUtil.markIdNumber((String) map.get("orhm")));
                }
                if (map.get("orid") != null) {// 订单号
                    orid = map.get("orid").toString();
                    Long cenicid = new Long(map.get("iscenicid").toString());
                    // String
//                    String sql = "select distinct new map("
//                    		+ " tzl.itickettypeid as itickettypeid, tzl.pric as pric, sum(tzl.numb) as numb, sum(tzl.amnt) as amnt, tzl.isj as isj,"
//                    		+ " tor.dtstartdate as dtstartdate,"
//                    		+ " prd.sztickettypename as sztickettypename )"
//                    		
//                    		+ " from TOrder tor, Sysparv5 sys1, Edmtickettypetab prd, TOrderlist tzl, MOrder mor  "
//                    		
//                    		+ " where ( mor.orid='"+ orid + "' or mor.srid='"+ orid + "' and mor.isg != 1 ) "
//                    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
//                            + " and tor.id.iscenicid = tzl.id.iscenicid and tzl.id.orid = tor.id.orid "
//                            
//                            + " and tzl.itickettypeid = prd.itickettypeid "
//                            + " and sys1.id.pmky='DDZT' and sys1.id.pmcd=tor.ddzt "
//                            
//                            + " group by tzl.itickettypeid, tor.dtstartdate, prd.sztickettypename, tzl.pric,tzl.isj order by tzl.isj desc";
                    
                    String sql = "select distinct new map("
                    		+ " tzl.itickettypeid as itickettypeid, tzl.pric as pric, sum(tzl.numb) as numb, sum(tzl.amnt) as amnt, tzl.isj as isj,"
                    		+ " tor.dtstartdate as dtstartdate )"
                    		
                    		+ " from TOrder tor, TOrderlist tzl, MOrder mor  "
                    		
                    		+ " where ( mor.orid='"+ orid + "' or mor.srid='"+ orid + "' and mor.isg != 1 ) "
                    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
                            + " and tor.id.iscenicid = tzl.id.iscenicid and tzl.id.orid = tor.id.orid "
                            
                            + " group by tzl.itickettypeid, tor.dtstartdate, tzl.pric,tzl.isj"
                            + " order by tzl.isj desc";
                    
                    
                    
                    List<Map> torderList = this.find(sql);
                    if (torderList.size() >= 1) {
                    	
                    	StringBuilder ticketTypeIds = new StringBuilder();
                    	
                        for (int j = 0; j < torderList.size(); j++) {
                            Map zmap = (Map) torderList.get(j);
                            if (null != zmap.get("isj")) {
                                if (Long.parseLong(zmap.get("isj").toString()) == -1) {
                                    zmap.put("numb", new Long(zmap.get("numb").toString()) * -1);
                                }
                            }
                           
                            String typeid = zmap.get("itickettypeid").toString();
                            ticketTypeIds.append(typeid);
                            ticketTypeIds.append(",");
                            
                        }
                        map.put("torderlists", torderList);
                        
                        
                      //根据票种id集合，查询票种信息
                        ticketTypeIds.deleteCharAt(ticketTypeIds.length() - 1);
                        List<Map> ticketTypeList = ticketService.getTicketTypeListByIds(ticketTypeIds.toString());
                        
                        for (Map torder : torderList) {
                        	String torder_itickettypeid = torder.get("itickettypeid").toString();
							
                        	for (Map ticketType : ticketTypeList) {
                        		String itickettypeid = ticketType.get("itickettypeid").toString();
                        		if(torder_itickettypeid.equals(itickettypeid)) {
                        			String sztickettypename = ticketType.get("sztickettypename").toString();
                        			torder.put("sztickettypename", sztickettypename);
                        		}
							}
						}
                    }

                }

                if(map.get("iregionalid")!=null){
                    Long iregionalid=Long.parseLong(map.get("iregionalid").toString());
//                    Galsourceregiontab galsource=(Galsourceregiontab)this.get(Galsourceregiontab.class, iregionalid);
                    Galsourceregiontab galsource = sysparService.getSourceregionById(iregionalid);
                    
                    
                    if(galsource.getSzinnername()!=null&&!galsource.getSzinnername().equals("")){
                        map.put("szinnername", galsource.getSzinnername());
                    }else{
                        map.put("szinnername", galsource.getSzregionalname());
                    }

                }else{
                    TOrder t=(TOrder)this.get(TOrder.class, new TOrderId(map.get("orid").toString(), Long.parseLong(map.get("iscenicid").toString())));
                    if(t.getIregionalid()!=null&&!t.getIregionalid().toString().equals("")){
//                        Galsourceregiontab galsource=(Galsourceregiontab)this.get(Galsourceregiontab.class, t.getIregionalid());
                    	Galsourceregiontab galsource = sysparService.getSourceregionById(t.getIregionalid());
                        if(galsource.getSzinnername()!=null&&!galsource.getSzinnername().trim().equals("")){
                            map.put("szinnername", galsource.getSzinnername());
                        }else{
                            map.put("szinnername", galsource.getSzregionalname());
                        }
                    }else{
                        map.put("szinnername", "");
                    }

                }

            }
        }

        return ps;
    }

    public List queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order){
        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hsql.append("select distinct new map( yor.id.orid as orid,yor.usid as usid,us.corpname as corpname,mor.orda as orda,yor.id.iscenicid as iscenicid,es.szscenicname as szscenicname,es.scenictype as scenictype,yor.ornm as ornm,mor.payorid as payorid ,yor.orhm as orhm,yor.orph as orph,yor.ddzt as ddzt,v5.pmva as strddzt,nvl(yor.mont,0) as summont,nvl(yor.tpmont,0) as tpmont,yor.tpsx as tpsx ) from YOrder yor,MOrder mor,Custom us,Sysparv5 v5,Esbscenicareatab es ");
        where.append(" where mor.ortp='01' ");
        if (0 == order.getErrorsid()) {
            // 所有服务商
            if (0L == order.getIscenicid()) {
                // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
                if (esfemployeetab.getCompanytype().equals("02")) {
                    String scenics = esfemployeetab.getScenics();
                    where.append(" and yor.id.iscenicid  in (" + scenics + ") ");
                }
            } else {// 指定服务商
                where.append(" and yor.id.iscenicid =" + order.getIscenicid());
            }
            if(4 == order.getRadiobutton1()){//散客
                where.append(" and us.lgtp='01' ");
            }
            if (3 == order.getRadiobutton1()) {// 下单用户
                where.append(" and mor.usid='" + order.getUsid() + "' ");
            }
            if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1() || 5 == order.getRadiobutton1()) {// 分社
                // 总社  //OTA用户
                where.append(" and mor.usid in (" + order.getUsids() + ") ");
            }

            if (order.getDyusid() != null && !"".equals(order.getDyusid())) {// 导游
                where.append(" and yor.dyusid ='" + order.getDyusid() + "' ");
            }
            if (order.getStrornm() != null && !"".equals(order.getStrornm())) {// 导游名称模糊
                where.append(" and	(yor.ornm like '%" + order.getStrornm()
                        + "%' or us.corpname like '%"+order.getStrornm()+"%') ");
            }
            if (0 == order.getRadiobutton2()) {// 首日游览日期
                where.append(" and mor.stdt>='" + order.startDate
                        + "' and mor.stdt<='" + order.getEndDate() + "' ");
            } else if (1 == order.getRadiobutton2()) {// 下订单日期
                where.append(" and mor.orda>='" + order.getStartDate()
                        + "' and mor.orda<='" + order.getEndDate() + "' ");
            } else if (2 == order.getRadiobutton2()) {// 支付日期日期
                where.append(" and mor.bankdata>='" + order.getStartDate()
                        + "' and mor.bankdata<='" + order.getEndDate() + "' ");
            } else if (4 == order.getRadiobutton2()) {// 出票时间
                where.append(" and substr(yor.notec,1,10)>='"
                        + order.getStartDate()
                        + "' and substr(yor.notec,1,10) <='"
                        + order.getEndDate() + "' ");
            } else if (3 == order.getRadiobutton2()) {// 竹筏日期
                hsql.append(",TZorderlist tzl");
                where.append(" and tzl.id.orid = yor.id.orid and tzl.id.iscenicid=yor.id.iscenicid and tzl.tripid>0 and substr(tzl.dtstartdate,1,10)>='"
                        + order.getStartDate()
                        + "' and substr(tzl.dtstartdate,1,10)<='"
                        + order.getEndDate() + "'");
            }
            if ("99".equals(order.getDdzt())) {// 所有
                where.append(" and yor.ddzt in ('00','02','11','18','21','20','30','95','98')");
            } else {
                where.append(" and yor.ddzt ='" + order.getDdzt() + "'");
            }

        }
        if (1 == order.getErrorsid()) {
            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
            if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
                String scenics = esfemployeetab.getScenics();
                where.append(" and yor.id.iscenicid in (" + scenics + ")  ");
            }
            if (0 == order.getRadiobutton3()) {// 订单号
                if (order.getOrid() != null && !"".equals(order.getOrid())) {
                    where.append(" and mor.orid='" + order.getOrid() + "' ");
                }
            } else if (1 == order.getRadiobutton3()) {// 联系人姓名
                if (order.getStrornm() != null
                        && !"".equals(order.getStrornm())) {
                    where.append(" and yor.ornm like '%" + order.getStrornm()
                            + "%' ");
                }
            } else if (2 == order.getRadiobutton3()) {// 联系人证件号码
                if (order.getOrhm() != null && !"".equals(order.getOrhm())) {
                    where.append(" and lower(yor.orhm) = lower('"
                            + order.getOrhm() + "') ");
                }
            } else if (3 == order.getRadiobutton3()) {// 逾期未领订单
                where.append(" and yor.dtstartdate<'" + Tools.getDays()
                        + "' and yor.ddzt='02' and yor.mont>0 ");
            } else if (4 == order.getRadiobutton3()) {// 行程单号
                if (order.getSztravelbillno() != null
                        && !"".equals(order.getSztravelbillno())) {
                    where.append(" and yor.sztravelbillno ='"
                            + order.getSztravelbillno() + "' ");
                }
            } else if (5 == order.getRadiobutton3()) { // 支付订单号
                if (order.getPayorid() != null
                        && !"".equals(order.getPayorid())) {
                    where.append(" and mor.payorid='" + order.getPayorid()
                            + "' ");
                }
            }
        }
        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
        where.append(" order by yor.id.orid desc ");
        System.out.println(hsql.toString() + where.toString());
        List list = this.find(hsql.toString() + where.toString());

        if (list != null && !list.isEmpty()) {
            Map map = null;
            String orid = "";
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                if (map.get("orid") != null) {// 订单号
                    orid = map.get("orid").toString();
                    Long cenicid = new Long(map.get("iscenicid").toString());
                    // String
                    String sql = "select distinct new map(tzl.itickettypeid as itickettypeid,tor.dtstartdate as dtstartdate,prd.sztickettypename as sztickettypename,tzl.pric as pric,sum(tzl.numb) as numb,sum(tzl.amnt) as amnt,tzl.isj as isj )from TOrder tor,Sysparv5 sys1,Edmtickettypetab prd,TOrderlist tzl,MOrder mor  where  ( mor.orid='"
                            + orid
                            + "' or mor.srid='"
                            + orid
                            + "' and mor.isg != 1 ) and mor.orid=tor.id.orid and tor.id.iscenicid="
                            + cenicid
                            + " and sys1.id.pmky='DDZT'  and tor.id.iscenicid = tzl.id.iscenicid  and sys1.id.pmcd=tor.ddzt  and tzl.id.orid = tor.id.orid and tzl.itickettypeid = prd.itickettypeid group by tzl.itickettypeid,tor.dtstartdate,prd.sztickettypename,tzl.pric,tzl.isj order by tzl.isj desc";
                    List torderList = this.find(sql);
                    if (torderList.size() >= 1) {
                        for (int j = 0; j < torderList.size(); j++) {
                            Map zmap = (Map) torderList.get(j);
                            if (null != zmap.get("isj")) {
                                if (Long.parseLong(zmap.get("isj").toString()) == -1) {
                                    zmap.put("numb", new Long(zmap.get("numb")
                                            .toString()) * -1);
                                }
                            }
                        }
                        map.put("torderlists", torderList);
                    }

                }
            }
        }

        return list;
    }

    /**
     * 根据条件查询退订单 Describe:
     *
     * @auth:huangyuqi
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-11-1
     */
    public PaginationSupport queryAllMsOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append("select distinct new map( mor.orid as orid,mor.srid as srid,tl.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,mor.usid as usid,us.corpname as corpname,mor.ddzt as ddzt,mor.orda as orda,mor.zfmont as zfmont,mor.tpmont as tpmont,mor.stdt as stdt,mor.tpsx as tpsx,mor.tpdate as tpdate,mor.tpmont as tpmont,mor.tpfs as tpfs,v5.pmva as strtdlb,mor.ddzt as ddzt,v51.pmva as strtpfs,v52.pmva as strddzt) from MOrder mor,");
        if (0 == order.getErrorsid()) {
            if (2 == order.getRadiobutton2()) {
                hsql.append("YZorderlist tl,");
            } else {
                hsql.append("YOrderlist tl,");
            }
        } else {
            hsql.append("YOrderlist tl,");
        }
        hsql
                .append("Esbscenicareatab pro,Custom us,Sysparv5 v5,Sysparv5 v51,Sysparv5 v52 where  mor.ortp in ('02','03') and  mor.ddzt in ('06','88') and tl.id.iscenicid = pro.iscenicid  ");

        if (0 == order.getErrorsid()) {
            // 所有服务商
            if (0L == order.getIscenicid()) {
                // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
                if (esfemployeetab.getCompanytype().equals("02")) {
                    String scenics = esfemployeetab.getScenics();
                    hsql.append(" and tl.id.iscenicid in (" + scenics + ") ");
                }
            } else {// 指定服务商
                hsql.append(" and tl.id.iscenicid =" + order.getIscenicid());
            }

            if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1()) {
                hsql.append(" and mor.usid in (" + order.getUsids() + ") ");
            }
            if (3 == order.getRadiobutton1()) {// 导游
                hsql.append(" and mor.usid ='" + order.getUsid() + "' ");
            }
            if (!order.getTpfs().equals("99")) {
                hsql.append(" and mor.tpfs ='" + order.getTpfs() + "'");
            }
            if (!order.getTdlb().equals("99")) {
                hsql.append(" and mor.notef ='" + order.getTdlb() + "' ");
            }
            if (0 == order.getRadiobutton2()) {// 消费（游览）日期
                hsql.append(" and mor.stdt>='" + order.startDate + "' and mor.stdt<='" + order.getEndDate() + "' ");
            } else if (1 == order.getRadiobutton2()) {// 退订单日期
                hsql.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate() + "' ");
            } else if (2 == order.getRadiobutton2()) {// 竹筏日期
                hsql.append(" and substr(tl.dtstartdate,1,10)>='" + order.getStartDate() + "' and substr(tl.dtstartdate,1,10)<='"
                        + order.getEndDate() + "' ");
            }
        }
        if (1 == order.getErrorsid()) {
            if (0 == order.getRadiobutton3()) {// 退订单号
                hsql.append(" and mor.orid='" + order.getXrid() + "' ");
            }
            if (1 == order.getRadiobutton3()) {// 原始订单号
                hsql.append(" and mor.srid='" + order.getOrid() + "' ");
            }

            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
            if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
                String scenics = esfemployeetab.getScenics();
                hsql.append(" and tl.id.iscenicid in (" + scenics + ")  ");
            }

        }
        hsql
                .append(" and mor.orid = tl.id.orid and  mor.usid = us.usid and v5.id.pmky='TDLB' and v5.id.pmcd=mor.notef and v51.id.pmky='TPFS' and v51.id.pmcd=mor.tpfs and v52.id.pmky='DDZT' and v52.id.pmcd=mor.ddzt");
        hsql.append(" order by mor.orid desc ");
        ps = this.findPage(hsql.toString(), pageSize, page, url);

        return ps;
    }

    /**
     * 查询停排订单 Describe:
     *
     * @auth:huangyuqi
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return return:PaginationSupport Date:2011-11-4
     */
    public PaginationSupport queryTingPaiOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        hsql
                .append(" select distinct new map(mor.orid as orid,mor.usid as usid,cu.corpname as corpname,mor.orda as orda,sys1.pmva as strddzt ) from MOrder mor,Custom cu,TOrderlist tl,Productcontrol prcon,Sysparv5 sys1  where prcon.bystate=-1 and mor.usid = cu.usid and  mor.orid = tl.id.orid and  tl.id.iscenicid = prcon.iscenicid and sys1.id.pmcd = mor.ddzt and sys1.id.pmky='DDZT' ");

        if (1 == order.getRadiobutton1()) {// 下单人
            hsql.append(" and mor.usid ='" + order.getUsid() + "' ");
        } else if (2 == order.getRadiobutton1()) {// 主用户
            hsql.append(" and mor.usid in (" + order.getUsids() + ") ");
        }

        if (0 == order.getRadiobutton2()) {// 消费日期
            hsql.append(" and prcon.stdata>='" + order.getStartDate() + "' and prcon.stdata<='" + order.getEndDate() + "' ");
        }

        hsql.append(" and mor.ortp<>'02' ");

        // 所有服务商
        if (0L == order.getIscenicid()) {
            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
            if (esfemployeetab.getCompanytype().equals("02")) {
                String scenics = esfemployeetab.getScenics();
                hsql.append(" and tl.id.iscenicid in (" + scenics + ") ");
            }
        } else {// 指定服务商
            hsql.append(" and tl.id.iscenicid =" + order.getIscenicid());
        }

        hsql.append(" order by mor.orid desc ");

        ps = this.findPage(hsql.toString(), pageSize, page, url);

        List list = ps.getItems();
        if (list.size() >= 1) {
            Map map = null;
            String orid = "";
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                if (map.get("orid") != null) {// 订单号
                    orid = map.get("orid").toString();
                    String sql = "select new map(tor.id.iscenicid as iscenicid,pro.szscenicname as szscenicname,tor.ddzt as tddzt,tor.dtstartdate as dtstartdate,tor.mont as tmont,tl.pric as pric,tl.numb as numb,tl.yhnumb as yhnumb,tl.amnt as amnt,tl.yhamnt as yhamnt,sys1.pmva as strtddzt)from TOrder tor, TOrderlist tl,Esbscenicareatab pro,Sysparv5 sys1,Edmtickettypetab prd where tor.id.orid='"
                            + orid
                            + "' and tor.id.iscenicid = tl.id.iscenicid and tl.id.orid = tor.id.orid and prd.itickettypeid = tl.itickettypeid and prd.bycategorytype in('0003','0010') and pro.iscenicid = tor.id.iscenicid and  sys1.id.pmky='DDZT' and sys1.id.pmcd=tor.ddzt ";
                    List torderList = this.find(sql);
                    if (torderList.size() >= 1) {
                        map.put("torderlists", torderList);
                    }
                }
            }
        }

        return ps;
    }

    /**
     * * Describe:获取所有的申请单
     *
     * @see com.ectrip.report.system.order.dao.idao.IQueryOrderDAO#queryAllAuditOrder(int, int, java.lang.String)
     * @param pagesize
     * @param startIndex
     * @param url
     * @return
     * @author yangguang Date:2012-1-7
     */
    public PaginationSupport queryAllAuditOrder(String iscenicid, int pagesize, int startIndex, String url, QueryOrder order) {
        StringBuffer hql = new StringBuffer(
                "select new map(t.id.orid as orid,t.id.iscenicid as iscenicid,t.scenictype as scenictype,t.orfl as orfl,t.usid as usid,t.ibusinessid as ibusinessid,t.sztravelbillno as sztravelbillno,t.iregionalid as iregionalid,t.tdlx as tdlx,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.dyusid as dyusid,t.ornm as ornm,t.orzj as orzj,t.orhm as orhm,t.orph as orph,t.mont as mont,t.zfmont as zfmont,t.youfei as youfei,sys.pmva as strddzt,cus.corpname as corpname,p.szscenicname as szscenicname,m.stdt as stdt,m.orda as orda) from TOrder t,Sysparv5 sys,Custom cus,Esbscenicareatab p,MOrder m where m.orid=t.id.orid and  p.iscenicid=t.id.iscenicid and cus.usid=t.usid and t.ddzt='23' and t.ddzt=sys.id.pmcd and sys.id.pmky='DDZT' and t.orfl='02'");
        if (order.getRadiobutton1() == 2) {
            if (order.getUsid() != null && !order.getUsid().equals("")) {
                hql.append(" and cus.corpname like '%" + order.getUsid() + "%'");
            }
        } else if (order.getRadiobutton1() == 1) {
            if (order.getUsid() != null && !order.getUsid().equals("")) {
                hql.append(" and m.usid like '%" + order.getUsid() + "%'");
            }
        }
        if (order.getRadiobutton2() == 0) {
            if (order.getStartDate() != null && !order.getStartDate().equals("") && order.getEndDate() != null
                    && !order.getEndDate().equals("")) {
                hql.append(" and m.stdt>='" + order.getStartDate() + "' and m.stdt<='" + order.getEndDate() + "'");
            }
        } else if (order.getRadiobutton2() == 1) {
            if (order.getStartDate() != null && !order.getStartDate().equals("") && order.getEndDate() != null
                    && !order.getEndDate().equals("")) {
                hql.append(" and m.bankdata>='" + order.getStartDate() + "' and m.stdt<='" + order.getEndDate() + "'");
            }
        }
        if (order.getRadiobutton3() == 1) {
            hql.append(" and m.orid in (select id.orid from TZorderlist where iztickettypeid=" + order.getItickettypeid() + " and tripid="
                    + order.getTripid() + " and subStr(dtstartdate,0,10)='" + order.getDateQuery() + "'");
            if (iscenicid != null && iscenicid.equals("")) {
                hql.append("and id.iscenicid in (" + iscenicid + ")");
            }
            hql.append("group by orid)");
        }
        hql.append(" order by t.id.orid desc");
        return findPage(hql.toString(), pagesize, startIndex, url);
    }

    public List queryTOrderlistDetail(String orid, String iscenicid) {
        StringBuffer hql = new StringBuffer();
        hql
                .append("select new map(t.id.orid as orid,t.id.orderlistid as orderlistid,t.id.iscenicid as iscenicid,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.pric as pric,t.numb as numb,t.amnt as amnt,e.sztickettypename as sztickettypename,kind.szcrowdkindname as szcrowdkindname,(select substr(z.dtstartdate,0,10) from TZorderlist z where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharfdate,(select substr(z.dtstartdate,12,5) from TZorderlist z where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharftime,(select trip.tripname from TZorderlist z,Trip trip where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid and z.tripid=trip.tripid) as tripname,(select v1.ivenueareaname from TZorderlist z,Venuearea v1,Venue v2 where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid and z.ivenueid=v1.ivenueid and z.ivenueareaid=v1.ivenueareaid) as wharfname) from TOrderlist t,Edmtickettypetab e,Edpcrowdkindtab kind where kind.icrowdkindid=t.icrowdkindid and t.id.orid = '"
                        + orid + "' and t.itickettypeid=e.itickettypeid");
        if (iscenicid != null && !iscenicid.equals("")) {
            hql.append(" and t.id.iscenicid in (" + iscenicid + ")");
        }
        return find(hql.toString());
    }

    /**
     *
     * Describe:获取torder
     *
     * @auth:yangguang
     * @param orid
     * @param iscenicid
     * @return return:List Date:2012-1-7
     */
    public List getTOrderDetail(String orid, String iscenicid) {
        StringBuffer hql = new StringBuffer(
                "select new map(t.id.orid as orid,t.id.iscenicid as iscenicid,e.szscenicname as szscenicname,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.mont as mont,t.zfmont as zfmont,t.orph as orph,t.ornm as ornm,t.orhm as orhm) from TOrder t,Esbscenicareatab e where t.id.orid='"
                        + orid + "' and t.id.iscenicid=e.iscenicid");
        if (iscenicid != null && !iscenicid.equals("")) {
            hql.append(" and t.id.iscenicid in (" + iscenicid + ")");
        }
        return find(hql.toString());
    }

    /**
     *
     * Describe:根据订单获取趟次分组 及 数量
     *
     * @auth:yangguang
     * @param orid
     * @param iscenicid
     * @return return:List Date:2012-1-8
     */
    public List getTripNumberByOrid(String orid, String iscenicid) {
        StringBuffer hql = new StringBuffer(
                "select new map(sum(z.znumb) as num,trip.tripid as tripid,z.ivenueid as ivenueid,z.ivenueareaid as ivenueareaid,trip.tripname as tripname,z.iztickettypeid as ticketid,z.dtstartdate as dtstartdate) from TZorderlist z,MOrder m,Trip trip where z.id.orid='"
                        + orid + "' and z.id.orid=m.orid and m.ddzt='23' and trip.tripid=z.tripid ");
        if (iscenicid != null && !iscenicid.equals("")) {
            hql.append(" and z.id.iscenicid in (" + iscenicid + ")");
        }
        hql.append("group by trip.tripname, z.dtstartdate,trip.tripid,z.ivenueid,z.ivenueareaid,z.iztickettypeid order by z.dtstartdate");
        return find(hql.toString());
    }

    /**
     * * Describe:根据产品日期 趟次获取数量控制
     *
     * @see com.ectrip.report.system.order.dao.idao.IQueryOrderDAO#getProductControlByTripInfo(java.lang.String, java.lang.String,
     *      java.lang.String)
     * @param date
     * @param tripid
     * @param itickettypeid
     * @return
     * @author yangguang Date:2012-1-8
     */
    public Productcontrol getProductControlByTripInfo(String date, String tripid, String itickettypeid) {
        StringBuffer hql = new StringBuffer("from Productcontrol where tripid=" + tripid + " and stdata='" + date.substring(0, 10)
                + "' and itickettypeid=" + itickettypeid + "");
        List list = find(hql.toString());
        if (list != null) {
            return (Productcontrol) list.get(0);
        } else {
            return null;
        }
    }

    public void updateAuditOrderDdzt(String orid, String iscenicid, Long employeeid, String ddzt, String tporid) throws Exception {
        String hql1 = " from TOrder where id.orid='" + orid + "'";
        String hql2 = " from YOrder where id.orid='" + orid + "'";
        String hql3 = "";
        String hql4 = "";
        try {
            List tordelists = null;
            YOrder tpyorder = null;
            YOrderlist yorderlist = null;
            TOrderlist torderlist = null;
            List tzordelrists = null;
            List list = null;
            TOrder torder = null;
            TZorderlist tzorderlist = null;
            YZorderlist yzorderlist = null;
            MOrder morder = (MOrder) this.get(MOrder.class, orid);
            morder.setDdzt(ddzt);
            if (!ddzt.equals("02")) {
                morder.setTpdate(Tools.getDays());
                morder.setTpmont(morder.getZfmont());
                morder.setTpfs("02");
                morder.setNotea("26");
            } else {
                morder.setNotea("27");
            }
            this.update(morder);
            if (!ddzt.equals("02")) {
                MOrder tdmorder = copyPropertyReturnMorder(morder, tporid);
                tdmorder.setOrtp("02");
                tdmorder.setDdzt("06");
                tdmorder.setTpfs("02");
                tdmorder.setNotef("05");
                tdmorder.setIsf(1l);
                save(tdmorder);
            }
            list = this.find(hql1);// torder
            for (int i = 0; i < list.size(); i++) {
                torder = (TOrder) list.get(i);
                torder.setDdzt(ddzt);
                this.update(torder);
                if (!ddzt.equals("02")) {
                    tpyorder = copyPropertyReturnYorder(torder, employeeid.toString());
                    tpyorder.setId(new YOrderId(tporid, torder.getId().getIscenicid()));
                    tpyorder.setOrid(tporid);
                    tpyorder.setDdzt("06");
                    tpyorder.setNotef("05");
                    tpyorder.setIsf(1l);
                    save(tpyorder);
                    hql3 = " from TOrderlist where id.orid='" + torder.getId().getOrid() + "' and id.iscenicid="
                            + torder.getId().getIscenicid() + "";
                    tordelists = find(hql3);
                    for (int j = 0; j < tordelists.size(); j++) {
                        torderlist = (TOrderlist) tordelists.get(j);
                        yorderlist = copyPropertyReturnYOrderlist(torderlist);
                        yorderlist.setId(new YOrderlistId(new Long(j + 1), tpyorder.getId().getOrid(), tpyorder.getId().getIscenicid()));
                        save(yorderlist);
                        hql4 = "from TZorderlist where id.orid='" + torderlist.getId().getOrid() + "' and id.orderlistid="
                                + torderlist.getId().getOrderlistid() + " and id.iscenicid=" + torderlist.getId().getIscenicid() + "";
                        tzordelrists = find(hql4);
                        for (int x = 0; x < tzordelrists.size(); x++) {
                            tzorderlist = (TZorderlist) tzordelrists.get(x);
                            yzorderlist = copyPropertyReturnYOrderlist(tzorderlist);
                            yzorderlist.setId(new YZorderlistId(new Long(x + 1), yorderlist.getId().getOrderlistid(), yorderlist.getId()
                                    .getOrid(), yorderlist.getId().getIscenicid()));
                            save(yzorderlist);
                        }
                    }
                }
            }
            List list1 = this.find(hql2);// yorder
            YOrder yorder = null;
            for (int j = 0; j < list1.size(); j++) {
                yorder = (YOrder) list1.get(j);
                yorder.setDdzt(ddzt);
                yorder.setNotea(ddzt);
                this.update(yorder);
            }
            // 0156 新增订单 0157 修改订单 0158 删除订单
            Orderlog log = new Orderlog();
            log.setLogid(getMaxPk("logid", "Orderlog") + 1);
            log.setOrid(morder.getOrid());
            if (ddzt.equals("02")) {
                log.setStlg("0188");
                log.setBrief("申请单审核成功");
                log.setNote("申请单审核成功");
            } else {
                log.setStlg("0189");
                log.setBrief("申请单审核失败");
                log.setNote("申请单审核失败");
            }
            log.setIemployeeid(employeeid);
            log.setUsid(morder.getUsid());
            log.setLogtype(Long.parseLong("0"));
            log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
            save(log);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public YOrder copyPropertyReturnYorder(TOrder torder, String employeeid) {
        YOrder tpyorder = new YOrder();
        tpyorder.setId(new YOrderId());
        tpyorder.setScenictype("01");
        tpyorder.setUsid(torder.getUsid());
        tpyorder.setIbusinessid(torder.getIbusinessid());
        tpyorder.setSztravelbillno(torder.getSztravelbillno());
        tpyorder.setIregionalid(torder.getIregionalid());
        tpyorder.setTdlx(torder.getTdlx());
        tpyorder.setDtstartdate(torder.getDtstartdate());
        tpyorder.setDtenddate(torder.getDtenddate());
        tpyorder.setDyusid(torder.getDyusid());
        tpyorder.setOrnm(torder.getOrhm());
        tpyorder.setOrzj(torder.getOrzj());
        tpyorder.setOrhm(torder.getOrhm());
        tpyorder.setFaxno(torder.getFaxno());
        tpyorder.setOrph(torder.getOrph());
        tpyorder.setMont(torder.getMont());
        tpyorder.setYhamnt(0.0);
        tpyorder.setZfmont(torder.getZfmont());
        tpyorder.setYoufei(0.0);
        tpyorder.setFempid(employeeid);
        return tpyorder;
    }

    public MOrder copyPropertyReturnMorder(MOrder morder, String orid) throws IllegalAccessException, InvocationTargetException {
        MOrder tdmorder = new MOrder();
        BeanUtils.copyProperties(tdmorder, morder);
        tdmorder.setOrid(orid);
        tdmorder.setOrti(Tools.getNowTime());
        tdmorder.setZffs("08");
        tdmorder.setOrda(Tools.getDays());
        tdmorder.setIsjl(0l);
        tdmorder.setYhamnt(0.0);
        tdmorder.setIsallcp(0l);
        tdmorder.setBankdata(Tools.getDays());
        tdmorder.setBanktime(Tools.getNowTime());
        tdmorder.setSrid(morder.getOrid());
        tdmorder.setTpdate(Tools.getDays());
        tdmorder.setTpfs("02");
        tdmorder.setTpsx(0.0);
        tdmorder.setTpfl(0.0);
        tdmorder.setNote("订单审核未通过");
        return tdmorder;
    }

    public YOrderlist copyPropertyReturnYOrderlist(TOrderlist torderlist) {
        YOrderlist tpyorderlist = new YOrderlist();
        tpyorderlist.setItickettypeid(torderlist.getItickettypeid());
        tpyorderlist.setIcrowdkindpriceid(torderlist.getIcrowdkindpriceid());
        tpyorderlist.setIcrowdkindid(torderlist.getIcrowdkindid());
        tpyorderlist.setDtstartdate(torderlist.getDtstartdate());
        tpyorderlist.setDtenddate(torderlist.getDtenddate());
        tpyorderlist.setPric(torderlist.getPric());
        tpyorderlist.setNumb(torderlist.getNumb());
        tpyorderlist.setYhnumb(0l);
        tpyorderlist.setAmnt(torderlist.getAmnt());
        tpyorderlist.setYhamnt(0.0);
        tpyorderlist.setDtenddate(torderlist.getDtenddate());
        tpyorderlist.setDtenddate(torderlist.getDtenddate());
        tpyorderlist.setDtenddate(torderlist.getDtenddate());
        return tpyorderlist;
    }

    public YZorderlist copyPropertyReturnYOrderlist(TZorderlist tzorderlist) {
        YZorderlist tpzlist = new YZorderlist();
        tpzlist.setIcrowdkindpriceid(tzorderlist.getIcrowdkindpriceid());
        tpzlist.setIcrowdkindid(tzorderlist.getIcrowdkindid());
        tpzlist.setItickettypeid(tzorderlist.getItickettypeid());
        tpzlist.setIztickettypeid(tzorderlist.getIztickettypeid());
        tpzlist.setDtstartdate(tzorderlist.getDtstartdate());
        tpzlist.setDtenddate(tzorderlist.getDtenddate());
        tpzlist.setTripid(tzorderlist.getTripid());
        tpzlist.setIvenueid(tzorderlist.getIvenueid());
        tpzlist.setIvenueareaid(tzorderlist.getIvenueareaid());
        tpzlist.setIvenueseatsid(tzorderlist.getIvenueseatsid());
        tpzlist.setZpric(tzorderlist.getZpric());
        tpzlist.setZyhnumb(0l);
        tpzlist.setZyhamnt(0.0);
        tpzlist.setZamnt(tzorderlist.getZamnt());
        tpzlist.setJsprice(tzorderlist.getJsprice());
        tpzlist.setZnumb(tzorderlist.getZnumb());
        tpzlist.setSqnumber(tzorderlist.getZnumb());
        return tpzlist;
    }

    public PaginationSupport getOrderByRaftTrip(String orid, Long itickettypeid, String date, Long tripid, Long iscenicid, int pageSize,
                                                int starIndex, String url, String zfzt) {
        StringBuffer hsql = new StringBuffer();
        hsql
                .append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,m.notea as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz,Custom c,Sysparv5 v5 where yz.iztickettypeid="
                        + itickettypeid);
        if (iscenicid != null && iscenicid.equals("")) {
            hsql.append(" and y.id.iscenicid in (" + iscenicid + ")");
        }
        hsql
                .append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='ZFZT' and v5.id.pmcd=m.notea and m.zfzt='02'");
        if (orid != null && !orid.equals("")) {
            hsql.append(" and m.orid='" + orid + "'");
        } else {
            hsql.append(" and yz.tripid=" + tripid + " and subStr(0,10,yz.dtstartdate)=" + date);
        }
        hsql
                .append(" group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,m.notea,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
        return this.findPage(hsql.toString(), pageSize, starIndex, url);
    }

    public PaginationSupport getOrderByRaftTrip(String orid, String itickettypeid, String date, Long tripid, String iscenicid,
                                                int pageSize, int starIndex, String url, String zfzt) {
        StringBuffer hsql = new StringBuffer();
        hsql
                .append("select m.orid as orid0,m.usid as usid1,c.corpname as corpname2,c.lname as lname3,c.lgtp as lgtp4,m.orda as orda5,m.orti as orti6,m.stdt as stdt7,m.notea as notea8,v5.pmva as strnotea9,y.ornm as ornm10,y.orhm as orhm11,yz.dtstartdate as dtstartdate12,sum(yz.znumb) as znumb13 from MOrder m,TOrder y,TZorderlist yz,Custom c,Sysparv5 v5 where yz.iztickettypeid="
                        + itickettypeid);
        if (iscenicid != null && !iscenicid.equals("")) {
            hsql.append(" y.id.iscenicid in (" + iscenicid + ")");
        }
        hsql
                .append(" and m.orid=y.id.orid  and m.usid=c.usid and y.id.orid=yz.id.orid and y.id.iscenicid=yz.id.iscenicid and v5.id.pmky='ZFZT' and v5.id.pmcd=m.ddzt and m.ddzt='02'");
        if (orid != null && !orid.equals("")) {
            hsql.append(" and m.orid='" + orid + "'");
        } else {
            if (tripid != null && !tripid.equals("") && date != null && !date.equals("")) {
                hsql.append(" and yz.tripid=" + tripid + " and subStr(yz.dtstartdate,0,10)='" + date + "'");
            }
        }
        hsql
                .append(" group by  m.orid,m.usid,c.corpname,c.lname,c.lgtp,m.orda,m.orti,m.stdt,m.notea,v5.pmva,y.ornm,y.orhm,yz.dtstartdate order by m.orda desc,m.orti desc");
        return this.findPage(hsql.toString(), pageSize, starIndex, url);
    }

    public List getTZorderlist(String orid) {
        String hql = "from TZorderlist where id.orid='" + orid + "' and tripid!=0";
        return find(hql);
    }

    public List getTZorderlistByOrid(String orid) {
        String hql = "select new map(sum(tzlist.znumb) as znumb,tzlist.tripid as tripid,tzlist.ivenueid as ivenueid,tzlist.ivenueareaid as ivenueareaid,tzlist.ivenueseatsid as ivenueseatsid,tzlist.id.iscenicid  as iscenicid,tzlist.id.orid as orid,tzlist.iztickettypeid as iztickettypeid,tzlist.dtstartdate as   dtstartdate) from TZorderlist tzlist where tzlist.id.orid='"
                + orid
                + "' group by  tzlist.tripid,tzlist.ivenueid,tzlist.ivenueareaid,tzlist.id.iscenicid,tzlist.id.orid,tzlist.ivenueseatsid,tzlist.iztickettypeid,tzlist.dtstartdate";
        return find(hql);
    }

    /**
     *
     * Describe:已售量返还
     *
     * @auth:yangguang
     * @param orid
     * @param zorderlist
     * @return return:boolean Date:2011-11-15
     */
    public void updateStock(String orid, TZorderlist zorderlist) {
        StringBuffer hql = new StringBuffer();
        Edmtickettypetab ticket = (Edmtickettypetab) get(Edmtickettypetab.class, zorderlist.getIztickettypeid());
        if (ticket.getBycategorytype().equals("0003")) {// 竹筏票
            hql.append(" from Productcontrol where iscenicid=" + zorderlist.getIscenicid() + " and itickettypeid="
                    + zorderlist.getIztickettypeid() + " and stdata='" + zorderlist.getDtstartdate().substring(0, 10)
                    + "' and controltype='03'");
            if (zorderlist != null && !zorderlist.getTripid().equals("") && zorderlist.getTripid().intValue() != 0) {
                hql.append(" and tripid=" + zorderlist.getTripid().intValue() + "");
            }
            List list = find(hql.toString());
            Productcontrol p = null;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    p = (Productcontrol) list.get(i);
                    p.setSoldnumber(p.getSoldnumber() - zorderlist.getZnumb());
                    this.update(p);
                }
            }

        }
    }

    /**
     * * Describe:更新日控制
     *
     * @see com.ectrip.order.dao.idao.IPayOrderDAO#updateDayControl(java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     *      int)
     * @param orid
     * @param date
     * @param itickettypeid
     * @param iscenicid
     * @param numb
     * @return
     * @author yangguang Date:2011-11-18
     */
    public void updateDayControl(String orid, String date, String itickettypeid, String iscenicid, int numb) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from Productcontrol where iscenicid=" + iscenicid + " and itickettypeid=" + itickettypeid + " and stdata='" + date
                + "' and controltype='02'");
        List list = this.find(hql.toString());
        Productcontrol p = null;
        if (list != null && list.size() > 0) {
            for (int j = 0; j < list.size(); j++) {
                p = (Productcontrol) list.get(j);
                p.setSoldnumber(p.getSoldnumber() - numb);
                this.update(p);
            }
        }
    }

    public List getTorderlists(Long iscenicid, String orid) {
        String hql = "from TOrderlist where id.orid='" + orid + "' and id.iscenicid=" + iscenicid + "";
        return find(hql);
    }

    public List getTzorderlists(Long iscenicid, String orid, Long orderlistid) {
        String hql = "from TZorderlist where id.orid='" + orid + "' and id.iscenicid=" + iscenicid + " and id.orderlistid=" + orderlistid
                + "";
        return find(hql);
    }

    public List getAuditNum(QueryOrder order) {
        String hql = new String(
                "select new map(sum(z.znumb) as numb,z.tripid as tripid,v.ivenueareaname as ivenueareaname,t.tripname as tripname, p.starttime as starttime,c.stdata as startdata,c.productcontrolid as productcontrolid) from TZorderlist z,MOrder m,Trip t,Venuearea v,Prdtripvenuemanage p,Productcontrol c  where z.iztickettypeid = 1 and subStr(dtstartdate, 0, 10)='"
                        + order.getDateQuery()
                        + "' and z.id.orid = m.orid and m.ddzt = '23' and z.tripid = t.tripid and z.tripid = p.tripid and z.ivenueid = p.ivenueid  and z.ivenueareaid = p.ivenueareaid and p.startdata <= subStr(dtstartdate, 0, 10) and p.enddata >= subStr(dtstartdate, 0, 10) and c.stdata = subStr(dtstartdate, 0, 10)  and c.itickettypeid = z.iztickettypeid  and c.tripid = z.tripid and c.ivenueid = z.ivenueid  and c.ivenueareaid = z.ivenueareaid and z.ivenueareaid = v.ivenueareaid group by z.tripid,z.itickettypeid, z.ivenueareaid,v.ivenueareaname,t.tripname,p.starttime,c.stdata, c.productcontrolid  order by p.starttime");
        List list=find(hql);
        Map map=null;
        for(int i=0;i<list.size();i++){
            map=(Map) list.get(i);
            Reservecontrol reserve=getLxsreserveGroup(Long.parseLong(map.get("productcontrolid").toString()));
            if(reserve!=null){
                map.put("cansale", reserve.getReservednumber()-reserve.getReservedsalenumber());
            }else{
                map.put("cansale", 0);
            }
        }
        return list;
    }

    public List getAllbussiness() {
        String hsql="select new map(b.ibusinessid as ibusinessid,b.szbusinesscode as szbusinesscode,b.szbusinessname as szbusinessname,b.businesstype as businesstype,b.isregister as isregister,b.bymarketingway as bymarketingway,b.bywithmember as bywithmember,b.byconfirmemberway as byconfirmemberway,b.btouristsaddr as btouristsaddr,b.bregsalesman as bregsalesman,b.bteamnature as bteamnature,b.bselfcollarselling as bselfcollarselling,b.bticketnounited as bticketnounited,b.byisuse as byisuse ) from Edmbusinesstab b where b.byisuse=1 order by b.ibusinessid,b.szbusinesscode";
        List list=find(hsql);
        if(list!=null&&list.size()>0&&list.get(0)!=null){
            return list;
        }else{
            return null;
        }
    }


    /**
     *
     * Describe:获取旅行社预留量分配
     * @auth:yangguang
     * @param ibssiness
     * @param usid
     * @param productcontrolid
     * @return
     * return:Reservecontrol
     * Date:2012-4-18
     */
    public Reservecontrol getLxsreserve(Long ibssiness,String usid,Long productcontrolid){
        String hql="from Reservecontrol where productcontrolid="+productcontrolid+" and ibusinessid="+ibssiness+"";
        if(usid!=null&&!usid.equals("")){
            hql+=" and usid='"+usid+"'";
        }
        List list=find(hql);
        if(list!=null&&list.size()>0&&list.get(0)!=null){
            return (Reservecontrol) list.get(0);
        }else{
            return null;
        }
    }

    private Reservecontrol getLxsreserveGroup(Long productcontrolid){
        String hql="from Reservecontrol where productcontrolid="+productcontrolid+" and  usid is null";
        List list=find(hql);
        if(list!=null&&list.size()>0&&list.get(0)!=null){
            return (Reservecontrol) list.get(0);
        }else{
            return null;
        }
    }

    /**
     * *
     * Describe:现场支付订单查询
     * @see com.ectrip.report.system.order.service.iservice.IQueryOrderService#xczfQueryAllOrder(com.ectrip.model.employee.Esfemployeetab, com.ectrip.model.order.QueryOrder, int, int, java.lang.String)
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return
     * @author lijingrui
     * Date:2014-3-11
     */
    public PaginationSupport xczfQueryAllOrder(Esfemployeetab esfemployeetab,QueryOrder order,int page,int pageSize,String url){
        PaginationSupport ps = null;

        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hsql
                .append("select distinct new map( yor.id.orid as orid,yor.usid as usid,us.corpname as corpname,mor.orda as orda,yor.id.iscenicid as iscenicid,es.szscenicname as szscenicname,es.scenictype as scenictype,yor.ornm as ornm,mor.payorid as payorid ,yor.orhm as orhm,yor.orph as orph,yor.ddzt as ddzt,v5.pmva as strddzt,yor.mont as summont,yor.tpmont as tpmont,yor.tpsx as tpsx ) from YOrder yor,MOrder mor,Custom us,Sysparv5 v5,Esbscenicareatab es ");
        where.append(" where mor.ortp='01' and mor.zffs='05' ");

        // 所有服务商
        if (0L == order.getIscenicid()) {
            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
            if (esfemployeetab.getCompanytype().equals("02")) {
                String scenics = esfemployeetab.getScenics();
                where.append(" and yor.id.iscenicid  in (" + scenics + ") ");
            }

        } else {// 指定服务商
            where.append(" and yor.id.iscenicid =" + order.getIscenicid());
        }

        if (3 == order.getRadiobutton1()) {// 下单用户
            where.append(" and mor.usid='" + order.getUsid() + "' ");
        }
        if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1()) {// 分社
            // 总社
            where.append(" and mor.usid in (" + order.getUsids() + ") ");
        }

        if (0 == order.getRadiobutton2()) {// 首日游览日期
            where.append(" and mor.stdt>='" + order.startDate + "' and mor.stdt<='" + order.getEndDate() + "' ");
        } else if (1 == order.getRadiobutton2()) {// 下订单日期
            where.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate() + "' ");
        } else if (2 == order.getRadiobutton2()) {// 支付日期日期
            where.append(" and mor.bankdata>='" + order.getStartDate() + "' and mor.bankdata<='" + order.getEndDate() + "' ");
        } else if (4 == order.getRadiobutton2()) {// 出票时间
            where.append(" and substr(yor.notec,1,10)>='" + order.getStartDate() + "' and substr(yor.notec,1,10) <='"
                    + order.getEndDate() + "' ");
        }
        if ("99".equals(order.getDdzt())) {// 所有
            where.append(" and yor.ddzt in ('00','11')");
        } else {
            where.append(" and yor.ddzt ='" + order.getDdzt() + "'");
        }

        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
        where.append(" order by yor.id.orid desc ");
        System.out.println("=========="+hsql.toString() + where.toString());
        ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);

        List list = ps.getItems();
        if (list.size() >= 1) {
            Map map = null;
            String orid = "";
            for (int i = 0; i < list.size(); i++) {

                map = (Map) list.get(i);
                if(map.get("orhm") != null) {//格式化身份证显示
                    map.put("orhm", StringUtil.markIdNumber((String) map.get("orhm")));
                }
                if (map.get("orid") != null) {// 订单号
                    orid = map.get("orid").toString();
                    Long cenicid = new Long(map.get("iscenicid").toString());
                    // String
                    String sql = "select distinct new map(tzl.itickettypeid as itickettypeid,tor.dtstartdate as dtstartdate,prd.sztickettypename as sztickettypename,tzl.pric as pric,tzl.numb as numb,tzl.amnt as amnt,tzl.isj as isj )from TOrder tor,Sysparv5 sys1,Edmtickettypetab prd,TOrderlist tzl,MOrder mor  where  ( mor.orid='"
                            + orid
                            + "' or mor.srid='"
                            + orid
                            + "') and mor.orid=tor.id.orid and tor.id.iscenicid="
                            + cenicid
                            + " and sys1.id.pmky='DDZT'  and tor.id.iscenicid = tzl.id.iscenicid  and sys1.id.pmcd=tor.ddzt  and tzl.id.orid = tor.id.orid and tzl.itickettypeid = prd.itickettypeid order by tzl.isj desc";
                    List torderList = this.find(sql);
                    if (torderList.size() >= 1) {
                        for (int j = 0; j < torderList.size(); j++) {
                            Map zmap = (Map) torderList.get(j);
                            if (null != zmap.get("isj")) {
                                if (Long.parseLong(zmap.get("isj").toString()) == -1) {
                                    zmap.put("numb", new Long(zmap.get("numb").toString()) * -1);
                                }
                            }
                        }
                        map.put("torderlists", torderList);
                    }

                }

            }
        }

        return ps;
    }
    /**
     *
     * Describe:订单人数统计
     * @author:zhangwubin
     * @param esfemployeetab
     * @param order
     * @param page
     * @param pageSize
     * @param url
     * @return
     * return:PaginationSupport
     * Date:2015-1-28
     */
    public PaginationSupport orderNumbQuery(Esfemployeetab esfemployeetab,
                                            QueryOrder order, int page, int pageSize, String url) {
        PaginationSupport ps = null;

        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hsql.append("select new map(m.orid as orid,v5.pmva as ddzt,sum(yl.numb) as cnumb,c.corpname as corpname) from MOrder m,YOrder y,YOrderlist yl,Sysparv5 v5,Custom c where m.orid = y.id.orid and y.id.orid = yl.id.orid and m.ortp='01' and v5.id.pmky='DDZT' and v5.id.pmcd=m.ddzt and m.usid = c.usid ");
        if (order.getOrid() != null && !"".equals(order.getOrid())) {
            where.append(" and m.orid='" + order.getOrid() + "' ");
        } else {
            if (3 == order.getRadiobutton1()) {// 下单用户
                where.append(" and m.usid='" + order.getUsid() + "' ");
            }
            if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1()) {// 分社
                // 总社
                where.append(" and m.usid in (" + order.getUsids() + ") ");
            }
            if (0 == order.getRadiobutton2()) {// 首日游览日期
                where.append(" and m.stdt>='" + order.startDate
                        + "' and m.stdt<='" + order.getEndDate() + "' ");
            } else if (1 == order.getRadiobutton2()) {// 下订单日期
                where.append(" and m.orda>='" + order.getStartDate()
                        + "' and m.orda<='" + order.getEndDate() + "' ");
            } else if (2 == order.getRadiobutton2()) {// 支付日期日期
                where.append(" and m.bankdata>='" + order.getStartDate()
                        + "' and m.bankdata<='" + order.getEndDate() + "' ");
            } else if (4 == order.getRadiobutton2()) {// 出票时间
                where.append(" and substr(y.notec,1,10)>='"
                        + order.getStartDate()
                        + "' and substr(y.notec,1,10) <='" + order.getEndDate()
                        + "' ");
            }
            if ("99".equals(order.getDdzt())) {// 所有
                where.append(" and m.ddzt in ('00','02','11','18','21','20','95','98','27')");
            } else {
                where.append(" and m.ddzt ='" + order.getDdzt() + "'");
            }
        }

        where.append("group by m.orid,v5.pmva, c.corpname  order by m.orid desc");
        ps = this.findPage(hsql.toString() + where.toString(), pageSize, page,
                url);
        if(ps!=null){
            List list = ps.getItems();
            if(list!=null && !list.isEmpty()){
                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    String orid = map.get("orid").toString();
                    String sql = "SELECT NVL(SUM(CASE WHEN M.ORTP='02' THEN -YL.NUMB ELSE YL.NUMB END),0) AS BNUMB FROM M_ORDER M,Y_ORDERLIST YL WHERE M.ORID=YL.ORID AND M.SRID = ?";
                    try {
                        List qlist = this.findBySqlToMap(sql, orid);
                        map.put("bnumb", Long.parseLong(((Map)qlist.get(0)).get("BNUMB").toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ps;
    }

    /**
     * 导出excel操作，获取订单信息
     * Describe:
     * @author:xiaogaoxiang
     * @param esfemployeetab
     * @param order
     * @return
     * return:List
     * Date:2014-10-6
     */
    public List getAllOrderInfo(Esfemployeetab esfemployeetab, QueryOrder order) {
        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hsql
                .append("select distinct new map( yor.id.orid as orid,yor.usid as usid,us.corpname as corpname,mor.orda as orda,yor.id.iscenicid as iscenicid,es.szscenicname as szscenicname,es.scenictype as scenictype,yor.ornm as ornm,mor.payorid as payorid ,yor.orhm as orhm,yor.orph as orph,yor.ddzt as ddzt,v5.pmva as strddzt,yor.mont as summont,yor.tpmont as tpmont,yor.tpsx as tpsx ) from YOrder yor,MOrder mor,Custom us,Sysparv5 v5,Esbscenicareatab es ");
        where.append(" where mor.ortp='01' ");
        if (0 == order.getErrorsid()) {
            // 所有服务商
            if (order.getIscenicid()==null || 0L == order.getIscenicid()) {
                // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
                if (esfemployeetab.getCompanytype().equals("02")) {
                    String scenics = esfemployeetab.getScenics();
                    where.append(" and yor.id.iscenicid  in (" + scenics + ") ");
                }
            } else {// 指定服务商
                where.append(" and yor.id.iscenicid =" + order.getIscenicid());
            }

            if (3 == order.getRadiobutton1()) {// 下单用户
                where.append(" and mor.usid='" + order.getUsid() + "' ");
            }
            if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1()) { // 分社
                // 总社
                where.append(" and mor.usid in (" + order.getUsids() + ") ");
            }

            if (order.getDyusid() != null && !"".equals(order.getDyusid())) { // 导游
                where.append(" and yor.dyusid ='" + order.getDyusid() + "' ");
            }
            if (order.getStrornm() != null && !"".equals(order.getStrornm())) { // 导游名称模糊
                where.append(" and	yor.ornm like ('%" + order.getStrornm() + "%') ");
            }
            if (0 == order.getRadiobutton2()) { // 首日游览日期
                where.append(" and mor.stdt>='" + order.startDate
                        + "' and mor.stdt<='" + order.getEndDate() + "' ");
            } else if (1 == order.getRadiobutton2()) { // 下订单日期
                where.append(" and mor.orda>='" + order.getStartDate()
                        + "' and mor.orda<='" + order.getEndDate() + "' ");
            } else if (2 == order.getRadiobutton2()) { // 支付日期日期
                where.append(" and mor.bankdata>='" + order.getStartDate()
                        + "' and mor.bankdata<='" + order.getEndDate() + "' ");
            } else if (4 == order.getRadiobutton2()) { // 出票时间
                where.append(" and substr(yor.notec,1,10)>='"
                        + order.getStartDate()
                        + "' and substr(yor.notec,1,10) <='"
                        + order.getEndDate() + "' ");
            } else if (3 == order.getRadiobutton2()) { // 竹筏日期
                hsql.append(",TZorderlist tzl");
                where.append(" and tzl.id.orid = yor.id.orid and tzl.id.iscenicid=yor.id.iscenicid and tzl.tripid>0 and substr(tzl.dtstartdate,1,10)>='"
                        + order.getStartDate()
                        + "' and substr(tzl.dtstartdate,1,10)<='"
                        + order.getEndDate() + "'");
            }
            if ("99".equals(order.getDdzt())) { // 所有
                where.append(" and yor.ddzt in ('00','02','11','18','21','20','95','98')");
            } else {
                where.append(" and yor.ddzt ='" + order.getDdzt() + "'");
            }
        }
        if (1 == order.getErrorsid()) {
            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
            if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
                String scenics = esfemployeetab.getScenics();
                where.append(" and yor.id.iscenicid in (" + scenics + ")  ");
            }
            if (0 == order.getRadiobutton3()) {// 订单号
                if (order.getOrid() != null && !"".equals(order.getOrid())) {
                    where.append(" and mor.orid='" + order.getOrid() + "' ");
                }
            } else if (1 == order.getRadiobutton3()) {// 联系人姓名
                if (order.getStrornm() != null
                        && !"".equals(order.getStrornm())) {
                    where.append(" and yor.ornm like '%" + order.getStrornm() + "%' ");
                }
            } else if (2 == order.getRadiobutton3()) {// 联系人证件号码
                if (order.getOrhm() != null && !"".equals(order.getOrhm())) {
                    where.append(" and lower(yor.orhm) = lower('"
                            + order.getOrhm() + "') ");
                }
            } else if (3 == order.getRadiobutton3()) {// 逾期未领订单
                where.append(" and yor.dtstartdate<'" + Tools.getDays()
                        + "' and yor.ddzt='02' and yor.mont>0 ");
            } else if (4 == order.getRadiobutton3()) {// 行程单号
                if (order.getSztravelbillno() != null
                        && !"".equals(order.getSztravelbillno())) {
                    where.append(" and yor.sztravelbillno ='"
                            + order.getSztravelbillno() + "' ");
                }
            } else if (5 == order.getRadiobutton3()) { // 支付订单号
                if (order.getPayorid() != null
                        && !"".equals(order.getPayorid())) {
                    where.append(" and mor.payorid='" + order.getPayorid()
                            + "' ");
                }
            }
        }
        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
        where.append(" order by yor.id.orid desc ");
        List list = this.find(hsql.toString() + where.toString());
        if (list.size() >= 1) {
            Map map = null;
            String orid = "";
            for (int i = 0; i < list.size(); i++) {
                map = (Map) list.get(i);
                if (map.get("orid") != null) {// 订单号
                    orid = map.get("orid").toString();
                    Long cenicid = new Long(map.get("iscenicid").toString());

                    TOrder torder = (TOrder) this.get(TOrder.class,
                            new TOrderId(orid, cenicid));
                    if (torder != null) {
                        Long iregionalid = torder.getIregionalid();
                        if (iregionalid != null && !"".equals(iregionalid)) {
                            Galsourceregiontab regional = (Galsourceregiontab) this
                                    .get(Galsourceregiontab.class, iregionalid);
                            if (regional != null) {
                                map.put("szregionalname",regional.getSzregionalname());
                            }
                        }
                    }

                    // String
                    String sql = "select distinct new map(tzl.icrowdkindid as icrowdkindid,p.szcrowdkindname as szcrowdkindname,tzl.itickettypeid as itickettypeid,tor.dtstartdate as dtstartdate,prd.sztickettypename as sztickettypename,tzl.pric as pric,sum(tzl.numb) as numb,sum(tzl.amnt) as amnt,tzl.isj as isj,nvl(tzl.jsprice,0) as jsprice)from Edpcrowdkindtab p,TOrder tor,Sysparv5 sys1,Edmtickettypetab prd,TOrderlist tzl,MOrder mor  where  ( mor.orid='"
                            + orid
                            + "' or mor.srid='"
                            + orid
                            + "') and mor.orid=tor.id.orid and tor.id.iscenicid="
                            + cenicid
                            + " and tzl.icrowdkindid=p.icrowdkindid and sys1.id.pmky='DDZT'  and tor.id.iscenicid = tzl.id.iscenicid  and sys1.id.pmcd=tor.ddzt  and tzl.id.orid = tor.id.orid and tzl.itickettypeid = prd.itickettypeid group by tzl.icrowdkindid,p.szcrowdkindname,tzl.itickettypeid,tor.dtstartdate,prd.sztickettypename,tzl.pric,tzl.isj,tzl.jsprice order by tzl.isj desc";
                    List torderList = this.find(sql);
                    if (torderList.size() >= 1) {
                        double totalprice = 0;
                        double jsprice = 0;
                        for (int j = 0; j < torderList.size(); j++) {
                            Map zmap = (Map) torderList.get(j);
                            if (null != zmap.get("isj")) {
                                if (Long.parseLong(zmap.get("isj").toString()) == -1) {
                                    zmap.put("numb", new Long(zmap.get("numb").toString()) * -1);
                                }
                            }

                            totalprice += Double.parseDouble(zmap.get("pric")
                                    .toString())
                                    * Long.parseLong(zmap.get("numb")
                                    .toString());
                            jsprice = MathUtil.add(jsprice,MathUtil.multiply(Double.parseDouble(zmap.get("jsprice").toString()), Long.parseLong(zmap.get("numb").toString())));
                        }
                        map.put("torderlists", torderList);
                        map.put("totalprice", totalprice);
                        map.put("totaljsprice", jsprice);
                    }else {
                        torderList = null;
                        map.put("torderlists", torderList);
                        map.put("totalprice", "");
                        map.put("totalprice", "");
                    }

                }
            }
        }

        return list;
    }


}
