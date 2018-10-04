package com.its.sims.services.impl;

import com.its.sims.mapper.BaseMapper;
import com.its.sims.mapper.RoleMapper;
import com.its.sims.model.Role;
import com.its.sims.services.IBaseService;
import com.its.sims.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by csz on 2017/9/16.
 */
@Service
public class RoleImpl extends BaseImpl<Role> implements IRoleService{

    @Autowired
    RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }
}
