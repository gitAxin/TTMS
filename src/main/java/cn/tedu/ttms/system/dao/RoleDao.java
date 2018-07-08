package cn.tedu.ttms.system.dao;

import java.util.List;

import cn.tedu.ttms.system.entity.Role;

/**角色Dao接口*/
public interface RoleDao {

	
	/**查询所有角色信息*/
	List<Role> findRoleList();
	
}
