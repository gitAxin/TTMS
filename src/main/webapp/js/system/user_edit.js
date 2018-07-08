$(document).ready(function() {
	setBtnAndTitle();
	loadRoleList();// 加载所有角色信息
	$("#editUserForm").on('click','#btn_ok', commitUserForm)
	$("#editUserForm").on('click','#btn_return', backListPage)
	$("#editUserForm").on('blur', '#userName', isExists)
	$("#editUserForm").on('keyup','#userPwd,#newPwd', checkPassword)// 当按键弹起验证输入的密码
	$("#editUserForm").on('blur', '#userPwd,#newPwd', checkPasswordLength)// 检测密码长度
	$("#editUserForm").on('blur', '#email', checkEmailFormat)// 检测邮箱格式是否正确
	$("#editUserForm").on('blur', '#mobile', checkMobileFormat)// 检测手机号格式是否正确
})

/** 如果点击的是修改,根据点击修改的用户的id修改用户信息后,填充到表单中 */
function doGetObjectById(id) {
	var url = "user/doGetObjectById.do";
	var params = {
		id : id
	};
	$.post(url, params, function(result) {
		if (result.state == 1) {
			setFormData(result.data);
			
		} else {
			alert(result.message);
		}
	});

}
/**修改用户信息时根据用户id查询返回来数据填充到表单中*/
function setFormData(data) {
	$("#userName").val(data.username);
	$("#userPwd").val(data.password);
	$("#email").val(data.email);
	$("#mobile").val(data.mobile);
	$("#editUserForm").data("username",data.username);
	var roleIdList = data.roleIdList;
	for(var i in roleIdList){
		$("#roleList input[name='roleId']").each(function(){
			if($(this).val()==roleIdList[i]){
				$(this).prop('checked',true);
			}
		});
	}
	

}

/** 光标失去焦点时 检测手机号码格式 */
function checkMobileFormat() {//

	var mobileValue = $("#mobile").val();
	var regExp = new RegExp(/^1\d{10}$/);
	if (regExp.test(mobileValue)) {
		$("#mobileMessage").css('display', 'none');
		$("#mobileMessage").text('');
		return true;
	} else {
		$("#mobileMessage").css('display', 'block');
		$("#mobileMessage").text('请输入正确的手机号!');
		return false;
	}
}

/** 光标失去焦点时检测邮箱格式 */
function checkEmailFormat() {
	var emailValue = $("#email").val();
	if (emailValue) {// 如果邮箱输入了值则继续执行(因为邮箱是可选项,可输可不输)

		var regExp = new RegExp(
				/^([a-zA-Z0-9]+(_|\.)?)*[a-zA-Z0-9]+@[a-zA-Z0-9]+\.(com|cn){1}$/);
		if (regExp.test(emailValue)) {// 判断邮箱格式是否正确
			$("#emailMessage").css('display', 'none');
			$("#emailMessage").text('');
			return true;
		} else {
			$("#emailMessage").css('display', 'block');
			$("#emailMessage").text('请输入正确的邮箱!');
			return false;
		}

	} else {
		$("#emailMessage").css('display', 'none');
		$("#emailMessage").text('');
		return true;
	}

}

/** 对象失去焦点时 检测密码长度 */
function checkPasswordLength() {
	var pwdValue;
	var idName;
	var id =$("#container").data('id');
	if(id){
		pwdValue=$("#newPwd").val();
		idName="#newPwdMessage";
		
	}else{
		pwdValue=$("#userPwd").val();
		idName="#passwordMessage";
	}
	if (pwdValue.length < 6) {
		$(idName).css('display', 'block');
		$(idName).text("请输入6位以上密码!");
		return false;
	} else {
		$(idName).css('display', 'none');
		$(idName).text("");
		return true;
	}
}

