var zTree;
$(document).ready(function() {
	doGetTeamNameAndId();
	$("#modal-dialog").on('click','.ok',doSaveOrUpdate)//点击保存按钮时执行保存或更新操作
	$("#editFormId").on('click','#typeId',showTreeForm);
	$("#typeLayer").on('click','.btn-confirm',getNoteName)//点击树菜单 确定按钮后执行函数
	.on('click','.btn-cancle',hideTreeMenu);//点击树菜单 取消按钮后执行函数
	$("#modal-dialog").on('hidden.bs.modal',function(){//当模态框隐藏时执行 解绑
		$(this).off('click','.ok').removeData('id');
		clearFormData();
	});
})


//根据id查询
function doGetObjectById(id){
	
	var url = "product/doGetObjectById.do";
	$.post(url,{id:id},function(result){
		if(result.state==1){
			$("#modal-dialog").modal();//当查询结果正确后再显示
			setEditFormData(result.data);
		}else{
			alert(result.message);
		}
		
	})
	
}
/**将返回的数据填充到表单中*/
function setEditFormData(obj){
	$("#codeId").val(obj.code);
	$("#nameId").val(obj.name);
	$("#teamId").val(obj.teamId);
	$("#exTextId").val(obj.exText);
	$("#minQtyId").val(obj.minQty);
	$("#typeId").val(obj.typeName);
	$("#priceId").val(obj.price);
	$("#nightsId").val(obj.nights);
	$("#noteId").val(obj.note);
	//将classID绑定到表单上
	$("#editFormId").data('classId',obj.classId);
}


//保存或更新操作
function doSaveOrUpdate(){
	
	var id =$("#modal-dialog").data('id');
	var url=id?"product/doUpdateObject.do":"product/doSaveObject.do";
	var params=getFormData();
	//console.log(params);
	if(id){params.id=id};
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal('hide');
			doGetObjects();
			alert("保存成功");
		}else{
			alert(result.state);
		}
		
	});
}
//清空表单数据
function clearFormData(){
	$("#codeId").val("");
	$("#nameId").val("");
	$("#teamId").val("");
	$("#exTextId").val("");
	$("#minQtyId").val("");
	$("#priceId").val("");
	$("#editFormId").removeData('classId');
	$("#typeId").val("");
	$("#nightsId").val("");
	$("#noteId").val("");
}

//获得表单数据
function getFormData(){
	var params={
			code:$("#codeId").val(),
			name:$("#nameId").val(),
			teamId:$("#teamId").val(),
			exText:$("#exTextId").val(),
			minQty:$("#minQtyId").val(),
			price:$("#priceId").val(),
			classId:$("#editFormId").data('classId'),
			nights:$("#nightsId").val(),
			state:$("#editFormId input[name='state']:checked").val(),
			note:$("#noteId").val()
	};
	return params;
	
}

//隐藏树菜单 
function hideTreeMenu(){
	$("#typeLayer").css('display','none');
	
}
//点击确定按钮后获得选中的节点信息点充到表单中
function getNoteName(){
	var notes= zTree.getSelectedNodes();
	if(notes.length){
		var note = notes[0];
		$("#typeId").val(note.name);
		$("#editFormId").data('classId',note.id);
	}
	//隐藏树菜单 
	hideTreeMenu();
}

function showTreeForm(){
	var url="product/findTypeNameAndId.do";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			loadTreeData(result.data);
		} else {
			alert(result.message);
		}

	});	
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
function loadTreeData(classesList){
	
	zTree = $.fn.zTree.init($("#typeTree"),//显示树的位置
			setting,//树的基本配置
			classesList);//树上显示的数据
	$("#typeLayer").css("display","block");//显示zTree树
}



function doGetTeamNameAndId() {
	var url = "product/findTeamNameAndId.do";
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			setEditFormTeam(result.data);
		} else {
			alert(result.message);
		}

	});
}

//填充表单中团信息下拉列表
function setEditFormTeam(teamsList) {
	var template = "<option value=[id]>[name]</option>";
	var teamId = $("#teamId");
	
	for ( var i in teamsList) {
		var option = template.replace('[id]', teamsList[i].id)
						.replace('[name]', teamsList[i].name);
		teamId.append(option);
	}
	//解决ajax执行的先后顺序  将填充完下拉先后再执行下列方法
	//尝试从模态框上获取绑定的id,如果可以获取到则表示当前执行的是修改操作(点击修改时,已经在模态框上绑定了id)
	var id = $("#modal-dialog").data('id');
	if(id)doGetObjectById(id);//根据id查询项目	
}
