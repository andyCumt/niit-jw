package com.niit.mapper;

import org.springframework.stereotype.Repository;

/**
 *@CLassName: TestMapper
 *@Description:  
 *@Author andy
 */
@Repository("testMapper")
public interface TestMapper {
	//查询当前日期
	public String queryCurrentDate();
}
