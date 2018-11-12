package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.provider.dao.IEdpofferschemetabDAO;
import com.ectrip.ticket.provider.service.IEdpofferschemetabService;


/**
 * Description:�Żݷ���Service
 * 
 * @author Jzhenhua<br>
 *         Date 2011-10-24
 */
@Service
public class EdpofferschemetabService implements IEdpofferschemetabService {

	private IEdpofferschemetabDAO edpofferschemetabDao;

	public IEdpofferschemetabDAO getEdpofferschemetabDao() {
		return edpofferschemetabDao;
	}

	public void setEdpofferschemetabDao(
			IEdpofferschemetabDAO edpofferschemetabDao) {
		this.edpofferschemetabDao = edpofferschemetabDao;
	}

	/**
	 * Description:添加优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void addEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog) {
		edpofferschemetab.setIoffersschemeid(this.edpofferschemetabDao
				.getMaxId());
		this.edpofferschemetabDao.addEdpofferschemetab(edpofferschemetab,syslog);
	}

	/**
	 * Description:删除优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void deleteEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog) {
		this.edpofferschemetabDao.deleteEdpofferschemetab(edpofferschemetab,syslog);
	}

	/**
	 * Description:修改优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void updateEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog) {
		this.edpofferschemetabDao.updateEdpofferschemetab(edpofferschemetab,syslog);
	}

	/**
	 * Description:根据编号查询优惠方案
	 * 
	 * @param ioffersschemeid
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public Edpofferschemetab getEdpofferschemetabbyId(Long ioffersschemeid) {
		return this.edpofferschemetabDao
				.getEdpofferschemetabbyId(ioffersschemeid);
	}

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
			int pageSize, String url) {
		return this.edpofferschemetabDao.getEdpofferschemetabListView(
				edpofferschemetab, scenics, page, pageSize, url);
	}

	/**
	 * Description:获得所有产品类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public List getTickettypeList(String scenics) {
		return this.edpofferschemetabDao.getTickettypeList(scenics);
	}

	/**
	 * Description:获得所有人群种族
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public List getCrowdList() {
		return this.edpofferschemetabDao.getCrowdList();
	}

	/**
	 * Description:获得所有业务类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public List getBusinessList() {
		return this.edpofferschemetabDao.getBusinessList();
	}

	/**
	 * Description:获得所有优惠类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-25
	 */
	public List getOSTPList() {
		return this.edpofferschemetabDao.getOSTPList();
	}

	/**
	 * Descrption:获得所有优惠对象方式
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-25
	 */
	public List getOOTPList() {
		return this.edpofferschemetabDao.getOOTPList();
	}
	/**
	 * *
	 * Describe:查找pmky='OOTP' pmcd = '0' 的信息
	 * @see com.ectrip.system.provider.service.iservice.IEdpofferschemetabService#showOOTPList()
	 * @return
	 * @author chenxinhao
	 * Date:2013-1-7
	 */
	public List showOOTPList(){
		return this.edpofferschemetabDao.showOOTPList();
	}
	/**
	 * *
	 * Describe:查找优惠信息
	 * @see com.ectrip.system.provider.service.iservice.IEdpofferschemetabService#showEdpofferschemetabList(com.ectrip.model.provider.Edpofferschemetab)
	 * @param scheme
	 * @return
	 * @author chenxinhao
	 * Date:2013-1-7
	 */
	public boolean showEdpofferschemetabList(Edpofferschemetab scheme){
		return this.edpofferschemetabDao.showEdpofferschemetabList(scheme);
	}
	
	/**
	 * 
	 * Describe:判断该优惠方案是否已存在订单
	 * @author:chenxinhao
	 * @param ioffersschemeid
	 * @return
	 * return:int
	 * Date:2013-5-30
	 */
	public int checkorder(Long ioffersschemeid){
		return this.edpofferschemetabDao.checkorder(ioffersschemeid);
	}
}
