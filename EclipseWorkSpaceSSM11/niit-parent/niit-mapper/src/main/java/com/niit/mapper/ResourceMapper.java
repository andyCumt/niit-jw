package com.niit.mapper;

import java.util.List;
import java.util.Map;

import com.niit.entity.Resource;

public interface ResourceMapper extends BaseMapper<Resource>{
	/** 查询所有的资源按照代码倒叙*/
	List<Map<String, Object>> selectAllOrderByCode();
	/**生成主键的数据*/
	String getGenerator(Resource resource);
	/**根据code查询所有资源 */
	List<Resource> selectAllByCode(String code);
}