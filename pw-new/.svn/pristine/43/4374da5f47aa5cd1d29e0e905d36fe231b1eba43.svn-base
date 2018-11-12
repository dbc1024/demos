package com.ectrip.ec.custom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.custom.dao.idao.ICustomRealNameDAO;
import com.ectrip.ec.custom.service.iservice.ICustomRealNameService;
import com.ectrip.ec.model.user.CustomRealName;

@Service
public class CustomRealNameService extends GenericService implements ICustomRealNameService{
	
	@Autowired
    private ICustomRealNameDAO customRealNameDAO;

    public void setCustomRealNameDAO(ICustomRealNameDAO customRealNameDAO) {
        this.customRealNameDAO = customRealNameDAO;
        super.setGenericDao(customRealNameDAO);
    }

    public CustomRealName get(String id) {
        return customRealNameDAO.get(id);
    }

    public void oneTimePatchUserBank() {
        customRealNameDAO.oneTimePatchUserBank();
    }

	@Override
	public List getRealByUserIds(String userIds) {
		// TODO Auto-generated method stub
		return customRealNameDAO.getRealByUserIds(userIds);
	}
}
