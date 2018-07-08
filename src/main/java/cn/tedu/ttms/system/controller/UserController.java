package cn.tedu.ttms.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.entity.Role;
import cn.tedu.ttms.system.entity.User;
import cn.tedu.ttms.system.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

	/**返回一个用户信息管理页面*/
	@RequestMapping("/listUI")
	public String userUI(){
		return "system/user_list";
	}
	
	
	/**返回一个用户编辑页面*/
	@RequestMapping("/editUI")
	public String editUI(){
		return "system/user_edit";
	}
	
	
	/**查找所有用户信息*/
	@RequestMapping("/doGetObjects")
	@ResponseBody
	public JsonResult doGetObjects(){
		List<User> userList =userService.findAllObject();
		return new JsonResult(userList);
	}
	
	
	/**分页查询用户信息 
	 * 根据用户输入的条件查询信息*/
	@RequestMapping("/doGetPageObjects")
	@ResponseBody
	public JsonResult doGetPageObject(PageObject page,User user){
		Map<String,Object> map = userService.findPageObject(page, user);
		return new JsonResult(map);
	}
	
	
	
	
	/**查询所有角色信息*/
	@RequestMapping("/doFindAllRole")
	@ResponseBody
	public JsonResult doFindAllRole(){
		List<Role> roleList =userService.findRoleList();
		return new JsonResult(roleList);
	}
	
	
	/**插入用户信息和角色关系*/
	@RequestMapping("saveUserAndRole")
	@ResponseBody
	public JsonResult saveUserAndRole(User user,String roleIdStr){
		userService.insertObject(user, roleIdStr);
		return new JsonResult();
	}
	
	/**根据用户名查询用户是否存在*/
	@RequestMapping("/isExists")
	@ResponseBody
	public JsonResult isExists(String username){
		int n =userService.isExists(username);
		return new JsonResult(n);
		
	}
	
	
	/**根据用户id查询用户信息及角色id*/
	@RequestMapping("/doGetObjectById")
	@ResponseBody
	public JsonResult doGetObjectById(Integer id){
		Map<String,Object> map = userService.findUserAndRoleId(id);
		return new JsonResult(map);
	}
	
	
	/**修改用户信息及用户角色信息*/
	@RequestMapping("/updateUserAndRole")
	@ResponseBody
	public JsonResult updateUserAndRole(User user,String roleIdStr){
		userService.updateObjectByUserId(user, roleIdStr);
		return new JsonResult();
	}
	
	/**根据用户id修改禁用/启用状态*/
	@RequestMapping("/updateValidById")
	@ResponseBody
	public JsonResult updateValidById(Integer valid,Integer userId){
		userService.validById(valid, userId);
		return new JsonResult();
	}
	
}
