package com.dbc.user.sys.service.impl;

import com.dbc.user.sys.entity.User;
import com.dbc.user.sys.mapper.UserMapper;
import com.dbc.user.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
