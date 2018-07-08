package project.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.team.controller.TeamController;
import cn.tedu.ttms.team.dao.TeamDao;
import cn.tedu.ttms.team.entity.Team;
import cn.tedu.ttms.team.service.TeamService;
import common.dao.TestBaseDao;


public class TestTeamDao extends TestBaseDao{
	
	@Test
	public void testInsertObject(){
		TeamDao dao = (ctx.getBean("teamDao",TeamDao.class));
		Team team = new Team();
		team.setId(1);
		team.setName("美国马拉松2日团");
		team.setProjectId(41);
		team.setValid(1);
		team.setCreatedUser("admin");
		team.setModifiedUser("admin");
		team.setCreatedTime(new Date());
		team.setCreatedTime(new Date());
		int i =dao.insertObject(team);
		Assert.assertEquals(1, i);
	}
	
	@Test
	public void testFindPageObjects(){
		TeamDao dao = (TeamDao)ctx.getBean("teamDao");
		String projectName="美国";
		Integer valid=1;
		int startIndex=0;
		int pageSize=2;
		List<Map<String,Object>> list = dao.findPageObject(projectName,valid,startIndex,pageSize);
		System.out.println(list);
		Assert.assertNotEquals(null, list);
		System.out.println("list.size="+list.size());
		
		int rowCount = dao.getRowCount(projectName, valid);
		PageObject pageObject = new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		System.out.println(rowCount);
		Assert.assertEquals(1, pageObject.getRowCount());
		
	}
	
	@Test
	public void testServiceTeamFindPageObject(){
		TeamService ts =ctx.getBean("teamService",TeamService.class);
		Map<String,Object> map=ts.findPageObject("美国", 1, 1);
		System.out.println(map);
	}
	
	@Test
	public void testControllerTeamFindPageObject(){
		TeamController tc =ctx.getBean("teamController",TeamController.class);
		JsonResult jr=tc.doFindPageObjects("美国", 1, 1);
		System.out.println(jr.getData());
	}
	
	@Test
	public void testGetObjectById(){
		TeamDao teamDao = ctx.getBean("teamDao",TeamDao.class);
		Team team =teamDao.getObjectById(1);
		System.out.println(team);
	}
	
	@Test
	public void testUpdateObjectById(){
		TeamDao teamDao = ctx.getBean("teamDao",TeamDao.class);
		Team team =teamDao.getObjectById(14);
		team.setNote("美国纽约马拉松5日团飞机票");
		int rows =teamDao.updateObject(team);
		System.out.println(rows);
	}
	
	/**测试根据ID修改状态 */
	@Test
	public void testValidById(){
		TeamDao teamDao = ctx.getBean("teamDao",TeamDao.class);
		Integer n =teamDao.validById(new String[]{"12"}, 0);
		System.out.println(n);
		Assert.assertNotEquals(n, null);
	}
	
	
	
}
