package com.ectrip.ec.custom.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.user.CustomRealName;


public interface ICustomRealNameDAO extends IGenericDao {

    public CustomRealName get(String id);

    public void oneTimePatchUserBank();
    
    public List getRealByUserIds(String userIds);
}
