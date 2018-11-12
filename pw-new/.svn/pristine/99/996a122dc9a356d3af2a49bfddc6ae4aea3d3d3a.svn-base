package com.ectrip.ec.order.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.feign.service.TourCardService;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.order.dao.idao.ITourcardTicketOrderDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Galsourceregiontab;

@Repository
public class TourcardTicketOrderDAO extends GenericDao implements ITourcardTicketOrderDAO{
	
	@Autowired
	private SysparService sysparService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private TourCardService tourCardService;
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PaginationSupport queryOrderInfoByPage(Esfemployeetab esfemployeetab, QueryOrder order, 
			int page, int pageSize, String url,
			String cardNumber, String cardName, String profitNum) throws RuntimeException, Exception {

        PaginationSupport ps = null;

        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        hsql.append("select distinct new map( yor.id.orid as orid, yor.usid as usid, yor.id.iscenicid as iscenicid, yor.ornm as ornm,yor.orhm as orhm,yor.orph as orph,"
        		+ " yor.ddzt as ddzt, yor.mont as summont, yor.tpmont as tpmont, yor.tpsx as tpsx, yor.iregionalid as iregionalid,"
        		+ " us.corpname as corpname,"
        		+ " mor.notee as notee, mor.orda as orda, mor.payorid as payorid,"
        		+ " t.ischupiao as ischupiao)"
        		+ " from YOrder yor, MOrder mor, Custom us, TOrder t ");
        
        where.append(" where mor.ordersource='lykgp' and mor.ortp='01' and t.id.orid=yor.id.orid and t.id.iscenicid=yor.id.iscenicid ");
        
//        hsql.append("select distinct new map( "
//        		+ " yor.id.orid as orid, yor.usid as usid, yor.id.iscenicid as iscenicid, yor.ornm as ornm,yor.orhm as orhm,yor.orph as orph,"
//        		+ " yor.ddzt as ddzt, yor.mont as summont, yor.tpmont as tpmont, yor.tpsx as tpsx, yor.iregionalid as iregionalid,"
//        		
//        		+ " us.corpname as corpname,"
//        		+ " mor.orda as orda, mor.payorid as payorid,"
//        		+ " es.szscenicname as szscenicname, es.scenictype as scenictype,"
//        		+ " v5.pmva as strddzt,"
//        		+ " t.ischupiao as ischupiao,"
//        		+ " card.cardNumber as cardNumber,card.profitNum as profitNum"
//        		+ " ) "
//        		+ " from TourCardDetail card, YOrder yor, MOrder mor, Custom us, Sysparv5 v5, Esbscenicareatab es, TOrder t "
//        		);
//        
//        where.append(" where card.cardNumber = mor.notee and mor.ordersource = 'lykgp' and mor.ortp='01' and t.id.orid = yor.id.orid and t.id.iscenicid = yor.id.iscenicid ");
       
        
        
        
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
        
        //先从旅游卡服务查询满足条件的旅游卡明细，并将其cardNumber集合作为订单的查询条件
        List<Map> cardDetailList = tourCardService.getCardDetailListAll(cardNumber, cardName, profitNum);
        if(cardDetailList!=null && cardDetailList.size()>0) {
        	StringBuilder cardNumbers = new StringBuilder();
            for (Map map : cardDetailList) {
            	Object number = map.get("cardNumber");
            	cardNumbers.append("'"+ number +"'");
            	cardNumbers.append(",");
    		}
            cardNumbers.deleteCharAt(cardNumbers.length()-1);//去除最后一个逗号
        	
            where.append(" and mor.notee in ("+ cardNumbers +")");
        }else {
        	where.append(" and 1=2");//表示连表查询无数据
		}
        
        
//        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
        where.append(" and us.usid = mor.usid and mor.orid = yor.id.orid ");
        where.append(" order by yor.id.orid desc ");
        
        ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);
        
        List<Map> itemList = ps.getItems();
        
