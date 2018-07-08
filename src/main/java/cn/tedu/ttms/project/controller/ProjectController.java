package cn.tedu.ttms.project.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;

/**
 * 产品项目管理控制器对象
 * @author 天大java
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
	
		@Resource
		private ProjectService projectService;
	
	
		/**
		 * 此方法用于返回项目管理的列表页面
		 * http://localhost:8080/ttms2.0/project/listUI.do
		 * @return
		 */
		@RequestMapping("/listUI")
		public String listUI(){
			System.out.println("listUI()");
			return "project/project_list";
			//通过查看spring-mvc.xml
			//return 语句返回的字符串对应一个jsp文件(在哪,名字是什么)
		}
		
		
		/**返回一个编辑页面*/
		@RequestMapping("/editUI")
		public String editUI(){
			return "project/project_edit";
		}
		
		
		/**
		 * 返回分页信息
		 * @param pageObject
		 * @return
		 */
		@RequestMapping("/findPageObjects")
		@ResponseBody//此注解的作用是将java对象转换成一个json字符串
		//页面上会将这个json字符串转换成json对象
		public JsonResult findPageObjects(Project project,PageObject pageObject){
			Map<String,Object> map =projectService.findPageObjects(project,pageObject);
			return new JsonResult(map);
		}
		
		/**
		 * 启用禁用
		 */
		@RequestMapping("/doValidById")
		@ResponseBody
		public JsonResult doVaildById(String checkedIds,Integer valid){
			projectService.ValidById(checkedIds, valid);
			return new JsonResult();
		}
		
		
		
		/**
		 * 保存项目信息
		 * @param project
		 * @return
		 */
		@RequestMapping("/doSaveProject")
		@ResponseBody
		public JsonResult doSaveProject(Project project){
			System.out.println(project.toString());
			projectService.saveObject(project);
			return new JsonResult();
		}
		
		/**
		 * 根据ID查找项目信息
		 * @param id
		 * @return
		 */
		@RequestMapping("doFindById")
		@ResponseBody
		public JsonResult doFindProjectById(Integer id){
			System.out.println("doFindbyId");
			System.out.println(id);
			Project project = projectService.findObjectById(id);
			System.out.println(project);
			return new JsonResult(project);
		}
		
		/**
		 * 修改项目信息
		 * @param project
		 * @return
		 */
		@RequestMapping("doUpdateProject")
		@ResponseBody
		public JsonResult doUpdateProject(Project project){
			projectService.updateObject(project);
			return new JsonResult();
		}
		

}
