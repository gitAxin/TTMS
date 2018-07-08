package cn.tedu.ttms.login.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;

@Controller
public class LoginController {

	/** 登录页面 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		System.out.println("toLogin");
		return "login";
	}

	@RequestMapping("confirmUser.do")
	@ResponseBody
	public JsonResult confirmUser(String username, String userpwd) {
		Subject currentUser = SecurityUtils.getSubject();
		/* 判断当前用户是否登录,没有则进行认证 */
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, userpwd);
			// rememberMe
			// token.setRememberMe(true);
			try {
				currentUser.login(token);
			} catch (IncorrectCredentialsException ice) {
				throw new IncorrectCredentialsException("密码错误！");
			} catch (AuthenticationException ae) {
				throw new AuthenticationException("登录失败");
			}
		}
		System.out.println("登录成功!");
		return new JsonResult();
	}

}
