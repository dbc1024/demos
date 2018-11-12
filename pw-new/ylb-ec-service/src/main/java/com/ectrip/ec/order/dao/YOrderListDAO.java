package com.ectrip.ec.order.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.dao.idao.IYOrderListDAO;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.venuemarketing.Prdtripvenuemanage;
/**
 * 
* @ClassName: YOrderListDAO 
* @Description: 网上预定服务商订票明细 
* @author Dicky
* @date Oct 11, 2011 2:49:45 PM 
*
 */
@Repository
public class YOrderListDAO extends GenericDao implements IYOrderListDAO {
	@Autowired
	private TicketService ticketService;
	/**
	 * (�� Javadoc) 
	* <p>Title: saveYOrderList</p> 
	* <p>Description: </p> 
	* @param yorderlist 
	* @see com.ectrip.order.dao.idao.IYOrderListDAO#saveYOrderList(com.ectrip.model.order.YOrderlist)
	 */
	public void saveYOrderList(YOrderlist yorderlist) {
		this.save(yorderlist);		
	}
    /**
     * (�� Javadoc) 
    * <p>Title: getYOrderListList</p> 
    * <p>Description:����Ԥ�������̶�����ϸList </p> 
    * @param orid
    * @param iscenicid
    * @return 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
    * @see com.ectrip.order.dao.idao.IYOrderListDAO#getYOrderListList(java.lang.String, java.lang.String)
     */
	public List getYOrderListList(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException {
	    String hql="select new map(y.id.iscenicid as iscenicid,y.dtstartdate as dtstartdate,y.dtenddate as dtenddate,y.pric as pric,y.numb as numb,y.amnt as amnt,y.yhnumb as yhnumb,y.yhamnt as yhamnt,e.sztickettypename as sztickettypename,kind.szcrowdkindname as szcrowdkindname,(select substr(z.dtstartdate,0,10) from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharfdate,(select trip.tripname from YZorderlist z,Trip trip where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid and z.tripid=trip.tripid) as tripname,(select substr(z.dtstartdate,12,5) from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharftime,(select v1.ivenueareaname from YZorderlist z,Venuearea v1,Venue v2 where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid =y.id.orid and z.id.iscenicid = y.id.iscenicid and z.ivenueid=v1.ivenueid and z.ivenueareaid=v1.ivenueareaid and v1.ivenueid=v2.ivenueid) as wharfname)  from YOrderlist y,Edmtickettypetab e,Edpcrowdkindtab kind  where kind.icrowdkindid=y.icrowdkindid and y.id.orid='"+orid+"' and y.id.iscenicid='"+iscenicid+"' and y.itickettypeid=e.itickettypeid order by e.sztickettypename,y.dtstartdate,9,10,11,12";
		List list=this.find(hql);
//		if(list!=null&&list.size()>0){
//			YOrderlist yorderlist=null;
//			List zlist=null;
//			for(int i=0;i<list.size();i++){
//				yorderlist=new YOrderlist();
//				BeanUtils.populate(yorderlist, (Map)list.get(i));
//				zlist=this.find("select new map(z.id.zorderlistid as zorderlistid,z.id.orderlistid as orderlistid,z.id.orid as orid,z.id.iscenicid as iscenicid,z.icrowdkindpriceid as icrowdkindpriceid,z.icrowdkindid as icrowdkindid,z.itickettypeid as itickettypeid,z.iztickettypeid as iztickettypeid,z.dtstartdate as dtstartdate,z.dtenddate as dtenddate,z.tripid as tripid,z.ivenueid as ivenueid,z.ivenueareaid as ivenueareaid,z.ivenueseatsid as ivenueseatsid,z.zpric as zpric,z.jsprice as jsprice,z.znumb as znumb,z.zyhnumb as zyhnumb,z.zyhamnt as zyhamnt,z.zamnt as zamnt,ticket.sztickettypename as sztickettypename,e.szcrowdkindname as szcrowdkindname) from YZorderlist z,Edmtickettypetab ticket,Edpcrowdkindtab e where  z.iztickettypeid=ticket.itickettypeid and  z.icrowdkindid=e.icrowdkindid  and  z.id.orid='"+yorderlist.getOrid()+"' and z.id.iscenicid="+yorderlist.getIscenicid()+" and z.id.orderlistid="+yorderlist.getOrderlistid()+"");
//				yorderlist.setZorderlist(zlist);
//				list.set(i, yorderlist);
//			}
//		}
		return list;
	}
	/**
	 * (�� Javadoc) 
	* <p>Title: getYOrderListId</p> 
	* <p>Description: ��ȡ����id  yorderlistid  </p> 
	* @return 
	* @see com.ectrip.order.dao.idao.IYOrderListDAO#getYOrderListId()
	 */
	public int getLastYOrderListId() {
		String sql ="select max(yol.id.orderlistid) from YOrderlist yol";
		List list = this.find(sql);
		String s =  list.get(0).toString();
		return Integer.parseInt(s);
	}
	/**
	 * (非 Javadoc) 
	* <p>Title: getYOrderListChildList</p> 
	* <p>Description:获取 orid订单的子订单的增量、退订  网上预订服务商订单  详细信息 </p> 
	* @param orid
	* @param iscenicid
	* @return 
	* @see com.ectrip.order.dao.idao.IYOrderListDAO#getYOrderListChildList(java.lang.String, java.lang.String)
	 */			
	public List getYOrderListChildList(String orid, String iscenicid) {
//	    	String hql="select new map(y.id.orid as orid,y.id.iscenicid as iscenicid,"
//	    			+ "y.dtstartdate as dtstartdate,y.dtenddate as dtenddate,y.pric as pric,"
//	    			+ "y.numb as numb,y.amnt as amnt,y.yhnumb as yhnumb,e.sztickettypename as sztickettypename,"
//	    			+ "kind.szcrowdkindname as szcrowdkindname,(select substr(z.dtstartdate,0,10) "
//	    			+ "from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and"
//	    			+ " z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharfdate,"
//	    			+ "(select trip.tripname from YZorderlist z,Trip trip where z.id.orderlistid = y.id.orderlistid "
//	    			+ "and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid and "
//	    			+ "z.tripid=trip.tripid) as tripname,(select substr(z.dtstartdate,12,5) from "
//	    			+ "YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and "
//	    			+ "z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharftime,"
//	    			+ "(select v1.ivenueareaname from YZorderlist z,Venuearea v1,Venue v2 where "
//	    			+ "z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid =y.id.orid and "
//	    			+ "z.id.iscenicid = y.id.iscenicid and z.ivenueid=v1.ivenueid and z.ivenueareaid=v1.ivenueareaid "
//	    			+ "and v2.ivenueid=z.ivenueid) as wharfname,provider.szscenicname as szscenicname,y.yhamnt as yhamnt)  "
//	    			+ "from YOrderlist y,Edmtickettypetab e,Edpcrowdkindtab kind,Esbscenicareatab provider  where "
//	    			+ "kind.icrowdkindid=y.icrowdkindid and  y.id.orid='"+orid+"' and y.id.iscenicid='"+iscenicid
//	    			+"' and y.itickettypeid=e.itickettypeid  and provider.iscenicid=y.id.iscenicid order by e.sztickettypename,"
//	    			+ "y.dtstartdate,10,11,12,13";
		String hql="select new map(y.id.orid as orid,y.id.iscenicid as iscenicid,y.id.orderlistid as orderlistid,y.dtstartdate as dtstartdate,"
				+ "y.dtenddate as dtenddate,y.pric as pric,y.numb as numb,y.amnt as amnt,y.yhnumb as yhnumb,"
				+ "y.yhamnt as yhamnt,y.itickettypeid as itickettypeid,y.icrowdkindid as icrowdkindid) "
				+ "from YOrderlist y  where y.id.orid='"+orid+"' and "
				+ "y.id.iscenicid='"+iscenicid+"' order by y.dtstartdate";
		
//		provider.szscenicname as szscenicname,
		 List<Map> list = this.find(hql);
		 
		 StringBuffer tickettypeids = new StringBuffer();
		 StringBuffer icrowdkindids = new StringBuffer();
		 StringBuffer iscenicids = new StringBuffer();
		 for (int i = 0; i < list.size(); i++) {
			 tickettypeids.append(((Map)list.get(i)).get("itickettypeid"));
			 icrowdkindids.append(((Map)list.get(i)).get("icrowdkindid"));
			 iscenicids.append(((Map)list.get(i)).get("iscenicid"));
			 if(i!=(list.size()-1)) {
				 tickettypeids.append(",");
				 icrowdkindids.append(",");
				 iscenicids.append(",");
			 }
		}
//		 e.sztickettypename as sztickettypename,
		List<Map> edmtickettypetabs = ticketService.getTicketTypeListByIds(tickettypeids.toString());
		for (Map map : list) {
			String listItickettypeid = map.get("itickettypeid").toString();
			for (Map edmtickettypetab : edmtickettypetabs) {
				String edmtickettypetabItickettypeid = edmtickettypetab.get("itickettypeid").toString();
				if(listItickettypeid.equals(edmtickettypetabItickettypeid)) {
					map.put("sztickettypename", edmtickettypetab.get("sztickettypename"));
				}
			}
		}
//		Edpcrowdkindtab
//		kind.szcrowdkindname as szcrowdkindname
//		kind.icrowdkindid=y.icrowdkindid
		List<Map> crowdKindLists = ticketService.crowdKindListByIds(icrowdkindids.toString());
		for (Map map : list) {
			String listIcrowdkindid = map.get("icrowdkindid").toString();
			for (Map crowdKindList : crowdKindLists) {
				if(listIcrowdkindid.equals(crowdKindList.get("icrowdkindid").toString())) {
					map.put("szcrowdkindname", crowdKindList.get("szcrowdkindname"));
				}
			}
		}
//		Esbscenicareatab
//		provider.szscenicname as szscenicname,
//		provider.iscenicid=y.id.iscenicid
//		provider.szscenicname as szscenicname
		List<Map> providers = ticketService.getProvidersByIds(iscenicids.toString());
		for (Map map : list) {
			String listIscenicid = map.get("iscenicid").toString();
			for (Map provider : providers) {
				if(listIscenicid.equals(provider.get("iscenicid").toString())) {
					map.put("szscenicname", provider.get("szscenicname"));
				}
			}
		}
		
//		(select substr(z.dtstartdate,0,10) "+ "from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and"
//    			+ " z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharfdate,
//		(select substr(z.dtstartdate,12,5) from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and "
//				+ "z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharftime,
		String hql1="select new map(substr(z.dtstartdate,0,10) as wharfdate,z.id.orderlistid as orderlistid,"
				+ "z.id.orid as orid,z.id.iscenicid as iscenicid,z.tripid as tripid,z.ivenueid as ivenueid,"
				+ "substr(z.dtstartdate,12,5) as wharftime,z.ivenueid as ivenueid,z.ivenueareaid as ivenueareaid)from YZorderlist z where z.tripid != 0";
		List<Map> YZorderlists = this.find(hql1);
			if(YZorderlists.size()>0) {
			StringBuffer sb = new StringBuffer();
			StringBuffer sb1 = new StringBuffer();
			for (Map map : list) {
				String listOrderlistid = map.get("orderlistid").toString();
				String listOrid = map.get("orid").toString();
				String listIscenicid = map.get("iscenicid").toString();
				for (Map YZorderlist : YZorderlists) {
					String YZorderlistOrderlistid = YZorderlist.get("orderlistid").toString();
					String YZorderlistorid = YZorderlist.get("orid").toString();
					String YZorderlistiscenicid= YZorderlist.get("iscenicid").toString();
					if(listOrderlistid.equals(YZorderlistOrderlistid)&&listOrid.equals(YZorderlistorid)&&listIscenicid.equals(YZorderlistiscenicid)) {
						map.put("wharfdate", YZorderlist.get("wharfdate"));
						map.put("wharftime", YZorderlist.get("wharftime"));
						map.put("ivenueareaid", YZorderlist.get("ivenueareaid"));
						map.put("ivenueid", YZorderlist.get("ivenueid"));
						sb.append(YZorderlist.get("tripid")+",");
						sb1.append(YZorderlist.get("ivenueid")+",");
					}
				}
			}
	//		(select trip.tripname from YZorderlist z,Trip trip where z.id.orderlistid = y.id.orderlistid "
	//    			+ "and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid and "
	//    			+ "z.tripid=trip.tripid) as tripname,
			String tripids = sb.substring(0, sb.length()-1);
			List<Map> trips = ticketService.getTripByTripids(tripids);
			for (Map map : list) {
				String listOrderlistid = map.get("orderlistid").toString();
				String listOrid = map.get("orid").toString();
				String listIscenicid = map.get("iscenicid").toString();
				for (Map trip : trips) {
					String tripOrderlistid = trip.get("orderlistid").toString();
					String tripOrid = trip.get("orid").toString();
					String tripIscenicid = trip.get("iscenicid").toString();
					if(listOrderlistid.equals(tripOrderlistid)&&listOrid.equals(tripOrid)&&listIscenicid.equals(tripIscenicid)) {
						map.put("tripname", trip.get("tripname"));
					}
				}
			}
	//		(select v1.ivenueareaname from YZorderlist z,Venuearea v1,Venue v2 where "
	//    			+ "z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid =y.id.orid and "
	//    			+ "z.id.iscenicid = y.id.iscenicid and z.ivenueid=v1.ivenueid and z.ivenueareaid=v1.ivenueareaid "
	//    			+ "and v2.ivenueid=z.ivenueid) as wharfname,
			String ivenueids = sb1.substring(0, sb1.length()-1);
			List<Map> venueareas = ticketService.getVenueareaByIvenueids(ivenueids);
			for (Map map : list) {
				String listIvenueareaid = map.get("ivenueareaid").toString();
				String listIvenueid = map.get("ivenueid").toString();
				for (Map venuearea : venueareas) {
					String venueareaIvenueareaid = venuearea.get("ivenueareaid").toString();
					String venueareaIvenueid = venuearea.get("ivenueid").toString();
					if(listIvenueareaid.equals(venueareaIvenueareaid)&&listIvenueid.equals(venueareaIvenueid)) {
						map.put("wharfname", venuearea.get("ivenueareaname"));
					}
				}
			}
		}
		return list;
	}
	
	
	
	public List getOrderOpearHistory(String orid) {
    	String hql="select new map(y.id.orid as orid,y.id.iscenicid as iscenicid,y.dtstartdate as dtstartdate,y.dtenddate as dtenddate,y.pric as pric,y.numb as numb,y.amnt as amnt,e.sztickettypename as sztickettypename,kind.szcrowdkindname as szcrowdkindname,(select substr(z.dtstartdate,0,10) from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharfdate,(select trip.tripname from YZorderlist z,Trip trip where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid and z.tripid=trip.tripid) as tripname,(select substr(z.dtstartdate,12,5) from YZorderlist z where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid = y.id.orid and z.id.iscenicid = y.id.iscenicid) as wharftime,(select v1.ivenueareaname from YZorderlist z,Venuearea v1,Venue v2 where z.id.orderlistid = y.id.orderlistid and z.tripid != 0 and z.id.orid =y.id.orid and z.id.iscenicid = y.id.iscenicid and z.ivenueid=v1.ivenueid and z.ivenueareaid=v1.ivenueareaid and v2.ivenueid=z.ivenueid ) as wharfname,provider.szscenicname as szscenicname)  from YOrderlist y,Edmtickettypetab e,Edpcrowdkindtab kind,Esbscenicareatab provider  where kind.icrowdkindid=y.icrowdkindid and  y.id.orid in (select orid from MOrder morder where morder.srid='"+orid+"' )  and y.itickettypeid=e.itickettypeid and provider.iscenicid=y.id.iscenicid order by e.sztickettypename,y.dtstartdate,10,11,12,13";
	return this.find(hql);
	}
	
	/**
	 * 
	 * Describe:���ݵ���֤�������ȡ������Ϣ
	 * @auth:yangguang
	 * @param orhm
	 * @return
	 * return:Custom
	 * Date:2011-11-30
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public Custom queryDaoyouByOrhm(String orhm) throws IllegalAccessException, InvocationTargetException{
		String hql="select new map(usid as usid,lname as lname,zjhm as zjhm,mobile as mobile) from Custom where zjhm='"+orhm+"' and lgtp='02' and ttlb='02' and status='01'";
		List list=find(hql);
		if(list!=null&&list.size()>0){
			Custom custom=new Custom();
			BeanUtils.populate(custom, (Map)list.get(0));
			return custom;
		}else{
			return null;
		}
	}
	
	public List<Edmticketcomposepricetab> getSonPrice(Long icrowkindid,Long itckettypeid){
		String hql=" from Edmticketcomposepricetab where id.icrowdkindpriceid="+icrowkindid+" and itickettypeid="+itckettypeid+"";
		return find(hql);
	}
	
	
	public Prdtripvenuemanage getPrdtripvenuemanage(Long iscenicid,Long tripid,Long itickettypeid,String date){
		String hql=" from Prdtripvenuemanage where iscenicid="+iscenicid+" and tripid="+tripid+" and itickettypeid="+itickettypeid+" and startdata<='"+date+"' and enddata>='"+date+"'";
		List list=find(hql);
		if(list!=null&&list.size()>0){
			return (Prdtripvenuemanage) find(hql);
		}else{
			return null;
		}
	}
	
	
	
}
