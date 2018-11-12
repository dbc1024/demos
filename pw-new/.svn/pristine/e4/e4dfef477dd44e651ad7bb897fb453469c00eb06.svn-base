package com.ectrip.ec.userxfjf.service.iservice;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Userjf;
import com.ectrip.sys.model.syspar.Syslog;
public interface IUserjfService extends IGenericService {
	/**
	 * 
	 * Describe:查看所有用户积分
	 * @author:chenxinhao
	 * @param usid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-25
	 */
	public PaginationSupport showlistUserjf(String usid,int pageSize, int startInt, String url);
	/**
	 * 
	 * Describe:添加用户积分
	 * @author:chenxinhao
	 * @param userjf
	 * @param syslog
	 * return:void
	 * Date:2012-8-25
	 */
	public void insertUserjf(Userjf userjf,Long points, Syslog syslog);
	/**
	 * 
	 * Describe:根据编号查看用户积分
	 * @author:chenxinhao
	 * @param seq
	 * @return
	 * return:Userjf
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(Long seq);
	/**
	 * 
	 * Describe:显示用户积分表里的所有用户
	 * @author:chenxinhao
	 * @return
	 * return:List
	 * Date:2012-8-25
	 */
	public List showUserList();
	/**
	 * 
	 * Describe:根据用户名称查找用户积分
	 * @author:chenxinhao
	 * @param usid
	 * @return
	 * return:Userjf
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(String usid);
	/**
	 * 
	 * Describe:根据用户名称查看用户积分详细
	 * @author:chenxinhao
	 * @param usid
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-8-25
	 */
	public PaginationSupport showUserjflist(String usid,int pageSize, int startInt, String url);
	/**
	 * 
	 * Describe:根据积分规则增加用户积分
	 * @author:chenxinhao
	 * @param usid	用户名称
	 * @param jfcode	积分规则代码
	 * @param amount	消费数量(不是积分数量)
	 * @return
	 * return:boolean
	 * Date:2012-8-25
	 */
	public boolean addUserJf(String usid,String jfcode,Long amount);
	/**
	 * 
	 * Describe:积分转预付款
	 * @author:chenxinhao
	 * @param usid
	 * @param xfcode
	 * @param jf
	 * return:void
	 * Date:2012-8-27
	 */
	public void changejftoyfk(String usid, String xfcode, Long jf);
	
	public List find(String query);
}

