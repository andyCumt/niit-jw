package com.niit.mapper;

import java.util.List;

import com.niit.entity.Resource;
import com.niit.entity.RoleResourceKey;

public interface RoleResourceMapper extends BaseMapper<RoleResourceKey> {
	
	void deleteByIds(RoleResourceKey roleResourceKey);
	
     List<Resource> selectByWhere(RoleResourceKey roleResourceKey);
     
     Resource selectByids(RoleResourceKey roleResourceKey);
     
     List<Resource> selectByLikeids(RoleResourceKey roleResourceKey);
     

}