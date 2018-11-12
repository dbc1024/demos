package com.ectrip.ticket.provider.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.provider.dao.ITicketRuleDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRuleDAO extends GenericDao implements ITicketRuleDAO{
	/**
	 * 票号规则列表*
	 * Describe:
	 * @see com.ectrip.system.provider.dao.idao.ITicketRuleDAO#getAllTicketRuleList(int, int, java.lang.String)
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public PaginationSupport getAllTicketRuleList(int page,int pageSize,String url){
		PaginationSupport ps;
		String hsql=" select new  map(rule.iticketnoruleid as iticketnoruleid,rule.iscenicid as iscenicid,esb.szscenicname as szscenicname,rule.szticketnorulecode as szticketnorulecode,rule.szticketnorulename as szticketnorulename,rule.iticketnolen as iticketnolen,rule.szticketnoprefix as szticketnoprefix,rule.itickettypecodepos as itickettypecodepos,rule.iserialnopos as iserialnopos,rule.iserialnolen as iserialnolen,rule.szticketnosuffix as szticketnosuffix,rule.intons1 as intons1,rule.intons2 as intons2,rule.sztron1 as sztron1,rule.byisuse as byisuse) from Edmticketnoruletab rule,Esbscenicareatab esb where esb.iscenicid=rule.iscenicid ";
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
	/**
	 * 查询票号规则（只显示规则Id与名称）*
	 * Describe:
	 * @see com.ectrip.system.provider.dao.idao.ITicketRuleDAO#getTicketRullList()
	 * @return
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public List getTicketRullList(){
		List list = new ArrayList();
		String hsql="select new map(iticketnoruleid as iticketnoruleid,szticketnorulecode as szticketnorulecode,szticketnorulename as szticketnorulename) from Edmticketnoruletab";
		list = this.find(hsql);
		return list;
	}
	
	/**
	 * 判断它是否被产品中引用
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketruleId
	 * @return
	 * return:boolean
	 * Date:2011-9-27
	 */
	public boolean queryTicketRuleIsUse(Long ticketruleId){
		boolean isuse=false;
		String hsql=" from Edmtickettypetab where iticketnoruleid="+ticketruleId;
		List list = this.find(hsql);
		if(list.size()>0){
			isuse=true;
		}
		return isuse;
	}

}
