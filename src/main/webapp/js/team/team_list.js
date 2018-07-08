$(function(){
	doGetObjects();
	$("#queryFormId").on('click','.btn-search',doGetObjects);//查询
	$("#queryFormId").on('click','.btn-add',showEditDiglog);//点击添加时显示模态框
	$("#tbodyId").on('click','.btn-update',showEditDiglog);//点击修改时显示模态框
	$("#queryFormId").on('click','.btn-invalid,.btn-valid',doValidById);
	$("#theadId").on('change','input[name="checkAll"]',checkAll)//当点击选中所有时执行函数
	$("#tbodyId").on('change','input[name="teamId"]',checkTeamId)//当点击选中单行时判断是否全选中,如果全选中则改变全选按钮状态
})

//改变全选复选框的状态
function checkTeamId(){
	if(isCheckedAll()){//检查是否已经全部选中
		$("#theadId input[name='checkAll']").prop('checked',true);
	}else{
		$("#theadId input[name='checkAll']").prop('checked',false);
		
	}
	
	
}

/**判断是否全选中*/
function isCheckedAll(){
	var flag=true;
	//console.log(flag);
	$("#tbodyId input[name='teamId']").each(function(){
		if(!$(this).prop('checked')){
			flag=false;
		}
	});
	return flag;
}

//当点击全选按钮时 选中/或取消选中所有行
function checkAll(){
	
	if($(this).prop("checked")){
		
		$("#tbodyId input[name='teamId']").each(function(){
			$(this).prop('checked',true);
		});
	}else{
		$("#tbodyId input[name='teamId']").each(function(){
			$(this).prop('checked',false);
		});
		
		
	}
}

//根据id改变启用/禁用状态
function doValidById(){
	var state;
	if($(this).hasClass("btn-invalid")){
		state=0;
	}else{
		state=1;
	}
	var checkedId=getCheckedId();
	var params={
			valid:state,
			checkedId:checkedId
	};
	
	$.ajax({
		url:"team/doValid.do",
		type:"post",
		data:params,
		dataType:"json",
		error:function(){
			alert("服务器忙");
		},
		success:function(result){
			if(result.state==1){
				doGetObjects();
			}else{
				alert(result.message);
			}
		}
	});
}

//获得全部选中行的id,以字符串 1,2,3,4的形式返回
function getCheckedId(){
	var checkedId="";
	$("#tbodyId input[name='teamId']:checked").each(function(){
			var id=$(this).val();
			if(checkedId==""){
				checkedId+=id;
			}else{
				checkedId+=","+id;
			}
	});
	return checkedId;
}

//显示模态框
function showEditDiglog(){
	var title;
	var url ="team/doEditUI.do"
	if($(this).hasClass("btn-add")){
		title="添加团信息";
	}
	
	if($(this).hasClass("btn-update")){
		title="修改团信息";
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));//获取每一行绑定的id 然后绑定到模态框上
	}
	
	
	$("#modal-dialog .modal-body").load(url,function(){
		$(".modal-title").html(title);
		$("#modal-dialog").modal('show');
	})
}



//获得查询参数
function getSearchParam(){
	var params={
			"projectName":$("#searchNameId").val(),
			"valid":$("#searchValidId").val(),
			"pageCurrent":1
	};
	return params;
}


//页面加载后查询数据
function doGetObjects(){
	//1.url
	var url="team/doFindPageObjects.do";
	//2.params
	//获得查询的参数
	var params=getSearchParam();
	
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(pageCurrent){
		params.pageCurrent=pageCurrent;
	}else{
		params.pageCurrent=1;
	}
	if(params.projectName){
		params.pageCurrent=1;
	}
	//3.ajax(post)
	$.post(url,params,function(result){
		if(result.state==1){
			//填充表格
			setTableContent(result.data.list);
			//设置分页
			setPagination(result.data.pageObject);
			//填充页码数据
			setPageCode(result.data.pageObject);
		}else{
			alert(result.message);
		}
		
	})
}

function setPageCode(pageObject){
	$("#currentPage").html("当前第"+ pageObject.pageCurrent+"页");
	$("#countPage").html("共"+ pageObject.pageCount+"页");
}


//绑定分页数据
function setPagination(pageObject){
	$("#pageId").data("pageCurrent",pageObject.pageCurrent);
	$("#pageId").data("pageCount",pageObject.pageCount);
}



function setTableContent(list){
	var tBody = $('#tbodyId');
	var temp="<td><input type='checkbox' name='teamId' value='[id]'/></td>"+
			"<td>[name]</td>" +
			"<td>[projectName]</td>" +
			"<td>[valid]</td>" +
			"<td><button type='button'class ='btn btn-default btn-update'>修改</button></td>";
	tBody.empty();
	for ( var i in list) { // 循环一次取一行数据
		var tr = $('<tr></tr>');//创建一对tr对象
		tr.data("id",list[i].id);
		tr.append(temp
				.replace('[id]',list[i].id)
				.replace('[name]',list[i].name)
				.replace('[projectName]',list[i].projectName)
				.replace('[valid]',list[i].valid?'启动':'禁用')
				);
		tBody.append(tr);
	}
	
}