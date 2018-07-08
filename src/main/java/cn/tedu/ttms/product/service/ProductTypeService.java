package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.ProductType;

/**
 * 
 * @author 天大java
 *
 */
public interface ProductTypeService {
	
	/**查询产品分类一级相关子分类,并在treeGrid中进行显示*/
	List<Map<String,Object>> findObjects();
	/**查询分类信息在zTree中进行数据呈现*/
	List<Map<String,Object>> findTreeNodes();
	/**插入类型信息*/
	void insertObject(ProductType productType);
	
	/**更新类型信息*/
	void updateObject(ProductType productType);
	
	/**根据ID查找类型信息*/
	Map<String,Object> findObjectById(Integer id);
	
	
	void deleteObject(Integer id);
	
	
	
	
	
}
