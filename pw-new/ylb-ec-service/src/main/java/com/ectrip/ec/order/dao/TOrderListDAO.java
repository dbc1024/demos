package com.ectrip.ec.order.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.order.dao.idao.ITOrderListDAO;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;

/**
 * 
 * @ClassName: TOrderListDAO
 * @Description:网上订票销售订单明细
 * @author Dicky
 * @date Oct 11, 2011 2:50:18 PM
 * 
 */
@Repository
public class TOrderListDAO extends GenericDao implements ITOrderListDAO {
	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: saveTOrderList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param torderlist
	 * @see com.ectrip.order.dao.idao.ITOrderListDAO#saveTOrderList(com.ectrip.model.order.TOrderlist)
	 */
	public void saveTOrderList(TOrderlist torderlist) {
		this.save(torderlist);
	}

	/**
	 * 
	 * @Title: updateTOrderList
	 * @Description: 更新TOrderlist
	 * @param @param torderlist 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void updateTOrderList(TOrderlist torderlist) {
		this.update(torderlist);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getTOrderListList
	 * </p>
	 * <p>
	 * Description: 获取网上订单服务商出票明细
	 * </p>
	 * 
	 * @param orid
	 * @param iscenicid
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @see com.ectrip.order.dao.idao.ITOrderListDAO#getTOrderListList(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	public List getTOrderListList(String orid, String iscenicid)
			throws IllegalAccessException, InvocationTargetException {
		/*
		 * List list = this .find(
		 * "select new map(t.ioffersschemeid as ioffersschemeid,t.isi as isi,t.ish as ish,t.id.orid as orid,t.id.orderlistid as orderlistid,t.itickettypeid as itickettypeid,t.icrowdkindid as icrowdkindid,t.id.iscenicid as iscenicid,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.pric as pric,t.numb as numb,t.amnt as amnt,t.yhamnt as yhamnt,e.sztickettypename as sztickettypename,kind.szcrowdkindname as szcrowdkindname,(select substr(z.dtstartdate,0,10) from TZorderlist z where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharfdate,(select substr(z.dtstartdate,12,5) from TZorderlist z where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharftime,(select trip.tripname from TZorderlist z,Trip trip where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid and z.tripid=trip.tripid) as tripname,(select v1.venueidname from TZorderlist z,Venue v1 where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid and z.ivenueid=v1.ivenueid and   v1.ivenueid=z.ivenueid) as wharfname,torder.scenictype as scenictype,t.yhnumb as yhnumb,t.yhamnt as yhamnt) from TOrderlist t,TOrder torder,Edmtickettypetab e,Edpcrowdkindtab kind where kind.icrowdkindid=t.icrowdkindid and torder.id.iscenicid=t.id.iscenicid and t.id.orid=torder.id.orid  and t.id.orid = '"
		 * + orid + "' and t.id.iscenicid = '" + iscenicid +
		 * "' and t.itickettypeid=e.itickettypeid order by e.sztickettypename,t.dtstartdate,13,14,15,16"
		 * );
		 */
		/*List list = this.find("select new map(e.bycategorytype as bycategorytype,t.ioffersschemeid as ioffersschemeid,t.starttime as starttime,t.endtime as endtime,"
		+ " t.isi as isi,t.ish as ish,t.id.orid as orid,t.id.orderlistid as orderlistid,t.icrowdkindpriceid as icrowdkindpriceid,t.itickettypeid as itickettypeid,"
		+ "t.icrowdkindid as icrowdkindid,t.id.iscenicid as iscenicid,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.pric as pric,t.numb as numb,t.amnt as amnt,t.yhamnt as yhamnt,"
		+ "e.sztickettypename as sztickettypename,e.bycategorytype as bycaegorytype,kind.szcrowdkindname as szcrowdkindname,"
		+ "(select substr(z.dtstartdate,0,10) from TZorderlist z where z.id.orderlistid = t.id.orderlistid "
		+ "and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharfdate,"
		+ "(select substr(z.dtstartdate,12,5) from TZorderlist z "
		+ "where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid "
		+ "and z.id.iscenicid = t.id.iscenicid) as wharftime,"
		+ "(select trip.tripname from TZorderlist z,"
		+ "Trip trip where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid "
		+ "and z.id.iscenicid = t.id.iscenicid and z.tripid=trip.tripid) as tripname,"
		+ "(select v1.venueidname from TZorderlist z,Venue v1 "
		+ "where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid "
		+ "and z.id.iscenicid = t.id.iscenicid  and   v1.ivenueid=z.ivenueid) as wharfname,"
		+ "(select vp.szprogramname from TZorderlist z,Venueprogram vp where z.id.orderlistid = t.id.orderlistid "
		+ "and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid "
		+ "and  vp.iprogramid=z.ivenueseatsid ) as filmname,torder.scenictype as scenictype,"
		+ "t.yhnumb as yhnumb,t.yhamnt as yhamnt,t.timeid as timeid) from TOrderlist t,"
		+ "TOrder torder,Edmtickettypetab e,Edpcrowdkindtab kind "
		+ "where kind.icrowdkindid=t.icrowdkindid and torder.id.iscenicid=t.id.iscenicid "
		+ "and t.id.orid=torder.id.orid  and t.id.orid = '"
		+ orid
		+ "' and t.id.iscenicid = '"
		+ iscenicid
		+ "' and t.itickettypeid=e.itickettypeid order by e.sztickettypename,t.dtstartdate,13,14,15,16");*/
		String sql = "select new map(t.ioffersschemeid as ioffersschemeid,t.starttime as starttime,t.endtime as endtime," 
				+ " t.isi as isi,t.ish as ish,t.id.orid as orid,t.id.orderlistid as orderlistid,t.icrowdkindpriceid as icrowdkindpriceid,t.itickettypeid as itickettypeid," 
				+ "t.icrowdkindid as icrowdkindid,t.id.iscenicid as iscenicid,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.pric as pric,t.numb as numb,t.amnt as amnt,t.yhamnt as yhamnt,"
				+ "(select substr(z.dtstartdate,0,10) from TZorderlist z where z.id.orderlistid = t.id.orderlistid " 
				+ " and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharfdate," 
				+ " (select substr(z.dtstartdate,12,5) from TZorderlist z " 
				+ " where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid " 
				+ " and z.id.iscenicid = t.id.iscenicid) as wharftime,torder.scenictype as scenictype,"  
				+ "t.yhnumb as yhnumb,t.yhamnt as yhamnt,t.timeid as timeid) "
				+ "from TOrderlist t,TOrder torder "
				+ "where t.id.orid = torder.id.orid  and t.id.orid = '"+orid+"' and t.id.iscenicid = "+ iscenicid;
		List list = this.find(sql);
		return list;
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getTOrderLis
	 * </p>
	 * <p>
	 * Description:获取网上订单服务商出票明细
	 * </p>
	 * 
	 * @param torderlistid
	 * @param iscenicid
	 * @param orid
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @see com.ectrip.order.dao.idao.ITOrderListDAO#getTOrderList(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public TOrderlist getTOrderList(String torderlistid, String iscenicid,
			String orid) {
		TOrderlist orderlist = new TOrderlist();
		List list = this
				.find("select new map(tol.id.orid as orid,tol.id.iscenicid as iscenicid,tol.id.orderlistid as orderlistid,e.sztickettypename as sztickettypename,tol.itickettypeid as itickettypeid,tol.icrowdkindpriceid as icrowdkindpriceid,tol.icrowdkindid as icrowdkindid,tol.dtstartdate as dtstartdate,tol.dtenddate as dtenddate,tol.pric as pric,tol.numb as numb,tol.yhnumb as yhnumb,tol.amnt as amnt,tol.yhamnt as yhamnt,tol.ioffersschemeid as ioffersschemeid)  from TOrderlist tol,Edmtickettypetab e where tol.id.orid='"
						+ orid
						+ "' and tol.id.iscenicid="
						+ iscenicid
						+ " and tol.id.orderlistid="
						+ torderlistid
						+ " and e.itickettypeid=tol.itickettypeid");
		try {
			BeanUtils.populate(orderlist, (Map) list.get(0));
		} catch (Exception se) {
			se.printStackTrace();
		}
		return orderlist;
	}

	/**
	 * 
	 * Describe:根据订单编号 服务商编号获取订单列表
	 * 
	 * @auth:yangguang
	 * @param orid
	 * @param iscenicid
	 * @return return:List<TOrderlist> Date:2012-3-31
	 */
	public List<TOrderlist> getTOrderlists(String orid, Long iscenicid) {
		String hql = "from TOrderlist where id.orid='" + orid
				+ "' and id.iscenicid=" + iscenicid + "";
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	public List<TOrderlist> getTOrderlists(String orid) {
		String hql = "from TOrderlist where id.orid='" + orid + "'";
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}

	public TOrderlist getTorderlistByProductInfo(Long itickettypeid,
			String orid, Long iscenicid, Long icrowdkindid, String dtstartdate,
			String dtenddate, String tourdate, Long productcontrolid) {
		String hql = " from TOrderlist where itickettypeid=" + itickettypeid
				+ " and id.orid='" + orid + "' and id.iscenicid=" + iscenicid
				+ " and icrowdkindid=" + icrowdkindid + " and dtstartdate='"
				+ dtstartdate + "' and dtenddate='" + dtenddate + "'";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			for (int x = 0; x < list.size(); x++) {
				TOrderlist orderlist = (TOrderlist) list.get(x);
				if (tourdate != null && !tourdate.equals("")
						&& productcontrolid != null
						&& !productcontrolid.equals("")) {
					Productcontrol control = (Productcontrol) get(
							Productcontrol.class, productcontrolid);
					hql = "from TZorderlist where id.orderlistid="
							+ orderlist.getId().getOrderlistid()
							+ " and  itickettypeid=" + itickettypeid
							+ " and id.orid='" + orid + "' and id.iscenicid="
							+ iscenicid + " and icrowdkindid=" + icrowdkindid
							+ " and tripid=" + control.getTripid()
							+ " and ivenueid=" + control.getIvenueid()
							+ " and ivenueareaid=" + control.getIvenueareaid()
							+ " and iztickettypeid="
							+ control.getItickettypeid()
							+ " and substr(dtstartdate,0,10)='" + tourdate
							+ "'";
					List list1 = find(hql);
					if (list1 != null && list1.size() > 0) {
						return (TOrderlist) list.get(x);
					}
				} else {
					return (TOrderlist) list.get(0);
				}
			}
		} else {
			return null;
		}
		return null;
	}

	public boolean delZeroTicketInOrder(String orid) {
		String hql = "from TOrderlist where id.orid='" + orid + "' and numb=0";
		List list = find(hql);
		List list1 = null;
		String hql1 = "";
		if (list != null && list.size() > 0) {
			TOrderlist torderlist = null;
			for (int i = 0; i < list.size(); i++) {
				torderlist = (TOrderlist) list.get(i);
				hql = "from TZorderlist where id.orid='" + orid
						+ "' and id.orderlistid="
						+ torderlist.getId().getOrderlistid()
						+ " and id.iscenicid="
						+ torderlist.getId().getIscenicid() + "";
				list1 = find(hql1);
				if (list1 != null && list.size() > 0) {
					for (int j = 0; j < list1.size(); j++) {

					}
				}
			}
		}

		return false;
	}

	/**
	 * * Describe:获取订单中票的最小有效天数
	 * 
	 * @see com.ectrip.order.dao.idao.ITOrderListDAO#getMaxCanEditDate(java.lang.String)
	 * @param orid
	 * @return
	 * @author yangguang Date:2012-4-7
	 */
	public Integer getMaxCanEditDate(String orid) {
		String hql = "select max(t.validityday) from TOrderlist order,Edmtickettypetab t where order.itickettypeid=t.itickettypeid";
		List list = find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			return Integer.parseInt(list.get(0).toString());
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * Describe:验证输入日期是否正确
	 * 
	 * @auth:yangguang
	 * @param orid
	 * @param stdt
	 * @param enddate
	 * @return return:boolean Date:2012-4-7
	 */
	public boolean validationEditDate(String orid, String stdt, String enddate,
			String iscenicid) {
		String hql = "from TZorderlist z,Edmtickettypetab t where z.iztickettypeid=t.itickettypeid and t.bycategorytype='0003' and (substr(z.dtstartdate,0,10)<'"
				+ stdt
				+ "' or substr(z.dtstartdate,0,10)>'"
				+ enddate
				+ "') and z.id.iscenicid="
				+ iscenicid
				+ " and z.id.orid='"
				+ orid + "'";
		List list = find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validationEditDatePrice(String orid, String stdt,
			String iscenicid, String ibussiness) {
		MOrder morder = (MOrder) this.get(MOrder.class, orid);
		
		String hql = "from TOrderlist where id.orid='" + orid
				+ "' and id.iscenicid=" + iscenicid + "";
		List list = find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			for (int i = 0; i < list.size(); i++) {
				TOrderlist torderlist = (TOrderlist) list.get(i);
				
				String jgfz=searchJgfz(morder.getUsid(), Long.parseLong(iscenicid));
				
				Edmcrowdkindpricetab price = getTicketPrice(torderlist
						.getItickettypeid().toString(), stdt, torderlist
						.getIcrowdkindid().toString(), ibussiness,jgfz);
				if (price == null) {
					return false;
				} else {
					if (torderlist.getIcrowdkindpriceid().intValue() != price
							.getIcrowdkindpriceid().intValue()) {
						return false;
					}
				}

			}
		} else {
			return false;
		}
		return true;
	}

	public boolean validationEditScheme(String orid, String stdt,
			String iscenicid, String ibussiness) {
		String hql = "from TOrderlist where id.orid='" + orid
				+ "' and id.iscenicid=" + iscenicid + "";
		List list = find(hql);
		boolean b=false;
		Long numzs=0L;
		
		if (list != null && list.size() > 0 && list.get(0) != null) {
			for (int i = 0; i < list.size(); i++) {
				TOrderlist torderlist = (TOrderlist) list.get(i);
				
				numzs=numzs+torderlist.getNumb();
				
				if (torderlist.getIoffersschemeid() != null
						&& torderlist.getIoffersschemeid() > 0) {
					if (torderlist.getYhnumb() > 0) {
						
						b=true;
						
						Edpofferschemetab e = (Edpofferschemetab) this.get(
								Edpofferschemetab.class,
								torderlist.getIoffersschemeid());
						if (Tools.getDayNumbCha(e.getStartdata(),stdt) >= 0
								&& Tools.getDayNumbCha(
										e.getEnddata(), stdt) <= 0) {
						} else {
							return false;
						}
					} else {
						String sql = "from Edpofferschemetab where  icrowdkindid="
								+ torderlist.getIcrowdkindid()
								+ "  and  iscenicid="
								+ iscenicid
								+ " and ibusinessid="
								+ ibussiness
								+ " and itickettypeid="
								+ torderlist.getItickettypeid()
								+ " and startdata<='"
								+ stdt
								+ "' and enddata>='" + stdt + "' and byisuse=1 and ioffertype=0 ";
						List slist = find(sql);
						if (slist != null & slist.size() > 0) {
							Edpofferschemetab e = (Edpofferschemetab) slist
									.get(0);
							if (e.getIoffersschemeid().intValue() != torderlist
									.getIoffersschemeid().intValue()) {
								return false;
							}
						}
					}
				} else {
					String sql = "from Edpofferschemetab where  icrowdkindid="
							+ torderlist.getIcrowdkindid()
							+ "  and  iscenicid=" + iscenicid
							+ " and ibusinessid=" + ibussiness
							+ " and itickettypeid="
							+ torderlist.getItickettypeid()
							+ " and startdata<='" + stdt + "' and enddata>='"
							+ stdt + "' and byisuse=1 and ioffertype=0 ";
					List slist = find(sql);
					if (slist != null & slist.size() > 0) {
						return false;
					}
				}
			}
		}
		
		if(!b){
			String sql = "from Edpofferschemetab where iscenicid=" + iscenicid
					+ " and startdata<='" + stdt + "' and enddata>='"
					+ stdt + "' and byisuse=1 and ioffertype=1 ";
			List slist = find(sql);
			if (slist != null & slist.size() > 0) {
				Edpofferschemetab e = (Edpofferschemetab) slist.get(0);
				
				if(numzs>=e.getImultiples()){
					return false;
				}
			}
		}
		
		
		return true;
	}

	private Edmcrowdkindpricetab getTicketPrice(String itickettypeid,
			String tourDate, String icrowdkindid, String ibusinessid,String jgfz)
			throws RuntimeException {
		StringBuffer hql = new StringBuffer();
		hql.append("from Edmcrowdkindpricetab price where price.itickettypeid="
				+ itickettypeid + " and price.startdata<='" + tourDate
				+ "' and price.enddata>='" + tourDate
				+ "' and price.icrowdkindid=" + icrowdkindid
				+ " and price.ibusinessid=" + ibusinessid+" and price.note1='"+jgfz+"'");
		List list = find(hql.toString());
		if (list != null && list.size() > 0) {
			return (Edmcrowdkindpricetab) list.get(0);
		} else {
			return null;
		}
	}

	public List getTZorderlists(String orid, TOrderlistId id) {
		String hql = "from TZorderlist z,Edmtickettypetab t where z.iztickettypeid=t.itickettypeid and t.bycategorytype!='0003' and  z.id.orderlistid="
				+ id.getOrderlistid()
				+ " and z.id.orid='"
				+ id.getOrid()
				+ "' and z.id.iscenicid=" + id.getIscenicid() + "";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	   /**
	    * *
	    * Describe:获取某用户的价格分组  服务商id可为空
	    * @see com.ectrip.ticket.service.iservice.ITicketService#searchJgfz(java.lang.String, java.lang.Long)
	    * @param usid
	    * @param iscenicid
	    * @return
	    * @author lijingrui
	    * Date:2014-4-16
	    */
	    public String searchJgfz(String usid,Long iscenicid){
	    	StringBuffer result=new StringBuffer();
	    	StringBuffer hsql=new StringBuffer("select gz.usid as usid,gz.iscenicid as iscenicid,gz.pmcd as pmcd from Jgfz gz,(select connect_by_root usid as susid,connect_by_root corpname as scorpname,connect_by_root bname as bname,connect_by_root ibusinessid as ibusinessid,usid from custom c  START WITH c.lgtp = '02' and ttlb = '01' and ustp='01' connect by prior c.usid = c.susid) c where c.susid=gz.usid and gz.byisuse=1 and c.usid='"+usid+"' ");
	    	if(iscenicid!=null&&!iscenicid.equals("")){
	    		hsql.append(" and gz.iscenicid="+iscenicid);
	    	}
	    	List<Map> lst=new ArrayList<Map>();
			try {
				lst = this.findBySqlToMap(hsql.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	if(lst!=null&&lst.size()>0){
	    		for(int i=0;i<lst.size();i++){
	    			Map map=(Map)lst.get(i);
	    			if(map.get("PMCD")!=null){
	    				if(i==0){
	    					result.append(map.get("PMCD").toString());
	    				}else{
	    					result.append(","+map.get("PMCD").toString());
	    				}
	    				
	    			}
	    		}
	    		return result.toString();
	    	}else{
	    		result.append("0000");
	    		return result.toString();
	    	}
	    	
	    }

	public List getTOrderList(String orid, String ddzt) {
		String hql = "from TOrder where id.orid = '"+orid+"' and  ddzt = '"+ddzt+"'";
		List find = this.find(hql);
		return find;
	}

	public List<TOrderlist> getTOrderlists(String orid, String iscenicid) {
		// TODO Auto-generated method stub
		String hql = "from TOrderlist where id.orid = '"+orid+"' and  id.iscenicid = "+iscenicid;
		List<TOrderlist> find = this.find(hql);
		return find;
	}

	public List findSendHotelMsg(String orid, Long iscenicid) {
		String hql = "select new map(tdlist.numb as numb,hp.breakfasttype as breakfasttype) from TOrderlist tdlist,Hotelproduct hp where tdlist.itickettypeid=hp.itickettypeid and tdlist.id.orid='"+orid+"' and tdlist.id.iscenicid="+iscenicid;
		return this.find(hql);
	}
	
	public List<Map<String, Object>> getTOrderInfos(String orid, String iscenicids){
		String sql = "select orderlistid,CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.itickettypeid,t.icrowdkindid," + 
				" t.dtstartdate,t.dtenddate,t.icrowdkindpriceid,t.pric,t.numb,t.yhnumb,t.amnt,t.yhamnt" + 
				" from t_orderlist t" + 
				" where  t.orid='"+orid+"'" + 
				" and t.iscenicid in ("+iscenicids+")";
		try {
			List list = this.findBySqlToMap(sql);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Map<String, Object>> getZOrderInfos(String orid, String iscenicids, Long orderlistid){
		String sql = "select t.zorderlistid,t.orderlistid,CASt(t.orid as varchar(17)) as orid,t.iscenicid,t.itickettypeid,t.iztickettypeid,t.dtstartdate," + 
				"t.dtenddate,t.zpric,t.znumb,t.zyhnumb,t.zyhamnt,t.zamnt,t.tripid,t.ivenueid,t.ivenueareaid,t.ivenueseatsid, t.ise " + 
				"from t_zorderlist t where  t.orid='"+orid+"' and t.iscenicid in ("+iscenicids+") and t.orderlistid="+orderlistid;
		try {
			List list = this.findBySqlToMap(sql);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> getTOrderMapList(String orId,String iscennicId) throws Exception {
		String sql = "select new map(t.id.orderlistid as orderlistid,t.id.orid as orid,t.id.iscenicid as iscenicid,t.itickettypeid as itickettypeid,t.icrowdkindid as icrowdkindid,"
        		+ "t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.icrowdkindpriceid as icrowdkindpriceid,t.pric as pric,t.numb as numb,t.yhnumb as yhnumb,t.amnt as amnt,t.yhamnt as yhamnt)"
				+ "from TOrderlist t where t.id.orid = '"+orId+"' and t.id.iscenicid = "+iscennicId;
		return this.find(sql);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getTZOrderMapList(String orId, String iscenicid, String orderlistid) throws Exception {
		// TODO Auto-generated method stub
		String sql1 = " select t.zorderlistid,t.orderlistid,CASt(t.orid as varchar(17)) as orid,"
	             + "t.iscenicid,t.itickettypeid,t.iztickettypeid,t.dtstartdate,t.dtenddate,t.zpric,"
	             + "t.znumb,t.zyhnumb,t.zyhamnt,t.zamnt,t.tripid,t.ivenueid,t.ivenueareaid,t.ivenueseatsid,t.ise"
	             + " from t_zorderlist t where  t.orid='"+ orId + "' and iscenicid = " + iscenicid
	             + " and t.orderlistid = "+ orderlistid;
		return this.findBySqlToMap(sql1, new Object[] {});
	}

	@Override
	public List<Map> getTZOrderMapListByOrIdAndIscenicid(String orId, String iscenicid) throws Exception {
        String sql = " select CASt(orid as varchar(17)) as orid,zorderlistid,orderlistid,iscenicid,icrowdkindpriceid,icrowdkindid,itickettypeid,iztickettypeid,dtstartdate,dtenddate,tripid,ivenueid,ivenueareaid,ivenueseatsid,zpric,znumb,zyhnumb,zyhamnt,zamnt,isa,isb,isc,isd,ise,isf,isg,ish,isi,isj,notea,notej,notei,noteh,noteg,notef,notee,noted,notec,noteb,jsprice from T_zorderlist t where  orid=?  and iscenicid in (select iscenicid from Esbscenicareatab where (( isjd = 0 and  iparentid =? ) or iscenicid = ?)) and znumb>0 order by t.orderlistid,zorderlistid";
		return this.findBySqlToMap(sql, orId, iscenicid, iscenicid);
	}

	@Override
	public List<TOrderlist> findTOrderList(String orid, Long iscenicid) {
		String sql = "from TOrderlist t where t.id.orid = '"+orid+"' and t.id.iscenicid = "+iscenicid;
		return this.find(sql);
	}
}
