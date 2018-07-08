package user.dao;

import java.util.List;

import org.junit.Test;

import cn.tedu.ttms.system.dao.RoleDao;
import cn.tedu.ttms.system.entity.Role;
import common.dao.TestBaseDao;

public class testRoleDao extends TestBaseDao{
	
	
	/**测试查询所有Role信息*/
	@Test
	public void testFindAllObject(){
		RoleDao roleDao = ctx.getBean("roleDao",RoleDao.class);
		List<Role> roleList=roleDao.findRoleList();
		System.out.println(roleList);
	}
}
