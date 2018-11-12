package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmticketnoruletab;
import com.ectrip.ticket.provider.dao.ITicketRuleDAO;
import com.ectrip.ticket.provider.service.ITicletRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketRuleService extends GenericService implements ITicletRuleService{

	@Autowired
	public ITicketRuleDAO ticketruleDao;
	
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
	public PaginationSupport getAllTicketRuleList(int page,int pageSize,String url){
		return ticketruleDao.getAllTicketRuleList(page,pageSize,url);
	}
	/**
	 * 查询票号规则(只显示规则Id与名称)
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-27
	 */
	public List getTicketRullList(){
		return ticketruleDao.getTicketRullList();
	}
	
	/**
	 * 增加票号规则
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketrule
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void insertTicketRule(Edmticketnoruletab ticketrule,Syslog syslog){
		ticketruleDao.save(ticketrule);
		syslog.setStlg("0036");
		syslog.setBrief("票号规则：" + ticketrule.getSzticketnorulename() );
		syslog.setNote("票号规则增加：" + ticketrule.getSzticketnorulename() +"("+ticketrule.getIticketnoruleid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = ticketruleDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		ticketruleDao.save(syslog);
	}
	/**
	 * 修改票号规则
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketrule
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void updateTicketRule(Edmticketnoruletab ticketrule,Syslog syslog){
		ticketruleDao.update(ticketrule);
		
		syslog.setStlg("0037");
		syslog.setBrief("票号规则：" + ticketrule.getSzticketnorulename() );
		syslog.setNote("票号规则修改：" + ticketrule.getSzticketnorulename() +"("+ticketrule.getIticketnoruleid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = ticketruleDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		ticketruleDao.save(syslog);
	}
	/**
	 * 删除票号规则
	 * Describe:
	 * @auth:huangyuqi
	 * @param ticketRuleId
	 * @param syslog
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteTicketRule(Long ticketRuleId,Syslog syslog){
		Edmticketnoruletab ticketrule = (Edmticketnoruletab)ticketruleDao.get(Edmticketnoruletab.class, ticketRuleId);
		
		syslog.setStlg("0038");
		syslog.setBrief("票号规则：" + ticketrule.getSzticketnorulename() );
		syslog.setNote("票号规则删除：" + ticketrule.getSzticketnorulename() +"("+ticketrule.getIticketnoruleid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = ticketruleDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		ticketruleDao.save(syslog);
		
		ticketruleDao.delete(ticketrule);
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
		return ticketruleDao.queryTicketRuleIsUse(ticketruleId);
	}
}
