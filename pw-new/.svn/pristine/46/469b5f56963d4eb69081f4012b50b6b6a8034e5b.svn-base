package com.ectrip.ticket.provider.service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Esplxqytab;

public interface IEsplxqytabService {

	/**
	 * Describe:添加旅行社区域
	 * @auth：shenzhixiong
	 * @param esplxqytab
	 * @param syslog
	 * @return void
	 * Date:2014-4-28
	 */
	public void addEsplxqytab(Esplxqytab esplxqytab,Syslog syslog);
	
	/**
	 * Describe:删除旅行社区域
	 * @auth：shenzhiiong
	 * @param syslog
	 * @param seq
	 * @return void
	 * Date:2014-4-28
	 */
	public void deleteEsplxqytab(Long seq,Syslog syslog);
	 
	/**
	 * Describe:修改旅行社区域
	 * @auth:shenzhiiong
	 * @param esplxqytab
	 * @param syslog
	 * @return void
	 * Date:2014-4-28
	 */
	public void updateEsplxqytab(Esplxqytab esplxqytab,Syslog syslog);
	
	/**
	 * Describe:显示旅行社区域列表
	 * @auth:shenzhixiong
	 * @param iscenicid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return ps
	 * Date:2014-4-28
	 */
	public PaginationSupport showEsplxqytabList(Long iscenicid,String flag,String query,int pageSize,int startIndex,String url);

	/**
	 * Describe:根据seq查询旅行社区域列表
	 * @auth:shenzhixiong
	 * @param seq
	 * @return Esplxqytab
	 * Date:2014-4-28
	 */
	public Esplxqytab getEsplxqytabfindBySeq(Long seq)  throws Exception;
	
	
}
