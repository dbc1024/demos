package com.ectrip.sys.message.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.enums.MESG;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.message.dao.idao.IContMessageDAO;
import com.ectrip.sys.model.syspar.Contmessage;

@Repository
public class ContMessageDAO extends GenericDao implements IContMessageDAO{

	/**
	 * *
	 * Describe:显示出所有的短信发送控制信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#showALLContMessage(int, int, java.lang.String)
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	public PaginationSupport showALLContMessage(int page, int pageSize, String url) {
		
//		String sql="select new map("
//				+ " cm.conid as conid, cm.templates as templates, cm.byisuse as byisuse, cm.inote1 as inote1, cm.inote2 as inote2, cm.snote1 as snote1, cm.snote2 as snote2,"
//				+ " sys1.pmva as controlpoin"
//				+ " ) "
//				+ " from Contmessage cm, Sysparv5 sys1 "
//				+ " where sys1.id.pmcd=cm.controlpoin and sys1.id.pmky='MESG'";
		
		String sql="select new map("
				+ " cm.conid as conid, cm.controlpoin as controlpoin, cm.templates as templates, cm.byisuse as byisuse, cm.inote1 as inote1, cm.inote2 as inote2, cm.snote1 as snote1, cm.snote2 as snote2"
				+ " ) "
				+ " from Contmessage cm";
		
		PaginationSupport ps = this.findPage(sql, pageSize, page, url);
		List<Map> items = ps.getItems();
		if(items!=null && items.size()>0) {
			
			for (Map contmessage : items) {
				String controlpoin = contmessage.get("controlpoin").toString();
				String pmva = MESG.getPmvaByPmcd(controlpoin);
				
				contmessage.put("controlpoin", pmva);
			}
		}
		
		return ps;
	}
	
	/**
	 * *
	 * Describe:添加短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#addContMessage(com.ectrip.sys.model.syspar.model.permitenter.Contmessage)
	 * @param contage
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	public void addContMessage(Contmessage contage) {
		Long maxid=this.getMaxPk("conid", "Contmessage");
		contage.setConid(maxid+1);
		this.save(contage);
	}
	
	/**
	 * *
	 * Describe:修改短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#editContMessage(com.ectrip.sys.model.syspar.model.permitenter.Contmessage)
	 * @param contage
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	public void editContMessage(Contmessage contage) {
		Contmessage message=(Contmessage) this.get(Contmessage.class, contage.getConid());
		message.setControlpoin(contage.getControlpoin());
		message.setTemplates(contage.getTemplates());
		message.setByisuse(contage.getByisuse());
		
		this.update(message);
	}

	/**
	 * *
	 * Describe:删除短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#delContMessage(java.lang.Long)
	 * @param contid
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	public void delContMessage(Long contid) {
		Contmessage message=(Contmessage) this.get(Contmessage.class, contid);
		this.delete(message);
	}
	
	/**
	 * *
	 * Describe:查看短信发送设置信息
	 * @see com.ectrip.message.dao.idao.IContMessageDAO#viewContMessage(java.lang.Long)
	 * @param contid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Mar 21, 2012
	 */
	public Contmessage viewContMessage(Long contid) throws Exception {
		
//		String sql="select new map("
//				+ " cm.conid as conid, cm.templates as templates, cm.byisuse as byisuse, cm.inote1 as inote1, cm.inote2 as inote2, cm.snote1 as snote1, cm.snote2 as snote2,"
//				+ " sys1.pmva as controlpoin"
//				+ " ) "
//				+ " from Contmessage cm, Sysparv5 sys1 "
//				+ " where sys1.id.pmcd=cm.controlpoin and sys1.id.pmky='MESG' and cm.conid="+contid;
		
		String sql="select new map("
				+ " cm.controlpoin as controlpoin, cm.conid as conid, cm.templates as templates, cm.byisuse as byisuse, cm.inote1 as inote1, cm.inote2 as inote2, cm.snote1 as snote1, cm.snote2 as snote2"
				+ " ) "
				+ " from Contmessage cm"
				+ " where cm.conid="+contid;
		
		
		List lst=this.find(sql);
		Contmessage message=new Contmessage();
		if(lst!=null&&lst.size()>0){
			BeanUtils.populate(message, (Map) lst.get(0));
			
			message.setControlpoin(MESG.getPmvaByPmcd(message.getControlpoin()));
		}
		return message;
	}
}

