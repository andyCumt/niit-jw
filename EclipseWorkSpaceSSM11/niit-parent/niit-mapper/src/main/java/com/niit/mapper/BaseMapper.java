package com.niit.mapper;

import java.util.List;

public interface BaseMapper<T> {

    /**
     * @param t 实体
     * @return list  分页信息
     * 描述:多条件分页查询
     * */
    List<T> selectPageInfoByWhere(T t);
 
    /**
     * @param  id
     * @return  t 实体类
     * 描述:根据id获取实体对象
     * */
    T selectByPrimaryKey(String id);

    int insert(T t);

    /**
     * @param t 实体类
     * @return int 执行条数
     * 描述:选择的添加实体对象
     * */
    int insertSelective(T t);
    /**
     * @param   t 实体类
     * @return int 执行条数
     * 描述:选择行的更新实体对象
     * */
    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    /**
     * @param id  实体id
     * @return int 执行条数
     * 描述:int deleteByPrimaryKey(String id);
     * */
    int deleteByPrimaryKey(String id);
    
    /**
     * @param name 实体name
     * @return T 实体
     * 描述:根据名字查询实体
     * */
    T selectByName(String name);

}
