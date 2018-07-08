package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;


@Transactional
@Service
public class ProductTypeServiceImpl implements ProductTypeService{
	
	@Resource
	private ProductTypeDao productTypeDao;
	
	/**查询所有产品分类*/
	public List<Map<String, Object>> findObjects() {
		return productTypeDao.findObjects();
	}
	
	/**查询ID和名字用来显示Tree菜单 */
	public List<Map<String, Object>> findTreeNodes() {
		return productTypeDao.findTreeNodes();
	}

	public void insertObject(ProductType productType) {
		int i =productTypeDao.insertObject(productType);
		if(i==-1){
			throw new SaveRuntimeException("保存失败");
		}
		
	}
	
	/**更新类型信息*/
	@Override
	public void updateObject(ProductType productType) {
		int i =productTypeDao.updateObject(productType);
		if(i==-1){
			 throw new UpdateRuntimeException("更新失败");
		}
	}
	
	/**查找类型信息根据ID*/
	@Override
	public Map<String, Object> findObjectById(Integer id) {
		if(id==null){
			throw new RuntimeException("id can not be null");
		}
		Map<String,Object> map = productTypeDao.findObjectById(id);
		return map;
	}

	@Override
	public void deleteObject(Integer id) {
		//1.根据id查找子元素个数
		int count=productTypeDao.hasChilds(id);
		if(count>0)throw new UpdateRuntimeException("请先删除子类");
		//如果拋了异常下面就不会执行了
		//2.执行删除动作
		int rows =productTypeDao.deleteObject(id);
		if(rows==-1)throw new UpdateRuntimeException("删除失败");
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
