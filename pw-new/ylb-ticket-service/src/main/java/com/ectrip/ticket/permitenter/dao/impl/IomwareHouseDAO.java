package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.permitenter.Iomwarehouse;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.permitenter.dao.IIomwareHouseDAO;

public class IomwareHouseDAO extends GenericDao implements IIomwareHouseDAO{
	
	/**
	 * *
	 * Describe:��ʾ�����еĲֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#getAllListwareHouse(java.lang.String, int, int, java.lang.String)
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public PaginationSupport getAllListwareHouse(String scenics,int pageSize,int startIndex, String url){
		PaginationSupport ps =null;	
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(wh.iwarehouseid as iwarehouseid,wh.iscenicid as iscenicid,wh.szwarehousecode as szwarehousecode,wh.szwarehousename as szwarehousename,wh.szcontact as szcontact,wh.szaddress as szaddress,wh.byisuse as byisuse,wh.bisdefault as bisdefault,wh.szmemo as szmemo) from Iomwarehouse wh where 1=1 ");
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" and (wh.iscenicid in ("+scenics+") or wh.iscenicid=0 )");
		}
		sql.append(" order by wh.iscenicid ");
		ps=this.findPage(sql.toString(), pageSize, startIndex, url);
		List list = ps.getItems();
		if(list!=null && list.size()>=1){
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if( map.get("iscenicid")!=null){//�����̱��
					String iscenicid = map.get("iscenicid").toString();
					Esbscenicareatab esb=(Esbscenicareatab) this.get(Esbscenicareatab.class, Long.parseLong(iscenicid));
					if(esb!=null){
						map.put("szscenicname", esb.getSzscenicname());
					}else{
						map.put("szscenicname", null);
					}
					
				}
			}
		}
		return ps;
	}
	
	/**
	 * *
	 * Describe:��Ӳֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#insertWareHouse(com.ectrip.model.permitenter.Iomwarehouse, com.ectrip.model.syspar.Syslog)
	 * @param house
	 * @param syslog
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public void insertWareHouse(Iomwarehouse house,Syslog syslog){
		Long maxid=this.getMaxPk("iwarehouseid", "Iomwarehouse");
		house.setIwarehouseid(maxid+1);
		if(house.getIscenicid()==0){
			house.setBisdefault(new Long(1));
		}else{
			house.setBisdefault(new Long(0));
		}
		this.save(house);
		
		syslog.setStlg("0206");
		syslog.setBrief("�ֿ���Ϣ��" + house.getIwarehouseid()+"����"+house.getSzwarehousename());
		syslog.setNote("��Ӳֿ���Ϣ��" + house.getIwarehouseid()+"����"+house.getSzwarehousename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:�޸Ĳֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#updateWareHouse(com.ectrip.model.permitenter.Iomwarehouse, com.ectrip.model.syspar.Syslog)
	 * @param house
	 * @param syslog
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public void updateWareHouse(Iomwarehouse house,Syslog syslog){
		Iomwarehouse ware=(Iomwarehouse) this.get(Iomwarehouse.class, house.getIwarehouseid());
		ware.setIscenicid(house.getIscenicid());
		ware.setSzwarehousecode(house.getSzwarehousecode());
		ware.setSzwarehousename(house.getSzwarehousename());
		ware.setSzcontact(house.getSzcontact());
		ware.setSzaddress(house.getSzaddress());
		ware.setByisuse(house.getByisuse());
		ware.setSzmemo(house.getSzmemo());
		if(house.getIscenicid().equals("0")){
			ware.setBisdefault(new Long(1));
		}else{
			ware.setBisdefault(new Long(0));
		}
		this.update(ware);
		
		
		syslog.setStlg("0207");
		syslog.setBrief("�ֿ���Ϣ��" + house.getIwarehouseid()+"����"+house.getSzwarehousename());
		syslog.setNote("�޸Ĳֿ���Ϣ��" + house.getIwarehouseid()+"����"+house.getSzwarehousename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:ɾ���ֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#deleteWareHouse(com.ectrip.model.permitenter.Iomwarehouse, com.ectrip.model.syspar.Syslog)
	 * @param house
	 * @param syslog
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public void deleteWareHouse(Iomwarehouse house,Syslog syslog){
		Iomwarehouse ware=(Iomwarehouse) this.get(Iomwarehouse.class, house.getIwarehouseid());
		this.delete(ware);
		
		syslog.setStlg("0208");
		syslog.setBrief("�ֿ���Ϣ��" + house.getIwarehouseid()+"����"+house.getSzwarehousename());
		syslog.setNote("ɾ���ֿ���Ϣ��" + house.getIwarehouseid()+"����"+house.getSzwarehousename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * *
	 * Describe:�鿴�ֿ���Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.IIomwareHouseService#viewWarehouse(java.lang.Long)
	 * @param iwarehouseid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:May 31, 2012
	 */
	public Iomwarehouse viewWarehouse(Long iwarehouseid)  throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(wh.iwarehouseid as iwarehouseid,wh.iscenicid as iscenicid,wh.szwarehousecode as szwarehousecode,wh.szwarehousename as szwarehousename,wh.szcontact as szcontact,wh.szaddress as szaddress,wh.byisuse as byisuse,wh.bisdefault as bisdefault,wh.szmemo as szmemo) from Iomwarehouse wh where wh.iwarehouseid="+iwarehouseid);
		List list = super.find(sql.toString());
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Iomwarehouse ts=new Iomwarehouse();
			BeanUtils.populate(ts, (Map) list.get(0));
			Esbscenicareatab esb=(Esbscenicareatab) this.get(Esbscenicareatab.class,ts.getIscenicid());
			if(esb!=null){
				ts.setSzscenicname(esb.getSzscenicname());
			}else{
				ts.setSzscenicname("");
			}
			return ts;
		}
	}
	
}

