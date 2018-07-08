$(function() {
	$("#queryFormId").on("click",".btn-search",doGetObjects); //查询
	$("#queryFormId").on("click",".btn-invalid,.btn-valid",doValidById);
	$("#queryFormId").on('click','.btn-add',showEditDiglog);
	$("#queryFormId").on('click','.btn-update',showEditDiglog);
	
//	$("#pageId").on('click','.pre',doPre);
//	$("#pageId").on('click','.next',doNext);
	
	
	//页面加载完成执行此方法
	//1.发起ajax请求(findObjects.do)
	//2.将返回的结果填充到content位置
	//fund01();
	//fund02();
	//fund03();
	doGetObjects();
});


function showEditDiglog(){
	var url="project/editUI.do";
	
	var title;
	if($(this).hasClass("btn-add")){
		title="添加项目";
	}
	
	if($(this).hasClass("btn-update")){
		title="修改项目";
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));//获取每一行绑定的id 然后绑定到模态框上
	}
	
	$("#modal-dialog .modal-body").load(url,function(){
		$(".modal-title").html(title);
		$('#modal-dialog').modal('show');
	})
}



/**启用禁用**/
function doValidById(){
	
	//1.判定触发的是启用还是禁用按钮(根据类选择器)
	var state; //定义一个状态值,表示状态
	if($(this).hasClass("btn-valid")){
		state=1;   //启用(将选中的记录的valid修改为1)
	}else{
		state=0;	//禁用
	}
	
	//2.获得选中的checkbox对应的id值(id是记录的唯一标识)
	 var checkedIds=getCheckedIds();
	var params={"checkedIds":checkedIds,"valid":state};
	//3.获得的数据通过ajax发送异步请求到服务器然后执行更新操作
	var url = "project/doValidById.do";
	$.post(url,params,function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message);
		}
		
	});
	
	
}

//获取所有选中的ID
function getCheckedIds(){
	var checkedIds='';
	//获得tbody对象中名字为projectId的input对象
	$('tbody input[name="projectId"]').each(function (){
		//迭代input对象
		//判定这个input对象是否是选中的input
		if($(this).is(":checked")){
			//将选中的checkbox的值拼接为字符串
			if(checkedIds==''){
				checkedIds+=$(this).val();
			}else{
				checkedIds+=","+$(this).val();
			}
		};
	})
	return checkedIds;
}

	

















// 获取项目信息
function doGetObjects() {
	var url = "project/findPageObjects.do";
	 //获得查询参数
	var params=getQueryFormData();
	//console.log("params:"+params.name+"/"+params.valid+"/"+params.pageCurrent);
	
	var pageCurrent=$("#pageId").data("pageCurrent");
	console.log("当前页:"+pageCurrent);
	if(pageCurrent){
		params.pageCurrent=pageCurrent;
	}else{
		params.pageCurrent=1;
	}
	
	$.post(url, params,function(result) {
		//设置表格内容
		if(result.state==1){
			//将表格分页方法数据绑定到元素上
			setTablePage(result.data.pageObject);
			setTableContent(result.data.list);
			setPag(result.data.pageObject);//输出页面信息
		}else{
			alert(result.message);
		}
	
	
	});
}

//获取模糊查询的参数
function getQueryFormData(){
	//JSON对象
	var params={
		"name":$("#searchNameId").val().trim(),
		"valid":$("#searchValidId").val(),
		//"pageCurrent":$("#pageId").data("pageCurrent")
	}
	return params;
}


//输出页码
function setPag(pageObject){
	$("#currentPage").html("当前第"+pageObject.pageCurrent+"页");
	$("#countPage").html("总共"+pageObject.pageCount+"页");
}


//将表格分页方法数据绑定到元素上
function setTablePage(pageObject){
	$("#pageId").data("pageCurrent",pageObject.pageCurrent);
	$("#pageId").data("pageCount",pageObject.pageCount);
}

//将从服务端获得的列表数据填充的表格中
function setTableContent(list){
	var tBody = $('#tbodyId');
	var temp="<td><input type='checkbox' name='projectId' value='[id]'/></td>"+
			"<td>[code]</td>" +
			"<td>[name]</td>" +
			"<td>[beginDate]</td>" +
			"<td>[endDate]</td>" +
			"<td>[valid]</td>" +
			"<td><button type='button'class ='btn btn-default btn-update'>修改</button></td>";
	tBody.empty();
	for ( var i in list) { // 循环一次取一行数据
		var tr = $('<tr></tr>');// 创建一对tr对象
		tr.data("id",list[i].id);
		tr.append(temp
				.replace('[id]',list[i].id)
				.replace('[code]',list[i].code)
				.replace('[name]',list[i].name)
				.replace('[beginDate]',new Date(list[i].beginDate).toLocaleDateString())
				.replace('[endDate]',new Date(list[i].endDate).toLocaleDateString())
				.replace('[valid]',list[i].valid?'启动':'禁用')
				);
		tBody.append(tr);
	}
	
}














/*  ********演示********    */



//跳转到上一页
function doPre(){ //上一页 (当前页减1)
	console.log("上一页");
	var pageCurrent = $("#pageId").data("pageCurrent");
	if(pageCurrent>1){//页数大于1时可以减
		pageCurrent--;
		$("#pageId").data("pageCurrent",pageCurrent);
	}
	doGetObjects();
}


//跳转到下一页
function doNext(){ //下一页(当前页加1)
	console.log("下一页");
	var pageCurrent = $("#pageId").data("pageCurrent");
	var pageCount=$("#pageId").data("pageCount");//获得pageCount属性key对应的值
	console.log(pageCount);
	if(pageCurrent<pageCount){
		pageCurrent++;
		$("#pageId").data("pageCurrent",pageCurrent);
	}
	doGetObjects();
}







function fund01() {
	$.ajax({
		"url" : "findObjects.do",
		"type" : "get",
		// "data":"",
		"dataType" : "json",
		"success" : function(result) {
			$("#content1").html(
					result.id + "," + result.code + "," + result.name);
		}
	});
}


function fund02() { // $.getJSON();默认get请求
	var url = "findObjects.do";
	$.getJSON(url, function(result) {
		$("#content2").html(result.id + "," + result.code + "," + result.name);

	})

}

function fund03() { // $.post(....)post请求
	var url = "findObjects.do";
	$.post(url, function(result) {
		$("#content3").html(result.id + "," + result.code + "," + result.name);

	});

}
