package com.niit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.iservice.TestService;
import com.niit.mapper.TestMapper;

/**
 *@CLassName: TestServiceImpl
 *@Description: TODO
 *@Author andy
 */
@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestMapper testMapper;

	@Override
	public String queryCurrentDate() {
		return testMapper.queryCurrentDate();
	}
	
    
	
}