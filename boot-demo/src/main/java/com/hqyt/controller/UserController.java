package com.hqyt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hqyt.mapper.UserMapper;
import com.hqyt.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="用户信息相关接口")
@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	
	@ApiOperation("新增用户信息")
	@PostMapping("v1/add")
	Object addUser(@RequestBody User user) {
		
		userMapper.add(user);
		
		return user;
	}
	
	@ApiOperation("删除用户信息")
	@PostMapping("v1/delete/{id}")
	Object deleteUser(@PathVariable Integer id) {
		
		userMapper.delete(id);
		
		return id;
	}
	
	@ApiOperation("修改用户信息")
	@PostMapping("v1/update")
	Object updateUser(@RequestBody User user) {
		
		userMapper.update(user);
		
		return user;
	}
	
	@ApiOperation("根据Id获取用户信息")
	@GetMapping("v1/query/{id}")
	Object queryUserById(@PathVariable Integer id) {
		
		User user = userMapper.getUserById(id);
		
		return user;
	}
	
	@ApiOperation("根据Id以map形式获取用户信息")
	@GetMapping("v1/querymap/{id}")
	Object queryUserMapById(@PathVariable Integer id) {
		
		Map<String, Object> user = userMapper.getUserMapById(id);
		
		return user;
	}
	
	@ApiOperation("查询用户信息")
	@PostMapping("v1/list")
	Object queryList(@RequestBody User user) {
		
		List<User> users = userMapper.getUserList(user);
		
		return users;
	}
	
	@ApiOperation("以map形式查询用户列表信息")
	@PostMapping("v1/listmap")
	Object queryListMap(@RequestBody User user) {
		
		List<Map<String, Object>> users = userMapper.getUserMapList(user);
		
		return users;
	}
	
	@ApiOperation("关联用户")
	@RequestMapping(value="v1/relaUser", method=RequestMethod.GET)
	String relaUser(@RequestParam @ApiParam("用户id以逗号分隔，如：12,7,99,33,12") String userIds) {
		
		return userIds;
	}
}
