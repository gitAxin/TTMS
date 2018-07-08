$(function(){
	doGetObjects();//页面加载时加载产品信息
	$("#queryFormId").on('click','.btn-search',doGetObjects);//给查询按钮绑定事件
	$("#queryFormId").on('click','.btn-add',showEditModal);//给添加按钮绑定事件
	$("#queryFormId").on('click','.btn-update',showEditModal);//给修改按钮绑定事件
	$("#queryFormId").on('click','.btn-online,.btn-offline',doStateById);//给上架和下架按钮绑定事件
	$('#queryFormId').on('click','.btn-attachement',loadAttachement);//给附件按钮绑定事件
	$("#pageId").on('click','.first,.pre,.next,.end',jumpPage);//给首页,尾页,上一页,下一页,绑定事件
})


//上架,下架
function doStateById(){
	var state;
	var url="product/doStateById.do";
	//获得选中的id
	var id=getSelectedId();
	
	if($(this).hasClass("btn-online")){//如果点击上架按钮
		state=1;
	}
	if($(this).hasClass("btn-offline")){//如果点击下架按钮
		state=2;
	}
	
	var params={
		state:state,
		id:id
	};
	
	$.post(url,params,function(result){
		if(result.state==1){
			alert("修改成功")
			doGetObjects();
		}else{
			alert(result.message);
		}
		
	});
	
	
}



//获得选中的id
function getSelectedId(){
	var id =new String("");
	$("#tbodyId input[name='productId']:checked").each(function(){
		if(id.length==0){
			id+=$(this).val();
		}else{
			id+=","+$(this).val();
		}
	});
	if(id.length===0){
		alert("至少选中一个")
	}
	return id;
}
//点击增加按钮时显示模态框
function showEditModal(){
	var current=$(this);//当前点击的按钮
	var id;
	if($(this).hasClass("btn-add")){//如果点击的按钮中有此class则说明是添加
		$(".modal-title").html("添加信息");
	}

	if($(this).hasClass("btn-update")){ //否则是更新按钮
		$(".modal-title").html("修改信息");
		$("#modal-dialog").data('id',$(this).parent().parent().data("id"));
		
	}
	
	var url="product/editUI.do"
		$("#modal-dialog .modal-body").load(url,function(){//在模态框指定位置加载编辑页面
			if(current.hasClass("btn-add")){//如果当前点击的按钮时添加则立即显示
											//如果当前点击的更新按钮则在edit.js中当读取当前行据成功后再显示
				$("#modal-dialog").modal();
			}
		})
}



//当点击,首页,尾页,上一页,下一页时执行此函数
function jumpPage(){
	//首先获取绑定在节点上的当前页数据和总页数
	var pageCurrent =$("#pageId").data("pageCurrent");
	var pageCount =$("#pageId").data("pageCount");
	
	if($(this).hasClass("first")){//点击了首页
		console.log("首页")
		pageCurrent=1;//将当前页值改变为1
	}
	
	if($(this).hasClass("end")){//点击了尾页
		console.log("尾页")
		pageCurrent=pageCount;//将当前页值改变为最后一页
		
	}
	if($(this).hasClass("pre")){//点击了上一页
		console.log("上一页")
		if(pageCurrent>1){//当前页-1
			pageCurrent--;
		}else{
			alert("警告!已经是第1页了");
		}
	}
	
	if($(this).hasClass("next")){//点击了下一页
		console.log("下一页")
		if(pageCurrent<pageCount){ //当前页+1
			pageCurrent++;
		}else{
			alert("警告!已经是最后一页了");
		}
	}
	//重新将当前页数据绑定到节点上
	$("#pageId").data("pageCurrent",pageCurrent);
	//刷新页面
	doGetObjects();
}


//页面加载后获数分页数据
function doGetObjects(){
	
	var url="product/findPageObjects.do";
	var params=getQueryFormData();
	//获取节点上绑定页数信息
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(pageCurrent){//如果获取到则动态添加请求参数(当前页参数)
		params.pageCurrent=pageCurrent;
	}
	if($(this).hasClass("btn-search")){//如果点击的是查询按钮则将将当前页面设置为第一页
		params.pageCurrent=1;
	}
	$.post(url,params,function(result){
		if(result.state==1){
			//设置表格数据
			setTableContent(result.data.list);
			//将服务器返回的分页信息绑定节点上
			setPageData(result.data.page);
		}else{
			alert(result.message);
		}
	});
}



function getQueryFormData(){
	var params={
			name:$("#searchNameId").val(),
			state:$("#searchValidId").val()
	}
	return params;
}

//将服务器返回的分页信息绑定节点上
function setPageData(page){
	$("#pageId").data("pageCurrent",page.pageCurrent);
	$("#pageId").data("pageCount",page.pageCount);
	//在页面显示当前页和总页数
	$("#currentPage").html("当前第"+page.pageCurrent+"页");
	$("#countPage").html("总共"+page.pageCount+"页");
}


//填充表格数据
function setTableContent(list){
	
	var template="<td><input type='checkbox' name='productId' value=[id]></td>" +
			"<td>[code]</td>" +
			"<td>[productName]</td>" +
			"<td>[teamName]</td>" +
			"<td>[className]</td>" +
			"<td>[state]</td>" +
			"<td><input type='button' class='btn btn-default btn-update' value='修改'>" +
			"&nbsp;<input type='button' class='btn btn-default btn-text' value='祥情'></td>";
	var tbody=$("#tbodyId");
	tbody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.append(template.replace('[id]',list[i].id)
				.replace('[code]',list[i].code)
				.replace('[productName]',list[i].name)
				.replace('[teamName]',list[i].teamName)
				.replace('[className]',list[i].className)
				.replace('[state]',list[i].state==0?"预售":list[i].state==1?"上架":"下架"));
		tr.data("id",list[i].id);
		tbody.append(tr);
	}
}


//加载产品附件页面
function loadAttachement(){
	var url = "attach/uploadUI.do";
	$("#container").data('athType',1);
	$('#container').data('belongId',1);
	$('#container').load(url);
}