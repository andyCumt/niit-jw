package com.niit.mapper;

import java.util.List;

import com.niit.entity.Role;
import com.niit.entity.UserRoleKey;

public interface UserRoleMapper extends BaseMapper<UserRoleKey>{
	void deleteByIds(UserRoleKey userRoleKey);
	
	List<Role> selectAllRoleByUserId(UserRoleKey userRoleKey);
}