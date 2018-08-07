package com.niit.iservice;

import com.niit.core.vo.DataGridResult;

public interface BaseService<T> {
	
	/**
	 * @param  t 实体
	 * @param pageSize 页面大小
	 * @param pageNum  当前页面
	 * @return DataGridResult  分页信息
	 * 描述: 多条件分页查询
	 * */
	DataGridResult selectPageInfoByWhere(T t, Integer pageSize, Integer pageNum);
	
    /**
	 * @param  id
	 * @return T  实体类
	 * 描述:根据id获取实体对象
	 * */
	T selectByPrimaryKey(String id);
	
	 /**
		 * @param  name
		 * @return T  实体类
		 * 描述:根据id获取实体对象
		 * */
	T selectByName(String name);

	/**
	 * @param t 实体类
	 * @return int 执行条数
	 * 描述:选择行的添加实体对象
	 * */
	Integer insertSelective(T t);

	/**
	 * @param t  实体类
	 * @return int 执行条数
	 * 描述:选择行的更新实体对象
	 * */
	Integer updateByPrimaryKeySelective(T t);

	/**
	 * @param ids  实体id的数组
	 * 描述:根据实体的id批量删除实体
	 * */
	void deleteByPrimaryKey(String[] ids);

}
