package com.ectrip.ticket.provider.service;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esbprovicerq;


public interface IEsbprovicerqService extends IGenericService{

	/**
	 * 
	 * Describe:显示所有设置信息
	 * @author: huying
	 * @param iscenicid
	 * @param scenics
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2015-3-28
	 */
	public PaginationSupport checkListEsbprovicerq(Long iscenicid,String scenics,int pageSize,int startIndex,String url);

	
	/**
	 * Describe:新增
	 * @author: huying
	 * @param esbprovicerq
	 * @param syslog
	 * return:void
	 * Date:2015-3-27
	 */
	public void insertEsbprovice(Esbprovicerq esbprovicerq,Syslog syslog);
	
	/**
	 * Describe:修改
	 * @author: huying
	 * @param esbprovicerq
	 * @param syslog
	 * return:void
	 * Date:2015-3-27
	 */
	public void updateEsbprovice(Esbprovicerq esbprovicerq,Syslog syslog);
	/**
	 * 
	 * Describe:根据编号删除
	 * @author: huying
	 * @param seq
	 * @param syslog
	 * return:void
	 * Date:2015-3-27
	 */
	public void deleteEsbprovice(Long seq,Syslog syslog);
	

	/**
	 * Describe:判断该数据是否存在
	 * @author: huying
	 * @param scenicid
	 * @param businessId
	 * @return
	 * return:boolean
	 * Date:2015-3-27
	 */
	public boolean esbproviceIsuse(Long scenicid,Long businessId);
	
	/**
	 * 
	 * Describe:根据编号查询对象
	 * @author: huying
	 * @param seqLong
	 * @return
	 * return:Esbprovicerq
	 * Date:2015-3-28
	 * @throws Exception 
	 */
	public Esbprovicerq queryEsbproviceById(Long seqLong) throws Exception;

}
