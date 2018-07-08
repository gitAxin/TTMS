$(document).ready(function() {
  $('#pagination').on('click','.firstPage,.upperPage,.nextPage,.lastPage,.jump',jumpPage);
});

//分页显示
function setPagination(pageObject){
	//初始化首页，上一页，下一页，尾页
	$('.firstPage,.upperPage,.nextPage,.lastPage').parent().show();
	//初始化页面当前页
	$('#currentPage').text(pageObject.pageCurrent+'/'+pageObject.pageCount);
	//初始化页面总页数
	$('#totalCount').text(pageObject.rowCount);
	//初始化页面元素的数据绑定
	$("#pagination").data('curPage',pageObject.pageCurrent);
	$("#pagination").data('lastPage',pageObject.pageCount);
	//当前页面是第一页则隐藏首页和上一页
	if(pageObject.pageCurrent=='1'){
	  $('.firstPage,.upperPage').parent().hide();
	}
	//当前页面是尾页初始化隐藏尾页和下一页
	if(pageObject.pageCurrent==pageObject.pageCount){
	  $('.nextPage,.lastPage').parent().hide();
	}
}
//跳转到下一页
function jumpPage(){
	//获取点击对象的类选择器
	var classVal = $(this).attr('class');
	//获得当前页面绑定的分页数据
	var lastPage =  $('#pagination').data('lastPage');
	var currentPage=$('#pagination').data('curPage');
	//根据类选择器的不同跳转到不同页面
	if('firstPage' == classVal){
		currentPage = 1;
	}
	if('nextPage' == classVal&&currentPage<lastPage){
		currentPage = currentPage+1;
	}
	if('upperPage' == classVal&&currentPage>1){
		currentPage = currentPage-1;
	}
	if('lastPage' == classVal){
		currentPage =lastPage;
	}
	//获得跳转页面输入的页码数据
	if('jump' == classVal){
		var pageInput = $('#pageSelect').val();
		if(pageInput>lastPage){
			$('#pageSelect').val(lastPage);
			currentPage = lastPage;
		}else if(pageInput<1){
			$('#pageSelect').val(1);
			currentPage = 1;
		}else{
			currentPage = pageInput;
		}
	}
	$('#pagination').data('curPage',currentPage);
	//执行查询操作
	doGetObjects();
}
