package cn.tedu.ttms.common.reaml;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

import cn.tedu.ttms.login.service.LoginService;
import cn.tedu.ttms.system.entity.User;

public class UserRealm extends AuthenticatingRealm {

	@Resource
	private LoginService loginService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// 1.把AuthenticationToken 转换为UserPasswordToken

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2.从UsernamePasswordToken获取username
		String username = upToken.getUsername();

		// 3.调用数据库的方法,从数据库中查询username对应的用户记录
		User user = loginService.isExists(username);

		// 3).realmName:当前realm对象的name,调 用父类的getName()方法即可
		String realmName = getName();
		//4). 从数据库中查询出盐,计算出盐值
		String salt =user.getSalt();
		ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
		SimpleAuthenticationInfo info =
				new SimpleAuthenticationInfo(username, user.getPassword(), credentialsSalt, realmName);
		SecurityUtils.getSubject().getSession().setAttribute("currentUser", user);
		return info;
	}

	public static void main(String[] args) {
		String saltStr = UUID.randomUUID().toString();
		ByteSource credentialsSalt = ByteSource.Util.bytes(saltStr);
		SimpleHash simpleHash = new SimpleHash("MD5", "123456", credentialsSalt, 1024);
		System.out.println(saltStr);
		System.out.println(simpleHash.toString());
	}
}
