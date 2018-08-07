package com.niit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.core.common.Constants;
import com.niit.entity.Resource;
import com.niit.entity.RoleResourceKey;
import com.niit.iservice.ResourceService;
import com.niit.mapper.ResourceMapper;
import com.niit.mapper.RoleResourceMapper;

/**
 *@CLassName: ResourceServiceImpl
 *@Description: 资源模块实现层
 *@Author andy
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService {
	
	private static final String String = null;
	/*注入mapper接口*/
	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public List<Map<String, Object>> loadResourceTree() {
		
		try{
			// [{id : 1, pId: '', name: ''},{}]
			List<Map<String, Object>> data = resourceMapper.selectAllOrderByCode();
			//循环map集合
			for (Map<String, Object> map : data) {
				//获取自己的code
				String code = (String) map.get("id");
				//计算父pid
				String pid = (code.length() == Constants.CODE_LEN ? "0" : code.substring(0, code.length()-Constants.CODE_LEN)); 
				map.put("pid", pid);		
			}
		    return data;
		}catch(Exception ex){
			throw new RuntimeException("加载资源树方法出现了异常！", ex);
		}
	}

	@Override
	public String getGenerator(Resource resource) {
		
		return resourceMapper.getGenerator(resource);
	}

	@Override
	public void deleteByCode(String codes) {
		try{
			 String[] cods= codes.split(","); 
			 for (int i = 0; i < cods.length; i++) {
				List<Resource> selectAllByCodes = resourceMapper.selectAllByCode(cods[i]);
				for (int j = 0; j < selectAllByCodes.size(); j++) {
					String code = selectAllByCodes.get(j).getCode();
					resourceMapper.deleteByPrimaryKey(code);
					RoleResourceKey roleResourceKey = new RoleResourceKey();
					roleResourceKey.setResourceCode(code);
					roleResourceMapper.deleteByIds(roleResourceKey);
					
				}
			}
	
		}catch(Exception ex){
			throw new RuntimeException("根据主键删除角色及角色资源中间表及用户角色中间表方法出现了异常！", ex);
		}
		
	}

	@Override
	public List<Map<String, Object>> selectAllResource(String roleId) {
		 try{
			 //查询该角色绑定的所有的资源
			 RoleResourceKey roleResourceKey = new RoleResourceKey();
			 roleResourceKey.setRoleId(roleId);
			 List<Resource> resources = roleResourceMapper.selectByWhere(roleResourceKey);
			 
			 //定义list集合,很尊贵该角色已经绑定的资源
			 List<String> codes = new ArrayList<String>();
			 for (int i = 0; i < resources.size(); i++) {
					codes.add(resources.get(i).getCode());
			 }
			 //查询所有的资源 
			 List<Map<String, Object>> data = resourceMapper.selectAllOrderByCode();
			//{"code" : 2, "name" : "苹果手机", "type" : "苹果手机",
			   //  "_parentId" : 1, "state" : "closed", "checked": true}
			 for (Map<String, Object> map : data) {
				//获取code
				 String code = (String) map.get("id");
				 if (code.length()>Constants.CODE_LEN) {
					map.put("_parentId", code.substring(0,code.length()-Constants.CODE_LEN));
					if (code.length()==Constants.CODE_LEN* 2) {
						map.put("state", "closed");
					}
					map.put("checked", codes.contains(code));
				}
			}
			 return data;
			}catch(Exception ex){
				throw new RuntimeException("查询所有的资源出现了异常！", ex);
			}
	}

	@Override
	public void bingResource(Map<String, Object> map) {
		try{
			/**获取前台参数*/
			String roleId = (String)map.get("id");
			String codeStr = (String)map.get("codes");
			String[] codes = codeStr.split(",");
			
			RoleResourceKey roleResourceKey = new RoleResourceKey();
			roleResourceKey.setRoleId(roleId);
			/**删除该角色已经绑定的资源 */
			roleResourceMapper.deleteByIds(roleResourceKey);
			
			/**设计标记资源code8位字码 */
			Set<String> set = new HashSet<>();
		    /** 添加选定的角色资源*/
			for (int i = 0; i < codes.length; i++) {
				List<Resource> selectByLikeids = resourceMapper.selectAllByCode(codes[i]);
				/**模糊匹配所有符合条件的资源 */
				for (Resource resource : selectByLikeids) {
					set.add(resource.getCode());
				}
			}
			
			for (String string : set) {
				roleResourceKey.setResourceCode(string);
				roleResourceMapper.insertSelective(roleResourceKey);
			}
		 }catch(Exception ex){
				throw new RuntimeException("绑定资源的方法出现了异常！", ex);
		}
		
		
	}
	
	
}
