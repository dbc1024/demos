package com.ectrip.ec.order.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.enums.DDZT;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.LOrder;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.dao.idao.IMOrderDAO;
import com.ectrip.ticket.model.provider.Changebackrate;
import com.ectrip.ticket.model.provider.Ticketxgz;
/**
<<<<<<< .mine
*
* @ClassName: MOrderDAO
* @Description: 网上预定订票
* @author Dicky
* @date Oct 11, 2011 2:46:13 PM
*
*/
@Repository
public class MOrderDAO extends GenericDao implements IMOrderDAO {
	//DAO 相互调用



    /**
     * (非 Javadoc)
     * <p>
     * Title: saveMOrder
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @see com.ectrip.order.dao.idao.IMOrderDAO#saveMOrder()
     */
    public void saveMOrder(MOrder morder) {
        this.save(morder);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getOrderViewList
     * </p>
     * <p>
     * Description:订单分页
     * </p>
     *
     * @param usid
     * @param hql
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.order.dao.idao.IMOrderDAO#getOrderViewList(java.lang.String,
     *      java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getOrderViewList(String usid, QueryOrder query,
                                              int pageSize, int startIndex, String url) {
        StringBuffer sql = new StringBuffer();
        // distinct
        StringBuffer where = new StringBuffer();

        sql.append("select new map(mor.note as note,tor.dtstartdate as dtstartdate,tor.id.iscenicid as iscenicid,tor.dtenddate as dtenddate,s.pmva as strddzt,mor.orda as orda,mor.orti as orti,tor.ddzt as ddzt,mor.mont as mont,mor.zfmont as zfmont,mor.usid as usid,com.corpname as corpname,com.lname as lname,mor.zfusid,mor.tpmont as tpmont,mor.tpsx as tpsx,tor.ornm as ornm,mor.stdt as stdt,mor.orid as orid,provider.szscenicname as szscenicname,provider.scenictype as scenictype,mor.yhamnt as yhamnt,tor.orfl as orfl) from MOrder mor,Sysparv5 s,Custom com,TOrder tor,Esbscenicareatab provider");
        where.append(" where (mor.ortp='01' or mor.ortp='02') and tor.id.iscenicid=provider.iscenicid ");
        if (query.getOrid() != null && !query.getOrid().equals("")) {
            where.append(" and mor.orid='" + query.getOrid()+"' ");

            // 自己+所有子用户
            List list = null;
            list = getSonCustom(usid, list);
            if (list.size() > 0) {
                where.append(" and mor.usid in (");
                for (int i = 0; i < list.size(); i++) {
                    where.append("'" + list.get(i).toString()
                            + "',");
                }
                where.append("'" + usid + "')");
            } else {
                where.append(" and mor.usid='" + usid + "'");
            }
            // 2014年4月7日 10:44:26
            // hejiahua 新增用户控制
            // and
            // mor.usid='"+usid+"'
        } else {
            // 判断usid是否是团体用户 02团体用户 取出团体用户子用户所有的主订单
            if (query.getRadiobutton2() == 1) {// 下单日期方式
                where.append(" and mor.orda >= '" + query.getStartDate() + "'");
                where.append(" and mor.orda <= '" + query.getEndDate() + "'");
            }
            if (query.getRadiobutton2() == 2) {// 支付日期
                where.append(" and mor.bankdata >= '" + query.getStartDate()
                        + "'");
                where.append(" and mor.bankdata <= '" + query.getEndDate()
                        + "'");
            }
            if (query.getRadiobutton2() == 0) {// 消费日期
                where.append(" and tor.dtstartdate >= '" + query.getStartDate()
                        + "'");
                where.append(" and tor.dtenddate <= '" + query.getEndDate()
                        + "'");
            }
            Custom cu = (Custom) this.get(Custom.class, usid);

            if (cu.getLgtp().equals("02")) {// 团体用户
                if (cu.getTtlb().equals("02")) {// 导游
                    // 导游用户 取出所有导游关联订单用户id
                    where.append(" and ( tor.dyusid='" + usid
                            + "' or tor.usid='" + usid + "')");
                } else {
                    if (query.getRadiobutton1() == 0) {
                        // 自己
                        where.append(" and mor.usid='" + usid + "'");
                    }
                    if (query.getRadiobutton1() == 1) {
                        // 自己+所有子用户
                        List list = null;
                        list = getSonCustom(usid, list);
                        if (list.size() > 0) {
                            where.append(" and mor.usid in (");
                            for (int i = 0; i < list.size(); i++) {
                                where.append("'" + list.get(i).toString()
                                        + "',");
                            }
                            where.append("'" + usid + "')");
                        } else {
                            where.append(" and mor.usid='" + usid + "'");
                        }
                    }
                    if (query.getRadiobutton1() == 2) { // 所有子用户
                        List list = null;
                        list = getSonCustom(usid, list);
                        if (list.size() > 0) {
                            where.append(" and mor.usid in (");
                            for (int i = 0; i < list.size(); i++) {
                                if (i < list.size() - 1) {
                                    where.append("'" + list.get(i).toString()
                                            + "',");
                                } else {
                                    where.append("'" + list.get(i).toString()
                                            + "' )");
                                }
                            }

                        } else {
                            where.append(" and mor.usid='" + usid + "'");
                        }
                    }
                    if (query.getRadiobutton1() == 3) {
                        // 指定子用户
                        List list = null;
                        list = getSonCustom(query.getChildCustom(), list);
                        // 判断子用户是否还有子用户
                        if (list.size() > 0) {
                            where.append(" and mor.usid in (");
                            for (int i = 0; i < list.size(); i++) {
                                where.append("'" + list.get(i).toString()
                                        + "',");
                            }
                            // 将子用户的操作员一起查询
                            where.append("'" + query.getChildCustom() + "')");
                        } else {
                            where.append(" and mor.usid='"
                                    + query.getChildCustom() + "'");
                        }

                    }
                }
            } else {
                where.append(" and mor.usid in ('" + cu.getUsid() + "')");
            }
            if (query.getDyusid() != null && !query.getDyusid().equals("")) {
                if (!query.getDyusid().equals("0000")) {
                    where.append(" and tor.dyusid =  '" + query.getDyusid()
                            + "'");
                }
            }
            if (!query.getDdzt().equals("99")) {
                if("93".equals(query.getDdzt())){
                    where.append(" and mor.ddzt in('02','11','33') ");
                }else{
                    where.append(" and mor.ddzt ='" + query.getDdzt() + "'");
                }

            }
            if (query.getOrfl() != null && !query.getOrfl().equals("")) {
                where.append(" and tor.orfl =  '" + query.getOrfl() + "'");
            }
            if (query.getOrnm()!=null&&!query.getOrnm().equals("")) {
                where.append(" and tor.ornm  like  '%" + query.getOrnm()+ "%'");
            }
            if (query.getNote()!=null&&!query.getNote().equals("")) {
                where.append(" and mor.note like  '%" +query.getNote()+ "%'");
            }
        }
        where
                .append(" and tor.id.orid=mor.orid and s.id.pmky='DDZT' and s.id.pmcd=mor.ddzt and mor.usid=com.usid");
        where.append(" order by mor.orda desc,mor.orti desc ");
        System.out.println("===>>sql:" + sql.toString() + " "
                + where.toString());

        PaginationSupport ps = this.findPage(sql.toString() + where.toString(),
                pageSize, startIndex, url);

        List item = ps.getItems();
        // 获取取号订单 2014-04-17 10:40:41 hejiahua
        Iterator iterator = item.iterator();
        while (iterator.hasNext()) {
            Map map = (Map) iterator.next();

            String orid = map.get("orid").toString();
            String ddzt = map.get("ddzt").toString();
            String strddzt = map.get("strddzt").toString();
            // 判断前台订单状态
            String ddzt_hsql = "select new map(m.ddzt as ddzt ,s.pmva as strddzt) from MOrder m,Sysparv5 s where m.ddzt=s.id.pmcd and s.id.pmky='DDZT' and m.srid='"
                    + orid + "'";

            List ddzt_list = find(ddzt_hsql);
            if (ddzt_list != null && ddzt_list.size() > 0) {
                Map ddzt_map = (Map) ddzt_list.get(0);
                String two_ddzt = ddzt_map.get("strddzt").toString();
                if (two_ddzt != null && !two_ddzt.trim().equals("")) {
                    strddzt = strddzt + "(" + two_ddzt + ")";
                    map.put("strddzt", strddzt);
                }
            }

            // 是否过期
            String stdt = map.get("dtstartdate").toString();
            if (Tools.getDayNumbCha(Tools.getDays(), stdt) < 0) {
                map.put("expire", 1);// 过期
            } else {
                map.put("expire", 0);
            }
            
            String ss_hsql="select new map(tl.id.orid as orid,ecp.ipeoplenumrange as ipeoplenumrange) from TOrderlist tl,Edmcrowdkindpricetab ecp where tl.itickettypeid=ecp.itickettypeid and tl.icrowdkindid=ecp.icrowdkindid and tl.icrowdkindpriceid=ecp.icrowdkindpriceid and ecp.ipeoplenumrange=1 and tl.id.orid='"+orid+"'";
            List ss_list = find(ss_hsql);
            if(ss_list!=null&&ss_list.size()>0){
            	map.put("tuanti", 1);
            	boolean isrealnam=false;
            	for(int i=0;i<ss_list.size();i++){
            		 Map maps = (Map) ss_list.get(i);
            		 Long ipeoplenumrange=Long.valueOf(maps.get("ipeoplenumrange").toString()) ;
            		 if(ipeoplenumrange==1){
            			 isrealnam=true;
            		 }
            	}
            	map.put("isrealnam", isrealnam);
            } else {
                map.put("tuanti", 0);
            }
            
        }

        return ps;
    }
    /**
     * (非 Javadoc)
     * <p>
     * Title: getOrderViewList
     * </p>
     * <p>
     * Description:订单分页
     * </p>
     *
     * @param usid
     * @param hql
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.order.dao.idao.IMOrderDAO#getOrderViewList(java.lang.String,
     *      java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getOrderViewListByGroupId(String usid, QueryOrder query,
    		int pageSize, int startIndex, String url,String groupId) {
    	StringBuffer sql = new StringBuffer();
    	// distinct
    	StringBuffer where = new StringBuffer();
    	
    	
    	sql.append("select new map(mor.note as note,tor.dtstartdate as dtstartdate,tor.id.iscenicid as iscenicid,tor.dtenddate as dtenddate,s.pmva as strddzt,mor.orda as orda,mor.orti as orti,tor.ddzt as ddzt,mor.mont as mont,mor.zfmont as zfmont,mor.usid as usid,com.corpname as corpname,com.lname as lname,mor.zfusid,mor.tpmont as tpmont,mor.tpsx as tpsx,tor.ornm as ornm,mor.stdt as stdt,mor.orid as orid,provider.szscenicname as szscenicname,provider.scenictype as scenictype,mor.yhamnt as yhamnt,tor.orfl as orfl) from MOrder mor,Sysparv5 s,Custom com,TOrder tor,Esbscenicareatab provider ");
    	
    	
    	groupId = groupId.replaceAll("\'", "");
    	String[] iscenicids = groupId.split(",");
    	
    	
    	where.append(" where (mor.ortp='01' or mor.ortp='02') and tor.id.iscenicid=provider.iscenicid   ");
    	
    	if(groupId !=null && groupId.length()>0){
    		where.append(" and provider.iscenicid  in ("); 
    		for(int i =0;i<iscenicids.length;i++){
    			long id = Long.parseLong(iscenicids[i]);
    			where.append(id);
    			if(i+1<iscenicids.length){
    				where.append(",");
    			}
    		}
    		where.append(")  ");
    	}
    	
    	if (query.getOrid() != null && !query.getOrid().equals("")) {
    		where.append(" and mor.orid='" + query.getOrid()+"' ");
    		
    		// 自己+所有子用户
    		List list = null;
    		list = getSonCustom(usid, list);
    		if (list.size() > 0) {
    			where.append(" and mor.usid in (");
    			for (int i = 0; i < list.size(); i++) {
    				where.append("'" + list.get(i).toString()
    						+ "',");
    			}
    			where.append("'" + usid + "')");
    		} else {
    			where.append(" and mor.usid='" + usid + "'");
    		}
    		// 2014年4月7日 10:44:26
    		// hejiahua 新增用户控制
    		// and
    		// mor.usid='"+usid+"'
    	} else {
    		// 判断usid是否是团体用户 02团体用户 取出团体用户子用户所有的主订单
    		if (query.getRadiobutton2() == 1) {// 下单日期方式
    			where.append(" and mor.orda >= '" + query.getStartDate() + "'");
    			where.append(" and mor.orda <= '" + query.getEndDate() + "'");
    		}
    		if (query.getRadiobutton2() == 2) {// 支付日期
    			where.append(" and mor.bankdata >= '" + query.getStartDate()
    			+ "'");
    			where.append(" and mor.bankdata <= '" + query.getEndDate()
    			+ "'");
    		}
    		if (query.getRadiobutton2() == 0) {// 消费日期
    			where.append(" and tor.dtstartdate >= '" + query.getStartDate()
    			+ "'");
    			where.append(" and tor.dtenddate <= '" + query.getEndDate()
    			+ "'");
    		}
    		Custom cu = (Custom) this.get(Custom.class, usid);
    		
    		if (cu.getLgtp().equals("02")) {// 团体用户
    			if (cu.getTtlb().equals("02")) {// 导游
    				// 导游用户 取出所有导游关联订单用户id
    				where.append(" and ( tor.dyusid='" + usid
    						+ "' or tor.usid='" + usid + "')");
    			} else {
    				if (query.getRadiobutton1() == 0) {
    					// 自己
    					where.append(" and mor.usid='" + usid + "'");
    				}
    				if (query.getRadiobutton1() == 1) {
    					// 自己+所有子用户
    					List list = null;
    					list = getSonCustom(usid, list);
    					if (list.size() > 0) {
    						where.append(" and mor.usid in (");
    						for (int i = 0; i < list.size(); i++) {
    							where.append("'" + list.get(i).toString()
    									+ "',");
    						}
    						where.append("'" + usid + "')");
    					} else {
    						where.append(" and mor.usid='" + usid + "'");
    					}
    				}
    				if (query.getRadiobutton1() == 2) { // 所有子用户
    					List list = null;
    					list = getSonCustom(usid, list);
    					if (list.size() > 0) {
    						where.append(" and mor.usid in (");
    						for (int i = 0; i < list.size(); i++) {
    							if (i < list.size() - 1) {
    								where.append("'" + list.get(i).toString()
    										+ "',");
    							} else {
    								where.append("'" + list.get(i).toString()
    										+ "' )");
    							}
    						}
    						
    					} else {
    						where.append(" and mor.usid='" + usid + "'");
    					}
    				}
    				if (query.getRadiobutton1() == 3) {
    					// 指定子用户
    					List list = null;
    					list = getSonCustom(query.getChildCustom(), list);
    					// 判断子用户是否还有子用户
    					if (list.size() > 0) {
    						where.append(" and mor.usid in (");
    						for (int i = 0; i < list.size(); i++) {
    							where.append("'" + list.get(i).toString()
    									+ "',");
    						}
    						// 将子用户的操作员一起查询
    						where.append("'" + query.getChildCustom() + "')");
    					} else {
    						where.append(" and mor.usid='"
    								+ query.getChildCustom() + "'");
    					}
    					
    				}
    			}
    		} else {
    			where.append(" and mor.usid in ('" + cu.getUsid() + "')");
    		}
    		if (query.getDyusid() != null && !query.getDyusid().equals("")) {
    			if (!query.getDyusid().equals("0000")) {
    				where.append(" and tor.dyusid =  '" + query.getDyusid()
    				+ "'");
    			}
    		}
    		if (!query.getDdzt().equals("99")) {
    			if("93".equals(query.getDdzt())){
    				where.append(" and mor.ddzt in('02','11','33') ");
    			}else if("100".equals(query.getDdzt())){//全部订单但去掉垃圾订单
                    where.append(" and mor.ddzt not in('98') ");
                }else if("101".equals(query.getDdzt())){//退款包括（27 全退订 31 退款中 30 已退订(退款中) 33 退款失败）
                    where.append(" and mor.ddzt in('27','31','30','33') ");
                }else{
    				where.append(" and mor.ddzt ='" + query.getDdzt() + "'");
    			}
    		}
    		if (query.getOrfl() != null && !query.getOrfl().equals("")) {
    			where.append(" and tor.orfl =  '" + query.getOrfl() + "'");
    		}
    		if (query.getOrnm()!=null&&!query.getOrnm().equals("")) {
    			where.append(" and tor.ornm  like  '%" + query.getOrnm()+ "%'");
    		}
    		if (query.getNote()!=null&&!query.getNote().equals("")) {
    			where.append(" and mor.note like  '%" +query.getNote()+ "%'");
    		}
    	}
    	where
    	.append(" and tor.id.orid=mor.orid and s.id.pmky='DDZT' and s.id.pmcd=mor.ddzt and mor.usid=com.usid");
    	where.append(" order by mor.orda desc,mor.orti desc ");
    	System.out.println("===>>sql:" + sql.toString() + " "
    			+ where.toString());
    	
    	PaginationSupport ps = this.findPage(sql.toString() + where.toString(),
    			pageSize, startIndex, url);
    	
    	List item = ps.getItems();
    	// 获取取号订单 2014-04-17 10:40:41 hejiahua
    	Iterator iterator = item.iterator();
    	while (iterator.hasNext()) {
    		Map map = (Map) iterator.next();
    		
    		String orid = map.get("orid").toString();
    		String ddzt = map.get("ddzt").toString();
    		String strddzt = map.get("strddzt").toString();
    		// 判断前台订单状态
    		String ddzt_hsql = "select new map(m.ddzt as ddzt ,s.pmva as strddzt) from MOrder m,Sysparv5 s where m.ddzt=s.id.pmcd and s.id.pmky='DDZT' and m.srid='"
    				+ orid + "'";
    		
    		List ddzt_list = find(ddzt_hsql);
    		if (ddzt_list != null && ddzt_list.size() > 0) {
    			Map ddzt_map = (Map) ddzt_list.get(0);
    			String two_ddzt = ddzt_map.get("strddzt").toString();
    			if (two_ddzt != null && !two_ddzt.trim().equals("")) {
    				strddzt = strddzt + "(" + two_ddzt + ")";
    				map.put("strddzt", strddzt);
    			}
    		}
    		
    		// 是否过期
    		String stdt = map.get("dtstartdate").toString();
    		if (Tools.getDayNumbCha(Tools.getDays(), stdt) < 0) {
    			map.put("expire", 1);// 过期
    		} else {
    			map.put("expire", 0);
    		}
    		
    		String ss_hsql="select new map(tl.id.orid as orid,ecp.ipeoplenumrange as ipeoplenumrange) from TOrderlist tl,Edmcrowdkindpricetab ecp where tl.itickettypeid=ecp.itickettypeid and tl.icrowdkindid=ecp.icrowdkindid and tl.icrowdkindpriceid=ecp.icrowdkindpriceid and ecp.ipeoplenumrange=1 and tl.id.orid='"+orid+"'";
    		List ss_list = find(ss_hsql);
    		if(ss_list!=null&&ss_list.size()>0){
    			map.put("tuanti", 1);
    			boolean isrealnam=false;
    			for(int i=0;i<ss_list.size();i++){
    				Map maps = (Map) ss_list.get(i);
    				Long ipeoplenumrange=Long.valueOf(maps.get("ipeoplenumrange").toString()) ;
    				if(ipeoplenumrange==1){
    					isrealnam=true;
    				}
    			}
    			map.put("isrealnam", isrealnam);
    		} else {
    			map.put("tuanti", 0);
    		}
    		
    	}
    	
    	return ps;
    }

    /**
     * 取订单的数量
     *
     * @param orid
     * @return
     */
    public Long getOrderNumb(String orid) {
        String Hsql = " select new map( sum(numb) as rc )  from TOrderlist where orid = '"
                + orid + "'";
        List list = this.find(Hsql);
        Long b = 0L;
        if (list.size() > 0) {
            Map a = (Map) list.get(0);
            Long l = (Long) a.get("rc");
            b = l;
        }
        return b;
    }

    /**
     * 根据订单号取数量
     *
     * @param orid
     * @return
     */
    public Long getTdOderNumb(String orid) {

        String Hsql = "  select new map( sum(numb)   as rc ) from YOrderlist where orid in ( select orid from MOrder where srid = '"
                + orid + "')";
        List list = this.find(Hsql);
        System.out.println(  Hsql);
        Long b = 0L;
        if (list.size() > 0) {
            Map a = (Map) list.get(0);
            Long l = (Long) a.get("rc");

            if (l == null)

            {
                l = 0L;
            }
            else
            {
                b = l;
            }
        }
        return b;
    }

    public PaginationSupport getOrderMoneyChangeViewlist(String usid, QueryOrder query,
                                                         int pageSize, int startIndex, String url){
        StringBuffer sql = new StringBuffer();
        // distinct
        StringBuffer where = new StringBuffer();
        sql
                .append(" select new map(mor.orid as orid,s.pmva as strddzt,mor.orda as orda,mor.orti as orti,mor.ddzt as ddzt,mor.mont as mont,mor.usid as usid,com.corpname as corpname,com.lname as lname,mor.zfusid,mor.tpmont as tpmont,mor.tpsx as tpsx,mor.stdt as stdt,mor.zfmont as zfmont) from MOrder mor,Sysparv5 s,Custom com");
        where.append(" where mor.ortp='01' ");
        if (query.getOrid() != null && !query.getOrid().equals("")) {
            where.append(" and mor.orid='" + query.getOrid() + "'");
        } else {

            // 判断usid是否是团体用户 02团体用户 取出团体用户子用户所有的主订单
            if (query.getRadiobutton2() == 1) {// 下单日期方式
                where.append(" and mor.orda >= '" + query.getStartDate() + "'");
                where.append(" and mor.orda <= '" + query.getEndDate() + "'");
            }
            if (query.getRadiobutton2() == 2) {// 支付日期
                where.append(" and mor.bankdata >= '" + query.getStartDate() + "'");
                where.append(" and mor.bankdata <= '" + query.getEndDate() + "'");
            }
            Custom cu = (Custom) this.get(Custom.class, usid);

            if (cu.getLgtp().equals("02")) {// 团体用户
                if (query.getRadiobutton1() == 0) {
                    // 自己
                    where.append(" and mor.usid='" + usid + "'");
                }
                if (query.getRadiobutton1() == 1) {
                    // 自己+所有子用户
                    List list = null;
                    list = getSonCustom(usid, list);
                    if (list.size() > 0) {
                        where.append(" and mor.usid in (");
                        for (int i = 0; i < list.size(); i++) {
                            where.append("'" + list.get(i).toString() + "',");
                        }
                        where.append("'" + usid + "')");
                    } else {
                        where.append(" and mor.usid='" + usid + "'");
                    }
                }
                if (query.getRadiobutton1() == 2) { // 所有子用户
                    List list = null;
                    list = getSonCustom(usid, list);
                    if (list.size() > 0) {
                        where.append(" and mor.usid in (");
                        for (int i = 0; i < list.size(); i++) {
                            if (i < list.size() - 1) {
                                where.append("'" + list.get(i).toString() + "',");
                            } else {
                                where.append("'" + list.get(i).toString() + "' )");
                            }
                        }

                    } else {
                        where.append(" and mor.usid='" + usid + "'");
                    }
                }
                if (query.getRadiobutton1() == 3) {
                    // 指定子用户
                    List list = null;
                    list = getSonCustom(query.getChildCustom(), list);
                    // 判断子用户是否还有子用户
                    if (list.size() > 0) {
                        where.append(" and mor.usid in (");
                        for (int i = 0; i < list.size(); i++) {
                            where.append("'" + list.get(i).toString() + "',");
                        }
                        // 将子用户的操作员一起查询
                        where.append("'" + query.getChildCustom() + "')");
                    } else {
                        where.append(" and mor.usid='" + query.getChildCustom() + "'");
                    }

                }

            } else {
                where.append(" and mor.usid in ('" + cu.getUsid() + "')");
            }
            if (!query.getDdzt().equals("99")) {
                where.append(" and mor.ddzt ='" + query.getDdzt() + "'");
            }
        }
        where.append(" and s.id.pmky='DDZT' and s.id.pmcd=mor.ddzt and mor.usid=com.usid");
        where.append(" order by mor.orda desc,mor.orti desc ");
        System.out.println("===>>sql:"+sql.toString()+" "+where.toString());
        return this.findPage(sql.toString() + where.toString(), pageSize,
                startIndex, url);
    }



    public PaginationSupport getOrderMoneyChangeViewlistByGroupid(String usid, QueryOrder query, int pageSize, int startIndex,
			String url,String groupId) {
		StringBuffer sql = new StringBuffer();
		// distinct
		StringBuffer where = new StringBuffer();
		sql.append(
				" select new map(mor.orid as orid,s.pmva as strddzt,mor.orda as orda,mor.orti as orti,mor.ddzt as ddzt,mor.mont as mont,mor.usid as usid,com.corpname as corpname,com.lname as lname,mor.zfusid,mor.tpmont as tpmont,mor.tpsx as tpsx,mor.stdt as stdt,mor.zfmont as zfmont) from MOrder mor,Sysparv5 s,Custom com");
		where.append(" where mor.ortp='01' ");
		
		if (groupId != null && groupId.length() > 0) {
			sql.append(" ,Galcompanyinfotab gal , Companyscenic c ,TOrder t");
		}

		if (groupId != null && groupId.length() > 0) {
			where.append(
					" and c.id.icompanyinfoid = gal.icompanyinfoid and c.id.iscenicid = t.id.iscenicid and mor.orid = t.id.orid and gal.icompanyinfoid = '"
							+ groupId + "'  ");
		}
		
		if (query.getOrid() != null && !query.getOrid().equals("")) {
			where.append(" and mor.orid='" + query.getOrid() + "'");
		} else {

			// 判断usid是否是团体用户 02团体用户 取出团体用户子用户所有的主订单
			if (query.getRadiobutton2() == 1) {// 下单日期方式
				where.append(" and mor.orda >= '" + query.getStartDate() + "'");
				where.append(" and mor.orda <= '" + query.getEndDate() + "'");
			}
			if (query.getRadiobutton2() == 2) {// 支付日期
				where.append(" and mor.bankdata >= '" + query.getStartDate() + "'");
				where.append(" and mor.bankdata <= '" + query.getEndDate() + "'");
			}
			Custom cu = (Custom) this.get(Custom.class, usid);

			if (cu.getLgtp().equals("02")) {// 团体用户
				if (query.getRadiobutton1() == 0) {
					// 自己
					where.append(" and mor.usid='" + usid + "'");
				}
				if (query.getRadiobutton1() == 1) {
					// 自己+所有子用户
					List list = null;
					list = getSonCustom(usid, list);
					if (list.size() > 0) {
						where.append(" and mor.usid in (");
						for (int i = 0; i < list.size(); i++) {
							where.append("'" + list.get(i).toString() + "',");
						}
						where.append("'" + usid + "')");
					} else {
						where.append(" and mor.usid='" + usid + "'");
					}
				}
				if (query.getRadiobutton1() == 2) { // 所有子用户
					List list = null;
					list = getSonCustom(usid, list);
					if (list.size() > 0) {
						where.append(" and mor.usid in (");
						for (int i = 0; i < list.size(); i++) {
							if (i < list.size() - 1) {
								where.append("'" + list.get(i).toString() + "',");
							} else {
								where.append("'" + list.get(i).toString() + "' )");
							}
						}

					} else {
						where.append(" and mor.usid='" + usid + "'");
					}
				}
				if (query.getRadiobutton1() == 3) {
					// 指定子用户
					List list = null;
					list = getSonCustom(query.getChildCustom(), list);
					// 判断子用户是否还有子用户
					if (list.size() > 0) {
						where.append(" and mor.usid in (");
						for (int i = 0; i < list.size(); i++) {
							where.append("'" + list.get(i).toString() + "',");
						}
						// 将子用户的操作员一起查询
						where.append("'" + query.getChildCustom() + "')");
					} else {
						where.append(" and mor.usid='" + query.getChildCustom() + "'");
					}

				}

			} else {
				where.append(" and mor.usid in ('" + cu.getUsid() + "')");
			}
			if (!query.getDdzt().equals("99")) {
				where.append(" and mor.ddzt ='" + query.getDdzt() + "'");
			}
		}
		where.append(" and s.id.pmky='DDZT' and s.id.pmcd=mor.ddzt and mor.usid=com.usid");
		where.append(" order by mor.orda desc,mor.orti desc ");
		System.out.println("===>>sql:" + sql.toString() + " " + where.toString());
		return this.findPage(sql.toString() + where.toString(), pageSize, startIndex, url);
	}





    /**
     * 查询自己及所有子用户
     * @param susid
     * @param list
     * @return
     */
    public List getSonCustom(String susid, List list) {
        if (list == null) {
            list = new ArrayList();
        }
        String hql = " select usid from Custom c where c.susid='" + susid + "'";
        List slist = find(hql);
        if (slist != null && slist.size() > 0) {
            list.addAll(slist);
            for (int i = 0; i < slist.size(); i++) {
                getSonCustom(slist.get(i).toString(), list);
            }
        } else {
            return list;
        }
        return list;
    }


    /**
     * 根据自己找主用户
     * @param susid
     * @param list
     * @return
     */
    public String getMainCustom(String usid) {

        String hql = "from Custom c where c.usid='" + usid + "'";
        List slist = find(hql);
        if (slist != null && slist.size() > 0) {

            for (int i = 0; i < slist.size(); i++) {
                Custom c  = (Custom) slist.get(i);
                if  (c.getSusid()==null || c.getSusid().equals("") )
                {
                    return usid ;
                }
                else
                {
                    return  getMainCustom(c.getSusid());
                }
            }
        } else {
            return usid;  //如果自己是主用户，就直接返回
        }
        return usid;
    }

    /**
     * 查询自己同级用户和同级用户有权限的子用户
     * @param susid
     * @param list
     * @return
     */
    public String getUsqxSonCustom(String usid, List list) {
        if (list == null) {
            list = new ArrayList();
        }
        String mainusid = getMainCustom(usid);  //当前用户的主用户；
        StringBuffer sb = new StringBuffer();
        return getUsids(mainusid, sb,usid);
    }
    /**
     * (非 Javadoc)
     * <p>
     * Title: getMOrderList
     * </p>
     * <p>
     * Description: 查询订单MOrder
     * </p>
     *
     * @param orid
     * @param usid
     * @return
     * @see com.ectrip.order.dao.idao.IMOrderDAO#getMOrderList(java.lang.String,
     *      java.lang.String)
     */
    public List getMOrderList(String orid) {

        List<Map> list = this.find("select new map(m.isb as isb, m.isd as isd,m.isi as isi,m.isjl as isjl,"
        		+ "m.orid as orid,m.usid as usid,m.ddzt as ddzt,m.mont as mont,m.zfmont as zfmont,m.orda as orda,"
        		+ "m.orti as orti,m.zffs as zffs,m.tpmont as tpmont,m.tpsx as tpsx,m.yhamnt as yhamnt,m.notec as notec,"
        		+ "m.noteg as noteg,m.note as note,m.isg as isg, m.ordersource as ordersource, m.dtmakedate as dtmakedate)"
//        		+ "s.id.pmcd as pmcd, s.pmva as pmva,"
        		+ " from MOrder m where m.orid='"+ orid + "'");
        if(list !=null && !list.isEmpty()){
            for(Map map : list){
                String ddzt = (String)map.get("ddzt");
                String pmva = DDZT.getPmvaByPmcd(ddzt);
                map.put("pmcd", ddzt);
                map.put("pmva", pmva);
                if("27".equals(ddzt)){//查询退订订单状态
                    String sql = "select notec from MOrder m2 where m2.srid=? and rownum=1";
                    List<String> tmpList = this.find(sql, new Object[]{(String)map.get("orid")});
                    if(tmpList!=null && !tmpList.isEmpty()){
                        String prefix = "lykgp".equals(map.get("ordersource")) ? "退订" : "退款";
                        String notec = tmpList.get(0);
                        map.put("refundddzt", notec);
                        if("0".equals(notec)){
                            map.put("pmva", prefix + "失败");
                        }else if("1".equals(notec)){
                            map.put("pmva", prefix + "成功");
                        }else if("2".equals(notec)){
                            map.put("pmva", prefix + "中");
                        }
                    }
                }
            }
        }
        return list;
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getMOrder
     * </p>
     * <p>
     * Description: 获取MOrder
     * </p>
     *
     * @param orid
     * @return
     * @see com.ectrip.order.dao.idao.IMOrderDAO#getMOrder(java.lang.String)
     */
    public MOrder getMOrder(String orid) {
        return (MOrder) this.get(MOrder.class, orid);
    }

    /**
     * 查询退订订单详情
     * @param srid 原订单号
     * @return
     */
    public MOrder getRefundMOrder(String srid) {
        List<MOrder> list = this.find("from MOrder where srid = ?", new Object[]{srid});
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    /**
     * (非 Javadoc)
     * <p>
     * Title: updateMOrder
     * </p>
     * <p>
     * Description: 修改MOrder
     * </p>
     *
     * @param morder
     * @see com.ectrip.order.dao.idao.IMOrderDAO#updateMOrder(com.ectrip.model.order.MOrder)
     */
    public void updateMOrder(MOrder morder) {
        this.update(morder);
    }

    public List getOrderLogByOrid(String orid) {
        String hql = "select new map(log.logid as logid,log.orid as orid,log.stlg as stlg,log.brief as brief,log.logdatetime as logdatetime,log.note as note,log.usid as usid,log.logtype as logtype) from Orderlog log where log.orid='"
                + orid + "' order by log.logdatetime";
        return find(hql);
    }

    /**
     *
     * Describe:获取退订规则
     *
     * @auth:yangguang
     * @param itickettypeid
     * @param iscenicid
     * @param lgtp
     * @return return:Ticketxgz Date:2012-2-17
     */
    public Ticketxgz ticketbackrule(Long itickettypeid, Long iscenicid,
                                    String lgtp) {
        String hql = "from Ticketxgz where iscenicid=" + iscenicid
                + " and itickettypeid=" + itickettypeid + " and isvalid=1 and xyjs4=0";
        Ticketxgz tdrule = null;
        List list = find(hql);
        if (list != null && list.size() > 0) {
            tdrule = (Ticketxgz) list.get(0);
            return tdrule;
        } else {
            return null;
        }
    }

    /**
     *
     * Describe:获取退订费率 根据退订规则和事件
     *
     * @auth:yangguang
     * @param gzid
     * @param time
     * @return return:Changebackrate Date:2012-2-17
     */
    public Changebackrate getflByTime(Long gzid, String time, String jsfs) {
        String hql = " from Changebackrate where gzid=" + gzid + "  ";
        if(time!=null&&!time.equals("")){
            hql+=" and time>"+ time + "";
        }
        hql+="order by time";
        List list = find(hql);
        if (list != null && list.size() > 0) {
            Changebackrate fl = (Changebackrate) list.get(0);
            return fl;
        } else {
            return null;
        }
    }

    public void removeevit(Object obj) {
        getHibernateTemplate().clear();
    }

    public List daoyoulist(String usid) {
        String usids = "";
        List list1 = new ArrayList();
        list1 = this.getSonCustom(usid, list1);
        if (list1.size() > 0) {
            for (int i = 0; i < list1.size(); i++) {
                usids = "'" + list1.get(i).toString() + "',";
            }
        }
        usids = usids + "'" + usid + "'";
        String hql = "select distinct new map(d.id.dyusid as dyusid,c.lname as lname) from Daoyou d,Custom c where c.usid=d.id.dyusid and d.id.usid in ("
                + usids + ")";
        return find(hql);
    }

    public PaginationSupport queryAllMsOrder(String usid, QueryOrder order,
                                             int page, int pageSize, String url) {
        PaginationSupport ps = null;
        StringBuffer hsql = new StringBuffer();
        // 将导游和旅行社分开查询

        Custom cu = (Custom) this.get(Custom.class, usid);
        if (cu.getTtlb().equals("02")) {
            hsql.append("select distinct new map(mor.orda as orda,mor.orti as orti,mor. mor.orid as orid,mor.srid as srid,mor.usid as usid,us.corpname as corpname,us.lname as lname,mor.ddzt as ddzt,mor.orda as orda,mor.zfmont as zfmont,mor.tpmont as tpmont,mor.stdt as stdt,mor.tpsx as tpsx,mor.tpmont as tpmont,mor.tpfs as tpfs,v5.pmva as strtdlb,mor.ddzt as ddzt,v51.pmva as strtpfs,v52.pmva as strddzt,mor.yhamnt as yhamnt,t.ornm as ornm) from MOrder mor,Custom us,Sysparv5 v5,Sysparv5 v51,Sysparv5 v52,MOrder smor,TOrder t,M_order mor2 where mor.srid=mor2.orid  mor.ortp in ('02','03') and  mor.ddzt in ('06','88','27','30','33')   ");
            hsql.append(" and (t.dyusid='"+usid+"' or mor.usid='"+usid+"')");
            if (order.getOrid() != null && !order.getOrid().equals("")) {
                hsql.append(" and mor.orid='" + order.getOrid() + "'");
            }else if (order.getXrid() != null && !order.getXrid().equals("")){
                hsql.append(" and mor.srid='" + order.getXrid() + "'");
            }else{
                if (!order.getTpfs().equals("99")) {
                    hsql.append(" and mor.tpfs ='" + order.getTpfs() + "'");
                }
                if (!order.getTdlb().equals("99")) {
                    hsql.append(" and mor.notef ='" + order.getTdlb() + "' ");
                }
                if (0 == order.getRadiobutton2()) {// 消费（游览）日期
                    hsql.append(" and mor2.stdt>='" + order.startDate
                            + "' and mor2.stdt<='" + order.getEndDate() + "' ");
                } else if (1 == order.getRadiobutton2()) {// 退订单日期
                    hsql.append(" and mor.orda>='" + order.getStartDate()
                            + "' and mor.orda<='" + order.getEndDate() + "' ");
                }
            }
            hsql.append(" and smor.orid=mor.srid and smor.orid=t.id.orid  and  mor.usid = us.usid and v5.id.pmky='TDLB' and v5.id.pmcd=mor.notef and v51.id.pmky='TPFS' and v51.id.pmcd=mor.tpfs and v52.id.pmky='DDZT' and v52.id.pmcd=mor.ddzt");
            hsql.append(" order by mor.orda desc,mor.orti desc, mor.orid desc ");
            ps = this.findPage(hsql.toString(), pageSize, page, url);
        } else {
            hsql.append("select distinct new map( mor.orda as orda,mor.orti as orti,mor.orid as orid,mor.srid as srid,mor.usid as usid,us.corpname as corpname,us.lname as lname,mor.ddzt as ddzt,mor.orda as orda,mor.zfmont as zfmont,mor.tpmont as tpmont,mor.stdt as stdt,mor.tpsx as tpsx,mor.tpmont as tpmont,mor.tpfs as tpfs,v5.pmva as strtdlb,mor.ddzt as ddzt,v51.pmva as strtpfs,v52.pmva as strddzt,mor.yhamnt as yhamnt,tor.ornm as ornm) from MOrder mor,Custom us,Sysparv5 v5,Sysparv5 v51,Sysparv5 v52,MOrder  mor2,TOrder tor where mor.srid=mor2.orid and mor2.orid=tor.id.orid and mor.ortp in ('02','03') and  mor.ddzt in ('06','88','27','30','33')   ");
            if (order.getOrid() != null && !order.getOrid().equals("")) {
                hsql.append(" and mor.orid='" + order.getOrid() + "'");
                List list = null;
                list = getSonCustom(usid, list);
                if (list.size() > 0) {
                    hsql.append(" and mor.usid in (");
                    for (int i = 0; i < list.size(); i++) {
                        if (i < list.size() - 1) {
                            hsql.append("'" + list.get(i).toString() + "',");
                        } else {
                            hsql.append("'" + list.get(i).toString() + "' )");
                        }
                    }
                } else {
                    hsql.append(" and mor.usid='" + usid + "'");
                }
            } else if (order.getXrid() != null && !order.getXrid().equals("")) {
                hsql.append(" and mor.srid='" + order.getXrid() + "'");
                List list = null;
                list = getSonCustom(usid, list);
                if (list.size() > 0) {
                    hsql.append(" and mor.usid in (");
                    for (int i = 0; i < list.size(); i++) {
                        if (i < list.size() - 1) {
                            hsql.append("'" + list.get(i).toString() + "',");
                        } else {
                            hsql.append("'" + list.get(i).toString() + "' )");
                        }
                    }

                } else {
                    hsql.append(" and mor.usid='" + usid + "'");
                }
            } else {
                if (cu.getLgtp().equals("02")) {// 团体用户
                    if (order.getRadiobutton1() == 0) {
                        // 自己
                        hsql.append(" and mor.usid='" + usid + "'");
                    }
                    if (order.getRadiobutton1() == 1) {
                        // 自己+所有子用户
                        List list = null;
                        list = getSonCustom(usid, list);
                        if (list.size() > 0) {
                            hsql.append(" and mor.usid in (");
                            for (int i = 0; i < list.size(); i++) {
                                hsql.append("'" + list.get(i).toString() + "',");
                            }
                            hsql.append("'" + usid + "')");
                        } else {
                            hsql.append(" and mor.usid='" + usid + "'");
                        }
                    }
                    if (order.getRadiobutton1() == 2) { // 所有子用户
                        List list = null;
                        list = getSonCustom(usid, list);
                        if (list.size() > 0) {
                            hsql.append(" and mor.usid in (");
                            for (int i = 0; i < list.size(); i++) {
                                if (i < list.size() - 1) {
                                    hsql.append("'" + list.get(i).toString() + "',");
                                } else {
                                    hsql.append("'" + list.get(i).toString() + "' )");
                                }
                            }

                        } else {
                            hsql.append(" and mor.usid='" + usid + "'");
                        }
                    }
                    if (order.getRadiobutton1() == 3) {
                        // 指定子用户
                        List list = null;
                        list = getSonCustom(order.getChildCustom(), list);
                        // 判断子用户是否还有子用户
                        if (list.size() > 0) {
                            hsql.append(" and mor.usid in (");
                            for (int i = 0; i < list.size(); i++) {
                                hsql.append("'" + list.get(i).toString() + "',");
                            }
                            // 将子用户的操作员一起查询
                            hsql.append("'" + order.getChildCustom() + "')");
                        } else {
                            hsql.append(" and mor.usid='" + order.getChildCustom() + "'");
                        }
                    }
                } else {
                    hsql.append(" and mor.usid in ('" + cu.getUsid() + "')");
                }
                if (!order.getTpfs().equals("99")) {
                    hsql.append(" and mor.tpfs ='" + order.getTpfs() + "'");
                }
                if (!order.getTdlb().equals("99")) {
                    hsql.append(" and mor.notef ='" + order.getTdlb() + "' ");
                }
                if (0 == order.getRadiobutton2()) {// 消费（游览）日期
                    hsql.append(" and mor2.stdt>='" + order.startDate
                            + "' and mor2.stdt<='" + order.getEndDate() + "' ");
                } else if (1 == order.getRadiobutton2()) {// 退订单日期
                    hsql.append(" and mor.orda>='" + order.getStartDate()
                            + "' and mor.orda<='" + order.getEndDate() + "' ");
                }
            }
            hsql.append("  and  mor.usid = us.usid and v5.id.pmky='TDLB' and v5.id.pmcd=mor.notef and v51.id.pmky='TPFS' and v51.id.pmcd=mor.tpfs and v52.id.pmky='DDZT' and v52.id.pmcd=mor.ddzt");
            hsql.append(" order by mor.orda desc,mor.orti desc, mor.orid desc ");
            System.out.println("==============>>>>sql:"+hsql);
            ps = this.findPage(hsql.toString(), pageSize, page, url);
        }
        return ps;
    }
    
	public PaginationSupport queryAllMsOrderByGroupId(String usid, QueryOrder order, int page, int pageSize, String url,String groupId) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		// 将导游和旅行社分开查询

		Custom cu = (Custom) this.get(Custom.class, usid);
		if (cu.getTtlb().equals("02")) {
			hsql.append(
					"select distinct new map(mor.orda as orda,mor.orti as orti,mor. mor.orid as orid,mor.srid as srid,mor.usid as usid,us.corpname as corpname,us.lname as lname,mor.ddzt as ddzt,mor.orda as orda,mor.zfmont as zfmont,mor.tpmont as tpmont,mor.stdt as stdt,mor.tpsx as tpsx,mor.tpmont as tpmont,mor.tpfs as tpfs,v5.pmva as strtdlb,mor.ddzt as ddzt,v51.pmva as strtpfs,v52.pmva as strddzt,mor.yhamnt as yhamnt,t.ornm as ornm) "
					+ "from MOrder mor,Custom us,Sysparv5 v5,Sysparv5 v51,Sysparv5 v52,MOrder smor,TOrder t,MOrder mor2  ");
			if(groupId !=null && groupId.length()>0){
				hsql.append(",Esbscenicareatab provider,Galcompanyinfotab gal,Companyscenic c");
			}
			
				hsql.append(" where (mor.ortp='01' or mor.ortp='02') ");
	    	
	    	if(groupId !=null && groupId.length()>0){
	    		hsql.append(" and t.id.iscenicid=provider.iscenicid and c.id.icompanyinfoid = gal.icompanyinfoid and c.id.iscenicid = provider.iscenicid and gal.icompanyinfoid = '"+groupId+"'  "); 
	    	}
			//hsql.append("where mor.srid=mor2.orid  mor.ortp in ('02','03') and  mor.ddzt in ('06','88','27','30','33')  and (t.dyusid='" + usid + "' or mor.usid='" + usid + "')");
	    	hsql.append("where mor.srid=mor2.orid  mor.ortp in ('02','03')  and (t.dyusid='" + usid + "' or mor.usid='" + usid + "')");
			if (order.getOrid() != null && !order.getOrid().equals("")) {
				hsql.append(" and mor.orid='" + order.getOrid() + "'");
			} else if (order.getXrid() != null && !order.getXrid().equals("")) {
				hsql.append(" and mor.srid='" + order.getXrid() + "'");
			} else {
				if (!order.getTpfs().equals("99")) {
					hsql.append(" and mor.tpfs ='" + order.getTpfs() + "'");
				}
				if (!order.getTdlb().equals("99")) {
					hsql.append(" and mor.notef ='" + order.getTdlb() + "' ");
				}
				if (0 == order.getRadiobutton2()) {// 消费（游览）日期
					hsql.append(
							" and mor2.stdt>='" + order.startDate + "' and mor2.stdt<='" + order.getEndDate() + "' ");
				} else if (1 == order.getRadiobutton2()) {// 退订单日期
					hsql.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate()
							+ "' ");
				}
			}
			hsql.append(
					" and smor.orid=mor.srid and smor.orid=t.id.orid  and  mor.usid = us.usid and v5.id.pmky='TDLB' and v5.id.pmcd=mor.notef and v51.id.pmky='TPFS' and v51.id.pmcd=mor.tpfs and v52.id.pmky='DDZT' and v52.id.pmcd=mor.ddzt");
			hsql.append(" order by mor.orda desc,mor.orti desc, mor.orid desc ");
			ps = this.findPage(hsql.toString(), pageSize, page, url);
			
		} else {
			hsql.append(
					"select distinct new map( mor.orda as orda,mor.orti as orti,mor.orid as orid,mor.srid as srid,mor.usid as usid,us.corpname as corpname,us.lname as lname,mor.ddzt as ddzt,mor.orda as orda,mor.zfmont as zfmont,mor.tpmont as tpmont,mor.stdt as stdt,mor.tpsx as tpsx,mor.tpmont as tpmont,mor.tpfs as tpfs,v5.pmva as strtdlb,mor.ddzt as ddzt,v51.pmva as strtpfs,v52.pmva as strddzt,mor.yhamnt as yhamnt,tor.ornm as ornm) from MOrder mor,Custom us,Sysparv5 v5,Sysparv5 v51,Sysparv5 v52,MOrder  mor2,TOrder tor   ");
			if(groupId !=null && groupId.length()>0){
				hsql.append(",Esbscenicareatab provider,Galcompanyinfotab gal,Companyscenic c");
			}
			//hsql.append(" where mor.srid=mor2.orid and mor2.orid=tor.id.orid and mor.ortp in ('02','03') and  mor.ddzt in ('06','88','27','30','33')");
			hsql.append(" where mor.srid=mor2.orid and mor2.orid=tor.id.orid and mor.ortp in ('02','03') ");
			if(groupId !=null && groupId.length()>0){
	    		hsql.append(" and tor.id.iscenicid=provider.iscenicid and c.id.icompanyinfoid = gal.icompanyinfoid and c.id.iscenicid = provider.iscenicid and gal.icompanyinfoid = '"+groupId+"'  "); 
	    	}
			if (order.getOrid() != null && !order.getOrid().equals("")) {
				hsql.append(" and mor.orid='" + order.getOrid() + "'");
				List list = null;
				list = getSonCustom(usid, list);
				if (list.size() > 0) {
					hsql.append(" and mor.usid in (");
					for (int i = 0; i < list.size(); i++) {
						if (i < list.size() - 1) {
							hsql.append("'" + list.get(i).toString() + "',");
						} else {
							
							hsql.append("'" + list.get(i).toString() + "' )");
						}
					}
				} else {
					hsql.append(" and mor.usid='" + usid + "'");
				}
			} else if (order.getXrid() != null && !order.getXrid().equals("")) {
				hsql.append(" and mor.srid='" + order.getXrid() + "'");
				List list = null;
				list = getSonCustom(usid, list);
				if (list.size() > 0) {
					hsql.append(" and mor.usid in (");
					for (int i = 0; i < list.size(); i++) {
						if (i < list.size() - 1) {
							hsql.append("'" + list.get(i).toString() + "',");
						} else {
							hsql.append("'" + list.get(i).toString() + "' )");
						}
					}

				} else {
					hsql.append(" and mor.usid='" + usid + "'");
				}
			} else {
				if (cu.getLgtp().equals("02")) {// 团体用户
					if (order.getRadiobutton1() == 0) {
						// 自己
						hsql.append(" and mor.usid='" + usid + "'");
					}
					if (order.getRadiobutton1() == 1) {
						// 自己+所有子用户
						List list = null;
						list = getSonCustom(usid, list);
						if (list.size() > 0) {
							hsql.append(" and mor.usid in (");
							for (int i = 0; i < list.size(); i++) {
								hsql.append("'" + list.get(i).toString() + "',");
							}
							hsql.append("'" + usid + "')");
						} else {
							hsql.append(" and mor.usid='" + usid + "'");
						}
					}
					if (order.getRadiobutton1() == 2) { // 所有子用户
						List list = null;
						list = getSonCustom(usid, list);
						if (list.size() > 0) {
							hsql.append(" and mor.usid in (");
							for (int i = 0; i < list.size(); i++) {
								if (i < list.size() - 1) {
									hsql.append("'" + list.get(i).toString() + "',");
								} else {
									hsql.append("'" + list.get(i).toString() + "' )");
								}
							}

						} else {
							hsql.append(" and mor.usid='" + usid + "'");
						}
					}
					if (order.getRadiobutton1() == 3) {
						// 指定子用户
						List list = null;
						list = getSonCustom(order.getChildCustom(), list);
						// 判断子用户是否还有子用户
						if (list.size() > 0) {
							hsql.append(" and mor.usid in (");
							for (int i = 0; i < list.size(); i++) {
								hsql.append("'" + list.get(i).toString() + "',");
							}
							// 将子用户的操作员一起查询
							hsql.append("'" + order.getChildCustom() + "')");
						} else {
							hsql.append(" and mor.usid='" + order.getChildCustom() + "'");
						}
					}
				} else {
					hsql.append(" and mor.usid in ('" + cu.getUsid() + "')");
				}
				if (!order.getTpfs().equals("99")) {
					hsql.append(" and mor.tpfs ='" + order.getTpfs() + "'");
				}
				if (!order.getTdlb().equals("99")) {
					hsql.append(" and mor.notef ='" + order.getTdlb() + "' ");
				}
				if (0 == order.getRadiobutton2()) {// 消费（游览）日期
					hsql.append(
							" and mor2.stdt>='" + order.startDate + "' and mor2.stdt<='" + order.getEndDate() + "' ");
				} else if (1 == order.getRadiobutton2()) {// 退订单日期
					hsql.append(" and mor.orda>='" + order.getStartDate() + "' and mor.orda<='" + order.getEndDate()
							+ "' ");
				}
			}
			hsql.append(
					"  and  mor.usid = us.usid and v5.id.pmky='TDLB' and v5.id.pmcd=mor.notef and v51.id.pmky='TPFS' and v51.id.pmcd=mor.tpfs and v52.id.pmky='DDZT' and v52.id.pmcd=mor.ddzt");
			hsql.append(" order by mor.orda desc,mor.orti desc, mor.orid desc ");
			System.out.println("==============>>>>sql:" + hsql);
			ps = this.findPage(hsql.toString(), pageSize, page, url);
		}
		return ps;
	}
    


    /**
     * 根据主用户查询子用户 用于权限判断 Describe:
     *
     * @auth:huangyuqi
     * @param usid主用户编号
     * @param bs,李进增加，从 CUSTOMDAO中拷过来。
     * @return所有子用户列表，如('a','b','c','d') return:String Date:2011-10-31
     */
    private String getUsids(String usid, StringBuffer bs,String loginusid) {


        List list = new ArrayList();
        list.add(usid);
        //loginusid 是当前登录用户
        list = getSonCustom(list,usid,loginusid);
        if(list!=null && list.size()>=1){
            for (int i = 0; i <list.size(); i++) {
                if(i==list.size()-1){
                    bs.append("'"+list.get(i)+"'");
                }else{
                    bs.append("'"+list.get(i)+"'"+",");
                }
            }
        }


        return bs.toString();
    }

    /** 用于查询权限判断的  */
    private List getSonCustom(List list, String usid,String loginusid) {
        String hsql = " from Custom where susid = '" + usid + "' ";
        //System.out.println("loginusid="+loginusid);
        List list1 = this.find(hsql);
        if (list1 != null && list1.size() >= 1) {
            for (int i = 0; i < list1.size(); i++) {
                Custom custom = (Custom) list1.get(i);
                //判断是不是分社，如果是分社将判断权限
                //找出这个分社以及操作；
				/*
				 * 总社   lgtp = 02 ustp = 01 
                 * 分社   lgtp = 02 ustp = 02   上级用户 ustp = 01
                 * 操作员 lgtp = 02 ustp = 02   上级用户 ustp = 02

				 */
                if (custom.getUsid().equals(loginusid))
                // 这是当前登录的用户
                {
                    list.add(custom.getUsid());
                    list = getSonCustom(list, custom.getUsid(), loginusid);
                } else {
                    //不是当前登录的用户
                    if (custom.getLgtp().equals("02")
                            && custom.getUstp().equals("02")
                            && custom.getUsqx().substring(0, 1).equals("1")) {
                        //是分社
                        if (getUsqx8(custom) && !custom.getUsid().equals(usid)) // 不用在查数据库了，直接判断
                        { // 分社将用户共享，可以给其它用户查询；
                            list.add(custom.getUsid());
                            list = getSonCustom(list, custom.getUsid(),
                                    loginusid);
                        }

                    } else {
                        //不是分社
                        list.add(custom.getUsid());
                        list = getSonCustom(list, custom.getUsid(), loginusid);
                    }
                }
            }
        }
        return list;
    }
    /**
     * 判断第8位是不是有权限
     * 李进新加，2013-12-13
     * @param usid
     * @return
     */

    private boolean getUsqx8(String usid)
    {

        boolean ib_true  = false;
        try{
            String mainusid = this.getMainCustom(usid);
            Custom mcu = (Custom) this.get(Custom.class, mainusid);
            mainusid  = mcu.getUsqx().substring(7, 8);
            if (mainusid. equals("1"))
                ib_true = true;
            else
                ib_true = false;
        }catch(Exception e)  //出错后，直接返回没有权限
        {
            ib_true = false;
        }
        return  ib_true;
    }

    /**
     * 判断第8位是不是有权限
     * 李进新加，2013-12-13
     * @param usid
     * @return
     */

    private boolean getUsqx8(Custom mcu) {

        boolean ib_true = false;
        try {

            String mainusid = mcu.getUsqx().substring(7, 8);
            if (mainusid.equals("1"))
                ib_true = true;
            else
                ib_true = false;
        } catch (Exception e) // 出错后，直接返回没有权限
        {
            ib_true = false;
        }
        return ib_true;
    }

    public boolean updateNotea(String orid, String notea) {
        try {
            String sql = "update MOrder m set m.note='"+notea+"' where m.orid='"+orid+"' or m.srid='"+orid+"'";
            int i =this.bulkUpdate(sql);
            if (i>0) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 獲取需要核銷的訂單
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getNeedConsumeMOrderList(String today) {
		 String hsql = "select new map(m.orid as orid,m.noteh as noteh) from MOrder m,TOrder t where  m.orid=t.id.orid and m.noteg!='1' and (m.ddzt='27' or m.ddzt='02') and  t.dtenddate >= '" + today + "' ";
	     List list1 = this.find(hsql);
	     String hsq2 = "select new map(m.orid as orid,m.noteh as noteh) from MOrder m,TOrder t,Stssalesvouchertab sst,Ticketchecklist tk where m.orid=t.id.orid and  m.orid=sst.szsalesvoucherno and m.noteg!='1' and m.ddzt='11' and sst.id.isalesvoucherid=tk.isalesvoucherid and sst.id.iticketstationid=tk.iticketstationid and sst.iscenicid=tk.iscenicid and  t.dtenddate >= '" + today + "' ";
	     List list2 = this.find(hsq2);
	     list1.addAll(list2);
	     //List list=list1+list2;
		return list1;
	}

    /**
     * 獲取需要核銷的訂單
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List getNeedConsumeLOrderList(String today) {
        String hsql = "select new map(l.id.orid as orid,l.noteh as noteh) from LOrder l where l.dtenddate >= '" + today + "' and (l.noteg is null or l.noteg!='1') and (l.ddzt='27' or l.ddzt='02') and (l.isb is null or l.isb!=1) and (l.isd is null or l.isd < 5) ";
        List list1 = this.find(hsql);
        String hsq2 = "select new map(l.id.orid as orid,l.noteh as noteh) from LOrder l,Stssalesvouchertab sst,Ticketchecklist tk where l.id.orid=sst.szsalesvoucherno and (l.noteg is null or l.noteg!='1') and l.ddzt='11' and sst.id.isalesvoucherid=tk.isalesvoucherid and sst.id.iticketstationid=tk.iticketstationid and sst.iscenicid=tk.iscenicid and  l.dtenddate >= '" + today + "' ";
        List list2 = this.find(hsq2);
        list1.addAll(list2);
        //List list=list1+list2;
        return list1;
    }
    
    public List<MOrder> getMorderListInfo(String orid){
    	 List<MOrder> ml = this.find("from MOrder m where m.orid='"+orid+"' and (m.ddzt='02' or m.ddzt='11' or (m.ddzt='00' and m.zffs='09'))");
		 return ml;
    }

	@Override
	public List<Map> getOrderListInfo(String param,String msql) throws Exception {
		String sql = "select CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.usid,t.ibusinessid,t.sztravelbillno,t.iregionalid,t.tdlx,cast(t.ddzt as varchar(2)) as ddzt,\r\n" + 
				"t.dtenddate,t.dtstartdate,t.dyusid,t.ornm,t.orhm,t.orzj,t.orph,t.mont,t.yhamnt,t.zfmont,t.ischupiao,t.notea,c.corpname,c.lname,dy.password,m.isjl as isjl,m.zfusid as zfusid,m.zffs as zffs,t.ornote1,\r\n" + 
				"t.ornote2,t.ornote3,t.ornote4,t.ornote5,t.ornote6,t.ornote7,t.ornote8,t.ornote9,t.ornote10,c.lgtp,c.note2 as jsfz,m.note,c.note6 \r\n" + 
				"from T_order t left outer join custom dy on dy.usid=t.dyusid and dy.status='01',custom c,m_order m\r\n" + 
				"where  ( upper(trim(orhm))='"+param+"' or CASt(t.orid as varchar(17))='"+param+"' or t.ornote4 = '"+param+"' or t.orph='"+param+"' ) \r\n" + msql +
				"and ( t.ddzt='02' or( t.ddzt='00' and (m.zffs='05' or m.zffs='09')))  and m.orid=t.orid and c.usid=t.usid";
		return this.findBySqlToMap(sql, new Object[] {});
	}

	@Override
	public void updateOrderDdzt(String orid, String ddzt) {
		String hql="from LOrder where orid="+orid;
		List<LOrder> list = this.find(hql);
		LOrder lOrder = list.get(0);
		lOrder.setDdzt(ddzt);
		this.update(lOrder);
	}
}
