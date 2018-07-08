package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {
	
	@Resource
	private ProductTypeService productTypeService;

	
	/**
	 * 点击增加时返回一个编辑页面
	 * @return
	 */
	@RequestMapping("/editUI")
	public String editUI(){
		System.out.println("editUI");
		return "product/type_edit";
	}
	
	@RequestMapping("/listUI")
	public String listUI(){
		return "product/type_list";
	}
	
	@RequestMapping("/doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects(){
		List<Map<String,Object>> list = productTypeService.findObjects();
		return new JsonResult(list);
	}
	
	
	
	/**查询分类信息在zTree中进行数据呈现*/
	@RequestMapping("/doFindTreeNodes")
	@ResponseBody
	public JsonResult doFindTreeNodes(){
		System.out.println("doFindTreeNodes");
		List<Map<String,Object>> list = productTypeService.findTreeNodes();
		return new JsonResult(list);
	}
	
	
	/**保存类型信息*/
	@RequestMapping("/doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType productType){
		productTypeService.insertObject(productType);
		return new JsonResult();
	}
	
	/**根据id查找类型信息*/
	@RequestMapping("/doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Map<String,Object> map = productTypeService.findObjectById(id);
		return new JsonResult(map);
	}
	
	/**修改类型信息*/
	@RequestMapping("/doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(ProductType productType){
		productTypeService.updateObject(productType);
		return new JsonResult();
	}
	
	
	/**删除类型*/
	@RequestMapping("/doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		productTypeService.deleteObject(id);
		return new JsonResult();
	}
	
}
