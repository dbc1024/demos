package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;

public interface ITicketRuleDAO extends IGenericDao {
	/**
	 * 查询票号规则（分页）
	 * Describe:
	 * @auth:huangyuqi
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-27
	 */
	public PaginationSupport getAllTicketRuleList(int page,int pageSize,String url);
	/**
	 * 查询票号规则（显示规则Id与名称）
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-27
	 */
	public List getTicketRullList();
	
	/**
	 * 判断它是否被产品中引用
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketruleId
	 * @return
	 * return:boolean
	 * Date:2011-9-27
	 */
	public boolean queryTicketRuleIsUse(Long ticketruleId);
}
