$(function() {
	doGetObjects();
	$("#formHead").on('click', '.btn-add,.btn-update', loadEditPage)
	.on('click','.btn-delete',doDeleteById);
	

})



function doDeleteById(){
	var typeId = getSelectedId();
	if(typeId==-1){
		return false;
	}
	
	var url="productType/doDeleteObject.do";
	var params={"id":typeId}
	$.post(url,params,function(result){
			if(result.state==1){
				alert("删除成功");
				doGetObjects();//更新列表
			}else{
				alert(result.message);
			}
	})
}



/**
 * 加载编辑页面
 */
function loadEditPage() {
	var url = "productType/editUI.do";
	//检查是否是更新操作
	if ($(this).hasClass("btn-update")) {
		//首先获得选中的记录id(在修改页面要根据id执行查询操作)
		var id=getSelectedId();
		if(id==-1){
			return false;
		}
		//如果id有值,则将id绑定到当前页面的container对象上
		$("#container").data("typeId", id);
	}
	
	//在container位置异步加载url
	//其中container为index.jsp中的一个id
	$("#container").load(url);
}

/** 获取选中产品分类的ID */
function getSelectedId() {
	var selections = $("#typeTable").bootstrapTreeTable("getSelections");//返回数组
	if (selections.length == 0) {
		alert("请先选中一个");
		return -1;
	}
	return selections[0].id;
}



/** 声明一个表头信息 */
var columns = [ {
	field : 'selectItem',
	radio : true
}, {
	title : '分类id',
	field : 'id',//数据库返回的字段
	visible : false,
	align : 'center',//水平显示位置
	valign : 'middle',//垂直显示位置
	width : '80px'//列宽
}, {
	title : '分类名称',
	field : 'name',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
}, {
	title : '上级分类',
	field : 'parentName',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '180px'
}, {
	title : '排序号',
	field : 'sort',
	align : 'center',
	valign : 'middle',
	sortable : true,
	width : '100px'
} ]

function doGetObjects() {

	var url = "productType/doFindObjects.do";
	var id = "typeTable";
	var table = new TreeTable(id, url, columns); //id 为显示treeTable的表格id columns表头列名
	table.setIdField("id");//设置记录返回的id值 选中后返回的字段
	table.setCodeField("id");//设置记录分级的字段
	table.setParentCodeField("parentId");//设置记录分级的父级字段
	table.setExpandColumn(2);// 设置展开列
	table.setExpandAll(false);// 默认是否展开
	table.init();

	// $.getJSON(url,function(result){
	// console.log(result.data[0]);
	// if(result.state==1){
	// console.log(result.data);
	// $("#typeTable").html(result.data);
	// }else{
	// alert(result.message);
	// }
	//		
	// })

}