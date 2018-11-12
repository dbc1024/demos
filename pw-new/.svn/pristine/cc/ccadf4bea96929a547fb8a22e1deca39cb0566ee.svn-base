package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.provider.dao.IEsbprovicerqDAO;

@Repository
public class EsbprovicerqDAO extends GenericDao implements IEsbprovicerqDAO {
	
	/**
	 * *
	 * Describe:显示所有设置信息
	 * @see com.ectrip.system.provider.service.iservice.IEsbprovicerqService#checkListEsbprovicerq(java.lang.Long, int, int, java.lang.String)
	 * @param iscenicid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author huying
	 * Date:2015-3-26
	 */

	public PaginationSupport checkListEsbprovicerq(Long iscenicid,String scenics,int pageSize,int startIndex,String url){
		StringBuffer hsql=new StringBuffer();
		hsql.append("select new map(p.seq as seq,p.iscenicid as iscenicid ,s.szscenicname as szscenicname,p.szlasttime as szlasttime,p.imaxdata as imaxdata,p.ibusinessid as ibusinessid,b.szbusinessname as szbusinessname,p.szmemo as szmemo,p.int1 as int1) from Esbprovicerq p,Esbscenicareatab s,Edmbusinesstab b where s.iscenicid=p.iscenicid and b.ibusinessid=p.ibusinessid ");
		if(iscenicid!=null&&iscenicid!=0){
			hsql.append(" and s.iscenicid="+iscenicid);
		}else {
			hsql.append(" order by p.seq");
		}
		return this.findPage(hsql.toString(), pageSize, startIndex, url);
	}

	/**
	 * 判断该数据是否存在
	 * Describe:
	 * @author: huying
	 * return:boolean
	 * Date:2015-3-27
	 */
	public boolean esbproviceIsuse(Long scenicid,Long businessId){
		boolean isuse=false;
		String hsql=" from Esbprovicerq p where p.iscenicid="+scenicid+" and  p.ibusinessid="+businessId;
		List<?> list = this.find(hsql);
		if(list!= null && list.size()>0){
			isuse = true;
		}else{
			isuse = false;
		}
		return isuse;
	}
	
	/**
	 * *
	 * Describe:根据主键查询
	 * @see com.ectrip.system.provider.dao.idao.IEsbprovicerqDAO#queryEsbproviceById(java.lang.Long)
	 * @param seqLong
	 * @return
	 * @author huying
	 * Date:2015-3-28
	 */
	public Esbprovicerq queryEsbproviceById(Long seqLong) throws Exception {
		String hsql="select new map(p.seq as seq,p.iscenicid as iscenicid,s.szscenicname as szscenicname,p.szlasttime as szlasttime,p.imaxdata as imaxdata,p.ibusinessid as ibusinessid,b.szbusinessname as szbusinessname,p.szmemo as szmemo,p.int1 as int1) from Esbprovicerq p,Esbscenicareatab s,Edmbusinesstab b where s.iscenicid=p.iscenicid and b.ibusinessid=p.ibusinessid and p.seq="+seqLong;
		List<?> list=super.find(hsql);
		if (list == null || list.size() < 1) {
			return null;
		} else {
			Esbprovicerq esbprovice=new Esbprovicerq();
			BeanUtils.populate(esbprovice, (Map) list.get(0));
			return esbprovice;
		}	

	}
}
