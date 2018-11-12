package com.ectrip.ec.custom.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.user.CustomRealName;

public interface ICustomRealNameService  extends IGenericService {
    public CustomRealName get(String id);

    public void oneTimePatchUserBank();
    
    public List getRealByUserIds(String userIds);
}

