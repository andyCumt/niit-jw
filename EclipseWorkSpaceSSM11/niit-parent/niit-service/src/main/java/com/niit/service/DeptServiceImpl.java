package com.niit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.Dept;
import com.niit.iservice.DeptService;
import com.niit.mapper.DeptMapper;

/**
 *@CLassName: JobServiceImpl
 *@Description: 职位模块实现层
 *@Author andy
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept> implements DeptService {
	
	/*注入mapper接口*/
	@Autowired
	private DeptMapper deptMapper;
	
	
}
