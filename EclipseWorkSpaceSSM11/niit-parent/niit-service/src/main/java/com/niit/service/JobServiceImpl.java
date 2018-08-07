package com.niit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.entity.Job;
import com.niit.iservice.JobService;
import com.niit.mapper.JobMapper;

/**
 *@CLassName: JobServiceImpl
 *@Description: 职位模块实现层
 *@Author andy
 */
@Service("jobService")
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {
	
	/*注入mapper接口*/
	@Autowired
	private JobMapper jobMapper;
	
	
}
