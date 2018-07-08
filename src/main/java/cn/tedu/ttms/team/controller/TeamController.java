package cn.tedu.ttms.team.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {
	
	@Resource
	private TeamService teamService;
	
	@RequestMapping("/listUI")
	public String listUI(){
		
		return "team/team_list";
	}
	
	/**
	 * 获取团信息分页显示
	 * @param projectName
	 * @param valid
	 * @param pageCurrent
	 * @return
	 */
	@RequestMapping("/doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
						String projectName,
						Integer valid,
						Integer pageCurrent){
		System.out.println("pageCurrent"+pageCurrent);
		Map<String,Object> map =
				teamService.findPageObject(projectName, valid, pageCurrent);
		return new JsonResult(map);
		
	}
	
	/**保存团信息*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Team team){
		teamService.saveObject(team);
		return new JsonResult();
	}
	
	
	/**获取项目的idAndName*/
	@RequestMapping("/doFindPrjIdNames")
	@ResponseBody
	public JsonResult doFindProjectIdAndNames(){
		System.out.println("获取项目名和ID");
		List<Map<String,Object>> map =teamService.findProjectIdAndNames();
		return new JsonResult(map);
	}
	
	
	/**返回一个编辑框*/
	@RequestMapping("/doEditUI")
	public String doEditUI(){
		System.out.println("doEditUI");
		return "team/team_edit";
	}

	
	
	/**根据id查询团信息，将团信息填充到编辑框，便于修改*/
	@RequestMapping("/doFindObjectById.do")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Team team =teamService.findObjectById(id);
		return new JsonResult(team);
	}
	
	
	/**根据id修改团信息*/
	@RequestMapping("/doUpdateObjectById")
	@ResponseBody
	public JsonResult doUpdateObjectById(Team team){
		teamService.updateObject(team);
		return new JsonResult();
	}
	
	/**根据id修改团状态启用或禁用*/
	@RequestMapping("/doValid")
	@ResponseBody
	public JsonResult doValid(String checkedId,Integer valid){
		teamService.validById(checkedId, valid);
		return new JsonResult();
	}
	
}
