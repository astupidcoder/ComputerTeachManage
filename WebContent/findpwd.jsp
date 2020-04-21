<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ysu.util.SendEmail"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String userId=request.getParameter("userId");
String to=request.getParameter("email");
System.out.println("userId"+userId);
System.out.println("to"+to);
 SendEmail s=new SendEmail();
s.setTo(to);
if(s.send()){
	out.println("<script>alert('验证码已发送至您的邮箱中，请在下方输入收到的验证码');</script>");
}; 
int num=s.getNum();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.5.2/themes/default/easyui.css">
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.5.2/locale/easyui-lang-zh_CN.js"></script>
<script>
function submitForm(){
$("#fm").form("submit",{
	url:"resetPwd",
	onSubmit:function(){},
	success:function(r){
		var d=eval('('+r+')');
		if(d.success){
			$("#pwddg").dialog('open');
		}else{
			$.messager.alert("系统提示","验证码错误");
		}
	}
})
}

function modifyPwd(){
	var p1=$("#p1").val();
	var p2=$("#p2").val();
	if(p1==""){
		$.messager.alert("系统提示","密码不能为空！！");
		return;
	}
		if(p1!=p2){
			$("#ff").form("clear");
			$.messager.alert("系统提示","两次密码输入不一致！！");
		}else{
			$.messager.confirm(
				"提示信息",
				"您确定要修改密码吗？？",
				function(r){
					if(r){
						$("#ff").form("submit",{
							url:"modifyPwd",
							onSubmit:function(){
								
							},
							success:function(result){
								var result=eval('('+result+')');
								if(result.success){
									$.messager.alert("系统提示","密码修改成功！！！");
									$("#ff").form("clear");
									closeDialog();
								}else{
									$.messager.alert("系统提示","密码修改失败！！！");
									$("#ff").form("clear");
								}
							}
						});
					}
				}
			);
		
		}
}

function closeDialog(){
	$("#pwddg").dialog("close");
}
</script>
</head>
<body>
<form id="fm" method="post">
<h1>已向您的邮箱……<%= to.substring(to.indexOf("@")-4) %>发送验证码，请查收……</h1><br><br><br>
请输入验证码：<input type="text" name="verify" id="verify">
<input type="hidden" id="num" name="num" value=<%=num %>><a href="findpwd.jsp?email=<%=to %>&userId=<%=userId %>" class="easyui-linkbutton">重新发送</a><br>
<a class="easyui-linkbutton" href="javascript:submitForm()">提交</a>
</form>
<div class="easyui-dialog" name="pwdDialog" id="pwddg" title="重置密码"
style="width:400px;height:200px;padding:50px 40px" closed=true>
	  <form id="ff" method="post">
	 请输入密码： <input type="easyui-passwordbox" name="p1" id="p1"><br>
	 请再次输入密码： <input type="easyui-passwordbox" name="p2" id="p2"><br>
	 <input type="hidden" name="userId" value=<%=userId %>>
	 <a href="javascript:modifyPwd()" class="easyui-linkbutton">提交</a>
	 <a class="easyui-linkbutton" onClick="closeDialog()">返回</a>
	</form> 
</div>
</body>
</html>