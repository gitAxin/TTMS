package product.dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import common.dao.TestBaseDao;


public class testProductTypeDao extends TestBaseDao{
	
	
	@Test
	public void testInsertProduct(){
		ProductTypeDao ptd =ctx.getBean("productTypeDao",ProductTypeDao.class); 
		ProductType product = new ProductType();
		product.setName("快乐游学");
		product.setSort(4);
		product.setParentId(137);
		product.setNote("快乐游学...");
		product.setCreatedUser("admin");
		product.setModifiedUser("admin");
		int i=ptd.insertObject(product);
		System.out.println(i);
		Assert.assertEquals(1, i);
	}
	
	@Test
	public void testFindObject(){
		ProductTypeDao ptd =ctx.getBean("productTypeDao",ProductTypeDao.class);
		List list =ptd.findObjects();
		System.out.println(list);
	}

	
	@Test
	public void testFindIdAndName(){
		ProductTypeDao ptd = ctx.getBean("productTypeDao",ProductTypeDao.class);
		List list =ptd.findTreeNodes();
		System.out.println(list);
		Assert.assertNotEquals(null, list);
		
	}
	
	@Test
	public void testUpdateObject(){
		ProductTypeDao ptd = ctx.getBean("productTypeDao",ProductTypeDao.class);
		//测试查询
		Map<String,Object> map = ptd.findObjectById(138);
		System.out.println(map);
		//测试更新
		ProductType productType = new ProductType();
		productType.setParentId((Integer)map.get("parentId"));
		ptd.updateObject(productType);
	}
	
	
	
	
}
