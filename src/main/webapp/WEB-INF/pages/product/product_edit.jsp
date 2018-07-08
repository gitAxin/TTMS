<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript">
	$('.datepicker').datepicker({
		format : 'yyyy/mm/dd',
		autoclose : true
	//选中自动关闭
	})
</script>
<div class="container">
	<form class="form-horizontal" role="form" id="editFormId">
		<div class="form-group">
			<label for="codeId" class="col-sm-2 control-label">产品编码:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control required" name="code"
					id="codeId" placeholder="请输入编码">
			</div>
		</div>
		<div class="form-group">
			<label for="nameId" class="col-sm-2 control-label">产品名称:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control required" name="name"
					id="nameId" placeholder="请输入名字">
			</div>
		</div>
		<div class="form-group">
			<label for="teamId" class="col-sm-2 control-label">所属团:</label>
			<div class="col-sm-10">
				<select id="teamId" name="team" class="form-control required">
					<option>请选择</option>
				</select>
			</div>
		</div>

		<div class="form-group">
			<label for="exTextId" class="col-sm-2 control-label">特殊提示:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control required" name="exText"
					id="exTextId" placeholder="...">
			</div>
		</div>

		<div class="form-group">
			<label for="minQtyId" class="col-sm-2 control-label">最低数量:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control required" name="minQty"
					id="minQtyId" placeholder="...">
			</div>
		</div>

		<div class="form-group">
			<label for="priceId" class="col-sm-2 control-label">产品单价:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control required" 
					name="price" id="priceId" placeholder="...">
			</div>
		</div>

		<div class="form-group">
			<label for="typeId" class="col-sm-2 control-label">所属分类:</label>
			<div class="col-sm-10">
				<input type="text" id="typeId" readonly="readonly"
					placeholder="上级分类"
					class="form-control required dynamicClear text-parentType load-product-type"
					style="cursor: pointer;">
			</div>
		</div>

		<div class="form-group">
			<label for="nightsId" class="col-sm-2 control-label">晚数:</label>
			<div class="col-sm-10">
				<input type="text" class="form-control required" name="nights"
					id="nightsId" placeholder="...">
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label">产品状态</label>
			<div class="col-md-10">
				<label class="radio-inline"> <input type="radio"
					name="state" checked value="0">待售
				</label> <label class="radio-inline"> <input type="radio"
					name="state" value="1">上架
				</label>
			</div>
		</div>
		<div class="form-group">
			<label for="noteId" class="col-sm-2 control-label">备注:</label>
			<div class="col-sm-10">
				<textarea class="form-control" name="note" id="noteId"></textarea>
			</div>
		</div>
	</form>
	

	<!-- 选择菜单 -->
	<div class="layui-layer layui-layer-page layui-layer-molv layer-anim"
		id="typeLayer" type="page" times="2" showtime="0" contype="object"
		style="z-index: 19891016; width: 300px; height: 450px; top: 100px; margin:0 150px; display: none">
		<div class="layui-layer-title" style=";">选择菜单</div>
		<div class="layui-layer-content" style="height: 358px;">
			<div style="padding: 10px;" class="layui-layer-wrap">
				<ul id="typeTree" class="ztree">

				</ul>
				<!-- 动态加载树 -->
			</div>
		</div>
		<span class="layui-layer-setwin"> <a
			class="layui-layer-ico layui-layer-close layui-layer-close1 btn_cancle"></a></span>
		<div class="layui-layer-btn layui-layer-btn-">
			<a class="layui-layer-btn0 btn-confirm">确定</a> <a
				class="layui-layer-btn1 btn-cancle">取消</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="${basePath}/js/product/product_edit.js"></script>

