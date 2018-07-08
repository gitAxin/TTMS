package user.service;

import org.junit.Test;

import cn.tedu.ttms.system.entity.User;
import cn.tedu.ttms.system.service.UserService;
import common.dao.TestBaseDao;

public class TestUserService extends TestBaseDao{
	
	@Test
	public void testInsertObject(){
		UserService userService = ctx.getBean("userServiceImpl",UserService.class);
		User user = new User();
		user.setUsername("zhangsan");
		user.setPassword("123456");
		userService.insertObject(user, "2,3");
	}

}
