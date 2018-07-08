package cn.tedu.ttms.login.service;

import cn.tedu.ttms.system.entity.User;

public interface LoginService {
	
	User isExists(String username);
}
