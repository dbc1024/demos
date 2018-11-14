package com.dbc.user.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dbc.user.controller.BaseController;
import com.dbc.user.sys.entity.User;
import com.dbc.user.sys.service.IUserService;
import com.dbc.user.util.DictionaryUtil;
import com.dbc.user.util.PageUtil;
import com.dbc.user.util.Result;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dbc
 * @since 2018-11-12
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;



    @GetMapping("/detail/{id}")
    public Result detail(@PathVariable Integer id){

        User user = userService.getById(id);
        DictionaryUtil.keyValueHandle(user);

        return Result.success(user);
    }


    /**
     * MybatisPlus原生分页方法
     * @param params
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody Map<String, String> params){

        IPage<User> userList = userService.page(prePagePlus(params), condition(params));
        DictionaryUtil.keyValueHandle(userList);
        PageUtil pageResult = PageUtil.result(userList);

        return Result.success(pageResult);
    }


    /**
     * xml中写sql的方式
     * @param params
     * @return
     */
    @PostMapping("/xmlPage")
    public Result xmlPage(@RequestBody Map<String, String> params){

        Page<Object> page = prePage(params);
        userService.xmlPage(params);
        PageUtil pageResult = PageUtil.result(page);

        return Result.success(pageResult);
    }


    /**
     * 原生map封装结果集中不会有非数据库字段
     * @param id
     * @return
     */
    @GetMapping("/detailMap/{id}")
    public Result detailMap(@PathVariable Integer id){

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, id);

        Map<String, Object> user = userService.getMap(wrapper);

        return Result.success(user);
    }





    /**
     * 在service中定义新方法调用MybatisPlus原生分页方法
     * @param params
     * @return
     */
    @PostMapping("/page1")
    public Result page1(@RequestBody Map<String, String> params){

        IPage<User> userList = userService.page(params);

        return Result.success(userList);
    }

    /**
     * MybatisPlus原生map结果集分页方法
     * @param params
     * @return
     */
    @PostMapping("/pageMaps")
    public Result pageMaps(@RequestBody Map<String, String> params){

        IPage<Map<String, Object>> userList = userService.pageMaps(prePagePlus(params), condition(params));
        PageUtil pageResult = PageUtil.result(userList);


        return Result.success(pageResult);
    }



    /**
     * 参数预处理方法
     */
    public LambdaQueryWrapper condition(Map<String, String> params){

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        String name = params.get("name");
        String address = params.get("address");
        if(StringUtils.isNotBlank(name)){
            wrapper.like(User::getName, params.get("name"));
        }
        if(StringUtils.isNotBlank(address)){
            wrapper.like(User::getAddress, params.get("address"));
        }

        return wrapper;
    }
}

