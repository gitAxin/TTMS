$(function(){
	$("#pageId").on('click','.pre,.next',toPage); //上一页下一页
	$("#pageId").on('click','.first',toFirst);//点击首页
	$("#pageId").on('click','.end',toEnd);//点击尾页
	
})

//转到首页
function toFirst(){
	console.log("首页");
	$("#pageId").data("pageCurrent",1);
	doGetObjects();
	
}


//转到尾页
function toEnd(){
	console.log("尾页");
	var pageCount= $("#pageId").data("pageCount");
	$("#pageId").data("pageCurrent",pageCount);
	doGetObjects();
}



//跳转到上一页或下一页
function toPage(){
	//var type =$(this).attr("class");
	//var  =e.target || e.srcElement;
	console.log(1);
	var type = $(this).attr("class");
	var pageCurrent = $("#pageId").data("pageCurrent");
	var pageCount=$("#pageId").data("pageCount");//获得pageCount属性key对应的值
	if('pre'==type && pageCurrent>1){//页数大于1时可以减
			pageCurrent--;
	}else if('next'==type && pageCurrent<pageCount){  //页数小于总页数时
			pageCurrent++;
	}
	$("#pageId").data("pageCurrent",pageCurrent);
	doGetObjects();
}

