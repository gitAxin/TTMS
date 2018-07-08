$(document).ready(function(){
	doGetObjects();//加载用户信息
	$("#queryFormId").on('click','.btn-search',doGetObjects);//查询
	$("#queryFormId").on('click','.btn-add,.btn-update',loadEditPage);//加载编辑页面
	$("#tbodyId").on('click','.changeState',changeStateById);//启用/禁用
	
	
	
});
/**启用/禁用*/
function changeStateById(){
	var url="user/updateValidById.do"
	var params={
			valid:$(this).val(),
			userId:$(this).parent().parent().data('id')
	};
	$.post(url,params,function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}

/**加载编辑页面*/
function loadEditPage(){
	if($(this).hasClass("btn-update")){
		var id =$("#tbodyId input[name='userId']:checked").val();
		if(id){
			$("#container").data("id",id);
		}else{
			alert("请选中要修改的用户!");
			return;
		}
	}
	
	$("#container").load("user/editUI.do");
	
}


/**获取查询参数*/
function getSearchParam(){
	var params = {
			username:$("#userNameId").val(),
			mobile:$("#mobileId").val(),
			email:$("#emailId").val()
	};
	return params;
}

/**加载用户信息*/
function doGetObjects(){
	var params = getSearchParam();
	
	var pageCurrent =$("#pageId").data("pageCurrent");
	if($(this).hasClass("btn-search")){
		pageCurrent=1;
	}
	params.pageCurrent=pageCurrent;
	var url="user/doGetPageObjects.do";
	$.post(url,params,function(result){
		if(result.state==1){
			setTableData(result.data.userList);
			setPageData(result.data.page);
		}else{
			alert(result.message);
		}
	})
}



/**绑定分页数据*/
function setPageData(page){
	$("#pageId").data("pageCurrent",page.pageCurrent);
	$("#pageId").data("pageCount",page.pageCount);
}
/**填充用户信息*/
function setTableData(list){
	var tbody=$('#tbodyId');
	var template="<td><input type='radio' name='userId' value='[id]'/></td>" +
			"<td>[username]</td>" +
			"<td>[email]</td>" +
			"<td>[mobile]</td>" +
			"<td>[state]</td>" +
			"<td><button type='button' class='btn btn-default btn-xs changeState' value='[validChange]'>[stateStr]</button></td>";
	tbody.empty();
	for(var i in list){
		var state=list[i].valid==0?'<span class="label label-danger">禁用</span>':'<span class="label label-success">启用</span>';
		var validchange=list[i].valid==0?1:0;
		var stateStr=list[i].valid==0?"启用":"禁用";
		var tr = $('<tr></tr>');
		tr.data('id',list[i].id);
		tr.append(template.replace('[id]',list[i].id)
				.replace('[username]',list[i].username)
				.replace('[email]',list[i].email)
				.replace('[mobile]',list[i].mobile)
				.replace('[state]',state)
				.replace('[validChange]',validchange)
				.replace('[stateStr]',stateStr));
		tbody.append(tr);
	}
}