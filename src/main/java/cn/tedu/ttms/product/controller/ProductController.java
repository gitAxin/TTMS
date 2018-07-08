package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.entity.Product;
import cn.tedu.ttms.product.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Resource
	private ProductService productService;
	
	
	/**产品列表页面*/
	@RequestMapping("/listUI")
	public String listUI(){
		System.out.println("product/listUI");
		return "product/product_list";
	}
	
	/**编辑框页面*/
	@RequestMapping("/editUI")
	public String editUI(){
		System.out.println("product/editUI");
		return "product/product_edit";
		
	}

	/**查询所有产品信息*/
	@RequestMapping("/findObjects")
	@ResponseBody
	public JsonResult findObjects(){
		List<Map<String,Object>> list =productService.findAllObjects();
		return new JsonResult(list);
	}
	
	
	/**根据分页信息,和模糊查询的条件查询数据*/
	@RequestMapping("/findPageObjects")
	@ResponseBody
	public JsonResult findPageObjects(PageObject page,Product product){
		Map<String,Object> map =productService.findPageObjects(page, product);
		return new JsonResult(map);
	}
	
	@RequestMapping("/findTeamNameAndId")
	@ResponseBody
	/**查询所有团名及团id 产品分类及产品分类id*/
	public JsonResult findTeamNameAndId(){
		List<Map<String,Object>> teamsList =productService.findTeamNameAndId();
		return new JsonResult(teamsList);
	}
	
	
	@RequestMapping("/findTypeNameAndId")
	@ResponseBody
	/**查询所有团名及团id 产品分类及产品分类id*/
	public JsonResult findTypeNameAndId(){
		List<Map<String,Object>> classesList =productService.findTypeNameAndId();
		return new JsonResult(classesList);
	}
	
	/**插入产品信息*/
	@RequestMapping("/doSaveObject.do")
	@ResponseBody
	public JsonResult doSaveObject(Product product){
		productService.insertObject(product);
		return new JsonResult();
	}
	
	
	/**根据id查询产品信息*/
	@RequestMapping("/doGetObjectById")
	@ResponseBody
	public JsonResult doGetObjectById(Integer id){
		System.out.println(id);
		Map<String,Object> map =productService.findObjectById(id);
		return new JsonResult(map);
	}
	
	/**更新Product信息*/
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Product product){
		productService.updateObject(product);
		return new JsonResult();
	}
	
	/**根据id修改产品状态信息*/
	@RequestMapping("/doStateById")
	@ResponseBody
	public JsonResult doStateById(String id,Integer state){
		productService.stateById(id, state);
		return new JsonResult();
		
	}
	
	
}
