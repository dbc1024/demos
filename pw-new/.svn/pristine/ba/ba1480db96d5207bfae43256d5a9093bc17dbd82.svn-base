package com.ectrip.ec.custom.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Daoyou;
import com.ectrip.sys.model.syspar.Customlog;
/**
 * 
* @ClassName: IDaoYouService 
* @Description:  �ҵ��˻�  �Ŷӳ��õ��ι���
* @author Dicky
* @date 2011-10-17 ����03:49:16 
*
 */
public interface IDaoYouService extends IGenericService {
    public void saveDaoYou(Daoyou dy,Customlog log);
    public void deleteDaoYou(Daoyou dy,Customlog log);
    public void updateDaoYou(Daoyou dy,Customlog log);
    public Daoyou getDaoYou(String usid,String dyusid);
    public PaginationSupport getDaoYouViewList(String hql,int pageSize,
    		int startIndex, String url);
    public List searchContent(String key);
    public PaginationSupport getDaoYouList(String hql, int pageSize, int startIndex, String url);
}
