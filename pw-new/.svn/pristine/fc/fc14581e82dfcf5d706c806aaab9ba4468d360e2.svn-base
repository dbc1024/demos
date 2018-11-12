package com.ectrip.sys.other.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.Goodlink;
import com.ectrip.sys.model.syspar.Syslog;

/**
 * <li>Description:友情链接Service</li><br>
 * <li>@author Jzhenhua</li><br>
 * <li>@version 1.0</li><br>
 * <li>Date 2011-11-07</li>
 */
public interface IGoodlinkService extends IGenericService {
	/**
	 * <li>Description:新增友情链接</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@param goodlink 新增数据</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void addGoodlink(Goodlink goodlink,Syslog syslog) throws Exception;

	/**
	 * <li>Description:修改友情链接</li><br>
	 * <li>@param goodlink 修改数据</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void updateGoodlink(Goodlink goodlink,Syslog syslog) throws Exception;

	/**
	 * <li>Description:删除友情链接</li><br>
	 * <li>@param goodlink 删除数据</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void deleteGoodlink(Goodlink goodlink,Syslog syslog) throws Exception;

	/**
	 * <li>Description:删除友情链接</li><br>
	 * <li>@param goodlink 删除数据</li><br>
	 * <li>@return com.ectrip.model.other.Goodlink</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public Goodlink getGoodlinkById(Long id) throws Exception;

	/**
	 * <li>Description:获取友情链接列表</li><br>
	 * <li>@param pageSize 页大小</li><br>
	 * <li>@param page 当前页</li><br>
	 * <li>@param String</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@return com.ectrip.base.PaginationSupport</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public PaginationSupport getGoodlinkListView(int pageSize, int page,
												 String url) throws Exception;

	/**
	 *
	 * Describe:前台显示所有的友情链接网址(有网站LOGO的)
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:Nov 17, 2011
	 */
	public List getLinkmoreviewlist();

	/**
	 *
	 * Describe:前台显示所有的友情链接网址(没有网站LOGO的)
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:Dec 1, 2011
	 */
	public List getupfileviewlink();

	public List getupfileviewlink(int top);
	public List getLinkmoreviewlist(int top);
}
