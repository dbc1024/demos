package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.permitenter.Discountcoupon;
import com.ectrip.ticket.model.permitenter.Discountcoupondetail;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.permitenter.dao.IDiscountcouponDAO;

public class DiscountcouponDAO extends GenericDao implements IDiscountcouponDAO{

	/**
	 * Describe:ɾ���Ż݄�
	 * @auth:shenzhixiong
	 * @see com.ectrip.system.permitenter.dao.idao.IDiscountcouponDAO#deleteDiscountcoupon(com.ectrip.model.permitenter.Discountcoupon,com.ectrip.model.syspar.Syslog)
	 * @param discountcoupon
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	public void deleteDiscountcoupon(Discountcoupon discountcoupon,Syslog syslog) {
		
		Discountcoupon dt = (Discountcoupon) this.get(Discountcoupon.class, discountcoupon.getDcid());
		
		this.delete(dt);
		
		syslog.setStlg("0275");
		syslog.setBrief("�Ż݄���" + dt.getDcid());
		syslog.setNote("ɾ���Ż݄���" + dt.getDcid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
	}

	/**
	 * Describe:��ѯ�Żݾ�
	 * @auth:shenzhixiong
	 * @param iscenicid
	 * @param dcstartdate
	 * @param dcenddate
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * return:PaginationSupport
	 * Date:2014-4-1
	 */
	public PaginationSupport discountcouponList(Long iscenicid,String dcstartdate,String dcenddate,int pageSize, int startIndex, String url) {
		PaginationSupport ps =null;
		StringBuffer hsql = new StringBuffer();																																																																														
		hsql.append("select new map(dc.dcid as dcid,dc.dccode as dccode,dc.dtmakedate as dtmakedate,dc.szdiscountname as szdiscountname,dc.stocknumb as stocknumb,dc.dcsort as dcsort,dc.dcrule as dcrule,dc.iscenicid as iscenicid,dc.money as money,dc.dcmoney as dcmoney,dc.dcstartdate as dcstartdate,dc.dcenddate as dcenddate,dc.dcmodel as dcmodel,dc.dcaid as dcaid,dc.sxmemo as sxmemo) from Discountcoupon dc where 1=1");
		
		//,Esbscenicareatab es where dc.iscenicid = es.iscenicid    ,dc.szdiscountname as szdiscountname
		
		if(iscenicid != null && !"".equals(iscenicid) && iscenicid!=0)
		{
			hsql.append(" and dc.iscenicid = " + iscenicid);
		}
		if(dcstartdate != null &&  !"".equals(dcstartdate))
		{ 
			hsql.append(" and dc.dcstartdate >= '" + dcstartdate+"'");
		}
		if(dcenddate != null &&  !"".equals(dcstartdate))
		{
			hsql.append(" and dc.dcenddate <= '" + dcenddate+"'");
		}
		ps=this.findPage(hsql.toString(), pageSize, startIndex, url);
		List list=ps.getItems();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map=(Map)list.get(i);
				if(map.get("iscenicid")!=null){
					Esbscenicareatab esbscen=(Esbscenicareatab)this.get(Esbscenicareatab.class, Long.parseLong(map.get("iscenicid").toString()));
					if(esbscen!=null){
						map.put("szscenicname", esbscen.getSzscenicname());
					}else{
						map.put("szscenicname", "");
					}
					
				}
			}
		}
		return ps;
	}

	
	/**
	 * Describe:����id��ѯ�Żݾ�
	 * @auth:shenzhixiong
	 * @param dcid
	 * return:discountcoupon
	 * Date:2014-4-2
	 * @throws Exception 
	 */
	public Discountcoupon getDiscountcouponFindByID(Long dcid) throws Exception
	{

		Discountcoupon discountcoupon =(Discountcoupon)this.get(Discountcoupon.class, dcid);
		if(discountcoupon.getIscenicid()!=null&&discountcoupon.getIscenicid()!=0)
		{
			Esbscenicareatab esbscen=(Esbscenicareatab) this.get(Esbscenicareatab.class, discountcoupon.getIscenicid());
			discountcoupon.setSzscenicname(esbscen.getSzscenicname());
		}
		Sysparv5Id sysparid = null;
		Sysparv5 sysparv5=null;
		if(discountcoupon.getDcsort()!=null && !"".equals(discountcoupon.getDcsort())){
			sysparid =new Sysparv5Id();
			sysparid.setPmcd(discountcoupon.getDcsort());
			sysparid.setPmky("YHLB");//�Ż�ȯ����			
			sysparv5 =(Sysparv5) this.get(Sysparv5.class, sysparid);
			if(sysparv5!=null){
				discountcoupon.setTickittype(sysparv5.getPmva());
			}
		}
		
		if(discountcoupon.getDcrule()!=null && !"".equals(discountcoupon.getDcrule())){
			sysparid =new Sysparv5Id();
			sysparid.setPmcd(discountcoupon.getDcrule());
			sysparid.setPmky("YHGZ");//�Ż�ȯ����		
			sysparv5 =(Sysparv5) this.get(Sysparv5.class, sysparid);
			if(sysparv5!=null){
				discountcoupon.setTickitrule(sysparv5.getPmva());
			}
		}
		return discountcoupon;
	}
	
	
	
	/**
	 * Describe:����Ż݄�
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	public Discountcoupon insertDiscountcoupon(Discountcoupon discountcoupon,Syslog syslog) {

		Long maxid = this.getMaxPk("dcid", "Discountcoupon");
		discountcoupon.setDcid(maxid + 1);
		discountcoupon.setDtmakedate(Tools.getDayTimes());
		this.save(discountcoupon);
		
		syslog.setStlg("0274");
		syslog.setBrief("�Ż݄���" + discountcoupon.getDcid());
		syslog.setNote("����Ż݄���" + discountcoupon.getDcid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		return discountcoupon;
		
	}

	/**
	 * Describe:�޸��Ż݄���Ϣ
	 * @auth:shenzhixiong
	 * @param discountcoupon
	 * @param syslog
	 * return void
	 * Date:2014-4-1
	 */
	public void updateDiscountcoupon(Discountcoupon discountcoupon,Syslog syslog) {

		
		Discountcoupon dc = (Discountcoupon) this.get(Discountcoupon.class, discountcoupon.getDcid());
		if(dc.getStocknumb()>discountcoupon.getStocknumb()){
			//ԭ�����������޸ĺ������ 
			String sql = "from Discountcoupondetail dcd  where dcid="+discountcoupon.getDcid()+" and byisuser=0 order by dcd.dcdid desc";
			List list = this.find(sql);
			for (int i = 0; i <  (dc.getStocknumb().longValue()-discountcoupon.getStocknumb().longValue()); i++) {
				System.out.println("i="+i);
				Discountcoupondetail dcd = (Discountcoupondetail) list.get(i);
				this.delete(dcd);
			}
		}
		
		if(dc.getStocknumb()<discountcoupon.getStocknumb()){
			//��ȡ���ı���
			String sql = "from Discountcoupondetail dcd  where dcid="+discountcoupon.getDcid()+"  order by dcd.szcode desc";
			List list = this.find(sql);
			Discountcoupondetail d1=(Discountcoupondetail)list.get(0);
			int max=Integer.parseInt(d1.getSzcode().substring(6));
			//ԭ������С�����޸ĺ������ 
			for (int i = 0; i < discountcoupon.getStocknumb()-dc.getStocknumb(); i++) {
			Discountcoupondetail discountcoupondetail = new Discountcoupondetail();
			
			Long maxId = this.getMaxPk("dcdid", "Discountcoupondetail");
			discountcoupondetail.setDcdid(maxId+1);
			discountcoupondetail.setDcid(discountcoupon.getDcid());
			discountcoupondetail.setSzcode(discountcoupon.getDccode()
					+ lpad(10, new Long(max+i + 1).longValue()));
			discountcoupondetail.setByisuser(0L);
			discountcoupondetail.setDcdreleasedate(Tools.getDayTimes());
			this.save(discountcoupondetail);
			}
		}
		dc.setSzdiscountname(discountcoupon.getSzdiscountname());
		dc.setDtmakedate(discountcoupon.getDccode());
		dc.setStocknumb(discountcoupon.getStocknumb());
		dc.setDcsort(discountcoupon.getDcsort());
		dc.setDcrule(discountcoupon.getDcrule());
		dc.setIscenicid(discountcoupon.getIscenicid());
		dc.setDcmoney(discountcoupon.getDcmoney());
		dc.setDcstartdate(discountcoupon.getDcstartdate());
		dc.setDcenddate(discountcoupon.getDcenddate());
		dc.setDcmodel(discountcoupon.getDcmodel());
		dc.setDcaid(discountcoupon.getDcaid());
		dc.setSxmemo(discountcoupon.getSxmemo());
		dc.setDtmakedate(Tools.getDayTimes());
		this.update(dc);
		
		
		syslog.setStlg("0276");
		syslog.setBrief("�Ż݄���" + discountcoupon.getDcid());
		syslog.setNote("�޸��Ż݄���" + dc.getDcid());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
		
		
	}

	private String lpad(int length, Long number) {
		String f = "%0" + length + "d";
		return String.format(f, number);
	}
	
	public int verifyDisountcoupon(Discountcoupon discountcoupon, boolean b)
	{
		String sql = null;
		if(b)
		{
			 sql = "select count(*) from Discountcoupon dc where dc.dcsort = '"+discountcoupon.getDcsort()+"' and dc.dcrule = '" + discountcoupon.getDcrule() + "' and dc.iscenicid = "+ discountcoupon.getIscenicid() +" and dc.dcstartdate <= '" + discountcoupon.getDcstartdate() + "' and dc.dcenddate >=" +discountcoupon.getDcenddate();
		}
		else
		{
			 sql = "select count(*) from Discountcoupon dc where dc.dcsort = '"+discountcoupon.getDcsort()+"' and dc.dcrule = '" + discountcoupon.getDcrule() + "' and dc.dcstartdate = '" + discountcoupon.getDcstartdate() + "' and dc.dcenddate >=" +discountcoupon.getDcenddate();
		}
		
		
		List list = this.find(sql);
		return Integer.parseInt(list.get(0).toString());
	}

}
