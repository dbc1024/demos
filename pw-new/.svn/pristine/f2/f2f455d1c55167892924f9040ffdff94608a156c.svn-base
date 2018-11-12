package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;

public interface ITicletRuleService extends IGenericService {
	/**
	 * 查询所有票号规则
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
	 * 查询票号规则(只显示规则Id与名称)
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-27
	 */
	public List getTicketRullList();
	/**
	 * 增加票号规则
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketrule
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void insertTicketRule(Edmticketnoruletab ticketrule,Syslog syslog);
	/**
	 * 修改票号规则
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketrule
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void updateTicketRule(Edmticketnoruletab ticketrule,Syslog syslog);
	/**
	 * 删除票号规则
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketRuleId
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteTicketRule(Long ticketRuleId,Syslog syslog);
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
