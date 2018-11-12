package com.ectrip.sys.other.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.other.Goodlink;
import com.ectrip.sys.model.syspar.Syslog;
import org.springframework.stereotype.Repository;

/**
 * <li>Description:��������DAO</li><br>
 * <li>@author Jzhenhua</li><br>
 * <li>@version 1.0</li><br>
 * <li>Date 2011-11-07</li>
 */

public interface IGoodlinkDAO extends IGenericDao {

	/**
	 * <li>Description:������������</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@param goodlink ��������</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void addGoodlink(Goodlink goodlink,Syslog syslog) throws Exception;

	/**
	 * <li>Description:�޸���������</li><br>
	 * <li>@param goodlink �޸�����</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void updateGoodlink(Goodlink goodlink,Syslog syslog) throws Exception;

	/**
	 * <li>Description:ɾ����������</li><br>
	 * <li>@param goodlink ɾ������</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public void deleteGoodlink(Goodlink goodlink,Syslog syslog) throws Exception;

	/**
	 * <li>Description:ɾ����������</li><br>
	 * <li>@param goodlink ɾ������</li><br>
	 * <li>@return com.ectrip.model.other.Goodlink</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public Goodlink getGoodlinkById(Long id) throws Exception;

	/**
	 * <li>Description:��ȡ���������б�</li><br>
	 * <li>@param pageSize ҳ��С</li><br>
	 * <li>@param page ��ǰҳ</li><br>
	 * <li>@param String</li><br>
	 * <li>@author Jzhenhua</li><br>
	 * <li>@return com.ectrip.base.PaginationSupport</li><br>
	 * <li>Date 2011-11-07</li>
	 */
	public PaginationSupport getGoodlinkListView(int pageSize, int page,
			String url) throws Exception;
	
	/**
	 * 
	 * Describe:ǰ̨��ʾ���е�����������ַ(����վLOGO��)
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:Nov 17, 2011
	 */
	public List getLinkmoreviewlist();

	/**
	 * 
	 * Describe:ǰ̨��ʾ���е�����������ַ(û����վLOGO��)
	 * @auth:lijingrui
	 * @return
	 * return:List
	 * Date:Dec 1, 2011
	 */
	public List getupfileviewlink();
	
	public List getupfileviewlink(int top);
	public List getLinkmoreviewlist(int top);
	
}
