<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生注册</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script>
function check(){
var name=document.forms["myForm"]["userName"].value;
var id=document.forms["myForm"]["userId"].value;
	if(name==""){
		alert("用户名不能为空");
		myForm.userName.focus();
		return false;
	}
		if(id==""){
			alert("学号不能为空");
			myForm.userId.focus();
			return false;
		}
	if(myForm.password.value==""){
		alert("密码不能为空");
		myForm.password.focus();
		return false;
	}
		if(myForm.password.value!=myForm.confirmpwd.value){
			alert("前后密码输入不一致");
			myForm.password.focus();
			return false;
		}
	return true;
}

/* function submitForm(){
	$("#myForm").form("submit",{
		url:"register",
		onSubmit:function(){
			return check();
		},
		success:function(r){
			alert("cc");
			 var result=eval('('+r+')');
			 if(!result.success){
				alert("该用户不是本校学生");
			}
		}
	})
} */
/**
 * 这段有问题，返回值取不到
 */
function submitForm(){
	 $("#myForm").form("submit",{
		  url:"register", 
		 success:function(r){
			 alert("dd5");
			 var result=eval('('+r+')');
			 if(result.success){
				 alert("是");
			 }else{
				 alert("否");
			 }
		 }
		 
	 })
	 
}
</script>
</head>

<body>
<div class="container" style="text-align:center">
	<form id="myForm" name="myForm" action="register" method="post" class="form-signin">
	<h2 class="form-sigin-heading">学生注册</h2>
	姓名:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="userName" class="form-control" id="userName" value="${stu.name }"><br>
	学号:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="userId" class="form-control" id="userId" value="${stu.userId }"><br>
	密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-passwordbox" name="password" id="password" value=${stu.password }><br><br>
	确认密码:<input class="easyui-passwordbox" name="confirmpwd" id="confirmpwd" value="${stu.password }"><br><br>
	邮箱:&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-validatebox" type="text" name="email" id="email" value="${stu.email }" required=true validType='email'/><br>
	电话:&nbsp;&nbsp;&nbsp;&nbsp;<input class="easyui-numberbox" name="tel" id="tel" value="${stu.tel }" required=true/><br><br><br>
	<button  class="easyui-linkbutton" onClick="submitForm()">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a type="button" class="btn btn-large btn-primary" href="login.jsp">返回</a>
	</form>
</div>

</body>
</html>