        if(itemList!=null && itemList.size()>0) {
        	StringBuilder sysparaPmcds = new StringBuilder();
            StringBuilder providerIscenicids = new StringBuilder();
            
            //循环遍历，进行旅游卡明细信息的赋值
            for (Map item : itemList) {
            	String notee = item.get("notee").toString();
            	
            	for (Map cardDetail : cardDetailList) {
            		String detailCardNumber = cardDetail.get("cardNumber").toString();
            		
            		if(notee.equals(detailCardNumber)) {
            			String detailProfitNum = cardDetail.get("profitNum").toString();
            			item.put("cardNumber", detailCardNumber);
            			item.put("profitNum", detailProfitNum);
            			
            			break;
            		}
    			}
            	
            	
            	//获取系统参数查询条件
            	String ddzt = item.get("ddzt").toString();
            	sysparaPmcds.append(ddzt + ",");
            	//获取服务商查询条件
            	String iscenicid = item.get("iscenicid").toString();
            	providerIscenicids.append(iscenicid + ",");
    		}
            
            
            //从系统服务查询系统参数相关数据
            sysparaPmcds.deleteCharAt(sysparaPmcds.length() - 1);//去除最后一个逗号
            List<Map> sysparamList = sysparService.getSysparamsByPmcds(sysparaPmcds.toString());
            for (Map item : itemList) {
            	String ddzt = item.get("ddzt").toString();
            	
            	for (Map sysparam : sysparamList) {
            		String pmcd = sysparam.get("pmcd").toString();
            		
            		if(ddzt.equals(pmcd)) {
            			String strddzt = sysparam.get("strddzt").toString();
            			item.put("strddzt", strddzt);
            			
            			break;
            		}
    			}
    		}
            
            
            //从票务服务查询服务商相关数据
            providerIscenicids.deleteCharAt(providerIscenicids.length()-1);//去除最后一个逗号
    		List<Map> providerList = ticketService.getProvidersByIds(providerIscenicids.toString());
    		for (Map item : itemList) {
    		    String iscenicid = item.get("iscenicid").toString();
    			
    			for (Map provider : providerList) {
    				String provider_iscenicid = provider.get("iscenicid").toString();
    				
    				if(iscenicid.equals(provider_iscenicid)) {
    					String szscenicname = provider.get("szscenicname").toString();
    					String scenictype = provider.get("scenictype").toString();
    					item.put("szscenicname", szscenicname);
    					item.put("scenictype", scenictype);
    					
    					break;
    				}
    			}
    		}
        }
        

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
                    /*String sql = "select distinct new map("
                    		+ " tzl.itickettypeid as itickettypeid, "
                    		+ " tor.dtstartdate as dtstartdate,"
                    		+ " prd.sztickettypename as sztickettypename,"
                    		+ " tzl.pric as pric, sum(tzl.numb) as numb, sum(tzl.amnt) as amnt, tzl.isj as isj "
                    		+ " )"
                    		+ " from TOrder tor, Sysparv5 sys1, Edmtickettypetab prd, TOrderlist tzl, MOrder mor"
                    		
                    		+ " where ( mor.orid='"+ orid +"' or mor.srid='"+ orid +"' and mor.isg != 1 )"
                    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
                    		
                            + " and sys1.id.pmky='DDZT' and sys1.id.pmcd=tor.ddzt and tor.id.iscenicid = tzl.id.iscenicid and tzl.id.orid = tor.id.orid "
                            + " and tzl.itickettypeid = prd.itickettypeid "
                            + " group by tzl.itickettypeid, tor.dtstartdate, prd.sztickettypename, tzl.pric, tzl.isj "
                            + " order by tzl.isj desc";*/
                    
                    
                    //根据原始sql分析可得：连表Sysparv5是为了加条件sys1.id.pmky='DDZT'，所以拆分sql后需要先查询Sysparv5来作为条件
                    //而连表Edmtickettypetab是为了取值，所以可在主sql查询之后再做处理
                    
                    List<Map> sysparList = sysparService.queryByPmkyPmcds("DDZT", "pmcd not in('1234567')");//利用一个不存在的参数值来忽略pmcd参数条件，达到查询所有DDZT的系统参数
                    StringBuilder pmcd_ddzts = new StringBuilder();
                    for (Map syspar : sysparList) {
                    	String pmcd = syspar.get("pmcd").toString();
                    	pmcd_ddzts.append("'"+ pmcd + "'");
                    	pmcd_ddzts.append(",");
					}
                    pmcd_ddzts.deleteCharAt(pmcd_ddzts.length() - 1);//去除最后一个逗号
                    
                    
                    String sql = "select distinct new map(tzl.itickettypeid as itickettypeid,"
                    		+ " tor.dtstartdate as dtstartdate,"
                    		+ " tzl.pric as pric, sum(tzl.numb) as numb, sum(tzl.amnt) as amnt, tzl.isj as isj )"
                    		+ " from TOrder tor, TOrderlist tzl, MOrder mor"
                    		
