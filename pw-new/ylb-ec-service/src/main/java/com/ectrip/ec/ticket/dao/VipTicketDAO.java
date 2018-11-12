package com.ectrip.ec.ticket.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.ticket.dao.idao.IVipticketDAO;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;

public class VipTicketDAO extends GenericDao implements IVipticketDAO{
	
	/**
	 * *
	 * Describe:��ȡ��ƱƱ���˴���Ϣ
	 * @see com.ectrip.ticket.dao.idao.IVipticketDAO#getRafttripInfoList(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @param itickettypeid
	 * @param date
	 * @param ziticketid
	 * @param usid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Apr 25, 2012
	 */
	 public List getRafttripInfoList(String itickettypeid, String date, String ziticketid,String usid) throws Exception {
		 Sysparv5 sys=(Sysparv5) get(Sysparv5.class, new Sysparv5Id("YDJF","02"));
		 List list = getNumberControllData(itickettypeid, date, "02");
		 StringBuffer hql = new StringBuffer();
		 if (list == null || list.size() < 1) {
			 return null;
		 } else {
			 hql
			 .append("select new map(numcontroll.productcontrolid as productcontrolid, tm.productmanageid as productmanageid,trip.tripname as tripname,tm.starttime as starttime,tm.endtime as endtime,tm.startdata as startdata,tm.enddata as enddata,wharf.ivenueareaname as ivenueareaname,trip.tripid as tripid,");
			 if (list == null || list.size() < 1) {
				 hql.append("(numcontroll.salablenumber-numcontroll.soldnumber) as salablenumber,");
			 } else {
				 Productcontrol pc = new Productcontrol();
				 BeanUtils.populate(pc, (Map) list.get(0));
				 int raftnumber = pc.getSalablenumber().intValue() - pc.getSoldnumber().intValue();
				 hql
				 .append("(select (case when (p.salablenumber-p.soldnumber)>"
						 + raftnumber
						 + " then "
						 + raftnumber
						 + "  else (p.salablenumber-p.soldnumber) end) from Productcontrol p where numcontroll.productcontrolid = p.productcontrolid) as salablenumber,");
			 }
			 hql
			 .append(" ticket.iscenicid as iscenicid) from Prdtripvenuemanage tm,Edmtickettypetab ticket,Trip trip,Venuearea wharf,Productcontrol numcontroll"
					 + " where tm.itickettypeid = ticket.itickettypeid and numcontroll.bystate=1  and numcontroll.byisuse=1 and trip.iscenicid = tm.iscenicid and tm.tripid = trip.tripid and ticket.itickettypeid ="
					 + ziticketid
					 + " and ticket.byisuse=1 and   trip.byisuse=1  and tm.startdata <= '"
					 + date
					 + "' and tm.enddata >= '"
					 + date
					 + "' and tm.iscenicid=wharf.iscenicid and tm.ivenueareaid=wharf.ivenueareaid and tm.iscenicid=numcontroll.iscenicid and ticket.itickettypeid=numcontroll.itickettypeid and trip.tripid=numcontroll.tripid and tm.ivenueareaid=numcontroll.ivenueareaid and tm.ivenueid=numcontroll.ivenueid and numcontroll.stdata='"
					 + date + "'");
			 if (Tools.getDayNumb(date, Tools.getDays()) == 1) {
				 hql.append(" and tm.starttime>='" + Tools.getNowTime().substring(0, 5) + "'");
			 }
			 hql
			 .append(" group by numcontroll.salablenumber,numcontroll.productcontrolid,tm.productmanageid,trip.tripname,tm.starttime,tm.endtime,tm.startdata,tm.enddata,wharf.ivenueareaname,ticket.iscenicid,ticket.itickettypeid,trip.tripid order by tm.starttime");
	
		 }
		 return find(hql.toString());
	}
	 
	 
	 /**
	     * * Describe:����ʱ�䡢��Ʒ������ģʽ��ȡ��Ʒ������������
	     * 
	     * @see com.ectrip.ticket.dao.idao.ITicketDAO#getNumberControllData(java.lang.String, java.lang.String, java.lang.String)
	     * @param iticketid
	     * @param date
	     * @param controlltype
	     *            01���������� 02:�տ��� 03:�˴ο��� 04:�˴�������� 05����λ����
	     * @return
	     * @author yangguang Date:2011-10-29
	     */
	public List getNumberControllData(String iticketid, String date, String controlltype) {
		String hql = "select new map(pc.productcontrolid as productcontrolid,pc.iscenicid as iscenicid,pc.itickettypeid as itickettypeid,pc.stdata as stdata,pc.salablenumber as salablenumber,pc.soldnumber as soldnumber,pc.reservednumber as reservednumber,pc.reservedsalenumber as reservedsalenumber,pc.controltype as controltype) from Productcontrol pc where itickettypeid="
			+ iticketid + " and stdata='" + date + "' and byisuse=1 and bystate=1 and pc.controltype='" + controlltype + "'";
		if (controlltype.equals("02")) {
		    hql += " and tripid is null";
		}
		return find(hql);
	}
	
