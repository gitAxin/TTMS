$(function(){
	$("#modal-dialog").on('click', '.ok', doSaveOrUpdate);
	doInitProjectIdAndNames();//页面加载时获取项目id和名字填充到下拉列表中
	
	$("#modal-dialog").on('hidden.bs.modal',function(){
		$(this).off('click','.ok').removeData('id');
		clearFormData();
	})
})



function clearFormData(){
	
};


//当点击修改按钮时，加载模态框时，首先检查模态框上面是否有绑定id的值
//如果有则表示当前点击的为修改按钮，则执行此方法根据id查询出对象
function getObjectById(id){
	var url = "team/doFindObjectById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			//服务器返回对象后将对象填充到编辑框中
			setEditContent(result.data);
		}else{
			alert(result.message);
		}
		
	})
}
//将根据id查询返回的名称填充到编辑框中
function setEditContent(data){
	alert(JSON.stringify(data));
	$("#nameId").val(data.name);
	$("#selectId").find("option[value='"+data.projectId+"']").attr("selected",true);
	$("input[name='valid']:checked").val(),
	$("#editFormId input[name='valid']").each(function(){
		if($(this).val()==data.valid){
			$(this).prop("checked",true);
		}
	});
	$("#noteId").val(data.note);
}



function doSaveOrUpdate(){
	if ($("#editFormId").valid()) {
		// 1.获得表单数据
		var params = doGetEditFormData();
		// 2.将数据提交到服务端
		var url ="team/doSaveObject.do";
		var id =$("#modal-dialog").data("id");
		//如果可以获得id则代表是更新操作
		if(id){//添加id并改变url地址
			params.id=id;
			url="team/doUpdateObjectById.do";
		}
		$.post(url, params, function(result) {
			if (result.state == 1) {
				// 1)隐藏模态框
				$("#modal-dialog").modal("hide");
				// 2)重新查询列表数据
				doGetObjects();
			} else {
				alert(result.message);
			}
		})
	}
}


function doGetEditFormData(){
	var params = {
			"projectId":$("#selectId").val(),
			"name" : $("#nameId").val(),
			"valid": $("input[name='valid']:checked").val(),
			"note" : $("#noteId").val()
		};
	//console.log(JSON.stringify(params));
	return params;
}
//初始化参数
function doInitProjectIdAndNames(){
	var url="team/doFindPrjIdNames.do";
	/*$.getJSON(url,function(result){
		console.log(result);
		if(result.state==1){
			setProjectSelectOptions(result.data);//
		}else{
			alert(result.message);
		}
	})*/
	
	
	$.ajax({
		type:"get",
		url:"team/doFindPrjIdNames.do",
		dataType:"json",
		async:"false",
		error:function(){
			alert("服务器出错");
		},
		success:function(result){
			if(result.state==1){
				setProjectSelectOptions(result.data);//
			}else{
				alert(result.message);
			}
		}
	});
}

//填充select选项
function setProjectSelectOptions(list){
	var temp="<option value=[id]>[name]</option>";
	for( var i in list){
		$("#selectId").append(
				temp.replace("[id]",list[i].id)
				.replace("[name]",list[i].name)
				);
	}
	//填充完毕后,再获得模态框上面绑定的id,如果有绑定的id则说明是修改并根据id查询出团信息
	var id = $("#modal-dialog").data("id");
	if(id)getObjectById(id);
};