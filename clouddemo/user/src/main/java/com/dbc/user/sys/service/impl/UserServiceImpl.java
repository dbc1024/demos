package com.dbc.user.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dbc.user.sys.entity.User;
import com.dbc.user.sys.mapper.UserMapper;
import com.dbc.user.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dbc.user.util.DictionaryUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> page(Map<String, String> params) {

        IPage<User> page = new Page<User>(1, 10);

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        String name = params.get("name");
        String address = params.get("address");
        if(StringUtils.isNotBlank(name)){
            wrapper.like(User::getName, params.get("name"));
        }
        if(StringUtils.isNotBlank(address)){
            wrapper.like(User::getAddress, params.get("address"));
        }

        IPage<User> userPage = super.page(page, wrapper);

        return userPage;
    }


    @Override
    public List<Map<String, String>> xmlPage(Map<String, String> params) {

        List<Map<String, String>> userList = userMapper.xmlPage(params);
        DictionaryUtil.keyValueHandle(userList);

        return userList;
    }
}
