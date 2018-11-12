package com.ectrip.ec.custom.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Daoyou;
/**
 * 
* @ClassName: IDaoYouDAO 
* @Description: �Ŷӳ��õ��ι���   
* @author Dicky
* @date 2011-10-17 ����03:40:04 
*
 */
public interface IDaoYouDAO extends IGenericDao {
     public void saveDaoYou(Daoyou dy);
     public void deleteDaoYou(Daoyou dy);
     public void updateDaoYou(Daoyou dy);
     public Daoyou getDaoYou(String usid,String dyusid);
     public PaginationSupport getDaoYouViewList(String hql,int pageSize,
 			int startIndex, String url);
     public List searchContent(String key);
     public PaginationSupport getDaoYouList(String hql, int pageSize, int startIndex, String url) ;
}
