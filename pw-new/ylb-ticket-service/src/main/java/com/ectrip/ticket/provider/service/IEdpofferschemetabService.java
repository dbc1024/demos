package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edpofferschemetab;

/**
 * Description:优惠方案Service
 * 
 * @author Jzhenhua<br>
 *         Date 2011-10-24
 */
public interface IEdpofferschemetabService {
	/**
	 * Description:添加优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void addEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog);

	/**
	 * Description:删除优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void deleteEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog);

	/**
	 * Description:修改优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void updateEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog);

	/**
	 * Description:根据编号查询优惠方案
	 * 
	 * @param ioffersschemeid
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public Edpofferschemetab getEdpofferschemetabbyId(Long ioffersschemeid);

	/**
	 * Description:获取优惠方案列表
	 * 
	 * @param edpofferschemetab
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public PaginationSupport getEdpofferschemetabListView(
			Edpofferschemetab edpofferschemetab, String scenics, int page,
			int pageSize, String url);

	/**
	 * Description:获得所有产品类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public List getTickettypeList(String scenics);

	/**
	 * Description:获得所有人群种族
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public List getCrowdList();

	/**
	 * Description:获得所有业务类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public List getBusinessList();
	
	/**
	 * Description:获得所有优惠类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-25
	 */
	public List getOSTPList();

	/**
	 * Descrption:获得所有优惠对象方式
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-25
	 */
	public List getOOTPList();
	/**
	 * 
	 * Describe:查找pmky='OOTP' pmcd = '0' 的信息
	 * @author:chenxinhao
	 * @return
	 * return:List
	 * Date:2013-1-7
	 */
	public List showOOTPList();
	/**
	 * 
	 * Describe:查找优惠信息
	 * @author:chenxinhao
	 * @param scheme
	 * @return
	 * return:List
	 * Date:2013-1-7
	 */
	public boolean showEdpofferschemetabList(Edpofferschemetab scheme);
	
	/**
	 * 
	 * Describe:判断该优惠方案是否已存在订单
	 * @author:chenxinhao
	 * @param ioffersschemeid
	 * @return
	 * return:int
	 * Date:2013-5-30
	 */
	public int checkorder(Long ioffersschemeid);
}
