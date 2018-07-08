package cn.tedu.ttms.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.dao.RoleDao;
import cn.tedu.ttms.system.dao.UserDao;
import cn.tedu.ttms.system.dao.UserRoleDao;
import cn.tedu.ttms.system.entity.Role;
import cn.tedu.ttms.system.entity.User;
import cn.tedu.ttms.system.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	/**角色Dao接口*/
	@Resource
	private UserDao userDao;
	
	/**角色Dao接口*/
	@Resource
	private RoleDao roleDao;
	
	/**用户与角色关系Dao接口*/
	@Resource
	private UserRoleDao userRoleDao;
	
	
	/**查找所有用户信息*/
	@Override
	public List<User> findAllObject() {
		List<User> userList =userDao.findAllObject();
		if(userList==null){
				throw new RuntimeException("服务器忙");
		}
		return userList;
	}

	/**分页查找用户信息*/
	@Override
	public Map<String,Object> findPageObject(PageObject page,User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		/**根据页面输入的条件查询表中数据记录数*/
		int rows =userDao.getRowCount(user);
		page.setRowCount(rows);
		
		/**根据分页信息,和页面输入的查询条件查询用户信息*/
		List<User> userList =userDao.findPageObject(page, user);
		map.put("userList", userList);
		map.put("page",page);
		return map;
	}

	/**查询所有角色信息*/
	@Override
	public List<Role> findRoleList() {
		List<Role> roleList =roleDao.findRoleList();
		return roleList;
	}

	/**添加用户信息*/
	@Override
	public void insertObject(User user,String roleIdStr) {
		if(user==null){
			throw new NullPointerException("提交数据不能为空");
		}
		int n =userDao.isExists(user.getUsername());
		if(n==1){
			throw new SaveRuntimeException("用户名已存在!");
		}
		
		String saltStr = UUID.randomUUID().toString();
		//使用字符串生成盐值
		String salt = ByteSource.Util.bytes(saltStr).toString();
		
		String pwd = new SimpleHash("MD5",user.getPassword(),salt,1024).toString();
		user.setSalt(saltStr);//设置盐
		user.setPassword(pwd);
		int row = userDao.insertObject(user); 
		if(row!=1){
			throw new SaveRuntimeException("保存用户信息失败");
		}
		
		//保存用户角色关系 
		String[] roleIdList = roleIdStr.split(",");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());//user.getId(),可以得上插入用户时返回的id
		map.put("roleIdList", roleIdList);
		int rows = userRoleDao.insertObject(map);
		if(rows!=roleIdList.length){
			throw new SaveRuntimeException("保存用户角色失败");
		}
	}
	/**根据用户名查询用户是否存在*/
	@Override
	public int isExists(String username) {
		if(username==null || username==""){
			throw new NullPointerException("请输入用户名!");
		}
		int n =userDao.isExists(username);
		System.out.println("userService:--"+n);
		return n;
	}

	/**根据用户ID 查找用户信息及角色id*/
	@Override
	public Map<String, Object> findUserAndRoleId(Integer id) {
		if(id==null){
			throw new NullPointerException("请先选中用户!");
		}
		Map<String,Object> map =userDao.findObjectById(id);
		if(map==null|| map.size()==0){
			throw new RuntimeException("获取用户信息失败!");
		}
		
		List<Integer> roleIdList=userRoleDao.findRoleIdByUserId(id);
		if(roleIdList==null || roleIdList.size()==0){
			throw new RuntimeException("获取用户角色信息失败!");
		}
		map.put("roleIdList", roleIdList);
		return map;
	}
	
	/**要据用户id修改用户信息 及用户角色*/
	@Override
	public void updateObjectByUserId(User user, String roleIds) {
		if(user==null){
			throw new NullPointerException("用户对象不能为空!");
		}
		String saltStr = UUID.randomUUID().toString();
		ByteSource salt = ByteSource.Util.bytes(saltStr);
		SimpleHash simpleHash = new SimpleHash("MD5", user.getPassword(), salt, 1024);
		String password = simpleHash.toString();
		user.setSalt(saltStr);
		user.setPassword(password);
		int i = userDao.updateObject(user);
		if(i!=1){
			throw new UpdateRuntimeException("更新用户信息失败!");
		}
		
		//删除用户角色关系 再插入用户角色
		String[] roleIdList =roleIds.split(",");
		int n =userRoleDao.deleteObject(user.getId());
		if(n!=roleIdList.length){
			throw new UpdateRuntimeException("更新角色信息失败!");
		}
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", user.getId());
		map.put("roleIdList", roleIdList);
		int rows = userRoleDao.insertObject(map);
		if(rows!=roleIdList.length){
			throw new SaveRuntimeException("更新角色信息失败!");
		}
	}
	/**根据用户id修改禁用/启用状态*/
	@Override
	public void validById(Integer valid, Integer userId) {
		String message;
		if(valid==0){
			message="禁用";
		}else{
			message="启用";
		}
		int i = userDao.validById(valid, userId);
		if(i!=1){
			throw new UpdateRuntimeException(message+"失败!");
		}
		
	}
	
	
	
	
	
	
}
