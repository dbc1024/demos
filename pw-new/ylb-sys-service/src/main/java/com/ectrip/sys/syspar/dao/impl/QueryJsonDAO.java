package com.ectrip.sys.syspar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.sys.syspar.dao.IQueryJsonDAO;

public class QueryJsonDAO extends GenericDao implements IQueryJsonDAO {

	/**
	 *  查询服务商json数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param proname服务商名称 
	 * @param prtp服务商类型
	 * @param isjd是否是景点
	 * @return
	 * return:List
	 * Date:2011-11-3
	 */
	public List getProviderJsonList(String proname,String prtp,String isjd){
		List list = new ArrayList();
		
		StringBuffer hsql= new StringBuffer();
		hsql.append("select pd.iscenicid as iscenicid,pd.szscenicname as szscenicname from Esbscenicareatab pd where pd.byisuse =1 ");
		
		if(proname!=null && !"".equals(proname)){
			hsql.append(" and pd.szscenicname like '%"+proname+"%'");
		}
		if(prtp!=null || !"".equals(prtp)){
			hsql.append(" and pd.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"+prtp+"'or spmcd='"+prtp+"')) ");
		}
		
		if(isjd!=null && !"".equals(isjd)){
			if("01".equals(prtp)){
				if("0".equals(isjd)){
					hsql.append(" and pd.isjd=0 ");
				}
				if("1".equals(isjd)){
					hsql.append(" and pd.isjd=1 ");
				}
			}else{
				hsql.append(" and pd.isjd=1");
			}
		}	
		
		hsql.append(" order by pd.scenictype, pd.iscenicid ");
		list = this.find(hsql.toString());
		return list;
	}

	/**
	 * 查询用户json数据库
	 * Describe:
	 * @auth:huangyuqi
	 * @param username用户名称
	 * @param lgtp用户类别
	 * @param ttlb团体类别（当lgtp为散客时，ttlb可以为空）
	 * @return
	 * return:List
	 * Date:2011-11-3
	 */
	public List getCustomJsionList(String username,String lgtp,String ttlb){
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select cu.usid as usid,cu.corpname as corpname from Custom cu and cu.status = '01' ");
		if(username!=null && !"".equals(username)){
			hsql.append(" and cu.corpname like '%"+username+"%' ");
		}
		if(lgtp!=null && !"".equals(lgtp)){
			hsql.append(" and cu.lgtp='"+lgtp+"' ");
			if("02".equals(lgtp)){
				if(ttlb!=null && !"".equals(ttlb)){
					hsql.append(" and cu.ttlb='"+ttlb+"' ");
				}
			}
		}
		
		hsql.append(" order by cu.usid ");
		list = this.find(hsql.toString());
		return list;
	}

}

