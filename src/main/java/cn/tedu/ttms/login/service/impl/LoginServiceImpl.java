package cn.tedu.ttms.login.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.login.service.LoginService;
import cn.tedu.ttms.system.dao.UserDao;
import cn.tedu.ttms.system.entity.User;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private UserDao userDao;
	

	@Override
	public User isExists(String username) {
		
		if(username==null || username==""){
			throw new IllegalArgumentException("用户名不能为空!");
		}
		
		int n =userDao.isExists(username);
		if(n!=1){
			throw new UnknownAccountException("该用户不存在！");
		}
		
		User user = userDao.findObjectByName(username);
		
		return user;
	}

}
