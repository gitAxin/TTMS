/**
 * 加载表格时填入数据
 */
$(document).ready(function() {
	$("#modal-dialog").on('click', '.ok', doSaveOrUpdate);
	// release the click function
	//获得模态框中绑定的ID值
	var id = $("#modal-dialog").data("id");
	//假如id有值说明这是修改,然后根据id获得对象,初始化对象
	if(id)doGetObjectById(id);
	//解绑模态框上绑定的值
	$("#modal-dialog").on("hidden.bs.modal", function() {//当模态框隐藏时触发事件
		$(this).off('click', '.ok').removeData("id");//移除为.ok绑定的所有事件 并移除模态框上绑定的id
	});
});



function doGetObjectById(id){

	var url="project/doFindById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			//初始化表单数据
			doFillFormData(result.data);
		}else{
			alert(result.message);
		}
	});
}

//将获得的数据填充到form表单中
function doFillFormData(obj){
	$("#nameId").val(obj.name);
	$("#codeId").val(obj.code);
	$("#beginDateId").val(new Date(obj.beginDate).toLocaleDateString());
	$("#endDateId").val(new Date(obj.endDate).toLocaleDateString());
	$("#noteId").html(obj.note);
	//启用禁用
	$("#editFormId input[name='valid']").each(function(){
		
		if($(this).val()==obj.valid){
			$(this).prop("checked",true);
		}
	});
}


// 保存或更新数据
function doSaveOrUpdate() {

	if ($("#editFormId").valid()) {
		// 1.获得表单数据
		var params = doGetEditFormData();
		// 2.将数据提交到服务端
		var id=$("#modal-dialog").data("id");
		var url =id?"project/doUpdateProject.do":"project/doSaveProject.do";
		// console.log(JSON.stringify(params));
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

// 获得表单 数据
function doGetEditFormData() {
	var params = {
		"id":$("#modal-dialog").data("id"),
		"name" : $("#nameId").val(),
		"code" : $("#codeId").val(),
		"beginDate" : $("#beginDateId").val(),
		"endDate" : $("#endDateId").val(),
		"valid" : $("input[name='valid']:checked").val(),
		"note" : $("#noteId").val()
	};
	// 检测获得的结果
	return params;
}