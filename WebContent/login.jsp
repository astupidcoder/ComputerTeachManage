<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ysu.model.StuInfo" import="com.ysu.model.TeaInfo" import="java.io.*"%>
<%
session.invalidate();//让sesion失效，以免泄露刚才注册的人的信息
if(request.getAttribute("user")==null){
	Cookie[] cookies=request.getCookies();
	String userId=null;
	String password=null;
	String label=null;
	for(int i=0;cookies!=null&&i<cookies.length;i++){
		if(cookies[i].getName().equals("user")){
			label=cookies[i].getValue().split("-")[0];
			userId=cookies[i].getValue().split("-")[1];
			password=cookies[i].getValue().split("-")[2];
			if(label.equals("student")){
				
				pageContext.setAttribute("user",new StuInfo(Integer.parseInt(userId),password));
			}else{
				pageContext.setAttribute("user",new TeaInfo(Integer.parseInt(userId),password));
			}
		}
	}
}
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/jquery.slider.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.slider.css" />
<script>
 function openInputId(){
	 $("#dg").dialog('open');
	 
 }
 
 function submitForm(){
	 $("#fm").form("submit",{
		 /* url:"findpwd", */
		 success:function(r){
			 var result=eval('('+r+')');
			 if(result.success){
				 $.messager.alert("系统提示","学号和姓名验证成功！！！请点击下方按钮发送验证码！");
				 document.getElementById("a").setAttribute("href","findpwd.jsp?email="+result.email+"&userId="+$('#userID').val());
			 }
		 }
		 
	 })
	 
 }
</script>
</head>
<body>
<h1 align="center">计算机系课程设计教学管理系统</h1>
<div align="center" style="padding-top:50px">
<form name="myForm" action="login" method="post" class="form-signin">
	<table>
		<tr><td>用户名:<input type="text" name="userId" id="userId" class="form-control" value="${user.userId }" placeholder="请输入职工号或学号……"/><td></tr>
<input type="hidden" id="hd" name="hd" value="">
		<tr><td>&nbsp;密码:&nbsp;&nbsp;&nbsp;<input type="password" name="password" id="password" class="form-control" value="${user.password }" placeholder="请输入密码……"/></td></tr>
		<tr><td>滑块验证:
<div class="demo1">
		<div id="slider1" class="slider"></div>
		<!-- <div class="result">验证结果：<span id="result1"></span></div> -->
	</div>
<script>
$("#slider1").slider({
	callback: function(result) {
		$("#result1").text(result);
		if(result){
			$("#hd").val("1");
		}else{
			$("#hd").val("0");
		}
		}
	
});
</script>
</td></tr>
		<tr><td><font color="red">${error }</font></td></tr>
		<tr><td align="center"><input type="radio" name="career" value="教师">教师&nbsp;&nbsp;<input type="radio" name="career" value="学生">学生</td></tr>
		<tr><td align="center">&nbsp;&nbsp;&nbsp;<input type="submit" name="submit" value="登陆" class="btn btn-primary"/>&nbsp;&nbsp;<a href="register.jsp">注册</a>&nbsp;&nbsp;&nbsp;<font><a href="javascript:openInputId()">找回密码</a></font></td></tr>
	</table>
</form>
</div>
<div class="easyui-dialog" id="dg" name="dg" closed=true title="找回密码" style="width:400px;height:200px;padding:10px">
<form id="fm" name='fm' method="post" action="findpwd">
 请输入学号：<input class="easyui-numberbox" id="userID" name="userId"/><br>
 请输入姓名：<input class="easyui-textbox" id="name" name="name"/><br>
 <a class="easyui-linkbutton" href="javascript:submitForm()" onClick="">确认</a>
 </form>
 <a id="a" class="easyui-linkbutton" href="#" onClick="">发送验证码</a>
</div>
</body>
</html>