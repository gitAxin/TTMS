var zTree;//生成tree树的时候返回的节点信息
$(function(){
	
	$("#editTypeForm").on('click','.load-product-type',loadTypeTree);//点击后加载并获得填充zTree的数据
	$("#typeLayer").on('click','.btn-cancle',hideTypeTree)
	.on("click",".btn-confirm",setSelectedTypeNode);
	$("#editTypeForm").on('click','.btn-primary',doSaveOrUpdate);
	
	//$("#btn-save").click(doSaveOrUpdate);
	//首先判定当前页面中有没有Id值
	//如果有则根据id查询产品分类,
	//并将产品分类的信息填充到表单中
	var typeId=$("#container").data("typeId");
	//console.log(typeId);
	if(typeId)doGetObjectById(typeId);
})


//根据id查询产品分类
function doGetObjectById(typeId){
	var url = "productType/doFindObjectById.do";
	var params={"id":typeId};
	$.post(url,params,function(result){
		//console.log(JSON.stringify(result));
		if(result.state==1){
			setEditFormData(result.data)
		}else{
			alert(result.message);
		}
	})
}

//将产品分类的信息填充到表单中
function setEditFormData(data){
	$("#editTypeForm").data("parentId",data.parentId);
	$("#typeNameId").val(data.name);
	$("#parentNameId").val(data.parentName);
	$("#typeSortId").val(data.sort);
	$("#typeNoteId").val(data.note);
}


//执行保存或更新动作
function doSaveOrUpdate(){
	
	//1.获得表单数据
	var params=getEditFormData();
	//2.提交表单数据
	//2.1检查当前页面是否有绑定的typeId值
	var typeId = $("#container").data("typeId");
	var updateUrl ="productType/doUpdateObject.do";
	var saveUrl = "productType/doSaveObject.do";
	if(typeId)params.id=typeId;//2.2有的话则说明是修改,要在提交的表单中添加一个id值
	var url =typeId?updateUrl:saveUrl;//2.3根据typeId是否有值确定是更新还是添加操作
	//2.4提交数据到数据库
	$.post(url,params,function(result){
		if(result.state==1){
			alert("save ok");
			doClearFormData();//解除绑定的数据
			var url="productType/listUI.do?t="+Math.random(1000);
			$("#container").load(url);
		}else{
			alert(result.message);
		}
	})
	return false;
}
//获取输入的值
function getEditFormData(){
	var params={
			"name":$("#typeNameId").val(),
			"parentId":$("#editTypeForm").data("parentId"),
			"sort":$("#typeSortId").val(),
			"note":$("#typeNoteId").val()
	};
	return params;
}	


//解除绑定的数据
function doClearFormData(){
	
	$("#editTypeForm .dynamicClear").val("");
	$("#container").removeData("typeId");
	$("#editTypeForm").removeData("parentId");
}





//隐藏 typeTree
function hideTypeTree(){
	$("#typeLayer").css("display","none");
}
//设置选中的type节点
function setSelectedTypeNode(){
	//获得zTree中选中的节点
	var nodes =zTree.getSelectedNodes();
	//获得选中的第一个节点
	var node = nodes[0];
	//给parentNameId赋值
	$("#parentNameId").val(node.name);
	$("#editTypeForm").data("parentId",node.id);
	$("#typeLayer").css("display","none");
}






var setting = {
	data : {
		    simpleData : {
			enable : true,
			idKey : "id",  //节点数据中保存唯一标识的属性名称
			pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
			rootPId : null  //根节点id
		}
	}
}


/**加载产品分类信息,然后以zTree形式显示*/
function loadTypeTree(){
	var url = "productType/doFindTreeNodes.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			//初始化
			zTree = $.fn.zTree.init($("#typeTree"),//显示树的位置
					setting,//树的基本配置
					result.data);//树上显示的数据
			$("#typeLayer").css("display","block");//显示zTree树
		}else{
			alert(result.message);
		}
	})
}


