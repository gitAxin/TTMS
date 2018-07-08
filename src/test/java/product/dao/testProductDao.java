package product.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProductDao;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.Product;
import cn.tedu.ttms.team.dao.TeamDao;
import common.dao.TestBaseDao;

public class testProductDao extends TestBaseDao{
	
	@Test
	public void testFindAllObjects(){
		ProductDao pd = ctx.getBean("productDao",ProductDao.class);
		List<Map<String,Object>> list =pd.findAllObjects();
		System.out.println(list);
	}
	
	
	@Test
	public void testFindPageObjects(){
		ProductDao pd = ctx.getBean("productDao",ProductDao.class);
		PageObject page = new PageObject();
		Product product = new Product();
		product.setName("船");
		int row =pd.getRowCount(product);
		page.setRowCount(row);
		System.out.println("共多少条记录:"+row);
		List<Map<String,Object>> list =pd.findPageObjects(page, product);
		for(Map<String,Object> map:list){
			Set<Entry<String,Object>> set = map.entrySet();
			System.out.println(set);
		}
	}
	
	
	@Test
	public void testFindNameAndIdForTeam(){
		TeamDao teamDao = ctx.getBean("teamDao",TeamDao.class);
		List<Map<String,Object>> list =teamDao.findNameAndId();
		System.out.println(list);
	}
	
	@Test
	public void testFindNameAndIdForClass(){
		ProductTypeDao ptd = ctx.getBean("productTypeDao",ProductTypeDao.class);
		List<Map<String,Object>> list =ptd.findNameAndId();
		System.out.println(list);
	}
	
	@Test
	public void testInsertObject(){
		ProductDao pd = ctx.getBean("productDao",ProductDao.class);
		Product product = new Product();
		product.setCode("MMMMM");
		product.setName("故宫游");
		product.setTeamId(1);
		product.setExText("特别提示");
		product.setMinQty(50);
		product.setPrice(666.0);
		product.setClassId(138);
		product.setNights(3);
		product.setState(1);
		product.setNote("备注");
		product.setCreatedUser("admin");
		int n =pd.insertObject(product);
		System.out.println(n);
	}
	
	/**测试根据id查询产品信息*/
	@Test
	public void testFindObjectById(){
		ProductDao pd = ctx.getBean("productDao",ProductDao.class);
			Map<String,Object> map= pd.findObjectById(1);
			System.out.println(map);
			
	}
	
	/**修改产品信息*/
	@Test
	public void testUpdateObject(){
		ProductDao pd = ctx.getBean("productDao",ProductDao.class);
		Product product =pd.findObjectOne(1);
		product.setName("游船");
		product.setState(1);
		System.out.println(product);
		int n =pd.updateObject(product);
		System.out.println(n);
	}
	
	/**测试根据id修改产品状态信息*/
	@Test
	public void testStateById(){
		ProductDao pd = ctx.getBean("productDao",ProductDao.class);
		int n  =pd.stateById(new String[]{"4","5"}, 2);
		System.out.println(n);
	}
	

}
