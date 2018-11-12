package com.ectrip.ec.userxfjf.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Userjf;
import com.ectrip.sys.model.syspar.Syslog;

public interface IUserjfDao extends IGenericDao {
	/**
	 * 
	 * Describe:�鿴�����û�����
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
	 * Describe:����û�����
	 * @author:chenxinhao
	 * @param userjf
	 * @param syslog
	 * return:void
	 * Date:2012-8-25
	 */
	public void insertUserjf(Userjf userjf,Long points, Syslog syslog);
	/**
	 * 
	 * Describe:���ݱ�Ų鿴�û�����
	 * @author:chenxinhao
	 * @param seq
	 * @return
	 * return:Userjf
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(Long seq);
	/**
	 * 
	 * Describe:��ʾ�û����ֱ���������û�
	 * @author:chenxinhao
	 * @return
	 * return:List
	 * Date:2012-8-25
	 */
	public List showUserList();
	/**
	 * 
	 * Describe:�����û����Ʋ����û�����
	 * @author:chenxinhao
	 * @param usid
	 * @return
	 * return:Userjf
	 * Date:2012-8-25
	 */
	public Userjf viewUserjf(String usid);
	/**
	 * 
	 * Describe:�����û����Ʋ鿴�û�������ϸ
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
	 * Describe:���ݻ��ֹ��������û�����
	 * @author:chenxinhao
	 * @param usid	�û�����
	 * @param jfcode	���ֹ������
	 * @param amount	��������(���ǻ�������)
	 * return:void
	 * Date:2012-8-25
	 */
	public boolean addUserJf(String usid,String jfcode,Long amount);
	/**
	 * 
	 * Describe:����תԤ����
	 * @author:chenxinhao
	 * @param usid
	 * @param xfcode
	 * @param jf
	 * @return
	 * return:Useryfk
	 * Date:2012-8-27
	 */
	public Useryfk changejftoyfk(String usid, String xfcode, Long jf);
}

