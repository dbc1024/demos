package com.ectrip.sys.other.service;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.Goodlink;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.other.dao.idao.IGoodlinkDAO;
import com.ectrip.sys.other.service.iservice.IGoodlinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <li>Description:��������Service</li><br>
 * <li>@author Jzhenhua</li><br>
 * <li>@version 1.0</li><br>
 * <li>Date 2011-11-07</li>
 */
@Service
public class GoodlinkService extends GenericService implements IGoodlinkService {

	@Autowired
	private IGoodlinkDAO goodlinkDao;

	/**
	 * <li>Description:新增友情链接</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@param goodlink 新增数据</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void addGoodlink(Goodlink goodlink, Syslog syslog) throws Exception {
		this.goodlinkDao.addGoodlink(goodlink, syslog);
	}

	/**
	 * <li>Description:修改友情链接</li><br>
	 * <li>@param goodlink 修改数据</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void updateGoodlink(Goodlink goodlink, Syslog syslog)
			throws Exception {
		this.goodlinkDao.updateGoodlink(goodlink, syslog);
	}

	/**
	 * <li>Description:删除友情链接</li><br>
	 * <li>@param goodlink 删除数据</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void deleteGoodlink(Goodlink goodlink, Syslog syslog)
			throws Exception {
		this.goodlinkDao.deleteGoodlink(goodlink, syslog);
	}

	/**
	 * <li>Description:删除友情链接</li><br>
	 * <li>@param goodlink 删除数据</li><br>
	 * <li>@return com.ectrip.model.other.Goodlink</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public Goodlink getGoodlinkById(Long id) throws Exception {
		return this.goodlinkDao.getGoodlinkById(id);
	}

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
												 String url) throws Exception {
		return this.goodlinkDao.getGoodlinkListView(pageSize, page, url);
	}

	/**
	 * *
	 * Describe:前台显示所有的友情链接网址(有网站LOGO的)
	 * @see com.ectrip.system.other.service.iservice.IGoodlinkService#getLinkmoreviewlist()
	 * @return
	 * @author lijingrui
	 * Date:Nov 17, 2011
	 */
	public List getLinkmoreviewlist() {
		return goodlinkDao.getLinkmoreviewlist();
	}


	/**
	 * *
	 * Describe:前台显示所有的友情链接网址(没有网站LOGO的)
	 * @see com.ectrip.system.other.service.iservice.IGoodlinkService#getupfileviewlink()
	 * @return
	 * @author lijingrui
	 * Date:Dec 1, 2011
	 */
	public List getupfileviewlink(){
		return goodlinkDao.getupfileviewlink();
	}

	public List getupfileviewlink(int top){
		return goodlinkDao.getupfileviewlink(top);
	}
	public List getLinkmoreviewlist(int top){
		return goodlinkDao.getLinkmoreviewlist(top);
	}
}
