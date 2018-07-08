package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.entity.Product;

public interface ProductDao extends BaseDao<Product> {

	List<Map<String,Object>> findAllObjects();
	
	List<Map<String,Object>> findPageObjects(@Param("page")PageObject page,
											@Param("product")Product product);
	
	/**获得总记录数*/
	Integer getRowCount(@Param("product")Product product);
	
	/**根据id修改产品信息包含团名,及所属分类名*/
	Map<String,Object> findObjectById(Integer id);
	
	/**用来测试查找ObjectById*/
	Product findObjectOne(Integer id);
	
	
	
	/**根据id修改产品状态(上架,下架)*/
	int stateById(@Param("ids")String[] ids,
					@Param("state")Integer state);
}
