$(function(){
	$('#uploadFormId').on('click','.btn-upload',doUpload)
	.on('click','.btn-download',doDownload);
	
	doGetObjects();
})




/**下载*/
function doDownload(){
	var id = $(this).parent().parent().data("id");
	var url='attach/doDownload.do?id='+id;
	document.location.href=url;
}





/**获取信息*/
function doGetObjects(){
	var url="attach/doFindObjects.do";
	$.getJSON(url,function(result){
		
		if(result.state==1){
			setTableRows(result.data);
		}else{
			alert(result.message);
		}
		
	});
	
}

//填充表格数据
function setTableRows(list){
	var tBody = $('#tbodyId');
	tBody.empty();
	var temp="<td><input type='checkbox' name='selectItem'/></td>"+
			"<td>[title]</td>" +
			"<td>[name]</td>" +
			"<td>[contentType]</td>" +
			"<td><input type='button' class='btn btn-primary btn-download' value='下载'/></td>";
	
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		var td = temp.replace('[id]',list[i].id)
				.replace('[title]',list[i].title)
				.replace('[name]',list[i].fileName)
				.replace('[contentType]',list[i].contentType)
		tr.append(td);
		tBody.append(tr);
	}
}


/**上传*/
function doUpload(){
	console.log(123);
	url="attach/doSaveObject.do";
	//异步提交表单
	$("#uploadFormId").ajaxSubmit({
		url:url,
		type:"post",
		data:{"athType":1,"belongId":1},//假数据:beblongId是某个产品的ide
		dataType:'json',
		success:function(result){
			if(result.state==1){
				alert("ok");
				doGetObjects();
			}else{
				alert(result.message);
			}
		}
	})
	
}


