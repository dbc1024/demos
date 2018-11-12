package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.dao.IPriceSaleLimitDao;

@Repository
public class PriceSaleLimitDao extends GenericDao implements IPriceSaleLimitDao {
	/**
	 * 
	 * Describe:根据价格ID查看产品信息
	 * @author:chenxinhao
	 * @param icrowdkindpriceid
	 * @return
	 * @throws Exception
	 * return:Edmcrowdkindpricetab
	 * Date:2013-7-23
	 */
	public Edmcrowdkindpricetab findprice(Long icrowdkindpriceid) throws Exception{
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(p.icrowdkindpriceid as icrowdkindpriceid,b.szbusinessname as strbusiness,c.szcrowdkindname as strcrowdkind,t.sztickettypename as strtickettype) ");
		hsql.append(" from Edmcrowdkindpricetab p,Edmbusinesstab b,Edpcrowdkindtab c,Edmtickettypetab t ");
		hsql.append(" where p.itickettypeid=t.itickettypeid and p.icrowdkindid=c.icrowdkindid and p.ibusinessid=b.ibusinessid and p.icrowdkindpriceid="+icrowdkindpriceid);
		List list = this.find(hsql.toString());
		if(list!=null&&!list.isEmpty()){
			Edmcrowdkindpricetab price = new Edmcrowdkindpricetab();
			BeanUtils.populate(price,(Map) list.get(0));
			return price;
		}
		return null;
	}
	/**
	 * 
	 * Describe:根据价格ID查找销售权限信息
	 * @author:chenxinhao
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2013-7-23
	 */
	public List querySaleLimitList(Long icrowdkindpriceid){
		return this.find(" from Ospsaleslimitstab o where o.icrowdkindpriceid="+icrowdkindpriceid);
	}
	/**
	 * 
	 * Describe:保存员工销售权限
	 * @author:chenxinhao
	 * @param salelimits
	 * @param syslog
	 * return:void
	 * Date:2013-7-23
	 */
	public void insertSaleLimit(Ospsaleslimitstab salelimits,Syslog syslog){
		List list = this.find(" from Ospsaleslimitstab o where o.icrowdkindpriceid="+salelimits.getIcrowdkindpriceid());
		if(list!=null&&!list.isEmpty()){
			Ospsaleslimitstab o = null;
			for(int i=0;i<list.size();i++){
				o = (Ospsaleslimitstab) list.get(i);
				this.delete(o);
			}
		}
		Long[] empids = salelimits.getIempids();
		if(empids!=null&&empids.length>0){
			for(int i=0;i<empids.length;i++){
				Ospsaleslimitstab o = new Ospsaleslimitstab();
				o.setIcrowdkindpriceid(salelimits.getIcrowdkindpriceid());
				o.setIemployeeid(empids[i]);
				long maxsale = this.getMaxPk("isaleslimitsid", "Ospsaleslimitstab");
				o.setIsaleslimitsid(maxsale+1);
				this.save(o);
				
				syslog.setStlg("0073");
				syslog.setBrief("销售权限：" + o.getIsaleslimitsid() );
				syslog.setNote("销售权限增加：" +  o.getIsaleslimitsid() +",价格编号："+o.getIcrowdkindpriceid()+",员工编号："+o.getIemployeeid());
				syslog.setLogdatetime(Tools.getDayTimes());
				long logid = this.getMaxPk("logid", "Syslog");
				syslog.setLogid(logid + 1);
				this.save(syslog);
			}
		}
	}
	
	/**
	 * 
	 * Describe:获取服务商列表
	 * @author:chenxinhao
	 * @param scenics	服务商编号，以,隔开，例如1,2,3
	 * @param scenictype	服务商类别
	 * @return
	 * return:List
	 * Date:2013-7-23
	 */
	public List sceniclist(String scenics, String scenictype) {
		List sceniclist = new ArrayList();
		String hsql = "";
		if (scenics != null && !scenics.equals("")) {
			hsql = "from Esbscenicareatab where iscenicid in (" + scenics
					+ ") and scenictype ='" + scenictype + "'";

		} else {
			hsql = "from Esbscenicareatab where  scenictype ='" + scenictype
					+ "' order by iscenicid";

		}

		sceniclist = this.find(hsql);
		return sceniclist;
	}
	/**
	 * 
	 * Describe:根据服务商获取窗口列表
	 * @author:chenxinhao
	 * @param iscenics
	 * @return
	 * @throws Exception
	 * return:List
	 * Date:2013-7-23
	 */
	public List queryWinList(String iscenics) throws Exception{
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select new map(w.iticketwinid as iticketwinid,w.szticketwinname as szticketwinname,w.szipaddress as szipaddress,t.szstationname as szstationname) ");
		hsql.append(" from Esbticketwintab w,Esbticketstationtab t ");
		hsql.append(" where w.iticketstationid=t.iticketstationid and w.iscenicid in ("+iscenics+") and w.byisuse=1 ");
		List list = this.find(hsql.toString());
		List winList = new ArrayList();
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Esbticketwintab win = new Esbticketwintab();
				BeanUtils.populate(win,(Map) list.get(i));
				winList.add(win);
			}
		}
		return winList;
	}
	/**
	 * 
	 * Describe:根据价格ID查找窗口销售权限信息
	 * @author:chenxinhao
	 * @param icrowdkindpriceid
	 * @return
	 * return:List
	 * Date:2013-7-23
	 */
	public List queryWinLimitList(Long icrowdkindpriceid){
		return this.find(" from Ospticketwinlimitstab o where o.icrowdkindpriceid="+icrowdkindpriceid);
	}
	/**
	 * 
	 * Describe:保存窗口销售权限
	 * @author:chenxinhao
	 * @param winlimits
	 * @param syslog
	 * return:void
	 * Date:2013-7-23
	 */
	public void insertWinLimit(Ospticketwinlimitstab winlimits,Syslog syslog){
		List list = this.find(" from Ospticketwinlimitstab o where o.icrowdkindpriceid="+winlimits.getIcrowdkindpriceid());
		if(list!=null&&!list.isEmpty()){
			Ospticketwinlimitstab o = null;
			for(int i=0;i<list.size();i++){
				o = (Ospticketwinlimitstab) list.get(i);
				this.delete(o);
			}
		}
		Long[] winids = winlimits.getIwinids();
		if(winids!=null&&winids.length>0){
			for(int i=0;i<winids.length;i++){
				Ospticketwinlimitstab o = new Ospticketwinlimitstab();
				o.setIcrowdkindpriceid(winlimits.getIcrowdkindpriceid());
				o.setIticketwinid(winids[i]);
				long maxsale = this.getMaxPk("iticketwinlimitsid", "Ospticketwinlimitstab");
				o.setIticketwinlimitsid(maxsale+1);
				this.save(o);
				
				syslog.setStlg("0070");
				syslog.setBrief("窗口销售权限：" + o.getIticketwinlimitsid() );
				syslog.setNote("窗口销售权限增加：" +  o.getIticketwinlimitsid() +",价格编号："+o.getIcrowdkindpriceid()+",窗口编号："+o.getIticketwinid());
				syslog.setLogdatetime(Tools.getDayTimes());
				Long logid = this.getMaxPk("logid", "Syslog");
				syslog.setLogid(logid + 1);
				this.save(syslog);
			}
		}
	}
}

