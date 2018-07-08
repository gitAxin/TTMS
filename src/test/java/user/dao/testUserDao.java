package user.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.dao.UserDao;
import cn.tedu.ttms.system.entity.User;
import common.dao.TestBaseDao;

public class testUserDao extends TestBaseDao{
	
	
	
	@Test
	public void testIsExists(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		int n =userDao.isExists("admin");
		System.out.println(n);
	}
	
	@Test
	public void testFindObjectByName(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		String username="admin";
		User user =userDao.findObjectByName(username);
		System.out.println(user);
	}
	

	@Test
	public void testFindAllObject(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		List<User> userList =userDao.findAllObject();
		System.out.println(userList);
	}
	
	@Test
	public void testFindPageObject(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		User user = new User();
		int i =userDao.getRowCount(user);
		System.out.println(i);
		PageObject page = new PageObject();
		page.setRowCount(i);
		List<User> list =
				userDao.findPageObject(page, user);
		System.out.println(list);
	}
	
	/**测试插入用户信息*/
	@Test
	public void testInsertObject(){
		User user = new User();
		System.out.println(user.getId());
		user.setUsername("lisi");
		user.setPassword("li");
		user.setSalt("salt");
		user.setEmail("lisi@qq.com");
		user.setCreatedUser("admin");
		user.setMobile("456789123");
		user.setValid(1);
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		int n =userDao.insertObject(user);
		System.out.println(user.getId());
		System.out.println(n);
	}
	
	
	@Test
	public void testFindObjectById(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		Map<String,Object>map =userDao.findObjectById(5);
		System.out.println(map);
	}
	
	@Test
	public void testUpdateObject(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		User user = new User();
		user.setId(6);
		user.setUsername("456");
		user.setPassword("456789");
		user.setSalt("dskjflas");
		user.setCreatedUser("admin");
		user.setEmail("456@163.com");
		user.setMobile("12345678900");
		int i =userDao.updateObject(user);
		System.out.println(i);
	}
	
	@Test
	public void testValidById(){
		UserDao userDao = ctx.getBean("userDao",UserDao.class);
		userDao.validById(1, 5);

		
		
	}
}
