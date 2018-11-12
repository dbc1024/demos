package com.ectrip.ec.userxfjf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Userjf;
import com.ectrip.ec.userxfjf.dao.idao.IUserjfDao;
import com.ectrip.ec.userxfjf.service.iservice.IUserjfService;
import com.ectrip.sys.model.syspar.Syslog;

@Service
public class UserjfService extends GenericService implements IUserjfService {
	private IUserjfDao iUserjfDao;
	
	private IBalanceCenterDAO ibalanceCenterDAO;
	
	@Autowired
	public void setiUserjfDao(IUserjfDao iUserjfDao) {
		this.iUserjfDao = iUserjfDao;
		setGenericDao(iUserjfDao);
	}
	@Autowired
	public void setIbalanceCenterDAO(IBalanceCenterDAO ibalanceCenterDAO) {
		this.ibalanceCenterDAO = ibalanceCenterDAO;
		setGenericDao(ibalanceCenterDAO);
	}

	/**
	 * *
	 * Describe:添加用户积分
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#insertUserjf(com.ectrip.model.user.Userjf, com.ectrip.model.syspar.Syslog)
	 * @param userjf
	 * @param syslog
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public void insertUserjf(Userjf userjf, Long points, Syslog syslog) {
		this.iUserjfDao.insertUserjf(userjf, points, syslog);
	}
	/**
	 * *
	 * Describe:查看所有用户积分
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#showlistUserjf(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public PaginationSupport showlistUserjf(String usid,int pageSize, int startInt,
			String url) {
		return this.iUserjfDao.showlistUserjf(usid,pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:根据编号查看用户积分
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#viewUserjf(java.lang.Long)
	 * @param seq
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(Long seq) {
		return this.iUserjfDao.viewUserjf(seq);
	}
	/**
	 * *
	 * Describe:显示用户积分表里的所有用户
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#showUserList()
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public List showUserList() {
		return this.iUserjfDao.showUserList();
	}
	/**
	 * *
	 * Describe:根据用户名称查找用户积分
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#viewUserjf(java.lang.String)
	 * @param usid
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(String usid) {
		return this.iUserjfDao.viewUserjf(usid);
	}
	/**
	 * *
	 * Describe:根据用户名称查看用户积分详细
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#showUserjflist(java.lang.String, int, int, java.lang.String)
	 * @param usid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public PaginationSupport showUserjflist(String usid, int pageSize,int startInt, String url) {
		return this.iUserjfDao.showUserjflist(usid, pageSize, startInt, url);
	}
	/**
	 * *
	 * Describe:根据积分规则增加用户积分
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#addUserJf(java.lang.String, java.lang.String, java.lang.Long)
	 * @param usid	用户名称
	 * @param jfcode	积分规则代码
	 * @param amount	消费数量(不是积分数量)
	 * @return
	 * @author chenxinhao
	 * Date:2012-8-25
	 */
	public boolean addUserJf(String usid, String jfcode, Long amount) {
		return this.iUserjfDao.addUserJf(usid, jfcode, amount);
	}
	/**
	 * *
	 * Describe:积分转预付款
	 * @see com.ectrip.system.userxfjf.service.iservice.IUserjfService#changejftoyfk(java.lang.String, java.lang.String, java.lang.Long)
	 * @param usid
	 * @param xfcode
	 * @param jf
	 * @author chenxinhao
	 * Date:2012-8-27
	 */
	public void changejftoyfk(String usid, String xfcode, Long jf) {
		Useryfk yfk = this.iUserjfDao.changejftoyfk(usid, xfcode, jf);
		this.ibalanceCenterDAO.useryfksave(yfk);
	}
	
	public List find(String query) {
		return iUserjfDao.find(query);
	}

}

