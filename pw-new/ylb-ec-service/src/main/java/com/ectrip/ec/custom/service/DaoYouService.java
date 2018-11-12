package com.ectrip.ec.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.custom.dao.idao.IDaoYouDAO;
import com.ectrip.ec.custom.service.iservice.IDaoYouService;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.model.user.Daoyou;
import com.ectrip.sys.model.syspar.Customlog;

/**
 * 
 * @ClassName: DaoYouService
 * @Description: 我的账户- 团队常用导游管理
 * @author Dicky
 * @date 2011-10-17 下午03:49:09
 * 
 */
public class DaoYouService extends GenericService implements IDaoYouService {
	
	@Autowired
	public IDaoYouDAO daoyouDAO;
	
	private SysparService SysparService;
//	private ISyslogDao syslogDao;

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: deleteDaoYou
	 * </p>
	 * <p>
	 * Description:删除单个团队导游
	 * </p>
	 * 
	 * @param dy
	 * @see com.ectrip.custom.service.iservice.IDaoYouService#deleteDaoYou(com.ectrip.model.user.Daoyou)
	 */
	public void deleteDaoYou(Daoyou dy, Customlog log) {
		SysparService.insertTomlog(log);
		daoyouDAO.deleteDaoYou(dy);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getDaoYou
	 * </p>
	 * <p>
	 * Description:获取单个团队导游
	 * </p>
	 * 
	 * @param usid
	 * @param dyusid
	 * @return
	 * @see com.ectrip.custom.service.iservice.IDaoYouService#getDaoYou(java.lang.String,
	 *      java.lang.String)
	 */
	public Daoyou getDaoYou(String usid, String dyusid) {
		return daoyouDAO.getDaoYou(usid, dyusid);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: saveDaoYou
	 * </p>
	 * <p>
	 * Description: 保存团队导游
	 * </p>
	 * 
	 * @param dy
	 * @see com.ectrip.custom.service.iservice.IDaoYouService#saveDaoYou(com.ectrip.model.user.Daoyou)
	 */
	public void saveDaoYou(Daoyou dy, Customlog log) {
		SysparService.insertTomlog(log);
		daoyouDAO.saveDaoYou(dy);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateDaoYou
	 * </p>
	 * <p>
	 * Description:更新团队导游信息
	 * </p>
	 * 
	 * @param dy
	 * @see com.ectrip.custom.service.iservice.IDaoYouService#updateDaoYou(com.ectrip.model.user.Daoyou)
	 */
	public void updateDaoYou(Daoyou dy, Customlog log) {
		SysparService.insertTomlog(log);
		daoyouDAO.updateDaoYou(dy);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: getDaoYouViewList
	 * </p>
	 * <p>
	 * Description: 分页 用户导游管理
	 * </p>
	 * 
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @see com.ectrip.custom.service.iservice.IDaoYouService#getDaoYouViewList(int,
	 *      int, java.lang.String)
	 */
	public PaginationSupport getDaoYouViewList(String hql, int pageSize, int startIndex, String url) {
		return daoyouDAO.getDaoYouViewList(hql, pageSize, startIndex, url);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: searchContent
	 * </p>
	 * <p>
	 * Description: 导游名字 自动补全
	 * </p>
	 * 
	 * @param key
	 * @return
	 * @see com.ectrip.custom.service.iservice.IDaoYouService#searchContent(java.lang.String)
	 */
	public List searchContent(String key) {
		return daoyouDAO.searchContent(key);
	}
	
	/**
	 * 获取导游信息,daoyou表中只做关联 所有信息以custom表为准
	 * @param hql
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 */
	public PaginationSupport getDaoYouList(String hql, int pageSize, int startIndex, String url) {
		return daoyouDAO.getDaoYouList(hql, pageSize, startIndex, url);
	}

}
