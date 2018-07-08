package cn.tedu.ttms.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.entity.User;

public interface UserDao extends BaseDao<User>{
	
	/**判断是否存在*/
	int isExists(String username);
	
	/**根据用户名相询用户信息*/
	User findObjectByName(String username);

	/**查找所有用户信息*/
	List<User> findAllObject();
	
	
	/**查找用户分页信息*/
	List<User> findPageObject(@Param("page")PageObject page,
											@Param("user")User user);
	
	/**查询用户总记录*/
	int getRowCount(@Param("user")User user);
	
	/**根据用户id查询用户信息与用户角色id*/
	Map<String,Object> findObjectById(@Param("id")Integer id);
	
	/**根据用户id修改禁用/启用状态*/
	int validById(@Param("valid")Integer valid,@Param("userId")Integer userId);
	
	
	
}
