package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.ttms.common.exception.SaveRuntimeException;
import cn.tedu.ttms.common.exception.UpdateRuntimeException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProductDao;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.Product;
import cn.tedu.ttms.product.service.ProductService;
import cn.tedu.ttms.team.dao.TeamDao;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	
	@Resource
	private ProductDao productDao;
	
	@Resource
	private TeamDao teamDao;
	
	@Resource
	private ProductTypeDao productTypeDao;

	/**查询所有产品信息*/
	@Override
	public List<Map<String, Object>> findAllObjects() {
		return productDao.findAllObjects();
	}

	/**查找分页信息*/
	@Override
	public Map<String, Object> findPageObjects(PageObject page, Product product) {
		//查找表中总记录数
		int rows =productDao.getRowCount(product);
		//将结果赋值给pageObject,
		page.setRowCount(rows);
		//根据pageObject中的分页信息,和Product中是否有查询条件项目 查询数据
		List<Map<String,Object>> list =productDao.findPageObjects(page, product);
		//创建一个map对象,用来存放返回给页面的信息
		Map<String,Object> map = new HashMap<String,Object>();
		//将pageObject对象添加到map中,返回给页面以显示
		map.put("page", page);
		//将根据页数查询出来的数据显示添加到map中,返回到页面显示
		map.put("list", list);
		return map;
	}
	
	/**查询所有团名及团id*/
	@Override
	public List<Map<String, Object>> findTeamNameAndId() {
		//查询所有团名及团id
		List<Map<String,Object>> teamsList = teamDao.findNameAndId();
		return teamsList;
	}

	/**查询所有产品分类名称及及分类id*/
	@Override
	public List<Map<String, Object>> findTypeNameAndId() {
		//	查询所有产品分类名及分类id
		List<Map<String,Object>> classesList = productTypeDao.findNameAndId();
		return classesList;
	}

	
	/**插入产品信息*/
	@Override
	public int insertObject(Product product) {
		int n =productDao.insertObject(product);
		if(n==-1)throw new SaveRuntimeException("保存失败");
		return n;
	}

	/**根据id查询产品信息*/
	@Override
	public Map<String,Object> findObjectById(Integer id) {
		if(id==null)throw new NullPointerException("id不能为空");
		Map<String,Object> map = productDao.findObjectById(id);
		if(map==null)throw new RuntimeException("查询信息失败");
		return map;
	}

	@Override
	public void updateObject(Product product) {
		if(product.getClassId()==0 || product.getClassId()==null)
			throw new UpdateRuntimeException("请选择所属分类");
		if(product.getTeamId()==0 || product.getTeamId()==null)
			throw new UpdateRuntimeException("请选择所属团");
		int n =productDao.updateObject(product);
		if(n==-1)throw new UpdateRuntimeException("更新失败,请稍候重试");
	}
	
	/**根据产品id修改产品状态*/
	@Override
	public void stateById(String id,Integer state) {
		if(id==null || id=="")throw  new IllegalArgumentException("至少选中一个");
		if(state!=1 && state!=2){
			throw new IllegalArgumentException("参数值不合法");
		}
		String[] ids = id.split(",");
		int n=productDao.stateById(ids, state);
		if(n==-1){
			throw new UpdateRuntimeException("更新失败");
		}
	}
	
	
	
	

	
	
	

	
	
	
	
	
}
