package com.niit.iservice;

import java.util.List;
import java.util.Map;

import com.niit.entity.Resource;

/**
 *@CLassName: ResourceService
 *@Description: 资源模块业务层
 *@Author andy
 */
public interface ResourceService extends BaseService<Resource> {
	/**异步加载资源树 */
	List<Map<String, Object>> loadResourceTree();
	/**生成主键的数据*/
	String getGenerator(Resource resource);
	
	/**批量删除资源 */
	void deleteByCode(String codes);
	/** 查询所有的资源*/
	List<Map<String, Object>> selectAllResource(String roleId);
	
	/**角色绑定资源 */
	void bingResource(Map<String,Object> map);

}
