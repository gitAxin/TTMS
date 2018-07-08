package user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.ttms.system.dao.UserRoleDao;
import common.dao.TestBaseDao;

public class testUserRoleDao extends TestBaseDao{

	
	@Test
	public void testInsertObject(){
		UserRoleDao userRoleDao = ctx.getBean("userRoleDao",UserRoleDao.class);
		Map<String,Object> map = new HashMap<String,Object>();
		String[] roleIdList = new String[]{"2","3"}; 
		map.put("userId", 5);
		map.put("roleIdList", roleIdList);
		int n =userRoleDao.insertObject(map);
		System.out.println(n);
	}
	
	@Test
	public void testFindRoleIdByUserId(){
		UserRoleDao userRoleDao = ctx.getBean("userRoleDao",UserRoleDao.class);
		List<Integer> roleIds =userRoleDao.findRoleIdByUserId(5);
		System.out.println(roleIds);
	}
	@Test
	public void testDeleteObject(){
		UserRoleDao userRoleDao = ctx.getBean("userRoleDao",UserRoleDao.class);
		int i =userRoleDao.deleteObject(5);
		System.out.println(i);
	}
	
	
}
