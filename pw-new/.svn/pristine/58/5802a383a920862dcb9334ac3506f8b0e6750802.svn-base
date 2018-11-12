package com.ectrip.sys.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.message.dao.idao.IContMessageDAO;
import com.ectrip.sys.message.service.iservice.IContMessageService;
import com.ectrip.sys.model.syspar.Contmessage;

@Service
public class ContMessageService  extends GenericService implements IContMessageService{
	
	private IContMessageDAO contmessageDao;

	@Autowired
	public void setContmessageDao(IContMessageDAO contmessageDao) {
		this.contmessageDao = contmessageDao;
		super.setGenericDao(contmessageDao);
	}

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
	public PaginationSupport showALLContMessage(int page, int pageSize,
			String url) {
		return contmessageDao.showALLContMessage(page, pageSize, url);
	}
	
	/**
	 * *
	 * Describe:添加短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#addContMessage(com.ectrip.sys.model.syspar.model.permitenter.Contmessage)
	 * @param contage
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void addContMessage(Contmessage contage) {
		contmessageDao.addContMessage(contage);
	}
	
	/**
	 * *
	 * Describe:修改短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#editContMessage(com.ectrip.sys.model.syspar.model.permitenter.Contmessage)
	 * @param contage
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void editContMessage(Contmessage contage) {
		contmessageDao.editContMessage(contage);
	}

	/**
	 * *
	 * Describe:删除短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#delContMessage(java.lang.Long)
	 * @param contid
	 * @author lijingrui
	 * Date:Mar 10, 2012
	 */
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void delContMessage(Long contid) {
		contmessageDao.delContMessage(contid);
	}
	
	/**
	 * *
	 * Describe:查看短信发送设置信息
	 * @see com.ectrip.message.service.iservice.IContMessageService#viewContMessage(java.lang.Long)
	 * @param contid
	 * @return
	 * @throws Exception
	 * @author lijingrui
	 * Date:Mar 21, 2012
	 */
	public Contmessage viewContMessage(Long contid) throws Exception {
		return contmessageDao.viewContMessage(contid);
	}
}

