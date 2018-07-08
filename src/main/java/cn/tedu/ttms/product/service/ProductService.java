package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.entity.Product;

public interface ProductService {
	
	
	List<Map<String,Object>> findAllObjects();

	
	Map<String,Object> findPageObjects(PageObject page,Product product);
	
	/**查询所有团的团名及id*/
	List<Map<String,Object>> findTeamNameAndId();
	
	/**查所有产品分类的分类名称 及id*/
	List<Map<String,Object>> findTypeNameAndId();
	
	/**插入产品*/
	int insertObject(Product product);
	
	/**根据id查询产品信息*/
	Map<String,Object> findObjectById(Integer id);
	
	/**更新产品信息*/
	void updateObject(Product product);
	
	
	/**根据id修改产品状态信息*/
	void stateById(String id,Integer state);
	
}
