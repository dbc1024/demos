package com.ectrip.ec.custom.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.custom.dao.idao.IDaoYouDAO;
import com.ectrip.ec.model.user.Daoyou;

/**
 * 
 * @ClassName: DaoYouDAO
 * @Description: �Ŷӳ��õ��ι���
 * @author Dicky
 * @date 2011-10-17 ����03:39:34
 * 
 */
public class DaoYouDAO extends GenericDao implements IDaoYouDAO {
	/**
	 * (�� Javadoc)
	 * <p>
	 * Title: deleteDaoYou
	 * </p>
	 * <p>
	 * Description: ɾ��
	 * </p>
	 * 
	 * @param usid
	 * @param dyusid
	 * @see com.ectrip.custom.dao.idao.IDaoYouDAO#deleteDaoYou(java.lang.String,
	 *      java.lang.String)
	 */
	public void deleteDaoYou(Daoyou dy) {
		this.delete(dy);
	}

	/**
	 * (�� Javadoc)
	 * <p>
	 * Title: getDaoYou
	 * </p>
	 * <p>
	 * Description:��ȡһ���Ŷӵ�����Ϣ
	 * </p>
	 * 
	 * @param usid
	 * @param dyusid
	 * @return
	 * @see com.ectrip.custom.dao.idao.IDaoYouDAO#getDaoYou(java.lang.String,
	 *      java.lang.String)
	 */
	public Daoyou getDaoYou(String usid, String dyusid) {
		String hql = " from Daoyou where usid='" + usid + "' and dyusid='" + dyusid + "'";
		if (this.find(hql).size() == 0) {
			return null;
		}
		return (Daoyou) this.find(hql).get(0);
	}

	/**
	 * (�� Javadoc)
	 * <p>
	 * Title: saveDaoYou
	 * </p>
	 * <p>
	 * Description:����
	 * </p>
	 * 
	 * @param dy
	 * @see com.ectrip.custom.dao.idao.IDaoYouDAO#saveDaoYou(com.ectrip.model.user.Daoyou)
	 */
	public void saveDaoYou(Daoyou dy) {
		this.save(dy);
	}

	/**
	 * (�� Javadoc)
	 * <p>
	 * Title: updateDaoYou
	 * </p>
	 * <p>
	 * Description:����
	 * </p>
	 * 
	 * @param dy
	 * @see com.ectrip.custom.dao.idao.IDaoYouDAO#updateDaoYou(com.ectrip.model.user.Daoyou)
	 */
	public void updateDaoYou(Daoyou dy) {
		this.update(dy);
	}

	/**
	 * (�� Javadoc)
	 * <p>
	 * Title: getDaoYouViewList
	 * </p>
	 * <p>
	 * Description: ��ҳ �û����ι���
	 * </p>
	 * 
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @see com.ectrip.custom.dao.idao.IDaoYouDAO#getDaoYouViewList(java.lang.String,
	 *      int, int, java.lang.String)
	 */
	public PaginationSupport getDaoYouViewList(String hql, int pageSize, int startIndex, String url) {
		String hsql = " FROM Daoyou d WHERE 1=1 " + hql;
		return this.findPage(hsql, pageSize, startIndex, url);
	}
	
	
	public PaginationSupport getDaoYouList(String hql, int pageSize, int startIndex, String url) {
		String hsql = "select new map(d.id.dyusid as dyusid,custom.zjhm as idcard,custom.lname as lname,custom.daoyouno as tourcard,custom.telno as telno,custom.mobile as mobile,custom.status as status) FROM Custom custom,Daoyou d WHERE custom.usid=d.id.dyusid and custom.lgtp='02' and custom.ttlb='02' " + hql;
		return this.findPage(hsql, pageSize, startIndex, url);
	} 

	/**
	 * (�� Javadoc)
	 * <p>
	 * Title: searchContent
	 * </p>
	 * <p>
	 * Description:�Զ���ȫ ����name
	 * </p>
	 * 
	 * @param key
	 * @return
	 * @see com.ectrip.custom.dao.idao.IDaoYouDAO#searchContent(java.lang.String)
	 */
	public List searchContent(String key) {
		return this.find("select distinct lname from daoyou where lname like '%" + key + "%'");
	}

}
