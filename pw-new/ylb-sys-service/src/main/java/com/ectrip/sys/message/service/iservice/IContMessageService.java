package com.ectrip.sys.message.service.iservice;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Contmessage;

public interface IContMessageService  extends IGenericService {
	/**
	 *
	 * Describe:显示出所有的短信发送控制信息
	 * @auth:lijingrui
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Mar 10, 2012
	 */
	public PaginationSupport showALLContMessage(int page,int pageSize,String url);

	/**
	 *
	 * Describe:添加短信发送设置信息
	 * @auth:lijingrui
	 * @param contage
	 * return:void
	 * Date:Mar 10, 2012
	 */
	public void addContMessage(Contmessage contage);

	/**
	 *
	 * Describe:修改短信发送设置信息
	 * @auth:lijingrui
	 * @param contage
	 * return:void
	 * Date:Mar 10, 2012
	 */
	public void editContMessage(Contmessage contage);

	/**
	 *
	 * Describe:删除短信发送设置信息
	 * @auth:lijingrui
	 * @param contid
	 * return:void
	 * Date:Mar 10, 2012
	 */
	public void delContMessage(Long contid);

	/**
	 *
	 * Describe:查看短信发送设置信息
	 * @auth:lijingrui
	 * @param contid
	 * @return
	 * @throws Exception
	 * return:Contmessage
	 * Date:Mar 21, 2012
	 */
	public Contmessage viewContMessage(Long contid) throws Exception;
}