/** 按键弹起时判断密码强度,此函数每敲一个密码值松开按键时执行一次 */
function checkPassword() {
	
	var pwdValue;
	var idName;
	var id =$("#container").data('id');
	if(id){
		pwdValue=$("#newPwd").val();//获得表单 中输入的新密码(修改时)
		idName="#newPwdMessage";
	}else{
		pwdValue = $("#userPwd").val();// 获得表单输入的正个密码字符串
		idName="#passwordMessage";
	}
	
	
	
	

	if (pwdValue.length >= 6) {// 如果密码长度大于6位则隐藏提示信息
		$(idName).css('display', 'none');
		$(idName).text("");
	}

	/**
	 * 创建一个数组,数组中存放三个正则表达式, 0:除字母,数字以外的其它字符 1:字母 2:数字
	 */
	var regExp = [];
	regExp.push(/[^a-zA-Z0-9]/g);
	regExp.push(/[a-zA-Z]/g);
	regExp.push(/[0-9]/g);
	var src = 0;
	if (pwdValue.length >= 6) { // 如果密码和其中一个正则匹配则src++
		for (var i = 0; i < regExp.length; i++) {
			if (pwdValue.match(regExp[i])) {
				src++;
			}
		}
	}
	var percent = src / regExp.length * 100;// 计算匹配了百分比,根据数据修改样式
	$(".vali_pass_inner_progress").css('width', percent + "%");
	if (percent >= 0 && percent <= 50) {
		$(".vali_pass_inner_progress").attr("class",
				"vali_pass_inner_progress" + " error");
	} else if (percent > 50 && percent < 100) {
		$(".vali_pass_inner_progress").attr("class",
				"vali_pass_inner_progress" + " middle");
	} else if (percent == 100) {
		$(".vali_pass_inner_progress").attr("class",
				"vali_pass_inner_progress" + " strong");
	}

}
/** 查看用户是否存在 */
function isExists() {
	var url = "user/isExists.do";
	var params = {
		username : $("#userName").val()
	}
	$.getJSON(url, params, function(result) {
		if (result.state == 1) {
			if (result.data == 1) {
				var username=$("#editUserForm").data('username');
				var un=$("#userName").val();
				var message;
				var color;
				if(username==un){	
					message ="";
					color="green";
				}else{
					message="用户名已存在!";
					color="red";
				}
					$("#userNameMessage").css('display', 'block').css('color',
					color);
					
					$("#userNameMessage").text(message);
				
				
			} else if (result.data == 0) {
				$("#userNameMessage").css('display', 'block').css('color',
						'green');
				$("#userNameMessage").text("用户名可以使用");
			}
		} else {
			$("#userNameMessage").css('display', 'block').css('color', 'red');
			$("#userNameMessage").text(result.message);
		}

	})
}

// 提交用户表单数据
function commitUserForm() {
	var username = $("#userName").val();
	if (username == null || username == "") {
		alert("用户名不能为空!")
	}

	if (!checkPasswordLength()) {
		alert("请输入6位以上密码!");
		return;
	}
	if (!checkEmailFormat()) {
		alert("邮箱格式不正确,不能提交!")
		return;
	}
	if (!checkMobileFormat()) {
		alert("手机号格式不正确,不能提交!");
		return;
	}
	var id =$("#container").data('id');
	var url = id?"user/updateUserAndRole.do":"user/saveUserAndRole.do";
	var params = getFormData();
	if(id){
		params.id=id;
	}
	$.post(url, params, function(result) {
		if (result.state == 1) {
			alert("保存成功!");
			clearFormData();
			$("#container").load("user/listUI.do");
		} else {
			alert(result.message);
		}
	});
}

/** 清除表单数据 */
function clearFormData() {
	$("#userName").val("");
	$("#userPwd").val("");
	$("#newPwd").val("");
	$("#email").val("");
	$("#mobile").val("");
	$("#container").removeData('id');
	$("#editUserForm").removeData('username');
}

// 按下鼠标时mousedown
// 光标移出时blur
// 光标移入时focus
// 鼠标移出时mouseover
// 鼠标移入时mouseout
function getFormData() {
	var id = $("#container").data('id');
	var params = {
		username : $("#userName").val(),
		password : id?$("#newPwd").val():$("#userPwd").val(),
		email : $("#email").val(),
		mobile : $("#mobile").val(),
		roleIdStr : getSelectedRoleId()
	};
	return params;
}

/** 获取所有选中的角色id */
function getSelectedRoleId() {
	var roleIdStr = "";
	$("#roleList input[name='roleId']:checked").each(function() {
		if (roleIdStr == "") {
			roleIdStr += $(this).val();
		} else {
			roleIdStr += "," + $(this).val();
		}
	});
	return roleIdStr;
}

/** 加载所有角色信息 */
function loadRoleList() {
	var url = "user/doFindAllRole.do"
	$.getJSON(url, function(result) {
		if (result.state == 1) {
			setRoleList(result.data);
		} else {
			alert(result.message);
		}
	})
}

function setRoleList(list) {

	var roleId = $("#roleList");
	var template = "<input type='checkbox' name='roleId' value='[id]'/><span style='padding-right:30px;'>[name]</span>";
	for ( var i in list) {
		roleId.append(template.replace('[id]', list[i].id).replace('[name]',
				list[i].name));
	}
	//加载完所有角色信息后获取id,如果可以获取到则说明是修改,根据id查询用户信息及角色id
	var id = $("#container").data('id');
	if (id)
		doGetObjectById(id);
	
}

/** 点击返回时,返回到list页面 */
function backListPage() {
	clearFormData();
	$("#container").load('user/listUI.do');
}

/** 设置编辑页面及按钮名称 */
function setBtnAndTitle() {
	var title = '添加用户';
	var btn = '保存';
	var id = $("#container").data('id');
	if (id) {
		title = "修改用户";
		btn = '修改';
		$("#newPwdDiv").css('display','block');//显示新密码强度条
		$("#userPwdCue").css('display','none');//显示旧密码强度条
		$("#userPwd").attr("readOnly",'false');//旧密码框只读
	}
	$("#editTitle").text(title);
	$(".page-header .active").text(title);
	$("#btn_ok").val(btn);
}