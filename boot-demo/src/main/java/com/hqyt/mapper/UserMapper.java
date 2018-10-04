package com.hqyt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hqyt.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	public User getUserById(Integer id);
	public Map<String, Object> getUserMapById(Integer id);
	 
	public List<User> getUserList(User user);
	public List<Map<String, Object>> getUserMapList(User user);
 
	public int add(User user);
 
	public int update(@Param("user") User user);
 
	public int delete(Integer id);
	
	
}