                    		+ " where ( mor.orid='"+ orid +"' or mor.srid='"+ orid +"' and mor.isg != 1 )"
                    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
                    		+ " and tor.ddzt in ("+ pmcd_ddzts +")"//等值查询改为in查询
                    		
                            + " and tor.id.iscenicid = tzl.id.iscenicid and tzl.id.orid = tor.id.orid "
                            + " group by tzl.itickettypeid, tor.dtstartdate, tzl.pric, tzl.isj "
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
    public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize, String url) throws Exception{
        PaginationSupport ps = null;

        StringBuffer hsql = new StringBuffer();
        StringBuffer where = new StringBuffer();
        
//        hsql.append("select distinct new map("
//        		+ " yor.id.orid as orid,yor.usid as usid, yor.id.iscenicid as iscenicid, yor.ornm as ornm, yor.orhm as orhm,yor.orph as orph,"
//        		+ " yor.ddzt as ddzt,yor.mont as summont,yor.tpmont as tpmont,yor.tpsx as tpsx,yor.iregionalid as iregionalid,"
//        		
//        		+ " us.corpname as corpname,"
//        		+ " mor.orda as orda, mor.payorid as payorid,"
//        		+ " es.szscenicname as szscenicname, es.scenictype as scenictype, "
//        		+ " v5.pmva as strddzt,"
//        		+ " t.ischupiao as ischupiao,"
//        		+ " card.cardNumber as cardNumber,card.profitNum as profitNum"
//        		+ " ) "
//        		+ " from TourCardDetail card, YOrder yor, MOrder mor, Custom us, Sysparv5 v5, Esbscenicareatab es, TOrder t "
//        		);
//        
//        where.append(" where card.cardNumber = mor.notee and mor.ordersource = 'lykgp' and mor.ortp='01' and t.id.orid = yor.id.orid and t.id.iscenicid = yor.id.iscenicid ");
        
//        where.append(" where mor.notee= card.cardNumber and mor.ordersource = 'lykgp' and mor.ortp='01' and mor.usid= us.usid and mor.orid = yor.id.orid "
//        		+ " and t.id.orid = yor.id.orid and t.id.iscenicid = yor.id.iscenicid "
//        		+ " and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt "
//        		+ " and es.iscenicid=yor.id.iscenicid"
//        		);
        
        hsql.append("select distinct new map("
        		+ " yor.id.orid as orid,yor.usid as usid, yor.id.iscenicid as iscenicid, yor.ornm as ornm, yor.orhm as orhm,yor.orph as orph,"
        		+ " yor.ddzt as ddzt,yor.mont as summont,yor.tpmont as tpmont,yor.tpsx as tpsx,yor.iregionalid as iregionalid,"
        		
        		+ " us.corpname as corpname,"
        		+ " mor.notee as notee, mor.orda as orda, mor.payorid as payorid,"
        		+ " t.ischupiao as ischupiao"
        		+ " ) "
        		+ " from YOrder yor, MOrder mor, Custom us, TOrder t "
        		);
        
        
        
        
        
        where.append(" where mor.ordersource = 'lykgp' and mor.ortp='01' and mor.usid= us.usid and mor.orid = yor.id.orid "
        		+ " and t.id.orid = yor.id.orid and t.id.iscenicid = yor.id.iscenicid "
        		);
        
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
        
//        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");//拼接到上一个where中了
        where.append(" order by yor.id.orid desc ");
        
        ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);
        
        
        
        //原始查询结果
        //+ " es.szscenicname as szscenicname, es.scenictype as scenictype, "
        //+ " v5.pmva as strddzt,"
        //+ " card.cardNumber as cardNumber,card.profitNum as profitNum"
        
        
        //原始等值条件
        //mor.notee= card.cardNumber 
        //+ " and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt "
        //+ " and es.iscenicid=yor.id.iscenicid"
        
        List<Map> itemList = ps.getItems();
        
        if(itemList!=null && itemList.size()>0) {
        	
        	StringBuilder cardNumbers = new StringBuilder();
        	StringBuilder pmcds = new StringBuilder();
        	StringBuilder iscenicids = new StringBuilder();
        	
        	for (Map item : itemList) {
            	String notee = item.get("notee").toString();
            	cardNumbers.append(notee);
            	cardNumbers.append(",");
            	
            	String ddzt = item.get("ddzt").toString();
            	pmcds.append(ddzt);
            	pmcds.append(",");
            	
            	String iscenicid = item.get("iscenicid").toString();
            	iscenicids.append(iscenicid);
            	iscenicids.append(",");
    		}
        	cardNumbers.deleteCharAt(cardNumbers.length() - 1);
        	pmcds.deleteCharAt(pmcds.length() - 1);
        	iscenicids.deleteCharAt(iscenicids.length() - 1);
        	
        	
        	//从旅游卡服务查询旅游卡明细相关数据
        	List<Map> tourCardDetails = tourCardService.getCardDetailsByCardNumbers(cardNumbers.toString());
        	 //循环遍历，进行旅游卡明细信息的赋值
            for (Map item : itemList) {
            	String notee = item.get("notee").toString();
            	
            	for (Map cardDetail : tourCardDetails) {
            		String detailCardNumber = cardDetail.get("cardNumber").toString();
            		
            		if(notee.equals(detailCardNumber)) {
            			String detailProfitNum = cardDetail.get("profitNum").toString();
            			item.put("cardNumber", detailCardNumber);
            			item.put("profitNum", detailProfitNum);
            			
            			break;
            		}
    			}
            	
    		}
        	
        	//从系统服务查询系统参数相关数据
        	List<Map> sysparamList = sysparService.getSysparamsByPmcds(pmcds.toString());
        	for (Map item : itemList) {
            	String ddzt = item.get("ddzt").toString();
            	
            	for (Map sysparam : sysparamList) {
            		String pmcd = sysparam.get("pmcd").toString();
            		
            		if(ddzt.equals(pmcd)) {
            			String strddzt = sysparam.get("strddzt").toString();
            			item.put("strddzt", strddzt);
            			
            			break;
            		}
    			}
    		}
        	
        	//从票务服务查询服务商相关数据
    		List<Map> providerList = ticketService.getProvidersByIds(iscenicids.toString());
    		for (Map item : itemList) {
    		    String iscenicid = item.get("iscenicid").toString();
    			
    			for (Map provider : providerList) {
    				String provider_iscenicid = provider.get("iscenicid").toString();
    				
    				if(iscenicid.equals(provider_iscenicid)) {
    					String szscenicname = provider.get("szscenicname").toString();
    					String scenictype = provider.get("scenictype").toString();
    					item.put("szscenicname", szscenicname);
    					item.put("scenictype", scenictype);
    					
    					break;
    				}
    			}
    		}
        	
        }
        
        
        
        
        

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
//                    String sql = "select distinct new map("
//                    		+ " tzl.itickettypeid as itickettypeid,"
//                    		+ " tor.dtstartdate as dtstartdate,"
//                    		+ " prd.sztickettypename as sztickettypename,"
//                    		+ " tzl.pric as pric,sum(tzl.numb) as numb,sum(tzl.amnt) as amnt,tzl.isj as isj "
//                    		+ " )"
//                    		+ " from TOrder tor,Sysparv5 sys1,Edmtickettypetab prd,TOrderlist tzl,MOrder mor "
//                    		+ " where  ( mor.orid='"+ orid +"' or mor.srid='"+ orid +"' and mor.isg != 1 ) "
//                    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
//                            + " and sys1.id.pmky='DDZT'  and tor.id.iscenicid = tzl.id.iscenicid  and sys1.id.pmcd=tor.ddzt  and tzl.id.orid = tor.id.orid "
//                            + " and tzl.itickettypeid = prd.itickettypeid "
//                            + " group by tzl.itickettypeid,tor.dtstartdate,prd.sztickettypename,tzl.pric,tzl.isj "
//                            + " order by tzl.isj desc";
                    
                    
                  //根据原始sql分析可得：连表Sysparv5是为了加条件sys1.id.pmky='DDZT'，所以拆分sql后需要先查询Sysparv5来作为条件
                    //而连表Edmtickettypetab是为了取值，所以可在主sql查询之后再做处理
                    
                    List<Map> sysparList = sysparService.queryByPmkyPmcds("DDZT", "pmcd not in('1234567')");//利用一个不存在的参数值来忽略pmcd参数条件，达到查询所有DDZT的系统参数
                    StringBuilder pmcd_ddzts = new StringBuilder();
                    for (Map syspar : sysparList) {
                    	String pmcd = syspar.get("pmcd").toString();
                    	pmcd_ddzts.append("'"+ pmcd + "'");
                    	pmcd_ddzts.append(",");
					}
                    pmcd_ddzts.deleteCharAt(pmcd_ddzts.length() - 1);//去除最后一个逗号
                    
                    
                    String sql = "select distinct new map(tzl.itickettypeid as itickettypeid,"
                    		+ " tor.dtstartdate as dtstartdate,"
                    		+ " tzl.pric as pric, sum(tzl.numb) as numb, sum(tzl.amnt) as amnt, tzl.isj as isj )"
                    		+ " from TOrder tor, TOrderlist tzl, MOrder mor"
                    		
                    		+ " where ( mor.orid='"+ orid +"' or mor.srid='"+ orid +"' and mor.isg != 1 )"
                    		+ " and mor.orid=tor.id.orid and tor.id.iscenicid="+ cenicid
                    		+ " and tor.ddzt in ("+ pmcd_ddzts +")"//等值查询改为in查询
                    		
                            + " and tor.id.iscenicid = tzl.id.iscenicid and tzl.id.orid = tor.id.orid "
                            + " group by tzl.itickettypeid, tor.dtstartdate, tzl.pric, tzl.isj "
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
    
    

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public PaginationSupport queryOrderInfoByPage(Esfemployeetab esfemployeetab, QueryOrder order, 
//			int page, int pageSize, String url,
//			String cardNumber, String cardName, String profitNum) throws RuntimeException, Exception {
//
//        PaginationSupport ps = null;
//
//        StringBuffer hsql = new StringBuffer();
//        StringBuffer where = new StringBuffer();
//        hsql.append("select distinct new map( yor.id.orid as orid,yor.usid as usid,us.corpname as corpname,mor.orda as orda,yor.id.iscenicid as iscenicid,es.szscenicname as szscenicname,es.scenictype as scenictype,yor.ornm as ornm,mor.payorid as payorid ,yor.orhm as orhm,yor.orph as orph,yor.ddzt as ddzt,v5.pmva as strddzt,yor.mont as summont,yor.tpmont as tpmont,yor.tpsx as tpsx,yor.iregionalid as iregionalid,t.ischupiao as ischupiao,card.cardNumber as cardNumber,card.profitNum as profitNum) from TourCardDetail card,YOrder yor,MOrder mor,Custom us,Sysparv5 v5,Esbscenicareatab es,TOrder t ");
//        where.append(" where card.cardNumber = mor.notee and mor.ordersource = 'lykgp' and mor.ortp='01' and t.id.orid = yor.id.orid and t.id.iscenicid = yor.id.iscenicid ");
//        if (0 == order.getErrorsid()) {
//            // 所有服务商
//            if (0L == order.getIscenicid()) {
//                // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
//                if (esfemployeetab.getCompanytype().equals("02")) {
//                    String scenics = esfemployeetab.getScenics();
//                    where.append(" and yor.id.iscenicid  in (" + scenics + ") ");
//                }
//            } else {// 指定服务商
//                where.append(" and yor.id.iscenicid =" + order.getIscenicid());
//            }
//            if(4 == order.getRadiobutton1()){//散客
//                where.append(" and us.lgtp='01' ");
//            }
//            if (3 == order.getRadiobutton1()) {// 下单用户
//                where.append(" and mor.usid='" + order.getUsid() + "' ");
//            }
//            if (1 == order.getRadiobutton1() || 2 == order.getRadiobutton1() || 5 == order.getRadiobutton1()) {// 分社
//                // 总社  //OTA用户
//                where.append(" and mor.usid in (" + order.getUsids() + ") ");
//            }
//
//            if (order.getDyusid() != null && !"".equals(order.getDyusid())) {// 导游
//                where.append(" and yor.dyusid ='" + order.getDyusid() + "' ");
//            }
//            if (order.getStrornm() != null && !"".equals(order.getStrornm())) {// 导游名称模糊
//                where.append(" and	(yor.ornm like '%" + order.getStrornm()
//                        + "%' or us.corpname like '%"+order.getStrornm()+"%') ");
//            }
//            if (0 == order.getRadiobutton2()) {// 首日游览日期
//                where.append(" and mor.stdt>='" + order.startDate + "' and mor.stdt<='" + order.getEndDate() + "' ");
//            } else if (1 == order.getRadiobutton2()) {// 下订单日期
//                where.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate() + "' ");
//            } else if (2 == order.getRadiobutton2()) {// 支付日期日期
//                where.append(" and mor.bankdata>='" + order.getStartDate() + "' and mor.bankdata<='" + order.getEndDate() + "' ");
//            } else if (4 == order.getRadiobutton2()) {// 出票时间
//                where.append(" and substr(yor.notec,1,10)>='" + order.getStartDate() + "' and substr(yor.notec,1,10) <='"
//                        + order.getEndDate() + "' ");
//            } else if (3 == order.getRadiobutton2()) {// 竹筏日期
//                hsql.append(",TZorderlist tzl");
//                where
//                        .append(" and tzl.id.orid = yor.id.orid and tzl.id.iscenicid=yor.id.iscenicid and tzl.tripid>0 and substr(tzl.dtstartdate,1,10)>='"
//                                + order.getStartDate() + "' and substr(tzl.dtstartdate,1,10)<='" + order.getEndDate() + "'");
//            }
//            if ("99".equals(order.getDdzt())) {// 所有
//                where.append(" and yor.ddzt in ('00','02','11','18','21','20','27','30','95','98')");
//            } else {
//                if("11".equals(order.getDdzt())){
//                    where.append(" and yor.ddzt in ('11','27') ");
//                }else{
//                    where.append(" and yor.ddzt ='" + order.getDdzt() + "'");
//                }
//
//            }
//            if(cardNumber != null && !"".equals(cardNumber)) {
//            	where.append(" and card.cardNumber like '%"+cardNumber+"%'");
//            }
//            if(cardName != null && !"".equals(cardName)) {
//            	where.append(" and card.name like '%"+cardName+"%'");
//            }
//            if(profitNum != null && !"".equals(profitNum)) {
//            	where.append(" and card.profitNum like '%"+profitNum+"%'");
//            }
//        }
//        if (1 == order.getErrorsid()) {
//            // 读取景区企业对应可管理的服务商，服务商用户只能查询对应管理服务商的信息。
//            if (esfemployeetab.getCompanytype().equals("02")) {// 表示景区企业
//                String scenics = esfemployeetab.getScenics();
//                where.append(" and yor.id.iscenicid in (" + scenics + ")  ");
//            }
//            if (0 == order.getRadiobutton3()) {// 订单号
//                if (order.getOrid() != null && !"".equals(order.getOrid())) {
//                    where.append(" and mor.orid='" + order.getOrid() + "' ");
//                }
//            } else if (1 == order.getRadiobutton3()) {// 联系人姓名
//                if (order.getStrornm() != null && !"".equals(order.getStrornm())) {
//                    where.append(" and yor.ornm like '%" + order.getStrornm() + "%' ");
//                }
//            } else if (2 == order.getRadiobutton3()) {// 联系人证件号码
//                if (order.getOrhm() != null && !"".equals(order.getOrhm())) {
//                    where.append(" and lower(yor.orhm) = lower('" + order.getOrhm() + "') ");
//                }
//            } else if (3 == order.getRadiobutton3()) {// 逾期未领订单
//                where.append(" and yor.dtstartdate<'" + Tools.getDays() + "' and yor.ddzt='02' and yor.mont>0 ");
//            } else if (4 == order.getRadiobutton3()) {// 行程单号
//                if (order.getSztravelbillno() != null && !"".equals(order.getSztravelbillno())) {
//                    where.append(" and yor.sztravelbillno ='" + order.getSztravelbillno() + "' ");
//                }
//            } else if (5 == order.getRadiobutton3()) { // 支付订单号
//                if (order.getPayorid() != null && !"".equals(order.getPayorid())) {
//                    where.append(" and mor.payorid='" + order.getPayorid() + "' ");
//                }
//            }
//        }
//        where.append(" and us.usid = mor.usid  and mor.orid = yor.id.orid and v5.id.pmky='DDZT' and v5.id.pmcd=yor.ddzt and es.iscenicid=yor.id.iscenicid");
//        where.append(" order by yor.id.orid desc ");
//        System.out.println(hsql.toString() + where.toString());
//        
//        ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);
//
//        List list = ps.getItems();
//        if (list.size() >= 1) {
//            Map map = null;
//            String orid = "";
//            for (int i = 0; i < list.size(); i++) {
//                map = (Map) list.get(i);
//                if(map.get("orhm") != null) {//格式化身份证显示
//                    map.put("orhm", StringUtil.markIdNumber((String) map.get("orhm")));
//                }
//                if (map.get("orid") != null) {// 订单号
//                    orid = map.get("orid").toString();
//                    Long cenicid = new Long(map.get("iscenicid").toString());
//                    // String
//                    String sql = "select distinct new map(tzl.itickettypeid as itickettypeid,tor.dtstartdate as dtstartdate,prd.sztickettypename as sztickettypename,tzl.pric as pric,sum(tzl.numb) as numb,sum(tzl.amnt) as amnt,tzl.isj as isj )from TOrder tor,Sysparv5 sys1,Edmtickettypetab prd,TOrderlist tzl,MOrder mor  where  ( mor.orid='"
//                            + orid
//                            + "' or mor.srid='"
//                            + orid
//                            + "' and mor.isg != 1 ) and mor.orid=tor.id.orid and tor.id.iscenicid="
//                            + cenicid
//                            + " and sys1.id.pmky='DDZT'  and tor.id.iscenicid = tzl.id.iscenicid  and sys1.id.pmcd=tor.ddzt  and tzl.id.orid = tor.id.orid and tzl.itickettypeid = prd.itickettypeid group by tzl.itickettypeid,tor.dtstartdate,prd.sztickettypename,tzl.pric,tzl.isj order by tzl.isj desc";
//                    List torderList = this.find(sql);
//                    if (torderList.size() >= 1) {
//                        for (int j = 0; j < torderList.size(); j++) {
//                            Map zmap = (Map) torderList.get(j);
//                            if (null != zmap.get("isj")) {
//                                if (Long.parseLong(zmap.get("isj").toString()) == -1) {
//                                    zmap.put("numb", new Long(zmap.get("numb").toString()) * -1);
//                                }
//                            }
//                        }
//                        map.put("torderlists", torderList);
//                    }
//
//                }
//
//                if(map.get("iregionalid")!=null){
//                    Long iregionalid=Long.parseLong(map.get("iregionalid").toString());
//                    Galsourceregiontab galsource=(Galsourceregiontab)this.get(Galsourceregiontab.class, iregionalid);
//                    if(galsource.getSzinnername()!=null&&!galsource.getSzinnername().equals("")){
//                        map.put("szinnername", galsource.getSzinnername());
//                    }else{
//                        map.put("szinnername", galsource.getSzregionalname());
//                    }
//
//                }else{
//                    TOrder t=(TOrder)this.get(TOrder.class, new TOrderId(map.get("orid").toString(), Long.parseLong(map.get("iscenicid").toString())));
//                    if(t.getIregionalid()!=null&&!t.getIregionalid().toString().equals("")){
//                        Galsourceregiontab galsource=(Galsourceregiontab)this.get(Galsourceregiontab.class, t.getIregionalid());
//                        if(galsource.getSzinnername()!=null&&!galsource.getSzinnername().trim().equals("")){
//                            map.put("szinnername", galsource.getSzinnername());
//                        }else{
//                            map.put("szinnername", galsource.getSzregionalname());
//                        }
//                    }else{
//                        map.put("szinnername", "");
//                    }
//
//                }
//
//            }
//        }
//
//        return ps;
//	}
}
