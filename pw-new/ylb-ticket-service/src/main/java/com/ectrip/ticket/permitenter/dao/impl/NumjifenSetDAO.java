package com.ectrip.ticket.permitenter.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.permitenter.dao.INumjifenSetDAO;

public class NumjifenSetDAO extends GenericDao implements INumjifenSetDAO {
	
	/**
	 * *
	 * Describe:��ʾ�����еĹ�����Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#showAllviewNumjifen(java.lang.Long, int, int, java.lang.String)
	 * @param jflb
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public PaginationSupport showAllviewNumjifen(String scenics,Long jflb,int pageSize,int startIndex, String url){
		StringBuffer sql=new StringBuffer();
		sql.append("select new map(ns.nid as nid,sc.szscenicname as szscenicname,ns.minnum as minnum,ns.maxnum as maxnum,ns.jflb as jflb,ns.jnum as jnum,ns.point as point) from Numjifenset ns,Esbscenicareatab sc where sc.iscenicid=ns.iscenicid and ns.jflb="+jflb);
		if(scenics!=null&&!scenics.equals("")){
			sql.append(" and ns.iscentcid in ("+scenics+") ");
		}
		return this.findPage(sql.toString(), pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:��ӹ���
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#insertNumjifen(com.ectrip.model.permitenter.Numjifenset)
	 * @param numset
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public void insertNumjifen(Numjifenset numset){
		Long maxid=this.getMaxPk("nid", "Numjifenset");
		numset.setNid(maxid+1);
		
		this.save(numset);
	}
	
	/**
	 * *
	 * Describe:�޸Ĺ���
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#updateNumjifen(com.ectrip.model.permitenter.Numjifenset)
	 * @param numset
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public void updateNumjifen(Numjifenset numset){
		Numjifenset jifenset=(Numjifenset) this.get(Numjifenset.class, numset.getNid());
		jifenset.setIscenicid(numset.getIscenicid());
		jifenset.setMinnum(numset.getMinnum());
		jifenset.setMaxnum(numset.getMaxnum());
		jifenset.setJnum(numset.getJnum());
		jifenset.setPoint(numset.getPoint());
		this.update(jifenset);
	}
	
	/**
	 * *
	 * Describe:ɾ������
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#deleteNumjifen(java.lang.Long)
	 * @param nid
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public void deleteNumjifen(Long nid){
		Numjifenset jifenset=(Numjifenset) this.get(Numjifenset.class, nid);
		this.delete(jifenset);
	}
	
	/**
	 * *
	 * Describe:�鿴����
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetService#viewNumjifen(java.lang.Long)
	 * @param nid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Mar 29, 2012
	 */
	public Numjifenset viewNumjifen(Long nid) throws Exception{
		String sql="select new map(ns.nid as nid,sc.szscenicname as szscenicname,ns.minnum as minnum,ns.maxnum as maxnum,ns.jflb as jflb,ns.jnum as jnum,ns.point as point) from Numjifenset ns,Esbscenicareatab sc where sc.iscenicid=ns.iscenicid and ns.nid="+nid;
		List list = super.find(sql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Numjifenset ts=new Numjifenset();
			BeanUtils.populate(ts, (Map) list.get(0));
			return ts;
		}
	}
}

