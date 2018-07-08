package cn.tedu.ttms.system.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.dao.BaseDao;

/**用户与角色关系Dao接口*/
public interface UserRoleDao extends BaseDao{
	
	int insertObject(Map<String, Object> map);
	
	List<Integer> findRoleIdByUserId(Integer userId);
	
	
	int deleteObject(Integer userId);
	
	
	
}



