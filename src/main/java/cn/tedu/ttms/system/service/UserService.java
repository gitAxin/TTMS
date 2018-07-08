package cn.tedu.ttms.system.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.entity.Role;
import cn.tedu.ttms.system.entity.User;

public interface UserService {
	
	/**查找所有用户信息*/
	List<User> findAllObject();
	
	/**分页查找用户信息*/
	Map<String,Object> findPageObject(PageObject page,User user);
	
	
	/**查询所有角色信息*/
	List<Role> findRoleList();
	
	
	/**添加用户信息*/
	void insertObject(User user,String roleIdStr);

	
	
	/**查询用户是否存在*/
	int isExists(String username);
	
	
	/**根据用户id查找用户信息和角色id*/
	Map<String,Object> findUserAndRoleId(Integer id);
	
	
	/**要据用户id修改用户信息 及用户角色*/
	void updateObjectByUserId(User user,String roleIds);
	
	/**根据用户id修改禁用/启用状态*/
	void validById(Integer valid,Integer userId);
}
