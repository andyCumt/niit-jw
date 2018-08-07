package com.niit.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niit.core.vo.DataGridResult;
import com.niit.iservice.BaseService;
import com.niit.mapper.BaseMapper;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public  class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected BaseMapper<T> baseMapper;

	@Override
	public Integer updateByPrimaryKeySelective(T t) {
			
		try{
			return baseMapper.updateByPrimaryKeySelective(t);
		}catch(Exception ex){
			throw new RuntimeException("根据主键选择性更新方法出现了异常！", ex);
		}
		
	}

	@Override
	public void deleteByPrimaryKey(String[] ids) {
		try{
			for (int i = 0; i < ids.length; i++) {
				baseMapper.deleteByPrimaryKey(ids[i]);
			}
		}catch(Exception ex){
			throw new RuntimeException("根据主键删除方法出现了异常！", ex);
		}
		
	}

	@Override
	public DataGridResult selectPageInfoByWhere(T t, Integer pageSize, Integer pageNum) {
		
		try{
			 //分页信息判空
			if (pageNum!=null && pageSize != null && pageNum != 0 && pageSize != 0 ){
				PageHelper.startPage(pageNum,pageSize);
			}
			//执行查询
			List<T> list = baseMapper.selectPageInfoByWhere(t);

			//转换成分页信息对象
			PageInfo<T> pageInfo = new PageInfo<>(list);

			DataGridResult data = new DataGridResult(pageInfo.getTotal(),pageInfo.getList(),pageInfo.getPageSize(),pageInfo.getPageNum());

			return data;
		}catch(Exception ex){
			throw new RuntimeException("分页查询方法出现了异常！", ex);
		}
       
	}

	@Override
	public T selectByPrimaryKey(String id) {
		
		try{
			return baseMapper.selectByPrimaryKey(id);
		}catch(Exception ex){
			throw new RuntimeException("根据主键查询方法出现了异常！", ex);
		}
		
		
	}

	@Override
	public Integer insertSelective(T t) {
		try{
			return baseMapper.insertSelective(t);
		}catch(Exception ex){
			throw new RuntimeException("选择性添加方法出现了异常！", ex);
		}
		
	}

	@Override
	public T selectByName(String name) {
		
		return baseMapper.selectByName(name);
	}



}
