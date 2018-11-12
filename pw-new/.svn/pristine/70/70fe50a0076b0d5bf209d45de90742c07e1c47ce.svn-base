package com.ectrip.ec.user.dao.idao;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Backcustom;
import com.ectrip.sys.model.syspar.Syslog;

public interface IBackCustomDAO {

	/**
	 * Describe:��ȡ�������б�
	 * @auth:aozhuozu
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-10-16
	 */
	PaginationSupport showlistBackcustom(int pageSize, int startInt, String url);

	/**
	 * Describe:��ѯ�������б�
	 * @auth:aozhuozu
	 * @param pageSize
	 * @param startInt
	 * @param url
	 * @param usid
	 * @return
	 * return:PaginationSupport
	 * Date:2012-10-16
	 */
	PaginationSupport searchlistBackcustom(int pageSize, int startInt,
			String url, String usid);

	/**
	 * Describe:�޸ĺ�����
	 * @auth:aozhuozu
	 * @param usid
	 * @return
	 * return:Backcustom
	 * Date:2012-10-16
	 */
	Backcustom viewBackcustom(String usid);

	/**
	 * Describe:ɾ��������
	 * @auth:aozhuozu
	 * @param backcustom
	 * @param syslog
	 * return:void
	 * Date:2012-10-16
	 */
	void deleteBackcustom(Backcustom backcustom, Syslog syslog);

	/**
	 * Describe:���������
	 * @auth:aozhuozu
	 * @param backcustom
	 * @param syslog
	 * return:void
	 * Date:2012-10-16
	 */
	void insertBackcustom(Backcustom backcustom, Syslog syslog);

	/**
	 * Describe:���º�����
	 * @auth:aozhuozu
	 * @param backcustom
	 * @param syslog
	 * return:void
	 * Date:2012-10-16
	 */
	void updateBackcustom(Backcustom backcustom, Syslog syslog);

	/**
	 * Describe:�����û��Ƿ��������ע���û���
	 * @auth:aozhuozu
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2012-10-16
	 */
	boolean checkBackcustom(String usid);

	/**
	 * Describe:�����û��Ƿ�����ں�������
	 * @auth:aozhuozu
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2012-10-16
	 */
	boolean checkCustom(String usid);

}
