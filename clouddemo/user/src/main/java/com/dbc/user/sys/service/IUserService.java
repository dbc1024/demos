package com.dbc.user.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dbc.user.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
public interface IUserService extends IService<User> {

    IPage<User> page(Map<String, String> params);

    List<Map<String, String>> xmlPage(Map<String, String> params);

}
