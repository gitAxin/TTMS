package project.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.project.dao.ProjectDao;
import cn.tedu.ttms.project.entity.Project;
import cn.tedu.ttms.project.service.ProjectService;
import common.dao.TestBaseDao;

public class TestProjectDao extends TestBaseDao{
	
	
	
	@Test
	public void testInsertObject() throws ParseException{
		ProjectDao projectDao = (ProjectDao)ctx.getBean("projectDao");
		Project project = new Project();
		project.setId(10);
		project.setCode("TT-20170711-CHN-BJ-007");
		project.setName("西双版纳");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		project.setBeginDate(sdf.parse("2017-08-19"));
		project.setEndDate(sdf.parse("2017-08-19"));
		project.setValid(1);
		project.setNote("中国白金游......");
		project.setCreatedUser("admin");
		project.setModifiedUser("admin");
		int n = projectDao.insertObject(project);
		Assert.assertEquals(1, n);
		
	}
	
	@Test
	public void testFindObjects(){
		ProjectDao projectDao =ctx.getBean("projectDao",ProjectDao.class);
		List<Project> projects =projectDao.findObjects();
		System.out.println(projects);
		Assert.assertNotEquals(null, projects);
		
	}
	
	/**
	 * 测试分页查询
	 */
	@Test
	public void testFindPageObjects(){
		ProjectDao dao = ctx.getBean("projectDao",ProjectDao.class);
		PageObject pageObject=new PageObject();
		pageObject.setPageSize(3);
		int pageCount = pageObject.getPageCount();
		System.out.println(pageCount);
		Project project = new Project();
		List<Project> list =dao.findPageObjects(project,pageObject);
		
//		获得总记录数
		int rowCount = dao.getRowCount(project);
//		获得总页数(根据总记录数,pageSize计算总页数)
		pageObject.setRowCount(rowCount);
		System.out.println(list);
		Assert.assertEquals(2, list.size());
	}
	
	@Test
	public void testService(){
		ProjectService ps = ctx.getBean("projectServiceImpl",ProjectService.class);
		PageObject pageObject = new PageObject();
		pageObject.setPageSize(4);
		Project project = new Project();
		Map<String,Object> map =ps.findPageObjects(project,pageObject);
		System.out.println(map);
	}
	
	
	
	
	/**
	 * 测试修改项目信息
	 */
	@Test
	public void testUpdateObject(){
		ProjectDao dao =ctx.getBean("projectDao",ProjectDao.class);
		//根据id找到Project对象
		Project project =dao.findObjectById(14);
		//修改Project对象内容
		project.setName("zhongguoren");
		int i =dao.updateObject(project);
		System.out.println(i);
		Assert.assertEquals(1, i);
	}
	
	
	
}