	 /**
     * 
     * Describe:���ݵ�ǰ�û�����ҵ�����ͻ�ȡ�ֿ��۵�Ʊ������,������Ʊ�����ƽ��з���
     * 
     * @auth:yangguang
     * @param ibusinessid
     * @return return:PaginationSupport Date:2011-10-9
     */
    public List getTicketList(String ibusinessid) {
    	String hql = "select new map(ticket.sztickettypename as ticketname,ticket.itickettypeid as ticketid,ticket.iscenicid as iscenicid,ticket.issale as issale,ticket.isequence as isequence,ticket.bycategorytype as bycategorytype,ticket.validityday as validityday,price.ibusinessid as ibusinessid,price.isequence as isequence)  from Edmcrowdkindpricetab price,Esbscenicareatab provider,Edmtickettypetab ticket where  price.ibusinessid="
			+ ibusinessid
			+ "  and  price.itickettypeid=ticket.itickettypeid and price.byisuse=1 and provider.byisuse=1 and ticket.byisuse=1 and price.isnet=1 and  ticket.iscenicid=provider.iscenicid and  provider.scenictype='01' and ticket.bycategorytype in (select sys.id.pmcd from Sysparv5 sys where sys.id.pmky='PRTP' and systp='1') and price.startdata<='"
			+ Tools.getDays()
			+ "' and price.enddata>='"
			+ Tools.getDays()
			+ "' group by ticket.sztickettypename,ticket.itickettypeid,ticket.iscenicid,ticket.issale,ticket.bycategorytype, ticket.isequence,ticket.validityday,price.ibusinessid,price.isequence order by price.isequence desc";
		return find(hql);
    }
    
    /**
     * (�� Javadoc)
     * <p>
     * Title: getTOrderListList
     * </p>
     * <p>
     * Description: ��ȡ���϶��������̳�Ʊ��ϸ
     * </p>
     * 
     * @param orid
     * @param iscenicid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see com.ectrip.order.dao.idao.ITOrderListDAO#getTOrderListList(java.lang.String, java.lang.String)
     */
    public List getTOrderListList(String orid, String iscenicid) throws IllegalAccessException, InvocationTargetException {
	List list = this
		.find("select new map(t.id.orid as orid,t.id.orderlistid as orderlistid,t.itickettypeid as itickettypeid,t.icrowdkindid as icrowdkindid,t.id.iscenicid as iscenicid,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.pric as pric,t.numb as numb,t.amnt as amnt,e.sztickettypename as sztickettypename,kind.szcrowdkindname as szcrowdkindname,(select substr(z.dtstartdate,0,10) from TZorderlist z where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharfdate,(select substr(z.dtstartdate,12,5) from TZorderlist z where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid) as wharftime,(select trip.tripname from TZorderlist z,Trip trip where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid and z.tripid=trip.tripid) as tripname,(select v1.ivenueareaname from TZorderlist z,Venuearea v1,Venue v2 where z.id.orderlistid = t.id.orderlistid and z.tripid != 0 and z.id.orid = t.id.orid and z.id.iscenicid = t.id.iscenicid and z.ivenueid=v1.ivenueid and z.ivenueareaid=v1.ivenueareaid) as wharfname,torder.scenictype as scenictype,cord.isequence as isequence) from TOrderlist t,TOrder torder,Edmtickettypetab e,Edpcrowdkindtab kind,Edmcrowdkindpricetab cord where kind.icrowdkindid=t.icrowdkindid and cord.icrowdkindpriceid=t.icrowdkindpriceid and torder.id.iscenicid=t.id.iscenicid and t.id.orid=torder.id.orid  and t.id.orid = '"
			+ orid + "' and t.id.iscenicid = '" + iscenicid + "' and t.itickettypeid=e.itickettypeid order by cord.isequence desc,13,14,15,16 ");

	return list;
    }
}

